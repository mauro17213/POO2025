/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MaServicioHabilitacionRemoto {
    
    /**
     * Consulta todos los registros de la tabla
     * @return
     * @throws Exception 
     */
    List<MaServicioHabilitacion> consultarTodos() throws Exception;
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
    List<MaServicioHabilitacion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<MaServicioHabilitacion> consultarListaTodo(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaServicioHabilitacion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaServicioHabilitacion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaServicioHabilitacion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaServicioHabilitacion eliminar(int id) throws Exception;
    
    /**
     * Método para consultar por Código
     * @param codigo
     * @return
     * @throws Exception 
     */
    public MaServicioHabilitacion consultarPorCodigo(int codigo) throws Exception;
    
    /**
     * Obtener los servicios de habilitación en un hash identificados por código
     * 
     * @return
     * @throws Exception
     */
    HashMap<String, MaServicioHabilitacion> consultarHash() throws Exception;
    
    /**
     * Consulta todo para singleton
     * @return
     * @throws Exception 
     */
    List<MaServicioHabilitacion> consultarTodoSingleton() throws Exception;
    
}
