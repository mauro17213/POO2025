/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author jeperez
 */
public class RespuestaNotificacionFacturaPaquete {

    @SerializedName("nut")
    private String nut;
    @SerializedName("fechaTransaccion")
    private String fechaTransaccion;
    @SerializedName("cantidadRegistros")
    private String cantidadRegistros;
    @SerializedName("facturas")
    private List<RespuestaNotificacionFactura> facturas;

    public String getNut() {
        return nut;
    }

    public void setNut(String nut) {
        this.nut = nut;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public List<RespuestaNotificacionFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<RespuestaNotificacionFactura> facturas) {
        this.facturas = facturas;
    }
    
}
