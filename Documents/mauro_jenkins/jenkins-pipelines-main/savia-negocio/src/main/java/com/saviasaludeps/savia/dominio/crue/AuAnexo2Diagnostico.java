/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author AlexanderDiaz
 */
public class AuAnexo2Diagnostico extends Auditoria {

    private Integer id;
    private int maDiagnosticosId;
    private String maDiagnosticosCodigo;
    private String maDiagnosticosValor;
    private int maeTipoDiagnosticoId;
    private String maeTipoDiagnosticoCodigo;
    private String maeTipoDiagnosticoValor;
    private boolean principal;
    private AuAnexo2 auAnexo2;

    public AuAnexo2Diagnostico() {
    }

    public AuAnexo2Diagnostico(Integer id, String maDiagnosticosValor, boolean principal) {
        this.id = id;
        this.maDiagnosticosValor = maDiagnosticosValor;
        this.principal = principal;
    }

    public AuAnexo2Diagnostico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(int maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    public String getMaDiagnosticosCodigo() {
        return maDiagnosticosCodigo;
    }

    public void setMaDiagnosticosCodigo(String maDiagnosticosCodigo) {
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
    }

    public String getMaDiagnosticosValor() {
        return maDiagnosticosValor;
    }

    public void setMaDiagnosticosValor(String maDiagnosticosValor) {
        this.maDiagnosticosValor = maDiagnosticosValor;
    }

    public int getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(int maeTipoDiagnosticoId) {
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
    }

    public String getMaeTipoDiagnosticoCodigo() {
        return maeTipoDiagnosticoCodigo;
    }

    public void setMaeTipoDiagnosticoCodigo(String maeTipoDiagnosticoCodigo) {
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
    }

    public String getMaeTipoDiagnosticoValor() {
        return maeTipoDiagnosticoValor;
    }

    public void setMaeTipoDiagnosticoValor(String maeTipoDiagnosticoValor) {
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public AuAnexo2 getAuAnexo2() {
        return auAnexo2;
    }

    public void setAuAnexo2(AuAnexo2 auAnexo2) {
        this.auAnexo2 = auAnexo2;
    }

    @Override
    public String toString() {
        return "AuAnexo2Diagnostico{" + "id=" + id + ", maDiagnosticosId=" + maDiagnosticosId + ", maDiagnosticosCodigo=" + maDiagnosticosCodigo + ", maDiagnosticosValor=" + maDiagnosticosValor + ", maeTipoDiagnosticoId=" + maeTipoDiagnosticoId + ", maeTipoDiagnosticoCodigo=" + maeTipoDiagnosticoCodigo + ", maeTipoDiagnosticoValor=" + maeTipoDiagnosticoValor + ", principal=" + principal + '}';
    }

}
