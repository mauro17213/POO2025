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
public class DetalleEntregaDTO implements Serializable {

    private String codItemEntregado;
    private Integer cantidadEntregada;
    private String fechaEntregaTecnologia;
    private Integer codigoCausaNoEntrega;

    public String getCodItemEntregado() {
        return codItemEntregado;
    }

    public void setCodItemEntregado(String codItemEntregado) {
        this.codItemEntregado = codItemEntregado;
    }

    public Integer getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Integer cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public String getFechaEntregaTecnologia() {
        return fechaEntregaTecnologia;
    }

    public void setFechaEntregaTecnologia(String fechaEntregaTecnologia) {
        this.fechaEntregaTecnologia = fechaEntregaTecnologia;
    }

    public Integer getCodigoCausaNoEntrega() {
        return codigoCausaNoEntrega;
    }

    public void setCodigoCausaNoEntrega(Integer codigoCausaNoEntrega) {
        this.codigoCausaNoEntrega = codigoCausaNoEntrega;
    }

}
