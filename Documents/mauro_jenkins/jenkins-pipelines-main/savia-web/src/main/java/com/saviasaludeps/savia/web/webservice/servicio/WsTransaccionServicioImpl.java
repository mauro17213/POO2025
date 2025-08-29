/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.servicio;

import com.saviasaludeps.savia.negocio.webservice.WsServicioRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.webservice.bean.WsTransaccionBean;
import com.saviasaludeps.savia.negocio.webservice.WsConexionRemoto;
import com.saviasaludeps.savia.negocio.webservice.WsTransaccionRemoto;

/**
 *
 * @author raul-palacios
 */
public class WsTransaccionServicioImpl extends AccionesBO implements WsTransaccionServicioIface {

    private WsTransaccionRemoto getTransaccionRemoto() throws Exception {
        return (WsTransaccionRemoto) RemotoEJB.getEJBRemoto("WsTransaccionServicio", WsTransaccionRemoto.class.getName());
    }
    
    private WsServicioRemoto getWsServicioRemoto() throws Exception {
        return (WsServicioRemoto) RemotoEJB.getEJBRemoto("WsServicioServicio", WsServicioRemoto.class.getName());
    }
    
    private WsConexionRemoto getWSConexionRemoto() throws Exception {
        return (WsConexionRemoto) RemotoEJB.getEJBRemoto("WsConexionServicio", WsConexionRemoto.class.getName());
    }
    
    @Override
    public void Accion(WsTransaccionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(WsTransaccionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getTransaccionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTransaccionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(WsTransaccionBean bean) {
        try {
            bean.setObjeto(getTransaccionRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(WsTransaccionBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void cargaInicial(WsTransaccionBean bean) {
        try {
            bean.setListaServicios(getWsServicioRemoto().consultarTodas());
            bean.setListaConexiones(getWSConexionRemoto().consultarTodas());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
