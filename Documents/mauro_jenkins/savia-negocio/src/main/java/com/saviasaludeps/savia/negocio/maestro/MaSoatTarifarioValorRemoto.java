/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface MaSoatTarifarioValorRemoto {
    
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
    List<MaSoatTarifarioValor> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaSoatTarifarioValor consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaSoatTarifarioValor obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaSoatTarifarioValor obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaSoatTarifarioValor eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaSoatTarifarioValor> consultarTodos() throws Exception;
    
    /**
     * 
     * @param idMaSoatTarifario
     * @return
     * @throws Exception 
     */
    List<MaSoatTarifarioValor> consultarPorSoatTarifario(int idMaSoatTarifario) throws Exception;

    /**
     * consultar un registro por id Manual Tarifario y Año
     * @param IdmanualTarifario
     * @param agno
     * @return 
     * @throws java.lang.Exception 
     */
    public MaSoatTarifarioValor consultarPorSoatTarifarioYAgno(Integer IdmanualTarifario, String agno) throws Exception;
    
    /**
     * consulta tarifario por codigo tecnologia y ultimo año registrado
     * @param codigoPropio
     * @return
     * @throws java.lang.Exception 
     */
    MaSoatTarifarioValor consultarTarifarioTecnolgia(String codigoPropio) throws java.lang.Exception;
}
