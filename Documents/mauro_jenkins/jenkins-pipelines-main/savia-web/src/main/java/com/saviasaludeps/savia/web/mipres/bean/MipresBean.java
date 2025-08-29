package com.saviasaludeps.savia.web.mipres.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaInsumoMipres;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaqueteMipres;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import com.saviasaludeps.savia.dominio.mipres.MpAnuladaPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpCotizacionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpHomologacion;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoIndicacionUnirs;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoPrincipioActivo;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpNotificacionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionAuditoria;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.MpProgramadaEntrega;
import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.ReportePlanManejo;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;
import com.saviasaludeps.savia.web.mipres.servicio.MipresIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * @author bsgomez
 */
@ManagedBean
@ViewScoped
public class MipresBean extends Url {

    @Autowired
    private MipresIface MipresServicio;
    private MpPrescripcionDetalle objeto;
    private MpPrescripcionDetalle objetoRespaldo;
    private List<MpPrescripcionDetalle> registros;
    private LazyDataModel<MpPrescripcionDetalle> lazyRegistros;
    private LazyDataModel<CntPrestador> lazyPrestador;
    private LazyDataModel<CntPrestadorSede> lazyPrestadorSede;
    private LazyDataModel<CntContratoDetalle> lazyContratoDetalle;
    private LazyDataModel<MaMedicamento> lazyMaMedicamento;
    private LazyDataModel<MaInsumo> lazyMaInsumo;
    private LazyDataModel<MaInsumoMipres> lazyMaInsumoMipres;
    private LazyDataModel<MaPaqueteMipres> lazyMaPaqueteMipres;
    private LazyDataModel<MaTecnologiaMipres> lazyMaTecnologiaMipres;
    private LazyDataModel<MaTecnologia> lazyMaTecnologia;
    private List<MaMedicamento> registroMaMedicamento;
    private List<MaInsumo> registroMaInsumo;
    private List<MaInsumoMipres> registroMaInsumoMipres;
    private List<MaPaqueteMipres> registroMaPaqueteMipres;
    private List<MaTecnologiaMipres> registroMaTecnologiaMipres;
    private List<MaTecnologia> registroMaTecnologia;
    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private ParamConsulta paramConsultaMaMedicemento;
    private ParamConsulta paramConsultaMaInsumo;
    private ParamConsulta paramConsultaMaPaquete;
    private ParamConsulta paramConsultaMaTecnologia;
    private ParamConsulta paramConsultaMaTecnologiaMipres;
    private MpDireccionamientoEntregado direccionamientoEntregado;
    private MpPrescripcionSuministro prescripcionSuministro;
    private MpDireccionamiento direccionamiento;
    private MpDireccionamiento direccionamientoPorAnulacion;
    private MpEntregaFactura entregaFactura;
    private List<MpDireccionamiento> direccionamientoDireccionado;
    private MpNoDireccionado noDireccionado;
    private MpAnuladaPrescripcion MpPrescripcionAnulada;
    private MpEntregaSuministro mpEntregaSuministro;

    private List<MpPrescripcion> prescripcionListaHistoricos;
    private AuCotizacion objetoCotizacion;
    private MpCotizacionDetalle objetoCotizacionDetalle;
    private MpPrescripcion prescripcionSeleccionada;
    private MpPrescripcion prescripcion;
    private MpPrescripcion prescripcionH;
    private MpDetalleDTO prescripcionDto;
    private MpDetalleDTO prescripcionDtoH;
    private MpPrescripcionTecnologia prescripcionTecnologia;
    private MpPrescripcionInsumo prescripcionInsumo;
    private MpPrescripcionMedicamento prescripcionMedicamento;
    private MpPrescripcionRecobrante prescripcionRecobrante;
    private MpPrescripcionRecobrante prescripcionRecobranteH;
    private MpPrescripcionProgramada prescripcionProgramada;
    private List<MpPrescripcionProgramada> listaPrescripcionProgramada;
    private MpNotificacionHistorico notificacionHistorico;
    private List<MpNotificacionHistorico> listaNotificacionHistorico;
    private MpProgramadaEntrega programadaEntrega;
    private List<MpProgramadaEntrega> listaprogramadaEntrega;
    private List<MpDetalleDTO> listaPrescripcionDto;
    private List<MpDetalleDTO> listaPrescripcionDtoRespaldo;
    private List<MpDetalleDTO> listaPrescripcionDtoH;
    private List<MpMedicamentoPrincipioActivo> listaPrincipiosActivos;
    private List<MpMedicamentoIndicacionUnirs> listaMpIndicacionUnirs;
    private List<MpHomologacion> listaHomologaciones;
    private List<MpDireccionamiento> listadireccionamientos;
    private List<MpDireccionamiento> listadireccionamientosAux;
    private List<MpDireccionamientoEntregado> listaDireccionamientosEntregados;
    private List<MpNoDireccionado> listanoDireccionados;
    private MpPrescripcionAuditoria prescripcionAuditoria;
    private MpPrescripcionAuditoria prescripcionAuditoriaRespaldo;
    private List<MpPrescripcionAuditoria> listaPrescripcionAuditoriaRespaldo;
    private MpMedicamentoIndicacionUnirs indicacionUnirs;
    private MpPrescripcionHistorico prescripcionHistorico;
    private List<MpPrescripcionHistorico> listaPrescripcionHistoricos;
    private List<Maestro> listaTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private List<CntPrestador> registroPrestadores;
    private List<CntPrestadorSede> registroPrestadorSede;
    private List<CntContratoDetalle> registroDetalleContrato;
    private ParamConsulta paramConsultaPrestador;
    private ParamConsulta paramConsultaPrestadorSede;
    private ParamConsulta paramConsultaContratoDetalle;
    private boolean tienePrincipioActivo;
    private boolean tieneNoDireccionamientos;
    private boolean tieneEntregas;
    private boolean tieneDireccionamiento;
    private Integer calcularCantidadDeEntregas;
    private Integer calculoEntregaDisponible;
    private boolean activarBotonMed = false;
    private boolean activarBotonTec = false;
    private boolean activarBotonMpTec = false;
    private boolean activarBotonIns = false;
    private boolean mostrarBotonDireccionamiento;
    private boolean mostrarBotonAuditar;
    private boolean mostrarBotonNoDireccionamiento;
    private boolean refrescarAtencion = false;
    private boolean renderizarPanelNotificar = false;
    private boolean notificarPaciente = false;
    private boolean notificarPrestador = false;
    private boolean notificarCorreoPaciente = false;
    private boolean notificarCorreoPrestador = false;
    private boolean notificarSmsPaciente = false;
    private boolean notificarSmsPrestador = false;
    private boolean mostrarPanelSuministro = false;
    private boolean mostrarPanelSuministroBotonera = false;
    private boolean desactivarBotonAnular = false;
    private boolean desactivarBotonGuardar = false;
    private boolean desactivarBotonCrear = false;
    private boolean desactivarBotonUltimaEntrega = false;
    private boolean desactivarBotonEntregaCompleta = false;
    private String correoPrestador = null;
    private String telefonoPrestador = null;
    private String correoPaciente = null;
    private String telefonoPaciente = null;
    private int numeroDeEntregasCalculada;
    private List<Integer> cantidadesParaEntregar = new ArrayList<>();
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<SelectItem> causasNoDireccionamiento;
    private List<SelectItem> causasNoDireccionamientoParcial;
    private List<SelectItem> causasNoDireccionamientoDiferido;
    private List<SelectItem> listaPrescripcionesAsociadas;
    private boolean mostrarPrescripcionAsociada;
    private boolean mostrarTablaItems;
    private boolean mostrarPanelParcial = false;
    private boolean mostrarPanelDiferido = false;
    private String valorAsociado = null;
    private boolean mostrarBotonParcial = false;
    private boolean mostrarBotonDiferido = false;
    private boolean mostrarAlertaPrimeraV;
    private boolean tieneAuditoria = false;
    private Integer numeroDeTutelas;
    private Integer numeroDeTutelasH;

    private boolean requiereAnulacion = false;
    private boolean renderizarPanelAnular = false;
    private boolean tieneAnulacion = false;
    private boolean mostrarPanelSinFecha;
    private boolean validoParaEditar;
    private boolean mostrarPanelConFecha;
//    private Date minDate;
//    private Date maxDate;
    private final long oneDay = 24 * 60 * 60 * 1000;

    private Integer consecutivoDireccionado;
    private Integer numDirecionamientoDireccionado;
    private Integer disponibleMasAnulado;
    private Integer reservaValorAnterior;
    private String numeroPrescripcionStr = null;

    private List<SelectItem> juntaOpt;
    private List<SelectItem> estadoOpt;
    private List<Integer> direccionamientoIds;
    Integer idSeleccionTecnologia;
    Integer tipoSeleccionTecnologia;
    Integer idSeleccionTecnologiaAnulada;
    Integer tipoSeleccionTecnologiaAnulada;
    Integer idPrescripcionEntrega;
    Integer idItemEntrega;
    Integer tipoTecnologiaEntrega;
    private String reporteTicketString;
    String documentoAfiliado;
    Integer idPRescripcion;
    Integer idPrescripcionRe = null;
    String numeroPrescripcionRe = null;
    String regimenRe = null;
    private Boolean tieneCotizacion;
    private Integer idCotizacion;
    private Boolean borradoLogico;

    public MipresBean() {
        this.objeto = new MpPrescripcionDetalle();
        Modulo mod = super.validarModulo(Modulo.ID_GESTION_MIPRES);

        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyRegistros = new LazyDataModel<MpPrescripcionDetalle>() {
                private List<MpPrescripcionDetalle> listaMpPrescripcion;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MpPrescripcionDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    validarFiltros(getParamConsulta());
                    refrescar();
                    listaMpPrescripcion = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaMpPrescripcion;
                }

                @Override
                public String getRowKey(MpPrescripcionDetalle mpPrescripcion) {
                    return mpPrescripcion.getIdPrescripcion().toString();
                }

                @Override
                public MpPrescripcionDetalle getRowData(String ref) {
                    Integer id = Integer.valueOf(ref);
                    for (MpPrescripcionDetalle refe : listaMpPrescripcion) {
                        if (id.equals(refe.getIdPrescripcion())) {
                            return refe;
                        }
                    }
                    return null;
                }

            };
            lazyPrestador = new LazyDataModel<CntPrestador>() {

                private List<CntPrestador> prestadores;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaPrestador().getCantidadRegistros();
                }

