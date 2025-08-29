/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class DetalleSolicitudAnexo3DTO implements Serializable{

    private String codigoTecnologiaAut;
    private String descripcionTecnologia;
    private String cantidadAut;
    private String observaciones;
    private String cuotaModeradora;
    private String copago;
    private String estadoTecnologia;
    private String codEstadoTecnologia;
    private String fechaActualizada;
    private String responsableProceso;
    private VolanteRechazoSolicitudAnexo3DTO volanteRechazo;

    public String getCodigoTecnologiaAut() {
        return codigoTecnologiaAut;
    }

    public void setCodigoTecnologiaAut(String codigoTecnologiaAut) {
        this.codigoTecnologiaAut = codigoTecnologiaAut;
    }

    public String getDescripcionTecnologia() {
        return descripcionTecnologia;
    }

    public void setDescripcionTecnologia(String descripcionTecnologia) {
        this.descripcionTecnologia = descripcionTecnologia;
    }

    public String getCantidadAut() {
        return cantidadAut;
    }

    public void setCantidadAut(String cantidadAut) {
        this.cantidadAut = cantidadAut;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(String cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public String getEstadoTecnologia() {
        return estadoTecnologia;
    }

    public void setEstadoTecnologia(String estadoTecnologia) {
        this.estadoTecnologia = estadoTecnologia;
    }

    public String getCodEstadoTecnologia() {
        return codEstadoTecnologia;
    }

    public void setCodEstadoTecnologia(String codEstadoTecnologia) {
        this.codEstadoTecnologia = codEstadoTecnologia;
    }

    public String getFechaActualizada() {
        return fechaActualizada;
    }

    public void setFechaActualizada(String fechaActualizada) {
        this.fechaActualizada = fechaActualizada;
    }

    public String getResponsableProceso() {
        return responsableProceso;
    }

    public void setResponsableProceso(String responsableProceso) {
        this.responsableProceso = responsableProceso;
    }

    public VolanteRechazoSolicitudAnexo3DTO getVolanteRechazo() {
        return volanteRechazo;
    }

    public void setVolanteRechazo(VolanteRechazoSolicitudAnexo3DTO volanteRechazo) {
        this.volanteRechazo = volanteRechazo;
    }

}
