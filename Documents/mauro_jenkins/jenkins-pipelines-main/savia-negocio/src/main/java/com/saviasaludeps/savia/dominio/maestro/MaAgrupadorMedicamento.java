/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import java.io.Serializable;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaAgrupadorMedicamento implements Serializable {
    
    private Integer id;
    private String codigo;
    private String nombre;

    public MaAgrupadorMedicamento() {
    }

    public MaAgrupadorMedicamento(Integer id) {
        this.id = id;
    }
    
    public MaAgrupadorMedicamento(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "MaAgrupadorMedicamento{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
    
}
