package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CmFeRipsCargaServiciosDTO {
   
    @SerializedName("consultas")
    public List<CmFeRipsCargaConsultaDTO> consultas;
    @SerializedName("procedimientos")
    public List<CmFeRipsCargaProcedimientoDTO> procedimientos;
    @SerializedName("urgencias")
    public List<CmFeRipsCargaUrgenciaDTO> urgencias;
    @SerializedName("hospitalizacion")
    public List<CmFeRipsCargaHospitalizacionDTO> hospitalizacion;
    @SerializedName("recienNacidos")
    public List<CmFeRipsCargaRecienNacidoDTO> recienNacidos;
    @SerializedName("medicamentos")
    public List<CmFeRipsCargaMedicamentoDTO> medicamentos;
    @SerializedName("otrosServicios")
    public List<CmFeRipsCargaOtroServicioDTO> otrosServicios;
}
