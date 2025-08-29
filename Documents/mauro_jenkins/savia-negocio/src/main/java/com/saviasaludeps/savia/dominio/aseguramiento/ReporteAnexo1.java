/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import java.io.Serializable;

/**
 *
 * @author jyperez
 */
public class ReporteAnexo1 implements Serializable{
    
    private int id;
    //Para el tipo de inconsistencia (1,2)
    private Integer strNumeroInforme;
    private String strFecha;
    private String strHora;
    private String strNombrePrestador;
    private String strTipoDocumentoPrestador;
    private String strDocumentoPrestador;
    private String strCodigoPrestador;
    private String strDireccionPrestador;
    private String strTelefonoPrestador;
    private String strDepartamentoPrestador;
    private String strMunicipioPrestador;
    private String strNombreEntidad;
    private String strCodigoEntidad;
    private String strTipoInconsistencia;
    private String strPrimerApellidoUsuario;
    private String strSegundoApellidoUsuario;
    private String strPrimerNombreUsuario;
    private String strSegundoNombreUsuario;
    private String strTipoDocumentoUsuario;
    private String strDocumentoUsuario;
    private String strFechaNacimientoUsuario;
    private String strDireccionUsuario;
    private String strTelefonoUsuario;
    private String strDepartamentoUsuario;
    private String strMunicipioUsuario;
    private String strTipoCobertura;
    private String strOpcionPrimerApellido;
    private String strOpcionSegundoApellido;
    private String strOpcionPrimerNombre;
    private String strOpcionSegundoNombre;
    private String strOpcionTipoDocumento;
    private String strOpcionDocumento;
    private String strOpcionFechaNacimiento;
    private String strPrimerApellidoFisico;
    private String strSegundoApellidoFisico;
    private String strPrimerNombreFisico;
    private String strSegundoNombreFisico;
    private String strTipoDocumentoFisico;
    private String strDocumentoFisico;
    private String strFechaNacimientoFisico;
    private String strObservacion;
    private String strNombreReporta;
    private String strTelefonoReporta;
    private String strCargoReporta;
    private String strCelularReporta;
    //2022-11-28 nuevos campos reporte - ley 1581 de 2012
    private String strAutorizaDatos;
    private String strFechaActualizaDatos;
    //2024-04-05 jyperez nueos campos RES 2335
    private String strMunicipioResidencia;
    private String strEmail;
    private String strNombreContactoEmergencia;
    private String strTelefonoContactoEmergencia;
    private String strDireccionAlternaAfiliado;
    private String strOpcionMunicipioResidencia;
    private String strOpcionEmail;
    private String strOpcionNombreContactoEmergencia;
    private String strOpcionTelefonoContactoEmergencia;
    private String strOpcionDireccionAlternaAfiliado;
    //2024-04-12 jyperez nuevo campo RES 2335
    private String strConsecutivo;

