/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.referencia;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class SolicitudAnexo2DTO implements Serializable {

    private String id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String codigoHabilitacion;
    private Integer tipoUrgencia;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public Integer getTipoUrgencia() {
        return tipoUrgencia;
    }

    public void setTipoUrgencia(Integer tipoUrgencia) {
        this.tipoUrgencia = tipoUrgencia;
    }

}
