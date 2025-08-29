package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

/**
 *
 * @author LFRIVERA
 */
public class AfiliadoDTO {

    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String ordenMedica;
    private String ambitoAtencion;
    private String fechaOrdenMedica;
    private String servicioAtencion;
    private String codigoHabilitacionSedeSolicita;
    private String tipoDocumentoProfesional;
    private String numeroDocumentoProfesional;
    private String primerNombreProfesional;
    private String segundoNombreProfesional;
    private String primerApellidoProfesional;
    private String segundoApellidoProfesional;
    private String registroMedico;
    private String especialidad;
    private String motivo; // 2025-07-30 lfriverad - Se agrega campo motivo
    private String diagnosticoPrincipal;
    private String altoCostoDiagnosticoPrincipal;
    private String diagnosticoRelacionado1;
    private String altoCostoDiagnosticoRelacionado1;
    private String diagnosticoRelacionado2;
    private String altoCostoDiagnosticoRelacionado2;
    private TecnologiaDTO tecnologias;
    private TecnologiaDTO entregaTecnologias;
    private String solicitudId;
    private String tutela;
    private String tipoFormula;

    public TecnologiaDTO getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(TecnologiaDTO tecnologias) {
        this.tecnologias = tecnologias;
    }

    public TecnologiaDTO getEntregaTecnologias() {
        return entregaTecnologias;
    }

    public void setEntregaTecnologias(TecnologiaDTO entregaTecnologias) {
        this.entregaTecnologias = entregaTecnologias;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getOrdenMedica() {
        return ordenMedica;
    }

    public void setOrdenMedica(String ordenMedica) {
        this.ordenMedica = ordenMedica;
    }

    public String getAmbitoAtencion() {
        return ambitoAtencion;
    }

    public void setAmbitoAtencion(String ambitoAtencion) {
        this.ambitoAtencion = ambitoAtencion;
    }

    public String getFechaOrdenMedica() {
        return fechaOrdenMedica;
    }

    public void setFechaOrdenMedica(String fechaOrdenMedica) {
        this.fechaOrdenMedica = fechaOrdenMedica;
    }

    public String getServicioAtencion() {
        return servicioAtencion;
    }

    public void setServicioAtencion(String servicioAtencion) {
        this.servicioAtencion = servicioAtencion;
    }

    public String getCodigoHabilitacionSedeSolicita() {
        return codigoHabilitacionSedeSolicita;
    }

    public void setCodigoHabilitacionSedeSolicita(String codigoHabilitacionSedeSolicita) {
        this.codigoHabilitacionSedeSolicita = codigoHabilitacionSedeSolicita;
    }

    public String getTipoDocumentoProfesional() {
        return tipoDocumentoProfesional;
    }

    public void setTipoDocumentoProfesional(String tipoDocumentoProfesional) {
        this.tipoDocumentoProfesional = tipoDocumentoProfesional;
    }

    public String getNumeroDocumentoProfesional() {
        return numeroDocumentoProfesional;
    }

    public void setNumeroDocumentoProfesional(String numeroDocumentoProfesional) {
        this.numeroDocumentoProfesional = numeroDocumentoProfesional;
    }

    public String getPrimerNombreProfesional() {
        return primerNombreProfesional;
    }

    public void setPrimerNombreProfesional(String primerNombreProfesional) {
        this.primerNombreProfesional = primerNombreProfesional;
    }

    public String getSegundoNombreProfesional() {
        return segundoNombreProfesional;
    }

    public void setSegundoNombreProfesional(String segundoNombreProfesional) {
        this.segundoNombreProfesional = segundoNombreProfesional;
    }

    public String getPrimerApellidoProfesional() {
        return primerApellidoProfesional;
    }

    public void setPrimerApellidoProfesional(String primerApellidoProfesional) {
        this.primerApellidoProfesional = primerApellidoProfesional;
    }

    public String getSegundoApellidoProfesional() {
        return segundoApellidoProfesional;
    }

    public void setSegundoApellidoProfesional(String segundoApellidoProfesional) {
        this.segundoApellidoProfesional = segundoApellidoProfesional;
    }

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public String getAltoCostoDiagnosticoPrincipal() {
        return altoCostoDiagnosticoPrincipal;
    }

    public void setAltoCostoDiagnosticoPrincipal(String altoCostoDiagnosticoPrincipal) {
        this.altoCostoDiagnosticoPrincipal = altoCostoDiagnosticoPrincipal;
    }

    public String getDiagnosticoRelacionado1() {
        return diagnosticoRelacionado1;
    }

    public void setDiagnosticoRelacionado1(String diagnosticoRelacionado1) {
        this.diagnosticoRelacionado1 = diagnosticoRelacionado1;
    }

    public String getAltoCostoDiagnosticoRelacionado1() {
        return altoCostoDiagnosticoRelacionado1;
    }

    public void setAltoCostoDiagnosticoRelacionado1(String altoCostoDiagnosticoRelacionado1) {
        this.altoCostoDiagnosticoRelacionado1 = altoCostoDiagnosticoRelacionado1;
    }

    public String getDiagnosticoRelacionado2() {
        return diagnosticoRelacionado2;
    }

    public void setDiagnosticoRelacionado2(String diagnosticoRelacionado2) {
        this.diagnosticoRelacionado2 = diagnosticoRelacionado2;
    }

    public String getAltoCostoDiagnosticoRelacionado2() {
        return altoCostoDiagnosticoRelacionado2;
    }

    public void setAltoCostoDiagnosticoRelacionado2(String altoCostoDiagnosticoRelacionado2) {
        this.altoCostoDiagnosticoRelacionado2 = altoCostoDiagnosticoRelacionado2;
    }

    public String getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(String solicitudId) {
        this.solicitudId = solicitudId;
    }

    public String getTutela() {
        return tutela;
    }

    public void setTutela(String tutela) {
        this.tutela = tutela;
    }

    public String getTipoFormula() {
        return tipoFormula;
    }

    public void setTipoFormula(String tipoFormula) {
        this.tipoFormula = tipoFormula;
    }
}
