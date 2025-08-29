/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaIss2001Tarifario;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface MaIss2000TarifarioRemoto {
    
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
    List<MaIss2000Tarifario> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaIss2000Tarifario consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaIss2000Tarifario obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaIss2000Tarifario obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaIss2000Tarifario eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaIss2000Tarifario> consultarTodos() throws Exception;

    /**
     * consultar un registro por su código
     * @param codigo
     * @return 
     * @throws java.lang.Exception 
     */
    MaIss2000Tarifario consultarPorCodigo(String codigo) throws Exception;
}
