/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefAnexo9DatoClinico extends Auditoria {
    
    private Integer id;
    private Date fechaHoraDatos;
    private Integer triage;
    private String antecedentes;
    private String resumenClinico;
    private BigDecimal temperatura;
    private BigDecimal frecuenciaCardiaca;
    private BigDecimal tensionArtedialDiastole;
    private BigDecimal tensionArterialSistole;
    private BigDecimal frecuenciaRespiratoria;
    private BigDecimal saturacionOxigeno;
    private BigDecimal peso;
    private Integer talla;
    private BigDecimal perimetroAbdominal;
    private BigDecimal imc;
    private String hallazgosExamenFisico;
    private String examenesApoyoDiagnostico;
    private String tratamientoAplicado;
    private String motivoRemision;
    private Integer escalaGlasgow;
    private String sistemaNeurologico;
    private String sistemaSentidos;
    private String sistemaCardiovascular;
    private String sistemaRespiratorio;
    private String sistemaDigestivo;
    private String sistemaGenitoUrinario;
    private String sistemaOsteomuscular;
    private String observacionGeneral;
    private RefAnexo9 refAnexo9;

    public RefAnexo9DatoClinico() {
    }

    public RefAnexo9DatoClinico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaHoraDatos() {
        return fechaHoraDatos;
    }

    public void setFechaHoraDatos(Date fechaHoraDatos) {
        this.fechaHoraDatos = fechaHoraDatos;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getResumenClinico() {
        return resumenClinico;
    }

    public void setResumenClinico(String resumenClinico) {
        this.resumenClinico = resumenClinico;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public BigDecimal getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(BigDecimal frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public BigDecimal getTensionArtedialDiastole() {
        return tensionArtedialDiastole;
    }

    public void setTensionArtedialDiastole(BigDecimal tensionArtedialDiastole) {
        this.tensionArtedialDiastole = tensionArtedialDiastole;
    }

    public BigDecimal getTensionArterialSistole() {
        return tensionArterialSistole;
    }

    public void setTensionArterialSistole(BigDecimal tensionArterialSistole) {
        this.tensionArterialSistole = tensionArterialSistole;
    }

    public BigDecimal getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(BigDecimal frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public BigDecimal getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(BigDecimal saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public BigDecimal getPerimetroAbdominal() {
        return perimetroAbdominal;
    }

    public void setPerimetroAbdominal(BigDecimal perimetroAbdominal) {
        this.perimetroAbdominal = perimetroAbdominal;
    }

    public String getHallazgosExamenFisico() {
        return hallazgosExamenFisico;
    }

    public void setHallazgosExamenFisico(String hallazgosExamenFisico) {
        this.hallazgosExamenFisico = hallazgosExamenFisico;
    }

    public String getExamenesApoyoDiagnostico() {
        return examenesApoyoDiagnostico;
    }

    public void setExamenesApoyoDiagnostico(String examenesApoyoDiagnostico) {
        this.examenesApoyoDiagnostico = examenesApoyoDiagnostico;
    }

    public String getTratamientoAplicado() {
        return tratamientoAplicado;
    }

    public void setTratamientoAplicado(String tratamientoAplicado) {
        this.tratamientoAplicado = tratamientoAplicado;
    }

    public String getMotivoRemision() {
        return motivoRemision;
    }

    public void setMotivoRemision(String motivoRemision) {
        this.motivoRemision = motivoRemision;
    }

    public Integer getEscalaGlasgow() {
        return escalaGlasgow;
    }

    public void setEscalaGlasgow(Integer escalaGlasgow) {
        this.escalaGlasgow = escalaGlasgow;
    }

    public String getSistemaNeurologico() {
        return sistemaNeurologico;
    }

    public void setSistemaNeurologico(String sistemaNeurologico) {
        this.sistemaNeurologico = sistemaNeurologico;
    }

    public String getSistemaSentidos() {
        return sistemaSentidos;
    }

    public void setSistemaSentidos(String sistemaSentidos) {
        this.sistemaSentidos = sistemaSentidos;
    }

    public String getSistemaCardiovascular() {
        return sistemaCardiovascular;
    }

    public void setSistemaCardiovascular(String sistemaCardiovascular) {
        this.sistemaCardiovascular = sistemaCardiovascular;
    }

    public String getSistemaRespiratorio() {
        return sistemaRespiratorio;
    }

    public void setSistemaRespiratorio(String sistemaRespiratorio) {
        this.sistemaRespiratorio = sistemaRespiratorio;
    }

    public String getSistemaDigestivo() {
        return sistemaDigestivo;
    }

    public void setSistemaDigestivo(String sistemaDigestivo) {
        this.sistemaDigestivo = sistemaDigestivo;
    }

    public String getSistemaGenitoUrinario() {
        return sistemaGenitoUrinario;
    }

    public void setSistemaGenitoUrinario(String sistemaGenitoUrinario) {
        this.sistemaGenitoUrinario = sistemaGenitoUrinario;
    }

    public String getSistemaOsteomuscular() {
        return sistemaOsteomuscular;
    }

    public void setSistemaOsteomuscular(String sistemaOsteomuscular) {
        this.sistemaOsteomuscular = sistemaOsteomuscular;
    }

    public String getObservacionGeneral() {
        return observacionGeneral;
    }

    public void setObservacionGeneral(String observacionGeneral) {
        this.observacionGeneral = observacionGeneral;
    }

    public RefAnexo9 getRefAnexo9() {
        return refAnexo9;
    }

    public void setRefAnexo9(RefAnexo9 refAnexo9) {
        this.refAnexo9 = refAnexo9;
    }

    public Integer getTriage() {
        return triage;
    }

    public void setTriage(Integer triage) {
        this.triage = triage;
    }

    public BigDecimal getImc() {
        return imc;
    }

    public void setImc(BigDecimal imc) {
        this.imc = imc;
    }
    
    @Override
    public String toString() {
        
        return "RefAnexo9DatoClinico{" + "id=" + id + ", fechaHoraDatos=" + fechaHoraDatos + ", antecedentes=" + antecedentes + ", resumenClinico=" + resumenClinico + ", temperatura=" + temperatura + 
                ", frecuenciaCardiaca=" + frecuenciaCardiaca + ", tensionArtedialDiastole=" + tensionArtedialDiastole + ", tensionArterialSistole=" + tensionArterialSistole + 
                ", frecuenciaRespiratoria=" + frecuenciaRespiratoria + ", saturacionOxigeno=" + saturacionOxigeno + ", peso=" + peso + ", talla=" + talla + 
                ", perimetroAbdominal=" + perimetroAbdominal + ", hallazgosExamenFisico=" + hallazgosExamenFisico + ", examenesApoyoDiagnostico=" + examenesApoyoDiagnostico + 
                ", tratamientoAplicado=" + tratamientoAplicado + ", motivoRemision=" + motivoRemision + ", escalaGlasgow=" + escalaGlasgow + ", sistemaNeurologico=" + sistemaNeurologico + 
                ", sistemaSentidos=" + sistemaSentidos + ", sistemaCardiovascular=" + sistemaCardiovascular + ", sistemaRespiratorio=" + sistemaRespiratorio + ", sistemaDigestivo=" + sistemaDigestivo +
                ", sistemaGenitoUrinario=" + sistemaGenitoUrinario + ", sistemaOsteomuscular=" + sistemaOsteomuscular + ", observacionGeneral=" + observacionGeneral + 
                ", triage=" + triage + ", imc=" + imc + '}';
    }
    
}
