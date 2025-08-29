/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.web.informe.servicio;

import com.saviasaludeps.savia.web.informe.bean.ProcedimientosBean;

/**
 *
 * @author aguevara
 */
public interface ProcedimientosServicioIface {
    
        /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(ProcedimientosBean bean);

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void cargaInicial(ProcedimientosBean bean);
    
}
