/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.servicio;

import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.negocio.solicitud.GsMensajeRemoto;
import com.saviasaludeps.savia.web.solicitud.bean.GsMensajeBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author jramirez
 */
public class GsMensajeServicioImpl extends AccionesBO implements GsMensajeServicioIface{
       
    private GsMensajeRemoto getGsMensajeRemoto() throws Exception {
        return (GsMensajeRemoto) RemotoEJB.getEJBRemoto("GsMensajeServicio", GsMensajeRemoto.class.getName());
    }
     
    @Override
    public void Accion(GsMensajeBean bean) {
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
                case Url.ACCION_ADICIONAL_1:
                    
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    @Override
    public void listar(GsMensajeBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGsMensajeRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGsMensajeRemoto().consultarLista(bean.getParamConsulta()));

        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }
    private void ver(GsMensajeBean bean) {
        try {
            bean.setObjeto(getGsMensajeRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    private void crear(GsMensajeBean bean) {
        try {
            bean.setObjeto(new GsMensaje());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void editar(GsMensajeBean bean) {
        try {
            bean.setObjeto(getGsMensajeRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GsMensajeBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getGsMensajeRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }    
   

    private void modificar(GsMensajeBean bean) {
        try {
            getGsMensajeRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }  
    private void borrar(GsMensajeBean bean) {
        try {
            bean.setObjeto(getGsMensajeRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }      
}
