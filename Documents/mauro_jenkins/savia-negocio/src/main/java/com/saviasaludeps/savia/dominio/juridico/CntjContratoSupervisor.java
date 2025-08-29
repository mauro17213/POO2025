package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjContratoSupervisor extends Auditoria {
    
    //ETAPAS
    public static final int ETAPA_PRECONTRACTUAL = 1;
    public static final int ETAPA_CONTRACTUAL = 2;
    
    private Integer id;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer etapaDesignacion;
    private CntjContrato cntjContratosId;
    private CntjTercero cntjTercerosId;
    
    private Integer estadoSupervision;
    private Integer informesPendientes;

    public CntjContratoSupervisor() {
    }

    public CntjContratoSupervisor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getEtapaDesignacion() {
        return etapaDesignacion;
    }

    public void setEtapaDesignacion(Integer etapaDesignacion) {
        this.etapaDesignacion = etapaDesignacion;
    }

    public CntjContrato getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContrato cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
    }

    public CntjTercero getCntjTercerosId() {
        return cntjTercerosId;
    }

    public void setCntjTercerosId(CntjTercero cntjTercerosId) {
        this.cntjTercerosId = cntjTercerosId;
    }

    public Integer getEstadoSupervision() {
        return estadoSupervision;
    }

    public void setEstadoSupervision(Integer estadoSupervision) {
        this.estadoSupervision = estadoSupervision;
    }

    public Integer getInformesPendientes() {
        return informesPendientes;
    }

    public void setInformesPendientes(Integer informesPendientes) {
        this.informesPendientes = informesPendientes;
    }
    
    //Auxiliares

    @Override
    public String toString() {
        return "CntjContratoSupervisor{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", etapaDesignacion=" + etapaDesignacion + ", cntjContratosId=" + cntjContratosId + ", cntjTercerosId=" + cntjTercerosId + ", estadoSupervision=" + estadoSupervision + ", informesPendientes=" + informesPendientes + '}';
    }

   
}
