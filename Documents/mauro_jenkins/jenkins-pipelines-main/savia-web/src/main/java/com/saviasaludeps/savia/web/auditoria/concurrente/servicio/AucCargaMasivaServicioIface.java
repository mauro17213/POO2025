/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.servicio;

import com.saviasaludeps.savia.web.auditoria.concurrente.bean.AucCargaMasivaBean;

/**
 *
 * @author sgiraldov
 */
public interface AucCargaMasivaServicioIface {
    
    void Accion(AucCargaMasivaBean bean);

    void cargasInicial(AucCargaMasivaBean bean);
    
}
