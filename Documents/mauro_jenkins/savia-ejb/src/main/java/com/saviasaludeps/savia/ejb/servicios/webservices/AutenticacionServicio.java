/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservices;

import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.dominio.webservice.WsToken;
import com.saviasaludeps.savia.dominio.webservice.WsTransaccion;
import com.saviasaludeps.savia.ejb.entidades.WsConexiones;
import com.saviasaludeps.savia.ejb.entidades.WsServicios;
import com.saviasaludeps.savia.ejb.entidades.WsTokens;
import com.saviasaludeps.savia.ejb.entidades.WsTransacciones;
import com.saviasaludeps.savia.ejb.utilidades.Encrypt;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservices.AutenticacionRemoto;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(AutenticacionRemoto.class)
public class AutenticacionServicio extends GenericoServicio implements AutenticacionRemoto {
    
    @Override
    public WsConexion validarConexion(String usuario, String contrasena, Integer idServicio) throws Exception {
        WsConexion conexion = null;
        try {
            String sql = "FROM WsConexionesServicios AS cose "
                    + "INNER JOIN cose.wsConexionesId AS co "
                    + "INNER JOIN cose.wsServiciosId AS se "
                    + "LEFT JOIN se.wsExternoServiciosId AS exse "
                    + "LEFT JOIN exse.wsExternoUsuariosId AS exus "
                    + "WHERE co.usuario = :usuario "
                    + "AND co.contrasena = :contrasena "
                    + "AND se.id = :servicio_id ";
            Query query = getEntityManager().createQuery(sql)
                    .setParameter("usuario", usuario)
                    .setParameter("contrasena", Encrypt.sha512(contrasena))
                    .setParameter("servicio_id", idServicio);
            List<Object> list = query.getResultList();
            if (list != null && list.size() > 0) {
                WsConexiones conexiones = (WsConexiones) ((Object[]) list.get(0))[1];
                WsServicios servicios = (WsServicios) ((Object[]) list.get(0))[2];
                //Cargar Servicio
                WsServicio servicio = new WsServicio(
                        servicios.getId(),
                        servicios.getNombre(),
                        servicios.getDescripcion(),
                        servicios.getTiempoCont(),
                        servicios.getActivo()
                );
                //Cargar Conexion
                conexion = new WsConexion(
                        conexiones.getId(),
                        conexiones.getNombre(),
                        conexiones.getTipoAutenticacion(),
                        conexiones.getUsuario(),
                        conexiones.getContrasena(),
                        conexiones.getLlave(),
                        conexiones.getIp(),
                        conexiones.getActivo(),
                        servicio
                );
            }
        } catch (NoResultException e) {
            Exception("Usuario y/o contraseña invalido para este servicio", e);
        } catch (Exception e) {
            Exception("Usuario y/o contraseña invalido para este servicio", e);
        } finally {
            cerrarEntityManager();
        }
        return conexion;
    }

