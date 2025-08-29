/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnSmsEnvio;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface GnSmsEnvioRemoto {
    
    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GnSmsEnvio obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GnSmsEnvio obj) throws Exception;
    
    /**
     * Lista los mensajes pendientes
     * @return
     * @throws Exception 
     */
    List<GnSmsEnvio> listarPendientes() throws Exception;
    
}
