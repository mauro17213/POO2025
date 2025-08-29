/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.servicio;

import com.saviasaludeps.savia.web.webservice.bean.WsConexionBean;


/**
 *
 * @author raul-palacios
 */
public interface WsConexionServicioIface {

    void Accion(WsConexionBean bean);
    
    void cargaInicial(WsConexionBean bean);
    
}
