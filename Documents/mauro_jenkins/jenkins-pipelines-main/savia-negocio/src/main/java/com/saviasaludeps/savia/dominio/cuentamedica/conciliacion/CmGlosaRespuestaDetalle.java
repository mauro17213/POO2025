/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class CmGlosaRespuestaDetalle extends Auditoria {

    private Integer id;
    private String documento;
    private BigDecimal valorCobroDetalle;
    private BigDecimal valorFacturado;
    private BigDecimal valorPagado;
    private BigDecimal valorPagadoEps;
    private BigDecimal porcentajePagadoEps;
    private BigDecimal valorPendiente;
    private BigDecimal valorAceptadoIps;
    private BigDecimal porcentajeAceptadoIps;
    private String observacion;
    private String observacionMasiva;
    private CmGlosa cmGlosa;
    private List<CmSeguimientoTransaccion> listaCmSeguimientoTransaccion;
    private CmGlosaRespuesta cmGlosaRespuesta;
    private int tipoEstadoRespuesta;
    private BigDecimal valorPendienteActualPrecalculado;
    private BigDecimal valorFacturadoPrecalculado;
    private BigDecimal valorPagadoEpsPrecalculado;
    private BigDecimal valorAceptadoIpsPrecalculado;
    private CmDetalle cmDetalle;
    private BigDecimal valorAceptadoIpsSeleccionados;
    private BigDecimal valorPagadoEpsSeleccionados;
    private BigDecimal valorPagadoItemsSeleccionados;
    private BigDecimal valorPendienteActualItemsSeleccionados;
    private String representanteEps;
    private String representanteIps;
    private String tipoRespuestaStr;
    
    public static final int TAMANIO_OBSERVACION = 20;
   
    public CmGlosaRespuestaDetalle() {
    }

    public CmGlosaRespuestaDetalle(Integer id) {
        this.id = id;
    }

    public CmGlosaRespuestaDetalle(Integer id, String documento, BigDecimal valorCobroDetalle, BigDecimal valorFacturado, BigDecimal valorPagado, BigDecimal valorPagadoEps, BigDecimal valorPendiente, BigDecimal valorAceptadoIps, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.documento = documento;
        this.valorCobroDetalle = valorCobroDetalle;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.valorPagadoEps = valorPagadoEps;
        this.valorPendiente = valorPendiente;
        this.valorAceptadoIps = valorAceptadoIps;
        this.observacion = observacion;
    }
    
    public CmGlosaRespuestaDetalle(Integer id, String documento, CmGlosaRespuesta cmGlosaRespuesta, CmDetalle cmDetalle, BigDecimal valorPagadoEps) {
        this.id = id;
        this.documento = documento;
        this.cmGlosaRespuesta = cmGlosaRespuesta;
        this.cmDetalle = cmDetalle;
        this.valorPagadoEps = valorPagadoEps;
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
    
    public String getObservacionCorto() {
        if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacion;
        }
        
    }

    public CmGlosa getCmGlosa() {
        return cmGlosa;
    }

    public void setCmGlosa(CmGlosa cmGlosa) {
        this.cmGlosa = cmGlosa;
    }

    public List<CmSeguimientoTransaccion> getListaCmSeguimientoTransaccion() {
        return listaCmSeguimientoTransaccion;
    }

    public void setListaCmSeguimientoTransaccion(List<CmSeguimientoTransaccion> listaCmSeguimientoTransaccion) {
        this.listaCmSeguimientoTransaccion = listaCmSeguimientoTransaccion;
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

    public CmGlosaRespuesta getCmGlosaRespuesta() {
        return cmGlosaRespuesta;
    }

    public void setCmGlosaRespuesta(CmGlosaRespuesta cmGlosaRespuesta) {
        this.cmGlosaRespuesta = cmGlosaRespuesta;
    }

    public int getTipoEstadoRespuesta() {
        return tipoEstadoRespuesta;
    }

    public void setTipoEstadoRespuesta(int tipoEstadoRespuesta) {
        this.tipoEstadoRespuesta = tipoEstadoRespuesta;
    }

    public BigDecimal getValorPendienteActualPrecalculado() {
        return valorPendienteActualPrecalculado;
    }

    public void setValorPendienteActualPrecalculado(BigDecimal valorPendienteActualPrecalculado) {
        this.valorPendienteActualPrecalculado = valorPendienteActualPrecalculado;
    }

    public BigDecimal getValorFacturadoPrecalculado() {
        return valorFacturadoPrecalculado;
    }

    public void setValorFacturadoPrecalculado(BigDecimal valorFacturadoPrecalculado) {
        this.valorFacturadoPrecalculado = valorFacturadoPrecalculado;
    }

    public BigDecimal getValorPagadoEpsPrecalculado() {
        return valorPagadoEpsPrecalculado;
    }

    public void setValorPagadoEpsPrecalculado(BigDecimal valorPagadoEpsPrecalculado) {
        this.valorPagadoEpsPrecalculado = valorPagadoEpsPrecalculado;
    }

    public BigDecimal getValorAceptadoIpsPrecalculado() {
        return valorAceptadoIpsPrecalculado;
    }

    public void setValorAceptadoIpsPrecalculado(BigDecimal valorAceptadoIpsPrecalculado) {
        this.valorAceptadoIpsPrecalculado = valorAceptadoIpsPrecalculado;
    }

    public CmDetalle getCmDetalle() {
        return cmDetalle;
    }

    public void setCmDetalle(CmDetalle cmDetalle) {
        this.cmDetalle = cmDetalle;
    }

    public BigDecimal getValorAceptadoIpsSeleccionados() {
        return valorAceptadoIpsSeleccionados;
    }

    public void setValorAceptadoIpsSeleccionados(BigDecimal valorAceptadoIpsSeleccionados) {
        this.valorAceptadoIpsSeleccionados = valorAceptadoIpsSeleccionados;
    }

    public BigDecimal getValorPagadoEpsSeleccionados() {
        return valorPagadoEpsSeleccionados;
    }

    public void setValorPagadoEpsSeleccionados(BigDecimal valorPagadoEpsSeleccionados) {
        this.valorPagadoEpsSeleccionados = valorPagadoEpsSeleccionados;
    }

    public BigDecimal getValorPagadoItemsSeleccionados() {
        return valorPagadoItemsSeleccionados;
    }

    public void setValorPagadoItemsSeleccionados(BigDecimal valorPagadoItemsSeleccionados) {
        this.valorPagadoItemsSeleccionados = valorPagadoItemsSeleccionados;
    }

    public BigDecimal getValorPendienteActualItemsSeleccionados() {
        return valorPendienteActualItemsSeleccionados;
    }

    public void setValorPendienteActualItemsSeleccionados(BigDecimal valorPendienteActualItemsSeleccionados) {
        this.valorPendienteActualItemsSeleccionados = valorPendienteActualItemsSeleccionados;
    }

    public String getObservacionMasiva() {
        return observacionMasiva;
    }

    public void setObservacionMasiva(String observacionMasiva) {
        this.observacionMasiva = observacionMasiva;
    }

    public String getRepresentanteEps() {
        return representanteEps;
    }

    public void setRepresentanteEps(String representanteEps) {
        this.representanteEps = representanteEps;
    }

    public String getRepresentanteIps() {
        return representanteIps;
    }

    public void setRepresentanteIps(String representanteIps) {
        this.representanteIps = representanteIps;
    }

    public String getTipoRespuestaStr() {
        return tipoRespuestaStr;
    }

    public void setTipoRespuestaStr(String tipoRespuestaStr) {
        this.tipoRespuestaStr = tipoRespuestaStr;
    }
    
    public boolean hayValorPagadoEPS() {
        return this.getValorPagadoEps() != null
                && this.getValorPagadoEps().compareTo(new BigDecimal(BigInteger.ZERO)) > 0;
    }
     
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmGlosaRespuestaDetalle)) {
            return false;
        }
        CmGlosaRespuestaDetalle other = (CmGlosaRespuestaDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmGlosaRespuestaDetalle{" + "id=" + id + ", documento=" + documento + ", valorCobroDetalle=" + valorCobroDetalle + ", valorFacturado=" + valorFacturado + ", valorPagado=" + valorPagado + ", valorPagadoEps=" + valorPagadoEps + ", valorPendiente=" + valorPendiente + ", valorAceptadoIps=" + valorAceptadoIps + ", observacion=" + observacion + ", cmGlosa=" + cmGlosa + ", listaCmSeguimientoTransaccion=" + listaCmSeguimientoTransaccion + ", representanteIps="+representanteIps+", representanteEps="+representanteEps+", tipoEstadoRespuesta="+tipoEstadoRespuesta+'}';
    }

    
    
}
