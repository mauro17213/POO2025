/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.GnConfiguracionBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface GnConfiguracionServicioIface {
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(GnConfiguracionBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInial(GnConfiguracionBean bean);
}
