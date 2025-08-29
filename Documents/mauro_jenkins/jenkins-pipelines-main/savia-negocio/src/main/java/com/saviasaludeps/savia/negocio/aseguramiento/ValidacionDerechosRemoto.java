/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegValidacionDerecho;
import java.util.List;

public interface ValidacionDerechosRemoto {
    
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
    List<AsegValidacionDerecho> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegValidacionDerecho) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegValidacionDerecho consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegValidacionDerecho) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegValidacionDerecho obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegValidacionDerecho)
     * @throws java.lang.Exception
     */
    void actualizar(AsegValidacionDerecho obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegValidacionDerecho) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegValidacionDerecho eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegValidacionDerecho
     * @return
     * @throws Exception 
     */
    List<AsegValidacionDerecho> consultarTodos() throws Exception;
        
    /**
     * Realizar conteo de registros por Estado
     * @param estado
     * @return
     * @throws Exception 
     */
    int consultarValidacionDerechosPorEstado(int estado) throws Exception;

}
