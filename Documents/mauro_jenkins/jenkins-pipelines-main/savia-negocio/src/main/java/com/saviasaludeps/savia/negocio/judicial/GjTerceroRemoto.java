/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;



import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface GjTerceroRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
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
    List<GjTercero> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GjTercero> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (GjTerceros) cargado
     * @throws java.lang.Exception
     */
    GjTercero consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (GjTerceros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    Integer insertar(GjTercero per) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (GjTerceros)
     * @throws java.lang.Exception
     */
    void actualizar(GjTercero per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (GjTerceros) Objetop eliminado
     * @throws java.lang.Exception
     */
    GjTercero eliminar(int id) throws Exception;
    
    /**
     * Metodo que consulta la persona
     * @param per
     * @return
     * @throws Exception 
     */
    GjTercero consultarPersona(GjTercero per) throws Exception; 
    
    
    
/**
     * Metodo que consulta documento del tercero
     * @param per
     * @return
     * @throws Exception 
     */
    GjTercero consultarTerCero (GjTercero per) throws Exception; 
   

}
