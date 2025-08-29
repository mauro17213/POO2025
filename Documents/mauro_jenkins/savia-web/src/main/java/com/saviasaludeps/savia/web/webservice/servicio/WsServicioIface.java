/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.servicio;

import com.saviasaludeps.savia.web.webservice.bean.WsServicioBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface WsServicioIface {
    
    void Accion(WsServicioBean bean);
    
    void cargaInicial(WsServicioBean bean);
}
