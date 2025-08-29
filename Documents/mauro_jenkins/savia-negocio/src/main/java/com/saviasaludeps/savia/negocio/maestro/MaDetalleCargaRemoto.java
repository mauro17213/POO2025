/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDetalleCarga;
import java.util.List;

public interface MaDetalleCargaRemoto {
    
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
    List<MaDetalleCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (MaDetalleCarga) objeto consultadp
     * @throws java.lang.Exception
     */
    MaDetalleCarga consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (MaDetalleCarga) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaDetalleCarga obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (MaDetalleCarga)
     * @throws java.lang.Exception
     */
    void actualizar(MaDetalleCarga obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (MaDetalleCarga) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaDetalleCarga eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las MaDetalleCarga
     * @return
     * @throws Exception 
     */
    List<MaDetalleCarga> consultarTodos() throws Exception;
    
    /**
     * Consultar todas las MaDetalleCarga
     * @param radicado
     * @return
     * @throws Exception 
     */
    List<MaDetalleCarga> consultarPorRadicado(int radicado) throws Exception;
    
}
