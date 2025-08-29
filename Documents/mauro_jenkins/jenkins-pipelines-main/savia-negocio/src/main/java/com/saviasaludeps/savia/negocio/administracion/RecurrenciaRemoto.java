/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Recurrencia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jjmosquera
 */
public interface RecurrenciaRemoto {

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
    List<Recurrencia> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar una recurrencia  por ID
     *
     * @param id
     * @return (Usuarios) cargado
     * @throws java.lang.Exception
     */
    Recurrencia consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Recurrencia
     *
     * @param obj   
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Recurrencia obj) throws Exception;

    /**
     * Método para actualizar la información de una Recurrencia
     *
     * @param per (Recurrencia)
     * @throws java.lang.Exception
     */
    void actualizar(Recurrencia obj) throws Exception;

    /**
     * Método para eliminar un Recurrencia
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    Recurrencia eliminar(int id) throws Exception;

    /**
     * Consultar recurrencia por nombre de recurrencia
     *
     * @param nombre
     * @return
     * @throws Exception
     */
    Recurrencia consultarPorRecurrencia(String nombre) throws Exception;

}
