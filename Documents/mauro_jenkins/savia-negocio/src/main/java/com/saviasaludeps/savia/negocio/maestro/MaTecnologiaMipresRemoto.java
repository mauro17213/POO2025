/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import java.util.List;

/**
 *
 * @author Jose Perez
 */
public interface MaTecnologiaMipresRemoto {

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
    List<MaTecnologiaMipres> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaTecnologiaMipres consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaTecnologiaMipres obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaTecnologiaMipres obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaTecnologiaMipres eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaTecnologiaMipres> consultarTodos() throws Exception;

    /**
     * Consultar un registro por su código
     * @param codigo
     * @return
     * @throws Exception 
     */
    MaTecnologiaMipres consultarPorCodigo(String codigo) throws Exception;
    
    /**
     * Consultar un registro por su código
     * @param idTecnologia
     * @param codigo
     * @return
     * @throws Exception 
     */
    MaTecnologiaMipres consultarPorTecnologiaYCodigoMipres(int idTecnologia,String codigo) throws Exception;
    
    /**
     * Consultar un registro por su código
     * @param codigoTecnologia
     * @param codigoMipres
     * @return
     * @throws Exception 
     */
    MaTecnologiaMipres consultarPorCodigoTecnologiaYCodigoMipres(String codigoTecnologia,String codigoMipres) throws Exception;
    
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
    List<MaTecnologiaMipres> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;
}
