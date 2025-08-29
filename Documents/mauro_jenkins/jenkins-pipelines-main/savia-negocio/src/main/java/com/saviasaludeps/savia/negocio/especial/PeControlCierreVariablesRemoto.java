/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeCierreCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jdlopez
 */
public interface PeControlCierreVariablesRemoto {
    
       /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception 
     */
    List<PeCierreCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar registro de PeCierreCargas
     * @param id Parametro de la consulta de registro
     * @return Cierre consultado
     * @throws Exception 
     */
    PeCierreCarga consultar(int id) throws Exception;
    
    /**
     * Consultar registro de PeCierreCargas
     * @param idPrograma 
     * @param periodo 
     * @return Cierre consultado
     * @throws Exception 
     */
    PeCierreCarga consultarIdProgramaPeriodo(int idPrograma, int periodo) throws Exception;
    
     /**
     * Inserta un registro PeCierreCargas
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(PeCierreCarga obj) throws Exception;
    
     /**
     * Actualiza un registro PeCierreCargas
     * @param obj
     * @throws Exception 
     */
    void actualizar(PeCierreCarga obj) throws Exception;
}
