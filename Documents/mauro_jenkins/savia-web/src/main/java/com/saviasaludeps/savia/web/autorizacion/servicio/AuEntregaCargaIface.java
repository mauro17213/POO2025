/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.web.autorizacion.bean.AuEntregaCargaBean;

/**
 *
 * @author iavenegas
 */
public interface AuEntregaCargaIface {

    void Accion(AuEntregaCargaBean bean);

    void cargasInicial(AuEntregaCargaBean bean);
}
