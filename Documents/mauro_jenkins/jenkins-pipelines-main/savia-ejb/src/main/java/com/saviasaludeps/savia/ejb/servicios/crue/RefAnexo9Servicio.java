/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9DatoClinico;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Estado;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Gestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Gestiones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9Remoto;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefAnexo9Remoto.class)
@Local(RefAnexo9Local.class)
public class RefAnexo9Servicio extends GenericoServicio implements RefAnexo9Remoto, RefAnexo9Local {
    
    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT DISTINCT COUNT(r) FROM RefAnexos9 r ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "refAnexo9DatoClinico.triage":
                            strQuery += "INNER JOIN r.refAnexo9DatosClinicosList d ";
                            break;
                        case "refAnexo9Gestion.maeMotivoValor":
                            strQuery += "INNER JOIN r.refAnexo9GestionesList g ";
                            break;
                    }
                }
            }
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE r.id > 0 ";
            } else {
                strQuery += "WHERE r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND r.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroSolicitud":
                            strQuery += "AND r.numeroSolicitud = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeEstadoId":
                            strQuery += "AND r.maeEstadoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "refAnexo9Gestion.maeMotivoValor":
//                            strQuery += "AND g.maeMotivoValor = '" + (String) e.getValue() + "' and g.maeTipoId in( " + RefAnexo9Gestion.ESTADO_ANULADA + ", " + RefAnexo9Gestion.ESTADO_CANCELADA + " ) and r.maeEstadoId in ( " + RefAnexo9Estado.ESTADO_ANULADA + ", " + RefAnexo9Estado.ESTADO_CANCELADA + " ) ";
                            strQuery += "AND g.maeMotivoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraSolicitud":
                            strQuery += "AND r.fechaHoraSolicitud BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59' ";
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery += "AND r.asegAfiliadosId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery += "AND r.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "asegAfiliado.nombres":
                            strQuery += " AND (r.asegAfiliadosId.primerNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.segundoNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR CONCAT(r.asegAfiliadosId.primerNombre, ' ', COALESCE(r.asegAfiliadosId.segundoNombre,''), CASE WHEN COALESCE(r.asegAfiliadosId.segundoNombre,'') <> '' THEN ' ' ELSE '' END, r.asegAfiliadosId.primerApellido, ' ', COALESCE(r.asegAfiliadosId.segundoApellido,'')) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND r.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maServicioSolicitaId":
                            strQuery += "AND r.maServicioSolicitaId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND r.cntPrestadorSedesId.cntPrestadoresId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += "AND r.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "refAnexo9DatoClinico.triage":
                            strQuery += "AND d.triage = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND r.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "fuenteOrigen":
                            strQuery += "AND r.fuenteOrigen = '" + (String) e.getValue() + "' ";
                            break;
                        case "diagnosticoEmergente":
                            strQuery += "AND r.diagnosticoEmergente = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND r.fechaHoraCrea BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                } else {
                    strQuery += "AND r.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND r.fechaHoraCrea <= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
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
    public List<RefAnexo9> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RefAnexo9> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT DISTINCT r FROM RefAnexos9 r ";

            boolean flag = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "refAnexo9DatoClinico.triage":
                            strQuery += "INNER JOIN r.refAnexo9DatosClinicosList d ";
                            break;
                        case "refAnexo9Gestion.maeMotivoValor":
                            strQuery += "INNER JOIN r.refAnexo9GestionesList g ";
                            flag = false;
                            break;
                    }
                }
            }
            if (paramConsulta.getOrden() != null && flag) {
                if (paramConsulta.getOrden().equals("refAnexo9Gestion.maeMotivoValor")) {
                    strQuery += "INNER JOIN r.refAnexo9GestionesList g ";
                }
            }
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE r.id > 0 ";
            } else {
                strQuery += "WHERE r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND r.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "numeroSolicitud":
                            strQuery += "AND r.numeroSolicitud = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeEstadoId":
                            strQuery += "AND r.maeEstadoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "refAnexo9Gestion.maeMotivoValor":
                            strQuery += "AND g.maeMotivoValor = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaHoraSolicitud":
                            strQuery += "AND r.fechaHoraSolicitud BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59' ";
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery += "AND r.asegAfiliadosId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery += "AND r.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";

                            break;
                        case "asegAfiliado.nombres":
                            strQuery += " AND (r.asegAfiliadosId.primerNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.segundoNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR CONCAT(r.asegAfiliadosId.primerNombre, ' ', COALESCE(r.asegAfiliadosId.segundoNombre,''), CASE WHEN COALESCE(r.asegAfiliadosId.segundoNombre,'') <> '' THEN ' ' ELSE '' END, r.asegAfiliadosId.primerApellido, ' ', COALESCE(r.asegAfiliadosId.segundoApellido,'')) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += "AND r.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maServicioSolicitaId":
                            strQuery += "AND r.maServicioSolicitaId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.maeTipoDocumentoId":
                            strQuery += "AND r.cntPrestadorSedesId.cntPrestadoresId.maeTipoDocumentoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += "AND r.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento = '" + (String) e.getValue() + "' ";
                            break;
                        case "refAnexo9DatoClinico.triage":
                            strQuery += "AND d.triage = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND r.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "fuenteOrigen":
                            strQuery += "AND r.fuenteOrigen = '" + (String) e.getValue() + "' ";
                            break;
                        case "diagnosticoEmergente":
                            strQuery += "AND r.diagnosticoEmergente = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta1() != null) {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND r.fechaHoraCrea BETWEEN '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' AND '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                } else {
                    strQuery += "AND r.fechaHoraCrea >= '" + formatoCorto.format(paramConsulta.getParametroConsulta1()) + " 00:00:00' ";
                }
            } else {
                if (paramConsulta.getParametroConsulta2() != null) {
                    strQuery += "AND r.fechaHoraCrea <= '" + formatoCorto.format(paramConsulta.getParametroConsulta2()) + " 23:59:59' ";
                }
            }
            
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                if (paramConsulta.getOrden().equals("asegAfiliado.maeTipoDocumento")) {
                    strQuery += "r.asegAfiliadosId.maeTipoDocumentoId "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("asegAfiliado.numeroDocumento")) {
                    strQuery += "r.asegAfiliadosId.numeroDocumento "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("asegAfiliado.nombres")) {
                    strQuery += "r.asegAfiliadosId.primerNombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("cntPrestadorSede.nombreSede")) {
                    strQuery += "r.cntPrestadorSedesId.nombre "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else if (paramConsulta.getOrden().equals("refAnexo9Gestion.maeMotivoValor")) {
                    strQuery += "g.maeMotivoValor "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                } else {
                    strQuery += "r." + paramConsulta.getOrden() + " "
                            + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                }
            } else {
                strQuery += "r.id DESC";
            }
            List<RefAnexos9> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RefAnexos9 per : list) {
                listResult.add(castEntidadNegocioLista(per, paramConsulta));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public RefAnexo9 consultar(int id) throws Exception {
        RefAnexo9 objResult = new RefAnexo9();
        try {
            objResult = castEntidadNegocio((RefAnexos9) getEntityManager().find(RefAnexos9.class, id));
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
    public int insertar(RefAnexo9 obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(RefAnexo9 obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexos9 SET tipo = :tipo, "
                    + "numeroSolicitud = :numeroSolicitud, "
                    + "fechaHoraSolicitud = :fechaHoraSolicitud, "
                    + "fechaHoraRegiostro = :fechaHoraRegiostro, "
                    + "aplicaNoIpsContrato = :aplicaNoIpsContrato, "
                    + "aplicaNoAfiliado = :aplicaNoAfiliado, "
                    + "maEspecialidadesId = :maEspecialidadesId, "
                    + "maEspecialidadCodigo = :maEspecialidadCodigo, "
                    + "maEspecialidadValor = :maEspecialidadValor, "
                    + "maServicioSolicitaId = :maServicioSolicitaId, "
                    + "maServicioSolicitaCodigo = :maServicioSolicitaCodigo, "
                    + "maServicioSolicitaValor = :maServicioSolicitaValor, "
                    + "motivo = :motivo, "
                    + "maServicioRemiteId = :maServicioRemiteId, "
                    + "maServicioRemiteCodigo = :maServicioRemiteCodigo, "
                    + "maServicioRemiteValor = :maServicioRemiteValor, "
                    + "ubicacion = :ubicacion, "
                    + "cama = :cama, "
                    + "numeroTiket = :numeroTiket, "
                    + "aplicaLdf = :aplicaLdf, "
                    + "aplicaMaterna = :aplicaMaterna, "
                    + "aplicaNeonato = :aplicaNeonato, "
                    + "maeCanalComunicacionId = :maeCanalComunicacionId, "
                    + "maeCanalComunicacionCodigo = :maeCanalComunicacionCodigo, "
                    + "maeCanalComunicacionValor = :maeCanalComunicacionValor, "
                    + "estado = :estado, "
                    + "maeEstadoId = :maeEstadoId, "
                    + "maeEstadoCodigo = :maeEstadoCodigo, "
                    + "maeEstadoValor = :maeEstadoValor, "
                    + "maeAcompananteTipoDocumentoId = :maeAcompananteTipoDocumentoId, "
                    + "maeAcompananteTipoDocumentoCodigo = :maeAcompananteTipoDocumentoCodigo, "
                    + "maeAcompananteTipoDocumentoValor = :maeAcompananteTipoDocumentoValor, "
                    + "acompananteDocumento = :acompananteDocumento, "
                    + "acompanantePrimerNombre = :acompanantePrimerNombre, "
                    + "acompananteSegundoNombre = : acompananteSegundoNombre, "
                    + "acompanantePrimerApellido = :acompanantePrimerApellido, "
                    + "acompananteSegundoApellido = :acompananteSegundoApellido, "
                    + "acompananteTelefono = :acompananteTelefono, "
                    + "acompananteDireccion = :acompananteDireccion, "
                    + "acompananteMunicipio = :acompananteMunicipio, "
                    + "acompananteDepartamento  = :acompananteDepartamento, "
                    + "informanteNombre = :informanteNombre, "
                    + "informanteTelefono = :informanteTelefono, "
                    + "informanteCargo = :informanteCargo, "
                    + "diagnosticoEmergente = :diagnosticoEmergente, "
                   // + "consecutivo = :consecutivo, "
                    + "maeCausaExternaId = :maeCausaExternaId, "
                    + "maeCausaExternaCodigo = :maeCausaExternaCodigo, "
                    + "maeCausaExternaValor = :maeCausaExternaValor, "
                    + "prioridad = :prioridad, "
                    + "maeCondicionDestinoId = :maeCondicionDestinoId, "
                    + "maeCondicionDestinoCodigo = :maeCondicionDestinoCodigo, "
                    + "maeCondicionDestinoValor = :maeCondicionDestinoValor, "
                    + "maeTipoAtencionId = :maeTipoAtencionId, "
                    + "maeTipoAtencionCodigo = :maeTipoAtencionCodigo, "
                    + "maeTipoAtencionValor = :maeTipoAtencionValor, "
                    + "maeUbicacionId = :maeUbicacionId, "
                    + "maeUbicacionCodigo = :maeUbicacionCodigo, "
                    + "maeUbicacionValor = :maeUbicacionValor, "
                    + "maeModalidadTecnologiaId = :maeModalidadTecnologiaId, "
                    + "maeModalidadTecnologiaCodigo = :maeModalidadTecnologiaCodigo, "
                    + "maeModalidadTecnologiaValor = :maeModalidadTecnologiaValor, "
                    + "tipoTecnologia = :tipoTecnologia, "
                    + "maTecnologiaId = :maTecnologiaId, "
                    + "maTecnologiaCodigo = :maTecnologiaCodigo, "
                    + "maTecnologiaValor = :maTecnologiaValor, "
                    + "cantidadTecnologiaSolicitada = :cantidadTecnologiaSolicitada, "
                    + "requiereContraste = :requiereContraste, "
                    + "requiereSedacion = :requiereSedacion, "
                    + "examenBag = :examenBag, "
                    + "maeTipoAislamientoId = :maeTipoAislamientoId, "
                    + "maeTipoAislamientoCodigo = :maeTipoAislamientoCodigo, "
                    + "maeTipoAislamientoValor = :maeTipoAislamientoValor, "
                    + "maeTipoAislamientoTipo = :maeTipoAislamientoTipo, "
                    
                    + "maeMaternoPerinatalId = :maeMaternoPerinatalId, "
                    + "maeMaternoPerinatalCodigo = :maeMaternoPerinatalCodigo, "
                    + "maeMaternoPerinatalValor = :maeMaternoPerinatalValor, "
                    + "maeMaternoPerinatalTipo = :maeMaternoPerinatalTipo, "
                    
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, "
                    + "profesionalSolicitaNombre = :profesionalSolicitaNombre, ";
            if (obj.getAsegAfiliado() != null && obj.getAsegAfiliado().getId() != null) {
                sql += "asegAfiliadosId.id = :asegAfiliadosId, ";
            }
            if (obj.getCntPrestadorSede() != null && obj.getCntPrestadorSede().getId() != null) {
                sql += "cntPrestadorSedesId.id = :cntPrestadorSedesId, ";
            }
            if (obj.getCntPrestadorSedesUbicacion() != null && obj.getCntPrestadorSedesUbicacion().getId() != null) {
                sql += "cntPrestadorSedesUbicacionId.id = :cntPrestadorSedesUbicacionId, ";
            }
            if (obj.getCntProfesionales() != null && obj.getCntProfesionales().getId() != null) {
                sql += "cntProfesionalesId.id = :cntProfesionalesId, ";
            }
            sql += "profesionalSolicitaTelefono = :profesionalSolicitaTelefono ";
            sql += "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("numeroSolicitud", obj.getNumeroSolicitud());
            query.setParameter("fechaHoraSolicitud", obj.getFechaHoraSolicitud());
            query.setParameter("fechaHoraRegiostro", obj.getFechaHoraRegistro());
            query.setParameter("aplicaNoIpsContrato", obj.isAplicaNoIpsContrato());
            query.setParameter("aplicaNoAfiliado", obj.isAplicaNoAfiliado());
            query.setParameter("maEspecialidadesId", obj.getMaEspecialidadesId());
            query.setParameter("maEspecialidadCodigo", obj.getMaEspecialidadCodigo());
            query.setParameter("maEspecialidadValor", obj.getMaEspecialidadValor());
            query.setParameter("maServicioSolicitaId", obj.getMaServicioSolicitaId());
            query.setParameter("maServicioSolicitaCodigo", obj.getMaServicioSolicitaCodigo());
            query.setParameter("maServicioSolicitaValor", obj.getMaServicioSolicitaValor());
            query.setParameter("motivo", obj.getMotivo());
            query.setParameter("maServicioRemiteId", obj.getMaServicioRemiteId());
            query.setParameter("maServicioRemiteCodigo", obj.getMaServicioRemiteCodigo());
            query.setParameter("maServicioRemiteValor", obj.getMaServicioRemiteValor());
            query.setParameter("ubicacion", obj.getUbicacion());
            query.setParameter("cama", obj.getCama());
            query.setParameter("numeroTiket", obj.getNumeroTiket());
            query.setParameter("aplicaLdf", obj.getAplicaLdf());
            query.setParameter("aplicaMaterna", obj.getAplicaMaterna());
            query.setParameter("aplicaNeonato", obj.getAplicaNeonato());
            query.setParameter("maeCanalComunicacionId", obj.getMaeCanalComunicacionId());
            query.setParameter("maeCanalComunicacionCodigo", obj.getMaeCanalComunicacionCodigo());
            query.setParameter("maeCanalComunicacionValor", obj.getMaeCanalComunicacionValor());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("maeAcompananteTipoDocumentoId", obj.getMaeAcompananteTipoDocumentoId());
            query.setParameter("maeAcompananteTipoDocumentoCodigo", obj.getMaeAcompananteTipoDocumentoCodigo());
            query.setParameter("maeAcompananteTipoDocumentoValor", obj.getMaeAcompananteTipoDocumentoValor());
            query.setParameter("acompananteDocumento", obj.getAcompananteDocumento());
            query.setParameter("acompanantePrimerNombre", obj.getAcompanantePrimerNombre());
            query.setParameter("acompananteSegundoNombre", obj.getAcompananteSegundoNombre());
            query.setParameter("acompanantePrimerApellido", obj.getAcompanantePrimerApellido());
            query.setParameter("acompananteSegundoApellido", obj.getAcompananteSegundoApellido());
            query.setParameter("acompananteTelefono", obj.getAcompananteTelefono());
            query.setParameter("acompananteDireccion", obj.getAcompananteDireccion());
            query.setParameter("acompananteMunicipio", obj.getAcompananteMunicipio());
            query.setParameter("acompananteDepartamento", obj.getAcompananteDepartamento());
            query.setParameter("informanteNombre", obj.getInformanteNombre());
            query.setParameter("informanteTelefono", obj.getInformanteTelefono());
            query.setParameter("informanteCargo", obj.getInformanteCargo());
            query.setParameter("diagnosticoEmergente", obj.isDiagnosticoEmergente());
            //query.setParameter("consecutivo", obj.getConsecutivo());
            query.setParameter("maeCausaExternaId", obj.getMaeCausaExternaId());
            query.setParameter("maeCausaExternaCodigo", obj.getMaeCausaExternaCodigo());
            query.setParameter("maeCausaExternaValor", obj.getMaeCausaExternaValor());
            query.setParameter("prioridad", obj.getPrioridad());
            query.setParameter("maeCondicionDestinoId", obj.getMaeCondicionDestinoId());
            query.setParameter("maeCondicionDestinoCodigo", obj.getMaeCondicionDestinoCodigo());
            query.setParameter("maeCondicionDestinoValor", obj.getMaeCondicionDestinoValor());
            query.setParameter("maeTipoAtencionId", obj.getMaeTipoAtencionId());
            query.setParameter("maeTipoAtencionCodigo", obj.getMaeTipoAtencionCodigo());
            query.setParameter("maeTipoAtencionValor", obj.getMaeTipoAtencionValor());
            query.setParameter("maeUbicacionId", obj.getMaeUbicacionId());
            query.setParameter("maeUbicacionCodigo", obj.getMaeUbicacionCodigo());
            query.setParameter("maeUbicacionValor", obj.getMaeUbicacionValor());
            query.setParameter("maeModalidadTecnologiaId", obj.getMaeModalidadTecnologiaId());
            query.setParameter("maeModalidadTecnologiaCodigo", obj.getMaeModalidadTecnologiaCodigo());
            query.setParameter("maeModalidadTecnologiaValor", obj.getMaeModalidadTecnologiaValor());
            query.setParameter("tipoTecnologia", obj.getTipoTecnologia());
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            query.setParameter("cantidadTecnologiaSolicitada", obj.getCantidadTecnologiaSolicitada());           
            query.setParameter("requiereContraste", obj.isRequiereContraste());
            query.setParameter("requiereSedacion", obj.isRequiereSedacion());
            query.setParameter("examenBag", obj.isExamenBag());
            query.setParameter("maeTipoAislamientoId", obj.getMaeTipoAislamientoId());
            query.setParameter("maeTipoAislamientoCodigo", obj.getMaeTipoAislamientoCodigo());
            query.setParameter("maeTipoAislamientoValor", obj.getMaeTipoAislamientoValor());
            query.setParameter("maeTipoAislamientoTipo", obj.getMaeTipoAislamientoTipo());
            
            query.setParameter("maeMaternoPerinatalId", obj.getMaeMaternoPerinatalId());
            query.setParameter("maeMaternoPerinatalCodigo", obj.getMaeMaternoPerinatalCodigo());
            query.setParameter("maeMaternoPerinatalValor", obj.getMaeMaternoPerinatalValor());
            query.setParameter("maeMaternoPerinatalTipo", obj.getMaeMaternoPerinatalTipo());
            
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("profesionalSolicitaNombre", obj.getProfesionalSolicitaNombre());
            if (obj.getAsegAfiliado() != null && obj.getAsegAfiliado().getId() != null) {
                query.setParameter("asegAfiliadosId", obj.getAsegAfiliado().getId());
            }
            if (obj.getCntPrestadorSede() != null && obj.getCntPrestadorSede().getId() != null) {
                query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSede().getId());
            }
            if (obj.getCntPrestadorSedesUbicacion() != null && obj.getCntPrestadorSedesUbicacion().getId() != null) {
                query.setParameter("cntPrestadorSedesUbicacionId", obj.getCntPrestadorSedesUbicacion().getId());
            }
            if (obj.getCntProfesionales() != null && obj.getCntProfesionales().getId() != null) {
                query.setParameter("cntProfesionalesId", obj.getCntProfesionales().getId());
            }
            query.setParameter("profesionalSolicitaTelefono", obj.getProfesionalSolicitaTelefono());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstado(RefAnexo9 obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexos9 SET "
                    + "maeEstadoId = :maeEstadoId, "
                    + "maeEstadoCodigo = :maeEstadoCodigo, "
                    + "maeEstadoValor = :maeEstadoValor, "
                    + "diagnosticoEmergente = :diagnosticoEmergente, ";
            if (obj.getCntPrestadorSedesUbicacion() != null && obj.getCntPrestadorSedesUbicacion().getId() != null) {
                sql += "cntPrestadorSedesUbicacionId.id = :cntPrestadorSedesUbicacionId, ";
            }
            sql += "estado = :estado ";
            sql += "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("maeEstadoId", obj.getMaeEstadoId());
            query.setParameter("maeEstadoCodigo", obj.getMaeEstadoCodigo());
            query.setParameter("maeEstadoValor", obj.getMaeEstadoValor());
            query.setParameter("diagnosticoEmergente", obj.isDiagnosticoEmergente());
            if (obj.getCntPrestadorSedesUbicacion() != null && obj.getCntPrestadorSedesUbicacion().getId() != null) {
                query.setParameter("cntPrestadorSedesUbicacionId", obj.getCntPrestadorSedesUbicacion().getId());
            }
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarFechaHoraInicioGestion(RefAnexo9 obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexos9 SET "
                    + "fechaHoraInicioGestion = :fechaHoraInicioGestion "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("fechaHoraInicioGestion", obj.getFechaHoraInicioGestion());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarFechaHoraFinGestion(RefAnexo9 obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexos9 SET "
                    + "fechaHoraFinGestion = :fechaHoraFinGestion, "
                    + "diasGestion = :diasGestion "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("fechaHoraFinGestion", obj.getFechaHoraFinGestion());
            query.setParameter("diasGestion", obj.getDiasGestion());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarUltimaGestion(RefAnexo9 obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexos9 SET "
                    + "fechaHoraUltimaGestion = :fechaHoraUltimaGestion "   
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("fechaHoraUltimaGestion", obj.getFechaHoraUltimaGestion());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarUltimaEvolucion(RefAnexo9 obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexos9 SET "
                    + "fechaHoraAdjuntoEvolucion = :fechaHoraAdjuntoEvolucion "   
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("fechaHoraAdjuntoEvolucion", obj.getFechaHoraAdjuntoEvolucion());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarAuditoriaModifica(RefAnexo9 obj) throws Exception {
        try {
            String sql = "UPDATE RefAnexos9 SET "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public RefAnexo9 eliminar(int id) throws Exception {
        RefAnexo9 obj = null;
        try {
            RefAnexos9 ent = getEntityManager().find(RefAnexos9.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public List<RefAnexo9> anexos9ByAfiliadoByEstados(int idAfiliado, String estados) throws Exception {
        List<RefAnexo9> listResult = new ArrayList<>();
        try {
            String sql = "SELECT r FROM RefAnexos9 r "
                    + "WHERE r.asegAfiliadosId.id =:idAfiliado "
                    + "AND r.maeEstadoCodigo NOT IN(" + estados + ")";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("idAfiliado", idAfiliado);
            List<RefAnexos9> list = query.getResultList();
            for (RefAnexos9 per : list) {
                listResult.add(castEntidadNegocioLista(per, new ParamConsulta()));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static RefAnexo9 castEntidadNegocio(RefAnexos9 ent) {
        RefAnexo9 obj = new RefAnexo9();
        obj.setAcompananteDepartamento(ent.getAcompananteDepartamento());
        obj.setAcompananteDireccion(ent.getAcompananteDireccion());
        obj.setAcompananteDocumento(ent.getAcompananteDocumento());
        obj.setAcompananteMunicipio(ent.getAcompananteMunicipio());
        obj.setAcompanantePrimerApellido(ent.getAcompanantePrimerApellido());
        obj.setAcompanantePrimerNombre(ent.getAcompanantePrimerNombre());
        obj.setAcompananteSegundoApellido(ent.getAcompananteSegundoApellido());
        obj.setAcompananteSegundoNombre(ent.getAcompananteSegundoNombre());
        obj.setAcompananteTelefono(ent.getAcompananteTelefono());
        obj.setAplicaLdf(ent.getAplicaLdf());
        obj.setAplicaMaterna(ent.getAplicaMaterna());
        obj.setAplicaNeonato(ent.getAplicaNeonato());
        obj.setAplicaNoAfiliado(ent.getAplicaNoAfiliado());
        obj.setAplicaNoIpsContrato(ent.getAplicaNoIpsContrato());
        obj.setDiagnosticoEmergente(ent.getDiagnosticoEmergente());
        if (ent.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado(ent.getAsegAfiliadosId().getId());
            afiliado.setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            afiliado.setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            afiliado.setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            afiliado.setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            afiliado.setMaeTipoDocumento(ent.getAsegAfiliadosId().getMaeTipoDocumentoId());
            afiliado.setMaeTipoDocumentoCodigo(ent.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            afiliado.setMaeGeneroValor(ent.getAsegAfiliadosId().getMaeGeneroValor());
            obj.setAsegAfiliado(afiliado);
        }
        obj.setCama(ent.getCama());
        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
            prestadorSede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            prestadorSede.setCodigoHabilitacionSede(ent.getCntPrestadorSedesId().getCodigoHabilitacion());
            CntPrestador prestador = new CntPrestador();
            prestador.setMaeTipoDocumentoId(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
            prestador.setMaeTipoDocumentoValor(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
            prestador.setNumeroDocumento(ent.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            prestador.setRazonSocial(ent.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
            prestador.setCodigoMinSalud(ent.getCntPrestadorSedesId().getCntPrestadoresId().getCodigoMinSalud());
            prestadorSede.setCntPrestador(prestador);
            obj.setCntPrestadorSede(prestadorSede);
        }
        if (ent.getCntPrestadorSedesUbicacionId() != null) {
            obj.setCntPrestadorSedesUbicacion(new CntPrestadorSede(ent.getCntPrestadorSedesUbicacionId().getId(), ent.getCntPrestadorSedesUbicacionId().getNombre(), new CntPrestador(ent.getCntPrestadorSedesUbicacionId().getCntPrestadoresId().getId())));
        }
        if (ent.getCntProfesionalesId() != null) {
            obj.setCntProfesionales(new CntProfesional(ent.getCntProfesionalesId().getId()));
        }
        obj.setAfiliadoDireccionAlternativa(ent.getAfiliadoDireccionAlternativa());
        obj.setNombreContactoEmergencia(ent.getNombreContactoEmergencia());
        obj.setTelefonoContactoEmergencia(ent.getTelefonoContactoEmergencia());
        obj.setPrioridad(ent.getPrioridad());
        
        obj.setEstado(ent.getEstado());
        obj.setMaeEstadoCodigo(ent.getMaeEstadoCodigo());
        obj.setMaeEstadoId(ent.getMaeEstadoId());
        obj.setMaeEstadoValor(ent.getMaeEstadoValor());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaHoraRegistro(ent.getFechaHoraRegiostro());
        obj.setFechaHoraSolicitud(ent.getFechaHoraSolicitud());
        if (ent.getGnEmpresasId() != null) {
            obj.setGnEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }

        obj.setId(ent.getId());
        obj.setInformanteCargo(ent.getInformanteCargo());
        obj.setInformanteNombre(ent.getInformanteNombre());
        obj.setInformanteTelefono(ent.getInformanteTelefono());
        obj.setMaEspecialidadCodigo(ent.getMaEspecialidadCodigo());
        obj.setMaEspecialidadValor(ent.getMaEspecialidadValor());
        obj.setMaEspecialidadesId(ent.getMaEspecialidadesId());
        obj.setMaeAcompananteTipoDocumentoCodigo(ent.getMaeAcompananteTipoDocumentoCodigo());
        obj.setMaeAcompananteTipoDocumentoId(ent.getMaeAcompananteTipoDocumentoId());
        obj.setMaeAcompananteTipoDocumentoValor(ent.getMaeAcompananteTipoDocumentoValor());
        obj.setMaServicioRemiteCodigo(ent.getMaServicioRemiteCodigo());
        obj.setMaServicioRemiteId(ent.getMaServicioRemiteId());
        obj.setMaServicioRemiteValor(ent.getMaServicioRemiteValor());
        obj.setMaServicioSolicitaCodigo(ent.getMaServicioSolicitaCodigo());
        obj.setMaServicioSolicitaId(ent.getMaServicioSolicitaId());
        obj.setMaServicioSolicitaValor(ent.getMaServicioSolicitaValor());
        obj.setMaeCanalComunicacionCodigo(ent.getMaeCanalComunicacionCodigo());
        obj.setMaeCanalComunicacionId(ent.getMaeCanalComunicacionId());
        obj.setMaeCanalComunicacionValor(ent.getMaeCanalComunicacionValor());
        obj.setMotivo(ent.getMotivo());
        obj.setNumeroSolicitud(ent.getNumeroSolicitud());
        obj.setNumeroTiket(ent.getNumeroTiket());
        obj.setProfesionalSolicitaNombre(ent.getProfesionalSolicitaNombre());
        obj.setProfesionalSolicitaTelefono(ent.getProfesionalSolicitaTelefono());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipo(ent.getTipo());
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setUbicacion(ent.getUbicacion());
        
        obj.setVersion((ent.getVersion()) ? 1: 0);
        obj.setConsecutivo(ent.getConsecutivo());
        obj.setMaeCausaExternaId(ent.getMaeCausaExternaId());
        obj.setMaeCausaExternaCodigo(ent.getMaeCausaExternaCodigo());
        obj.setMaeCausaExternaValor(ent.getMaeCausaExternaValor());
        
        obj.setMaeCondicionDestinoId(ent.getMaeCondicionDestinoId());
        obj.setMaeCondicionDestinoCodigo(ent.getMaeCondicionDestinoCodigo());
        obj.setMaeCondicionDestinoValor(ent.getMaeCondicionDestinoValor());
        obj.setMaeTipoAtencionId(ent.getMaeTipoAtencionId());
        obj.setMaeTipoAtencionCodigo(ent.getMaeTipoAtencionCodigo());
        obj.setMaeTipoAtencionValor(ent.getMaeTipoAtencionValor());
            
        obj.setMaeUbicacionId(ent.getMaeUbicacionId());
        obj.setMaeUbicacionCodigo(ent.getMaeUbicacionCodigo());
        obj.setMaeUbicacionValor(ent.getMaeUbicacionValor());
            
        obj.setMaeModalidadTecnologiaId(ent.getMaeModalidadTecnologiaId());
        obj.setMaeModalidadTecnologiaCodigo(ent.getMaeModalidadTecnologiaCodigo());
        obj.setMaeModalidadTecnologiaValor(ent.getMaeModalidadTecnologiaValor());
        
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setCantidadTecnologiaSolicitada(ent.getCantidadTecnologiaSolicitada());
        obj.setFechaHoraInicioGestion(ent.getFechaHoraInicioGestion());
        obj.setFechaHoraFinGestion(ent.getFechaHoraFinGestion());
        
        obj.setRequiereContraste(ent.getRequiereContraste());
        obj.setRequiereSedacion(ent.getRequiereSedacion());
        obj.setExamenBag(ent.getExamenBag());
        obj.setMaeTipoAislamientoId(ent.getMaeTipoAislamientoId());
        obj.setMaeTipoAislamientoCodigo(ent.getMaeTipoAislamientoCodigo());
        obj.setMaeTipoAislamientoValor(ent.getMaeTipoAislamientoValor());
        obj.setMaeTipoAislamientoTipo(ent.getMaeTipoAislamientoTipo());
        
        obj.setMaeMaternoPerinatalId(ent.getMaeMaternoPerinatalId());
        obj.setMaeMaternoPerinatalCodigo(ent.getMaeMaternoPerinatalCodigo());
        obj.setMaeMaternoPerinatalValor(ent.getMaeMaternoPerinatalValor());
        obj.setMaeMaternoPerinatalTipo(ent.getMaeMaternoPerinatalTipo());
        
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }

    public static RefAnexo9 castEntidadNegocioLista(RefAnexos9 ent, ParamConsulta paramConsulta) {
        RefAnexo9 obj = new RefAnexo9();
        obj.setId(ent.getId());
        obj.setNumeroSolicitud(ent.getNumeroSolicitud());
        obj.setNumeroTiket(ent.getNumeroTiket());
        obj.setAplicaLdf(ent.getAplicaLdf());
        obj.setAplicaMaterna(ent.getAplicaMaterna());
        obj.setAplicaNeonato(ent.getAplicaNeonato());
        obj.setAplicaNoAfiliado(ent.getAplicaNoAfiliado());
        obj.setAplicaNoIpsContrato(ent.getAplicaNoIpsContrato());
        obj.setDiagnosticoEmergente(ent.getDiagnosticoEmergente());
        if (ent.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado(ent.getAsegAfiliadosId().getId());
            afiliado.setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            afiliado.setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            afiliado.setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            afiliado.setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            afiliado.setMaeTipoDocumento(ent.getAsegAfiliadosId().getMaeTipoDocumentoId());
            afiliado.setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            afiliado.setMaeGeneroValor(ent.getAsegAfiliadosId().getMaeGeneroValor());
            obj.setAsegAfiliado(afiliado);
        }
        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
            prestadorSede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            CntPrestador prestador = new CntPrestador();
            prestador.setMaeTipoDocumentoId(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
            prestador.setMaeTipoDocumentoValor(ent.getCntPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
            prestador.setNumeroDocumento(ent.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            prestadorSede.setCntPrestador(prestador);
            obj.setCntPrestadorSede(prestadorSede);
        }
        if (ent.getCntPrestadorSedesUbicacionId() != null) {
            obj.setCntPrestadorSedesUbicacion(new CntPrestadorSede(ent.getCntPrestadorSedesUbicacionId().getId(), ent.getCntPrestadorSedesUbicacionId().getNombre(), new CntPrestador(ent.getCntPrestadorSedesUbicacionId().getCntPrestadoresId().getId())));
        }

        obj.setEstado(ent.getEstado());
        obj.setMaeEstadoCodigo(ent.getMaeEstadoCodigo());
        obj.setMaeEstadoId(ent.getMaeEstadoId());
        obj.setMaeEstadoValor(ent.getMaeEstadoValor());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setFechaHoraRegistro(ent.getFechaHoraRegiostro());
        obj.setFechaHoraSolicitud(ent.getFechaHoraSolicitud());
        if (ent.getGnEmpresasId() != null) {
            obj.setGnEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }

        obj.setMaEspecialidadCodigo(ent.getMaEspecialidadCodigo());
        obj.setMaEspecialidadValor(ent.getMaEspecialidadValor());
        obj.setMaEspecialidadesId(ent.getMaEspecialidadesId());

        obj.setMaServicioRemiteCodigo(ent.getMaServicioRemiteCodigo());
        obj.setMaServicioRemiteId(ent.getMaServicioRemiteId());
        obj.setMaServicioRemiteValor(ent.getMaServicioRemiteValor());
        obj.setMaServicioSolicitaCodigo(ent.getMaServicioSolicitaCodigo());
        obj.setMaServicioSolicitaId(ent.getMaServicioSolicitaId());
        obj.setMaServicioSolicitaValor(ent.getMaServicioSolicitaValor());

        obj.setMotivo(ent.getMotivo());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setTipo(ent.getTipo());
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setUbicacion(ent.getUbicacion());
        obj.setVersion(ent.getVersion() ? 1 : 0);
        obj.setDiasGestion(ent.getDiasGestion());
        obj.setFechaHoraInicioGestion(ent.getFechaHoraInicioGestion());
        obj.setFechaHoraFinGestion(ent.getFechaHoraFinGestion());
        obj.setFechaHoraUltimaGestion(ent.getFechaHoraUltimaGestion());
        obj.setFechaHoraAdjuntoEvolucion(ent.getFechaHoraAdjuntoEvolucion());
        obj.setMaeMaternoPerinatalId(ent.getMaeMaternoPerinatalId());
        obj.setMaeMaternoPerinatalCodigo(ent.getMaeMaternoPerinatalCodigo());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        if (paramConsulta.getFiltros() != null) {
            for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                switch ((String) e.getKey()) {
                    case "refAnexo9Gestion.maeMotivoValor":
                        String dato_busqueda = (String) e.getValue();
                        RefAnexo9Gestion gestion = new RefAnexo9Gestion();
                        gestion.setMaeMotivoValor(dato_busqueda);
                        obj.setRefAnexo9Gestion(gestion);
                        break;
                }
            }
        }
        if (obj.getRefAnexo9Gestion() == null
            // && (ent.getMaeEstadoId().intValue() == RefAnexo9Estado.ESTADO_ANULADA || ent.getMaeEstadoId().intValue() == RefAnexo9Estado.ESTADO_CANCELADA)
            && !ent.getRefAnexo9GestionesList().isEmpty()) {
            RefAnexo9Gestion gestion = new RefAnexo9Gestion(ent.getRefAnexo9GestionesList().get(0).getId());
            gestion.setMaeMotivoCodigo(ent.getRefAnexo9GestionesList().get(ent.getRefAnexo9GestionesList().size() - 1).getMaeMotivoCodigo());
            gestion.setMaeMotivoValor(ent.getRefAnexo9GestionesList().get(ent.getRefAnexo9GestionesList().size() - 1).getMaeMotivoValor());
            gestion.setMaeTipoId(ent.getRefAnexo9GestionesList().get(ent.getRefAnexo9GestionesList().size() - 1).getMaeTipoId());
            obj.setRefAnexo9Gestion(gestion);
        }
        if (ent.getRefAnexo9DatosClinicosList() != null && !ent.getRefAnexo9DatosClinicosList().isEmpty()) {
            RefAnexo9DatoClinico datoClinico = new RefAnexo9DatoClinico(ent.getRefAnexo9DatosClinicosList().get(ent.getRefAnexo9DatosClinicosList().size() - 1).getId());
            datoClinico.setTriage(ent.getRefAnexo9DatosClinicosList().get(ent.getRefAnexo9DatosClinicosList().size() - 1).getTriage());
            obj.setRefAnexo9DatoClinico(datoClinico);
        }

        return obj;
    }

    public static RefAnexos9 castNegocioEntidad(RefAnexo9 obj) {
        RefAnexos9 ent = new RefAnexos9();
        ent.setAcompananteDepartamento(obj.getAcompananteDepartamento());
        ent.setAcompananteDireccion(obj.getAcompananteDireccion());
        ent.setAcompananteDocumento(obj.getAcompananteDocumento());
        ent.setAcompananteMunicipio(obj.getAcompananteMunicipio());
        ent.setAcompanantePrimerApellido(obj.getAcompanantePrimerApellido());
        ent.setAcompanantePrimerNombre(obj.getAcompanantePrimerNombre());
        ent.setAcompananteSegundoApellido(obj.getAcompananteSegundoApellido());
        ent.setAcompananteSegundoNombre(obj.getAcompananteSegundoNombre());
        ent.setAcompananteTelefono(obj.getAcompananteTelefono());
        ent.setAplicaLdf(obj.getAplicaLdf());
        ent.setAplicaMaterna(obj.getAplicaMaterna());
        ent.setAplicaNeonato(obj.getAplicaNeonato());
        ent.setAplicaNoAfiliado(obj.isAplicaNoAfiliado());
        ent.setAplicaNoIpsContrato(obj.isAplicaNoIpsContrato());
        ent.setDiagnosticoEmergente(obj.isDiagnosticoEmergente());
        if (obj.getAsegAfiliado() != null && obj.getAsegAfiliado().getId() != null) {
            ent.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));

        }
        ent.setAfiliadoDireccionAlternativa(obj.getAfiliadoDireccionAlternativa());
        ent.setNombreContactoEmergencia(obj.getNombreContactoEmergencia());
        ent.setTelefonoContactoEmergencia(obj.getTelefonoContactoEmergencia());
        ent.setPrioridad(obj.getPrioridad());
        ent.setCama(obj.getCama());
        if (obj.getCntPrestadorSede() != null && obj.getCntPrestadorSede().getId() != null) {
            ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        if (obj.getCntPrestadorSedesUbicacion() != null && obj.getCntPrestadorSedesUbicacion().getId() != null) {
            ent.setCntPrestadorSedesUbicacionId(new CntPrestadorSedes(obj.getCntPrestadorSedesUbicacion().getId()));
        }
        if (obj.getCntProfesionales() != null && obj.getCntProfesionales().getId() != null) {
            ent.setCntProfesionalesId(new CntProfesionales(obj.getCntProfesionales().getId()));
        }
        ent.setEstado(obj.getEstado());
        ent.setMaeEstadoCodigo(obj.getMaeEstadoCodigo());
        ent.setMaeEstadoId(obj.getMaeEstadoId());
        ent.setMaeEstadoValor(obj.getMaeEstadoValor());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setFechaHoraRegiostro(obj.getFechaHoraRegistro());
        ent.setFechaHoraSolicitud(obj.getFechaHoraSolicitud());
        if (obj.getGnEmpresa() != null && obj.getGnEmpresa().getId() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getGnEmpresa().getId()));
        }
        ent.setId(obj.getId());
        ent.setInformanteCargo(obj.getInformanteCargo());
        ent.setInformanteNombre(obj.getInformanteNombre());
        ent.setInformanteTelefono(obj.getInformanteTelefono());
        ent.setMaEspecialidadCodigo(obj.getMaEspecialidadCodigo());
        ent.setMaEspecialidadValor(obj.getMaEspecialidadValor());
        ent.setMaEspecialidadesId(obj.getMaEspecialidadesId());
        ent.setMaeAcompananteTipoDocumentoCodigo(obj.getMaeAcompananteTipoDocumentoCodigo());
        ent.setMaeAcompananteTipoDocumentoId(obj.getMaeAcompananteTipoDocumentoId());
        ent.setMaeAcompananteTipoDocumentoValor(obj.getMaeAcompananteTipoDocumentoValor());
        ent.setMaServicioRemiteCodigo(obj.getMaServicioRemiteCodigo());
        ent.setMaServicioRemiteId(obj.getMaServicioRemiteId());
        ent.setMaServicioRemiteValor(obj.getMaServicioRemiteValor());
        ent.setMaServicioSolicitaCodigo(obj.getMaServicioSolicitaCodigo());
        ent.setMaServicioSolicitaId(obj.getMaServicioSolicitaId());
        ent.setMaServicioSolicitaValor(obj.getMaServicioSolicitaValor());
        ent.setMaeCanalComunicacionCodigo(obj.getMaeCanalComunicacionCodigo());
        ent.setMaeCanalComunicacionId(obj.getMaeCanalComunicacionId());
        ent.setMaeCanalComunicacionValor(obj.getMaeCanalComunicacionValor());
        ent.setMotivo(obj.getMotivo());
        ent.setNumeroSolicitud(obj.getNumeroSolicitud());
        ent.setNumeroTiket(obj.getNumeroTiket());
        ent.setProfesionalSolicitaNombre(obj.getProfesionalSolicitaNombre());
        ent.setProfesionalSolicitaTelefono(obj.getProfesionalSolicitaTelefono());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setTipo(obj.getTipo());
        ent.setFuenteOrigen(obj.getFuenteOrigen());
        ent.setUbicacion(obj.getUbicacion());
        ent.setVersion((obj.getVersion().equals(1)));
        //ent.setConsecutivo(obj.getConsecutivo());
        ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
        ent.setMaeCausaExternaCodigo(obj.getMaeCausaExternaCodigo());
        ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
        
        ent.setMaeCondicionDestinoId(obj.getMaeCondicionDestinoId());
        ent.setMaeCondicionDestinoCodigo(obj.getMaeCondicionDestinoCodigo());
        ent.setMaeCondicionDestinoValor(obj.getMaeCondicionDestinoValor());
        ent.setMaeTipoAtencionId(obj.getMaeTipoAtencionId());
        ent.setMaeTipoAtencionCodigo(obj.getMaeTipoAtencionCodigo());
        ent.setMaeTipoAtencionValor(obj.getMaeTipoAtencionValor());
            
        ent.setMaeUbicacionId(obj.getMaeUbicacionId());
        ent.setMaeUbicacionCodigo(obj.getMaeUbicacionCodigo());
        ent.setMaeUbicacionValor(obj.getMaeUbicacionValor());
            
        ent.setMaeModalidadTecnologiaId(obj.getMaeModalidadTecnologiaId());
        ent.setMaeModalidadTecnologiaCodigo(obj.getMaeModalidadTecnologiaCodigo());
        ent.setMaeModalidadTecnologiaValor(obj.getMaeModalidadTecnologiaValor());
        
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        ent.setCantidadTecnologiaSolicitada(obj.getCantidadTecnologiaSolicitada());
        
        ent.setRequiereContraste(obj.isRequiereContraste());
        ent.setRequiereSedacion(obj.isRequiereSedacion());
        ent.setExamenBag(obj.isExamenBag());
        ent.setMaeTipoAislamientoId(obj.getMaeTipoAislamientoId());
        ent.setMaeTipoAislamientoCodigo(obj.getMaeTipoAislamientoCodigo());
        ent.setMaeTipoAislamientoValor(obj.getMaeTipoAislamientoValor());
        ent.setMaeTipoAislamientoTipo(obj.getMaeTipoAislamientoTipo());
        
        ent.setMaeMaternoPerinatalId(obj.getMaeMaternoPerinatalId());
        ent.setMaeMaternoPerinatalCodigo(obj.getMaeMaternoPerinatalCodigo());
        ent.setMaeMaternoPerinatalValor(obj.getMaeMaternoPerinatalValor());
        ent.setMaeMaternoPerinatalTipo(obj.getMaeMaternoPerinatalTipo());
        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        return ent;
    }

    @Override
    public int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(r) FROM RefAnexos9 r ";

            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE r.id > 0 ";
            } else {
                strQuery += "WHERE r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND r.asegAfiliadosId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND r.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND r.id = " + e.getValue() + " ";
                            break;
                        case "maeEstadoId":
                            strQuery += "AND r.maeEstadoId = " + e.getValue() + " ";
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
    public List<RefAnexo9> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception {
        List<RefAnexo9> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT r FROM RefAnexos9 r ";

            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE r.id > 0 ";
            } else {
                strQuery += "WHERE r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND r.asegAfiliadosId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND r.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND r.id = " + e.getValue() + " ";
                            break;
                        case "maeEstadoId":
                            strQuery += "AND r.maeEstadoId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "r." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "r.id DESC";
            }
            List<RefAnexos9> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RefAnexos9 per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
}
