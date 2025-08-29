/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeVariable;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jdlopez
 */
public interface PeVariableRemoto {

    /**
     * Guarda un registro en PeVariables
     * @param variable
     * @return
     * @throws Exception 
     */
    int insertar(PeVariable variable) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta)throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<PeVariable> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar por id de variable
     * @param id
     * @return
     * @throws Exception 
     */
    PeVariable consultarPorId(int id) throws Exception;
    
    /**
     * Consultar por id de programa relacionado
     * @param idPrograma
     * @return
     * @throws Exception 
     */
    List<PeVariable> consultarPorIdPrograma(String idPrograma) throws Exception;
    /**
     * Consultar el id de la variable por programa relacionado y su respectivo nombre
     * @param idPrograma
     * @param nombre
     * @return
     * @throws Exception 
     */
    Integer consultarIdPorIdProgramaNombre(Integer idPrograma, String nombre) throws Exception;
    /**
     * Consultar por id de programa relacionado y su respectivo nombre
     * @param idPrograma
     * @param nombre
     * @return
     * @throws Exception 
     */
    PeVariable consultarPorIdProgramaNombre(Integer idPrograma, String nombre) throws Exception;
    /**
     * Obtiene un listado de variables de acuerdo al idPrograma y nombres contenido (ordenada por la posicion del nombre en la lista nombres)
     * @param idPrograma
     * @param nombres
     * @return
     * @throws Exception 
     */
    List<PeVariable> consultarPorIdProgramaYNombres(Integer idPrograma, List<String> nombres) throws Exception;
     /**
     * Consultar por id de programa relacionado y tipo de variable (solo variables activas)
     * @param idPrograma
     * @param tipo
     * @return
     * @throws Exception 
     */
    List<PeVariable> consultarPorIdProgramaTipo(int idPrograma, short tipo) throws Exception;
    
    /**
     * Consulta listado de nombres de variables obligatorias por programa
     * excluye variables calculadas en caso que esten obligatorias
     * @param idPrograma
     * @return
     * @throws Exception 
     */
    List<String> consultarListadoVariablesObligatoriasPorIdPrograma(int idPrograma) throws Exception;
    /**
     * Actualiza un registro de PeVariables
     * @param variable
     * @throws Exception 
     */
    void actualizar(PeVariable variable) throws Exception;
    
    /**
     * Elimina un registro de PeVariables
     * @param id
     * @throws Exception 
     */
    void eliminar(int id) throws Exception;
   
}
