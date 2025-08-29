/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucDiagnostico;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Jaime Andres Olarte
 */
public class ReporteReferencia implements Serializable {

    private static final SimpleDateFormat formato1 = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat formato2 = new SimpleDateFormat("EEEE dd 'de' MMMM 'del año' yyyy", new Locale("es", "CO"));
    public static final String ENCABEZADO_ANEXO_9 = "5. Referencia";
    public static final String ENCABEZADO_ANEXO_10 = "6. Contrarreferencia";  
    
    public static final String LISTA_DIAGNOSTICOS = "LISTA_DIAGNOSTICOS";
    
    private Integer intTipoAnexo;
    private String strTipoAnexo;
    private Date dtmFechaHora;
    private Integer intNumeroSolicitud;
    private String strNombrePrestador;
    private String strTipoDocumentoPrestador;
    private String strDocumentoPrestador;
    private String strCodigoPrestador;
    private String strDireccionPrestador;
    private String strTelefonoPrestador;
    private String strDepartamentoPrestador;
    private String strCodigoDepartamentoPrestador;
    private String strMunicipioPrestador;
    private String strCodigoMunicipioPrestador;
    private String strPrimerNombrePaciente;
    private String strPrimerApellidoPaciente;
    private String strSegundoNombrePaciente;
    private String strSegundoApellidoPaciente;
    private String strTipoDocumentoPaciente;
    private String strDocumentoPaciente;
    private Date dtmFechaNacimientoPaciente;
    private String strRegimenPaciente;
    private String strDireccionPaciente;
    private String strTelefonoPaciente;
    private String strDepartamentoPaciente;
    private String strCodigoDepartamentoPaciente;
    private String strMunicipioPaciente;
    private String strCodigoMunicipioPaciente;
    private String strEntidadResponsablePago;
    private String strCodigoEntidadResponsablePago;
    private String strPrimerNombreResponsable;
    private String strPrimerApellidoResponsable;
    private String strSegundoNombreResponsable;
    private String strSegundoApellidoResponsable;
    private String strTipoDocumentoResponsable;
    private String strDocumentoResponsable;
    private String strDireccionResponsable;
    private String strTelefonoResponsable;
    private String strDepartamentoResponsable;
    private String strCodigoDepartamentoResponsable;
    private String strMunicipioResponsable;
    private String strCodigoMunicipioResponsable;
    private String strNombreProfesional;
    private String strTelefonoProfesional;
    private String strCelularProfesional;
    private String strServicioQueSolicita;
    private String strServicioCualSolicita;
    private String strServicioCualSolicitaCodigo;
    private String strObservacionRelevante;
    private String strObservacionEvolucion;
    private String strConsecutivo;
    private String strDireccionAlternativa;
    private String strCorreoContacto;
    private String strCauseMotivaAtencion;
    private String strPrivacidadAtencion;
    private String strGruposServicios;
    private String strModalidadrealizacionTecnologia;
    private String strTipoAtencionSolicita;
    private String strTecnologia;
    private String strCantidad;
    private String strCodigoPrestadoRemite;
    private String strCodigoCUPSProcedimiento;
    private String strDestinoPersona;
    private List<RefAnexo9Diagnostico> listaDiagnosticosIngreso;

    public Integer getIntTipoAnexo() {
        return intTipoAnexo;
    }

    public void setIntTipoAnexo(Integer intTipoAnexo) {
        this.intTipoAnexo = intTipoAnexo;
    }

    public String getStrTipoAnexo() {
        return strTipoAnexo;
    }

    public void setStrTipoAnexo(String strTipoAnexo) {
        this.strTipoAnexo = strTipoAnexo;
    }
    
    public Date getDtmFechaHora(){
        return dtmFechaHora;
    }

    public void setDtmFechaHora(Date dtmFechaHora) {
        this.dtmFechaHora = dtmFechaHora;
    }

