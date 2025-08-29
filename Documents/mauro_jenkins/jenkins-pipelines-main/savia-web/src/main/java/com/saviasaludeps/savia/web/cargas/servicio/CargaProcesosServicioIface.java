/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.servicio;

import com.saviasaludeps.savia.web.cargas.bean.CargaProcesosBean;

/**
 *
 * @author aguevara
 */
public interface CargaProcesosServicioIface {
    
    void Accion(CargaProcesosBean bean);
    
    void cargasInicial(CargaProcesosBean bean);
    
}