    public ReporteAnexo1() {
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the strNumeroInforme
     */
    public Integer getStrNumeroInforme() {
        return strNumeroInforme;
    }

    /**
     * @param strNumeroInforme the strNumeroInforme to set
     */
    public void setStrNumeroInforme(Integer strNumeroInforme) {
        this.strNumeroInforme = strNumeroInforme;
    }

    /**
     * @return the strFecha
     */
    public String getStrFecha() {
        return strFecha;
    }

    /**
     * @param strFecha the strFecha to set
     */
    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    /**
     * @return the strHora
     */
    public String getStrHora() {
        return strHora;
    }

    /**
     * @param strHora the strHora to set
     */
    public void setStrHora(String strHora) {
        this.strHora = strHora;
    }

    /**
     * @return the strNombrePrestador
     */
    public String getStrNombrePrestador() {
        return strNombrePrestador;
    }

    /**
     * @param strNombrePrestador the strNombrePrestador to set
     */
    public void setStrNombrePrestador(String strNombrePrestador) {
        this.strNombrePrestador = strNombrePrestador;
    }

    /**
     * @return the strTipoDocumentoPrestador
     */
    public String getStrTipoDocumentoPrestador() {
        return strTipoDocumentoPrestador;
    }

    /**
     * @param strTipoDocumentoPrestador the strTipoDocumentoPrestador to set
     */
    public void setStrTipoDocumentoPrestador(String strTipoDocumentoPrestador) {
        this.strTipoDocumentoPrestador = strTipoDocumentoPrestador;
    }

    /**
     * @return the strDocumentoPrestador
     */
    public String getStrDocumentoPrestador() {
        return strDocumentoPrestador;
    }

    /**
     * @param strDocumentoPrestador the strDocumentoPrestador to set
     */
    public void setStrDocumentoPrestador(String strDocumentoPrestador) {
        this.strDocumentoPrestador = strDocumentoPrestador;
    }

    /**
     * @return the strCodigoPrestador
     */
    public String getStrCodigoPrestador() {
        return strCodigoPrestador;
    }

    /**
     * @param strCodigoPrestador the strCodigoPrestador to set
     */
    public void setStrCodigoPrestador(String strCodigoPrestador) {
        this.strCodigoPrestador = strCodigoPrestador;
    }

    /**
     * @return the strDireccionPrestador
     */
    public String getStrDireccionPrestador() {
        return strDireccionPrestador;
    }

    /**
     * @param strDireccionPrestador the strDireccionPrestador to set
     */
    public void setStrDireccionPrestador(String strDireccionPrestador) {
        this.strDireccionPrestador = strDireccionPrestador;
    }

    /**
     * @return the strTelefonoPrestador
     */
    public String getStrTelefonoPrestador() {
        return strTelefonoPrestador;
    }

    /**
     * @param strTelefonoPrestador the strTelefonoPrestador to set
     */
    public void setStrTelefonoPrestador(String strTelefonoPrestador) {
        this.strTelefonoPrestador = strTelefonoPrestador;
    }

    /**
     * @return the strDepartamentoPrestador
     */
    public String getStrDepartamentoPrestador() {
        return strDepartamentoPrestador;
    }

    /**
     * @param strDepartamentoPrestador the strDepartamentoPrestador to set
     */
    public void setStrDepartamentoPrestador(String strDepartamentoPrestador) {
        this.strDepartamentoPrestador = strDepartamentoPrestador;
    }

    /**
     * @return the strMunicipioPrestador
     */
    public String getStrMunicipioPrestador() {
        return strMunicipioPrestador;
    }

    /**
     * @param strMunicipioPrestador the strMunicipioPrestador to set
     */
    public void setStrMunicipioPrestador(String strMunicipioPrestador) {
        this.strMunicipioPrestador = strMunicipioPrestador;
    }

    /**
     * @return the strNombreEntidad
     */
    public String getStrNombreEntidad() {
        return strNombreEntidad;
    }

    /**
     * @param strNombreEntidad the strNombreEntidad to set
     */
    public void setStrNombreEntidad(String strNombreEntidad) {
        this.strNombreEntidad = strNombreEntidad;
    }

    /**
     * @return the strCodigoEntidad
     */
    public String getStrCodigoEntidad() {
        return strCodigoEntidad;
    }

    /**
     * @param strCodigoEntidad the strCodigoEntidad to set
     */
    public void setStrCodigoEntidad(String strCodigoEntidad) {
        this.strCodigoEntidad = strCodigoEntidad;
    }

    /**
     * @return the strTipoInconsistencia
     */
    public String getStrTipoInconsistencia() {
        return strTipoInconsistencia;
    }

    /**
     * @param strTipoInconsistencia the strTipoInconsistencia to set
     */
    public void setStrTipoInconsistencia(String strTipoInconsistencia) {
        this.strTipoInconsistencia = strTipoInconsistencia;
    }

    /**
     * @return the strPrimerApellidoUsuario
     */
    public String getStrPrimerApellidoUsuario() {
        return strPrimerApellidoUsuario;
    }

    /**
     * @param strPrimerApellidoUsuario the strPrimerApellidoUsuario to set
     */
    public void setStrPrimerApellidoUsuario(String strPrimerApellidoUsuario) {
        this.strPrimerApellidoUsuario = strPrimerApellidoUsuario;
    }

    /**
     * @return the strSegundoApellidoUsuario
     */
    public String getStrSegundoApellidoUsuario() {
        return strSegundoApellidoUsuario;
    }

    /**
     * @param strSegundoApellidoUsuario the strSegundoApellidoUsuario to set
     */
    public void setStrSegundoApellidoUsuario(String strSegundoApellidoUsuario) {
        this.strSegundoApellidoUsuario = strSegundoApellidoUsuario;
    }

    /**
     * @return the strPrimerNombreUsuario
     */
    public String getStrPrimerNombreUsuario() {
        return strPrimerNombreUsuario;
    }

    /**
     * @param strPrimerNombreUsuario the strPrimerNombreUsuario to set
     */
    public void setStrPrimerNombreUsuario(String strPrimerNombreUsuario) {
        this.strPrimerNombreUsuario = strPrimerNombreUsuario;
    }

    /**
     * @return the strSegundoNombreUsuario
     */
    public String getStrSegundoNombreUsuario() {
        return strSegundoNombreUsuario;
    }

    /**
     * @param strSegundoNombreUsuario the strSegundoNombreUsuario to set
     */
    public void setStrSegundoNombreUsuario(String strSegundoNombreUsuario) {
        this.strSegundoNombreUsuario = strSegundoNombreUsuario;
    }

    /**
     * @return the strTipoDocumentoUsuario
     */
    public String getStrTipoDocumentoUsuario() {
        return strTipoDocumentoUsuario;
    }

    /**
     * @param strTipoDocumentoUsuario the strTipoDocumentoUsuario to set
     */
    public void setStrTipoDocumentoUsuario(String strTipoDocumentoUsuario) {
        this.strTipoDocumentoUsuario = strTipoDocumentoUsuario;
    }

    /**
     * @return the strDocumentoUsuario
     */
    public String getStrDocumentoUsuario() {
        return strDocumentoUsuario;
    }

    /**
     * @param strDocumentoUsuario the strDocumentoUsuario to set
     */
    public void setStrDocumentoUsuario(String strDocumentoUsuario) {
        this.strDocumentoUsuario = strDocumentoUsuario;
    }

    /**
     * @return the strFechaNacimientoUsuario
     */
    public String getStrFechaNacimientoUsuario() {
        return strFechaNacimientoUsuario;
    }

    /**
     * @param strFechaNacimientoUsuario the strFechaNacimientoUsuario to set
     */
    public void setStrFechaNacimientoUsuario(String strFechaNacimientoUsuario) {
        this.strFechaNacimientoUsuario = strFechaNacimientoUsuario;
    }

    /**
     * @return the strDireccionUsuario
     */
    public String getStrDireccionUsuario() {
        return strDireccionUsuario;
    }

    /**
     * @param strDireccionUsuario the strDireccionUsuario to set
     */
    public void setStrDireccionUsuario(String strDireccionUsuario) {
        this.strDireccionUsuario = strDireccionUsuario;
    }

    /**
     * @return the strTelefonoUsuario
     */
    public String getStrTelefonoUsuario() {
        return strTelefonoUsuario;
    }

    /**
     * @param strTelefonoUsuario the strTelefonoUsuario to set
     */
    public void setStrTelefonoUsuario(String strTelefonoUsuario) {
        this.strTelefonoUsuario = strTelefonoUsuario;
    }

    /**
     * @return the strDepartamentoUsuario
     */
    public String getStrDepartamentoUsuario() {
        return strDepartamentoUsuario;
    }

    /**
     * @param strDepartamentoUsuario the strDepartamentoUsuario to set
     */
    public void setStrDepartamentoUsuario(String strDepartamentoUsuario) {
        this.strDepartamentoUsuario = strDepartamentoUsuario;
    }

    /**
     * @return the strMunicipioUsuario
     */
    public String getStrMunicipioUsuario() {
        return strMunicipioUsuario;
    }

    /**
     * @param strMunicipioUsuario the strMunicipioUsuario to set
     */
    public void setStrMunicipioUsuario(String strMunicipioUsuario) {
        this.strMunicipioUsuario = strMunicipioUsuario;
    }

    /**
     * @return the strTipoCobertura
     */
    public String getStrTipoCobertura() {
        return strTipoCobertura;
    }

    /**
     * @param strTipoCobertura the strTipoCobertura to set
     */
    public void setStrTipoCobertura(String strTipoCobertura) {
        this.strTipoCobertura = strTipoCobertura;
    }

    /**
     * @return the strOpcionPrimerApellido
     */
    public String getStrOpcionPrimerApellido() {
        return strOpcionPrimerApellido;
    }

    /**
     * @param strOpcionPrimerApellido the strOpcionPrimerApellido to set
     */
    public void setStrOpcionPrimerApellido(String strOpcionPrimerApellido) {
        this.strOpcionPrimerApellido = strOpcionPrimerApellido;
    }

    /**
     * @return the strOpcionSegundoApellido
     */
    public String getStrOpcionSegundoApellido() {
        return strOpcionSegundoApellido;
    }

    /**
     * @param strOpcionSegundoApellido the strOpcionSegundoApellido to set
     */
    public void setStrOpcionSegundoApellido(String strOpcionSegundoApellido) {
        this.strOpcionSegundoApellido = strOpcionSegundoApellido;
    }

    /**
     * @return the strOpcionPrimerNombre
     */
    public String getStrOpcionPrimerNombre() {
        return strOpcionPrimerNombre;
    }

    /**
     * @param strOpcionPrimerNombre the strOpcionPrimerNombre to set
     */
    public void setStrOpcionPrimerNombre(String strOpcionPrimerNombre) {
        this.strOpcionPrimerNombre = strOpcionPrimerNombre;
    }

    /**
     * @return the strOpcionSegundoNombre
     */
    public String getStrOpcionSegundoNombre() {
        return strOpcionSegundoNombre;
    }

    /**
     * @param strOpcionSegundoNombre the strOpcionSegundoNombre to set
     */
    public void setStrOpcionSegundoNombre(String strOpcionSegundoNombre) {
        this.strOpcionSegundoNombre = strOpcionSegundoNombre;
    }

    /**
     * @return the strOpcionTipoDocumento
     */
    public String getStrOpcionTipoDocumento() {
        return strOpcionTipoDocumento;
    }

    /**
     * @param strOpcionTipoDocumento the strOpcionTipoDocumento to set
     */
    public void setStrOpcionTipoDocumento(String strOpcionTipoDocumento) {
        this.strOpcionTipoDocumento = strOpcionTipoDocumento;
    }

    /**
     * @return the strOpcionDocumento
     */
    public String getStrOpcionDocumento() {
        return strOpcionDocumento;
    }

    /**
     * @param strOpcionDocumento the strOpcionDocumento to set
     */
    public void setStrOpcionDocumento(String strOpcionDocumento) {
        this.strOpcionDocumento = strOpcionDocumento;
    }

    /**
     * @return the strOpcionFechaNacimiento
     */
    public String getStrOpcionFechaNacimiento() {
        return strOpcionFechaNacimiento;
    }

    /**
     * @param strOpcionFechaNacimiento the strOpcionFechaNacimiento to set
     */
    public void setStrOpcionFechaNacimiento(String strOpcionFechaNacimiento) {
        this.strOpcionFechaNacimiento = strOpcionFechaNacimiento;
    }

    /**
     * @return the strPrimerApellidoFisico
     */
    public String getStrPrimerApellidoFisico() {
        return strPrimerApellidoFisico;
    }

    /**
     * @param strPrimerApellidoFisico the strPrimerApellidoFisico to set
     */
    public void setStrPrimerApellidoFisico(String strPrimerApellidoFisico) {
        this.strPrimerApellidoFisico = strPrimerApellidoFisico;
    }

    /**
     * @return the strSegundoApellidoFisico
     */
    public String getStrSegundoApellidoFisico() {
        return strSegundoApellidoFisico;
    }

    /**
     * @param strSegundoApellidoFisico the strSegundoApellidoFisico to set
     */
    public void setStrSegundoApellidoFisico(String strSegundoApellidoFisico) {
        this.strSegundoApellidoFisico = strSegundoApellidoFisico;
    }

    /**
     * @return the strPrimerNombreFisico
     */
    public String getStrPrimerNombreFisico() {
        return strPrimerNombreFisico;
    }

    /**
     * @param strPrimerNombreFisico the strPrimerNombreFisico to set
     */
    public void setStrPrimerNombreFisico(String strPrimerNombreFisico) {
        this.strPrimerNombreFisico = strPrimerNombreFisico;
    }

    /**
     * @return the strSegundoNombreFisico
     */
    public String getStrSegundoNombreFisico() {
        return strSegundoNombreFisico;
    }

    /**
     * @param strSegundoNombreFisico the strSegundoNombreFisico to set
     */
    public void setStrSegundoNombreFisico(String strSegundoNombreFisico) {
        this.strSegundoNombreFisico = strSegundoNombreFisico;
    }

    /**
     * @return the strTipoDocumentoFisico
     */
    public String getStrTipoDocumentoFisico() {
        return strTipoDocumentoFisico;
    }

    /**
     * @param strTipoDocumentoFisico the strTipoDocumentoFisico to set
     */
    public void setStrTipoDocumentoFisico(String strTipoDocumentoFisico) {
        this.strTipoDocumentoFisico = strTipoDocumentoFisico;
    }

    /**
     * @return the strDocumentoFisico
     */
    public String getStrDocumentoFisico() {
        return strDocumentoFisico;
    }

    /**
     * @param strDocumentoFisico the strDocumentoFisico to set
     */
    public void setStrDocumentoFisico(String strDocumentoFisico) {
        this.strDocumentoFisico = strDocumentoFisico;
    }

    /**
     * @return the strFechaNacimientoFisico
     */
    public String getStrFechaNacimientoFisico() {
        return strFechaNacimientoFisico;
    }

    /**
     * @param strFechaNacimientoFisico the strFechaNacimientoFisico to set
     */
    public void setStrFechaNacimientoFisico(String strFechaNacimientoFisico) {
        this.strFechaNacimientoFisico = strFechaNacimientoFisico;
    }

    /**
     * @return the strObservacion
     */
    public String getStrObservacion() {
        return strObservacion;
    }

    /**
     * @param strObservacion the strObservacion to set
     */
    public void setStrObservacion(String strObservacion) {
        this.strObservacion = strObservacion;
    }

    /**
     * @return the strNombreReporta
     */
    public String getStrNombreReporta() {
        return strNombreReporta;
    }

    /**
     * @param strNombreReporta the strNombreReporta to set
     */
    public void setStrNombreReporta(String strNombreReporta) {
        this.strNombreReporta = strNombreReporta;
    }

    /**
     * @return the strTelefonoReporta
     */
    public String getStrTelefonoReporta() {
        return strTelefonoReporta;
    }

    /**
     * @param strTelefonoReporta the strTelefonoReporta to set
     */
    public void setStrTelefonoReporta(String strTelefonoReporta) {
        this.strTelefonoReporta = strTelefonoReporta;
    }

    /**
     * @return the strCargoReporta
     */
    public String getStrCargoReporta() {
        return strCargoReporta;
    }

    /**
     * @param strCargoReporta the strCargoReporta to set
     */
    public void setStrCargoReporta(String strCargoReporta) {
        this.strCargoReporta = strCargoReporta;
    }

    /**
     * @return the strCelularReporta
     */
    public String getStrCelularReporta() {
        return strCelularReporta;
    }

    /**
     * @param strCelularReporta the strCelularReporta to set
     */
    public void setStrCelularReporta(String strCelularReporta) {
        this.strCelularReporta = strCelularReporta;
    }

    @Override
    public String toString() {
        return "ReporteAnexo1{" + "strNumeroInforme=" + strNumeroInforme + ", strFecha=" + strFecha + ", strHora=" + strHora + ", strNombrePrestador=" + strNombrePrestador + ", strTipoDocumentoPrestador=" + strTipoDocumentoPrestador + ", strDocumentoPrestador=" + strDocumentoPrestador + ", strCodigoPrestador=" + strCodigoPrestador + ", strDireccionPrestador=" + strDireccionPrestador + ", strTelefonoPrestador=" + strTelefonoPrestador + ", strDepartamentoPrestador=" + strDepartamentoPrestador + ", strMunicipioPrestador=" + strMunicipioPrestador + ", strNombreEntidad=" + strNombreEntidad + ", strCodigoEntidad=" + strCodigoEntidad + ", strTipoInconsistencia=" + strTipoInconsistencia + ", strPrimerApellidoUsuario=" + strPrimerApellidoUsuario + ", strSegundoApellidoUsuario=" + strSegundoApellidoUsuario + ", strPrimerNombreUsuario=" + strPrimerNombreUsuario + ", strSegundoNombreUsuario=" + strSegundoNombreUsuario + ", strTipoDocumentoUsuario=" + strTipoDocumentoUsuario + ", strDocumentoUsuario=" + strDocumentoUsuario + ", strFechaNacimientoUsuario=" + strFechaNacimientoUsuario + ", strDireccionUsuario=" + strDireccionUsuario + ", strTelefonoUsuario=" + strTelefonoUsuario + ", strDepartamentoUsuario=" + strDepartamentoUsuario + ", strMunicipioUsuario=" + strMunicipioUsuario + ", strTipoCobertura=" + strTipoCobertura + ", strOpcionPrimerApellido=" + strOpcionPrimerApellido + ", strOpcionSegundoApellido=" + strOpcionSegundoApellido + ", strOpcionPrimerNombre=" + strOpcionPrimerNombre + ", strOpcionSegundoNombre=" + strOpcionSegundoNombre + ", strOpcionTipoDocumento=" + strOpcionTipoDocumento + ", strOpcionDocumento=" + strOpcionDocumento + ", strOpcionFechaNacimiento=" + strOpcionFechaNacimiento + ", strPrimerApellidoFisico=" + strPrimerApellidoFisico + ", strSegundoApellidoFisico=" + strSegundoApellidoFisico + ", strPrimerNombreFisico=" + strPrimerNombreFisico + ", strSegundoNombreFisico=" + strSegundoNombreFisico + ", strTipoDocumentoFisico=" + strTipoDocumentoFisico + ", strDocumentoFisico=" + strDocumentoFisico + ", strFechaNacimientoFisico=" + strFechaNacimientoFisico + ", strObservacion=" + strObservacion + ", strNombreReporta=" + strNombreReporta + ", strTelefonoReporta=" + strTelefonoReporta + ", strCargoReporta=" + strCargoReporta + ", strCelularReporta=" + strCelularReporta + '}';
    }

    /**
     * @return the strAutorizaDatos
     */
    public String getStrAutorizaDatos() {
        return strAutorizaDatos;
    }

    /**
     * @param strAutorizaDatos the strAutorizaDatos to set
     */
    public void setStrAutorizaDatos(String strAutorizaDatos) {
        this.strAutorizaDatos = strAutorizaDatos;
    }

    /**
     * @return the strFechaActualizaDatos
     */
    public String getStrFechaActualizaDatos() {
        return strFechaActualizaDatos;
    }

    /**
     * @param strFechaActualizaDatos the strFechaActualizaDatos to set
     */
    public void setStrFechaActualizaDatos(String strFechaActualizaDatos) {
        this.strFechaActualizaDatos = strFechaActualizaDatos;
    }

    /**
     * @return the strMunicipioResidencia
     */
    public String getStrMunicipioResidencia() {
        return strMunicipioResidencia;
    }

    /**
     * @param strMunicipioResidencia the strMunicipioResidencia to set
     */
    public void setStrMunicipioResidencia(String strMunicipioResidencia) {
        this.strMunicipioResidencia = strMunicipioResidencia;
    }

    /**
     * @return the strEmail
     */
    public String getStrEmail() {
        return strEmail;
    }

    /**
     * @param strEmail the strEmail to set
     */
    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    /**
     * @return the strNombreContactoEmergencia
     */
    public String getStrNombreContactoEmergencia() {
        return strNombreContactoEmergencia;
    }

    /**
     * @param strNombreContactoEmergencia the strNombreContactoEmergencia to set
     */
    public void setStrNombreContactoEmergencia(String strNombreContactoEmergencia) {
        this.strNombreContactoEmergencia = strNombreContactoEmergencia;
    }

    /**
     * @return the strTelefonoContactoEmergencia
     */
    public String getStrTelefonoContactoEmergencia() {
        return strTelefonoContactoEmergencia;
    }

    /**
     * @param strTelefonoContactoEmergencia the strTelefonoContactoEmergencia to set
     */
    public void setStrTelefonoContactoEmergencia(String strTelefonoContactoEmergencia) {
        this.strTelefonoContactoEmergencia = strTelefonoContactoEmergencia;
    }

    /**
     * @return the strDireccionAlternaAfiliado
     */
    public String getStrDireccionAlternaAfiliado() {
        return strDireccionAlternaAfiliado;
    }

    /**
     * @param strDireccionAlternaAfiliado the strDireccionAlternaAfiliado to set
     */
    public void setStrDireccionAlternaAfiliado(String strDireccionAlternaAfiliado) {
        this.strDireccionAlternaAfiliado = strDireccionAlternaAfiliado;
    }

    /**
     * @return the strOpcionMunicipioResidencia
     */
    public String getStrOpcionMunicipioResidencia() {
        return strOpcionMunicipioResidencia;
    }

    /**
     * @param strOpcionMunicipioResidencia the strOpcionMunicipioResidencia to set
     */
    public void setStrOpcionMunicipioResidencia(String strOpcionMunicipioResidencia) {
        this.strOpcionMunicipioResidencia = strOpcionMunicipioResidencia;
    }

    /**
     * @return the strOpcionEmail
     */
    public String getStrOpcionEmail() {
        return strOpcionEmail;
    }

    /**
     * @param strOpcionEmail the strOpcionEmail to set
     */
    public void setStrOpcionEmail(String strOpcionEmail) {
        this.strOpcionEmail = strOpcionEmail;
    }

    /**
     * @return the strOpcionNombreContactoEmergencia
     */
    public String getStrOpcionNombreContactoEmergencia() {
        return strOpcionNombreContactoEmergencia;
    }

    /**
     * @param strOpcionNombreContactoEmergencia the strOpcionNombreContactoEmergencia to set
     */
    public void setStrOpcionNombreContactoEmergencia(String strOpcionNombreContactoEmergencia) {
        this.strOpcionNombreContactoEmergencia = strOpcionNombreContactoEmergencia;
    }

    /**
     * @return the strOpcionTelefonoContactoEmergencia
     */
    public String getStrOpcionTelefonoContactoEmergencia() {
        return strOpcionTelefonoContactoEmergencia;
    }

    /**
     * @param strOpcionTelefonoContactoEmergencia the strOpcionTelefonoContactoEmergencia to set
     */
    public void setStrOpcionTelefonoContactoEmergencia(String strOpcionTelefonoContactoEmergencia) {
        this.strOpcionTelefonoContactoEmergencia = strOpcionTelefonoContactoEmergencia;
    }

    /**
     * @return the strOpcionDireccionAlternaAfiliado
     */
    public String getStrOpcionDireccionAlternaAfiliado() {
        return strOpcionDireccionAlternaAfiliado;
    }

    /**
     * @param strOpcionDireccionAlternaAfiliado the strOpcionDireccionAlternaAfiliado to set
     */
    public void setStrOpcionDireccionAlternaAfiliado(String strOpcionDireccionAlternaAfiliado) {
        this.strOpcionDireccionAlternaAfiliado = strOpcionDireccionAlternaAfiliado;
    }

    /**
     * @return the strConsecutivo
     */
    public String getStrConsecutivo() {
        return strConsecutivo;
    }

    /**
     * @param strConsecutivo the strConsecutivo to set
     */
    public void setStrConsecutivo(String strConsecutivo) {
        this.strConsecutivo = strConsecutivo;
    }

}
