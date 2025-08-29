/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Permiso;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface PermisoRemoto {
    
    /**
     * método para consultar un permiso por la clave primaria.
     * @param idModulo
     * @param idPerfil
     * @return
     * @throws Exception 
     */
    Permiso consultar(int idModulo, int idPerfil) throws Exception;
    
    /**
     * mëtodo para crear permisos
     * @param obj
     * @throws Exception 
     */
    void insertar(Permiso obj) throws Exception;
    
    /**
     * metodo para actualizar los permisos
     * @param obj
     * @throws Exception 
     */
    void actualizar(Permiso obj) throws Exception;
    
    /**
     * Método patra eliminar un permiso 
     * @param idModulo
     * @param idPerfil
     * @return
     * @throws Exception 
     */
    Permiso eliminar(int idModulo, int idPerfil) throws Exception;
    
    /**
     * Método para consultar permisos para un perfil
     * @param idPerfil
     * @return
     * @throws Exception 
     */
//    List<Permisos> consultarPorPerfil(int idPerfil) throws Exception;
    
//    List<Modulos> consultarModulosPermisos(int idPerfil) throws Exception;
    
    List<Permiso> consultarPermisosModulos(int idPerfil) throws Exception;
    
}
