/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class TuDiagnostico extends Auditoria{
    
    private Integer id;
    private int maDiagnosticosId;
    private String maDiagnosticosCodigo;
    private String maDiagnosticosValor;
    private int maeTipoDiagnosticoId;
    private String maeTipoDiagnosticoCodigo;
    private String maeTipoDiagnosticoValor;
    private TuTutelaItem tuTutelaItemsId;
    private boolean esPrincipal;
    private TuTutela tuTutelasId;
    private int pos;
    
    public TuDiagnostico(){
        
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

    public TuTutelaItem getTuTutelaItemsId() {
        return tuTutelaItemsId;
    }

    public void setTuTutelaItemsId(TuTutelaItem tuTutelaItemsId) {
        this.tuTutelaItemsId = tuTutelaItemsId;
    }

    public TuTutela getTuTutelasId() {
        return tuTutelasId;
    }

    public void setTuTutelasId(TuTutela tuTutelasId) {
        this.tuTutelasId = tuTutelasId;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
    
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    @Override
    public String toString() {
        return "TuDiagnostico{" + "id=" + id + ", maDiagnosticosId=" + maDiagnosticosId + ", maDiagnosticosCodigo=" + maDiagnosticosCodigo + ", maDiagnosticosValor=" + maDiagnosticosValor + ", maeTipoDiagnosticoId=" + maeTipoDiagnosticoId + ", maeTipoDiagnosticoCodigo=" + maeTipoDiagnosticoCodigo + ", maeTipoDiagnosticoValor=" + maeTipoDiagnosticoValor + ", tuTutelasId=" + tuTutelasId + '}';
    }
    
}
