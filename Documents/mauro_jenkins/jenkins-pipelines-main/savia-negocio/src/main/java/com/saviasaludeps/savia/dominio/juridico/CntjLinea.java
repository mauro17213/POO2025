
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjLinea extends Auditoria{
    
    private Integer id;
    private Integer tipo;
    private Integer estado;
    private String descripcion;
    private String area;
    private String observacion;    
    private CntjComite cntjComiteId;
    private Usuario usuariosId;
    private CntjExpediente expedienteId;

    public CntjLinea() {
        this.expedienteId = new CntjExpediente();
        this.cntjComiteId = new CntjComite();
        this.usuariosId = new Usuario();
    }

    public CntjLinea(Integer id) {
        this.id = id;
        this.expedienteId = new CntjExpediente();
        this.cntjComiteId = new CntjComite();
        this.usuariosId = new Usuario();
    }

    public Integer getId() {
        return id;        
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Usuario getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuario usuariosId) {
        this.usuariosId = usuariosId;
    }

    public CntjExpediente getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(CntjExpediente expedienteId) {
        this.expedienteId = expedienteId;
    }

    @Override
    public String toString() {
        return "CntjLinea{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", descripcion=" + descripcion + ", area=" + area + ", observacion=" + observacion + ", cntjComiteId=" + cntjComiteId + ", usuariosId=" + usuariosId + ", expedienteId=" + expedienteId + '}';
    }
    
}
