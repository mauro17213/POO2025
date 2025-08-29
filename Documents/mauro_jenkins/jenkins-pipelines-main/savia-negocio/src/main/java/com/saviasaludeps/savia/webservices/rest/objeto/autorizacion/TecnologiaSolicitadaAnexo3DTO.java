/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class TecnologiaSolicitadaAnexo3DTO implements Serializable{

    private String tipoTecnologia;
    private String codigoServicioSolicitud;
    private String codigoTecnologia;
    private String cantidadTecnologia;
    private String indicacion;
    private Integer dosisMedicamento;
    private Integer frecuenciaMedicamento;
    private String viaAdministracion;
    private Integer duracionTratamiento;
    //2022-11-17|lguerrero| adicion de campos faltantes
    private String diagnosticoTecnologia;
    private String causaExternaMedicamento;
    private String finalidadMedicamento;
    private String tipoCatastroficoMedicamento;
    private String fechaFormulaMedicamento;
    
    public String getCodigoServicioSolicitud() {
        return codigoServicioSolicitud;
    }

    public void setCodigoServicioSolicitud(String codigoServicioSolicitud) {
        this.codigoServicioSolicitud = codigoServicioSolicitud;
    }

    public String getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(String tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public String getCantidadTecnologia() {
        return cantidadTecnologia;
    }

    public void setCantidadTecnologia(String cantidadTecnologia) {
        this.cantidadTecnologia = cantidadTecnologia;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    public Integer getDosisMedicamento() {
        return dosisMedicamento;
    }

    public void setDosisMedicamento(Integer dosisMedicamento) {
        this.dosisMedicamento = dosisMedicamento;
    }

    public Integer getFrecuenciaMedicamento() {
        return frecuenciaMedicamento;
    }

    public void setFrecuenciaMedicamento(Integer frecuenciaMedicamento) {
        this.frecuenciaMedicamento = frecuenciaMedicamento;
    }

    public String getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(String viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public String getDiagnosticoTecnologia() {
        return diagnosticoTecnologia;
    }

    public void setDiagnosticoTecnologia(String diagnosticoTecnologia) {
        this.diagnosticoTecnologia = diagnosticoTecnologia;
    }

    public String getCausaExternaMedicamento() {
        return causaExternaMedicamento;
    }

    public void setCausaExternaMedicamento(String causaExternaMedicamento) {
        this.causaExternaMedicamento = causaExternaMedicamento;
    }

    public String getFinalidadMedicamento() {
        return finalidadMedicamento;
    }

    public void setFinalidadMedicamento(String finalidadMedicamento) {
        this.finalidadMedicamento = finalidadMedicamento;
    }

    public String getTipoCatastroficoMedicamento() {
        return tipoCatastroficoMedicamento;
    }

    public void setTipoCatastroficoMedicamento(String tipoCatastroficoMedicamento) {
        this.tipoCatastroficoMedicamento = tipoCatastroficoMedicamento;
    }

    public String getFechaFormulaMedicamento() {
        return fechaFormulaMedicamento;
    }

    public void setFechaFormulaMedicamento(String fechaFormulaMedicamento) {
        this.fechaFormulaMedicamento = fechaFormulaMedicamento;
    }

}
