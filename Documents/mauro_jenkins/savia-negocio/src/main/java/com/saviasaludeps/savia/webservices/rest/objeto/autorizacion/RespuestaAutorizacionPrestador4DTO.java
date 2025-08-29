package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

public class RespuestaAutorizacionPrestador4DTO implements Serializable {

    private String tipoTecnologia;
    private String codigoTecnologia;
    private String nombreTecnologia;
    private String nombreIPSAutorizada;
    private String cantidadAutorizada;
    private String cantidadEntregada;
    private String cantidadPendiente;

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public String getNombreTecnologia() {
        return nombreTecnologia;
    }

    public void setNombreTecnologia(String nombreTecnologia) {
        this.nombreTecnologia = nombreTecnologia;
    }

    public String getNombreIPSAutorizada() {
        return nombreIPSAutorizada;
    }

    public void setNombreIPSAutorizada(String nombreIPSAutorizada) {
        this.nombreIPSAutorizada = nombreIPSAutorizada;
    }

    public String getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(String cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public String getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(String cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public String getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(String cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public String getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(String tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RespuestaAutorizacionPrestador4DTO{codigoTecnologia=").append(codigoTecnologia);
        sb.append(", nombreTecnologia=").append(nombreTecnologia);
        sb.append(", nombreIPSAutorizada=").append(nombreIPSAutorizada);
        sb.append(", cantidadAutorizada=").append(cantidadAutorizada);
        sb.append(", cantidadEntregada=").append(cantidadEntregada);
        sb.append(", cantidadPendiente=").append(cantidadPendiente);
        sb.append('}');
        return sb.toString();
    }
  
}
