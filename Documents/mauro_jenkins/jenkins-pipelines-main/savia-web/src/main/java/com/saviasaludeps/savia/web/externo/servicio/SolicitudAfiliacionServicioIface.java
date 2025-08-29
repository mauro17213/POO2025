/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.web.externo.bean.SolicitudAfiliacionBean;

/**
 *
 * @author admin
 */
public interface SolicitudAfiliacionServicioIface {
    
    void Accion(SolicitudAfiliacionBean bean);
    
    /**
     * Cargar lista 
     * @param bean 
     */
    void cargaInicial(SolicitudAfiliacionBean bean);
    
}
