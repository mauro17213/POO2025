/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.web.administracion.bean.GnAlertaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author sgiraldov
 */
public class GnAlertaServicioImpl extends AccionesBO implements GnAlertaServicioIface {
    
    
    private GnAlertaRemoto getGnAlertaRemoto() throws Exception {
        return (GnAlertaRemoto) RemotoEJB.getEJBRemoto("GnAlertaServicio", GnAlertaRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(GnAlertaBean bean) {
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
    public void cargasInicial(GnAlertaBean bean) {
        try {
            bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
        } catch (Exception e) {
        }
    }

    private void listar(GnAlertaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGnAlertaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGnAlertaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

    private void ver(GnAlertaBean bean) {
        try {
            bean.setObjeto(getGnAlertaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el ver, favor contactar al administrador");
        }
    }

    private void crear(GnAlertaBean bean) {
        try {
            bean.setObjeto(new GnAlerta());
            bean.getObjeto().setEstado(GnAlerta.ESTADO_GENERADO);
            bean.getObjeto().setGnUsuarioId(new Usuario());
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el crear, favor contactar al administrador");
        }
    }

    private void guardar(GnAlertaBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            getGnAlertaRemoto().insertar(bean.getObjeto());
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el guardar, favor contactar al administrador");
        }
    }

    private void borrar(GnAlertaBean bean) {
        try {
            bean.setObjeto(getGnAlertaRemoto().eliminar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el borrar, favor contactar al administrador");
        }
    }

    private void editar(GnAlertaBean bean) {
        try {
            bean.setObjeto(getGnAlertaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al editar, favor contactar al administrador");
        }
    }

    private void modificar(GnAlertaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getGnAlertaRemoto().actualizar(bean.getObjeto());
        } catch (Exception e) {
            bean.addError("Hubo un fallo al modificar, favor contactar al administrador");
        }
    }
    
}
