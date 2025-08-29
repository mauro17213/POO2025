/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class ReporteReferenciaContraReferencia implements Serializable{
    
    private String strFechaDescarga;
    private String strTipoSolicitud;
    private Integer intNumeroSolicitud;
    private String strEstadoSolicitud;
    private String strCanalComunicacion;
    private String strUsuarioCrea;
    private Date strFechaCrea;
    private String strTipoDocumento;
    private String strNumeroDocumento;
    private String strNombres;
    private String strApellidos;
    private String strGenero;
    private String strEdad;
    private Date strFechaNacimiento;
    private String strRegimen;
    private String strNivelSisben;
    private String strTieneDiscapacidad;
    private String strTipoAfiliado;
    private String strEstadoAfiliado;
    private String strExisteBDUA;
    private String strCodigoUnico;
    private String strGrupoPoblacional;
    private String strGrupoEtnico;
    private String strIpsPrimaria;
    private String strSedeIpsPrimaria;
    private String strCiudadMunicipioIpsPrimaria;
    private String strDepartamentoIpsPrimaria;
    private String strTienePortabilidad;
    private String strSedePrestadorPortabilidad;
    private String strMunicipioPrestadorPortabilidad;
    private String strTipoDocumentoCF;
    private String strNumeroDocumentoCF;
    private String strModeloLiquidacion;
    private Date strFechaMovilidad;
    private String strFechaReactivacion;
    private Date strFechaRetiro;
    private Date strFechaAfiliacionEPS;
    private String strMunicipioAfiliacion;
    private String strMunicipioResidencia;
    private String strDireccion;
    private String strZona;
    private String strTelefono;
    private String strCelular;
    private String strEmail;
    private String strTipoDocumentoIPS;
    private String strNumeroDocumentoIPS;
    private String strRazonSocialIPS;
    private String strCiudadMunicipioIPS;
    private String strDepartamentoIPS;
    private String strDireccionIPS;
    private String strTipoDocumentoProfesionalReferencia;
    private String strNumeroDocumentoProfesionalReferencia;
    private String strPrimerNombreProfesionalReferencia;
    private String strSegundoNombreProfesionalReferencia;
    private String strPrimerApellidoProfesionalReferencia;
    private String strSegundoApellidoProfesionalReferencia;
    private String strRegistroMedicoProfesionalReferencia;
    private String strEsécialidadProfesionalReferencia;
    private String strTelefonoProfesionalReferencia;
    private String strServicioRefiere;
    private String strServicioSolicita;
    private String strServicioCama;
    private String strServicioPiso;
    private String strNombreInformante;
    private String strCargoInformante;
    private String strTelefonoInformante;
    private BigDecimal strTemperaturaC;
    private BigDecimal strFrecuenciaCardiaca;
    private BigDecimal strFrecuenciaRespiratoria;
    private BigDecimal strTensionArterialS;
    private BigDecimal strTensionArterialD;
    private BigDecimal strSaturacionOxigeno;
    private BigDecimal strPeso;
    private Integer strTalla;
    private BigDecimal strImc;
    private Integer strEscalaGlasglow;
    private String strEscalaGlasglowDescripcion;
    private String strResumenEnfermedadActual;
    private String strAntecedentes;
    private Integer strTriageNivel;
    private String strMotivosRemisionDescripcion;
    private String strFechaHoraAceptacionReceptora;
    private Integer intTotalDiasRemision;
    private String strTipoDocumentoIPSReceptora;
    private String strNumeroDocumentoIPSReceptora;
    private String strRazonSocialIPSReceptora;
    private String strCiudadMunicipioIPSReceptora;
    private String strDepartamentoIPSReceptora;
    private String strDireccionIPSReceptora;
    
    private List<RefAnexo9Diagnostico> listaDiagnosticos;
    private List<RefAnexo9Gestion> listaRefAnexo9Gestion;
    
    public ReporteReferenciaContraReferencia (){
        
    }

    public String getStrFechaDescarga() {
        return strFechaDescarga;
    }

    public void setStrFechaDescarga(String strFechaDescarga) {
        this.strFechaDescarga = strFechaDescarga;
    }

    public String getStrTipoSolicitud() {
        return strTipoSolicitud;
    }

    public void setStrTipoSolicitud(String strTipoSolicitud) {
        this.strTipoSolicitud = strTipoSolicitud;
    }

    public Integer getIntNumeroSolicitud() {
        return intNumeroSolicitud;
    }

    public void setIntNumeroSolicitud(Integer intNumeroSolicitud) {
        this.intNumeroSolicitud = intNumeroSolicitud;
    }

    public String getStrEstadoSolicitud() {
        return strEstadoSolicitud;
    }

    public void setStrEstadoSolicitud(String strEstadoSolicitud) {
        this.strEstadoSolicitud = strEstadoSolicitud;
    }

    public String getStrCanalComunicacion() {
        return strCanalComunicacion;
    }

    public void setStrCanalComunicacion(String strCanalComunicacion) {
        this.strCanalComunicacion = strCanalComunicacion;
    }

    public String getStrUsuarioCrea() {
        return strUsuarioCrea;
    }

    public void setStrUsuarioCrea(String strUsuarioCrea) {
        this.strUsuarioCrea = strUsuarioCrea;
    }

    public Date getStrFechaCrea() {
        return strFechaCrea;
    }

    public void setStrFechaCrea(Date strFechaCrea) {
        this.strFechaCrea = strFechaCrea;
    }

    public String getStrTipoDocumento() {
        return strTipoDocumento;
    }

    public void setStrTipoDocumento(String strTipoDocumento) {
        this.strTipoDocumento = strTipoDocumento;
    }

    public String getStrNumeroDocumento() {
        return strNumeroDocumento;
    }

    public void setStrNumeroDocumento(String strNumeroDocumento) {
        this.strNumeroDocumento = strNumeroDocumento;
    }

    public String getStrNombres() {
        return strNombres;
    }

    public void setStrNombres(String strNombres) {
        this.strNombres = strNombres;
    }

    public String getStrApellidos() {
        return strApellidos;
    }

    public void setStrApellidos(String strApellidos) {
        this.strApellidos = strApellidos;
    }

    public String getStrGenero() {
        return strGenero;
    }

    public void setStrGenero(String strGenero) {
        this.strGenero = strGenero;
    }

    public String getStrEdad() {
        return strEdad;
    }

    public void setStrEdad(String strEdad) {
        this.strEdad = strEdad;
    }

    public Date getStrFechaNacimiento() {
        return strFechaNacimiento;
    }

    public void setStrFechaNacimiento(Date strFechaNacimiento) {
        this.strFechaNacimiento = strFechaNacimiento;
    }

    public String getStrRegimen() {
        return strRegimen;
    }

    public void setStrRegimen(String strRegimen) {
        this.strRegimen = strRegimen;
    }

    public String getStrNivelSisben() {
        return strNivelSisben;
    }

    public void setStrNivelSisben(String strNivelSisben) {
        this.strNivelSisben = strNivelSisben;
    }

    public String getStrTieneDiscapacidad() {
        return strTieneDiscapacidad;
    }

    public void setStrTieneDiscapacidad(String strTieneDiscapacidad) {
        this.strTieneDiscapacidad = strTieneDiscapacidad;
    }

    public String getStrTipoAfiliado() {
        return strTipoAfiliado;
    }

    public void setStrTipoAfiliado(String strTipoAfiliado) {
        this.strTipoAfiliado = strTipoAfiliado;
    }

    public String getStrEstadoAfiliado() {
        return strEstadoAfiliado;
    }

    public void setStrEstadoAfiliado(String strEstadoAfiliado) {
        this.strEstadoAfiliado = strEstadoAfiliado;
    }

    public String getStrExisteBDUA() {
        return strExisteBDUA;
    }

    public void setStrExisteBDUA(String strExisteBDUA) {
        this.strExisteBDUA = strExisteBDUA;
    }

    public String getStrCodigoUnico() {
        return strCodigoUnico;
    }

    public void setStrCodigoUnico(String strCodigoUnico) {
        this.strCodigoUnico = strCodigoUnico;
    }

    public String getStrGrupoPoblacional() {
        return strGrupoPoblacional;
    }

    public void setStrGrupoPoblacional(String strGrupoPoblacional) {
        this.strGrupoPoblacional = strGrupoPoblacional;
    }

    public String getStrGrupoEtnico() {
        return strGrupoEtnico;
    }

    public void setStrGrupoEtnico(String strGrupoEtnico) {
        this.strGrupoEtnico = strGrupoEtnico;
    }

    public String getStrIpsPrimaria() {
        return strIpsPrimaria;
    }

    public void setStrIpsPrimaria(String strIpsPrimaria) {
        this.strIpsPrimaria = strIpsPrimaria;
    }

    public String getStrSedeIpsPrimaria() {
        return strSedeIpsPrimaria;
    }

    public void setStrSedeIpsPrimaria(String strSedeIpsPrimaria) {
        this.strSedeIpsPrimaria = strSedeIpsPrimaria;
    }

    public String getStrCiudadMunicipioIpsPrimaria() {
        return strCiudadMunicipioIpsPrimaria;
    }

    public void setStrCiudadMunicipioIpsPrimaria(String strCiudadMunicipioIpsPrimaria) {
        this.strCiudadMunicipioIpsPrimaria = strCiudadMunicipioIpsPrimaria;
    }

    public String getStrDepartamentoIpsPrimaria() {
        return strDepartamentoIpsPrimaria;
    }

    public void setStrDepartamentoIpsPrimaria(String strDepartamentoIpsPrimaria) {
        this.strDepartamentoIpsPrimaria = strDepartamentoIpsPrimaria;
    }

    public String getStrTienePortabilidad() {
        return strTienePortabilidad;
    }

    public void setStrTienePortabilidad(String strTienePortabilidad) {
        this.strTienePortabilidad = strTienePortabilidad;
    }

    public String getStrSedePrestadorPortabilidad() {
        return strSedePrestadorPortabilidad;
    }

    public void setStrSedePrestadorPortabilidad(String strSedePrestadorPortabilidad) {
        this.strSedePrestadorPortabilidad = strSedePrestadorPortabilidad;
    }

    public String getStrMunicipioPrestadorPortabilidad() {
        return strMunicipioPrestadorPortabilidad;
    }

    public void setStrMunicipioPrestadorPortabilidad(String strMunicipioPrestadorPortabilidad) {
        this.strMunicipioPrestadorPortabilidad = strMunicipioPrestadorPortabilidad;
    }

    public String getStrTipoDocumentoCF() {
        return strTipoDocumentoCF;
    }

    public void setStrTipoDocumentoCF(String strTipoDocumentoCF) {
        this.strTipoDocumentoCF = strTipoDocumentoCF;
    }

    public String getStrNumeroDocumentoCF() {
        return strNumeroDocumentoCF;
    }

    public void setStrNumeroDocumentoCF(String strNumeroDocumentoCF) {
        this.strNumeroDocumentoCF = strNumeroDocumentoCF;
    }

    public String getStrModeloLiquidacion() {
        return strModeloLiquidacion;
    }

    public void setStrModeloLiquidacion(String strModeloLiquidacion) {
        this.strModeloLiquidacion = strModeloLiquidacion;
    }

    public Date getStrFechaMovilidad() {
        return strFechaMovilidad;
    }

    public void setStrFechaMovilidad(Date strFechaMovilidad) {
        this.strFechaMovilidad = strFechaMovilidad;
    }

    public String getStrFechaReactivacion() {
        return strFechaReactivacion;
    }

    public void setStrFechaReactivacion(String strFechaReactivacion) {
        this.strFechaReactivacion = strFechaReactivacion;
    }

    public Date getStrFechaRetiro() {
        return strFechaRetiro;
    }

    public void setStrFechaRetiro(Date strFechaRetiro) {
        this.strFechaRetiro = strFechaRetiro;
    }

    public Date getStrFechaAfiliacionEPS() {
        return strFechaAfiliacionEPS;
    }

    public void setStrFechaAfiliacionEPS(Date strFechaAfiliacionEPS) {
        this.strFechaAfiliacionEPS = strFechaAfiliacionEPS;
    }

    public String getStrMunicipioAfiliacion() {
        return strMunicipioAfiliacion;
    }

    public void setStrMunicipioAfiliacion(String strMunicipioAfiliacion) {
        this.strMunicipioAfiliacion = strMunicipioAfiliacion;
    }

    public String getStrMunicipioResidencia() {
        return strMunicipioResidencia;
    }

    public void setStrMunicipioResidencia(String strMunicipioResidencia) {
        this.strMunicipioResidencia = strMunicipioResidencia;
    }

    public String getStrDireccion() {
        return strDireccion;
    }

    public void setStrDireccion(String strDireccion) {
        this.strDireccion = strDireccion;
    }

    public String getStrZona() {
        return strZona;
    }

    public void setStrZona(String strZona) {
        this.strZona = strZona;
    }

    public String getStrTelefono() {
        return strTelefono;
    }

    public void setStrTelefono(String strTelefono) {
        this.strTelefono = strTelefono;
    }

    public String getStrCelular() {
        return strCelular;
    }

    public void setStrCelular(String strCelular) {
        this.strCelular = strCelular;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrTipoDocumentoIPS() {
        return strTipoDocumentoIPS;
    }

    public void setStrTipoDocumentoIPS(String strTipoDocumentoIPS) {
        this.strTipoDocumentoIPS = strTipoDocumentoIPS;
    }

    public String getStrNumeroDocumentoIPS() {
        return strNumeroDocumentoIPS;
    }

    public void setStrNumeroDocumentoIPS(String strNumeroDocumentoIPS) {
        this.strNumeroDocumentoIPS = strNumeroDocumentoIPS;
    }

    public String getStrRazonSocialIPS() {
        return strRazonSocialIPS;
    }

    public void setStrRazonSocialIPS(String strRazonSocialIPS) {
        this.strRazonSocialIPS = strRazonSocialIPS;
    }

    public String getStrCiudadMunicipioIPS() {
        return strCiudadMunicipioIPS;
    }

    public void setStrCiudadMunicipioIPS(String strCiudadMunicipioIPS) {
        this.strCiudadMunicipioIPS = strCiudadMunicipioIPS;
    }

    public String getStrDepartamentoIPS() {
        return strDepartamentoIPS;
    }

    public void setStrDepartamentoIPS(String strDepartamentoIPS) {
        this.strDepartamentoIPS = strDepartamentoIPS;
    }

    public String getStrDireccionIPS() {
        return strDireccionIPS;
    }

    public void setStrDireccionIPS(String strDireccionIPS) {
        this.strDireccionIPS = strDireccionIPS;
    }

    public String getStrTipoDocumentoProfesionalReferencia() {
        return strTipoDocumentoProfesionalReferencia;
    }

    public void setStrTipoDocumentoProfesionalReferencia(String strTipoDocumentoProfesionalReferencia) {
        this.strTipoDocumentoProfesionalReferencia = strTipoDocumentoProfesionalReferencia;
    }

    public String getStrNumeroDocumentoProfesionalReferencia() {
        return strNumeroDocumentoProfesionalReferencia;
    }

    public void setStrNumeroDocumentoProfesionalReferencia(String strNumeroDocumentoProfesionalReferencia) {
        this.strNumeroDocumentoProfesionalReferencia = strNumeroDocumentoProfesionalReferencia;
    }

    public String getStrPrimerNombreProfesionalReferencia() {
        return strPrimerNombreProfesionalReferencia;
    }

    public void setStrPrimerNombreProfesionalReferencia(String strPrimerNombreProfesionalReferencia) {
        this.strPrimerNombreProfesionalReferencia = strPrimerNombreProfesionalReferencia;
    }

    public String getStrSegundoNombreProfesionalReferencia() {
        return strSegundoNombreProfesionalReferencia;
    }

    public void setStrSegundoNombreProfesionalReferencia(String strSegundoNombreProfesionalReferencia) {
        this.strSegundoNombreProfesionalReferencia = strSegundoNombreProfesionalReferencia;
    }

    public String getStrPrimerApellidoProfesionalReferencia() {
        return strPrimerApellidoProfesionalReferencia;
    }

    public void setStrPrimerApellidoProfesionalReferencia(String strPrimerApellidoProfesionalReferencia) {
        this.strPrimerApellidoProfesionalReferencia = strPrimerApellidoProfesionalReferencia;
    }

    public String getStrSegundoApellidoProfesionalReferencia() {
        return strSegundoApellidoProfesionalReferencia;
    }

    public void setStrSegundoApellidoProfesionalReferencia(String strSegundoApellidoProfesionalReferencia) {
        this.strSegundoApellidoProfesionalReferencia = strSegundoApellidoProfesionalReferencia;
    }

    public String getStrRegistroMedicoProfesionalReferencia() {
        return strRegistroMedicoProfesionalReferencia;
    }

    public void setStrRegistroMedicoProfesionalReferencia(String strRegistroMedicoProfesionalReferencia) {
        this.strRegistroMedicoProfesionalReferencia = strRegistroMedicoProfesionalReferencia;
    }

    public String getStrEsécialidadProfesionalReferencia() {
        return strEsécialidadProfesionalReferencia;
    }

    public void setStrEsécialidadProfesionalReferencia(String strEsécialidadProfesionalReferencia) {
        this.strEsécialidadProfesionalReferencia = strEsécialidadProfesionalReferencia;
    }

    public String getStrTelefonoProfesionalReferencia() {
        return strTelefonoProfesionalReferencia;
    }

    public void setStrTelefonoProfesionalReferencia(String strTelefonoProfesionalReferencia) {
        this.strTelefonoProfesionalReferencia = strTelefonoProfesionalReferencia;
    }

    public String getStrServicioRefiere() {
        return strServicioRefiere;
    }

    public void setStrServicioRefiere(String strServicioRefiere) {
        this.strServicioRefiere = strServicioRefiere;
    }

    public String getStrServicioSolicita() {
        return strServicioSolicita;
    }

    public void setStrServicioSolicita(String strServicioSolicita) {
        this.strServicioSolicita = strServicioSolicita;
    }

    public String getStrServicioCama() {
        return strServicioCama;
    }

    public void setStrServicioCama(String strServicioCama) {
        this.strServicioCama = strServicioCama;
    }

    public String getStrServicioPiso() {
        return strServicioPiso;
    }

    public void setStrServicioPiso(String strServicioPiso) {
        this.strServicioPiso = strServicioPiso;
    }

    public String getStrNombreInformante() {
        return strNombreInformante;
    }

    public void setStrNombreInformante(String strNombreInformante) {
        this.strNombreInformante = strNombreInformante;
    }

    public String getStrCargoInformante() {
        return strCargoInformante;
    }

    public void setStrCargoInformante(String strCargoInformante) {
        this.strCargoInformante = strCargoInformante;
    }

    public String getStrTelefonoInformante() {
        return strTelefonoInformante;
    }

    public void setStrTelefonoInformante(String strTelefonoInformante) {
        this.strTelefonoInformante = strTelefonoInformante;
    }

    public BigDecimal getStrTemperaturaC() {
        return strTemperaturaC;
    }

    public void setStrTemperaturaC(BigDecimal strTemperaturaC) {
        this.strTemperaturaC = strTemperaturaC;
    }

    public BigDecimal getStrFrecuenciaCardiaca() {
        return strFrecuenciaCardiaca;
    }

    public void setStrFrecuenciaCardiaca(BigDecimal strFrecuenciaCardiaca) {
        this.strFrecuenciaCardiaca = strFrecuenciaCardiaca;
    }

    public BigDecimal getStrFrecuenciaRespiratoria() {
        return strFrecuenciaRespiratoria;
    }

    public void setStrFrecuenciaRespiratoria(BigDecimal strFrecuenciaRespiratoria) {
        this.strFrecuenciaRespiratoria = strFrecuenciaRespiratoria;
    }

    public BigDecimal getStrTensionArterialS() {
        return strTensionArterialS;
    }

    public void setStrTensionArterialS(BigDecimal strTensionArterialS) {
        this.strTensionArterialS = strTensionArterialS;
    }

    public BigDecimal getStrTensionArterialD() {
        return strTensionArterialD;
    }

    public void setStrTensionArterialD(BigDecimal strTensionArterialD) {
        this.strTensionArterialD = strTensionArterialD;
    }

    public BigDecimal getStrSaturacionOxigeno() {
        return strSaturacionOxigeno;
    }

    public void setStrSaturacionOxigeno(BigDecimal strSaturacionOxigeno) {
        this.strSaturacionOxigeno = strSaturacionOxigeno;
    }

    public BigDecimal getStrPeso() {
        return strPeso;
    }

    public void setStrPeso(BigDecimal strPeso) {
        this.strPeso = strPeso;
    }

    public Integer getStrTalla() {
        return strTalla;
    }

    public void setStrTalla(Integer strTalla) {
        this.strTalla = strTalla;
    }

    public BigDecimal getStrImc() {
        return strImc;
    }

    public void setStrImc(BigDecimal strImc) {
        this.strImc = strImc;
    }

    public Integer getStrEscalaGlasglow() {
        return strEscalaGlasglow;
    }

    public void setStrEscalaGlasglow(Integer strEscalaGlasglow) {
        this.strEscalaGlasglow = strEscalaGlasglow;
    }

    public String getStrEscalaGlasglowDescripcion() {
        return strEscalaGlasglowDescripcion;
    }

    public void setStrEscalaGlasglowDescripcion(String strEscalaGlasglowDescripcion) {
        this.strEscalaGlasglowDescripcion = strEscalaGlasglowDescripcion;
    }

    public String getStrResumenEnfermedadActual() {
        return strResumenEnfermedadActual;
    }

    public void setStrResumenEnfermedadActual(String strResumenEnfermedadActual) {
        this.strResumenEnfermedadActual = strResumenEnfermedadActual;
    }

    public String getStrAntecedentes() {
        return strAntecedentes;
    }

    public void setStrAntecedentes(String strAntecedentes) {
        this.strAntecedentes = strAntecedentes;
    }

    public Integer getStrTriageNivel() {
        return strTriageNivel;
    }

    public void setStrTriageNivel(Integer strTriageNivel) {
        this.strTriageNivel = strTriageNivel;
    }

    public String getStrMotivosRemisionDescripcion() {
        return strMotivosRemisionDescripcion;
    }

    public void setStrMotivosRemisionDescripcion(String strMotivosRemisionDescripcion) {
        this.strMotivosRemisionDescripcion = strMotivosRemisionDescripcion;
    }

    public String getStrFechaHoraAceptacionReceptora() {
        return strFechaHoraAceptacionReceptora;
    }

    public void setStrFechaHoraAceptacionReceptora(String strFechaHoraAceptacionReceptora) {
        this.strFechaHoraAceptacionReceptora = strFechaHoraAceptacionReceptora;
    }

    public Integer getIntTotalDiasRemision() {
        return intTotalDiasRemision;
    }

    public void setIntTotalDiasRemision(Integer intTotalDiasRemision) {
        this.intTotalDiasRemision = intTotalDiasRemision;
    }

    public String getStrTipoDocumentoIPSReceptora() {
        return strTipoDocumentoIPSReceptora;
    }

    public void setStrTipoDocumentoIPSReceptora(String strTipoDocumentoIPSReceptora) {
        this.strTipoDocumentoIPSReceptora = strTipoDocumentoIPSReceptora;
    }

    public String getStrNumeroDocumentoIPSReceptora() {
        return strNumeroDocumentoIPSReceptora;
    }

    public void setStrNumeroDocumentoIPSReceptora(String strNumeroDocumentoIPSReceptora) {
        this.strNumeroDocumentoIPSReceptora = strNumeroDocumentoIPSReceptora;
    }

    public String getStrRazonSocialIPSReceptora() {
        return strRazonSocialIPSReceptora;
    }

    public void setStrRazonSocialIPSReceptora(String strRazonSocialIPSReceptora) {
        this.strRazonSocialIPSReceptora = strRazonSocialIPSReceptora;
    }

    public String getStrCiudadMunicipioIPSReceptora() {
        return strCiudadMunicipioIPSReceptora;
    }

    public void setStrCiudadMunicipioIPSReceptora(String strCiudadMunicipioIPSReceptora) {
        this.strCiudadMunicipioIPSReceptora = strCiudadMunicipioIPSReceptora;
    }

    public String getStrDepartamentoIPSReceptora() {
        return strDepartamentoIPSReceptora;
    }

    public void setStrDepartamentoIPSReceptora(String strDepartamentoIPSReceptora) {
        this.strDepartamentoIPSReceptora = strDepartamentoIPSReceptora;
    }

    public String getStrDireccionIPSReceptora() {
        return strDireccionIPSReceptora;
    }

    public void setStrDireccionIPSReceptora(String strDireccionIPSReceptora) {
        this.strDireccionIPSReceptora = strDireccionIPSReceptora;
    }

    public List<RefAnexo9Diagnostico> getListaDiagnosticos() {
        return listaDiagnosticos;
    }

    public void setListaDiagnosticos(List<RefAnexo9Diagnostico> listaDiagnosticos) {
        this.listaDiagnosticos = listaDiagnosticos;
    }

    public List<RefAnexo9Gestion> getListaRefAnexo9Gestion() {
        return listaRefAnexo9Gestion;
    }

    public void setListaRefAnexo9Gestion(List<RefAnexo9Gestion> listaRefAnexo9Gestion) {
        this.listaRefAnexo9Gestion = listaRefAnexo9Gestion;
    }
    
}
