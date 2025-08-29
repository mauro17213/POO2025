/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;
import com.saviasaludeps.savia.web.externo.bean.AfiliadoActualizacionBean;

/**
 *
 * @author admin
 */
public interface AfiliadoActualizacionServicioIface {
    
    void Accion(AfiliadoActualizacionBean bean);
    
    /**
     * Cargar lista de Zonas
     * @param bean 
     */
    void cargaInicial(AfiliadoActualizacionBean bean);
    
}
