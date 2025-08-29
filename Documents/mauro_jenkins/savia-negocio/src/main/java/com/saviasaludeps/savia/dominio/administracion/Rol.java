/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Fabian Coronel
 */
public class Rol extends Auditoria {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private boolean rolescol;
    private List<RolPerfil> rolesPerfilesList;

    public Rol() {
    }

    public Rol(Integer id) {
        this.id = id;
    }

    public Rol(Integer id, String nombre, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
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
    
    public String getActivoStr() {
        if(activo) {
            return "SI";
        } else {
            return "NO";
        }
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isRolescol() {
        return rolescol;
    }

    public void setRolescol(boolean rolescol) {
        this.rolescol = rolescol;
    }

    
    public List<RolPerfil> getRolesPerfilesList() {
        return rolesPerfilesList;
    }

    public void setRolesPerfilesList(List<RolPerfil> rolesPerfilesList) {
        this.rolesPerfilesList = rolesPerfilesList;
    }
    
    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + ", rolescol=" + rolescol + ", rolesPerfilesList=" + rolesPerfilesList + '}';
    }


}
