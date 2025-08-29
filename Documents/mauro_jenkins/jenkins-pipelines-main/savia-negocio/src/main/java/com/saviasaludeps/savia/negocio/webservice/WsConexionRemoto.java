/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservice;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface WsConexionRemoto {
    
//    /**
//     * Método para consultar todos los registros
//     * @return 
//     * @throws java.lang.Exception 
//     */
//    List<WsConexion> consultarTodos() throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<WsConexion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (WsConexiones) cargado
     * @throws java.lang.Exception
     */
    WsConexion consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (WsConexiones)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(WsConexion obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (WsConexiones)
     * @throws java.lang.Exception
     */
    void actualizar(WsConexion obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (WsConexiones) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    WsConexion eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las WsConexiones
     * @return
     * @throws Exception 
     */
    List<WsConexion> consultarTodas() throws Exception;
    
}
