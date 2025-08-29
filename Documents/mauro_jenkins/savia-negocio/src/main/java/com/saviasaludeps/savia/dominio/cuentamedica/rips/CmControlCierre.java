/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author rpalacic
 */
public class CmControlCierre extends Auditoria{
    
    public static final int COBERTURA_CIERRE_PBS = 0;
    public static final int COBERTURA_CIERRE_NO_PBS = 1;

    private Integer id;
    private String motivo;
    private Date fechaHoraDesde;
    private Date fechaHoraHasta;
    private String maeContratoModalidadCodigo;
    private String maeContratoModalidadValor;
    private Integer maeContratoModalidadId;
    private Integer coberturaCierre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public void setFechaHoraDesde(Date fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public Date getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public void setFechaHoraHasta(Date fechaHoraHasta) {
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public String getMaeContratoModalidadCodigo() {
        return maeContratoModalidadCodigo;
    }

    public void setMaeContratoModalidadCodigo(String maeContratoModalidadCodigo) {
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
    }

    public String getMaeContratoModalidadValor() {
        return maeContratoModalidadValor;
    }

    public void setMaeContratoModalidadValor(String maeContratoModalidadValor) {
        this.maeContratoModalidadValor = maeContratoModalidadValor;
    }

    public Integer getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(Integer maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
    }

    public Integer getCoberturaCierre() {
        return coberturaCierre;
    }

    public void setCoberturaCierre(Integer coberturaCierre) {
        this.coberturaCierre = coberturaCierre;
    }
    
     public String getCoberturaCierreStr() {
         String cobertura = "";
         if(this.coberturaCierre != null ){
             cobertura = coberturaCierre == COBERTURA_CIERRE_PBS ? "Pbs" : "No Pbs";
         }
        return cobertura;
    }
    
    @Override
    public String toString() {
        return "ControlCierre{" + "id=" + id + ", motivo=" + motivo + ", fechaHoraDesde=" + fechaHoraDesde + ", fechaHoraHasta=" + fechaHoraHasta + ", maeContratoModalidadCodigo=" + maeContratoModalidadCodigo + ", maeContratoModalidadValor=" + maeContratoModalidadValor + ", maeContratoModalidadId=" + maeContratoModalidadId + ", cobertura ="+this.getCoberturaCierreStr()+'}';
    }

}
