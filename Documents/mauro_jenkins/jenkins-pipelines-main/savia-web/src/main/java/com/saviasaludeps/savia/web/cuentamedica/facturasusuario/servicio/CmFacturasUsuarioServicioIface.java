/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.facturasusuario.servicio;

import com.saviasaludeps.savia.web.cuentamedica.facturasusuario.bean.CmFacturasUsuarioBean;

/**
 *
 * @author jepn
 */
public interface CmFacturasUsuarioServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CmFacturasUsuarioBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(CmFacturasUsuarioBean bean);
    
}
