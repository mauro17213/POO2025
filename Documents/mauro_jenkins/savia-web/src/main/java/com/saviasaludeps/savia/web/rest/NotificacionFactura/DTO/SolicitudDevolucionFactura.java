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
 * @author yjimenez
 */
public class SolicitudDevolucionFactura {

    @SerializedName("NUT")
    private String NUT;
    @SerializedName("fechaTransaccion")
    private String fechaTransaccion;
    @SerializedName("facturas")
    private List<DevolucionFactura> facturas;

    public String getNUT() {
        return NUT;
    }

    public void setNUT(String NUT) {
        this.NUT = NUT;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public List<DevolucionFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<DevolucionFactura> facturas) {
        this.facturas = facturas;
    }
    
}
