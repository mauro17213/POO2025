/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.web.atencionusuario.bean.SolicitudBean;

/**
 *
 * @author José Pérez
 */
public interface SolicitudServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(SolicitudBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(SolicitudBean bean);
    
}
