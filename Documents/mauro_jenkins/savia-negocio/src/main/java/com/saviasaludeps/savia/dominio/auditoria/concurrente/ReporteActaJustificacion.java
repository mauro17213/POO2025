/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import java.io.Serializable;

/**
 *
 * @author sgiraldov
 */
public class ReporteActaJustificacion implements Serializable {
    
    private String strFecha;
    private String strHora;
    private String strNombreIps;
    private String strCodigoHabilitacion;
    private String strNumero;
    private String strTipoDoc;
    private String strNumeroDoc;
    private String strNombreCompleto;
    private String strDias;
    private String strCodigoDx;
    private String strNombreDx;
    private String strAuditorAsignado;
    private String strResumen;
    private String strCausa;
    private String strPropuesta;
    private String strAuditorAsistente;

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getStrHora() {
        return strHora;
    }

    public void setStrHora(String strHora) {
        this.strHora = strHora;
    }

    public String getStrNombreIps() {
        return strNombreIps;
    }

    public void setStrNombreIps(String strNombreIps) {
        this.strNombreIps = strNombreIps;
    }

    public String getStrCodigoHabilitacion() {
        return strCodigoHabilitacion;
    }

    public void setStrCodigoHabilitacion(String strCodigoHabilitacion) {
        this.strCodigoHabilitacion = strCodigoHabilitacion;
    }

    public String getStrNumero() {
        return strNumero;
    }

    public void setStrNumero(String strNumero) {
        this.strNumero = strNumero;
    }

    public String getStrTipoDoc() {
        return strTipoDoc;
    }

    public void setStrTipoDoc(String strTipoDoc) {
        this.strTipoDoc = strTipoDoc;
    }

    public String getStrNumeroDoc() {
        return strNumeroDoc;
    }

    public void setStrNumeroDoc(String strNumeroDoc) {
        this.strNumeroDoc = strNumeroDoc;
    }

    public String getStrNombreCompleto() {
        return strNombreCompleto;
    }

    public void setStrNombreCompleto(String strNombreCompleto) {
        this.strNombreCompleto = strNombreCompleto;
    }

    public String getStrDias() {
        return strDias;
    }

    public void setStrDias(String strDias) {
        this.strDias = strDias;
    }

    public String getStrCodigoDx() {
        return strCodigoDx;
    }

    public void setStrCodigoDx(String strCodigoDx) {
        this.strCodigoDx = strCodigoDx;
    }

    public String getStrNombreDx() {
        return strNombreDx;
    }

    public void setStrNombreDx(String strNombreDx) {
        this.strNombreDx = strNombreDx;
    }

    public String getStrAuditorAsignado() {
        return strAuditorAsignado;
    }

    public void setStrAuditorAsignado(String strAuditorAsignado) {
        this.strAuditorAsignado = strAuditorAsignado;
    }

    public String getStrResumen() {
        return strResumen;
    }

    public void setStrResumen(String strResumen) {
        this.strResumen = strResumen;
    }

    public String getStrCausa() {
        return strCausa;
    }

    public void setStrCausa(String strCausa) {
        this.strCausa = strCausa;
    }

    public String getStrPropuesta() {
        return strPropuesta;
    }

    public void setStrPropuesta(String strPropuesta) {
        this.strPropuesta = strPropuesta;
    }

    public String getStrAuditorAsistente() {
        return strAuditorAsistente;
    }

    public void setStrAuditorAsistente(String strAuditorAsistente) {
        this.strAuditorAsistente = strAuditorAsistente;
    }
    
    
    
}
