/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Estado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4EstadoRemoto {
    
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
    List<AuAnexo4Estado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Estado consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuAnexo4Estado obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo4Estado obj) throws Exception;
    
    /**
     * Elimina el objeto
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Estado eliminar(int id) throws Exception;
    
}
