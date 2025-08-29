/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadoresUnionTemporal;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntPrestadorUnionTemporalRemoto {
    
    /**
     * Consultar lista de registro según parámetros establecidos
     * @param paramConsulta objeto de parametros para la consulta
     * @return Lista de Objetos CntPrestadoresUnionTemporal
     * @throws Exception 
     */
    List<CntPrestadoresUnionTemporal> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Contar cantidad de registros según parámetros establecidos
     * @param paramConsulta objeto de parametros para la consulta
     * @return cantidad de registros a listar
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CntPrestadoresUnionTemporal)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CntPrestadoresUnionTemporal obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CntPrestadoresUnionTemporal)
     * @throws java.lang.Exception
     */
    void actualizar(CntPrestadoresUnionTemporal obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CntPrestadoresUnionTemporal) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CntPrestadoresUnionTemporal eliminar(int id) throws Exception;

    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (CntPrestadoresUnionTemporal)
     * @throws Exception 
     */
    CntPrestadoresUnionTemporal consultar(int id) throws Exception;
    
    /**
     * Método para consultar la cantidad de registros similares por id de Prestador de Unión Temporal y id de Prestador asociado.
     * @param idPrestadorUnionTemporal
     * @param idPrestador
     * @return
     * @throws Exception 
     */
    int consultarPorPrestadorUnionTemporalIdyPrestadorId(int idPrestadorUnionTemporal, int idPrestador) throws Exception;
    
    /**
     * Consulta lista completa con datos minimos
     *
     * @return Lista de Objetos CntPrestadoresUnionTemporal
     * @throws Exception
     */
    List<CntPrestadoresUnionTemporal> consultarTodos() throws Exception;

}
