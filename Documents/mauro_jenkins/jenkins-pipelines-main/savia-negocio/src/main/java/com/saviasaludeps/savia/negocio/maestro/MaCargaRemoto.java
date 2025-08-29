/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaCarga;
import java.util.List;

public interface MaCargaRemoto {
    
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
    List<MaCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (MaCarga) objeto consultadp
     * @throws java.lang.Exception
     */
    MaCarga consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (MaCarga) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaCarga obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (MaCarga)
     * @throws java.lang.Exception
     */
    void actualizar(MaCarga obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (MaCarga) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaCarga eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las MaCarga
     * @return
     * @throws Exception 
     */
    List<MaCarga> consultarTodos() throws Exception;
    
    /**
     * Consulta de cantidad de registros en la tabla aseg_detalle_cargas asociados a un id de aseg_carga y el estado.
     * 
     * @param id
     * @param estado
     * @return
     * @throws Exception 
     */
    int consultarCantidadRegistrosProcesadosPorEstado(int id,int estado) throws Exception;
    
     /**
     * Método para consultar el registro en estado Pendiente que debe ser procesado
     *
     * @param estado
     * @return (CntContratoCarga) cargado
     * @throws java.lang.Exception
     */
    MaCarga consultarSiguienteCarga(int estado) throws Exception;
    
}
