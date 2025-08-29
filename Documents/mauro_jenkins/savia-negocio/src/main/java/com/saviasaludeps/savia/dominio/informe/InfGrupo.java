
package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;


public class InfGrupo extends Auditoria{
    private Integer id;
    private String nombre;
    private String descripcion;
    private List<InfInforme> listaInformes;
    private List<InfGrupoUsuario> listaGrupoUsuarios;

    public InfGrupo() {
    }

    public InfGrupo(Integer id) {
        this.id = id;
    }

    public InfGrupo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<InfInforme> getListaInformes() {
        return listaInformes;
    }

    public void setListaInformes(List<InfInforme> listaInformes) {
        this.listaInformes = listaInformes;
    }

    public List<InfGrupoUsuario> getListaGrupoUsuarios() {
        return listaGrupoUsuarios;
    }

    public void setListaGrupoUsuarios(List<InfGrupoUsuario> listaGrupoUsuarios) {
        this.listaGrupoUsuarios = listaGrupoUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "InfGrupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", listaGrupoUsuarios=" + listaGrupoUsuarios + '}';
    }
    
}
