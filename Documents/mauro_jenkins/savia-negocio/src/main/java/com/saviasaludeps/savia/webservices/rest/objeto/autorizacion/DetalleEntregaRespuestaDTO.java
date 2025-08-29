/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class DetalleEntregaRespuestaDTO implements Serializable {

    private String codItemEntregado;
    private Integer cantidadAutorizada;
    private Integer cantidadEntregada;
    private Integer cantidadPendiente;
    private String codigoCausaNoEntrega;
    private String tipoEntrega;
    private Integer codigoRespuesta;
    private String descripcionRespuesta;

    public String getCodItemEntregado() {
        return codItemEntregado;
    }

    public void setCodItemEntregado(String codItemEntregado) {
        this.codItemEntregado = codItemEntregado;
    }

    public Integer getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(Integer cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public Integer getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Integer cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Integer cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public String getCodigoCausaNoEntrega() {
        return codigoCausaNoEntrega;
    }

    public void setCodigoCausaNoEntrega(String codigoCausaNoEntrega) {
        this.codigoCausaNoEntrega = codigoCausaNoEntrega;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

}
