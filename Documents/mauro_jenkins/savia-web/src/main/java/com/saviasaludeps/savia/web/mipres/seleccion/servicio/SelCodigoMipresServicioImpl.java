/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.seleccion.servicio;

import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionDetalleRemoto;
import com.saviasaludeps.savia.web.mipres.seleccion.bean.SelCodigoMipresBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class SelCodigoMipresServicioImpl  extends AccionesBO implements SelCodigoMipresServicioIface{
    private MpPrescripcionDetalleRemoto getMpPrescripcionDetalleRemoto() throws Exception {
        return (MpPrescripcionDetalleRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionDetalleServicio", MpPrescripcionDetalleRemoto.class.getName());
    }
    
    
    
    @Override
    public void cargaInicial(SelCodigoMipresBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaPorCodigoInsumo(bean.getParamConsulta()));
            bean.setRegistros(getMpPrescripcionDetalleRemoto().consultarListaPorCodigoInsumo(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
