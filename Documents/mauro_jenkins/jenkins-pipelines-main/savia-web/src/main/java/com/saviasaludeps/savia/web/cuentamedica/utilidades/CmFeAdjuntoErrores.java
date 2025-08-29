/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.utilidades;

/**
 *
 * @author JEPEREZML-140741
 */
public class CmFeAdjuntoErrores {
    
    private String nombre = new String();
    private String descripcion = new String();

    public CmFeAdjuntoErrores() {
    }
    
    public CmFeAdjuntoErrores(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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
    
}
