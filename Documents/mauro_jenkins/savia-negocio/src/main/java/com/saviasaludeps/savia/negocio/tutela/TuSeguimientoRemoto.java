/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuSeguimiento;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface TuSeguimientoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<TuSeguimiento> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuSeguimiento) cargado
     * @throws java.lang.Exception
     */
    TuSeguimiento consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuSeguimiento)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuSeguimiento obj) throws Exception;
    
    /**
     * Método para elminar un registro por ID
     * @param id
     * @return (TuSeguimiento) cargado
     * @throws java.lang.Exception
     */
    TuSeguimiento eliminar(int id) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuSeguimiento)
     * @throws java.lang.Exception
     */
    void actualizar(TuSeguimiento obj) throws Exception;
    
    /**
     * Método para obtener la lista de Seguimientos de una tutela.
     * @param idTutela
     * @return
     * @throws Exception 
     */
    List<TuSeguimiento> consultarLista(int idTutela) throws Exception;
}
