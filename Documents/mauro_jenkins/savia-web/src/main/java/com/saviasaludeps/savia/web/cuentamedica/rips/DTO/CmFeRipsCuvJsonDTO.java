package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CmFeRipsCuvJsonDTO {

    @SerializedName(value = "resultState", alternate = {"RESULTSTATE", "ResultState"})
    public boolean resultState;
    @SerializedName(value = "procesoId", alternate = {"PROCESOID", "ProcesoId"})
    public int procesoId;
    @SerializedName(value = "numFactura", alternate = {"NUMFACTURA", "NumFactura"})
    public String numFactura;
    @SerializedName(value = "codigoUnicoValidacion", alternate = {"CODIGOUNICOVALIDACION", "CodigoUnicoValidacion"})
    public String codigoUnicoValidacion;
    @SerializedName(value = "fechaRadicacion", alternate = {"FECHARADICACION", "FechaRadicacion"})
    public String fechaRadicacion;
    @SerializedName(value = "rutaArchivos", alternate = {"RUTAARCHIVOS", "RutaArchivos"})
    public String rutaArchivos = null;
    @SerializedName(value = "resultadosValidacion", alternate = {"RESULTADOSVALIDACION", "ResultadosValidacion"})
    ArrayList< Object> resultadosValidacion = new ArrayList< Object>();
    
}
