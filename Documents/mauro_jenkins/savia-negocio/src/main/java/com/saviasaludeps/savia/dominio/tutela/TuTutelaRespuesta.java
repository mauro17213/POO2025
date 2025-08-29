/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;


public class TuTutelaRespuesta extends Auditoria {

    private String numeroTutela;
    private String feachaNotificacion;
    private String feachaVencimiento;
    private String estado;
    private String estadoProceso;
    private String tipoFase;
    private String numeroRadicadoJuzgado;
    private String numeroFallo;
    private String fechaFallo;
    private List<TuTutelaItem> servicios;
    private List<TuDiagnostico> diagnosticos;
    private List<TuAdjunto> adjuntos;
    private String exoneracionCopago;
    private String integralidad;
    private String medidaProvisional;
    private boolean exoneracionTutela;

    public String getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public String getFeachaNotificacion() {
        return feachaNotificacion;
    }

    public void setFeachaNotificacion(String feachaNotificacion) {
        this.feachaNotificacion = feachaNotificacion;
    }

    public String getFeachaVencimiento() {
        return feachaVencimiento;
    }

    public void setFeachaVencimiento(String feachaVencimiento) {
        this.feachaVencimiento = feachaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public String getTipoFase() {
        return tipoFase;
    }

    public void setTipoFase(String tipoFase) {
        this.tipoFase = tipoFase;
    }

    public String getNumeroRadicadoJuzgado() {
        return numeroRadicadoJuzgado;
    }

    public void setNumeroRadicadoJuzgado(String numeroRadicadoJuzgado) {
        this.numeroRadicadoJuzgado = numeroRadicadoJuzgado;
    }

    public String getNumeroFallo() {
        return numeroFallo;
    }

    public void setNumeroFallo(String numeroFallo) {
        this.numeroFallo = numeroFallo;
    }

    public String getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(String fechaFallo) {
        this.fechaFallo = fechaFallo;
    }
    

    public List<TuTutelaItem> getServicios() {
        if (servicios == null) {
            servicios = new ArrayList<>();
        }
        return servicios;
    }

    public void setServicios(List<TuTutelaItem> servicios) {
        this.servicios = servicios;
    }

    public List<TuDiagnostico> getDiagnosticos() {
        if(diagnosticos==null){
            diagnosticos = new ArrayList<>();
        }
        return diagnosticos;
    }

    public void setDiagnosticos(List<TuDiagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public List<TuAdjunto> getAdjuntos() {
        if(adjuntos==null){
            adjuntos = new ArrayList<>();
        }
        return adjuntos;
    }

    public void setAdjuntos(List<TuAdjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getExoneracionCopago() {
        return exoneracionCopago;
    }

    public void setExoneracionCopago(String exoneracionCopago) {
        this.exoneracionCopago = exoneracionCopago;
    }

    public String getIntegralidad() {
        return integralidad;
    }

    public void setIntegralidad(String integralidad) {
        this.integralidad = integralidad;
    }

    public String getMedidaProvisional() {
        return medidaProvisional;
    }

    public void setMedidaProvisional(String medidaProvisional) {
        this.medidaProvisional = medidaProvisional;
    }

    public boolean isExoneracionTutela() {
        return exoneracionTutela;
    }

    public void setExoneracionTutela(boolean exoneracionTutela) {
        this.exoneracionTutela = exoneracionTutela;
    }
    
    
    @Override
    public String toString() {
        return "TuTutelaRespuesta{" + "numeroTutela=" + numeroTutela + ", feachaNotificacion=" + feachaNotificacion + ", feachaVencimiento=" + feachaVencimiento + ", estadoProceso=" + estadoProceso + ", tipoFase=" + tipoFase + ", numeroRadicadoJuzgado=" + numeroRadicadoJuzgado + ", numeroFallo=" + numeroFallo + ", fechaFallo=" + fechaFallo + ", servicios=" + servicios + ", diagnosticos=" + diagnosticos + ", adjuntos=" + adjuntos + ", exoneracionCopago=" + exoneracionCopago + ", integralidad=" + integralidad + ", medidaProvisional=" + medidaProvisional + '}';
    }
    
    
       
}
