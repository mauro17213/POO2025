/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaIss2001TarifarioRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelIss2001Bean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class Iss2001ServicioImpl  extends AccionesBO implements Iss2001ServicioIface{
    private MaIss2001TarifarioRemoto getMaIss2001TarifarioRemoto() throws Exception {
        return (MaIss2001TarifarioRemoto) RemotoEJB.getEJBRemoto("MaIss2001TarifarioServicio", MaIss2001TarifarioRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelIss2001Bean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaIss2001TarifarioRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaIss2001TarifarioRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 
}
