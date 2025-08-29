///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.saviasaludeps.savia.negocio.webservice;
//
//import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
//import com.saviasaludeps.savia.dominio.webservice.WsServicio;
//import java.util.List;
//
///**
// *
// * @author rpalacic
// */
//public interface ServicioRemoto {
//    
////    /**
////     * Método para consultar todos los registros
////     * @return 
////     * @throws java.lang.Exception 
////     */
////    List<WsServicio> consultarTodos() throws Exception;
//    
//    /**
//     * Consulta de cantidad de registros en una lista
//     * @param paramConsulta
//     * @return
//     * @throws Exception 
//     */
//    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
//    
//    /**
//     * Consultar lista de registros
//     * @param paramConsulta
//     * @return
//     * @throws Exception 
//     */
//    List<WsServicio> consultarLista(ParamConsulta paramConsulta) throws Exception;
//    
//    /**
//     * Método para consultar un registro por ID
//     * @param id
//     * @return (WsServicios) cargado
//     * @throws java.lang.Exception
//     */
//    WsServicio consultar(int id) throws Exception;
//    
//    /**
//     * Método para crear una nuevo registro
//     * @param obj (WsServicios)
//     * @return (int) id del registro generado
//     * @throws java.lang.Exception
//     */
//    int insertar(WsServicio obj) throws Exception;
//    
//    /**
//     * Método para actualizar la información de un registro
//     * @param obj (WsServicios)
//     * @throws java.lang.Exception
//     */
//    void actualizar(WsServicio obj) throws Exception;
//    
//    /**
//     * Método para eliminar un registro
//     * @param id
//     * @return (WsServicios) La persistencia del objeto eliminado
//     * @throws java.lang.Exception
//     */
//    WsServicio eliminar(int id) throws Exception;
//    
//    /**
//     * Consultar todas las WsServicios
//     * @return
//     * @throws Exception 
//     */
//    List<WsServicio> consultarTodas() throws Exception;
//    
//    /**
//     * Consultar lista de servicios asignados a una conexión
//     * @param conexionId
//     * @return
//     * @throws Exception 
//     */
//    List<WsServicio> consultarListaPorConexion(int conexionId) throws Exception;
//    
//}
