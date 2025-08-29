package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import java.util.List;

public interface MpTecnologiaRemoto {

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpPrescripcionTecnologia consultar(int id) throws Exception;

    /**
     * Consultar por id
     *
     * @param mpPrescripcionId
     * @return
     * @throws Exception
     */
    List<MpPrescripcionTecnologia> consultarPorMpPrescripcion(int mpPrescripcionId) throws Exception;

    void actualizarDireccionamiento(MpPrescripcionTecnologia tecnologia) throws Exception;

    void actualizarEstadoCantidad(MpPrescripcionTecnologia tecnologia) throws Exception;

    void actualizarEstado(MpPrescripcionTecnologia tecnologia) throws Exception;
}
