/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.seleccion.servicio;

import com.saviasaludeps.savia.web.mipres.seleccion.bean.SelCodigoMipresBean;



/**
 *
 * @author AlexanderDiaz
 */
public interface SelCodigoMipresServicioIface {

    /**
     * Método para carga inicial de variables
     * @param bean 
     */
    void cargaInicial(SelCodigoMipresBean bean);
    
}