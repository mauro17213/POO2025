/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 * 
 * @author Jose Perez
 */
public interface MaInsumoMipresRemoto {

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
    List<MaInsumoMipres> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaInsumoMipres consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaInsumoMipres obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaInsumoMipres obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaInsumoMipres eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaInsumoMipres> consultarTodos() throws Exception;

    /**
     * Consultar un registro por su código
     * @param codigo
     * @return
     * @throws Exception 
     */
    MaInsumoMipres consultarPorCodigo(String codigo) throws Exception;
    
    /**
     * Consultar un registro por su código
     * @param idTecnologia
     * @param codigo
     * @return
     * @throws Exception 
     */
    MaInsumoMipres consultarPorTecnologiaYCodigoMipres(int idTecnologia,String codigo) throws Exception;
    
    /**
     * Consultar un registro por su código
     * @param codigoTecnologia
     * @param codigoMipres
     * @return
     * @throws Exception 
     */
    MaInsumoMipres consultarPorCodigoTecnologiaYCodigoMipres(String codigoTecnologia,String codigoMipres) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<MaInsumoMipres> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;

    }
