/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1;
import java.util.List;

public interface Anexo1Remoto {
    
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
    List<AsegAnexo1> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegAnexo1) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegAnexo1 consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegAnexo1) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAnexo1 obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegAnexo1)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAnexo1 obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegAnexo1) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAnexo1 eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegAnexo1
     * @return
     * @throws Exception 
     */
    List<AsegAnexo1> consultarTodos() throws Exception;
    
}
