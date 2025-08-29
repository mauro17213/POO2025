/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jdlopez
 */
public class PeCierreCarga extends Auditoria {
    private Integer id;
    private String motivo;
    private int periodo;
    private Date fechaHoraDesde;
    private Date fechaHoraHasta;
    private PePrograma programa;

    public PeCierreCarga() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Date getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public void setFechaHoraDesde(Date fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public Date getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public void setFechaHoraHasta(Date fechaHoraHasta) {
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public PePrograma getPrograma() {
        return programa;
    }

    public void setPrograma(PePrograma programa) {
        this.programa = programa;
    }

    @Override
    public String toString() {
        return "PeCierreCarga{" + "id=" + id + ", motivo=" + motivo 
                + ", periodo=" + periodo + ", fechaHoraDesde=" + fechaHoraDesde
                + ", fechaHoraHasta=" + fechaHoraHasta + ", programa=" + (programa != null ? programa.getId() : null) + '}';
    }
    
    
}
