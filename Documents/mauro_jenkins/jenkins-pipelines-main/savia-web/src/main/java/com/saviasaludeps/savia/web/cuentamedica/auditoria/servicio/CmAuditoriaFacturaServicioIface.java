/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio;

import com.saviasaludeps.savia.web.cuentamedica.auditoria.bean.CmAuditoriaFacturaBean;


/**
 *
 * @author jeperez
 */
public interface CmAuditoriaFacturaServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CmAuditoriaFacturaBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(CmAuditoriaFacturaBean bean);
   
}
