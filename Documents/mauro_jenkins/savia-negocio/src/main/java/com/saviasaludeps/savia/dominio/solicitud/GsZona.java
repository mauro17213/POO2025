/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author jramirez
 */
public class GsZona extends Auditoria {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Boolean porDefecto;
    private Ubicacion ubicacion;
    private List<GsZonaUsuario> listaGsZonaUsuarios;
    private List<GsSolicitud> listaGsSolicitudes;

    public GsZona() {
    }

    public GsZona(Integer id) {
        this.id = id;
    }

    public GsZona(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public GsZona(Integer id, String nombre, String descripcion, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
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

    public Boolean getPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(Boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<GsZonaUsuario> getListaGsZonaUsuarios() {
        return listaGsZonaUsuarios;
    }

    public void setListaGsZonaUsuarios(List<GsZonaUsuario> listaGsZonaUsuarios) {
        this.listaGsZonaUsuarios = listaGsZonaUsuarios;
    }

    public List<GsSolicitud> getListaGsSolicitudes() {
        return listaGsSolicitudes;
    }

    public void setListaGsSolicitudes(List<GsSolicitud> listaGsSolicitudes) {
        this.listaGsSolicitudes = listaGsSolicitudes;
    }

    @Override
    public String toString() {
        return "GsZona{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", ubicacion=" + ubicacion + '}';
    }
}
