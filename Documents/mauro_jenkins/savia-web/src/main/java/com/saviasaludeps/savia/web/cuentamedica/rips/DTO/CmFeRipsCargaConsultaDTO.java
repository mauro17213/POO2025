package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;


public class CmFeRipsCargaConsultaDTO  {

    @SerializedName("codPrestador")
    public String codPrestador;
    @SerializedName("numAutorizacion")
    public String numAutorizacion;
    @SerializedName("fechaInicioAtencion")
    public String fechaInicioAtencion;
    @SerializedName("codConsulta")
    public String codConsulta;
    @SerializedName("modalidadGrupoServicioTecSal")
    public String modalidadGrupoServicioTecSal;
    @SerializedName("grupoServicios")
    public String grupoServicios;
    @SerializedName("codServicio")
    public double codServicio;
    @SerializedName("finalidadTecnologiaSalud")
    public String finalidadTecnologiaSalud;
    @SerializedName("causaMotivoAtencion")
    public String causaMotivoAtencion;
    @SerializedName("codDiagnosticoPrincipal")
    public String codDiagnosticoPrincipal;
    @SerializedName("codDiagnosticoRelacionado1")
    public String codDiagnosticoRelacionado1;
    @SerializedName("codDiagnosticoRelacionado2")
    public String codDiagnosticoRelacionado2;
    @SerializedName("codDiagnosticoRelacionado3")
    public String codDiagnosticoRelacionado3;
    @SerializedName("tipoDiagnosticoPrincipal")
    public String tipoDiagnosticoPrincipal;
    @SerializedName("tipoDocumentoldentificacion")
    public String tipoDocumentoldentificacion;
    @SerializedName("numDocumentoldentificacion")
    public String numDocumentoldentificacion;
    @SerializedName("vrServicio")
    public double vrServicio;
    @SerializedName("tipoPagoModerador")
    public String tipoPagoModerador;
    @SerializedName("valorPagoModerador")
    public double valorPagoModerador;
    @SerializedName("numFEVPagoModerador")
    public String numFEVPagoModerador;
    @SerializedName("consecutivo")
    public double consecutivo;
    @SerializedName("vrConsulta")
    public double vrConsulta;
}
