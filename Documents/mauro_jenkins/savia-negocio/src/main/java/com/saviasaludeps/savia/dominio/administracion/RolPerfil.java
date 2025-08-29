/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import java.io.Serializable;

/**
 *
 * @author Fabian Coronel
 */
public class RolPerfil implements Serializable {

    private Integer id;
    private Perfil perfiles;
    private Rol rolesId;

    public RolPerfil() {
    }

    public RolPerfil(Perfil perfiles) {
        this.perfiles = perfiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfil getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Perfil perfiles) {
        this.perfiles = perfiles;
    }

    public Rol getRolesId() {
        return rolesId;
    }

    public void setRolesId(Rol rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public String toString() {
        return "RolPerfil{" + "id=" + id + ", perfiles=" + perfiles + ", rolesId=" + rolesId + '}';
    }

}
