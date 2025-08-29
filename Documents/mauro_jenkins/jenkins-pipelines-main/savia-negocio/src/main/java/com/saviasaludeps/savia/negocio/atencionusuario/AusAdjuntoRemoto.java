/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AusAdjuntoRemoto {
    
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un Adjunto por ID
     * @param id
     * @return (Adjunto) objeto consultado
     * @throws java.lang.Exception 
     */
    AusAdjunto consultar(int id)throws Exception;
    
    /**
     * Método para crear un Adjunto
     * @param obj (Adjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusAdjunto obj)throws Exception;
    
    /**
     * Método para actualizar la información de  Adjunto
     * @param obj (Adjunto) Objeto actualizado
     * @throws java.lang.Exception
     */
    void actualizar(AusAdjunto obj)throws Exception;
    
    /**
     * Método para eliminar un Adjunto
     * @param id
     * @return (Adjunto) objeto eliminado
     * @throws java.lang.Exception
     */
    AusAdjunto eliminar(int id)throws Exception;
    
}
