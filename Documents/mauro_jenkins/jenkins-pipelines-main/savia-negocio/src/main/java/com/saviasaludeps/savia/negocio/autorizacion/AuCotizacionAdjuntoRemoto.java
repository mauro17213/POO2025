/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionAdjunto;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface AuCotizacionAdjuntoRemoto {
    
    /**
     * Insertar adjunto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuCotizacionAdjunto obj) throws Exception;
    
    /**
     * Lista los adjuntos segun la cotizacion
     * @param idCotizacion
     * @return
     * @throws Exception 
     */
    List<AuCotizacionAdjunto> listarAdjuntosByIdCotizacion(int idCotizacion) throws Exception;
    
    /**
     * Elimina
     * @param id
     * @return
     * @throws Exception 
     */
    AuCotizacionAdjunto eliminar(int id) throws Exception;
    
}
