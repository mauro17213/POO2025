/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.servicio;

import com.saviasaludeps.savia.web.gestion.atencion.bean.GatTurnoBean;

/**
 *
 * @author sgiraldov
 */
public interface GatTurnoServicioIface {
    
    void Accion(GatTurnoBean bean);
    
    void cargaInicial(GatTurnoBean bean);
    
}
