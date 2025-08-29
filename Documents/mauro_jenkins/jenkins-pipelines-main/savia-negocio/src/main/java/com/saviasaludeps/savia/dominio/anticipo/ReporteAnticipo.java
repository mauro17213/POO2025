/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.anticipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NEXOS
 */
public class ReporteAnticipo implements Serializable{
    
    public static final String LISTA_ITEMS = "LISTA_ANTICIPOITEM";
    
    private String strFechaAnticipo;
    private String strFechaSolicitud;
    private String strSolicitante;
    private String strCedula;
    private String strAreaoDepencencia;
    private String strCargo;
    private BigDecimal bigTotalAnticipo;
    private String strDetalleAnticipo;
    private String strUsuario;
    private String strCedulaCodigoyNumeroCedula;
    private String strMaDiagnosticoValor;
    private String strMaDiagnosticoCodigo;
    private String strObservacion;
    private String strAreaSolicitante;
    private String strElaboradoPor;
    private BigDecimal bigValorCotizado;
    private String strRetencionSugerida;
    private BigDecimal bigValorDespuesRetencionSugerida;
    private List<ReporteAnticipoItem> listItems;
    
    public ReporteAnticipo(){
        
    }

    public String getStrFechaAnticipo() {
        return strFechaAnticipo;
    }

    public void setStrFechaAnticipo(String strFechaAnticipo) {
        this.strFechaAnticipo = strFechaAnticipo;
    }

    public String getStrFechaSolicitud() {
        return strFechaSolicitud;
    }

    public void setStrFechaSolicitud(String strFechaSolicitud) {
        this.strFechaSolicitud = strFechaSolicitud;
    }

    public String getStrSolicitante() {
        return strSolicitante;
    }

    public void setStrSolicitante(String strSolicitante) {
        this.strSolicitante = strSolicitante;
    }

    public String getStrCedula() {
        return strCedula;
    }

    public void setStrCedula(String strCedula) {
        this.strCedula = strCedula;
    }

    public String getStrAreaoDepencencia() {
        return strAreaoDepencencia;
    }

    public void setStrAreaoDepencencia(String strAreaoDepencencia) {
        this.strAreaoDepencencia = strAreaoDepencencia;
    }

    public String getStrCargo() {
        return strCargo;
    }

    public void setStrCargo(String strCargo) {
        this.strCargo = strCargo;
    }

    public BigDecimal getBigTotalAnticipo() {
        return bigTotalAnticipo;
    }

    public void setBigTotalAnticipo(BigDecimal bigTotalAnticipo) {
        this.bigTotalAnticipo = bigTotalAnticipo;
    }

    public String getStrDetalleAnticipo() {
        return strDetalleAnticipo;
    }

    public void setStrDetalleAnticipo(String strDetalleAnticipo) {
        this.strDetalleAnticipo = strDetalleAnticipo;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    public String getStrCedulaCodigoyNumeroCedula() {
        return strCedulaCodigoyNumeroCedula;
    }

    public void setStrCedulaCodigoyNumeroCedula(String strCedulaCodigoyNumeroCedula) {
        this.strCedulaCodigoyNumeroCedula = strCedulaCodigoyNumeroCedula;
    }

    public String getStrMaDiagnosticoValor() {
        return strMaDiagnosticoValor;
    }

    public void setStrMaDiagnosticoValor(String strMaDiagnosticoValor) {
        this.strMaDiagnosticoValor = strMaDiagnosticoValor;
    }

    public String getStrMaDiagnosticoCodigo() {
        return strMaDiagnosticoCodigo;
    }

    public void setStrMaDiagnosticoCodigo(String strMaDiagnosticoCodigo) {
        this.strMaDiagnosticoCodigo = strMaDiagnosticoCodigo;
    }

    public String getStrObservacion() {
        return strObservacion;
    }

    public void setStrObservacion(String strObservacion) {
        this.strObservacion = strObservacion;
    }

    public String getStrAreaSolicitante() {
        return strAreaSolicitante;
    }

    public void setStrAreaSolicitante(String strAreaSolicitante) {
        this.strAreaSolicitante = strAreaSolicitante;
    }

    public String getStrElaboradoPor() {
        return strElaboradoPor;
    }

    public void setStrElaboradoPor(String strElaboradoPor) {
        this.strElaboradoPor = strElaboradoPor;
    }

    public BigDecimal getBigValorCotizado() {
        return bigValorCotizado;
    }

    public void setBigValorCotizado(BigDecimal bigValorCotizado) {
        this.bigValorCotizado = bigValorCotizado;
    }

    public String getStrRetencionSugerida() {
        return strRetencionSugerida;
    }

    public void setStrRetencionSugerida(String strRetencionSugerida) {
        this.strRetencionSugerida = strRetencionSugerida;
    }

    public BigDecimal getBigValorDespuesRetencionSugerida() {
        return bigValorDespuesRetencionSugerida;
    }

    public void setBigValorDespuesRetencionSugerida(BigDecimal bigValorDespuesRetencionSugerida) {
        this.bigValorDespuesRetencionSugerida = bigValorDespuesRetencionSugerida;
    }

    public List<ReporteAnticipoItem> getListItems() {
        if(listItems == null){
            listItems = new ArrayList<>();
        }
        return listItems;
    }

    public void setListItems(List<ReporteAnticipoItem> listItems) {
        this.listItems = listItems;
    }
    
}
