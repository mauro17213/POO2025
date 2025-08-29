/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnMensaje;
import com.saviasaludeps.savia.negocio.administracion.GnMensajeRemoto;
import com.saviasaludeps.savia.web.administracion.bean.GnMensajeBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author sgiraldov
 */
public class GnMensajeServicioImpl extends AccionesBO implements GnMensajeServicioIface {
    
    private GnMensajeRemoto getGnMensajeRemoto() throws Exception {
        return (GnMensajeRemoto) RemotoEJB.getEJBRemoto("GnMensajeServicio", GnMensajeRemoto.class.getName());
    }

    @Override
    public void Accion(GnMensajeBean bean) {
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
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;                
            }
        }
    }

    @Override
    public void cargasInicial(GnMensajeBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError("Hubo un error en la carga inicial, favor contactar con el adminitrador");
        }
    }

    private void listar(GnMensajeBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGnMensajeRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGnMensajeRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("Hubo un error al listar, favor contactar con el adminitrador");
        }
    }

    private void ver(GnMensajeBean bean) {
        try {
            bean.setObjeto(getGnMensajeRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al ver, favor contacatar con el adminitrador");
        }
    }

    private void crear(GnMensajeBean bean) {
        try {
            bean.setObjeto(new GnMensaje());
        } catch (Exception e) {
            bean.addError("Hubo un error al crear favor contactar al adminitrador");
        }
    }

    private void guardar(GnMensajeBean bean) {
        try {
            bean.getObjeto().setContenido(bean.getObjeto().getTexto().getBytes());
            bean.auditoriaGuardar(bean.getObjeto());
            getGnMensajeRemoto().insertar(bean.getObjeto());
        } catch (Exception e) {
            bean.addError("Hubo un error al guardar, favor contactar con el administrador");
        }
    }

    private void borrar(GnMensajeBean bean) {
        try {
            getGnMensajeRemoto().eliminar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Hubo un error al borrar, favor contactar con el administrador");
        }

    }

    private void editar(GnMensajeBean bean) {
        try {
            bean.setObjeto(getGnMensajeRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al editar, favor contactar con el administrador");
        }
    }

    private void modificar(GnMensajeBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setContenido(bean.getObjeto().getTexto().getBytes());
            getGnMensajeRemoto().actualizar(bean.getObjeto());
        } catch (Exception e) {
            bean.addError("Hubo un error al modificar, favor contactar al adminitrador");
        }
    }
    
}
