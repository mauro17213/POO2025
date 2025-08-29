/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.rips.regla.servicio;

import com.saviasaludeps.savia.web.cuentamedica.rips.regla.bean.CmControlCierreBean;

/**
 *
 * @author raul-palacios
 */
public interface CmControlCierreServicioIface {

    void Accion(CmControlCierreBean bean);

    void cargasInicial(CmControlCierreBean bean);

    void editar(CmControlCierreBean bean);

    void guardar(CmControlCierreBean bean);

}
