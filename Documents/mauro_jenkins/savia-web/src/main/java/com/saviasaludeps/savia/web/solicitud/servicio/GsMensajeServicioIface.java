/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.servicio;

import com.saviasaludeps.savia.web.solicitud.bean.GsMensajeBean;

/**
 *
 * @author jramirez
 */
public interface GsMensajeServicioIface {

    void Accion(GsMensajeBean bean);

    void listar(GsMensajeBean bean);

}
