/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.servicio;

import com.saviasaludeps.savia.web.financiera.bean.FinGiroBean;

/**
 *
 * @author jeperez
 */
public interface FinGiroServicioIface {
    
    void Accion(FinGiroBean bean);
    
    /**
     * Carga inicial de la opción
     * @param bean 
     */
    void cargasInicial(FinGiroBean bean);
    
}
