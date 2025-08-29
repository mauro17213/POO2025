/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.servicio;

import com.saviasaludeps.savia.web.auditoria.concurrente.bean.AucCargaMasivaValorBean;

/**
 *
 * @author idbohorquez
 */
public interface AucCargaMasivaValorServicioIface {
    
    void Accion(AucCargaMasivaValorBean bean);
    
    void cargaInicial(AucCargaMasivaValorBean bean);
    
}
