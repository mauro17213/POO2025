/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;


public class CmAuditoriaDetalle extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String nombreCompletoAfiliado;
    private int consecutivoItem; 
    private int maServicioId;
    private String maServicioCodigo;
    private String maServicioValor;
    private Integer radicadoGlosa;
    private BigDecimal valorCopago;
    private BigDecimal valorFacturado;
    private BigDecimal valorPagado;
    private BigDecimal valorPendiente;
    private BigDecimal valorPendienteActual;
    private BigDecimal valorAceptadoIps;
    private String observacion;
    private BigDecimal valorPagadoEps;
    private BigDecimal porcentajePagadoEps;
    private BigDecimal porcentajeAceptadoIps;
    private String observacionRespuestaDetalles;
    private Boolean aplicaAc;
    private Boolean aplicaDc;
    private Boolean aplicaPbs;
    private int tipoServicio;
    private CmAuditoriaFactura cmAuditoriaFactura; 
     
    public static final int TAMANIO_TEXTO_SIMPLIFICAR= 20;
    
    public CmAuditoriaDetalle() {
    }

    public CmAuditoriaDetalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompletoAfiliado() {
        return nombreCompletoAfiliado;
    }

    public void setNombreCompletoAfiliado(String nombreCompletoAfiliado) {
        this.nombreCompletoAfiliado = nombreCompletoAfiliado;
    }

    public int getConsecutivoItem() {
        return consecutivoItem;
    }

    public void setConsecutivoItem(int consecutivoItem) {
        this.consecutivoItem = consecutivoItem;
    }

    public int getMaServicioId() {
        return maServicioId;
    }

    public void setMaServicioId(int maServicioId) {
        this.maServicioId = maServicioId;
    }

    public String getMaServicioCodigo() {
        return maServicioCodigo;
    }

    public void setMaServicioCodigo(String maServicioCodigo) {
        this.maServicioCodigo = maServicioCodigo;
    }

    public String getMaServicioValor() {
        return maServicioValor;
    }

    public void setMaServicioValor(String maServicioValor) {
        this.maServicioValor = maServicioValor;
    }

    public Integer getRadicadoGlosa() {
        return radicadoGlosa;
    }

    public void setRadicadoGlosa(Integer radicadoGlosa) {
        this.radicadoGlosa = radicadoGlosa;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(BigDecimal valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public BigDecimal getValorPendienteActual() {
        return valorPendienteActual;
    }

    public void setValorPendienteActual(BigDecimal valorPendienteActual) {
        this.valorPendienteActual = valorPendienteActual;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public BigDecimal getPorcentajePagadoEps() {
        return porcentajePagadoEps;
    }

    public void setPorcentajePagadoEps(BigDecimal porcentajePagadoEps) {
        this.porcentajePagadoEps = porcentajePagadoEps;
    }

    public BigDecimal getPorcentajeAceptadoIps() {
        return porcentajeAceptadoIps;
    }

    public void setPorcentajeAceptadoIps(BigDecimal porcentajeAceptadoIps) {
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
    }

    public String getObservacionRespuestaDetalles() {
        return observacionRespuestaDetalles;
    }

    public void setObservacionRespuestaDetalles(String observacionRespuestaDetalles) {
        this.observacionRespuestaDetalles = observacionRespuestaDetalles;
    }

    public Boolean getAplicaAc() {
        return aplicaAc;
    }

    public void setAplicaAc(Boolean aplicaAc) {
        this.aplicaAc = aplicaAc;
    }

    public Boolean getAplicaDc() {
        return aplicaDc;
    }

    public void setAplicaDc(Boolean aplicaDc) {
        this.aplicaDc = aplicaDc;
    }

    public Boolean getAplicaPbs() {
        return aplicaPbs;
    }

    public void setAplicaPbs(Boolean aplicaPbs) {
        this.aplicaPbs = aplicaPbs;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public CmAuditoriaFactura getCmAuditoriaFactura() {
        return cmAuditoriaFactura;
    }

    public void setCmAuditoriaFactura(CmAuditoriaFactura cmAuditoriaFactura) {
        this.cmAuditoriaFactura = cmAuditoriaFactura;
    }
    
}
