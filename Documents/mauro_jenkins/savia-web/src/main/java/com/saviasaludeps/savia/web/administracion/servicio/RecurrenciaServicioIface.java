/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.RecurrenciaBean;



/**
 *
 *@author jjmosquera
 */
public interface RecurrenciaServicioIface {

    void Accion(RecurrenciaBean bean);
    
    void cargasInicial(RecurrenciaBean bean);
    
}
