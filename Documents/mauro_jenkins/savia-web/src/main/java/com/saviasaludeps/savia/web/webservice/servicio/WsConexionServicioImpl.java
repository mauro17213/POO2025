/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.servicio;

import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsConexionServicio;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.negocio.webservice.WsServicioRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.webservice.bean.WsConexionBean;
import com.saviasaludeps.savia.negocio.webservice.WsConexionRemoto;
import com.saviasaludeps.savia.negocio.webservice.WsConexionesServiciosRemoto;
import com.saviasaludeps.savia.negocio.webservice.WsTokenRemoto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public class WsConexionServicioImpl extends AccionesBO implements WsConexionServicioIface {

    private WsConexionRemoto getWSConexionRemoto() throws Exception {
        return (WsConexionRemoto) RemotoEJB.getEJBRemoto("WsConexionServicio", WsConexionRemoto.class.getName());
    }

    private WsServicioRemoto getWsServicioRemoto() throws Exception {
        return (WsServicioRemoto) RemotoEJB.getEJBRemoto("WsServicioServicio", WsServicioRemoto.class.getName());
    }

    private WsConexionesServiciosRemoto getWsConexionesServiciosRemoto() throws Exception {
        return (WsConexionesServiciosRemoto) RemotoEJB.getEJBRemoto("WsConexionesServiciosServicio", WsConexionesServiciosRemoto.class.getName());
    }

    private WsTokenRemoto getWsTokenRemoto() throws Exception {
        return (WsTokenRemoto) RemotoEJB.getEJBRemoto("WsTokenServicio", WsTokenRemoto.class.getName());
    }

    @Override
    public void Accion(WsConexionBean bean) {
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
                    verToken(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(WsConexionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getWSConexionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getWSConexionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(WsConexionBean bean) {
        try {
            WsConexion objeto = getWSConexionRemoto().consultar(bean.getObjeto().getId());
            objeto.setListaWsServicios(getWsServicioRemoto().consultarListaPorConexion(bean.getObjeto().getId()));
            bean.setObjeto(objeto);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(WsConexionBean bean) {
        try {
            bean.setObjeto(new WsConexion());
            bean.getObjeto().setFechaHoraCrea(new Date());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(WsConexionBean bean) {
        try {
            WsConexion objeto = getWSConexionRemoto().consultar(bean.getObjeto().getId());
            objeto.setListaWsServicios(getWsServicioRemoto().consultarListaPorConexion(bean.getObjeto().getId()));
            List<WsServicio> servicios = getWsServicioRemoto().consultarListaPorConexion(bean.getObjeto().getId());
            objeto.getListaWsServicios().clear();
            for (WsServicio servicio : servicios) {
                for (WsServicio serv : bean.getListaWsServicios()) {
                    if (servicio.getId().intValue() == serv.getId().intValue()) {
                        objeto.getListaWsServicios().add(serv);
                    }
                }
            }
            bean.setObjeto(objeto);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(WsConexionBean bean) {
        try {
            if (bean.getObjeto().getContrasena().length() < 8) {
                bean.addError("La contraseña debe mayor a 8 caracteres.");
                return;
            }
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getWSConexionRemoto().insertar(bean.getObjeto()));
            //Guardar wsConexionesServicios
            WsConexionServicio conexionServicio = new WsConexionServicio();
            if (bean.getObjeto() != null && bean.getObjeto().getId() != null && bean.getObjeto().getId() != 0) {
                for (WsServicio servicio : bean.getObjeto().getListaWsServicios()) {
                    conexionServicio.setWsConexion(new WsConexion(bean.getObjeto().getId()));
                    conexionServicio.setWsServicio(new WsServicio(servicio.getId()));
                    getWsConexionesServiciosRemoto().insertar(conexionServicio);
                    conexionServicio = new WsConexionServicio();
                }
            }

            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(WsConexionBean bean) {
        try {
            if (bean.getObjeto().getContrasenaNueva() != null && !bean.getObjeto().getContrasenaNueva().isEmpty()) {
                if (bean.getObjeto().getContrasena().length() < 8) {
                    bean.addError("La contraseña debe mayor a 8 caracteres.");
                    return;
                }
            }
            bean.auditoriaModificar(bean.getObjeto());
            getWSConexionRemoto().actualizar(bean.getObjeto());
            List<WsConexionServicio> conexionServicios = getWsConexionesServiciosRemoto().getWsConexionServicio(bean.getObjeto().getId());
            for (WsConexionServicio conexionServicio : conexionServicios) {
                boolean eliminar = true;
                for (WsServicio serv : bean.getObjeto().getListaWsServicios()) {
                    if (conexionServicio.getWsServicio().getId().intValue() == serv.getId().intValue()) {
                        eliminar = false;
                    }
                }
                if (eliminar) {
                    getWsConexionesServiciosRemoto().eliminar(conexionServicio.getId());
                }
            }
            for (WsServicio serv : bean.getObjeto().getListaWsServicios()) {
                boolean guardar = true;
                for (WsConexionServicio conexionServicio : conexionServicios) {
                    if (serv.getId().intValue() == conexionServicio.getWsServicio().getId().intValue()) {
                        guardar = false;
                    }
                }
                if (guardar) {
                    WsConexionServicio conexionServ = new WsConexionServicio();
                    conexionServ.setWsConexion(new WsConexion(bean.getObjeto().getId()));
                    conexionServ.setWsServicio(new WsServicio(serv.getId()));
                    getWsConexionesServiciosRemoto().insertar(conexionServ);
                }
            }
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(WsConexionBean bean) {
        try {
            List<WsConexionServicio> conexionServicios = getWsConexionesServiciosRemoto().getWsConexionServicio(bean.getObjeto().getId());

            for (WsConexionServicio per : conexionServicios) {
                getWsConexionesServiciosRemoto().eliminar(per.getId());
            }

            bean.setObjeto(getWSConexionRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verToken(WsConexionBean bean) {
        try {
            bean.setListWsTokens(getWsTokenRemoto().consultarByWsConexiones(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(WsConexionBean bean) {
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
    public void cargaInicial(WsConexionBean bean) {
        try {
            bean.setListaWsServicios(getWsServicioRemoto().consultarTodas());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