    public Integer getIntNumeroSolicitud() {
        return intNumeroSolicitud;
    }

    public String getStrConsecutivo() {
        return strConsecutivo;
    }

    public void setStrConsecutivo(String strConsecutivo) {
        this.strConsecutivo = strConsecutivo;
    }

    public String getStrDireccionAlternativa() {
        return strDireccionAlternativa;
    }

    public void setStrDireccionAlternativa(String strDireccionAlternativa) {
        this.strDireccionAlternativa = strDireccionAlternativa;
    }

    public String getStrCorreoContacto() {
        return strCorreoContacto;
    }

    public void setStrCorreoContacto(String strCorreoContacto) {
        this.strCorreoContacto = strCorreoContacto;
    }

    public String getStrCauseMotivaAtencion() {
        return strCauseMotivaAtencion;
    }

    public void setStrCauseMotivaAtencion(String strCauseMotivaAtencion) {
        this.strCauseMotivaAtencion = strCauseMotivaAtencion;
    }

    public String getStrPrivacidadAtencion() {
        return strPrivacidadAtencion;
    }

    public void setStrPrivacidadAtencion(String strPrivacidadAtencion) {
        this.strPrivacidadAtencion = strPrivacidadAtencion;
    }

    public String getStrGruposServicios() {
        return strGruposServicios;
    }

    public void setStrGruposServicios(String strGruposServicios) {
        this.strGruposServicios = strGruposServicios;
    }

    public String getStrModalidadrealizacionTecnologia() {
        return strModalidadrealizacionTecnologia;
    }

    public void setStrModalidadrealizacionTecnologia(String strModalidadrealizacionTecnologia) {
        this.strModalidadrealizacionTecnologia = strModalidadrealizacionTecnologia;
    }

    public String getStrServicioCualSolicitaCodigo() {
        return strServicioCualSolicitaCodigo;
    }

    public void setStrServicioCualSolicitaCodigo(String strServicioCualSolicitaCodigo) {
        this.strServicioCualSolicitaCodigo = strServicioCualSolicitaCodigo;
    }

    public String getStrTipoAtencionSolicita() {
        return strTipoAtencionSolicita;
    }

    public void setStrTipoAtencionSolicita(String strTipoAtencionSolicita) {
        this.strTipoAtencionSolicita = strTipoAtencionSolicita;
    }

    public String getStrTecnologia() {
        return strTecnologia;
    }

    public void setStrTecnologia(String strTecnologia) {
        this.strTecnologia = strTecnologia;
    }

    public String getStrCantidad() {
        return strCantidad;
    }

    public void setStrCantidad(String strCantidad) {
        this.strCantidad = strCantidad;
    }

    public String getStrCodigoPrestadoRemite() {
        return strCodigoPrestadoRemite;
    }

    public void setStrCodigoPrestadoRemite(String strCodigoPrestadoRemite) {
        this.strCodigoPrestadoRemite = strCodigoPrestadoRemite;
    }

    public String getStrCodigoCUPSProcedimiento() {
        return strCodigoCUPSProcedimiento;
    }

    public void setStrCodigoCUPSProcedimiento(String strCodigoCUPSProcedimiento) {
        this.strCodigoCUPSProcedimiento = strCodigoCUPSProcedimiento;
    }

    public String getStrDestinoPersona() {
        return strDestinoPersona;
    }

    public void setStrDestinoPersona(String strDestinoPersona) {
        this.strDestinoPersona = strDestinoPersona;
    }

    public List<RefAnexo9Diagnostico> getListaDiagnosticosIngreso() {
        return listaDiagnosticosIngreso;
    }

    public void setListaDiagnosticosIngreso(List<RefAnexo9Diagnostico> listaDiagnosticosIngreso) {
        this.listaDiagnosticosIngreso = listaDiagnosticosIngreso;
    }

