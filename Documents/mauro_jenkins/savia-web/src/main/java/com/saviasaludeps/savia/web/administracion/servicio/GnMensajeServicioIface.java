/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.GnMensajeBean;

/**
 *
 * @author sgiraldov
 */
public interface GnMensajeServicioIface {
    
    void Accion(GnMensajeBean bean);
    
    /**
     * Carga inicial de la opción
     * @param bean 
     */
    void cargasInicial(GnMensajeBean bean);
    
}
