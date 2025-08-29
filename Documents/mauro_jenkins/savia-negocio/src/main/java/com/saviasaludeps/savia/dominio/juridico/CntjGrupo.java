
package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class CntjGrupo extends Auditoria{
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private List<CntjUsuarioGrupo> listaUsuarioGrupo;

    public CntjGrupo() {
        this.listaUsuarioGrupo = new ArrayList<>();
    }

    public CntjGrupo(Integer id) {
        this.id = id;
        this.listaUsuarioGrupo = new ArrayList<>();
    }

    public CntjGrupo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaUsuarioGrupo = new ArrayList<>();
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

    public String getActivoStr(){
        if (this.activo) {
            return "Si";
        } else {
            return "No";
        }
    }

    public List<CntjUsuarioGrupo> getListaUsuarioGrupo() {
        return listaUsuarioGrupo;
    }

    public void setListaUsuarioGrupo(List<CntjUsuarioGrupo> listaUsuarioGrupo) {
        this.listaUsuarioGrupo = listaUsuarioGrupo;
    }

    
    @Override
    public String toString() {
        return "CntjGrupo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + '}';
    }
    
    
}
