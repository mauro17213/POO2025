/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class ConsultaSolicitud implements Serializable {

    @SerializedName("numeroSolicitud")
    private String numeroSolicitud = "";

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

}
