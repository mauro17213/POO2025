/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.servicio;

import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.negocio.webservice.WsServicioRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.webservice.bean.WsServicioBean;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class WsServicioImpl extends AccionesBO implements WsServicioIface {

    private WsServicioRemoto getWsServicioRemoto() throws Exception {
        return (WsServicioRemoto) RemotoEJB.getEJBRemoto("WsServicioServicio", WsServicioRemoto.class.getName());
    }

    @Override
    public void Accion(WsServicioBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(WsServicioBean bean) {
        
    }

    private void listar(WsServicioBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getWsServicioRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getWsServicioRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(WsServicioBean bean) {
        try {
            WsServicio objeto = getWsServicioRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(objeto);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(WsServicioBean bean) {
        try {
            bean.setObjeto(new WsServicio());
            bean.getObjeto().setFechaHoraCrea(new Date());
            bean.getObjeto().setActivo(false);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(WsServicioBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getWsServicioRemoto().insertar(bean.getObjeto()));            
            if (bean.getObjeto().getId() != null) {
                bean.addMensaje("Se creo un registro de manera exitosa");
            } else {
                bean.addError("Error al guardar el registro");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void editar(WsServicioBean bean) {
        try {
            WsServicio objeto = getWsServicioRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(objeto);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
    private void modificar(WsServicioBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getWsServicioRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrar(WsServicioBean bean) {
        try {       
            bean.setObjeto(getWsServicioRemoto().eliminar(bean.getObjeto().getId()));
            
            if (bean.getObjeto() != null) {
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            } else {
                bean.addError("Error al eliminar el registro");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    

}
