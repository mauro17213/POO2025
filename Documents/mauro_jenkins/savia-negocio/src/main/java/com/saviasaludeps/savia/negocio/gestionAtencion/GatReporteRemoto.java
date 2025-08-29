/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiempo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface GatReporteRemoto {

    /**
     * 
     * @param idSede
     * @return
     * @throws Exception 
     */
    public List<GatSedeTaquilla> listarTaquillasPorSede(int idSede) throws Exception;
    
    /**
     * 
     * @param idTaquilla
     * @return
     * @throws Exception 
     */
    public List<GatAtencion> listarAtencionesActivasPorTaquilla(int idTaquilla) throws Exception;
    
    /**
     * 
     * @param idUsuario
     * @return
     * @throws Exception 
     */
    public GatTiempo reposoActivo(int idUsuario) throws Exception;
    
    /**
     * 
     * @param idTaquilla
     * @param fecha
     * @return
     * @throws Exception 
     */
    public List<GatAtencion> listarAtencionesActivasPorSede(int idTaquilla, Date fecha) throws Exception;
    
   
}
