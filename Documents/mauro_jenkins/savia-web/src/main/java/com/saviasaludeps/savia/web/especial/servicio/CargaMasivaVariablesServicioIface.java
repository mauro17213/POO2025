/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.web.especial.bean.CargaMasivaVariablesBean;


/**
 *
 * @author jdlopez
 */
public interface CargaMasivaVariablesServicioIface {
     /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CargaMasivaVariablesBean bean);
    
    /**
     * Método para cargas inicial de variables
     * @param bean 
     */
    void cargaInicial(CargaMasivaVariablesBean bean);
    
     /**
     * Método para verificar si hay un cierre de carga
     * donde retorna si es valido o no el cargue
     * @param bean 
     * @return  
     */
    boolean verificarCierreCarga(CargaMasivaVariablesBean bean);
}
