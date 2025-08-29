
package com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigInteger;


public class AfiliadoNovedadesDTO extends Auditoria {

    @SerializedName("consecutivo")
    private int consecutivo;
    @SerializedName("afiliadoTipoRegistro")
    private int afiliadoTipoRegistro;
    @SerializedName("afiliadoContrato")
    private String afiliadoContrato;
    @SerializedName("afiliadoRegistroBDUA")
    private String afiliadoRegistroBDUA;
    @SerializedName("afiliadoSerialBDUA")
    private BigInteger afiliadoSerialBDUA;
    @SerializedName("afiliadoTipoDocumento")
    private String afiliadoTipoDocumento;
    @SerializedName("afiliadoNumeroDocumento")
    private String afiliadoNumeroDocumento;
    @SerializedName("afiliadoFechaExpedicionCedula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaExpedicionCedula;
    @SerializedName("afiliadoPrimerApellido")
    private String afiliadoPrimerApellido;
    @SerializedName("afiliadoSegundoApellido")
    private String afiliadoSegundoApellido;
    @SerializedName("afiliadoPrimerNombre")
    private String afiliadoPrimerNombre;
    @SerializedName("afiliadoSegundoNombre")
    private String afiliadoSegundoNombre;
    @SerializedName("afiliadoFechaNacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaNacimiento;
    @SerializedName("afiliadoGenero")
    private String afiliadoGenero;
    @SerializedName("afiliadoTipoDocumentoBDUA")
    private String afiliadoTipoDocumentoBDUA;
    @SerializedName("afiliadoNumeroDocumentoBDUA")
    private String afiliadoNumeroDocumentoBDUA;
    @SerializedName("afiliadoPrimerApellidoBDUA")
    private String afiliadoPrimerApellidoBDUA;
    @SerializedName("afiliadoSegundoApellidoBDUA")
    private String afiliadoSegundoApellidoBDUA;
    @SerializedName("afiliadoPrimerNombreBDUA")
    private String afiliadoPrimerNombreBDUA;
    @SerializedName("afiliadoSegundoNombreBDUA")
    private String afiliadoSegundoNombreBDUA;
    @SerializedName("afiliadoFechaNacimientoBDUA")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaNacimientoBDUA;
    @SerializedName("afiliadoEPSBDUA")
    private String afiliadoEPSBDUA;
    @SerializedName("afiliadoEstadoCivil")
    private String afiliadoEstadoCivil;
    @SerializedName("afiliadoOrigen")
    private String afiliadoOrigen;
    @SerializedName("afiliadoTipoBeneficiario")
    private String afiliadoTipoBeneficiario;
    @SerializedName("afiliadoParentescoCotizante")
    private String afiliadoParentescoCotizante;
    @SerializedName("afiliadoTipoDocumentoCF")
    private String afiliadoTipoDocumentoCF;
    @SerializedName("afiliadoNumeroDocumentoCF")
    private String afiliadoNumeroDocumentoCF;
    @SerializedName("afiliadoRegimen")
    private String afiliadoRegimen;
    @SerializedName("afiliadoFechaMovilidad")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaMovilidad;
    @SerializedName("afiliadoCategoríaIBC")
    private String afiliadoCategoríaIBC;
    @SerializedName("afiliadoTipoCotizante")
    private String afiliadoTipoCotizante;
    @SerializedName("afiliadoTipoDocumentoAportante")
    private String afiliadoTipoDocumentoAportante;
    @SerializedName("afiliadoNumeroDocumentoAportante")
    private String afiliadoNumeroDocumentoAportante;
    @SerializedName("afiliadoActividadEconomica")
    private int afiliadoActividadEconomica;
    @SerializedName("afiliadoARL")
    private String afiliadoARL;
    @SerializedName("afiliadoAFP")
    private int afiliadoAFP;
    @SerializedName("afiliadoCCF")
    private String afiliadoCCF;
    @SerializedName("afiliadoFechaAfiliacionSGSSS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaAfiliacionSGSSS;
    @SerializedName("afiliadoFechaAfiliacionEPS")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaAfiliacionEPS;
    @SerializedName("afiliadoMunicipioAfiliacion")
    private String afiliadoMunicipioAfiliacion;
    @SerializedName("afiliadoMunicipioResidencia")
    private String afiliadoMunicipioResidencia;
    @SerializedName("afiliadoDireccionResidencia")
    private String afiliadoDireccionResidencia;
    @SerializedName("afiliadoBarrio")
    private String afiliadoBarrio;
    @SerializedName("afiliadoZona")
    private String afiliadoZona;
    @SerializedName("afiliadoTelefonoFijo")
    private String afiliadoTelefonoFijo;
    @SerializedName("afiliadoTelefonoMovil")
    private String afiliadoTelefonoMovil;
    @SerializedName("afiliadoCorreoElectronico")
    private String afiliadoCorreoElectronico;
    @SerializedName("afiliadoAutorizaSMS")
    private String afiliadoAutorizaSMS;
    @SerializedName("afiliadoAutorizaCorreoElectronico")
    private String afiliadoAutorizaCorreoElectronico;
    @SerializedName("AfiliadoCodigoHabilitacionIPSPrimaria")
    private String AfiliadoCodigoHabilitacionIPSPrimaria;
    @SerializedName("AfiliadoGrupoPoblacional")
    private int AfiliadoGrupoPoblacional;
    @SerializedName("afiliadoVictima")
    private String afiliadoVictima;
    @SerializedName("afiliadoEtnia")
    private String afiliadoEtnia;
    @SerializedName("afiliadoNivelSisben")
    private String afiliadoNivelSisben;
    @SerializedName("afiliadoPuntajeSisben")
    private Double afiliadoPuntajeSisben;
    @SerializedName("afiliadoFichaSisben")
    private String afiliadoFichaSisben;
    @SerializedName("afiliadoFechaSisben")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaSisben;
    @SerializedName("afiliadoDiscapacidad")
    private String afiliadoDiscapacidad;
    @SerializedName("afiliadoTipoDiscapacidad")
    private String afiliadoTipoDiscapacidad;
    @SerializedName("afiliadoCondicionDiscapacidad")
    private String afiliadoCondicionDiscapacidad;
    @SerializedName("afiliadoFechainicioDiscapacidad")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private String afiliadoFechainicioDiscapacidad;
    @SerializedName("afiliadoFechaFinDiscapacidad")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private String afiliadoFechaFinDiscapacidad;
    @SerializedName("afiliadoModeloLiquidacion")
    private String afiliadoModeloLiquidacion;
    @SerializedName("afiliadoEstadoAfiliacion")
    private String afiliadoEstadoAfiliacion;
    @SerializedName("afiliadoCausaNovedad")
    private String afiliadoCausaNovedad;
    @SerializedName("afiliadoFechaNovedad")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone ="America/Bogota")
    private Date afiliadoFechaNovedad;
    @SerializedName("observacion")
    private String observacion;
    @SerializedName("afiliadoSubGruposisben")
    private String afiliadoSubGruposisben;
    @SerializedName("usuarioCrea")
    private String usuarioCrea;
    

    public List<String> errores = new ArrayList();
    public List<String> valoresNoPermitidosTelefonoFijo = new ArrayList();

    public List<String> getErrores() {
        return errores;
    }

    public String getErroresStr() {
        String strError = "";
        for (String str : errores) {
            String cambio = str.replace(',', '|');
            strError += cambio + ". ";
        }
        return strError;
    }

    public boolean isError() {
        return (!this.errores.isEmpty());
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getAfiliadoTipoRegistro() {
        return afiliadoTipoRegistro;
    }

    public void setAfiliadoTipoRegistro(int afiliadoTipoRegistro) {
        this.afiliadoTipoRegistro = afiliadoTipoRegistro;
    }

    public String getAfiliadoContrato() {
        return afiliadoContrato;
    }

    public void setAfiliadoContrato(String afiliadoContrato) {
        this.afiliadoContrato = afiliadoContrato;
    }

    public String getAfiliadoRegistroBDUA() {
        return afiliadoRegistroBDUA;
    }

    public void setAfiliadoRegistroBDUA(String afiliadoRegistroBDUA) {
        this.afiliadoRegistroBDUA = afiliadoRegistroBDUA;
    }

    public BigInteger getAfiliadoSerialBDUA() {
        return afiliadoSerialBDUA;
    }

    public void setAfiliadoSerialBDUA(BigInteger afiliadoSerialBDUA) {
        this.afiliadoSerialBDUA = afiliadoSerialBDUA;
    }

    public String getAfiliadoTipoDocumento() {
        return afiliadoTipoDocumento;
    }

    public void setAfiliadoTipoDocumento(String afiliadoTipoDocumento) {
        this.afiliadoTipoDocumento = afiliadoTipoDocumento;
    }

    public String getAfiliadoNumeroDocumento() {
        return afiliadoNumeroDocumento;
    }

    public void setAfiliadoNumeroDocumento(String afiliadoNumeroDocumento) {
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
    }

    public Date getAfiliadoFechaExpedicionCedula() {
        return afiliadoFechaExpedicionCedula;
    }

    public void setAfiliadoFechaExpedicionCedula(Date afiliadoFechaExpedicionCedula) {
        this.afiliadoFechaExpedicionCedula = afiliadoFechaExpedicionCedula;
    }

    public String getAfiliadoPrimerNombre() {
        return afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    public String getAfiliadoPrimerApellido() {
        return afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    public Date getAfiliadoFechaNacimiento() {
        return afiliadoFechaNacimiento;
    }

    public void setAfiliadoFechaNacimiento(Date afiliadoFechaNacimiento) {
        this.afiliadoFechaNacimiento = afiliadoFechaNacimiento;
    }

    public String getAfiliadoGenero() {
        return afiliadoGenero;
    }

    public void setAfiliadoGenero(String afiliadoGenero) {
        this.afiliadoGenero = afiliadoGenero;
    }

    public String getAfiliadoEstadoCivil() {
        return afiliadoEstadoCivil;
    }

    public void setAfiliadoEstadoCivil(String afiliadoEstadoCivil) {
        this.afiliadoEstadoCivil = afiliadoEstadoCivil;
    }

    public String getAfiliadoOrigen() {
        return afiliadoOrigen;
    }

    public void setAfiliadoOrigen(String afiliadoOrigen) {
        this.afiliadoOrigen = afiliadoOrigen;
    }

    public String getAfiliadoTipoBeneficiario() {
        return afiliadoTipoBeneficiario;
    }

    public void setAfiliadoTipoBeneficiario(String afiliadoTipoBeneficiario) {
        this.afiliadoTipoBeneficiario = afiliadoTipoBeneficiario;
    }

    public String getAfiliadoParentescoCotizante() {
        return afiliadoParentescoCotizante;
    }

    public void setAfiliadoParentescoCotizante(String afiliadoParentescoCotizante) {
        this.afiliadoParentescoCotizante = afiliadoParentescoCotizante;
    }

    public String getAfiliadoTipoDocumentoCF() {
        return afiliadoTipoDocumentoCF;
    }

    public void setAfiliadoTipoDocumentoCF(String afiliadoTipoDocumentoCF) {
        this.afiliadoTipoDocumentoCF = afiliadoTipoDocumentoCF;
    }

    public String getAfiliadoRegimen() {
        return afiliadoRegimen;
    }

    public void setAfiliadoRegimen(String afiliadoRegimen) {
        this.afiliadoRegimen = afiliadoRegimen;
    }

    public Date getAfiliadoFechaMovilidad() {
        return afiliadoFechaMovilidad;
    }

    public void setAfiliadoFechaMovilidad(Date afiliadoFechaMovilidad) {
        this.afiliadoFechaMovilidad = afiliadoFechaMovilidad;
    }

    public String getAfiliadoCategoríaIBC() {
        return afiliadoCategoríaIBC;
    }

    public void setAfiliadoCategoríaIBC(String afiliadoCategoríaIBC) {
        this.afiliadoCategoríaIBC = afiliadoCategoríaIBC;
    }

    public String getAfiliadoTipoCotizante() {
        return afiliadoTipoCotizante;
    }

    public void setAfiliadoTipoCotizante(String afiliadoTipoCotizante) {
        this.afiliadoTipoCotizante = afiliadoTipoCotizante;
    }

    public String getAfiliadoTipoDocumentoAportante() {
        return afiliadoTipoDocumentoAportante;
    }

    public void setAfiliadoTipoDocumentoAportante(String afiliadoTipoDocumentoAportante) {
        this.afiliadoTipoDocumentoAportante = afiliadoTipoDocumentoAportante;
    }

    public String getAfiliadoNumeroDocumentoAportante() {
        return afiliadoNumeroDocumentoAportante;
    }

    public void setAfiliadoNumeroDocumentoAportante(String afiliadoNumeroDocumentoAportante) {
        this.afiliadoNumeroDocumentoAportante = afiliadoNumeroDocumentoAportante;
    }

    public int getAfiliadoActividadEconomica() {
        return afiliadoActividadEconomica;
    }

    public void setAfiliadoActividadEconomica(int afiliadoActividadEconomica) {
        this.afiliadoActividadEconomica = afiliadoActividadEconomica;
    }

    public String getAfiliadoARL() {
        return afiliadoARL;
    }

    public void setAfiliadoARL(String afiliadoARL) {
        this.afiliadoARL = afiliadoARL;
    }

    public int getAfiliadoAFP() {
        return afiliadoAFP;
    }

    public void setAfiliadoAFP(int afiliadoAFP) {
        this.afiliadoAFP = afiliadoAFP;
    }

    public String getAfiliadoCCF() {
        return afiliadoCCF;
    }

    public void setAfiliadoCCF(String afiliadoCCF) {
        this.afiliadoCCF = afiliadoCCF;
    }

    public Date getAfiliadoFechaAfiliacionSGSSS() {
        return afiliadoFechaAfiliacionSGSSS;
    }

    public void setAfiliadoFechaAfiliacionSGSSS(Date afiliadoFechaAfiliacionSGSSS) {
        this.afiliadoFechaAfiliacionSGSSS = afiliadoFechaAfiliacionSGSSS;
    }

    public Date getAfiliadoFechaAfiliacionEPS() {
        return afiliadoFechaAfiliacionEPS;
    }

    public void setAfiliadoFechaAfiliacionEPS(Date afiliadoFechaAfiliacionEPS) {
        this.afiliadoFechaAfiliacionEPS = afiliadoFechaAfiliacionEPS;
    }

    public String getAfiliadoMunicipioAfiliacion() {
        return afiliadoMunicipioAfiliacion;
    }

    public void setAfiliadoMunicipioAfiliacion(String afiliadoMunicipioAfiliacion) {
        this.afiliadoMunicipioAfiliacion = afiliadoMunicipioAfiliacion;
    }

    public String getAfiliadoMunicipioResidencia() {
        return afiliadoMunicipioResidencia;
    }

    public void setAfiliadoMunicipioResidencia(String afiliadoMunicipioResidencia) {
        this.afiliadoMunicipioResidencia = afiliadoMunicipioResidencia;
    }

    public String getAfiliadoDireccionResidencia() {
        return afiliadoDireccionResidencia;
    }

    public void setAfiliadoDireccionResidencia(String afiliadoDireccionResidencia) {
        this.afiliadoDireccionResidencia = afiliadoDireccionResidencia;
    }

    public String getAfiliadoBarrio() {
        return afiliadoBarrio;
    }

    public void setAfiliadoBarrio(String afiliadoBarrio) {
        this.afiliadoBarrio = afiliadoBarrio;
    }

    public String getAfiliadoZona() {
        return afiliadoZona;
    }

    public void setAfiliadoZona(String afiliadoZona) {
        this.afiliadoZona = afiliadoZona;
    }

    public String getAfiliadoTelefonoFijo() {
        return afiliadoTelefonoFijo;
    }

    public void setAfiliadoTelefonoFijo(String afiliadoTelefonoFijo) {
        this.afiliadoTelefonoFijo = afiliadoTelefonoFijo;
    }

    public String getAfiliadoTelefonoMovil() {
        return afiliadoTelefonoMovil;
    }

    public void setAfiliadoTelefonoMovil(String afiliadoTelefonoMovil) {
        this.afiliadoTelefonoMovil = afiliadoTelefonoMovil;
    }

    public String getAfiliadoCorreoElectronico() {
        return afiliadoCorreoElectronico;
    }

    public void setAfiliadoCorreoElectronico(String afiliadoCorreoElectronico) {
        this.afiliadoCorreoElectronico = afiliadoCorreoElectronico;
    }

    public String isAfiliadoAutorizaSMS() {
        return afiliadoAutorizaSMS;
    }

    public void setAfiliadoAutorizaSMS(String afiliadoAutorizaSMS) {

        this.afiliadoAutorizaSMS = afiliadoAutorizaSMS;
    }

    public String getAfiliadoAutorizaCorreoElectronico() {
        return afiliadoAutorizaCorreoElectronico;
    }

    public void setAfiliadoAutorizaCorreoElectronico(String afiliadoAutorizaCorreoElectronico) {
        this.afiliadoAutorizaCorreoElectronico = afiliadoAutorizaCorreoElectronico;
    }

    public String getAfiliadoCodigoHabilitacionIPSPrimaria() {
        return AfiliadoCodigoHabilitacionIPSPrimaria;
    }

    public void setAfiliadoCodigoHabilitacionIPSPrimaria(String AfiliadoCodigoHabilitacionIPSPrimaria) {
        this.AfiliadoCodigoHabilitacionIPSPrimaria = AfiliadoCodigoHabilitacionIPSPrimaria;
    }

    public int getAfiliadoGrupoPoblacional() {
        return AfiliadoGrupoPoblacional;
    }

    public void setAfiliadoGrupoPoblacional(int AfiliadoGrupoPoblacional) {
        this.AfiliadoGrupoPoblacional = AfiliadoGrupoPoblacional;
    }

    public String isAfiliadoVictima() {
        return afiliadoVictima;
    }

    public void setAfiliadoVictima(String afiliadoVictima) {
        this.afiliadoVictima = afiliadoVictima;
    }

    public String getAfiliadoEtnia() {
        return afiliadoEtnia;
    }

    public void setAfiliadoEtnia(String afiliadoEtnia) {
        this.afiliadoEtnia = afiliadoEtnia;
    }

    public String getAfiliadoNivelSisben() {
        return afiliadoNivelSisben;
    }

    public void setAfiliadoNivelSisben(String afiliadoNivelSisben) {
        this.afiliadoNivelSisben = afiliadoNivelSisben;
    }

    public Double getAfiliadoPuntajeSisben() {
        return afiliadoPuntajeSisben;
    }

    public void setAfiliadoPuntajeSisben(Double afiliadoPuntajeSisben) {
        this.afiliadoPuntajeSisben = afiliadoPuntajeSisben;
    }

    public String getAfiliadoFichaSisben() {
        return afiliadoFichaSisben;
    }

    public void setAfiliadoFichaSisben(String afiliadoFichaSisben) {
        this.afiliadoFichaSisben = afiliadoFichaSisben;
    }

    public Date getAfiliadoFechaSisben() {
        return afiliadoFechaSisben;
    }

    public void setAfiliadoFechaSisben(Date afiliadoFechaSisben) {
        this.afiliadoFechaSisben = afiliadoFechaSisben;
    }

    public String getAfiliadoDiscapacidad() {
        return afiliadoDiscapacidad;
    }

    public void setAfiliadoDiscapacidad(String afiliadoDiscapacidad) {
        this.afiliadoDiscapacidad = afiliadoDiscapacidad;
    }

    public String getAfiliadoTipoDiscapacidad() {
        return afiliadoTipoDiscapacidad;
    }

    public void setAfiliadoTipoDiscapacidad(String afiliadoTipoDiscapacidad) {
        this.afiliadoTipoDiscapacidad = afiliadoTipoDiscapacidad;
    }

    public String getAfiliadoCondicionDiscapacidad() {
        return afiliadoCondicionDiscapacidad;
    }

    public void setAfiliadoCondicionDiscapacidad(String afiliadoCondicionDiscapacidad) {
        this.afiliadoCondicionDiscapacidad = afiliadoCondicionDiscapacidad;
    }

    public String getAfiliadoFechainicioDiscapacidad() {
        return afiliadoFechainicioDiscapacidad;
    }

    public void setAfiliadoFechainicioDiscapacidad(String afiliadoFechainicioDiscapacidad) {
        this.afiliadoFechainicioDiscapacidad = afiliadoFechainicioDiscapacidad;
    }

    public String getAfiliadoFechaFinDiscapacidad() {
        return afiliadoFechaFinDiscapacidad;
    }

    public void setAfiliadoFechaFinDiscapacidad(String afiliadoFechaFinDiscapacidad) {
        this.afiliadoFechaFinDiscapacidad = afiliadoFechaFinDiscapacidad;
    }

    public String getAfiliadoModeloLiquidacion() {
        return afiliadoModeloLiquidacion;
    }

    public void setAfiliadoModeloLiquidacion(String afiliadoModeloLiquidacion) {
        this.afiliadoModeloLiquidacion = afiliadoModeloLiquidacion;
    }

    public String getAfiliadoEstadoAfiliacion() {
        return afiliadoEstadoAfiliacion;
    }

    public void setAfiliadoEstadoAfiliacion(String afiliadoEstadoAfiliacion) {
        this.afiliadoEstadoAfiliacion = afiliadoEstadoAfiliacion;
    }

    public Date getAfiliadoFechaNovedad() {
        return afiliadoFechaNovedad;
    }

    public void setAfiliadoFechaNovedad(Date afiliadoFechaNovedad) {
        this.afiliadoFechaNovedad = afiliadoFechaNovedad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getAfiliadoSubGruposisben() {
        return afiliadoSubGruposisben;
    }

    public void setAfiliadoSubGruposisben(String afiliadoSubGruposisben) {
        this.afiliadoSubGruposisben = afiliadoSubGruposisben;
    }

    public String getAfiliadoAutorizaSMS() {
        return afiliadoAutorizaSMS;
    }

    public String getAfiliadoVictima() {
        return afiliadoVictima;
    }

    public String getAfiliadoSegundoApellido() {
        return afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    public String getAfiliadoSegundoNombre() {
        return afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    public String getAfiliadoTipoDocumentoBDUA() {
        return afiliadoTipoDocumentoBDUA;
    }

    public void setAfiliadoTipoDocumentoBDUA(String afiliadoTipoDocumentoBDUA) {
        this.afiliadoTipoDocumentoBDUA = afiliadoTipoDocumentoBDUA;
    }

    public String getAfiliadoNumeroDocumentoBDUA() {
        return afiliadoNumeroDocumentoBDUA;
    }

    public void setAfilaidoNumeroDocumentoBDUA(String afiliadoNumeroDocumentoBDUA) {
        this.afiliadoNumeroDocumentoBDUA = afiliadoNumeroDocumentoBDUA;
    }

    public String getAfiliadoPrimerApellidoBDUA() {
        return afiliadoPrimerApellidoBDUA;
    }

    public void setAfiliadoPrimerApellidoBDUA(String afiliadoPrimerApellidoBDUA) {
        this.afiliadoPrimerApellidoBDUA = afiliadoPrimerApellidoBDUA;
    }

    public String getAfiliadoSegundoApellidoBDUA() {
        return afiliadoSegundoApellidoBDUA;
    }

    public void setAfiliadoSegundoApellidoBDUA(String afiliadoSegundoApellidoBDUA) {
        this.afiliadoSegundoApellidoBDUA = afiliadoSegundoApellidoBDUA;
    }

    public String getAfiliadoPrimerNombreBDUA() {
        return afiliadoPrimerNombreBDUA;
    }

    public void setAfiliadoPrimerNombreBDUA(String afiliadoPrimerNombreBDUA) {
        this.afiliadoPrimerNombreBDUA = afiliadoPrimerNombreBDUA;
    }

    public String getAfiliadoSegundoNombreBDUA() {
        return afiliadoSegundoNombreBDUA;
    }

    public void setAfiliadoSegundoNombreBDUA(String afiliadoSegundoNombreBDUA) {
        this.afiliadoSegundoNombreBDUA = afiliadoSegundoNombreBDUA;
    }

    public Date getAfiliadoFechaNacimientoBDUA() {
        return afiliadoFechaNacimientoBDUA;
    }

    public void setAfiliadoFechaNacimientoBDUA(Date afiliadoFechaNacimientoBDUA) {
        this.afiliadoFechaNacimientoBDUA = afiliadoFechaNacimientoBDUA;
    }

    public String getAfiliadoEPSBDUA() {
        return afiliadoEPSBDUA;
    }

    public void setAfiliadoEPSBDUA(String afiliadoEPSBDUA) {
        this.afiliadoEPSBDUA = afiliadoEPSBDUA;
    }

    public String getAfiliadoNumeroDocumentoCF() {
        return afiliadoNumeroDocumentoCF;
    }

    public void setAfiliadoNumeroDocumentoCF(String afiliadoNumeroDocumentoCF) {
        this.afiliadoNumeroDocumentoCF = afiliadoNumeroDocumentoCF;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getAfiliadoCausaNovedad() {
        return afiliadoCausaNovedad;
    }

    public void setAfiliadoCausaNovedad(String afiliadoCausaNovedad) {
        this.afiliadoCausaNovedad = afiliadoCausaNovedad;
    }
}
