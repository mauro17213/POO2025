/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class Anexo3AutorizacionesDTO  implements Serializable{

    private String consecutivo;
    private String tipoDocumento;
    private String documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;
    private String sexo;
    //acompañante
    private String nombreAcompaniante;
    private String apellidoAcompaniante;
    private String celularAcompaniante;
    private String telefonoAcompaniante;
    
    private String codigoServicioAtencion;
    private String ambito;
    private String numeroSolicitud;
    private String fechaOrdenMedica;
    private String codigoHabilitacionIPSSolicita;
    //medico
    private String tipoDocumentoMedico;
    private String documentoMedico;
    private String primerApellidoMedico;
    private String segundoApellidoMedico;
    private String primerNombreMedico;
    private String segundoNombreMedico;
    private String registroMedico;
    private String codigoEspecialidadMedica;
    private String nombreEspecialidadMedica;
    
    private String origenAtencion;
    private String prioridadAtencion;
    private String tipoServiciosSolicitados;
    private String ubicacionPaciente;
    private String tipoDiagnostico;
    private String diagnosticoPrincipal;
    private String diagnosticoRelacionado1;
    private String diagnosticoRelacionado2;
    private String justificacionClinica;
    //2024-04-16|lguerreroh| nuevos campos
    private String modalidadTecnologia;
    private String finalidadTecnologia;
    private String direccionAlternativa;
    private List<TecnologiaSolicitadaAnexo3DTO> tecnologiaSolicitada;
    private List<AdjuntoAnexo3DTO> adjuntos;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public String getUbicacionPaciente() {
        return ubicacionPaciente;
    }

    public void setUbicacionPaciente(String ubicacionPaciente) {
        this.ubicacionPaciente = ubicacionPaciente;
    }

    public String getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(String tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public String getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public String getDiagnosticoRelacionado1() {
        return diagnosticoRelacionado1;
    }

    public void setDiagnosticoRelacionado1(String diagnosticoRelacionado1) {
        this.diagnosticoRelacionado1 = diagnosticoRelacionado1;
    }

    public String getDiagnosticoRelacionado2() {
        return diagnosticoRelacionado2;
    }

    public void setDiagnosticoRelacionado2(String diagnosticoRelacionado2) {
        this.diagnosticoRelacionado2 = diagnosticoRelacionado2;
    }

    public String getTipoServiciosSolicitados() {
        return tipoServiciosSolicitados;
    }

    public void setTipoServiciosSolicitados(String tipoServiciosSolicitados) {
        this.tipoServiciosSolicitados = tipoServiciosSolicitados;
    }

    public String getPrioridadAtencion() {
        return prioridadAtencion;
    }

    public void setPrioridadAtencion(String prioridadAtencion) {
        this.prioridadAtencion = prioridadAtencion;
    }

    public String getCodigoHabilitacionIPSSolicita() {
        return codigoHabilitacionIPSSolicita;
    }

    public void setCodigoHabilitacionIPSSolicita(String codigoHabilitacionIPSSolicita) {
        this.codigoHabilitacionIPSSolicita = codigoHabilitacionIPSSolicita;
    }

    public String getOrigenAtencion() {
        return origenAtencion;
    }

    public void setOrigenAtencion(String origenAtencion) {
        this.origenAtencion = origenAtencion;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getFechaOrdenMedica() {
        return fechaOrdenMedica;
    }

    public void setFechaOrdenMedica(String fechaOrdenMedica) {
        this.fechaOrdenMedica = fechaOrdenMedica;
    }

    public List<TecnologiaSolicitadaAnexo3DTO> getTecnologiaSolicitada() {
        return tecnologiaSolicitada;
    }

    public void setTecnologiaSolicitada(List<TecnologiaSolicitadaAnexo3DTO> tecnologiaSolicitada) {
        this.tecnologiaSolicitada = tecnologiaSolicitada;
    }

    public List<AdjuntoAnexo3DTO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<AdjuntoAnexo3DTO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getNombreAcompaniante() {
        return nombreAcompaniante;
    }

    public void setNombreAcompaniante(String nombreAcompaniante) {
        this.nombreAcompaniante = nombreAcompaniante;
    }

    public String getCelularAcompaniante() {
        return celularAcompaniante;
    }

    public void setCelularAcompaniante(String celularAcompaniante) {
        this.celularAcompaniante = celularAcompaniante;
    }

    public String getCodigoServicioAtencion() {
        return codigoServicioAtencion;
    }

    public void setCodigoServicioAtencion(String codigoServicioAtencion) {
        this.codigoServicioAtencion = codigoServicioAtencion;
    }

    public String getTipoDocumentoMedico() {
        return tipoDocumentoMedico;
    }

    public void setTipoDocumentoMedico(String tipoDocumentoMedico) {
        this.tipoDocumentoMedico = tipoDocumentoMedico;
    }

    public String getDocumentoMedico() {
        return documentoMedico;
    }

    public void setDocumentoMedico(String documentoMedico) {
        this.documentoMedico = documentoMedico;
    }

    public String getPrimerApellidoMedico() {
        return primerApellidoMedico;
    }

    public void setPrimerApellidoMedico(String primerApellidoMedico) {
        this.primerApellidoMedico = primerApellidoMedico;
    }

    public String getSegundoApellidoMedico() {
        return segundoApellidoMedico;
    }

    public void setSegundoApellidoMedico(String segundoApellidoMedico) {
        this.segundoApellidoMedico = segundoApellidoMedico;
    }

    public String getPrimerNombreMedico() {
        return primerNombreMedico;
    }

    public void setPrimerNombreMedico(String primerNombreMedico) {
        this.primerNombreMedico = primerNombreMedico;
    }

    public String getSegundoNombreMedico() {
        return segundoNombreMedico;
    }

    public void setSegundoNombreMedico(String segundoNombreMedico) {
        this.segundoNombreMedico = segundoNombreMedico;
    }

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getCodigoEspecialidadMedica() {
        return codigoEspecialidadMedica;
    }

    public void setCodigoEspecialidadMedica(String codigoEspecialidadMedica) {
        this.codigoEspecialidadMedica = codigoEspecialidadMedica;
    }

    public String getNombreEspecialidadMedica() {
        return nombreEspecialidadMedica;
    }

    public void setNombreEspecialidadMedica(String nombreEspecialidadMedica) {
        this.nombreEspecialidadMedica = nombreEspecialidadMedica;
    }

    public String getTelefonoAcompaniante() {
        return telefonoAcompaniante;
    }

    public void setTelefonoAcompaniante(String telefonoAcompaniante) {
        this.telefonoAcompaniante = telefonoAcompaniante;
    }

    public String getApellidoAcompaniante() {
        return apellidoAcompaniante;
    }

    public void setApellidoAcompaniante(String apellidoAcompaniante) {
        this.apellidoAcompaniante = apellidoAcompaniante;
    }

    public String getModalidadTecnologia() {
        return modalidadTecnologia;
    }

    public void setModalidadTecnologia(String modalidadTecnologia) {
        this.modalidadTecnologia = modalidadTecnologia;
    }

    public String getFinalidadTecnologia() {
        return finalidadTecnologia;
    }

    public void setFinalidadTecnologia(String finalidadTecnologia) {
        this.finalidadTecnologia = finalidadTecnologia;
    }

    public String getDireccionAlternativa() {
        return direccionAlternativa;
    }

    public void setDireccionAlternativa(String direccionAlternativa) {
        this.direccionAlternativa = direccionAlternativa;
    }
    
}
