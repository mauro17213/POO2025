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
public class NotificacionFacturaPaqueteM2 {

    @SerializedName("nut")
    private String nut;
    @SerializedName("cantidadRegistros")
    private String cantidadRegistros;
    @SerializedName("fechaHoraTrasaccion")
    private String fechaHoraTrasaccion;
    @SerializedName("tipoTrasaccion")
    private String tipoTrasaccion;
    @SerializedName("facturas")
    private List<CierreAuditoriaFactura> facturas;

    public String getNut() {
        return nut;
    }

    public void setNut(String nut) {
        this.nut = nut;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getFechaHoraTrasaccion() {
        return fechaHoraTrasaccion;
    }

    public void setFechaHoraTrasaccion(String fechaHoraTrasaccion) {
        this.fechaHoraTrasaccion = fechaHoraTrasaccion;
    }

    public List<CierreAuditoriaFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<CierreAuditoriaFactura> facturas) {
        this.facturas = facturas;
    }

    public String getTipoTrasaccion() {
        return tipoTrasaccion;
    }

    public void setTipoTrasaccion(String tipoTrasaccion) {
        this.tipoTrasaccion = tipoTrasaccion;
    }
    

}
