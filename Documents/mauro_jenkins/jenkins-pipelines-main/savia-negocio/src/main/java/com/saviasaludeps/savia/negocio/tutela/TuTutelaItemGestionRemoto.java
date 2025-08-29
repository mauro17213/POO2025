/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItemGestion;
import java.util.List;

/**
 *
 * @author Jose Perez Hernandez
 */
public interface TuTutelaItemGestionRemoto {
    
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
    List<TuTutelaItemGestion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuTutelaItemGestion) cargado
     * @throws java.lang.Exception
     */
    TuTutelaItemGestion consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuTutelaItemGestion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuTutelaItemGestion obj) throws Exception;
    
    /**
     * Método para elminar un registro por ID
     * @param id
     * @return (TuTutelaItemGestion) cargado
     * @throws java.lang.Exception
     */
    TuTutelaItemGestion eliminar(int id) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuTutelaItemGestion)
     * @throws java.lang.Exception
     */
    void actualizar(TuTutelaItemGestion obj) throws Exception;
    
    /**
     * Método para obtener la lista de Seguimientos de un item de tutela.
     * @param idTutela
     * @return
     * @throws Exception 
     */
    List<TuTutelaItemGestion> consultarLista(int idTutelaItem) throws Exception;
}