    public void setIntNumeroSolicitud(Integer intNumeroSolicitud) {
        this.intNumeroSolicitud = intNumeroSolicitud;
    }

    public String getStrNombrePrestador() {
        return strNombrePrestador;
    }

    public void setStrNombrePrestador(String strNombrePrestador) {
        this.strNombrePrestador = strNombrePrestador;
    }

    public String getStrTipoDocumentoPrestador() {
        return strTipoDocumentoPrestador;
    }

    public void setStrTipoDocumentoPrestador(String strTipoDocumentoPrestador) {
        this.strTipoDocumentoPrestador = strTipoDocumentoPrestador;
    }

    public String getStrDocumentoPrestador() {
        return strDocumentoPrestador;
    }

    public void setStrDocumentoPrestador(String strDocumentoPrestador) {
        this.strDocumentoPrestador = strDocumentoPrestador;
    }

    public String getStrCodigoPrestador() {
        return strCodigoPrestador;
    }

    public void setStrCodigoPrestador(String strCodigoPrestador) {
        this.strCodigoPrestador = strCodigoPrestador;
    }

    public String getStrDireccionPrestador() {
        return strDireccionPrestador;
    }

    public void setStrDireccionPrestador(String strDireccionPrestador) {
        this.strDireccionPrestador = strDireccionPrestador;
    }

    public String getStrTelefonoPrestador() {
        return strTelefonoPrestador;
    }

    public void setStrTelefonoPrestador(String strTelefonoPrestador) {
        this.strTelefonoPrestador = strTelefonoPrestador;
    }

    public String getStrDepartamentoPrestador() {
        return strDepartamentoPrestador;
    }

    public void setStrDepartamentoPrestador(String strDepartamentoPrestador) {
        this.strDepartamentoPrestador = strDepartamentoPrestador;
    }

    public String getStrCodigoDepartamentoPrestador() {
        return strCodigoDepartamentoPrestador;
    }

    public void setStrCodigoDepartamentoPrestador(String strCodigoDepartamentoPrestador) {
        this.strCodigoDepartamentoPrestador = strCodigoDepartamentoPrestador;
    }

    public String getStrMunicipioPrestador() {
        return strMunicipioPrestador;
    }

    public void setStrMunicipioPrestador(String strMunicipioPrestador) {
        this.strMunicipioPrestador = strMunicipioPrestador;
    }

    public String getStrCodigoMunicipioPrestador() {
        return strCodigoMunicipioPrestador;
    }

    public void setStrCodigoMunicipioPrestador(String strCodigoMunicipioPrestador) {
        this.strCodigoMunicipioPrestador = strCodigoMunicipioPrestador;
    }

    public String getStrPrimerNombrePaciente() {
        return strPrimerNombrePaciente;
    }

    public void setStrPrimerNombrePaciente(String strPrimerNombrePaciente) {
        this.strPrimerNombrePaciente = strPrimerNombrePaciente;
    }

    public String getStrPrimerApellidoPaciente() {
        return strPrimerApellidoPaciente;
    }

    public void setStrPrimerApellidoPaciente(String strPrimerApellidoPaciente) {
        this.strPrimerApellidoPaciente = strPrimerApellidoPaciente;
    }

    public String getStrSegundoNombrePaciente() {
        return strSegundoNombrePaciente;
    }

    public void setStrSegundoNombrePaciente(String strSegundoNombrePaciente) {
        this.strSegundoNombrePaciente = strSegundoNombrePaciente;
    }

    public String getStrSegundoApellidoPaciente() {
        return strSegundoApellidoPaciente;
    }

    public void setStrSegundoApellidoPaciente(String strSegundoApellidoPaciente) {
        this.strSegundoApellidoPaciente = strSegundoApellidoPaciente;
    }

    public String getStrTipoDocumentoPaciente() {
        return strTipoDocumentoPaciente;
    }

