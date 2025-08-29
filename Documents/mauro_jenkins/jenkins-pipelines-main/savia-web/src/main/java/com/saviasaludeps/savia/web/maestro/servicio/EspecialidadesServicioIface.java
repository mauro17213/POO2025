/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.web.maestro.bean.EspecialidadesBean;

/**
 *
 * @author José Pérez
 */
public interface EspecialidadesServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(EspecialidadesBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(EspecialidadesBean bean);
    
}
