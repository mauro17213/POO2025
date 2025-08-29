/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.web.supervisioncontrato.servicio;

import com.saviasaludeps.savia.web.supervisioncontrato.bean.IndicadoresBean;

/**
 *
 * @author aguevara
 */
public interface IndicadoresServicioIface {
    
    
    /**
     * Método de acciones central
     * @author aguevara
     * @creacion 01/04/2025
     * @param bean 
     */
    void Accion(IndicadoresBean bean);
    
    
    /**
     * Método para realizar carga inicial de información
     * @author aguevara
     * @creacion 01/04/2025
     * @param bean 
     */
    void cargasInicial(IndicadoresBean bean);
    
}
