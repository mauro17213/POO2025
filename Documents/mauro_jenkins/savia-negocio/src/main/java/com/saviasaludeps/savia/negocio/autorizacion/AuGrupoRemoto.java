/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuGrupoRemoto {
    
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
    List<AuGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar todos los registros activos
     * @return
     * @throws Exception 
     */
    List<AuGrupo> consultarActivos() throws Exception;
    
    /**
     * Consulta el objeto dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuGrupo consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuGrupo obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuGrupo obj) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param id
     * @return 
     * @throws Exception 
     */
    AuGrupo eliminar(int id) throws Exception;
    
    /**
     * Actualiza el objeto
     * @param obj
     * @throws Exception 
     */
    void actualizarUltimoUsuario(AuGrupo obj) throws Exception;
    
    /**
     * Consulta los otros grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<AuGrupo> consultarOtrosActivos(int idGrupo) throws Exception;
    
    /**
     * Valida si exite un grupo generico
     * @return
     * @throws Exception 
     */
    boolean validarGrupoGenerico() throws Exception;
    
    /**
     * Valida si exite un grupo tutela
     * @return
     * @throws Exception 
     */
    boolean validarGrupoTutela() throws Exception;
    
    /**
     * Valida si existe un grupo Pbs
     * @return
     * @throws Exception 
     */
    boolean validarGrupoPbs() throws Exception;
    
    /**
     * Consulta por orden
     * @param orden
     * @return
     * @throws Exception 
     */
    List<AuGrupo> consultarPorOrden(int orden) throws Exception;
    
    /**
     * Consulta el grupo asociado a una tecnologia
     * @param tecnologia_id
     * @return AuGrupo
     * @throws Exception 
     */
    public List<AuGrupo> consultarPorTecnologia(int tecnologia_id) throws Exception;
    
}
