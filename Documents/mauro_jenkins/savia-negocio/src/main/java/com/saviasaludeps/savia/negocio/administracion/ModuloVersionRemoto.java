/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.ModuloVersion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface ModuloVersionRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<ModuloVersion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de módulos Versión
     * @return
     * @throws Exception 
     */
    List<ModuloVersion> consultarTodos() throws Exception;
    
    /**
     * Método para consultar por ID
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    ModuloVersion consultar(int id) throws Exception;
    
    /**
     * Método para consultar por modulo
     * @param id
     * @return
     * @throws Exception 
     */
    List<ModuloVersion> consultarByModulo(int id) throws Exception;
    
    
    /**
     * Método para crear un registro
     * @param objeto
     * @return
     * @throws Exception 
     */
    int insertar(ModuloVersion objeto) throws Exception;
    
    /**
     * Método para consultar la versión mas actual por modulo
     * @param id
     * @return
     * @throws Exception 
     */
    ModuloVersion consultarActualByModulo(int id) throws Exception;
}
