/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface TuItemRemoto {
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
    List<TuTutelaItem> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuTutelaItem) cargado
     * @throws java.lang.Exception
     */
    TuTutelaItem consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuTutelaItem)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuTutelaItem obj) throws Exception;
    
    /**
     * Método para elminar un registro por ID
     * @param id
     * @return (TuTutelaItem) cargado
     * @throws java.lang.Exception
     */
    TuTutelaItem eliminar(int id) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuTutelaItem)
     * @throws java.lang.Exception
     */
    void actualizar(TuTutelaItem obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuTutelaItem)
     * @throws java.lang.Exception
     */
    void actualizarGestionItem(TuTutelaItem obj) throws Exception;
    
    /**
     * Método para elminar una autorizacion del registro por ID
     * @param obj Objeto item
     * @throws java.lang.Exception
     */
    void eliminarAutorizacion(TuTutelaItem obj) throws Exception;
}
