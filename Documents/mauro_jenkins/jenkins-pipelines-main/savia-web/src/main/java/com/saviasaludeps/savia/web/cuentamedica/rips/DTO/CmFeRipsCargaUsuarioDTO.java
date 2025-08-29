package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;

public class CmFeRipsCargaUsuarioDTO  {

    @SerializedName("tipoDocumentoIdentificacion")
    public String tipoDocumentoIdentificacion;
    @SerializedName("numDocumentoIdentificacion")
    public String numDocumentoIdentificacion;
    @SerializedName("tipoUsuario")
    public String tipoUsuario;
    @SerializedName("fechaNacimiento")
    public String fechaNacimiento;
    @SerializedName("codSexo")
    public String codSexo;
    @SerializedName("codPaisResidencia")
    public String codPaisResidencia;
    @SerializedName("codMunicipioResidencia")
    public String codMunicipioResidencia;
    @SerializedName("codZonaTerritorialResidencia")
    public String codZonaTerritorialResidencia;
    @SerializedName("nombreCompleto")
    public String nombreCompleto;
    @SerializedName("incapacidad")
    public String incapacidad;
    @SerializedName("consecutivo")
    public double consecutivo;
    @SerializedName("codPaisOrigen")
    public double codPaisOrigen;
    @SerializedName("asegAfiliadoId")
    public int asegAfiliadoId;
    @SerializedName("servicios")
    public CmFeRipsCargaServiciosDTO servicios;
}
