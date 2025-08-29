package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RespuestaAutorizacionPrestadorFecha1DTO implements Serializable {

    private String nut;
    private String rc;
    private String msg;
    private String fechaTransaccion;
    private List<RespuestaAutorizacionPrestadorFecha2DTO> respuesta;

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

    public List<RespuestaAutorizacionPrestadorFecha2DTO> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<RespuestaAutorizacionPrestadorFecha2DTO> respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RespuestaAutorizacionPrestadorFecha1DTO{nut=").append(nut);
        sb.append(", rc=").append(rc);
        sb.append(", msg=").append(msg);
        sb.append(", fechaTransaccion=").append(fechaTransaccion);
        sb.append(", respuesta=").append(respuesta);
        sb.append('}');
        return sb.toString();
    }
}
