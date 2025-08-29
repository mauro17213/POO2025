/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class ReporteAfiliacion implements Serializable {
    
    private String id;
    private String strNumeroRadicacion;
    private Date dtmFechaRadicacion;
    private String strTipoTramite;
    private String strTipoAfiliacion;
    private String strRegimen;
    private String strTipoAfiliado;
    private String strCodigo;
    private String strPrimerApellido;
    private String strSegundoApellido;
    private String strPrimerNombre;
    private String strSegundoNombre;
    private String strTipoDocumento;
    private String strNumeroDocumento;
    private String strSexo;
    private Date dtmFechaNacimiento;
    private String strEtnia;
    private String strTipoDiscapacidad;
    private String strCondicionDiscapacidad;
    private String strPuntajeSisben;
    private String strGrupoPoblacionEspecial;
    private String strNombreARL;
    private String strNombrePensiones;
    private String strDireccionResidencia;
    private String strTelefono;
    private String strCelular;
    private String strCorreoElectronico;
    private String strMunicipio;
    private String strTipoZona;
    private String strDepartamento;
    private String strPrimerApellidoCompanero;
    private String strSegundoApellidoCompanero;
    private String strPrimerNombreCompanero;
    private String strSegundoNombreCompanero;
    private String strTipoDocumentoCompanero;
    private String strNumeroDocumentoCompanero;
    private String strSexoCompanero;
    private Date dtmFechaNacimientoCompanero;
    private String strPrimerApellidoBeneficiario;
    private String strSegundoApellidoBeneficiario;
    private String strPrimerNombreBeneficiario;
    private String strSegundoNombreBeneficiario;
    private String strTipoDocumentoBeneficiario;
    private String strNumeroDocumentoBeneficiario;
    private String strSexoBeneficiario;
    private Date dtmFechaNacimientoBeneficiario;
    private String strParentescoBeneficiario;
    private String strEtniaBeneficiario;
    private String strTipoDiscapacidadBeneficiario;
    private String strCondicionDiscapacidadBeneficiario;
    private String strMunicipioBeneficiario;
    private String strZonaBeneficiario;
    private String strDepartamentoBeneficiario;
    private String strTelefonoBeneficiario;
    private String strIpsPrimaria;
    private String strCodigoIpsPrimaria;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStrNumeroRadicacion() {
        return strNumeroRadicacion;
    }

    public void setStrNumeroRadicacion(String strNumeroRadicacion) {
        this.strNumeroRadicacion = strNumeroRadicacion;
    }

    public Date getDtmFechaRadicacion() {
        return dtmFechaRadicacion;
    }

    public void setDtmFechaRadicacion(Date dtmFechaRadicacion) {
        this.dtmFechaRadicacion = dtmFechaRadicacion;
    }

    public String getStrTipoTramite() {
        return strTipoTramite;
    }

    public void setStrTipoTramite(String strTipoTramite) {
        this.strTipoTramite = strTipoTramite;
    }

    public String getStrTipoAfiliacion() {
        return strTipoAfiliacion;
    }

    public void setStrTipoAfiliacion(String strTipoAfiliacion) {
        switch (strTipoAfiliacion) {
            case "101":
                strTipoAfiliacion = "A1";
                break;
            case "102":
                strTipoAfiliacion = "A2";
                break;
            case "103":
                strTipoAfiliacion = "A1";
                break;
            case "104":
                strTipoAfiliacion = "A2";
                break;
            default:
                strTipoAfiliacion = "";
                break;
        }
        this.strTipoAfiliacion = strTipoAfiliacion;
    }

    public String getStrRegimen() {
        return strRegimen;
    }

    public void setStrRegimen(String strRegimen) {
        switch (strRegimen) {
            case "1":
                strRegimen = "S";
                setStrCodigo("EPSS40");
                break;
            case "2":
                strRegimen = "C";
                setStrCodigo("EPS040");
                break;
            default:
                strRegimen = "";
                break;
        }
        
        this.strRegimen = strRegimen;
    }

    public String getStrTipoAfiliado() {
        return strTipoAfiliado;
    }

    public void setStrTipoAfiliado(String strTipoAfiliado) {
        switch (strTipoAfiliado) {
            case "101":
                strTipoAfiliado = "A";
                break;
            case "102":
                strTipoAfiliado = "C";
                break;
            case "103":
                strTipoAfiliado = "B";
                break;
            case "104":
                strTipoAfiliado = "C";
                break;
            default:
                strTipoAfiliado = "";
                break;
        }
        this.strTipoAfiliado = strTipoAfiliado;
    }

    public String getStrCodigo() {
        return strCodigo;
    }

    public void setStrCodigo(String strCodigo) {
        this.strCodigo = strCodigo;
    }

    public String getStrPrimerApellido() {
        return strPrimerApellido;
    }

    public void setStrPrimerApellido(String strPrimerApellido) {
        this.strPrimerApellido = strPrimerApellido;
    }

    public String getStrSegundoApellido() {
        return strSegundoApellido;
    }

    public void setStrSegundoApellido(String strSegundoApellido) {
        this.strSegundoApellido = strSegundoApellido;
    }

    public String getStrPrimerNombre() {
        return strPrimerNombre;
    }

    public void setStrPrimerNombre(String strPrimerNombre) {
        this.strPrimerNombre = strPrimerNombre;
    }

    public String getStrSegundoNombre() {
        return strSegundoNombre;
    }

    public void setStrSegundoNombre(String strSegundoNombre) {
        this.strSegundoNombre = strSegundoNombre;
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

    public String getStrSexo() {
        return strSexo;
    }

    public void setStrSexo(String strSexo) {
        this.strSexo = strSexo;
    }

    public Date getDtmFechaNacimiento() {
        return dtmFechaNacimiento;
    }

    public void setDtmFechaNacimiento(Date dtmFechaNacimiento) {
        this.dtmFechaNacimiento = dtmFechaNacimiento;
    }

    public String getStrEtnia() {
        return strEtnia;
    }

    public void setStrEtnia(String strEtnia) {
        this.strEtnia = strEtnia;
    }

    public String getStrTipoDiscapacidad() {
        return strTipoDiscapacidad;
    }

    public void setStrTipoDiscapacidad(String strTipoDiscapacidad) {
        switch (strTipoDiscapacidad) {
            case "1":
                strTipoDiscapacidad = "F";
                break;
            case "2":
                strTipoDiscapacidad = "N";
                break;
            case "3":
                strTipoDiscapacidad = "M";
                break;
            default:
                strTipoDiscapacidad = "";
                break;
        }
        this.strTipoDiscapacidad = strTipoDiscapacidad;
    }

    public String getStrCondicionDiscapacidad() {
        return strCondicionDiscapacidad;
    }

    public void setStrCondicionDiscapacidad(String strCondicionDiscapacidad) {
        switch (strCondicionDiscapacidad) {
            case "1":
                strCondicionDiscapacidad = "P";
                break;
            case "2":
                strCondicionDiscapacidad = "T";
                break;
            default:
                strCondicionDiscapacidad = "";
                break;
        }
        this.strCondicionDiscapacidad = strCondicionDiscapacidad;
    }

    public String getStrPuntajeSisben() {
        return strPuntajeSisben;
    }

    public void setStrPuntajeSisben(String strPuntajeSisben) {
        this.strPuntajeSisben = strPuntajeSisben;
    }

    public String getStrGrupoPoblacionEspecial() {
        return strGrupoPoblacionEspecial;
    }

    public void setStrGrupoPoblacionEspecial(String strGrupoPoblacionEspecial) {
        this.strGrupoPoblacionEspecial = strGrupoPoblacionEspecial;
    }

    public String getStrNombreARL() {
        return strNombreARL;
    }

    public void setStrNombreARL(String strNombreARL) {
        this.strNombreARL = strNombreARL;
    }

    public String getStrNombrePensiones() {
        return strNombrePensiones;
    }

    public void setStrNombrePensiones(String strNombrePensiones) {
        this.strNombrePensiones = strNombrePensiones;
    }

    public String getStrDireccionResidencia() {
        return strDireccionResidencia;
    }

    public void setStrDireccionResidencia(String strDireccionResidencia) {
        this.strDireccionResidencia = strDireccionResidencia;
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

    public String getStrCorreoElectronico() {
        return strCorreoElectronico;
    }

    public void setStrCorreoElectronico(String strCorreoElectronico) {
        this.strCorreoElectronico = strCorreoElectronico;
    }

    public String getStrMunicipio() {
        return strMunicipio;
    }

    public void setStrMunicipio(String strMunicipio) {
        this.strMunicipio = strMunicipio;
    }

    public String getStrTipoZona() {
        return strTipoZona;
    }

    public void setStrTipoZona(String strTipoZona) {
        this.strTipoZona = strTipoZona;
    }

    public String getStrDepartamento() {
        return strDepartamento;
    }

    public void setStrDepartamento(String strDepartamento) {
        this.strDepartamento = strDepartamento;
    }

    public String getStrPrimerApellidoCompanero() {
        return strPrimerApellidoCompanero;
    }

    public void setStrPrimerApellidoCompanero(String strPrimerApellidoCompanero) {
        this.strPrimerApellidoCompanero = strPrimerApellidoCompanero;
    }

    public String getStrSegundoApellidoCompanero() {
        return strSegundoApellidoCompanero;
    }

    public void setStrSegundoApellidoCompanero(String strSegundoApellidoCompanero) {
        this.strSegundoApellidoCompanero = strSegundoApellidoCompanero;
    }

    public String getStrPrimerNombreCompanero() {
        return strPrimerNombreCompanero;
    }

    public void setStrPrimerNombreCompanero(String strPrimerNombreCompanero) {
        this.strPrimerNombreCompanero = strPrimerNombreCompanero;
    }

    public String getStrSegundoNombreCompanero() {
        return strSegundoNombreCompanero;
    }

    public void setStrSegundoNombreCompanero(String strSegundoNombreCompanero) {
        this.strSegundoNombreCompanero = strSegundoNombreCompanero;
    }

    public String getStrTipoDocumentoCompanero() {
        return strTipoDocumentoCompanero;
    }

    public void setStrTipoDocumentoCompanero(String strTipoDocumentoCompanero) {
        this.strTipoDocumentoCompanero = strTipoDocumentoCompanero;
    }

    public String getStrNumeroDocumentoCompanero() {
        return strNumeroDocumentoCompanero;
    }

    public void setStrNumeroDocumentoCompanero(String strNumeroDocumentoCompanero) {
        this.strNumeroDocumentoCompanero = strNumeroDocumentoCompanero;
    }

    public String getStrSexoCompanero() {
        return strSexoCompanero;
    }

    public void setStrSexoCompanero(String strSexoCompanero) {
        this.strSexoCompanero = strSexoCompanero;
    }

    public Date getDtmFechaNacimientoCompanero() {
        return dtmFechaNacimientoCompanero;
    }

    public void setDtmFechaNacimientoCompanero(Date dtmFechaNacimientoCompanero) {
        this.dtmFechaNacimientoCompanero = dtmFechaNacimientoCompanero;
    }

    public String getStrPrimerApellidoBeneficiario() {
        return strPrimerApellidoBeneficiario;
    }

    public void setStrPrimerApellidoBeneficiario(String strPrimerApellidoBeneficiario) {
        this.strPrimerApellidoBeneficiario = strPrimerApellidoBeneficiario;
    }

    public String getStrSegundoApellidoBeneficiario() {
        return strSegundoApellidoBeneficiario;
    }

    public void setStrSegundoApellidoBeneficiario(String strSegundoApellidoBeneficiario) {
        this.strSegundoApellidoBeneficiario = strSegundoApellidoBeneficiario;
    }

    public String getStrPrimerNombreBeneficiario() {
        return strPrimerNombreBeneficiario;
    }

    public void setStrPrimerNombreBeneficiario(String strPrimerNombreBeneficiario) {
        this.strPrimerNombreBeneficiario = strPrimerNombreBeneficiario;
    }

    public String getStrSegundoNombreBeneficiario() {
        return strSegundoNombreBeneficiario;
    }

    public void setStrSegundoNombreBeneficiario(String strSegundoNombreBeneficiario) {
        this.strSegundoNombreBeneficiario = strSegundoNombreBeneficiario;
    }

    public String getStrTipoDocumentoBeneficiario() {
        return strTipoDocumentoBeneficiario;
    }

    public void setStrTipoDocumentoBeneficiario(String strTipoDocumentoBeneficiario) {
        this.strTipoDocumentoBeneficiario = strTipoDocumentoBeneficiario;
    }

    public String getStrNumeroDocumentoBeneficiario() {
        return strNumeroDocumentoBeneficiario;
    }

    public void setStrNumeroDocumentoBeneficiario(String strNumeroDocumentoBeneficiario) {
        this.strNumeroDocumentoBeneficiario = strNumeroDocumentoBeneficiario;
    }

    public String getStrSexoBeneficiario() {
        return strSexoBeneficiario;
    }

    public void setStrSexoBeneficiario(String strSexoBeneficiario) {
        this.strSexoBeneficiario = strSexoBeneficiario;
    }

    public Date getDtmFechaNacimientoBeneficiario() {
        return dtmFechaNacimientoBeneficiario;
    }

    public void setDtmFechaNacimientoBeneficiario(Date dtmFechaNacimientoBeneficiario) {
        this.dtmFechaNacimientoBeneficiario = dtmFechaNacimientoBeneficiario;
    }

    public String getStrParentescoBeneficiario() {
        return strParentescoBeneficiario;
    }

    public void setStrParentescoBeneficiario(String strParentescoBeneficiario) {
        this.strParentescoBeneficiario = strParentescoBeneficiario;
    }

    public String getStrEtniaBeneficiario() {
        return strEtniaBeneficiario;
    }

    public void setStrEtniaBeneficiario(String strEtniaBeneficiario) {
        this.strEtniaBeneficiario = strEtniaBeneficiario;
    }

    public String getStrTipoDiscapacidadBeneficiario() {
        return strTipoDiscapacidadBeneficiario;
    }

    public void setStrTipoDiscapacidadBeneficiario(String strTipoDiscapacidadBeneficiario) {
        switch (strTipoDiscapacidadBeneficiario) {
            case "1":
                strTipoDiscapacidadBeneficiario = "F";
                break;
            case "2":
                strTipoDiscapacidadBeneficiario = "N";
                break;
            case "3":
                strTipoDiscapacidadBeneficiario = "M";
                break;
            default:
                strTipoDiscapacidadBeneficiario = "";
                break;
        }
        this.strTipoDiscapacidadBeneficiario = strTipoDiscapacidadBeneficiario;
    }

    public String getStrCondicionDiscapacidadBeneficiario() {
        return strCondicionDiscapacidadBeneficiario;
    }

    public void setStrCondicionDiscapacidadBeneficiario(String strCondicionDiscapacidadBeneficiario) {
        switch (strCondicionDiscapacidadBeneficiario) {
            case "1":
                strCondicionDiscapacidadBeneficiario = "P";
                break;
            case "2":
                strCondicionDiscapacidadBeneficiario = "T";
                break;
            default:
                strCondicionDiscapacidadBeneficiario = "";
                break;
        }
        this.strCondicionDiscapacidadBeneficiario = strCondicionDiscapacidadBeneficiario;
    }

    public String getStrMunicipioBeneficiario() {
        return strMunicipioBeneficiario;
    }

    public void setStrMunicipioBeneficiario(String strMunicipioBeneficiario) {
        this.strMunicipioBeneficiario = strMunicipioBeneficiario;
    }

    public String getStrZonaBeneficiario() {
        return strZonaBeneficiario;
    }

    public void setStrZonaBeneficiario(String strZonaBeneficiario) {
        this.strZonaBeneficiario = strZonaBeneficiario;
    }

    public String getStrDepartamentoBeneficiario() {
        return strDepartamentoBeneficiario;
    }

    public void setStrDepartamentoBeneficiario(String strDepartamentoBeneficiario) {
        this.strDepartamentoBeneficiario = strDepartamentoBeneficiario;
    }

    public String getStrTelefonoBeneficiario() {
        return strTelefonoBeneficiario;
    }

    public void setStrTelefonoBeneficiario(String strTelefonoBeneficiario) {
        this.strTelefonoBeneficiario = strTelefonoBeneficiario;
    }

    public String getStrIpsPrimaria() {
        return strIpsPrimaria;
    }

    public void setStrIpsPrimaria(String strIpsPrimaria) {
        this.strIpsPrimaria = strIpsPrimaria;
    }

    public String getStrCodigoIpsPrimaria() {
        return strCodigoIpsPrimaria;
    }

    public void setStrCodigoIpsPrimaria(String strCodigoIpsPrimaria) {
        this.strCodigoIpsPrimaria = strCodigoIpsPrimaria;
    }

    @Override
    public String toString() {
        return "ReporteAfiliacion{" + "id=" + id + ", strNumeroRadicacion=" + strNumeroRadicacion + ", dtmFechaRadicacion=" + dtmFechaRadicacion + ", strTipoTramite=" + strTipoTramite + ", strTipoAfiliacion=" + strTipoAfiliacion + ", strRegimen=" + strRegimen + ", strTipoAfiliado=" + strTipoAfiliado + ", strCodigo=" + strCodigo + ", strPrimerApellido=" + strPrimerApellido + ", strSegundoApellido=" + strSegundoApellido + ", strPrimerNombre=" + strPrimerNombre + ", strSegundoNombre=" + strSegundoNombre + ", strTipoDocumento=" + strTipoDocumento + ", strNumeroDocumento=" + strNumeroDocumento + ", strSexo=" + strSexo + ", dtmFechaNacimiento=" + dtmFechaNacimiento + ", strEtnia=" + strEtnia + ", strTipoDiscapacidad=" + strTipoDiscapacidad + ", strCondicionDiscapacidad=" + strCondicionDiscapacidad + ", strPuntajeSisben=" + strPuntajeSisben + ", strGrupoPoblacionEspecial=" + strGrupoPoblacionEspecial + ", strNombreARL=" + strNombreARL + ", strNombrePensiones=" + strNombrePensiones + ", strDireccionResidencia=" + strDireccionResidencia + ", strTelefono=" + strTelefono + ", strCelular=" + strCelular + ", strCorreoElectronico=" + strCorreoElectronico + ", strMunicipio=" + strMunicipio + ", strTipoZona=" + strTipoZona + ", strDepartamento=" + strDepartamento + ", strPrimerApellidoCompanero=" + strPrimerApellidoCompanero + ", strSegundoApellidoCompanero=" + strSegundoApellidoCompanero + ", strPrimerNombreCompanero=" + strPrimerNombreCompanero + ", strSegundoNombreCompanero=" + strSegundoNombreCompanero + ", strTipoDocumentoCompanero=" + strTipoDocumentoCompanero + ", strNumeroDocumentoCompanero=" + strNumeroDocumentoCompanero + ", strSexoCompanero=" + strSexoCompanero + ", dtmFechaNacimientoCompanero=" + dtmFechaNacimientoCompanero + ", strPrimerApellidoBeneficiario=" + strPrimerApellidoBeneficiario + ", strSegundoApellidoBeneficiario=" + strSegundoApellidoBeneficiario + ", strPrimerNombreBeneficiario=" + strPrimerNombreBeneficiario + ", strSegundoNombreBeneficiario=" + strSegundoNombreBeneficiario + ", strTipoDocumentoBeneficiario=" + strTipoDocumentoBeneficiario + ", strNumeroDocumentoBeneficiario=" + strNumeroDocumentoBeneficiario + ", strSexoBeneficiario=" + strSexoBeneficiario + ", dtmFechaNacimientoBeneficiario=" + dtmFechaNacimientoBeneficiario + ", strParentescoBeneficiario=" + strParentescoBeneficiario + ", strEtniaBeneficiario=" + strEtniaBeneficiario + ", strTipoDiscapacidadBeneficiario=" + strTipoDiscapacidadBeneficiario + ", strCondicionDiscapacidadBeneficiario=" + strCondicionDiscapacidadBeneficiario + ", strMunicipioBeneficiario=" + strMunicipioBeneficiario + ", strZonaBeneficiario=" + strZonaBeneficiario + ", strDepartamentoBeneficiario=" + strDepartamentoBeneficiario + ", strTelefonoBeneficiario=" + strTelefonoBeneficiario + ", strIpsPrimaria=" + strIpsPrimaria + ", strCodigoIpsPrimaria=" + strCodigoIpsPrimaria + '}';
    }

}
