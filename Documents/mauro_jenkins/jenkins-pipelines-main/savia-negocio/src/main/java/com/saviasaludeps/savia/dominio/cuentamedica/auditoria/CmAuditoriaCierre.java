/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jperezn
 */
public class CmAuditoriaCierre  extends Auditoria implements Serializable{

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    private BigDecimal valorFacturado;
    private BigDecimal valorPagado;
    private BigDecimal valorGlosado;
    private int cantidadDetalles;
    private Integer cantidadDetallesRegistradas;
    private Date fechaHoraRegistroInicio;
    private Date fechaHoraRegistroFinalizacion;
    private CmFactura cmFactura;
    private CmRadicado cmRadicado;
    private CmAuditoriaMasivaN cmAuditoriaMasivaN;

    public CmAuditoriaCierre() {
    }

    public CmAuditoriaCierre(Integer id) {
        this.id = id;
    }

    public CmAuditoriaCierre(Integer id, BigDecimal valorFacturado, BigDecimal valorPagado, int cantidadDetalles, Date fechaHoraRegistroInicio, String usuariosCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.cantidadDetalles = cantidadDetalles;
        this.fechaHoraRegistroInicio = fechaHoraRegistroInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getCantidadDetalles() {
        return cantidadDetalles;
    }

    public void setCantidadDetalles(int cantidadDetalles) {
        this.cantidadDetalles = cantidadDetalles;
    }

    public Integer getCantidadDetallesRegistradas() {
        return cantidadDetallesRegistradas;
    }

    public void setCantidadDetallesRegistradas(Integer cantidadDetallesRegistradas) {
        this.cantidadDetallesRegistradas = cantidadDetallesRegistradas;
    }

    public Date getFechaHoraRegistroInicio() {
        return fechaHoraRegistroInicio;
    }

    public void setFechaHoraRegistroInicio(Date fechaHoraRegistroInicio) {
        this.fechaHoraRegistroInicio = fechaHoraRegistroInicio;
    }

    public Date getFechaHoraRegistroFinalizacion() {
        return fechaHoraRegistroFinalizacion;
    }

    public void setFechaHoraRegistroFinalizacion(Date fechaHoraRegistroFinalizacion) {
        this.fechaHoraRegistroFinalizacion = fechaHoraRegistroFinalizacion;
    }



    public CmFactura getCmFactura() {
        if(cmFactura==null){
            cmFactura = new CmFactura();
        }
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public CmRadicado getCmRadicado() {
        if(cmRadicado==null){
            cmRadicado = new CmRadicado();
        }
        return cmRadicado;
    }

    public void setCmRadicado(CmRadicado cmRadicado) {
        this.cmRadicado = cmRadicado;
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public CmAuditoriaMasivaN getCmAuditoriaMasivaN() {
        if( cmAuditoriaMasivaN == null ){
            cmAuditoriaMasivaN = new CmAuditoriaMasivaN();
        }
        return cmAuditoriaMasivaN;
    }

    public void setCmAuditoriaMasivaN(CmAuditoriaMasivaN cmAuditoriaMasivaN) {
        this.cmAuditoriaMasivaN = cmAuditoriaMasivaN;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCierres[ id=" + id + " ]";
    }
    
}
