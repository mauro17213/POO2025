/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.utilidades;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author ripalacios
 */
public class GenericoServicio {

    protected static final String CONSULTAR = "CONSULTAR";
    protected static final String CONSULTAR_TODOS = "CONSULTAR_TODOS";
    protected static final String INSERTAR = "INSERTAR";
    protected static final String ACTUALIZAR = "ACTUALIZAR";
    protected static final String ELIMINAR = "ELIMINAR";
    protected static final String REPORTE = "REPORTE";

    @PersistenceContext(unitName = "mysqlConnection")
    protected EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    protected void cerrarEntityManager() {
        if (em != null && em.isOpen()) {
            em.flush();
        }
//        if (em != null && em.isOpen()) {
//            em.close();
//        }
    }

////    public Connection getConnection() throws SQLException {
////        Session session = em.unwrap(Session.class);
////        SessionImplementor sessionImplementor = (SessionImplementor) session;
////        return sessionImplementor.getJdbcConnectionAccess().obtainConnection();
////    }
//    public Connection getConnection() throws Exception{
//        Connection dbConnection = null;
//        try {
//            DataSource data = (DataSource) getEntityManager().getEntityManagerFactory().getProperties().get( "javax.persistence.jpaDataSource" );
//            dbConnection = (Connection) data.getConnection();
//        } catch (SQLException e) {
//             Exception("Error obtener conexion sql de GenericoServicio : ", e);
//        }
//    
//      return dbConnection;
//   }
    protected void Exception(String mensaje, String... values) throws Exception {
        throw new Exception(getMessageException("", new Exception(mensaje), values));
    }

    protected void Exception(String key, Exception ex, String... values) throws Exception {
        throw new Exception(getMessageException(key, ex, values));
    }

    private String getMessageException(String key, Exception e, String... values) {
        String _mensaje;
        switch (key) {
            case CONSULTAR_TODOS:
                _mensaje = "Error al consultar una lista de registros";
                break;
            case CONSULTAR:
                _mensaje = "Error al consultar un registro";
                break;
            case INSERTAR:
                _mensaje = "Error al insertar un registro";
                break;
            case ACTUALIZAR:
                _mensaje = "Error al editar un registro";
                break;
            case ELIMINAR:
                _mensaje = "Error al eliminar un registro";
                break;
            default:
                _mensaje = "Error " + key;
                break;
        }
        _mensaje += " (" + e.getMessage() + ")";
        for (String value : values) {
            _mensaje += "\n - " + value;
        }
        return _mensaje;
    }

    public static Calendar addHourToParam1(Object obj) {
        Date date = (Date) obj;
        Calendar calIni = Calendar.getInstance();
        calIni.setTime(date);
        calIni.set(Calendar.HOUR, 00);
        calIni.set(Calendar.MINUTE, 00);
        return calIni;
    }

    public static Calendar addHourToParam2(Object obj) {
        Date date = (Date) obj;
        Calendar calFin = Calendar.getInstance();
        calFin.setTime(date);
        calFin.set(Calendar.HOUR, 23);
        calFin.set(Calendar.MINUTE, 29);
        return calFin;
    }
}
