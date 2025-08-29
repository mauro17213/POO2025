/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface TuJuzgadoRemoto {
    
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
    List<TuJuzgado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuJuzgado) cargado
     * @throws java.lang.Exception
     */
    TuJuzgado consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo Juzgado
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuJuzgado per) throws Exception;

    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuJuzgado)
     * @throws java.lang.Exception
     */
      void actualizar(TuJuzgado obj) throws Exception;
      
    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuJuzgado eliminar(int id) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param idUbicacion
     * @param idJuzgado
     * @return
     * @throws Exception 
     */
    List<TuJuzgado> consultarJuzgadoPorUbicacion(int idUbicacion) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param idUbicacion
     * @param idJuzgado
     * @return
     * @throws Exception 
     */
    List<TuJuzgado> consultarJuzgadoPorUbicacion(int idUbicacion, int idJuzgado) throws Exception;
}
