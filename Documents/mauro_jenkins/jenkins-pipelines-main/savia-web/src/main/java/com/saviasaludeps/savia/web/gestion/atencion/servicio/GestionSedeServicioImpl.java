/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnSedeHorario;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatReporteSede;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatReporteTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaServicio;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiketeLlamado;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.dominio.gestionAtencion.HistoricoSede;
import com.saviasaludeps.savia.negocio.administracion.GnSedeHorarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnSedeRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatAtencionRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatPantallaRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeConfiguracionRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeFuncionarioRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeTaquillaRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTaquillaServicioRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiempoRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiqueteRemoto;
import com.saviasaludeps.savia.web.gestion.atencion.bean.GestionSedeBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;

/**
 *
 * @author sgiraldov
 */
public class GestionSedeServicioImpl extends AccionesBO implements GestionSedeServicioIface {

    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");

    private GnSedeRemoto getGnSedeRemoto() throws Exception {
        return (GnSedeRemoto) RemotoEJB.getEJBRemoto("GnSedeServicio", GnSedeRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private GatSedeConfiguracionRemoto getGatSedeConfiguracionRemoto() throws Exception {
        return (GatSedeConfiguracionRemoto) RemotoEJB.getEJBRemoto("GatSedeConfiguracionServicio", GatSedeConfiguracionRemoto.class.getName());
    }

    private GatSedeFuncionarioRemoto getGatSedeFuncionarioRemoto() throws Exception {
        return (GatSedeFuncionarioRemoto) RemotoEJB.getEJBRemoto("GatSedeFuncionarioServicio", GatSedeFuncionarioRemoto.class.getName());
    }

    private GatSedeTaquillaRemoto getGatSedeTaquillaRemoto() throws Exception {
        return (GatSedeTaquillaRemoto) RemotoEJB.getEJBRemoto("GatSedeTaquillaServicio", GatSedeTaquillaRemoto.class.getName());
    }

    private GatTaquillaServicioRemoto getGatTaquillaServicioRemoto() throws Exception {
        return (GatTaquillaServicioRemoto) RemotoEJB.getEJBRemoto("GatTaquillaServicioServicio", GatTaquillaServicioRemoto.class.getName());
    }

    private GnSedeHorarioRemoto getGnSedeHorarioRemoto() throws Exception {
        return (GnSedeHorarioRemoto) RemotoEJB.getEJBRemoto("GnSedeHorarioServicio", GnSedeHorarioRemoto.class.getName());
    }

    private GatPantallaRemoto getGatPantallaRemoto() throws Exception {
        return (GatPantallaRemoto) RemotoEJB.getEJBRemoto("GatPantallaServicio", GatPantallaRemoto.class.getName());
    }

    private GatTiqueteRemoto getGatTiqueteRemoto() throws Exception {
        return (GatTiqueteRemoto) RemotoEJB.getEJBRemoto("GatTiqueteServicio", GatTiqueteRemoto.class.getName());
    }

    private GatAtencionRemoto getGatAtencionRemoto() throws Exception {
        return (GatAtencionRemoto) RemotoEJB.getEJBRemoto("GatAtencionServicio", GatAtencionRemoto.class.getName());
    }

    private GatTiempoRemoto getGatTiempoRemoto() throws Exception {
        return (GatTiempoRemoto) RemotoEJB.getEJBRemoto("GatTiempoServicio", GatTiempoRemoto.class.getName());
    }

    @Override
    public void Accion(GestionSedeBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verGestionar(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestionar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verLiberar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            liberar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    inactivarPantallas(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    reporteSede(bean);
                    break;
                case Url.ACCION_ADICIONAL_5:
                    historicoSede(bean);
                    break;
            }
        }
    }

    @Override
    public void cargasInicial(GestionSedeBean bean) {
        try {
            bean.setListaTiposSede(getMaestroRemoto().consultarPorTipo(MaestroTipo.GAT_SEDE_TIPO));
            if (!bean.getListaTiposSede().isEmpty()) {
                int contador = 0;
                String filtro = "";
                List<Maestro> nueva = new ArrayList<>();
                for (Maestro tipo : bean.getListaTiposSede()) {
                    contador++;
                    if (!tipo.getValor().equals("1")) {
                        nueva.add(tipo);
                        filtro += tipo.getId();
                        if (contador < bean.getListaTiposSede().size()) {
                            filtro += ",";
                        }
                    }
                }
                bean.setListaTiposSede(nueva);
                bean.setListaFiltro(filtro);
            }
            bean.setHashTiposSede(Util.convertToHash(bean.getListaTiposSede()));
            bean.setListaServiciosTaquilla(getMaestroRemoto().consultarPorTipo(MaestroTipo.GAT_TAQUILLA_TIPO_SERVICIO));
            bean.setHashServiciosTaquilla(Util.convertToHash(bean.getListaServiciosTaquilla()));
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaUbicaciones());
            bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
        } catch (Exception e) {
        }
    }

    private void listar(GestionSedeBean bean) {
        try {
            if (bean.getListaFiltro() != null) {

                bean.getParamConsulta().setParametroConsulta1(bean.getListaFiltro());
            }
            bean.getParamConsulta().setCantidadRegistros(getGnSedeRemoto().consultarCantidadListaActiva(bean.getParamConsulta()));
            bean.setRegistros(getGnSedeRemoto().consultarListaActiva(bean.getParamConsulta()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

    private void ver(GestionSedeBean bean) {
        try {
            bean.setObjeto(getGnSedeRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoConfiguracion(getGatSedeConfiguracionRemoto().consultarPorIdSede(bean.getObjeto().getId()));
            bean.getObjeto().setGatSedeFuncionarioList(getGatSedeFuncionarioRemoto().listarPorIdSede(bean.getObjeto().getId()));
            bean.getObjeto().setGatSedeTaquillaList(getGatSedeTaquillaRemoto().listarPorIdSede(bean.getObjeto().getId()));
            for (GatSedeTaquilla taquilla : bean.getObjeto().getGatSedeTaquillaList()) {
                taquilla.setGatTaquillaServicioList(getGatTaquillaServicioRemoto().listarPorIdTaquilla(taquilla.getId()));
            }
            bean.getObjeto().setGnSedeHorarioList(getGnSedeHorarioRemoto().listarPorIdSede(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo en el ver, favor contactar al administrador");
        }
    }

    private void verGestionar(GestionSedeBean bean) {
        try {
            bean.setObjeto(getGnSedeRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoConfiguracion(getGatSedeConfiguracionRemoto().consultarPorIdSede(bean.getObjeto().getId()));
            bean.getObjeto().setGatSedeFuncionarioList(getGatSedeFuncionarioRemoto().listarPorIdSede(bean.getObjeto().getId()));
            bean.getObjeto().setGatSedeTaquillaList(getGatSedeTaquillaRemoto().listarPorIdSede(bean.getObjeto().getId()));
            for (GatSedeTaquilla taquilla : bean.getObjeto().getGatSedeTaquillaList()) {
                taquilla.setGatTaquillaServicioList(getGatTaquillaServicioRemoto().listarPorIdTaquilla(taquilla.getId()));
            }
            bean.getObjeto().setGnSedeHorarioList(getGnSedeHorarioRemoto().listarPorIdSede(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al ver gestionar, favor contactar con el adminitrador");
        }
    }

    private void guardarGestionar(GestionSedeBean bean) {
        try {
            //Completar Historicos
            Maestro tipo = bean.getHashTiposSede().get(bean.getObjeto().getMaeTipoId());
            bean.getObjeto().setMaeTipoCodigo(tipo.getValor());
            bean.getObjeto().setMaeTipoValor(tipo.getNombre());
            bean.auditoriaModificar(bean.getObjeto());
            getGnSedeRemoto().actualizar(bean.getObjeto());
            //Guardar Configuraciones
            try {
                if (bean.getObjetoConfiguracion().getId() == null) {
                    bean.getObjetoConfiguracion().setGnSedeId(bean.getObjeto());
                    getGatSedeConfiguracionRemoto().insertar(bean.getObjetoConfiguracion());
                } else {
                    getGatSedeConfiguracionRemoto().actualizar(bean.getObjetoConfiguracion());
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al guardar la configuracion, favor contactar al administrador");
            }
            //Guardar/Modificar Funcionarios
            try {
                for (GatSedeFuncionario funcionario : bean.getObjeto().getGatSedeFuncionarioList()) {
                    if (funcionario.getId() == null) {
                        funcionario.setGnSedeId(bean.getObjeto());
                        bean.auditoriaGuardar(funcionario);
                        funcionario.setId(getGatSedeFuncionarioRemoto().insertar(funcionario));
                    } else {
                        if (funcionario.isModificado()) {
                            bean.auditoriaModificar(funcionario);
                            getGatSedeFuncionarioRemoto().actualizar(funcionario);
                        }
                    }
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al guardar o modificar el funcionario, favor contactar al adminitrador");
            }
            //Eliminar funcionarios
            try {
                if (bean.getListaFuncionariosEliminar() != null) {
                    for (GatSedeFuncionario funEliminar : bean.getListaFuncionariosEliminar()) {
                        getGatSedeFuncionarioRemoto().eliminar(funEliminar.getId());
                    }
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al eliminar el funcionario, favor contactar al adminitrador");
            }
            //Guardar/Modificar Taquillas
            try {
                for (GatSedeTaquilla taquilla : bean.getObjeto().getGatSedeTaquillaList()) {
                    if (taquilla.getId() == null) {
                        bean.auditoriaGuardar(taquilla);
                        taquilla.setGnSedeId(bean.getObjeto());
                        taquilla.setId(getGatSedeTaquillaRemoto().insertar(taquilla));
                        try {
                            for (GatTaquillaServicio servicio : taquilla.getGatTaquillaServicioList()) {
                                bean.auditoriaGuardar(servicio);
                                servicio.setGatSedeTaquillaId(taquilla);
                                getGatTaquillaServicioRemoto().insertar(servicio);
                            }
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo al guardar servicios taquilla, favor contactar al administrador");
                        }
                    } else {
                        if (taquilla.isModificado()) {
                            bean.auditoriaModificar(taquilla);
                            getGatSedeTaquillaRemoto().actualizar(taquilla);
                            try {
                                for (GatTaquillaServicio servicio : taquilla.getGatTaquillaServicioList()) {
                                    if (servicio.getId() == null) {
                                        servicio.setGatSedeTaquillaId(taquilla);
                                        bean.auditoriaGuardar(servicio);
                                        getGatTaquillaServicioRemoto().insertar(servicio);
                                    }
                                }
                            } catch (Exception e) {
                                bean.addError("Hubo un fallo al guardar servicios taquilla, favor contactar al adminitrador");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al guardar o modificar taquillas, favor contactar al administrador");
            }
            //Eliminar Servicios
            try {
                if (bean.getListaTaquillaServiciosEliminar() != null) {
                    for (GatTaquillaServicio servicio : bean.getListaTaquillaServiciosEliminar()) {
                        if (servicio.getId() != null) {
                            getGatTaquillaServicioRemoto().eliminar(servicio.getId());
                        }
                    }
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al eliminar servicios, favor contactar al administrador");
            }
            //Eliminar Taquilla
            try {
                if (bean.getListaTaquillasEliminar() != null) {
                    for (GatSedeTaquilla taquilla : bean.getListaTaquillasEliminar()) {
                        getGatSedeTaquillaRemoto().eliminar(taquilla.getId());
                    }
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al borrar una taquilla, favor contactar al adminitrador");
            }
            //Guardar/Modificar Horarios
            try {
                for (GnSedeHorario horario : bean.getObjeto().getGnSedeHorarioList()) {
                    if (horario.getId() == null) {
                        bean.auditoriaGuardar(horario);
                        getGnSedeHorarioRemoto().insertar(horario);
                    } else {
                        if (horario.isModificado()) {
                            bean.auditoriaModificar(horario);
                            getGnSedeHorarioRemoto().actualizar(horario);
                        }
                    }
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al guardar o modificar horarios, favor contactar al adminitrador");
            }
            //Elimina horarios
            try {
                if (bean.getListaHorariosEliminar() != null) {
                    for (GnSedeHorario horario : bean.getListaHorariosEliminar()) {
                        getGnSedeHorarioRemoto().eliminar(horario.getId());
                    }
                }
            } catch (Exception e) {
                bean.addError("Hubo un fallo al eliminar horario, favor contactar al administrador");
            }
            if (bean.getErrores().isEmpty()) {
                bean.addMensaje("Se actualizó el registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar gestionar, favor contactar con el adminitrador");
        }
    }

    private void verLiberar(GestionSedeBean bean) {
        try {
            bean.setObjeto(getGnSedeRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setGatSedeTaquillaList(getGatSedeTaquillaRemoto().listarPorIdSede(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al ver liberar, favor contactar al administrador");
        }
    }

    private void liberar(GestionSedeBean bean) {
        try {
            if (bean.getIdTaquillaLiberar() > 0) {
                getGatSedeTaquillaRemoto().liberarTaquilla(bean.getIdTaquillaLiberar());
                bean.addMensaje("Se liberó la taquilla con éxito");
                bean.getObjeto().setGatSedeTaquillaList(getGatSedeTaquillaRemoto().listarPorIdSede(bean.getObjeto().getId()));
            }
        } catch (Exception e) {
        }
    }

    private void inactivarPantallas(GestionSedeBean bean) {
        try {
            getGatPantallaRemoto().inactivarPantallas(bean.getObjeto().getId());
            bean.addMensaje("Se inactivaron todas las pantallas");
        } catch (Exception e) {
            bean.addError("Hubo un fallo inactivando pantallas, favor contactar al administrador");
        }
    }

    private void reporteSede(GestionSedeBean bean) {
        try {
            bean.setObjetoReporteSede(new GatReporteSede());
            bean.getObjetoReporteSede().setTiquetesGenerados(getGatTiqueteRemoto().consultarTotalHoy(bean.getObjeto().getId()));
            bean.getObjetoReporteSede().setTiquetesPrioritarios(getGatTiqueteRemoto().consultarTotalHoyPrioritarios(bean.getObjeto().getId()));
            bean.getObjetoReporteSede().setTiquetesAtendidos(getGatAtencionRemoto().consultarTotalAtentidosHoy(bean.getObjeto().getId()));
            bean.getObjetoReporteSede().setTiquetesSobreatendidos(getGatAtencionRemoto()
                    .consultarTotalSobreAtendidosHoy(bean.getObjeto().getId()));
            bean.getObjetoReporteSede().setTiqueteAbandonadas(getGatAtencionRemoto().consultarTotalAbandonadosHoy(bean.getObjeto().getId()));
            bean.getObjetoReporteSede().setPromedioAtencion(getGatAtencionRemoto().calcularPromedioHoy(bean.getObjeto().getId(), null));
            bean.getObjetoReporteSede().setTaquillasAtencion(getGatTaquillaServicioRemoto().cantidadTaquillasEnAtencion(bean.getObjeto().getId()));
            List<Usuario> listaUsuarios = getGatSedeFuncionarioRemoto().consultarIdUsuarios(bean.getObjeto().getId());
            String idUsuarios = "";
            if (!listaUsuarios.isEmpty()) {
                for (Usuario usuario : listaUsuarios) {
                    if (!idUsuarios.isEmpty()) {
                        idUsuarios += ",";
                    }
                    idUsuarios += "" + usuario.getId();
                }
            }
            bean.getObjetoReporteSede().setTaquillasReposo(getGatTiempoRemoto().consultarTaquillasEnReposo(idUsuarios));
            bean.getObjetoReporteSede().setUsuarioAtencion(getGatAtencionRemoto().consultarTotalAtencionesPorEstadoYSedeHoy(GatAtencion.ESTADO_EN_GESTION,
                    bean.getObjeto().getId()));
            bean.getObjetoReporteSede().setUsuarioEspera(getGatTiqueteRemoto().consultarTotalHoyPorEstadoYSede(bean.getObjeto().getId(),
                    GatTiquete.ESTADO_PENDIENTE));

            bean.setListaReportesTaquilla(new ArrayList<>());
            bean.getObjeto().setGatSedeTaquillaList(getGatSedeTaquillaRemoto().listarPorIdSede(bean.getObjeto().getId()));
            if (!bean.getObjeto().getGatSedeTaquillaList().isEmpty()) {
                for (GatSedeTaquilla taquilla : bean.getObjeto().getGatSedeTaquillaList()) {
                    GatReporteTaquilla reporte = new GatReporteTaquilla();
                    reporte.setNombreTaquilla(taquilla.getNombre());
                    reporte.setUsuariosAtencion(getGatAtencionRemoto().consultaTotalUsuariosTaquilla(taquilla.getId()));
                    reporte.setAtendidos(getGatAtencionRemoto().consultarTotalAtendidoTaquilla(taquilla.getId()));
                    reporte.setAbandonos(getGatAtencionRemoto().consultarTotalAbandonosTaquilla(taquilla.getId()));
                    bean.getListaReportesTaquilla().add(reporte);
                }
            }

            bean.setGraficoBarraModelo(new BarChartModel());
            ChartData datos = new ChartData();
            BarChartDataSet barraDatos = new BarChartDataSet();
            barraDatos.setLabel("Tiquetes");
            List<String> colores = new ArrayList<>();
            colores.add("rgba(255, 99, 132, 0.2)");
            colores.add("rgba(255, 159, 64, 0.2)");
            colores.add("rgba(255, 205, 86, 0.2)");
            colores.add("rgba(75, 192, 192, 0.2)");
            barraDatos.setBackgroundColor(colores);
            List<Number> valores = new ArrayList<>();
            valores.add(bean.getObjetoReporteSede().getTiquetesGenerados());
            valores.add(bean.getObjetoReporteSede().getTiquetesAtendidos());
            valores.add(bean.getObjetoReporteSede().getTiqueteAbandonadas());
            valores.add(bean.getObjetoReporteSede().getTiquetesSobreatendidos());
//            barraDatos.setData(valores);

            List<String> titulos = new ArrayList<>();
            titulos.add("Generados");
            titulos.add("Atendidos");
            titulos.add("Abandonadas");
            titulos.add("Sobre Atendidos");
            datos.setLabels(titulos);

            datos.addChartDataSet(barraDatos);

            bean.getGraficoBarraModelo().setData(datos);

        } catch (Exception e) {
            bean.addError("Hubo un fallo cargando reporte sede, favor contactar al administrador");
        }
    }

    private void historicoSede(GestionSedeBean bean) {
        try {
            Date fechaInicial = bean.getFechaInicio();
            List<GatTiquete> listaTiquetes = getGatTiqueteRemoto().consultarPorFecha(bean.getObjeto().getId(), bean.getFechaInicio(), agregarTiempoFinal(bean.getFechaFin()));
            bean.setListaHistorico(new ArrayList<>());
            if (listaTiquetes.size() > 0) {
                boolean continuar = true;
                while (continuar) {
                    Date fechaFinal = agregarTiempoFinal(fechaInicial);
                    HistoricoSede historico = new HistoricoSede();
                    historico.setFecha(formatoCorto.format(fechaInicial));
                    for (GatTiquete tiquete : listaTiquetes) {
                        if (tiquete.getFechaHoraCrea().getTime()  >= fechaInicial.getTime()
                                && tiquete.getFechaHoraCrea().getTime() <= fechaFinal.getTime()) {
                            historico.setTurnosCreados(historico.getTurnosCreados() + 1);
                            if (tiquete.getEstado().equals(GatTiquete.ESTADO_FINALIZADO)) {
                                historico.setTurnosAtendidos(historico.getTurnosAtendidos() + 1);
                            } else if (tiquete.getEstado().equals(GatTiquete.ESTADO_ABANDONADO)) {
                                historico.setTurnosAbandonados(historico.getTurnosAbandonados() + 1);
                            }
                        }
                    }
                    historico.setTurnosSobreAtendidos(getGatAtencionRemoto().consultarTotalSobreatendidoPorFecha(bean.getObjeto().getId(),
                            fechaInicial));
                    historico.setPromedioAtencion(getGatAtencionRemoto().calcularPromedioPorFecha(bean.getObjeto().getId(), fechaInicial));
                    bean.getListaHistorico().add(historico);
                    fechaInicial = agregarDia(fechaInicial);
                    if (fechaInicial.after(bean.getFechaFin())) {
                        continuar = false;
                    }
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al cargar el historico de la sede, favor contactar al administrador");
        }
    }

    private Date agregarTiempoFinal(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    private Date agregarDia(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

}
