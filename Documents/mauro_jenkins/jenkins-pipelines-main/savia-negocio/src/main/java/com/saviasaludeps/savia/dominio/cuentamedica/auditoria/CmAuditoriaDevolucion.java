/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class CmAuditoriaDevolucion extends Auditoria implements Serializable {
    
    public static final int TIPO_DEVOLUCION_INDIVIDUAL = 1;
    public static final int TIPO_DEVOLUCION_MASIVA = 2;

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int idCmAuditoriaDetalle;
    private CmAuditoriaDetalle cmAuditoriaDetalle;
    private int maeMotivoDevolucionId;
    private String maeMotivoDevolucionCodigo;
    private String maeMotivoDevolucionValor;   
    private Integer maeDevolucionMotivoGeneralId;
    private String maeDevolucionMotivoGeneralCodigo;
    private String maeDevolucionMotivoGeneralValor;  
    private String maeDevolucionMotivoGeneralDescripcion;
    private int maeContratoModalidadId;
    private String maeContratoModalidadCodigo;
    private String maeContratoModalidadValor;
    private int maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private String nit;
    private String ips;
    private String numeroRadicado;
    private String numeroFacturado;
    private Date fechaRadicacion;
    private Date  fechaDevolucion;
    private BigDecimal valorFactura;
    private CmFactura cmFactura;
    private Maestro maestroMotivoDevolucion;
    private Maestro maestroMotivoDevolucionEspecifico;
    private Maestro maestroContrato;
    private String observacion;
    private CmDevolucionMasivaN cmDevolucionMasiva;

  
    public CmAuditoriaDevolucion() {
    }

    public CmAuditoriaDevolucion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCmAuditoriaDetalle() {
        return idCmAuditoriaDetalle;
    }

    public void setIdCmAuditoriaDetalle(int idCmAuditoriaDetalle) {
        this.idCmAuditoriaDetalle = idCmAuditoriaDetalle;
    }

    public CmAuditoriaDetalle getCmAuditoriaDetalle() {
        if(cmAuditoriaDetalle== null){
            cmAuditoriaDetalle = new CmAuditoriaDetalle();
        }
        return cmAuditoriaDetalle;
    }

    public void setCmAuditoriaDetalle(CmAuditoriaDetalle cmAuditoriaDetalle) {
        this.cmAuditoriaDetalle = cmAuditoriaDetalle;
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

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
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

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public int getMaeMotivoDevolucionId() {
        return maeMotivoDevolucionId;
    }

    public void setMaeMotivoDevolucionId(int maeMotivoDevolucionId) {
        this.maeMotivoDevolucionId = maeMotivoDevolucionId;
    }

    public String getMaeMotivoDevolucionCodigo() {
        return maeMotivoDevolucionCodigo;
    }

    public void setMaeMotivoDevolucionCodigo(String maeMotivoDevolucionCodigo) {
        this.maeMotivoDevolucionCodigo = maeMotivoDevolucionCodigo;
    }

    public String getMaeMotivoDevolucionValor() {
        return maeMotivoDevolucionValor;
    }

    public void setMaeMotivoDevolucionValor(String maeMotivoDevolucionValor) {
        this.maeMotivoDevolucionValor = maeMotivoDevolucionValor;
    }

    public int getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(int maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
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

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }
    
    public void castMaeRegimen(Maestro regimen){
        if (regimen != null) {
            this.maeRegimenId = regimen.getId();
            this.maeRegimenCodigo = regimen.getValor();
            this.maeRegimenValor = regimen.getNombre();
        }
    }
    
    public void castMaeContratoModalidad(Maestro contratoModalidad){
        if (contratoModalidad != null) {
            this.maeContratoModalidadId = contratoModalidad.getId();
            this.maeContratoModalidadCodigo = contratoModalidad.getValor();
            this.maeContratoModalidadValor = contratoModalidad.getNombre();
        }
    }
    
    public void castMaeMotivoDevolucion(Maestro motivoDevolucion) {
        if (motivoDevolucion != null) {
            this.maeDevolucionMotivoGeneralId = motivoDevolucion.getId();
            this.maeDevolucionMotivoGeneralCodigo = motivoDevolucion.getValor();
            this.maeDevolucionMotivoGeneralValor = motivoDevolucion.getNombre();
            this.maeDevolucionMotivoGeneralDescripcion =  motivoDevolucion.getDescripcion();
        }
    }
    
    public void castMaeMotivoDevolucionEspecifico(Maestro motivoDevolucion) {
        if (motivoDevolucion != null) {
            this.maeMotivoDevolucionId = motivoDevolucion.getId();
            this.maeMotivoDevolucionCodigo = motivoDevolucion.getValor();
            this.maeMotivoDevolucionValor = motivoDevolucion.getNombre();
        }
    }

    public CmFactura getCmFactura() {
        if(cmFactura == null){
            cmFactura = new CmFactura();
        }
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public Maestro getMaestroMotivoDevolucion() {
        return maestroMotivoDevolucion;
    }

    public void setMaestroMotivoDevolucion(Maestro maestroMotivoDevolucion) {
        this.maestroMotivoDevolucion = maestroMotivoDevolucion;
    }

    public Maestro getMaestroMotivoDevolucionEspecifico() {
        return maestroMotivoDevolucionEspecifico;
    }

    public void setMaestroMotivoDevolucionEspecifico(Maestro maestroMotivoDevolucionEspecifico) {
        this.maestroMotivoDevolucionEspecifico = maestroMotivoDevolucionEspecifico;
    }

    public Integer getMaeDevolucionMotivoGeneralId() {
        return maeDevolucionMotivoGeneralId;
    }

    public void setMaeDevolucionMotivoGeneralId(Integer maeDevolucionMotivoGeneralId) {
        this.maeDevolucionMotivoGeneralId = maeDevolucionMotivoGeneralId;
    }

    public String getMaeDevolucionMotivoGeneralCodigo() {
        return maeDevolucionMotivoGeneralCodigo;
    }

    public void setMaeDevolucionMotivoGeneralCodigo(String maeDevolucionMotivoGeneralCodigo) {
        this.maeDevolucionMotivoGeneralCodigo = maeDevolucionMotivoGeneralCodigo;
    }

    public String getMaeDevolucionMotivoGeneralValor() {
        return maeDevolucionMotivoGeneralValor;
    }

    public void setMaeDevolucionMotivoGeneralValor(String maeDevolucionMotivoGeneralValor) {
        this.maeDevolucionMotivoGeneralValor = maeDevolucionMotivoGeneralValor;
    }

    public String getMaeDevolucionMotivoGeneralDescripcion() {
        return maeDevolucionMotivoGeneralDescripcion;
    }

    public void setMaeDevolucionMotivoGeneralDescripcion(String maeDevolucionMotivoGeneralDescripcion) {
        this.maeDevolucionMotivoGeneralDescripcion = maeDevolucionMotivoGeneralDescripcion;
    }
  
    public Maestro getMaestroContrato() {
        return maestroContrato;
    }

    public void setMaestroContrato(Maestro maestroContrato) {
        this.maestroContrato = maestroContrato;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public CmDevolucionMasivaN getCmDevolucionMasiva() {
        return cmDevolucionMasiva;
    }

    public void setCmDevolucionMasiva(CmDevolucionMasivaN cmDevolucionMasiva) {
        this.cmDevolucionMasiva = cmDevolucionMasiva;
    }
    
    

    @Override
    public String toString() {
        return "CmAuditoriaDevolucion{" + "id=" + id + ", idCmAuditoriaDetalle=" + idCmAuditoriaDetalle + ", maeMotivoDevolucionId=" + maeMotivoDevolucionId + ", maeMotivoDevolucionCodigo=" + maeMotivoDevolucionCodigo + ", maeMotivoDevolucionValor=" + maeMotivoDevolucionValor + ", maeContratoModalidadId=" + maeContratoModalidadId + ", maeContratoModalidadCodigo=" + maeContratoModalidadCodigo + ", maeContratoModalidadValor=" + maeContratoModalidadValor + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", nit=" + nit + ", ips=" + ips + ", numeroRadicado=" + numeroRadicado + ", numeroFacturado=" + numeroFacturado + ", fechaRadicacion=" + fechaRadicacion + ", fechaDevolucion=" + fechaDevolucion + ", valorFactura=" + valorFactura + ", cmFactura id=" + (cmFactura != null ? cmFactura.getId() : "" ) + ", observacion=" + observacion + '}';
    }

   
      
}
