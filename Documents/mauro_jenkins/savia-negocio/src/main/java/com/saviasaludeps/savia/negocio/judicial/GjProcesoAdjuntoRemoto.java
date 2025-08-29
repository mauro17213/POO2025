/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface GjProcesoAdjuntoRemoto {
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuAdjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GjProcesoAdjunto obj) throws Exception;
    
    /**
     * Método para eliminar un TuAdjunto
     *
     * @param id
     * @return (TuAdjunto) objeto eliminado
     * @throws java.lang.Exception
     */
    GjProcesoAdjunto eliminar(int id) throws Exception;
    
    /**
     * Consulta de cantidad de adjuntos en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Consultar lista de adjuntos por historico y proceso
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<GjProcesoAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * 
     * @param idGjProceso
     * @return
     * @throws Exception 
     */
    List<GjProcesoAdjunto> consultarPorProceso(int idGjProceso) throws Exception; 
    
    /**
     * 
     * @param idGjProceso
     * @return
     * @throws Exception 
     */
    List<GjProcesoAdjunto> consultarPorHistorico(int idGjProceso) throws Exception;
}
