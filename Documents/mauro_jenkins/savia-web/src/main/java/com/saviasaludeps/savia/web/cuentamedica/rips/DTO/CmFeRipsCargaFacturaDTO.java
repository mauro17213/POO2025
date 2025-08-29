package com.saviasaludeps.savia.web.cuentamedica.rips.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CmFeRipsCargaFacturaDTO  {
  @SerializedName("numDocumentoIdObligado")
  public String numDocumentoIdObligado;
  @SerializedName(value = "numFactura", alternate = {"NUMFACTURA", "NumFactura"})
  public String numFactura;
  @SerializedName("tipoNota")
  public String tipoNota;
  @SerializedName("numNota")
  public String numNota;
  @SerializedName("tipoRegimen")
  public String tipoRegimen;
  @SerializedName("multiusuario")
  public Boolean multiusuario;
  @SerializedName(value = "usuarios", alternate = {"USUARIOS", "Usuarios"})
  public List<CmFeRipsCargaUsuarioDTO> usuarios;
}
