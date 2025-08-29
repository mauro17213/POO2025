/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCarga;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCargaMasivaSinAutorizacionBean;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuCargaMasivaSinAutorizacionServicioIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(AuCargaMasivaSinAutorizacionBean bean);
    
    void cargaInicial(AuCargaMasivaSinAutorizacionBean bean);
    
    AuNoSolicitudCarga consultarCarga(AuCargaMasivaSinAutorizacionBean bean);

}
