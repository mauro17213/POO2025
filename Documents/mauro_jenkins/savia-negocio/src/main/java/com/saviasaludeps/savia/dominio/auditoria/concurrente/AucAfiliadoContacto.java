/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class AucAfiliadoContacto extends Auditoria {
    
    private Integer id;
    private int posicion;
    private String numeroContacto;
    private String observacion;
    private AucAfiliado aucAfiliadoId;

    public AucAfiliadoContacto() {
    }

    public AucAfiliadoContacto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AucAfiliado getAucAfiliadoId() {
        return aucAfiliadoId;
    }

    public void setAucAfiliadoId(AucAfiliado aucAfiliadoId) {
        this.aucAfiliadoId = aucAfiliadoId;
    }

    @Override
    public String toString() {
        return "AucAfiliadoContacto{" + "id=" + id + ", numeroContacto=" + numeroContacto + ", observacion=" + observacion + ", aucAfiliadoId=" + aucAfiliadoId + '}';
    }
    
}
