/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class RespuestaEntregaServiciosDTO implements Serializable {

    private Integer respuesta;
    private String mensaje;
    private String NUT;
    private Integer cantidadRegistros;
    private String fechaTransaccion;
    private List<EntregaTecnologiaRespuestaDTO> entregaTecnologia;

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNUT() {
        return NUT;
    }

    public void setNUT(String NUT) {
        this.NUT = NUT;
    }

    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public List<EntregaTecnologiaRespuestaDTO> getEntregaTecnologia() {
        return entregaTecnologia;
    }

    public void setEntregaTecnologia(List<EntregaTecnologiaRespuestaDTO> entregaTecnologia) {
        this.entregaTecnologia = entregaTecnologia;
    }

}
