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
public class TuMemorialFirma extends Auditoria {

    private Integer id;
    private String nombre;
    private byte[] firma;
    private TuMemorialPersona tuMemorialPersonaId;

    public TuMemorialFirma() {

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

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public TuMemorialPersona getTuMemorialPersonaId() {
        return tuMemorialPersonaId;
    }

    public void setTuMemorialPersonaId(TuMemorialPersona tuMemorialPersonaId) {
        this.tuMemorialPersonaId = tuMemorialPersonaId;
    }

    @Override
    public String toString() {
        return "TuMemorialFirma{" + "id=" + id + ", nombre=" + nombre + ", firma=" + firma + ", tuMemorialPersonaId=" + tuMemorialPersonaId + '}';
    }

}
