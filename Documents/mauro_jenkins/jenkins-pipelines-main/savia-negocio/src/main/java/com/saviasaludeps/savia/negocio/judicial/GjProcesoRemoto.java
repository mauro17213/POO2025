/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import java.util.List;

/**
 *
 * @author bsgomez
 */
public interface GjProcesoRemoto {

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
    List<GjProceso> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (GjProceso) cargado
     * @throws java.lang.Exception
     */
    GjProceso consultar(int id) throws Exception;

    /**
     * Método para consultar una lista registros
     *
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GjProceso> consultarProces(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (GjProceso)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GjProceso per) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (GjProceso)
     * @throws java.lang.Exception
     */
    void actualizar(GjProceso obj) throws Exception;
    
    /**
     * Método para actualizar Borrado  de un registro
     *
     * @param obj (GjProceso)
     * @throws java.lang.Exception
     */
    void borradoActualizar(GjProceso obj) throws Exception;

    /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    GjProceso eliminar(int id) throws Exception;

    /**
     * Permite consultar por id grupo
     *
     * @param idJuzgado
     * @return
     * @throws Exception
     */
    List<GjProceso> consultarListaPorIdJuzgado(int idJuzgado) throws Exception;
    
    /**
     * Metodo que consulta radicado del proceso
     * @param per
     * @return
     * @throws Exception 
     */
    GjProceso consultarRadicado (GjProceso per) throws Exception; 
}
