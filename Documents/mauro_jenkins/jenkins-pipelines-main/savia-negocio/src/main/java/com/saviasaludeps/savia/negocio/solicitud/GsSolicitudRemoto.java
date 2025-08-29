/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.solicitud;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsAsignacionUsuario;
import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import java.util.List;

/**
 *
 * @author jramirez
 */
public interface GsSolicitudRemoto {

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
    List<GsSolicitud> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    GsSolicitud consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GsSolicitud obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(GsSolicitud obj) throws Exception;

    /**
     * Actualizar estado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(GsSolicitud obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    GsSolicitud eliminar(int id) throws Exception;

    /**
     * Generación próximo
     *
     * @param tipoSolicitud
     * @param ubicacionId
     * @return
     * @throws Exception
     */
    GsAsignacionUsuario proximaAsignacion(int tipoSolicitud, int ubicacionId) throws Exception;

    /**
     * Consultar lista de mensajes por tipo y estado
     *
     * @param tipo
     * @param estado
     * @return
     * @throws Exception
     */
    List<GsMensaje> consultarMensajesPorTipoEstado(int tipo, int estado) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaExterna(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GsSolicitud> consultarListaExterna(ParamConsulta paramConsulta) throws Exception;
}
