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
public class SolicitudNotificacionFactura {

  
    @SerializedName("NotificacionFactura")
    private List<NotificacionFactura> facturas;


    public List<NotificacionFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<NotificacionFactura> facturas) {
        this.facturas = facturas;
    }

}
