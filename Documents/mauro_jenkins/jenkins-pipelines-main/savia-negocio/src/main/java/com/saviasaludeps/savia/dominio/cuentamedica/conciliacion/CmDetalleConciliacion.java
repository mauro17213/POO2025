/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;


/**
 *
 * @author rpalacios
 */

public class CmDetalleConciliacion extends Auditoria {

    private Integer id;
    private String documento;
    private int itemConciliacion;
    private short valorCobroDetalle;
    private short valorFacturado;
    private short valorPagado;
    private short valorPagadoEps;
    private short valoraceptadoIps;
    private short valorPendiente;
    private String observacion;
    private CmConciliacion cmConciliacionesId;
    private CmGlosa cmGlosa;
    private List<CmSeguimientoTransaccion> listaSeguimientoTransaccion;

    public CmDetalleConciliacion() {
    }

    public CmDetalleConciliacion(Integer id) {
        this.id = id;
    }

    public CmDetalleConciliacion(Integer id, String documento, int itemConciliacion, short valorCobroDetalle, short valorFacturado, short valorPagado, short valorPagadoEps, short valoraceptadoIps, short valorPendiente, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.documento = documento;
        this.itemConciliacion = itemConciliacion;
        this.valorCobroDetalle = valorCobroDetalle;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.valorPagadoEps = valorPagadoEps;
        this.valoraceptadoIps = valoraceptadoIps;
        this.valorPendiente = valorPendiente;
        this.observacion = observacion;
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

    public int getItemConciliacion() {
        return itemConciliacion;
    }

    public void setItemConciliacion(int itemConciliacion) {
        this.itemConciliacion = itemConciliacion;
    }

    public short getValorCobroDetalle() {
        return valorCobroDetalle;
    }

    public void setValorCobroDetalle(short valorCobroDetalle) {
        this.valorCobroDetalle = valorCobroDetalle;
    }

    public short getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(short valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public short getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(short valorPagado) {
        this.valorPagado = valorPagado;
    }

    public short getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(short valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public short getValoraceptadoIps() {
        return valoraceptadoIps;
    }

    public void setValoraceptadoIps(short valoraceptadoIps) {
        this.valoraceptadoIps = valoraceptadoIps;
    }

    public short getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(short valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public CmConciliacion getCmConciliacionesId() {
        return cmConciliacionesId;
    }

    public void setCmConciliacionesId(CmConciliacion cmConciliacionesId) {
        this.cmConciliacionesId = cmConciliacionesId;
    }

    public CmGlosa getCmGlosa() {
        return cmGlosa;
    }

    public void setCmGlosa(CmGlosa cmGlosa) {
        this.cmGlosa = cmGlosa;
    }

    public List<CmSeguimientoTransaccion> getListaSeguimientoTransaccion() {
        return listaSeguimientoTransaccion;
    }

    public void setListaSeguimientoTransaccion(List<CmSeguimientoTransaccion> listaSeguimientoTransaccion) {
        this.listaSeguimientoTransaccion = listaSeguimientoTransaccion;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmDetalleConciliacion)) {
            return false;
        }
        CmDetalleConciliacion other = (CmDetalleConciliacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmDetalleConciliacion{" + "id=" + id + ", documento=" + documento + ", itemConciliacion=" + itemConciliacion + ", valorCobroDetalle=" + valorCobroDetalle + ", valorFacturado=" + valorFacturado + ", valorPagado=" + valorPagado + ", valorPagadoEps=" + valorPagadoEps + ", valoraceptadoIps=" + valoraceptadoIps + ", valorPendiente=" + valorPendiente + ", observacion=" + observacion + ", cmConciliacionesId=" + cmConciliacionesId + ", cmGlosa=" + cmGlosa + ", listaSeguimientoTransaccion=" + listaSeguimientoTransaccion + '}';
    }

}
