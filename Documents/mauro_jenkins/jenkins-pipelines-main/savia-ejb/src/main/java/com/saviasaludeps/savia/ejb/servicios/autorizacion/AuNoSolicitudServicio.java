package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudItem;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudItems;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.servicios.contratacion.CntProfesionalServicio;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuNoSolicitudRemoto.class)
public class AuNoSolicitudServicio extends GenericoServicio implements AuNoSolicitudRemoto {

    final static int CANTIDAD_BATCH = 1000;
    final static SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM AuNoSolicitudes u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }

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
                        case "cntPrestadorEntregaId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadorEntregaId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliadosId.numeroDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '").append(e.getValue()).append("') ");
                            break;
                        case "usuarioCrea":
                           strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
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
    public List<AuNoSolicitud> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AuNoSolicitud> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudes u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getEmpresaId() != null) {
                strTitulo = agregarJoin("INNER JOIN GnEmpresas gne ON u.gnEmpresasId = gne.id ", strTitulo);
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            }
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
                        case "cntPrestadorEntregaId.razonSocial":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cnp ON u.cntPrestadorEntregaId = cnp.id ", strTitulo);
                            strQuery.append("AND cnp.razonSocial LIKE '%").append(e.getValue()).append("%' ");
                            break;
                        case "asegAfiliadosId.numeroDocumento":
                            strTitulo = agregarJoin("INNER JOIN AsegAfiliados aseg ON u.asegAfiliadosId = aseg.id ", strTitulo);
                            strQuery.append("AND aseg.id IN ( SELECT ad.asegAfiliadosId.id FROM AsegAfiliadoDocumentos ad WHERE ad.numeroDocumento = '").append(e.getValue()).append("') ");
                            break;
                        case "usuarioCrea":
                           strQuery.append("AND u.usuarioCrea LIKE '%").append(e.getValue()).append("%' ");
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
            List<AuNoSolicitudes> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AuNoSolicitudes per : list) {
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
            return sql += join;
        }
    }

    @Override
    public AuNoSolicitud consultar(int id) throws Exception {
        AuNoSolicitud objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudes) getEntityManager().find(AuNoSolicitudes.class, id));
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
    public AuNoSolicitud consultarCorto(int id) throws Exception {
        AuNoSolicitud objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudes) getEntityManager().find(AuNoSolicitudes.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            objRes = null;
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    @Override
    public int insertar(AuNoSolicitud obj) throws Exception {
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
    public void actualizar(AuNoSolicitud obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudes SET "
                    + "asegAfiliadosId.id = :asegAfiliadosId, "
                    + "consecutivoOrden = :consecutivoOrden, "
                    + "fechaOrdenMedica = :fechaOrdenMedica, "
                    + "maeAmbitoAtencionId = :maeAmbitoAtencionId, "
                    + "maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo, "
                    + "maeAmbitoAtencionValor = :maeAmbitoAtencionValor, "
                    + "maeAmbitoAtencionTipo = :maeAmbitoAtencionTipo, "
                    + "tutela = :tutela, "
                    + "tipoFormula = :tipoFormula, "
                    + "maServicioHabilitacionId = :maServicioHabilitacionId, "
                    + "maServicioHabilitacionCodigo = :maServicioHabilitacionCodigo, "
                    + "maServicioHabilitacionValor = :maServicioHabilitacionValor, "
                    + "cntPrestadorId.id = :cntPrestadorId, "
                    + "cntPrestadorSedesId = :cntPrestadorSedesId, "
                    + "cntProfesionalesId.id = :cntProfesionalesId, "
                    + "cntPrestadorEntregaId = :cntPrestadorEntregaId, "
                    + "cntPrestadorSedeEntregaId = :cntPrestadorSedeEntregaId, "
                    + "justificacionClinica = :justificacionClinica, "
                    + "maeMotivoSinAutorizacionId = :maeMotivoSinAutorizacionId, "
                    + "maeMotivoSinAutorizacionCodigo = :maeMotivoSinAutorizacionCodigo, "
                    + "maeMotivoSinAutorizacionValor = :maeMotivoSinAutorizacionValor, "
                    + "maeMotivoSinAutorizacionTipo = :maeMotivoSinAutorizacionTipo, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("asegAfiliadosId", obj.getAsegAfiliadosId().getId());
            query.setParameter("consecutivoOrden", obj.getConsecutivoOrden());
            query.setParameter("fechaOrdenMedica", obj.getFechaOrdenMedica());
            query.setParameter("maeAmbitoAtencionId", obj.getMaeAmbitoAtencionId());
            query.setParameter("maeAmbitoAtencionCodigo", obj.getMaeAmbitoAtencionCodigo());
            query.setParameter("maeAmbitoAtencionValor", obj.getMaeAmbitoAtencionValor());
            query.setParameter("maeAmbitoAtencionTipo", obj.getMaeAmbitoAtencionTipo());
            query.setParameter("tutela", obj.isTutela());
            query.setParameter("tipoFormula", obj.getTipoFormula());
            query.setParameter("maServicioHabilitacionId", obj.getMaServicioHabilitacionId());
            query.setParameter("maServicioHabilitacionCodigo", obj.getMaServicioHabilitacionCodigo());
            query.setParameter("maServicioHabilitacionValor", obj.getMaServicioHabilitacionValor());
            query.setParameter("cntPrestadorId", obj.getCntPrestadorId().getId());
            query.setParameter("cntPrestadorSedesId", obj.getCntPrestadorSedesId().getId());
            query.setParameter("cntProfesionalesId", obj.getCntProfesionalesId().getId());
            query.setParameter("cntPrestadorEntregaId", obj.getCntPrestadorEntregaId().getId());
            query.setParameter("cntPrestadorSedeEntregaId", obj.getCntPrestadorSedeEntregaId().getId());
            query.setParameter("justificacionClinica", obj.getJustificacionClinica());
            query.setParameter("maeMotivoSinAutorizacionId", obj.getMaeMotivoSinAutorizacionId());
            query.setParameter("maeMotivoSinAutorizacionCodigo", obj.getMaeMotivoSinAutorizacionCodigo());
            query.setParameter("maeMotivoSinAutorizacionValor", obj.getMaeMotivoSinAutorizacionValor());
            query.setParameter("maeMotivoSinAutorizacionTipo", obj.getMaeMotivoSinAutorizacionTipo());
            //auditoria
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEstado(AuNoSolicitud obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudes SET "
                    + "estado = :estado, "
                    + "estadoJustificacion = :estadoJustificacion, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("estadoJustificacion", obj.getEstadoJustificacion());
            //auditoria
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public boolean insertarCargaMasivaSinAutoizacion(List<AuNoSolicitud> listaAuNoSolicitud, int idCarga) throws Exception {
        boolean respuesta = false;
        int contadorInserts = 0;
        StringBuilder stringBuilder = new StringBuilder(10000000);
        int cantidadLista = listaAuNoSolicitud.size();
        String columnas = "gn_empresas_id,";
        columnas += "cnt_prestador_id,";
        columnas += "aseg_afiliados_id,";
        columnas += "cnt_profesionales_id,";
        columnas += "cnt_prestador_sedes_id,";
        columnas += "fuente_origen,";
        columnas += "estado,";
        columnas += "estado_justificacion,";
        columnas += "cnt_prestador_entrega_id,";
        columnas += "cnt_prestador_sede_entrega_id,";
        columnas += "au_no_solicitud_cargas_id,";
        columnas += "fecha_orden_medica,";
        columnas += "mae_ambito_atencion_id,";
        columnas += "mae_ambito_atencion_codigo,";
        columnas += "mae_ambito_atencion_valor,";
        columnas += "mae_ambito_atencion_tipo,";
        columnas += "ma_servicio_habilitacion_id,";
        columnas += "ma_servicio_habilitacion_codigo,";
        columnas += "ma_servicio_habilitacion_valor,";
        columnas += "ma_especialidad_id,";
        columnas += "ma_especialidad_codigo,";
        columnas += "ma_especialidad_valor,";
        columnas += "justificacion_clinica,";
        columnas += "consecutivo_orden,";
        columnas += "tutela,";
        columnas += "tipo_formula,";
        columnas += "mae_motivo_sin_autorizacion_id,";
        columnas += "mae_motivo_sin_autorizacion_codigo,";
        columnas += "mae_motivo_sin_autorizacion_valor,";
        columnas += "mae_motivo_sin_autorizacion_tipo,";
        columnas += "usuario_crea,";
        columnas += "terminal_crea,";
        columnas += "fecha_hora_crea";
        String query = "insert into au_no_solicitudes (" + columnas + ") values ";
        stringBuilder.append(query);
        for (AuNoSolicitud obj : listaAuNoSolicitud) {
            contadorInserts++;
            stringBuilder.append("(");
            if (obj.getGnEmpresasId() != null) {
                stringBuilder.append("").append(obj.getGnEmpresasId().getId()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getCntPrestadorId().getId()).append("',");
            stringBuilder.append("'").append(obj.getAsegAfiliadosId().getId()).append("',");
            if (obj.getCntProfesionalesId() != null) {
                stringBuilder.append("'").append(obj.getCntProfesionalesId().getId()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getCntPrestadorSedesId().getId()).append("',");
            stringBuilder.append("'").append(obj.getFuenteOrigen()).append("',");
            stringBuilder.append("'").append(obj.getEstado()).append("',");
            if (obj.getEstadoJustificacion() != null) {
                stringBuilder.append("'").append(obj.getEstadoJustificacion()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("'").append(obj.getCntPrestadorEntregaId().getId()).append("',");
            stringBuilder.append("'").append(obj.getCntPrestadorSedeEntregaId().getId()).append("',");
            stringBuilder.append("'").append(obj.getAuNoSolicitudCargasId().getId()).append("',");
            stringBuilder.append("'").append(formatFecha.format(obj.getFechaOrdenMedica())).append("',");
            stringBuilder.append("'").append(obj.getMaeAmbitoAtencionId()).append("',");
            stringBuilder.append("'").append(obj.getMaeAmbitoAtencionCodigo()).append("',");
            stringBuilder.append("'").append(obj.getMaeAmbitoAtencionValor()).append("',");
            if (obj.getMaeAmbitoAtencionTipo() != null) {
                stringBuilder.append("'").append(obj.getMaeAmbitoAtencionTipo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if(obj.getMaServicioHabilitacionId() != null){
                stringBuilder.append("").append(obj.getMaServicioHabilitacionId()).append(",");
            }else{
                stringBuilder.append("NULL,");
            }
            if(obj.getMaServicioHabilitacionCodigo() != null){
                stringBuilder.append("'").append(obj.getMaServicioHabilitacionCodigo()).append("',");
            }else{
                stringBuilder.append("NULL,");
            }
            if(obj.getMaServicioHabilitacionValor() != null){
                stringBuilder.append("'").append(obj.getMaServicioHabilitacionValor()).append("',");
            }else{
                stringBuilder.append("NULL,");
            }
            if(obj.getMaEspecialidadId() != null){
                stringBuilder.append("'").append(obj.getMaEspecialidadId()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaEspecialidadCodigo() != null) {
                stringBuilder.append("'").append(obj.getMaEspecialidadCodigo()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getMaEspecialidadValor() != null) {
                stringBuilder.append("'").append(obj.getMaEspecialidadValor()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getJustificacionClinica() != null) {
                stringBuilder.append("'").append(obj.getJustificacionClinica()).append("',");
            } else {
                stringBuilder.append("NULL,");
            }
            if (obj.getConsecutivoOrden() != null) {
                stringBuilder.append("").append(obj.getConsecutivoOrden()).append(",");
            } else {
                stringBuilder.append("NULL,");
            }
            stringBuilder.append("b'").append(obj.isTutela() ? "1" : "0").append("',");
            stringBuilder.append("").append(obj.getTipoFormula()).append(",");
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
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = getConnection();
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                    respuesta = true;
                } catch (SQLException ex) {
                    System.out.println("Error cargando anexos 3: " + ex.toString());
                    respuesta = false;
                } finally {
                    //stringBuilder = new StringBuilder(10000000);
                    //query = "insert into au_anexos3 (" + columnas + ") values ";
                    //stringBuilder.append(query);
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
            }
        }
        //listaAuNoSolicitud = obtenerIdYInsertarItems(listaAuNoSolicitud, idCarga, exitosos, fallidos);
        return respuesta;
    }

    @Override
    public List<AuNoSolicitud> obtenerIdSolicitudes(List<AuNoSolicitud> listaNegocio, int idCarga) throws Exception {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection conn = null;
        String strQuery;
        try {
            //Obtener id de detalles
            strQuery = "SELECT ans.id, "
                    + "ans.aseg_afiliados_id, "
                    + "ans.mae_ambito_atencion_id, "
                    + "ans.fuente_origen "
                    + "FROM au_no_solicitudes ans "
                    + "WHERE ans.au_no_solicitud_cargas_id = " + idCarga + " "
                    + "ORDER BY ans.id ASC ";
            conn = getConnection();
            ps = conn.prepareStatement(strQuery);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                //Obtener el id 
                //TODO: Se requiere implementar una columna que maneje el valor del consecutivo para hacer un match seguro
                for (AuNoSolicitud noSolicitud : listaNegocio) {
                    int afiliadoId = resultSet.getInt("aseg_afiliados_id");
                    int maeAmbitoAtencionId = resultSet.getInt("mae_ambito_atencion_id");
                    //int maServicioSolicitadoId = resultSet.getInt("ma_servicio_solicitado_id");
                    //int maServicioHabilitadoId = resultSet.getInt("ma_servicio_habilitacion_id");
                    //int maeOrigenAtencionId = resultSet.getInt("mae_origen_atencion_id");
                    //int maeTipoServicioId = resultSet.getInt("mae_tipo_servicio_id");
                    //int maeUbicacionId = resultSet.getInt("mae_ubicacion_id");
                    //int cntProfesionalesId = resultSet.getInt("cnt_profesionales_id");
                    int fuenteOrigen = resultSet.getInt("fuente_origen");
                    if (noSolicitud.getId() == null) {
                        if (Objects.equals(noSolicitud.getAsegAfiliadosId().getId(), afiliadoId)
                                && noSolicitud.getMaeAmbitoAtencionId() == maeAmbitoAtencionId
                                //&& anexo3.getMaServicio() == maServicioSolicitadoId
                                //&& noSolicitud.getMaServicioHabilitacionId() == maServicioHabilitadoId
                                //&& anexo3.getMaeOrigenAtencionId() == maeOrigenAtencionId
                                //&& anexo3.getMaeTipoServicioId() == maeTipoServicioId
                                //&& anexo3.getMaeUbicacionId() == maeUbicacionId
                                && noSolicitud.getFuenteOrigen() == fuenteOrigen) {
                            noSolicitud.setId(resultSet.getInt("id"));
                            break;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error consultando no solicitud: " + ex.toString());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        //AuNoSolicitudItemServicio auNoSolicitudItemServicio = new AuNoSolicitudItemServicio();
        //auNoSolicitudItemServicio.insertarItems(listaNegocio, exitosos, fallidos, idCarga);
        //listaNegocio = registroBitacora(listaNegocio);
        //AuNoSolicitudDiagnosticoServicio auNoSolicitudDiagnosticoServicio = new AuNoSolicitudDiagnosticoServicio();
        //auNoSolicitudDiagnosticoServicio.insertarDiagnosticos(listaNegocio);
        /*for (AuAnexo3 item : listaNegocio) {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(item.getAsegAfiliadoId().getId());
            item.setAsegAfiliadoId(afiliado);
            item.setErrores(new ArrayList<>());
            getAuAnexo3SolicitudAfiliadoRemoto().insertar(item);
        }*/

        //AuNoSolicitudCargaDetalleServicio auNoSolicitudCargaDetalleServicio = new AuNoSolicitudCargaDetalleServicio();
        //auNoSolicitudCargaDetalleServicio.llenarAnexo3IdCargaDetalle(listaNegocio);
        return listaNegocio;
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

    public static void closeConnection(Connection conn) throws ClassNotFoundException, SQLException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión");
            }
        }
    }

    public static AuNoSolicitud castEntidadNegocioCorto(AuNoSolicitudes ent) {
        AuNoSolicitud obj = new AuNoSolicitud();
        obj.setId(ent.getId());
        obj.setEstado(ent.getEstado());
        List<AuNoSolicitudItem> listaItem = new ArrayList<>();
        if (ent.getAuNoSolicitudItemsList() != null) {
            for (AuNoSolicitudItems item : ent.getAuNoSolicitudItemsList()) {
                if(!item.getBorrado()){
                    listaItem.add(AuNoSolicitudItemServicio.castEntidadNegocioCorto(item));
                }
            }
        }
        if (ent.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setId(ent.getAsegAfiliadosId().getId());
            afiliado.setMaeTipoDocumentoValor(ent.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setNumeroDocumento(ent.getAsegAfiliadosId().getNumeroDocumento());
            afiliado.setPrimerNombre(ent.getAsegAfiliadosId().getPrimerNombre());
            afiliado.setSegundoNombre(ent.getAsegAfiliadosId().getSegundoNombre());
            afiliado.setPrimerApellido(ent.getAsegAfiliadosId().getPrimerApellido());
            afiliado.setSegundoApellido(ent.getAsegAfiliadosId().getSegundoApellido());
            obj.setAsegAfiliadosId(afiliado);
        }
        if (ent.getCntPrestadorEntregaId() > 0) {
            CntPrestador prestadorEntrega = new CntPrestador();
            prestadorEntrega.setId(ent.getCntPrestadorEntregaId());
            obj.setCntPrestadorEntregaId(prestadorEntrega);
        }
        obj.setListAuNoSolicitudItem(listaItem);
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitud castEntidadNegocioLargo(AuNoSolicitudes ent) {
        AuNoSolicitud obj = new AuNoSolicitud();
        obj.setId(ent.getId());
        if (ent.getGnEmpresasId() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(ent.getGnEmpresasId().getId());
            obj.setGnEmpresasId(empresa);
        }
        obj.setFuenteOrigen(ent.getFuenteOrigen());
        obj.setEstado(ent.getEstado());
        obj.setEstadoJustificacion(ent.getEstadoJustificacion());
        if (ent.getAsegAfiliadosId() != null) {
            AsegAfiliado afiliado = new AsegAfiliado();
            afiliado.setId(ent.getAsegAfiliadosId().getId());
            obj.setAsegAfiliadosId(afiliado);
        }
        if (ent.getCntProfesionalesId() != null) {
            obj.setCntProfesionalesId(CntProfesionalServicio.castEntidadNegocio(ent.getCntProfesionalesId()));
        }
        if (ent.getCntPrestadorId() != null) {
            CntPrestador prestador = new CntPrestador();
            prestador.setId(ent.getCntPrestadorId().getId());
            obj.setCntPrestadorId(prestador);
        }

        if (ent.getCntPrestadorSedesId() > 0) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede();
            prestadorSede.setId(ent.getCntPrestadorSedesId());
            obj.setCntPrestadorSedesId(prestadorSede);
        }

        if (ent.getCntPrestadorEntregaId() > 0) {
            CntPrestador prestador = new CntPrestador();
            prestador.setId(ent.getCntPrestadorEntregaId());
            obj.setCntPrestadorEntregaId(prestador);
        }

        if (ent.getCntPrestadorSedeEntregaId() > 0) {
            CntPrestadorSede prestadorSede = new CntPrestadorSede();
            prestadorSede.setId(ent.getCntPrestadorSedeEntregaId());
            obj.setCntPrestadorSedeEntregaId(prestadorSede);
        }

        obj.setFechaOrdenMedica(ent.getFechaOrdenMedica());
        obj.setMaeAmbitoAtencionId(ent.getMaeAmbitoAtencionId());
        obj.setMaeAmbitoAtencionCodigo(ent.getMaeAmbitoAtencionCodigo());
        obj.setMaeAmbitoAtencionValor(ent.getMaeAmbitoAtencionValor());
        obj.setMaeAmbitoAtencionTipo(ent.getMaeAmbitoAtencionTipo());
        obj.setMaServicioHabilitacionId(ent.getMaServicioHabilitacionId());
        obj.setMaServicioHabilitacionCodigo(ent.getMaServicioHabilitacionCodigo());
        obj.setMaServicioHabilitacionValor(ent.getMaServicioHabilitacionValor());
        obj.setMaEspecialidadId(ent.getMaEspecialidadId());
        obj.setMaEspecialidadCodigo(ent.getMaEspecialidadCodigo());
        obj.setMaEspecialidadValor(ent.getMaEspecialidadValor());
        obj.setJustificacionClinica(ent.getJustificacionClinica());;
        obj.setConsecutivoOrden(ent.getConsecutivoOrden());
        obj.setTutela(ent.getTutela());
        obj.setTipoFormula(ent.getTipoFormula());
        obj.setMaeMotivoSinAutorizacionId(ent.getMaeMotivoSinAutorizacionId());
        obj.setMaeMotivoSinAutorizacionCodigo(ent.getMaeMotivoSinAutorizacionCodigo());
        obj.setMaeMotivoSinAutorizacionValor(ent.getMaeMotivoSinAutorizacionValor());
        obj.setMaeMotivoSinAutorizacionTipo(ent.getMaeMotivoSinAutorizacionTipo());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

    public static AuNoSolicitudes castNegocioEntidad(AuNoSolicitud obj) {
        AuNoSolicitudes ent = new AuNoSolicitudes();
        ent.setId(obj.getId());
        if (obj.getGnEmpresasId() != null) {
            GnEmpresas empresa = new GnEmpresas();
            empresa.setId(obj.getGnEmpresasId().getId());
            ent.setGnEmpresasId(empresa);
        }
        ent.setFuenteOrigen(obj.getFuenteOrigen());
        ent.setEstado(obj.getEstado());
        ent.setEstadoJustificacion(obj.getEstadoJustificacion());
        if (obj.getAsegAfiliadosId() != null) {
            AsegAfiliados afiliado = new AsegAfiliados();
            afiliado.setId(obj.getAsegAfiliadosId().getId());
            ent.setAsegAfiliadosId(afiliado);
        }
        if (obj.getCntProfesionalesId() != null && obj.getCntProfesionalesId().getId() != null) {
            CntProfesionales profesional = new CntProfesionales();
            profesional.setId(obj.getCntProfesionalesId().getId());
            ent.setCntProfesionalesId(profesional);
        }
        if (obj.getCntPrestadorId() != null) {
            CntPrestadores prestador = new CntPrestadores();
            prestador.setId(obj.getCntPrestadorId().getId());
            ent.setCntPrestadorId(prestador);
        }
        if (obj.getCntPrestadorSedesId() != null) {
            ent.setCntPrestadorSedesId(obj.getCntPrestadorSedesId().getId());
        }
        if (obj.getCntPrestadorEntregaId() != null) {
            ent.setCntPrestadorEntregaId(obj.getCntPrestadorEntregaId().getId());
        }
        if (obj.getCntPrestadorSedeEntregaId() != null) {
            ent.setCntPrestadorSedeEntregaId(obj.getCntPrestadorSedeEntregaId().getId());
        }
        ent.setFechaOrdenMedica(obj.getFechaOrdenMedica());
        ent.setMaeAmbitoAtencionId(obj.getMaeAmbitoAtencionId());
        ent.setMaeAmbitoAtencionCodigo(obj.getMaeAmbitoAtencionCodigo());
        ent.setMaeAmbitoAtencionValor(obj.getMaeAmbitoAtencionValor());
        ent.setMaeAmbitoAtencionTipo(obj.getMaeAmbitoAtencionTipo());
        ent.setMaServicioHabilitacionId(obj.getMaServicioHabilitacionId());
        ent.setMaServicioHabilitacionCodigo(obj.getMaServicioHabilitacionCodigo());
        ent.setMaServicioHabilitacionValor(obj.getMaServicioHabilitacionValor());
        ent.setMaEspecialidadId(obj.getMaEspecialidadId());
        ent.setMaEspecialidadCodigo(obj.getMaEspecialidadCodigo());
        ent.setMaEspecialidadValor(obj.getMaEspecialidadValor());
        ent.setJustificacionClinica(obj.getJustificacionClinica());
        ent.setConsecutivoOrden(obj.getConsecutivoOrden());
        ent.setTutela(obj.isTutela());
        ent.setTipoFormula(obj.getTipoFormula());
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
