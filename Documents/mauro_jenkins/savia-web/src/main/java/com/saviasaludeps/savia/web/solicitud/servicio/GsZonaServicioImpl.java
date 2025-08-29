/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.servicio;

import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.dominio.solicitud.GsZonaUsuario;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsZonaRemoto;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.solicitud.bean.GsZonaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;

/**
 *
 * @author jramirez
 */
public class GsZonaServicioImpl extends AccionesBO implements GsZonaServicioIface{
     private GsZonaRemoto getGsZonaRemoto() throws Exception {
        return (GsZonaRemoto) RemotoEJB.getEJBRemoto("GsZonaServicio", GsZonaRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(GsZonaBean bean) {
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
                    usuarios(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(GsZonaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGsZonaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGsZonaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(GsZonaBean bean) {
        try {
            bean.setObjeto(getGsZonaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(GsZonaBean bean) {
        try {
            bean.setObjeto(new GsZona());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(GsZonaBean bean) {
        try {
            bean.setObjeto(getGsZonaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GsZonaBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getGsZonaRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(GsZonaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getGsZonaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(GsZonaBean bean) {
        try {
            bean.setObjeto(getGsZonaRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void usuarios(GsZonaBean bean) {
        try {
            switch (bean.getDoAccion()) {
                case Url.ACCION_LISTAR:
                    try {
                        GsZona gsZona = getGsZonaRemoto().consultar(bean.getObjeto().getId());
                        gsZona.setListaGsZonaUsuarios(getGsZonaRemoto().consultarUsuarios(bean.getObjeto().getId()));
                        bean.setObjeto(gsZona);
                    } catch (Exception e) {
                        bean.addError(e.getMessage());
                    }
                    break;
                case Url.ACCION_CREAR:
                    GsZonaUsuario gsZonaUsuario = new GsZonaUsuario();
                    gsZonaUsuario.setGsZona(bean.getObjeto());
                    gsZonaUsuario.setActivo(true);
                    gsZonaUsuario.setGestiones("1100000000000000");
                    bean.setZonaUsuario(gsZonaUsuario);
                    break;
                case Url.ACCION_GUARDAR:
                    try {
                        bean.auditoriaGuardar(bean.getZonaUsuario());
                        bean.getZonaUsuario().setId(getGsZonaRemoto().insertarUsuario(bean.getZonaUsuario()));
                        bean.addMensaje("Se creo un registro de manera exitosa");
                        bean.getObjeto().setListaGsZonaUsuarios(getGsZonaRemoto().consultarUsuarios(bean.getObjeto().getId()));
                    } catch (Exception e) {
                        bean.addError(e.getMessage());
                    }
                    break;
                case Url.ACCION_EDITAR:
                    try {
                        bean.setZonaUsuario(getGsZonaRemoto().consultarUsuario(bean.getZonaUsuario().getId()));
                    } catch (Exception e) {
                        bean.addError(e.getMessage());
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    bean.auditoriaModificar(bean.getZonaUsuario());
                    getGsZonaRemoto().actualizarUsuario(bean.getZonaUsuario());
                    bean.addMensaje("Se actualizó el registro de manera exitosa");
                    bean.getObjeto().setListaGsZonaUsuarios(getGsZonaRemoto().consultarUsuarios(bean.getObjeto().getId()));
                    break;
                case Url.ACCION_BORRAR:
                    try {
                        bean.setZonaUsuario(getGsZonaRemoto().eliminarUsuario(bean.getZonaUsuario().getId()));
                        bean.getObjeto().setListaGsZonaUsuarios(getGsZonaRemoto().consultarUsuarios(bean.getObjeto().getId()));
                        bean.addMensaje("Se eliminó un registro de manera exitosa");
                    } catch (Exception e) {
                        bean.addError(e.getMessage());
                    }
                    break;
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public GsZona consultar(int id) {
        GsZona gsZona = null;
        try {
            gsZona = getGsZonaRemoto().consultar(id);
        } catch (Exception e) {

        }
        return gsZona;
    }

    private void cargas(GsZonaBean bean) {
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

        }
    }

    @Override
    public void cargaInicial(GsZonaBean bean) {
        try {
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
        } catch (Exception ex) {
            System.out.println("error" + ex);
        }
    }

    @Override
    public void consultaUsuariosCarga(GsZonaBean bean, String query) {
        try {
            bean.setUsuarios(getUsuarioRemoto().consultarSegmentado(bean.getConexion().getEmpresa().getId(), 2, 4, query));
        } catch (Exception ex) {
            bean.setUsuarios(new ArrayList());
        }
    }
}
