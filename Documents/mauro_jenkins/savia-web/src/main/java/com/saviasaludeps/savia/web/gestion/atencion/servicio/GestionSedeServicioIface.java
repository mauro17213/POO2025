/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.servicio;

import com.saviasaludeps.savia.web.gestion.atencion.bean.GestionSedeBean;

/**
 *
 * @author sgiraldov
 */
public interface GestionSedeServicioIface {
    
    void Accion(GestionSedeBean bean);
    
    /**
     * Carga inicial de la opción
     * @param bean 
     */
    void cargasInicial(GestionSedeBean bean);
    
}
