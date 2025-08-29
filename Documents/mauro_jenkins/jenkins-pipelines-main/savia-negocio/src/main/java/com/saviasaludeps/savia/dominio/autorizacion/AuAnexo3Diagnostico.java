/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo3Diagnostico extends Auditoria {

    private Integer id;
    private boolean principal;
    private int maDiagnosticosId;
    private String maDiagnosticosCodigo;
    private String maDiagnosticosValor;
    private int maeTipoDiagnosticoId;
    private String maeTipoDiagnosticoCodigo;
    private String maeTipoDiagnosticoValor;
    private AuAnexo3 auAnexos3Id;

    //Variables auxiliares
    private Maestro objetoTipoDiagnostico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
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

    public AuAnexo3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexo3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public Maestro getObjetoTipoDiagnostico() {
        return objetoTipoDiagnostico;
    }

    public void setObjetoTipoDiagnostico(Maestro objetoTipoDiagnostico) {
        this.objetoTipoDiagnostico = objetoTipoDiagnostico;
        setMaeTipoDiagnosticoId(objetoTipoDiagnostico.getId());
        setMaeTipoDiagnosticoCodigo(objetoTipoDiagnostico.getValor());
        setMaeTipoDiagnosticoValor(objetoTipoDiagnostico.getNombre());
    }

    
    @Override
    public String toString() {
        return "AuAnexo3Diagnostico{" + "id=" + id + ", principal=" + principal + ", maDiagnosticosId=" + maDiagnosticosId + ", maDiagnosticosCodigo=" + maDiagnosticosCodigo + ", maDiagnosticosValor=" + maDiagnosticosValor + ", maeTipoDiagnosticoId=" + maeTipoDiagnosticoId + ", maeTipoDiagnosticoCodigo=" + maeTipoDiagnosticoCodigo + ", maeTipoDiagnosticoValor=" + maeTipoDiagnosticoValor + '}';
    }

}