    public void setStrTipoDocumentoPaciente(String strTipoDocumentoPaciente) {
        this.strTipoDocumentoPaciente = strTipoDocumentoPaciente;
    }

    public String getStrDocumentoPaciente() {
        return strDocumentoPaciente;
    }

    public void setStrDocumentoPaciente(String strDocumentoPaciente) {
        this.strDocumentoPaciente = strDocumentoPaciente;
    }

    public Date getDtmFechaNacimientoPaciente(){
        return dtmFechaNacimientoPaciente;
    }

    public void setDtmFechaNacimientoPaciente(Date dtmFechaNacimientoPaciente) {
        this.dtmFechaNacimientoPaciente = dtmFechaNacimientoPaciente;
    }

    public String getStrRegimenPaciente() {
        return strRegimenPaciente;
    }

    public void setStrRegimenPaciente(String strRegimenPaciente) {
        this.strRegimenPaciente = strRegimenPaciente;
    }

    public String getStrDireccionPaciente() {
        return strDireccionPaciente;
    }

    public void setStrDireccionPaciente(String strDireccionPaciente) {
        this.strDireccionPaciente = strDireccionPaciente;
    }

    public String getStrTelefonoPaciente() {
        return strTelefonoPaciente;
    }

    public void setStrTelefonoPaciente(String strTelefonoPaciente) {
        this.strTelefonoPaciente = strTelefonoPaciente;
    }

    public String getStrDepartamentoPaciente() {
        return strDepartamentoPaciente;
    }

    public void setStrDepartamentoPaciente(String strDepartamentoPaciente) {
        this.strDepartamentoPaciente = strDepartamentoPaciente;
    }

    public String getStrCodigoDepartamentoPaciente() {
        return strCodigoDepartamentoPaciente;
    }

    public void setStrCodigoDepartamentoPaciente(String strCodigoDepartamentoPaciente) {
        this.strCodigoDepartamentoPaciente = strCodigoDepartamentoPaciente;
    }

    public String getStrMunicipioPaciente() {
        return strMunicipioPaciente;
    }

    public void setStrMunicipioPaciente(String strMunicipioPaciente) {
        this.strMunicipioPaciente = strMunicipioPaciente;
    }

    public String getStrCodigoMunicipioPaciente() {
        return strCodigoMunicipioPaciente;
    }

    public void setStrCodigoMunicipioPaciente(String strCodigoMunicipioPaciente) {
        this.strCodigoMunicipioPaciente = strCodigoMunicipioPaciente;
    }

    public String getStrEntidadResponsablePago() {
        return strEntidadResponsablePago;
    }

    public void setStrEntidadResponsablePago(String strEntidadResponsablePago) {
        this.strEntidadResponsablePago = strEntidadResponsablePago;
    }

    public String getStrCodigoEntidadResponsablePago() {
        return strCodigoEntidadResponsablePago;
    }

    public void setStrCodigoEntidadResponsablePago(String strCodigoEntidadResponsablePago) {
        this.strCodigoEntidadResponsablePago = strCodigoEntidadResponsablePago;
    }

    public String getStrPrimerNombreResponsable() {
        return strPrimerNombreResponsable;
    }

    public void setStrPrimerNombreResponsable(String strPrimerNombreResponsable) {
        this.strPrimerNombreResponsable = strPrimerNombreResponsable;
    }

    public String getStrPrimerApellidoResponsable() {
        return strPrimerApellidoResponsable;
    }

    public void setStrPrimerApellidoResponsable(String strPrimerApellidoResponsable) {
        this.strPrimerApellidoResponsable = strPrimerApellidoResponsable;
    }

    public String getStrSegundoNombreResponsable() {
        return strSegundoNombreResponsable;
    }

    public void setStrSegundoNombreResponsable(String strSegundoNombreResponsable) {
        this.strSegundoNombreResponsable = strSegundoNombreResponsable;
    }

    public String getStrSegundoApellidoResponsable() {
        return strSegundoApellidoResponsable;
    }

