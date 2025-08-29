/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmFacturaConciliacionBean;


/**
 *
 * @author raul-palacios
 */
public interface CmFacturaConciliacionServicioIface {
    
    /**
     * 
     * @param bean 
     */
    void Accion(CmFacturaConciliacionBean bean);
    
    /**
     * 
     * @param bean 
     */
    void cargaInial(CmFacturaConciliacionBean bean);
}
