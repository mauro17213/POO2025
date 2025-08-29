/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaAgrupadoresMedicamentoRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelAgrupadoresBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author jarodriguez
 */

public class AgrupadoresServicioImpl extends AccionesBO implements AgrupadoresServicioIface {
    private MaAgrupadoresMedicamentoRemoto getMaAgrupadoresRemoto () throws Exception{
        return (MaAgrupadoresMedicamentoRemoto) RemotoEJB.getEJBRemoto("MaAgrupadoresServicio", MaAgrupadoresMedicamentoRemoto.class.getName());   
    }
    
    @Override
    public void cargaInicial(SelAgrupadoresBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaAgrupadoresRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaAgrupadoresRemoto().consultarLista(bean.getParamConsulta()));
        }catch (Exception e){
            bean.addError(e.getMessage());
        }
    }
}
