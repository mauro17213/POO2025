/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.web.maestro.bean.AgrupadoresMedicamentoBean;


/**
 *
 * @author jarodriguez
 */
public interface AgrupadoresServicioIface {
    //AgrupadoresMedicamentoBean
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(AgrupadoresMedicamentoBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(AgrupadoresMedicamentoBean bean);
}