                @Override
                public List<CntPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    setParamConsultaPrestador(new ParamConsulta());
                    getParamConsultaPrestador().setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsultaPrestador().setPrimerRegistro(primerRegistro);
                    getParamConsultaPrestador().setRegistrosPagina(registrosPagina);
                    getParamConsultaPrestador().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaPrestador().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarPrestador();
                    prestadores = getRegistroPrestadores();
                    setRowCount(getParamConsultaPrestador().getCantidadRegistros());
                    return prestadores;
                }

                @Override
                public String getRowKey(CntPrestador sede) {
                    return sede.getId().toString();
                }

                @Override
                public CntPrestador getRowData(String prestadoresId) {
                    Integer id = Integer.valueOf(prestadoresId);
                    for (CntPrestador pres : prestadores) {
                        if (id.equals(pres.getId())) {
                            return pres;
                        }
                    }
                    return null;
                }
            };

        }
        generarMensajes();
    }

    @PostConstruct
    public void cargaInicial() {
        juntaOpt = new ArrayList<>();
        estadoOpt = new ArrayList<>();
        FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
        getMipresServicio().CargaInicial(this);
        listar();

    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    //Acciones
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getMipresServicio().Accion(this);
        generarMensajes();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MipresBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Ver(int _id) {
        setNumeroDeTutelas(0);
        setPrescripcion(new MpPrescripcion());
        setMpPrescripcionAnulada(new MpAnuladaPrescripcion());
        listaPrescripcionDto = new ArrayList<>();
        listaPrescripcionHistoricos = new ArrayList<>();
        getObjeto().setIdPrescripcion(_id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getMipresServicio().Accion(this);
        if (MpPrescripcionAnulada != null) {
            tieneAnulacion = true;
        } else {
            tieneAnulacion = false;
        }
        if (prescripcionListaHistoricos.size() == 1) {
            mostrarAlertaPrimeraV = true;
        } else {
            mostrarAlertaPrimeraV = false;
        }
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verH(String documento, Integer id) {

        if (documento != null && id != null) {
            // Si no hay valores previos, asignar directamente
            if (documentoAfiliado == null && idPRescripcion == null) {
                documentoAfiliado = documento;
                idPRescripcion = id;
            } // Si ya existen valores pero son diferentes, actualizar
            else if (!documento.equals(documentoAfiliado) || !id.equals(idPRescripcion)) {
                documentoAfiliado = documento;
                idPRescripcion = id;
            }
        }

        setPrescripcion(new MpPrescripcion());
        listaPrescripcionHistoricos = new ArrayList<>();
        getObjeto().setIdPrescripcion(idPRescripcion);
        getObjeto().setNumeroDocumentoAfiliado(documentoAfiliado);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getMipresServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmHistori");
        PrimeFaces.current().executeScript("PF('dialogoVerHistoricoNuevo').show()");
        procesoFinal();
    }

//    public void sincronizarDireccionamiento(String prescripcion, int id, String regimen) {
//        objeto.setNumeroPrescripcion(prescripcion);
//        objeto.setIdPrescripcion(id);
//        objeto.setRegimen(regimen);
//        super.setAccion(ACCION_ADICIONAL_10);
//        super.setDoAccion(ACCION_ADICIONAL_10);
//        getMipresServicio().Accion(this);
////        getMipresServicio().sincronizarDireccionamiento(prescripcion, id, isSub);
//        procesoFinal();
//        direcciona(this.objeto);
//    }
    public void sincronizarDireccionamiento() {
        super.setAccion(ACCION_ADICIONAL_10);
        super.setDoAccion(ACCION_ADICIONAL_10);
        getMipresServicio().Accion(this);
//        getMipresServicio().sincronizarDireccionamiento(prescripcion, id, isSub);
        procesoFinal();
        direcciona(this.objeto);
    }

    public void reSincronizar(String pres, Integer idPres, String regimen) {
        numeroPrescripcionRe = pres;
        idPrescripcionRe = idPres;
        regimenRe = regimen;
        super.setAccion(ACCION_ADICIONAL_10);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getMipresServicio().Accion(this);
        procesoFinal();
    }

    public void validarFiltros(ParamConsulta param) {
        if (param.getFiltros().get("tipoTecnologiaItem") == null && param.getFiltros().get("estadoItem") != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Debe seleccionar una tecnología antes de filtrar por estado.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void guardarAuditoria() {
        if (prescripcionAuditoria.getEstado() == null) {
            addError("no se permite audirtar sin antes seleccionar un estado valido.");
        }
        if (prescripcionAuditoria.getNotaAuditoria() == null || "".equals(prescripcionAuditoria.getNotaAuditoria())) {
            addError("no se permite auditar sin una descripción / nota de auditoria");
        }
        if (prescripcionAuditoria.getEstado() == 3) {

            if (notificacionHistorico.getMensaje() == null || notificacionHistorico.getMensaje().trim().isEmpty()) {
                addError("No se permite auditar sin mensaje de notificación");
            }
            if (!(notificarPrestador)) {
                addError("No se permite auditar sin notificar");
            }
            if (notificarPrestador) {
                if ((correoPrestador == null || correoPrestador.isEmpty())
                        && (telefonoPrestador == null || telefonoPrestador.isEmpty())) {
                    addError("Se requiere al menos un medio de notificación: Correo o número telefónico  ");
                } else {
                    if (correoPrestador != null) {
                        if (!correoPrestador.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                            addError("Correo prestador: no es un correo valido");
                        }
                    }
                    if (telefonoPrestador != null) {
                        if (!telefonoPrestador.matches("^[0-9]{10}$")) {
                            addError("Número de celular del Prestador: no es un número válido");
                        }
                    }

                }
            }
        }

//        if (renderizarPanelNotificar) {
//            if (!(notificarPrestador || notificarPaciente)) {
//                addError("No se permite auditar sin notificar al menos a un prestador o un paciente");
//            }
//            if (notificarPaciente) {
//                if ((notificarCorreoPaciente && (correoPaciente == null || correoPaciente.isEmpty()))
//                        || (notificarSmsPaciente && (telefonoPaciente == null || telefonoPaciente.isEmpty()))) {
//                    addError("No se permite auditar sin asignar un correo o número celular para el paciente");
//                } else {
//                    if (correoPaciente != null) {
//                        if (!correoPaciente.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
//                            addError("Correo paciente: no es un correo valido");
//                        }
//                    }
//                    if (telefonoPaciente != null) {
//                        if (!telefonoPaciente.matches("^[0-9]{10}$")) {
//                            addError("Número de celular paciente: no es un número válido");
//                        }
//                    }
//                }
//            }
//            if (notificarPrestador) {
//                if ((notificarCorreoPrestador && (correoPrestador == null || correoPrestador.isEmpty()))
//                        || (notificarSmsPrestador && (telefonoPrestador == null || telefonoPrestador.isEmpty()))) {
//                    addError("No se permite auditar sin asignar un correo o número celular para el prestador");
//                } else {
//                    if (correoPrestador != null) {
//                        if (!correoPrestador.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
//                            addError("Correo prestador: no es un correo valido");
//                        }
//                    }
//                    if (telefonoPrestador != null) {
//                        if (!telefonoPrestador.matches("^[0-9]{10}$")) {
//                            addError("Número de celular del Prestador: no es un número válido");
//                        }
//                    }
//                }
//            }
//            if (notificacionHistorico.getMensaje() == null || notificacionHistorico.getMensaje().isEmpty()) {
//                addError("No se permite auditar sin un mensaje de notificación en la auditoría");
//            }
//        }
        if (!super.isError()) {
            super.setAccion(ACCION_CREAR);
            getMipresServicio().Accion(this);
            crearLog("Guardar Auditoria", getPrescripcionAuditoria().toString());
            cerrarModalGestionar();
        }

        procesoFinal();
    }

    public void verItem(int _id, int tipoTecnologia, int origen) {
        tieneAuditoria = false;
        prescripcionAuditoriaRespaldo = new MpPrescripcionAuditoria();
        listaPrescripcionHistoricos = new ArrayList<>();
        listaPrincipiosActivos = new ArrayList<>();
        listaPrescripcionProgramada = new ArrayList<>();
        listaprogramadaEntrega = new ArrayList<>();
        listaDireccionamientosEntregados = new ArrayList<>();
        listaMpIndicacionUnirs = new ArrayList<>();

        getObjeto().setIdItem(_id);
        getObjeto().setTipoTecnologiaItem((tipoTecnologia));

        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_1);

        getMipresServicio().Accion(this);
        switch (tipoTecnologia) {
            case 1:
                PrimeFaces.current().ajax().update("frmVerItemMM");
                PrimeFaces.current().executeScript("PF('dialogoVerItemMM').show()");

                break;
            case 4:
                PrimeFaces.current().ajax().update("frmVerItemM");
                PrimeFaces.current().executeScript("PF('dialogoVerItemM').show()");

                break;
            case 2:
                PrimeFaces.current().ajax().update("frmVerItemT");
                PrimeFaces.current().executeScript("PF('dialogoVerItemT').show()");

                break;
            case 3:
                PrimeFaces.current().ajax().update("frmVerItemII");
                PrimeFaces.current().executeScript("PF('dialogoVerItemII').show()");

                break;
            case 5:
                PrimeFaces.current().ajax().update("frmVerItemI");
                PrimeFaces.current().executeScript("PF('dialogoVerItemI').show()");

                break;
        }
        procesoFinal();
    }

    public void verItemOrigenGestion(int _id, int tipoTecnologia, int origen) {
        objetoRespaldo = new MpPrescripcionDetalle();
        tieneAuditoria = false;
        prescripcionAuditoriaRespaldo = new MpPrescripcionAuditoria();
        listaPrescripcionHistoricos = new ArrayList<>();
        listaPrincipiosActivos = new ArrayList<>();
        listaPrescripcionProgramada = new ArrayList<>();
        listaprogramadaEntrega = new ArrayList<>();
        listaDireccionamientosEntregados = new ArrayList<>();
        listaMpIndicacionUnirs = new ArrayList<>();

        getObjetoRespaldo().setIdItem(_id);
        getObjetoRespaldo().setTipoTecnologiaItem((tipoTecnologia));

        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_2);

        getMipresServicio().Accion(this);
        switch (tipoTecnologia) {
            case 1:
                PrimeFaces.current().ajax().update("frmVerItemMMm");
                PrimeFaces.current().executeScript("PF('dialogoVerItemMMm').show()");
                break;
            case 4:
                PrimeFaces.current().ajax().update("frmVerItemMm");
                PrimeFaces.current().executeScript("PF('dialogoVerItemMm').show()");

                break;
            case 2:
                PrimeFaces.current().ajax().update("frmVerItemTt");
                PrimeFaces.current().executeScript("PF('dialogoVerItemTt').show()");

                break;
            case 3:
                PrimeFaces.current().ajax().update("frmVerItemIIi");
                PrimeFaces.current().executeScript("PF('dialogoVerItemIIi').show()");

                break;
            case 5:
                PrimeFaces.current().ajax().update("frmVerItemIi");
                PrimeFaces.current().executeScript("PF('dialogoVerItemIi').show()");

                break;
        }
        procesoFinal();
    }

    public void auditar(MpPrescripcionDetalle obj) {
        if (!obj.getBanderaAtencion()) {

            getMipresServicio().actualizarAtencion(obj.getIdItem(), obj.getTipoTecnologiaItem());

            setNumeroDeTutelas(0);
            this.setRequiereAnulacion(false);
            this.setRenderizarPanelAnular(false);
            setObjeto(obj);
            MpNotificacionHistorico h = new MpNotificacionHistorico();
            setNotificacionHistorico(h);
            reiniciarNotificacion();
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_ADICIONAL_1);
            getMipresServicio().Accion(this);
//            if (obj.getCodigoAmbitoAtencion() == null) {
//                renderizarPanelNotificar = false;
//            } else {
//                if ("22".equals(obj.getCodigoAmbitoAtencion()) || "30".equals(obj.getCodigoAmbitoAtencion())) {
//                    if (obj.getReferenciaContra() != null && obj.getReferenciaContra()) {
//                        renderizarPanelNotificar = true;
//                    } else {
//                        renderizarPanelNotificar = false;
//                    }
//                } else {
//                    renderizarPanelNotificar = true;
//                }
//            }
            renderizarPanelNotificar = true;
            notificarBtn();
            procesoFinal();
        }
    }

    public void direccionar(int _id, int tecnologia) {
        resetFormularioDireccionamiento();
        getObjeto().setIdItem(_id);
        getObjeto().setTipoTecnologiaItem(tecnologia);
        super.setAccion(ACCION_ADICIONAL_2);
        getMipresServicio().Accion(this);
        entregasLista();
        PrimeFaces.current().executeScript("PF('dlgDireccionamiento').show()");
        procesoFinal();
    }

    public void noDireccionar(MpPrescripcionDetalle obj) {
        boolean valor = true;
        if (obj.getJuntaP() == 2) {
            try {
                SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fecha = getMipresServicio().consultarHorarioDePrescripcion(obj.getIdPrescripcion());
                Date fechaActual = new Date();
                Date fechaPrescripcion = formatoFechaHora.parse(fecha);
                long diferenciaMilisegundos = fechaActual.getTime() - fechaPrescripcion.getTime();
                long diferenciaHoras = diferenciaMilisegundos / (1000 * 60 * 60);
                if (diferenciaHoras > 72) {
                    valor = true;
                } else {
                    valor = false;
                }
            } catch (ParseException ex) {
                Logger.getLogger(MipresBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            valor = true;
        }
        if (valor) {
            if (!obj.getBanderaAtencion()) {
                objetoCotizacion = new AuCotizacion();
                objetoCotizacionDetalle = new MpCotizacionDetalle();
                getMipresServicio().actualizarAtencion(obj.getIdItem(), obj.getTipoTecnologiaItem());
                setNumeroDeTutelas(0);
                numeroPrescripcionStr = obj.getNumeroPrescripcion();
                noDireccionado = new MpNoDireccionado();
                listaPrescripcionDto = new ArrayList<>();
                listaPrescripcionesAsociadas = new ArrayList<>();
                setMostrarPrescripcionAsociada(false);
                setMostrarTablaItems(false);
                setObjeto(obj);
                noDireccionado.setTipoTecnologia(obj.getTipoTecnologiaItem());
                noDireccionado.setMpPrescripcionesId(new MpPrescripcion(obj.getIdPrescripcion()));
                if (null != obj.getTipoTecnologiaItem()) {
                    switch (obj.getTipoTecnologiaItem()) {
                        case 1:
                        case 4:
                            noDireccionado.setMpPrescripcionMedicamentosId(new MpPrescripcionMedicamento(obj.getIdItem()));
                            break;
                        case 2:
                            noDireccionado.setMpPrescripcionTecnologiasId(new MpPrescripcionTecnologia(obj.getIdItem()));
                            break;
                        case 3:
                        case 5:
                            noDireccionado.setMpPrescripcionInsumosId(new MpPrescripcionInsumo(obj.getIdItem()));
                            break;
                        default:
                            break;
                    }
                }
                noDireccionado.setJustificacionNoDireccionamiento("");
                noDireccionado.setFecNoDireccionamiento(null);
                noDireccionado.setConsecutivoTecnologia(obj.getConsecutivoItem());
                noDireccionado.setNumeroPrescripcionAsociada(null);
                valorAsociado = null;
                getMipresServicio().consultarTutela(this);
                getMipresServicio().validarCotizacion(this);
                PrimeFaces.current().ajax().update("frmNoDireccionar");
                PrimeFaces.current().executeScript("PF('dlgNoDireccionar').show()");
                crearLog("Crear No Direccionamiento", getObjeto().toString());
            }
        } else {
            addMensaje("Prescripción En Espera De Junta Profesionales - Pendiente");
        }
        generarMensajes();
    }

    public void cerrarModalGestionar() {

        if (!getObjeto().getBanderaAtencion()) {
            getMipresServicio().actualizarFinAtencion(getObjeto().getIdItem(), getObjeto().getTipoTecnologiaItem());
        }
        PrimeFaces.current().ajax().update("frmPrescripciones");
    }

    public void gestionar(Integer tipoTec, int id) {

        char z = 'A';
        setAccion(z);

        setObjeto(getMipresServicio().consultarPrescripcionDetalle(tipoTec, id));
        if (!getObjeto().getBanderaAtencion()) {
            getMipresServicio().actualizarAtencion(getObjeto().getIdItem(), getObjeto().getTipoTecnologiaItem());

            mostrarBotonAuditar = true;
            mostrarBotonDireccionamiento = false;
            mostrarBotonNoDireccionamiento = false;

            getMipresServicio()
                    .consultarAuditoria(this);
            if (mostrarBotonDireccionamiento == true) {
                mostrarBotonAuditar = true;
                mostrarBotonNoDireccionamiento = true;
                if (!"22".equals(objeto.getCodigoAmbitoAtencion()) && !"30".equals(objeto.getCodigoAmbitoAtencion())) {
                    mostrarBotonDireccionamiento = true;
                    mostrarBotonNoDireccionamiento = true;
                } else {
                    mostrarBotonDireccionamiento = false;
                    mostrarBotonNoDireccionamiento = false;
                    if (getObjeto().getReferenciaContra() == true) {
                        mostrarBotonDireccionamiento = true;
                        mostrarBotonNoDireccionamiento = true;
                    }
                }
            } else {
                mostrarBotonAuditar = true;
                mostrarBotonNoDireccionamiento = false;
            }
            if (objeto.getEstadoItem() == 2 || objeto.getEstadoItem() == 14) {
//            addMensaje("Prescripción ya fue gestionada no direccionada");
                mostrarBotonDireccionamiento = false;
                mostrarBotonNoDireccionamiento = false;
            }
            if (objeto.getEstadoItem() == 1) {
////            addMensaje("Prescripción ya se encuenta direccionada");
////            mostrarBotonDireccionamiento = false;
                mostrarBotonNoDireccionamiento = false;
            }

            PrimeFaces.current().ajax().update("frmgestionopciones");
            PrimeFaces.current().executeScript("PF('dlgGestion').show()");

            procesoFinal();

        } else {
            addError("Item Se encuentra En Atención");
            generarMensajes();
        }
    }

    public void direcciona(MpPrescripcionDetalle obj) {
        try {
            boolean valor = true;
            if (obj.getJuntaP() == 2) {
                SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fecha = getMipresServicio().consultarHorarioDePrescripcion(obj.getIdPrescripcion());
                Date fechaActual = new Date();
                Date fechaPrescripcion = formatoFechaHora.parse(fecha);
                long diferenciaMilisegundos = fechaActual.getTime() - fechaPrescripcion.getTime();
                long diferenciaHoras = diferenciaMilisegundos / (1000 * 60 * 60);
                if (diferenciaHoras > 72) {
                    valor = true;
                } else {
                    valor = false;
                }
            } else {
                valor = true;
            }
            if (obj.getJuntaP() != 4) {
                if (valor) {
                    if (!obj.getBanderaAtencion()) {
                        getMipresServicio().actualizarAtencion(obj.getIdItem(), obj.getTipoTecnologiaItem());
                        objetoCotizacion = new AuCotizacion();
                        objetoCotizacionDetalle = new MpCotizacionDetalle();
                        setTipoSeleccionTecnologia(null);
                        setIdSeleccionTecnologia(null);
                        numeroPrescripcionStr = obj.getNumeroPrescripcion();
                        setNumeroDeTutelas(0);
                        inicializarFormDir();
                        validoParaEditar = true;
                        if (obj.getTipoTecnologiaItem() == 2 || obj.getTipoTecnologiaItem() == 3) {
                            setMostrarBotonDiferido(true);
                        } else {
                            setMostrarBotonDiferido(false);
                        }
                        setMostrarBotonParcial(false);
                        resetFormularioDireccionamiento();
                        setObjeto(obj);
                        super.setAccion(ACCION_ADICIONAL_2);
                        getMipresServicio().Accion(this);
                        numDirecionamientoDireccionado = direccionamientoDireccionado.size();
                        consecutivoDireccionado = numDirecionamientoDireccionado + 1;

                        direccionamiento.setNumeroPrescripcionAso(null);
                        direccionamiento.setUbicacionSedeId(obj.getAfiliacionUbicacion().getId());
                        direccionamiento.setUbicacionSedeIdStr(getMipresServicio().consultarPrefijo(direccionamiento.getUbicacionSedeId()));
                        entregasLista();
                        borradoLogico = false;
                        if (direccionamientoDireccionado != null && !direccionamientoDireccionado.isEmpty()) {
                            for (MpDireccionamiento registro : direccionamientoDireccionado) {
                                String respuesta = registro.getRespuestaDireccionamiento();
                                if (registro.getEstado() == 3 && respuesta != null
                                        && respuesta.contains("CodSerTecAEntregar")) {
                                    borradoLogico = true;
                                }

                            }
                        }

                        if (obj.getTipoTecnologiaItem() != null) {
                            switch (obj.getTipoTecnologiaItem()) {
                                case 1: {
                                    setActivarBotonMed(false);
                                    setActivarBotonTec(false);
                                    setActivarBotonIns(false);
                                    setActivarBotonMpTec(false);

                                }
                                break;
                                case 2: {
                                    setActivarBotonMed(false);
                                    setActivarBotonTec(false);
                                    setActivarBotonIns(false);
                                    setActivarBotonMpTec(false);
                                }
                                break;
                                case 3: {
                                    setActivarBotonMed(false);
                                    setActivarBotonTec(false);//insumo
                                    setActivarBotonIns(false);
                                    setActivarBotonMpTec(false);

                                }
                                break;
                                case 4: {
                                    setActivarBotonMed(false);
                                    setActivarBotonTec(false);
                                    setActivarBotonIns(false);
                                    setActivarBotonMpTec(false);

                                }
                                break;
                                case 5: {
                                    setActivarBotonMed(false);
                                    setActivarBotonTec(false);//insumo
                                    setActivarBotonIns(false);
                                    setActivarBotonMpTec(false);

                                }
                                break;
                            }
                        }
                        if (prescripcionListaHistoricos.size() == 1) {
                            mostrarAlertaPrimeraV = true;
                        } else {
                            mostrarAlertaPrimeraV = false;
                        }
                        mostrarPanelSinFecha = true;
                        mostrarPanelConFecha = false;
                        PrimeFaces.current().executeScript("PF('dlgDireccionamiento').show()");
                        crearLog("Gestión - Crear Direccionamiento", getObjeto().toString());

                        procesoFinal();
                    }
                } else {
                    addMensaje("Prescripción En Espera De Junta Profesionales - Pendiente");
                }
            } else {
                addMensaje("No Se Aprobó Junta Profesionales - Rechazado");
            }
            generarMensajes();
        } catch (ParseException ex) {
            Logger.getLogger(MipresBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardar() {
        boolean hayTotalEntregaCero = false;
        if (direccionamiento.getPrestadorNumeroDocumentoStr() == null || direccionamiento.getPrestadorNumeroDocumentoStr().equalsIgnoreCase("")) {
            addError("Prestador:  este campo es obligatorio");
        }
        if (direccionamiento.getCodigoMpEntrega() == null) {
            addError("Tecnologia:  este campo es obligatorio");
        } else {
            direccionamiento.setCodigoMpEntrega(this.direccionamiento.getCodigoMpEntrega());
        }
        if (direccionamiento.getCodigoMpEntrega() == null || direccionamiento.getCodigoMpEntrega().trim().isEmpty()) {
            addError("Tecnología: este campo es obligatorio");
        } else {
            direccionamiento.setCodigoMpEntrega(this.direccionamiento.getCodigoMpEntrega());
        }
        if (getListadireccionamientos().isEmpty()) {
            addError("Entregas:  este campo es obligatorio");
        } else {
            for (MpDireccionamiento direccionamiento : getListadireccionamientos()) {
                if (direccionamiento.getEntregaTotal() == null || direccionamiento.getEntregaTotal() <= 0) {
                    hayTotalEntregaCero = true;
                    break;
                } else {
                    hayTotalEntregaCero = false;
                }
            }
            if (hayTotalEntregaCero == true) {
                addError("Uno de los registros tiene un valor igual a 0");
            }
        }

        if (direccionamiento.getUbicacionSedeId() == null) {
            addError("Ubicación:  este campo es obligatorio");
        } else {
            direccionamiento.setUbicacionSedeId(this.direccionamiento.getUbicacionSedeId());
            direccionamiento.setUbicacionSedeIdStr(this.direccionamiento.getUbicacionSedeIdStr());
        }
        if (direccionamiento.getEsEntregaParcial()) {
            direccionamiento.setEsEntregaParcial(this.direccionamiento.getEsEntregaParcial());
            if (direccionamiento.getCodigoEntregaParcial() == null) {
                addError("Causa entrega parcial:  este campo es obligatorio");
            } else {
                direccionamiento.setCodigoEntregaParcial(this.direccionamiento.getCodigoEntregaParcial());
            }
        }
        if (direccionamiento.getEsEntregaDiferida()) {
            direccionamiento.setEsEntregaDiferida(this.direccionamiento.getEsEntregaDiferida());
            if (direccionamiento.getCodigoEntregaDiferida() == null) {
                addError("Causa entrega diferida:  este campo es obligatorio");
            } else {
                direccionamiento.setCodigoEntregaDiferida(this.direccionamiento.getCodigoEntregaDiferida());
            }
            if (direccionamiento.getNumeroPrescripcionAso() == null) {
                addError("Prescripción asociada:  este campo es obligatorio");
            } else {

                direccionamiento.setNumeroPrescripcionAso(this.valorAsociado);
            }
            if (direccionamiento.getConsecutivoTecAsociada() == null) {
                addError("Consecutivo tecnologia asociada:  este campo es obligatorio");
            } else {
                direccionamiento.setConsecutivoTecAsociada(this.direccionamiento.getConsecutivoTecAsociada());
            }

            if (direccionamiento.getEnvioCorreoAuto() != false) {
                direccionamiento.setEnvioCorreoAuto(this.direccionamiento.getEnvioCorreoAuto());
            }
            if (direccionamiento.getFechaEnvioAuto() != null) {
                direccionamiento.setFechaEnvioAuto(this.direccionamiento.getFechaEnvioAuto());
            }
        }
        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getMipresServicio().Accion(this);
            crearLog("Guardar Direccionamiento", objeto.toString());
            cerrarModalGestionar();
            addMensaje("Se Realizó Direccionamiento De Manera Exitosa");
        }
        procesoFinal();
    }

    public void guardarDireccionamientoIndividual(MpDireccionamiento objeto, int numero) {

        boolean hayTotalEntregaCero = false;
        MpDireccionamiento obj = direccionamiento;

        if (objeto.getEntregaTotal() == null || objeto.getEntregaTotal() <= 0) {
            addError("Cantidad:  Este Campo Es Obligatorio No Se Permiten Direccionamientos En 0");
        }

        if (obj.getNombreSede() == null || obj.getNombreSede().equalsIgnoreCase("")) {
            addError("Prestador:  Este Campo Es Obligatorio");
        }
        if (obj.getCodigoMpEntrega() == null) {
            addError("Tecnologia:  Este Campo Es Obligatorio");
        } else {
            objeto.setCodigoMpEntrega(obj.getCodigoMpEntrega());
        }
        if (obj.getCodigoMpPropio().equalsIgnoreCase("")) {
            addError("Tecnologia:  Este Campo Es Obligatorio");
        } else {
            objeto.setCodigoMpPropio(obj.getCodigoMpPropio());
        }

        if (obj.getUbicacionSedeId() == null) {
            addError("Ubicación:  Este Campo Es Obligatorio");
        } else {
            objeto.setUbicacionSedeId(obj.getUbicacionSedeId());
            objeto.setUbicacionSedeIdStr(obj.getUbicacionSedeIdStr());
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoDireccionarAnulado').hide()");
            getMipresServicio().guardarDireccionamientoIndividual(objeto, this, numero);
            PrimeFaces.current().executeScript("PF('dlgDireccionamiento').hide()");
            direcciona(this.objeto);

            addMensaje("Se Realizó Direccionamiento De Manera Exitosa");
        } else {
        }
        generarMensajes();
    }

    public void guardarDireccionamientoIndividualAnulado(MpDireccionamiento objeto, int numero) {

        boolean hayTotalEntregaCero = false;
        MpDireccionamiento obj = direccionamientoPorAnulacion;

        if (objeto.getEntregaTotal() == null || objeto.getEntregaTotal() <= 0) {
            addError("Cantidad:  Este Campo Es Obligatorio No Se Permiten Direccionamientos En 0");
        }

        if (obj.getNombreSede() == null || obj.getNombreSede().equalsIgnoreCase("")) {
            addError("Prestador:  Este Campo Es Obligatorio");
        }
        if (obj.getCodigoMpEntrega() == null) {
            addError("Tecnologia:  Este Campo Es Obligatorio");
        } else {
            objeto.setCodigoMpEntrega(obj.getCodigoMpEntrega());
        }
        if (obj.getCodigoMpEntrega().equalsIgnoreCase("")) {
            addError("Tecnologia:  Este Campo Es Obligatorio");
        } else {
            objeto.setCodigoMpEntrega(obj.getCodigoMpEntrega());
        }

        if (obj.getUbicacionSedeId() == null) {
            addError("Ubicación:  Este Campo Es Obligatorio");
        } else {
            objeto.setUbicacionSedeId(obj.getUbicacionSedeId());
            objeto.setUbicacionSedeIdStr(obj.getUbicacionSedeIdStr());
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoDireccionarAnulado').hide()");
            getMipresServicio().guardarDireccionamientoIndividual(objeto, this, numero);
            PrimeFaces.current().executeScript("PF('dlgDireccionamiento').hide()");
            direcciona(this.objeto);
            addMensaje("Se Realizó Direccionamiento De Manera Exitosa");
        }
        generarMensajes();
    }

    public void guardarNoDireccionar() {
        noDireccionado.setJustificacionNoDireccionamiento(noDireccionado.getJustificacionNoDireccionamiento());
        if ("".equals(noDireccionado.getJustificacionNoDireccionamiento()) || noDireccionado.getJustificacionNoDireccionamiento() == null) {
            addError("Justificación No Direccionamiento: este campo es obligatorio");
        }
        if (noDireccionado.getCodigoNoDireccionamiento() <= 0) {
            addError("Causa Nodireccionamiento: Este Campo Es Obligatorio");
        }
        if (noDireccionado.getCodigoNoDireccionamiento() == 1) {
            if (mostrarPrescripcionAsociada) {
                if (noDireccionado.getNumeroPrescripcionAsociada() == null) {
                    addError("Numero Prescripción Asociada: Este Campo Es Obligatorio");
                } else {
                    noDireccionado.setNumeroPrescripcionAsociada(getValorAsociado());
                }
                if (noDireccionado.getConTecAsociada() == null) {
                    addError("Item: Debe Seleccionar Un Consecutivo");
                }
            }
        }
        if (!super.isError()) {
            getMipresServicio().guardarNoDirec(this);
            PrimeFaces.current().ajax().update("frmPrescripciones");
            PrimeFaces.current().executeScript("PF('dlgNoDireccionar').hide()");
            cerrarModalGestionar();
        }
        generarMensajes();
    }

    public void verDesdeGestion() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        listaPrescripcionDto = new ArrayList<>();
        getMipresServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void borradoLogico() {
        super.setAccion(ACCION_ADICIONAL_12);
        getMipresServicio().Accion(this);
        direcciona(this.objeto);
        procesoFinal();
    }

    public void verHistoricoP(int id) {
        listaPrescripcionDtoH = new ArrayList<>();
        getMipresServicio().verHistorico(this, id);
        PrimeFaces.current().ajax().update("frmVerH");
        PrimeFaces.current().executeScript("PF('dialogoVerPh').show()");
        crearLog("Ver Historico Prescripción", prescripcionH.toString());
        generarMensajes();
    }

    public void resetFormularioDireccionamiento() {
        direccionamiento = new MpDireccionamiento();

        if (listadireccionamientos != null) {
            listadireccionamientos.clear();
        }
        PrimeFaces.current().ajax().update("frmDireccionamiento");
    }

    public String obtenerTipoDocumentoPersona(Integer id) {
        try {
            if (id != null && id != 0) {
                return hashTiposDocumentoPersona.get(id).getNombre();
            } else {
                return "";
            }
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public String obtenerHomologacion(String codigo, int tipo) {
        String resultado = "";
        for (MpHomologacion homologacion : listaHomologaciones) {
            if (homologacion.getTipo() == tipo) {
                if (homologacion.getCodigo().equals(codigo)) {
                    resultado = homologacion.getNombre();
                }
            }
        }
        return resultado;
    }

    public String obtenerTipoGenero(String id) {
        String resultado;
        switch (id) {
            case "1":
                resultado = "Masculino";
                break;
            case "2":
                resultado = "Femenino";
                break;
            default:
                resultado = "Sin genero asignado";
                break;
        }
        return resultado;
    }

    public void inicializarFormDir() {
        setActivarBotonMed(false);
        setActivarBotonTec(false);
        setActivarBotonIns(false);
        setMostrarPanelParcial(false);
        setMostrarPanelDiferido(false);
        listaPrescripcionDto = new ArrayList<>();
        direccionamientoDireccionado = new ArrayList<>();
        mostrarTablaItems = false;
        mostrarPrescripcionAsociada = false;

    }

    public String obtenerCobertura(int id) {
        String resultado;
        switch (id) {
            case 1:
                resultado = "PBS";
                break;
            case 2:
                resultado = "NO PBS";
                break;
            case 3:
                resultado = "CONDICIONADO";
                break;
            default:
                resultado = "Sin asignación";
                break;
        }
        return resultado;
    }

    public String obtenerEdad(Date fechaNacimiento) {
        String resultado = "";
        if (fechaNacimiento != null) {
            String fechaNacimientoStr = fechaNacimiento.toString();
            LocalDate fechaNacimientoD = LocalDate.parse(fechaNacimientoStr);
            LocalDate fechaActual = LocalDate.now();
            Period periodo = Period.between(fechaNacimientoD, fechaActual);

            if (periodo.getYears() > 0) {
                resultado = periodo.getYears() + " años";
            } else if (periodo.getMonths() > 0) {
                resultado = periodo.getMonths() + " meses";
            } else {
                resultado = periodo.getDays() + " días";
            }
        } else {
            resultado = "";
        }

        return resultado;
    }

    public String optenerTipoPrestacion(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Única";
                break;
            case 2:
                resultado = "Sucesiva";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerConfirmacion(boolean id) {
        String resultado;
        if (id) {
            resultado = "SI";
        } else {
            resultado = "NO";
        }
        return resultado;
    }

    public boolean validarResiduoDecimal(String valor) {
        BigDecimal valorDecimal;
        if (valor.isEmpty()) {
            return true;
        } else {
            valorDecimal = new BigDecimal(valor);
        }
        return this.validarResiduoDecimal(valorDecimal);
    }

    public boolean validarResiduoDecimal(BigDecimal valor) {
        if (valor.remainder(new BigDecimal(1)).compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }

    public Integer convertirTextoAEntero(String valor) {
        BigDecimal valorDecimal = new BigDecimal(valor);
        return valorDecimal.intValue();
    }

    public String calcularDecimales(BigDecimal valor) {
        if (valor == null) {
            return "-- -- --";
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);

        String valorFormateado = numberFormat.format(valor);

        if (valor.scale() == 0) {
            return valor.toBigInteger().toString();
        } else if (valorFormateado.endsWith(".00")) {
            valorFormateado = valorFormateado.substring(0, valorFormateado.length() - 3);
        }

        return valorFormateado;
    }

    public String obtenerEstadoAfiliacion(int id) {
        try {
            if (id != 0) {
                return hashEstadosAfiliacion.get(id).getNombre();
            } else {
                return "";
            }
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public String valorEstadoF(short id) {
        String valor = "";
        try {
            if (id != 0) {
                if (id == 1) {
                    valor = "Activo";
                } else if (id == 2) {
                    valor = "Procesado";
                } else if (id == 0) {
                    valor = "Anulado";
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            return String.valueOf(id);
        }
        return valor;
    }

    public String obtenerRegimen(String id) {
        String resultado;
        switch (id) {
            case "1":
                resultado = "Subsidiado";
                break;
            case "2":
                resultado = "Contributivo";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerAmbito(String id) {
        String resultado;

        switch (id) {
            case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                resultado = "Ambulatorio - Priorizado";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                resultado = "Ambulatorio - No Priorizado";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                resultado = "Hospitalario - Domiciliario";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_INTERNACION:
                resultado = "Hospitalario - Internacion";
                break;
            case MpPrescripcionDetalle.ID_AMBITO_URGENCIAS:
                resultado = "Urgencias";
                break;
            default:
                resultado = "Recobrante";
                break;
        }
        if (objeto.getReferenciaContra() != null) {
            if (objeto.getReferenciaContra() == true) {
                resultado = resultado + " - Referencia Contrarreferencia";
            }
        }

        return resultado;

    }

    public String obtenerAmbitoHis(String id) {
        String resultado;
        if (id != null && !id.equals("")) {

            switch (id) {
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                    resultado = "Ambulatorio - Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                    resultado = "Ambulatorio - No Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                    resultado = "Hospitalario - Domiciliario";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_INTERNACION:
                    resultado = "Hospitalario - Internacion";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_URGENCIAS:
                    resultado = "Urgencias";
                    break;
                default:
                    resultado = "";
                    break;
            }

        } else {
            resultado = "Recobrante";
        }
        return resultado;
    }

    public String optenerEstadoJunta(int id) {
        String resultado;
        switch (id) {
            case 1:
                resultado = "No Requiere";
                break;
            case 2:
                resultado = "Pendiente";
                break;
            case 3:
                resultado = "Aprobada";
                break;
            case 4:
                resultado = "No Aprobada";
                break;
            default:
                resultado = "Sin Estado Junta";
                break;
        }
        return resultado;
    }

    public String obtenerTipoTecnologia(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO1:
                resultado = "Medicamento";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1:
                resultado = "Procedimientos";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO1:
                resultado = "Dispositivos";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1:
                resultado = "Productos Nutricionales";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1:
                resultado = "Servicios Complementarios";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerTipoPrestacion(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_TIPO_PRESTACION_UNICA:
                resultado = "Unica";
                break;
            case MpDetalleDTO.ID_TIPO_PRESTACION_SUCESIVA:
                resultado = "Sucesiva";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerEstadoJuntaProfesional(int id) {
        String resultado;
        switch (id) {
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_NO_REQUIERE:
                resultado = "No requiere";
                break;
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_PENDIENTE:
                resultado = "Pendiente";
                break;
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_APROBADA:
                resultado = "Aprobada";
                break;
            case MpDetalleDTO.ID_JUNTA_PROFESIONAL_NO_APROBADA:
                resultado = "No Aprobada";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerTipoMedicamento(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_MEDICAMENTO:
                resultado = "Medicamento";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_VITAL_NO_DISPO:
                resultado = "Vital No Disponible";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_PREPARA_MAGISTRAL:
                resultado = "Preparación Magistral";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_UNIRS:
                resultado = "UNIRS";
                break;
            case MpDetalleDTO.ID_TIPO_MEDICAMENTO_URGENCIA_MEDICA:
                resultado = "Urgencia Médica(Solo Transcripción)";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerEstado(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_ESTADO_DIRECCIONADO:
                resultado = "Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_NO_DIRECCIONADO:
                resultado = "No Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_PENDIENTE:
                resultado = "Pendiente";
                break;
            case MpDetalleDTO.ID_ESTADO_ANULADO_DIRECCIONADO:
                resultado = "Auditado - Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_ANULADO_NO_DIRECCIONADO:
                resultado = "Auditado - No direccionamiento";
                break;
            case MpDetalleDTO.ID_ESTADO_PROGRAMADO:
                resultado = "Ampliación justificación no pbs insuficiente";
                break;
            case MpDetalleDTO.ID_ESTADO_ENTREGADO:
                resultado = "Error en la prescripción: cantidad duración tratamiento";
                break;
            case MpDetalleDTO.ID_ESTADO_REPORTADO:
                resultado = "Avalado Hos_Urg";
                break;
            case 9:
                resultado = "No Avalado Hos_Urg";
                break;
            case 10:
                resultado = "No direccionamiento sin causa";
                break;
            case 11:
                resultado = "Pendiente De Cotizaciòn";//No direccionamiento sin causa
                break;
            case 12:
                resultado = "Con Cotización";//No direccionamiento sin causa
                break;
            case 13:
                resultado = "Rechazo De Cotización";//
                break;
            case 14:
                resultado = "Direccionado Con Cotización";//
                break;
            case 15:
                resultado = "Direccionado Sin Cotización";//
                break;
            case 17:
                resultado = "Direccionado - Cotización Activa";//
                break;
            case 18:
                resultado = "Anulaciòn Direccionamiento";//
                break;
            case 19:
                resultado = "Anulaciòn No Direccionamiento";//
                break;
            case 20:
                resultado = "NoDireccionado Sin Cotización";//
                break;
            case 21:
                resultado = "NoDireccionado Con Cotización";//
                break;
            case 27:
                resultado = "Inicio de atencion";
                break;
            case 50:
                resultado = "Direccionamiento parcial";
                break;
            case 51:
                resultado = "Direccionamiento diferido";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerEstadoAuditado(int id) {
        String resultado = "";
        switch (id) {
            case 1:
                resultado = "Auditado - Direccionado";
                break;
            case 2:
                resultado = "Auditado - No direccionamiento";
                break;
            case 3:
                resultado = "Ampliación justificación no pbs insuficiente o invalida para definir el aval de la tecnologí­a";
                break;
            case 5:
                resultado = "Avalado Hos_Urg";
                break;
        }
        return resultado;
    }

    public String obtenerTecno(int id) {
        String resultado = "";
        switch (id) {
            case 1:
                resultado = "Medicamento";
                break;
            case 2:
                resultado = "Procedimiento";
                break;

            case 3:
                resultado = "Dispositivo";
                break;

            case 4:
                resultado = "Producto nutricional";
                break;
            case 5:
                resultado = "Servicio complementario";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String optenerFrecuenciaUso(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Minuto(s)";
                break;
            case 2:
                resultado = "Hora(s)";
                break;
            case 3:
                resultado = "Día(s)";
                break;
            case 4:
                resultado = "Semana(s)";
                break;
            case 5:
                resultado = "Mes(es)";
                break;
            case 6:
                resultado = "Año(s)";
                break;
            case 7:
                resultado = "Seguns respuesta al tratamiento";
                break;
            case 8:
                resultado = "Única";
                break;
            case 0:
                resultado = "Única";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String optenerDuracionTratamiento(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Minuto(s)";
                break;
            case 2:
                resultado = "Hora(s)";
                break;
            case 3:
                resultado = "Día(s)";
                break;
            case 4:
                resultado = "Semana(s)";
                break;
            case 5:
                resultado = "Mes(es)";
                break;
            case 6:
                resultado = "Año(s)";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String optenerViaAdminPNutri(String tipo) {
        String resultado;
        switch (tipo) {
            case "1":
                resultado = "Oral";
                break;
            case "2":
                resultado = "Sonda";
                break;
            default:
                resultado = "N/A";
                break;
        }
        return resultado;
    }

    public String obtenerToolTip(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Prescripción con tecnología medicamento";
                break;
            case 2:
                resultado = "Prescripción con tecnología procedimiento";
                break;
            case 3:
                resultado = "Prescripción con tecnología dispositivos";
                break;
            case 4:
                resultado = "Prescripción con tecnología nutricionales";
                break;
            case 5:
                resultado = "Prescripción con tecnología complementarios";
                break;
            default:
                resultado = "N/A";
                break;
        }
        return resultado;
    }

    public String obtenerToolTipEstado(int tipo) {
        String resultado = "";
        switch (tipo) {
            case 1:
                resultado = "Prescripción direccionada";
                break;
            case 2:
                resultado = "Prescripción No direccionada";
                break;
            case 3:
                resultado = "Prescripción pendiente por direccionar";
                break;
            case 4:
                resultado = "Prescripción direccionada Auditoria";
                break;
            case 5:
                resultado = "Prescripción Anulada";
                break;
            case 6:
                resultado = "Prescripción Anulada";
                break;
            case 7:
                resultado = "Prescripción Anulada";
                break;
            case 8:
                resultado = "Prescripción Anulada";
                break;
            case 9:
                resultado = "Prescripción Anulada";
                break;
            case 10:
                resultado = "Prescripción Anulada";
                break;
            case 11:
                resultado = "Pendiente De Cotizaciòn";
                break;
            case 12:
                resultado = "Con Cotización";
                break;
            case 13:
                resultado = "Rechazo De Cotización";
                break;
            case 14:
                resultado = "Anulado No Direccionamiento";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String optenerPresentacionPNutri(String tipo) {
        String resultado;
        switch (tipo) {
            case "1":
                resultado = "Bolsa";
                break;
            case "2":
                resultado = "Bolsa ultrapack";
                break;
            case "3":
                resultado = "Botella";
                break;
            case "4":
                resultado = "EasyBag";
                break;
            case "5":
                resultado = "Lata";
                break;
            case "6":
                resultado = "LOC";
                break;
            case "7":
                resultado = "LPC";
                break;
            case "8":
                resultado = "LPM";
                break;
            case "9":
                resultado = "Sobre";
                break;
            case "10":
                resultado = "Tetraprisma";
                break;
            case "11":
                resultado = "Ultrapack";
                break;
            case "12":
                resultado = "Frasco";
                break;
            default:
                resultado = "N/A";
                break;
        }
        return resultado;
    }

    public String optenerIndicacionesEspeciales(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Administración en dosis única";
                break;
            case 2:
                resultado = "Administración inmediata";
                break;
            case 3:
                resultado = "Administrar en Bolo";
                break;
            case 4:
                resultado = "Administrar en Goteo";
                break;
            case 5:
                resultado = "Infusión continua";
                break;
            case 6:
                resultado = "Infusión intermitente";
                break;
            case 7:
                resultado = "Infusión intermitente simultánea con perfusión de otra solución";
                break;
            case 8:
                resultado = "Microgoteo";
                break;
            case 9:
                resultado = "Perfusión";
                break;
            case 10:
                resultado = "Sin indicación especial";
                break;
            default:
                resultado = "Sin indicación especial";
                break;
        }
        return resultado;
    }

    public String optenerTipoTransporte(int id) {
        String resultado;
        switch (id) {
            case 1:
                resultado = "Terrestre";
                break;
            case 2:
                resultado = "Maritimo";
                break;
            case 3:
                resultado = "Aereo";
                break;

            default:
                resultado = "Sin transporte asignado";
                break;
        }
        return resultado;
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {

                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().resetInputs("frmVer");
                            PrimeFaces.current().ajax().update("frmVer");
                            PrimeFaces.current().ajax().update("frmAuditoriaVer");
                            crearLog("Ver", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            switch (getObjeto().getTipoTecnologiaItem()) {
                                case 1: {
                                    PrimeFaces.current().resetInputs("frmVerItemMM");
                                    PrimeFaces.current().ajax().update("frmVerItemMM");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemMM");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemMM");
                                    crearLog("Ver Medicamento", getPrescripcionMedicamento().toString());
                                }
                                break;
                                case 4: {
                                    PrimeFaces.current().resetInputs("frmVerItemM");
                                    PrimeFaces.current().ajax().update("frmVerItemM");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemM");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemM");
                                    crearLog("Ver Producto Nutricional", getPrescripcionMedicamento().toString());
                                }
                                break;
                                case 2: {
                                    PrimeFaces.current().resetInputs("frmVerItemT");
                                    PrimeFaces.current().ajax().update("frmVerItemT");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemT");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemT");
                                    crearLog("Ver Procedimiento", getPrescripcionTecnologia().toString());
                                }
                                break;
                                case 3: {
                                    PrimeFaces.current().resetInputs("frmVerItemII");
                                    PrimeFaces.current().ajax().update("frmVerItemII");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemII");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemII");
                                    crearLog("Ver Dispositivo", getPrescripcionInsumo().toString());
                                }
                                break;
                                case 5: {
                                    PrimeFaces.current().resetInputs("frmVerItemI");
                                    PrimeFaces.current().ajax().update("frmVerItemI");
                                    PrimeFaces.current().resetInputs("frmAuditoriaVerItemI");
                                    PrimeFaces.current().ajax().update("frmAuditoriaVerItemI");
                                    crearLog("Ver Servicio Complementario", getPrescripcionInsumo().toString());
                                }
                                break;
                            }
                            break;
                    }
                    break;

                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmPrescripciones");
                    PrimeFaces.current().ajax().update("frmPrescripciones:tablaRegistros");
                    crearLog(getObjeto().toString());
                    break;

                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dlgDireccionamiento').hide()");
                    PrimeFaces.current().ajax().update("frmPrescripciones");
                    break;

                case Url.ACCION_CREAR:
                    PrimeFaces.current().executeScript("PF('dlgAuditar').hide()");
                    PrimeFaces.current().ajax().update("frmPrescripciones");
                    break;

                case Url.ACCION_ADICIONAL_2:
                    break;

                case 'A':
                    crearLog("Gestión - Inicio Atención", getObjeto().toString());
                    break;

                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmDireccionamiento");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmAuditoria");
                            PrimeFaces.current().executeScript("PF('dlgAuditar').show()");
                            if (refrescarAtencion == true) {
                                PrimeFaces.current().ajax().update("frmPrescripciones");
                            }
                            crearLog("Gestión - Auditar", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmPrescripciones");
                            PrimeFaces.current().ajax().update("frmPrescripciones:tablaRegistros");
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        generarMensajes();

    }

    public MipresIface getMipresServicio() {
        return MipresServicio;
    }

    public void setMipresServicio(MipresIface MipresServicio) {
        this.MipresServicio = MipresServicio;
    }

    public MpPrescripcionDetalle getObjeto() {
        return objeto;
    }

    public void setObjeto(MpPrescripcionDetalle objeto) {
        this.objeto = objeto;
    }

    public MpPrescripcionDetalle getObjetoRespaldo() {
        return objetoRespaldo;
    }

    public void setObjetoRespaldo(MpPrescripcionDetalle objetoRespaldo) {
        this.objetoRespaldo = objetoRespaldo;
    }

    public List<MpPrescripcionDetalle> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MpPrescripcionDetalle> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MpPrescripcionDetalle> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MpPrescripcionDetalle> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public LazyDataModel<CntPrestador> getLazyPrestador() {
        return lazyPrestador;
    }

    public void setLazyPrestador(LazyDataModel<CntPrestador> lazyPrestador) {
        this.lazyPrestador = lazyPrestador;
    }

    public MpDireccionamiento getDireccionamiento() {
        return direccionamiento;
    }

    public void setDireccionamiento(MpDireccionamiento direccionamiento) {
        this.direccionamiento = direccionamiento;
    }

    public MpDireccionamiento getDireccionamientoPorAnulacion() {
        return direccionamientoPorAnulacion;
    }

    public void setDireccionamientoPorAnulacion(MpDireccionamiento direccionamientoPorAnulacion) {
        this.direccionamientoPorAnulacion = direccionamientoPorAnulacion;
    }

    public MpPrescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(MpPrescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    public List<MpPrescripcion> getPrescripcionListaHistoricos() {
        return prescripcionListaHistoricos;
    }

    public void setPrescripcionListaHistoricos(List<MpPrescripcion> prescripcionListaHistoricos) {
        this.prescripcionListaHistoricos = prescripcionListaHistoricos;
    }

    public AuCotizacion getObjetoCotizacion() {
        return objetoCotizacion;
    }

    public void setObjetoCotizacion(AuCotizacion objetoCotizacion) {
        this.objetoCotizacion = objetoCotizacion;
    }

    public MpCotizacionDetalle getObjetoCotizacionDetalle() {
        return objetoCotizacionDetalle;
    }

    public void setObjetoCotizacionDetalle(MpCotizacionDetalle objetoCotizacionDetalle) {
        this.objetoCotizacionDetalle = objetoCotizacionDetalle;
    }

    public MpPrescripcion getPrescripcionSeleccionada() {
        return prescripcionSeleccionada;
    }

    public void setPrescripcionSeleccionada(MpPrescripcion prescripcionSeleccionada) {
        this.prescripcionSeleccionada = prescripcionSeleccionada;
    }

    public MpDetalleDTO getPrescripcionDto() {
        return prescripcionDto;
    }

    public void setPrescripcionDto(MpDetalleDTO prescripcionDto) {
        this.prescripcionDto = prescripcionDto;
    }

    public MpPrescripcion getPrescripcionH() {
        return prescripcionH;
    }

    public void setPrescripcionH(MpPrescripcion prescripcionH) {
        this.prescripcionH = prescripcionH;
    }

    public MpDetalleDTO getPrescripcionDtoH() {
        return prescripcionDtoH;
    }

    public void setPrescripcionDtoH(MpDetalleDTO prescripcionDtoH) {
        this.prescripcionDtoH = prescripcionDtoH;
    }

    public List<MpDetalleDTO> getListaPrescripcionDtoH() {
        return listaPrescripcionDtoH;
    }

    public void setListaPrescripcionDtoH(List<MpDetalleDTO> listaPrescripcionDtoH) {
        this.listaPrescripcionDtoH = listaPrescripcionDtoH;
    }

    public MpPrescripcionTecnologia getPrescripcionTecnologia() {
        return prescripcionTecnologia;
    }

    public void setPrescripcionTecnologia(MpPrescripcionTecnologia prescripcionTecnologia) {
        this.prescripcionTecnologia = prescripcionTecnologia;
    }

    public MpPrescripcionInsumo getPrescripcionInsumo() {
        return prescripcionInsumo;
    }

    public void setPrescripcionInsumo(MpPrescripcionInsumo prescripcionInsumo) {
        this.prescripcionInsumo = prescripcionInsumo;
    }

    public MpPrescripcionMedicamento getPrescripcionMedicamento() {
        return prescripcionMedicamento;
    }

    public void setPrescripcionMedicamento(MpPrescripcionMedicamento prescripcionMedicamento) {
        this.prescripcionMedicamento = prescripcionMedicamento;
    }

    public MpPrescripcionRecobrante getPrescripcionRecobrante() {
        return prescripcionRecobrante;
    }

    public void setPrescripcionRecobrante(MpPrescripcionRecobrante prescripcionRecobrante) {
        this.prescripcionRecobrante = prescripcionRecobrante;
    }

    public MpPrescripcionRecobrante getPrescripcionRecobranteH() {
        return prescripcionRecobranteH;
    }

    public void setPrescripcionRecobranteH(MpPrescripcionRecobrante prescripcionRecobranteH) {
        this.prescripcionRecobranteH = prescripcionRecobranteH;
    }

    public MpPrescripcionProgramada getPrescripcionProgramada() {
        return prescripcionProgramada;
    }

    public void setPrescripcionProgramada(MpPrescripcionProgramada prescripcionProgramada) {
        this.prescripcionProgramada = prescripcionProgramada;
    }

    public List<MpPrescripcionProgramada> getListaPrescripcionProgramada() {
        return listaPrescripcionProgramada;
    }

    public void setListaPrescripcionProgramada(List<MpPrescripcionProgramada> listaPrescripcionProgramada) {
        this.listaPrescripcionProgramada = listaPrescripcionProgramada;
    }

    public MpNotificacionHistorico getNotificacionHistorico() {
        return notificacionHistorico;
    }

    public void setNotificacionHistorico(MpNotificacionHistorico notificacionHistorico) {
        this.notificacionHistorico = notificacionHistorico;
    }

    public List<MpNotificacionHistorico> getListaNotificacionHistorico() {
        return listaNotificacionHistorico;
    }

    public void setListaNotificacionHistorico(List<MpNotificacionHistorico> listaNotificacionHistorico) {
        this.listaNotificacionHistorico = listaNotificacionHistorico;
    }

    public MpPrescripcionAuditoria getPrescripcionAuditoria() {
        return prescripcionAuditoria;
    }

    public void setPrescripcionAuditoria(MpPrescripcionAuditoria prescripcionAuditoria) {
        this.prescripcionAuditoria = prescripcionAuditoria;
    }

    public MpPrescripcionAuditoria getPrescripcionAuditoriaRespaldo() {
        return prescripcionAuditoriaRespaldo;
    }

    public void setPrescripcionAuditoriaRespaldo(MpPrescripcionAuditoria prescripcionAuditoriaRespaldo) {
        this.prescripcionAuditoriaRespaldo = prescripcionAuditoriaRespaldo;
    }

    public List<MpPrescripcionAuditoria> getListaPrescripcionAuditoriaRespaldo() {
        return listaPrescripcionAuditoriaRespaldo;
    }

    public void setListaPrescripcionAuditoriaRespaldo(List<MpPrescripcionAuditoria> listaPrescripcionAuditoriaRespaldo) {
        this.listaPrescripcionAuditoriaRespaldo = listaPrescripcionAuditoriaRespaldo;
    }

    public boolean isTieneAuditoria() {
        return tieneAuditoria;
    }

    public void setTieneAuditoria(boolean tieneAuditoria) {
        this.tieneAuditoria = tieneAuditoria;
    }

    public MpPrescripcionHistorico getPrescripcionHistorico() {
        return prescripcionHistorico;
    }

    public void setPrescripcionHistorico(MpPrescripcionHistorico prescripcionHistorico) {
        this.prescripcionHistorico = prescripcionHistorico;
    }

    public List<MpMedicamentoIndicacionUnirs> getListaMpIndicacionUnirs() {
        return listaMpIndicacionUnirs;
    }

    public void setListaMpIndicacionUnirs(List<MpMedicamentoIndicacionUnirs> listaMpIndicacionUnirs) {
        this.listaMpIndicacionUnirs = listaMpIndicacionUnirs;
    }

    public MpMedicamentoIndicacionUnirs getIndicacionUnirs() {
        return indicacionUnirs;
    }

    public void setIndicacionUnirs(MpMedicamentoIndicacionUnirs indicacionUnirs) {
        this.indicacionUnirs = indicacionUnirs;
    }

    public List<MpPrescripcionHistorico> getListaPrescripcionHistoricos() {
        return listaPrescripcionHistoricos;
    }

    public void setListaPrescripcionHistoricos(List<MpPrescripcionHistorico> listaPrescripcionHistoricos) {
        this.listaPrescripcionHistoricos = listaPrescripcionHistoricos;
    }

    public MpProgramadaEntrega getProgramadaEntrega() {
        return programadaEntrega;
    }

    public void setProgramadaEntrega(MpProgramadaEntrega programadaEntrega) {
        this.programadaEntrega = programadaEntrega;
    }

    public List<MpProgramadaEntrega> getListaprogramadaEntrega() {
        return listaprogramadaEntrega;
    }

    public void setListaprogramadaEntrega(List<MpProgramadaEntrega> listaprogramadaEntrega) {
        this.listaprogramadaEntrega = listaprogramadaEntrega;
    }

    public List<MpDetalleDTO> getListaPrescripcionDto() {
        return listaPrescripcionDto;
    }

    public void setListaPrescripcionDto(List<MpDetalleDTO> listaPrescripcionDto) {
        this.listaPrescripcionDto = listaPrescripcionDto;
    }

    public List<MpDetalleDTO> getListaPrescripcionDtoRespaldo() {
        return listaPrescripcionDtoRespaldo;
    }

    public void setListaPrescripcionDtoRespaldo(List<MpDetalleDTO> listaPrescripcionDtoRespaldo) {
        this.listaPrescripcionDtoRespaldo = listaPrescripcionDtoRespaldo;
    }

    public List<MpMedicamentoPrincipioActivo> getListaPrincipiosActivos() {
        return listaPrincipiosActivos;
    }

    public void setListaPrincipiosActivos(List<MpMedicamentoPrincipioActivo> listaPrincipiosActivos) {
        this.listaPrincipiosActivos = listaPrincipiosActivos;
    }

    public List<MpHomologacion> getListaHomologaciones() {
        return listaHomologaciones;
    }

    public void setListaHomologaciones(List<MpHomologacion> listaHomologaciones) {
        this.listaHomologaciones = listaHomologaciones;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoPersona() {
        return hashTiposDocumentoPersona;
    }

    public void setHashTiposDocumentoPersona(HashMap<Integer, Maestro> hashTiposDocumentoPersona) {
        this.hashTiposDocumentoPersona = hashTiposDocumentoPersona;
    }

    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<SelectItem> getCausasNoDireccionamiento() {
        if (objeto.getTipoTecnologiaItem() != null) {
            causasNoDireccionamiento = inicializarCausasNoDireccionamiento(objeto.getTipoTecnologiaItem());
        }
        return causasNoDireccionamiento;
    }

    public void setCausasNoDireccionamiento(List<SelectItem> causasNoDireccionamiento) {
        this.causasNoDireccionamiento = causasNoDireccionamiento;
    }

    public List<SelectItem> getCausasNoDireccionamientoParcial() {
        if (objeto.getTipoTecnologiaItem() != null) {
            causasNoDireccionamientoParcial = inicializarCausasNoDireccionamientoParcial(objeto.getTipoTecnologiaItem());
        }
        return causasNoDireccionamientoParcial;
    }

    public void setCausasNoDireccionamientoParcial(List<SelectItem> causasNoDireccionamientoParcial) {
        this.causasNoDireccionamientoParcial = causasNoDireccionamientoParcial;
    }

    public List<SelectItem> getCausasNoDireccionamientoDiferido() {
        if (objeto.getTipoTecnologiaItem() != null) {
            causasNoDireccionamientoDiferido = inicializarCausasNoDireccionamientoDiferido(objeto.getTipoTecnologiaItem());
        }
        return causasNoDireccionamientoDiferido;
    }

    public void setCausasNoDireccionamientoDiferido(List<SelectItem> causasNoDireccionamientoDiferido) {
        this.causasNoDireccionamientoDiferido = causasNoDireccionamientoDiferido;
    }

    public List<SelectItem> getListaPrescripcionesAsociadas() {
        return listaPrescripcionesAsociadas;
    }

    public void setListaPrescripcionesAsociadas(List<SelectItem> listaPrescripcionesAsociadas) {
        this.listaPrescripcionesAsociadas = listaPrescripcionesAsociadas;
    }

    public boolean isTienePrincipioActivo() {
        return tienePrincipioActivo;
    }

    public void setTienePrincipioActivo(boolean tienePrincipioActivo) {
        this.tienePrincipioActivo = tienePrincipioActivo;
    }

    public boolean isTieneNoDireccionamientos() {
        return tieneNoDireccionamientos;
    }

    public void setTieneNoDireccionamientos(boolean tieneNoDireccionamientos) {
        this.tieneNoDireccionamientos = tieneNoDireccionamientos;
    }

    public boolean isTieneDireccionamiento() {
        return tieneDireccionamiento;
    }

    public void setTieneDireccionamiento(boolean tieneDireccionamiento) {
        this.tieneDireccionamiento = tieneDireccionamiento;
    }

    public boolean isTieneEntregas() {
        return tieneEntregas;
    }

    public void setTieneEntregas(boolean tieneEntregas) {
        this.tieneEntregas = tieneEntregas;
    }

    public List<CntPrestador> getRegistroPrestadores() {
        return registroPrestadores;
    }

    public void setRegistroPrestadores(List<CntPrestador> registroPrestadores) {
        this.registroPrestadores = registroPrestadores;
    }

    public List<CntContratoDetalle> getRegistroDetalleContrato() {
        return registroDetalleContrato;
    }

    public void setRegistroDetalleContrato(List<CntContratoDetalle> registroDetalleContrato) {
        this.registroDetalleContrato = registroDetalleContrato;
    }

    public boolean isRefrescarAtencion() {
        return refrescarAtencion;
    }

    public void setRefrescarAtencion(boolean refrescarAtencion) {
        this.refrescarAtencion = refrescarAtencion;
    }

    public boolean isRenderizarPanelNotificar() {
        return renderizarPanelNotificar;
    }

    public void setRenderizarPanelNotificar(boolean renderizarPanelNotificar) {
        this.renderizarPanelNotificar = renderizarPanelNotificar;
    }

    public boolean isNotificarPaciente() {
        return notificarPaciente;
    }

    public void setNotificarPaciente(boolean notificarPaciente) {
        this.notificarPaciente = notificarPaciente;
    }

    public boolean isNotificarPrestador() {
        return notificarPrestador;
    }

    public void setNotificarPrestador(boolean notificarPrestador) {
        this.notificarPrestador = notificarPrestador;
    }

    public boolean isNotificarCorreoPaciente() {
        return notificarCorreoPaciente;
    }

    public void setNotificarCorreoPaciente(boolean notificarCorreoPaciente) {
        this.notificarCorreoPaciente = notificarCorreoPaciente;
    }

    public boolean isNotificarCorreoPrestador() {
        return notificarCorreoPrestador;
    }

    public void setNotificarCorreoPrestador(boolean notificarCorreoPrestador) {
        this.notificarCorreoPrestador = notificarCorreoPrestador;
    }

    public boolean isNotificarSmsPaciente() {
        return notificarSmsPaciente;
    }

    public void setNotificarSmsPaciente(boolean notificarSmsPaciente) {
        this.notificarSmsPaciente = notificarSmsPaciente;
    }

    public boolean isNotificarSmsPrestador() {
        return notificarSmsPrestador;
    }

    public void setNotificarSmsPrestador(boolean notificarSmsPrestador) {
        this.notificarSmsPrestador = notificarSmsPrestador;
    }

    public String getCorreoPrestador() {
        return correoPrestador;
    }

    public void setCorreoPrestador(String correoPrestador) {
        this.correoPrestador = correoPrestador;
    }

    public String getTelefonoPrestador() {
        return telefonoPrestador;
    }

    public void setTelefonoPrestador(String telefonoPrestador) {
        this.telefonoPrestador = telefonoPrestador;
    }

    public String getCorreoPaciente() {
        return correoPaciente;
    }

    public void setCorreoPaciente(String correoPaciente) {
        this.correoPaciente = correoPaciente;
    }

    public String getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public ParamConsulta getParamConsultaPrestador() {
        return paramConsultaPrestador;
    }

    public void setParamConsultaPrestador(ParamConsulta paramConsultaPrestador) {
        this.paramConsultaPrestador = paramConsultaPrestador;
    }

    public LazyDataModel<CntPrestadorSede> getLazyPrestadorSede() {
        return lazyPrestadorSede;
    }

    public void setLazyPrestadorSede(LazyDataModel<CntPrestadorSede> lazyPrestadorSede) {
        this.lazyPrestadorSede = lazyPrestadorSede;
    }

    public LazyDataModel<CntContratoDetalle> getLazyContratoDetalle() {
        return lazyContratoDetalle;
    }

    public void setLazyContratoDetalle(LazyDataModel<CntContratoDetalle> lazyContratoDetalle) {
        this.lazyContratoDetalle = lazyContratoDetalle;
    }

    public LazyDataModel<MaMedicamento> getLazyMaMedicamento() {
        return lazyMaMedicamento;
    }

    public ParamConsulta getParamConsultaMaMedicemento() {
        if (paramConsultaMaMedicemento == null) {
            paramConsultaMaMedicemento = new ParamConsulta();
        }
        return paramConsultaMaMedicemento;
    }

    public void setParamConsultaMaMedicemento(ParamConsulta paramConsultaMaMedicemento) {
        this.paramConsultaMaMedicemento = paramConsultaMaMedicemento;
    }

    public ParamConsulta getParamConsultaMaInsumo() {
        if (paramConsultaMaInsumo == null) {
            paramConsultaMaInsumo = new ParamConsulta();
        }
        return paramConsultaMaInsumo;
    }

    public void setParamConsultaMaInsumo(ParamConsulta paramConsultaMaInsumo) {
        this.paramConsultaMaInsumo = paramConsultaMaInsumo;
    }

    public ParamConsulta getParamConsultaMaPaquete() {
        if (paramConsultaMaPaquete == null) {
            paramConsultaMaPaquete = new ParamConsulta();
        }
        return paramConsultaMaPaquete;
    }

    public void setParamConsultaMaPaquete(ParamConsulta paramConsultaMaPaquete) {
        this.paramConsultaMaPaquete = paramConsultaMaPaquete;
    }

    public void setLazyMaMedicamento(LazyDataModel<MaMedicamento> lazyMaMedicamento) {
        this.lazyMaMedicamento = lazyMaMedicamento;
    }

    public LazyDataModel<MaInsumo> getLazyMaInsumo() {
        return lazyMaInsumo;
    }

    public void setLazyMaInsumo(LazyDataModel<MaInsumo> lazyMaInsumo) {
        this.lazyMaInsumo = lazyMaInsumo;
    }

    public LazyDataModel<MaInsumoMipres> getLazyMaInsumoMipres() {
        return lazyMaInsumoMipres;
    }

    public void setLazyMaInsumoMipres(LazyDataModel<MaInsumoMipres> lazyMaInsumoMipres) {
        this.lazyMaInsumoMipres = lazyMaInsumoMipres;
    }

    public LazyDataModel<MaPaqueteMipres> getLazyMaPaqueteMipres() {
        return lazyMaPaqueteMipres;
    }

    public void setLazyMaPaqueteMipres(LazyDataModel<MaPaqueteMipres> lazyMaPaqueteMipres) {
        this.lazyMaPaqueteMipres = lazyMaPaqueteMipres;
    }

    public LazyDataModel<MaTecnologiaMipres> getLazyMaTecnologiaMipres() {
        return lazyMaTecnologiaMipres;
    }

    public void setLazyMaTecnologiaMipres(LazyDataModel<MaTecnologiaMipres> lazyMaTecnologiaMipres) {
        this.lazyMaTecnologiaMipres = lazyMaTecnologiaMipres;
    }

    public List<MaMedicamento> getRegistroMaMedicamento() {
        return registroMaMedicamento;
    }

    public void setRegistroMaMedicamento(List<MaMedicamento> registroMaMedicamento) {
        this.registroMaMedicamento = registroMaMedicamento;
    }

    public Integer getIdPrescripcionRe() {
        return idPrescripcionRe;
    }

    public void setIdPrescripcionRe(Integer idPrescripcionRe) {
        this.idPrescripcionRe = idPrescripcionRe;
    }

    public String getNumeroPrescripcionRe() {
        return numeroPrescripcionRe;
    }

    public void setNumeroPrescripcionRe(String numeroPrescripcionRe) {
        this.numeroPrescripcionRe = numeroPrescripcionRe;
    }

    public String getRegimenRe() {
        return regimenRe;
    }

    public void setRegimenRe(String regimenRe) {
        this.regimenRe = regimenRe;
    }

    public Boolean getTieneCotizacion() {
        return tieneCotizacion;
    }

    public void setTieneCotizacion(Boolean tieneCotizacion) {
        this.tieneCotizacion = tieneCotizacion;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Boolean getBorradoLogico() {
        return borradoLogico;
    }

    public void setBorradoLogico(Boolean borradoLogico) {
        this.borradoLogico = borradoLogico;
    }

    public LazyDataModel<MaTecnologia> getLazyMaTecnologia() {
        return lazyMaTecnologia;
    }

    public void setLazyMaTecnologia(LazyDataModel<MaTecnologia> lazyMaTecnologia) {
        this.lazyMaTecnologia = lazyMaTecnologia;
    }

    public List<MaTecnologia> getRegistroMaTecnologia() {
        return registroMaTecnologia;
    }

    public void setRegistroMaTecnologia(List<MaTecnologia> registroMaTecnologia) {
        this.registroMaTecnologia = registroMaTecnologia;
    }

    public List<MaTecnologiaMipres> getRegistroMaTecnologiaMipres() {
        return registroMaTecnologiaMipres;
    }

    public void setRegistroMaTecnologiaMipres(List<MaTecnologiaMipres> registroMaTecnologiaMipres) {
        this.registroMaTecnologiaMipres = registroMaTecnologiaMipres;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public ParamConsulta getParamConsultaMaTecnologia() {
        if (paramConsultaMaTecnologia == null) {
            paramConsultaMaTecnologia = new ParamConsulta();
        }
        return paramConsultaMaTecnologia;
    }

    public void setParamConsultaMaTecnologia(ParamConsulta paramConsultaMaTecnologia) {
        this.paramConsultaMaTecnologia = paramConsultaMaTecnologia;
    }

    public ParamConsulta getParamConsultaMaTecnologiaMipres() {
        if (paramConsultaMaTecnologiaMipres == null) {
            paramConsultaMaTecnologiaMipres = new ParamConsulta();
        }
        return paramConsultaMaTecnologiaMipres;
    }

    public void setParamConsultaMaTecnologiaMipres(ParamConsulta paramConsultaMaTecnologiaMipres) {
        this.paramConsultaMaTecnologiaMipres = paramConsultaMaTecnologiaMipres;
    }

    public MpDireccionamientoEntregado getDireccionamientoEntregado() {
        return direccionamientoEntregado;
    }

    public void setDireccionamientoEntregado(MpDireccionamientoEntregado direccionamientoEntregado) {
        this.direccionamientoEntregado = direccionamientoEntregado;
    }

    public MpPrescripcionSuministro getPrescripcionSuministro() {
        return prescripcionSuministro;
    }

    public void setPrescripcionSuministro(MpPrescripcionSuministro prescripcionSuministro) {
        this.prescripcionSuministro = prescripcionSuministro;
    }

    public List<MaInsumo> getRegistroMaInsumo() {
        return registroMaInsumo;
    }

    public void setRegistroMaInsumo(List<MaInsumo> registroMaInsumo) {
        this.registroMaInsumo = registroMaInsumo;
    }

    public List<MaInsumoMipres> getRegistroMaInsumoMipres() {
        return registroMaInsumoMipres;
    }

    public void setRegistroMaInsumoMipres(List<MaInsumoMipres> registroMaInsumoMipres) {
        this.registroMaInsumoMipres = registroMaInsumoMipres;
    }

    public List<MaPaqueteMipres> getRegistroMaPaqueteMipres() {
        return registroMaPaqueteMipres;
    }

    public void setRegistroMaPaqueteMipres(List<MaPaqueteMipres> registroMaPaqueteMipres) {
        this.registroMaPaqueteMipres = registroMaPaqueteMipres;
    }

    public List<CntPrestadorSede> getRegistroPrestadorSede() {
        return registroPrestadorSede;
    }

    public void setRegistroPrestadorSede(List<CntPrestadorSede> registroPrestadorSede) {
        this.registroPrestadorSede = registroPrestadorSede;
    }

    public ParamConsulta getParamConsultaPrestadorSede() {
        if (paramConsultaPrestadorSede == null) {
            paramConsultaPrestadorSede = new ParamConsulta();
        }
        return paramConsultaPrestadorSede;
    }

    public ParamConsulta getParamConsultaContratoDetalle() {
        if (paramConsultaContratoDetalle == null) {
            paramConsultaContratoDetalle = new ParamConsulta();
        }
        return paramConsultaContratoDetalle;
    }

    public void setParamConsultaContratoDetalle(ParamConsulta paramConsultaContratoDetalle) {
        this.paramConsultaContratoDetalle = paramConsultaContratoDetalle;
    }

    public void setParamConsultaPrestadorSede(ParamConsulta paramConsultaPrestadorSede) {
        this.paramConsultaPrestadorSede = paramConsultaPrestadorSede;
    }

    public List<MpDireccionamiento> getListadireccionamientos() {
        return listadireccionamientos;
    }

    public void setListadireccionamientos(List<MpDireccionamiento> listadireccionamientos) {
        this.listadireccionamientos = listadireccionamientos;
    }

    public List<MpDireccionamiento> getDireccionamientoDireccionado() {
        return direccionamientoDireccionado;
    }

    public void setDireccionamientoDireccionado(List<MpDireccionamiento> direccionamientoDireccionado) {
        this.direccionamientoDireccionado = direccionamientoDireccionado;
    }

    public MpEntregaFactura getEntregaFactura() {
        return entregaFactura;
    }

    public void setEntregaFactura(MpEntregaFactura entregaFactura) {
        this.entregaFactura = entregaFactura;
    }

    public List<MpDireccionamiento> getListadireccionamientosAux() {
        return listadireccionamientosAux;
    }

    public void setListadireccionamientosAux(List<MpDireccionamiento> listadireccionamientosAux) {
        this.listadireccionamientosAux = listadireccionamientosAux;
    }

    public List<MpDireccionamientoEntregado> getListaDireccionamientosEntregados() {
        return listaDireccionamientosEntregados;
    }

    public void setListaDireccionamientosEntregados(List<MpDireccionamientoEntregado> listaDireccionamientosEntregados) {
        this.listaDireccionamientosEntregados = listaDireccionamientosEntregados;
    }

    public MpNoDireccionado getNoDireccionado() {
        return noDireccionado;
    }

    public void setNoDireccionado(MpNoDireccionado noDireccionado) {
        this.noDireccionado = noDireccionado;
    }

    public MpAnuladaPrescripcion getMpPrescripcionAnulada() {
        return MpPrescripcionAnulada;
    }

    public void setMpPrescripcionAnulada(MpAnuladaPrescripcion MpPrescripcionAnulada) {
        this.MpPrescripcionAnulada = MpPrescripcionAnulada;
    }

    public MpEntregaSuministro getMpEntregaSuministro() {
        return mpEntregaSuministro;
    }

    public void setMpEntregaSuministro(MpEntregaSuministro mpEntregaSuministro) {
        this.mpEntregaSuministro = mpEntregaSuministro;
    }

    public List<MpNoDireccionado> getListanoDireccionados() {
        return listanoDireccionados;
    }

    public void setListanoDireccionados(List<MpNoDireccionado> listanoDireccionados) {
        this.listanoDireccionados = listanoDireccionados;
    }

    public int getNumeroDeEntregasCalculada() {
        return numeroDeEntregasCalculada;
    }

    public void setNumeroDeEntregasCalculada(int numeroDeEntregasCalculada) {
        this.numeroDeEntregasCalculada = numeroDeEntregasCalculada;
    }

    public List<Integer> getCantidadesParaEntregar() {
        return cantidadesParaEntregar;
    }

    public void setCantidadesParaEntregar(List<Integer> cantidadesParaEntregar) {
        this.cantidadesParaEntregar = cantidadesParaEntregar;
    }

    public boolean isMostrarBotonDireccionamiento() {
        return mostrarBotonDireccionamiento;
    }

    public void setMostrarBotonDireccionamiento(boolean mostrarBotonDireccionamiento) {
        this.mostrarBotonDireccionamiento = mostrarBotonDireccionamiento;
    }

    public boolean isActivarBotonMed() {
        return activarBotonMed;
    }

    public void setActivarBotonMed(boolean activarBotonMed) {
        this.activarBotonMed = activarBotonMed;
    }

    public boolean isActivarBotonTec() {
        return activarBotonTec;
    }

    public void setActivarBotonTec(boolean activarBotonTec) {
        this.activarBotonTec = activarBotonTec;
    }

    public boolean isActivarBotonMpTec() {
        return activarBotonMpTec;
    }

    public void setActivarBotonMpTec(boolean activarBotonMpTec) {
        this.activarBotonMpTec = activarBotonMpTec;
    }

    public boolean isActivarBotonIns() {
        return activarBotonIns;
    }

    public void setActivarBotonIns(boolean activarBotonIns) {
        this.activarBotonIns = activarBotonIns;
    }

    public boolean isMostrarBotonAuditar() {
        return mostrarBotonAuditar;
    }

    public void setMostrarBotonAuditar(boolean mostrarBotonAuditar) {
        this.mostrarBotonAuditar = mostrarBotonAuditar;
    }

    public boolean isMostrarBotonNoDireccionamiento() {
        return mostrarBotonNoDireccionamiento;
    }

    public void setMostrarBotonNoDireccionamiento(boolean mostrarBotonNoDireccionamiento) {
        this.mostrarBotonNoDireccionamiento = mostrarBotonNoDireccionamiento;
    }

    public boolean isMostrarPrescripcionAsociada() {
        return mostrarPrescripcionAsociada;
    }

    public void setMostrarPrescripcionAsociada(boolean mostrarPrescripcionAsociada) {
        this.mostrarPrescripcionAsociada = mostrarPrescripcionAsociada;
    }

    public boolean isMostrarTablaItems() {
        return mostrarTablaItems;
    }

    public void setMostrarTablaItems(boolean mostrarTablaItems) {
        this.mostrarTablaItems = mostrarTablaItems;
    }

    public boolean isMostrarPanelParcial() {
        return mostrarPanelParcial;
    }

    public void setMostrarPanelParcial(boolean mostrarPanelParcial) {
        this.mostrarPanelParcial = mostrarPanelParcial;
    }

    public boolean isMostrarPanelDiferido() {
        return mostrarPanelDiferido;
    }

    public void setMostrarPanelDiferido(boolean mostrarPanelDiferido) {
        this.mostrarPanelDiferido = mostrarPanelDiferido;
    }

    public boolean isMostrarBotonParcial() {
        return mostrarBotonParcial;
    }

    public String getValorAsociado() {
        return valorAsociado;
    }

    public void setValorAsociado(String valorAsociado) {
        this.valorAsociado = valorAsociado;
    }

    public void setMostrarBotonParcial(boolean mostrarBotonParcial) {
        this.mostrarBotonParcial = mostrarBotonParcial;
    }

    public boolean isMostrarBotonDiferido() {
        return mostrarBotonDiferido;
    }

    public void setMostrarBotonDiferido(boolean mostrarBotonDiferido) {
        this.mostrarBotonDiferido = mostrarBotonDiferido;
    }

    public boolean isMostrarAlertaPrimeraV() {
        return mostrarAlertaPrimeraV;
    }

    public void setMostrarAlertaPrimeraV(boolean mostrarAlertaPrimeraV) {
        this.mostrarAlertaPrimeraV = mostrarAlertaPrimeraV;
    }

    public Integer getNumeroDeTutelas() {
        return numeroDeTutelas;
    }

    public Integer getNumeroDeTutelasH() {
        return numeroDeTutelasH;
    }

    public void setNumeroDeTutelasH(Integer numeroDeTutelasH) {
        this.numeroDeTutelasH = numeroDeTutelasH;
    }

    public void setNumeroDeTutelas(Integer numeroDeTutelas) {
        this.numeroDeTutelas = numeroDeTutelas;
    }

    public boolean isRequiereAnulacion() {
        return requiereAnulacion;
    }

    public void setRequiereAnulacion(boolean requiereAnulacion) {
        this.requiereAnulacion = requiereAnulacion;
    }

    public boolean isRenderizarPanelAnular() {
        return renderizarPanelAnular;
    }

    public boolean isTieneAnulacion() {
        return tieneAnulacion;
    }

    public void setTieneAnulacion(boolean tieneAnulacion) {
        this.tieneAnulacion = tieneAnulacion;
    }

    public void setRenderizarPanelAnular(boolean renderizarPanelAnular) {
        this.renderizarPanelAnular = renderizarPanelAnular;
    }

    public Integer getConsecutivoDireccionado() {
        return consecutivoDireccionado;
    }

    public void setConsecutivoDireccionado(Integer consecutivoDireccionado) {
        this.consecutivoDireccionado = consecutivoDireccionado;
    }

    public Integer getNumDirecionamientoDireccionado() {
        return numDirecionamientoDireccionado;
    }

    public void setNumDirecionamientoDireccionado(Integer numDirecionamientoDireccionado) {
        this.numDirecionamientoDireccionado = numDirecionamientoDireccionado;
    }

    public Integer getDisponibleMasAnulado() {
        return disponibleMasAnulado;
    }

    public void setDisponibleMasAnulado(Integer disponibleMasAnulado) {
        this.disponibleMasAnulado = disponibleMasAnulado;
    }

    public Integer getReservaValorAnterior() {
        return reservaValorAnterior;
    }

    public void setReservaValorAnterior(Integer reservaValorAnterior) {
        this.reservaValorAnterior = reservaValorAnterior;
    }

    public String getNumeroPrescripcionStr() {
        return numeroPrescripcionStr;
    }

    public void setNumeroPrescripcionStr(String numeroPrescripcionStr) {
        this.numeroPrescripcionStr = numeroPrescripcionStr;
    }

    public List<SelectItem> getJuntaOpt() {
        return juntaOpt;
    }

    public void setJuntaOpt(List<SelectItem> juntaOpt) {
        this.juntaOpt = juntaOpt;
    }

    public List<SelectItem> getEstadoOpt() {
        return estadoOpt;
    }

    public List<Integer> getDireccionamientoIds() {
        return direccionamientoIds;
    }

    public void setDireccionamientoIds(List<Integer> direccionamientoIds) {
        this.direccionamientoIds = direccionamientoIds;
    }

    public void setEstadoOpt(List<SelectItem> estadoOpt) {
        this.estadoOpt = estadoOpt;
    }

    public Integer getIdSeleccionTecnologia() {
        return idSeleccionTecnologia;
    }

    public void setIdSeleccionTecnologia(Integer idSeleccionTecnologia) {
        this.idSeleccionTecnologia = idSeleccionTecnologia;
    }

    public Integer getIdSeleccionTecnologiaAnulada() {
        return idSeleccionTecnologiaAnulada;
    }

    public void setIdSeleccionTecnologiaAnulada(Integer idSeleccionTecnologiaAnulada) {
        this.idSeleccionTecnologiaAnulada = idSeleccionTecnologiaAnulada;
    }

    public Integer getTipoSeleccionTecnologiaAnulada() {
        return tipoSeleccionTecnologiaAnulada;
    }

    public void setTipoSeleccionTecnologiaAnulada(Integer tipoSeleccionTecnologiaAnulada) {
        this.tipoSeleccionTecnologiaAnulada = tipoSeleccionTecnologiaAnulada;
    }

    public Integer getIdPrescripcionEntrega() {
        return idPrescripcionEntrega;
    }

    public void setIdPrescripcionEntrega(Integer idPrescripcionEntrega) {
        this.idPrescripcionEntrega = idPrescripcionEntrega;
    }

    public Integer getIdItemEntrega() {
        return idItemEntrega;
    }

    public void setIdItemEntrega(Integer idItemEntrega) {
        this.idItemEntrega = idItemEntrega;
    }

    public Integer getTipoTecnologiaEntrega() {
        return tipoTecnologiaEntrega;
    }

    public String getReporteTicketString() {
        return reporteTicketString;
    }

    public void setReporteTicketString(String reporteTicketString) {
        this.reporteTicketString = reporteTicketString;
    }

    public void setTipoTecnologiaEntrega(Integer tipoTecnologiaEntrega) {
        this.tipoTecnologiaEntrega = tipoTecnologiaEntrega;
    }

    public Integer getTipoSeleccionTecnologia() {
        return tipoSeleccionTecnologia;
    }

    public void setTipoSeleccionTecnologia(Integer tipoSeleccionTecnologia) {
        this.tipoSeleccionTecnologia = tipoSeleccionTecnologia;
    }

    public boolean isMostrarPanelSinFecha() {
        return mostrarPanelSinFecha;
    }

    public void setMostrarPanelSinFecha(boolean mostrarPanelSinFecha) {
        this.mostrarPanelSinFecha = mostrarPanelSinFecha;
    }

    public boolean isValidoParaEditar() {
        return validoParaEditar;
    }

    public void setValidoParaEditar(boolean validoParaEditar) {
        this.validoParaEditar = validoParaEditar;
    }

    public boolean isMostrarPanelConFecha() {
        return mostrarPanelConFecha;
    }

    public void setMostrarPanelConFecha(boolean mostrarPanelConFecha) {
        this.mostrarPanelConFecha = mostrarPanelConFecha;
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public void consultarCntPrestador(Integer id, Integer tipo) {
        if (id != null) {
//            if (tipo != 3) {
            try {
                inicializarLazyPrestadorSedeContrato(id, tipo);
                refrescarContratoDetalle();
                PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeListaContratos').show()");
            } catch (Exception ex) {
                addError(ex.getMessage());
            }
//            } else {
//                consultarCntPrestador();
//            }
        } else {
            addError("Debe Elegir Una Tecnologia");
        }
        generarMensajes();
    }

    public void solicitarCotizacion(Integer prescripcion, Integer item, Integer tipo) {
        Integer valor = null;
        try {
            valor = getMipresServicio().validarCotizacion(prescripcion, item, tipo);
            if (valor != null) {
                switch (valor) {
                    case 11:
                        addError("Ya Cuenta Con Una Solicitud Por Cotización");
                        break;
                    case 12:
                        addError("Ya Cuenta Con Una Cotización O Se Encuentra Fuera De Vigencia");
                        break;
                    case 13:
                        getMipresServicio().registroSolicitudCotizacion(prescripcion, item, tipo, this);
                        addMensaje("La Solicitud Se Realizo ExitosaMente");
                        break;
                    case 14:
                        addMensaje("Ya cuenta con direccionamiento con esta cotizacion O Se Encuentra Fuera De Vigencia");
                        break;
                    case 15:
                        getMipresServicio().registroSolicitudCotizacion(prescripcion, item, tipo, this);
                        addMensaje("La Solicitud Se Realizo ExitosaMente");
                        break;
                    case 17:
                        addMensaje("Ya Cuenta Con Una Cotización O Se Encuentra Fuera De Vigencia");
                        break;
                    case 18:
                        addMensaje("ya cuenta con un NoDireccionado Sin Cotizacion");
                        break;
                    case 19:
                        addMensaje("ya cuenta con un NoDireccionado Con Cotizacion");
                        break;
                    default:
                        break;

                }
            } else {
                getMipresServicio().registroSolicitudCotizacion(prescripcion, item, tipo, this);
                addMensaje("La Solicitud Se Realizo ExitosaMente");
            }
        } catch (Exception ex) {
            addError(ex.getMessage());
        }

        generarMensajes();
    }

    public void consultarCntPrestadorAnulado(Integer id, Integer tipo) {
        if (id != null) {
//            if (tipo != 3) {
            try {
                inicializarLazyPrestadorSedeContratoAnulado(id, tipo);
                refrescarContratoDetalleAnulado();
                PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeListaContratosAnulado').show()");
            } catch (Exception ex) {
                addError(ex.getMessage());
            }
//            } else {
//                consultarCntPrestador();
//            }
        } else {
            addError("Se Debe Confirmar La Seleccion De La Tecnologia");
        }
        generarMensajes();
    }

    public void consultarCntPrestador() {
        try {
            inicializarLazyPrestadorSede();
            PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeLista').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarCntPrestadorAnulado() {
        try {
            inicializarLazyPrestadorSede();
            PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeListaAnulado').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyPrestadorSede() {

        lazyPrestadorSede = new LazyDataModel<CntPrestadorSede>() {

            private List<CntPrestadorSede> prestadores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaPrestadorSede().getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaPrestadorSede(new ParamConsulta());
                getParamConsultaPrestadorSede().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaPrestadorSede().setPrimerRegistro(primerRegistro);
                getParamConsultaPrestadorSede().setRegistrosPagina(registrosPagina);
                getParamConsultaPrestadorSede().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaPrestadorSede().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPrestadorSede();
                prestadores = getRegistroPrestadorSede();
                setRowCount(getParamConsultaPrestadorSede().getCantidadRegistros());
                return prestadores;
            }

            @Override
            public String getRowKey(CntPrestadorSede sede) {
                return sede.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String prestadoresId) {
                Integer id = Integer.valueOf(prestadoresId);
                for (CntPrestadorSede pres : prestadores) {
                    if (id.equals(pres.getId())) {
                        return pres;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarLazyPrestadorSedeContrato(Integer id, Integer tipo) {
        lazyContratoDetalle = new LazyDataModel<CntContratoDetalle>() {
            private List<CntContratoDetalle> detalleContrato;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaPrestadorSede().getCantidadRegistros();
            }

            @Override
            public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaContratoDetalle(new ParamConsulta());
                getParamConsultaContratoDetalle().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaContratoDetalle().setPrimerRegistro(primerRegistro);
                getParamConsultaContratoDetalle().setRegistrosPagina(registrosPagina);
                getParamConsultaContratoDetalle().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaContratoDetalle().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarContratoDetalle();
                detalleContrato = getRegistroDetalleContrato();
                setRowCount(getParamConsultaContratoDetalle().getCantidadRegistros());
                return detalleContrato;
            }

            @Override
            public String getRowKey(CntContratoDetalle sede) {
                return sede.getId().toString();
            }

            @Override
            public CntContratoDetalle getRowData(String contrato) {
                Integer id = Integer.valueOf(contrato);
                for (CntContratoDetalle pres : detalleContrato) {
                    if (id.equals(pres.getId())) {
                        return pres;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarLazyPrestadorSedeContratoAnulado(Integer id, Integer tipo) {
        lazyContratoDetalle = new LazyDataModel<CntContratoDetalle>() {
            private List<CntContratoDetalle> detalleContrato;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaPrestadorSede().getCantidadRegistros();
            }

            @Override
            public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaContratoDetalle(new ParamConsulta());
                getParamConsultaContratoDetalle().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaContratoDetalle().setPrimerRegistro(primerRegistro);
                getParamConsultaContratoDetalle().setRegistrosPagina(registrosPagina);
                getParamConsultaContratoDetalle().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaContratoDetalle().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarContratoDetalleAnulado();
                detalleContrato = getRegistroDetalleContrato();
                setRowCount(getParamConsultaContratoDetalle().getCantidadRegistros());
                return detalleContrato;
            }

            @Override
            public String getRowKey(CntContratoDetalle sede) {
                return sede.getId().toString();
            }

            @Override
            public CntContratoDetalle getRowData(String contrato) {
                Integer id = Integer.valueOf(contrato);
                for (CntContratoDetalle pres : detalleContrato) {
                    if (id.equals(pres.getId())) {
                        return pres;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarPrestador() {
        this.getMipresServicio().listarPrestador(this);
    }

    public void consultarMaMedicamento() {
        try {
            inicializarLazyMaMedicamento();
            PrimeFaces.current().executeScript("PF('dialogoMaMedicamento').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaMedicamentoAnulado() {
        try {
            inicializarLazyMaMedicamento();
            PrimeFaces.current().executeScript("PF('dialogoMaMedicamentoAnulado').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyMaMedicamento() {
        lazyMaMedicamento = new LazyDataModel<MaMedicamento>() {

            private List<MaMedicamento> medicamentos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaMedicemento().getCantidadRegistros();
            }

            @Override
            public List<MaMedicamento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaMedicemento(new ParamConsulta());
                getParamConsultaMaMedicemento().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaMedicemento().setPrimerRegistro(primerRegistro);
                getParamConsultaMaMedicemento().setRegistrosPagina(registrosPagina);
                getParamConsultaMaMedicemento().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaMedicemento().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaMedicamento();
                medicamentos = getRegistroMaMedicamento();
                setRowCount(getParamConsultaMaMedicemento().getCantidadRegistros());
                return medicamentos;
            }

            @Override
            public String getRowKey(MaMedicamento sede) {
                return sede.getId().toString();
            }

            @Override
            public MaMedicamento getRowData(String maMedicamentoId) {
                Integer id = Integer.valueOf(maMedicamentoId);
                for (MaMedicamento med : medicamentos) {
                    if (id.equals(med.getId())) {
                        return med;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaMedicamento() {
        try {
            getMipresServicio().listarMaMedicamento(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaTecnologia() {
        try {
            inicializarLazyMaTecnologia();
            PrimeFaces.current().executeScript("PF('dialogoMaTecnologia').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaTecnologiaAnulado() {
        try {
            inicializarLazyMaTecnologia();
            PrimeFaces.current().executeScript("PF('dialogoMaTecnologiaAnulado').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyMaTecnologia() {
        lazyMaTecnologia = new LazyDataModel<MaTecnologia>() {

            private List<MaTecnologia> tecnologias;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaTecnologia().getCantidadRegistros();
            }

            @Override
            public List<MaTecnologia> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaTecnologia(new ParamConsulta());
                getParamConsultaMaTecnologia().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaTecnologia().setPrimerRegistro(primerRegistro);
                getParamConsultaMaTecnologia().setRegistrosPagina(registrosPagina);
                getParamConsultaMaTecnologia().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaTecnologia().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaTecnologia();
                tecnologias = getRegistroMaTecnologia();
                setRowCount(getParamConsultaMaTecnologia().getCantidadRegistros());
                return tecnologias;
            }

            @Override
            public String getRowKey(MaTecnologia tecnologia) {
                return tecnologia.getId().toString();
            }

            @Override
            public MaTecnologia getRowData(String maTecnologiaId) {
                Integer id = Integer.valueOf(maTecnologiaId);
                for (MaTecnologia tec : tecnologias) {
                    if (id.equals(tec.getId())) {
                        return tec;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaTecnologia() {
        try {
            getMipresServicio().listarMaTecnologia(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

//    public void consultarMaInsumo() {
//        try {
//            inicializarLazyMaInsumo();
//            PrimeFaces.current().executeScript("PF('dialogoMaInsumo').show()");
//        } catch (Exception ex) {
//            addError(ex.getMessage());
//        }
//    }
    public void consultarMaInsumo() {
        try {
            inicializarLazyInsumoMipres();
            PrimeFaces.current().executeScript("PF('dialogoMaInsumoMipres').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaPaquete() {
        try {
            inicializarLazyPaqueteMipres();
            PrimeFaces.current().executeScript("PF('dialogoMaPaqueteMipres').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMpMaTecnologia() {
        try {
            inicializarLazyMipresTecnologia();
            PrimeFaces.current().executeScript("PF('dialogoMaMipresTecnologia').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyMipresTecnologia() {

        lazyMaTecnologiaMipres = new LazyDataModel<MaTecnologiaMipres>() {

            private List<MaTecnologiaMipres> tecnologiasMipres;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaTecnologiaMipres().getCantidadRegistros();
            }

            @Override
            public List<MaTecnologiaMipres> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaTecnologiaMipres(new ParamConsulta());
                getParamConsultaMaTecnologiaMipres().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaTecnologiaMipres().setPrimerRegistro(primerRegistro);
                getParamConsultaMaTecnologiaMipres().setRegistrosPagina(registrosPagina);
                getParamConsultaMaTecnologiaMipres().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaTecnologiaMipres().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaTecnologiaMipres();
                tecnologiasMipres = getRegistroMaTecnologiaMipres();
                setRowCount(getParamConsultaMaTecnologiaMipres().getCantidadRegistros());
                return tecnologiasMipres;
            }

            @Override
            public String getRowKey(MaTecnologiaMipres tecnologia) {
                return tecnologia.getId().toString();
            }

            @Override
            public MaTecnologiaMipres getRowData(String maMpId) {
                Integer id = Integer.valueOf(maMpId);
                for (MaTecnologiaMipres ins : tecnologiasMipres) {
                    if (id.equals(ins.getId())) {
                        return ins;
                    }
                }
                return null;
            }
        };
    }

    public void consultarMaInsumoMipresAnulado() {
        try {
            inicializarLazyInsumoMipres();
            PrimeFaces.current().executeScript("PF('dialogoMaInsumoMipresAnulado').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaPaqueteAnulado() {
        try {
            inicializarLazyPaqueteMipres();
            PrimeFaces.current().executeScript("PF('dialogoMaPaqueteMipresAnulado').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMpMaTecnologiaAnulado() {
        try {
            inicializarLazyMipresTecnologia();
            PrimeFaces.current().executeScript("PF('dialogoMaMpMipresAnulado').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarMaInsumoAnulado() {
        try {
            inicializarLazyMaInsumo();
            PrimeFaces.current().executeScript("PF('dialogoMaInsumoAnulado').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyInsumoMipres() {
        lazyMaInsumoMipres = new LazyDataModel<MaInsumoMipres>() {

            private List<MaInsumoMipres> insumos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaInsumo().getCantidadRegistros();
            }

            @Override
            public List<MaInsumoMipres> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaInsumo(new ParamConsulta());
                getParamConsultaMaInsumo().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaInsumo().setPrimerRegistro(primerRegistro);
                getParamConsultaMaInsumo().setRegistrosPagina(registrosPagina);
                getParamConsultaMaInsumo().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaInsumo().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaInsumoMipres();
                insumos = getRegistroMaInsumoMipres();
                setRowCount(getParamConsultaMaInsumo().getCantidadRegistros());
                return insumos;
            }

            @Override
            public String getRowKey(MaInsumoMipres insumo) {
                return insumo.getId().toString();
            }

            @Override
            public MaInsumoMipres getRowData(String maInsumoId) {
                Integer id = Integer.valueOf(maInsumoId);
                for (MaInsumoMipres ins : insumos) {
                    if (id.equals(ins.getId())) {
                        return ins;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarLazyPaqueteMipres() {
        lazyMaPaqueteMipres = new LazyDataModel<MaPaqueteMipres>() {

            private List<MaPaqueteMipres> paquetes;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaPaquete().getCantidadRegistros();
            }

            @Override
            public List<MaPaqueteMipres> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaPaquete(new ParamConsulta());
                getParamConsultaMaPaquete().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaPaquete().setPrimerRegistro(primerRegistro);
                getParamConsultaMaPaquete().setRegistrosPagina(registrosPagina);
                getParamConsultaMaPaquete().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaPaquete().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaPaqueteMipres();
                paquetes = getRegistroMaPaqueteMipres();
                setRowCount(getParamConsultaMaPaquete().getCantidadRegistros());
                return paquetes;
            }

            @Override
            public String getRowKey(MaPaqueteMipres paquete) {
                return paquete.getId().toString();
            }

            @Override
            public MaPaqueteMipres getRowData(String paqueteId) {
                Integer id = Integer.valueOf(paqueteId);
                for (MaPaqueteMipres ins : paquetes) {
                    if (id.equals(ins.getId())) {
                        return ins;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaInsumoMipres() {
        try {
            getMipresServicio().listarMaInsumoMipres(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void refrescarMaPaqueteMipres() {
        try {
            getMipresServicio().listarMaPaqueteMipres(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void refrescarMaTecnologiaMipres() {
        try {
            getMipresServicio().listarMaTecnologiaMipres(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarLazyMaInsumo() {
        lazyMaInsumo = new LazyDataModel<MaInsumo>() {

            private List<MaInsumo> insumos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaMaInsumo().getCantidadRegistros();
            }

            @Override
            public List<MaInsumo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaMaInsumo(new ParamConsulta());
                getParamConsultaMaInsumo().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaMaInsumo().setPrimerRegistro(primerRegistro);
                getParamConsultaMaInsumo().setRegistrosPagina(registrosPagina);
                getParamConsultaMaInsumo().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMaInsumo().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMaInsumo();
                insumos = getRegistroMaInsumo();
                setRowCount(getParamConsultaMaInsumo().getCantidadRegistros());
                return insumos;
            }

            @Override
            public String getRowKey(MaInsumo insumo) {
                return insumo.getId().toString();
            }

            @Override
            public MaInsumo getRowData(String maInsumoId) {
                Integer id = Integer.valueOf(maInsumoId);
                for (MaInsumo ins : insumos) {
                    if (id.equals(ins.getId())) {
                        return ins;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarMaInsumo() {
        try {
            getMipresServicio().listarMaInsumo(this);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void refrescarPrestadorSede() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_VER);
        getMipresServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarContratoDetalle() {
        getMipresServicio().consultarContratoDetalle(this);
        procesoFinal();
    }

    public void refrescarContratoDetalleAnulado() {
        getMipresServicio().consultarContratoDetalleAnulado(this);
        procesoFinal();
    }

    public void notificarBtn() {
        if (!notificarPrestador) {
            notificarPrestador = false;
            notificarCorreoPrestador = false;
            notificarSmsPrestador = false;
            correoPrestador = null;
            telefonoPrestador = null;
        }
        PrimeFaces.current().ajax().update("frmAuditoria:panelCrearNotificacion");
    }

    public void notificarBtnP() {
        if (!notificarPrestador) {
            notificarCorreoPrestador = false;
            notificarSmsPrestador = false;
            correoPrestador = null;
            telefonoPrestador = null;
        }
        PrimeFaces.current().ajax().update("frmAuditoria:botonNotificarPrestadorCorreo");
        PrimeFaces.current().ajax().update("frmAuditoria:txtPrestadorCorreo");
        PrimeFaces.current().ajax().update("frmAuditoria:botonNotificarPrestadorSmss");
        PrimeFaces.current().ajax().update("frmAuditoria:txtPrestadorSms");
    }

    public boolean isMostrarPanelSuministro() {
        return mostrarPanelSuministro;
    }

    public void setMostrarPanelSuministro(boolean mostrarPanelSuministro) {
        this.mostrarPanelSuministro = mostrarPanelSuministro;
    }

    public boolean isMostrarPanelSuministroBotonera() {
        return mostrarPanelSuministroBotonera;
    }

    public void setMostrarPanelSuministroBotonera(boolean mostrarPanelSuministroBotonera) {
        this.mostrarPanelSuministroBotonera = mostrarPanelSuministroBotonera;
    }

    public boolean isDesactivarBotonAnular() {
        return desactivarBotonAnular;
    }

    public void setDesactivarBotonAnular(boolean desactivarBotonAnular) {
        this.desactivarBotonAnular = desactivarBotonAnular;
    }

    public boolean isDesactivarBotonGuardar() {
        return desactivarBotonGuardar;
    }

    public void setDesactivarBotonGuardar(boolean desactivarBotonGuardar) {
        this.desactivarBotonGuardar = desactivarBotonGuardar;
    }

    public boolean isDesactivarBotonCrear() {
        return desactivarBotonCrear;
    }

    public void setDesactivarBotonCrear(boolean desactivarBotonCrear) {
        this.desactivarBotonCrear = desactivarBotonCrear;
    }

    public boolean isDesactivarBotonUltimaEntrega() {
        return desactivarBotonUltimaEntrega;
    }

    public void setDesactivarBotonUltimaEntrega(boolean desactivarBotonUltimaEntrega) {
        this.desactivarBotonUltimaEntrega = desactivarBotonUltimaEntrega;
    }

    public boolean isDesactivarBotonEntregaCompleta() {
        return desactivarBotonEntregaCompleta;
    }

    public void setDesactivarBotonEntregaCompleta(boolean desactivarBotonEntregaCompleta) {
        this.desactivarBotonEntregaCompleta = desactivarBotonEntregaCompleta;
    }

    public void notificarBtnA() {
        if (!notificarPaciente) {
            notificarCorreoPaciente = false;
            notificarSmsPaciente = false;
            correoPaciente = null;
            telefonoPaciente = null;
        }
        PrimeFaces.current().ajax().update("frmAuditoria:botonNotificarPacienteCorreo");
        PrimeFaces.current().ajax().update("frmAuditoria:txtAfiliadoCorreo");
        PrimeFaces.current().ajax().update("frmAuditoria:botonNotificarPacienteSmss");
        PrimeFaces.current().ajax().update("frmAuditoria:txtAfiliadoSms");
    }

    public void notificarPorCorreoPresta() {
        if (notificarCorreoPrestador) {
            notificarCorreoPrestador = true;
            getMipresServicio().consultarCorreoPrestador(this);
        } else {
            notificarCorreoPrestador = false;
            correoPrestador = null;
        }

        PrimeFaces.current().ajax().update("frmAuditoria:txtPrestadorCorreo");
    }

    public void consultarCorreoAfiliado() {
        if (notificarCorreoPaciente) {
            notificarCorreoPaciente = true;
            getMipresServicio().consultarCorreoAfiliado(this);
        } else {
            notificarCorreoPaciente = false;
            correoPaciente = null;
        }

        PrimeFaces.current().ajax().update("frmAuditoria:txtAfiliadoCorreo");
    }

    public void notificarPorSmsPresta() {
        if (notificarSmsPrestador) {
            notificarSmsPrestador = true;
        } else {
            notificarSmsPrestador = false;
            telefonoPrestador = null;
        }
        PrimeFaces.current().ajax().update("frmAuditoria:txtPrestadorSms");
    }

    public void notificarPorSmsAfiliado() {
        if (notificarSmsPaciente) {
            notificarSmsPaciente = true;
        } else {
            notificarSmsPaciente = false;
            telefonoPaciente = null;
        }
        PrimeFaces.current().ajax().update("frmAuditoria:txtAfiliadoSms");
    }

    public Integer desimalConvertido(BigDecimal valor) {
        Integer resultado = valor.intValue();
        return resultado;
    }

    public void onRowSelectPrestador(SelectEvent event) {
        CntPrestador prestador = (CntPrestador) event.getObject();
        direccionamiento.setMaeTipoDocumentoPrestadorId(prestador.getMaeTipoDocumentoId());
        direccionamiento.setMaeTipoDocumentoPrestadorCodigo(prestador.getMaeTipoDocumentoCodigo());
        direccionamiento.setMaeTipoDocumentoPrestadorValor(prestador.getMaeTipoDocumentoValor());
        direccionamiento.setPrestadorRazonSocial(prestador.getRazonSocial());

        PrimeFaces.current().ajax().update("frmDireccionamiento");
        PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').hide()");

    }

    public void onRowSelectSedeContrato(SelectEvent event) {
        CntContratoDetalle ContratoDetalle = (CntContratoDetalle) event.getObject();
        direccionamiento.setPrestadorNumeroDocumentoStr(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoPrestador());
        direccionamiento.setNombreSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getNombreSede() + " - " + ContratoDetalle.getCntContrato().getCntPrestador().getRazonSocial());
        direccionamiento.setCodigoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoSede());
        direccionamiento.setCodigoHabilitacionSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
        direccionamiento.setFaxSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getFax());
        direccionamiento.setTelefonoCitasSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getTelefonoCitas());
        direccionamiento.setTelefonoAdministrativoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getTelefonoAdministrativo());
        direccionamiento.setCorreoElectronicoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCorreoElectronico());
        direccionamiento.setDireccionSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getDireccion());
        direccionamiento.setValorTecContratada(ContratoDetalle.getValorContratado());

        direccionamiento.setMaeTipoDocumentoPrestadorId(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoId());
        direccionamiento.setMaeTipoDocumentoPrestadorCodigo(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
        direccionamiento.setMaeTipoDocumentoPrestadorValor(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoValor());
        direccionamiento.setPrestadorNumeroDocumento(new BigInteger(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getNumeroDocumento()));
        direccionamiento.setPrestadorRazonSocial(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getRazonSocial());

        this.setIdCotizacion(null);

        if (!getListadireccionamientos().isEmpty()) {
            for (MpDireccionamiento direccionamientos : getListadireccionamientos()) {
                direccionamientos.setPrestadorNumeroDocumentoStr(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoPrestador());
                direccionamientos.setNombreSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getNombreSede() + " - " + ContratoDetalle.getCntContrato().getCntPrestador().getRazonSocial());
                direccionamientos.setCodigoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoSede());
                direccionamientos.setCodigoHabilitacionSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
                direccionamientos.setFaxSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getFax());
                direccionamientos.setTelefonoCitasSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getTelefonoCitas());
                direccionamientos.setTelefonoAdministrativoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getTelefonoAdministrativo());
                direccionamientos.setCorreoElectronicoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCorreoElectronico());
                direccionamientos.setDireccionSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getDireccion());
                direccionamientos.setValorTecContratada(ContratoDetalle.getValorContratado());

                direccionamientos.setMaeTipoDocumentoPrestadorId(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoId());
                direccionamientos.setMaeTipoDocumentoPrestadorCodigo(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
                direccionamientos.setMaeTipoDocumentoPrestadorValor(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoValor());
                direccionamientos.setPrestadorNumeroDocumento(new BigInteger(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getNumeroDocumento()));
                direccionamientos.setPrestadorRazonSocial(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getRazonSocial());
            }
        }

        PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelDireccionamientoPrestador");
        PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeListaContratos').hide()");
    }

    public void asignarCotizacion(Integer prescripcion, Integer item, Integer tipo) {//continuar con asignacion de la tecnologia
        if (getObjetoCotizacion() != null) {
            direccionamiento.setPrestadorNumeroDocumentoStr(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getNumeroDocumento());
            direccionamiento.setNombreSede(getObjetoCotizacion().getCntPrestadorSede().getNombreSede());
            direccionamiento.setCodigoSede(getObjetoCotizacion().getCntPrestadorSede().getCodigoPrestador());
            direccionamiento.setCodigoHabilitacionSede(getObjetoCotizacion().getCntPrestadorSede().getCodigoHabilitacionSede());
            direccionamiento.setFaxSede("");
            direccionamiento.setTelefonoCitasSede("");
            direccionamiento.setTelefonoAdministrativoSede("");
            direccionamiento.setCorreoElectronicoSede("");
            direccionamiento.setDireccionSede(getObjetoCotizacion().getCntPrestadorSede().getDireccion());
            direccionamiento.setValorTecContratada(getObjetoCotizacion().getValorTecnologia());

            direccionamiento.setMaeTipoDocumentoPrestadorId(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoId());
            direccionamiento.setMaeTipoDocumentoPrestadorCodigo(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
            direccionamiento.setMaeTipoDocumentoPrestadorValor(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoValor());
            direccionamiento.setPrestadorNumeroDocumento(new BigInteger(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getNumeroDocumento()));
            direccionamiento.setPrestadorRazonSocial(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getRazonSocial());

            direccionamiento.setCodigoMpEntrega(getObjetoCotizacion().getMaTecnologiaCodigo());
            direccionamiento.setCodigoMpPropio(getObjetoCotizacion().getMaTecnologiaValor());

            this.setIdCotizacion(getObjetoCotizacion().getId());

            if (!getListadireccionamientos().isEmpty()) {
                for (MpDireccionamiento direccionamientos : getListadireccionamientos()) {
                    direccionamientos.setPrestadorNumeroDocumentoStr(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getNumeroDocumento());
                    direccionamientos.setNombreSede(getObjetoCotizacion().getCntPrestadorSede().getNombreSede());
                    direccionamientos.setCodigoSede(getObjetoCotizacion().getCntPrestadorSede().getCodigoPrestador());
                    direccionamientos.setCodigoHabilitacionSede(getObjetoCotizacion().getCntPrestadorSede().getCodigoHabilitacionSede());
                    direccionamientos.setFaxSede("");
                    direccionamientos.setTelefonoCitasSede("");
                    direccionamientos.setTelefonoAdministrativoSede("");
                    direccionamientos.setCorreoElectronicoSede("");
                    direccionamientos.setDireccionSede(getObjetoCotizacion().getCntPrestadorSede().getDireccion());
                    direccionamientos.setValorTecContratada(getObjetoCotizacion().getValorTecnologia());

                    direccionamientos.setMaeTipoDocumentoPrestadorId(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoId());
                    direccionamientos.setMaeTipoDocumentoPrestadorCodigo(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
                    direccionamientos.setMaeTipoDocumentoPrestadorValor(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoValor());
                    direccionamientos.setPrestadorNumeroDocumento(new BigInteger(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getNumeroDocumento()));
                    direccionamientos.setPrestadorRazonSocial(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getRazonSocial());
                }
            }
        } else {
            addError("Cotización Se Encuentra Fuera De Vigencia");
            getMipresServicio().registroSolicitudCotizacion(prescripcion, item, tipo, this);
            generarMensajes();
        }

        PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelDireccionamientoPrestador");
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelCodDirecciona");

    }

    public void asignarCotizacionAnulacion(Integer prescripcion, Integer item, Integer tipo) {//continuar con asignacion de la tecnologia
        if (getObjetoCotizacion() != null) {
            direccionamientoPorAnulacion.setPrestadorNumeroDocumentoStr(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getNumeroDocumento());
            direccionamientoPorAnulacion.setNombreSede(getObjetoCotizacion().getCntPrestadorSede().getNombreSede());
            direccionamientoPorAnulacion.setCodigoSede(getObjetoCotizacion().getCntPrestadorSede().getCodigoPrestador());
            direccionamientoPorAnulacion.setCodigoHabilitacionSede(getObjetoCotizacion().getCntPrestadorSede().getCodigoHabilitacionSede());
            direccionamientoPorAnulacion.setFaxSede("");
            direccionamientoPorAnulacion.setTelefonoCitasSede("");
            direccionamientoPorAnulacion.setTelefonoAdministrativoSede("");
            direccionamientoPorAnulacion.setCorreoElectronicoSede("");
            direccionamientoPorAnulacion.setDireccionSede(getObjetoCotizacion().getCntPrestadorSede().getDireccion());
            direccionamientoPorAnulacion.setValorTecContratada(getObjetoCotizacion().getValorTecnologia());

            direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorId(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoId());
            direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorCodigo(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
            direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorValor(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoValor());
            direccionamientoPorAnulacion.setPrestadorNumeroDocumento(new BigInteger(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getNumeroDocumento()));
            direccionamientoPorAnulacion.setPrestadorRazonSocial(getObjetoCotizacion().getCntPrestadorSede().getCntPrestador().getRazonSocial());

            direccionamientoPorAnulacion.setCodigoMpEntrega(getObjetoCotizacion().getMaTecnologiaCodigo());
            direccionamientoPorAnulacion.setCodigoMpPropio(getObjetoCotizacion().getMaTecnologiaValor());
            this.setIdCotizacion(getObjetoCotizacion().getId());
        } else {
            addError("Cotización Se Encuentra Fuera De Vigencia");
            getMipresServicio().registroSolicitudCotizacion(prescripcion, item, tipo, this);
            generarMensajes();
        }

        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelPrestador2");
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDirecciona22");
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:pJuzgadoCrear2");

    }

    public void onRowSelectSedeContratoAnulado(SelectEvent event) {
        CntContratoDetalle ContratoDetalle = (CntContratoDetalle) event.getObject();
        direccionamientoPorAnulacion.setPrestadorNumeroDocumentoStr(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoPrestador());
        direccionamientoPorAnulacion.setNombreSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getNombreSede());
        direccionamientoPorAnulacion.setCodigoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoSede());
        direccionamientoPorAnulacion.setCodigoHabilitacionSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
        direccionamientoPorAnulacion.setFaxSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getFax());
        direccionamientoPorAnulacion.setTelefonoCitasSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getTelefonoCitas());
        direccionamientoPorAnulacion.setTelefonoAdministrativoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getTelefonoAdministrativo());
        direccionamientoPorAnulacion.setCorreoElectronicoSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCorreoElectronico());
        direccionamientoPorAnulacion.setDireccionSede(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getDireccion());
        direccionamientoPorAnulacion.setValorTecContratada(ContratoDetalle.getValorContratado());

        direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorId(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoId());
        direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorCodigo(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoCodigo());
        direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorValor(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getMaeTipoDocumentoValor());
        direccionamientoPorAnulacion.setPrestadorNumeroDocumento(new BigInteger(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getNumeroDocumento()));
        direccionamientoPorAnulacion.setPrestadorRazonSocial(ContratoDetalle.getCntContratoSede().getCntPrestadorSede().getCntPrestador().getRazonSocial());
        this.setIdCotizacion(null);
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelDireccionamientoPrestador5");
        PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeListaContratosAnulado').hide()");
    }

    public void onRowSelectSede(SelectEvent event) {
        CntPrestadorSede ContratoDetalle = (CntPrestadorSede) event.getObject();
        direccionamiento.setPrestadorNumeroDocumentoStr(ContratoDetalle.getCodigoPrestador());
        direccionamiento.setNombreSede(ContratoDetalle.getNombreSede());
        direccionamiento.setCodigoSede(ContratoDetalle.getCodigoSede());
        direccionamiento.setCodigoHabilitacionSede(ContratoDetalle.getCodigoHabilitacionSede());
        direccionamiento.setFaxSede(ContratoDetalle.getFax());
        direccionamiento.setTelefonoCitasSede(ContratoDetalle.getTelefonoCitas());
        direccionamiento.setTelefonoAdministrativoSede(ContratoDetalle.getTelefonoAdministrativo());
        direccionamiento.setCorreoElectronicoSede(ContratoDetalle.getCorreoElectronico());
        direccionamiento.setDireccionSede(ContratoDetalle.getDireccion());

        direccionamiento.setMaeTipoDocumentoPrestadorId(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoId());
        direccionamiento.setMaeTipoDocumentoPrestadorCodigo(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoCodigo());
        direccionamiento.setMaeTipoDocumentoPrestadorValor(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoValor());
        direccionamiento.setPrestadorNumeroDocumento(new BigInteger(ContratoDetalle.getCntPrestador().getNumeroDocumento()));
        direccionamiento.setPrestadorRazonSocial(ContratoDetalle.getCntPrestador().getRazonSocial());

        if (!getListadireccionamientos().isEmpty()) {
            for (MpDireccionamiento direccionamientos : getListadireccionamientos()) {
                direccionamientos.setPrestadorNumeroDocumentoStr(ContratoDetalle.getCodigoPrestador());
                direccionamientos.setNombreSede(ContratoDetalle.getNombreSede());
                direccionamientos.setCodigoSede(ContratoDetalle.getCodigoSede());
                direccionamientos.setCodigoHabilitacionSede(ContratoDetalle.getCodigoHabilitacionSede());
                direccionamientos.setFaxSede(ContratoDetalle.getFax());
                direccionamientos.setTelefonoCitasSede(ContratoDetalle.getTelefonoCitas());
                direccionamientos.setTelefonoAdministrativoSede(ContratoDetalle.getTelefonoAdministrativo());
                direccionamientos.setCorreoElectronicoSede(ContratoDetalle.getCorreoElectronico());
                direccionamientos.setDireccionSede(ContratoDetalle.getDireccion());

                direccionamientos.setMaeTipoDocumentoPrestadorId(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoId());
                direccionamientos.setMaeTipoDocumentoPrestadorCodigo(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoCodigo());
                direccionamientos.setMaeTipoDocumentoPrestadorValor(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoValor());
                direccionamientos.setPrestadorNumeroDocumento(new BigInteger(ContratoDetalle.getCntPrestador().getNumeroDocumento()));
                direccionamientos.setPrestadorRazonSocial(ContratoDetalle.getCntPrestador().getRazonSocial());
            }
        }

        PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelDireccionamientoPrestador");
        PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeLista').hide()");
    }

    public void onRowSelectSedeAnulado(SelectEvent event) {
        CntPrestadorSede ContratoDetalle = (CntPrestadorSede) event.getObject();
        direccionamientoPorAnulacion.setPrestadorNumeroDocumentoStr(ContratoDetalle.getCodigoPrestador());
        direccionamientoPorAnulacion.setNombreSede(ContratoDetalle.getNombreSede());
        direccionamientoPorAnulacion.setCodigoSede(ContratoDetalle.getCodigoSede());
        direccionamientoPorAnulacion.setCodigoHabilitacionSede(ContratoDetalle.getCodigoHabilitacionSede());
        direccionamientoPorAnulacion.setFaxSede(ContratoDetalle.getFax());
        direccionamientoPorAnulacion.setTelefonoCitasSede(ContratoDetalle.getTelefonoCitas());
        direccionamientoPorAnulacion.setTelefonoAdministrativoSede(ContratoDetalle.getTelefonoAdministrativo());
        direccionamientoPorAnulacion.setCorreoElectronicoSede(ContratoDetalle.getCorreoElectronico());
        direccionamientoPorAnulacion.setDireccionSede(ContratoDetalle.getDireccion());

        direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorId(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoId());
        direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorCodigo(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoCodigo());
        direccionamientoPorAnulacion.setMaeTipoDocumentoPrestadorValor(ContratoDetalle.getCntPrestador().getMaeTipoDocumentoValor());
        direccionamientoPorAnulacion.setPrestadorNumeroDocumento(new BigInteger(ContratoDetalle.getCntPrestador().getNumeroDocumento()));
        direccionamientoPorAnulacion.setPrestadorRazonSocial(ContratoDetalle.getCntPrestador().getRazonSocial());

        this.setIdCotizacion(null);
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelDireccionamientoPrestador5");
        PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeListaAnulado').hide()");
    }

    public <MpDireccionamiento> MpDireccionamiento obtenerUltimoElemento(List<MpDireccionamiento> lista) {
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(lista.size() - 1); // Retorna el último elemento de la lista
        }
    }

    public void entregasLista() {

        Integer direcCantidad = (numDirecionamientoDireccionado == null || numDirecionamientoDireccionado == 0) ? 0 : numDirecionamientoDireccionado;

        MpDireccionamiento respaldo = new MpDireccionamiento();
        Date fechaDireccionada = new Date();
        getCalcularCantidadDeEntregas();

        List<MpDireccionamiento> listadireccionamiento = new ArrayList<>();
        int entregaTotal = direccionamiento.getEntregaTotal();
        for (int i = 0; i < numeroDeEntregasCalculada; i++) {
            MpDireccionamiento direccionamientos = new MpDireccionamiento();
            if (cantidadesParaEntregar.get(i) != 0) {
                direccionamientos.setEntregaTotal(cantidadesParaEntregar.get(i));
                direccionamientos.setId(i + 1);
                direccionamientos.setConsecutivoEntrega(i + 1);
                if (direcCantidad == 0) {
                    Date fechaActual = new Date();
                    Date fechaMaxEntrega = new Date();
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(fechaActual);
                    calendario.add(Calendar.MONTH, i + 1);
                    fechaMaxEntrega = calendario.getTime();

                    direccionamientos.setFechaMaxEntrega(fechaMaxEntrega);

                } else if (direcCantidad > 0) {
                    respaldo = obtenerUltimoElemento(direccionamientoDireccionado);
                    fechaDireccionada = respaldo.getFechaMaxEntrega();
                    direccionamientos.setFechaMaxEntrega(fechaDireccionada);
                    direcCantidad = -1;
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(fechaDireccionada);
                    calendario.add(Calendar.MONTH, i - 1);
                    fechaDireccionada = calendario.getTime();
                } else if (direcCantidad < 0) {
                    Date fechaMaxEntrega = new Date();
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(fechaDireccionada);
                    calendario.add(Calendar.MONTH, i + 1);
                    fechaMaxEntrega = calendario.getTime();

                    direccionamientos.setFechaMaxEntrega(fechaMaxEntrega);
                }

                direccionamientos.setSubEntrega(0);
                entregaTotal -= cantidadesParaEntregar.get(i);

                listadireccionamiento.add(direccionamientos);
            }
        }

        List<MpDireccionamiento> listadireccionamientoOrdenada = listadireccionamiento.stream()
                .sorted(Comparator.comparingInt(MpDireccionamiento::getId))
                .collect(Collectors.toList());

        setListadireccionamientos(listadireccionamientoOrdenada);

        calculoEntregaDisponible = entregaTotal;
        if (numeroDeEntregasCalculada != listadireccionamiento.size()) {
            numeroDeEntregasCalculada = listadireccionamiento.size();
        }

        if (numDirecionamientoDireccionado > 0 && numDirecionamientoDireccionado <= listadireccionamiento.size()) {
            listadireccionamiento.subList(0, numDirecionamientoDireccionado).clear();
        }

        int cantidadTotal = direccionamiento.getEntregaTotal(); // Cantidad total a entregar
        int contadorEntregadas = 0;
        int contadorDireccionar = 0;
        for (MpDireccionamiento dir : direccionamientoDireccionado) {
            contadorEntregadas += dir.getEntregaTotal();
        }
        int cantidadEntregada = contadorEntregadas;
        int cantidadRestante = cantidadTotal - cantidadEntregada;
        for (MpDireccionamiento dir : listadireccionamiento) {
            contadorDireccionar += dir.getEntregaTotal();
        }
        int cantidadDireccionar = contadorDireccionar;
        if (cantidadDireccionar > cantidadRestante) {
            for (int i = 0; i < listadireccionamiento.size(); i++) {
                MpDireccionamiento entrega = listadireccionamiento.get(i);
                int cantidadEsperada = entrega.getEntregaTotal();
                if (cantidadRestante < cantidadEsperada) {
                    entrega.setEntregaTotal(cantidadRestante);
                    cantidadRestante = 0;
                } else {
                    if (cantidadRestante == 0) {
                        entrega.setEntregaTotal(cantidadRestante);
                    } else {
                        cantidadRestante -= cantidadEsperada;
                    }
                }
                if (entrega.getEntregaTotal() <= 0) {
                    entrega.setEntregaTotal(0);
//                    listadireccionamiento.remove(i);
                }
            }
            Iterator<MpDireccionamiento> iterator = listadireccionamiento.iterator();
            while (iterator.hasNext()) {
                MpDireccionamiento entrega = iterator.next();
                if (entrega.getEntregaTotal() <= 0) {
                    iterator.remove();
                }
            }
        } else {
            for (int i = 0; i < listadireccionamiento.size(); i++) {
                MpDireccionamiento entrega = listadireccionamiento.get(i);
                int cantidadEsperada = entrega.getEntregaTotal();
                if (cantidadRestante < cantidadEsperada) {
                    entrega.setEntregaTotal(cantidadRestante);
                    cantidadRestante = 0;
                } else {
                    if (cantidadRestante == 0) {
                        entrega.setEntregaTotal(cantidadRestante);
                    } else {
                        cantidadRestante -= cantidadEsperada;
                    }
                }
            }
            calculoEntregaDisponible = cantidadRestante;
        }

        setListadireccionamientos(listadireccionamiento);
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelDireccionamiento");
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
        PrimeFaces.current().executeScript("PF('dialogoPrestadorSedeLista').hide()");

    }

    public Integer getCalculoEntregaDisponible() {
        return calculoEntregaDisponible;
    }

    public void setCalculoEntregaDisponible(Integer calculoEntregaDisponible) {
        this.calculoEntregaDisponible = calculoEntregaDisponible;
    }

    public void getCalcularCantidadDeEntregas() {

        Integer dias = (direccionamiento.getCronoEntregas() != null && direccionamiento.getCronoEntregas() > 0) ? direccionamiento.getCronoEntregas() : 5;

        Integer cantidad = (direccionamiento.getNumeroDeEntregas() != null && direccionamiento.getNumeroDeEntregas() > 0) ? direccionamiento.getNumeroDeEntregas() : 1;
        Integer cantidadPrescrita = direccionamiento.getEntregaTotal();

        switch (dias) {
            case 1:
                calcularCantidadDeEntregas = Math.round((float) cantidad / 60);
                if (calcularCantidadDeEntregas <= 0) {
                    calcularCantidadDeEntregas = 1;
                }
                break;
            case 2:
                calcularCantidadDeEntregas = Math.round((float) cantidad / 60);
                if (calcularCantidadDeEntregas <= 0) {
                    calcularCantidadDeEntregas = 1;
                }
                break;

            case 3:
                calcularCantidadDeEntregas = Math.round((float) cantidad / 30);
                if (calcularCantidadDeEntregas <= 0) {
                    calcularCantidadDeEntregas = 1;
                }
                break;
            case 4:
                calcularCantidadDeEntregas = Math.round((float) (cantidad / 4));
                if (calcularCantidadDeEntregas <= 0) {
                    calcularCantidadDeEntregas = 1;
                }
                break;
            case 5: {
                calcularCantidadDeEntregas = cantidad;
                if (calcularCantidadDeEntregas <= 0) {
                    calcularCantidadDeEntregas = 1;
                }
            }
            break;
            case 6: {
                if (cantidadPrescrita > 1) {
                    calcularCantidadDeEntregas = cantidad * 12;
                    if (calcularCantidadDeEntregas <= 0) {
                        calcularCantidadDeEntregas = 1;
                    }
                } else {
                    calcularCantidadDeEntregas = cantidad;
                }

            }
            break;

        }

        distribuirCantidadEnMeses(cantidadPrescrita, calcularCantidadDeEntregas);
        numeroDeEntregasCalculada = calcularCantidadDeEntregas;
    }

    public void setCalcularCantidadDeEntregas(Integer calcularCantidadDeEntregas) {
        this.calcularCantidadDeEntregas = calcularCantidadDeEntregas;
    }

    public synchronized void distribuirCantidadEnMeses(int cantidadPrescrita, int meses) {
        cantidadesParaEntregar = new ArrayList<>();
        int cantidadPorMes = cantidadPrescrita / meses;
        int cantidadRestante = cantidadPrescrita % meses;

        for (int i = 0; i < meses; i++) {
            int cantidad = cantidadPorMes;

            if (cantidadRestante > 0) {
                cantidad++;
                cantidadRestante--;
            }

            cantidadesParaEntregar.add(cantidad);
        }
    }

    public void quitarProgramacion(MpDireccionamiento direccionamiento) {
        calculoEntregaDisponible = calculoEntregaDisponible + direccionamiento.getEntregaTotal();
        if (listadireccionamientos != null && direccionamiento != null) {
            listadireccionamientos.remove(direccionamiento);

            // Recalcular fechas y orden de entrega
            Date fechaActual = new Date();
            if (!direccionamientoDireccionado.isEmpty()) {

                if (listadireccionamientos.isEmpty()) {
                    for (int i = 0; i < listadireccionamientos.size(); i++) {
                        MpDireccionamiento dir = listadireccionamientos.get(i);
                        dir.setConsecutivoEntrega(i + 1);
                        dir.setId(dir.getConsecutivoEntrega());
                        Calendar calendario = Calendar.getInstance();
                        calendario.setTime(fechaActual);
                        calendario.add(Calendar.MONTH, i + 1);
                        Date fechaMaxEntrega = calendario.getTime();

                        dir.setFechaMaxEntrega(fechaMaxEntrega);
                    }
                } else {
                    MpDireccionamiento primerDireccionamiento = direccionamientoDireccionado.get(0);

                    Date fechaReferencia = primerDireccionamiento.getFechaMaxEntrega();
                    int consecutivoReferencia = primerDireccionamiento.getConsecutivoEntrega();

                    for (int i = 0; i < listadireccionamientos.size(); i++) {
                        MpDireccionamiento dir = listadireccionamientos.get(i);
                        dir.setConsecutivoEntrega(consecutivoReferencia + 1);
                        dir.setId(dir.getConsecutivoEntrega());
                        Calendar calendario = Calendar.getInstance();
                        calendario.setTime(fechaReferencia);
                        calendario.add(Calendar.MONTH, i + 1);
                        Date fechaMaxEntrega = calendario.getTime();

                        dir.setFechaMaxEntrega(fechaMaxEntrega);
                        consecutivoReferencia++;
                    }
                }
            } else {
                for (int i = 0; i < listadireccionamientos.size(); i++) {
                    MpDireccionamiento dir = listadireccionamientos.get(i);
                    dir.setConsecutivoEntrega(i + 1);
                    dir.setId(dir.getConsecutivoEntrega());
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(fechaActual);
                    calendario.add(Calendar.MONTH, i + 1);
                    Date fechaMaxEntrega = calendario.getTime();

                    dir.setFechaMaxEntrega(fechaMaxEntrega);
                }
            }

            List<MpDireccionamiento> listadireccionamientoOrdenada = listadireccionamientos.stream()
                    .sorted(Comparator.comparingInt(MpDireccionamiento::getId))
                    .collect(Collectors.toList());

            setListadireccionamientos(listadireccionamientoOrdenada);

            PrimeFaces.current().ajax().update("frmDireccionamiento");
            PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Direccionamiento", "Entrega Eliminada de Manera Exitosa"));
        }
    }

    public void onRowEdit(RowEditEvent event) {

        MpDireccionamiento direccionamientoEditado = (MpDireccionamiento) event.getObject();
        Integer validar = direccionamientoEditado.getEntregaTotal();
        if (validar == null) {

        } else {
            int entregaTotalActual = direccionamientoEditado.getEntregaTotal();
            int sumaEntregas = 0;

            for (MpDireccionamiento dir : listadireccionamientos) {
                sumaEntregas += dir.getEntregaTotal();
            }
            int diferencia = 0;
            int Valores = 0;
            if (calculoEntregaDisponible == 0) {
                if (!direccionamientoDireccionado.isEmpty()) {
                    for (MpDireccionamiento direccionamientosRealizados : direccionamientoDireccionado) {
                        Valores += direccionamientosRealizados.getEntregaTotal();
                    }
                    diferencia = direccionamiento.getEntregaTotal() - Valores;

                    calculoEntregaDisponible = diferencia - sumaEntregas;
                } else {
                    diferencia = direccionamiento.getEntregaTotal() - sumaEntregas;
                    calculoEntregaDisponible = diferencia;
                }

            } else {
                for (MpDireccionamiento direccionamientosRealizados : direccionamientoDireccionado) {
                    Valores += direccionamientosRealizados.getEntregaTotal();
                }
                if (!direccionamientoDireccionado.isEmpty()) {
                    diferencia = direccionamiento.getEntregaTotal() - (sumaEntregas + Valores);
                    calculoEntregaDisponible = diferencia;
                } else {
                    diferencia = direccionamiento.getEntregaTotal() - sumaEntregas;
                    calculoEntregaDisponible = diferencia;
                }
            }

            if (calculoEntregaDisponible >= 0) {

                for (MpDireccionamiento direccionamientoActual : listadireccionamientos) {
                    if (Objects.equals(direccionamientoActual.getConsecutivoEntrega(), direccionamientoEditado.getConsecutivoEntrega())) {
                        direccionamientoActual.setEntregaTotal(direccionamientoEditado.getEntregaTotal());
                        direccionamientoActual.setConsecutivoEntrega(direccionamientoEditado.getConsecutivoEntrega());
                        direccionamientoActual.setNombreSede(direccionamiento.getNombreSede());
                        direccionamientoActual.setSubEntrega(0);
                        direccionamientoActual.setFechaMaxEntrega(direccionamientoEditado.getFechaMaxEntrega());
                        break;
                    }
                }
                if (direccionamiento.getEsEntregaDiferida()) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(direccionamientoEditado.getFechaMaxEntrega());
                    cal.add(Calendar.MONTH, -1);
                    direccionamiento.setFechaEnvioAuto(cal.getTime());
                    PrimeFaces.current().ajax().update("frmDireccionamiento:envioAuto");
                }

                PrimeFaces.current().ajax().update("frmDireccionamiento:disentrega");
                PrimeFaces.current().ajax().update("frmDireccionamiento:disentrega2");

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Direccionamiento", "actualizado correctamente"));
            } else {
                direccionamientoEditado.setEntregaTotal(0);
                PrimeFaces.current().ajax().update("frmDireccionamiento:tablaRegistrosDirec");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Direccionamiento", "La Cantidad Entrega Excede La Cantidad Disponible"));
                onRowEdit(event);
            }
        }
    }

    public void onRowCancel(RowEditEvent event) {
        MpDireccionamiento direccionamiento = (MpDireccionamiento) event.getObject();
    }

    public void agregarNuevaEntrega() {
        if (direccionamiento.getEsEntregaParcial() == true && getListadireccionamientos().size() >= 3) {
            addError("Entrega Diferida: No Permite Más De 3 Direccionamientos");
            generarMensajes();
            return;
        }
        if (calculoEntregaDisponible > 0) {
            MpDireccionamiento nuevaEntrega = new MpDireccionamiento();

            if (!listadireccionamientos.isEmpty()) {
                MpDireccionamiento ultimaEntrega = listadireccionamientos.get(listadireccionamientos.size() - 1);
                nuevaEntrega.setPrestadorNumeroDocumentoStr(ultimaEntrega.getPrestadorNumeroDocumentoStr());
                nuevaEntrega.setNombreSede(ultimaEntrega.getNombreSede());
                nuevaEntrega.setCodigoSede(ultimaEntrega.getCodigoSede());
                nuevaEntrega.setCodigoHabilitacionSede(ultimaEntrega.getCodigoHabilitacionSede());
                nuevaEntrega.setFaxSede(ultimaEntrega.getFaxSede());
                nuevaEntrega.setTelefonoCitasSede(ultimaEntrega.getTelefonoCitasSede());
                nuevaEntrega.setTelefonoAdministrativoSede(ultimaEntrega.getTelefonoAdministrativoSede());
                nuevaEntrega.setCorreoElectronicoSede(ultimaEntrega.getCorreoElectronicoSede());
                nuevaEntrega.setDireccionSede(ultimaEntrega.getDireccionSede());
                nuevaEntrega.setNombreSede(ultimaEntrega.getNombreSede());
                nuevaEntrega.setConsecutivoEntrega(ultimaEntrega.getConsecutivoEntrega() + 1);
                nuevaEntrega.setId(ultimaEntrega.getConsecutivoEntrega() + 1);

                nuevaEntrega.setMaeTipoDocumentoPrestadorId(direccionamiento.getMaeTipoDocumentoPrestadorId());
                nuevaEntrega.setMaeTipoDocumentoPrestadorCodigo(direccionamiento.getMaeTipoDocumentoPrestadorCodigo());
                nuevaEntrega.setMaeTipoDocumentoPrestadorValor(direccionamiento.getMaeTipoDocumentoPrestadorValor());
                nuevaEntrega.setPrestadorNumeroDocumento(direccionamiento.getPrestadorNumeroDocumento());
                nuevaEntrega.setPrestadorRazonSocial(direccionamiento.getPrestadorRazonSocial());

            } else {
                if (direccionamientoDireccionado.size() > 0) {
                    nuevaEntrega.setConsecutivoEntrega(numDirecionamientoDireccionado + 1);
                    nuevaEntrega.setId(numDirecionamientoDireccionado + 1);
                } else {
                    nuevaEntrega.setConsecutivoEntrega(1);
                    nuevaEntrega.setId(1);
                }
                if (direccionamiento.getPrestadorNumeroDocumentoStr() != null) {
                    nuevaEntrega.setPrestadorNumeroDocumentoStr(direccionamiento.getPrestadorNumeroDocumentoStr());
                    nuevaEntrega.setNombreSede(direccionamiento.getNombreSede());
                    nuevaEntrega.setCodigoSede(direccionamiento.getCodigoSede());
                    nuevaEntrega.setCodigoHabilitacionSede(direccionamiento.getCodigoHabilitacionSede());
                    nuevaEntrega.setFaxSede(direccionamiento.getFaxSede());
                    nuevaEntrega.setTelefonoCitasSede(direccionamiento.getTelefonoCitasSede());
                    nuevaEntrega.setTelefonoAdministrativoSede(direccionamiento.getTelefonoAdministrativoSede());
                    nuevaEntrega.setCorreoElectronicoSede(direccionamiento.getCorreoElectronicoSede());
                    nuevaEntrega.setDireccionSede(direccionamiento.getDireccionSede());
                    nuevaEntrega.setNombreSede(direccionamiento.getNombreSede());

                    nuevaEntrega.setMaeTipoDocumentoPrestadorId(direccionamiento.getMaeTipoDocumentoPrestadorId());
                    nuevaEntrega.setMaeTipoDocumentoPrestadorCodigo(direccionamiento.getMaeTipoDocumentoPrestadorCodigo());
                    nuevaEntrega.setMaeTipoDocumentoPrestadorValor(direccionamiento.getMaeTipoDocumentoPrestadorValor());
                    nuevaEntrega.setPrestadorNumeroDocumento(direccionamiento.getPrestadorNumeroDocumento());
                    nuevaEntrega.setPrestadorRazonSocial(direccionamiento.getPrestadorRazonSocial());
                }
            }

            nuevaEntrega.setEntregaTotal(0);
            nuevaEntrega.setSubEntrega(0);
            Date fechaActual = new Date();

            if (direccionamientoDireccionado.size() == 0) {
                if (!listadireccionamientos.isEmpty()) {
                    MpDireccionamiento ultimaEntrega = listadireccionamientos.get(listadireccionamientos.size() - 1);
                    fechaActual = ultimaEntrega.getFechaMaxEntrega();
                }
            } else {

                if (listadireccionamientos.isEmpty()) {
                    MpDireccionamiento ultimaEntrega = direccionamientoDireccionado.get(0);
                    fechaActual = ultimaEntrega.getFechaMaxEntrega();
                } else {
                    if (!listadireccionamientos.isEmpty()) {
                        MpDireccionamiento ultimaEntrega = listadireccionamientos.get(listadireccionamientos.size() - 1);
                        fechaActual = ultimaEntrega.getFechaMaxEntrega();
                    }
                }
            }
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaActual);
            calendario.add(Calendar.DAY_OF_MONTH, 30);
            Date fechaMaxEntrega = calendario.getTime();
            nuevaEntrega.setFechaMaxEntrega(fechaMaxEntrega);

            listadireccionamientos.add(nuevaEntrega);

            calculoEntregaDisponible = calculoEntregaDisponible - nuevaEntrega.getEntregaTotal();
            PrimeFaces.current().ajax().update("frmDireccionamiento:disentrega");
            PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
        } else {
            addError("Direccionamiento: La Cantidad Entrega Excede La Cantidad Disponible");
        }
        generarMensajes();
    }

    public void reiniciarNotificacion() {
        renderizarPanelNotificar = false;
        notificarPaciente = false;
        notificarPrestador = false;
        notificarCorreoPaciente = false;
        notificarCorreoPrestador = false;
        notificarSmsPaciente = false;
        notificarSmsPrestador = false;
        correoPrestador = null;
        telefonoPrestador = null;
        correoPaciente = null;
        telefonoPaciente = null;
    }

    public void entregaParcial() {
        try {
            if (direccionamiento.getEsEntregaParcial()) {
                if (direccionamiento.getEsEntregaDiferida() == true && direccionamiento.getEsEntregaDiferida() != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se permite activar Entrega Parcial y Entrega Diferida al mismo tiempo."));
                    direccionamiento.setEsEntregaParcial(false);
                } else {
                    setMostrarPanelParcial(true);
                    eliminarUltimosRegistros(getListadireccionamientos());
                    direccionamiento.setEsEntregaDiferida(false);
                }
            } else {
                setMostrarPanelParcial(false);
                direccionamiento.setCodigoEntregaParcial(null);
                entregasLista();
                direccionamiento.setNombreSede("");
                direccionamiento.setPrestadorNumeroDocumentoStr("");
                direccionamiento.setCodigoHabilitacionSede("");
                direccionamiento.setTelefonoAdministrativoSede("");
                direccionamiento.setCorreoElectronicoSede("");
                PrimeFaces.current().ajax().update("frmDireccionamiento:panelDireccionamientoPrestador");
            }

            PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
            PrimeFaces.current().ajax().update("frmDireccionamiento:disentrega");
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void eliminarUltimosRegistros(List<MpDireccionamiento> lista) {
        if (lista.size() <= 3) {
            return;
        }

        List<MpDireccionamiento> nuevaLista = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            nuevaLista.add(lista.get(i));
        }

        for (int i = lista.size() - 1; i >= 3; i--) {
            MpDireccionamiento direc = lista.get(i);
            calculoEntregaDisponible += direc.getEntregaTotal();
        }
        lista.clear();
        lista.addAll(nuevaLista);
    }

    public void entregaDiferida() {
        if (direccionamiento.getEsEntregaDiferida()) {

            if (direccionamiento.getEsEntregaParcial()) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se permite activar Entrega Diferida y Entrega Parcial al mismo tiempo."));

                direccionamiento.setEsEntregaDiferida(false);
            } else {

                setMostrarPanelDiferido(true);
                eliminarUltimosRegistrosDife(getListadireccionamientos());

                direccionamiento.setEsEntregaParcial(false);
                mostrarPanelSinFecha = false;
                mostrarPanelConFecha = true;
                direccionamiento.setEnvioCorreoAuto(true);

                List<MpDireccionamiento> listaDireccionamientos = getListadireccionamientos();
                Date maxFechaEntrega = null;
                for (MpDireccionamiento direccionamiento : listaDireccionamientos) {
                    maxFechaEntrega = direccionamiento.getFechaMaxEntrega();

                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(maxFechaEntrega);
                cal.add(Calendar.MONTH, -1);
                direccionamiento.setFechaEnvioAuto(cal.getTime());
            }
        } else {

            direccionamiento.setCodigoEntregaDiferida(null);
            direccionamiento.setEnvioCorreoAuto(false);
            direccionamiento.setFechaEnvioAuto(null);
            setMostrarPanelDiferido(false);
            entregasLista();
            direccionamiento.setNombreSede(null);
            direccionamiento.setPrestadorNumeroDocumentoStr(null);
            direccionamiento.setCodigoHabilitacionSede(null);
            direccionamiento.setTelefonoAdministrativoSede(null);
            direccionamiento.setCorreoElectronicoSede(null);
            direccionamiento.setNumeroPrescripcionAso(null);
            listaPrescripcionDto = new ArrayList<>();
            mostrarTablaItems = false;
            mostrarPrescripcionAsociada = false;

            mostrarPanelSinFecha = true;
            mostrarPanelConFecha = false;

            PrimeFaces.current().ajax().update("frmDireccionamiento:panelDireccionamientoPrestador");
        }

        PrimeFaces.current().ajax().update("frmDireccionamiento:panelListaDireccionamiento");
        PrimeFaces.current().ajax().update("frmDireccionamiento:direc");
        PrimeFaces.current().ajax().update("frmDireccionamiento:direc1");
        PrimeFaces.current().ajax().update("frmDireccionamiento:disentrega");
    }

    public void eliminarUltimosRegistrosDife(List<MpDireccionamiento> lista) {
        if (lista.size() <= 1) {
            return;
        }

        List<MpDireccionamiento> nuevaLista = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            nuevaLista.add(lista.get(i));
        }

        for (int i = lista.size() - 1; i >= 1; i--) {
            MpDireccionamiento direc = lista.get(i);
            calculoEntregaDisponible += direc.getEntregaTotal();
        }
        lista.clear();
        lista.addAll(nuevaLista);
    }

    public void onRowSelectTecnologia(SelectEvent event) {
        MpDetalleDTO detalle = (MpDetalleDTO) event.getObject();
        noDireccionado.setConTecAsociada(detalle.getConsecutivo());
    }

    public void onRowSelectTecnologiaDirec(SelectEvent event) {
        MpDetalleDTO detalle = (MpDetalleDTO) event.getObject();
        direccionamiento.setConsecutivoTecAsociada(detalle.getConsecutivo().toString());
    }

    public void onRowSelectMaMedicamento(SelectEvent event) {
        MaMedicamento medicamento = (MaMedicamento) event.getObject();
        setIdSeleccionTecnologia(medicamento.getId());// medicamentos si esta buscando por contrato
        setTipoSeleccionTecnologia(2);
        direccionamiento.setCodigoMpEntrega(medicamento.getCum());
        direccionamiento.setCodigoMpPropio(medicamento.getDescripcionInvima());
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelCodDirecciona");
        PrimeFaces.current().executeScript("PF('dialogoMaMedicamento').hide()");

    }

    public void onRowSelectMaMedicamentoAnulado(SelectEvent event) {
        MaMedicamento medicamento = (MaMedicamento) event.getObject();
        setIdSeleccionTecnologiaAnulada(medicamento.getId());
        setTipoSeleccionTecnologiaAnulada(2);
        direccionamientoPorAnulacion.setCodigoMpEntrega(medicamento.getCum());
        direccionamientoPorAnulacion.setCodigoMpPropio(medicamento.getDescripcionInvima());
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().executeScript("PF('dialogoMaMedicamentoAnulado').hide()");

    }

    public void onRowSelectMaInsumo(SelectEvent event) {
        MaInsumo insumo = (MaInsumo) event.getObject();
        direccionamiento.setCodigoMpEntrega(insumo.getCodigo());
        direccionamiento.setCodigoMpPropio(insumo.getDescripcion());
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelCodDirecciona");
        PrimeFaces.current().executeScript("PF('dialogoMaInsumo').hide()");

    }

    public void onRowSelectMaInsumoMipres(SelectEvent event) {
        MaInsumoMipres insumo = (MaInsumoMipres) event.getObject();
        setIdSeleccionTecnologia(insumo.getMaInsumo().getId());
        setTipoSeleccionTecnologia(3);
        direccionamiento.setCodigoMpEntrega(insumo.getCodigoMipres());
        direccionamiento.setCodigoMpPropio(insumo.getMaInsumo().getDescripcion());
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelCodDirecciona");
        PrimeFaces.current().executeScript("PF('dialogoMaInsumoMipres').hide()");

    }

    public void onRowSelectMaPaqueteMipres(SelectEvent event) {
        MaPaqueteMipres paquete = (MaPaqueteMipres) event.getObject();
        setIdSeleccionTecnologia(paquete.getMaPaquete().getId());
        setTipoSeleccionTecnologia(4);
        direccionamiento.setCodigoMpEntrega(paquete.getCodigoMipres());
        direccionamiento.setCodigoMpPropio(paquete.getDescripcionMipres());
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelCodDirecciona");
        PrimeFaces.current().executeScript("PF('dialogoMaPaqueteMipres').hide()");

    }

    public void onRowSelectMaMpMipres(SelectEvent event) {
        MaTecnologiaMipres tecno = (MaTecnologiaMipres) event.getObject();
        setIdSeleccionTecnologia(tecno.getMaTecnologia().getId());
        setTipoSeleccionTecnologia(1);
        direccionamiento.setCodigoMpEntrega(tecno.getCodigoMipres());
        direccionamiento.setCodigoMpPropio(tecno.getMaTecnologia().getPropioDescripcion());
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelCodDirecciona");
        PrimeFaces.current().executeScript("PF('dialogoMaMipresTecnologia').hide()");

    }

    public void onRowSelectMaInsumoAnulado(SelectEvent event) {
        MaInsumo insumo = (MaInsumo) event.getObject();
        direccionamientoPorAnulacion.setCodigoMpEntrega(insumo.getCodigo());
        direccionamientoPorAnulacion.setCodigoMpPropio(insumo.getDescripcion());
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().executeScript("PF('dialogoMaInsumoAnulado').hide()");

    }

    public void onRowSelectMaMpAnulado(SelectEvent event) {
        MaTecnologiaMipres tecno = (MaTecnologiaMipres) event.getObject();
        setIdSeleccionTecnologiaAnulada(tecno.getMaTecnologia().getId());
        setTipoSeleccionTecnologiaAnulada(1);
        direccionamientoPorAnulacion.setCodigoMpEntrega(tecno.getCodigoMipres());
        direccionamientoPorAnulacion.setCodigoMpPropio(tecno.getMaTecnologia().getPropioDescripcion());
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().executeScript("PF('dialogoMaMpMipresAnulado').hide()");

    }

    public void onRowSelectMaInsumoMipresAnulado(SelectEvent event) {
        MaInsumoMipres insumo = (MaInsumoMipres) event.getObject();
        setIdSeleccionTecnologiaAnulada(insumo.getMaInsumo().getId());
        setTipoSeleccionTecnologiaAnulada(3);
        direccionamientoPorAnulacion.setCodigoMpEntrega(insumo.getCodigoMipres());
        direccionamientoPorAnulacion.setCodigoMpPropio(insumo.getMaInsumo().getDescripcion());
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().executeScript("PF('dialogoMaInsumoMipresAnulado').hide()");

    }

    public void onRowSelectMaPaqueteMipresAnulado(SelectEvent event) {
        MaPaqueteMipres paquete = (MaPaqueteMipres) event.getObject();
        setIdSeleccionTecnologiaAnulada(paquete.getMaPaquete().getId());
        setTipoSeleccionTecnologiaAnulada(4);
        direccionamientoPorAnulacion.setCodigoMpEntrega(paquete.getCodigoMipres());
        direccionamientoPorAnulacion.setCodigoMpPropio(paquete.getDescripcionMipres());
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().executeScript("PF('dialogoMaPaqueteMipresAnulado').hide()");

    }

    public void onRowSelectMaTecnologia(SelectEvent event) {
        MaTecnologia tecnologia = (MaTecnologia) event.getObject();
        setIdSeleccionTecnologia(tecnologia.getId());//tecnologia esta buscando por contrato tambien
        setTipoSeleccionTecnologia(1);
        direccionamiento.setCodigoMpEntrega(tecnologia.getCups());
        direccionamiento.setCodigoMpPropio(tecnologia.getCupsDescipcion());
        PrimeFaces.current().ajax().update("frmDireccionamiento:panelCodDirecciona");
        PrimeFaces.current().executeScript("PF('dialogoMaTecnologia').hide()");

    }

    public void onRowSelectMaTecnologiaAnulado(SelectEvent event) {
        MaTecnologia tecnologia = (MaTecnologia) event.getObject();
        setIdSeleccionTecnologiaAnulada(tecnologia.getId());
        setTipoSeleccionTecnologiaAnulada(1);
        direccionamientoPorAnulacion.setCodigoMpEntrega(tecnologia.getCups());
        direccionamientoPorAnulacion.setCodigoMpPropio(tecnologia.getCupsDescipcion());
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().executeScript("PF('dialogoMaTecnologiaAnulado').hide()");

    }

    public List<SelectItem> inicializarCausasNoDireccionamiento(int valor) {
        List<SelectItem> listaCausasNoDireccionamiento = new ArrayList<>();

        switch (valor) {
            case 1:
                listaCausasNoDireccionamiento.add(new SelectItem(1, "Misma solicitud en otra prescripción"));
                listaCausasNoDireccionamiento.add(new SelectItem(2, "Existe evidencia de interacción o reacción medicamentosa"));
                listaCausasNoDireccionamiento.add(new SelectItem(3, "La indicación de uso del medicamento no está aprobada por el INVIMA"));
                listaCausasNoDireccionamiento.add(new SelectItem(5, "Suministro por tutela"));
                listaCausasNoDireccionamiento.add(new SelectItem(6, "Paciente corresponde a otra EPS"));
                listaCausasNoDireccionamiento.add(new SelectItem(8, "Paciente fallecido"));
                listaCausasNoDireccionamiento.add(new SelectItem(10, "No se han agotado los topes o su prescripción corresponde a los condicionamientos de cobertura del PBS"));
                listaCausasNoDireccionamiento.add(new SelectItem(14, "El INVIMA no aprobó el MVND"));
                listaCausasNoDireccionamiento.add(new SelectItem(16, "El prescriptor y el paciente son el mismo"));
                listaCausasNoDireccionamiento.add(new SelectItem(17, "Tecnología incluida en el Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(18, "Exclusión del Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(19, "Tecnología cubierta por otro Plan Adicional en Salud"));
                break;
            case 2:
                listaCausasNoDireccionamiento.add(new SelectItem(1, "Misma solicitud en otra prescripción"));
                listaCausasNoDireccionamiento.add(new SelectItem(5, "Suministro por tutela"));
                listaCausasNoDireccionamiento.add(new SelectItem(6, "Paciente corresponde a otra EPS"));
                listaCausasNoDireccionamiento.add(new SelectItem(8, "Paciente fallecido"));
                listaCausasNoDireccionamiento.add(new SelectItem(16, "El prescriptor y el paciente son el mismo"));
                listaCausasNoDireccionamiento.add(new SelectItem(17, "Tecnología incluida en el Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(18, "Exclusión del Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(19, "Tecnología cubierta por otro Plan Adicional en Salud"));
                break;
            case 3:
                listaCausasNoDireccionamiento.add(new SelectItem(1, "Misma solicitud en otra prescripción"));
                listaCausasNoDireccionamiento.add(new SelectItem(5, "Suministro por tutela"));
                listaCausasNoDireccionamiento.add(new SelectItem(6, "Paciente corresponde a otra EPS"));
                listaCausasNoDireccionamiento.add(new SelectItem(8, "Paciente fallecido"));
                listaCausasNoDireccionamiento.add(new SelectItem(10, "No se han agotado los topes o su prescripción corresponde a los condicionamientos de cobertura del PBS"));
                listaCausasNoDireccionamiento.add(new SelectItem(16, "El prescriptor y el paciente son el mismo"));
                listaCausasNoDireccionamiento.add(new SelectItem(17, "Tecnología incluida en el Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(18, "Exclusión del Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(19, "Tecnología cubierta por otro Plan Adicional en Salud"));
                break;
            case 4:
                listaCausasNoDireccionamiento.add(new SelectItem(1, "Misma solicitud en otra prescripción"));
                listaCausasNoDireccionamiento.add(new SelectItem(2, "Existe evidencia de interacción o reacción medicamentosa"));
                listaCausasNoDireccionamiento.add(new SelectItem(5, "Suministro por tutela"));
                listaCausasNoDireccionamiento.add(new SelectItem(6, "Paciente corresponde a otra EPS"));
                listaCausasNoDireccionamiento.add(new SelectItem(8, "Paciente fallecido"));
                listaCausasNoDireccionamiento.add(new SelectItem(14, "El INVIMA no aprobó el MVND"));
                listaCausasNoDireccionamiento.add(new SelectItem(16, "El prescriptor y el paciente son el mismo"));
                listaCausasNoDireccionamiento.add(new SelectItem(18, "Exclusión del Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(19, "Tecnología cubierta por otro Plan Adicional en Salud"));
                break;
            case 5:
                listaCausasNoDireccionamiento.add(new SelectItem(1, "Misma solicitud en otra prescripción"));
                listaCausasNoDireccionamiento.add(new SelectItem(5, "Suministro por tutela"));
                listaCausasNoDireccionamiento.add(new SelectItem(6, "Paciente corresponde a otra EPS"));
                listaCausasNoDireccionamiento.add(new SelectItem(8, "Paciente fallecido"));
                listaCausasNoDireccionamiento.add(new SelectItem(14, "El INVIMA no aprobó el MVND"));
                listaCausasNoDireccionamiento.add(new SelectItem(16, "El prescriptor y el paciente son el mismo"));
                listaCausasNoDireccionamiento.add(new SelectItem(18, "Exclusión del Plan de Beneficios en Salud"));
                listaCausasNoDireccionamiento.add(new SelectItem(19, "Tecnología cubierta por otro Plan Adicional en Salud"));
                break;
        }

        return listaCausasNoDireccionamiento;
    }

    public List<SelectItem> inicializarCausasNoDireccionamientoParcial(int valor) {
        List<SelectItem> listaCausasNoDireccionamientoPar = new ArrayList<>();

        switch (valor) {
            case 1:
                listaCausasNoDireccionamientoPar.add(new SelectItem(4, "Prestación no fraccionable"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(11, "La prescripción excede la dosis máxima recomendada"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(12, "La prescripción excede los tres meses tratándose de una formulación de primera vez"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(13, "La prescripción excede el año y no está formulada como sucesiva"));

                break;
            case 2:
                listaCausasNoDireccionamientoPar.add(new SelectItem(4, "Prestación no fraccionable"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(12, "La prescripción excede los tres meses tratándose de una formulación de primera vez"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(13, "La prescripción excede el año y no está formulada como sucesiva"));

                break;
            case 3:
                listaCausasNoDireccionamientoPar.add(new SelectItem(12, "La prescripción excede los tres meses tratándose de una formulación de primera vez"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(13, "La prescripción excede el año y no está formulada como sucesiva"));

                break;
            case 4:
                listaCausasNoDireccionamientoPar.add(new SelectItem(4, "Prestación no fraccionable"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(11, "La prescripción excede la dosis máxima recomendada"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(12, "La prescripción excede los tres meses tratándose de una formulación de primera vez"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(13, "La prescripción excede el año y no está formulada como sucesiva"));

                break;
            case 5:

                listaCausasNoDireccionamientoPar.add(new SelectItem(12, "La prescripción excede los tres meses tratándose de una formulación de primera vez"));
                listaCausasNoDireccionamientoPar.add(new SelectItem(13, "La prescripción excede el año y no está formulada como sucesiva"));

                break;
        }

        return listaCausasNoDireccionamientoPar;
    }

    public List<SelectItem> inicializarCausasNoDireccionamientoDiferido(int valor) {
        List<SelectItem> listaCausasNoDireccionamientoPar = new ArrayList<>();

        switch (valor) {
            case 1:
                listaCausasNoDireccionamientoPar.add(new SelectItem(15, "El paciente tiene suministro de otra prescripcion"));
                break;
            case 4:
                listaCausasNoDireccionamientoPar.add(new SelectItem(15, "El paciente tiene suministro de otra prescripcion"));
                break;
            case 5:
                listaCausasNoDireccionamientoPar.add(new SelectItem(15, "El paciente tiene suministro de otra prescripcion"));
                break;
        }

        return listaCausasNoDireccionamientoPar;
    }

    public void validarAsociada(String prescripcion) {
        if (noDireccionado.getCodigoNoDireccionamiento() == 1) {
            mostrarPrescripcionAsociada = true;
            getMipresServicio().consultarPrescripcionPorId(this);
            List<SelectItem> nuevaListaPrescripcionesAsociadas = new ArrayList<>();
            for (SelectItem selectItem : listaPrescripcionesAsociadas) {
                if (!selectItem.getLabel().equals(prescripcion)) {
                    nuevaListaPrescripcionesAsociadas.add(selectItem);
                }
            }

            // Reemplazar la lista original con la nueva lista filtrada
            listaPrescripcionesAsociadas = nuevaListaPrescripcionesAsociadas;

        } else {
            listaPrescripcionesAsociadas = new ArrayList<>();
            listaPrescripcionDto = new ArrayList<>();
            noDireccionado.setNumeroPrescripcionAsociada(null);
            setMostrarPrescripcionAsociada(false);
            setMostrarTablaItems(false);
        }
        PrimeFaces.current().ajax().update("frmNoDireccionar");
    }

    public void validarPrescripcionAsociada() {
        if (noDireccionado.getNumeroPrescripcionAsociada() != null) {
            getMipresServicio().consultarItemsPorId(this);
            for (SelectItem selectItem : listaPrescripcionesAsociadas) {
                Integer valueItem = 0;
                Integer valueItemSelec = 0;
                valueItem = (Integer) selectItem.getValue();
                valueItemSelec = Integer.parseInt(noDireccionado.getNumeroPrescripcionAsociada());
                if (Objects.equals(valueItem, valueItemSelec)) {
                    this.setValorAsociado(selectItem.getLabel());
                }
            }
            if (listaPrescripcionDto != null) {
                mostrarTablaItems = true;
            } else {
                mostrarTablaItems = false;
            }

        } else {
            listaPrescripcionDto = new ArrayList<>();
            mostrarTablaItems = false;
        }
        PrimeFaces.current().ajax().update("frmNoDireccionar");
    }

    public void buscarAsociada(String prescripcion) {
        if (direccionamiento.getEsEntregaDiferida() && direccionamiento.getCodigoEntregaDiferida() != null) {
            mostrarPrescripcionAsociada = true;
            getMipresServicio().consultarPrescripcionPorId(this);

            List<SelectItem> nuevaListaPrescripcionesAsociadas = new ArrayList<>();
            for (SelectItem selectItem : listaPrescripcionesAsociadas) {
                if (!selectItem.getLabel().equals(prescripcion)) {
                    nuevaListaPrescripcionesAsociadas.add(selectItem);
                }
            }

            // Reemplazar la lista original con la nueva lista filtrada
            listaPrescripcionesAsociadas = nuevaListaPrescripcionesAsociadas;

        } else {
            listaPrescripcionesAsociadas = new ArrayList<>();
            listaPrescripcionDto = new ArrayList<>();
            direccionamiento.setCodigoEntregaDiferida(null);
            setMostrarPrescripcionAsociada(false);
            setMostrarTablaItems(false);
        }
        PrimeFaces.current().ajax().update("frmDireccionamiento");
    }

    public void validarPrescripcionAso() {
        if (direccionamiento.getNumeroPrescripcionAso() != null) {
            getMipresServicio().consultarItemsPorIdDir(this);
            for (SelectItem item : listaPrescripcionesAsociadas) {
                if (item.getValue().equals(Integer.parseInt(direccionamiento.getNumeroPrescripcionAso()))) {
                    valorAsociado = (item.getLabel());
                    break;
                }
            }
            if (listaPrescripcionDto != null) {
                mostrarTablaItems = true;
            } else {
                mostrarTablaItems = false;
            }

        } else {
            listaPrescripcionDto = new ArrayList<>();
            mostrarTablaItems = false;
        }
        PrimeFaces.current().ajax().update("frmDireccionamiento");
    }

    public String fechaFormat(Date fecha) {
        String resultado = "";
        Date fechaPrescripcion = fecha;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        resultado = sdf.format(fechaPrescripcion);
        return resultado;
    }
//
//    public String horaFormat(Date fecha) {
//        String resultado = "";
//        if (fecha instanceof Time) {
//            Time time = (Time) fecha;
//            resultado = formatTimeUsingAppropriateMethods(time);
//        } else {
//            LocalTime localTime = LocalTime.from(fecha.toInstant());
//        }
//        return resultado;
//    }
//
//    private String formatTimeUsingAppropriateMethods(Time time) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(time);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        int second = calendar.get(Calendar.SECOND);
//        String amPm = hour < 12 ? "am" : "pm";
//        hour = hour % 12;
//        if (hour == 0) {
//            hour = 12;
//        }
//        return String.format("%02d:%02d:%02d %s", hour, minute, second, amPm);
//    }

    public String horaFormat(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(fecha);
    }

    public Date asignarMinDate() {
        return new Date();
    }

    public Date asignarMaxDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

//    public void validarEdicion(Integer valor) {
//        if (valor == null) {
//            validoParaEditar = false;
//        } else {
//            validoParaEditar = true;
//        }
//        PrimeFaces.current().ajax().update("frmDireccionamiento:tablaRegistrosDirec2");
//    }
//    
//    public void validarEdicion2(Integer valor) {
//        if (valor == null) {
//            validoParaEditar = false;
//        } else {
//            validoParaEditar = true;
//        }
//        PrimeFaces.current().ajax().update("frmDireccionamiento:tablaRegistrosDirec");
//    }
    public String obtenerEstadoNoDir(int valor) {
        String resultado = "";
        switch (valor) {
            case 1:
                resultado = "Misma solicitud en otra prescripción";
                break;
            case 2:
                resultado = "Existe evidencia de interacción o reacción medicamentosa";
                break;
            case 3:
                resultado = "La indicación de uso del medicamento no está aprobada por el INVIMA";
                break;
            case 5:
                resultado = "Suministro por tutela";
                break;
            case 6:
                resultado = "Paciente corresponde a otra EPS";
                break;
            case 8:
                resultado = "Paciente fallecido";
                break;
            case 10:
                resultado = "No se han agotado los topes o su prescripción corresponde a los condicionamientos de cobertura del PBS";
                break;
            case 14:
                resultado = "El INVIMA no aprobó el MVND";
                break;
            case 16:
                resultado = "El prescriptor y el paciente son el mismo";
                break;
            case 17:
                resultado = "Tecnología incluida en el Plan de Beneficios en Salud";
                break;
            case 18:
                resultado = "Exclusión del Plan de Beneficios en Salud";
                break;
            case 19:
                resultado = "Tecnología cubierta por otro Plan Adicional en Salud";
                break;
        }

        return resultado;

    }

    public String obtenerModJunta(String valor) {
        String resultado = "";
        if (valor != null) {
            switch (valor) {
                case "1":
                    resultado = "Presencial";
                    break;
                case "2":
                    resultado = "Virtual";
                    break;

            }
        } else {
            resultado = "";
        }

        return resultado;

    }

    public void validarAnular(Integer valor) {
        if (valor != null) {
            if (valor != 1 && valor != 5) {
                renderizarPanelAnular = true;
            } else {
                renderizarPanelAnular = false;
                requiereAnulacion = false;
            }
        } else {
            renderizarPanelAnular = false;
            requiereAnulacion = false;
        }

        PrimeFaces.current().ajax().update("frmAuditoria");
    }

    public void anularDirec(int id, Integer estado) {
        try {
            getMipresServicio().anularDireccionamiento(this, id, estado);
            crearLog("Anular Direccionamiento", objeto.toString());
            direcciona(this.objeto);
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        addMensaje("Se realizó la solicitud de anulación de direccionamiento correctamente.");
        generarMensajes();
    }

    public void verEntrega(Integer id) {
        try {
            this.setDireccionamientoEntregado(getMipresServicio().verEntrega(id));
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        PrimeFaces.current().executeScript("PF('dialogoVerEntrega').show()");
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
        generarMensajes();
    }

    public void verEntregaD(Integer id) {
        try {
            this.setDireccionamientoEntregado(getMipresServicio().verEntregaD(id));
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        PrimeFaces.current().executeScript("PF('dialogoVerEntrega').show()");
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
        generarMensajes();
    }

    public void verEntregaD(Integer id, Integer prescripcion, Integer item, Integer tipo, Integer dir) {
        try {
            MpPrescripcion prescrip = new MpPrescripcion();
            MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
            MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
            MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();
            MpDireccionamiento direcc = new MpDireccionamiento();
            MpPrescripcionAuditoria auditoria = new MpPrescripcionAuditoria();
            MpDireccionamientoEntregado entrega = new MpDireccionamientoEntregado();
            entrega = getMipresServicio().verEntrega(id);

            MpPrescripcionSuministro prescripSuministro = new MpPrescripcionSuministro();
            prescrip = getMipresServicio().consultarPrescripcion(prescripcion);
            switch (tipo) {
                case 1:
                case 4:
                    medicamento = getMipresServicio().consultarMedicamentoSuministro(item);
                    break;
                case 2:
                    tecnologia = getMipresServicio().consultarTecnologiaSuministro(item);
                    break;
                case 3:
                case 5:
                    insumo = getMipresServicio().consultarInsumoSuministro(item);
                    break;
            }
            if (dir != null) {
                direcc = getMipresServicio().consultarDireccionamientoS(dir);

                Integer entregaTotal = direcc.getEntregaTotal();
                String cantidadDireccionada = entregaTotal.toString();
                prescripSuministro.setCantidadDireccionada(cantidadDireccionada);
            }
            auditoria = getMipresServicio().consultarAudirotiaS(prescripcion);
            if (prescrip.getId() != null && medicamento.getId() != null || tecnologia.getId() != null || insumo.getId() != null && auditoria.getId() != null) {
                prescripSuministro.setIdPrescripcion(prescrip.getId());
                prescripSuministro.setNumeroPrescripcion(prescrip.getNumeroPrescripcion());
                prescripSuministro.setCodigoAmbitoAtencion(prescrip.getCodAmbAte());
                prescripSuministro.setMaDiagnosticoPrincipalCodigo(prescrip.getMaDiagnosticoPrincipalCodigo());
                prescripSuministro.setMaDiagnosticoPrincipalValor(prescrip.getMaDiagnosticoPrincipalValor());
                prescripSuministro.setIdAfiliado(prescrip.getAsegAfiliado().getId());
                prescripSuministro.setPrimerNombreAfiliado(prescrip.getAsegAfiliado().getPrimerNombre());
                prescripSuministro.setPrimerApellidoAfiliado(prescrip.getAsegAfiliado().getPrimerApellido());
                prescripSuministro.setSegundoNombreAfiliado(prescrip.getAsegAfiliado().getSegundoNombre());
                prescripSuministro.setSegundoApellidoAfiliado(prescrip.getAsegAfiliado().getSegundoApellido());
                prescripSuministro.setRegimenAfiliado(prescrip.getAsegAfiliado().getRegimen());
                prescripSuministro.setNumeroDocumentoAfiliado(prescrip.getAsegAfiliado().getNumeroDocumento());
                if (medicamento.getId() != null) {
                    prescripSuministro.setIdItem(medicamento.getId());
                    prescripSuministro.setTipoTecnologiaItem(medicamento.getTipoTecnologia());
                    switch (medicamento.getTipoTecnologia()) {
                        case 1:
                            prescripSuministro.setNombreItem(medicamento.getDescripcionMedicamentoPrincipioActivo());
                            break;
                        case 4:
                            prescripSuministro.setNombreItem(medicamento.getMaeProductosNutricionalesValor());
                            break;
                        default:
                            break;
                    }
                    BigDecimal cantidadTotalFormulada = medicamento.getCantidadTotalFormulada();
                    Integer valorConverter = cantidadTotalFormulada.intValue();
                    String cantidadOrdenada = valorConverter.toString();
                    prescripSuministro.setCantidadOrdenada(cantidadOrdenada);
                } else if (tecnologia.getId() != null) {
                    prescripSuministro.setIdItem(tecnologia.getId());
                    prescripSuministro.setTipoTecnologiaItem(tecnologia.getTipoTecnologia());
                    prescripSuministro.setNombreItem(tecnologia.getMaTecnologiaValor());
                    BigDecimal cantidadTotalFormulada = insumo.getCantidadTotalEntrega();
                    String cantidadOrdenada = cantidadTotalFormulada.toString();
                    prescripSuministro.setCantidadOrdenada(cantidadOrdenada);
                } else if (insumo.getId() != null) {
                    prescripSuministro.setIdItem(insumo.getId());
                    prescripSuministro.setTipoTecnologiaItem(insumo.getTipoTecnologia());
                    switch (insumo.getTipoTecnologia()) {
                        case 3:
                            prescripSuministro.setNombreItem(insumo.getMaeDispositivosNombre());
                            break;
                        case 5:
                            prescripSuministro.setNombreItem(insumo.getMaeServiciosComplementariosNombre());
                            break;
                        default:
                            break;
                    }
                    BigDecimal cantidadTotalFormulada = insumo.getCantidadTotalEntrega();
                    Integer valorConverter = cantidadTotalFormulada.intValue();
                    String cantidadOrdenada = valorConverter.toString();
                    prescripSuministro.setCantidadOrdenada(cantidadOrdenada);
                }

                prescripSuministro.setNotaAuditoria(auditoria.getNotaAuditoria());
                this.setPrescripcionSuministro(prescripSuministro);
                if (dir != null) {
                    this.setDireccionamientoEntregado(getMipresServicio().verEntregaD(dir));
                } else {
                    this.setDireccionamientoEntregado(getMipresServicio().verEntregaSinDireccionamiento(id));
                }

            }

        } catch (Exception ex) {
            addError(ex.getMessage());
        }

        PrimeFaces.current().executeScript("PF('dialogoVerEntrega').show()");
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
        generarMensajes();
    }

    public void verEntregaSuministroD(Integer id) {
        try {
            MpDireccionamientoEntregado entrega = getMipresServicio().verEntregaSumin(id);
            Integer idTipo = null;

            MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
            MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
            MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();

            MpPrescripcionSuministro prescripSuministro = new MpPrescripcionSuministro();
            MpPrescripcion prescrip = entrega.getMpPrescripcion();

            Integer tipo = null;
            if (entrega.getMpPrescripcionMedicamentoId() != null) {
                tipo = 1;
            } else if (entrega.getMpPrescripcionTecnologiaId() != null) {
                tipo = 2;
            } else if (entrega.getMpPrescripcionInsumoId() != null) {
                tipo = 3;
            }
            switch (tipo) {
                case 1:
                case 4:
                    medicamento = entrega.getMpPrescripcionMedicamentoId();
                    idTipo = medicamento.getId();
                    break;
                case 2:
                    tecnologia = entrega.getMpPrescripcionTecnologiaId();
                    idTipo = tecnologia.getId();
                    break;
                case 3:
                case 5:
                    insumo = entrega.getMpPrescripcionInsumoId();
                    idTipo = insumo.getId();
                    break;
            }
            if (entrega.getMpDireccionamientoId() != null) {
                MpDireccionamiento direcc = entrega.getMpDireccionamientoId();
                Integer entregaTotal = direcc.getEntregaTotal();
                prescripSuministro.setCantidadDireccionada(entregaTotal != null ? entregaTotal.toString() : "");
            }
            MpPrescripcionAuditoria auditoria = getMipresServicio().consultarAudirotiaSis(prescrip.getId(), tipo, idTipo);
            if (prescrip != null && (medicamento != null || tecnologia != null || insumo != null)) {
                prescripSuministro.setIdPrescripcion(prescrip.getId());
                prescripSuministro.setNumeroPrescripcion(prescrip.getNumeroPrescripcion());
                prescripSuministro.setCodigoAmbitoAtencion(prescrip.getCodAmbAte());
                prescripSuministro.setMaDiagnosticoPrincipalCodigo(prescrip.getMaDiagnosticoPrincipalCodigo());
                prescripSuministro.setMaDiagnosticoPrincipalValor(prescrip.getMaDiagnosticoPrincipalValor());
                prescripSuministro.setIdAfiliado(prescrip.getAsegAfiliado().getId());
                prescripSuministro.setPrimerNombreAfiliado(prescrip.getAsegAfiliado().getPrimerNombre());
                prescripSuministro.setPrimerApellidoAfiliado(prescrip.getAsegAfiliado().getPrimerApellido());
                prescripSuministro.setSegundoNombreAfiliado(prescrip.getAsegAfiliado().getSegundoNombre());
                prescripSuministro.setSegundoApellidoAfiliado(prescrip.getAsegAfiliado().getSegundoApellido());
                prescripSuministro.setRegimenAfiliado(prescrip.getAsegAfiliado().getRegimen());
                prescripSuministro.setNumeroDocumentoAfiliado(prescrip.getAsegAfiliado().getNumeroDocumento());

                if (medicamento.getId() != null) {
                    prescripSuministro.setIdItem(medicamento.getId());
                    prescripSuministro.setTipoTecnologiaItem(medicamento.getTipoTecnologia());
                    switch (medicamento.getTipoTecnologia()) {
                        case 1:
                            prescripSuministro.setNombreItem(medicamento.getDescripcionMedicamentoPrincipioActivo() + " - " + medicamento.getCodigoFormulaFarmaceutica());
                            break;
                        case 4:
                            prescripSuministro.setNombreItem(medicamento.getMaeProductosNutricionalesValor() + " - " + medicamento.getMaeProductosNutricionalesCodigo());
                            break;
                        default:
                            break;
                    }
                    BigDecimal cantidadTotalFormulada = medicamento.getCantidadTotalFormulada();
                    String cantidadOrdenada;
                    if (cantidadTotalFormulada != null) {
                        try {
                            Integer valorConverter = cantidadTotalFormulada.intValue();
                            cantidadOrdenada = valorConverter.toString();
                        } catch (NumberFormatException e) {
                            cantidadOrdenada = "";
                        }
                    } else {
                        cantidadOrdenada = "";
                    }
                    prescripSuministro.setCantidadOrdenada(cantidadOrdenada);
                } else if (tecnologia.getId() != null) {
                    prescripSuministro.setIdItem(tecnologia.getId());
                    prescripSuministro.setTipoTecnologiaItem(tecnologia.getTipoTecnologia());
                    prescripSuministro.setNombreItem(tecnologia.getMaTecnologiaValor() + " - " + tecnologia.getMaTecnologiaCodigo());
                    BigDecimal cantidadTotalFormulada = tecnologia.getCantidadTotalEntrega();
                    String cantidadOrdenada;
                    if (cantidadTotalFormulada != null) {
                        cantidadOrdenada = cantidadTotalFormulada.toString();
                    } else {
                        cantidadOrdenada = "";
                    }
                    prescripSuministro.setCantidadOrdenada(cantidadOrdenada);

                } else if (insumo.getId() != null) {
                    prescripSuministro.setIdItem(insumo.getId());
                    prescripSuministro.setTipoTecnologiaItem(insumo.getTipoTecnologia());
                    switch (insumo.getTipoTecnologia()) {
                        case 3:
                            prescripSuministro.setNombreItem(insumo.getMaeDispositivosNombre() + " - " + insumo.getMaeDispositivosCodigo());
                            break;
                        case 5:
                            prescripSuministro.setNombreItem(insumo.getMaeServiciosComplementariosNombre() + " - " + insumo.getMaeServiciosComplementariosCodigo());
                            break;
                        default:
                            break;
                    }
                    BigDecimal cantidadTotalFormulada = insumo.getCantidadTotalEntrega();
                    String cantidadOrdenada;
                    if (cantidadTotalFormulada != null) {
                        try {
                            Integer valorConverter = cantidadTotalFormulada.intValue();
                            cantidadOrdenada = valorConverter.toString();
                        } catch (NumberFormatException e) {
                            cantidadOrdenada = "";
                        }
                    } else {
                        cantidadOrdenada = "";
                    }

                    prescripSuministro.setCantidadOrdenada(cantidadOrdenada);

                }
                if (auditoria != null) {
                    prescripSuministro.setNotaAuditoria(auditoria.getNotaAuditoria());
                } else {
                    prescripSuministro.setNotaAuditoria("- SIN AUDITORIA -");
                }
                prescripSuministro.setEntrega(entrega);

                this.setDireccionamientoEntregado(entrega);

                MpEntregaSuministro suministro;
                suministro = getMipresServicio().consultarSuministroDeEntrega(id);

                if (suministro != null) {
                    this.setMpEntregaSuministro(suministro);
                    setDesactivarBotonCrear(true);
                    setMostrarPanelSuministro(true);
                    setMostrarPanelSuministroBotonera(false);
                    setDesactivarBotonGuardar(true);
                    setDesactivarBotonAnular(false);
                    setDesactivarBotonUltimaEntrega(true);
                    setDesactivarBotonEntregaCompleta(true);
                    prescripSuministro.setIdSuministro(suministro.getIdSuministro() != null ? suministro.getIdSuministro() : null);
                } else {
                    setDesactivarBotonCrear(false);
                    setMostrarPanelSuministro(false);
                    setMostrarPanelSuministroBotonera(true);
                    setDesactivarBotonGuardar(false);
                    setDesactivarBotonAnular(true);
                    setDesactivarBotonUltimaEntrega(false);
                    setDesactivarBotonEntregaCompleta(false);
                    prescripSuministro.setIdSuministro(null);
                }
                this.setPrescripcionSuministro(prescripSuministro);
            }

        } catch (Exception ex) {
            addError(ex.getMessage());
        }

        PrimeFaces.current().ajax().update("frmVerItemEntrega");
        PrimeFaces.current().ajax().update("frmVerItemEntrega:panelDialogoSuministroRegistro");
        PrimeFaces.current().ajax().update("frmVerItemEntrega:panelSuministroRegistro");
        PrimeFaces.current().executeScript("PF('dialogoVerEntrega').show()");

        generarMensajes();
    }

    public void verFactura(Integer id) {
        entregaFactura = null;
        try {

            this.entregaFactura = getMipresServicio().consultarFactura(id);

        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        if (entregaFactura != null) {
            PrimeFaces.current().ajax().update("frmVerItemEntregaFacturada");
            PrimeFaces.current().executeScript("PF('dialogoVerEntregaFacturada').show()");
        } else {
            addError("Entrega Aún No cuenta Con Facturación");
        }
        generarMensajes();
    }

    public String obtenerTipoComparador(Short valor) {
        String valor2 = "";
        if (valor == null || valor == 3) {
            valor2 = "No Aplica";
        }
        return valor2;
    }

    public void actualizarAfectaPresupuesto(Boolean afecta, Integer id) {
        getMipresServicio().afectaPresupuestoMax(afecta, id);
        PrimeFaces.current().ajax().update("frmVerItemEntregaFacturada");
    }

    public void cierreCiloFactura(Integer id, Integer entrega) {
        getMipresServicio().cierreCiloFactura(id, this);
        PrimeFaces.current().executeScript("PF('dialogoVerEntregaFacturada').hide()");
        verFactura(entrega);
    }

    public void ActualizarTablaEntrega(Boolean cierre, Integer id) {
        MpDireccionamientoEntregado direcEntrega = new MpDireccionamientoEntregado();
        direcEntrega = (getMipresServicio().actualizarDireEntrega(cierre, id));
        this.setDireccionamientoEntregado(direcEntrega);
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
    }

    public void ActualizarTablaEntregaAnula(Boolean anula, Integer id) {
        MpDireccionamientoEntregado direcEntrega = new MpDireccionamientoEntregado();
        direcEntrega = (getMipresServicio().actualizarDireEntregaAnula(anula, id));
        this.setDireccionamientoEntregado(direcEntrega);
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
    }

    public void ActualizarCompletaUltimaa(Integer val, Integer valor, Integer id) {
        Boolean va;
        if (val == 1) {
            va  = true;
        } else {
            va  = false;
        }
        MpDireccionamientoEntregado direcEntrega = new MpDireccionamientoEntregado();
        direcEntrega = (getMipresServicio().ActualizarCompletaUltima(va, valor, id));
        this.setDireccionamientoEntregado(direcEntrega);
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
    }

    public void ActualizarCompletaUltima(Boolean val, Integer valor, Integer id) {
        MpDireccionamientoEntregado direcEntrega = (getMipresServicio().ActualizarCompletaUltima(val, valor, id));
        this.setDireccionamientoEntregado(direcEntrega);
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
    }

    public void verEntregas(Integer idPrescripcion, Integer idItem, Integer tipo) {
        try {
            inicializarValores();
            this.listaDireccionamientosEntregados = new ArrayList<>();

            listaDireccionamientosEntregados = getMipresServicio().verEntregaSinDireccionamiento(idPrescripcion, idItem, tipo);

        } catch (Exception ex) {
            addError(ex.getMessage());
            ex.printStackTrace();
        }
        PrimeFaces.current().executeScript("PF('dialogoVerEntregas').show()");
        PrimeFaces.current().ajax().update("frmVerItemEntregas");
        generarMensajes();
    }

    public void verEntregasFactura(Integer idPrescripcion, Integer idItem, Integer tipo) {
        try {
            this.listaDireccionamientosEntregados = new ArrayList<>();

            listaDireccionamientosEntregados = getMipresServicio().verEntregaSinDireccionamiento(idPrescripcion, idItem, tipo);

        } catch (Exception ex) {
            addError(ex.getMessage());
            ex.printStackTrace();
        }
        PrimeFaces.current().executeScript("PF('dialogoVerEntregasFacturadas').show()");
        PrimeFaces.current().ajax().update("frmVerItemEntregasFacturadas");
        generarMensajes();
    }

    public void liberar(Integer idItem, Integer tipo) {

        getMipresServicio().liberarTecnologia(idItem, tipo);
        addMensaje("EXITO: Atención Libre");
        generarMensajes();
    }

    public void direccionarDirec() {
        addMensaje("Servicio Para Direccionar Uno a Uno, Aun No Se Encuentra Disponible");
        generarMensajes();
    }

    public void asignarUbicacion(Integer id) {
        this.direccionamiento.setUbicacionSedeId(id);
        direccionamiento.setUbicacionSedeIdStr(getMipresServicio().consultarPrefijo(direccionamiento.getUbicacionSedeId()));
    }

    public void asignarUbicacionAnulado(Integer id) {
        this.direccionamientoPorAnulacion.setUbicacionSedeId(id);
        direccionamientoPorAnulacion.setUbicacionSedeIdStr(getMipresServicio().consultarPrefijo(direccionamientoPorAnulacion.getUbicacionSedeId()));
    }

    public void asignarUbicaciond(Integer id) {
        this.direccionamientoPorAnulacion.setUbicacionSedeId(id);
    }

    public String obtenerTipoEstado(Integer valor) {
        String val = "";

        switch (valor) {
            case 0: {
                val = "Anulado";
            }
            break;
            case 1: {
                val = "Direccionado";
            }
            break;
            case 2: {
                val = "Programado";
            }
            break;
            case 3: {
                val = "Creado";
            }
            break;
            case 5: {
                val = "Anulado";
            }
            break;
            case 6: {
                val = "Programación-Anulada";
            }
            break;

        }
        return val;
    }

    public String obtenerTipoEstadoPrescripcion(Integer valor) {
        String val = "";

        switch (valor) {
            case 1: {
                val = "Modificado";
            }
            break;
            case 2: {
                val = "Anulado";
            }
            break;
            case 4: {
                val = "Activo";
            }
            break;
            case 5: {
                val = "Solicitud Anulación";
            }
            break;

        }
        return val;
    }

    public String establecerColorPrecripcion(Integer valor) {
        String color = "";
        switch (valor) {
            case 1:
                color = "celeste";
                break;
            case 2:
                color = "rojo";
                break;
            case 4:
                color = "verde";
                break;
            case 5:
                color = "naranja";
                break;
            default:
                break;
        }

        return color;
    }

    public void direccionarAnulado(MpDireccionamiento direccionamientoAnulado) {
        direccionamientoPorAnulacion = new MpDireccionamiento();
        direccionamientoPorAnulacion = direccionamientoAnulado;
        reservaValorAnterior = 0;
        disponibleMasAnulado = 0;
        reservaValorAnterior = direccionamientoPorAnulacion.getEntregaTotal();
        disponibleMasAnulado = calculoEntregaDisponible + direccionamientoPorAnulacion.getEntregaTotal();
        direccionamientoPorAnulacion.setUbicacionSedeId(direccionamientoAnulado.getUbicacionSedeId());
        PrimeFaces.current().ajax().update("frmanuladoDireccionar");
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia3");
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelDireccionamientoPrestador5");
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:panelCodDireccionaTecnologia31");
        PrimeFaces.current().executeScript("PF('dialogoDireccionarAnulado').show()");
//        crearLog("Crear Re/Direccionamiento Anulado", objeto.toString());
    }

    public void validarPermisoEntrega(Integer valor) {
        if (valor > disponibleMasAnulado) {
            addError("No Cuenta Con Las Unidades Diponibles Para Direccionar Esa Cantidad");
            direccionamientoPorAnulacion.setEntregaTotal(reservaValorAnterior);
        } else if (valor <= disponibleMasAnulado) {
            direccionamientoPorAnulacion.setEntregaTotal(valor);
        }
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:valoracuerdotrans");
        generarMensajes();
    }

    public void ActualizaJustif() {
        PrimeFaces.current().ajax().update("frmanuladoDireccionar:fechaMax");
    }

    public String obtenerEstadoAnulacion(int valor) {
        String resultado = "";
        switch (valor) {
            case 1:
                resultado = "Modificado";
                break;
            case 2:
                resultado = "Anulado";
                break;
            case 4:
                resultado = "Activo";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String obtenerTipoAnulacion(int valor) {
        String resultado = "";
        switch (valor) {
            case 1:
                resultado = "Por Solicitud De La IPS";
                break;
            case 2:
                resultado = "Por Solicitud De La EPS";
                break;
            case 3:
                resultado = "Por Solicitud Del Prescriptor";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String validarEstadoEntre(Integer valor) {
        String resultado = "";
        switch (valor) {
            case 0:
                resultado = "No Entregado";
                break;
            case 1:
                resultado = "Entregado";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String validarEstadoReporteEntrega(Integer valor) {
        String resultado = "";
        switch (valor) {
            case 0:
                resultado = "Anulado";
                break;
            case 1:
                resultado = "Activo";
                break;
            case 2:
                resultado = "Procesado";
                break;
            default:
                break;
        }
        return resultado;
    }

    public String validarEstadoReporteCiclo(Integer valor) {
        String resultado = "";
        switch (valor) {
            case 0:
                resultado = "Anulado";
                break;
            case 1:
                resultado = "Abierto";
                break;
            case 2:
                resultado = "Cerrado";
                break;
            default:
                break;
        }
        return resultado;
    }

    public void onTecnologiaChange() {
        juntaOpt.clear();
        estadoOpt.clear();
//        if (tipo != null) {

        juntaOpt.add(new SelectItem(1, "No requiere"));
        juntaOpt.add(new SelectItem(2, "Pendiente"));
        juntaOpt.add(new SelectItem(3, "Aprobada"));
        juntaOpt.add(new SelectItem(4, "No aprobada"));

        estadoOpt.add(new SelectItem(1, "Direccionado"));
        estadoOpt.add(new SelectItem(2, "No direccionado"));
        estadoOpt.add(new SelectItem(3, "Pendiente"));
        estadoOpt.add(new SelectItem(4, "Auditado - Direccionado"));
        estadoOpt.add(new SelectItem(5, "Auditado - No direccionamiento"));
        estadoOpt.add(new SelectItem(6, "Ampliación justificación no pbs insuficiente o invalida"));
        estadoOpt.add(new SelectItem(7, "Error en la prescripción: cantidad duración tratamiento"));
        estadoOpt.add(new SelectItem(8, "Avalado Hos_Urg"));
        estadoOpt.add(new SelectItem(9, "No Avalado Hos_Urg"));
        estadoOpt.add(new SelectItem(10, "No direccionamiento sin causa"));
//        }

    }

    public String esUltimaEntrega(Integer valor) {
        String resultado = "";
        switch (valor) {
            case 0:
                resultado = "No";
                break;
            case 1:
                resultado = "Si";
                break;
            default:
                break;
        }
        return resultado;
    }

    public void inicializarValores() {
        setIdPrescripcionEntrega(null);
        setIdItemEntrega(null);
        setTipoTecnologiaEntrega(null);
    }

//     public void listarContratosAprobar() {
//        setParamConsulta4(new ParamConsulta());
//        if (this.getDireccionamiento(). != null) {
//            String tecnologias = String.valueOf(getObjeto().getObjetoTecnologia().getMaTecnologiaId());
//            getParamConsulta4().setParametroConsulta1(getObjeto().getObjetoTecnologia().getTipoTecnologia());
//            getParamConsulta4().setParametroConsulta2(tecnologias);
//            PrimeFaces.current().executeScript("PF('dialogoContratoLista').show()");
//            PrimeFaces.current().ajax().update("frmContratoLista");
//        } else {
//            addError("No se encontro la tecnologia");
//        }
//        generarMensajes();
//    }
    public void crearSuministro(MpDireccionamientoEntregado entrega) {
        setMostrarPanelSuministro(true);
        setMostrarPanelSuministroBotonera(true);
        setDesactivarBotonGuardar(false);
        setDesactivarBotonAnular(true);
        setDesactivarBotonCrear(true);
        mpEntregaSuministro = new MpEntregaSuministro();
        MpEntregaSuministro suministro = new MpEntregaSuministro();
        suministro.setMpDireccionamientoEntregadoId(new MpDireccionamientoEntregado(entrega.getId()));
        suministro.setUltimaEntrega(false);
        suministro.setEntregaCompleta(entrega.getEntregaCompleta());
        if (entrega.getCausaNoEntrega() != null) {
            suministro.setCausaNoEntrega(entrega.getCausaNoEntrega().toString());
        }
        suministro.setNumeroPrescripcionAsociada(entrega.getMpPrescripcion().getNumeroPrescripcion());
        if (entrega.getMpPrescripcionMedicamentoId() != null) {
            suministro.setConsecutivo(entrega.getMpPrescripcionMedicamentoId().getConsecutivoOrden());
        } else if (entrega.getMpPrescripcionTecnologiaId() != null) {
            suministro.setConsecutivo(entrega.getMpPrescripcionTecnologiaId().getConsecutivoOrden());
        } else if (entrega.getMpPrescripcionInsumoId() != null) {
            suministro.setConsecutivo(entrega.getMpPrescripcionInsumoId().getConsecutivoOrden());
        }
        suministro.setCantidadEntrega(entrega.getCantidadEntrega());
        suministro.setNumeroLote(entrega.getNumeroLote());
        suministro.setValorEntregado(entrega.getValorTotal());
        this.setMpEntregaSuministro(suministro);
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
    }

    public void cancelarSuministro() {
        setDesactivarBotonCrear(false);
        setMostrarPanelSuministro(false);
        setMostrarPanelSuministroBotonera(true);
        setDesactivarBotonGuardar(true);
        setDesactivarBotonAnular(true);
        mpEntregaSuministro = new MpEntregaSuministro();
        MpEntregaSuministro suministro = new MpEntregaSuministro();
        this.setMpEntregaSuministro(suministro);
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
    }

    public void guardarSuministro(MpEntregaSuministro suministro) {

        MpEntregaSuministro sum = new MpEntregaSuministro();
        sum.setMpDireccionamientoEntregadoId(new MpDireccionamientoEntregado(suministro.getMpDireccionamientoEntregadoId().getId()));
        sum.setUltimaEntrega(suministro.getUltimaEntrega());
        sum.setEntregaCompleta(suministro.getEntregaCompleta());
        if (suministro.getCausaNoEntrega() != null) {
            suministro.setCausaNoEntrega(suministro.getCausaNoEntrega());
        }
        sum.setNumeroPrescripcionAsociada(suministro.getNumeroPrescripcionAsociada());
        sum.setConsecutivo(suministro.getConsecutivo());
        sum.setCantidadEntrega(suministro.getCantidadEntrega());
        sum.setNumeroLote(suministro.getNumeroLote());
        sum.setValorEntregado(suministro.getValorEntregado());
        sum.setEstadoMipres(1);
        sum.setAnulado(false);
        sum.setUsuarioCrea(this.getConexion().getUsuario().getUsuarioNombre());
        sum.setTerminalCrea(this.getConexion().getIp());
        sum.setFechaHoraCrea(new Date());
        sum.setFechaSuministro(new Date());

        getMipresServicio().guardarSuministro(sum);

        this.setMpEntregaSuministro(suministro);
        PrimeFaces.current().ajax().update("frmVerItemEntrega");
        PrimeFaces.current().executeScript("PF('dialogoVerEntrega').hide()");
        verEntregaSuministroD(suministro.getMpDireccionamientoEntregadoId().getId());
    }

    public void anularSuministro(Integer id, Integer entrega) {

        if (getMipresServicio().anularSuministro(id)) {
            PrimeFaces.current().executeScript("PF('dialogoVerEntrega').hide()");
            verEntregaSuministroD(entrega);
        } else {
            addMensaje("Enviando Suministro A Ministerio. Inténtelo En Unos Minutos");
            generarMensajes();
        }
    }

    public StreamedContent generarPdf(Integer id) {
        StreamedContent streamContent = null;
        try {
            ReporteDireccionamiento objeto = getMipresServicio().consultarDireccionamientoReporte(id);

            if (objeto != null) {
                InputStream is = getClass().getResourceAsStream("/reportes/MipresDireccionamiento.jasper");

                Map<String, Object> parameters = new HashMap<>();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                // Crear la fuente de datos con el objeto
                List<ReporteDireccionamiento> dataList = Collections.singletonList(objeto);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

                // Generar el reporte
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder()
                        .contentType("application/pdf")
                        .stream(() -> stream)
                        .name("Reporte De Direccionamiento.pdf")
                        .build();
            } else {
                addError("Error: no hay datos para generar el reporte");
            }

        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + " " + ex.getMessage());
            generarMensajes();
        }

        return streamContent;
    }

    public StreamedContent generarReporteMipresPlanManejo(Integer id) {
        StreamedContent streamContent = null;
        try {
            List<ReportePlanManejo> listaReportes = getMipresServicio().gestionarPlanDeManejo(id);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/MipresPlanManejo.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                // Generar el reporte
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder()
                        .contentType("application/pdf")
                        .stream(() -> stream)
                        .name("Reporte Mipres PlanManejo.pdf")
                        .build();
            } else {
                addError("Error no hay datos para generar el reporte");
            }

        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        }
        return streamContent;
    }

    private boolean listaValida = true; // Variable para almacenar el estado de la lista

// Método para actualizar el estado de listaValida
    public void actualizarListaValida() {
        listaValida = isListaValida();
    }

// Método que valida si la lista es correcta (sin campos vacíos)
    public boolean isListaValida() {
        for (MpDireccionamiento item : listadireccionamientos) {
            if (item.getJustificacionDireccionamiento() == null || item.getJustificacionDireccionamiento().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean getListaValida() {
        return listaValida;
    }

    public void validarLista() {
        actualizarListaValida(); // Asegura que listaValida tenga el último estado

        PrimeFaces current = PrimeFaces.current();
        if (!listaValida) {
            current.executeScript("PF('dialogValidacion').show();"); // Abre el diálogo si hay errores
        } else {
            guardar(); // Guarda directamente si todo está bien
        }
    }

    public Boolean validarDesactivar(Integer valor) {
        Boolean desactivar = false;
        if (valor == 1) {
            desactivar = false;
        } else if (valor == 2) {
            desactivar = true;
        }
        return desactivar;
    }

    public Boolean validarRender(Integer valor) {
        Boolean render = false;
        if (valor == 2) {
            render = true;
        } else if (valor == 1) {
            render = false;
        }
        return render;
    }

    public void anularNoDireccionado() {
        getMipresServicio().anularNoDireccionamiento(this);
        cerrarModalGestionar();
        generarMensajes();
        PrimeFaces.current().ajax().update("frmPrescripciones");
    }

    private String respuestaSeleccionada;

    public void setRespuestaSeleccionada(String respuesta) {
        this.respuestaSeleccionada = respuesta;
        PrimeFaces.current().ajax().update("idcontenidoRespuesta");
        PrimeFaces.current().executeScript("PF('dlgRespuesta').show()");
    }

    public String getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

//    public String nombreUnionTemp(Boolean tieneUnion, Integer id) {
//        String valor = "";
//        if (tieneUnion) {
//            valor = getMipresServicio().consultarCntUnionTemp(id);
//        }
//        return " - " + valor;
//    }
}
