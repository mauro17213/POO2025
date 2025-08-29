/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class GnZonaHoraria extends Auditoria {
    
    private Integer id;
    private String codigo;
    private String nombre;
    private BigDecimal utc;
    private List<Ubicacion> gnUbicacionList;
    private List<Usuario> gnUsuarioList;

    public GnZonaHoraria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getUtc() {
        return utc;
    }

    public void setUtc(BigDecimal utc) {
        this.utc = utc;
    }

    public List<Ubicacion> getGnUbicacionList() {
        return gnUbicacionList;
    }

    public void setGnUbicacionList(List<Ubicacion> gnUbicacionList) {
        this.gnUbicacionList = gnUbicacionList;
    }

    public List<Usuario> getGnUsuarioList() {
        return gnUsuarioList;
    }

    public void setGnUsuarioList(List<Usuario> gnUsuarioList) {
        this.gnUsuarioList = gnUsuarioList;
    }

    @Override
    public String toString() {
        return "GnZonaHoraria{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", utc=" + utc + '}';
    }
    
}
