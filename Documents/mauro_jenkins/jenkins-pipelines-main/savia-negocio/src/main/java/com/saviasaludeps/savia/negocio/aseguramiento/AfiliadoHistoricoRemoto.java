/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoHistorico;
import java.util.List;

public interface AfiliadoHistoricoRemoto {
    
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
    List<AsegAfiliadoHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegAfiliadoHistorico) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegAfiliadoHistorico consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegAfiliadoHistorico) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAfiliadoHistorico obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegAfiliadoHistorico)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAfiliadoHistorico obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegAfiliadoHistorico) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAfiliadoHistorico eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegAfiliadoHistorico
     * @return
     * @throws Exception 
     */
    List<AsegAfiliadoHistorico> consultarTodos() throws Exception;
    /**
     * Metodo que permite consultar historico por fecha y documento afiliado
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaPorDocumentoFecha(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar historico por fecha y documento afiliado
     * @param paramConsulta: getParamConsulta1() : numeroDocumento afiliado, 
     * getParamConsulta2(): fecha creacion para consulta de historial, 
     * getParamConsulta3() : fecha  para ordenamiento de items de manera inicial con esta fecha
     * @return
     * @throws Exception 
     */
    List<AsegAfiliadoHistorico> consultarListaPorDocumentoFecha(ParamConsulta paramConsulta) throws Exception;
        
}
