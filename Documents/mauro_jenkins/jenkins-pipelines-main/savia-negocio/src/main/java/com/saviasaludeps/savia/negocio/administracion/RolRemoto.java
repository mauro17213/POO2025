/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface RolRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<Rol> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (Rols) cargado
     * @throws java.lang.Exception
     */
    Rol consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (Rols)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Rol rol) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (Rols)
     * @throws java.lang.Exception
     */
    void actualizar(Rol per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Rols) Objetop eliminado
     * @throws java.lang.Exception
     */
    Rol eliminar(int id) throws Exception;

    /**
     * Consultar usuario por nombre de usuario
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    Rol consultarPorRol(String rol) throws Exception;

    /**
     *
     * @param empresaId
     * @return
     * @throws Exception
     */
    HashMap<Integer, Rol> consultarHashTodos(int empresaId) throws Exception;

    /**
     * Método para consultar todos los registros
     *
     * @return
     * @throws java.lang.Exception
     */
    List<Rol> consultarTodos() throws Exception;
    
}
