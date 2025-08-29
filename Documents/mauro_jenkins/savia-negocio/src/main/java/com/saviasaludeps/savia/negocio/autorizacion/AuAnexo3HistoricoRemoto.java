/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Historico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Historico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo3HistoricoRemoto {
    
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
    List<AuAnexo3Historico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo3Historico consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuAnexo3Historico obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo3Historico obj) throws Exception;
    
    /**
     * Elimina el objeto
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo3Historico eliminar(int id) throws Exception;
    
    
    /**
     * Consultar el listado de historico segun el id de anexo 3 recibido
     * @param idItem
     * @return List<AuAnexo3Historico>
     * @throws Exception 
     */
    List<AuAnexo3Historico> listarPorIdItem(int idItem) throws Exception;
    
}
