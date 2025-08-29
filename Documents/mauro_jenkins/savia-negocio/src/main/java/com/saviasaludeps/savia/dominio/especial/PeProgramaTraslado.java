/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jhohan Lopez
 */
public class PeProgramaTraslado extends Auditoria {
    private Integer id;
    private AsegAfiliado afiliadoAseg;
    private PeAfiliadosPrograma afiliadoPrograma;
    private PePrograma programaOrigen;
    private PePrograma programaDestino;
    private String observacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AsegAfiliado getAfiliadoAseg() {
        return afiliadoAseg;
    }

    public void setAfiliadoAseg(AsegAfiliado afiliadoAseg) {
        this.afiliadoAseg = afiliadoAseg;
    }

    public PeAfiliadosPrograma getAfiliadoPrograma() {
        return afiliadoPrograma;
    }

    public void setAfiliadoPrograma(PeAfiliadosPrograma afiliadoPrograma) {
        this.afiliadoPrograma = afiliadoPrograma;
    }

    public PePrograma getProgramaOrigen() {
        return programaOrigen;
    }

    public void setProgramaOrigen(PePrograma programaOrigen) {
        this.programaOrigen = programaOrigen;
    }

    public PePrograma getProgramaDestino() {
        return programaDestino;
    }

    public void setProgramaDestino(PePrograma programaDestino) {
        this.programaDestino = programaDestino;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String Observacion) {
        this.observacion = Observacion;
    }

    @Override
    public String toString() {
        return "PeProgramaTraslado{" + "id=" + id + ", afiliadoAseg= " + (afiliadoAseg != null ? afiliadoAseg.getId() : null) + ", afiliadoPrograma=" + (afiliadoPrograma != null ? afiliadoPrograma.getId() : null) 
                + ", programaOrigen=" + (programaOrigen != null ? programaOrigen.getId() : null) + ", programaDestino=" + (programaDestino != null ? programaDestino.getId() : null) + ", observacion=" + observacion + '}';
    }
    
    
}
