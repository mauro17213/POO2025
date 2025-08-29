/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.solicitud;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author rpalacic
 */
public class RespuestaSolicitudDTO implements Serializable {

    @SerializedName("rc")
    private int rc;
    @SerializedName("msg")
    private String msg;
    @SerializedName("fechaTransaccion")
    private String fechaTransaccion;
    @SerializedName("radicado")
    private int radicado;

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
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

    public int getRadicado() {
        return radicado;
    }

    public void setRadicado(int radicado) {
        this.radicado = radicado;
    }

}
