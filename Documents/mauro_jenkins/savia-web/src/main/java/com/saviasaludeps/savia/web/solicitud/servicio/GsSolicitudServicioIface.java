/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.servicio;

import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.web.solicitud.bean.GsSolicitudBean;
import java.util.List;

/**
 *
 * @author jramirez
 */
public interface GsSolicitudServicioIface {

    /**
     *
     * @param bean
     */
    void Accion(GsSolicitudBean bean);

    /**
     *
     * @param bean
     */
    void cargaInial(GsSolicitudBean bean);

    /**
     * Consultar solicitud por Id
     *
     * @param id
     * @return
     */
    GsSolicitud consultarSolicitud(int id);

    /**
     * Consulta de ñlista de mensajes por tipo y estado
     *
     * @param tipo
     * @param estado
     * @return
     */
    List<GsMensaje> consultarMenajesPorTipoEstado(int tipo, int estado);
}
