/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuSolicitudAdjuntoRemoto {

    /**
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuSolicitudAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuSolicitudAdjunto consultar(int id) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuSolicitudAdjunto obj) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuSolicitudAdjunto eliminar(int id) throws Exception;

    /**
     *
     * @param idCotizacion
     * @return
     * @throws Exception
     */
    List<AuSolicitudAdjunto> listarAdjuntosByIdCotizacion(int idCotizacion) throws Exception;
    
    /**
     *
     * @param idTopeAfiliado
     * @return
     * @throws Exception
     */
    List<AuSolicitudAdjunto> listarAdjuntosByIdTopeAfiliado(int idTopeAfiliado) throws Exception;
    
    /**
     *
     * @param idNoSolicitudes
     * @return
     * @throws Exception
     */
    List<AuSolicitudAdjunto> listarAdjuntosByIdNoSolicitudes(int idNoSolicitudes) throws Exception;
    
    
    /**
     *
     * @param idNoSollicitudesEntregas
     * @return
     * @throws Exception
     */
    List<AuSolicitudAdjunto> listarAdjuntosByIdNoSollicitudesEntregas(int idNoSollicitudesEntregas) throws Exception;
}
