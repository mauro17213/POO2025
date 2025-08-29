/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatSedeTaquillaRemoto {

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatSedeTaquilla obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatSedeTaquilla obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatSedeTaquilla eliminar(int id) throws Exception;
    
    /**
     * Lista las taquillas de cada sede
     * @param idSede
     * @return
     * @throws Exception 
     */
    List<GatSedeTaquilla> listarPorIdSede(int idSede) throws Exception;
    
    /**
     * Consulta por id usuarios
     * @param idUsuario
     * @return
     * @throws Exception 
     */
    GatSedeTaquilla consultarPorIdUsuario(int idUsuario) throws Exception;
    
    /**
     * Libera todas las taquillas de un usuario
     * @param idUsuario
     * @throws Exception 
     */
    void liberarTaquillasDeUsuario(int idUsuario) throws Exception;
    
    /**
     * Libera el usuario de la taquilla
     * @param idTaquilla
     * @throws Exception 
     */
    void liberarTaquilla(int idTaquilla) throws Exception;
    
    /**
     * Lista las taquillas ocupadas
     * @param idSede
     * @param maeServicioId
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    List<GatSedeTaquilla> listarOcupadas(int idSede, int maeServicioId, int idTaquilla) throws Exception;
    
    /**
     * Consulta por id
     * @param id
     * @return
     * @throws Exception 
     */
    GatSedeTaquilla consultar(int id) throws Exception;
    
    /**
     * Lista las taquillas de cada sede solo activas
     * @param idSede
     * @return
     * @throws Exception 
     */
    List<GatSedeTaquilla> listarPorIdSedeActivas(int idSede) throws Exception;
    
    /**
     * Valida si la taquilla esta libre
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    boolean estaLibre(int idTaquilla) throws Exception;
}
