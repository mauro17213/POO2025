/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import java.util.List;

/**
 *
 * @author jorgePerez
 */
public interface TuGrupoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<TuGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuGrupo) cargado
     * @throws java.lang.Exception
     */
    TuGrupo consultar(int id) throws Exception;
    
      /**
     * Método para crear una nuevo Juzgado
     *
     * @param per (TuGrupo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuGrupo per) throws Exception;

    
     /**
     * Permite actualizar la información de un registro
     * @param obj (TuGrupo)
     * @throws java.lang.Exception
     */
      void actualizar(TuGrupo obj) throws Exception;
      
    /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuGrupo eliminar(int id) throws Exception;

    /**
     * Permite modificar el orden de un grupo
     * @param obj
     * @throws Exception 
     */
    void actualizarOrden(TuGrupo obj) throws Exception;

    /**
     * Permite actualizar ultimo usuario relacionado con un grupo.
     * @param obj
     * @throws Exception 
     */
    void actualizarUltimoUsuario(TuGrupo obj) throws Exception;

    /**
     * Funcion que permite consultar los grupos que se relacionan con un estado
     * y que estan activos
     * @param paramConsulta :getParamConsulta()1: idMaeEstado, getParamConsulta()2: reparto (false - manual,true - automatico)
     * @return
     * @throws Exception 
     */
    List<TuGrupo> consultarListaPorEstado(ParamConsulta paramConsulta) throws Exception;

    /***
     * Funcion que permite dejar en blanco el campo ultimo usuario de un grupo dado
     * @param ids: string ej; 1,13
     * @throws Exception 
     */
    void reiniciarUltimosUsuarios(String ids) throws Exception;

  
}
