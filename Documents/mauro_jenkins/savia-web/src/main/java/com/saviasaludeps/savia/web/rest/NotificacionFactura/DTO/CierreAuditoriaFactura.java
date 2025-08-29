/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class CierreAuditoriaFactura {

    @SerializedName("consecutivo")
    private String consecutivo;
    @SerializedName("NITProveedor")
    private String NITProveedor;
    @SerializedName("tipoDocumento")
    private String tipoDocumento;
    @SerializedName("numeroDocumento")
    private String numeroDocumento;
    @SerializedName("contrato")
    private String contrato;
    @SerializedName("periodoPago")
    private String periodoPago;
    @SerializedName("regimenAfiliado")
    private String regimenAfiliado;
    @SerializedName("valorDocumento")
    private String valorDocumento;
    @SerializedName("numeroDocumentoOrigen")
    private String numeroDocumentoOrigen;
    @SerializedName("copago")
    private String copago;
    @SerializedName("cuotaModeradora")
    private String cuotaModeradora;
    @SerializedName("descuento")
    private String descuento;
    @SerializedName("IVA")
    private String IVA;
    @SerializedName("valorNotaDebito")
    private String valorNotaDebito;
    @SerializedName("fechaDocumento")
    private String fechaDocumento;
    @SerializedName("fechaRadicacion")
    private String fechaRadicacion;
    @SerializedName("fechaProceso")
    private String fechaProceso;
    @SerializedName("documentoAnticipo")
    private String documentoAnticipo; 
    @SerializedName("tipoTransaccion")
    private String tipoTransaccion;
 
    
    @SerializedName("detalleServicio")
    private List<DetalleServicio> detalleServicio;
    @SerializedName("detalleNotaDebito")
    private List<DetalleServicio> detalleNotaDebito;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNITProveedor() {
        return NITProveedor;
    }

    public void setNITProveedor(String NITProveedor) {
        this.NITProveedor = NITProveedor;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

  

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(String periodoPago) {
        this.periodoPago = periodoPago;
    }

    public String getRegimenAfiliado() {
        return regimenAfiliado;
    }

    public void setRegimenAfiliado(String regimenAfiliado) {
        this.regimenAfiliado = regimenAfiliado;
    }

    public String getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(String valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public String getNumeroDocumentoOrigen() {
        return numeroDocumentoOrigen;
    }

    public void setNumeroDocumentoOrigen(String numeroDocumentoOrigen) {
        this.numeroDocumentoOrigen = numeroDocumentoOrigen;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public String getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(String cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getIVA() {
        return IVA;
    }

    public void setIVA(String IVA) {
        this.IVA = IVA;
    }

    public String getValorNotaDebito() {
        return valorNotaDebito;
    }

    public void setValorNotaDebito(String valorNotaDebito) {
        this.valorNotaDebito = valorNotaDebito;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(String fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(String fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getDocumentoAnticipo() {
        return documentoAnticipo;
    }

    public void setDocumentoAnticipo(String documentoAnticipo) {
        this.documentoAnticipo = documentoAnticipo;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public List<DetalleServicio> getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(List<DetalleServicio> detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public List<DetalleServicio> getDetalleNotaDebito() {
        return detalleNotaDebito;
    }

    public void setDetalleNotaDebito(List<DetalleServicio> detalleNotaDebito) {
        this.detalleNotaDebito = detalleNotaDebito;
    }
}
