/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoEstado;
import java.util.List;

/**
 *
 * @author jorgePerez
 */
public interface TuGrupoEstadoRemoto {
    
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
    List<TuGrupoEstado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuGrupo) cargado
     * @throws java.lang.Exception
     */
    TuGrupoEstado consultar(int id) throws Exception;
    
      /**
     * Método para crear una nuevo Juzgado
     *
     * @param per (TuGrupo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuGrupoEstado per) throws Exception;

    
     /**
     * Permite actualizar la información de un registro
     * @param obj (TuGrupo)
     * @throws java.lang.Exception
     */
      void actualizar(TuGrupoEstado obj) throws Exception;
      
    /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuGrupoEstado eliminar(int id) throws Exception;

    /**
     * Permite consultar por id grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<TuGrupoEstado> consultarListaPorIdGrupo(int idGrupo) throws Exception;


}
