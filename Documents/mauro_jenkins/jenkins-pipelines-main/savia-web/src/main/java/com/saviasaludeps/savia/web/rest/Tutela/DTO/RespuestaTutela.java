/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.Tutela.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class RespuestaTutela {
    
    @SerializedName("numeroTutela")
    private String numeroTutela;
    @SerializedName("fechaNotificacion")
    private String fechaNotificacion;
    @SerializedName("fechaVencimiento")
    private String fechaVencimiento;
    @SerializedName("estadoProceso")
    private String estadoProceso;
    @SerializedName("tipoFase")
    private String tipoFase;
    @SerializedName("numeroRadicadoJuzgado")
    private String numeroRadicadoJuzgado;
    @SerializedName("numeroFallo")
    private String numeroFallo;
    @SerializedName("fechaFallo")
    private String fechaFallo;
    @SerializedName("servicios")
    private List<RespuestaServicio> servicios;
    @SerializedName("diagnosticoPrincipal")
    private RespuestaDiagnostico diagnosticoPrincipal;
    @SerializedName("diagnosticoRelacionado1")
    private RespuestaDiagnostico diagnosticoRelacionado1;
    @SerializedName("diagnosticoRelacionado2")
    private RespuestaDiagnostico diagnosticoRelacionado2;
    @SerializedName("exoneracionCopago")
    private String exoneracionCopago;
    @SerializedName("medidaProvisional")
    private String medidaProvisional;
    @SerializedName("integralidad")
    private String integralidad;
    @SerializedName("anexos")
    private List<RespuestaAnexo> anexos;

    public String getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    public List<RespuestaServicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<RespuestaServicio> servicios) {
        this.servicios = servicios;
    }

    public RespuestaDiagnostico getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(RespuestaDiagnostico diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public RespuestaDiagnostico getDiagnosticoRelacionado1() {
        return diagnosticoRelacionado1;
    }

    public void setDiagnosticoRelacionado1(RespuestaDiagnostico diagnosticoRelacionado1) {
        this.diagnosticoRelacionado1 = diagnosticoRelacionado1;
    }

    public RespuestaDiagnostico getDiagnosticoRelacionado2() {
        return diagnosticoRelacionado2;
    }

    public void setDiagnosticoRelacionado2(RespuestaDiagnostico diagnosticoRelacionado2) {
        this.diagnosticoRelacionado2 = diagnosticoRelacionado2;
    }

    public String getExoneracionCopago() {
        return exoneracionCopago;
    }

    public void setExoneracionCopago(String exoneracionCopago) {
        this.exoneracionCopago = exoneracionCopago;
    }

    public String getMedidaProvisional() {
        return medidaProvisional;
    }

    public void setMedidaProvisional(String medidaProvisional) {
        this.medidaProvisional = medidaProvisional;
    }

    public String getIntegralidad() {
        return integralidad;
    }

    public void setIntegralidad(String integralidad) {
        this.integralidad = integralidad;
    }

    public List<RespuestaAnexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<RespuestaAnexo> anexos) {
        this.anexos = anexos;
    }
    
    public List<RespuestaDiagnostico> obtenerListaDiagnosticos(){
        List<RespuestaDiagnostico> lista = new ArrayList<>();
        if(this.diagnosticoPrincipal != null){
            lista.add(getDiagnosticoPrincipal());
        }
        if(this.diagnosticoRelacionado1 != null){
            lista.add(getDiagnosticoRelacionado1());
        }
        if(this.diagnosticoRelacionado2 != null){
            lista.add(getDiagnosticoRelacionado2());
        }        
        return lista;
    }
    
    
}
