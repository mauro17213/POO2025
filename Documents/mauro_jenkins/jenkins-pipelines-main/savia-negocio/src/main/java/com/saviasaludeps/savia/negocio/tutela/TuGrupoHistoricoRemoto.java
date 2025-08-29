/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoHistorico;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface TuGrupoHistoricoRemoto {
    
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
    List<TuGrupoHistorico> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuGrupoHistorico) cargado
     * @throws java.lang.Exception
     */
    TuGrupoHistorico consultar(int id) throws Exception;
    
      /**
     * Método para crear una nueva Empresa
     *
     * @param per (TuGrupoHistorico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuGrupoHistorico per) throws Exception;

    
     /**
     * Método para actualizar la información de un registro
     * @param obj (TuGrupoHistorico)
     * @throws java.lang.Exception
     */
      void actualizar(TuGrupoHistorico obj) throws Exception;
      

     /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuGrupoHistorico eliminar(int id) throws Exception;

    /**
     * Permite consultar por id grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<TuGrupoHistorico> consultarListaPorIdGrupo(int idGrupo) throws Exception;
}
