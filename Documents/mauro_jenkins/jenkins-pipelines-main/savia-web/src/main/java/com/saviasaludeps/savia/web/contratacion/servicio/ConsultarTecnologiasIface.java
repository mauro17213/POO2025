/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.web.contratacion.bean.ConsultarTecnologiasBean;


/**
 *
 * @author José Pérez
 */
public interface ConsultarTecnologiasIface {
    
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(ConsultarTecnologiasBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(ConsultarTecnologiasBean bean);
}
