/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres.contingencia;

import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcionAdjunto;
import java.util.List;

/**
 *
 * @author stive
 */
public interface PrescripcionAdjuntoRemoto {
    
    /**
     * Método para consultar lista de adjuntos
     * @param idPrescripcion
     * @return
     * @throws Exception 
     */
    List<MpcPrescripcionAdjunto> consultarLista(int idPrescripcion) throws Exception;
    
    /**
     * Inserta el adjunto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(MpcPrescripcionAdjunto obj) throws Exception;
    
    /**
     * Elimina el adjunto
     * @param id
     * @return
     * @throws Exception 
     */
    MpcPrescripcionAdjunto eliminar(int id) throws Exception;
    
}
