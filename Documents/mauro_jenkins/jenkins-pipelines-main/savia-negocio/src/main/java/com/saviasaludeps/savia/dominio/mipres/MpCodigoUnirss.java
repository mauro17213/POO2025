/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class MpCodigoUnirss extends Auditoria {

    private Integer id;
    private String codigo;
    private String indicacion;

    public MpCodigoUnirss() {
    }

    public MpCodigoUnirss(Integer id) {
        this.id = id;
    }

    public MpCodigoUnirss(Integer id, String codigo, String indicacion) {
        this.id = id;
        this.codigo = codigo;
        this.indicacion = indicacion;
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

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    @Override
    public String toString() {
        return "MpCodigoUnirs{" + "id=" + id + ", codigo=" + codigo + ", indicacion=" + indicacion + '}';
    }

    
    
}
