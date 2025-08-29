package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.List;

public class RespuestaReporteEntrega1DTO implements Serializable {

    private String nut;
    private String rc;
    private String msg;
    private String fechaTransaccion;
    private String numeroAutorizacion;
    private String registros;
    private List<RespuestaReporteEntrega2DTO> respuesta;

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

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getRegistros() {
        return registros;
    }

    public void setRegistros(String registros) {
        this.registros = registros;
    }

    public List<RespuestaReporteEntrega2DTO> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<RespuestaReporteEntrega2DTO> respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RespuestaReporteEntrega1DTO{nut=").append(nut);
        sb.append(", rc=").append(rc);
        sb.append(", msg=").append(msg);
        sb.append(", fechaTransaccion=").append(fechaTransaccion);
        sb.append(", numeroAutorizacion=").append(numeroAutorizacion);
        sb.append(", registros=").append(registros);
        sb.append(", respuesta=").append(respuesta);
        sb.append('}');
        return sb.toString();
    }


}
