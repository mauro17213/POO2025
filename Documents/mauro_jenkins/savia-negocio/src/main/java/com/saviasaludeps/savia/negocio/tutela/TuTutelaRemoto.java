/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface TuTutelaRemoto {
    
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
    List<TuTutela> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuJuzgado) cargado
     * @throws java.lang.Exception
     */
    TuTutela consultar(int id) throws Exception;
    
    /**
     * Método para crear una nueva Empresa
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuTutela per) throws Exception;

    
     /**
     * Método para actualizar la información de un registro
     * @param obj (TuJuzgado)
     * @throws java.lang.Exception
     */
    void actualizar(TuTutela obj) throws Exception;
      
    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuTutela eliminar(int id) throws Exception;   
    
    /**
     * Permite obtener informacion de tutelas dados tipo y numero documento
     * @param tipoDocumento
     * @param numeroDocumento
     * @param idEstadoFallo
     * @return
     * @throws Exception 
     */
    List<TuTutelaRespuesta> consultarConExoneracionAfiliado(String tipoDocumento, String numeroDocumento, Integer idEstadoFallo) throws Exception;
    
    /**
     * Permite obtener informacion de tutelas dados id del afiliado
     * @param id
     * @param idEstadoFallo
     * @return
     * @throws Exception 
     */
    List<TuTutelaRespuesta> consultarConExoneracionAfiliadoPorId(Integer id, Integer idEstadoFallo) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<TuTutela> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta de cantidad de registros que tiene un afiliado
     * @param idAfiliado
     * @return
     * @throws Exception 
     */
    int consultarCantidadTutelasParaSolicitutesAnexoTres(Integer idAfiliado) throws Exception;
    
    /**
     * Actualiza el estado de la tutela
     * @param obj
     * @throws Exception 
     */
    void actualizarEstado(TuTutela obj) throws Exception;
    
    /**
     * Consultar lista de tutelas que tengan integralidad
     * @param idAfiliado
     * @return List<TuTutela>
     * @throws Exception 
     */
    List<TuTutela> consultarListaPorAfiliadoIntegralidad(int idAfiliado) throws Exception;
}
