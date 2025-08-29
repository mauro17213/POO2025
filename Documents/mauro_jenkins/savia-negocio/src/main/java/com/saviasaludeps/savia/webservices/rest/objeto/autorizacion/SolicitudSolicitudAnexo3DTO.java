/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class SolicitudSolicitudAnexo3DTO implements Serializable {

    private String numeroSolicitud;
    private String codigoHabilitacionSolicita;
    private String prestadorSede;
    private String estado;
    private String ambito;
    private String servicioSolicitado;
    private String servicioHabilitado;
    private String programaEspecial;
    private String origenAtencion;
    private String tipoServicio;
    private String ubicacion;
    private String justificacionClinica;
    private String responsable;
    private List<Anexo3ItemDTO> servicios;

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public String getCodigoHabilitacionSolicita() {
        return codigoHabilitacionSolicita;
    }

    public void setCodigoHabilitacionSolicita(String codigoHabilitacionSolicita) {
        this.codigoHabilitacionSolicita = codigoHabilitacionSolicita;
    }

    public String getPrestadorSede() {
        return prestadorSede;
    }

    public void setPrestadorSede(String prestadorSede) {
        this.prestadorSede = prestadorSede;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getServicioSolicitado() {
        return servicioSolicitado;
    }

    public void setServicioSolicitado(String servicioSolicitado) {
        this.servicioSolicitado = servicioSolicitado;
    }

    public String getServicioHabilitado() {
        return servicioHabilitado;
    }

    public void setServicioHabilitado(String servicioHabilitado) {
        this.servicioHabilitado = servicioHabilitado;
    }

    public String getProgramaEspecial() {
        return programaEspecial;
    }

    public void setProgramaEspecial(String programaEspecial) {
        this.programaEspecial = programaEspecial;
    }

    public String getOrigenAtencion() {
        return origenAtencion;
    }

    public void setOrigenAtencion(String origenAtencion) {
        this.origenAtencion = origenAtencion;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public List<Anexo3ItemDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<Anexo3ItemDTO> servicios) {
        this.servicios = servicios;
    }

}
