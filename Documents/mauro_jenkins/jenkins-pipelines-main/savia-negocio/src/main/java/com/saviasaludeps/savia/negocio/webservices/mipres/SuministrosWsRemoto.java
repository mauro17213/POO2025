/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.suministro.Suministro;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yjimeneh
 */
public interface SuministrosWsRemoto {

    /**
     * actualiza todas las prescripciones que han sido suministros
     *
     * @param suministro
     * @param idConsumo
     * @param listaMedicamentos
     * @param listaTecnologias
     * @param listaInsumos
     * @param listaEntregaMedicamentos
     * @param listaEntregaTecnologias
     * @param listaEntregaInsumos
     * @return 
     * @throws Exception
     */
    MpConsumoFallo actualizarPrescripcionesSuministrosWs(
            Suministro suministro,
            Map<String,MpPrescripcionMedicamento> listaMedicamentos,
            Map<String,MpPrescripcionTecnologia> listaTecnologias,
            Map<String,MpPrescripcionInsumo> listaInsumos,
            Map<String, MpProgramadaEntrega> listaEntregaMedicamentos,
            Map<String, MpProgramadaEntrega> listaEntregaTecnologias,
            Map<String, MpProgramadaEntrega> listaEntregaInsumos
    ) throws Exception;

}
