/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.solicitud.dominio;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Stiven Giraldo
 */
public class RespuestaAnexo implements Serializable {
    
    @SerializedName("ruta")
    private String ruta;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("tipoDescripcion")
    private String tipoDescripcion;
    @SerializedName("tipoCodigo")
    private String tipoCodigo;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    public String getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }
    
    
    
}
