/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.solicitud;

import com.saviasaludeps.savia.ejb.entidades.GsZonaUsuarios;
import com.saviasaludeps.savia.ejb.entidades.GsZonas;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jramirez
 */
public interface GsZonaLocal {

    /**
     * Consultar zona por ubicacion
     *
     * @param ubicacionId
     * @param em
     * @return
     */
    GsZonas consultarPorUbicacion(int ubicacionId, EntityManager em);

    /**
     * Consultar zona por defecto
     *
     * @param em
     * @return
     */
    GsZonas consultarPorDefecto(EntityManager em);

    /**
     *
     * @param zonaId
     * @param tipoSolicitud
     * @param em
     * @return
     */
    List<GsZonaUsuarios> consultarUsuariosActivos(int zonaId, int tipoSolicitud, EntityManager em);

}
