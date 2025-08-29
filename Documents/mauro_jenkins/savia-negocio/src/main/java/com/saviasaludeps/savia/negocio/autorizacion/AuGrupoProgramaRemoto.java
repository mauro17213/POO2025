/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoPrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author sgiraldo
 */
public interface AuGrupoProgramaRemoto {
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuGrupoPrograma consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuGrupoPrograma obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuGrupoPrograma obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param id
     * @return 
     * @throws Exception 
     */
    AuGrupoPrograma eliminar(int id) throws Exception;
    
    /**
     * Listar por Id grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<AuGrupoPrograma> consultarListaPorIdGrupo(int idGrupo) throws Exception;
    
    /**
     * Cuenta la cantidad de lista dado unos parametros
     * @param paramConsulta
     * @return 
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Lista dados unos parametros
     * @param paramConsulta
     * @return 
     * @throws Exception 
     */
    List<AuGrupoPrograma> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Valida la existencia de un programa
     * @param idPrograma
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    boolean validarPrograma(int idPrograma, int idGrupo) throws Exception;
    
}
