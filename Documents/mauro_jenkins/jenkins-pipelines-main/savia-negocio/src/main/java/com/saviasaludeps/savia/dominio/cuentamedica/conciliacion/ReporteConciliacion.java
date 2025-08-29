/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class ReporteConciliacion implements Serializable{
    
    private int id;
    private String strInstitucion;
    private Date dtmFecha;
    private String strRepresentanteIPS;
    private String strRepresentanteSAVIA;
    private int intIdFactura;
    private int intRadicacion;
    private String strFactura;
    private String strDocumentoPaciente;
    private BigDecimal dblValorFactura;
    private BigDecimal dblValorGlosa;
    private String strObservacion;
    private BigDecimal dblValorConsolidado;
    private BigDecimal dblValorAceptadoEPS;
    private BigDecimal dblValorAceptadoIPS;
    private String strRangoConciliacion;
    private int intNumeroActa;
    private String strNit;
    private String strObservacionGlosa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrInstitucion() {
        return strInstitucion;
    }

    public void setStrInstitucion(String strInstitucion) {
        this.strInstitucion = strInstitucion;
    }

    public Date getDtmFecha() {
        return dtmFecha;
    }

    public void setDtmFecha(Date dtmFecha) {
        this.dtmFecha = dtmFecha;
    }

    public String getStrRepresentanteIPS() {
        return strRepresentanteIPS;
    }

    public void setStrRepresentanteIPS(String strRepresentanteIPS) {
        this.strRepresentanteIPS = strRepresentanteIPS;
    }

    public String getStrRepresentanteSAVIA() {
        return strRepresentanteSAVIA;
    }

    public void setStrRepresentanteSAVIA(String strRepresentanteSAVIA) {
        this.strRepresentanteSAVIA = strRepresentanteSAVIA;
    }

    public int getIntIdFactura() {
        return intIdFactura;
    }

    public void setIntIdFactura(int intIdFactura) {
        this.intIdFactura = intIdFactura;
    }

    public int getIntRadicacion() {
        return intRadicacion;
    }

    public void setIntRadicacion(int intRadicacion) {
        this.intRadicacion = intRadicacion;
    }

    public String getStrFactura() {
        return strFactura;
    }

    public void setStrFactura(String strFactura) {
        this.strFactura = strFactura;
    }

    public String getStrDocumentoPaciente() {
        return strDocumentoPaciente;
    }

    public void setStrDocumentoPaciente(String strDocumentoPaciente) {
        this.strDocumentoPaciente = strDocumentoPaciente;
    }

    public BigDecimal getDblValorFactura() {
        return dblValorFactura;
    }

    public void setDblValorFactura(BigDecimal dblValorFactura) {
        this.dblValorFactura = dblValorFactura;
    }

    public BigDecimal getDblValorGlosa() {
        return dblValorGlosa;
    }

    public void setDblValorGlosa(BigDecimal dblValorGlosa) {
        this.dblValorGlosa = dblValorGlosa;
    }

    public String getStrObservacion() {
        return strObservacion;
    }

    public void setStrObservacion(String strObservacion) {
        this.strObservacion = strObservacion;
    }

    public BigDecimal getDblValorConsolidado() {
        return dblValorConsolidado;
    }

    public void setDblValorConsolidado(BigDecimal dblValorConsolidado) {
        this.dblValorConsolidado = dblValorConsolidado;
    }

    public BigDecimal getDblValorAceptadoEPS() {
        return dblValorAceptadoEPS;
    }

    public void setDblValorAceptadoEPS(BigDecimal dblValorAceptadoEPS) {
        this.dblValorAceptadoEPS = dblValorAceptadoEPS;
    }

    public BigDecimal getDblValorAceptadoIPS() {
        return dblValorAceptadoIPS;
    }

    public void setDblValorAceptadoIPS(BigDecimal dblValorAceptadoIPS) {
        this.dblValorAceptadoIPS = dblValorAceptadoIPS;
    }

    public String getStrRangoConciliacion() {
        return strRangoConciliacion;
    }

    public void setStrRangoConciliacion(String strRangoConciliacion) {
        this.strRangoConciliacion = strRangoConciliacion;
    }

    public int getIntNumeroActa() {
        return intNumeroActa;
    }

    public void setIntNumeroActa(int intNumeroActa) {
        this.intNumeroActa = intNumeroActa;
    }

    public String getStrNit() {
        return strNit;
    }

    public void setStrNit(String strNit) {
        this.strNit = strNit;
    }

    public String getStrObservacionGlosa() {
        return strObservacionGlosa;
    }

    public void setStrObservacionGlosa(String strObservacionGlosa) {
        this.strObservacionGlosa = strObservacionGlosa;
    }

    @Override
    public String toString() {
        return "ReporteConciliacion{" + "id=" + id + ", strInstitucion=" + strInstitucion + ", dtmFecha=" + dtmFecha + ", strRepresentanteIPS=" + strRepresentanteIPS + ", strRepresentanteSAVIA=" + strRepresentanteSAVIA + ", intIdFactura=" + intIdFactura + ", intRadicacion=" + intRadicacion + ", strFactura=" + strFactura + ", strDocumentoPaciente=" + strDocumentoPaciente + ", dblValorFactura=" + dblValorFactura + ", dblValorGlosa=" + dblValorGlosa + ", strObservacion=" + strObservacion + ", dblValorConsolidado=" + dblValorConsolidado + ", dblValorAceptadoEPS=" + dblValorAceptadoEPS + ", dblValorAceptadoIPS=" + dblValorAceptadoIPS + ", strRangoConciliacion=" + strRangoConciliacion + ", intNumeroActa=" + intNumeroActa + ", strNit=" + strNit + ", strObservacionGlosa=" + strObservacionGlosa + '}';
    }

    

    
    
}
