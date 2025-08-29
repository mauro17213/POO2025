/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.JrAco;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jhonatan Jimenez
 */
public interface JrAcoRemoto {
    
    /**
     * Método para retronar el listaod de registros
     * 
     * @param paramConsulta: Contiene los listros de la consulta
     * @return (List<JrAco>) lista de registros cargados
     * @throws Exception 
     */
    List<JrAco> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar la cantidad de registros de la lista
     * 
     * @param paramConsulta: Contiene los listros de la consulta
     * @return (int) total de registros
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (JrAco) cargado
     * @throws java.lang.Exception
     */
    JrAco consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     *
     * @param obj (JrAco)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(JrAco obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (JrAco)
     * @throws java.lang.Exception
     */
    void actualizar(JrAco obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (JrAco) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    JrAco eliminar(int id) throws Exception;
    
}
