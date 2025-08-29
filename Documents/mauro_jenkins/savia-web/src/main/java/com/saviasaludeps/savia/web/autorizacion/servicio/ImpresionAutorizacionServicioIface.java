/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.web.autorizacion.bean.ImpresionAutorizacionBean;

/**
 *
 * @author Stiven Giraldo
 */
public interface ImpresionAutorizacionServicioIface {
    
    void Accion(ImpresionAutorizacionBean bean);
    
    void cargaInicial(ImpresionAutorizacionBean bean);
    
}
