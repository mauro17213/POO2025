package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaDetalle;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import java.util.List;

public interface ValidadorAuAnexo3FnRemoto {

    List<ValidaRespuestaDTO> validarCarga(List<AuAnexo3CargaDetalle> listaFilasValida);

}
