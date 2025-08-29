package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmFeRipsCargaHospitalizacionDTO extends Auditoria {

  @SerializedName("codPrestador")
  public String codPrestador;
  @SerializedName("viaIngresoServicioSalud")
  public String viaIngresoServicioSalud;
  @SerializedName("fechaInicioAtencion")
  public String fechaInicioAtencion;
  @SerializedName("numAutorizacion")
  public String numAutorizacion;
  @SerializedName("causaMotivoAtencion")
  public String causaMotivoAtencion;
  @SerializedName("codDiagnosticoPrincipal")
  public String codDiagnosticoPrincipal;
  @SerializedName("codDiagnosticoPrincipalE")
  public String codDiagnosticoPrincipalE;
  @SerializedName("codDiagnosticoRelacionadoE1")
  public String codDiagnosticoRelacionadoE1;
  @SerializedName("codDiagnosticoRelacionadoE2")
  public String codDiagnosticoRelacionadoE2;
  @SerializedName("codDiagnosticoRelacionadoE3")
  public String codDiagnosticoRelacionadoE3;
  @SerializedName("codComplicacion")
  public String codComplicacion;
  @SerializedName("condicionDestinoUsuarioEgreso")
  public String condicionDestinoUsuarioEgreso;
  @SerializedName("codDiagnosticoMuerte")
  public String codDiagnosticoMuerte;
  @SerializedName("fechaEgreso")
  public String fechaEgreso;
  @SerializedName("consecutivo")
  public double consecutivo;
  @SerializedName("viaingresoServicioSalud")
  public String viaingresoServicioSalud;
  @SerializedName("codDiagnosticoCausaMuerte")
  public String codDiagnosticoCausaMuerte;
}
