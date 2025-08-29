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
public class ReporteRespuestaGlosa implements Serializable{
    
    private Integer id;
    private String strNumeroDocumento;
    private String strFactura;
    private String strRadicacionRG;
    private Date dtmFechaFactura;
    private String strProveedor;
    private String strRadicacion;
    private Date dtmFechaRadicacion;
    private String strNit;
    private String strItem;
    private String strDocumento;
    private String strServicio;
    private BigDecimal dblValorFacturado;
    private BigDecimal dblValorPagado;
    private BigDecimal dblValorAceptado;
    private BigDecimal dblValorPendiente;
    private String strObservacion;
    private String strGlosa;
    private String strDetalleGlosa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrNumeroDocumento() {
        return strNumeroDocumento;
    }

    public void setStrNumeroDocumento(String strNumeroDocumento) {
        this.strNumeroDocumento = strNumeroDocumento;
    }

    public String getStrFactura() {
        return strFactura;
    }

    public void setStrFactura(String strFactura) {
        this.strFactura = strFactura;
    }

    public String getStrRadicacionRG() {
        return strRadicacionRG;
    }

    public void setStrRadicacionRG(String strRadicacionRG) {
        this.strRadicacionRG = strRadicacionRG;
    }

    public Date getDtmFechaFactura() {
        return dtmFechaFactura;
    }

    public void setDtmFechaFactura(Date dtmFechaFactura) {
        this.dtmFechaFactura = dtmFechaFactura;
    }

    public String getStrProveedor() {
        return strProveedor;
    }

    public void setStrProveedor(String strProveedor) {
        this.strProveedor = strProveedor;
    }

    public String getStrRadicacion() {
        return strRadicacion;
    }

    public void setStrRadicacion(String strRadicacion) {
        this.strRadicacion = strRadicacion;
    }

    public Date getDtmFechaRadicacion() {
        return dtmFechaRadicacion;
    }

    public void setDtmFechaRadicacion(Date dtmFechaRadicacion) {
        this.dtmFechaRadicacion = dtmFechaRadicacion;
    }

    public String getStrNit() {
        return strNit;
    }

    public void setStrNit(String strNit) {
        this.strNit = strNit;
    }

    public String getStrItem() {
        return strItem;
    }

    public void setStrItem(String strItem) {
        this.strItem = strItem;
    }

    public String getStrDocumento() {
        return strDocumento;
    }

    public void setStrDocumento(String strDocumento) {
        this.strDocumento = strDocumento;
    }

    public String getStrServicio() {
        return strServicio;
    }

    public void setStrServicio(String strServicio) {
        this.strServicio = strServicio;
    }

    public BigDecimal getDblValorFacturado() {
        return dblValorFacturado;
    }

    public void setDblValorFacturado(BigDecimal dblValorFacturado) {
        this.dblValorFacturado = dblValorFacturado;
    }

    public BigDecimal getDblValorPagado() {
        return dblValorPagado;
    }

    public void setDblValorPagado(BigDecimal dblValorPagado) {
        this.dblValorPagado = dblValorPagado;
    }

    public BigDecimal getDblValorAceptado() {
        return dblValorAceptado;
    }

    public void setDblValorAceptado(BigDecimal dblValorAceptado) {
        this.dblValorAceptado = dblValorAceptado;
    }

    public BigDecimal getDblValorPendiente() {
        return dblValorPendiente;
    }

    public void setDblValorPendiente(BigDecimal dblValorPendiente) {
        this.dblValorPendiente = dblValorPendiente;
    }

    public String getStrObservacion() {
        return strObservacion;
    }

    public void setStrObservacion(String strObservacion) {
        this.strObservacion = strObservacion;
    }

    public String getStrGlosa() {
        return strGlosa;
    }

    public void setStrGlosa(String strGlosa) {
        this.strGlosa = strGlosa;
    }

    public String getStrDetalleGlosa() {
        return strDetalleGlosa;
    }

    public void setStrDetalleGlosa(String strDetalleGlosa) {
        this.strDetalleGlosa = strDetalleGlosa;
    }

    @Override
    public String toString() {
        return "ReporteRespuestaGlosa{" + "idRespuestaGlosa=" + id + ", strNumeroDocumento=" + strNumeroDocumento + ", strFactura=" + strFactura + ", strRadicacionRG=" + strRadicacionRG + ", strDocumento=" + strDocumento + ", strServicio=" + strServicio + '}';
    }

}
