package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;


public class CmFeRipsCargaProcedimientoDTO  {

  @SerializedName("codPrestador")
  public String codPrestador;
  @SerializedName("fechaInicioAtencion")
  public String fechaInicioAtencion;
  @SerializedName("numAutorizacion")
  public String numAutorizacion;
  @SerializedName("codProcedimiento")
  public String codProcedimiento;
  @SerializedName("viaIngresoServicioSalud")
  public String viaIngresoServicioSalud;
  @SerializedName("modalidadGrupoServicioTecSal")
  public String modalidadGrupoServicioTecSal;
  @SerializedName("grupoServicios")
  public String grupoServicios;
  @SerializedName("codServicio")
  public double codServicio;
  @SerializedName("finalidadTecnologiaSalud")
  public String finalidadTecnologiaSalud;
  @SerializedName("tipoDocumentoldentificacion")
  public String tipoDocumentoldentificacion;
  @SerializedName("numDocumentoldentificacion")
  public String numDocumentoldentificacion;
  @SerializedName("codDiagnosticoPrincipal")
  public String codDiagnosticoPrincipal;
  @SerializedName("codDiagnosticoRelacionado")
  public String codDiagnosticoRelacionado;
  @SerializedName("codComplicacion")
  public String codComplicacion;
  @SerializedName("vrProcedimiento")
  public double vrProcedimiento;
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
}
