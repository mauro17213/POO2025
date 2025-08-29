package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaDetalle;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import java.util.List;

public interface ValidadorAuNoAutorizacionesFnRemoto {

    List<ValidaRespuestaDTO> validarCarga(List<AuNoSolicitudCargaDetalle> listaFilasValida);

}
