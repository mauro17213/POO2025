/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class ReporteMemorial implements Serializable{
    private TuTutela tuTutelas;
    private TuTutelaEstado tuTutelasEstados;
    private TuMemorialPersona tuPersonaApoderado;
    private TuMemorialPersona tuPersonaAsistente;
    private TuMemorialFirma TuMemorialFirma;
    private Integer maeReferenciaId;
    private String maeReferenciaCodigo;
    private String maeReferenciaValor;
    private List<String> pretensiones;
    private List<String> argumentos;
    
    private Integer[] listaFiltrosPretensiones;
    private Integer[] listaFiltrosArgumentos;
    
    public TuTutela getTuTutelas() {
        if(tuTutelas == null){
            tuTutelas = new TuTutela();
        }
        return tuTutelas;
    }

    public void setTuTutelas(TuTutela tuTutelas) {
        this.tuTutelas = tuTutelas;
    }

    public TuTutelaEstado getTuTutelasEstados() {
        if(tuTutelasEstados == null){
            tuTutelasEstados = new TuTutelaEstado();
        }
        return tuTutelasEstados;
    }

    public void setTuTutelasEstados(TuTutelaEstado tuTutelasEstados) {
        this.tuTutelasEstados = tuTutelasEstados;
    }

    public TuMemorialPersona getTuPersonaApoderado() {
        if(tuPersonaApoderado == null){
            tuPersonaApoderado = new TuMemorialPersona();
        }
        return tuPersonaApoderado;
    }

    public void setTuPersonaApoderado(TuMemorialPersona tuPersonaApoderado) {
        this.tuPersonaApoderado = tuPersonaApoderado;
    }

    public TuMemorialPersona getTuPersonaAsistente() {
        if(tuPersonaAsistente == null){
            tuPersonaAsistente = new TuMemorialPersona();
        }
        return tuPersonaAsistente;
    }

    public void setTuPersonaAsistente(TuMemorialPersona tuPersonaAsistente) {
        this.tuPersonaAsistente = tuPersonaAsistente;
    }

    public TuMemorialFirma getTuMemorialFirma() {
        if(TuMemorialFirma == null){
            TuMemorialFirma = new TuMemorialFirma();
        }
        return TuMemorialFirma;
    }

    public void setTuMemorialFirma(TuMemorialFirma TuMemorialFirma) {
        this.TuMemorialFirma = TuMemorialFirma;
    }

    public Integer getMaeReferenciaId() {
        return maeReferenciaId;
    }

    public void setMaeReferenciaId(Integer maeReferenciaId) {
        this.maeReferenciaId = maeReferenciaId;
    }

    public String getMaeReferenciaCodigo() {
        return maeReferenciaCodigo;
    }

    public void setMaeReferenciaCodigo(String maeReferenciaCodigo) {
        this.maeReferenciaCodigo = maeReferenciaCodigo;
    }

    public String getMaeReferenciaValor() {
        return maeReferenciaValor;
    }

    public void setMaeReferenciaValor(String maeReferenciaValor) {
        this.maeReferenciaValor = maeReferenciaValor;
    }

    public List<String> getPretensiones() {
        if(pretensiones == null){
            pretensiones = new ArrayList<>();
        }
        return pretensiones;
    }

    public void setPretensiones(List<String> pretensiones) {
        this.pretensiones = pretensiones;
    }

    public List<String> getArgumentos() {
        if(argumentos == null){
            argumentos = new ArrayList<>();
        }
        return argumentos;
    }

    public void setArgumentos(List<String> argumentos) {
        this.argumentos = argumentos;
    }

    public Integer[] getListaFiltrosPretensiones() {
        return listaFiltrosPretensiones;
    }

    public void setListaFiltrosPretensiones(Integer[] listaFiltrosPretensiones) {
        this.listaFiltrosPretensiones = listaFiltrosPretensiones;
    }

    public Integer[] getListaFiltrosArgumentos() {
        return listaFiltrosArgumentos;
    }

    public void setListaFiltrosArgumentos(Integer[] listaFiltrosArgumentos) {
        this.listaFiltrosArgumentos = listaFiltrosArgumentos;
    }
    
}
