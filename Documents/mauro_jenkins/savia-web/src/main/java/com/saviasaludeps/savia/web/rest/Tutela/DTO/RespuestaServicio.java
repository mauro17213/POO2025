/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.Tutela.DTO;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Stiven Giraldo
 */
public class RespuestaServicio {
    
    @SerializedName("codigo")
    private String codigo;
    @SerializedName("descripcion")
    private String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
