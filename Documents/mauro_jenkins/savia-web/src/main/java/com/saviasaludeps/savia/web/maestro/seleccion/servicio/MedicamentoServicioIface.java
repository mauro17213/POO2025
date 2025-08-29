/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;

/**
 *
 * @author AlexanderDiaz
 */
public interface MedicamentoServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(SelMedicamentoBean bean);
    
    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(SelMedicamentoBean bean);
    
}
