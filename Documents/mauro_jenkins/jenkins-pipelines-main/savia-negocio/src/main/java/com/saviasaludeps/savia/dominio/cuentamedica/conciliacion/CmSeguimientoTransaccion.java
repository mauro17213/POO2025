/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author rpalacios
 */

public class CmSeguimientoTransaccion extends Auditoria  {

    private Integer id;
    private CmDetalleConciliacion listaCmDetalleConciliacion;
    private CmGlosaRespuestaDetalle listaCmGlosaRespuestaDetalle;

    public CmSeguimientoTransaccion() {
    }

    public CmSeguimientoTransaccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CmDetalleConciliacion getListaCmDetalleConciliacion() {
        return listaCmDetalleConciliacion;
    }

    public void setListaCmDetalleConciliacion(CmDetalleConciliacion listaCmDetalleConciliacion) {
        this.listaCmDetalleConciliacion = listaCmDetalleConciliacion;
    }

    public CmGlosaRespuestaDetalle getListaCmGlosaRespuestaDetalle() {
        return listaCmGlosaRespuestaDetalle;
    }

    public void setListaCmGlosaRespuestaDetalle(CmGlosaRespuestaDetalle listaCmGlosaRespuestaDetalle) {
        this.listaCmGlosaRespuestaDetalle = listaCmGlosaRespuestaDetalle;
    }

   


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmSeguimientoTransaccion)) {
            return false;
        }
        CmSeguimientoTransaccion other = (CmSeguimientoTransaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmSeguimientoTransaccion{" + "id=" + id + ", listaCmDetalleConciliacion=" + listaCmDetalleConciliacion + ", listaCmGlosaRespuestaDetalle=" + listaCmGlosaRespuestaDetalle + '}';
    }

    
    
}
