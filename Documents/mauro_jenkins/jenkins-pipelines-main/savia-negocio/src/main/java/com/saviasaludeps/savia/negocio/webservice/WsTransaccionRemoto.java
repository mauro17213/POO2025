/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservice;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.webservice.WsTransaccion;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface WsTransaccionRemoto {
    
//    /**
//     * Método para consultar todos los registros
//     * @return 
//     * @throws java.lang.Exception 
//     */
//    List<WsTransaccion> consultarTodos() throws Exception;
    
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
    List<WsTransaccion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (WsTransacciones) cargado
     * @throws java.lang.Exception
     */
    WsTransaccion consultar(int id) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (WsTransacciones) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    WsTransaccion eliminar(int id) throws Exception;
    
}
