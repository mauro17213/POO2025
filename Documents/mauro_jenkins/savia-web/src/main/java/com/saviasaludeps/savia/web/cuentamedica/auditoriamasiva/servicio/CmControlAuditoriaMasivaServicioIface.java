/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.servicio;

import com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.bean.CmControlAuditoriaMasivaBean;

/**
 *
 * @author admiin
 */
public interface CmControlAuditoriaMasivaServicioIface {
    
    /**
     * permite el control de acciones central
     * @param bean 
     */
    void Accion(CmControlAuditoriaMasivaBean bean);
    
    /**
     * Permite la carga inicial de variables
     * @param bean 
     */
    void cargaInicial(CmControlAuditoriaMasivaBean bean);
}
