/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAdjunto;
import java.util.List;

/**
 *
 * @author Jose Perez Hernandez
 */
public interface AdjuntoRemoto {
    
    /**
     * 
     * @param afiliadoId
     * @return
     * @throws Exception 
     */
    List<AsegAdjunto> consultarListaPorAfiliado(int afiliadoId) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegAdjuntos) cargado
     * @throws java.lang.Exception
     */
    AsegAdjunto consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegAdjuntos)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAdjunto obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegAdjuntos)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAdjunto obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegAdjuntos) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAdjunto eliminar(int id) throws Exception;
    
    /**
     * Consultar todos las Traslados
     * @param empresaId
     * @return
     * @throws Exception 
     */
    List<AsegAdjunto> consultarTodos() throws Exception;
    
}
