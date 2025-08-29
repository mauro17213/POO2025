/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.web.autorizacion.bean.AuZonaBean;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuZonaServicioIface {
    
    void Accion(AuZonaBean bean);
    
    void cargaInicial(AuZonaBean bean);
    
    void eliminarDestino(AuZonaBean bean);
    
}
