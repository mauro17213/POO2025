package com.saviasaludeps.savia.web.reserva.servicio;

import com.saviasaludeps.savia.dominio.reserva.RtReserva;
import com.saviasaludeps.savia.dominio.reserva.RtReservaArchivo;
import com.saviasaludeps.savia.negocio.reserva.RtReservaArchivoProcesoRemoto;
import com.saviasaludeps.savia.negocio.reserva.RtReservaArchivoRemoto;
import com.saviasaludeps.savia.negocio.reserva.RtReservaRemoto;

import com.saviasaludeps.savia.web.reserva.bean.ReservaTecnicaBean;
import com.saviasaludeps.savia.web.reserva.utilidades.RtConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author idbohorquez
 */
public class ReservaTecnicaServicioImpl extends AccionesBO implements ReservaTecnicaServicioIface {

    private RtReservaRemoto getRtReservaRemoto() throws Exception {
        return (RtReservaRemoto) RemotoEJB.getEJBRemoto("ReservaServicio", RtReservaRemoto.class.getName());
    }

    private RtReservaArchivoRemoto getRtReservaArchivoRemoto() throws Exception {
        return (RtReservaArchivoRemoto) RemotoEJB.getEJBRemoto("ReservaArchivoServicio", RtReservaArchivoRemoto.class.getName());
    }

    private RtReservaArchivoProcesoRemoto getRtReservaArchivoProcesoRemoto() throws Exception {
        return (RtReservaArchivoProcesoRemoto) RemotoEJB.getEJBRemoto("ReservaArchivoProcesoServicio", RtReservaArchivoProcesoRemoto.class.getName());
    }

