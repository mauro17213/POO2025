package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjExpedienteResponsable extends Auditoria {

    private Integer id;
    private Usuario usuarioId;
    private CntjExpediente expedienteId;
    private Date fechaInicial;
    private Date fechaFinal;
    private Integer rol;

    public CntjExpedienteResponsable() {
        this.usuarioId = new Usuario();
        this.expedienteId = new CntjExpediente();
    }

    public CntjExpedienteResponsable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuariosId) {
        this.usuarioId = usuariosId;
    }

    public CntjExpediente getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(CntjExpediente expedienteId) {
        this.expedienteId = expedienteId;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
    
    

    @Override
    public String toString() {
        return "CntjExpedienteResponsable{" + "id=" + id + ", usuariosId=" + usuarioId + ", expedienteId=" + expedienteId + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + '}';
    }
    
}
