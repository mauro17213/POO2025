/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

public interface MaMedicamentoRemoto {

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
    List<MaMedicamento> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaMedicamento consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaMedicamento obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaMedicamento obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaMedicamento eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaMedicamento> consultarTodos() throws Exception;

    /**
     * consultar un registro por cum
     * @param codigo
     * @return 
     * @throws java.lang.Exception 
     */
    MaMedicamento consultarPorCodigoCum(String codigo) throws Exception;

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
    List<MaMedicamento> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar una lista de medicamentos por el id del agrupador y los estados de registro sanitario 
     * Vigente , En tramite renov , Temp. no comerc - Vigente y Temp. no comercializado - En Trámite Renov
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public List<MaMedicamento> consultarPorAgrupadorIdYEstadoRegSan(int id) throws Exception;
    
    /**
     * * Método para consultar una lista de medicamentos por el id del agrupador y los estados de registro sanitario 
     * Vigente , En tramite renov , Temp. no comerc - Vigente y Temp. no comercializado - En Trámite Renov
     * 
     * @param codigo
     * @return
     * @throws Exception 
     */
    public List<MaMedicamento> consultarPorCodigoAgrupadorYEstadoRegSan(String codigo) throws Exception;
    
    /**
     * 
     * Método para consultar una lista de medicamentos activos por el id del agrupador
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public List<MaMedicamento> consultarPorAgrupadorId(int id) throws Exception;
    
    /**
     * 
     * Método para consultar una lista de medicamentos activos por el código del agrupador
     * 
     * @param codigo
     * @return
     * @throws Exception 
     */
    public List<MaMedicamento> consultarPorAgrupadorCodigo(String codigo) throws Exception;
    
    /**
     * Obtener los medicamentos en un hash identificados por código cum
     * 
     * @return
     * @throws Exception
     */
    HashMap<String, MaMedicamento> consultarHash() throws Exception;
    
    /**
     * Consulta todo para el singleton
     * @return
     * @throws Exception 
     */
    List<MaMedicamento> consultarTodoSingleton() throws Exception;
            
}
