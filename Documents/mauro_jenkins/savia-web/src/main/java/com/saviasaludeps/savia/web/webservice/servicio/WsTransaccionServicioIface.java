/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.servicio;

import com.saviasaludeps.savia.web.webservice.bean.WsTransaccionBean;

/**
 *
 * @author raul-palacios
 */
public interface WsTransaccionServicioIface {

    void Accion(WsTransaccionBean bean);
    
    void cargaInicial(WsTransaccionBean bean);
    
}
