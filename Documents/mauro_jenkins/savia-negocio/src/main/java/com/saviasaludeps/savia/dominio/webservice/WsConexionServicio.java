/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.webservice;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Raúl Palacios
 */
public class WsConexionServicio extends Auditoria{

    private Integer id;
    private WsConexion wsConexion;
    private WsServicio wsServicio;

    public WsConexionServicio() {
    }

    public WsConexionServicio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WsConexion getWsConexion() {
        return wsConexion;
    }

    public void setWsConexion(WsConexion wsConexion) {
        this.wsConexion = wsConexion;
    }

    public WsServicio getWsServicio() {
        return wsServicio;
    }

    public void setWsServicio(WsServicio wsServicio) {
        this.wsServicio = wsServicio;
    }
    
}
