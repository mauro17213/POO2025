/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaCarga;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCargaEntregaSinAutorizacionBean;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuCargaEntregaSinAutorizacionServicioIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(AuCargaEntregaSinAutorizacionBean bean);
    
    void cargaInicial(AuCargaEntregaSinAutorizacionBean bean);
    
    AuNoSolicitudEntregaCarga consultarCarga(AuCargaEntregaSinAutorizacionBean bean);

}
