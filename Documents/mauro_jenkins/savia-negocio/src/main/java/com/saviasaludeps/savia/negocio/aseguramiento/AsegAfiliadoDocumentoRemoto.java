/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoDocumento;
import java.util.List;

/**
 *
 * @author Jose Perez Hernandez
 */
public interface AsegAfiliadoDocumentoRemoto {
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegAfiliadoDocumentos) cargado
     * @throws java.lang.Exception
     */
    AsegAfiliadoDocumento consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegAfiliadoDocumentos)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAfiliadoDocumento obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegAfiliadoDocumentos)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAfiliadoDocumento obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegAfiliadoDocumentos) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAfiliadoDocumento eliminar(int id) throws Exception;
    
    /**
     * Consultar todos las Traslados
     * @return
     * @throws Exception 
     */
    List<AsegAfiliadoDocumento> consultarTodos() throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public List<AsegAfiliadoDocumento> consultarPorAfiliado(int id) throws Exception;
    
    /**
     * Se obtiene en forma de Texto plano los datos del histórico del afiliado.
     * @param id
     * @return
     * @throws Exception 
     */
    public String obtenerHistoricoPorAfiliado(int id) throws Exception;
}
