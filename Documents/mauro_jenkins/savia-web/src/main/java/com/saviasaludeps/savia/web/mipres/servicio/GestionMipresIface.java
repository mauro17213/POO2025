/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.servicio;

import com.saviasaludeps.savia.web.mipres.bean.GestionMipresBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface GestionMipresIface {
    /**
     * Método de acciones central
     * @param bean 
     */
    void accion(GestionMipresBean bean);
    
    void cargaInicial(GestionMipresBean  bean);
}
