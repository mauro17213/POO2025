/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaIss2000TarifarioRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelIss2000Bean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class Iss2000ServicioImpl  extends AccionesBO implements Iss2000ServicioIface{
    private MaIss2000TarifarioRemoto getMaIss2000TarifarioRemoto() throws Exception {
        return (MaIss2000TarifarioRemoto) RemotoEJB.getEJBRemoto("MaIss2000TarifarioServicio", MaIss2000TarifarioRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelIss2000Bean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaIss2000TarifarioRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaIss2000TarifarioRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 
}
