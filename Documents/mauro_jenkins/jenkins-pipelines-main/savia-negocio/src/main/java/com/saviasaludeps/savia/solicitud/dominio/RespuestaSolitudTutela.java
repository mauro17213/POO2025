/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.solicitud.dominio;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class RespuestaSolitudTutela implements Serializable {    
    
    @SerializedName("codigoRespuesta")
    private int codigoRespuesta;
    @SerializedName("mensaje")
    private String mensaje;
    @SerializedName("fechaTransaccion")
    private String fechaTransaccion;
    @SerializedName("tutelas")
    private List<RespuestaTutela> tutelas;

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public List<RespuestaTutela> getTutelas() {
        return tutelas;
    }

    public void setTutelas(List<RespuestaTutela> tutelas) {
        this.tutelas = tutelas;
    }
    
    
    
}
