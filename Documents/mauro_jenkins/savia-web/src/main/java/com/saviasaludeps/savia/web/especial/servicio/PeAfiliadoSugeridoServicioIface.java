/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.web.especial.bean.PeAfiliadoSugeridoBean;

/**
 *
 * @author idbohorquez
 */
public interface PeAfiliadoSugeridoServicioIface {
    
    
    /**
     * Método de acciones central
     * @author idbohorquez
     * @creacion 03/11/2022
     * @param bean 
     */
    void Accion(PeAfiliadoSugeridoBean bean);
    
    
    /**
     * Método para realizar carga inicial de información
     * @author idbohorquez
     * @creacion 16/11/2022
     * @param bean 
     */
    void cargasInicial(PeAfiliadoSugeridoBean bean);
    
}
