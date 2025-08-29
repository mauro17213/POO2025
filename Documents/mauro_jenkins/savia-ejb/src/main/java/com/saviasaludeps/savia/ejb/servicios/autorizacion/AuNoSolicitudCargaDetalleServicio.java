/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargaDetalles;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargas;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudCargaDetalleRemoto;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.sql.DataSource;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuNoSolicitudCargaDetalleRemoto.class)
public class AuNoSolicitudCargaDetalleServicio extends GenericoServicio implements AuNoSolicitudCargaDetalleRemoto {
    
    final static SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
    final static int CANTIDAD_BATCH = 1000;
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudCargaDetalles u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
 
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "maeClasificacionId.id":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeClasificacionId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
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
    public List<AuNoSolicitudCargaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudCargaDetalle> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudCargaDetalles u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "tipo":
                            strQuery.append("AND u.tipo = ").append(e.getValue()).append(" ");
                            break;
                        case "estado":
                            strQuery.append("AND u.estado = ").append(e.getValue()).append(" ");
                            break;
                        case "maeClasificacionId.id":
                            strTitulo = agregarJoin("INNER JOIN GnMaestros maes ON u.maeClasificacionId = maes.id ", strTitulo);
                            strQuery.append("AND maes.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadoresId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<AuNoSolicitudCargaDetalles> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudCargaDetalles per : list) {
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
    public int consultarCantidadDetalleLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudCargaDetalles u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strTitulo = agregarJoin("INNER JOIN AuNoSolicitudCargas auc ON u.auNoSolicitudCargasId = auc.id ", strTitulo);
                strQuery.append("AND auc.id = ").append(paramConsulta.getParametroConsulta3()).append(" ");
            }  
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "consecutivo":
                            strQuery.append("AND u.consecutivo = ").append(e.getValue()).append(" ");
                            break;
                        case "auNoSolicitudId.id":
                            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudes aus ON u.auNoSolicitudId = aus.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
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
    public List<AuNoSolicitudCargaDetalle> consultarDetalleLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitudCargaDetalle> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudCargaDetalles u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strTitulo = agregarJoin("INNER JOIN AuNoSolicitudCargas auc ON u.auNoSolicitudCargasId = auc.id ", strTitulo);
                strQuery.append("AND auc.id = ").append(paramConsulta.getParametroConsulta3()).append(" ");
            }  
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "consecutivo":
                            strQuery.append("AND u.consecutivo = ").append(e.getValue()).append(" ");
                            break;
                        case "auNoSolicitudId.id":
                            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudes aus ON u.auNoSolicitudId = aus.id ", strTitulo);
                            strQuery.append("AND auc.id = ").append(e.getValue()).append(" ");
                            break;
                        case "fila":
                            strQuery.append("AND u.fila = ").append(e.getValue()).append(" ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<AuNoSolicitudCargaDetalles> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudCargaDetalles per : list) {
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
    
    @SuppressWarnings("UnusedAssignment")
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql = sql + join;
        }
    }
    
    @Override
    public AuNoSolicitudCargaDetalle consultar(int id) throws Exception {
        AuNoSolicitudCargaDetalle objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudCargaDetalles) getEntityManager().find(AuNoSolicitudCargaDetalles.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(AuNoSolicitudCargaDetalle obj) throws Exception {
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
    
    @Override
    public List<AuNoSolicitudCargaDetalle> consultarDetallesByuNoSolicitudCargasId(int id) throws Exception {
        List<AuNoSolicitudCargaDetalle> obj = new ArrayList();
        try {
            String strQuery = "SELECT p "
                    + "FROM AuNoSolicitudCargaDetalles p "
                    + "INNER JOIN AuNoSolicitudCargas auc ON p.auNoSolicitudCargasId = auc.id "
                    + "WHERE p.id > 0 "
                    + "AND auc.id = " + id + " "
                    + "ORDER BY p.id DESC";
            List<AuNoSolicitudCargaDetalles> list = getEntityManager().createQuery(strQuery).getResultList();
            for (AuNoSolicitudCargaDetalles gestion : list) {
                obj.add(castEntidadNegocioLargo(gestion));
            }
        } catch (NoResultException e) {
            obj = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
    @Override
    public boolean insertarSinAutorizacionesCargaDetalles(List<AuNoSolicitudCargaDetalle> listaNegocio) throws ClassNotFoundException, SQLException {
        boolean respuesta = false;
        int contadorInserts = 0;
        StringBuilder stringBuilder = new StringBuilder(10000000);
        int cantidadLista = 0;
        String columnas = "au_no_solicitud_cargas_id,";
        columnas += "numero_no_solicitud,";
        columnas += "mae_tipo_documento_id,";
        columnas += "mae_tipo_documento_codigo,";
        columnas += "mae_tipo_documento_valor,";
        columnas += "mae_tipo_documento_tipo,";
        columnas += "documento_afiliado,";
        columnas += "mae_ambito_atencion_id,";
        columnas += "mae_ambito_atencion_codigo,";
        columnas += "mae_ambito_atencion_valor,";
        columnas += "mae_ambito_atencion_tipo,";
        columnas += "fecha_orden_medica,";
        columnas += "ma_servicio_solicitado_id,";
        columnas += "ma_servicio_solicitado_codigo,";
        columnas += "ma_servicio_solicitado_valor,";
        columnas += "codigo_reps_ips_solicita,";
        columnas += "mae_tipo_documento_profesional_id,";
        columnas += "mae_tipo_documento_profesional_codigo,";
        columnas += "mae_tipo_documento_profesional_valor,";
        columnas += "mae_tipo_documento_profesional_tipo,";
        columnas += "documento_profesional,";
        columnas += "ma_especialidad_id,";
        columnas += "ma_especialidad_codigo,";
        columnas += "ma_especialidad_valor,";
        columnas += "codigo_reps_ips_entrega,";
        columnas += "ma_diagnosticos_id,";
        columnas += "ma_diagnosticos_codigo,";
        columnas += "ma_diagnosticos_valor,";
        columnas += "principal,";
        columnas += "ma_servicio_habilitado_id,";
        columnas += "ma_servicio_habilitado_codigo,";
        columnas += "ma_servicio_habilitado_valor,"; 
        columnas += "tipo_tecnologia,";
        columnas += "ma_tecnologia_id,";
        columnas += "ma_tecnologia_codigo,";
        columnas += "ma_tecnologia_valor,";
        columnas += "ma_medicamento_id,";
        columnas += "ma_medicamento_codigo,";
        columnas += "ma_medicamento_valor,";
        columnas += "cantidad_solicitada,";
        columnas += "dosis,";
        columnas += "frecuencia,";
        columnas += "via_administracion,";
        columnas += "justificacion_clinica,";
        columnas += "consecutivo,";
        columnas += "fila,";
        columnas += "duracion_tratamiento,";
        columnas += "tipo_formula,";
        columnas += "tutela,";
        columnas += "num_entregas,";
        columnas += "valor_unitario,";
        columnas += "alto_costo,"; 
        columnas += "pbs,"; 
        columnas += "consecutivo_orden,"; 
        columnas += "mae_motivo_sin_autorizacion_id,";
        columnas += "mae_motivo_sin_autorizacion_codigo,";
        columnas += "mae_motivo_sin_autorizacion_valor,";
        columnas += "mae_motivo_sin_autorizacion_tipo,";
        columnas += "usuario_crea,";
        columnas += "terminal_crea,";
        columnas += "fecha_hora_crea";
        String query = "INSERT INTO au_no_solicitud_carga_detalles (" + columnas + ") VALUES ";
        stringBuilder.append(query);
        cantidadLista += listaNegocio.size();
        for (AuNoSolicitudCargaDetalle obj : listaNegocio) {
            contadorInserts++;
            stringBuilder.append("(");
            stringBuilder.append("").append(obj.getAuNoSolicitudCargasId().getId()).append(",");
            if (obj.getNumeroNoSolicitud() != null && !obj.getNumeroNoSolicitud().isEmpty()) {
                stringBuilder.append("'").append(obj.getNumeroNoSolicitud()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeTipoDocumentoId() != null) {
                stringBuilder.append("").append(obj.getMaeTipoDocumentoId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getMaeTipoDocumentoCodigo()).append("',");
            if (obj.getMaeTipoDocumentoValor() != null && !obj.getMaeTipoDocumentoValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeTipoDocumentoValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeTipoDocumentoTipo() != null && !obj.getMaeTipoDocumentoTipo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeTipoDocumentoTipo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getDocumentoAfiliado()).append("',");
            if (obj.getMaeAmbitoAtencionId() != null) {
                stringBuilder.append("").append(obj.getMaeAmbitoAtencionId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeAmbitoAtencionCodigo() != null && !obj.getMaeAmbitoAtencionCodigo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeAmbitoAtencionCodigo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeAmbitoAtencionValor() != null && !obj.getMaeAmbitoAtencionValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeAmbitoAtencionValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeAmbitoAtencionTipo() != null && !obj.getMaeAmbitoAtencionTipo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeAmbitoAtencionTipo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(formatFecha.format(obj.getFechaOrdenMedica())).append("',");
            if (obj.getMaServicioSolicitadoId() != null) {
                stringBuilder.append("").append(obj.getMaServicioSolicitadoId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getMaServicioSolicitadoCodigo()).append("',");
            if (obj.getMaServicioSolicitadoValor() != null && !obj.getMaServicioSolicitadoValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaServicioSolicitadoValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getCodigoRepsIpsSolicita()).append("',");
            if (obj.getMaeTipoDocumentoProfesionalId() != null) {
                stringBuilder.append("").append(obj.getMaeTipoDocumentoProfesionalId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeTipoDocumentoProfesionalCodigo() != null && !obj.getMaeTipoDocumentoProfesionalCodigo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeTipoDocumentoProfesionalCodigo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeTipoDocumentoProfesionalValor() != null && !obj.getMaeTipoDocumentoProfesionalValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeTipoDocumentoProfesionalValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaeTipoDocumentoProfesionalTipo() != null && !obj.getMaeTipoDocumentoProfesionalTipo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaeTipoDocumentoProfesionalTipo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getDocumentoProfesional() != null){
                stringBuilder.append("'").append(obj.getDocumentoProfesional()).append("',");
            }else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaEspecialidadId() != null) {
                stringBuilder.append("").append(obj.getMaEspecialidadId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaEspecialidadCodigo() != null && !obj.getMaEspecialidadCodigo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaEspecialidadCodigo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaEspecialidadValor() != null && !obj.getMaEspecialidadValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaEspecialidadValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getCodigoRepsIpsEntrega()).append("',");
            if (obj.getMaDiagnosticosId() != null) {
                stringBuilder.append("").append(obj.getMaDiagnosticosId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaDiagnosticosCodigo() != null && !obj.getMaDiagnosticosCodigo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaDiagnosticosCodigo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaDiagnosticosValor() != null && !obj.getMaDiagnosticosValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaDiagnosticosValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("").append(obj.isPrincipal()).append(",");
            if (obj.getMaServicioHabilitadoId() != null) {
                stringBuilder.append("").append(obj.getMaServicioHabilitadoId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if(obj.getMaServicioHabilitadoCodigo() != null && !obj.getMaServicioHabilitadoCodigo().isEmpty()){
                stringBuilder.append("'").append(obj.getMaServicioHabilitadoCodigo()).append("',");
            }else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaServicioHabilitadoValor() != null && !obj.getMaServicioHabilitadoValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaServicioHabilitadoValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("").append(obj.getTipoTecnologia()).append(",");
            if (obj.getMaTecnologiaId() != null) {
                stringBuilder.append("").append(obj.getMaTecnologiaId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaTecnologiaCodigo() != null && !obj.getMaTecnologiaCodigo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaTecnologiaCodigo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaTecnologiaValor() != null && !obj.getMaTecnologiaValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaTecnologiaValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaMedicamentoId() != null) {
                stringBuilder.append("").append(obj.getMaMedicamentoId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaMedicamentoCodigo() != null && !obj.getMaMedicamentoCodigo().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaMedicamentoCodigo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaMedicamentoValor() != null && !obj.getMaMedicamentoValor().isEmpty()) {
                stringBuilder.append("'").append(obj.getMaMedicamentoValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getCantidadSolicitada() != null) {
                stringBuilder.append("").append(obj.getCantidadSolicitada()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getDosis() != null) {
                stringBuilder.append("'").append(obj.getDosis()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getFrecuencia() != null) {
                stringBuilder.append("").append(obj.getFrecuencia()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getViaAdministracion() != null) {
                stringBuilder.append("").append(obj.getViaAdministracion()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getJustificacionClinica()).append("',");
            stringBuilder.append("'").append(obj.getConsecutivo()).append("',");
            stringBuilder.append("").append(obj.getFila()).append(",");
            if (obj.getDuracionTratamiento() != null) {
                stringBuilder.append("").append(obj.getDuracionTratamiento()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("").append(obj.getTipoFormula()).append(",");
            stringBuilder.append("").append(obj.isTutela()).append(",");
            stringBuilder.append("").append(obj.getNumEntregas()).append(",");
            stringBuilder.append("").append(obj.getValorUnitario()).append(","); 
            stringBuilder.append("").append(obj.isAltoCosto()).append(",");
            stringBuilder.append("").append(obj.isPbs()).append(",");
            if (obj.getConsecutivoOrden() != null) {
                stringBuilder.append("").append(obj.getConsecutivoOrden()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("").append(obj.getMaeMotivoSinAutorizacionId()).append(",");
            stringBuilder.append("'").append(obj.getMaeMotivoSinAutorizacionCodigo()).append("',"); 
            stringBuilder.append("'").append(obj.getMaeMotivoSinAutorizacionValor()).append("',");
            stringBuilder.append("'").append(obj.getMaeMotivoSinAutorizacionTipo()).append("',");
            stringBuilder.append("'").append(obj.getUsuarioCrea()).append("',");
            stringBuilder.append("'").append(obj.getTerminalCrea()).append("',");
            stringBuilder.append(" NOW() ");
            stringBuilder.append("),");
            if (contadorInserts % CANTIDAD_BATCH == 0 || contadorInserts == cantidadLista) {
                //Remover ultima coma y cerrar el paretensis
                query = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
                query = query.replace("?", "");
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = getConnection();
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                    respuesta = true;
                } catch (SQLException ex) {
                    System.out.println("Error insertando detalles de carga: " + ex.toString());
                } catch (java.lang.Exception ex) {
                    Logger.getLogger(AuNoSolicitudCargaDetalleServicio.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    //stringBuilder = new StringBuilder(10000000);
                    //query = "insert into au_anexo3_carga_detalles (" + columnas + ") values ";
                    //stringBuilder.append(query);
                    if(ps != null){
                        ps.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                    
                }
            }
        }
        return respuesta;
        //finalizarProceso(idCarga, ESTADO_CARGA_PROCESADO);
    }
    
    @Override
    public List<ValidaRespuestaDTO> ejecutarSelFuncionesSinAutorizacion(List<String> listaQuery) throws SQLException, Exception {
        ResultSet rs = null;
        Statement stmt = null;
        ResultSetMetaData rsMetaData = null;
        Connection conn = null;
        List<ValidaRespuestaDTO> listaRespuestas = new ArrayList<>();
        ValidaRespuestaDTO respuesta;
        String strQueryUnion = "";
        int y = 0;
        try {
            int batchSize = 500;
            for (String query : listaQuery) {
                y++;
                strQueryUnion += query + " UNION ";
                if (y >= batchSize || y == listaQuery.size()) {
                    try {
                        strQueryUnion = strQueryUnion.substring(0, strQueryUnion.length() - 6);
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(strQueryUnion);
                        rsMetaData = rs.getMetaData();
                        int cantidadColumnas = rsMetaData.getColumnCount();
                        ArrayList<String> listaColumnas = new ArrayList<>();
                        for (int i = 1; i <= cantidadColumnas; i++) {
                            listaColumnas.add(rsMetaData.getColumnName(i));
                        }
                        while (rs.next()) {
                            for (int x = 0; x <= (cantidadColumnas - 2); x++) {
                                String strSplit = rs.getString(listaColumnas.get(x));
                                if (strSplit != null) {
                                    if (!strSplit.equals("N/A")) {
                                        String[] strSplitPartes = strSplit.split("\\|");
                                        respuesta = new ValidaRespuestaDTO(
                                                rs.getInt("fila"),
                                                Integer.parseInt(strSplitPartes[0]),
                                                strSplitPartes[1],
                                                listaColumnas.get(x)
                                        );
                                        listaRespuestas.add(respuesta);
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        //TODO: Controlar error
                        System.out.println("Error validando carga masiva de autorizaciones: " + ex.toString());
                    } 
                }
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                 conn.close();
            }
            strQueryUnion = "";
            y = 0;
        }
        return listaRespuestas;
    }
    
    @Override
    public List<String> itemsByAfiliadoByFechaOrdenMedica(int afiliado, String tecnologias, String estados, Date fechaOrdenMedica) {
        List<String> listaItems = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection conn = null;
        StringBuilder strQuery = new StringBuilder();
        try {
            strQuery.append("SELECT p.ma_tecnologia_codigo,a.id FROM au_anexo3_items p ");
            strQuery.append("INNER JOIN au_anexos3 a on a.id =p.au_anexos3_id ");
            strQuery.append("INNER JOIN aseg_afiliados aa on aa.id =a.aseg_afiliados_id ");
            strQuery.append("WHERE aa.id=").append(afiliado);
            strQuery.append(" AND a.fecha_solicitud='").append(formatFecha.format(fechaOrdenMedica)).append("' ");
            strQuery.append("AND p.ma_tecnologia_id  IN(").append(tecnologias).append(") ");
            strQuery.append("AND p.estado IN(").append(estados).append(") ");

            conn = getConnection();
            ps = conn.prepareStatement(strQuery.toString());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {         
                listaItems.add(resultSet.getInt("id") + "-" + resultSet.getString("ma_tecnologia_codigo"));
            }
        } catch (Exception ex) {
            System.out.println("Error obteniendo items fecha orden medica " + ex.toString());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if(ps != null){
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error cerrando los objetos mysql");
            }
        }
        return listaItems;
    }
    
    @Override
    public boolean llenarSinAutorizacionesIdCargaDetalle(List<AuNoSolicitud> listaNegocio) throws SQLException {
        boolean respuesta = false;
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        try {
            String query = "UPDATE au_no_solicitud_carga_detalles SET au_no_solicitud_id = ? WHERE au_no_solicitud_cargas_id = ? AND consecutivo = ? ";
            conn = getConnection();
            preparedStmt = conn.prepareStatement(query);
            //conn.setAutoCommit(false);
            int contadorUpdate = 0;
            int cantidadLista = listaNegocio.size();
            for (AuNoSolicitud noSolicitud : listaNegocio) {
                preparedStmt.setInt(1, noSolicitud.getId());
                preparedStmt.setInt(2, noSolicitud.getAuNoSolicitudCargasId().getId());
                preparedStmt.setString(3, noSolicitud.getConsecutivo());
                preparedStmt.addBatch();
                contadorUpdate++;                
            }
            if (contadorUpdate % CANTIDAD_BATCH == 0 || contadorUpdate == cantidadLista) {
                preparedStmt.executeBatch();
                //conn.commit();
            }
            respuesta = true;
        } catch (SQLException ex) {
            System.out.println("Error actualizando id de sin autorizacion para los detalles de carga de solicitudes : " + ex.toString());
            respuesta = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuNoSolicitudCargaDetalleServicio.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = false;
        } catch (java.lang.Exception ex) {
            Logger.getLogger(AuNoSolicitudCargaDetalleServicio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(preparedStmt != null){
                preparedStmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return respuesta;
    }
    
    public Connection getConnection() throws java.lang.Exception {
        Connection dbConnection = null;
        try {
            DataSource data = (DataSource) getEntityManager().getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
            if (data == null) {
                throw new Exception("No se pudo obtener el datasource ");
            }
            dbConnection = (Connection) data.getConnection();
            if (dbConnection == null) {
                throw new Exception("No se pudo obtener la conexion con DB");
            }
        } catch (SQLException e) {
            Exception("Error obtener conexion sql de GenericoServicio : ", e);
        } catch (Exception e) {
            Exception("Error general al obtener la conexion : ", e);
        }

        return dbConnection;
    }
    
    public static AuNoSolicitudCargaDetalle castEntidadNegocioCorto(AuNoSolicitudCargaDetalles ent) {
        AuNoSolicitudCargaDetalle obj = new AuNoSolicitudCargaDetalle();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudCargasId() != null){
            AuNoSolicitudCarga carga = new AuNoSolicitudCarga();
            carga.setId(ent.getAuNoSolicitudCargasId().getId());
            obj.setAuNoSolicitudCargasId(carga);
        }
        if(ent.getAuNoSolicitudId() != null){
            AuNoSolicitud solicitud = new AuNoSolicitud();
            solicitud.setId(ent.getAuNoSolicitudId().getId());
            obj.setAuNoSolicitudId(solicitud);
        }
        obj.setMaeTipoDocumentoId(ent.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(ent.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(ent.getMaeTipoDocumentoValor());
        obj.setDocumentoAfiliado(ent.getDocumentoAfiliado());
        obj.setFila(ent.getFila());
        obj.setConsecutivo(ent.getConsecutivo());
        //Auditoria
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        
        return obj;
    }

    public static AuNoSolicitudCargaDetalle castEntidadNegocioLargo(AuNoSolicitudCargaDetalles ent) {
        AuNoSolicitudCargaDetalle obj = new AuNoSolicitudCargaDetalle();
        obj.setId(ent.getId());
        
        if(ent.getAuNoSolicitudCargasId() != null){
            AuNoSolicitudCarga carga = new AuNoSolicitudCarga();
            carga.setId(ent.getAuNoSolicitudCargasId().getId());
            obj.setAuNoSolicitudCargasId(carga);
        }
        if(ent.getAuNoSolicitudId() != null){
            AuNoSolicitud solicitud = new AuNoSolicitud();
            solicitud.setId(ent.getAuNoSolicitudId().getId());
            obj.setAuNoSolicitudId(solicitud);
        }
        obj.setNumeroNoSolicitud(ent.getNumeroNoSolicitud());
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setMaeTipoDocumentoId(ent.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(ent.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(ent.getMaeTipoDocumentoValor());
        obj.setMaeTipoDocumentoTipo(ent.getMaeTipoDocumentoTipo());
        obj.setDocumentoAfiliado(ent.getDocumentoAfiliado());
        obj.setMaeAmbitoAtencionId(ent.getMaeAmbitoAtencionId());
        obj.setMaeAmbitoAtencionCodigo(ent.getMaeAmbitoAtencionCodigo());
        obj.setMaeAmbitoAtencionValor(ent.getMaeAmbitoAtencionValor());
        obj.setMaeAmbitoAtencionTipo(ent.getMaeAmbitoAtencionTipo());
        obj.setFechaOrdenMedica(ent.getFechaOrdenMedica());
        obj.setMaServicioSolicitadoId(ent.getMaServicioSolicitadoId());
        obj.setMaServicioSolicitadoCodigo(ent.getMaServicioSolicitadoCodigo());
        obj.setMaServicioSolicitadoValor(ent.getMaServicioSolicitadoValor());
        obj.setCodigoRepsIpsSolicita(ent.getCodigoRepsIpsSolicita());
        obj.setMaeTipoDocumentoProfesionalId(ent.getMaeTipoDocumentoProfesionalId());
        obj.setMaeTipoDocumentoProfesionalCodigo(ent.getMaeTipoDocumentoProfesionalCodigo());
        obj.setMaeTipoDocumentoProfesionalValor(ent.getMaeTipoDocumentoProfesionalValor());
        obj.setMaeTipoDocumentoProfesionalTipo(ent.getMaeTipoDocumentoProfesionalTipo());
        obj.setDocumentoProfesional(ent.getDocumentoProfesional());
        obj.setMaEspecialidadId(ent.getMaEspecialidadId());
        obj.setMaEspecialidadCodigo(ent.getMaEspecialidadCodigo());
        obj.setMaEspecialidadValor(ent.getMaEspecialidadValor());
        obj.setCodigoRepsIpsEntrega(ent.getCodigoRepsIpsEntrega());
        obj.setMaDiagnosticosId(ent.getMaDiagnosticosId());
        obj.setMaDiagnosticosCodigo(ent.getMaDiagnosticosCodigo());
        obj.setMaDiagnosticosValor(ent.getMaDiagnosticosValor());
        obj.setPrincipal(ent.getPrincipal());
        obj.setMaServicioHabilitadoId(ent.getMaServicioHabilitadoId());
        obj.setMaServicioHabilitadoCodigo(ent.getMaServicioHabilitadoCodigo());
        obj.setMaServicioHabilitadoValor(ent.getMaServicioHabilitadoValor());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setMaMedicamentoId(ent.getMaMedicamentoId());
        obj.setMaMedicamentoCodigo(ent.getMaMedicamentoCodigo());
        obj.setMaMedicamentoValor(ent.getMaMedicamentoValor());
        obj.setCantidadSolicitada(ent.getCantidadSolicitada());
        obj.setDosis(ent.getDosis());
        obj.setFrecuencia(ent.getFrecuencia());
        if(ent.getViaAdministracion() != null){
             obj.setViaAdministracion(ent.getViaAdministracion().toString());
        }
        obj.setJustificacionClinica(ent.getJustificacionClinica());
        obj.setFila(ent.getFila());
        obj.setConsecutivo(ent.getConsecutivo());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setTipoFormula(ent.getTipoFormula());
        obj.setTutela(ent.getTutela());
        obj.setNumEntregas(ent.getNumEntregas());
        if(ent.getValorUnitario() != null){
            obj.setValorUnitario(ent.getValorUnitario().toString());
        }
        obj.setAltoCosto(ent.getAltoCosto());
        obj.setPbs(ent.getPbs());
        obj.setMaeMotivoSinAutorizacionId(ent.getMaeMotivoSinAutorizacionId());
        obj.setMaeMotivoSinAutorizacionCodigo(ent.getMaeMotivoSinAutorizacionCodigo());
        obj.setMaeMotivoSinAutorizacionValor(ent.getMaeMotivoSinAutorizacionValor());
        obj.setMaeMotivoSinAutorizacionTipo(ent.getMaeMotivoSinAutorizacionTipo());
        //Auditoria
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }
    
    public static AuNoSolicitudCargaDetalles castNegocioEntidad(AuNoSolicitudCargaDetalle obj) {
        AuNoSolicitudCargaDetalles ent = new AuNoSolicitudCargaDetalles();
        ent.setId(obj.getId());
        if(obj.getAuNoSolicitudCargasId() != null){
            AuNoSolicitudCargas carga = new AuNoSolicitudCargas();
            carga.setId(obj.getAuNoSolicitudCargasId().getId());
            ent.setAuNoSolicitudCargasId(carga);
        }
        if(obj.getAuNoSolicitudId() != null){
            AuNoSolicitudes solicitud = new AuNoSolicitudes();
            solicitud.setId(obj.getAuNoSolicitudId().getId());
            ent.setAuNoSolicitudId(solicitud);
        }
        ent.setNumeroNoSolicitud(obj.getNumeroNoSolicitud());
        ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        ent.setMaeTipoDocumentoTipo(obj.getMaeTipoDocumentoTipo());
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setMaeAmbitoAtencionId(obj.getMaeAmbitoAtencionId());
        ent.setMaeAmbitoAtencionCodigo(obj.getMaeAmbitoAtencionCodigo());
        ent.setMaeAmbitoAtencionValor(obj.getMaeAmbitoAtencionValor());
        ent.setMaeAmbitoAtencionTipo(obj.getMaeAmbitoAtencionTipo());
        ent.setFechaOrdenMedica(obj.getFechaOrdenMedica());
        ent.setMaServicioSolicitadoId(obj.getMaServicioSolicitadoId());
        ent.setMaServicioSolicitadoCodigo(obj.getMaServicioSolicitadoCodigo());
        ent.setMaServicioSolicitadoValor(obj.getMaServicioSolicitadoValor());
        ent.setCodigoRepsIpsSolicita(obj.getCodigoRepsIpsSolicita());
        ent.setMaeTipoDocumentoProfesionalId(obj.getMaeTipoDocumentoProfesionalId());
        ent.setMaeTipoDocumentoProfesionalCodigo(obj.getMaeTipoDocumentoProfesionalCodigo());
        ent.setMaeTipoDocumentoProfesionalValor(obj.getMaeTipoDocumentoProfesionalValor());
        ent.setMaeTipoDocumentoProfesionalTipo(obj.getMaeTipoDocumentoProfesionalTipo());
        ent.setDocumentoProfesional(obj.getDocumentoProfesional());
        ent.setMaEspecialidadId(obj.getMaEspecialidadId());
        ent.setMaEspecialidadCodigo(obj.getMaEspecialidadCodigo());
        ent.setMaEspecialidadValor(obj.getMaEspecialidadValor());
        ent.setCodigoRepsIpsEntrega(obj.getCodigoRepsIpsEntrega());
        ent.setMaDiagnosticosId(obj.getMaDiagnosticosId());
        ent.setMaDiagnosticosCodigo(obj.getMaDiagnosticosCodigo());
        ent.setMaDiagnosticosValor(obj.getMaDiagnosticosValor());
        ent.setPrincipal(obj.isPrincipal());
        ent.setMaServicioHabilitadoId(obj.getMaServicioHabilitadoId());
        ent.setMaServicioHabilitadoCodigo(obj.getMaServicioHabilitadoCodigo());
        ent.setMaServicioHabilitadoValor(obj.getMaServicioHabilitadoValor());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        ent.setMaMedicamentoId(obj.getMaMedicamentoId());
        ent.setMaMedicamentoCodigo(obj.getMaMedicamentoCodigo());
        ent.setMaMedicamentoValor(obj.getMaMedicamentoValor());
        ent.setCantidadSolicitada(obj.getCantidadSolicitada());
        ent.setDosis(obj.getDosis());
        ent.setFrecuencia(obj.getFrecuencia());
        ent.setViaAdministracion(Integer.parseInt(obj.getViaAdministracion()));
        ent.setJustificacionClinica(obj.getJustificacionClinica());
        ent.setFila(obj.getFila());
        ent.setConsecutivo(obj.getConsecutivo());
        ent.setDuracionTratamiento(obj.getDuracionTratamiento());
        ent.setMaeMotivoSinAutorizacionId(obj.getMaeMotivoSinAutorizacionId());
        ent.setMaeMotivoSinAutorizacionCodigo(obj.getMaeMotivoSinAutorizacionCodigo());
        ent.setMaeMotivoSinAutorizacionValor(obj.getMaeMotivoSinAutorizacionValor());
        ent.setMaeMotivoSinAutorizacionTipo(obj.getMaeMotivoSinAutorizacionTipo());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
