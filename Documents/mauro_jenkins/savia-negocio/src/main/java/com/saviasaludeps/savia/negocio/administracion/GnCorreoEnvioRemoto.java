/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import java.util.List;

/**
 *
 * @author stive
 */
public interface GnCorreoEnvioRemoto {
    
    /**
     * Consulta los correos pendientes de ser enviados
     * @return
     * @throws Exception 
     */
    List<GnCorreoEnvio> consultarListaPendientes() throws Exception;
    
    /**
     * Actualiza el estado y hora de envio del correo
     * @param idGnCorreoEnvio
     * @param estado
     * @throws Exception 
     */
    void actualizarEnviados(int idGnCorreoEnvio, int estado) throws Exception;
    
    /**
     * Inserta un nuevo objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(GnCorreoEnvio obj) throws Exception;
    
}
