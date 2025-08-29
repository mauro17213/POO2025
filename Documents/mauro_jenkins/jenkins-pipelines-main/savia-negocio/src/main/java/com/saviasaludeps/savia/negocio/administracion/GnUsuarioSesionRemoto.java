/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnUsuarioSesion;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface GnUsuarioSesionRemoto {
    
    /**
     * Consulta los datos de inicio de sesion por idSesion y ip del server
     * @param idSesion
     * @param ip
     * @return
     * @throws Exception 
     */
    GnUsuarioSesion consultarPorIdYIp(String idSesion, String ip) throws Exception;
    
    /**
     * Inserta los datos de sesion
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(GnUsuarioSesion obj) throws Exception;
    
    /**
     * Actualiza los datos de sesion
     * @param obj
     * @throws Exception 
     */
    void actualizar(GnUsuarioSesion obj) throws Exception;
    
    /**
     * Lista todas las sesiones de un usuario
     * @param idUser
     * @return
     * @throws Exception 
     */
    List<GnUsuarioSesion> listarSesionesUsuario(int idUser) throws Exception;
    
    /**
     * Consulta las sesiones por idSesion
     * @param idSesion
     * @return
     * @throws Exception 
     */
    List<GnUsuarioSesion> consultarPorIdSesion(String idSesion) throws Exception;
    
}
