/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jose perez
 */

public class AusGrupoUsuario extends Auditoria {

    private Integer id;
    private boolean activo;
    private Usuario usuario;
    private AusGrupo ausGrupo;
    
    public AusGrupoUsuario() {
    }

    public AusGrupoUsuario(Integer id) {
        this.id = id;
    }

    public AusGrupoUsuario(Integer id, boolean activo) {
        this.id = id;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ausGrupo
     */
    public AusGrupo getAusGrupo() {
        return ausGrupo;
    }

    /**
     * @param ausGrupo the ausGrupo to set
     */
    public void setAusGrupo(AusGrupo ausGrupo) {
        this.ausGrupo = ausGrupo;
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
        if (!(object instanceof AusGrupoUsuario)) {
            return false;
        }
        AusGrupoUsuario other = (AusGrupoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
