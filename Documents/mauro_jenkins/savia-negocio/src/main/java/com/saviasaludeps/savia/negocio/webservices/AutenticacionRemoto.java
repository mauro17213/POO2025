/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices;

import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.dominio.webservice.WsToken;
import com.saviasaludeps.savia.dominio.webservice.WsTransaccion;

/**
 *
 * @author rpalacios
 */
public interface AutenticacionRemoto {
    
    /**
     *
     * @param usuario
     * @param contrasena
     * @param idServicio
     * @return
     * @throws Exception
     */
    WsConexion validarConexion(String usuario, String contrasena, Integer idServicio) throws Exception;

    /**
     * Insertar token
     *
     * @param idConexion
     * @param idServcicio
     * @param token
     * @param tiempo
     * @throws Exception
     */
    void insertarToken(Integer idConexion, Integer idServcicio, String token, int tiempo) throws Exception;

    /**
     * Consultar servicio completo
     *
     * @param id
     * @return
     * @throws Exception
     */
    WsServicio consultarServicio(Integer id) throws Exception;

    /**
     *
     * @param token
     * @param idServicioInterno
     * @return
     * @throws Exception
     */
    WsConexion consultaConexion(String token, Integer idServicioInterno) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarTransaccion(WsTransaccion obj) throws Exception;

    /**
     *
     * @param obj
     * @throws Exception
     */
    void actualizarTransaccion(WsTransaccion obj) throws Exception;

    /**
     *
     * @param usuario
     * @param contrasena
     * @param llave
     * @param ip
     * @param tiempo
     * @return
     * @throws Exception
     */
    WsToken generarToken(String usuario, String contrasena, String llave, String ip, int tiempo) throws Exception;
    
}
