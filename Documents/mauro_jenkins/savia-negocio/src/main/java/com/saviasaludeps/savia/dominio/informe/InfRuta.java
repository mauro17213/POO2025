/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class InfRuta extends Auditoria {
    
    private Integer id;
    private String nombre;
    private String ruta;

    public InfRuta() {
    }

    public InfRuta(Integer id) {
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "InfRuta{" + "id=" + id + ", nombre=" + nombre + ", ruta=" + ruta + '}';
    }
    
}
