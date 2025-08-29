package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author stive
 */
public class ReportePlanManejo extends Auditoria {

    private String strFecha;
    private String strHora;
    private String strNumeroPrescripcion;
    private String strDepartamento;
    private String strMunicipio;
    private String strCodigoHabilitacion;
    private String strNumeroDocumentoPrestador;
    private String strNombrePrestador;
    private String strDireccion;
    private String strTelefono;
    private String strNumeroDocumentoPaciente;
    private String strNombre;
    private String strNumeroHistoria;
    private String strDiagnosticoPrincipal;
    private String strRegimen;
    private String strAmbito;
    private String strTipoPrestacion;
    private String strServicioComplementario;
    private String strIndicaciones;
    private String strCantidad;
    private String strFrecuencia;
    private String strDuracion;
    private String strCantidadTotal;
    private String strNumeroDocumentoProfesional;
    private String strNombreProfesional;
    private String strRegistroProfesional;
    private String strEspecialidad;

    public ReportePlanManejo() {
    }

    public ReportePlanManejo(String strFecha, String strHora, String strNumeroPrescripcion, String strDepartamento, String strMunicipio, String strCodigoHabilitacion, String strNumeroDocumentoPrestador, String strNombrePrestador, String strDireccion, String strTelefono, String strNumeroDocumentoPaciente, String strNombre, String strNumeroHistoria, String strDiagnosticoPrincipal, String strRegimen, String strAmbito, String strTipoPrestacion, String strServicioComplementario, String strIndicaciones, String strCantidad, String strFrecuencia, String strDuracion, String strCantidadTotal, String strNumeroDocumentoProfesional, String strNombreProfesional, String strRegistroProfesional, String strEspecialidad) {
        this.strFecha = strFecha;
        this.strHora = strHora;
        this.strNumeroPrescripcion = strNumeroPrescripcion;
        this.strDepartamento = strDepartamento;
        this.strMunicipio = strMunicipio;
        this.strCodigoHabilitacion = strCodigoHabilitacion;
        this.strNumeroDocumentoPrestador = strNumeroDocumentoPrestador;
        this.strNombrePrestador = strNombrePrestador;
        this.strDireccion = strDireccion;
        this.strTelefono = strTelefono;
        this.strNumeroDocumentoPaciente = strNumeroDocumentoPaciente;
        this.strNombre = strNombre;
        this.strNumeroHistoria = strNumeroHistoria;
        this.strDiagnosticoPrincipal = strDiagnosticoPrincipal;
        this.strRegimen = strRegimen;
        this.strAmbito = strAmbito;
        this.strTipoPrestacion = strTipoPrestacion;
        this.strServicioComplementario = strServicioComplementario;
        this.strIndicaciones = strIndicaciones;
        this.strCantidad = strCantidad;
        this.strFrecuencia = strFrecuencia;
        this.strDuracion = strDuracion;
        this.strCantidadTotal = strCantidadTotal;
        this.strNumeroDocumentoProfesional = strNumeroDocumentoProfesional;
        this.strNombreProfesional = strNombreProfesional;
        this.strRegistroProfesional = strRegistroProfesional;
        this.strEspecialidad = strEspecialidad;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getStrHora() {
        return strHora;
    }

    public void setStrHora(String strHora) {
        this.strHora = strHora;
    }

    public String getStrNumeroPrescripcion() {
        return strNumeroPrescripcion;
    }

    public void setStrNumeroPrescripcion(String strNumeroPrescripcion) {
        this.strNumeroPrescripcion = strNumeroPrescripcion;
    }

    public String getStrDepartamento() {
        return strDepartamento;
    }

    public void setStrDepartamento(String strDepartamento) {
        this.strDepartamento = strDepartamento;
    }

    public String getStrMunicipio() {
        return strMunicipio;
    }

    public void setStrMunicipio(String strMunicipio) {
        this.strMunicipio = strMunicipio;
    }

    public String getStrCodigoHabilitacion() {
        return strCodigoHabilitacion;
    }

    public void setStrCodigoHabilitacion(String strCodigoHabilitacion) {
        this.strCodigoHabilitacion = strCodigoHabilitacion;
    }

    public String getStrNumeroDocumentoPrestador() {
        return strNumeroDocumentoPrestador;
    }

    public void setStrNumeroDocumentoPrestador(String strNumeroDocumentoPrestador) {
        this.strNumeroDocumentoPrestador = strNumeroDocumentoPrestador;
    }

    public String getStrNombrePrestador() {
        return strNombrePrestador;
    }

    public void setStrNombrePrestador(String strNombrePrestador) {
        this.strNombrePrestador = strNombrePrestador;
    }

    public String getStrDireccion() {
        return strDireccion;
    }

    public void setStrDireccion(String strDireccion) {
        this.strDireccion = strDireccion;
    }

    public String getStrTelefono() {
        return strTelefono;
    }

    public void setStrTelefono(String strTelefono) {
        this.strTelefono = strTelefono;
    }

    public String getStrNumeroDocumentoPaciente() {
        return strNumeroDocumentoPaciente;
    }

    public void setStrNumeroDocumentoPaciente(String strNumeroDocumentoPaciente) {
        this.strNumeroDocumentoPaciente = strNumeroDocumentoPaciente;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrNumeroHistoria() {
        return strNumeroHistoria;
    }

    public void setStrNumeroHistoria(String strNumeroHistoria) {
        this.strNumeroHistoria = strNumeroHistoria;
    }

    public String getStrDiagnosticoPrincipal() {
        return strDiagnosticoPrincipal;
    }

    public void setStrDiagnosticoPrincipal(String strDiagnosticoPrincipal) {
        this.strDiagnosticoPrincipal = strDiagnosticoPrincipal;
    }

    public String getStrRegimen() {
        return strRegimen;
    }

    public void setStrRegimen(String strRegimen) {
        this.strRegimen = strRegimen;
    }

    public String getStrAmbito() {
        return strAmbito;
    }

    public void setStrAmbito(String strAmbito) {
        this.strAmbito = strAmbito;
    }

    public String getStrTipoPrestacion() {
        return strTipoPrestacion;
    }

    public void setStrTipoPrestacion(String strTipoPrestacion) {
        this.strTipoPrestacion = strTipoPrestacion;
    }

    public String getStrServicioComplementario() {
        return strServicioComplementario;
    }

    public void setStrServicioComplementario(String strServicioComplementario) {
        this.strServicioComplementario = strServicioComplementario;
    }

    public String getStrIndicaciones() {
        return strIndicaciones;
    }

    public void setStrIndicaciones(String strIndicaciones) {
        this.strIndicaciones = strIndicaciones;
    }

    public String getStrCantidad() {
        return strCantidad;
    }

    public void setStrCantidad(String strCantidad) {
        this.strCantidad = strCantidad;
    }

    public String getStrFrecuencia() {
        return strFrecuencia;
    }

    public void setStrFrecuencia(String strFrecuencia) {
        this.strFrecuencia = strFrecuencia;
    }

    public String getStrDuracion() {
        return strDuracion;
    }

    public void setStrDuracion(String strDuracion) {
        this.strDuracion = strDuracion;
    }

    public String getStrCantidadTotal() {
        return strCantidadTotal;
    }

    public void setStrCantidadTotal(String strCantidadTotal) {
        this.strCantidadTotal = strCantidadTotal;
    }

    public String getStrNumeroDocumentoProfesional() {
        return strNumeroDocumentoProfesional;
    }

    public void setStrNumeroDocumentoProfesional(String strNumeroDocumentoProfesional) {
        this.strNumeroDocumentoProfesional = strNumeroDocumentoProfesional;
    }

    public String getStrNombreProfesional() {
        return strNombreProfesional;
    }

    public void setStrNombreProfesional(String strNombreProfesional) {
        this.strNombreProfesional = strNombreProfesional;
    }

    public String getStrRegistroProfesional() {
        return strRegistroProfesional;
    }

    public void setStrRegistroProfesional(String strRegistroProfesional) {
        this.strRegistroProfesional = strRegistroProfesional;
    }

    public String getStrEspecialidad() {
        return strEspecialidad;
    }

    public void setStrEspecialidad(String strEspecialidad) {
        this.strEspecialidad = strEspecialidad;
    }
    
    
}
