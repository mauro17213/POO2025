/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.ModuloManual;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface ModuloManualRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<ModuloManual> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar por ID
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    ModuloManual consultar(int id) throws Exception;
    
    /**
     * Método para consultar por Modulo
     * @param id
     * @param actual
     * @param tipo
     * @return
     * @throws Exception 
     */
    ModuloManual consultarXModulo(int id, boolean actual, int tipo) throws Exception;
    
    int insertar(ModuloManual objeto) throws Exception;
    
    /**
     * Permite actualizar todos los Modulos_Manual a false de un modulo.
     * @param modulos_id
     * @param tipo
     * @throws Exception 
     */
    void actualizarXModulo(int modulos_id, int tipo) throws Exception;
    
    /**
     * Método que permite actualizar el campo actual de la tabla Modulos_Manual.
     * @param id
     * @param actual
     * @throws Exception 
     */
    void actualizarActual(int id, boolean actual) throws Exception;
}
