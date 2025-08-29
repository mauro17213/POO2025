/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface ModuloRemoto {
    
    /**
     * Consultar lista de módulos
     * @return
     * @throws Exception 
     */
    List<Modulo> consultarTodos() throws Exception;
    
    /**
     * Método para consultar un módulo por ID
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    Modulo consultar(int id) throws Exception;
   
    /**
     * Método para insertar un nuevo módulo
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(Modulo obj) throws Exception;
    /**
     * Método para actualizar la información de un Módulo
     * @param obj (Modulos)
     * @throws java.lang.Exception
     */
    void actualizar(Modulo obj) throws Exception;
    
    /**
     * Método para eliminar un módulo
     * @param id
     * @return
     * @throws Exception 
     */
    Modulo eliminar(int id) throws Exception;
    
    /**
     * Método para consultar la lista de módulos de un tipo en particular
     * @param _tipo
     * @return
     * @throws Exception 
     */
    List<Modulo> consultarPorTipo(char _tipo) throws Exception;
    
    /**
     * Método para consultar lista de módulos por usuario
     * @param usuario
     * @return
     * @throws Exception 
     */
    List<Modulo> consultarModulosPorUsuario(Usuario usuario) throws Exception;
    
    /**
     * Método para consultar todos los módulos en forma recursiva según los
     * permisos del usuario
     * @param usuario
     * @return
     * @throws Exception 
     */
    Modulo consultarArbolModuloPorUsuario(Usuario usuario) throws Exception;
    
    /**
     * Metodo para consultar todos los módulos en forma recursiva
     * @return
     * @throws Exception 
     */
    Modulo consultarArbolModulo() throws Exception;
    
    
}
