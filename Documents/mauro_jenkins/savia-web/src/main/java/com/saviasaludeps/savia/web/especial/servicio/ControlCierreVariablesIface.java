/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.web.especial.bean.ControlCierreVariablesBean;

/**
 *
 * @author jdlopez
 */
public interface ControlCierreVariablesIface {
      /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(ControlCierreVariablesBean bean);
    
    /**
     * Método para cargas inicial de variables
     * @param bean 
     */
    void cargaInicial(ControlCierreVariablesBean bean);
}
