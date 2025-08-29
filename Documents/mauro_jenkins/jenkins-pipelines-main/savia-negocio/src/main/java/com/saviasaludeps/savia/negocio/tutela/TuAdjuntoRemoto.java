/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface TuAdjuntoRemoto {
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuAdjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuAdjunto obj) throws Exception;
    
    /**
     * Método para eliminar un TuAdjunto
     *
     * @param id
     * @return (TuAdjunto) objeto eliminado
     * @throws java.lang.Exception
     */
    TuAdjunto eliminar(int id) throws Exception;
    
    /**
     * 
     * @param idTutela
     * @return
     * @throws Exception 
     */
    List<TuAdjunto> consultarPorTutela(int idTutela) throws Exception; 
}
