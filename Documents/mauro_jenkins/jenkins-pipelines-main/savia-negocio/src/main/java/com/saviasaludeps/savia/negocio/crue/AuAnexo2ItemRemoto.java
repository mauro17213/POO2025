/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface AuAnexo2ItemRemoto {
    

    
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
    List<AuAnexo2Item> consultarLista(ParamConsulta paramConsulta) throws Exception;
    List<AuAnexo2Item> consultarListaByIdAnexo2(int idAnexo2) throws Exception;
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo2Item consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuAnexo2Item obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo2Item obj) throws Exception;
    
    /**
     * Elimina el objeto
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo2Item eliminar(int id) throws Exception;
}
