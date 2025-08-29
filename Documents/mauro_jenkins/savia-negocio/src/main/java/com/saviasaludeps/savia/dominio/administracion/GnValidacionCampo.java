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
 * @author sgiraldov
 */
public class GnValidacionCampo extends Auditoria {
    
    private Integer id;
    private String validator;
    private String nombre;
    private String expresionRegular;
    private MaestroTipo gnMaestroTiposTipo;
    private List<Maestro> gnMaestrosList;
    
    public GnValidacionCampo() {
    }

    public GnValidacionCampo(Integer id) {
        this.id = id;
    }

    public GnValidacionCampo(Integer id, String validator, String nombre, String expresionRegular) {
        this.id = id;
        this.validator = validator;
        this.nombre = nombre;
        this.expresionRegular = expresionRegular;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public MaestroTipo getGnMaestroTiposTipo() {
        return gnMaestroTiposTipo;
    }

    public void setGnMaestroTiposTipo(MaestroTipo gnMaestroTiposTipo) {
        this.gnMaestroTiposTipo = gnMaestroTiposTipo;
    }

    public List<Maestro> getGnMaestrosList() {
        return gnMaestrosList;
    }

    public void setGnMaestrosList(List<Maestro> gnMaestrosList) {
        this.gnMaestrosList = gnMaestrosList;
    }

    @Override
    public String toString() {
        return "GnValidacionCampo{" + "id=" + id + ", validator=" + validator + ", nombre=" + nombre + ", expresionRegular=" + expresionRegular + '}';
    }
    
}
