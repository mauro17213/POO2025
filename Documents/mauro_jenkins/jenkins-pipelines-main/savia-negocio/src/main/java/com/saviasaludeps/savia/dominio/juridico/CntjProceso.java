
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class CntjProceso extends Auditoria{
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private Integer tipoProceso;

    public CntjProceso() {
    }

    public CntjProceso(Integer id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(Integer tipoProceso) {
        this.tipoProceso = tipoProceso;
    }
    
    
    public String getActivoStr(){
        if (this.activo) {
            return "Si";
        } else {
            return "No";
        }
    }
    
    

    @Override
    public String toString() {
        return "CntjProceso{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + ", tipoProceso=" + tipoProceso + '}';
    }

    
    
}
