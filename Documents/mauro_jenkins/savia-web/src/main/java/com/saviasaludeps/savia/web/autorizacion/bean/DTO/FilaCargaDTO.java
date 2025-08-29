package com.saviasaludeps.savia.web.autorizacion.bean.DTO;

import java.util.Date;

public class FilaCargaDTO {

    String consecutivo;
    String ambito;
    int codigoServicioAtencion;
    Date fechaOrden;
    String codigoHabilitacion;
    int origenAtencion;
    int prioridad;
    String servicioSolicitado;
    int ubicacionPaciente;
    String justificacion;
    String tipoDocumento;
    String numeroDocumento;
    String tipoDocProfesional;
    String numDocProfesional;
    String especialidad;
    String codigoDiagnostico;
    String tipoDiagnostico;
    int principal;
    int tipoTecnologia;
    String codigoTecnologia;
    int cantidadTecnologia;
    String codigoServicioTecnologia;
    int duracionTecnologia = 0;
    int dosisTecnologia = 0;
    String frecuenciaTecnologia;
    String tipoFrefcuenciaTecnologia;
    String viaTecnologia;
    int contador;
    String filaXLS;
    boolean anexado;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public int getCodigoServicioAtencion() {
        return codigoServicioAtencion;
    }

    public void setCodigoServicioAtencion(int codigoServicioAtencion) {
        this.codigoServicioAtencion = codigoServicioAtencion;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public int getOrigenAtencion() {
        return origenAtencion;
    }

    public void setOrigenAtencion(int origenAtencion) {
        this.origenAtencion = origenAtencion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getServicioSolicitado() {
        return servicioSolicitado;
    }

    public void setServicioSolicitado(String servicioSolicitado) {
        this.servicioSolicitado = servicioSolicitado;
    }

    public int getUbicacionPaciente() {
        return ubicacionPaciente;
    }

    public void setUbicacionPaciente(int ubicacionPaciente) {
        this.ubicacionPaciente = ubicacionPaciente;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocProfesional() {
        return tipoDocProfesional;
    }

    public void setTipoDocProfesional(String tipoDocProfesional) {
        this.tipoDocProfesional = tipoDocProfesional;
    }

    public String getNumDocProfesional() {
        return numDocProfesional;
    }

    public void setNumDocProfesional(String numDocProfesional) {
        this.numDocProfesional = numDocProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCodigoDiagnostico() {
        return codigoDiagnostico;
    }

    public void setCodigoDiagnostico(String codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }

    public String getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(String tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public int getCantidadTecnologia() {
        return cantidadTecnologia;
    }

    public void setCantidadTecnologia(int cantidadTecnologia) {
        this.cantidadTecnologia = cantidadTecnologia;
    }

    public String getCodigoServicioTecnologia() {
        return codigoServicioTecnologia;
    }

    public void setCodigoServicioTecnologia(String codigoServicioTecnologia) {
        this.codigoServicioTecnologia = codigoServicioTecnologia;
    }

    public int getDuracionTecnologia() {
        return duracionTecnologia;
    }

    public void setDuracionTecnologia(int duracionTecnologia) {
        this.duracionTecnologia = duracionTecnologia;
    }

    public int getDosisTecnologia() {
        return dosisTecnologia;
    }

    public void setDosisTecnologia(int dosisTecnologia) {
        this.dosisTecnologia = dosisTecnologia;
    }

    public String getFrecuenciaTecnologia() {
        return frecuenciaTecnologia;
    }

    public void setFrecuenciaTecnologia(String frecuenciaTecnologia) {
        this.frecuenciaTecnologia = frecuenciaTecnologia;
    }

    public String getTipoFrefcuenciaTecnologia() {
        return tipoFrefcuenciaTecnologia;
    }

    public void setTipoFrefcuenciaTecnologia(String tipoFrefcuenciaTecnologia) {
        this.tipoFrefcuenciaTecnologia = tipoFrefcuenciaTecnologia;
    }

    public String getViaTecnologia() {
        return viaTecnologia;
    }

    public void setViaTecnologia(String viaTecnologia) {
        this.viaTecnologia = viaTecnologia;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getFilaXLS() {
        return filaXLS;
    }

    public void setFilaXLS(String filaXLS) {
        this.filaXLS = filaXLS;
    }

    public boolean isAnexado() {
        return anexado;
    }

    public void setAnexado(boolean anexado) {
        this.anexado = anexado;
    }

    @Override
    public String toString() {
        return "ExcelCargaDTO{" + "ambito=" + ambito + ", codigoServicioAtencion=" + codigoServicioAtencion + ", codigoHabilitacion=" + codigoHabilitacion + ", prioridad=" + prioridad + ", ubicacionPaciente=" + ubicacionPaciente + ", justificacion=" + justificacion + ", numeroDocumento=" + numeroDocumento + ", tipoDocProfesional=" + tipoDocProfesional + ", numDocProfesional=" + numDocProfesional + ", especialidad=" + especialidad + ", codigoDiagnostico=" + codigoDiagnostico + ", tipoDiagnostico=" + tipoDiagnostico + ", tipoTecnologia=" + tipoTecnologia + '}';
    }

}
