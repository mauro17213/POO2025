/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import java.io.Serializable;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaDiagnosticoMedicamento implements Serializable {
 
    
    private Integer iddiagnosticoMedicamentos;
    private int maMedicamentosMaeTipoMedicamento;
    private boolean activo;
    private MaDiagnostico maDiagniostico;
    private MaMedicamento maMedicamento;

    public MaDiagnosticoMedicamento() {
    }

    public MaDiagnosticoMedicamento(Integer iddiagnosticoMedicamentos) {
        this.iddiagnosticoMedicamentos = iddiagnosticoMedicamentos;
    }

    public Integer getIddiagnosticoMedicamentos() {
        return iddiagnosticoMedicamentos;
    }

    public void setIddiagnosticoMedicamentos(Integer iddiagnosticoMedicamentos) {
        this.iddiagnosticoMedicamentos = iddiagnosticoMedicamentos;
    }

    public int getMaMedicamentosMaeTipoMedicamento() {
        return maMedicamentosMaeTipoMedicamento;
    }

    public void setMaMedicamentosMaeTipoMedicamento(int maMedicamentosMaeTipoMedicamento) {
        this.maMedicamentosMaeTipoMedicamento = maMedicamentosMaeTipoMedicamento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public MaDiagnostico getMaDiagniostico() {
        return maDiagniostico;
    }

    public void setMaDiagniostico(MaDiagnostico maDiagniostico) {
        this.maDiagniostico = maDiagniostico;
    }

    public MaMedicamento getMaMedicamento() {
        return maMedicamento;
    }

    public void setMaMedicamento(MaMedicamento maMedicamento) {
        this.maMedicamento = maMedicamento;
    }

    @Override
    public String toString() {
        return "MaDiagnosticoMedicamento{" + "iddiagnosticoMedicamentos=" + iddiagnosticoMedicamentos + "maMedicamentosMaeTipoMedicamento=" + maMedicamentosMaeTipoMedicamento 
                + "activo=" + activo + "maDiagniostico=" + maMedicamento + "id=" + maMedicamento;
    }
    
    
    
    
}
