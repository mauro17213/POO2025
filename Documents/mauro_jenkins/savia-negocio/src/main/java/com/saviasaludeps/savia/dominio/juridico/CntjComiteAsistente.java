package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjComiteAsistente extends Auditoria {
    
    private Integer id;
    private boolean aprueba;
    private String observacion;   
    private CntjComite cntjComiteId;
    private Usuario usuarioId;

    public CntjComiteAsistente() {
    }

    public CntjComiteAsistente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAprueba() {
        return aprueba;
    }

    public void setAprueba(boolean aprueba) {
        this.aprueba = aprueba;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public CntjComite getCntjComiteId() {
        return cntjComiteId;
    }

    public void setCntjComiteId(CntjComite cntjComiteId) {
        this.cntjComiteId = cntjComiteId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "CntjComiteAsistente{" + "id=" + id + ", aprueba=" + aprueba + ", observacion=" + observacion + ", cntjComiteId=" + cntjComiteId + ", usuarioId=" + usuarioId + '}';
    }
    
}
