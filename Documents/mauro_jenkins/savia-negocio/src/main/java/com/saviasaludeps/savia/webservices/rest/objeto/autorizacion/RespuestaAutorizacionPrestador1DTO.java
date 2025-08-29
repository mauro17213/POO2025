package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.Date;

public class RespuestaAutorizacionPrestador1DTO implements Serializable {

    private String nut;
    private String rc;
    private String msg;
    private String fechaTransaccion;
    private RespuestaAutorizacionPrestador2DTO respuesta;

    public String getNut() {
        return nut;
    }

    public void setNut(String nut) {
        this.nut = nut;
    }

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

    public RespuestaAutorizacionPrestador2DTO getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(RespuestaAutorizacionPrestador2DTO respuesta) {
        this.respuesta = respuesta;
    }

}
