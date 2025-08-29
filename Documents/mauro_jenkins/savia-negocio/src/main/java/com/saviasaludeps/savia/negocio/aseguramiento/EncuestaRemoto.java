/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegEncuestaPregunta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTabulacionEncuesta;
import java.util.List;

/**
 *
 * @author Jose Perez Hernandez
 */
public interface EncuestaRemoto {
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegTabulacionEncuestas) cargado
     * @throws java.lang.Exception
     */
    AsegTabulacionEncuesta consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegTabulacionEncuestas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegTabulacionEncuesta obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegTabulacionEncuestas)
     * @throws java.lang.Exception
     */
    void actualizar(AsegTabulacionEncuesta obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegTabulacionEncuestas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegTabulacionEncuesta eliminar(int id) throws Exception;
    
    /**
     * Consultar todos las Traslados
     * @param empresaId
     * @return
     * @throws Exception 
     */
    List<AsegTabulacionEncuesta> consultarTodos() throws Exception;
    
    /**
     * consultar preguntas de la encuesta de afiliación
     * @return
     * @throws Exception 
     */
    public List<AsegEncuestaPregunta> consultarPreguntasEncuesta() throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public List<AsegTabulacionEncuesta> consultarPorAfiliado(int id) throws Exception;
    
}