    @Override
    public void Accion(ReservaTecnicaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            ver(bean);
                            break;
                        case Url.ACCION_VER:
                            verArchivoReserva(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            confirmarArchivo(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            rechazarArchivo(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            cambiarEstadoDescarga(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    autorizarReserva(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    rechazarReserva(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    cancelarArchivo(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    cancelarReserva(bean);
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verArchivoProceso(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    regenerarArchivo(bean);
                    break;
            }
        }
    }

    @Override
    public void cargasInicial(ReservaTecnicaBean bean) {
        bean.setListaEstadoReserva(RtConstantes.getListaEstadosReserva());
        bean.setHashListaEstadoReserva(RtConstantes.obtenerHashMaestro(bean.getListaEstadoReserva()));
    }

    private void listar(ReservaTecnicaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getRtReservaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getRtReservaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ReservaTecnicaBean bean) {
        try {
            bean.setObjeto(getRtReservaRemoto().consultar(bean.getObjeto().getId()));
            bean.getParamConsulta(0).getFiltros().put("idReserva", bean.getObjeto().getId().toString());
            bean.getParamConsulta(0).setCantidadRegistros(getRtReservaArchivoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setArchivoReservaList(getRtReservaArchivoRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception ex) {
            Logger.getLogger(ReservaTecnicaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.addError("Inconvenientes al consultar archivos de la reserva");
        }
    }

    private void verArchivoReserva(ReservaTecnicaBean bean) {
        try {
            bean.setObjetoArchivo(getRtReservaArchivoRemoto().consultar(bean.getObjetoArchivo().getId()));
        } catch (Exception ex) {
            Logger.getLogger(ReservaTecnicaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.addError("Inconvenientes al consultar información de archivo reserva");
        }
    }

    private void confirmarArchivo(ReservaTecnicaBean bean) {
        try {
            bean.setObjetoArchivo(getRtReservaArchivoRemoto().consultar(bean.getObjetoArchivo().getId()));
            bean.getObjetoArchivo().setEstado(RtConstantes.RT_ARCHIVO_GENERADO);
            bean.auditoriaModificar(bean.getObjetoArchivo());
            getRtReservaArchivoRemoto().cambiarEstadoArchivo(bean.getObjetoArchivo());
            bean.addMensaje("Registro confirmado");
        } catch (Exception e) {
            Logger.getLogger(ReservaTecnicaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
            bean.addError("Inconvenientes al confirmar archivo reserva");
        }
    }

    private void rechazarArchivo(ReservaTecnicaBean bean) {
        try {
            bean.setObjetoArchivo(getRtReservaArchivoRemoto().consultar(bean.getObjetoArchivo().getId()));
            bean.getObjetoArchivo().setEstado(RtConstantes.RT_ARCHIVO_RECHAZADO);
            bean.auditoriaModificar(bean.getObjetoArchivo());
            getRtReservaArchivoRemoto().cambiarEstadoArchivo(bean.getObjetoArchivo());
            bean.addMensaje("Registro rechazado");
        } catch (Exception e) {
            Logger.getLogger(ReservaTecnicaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
            bean.addError("Inconvenientes al confirmar archivo reserva");
        }
    }

    private void autorizarReserva(ReservaTecnicaBean bean) {
        try {
            bean.setObjeto(getRtReservaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(RtConstantes.AUTORIZADA);
            bean.auditoriaModificar(bean.getObjeto());
            getRtReservaRemoto().cambiarEstadoReserva(bean.getObjeto());
            bean.addMensaje("Registro autorizado");
        } catch (Exception e) {
            Logger.getLogger(ReservaTecnicaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
            bean.addError("Inconvenientes al autorizar reserva");
        }
    }

    private void rechazarReserva(ReservaTecnicaBean bean) {
        try {
            bean.getObjeto().setEstado(RtConstantes.RECHAZADA);
            bean.auditoriaModificar(bean.getObjeto());
            getRtReservaRemoto().cambiarEstadoReserva(bean.getObjeto());
            bean.addMensaje("Registro rechazado");
        } catch (Exception e) {
            Logger.getLogger(ReservaTecnicaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
            bean.addError("Inconvenientes al rechazar reserva");
        }
    }

    private void cancelarReserva(ReservaTecnicaBean bean) {
        try {
            RtReserva reserva = getRtReservaRemoto().consultar(bean.getObjeto().getId());
            if (reserva.getEstado() <= RtConstantes.GENERANDO) {
                bean.getObjeto().setEstado(RtConstantes.CANCELADA);
                bean.auditoriaModificar(bean.getObjeto());
                getRtReservaRemoto().cambiarEstadoReserva(bean.getObjeto());
                bean.addMensaje("Registro cancelado.");
            } else {
                bean.addError("No es posible cancelar la reserva debido a que no se encuentra en estado de generación.");
            }
        } catch (Exception ex) {
            bean.addError("Se presento inconveniente al cancelar la reserva, intentelo nuevamente.");
        }
    }

    private void verArchivoProceso(ReservaTecnicaBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getRtReservaArchivoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setObjetoArchivoProcesoList(getRtReservaArchivoProcesoRemoto().consultarLista(bean.getObjetoArchivo().getId()));
        } catch (Exception ex) {
            Logger.getLogger(ReservaTecnicaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.addError("Inconvenientes al consultar archivos de la reserva");
        }
    }

    private void cancelarArchivo(ReservaTecnicaBean bean) {
        try {
            RtReservaArchivo reserva = getRtReservaArchivoRemoto().consultar(bean.getObjetoArchivo().getId());
            if (reserva.getEstado() <= RtConstantes.GENERANDO) {
                bean.getObjetoArchivo().setEstado(RtConstantes.RT_ARCHIVO_FALLIDO);
                bean.auditoriaModificar(bean.getObjeto());
                getRtReservaArchivoRemoto().cambiarEstadoArchivo(bean.getObjetoArchivo());
                bean.addMensaje("Registro cancelado.");
            } else {
                bean.addError("No es posible cancelar la reserva debido a que no se encuentra en estado de generación.");
            }
        } catch (Exception ex) {
            bean.addError("Se presento inconveniente al cancelar la reserva, intentelo nuevamente.");
        }
    }

    private void regenerarArchivo(ReservaTecnicaBean bean) {
        try {
            RtReservaArchivo reserva = getRtReservaArchivoRemoto().consultar(bean.getObjetoArchivo().getId());
            if (reserva.getEstado() == RtConstantes.RT_ARCHIVO_FALLIDO) {
                bean.getObjetoArchivo().setEstado(RtConstantes.EN_COLA);
                bean.auditoriaModificar(bean.getObjeto());
                getRtReservaArchivoRemoto().cambiarEstadoArchivo(bean.getObjetoArchivo());
                bean.addMensaje("Registro regenerado.");
            } else {
                bean.addError("No es posible regenerar la reserva debido a que no se encuentra en estado fallido o rechazado.");
            }
        } catch (Exception ex) {
            bean.addError("Se presento inconveniente al regenerar la reserva, intentelo nuevamente.");
        }
    }

    private void cambiarEstadoDescarga(ReservaTecnicaBean bean) {
        try {
            bean.setObjetoArchivo(getRtReservaArchivoRemoto().consultar(bean.getObjetoArchivo().getId()));   
            bean.getObjetoArchivo().setDescargado(true);
            bean.auditoriaModificar(bean.getObjetoArchivo());            
            getRtReservaArchivoRemoto().cambiarEstadoArchivo(bean.getObjetoArchivo());

        } catch (Exception ex) {
            bean.addError("Se presento inconveniente al regenerar la reserva, intentelo nuevamente.");
        }
    }

}
