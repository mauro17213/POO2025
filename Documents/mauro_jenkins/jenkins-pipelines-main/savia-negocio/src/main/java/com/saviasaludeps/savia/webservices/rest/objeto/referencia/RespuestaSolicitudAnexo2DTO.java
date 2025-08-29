/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.referencia;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class RespuestaSolicitudAnexo2DTO implements Serializable{

    private String rc;
    private String msg;
    private String fechaTransaccion;
    private AutorizacionAnexo2DTO autorizacion;

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public AutorizacionAnexo2DTO getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(AutorizacionAnexo2DTO autorizacion) {
        this.autorizacion = autorizacion;
    }

}
