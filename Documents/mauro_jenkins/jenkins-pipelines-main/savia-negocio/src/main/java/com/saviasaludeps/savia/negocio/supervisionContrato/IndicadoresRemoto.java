/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.negocio.supervisionContrato;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.supervisioncontrato.ScIndicador;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface IndicadoresRemoto {
    
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
    List<ScIndicador> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (ScIndicador) cargado
     * @throws java.lang.Exception
     */
    ScIndicador consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (ScIndicador)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(ScIndicador per) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (ScIndicador)
     * @throws java.lang.Exception
     */
    void actualizar(ScIndicador per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (ScIndicador) Objetop eliminado
     * @throws java.lang.Exception
     */
    ScIndicador eliminar(int id) throws Exception;
    
}
