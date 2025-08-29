/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CargaMasivaConciliacionBean;


/**
 *
 * @author admiin
 */
public interface CargaMasivaConciliacionServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CargaMasivaConciliacionBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(CargaMasivaConciliacionBean bean);
}
