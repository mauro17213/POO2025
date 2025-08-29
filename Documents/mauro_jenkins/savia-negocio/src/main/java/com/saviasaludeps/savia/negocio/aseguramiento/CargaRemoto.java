/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegCarga;
import java.util.List;

public interface CargaRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception 
     */
    List<AsegCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegCarga) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegCarga consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegCarga) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegCarga obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegCarga)
     * @throws java.lang.Exception
     */
    void actualizar(AsegCarga obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegCarga) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegCarga eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegCarga
     * @return
     * @throws Exception 
     */
    List<AsegCarga> consultarTodos() throws Exception;
    
    /**
     * Consulta de cantidad de registros en la tabla aseg_detalle_cargas asociados a un id de aseg_carga y el estado.
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadRegistrosProcesadosPorEstado(int id,int estado) throws Exception;
    
     /**
     * Método para consultar el registro en estado Pendiente que debe ser procesado
     *
     * @param id
     * @return (CntContratoCarga) cargado
     * @throws java.lang.Exception
     */
    AsegCarga consultarSiguienteCarga(int estado) throws Exception;
    
}
