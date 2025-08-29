/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.web.especial.bean.GestionCargaMasivaBean;


/**
 *
 * @author Jaime Andres Olarte
 */
public interface GestionCargaMasivaIface {
    
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(GestionCargaMasivaBean bean);
    
    /**
     * Método para cargar inicial del Bean
     * @param bean
     */
    void cargaInicial(GestionCargaMasivaBean bean);
    
}
