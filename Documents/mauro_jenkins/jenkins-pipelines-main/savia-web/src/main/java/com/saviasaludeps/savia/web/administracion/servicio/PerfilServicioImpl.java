/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.PerfilBean;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.administracion.Permiso;
import com.saviasaludeps.savia.negocio.administracion.PerfilRemoto;
import com.saviasaludeps.savia.negocio.administracion.PermisoRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public class PerfilServicioImpl extends AccionesBO implements PerfilServicioIface {

    private PerfilRemoto getPerfilRemoto() throws Exception {
        return (PerfilRemoto) RemotoEJB.getEJBRemoto("PerfilServicio", PerfilRemoto.class.getName());
    }

    private PermisoRemoto getPermisoRemoto() throws Exception {
        return (PermisoRemoto) RemotoEJB.getEJBRemoto("PermisoServicio", PermisoRemoto.class.getName());
    }

    @Override
    public void Accion(PerfilBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editarPermisos(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarPermisos(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    public void listar(PerfilBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPerfilRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPerfilRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(PerfilBean bean) {
        try {
            bean.setObjeto(getPerfilRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(PerfilBean bean) {
        try {
            bean.setObjeto(new Perfil());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(PerfilBean bean) {
        try {
            bean.setObjeto(getPerfilRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(PerfilBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getPerfilRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(PerfilBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getPerfilRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(PerfilBean bean) {
        try {
            bean.setObjeto(getPerfilRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<Perfil> consultarLista() {
        List<Perfil> _listaPerfiles;
        try {
            _listaPerfiles = getPerfilRemoto().consultarTodos();
        } catch (Exception ex) {
            _listaPerfiles = new ArrayList();
        }
        return _listaPerfiles;
    }

    private void editarPermisos(PerfilBean bean) {
        try {
            bean.setObjeto(getPerfilRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaPermisos(getPermisoRemoto().consultarPermisosModulos(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarPermisos(PerfilBean bean) {
        try {
            Permiso per = getPermisoRemoto().consultar(bean.getObjetoPermiso().getModulo().getId(), bean.getObjetoPermiso().getPerfil().getId());
            if (per == null) {
                getPermisoRemoto().insertar(bean.getObjetoPermiso());
            } else {
                getPermisoRemoto().actualizar(bean.getObjetoPermiso());
            }
            bean.addMensaje("Perfiles modificados de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
