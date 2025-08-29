/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegDetalleCarga;
import java.util.List;

public interface DetalleCargaRemoto {
    
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
    List<AsegDetalleCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegDetalleCarga) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegDetalleCarga consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegDetalleCarga) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegDetalleCarga obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegDetalleCarga)
     * @throws java.lang.Exception
     */
    void actualizar(AsegDetalleCarga obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegDetalleCarga) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegDetalleCarga eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegDetalleCarga
     * @return
     * @throws Exception 
     */
    List<AsegDetalleCarga> consultarTodos() throws Exception;
    
    /**
     * Consultar todas las AsegDetalleCarga
     * @return
     * @throws Exception 
     */
    List<AsegDetalleCarga> consultarPorRadicado(int radicado) throws Exception;
    
}
