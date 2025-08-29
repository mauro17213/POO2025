package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;


public class CmFeRipsCargaRecienNacidoDTO  {

  @SerializedName("codPrestador")
  public String codPrestador;
  @SerializedName("tipoDocumentoldentificacion")
  public String tipoDocumentoldentificacion;
  @SerializedName("numDocumentoldentificacion")
  public String numDocumentoldentificacion;
  @SerializedName("fechaNacimiento")
  public String fechaNacimiento;
  @SerializedName("edadGestacional")
  public double edadGestacional;
  @SerializedName("numConsultasCPrenatal")
  public double numConsultasCPrenatal;
  @SerializedName("codSexoBiologico")
  public String codSexoBiologico;
  @SerializedName("peso")
  public double peso;
  @SerializedName("codDiagnosticoPrincipal")
  public String codDiagnosticoPrincipal;
  @SerializedName("condicionDestino")
  public String condicionDestino;
  @SerializedName("codDiagnosticoCausaMuerte")
  public String codDiagnosticoCausaMuerte;
  @SerializedName("fechaEgreso")
  public String fechaEgreso;
  @SerializedName("consecutivo")
  public double consecutivo;
}
