/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelSoatBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class SoatServicioImpl  extends AccionesBO implements SoatServicioIface{
    private MaSoatTarifarioRemoto getMaSoatTarifarioRemoto() throws Exception {
        return (MaSoatTarifarioRemoto) RemotoEJB.getEJBRemoto("MaSoatTarifarioServicio", MaSoatTarifarioRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelSoatBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaSoatTarifarioRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaSoatTarifarioRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 
}
