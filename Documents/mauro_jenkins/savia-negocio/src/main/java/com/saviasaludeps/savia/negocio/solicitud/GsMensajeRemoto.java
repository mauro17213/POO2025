/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.solicitud;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import java.util.List;

/**
 *
 * @author jramirez
 */
public interface GsMensajeRemoto {

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GsMensaje> consultarLista(ParamConsulta paramConsulta) throws Exception;

    GsMensaje consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GsMensaje obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GsMensaje obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GsMensaje eliminar(int id) throws Exception;
}
