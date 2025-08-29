/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaPaqueteRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class PaqueteServicioImpl  extends AccionesBO implements PaqueteServicioIface{
    private MaPaqueteRemoto getMaPaqueteRemoto() throws Exception {
        return (MaPaqueteRemoto) RemotoEJB.getEJBRemoto("MaPaqueteServicio", MaPaqueteRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelPaquetesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaPaqueteRemoto().consultarCantidadListaBuscador(bean.getParamConsulta()));
            bean.setRegistros(getMaPaqueteRemoto().consultarListaBuscador(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 
}