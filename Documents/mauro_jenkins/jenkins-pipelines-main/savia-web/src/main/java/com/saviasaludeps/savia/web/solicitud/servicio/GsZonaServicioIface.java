/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.servicio;

import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.web.solicitud.bean.GsZonaBean;

/**
 *
 * @author jramirez
 */
public interface GsZonaServicioIface {

    /**
     *
     * @param bean
     */
    void Accion(GsZonaBean bean);

    /**
     *
     * @param bean
     */
    void cargaInicial(GsZonaBean bean);

    /**
     *
     * @param id
     * @return
     */
    GsZona consultar(int id);

    /**
     *
     * @param bean
     * @param query
     */
    void consultaUsuariosCarga(GsZonaBean bean, String query);
}
