/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.solicitud;

import com.saviasaludeps.savia.dominio.solicitud.GsAdjunto;
import java.util.List;

/**
 *
 * @author jramirez
 */
public interface GsAdjuntoRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    GsAdjunto consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GsAdjunto obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(GsAdjunto obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    GsAdjunto eliminar(int id) throws Exception;

    /**
     * Metodo que permite consultar casos por una seria de filtros entre ellos
     * (tipo documento, documeto, idcaso)
     *
     * @param solicitudId
     * @return
     * @throws Exception
     */
    public List<GsAdjunto> consultarPorSolicitud(int solicitudId) throws Exception;

}
