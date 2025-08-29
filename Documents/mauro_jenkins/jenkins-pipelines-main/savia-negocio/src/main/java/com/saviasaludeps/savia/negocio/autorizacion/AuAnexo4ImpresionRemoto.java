/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Impresion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface AuAnexo4ImpresionRemoto {
    
    
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
    List<AuAnexo4Impresion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Impresion consultar(int id) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo4Impresion obj) throws Exception;
    
    /**
     * Trae la lista dado el id del Anexo 4
     * @param idAnexo4
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Impresion> consultarPorIdAnexo4(int idAnexo4) throws Exception;
    
    /**
     * Inserta
     * @param anexo4Impresion
     * @return
     * @throws Exception 
     */
    int insertar(AuAnexo4Impresion anexo4Impresion) throws Exception;
    
    
    
    
    
}
