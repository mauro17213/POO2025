/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaRelacionTipo extends Auditoria {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private List<MaRelacion> maRelacionesList;
    
    public MaRelacionTipo() {
    }

    public MaRelacionTipo(Integer id) {
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

    public List<MaRelacion> getMaRelacionesList() {
        return maRelacionesList;
    }

    public void setMaRelacionesList(List<MaRelacion> maRelacionesList) {
        this.maRelacionesList = maRelacionesList;
    }

    @Override
    public String toString() {
        return "MaRelacionTipo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
}
