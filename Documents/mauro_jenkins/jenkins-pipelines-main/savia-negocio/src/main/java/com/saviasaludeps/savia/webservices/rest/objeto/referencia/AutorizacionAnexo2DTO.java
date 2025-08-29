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
public class AutorizacionAnexo2DTO implements Serializable{

    private String nua;
    private String codigoRespuesta;
    private String numeroAtencion;

    public String getNua() {
        return nua;
    }

    public void setNua(String nua) {
        this.nua = nua;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getNumeroAtencion() {
        return numeroAtencion;
    }

    public void setNumeroAtencion(String numeroAtencion) {
        this.numeroAtencion = numeroAtencion;
    }
    
    

}
