/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservice;

import com.saviasaludeps.savia.dominio.webservice.WsConexionServicio;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface WsConexionesServiciosRemoto {
    
    /**
     * Método que consulta por el wx_conexiones_id
     * @param ws_conexion_id
     * @return
     * @throws Exception 
     */
    List<WsConexionServicio> getWsConexionServicio(int ws_conexion_id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (WsConexiones)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(WsConexionServicio obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return
     * @throws Exception 
     */
    WsConexionServicio eliminar(int id) throws Exception;
}
