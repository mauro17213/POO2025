/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import static com.itextpdf.commons.utils.DateTimeUtil.getCalendar;
import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoAdjunto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Tutela;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Automatico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuRango;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeSugeridoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuDiagnostico;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuSolicitudServicioIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuReporte;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Stiven Giraldo
 */
@Named
@ViewScoped
public class AuSolicitudBean extends Url {

    @Autowired
    private AuSolicitudServicioIface auSolicitudServicio;
    private AuAnexo3 objeto;
    private AuAnexo2Rescate objetoRescate;
    private MaDiagnostico diagnostico;
    private List<AuAnexo3> registros;
    private LazyDataModel<AuAnexo3> lazyRegistro;

    private SelDiagnosticosBean diagnosticosBean;
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;
    private SelServiciosHabilitacionBean servicioHabilitacionBean;
    private SelEspecialidadesBean selEspecialidadesBean;

    //Variables para consulta
    private ParamConsulta paramConsulta4;
    private List<AsegAfiliado> listaAfiliados;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
    private List<CntPrestadorSede> listaIps;
    private LazyDataModel<CntPrestadorSede> lazyIps;
    private List<CntContratoDetalle> listaContratosDetalles;
    private LazyDataModel<CntContratoDetalle> lazyContratoDetalles;
    private List<AuCotizacion> listaCotizaciones;
    private LazyDataModel<AuCotizacion> lazyCotizaciones;

    //Listas auxiliares
    private List<Maestro> listaTipoCargue;
    private HashMap<Integer, Maestro> hashTipoCargue;
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaTipoAmbito;
    private HashMap<Integer, Maestro> hashTipoAmbito;
    private List<Maestro> listaTipoServicioAtencion;
    private HashMap<Integer, Maestro> hashTipoServicioAtencion;
    private HashMap<Integer, Usuario> hashUsuarios;
    private List<Maestro> listaEstadoSolicitud;
    private List<Maestro> listaRegimenAfiliado;
    private HashMap<Integer, Maestro> hashRegimenAfiliado;
    private List<Maestro> listaGeneroAfiliado;
    private HashMap<Integer, Maestro> hashGeneroAfiliado;
    private List<PePrograma> listaProgramaEspecial;
    private List<PeAfiliadosPrograma> listaAfiliadoPrograma;
    private HashMap<Integer, PePrograma> hashProgramaEspecial;
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaOrigenAtencion;
    private HashMap<Integer, Maestro> hashOrigenAtencion;
    private List<TuTutelaRespuesta> listaTutelas;
    private TuTutelaRespuesta respuestaTutela;
    private List<Maestro> listaTipoUbicacionPaciente;
    private HashMap<Integer, Maestro> hashTipoUbicacionPaciente;
    private List<Maestro> listaTipoDiagnostico;
    private HashMap<Integer, Maestro> hashTipoDiagnostico;
    private List<Maestro> listaTipoDocumentoArchivo;
    private HashMap<Integer, Maestro> hashTipoDocumentoArchivo;
    private List<Maestro> listaEstadosAfiliado;
    private HashMap<Integer, Maestro> hashEstadosAfiliado;
    private List<Maestro> listaGrupoPoblacional;
    private HashMap<Integer, Maestro> hashGrupoPoblacional;
    private List<Maestro> listaNiveles;
    private List<Maestro> listaCausaExterna;
    private HashMap<Integer, Maestro> hashCausaExterna;
    private List<Maestro> listaTipoFinalidad;
    private HashMap<Integer, Maestro> hashTipoFinalidad;
    private List<Maestro> listaTipoCatastrofico;
    private HashMap<Integer, Maestro> hashTipoCatastrofico;
    private List<Maestro> listaTipoViaAdministracion;
    private HashMap<Integer, Maestro> hashTipoViaAdministracion;
    private List<String> listaFrecuencia;
    private List<Maestro> listaTipoAcciones;
    private HashMap<Integer, Maestro> hashTipoAcciones;
    private List<Maestro> listaTipoMotivosRechazo;
    private HashMap<Integer, Maestro> hashTipoMotivosRechazo;
    private List<Maestro> listaTipoDocumentoProfesional;
    private HashMap<Integer, Maestro> hashTipoDocumentoProfesional;
    private List<Maestro> listaRechazos;
    private HashMap<Integer, Maestro> hashRechazos;
    private HashMap<Integer, Maestro> hashAnulado;
    private List<Maestro> listaTipoAuditor;
    private HashMap<Integer, Maestro> hashTipoAuditor;
    private List<MaTecnologiaServicioHabilitacion> listaServicioTecnologia;
    private List<Maestro> listaAnulaciones;
    private HashMap<Integer, Maestro> hashAnulaciones;
    private HashMap<String, Maestro> hashEstadosSeguimiento;
    private HashMap<String, Maestro> hashEstadoGestion;
    private HashMap<String, Maestro> hashMotivoGestion;
    private List<Ubicacion> listaCiudades;
    private List<Ubicacion> listaDepartamentos;
    private List<AuAnexo3Item> listaItemsBorrar;
    private List<AuAnexo3Tutela> listTutelasBorrar;
    private List<AuAnexo3Diagnostico> listaDiagnosticosBorrar;
    private List<Maestro> listaFinalidadTecnologia;
    private HashMap<Integer, Maestro> hashFinalidadTecnologia;
    private List<Maestro> listaModalidadTecnologia;
    private HashMap<Integer, Maestro> hashModalidadTecnologia;
    private List<Maestro> listaTiposSugeridoAdjuntos;
    private HashMap<Integer, Maestro> HashTiposSugeridoAdjuntos;
    private List<PePrograma> listaProgramaEspeciales;
    private HashMap<String, PePrograma> HashProgramaEspeciales;
    //Variables auxiliares
    private Date fechaMaximaCalendario;
    private Date fechaAutorizacion;
    private Maestro idEstadoAfiliado;
    private Date fechaActual = new Date();
    private Date fechaInicio;
    private Date fechaFin;
    private String observacionGenerica;
    private PeAfiliadoSugerido objetoSugerido;
    private List<PeAfiliadoSugerido> listaSegueridoMemoria;
    private HashMap<Integer, PePrograma> hashPePrograma;

    private int maximaCantidadAnexos;
    private int tamanoLimiteAnexo;

    private int idTipoDiagnostico;
    private int idServicioTecnogia;
    private int idDiagnosticoTecnologia;
    private int idProgramaEspecial;
    private int idMotivoAnulacionSolicitud;
    private AuAnexo4 objetoAnexo4;
    private List<AuAnexo4Item> listaHistoricoAnexo4;

    private UploadedFile archivoAdjunto;

    private List<AuAnexo3Item> selectedTecnologia;

    //Variables para mensaje
    private String tituloMensaje;
    private String subtituloMensaje;
    private String mensaje;
    private String mensajeAnticipoAfiliado;
    private String mensajeAnticipo;
    private StringBuilder mensajeNumeroAnticipoAfiliado;
    private StringBuilder mensajeNumeroAnticipo;

    private boolean automatica;
    private List<String> listaErroresAutomaticas;
    private List<AuAnexo4Automatico> listaAutomaticos;
    private boolean capitaPGPAprobarTodo;

    private String comentarioDevolucion;
    private List<AuGrupo> listaGrupos;
    private int idGrupo;
    private List<AuAnexo3Item> listaItemsDerivar;
    private List<AuSolicitudAdjunto> listaAdjuntosCotizacion;
    private List<AntAnticipoAdjunto> antAnticipoAdjuntosList;
    private List<AuGrupo> listaGruposActivos;
    private String mensajeTecnologia;
    private String contratoAnticipadoObservacion;

    private String telefonoFijoAfiliado;
    private String telefonoMovilAfiliado;

    private CntContratoDetalle objetoContratoDetalle;

    private List<Integer> usuarioGruposAsigandos;
    private String usuarioFiltro;

    public static final int MAXIMO_CANTIDAD_ARCHIVOS = 1;
    public static final int MAXIMO_TAMANO_ARCHIVOS = 15000000;

    public static final char ACCION_CREAR_SUGERIDO_ADJUNTO = 'a';
    public static final char ACCION_GUARDAR_SUGERIDO_ADJUNTO = 'b';

    @PreDestroy
    public void preDestroy() {
        this.hashUbicaciones = null;
        this.objeto = null;
    }