    public void setStrSegundoApellidoResponsable(String strSegundoApellidoResponsable) {
        this.strSegundoApellidoResponsable = strSegundoApellidoResponsable;
    }

    public String getStrTipoDocumentoResponsable() {
        return strTipoDocumentoResponsable;
    }

    public void setStrTipoDocumentoResponsable(String strTipoDocumentoResponsable) {
        this.strTipoDocumentoResponsable = strTipoDocumentoResponsable;
    }

    public String getStrDocumentoResponsable() {
        return strDocumentoResponsable;
    }

    public void setStrDocumentoResponsable(String strDocumentoResponsable) {
        this.strDocumentoResponsable = strDocumentoResponsable;
    }

    public String getStrDireccionResponsable() {
        return strDireccionResponsable;
    }

    public void setStrDireccionResponsable(String strDireccionResponsable) {
        this.strDireccionResponsable = strDireccionResponsable;
    }

    public String getStrTelefonoResponsable() {
        return strTelefonoResponsable;
    }

    public void setStrTelefonoResponsable(String strTelefonoResponsable) {
        this.strTelefonoResponsable = strTelefonoResponsable;
    }

    public String getStrDepartamentoResponsable() {
        return strDepartamentoResponsable;
    }

    public void setStrDepartamentoResponsable(String strDepartamentoResponsable) {
        this.strDepartamentoResponsable = strDepartamentoResponsable;
    }

    public String getStrCodigoDepartamentoResponsable() {
        return strCodigoDepartamentoResponsable;
    }

    public void setStrCodigoDepartamentoResponsable(String strCodigoDepartamentoResponsable) {
        this.strCodigoDepartamentoResponsable = strCodigoDepartamentoResponsable;
    }

    public String getStrMunicipioResponsable() {
        return strMunicipioResponsable;
    }

    public void setStrMunicipioResponsable(String strMunicipioResponsable) {
        this.strMunicipioResponsable = strMunicipioResponsable;
    }

    public String getStrCodigoMunicipioResponsable() {
        return strCodigoMunicipioResponsable;
    }

    public void setStrCodigoMunicipioResponsable(String strCodigoMunicipioResponsable) {
        this.strCodigoMunicipioResponsable = strCodigoMunicipioResponsable;
    }

    public String getStrNombreProfesional() {
        return strNombreProfesional;
    }

    public void setStrNombreProfesional(String strNombreProfesional) {
        this.strNombreProfesional = strNombreProfesional;
    }

    public String getStrTelefonoProfesional() {
        return strTelefonoProfesional;
    }

    public void setStrTelefonoProfesional(String strTelefonoProfesional) {
        this.strTelefonoProfesional = strTelefonoProfesional;
    }

    public String getStrCelularProfesional() {
        return strCelularProfesional;
    }

    public void setStrCelularProfesional(String strCelularProfesional) {
        this.strCelularProfesional = strCelularProfesional;
    }

    public String getStrServicioQueSolicita() {
        return strServicioQueSolicita;
    }

    public void setStrServicioQueSolicita(String strServicioQueSolicita) {
        this.strServicioQueSolicita = strServicioQueSolicita;
    }

    public String getStrServicioCualSolicita() {
        return strServicioCualSolicita;
    }

    public void setStrServicioCualSolicita(String strServicioCualSolicita) {
        this.strServicioCualSolicita = strServicioCualSolicita;
    }

    public String getStrObservacionRelevante() {
        return strObservacionRelevante;
    }

    public void setStrObservacionRelevante(String strObservacionRelevante) {
        this.strObservacionRelevante = strObservacionRelevante;
    }

    public String getStrObservacionEvolucion() {
        return strObservacionEvolucion;
    }

    public void setStrObservacionEvolucion(String strObservacionEvolucion) {
        this.strObservacionEvolucion = strObservacionEvolucion;
    }

}
