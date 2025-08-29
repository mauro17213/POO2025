/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.consulta.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.consulta.bean.SelPrestadorSedesGenericoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author José Pérez Hernández
 */
public class SelPrestadorSedesGenericoImpl extends AccionesBO implements SelPrestadorSedesGenericoIface {
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelPrestadorSedesGenericoBean bean) {
        try {
            if (bean.getCntPrestador() == null) {
                bean.getParamConsulta().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta()));
                bean.setRegistros(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta()));
                bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                for (CntPrestadorSede registro : bean.getRegistros()) {
                    if (registro.getCntPrestador() != null) {
                        registro.setCntPrestador(getCntPrestadorRemoto().consultar(registro.getCntPrestador().getId()));
                    }else {
                        registro.setCntPrestador(new CntPrestador(0, ""));
                    }
                }
            } else {
                //2025-05-26 jyperez validamos si es un prestador marcado como unión temporal. Si lo es entonces ejecutamos 
                // una consulta nueva configurando la validación de la obtención de las sedes pertenecientes a cada prestador en la unión temporal
                if (bean.getCntPrestador().getUnionTemporal()) {
                    bean.getParamConsulta().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSedeUnionTemporal(bean.getParamConsulta()));
                    bean.setRegistros(getCntPrestadorRemoto().consultarListaSedeUnionTemporal(bean.getParamConsulta()));
                    bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                    for (CntPrestadorSede registro : bean.getRegistros()) {
                        registro.setCntPrestador(bean.getCntPrestador());
                    }
                } else {
                    bean.getParamConsulta().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta()));
                    bean.setRegistros(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta()));
                    bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                    for (CntPrestadorSede registro : bean.getRegistros()) {
                        registro.setCntPrestador(bean.getCntPrestador());
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
