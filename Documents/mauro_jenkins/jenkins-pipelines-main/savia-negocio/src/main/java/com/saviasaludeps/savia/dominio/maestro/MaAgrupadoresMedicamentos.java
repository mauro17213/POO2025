/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jarodriguez
 */
public class MaAgrupadoresMedicamentos extends Auditoria{
    
    private Integer id;
    private String codigo;
    private String nombre;
    
    public MaAgrupadoresMedicamentos(){
    }
    
    public MaAgrupadoresMedicamentos(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
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
        return "MaAgrupadoresMedicamento{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + "}";
    }
    
    
}
