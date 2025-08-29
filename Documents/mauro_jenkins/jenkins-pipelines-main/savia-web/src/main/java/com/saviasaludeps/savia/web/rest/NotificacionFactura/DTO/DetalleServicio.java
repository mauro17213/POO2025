/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author yjimenez
 */
public class DetalleServicio {

    @SerializedName("municipio")
    private String municipio;
    @SerializedName("conceptoContable")
    private String conceptoContable;
    @SerializedName("valorOperacion")
    private String valorOperacion;

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public String getValorOperacion() {
        return valorOperacion;
    }

    public void setValorOperacion(String valorOperacion) {
        this.valorOperacion = valorOperacion;
    }

}
