/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;

public class CmAuditoriaCapitaDescuento extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int TAMANIO_OBSERVACION = 20;
    private Integer id;
    private CmDetalle cmDetalle;
    private CntContrato cntContrato;
    private boolean marcacion;
    private String contrato;
    private String observacion;
    private Integer posInsertar;
    

    public CmAuditoriaCapitaDescuento() {
    }

    public CmAuditoriaCapitaDescuento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CmDetalle getCmDetalle() {
        if(cmDetalle== null){
            cmDetalle = new CmDetalle();
        }
        return cmDetalle;
    }

    public void setCmDetalle(CmDetalle cmDetalle) {
        this.cmDetalle = cmDetalle;
    }

    public boolean isMarcacion() {
        return marcacion;
    }
    
    public String getMarcacionStr() {
        return marcacion ? "Si":"No";
    }

    public void setMarcacion(boolean marcacion) {
        this.marcacion = marcacion;
    }
    public boolean getMarcacion() {
       return  this.marcacion ;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getObservacion() {
        return observacion;
    }
    
    public String getObservacionCorto() {
        if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...";
        } else {
            return observacion;
        }   
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getPosInsertar() {
        return posInsertar;
    }

    public void setPosInsertar(Integer posInsertar) {
        this.posInsertar = posInsertar;
    }

    public CntContrato getCntContrato() {
        if(cntContrato== null){
            cntContrato = new CntContrato();
        }
        return cntContrato;
    }

    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }
    
    
    

    @Override
    public String toString() {
        return "CmAuditoriaDescuentoCapitaE{" + "id=" + id + ", cmDetalle id=" + getCmDetalle().getId() + ", cntContratosId=" + getCntContrato().getId() + ", marcacion=" + marcacion + ", contrato=" + contrato + ", observacion=" + observacion + '}';
    }
    
}
