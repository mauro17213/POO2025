/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MpEntregadaRemoto {

    /**
     * Consultar lista de entregas id de tecnologia
     *
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    List<MpProgramadaEntrega> consultarListaPorTecnologia(int idTecnologia) throws Exception;

    /**
     * Consultar lista de entregas id de tecnologia
     *
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    List<MpProgramadaEntrega> consultarListaPorMedicamento(int idTecnologia) throws Exception;

    /**
     * Consultar lista de entregas id de tecnologia
     *
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    List<MpProgramadaEntrega> consultarListaPorInsumo(int idTecnologia) throws Exception;
}
