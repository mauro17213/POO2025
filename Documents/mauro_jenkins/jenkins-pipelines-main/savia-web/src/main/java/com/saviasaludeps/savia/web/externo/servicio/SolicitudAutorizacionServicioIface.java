/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;
import com.saviasaludeps.savia.web.externo.bean.SolicitudAutorizacionBean;

/**
 *
 * @author admin
 */
public interface SolicitudAutorizacionServicioIface {
    
    void Accion(SolicitudAutorizacionBean bean);
    
    /**
     * Cargar lista de Zonas
     * @param bean 
     */
    void cargaInicial(SolicitudAutorizacionBean bean);
    
}
