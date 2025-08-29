/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1Adjunto;
import java.util.List;

public interface Anexo1AdjuntoRemoto {
    
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
    List<AsegAnexo1Adjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegAnexo1Adjunto) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegAnexo1Adjunto consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegAnexo1Adjunto) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAnexo1Adjunto obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegAnexo1Adjunto)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAnexo1Adjunto obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegAnexo1Adjunto) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAnexo1Adjunto eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegAnexo1Adjunto
     * @return
     * @throws Exception 
     */
    List<AsegAnexo1Adjunto> consultarTodos() throws Exception;
    
}
