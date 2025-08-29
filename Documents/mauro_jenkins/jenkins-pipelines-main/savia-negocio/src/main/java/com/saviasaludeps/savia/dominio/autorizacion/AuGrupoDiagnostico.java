/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class AuGrupoDiagnostico extends Auditoria {
    
    private Integer id;
    private AuGrupo auGrupo;
    private int maDiagnosticoId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;

    public AuGrupoDiagnostico() {
    }

    public AuGrupoDiagnostico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuGrupo getAuGrupo() {
        return auGrupo;
    }

    public void setAuGrupo(AuGrupo auGrupo) {
        this.auGrupo = auGrupo;
    }

    public int getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(int maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    @Override
    public String toString() {
        return "AuGrupoDiagnostico{" + "id=" + id + ", maDiagnosticoId=" + maDiagnosticoId + ", maDiagnosticoCodigo=" + maDiagnosticoCodigo + ", maDiagnosticoValor=" + maDiagnosticoValor + '}';
    }
}
