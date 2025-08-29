/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.servicio;

import com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.bean.CmAuditoriaMasivaBean;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaMasivaServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CmAuditoriaMasivaBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(CmAuditoriaMasivaBean bean);
   
}
