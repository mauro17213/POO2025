/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class PeProgramaDiagnostico  extends Auditoria  {
    
    private Integer id;
    private PePrograma peProgramasId;
    private Integer maDiagnosticoId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;
    private Boolean borrado;
    private boolean marcaAutomatico; 
    private boolean direcciona;
    private boolean aplicaRescate;
    private Integer accion;
    
    private transient Integer sincronizado;
    private transient String programaCargaMasiva;
    private String errorCarga;
    private String registroArchivo;

    public PeProgramaDiagnostico() {
    }

    public PeProgramaDiagnostico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PePrograma getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PePrograma peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public boolean getMarcaAutomatico() {
        return marcaAutomatico;
    }

    public void setMarcaAutomatico(boolean marcaAutomatico) {
        this.marcaAutomatico = marcaAutomatico;
    }

    public boolean getDirecciona() {
        return direcciona;
    }

    public void setDirecciona(boolean direcciona) {
        this.direcciona = direcciona;
    }

    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getProgramaCargaMasiva() {
        return programaCargaMasiva;
    }

    public void setProgramaCargaMasiva(String programaCargaMasiva) {
        this.programaCargaMasiva = programaCargaMasiva;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    public String getRegistroArchivo() {
        return registroArchivo;
    }

    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    public boolean getAplicaRescate() {
        return aplicaRescate;
    }

    public void setAplicaRescate(boolean aplicaRescate) {
        this.aplicaRescate = aplicaRescate;
    }

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "PeProgramaDiagnostico{" + "id=" + id + ", peProgramasId=" + peProgramasId + ", maDiagnosticoId=" + maDiagnosticoId + ", maDiagnosticoCodigo=" + maDiagnosticoCodigo + ", maDiagnosticoValor=" + maDiagnosticoValor + ", borrado=" + borrado + ", marcaAutomatico=" + marcaAutomatico + ", direcciona=" + direcciona + ", aplicaRescate=" + aplicaRescate + ", sincronizado=" + sincronizado + ", programaCargaMasiva=" + programaCargaMasiva + ", errorCarga=" + errorCarga + ", registroArchivo=" + registroArchivo + '}';
    }
    
      

    
      
}
