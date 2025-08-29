/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoHistorico;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuGrupoHistoricoRemoto {
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuGrupoHistorico consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuGrupoHistorico obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuGrupoHistorico obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param id
     * @return 
     * @throws Exception 
     */
    AuGrupoHistorico eliminar(int id) throws Exception;
    
    /**
     * Listar por Id grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<AuGrupoHistorico> consultarListaPorIdGrupo(int idGrupo) throws Exception;
    
}
