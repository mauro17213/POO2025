package com.saviasaludeps.savia.web.judicial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAbogado;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAdjunto;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoHistorico;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoPretencion;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTerceroContacto;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoGarantia;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.web.judicial.servicio.GjProcesoServicioIface;
import com.saviasaludeps.savia.web.judicial.servicio.GjProcesoServicioImpl;
//import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.tutela.utilidades.PropTutelasUsuario;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_BORRAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_CREAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_GUARDAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_LISTAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_MODIFICAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_VER;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author bsgomez
 *
 */
@ManagedBean
@ViewScoped
public final class GjProcesoBean extends Url {

    private GjProcesoServicioIface gjProcesoServicio;
    private GjProceso objeto;

    private List<GjProceso> registros;
    private List<GjTercero> registrosTerceros;
    private List<GjAbogado> registrosAbogados;
    private List<GjProcesoAbogado> registrosProcesoAbogados;
    private List<GjProcesoAdjunto> registrosProcesoAdjuntos;
    private List<GjProcesoPretencion> registrosProcesoPretenciones;
    private List<GjProcesoTercero> registrosProcesoTerceros;
    private List<GjProcesoGarantia> registrosProcesoGarantias;
    private List<GjProcesoTercero> auxRegistrosProcesoTerceros;
    private List<GjTerceroContacto> registrosTerceroContactos;
    private List<GjProcesoHistorico> registrosProcesoHistoricos;

    private LazyDataModel<GjProceso> lazyGjProceso;
    private LazyDataModel<GjTercero> lazyGjTercero;
    private LazyDataModel<GjAbogado> lazyGjAbogado;
    private LazyDataModel<GjProcesoHistorico> lazyGjProcesoHistorico;
    private LazyDataModel<GjProcesoAbogado> lazyGjProcesoAbogado;
    private LazyDataModel<GjProcesoTercero> lazyGjProcesoTercero;
    private LazyDataModel<GjProcesoAdjunto> lazyGjProcesoAdjunto;

    private ParamConsulta paramConsultaJuzgado;
    private ParamConsulta paramConsultaTercero;
    private ParamConsulta paramConsultaGarantia;
    private ParamConsulta paramConsultaAbogado;
    private ParamConsulta paramConsultaHistorialProceso;
    private ParamConsulta paramConsultaProcesoAbogado;
    private ParamConsulta paramConsultaProcesoPretencion;
    private ParamConsulta paramConsultaProcesoTercero;
    private ParamConsulta paramConsultaTerceroContacto;
    private ParamConsulta paramConsultaProcesoHistorico;
    private ParamConsulta paramConsultaProcesoAdjunto;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaTipo;
    private HashMap<Integer, Maestro> hashTipo;
    private List<Maestro> listaInstancia;
    private HashMap<Integer, Maestro> hashInstancia;
    private List<Maestro> listaMedicaCautelar;
    private HashMap<Integer, Maestro> hashMedicaCautelar;
    private List<Maestro> listaActuacionTerminacion;
    private HashMap<Integer, Maestro> hashActuacionTerminacion;
    private List<Maestro> listaPretencion;
    private HashMap<Integer, Maestro> hashPretencion;
    private List<Maestro> listaCalidadAcatua;
    private HashMap<Integer, Maestro> hashCalidadAcatua;
    private List<Maestro> listaJurisdiccion;
    private HashMap<Integer, Maestro> hashJurisdiccion;
    private List<Maestro> listaClase;
    private HashMap<Integer, Maestro> hashClase;
    private List<Maestro> listaClaseDescripcion;
    private HashMap<Integer, Maestro> hashClaseDescripcion;
    private List<Maestro> listaTipoContacto;
    private HashMap<Integer, Maestro> hashTipoContacto;

    private List<GjProcesoAbogado> listaGjProcesoAbogados;
    private List<GjTerceroContacto> listaGjTerceroContactos;
    private List<GjTercero> listaGjTerceros;
    private List<GjAbogado> GjAbogados;

    private List<Maestro> listaTiposDocumentoEmpresa;

    private List<TuJuzgado> listaJuzgados;
    private HashMap<Integer, TuJuzgado> hashJuzgados;
    private List<GjTercero> listaTerceroReprecentante;
    private List<GjAbogado> listaAbogados;

    private List<GjProcesoAdjunto> listaProcesoAdjuntosServicio;

    private TuJuzgado objJuzgado;
    private GjTercero objTercero;
    private GjAbogado objAbogado;
    private GjProcesoAbogado objProcesoAbogado;
    private GjProcesoAdjunto objProcesoAdjunto;
    private GjProcesoHistorico objProcesoHistorico;
    private GjProcesoPretencion objProcesoPretencion;
    private GjProcesoTercero objProcesoTercero;

    private GjTerceroContacto GjTerceroContacto;
    private GjProcesoAdjunto borrarAdjunto;

    private int contadorArchivos = 0;
    private int sizeLimitByAnexo;
    private int maxCantAnexos;
    private Date fechaActual;
    private boolean validarvisibletercero = true;
    private boolean validarrequeridotercero = false;
    private boolean visible = false;
    private boolean requerido = false;
    private boolean visibleBotonAgregarTercero = true;
    private boolean visibleCalidadActua = false;

    private boolean disabeBotonAgregarGarantia = true;
    private boolean disablegarantia = false;
//    @Autowired
//    private UbicacionSingle ubicacionSingle;

    public static final char BORRAR_ADJUNTO = 'F';
    public static final char ACCION_INICIO_GESTION = 'o';
    public static final char ACCION_INSERTAR_GESTION = 'r';
    public static final char ACCION_VER1 = 'q';
    public static final char ACCION_VER2 = 's';
    public static final char ACCION_LISTAR1 = 't';
    public static final char ACCION_LISTAR2 = 'u';
    public static final char ACCION_LISTAR3 = 'r';
    public static final char ACCION_VER3 = 'a';

    private boolean radicadoRegistradoEnSistema = false;
    private static final NumberFormat FORMATO_VALORES_COP = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
    private static final Currency MONEDA_COP = Currency.getInstance("COP");
    public static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

