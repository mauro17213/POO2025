/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

/**
 *
 * @author sgiraldov
 */
public class Anexo4ItemDTO implements Serializable {
    
    private String codigoTecnologia;
    private String cantidad;
    private String costo;
    private String entregado;

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getEntregado() {
        return entregado;
    }

    public void setEntregado(String entregado) {
        this.entregado = entregado;
    }
    
}
