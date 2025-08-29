
package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jepn
 */

public class CmFeRipsValidacion extends Auditoria {


    private Integer id;
    private String nombreValidacion;
    private String descripcion;
    private boolean estado;
    private int idValidacion;

    public CmFeRipsValidacion() {
    }

    public CmFeRipsValidacion(Integer id) {
        this.id = id;
    }

    public CmFeRipsValidacion(Integer id, String nombreValidacion, String descripcion, boolean estado, int idValidacion) {
        this.id = id;
        this.nombreValidacion = nombreValidacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idValidacion = idValidacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreValidacion() {
        return nombreValidacion;
    }

    public void setNombreValidacion(String nombreValidacion) {
        this.nombreValidacion = nombreValidacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(int idValidacion) {
        this.idValidacion = idValidacion;
    }
    
     public String getEstadoStr() {
        if (this.estado) {
            return "Activo";
        } else {
            return "Inactivo";
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmFeRipsValidacion)) {
            return false;
        }
        CmFeRipsValidacion other = (CmFeRipsValidacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmFeRipsValidacion{" + "id=" + id + ", nombreValidacion=" + nombreValidacion + ", descripcion=" + descripcion + ", estado=" + estado + ", idValidacion=" + idValidacion + '}';
    }

 
    
}
