/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Entrega;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4EntregaRemoto {
    
    /**
     * Consulta la cantidad de datos de parametros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta la lista dado los parametros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Entrega> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Entrega consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuAnexo4Entrega obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo4Entrega obj) throws Exception;
    
    /**
     * Elimina el objeto
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Entrega eliminar(int id) throws Exception;
    
    /**
     * Consulta la lista segun el id del item
     * @param id
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Entrega> consultarPorIdItemAnexo4(int id) throws Exception;
    
    /**
     * Consulta la lista segun el id del item
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Entrega consultarPorIdItemAnexo4NoAnulado(int id) throws Exception;
    
    /**
     * Consulta la lista segun el id del anexo4
     * @param id
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Entrega> consultarListaPorIdAnexo4(int id) throws Exception;
    
    /**
     * Realiza las entregas del anexo 4
     * @param listaAnexos
     * @throws Exception 
     */
    void entregarTodo(List<AuAnexo4> listaAnexos) throws Exception;
    
    /**
     * 
     * @param idAnexoItem
     * @return
     * @throws Exception 
     */
    int consultarCantidadEstadoAnuladaloporAnexo4Item(int idAnexoItem) throws Exception;
    
}
