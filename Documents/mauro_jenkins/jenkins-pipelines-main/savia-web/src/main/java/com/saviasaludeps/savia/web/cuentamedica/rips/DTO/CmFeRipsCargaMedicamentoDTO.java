package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;


public class CmFeRipsCargaMedicamentoDTO{

  @SerializedName("codPrestador")
  public String codPrestador;
  @SerializedName("numAutorizacion")
  public String numAutorizacion;
  @SerializedName("idMIPRES")
  public String idMIPRES;
  @SerializedName("fechaDispensAdmon")
  public String fechaDispensAdmon;
  @SerializedName("codDiagnosticoPrincipal")
  public String codDiagnosticoPrincipal;
  @SerializedName("codDiagnosticoRelacionado")
  public String codDiagnosticoRelacionado;
  @SerializedName("tipoMedicamento")
  public String tipoMedicamento;
  @SerializedName("codTecnologiaSalud")
  public String codTecnologiaSalud;
  @SerializedName("nomTecnologiaSalud")
  public String nomTecnologiaSalud;
  @SerializedName("concentracionMedicamento")
  public double concentracionMedicamento;
  @SerializedName("unidadMedida")
  public double unidadMedida;
  @SerializedName("formaFarmaceutica")
  public String formaFarmaceutica;
  @SerializedName("unidadMinDispensa")
  public double unidadMinDispensa;
  @SerializedName("cantidadMedicamento")
  public double cantidadMedicamento;
  @SerializedName("diasTratamiento")
  public double diasTratamiento;
  @SerializedName("tipoDocumentoldentificacion")
  public String tipoDocumentoldentificacion;
  @SerializedName("numDocumentoldentificacion")
  public String numDocumentoldentificacion;
  @SerializedName("vrUnitMedicamento")
  public double vrUnitMedicamento;
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
