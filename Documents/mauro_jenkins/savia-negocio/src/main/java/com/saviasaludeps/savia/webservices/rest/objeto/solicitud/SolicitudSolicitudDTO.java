/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.solicitud;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SolicitudSolicitudDTO {

    @SerializedName("tipo")
    int tipo;
    
    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @SerializedName("afiliadoDocumentoTipo")
    String afiliadoDocumentoTipo;
    
    public String getAfiliadoDocumentoTipo() {
        return this.afiliadoDocumentoTipo;
    }

    public void setAfiliadoDocumentoTipo(String afiliadoDocumentoTipo) {
        this.afiliadoDocumentoTipo = afiliadoDocumentoTipo;
    }

    @SerializedName("afiliadoDocumentoNumero")
    String afiliadoDocumentoNumero;
    
    public String getAfiliadoDocumentoNumero() {
        return this.afiliadoDocumentoNumero;
    }

    public void setAfiliadoDocumentoNumero(String afiliadoDocumentoNumero) {
        this.afiliadoDocumentoNumero = afiliadoDocumentoNumero;
    }

    @SerializedName("afiliadoDocumentoFechaExpedicion")
    String afiliadoDocumentoFechaExpedicion;
    
    public String getAfiliadoDocumentoFechaExpedicion() {
        return this.afiliadoDocumentoFechaExpedicion;
    }

    public void setAfiliadoDocumentoFechaExpedicion(String afiliadoDocumentoFechaExpedicion) {
        this.afiliadoDocumentoFechaExpedicion = afiliadoDocumentoFechaExpedicion;
    }

    @SerializedName("afiliadoPrimerApellido")
    private String afiliadoPrimerApellido;
    
    public String getAfiliadoPrimerApellido() {
        return this.afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    @SerializedName("afiliadoSegundoApellido")
    private String afiliadoSegundoApellido;
    
    public String getAfiliadoSegundoApellido() {
        return this.afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    @SerializedName("afiliadoPrimerNombre")
    private String afiliadoPrimerNombre;
    
    public String getAfiliadoPrimerNombre() {
        return this.afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    @SerializedName("afiliadoSegundoNombre")
    private String afiliadoSegundoNombre;
    
    public String getAfiliadoSegundoNombre() {
        return this.afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    @SerializedName("afiliadoFechaNacimiento")
    private String afiliadoFechaNacimiento;
    
    public String getAfiliadoFechaNacimiento() {
        return this.afiliadoFechaNacimiento;
    }

    public void setAfiliadoFechaNacimiento(String afiliadoFechaNacimiento) {
        this.afiliadoFechaNacimiento = afiliadoFechaNacimiento;
    }

    @SerializedName("afiliadoSexo")
    private String afiliadoSexo;
    
    public String getAfiliadoSexo() {
        return this.afiliadoSexo;
    }

    public void setAfiliadoSexo(String afiliadoSexo) {
        this.afiliadoSexo = afiliadoSexo;
    }

    @SerializedName("afiliadoCodDepartamentoResidencia")
    private String afiliadoCodDepartamentoResidencia;
    
    public String getAfiliadoCodDepartamentoResidencia() {
        return this.afiliadoCodDepartamentoResidencia;
    }

    public void setAfiliadoCodDepartamentoResidencia(String afiliadoCodDepartamentoResidencia) {
        this.afiliadoCodDepartamentoResidencia = afiliadoCodDepartamentoResidencia;
    }

    @SerializedName("afiliadoCodCiudadResidencia")
    private String afiliadoCodCiudadResidencia;
    
    public String getAfiliadoCodCiudadResidencia() {
        return this.afiliadoCodCiudadResidencia;
    }

    public void setAfiliadoCodCiudadResidencia(String afiliadoCodCiudadResidencia) {
        this.afiliadoCodCiudadResidencia = afiliadoCodCiudadResidencia;
    }

    @SerializedName("afiliadoDescripcionCiudadResidencia")
    private String afiliadoDescripcionCiudadResidencia;
    
    public String getAfiliadoDescripcionCiudadResidencia() {
        return this.afiliadoDescripcionCiudadResidencia;
    }

    public void setAfiliadoDescripcionCiudadResidencia(String afiliadoDescripcionCiudadResidencia) {
        this.afiliadoDescripcionCiudadResidencia = afiliadoDescripcionCiudadResidencia;
    }

    @SerializedName("afiliadoDireccion")
    private String afiliadoDireccion;
    
    public String getAfiliadoDireccion() {
        return this.afiliadoDireccion;
    }

    public void setAfiliadoDireccion(String afiliadoDireccion) {
        this.afiliadoDireccion = afiliadoDireccion;
    }

    @SerializedName("afiliadoZonaAfiliacion")
    private String afiliadoZonaAfiliacion;
    
    public String getAfiliadoZonaAfiliacion() {
        return this.afiliadoZonaAfiliacion;
    }

    public void setAfiliadoZonaAfiliacion(String afiliadoZonaAfiliacion) {
        this.afiliadoZonaAfiliacion = afiliadoZonaAfiliacion;
    }

    @SerializedName("afiliadoTelefono")
    private String afiliadoTelefono;
    
    public String getAfiliadoTelefono() {
        return this.afiliadoTelefono;
    }

    public void setAfiliadoTelefono(String afiliadoTelefono) {
        this.afiliadoTelefono = afiliadoTelefono;
    }

    @SerializedName("afiliadoTelefonoMovil")
    private String afiliadoTelefonoMovil;
    
    public String getAfiliadoTelefonoMovil() {
        return this.afiliadoTelefonoMovil;
    }

    public void setAfiliadoTelefonoMovil(String afiliadoTelefonoMovil) {
        this.afiliadoTelefonoMovil = afiliadoTelefonoMovil;
    }

    @SerializedName("afiliadoCorreoElectronico")
    private String afiliadoCorreoElectronico;
    
    public String getAfiliadoCorreoElectronico() {
        return this.afiliadoCorreoElectronico;
    }

    public void setAfiliadoCorreoElectronico(String afiliadoCorreoElectronico) {
        this.afiliadoCorreoElectronico = afiliadoCorreoElectronico;
    }

    @SerializedName("afiliadoAutorizaNotificacion")
    private int afiliadoAutorizaNotificacion;
    
    public int getAfiliadoAutorizaNotificacion() {
        return this.afiliadoAutorizaNotificacion;
    }

    public void setAfiliadoAutorizaNotificacion(int afiliadoAutorizaNotificacion) {
        this.afiliadoAutorizaNotificacion = afiliadoAutorizaNotificacion;
    }

    @SerializedName("afiliadoDepartamentoAtencion")
    private String afiliadoDepartamentoAtencion;
    
    public String getAfiliadoDepartamentoAtencion() {
        return this.afiliadoDepartamentoAtencion;
    }

    public void setAfiliadoDepartamentoAtencion(String afiliadoDepartamentoAtencion) {
        this.afiliadoDepartamentoAtencion = afiliadoDepartamentoAtencion;
    }

    @SerializedName("afiliadoCiudadAtencion")
    private String afiliadoCiudadAtencion;
    
    public String getAfiliadoCiudadAtencion() {
        return this.afiliadoCiudadAtencion;
    }

    public void setAfiliadoCiudadAtencion(String afiliadoCiudadAtencion) {
        this.afiliadoCiudadAtencion = afiliadoCiudadAtencion;
    }

    @SerializedName("observacion")
    private String observacion;
    
    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @SerializedName("adjuntos")
    private List<AdjuntoDTO> adjuntos;
    
    public List<AdjuntoDTO> getAdjuntos() {
        return this.adjuntos;
    }

    public void setAdjuntos(List<AdjuntoDTO> adjuntos) {
        this.adjuntos = adjuntos;
    }
    
}
