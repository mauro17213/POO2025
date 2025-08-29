package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateGestion;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2RescateGestiones;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Rescates;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.servicios.aseguramiento.AfiliadoServicio;
import com.saviasaludeps.savia.ejb.servicios.autorizacion.AuSolicitudAdjuntoServicio;
import com.saviasaludeps.savia.ejb.servicios.contratacion.CntPrestadorServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateSedeRemoto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author iavenegas
 */
@Stateless
@Remote(AuAnexo2RescateRemoto.class)
public class AuAnexo2RescateServicio extends GenericoServicio implements AuAnexo2RescateRemoto {

    @EJB
    private AuAnexo2RescateSedeRemoto auAnexo2RescateSedeRemoto;

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT a) FROM AuAnexo2Rescates a ";
            strQuery += "JOIN a.auAnexo2RescateGestionesList aarg ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE a.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE a.id > 0 ";
            }
            //no listar estado
            strQuery += " AND a.estado <> " + AuAnexo2Rescate.ESTADO_NO_REQUIERE + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND a.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += " AND a.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND a.fechaHoraCrea BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59'";
                            break;
                        case "fechaHoraRescate":
                            strQuery += " AND a.fechaHoraRescate BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59'";
                            break;
                        case "fuenteOrigen":
                            strQuery += " AND a.fuenteOrigen = " + e.getValue() + " ";
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery += "AND a.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery += "AND a.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "asegAfiliado.nombreCompleto":
                            strQuery += " AND (a.asegAfiliadosId.primerNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR a.asegAfiliadosId.segundoNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR a.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR a.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR CONCAT(a.asegAfiliadosId.primerNombre, ' ', COALESCE(a.asegAfiliadosId.segundoNombre,''), CASE WHEN COALESCE(a.asegAfiliadosId.segundoNombre,'') <> '' THEN ' ' ELSE '' END, a.asegAfiliadosId.primerApellido, ' ', COALESCE(a.asegAfiliadosId.segundoApellido,'')) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "cntPrestadorSedeOrigen.nombreSede":
                            strQuery += " AND a.cntPrestadorSedesOrigenId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeDestino.nombreSede":
                            strQuery += " AND a.cntPrestadorSedesDestinoId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "auAnexo2.id":
                            strQuery += " AND a.auAnexo2.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "pePrograma.descripcionPrograma":
                            strQuery += " AND a.peProgramasId.descripcionPrograma LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoRescate":
                            strQuery += " AND a.tipoRescate = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeMotivoRescateCodigo":
                           strQuery += " AND aarg.maeMotivoRescateCodigo = '" + (String) e.getValue() + "' ";
                           break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<AuAnexo2Rescate> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo2Rescate> listResult = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT a FROM AuAnexo2Rescates a ";
            strQuery += "JOIN a.auAnexo2RescateGestionesList aarg ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE a.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE a.id > 0 ";
            }
            //no listar estado
            strQuery += " AND a.estado <> " + AuAnexo2Rescate.ESTADO_NO_REQUIERE + " ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND a.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += " AND a.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += " AND a.fechaHoraCrea BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59'";
                            break;
                        case "fechaHoraModifica":
                            strQuery += " AND a.fechaHoraModifica BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59'";
                            break;
                        case "fechaHoraRescate":
                            strQuery += " AND a.fechaHoraRescate BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59'";
                            break;
                        case "fuenteOrigen":
                            strQuery += " AND a.fuenteOrigen = " + e.getValue() + " ";
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery += "AND a.asegAfiliadosId.maeTipoDocumentoId = " + e.getValue() + " ";
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery += "AND a.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "asegAfiliado.nombreCompleto":
                            strQuery += " AND (a.asegAfiliadosId.primerNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR a.asegAfiliadosId.segundoNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR a.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR a.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR CONCAT(a.asegAfiliadosId.primerNombre, ' ', COALESCE(a.asegAfiliadosId.segundoNombre,''), CASE WHEN COALESCE(a.asegAfiliadosId.segundoNombre,'') <> '' THEN ' ' ELSE '' END, a.asegAfiliadosId.primerApellido, ' ', COALESCE(a.asegAfiliadosId.segundoApellido,'')) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "cntPrestadorSedeOrigen.nombreSede":
                            strQuery += " AND a.cntPrestadorSedesOrigenId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "cntPrestadorSedeDestino.nombreSede":
                            strQuery += " AND a.cntPrestadorSedesDestinoId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "auAnexo2.id":
                            strQuery += " AND a.auAnexo2.id LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "pePrograma.descripcionPrograma":
                            strQuery += " AND a.peProgramasId.descripcionPrograma LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "tipoRescate":
                            strQuery += " AND a.tipoRescate = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeMotivoRescateCodigo":
                           strQuery += " AND aarg.maeMotivoRescateCodigo = '" + (String) e.getValue() + "' ";
                           break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "asegAfiliado.maeTipoDocumento":
                        strQuery += "a.asegAfiliadosId.maeTipoDocumentoId "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "asegAfiliado.numeroDocumento":
                        strQuery += "a.asegAfiliadosId.numeroDocumento "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "asegAfiliado.nombreCompleto":
                        strQuery += "a.asegAfiliadosId.primerNombre "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestadorSedeOrigen.nombreSede":
                        strQuery += "a.cntPrestadorSedesOrigenId.nombre  "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestadorSedeDestino.nombreSede":
                        strQuery += "a.cntPrestadorSedesDestinoId.nombre  "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "pePrograma.descripcionPrograma":
                        strQuery += " a.peProgramasId.descripcionPrograma "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "a." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "a.id DESC";
            }
            List<AuAnexo2Rescates> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexo2Rescates entidad : list) {
                listResult.add(castEntidadNegocioCorto(entidad));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public AuAnexo2Rescate consultar(int id) throws Exception {
        AuAnexo2Rescate objResult = new AuAnexo2Rescate();
        try {
            objResult = castEntidadNegocioLargo((AuAnexo2Rescates) getEntityManager().find(AuAnexo2Rescates.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objResult;
    }

    @Override
    public int insertar(AuAnexo2Rescate obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static AuAnexo2Rescate castEntidadNegocioLargo(AuAnexo2Rescates ent) {
        AuAnexo2Rescate obj = new AuAnexo2Rescate();
        obj.setId(ent.getId());
        if (ent.getAuAnexos2Id() != null) {
            obj.setAuAnexo2(new AuAnexo2(ent.getAuAnexos2Id().getId()));
        }
        if (ent.getAuAnexos3Id() != null) {
            obj.setAuAnexo3(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        }
        if (ent.getAucHospitalizacionesId() != null) {
            obj.setAucHospitalizacion(new AucHospitalizacion(ent.getAucHospitalizacionesId().getId()));
        }
        if (ent.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }
        if (ent.getPeProgramasId() != null) {
            obj.setPePrograma(new PePrograma(ent.getPeProgramasId().getId()));
            obj.getPePrograma().setDescripcionPrograma(ent.getPeProgramasId().getDescripcionPrograma());
            obj.getPePrograma().setCodigoPrograma(ent.getPeProgramasId().getCodigoPrograma());
        }

        obj.setAsegAfiliado(AfiliadoServicio.castEntidadNegocioLargo(ent.getAsegAfiliadosId()));
        obj.setTipoRescate(ent.getTipoRescate());
        obj.setMaeAfiliadoTipoDocumentoId(ent.getMaeAfiliadoTipoDocumentoId());
        obj.setMaeAfiliadoTipoDocumentoValor(ent.getMaeAfiliadoTipoDocumentoValor());
        obj.setMaeAfiliadoTipoDocumentoCodigo(ent.getMaeAfiliadoTipoDocumentoCodigo());
        obj.setAfiliadoNumeroDocumento(ent.getAfiliadoNumeroDocumento());
        if (ent.getCntPrestadoresOrigenId() != null) {
            obj.setCntPrestadorOrigen(
                    CntPrestadorServicio.castEntidadNegocio(ent.getCntPrestadoresOrigenId())
            );

        }
        if (ent.getCntPrestadoresDestinoId() != null) {
            obj.setCntPrestadorDestino(
                    CntPrestadorServicio.castEntidadNegocio(ent.getCntPrestadoresDestinoId())
            );
        }

        if (ent.getCntPrestadorSedesOrigenId() != null) {
            obj.setCntPrestadorSedeOrigen(
                    CntPrestadorServicio.castPrestadorSedesEntidadNegocio(ent.getCntPrestadorSedesOrigenId())
            );
        }

        if (ent.getCntPrestadorSedesDestinoId() != null) {
            obj.setCntPrestadorSedeDestino(
                    CntPrestadorServicio.castPrestadorSedesEntidadNegocio(ent.getCntPrestadorSedesDestinoId())
            );
        }
        if (ent.getAuAnexo2RescateGestionesList() != null) {
            List<AuAnexo2RescateGestion> gestiones = new ArrayList();
            ent.getAuAnexo2RescateGestionesList().forEach(gestion -> {
                gestiones.add(AuAnexo2RescateGestionServicio.castEntidadNegocio(gestion));
            });
            obj.setAuAnexo2RescateGestionList(gestiones);
        }
        if (ent.getAuSolicitudAdjuntosList() != null) {
            obj.setAuSolicitudAdjuntosList(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuSolicitudAdjuntosList()));
        }
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setMotivoConsulta(ent.getMotivoConsulta());
        obj.setEstado(ent.getEstado());
        obj.setDescripcion(ent.getDescripcion());
        obj.setFechaHoraRescate(ent.getFechaHoraRescate());

        //auditoria
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());

        return obj;
    }

    public static AuAnexo2Rescate castEntidadNegocioCorto(AuAnexo2Rescates ent) {
        AuAnexo2Rescate obj = new AuAnexo2Rescate();
        obj.setId(ent.getId());
        if (ent.getAuAnexos2Id() != null) {
            obj.setAuAnexo2(new AuAnexo2(ent.getAuAnexos2Id().getId()));
        }
        if (ent.getAuAnexos3Id() != null) {
            obj.setAuAnexo3(new AuAnexo3(ent.getAuAnexos3Id().getId()));
        }
        if (ent.getAucHospitalizacionesId() != null) {
            obj.setAucHospitalizacion(new AucHospitalizacion(ent.getAucHospitalizacionesId().getId()));
        }
        if (ent.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }
        if (ent.getPeProgramasId() != null) {
            obj.setPePrograma(new PePrograma(ent.getPeProgramasId().getId()));
            obj.getPePrograma().setDescripcionPrograma(ent.getPeProgramasId().getDescripcionPrograma());
            obj.getPePrograma().setCodigoPrograma(ent.getPeProgramasId().getCodigoPrograma());
        }
        AsegAfiliado afiliado = new AsegAfiliado(ent.getAsegAfiliadosId().getId());
        afiliado.setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
        afiliado.setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
        afiliado.setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
        afiliado.setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
        afiliado.setMaeTipoDocumento(ent.getAsegAfiliadosId().getMaeTipoDocumentoId());
        afiliado.setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
        afiliado.setMaeTipoDocumentoCodigo(ent.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
        afiliado.setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
        obj.setAsegAfiliado(afiliado);
        obj.setMaeAfiliadoTipoDocumentoId(ent.getMaeAfiliadoTipoDocumentoId());
        obj.setMaeAfiliadoTipoDocumentoValor(ent.getMaeAfiliadoTipoDocumentoValor());
        obj.setMaeAfiliadoTipoDocumentoCodigo(ent.getMaeAfiliadoTipoDocumentoCodigo());
        obj.setAfiliadoNumeroDocumento(ent.getAfiliadoNumeroDocumento());
        obj.setTipoRescate(ent.getTipoRescate());
        if (ent.getCntPrestadoresOrigenId() != null) {
            obj.setCntPrestadorOrigen(new CntPrestador(ent.getCntPrestadoresOrigenId().getId()));
        }
        if (ent.getCntPrestadoresDestinoId() != null) {
            obj.setCntPrestadorDestino(new CntPrestador(ent.getCntPrestadoresDestinoId().getId()));
        }

        if (ent.getCntPrestadorSedesOrigenId() != null) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede(ent.getCntPrestadorSedesOrigenId().getId());
            prestadorSede.setNombreSede(ent.getCntPrestadorSedesOrigenId().getNombre());
            prestadorSede.setCodigoHabilitacionSede(ent.getCntPrestadorSedesOrigenId().getCodigoHabilitacion());
            obj.setCntPrestadorSedeOrigen(prestadorSede);
        }
        if (ent.getCntPrestadorSedesDestinoId() != null) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede(ent.getCntPrestadorSedesDestinoId().getId());
            prestadorSede.setNombreSede(ent.getCntPrestadorSedesDestinoId().getNombre());
            obj.setCntPrestadorSedeDestino(prestadorSede);
        }
        
        if (ent.getAuAnexo2RescateGestionesList() != null && ent.getEstado() == AuAnexo2Rescate.ESTADO_RESCATADO) {
                AuAnexo2RescateGestiones gestion = null;
                for (AuAnexo2RescateGestiones rescateGestion : ent.getAuAnexo2RescateGestionesList()) {
                    if (rescateGestion.getMaeMotivoRescateCodigo() != null) {
                        gestion = rescateGestion;
                        break;
                    }
                }
            
            if (gestion != null) {
                obj.setMaeMotivoRescateCodigo(gestion.getMaeMotivoRescateCodigo());
                obj.setMaeMotivoRescateValor(gestion.getMaeMotivoRescateValor());
                obj.setFechaHoraDireccionamiento(gestion.getFechaHoraDireccionamiento());
            }
            
        }
        
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setMotivoConsulta(ent.getMotivoConsulta());
        obj.setEstado(ent.getEstado());
        obj.setDescripcion(ent.getDescripcion());
        obj.setFechaHoraRescate(ent.getFechaHoraRescate());

        //auditoria
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());

        return obj;
    }

    public static AuAnexo2Rescates castNegocioEntidad(AuAnexo2Rescate obj) {
        AuAnexo2Rescates ent = new AuAnexo2Rescates();
        ent.setId(obj.getId());
        if (obj.getAuAnexo2() != null) {
            ent.setAuAnexos2Id(new AuAnexos2(obj.getAuAnexo2().getId()));
        }
        if (obj.getAuAnexo3() != null) {
            ent.setAuAnexos3Id(new AuAnexos3(obj.getAuAnexo3().getId()));
        }
        if (obj.getAucHospitalizacion() != null) {
            ent.setAucHospitalizacionesId(new AucHospitalizaciones(obj.getAucHospitalizacion().getId()));
        }
        if (obj.getEmpresa() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }
        if (obj.getPePrograma() != null) {
            ent.setPeProgramasId(new PeProgramas(obj.getPePrograma().getId()));
        }
        ent.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        ent.setMaeAfiliadoTipoDocumentoId(obj.getMaeAfiliadoTipoDocumentoId());
        ent.setMaeAfiliadoTipoDocumentoCodigo(obj.getMaeAfiliadoTipoDocumentoCodigo());
        ent.setMaeAfiliadoTipoDocumentoValor(obj.getMaeAfiliadoTipoDocumentoValor());
        ent.setAfiliadoNumeroDocumento(obj.getAfiliadoNumeroDocumento());
        ent.setAfiliadoPrimerNombre(obj.getAfiliadoPrimerNombre());
        ent.setAfiliadoSegundoNombre(obj.getAfiliadoSegundoNombre());
        ent.setAfiliadoPrimerApellido(obj.getAfiliadoPrimerApellido());
        ent.setAfiliadoSegundoApellido(obj.getAfiliadoSegundoApellido());
        if (obj.getCntPrestadorOrigen() != null) {
            ent.setCntPrestadoresOrigenId(new CntPrestadores(obj.getCntPrestadorOrigen().getId()));
        }
        if (obj.getCntPrestadorSedeOrigen() != null) {
            ent.setCntPrestadorSedesOrigenId(new CntPrestadorSedes(obj.getCntPrestadorSedeOrigen().getId()));
        }
        if (obj.getCntPrestadorDestino() != null) {
            ent.setCntPrestadoresDestinoId(new CntPrestadores(obj.getCntPrestadorDestino().getId()));
        }
        if (obj.getCntPrestadorSedeDestino() != null) {
            ent.setCntPrestadorSedesDestinoId(new CntPrestadorSedes(obj.getCntPrestadorSedeDestino().getId()));

        }
        ent.setTipoRescate(obj.getTipoRescate());
        ent.setFuenteOrigen(obj.getFuenteOrigen());
        ent.setMotivoConsulta(obj.getMotivoConsulta());
        ent.setEstado(obj.getEstado());
        ent.setDescripcion(obj.getDescripcion());
        ent.setFechaHoraRescate(obj.getFechaHoraRescate());
        //auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;
    }

    @Override
    public List<AuAnexo2Rescate> consultarxFechas(String desde, String hasta, Integer estado, String codigoHabilitacion) throws Exception {
        List<AuAnexo2Rescate> listResult = new ArrayList();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strQuery = "FROM AuAnexo2Rescates "
                + " WHERE cntPrestadoresDestinoId.codigoMinSalud = :codigoHab "
                + " AND estado = :estado"
                + " AND fechaHoraCrea BETWEEN :fh_desde AND :fh_hasta";
        try {
            List<AuAnexo2Rescates> list = getEntityManager().createQuery(strQuery)
                    .setParameter("fh_desde", dateFormat.parse(desde))
                    .setParameter("fh_hasta", dateFormat.parse(hasta))
                    .setParameter("estado", estado)
                    .setParameter("codigoHab", codigoHabilitacion)
                    .getResultList();
            for (AuAnexo2Rescates obj : list) {
                listResult.add(castEntidadNegocioCorto(obj));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<AuAnexo2Rescate> consultaPacientesWS(String tipodoc, String doc, String codHabilitacion) throws Exception {
        List<AuAnexo2Rescate> listResult = new ArrayList();

        String strQuery = "FROM AuAnexo2Rescates "
                + "WHERE maeAfiliadoTipoDocumentoCodigo = :tipodoc "
                + "AND afiliadoNumeroDocumento = :doc "
                + "AND cntPrestadoresDestinoId.codigoMinSalud = :codigoHab "
                + "AND estado IN (" + AuAnexo2Rescate.ESTADO_PENDIENTE + "," + AuAnexo2Rescate.ESTADO_GESTION + ") "
                + "ORDER BY fechaHoraCrea DESC ";
        try {
            List<AuAnexo2Rescates> list = getEntityManager().createQuery(strQuery)
                    .setParameter("tipodoc", tipodoc)
                    .setParameter("doc", doc)
                    .setParameter("codigoHab", codHabilitacion)
                    .getResultList();
            for (AuAnexo2Rescates obj : list) {
                listResult.add(castEntidadNegocioCorto(obj));
            }

        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public void actualizarEstado(AuAnexo2Rescate obj) throws Exception {
        try {
            String sql = "UPDATE AuAnexo2Rescates a SET "
                    + " a.estado=:estado,"
                    + " a.descripcion=:descripcion,"
                    + " a.usuarioModifica=:usuarioModifica,"
                    + " a.terminalModifica=:terminalModifica,"
                    + " a.fechaHoraModifica=:fechaHoraModifica,"
                    + " a.fechaHoraRescate=:fechaHoraRescate"
                    + " WHERE a.id =:id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", obj.getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("fechaHoraRescate", obj.getFechaHoraRescate());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarFechaRescate(int idRescate, Date fecha) throws Exception {
        try {
            String sql = "UPDATE AuAnexo2Rescates a SET "
                    + " a.fechaHoraRescate=:fechaRescate"
                    + " WHERE a.id =:id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("fechaRescate", fecha);
            query.setParameter("id", idRescate);

            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarDescripcion(int idRescate, String descripcion) throws Exception {
        try {
            String sql = "UPDATE AuAnexo2Rescates a SET "
                    + " a.descripcion=:descripcion"
                    + " WHERE a.id =:id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("descripcion", descripcion);
            query.setParameter("id", idRescate);

            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<AuAnexo2Rescate> consultarxAnexo2(int anexo2) throws Exception {
        List<AuAnexo2Rescate> rescates = new ArrayList();

        String strQuery = "FROM AuAnexo2Rescates "
                + "WHERE auAnexos2Id.id = :anexo2 ";
        try {
            List<AuAnexo2Rescates> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("anexo2", anexo2)
                    .getResultList();
            for (AuAnexo2Rescates obj : list) {
                rescates.add(castEntidadNegocioCorto(obj));
            }

        } catch (NoResultException e) {
            rescates = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rescates;
    }

    @Override
    public List<AuAnexo2Rescate> consultarPorAnexo3(int anexo3) throws Exception {
        List<AuAnexo2Rescate> rescates = new ArrayList();

        String strQuery = "FROM AuAnexo2Rescates "
                + "WHERE auAnexos3Id.id = :anexo3 ";
        try {
            List<AuAnexo2Rescates> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("anexo3", anexo3)
                    .getResultList();
            for (AuAnexo2Rescates obj : list) {
                rescates.add(castEntidadNegocioCorto(obj));
            }

        } catch (NoResultException e) {
            rescates = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rescates;
    }

    @Override
    public List<AuAnexo2Rescate> consultarPorNoaptoRescateAnexo2(int idcntPrestadorSedesOrigen, int idAsegAfiliado) throws Exception {
        List<AuAnexo2Rescate> rescates = new ArrayList<>();

        StringBuilder strQuery = new StringBuilder("SELECT aar.id FROM au_anexo2_rescates aar WHERE aar.id > 0 ");
        strQuery.append("AND aar.estado IN (").append(AuAnexo2Rescate.ESTADO_PENDIENTE).append(", ").append(AuAnexo2Rescate.ESTADO_GESTION).append(") ");
        strQuery.append("AND aar.cnt_prestador_sedes_origen_id = :idcntPrestadorSedesOrigen ");
        strQuery.append("AND aar.aseg_afiliados_id = :idAsegAfiliado ");
        strQuery.append("AND aar.au_anexos2_id = ").append("(SELECT aar.au_anexos2_id FROM au_anexo2_rescates aar WHERE aar.id > 0 AND aar.au_anexos2_id IS NOT NULL ORDER BY aar.fecha_hora_crea DESC LIMIT 1) ORDER BY aar.id DESC");
        try {
            List<Object[]> list = getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("idcntPrestadorSedesOrigen", idcntPrestadorSedesOrigen)
                    .setParameter("idAsegAfiliado", idAsegAfiliado).getResultList();
            if (list != null) {
                for (Object anexo2Item : list) {
                    AuAnexo2Rescate item = new AuAnexo2Rescate();
                    item.setId((Integer) anexo2Item);
                    rescates.add(item);
                }
            }
        } catch (NoResultException e) {
            rescates = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rescates;
    }

    @Override
    public List<AuAnexo2Rescate> consultarPorNoaptoRescateAnexo3(int idcntPrestadorSedesOrigen, int idAsegAfiliado) throws Exception {
        List<AuAnexo2Rescate> rescates = new ArrayList<>();

        StringBuilder strQuery = new StringBuilder("SELECT aar.id FROM au_anexo2_rescates aar WHERE aar.id > 0 ");
        strQuery.append("AND aar.estado IN (").append(AuAnexo2Rescate.ESTADO_PENDIENTE).append(", ").append(AuAnexo2Rescate.ESTADO_GESTION).append(") ");
        strQuery.append("AND aar.cnt_prestador_sedes_origen_id = :idcntPrestadorSedesOrigen ");
        strQuery.append("AND aar.aseg_afiliados_id = :idAsegAfiliado ");
        strQuery.append("AND aar.au_anexos3_id = ").append("(SELECT aar.au_anexos3_id FROM au_anexo2_rescates aar WHERE aar.id > 0 AND aar.au_anexos3_id IS NOT NULL ORDER BY aar.fecha_hora_crea DESC LIMIT 1) ORDER BY aar.id DESC");

        try {
            List<Object[]> list = getEntityManager().createNativeQuery(strQuery.toString())
                    .setParameter("idcntPrestadorSedesOrigen", idcntPrestadorSedesOrigen)
                    .setParameter("idAsegAfiliado", idAsegAfiliado).getResultList();
            if (list != null) {
                for (Object anexo3Item : list) {
                    AuAnexo2Rescate item = new AuAnexo2Rescate();
                    item.setId((Integer) anexo3Item);
                    rescates.add(item);
                }
            }

        } catch (NoResultException e) {
            rescates = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rescates;
    }

    @Override
    public List<AuAnexo2Rescate> rescatesPorHospitalizacion(int idHospitalizacion) throws Exception {
        List<AuAnexo2Rescate> rescates = new ArrayList();

        String strQuery = "FROM AuAnexo2Rescates "
                + "WHERE aucHospitalizacionesId.id = :idHospitalizacion ";
        try {
            List<AuAnexo2Rescates> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("idHospitalizacion", idHospitalizacion)
                    .getResultList();
            for (AuAnexo2Rescates obj : list) {
                rescates.add(castEntidadNegocioCorto(obj));
            }

        } catch (NoResultException e) {
            rescates = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rescates;
    }

    @Override
    public Boolean aplicaRescateHospitalizacionPrograma(int idHospitalizacion, int idPrograma) throws Exception {
        int cant = 0;
        String strQuery = "SELECT COUNT(a) FROM AuAnexo2Rescates a "
                + "WHERE a.aucHospitalizacionesId.id = :idHospitalizacion AND a.peProgramasId.id = :idPrograma"
                + " AND a.estado IN(" + AuAnexo2Rescate.ESTADO_PENDIENTE + "," + AuAnexo2Rescate.ESTADO_RESCATADO + "," + AuAnexo2Rescate.ESTADO_GESTION + ")";
        try {

            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idHospitalizacion", idHospitalizacion)
                    .setParameter("idPrograma", idPrograma)
                    .getSingleResult();

        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant == 0;
    }

    @Override
    public Boolean rescatesPendientesHospitalizacion(int idHospitalizacion) throws Exception {
        int cant = 0;

        String strQuery = "SELECT COUNT(a) FROM AuAnexo2Rescates a"
                + " WHERE a.aucHospitalizacionesId.id = :idHospitalizacion";
        try {
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idHospitalizacion", idHospitalizacion)
                    //                    .setParameter("estado", AuAnexo2Rescate.ESTADO_PENDIENTE)
                    .getSingleResult();

        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant > 0;
    }

    @Override
    public Boolean tieneRescateAnexo3(int idAfiliado, int idSede, Date fecha) throws Exception {
        int cant = 0;

        String strQuery = "SELECT COUNT(a) FROM AuAnexo2Rescates a WHERE a.asegAfiliadosId.id=:idAfiliado "
                + "AND a.cntPrestadorSedesOrigenId.id =:idSede AND a.fuenteOrigen=:fuenteOrigen AND a.fechaHoraCrea >= :fecha";
        try {
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("idAfiliado", idAfiliado)
                    .setParameter("idSede", idSede)
                    .setParameter("fecha", fecha)
                    .setParameter("fuenteOrigen", AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO3)
                    .getSingleResult();

        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant > 0;
    }

    @Override
    public List<AuAnexo2Rescate> rescatesAfiliadoHistorico(int afiliado) throws Exception {
        List<AuAnexo2Rescate> rescates = new ArrayList();

        String strQuery = "SELECT p FROM AuAnexo2Rescates p WHERE p.asegAfiliadosId.id =:afiliado";
        try {
            List<AuAnexo2Rescates> list = getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("afiliado", afiliado)
                    .getResultList();
            for (AuAnexo2Rescates obj : list) {
                rescates.add(castEntidadNegocioCorto(obj));
            }

        } catch (NoResultException e) {
            rescates = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rescates;
    }
}
