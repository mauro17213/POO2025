/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class TuPersonaContacto extends Auditoria  {

    private Integer id;
    private int posicion;
    private String numeroContacto;
    private String observacion;
    private TuPersona tuPersona;
    
    public TuPersonaContacto() {
    }

    public TuPersonaContacto(Integer id) {
        this.id = id;
    }

    public TuPersonaContacto(Integer id, String numeroContacto, String observacion) {
        this.id = id;
        this.numeroContacto = numeroContacto;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TuPersona getTuPersona() {
        if(tuPersona==null){
            tuPersona = new TuPersona();
        }
        return tuPersona;
    }

    public void setTuPersona(TuPersona tuPersona) {
        this.tuPersona = tuPersona;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
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
        if (!(object instanceof TuPersonaContacto)) {
            return false;
        }
        TuPersonaContacto other = (TuPersonaContacto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TuPersonasContacto{" + "id=" + id + ", numeroContacto=" + numeroContacto + ", observacion=" + observacion + '}';
    }
    
}
