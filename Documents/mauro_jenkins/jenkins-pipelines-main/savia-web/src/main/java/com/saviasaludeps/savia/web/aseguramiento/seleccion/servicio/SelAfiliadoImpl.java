/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.seleccion.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.web.aseguramiento.seleccion.bean.SelAfiliadoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.HashMap;

/**
 *
 * @author Jaime Andres Olarte
 */
public class SelAfiliadoImpl extends AccionesBO implements SelAfiliadoIface {
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelAfiliadoBean bean) {
        try {
            //cargamos maestros
            bean.setListaTiposDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumentoPersona(new HashMap());
            bean.getListaTiposDocumentoPersona().forEach(m -> {
                bean.getHashTiposDocumentoPersona().put(m.getId(), m);
            });
            //Maestro Estado afiliación
            bean.setListaEstadosAfiliacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashEstadosAfiliacion(new HashMap());
            bean.getListaEstadosAfiliacion().forEach(m -> {
                bean.getHashEstadosAfiliacion().put(m.getId(), m);
            });
            //2021-10-27 jyperez Maestro regimen
            bean.setListaRegimen(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashRegimen(new HashMap());
            bean.getListaRegimen().forEach(m -> {
                bean.getHashRegimen().put(m.getId(), m);
            });
            /*bean.getParamConsulta().setCantidadRegistros(getAfiliadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAfiliadoRemoto().consultarLista(bean.getParamConsulta()));
            for (AsegAfiliado registro : bean.getRegistros()) {
                if (registro.getResidenciaUbicacion() != null) {
                    registro.setResidenciaUbicacion(getUbicacionRemoto().consultar(registro.getResidenciaUbicacion().getId()));
                }
            }*/
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void listar(SelAfiliadoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta()));
            bean.setRegistros(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta()));
            /*for (AsegAfiliado registro : bean.getRegistros()) {
                if (registro.getResidenciaUbicacion() != null) {
                    registro.setResidenciaUbicacion(getUbicacionRemoto().consultar(registro.getResidenciaUbicacion().getId()));
                }
            }*/
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