    private boolean requerirCampoSentidoSentencia = false;
    private boolean requerirCampoFechaTerminacion = false;
    private boolean requerirCampoValorSentenciaEjecutoria = false;
    private boolean requerirCampoActuacionTerminacion = false;
    private boolean requerirCampoValorAcuerdoT = false;

//Constructor lazzys proceso,tercero,abogado
    public GjProcesoBean() {

        this.objeto = new GjProceso();
        this.objJuzgado = new TuJuzgado();
        setMaxCantAnexos(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.MAXIMO_CANTIDAD_ANEXOS)));
        setSizeLimitByAnexo(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.MAXIMO_TAMANO_ANEXO)));
        Modulo _mod = super.validarModulo(Modulo.ID_GJ_PROCESOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyGjProceso = new LazyDataModel<GjProceso>() {

                private List<GjProceso> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GjProceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());

                    return lista;
                }

                @Override
                public String getRowKey(GjProceso objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GjProceso getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GjProceso objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
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
        getGjProcesoServicio().cargasInicial(this);
        listar();
    }

    public GjProcesoServicioIface getGjProcesoServicio() {
        if (gjProcesoServicio == null) {
            gjProcesoServicio = new GjProcesoServicioImpl();
        }
        return gjProcesoServicio;
    }

    public void setGjProcesoServicio(GjProcesoServicioIface gjProcesoServicio) {
        this.gjProcesoServicio = gjProcesoServicio;
    }

    public GjProceso getObjeto() {
        return objeto;
    }

    public void setObjeto(GjProceso objeto) {
        this.objeto = objeto;
    }

    public List<GjProceso> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GjProceso> registros) {
        this.registros = registros;
    }

    public List<GjTercero> getRegistrosTerceros() {
        return registrosTerceros;
    }

    public void setRegistrosTerceros(List<GjTercero> registrosTerceros) {
        this.registrosTerceros = registrosTerceros;
    }

    public List<GjAbogado> getRegistrosAbogados() {
        return registrosAbogados;
    }

    public void setRegistrosAbogados(List<GjAbogado> registrosAbogados) {
        this.registrosAbogados = registrosAbogados;
    }

    public List<GjProcesoAbogado> getRegistrosProcesoAbogados() {
        return registrosProcesoAbogados;
    }

    public void setRegistrosProcesoAbogados(List<GjProcesoAbogado> registrosProcesoAbogados) {
        this.registrosProcesoAbogados = registrosProcesoAbogados;
    }

    public List<GjProcesoAdjunto> getRegistrosProcesoAdjuntos() {
        return registrosProcesoAdjuntos;
    }

    public void setRegistrosProcesoAdjuntos(List<GjProcesoAdjunto> registrosProcesoAdjuntos) {
        this.registrosProcesoAdjuntos = registrosProcesoAdjuntos;
    }

    public List<GjProcesoPretencion> getRegistrosProcesoPretenciones() {
        return registrosProcesoPretenciones;
    }

    public void setRegistrosProcesoPretenciones(List<GjProcesoPretencion> registrosProcesoPretenciones) {
        this.registrosProcesoPretenciones = registrosProcesoPretenciones;
    }

    public List<GjProcesoTercero> getRegistrosProcesoTerceros() {
        if (registrosProcesoTerceros == null) {
            registrosProcesoTerceros = new ArrayList<>();
        }
        return registrosProcesoTerceros;
    }

    public void setRegistrosProcesoTerceros(List<GjProcesoTercero> registrosProcesoTerceros) {
        this.registrosProcesoTerceros = registrosProcesoTerceros;
    }

    public List<GjProcesoGarantia> getRegistrosProcesoGarantias() {
        if (registrosProcesoGarantias == null) {
            registrosProcesoGarantias = new ArrayList<>();
        }
        return registrosProcesoGarantias;
    }

    public void setRegistrosProcesoGarantias(List<GjProcesoGarantia> registrosProcesoGarantias) {
        this.registrosProcesoGarantias = registrosProcesoGarantias;
    }

    public List<GjProcesoTercero> getAuxRegistrosProcesoTerceros() {
        if (auxRegistrosProcesoTerceros == null) {
            auxRegistrosProcesoTerceros = new ArrayList<>();
        }
        return auxRegistrosProcesoTerceros;
    }

    public void setAuxRegistrosProcesoTerceros(List<GjProcesoTercero> auxRegistrosProcesoTerceros) {
        this.auxRegistrosProcesoTerceros = auxRegistrosProcesoTerceros;
    }

    public List<GjTerceroContacto> getRegistrosTerceroContactos() {
        return registrosTerceroContactos;
    }

    public void setRegistrosTerceroContactos(List<GjTerceroContacto> registrosTerceroContactos) {
        this.registrosTerceroContactos = registrosTerceroContactos;
    }

    public List<GjProcesoHistorico> getRegistrosProcesoHistoricos() {
        return registrosProcesoHistoricos;
    }

    public void setRegistrosProcesoHistoricos(List<GjProcesoHistorico> registrosProcesoHistoricos) {
        this.registrosProcesoHistoricos = registrosProcesoHistoricos;
    }

    public LazyDataModel<GjProceso> getLazyGjProceso() {
        return lazyGjProceso;
    }

    public void setLazyGjProceso(LazyDataModel<GjProceso> lazyGjProceso) {
        this.lazyGjProceso = lazyGjProceso;
    }

    public LazyDataModel<GjTercero> getLazyGjTercero() {
        return lazyGjTercero;
    }

    public void setLazyGjTercero(LazyDataModel<GjTercero> lazyGjTercero) {
        this.lazyGjTercero = lazyGjTercero;
    }

    public LazyDataModel<GjAbogado> getLazyGjAbogado() {
        return lazyGjAbogado;
    }

    public void setLazyGjAbogado(LazyDataModel<GjAbogado> lazyGjAbogado) {
        this.lazyGjAbogado = lazyGjAbogado;
    }

    public LazyDataModel<GjProcesoHistorico> getLazyGjProcesoHistorico() {
        return lazyGjProcesoHistorico;
    }

    public void setLazyGjProcesoHistorico(LazyDataModel<GjProcesoHistorico> lazyGjProcesoHistorico) {
        this.lazyGjProcesoHistorico = lazyGjProcesoHistorico;
    }

    public LazyDataModel<GjProcesoAbogado> getLazyGjProcesoAbogado() {
        return lazyGjProcesoAbogado;
    }

    public void setLazyGjProcesoAbogado(LazyDataModel<GjProcesoAbogado> lazyGjProcesoAbogado) {
        this.lazyGjProcesoAbogado = lazyGjProcesoAbogado;
    }

    public LazyDataModel<GjProcesoTercero> getLazyGjProcesoTercero() {
        return lazyGjProcesoTercero;
    }

    public void setLazyGjProcesoTercero(LazyDataModel<GjProcesoTercero> lazyGjProcesoTercero) {
        this.lazyGjProcesoTercero = lazyGjProcesoTercero;
    }

    public LazyDataModel<GjProcesoAdjunto> getLazyGjProcesoAdjunto() {
        return lazyGjProcesoAdjunto;
    }

    public void setLazyGjProcesoAdjunto(LazyDataModel<GjProcesoAdjunto> lazyGjProcesoAdjunto) {
        this.lazyGjProcesoAdjunto = lazyGjProcesoAdjunto;
    }

    public ParamConsulta getParamConsultaJuzgado() {
        return paramConsultaJuzgado;
    }

    public void setParamConsultaJuzgado(ParamConsulta paramConsultaJuzgado) {
        this.paramConsultaJuzgado = paramConsultaJuzgado;
    }

    public ParamConsulta getParamConsultaTercero() {
        if (paramConsultaTercero == null) {
            paramConsultaTercero = new ParamConsulta();
        }
        return paramConsultaTercero;
    }

    public void setParamConsultaTercero(ParamConsulta paramConsultaTercero) {
        this.paramConsultaTercero = paramConsultaTercero;
    }

    public ParamConsulta getParamConsultaGarantia() {
        return paramConsultaGarantia;
    }

    public void setParamConsultaGarantia(ParamConsulta paramConsultaGarantia) {
        this.paramConsultaGarantia = paramConsultaGarantia;
    }

    public ParamConsulta getParamConsultaAbogado() {
        if (paramConsultaAbogado == null) {
            paramConsultaAbogado = new ParamConsulta();
        }
        return paramConsultaAbogado;
    }

    public void setParamConsultaAbogado(ParamConsulta paramConsultaAbogado) {
        this.paramConsultaAbogado = paramConsultaAbogado;
    }

    public ParamConsulta getParamConsultaHistorialProceso() {
        return paramConsultaHistorialProceso;
    }

    public void setParamConsultaHistorialProceso(ParamConsulta paramConsultaHistorialProceso) {
        this.paramConsultaHistorialProceso = paramConsultaHistorialProceso;
    }

    public ParamConsulta getParamConsultaProcesoAbogado() {
        return paramConsultaProcesoAbogado;
    }

    public void setParamConsultaProcesoAbogado(ParamConsulta paramConsultaProcesoAbogado) {
        this.paramConsultaProcesoAbogado = paramConsultaProcesoAbogado;
    }

    public ParamConsulta getParamConsultaProcesoPretencion() {
        return paramConsultaProcesoPretencion;
    }

    public void setParamConsultaProcesoPretencion(ParamConsulta paramConsultaProcesoPretencion) {
        this.paramConsultaProcesoPretencion = paramConsultaProcesoPretencion;
    }

    public ParamConsulta getParamConsultaProcesoTercero() {
        return paramConsultaProcesoTercero;
    }

    public void setParamConsultaProcesoTercero(ParamConsulta paramConsultaProcesoTercero) {
        this.paramConsultaProcesoTercero = paramConsultaProcesoTercero;
    }

    public ParamConsulta getParamConsultaTerceroContacto() {
        return paramConsultaTerceroContacto;
    }

    public void setParamConsultaTerceroContacto(ParamConsulta paramConsultaTerceroContacto) {
        this.paramConsultaTerceroContacto = paramConsultaTerceroContacto;
    }

    public ParamConsulta getParamConsultaProcesoHistorico() {
        return paramConsultaProcesoHistorico;
    }

    public void setParamConsultaProcesoHistorico(ParamConsulta paramConsultaProcesoHistorico) {
        this.paramConsultaProcesoHistorico = paramConsultaProcesoHistorico;
    }

    public ParamConsulta getParamConsultaProcesoAdjunto() {
        return paramConsultaProcesoAdjunto;
    }

    public void setParamConsultaProcesoAdjunto(ParamConsulta paramConsultaProcesoAdjunto) {
        this.paramConsultaProcesoAdjunto = paramConsultaProcesoAdjunto;
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

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public List<Maestro> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<Maestro> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public HashMap<Integer, Maestro> getHashTipo() {
        return hashTipo;
    }

    public void setHashTipo(HashMap<Integer, Maestro> hashTipo) {
        this.hashTipo = hashTipo;
    }

    public List<Maestro> getListaInstancia() {
        return listaInstancia;
    }

    public void setListaInstancia(List<Maestro> listaInstancia) {
        this.listaInstancia = listaInstancia;
    }

    public HashMap<Integer, Maestro> getHashInstancia() {
        return hashInstancia;
    }

    public void setHashInstancia(HashMap<Integer, Maestro> hashInstancia) {
        this.hashInstancia = hashInstancia;
    }

    public List<Maestro> getListaMedicaCautelar() {
        return listaMedicaCautelar;
    }

    public void setListaMedicaCautelar(List<Maestro> listaMedicaCautelar) {
        this.listaMedicaCautelar = listaMedicaCautelar;
    }

    public HashMap<Integer, Maestro> getHashMedicaCautelar() {
        return hashMedicaCautelar;
    }

    public void setHashMedicaCautelar(HashMap<Integer, Maestro> hashMedicaCautelar) {
        this.hashMedicaCautelar = hashMedicaCautelar;
    }

    public List<Maestro> getListaActuacionTerminacion() {
        return listaActuacionTerminacion;
    }

    public void setListaActuacionTerminacion(List<Maestro> listaActuacionTerminacion) {
        this.listaActuacionTerminacion = listaActuacionTerminacion;
    }

    public HashMap<Integer, Maestro> getHashActuacionTerminacion() {
        return hashActuacionTerminacion;
    }

    public void setHashActuacionTerminacion(HashMap<Integer, Maestro> hashActuacionTerminacion) {
        this.hashActuacionTerminacion = hashActuacionTerminacion;
    }

    public List<Maestro> getListaPretencion() {
        return listaPretencion;
    }

    public void setListaPretencion(List<Maestro> listaPretencion) {
        this.listaPretencion = listaPretencion;
    }

    public HashMap<Integer, Maestro> getHashPretencion() {
        return hashPretencion;
    }

    public void setHashPretencion(HashMap<Integer, Maestro> hashPretencion) {
        this.hashPretencion = hashPretencion;
    }

    public List<Maestro> getListaCalidadAcatua() {
        return listaCalidadAcatua;
    }

    public void setListaCalidadAcatua(List<Maestro> listaCalidadAcatua) {
        this.listaCalidadAcatua = listaCalidadAcatua;
    }

    public HashMap<Integer, Maestro> getHashCalidadAcatua() {
        return hashCalidadAcatua;
    }

    public void setHashCalidadAcatua(HashMap<Integer, Maestro> hashCalidadAcatua) {
        this.hashCalidadAcatua = hashCalidadAcatua;
    }

    public List<Maestro> getListaJurisdiccion() {
        return listaJurisdiccion;
    }

    public void setListaJurisdiccion(List<Maestro> listaJurisdiccion) {
        this.listaJurisdiccion = listaJurisdiccion;
    }

    public HashMap<Integer, Maestro> getHashJurisdiccion() {
        return hashJurisdiccion;
    }

    public void setHashJurisdiccion(HashMap<Integer, Maestro> hashJurisdiccion) {
        this.hashJurisdiccion = hashJurisdiccion;
    }

    public List<Maestro> getListaClase() {
        return listaClase;
    }

    public void setListaClase(List<Maestro> listaClase) {
        this.listaClase = listaClase;
    }

    public HashMap<Integer, Maestro> getHashClase() {
        return hashClase;
    }

    public void setHashClase(HashMap<Integer, Maestro> hashClase) {
        this.hashClase = hashClase;
    }

    public List<Maestro> getListaClaseDescripcion() {
        return listaClaseDescripcion;
    }

    public void setListaClaseDescripcion(List<Maestro> listaClaseDescripcion) {
        this.listaClaseDescripcion = listaClaseDescripcion;
    }

    public HashMap<Integer, Maestro> getHashClaseDescripcion() {
        return hashClaseDescripcion;
    }

    public void setHashClaseDescripcion(HashMap<Integer, Maestro> hashClaseDescripcion) {
        this.hashClaseDescripcion = hashClaseDescripcion;
    }

    public List<Maestro> getListaTipoContacto() {
        return listaTipoContacto;
    }

    public void setListaTipoContacto(List<Maestro> listaTipoContacto) {
        this.listaTipoContacto = listaTipoContacto;
    }

    public HashMap<Integer, Maestro> getHashTipoContacto() {
        return hashTipoContacto;
    }

    public void setHashTipoContacto(HashMap<Integer, Maestro> hashTipoContacto) {
        this.hashTipoContacto = hashTipoContacto;
    }

    public List<GjProcesoAbogado> getListaGjProcesoAbogados() {
        return listaGjProcesoAbogados;
    }

    public void setListaGjProcesoAbogados(List<GjProcesoAbogado> listaGjProcesoAbogados) {
        this.listaGjProcesoAbogados = listaGjProcesoAbogados;
    }

    public List<GjTerceroContacto> getListaGjTerceroContactos() {
        if (listaGjTerceroContactos == null) {
            listaGjTerceroContactos = new ArrayList<>();
        }
        return listaGjTerceroContactos;
    }

    public void setListaGjTerceroContactos(List<GjTerceroContacto> listaGjTerceroContactos) {
        this.listaGjTerceroContactos = listaGjTerceroContactos;
    }

    public List<GjTercero> getListaGjTerceros() {
        return listaGjTerceros;
    }

    public void setListaGjTerceros(List<GjTercero> listaGjTerceros) {
        this.listaGjTerceros = listaGjTerceros;
    }

    public List<GjAbogado> getGjAbogados() {
        return GjAbogados;
    }

    public void setGjAbogados(List<GjAbogado> GjAbogados) {
        this.GjAbogados = GjAbogados;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public List<TuJuzgado> getListaJuzgados() {
        if (listaJuzgados == null) {
            listaJuzgados = new ArrayList<>();
        }
        return listaJuzgados;
    }

    public void setListaJuzgados(List<TuJuzgado> listaJuzgados) {
        this.listaJuzgados = listaJuzgados;
    }

    public HashMap<Integer, TuJuzgado> getHashJuzgados() {
        return hashJuzgados;
    }

    public void setHashJuzgados(HashMap<Integer, TuJuzgado> hashJuzgados) {
        this.hashJuzgados = hashJuzgados;
    }

    public List<GjTercero> getListaTerceroReprecentante() {
        return listaTerceroReprecentante;
    }

    public void setListaTerceroReprecentante(List<GjTercero> listaTerceroReprecentante) {
        this.listaTerceroReprecentante = listaTerceroReprecentante;
    }

    public List<GjAbogado> getListaAbogados() {
        return listaAbogados;
    }

    public void setListaAbogados(List<GjAbogado> listaAbogados) {
        this.listaAbogados = listaAbogados;
    }

    public List<GjProcesoAdjunto> getListaProcesoAdjuntosServicio() {
        return listaProcesoAdjuntosServicio;
    }

    public void setListaProcesoAdjuntosServicio(List<GjProcesoAdjunto> listaProcesoAdjuntosServicio) {
        this.listaProcesoAdjuntosServicio = listaProcesoAdjuntosServicio;
    }

    public TuJuzgado getObjJuzgado() {
        if (objJuzgado == null) {
            objJuzgado = new TuJuzgado();
        }
        return objJuzgado;
    }

    public void setObjJuzgado(TuJuzgado objJuzgado) {
        this.objJuzgado = objJuzgado;
    }

    public GjTercero getObjTercero() {
        return objTercero;
    }

    public void setObjTercero(GjTercero objTercero) {
        this.objTercero = objTercero;
    }

    public GjAbogado getObjAbogado() {
        return objAbogado;
    }

    public void setObjAbogado(GjAbogado objAbogado) {
        this.objAbogado = objAbogado;
    }

    public GjProcesoAbogado getObjProcesoAbogado() {
        return objProcesoAbogado;
    }

    public void setObjProcesoAbogado(GjProcesoAbogado objProcesoAbogado) {
        this.objProcesoAbogado = objProcesoAbogado;
    }

    public GjProcesoAdjunto getObjProcesoAdjunto() {
        return objProcesoAdjunto;
    }

    public void setObjProcesoAdjunto(GjProcesoAdjunto objProcesoAdjunto) {
        this.objProcesoAdjunto = objProcesoAdjunto;
    }

    public GjProcesoHistorico getObjProcesoHistorico() {
        return objProcesoHistorico;
    }

    public void setObjProcesoHistorico(GjProcesoHistorico objProcesoHistorico) {
        this.objProcesoHistorico = objProcesoHistorico;
    }

    public GjProcesoPretencion getObjProcesoPretencion() {
        return objProcesoPretencion;
    }

    public void setObjProcesoPretencion(GjProcesoPretencion objProcesoPretencion) {
        this.objProcesoPretencion = objProcesoPretencion;
    }

    public GjProcesoTercero getObjProcesoTercero() {
        return objProcesoTercero;
    }

    public void setObjProcesoTercero(GjProcesoTercero objProcesoTercero) {
        this.objProcesoTercero = objProcesoTercero;
    }

    public GjTerceroContacto getGjTerceroContacto() {
        if (GjTerceroContacto == null) {
            GjTerceroContacto = new GjTerceroContacto();
        }
        return GjTerceroContacto;
    }

    public void setGjTerceroContacto(GjTerceroContacto GjTerceroContacto) {
        this.GjTerceroContacto = GjTerceroContacto;
    }

    public GjProcesoAdjunto getBorrarAdjunto() {
        return borrarAdjunto;
    }

    public void setBorrarAdjunto(GjProcesoAdjunto borrarAdjunto) {
        this.borrarAdjunto = borrarAdjunto;
    }

    public int getContadorArchivos() {
        return contadorArchivos;
    }

    public void setContadorArchivos(int contadorArchivos) {
        this.contadorArchivos = contadorArchivos;
    }

    public int getSizeLimitByAnexo() {
        return sizeLimitByAnexo;
    }

    public void setSizeLimitByAnexo(int sizeLimitByAnexo) {
        this.sizeLimitByAnexo = sizeLimitByAnexo;
    }

    public int getMaxCantAnexos() {
        return maxCantAnexos;
    }

    public void setMaxCantAnexos(int maxCantAnexos) {
        this.maxCantAnexos = maxCantAnexos;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

//    public UbicacionSingle getUbicacionSingle() {
//        return ubicacionSingle;
//    }
//
//    public void setUbicacionSingle(UbicacionSingle ubicacionSingle) {
//        this.ubicacionSingle = ubicacionSingle;
//    }
    public boolean isValidarvisibletercero() {
        return validarvisibletercero;
    }

    public void setValidarvisibletercero(boolean validarvisibletercero) {
        this.validarvisibletercero = validarvisibletercero;
    }

    public boolean isValidarrequeridotercero() {
        return validarrequeridotercero;
    }

    public void setValidarrequeridotercero(boolean validarrequeridotercero) {
        this.validarrequeridotercero = validarrequeridotercero;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isRequerido() {
        return requerido;
    }

    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    public boolean isVisibleBotonAgregarTercero() {
        return visibleBotonAgregarTercero;
    }

    public void setVisibleBotonAgregarTercero(boolean visibleBotonAgregarTercero) {
        this.visibleBotonAgregarTercero = visibleBotonAgregarTercero;
    }

    public boolean isVisibleCalidadActua() {
        return visibleCalidadActua;
    }

    public void setVisibleCalidadActua(boolean visibleCalidadActua) {
        this.visibleCalidadActua = visibleCalidadActua;
    }

    public boolean isDisabeBotonAgregarGarantia() {
        return disabeBotonAgregarGarantia;
    }

    public void setDisabeBotonAgregarGarantia(boolean disabeBotonAgregarGarantia) {
        this.disabeBotonAgregarGarantia = disabeBotonAgregarGarantia;
    }

    public boolean isDisablegarantia() {
        return disablegarantia;
    }

    public void setDisablegarantia(boolean disablegarantia) {
        this.disablegarantia = disablegarantia;
    }

    public boolean isRequerirCampoSentidoSentencia() {
        return requerirCampoSentidoSentencia;
    }

    public void setRequerirCampoSentidoSentencia(boolean requerirCampoSentidoSentencia) {
        this.requerirCampoSentidoSentencia = requerirCampoSentidoSentencia;
    }

    public boolean isRequerirCampoFechaTerminacion() {
        return requerirCampoFechaTerminacion;
    }

    public void setRequerirCampoFechaTerminacion(boolean requerirCampoFechaTerminacion) {
        this.requerirCampoFechaTerminacion = requerirCampoFechaTerminacion;
    }

    public boolean isRequerirCampoValorSentenciaEjecutoria() {
        return requerirCampoValorSentenciaEjecutoria;
    }

    public void setRequerirCampoValorSentenciaEjecutoria(boolean requerirCampoValorSentenciaEjecutoria) {
        this.requerirCampoValorSentenciaEjecutoria = requerirCampoValorSentenciaEjecutoria;
    }

    public boolean isRequerirCampoActuacionTerminacion() {
        return requerirCampoActuacionTerminacion;
    }

    public void setRequerirCampoActuacionTerminacion(boolean requerirCampoActuacionTerminacion) {
        this.requerirCampoActuacionTerminacion = requerirCampoActuacionTerminacion;
    }

    public boolean isRequerirCampoValorAcuerdoT() {
        return requerirCampoValorAcuerdoT;
    }

    public void setRequerirCampoValorAcuerdoT(boolean requerirCampoValorAcuerdoT) {
        this.requerirCampoValorAcuerdoT = requerirCampoValorAcuerdoT;
    }

    public boolean isRadicadoRegistradoEnSistema() {
        return radicadoRegistradoEnSistema;
    }

    public void setRadicadoRegistradoEnSistema(boolean radicadoRegistradoEnSistema) {
        this.radicadoRegistradoEnSistema = radicadoRegistradoEnSistema;
    }

//metodos
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(GjProcesoBean.ACCION_LISTAR3);
        getGjProcesoServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        setRegistrosProcesoTerceros(new ArrayList<>());
        setListaGjTerceroContactos(new ArrayList<>());
        setRegistrosProcesoGarantias(new ArrayList<>());
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getGjProcesoServicio().Accion(this);
        formatearValoresCop();
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        setRegistrosProcesoTerceros(new ArrayList<>());
        getGjProcesoServicio().Accion(this);
        getGjProcesoServicio().consultarUbicaciones(this);
        fechaActual();
        procesoFinal();
    }

    public void guardar() {
        if (getObjeto().getRadicado().length() != 23) {
            addError("El campo Radicado debe tener 23 caracteres");

        } else if (getObjeto().getRadicado().length() == 23) {
            getGjProcesoServicio().consultarRadicado(this);
            if (isRadicadoRegistradoEnSistema() == true) {
                addError("El radicado ya a sido asignado a un proceso dentro del sistema");
            }
        }
        if (getObjeto().getGjProcesoAbogado().getGjAbogadosId().getId() == null) {
            addError("El proceso debe llevar un abogado");
        }

        if (getObjeto().getPretencionDescripcion().length() >= 1024) {
            addError("La pretencion excede el limite permitido de caracteres");
        }
        validarRangosDeFecha();
        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getGjProcesoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
            }
        }
        procesoFinal();
    }

    public void guardarGestion() {
        if (getObjeto().getRadicado().length() != 23) {
            addError("El campo Radicado debe tener 23 caracteres");
        }
        if (!super.isError()) {
            if (objeto.getEstado() == 4 || objeto.getEstado() == 5) {
                objeto.getGjProcesoAbogado().setFechaFin(fechaActual);
            }
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_INSERTAR_GESTION);
            getGjProcesoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoGestionar').hide();");
            }
        }
        procesoFinal();
    }

    public void editar(int _id, short estado, String est) {
        setRegistrosProcesoTerceros(new ArrayList<>());
        setListaGjTerceroContactos(new ArrayList<>());
        getObjeto().setId(_id);
        if (estado == 4 || estado == 5) {
            addError("No tiene permitido realizar la edicion de un proceso en estado: " + est + "");
        }
        if (!super.isError()) {
            super.setAccion(ACCION_EDITAR);
            getGjProcesoServicio().Accion(this);
        }
        procesoFinal();
    }

    public void modificar() {
        if (getObjeto().getRadicado().length() != 23) {
            addError("El campo Radicado debe tener 23 caracteres");
        }
        validarRangosDeFecha();
        if (!super.isError()) {
            super.setAccion(ACCION_MODIFICAR);
            getGjProcesoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            }
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        getObjeto().setBorrado(Boolean.TRUE);
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR);
        getGjProcesoServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                     crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmProcesos");
                    break;
                case Url.ACCION_GUARDAR:
                     crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmProcesos");
                    break;
                case Url.ACCION_MODIFICAR:
                     crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmProcesos");
                    break;
                case Url.ACCION_BORRAR:
                     crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmProcesos");
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case GjProcesoBean.ACCION_VER1:
                             crearLog("Ver Historicos",getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmH");
                            PrimeFaces.current().executeScript("PF('dialogoHistorial2').show()");
                            break;
                        case Url.ACCION_VER:
                             crearLog("Ver",getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerProceso");
                            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                     crearLog(getObjeto().toString());
                    visibleBotonAgregarTercero = true;
                    visibleCalidadActua = false;
                    PrimeFaces.current().resetInputs("frmCrear");
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    break;
                case Url.ACCION_EDITAR:                  
                    getGjProcesoServicio().consultarUbicaciones(this);
                    getGjProcesoServicio().listarJuzgados(this);
                    PrimeFaces.current().ajax().update("frmEditar");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case GjProcesoBean.ACCION_INICIO_GESTION:
                             crearLog("Gestionar",getObjeto().toString());
                            getGjProcesoServicio().consultarUbicaciones(this);
                            getGjProcesoServicio().listarJuzgados(this);
                            objeto.setUltimaActuacionStr("");
                            ValidarEstado();
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            break;
                        case GjProcesoBean.ACCION_INSERTAR_GESTION:
                            crearLog("Guardar Gestion",getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmProcesos");
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public void formatearValoresCop() {

        FORMATO_VALORES_COP.setCurrency(MONEDA_COP);

        BigDecimal cuantia = objeto.getCuantia();
        BigDecimal valorSentenciaEjecutoria = objeto.getValorSentenciaEjecutoria();
        BigDecimal montoMedida = objeto.getMontoMedida();

        BigDecimal valorRiesgoCondena = objeto.getValorRiesgoCondena();
        BigDecimal cuantiaObjetiva = objeto.getCuantiaObjetiva();

        StringBuilder builder = new StringBuilder();

        if (cuantia != null) {
            builder.append(FORMATO_VALORES_COP.format(cuantia)).append(" ");
            objeto.setSetCuantiaStr(builder.toString());
            builder.setLength(0);
        }
        if (valorSentenciaEjecutoria != null) {
            builder.append(FORMATO_VALORES_COP.format(valorSentenciaEjecutoria)).append(" ");
            objeto.setSetValorSentenciaEjecutoriaStr(builder.toString());
            builder.setLength(0);
        }
        if (montoMedida != null) {
            builder.append(FORMATO_VALORES_COP.format(montoMedida)).append(" ");
            objeto.setSetMontoMedidaStr(builder.toString());
            builder.setLength(0);
        }
        if (valorRiesgoCondena != null) {
            builder.append(FORMATO_VALORES_COP.format(valorRiesgoCondena)).append(" ");
            objeto.setSetValorRiesgoCondenaStr(builder.toString());
            builder.setLength(0);
        }
        if (cuantiaObjetiva != null) {
            builder.append(FORMATO_VALORES_COP.format(cuantiaObjetiva)).append(" ");
            objeto.setSetCuantiaObjetivaStr(builder.toString());
        }
    }

    public void formatearValoresCopG() {

        FORMATO_VALORES_COP.setCurrency(MONEDA_COP);

        BigDecimal cuantia = objeto.getCuantia();
        BigDecimal valorRiesgoCondena = objeto.getValorRiesgoCondena();
        BigDecimal cuantiaObjetiva = objeto.getCuantiaObjetiva();

        StringBuilder builder = new StringBuilder();

        if (cuantia != null) {
            builder.append(FORMATO_VALORES_COP.format(cuantia)).append(" ");
            objeto.setSetCuantiaStr(builder.toString());
            builder.setLength(0);
        }
        if (valorRiesgoCondena != null) {
            builder.append(FORMATO_VALORES_COP.format(valorRiesgoCondena)).append(" ");
            objeto.setSetValorRiesgoCondenaStr(builder.toString());
            builder.setLength(0);
        }
        if (cuantiaObjetiva != null) {
            builder.append(FORMATO_VALORES_COP.format(cuantiaObjetiva)).append(" ");
            objeto.setSetCuantiaObjetivaStr(builder.toString());
        }
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = ubicacionesRecursiva.get(id).getUbicacionPadre().getId();
            return ubicacionesRecursiva.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void verHistorial(int id) {
        try {
            inicializarTablaHistorial(id);
            PrimeFaces.current().ajax().update("frmHistorial");
            PrimeFaces.current().executeScript("PF('dialogoHistorial').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void inicializarTablaHistorial(int id) {
        this.setParamConsultaHistorialProceso(new ParamConsulta());
        this.getParamConsultaHistorialProceso().setParametroConsulta1(id);
        lazyGjProcesoHistorico = new LazyDataModel<GjProcesoHistorico>() {
            private List<GjProcesoHistorico> tuPersona;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaHistorialProceso().getCantidadRegistros();
            }

            @Override
            public List<GjProcesoHistorico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaHistorialProceso().setPrimerRegistro(primerRegistro);
                getParamConsultaHistorialProceso().setRegistrosPagina(registrosPagina);
                getParamConsultaHistorialProceso().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaHistorialProceso().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHistorial();
                tuPersona = getRegistrosProcesoHistoricos();
                setRowCount(getParamConsultaHistorialProceso().getCantidadRegistros());
                return tuPersona;
            }

            @Override
            public String getRowKey(GjProcesoHistorico persona) {
                return persona.getId().toString();
            }

            @Override
            public GjProcesoHistorico getRowData(String personaId
            ) {
                Integer id = Integer.valueOf(personaId);
                for (GjProcesoHistorico persona : tuPersona) {
                    if (id.equals(persona.getId())) {
                        return persona;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarHistorial() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getGjProcesoServicio().Accion(this);
    }

    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 15000000 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }

    public void verSeguimientoObservacion(int _id, int _idProces) {
        getObjeto().getGjProcesoHistorico().setId(_id);
        inicializarTablaAdjunto(_id, _idProces);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(GjProcesoBean.ACCION_VER1);
        getGjProcesoServicio().Accion(this);
        procesoFinal();
    }

    public void inicializarTablaAdjunto(int idH, int idP) {
        this.setParamConsultaProcesoAdjunto(new ParamConsulta());
        this.getParamConsultaProcesoAdjunto().setParametroConsulta1(idP);
        this.getParamConsultaProcesoAdjunto().setParametroConsulta2(idH);
        lazyGjProcesoAdjunto = new LazyDataModel<GjProcesoAdjunto>() {
            private List<GjProcesoAdjunto> adjunto;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaProcesoAdjunto().getCantidadRegistros();
            }

            @Override
            public List<GjProcesoAdjunto> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaProcesoAdjunto().setPrimerRegistro(primerRegistro);
                getParamConsultaProcesoAdjunto().setRegistrosPagina(registrosPagina);
                getParamConsultaProcesoAdjunto().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaProcesoAdjunto().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAdjunto();
                adjunto = getRegistrosProcesoAdjuntos();
                setRowCount(getParamConsultaProcesoAdjunto().getCantidadRegistros());
                return adjunto;
            }

            @Override
            public String getRowKey(GjProcesoAdjunto persona
            ) {
                return persona.getId().toString();
            }

            @Override
            public GjProcesoAdjunto getRowData(String personaId
            ) {
                Integer id = Integer.valueOf(personaId);
                for (GjProcesoAdjunto persona : adjunto) {
                    if (id.equals(persona.getId())) {
                        return persona;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarAdjunto() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(GjProcesoBean.ACCION_VER3);
        getGjProcesoServicio().Accion(this);
    }

    public void handleFileUploadServicio(FileUploadEvent event) throws IOException {
        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_Proceso_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);
            String ruta = PropApl.getInstance().get(PropApl.TU_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            }
            OutputStream output = new FileOutputStream(new File(ruta, filename));
            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
            //agraegar el nuevo adjunto a la lista de adjunto de Procesoadjuntos
            GjProcesoAdjunto adjunto = new GjProcesoAdjunto();
            adjunto.setId(null);
            adjunto.setNombreArchivo(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            adjunto.setMaeTipoId(728);
            adjunto.setMaeTipoCodigo(ext);
            adjunto.setMaeTipoValor(ext);
            this.auditoriaGuardar(adjunto);
            //listaAnexos.add(adjunto);
            this.getObjeto().getGjProceso().getGjProcesoAdjuntosList().add(adjunto);
            List<GjProcesoAdjunto> prueba = getObjeto().getGjProceso().getGjProcesoAdjuntosList();

            objeto.setGjProcesoAdjuntosList(prueba);
            PrimeFaces.current().ajax().update("frmGestionar:tablaCrearAnexos");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public void borrarAdjuntoSMemoria(int id, int pos, Integer identificadoArchivo) {
        try {
            if (id > 0) {
                getBorrarAdjunto().setId(id);
                setBorrarAdjunto(new GjProcesoAdjunto());
                borrarAdjunto.setId(id);
                super.setAccion(Url.ACCION_BORRAR);
                super.setDoAccion(BORRAR_ADJUNTO);
                getGjProcesoServicio().Accion(this);
            }
            switch (identificadoArchivo) {
                case 0:
                    delAdjuntoProcesoMemoria(pos);
                    break;
            }
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:tablaCrearAnexosEstados");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:tablaItemdicionalAnexos");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:panelCrearAutorizaciones");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:tablaCrearAutorizaciones");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaEditarDiagnostico");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaItemdicionalAnexos");
            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:tablaItemdicionalAnexos");
            PrimeFaces.current().ajax().update("frmCrearSeguimientoAdicional:tablaSeguimientoAdicionalAnexos");
            PrimeFaces.current().ajax().update("frmCrearEstadoAdicional:tablaEstadosAdicionalAnexos");
            PrimeFaces.current().ajax().update("frmEditarEstadoAdicional:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmEditarSeguimiento:tablaEditarAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:panelEditarAutorizaciones");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaEditarAutorizaciones");
            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:panelEditarGestionAutorizaciones");
            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:tablaEditarGestionAutorizaciones");
        }
        generarMensajes();
    }

    public void delAdjuntoProcesoMemoria(int pos) {
        List<GjProcesoAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (GjProcesoAdjunto det : this.objeto.getGjProcesoAdjuntosList()) {
            if (j != pos) {
                lista.add(det);
                i++;
            }
            j++;
        }
        this.objeto.setGjProcesoAdjuntosList(lista);
    }

    public void descargarAnexo(GjProcesoAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + File.separator + adjunto.getNombreArchivo();
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombreArchivo() + "\"";
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

    public void descargarAnexo(String nombre, String ruta) {
        try {
            File path = new File(ruta + nombre);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return ubicacionesRecursiva.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        return ubicacionesFiltradas;
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        if (ubicacionesRecursiva != null && !ubicacionesRecursiva.isEmpty()) {
            Ubicacion _municipio = ubicacionesRecursiva.get(id);
            if (_municipio != null && _municipio.getUbicacionPadre() != null) {
                Ubicacion _departamento = _municipio.getUbicacionPadre();
                if (_departamento.getUbicacionPadre() != null) {
                    Ubicacion _pais = _departamento.getUbicacionPadre();
                    ubicacionStr = _pais.getNombre();
                }
                ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
                ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
            }
        }
        return ubicacionStr;
    }

    public String obtenerMunicipioDepartamento(int id) {
        try {
            String municipio = obtenerMunicipio(id);
            String departamento = obtenerDepartamento(id);
            return municipio + " - " + departamento;
        } catch (Exception e) {
            return "";
        }
    }

    public void verGestion(int _id, short estado, String est) {
        getObjeto().setId(_id);
        setRegistrosProcesoTerceros(new ArrayList<>());
        setListaGjTerceroContactos(new ArrayList<>());
        setRegistrosProcesoGarantias(new ArrayList<>());
        if (estado == 4 || estado == 5) {
            addError("No tiene permitido realizar gestiones de un proceso en estado: " + est + "");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_INICIO_GESTION);
            getGjProcesoServicio().Accion(this);
            fechaUltimaActual();
            formatearValoresCopG();
        }
        procesoFinal();
    }

    public void llamamientoGarantia() {
        disabeBotonAgregarGarantia = false;
        disablegarantia = true;
        PrimeFaces.current().ajax().update("frmGestionar:pGarantias");
    }

    public void onRowSelectTerceroAfiliado(SelectEvent event) {
        GjTercero afiliado = (GjTercero) event.getObject();
//        getObjeto().getGjProcesoTercero().getGjTercerosId().setGjPersona(afiliado);

//        getGjProcesoServicio().completarTerceroAfiliado(this);
        GjProcesoTercero procesoTercero = new GjProcesoTercero();

        procesoTercero.setGjTercerosId(afiliado);
        Maestro calidadActua = getHashCalidadAcatua().get(objeto.getGjProcesoTercero().getMaeCalidadActuaId());
        if (calidadActua != null) {
            procesoTercero.setMaeCalidadActuaId(objeto.getGjProcesoTercero().getMaeCalidadActuaId());
            procesoTercero.setMaeCalidadActuaCodigo(calidadActua.getValor());
            procesoTercero.setMaeCalidadActuaValor(calidadActua.getNombre());
        }
        validarRepresentanteRequerido(procesoTercero);

        getParamConsultaTercero().setFiltros(new HashMap<>());

    }

    public void validarRepresentanteRequerido(GjProcesoTercero procesoTercero) {
        var maevalordoc = procesoTercero.getGjTercerosId().getMaeTipoDocumentoId();

        for (GjProcesoTercero contacto : getRegistrosProcesoTerceros()) {
            if (contacto.getGjTercerosId().getDocumento().equals(procesoTercero.getGjTercerosId().getDocumento())) {
                addError("El tercero ya existe en la lista de demandande(s) / demandado(s)");
                break;
            }
        }
        if (!super.isError()) {

            if (maevalordoc == 193
                    || maevalordoc == 127
                    || maevalordoc == 8
                    || maevalordoc == 6) {
                getObjeto().setGjProcesoTercero(procesoTercero);
                PrimeFaces.current().resetInputs("frmRepre");
                PrimeFaces.current().ajax().update("frmRepre");
                PrimeFaces.current().executeScript("PF('dialogoListaRepresentantes').show()");

            } else {

                if (!super.isError()) {
                    this.getObjeto().getGjProceso().getGjProcesoTercerosList().add(procesoTercero);
                    List<GjProcesoTercero> prueba = getObjeto().getGjProceso().getGjProcesoTercerosList();
                    setRegistrosProcesoTerceros(prueba);
                    PrimeFaces.current().ajax().update("frmCrear:tablaTerceroCrear");
                    PrimeFaces.current().executeScript("PF('dialogoTerceroLista').hide()");
                }
            }

        }
        generarMensajes();
    }

    public void guardarRepresentante() {
        GjProcesoTercero procesoTercero = getObjeto().getGjProcesoTercero();
        Maestro tipoDocumento = getHashTiposDocumento().get(procesoTercero.getMaeTipoDocumentoId());
        if (tipoDocumento != null) {
            procesoTercero.setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
            procesoTercero.setMaeTipoDocumentoValor(tipoDocumento.getNombre());
        }
        for (GjProcesoTercero contacto : getRegistrosProcesoTerceros()) {
            if (contacto.getDocumento() != null) {
                if (contacto.getDocumento().equals(procesoTercero.getDocumento())) {
                    addError("Un representante ya existe en la lista");
                    break;
                }
            }
        }
        if (!super.isError()) {

            this.getObjeto().getGjProceso().getGjProcesoTercerosList().add(procesoTercero);
            List<GjProcesoTercero> prueba = getObjeto().getGjProceso().getGjProcesoTercerosList();
            setRegistrosProcesoTerceros(prueba);

            PrimeFaces.current().ajax().update("frmCrear:tablaTerceroCrear");
            PrimeFaces.current().ajax().update("frmCrear:tablaRepre");
            PrimeFaces.current().executeScript("PF('dialogoTerceroLista').hide()");
            PrimeFaces.current().executeScript("PF('dialogoListaRepresentantes').hide()");
        }
        generarMensajes();

    }

    public void borrarTercero(GjProcesoTercero terceroP) {
        List<GjProcesoTercero> listaterceroP = new ArrayList<>();
        int posicionEliminar = registrosProcesoTerceros.indexOf(terceroP);
        for (GjProcesoTercero contact : getRegistrosProcesoTerceros()) {
            listaterceroP.add(contact);
        }

        List<GjProcesoTercero> prueba = getObjeto().getGjProceso().getGjProcesoTercerosList();
        prueba.remove(posicionEliminar);
        listaterceroP.remove(posicionEliminar);
        setRegistrosProcesoTerceros(listaterceroP);
        addMensaje("Se ha eliminado un tercero de la lista demandante(s) / demandado(s)");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear");
    }

    public void onRowSelectAbogadoAfiliado(SelectEvent event) {
        GjAbogado abogado = (GjAbogado) event.getObject();
//        Integer maevalordoc = getObjeto().getGjProcesoAbogado().getGjAbogadosId().getMaeTipoDocumentoId();
        getObjeto().getGjProcesoAbogado().getGjAbogadosId().setGjPersona(abogado);
        getGjProcesoServicio().completarAbogadoAfiliado(this);
        getParamConsultaAbogado().setFiltros(new HashMap<>());
        PrimeFaces.current().ajax().update("frmCrear:pAbogadoCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrearAbogado");
        PrimeFaces.current().ajax().update("frmEditar:pAbogadoEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditarAbogado");
        PrimeFaces.current().executeScript("PF('dialogoAbogadoLista').hide()");
        generarMensajes();
    }

    public void guardarGarantia() {
        GjProcesoGarantia proceso = new GjProcesoGarantia();
        GjProcesoGarantia procesoTercero = getObjeto().getGjProcesoGarantia();

//        if (getRegistrosProcesoGarantias().size() >= 3) {
//            addError("No se pueden agregar más de tres registros de garantías");
//        }
        if (!super.isError()) {

            Maestro tipoDocumento = getHashTiposDocumento().get(objeto.getGjProcesoGarantia().getMaeTipoDocumentoId());
            if (tipoDocumento != null) {
                proceso.setMaeTipoDocumentoId(tipoDocumento.getId());
                proceso.setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                proceso.setMaeTipoDocumentoValor(tipoDocumento.getNombre());
                proceso.setDocumento(procesoTercero.getDocumento());
                proceso.setNombres(procesoTercero.getNombres());
                proceso.setApellidos(procesoTercero.getApellidos());
            }

            if (validarGarantia(proceso)) {

                this.getObjeto().getGjProceso().getGjProcesoGarantiasList().add(proceso);
                List<GjProcesoGarantia> prueba = getObjeto().getGjProceso().getGjProcesoGarantiasList();
                setRegistrosProcesoGarantias(prueba);
                PrimeFaces.current().ajax().update("frmGestionar:tablaGarantias");
                PrimeFaces.current().executeScript("PF('dialogoGarantias').hide()");
            }
        }
        generarMensajes();
    }

    public boolean validarGarantia(GjProcesoGarantia procesoGarantia) {
        for (GjProcesoGarantia contacto : getRegistrosProcesoGarantias()) {
            if (contacto.getDocumento().equals(procesoGarantia.getDocumento())) {
                addError("Ya existe en la lista un llamamiento a garantias con el mismo documento");
                return false;
            }
        }
        return true;
    }

    public void consultarTercero() {
        inizializarTerceros();
        PrimeFaces.current().executeScript("PF('dialogoTerceroLista').show()");
    }

    public void agregarGarantia() {
        objeto.getGjProcesoGarantia().setMaeTipoDocumentoId(0);
        objeto.getGjProcesoGarantia().setDocumento("");
        objeto.getGjProcesoGarantia().setNombres("");
        objeto.getGjProcesoGarantia().setApellidos("");
        PrimeFaces.current().ajax().update("frmGarantia");
        PrimeFaces.current().executeScript("PF('dialogoGarantias').show()");
    }

    public void consultarAbogado() {
        inicializarLazyAbogado();
        PrimeFaces.current().executeScript("PF('dialogoAbogadoLista').show()");
    }

    public void refrescarTerceroAfiliado() {
        getGjProcesoServicio().listarTerceroAfiliado(this);
    }

    public void refrescarAbogadoAfiliado() {
        getGjProcesoServicio().listarAbogadoAfiliado(this);
    }

    public void listarJuszadosAsociadas() {
        getGjProcesoServicio().listarJuzgados(this);
        PrimeFaces.current().ajax().update("frmCrear:juzgado");
        PrimeFaces.current().ajax().update("frmEditar:juzgadoEditar");
        PrimeFaces.current().ajax().update("frmGestionar:juzgadoGestionar");
        generarMensajes();
    }

    public void listarMaeClaseDes() {
        getGjProcesoServicio().listarClaseDes(this);
        PrimeFaces.current().ajax().update("frmCrear:claseDescripcion");
        PrimeFaces.current().ajax().update("frmEditar:claseDescripcionedit");
        PrimeFaces.current().ajax().update("frmGestionar:claseDescripcionGestion");
        generarMensajes();
    }

    public void fechaActual() {
        fechaActual = new Date();
        sdf.format(fechaActual);
        objeto.setFechaRadicado(fechaActual);
        objeto.setFechaUltimaActuacion(fechaActual);
    }

    public void validarRangosDeFecha() {
        fechaActual = new Date();
        sdf.format(fechaActual);
        if (objeto.getFechaAdmision() != null) {
            if (objeto.getFechaAdmision().compareTo(fechaActual) > 0) {
                this.addError("No Tiene Permitido Seleccionar Un Rango De Fecha De Admision Mayor Al Actual");
            }
        }
        if (objeto.getFechaNotificacion() != null) {
            if (objeto.getFechaNotificacion().compareTo(fechaActual) > 0) {
                this.addError("No Tiene Permitido Seleccionar Un Rango De Fecha De Notificacion Mayor Al Actual");
            }
        }
        if (objeto.getGjProcesoAbogado().getFechaInicio() != null) {
            if (objeto.getGjProcesoAbogado().getFechaInicio().compareTo(fechaActual) > 0) {
                this.addError("No Tiene Permitido Seleccionar Un Rango De Fecha De Inicio Mayor Al Actual");
            }
        }
    }

    private List<SelectItem> listaClaseProvision;

    public List<SelectItem> getListaClaseProvision() {
        if (listaClaseProvision == null) {
            seleccionClaseProvision();
        }
        return listaClaseProvision;
    }

    public void setListaClaseProvision(List<SelectItem> listaClaseProvision) {
        this.listaClaseProvision = listaClaseProvision;
    }

    private List<SelectItem> listaClasificacionRiesgo;

    public void cargarListaClasificacionRiesgo() {
        listaClasificacionRiesgo = new ArrayList<>();
        if (objeto.getClaseProvision() == null) {
            listaClasificacionRiesgo.add(new SelectItem("0", " -- "));
            listaClasificacionRiesgo.add(new SelectItem("1", "Alto"));
            listaClasificacionRiesgo.add(new SelectItem("2", "Medio"));
            listaClasificacionRiesgo.add(new SelectItem("3", "Bajo"));
            listaClasificacionRiesgo.add(new SelectItem("4", "Remoto"));
            listaClasificacionRiesgo.add(new SelectItem("5", "Por Definir"));
        } else {
            listaClasificacionRiesgo.add(new SelectItem("0", " -- "));
            listaClasificacionRiesgo.add(new SelectItem("1", "Alto"));
            listaClasificacionRiesgo.add(new SelectItem("2", "Medio"));
            listaClasificacionRiesgo.add(new SelectItem("3", "Bajo"));
            listaClasificacionRiesgo.add(new SelectItem("4", "Remoto"));
            listaClasificacionRiesgo.add(new SelectItem("5", "Por Definir"));
        }
    }

    public List<SelectItem> getListaClasificacionRiesgo() {
        if (listaClasificacionRiesgo == null) {
            cargarListaClasificacionRiesgo();
        }
        return listaClasificacionRiesgo;
    }

    public void setListaClasificacionRiesgo(List<SelectItem> listaClasificacionRiesgo) {
        this.listaClasificacionRiesgo = listaClasificacionRiesgo;
    }

    public void seleccionClaseProvision() {
        listaClaseProvision = new ArrayList<>();
        Short valor = objeto.getRiesgoClasificacion();
        if (valor == null) {
            listaClaseProvision.add(new SelectItem("0", " -- "));
        } else {
            switch (valor) {
                case 0:
                    listaClaseProvision.add(new SelectItem("0", " -- "));
                    break;
                case 1:
                    listaClaseProvision.add(new SelectItem("1", "Provisión Contable"));
                    break;
                case 2:
                    listaClaseProvision.add(new SelectItem("2", "Cuentas De Orden"));
                    break;
                case 3:
                    listaClaseProvision.add(new SelectItem("2", "Cuentas De Orden"));
                    break;
                case 4:
                    listaClaseProvision.add(new SelectItem("3", "Sin Provision"));
                    break;
                case 5:
                    listaClaseProvision.add(new SelectItem("4", "Por Definir"));
                    break;

            }
        }
    }

    public void desactivarBoton() {
        visibleBotonAgregarTercero = false;
        visibleCalidadActua = true;
        PrimeFaces.current().ajax().update("frmCrear:panelCrearListaTercero");
    }

    public void ValidarEstado() {
        short estado = objeto.getEstado();
        if (estado == 4 || estado == 5) {

            requerirCampoSentidoSentencia = true;
            requerirCampoFechaTerminacion = true;
            requerirCampoValorSentenciaEjecutoria = true;
            requerirCampoActuacionTerminacion = true;
            requerirCampoValorAcuerdoT = true;
        } else {
            requerirCampoSentidoSentencia = false;
            requerirCampoFechaTerminacion = false;
            requerirCampoValorSentenciaEjecutoria = false;
            requerirCampoActuacionTerminacion = false;
            requerirCampoValorAcuerdoT = false;
        }
        PrimeFaces.current().ajax().update("frmGestionar:pProcesoGestionarmedidacautelar");
    }

    public void ValidarFechaVacia() {
        short est = 4;

        if (objeto.getFechaTerminacion() != null) {
            if (objeto.getEstado() != 4 || objeto.getEstado() != 5) {
                objeto.setEstado(est);
            }
            requerirCampoSentidoSentencia = true;
            requerirCampoFechaTerminacion = true;
            requerirCampoValorSentenciaEjecutoria = true;
            requerirCampoActuacionTerminacion = true;
            requerirCampoValorAcuerdoT = true;
        } else {
            requerirCampoSentidoSentencia = false;
            requerirCampoFechaTerminacion = false;
            requerirCampoValorSentenciaEjecutoria = false;
            requerirCampoActuacionTerminacion = false;
            requerirCampoValorAcuerdoT = false;
        }
        PrimeFaces.current().ajax().update("frmGestionar:pProcesoGestionarmedidacautelar");
        PrimeFaces.current().ajax().update("frmGestionar:estadooGestion");
    }

    public void borrarAdjuntoSMemoria(GjProcesoAdjunto anexSer) {
        if (anexSer != null && anexSer.getNombreArchivo() != null) {
            String rutaArchivo = anexSer.getRuta() + File.separator + anexSer.getNombreArchivo();
            File archivo = new File(rutaArchivo);
            if (archivo.exists()) {
                boolean eliminado = archivo.delete();
                if (eliminado) {
                    // Eliminar el elemento de la lista
                    int index = objeto.getGjProcesoAdjuntosList().indexOf(anexSer);
                    objeto.getGjProcesoAdjuntosList().remove(index);
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El archivo ha sido removido de la lista de adjuntos", "");
                    FacesContext.getCurrentInstance().addMessage(null, message);

                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al remover el archivo", "");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        }
    }

    public void fechaUltimaActual() {
        fechaActual = new Date();
        sdf.format(fechaActual);
        objeto.setFechaUltimaActuacion(fechaActual);
    }

    public void inizializarTerceros() {
        //Tercero
        lazyGjTercero = new LazyDataModel<GjTercero>() {
            private List<GjTercero> afiliados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaTercero().getCantidadRegistros();
            }

            @Override
            public List<GjTercero> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaTercero(new ParamConsulta());
                getParamConsultaTercero().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaTercero().setPrimerRegistro(primerRegistro);
                getParamConsultaTercero().setRegistrosPagina(registrosPagina);
                getParamConsultaTercero().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaTercero().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarTerceroAfiliado();
                afiliados = getRegistrosTerceros();
                setRowCount(getParamConsultaTercero().getCantidadRegistros());
                return afiliados;
            }

            @Override
            public String getRowKey(GjTercero afiliado) {
                return afiliado.getId().toString();
            }

            @Override
            public GjTercero getRowData(String afiliadoId) {
                Integer id = Integer.valueOf(afiliadoId);
                for (GjTercero afiliado : afiliados) {
                    if (id.equals(afiliado.getId())) {
                        return afiliado;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarLazyAbogado() {
        //Abogado
        lazyGjAbogado = new LazyDataModel<GjAbogado>() {
            private List<GjAbogado> abogados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaAbogado().getCantidadRegistros();
            }

            @Override
            public List<GjAbogado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaAbogado(new ParamConsulta());
                getParamConsultaAbogado().setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsultaAbogado().setPrimerRegistro(primerRegistro);
                getParamConsultaAbogado().setRegistrosPagina(registrosPagina);
                getParamConsultaAbogado().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaAbogado().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAbogadoAfiliado();
                abogados = getRegistrosAbogados();
                setRowCount(getParamConsultaAbogado().getCantidadRegistros());
                return abogados;
            }

            @Override
            public String getRowKey(GjAbogado abogado) {
                return abogado.getId().toString();
            }

            @Override
            public GjAbogado getRowData(String abogadoId) {
                Integer id = Integer.valueOf(abogadoId);
                for (GjAbogado afiliado : abogados) {
                    if (id.equals(afiliado.getId())) {
                        return afiliado;
                    }
                }
                return null;
            }
        };
    }

}
