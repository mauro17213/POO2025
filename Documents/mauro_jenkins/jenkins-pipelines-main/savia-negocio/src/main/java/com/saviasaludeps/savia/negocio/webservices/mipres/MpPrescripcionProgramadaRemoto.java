/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento.Direccionamiento;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jramirez
 */
public interface MpPrescripcionProgramadaRemoto {
    
    /**
     * Función para insertar una prescripción programada
     * @param mpPrescripcionProgramada
     * @return
     */
    public int insertar(MpPrescripcionProgramada mpPrescripcionProgramada);
    
    /**
     * Función para buscaruna prescripción programada
     * @param tipoTecnologia
     * @param prescripcion
     * @param codTecnologia
     * @param numeroEntrega
     * @return
     */
    public int consultarPorPrescripcionTecnologiaEntrega( String tipoTecnologia, int prescripcion, int codTecnologia, int numeroEntrega);
    
    /**
     *  Consulta Consulta una lista de Direccionamiento por numeros de direccionamiento
     * 
     * @param listaDireccionamientos
     * @return
     * @throws Exception
     */
     Map<String, MpPrescripcionProgramada> consultarListaDireccionamiento(List<Direccionamiento> listaDireccionamientos) throws Exception;
}
