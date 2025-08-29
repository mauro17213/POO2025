package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;

public class CmFeRipsCargaOtroServicioDTO {

  @SerializedName("codPrestador")
  public String codPrestador;
  @SerializedName("numAutorizacion")
  public String numAutorizacion;
  @SerializedName("idMIPRES")
  public String idMIPRES;
  @SerializedName("fechaSuministroTecnologia")
  public String fechaSuministroTecnologia;
  @SerializedName("tipoOS")
  public String tipoOS;
  @SerializedName("codTecnologiaSalud")
  public String codTecnologiaSalud;
  @SerializedName("nomTecnologiaSalud")
  public String nomTecnologiaSalud;
  @SerializedName("cantidadOS")
  public double cantidadOS;
  @SerializedName("tipoDocumentoldentificacion")
  public String tipoDocumentoldentificacion;
  @SerializedName("numDocumentoldentificacion")
  public String numDocumentoldentificacion;
  @SerializedName("vrUnitOS")
  public double vrUnitOS;
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
