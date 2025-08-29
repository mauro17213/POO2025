/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Rescates;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2Remoto;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos2;
import com.saviasaludeps.savia.ejb.servicios.aseguramiento.AfiliadoServicio;
import com.saviasaludeps.savia.ejb.servicios.autorizacion.AuSolicitudAdjuntoServicio;
import com.saviasaludeps.savia.ejb.servicios.contratacion.CntPrestadorServicio;
import com.saviasaludeps.savia.ejb.servicios.contratacion.CntProfesionalServicio;
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
 * @author AlexanderDiaz
 */
@Stateless
@Remote(AuAnexo2Remoto.class)
@Local(AuAnexo2Local.class)
public class AuAnexo2Servicio extends GenericoServicio implements AuAnexo2Remoto, AuAnexo2Local {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(DISTINCT r) FROM AuAnexos2 r ";
            strQuery += "JOIN r.auAnexo2DiagnosticosList aad ";
            /*if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "obtenerDiagnosticoPrincipal(obj.listaAuAnexo2Diagnostico)":
                            strQuery += "INNER JOIN r.auAnexo2DiagnosticosList d ";
                            break;
                    }
                }
            }*/
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE r.id > 0 ";
            } else {
                strQuery += "WHERE r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
//                strQuery += "WHERE (r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " "
//                        + "OR r.gnEmpresasUbicacionId.id = " + paramConsulta.getEmpresaId() + ") ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "asegAfiliado.nombreCompleto":
                            strQuery += " AND (r.asegAfiliadosId.primerNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.segundoNombre LIKE '%" + (String) e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR r.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' "
                                    + "OR CONCAT(r.asegAfiliadosId.primerNombre, ' ', COALESCE(r.asegAfiliadosId.segundoNombre,''), CASE WHEN COALESCE(r.asegAfiliadosId.segundoNombre,'') <> '' THEN ' ' ELSE '' END, r.asegAfiliadosId.primerApellido, ' ', COALESCE(r.asegAfiliadosId.segundoApellido,'')) LIKE '%" + e.getValue() + "%') ";
                            break;
                        case "cntPrestadorSede.nombreSede":
                            strQuery += " AND r.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "fechaHoraAtencion":
                            strQuery += " AND r.fechaHoraAtencion BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59'";
                            break;
                        case "asegAfiliado.numeroDocumento":
                            strQuery += "AND r.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                            break;
                        case "id":
                            strQuery += " AND r.id = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeDestinoPacienteId":
                            strQuery += " AND r.maeDestinoPacienteId = '" + (String) e.getValue() + "' ";
                            break;
                        case "triage":
                            strQuery += " AND r.triage = '" + (String) e.getValue() + "' ";
                            break;
                        case "estado":
                            strQuery += " AND r.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "fuenteOrigen":
                            strQuery += " AND r.fuenteOrigen = '" + (String) e.getValue() + "' ";
                            break;
                        case "asegAfiliado.maeTipoDocumento":
                            strQuery += " AND r.asegAfiliadosId.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "listaAuAnexo2Diagnostico":
                            /*String strQueryAux = "SELECT COUNT(aad.maDiagnosticosValor) FROM AuAnexo2Diagnosticos aad, AuAnexos2 r WHERE aad.principal = true and aad.auAnexos2Id = r.id "
                                    + "AND aad.maDiagnosticosValor LIKE '%" + e.getValue() + "%'";
                            cant = (int) (long) getEntityManager().createQuery(strQueryAux).getSingleResult();
                            strQuery += "AND " + cant + " > 0  ";*/
                            /*strQuery += "AND (SELECT COUNT(aad.id) FROM AuAnexo2Diagnosticos aad"
                                    + " WHERE aad.principal = true AND aad.auAnexos2Id.id = r.id AND aad.ma_diagnosticos_valor LIKE '%" 
                                    + (String) e.getValue() + "%') > 0";*/
                            strQuery += " AND aad.principal = true ";
                            strQuery += " AND aad.maDiagnosticosValor LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery).getSingleResult();
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
    public List<AuAnexo2> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuAnexo2> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT DISTINCT r FROM AuAnexos2 r ";
            strQuery += "JOIN r.auAnexo2DiagnosticosList aad ";
            /*if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "obtenerDiagnosticoPrincipal(obj.listaAuAnexo2Diagnostico)":
                            strQuery += "INNER JOIN r.auAnexo2DiagnosticosList d ";
                            break;
                    }
                }
            }*/
            if (paramConsulta.getEmpresaId() == null) {
                strQuery += "WHERE r.id > 0 ";
            } else {
                strQuery += "WHERE r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
//                strQuery += "WHERE (r.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " "
//                        + "OR r.gnEmpresasUbicacionId.id = " + paramConsulta.getEmpresaId() + ") ";
            }
            for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                switch ((String) e.getKey()) {
                    case "asegAfiliado.nombreCompleto":
                        strQuery += " AND (r.asegAfiliadosId.primerNombre LIKE '%" + (String) e.getValue() + "%' "
                                + "OR r.asegAfiliadosId.segundoNombre LIKE '%" + (String) e.getValue() + "%' "
                                + "OR r.asegAfiliadosId.primerApellido LIKE '%" + e.getValue() + "%' "
                                + "OR r.asegAfiliadosId.segundoApellido LIKE '%" + e.getValue() + "%' "
                                + "OR CONCAT(r.asegAfiliadosId.primerNombre, ' ', COALESCE(r.asegAfiliadosId.segundoNombre,''), CASE WHEN COALESCE(r.asegAfiliadosId.segundoNombre,'') <> '' THEN ' ' ELSE '' END, r.asegAfiliadosId.primerApellido, ' ', COALESCE(r.asegAfiliadosId.segundoApellido,'')) LIKE '%" + e.getValue() + "%') ";
                        break;
                    case "cntPrestadorSede.nombreSede":
                        strQuery += " AND r.cntPrestadorSedesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                        break;
                    case "fechaHoraAtencion":
                        strQuery += " AND r.fechaHoraAtencion BETWEEN '" + (String) e.getValue().toString() + " 00:00:00' AND '" + (String) e.getValue().toString() + " 23:59:59'";
                        break;
                    case "asegAfiliado.numeroDocumento":
                        strQuery += "AND r.asegAfiliadosId.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '" + (String) e.getValue() + "' ) ";
                        break;
                    case "id":
                        strQuery += " AND r.id = '" + (String) e.getValue() + "' ";
                        break;
                    case "maeDestinoPacienteId":
                        strQuery += " AND r.maeDestinoPacienteId = '" + (String) e.getValue() + "' ";
                        break;
                    case "triage":
                        strQuery += " AND r.triage = '" + (String) e.getValue() + "' ";
                        break;
                    case "estado":
                        strQuery += " AND r.estado = '" + (String) e.getValue() + "' ";
                        break;
                    case "fuenteOrigen":
                        strQuery += " AND r.fuenteOrigen = '" + (String) e.getValue() + "' ";
                        break;
                    case "asegAfiliado.maeTipoDocumento":
                        strQuery += " AND r.asegAfiliadosId.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                        break;
                    case "listaAuAnexo2Diagnostico":
                        /*strQuery += "AND ((SELECT count(aad.maDiagnosticosValor) FROM AuAnexo2Diagnosticos aad WHERE aad.principal = true AND aad.auAnexos2Id.id = r.id "
                                    + " LIKE '%" + (String) e.getValue() + "%') > 0 ) ";*/
                        strQuery += " AND aad.principal = true ";
                        strQuery += " AND aad.maDiagnosticosValor LIKE '%" + e.getValue() + "%' ";
                        break;
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "asegAfiliado.numeroDocumento":
                        strQuery += "r.asegAfiliadosId.numeroDocumento "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "asegAfiliado.nombreCompleto":
                        strQuery += "r.asegAfiliadosId.primerNombre "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "cntPrestadorSede.nombreSede":
                        strQuery += "r.cntPrestadorSedesId.nombre "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "listaAuAnexo2Diagnostico":
                        strQuery += "aad.maDiagnosticosValor "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "asegAfiliado.maeTipoDocumento":
                        strQuery += "r.asegAfiliadosId.maeTipoDocumentoId "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "r." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }

            } else {
                strQuery += "r.id DESC";
            }
            List<AuAnexos2> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuAnexos2 per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    public AuAnexo2 consultar(int id) throws Exception {
        AuAnexo2 objResult = new AuAnexo2();
        try {
            objResult = castEntidadNegocioLargo((AuAnexos2) getEntityManager().find(AuAnexos2.class, id));
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
    public int insertar(AuAnexo2 obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizarEstado(AuAnexo2 obj) throws Exception {
        try {
            String sql = "UPDATE AuAnexos2 SET "
                    + "comentarioEstado = :comentarioEstado, "
                    + "estado = :estado, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", obj.getId());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("comentarioEstado", obj.getComentarioEstado());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarConsecutivo(String consecutivo, Integer id) throws Exception {
        try {
            String sql = "UPDATE AuAnexos2 SET "
                    + "consecutivo = :con "
                    + "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("con", consecutivo);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizar(AuAnexo2 obj) throws Exception {
        try {
            String sql = "UPDATE AuAnexos2 SET  "
                    + "maeOrigenAtencionId = :maeOrigenAtencionId, "
                    + "maeOrigenAtencionCodigo = :maeOrigenAtencionCodigo, "
                    + "maeOrigenAtencionValor = :maeOrigenAtencionValor, "
                    + "maeDestinoPacienteId = :maeDestinoPacienteId, "
                    + "maeDestinoPacienteCodigo = :maeDestinoPacienteCodigo, "
                    + "maeDestinoPacienteValor = :maeDestinoPacienteValor, "
                    + "codigoAtencionIps = :codigoAtencionIps, "
                    + "fechaHoraAtencion = :fechaHoraAtencion, "
                    + "fechaHoraReporte = :fechaHoraReporte, "
                    + "motivo = :motivo, "
                    + "remiteNit = :remiteNit, "
                    + "remiteNombre = :remiteNombre, "
                    + "triage = :triage, "
                    + "informaNombre = :informaNombre, "
                    + "informaCargo = :informaCargo, "
                    + "informaTelefono = :informaTelefono, "
                    //+ "consecutivo = :consecutivo, "
                    + "maeViaIngresoId = :maeViaIngresoId, "
                    + "maeViaIngresoCodigo = :maeViaIngresoCodigo, "
                    + "maeViaIngresoValor = :maeViaIngresoValor, "
                    + "maeCondicionDestinoId = :maeCondicionDestinoId, "
                    + "maeCondicionDestinoCodigo = :maeCondicionDestinoCodigo, "
                    + "maeCondicionDestinoValor = :maeCondicionDestinoValor, "
                    + "afiliadoDireccionAlternativa = :afiliadoDireccionAlternativa, "
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica, ";
            if (obj.getAsegAfiliado() != null && obj.getAsegAfiliado().getId() != null) {
                sql += "asegAfiliadosId.id = :asegAfiliadosId, ";
            }
            if (obj.getCntProfesionales() != null && obj.getCntProfesionales().getId() != null) {
                sql += "cntProfesionalesId.id = :cntProfesionalesId, ";
            }
            if (obj.getCntPrestadorSede() != null && obj.getCntPrestadorSede().getId() != null) {
                sql += "cntPrestadorSedesId.id = :cntPrestadorSedesId ";
            }
            
            sql += "WHERE id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", obj.getId());
            query.setParameter("maeOrigenAtencionId", obj.getMaeOrigenAtencionId());
            query.setParameter("maeOrigenAtencionCodigo", obj.getMaeOrigenAtencionCodigo());
            query.setParameter("maeOrigenAtencionValor", obj.getMaeOrigenAtencionValor());
            query.setParameter("maeDestinoPacienteId", obj.getMaeDestinoPacienteId());
            query.setParameter("maeDestinoPacienteCodigo", obj.getMaeDestinoPacienteCodigo());
            query.setParameter("maeDestinoPacienteValor", obj.getMaeDestinoPacienteValor());
            query.setParameter("codigoAtencionIps", obj.getCodigoAtencionIps());
            query.setParameter("fechaHoraAtencion", obj.getFechaHoraAtencion());
            query.setParameter("fechaHoraReporte", obj.getFechaHoraReporte());
            query.setParameter("motivo", obj.getMotivo());
            query.setParameter("remiteNit", obj.getRemiteNit());
            query.setParameter("remiteNombre", obj.getRemiteNombre());
            query.setParameter("triage", obj.getTriage());
            query.setParameter("informaNombre", obj.getInformaNombre());
            query.setParameter("informaCargo", obj.getInformaCargo());
            query.setParameter("informaTelefono", obj.getInformaTelefono());
            //query.setParameter("consecutivo", obj.getConsecutivo());
            query.setParameter("maeViaIngresoId", obj.getMaeViaIngresoId());
            query.setParameter("maeViaIngresoCodigo", obj.getMaeViaIngresoCodigo());
            query.setParameter("maeViaIngresoValor", obj.getMaeViaIngresoValor());
            query.setParameter("maeCondicionDestinoId", obj.getMaeCondicionDestinoId());
            query.setParameter("maeCondicionDestinoCodigo", obj.getMaeCondicionDestinoCodigo());
            query.setParameter("maeCondicionDestinoValor", obj.getMaeCondicionDestinoValor());
            query.setParameter("afiliadoDireccionAlternativa", obj.getDireccionAlternativa());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            if (obj.getAsegAfiliado() != null && obj.getAsegAfiliado().getId() != null) {
                query.setParameter("asegAfiliadosId", obj.getAsegAfiliado().getId());
            }
            if (obj.getCntPrestadorSede() != null && obj.getCntPrestadorSede().getId() != null) {
                query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSede().getId());
            }
            if (obj.getCntProfesionales() != null && obj.getCntProfesionales().getId() != null) {
                query.setParameter("cntProfesionalesId", obj.getCntProfesionales().getId());
            }
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AuAnexo2 eliminar(int id) throws Exception {
        AuAnexo2 obj = null;
        try {
            AuAnexos2 ent = getEntityManager().find(AuAnexos2.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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

    public static AuAnexo2 castEntidadNegocioCorto(AuAnexos2 ent) {
        AuAnexo2 obj = new AuAnexo2();
        obj.setId(ent.getId());
        obj.setMaeOrigenAtencionId(ent.getMaeOrigenAtencionId());
        obj.setMaeOrigenAtencionCodigo(ent.getMaeOrigenAtencionCodigo());
        obj.setMaeOrigenAtencionValor(ent.getMaeOrigenAtencionValor());
        obj.setMaeDestinoPacienteId(ent.getMaeDestinoPacienteId());
        obj.setMaeDestinoPacienteCodigo(ent.getMaeDestinoPacienteCodigo());
        obj.setMaeDestinoPacienteValor(ent.getMaeDestinoPacienteValor());
        obj.setCodigoAtencionIps(ent.getCodigoAtencionIps());
        obj.setFechaHoraAtencion(ent.getFechaHoraAtencion());
        obj.setFechaHoraReporte(ent.getFechaHoraReporte());
        obj.setTriage(ent.getTriage());
        obj.setComentarioEstado(ent.getComentarioEstado());
        obj.setEstado(ent.getEstado());
        obj.setFuenteOrigen(ent.getFuenteOrigen());

        if (ent.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado(
                    ent.getAsegAfiliadosId().getId(),
                    ent.getAsegAfiliadosId().getIdAfiliado(),
                    ent.getAsegAfiliadosId().getPrimerNombre(),
                    ent.getAsegAfiliadosId().getSegundoNombre(),
                    ent.getAsegAfiliadosId().getPrimerApellido(),
                    ent.getAsegAfiliadosId().getSegundoApellido(),
                    ent.getAsegAfiliadosId().getGenero(),
                    ent.getAsegAfiliadosId().getMaeTipoDocumentoId(),
                    ent.getAsegAfiliadosId().getNumeroDocumento(),
                    ent.getAsegAfiliadosId().getMaeEstadoAfiliacionId()
            );
            afiliado.setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            obj.setAsegAfiliado(afiliado);
        }

        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
            prestadorSede.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            obj.setCntPrestadorSede(prestadorSede);
        }

        List<AuAnexo2Diagnostico> listaDiagnostico = new ArrayList<>();
        ent.getAuAnexo2DiagnosticosList().forEach(diagnosticos -> {
            listaDiagnostico.add(new AuAnexo2Diagnostico(diagnosticos.getId(), diagnosticos.getMaDiagnosticosValor(), diagnosticos.getPrincipal()));
        });
        obj.setListaAuAnexo2Diagnostico(listaDiagnostico);

        List<AuAnexo2Rescate> listaRescate = new ArrayList<>();
        if (ent.getAuAnexo2RescatesList() != null) {
            for (AuAnexo2Rescates rescate : ent.getAuAnexo2RescatesList()) {
                listaRescate.add(new AuAnexo2Rescate(rescate.getId()));
                break;
            }

        }
        obj.setListaAuAnexo2Rescate(listaRescate);

        if (ent.getGnEmpresasId() != null) {
            obj.setGnEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }
        obj.setVersion(ent.getVersion() ? 1 : 0);
        //auditoria
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuAnexo2 castEntidadNegocioLargo(AuAnexos2 ent) {
        AuAnexo2 obj = new AuAnexo2();
        obj.setId(ent.getId());
        obj.setMaeOrigenAtencionId(ent.getMaeOrigenAtencionId());
        obj.setMaeOrigenAtencionCodigo(ent.getMaeOrigenAtencionCodigo());
        obj.setMaeOrigenAtencionValor(ent.getMaeOrigenAtencionValor());
        obj.setMaeDestinoPacienteId(ent.getMaeDestinoPacienteId());
        obj.setMaeDestinoPacienteCodigo(ent.getMaeDestinoPacienteCodigo());
        obj.setMaeDestinoPacienteValor(ent.getMaeDestinoPacienteValor());
        obj.setCodigoAtencionIps(ent.getCodigoAtencionIps());
        obj.setFechaHoraAtencion(ent.getFechaHoraAtencion());
        obj.setFechaHoraReporte(ent.getFechaHoraReporte());
        obj.setMotivo(ent.getMotivo());
        obj.setRemiteNit(ent.getRemiteNit());
        obj.setRemiteNombre(ent.getRemiteNombre());
        obj.setTriage(ent.getTriage());
        obj.setInformaNombre(ent.getInformaNombre());
        obj.setInformaCargo(ent.getInformaCargo());
        obj.setInformaTelefono(ent.getInformaTelefono());
        obj.setTipo(ent.getTipo());
        obj.setRemitido(ent.getRemitido());
        obj.setComentarioEstado(ent.getComentarioEstado());
        obj.setEstado(ent.getEstado());
        obj.setFuenteOrigen(ent.getFuenteOrigen());

        if (ent.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(AfiliadoServicio.castEntidadNegocioLargo(ent.getAsegAfiliadosId()));
        }

        if (ent.getCntPrestadorSedesId() != null) {
            obj.setCntPrestadorSede(
                    CntPrestadorServicio.castPrestadorSedesEntidadNegocio(ent.getCntPrestadorSedesId())
            );
        }

        if (ent.getCntProfesionalesId() != null) {
            obj.setCntProfesionales(CntProfesionalServicio.castEntidadNegocio(ent.getCntProfesionalesId()));
        }

        List<AuAnexo2Diagnostico> listaDiagnostico = new ArrayList<>();
        ent.getAuAnexo2DiagnosticosList().forEach(diagnosticos -> {
            listaDiagnostico.add(AuAnexo2DiagnosticoServicio.castEntidadNegocio(diagnosticos));
        });
        obj.setListaAuAnexo2Diagnostico(listaDiagnostico);

        if (ent.getAuSolicitudAdjuntosList() != null) {
            obj.setListaAuSolicitudAdjunto(AuSolicitudAdjuntoServicio.casteoListaEntidadNegocio(ent.getAuSolicitudAdjuntosList()));
        }

        List<AuAnexo2Item> listaItem = new ArrayList<>();
        ent.getAuAnexo2ItemsList().forEach(item -> {
            listaItem.add(AuAnexo2ItemServicio.castEntidadNegocio(item));
        });
        obj.setListaAuAnexo2Item(listaItem);

        if (ent.getGnEmpresasId() != null) {
            obj.setGnEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        }
        obj.setVersion(ent.getVersion() ? 1 : 0);
        obj.setConsecutivo(ent.getConsecutivo());
        obj.setMaeViaIngresoId(ent.getMaeViaIngresoId());
        obj.setMaeViaIngresoCodigo(ent.getMaeViaIngresoCodigo());
        obj.setMaeViaIngresoValor(ent.getMaeViaIngresoValor());
        obj.setMaeCondicionDestinoId(ent.getMaeCondicionDestinoId());
        obj.setMaeCondicionDestinoCodigo(ent.getMaeCondicionDestinoCodigo());
        obj.setMaeCondicionDestinoValor(ent.getMaeCondicionDestinoValor());
        obj.setDireccionAlternativa(ent.getAfiliadoDireccionAlternativa());
        //obj.setListaAuAnexo2Estado(listaAuAnexo2Estado);
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

    public static AuAnexos2 castNegocioEntidad(AuAnexo2 obj) {
        AuAnexos2 ent = new AuAnexos2();
        //ent.setId(obj.getId());
        ent.setMaeOrigenAtencionId(obj.getMaeOrigenAtencionId());
        ent.setMaeOrigenAtencionCodigo(obj.getMaeOrigenAtencionCodigo());
        ent.setMaeOrigenAtencionValor(obj.getMaeOrigenAtencionValor());
        if(obj.getMaeDestinoPacienteId() != null){
            ent.setMaeDestinoPacienteId(obj.getMaeDestinoPacienteId());
            ent.setMaeDestinoPacienteCodigo(obj.getMaeDestinoPacienteCodigo());
            ent.setMaeDestinoPacienteValor(obj.getMaeDestinoPacienteValor());
        }
        
        ent.setCodigoAtencionIps(obj.getCodigoAtencionIps());
        ent.setFechaHoraAtencion(obj.getFechaHoraAtencion());
        ent.setFechaHoraReporte(obj.getFechaHoraReporte());
        ent.setMotivo(obj.getMotivo());
        ent.setRemiteNit(obj.getRemiteNit());
        ent.setRemiteNombre(obj.getRemiteNombre());
        ent.setTriage(obj.getTriage());
        ent.setInformaNombre(obj.getInformaNombre());
        ent.setInformaCargo(obj.getInformaCargo());
        ent.setInformaTelefono(obj.getInformaTelefono());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        ent.setTipo(obj.getTipo());
        ent.setRemitido(obj.isRemitido());
        if (obj.getAsegAfiliado() != null) {
            ent.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        if (obj.getCntPrestadorSede() != null) {
            ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        if (obj.getCntProfesionales() != null && obj.getCntProfesionales().getId() != null) {
            ent.setCntProfesionalesId(new CntProfesionales(obj.getCntProfesionales().getId()));
        }

        ent.setComentarioEstado(obj.getComentarioEstado());
        ent.setEstado(obj.getEstado());
        ent.setFuenteOrigen(obj.getFuenteOrigen());
        if (obj.getGnEmpresa() != null && obj.getGnEmpresa().getId() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getGnEmpresa().getId()));
        }
        ent.setVersion(obj.getVersion().equals(1));
        //ent.setConsecutivo(obj.getConsecutivo());
        ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
        ent.setMaeViaIngresoCodigo(obj.getMaeViaIngresoCodigo());
        ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
        ent.setMaeCondicionDestinoId(obj.getMaeCondicionDestinoId());
        ent.setMaeCondicionDestinoCodigo(obj.getMaeCondicionDestinoCodigo());
        ent.setMaeCondicionDestinoValor(obj.getMaeCondicionDestinoValor());
        ent.setAfiliadoDireccionAlternativa(obj.getDireccionAlternativa());
        return ent;
    }
}
