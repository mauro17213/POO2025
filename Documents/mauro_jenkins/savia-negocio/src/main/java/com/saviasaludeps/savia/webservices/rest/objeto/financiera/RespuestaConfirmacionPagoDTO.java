/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.financiera;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class RespuestaConfirmacionPagoDTO implements Serializable {

    private String rc;
    private String msg;
    private String fechaTransaccion;
    private ConfirmacionPagoDTO respuesta;

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

    public ConfirmacionPagoDTO getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(ConfirmacionPagoDTO respuesta) {
        this.respuesta = respuesta;
    }

}
