/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuGrupoUsuarioRemoto {
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuGrupoUsuario consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuGrupoUsuario obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuGrupoUsuario obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param id
     * @return 
     * @throws Exception 
     */
    AuGrupoUsuario eliminar(int id) throws Exception;
    
    /**
     * Listar por Id grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<AuGrupoUsuario> consultarListaPorIdGrupo(int idGrupo) throws Exception;
    
    /**
     * Consulta la cantidad dado unos paramtros
     * @param paramConsulta
     * @return 
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta la lista dado unos parametros
     * @param paramConsulta
     * @return 
     * @throws Exception 
     */
    List<AuGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar la existencia de un usuario
     * @param idUsuario
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    boolean validarUsuario(int idUsuario, int idGrupo) throws Exception;
    
    /**
     * Consulta existencia usuario por grupo y tipo
     * @param idUsuario
     * @param idGrupo
     * @param maeTipoAuditorId
     * @return
     * @throws Exception 
     */
    boolean validarUsuarioTipo(int idUsuario, int idGrupo, int maeTipoAuditorId) throws Exception;
    
    /**
     * Consulta al lista de usuarios de un grupo activos
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<AuGrupoUsuario> consultarListaPorIdGrupoActivos(int idGrupo) throws Exception;
    
    /**
     * Consultar los grupos del usuario
     * @param idUsuario
     * @return
     * @throws Exception 
     */
    List<Integer> consultarGruposUsuario(Integer idUsuario) throws Exception;
}
