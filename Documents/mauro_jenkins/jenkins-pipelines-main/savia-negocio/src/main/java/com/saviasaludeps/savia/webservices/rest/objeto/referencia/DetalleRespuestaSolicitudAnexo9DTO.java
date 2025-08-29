/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.referencia;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class DetalleRespuestaSolicitudAnexo9DTO implements Serializable {

    private String consecutivo;
    private String referencia;
    private String tipoAnexo;
    private String codigoHabilitacionIPSSolicita;
    private String nombreIPS;
    private String nombreSedeIPS;
    private String tipoDocumento;
    private String documento;
    private String estadoRemision;
    private String codigoEstadoRemision;
    private String codigoRespuesta;
    private String descripcionRespuesta;
    
    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public String getCodigoHabilitacionIPSSolicita() {
        return codigoHabilitacionIPSSolicita;
    }

    public void setCodigoHabilitacionIPSSolicita(String codigoHabilitacionIPSSolicita) {
        this.codigoHabilitacionIPSSolicita = codigoHabilitacionIPSSolicita;
    }

    public String getNombreIPS() {
        return nombreIPS;
    }

    public void setNombreIPS(String nombreIPS) {
        this.nombreIPS = nombreIPS;
    }

    public String getNombreSedeIPS() {
        return nombreSedeIPS;
    }

    public void setNombreSedeIPS(String nombreSedeIPS) {
        this.nombreSedeIPS = nombreSedeIPS;
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

    public String getEstadoRemision() {
        return estadoRemision;
    }

    public void setEstadoRemision(String estadoRemision) {
        this.estadoRemision = estadoRemision;
    }

    public String getCodigoEstadoRemision() {
        return codigoEstadoRemision;
    }

    public void setCodigoEstadoRemision(String codigoEstadoRemision) {
        this.codigoEstadoRemision = codigoEstadoRemision;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

}
