/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.web.maestro.bean.TecnologiasBean;

/**
 *
 * @author José Pérez
 */
public interface TecnologiasServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(TecnologiasBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(TecnologiasBean bean);
    
}
