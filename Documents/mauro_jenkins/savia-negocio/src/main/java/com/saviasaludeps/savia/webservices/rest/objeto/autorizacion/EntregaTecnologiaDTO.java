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
public class EntregaTecnologiaDTO implements Serializable {

    private Integer autorizacion;
    private String fechaEntrega;
    private String tipoDocRecibe;
    private String documentoRecibe;
    private String nombreUsuarioRecibe;
    private String telefonoRecibe;
    private String celularRecibe;
    private List<DetalleEntregaDTO> detalleEntrega;

    public Integer getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Integer autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getTipoDocRecibe() {
        return tipoDocRecibe;
    }

    public void setTipoDocRecibe(String tipoDocRecibe) {
        this.tipoDocRecibe = tipoDocRecibe;
    }

    public String getDocumentoRecibe() {
        return documentoRecibe;
    }

    public void setDocumentoRecibe(String documentoRecibe) {
        this.documentoRecibe = documentoRecibe;
    }

    public String getNombreUsuarioRecibe() {
        return nombreUsuarioRecibe;
    }

    public void setNombreUsuarioRecibe(String nombreUsuarioRecibe) {
        this.nombreUsuarioRecibe = nombreUsuarioRecibe;
    }

    public String getTelefonoRecibe() {
        return telefonoRecibe;
    }

    public void setTelefonoRecibe(String telefonoRecibe) {
        this.telefonoRecibe = telefonoRecibe;
    }

    public String getCelularRecibe() {
        return celularRecibe;
    }

    public void setCelularRecibe(String celularRecibe) {
        this.celularRecibe = celularRecibe;
    }

    public List<DetalleEntregaDTO> getDetalleEntrega() {
        return detalleEntrega;
    }

    public void setDetalleEntrega(List<DetalleEntregaDTO> detalleEntrega) {
        this.detalleEntrega = detalleEntrega;
    }

}
