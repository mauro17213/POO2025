/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionComentario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionHistorico;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatServicioUmbral;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaServicio;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiempo;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiketeLlamado;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatUsuario;
import com.saviasaludeps.savia.negocio.administracion.GnSedeHorarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnSedeRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatAtencionComentarioRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatAtencionHistoricoRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatAtencionRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeConfiguracionRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeFuncionarioRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeTaquillaRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatServicioUmbralRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTaquillaServicioRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiempoRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiketeLlamadoRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiqueteRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatUsuarioRemoto;
import com.saviasaludeps.savia.web.gestion.atencion.bean.GatAtencionBean;
import com.saviasaludeps.savia.web.gestion.atencion.utilidades.GatContantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class GatAtencionServicioImpl extends AccionesBO implements GatAtencionServicioIface {

    private GatAtencionRemoto getGatAtencionRemoto() throws Exception {
        return (GatAtencionRemoto) RemotoEJB.getEJBRemoto("GatAtencionServicio", GatAtencionRemoto.class.getName());
    }

    private GatTiqueteRemoto getGatTiqueteRemoto() throws Exception {
        return (GatTiqueteRemoto) RemotoEJB.getEJBRemoto("GatTiqueteServicio", GatTiqueteRemoto.class.getName());
    }

    private GatServicioUmbralRemoto getGatServicioUmbralRemoto() throws Exception {
        return (GatServicioUmbralRemoto) RemotoEJB.getEJBRemoto("GatServicioUmbralServicio", GatServicioUmbralRemoto.class.getName());
    }

    private GatSedeTaquillaRemoto getGatSedeTaquillaRemoto() throws Exception {
        return (GatSedeTaquillaRemoto) RemotoEJB.getEJBRemoto("GatSedeTaquillaServicio", GatSedeTaquillaRemoto.class.getName());
    }

    private GatTaquillaServicioRemoto getGatTaquillaServicioRemoto() throws Exception {
        return (GatTaquillaServicioRemoto) RemotoEJB.getEJBRemoto("GatTaquillaServicioServicio", GatTaquillaServicioRemoto.class.getName());
    }

    private GnSedeRemoto getGnSedeRemoto() throws Exception {
        return (GnSedeRemoto) RemotoEJB.getEJBRemoto("GnSedeServicio", GnSedeRemoto.class.getName());
    }

    private GatAtencionHistoricoRemoto getGatAtencionHistoricoRemoto() throws Exception {
        return (GatAtencionHistoricoRemoto) RemotoEJB.getEJBRemoto("GatAtencionHistoricoServicio", GatAtencionHistoricoRemoto.class.getName());
    }

    private GatAtencionComentarioRemoto getGatAtencionComentarioRemoto() throws Exception {
        return (GatAtencionComentarioRemoto) RemotoEJB.getEJBRemoto("GatAtencionComentarioServicio", GatAtencionComentarioRemoto.class.getName());
    }

    private GatTiketeLlamadoRemoto getGatTiketeLlamadoRemoto() throws Exception {
        return (GatTiketeLlamadoRemoto) RemotoEJB.getEJBRemoto("GatTiketeLlamadoServicio", GatTiketeLlamadoRemoto.class.getName());
    }

    private GatSedeFuncionarioRemoto getGatSedeFuncionarioRemoto() throws Exception {
        return (GatSedeFuncionarioRemoto) RemotoEJB.getEJBRemoto("GatSedeFuncionarioServicio", GatSedeFuncionarioRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private GatSedeConfiguracionRemoto getGatSedeConfiguracion() throws Exception {
        return (GatSedeConfiguracionRemoto) RemotoEJB.getEJBRemoto("GatSedeConfiguracionServicio", GatSedeConfiguracionRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private GatUsuarioRemoto getGatUsuarioRemoto() throws Exception {
        return (GatUsuarioRemoto) RemotoEJB.getEJBRemoto("GatUsuarioServicio", GatUsuarioRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private GnSedeHorarioRemoto getGnSedeHorarioRemoto() throws Exception {
        return (GnSedeHorarioRemoto) RemotoEJB.getEJBRemoto("GnSedeHorarioServicio", GnSedeHorarioRemoto.class.getName());
    }

    private GatTiempoRemoto getGatTiempoRemoto() throws Exception {
        return (GatTiempoRemoto) RemotoEJB.getEJBRemoto("GatTiempoServicio", GatTiempoRemoto.class.getName());
    }

    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void Accion(GatAtencionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {//Listar registros
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listar(bean);
                            break;
                        //Seleccion de taquilla
//                        case Url.ACCION_CREAR:
//                            actualizarTaquilla(bean);
//                            break;
                        case Url.ACCION_GUARDAR:
                            taquillaGuardar(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            taquillaSalir(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    turnoCrear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    turnoGuardar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    turnoSolicitar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1: //Gestionar
                    switch (bean.getDoAccion()) {
                        //Gestionar turno
                        case Url.ACCION_EDITAR:
                            gestionEditar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            gestionModificar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2: //Rellamar
                    rellamar(bean);
                    break;
                case Url.ACCION_ADICIONAL_3: //Transferir
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            transferenciaCrear(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            transferenciaGuardar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4: //Abandonar
                    abandonar(bean);
                    break;
                case Url.ACCION_ADICIONAL_5: //Llamar
                    llamar(bean);
                    break;
                case Url.ACCION_ADICIONAL_6: //Reposo
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            reposoCrear(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            reposoGuardar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            reposoModificar(bean);
                            break;
                    }
                    break;
            }
        }
    }

    private void listar(GatAtencionBean bean) {
        try {
            bean.setListaAtenciones(getGatAtencionRemoto().listarActivasPorFuncionarioYTaquilla(bean.getObjetoFuncionario().getId(), bean.getObjetoTaquilla().getId()));
            bean.getObjetoTaquilla().setGatTaquillaServicioList(getGatTaquillaServicioRemoto().listarPorIdTaquilla(bean.getObjetoTaquilla().getId()));
            for (GatAtencion atencion : bean.getListaAtenciones()) {
                GatTiketeLlamado llamado = getGatTiketeLlamadoRemoto().tieneLlamado(atencion.getGatTiquete().getId());
                if (llamado.getCantidad() != null) {
                    if (llamado.getEstado() == GatTiketeLlamado.ESTADO_LLAMADO) {
                        atencion.setEnLlamado(true);
                    } else {
                        atencion.setEnLlamado(false);
                    }
                    if (llamado.getCantidad() == 0) {
                        atencion.setLlamados(1);
                    } else {
                        atencion.setLlamados(llamado.getCantidad());
                    }
                } else {
                    atencion.setEnLlamado(false);
                    atencion.setLlamados(0);
                }
                atencion.setFueraTiempo(validarTiempo(bean, atencion));
//                if (atencion.isFueraTiempo()) {
//                    bean.addMensaje("El turno " + atencion.getGatTiquete().getNumero() + " ha superado el tiempo de atención.");
//                }
                GatServicioUmbral ga = bean.getServicio(atencion.getGatTiquete().getMaeTipoServicio());
                if (ga != null) {
                    atencion.setTiempoServicio(ga.getTiempo());
                }
            }
            bean.setTotalReposos(getGatTiempoRemoto().cantidadReposos(formateador.format(new Date()), bean.getConexion().getUsuario().getId()));
            bean.setTotalAbandonados(getGatAtencionRemoto().consultarTipoFecha(GatContantes.ESTADO_ABANDONO, formateador.format(new Date()), bean.getObjetoFuncionario().getId()));
            bean.setTotalAtendidos(getGatAtencionRemoto().consultarTipoFecha(GatContantes.ESTADO_FINALIZADO, formateador.format(new Date()), bean.getObjetoFuncionario().getId()));
            bean.setTotalSobreAtendidos(getGatAtencionRemoto().consultarSobreAtendidos(formateador.format(new Date()), bean.getObjetoFuncionario().getId()));
            bean.setTotalEspera(getGatTiqueteRemoto().consultarTotalPendiente(new Date(), bean.getObjetoSede().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

    public void taquillaGuardar(GatAtencionBean bean) {
        try {
            if (getGatSedeTaquillaRemoto().estaLibre(bean.getObjetoTaquilla().getId())) {
                getGatSedeTaquillaRemoto().liberarTaquillasDeUsuario(bean.getConexion().getUsuario().getId());
                bean.auditoriaModificar(bean.getObjetoTaquilla());
                bean.getObjetoTaquilla().setDisponible(false);
                getGatSedeTaquillaRemoto().actualizar(bean.getObjetoTaquilla());
                bean.setObjetoSede(bean.getObjetoTaquilla().getGnSedeId());
                bean.getObjetoSede().setConfiguracion(getGatSedeConfiguracion().consultarPorIdSede(bean.getObjetoSede().getId()));
                bean.getObjetoTaquilla().setGatTaquillaServicioList(getGatTaquillaServicioRemoto().listarPorIdTaquilla(bean.getObjetoTaquilla().getId()));
                bean.setListaServicios(getGatServicioUmbralRemoto().listarTodos());
                bean.setObjetoFuncionario(getGatSedeFuncionarioRemoto().consultarPorSedeYUsuario(bean.getObjetoTaquilla().getGnSedeId().getId(), bean.getConexion().getUsuario().getId()));
            } else {
                bean.addError("La taquilla seleccionada esta ocupada");
                bean.setObjetoTaquilla(new GatSedeTaquilla());
            }

        } catch (Exception e) {
            bean.addError("Hubo un fallo al seleccionar una taquilla, favor contactar al adminitrador");
        }
    }

    private void gestionEditar(GatAtencionBean bean) {
        try {
            bean.setObjeto(getGatAtencionRemoto().consultar(bean.getObjeto().getId()));
//            if (bean.getObjeto().getId() == null) {
//                bean.addError("No se encontro la atención, favor contactar al administrador");
//            } else {
            bean.setObjetoTiquete(getGatTiqueteRemoto().consultar(bean.getObjeto().getGatTiquete().getId()));
            bean.getObjetoTiquete().setGatTiketeLlamadosList(getGatTiketeLlamadoRemoto().consultarPorTiquete(bean.getObjetoTiquete().getId()));
            bean.getObjeto().setGatListaComentarios(getGatAtencionComentarioRemoto().consultarListaPorAtencion(bean.getObjeto().getId()));
            int _estado = bean.getObjeto().getEstado();
            if (bean.getObjeto().getGatUsuario() == null) {
                bean.setMostrarDatosUsuario(true);
                bean.getObjeto().setGatUsuario(new GatUsuario());
            }
            if (GatAtencion.ESTADO_LLAMADO == _estado) {
                getGatTiketeLlamadoRemoto().actualizarRellamados(bean.getObjetoTiquete().getId());
                bean.getObjeto().setEstado(GatAtencion.ESTADO_EN_GESTION);
                bean.auditoriaModificar(bean.getObjeto());
//                    if (bean.getObjeto().getFechaHoraInicio() == null) {
                bean.getObjeto().setFechaHoraInicio(new Date());
//                    }
                //Insertar primer servicio
                getGatAtencionRemoto().actualizar(bean.getObjeto());
                GatAtencionHistorico servicio = new GatAtencionHistorico();
                servicio.setComentario("Servicio inicial");
                servicio.setGatAtencionId(new GatAtencion(bean.getObjeto().getId()));
                Maestro maestro = bean.getHashTipoServicio().get(bean.getObjetoTiquete().getMaeTipoServicio());
                servicio.setMaeTipoServicioId(maestro.getId());
                servicio.setMaeTipoServicioCodigo(maestro.getValor());
                servicio.setMaeTipoServicioValor(maestro.getNombre());
                bean.auditoriaGuardar(servicio);
                servicio.setFechaHoraInicio(new Date());
                servicio.setFechaHoraFin(null);
                getGatAtencionHistoricoRemoto().insertar(servicio);
            }
            bean.getObjeto().setGatListaHistoricos(getGatAtencionHistoricoRemoto().consultarListaPorAtencion(bean.getObjeto().getId()));
//            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al abrir gestionar, favor contactar al adminitrador");
        }
    }

    private void gestionModificar(GatAtencionBean bean) {
        try {
//            if (validarComentarios(bean) || bean.getObjeto().getEstado() == GatAtencion.ESTADO_ABANDONO) {
            if (validarComentarios(bean)) {
                bean.auditoriaModificar(bean.getObjetoTiquete());
                if (bean.getObjeto().getFinalizo()) {
                    bean.getObjeto().setFechaHoraFin(new Date());
                    bean.getObjeto().setEstado(GatAtencion.ESTADO_FINALIZADO);
                    bean.getObjetoTiquete().setFechaHoraFinaliza(new Date());
                    bean.getObjetoTiquete().setEstado(GatTiquete.ESTADO_FINALIZADO);
                }
                bean.getObjetoTiquete().setFechaHoraAtendido(bean.getObjeto().getFechaHoraInicio());
                getGatTiqueteRemoto().actualizar(bean.getObjetoTiquete());

                //Insertar usuario
                if (bean.getObjeto().getGatUsuario().getId() == null) {
                    Maestro tipoDocumento = bean.getHashTipoDocumentos().get(bean.getObjeto().getGatUsuario().getMaeTipoDocumentoId());
                    bean.getObjeto().getGatUsuario().setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                    bean.getObjeto().getGatUsuario().setMaeTipoDocumentoValor(tipoDocumento.getNombre());
                    bean.auditoriaGuardar(bean.getObjeto().getGatUsuario());
                    bean.getObjeto().getGatUsuario().setId(getGatUsuarioRemoto().insertar(bean.getObjeto().getGatUsuario()));
                }
                // Gestion de Servicios
                if (bean.getObjeto().getGatListaHistoricos() != null) {//Crear servicios
                    for (GatAtencionHistorico historico : bean.getObjeto().getGatListaHistoricos()) {
                        if (historico.getId() == null) {
                            bean.auditoriaGuardar(historico);
                            historico.setGatAtencionId(new GatAtencion(bean.getObjeto().getId()));
                            getGatAtencionHistoricoRemoto().insertar(historico);
                        }
                    }
                }
                //Gestion de comentarios
                if (bean.getObjeto().getGatListaComentarios() != null) {
                    for (GatAtencionComentario comentario : bean.getObjeto().getGatListaComentarios()) {
                        if (comentario.getId() == null) {
                            bean.auditoriaGuardar(comentario);
                            comentario.setGatAtencionId(new GatAtencion(bean.getObjeto().getId()));
                            getGatAtencionComentarioRemoto().insertar(comentario);
                        }
                    }
                }
                //Cerrar todos los servicios abiertos
                if (bean.getObjeto().getFinalizo()) {
                    List<GatAtencionHistorico> listaServicios = getGatAtencionHistoricoRemoto().consultarListaPorAtencion(bean.getObjeto().getId());
                    for (GatAtencionHistorico servicio : listaServicios) {
                        if (servicio.getFechaHoraFin() == null) {
                            servicio.setFechaHoraFin(new Date());
                            servicio.setTiempo(calcularTotalTiempo(servicio.getFechaHoraInicio(), servicio.getFechaHoraFin()));
                            servicio.setComentario("Cierre de servicio por cierre de Atención");
                            bean.auditoriaModificar(servicio);
                            getGatAtencionHistoricoRemoto().actualizar(servicio);
                        }
                    }
                }
                bean.auditoriaModificar(bean.getObjeto());
                getGatAtencionRemoto().actualizar(bean.getObjeto());
//                listar(bean);
                bean.addMensaje("Se actualizó la atención de manera exitosa");
            } else {
                bean.addError("Se debe realizar al menos un comentario");
            }
        } catch (Exception e) {
            bean.addError("Error: " + e.getMessage());
            bean.addError("Hubo un fallo al gestionar, favor contactar al administrador");
        }
        bean.getObjeto().setFinalizo(false);
    }

    private void transferenciaCrear(GatAtencionBean bean) {
        try {
            bean.setObjeto(getGatAtencionRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoTiquete(getGatTiqueteRemoto().consultar(bean.getObjeto().getGatTiquete().getId()));
            bean.setListaTaquillasOcupadas(getGatSedeTaquillaRemoto().listarOcupadas(bean.getObjetoSede().getId(), bean.getObjetoTiquete().getMaeTipoServicio(), bean.getObjetoTaquilla().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al abrir transferir, favor contactarse con el administrador");
        }
    }

    private void transferenciaGuardar(GatAtencionBean bean) {
        try {
            if (bean.getIdTaquillaTransferir() != null) {
                GatSedeTaquilla taquilla = getGatSedeTaquillaRemoto().consultar(bean.getIdTaquillaTransferir());
                bean.getObjeto().setGatTaquilla(taquilla);
                GatSedeFuncionario funcionario = getGatSedeFuncionarioRemoto().consultarPorSedeYUsuario(bean.getObjetoSede().getId(), taquilla.getUsuarioId().getId());
                bean.getObjeto().setGatSedeFuncionario(funcionario);
                bean.auditoriaModificar(bean.getObjeto());
                getGatAtencionRemoto().actualizar(bean.getObjeto());
//                listar(bean);
                bean.addMensaje("Se transfirio con exito");
            } else {
                bean.addError("No se ha seleccionado una taquilla");
            }

        } catch (Exception e) {
            bean.addError("Hubo un error al transferir favor contactar con el administrador");
        }
    }

    private void taquillaSalir(GatAtencionBean bean) {
        try {
            bean.getObjetoTaquilla().setUsuarioId(null);
            bean.getObjetoTaquilla().setDisponible(true);
            bean.auditoriaModificar(bean.getObjetoTaquilla());
            getGatSedeTaquillaRemoto().actualizar(bean.getObjetoTaquilla());
            bean.setObjetoTaquilla(new GatSedeTaquilla());
            bean.setObjetoSede(new GnSede());
            bean.setListaSedes(getGnSedeRemoto().listarSedesPorFuncionario(bean.getConexion().getUsuario().getId()));
            for (GnSede sede : bean.getListaSedes()) {
                sede.setGatSedeTaquillaList(getGatSedeTaquillaRemoto().listarPorIdSedeActivas(sede.getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al salir de la taquilla, favor contactar al administrador");
        }
    }

    @Override
    public void buscarAfiliado(GatAtencionBean bean) {
        try {
            AsegAfiliado afiliado = null;
            if (bean.getObjetoTiquete().getGatUsuario() != null && bean.getObjetoTiquete().getGatUsuario().getMaeTipoDocumentoId() > 0 && bean.getObjetoTiquete().getGatUsuario().getNumeroDocumento() != null) {
                afiliado = getAfiliadoRemoto().consultar(bean.getObjetoTiquete().getGatUsuario().getMaeTipoDocumentoId(), bean.getObjetoTiquete().getGatUsuario().getNumeroDocumento());
                if (afiliado != null) {
                    bean.getObjetoTiquete().getGatUsuario().setAsegAfiliado(afiliado);
                    bean.getObjetoTiquete().getGatUsuario().setApellidos(afiliado.getApellidos());
                    bean.getObjetoTiquete().getGatUsuario().setNombres(afiliado.getNombres());
                    Maestro tipo = bean.getHashTipoDocumentos().get(bean.getObjetoTiquete().getGatUsuario().getMaeTipoDocumentoId());
                    bean.getObjetoTiquete().getGatUsuario().setMaeTipoDocumentoCodigo(tipo.getValor());
                    bean.getObjetoTiquete().getGatUsuario().setMaeTipoDocumentoValor(tipo.getNombre());
                    bean.getObjetoTiquete().getGatUsuario().setFechaNacimiento(afiliado.getFechaNacimiento());
                    bean.getObjetoTiquete().setPrioritario(validarPrioritario(afiliado));
                } else {
                    bean.getObjetoTiquete().getGatUsuario().setApellidos(null);
                    bean.getObjetoTiquete().getGatUsuario().setNombres(null);
                    bean.getObjetoTiquete().getGatUsuario().setFechaNacimiento(null);
                    bean.getObjetoTiquete().setAfiliado(false);
                }
            } else {
                afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getGatUsuario().getMaeTipoDocumentoId(), bean.getObjeto().getGatUsuario().getNumeroDocumento());
                if (afiliado != null) {
                    bean.getObjeto().getGatUsuario().setAsegAfiliado(afiliado);
                    bean.getObjeto().getGatUsuario().setApellidos(afiliado.getApellidos());
                    bean.getObjeto().getGatUsuario().setNombres(afiliado.getNombres());
                    Maestro tipo = bean.getHashTipoDocumentos().get(bean.getObjeto().getGatUsuario().getMaeTipoDocumentoId());
                    bean.getObjeto().getGatUsuario().setMaeTipoDocumentoCodigo(tipo.getValor());
                    bean.getObjeto().getGatUsuario().setMaeTipoDocumentoValor(tipo.getNombre());
                    bean.getObjeto().getGatUsuario().setFechaNacimiento(afiliado.getFechaNacimiento());
                    bean.setEditarDatosUsuario(true);
                } else {
                    bean.getObjeto().getGatUsuario().setApellidos(null);
                    bean.getObjeto().getGatUsuario().setNombres(null);
                    bean.getObjeto().getGatUsuario().setFechaNacimiento(null);
                    bean.setEditarDatosUsuario(false);
                }
            }

        } catch (Exception e) {
            bean.addError("Hubo un fallo al buscar afiliado, favor contactar al administrador");
        }
    }

    private void turnoGuardar(GatAtencionBean bean) {
        try {
            Maestro tipoDocumento = bean.getHashTipoDocumentos().get(bean.getObjetoTiquete().getGatUsuario().getMaeTipoDocumentoId());
            bean.getObjetoTiquete().getGatUsuario().setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
            bean.getObjetoTiquete().getGatUsuario().setMaeTipoDocumentoValor(tipoDocumento.getNombre());
            bean.auditoriaGuardar(bean.getObjetoTiquete().getGatUsuario());
            bean.getObjetoTiquete().getGatUsuario().setId(getGatUsuarioRemoto().insertar(bean.getObjetoTiquete().getGatUsuario()));
            Maestro tipoServicio = bean.getHashTipoServicio().get(bean.getObjetoTiquete().getMaeTipoServicio());
            bean.getObjetoTiquete().setMaeTipoServicio(tipoServicio.getId());
            bean.getObjetoTiquete().setMaeTipoServicioCodigo(tipoServicio.getValor());
            bean.getObjetoTiquete().setMaeTipoServicioValor(tipoServicio.getNombre());
            int anterior = getGatTiqueteRemoto().consultarTotalHoy(bean.getObjetoSede().getId(), tipoServicio.getId()) + 1;
            bean.getObjetoTiquete().setNumero(bean.getObjetoTiquete().getMaeTipoServicioCodigo() + anterior);
            bean.auditoriaGuardar(bean.getObjetoTiquete());
            bean.getObjetoTiquete().setId(getGatTiqueteRemoto().insertar(bean.getObjetoTiquete()));
            GatAtencion atencion = new GatAtencion();
            atencion.setEstado(GatAtencion.ESTADO_EN_GESTION);
            atencion.setGatSedeFuncionario(bean.getObjetoFuncionario());
            atencion.setGatTaquilla(bean.getObjetoTaquilla());
            atencion.setGatUsuario(bean.getObjetoTiquete().getGatUsuario());
            atencion.setGatTiquete(bean.getObjetoTiquete());
            atencion.setGnSede(bean.getObjetoSede());
            atencion.setFechaHoraTiquete(bean.getObjetoTiquete().getFechaHoraCrea());
            bean.auditoriaGuardar(atencion);
            atencion.setFechaHoraInicio(atencion.getFechaHoraCrea());
            getGatAtencionRemoto().insertar(atencion);
//            listar(bean);
        } catch (Exception e) {
            bean.addError("Hubo un fallo al generar turno, favor contactar al administrador");
        }
    }

    private void turnoCrear(GatAtencionBean bean) {
        try {
            if (getGnSedeHorarioRemoto().estaEnHorario(bean.getObjetoSede().getId())) {
                bean.setObjetoTiquete(new GatTiquete());
                bean.getObjetoTiquete().setGnSede(bean.getObjetoSede());
                bean.getObjetoTiquete().setGatUsuario(new GatUsuario());
                bean.getObjetoTiquete().setAfiliado(true);
            } else {
                bean.addError("No se encuentra en un horario disponible para generacion de turnos");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al crear turno, favor contactar al administrador");
        }
    }

    private void turnoSolicitar(GatAtencionBean bean) {
        try {
            Integer maximo = PropApl.getInstance().get(PropApl.GAT_MAXIMO_ATENCIONES) != null ? Integer.valueOf(PropApl.getInstance().get(PropApl.GAT_MAXIMO_ATENCIONES)) : 0;
            if (bean.getListaAtenciones().size() <= maximo) {
                String listaServicios = "";
                if (bean.getObjetoTaquilla().getGatTaquillaServicioList() != null) {
                    for (GatTaquillaServicio servicio : bean.getObjetoTaquilla().getGatTaquillaServicioList()) {
                        if (!listaServicios.isEmpty()) {
                            listaServicios += ",";
                        }
                        listaServicios += servicio.getMaeTipoServicioId();
                    }
                }
                if (listaServicios.isEmpty()) {
                    bean.addError("No se encontraron servicios");
                    return;
                }
                GatTiquete tiquete = getGatTiqueteRemoto().consultarSiguiente(listaServicios, bean.getObjetoSede().getId());
                if (tiquete != null) {
                    bean.setObjetoTiquete(tiquete);
                    bean.getObjetoTiquete().setEstado(GatTiquete.ESTADO_LLAMADO);
                    getGatTiqueteRemoto().actualizar(bean.getObjetoTiquete());
                    GatAtencion atencion = new GatAtencion();
                    atencion.setFechaHoraTiquete(tiquete.getFechaHoraCrea());
                    atencion.setGatSedeFuncionario(bean.getObjetoFuncionario());
                    atencion.setGatTaquilla(bean.getObjetoTaquilla());
                    atencion.setGatUsuario(bean.getObjetoTiquete().getGatUsuario());
                    atencion.setGatTiquete(bean.getObjetoTiquete());
                    atencion.setGnSede(bean.getObjetoSede());
                    atencion.setEstado(GatAtencion.ESTADO_LLAMADO);
                    bean.auditoriaGuardar(atencion);
                    getGatAtencionRemoto().insertar(atencion);
                    GatTiketeLlamado llamado = new GatTiketeLlamado();
                    llamado.setCantidad(0);
                    llamado.setMaximo(bean.getLlamadosMaximos());
                    llamado.setGatSedeTaquillaId(bean.getObjetoTaquilla());
                    llamado.setGatTiqueteId(bean.getObjetoTiquete());
                    llamado.setGnUsuarioId(bean.getConexion().getUsuario());
                    bean.auditoriaGuardar(llamado);
                    getGatTiketeLlamadoRemoto().insertar(llamado);
//                    listar(bean);
                    bean.addMensaje("Turno solicitado, espera el llamado por favor");
                } else {
                    bean.addMensaje("No hay turnos disponibles para asignar");
                }
            } else {
                bean.addError("Ya se alcanzo el maximo de atenciones disponible");
            }
        } catch (Exception e) {
            System.out.println(e);
            bean.addError("Hubo un error al solicitar turno, favor contactar al administrador");
        }
    }

    private Boolean validarPrioritario(AsegAfiliado afiliado) {
        try {
            if (afiliado.isDiscapacidad()) {
                return true;
            }
            Calendar actual = Calendar.getInstance();
            int anoActual = actual.get(Calendar.YEAR);
            Calendar nacimiento = Calendar.getInstance();
            nacimiento.setTime(afiliado.getFechaNacimiento());
            int anoNacimiento = nacimiento.get(Calendar.YEAR);
            int diferencia = anoActual - anoNacimiento;
            if (actual.get(Calendar.MONTH) > nacimiento.get(Calendar.MONTH)
                    || (actual.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH)
                    && actual.get(Calendar.DATE) > nacimiento.get(Calendar.DATE))) {
                diferencia--;
            }
            if (diferencia >= 60 || diferencia <= 5) {
                return true;
            } else {
                List<PeAfiliadosPrograma> programas = getPeAfiliadosProgramaRemoto().listarPorAfiliadoYCodigoPrograma(afiliado.getId(), "SP11");
                return programas.size() > 0;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void abandonar(GatAtencionBean bean) {
        try {
            bean.setObjeto(getGatAtencionRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoTiquete(getGatTiqueteRemoto().consultar(bean.getObjeto().getGatTiquete().getId()));
            getGatTiketeLlamadoRemoto().actualizarRellamados(bean.getObjetoTiquete().getId());
            bean.getObjeto().setFechaHoraFin(new Date());
            bean.getObjeto().setFechaHoraCancela(new Date());
            bean.getObjeto().setEstado(GatAtencion.ESTADO_ABANDONO);
            bean.getObjetoTiquete().setFechaHoraFinaliza(new Date());
            bean.getObjetoTiquete().setFechaHoraAbandona(new Date());
            bean.getObjetoTiquete().setEstado(GatTiquete.ESTADO_ABANDONADO);
            bean.auditoriaModificar(bean.getObjetoTiquete());
            getGatTiqueteRemoto().actualizar(bean.getObjetoTiquete());
            bean.auditoriaModificar(bean.getObjeto());
            getGatAtencionRemoto().actualizar(bean.getObjeto());
//            listar(bean);
            bean.addMensaje("Se abandono la atención con exito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al abandonar favor contactar al administrador");
        }
    }

    private void llamar(GatAtencionBean bean) {
        try {
            GatTiketeLlamado ultimoLlamado = getGatTiketeLlamadoRemoto().tieneLlamado(bean.getObjetoTiquete().getId());
            if (ultimoLlamado.getEstado() == GatTiketeLlamado.ESTADO_LLAMADO) {
                ultimoLlamado.setMaximo(ultimoLlamado.getMaximo() + 1);
                getGatTiketeLlamadoRemoto().actualizar(ultimoLlamado);
                bean.addMensaje("Se realiza el llamado de manera exitosa");
            } else {
                bean.addMensaje("No hay llamados activos, por favor rellamar");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al llamar, favor contactar al administrador");
        }
    }

    private void rellamar(GatAtencionBean bean) {
        try {
            bean.setObjeto(getGatAtencionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(GatAtencion.ESTADO_LLAMADO);
            bean.auditoriaModificar(bean.getObjeto());
            getGatAtencionRemoto().actualizar(bean.getObjeto());
            bean.setObjetoTiquete(getGatTiqueteRemoto().consultar(bean.getObjeto().getGatTiquete().getId()));
            getGatTiketeLlamadoRemoto().actualizarRellamados(bean.getObjetoTiquete().getId());
            GatTiketeLlamado llamado = new GatTiketeLlamado();
            llamado.setCantidad(0);
            llamado.setMaximo(1);
            llamado.setGatTiqueteId(bean.getObjetoTiquete());
            llamado.setGatSedeTaquillaId(bean.getObjetoTaquilla());
            llamado.setGnUsuarioId(bean.getConexion().getUsuario());
            bean.auditoriaGuardar(llamado);
            getGatTiketeLlamadoRemoto().insertar(llamado);
//            listar(bean);
            bean.addMensaje("Se realizo el llamado nuevamente");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al rellamar, favor contactar al administrador");
        }
    }

    private void reposoCrear(GatAtencionBean bean) {
        try {
            bean.setListaResposos(getGatServicioUmbralRemoto().listarTipo(GatContantes.TIPO_REPOSO));
            bean.setObjReposo(new GatTiempo());
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información para reposte de reposo.");
        }
    }

    private void reposoGuardar(GatAtencionBean bean) {
        try {
            GatServicioUmbral servicio = getGatServicioUmbralRemoto().consultar(bean.getObjReposo().getServicioUbralId());
            if (servicio == null) {
                bean.addError("Debe seleccionar el tipo de reposos a reportar.");
                return;
            }
            bean.auditoriaGuardar(bean.getObjReposo());
            bean.getObjReposo().setFechaInicio(new Date());
            bean.getObjReposo().setTipo(bean.getObjReposo().getServicioUbralId());
            bean.getObjReposo().setUsuariosId(new Usuario(bean.getConexion().getUsuario().getId()));
            //Se calcula el aproximado de tiempo total y el aproximado de fecha fin
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(bean.getObjReposo().getFechaInicio());
            calendar.add(Calendar.MINUTE, servicio.getTiempo());
            Date nuevaFecha = calendar.getTime();
            bean.getObjReposo().setActivo(true);
            bean.getObjReposo().setFechaFin(nuevaFecha);
            bean.getObjReposo().setTiempoTotal(servicio.getTiempo());
            bean.getObjReposo().setId(getGatTiempoRemoto().insertar(bean.getObjReposo()));
            bean.addMensaje("Se registro inicio de reposo.");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar reporte inicial de reposo.");
        }
    }

    private void reposoModificar(GatAtencionBean bean) {
        try {
            GatTiempo tiempo = getGatTiempoRemoto().consultar(bean.getObjReposo().getId());
            tiempo.setFechaFin(new Date());
            tiempo.setActivo(false);
            Integer totalTiempo = calcularTotalTiempo(tiempo.getFechaInicio(), tiempo.getFechaFin());
            tiempo.setTiempoTotal(totalTiempo);
            bean.auditoriaModificar(tiempo);
            getGatTiempoRemoto().actualizarTiempo(tiempo);
            bean.setTotalReposos(getGatTiempoRemoto().cantidadReposos(formateador.format(new Date()), bean.getConexion().getUsuario().getId()));
            bean.addMensaje("Se registro el fin del reposo correctamente.");
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al finalizar reporte inicial de reposo.");
        }
    }

    @Override
    public void cargasInicial(GatAtencionBean bean) {
        try {
            if (bean.getObjetoTaquilla() != null && bean.getObjetoTaquilla().getId() != null) {
                try {
                    if (!bean.getObjetoTaquilla().isSeleccionada()) {
                        GatSedeTaquilla taquillaActual = getGatSedeTaquillaRemoto().consultar(bean.getObjetoTaquilla().getId());
                        if (taquillaActual.isDisponible()) {
                            bean.addError("La taquilla se ha liberado");
                            bean.setSalir(true);
                            return;
                        } else {
                            bean.setSalir(false);
                        }
                    }
                } catch (Exception e) {
                    bean.addError("No se pudo validar la taquilla, favor contactar al administrador");
                    return;
                }
            }
            bean.setFechaMaxima(new Date());
            bean.setListaTiposDocumentos(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumentos(Util.convertToHash(bean.getListaTiposDocumentos()));
            bean.setListaTipoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.GAT_TAQUILLA_TIPO_SERVICIO));
            bean.setHashTipoServicio(Util.convertToHash(bean.getListaTipoServicio()));
            bean.setObjetoTaquilla(getGatSedeTaquillaRemoto().consultarPorIdUsuario(bean.getConexion().getUsuario().getId()));
            bean.setLlamadosMaximos(Integer.parseInt(PropApl.getInstance().get(PropApl.GAT_MAXIMO_LLAMADOS)));
            if (bean.getObjetoTaquilla().getId() != null) {
                bean.setObjetoSede(bean.getObjetoTaquilla().getGnSedeId());
                bean.getObjetoSede().setConfiguracion(getGatSedeConfiguracion().consultarPorIdSede(bean.getObjetoSede().getId()));
                bean.getObjetoTaquilla().setGatTaquillaServicioList(getGatTaquillaServicioRemoto().listarPorIdTaquilla(bean.getObjetoTaquilla().getId()));
                bean.setListaServicios(getGatServicioUmbralRemoto().listarTodos());
                bean.setSalir(!getGnSedeHorarioRemoto().estaEnHorarioDia(bean.getObjetoSede().getId()));
                bean.setObjetoFuncionario(getGatSedeFuncionarioRemoto().consultarPorSedeYUsuario(bean.getObjetoTaquilla().getGnSedeId().getId(), bean.getConexion().getUsuario().getId()));
            } else {
                bean.setListaSedes(getGnSedeRemoto().listarSedesPorFuncionario(bean.getConexion().getUsuario().getId()));
                for (GnSede sede : bean.getListaSedes()) {
                    sede.setGatSedeTaquillaList(getGatSedeTaquillaRemoto().listarPorIdSedeActivas(sede.getId()));
                }
            }
            bean.setObjReposo(getGatTiempoRemoto().consultarActivo(bean.getConexion().getUsuario().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar al administrador");
        }
    }

    private boolean validarTiempo(GatAtencionBean bean, GatAtencion gestion) {
        int tiempo = bean.calcularTimepoAtencion(gestion.getFechaHoraInicio());
        if (gestion != null) {
            if (tiempo > 5) {
                return true;
            }
        }
        return false;
    }

    private Integer calcularTotalTiempo(Date fechaInicio, Date fechaFin) throws Exception {
        long diferenciaEnMilisegundos = fechaFin.getTime() - fechaInicio.getTime();
        long tiempoEnSegundos = diferenciaEnMilisegundos / 1000;
        Long diferenciaEnMinutos = diferenciaEnMilisegundos / (60 * 1000);
        Integer resultado = diferenciaEnMinutos.intValue();
        if (resultado == 0) {
            if (tiempoEnSegundos > 0) {
                resultado++;
            }
        }
        return resultado;
    }

    public boolean validarComentarios(GatAtencionBean bean) {
        boolean valido = false;
        for (GatAtencionComentario comentario : bean.getObjeto().getGatListaComentarios()) {
            if (comentario.getId() == null) {
                valido = true;
                break;
            }
        }
        return valido;
    }

}
