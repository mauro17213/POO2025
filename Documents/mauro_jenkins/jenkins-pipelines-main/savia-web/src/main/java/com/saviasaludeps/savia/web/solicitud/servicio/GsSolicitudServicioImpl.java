/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.solicitud.GsAsignacionUsuario;
import com.saviasaludeps.savia.dominio.solicitud.GsGestion;
import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.dominio.solicitud.GsNotificacion;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsGestionRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsNotificacionRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsSolicitudRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsZonaRemoto;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.solicitud.bean.GsSolicitudBean;
import com.saviasaludeps.savia.web.solicitud.mensajes.correo.CorreoSolicitudesWeb;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jramirez
 */
public class GsSolicitudServicioImpl extends AccionesBO implements GsSolicitudServicioIface {
    
     private GsSolicitudRemoto getGsSolicitudRemoto() throws Exception {
        return (GsSolicitudRemoto) RemotoEJB.getEJBRemoto("GsSolicitudServicio", GsSolicitudRemoto.class.getName());
    }

    private GsNotificacionRemoto getGsNotificacionRemoto() throws Exception {
        return (GsNotificacionRemoto) RemotoEJB.getEJBRemoto("GsNotificacionServicio", GsNotificacionRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private GsZonaRemoto getGsZonaRemoto() throws Exception {
        return (GsZonaRemoto) RemotoEJB.getEJBRemoto("GsZonaServicio", GsZonaRemoto.class.getName());
    }

    private GsAdjuntoRemoto getGsAdjuntoRemoto() throws Exception {
        return (GsAdjuntoRemoto) RemotoEJB.getEJBRemoto("GsAdjuntoServicio", GsAdjuntoRemoto.class.getName());
    }

    private GsGestionRemoto getGsGestionRemoto() throws Exception {
        return (GsGestionRemoto) RemotoEJB.getEJBRemoto("GsGestionServicio", GsGestionRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(GsSolicitudBean bean) {
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
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            gestionListar(bean);
                            break;
                        case Url.ACCION_CREAR:
                            gestionCrear(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            gestionGuardar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            reasignacionCrear(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            reasignacionGuardar(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(GsSolicitudBean bean) {
        try {
            if (!bean.isAccionAdicional1()) {
                bean.getParamConsulta().setParametroConsulta3(bean.getConexion().getUsuario().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getGsSolicitudRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGsSolicitudRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(GsSolicitudBean bean) {
        try {
            GsSolicitud solicitud = getGsSolicitudRemoto().consultar(bean.getObjeto().getId());
            solicitud.setListaGsAdjuntos(getGsAdjuntoRemoto().consultarPorSolicitud(solicitud.getId()));
            solicitud.setListaGsGestiones(getGsGestionRemoto().consultarPorSolicitud(solicitud.getId()));
            bean.setObjeto(solicitud);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(GsSolicitudBean bean) {
        try {
            bean.setObjeto(new GsSolicitud());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(GsSolicitudBean bean) {
        try {
            bean.setObjeto(getGsSolicitudRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GsSolicitudBean bean) {
        //2020-03-25 jyperez
        try {
//            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getGsSolicitudRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(GsSolicitudBean bean) {
        try {
//            bean.auditoriaModificar(bean.getObjeto());
            getGsSolicitudRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(GsSolicitudBean bean) {
        try {
            bean.setObjeto(getGsSolicitudRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void gestionListar(GsSolicitudBean bean) {
        try {
            GsSolicitud solicitud = getGsSolicitudRemoto().consultar(bean.getObjeto().getId());
            if (solicitud.getEstado() == GsSolicitud.ESTADO_REGISTRADO) {
                solicitud.setFechaHoraAtiende(new Date());
                solicitud.setUsuarioAtiende(bean.getConexion().getUsuario().getUsuarioNombre());
                solicitud.setEstado(GsSolicitud.ESTADO_GESTION);
                solicitud.setCambioEstado(true);
                getGsSolicitudRemoto().actualizarEstado(solicitud);
                //Creación de Gestion
                GsGestion gestion = new GsGestion();
                bean.auditoriaGuardar(gestion);
                gestion.setUsuario(bean.getConexion().getUsuario());
                gestion.setGsSolicitud(new GsSolicitud(solicitud.getId()));
                gestion.setEstado(GsGestion.ESTADO_GESTION);
                gestion.setDescripcion("Inicio de atención por parte del usuario '" + bean.getConexion().getUsuario().getUsuarioNombre() + "'");
                getGsGestionRemoto().insertar(gestion);
            }
            solicitud.setListaGsAdjuntos(getGsAdjuntoRemoto().consultarPorSolicitud(solicitud.getId()));
            solicitud.setListaGsGestiones(getGsGestionRemoto().consultarPorSolicitud(solicitud.getId()));
            bean.setObjeto(solicitud);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void gestionCrear(GsSolicitudBean bean) {
        try {
//            bean.setObjeto(getGsSolicitudRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getEstado() == GsSolicitud.ESTADO_RESUELTO
                    || bean.getObjeto().getEstado() == GsSolicitud.ESTADO_NO_TRAMITADO) {
                bean.addError("La solicitud ya finalizo su gestión");
            }
            bean.setGsGestion(new GsGestion());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void gestionGuardar(GsSolicitudBean bean) {
        try {
            bean.auditoriaGuardar(bean.getGsGestion());
            bean.getGsGestion().setUsuario(bean.getConexion().getUsuario());
            bean.getGsGestion().setGsSolicitud(new GsSolicitud(bean.getObjeto().getId()));
            bean.getGsGestion().setId(getGsGestionRemoto().insertar(bean.getGsGestion()));
            if (bean.getGsGestion().getEstado() == GsGestion.ESTADO_RESUELTO
                    || bean.getGsGestion().getEstado() == GsGestion.ESTADO_NO_TRAMITADO) {
                GsSolicitud solicitud = bean.getObjeto();
                solicitud.setFechaHoraFinaliza(new Date());
                solicitud.setUsuarioFinaliza(bean.getConexion().getUsuario().getUsuarioNombre());
                solicitud.setEstado(bean.getGsGestion().getEstado());
                solicitud.setCambioEstado(true);
                solicitud.setDescripcion(bean.getGsMensaje().getMensajeLargo());
                solicitud.setGsMensaje(new GsMensaje(bean.getGsMensaje().getId()));
                getGsSolicitudRemoto().actualizarEstado(solicitud);
                //Crear notificacion Correo
                if (solicitud.getNotificacion() == GsSolicitud.TIPO_NOTIFICACION_EMAIL
                        || solicitud.getNotificacion() == GsSolicitud.TIPO_NOTIFICACION_AMBOS) {
                    GsNotificacion notificacion = new GsNotificacion();
                    notificacion.setTipo(GsNotificacion.TIPO_CORREO);
                    notificacion.setCorreo(solicitud.getContactoCorreoElectronico());
                    notificacion.setEstado(GsNotificacion.ESTADO_CREADO);
                    notificacion.setGsSolicitudesId(new GsSolicitud(solicitud.getId()));
                    notificacion.setEncabezado(bean.getGsMensaje().getEncabezado().replaceAll("xxx", String.valueOf(solicitud.getId())));
                    notificacion.setDetalle(bean.getGsMensaje().getMensajeLargo().replaceAll("xxx", String.valueOf(solicitud.getId())));
                    notificacion.setFechaHoraCrea(new Date());
                    notificacion.setId(getGsNotificacionRemoto().insertar(notificacion));
                    //Envío
                    new Thread(new CorreoSolicitudesWeb(notificacion)).start();
                }
//                //Crear notificacion SMS
//                if (solicitud.getNotificacion() == GsSolicitud.TIPO_NOTIFICACION_SMS
//                        || solicitud.getNotificacion() == GsSolicitud.TIPO_NOTIFICACION_AMBOS) {
//                    GsNotificacion notificacion = new GsNotificacion();
//                    notificacion.setTipo(GsNotificacion.TIPO_SMS);
//                    notificacion.setCelular(
//                            solicitud.getContactoCelular()
//                                    .replace("(", "")
//                                    .replace(")", "")
//                                    .replace("-", "")
//                                    .replace(" ", "")
//                    );
//                    notificacion.setEstado(GsNotificacion.ESTADO_CREADO);
//                    notificacion.setGsSolicitudesId(new GsSolicitud(solicitud.getId()));
//                    notificacion.setDetalle(bean.getGsMensaje().getMensajeCorto().replaceAll("xxx", String.valueOf(solicitud.getId())));
//                    notificacion.setFechaHoraCrea(new Date());
//                    notificacion.setId(getGsNotificacionRemoto().insertar(notificacion));
//                    //Envío
//                    new Thread(new SMSSolicitudesWeb(notificacion)).start();
//                }
            }
            bean.addMensaje("Se creo un registro de manera exitosa");
            //Cargar nuevamente la gestión
            GsSolicitud solicitud = getGsSolicitudRemoto().consultar(bean.getObjeto().getId());
            solicitud.setListaGsAdjuntos(getGsAdjuntoRemoto().consultarPorSolicitud(solicitud.getId()));
            solicitud.setListaGsGestiones(getGsGestionRemoto().consultarPorSolicitud(solicitud.getId()));
            solicitud.setCambioEstado(bean.getObjeto().isCambioEstado());
            bean.setObjeto(solicitud);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void reasignacionCrear(GsSolicitudBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void reasignacionGuardar(GsSolicitudBean bean) {
        try {
            if (bean.getObjeto().getEstado() == GsSolicitud.ESTADO_RESUELTO) {
                bean.addError("Esta solicitud esta en RESUELTO, por lo cual no se puede reasignar");
            } else if (bean.getGsGestion().getEstado() == GsGestion.ESTADO_NO_TRAMITADO) {
                bean.addError("Esta solicitud esta en NO TRAMITADO, por lo cual no se puede reasignar");
            } else {
                //Consultar nueva asignación
                GsZona zona = getGsZonaRemoto().consultar(bean.getObjeto().getGsZona().getId());
                try {
                    GsAsignacionUsuario asignacion = getGsSolicitudRemoto().proximaAsignacion(bean.getObjeto().getTipo(), zona.getUbicacion().getId());
                    bean.getObjeto().setUsuario(new Usuario(asignacion.getUsuarioId()));
                    bean.getObjeto().setGsZona(new GsZona(asignacion.getZonasId()));
                    bean.getObjeto().setUsuarioReasigna(bean.getConexion().getUsuario().getUsuarioNombre());
                    bean.getObjeto().setFechaHoraReasigna(new Date());
                    //Guardar cambio
                    getGsSolicitudRemoto().actualizarEstado(bean.getObjeto());
                    GsGestion gestion = new GsGestion();
                    bean.auditoriaGuardar(gestion);
                    gestion.setEstado(GsGestion.ESTADO_REASIGNADO);
                    gestion.setUsuario(bean.getConexion().getUsuario());
                    gestion.setGsSolicitud(new GsSolicitud(bean.getObjeto().getId()));
                    GsZona zonaAsignada = getGsZonaRemoto().consultar(asignacion.getZonasId());
                    Usuario usuarioAsignado = getUsuarioRemoto().consultar(asignacion.getUsuarioId());
                    gestion.setDescripcion("Reasignación de caso al usuario '" + usuarioAsignado.getUsuarioNombre() + "' de la zona '" + zonaAsignada.getNombre() + "'");
                    getGsGestionRemoto().insertar(gestion);
                    bean.addMensaje("Caso reasignado exitosamente al usuario '" + usuarioAsignado.getUsuarioNombre() + "' de la zona '" + zonaAsignada.getNombre() + "'");
                } catch (Exception e) {
                    bean.addError(e.getMessage());
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(GsSolicitudBean bean) {
        try {

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void cargaInial(GsSolicitudBean bean) {
        try {
            
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setListaZonas(getGsZonaRemoto().consultarTodas());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public GsSolicitud consultarSolicitud(int id) {
        GsSolicitud solicitud;
        try {
            solicitud = getGsSolicitudRemoto().consultar(id);
        } catch (Exception ex) {
            solicitud = null;
        }
        return solicitud;
    }

    @Override
    public List<GsMensaje> consultarMenajesPorTipoEstado(int tipo, int estado) {
        List<GsMensaje> listaMensajes;
        try {
            listaMensajes = getGsSolicitudRemoto().consultarMensajesPorTipoEstado(tipo, estado);
        } catch (Exception ex) {
            listaMensajes = new ArrayList();
        }
        return listaMensajes;
    }
}
