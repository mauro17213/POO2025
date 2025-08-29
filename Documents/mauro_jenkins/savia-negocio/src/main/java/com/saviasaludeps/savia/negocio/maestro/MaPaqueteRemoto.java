/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface MaPaqueteRemoto {
    
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
    List<MaPaquete> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaPaquete consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaPaquete obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaPaquete obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaPaquete eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaPaquete> consultarTodos() throws Exception;
    
    /**
     * Consultar un registro por su código
     * @param codigo
     * @return
     * @throws Exception 
     */
    MaPaquete consultarPorCodigo(String codigo) throws Exception;
        /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<MaPaquete> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Obtener los paquetes en un hash identificados por código
     * 
     * @return
     * @throws Exception
     */
    HashMap<String, MaPaquete> consultarHash() throws Exception;
    
    /**
     * Consulta todo para singleton
     * @return
     * @throws Exception 
     */
    List<MaPaquete> consultarTodoSingleton() throws Exception;
}
