/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface TuEstadoRemoto {
    
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
    List<TuTutelaEstado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuJuzgado) cargado
     * @throws java.lang.Exception
     */
    TuTutelaEstado consultar(int id) throws Exception;
    
      /**
     * Método para crear una nueva Empresa
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuTutelaEstado per) throws Exception;

    
     /**
     * Método para actualizar la información de un registro
     * @param obj (TuJuzgado)
     * @throws java.lang.Exception
     */
      void actualizar(TuTutelaEstado obj) throws Exception;
      
         /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuTutelaEstado eliminar(int id) throws Exception;

    List<TuTutelaEstado> consultarListaEstadosPorTutela(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que cuanta las cantidad de detalles dada una factura
     * @param id
     * @return
     * @throws Exception 
     */
    
    List<TuTutelaEstado> consultarEstadosPorTutela(int id) throws Exception;

    /**
     * Permite actualizar el responsable gn usuario de tabla usuario
     * @param obj
     * @throws Exception 
     */
    void actualizarResponsableGnUsuario(TuTutelaEstado obj) throws Exception;
    
    /**
     * Permite actualizar el proceso de la tabla tututelaEstado
     * @param obj
     * @throws Exception 
     */
    void actualizarProcesoEstado(TuTutelaEstado obj) throws Exception;
    
    /**
     * se obtiene la cantidad de items de los estados pertenecientes a la tutela
     * @return
     * @throws Exception 
     */
    int contarItemsTutela(int idTutela) throws Exception;
    
    /**
     * se obtiene la cantidad de items de los estados pertenecientes a la tutela
     * @return
     * @throws Exception 
     */
    int contarItemsCerradosTutela(int idTutela) throws Exception;
    
    /**
     * se obtiene la cantidad de items de los estados pertenecientes al estado de la tutela
     * @return
     * @throws Exception 
     */
    int contarItemsEstado(int idEstadoTutela) throws Exception;
    
    /**
     * se obtiene la cantidad de items de los estados pertenecientes al estado la tutela
     * @return
     * @throws Exception 
     */
    int contarItemsCerradosEstado(int idEstadoTutela) throws Exception;
    
}
