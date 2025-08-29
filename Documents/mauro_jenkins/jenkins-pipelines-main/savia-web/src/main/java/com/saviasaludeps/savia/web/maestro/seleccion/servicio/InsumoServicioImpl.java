/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaInsumoRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class InsumoServicioImpl  extends AccionesBO implements InsumoServicioIface{
    private MaInsumoRemoto getMaInsumoRemoto() throws Exception {
        return (MaInsumoRemoto) RemotoEJB.getEJBRemoto("MaInsumoServicio", MaInsumoRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelInsumosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaInsumoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta()));
            bean.setRegistros(getMaInsumoRemoto().consultarListaBuscador(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 
}