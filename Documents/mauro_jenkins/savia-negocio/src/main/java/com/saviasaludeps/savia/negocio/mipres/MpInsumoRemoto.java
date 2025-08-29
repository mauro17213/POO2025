/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MpInsumoRemoto {

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpPrescripcionInsumo consultar(int id) throws Exception;

    /**
     * Consultar por MpPrescripcionId
     *
     * @param mpPrescripcionId
     * @return
     * @throws Exception
     */
    List<MpPrescripcionInsumo> consultarPorMpPrescripcion(int mpPrescripcionId) throws Exception;

    void actualizarDireccionamiento(MpPrescripcionInsumo insumo) throws Exception;

    void actualizarEstadoCantidad(MpPrescripcionInsumo insumo) throws Exception;

    void actualizarEstado(MpPrescripcionInsumo insumo) throws Exception;
}
