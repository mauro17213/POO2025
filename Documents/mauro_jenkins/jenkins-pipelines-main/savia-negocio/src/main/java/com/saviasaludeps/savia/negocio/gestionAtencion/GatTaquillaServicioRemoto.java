/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaServicio;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatTaquillaServicioRemoto {

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatTaquillaServicio obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatTaquillaServicio eliminar(int id) throws Exception;
    
    /**
     * Lista los servicios de una taquilla
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    List<GatTaquillaServicio> listarPorIdTaquilla(int idTaquilla) throws Exception;
    
    /**
     * Lista de servicios por sede
     * @param idSede
     * @return
     * @throws Exception 
     */
    List<GatTaquillaServicio> listarPorIdSede(int idSede) throws Exception;
    
    /**
     * Cantidad de taquillas en funcionamiento
     * @param idSede
     * @return
     * @throws Exception 
     */
    int cantidadTaquillasEnAtencion(int idSede) throws Exception;
}
