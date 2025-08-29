/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MpMedicamentoRemoto {

    /**
     * Consultar por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    MpPrescripcionMedicamento consultar(int id) throws Exception;

    /**
     * Consultar por MpPrescripcionId
     *
     * @param mpPrescripcionId
     * @return
     * @throws Exception
     */
    List<MpPrescripcionMedicamento> consultarPorMpPrescripcion(int mpPrescripcionId) throws Exception;

    void actualizar(MpPrescripcionMedicamento medicamento) throws Exception;

    void actualizarDireccionamiento(MpPrescripcionMedicamento medicamento) throws Exception;

    void actualizarEstadoCantidad(MpPrescripcionMedicamento medicamento) throws Exception;

    void actualizarEstado(MpPrescripcionMedicamento medicamento) throws Exception;
}
