/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class TuRepresentante extends Auditoria{
   
    private Integer id;
    private String nombre;
    private boolean activo;
    
    public TuRepresentante(){
        
    }

    public TuRepresentante(Integer id) {
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
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getActivoStr() {
        return (isActivo()) ? "SI" : "NO";
    }

    @Override
    public String toString() {
        return "TuRepresentante{" + "id=" + id + ", nombre=" + nombre + ", activo=" + activo + '}';
    }
}
