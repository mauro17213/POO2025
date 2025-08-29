/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;

/**
 *
 * @author jperezn
 */
public class TuCargaFijo extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    private String nombre;
    private String valor;

    public TuCargaFijo() {
    }

    public TuCargaFijo(Integer id) {
        this.id = id;
    }

    public TuCargaFijo(Integer id, String nombre, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TuCargaFijo)) {
            return false;
        }
        TuCargaFijo other = (TuCargaFijo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuCargaFijos[ id=" + id + " ]";
    }

}
