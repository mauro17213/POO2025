/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.web.contratacion.bean.CargaMasivaContratoBean;


/**
 *
 * @author José Pérez
 */
public interface CargaMasivaContratoIface {
    
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CargaMasivaContratoBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(CargaMasivaContratoBean bean);
}
