/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class DetalleRespuestaSolicitudPortabilidadDTO implements Serializable{

    private String solicitudPortabilidad;
    private String tipoDocumento;
    private String documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;
    private String fechaSolicitud;
    private String tipoPortabilidad;
    private String periodoInicial;
    private String duracion;
    private String tipoSolcitud;
    private String origenSolicitud;
    private String municipioPortabilidad;
    private String IPSPortabilidad;
    private String nombreDepartamento;
    private String nombreMunicipio;
    private String estadoSolicitud;
    private String codigoRespuesta;

    public String getSolicitudPortabilidad() {
        return solicitudPortabilidad;
    }

    public void setSolicitudPortabilidad(String solicitudPortabilidad) {
        this.solicitudPortabilidad = solicitudPortabilidad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getTipoPortabilidad() {
        return tipoPortabilidad;
    }

    public void setTipoPortabilidad(String tipoPortabilidad) {
        this.tipoPortabilidad = tipoPortabilidad;
    }

    public String getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(String periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTipoSolcitud() {
        return tipoSolcitud;
    }

    public void setTipoSolcitud(String tipoSolcitud) {
        this.tipoSolcitud = tipoSolcitud;
    }

    public String getOrigenSolicitud() {
        return origenSolicitud;
    }

    public void setOrigenSolicitud(String origenSolicitud) {
        this.origenSolicitud = origenSolicitud;
    }

    public String getMunicipioPortabilidad() {
        return municipioPortabilidad;
    }

    public void setMunicipioPortabilidad(String municipioPortabilidad) {
        this.municipioPortabilidad = municipioPortabilidad;
    }

    public String getIPSPortabilidad() {
        return IPSPortabilidad;
    }

    public void setIPSPortabilidad(String IPSPortabilidad) {
        this.IPSPortabilidad = IPSPortabilidad;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

}
