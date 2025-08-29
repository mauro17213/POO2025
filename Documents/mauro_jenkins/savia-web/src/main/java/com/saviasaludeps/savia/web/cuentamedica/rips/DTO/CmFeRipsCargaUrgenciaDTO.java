package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;


public class CmFeRipsCargaUrgenciaDTO {

    @SerializedName("codPrestador")
    public String codPrestador;
    @SerializedName("fechaInicioAtencion")
    public String fechaInicioAtencion;
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
    @SerializedName("condicionDestino")
    public String condicionDestino;
    @SerializedName("codDiagnosticoCausaMuerte")
    public String codDiagnosticoCausaMuerte;
    @SerializedName("fechaEgreso")
    public String fechaEgreso;
    @SerializedName("consecutivo")
    public double consecutivo;
}
