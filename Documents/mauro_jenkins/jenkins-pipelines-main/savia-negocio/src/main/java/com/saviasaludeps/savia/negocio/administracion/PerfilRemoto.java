/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface PerfilRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<Perfil> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Perfiles) cargado
     * @throws java.lang.Exception
     */
    Perfil consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Perfiles)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Perfil obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Perfiles)
     * @throws java.lang.Exception
     */
    void actualizar(Perfil obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Perfiles) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    Perfil eliminar(int id) throws Exception;
    
    /**
     * Método para consultar todos los registros
     * @return 
     * @throws java.lang.Exception 
     */
    List<Perfil> consultarTodos() throws Exception;
    
    /**
     * Consultar Perfiles de un usuario
     * @param idUsuario
     * @return
     * @throws Exception 
     */
    List<Perfil> consultarListaPorUsuario(int idUsuario) throws Exception;
    
}