    public AuSolicitudBean() {
        this.objeto = new AuAnexo3();
        Modulo mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_SOLICITUDES);
//        this.usuarioFiltro = super.getConexion().getUsuario().getUsuario();
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta(0).setEmpresaId(null);
        } else {
            super.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        super.getParamConsulta(0).setParametroConsulta9(super.getConexion().getUsuario().getId());
        construirListas();
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            //SOLICITUDES
            lazyRegistro = new LazyDataModel<AuAnexo3>() {
                private List<AuAnexo3> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo3> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuAnexo3 solicitud) {
                    return solicitud.getId().toString();
                }

                @Override
                public AuAnexo3 getRowData(String solicitudId) {
                    Integer id = Integer.valueOf(solicitudId);
                    for (AuAnexo3 solicitud : lista) {
                        if (id.equals(solicitud.getId())) {
                            return solicitud;
                        }
                    }
                    return null;
                }

            };
            //AFILIADOS
            lazyAfiliados = new LazyDataModel<AsegAfiliado>() {
                private List<AsegAfiliado> afiliados;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(2).setPrimerRegistro(primerRegistro);
                    getParamConsulta(2).setRegistrosPagina(registrosPagina);
                    getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarAfiliado();
                    afiliados = getListaAfiliados();
                    setRowCount(getParamConsulta(2).getCantidadRegistros());
                    return afiliados;
                }

                @Override
                public String getRowKey(AsegAfiliado afiliado) {
                    return afiliado.getId().toString();
                }

                @Override
                public AsegAfiliado getRowData(String afiliadoId) {
                    Integer id = Integer.valueOf(afiliadoId);
                    for (AsegAfiliado afiliado : afiliados) {
                        if (id.equals(afiliado.getId())) {
                            return afiliado;
                        }
                    }
                    return null;
                }
            };
            //IPSs
            lazyIps = new LazyDataModel<CntPrestadorSede>() {
                private List<CntPrestadorSede> listaIps;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(3).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(3).setPrimerRegistro(primerRegistro);
                    getParamConsulta(3).setRegistrosPagina(registrosPagina);
                    getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarIps();
                    listaIps = getListaIps();
                    setRowCount(getParamConsulta(3).getCantidadRegistros());
                    return listaIps;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntPrestadorSede ips : listaIps) {
                        if (id.equals(ips.getId())) {
                            return ips;
                        }
                    }
                    return null;
                }
            };
            //Detalles
            lazyContratoDetalles = new LazyDataModel<CntContratoDetalle>() {
                private List<CntContratoDetalle> listaContrato;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta4().setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta4().setPrimerRegistro(primerRegistro);
                    getParamConsulta4().setRegistrosPagina(registrosPagina);
                    getParamConsulta4().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta4().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarContratoDetalle();
                    listaContrato = getListaContratosDetalles();
                    setRowCount(getParamConsulta4().getCantidadRegistros());
                    return listaContrato;
                }

                @Override
                public String getRowKey(CntContratoDetalle ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntContratoDetalle getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntContratoDetalle contrato : listaContrato) {
                        if (id.equals(contrato.getId())) {
                            return contrato;
                        }
                    }
                    return null;
                }
            };
            //Cotizaciones
            lazyCotizaciones = new LazyDataModel<AuCotizacion>() {
                private List<AuCotizacion> listaCotizacion;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuCotizacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(5).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(5).setPrimerRegistro(primerRegistro);
                    getParamConsulta(5).setRegistrosPagina(registrosPagina);
                    getParamConsulta(5).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(5).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarCotizacion();
                    listaCotizacion = getListaCotizaciones();
                    setRowCount(getParamConsulta(5).getCantidadRegistros());
                    return listaCotizacion;
                }

                @Override
                public String getRowKey(AuCotizacion cotizacion) {
                    return cotizacion.getId().toString();
                }

                @Override
                public AuCotizacion getRowData(String cotizacionId) {
                    Integer id = Integer.valueOf(cotizacionId);
                    for (AuCotizacion cotizacion : listaCotizacion) {
                        if (id.equals(cotizacion.getId())) {
                            return cotizacion;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAuSolicitudServicio().cargaInicial(this);
        listar();
    }

    public AuAnexo2Rescate getObjetoRescate() {
        return objetoRescate;
    }

    public void setObjetoRescate(AuAnexo2Rescate objetoRescate) {
        this.objetoRescate = objetoRescate;
    }

    public List<AuSolicitudAdjunto> getListaAdjuntosCotizacion() {
        return listaAdjuntosCotizacion;
    }

    public void setListaAdjuntosCotizacion(List<AuSolicitudAdjunto> listaAdjuntosCotizacion) {
        this.listaAdjuntosCotizacion = listaAdjuntosCotizacion;
    }

    public List<AntAnticipoAdjunto> getAntAnticipoAdjuntosList() {
        return antAnticipoAdjuntosList;
    }

    public void setAntAnticipoAdjuntosList(List<AntAnticipoAdjunto> antAnticipoAdjuntosList) {
        this.antAnticipoAdjuntosList = antAnticipoAdjuntosList;
    }

    public AuSolicitudServicioIface getAuSolicitudServicio() {
        return auSolicitudServicio;
    }

    public void setAuSolicitudServicio(AuSolicitudServicioIface auSolicitudServicio) {
        this.auSolicitudServicio = auSolicitudServicio;
    }

    public AuAnexo3 getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo3 objeto) {
        this.objeto = objeto;
    }

    public MaDiagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(MaDiagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<AuAnexo3> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo3> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo3> getLazyRegistro() {
        return lazyRegistro;
    }

    public void setLazyRegistro(LazyDataModel<AuAnexo3> lazyRegistro) {
        this.lazyRegistro = lazyRegistro;
    }

    public List<Maestro> getListaTipoCargue() {
        return listaTipoCargue;
    }

    public void setListaTipoCargue(List<Maestro> listaTipoCargue) {
        this.listaTipoCargue = listaTipoCargue;
    }

    public HashMap<Integer, Maestro> getHashTipoCargue() {
        return hashTipoCargue;
    }

    public void setHashTipoCargue(HashMap<Integer, Maestro> hashTipoCargue) {
        this.hashTipoCargue = hashTipoCargue;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    public List<Maestro> getListaTipoAmbito() {
        return listaTipoAmbito;
    }

    public void setListaTipoAmbito(List<Maestro> listaTipoAmbito) {
        this.listaTipoAmbito = listaTipoAmbito;
    }

    public HashMap<Integer, Maestro> getHashTipoAmbito() {
        return hashTipoAmbito;
    }

    public void setHashTipoAmbito(HashMap<Integer, Maestro> hashTipoAmbito) {
        this.hashTipoAmbito = hashTipoAmbito;
    }

    public List<Maestro> getListaTipoServicioAtencion() {
        return listaTipoServicioAtencion;
    }

    public void setListaTipoServicioAtencion(List<Maestro> listaTipoServicioAtencion) {
        this.listaTipoServicioAtencion = listaTipoServicioAtencion;
    }

    public HashMap<Integer, Maestro> getHashTipoServicioAtencion() {
        return hashTipoServicioAtencion;
    }

    public void setHashTipoServicioAtencion(HashMap<Integer, Maestro> hashTipoServicioAtencion) {
        this.hashTipoServicioAtencion = hashTipoServicioAtencion;
    }

    public HashMap<Integer, Usuario> getHashUsuarios() {
        return hashUsuarios;
    }

    public void setHashUsuarios(HashMap<Integer, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    public List<Maestro> getListaEstadoSolicitud() {
        return listaEstadoSolicitud;
    }

    public void setListaEstadoSolicitud(List<Maestro> listaEstadoSolicitud) {
        this.listaEstadoSolicitud = listaEstadoSolicitud;
    }

    public List<Maestro> getListaRegimenAfiliado() {
        return listaRegimenAfiliado;
    }

    public void setListaRegimenAfiliado(List<Maestro> listaRegimenAfiliado) {
        this.listaRegimenAfiliado = listaRegimenAfiliado;
    }

    public HashMap<Integer, Maestro> getHashRegimenAfiliado() {
        return hashRegimenAfiliado;
    }

    public void setHashRegimenAfiliado(HashMap<Integer, Maestro> hashRegimenAfiliado) {
        this.hashRegimenAfiliado = hashRegimenAfiliado;
    }

    public List<Maestro> getListaGeneroAfiliado() {
        return listaGeneroAfiliado;
    }

    public void setListaGeneroAfiliado(List<Maestro> listaGeneroAfiliado) {
        this.listaGeneroAfiliado = listaGeneroAfiliado;
    }

    public HashMap<Integer, Maestro> getHashGeneroAfiliado() {
        return hashGeneroAfiliado;
    }

    public void setHashGeneroAfiliado(HashMap<Integer, Maestro> hashGeneroAfiliado) {
        this.hashGeneroAfiliado = hashGeneroAfiliado;
    }

    public Date getFechaMaximaCalendario() {
        return fechaMaximaCalendario;
    }

    public void setFechaMaximaCalendario(Date fechaMaximaCalendario) {
        this.fechaMaximaCalendario = fechaMaximaCalendario;
    }

    public List<AsegAfiliado> getListaAfiliados() {
        return listaAfiliados;
    }

    public void setListaAfiliados(List<AsegAfiliado> listaAfiliados) {
        this.listaAfiliados = listaAfiliados;
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliados() {
        return lazyAfiliados;
    }

    public ParamConsulta getParamConsulta4() {
        return paramConsulta4;
    }

    public void setParamConsulta4(ParamConsulta paramConsulta4) {
        this.paramConsulta4 = paramConsulta4;
    }

    public List<PeAfiliadosPrograma> getListaAfiliadoPrograma() {
        return listaAfiliadoPrograma;
    }

    public void setListaAfiliadoPrograma(List<PeAfiliadosPrograma> listaAfiliadoPrograma) {
        this.listaAfiliadoPrograma = listaAfiliadoPrograma;
    }

    public List<PePrograma> getListaProgramaEspecial() {
        return listaProgramaEspecial;
    }

    public void setListaProgramaEspecial(List<PePrograma> listaProgramaEspecial) {
        this.listaProgramaEspecial = listaProgramaEspecial;
    }

    public HashMap<Integer, PePrograma> getHashProgramaEspecial() {
        return hashProgramaEspecial;
    }

    public void setHashProgramaEspecial(HashMap<Integer, PePrograma> hashProgramaEspecial) {
        this.hashProgramaEspecial = hashProgramaEspecial;
    }

    public List<CntPrestadorSede> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<CntPrestadorSede> listaIps) {
        this.listaIps = listaIps;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIps() {
        return lazyIps;
    }

    public void setLazyIps(LazyDataModel<CntPrestadorSede> lazyIps) {
        this.lazyIps = lazyIps;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashMunicipios) {
        this.hashUbicaciones = hashMunicipios;
    }

    public List<Maestro> getListaOrigenAtencion() {
        return listaOrigenAtencion;
    }

    public void setListaOrigenAtencion(List<Maestro> listaOrigenAtencion) {
        this.listaOrigenAtencion = listaOrigenAtencion;
    }

    public HashMap<Integer, Maestro> getHashOrigenAtencion() {
        return hashOrigenAtencion;
    }

    public void setHashOrigenAtencion(HashMap<Integer, Maestro> hashOrigenAtencion) {
        this.hashOrigenAtencion = hashOrigenAtencion;
    }

    public List<Maestro> getListaTipoUbicacionPaciente() {
        return listaTipoUbicacionPaciente;
    }

    public void setListaTipoUbicacionPaciente(List<Maestro> listaTipoUbicacionPaciente) {
        this.listaTipoUbicacionPaciente = listaTipoUbicacionPaciente;
    }

    public HashMap<Integer, Maestro> getHashTipoUbicacionPaciente() {
        return hashTipoUbicacionPaciente;
    }

    public void setHashTipoUbicacionPaciente(HashMap<Integer, Maestro> hashTipoUbicacionPaciente) {
        this.hashTipoUbicacionPaciente = hashTipoUbicacionPaciente;
    }

    public String getContratoAnticipadoObservacion() {
        return contratoAnticipadoObservacion;
    }

    public void setContratoAnticipadoObservacion(String contratoAnticipadoObservacion) {
        this.contratoAnticipadoObservacion = contratoAnticipadoObservacion;
    }

    public CntContratoDetalle getObjetoContratoDetalle() {
        return objetoContratoDetalle;
    }

    public void setObjetoContratoDetalle(CntContratoDetalle objetoContratoDetalle) {
        this.objetoContratoDetalle = objetoContratoDetalle;
    }

    public List<Maestro> getListaTipoDiagnostico() {
        return listaTipoDiagnostico;
    }

    public void setListaTipoDiagnostico(List<Maestro> listaTipoDiagnostico) {
        this.listaTipoDiagnostico = listaTipoDiagnostico;
    }

    public HashMap<Integer, Maestro> getHashTipoDiagnostico() {
        return hashTipoDiagnostico;
    }

    public void setHashTipoDiagnostico(HashMap<Integer, Maestro> hashTipoDiagnostico) {
        this.hashTipoDiagnostico = hashTipoDiagnostico;
    }

    public int getMaximaCantidadAnexos() {
        return maximaCantidadAnexos;
    }

    public void setMaximaCantidadAnexos(int maximaCantidadAnexos) {
        this.maximaCantidadAnexos = maximaCantidadAnexos;
    }

    public int getTamanoLimiteAnexo() {
        return tamanoLimiteAnexo;
    }

    public void setTamanoLimiteAnexo(int tamanoLimiteAnexo) {
        this.tamanoLimiteAnexo = tamanoLimiteAnexo;
    }

    public List<Maestro> getListaTipoDocumentoArchivo() {
        return listaTipoDocumentoArchivo;
    }

    public void setListaTipoDocumentoArchivo(List<Maestro> listaTipoDocumentoArchivo) {
        this.listaTipoDocumentoArchivo = listaTipoDocumentoArchivo;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoArchivo() {
        return hashTipoDocumentoArchivo;
    }

    public void setHashTipoDocumentoArchivo(HashMap<Integer, Maestro> hashTipoDocumentoArchivo) {
        this.hashTipoDocumentoArchivo = hashTipoDocumentoArchivo;
    }

    public List<Maestro> getListaTipoFinalidad() {
        return listaTipoFinalidad;
    }

    public void setListaTipoFinalidad(List<Maestro> listaTipoFinalidad) {
        this.listaTipoFinalidad = listaTipoFinalidad;
    }

    public HashMap<Integer, Maestro> getHashTipoFinalidad() {
        return hashTipoFinalidad;
    }

    public void setHashTipoFinalidad(HashMap<Integer, Maestro> hashTipoFinalidad) {
        this.hashTipoFinalidad = hashTipoFinalidad;
    }

    public List<Maestro> getListaTipoCatastrofico() {
        return listaTipoCatastrofico;
    }

    public void setListaTipoCatastrofico(List<Maestro> listaTipoCatastrofico) {
        this.listaTipoCatastrofico = listaTipoCatastrofico;
    }

    public HashMap<Integer, Maestro> getHashTipoCatastrofico() {
        return hashTipoCatastrofico;
    }

    public void setHashTipoCatastrofico(HashMap<Integer, Maestro> hashTipoCatastrofico) {
        this.hashTipoCatastrofico = hashTipoCatastrofico;
    }

    public List<Maestro> getListaTipoViaAdministracion() {
        return listaTipoViaAdministracion;
    }

    public void setListaTipoViaAdministracion(List<Maestro> listaTipoViaAdministracion) {
        this.listaTipoViaAdministracion = listaTipoViaAdministracion;
    }

    public HashMap<Integer, Maestro> getHashTipoViaAdministracion() {
        return hashTipoViaAdministracion;
    }

    public void setHashTipoViaAdministracion(HashMap<Integer, Maestro> hashTipoViaAdministracion) {
        this.hashTipoViaAdministracion = hashTipoViaAdministracion;
    }

    public List<String> getListaFrecuencia() {
        return listaFrecuencia;
    }

    public void setListaFrecuencia(List<String> listaFrecuencia) {
        this.listaFrecuencia = listaFrecuencia;
    }

    public List<Maestro> getListaTipoAcciones() {
        return listaTipoAcciones;
    }

    public void setListaTipoAcciones(List<Maestro> listaTipoAcciones) {
        this.listaTipoAcciones = listaTipoAcciones;
    }

    public HashMap<Integer, Maestro> getHashTipoAcciones() {
        return hashTipoAcciones;
    }

    public void setHashTipoAcciones(HashMap<Integer, Maestro> hashTipoAcciones) {
        this.hashTipoAcciones = hashTipoAcciones;
    }

    public List<Maestro> getListaTipoMotivosRechazo() {
        return listaTipoMotivosRechazo;
    }

    public void setListaTipoMotivosRechazo(List<Maestro> listaTipoMotivosRechazo) {
        this.listaTipoMotivosRechazo = listaTipoMotivosRechazo;
    }

    public HashMap<Integer, Maestro> getHashTipoMotivosRechazo() {
        return hashTipoMotivosRechazo;
    }

    public void setHashTipoMotivosRechazo(HashMap<Integer, Maestro> hashTipoMotivosRechazo) {
        this.hashTipoMotivosRechazo = hashTipoMotivosRechazo;
    }

    public SelDiagnosticosBean getDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    public void setDiagnosticosBean(SelDiagnosticosBean diagnosticosBean) {
        this.diagnosticosBean = diagnosticosBean;
    }

    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public SelMedicamentoBean getMedicamentosBean() {
        medicamentosBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return medicamentosBean;
    }

    public void setMedicamentosBean(SelMedicamentoBean medicamentosBean) {
        this.medicamentosBean = medicamentosBean;
    }

    public SelInsumosBean getInsumosBean() {
        insumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return insumosBean;
    }

    public void setInsumosBean(SelInsumosBean insumosBean) {
        this.insumosBean = insumosBean;
    }

    public SelPaquetesBean getPaquetesBean() {
        paquetesBean = (SelPaquetesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPaquetesBean");
        return paquetesBean;
    }

    public void setPaquetesBean(SelPaquetesBean paquetesBean) {
        this.paquetesBean = paquetesBean;
    }

    public SelServiciosHabilitacionBean getServicioHabilitacionBean() {
        servicioHabilitacionBean = (SelServiciosHabilitacionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selServiciosHabilitacionBean");
        return servicioHabilitacionBean;
    }

    public void setServicioHabilitacionBean(SelServiciosHabilitacionBean servicioHabilitacionBean) {
        this.servicioHabilitacionBean = servicioHabilitacionBean;
    }

    public SelEspecialidadesBean getSelEspecialidadesBean() {
        selEspecialidadesBean = (SelEspecialidadesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selEspecialidadesBean");
        return selEspecialidadesBean;
    }

    public void setSelEspecialidadesBean(SelEspecialidadesBean selEspecialidadesBean) {
        this.selEspecialidadesBean = selEspecialidadesBean;
    }

    public int getIdTipoDiagnostico() {
        return idTipoDiagnostico;
    }

    public void setIdTipoDiagnostico(int idTipoDiagnostico) {
        this.idTipoDiagnostico = idTipoDiagnostico;
        agregarAlDianosticoActual();
    }

    public List<TuTutelaRespuesta> getListaTutelas() {
        return listaTutelas;
    }

    public void setListaTutelas(List<TuTutelaRespuesta> listaTutelas) {
        this.listaTutelas = listaTutelas;
    }

    public TuTutelaRespuesta getRespuestaTutela() {
        return respuestaTutela;
    }

    public void setRespuestaTutela(TuTutelaRespuesta respuestaTutela) {
        this.respuestaTutela = respuestaTutela;
    }

    public List<Maestro> getListaEstadosAfiliado() {
        return listaEstadosAfiliado;
    }

    public void setListaEstadosAfiliado(List<Maestro> listaEstadosAfiliado) {
        this.listaEstadosAfiliado = listaEstadosAfiliado;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliado() {
        return hashEstadosAfiliado;
    }

    public void setHashEstadosAfiliado(HashMap<Integer, Maestro> hashEstadosAfiliado) {
        this.hashEstadosAfiliado = hashEstadosAfiliado;
    }

    public List<Maestro> getListaGrupoPoblacional() {
        return listaGrupoPoblacional;
    }

    public void setListaGrupoPoblacional(List<Maestro> listaGrupoPoblacional) {
        this.listaGrupoPoblacional = listaGrupoPoblacional;
    }

    public HashMap<Integer, Maestro> getHashGrupoPoblacional() {
        return hashGrupoPoblacional;
    }

    public void setHashGrupoPoblacional(HashMap<Integer, Maestro> hashGrupoPoblacional) {
        this.hashGrupoPoblacional = hashGrupoPoblacional;
    }

    public HashMap<String, Maestro> getHashEstadoGestion() {
        return hashEstadoGestion;
    }

    public void setHashEstadoGestion(HashMap<String, Maestro> hashEstadoGestion) {
        this.hashEstadoGestion = hashEstadoGestion;
    }

    public HashMap<String, Maestro> getHashMotivoGestion() {
        return hashMotivoGestion;
    }

    public void setHashMotivoGestion(HashMap<String, Maestro> hashMotivoGestion) {
        this.hashMotivoGestion = hashMotivoGestion;
    }

    public int getIdServicioTecnogia() {
        if (this.idServicioTecnogia == 0) {
            if (getObjeto().getMaeTipoServicioId() != null) {
                this.idServicioTecnogia = getObjeto().getMaeTipoServicioId();
            }
        }
        return idServicioTecnogia;
    }

    public void setIdServicioTecnogia(int idServicioTecnogia) {
        this.idServicioTecnogia = idServicioTecnogia;
        MaTecnologiaServicioHabilitacion servicio = obtenerMaServicioTecnolia(idServicioTecnogia);
        if (servicio != null) {
            getObjeto().getObjetoTecnologia().setMaServicioSolicitadoId(servicio.getMaServicioHabilitacion().getId());
            getObjeto().getObjetoTecnologia().setMaServicioSolicitadoCodigo("" + servicio.getMaServicioHabilitacion().getCodigo());
            getObjeto().getObjetoTecnologia().setMaServicioSolicitadoValor(servicio.getMaServicioHabilitacion().getNombre());
        }
        this.idServicioTecnogia = 0;
    }

    public int getIdMotivoAnulacionSolicitud() {
        return idMotivoAnulacionSolicitud;
    }

    public void setIdMotivoAnulacionSolicitud(int idMotivoAnulacionSolicitud) {
        this.idMotivoAnulacionSolicitud = idMotivoAnulacionSolicitud;
        Maestro anulacion = obtenerMaestroCausaAnulacion(idMotivoAnulacionSolicitud);
        if (anulacion != null) {
            getObjeto().setMaeEstadoMotivoId(anulacion.getId());
            getObjeto().setMaeEstadoMotivoCodigo(anulacion.getValor());
            getObjeto().setMaeEstadoMotivoValor(anulacion.getNombre());
        }
        this.idMotivoAnulacionSolicitud = 0;
    }

    public int getIdProgramaEspecial() {
        return idProgramaEspecial;
    }

    public List<Maestro> getListaNiveles() {
        return listaNiveles;
    }

    public void setListaNiveles(List<Maestro> listaNiveles) {
        this.listaNiveles = listaNiveles;
    }

    public List<Maestro> getListaCausaExterna() {
        return listaCausaExterna;
    }

    public void setListaCausaExterna(List<Maestro> listaCausaExterna) {
        this.listaCausaExterna = listaCausaExterna;
    }

    public HashMap<Integer, Maestro> getHashCausaExterna() {
        return hashCausaExterna;
    }

    public void setHashCausaExterna(HashMap<Integer, Maestro> hashCausaExterna) {
        this.hashCausaExterna = hashCausaExterna;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public List<Maestro> getListaTipoDocumentoProfesional() {
        return listaTipoDocumentoProfesional;
    }

    public void setListaTipoDocumentoProfesional(List<Maestro> listaTipoDocumentoProfesional) {
        this.listaTipoDocumentoProfesional = listaTipoDocumentoProfesional;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoProfesional() {
        return hashTipoDocumentoProfesional;
    }

    public void setHashTipoDocumentoProfesional(HashMap<Integer, Maestro> hashTipoDocumentoProfesional) {
        this.hashTipoDocumentoProfesional = hashTipoDocumentoProfesional;
    }

    public List<CntContratoDetalle> getListaContratosDetalles() {
        return listaContratosDetalles;
    }

    public void setListaContratosDetalles(List<CntContratoDetalle> listaContratosDetalles) {
        this.listaContratosDetalles = listaContratosDetalles;
    }

    public LazyDataModel<CntContratoDetalle> getLazyContratoDetalles() {
        return lazyContratoDetalles;
    }

    public void setLazyContratoDetalles(LazyDataModel<CntContratoDetalle> lazyContratoDetalles) {
        this.lazyContratoDetalles = lazyContratoDetalles;
    }

    public List<AuCotizacion> getListaCotizaciones() {
        return listaCotizaciones;
    }

    public void setListaCotizaciones(List<AuCotizacion> listaCotizaciones) {
        this.listaCotizaciones = listaCotizaciones;
    }

    public LazyDataModel<AuCotizacion> getLazyCotizaciones() {
        return lazyCotizaciones;
    }

    public void setLazyCotizaciones(LazyDataModel<AuCotizacion> lazyCotizaciones) {
        this.lazyCotizaciones = lazyCotizaciones;
    }

    public List<AuAnexo3Item> getSelectedTecnologia() {
        return selectedTecnologia;
    }

    public void setSelectedTecnologia(List<AuAnexo3Item> selectedTecnologia) {
        this.selectedTecnologia = selectedTecnologia;
    }

    public List<Maestro> getListaRechazos() {
        return listaRechazos;
    }

    public void setListaRechazos(List<Maestro> listaRechazos) {
        this.listaRechazos = listaRechazos;
    }

    public HashMap<Integer, Maestro> getHashRechazos() {
        return hashRechazos;
    }

    public void setHashRechazos(HashMap<Integer, Maestro> hashRechazos) {
        this.hashRechazos = hashRechazos;
    }

    public List<Maestro> getListaTipoAuditor() {
        return listaTipoAuditor;
    }

    public void setListaTipoAuditor(List<Maestro> listaTipoAuditor) {
        this.listaTipoAuditor = listaTipoAuditor;
    }

    public HashMap<Integer, Maestro> getHashTipoAuditor() {
        return hashTipoAuditor;
    }

    public void setHashTipoAuditor(HashMap<Integer, Maestro> hashTipoAuditor) {
        this.hashTipoAuditor = hashTipoAuditor;
    }

    public List<MaTecnologiaServicioHabilitacion> getListaServicioTecnologia() {
        return listaServicioTecnologia;
    }

    public void setListaServicioTecnologia(List<MaTecnologiaServicioHabilitacion> listaServicioTecnologia) {
        this.listaServicioTecnologia = listaServicioTecnologia;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<Ubicacion> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Ubicacion> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<Maestro> getListaAnulaciones() {
        return listaAnulaciones;
    }

    public void setListaAnulaciones(List<Maestro> listaAnulaciones) {
        this.listaAnulaciones = listaAnulaciones;
    }

    public HashMap<Integer, Maestro> getHashAnulaciones() {
        return hashAnulaciones;
    }

    public void setHashAnulaciones(HashMap<Integer, Maestro> hashAnulaciones) {
        this.hashAnulaciones = hashAnulaciones;
    }

    public Maestro getIdEstadoAfiliado() {
        return idEstadoAfiliado;
    }

    public void setIdEstadoAfiliado(Maestro idEstadoAfiliado) {
        this.idEstadoAfiliado = idEstadoAfiliado;
    }

    public AuAnexo4 getObjetoAnexo4() {
        return objetoAnexo4;
    }

    public void setObjetoAnexo4(AuAnexo4 objetoAnexo4) {
        this.objetoAnexo4 = objetoAnexo4;
    }

    public List<AuAnexo4Item> getListaHistoricoAnexo4() {
        return listaHistoricoAnexo4;
    }

    public void setListaHistoricoAnexo4(List<AuAnexo4Item> listaHistoricoAnexo4) {
        this.listaHistoricoAnexo4 = listaHistoricoAnexo4;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getTituloMensaje() {
        return tituloMensaje;
    }

    public void setTituloMensaje(String tituloMensaje) {
        this.tituloMensaje = tituloMensaje;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    public void setAutomatica(boolean automatica) {
        this.automatica = automatica;
    }

    public List<String> getListaErroresAutomaticas() {
        return listaErroresAutomaticas;
    }

    public void setListaErroresAutomaticas(List<String> listaErroresAutomaticas) {
        this.listaErroresAutomaticas = listaErroresAutomaticas;
    }

    public List<AuAnexo4Automatico> getListaAutomaticos() {
        return listaAutomaticos;
    }

    public void setListaAutomaticos(List<AuAnexo4Automatico> listaAutomaticos) {
        this.listaAutomaticos = listaAutomaticos;
    }

    public String getComentarioDevolucion() {
        return comentarioDevolucion;
    }

    public void setComentarioDevolucion(String comentarioDevolucion) {
        this.comentarioDevolucion = comentarioDevolucion;
    }

    public HashMap<String, Maestro> getHashEstadosSeguimiento() {
        return hashEstadosSeguimiento;
    }

    public void setHashEstadosSeguimiento(HashMap<String, Maestro> hashEstadosSeguimiento) {
        this.hashEstadosSeguimiento = hashEstadosSeguimiento;
    }

    public List<AuGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<AuGrupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdDiagnosticoTecnologia() {
        return idDiagnosticoTecnologia;
    }

    public void setIdDiagnosticoTecnologia(int idDiagnosticoTecnologia) {
        this.idDiagnosticoTecnologia = idDiagnosticoTecnologia;
    }

    public List<AuAnexo3Item> getListaItemsDerivar() {
        return listaItemsDerivar;
    }

    public void setListaItemsDerivar(List<AuAnexo3Item> listaItemsDerivar) {
        this.listaItemsDerivar = listaItemsDerivar;
    }

    public List<AuGrupo> getListaGruposActivos() {
        return listaGruposActivos;
    }

    public void setListaGruposActivos(List<AuGrupo> listaGruposActivos) {
        this.listaGruposActivos = listaGruposActivos;
    }

    public String getMensajeTecnologia() {
        return mensajeTecnologia;
    }

    public void setMensajeTecnologia(String mensajeTecnologia) {
        this.mensajeTecnologia = mensajeTecnologia;
    }

    public List<Integer> getUsuarioGruposAsigandos() {
        return usuarioGruposAsigandos;
    }

    public void setUsuarioGruposAsigandos(List<Integer> usuarioGruposAsigandos) {
        this.usuarioGruposAsigandos = usuarioGruposAsigandos;
    }

    public String getUsuarioFiltro() {
        return usuarioFiltro;
    }

    public void setUsuarioFiltro(String usuarioFiltro) {
        this.usuarioFiltro = usuarioFiltro;
    }

    public String getSubtituloMensaje() {
        return subtituloMensaje;
    }

    public void setSubtituloMensaje(String subtituloMensaje) {
        this.subtituloMensaje = subtituloMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeAnticipoAfiliado() {
        return mensajeAnticipoAfiliado;
    }

    public void setMensajeAnticipoAfiliado(String mensajeAnticipoAfiliado) {
        this.mensajeAnticipoAfiliado = mensajeAnticipoAfiliado;
    }

    public String getMensajeAnticipo() {
        return mensajeAnticipo;
    }

    public void setMensajeAnticipo(String mensajeAnticipo) {
        this.mensajeAnticipo = mensajeAnticipo;
    }

    public StringBuilder getMensajeNumeroAnticipoAfiliado() {
        return mensajeNumeroAnticipoAfiliado;
    }

    public void setMensajeNumeroAnticipoAfiliado(StringBuilder mensajeNumeroAnticipoAfiliado) {
        this.mensajeNumeroAnticipoAfiliado = mensajeNumeroAnticipoAfiliado;
    }

    public StringBuilder getMensajeNumeroAnticipo() {
        return mensajeNumeroAnticipo;
    }

    public void setMensajeNumeroAnticipo(StringBuilder mensajeNumeroAnticipo) {
        this.mensajeNumeroAnticipo = mensajeNumeroAnticipo;
    }

    public String getTelefonoFijoAfiliado() {
        return telefonoFijoAfiliado;
    }

    public void setTelefonoFijoAfiliado(String telefonoFijoAfiliado) {
        this.telefonoFijoAfiliado = telefonoFijoAfiliado;
    }

    public String getTelefonoMovilAfiliado() {
        return telefonoMovilAfiliado;
    }

    public void setTelefonoMovilAfiliado(String telefonoMovilAfiliado) {
        this.telefonoMovilAfiliado = telefonoMovilAfiliado;
    }

    public List<AuAnexo3Item> getListaItemsBorrar() {
        return listaItemsBorrar;
    }

    public void setListaItemsBorrar(List<AuAnexo3Item> listaItemsBorrar) {
        this.listaItemsBorrar = listaItemsBorrar;
    }

    public List<AuAnexo3Diagnostico> getListaDiagnosticosBorrar() {
        return listaDiagnosticosBorrar;
    }

    public void setListaDiagnosticosBorrar(List<AuAnexo3Diagnostico> listaDiagnosticosBorrar) {
        this.listaDiagnosticosBorrar = listaDiagnosticosBorrar;
    }

    public List<AuAnexo3Tutela> getListTutelasBorrar() {
        return listTutelasBorrar;
    }

    public void setListTutelasBorrar(List<AuAnexo3Tutela> listTutelasBorrar) {
        this.listTutelasBorrar = listTutelasBorrar;
    }

    public boolean isCapitaPGPAprobarTodo() {
        return capitaPGPAprobarTodo;
    }

    public void setCapitaPGPAprobarTodo(boolean capitaPGPAprobarTodo) {
        this.capitaPGPAprobarTodo = capitaPGPAprobarTodo;
    }

    public String getObservacionGenerica() {
        return observacionGenerica;
    }

    public void setObservacionGenerica(String observacionGenerica) {
        this.observacionGenerica = observacionGenerica;
    }

    public PeAfiliadoSugerido getObjetoSugerido() {
        return objetoSugerido;
    }

    public void setObjetoSugerido(PeAfiliadoSugerido objetoSugerido) {
        this.objetoSugerido = objetoSugerido;
    }

    public List<PeAfiliadoSugerido> getListaSegueridoMemoria() {
        return listaSegueridoMemoria;
    }

    public void setListaSegueridoMemoria(List<PeAfiliadoSugerido> listaSegueridoMemoria) {
        this.listaSegueridoMemoria = listaSegueridoMemoria;
    }

    public HashMap<Integer, PePrograma> getHashPePrograma() {
        return hashPePrograma;
    }

    public void setHashPePrograma(HashMap<Integer, PePrograma> hashPePrograma) {
        this.hashPePrograma = hashPePrograma;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getValidadorPatter() {
        return AuConstantes.PATTERN;
    }

    public List<Maestro> getListaFinalidadTecnologia() {
        return listaFinalidadTecnologia;
    }

    public void setListaFinalidadTecnologia(List<Maestro> listaFinalidadTecnologia) {
        this.listaFinalidadTecnologia = listaFinalidadTecnologia;
    }

    public HashMap<Integer, Maestro> getHashFinalidadTecnologia() {
        return hashFinalidadTecnologia;
    }

    public void setHashFinalidadTecnologia(HashMap<Integer, Maestro> hashFinalidadTecnologia) {
        this.hashFinalidadTecnologia = hashFinalidadTecnologia;
    }

    public List<Maestro> getListaModalidadTecnologia() {
        return listaModalidadTecnologia;
    }

    public void setListaModalidadTecnologia(List<Maestro> listaModalidadTecnologia) {
        this.listaModalidadTecnologia = listaModalidadTecnologia;
    }

    public HashMap<Integer, Maestro> getHashModalidadTecnologia() {
        return hashModalidadTecnologia;
    }

    public void setHashModalidadTecnologia(HashMap<Integer, Maestro> hashModalidadTecnologia) {
        this.hashModalidadTecnologia = hashModalidadTecnologia;
    }

    public List<Maestro> getListaTiposSugeridoAdjuntos() {
        return listaTiposSugeridoAdjuntos;
    }

    public void setListaTiposSugeridoAdjuntos(List<Maestro> listaTiposSugeridoAdjuntos) {
        this.listaTiposSugeridoAdjuntos = listaTiposSugeridoAdjuntos;
    }

    public HashMap<Integer, Maestro> getHashTiposSugeridoAdjuntos() {
        return HashTiposSugeridoAdjuntos;
    }

    public void setHashTiposSugeridoAdjuntos(HashMap<Integer, Maestro> HashTiposSugeridoAdjuntos) {
        this.HashTiposSugeridoAdjuntos = HashTiposSugeridoAdjuntos;
    }

    public List<PePrograma> getListaProgramaEspeciales() {
        return listaProgramaEspeciales;
    }

    public void setListaProgramaEspeciales(List<PePrograma> listaProgramaEspeciales) {
        this.listaProgramaEspeciales = listaProgramaEspeciales;
    }

    public HashMap<String, PePrograma> getHashProgramaEspeciales() {
        return HashProgramaEspeciales;
    }

    public void setHashProgramaEspeciales(HashMap<String, PePrograma> HashProgramaEspeciales) {
        this.HashProgramaEspeciales = HashProgramaEspeciales;
    }

    public void setIdProgramaEspecial(int idProgramaEspecial) {
        this.idProgramaEspecial = idProgramaEspecial;
        PePrograma programa = obtenerPeProgramaEspecial(idProgramaEspecial);
        if (programa != null) {
            getObjeto().setPeProgramaId(programa);
            getObjeto().setPeProgramaEspecialId(programa.getId());
            getObjeto().setPeProgramaEspecialCodigo(programa.getCodigoPrograma());
            getObjeto().setPeProgramaEspecialValor(programa.getDescripcionPrograma());
        }
    }

    public void aceptarTecnologia() {
        List<AuAnexo3Item> listaActual = new ArrayList();
        AuAnexo3Item itemActual;
        if (!getObjeto().getAuAnexo3ItemsList().isEmpty()) {
            for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
                if (item.getMaTecnologiaId() != getObjeto().getObjetoTecnologia().getMaTecnologiaId()) {
                    listaActual.add(item);
                }
            }
        }
        if (getObjeto().getObjetoTecnologia().isPosfechado()) {
            int cantidadTotal = getObjeto().getObjetoTecnologia().getListaRangos().size() - 1;
            for (int i = 0; i < getObjeto().getObjetoTecnologia().getListaRangos().size(); i++) {
                if (i == 0) {
                    if (i == cantidadTotal) {
                        Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                        if (getObjeto().getFechaSolicitud() == null) {
                            addError("La fecha de la orden medica es obligatoria.");
                            break;
                        }
                        int anios = obtenerAnios(getObjeto().getFechaSolicitud(), fechaLiberacionActual);
                        if (anios >= 1) {
                            addError("La fecha de liberación supera un año aparatir de la fecha orden medica. ");
                            break;
                        }
                        break;
                    } else {
                        Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                        if (getObjeto().getFechaSolicitud() == null) {
                            addError("La fecha de la orden medica es obligatoria.");
                            break;
                        }
                        int anios = obtenerAnios(getObjeto().getFechaSolicitud(), fechaLiberacionActual);
                        if (anios >= 1) {
                            addError("La fecha de liberación supera un año aparatir de la fecha orden medica. ");
                            break;
                        }
                        Date fechaLiberacionDespues = getObjeto().getObjetoTecnologia().getListaRangos().get(i + 1).getFechaLiberacion();
                        if (fechaLiberacionActual.after(fechaLiberacionDespues)) {
                            addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionActual) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                            break;
                        }
                    }
                }
                if (i == cantidadTotal) {
                    Date fechaLiberacionAnterior = getObjeto().getObjetoTecnologia().getListaRangos().get(i - 1).getFechaLiberacion();
                    Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                    if (fechaLiberacionAnterior.after(fechaLiberacionActual)) {
                        addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionActual));
                    }
                    break;
                }

                if (i != 0) {
                    Date fechaLiberacionAnterior = getObjeto().getObjetoTecnologia().getListaRangos().get(i - 1).getFechaLiberacion();
                    Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                    Date fechaLiberacionDespues = getObjeto().getObjetoTecnologia().getListaRangos().get(i + 1).getFechaLiberacion();
                    if (fechaLiberacionAnterior.before(fechaLiberacionActual) && fechaLiberacionDespues.after(fechaLiberacionActual)) {
                        continue;
                    } else {
                        addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                        break;
                    }
                }
            }
        }
        if (super.isError()) {
            generarMensajes();
            return;
        }
        String mensajeValidacion = getAuSolicitudServicio().validarCantidadTecnologia("" + getObjeto().getObjetoTecnologia().getTipoTecnologia(), getObjeto().getObjetoTecnologia().getCantidadSolicitada(), getObjeto().getObjetoTecnologia().getMaTecnologiaCodigo());
        if (mensajeValidacion == null) {
            itemActual = getObjeto().getObjetoTecnologia();
            boolean aceptar = true;
            // funcionalidad para devolver anticipo
            AuCotizacion cotizacion = getAuSolicitudServicio().consultarCotizacionAnticipoByAnexo3(this);
            if (cotizacion != null) {
                if (itemActual.getId() != null) {
                    AuAnexo3Item anexo3ItemDB = getAuSolicitudServicio().consultarAnxo3Item(itemActual.getId(), this);
                    if (itemActual.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                            && itemActual.getCantidadSolicitada() != anexo3ItemDB.getCantidadSolicitada()
                            && cotizacion.getAntAnticiposId() != null) {
                        addError("No se puede modificar la cantidad porque tiene un anticipo relacionado");
                        aceptar = false;
                    }
                }
            }

            if (aceptar) {
                if (itemActual.getEstado() == 0) {
                    itemActual.setEstado(-1);//items nuevos no deben tomar por defecto el estado 0(Rechazado), al guardar se asigna el estado correspondiente.                   
                }
                listaActual.add(itemActual);
                getObjeto().setAuAnexo3ItemsList(listaActual);
                PrimeFaces.current().ajax().update("frmCrear:tablaTecnologiasCrear");
                PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiasEditar");
                if (getObjeto().getListaDevoluciones() != null) {
                    listaActual = new ArrayList();
                    for (AuAnexo3Item item : getObjeto().getListaDevoluciones()) {
                        if (item.getMaTecnologiaId() != itemActual.getMaTecnologiaId()) {
                            listaActual.add(item);
                        }
                    }
                    listaActual.add(itemActual);
                    getObjeto().setListaDevoluciones(listaActual);
                }
                PrimeFaces.current().ajax().update("frmGestionarDevoluciones:tablaTecnologiasDevoluciones");
                PrimeFaces.current().executeScript("PF('dialogoGestionarProcedimiento').hide()");
                PrimeFaces.current().executeScript("PF('dialogoGestionarMedicamento').hide()");
                PrimeFaces.current().executeScript("PF('dialogoGestionarInsumo').hide()");
                PrimeFaces.current().executeScript("PF('dialogoGestionarPaquete').hide()");
                //formularios editar
                PrimeFaces.current().executeScript("PF('dialogoEditarProcedimiento').hide()");
                PrimeFaces.current().executeScript("PF('dialogoEditarMedicamento').hide()");
                PrimeFaces.current().executeScript("PF('dialogoEditarInsumo').hide()");
                PrimeFaces.current().executeScript("PF('dialogoEditarPaquete').hide()");
                getObjeto().setObjetoTecnologia(new AuAnexo3Item());
            } else {
                generarMensajes();
            }
        } else {
            addError("El valor de la cantidad no es valido");
            generarMensajes();
        }
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuSolicitudServicio().Accion(this);
    }

    public void refrescarProgrmaEspecial() {
        getAuSolicitudServicio().listarGestionRiesgo(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_VER);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getAuSolicitudServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        procesoFinal();
    }

    public void crearGestionRiesgo() {
        setListaSegueridoMemoria(new ArrayList<>());
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmSugerirProgramaCrear");
            PrimeFaces.current().ajax().update("frmSugerirProgramaCrear");
            PrimeFaces.current().executeScript("PF('dialogoSugerirProgramaCrear').show()");
        }
        generarMensajes();
    }

    public void crearSugerido() {
        setMaximaCantidadAnexos(MAXIMO_CANTIDAD_ARCHIVOS);
        setTamanoLimiteAnexo(MAXIMO_TAMANO_ARCHIVOS);
        setHashPePrograma(new HashMap<>());
        getObjeto().setAucSugerirProgramaList(new ArrayList<>());
        setObjetoSugerido(new PeAfiliadoSugerido());
        getObjetoSugerido().setListaAdjunto(new ArrayList<>());
        getAuSolicitudServicio().consultarGestionRiegosSugerido(this);
        PrimeFaces.current().resetInputs("frmAgregarSugerirPrograma");
        PrimeFaces.current().ajax().update("frmAgregarSugerirPrograma");
        PrimeFaces.current().executeScript("PF('dialogoAgregarSugerirPrograma').show()");
    }

    public void guardarGestionRiesgo() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void abrirAdjuntos(int id) {
        setMaximaCantidadAnexos(MAXIMO_CANTIDAD_ARCHIVOS);
        setTamanoLimiteAnexo(MAXIMO_TAMANO_ARCHIVOS);
        if (id > 0) {
            setObjetoSugerido(new PeAfiliadoSugerido());
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_CREAR_SUGERIDO_ADJUNTO);
        }
        getAuSolicitudServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmAdjuntoSugerido");
        PrimeFaces.current().ajax().update("frmAdjuntoSugerido");
        PrimeFaces.current().executeScript("PF('dialogoAgregarAdjunto').show()");

    }

    public void agregarAdjuntoSugerido() {
        try {

            PeAfiliadoSugerido obj = getObjetoSugerido();
            super.setAccion(ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_GUARDAR_SUGERIDO_ADJUNTO);
            getAuSolicitudServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().ajax().update("frmAdjuntoSugerido");
                PrimeFaces.current().executeScript("PF('dialogoAgregarAdjunto').hide()");
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getAuSolicitudServicio().Accion(this);

        if (getObjeto().getErroresAutomatica() != null && !getObjeto().getErroresAutomatica().isEmpty()) {
            addErrores(getObjeto().getErroresAutomatica());
        }
        if (getObjeto().getMensajeAutomatica() != null && !getObjeto().getMensajeAutomatica().isEmpty()) {
            getObjeto().getMensajeAutomatica().forEach(mensajeA -> {
                addMensaje(mensajeA);
            });
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        boolean modificar = true;
        if (getObjeto().getJustificacionClinica() != null) {
            int espacios = 0;
            for (int i = 0; i < getObjeto().getJustificacionClinica().length(); i++) {
                if (getObjeto().getJustificacionClinica().charAt(i) == ' ') {
                    espacios++;
                }
            }
            if (getObjeto().getJustificacionClinica().length() == espacios) {
                modificar = false;
                addError("La Justificacion no puede tener espacios");
            }
        }
        //funcionalidad para validar que no puede editar la cantidad de la tecnologia si tiene un anticipo relacionado
        for (AuAnexo3Item anexo3Item : getObjeto().getAuAnexo3ItemsList()) {
            if (anexo3Item.getId() != null) {
                AuCotizacion cotizacion = getAuSolicitudServicio().consultarCotizacionAnticipoByAnexo3(anexo3Item.getId(), this);
                AuAnexo3Item anexo3ItemDB = getAuSolicitudServicio().consultarAnxo3Item(anexo3Item.getId(), this);
                if (cotizacion != null) {
                    if (anexo3Item.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO
                            && anexo3ItemDB.getCantidadSolicitada() != anexo3Item.getCantidadSolicitada()
                            && cotizacion.getAntAnticiposId() != null) {
                        addError("No se puede modificar la cantidad porque tiene un anticipo relacionado");
                        modificar = false;
                    }
                }
            }
        }

        if (modificar) {
            super.setAccion(Url.ACCION_MODIFICAR);
            getAuSolicitudServicio().Accion(this);
            procesoFinal();
        } else {
            generarMensajes();
        }

    }

    public void borrar(int id) {
        setObjeto(new AuAnexo3(id));
        super.setAccion(Url.ACCION_BORRAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditar(int id) {
        setObjeto(new AuAnexo3(id));
        setSelectedTecnologia(new ArrayList());
        getObjeto().setObjetoTecnologia(null);
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditarVentanaAprobar(int id) {
        getObjeto().setObjetoTecnologia(null);
        setContratoAnticipadoObservacion("");
        setCapitaPGPAprobarTodo(false);
        setMensajeAnticipoAfiliado("No");
        setMensajeAnticipo("No");
        setMensajeNumeroAnticipoAfiliado(new StringBuilder());
        setMensajeNumeroAnticipo(new StringBuilder());
        for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
            if (item.getId() == id) {
                item.setValorTecnologia(BigDecimal.TEN);
                getObjeto().setObjetoTecnologia(item);
                break;
            }
        }
        getObjeto().setObjetoIps(null);
        getObjeto().setObservacionAuditar("");

        boolean validar = getAuSolicitudServicio().validarTecnologiaPosfechada(getObjeto().getAsegAfiliadoId().getId(), getObjeto().getObjetoTecnologia().getMaTecnologiaId(), this);
        if (validar) {
            PrimeFaces.current().ajax().update("frmConfirmacionTecnologiaAprobar");
            PrimeFaces.current().executeScript("PF('dialogoConfirmacionTecnologiaAprobar').show()");
        } else {
            //validacion grupos asignados por tecnoligia,auditor y tipo auditor
            if (!isAccionAdicional6() && isAccionAdicional8()) {
                getAuSolicitudServicio().validarTecnologiaGrupoAprueba(this);
            }
            if (isError()) {
                generarMensajes();
            } else {
                //consulta contratos capita/pgp
                super.setAccion(Url.ACCION_ADICIONAL_2);
                super.setDoAccion(Url.ACCION_ADICIONAL_1);
                super.setTakeAccion(Url.ACCION_ADICIONAL_1);
                getAuSolicitudServicio().Accion(this);
                //consulta cotizaciones
                super.setAccion(Url.ACCION_ADICIONAL_2);
                super.setDoAccion(Url.ACCION_ADICIONAL_1);
                super.setTakeAccion(Url.ACCION_EDITAR);
                getAuSolicitudServicio().Accion(this);
                //2023-09-27 jyperez obtenemos el valor de la tecnología y lo adjuntamos en el objeto
                //2024-04-10 jyperez se adiciona validación de objetoIps debido a que si no hay cotización este se devuelve nulo.
                if (getObjeto().getObjetoCotizacion() != null && getObjeto().getObjetoIps() != null && getObjeto().getObjetoIps().getNombreSede() != null) {
                    getObjeto().getObjetoTecnologia().setValorTecnologia(getObjeto().getObjetoCotizacion().getValorTecnologia());
                }

                AuAnexo3 anexo3 = getAuSolicitudServicio().consultarAnexo3(getObjeto().getId(), this);
                AntAnticipo anticipoAfiliadoYtecnologia = getAuSolicitudServicio().consultarAnticipoBYafiliado(anexo3.getAsegAfiliadoId().getId(), getObjeto().getObjetoTecnologia().getMaTecnologiaId(), this);
                if (anticipoAfiliadoYtecnologia != null) {
                    setMensajeAnticipoAfiliado("Si");
                }

                AntAnticipo anticipoTecnologia = getAuSolicitudServicio().consultarAnticipoBYTecnologia(getObjeto().getObjetoTecnologia().getMaTecnologiaId(), this);
                if (anticipoTecnologia != null) {
                    setMensajeAnticipo("Si");
                }
                List<AntAnticipo> listaAnticipoYtecnologia = getAuSolicitudServicio().consultarAnticipoBYTecnologiaList(getObjeto().getObjetoTecnologia().getMaTecnologiaId(), this);
                if(!listaAnticipoYtecnologia.isEmpty()){
                    listaAnticipoYtecnologia.forEach(anticipo -> {
                        mensajeNumeroAnticipo.append(anticipo.getId()).append(" | ");
                    });
                    mensajeNumeroAnticipo = new StringBuilder(mensajeNumeroAnticipo.substring(0, mensajeNumeroAnticipo.length() - 3));
                }
                setListaHistoricoAnexo4(new ArrayList());
                getAuSolicitudServicio().verAutorizaciones(this, getObjeto().getObjetoTecnologia().getMaTecnologiaId(), getObjeto().getObjetoTecnologia().getTipoTecnologia());
                procesoFinal();
            }
        }
    }

    public void verContrato(CntContratoDetalle contratoDetalle) {
        setObjetoContratoDetalle(new CntContratoDetalle());
        setObjetoContratoDetalle(contratoDetalle);
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        super.setTakeAccion(Url.ACCION_ADICIONAL_2);
        getAuSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerContrato");
            PrimeFaces.current().executeScript("PF('dialogoContratoVer').show()");
        }
        generarMensajes();
    }

    public String alertaColorMensajeAnticiposIndividual(String mensaje) {
        String color = "";
        if (mensaje != null) {
            if (mensaje.equals("No")) {
                color = "black; font-weight: bold;";
            } else {
                color = "green; font-weight: bold;";
            }
        }
        return color;
    }

    public String alertaColorMensajeAnticiposPaquete(String mensaje) {
        String color = "";
        if (mensaje != null) {
            if (mensaje.equals("No")) {
                color = "black; font-weight: bold;";
            } else {
                color = "green; font-weight: bold;";
            }
        }
        return color;
    }

    public String alertaColorMensajeAnticipos(String mensajeNumeroAnticipo) {
        String color = "";
        if(!mensajeNumeroAnticipo.isBlank()){
            color = "green; font-weight: bold;";
        } else {
            color = "black; font-weight: bold;"; 
        }
        return color;
    }

    public String getEstado(Integer id) {
        //1 - Pendiente | 2 - Marcado | 3 - Rechazado
        return PeConstantes.getEstadoSugerido(id);
    }

    /**
     * Metodo que retona el nombre completo mde la ubicacion a partir de un ID
     *
     * @param id
     * @return
     */
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion municipio = hashUbicaciones.get(id);
        if (getHashUbicaciones() != null) {
            municipio = getHashUbicaciones().get(id);
        }
        if (municipio != null && municipio.getUbicacionPadre() != null) {
            Ubicacion departamento = municipio.getUbicacionPadre();
            ubicacionStr = departamento.getNombre();
            ubicacionStr = municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public void verSeguegrirProgramaMemoria(PeAfiliadoSugerido suegerido) {
        setObjetoSugerido(new PeAfiliadoSugerido());
        setObjetoSugerido(suegerido);
        PrimeFaces.current().resetInputs("frmVerSugerirPrograma");
        PrimeFaces.current().ajax().update("frmVerSugerirPrograma");
        PrimeFaces.current().executeScript("PF('dialogoVerSugerirPrograma').show()");
    }

    public void borrarSugeridoMemoria(PeAfiliadoSugerido sugerido) {
        List<PeAfiliadoSugerido> listaSugeridoMe = new ArrayList<>();
        int posicionEliminar = sugerido.getPosicion();
        getListaSegueridoMemoria().stream().filter(contacto -> (contacto.getPosicion() != posicionEliminar)).forEachOrdered(contacto -> {
            listaSugeridoMe.add(contacto);
        });
        setListaSegueridoMemoria(listaSugeridoMe);
        addMensaje("Se ha realizado la eliminación del sugerido");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmSugerirProgramaCrear:tablaSugerirPrograma");
    }

    public void auditarAprobar() throws ParseException {
        boolean aprobar = true;
        if (getObjeto().getObjetoContratoDetalle() == null && getObjeto().getObjetoCotizacion() == null) {
            aprobar = false;
            addError("No se ha seleccionado el contrato");
        }
        if(getObjeto().getObjetoTecnologia().isPosfechado()){
            List<AuAnexo3Item> listaPosfechados = getAuSolicitudServicio().consultarListaPosfechados(this);
            if(!listaPosfechados.isEmpty()){
                AuAnexo3Item item = listaPosfechados.stream()
                    .filter(pediente -> pediente.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA 
                        || pediente.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION  
                        || pediente.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO)
                    .findFirst().orElse(null);
                if(item != null){
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaString = formato.format(fechaActual);
                    Date miFecha = formato.parse(fechaString); // convierte String a Date
                    if(miFecha.after(item.getFechaPosfechado())){
                        aprobar = false;
                        addError("La fecha del posfechado es menor a la fecha actual, por favor verificar fecha de liberación");
                    }
                }
            }
        }
        if (aprobar) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            super.setTakeAccion(Url.ACCION_MODIFICAR);
            setSelectedTecnologia(new ArrayList());//esta aprobando solo uno
            getAuSolicitudServicio().Accion(this);
            /*if(!super.isError()){
                getObjeto().setObjetoTecnologia(null);
            }*/
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void auditarVentanaAprobarTodo() {
        boolean aprobar = true;
        getObjeto().setObjetoIps(null);
        getObjeto().setObservacionAuditar("");
        setContratoAnticipadoObservacion("");
        setCapitaPGPAprobarTodo(false);
        setMensajeAnticipo("No");
        setMensajeNumeroAnticipoAfiliado(new StringBuilder());
        setMensajeNumeroAnticipo(new StringBuilder());
        if (!getSelectedTecnologia().isEmpty()) {
            int tipoTec = getSelectedTecnologia().get(0).getTipoTecnologia();
            for (AuAnexo3Item item : getSelectedTecnologia()) {
                //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                if (item.getEstado() != AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                        && item.getEstado() != AuAnexo3Item.ESTADO_CON_COTIZACION
                        && item.getEstado() != AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO) {
                    addError("No se puede realizar la aprobación");
                    aprobar = false;
                    break;
                }
                if (item.getTipoTecnologia() != tipoTec) {
                    addError("No son del mismo tipo las tecnología");
                    aprobar = false;
                    break;
                }
                //validacion grupos asignados por tecnoligia,auditor y tipo auditor
                if (!isAccionAdicional6() && isAccionAdicional8()) {
                    getObjeto().setObjetoTecnologia(item);
                    getAuSolicitudServicio().validarTecnologiaGrupoAprueba(this);
                }
            }
        } else {
            addError("No se ha seleccionado ninguna tecnología");
            aprobar = false;
        }
        getObjeto().setObjetoTecnologia(null);
        for (AuAnexo3Item auAnexo3Item : getSelectedTecnologia()) {
            AntAnticipo anticipo = getAuSolicitudServicio().consultarAnticipoBYTecnologia(auAnexo3Item.getMaTecnologiaId(), this);
            if (anticipo != null) {
                setMensajeAnticipo("Si");
                List<AntAnticipo> listaAnticipoYtecnologia = getAuSolicitudServicio().consultarAnticipoBYTecnologiaList(auAnexo3Item.getMaTecnologiaId(), this);
                for (AntAnticipo anticipoTecnologias : listaAnticipoYtecnologia) {
                    mensajeNumeroAnticipo.append(anticipoTecnologias.getId()).append(" | ");
                    mensajeNumeroAnticipoAfiliado.append(auAnexo3Item.getMaTecnologiaCodigo()).append(" | ");
                }
                mensajeNumeroAnticipo.deleteCharAt(mensajeNumeroAnticipo.length() - 1).toString();
                mensajeNumeroAnticipoAfiliado.deleteCharAt(mensajeNumeroAnticipoAfiliado.length() - 1).toString();
                break;
            }
        }
        if (aprobar && !isError()) {

            //consulta contratos capita/pgp
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            super.setTakeAccion(Url.ACCION_ADICIONAL_1);
            getAuSolicitudServicio().Accion(this);
            //consulta cotizaciones
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_2);
            super.setTakeAccion(Url.ACCION_EDITAR);
            getAuSolicitudServicio().Accion(this);
            setListaHistoricoAnexo4(new ArrayList());
            getSelectedTecnologia().forEach(item -> {
                getAuSolicitudServicio().verAutorizaciones(this, item.getMaTecnologiaId(), item.getTipoTecnologia());
            });
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void aceptarTecnologiaAprobar() {
        PrimeFaces.current().executeScript("PF('dialogoConfirmacionTecnologiaAprobar').hide()");
        //consulta contratos capita/pgp
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        super.setTakeAccion(Url.ACCION_ADICIONAL_1);
        getAuSolicitudServicio().Accion(this);
        //consulta cotizaciones
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        super.setTakeAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditarAprobarTodo() {
        boolean aprobar = true;
        if (getObjeto().getObjetoContratoDetalle() == null) {
            aprobar = false;
            addError("No se ha seleccionado el contrato");
        }
        if (aprobar) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_2);
            super.setTakeAccion(Url.ACCION_MODIFICAR);
            getAuSolicitudServicio().Accion(this);
            procesoFinal();
            setSelectedTecnologia(new ArrayList());
            getObjeto().setObjetoTecnologia(null);
        } else {
            generarMensajes();
        }

    }

    public void auditarVentanaRechazar(int id) {
        getObjeto().setComentario(null);
        getObjeto().setIdMotivo(0);
        for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
            if (item.getId() == id) {
                getObjeto().setObjetoTecnologia(item);
                break;
            }
        }
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        super.setTakeAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditarVentanaAnular(int id) {
        setIdMotivoAnulacionSolicitud(0);
        getObjeto().setEstadoJustificacion(null);
        for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
            if (item.getId() == id) {
                getObjeto().setObjetoTecnologia(item);
                break;
            }
        }
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_9);
        super.setTakeAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        List<Maestro> listaAnulacionesAux = new ArrayList<>();
        String valor = "\\d+";
        getListaAnulaciones().stream().filter((Maestro maestro) -> (Pattern.matches(
                "\\d+", maestro.getValor()))).forEachOrdered(maestro -> {
            int value = Integer.parseInt(maestro.getValor());
            if (value > 18) {
                listaAnulacionesAux.add(maestro);
            }
        });
        setListaAnulaciones(listaAnulacionesAux);
        procesoFinal();
    }

    public void auditarAnular() {

        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_9);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
        setSelectedTecnologia(new ArrayList());
        getObjeto().setObjetoTecnologia(null);;
    }

    public void auditarRechazar() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
        setSelectedTecnologia(new ArrayList());
        getObjeto().setObjetoTecnologia(null);
    }

    public void auditarVentanaRechazarTodo() {
        getObjeto().setComentario(null);
        getObjeto().setIdMotivo(0);
        if (!getSelectedTecnologia().isEmpty()) {
            for (AuAnexo3Item item : getSelectedTecnologia()) {
                if (item.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUDITORIA
                        || item.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO
                        || item.getEstado() == AuAnexo3Item.ESTADO_ANULADO
                        || item.getEstado() == AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA) {
                    addError("No se puede realizar la aprobacion");
                    break;
                }
            }
        } else {
            addError("No se ha seleccionado ninguna tecnologia");
        }
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_4);
        super.setTakeAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditarVentanaNegarTodo() {
        getObjeto().setComentario(null);
        getObjeto().setIdMotivo(0);
        if (!getSelectedTecnologia().isEmpty()) {
            for (AuAnexo3Item item : getSelectedTecnologia()) {
                if (item.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUDITORIA
                        || item.getEstado() == AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO
                        || item.getEstado() == AuAnexo3Item.ESTADO_ANULADO
                        || item.getEstado() == AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA) {
                    addError("No se puede realizar la aprobacion");
                    break;
                }
            }
        } else {
            addError("No se ha seleccionado ninguna tecnologia");
        }
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_10);
        super.setTakeAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        List<Maestro> listaAnulacionesAux = new ArrayList<>();
        String valor = "\\d+";
        getListaAnulaciones().stream().filter((Maestro maestro) -> (Pattern.matches(
                "\\d+", maestro.getValor()))).forEachOrdered(maestro -> {
            int value = Integer.parseInt(maestro.getValor());
            if (value > 18) {
                listaAnulacionesAux.add(maestro);
            }
        });
        setListaAnulaciones(listaAnulacionesAux);
        procesoFinal();
    }

    public void auditarNegarTodo() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_10);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAuditarSolicitud");
            
        }
        procesoFinal();
        if(!super.isError()){
            setSelectedTecnologia(new ArrayList());
        }
        getObjeto().setObjetoTecnologia(null);
    }

    public void auditarRechazarTodo() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_4);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAuditarSolicitud");
            setSelectedTecnologia(new ArrayList());
        }
        procesoFinal();
        getObjeto().setObjetoTecnologia(null);
    }

    public void auditarVentanaDevolver(int id) {
        for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
            if (item.getId() == id) {
                getObjeto().setObjetoTecnologia(item);
            }
        }
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_5);
        super.setTakeAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditarVentanaDevolverTodo() {
        //2023-10-31 jyperez validamos si se ha seleccionado una tecnología
        if (!getSelectedTecnologia().isEmpty()) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_6);
            super.setTakeAccion(Url.ACCION_EDITAR);
            getAuSolicitudServicio().Accion(this);
        } else {
            addError("No se ha seleccionado ninguna tecnologia");
        }
        procesoFinal();
    }

    public void auditarDevolver() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_5);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmAuditarSolicitud");
        procesoFinal();
        setSelectedTecnologia(new ArrayList());
        getObjeto().setObjetoTecnologia(null);
    }

    public void auditarDevolverTodo() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_6);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAuditarSolicitud");
            setSelectedTecnologia(new ArrayList());
        }
        procesoFinal();
        getObjeto().setObjetoTecnologia(null);
    }

    public void auditarVentanaDerivar(int id) {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        super.setTakeAccion(Url.ACCION_EDITAR);
        for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
            if (item.getId() == id) {
                getObjeto().setObjetoTecnologia(item);
            }
        }
        setIdGrupo(0);
        setComentarioDevolucion("");
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditarVentanaDerivarTodo() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        super.setTakeAccion(Url.ACCION_EDITAR);
        getObjeto().setObjetoTecnologia(null);
        setIdGrupo(0);
        setComentarioDevolucion("");
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void auditarDerivar() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void anular(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_EDITAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void anularSolicitud() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarAfiliado() {
        getAuSolicitudServicio().listarAfiliado(this);
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        AsegAfiliado afiliado = (AsegAfiliado) event.getObject();
        boolean isActivo = getAuSolicitudServicio().validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion(), this);
        if (isActivo) {
            //reset tutelas cambio afiliado
            setListTutelasBorrar(new ArrayList());
            if (getObjeto().getAsegAfiliadoId() != null
                    && !Objects.equals(getObjeto().getAsegAfiliadoId().getId(), afiliado.getId())) {
                getListTutelasBorrar().addAll(getObjeto().getAuAnexo3TutelasList());
                getObjeto().setAuAnexo3TutelasList(new ArrayList());
            }
            getObjeto().setAsegAfiliadoId(afiliado);
            getAuSolicitudServicio().completarAfiliado(this);
            getParamConsulta(2).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrear:pAfiliadoCrear");
            PrimeFaces.current().ajax().update("frmCrear:pProgramaEspecialCrear");
            PrimeFaces.current().ajax().update("frmCrear:pTutelaCrear");
            PrimeFaces.current().ajax().update("frmEditar:panelAfiliadoEditar");
            PrimeFaces.current().ajax().update("frmEditar:programaEditar");
            PrimeFaces.current().ajax().update("frmEditar:pTutelaEditar");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
            if (getListaTutelas() != null && !getListaTutelas().isEmpty()) {
                PrimeFaces.current().executeScript("PF('dialogoSeleccionarTutela').show()");
                PrimeFaces.current().ajax().update("frmSeleccionarTutela:tablaTutelas");
            }
        }
        generarMensajes();
    }

    public void listarContratosAprobar() {
        setParamConsulta4(new ParamConsulta());
        if (getObjeto().getObjetoTecnologia() != null) {
            String tecnologias = String.valueOf(getObjeto().getObjetoTecnologia().getMaTecnologiaId());
            getParamConsulta4().setParametroConsulta1(getObjeto().getObjetoTecnologia().getTipoTecnologia());
            getParamConsulta4().setParametroConsulta2(tecnologias);
            PrimeFaces.current().executeScript("PF('dialogoContratoLista').show()");
            PrimeFaces.current().ajax().update("frmContratoLista");
        } else {
            addError("No se encontro la tecnologia");
        }
        generarMensajes();
    }

    public void listarContratosAprobarTodo() {
        setParamConsulta4(new ParamConsulta());
        String tecnologias = "";
        for (AuAnexo3Item item : getSelectedTecnologia()) {
            if (!tecnologias.equals("")) {
                tecnologias += ",";
            }
            tecnologias += item.getMaTecnologiaId();
        }
        if (!tecnologias.equals("")) {
            getParamConsulta4().setParametroConsulta1(getSelectedTecnologia().get(0).getTipoTecnologia());
            getParamConsulta4().setParametroConsulta2(tecnologias);
            PrimeFaces.current().executeScript("PF('dialogoContratoListaTodo').show()");
            PrimeFaces.current().ajax().update("frmContratoListaTodo");
        } else {
            addError("No se encontrarón contratos para los servicios seleccionados");
        }
    }

    public void listarIpsAuditar() {
        getObjeto().setEsAuditar(true);
        consultarIps();
//        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
    }

    public void refrescarIps() {
        getAuSolicitudServicio().listarIps(this);
    }

    public void refrescarContratoDetalle() {
        getAuSolicitudServicio().listarContratoDetalle(this);
        generarMensajes();
    }

    public void refrescarCotizacion() {
        getAuSolicitudServicio().listarCotizacion(this);
        generarMensajes();
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjeto().setCntPrestadorSedeId(ips);
        // cambio por la ley 2335
        getObjeto().setHabilitarCampoDireccionAlternativa(true);
        getObjeto().setHabilitarCampoModalidadTecnologia(true);
        getObjeto().setHabilitarCampoFinalidadTecnologia(true);
        getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_ORIGEN_ATENCION);
        getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_SERVICIO_SOLICITADO);
        getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_UBICACION_PACIENTE);
        getObjeto().setMaeOrigenAtencionId(0);
        getObjeto().setMaeOrigenAtencionCodigo(null);
        getObjeto().setMaeOrigenAtencionValor(null);
        getObjeto().setMaeTipoServicioId(null);
        getObjeto().setMaeTipoServicioCodigo(null);
        getObjeto().setMaeTipoServicioValor(null);
        getObjeto().setMaeUbicacionId(null);
        getObjeto().setDireccionAlternativa(null);
        getObjeto().setMaeModalidadTecnologiaId(null);
        getObjeto().setMaeModalidadTecnologiaCodigo(null);
        getObjeto().setMaeModalidadTecnologiaValor(null);
        getObjeto().setMaeFinalidadTecnologiaId(null);
        getObjeto().setMaeFinalidadTecnologiaCodigo(null);
        getObjeto().setMaeFinalidadTecnologiaValor(null);
        getObjeto().setVersion(AuConstantes.VERSION_0);
        if (getObjeto().getCntPrestadorSedeId().getFechaFacturaElectronica() != null) {
            getObjeto().setVersion(AuConstantes.VERSION_1);
            getObjeto().setHabilitarCampoDireccionAlternativa(false);
            getObjeto().setHabilitarCampoModalidadTecnologia(false);
            getObjeto().setHabilitarCampoFinalidadTecnologia(false);
            getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_CAUSA_MOTIVA_ATENCION);
            getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_ATENCION_SOLICITADA);
            getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_GRUPO_SERVICIOS);
            getAuSolicitudServicio().completarMaestroVersion2335(this);
        } else if (getObjeto().getCntPrestadorSedeId().getGrupoRipsMinisterio() != null) {
            if (getObjeto().getCntPrestadorSedeId().getGrupoRipsMinisterio().equals(1)
                    || getObjeto().getCntPrestadorSedeId().getGrupoRipsMinisterio().equals(2)) {
                getObjeto().setVersion(AuConstantes.VERSION_1);
                getObjeto().setHabilitarCampoDireccionAlternativa(false);
                getObjeto().setHabilitarCampoModalidadTecnologia(false);
                getObjeto().setHabilitarCampoFinalidadTecnologia(false);
                getObjeto().setLabelOrigenAtencion(AuAnexo3.LABEL_CAUSA_MOTIVA_ATENCION);
                getObjeto().setLabelTipoServicioSolicitado(AuAnexo3.LABEL_TIPO_ATENCION_SOLICITADA);
                getObjeto().setLabelUbicacionPaciente(AuAnexo3.LABEL_GRUPO_SERVICIOS);
                getAuSolicitudServicio().completarMaestroVersion2335(this);
            } else {
                getAuSolicitudServicio().completarMaestro(this);
            }
        } else {
            getAuSolicitudServicio().completarMaestro(this);
        }
        //setParamConsulta2(new ParamConsulta());
        getParamConsulta(2).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmCrear:panelipsCrear");
        PrimeFaces.current().ajax().update("frmCrear:dirAlterna");
        PrimeFaces.current().ajax().update("frmCrear:pInfoAtenCrear");
        PrimeFaces.current().ajax().update("frmCrear:pInfoModailidadTecnologiaCrear");
        PrimeFaces.current().ajax().update("frmEditar:panelipsEditar");
        PrimeFaces.current().ajax().update("frmEditar:edirAlterna");
        PrimeFaces.current().ajax().update("frmEditar:pInfoAtenEditar");
        PrimeFaces.current().ajax().update("frmEditar:pInfoModailidadTecnologiaEditar");
        PrimeFaces.current().ajax().update("frmGestionarDevoluciones:panelipsDevoluciones");
    }

    public void onRowSelectContrato(SelectEvent event) {
        CntContratoDetalle contrato = (CntContratoDetalle) event.getObject();
        CntPrestadorSede ips = contrato.getCntContratoSede().getCntPrestadorSede();
        getObjeto().setObjetoIps(ips);
        getObjeto().setObjetoContratoDetalle(contrato);
        getObjeto().getObjetoTecnologia().setValorTecnologia(contrato.getValorContratado());
        PrimeFaces.current().executeScript("PF('dialogoContratoLista').hide()");
        PrimeFaces.current().ajax().update("frmAprobar");
    }

    public void onRowSelectContratoTodo(SelectEvent event) {
        CntContratoDetalle contrato = (CntContratoDetalle) event.getObject();
        CntPrestadorSede ips = contrato.getCntContratoSede().getCntPrestadorSede();
        getObjeto().setObjetoIps(ips);
        boolean aprobar = getAuSolicitudServicio().validarIpsContrato(this);
        if (aprobar) {
            getObjeto().setObjetoContratoDetalle(contrato);
            PrimeFaces.current().executeScript("PF('dialogoContratoListaTodo').hide()");
            PrimeFaces.current().ajax().update("frmAprobarTodo");
        } else {
            addMensaje("La ips seleccionada no cuenta con contrato valido en todas las tecnologias porfavor seleccionar otra");
            generarMensajes();
        }
    }

    public void onRowSelectCotizacion(SelectEvent event) {
        AuCotizacion cotizacion = (AuCotizacion) event.getObject();
        CntPrestadorSede ips = cotizacion.getCntPrestadorSede();
        getObjeto().setObjetoCotizacion(cotizacion);
        getObjeto().setObjetoIps(ips);
        PrimeFaces.current().executeScript("PF('dialogoListaCotizacion').hide()");
        PrimeFaces.current().ajax().update("frmAprobar");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmSolicitudes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().executeScript("PF('dialogoVerSolicitud').show()");
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().ajax().update("frmAuditoriaVer:labelDatosAuditoria");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().executeScript("PF('dialogoCrearSolicitud').show()");
                    PrimeFaces.current().ajax().update("frmCrear");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dialogoCrearSolicitud').hide()");
                    PrimeFaces.current().ajax().update("frmSolicitudes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().executeScript("PF('dialogoEditarSolicitud').show()");
                    PrimeFaces.current().ajax().update("frmEditar");
                    PrimeFaces.current().ajax().update("frmAuditoriaEditar:labelDatosAuditoria");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().executeScript("PF('dialogoEditarSolicitud').hide()");
                    PrimeFaces.current().ajax().update("frmSolicitudes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmSolicitudes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1://Ver todos
                    break;
                case Url.ACCION_ADICIONAL_2://Aditar solicitud
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().executeScript("PF('dialogoAuditarSolicitud').show()");
                            PrimeFaces.current().ajax().update("frmAuditarSolicitud");
                            PrimeFaces.current().ajax().update("frmAuditoriaAuditar:labelDatosAuditoria");
                            crearLog("Auditar solicitud", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1://APROBAR
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoAprobar').show()");
                                    PrimeFaces.current().ajax().update("frmAprobar");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoAprobar').hide()");
                                    PrimeFaces.current().ajax().update("frmAuditarSolicitud");
//                                    PrimeFaces.current().ajax().update("frmSolicitudes");
                                    crearLog("Aprobar item", getObjeto().getObjetoTecnologia().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2://APROBAR TODO
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoAprobarTodo').show()");
                                    PrimeFaces.current().ajax().update("frmAprobarTodo");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoAprobarTodo').hide()");
                                    PrimeFaces.current().ajax().update("frmAuditarSolicitud");
//                                    PrimeFaces.current().ajax().update("frmSolicitudes");
                                    crearLog("Aprobar varios items", getSelectedTecnologia().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_3://RECHAZAR
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoRechazar').show()");
                                    PrimeFaces.current().ajax().update("frmRechazar");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoRechazar').hide()");
//                                    PrimeFaces.current().ajax().update("frmSolicitudes");
                                    PrimeFaces.current().ajax().update("frmAuditarSolicitud");
                                    crearLog("Rechazar item", getObjeto().getObjetoTecnologia().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_4://RECHAZAR TODO
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoRechazarTodo').show()");
                                    PrimeFaces.current().ajax().update("frmRechazarTodo");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoRechazarTodo').hide()");
//                                    PrimeFaces.current().ajax().update("frmSolicitudes");
                                    crearLog("Rechazar varios items", getSelectedTecnologia().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_5://DEVOLVER
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoDevolver').show()");
                                    PrimeFaces.current().ajax().update("frmDevolver");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoDevolver').hide()");
//                                    PrimeFaces.current().ajax().update("frmSolicitudes");
                                    crearLog("Devolver un item", getObjeto().getObjetoTecnologia().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_6://DEVOLVER TODO
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoDevolverTodo').show()");
                                    PrimeFaces.current().ajax().update("frmDevolverTodo");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoDevolverTodo').hide()");
//                                    PrimeFaces.current().ajax().update("frmSolicitudes");
                                    crearLog("Devolver varios items", getSelectedTecnologia().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_7: //derivar
                            switch (getTakeAccion()) {
                                case Url.ACCION_EDITAR:
                                    PrimeFaces.current().ajax().update("frmDerivar");
                                    PrimeFaces.current().executeScript("PF('dialogoDerivar').show()");
                                    crearLog("Ver Derivación");
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoDerivar').hide()");
                                    PrimeFaces.current().ajax().update("frmAuditarSolicitud");
//                                    PrimeFaces.current().ajax().update("frmSolicitudes");
                                    crearLog("Derivar");
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_8: //Devolver Todo
                            break;
                        case Url.ACCION_ADICIONAL_9: //Negar item
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoAnular').show()");
                                    PrimeFaces.current().ajax().update("frmAnularItem");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoAnular').hide()");
                                    PrimeFaces.current().ajax().update("frmAuditarSolicitud");
                                    crearLog("Negar item", getObjeto().getObjetoTecnologia().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_10: //Negar todo
                            switch (getTakeAccion()) {
                                case ACCION_EDITAR:
                                    PrimeFaces.current().executeScript("PF('dialogoNegarTodo').show()");
                                    PrimeFaces.current().ajax().update("frmNegarTodo");
                                    break;
                                case ACCION_MODIFICAR:
                                    PrimeFaces.current().executeScript("PF('dialogoNegarTodo').hide()");
                                    PrimeFaces.current().ajax().update("frmAuditarSolicitud");
                                    crearLog("Negar varios items", getSelectedTecnologia().toString());
                                    break;
                            }
                            break;
                        case ACCION_GUARDAR:
                            refrescarProgrmaEspecial();
                            PrimeFaces.current().executeScript("PF('dialogoSugerirProgramaCrear').hide()");
                            PrimeFaces.current().ajax().update("frmAuditarSolicitud:pSugeridoAuditar");
                            PrimeFaces.current().ajax().update("frmAuditarSolicitud:listaProgrmaEspecialAuditar");
                            //crearLog("Guardar Sugerido", getObjetoSugerido().toString());
                            break;
                        case ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVerSugerido");
                            PrimeFaces.current().executeScript("PF('dialogoVerSugerido').show()");
                            //crearLog("Ver Sugerido", getObjetoSugerido().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3://Anular solicitud
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().executeScript("PF('dialogoAnularSolicitud').show()");
                            PrimeFaces.current().ajax().update("frmAnularSolicitud");
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().executeScript("PF('dialogoAnularSolicitud').hide()");
                            PrimeFaces.current().ajax().update("frmSolicitudes");
                            crearLog("Anular item", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5: //Gestionar Devoluciones
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().executeScript("PF('dialogoGestionarDevoluciones').show()");
                            PrimeFaces.current().ajax().update("frmGestionarDevoluciones");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoMensajeDevolucion').hide()");
                            PrimeFaces.current().ajax().update("frmGestionarDevoluciones");
                            if (getObjeto().getListaDevoluciones().isEmpty()) {
                                PrimeFaces.current().executeScript("PF('dialogoGestionarDevoluciones').hide()");
                            }
                            PrimeFaces.current().ajax().update("frmSolicitudes");
                            crearLog("Gestionar Devolucion", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_9: //Gestionar Posfechado
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().executeScript("PF('dialogoGestionarPosFechado').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoGestionarPosFechado').hide()");
                            crearLog("Gestionar Posfechado", getObjeto().getObjetoTecnologia().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public void consultarAfiliado() {
        PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
//        PrimeFaces.current().ajax().update("frmAfiliadoLista");
    }

    public void consultarIps() {
        getObjeto().setEsAuditar(false);
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
//        PrimeFaces.current().ajax().update("frmIpsLista");
    }

    public void consultarDiagnostico() {
        if (getObjeto().getAsegAfiliadoId() != null) {
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
        } else {
            getObjeto().setObjetoDiagnostico(new AuAnexo3Diagnostico());
            addError("Se debe seleccionar el afiliado");
            generarMensajes();
        }
    }

    public void consultarMedicamento() {
        if (getObjeto().getAsegAfiliadoId() != null) {
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
        } else {
            getObjeto().setObjetoDiagnostico(new AuAnexo3Diagnostico());
            addError("Se debe seleccionar el afiliado");
            generarMensajes();
        }
    }

    public void consultarProcedimiento() {
        if (getObjeto().getAsegAfiliadoId() != null) {
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
            PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
        } else {
            getObjeto().setObjetoDiagnostico(new AuAnexo3Diagnostico());
            addError("Se debe seleccionar el afiliado");
            generarMensajes();
        }
    }

    public void consultarInsumo() {
        if (getObjeto().getAsegAfiliadoId() != null) {
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmInsumoBusqueda");
        } else {
            getObjeto().setObjetoDiagnostico(new AuAnexo3Diagnostico());
            addError("Se debe seleccionar el afiliado");
            generarMensajes();
        }
    }

    public void consultarPaquete() {
        if (getObjeto().getAsegAfiliadoId() != null) {
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
            PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
        } else {
            getObjeto().setObjetoDiagnostico(new AuAnexo3Diagnostico());
            addError("Se debe seleccionar el afiliado");
            generarMensajes();
        }
    }

    public void consultarServicioSolicitado() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').show()");
            PrimeFaces.current().ajax().update("frmServiciosHabilitacionBusqueda");
        } catch (Exception ex) {
            //getObjeto().setObjetoServicioHabilitacion(new MaServicioHabilitacion());
            addError(ex.getMessage());
        }
    }

    public void consultarEspecialidad() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').show()");
            PrimeFaces.current().ajax().update("frmEspecialidadBusqueda");
        } catch (Exception ex) {
            //getObjeto().setObjetoServicioHabilitacion(new MaServicioHabilitacion());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoDiagnostico() {
        boolean agregar = true;
        for (AuAnexo3Diagnostico diag : getObjeto().getAuAnexo3DiagnosticosList()) {
            if (diag.getMaDiagnosticosId() == getDiagnosticosBean().getObjeto().getId()) {
                agregar = false;
                addMensaje("El diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
            }
        }
        if (agregar) {
            AuAnexo3Diagnostico diagnostico = new AuAnexo3Diagnostico();
            diagnostico.setMaDiagnosticosId(getDiagnosticosBean().getObjeto().getId());
            diagnostico.setMaDiagnosticosCodigo(getDiagnosticosBean().getObjeto().getCodigo());
            diagnostico.setMaDiagnosticosValor(getDiagnosticosBean().getObjeto().getNombre());
            if (getObjeto().getAuAnexo3DiagnosticosList().isEmpty()) {
                diagnostico.setPrincipal(true);
            }
            String valorTipoDocumento = obtenerValorTipoDocumentoAfiliado(getObjeto().getAsegAfiliadoId().getMaeTipoDocumento());
            String mensaje = getAuSolicitudServicio().validarDiagnostico(diagnostico.getMaDiagnosticosCodigo(), valorTipoDocumento, getObjeto().getAsegAfiliadoId().getNumeroDocumento());
            if (mensaje == null) {
                getObjeto().getAuAnexo3DiagnosticosList().add(diagnostico);
                getObjeto().setObjetoDiagnostico(diagnostico);
                PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
                PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
                PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosEditar");
                PrimeFaces.current().ajax().update("frmGestionarDevoluciones:tablaDiagnosticosDevoluciones");
            } else {
                addError(mensaje);
                generarMensajes();
            }

        } else {
            generarMensajes();
        }
        getDiagnosticosBean().setObjeto(new MaDiagnostico());
    }

    public void cerrarDialogoMedicamento() {
        AuAnexo3Item itemNuevo = new AuAnexo3Item();
        //intercambia medicamento por agrupador
        itemNuevo.setTipoTecnologia(AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO);
        MaMedicamento medicamento = getMedicamentosBean().getObjeto();
        itemNuevo.setMaTecnologiaId(medicamento.getMaAgrupadorMedicamento().getId());
        itemNuevo.setMaTecnologiaCodigo(medicamento.getMaAgrupadorMedicamento().getCodigo());
        if (medicamento.getMaAgrupadorMedicamento().getNombre().length() > 510) {
            itemNuevo.setMaTecnologiaValor(medicamento.getMaAgrupadorMedicamento().getNombre().substring(0, 510));
        } else {
            itemNuevo.setMaTecnologiaValor(medicamento.getMaAgrupadorMedicamento().getNombre());
        }
        boolean agregar = getObjeto().getAuAnexo3ItemsList().stream()
                .noneMatch(item -> item.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO
                && item.getMaTecnologiaId() == itemNuevo.getMaTecnologiaId());

        if (agregar) {
            //informacion de medicamenteo para uditoria
            itemNuevo.setMaMedicamentoId(medicamento.getId());
            itemNuevo.setMaMedicamentoCodigo(medicamento.getCum());
            itemNuevo.setMaMedicamentoValor(medicamento.getDescripcionEstandarizada());
//            itemNuevo.setMedicamento(medicamento);
            String valorTipoDocumento = obtenerValorTipoDocumentoAfiliado(getObjeto().getAsegAfiliadoId().getMaeTipoDocumento());
            String mensaje = getAuSolicitudServicio().validarTecnologia("" + itemNuevo.getTipoTecnologia(), itemNuevo.getMaTecnologiaCodigo(), valorTipoDocumento, getObjeto().getAsegAfiliadoId().getNumeroDocumento());
            if (mensaje == null) {
                getObjeto().setObjetoTecnologia(itemNuevo);
                getObjeto().getObjetoTecnologia().setCantidadSolicitada(1);
                PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
                if (getAuSolicitudServicio().validarTecnologiaPosfechada(getObjeto().getAsegAfiliadoId().getId(), itemNuevo.getMaTecnologiaId(), this)) {
                    PrimeFaces.current().ajax().update("frmConfirmacionPosfechada");
                    PrimeFaces.current().executeScript("PF('dialogoConfirmacionPosfechada').show()");
                } else {
                    PrimeFaces.current().ajax().update("frmGestionarMedicamento");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarMedicamento').show()");
                }
            } else {
                addError(mensaje);
                generarMensajes();
            }

        } else {
            addMensaje(String.format(AuConstantes.ERROR_ADICION_AGRUPADOR_MEDICAMENTO, medicamento.getMaAgrupadorMedicamento().getNombre()));
            generarMensajes();
        }
    }

    public void cerrarDialogoPaquete() {
        boolean agregar = true;
        if (!getObjeto().getAuAnexo3ItemsList().isEmpty()) {
            for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
                if (item.getTipoTecnologia() == AuConstantes.ID_PAQUETE
                        && item.getMaTecnologiaId() == getPaquetesBean().getObjeto().getId()) {
                    agregar = false;
                    addMensaje(String.format(AuConstantes.ERROR_ADICION_PAQUETE, getPaquetesBean().getObjeto().getNombre()));
                }
            }
        }

        if (agregar) {
            AuAnexo3Item itemNuevo = new AuAnexo3Item();
            itemNuevo.setTipoTecnologia(AuConstantes.ID_PAQUETE);
            MaPaquete paquete = getPaquetesBean().getObjeto();
            itemNuevo.setMaTecnologiaId(paquete.getId());
            itemNuevo.setMaTecnologiaCodigo(paquete.getCodigo());
            itemNuevo.setMaTecnologiaValor(paquete.getNombre());
            itemNuevo.setCantidadSolicitada(1);
            String valorTipoDocumento = obtenerValorTipoDocumentoAfiliado(getObjeto().getAsegAfiliadoId().getMaeTipoDocumento());
            String mensaje = getAuSolicitudServicio().validarTecnologia("" + itemNuevo.getTipoTecnologia(), itemNuevo.getMaTecnologiaCodigo(), valorTipoDocumento, getObjeto().getAsegAfiliadoId().getNumeroDocumento());
            if (mensaje == null) {
                getObjeto().setObjetoTecnologia(itemNuevo);
                PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
                PrimeFaces.current().ajax().update("frmGestionarPaquete");
                PrimeFaces.current().executeScript("PF('dialogoGestionarPaquete').show()");
            } else {
                addError(mensaje);
                generarMensajes();
            }
        } else {
            generarMensajes();
        }
    }

    public void cerrarDialogoTecnologia() {
        boolean agregar
                = getObjeto().getAuAnexo3ItemsList().stream()
                        .noneMatch(item -> item.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA
                        && item.getMaTecnologiaId() == getTecnologiasBean().getObjeto().getId());

        if (agregar) {
            AuAnexo3Item itemNuevo = new AuAnexo3Item();
            itemNuevo.setTipoTecnologia(AuConstantes.ID_TECNOLOGIA);
            MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
            itemNuevo.setMaTecnologiaId(tecnologia.getId());
            itemNuevo.setMaTecnologiaCodigo(tecnologia.getCodigoPropio());
            itemNuevo.setMaTecnologiaValor(tecnologia.getCupsDescipcion());
            itemNuevo.setTecnologia(tecnologia);
            String valorTipoDocumento = obtenerValorTipoDocumentoAfiliado(getObjeto().getAsegAfiliadoId().getMaeTipoDocumento());
            String mensaje = getAuSolicitudServicio().validarTecnologia("" + itemNuevo.getTipoTecnologia(), tecnologia.getCodigoPropio(), valorTipoDocumento, getObjeto().getAsegAfiliadoId().getNumeroDocumento());
            if (mensaje == null) {
                getObjeto().setObjetoTecnologia(itemNuevo);
                getObjeto().getObjetoTecnologia().setCantidadSolicitada(1);
                PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
                if (tecnologia.getCobertura() == 2) {
                    PrimeFaces.current().ajax().update("frmConfirmacionTecnologia");
                    PrimeFaces.current().executeScript("PF('dialogoConfirmacionTecnologia').show()");
                } else if (getAuSolicitudServicio().validarTecnologiaPosfechada(getObjeto().getAsegAfiliadoId().getId(), itemNuevo.getMaTecnologiaId(), this)) {
                    PrimeFaces.current().ajax().update("frmConfirmacionPosfechada");
                    PrimeFaces.current().executeScript("PF('dialogoConfirmacionPosfechada').show()");
                } else {
                    getAuSolicitudServicio().listarServiocioHabilitacionTecnologia(this);
                    PrimeFaces.current().ajax().update("frmGestionarProcedimiento");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarProcedimiento').show()");
                }
            } else {
                addError(mensaje);
                generarMensajes();
            }

        } else {
            addMensaje(String.format(AuConstantes.ERROR_ADICION_TECNOLOGIA, getTecnologiasBean().getObjeto().getCupsDescipcion()));
            generarMensajes();
        }
    }

    public void validarTecnologia() {
        getAuSolicitudServicio().listarServiocioHabilitacionTecnologia(this);
        PrimeFaces.current().executeScript("PF('dialogoConfirmacionTecnologia').hide()");
        PrimeFaces.current().ajax().update("frmGestionarProcedimiento");
        PrimeFaces.current().executeScript("PF('dialogoGestionarProcedimiento').show()");
    }

    public void cerrarDialogoInsumo() {
        boolean agregar = true;
        if (!getObjeto().getAuAnexo3ItemsList().isEmpty()) {
            for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
                if (item.getTipoTecnologia() == AuConstantes.ID_INSUMO
                        && item.getMaTecnologiaId() == getInsumosBean().getObjeto().getId()) {
                    agregar = false;
                    addMensaje(String.format(AuConstantes.ERROR_ADICION_INSUMO, getInsumosBean().getObjeto().getDescripcion()));
                }
            }
        }
        if (agregar) {
            AuAnexo3Item itemNuevo = new AuAnexo3Item();
            itemNuevo.setTipoTecnologia(AuConstantes.ID_INSUMO);
            MaInsumo insumo = getInsumosBean().getObjeto();
            if (insumo.getCodigo() != null && !insumo.getCodigo().equals("")) {
                itemNuevo.setMaTecnologiaId(insumo.getId());
                itemNuevo.setMaTecnologiaCodigo(insumo.getCodigo());
                itemNuevo.setMaTecnologiaValor(insumo.getDescripcion());
                itemNuevo.setInsumo(insumo);
                String valorTipoDocumento = obtenerValorTipoDocumentoAfiliado(getObjeto().getAsegAfiliadoId().getMaeTipoDocumento());
                String mensaje = getAuSolicitudServicio().validarTecnologia("" + itemNuevo.getTipoTecnologia(), itemNuevo.getMaTecnologiaCodigo(), valorTipoDocumento, getObjeto().getAsegAfiliadoId().getNumeroDocumento());
                if (mensaje == null) {
                    getObjeto().setObjetoTecnologia(itemNuevo);
                    getObjeto().getObjetoTecnologia().setCantidadSolicitada(1);
                    PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
                    PrimeFaces.current().ajax().update("frmGestionarInsumo");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarInsumo').show()");

                } else {
                    addError(mensaje);
                }
            } else {
                addError("El insumo no tiene codigo");
            }
        }
        generarMensajes();
    }

    public void cerrarDialogoServiciosHabilitacion() {
        MaServicioHabilitacion servicioHabilitado = getServicioHabilitacionBean().getObjeto();
        if (servicioHabilitado.isActivo()) {
            getObjeto().setMaServicioHabilitadoId(servicioHabilitado.getId());
            getObjeto().setMaServicioHabilitadoCodigo("" + servicioHabilitado.getCodigo());
            getObjeto().setMaServicioHabilitadoValor(servicioHabilitado.getNombre());
            getObjeto().setMaServicioSolicitadoId(servicioHabilitado.getId());
            getObjeto().setMaServicioSolicitadoCodigo("" + servicioHabilitado.getCodigo());
            getObjeto().setMaServicioSolicitadoValor(servicioHabilitado.getNombre());

            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrear:serAtenCrear");
            PrimeFaces.current().ajax().update("frmEditar:serAtenEditar");
            PrimeFaces.current().ajax().update("frmGestionarDevoluciones:serAtenDevoluciones");
        } else {
            addError("El servicio seleccionado no esta activo");
            generarMensajes();
        }

    }

    public void cerrarDialogoEspecialidad() {
        MaEspecialidad especialidad = getSelEspecialidadesBean().getObjeto();
        getObjeto().setObjetoEspecialidad(especialidad);
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmCrear:especialidadProfCrear");
        PrimeFaces.current().ajax().update("frmEditar:especialidadProfEditar");
        PrimeFaces.current().ajax().update("frmGestionarDevoluciones:especialidadProfDevoluciones");
    }

    public Date obtenerFechaActual() throws ParseException {
        SimpleDateFormat formatoBasico = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoCompleto = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fechaActual = new Date();
        String nuevaFecha = formatoBasico.format(fechaActual) + " 23:59:59";
        fechaActual = formatoCompleto.parse(nuevaFecha);
        return fechaActual;
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipioDepartamento(int id) {
        try {
            String municipio = obtenerMunicipio(id);
            String departamento = obtenerDepartamento(id);
            return municipio + " - " + departamento;
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerValorTipoDocumentoAfiliado(int id) {
        try {
            return hashTipoDocumento.get(id).getValor();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerString(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    public boolean obtenerBoolean(String tex) {
        return (tex == null ? false : tex.equals(AuConstantes.TEXTO_SI));
    }

    public String obtenerValorBoolean(boolean bool) {
        if (getObjeto().getAsegAfiliadoId() == null) {
            return "";
        } else {
            return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
        }
    }

    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getTamanoLimiteAnexo() <= 0 ? 5242880 : getTamanoLimiteAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (getObjeto().getMaeTipoDocumentoArchivo() != 0) {
                AuSolicitudAdjunto adjuntoDocumento = new AuSolicitudAdjunto();
                UploadedFile archivo = event.getFile();
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                byte[] arreglo = archivo.getInputStream().readAllBytes();
                adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
                adjuntoDocumento.setByteStream(arreglo);
                int indiceExtension = nombreAdjunto.lastIndexOf(".");
                String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
                adjuntoDocumento.setNombre(nombreAdjunto);
                adjuntoDocumento.setArchivo(nombreAdjunto);
                adjuntoDocumento.setExtension(extension);
                Maestro tipoArchivo = obtenerMaestroTipoArchivo(getObjeto().getMaeTipoDocumentoArchivo());
                adjuntoDocumento.setMaeTipoArchivoId(tipoArchivo.getId());
                adjuntoDocumento.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                adjuntoDocumento.setMaeTipoArchivoValor(tipoArchivo.getNombre());
                getObjeto().getAuSolicitudAdjuntosList().add(adjuntoDocumento);
                PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').hide()");
                PrimeFaces.current().ajax().update("frmCrear:pSoporteCrear");
                PrimeFaces.current().ajax().update("frmEditar:tablaSoportesEditar");
                PrimeFaces.current().ajax().update("frmGestionarDevoluciones:tablaSoportesDevoluciones");
            } else {
                this.addError("Tipo Archivo: Este campo es obligatorio.");
            }
        } catch (IOException ex) {
            Logger.getLogger(AuSolicitudBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            PrimeFaces.current().ajax().update("frmCrear:pSoporteCrear");
        }
    }

    public void borrarSoporte(AuSolicitudAdjunto adjunto) {
        this.getObjeto().getAuSolicitudAdjuntosList().remove(adjunto);
        PrimeFaces.current().ajax().update("frmCrear:pSoporteCrear");
    }

    public void borrarSoporteBD(AuSolicitudAdjunto adjunto) {
        this.getObjeto().getAuSolicitudAdjuntosList().remove(adjunto);
        if (adjunto.getId() != null && adjunto.getId() != 0) {
            getAuSolicitudServicio().borrarAdjunto(adjunto.getId(), this);
            generarMensajes();
        }
        PrimeFaces.current().ajax().update("frmEditar:tablaSoportesEditar");
    }

    private void construirListas() {
        List<String> listaFre = new ArrayList();
        listaFre.add("Dias");
        listaFre.add("Semanas");
        listaFre.add("Meses");
        this.setListaFrecuencia(listaFre);
    }

    public String calcularEdad(Date fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = AuConstantes.formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public void buscarProfesional() {
        boolean buscar = true;
        if (getObjeto().getCntPrestadorSedeId() == null) {
            buscar = false;
            addError("Se debe seleccionar una ips");
        }
        if (getObjeto().getCntProfesionaleId().getMaeTipoCodumentoId() == 0 || getObjeto().getCntProfesionaleId().getDocumento() == null) {
            buscar = false;
        }
        if (buscar) {
            getAuSolicitudServicio().buscarProfesional(this);
            PrimeFaces.current().ajax().update("frmCrear:pProfesionalCrear");
            PrimeFaces.current().ajax().update("frmEditar:pProfesionalEditar");
        } else {
            generarMensajes();
        }
    }

    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }

    }

    public String obtenerNombreFuenteOrigen(int id) {
        return AuConstantes.obtenerNombreFuenteOrigen(id);
    }

    private void agregarAlDianosticoActual() {
        Maestro maestro = null;
        try {
            maestro = hashTipoDiagnostico.get(getIdTipoDiagnostico());
        } catch (Exception e) {
            maestro = null;
        }

        if (maestro != null) {
            getObjeto().getObjetoDiagnostico().setObjetoTipoDiagnostico(maestro);
        } else {
            addError(AuConstantes.ERROR_NO_MAESTRO);
            generarMensajes();
        }
    }

    public Maestro obtenerMaestroAmbito(int id) {
        Maestro ma = null;
        try {
            ma = hashTipoAmbito.get(id);
        } catch (Exception e) {
            ma = null;
        }
        return ma;
    }

    private AuAnexo3Diagnostico obtenerMaDiagnostico(int id) {
        AuAnexo3Diagnostico dia = null;
        for (AuAnexo3Diagnostico diagno : getObjeto().getAuAnexo3DiagnosticosList()) {
            if (diagno.getMaDiagnosticosId() == id) {
                dia = diagno;
            }
        }
        return dia;
    }

    public void borrarDiagnostico(int id) {
        List<AuAnexo3Diagnostico> nuevaLista = new ArrayList();
        boolean tiene_pricipal = false;
        if (getObjeto().getAuAnexo3DiagnosticosList().size() > 1) {
            getObjeto().getAuAnexo3DiagnosticosList().stream().filter(diagnostico -> (diagnostico.getMaDiagnosticosId() != id)).forEachOrdered(diagnostico -> {
                nuevaLista.add(diagnostico);
            });

            for (AuAnexo3Diagnostico diagnostico : nuevaLista) {
                if (diagnostico.getPrincipal()) {
                    tiene_pricipal = true;
                }
            }
            if (!tiene_pricipal && nuevaLista.size() > 0) {
                nuevaLista.get(0).setPrincipal(true);
            }
            getObjeto().setAuAnexo3DiagnosticosList(nuevaLista);
            PrimeFaces.current().ajax().update("frmCrear:tablaDiagnosticosCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosEditar");

            List<AuAnexo3Item> listaNueva = new ArrayList();
            if (getObjeto().getAuAnexo3ItemsList().size() > 0) {
                getObjeto().getAuAnexo3ItemsList().stream().filter(tecnologia -> (tecnologia.getMaDiagnosticoId() != id)).forEach(tecnologia -> {
                    listaNueva.add(tecnologia);
                });
                getObjeto().setAuAnexo3ItemsList(listaNueva);
                PrimeFaces.current().ajax().update("frmCrear:tablaTecnologiasCrear");
                PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiasEditar");
            }

        } else {
            addError("Debe existir al menos un diagnostico");
            generarMensajes();
        }
    }

    public void borrarDiagnosticoBD(AuAnexo3Diagnostico diagnostico) {
        if (getObjeto().getAuAnexo3DiagnosticosList().size() > 1) {
            int idDiagnostico = 0;
            boolean pricipal = false;
            for (AuAnexo3Diagnostico diagn : getObjeto().getAuAnexo3DiagnosticosList()) {
                if (diagn.getId() != null && Objects.equals(diagn.getId(), diagnostico.getId())) {
                    idDiagnostico = diagn.getMaDiagnosticosId();
                    if (diagn.getPrincipal()) {
                        pricipal = true;
                    }
                }
            }
            getObjeto().getAuAnexo3DiagnosticosList().remove(diagnostico);
            getListaDiagnosticosBorrar().add(diagnostico);
//            getAuSolicitudServicio().borrarDiagnostico(diagnostico.getId(), this);
            if (pricipal && !getObjeto().getAuAnexo3DiagnosticosList().isEmpty()) {
                getObjeto().getAuAnexo3DiagnosticosList().get(0).setPrincipal(true);
            }
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosEditar");
            List<AuAnexo3Item> listaNueva = new ArrayList();
            if (getObjeto().getAuAnexo3ItemsList().size() > 0) {
                for (int i = 0; i < getObjeto().getAuAnexo3ItemsList().size(); i++) {
                    AuAnexo3Item item = getObjeto().getAuAnexo3ItemsList().get(i);
                    if (item.getMaDiagnosticoId() != idDiagnostico) {
                        listaNueva.add(item);
                    } else {
//                        getAuSolicitudServicio().borrarTecnologia(item.getId(), this);
                        getListaItemsBorrar().add(item);
                    }
                }
                getObjeto().setAuAnexo3ItemsList(listaNueva);
                PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiasEditar");
            }

        } else {
            addError("Debe existir al menos un diagnostico");
        }
        generarMensajes();
    }

    public void borrarTecnologia(int id) {
        List<AuAnexo3Item> listaNueva = new ArrayList();
        if (getObjeto().getAuAnexo3ItemsList().size() > 0) {
            getObjeto().getAuAnexo3ItemsList().stream().filter(tecnologia -> (tecnologia.getMaTecnologiaId() != id)).forEach(tecnologia -> {
                listaNueva.add(tecnologia);
            });
            getObjeto().setAuAnexo3ItemsList(listaNueva);
            PrimeFaces.current().ajax().update("frmCrear:tablaTecnologiasCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiasEditar");
        } else {
            addError("Debe existir al menos una prescripción");
            generarMensajes();
        }

    }

//    public void borrarTecnologiaBD(Integer id) {
//        List<AuAnexo3Item> listaNueva = new ArrayList();
//        if (getObjeto().getAuAnexo3ItemsList().size() > 1) {
//            getObjeto().getAuAnexo3ItemsList().stream().filter(tecnologia -> (tecnologia.getId() != id)).forEach(tecnologia -> {
//                listaNueva.add(tecnologia);
//            });
//            getObjeto().setAuAnexo3ItemsList(listaNueva);
//            getAuSolicitudServicio().borrarTecnologia(id, this);
//            PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiasEditar");
//        } else {
//            addError("Debe existir al menos una prescripción");
//        }
//        generarMensajes();
//    }
    public void borrarTecnologiaBD(AuAnexo3Item item) {
        getListaItemsBorrar().add(item);
        getObjeto().getAuAnexo3ItemsList().remove(item);
        PrimeFaces.current().ajax().update("frmEditar:tablaTecnologiasEditar");
    }

    public void editarTecnologia(int id) {
        AuAnexo3Item item = new AuAnexo3Item();
        for (AuAnexo3Item tecnologia : getObjeto().getAuAnexo3ItemsList()) {
            if (tecnologia.getMaTecnologiaId() == id) {
                item = tecnologia;
            }
        }
        getObjeto().setObjetoTecnologia(item);
        switch (item.getTipoTecnologia()) {
            case AuConstantes.ID_TECNOLOGIA:
                getAuSolicitudServicio().listarServiocioHabilitacionTecnologia(this);
                this.idServicioTecnogia = item.getMaServicioSolicitadoId();
                this.idDiagnosticoTecnologia = item.getMaDiagnosticoId();
                PrimeFaces.current().ajax().update("frmEditarProcedimiento");
                PrimeFaces.current().executeScript("PF('dialogoEditarProcedimiento').show()");
                break;
            case AuConstantes.ID_MEDICAMENTO:
                PrimeFaces.current().ajax().update("frmEditarMedicamento");
                PrimeFaces.current().executeScript("PF('dialogoEditarMedicamento').show()");
                break;
            case AuConstantes.ID_INSUMO:
                PrimeFaces.current().ajax().update("frmEditarInsumo");
                PrimeFaces.current().executeScript("PF('dialogoEditarInsumo').show()");
                break;
            case AuConstantes.ID_PAQUETE:
                PrimeFaces.current().ajax().update("frmEditarPaquete");
                PrimeFaces.current().executeScript("PF('dialogoEditarPaquete').show()");
                break;
            case AuConstantes.ID_AGRUPADOR_MEDICAMENTO:
                PrimeFaces.current().ajax().update("frmEditarMedicamento");
                PrimeFaces.current().executeScript("PF('dialogoEditarMedicamento').show()");
                break;
        }
    }

    public void editTecnologia() {
        List<AuAnexo3Item> nuevaLista = new ArrayList();
        for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
            if (item.getMaTecnologiaId() == getObjeto().getObjetoTecnologia().getMaTecnologiaId()) {
                nuevaLista.add(getObjeto().getObjetoTecnologia());
            } else {
                nuevaLista.add(item);
            }
        }
        getObjeto().setAuAnexo3ItemsList(nuevaLista);
        PrimeFaces.current().ajax().update("frmCrear:pPrescripcionCrear");
        switch (getObjeto().getObjetoTecnologia().getTipoTecnologia()) {
            case AuConstantes.ID_TECNOLOGIA:
                PrimeFaces.current().executeScript("PF('dialogoEditarProcedimiento').hide()");
                break;
            case AuConstantes.ID_MEDICAMENTO:
                PrimeFaces.current().executeScript("PF('dialogoEditarMedicamento').hide()");
                break;
            case AuConstantes.ID_INSUMO:
                PrimeFaces.current().executeScript("PF('dialogoEditarInsumo').hide()");
                break;
        }

    }

    public void gestionarTecnologia(int id) {
        boolean gestionar = false;
        AuAnexo3Item item = new AuAnexo3Item();
        for (AuAnexo3Item tecnologia : getObjeto().getAuAnexo3ItemsList()) {
            if (tecnologia.getMaTecnologiaId() == id) {
                item = tecnologia;
                gestionar = true;
            }
        }
        if (gestionar) {
            getObjeto().setObjetoTecnologia(item);
            switch (item.getTipoTecnologia()) {
                case AuConstantes.ID_TECNOLOGIA:
                    getAuSolicitudServicio().listarServiocioHabilitacionTecnologia(this);
                    this.idServicioTecnogia = item.getMaServicioSolicitadoId();
                    this.idDiagnosticoTecnologia = item.getMaDiagnosticoId();
                    PrimeFaces.current().ajax().update("frmGestionarProcedimiento");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarProcedimiento').show()");
                    break;
                case AuConstantes.ID_MEDICAMENTO:
                    PrimeFaces.current().ajax().update("frmGestionarMedicamento");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarMedicamento').show()");
                    break;
                //2023-09-22 jyperez se adiciona case de agrupador de medicamento. No se reemplaza el anterior
                // aunque este apuntando al mismo item
                case AuConstantes.ID_AGRUPADOR_MEDICAMENTO:
                    PrimeFaces.current().ajax().update("frmGestionarMedicamento");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarMedicamento').show()");
                    break;
                case AuConstantes.ID_INSUMO:
                    PrimeFaces.current().ajax().update("frmGestionarInsumo");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarInsumo').show()");
                    break;
                case AuConstantes.ID_PAQUETE:
                    PrimeFaces.current().ajax().update("frmGestionarPaquete");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarPaquete').show()");
                    break;
            }
        } else {
            addError("No se encontro la tecnologia");
            generarMensajes();
        }
    }
    
    public Maestro obtenerMaestroTipoDocumento(int id) {
        try {
            return hashTipoDocumento.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public PePrograma obtenerPeProgramaEspecial(int id) {
        try {
            return hashProgramaEspecial.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroOrigenAtencion(int id) {
        try {
            return hashOrigenAtencion.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroServicioSolicitado(int id) {
        try {
            return hashTipoServicioAtencion.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroUbicacion(int id) {
        try {
            return hashTipoUbicacionPaciente.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public String obtenerTipoTencnologia(int id) {
        return AuConstantes.obtenerTipoTecnologia(id);
    }

    public void cambiarPrincipalDiagnostico(int idDiagnostico) {
        for (AuAnexo3Diagnostico diagnostico : getObjeto().getAuAnexo3DiagnosticosList()) {
            if (diagnostico.getMaDiagnosticosId() != idDiagnostico) {
                diagnostico.setPrincipal(false);
            } else {
                diagnostico.setPrincipal(true);
            }
        }
    }

    public void cambiarTipoDiagnostico(int idDiagnostico) {
        for (AuAnexo3Diagnostico diagnostico : getObjeto().getAuAnexo3DiagnosticosList()) {
            if (diagnostico.getMaDiagnosticosId() == idDiagnostico) {
                Maestro tipoDiagnostico = hashTipoDiagnostico.get(diagnostico.getMaeTipoDiagnosticoId());
                diagnostico.setMaeTipoDiagnosticoCodigo(tipoDiagnostico.getValor());
                diagnostico.setMaeTipoDiagnosticoValor(tipoDiagnostico.getNombre());
            }
        }
    }

    public void realizarPosfechado() {
        boolean posfechar = true;
        if (getObjeto().getObjetoTecnologia().getDuracion() <= 0) {
            posfechar = false;
        }
        if (getObjeto().getObjetoTecnologia().getNumeroEntregas() <= 0) {
            posfechar = false;
        }
        if (getObjeto().getObjetoTecnologia().getCantidadSolicitada() <= 0) {
            posfechar = false;
        }
        if (posfechar) {
            if (getObjeto().getObjetoTecnologia().getDuracion() < getObjeto().getObjetoTecnologia().getNumeroEntregas()) {
                posfechar = false;
                addError("La duracion no puede ser menor al numero de entregas");
            }
            if (getObjeto().getObjetoTecnologia().getCantidadSolicitada() < getObjeto().getObjetoTecnologia().getNumeroEntregas()) {
                posfechar = false;
                addError("La cantidad solicitada no puede ser menor al numero de entregas");
            }
            if (posfechar) {
                getObjeto().getObjetoTecnologia().setListaRangos(new ArrayList());
                getObjeto().getObjetoTecnologia().calculaRango();
            } else {
                generarMensajes();
            }
        }
    }
    
    public void realizarPosfechadoGestionar() {
        boolean posfechar = true;
        if (getObjeto().getObjetoTecnologia().getDuracion() <= 0) {
            posfechar = false;
        }
        if (getObjeto().getObjetoTecnologia().getNumeroEntregas() <= 0) {
            posfechar = false;
        }
        if (getObjeto().getObjetoTecnologia().getCantidadSolicitada() <= 0) {
            posfechar = false;
        }
        if (posfechar) {
            if (getObjeto().getObjetoTecnologia().getDuracion() < getObjeto().getObjetoTecnologia().getNumeroEntregas()) {
                posfechar = false;
                addError("La duracion no puede ser menor al numero de entregas");
            }
            if (getObjeto().getObjetoTecnologia().getCantidadSolicitada() < getObjeto().getObjetoTecnologia().getNumeroEntregas()) {
                posfechar = false;
                addError("La cantidad solicitada no puede ser menor al numero de entregas");
            }
            if (posfechar) {
                getObjeto().getObjetoTecnologia().setListaRangos(new ArrayList());
                getObjeto().getObjetoTecnologia().calculaRangoGestionar();
            } else {
                generarMensajes();
            }
        }
    }
    
    public void validarFechasPosfechado(AuRango rangoActual, int gestionar)  {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            int cantidadTotal = getObjeto().getObjetoTecnologia().getListaRangos().size() - 1;
            for (int i = 0; i < getObjeto().getObjetoTecnologia().getListaRangos().size(); i++) {
                if(rangoActual.getPosicion() == 0){
                    Date fechaLiberacionActual = rangoActual.getFechaLiberacion();
                    if (getObjeto().getFechaSolicitud() == null) {
                        addError("La fecha de la orden medica es obligatoria.");
                        break;
                    }
                    int anios = obtenerAnios(getObjeto().getFechaSolicitud(), fechaLiberacionActual);
                    if (anios >= 1) {
                        addError("La fecha de liberación supera un año apartir de la fecha orden medica. ");
                        break;
                    }
                    getObjeto().getObjetoTecnologia().setListaRangos(new ArrayList());
                    if(gestionar == 0){
                        getObjeto().getObjetoTecnologia().recalcularRango(rangoActual.getFechaLiberacion());
                        PrimeFaces.current().ajax().update("frmGestionarProcedimiento:tablaProcedimientoEditar");
                        PrimeFaces.current().ajax().update("frmGestionarMedicamento:tablaMedicamentoEditar");
                        PrimeFaces.current().ajax().update("frmGestionarInsumo:tablaInsumoEditar");
                        PrimeFaces.current().ajax().update("frmGestionarPaquete:tablaPaqueteEditar");
                    }else{
                        getObjeto().getObjetoTecnologia().recalcularRangoGestionar(rangoActual.getFechaLiberacion());
                        PrimeFaces.current().ajax().update("frmGestionarPosFechado:tablaPosFechadoEditar");
                    }
                    break;
                }else{
                    if (i == 0) {
                        String strFechaLiberacionAnterior = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion());
                        Date fechaLiberacionAnterior = formato.parse(strFechaLiberacionAnterior);
                        String streFechaLiberacionActual = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i + 1).getFechaLiberacion());
                        Date fechaLiberacionActual = formato.parse(streFechaLiberacionActual);
                        String strFechaLiberacionDespues = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i + 2).getFechaLiberacion());
                        Date fechaLiberacionDespues = formato.parse(strFechaLiberacionDespues);
                        if((fechaLiberacionAnterior.equals(fechaLiberacionActual)) && fechaLiberacionDespues.after(fechaLiberacionActual)){
                            continue;
                        }else if(fechaLiberacionAnterior.before(fechaLiberacionActual) && (fechaLiberacionDespues.equals(fechaLiberacionActual))){
                            continue;
                        }else if(fechaLiberacionAnterior.equals(fechaLiberacionActual) && fechaLiberacionDespues.equals(fechaLiberacionActual)){
                            continue;
                        }else if (fechaLiberacionAnterior.before(fechaLiberacionActual) && fechaLiberacionDespues.after(fechaLiberacionActual)) {
                            continue;
                        } else {
                            addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                            break;
                        }
                    }
                    if (i == cantidadTotal) {
                        String strFechaLiberacionAnterior = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i - 1).getFechaLiberacion());
                        Date fechaLiberacionAnterior = formato.parse(strFechaLiberacionAnterior);
                        String streFechaLiberacionActual = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion());
                        Date fechaLiberacionActual = formato.parse(streFechaLiberacionActual);
                        if (fechaLiberacionAnterior.after(fechaLiberacionActual)) {
                            addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionActual));
                        }
                        break;
                    }
                    if (i != 0) {
                        i = i + 1; 
                        if (i == cantidadTotal) {
                            String strFechaLiberacionAnterior = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i - 1).getFechaLiberacion());
                            Date fechaLiberacionAnterior = formato.parse(strFechaLiberacionAnterior);
                            String streFechaLiberacionActual = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion());
                            Date fechaLiberacionActual = formato.parse(streFechaLiberacionActual);
                            if(fechaLiberacionAnterior.equals(fechaLiberacionActual)){
                                continue;
                            }else if (fechaLiberacionAnterior.after(fechaLiberacionActual)) {
                                addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionActual));
                            }
                            break;
                        }else{
                            String strFechaLiberacionAnterior = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i - 1).getFechaLiberacion());
                            Date fechaLiberacionAnterior = formato.parse(strFechaLiberacionAnterior);
                            String streFechaLiberacionActual = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion());
                            Date fechaLiberacionActual = formato.parse(streFechaLiberacionActual);
                            String strFechaLiberacionDespues = AuConstantes.formato2.format(getObjeto().getObjetoTecnologia().getListaRangos().get(i + 1).getFechaLiberacion());
                            Date fechaLiberacionDespues = formato.parse(strFechaLiberacionDespues);
                            if((fechaLiberacionAnterior.equals(fechaLiberacionActual)) && fechaLiberacionDespues.after(fechaLiberacionActual)){
                                continue;
                            }else if(fechaLiberacionAnterior.before(fechaLiberacionActual) && (fechaLiberacionDespues.equals(fechaLiberacionActual))){
                                continue;
                            }else if(fechaLiberacionAnterior.equals(fechaLiberacionActual) && fechaLiberacionDespues.equals(fechaLiberacionActual)){
                                continue;
                            }else if (fechaLiberacionAnterior.before(fechaLiberacionActual) && fechaLiberacionDespues.after(fechaLiberacionActual)) {
                                continue;
                            } else {
                                addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                                break;
                            }
                        }
                    }
                }
            }
        } catch (ParseException e) {
            addError("Por favor revisar las fechas: " + e.getMessage());
        }
        generarMensajes();
    }
    
    public Maestro obtenerMaestroTipoArchivo(int id) {
        Maestro mae = null;
        try {
            mae = hashTipoDocumentoArchivo.get(id);
        } catch (Exception e) {
            mae = null;
        }
        return mae;
    }

    public void ventanaAdjunto() {
        PrimeFaces.current().resetInputs("frmAdjuntoGestionar");
        PrimeFaces.current().ajax().update("frmAdjuntoGestionar");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntoGestionar').show()");
    }

    public Maestro obtenerMaestroCausaExterna(int id) {
        try {
            return hashCausaExterna.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroFinalidad(int id) {
        try {
            return hashTipoFinalidad.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroModalidadTecnologia(Integer id) {
        try {
            return hashModalidadTecnologia.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroFinalidadTecnologia(Integer id) {
        try {
            return hashFinalidadTecnologia.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroCatastrofico(int id) {
        try {
            return hashTipoCatastrofico.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroViaAdministracion(int id) {
        try {
            return hashTipoViaAdministracion.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroRechazo(int id) {
        try {
            return hashRechazos.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroAnulado(int id) {
        try {
            return hashAnulado.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean mostrarEditarTecnologia(int idTipoTecnologia) {
        boolean mostrar = idTipoTecnologia == AuConstantes.ID_MEDICAMENTO;
        return mostrar;
    }

    public boolean validarEstadoPendiente(int estado) {
        return estado == AuAnexo3.ESTADO_PENDIENTE;
    }

    public boolean validarEstadoAuditar(int estado) {
        boolean value = estado != AuAnexo3.ESTADO_ANULADO
                && estado != AuAnexo3.ESTADO_GESTIONADO;
        return estado != AuAnexo3.ESTADO_ANULADO
                && estado != AuAnexo3.ESTADO_GESTIONADO;
    }

    public boolean validarEstadoItem(int estado) {
        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
        return estado == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                || estado == AuAnexo3Item.ESTADO_CON_COTIZACION
                || estado == AuAnexo3Item.ESTADO_RECHAZO_COTIZACION
                || estado == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO;
    }

    public boolean validarEstadoItemTodo(int estado) {
        return estado == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA;
    }

    public boolean validarEstadoItemRechazado(int estado) {
        return estado == AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA;
    }

    public boolean validarEstadoItemMotivo(int estado) {
        return estado == AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA
                || estado == AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA
                || estado == AuAnexo3Item.ESTADO_ANULADO_AUTORIZACION
                || estado == AuAnexo3Item.ESTADO_NEGADO_AUDITORIA
                || estado == AuAnexo3Item.ESTADO_RECHAZO
                || estado == AuAnexo3Item.ESTADO_ANULADO;
    }

    public boolean validarEstadoItemDevolver(int estado) {
        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
        return estado == AuAnexo3Item.ESTADO_CON_COTIZACION
                || estado == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                || estado == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO;
    }

    public StreamedContent generarReporteAnexo3(AuAnexo3 obj) {
        StreamedContent streamContent = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        try {
            List<ReporteAnexo3> listaReportes = getAuSolicitudServicio().generarReporteAnexo3(obj.getId(), this);
            if (!listaReportes.isEmpty()) {
                String nombre = "anexo3-" + obj.getId() + "-" + obj.getAsegAfiliadoId().getNumeroDocumento() + "-" + formato.format(obj.getFechaHoraCrea()) + ".pdf";
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo3.jasper");
                if (obj.getVersion() == AuConstantes.VERSION_1) {
                    nombre = "anexo1-03-" + obj.getId() + "-" + obj.getAsegAfiliadoId().getNumeroDocumento() + "-" + formato.format(obj.getFechaHoraCrea()) + ".pdf";
                    is = getClass().getResourceAsStream("/reportes/Anexo3res2335.jasper");
                }
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombre).build();
                crearLog("Imprimir", getObjeto().toString());
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

    public StreamedContent generarReporteAnexo4(int id) {
        StreamedContent streamContent = null;
        try {
            List<ReporteAnexo4> listaReportes = getAuSolicitudServicio().generarReporteAnexo4(id, this);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo4.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("reporte.pdf").build();
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

    public StreamedContent generarReporteNegacion(AuAnexo3Item anexo3Item) {
        AuRechazo rechazo = getAuSolicitudServicio().buscarRechazo(anexo3Item.getId());
        AuReporte reporte = new AuReporte(getHashUbicaciones());
        StreamedContent streamContent = reporte.generarReporteNegacionImprimir(rechazo, anexo3Item.getMaTecnologiaCodigo());
        return streamContent;
    }

    public StreamedContent generarReporteItemDireccionado(AuAnexo3Item item) {
        AuReporte reporte = new AuReporte(getHashUbicaciones());
        item.setAuAnexo3Id(getObjeto());
        StreamedContent streamContent = reporte.generarReporteDireccionado(item);
        return streamContent;
    }

    private MaTecnologiaServicioHabilitacion obtenerMaServicioTecnolia(int idServicioTecnogia) {
        for (MaTecnologiaServicioHabilitacion servicio : getListaServicioTecnologia()) {
            if (servicio.getId() == idServicioTecnogia) {
                return servicio;
            }
        }
        return null;
    }

    public void seleccionarTutela(String numTutela) throws ParseException {
        TuTutelaRespuesta tute = new TuTutelaRespuesta();
        boolean isEncontrada = getObjeto().getAuAnexo3TutelasList()
                .stream()
                .anyMatch(tut -> Objects.equals(tut.getNumeroTutela(), Integer.parseInt(numTutela)));
        if (isEncontrada) {
            addMensaje("La tutela ya esta agregada a la solicitud.");
        } else {
            for (TuTutelaRespuesta tutela : getListaTutelas()) {
                if (tutela.getNumeroTutela().equals(numTutela)) {
                    tute = tutela;
                }
            }
            if (!tute.isExoneracionTutela()) {
                addMensaje("La tutela no esta exonerada de copago");
            }
            getObjeto().setCopago(true);
            AuAnexo3Tutela tutela = new AuAnexo3Tutela();
            tutela.setNumeroTutela(Integer.parseInt(tute.getNumeroTutela()));
            tutela.setEstadoProceso(tute.getEstadoProceso());
            tutela.setExoneracionCopago(tute.getExoneracionCopago().equalsIgnoreCase("Si"));
            if (tute.getFechaFallo() != null && !tute.getFechaFallo().equals("")) {
                tutela.setFechaFallo(AuConstantes.formato2.parse(tute.getFechaFallo()));
            }
            tutela.setFechaNotificacion(AuConstantes.formato2.parse(tute.getFeachaNotificacion()));
            tutela.setFechaVencimiento(AuConstantes.formato2.parse(tute.getFeachaVencimiento()));
            tutela.setIntegralidad(tute.getIntegralidad().equals(true));
            tutela.setMedidaProvisional(tute.getMedidaProvisional().equalsIgnoreCase("Si"));
            if (tute.getNumeroFallo() != null && !tute.getNumeroFallo().equals("")) {
                tutela.setNumeroFallo(tute.getNumeroFallo());
            }
            tutela.setRadicadoJuzgado(tute.getNumeroRadicadoJuzgado());
            tutela.setFase(tute.getTipoFase());
            getObjeto().getAuAnexo3TutelasList().add(tutela);
            if (getAccion() == Url.ACCION_ADICIONAL_2) {//agregar tutela en auditar
                try {
                    getAuSolicitudServicio().guardarTutelas(this);
                } catch (Exception ex) {
                    addError("Error al asignar tutela.");
                    Logger.getLogger(AuSolicitudBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                addMensaje("Tutela asignada con exito.");
                PrimeFaces.current().ajax().update("frmAuditarSolicitud:pTutelaAuditar");
            } else {
                PrimeFaces.current().ajax().update("frmCrear:pTutelaCrear");
                PrimeFaces.current().ajax().update("frmEditar:pTutelaEditar");
                PrimeFaces.current().ajax().update("frmGestionarDevoluciones:pTutelaDevoluciones");
            }
            PrimeFaces.current().executeScript("PF('dialogoSeleccionarTutela').hide()");
        }
        generarMensajes();
    }

    public void verTutela(TuTutelaRespuesta tutela) {
        setRespuestaTutela(tutela);
        PrimeFaces.current().ajax().update("frmTutelaServicio");
        PrimeFaces.current().executeScript("PF('dialogoTutelaServicio').show()");
    }

    public void ventanaTutelas() {
        try {
            getAuSolicitudServicio().buscarTutelasAfiliado(this);
            PrimeFaces.current().executeScript("PF('dialogoSeleccionarTutela').show()");
            PrimeFaces.current().ajax().update("frmSeleccionarTutela:tablaTutelas");
        } catch (Exception e) {
            addError("Error al consultar tutelas.");
            generarMensajes();
        }

    }

    public void borrarTutelaTemp(int numero) {
        List<AuAnexo3Tutela> listaNueva
                = getObjeto().getAuAnexo3TutelasList().stream()
                        .filter(t -> t.getNumeroTutela() != numero)
                        .collect(Collectors.toList());
        getObjeto().setAuAnexo3TutelasList(listaNueva);
        if (getAccion() == Url.ACCION_EDITAR) {
            PrimeFaces.current().ajax().update("frmEditar:pTutelaEditar");
        } else {
            PrimeFaces.current().ajax().update("frmCrear:pTutelaCrear");
        }
    }

    public void borrarTutelaBD(int id) {
        List<AuAnexo3Tutela> listaNueva
                = getObjeto().getAuAnexo3TutelasList().stream()
                        .filter(t -> t.getId() != id)
                        .collect(Collectors.toList());
        getObjeto().setAuAnexo3TutelasList(listaNueva);

        getAuSolicitudServicio().borrarTutela(id, this);
        PrimeFaces.current().ajax().update("frmEditar:pTutelaEditar");
        generarMensajes();
    }

    public String diagnosticoPrincipalTutela(TuTutelaRespuesta tutela) {
        String tuDiagnostico = "";
        if (!tutela.getDiagnosticos().isEmpty()) {
            tuDiagnostico = tutela.getDiagnosticos().stream()
                    .filter(dia -> dia.isEsPrincipal())
                    .findFirst().orElse(new TuDiagnostico())
                    .getMaDiagnosticosValor();
        }
        return tuDiagnostico;
    }

    public Maestro obtenerMaestroCausaAnulacion(int id) {
        try {
            return hashAnulaciones.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroTipoDocProfesional(int id) {
        try {
            return hashTipoDocumentoProfesional.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void filtrarTodo() {
        if (this.getParamConsulta().getParametroConsulta9() == null) {
            this.getParamConsulta().setParametroConsulta9(this.getConexion().getUsuario().getId());
            addMensaje("Se activo el filtro por responsable");
        } else {
            this.getParamConsulta().setParametroConsulta9(null);
            addMensaje("Se desactivo el filtro por responsable");
        }
        generarMensajes();
        PrimeFaces.current().ajax().update("frmSolicitudes");
    }

    public boolean validarCotizacion() {
        boolean validar = true;
        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
        if (getObjeto().getObjetoTecnologia() != null) {
            if ((AuAnexo3Item.ESTADO_CON_COTIZACION == getObjeto().getObjetoTecnologia().getEstado())
                    || (AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO == getObjeto().getObjetoTecnologia().getEstado())) {
                validar = false;
            }
        }
        return validar;
    }

    public boolean validarDirecionado(int estado) {
        boolean validar = false;
        if (AuAnexo3Item.ESTADO_DIRECCIONADO == estado
                || AuAnexo3Item.ESTADO_DIRECCIONADO_AUTOMATICO == estado) {
            validar = true;
        }
        return validar;
    }

    public boolean validarCotizacionAprobarTodo() {
        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
        boolean validar = getSelectedTecnologia().stream()
                .anyMatch(item -> ((item.getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION)
                || (item.getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO)));

        return validar;
    }

    public void descargarAdjunto(AuSolicitudAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public void descargarAdjunto(TuAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getNombreArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public void descargarAdjuntoAnticipo(AntAnticipoAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public Date getFechaActual() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }

    public int obtenerIntBigDecimal(BigDecimal decimal) {
        return decimal.intValue();
    }

    public void obtenerHistoricoTipoServicio() {
        if (getObjeto().getMaeTipoServicioId() != 0) {
            Maestro tipoServ = obtenerMaestroServicioSolicitado(getObjeto().getMaeTipoServicioId());
            if (tipoServ != null) {
                getObjeto().setMaeTipoServicioId(tipoServ.getId());
                getObjeto().setMaeTipoServicioCodigo(tipoServ.getValor());
                getObjeto().setMaeTipoServicioValor(tipoServ.getNombre());
            }
        }
    }

    public void cambiarEstadoACotizacion() {
        if (getObjeto().getObjetoTecnologia().getId() != null) {
            getAuSolicitudServicio().cambiarEstadoItemACotizacion(this);
            PrimeFaces.current().ajax().update("frmAuditarSolicitud:tablaTecnologiaAuditar");
            PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraAuditar').hide()");
            PrimeFaces.current().executeScript("PF('dialogoAprobar').hide()");
            generarMensajes();
        }
    }

    public void obtenerHistorialAmbito() {
        if (getObjeto().getMaeAmbitoAtencionId() > 0) {
            Maestro ambito = obtenerMaestroAmbito(getObjeto().getMaeAmbitoAtencionId());
            if (ambito != null) {
                getObjeto().setMaeAmbitoAtencionId(ambito.getId());
                getObjeto().setMaeAmbitoAtencionCodigo(ambito.getValor());
                getObjeto().setMaeAmbitoAtencionValor(ambito.getNombre());
            }
        }
    }

    public void obtenerHistorialProgramaEspecial() {
        if (getObjeto().getPeProgramaEspecialId() != null) {
            PePrograma programa = obtenerMaestroProgramaEspecial(getObjeto().getPeProgramaEspecialId());
            if (programa != null) {
                getObjeto().setPeProgramaId(programa);
                getObjeto().setPeProgramaEspecialId(programa.getId());
                getObjeto().setPeProgramaEspecialCodigo(programa.getCodigoPrograma());
                getObjeto().setPeProgramaEspecialValor(programa.getDescripcionPrograma());
            }
        } else {
            getObjeto().setPeProgramaId(null);
            getObjeto().setPeProgramaEspecialId(null);
            getObjeto().setPeProgramaEspecialCodigo(null);
            getObjeto().setPeProgramaEspecialValor(null);
        }
    }

    public PePrograma obtenerMaestroProgramaEspecial(int id) {
        try {
            return hashProgramaEspecial.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public String obtenerPrioridadTexto() {
        if (getObjeto().getPrioridad()) {
            return "Prioritario";
        } else {
            return "No Prioritario";
        }
    }

    public String obtenerColorSolicitud(Date fecha, boolean prioridad, int estado) {
        String color = "green";
        if (estado != AuConstantes.ESTADO_SOLICITUD_ANULADO && estado != AuConstantes.ESTADO_SOLICITUD_GESTIONADO) {
            Date fechaActual = new Date();
            int dias = 5;
            if (prioridad) {
                dias = 2;
            }
            boolean habil = false;
            while (!habil) {
                DiaHabil diaHabil = getAuSolicitudServicio().validarFechaHabil(fechaActual);
                if (diaHabil == null) {
                    habil = true;
                } else {
                    if (diaHabil.isHabil()) {
                        habil = true;
                    } else {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaActual);
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                        fechaActual = calendar.getTime();
                    }
                }
            }
            int milisecondsByDay = 86400000;
            int diasFecha = (int) ((fechaActual.getTime() - fecha.getTime()) / milisecondsByDay);
            if (diasFecha > dias) {
                color = "red";
            }
            if (diasFecha == dias) {
                color = "yellow";
            }
        } else {
            color = "white";
        }
        return color;
    }

    public String obtenerColorItem(Date fecha, int estado) {
        String color = "green";
        if (estado != AuAnexo3Item.ESTADO_ANULADO
                && estado != AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA
                && estado != AuAnexo3Item.ESTADO_APROBADO_AUDITORIA
                && estado != AuAnexo3Item.ESTADO_APROBADO_AUTOMATICO
                && estado != AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA) {
            Date fechaActual = new Date();
            int dias = 5;
            if (getObjeto().getPrioridad()) {
                dias = 2;
            }
            boolean habil = false;
            while (!habil) {
                DiaHabil diaHabil = getAuSolicitudServicio().validarFechaHabil(fechaActual);
                if (diaHabil == null) {
                    habil = true;
                } else {
                    if (diaHabil.isHabil()) {
                        habil = true;
                    } else {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaActual);
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                        fechaActual = calendar.getTime();
                    }
                }
            }
            int milisecondsByDay = 86400000;
            int diasFecha = (int) ((fechaActual.getTime() - fecha.getTime()) / milisecondsByDay);
            if (diasFecha > dias) {
                color = "red";
            }
            if (diasFecha == dias) {
                color = "yellow";
            }
        } else {
            color = "white";
        }
        return color;
    }

    public void verTutela(AuAnexo3Tutela tutela) {
        getObjeto().setObjetoTutela(tutela);
        PrimeFaces.current().ajax().update("frmVerTutela");
        PrimeFaces.current().executeScript("PF('dialogoVerTutela').show()");
    }

    public String obtenerTextoAnulado() {
        try {
            return getObjeto().getEstadoStr() + " " + getObjeto().getMaeEstadoMotivoValor();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerTextoEstadoMotivo(AuAnexo3Item item) {
        if (item.getMaeEstadoMotivoItemValor() != null) {
            if (validarEstadoItemMotivo(item.getEstado())) {
                return item.getMaeEstadoMotivoItemValor();
            } else {
                return item.getEstadoStr();
            }
        } else {
            return item.getEstadoStr();
        }
    }

    public void mostrarMensaje(AuAnexo3Item item) {
        if (item.getEstadoJustificacion() != null) {
            setTituloMensaje("Información Rechazo");
            setSubtituloMensaje("Justificación");
            setMensaje(item.getEstadoJustificacion());
            PrimeFaces.current().ajax().update("frmMensaje");
            PrimeFaces.current().executeScript("PF('dialogoMensaje').show()");
        }
    }

    public boolean validarEditar(AuAnexo3 objeto) {
        boolean validar = false;
        if (objeto.getEstado() == AuAnexo3.ESTADO_PENDIENTE) {
            validar = true;
            return validar;
        }

//        int cantidad = (int) objeto.getAuAnexo3ItemsList().stream()
//                .filter(item -> item.getEstado() == AuAnexo3Item.ESTADO_RECHAZO_COTIZACION
//                || item.getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA)
//                .count();
//
//        if (cantidad == objeto.getAuAnexo3ItemsList().size()) {
//            validar = true;
//        }
        return validar;
    }

    public boolean validarModificar(AuAnexo3 objeto) {
        boolean validar = false;
        if (objeto.getEstado() != AuAnexo3.ESTADO_PENDIENTE) {
            validar = true;
            return validar;
        }

        for (AuAnexo3Item itemPagina : getObjeto().getAuAnexo3ItemsList()) {
            for (AuAnexo3Item itemDB : objeto.getAuAnexo3ItemsList()) {
                if (Objects.equals(itemPagina.getId(), itemDB.getId())) {
                    if (itemPagina.getEstado() != itemDB.getEstado()) {
                        validar = true;
                        break;
                    }
                }
            }
        }
        return validar;
    }

    public void agregarHistoricoDiagnosticoTecnologia() {
        AuAnexo3Diagnostico diagno = obtenerMaDiagnostico(getObjeto().getObjetoTecnologia().getMaDiagnosticoId());
        if (diagno != null) {
            getObjeto().getObjetoTecnologia().setMaDiagnosticoId(diagno.getMaDiagnosticosId());
            getObjeto().getObjetoTecnologia().setMaDiagnosticoCodigo(diagno.getMaDiagnosticosCodigo());
            getObjeto().getObjetoTecnologia().setMaDiagnosticoValor(diagno.getMaDiagnosticosValor());
        }
    }

    public void agregarHistoricoServicioTecnologia() {
        MaTecnologiaServicioHabilitacion servicio = obtenerMaServicioTecnolia(getObjeto().getObjetoTecnologia().getMaServicioSolicitadoId());
        if (servicio != null) {
            getObjeto().getObjetoTecnologia().setMaServicioSolicitadoId(servicio.getMaServicioHabilitacion().getId());
            getObjeto().getObjetoTecnologia().setMaServicioSolicitadoCodigo("" + servicio.getMaServicioHabilitacion().getCodigo());
            getObjeto().getObjetoTecnologia().setMaServicioSolicitadoValor(servicio.getMaServicioHabilitacion().getNombre());
        }
    }

    public Date obtenerFechaActualAnioAnterior() {
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    public String obtenerTutelaActual() {
        if (!getObjeto().getAuAnexo3TutelasList().isEmpty()) {
            String numeroTutela = "";
            int cont = 1;
            for (AuAnexo3Tutela tutela : getObjeto().getAuAnexo3TutelasList()) {
                numeroTutela += tutela.getNumeroTutela()
                        + (getObjeto().getAuAnexo3TutelasList().size() == cont ? "" : "-");
                cont++;
            }
            return numeroTutela;
        } else {
            return "Sin Tutela";
        }
    }

    public void verPosfechados(AuAnexo3Item item) {
        getObjeto().setListaPosfechados(new ArrayList());
        getObjeto().setObjetoTecnologia(item);
        getAuSolicitudServicio().consultarPosfechados(this);
        if (!getObjeto().getListaPosfechados().isEmpty()) {
            PrimeFaces.current().executeScript("PF('dialogoVerPosfechados').show()");
            PrimeFaces.current().ajax().update("frmVerPosfechados");
        }
        generarMensajes();
    }

    public String obtenerFechaAutorizacion() {
        AuAnexo3Item item = getObjeto().getObjetoTecnologia();
        Date fecha = new Date();
        if (item.isPosfechado()) {
            if (item.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA) {
                fecha = item.getFechaPosfechado();
            }
        }
        if (fecha == null) {
            fecha = new Date();
        }
        return convertirFecha(fecha);
    }

    public void ventanaDevoluciones(int id) {
        getObjeto().setId(id);
        getObjeto().setEsEditar(true);
        getObjeto().setListaDevoluciones(new ArrayList());
        getObjeto().setObjetoTecnologia(new AuAnexo3Item());
        this.setAccion(Url.ACCION_ADICIONAL_5);
        this.setDoAccion(Url.ACCION_VER);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void guardarDevoluciones(AuAnexo3Item item) {
        getObjeto().setEsEditar(false);
//        List<AuAnexo3Item> nueva = new ArrayList();
//        getObjeto().getListaDevoluciones().stream().filter(devoluciones -> (!devoluciones.getId().equals(item.getId()))).forEachOrdered(devoluciones -> {
//            nueva.add(devoluciones);
//        });
//        getObjeto().setListaDevoluciones(nueva);
        getObjeto().setObjetoTecnologia(item);
        getObjeto().setObservacionAuditar(null);
        PrimeFaces.current().executeScript("PF('dialogoMensajeDevolucion').show()");
        PrimeFaces.current().ajax().update("frmMensajeDevolucion");
    }

    public void gestionarDevolucion() {
        this.setAccion(ACCION_ADICIONAL_5);
        this.setDoAccion(ACCION_GUARDAR);
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void verBitacoras(AuAnexo3Item item) {
        getObjeto().setObjetoTecnologia(item);
//        getObjeto().setListaBitacoras(new ArrayList());
//        getAuSolicitudServicio().verBitacoras(this);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacorass').show()");
        PrimeFaces.current().ajax().update("frmVerBitacoras");
        generarMensajes();
    }

    public void verBitacorasAnexo3(int id) {
        getObjeto().setId(id);
        getAuSolicitudServicio().verBitacorasAnexo3(this);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacorasSolicitud').show()");
        PrimeFaces.current().ajax().update("frmVerBitacorasAnulacion");
        generarMensajes();
    }

    public void ventanaBitacoraAuditar() {
        getObjeto().setObservacionAuditar("");
        PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraAuditar').show()");
        PrimeFaces.current().ajax().update("frmMensajeBitacoraAuditar");
    }

    public void ventanaCrearBitacora() {
        getObjeto().setObservacionAuditar("");
        PrimeFaces.current().executeScript("PF('dialogoComentarioBitacora').show()");
        PrimeFaces.current().ajax().update("frmMensajeBitacoraComentario");
    }

    public void guardarBitacora() {
        getAuSolicitudServicio().guardarBitacora(this);
        PrimeFaces.current().executeScript("PF('dialogoComentarioBitacora').hide()");
        PrimeFaces.current().ajax().update("frmVerBitacoras");
        generarMensajes();
    }

    public boolean validarGuardarBitacora() {
        if (super.getAccion() == ACCION_ADICIONAL_2) {//auditar
            //2024-01-25 jyperez se eliminan las validaciones de estados de anexo3 y anexo3item
            /*if (getObjeto().getEstado() == AuAnexo3.ESTADO_PENDIENTE || getObjeto().getEstado() == AuAnexo3.ESTADO_EN_GESTION) {
                //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
                if (getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_PENDIENTE_AUDITORIA
                        || getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_CON_COTIZACION
                        || getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_SIN_COTIZACION
                        || getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_RECHAZO_COTIZACION
                        || getObjeto().getObjetoTecnologia().getEstado() == AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO) {*/
            return true;
            //}
            //}
        }
        return false;
    }

    public void mostrarMensajes() {
        if (getObjeto().getObjetoCotizacion() != null && getObjeto().getObjetoCotizacion().getId() == null) {
            this.addMensaje("No se encontro una cotización activa, puede volver a realizar la cotización");
        }
        generarMensajes();
    }

    /* public void verAutorizacionAnerior() {
        setListaHistoricoAnexo4(new ArrayList());
        getAuSolicitudServicio().verAutorizacionAnterior(this, getObjeto().getObjetoTecnologia().getMaTecnologiaId(), getObjeto().getObjetoTecnologia().getTipoTecnologia());
        if (getListaHistoricoAnexo4().isEmpty()) {
            addError("Los items no tienen historico");
        }
        if (getErrores().isEmpty()) {
            PrimeFaces.current().executeScript("PF('dialogoListarUltimaAutorizacion').show()");
            PrimeFaces.current().ajax().update("frmListarUltimaAutorizacion");
        } else {
            generarMensajes();
        }
    }*/
    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("auSolicitudBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("auSolicitudBean");
    }

    public boolean evaluarUsuarioItems() {
        //aprobar todo
        if (isAccionAdicional6()) {
            return true;
        }
        //aprobar tecnologia
        if (isAccionAdicional8()) {
            return true;
        }
        Integer idUsuario = getConexion().getUsuario().getId();
        for (AuAnexo3Item item : getObjeto().getAuAnexo3ItemsList()) {
            if (item.getGnUsuarioId() != null) {
                if (!item.getGnUsuarioId().getId().equals(idUsuario)) {
                    return false;
                }
            } else {
                if (item.getAuGrupoId() != null) {
                    for (Integer idGrupo : getUsuarioGruposAsigandos()) {
                        if (!idGrupo.equals(item.getAuGrupoId().getId())) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean evaluarUsuarioItem(Usuario idUsuarioItem, AuGrupo idGrupoItem) {
        //aprobar todo
        if (isAccionAdicional6()) {
            return true;
        }
        //aprobar tecnologia
        if (isAccionAdicional8()) {
            return true;
        }

        if (idGrupoItem == null) {
            return false;
        }
        if (idUsuarioItem == null) {
            if (idGrupoItem != null) {
                for (Integer id : getUsuarioGruposAsigandos()) {
                    if (id.equals(idGrupoItem.getId())) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        Integer idUsuario = getConexion().getUsuario().getId();
        if (idUsuarioItem != null) {
            return idUsuarioItem.getId().equals(idUsuario);
        } else {
            return false;
        }
    }

    public void continuarTecnologia() {
        PrimeFaces.current().executeScript("PF('dialogoConfirmacionPosfechada').hide()");
        switch (getObjeto().getObjetoTecnologia().getTipoTecnologia()) {
            case AuAnexo3Item.TIPO_TECNOLOGIA_CUP:
                PrimeFaces.current().ajax().update("frmGestionarProcedimiento");
                PrimeFaces.current().executeScript("PF('dialogoGestionarProcedimiento').show()");
                break;
            case AuAnexo3Item.TIPO_TECNOLOGIA_CUM:
                PrimeFaces.current().ajax().update("frmGestionarMedicamento");
                PrimeFaces.current().executeScript("PF('dialogoGestionarMedicamento').show()");
                break;
            //2023-10-01 jyperez INC 1333: Crear ítem que ya está autorizado posfechado
            case AuAnexo3Item.TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO:
                PrimeFaces.current().ajax().update("frmGestionarMedicamento");
                PrimeFaces.current().executeScript("PF('dialogoGestionarMedicamento').show()");
                break;
        }
    }

    public void verPrograma(int id) {
        if (id > 0) {
            setObjetoSugerido(new PeAfiliadoSugerido());
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_VER);
        }
        getAuSolicitudServicio().Accion(this);
        procesoFinal();
    }

    public void borrarSugerido(int _id) {
        setObjetoSugerido(new PeAfiliadoSugerido());
        getObjetoSugerido().setId(_id);
        getAuSolicitudServicio().validarSugeridoParaBorrar(this);
        /*AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }*/
        //super.setAccion(Url.ACCION_ADICIONAL_5);
        //super.setDoAccion(ACCION_BORRAR_SUGERIDO);
        procesoFinal();
    }

    public void verRescates(int id) {
        getObjeto().setId(id);
        getObjeto().setAuAnexo2RescateList(new ArrayList());
        getAuSolicitudServicio().verRescatesAnexo3(this);
        PrimeFaces.current().executeScript("PF('dlgRescates').show()");
        PrimeFaces.current().ajax().update("frmRescates");
        generarMensajes();
    }

    public void verRescate(int id) {
        objetoRescate = new AuAnexo2Rescate();
        getObjetoRescate().setId(id);
        getAuSolicitudServicio().verRescate(this);
        PrimeFaces.current().executeScript("PF('dialogoVerRescate').show()");
        PrimeFaces.current().ajax().update("frmVerRescate");
        generarMensajes();
    }

    public void actualizarProcesoActual() {
        getAuSolicitudServicio().actualizarProcesoActual(this);
        generarMensajes();
    }

    public void cerrarModalAuditar() {
        actualizarProcesoActual();
        //actualiza lazy listar anexo3 cuando click X
        PrimeFaces.current().ajax().update("frmSolicitudes");
    }

    public void consultarAdjuntos(int id) {
        getObjeto().setObjetoTecnologia(
                getObjeto().getAuAnexo3ItemsList()
                        .stream()
                        .filter(item -> item.getId().equals(id))
                        .findFirst().orElse(null)
        );
        this.listaAdjuntosCotizacion = new ArrayList();
        this.antAnticipoAdjuntosList = new ArrayList<>();
        getAuSolicitudServicio().consultarAdjuntosCotizacion(this);

        PrimeFaces.current().executeScript("PF('dialogoVerAdjuntosItem').show()");
        PrimeFaces.current().ajax().update("frmVerAdjuntosItem");
        generarMensajes();
    }

    public boolean validarConsultarAdjuntos(AuAnexo3Item item) {
        boolean flag = false;
        //2023-09-28 jyperez se debe realizar para el estado CON_PAGO_ANTICIPADO las mismas opciones de CON_COTIZACION
        if ((AuAnexo3Item.ESTADO_CON_COTIZACION == item.getEstado())
                || (AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO == item.getEstado())) {
            flag = true;
        }
        if (AuAnexo3Item.ESTADO_APROBADO_AUDITORIA == item.getEstado()) {
            int tam = item.getAuItemBitacorasList().size();
            if (tam > 1) {
                AuItemBitacora bitacora = item.getAuItemBitacorasList().get(tam - 2);
                if (bitacora.getDescripcion().equals("Con cotización")) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public void ventanaPosFechadoItem() {
        getObjeto().setListaPosfechadosEditar(new ArrayList());
      
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(Url.ACCION_CREAR);
        getAuSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmGestionarPosFechado");
            PrimeFaces.current().ajax().update("frmGestionarPosFechado");
        }
        procesoFinal();
    }
    
    public void guardarPosfechado() {
     
        int cantidadTotal = getObjeto().getObjetoTecnologia().getListaRangos().size() - 1;
        for (int i = 0; i < getObjeto().getObjetoTecnologia().getListaRangos().size(); i++) {
            if (i == 0) {
                if (i == cantidadTotal) {
                    Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                    int anios = obtenerAnios(getObjeto().getFechaSolicitud(), fechaLiberacionActual);
                    if (anios >= 1) {
                        addError("La fecha de liberación supera un año aparatir de la fecha orden medica. ");
                        break;
                    }
                    break;
                } else {
                    Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                    if (getObjeto().getFechaSolicitud() == null) {
                        addError("La fecha de la orden medica es obligatoria.");
                        break;
                    }
                    int anios = obtenerAnios(getObjeto().getFechaSolicitud(), fechaLiberacionActual);
                    if (anios >= 1) {
                        addError("La fecha de liberación supera un año aparatir de la fecha orden medica. ");
                        break;
                    }
                    Date fechaLiberacionDespues = getObjeto().getObjetoTecnologia().getListaRangos().get(i + 1).getFechaLiberacion();
                    if (fechaLiberacionActual.after(fechaLiberacionDespues)) {
                        addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionActual) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                        break;
                    }
                }
            }
            if (i == cantidadTotal) {
                Date fechaLiberacionAnterior = getObjeto().getObjetoTecnologia().getListaRangos().get(i - 1).getFechaLiberacion();
                Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                if (fechaLiberacionAnterior.after(fechaLiberacionActual)) {
                    addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionActual));
                }
                break;
            }

            if (i != 0) {
                Date fechaLiberacionAnterior = getObjeto().getObjetoTecnologia().getListaRangos().get(i - 1).getFechaLiberacion();
                Date fechaLiberacionActual = getObjeto().getObjetoTecnologia().getListaRangos().get(i).getFechaLiberacion();
                Date fechaLiberacionDespues = getObjeto().getObjetoTecnologia().getListaRangos().get(i + 1).getFechaLiberacion();
                if (fechaLiberacionAnterior.before(fechaLiberacionActual) && fechaLiberacionDespues.after(fechaLiberacionActual)) {
                    continue;
                } else {
                    addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                    break;
                }
            }
        }
        if(!super.isError()){
            super.setAccion(Url.ACCION_ADICIONAL_9);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getAuSolicitudServicio().Accion(this);
        }
        
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmGestionarPosFechado");
            PrimeFaces.current().ajax().update("frmGestionarPosFechado");
            PrimeFaces.current().ajax().update("frmAprobar:fechaOrdenVer");
            PrimeFaces.current().ajax().update("frmAuditarSolicitud:tablaTecnologiaAuditar");
        }
        procesoFinal();
    }
    
    public void modificarPosFechadoItem() {
        int cantidadTotal = getObjeto().getListaPosfechadosEditar().size() - 1;
        for (int i = 0; i < getObjeto().getListaPosfechadosEditar().size(); i++) {
            if (i == 0) {
                if (i == cantidadTotal) {
                    Date fechaLiberacionActual = getObjeto().getListaPosfechadosEditar().get(i).getFechaPosfechado();
                    int anios = obtenerAnios(getObjeto().getFechaSolicitud(), fechaLiberacionActual);
                    if (anios >= 1) {
                        addError("La fecha de liberación supera un año aparatir de la fecha orden medica. ");
                        break;
                    }
                    break;
                } else {
                    Date fechaLiberacionActual = getObjeto().getListaPosfechadosEditar().get(i).getFechaPosfechado();
                    if (getObjeto().getFechaSolicitud() == null) {
                        addError("La fecha de la orden medica es obligatoria.");
                        break;
                    }
                    int anios = obtenerAnios(getObjeto().getFechaSolicitud(), fechaLiberacionActual);
                    if (anios >= 1) {
                        addError("La fecha de liberación supera un año aparatir de la fecha orden medica. ");
                        break;
                    }
                    Date fechaLiberacionDespues = getObjeto().getListaPosfechadosEditar().get(i + 1).getFechaPosfechado();
                    if (fechaLiberacionActual.after(fechaLiberacionDespues)) {
                        addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionActual) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                        break;
                    }
                }
            }
            if (i == cantidadTotal) {
                Date fechaLiberacionAnterior = getObjeto().getListaPosfechadosEditar().get(i - 1).getFechaPosfechado();
                Date fechaLiberacionActual = getObjeto().getListaPosfechadosEditar().get(i).getFechaPosfechado();
                if (fechaLiberacionAnterior.after(fechaLiberacionActual)) {
                    addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionActual));
                }
                break;
            }

            if (i != 0) {
                Date fechaLiberacionAnterior = getObjeto().getListaPosfechadosEditar().get(i - 1).getFechaPosfechado();
                Date fechaLiberacionActual = getObjeto().getListaPosfechadosEditar().get(i).getFechaPosfechado();
                Date fechaLiberacionDespues = getObjeto().getListaPosfechadosEditar().get(i + 1).getFechaPosfechado();
                if (fechaLiberacionAnterior.before(fechaLiberacionActual) && fechaLiberacionDespues.after(fechaLiberacionActual)) {
                    continue;
                } else {
                    addError("Por favor revisar las fechas: " + Util.fechaDateAString(fechaLiberacionAnterior) + " o " + Util.fechaDateAString(fechaLiberacionDespues));
                    break;
                }
            }
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_10);
            getAuSolicitudServicio().Accion(this);
        }
        
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestionarPosFechado:pListaPosfechadosE");
        }
        procesoFinal();
    }

    public void ventanaDireccionaItem() {
        boolean aplica = getAuSolicitudServicio().validarItemDireccionado(this);
        if (aplica) {
            getObjeto().setObservacionAuditar("");
            PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraDireccionar').show()");
            PrimeFaces.current().ajax().update("frmMensajeBitacoraDireccionar");
        } else {
            addMensaje("El item no aplica para ser direccionado.");
        }
        generarMensajes();
    }

    public void cambiarEstadoADireccionado() {
        if (getObjeto().getObjetoTecnologia().getId() != null) {
            getAuSolicitudServicio().cambiarEstadoItemADireccionado(this);
            PrimeFaces.current().ajax().update("frmAuditarSolicitud");
//            PrimeFaces.current().ajax().update("frmSolicitudes");
            PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraDireccionar').hide()");
            PrimeFaces.current().executeScript("PF('dialogoAprobar').hide()");
            generarMensajes();
        }
    }

    public void ventanaSeguimientoItem() {
        getAuSolicitudServicio().validarItemSeguimiento(this);
        if (!isError()) {
            getObjeto().setObservacionAuditar("");
            PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraSeguimiento').show()");
            PrimeFaces.current().ajax().update("frmMensajeBitacoraSeguimiento");
        }
        generarMensajes();
    }

    public void cambiarEstadoASeguimiento() {
        if (getObjeto().getObjetoTecnologia().getId() != null) {
            getAuSolicitudServicio().cambiarEstadoItemASeguimiento(this);
            PrimeFaces.current().ajax().update("frmAuditarSolicitud");
//            PrimeFaces.current().ajax().update("frmSolicitudes");
            PrimeFaces.current().executeScript("PF('dialogoMensajeBitacoraSeguimiento').hide()");
            PrimeFaces.current().executeScript("PF('dialogoAprobar').hide()");
            generarMensajes();
        }
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public boolean esAdministradora() {
        return super.getConexion().getEmpresa().isAdministradora();
    }

    public String estiloAdminnistradora() {
        if (esAdministradora()) {
            return "display:block;";
        } else {
            return "display:none;";
        }
    }

    public void mostrarMensaje(String desc) {
        setObservacionGenerica(desc);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public boolean validarAnulacion(String valor) {
        try {
            int intValor = Integer.parseInt(valor);
            return intValor > 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void agregarSugerido() {
        try {
            boolean validate = false;
            PeAfiliadoSugerido obj = getObjetoSugerido();
            PePrograma tipo = getHashPePrograma().get(obj.getPePrograma().getId());
            if (tipo != null) {
                obj.getPePrograma().setCodigoPrograma(tipo.getCodigoPrograma());
                obj.getPePrograma().setDescripcionPrograma(tipo.getDescripcionPrograma());
            }
            if (obj.getListaAdjunto().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "MENSAJE", "Debe adjuntar soporte"));
                validate = true;
            }
            //Agregar dato a la lista
            if (getListaSegueridoMemoria() == null) {
                setListaSegueridoMemoria(new ArrayList<>());
            }
            if (!getListaSegueridoMemoria().isEmpty()) {
                for (PeAfiliadoSugerido sugerido : getListaSegueridoMemoria()) {
                    if (sugerido.getPePrograma().getId().equals(obj.getPePrograma().getId())) {
                        addError("El programa especial  ya fue agregado");
                        break;
                    }
                }
            }

            if (!super.isError() && !validate) {
                obj.setPosicion(getListaSegueridoMemoria().size());
                getListaSegueridoMemoria().add(obj);
                //refrescarInoportunidades();
                //PrimeFaces.current().ajax().update("frmEditar:tablaInoportunidadEditar");
                PrimeFaces.current().ajax().update("frmSugerirProgramaCrear");
                PrimeFaces.current().ajax().update("frmSugerirProgramaCrear:tablaSugerirPrograma");
                PrimeFaces.current().executeScript("PF('dialogoAgregarSugerirPrograma').hide()");
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void handleFileUploadSugerido(FileUploadEvent event) throws IOException {

        try {
            if (getObjetoSugerido().getMaeTipoAdjunto() == null) {
                addError("Tipo Archivo: se necesita un valor.");
                generarMensajes();
                return;
            }
            //boorar archivo si el tipo de archivo es el mismo
            /*PeSugeridoAdjunto borrarObj = new PeSugeridoAdjunto();
            for (PeSugeridoAdjunto peAdjunto : this.getObjetoSugerido().getListaAdjunto()) {
                if (peAdjunto.getMaeTipoArchivoId() == getObjetoSugerido().getMaeTipoAdjunto()) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            this.getObjetoSugerido().getListaAdjunto().remove(borrarObj);*/
            Maestro maestro = getHashTiposSugeridoAdjuntos().get(getObjetoSugerido().getMaeTipoAdjunto());
            if (maestro != null) {
                getObjetoSugerido().setMaeTipoAdjuntoValor(maestro.getNombre());
                String file = FilenameUtils.getName(event.getFile().getFileName());
                int i = file.lastIndexOf(".");
                String ext = file.substring(i, file.length());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String nombre = getObjeto().getAsegAfiliadoId().getMaeTipoDocumentoCodigo() + getObjeto().getAsegAfiliadoId().getNumeroDocumento() + maestro.getNombre().replace(" ", "_") + "_";
                String filename = nombre + sdf.format(new Date()) + ext;
                UploadedFile archivo = event.getFile();
                byte[] arreglo = archivo.getInputStream().readAllBytes();
                InputStream input = new ByteArrayInputStream(arreglo);

                String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_SUGERIDOS_ADJUNTOS);
                if (ruta == null) {
                    throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
                }
                OutputStream output = new FileOutputStream(new File(ruta, filename));

                IOUtils.copy(input, output);
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);

                //agraegar el nuevo anexo a la lista de anexos de tutela
                PeSugeridoAdjunto adjunto = new PeSugeridoAdjunto();
                adjunto.setId(null);
                adjunto.setNombre(file);
                adjunto.setArchivo(filename);
                adjunto.setRuta(ruta);
                adjunto.setMaeTipoArchivoId(getObjetoSugerido().getMaeTipoAdjunto());
                adjunto.setMaeTipoArchivoCodigo(maestro.getValor());
                adjunto.setMaeTipoArchivoValor(maestro.getNombre());
                this.auditoriaGuardar(adjunto);
                adjunto.setPosicion(this.getObjetoSugerido().getListaAdjunto().size());

                this.getObjetoSugerido().getListaAdjunto().add(adjunto);
                getObjetoSugerido().setMaeTipoAdjunto(null);
                PrimeFaces.current().resetInputs("frmAgregarSugerirPrograma:somTipo");
                PrimeFaces.current().ajax().update("frmAgregarSugerirPrograma:somTipo");
                PrimeFaces.current().resetInputs("frmAgregarSugerirPrograma:tablaSugeridosAnexos");
                PrimeFaces.current().ajax().update("frmAgregarSugerirPrograma:tablaSugeridosAnexos");
            } else {
                this.addError("Hubo un problema al consultar maestro");
                generarMensajes();
            }

        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public void descargarSugeridoAnexo(PeSugeridoAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + File.separator + adjunto.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);;
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {

        }
    }

    public void borrarAdjuntoSugeridoMemoria(PeSugeridoAdjunto sugerido) {
        List<PeSugeridoAdjunto> listaSugeridoMe = new ArrayList<>();
        int posicionEliminar = sugerido.getPosicion();
        this.getObjetoSugerido().getListaAdjunto().stream().filter(contacto -> (contacto.getPosicion() != posicionEliminar)).forEachOrdered(contacto -> {
            listaSugeridoMe.add(contacto);
        });
        this.getObjetoSugerido().setListaAdjunto(listaSugeridoMe);
        addMensaje("Se ha realizado la eliminación del archivo");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmAgregarSugerirPrograma:tablaSugeridosAnexos");
        PrimeFaces.current().ajax().update("frmAdjuntoSugerido:tablaAdjuntoSugerido");
    }

    public void handleFileUploadAdjuntoSegerido(FileUploadEvent event) throws IOException {

        try {
            if (getObjetoSugerido().getMaeTipoAdjunto() == null) {
                addError("Tipo Archivo: se necesita un valor.");
                generarMensajes();
                return;
            }

            Maestro maestro = getHashTiposSugeridoAdjuntos().get(getObjetoSugerido().getMaeTipoAdjunto());
            if (maestro != null) {
                getObjetoSugerido().setMaeTipoAdjuntoValor(maestro.getNombre());
                String file = FilenameUtils.getName(event.getFile().getFileName());
                int i = file.lastIndexOf(".");
                String ext = file.substring(i, file.length());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String nombre = getObjeto().getAsegAfiliadoId().getMaeTipoDocumentoCodigo() + getObjeto().getAsegAfiliadoId().getNumeroDocumento() + maestro.getNombre().replace(" ", "_") + "_";
                String filename = nombre + sdf.format(new Date()) + ext;
                UploadedFile archivo = event.getFile();
                byte[] arreglo = archivo.getInputStream().readAllBytes();
                InputStream input = new ByteArrayInputStream(arreglo);

                String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_SUGERIDOS_ADJUNTOS);
                if (ruta == null) {
                    throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
                }
                OutputStream output = new FileOutputStream(new File(ruta, filename));

                IOUtils.copy(input, output);
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);

                //agraegar el nuevo anexo a la lista de anexos de tutela
                PeSugeridoAdjunto adjunto = new PeSugeridoAdjunto();
                adjunto.setId(null);
                adjunto.setNombre(file);
                adjunto.setArchivo(filename);
                adjunto.setRuta(ruta);
                adjunto.setMaeTipoArchivoId(getObjetoSugerido().getMaeTipoAdjunto());
                adjunto.setMaeTipoArchivoCodigo(maestro.getValor());
                adjunto.setMaeTipoArchivoValor(maestro.getNombre());
                this.auditoriaGuardar(adjunto);
                if (this.getObjetoSugerido().getListaAdjunto() == null) {
                    this.getObjetoSugerido().setListaAdjunto(new ArrayList<>());
                }
                adjunto.setPosicion(this.getObjetoSugerido().getListaAdjunto().size());

                this.getObjetoSugerido().getListaAdjunto().add(adjunto);
                getObjetoSugerido().setMaeTipoAdjunto(null);
                PrimeFaces.current().resetInputs("frmAdjuntoSugerido:somTipo");
                PrimeFaces.current().ajax().update("frmAdjuntoSugerido:somTipo");
                PrimeFaces.current().resetInputs("frmAdjuntoSugerido:tablaAdjuntoSugerido");
                PrimeFaces.current().ajax().update("frmAdjuntoSugerido:tablaAdjuntoSugerido");
            } else {
                this.addError("Hubo un problema al consultar maestro");
                generarMensajes();
            }

        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public static int obtenerAnios(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
                || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH)
                && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }
}