    @Override
    public void insertarToken(Integer idConexion, Integer idServcicio, String token, int tiempo) throws Exception {
        try {

        } catch (NoResultException e) {
        } catch (Exception e) {
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public WsServicio consultarServicio(Integer id) throws Exception {
        WsServicio servicio = null;
        try {
            WsServicios servicios = (WsServicios) getEntityManager().find(WsServicios.class, id);
            //Cargar Servicio
            servicio = new WsServicio(
                    servicios.getId(),
                    servicios.getNombre(),
                    servicios.getDescripcion(),
                    servicios.getTiempoCont(),
                    servicios.getActivo()
            );
        } catch (NoResultException e) {
            servicio = null;
        } catch (Exception e) {
            servicio = null;
        } finally {
            cerrarEntityManager();
        }
        return servicio;
    }

    @Override
    public WsConexion consultaConexion(String token, Integer idServicioInterno) throws Exception {
        WsConexion conexion = null;
        try {
            //Consulta Token
            String sql1 = "FROM WsTokens "
                    + "WHERE token = '" + token + "' ";
            Query query1 = getEntityManager().createQuery(sql1);
//                    .setParameter("token", 9);
            List<Object> listToken = query1.getResultList();
            if(listToken.isEmpty()){
                throw new Exception("Token inválido");
            }
            WsTokens token1 = (WsTokens) listToken.get(0);
//            WsTokens token1 = (WsTokens) query1.getSingleResult();
            if (token1.getFechaHoraFin().before(new Date())) {
                throw new Exception("Token inválido");
            }
            String sql = "FROM WsConexionesServicios AS cose "//0
                    + "INNER JOIN cose.wsConexionesId AS co "//1
                    + "INNER JOIN cose.wsServiciosId AS se "//2
                    + "WHERE se.id = :servicio_interno_id "
                    + "AND co.id = :conexion_id "
                    + "AND co.activo = true "
                    + "AND se.activo = true ";
            Query query = getEntityManager().createQuery(sql)
                    .setParameter("conexion_id", token1.getWsConexionesId().getId())
                    .setParameter("servicio_interno_id", idServicioInterno);
            List<Object> list = query.getResultList();
            if (list != null && list.size() > 0) {
                WsConexiones conexiones = (WsConexiones) ((Object[]) list.get(0))[1];
                WsServicios servicios = (WsServicios) ((Object[]) list.get(0))[2];
                //Cargar Servicio
                WsServicio servicio = new WsServicio(
                        servicios.getId(),
                        servicios.getNombre(),
                        servicios.getDescripcion(),
                        servicios.getTiempoCont(),
                        servicios.getActivo()
                );
                //Cargar Conexion
                conexion = new WsConexion(
                        conexiones.getId(),
                        conexiones.getNombre(),
                        conexiones.getTipoAutenticacion(),
                        conexiones.getUsuario(),
                        conexiones.getContrasena(),
                        conexiones.getLlave(),
                        conexiones.getIp(),
                        conexiones.getActivo(),
                        servicio
                );
            }
        } catch (NoResultException e) {
            Exception("Token inválido", e);
        } catch (Exception e) {
            Exception("Token inválido", e);
        } finally {
            cerrarEntityManager();
        }
        return conexion;
    }

    @Override
    public int insertarTransaccion(WsTransaccion obj) throws Exception {
        int id = 0;
        try {
            WsTransacciones per = new WsTransacciones();
            per.setWsServiciosId(new WsServicios(obj.getWsServicio().getId()));
            per.setWsConexionesId(new WsConexiones(obj.getWsConexion().getId()));
            per.setEstado(obj.getEstado());
            per.setIpSolicitud(obj.getIpSolicitud());
            per.setUsuario(obj.getUsuario());
            per.setToken(obj.getToken());
            per.setFechaHoraSolicitud(obj.getFechaHoraSolicitud());
            per.setFechaHoraRespuesta(obj.getFechaHoraRespuesta());
            per.setContingencia(obj.getContingencia());
//            per.setJsonSolicitud(obj.getJsonSolicitud());
//            per.setJsonRespuesta(obj.getJsonRespuesta());
            id = (int) getEntityManager().merge(per).getId();
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
    public void actualizarTransaccion(WsTransaccion obj) throws Exception {
        try {
            WsTransacciones per = new WsTransacciones();
            per.setId(obj.getId());
            per.setWsServiciosId(new WsServicios(obj.getWsServicio().getId()));
            per.setWsConexionesId(new WsConexiones(obj.getWsConexion().getId()));
            per.setEstado(obj.getEstado());
            per.setIpSolicitud(obj.getIpSolicitud());
            per.setUsuario(obj.getUsuario());
            per.setToken(obj.getToken());
            per.setFechaHoraSolicitud(obj.getFechaHoraSolicitud());
            per.setFechaHoraRespuesta(obj.getFechaHoraRespuesta());
            per.setContingencia(obj.getContingencia());
//            per.setJsonSolicitud(obj.getJsonSolicitud());
//            per.setJsonRespuesta(obj.getJsonRespuesta());
            getEntityManager().merge(per);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public WsToken generarToken(String usuario, String contrasena, String llave, String ip, int tiempo) throws Exception {
        WsToken token = null;
        try {
            //Consultar conexión
            String sql1 = "FROM WsConexiones "
                    + "WHERE usuario = :usuario "
                    + "AND contrasena = :contrasena";
            String pass = Encrypt.sha512(contrasena);
            Query query1 = getEntityManager().createQuery(sql1)
                    .setParameter("usuario", usuario)
                    .setParameter("contrasena", Encrypt.sha512(contrasena));
            WsConexiones conexiones = (WsConexiones) query1.getSingleResult();
//            WsConexiones conexiones = (WsConexiones) getEntityManager().find(WsConexiones.class, idConexion);
            if (conexiones != null && conexiones.getActivo()) {
                //Cosultar último token
                String sql2 = "FROM WsTokens "
                        + "WHERE wsConexionesId.id = :idConexion "
                        + "ORDER BY id DESC ";
                Query query2 = getEntityManager().createQuery(sql2)
                        .setParameter("idConexion", conexiones.getId())
                        .setMaxResults(1);
                List<Object> list = query2.getResultList();
                WsTokens tt = null;
                if (list != null && list.size() > 0) {
                    tt = (WsTokens) list.get(0);
                    if (tt.getFechaHoraFin().before(new Date())) {
                        tt = null;
                    }
                }
                if (tt == null) {
                    WsTokens wsTokens = new WsTokens();
                    wsTokens.setWsConexionesId(new WsConexiones(conexiones.getId()));
                    Calendar cal = Calendar.getInstance();
                    String clave
                            = usuario
                            + String.valueOf(cal.get(Calendar.YEAR))
                            + String.valueOf(cal.get(Calendar.MONTH))
                            + String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
                            + String.valueOf(cal.get(Calendar.HOUR_OF_DAY))
                            + String.valueOf(cal.get(Calendar.MINUTE))
                            + String.valueOf(cal.get(Calendar.SECOND))
                            + String.valueOf(cal.get(Calendar.MILLISECOND));
                    String encriptada = Encrypt.sha512(clave);
                    wsTokens.setToken(encriptada);
                    Calendar calInicio = Calendar.getInstance();
                    wsTokens.setFechaHoraInicio(calInicio.getTime());
                    wsTokens.setTiempo(tiempo);
                    Calendar calFin = (Calendar) calInicio.clone();
                    calFin.add(Calendar.SECOND, tiempo);
                    wsTokens.setFechaHoraFin(calFin.getTime());
                    wsTokens.setIpSolicita(ip);
                    wsTokens.setTransacciones(0);
                    wsTokens.setId(getEntityManager().merge(wsTokens).getId());
                    token = new WsToken(
                            wsTokens.getId(),
                            wsTokens.getToken(),
                            wsTokens.getFechaHoraInicio(),
                            wsTokens.getTiempo(),
                            wsTokens.getFechaHoraFin(),
                            wsTokens.getTransacciones(),
                            new WsConexion(conexiones.getId())
                    );
                } else {
                    long milliseconds = tt.getFechaHoraFin().getTime() - new Date().getTime();
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(milliseconds);
                    token = new WsToken(
                            tt.getId(),
                            tt.getToken(),
                            tt.getFechaHoraInicio(),
                            seconds,
                            tt.getFechaHoraFin(),
                            tt.getTransacciones(),
                            new WsConexion(conexiones.getId())
                    );
                }

            }
        } catch (NoResultException e) {
            token = null;
        } catch (Exception e) {
            token = null;
        } finally {
            cerrarEntityManager();
        }
        return token;
    }

}
