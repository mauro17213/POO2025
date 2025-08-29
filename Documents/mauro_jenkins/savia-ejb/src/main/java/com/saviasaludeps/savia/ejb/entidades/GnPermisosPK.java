/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author stive
 */
@Embeddable
public class GnPermisosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "gn_modulos_id")
    private int gnModulosId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gn_perfiles_id")
    private int gnPerfilesId;

    public GnPermisosPK() {
    }

    public GnPermisosPK(int gnModulosId, int gnPerfilesId) {
        this.gnModulosId = gnModulosId;
        this.gnPerfilesId = gnPerfilesId;
    }

    public int getGnModulosId() {
        return gnModulosId;
    }

    public void setGnModulosId(int gnModulosId) {
        this.gnModulosId = gnModulosId;
    }

    public int getGnPerfilesId() {
        return gnPerfilesId;
    }

    public void setGnPerfilesId(int gnPerfilesId) {
        this.gnPerfilesId = gnPerfilesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) gnModulosId;
        hash += (int) gnPerfilesId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GnPermisosPK)) {
            return false;
        }
        GnPermisosPK other = (GnPermisosPK) object;
        if (this.gnModulosId != other.gnModulosId) {
            return false;
        }
        if (this.gnPerfilesId != other.gnPerfilesId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnPermisosPK[ gnModulosId=" + gnModulosId + ", gnPerfilesId=" + gnPerfilesId + " ]";
    }
    
}
