/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import java.util.List;




/**
 *
 * @author bsteven_gomez
 */
public interface GjAbogadoRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GjAbogado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (GjAbogados) cargado
     * @throws java.lang.Exception
     */
    GjAbogado consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (GjAbogados)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    Integer insertar(GjAbogado per) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (GjAbogados)
     * @throws java.lang.Exception
     */
    void actualizar(GjAbogado per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (GjAbogados) Objetop eliminado
     * @throws java.lang.Exception
     */
    GjAbogado eliminar(int id) throws Exception;
    
    /**
     * Metodo que consulta la persona
     * @param per
     * @return
     * @throws Exception 
     */
    GjAbogado consultarPersona(GjAbogado per) throws Exception; 
    
     /**
     * Consultar lista de registros
     * @return
     * @throws Exception 
    */
    List<Usuario> consultarTodosUsuarios()throws Exception;

   /**
     * Metodo que consulta documento del tercero
     * @param per
     * @return
     * @throws Exception 
     */
    GjAbogado consultarAbogado (GjAbogado per) throws Exception; 
    
    /**
     * Consulta de cantidad de registros en una lista - Buscador
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Consultar lista de registros - Buscador
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<GjAbogado> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;

}
