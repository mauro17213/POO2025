/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class AsegAfiliadoHistorico extends Auditoria {

    private Integer id;
    private String tostringAfiliado;
    private AsegAfiliado asegAfiliado;

    public AsegAfiliadoHistorico() {
    }

    public AsegAfiliadoHistorico(Integer id) {
        this.id = id;
    }

    public AsegAfiliadoHistorico(Integer id, String tostringAfiliado) {
        this.id = id;
        this.tostringAfiliado = tostringAfiliado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTostringAfiliado() {
        return tostringAfiliado;
    }

    public void setTostringAfiliado(String tostringAfiliado) {
        this.tostringAfiliado = tostringAfiliado;
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
        if (!(object instanceof AsegAfiliadoHistorico)) {
            return false;
        }
        AsegAfiliadoHistorico other = (AsegAfiliadoHistorico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoHistoricos[ id=" + id + " ]";
    }

    /**
     * @return the asegAfiliado
     */
    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    /**
     * @param asegAfiliado the asegAfiliado to set
     */
    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }
    
}
