/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author rpalacios
 */
public class GnConfiguracion extends Auditoria {
    
    private Integer id;
    private String nombre;
    private String valor;

    public GnConfiguracion() {
    }

    public GnConfiguracion(Integer id) {
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "GnConfiguracion{" + "id=" + id + ", nombre=" + nombre + ", valor=" + valor + '}';
    }
    
}
