/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.web.contratacion.bean.MarcacionesBean;

/**
 *
 * @author Jose Perez
 */
public interface MarcacionesServicioIface {
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(MarcacionesBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(MarcacionesBean bean);
    
}
