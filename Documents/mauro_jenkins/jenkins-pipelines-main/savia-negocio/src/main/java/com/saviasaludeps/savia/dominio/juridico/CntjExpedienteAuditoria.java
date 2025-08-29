package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author StivenGV
 */
public class CntjExpedienteAuditoria extends Auditoria {
    
    private Integer id;
    private String observacion;
    private CntjEstadoEjecucion cntjEstadoEjecucionId;
    private CntjExpediente cntjExpedienteId;

    public CntjExpedienteAuditoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public CntjEstadoEjecucion getCntjEstadoEjecucionId() {
        return cntjEstadoEjecucionId;
    }

    public void setCntjEstadoEjecucionId(CntjEstadoEjecucion cntjEstadoEjecucionId) {
        this.cntjEstadoEjecucionId = cntjEstadoEjecucionId;
    }

    public CntjExpediente getCntjExpedienteId() {
        return cntjExpedienteId;
    }

    public void setCntjExpedienteId(CntjExpediente cntjExpedienteId) {
        this.cntjExpedienteId = cntjExpedienteId;
    }

    @Override
    public String toString() {
        return "CntjExpedienteAuditoria{" + "id=" + id + ", observacion=" + observacion + ", cntjEstadoEjecucionId=" + cntjEstadoEjecucionId + ", cntjExpedienteId=" + cntjExpedienteId + '}';
    }
    
    
    
}
