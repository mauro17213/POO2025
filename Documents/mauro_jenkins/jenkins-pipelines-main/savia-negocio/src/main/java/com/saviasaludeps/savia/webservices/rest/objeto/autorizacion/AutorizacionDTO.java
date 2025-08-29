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
public class AutorizacionDTO implements Serializable {

    private String codigoTecnologia;
    private String fechaAutorizacion;
    private String nombreIPSAutorizada;
    private String nombreTecnologia;
    private String nua;
    private String solicitud;
    private int impresionesRealizadas;

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public String getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(String fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getNombreIPSAutorizada() {
        return nombreIPSAutorizada;
    }

    public void setNombreIPSAutorizada(String nombreIPSAutorizada) {
        this.nombreIPSAutorizada = nombreIPSAutorizada;
    }

    public String getNombreTecnologia() {
        return nombreTecnologia;
    }

    public void setNombreTecnologia(String nombreTecnologia) {
        this.nombreTecnologia = nombreTecnologia;
    }

    public String getNua() {
        return nua;
    }

    public void setNua(String nua) {
        this.nua = nua;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public int getImpresionesRealizadas() {
        return impresionesRealizadas;
    }

    public void setImpresionesRealizadas(int impresionesRealizadas) {
        this.impresionesRealizadas = impresionesRealizadas;
    }

}
