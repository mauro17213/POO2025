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
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.nodireccionamiento.NoDireccionamiento;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yjimeneh
 */
public interface NoDireccionamientoWsRemoto {

    /**
     * inserta un direccionamiento y actualiza la tecnologia
     *
     * @param direccionamiento
     * @param idConsumo
     * @param listaMedicamentos
     * @param listaTecnologias
     * @param listaInsumos
     * @return 
     * @throws Exception
     */
    MpConsumoFallo procesarNoDireccionadas(NoDireccionamiento direccionamiento, Map<String, MpPrescripcionMedicamento> listaMedicamentos, Map<String, MpPrescripcionTecnologia> listaTecnologias, Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception;

}
