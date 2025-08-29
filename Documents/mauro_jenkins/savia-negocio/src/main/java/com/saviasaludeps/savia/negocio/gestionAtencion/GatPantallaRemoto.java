/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatPantalla;
import java.util.List;

/**
 *
 * @author stive
 */
public interface GatPantallaRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatPantalla consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatPantalla obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatPantalla obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatPantalla eliminar(int id) throws Exception;

    /**
     * Consulta si ya se creo la pantalla
     * @param idSede
     * @param idSesion
     * @return
     * @throws Exception 
     */
    GatPantalla consultaExiste(int idSede, String idSesion) throws Exception;
    
    /**
     * Consulta alguna inactiva para reutilizarla
     * @param idSede
     * @return
     * @throws Exception 
     */
    GatPantalla consultaInactiva(int idSede) throws Exception;
    
    /**
     * Valida si ya hay una sede contando
     * @param idSede
     * @param maeTipoServicioId
     * @return
     * @throws Exception 
     */
    boolean existeCuenta(int idSede, Integer maeTipoServicioId) throws Exception;
    
    /**
     * Inactiva todas las pantallas de la sede
     * @param idSede
     * @throws Exception 
     */
    void inactivarPantallas(int idSede) throws Exception;
    
    /**
     * Consulta solo por el id de sesion pantalla activa
     * @param idSesion
     * @return
     * @throws Exception 
     */
    GatPantalla consultaExiste(String idSesion) throws Exception;
    
}
