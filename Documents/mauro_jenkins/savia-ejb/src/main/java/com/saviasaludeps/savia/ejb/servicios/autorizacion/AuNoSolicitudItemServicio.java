package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudItem;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudItems;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudItemRemoto;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(AuNoSolicitudItemRemoto.class)
public class AuNoSolicitudItemServicio extends GenericoServicio implements AuNoSolicitudItemRemoto {

    private final static String FN_AU_CANTIDAD = "fn_au_cantidad";
    final static int CANTIDAD_BATCH = 1000;

    @SuppressWarnings("UnusedAssignment")
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }

    @Override
    public AuNoSolicitudItem consultar(int id) throws Exception {
        AuNoSolicitudItem objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudItems) getEntityManager().find(AuNoSolicitudItems.class, id));
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
    public int insertar(AuNoSolicitudItem obj) throws Exception {
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
    public void actualizar(AuNoSolicitudItem obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudItems SET "
                    + "cantidadSolicitada = :cantidadSolicitada, "
                    + "valorUnitario = :valorUnitario, "
                    + "valorTotal = :valorTotal, "
                    + "numEntregas = :numEntregas, "
                    + "duracionTratamiento = :duracionTratamiento, "
                    + "pbs = :pbs, "
                    + "maServicioHabilitacionId = :maServicioHabilitacionId, "
                    + "maServicioHabilitacionCodigo = :maServicioHabilitacionCodigo, "
                    + "maServicioHabilitacionValor = :maServicioHabilitacionValor, "
                    + "maeViaAdministracionId = :maeViaAdministracionId, "
                    + "maeViaAdministracionCodigo = :maeViaAdministracionCodigo, "
                    + "maeViaAdministracionValor = :maeViaAdministracionValor, "
                    + "dosis = :dosis, "
                    + "frecuencia = :frecuencia, "
                    + "posologia = :posologia, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cantidadSolicitada", obj.getCantidadSolicitada());
            query.setParameter("valorUnitario", obj.getValorUnitario());
            query.setParameter("valorTotal", obj.getValorTotal());
            query.setParameter("numEntregas", obj.getNumEntregas());
            query.setParameter("duracionTratamiento", obj.getDuracionTratamiento());
            query.setParameter("pbs", obj.isPbs());
            query.setParameter("maServicioHabilitacionId", obj.getMaServicioHabilitacionId());
            query.setParameter("maServicioHabilitacionCodigo", obj.getMaServicioHabilitacionCodigo());
            query.setParameter("maServicioHabilitacionValor", obj.getMaServicioHabilitacionValor());
            query.setParameter("maeViaAdministracionId", obj.getMaeViaAdministracionId());
            query.setParameter("maeViaAdministracionCodigo", obj.getMaeViaAdministracionCodigo());
            query.setParameter("maeViaAdministracionValor", obj.getMaeViaAdministracionValor());
            query.setParameter("dosis", obj.getDosis());
            query.setParameter("frecuencia", obj.getFrecuencia());
            query.setParameter("posologia", obj.getPosologia());
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
    public void actualizarTipoEntrega(AuNoSolicitudItem obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudItems SET "
                    + "tipoEntrega = :tipoEntrega, "
                    + "estado = :estado, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipoEntrega", obj.getTipoEntrega());
            query.setParameter("estado", obj.getEstado());
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
    public void actualizarAnularSinEntrega(AuNoSolicitudItem obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudItems SET "
                    + "tipoEntrega = :tipoEntrega, "
                    + "estado = :estado, "
                    + "estadoJustificacion = :estadoJustificacion, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipoEntrega", obj.getTipoEntrega());
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
    public void actualizarBorradoLogico(AuNoSolicitudItem obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudItems SET "
                    + "borrado = :borrado, "
                    + "usuarioBorra = :usuarioBorra, "
                    + "terminalBorra = :terminalBorra, "
                    + "fechaHoraBorra = :fechaHoraBorra "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("borrado", obj.isBorrado());
            //auditoria
            query.setParameter("usuarioBorra", obj.getUsuarioBorra());
            query.setParameter("terminalBorra", obj.getTerminalBorra());
            query.setParameter("fechaHoraBorra", obj.getFechaHoraBorra());
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
    public List<AuNoSolicitudItem> consultarItemPorAuNosolicitudesId(int idAuNoSolicitudItems) throws Exception {
        List<AuNoSolicitudItem> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudItems u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudes aun ON u.auNoSolicitudesId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudItems).append(" ");
            strQuery.append("AND u.borrado = 0 ");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id DESC");

            List<AuNoSolicitudItems> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudItems diagnostico : list) {
                obj.add(castEntidadNegocioLargo(diagnostico, posicion));
                posicion++;
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
    public ValidaRespuestaDTO validarAuCantidadTecnologia(String tipoTecnologia, String cantidad, String codigoTecnologia) {
        ValidaRespuestaDTO neg;
        try {
            String strQuery = "SELECT " + FN_AU_CANTIDAD + "("
                    + "'" + tipoTecnologia + "',"
                    + "'" + cantidad + "',"
                    + "'" + codigoTecnologia + "'"
                    + ")";
            neg = ejecutarFuncionAu(strQuery);
        } catch (ClassNotFoundException | SQLException ex) {
            neg = new ValidaRespuestaDTO();
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
        }
        return neg;
    }

    //Ejecuta un query
    public ValidaRespuestaDTO ejecutarFuncionAu(String strQuery) throws ClassNotFoundException, SQLException {
        ValidaRespuestaDTO neg = new ValidaRespuestaDTO();
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strQuery);
            while (rs.next()) {
                String strSplit = rs.getString(1);
                String[] strSplitPartes = strSplit.split("\\|");
                neg.setCodigo(Integer.parseInt(strSplitPartes[0]));
                neg.setMensaje(strSplitPartes[1]);
                if (strSplitPartes.length == 3) {
                    neg.setMensaje(strSplitPartes[2]);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            neg.setCodigo(99);
            neg.setMensaje("Error SQL: " + ex.toString());
            System.out.println("Error ejecutando validación de funciones Autorizaciones: " + strQuery + " error: " + ex.toString());
        } catch (java.lang.Exception ex) {
            Logger.getLogger(AuNoSolicitudItemServicio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return neg;
    }

    @Override
    public void insertarItems(List<AuNoSolicitud> listaNegocio, int exitosos, int fallidos, int idCarga) throws ClassNotFoundException, SQLException {
        int contadorInserts = 0;
        int cantidadLista = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder(10000000);
        String columnas = "au_no_solicitudes_id,";
        columnas += "cnt_contrato_detalles_id,";
        columnas += "estado,";
        columnas += "estado_justificacion,";
        columnas += "tipo_tecnologia,";
        columnas += "ma_tecnologia_id,";
        columnas += "ma_tecnologia_codigo,";
        columnas += "ma_tecnologia_valor,";
        columnas += "ma_medicamentos_id,";
        columnas += "ma_medicamentos_codigo,";
        columnas += "ma_medicamentos_valor,";
        columnas += "cantidad_solicitada,";
        columnas += "ma_servicio_habilitacion_id,";
        columnas += "ma_servicio_habilitacion_codigo,";
        columnas += "ma_servicio_habilitacion_valor,";
        columnas += "dosis,";
        columnas += "frecuencia,";
        columnas += "posologia,";
        columnas += "mae_via_administracion_id,";
        columnas += "mae_via_administracion_codigo,";
        columnas += "mae_via_administracion_valor,";
        columnas += "mae_via_administracion_tipo,";
        columnas += "tipo_entrega,";
        columnas += "duracion_tratamiento,";
        columnas += "num_entregas,";
        columnas += "pbs,";
        columnas += "valor_unitario,";
        columnas += "valor_total,";
        columnas += "usuario_crea,";
        columnas += "terminal_crea,";
        columnas += "fecha_hora_crea";
        String query = "INSERT INTO au_no_solicitud_items (" + columnas + ") VALUES ";
        stringBuilder.append(query);
        cantidadLista = listaNegocio.stream().map(auNoSolicitud -> auNoSolicitud.getListAuNoSolicitudItem().size())
                .reduce(cantidadLista, Integer::sum);
        for (AuNoSolicitud auNoSolicitud : listaNegocio) {
            /*if (idCarga == 0) {
                auNoSolicitud.getAuNoSolicitudCargasId().getId();
            }*/
            for (AuNoSolicitudItem obj : auNoSolicitud.getListAuNoSolicitudItem()) {
                obj.setAuNoSolicitudesId(new AuNoSolicitud(auNoSolicitud.getId()));
                contadorInserts++;
                stringBuilder.append("(");
                stringBuilder.append("").append(auNoSolicitud.getId()).append(",");
                if(obj.getCntContratoDetallesId() != null){
                    stringBuilder.append("").append(obj.getCntContratoDetallesId().getId()).append(",");
                }else {
                    stringBuilder.append("NULL,");
                }
                stringBuilder.append("").append(obj.getEstado()).append(",");
                if (obj.getEstadoJustificacion() != null) {
                    stringBuilder.append("'").append(obj.getEstadoJustificacion()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                stringBuilder.append("").append(obj.getTipoTecnologia()).append(",");
                stringBuilder.append("").append(obj.getMaTecnologiaId()).append(",");
                stringBuilder.append("'").append(obj.getMaTecnologiaCodigo()).append("',");
                stringBuilder.append("'").append(obj.getMaTecnologiaValor()).append("',");
                if (obj.getMaMedicamentosId() != null) {
                    stringBuilder.append("").append(obj.getMaMedicamentosId()).append(",");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaMedicamentosCodigo() != null) {
                    stringBuilder.append("'").append(obj.getMaMedicamentosCodigo()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaMedicamentosValor() != null) {
                    stringBuilder.append("'").append(obj.getMaMedicamentosValor()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                stringBuilder.append("").append(obj.getCantidadSolicitada()).append(",");
                if (obj.getMaServicioHabilitacionId() != null) {
                    stringBuilder.append("").append(obj.getMaServicioHabilitacionId()).append(",");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaServicioHabilitacionCodigo() != null) {
                    stringBuilder.append("'").append(obj.getMaServicioHabilitacionCodigo()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaServicioHabilitacionValor() != null) {
                    stringBuilder.append("'").append(obj.getMaServicioHabilitacionValor()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getDosis() != null) {
                    stringBuilder.append("'").append(obj.getDosis()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getFrecuencia() != null) {
                    stringBuilder.append("'").append(obj.getFrecuencia()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getPosologia() != null) {
                    stringBuilder.append("'").append(obj.getPosologia()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaeViaAdministracionId() != null) {
                    stringBuilder.append("").append(obj.getMaeViaAdministracionId()).append(",");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaeViaAdministracionCodigo() != null) {
                    stringBuilder.append("'").append(obj.getMaeViaAdministracionCodigo()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaeViaAdministracionValor() != null) {
                    stringBuilder.append("'").append(obj.getMaeViaAdministracionValor()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getMaeViaAdministracionTipo() != null) {
                    stringBuilder.append("'").append(obj.getMaeViaAdministracionTipo()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                stringBuilder.append("").append(obj.getTipoEntrega()).append(",");
                if (obj.getDuracionTratamiento() != null) {
                    stringBuilder.append("'").append(obj.getDuracionTratamiento()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getNumEntregas() > 0) {
                    stringBuilder.append("'").append(obj.getNumEntregas()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                stringBuilder.append("b'").append(obj.isPbs() ? "1" : "0").append("',");
                if (obj.getValorUnitario() != null) {
                    stringBuilder.append("'").append(obj.getValorUnitario()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
                if (obj.getValorTotal() != null) {
                    stringBuilder.append("'").append(obj.getValorTotal()).append("',");
                } else {
                    stringBuilder.append("NULL,");
                }
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
                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.toString());
                    } catch (java.lang.Exception ex) {
                        Logger.getLogger(AuNoSolicitudItemServicio.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        //stringBuilder = new StringBuilder(10000000);
                        //query = "insert into au_anexo3_items (" + columnas + ") values ";
                        //stringBuilder.append(query);
                        if (ps != null) {
                            ps.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                        //AuNoSolicitudCarga carga = new AuNoSolicitudCarga();
                        //carga.setId(idCarga);
                        //carga.setRegistrosExitosos(exitosos);
                        //carga.setRegistrosRechazados(fallidos);
                        //carga.setEstado(AuNoSolicitudCarga.ESTADO_ENVIO_PROCESO);
                        //carga.setFechaHoraFin(new Date());
                        try {
                            //AuNoSolicitudCargaServicio auNoSolicitudCargaServicio = new AuNoSolicitudCargaServicio();
                            //auNoSolicitudCargaServicio.actualizarProceso(carga);
                            //actualizarEstado(exitosos, fallidos, AuNoSolicitudCarga.ESTADO_ENVIO_PROCESO, idCarga, null);
                        } catch (java.lang.Exception ex) {
                            Logger.getLogger(AuNoSolicitudItemServicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    @Override
    public AuNoSolicitudItem consultarPorIdSinAutorizonPorCodigo(int idSinAutorizacion, String codigo) throws Exception {
        AuNoSolicitudItem objRes = null;
        try {
            String strQuery = "FROM AuNoSolicitudItems p "
                    + "WHERE p.auNoSolicitudesId.id =:idAnexo "
                    + " AND p.maTecnologiaCodigo =:codigo";

            AuNoSolicitudItems item = (AuNoSolicitudItems) getEntityManager().createQuery(strQuery)
                    .setParameter("idAnexo", idSinAutorizacion)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            objRes = castEntidadNegocioLargo(item);
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
    public AuNoSolicitudItem consultarPorIdSinAutorizonPorMedicamento(int idSinAutorizacion, String codigo) throws Exception {
        AuNoSolicitudItem objRes = null;
        try {
            String strQuery = "FROM AuNoSolicitudItems p "
                    + "WHERE p.auNoSolicitudesId.id =:idAnexo "
                    + " AND p.maMedicamentosCodigo =:codigo";

            AuNoSolicitudItems item = (AuNoSolicitudItems) getEntityManager().createQuery(strQuery)
                    .setParameter("idAnexo", idSinAutorizacion)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            objRes = castEntidadNegocioLargo(item);
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
    public List<AuNoSolicitud> obtenerIdSolicitudesItem(List<AuNoSolicitud> listaNegocio) throws Exception {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection conn = null;
        String strQuery;
        try {
            for(AuNoSolicitud noSolicitud : listaNegocio){
                //Obtener id de detalles
                strQuery = "SELECT ans.id, "
                        + "ans.au_no_solicitudes_id, "
                        + "ans.tipo_tecnologia, "
                        + "ans.ma_tecnologia_id "
                        + "FROM au_no_solicitud_items ans "
                        + "WHERE ans.au_no_solicitudes_id = " + noSolicitud.getId() + " "
                        + "ORDER BY ans.id ASC ";
                conn = getConnection();
                ps = conn.prepareStatement(strQuery);
                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int solicitudId = resultSet.getInt("au_no_solicitudes_id");
                    int tipoTecnologia = resultSet.getInt("tipo_tecnologia");
                    int maTecnologiaId = resultSet.getInt("ma_tecnologia_id");
                    if (noSolicitud.getId() != null) {
                        for(AuNoSolicitudItem item: noSolicitud.getListAuNoSolicitudItem()){
                            if (item.getTipoTecnologia() == tipoTecnologia
                                && item.getMaTecnologiaId() == maTecnologiaId) {
                                item.setId(resultSet.getInt("id"));
                                break;
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error consultando no solicitud Item: " + ex.toString());
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

    public static AuNoSolicitudItem castEntidadNegocioCorto(AuNoSolicitudItems ent) {
        AuNoSolicitudItem obj = new AuNoSolicitudItem();
        obj.setId(ent.getId());
        if (ent.getAuNoSolicitudesId() != null) {
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        obj.setEstado(ent.getEstado());
        obj.setEstadoJustificacion(ent.getEstadoJustificacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setMaMedicamentosId(ent.getMaMedicamentosId());
        obj.setMaMedicamentosCodigo(ent.getMaMedicamentosCodigo());
        obj.setMaMedicamentosValor(ent.getMaMedicamentosValor());
        obj.setCantidadSolicitada(ent.getCantidadSolicitada());
        obj.setMaServicioHabilitacionId(ent.getMaServicioHabilitacionId());
        obj.setMaServicioHabilitacionCodigo(ent.getMaServicioHabilitacionCodigo());
        obj.setMaServicioHabilitacionValor(ent.getMaServicioHabilitacionValor());
        obj.setDosis(ent.getDosis());
        obj.setFrecuencia(ent.getFrecuencia());
        obj.setPosologia(ent.getPosologia());
        obj.setMaeViaAdministracionId(ent.getMaeViaAdministracionId());
        obj.setMaeViaAdministracionCodigo(ent.getMaeViaAdministracionCodigo());
        obj.setMaeViaAdministracionValor(ent.getMaeViaAdministracionValor());
        obj.setMaeViaAdministracionTipo(ent.getMaeViaAdministracionTipo());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitudItem castEntidadNegocioLargo(AuNoSolicitudItems ent) {
        AuNoSolicitudItem obj = new AuNoSolicitudItem();
        obj.setId(ent.getId());
        if (ent.getAuNoSolicitudesId() != null) {
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        if(ent.getCntContratoDetallesId() != null && ent.getCntContratoDetallesId() > 0 ){
            CntContratoDetalle contratoDetalle = new CntContratoDetalle();
            contratoDetalle.setId(ent.getCntContratoDetallesId());
            obj.setCntContratoDetallesId(contratoDetalle);
        }
        obj.setEstado(ent.getEstado());
        obj.setEstadoJustificacion(ent.getEstadoJustificacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setMaMedicamentosId(ent.getMaMedicamentosId());
        obj.setMaMedicamentosCodigo(ent.getMaMedicamentosCodigo());
        obj.setMaMedicamentosValor(ent.getMaMedicamentosValor());
        obj.setCantidadSolicitada(ent.getCantidadSolicitada());
        obj.setMaServicioHabilitacionId(ent.getMaServicioHabilitacionId());
        obj.setMaServicioHabilitacionCodigo(ent.getMaServicioHabilitacionCodigo());
        obj.setMaServicioHabilitacionValor(ent.getMaServicioHabilitacionValor());
        obj.setDosis(ent.getDosis());
        obj.setFrecuencia(ent.getFrecuencia());
        obj.setPosologia(ent.getPosologia());
        obj.setMaeViaAdministracionId(ent.getMaeViaAdministracionId());
        obj.setMaeViaAdministracionCodigo(ent.getMaeViaAdministracionCodigo());
        obj.setMaeViaAdministracionValor(ent.getMaeViaAdministracionValor());
        obj.setMaeViaAdministracionTipo(ent.getMaeViaAdministracionTipo());
        obj.setTipoEntrega(ent.getTipoEntrega());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setNumEntregas(ent.getNumEntregas());
        obj.setPbs(ent.getPbs());
        obj.setValorUnitario(ent.getValorUnitario());
        obj.setValorTotal(ent.getValorTotal());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

    public static AuNoSolicitudItem castEntidadNegocioLargo(AuNoSolicitudItems ent, int posicion) {
        AuNoSolicitudItem obj = new AuNoSolicitudItem();
        obj.setId(ent.getId());
        obj.setPosicion(posicion);
        if (ent.getAuNoSolicitudesId() != null) {
            AuNoSolicitud prestador = new AuNoSolicitud();
            prestador.setId(ent.getAuNoSolicitudesId().getId());
            obj.setAuNoSolicitudesId(prestador);
        }
        if(ent.getCntContratoDetallesId() != null && ent.getCntContratoDetallesId() > 0 ){
            CntContratoDetalle contratoDetalle = new CntContratoDetalle();
            contratoDetalle.setId(ent.getCntContratoDetallesId());
            obj.setCntContratoDetallesId(contratoDetalle);
        }
        obj.setEstado(ent.getEstado());
        obj.setEstadoJustificacion(ent.getEstadoJustificacion());
        obj.setTipoTecnologia(ent.getTipoTecnologia());
        obj.setMaTecnologiaId(ent.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(ent.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(ent.getMaTecnologiaValor());
        obj.setMaMedicamentosId(ent.getMaMedicamentosId());
        obj.setMaMedicamentosCodigo(ent.getMaMedicamentosCodigo());
        obj.setMaMedicamentosValor(ent.getMaMedicamentosValor());
        obj.setCantidadSolicitada(ent.getCantidadSolicitada());
        obj.setMaServicioHabilitacionId(ent.getMaServicioHabilitacionId());
        obj.setMaServicioHabilitacionCodigo(ent.getMaServicioHabilitacionCodigo());
        obj.setMaServicioHabilitacionValor(ent.getMaServicioHabilitacionValor());
        obj.setDosis(ent.getDosis());
        obj.setFrecuencia(ent.getFrecuencia());
        obj.setPosologia(ent.getPosologia());
        obj.setMaeViaAdministracionId(ent.getMaeViaAdministracionId());
        obj.setMaeViaAdministracionCodigo(ent.getMaeViaAdministracionCodigo());
        obj.setMaeViaAdministracionValor(ent.getMaeViaAdministracionValor());
        obj.setMaeViaAdministracionTipo(ent.getMaeViaAdministracionTipo());
        obj.setTipoEntrega(ent.getTipoEntrega());
        obj.setDuracionTratamiento(ent.getDuracionTratamiento());
        obj.setNumEntregas(ent.getNumEntregas());
        obj.setPbs(ent.getPbs());
        obj.setValorUnitario(ent.getValorUnitario());
        obj.setValorTotal(ent.getValorTotal());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

    public static AuNoSolicitudItems castNegocioEntidad(AuNoSolicitudItem obj) {
        AuNoSolicitudItems ent = new AuNoSolicitudItems();
        ent.setId(ent.getId());
        if (obj.getAuNoSolicitudesId() != null) {
            AuNoSolicitudes auNoSolicitudes = new AuNoSolicitudes();
            auNoSolicitudes.setId(obj.getAuNoSolicitudesId().getId());
            ent.setAuNoSolicitudesId(auNoSolicitudes);
        }
        if (obj.getCntContratoDetallesId() != null) {
            ent.setCntContratoDetallesId(obj.getCntContratoDetallesId().getId());
        }
        ent.setEstado(obj.getEstado());
        ent.setEstadoJustificacion(obj.getEstadoJustificacion());
        ent.setTipoTecnologia(obj.getTipoTecnologia());
        ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        ent.setMaMedicamentosId(obj.getMaMedicamentosId());
        ent.setMaMedicamentosCodigo(obj.getMaMedicamentosCodigo());
        ent.setMaMedicamentosValor(obj.getMaMedicamentosValor());
        ent.setCantidadSolicitada(obj.getCantidadSolicitada());
        ent.setMaServicioHabilitacionId(obj.getMaServicioHabilitacionId());
        ent.setMaServicioHabilitacionCodigo(obj.getMaServicioHabilitacionCodigo());
        ent.setMaServicioHabilitacionValor(obj.getMaServicioHabilitacionValor());
        ent.setDosis(obj.getDosis());
        ent.setFrecuencia(obj.getFrecuencia());
        ent.setPosologia(obj.getPosologia());
        ent.setMaeViaAdministracionId(obj.getMaeViaAdministracionId());
        ent.setMaeViaAdministracionCodigo(obj.getMaeViaAdministracionCodigo());
        ent.setMaeViaAdministracionValor(obj.getMaeViaAdministracionValor());
        ent.setMaeViaAdministracionTipo(obj.getMaeViaAdministracionTipo());
        ent.setTipoEntrega(obj.getTipoEntrega());
        ent.setDuracionTratamiento(obj.getDuracionTratamiento());
        ent.setNumEntregas(obj.getNumEntregas());
        ent.setPbs(obj.isPbs());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setValorTotal(obj.getValorTotal());
        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
