/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstadoRepresentante;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface TuTutelaEstadoRepresentanteRemoto {
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuTutelaEstadoRepresentante) cargado
     * @throws java.lang.Exception
     */
    TuTutelaEstadoRepresentante consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuTutelaEstadoRepresentante)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuTutelaEstadoRepresentante obj) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuTutelaEstadoRepresentante)
     * @throws java.lang.Exception
     */
    void actualizar(TuTutelaEstadoRepresentante obj) throws Exception;
    
    /**
     * Método para eliminar un maestro
     *
     * @param id
     * @return (TuTutelaEstadoRepresentante) objeto eliminado
     * @throws java.lang.Exception
     */
    TuTutelaEstadoRepresentante eliminar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param idTutelaEstado
     * @throws java.lang.Exception
     */
    void eliminarResprentantes(int idTutelaEstado) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param idTutelaEstado
     * @return (TuTutelaEstadoRepresentante) cargado
     * @throws java.lang.Exception
     */
    List<TuTutelaEstadoRepresentante> consultarRespresentantesPorEstadoTutela(int idTutelaEstado) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param idRepresentante
     * @return (TuTutelaEstadoRepresentante) cargado
     * @throws java.lang.Exception
     */
    List<TuTutelaEstadoRepresentante> consultarRespresentantesPorIdRepresentante(int idRepresentante) throws Exception;
    
    /**
     * Método para consultar un registro por idRepresentante, idTutelaEstado
     * @param idRepresentante
     * @param idTutelaEstado
     * @return (TuTutelaEstadoRepresentante) cargado
     * @throws java.lang.Exception
     */
    TuTutelaEstadoRepresentante consultarRepresentantePorEstadoyYIdRepresentante(int idRepresentante, int idTutelaEstado) throws Exception;
}
