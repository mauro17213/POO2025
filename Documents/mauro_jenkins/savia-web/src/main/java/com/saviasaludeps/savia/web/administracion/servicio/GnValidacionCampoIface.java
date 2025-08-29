/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.GnValidacionCampoBean;

/**
 *
 * @author sgiraldov
 */
public interface GnValidacionCampoIface {
    
    /**
     * Acciones
     * @param bean 
     */
    void Accion(GnValidacionCampoBean bean);
    
    /**
     * Carga inicial de la opción
     * @param bean 
     */
    void cargasInicial(GnValidacionCampoBean bean);
    
}
