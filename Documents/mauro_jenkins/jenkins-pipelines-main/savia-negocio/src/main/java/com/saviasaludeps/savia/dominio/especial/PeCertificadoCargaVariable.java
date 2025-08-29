/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import java.io.Serializable;

/**
 *
 * @author jdlopez
 */
public class PeCertificadoCargaVariable implements Serializable {
    private String strId;
    private String strNitSede;
    private String strNombreInstitucion;
    private String strMunicipio;
    private String strNombreArchivo;
    private String strRegistros;
    private String strPeriodo;
    private String strAnio;
    private String strFechaCargue;
    private String strHoraCargue;
    private String strFechaGeneracion;
    private String strHoraGeneracion;
    private String strCumplimiento;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrNitSede() {
        return strNitSede;
    }

    public void setStrNitSede(String strNitSede) {
        this.strNitSede = strNitSede;
    }

    public String getStrNombreInstitucion() {
        return strNombreInstitucion;
    }

    public void setStrNombreInstitucion(String strNombreInstitucion) {
        this.strNombreInstitucion = strNombreInstitucion;
    }

    public String getStrMunicipio() {
        return strMunicipio;
    }

    public void setStrMunicipio(String strMunicipio) {
        this.strMunicipio = strMunicipio;
    }

    public String getStrNombreArchivo() {
        return strNombreArchivo;
    }

    public void setStrNombreArchivo(String strNombreArchivo) {
        this.strNombreArchivo = strNombreArchivo;
    }

    public String getStrRegistros() {
        return strRegistros;
    }

    public void setStrRegistros(String strRegistros) {
        this.strRegistros = strRegistros;
    }

    public String getStrPeriodo() {
        return strPeriodo;
    }

    public void setStrPeriodo(String strPeriodo) {
        this.strPeriodo = strPeriodo;
    }

    public String getStrAnio() {
        return strAnio;
    }

    public void setStrAnio(String strAnio) {
        this.strAnio = strAnio;
    }

    public String getStrFechaCargue() {
        return strFechaCargue;
    }

    public void setStrFechaCargue(String strFechaCargue) {
        this.strFechaCargue = strFechaCargue;
    }

    public String getStrHoraCargue() {
        return strHoraCargue;
    }

    public void setStrHoraCargue(String strHoraCargue) {
        this.strHoraCargue = strHoraCargue;
    }

    public String getStrFechaGeneracion() {
        return strFechaGeneracion;
    }

    public void setStrFechaGeneracion(String strFechaGeneracion) {
        this.strFechaGeneracion = strFechaGeneracion;
    }

    public String getStrCumplimiento() {
        return strCumplimiento;
    }

    public void setStrCumplimiento(String strCumplimiento) {
        this.strCumplimiento = strCumplimiento;
    }

    public String getStrHoraGeneracion() {
        return strHoraGeneracion;
    }

    public void setStrHoraGeneracion(String strHoraGeneracion) {
        this.strHoraGeneracion = strHoraGeneracion;
    }

    @Override
    public String toString() {
        return "PeCertificadoCargaVariable{" + "strId=" + strId + ", strNitSede=" + strNitSede + ", strNombreInstitucion=" + strNombreInstitucion + ", strMunicipio=" + strMunicipio + ", strNombreArchivo=" + strNombreArchivo + ", strRegistros=" + strRegistros + ", strPeriodo=" + strPeriodo + ", strAnio=" + strAnio + ", strFechaCargue=" + strFechaCargue + ", strHoraCargue=" + strHoraCargue + ", strFechaGeneracion=" + strFechaGeneracion + ", strHoraGeneracion=" + strHoraGeneracion + ", strCumplimiento=" + strCumplimiento + '}';
    }
    
}
