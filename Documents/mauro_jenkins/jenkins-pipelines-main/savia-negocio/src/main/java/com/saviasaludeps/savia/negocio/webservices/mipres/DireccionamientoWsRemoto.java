/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento.Direccionamiento;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yjimeneh
 */
public interface DireccionamientoWsRemoto {

    /**
     * inserta un direccionamiento y actualiza la tecnologia
     *
     * @param direccionamiento
     * @param listaMedicamentos
     * @param listaTecnologias
     * @param listaInsumos
     * @param listaSedes
     * @param hashMaestroTipoDocumentoEmpresas
     * @return 
     * @throws Exception
     */
    MpConsumoFallo procesarDireccionadas(
            Direccionamiento direccionamiento, 
            Map<String,MpPrescripcionMedicamento> listaMedicamentos, 
            Map<String,MpPrescripcionTecnologia> listaTecnologias, 
            Map<String,MpPrescripcionInsumo> listaInsumos,
            Map<String, CntPrestador> listaSedes,
            HashMap<String, Maestro> hashMaestroTipoDocumentoEmpresas) throws Exception;

}
