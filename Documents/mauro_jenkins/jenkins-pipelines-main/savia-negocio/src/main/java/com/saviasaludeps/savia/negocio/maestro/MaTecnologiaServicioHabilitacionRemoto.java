/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface MaTecnologiaServicioHabilitacionRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<MaTecnologiaServicioHabilitacion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaTecnologiaServicioHabilitacion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaTecnologiaServicioHabilitacion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaTecnologiaServicioHabilitacion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaTecnologiaServicioHabilitacion eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaTecnologiaServicioHabilitacion> consultarTodos() throws Exception;
    
    /**
     * Consultar todos los registros relacionados a una tecnologia
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<MaTecnologiaServicioHabilitacion> consultarPorTecnologia(int id) throws Exception;
    
    /**
     * consulta un registro por el id de Tecnologia y el id de Servicio de Habilitacion
     * @param idTecnologia
     * @param idServicio
     * @return
     * @throws Exception 
     */
    MaTecnologiaServicioHabilitacion consultarPorTecnologiaYServicio(int idTecnologia, int idServicio) throws Exception;
    
    /**
     * consulta un registro por el id de Tecnologia y el codigo de Servicio de Habilitacion
     * @param idTecnologia
     * @param codigoServicio
     * @return
     * @throws Exception 
     */
    MaTecnologiaServicioHabilitacion consultarPorTecnologiaYCodigoServicio(int idTecnologia, String codigoServicio) throws Exception;
    
    /**
     * consulta la cantidad de asociados a un servicio de habilitacion.
     * @param idServicio
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorServicioHabilitacion(int idServicio) throws Exception;

    }
