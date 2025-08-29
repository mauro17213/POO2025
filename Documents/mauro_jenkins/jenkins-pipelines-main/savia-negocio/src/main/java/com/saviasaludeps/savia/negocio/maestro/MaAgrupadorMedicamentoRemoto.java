/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.maestro.MaAgrupadorMedicamento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

public interface MaAgrupadorMedicamentoRemoto {

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
    List<MaAgrupadorMedicamento> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaAgrupadorMedicamento consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaAgrupadorMedicamento obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaAgrupadorMedicamento obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (id) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaAgrupadorMedicamento eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaAgrupadorMedicamento> consultarTodos() throws Exception;
    
    /**
     * Consultar todos los registros y devolverlos en un hashmap
     * @return
     * @throws Exception 
     */
    public HashMap<Integer, MaAgrupadorMedicamento> consultarTodosHashMap() throws Exception;
    
    /**
     * Consultar todos los registros y devolverlos en un hashmap por código
     * @return
     * @throws Exception 
     */
    public HashMap<String, MaAgrupadorMedicamento> consultarTodosHashMapPorCodigo() throws Exception;
    
    /**
     * Consulta todo para el singleton
     * @return
     * @throws Exception 
     */
    List<MaAgrupadorMedicamento> consultarTodoSingleton() throws Exception;
}
