/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.web.tutela.bean.TuRepresentanteBean;

/**
 *
 * @author pavacca
 */
public interface TuRepresentanteServicioIface {

    void Accion(TuRepresentanteBean bean);

    /**
     * Cargar lista TuRepresentantes
     *
     * @param bean
     */
    void cargaInicial(TuRepresentanteBean bean);

}
