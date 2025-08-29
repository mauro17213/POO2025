package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjTransicion extends Auditoria {

    private Integer id;
    private String nombre;
    private boolean activo;
    private CntjEstado cntjEstadoId;

    public CntjTransicion() {
        this.cntjEstadoId = new CntjEstado();
    }

    public CntjTransicion(Integer id) {
        this.id = id;
    }

    public CntjTransicion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getActivoStr(){
        if(activo){
            return "Si";
        }
        return "No";
    }

    public CntjEstado getCntjEstadoId() {
        return cntjEstadoId;
    }

    public void setCntjEstadoId(CntjEstado cntjEstadoId) {
        this.cntjEstadoId = cntjEstadoId;
    }

    @Override
    public String toString() {
        return "CntjTransicion{" + "id=" + id + ", nombre=" + nombre + ", activo=" + activo + ", cntjEstadoId=" + cntjEstadoId + '}';
    }
    
    

}
