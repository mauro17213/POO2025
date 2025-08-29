package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author StivenGV
 */
public class CntjEstadoEjecucion extends Auditoria {
    
    private Integer id;
    private CntjExpediente cntjExpedienteId;
    private CntjEstado cntjEstadoId;
    private Usuario gnUsuariosId;
    private CntjLinea cntjLineaId;
    private CntjDocumento cntjDocumentoId;
    private String observacion;
    private Date fechaEjecucion;
    private List<CntjExpedienteAuditoria> cntjExpedienteAuditoriaList;
    private String estadoSiguiente;

    public CntjEstadoEjecucion() {
        this.cntjEstadoId = new CntjEstado();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(Usuario gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }    

    public CntjDocumento getCntjDocumentoId() {
        return cntjDocumentoId;
    }

    public void setCntjDocumentoId(CntjDocumento cntjDocumentoId) {
        this.cntjDocumentoId = cntjDocumentoId;
    }

    public CntjEstado getCntjEstadoId() {
        return cntjEstadoId;
    }

    public void setCntjEstadoId(CntjEstado cntjEstadoId) {
        this.cntjEstadoId = cntjEstadoId;
    }

    public CntjExpediente getCntjExpedienteId() {
        return cntjExpedienteId;
    }

    public void setCntjExpedienteId(CntjExpediente cntjExpedienteId) {
        this.cntjExpedienteId = cntjExpedienteId;
    }

    public CntjLinea getCntjLineaId() {
        return cntjLineaId;
    }

    public void setCntjLineaId(CntjLinea cntjLineaId) {
        this.cntjLineaId = cntjLineaId;
    }

    public List<CntjExpedienteAuditoria> getCntjExpedienteAuditoriaList() {
        return cntjExpedienteAuditoriaList;
    }

    public void setCntjExpedienteAuditoriaList(List<CntjExpedienteAuditoria> cntjExpedienteAuditoriaList) {
        this.cntjExpedienteAuditoriaList = cntjExpedienteAuditoriaList;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getEstadoSiguiente() {
        return estadoSiguiente;
    }

    public void setEstadoSiguiente(String estadoSiguiente) {
        this.estadoSiguiente = estadoSiguiente;
    }

    @Override
    public String toString() {
        return "CntjEstadoEjecucion{" + "id=" + id + ", cntjExpedienteId=" + cntjExpedienteId + ", cntjEstadoId=" + cntjEstadoId + ", gnUsuariosId=" + gnUsuariosId + ", cntjLineaId=" + cntjLineaId + ", cntjDocumentoId=" + cntjDocumentoId + ", observacion=" + observacion + ", fechaEjecucion=" + fechaEjecucion + ", cntjExpedienteAuditoriaList=" + cntjExpedienteAuditoriaList + '}';
    }
    
}
