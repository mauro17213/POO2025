/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.referencia;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class RespuestaSolicitudAnexo9DTO implements Serializable{

    private String codigoRespuesta;
    private String mensaje;
    private String fechaTransaccion;
    private String cantidadRegistros;
    private List<DetalleRespuestaSolicitudAnexo9DTO> referencias;

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public List<DetalleRespuestaSolicitudAnexo9DTO> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<DetalleRespuestaSolicitudAnexo9DTO> referencias) {
        this.referencias = referencias;
    }
    
}
