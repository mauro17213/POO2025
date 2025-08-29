/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.solicitud;

import com.saviasaludeps.savia.dominio.solicitud.GsNotificacion;

/**
 *
 * @author jramirez
 */
public interface GsNotificacionRemoto {

    int insertar(GsNotificacion notificacion) throws Exception;

    void actualizar(GsNotificacion obj) throws Exception;

    GsNotificacion eliminar(int id) throws Exception;
}
