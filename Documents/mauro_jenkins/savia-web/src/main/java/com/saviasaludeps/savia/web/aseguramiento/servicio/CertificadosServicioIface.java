/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.web.aseguramiento.bean.CertificadosBean;

/**
 *
 * @author José Pérez
 */
public interface CertificadosServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CertificadosBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInicial(CertificadosBean bean);
    
}
