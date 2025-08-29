/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class ServicioHabilitacionServicioImpl  extends AccionesBO implements ServicioHabilitacionServicioIface{
    private MaServicioHabilitacionRemoto getMaServicioHabilitacionRemoto() throws Exception {
        return (MaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("MaServicioHabilitacionServicio", MaServicioHabilitacionRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelServiciosHabilitacionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaServicioHabilitacionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaServicioHabilitacionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 
}
