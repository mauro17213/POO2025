/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.web.especial.bean.GestionDireccionadosBean;

/**
 *
 * @author idbohorquez
 */
public interface GestionDireccionadoServicioIface {

    /**
     * Método de acciones centrales
     *
     * @author idbohorquez
     * @param bean
     */
    void Accion(GestionDireccionadosBean bean);

    /**
     * Método para cargas inicial de variables
     *
     * @author idbohorquez
     * @param bean
     */
    void cargaInicial(GestionDireccionadosBean bean);



}
