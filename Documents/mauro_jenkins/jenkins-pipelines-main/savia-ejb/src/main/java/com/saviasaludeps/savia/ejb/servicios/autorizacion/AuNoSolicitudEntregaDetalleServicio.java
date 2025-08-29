/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudItem;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaDetalles;
import com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudItems;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuNoSolicitudEntregaDetalleRemoto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@Remote(AuNoSolicitudEntregaDetalleRemoto.class)
public class AuNoSolicitudEntregaDetalleServicio extends GenericoServicio implements AuNoSolicitudEntregaDetalleRemoto {
    
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
    public AuNoSolicitudEntregaDetalle consultar(int id) throws Exception {
        AuNoSolicitudEntregaDetalle objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AuNoSolicitudEntregaDetalles) getEntityManager().find(AuNoSolicitudEntregaDetalles.class, id));
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
    public int insertar(AuNoSolicitudEntregaDetalle obj) throws Exception {
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
    public void actualizar(AuNoSolicitudEntregaDetalle obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudEntregaDetalles SET "      
                    + "cantidadEntregada = :cantidadEntregada, "
                    + "faltantes = :faltantes, "   
                    + "clasificacionEntrega = :clasificacionEntrega, "  
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("cantidadEntregada", obj.getCantidadEntregada());
            query.setParameter("faltantes", obj.getFaltantes());
            query.setParameter("clasificacionEntrega", obj.getClasificacionEntrega());
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
    public void actualizarBorradoLogico(AuNoSolicitudEntregaDetalle obj) throws Exception {
        try {
            String hql = "UPDATE AuNoSolicitudEntregaDetalles SET "
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
    public List<AuNoSolicitudEntregaDetalle> consultarEntregasPorAuNosolicitudesItemId(int idAuNoSolicitudItem) throws Exception {
        List<AuNoSolicitudEntregaDetalle> obj = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM AuNoSolicitudEntregaDetalles u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            strTitulo = agregarJoin("INNER JOIN AuNoSolicitudItems aun ON u.auNoSolicitudItemsId = aun.id ", strTitulo);
            strQuery.append("AND aun.id = ").append(idAuNoSolicitudItem).append(" ");
            strQuery.append("AND u.borrado = 0");
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY u.id ASC");
           
            List<AuNoSolicitudEntregaDetalles> list = getEntityManager().createQuery(sql.toString()).getResultList();
            int posicion = 0;
            for (AuNoSolicitudEntregaDetalles diagnostico : list) {
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
    public AuNoSolicitudEntregaDetalle consultarPorIdAuNoSolicitudItemPorNumeroEntrega(int idAuNoSolicitudItem, Integer numeroEntrega) throws Exception {
        AuNoSolicitudEntregaDetalle objRes = null;
        try {
            String strQuery = "SELECT p FROM AuNoSolicitudEntregaDetalles p "
                    + "INNER JOIN AuNoSolicitudItems ausi ON p.auNoSolicitudItemsId = ausi.id "
                    + "WHERE ausi.id =:idAuNoSolicitudItem "
                    + "AND p.numeroEntrega =:numeroEntrega";

            AuNoSolicitudEntregaDetalles item = (AuNoSolicitudEntregaDetalles) getEntityManager().createQuery(strQuery)
                    .setParameter("idAuNoSolicitudItem", idAuNoSolicitudItem)
                    .setParameter("numeroEntrega", numeroEntrega)
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
    public void insertarEntregaDetalles(List<AuNoSolicitud> listaNegocio) throws ClassNotFoundException, SQLException {
        int contadorInserts = 0;
        int cantidadLista = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder stringBuilder = new StringBuilder(10000000);
        String columnas = "au_no_solicitud_items_id,";
        columnas += "numero_entrega,";
        columnas += "catidad_total,";
        columnas += "cantidad_entregada,";
        columnas += "faltantes,";
        columnas += "fecha_inicio,";
        columnas += "fecha_fin,";
        columnas += "borrado,";
        columnas += "usuario_crea,";
        columnas += "terminal_crea,";
        columnas += "fecha_hora_crea";
        String query = "INSERT INTO au_no_solicitud_entrega_detalles (" + columnas + ") VALUES ";
        stringBuilder.append(query);
        for(AuNoSolicitud auNoSolicitud : listaNegocio){
            for(AuNoSolicitudItem item : auNoSolicitud.getListAuNoSolicitudItem()){
                cantidadLista = cantidadLista + item.getNumEntregas();
            }
        }
        int sumatorioCantidadTotal = 0;
        for (AuNoSolicitud auNoSolicitud : listaNegocio) {
            for (AuNoSolicitudItem item : auNoSolicitud.getListAuNoSolicitudItem()) {
                int cantidadEntregas = item.getCantidadSolicitada() / item.getNumEntregas();
                int numEntregas = 0;
                Date fechaTemporal = null;
                sumatorioCantidadTotal = 0;
                for(int i = 0; i < item.getNumEntregas(); i++){
                    contadorInserts++;
                    sumatorioCantidadTotal = sumatorioCantidadTotal + cantidadEntregas;
                    numEntregas = numEntregas + 1;
                    stringBuilder.append("(");
                    stringBuilder.append("").append(item.getId()).append(",");
                    stringBuilder.append("").append(numEntregas).append(",");
                    if(numEntregas == item.getNumEntregas()){
                        if(item.getCantidadSolicitada() != sumatorioCantidadTotal){
                            int restante = item.getCantidadSolicitada() - sumatorioCantidadTotal;
                            stringBuilder.append("").append(cantidadEntregas + restante).append(",");
                        }else{
                            stringBuilder.append("").append(cantidadEntregas).append(",");
                        }
                    }else{
                        stringBuilder.append("").append(cantidadEntregas).append(",");
                    }
                    stringBuilder.append("").append(0).append(",");
                    stringBuilder.append("").append(0).append(",");
                    int duracion = item.getDuracionTratamiento() / item.getNumEntregas();
                    if(i == 0){
                        Date FechaInicio = new Date();
                        stringBuilder.append("'").append(sdf.format(FechaInicio)).append("',");
                        fechaTemporal = sumarDiasAFecha(FechaInicio, duracion);
                        stringBuilder.append("'").append(sdf.format(fechaTemporal)).append("',");
                    }else{
                        stringBuilder.append("'").append(sdf.format(fechaTemporal)).append("',");
                        fechaTemporal = sumarDiasAFecha(fechaTemporal, duracion);
                        stringBuilder.append("'").append(sdf.format(fechaTemporal)).append("',");
                    }
                    stringBuilder.append("b'").append("0").append("',");
                    stringBuilder.append("'").append(item.getUsuarioCrea()).append("',");
                    stringBuilder.append("'").append(item.getTerminalCrea()).append("',");
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
                            Logger.getLogger(AuNoSolicitudEntregaDetalle.class.getName()).log(Level.SEVERE, null, ex);
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
                                Logger.getLogger(AuNoSolicitudEntregaDetalle.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static Date sumarDiasAFecha(Date fecha, int dias){
        if (dias == 0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        return calendar.getTime(); 
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
    
    public static AuNoSolicitudEntregaDetalle castEntidadNegocioCorto(AuNoSolicitudEntregaDetalles ent) {
        AuNoSolicitudEntregaDetalle obj = new AuNoSolicitudEntregaDetalle();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudItemsId() != null){
            AuNoSolicitudItem auNoSolicitudItem = new AuNoSolicitudItem();
            auNoSolicitudItem.setId(ent.getAuNoSolicitudItemsId().getId());
            obj.setAuNoSolicitudItemsId(auNoSolicitudItem);
        }
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setCatidadTotal(ent.getCatidadTotal());
        obj.setCantidadEntregada(ent.getCantidadEntregada());
        obj.setFaltantes(ent.getFaltantes());
        obj.setFechaInicio(ent.getFechaInicio());
        obj.setFechaFin(ent.getFechaFin());
        obj.setClasificacionEntrega(ent.getClasificacionEntrega());
        obj.setBorrado(ent.getBorrado());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static AuNoSolicitudEntregaDetalle castEntidadNegocioLargo(AuNoSolicitudEntregaDetalles ent) {
        AuNoSolicitudEntregaDetalle obj = new AuNoSolicitudEntregaDetalle();
        obj.setId(ent.getId());
        if(ent.getAuNoSolicitudItemsId() != null){
            AuNoSolicitudItem auNoSolicitudItem = new AuNoSolicitudItem();
            auNoSolicitudItem.setId(ent.getAuNoSolicitudItemsId().getId());
            obj.setAuNoSolicitudItemsId(auNoSolicitudItem);
        }
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setCatidadTotal(ent.getCatidadTotal());
        obj.setCantidadEntregada(ent.getCantidadEntregada());
        obj.setFaltantes(ent.getFaltantes());
        obj.setFechaInicio(ent.getFechaInicio());
        obj.setFechaFin(ent.getFechaFin());
        obj.setClasificacionEntrega(ent.getClasificacionEntrega());
        obj.setBorrado(ent.getBorrado());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static AuNoSolicitudEntregaDetalle castEntidadNegocioLargo(AuNoSolicitudEntregaDetalles ent, int posicion) {
        AuNoSolicitudEntregaDetalle obj = new AuNoSolicitudEntregaDetalle();
        obj.setId(ent.getId());
        obj.setPosicion(posicion);
        if(ent.getAuNoSolicitudItemsId() != null){
            AuNoSolicitudItem auNoSolicitudItem = new AuNoSolicitudItem();
            auNoSolicitudItem.setId(ent.getAuNoSolicitudItemsId().getId());
            obj.setAuNoSolicitudItemsId(auNoSolicitudItem);
        }
        obj.setNumeroEntrega(ent.getNumeroEntrega());
        obj.setCatidadTotal(ent.getCatidadTotal());
        obj.setCantidadEntregada(ent.getCantidadEntregada());
        obj.setFaltantes(ent.getFaltantes());
        obj.setFechaInicio(ent.getFechaInicio());
        obj.setFechaFin(ent.getFechaFin());
        obj.setClasificacionEntrega(ent.getClasificacionEntrega());
        obj.setBorrado(ent.getBorrado());
        //Auditoría
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }
    
    public static AuNoSolicitudEntregaDetalles castNegocioEntidad(AuNoSolicitudEntregaDetalle obj) {
        AuNoSolicitudEntregaDetalles ent = new AuNoSolicitudEntregaDetalles();
        ent.setId(obj.getId());
        if(obj.getAuNoSolicitudItemsId()!= null){
            AuNoSolicitudItems auNoSolicitudesItmes = new AuNoSolicitudItems();
            auNoSolicitudesItmes.setId(obj.getAuNoSolicitudItemsId().getId());
            ent.setAuNoSolicitudItemsId(auNoSolicitudesItmes);
        }
        ent.setNumeroEntrega(obj.getNumeroEntrega());
        ent.setCatidadTotal(obj.getCatidadTotal());
        ent.setCantidadEntregada(obj.getCantidadEntregada());
        ent.setFaltantes(obj.getFaltantes());
        ent.setFechaInicio(obj.getFechaInicio());
        ent.setFechaFin(obj.getFechaFin());
        ent.setClasificacionEntrega(obj.getClasificacionEntrega());
        ent.setBorrado(obj.isBorrado());
        //auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }
}
