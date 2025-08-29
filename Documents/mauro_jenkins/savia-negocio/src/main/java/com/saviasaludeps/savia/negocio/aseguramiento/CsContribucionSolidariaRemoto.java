/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.CsContribucionSolidaria;
import java.math.BigDecimal;
import java.util.List;

public interface CsContribucionSolidariaRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception 
     */
    List<CsContribucionSolidaria> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (CsContribucionSolidaria) objeto consultadp
     * @throws java.lang.Exception
     */
    CsContribucionSolidaria consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (CsContribucionSolidaria) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CsContribucionSolidaria obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (CsContribucionSolidaria)
     * @throws java.lang.Exception
     */
    void actualizar(CsContribucionSolidaria obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (CsContribucionSolidaria) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CsContribucionSolidaria eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las CsContribucionSolidaria
     * @return
     * @throws Exception 
     */
    List<CsContribucionSolidaria> consultarTodos() throws Exception;
    
    /**
     * Consultar el registro de CsContribucionSolidaria por el id del maestro del porcentaje
     * @param idmaestroPorcentaje
     * @return
     * @throws Exception 
     */
    CsContribucionSolidaria consultarPorPorcentaje(int idmaestroPorcentaje) throws Exception;
    
}
