/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.web.autorizacion.bean.CopagoModeradoraBean;

/**
 *
 * @author iavenegas
 */
public interface CopagoModeradoraIface {

    /**
     *
     * @param bean
     */
    void Accion(CopagoModeradoraBean bean);

    /**
     *
     * @param bean
     */
    void cargaInicial(CopagoModeradoraBean bean);

    /**
     * contratos para seleccion
     * @param bean
     */
    void listarContratoDetalle(CopagoModeradoraBean bean);

    /**
     * listar copago historico por afiliado y anio actual
     * @param bean
     */
    void listarCopagoModeradoraHistorico(CopagoModeradoraBean bean);
}
