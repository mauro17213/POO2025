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
 * @author rpalacic
 */
public class Perfil extends Auditoria {

    public Perfil() {
    }

    public Perfil(Integer id) {
        this.id = id;
    }

    public Perfil(Integer id, String nombre, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private List<Modulo> modulos;
    private List<RolPerfil> rolesPerfilesList;
    
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
    public String getDescripcionCorta() {
        if(descripcion!=null && descripcion.length()>32){
            return descripcion.substring(0, 32);
        }else{
            return descripcion;
        }
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public String getActivoStr() {
        if(activo) return "SI";
            else return "NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }
    
    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<RolPerfil> getRolesPerfilesList() {
        return rolesPerfilesList;
    }

    public void setRolesPerfilesList(List<RolPerfil> rolesPerfilesList) {
        this.rolesPerfilesList = rolesPerfilesList;
    }
    
    @Override
    public String toString() {
        return "Perfil{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + getActivoStr() + '}';
    }
    
}
