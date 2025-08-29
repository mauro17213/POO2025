/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.seleccion.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorSedesBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PrestadorSedeImpl extends AccionesBO implements PrestadorSedeIface {
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelPrestadorSedesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta()));
            bean.setRegistros(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta()));
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
//            for (CntPrestadorSede registro : bean.getRegistros()) {
//                registro.setCntPrestador(getCntPrestadorRemoto().consultar(registro.getId()));
//            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
