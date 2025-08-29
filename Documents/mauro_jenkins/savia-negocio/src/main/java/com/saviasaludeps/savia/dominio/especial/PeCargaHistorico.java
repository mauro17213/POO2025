/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author sgiraldo
 */
public class PeCargaHistorico extends Auditoria {
    
    public static final boolean NUEVO = true;
    public static final boolean ORIGINAL = false;
    
    private Integer id;
    private boolean tipo;
    private Integer maDiagnosticoPrincipalId;
    private String maDiagnosticoPrincipalCodigo;
    private String maDiagnosticoPrincipalValor;
    private Integer maDiagnostico2Id;
    private String maDiagnostico2Codigo;
    private String maDiagnostico2Valor;
    private Integer maDiagnostico3Id;
    private String maDiagnostico3Codigo;
    private String maDiagnostico3Valor;
    private Date fechaDiagnostico;
    private Date fechaInicioPrograma;
    private Date fechaFinPrograma;
    private Integer maeRegionCorporalId;
    private String maeRegionCorporalCodigo;
    private String maeRegionCorporalValor;
    private CntPrestadorSede cntPrestadorSedeId;
    private PeAfiliadosPrograma peAfiliadoProgramaId;
    private PeCarga peCargaId;

    public PeCargaHistorico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public Integer getMaDiagnostico2Id() {
        return maDiagnostico2Id;
    }

    public void setMaDiagnostico2Id(Integer maDiagnostico2Id) {
        this.maDiagnostico2Id = maDiagnostico2Id;
    }

    public String getMaDiagnostico2Codigo() {
        return maDiagnostico2Codigo;
    }

    public void setMaDiagnostico2Codigo(String maDiagnostico2Codigo) {
        this.maDiagnostico2Codigo = maDiagnostico2Codigo;
    }

    public String getMaDiagnostico2Valor() {
        return maDiagnostico2Valor;
    }

    public void setMaDiagnostico2Valor(String maDiagnostico2Valor) {
        this.maDiagnostico2Valor = maDiagnostico2Valor;
    }

    public Integer getMaDiagnostico3Id() {
        return maDiagnostico3Id;
    }

    public void setMaDiagnostico3Id(Integer maDiagnostico3Id) {
        this.maDiagnostico3Id = maDiagnostico3Id;
    }

    public String getMaDiagnostico3Codigo() {
        return maDiagnostico3Codigo;
    }

    public void setMaDiagnostico3Codigo(String maDiagnostico3Codigo) {
        this.maDiagnostico3Codigo = maDiagnostico3Codigo;
    }

    public String getMaDiagnostico3Valor() {
        return maDiagnostico3Valor;
    }

    public void setMaDiagnostico3Valor(String maDiagnostico3Valor) {
        this.maDiagnostico3Valor = maDiagnostico3Valor;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public Date getFechaInicioPrograma() {
        return fechaInicioPrograma;
    }

    public void setFechaInicioPrograma(Date fechaInicioPrograma) {
        this.fechaInicioPrograma = fechaInicioPrograma;
    }

    public Date getFechaFinPrograma() {
        return fechaFinPrograma;
    }

    public void setFechaFinPrograma(Date fechaFinPrograma) {
        this.fechaFinPrograma = fechaFinPrograma;
    }

    public Integer getMaeRegionCorporalId() {
        return maeRegionCorporalId;
    }

    public void setMaeRegionCorporalId(Integer maeRegionCorporalId) {
        this.maeRegionCorporalId = maeRegionCorporalId;
    }

    public String getMaeRegionCorporalCodigo() {
        return maeRegionCorporalCodigo;
    }

    public void setMaeRegionCorporalCodigo(String maeRegionCorporalCodigo) {
        this.maeRegionCorporalCodigo = maeRegionCorporalCodigo;
    }

    public String getMaeRegionCorporalValor() {
        return maeRegionCorporalValor;
    }

    public void setMaeRegionCorporalValor(String maeRegionCorporalValor) {
        this.maeRegionCorporalValor = maeRegionCorporalValor;
    }

    public CntPrestadorSede getCntPrestadorSedeId() {
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(CntPrestadorSede cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public PeAfiliadosPrograma getPeAfiliadoProgramaId() {
        return peAfiliadoProgramaId;
    }

    public void setPeAfiliadoProgramaId(PeAfiliadosPrograma peAfiliadoProgramaId) {
        this.peAfiliadoProgramaId = peAfiliadoProgramaId;
    }

    public PeCarga getPeCargaId() {
        return peCargaId;
    }

    public void setPeCargaId(PeCarga peCargaId) {
        this.peCargaId = peCargaId;
    }

    @Override
    public String toString() {
        return "PeCargaHistorico{" + "id=" + id + ", tipo=" + tipo + ", maDiagnosticoPrincipalId=" + maDiagnosticoPrincipalId + ", maDiagnosticoPrincipalCodigo=" + maDiagnosticoPrincipalCodigo + ", maDiagnosticoPrincipalValor=" + maDiagnosticoPrincipalValor + ", maDiagnostico2Id=" + maDiagnostico2Id + ", maDiagnostico2Codigo=" + maDiagnostico2Codigo + ", maDiagnostico2Valor=" + maDiagnostico2Valor + ", maDiagnostico3Id=" + maDiagnostico3Id + ", maDiagnostico3Codigo=" + maDiagnostico3Codigo + ", maDiagnostico3Valor=" + maDiagnostico3Valor + ", fechaDiagnostico=" + fechaDiagnostico + ", fechaInicioPrograma=" + fechaInicioPrograma + ", fechaFinPrograma=" + fechaFinPrograma + ", maeRegionCorporalId=" + maeRegionCorporalId + ", maeRegionCorporalCodigo=" + maeRegionCorporalCodigo + ", maeRegionCorporalValor=" + maeRegionCorporalValor + ", cntPrestadorSedeId=" + cntPrestadorSedeId + ", peAfiliadoProgramaId=" + peAfiliadoProgramaId + ", peCargaId=" + peCargaId + '}';
    }
    
}
