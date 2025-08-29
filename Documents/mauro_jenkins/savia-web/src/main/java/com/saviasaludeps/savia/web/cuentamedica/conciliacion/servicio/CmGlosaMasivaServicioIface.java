/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmGlosaMasivaBean;



/**
 *
 * @author jepn
 */
public interface CmGlosaMasivaServicioIface {
    
    /**
     * 
     * @param bean 
     */
    void Accion(CmGlosaMasivaBean bean);
    
    /**
     * 
     * @param bean 
     */
    void cargaInial(CmGlosaMasivaBean bean);
    
}
