/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.reporteentrega.ReporteEntrega;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yjimeneh
 */
public interface ReporteEntregasWsRemoto {

    /**
     * actualiza todas las prescripciones que han sido reportadas entregadas
     *
     * @param reporteEntrega
     * @param listaMedicamentos
     * @param listaTecnologias
     * @param listaInsumos
     * @return 
     * @throws Exception
     */
    MpConsumoFallo actualizarPrescripcionesReporteEntregasWs(
            ReporteEntrega reporteEntrega, 
            Map<String, MpPrescripcionMedicamento> listaMedicamentos, 
            Map<String, MpPrescripcionTecnologia> listaTecnologias, 
            Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception;
    
    /**
     * Registra los Reporte entregas en tabla MpDireccionamientosEntregados
     *
     * @param reporte
     * @param listaDireccionamiento
     * @return 
     * @throws Exception
     */
    MpConsumoFallo registrarReporteEntregas(
            ReporteEntrega reporte, 
            Map<String, MpDireccionamiento> listaDireccionamiento)
            throws Exception;

}
