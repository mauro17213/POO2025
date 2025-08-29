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

/**
 *
 * @author rpalacios
 */

public class CmGlosa extends Auditoria implements Comparable<CmGlosa> {

    private Integer id;
    private BigDecimal valorCobroDetalle;
    private BigDecimal valorFacturado;
    private BigDecimal valorPagado;
    private BigDecimal valorPagadoEps;
    private BigDecimal valorPendiente;
    private BigDecimal valorAceptadoIps;
    private String observacion;
    private List<CmGlosaRespuestaDetalle> listaCmGlosaRespuestaDetalle;
    private CmFactura cmFactura;
    private List<CmDetalleConciliacion> listaCmDetalleConciliacion;
    private List<CmDetalle> listaCmDetalles;

    public CmGlosa() {
    }

    public CmGlosa(Integer id) {
        this.id = id;
    }
    
    public CmGlosa(Integer id, BigDecimal valorCobroDetalle, BigDecimal valorFacturado, BigDecimal valorPagado, BigDecimal valorPagadoEps, BigDecimal valorPendiente, BigDecimal valorAceptadoIps, String observacion, String usuarioCrea, String terminaCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valorCobroDetalle = valorCobroDetalle;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.valorPagadoEps = valorPagadoEps;
        this.valorPendiente = valorPendiente;
        this.valorAceptadoIps = valorAceptadoIps;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorCobroDetalle() {
        return valorCobroDetalle;
    }

    public void setValorCobroDetalle(BigDecimal valorCobroDetalle) {
        this.valorCobroDetalle = valorCobroDetalle;
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

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public BigDecimal getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(BigDecimal valorPendiente) {
        this.valorPendiente = valorPendiente;
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

    public List<CmGlosaRespuestaDetalle> getListaCmGlosaRespuestaDetalle() {
        return listaCmGlosaRespuestaDetalle;
    }

    public void setListaCmGlosaRespuestaDetalle(List<CmGlosaRespuestaDetalle> listaCmGlosaRespuestaDetalle) {
        this.listaCmGlosaRespuestaDetalle = listaCmGlosaRespuestaDetalle;
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public List<CmDetalleConciliacion> getListaCmDetalleConciliacion() {
        return listaCmDetalleConciliacion;
    }

    public void setListaCmDetalleConciliacion(List<CmDetalleConciliacion> listaCmDetalleConciliacion) {
        this.listaCmDetalleConciliacion = listaCmDetalleConciliacion;
    }

    public List<CmDetalle> getListaCmDetalles() {
        return listaCmDetalles;
    }

    public void setListaCmDetalles(List<CmDetalle> listaCmDetalles) {
        this.listaCmDetalles = listaCmDetalles;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmGlosa)) {
            return false;
        }
        CmGlosa other = (CmGlosa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmGlosa{" + "id=" + id + ", valorCobroDetalle=" + valorCobroDetalle + ", valorFacturado=" + valorFacturado + ", valorPagado=" + valorPagado + ", valorPagadoEps=" + valorPagadoEps + ", valorPendiente=" + valorPendiente + ", valorAceptadoIps=" + valorAceptadoIps + ", observacion=" + observacion + ", listaCmGlosaRespuestaDetalle=" + listaCmGlosaRespuestaDetalle + ", cmFactura=" + cmFactura + ", listaCmDetalleConciliacion=" + listaCmDetalleConciliacion + ", listaCmDetalles=" + listaCmDetalles + '}';
    }

    @Override
    public int compareTo(CmGlosa o) {
        return  o.id - this.id;
    }
       
}
