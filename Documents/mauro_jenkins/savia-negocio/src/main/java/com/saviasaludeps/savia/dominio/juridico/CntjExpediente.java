package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjExpediente extends Auditoria {
    
    private Integer id;
    private String estado;
    private String jsonData;
    private CntjProceso procesoId;
    private String numeroExpediente;
    private Date fechaEjecucionEstado;
    private String contrato;
    private CntjEstado estadoActual;
    private Usuario usuarioPropietario;
    private Usuario usuarioResponsable;
    private CntjExpediente expedienteId;

    public CntjExpediente() {
        this.procesoId = new CntjProceso();
    }

    public CntjExpediente(Integer id) {
        this.id = id;
        this.procesoId = new CntjProceso();
    }

    public CntjExpediente(Integer id, String numeroExpediente) {
        this.id = id;
        this.numeroExpediente = numeroExpediente;
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public CntjProceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(CntjProceso procesoId) {
        this.procesoId = procesoId;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Date getFechaEjecucionEstado() {
        return fechaEjecucionEstado;
    }

    public void setFechaEjecucionEstado(Date fechaEjecucionEstado) {
        this.fechaEjecucionEstado = fechaEjecucionEstado;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public CntjEstado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(CntjEstado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Usuario getUsuarioPropietario() {
        return usuarioPropietario;
    }

    public void setUsuarioPropietario(Usuario usuarioPropietario) {
        this.usuarioPropietario = usuarioPropietario;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public CntjExpediente getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(CntjExpediente expedienteId) {
        this.expedienteId = expedienteId;
    }

    @Override
    public String toString() {
        return "CntjExpediente{" + "id=" + id + ", estado=" + estado + ", jsonData=" + jsonData + ", procesoId=" + procesoId + ", numeroExpediente=" + numeroExpediente + ", fechaEjecucionEstado=" + fechaEjecucionEstado + ", contrato=" + contrato + ", estadoActual=" + estadoActual + ", usuarioPropietario=" + usuarioPropietario + ", usuarioResponsable=" + usuarioResponsable + ", expedienteId=" + expedienteId + '}';
    }
    
}
