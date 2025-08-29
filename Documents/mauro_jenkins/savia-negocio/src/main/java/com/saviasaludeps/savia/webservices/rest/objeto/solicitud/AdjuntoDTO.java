/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.solicitud;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author rpalacic
 */
public class AdjuntoDTO implements Serializable {

    @SerializedName("tipo")
    private int tipo;
    @SerializedName("nombreArchivo")
    private String nombreArchivo;
    @SerializedName("archivo")
    private String archivo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

}
