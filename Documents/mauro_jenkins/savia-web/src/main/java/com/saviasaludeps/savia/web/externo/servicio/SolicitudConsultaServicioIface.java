/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.web.externo.bean.SolicitudConsultaBean;


/**
 *
 * @author admin
 */
public interface SolicitudConsultaServicioIface {
    
    void Accion(SolicitudConsultaBean bean);
    
    /**
     * Cargar lista de Zonas
     * @param bean 
     */
    void cargaInicial(SolicitudConsultaBean bean);
    
}
