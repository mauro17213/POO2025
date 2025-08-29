/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author jperezn
 */
public class DevolucionFactura {

    @SerializedName("consecutivo")
    private String consecutivo;
    @SerializedName("numeroRadicado")
    private String numeroRadicado;
    @SerializedName("NITProveedor")
    private String NITProveedor;
    @SerializedName("numeroDocumento")
    private String numeroDocumento;
    @SerializedName("codigoTipo")
    private String codigoTipo;
    @SerializedName("descripcionTipo")
    private String descripcionTipo;
    @SerializedName("justificacion")
    private String justificacion;
    @SerializedName("responsableDevolucion")
    private String responsableDevolucion;
    
    
    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNITProveedor() {
        return NITProveedor;
    }

    public void setNITProveedor(String NITProveedor) {
        this.NITProveedor = NITProveedor;
    }

  
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(String codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getResponsableDevolucion() {
        return responsableDevolucion;
    }

    public void setResponsableDevolucion(String responsableDevolucion) {
        this.responsableDevolucion = responsableDevolucion;
    }
    
    


}
