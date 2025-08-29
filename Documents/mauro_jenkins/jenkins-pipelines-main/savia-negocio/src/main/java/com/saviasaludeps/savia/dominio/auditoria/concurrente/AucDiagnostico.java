/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class AucDiagnostico extends Auditoria{
    
    private Integer id;
    private int maeTipoDiagnosticoId;
    private String maeTipoDiagnosticoCodigo;
    private String maeTipoDiagnosticoValor;
    private boolean principal;
    private int maDiagnosticoId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;
    private AucEgreso aucEgresosId;
    private AucIngreso aucIngresosId;
    private AucHospitalizacion aucHospitalizacionId;
    private int pos;
    
    public AucDiagnostico (){
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public AucEgreso getAucEgresosId() {
        return aucEgresosId;
    }

    public void setAucEgresosId(AucEgreso aucEgresosId) {
        this.aucEgresosId = aucEgresosId;
    }

    public AucIngreso getAucIngresosId() {
        return aucIngresosId;
    }

    public void setAucIngresosId(AucIngreso aucIngresosId) {
        this.aucIngresosId = aucIngresosId;
    }

    public AucHospitalizacion getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizacion aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    @Override
    public String toString() {
        return "AucDiagnostico{" + "id=" + id + ", maeTipoDiagnosticoId=" + maeTipoDiagnosticoId + ", maeTipoDiagnosticoCodigo=" + maeTipoDiagnosticoCodigo + ", maeTipoDiagnosticoValor=" + maeTipoDiagnosticoValor + ", principal=" + principal + ", maDiagnosticoId=" + maDiagnosticoId + ", maDiagnosticoCodigo=" + maDiagnosticoCodigo + ", maDiagnosticoValor=" + maDiagnosticoValor + ", aucEgresosId=" + aucEgresosId + ", aucIngresosId=" + aucIngresosId + '}';
    }
    
}
