/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class CmAuditoriaNovedad extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int TAMANIO_TEXTO_SIMPLIFICAR = 20;

    private Integer id;
    private String nit;
    private String ips;
    private int numeroRadicado;
    private String numeroFacturado;
    private Date fechaRadicacion;
    private Date fechaPrestacion;
    private String tipoContrato;
    private String regimen;
    private BigDecimal valorFactura;
    private BigDecimal valorInicialGlosa;
    private BigDecimal valorPendienteActual;
    private boolean multiUsuario;
    private String estadoFactura;
    private String usuarioGestiona;
    private String usuarioFactura;
    private String regional;
    
    private String codigoServicio;
    private String novedad;
    private Date fechaNovedad;
    
    private BigDecimal valorAnterior;
    private BigDecimal valorNuevo;
     
  
    public CmAuditoriaNovedad() {
    }

    public CmAuditoriaNovedad(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroFacturado() {
        return numeroFacturado;
    }

    public void setNumeroFacturado(String numeroFacturado) {
        this.numeroFacturado = numeroFacturado;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public BigDecimal getValorInicialGlosa() {
        return valorInicialGlosa;
    }

    public void setValorInicialGlosa(BigDecimal valorInicialGlosa) {
        this.valorInicialGlosa = valorInicialGlosa;
    }

    public BigDecimal getValorPendienteActual() {
        return valorPendienteActual;
    }

    public void setValorPendienteActual(BigDecimal valorPendienteActual) {
        this.valorPendienteActual = valorPendienteActual;
    }

    public boolean isMultiUsuario() {
        return multiUsuario;
    }

    public void setMultiUsuario(boolean multiUsuario) {
        this.multiUsuario = multiUsuario;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getUsuarioGestiona() {
        return usuarioGestiona;
    }

    public void setUsuarioGestiona(String usuarioGestiona) {
        this.usuarioGestiona = usuarioGestiona;
    }

    public String getUsuarioFactura() {
        return usuarioFactura;
    }

    public void setUsuarioFactura(String usuarioFactura) {
        this.usuarioFactura = usuarioFactura;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNovedad() {
        return novedad;
    }

    public void setNovedad(String novedad) {
        this.novedad = novedad;
    }
    
      public String getNovedadCorto() {
        if (novedad != null && novedad.length() >= TAMANIO_TEXTO_SIMPLIFICAR) {
            return novedad.substring(0, TAMANIO_TEXTO_SIMPLIFICAR) + "...   ";
        } else {
            return novedad;
        }
        
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    

    public BigDecimal getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(BigDecimal valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public BigDecimal getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(BigDecimal valorNuevo) {
        this.valorNuevo = valorNuevo;
    }
    

    @Override
    public String toString() {
        return "CmAuditoriaFactura{" + "id=" + id + ", nit=" + nit + ", ips=" + ips + ", numeroRadicado=" + numeroRadicado + ", numeroFacturado=" + numeroFacturado + ", fechaRadicacion=" + fechaRadicacion + ", fechaPrestacion=" + fechaPrestacion + ", tipoContrato=" + tipoContrato + ", regimen=" + regimen + ", valorFactura=" + valorFactura + ", valorInicialGlosa=" + valorInicialGlosa + ", valorPendienteActual=" + valorPendienteActual + ", multiUsuario=" + multiUsuario + ", estadoFactura=" + estadoFactura + ", usuarioGestiona=" + usuarioGestiona + ", usuarioFactura=" + usuarioFactura + ", regional=" + regional + '}';
    }
    
}
