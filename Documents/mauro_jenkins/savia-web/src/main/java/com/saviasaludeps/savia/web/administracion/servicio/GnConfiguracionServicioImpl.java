/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.negocio.administracion.ConfiguracionRemoto;
import com.saviasaludeps.savia.web.administracion.bean.GnConfiguracionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author Jaime Andres Olarte
 */
public class GnConfiguracionServicioImpl extends AccionesBO implements GnConfiguracionServicioIface {
    
    private ConfiguracionRemoto getConfiguracionRemoto() throws Exception {
        return (ConfiguracionRemoto) RemotoEJB.getEJBRemoto("ConfiguracionServicio", ConfiguracionRemoto.class.getName());
    }

    @Override
    public void Accion(GnConfiguracionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_EDITAR:
                    modificar(bean);
                    break;
            }
        }
    }

    @Override
    public void cargaInial(GnConfiguracionBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void listar(GnConfiguracionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getConfiguracionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getConfiguracionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(GnConfiguracionBean bean) {
        try {
            getConfiguracionRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(GnConfiguracionBean bean) {
        try {
            bean.getObjeto().setId(getConfiguracionRemoto().consultarIdNuevo());
            getConfiguracionRemoto().insertar(bean.getObjeto());
            bean.addMensaje("Se creo el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
}
