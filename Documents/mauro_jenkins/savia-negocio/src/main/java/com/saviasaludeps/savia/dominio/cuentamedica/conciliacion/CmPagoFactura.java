/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class CmPagoFactura extends Auditoria {

    public CmPagoFactura() {
    }

    public CmPagoFactura(Integer id) {
        this.id = id;
    }

    private Integer id;
    private Integer consecutivo;
    private String numeroFactura;
    private String claseDocumento;
    private String documentoContable;
    private BigDecimal valorBruto;
    private BigDecimal valorNeto;
    private BigDecimal valorDeducciones;
    private String descripcion;
    private Date fechaHoraCrea;
    private CmFactura cmFacturasId;
    private Integer cmFacturaEstado;
    private Date cmFacturaFecha;
    private BigDecimal cmFacturaValor;
    private List<CmPagoFacturaRetencion> cmPagoFacturaRetencionesList;
    private CmPagoTransaccion cmPagoTransaccionesId;
    private CmPago cmPagosId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getClaseDocumento() {
        return claseDocumento;
    }

    public void setClaseDocumento(String claseDocumento) {
        this.claseDocumento = claseDocumento;
    }

    public BigDecimal getCmFacturaValor() {
        return cmFacturaValor;
    }

    public void setCmFacturaValor(BigDecimal cmFacturaValor) {
        this.cmFacturaValor = cmFacturaValor;
    }

    public Date getCmFacturaFecha() {
        return cmFacturaFecha;
    }

    public void setCmFacturaFecha(Date cmFacturaFecha) {
        this.cmFacturaFecha = cmFacturaFecha;
    }

    public Integer getCmFacturaEstado() {
        return cmFacturaEstado;
    }

    public void setCmFacturaEstado(Integer cmFacturaEstado) {
        this.cmFacturaEstado = cmFacturaEstado;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(BigDecimal valorNeto) {
        this.valorNeto = valorNeto;
    }

    public BigDecimal getValorDeducciones() {
        return valorDeducciones;
    }

    public void setValorDeducciones(BigDecimal valorDeducciones) {
        this.valorDeducciones = valorDeducciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public List<CmPagoFacturaRetencion> getCmPagoFacturaRetencionesList() {
        return cmPagoFacturaRetencionesList;
    }

    public void setCmPagoFacturaRetencionesList(List<CmPagoFacturaRetencion> cmPagoFacturaRetencionesList) {
        this.cmPagoFacturaRetencionesList = cmPagoFacturaRetencionesList;
    }

    public CmFactura getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFactura cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public CmPagoTransaccion getCmPagoTransaccionesId() {
        return cmPagoTransaccionesId;
    }

    public void setCmPagoTransaccionesId(CmPagoTransaccion cmPagoTransaccionesId) {
        this.cmPagoTransaccionesId = cmPagoTransaccionesId;
    }

    public CmPago getCmPagosId() {
        return cmPagosId;
    }

    public void setCmPagosId(CmPago cmPagosId) {
        this.cmPagosId = cmPagosId;
    }

    public String getDocumentoContable() {
        return documentoContable;
    }

    public void setDocumentoContable(String documentoContable) {
        this.documentoContable = documentoContable;
    }

    @Override
    public String toString() {
        return "CmPagoFactura{" + "id=" + id + ", consecutivo=" + consecutivo + ", numeroFactura=" + numeroFactura + ", claseDocumento=" + claseDocumento + ", documentoContable=" + documentoContable + ", valorBruto=" + valorBruto + ", valorNeto=" + valorNeto + ", valorDeducciones=" + valorDeducciones + ", descripcion=" + descripcion + ", fechaHoraCrea=" + fechaHoraCrea + ", cmFacturasId=" + cmFacturasId + ", cmFacturaEstado=" + cmFacturaEstado + ", cmFacturaFecha=" + cmFacturaFecha + ", cmFacturaValor=" + cmFacturaValor + '}';
    }

}
