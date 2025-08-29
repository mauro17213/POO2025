/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialPersona;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface TuMemorialPersonaRemoto {

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
    List<TuMemorialPersona> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (TuMemorialPersona) cargado
     * @throws java.lang.Exception
     */
    TuMemorialPersona consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo TuMemorialFirma
     *
     * @param per (TuMemorialPersona)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuMemorialPersona per) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (TuMemorialPersona)
     * @throws java.lang.Exception
     */
    void actualizar(TuMemorialPersona obj) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (TuMemorialPersona) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuMemorialPersona eliminar(int id) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @return
     * @throws Exception
     */
    List<TuMemorialPersona> consultarListaApoderados() throws Exception;
    
    
    /**
     * Consultar lista de registros
     *
     * @return
     * @throws Exception
     */
    List<TuMemorialPersona> consultarListaAsistenten() throws Exception;
}
