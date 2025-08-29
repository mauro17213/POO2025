package com.saviasaludeps.savia.web.tutela.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.tutela.ReporteMemorial;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuDiagnostico;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.dominio.tutela.TuMemorialPersona;
import com.saviasaludeps.savia.dominio.tutela.TuPersona;
import com.saviasaludeps.savia.dominio.tutela.TuPersonaContacto;
import com.saviasaludeps.savia.dominio.tutela.TuRepresentante;
import com.saviasaludeps.savia.dominio.tutela.TuSeguimiento;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaItem;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaRespuesta;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.tutela.servicio.TuTutelaServicioIface;
import com.saviasaludeps.savia.web.tutela.utilidades.PropTutelasUsuario;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.GeneradorDocumento;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_VER;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public final class TuTutelaBean extends Url {

    public final static int TIPO_ENVIO_SEGUIMIENTO_ASIGNAR = 1;
    public final static int TIPO_ENVIO_SEGUIMIENTO_CIERRE_PARCIAL = 2;
    public final static int TIPO_ENVIO_SEGUIMIENTO_CIERRE_FINAL = 3;
    
    public final static String TIPO_ASIGNACION_ITEM_IPS = "1";
    public final static String TIPO_ASIGNACION_ITEM_EPS = "2";
    
    //Roles
    public final static int ROL_EXTERNOS_ID = 219;
    public final static int ROL_INTERNOS_ID = 220;

    private TuTutelaServicioIface tuTutelaServicio;
    private TuTutela objeto;
//    private AsegAfiliado afiliadoCompleto;
    private List<TuTutela> registros;
    private List<TuTutelaEstado> registrosEstados;
    private List<TuPersona> registroPersonas;
    private List<AuAnexo4> registrosAnexo4;
    private List<AuAnexo4Item> RegistrosAnexo4Item;
    private List<TuPersona> registrosHistorialPersona;
    private List<TuSeguimiento> registroSeguimiento;
    private LazyDataModel<TuTutela> lazyTuTutela;
    private LazyDataModel<TuTutelaEstado> lazyTuTutelaEstado;
    private LazyDataModel<TuSeguimiento> lazyTuTutelaSeguimiento;
    private LazyDataModel<TuPersona> lazyTuPersona;
    private LazyDataModel<CntPrestadorSede> lazySedes;
    private LazyDataModel<AuAnexo4> lazyAnexo4;
    private LazyDataModel<AuAnexo4Item> lazyAnexo4Items;
    private LazyDataModel<TuPersona> lazyHistorialPersona;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private List<Usuario> listaUsuarios;
    private HashMap<Integer, Usuario> hashUsuarios;
    private List<Maestro> listaEstadoProceso;
    private HashMap<Integer, Maestro> hashEstadoProceso;
    private List<Maestro> listaEstadoTutela;
    private HashMap<Integer, Maestro> hashEstadoTutela;
    private List<Maestro> listaEstadoFallo;
    private HashMap<Integer, Maestro> hashEstadoFallo;
    private List<Maestro> listaEstadoIntegridad;
    private HashMap<Integer, Maestro> hashEstadoIntegridad;
    private List<Maestro> listaEstadoEntregaSucesiva;
    private HashMap<Integer, Maestro> hashEstadoEntregaSucesiva;
    private List<Maestro> listaEstadoExoneracion;
    private HashMap<Integer, Maestro> hashEstadoExoneracion;
    private List<Maestro> listaMedidaProvisional;
    private HashMap<Integer, Maestro> hashMedidaProvisional;
    private List<Maestro> listaClaseSancion;
    private HashMap<Integer, Maestro> hashClaseSancion;
    private List<Maestro> listaSmlv;
    private HashMap<Integer, Maestro> hashSmlv;
    private List<Maestro> listaClaseArresto;
    private HashMap<Integer, Maestro> hashClaseArresto;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaSexo;
    private HashMap<Integer, Maestro> hashSexo;
    private List<Maestro> listaTipoEstadoPersona;
    private HashMap<Integer, Maestro> hashTipoEstadoPersona;
    private List<Maestro> listaTipoSeguimiento;
    private HashMap<Integer, Maestro> hashTipoSeguimiento;
    private List<TuRepresentante> listaUsuariosRepresentanteDemandado;
    private HashMap<Integer, TuRepresentante> hashUsuariosRepresentanteDemandado;
    private List<Maestro> listaTipoPresentacion;
    private HashMap<Integer, Maestro> hashTipoPresentacion;
    private List<Maestro> listaPresentacion;
    private HashMap<Integer, Maestro> hashPresentacion;
    private List<Maestro> listaTutelaCausa;
    private HashMap<Integer, Maestro> hashTutelaCausa;
    private List<TuGrupoUsuario> listaTuGrupoUsuarios;
    private List<TuPersonaContacto> listaTuPersonaContacto;

    private List<Maestro> listaArgumentos;
    private HashMap<Integer, Maestro> hashArgumentos;
    private List<Maestro> listaPretensiones;
    private HashMap<Integer, Maestro> hashPretensiones;
    private List<Maestro> listaReferencias;
    private HashMap<Integer, Maestro> hashReferencias;
    //2025-06-04 jyperez nueva lista maestro
    private List<Maestro> listaTipoServicio;
    private HashMap<Integer, Maestro> hashTipoServicio;
    //2025-06-27 jyperez nueva listas maestros
    private List<Maestro> listaTipoAsignacion;
    private HashMap<Integer, Maestro> hashTipoAsignacion;
    private List<Maestro> listaEstadoSolicitud;
    private HashMap<Integer, Maestro> hashEstadoSolicitud;
    private HashMap<String, Maestro> hashValorEstadoSolicitud;
    private List<Maestro> listaTiposDocumentoEmpresa;

    private List<TuGrupo> listaGrupos;
    private List<TuJuzgado> listaJuzgados;
    private List<Usuario> listaMedicos;
    private List<Usuario> listaAbogados;
    private List<Usuario> listaGestores;
    private List<TuGrupoUsuario> listaUsuarioGrupos;
    private HashMap<Integer, TuJuzgado> hashJuzgados;

    private List<TuTutelaEstado> listaServiciosCrear;
    private List<TuTutelaItem> listaTutelaEstadoItem;
    private List<CntPrestadorSede> ListaPrestadorSedes;

    private List<TuMemorialPersona> listApoderados;
    private List<TuMemorialPersona> listAsistentes;

    private List<TuAdjunto> listaAdjuntosServicio;

    private TuTutelaEstado objEstados;
    private TuJuzgado objJuzgado;
    private TuPersona objPersona;
    private TuTutelaItem objTutulaItem;
    private TuSeguimiento objEstadosSeguimiento;
    private AuAnexo4 cmAuditoriaAutorizacion;
    private SelDiagnosticosBean diagnosticosBean;
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;
    private TuPersonaContacto tuPersonaContacto;
    private TuTutelaEstado modicarResponsable;
    private TuDiagnostico cmAuditoriaDiagnostico;
    private TuAdjunto borrarAdjunto;
    private AuAnexo4 auAnexo4;
    private ReporteMemorial documentoMemorial;
    //

    private boolean deshabilitarCampoEstadoInicialFallo;
    private boolean deshabilitarCampoEstadoInicialImpugnacion;
    private boolean deshabilitarCampoEstadoInicialIntegralidad;
    //2025-04-08 jyperez creación variable maneno obligatoriedad campo Integralidad
    private boolean requeridoCampoEstadoIInicialIntegralidad;
    private boolean deshabilitarCampoEstadoInicialEntregaSucesiva;
    private boolean deshabilitarCampoEstadoInicialExoneracion;
    private boolean deshabilitarCampoEstadoInicialMedidaProvisional;
    private boolean deshabilitarCampoEstadoInicialClaseSancion;
    private boolean deshabilitarCampoEstadoInicialSMLV;
    private boolean deshabilitarCampoEstadoInicialUVT;
    private boolean deshabilitarCampoEstadoInicialClaseArresto;
    private boolean deshabilitarCampoEstadoInicialDiasArresto;
    private boolean deshabilitarCampoEstadoInicialJuzgado;
    private boolean deshabilitarCampoEstadoInicialRepresentanteDemandado;
    private boolean deshabilitarCampoEstadoInicialGrupo;
    private boolean habilitarPanelItem;
    
    private boolean campoObligatotioFallo;
    private boolean campoObligatorioMedidaProvisional;
    private boolean campoObligatotioClaseSansion;
    private boolean campoObligatotioSmlv;
    private boolean campoObligatotioUVT;
    private boolean campoObligatotioClaseArresto;
    private boolean campoObligatotioDiasArresto;
    private boolean campoObligatotioRepresentanteDemandado;
    private boolean crearOeditarItem;
    private boolean bloquearTipoReparto;
    private int contadorArchivos = 0;
    private int sizeLimitByAnexo;
    private int maxCantAnexos;
    private int idTipoSeguimientoEnviar;
    private Date fechaActual;

    private Integer idDiagostigoMemoria;
    private TuDiagnostico editarDiagnostico;
    private Integer identificarEditarGestionDiaganostico;
    
    private boolean campoObligatorioItem;
    //2025-07-15 jyperez campo para mensaje emergente
    private String descripcionGenerica;
    //para tecnologia no aplica
    public static final int ID_TECNOLOGIA_POR_DEFECTO = 101010101;
    public static final String VALOR_TECNOLOGIA_POR_DEFECTO = "NO APLICA";

    public static final char ACCION_LISTAR_ESTADOS = 'A';
    public static final char VER_ESTADOS = 'B';
    public static final char VER_ITEMS = 'C';
    public static final char VER_SEGUIMIENTO = 'D';
    public static final char EDITAR_TUTELA_ESTADO = 'e';
    public static final char ACCION_MODIFICAR_GESTION = 'f';
    public static final char ACCION_BUSCAR_SEDES = 'g';
    public static final char ACCION_GUARDAR_ESTADO_ADICIONAL = 'h';
    public static final char EDITAR_TUTELA_ESTADOS_ITEM = 'i';
    public static final char EDITAR_TUTELA_ESTADOS_SEGUIMIENTO = 'j';
    public static final char MODIFICAR_TUTELA_ESTADO_SEGUIMIENTO = 'k';
    public static final char MODIFICAR_TUTELA_ESTADO = 'l';
    public static final char BORRAR_SEGUIMIENTO = 'm';
    public static final char BORRAR_ITEM = 'n';
    public static final char BORRAR_ESTADO = 'p';
    public static final char MODIFICAR_TUTELA_ESTADO_ITEM = 'o';
    public static final char MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO = 'q';
    public static final char ACCION_VER_ENVIOS_SEGUIMIENTO = 'r';
    public static final char ACCION_EDITAR_GESTION = 'o';
    public static final char ACCION_GUARDAR_ESTADO_ITEM = 'p';
    public static final char ACCION_GUARDAR_ESTADO_SEGUIMIENTO = 'q';
    public static final char ACCION_LISTAR_HISTORIAL_PERSONA = 't';
    public static final char ACCION_LISTAR_CONTACTOS_PERSONAS = 'v';
    public static final char ACCION_BORRAR_CONTACTOS_PERSONAS = 'u';
    public static final char ACCION_LISTAR_SEGUIMIENTO = 'w';
    public static final char ACCION_MODIFICAR_RESPONSABLE_ESTADO_ACTUAL = 'x';
    public static final char ACCION_EDITAR_RESPONSABLE = 'y';
    public static final char BORRAR_DIAGNOSTICO = 'z';
    public static final char BORRAR_ADJUNTO = 'F';
    public static final char ACCION_LISTAR_ANEXOS4_ITEMS = 'G';
    public static final char BORRAR_AUTORIZACION = 'H';
    public static final char DESCARGAR_DOCUMENTO_MEMORIAL = 'I';
    public static final char ACCION_LISTAR_ANEXOS_4 = 'I';
    //public static final String SESION_ADJUNTO_SERVICIOS = "ajuntoServicios";

    public TuTutelaBean() {
        this.objeto = new TuTutela();
        this.objTutulaItem = new TuTutelaItem();
        this.objEstadosSeguimiento = new TuSeguimiento();
        this.fechaActual = new Date();
        setMaxCantAnexos(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.MAXIMO_CANTIDAD_ANEXOS)));
        setSizeLimitByAnexo(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.MAXIMO_TAMANO_ANEXO)));
        Modulo mod = super.validarModulo(Modulo.ID_TU_TUTELAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta()); // revisar si se esta utilizando el lzay personas
            super.addListaParamConsultas(new ParamConsulta()); // revisar consulta de grupos
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            lazyTuTutela = new LazyDataModel<TuTutela>() {
                private List<TuTutela> tuTutela;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<TuTutela> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    tuTutela = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return tuTutela;
                }

                @Override
                public String getRowKey(TuTutela TuTutela) {
                    return TuTutela.getId().toString();
                }

                @Override
                public TuTutela getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (TuTutela tuTutela : tuTutela) {
                        if (id.equals(tuTutela.getId())) {
                            return tuTutela;
                        }
                    }
                    return null;
                }
            };

            lazySedes = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaSedes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(4).getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(4).setPrimerRegistro(primerRegistro);
                    getParamConsulta(4).setRegistrosPagina(registrosPagina);
                    getParamConsulta(4).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(4).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarSedes();
                    listaSedes = getListaPrestadorSedes();
                    setRowCount(getParamConsulta(4).getCantidadRegistros());
                    return listaSedes;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntPrestadorSede objeto : listaSedes) {
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
        getTuTutelaServicio().cargaInial(this);
        listar();
    }

    public TuTutela getObjeto() {
        return objeto;
    }

    public void setObjeto(TuTutela objeto) {
        this.objeto = objeto;
    }

    public List<TuTutela> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TuTutela> registros) {
        this.registros = registros;
    }

    public List<TuPersona> getRegistroPersonas() {
        return registroPersonas;
    }

    public void setRegistroPersonas(List<TuPersona> registroPersonas) {
        this.registroPersonas = registroPersonas;
    }

    public List<AuAnexo4> getRegistrosAnexo4() {
        return registrosAnexo4;
    }

    public void setRegistrosAnexo4(List<AuAnexo4> registrosAnexo4) {
        this.registrosAnexo4 = registrosAnexo4;
    }

    public ReporteMemorial getDocumentoMemorial() {
        if (documentoMemorial == null) {
            documentoMemorial = new ReporteMemorial();
        }
        return documentoMemorial;
    }

    public void setDocumentoMemorial(ReporteMemorial documentoMemorial) {
        this.documentoMemorial = documentoMemorial;
    }

    public List<AuAnexo4Item> getRegistrosAnexo4Item() {
        return RegistrosAnexo4Item;
    }

    public void setRegistrosAnexo4Item(List<AuAnexo4Item> RegistrosAnexo4Item) {
        this.RegistrosAnexo4Item = RegistrosAnexo4Item;
    }

    public TuTutelaEstado getObjEstados() {
        if (objEstados == null) {
            objEstados = new TuTutelaEstado();
        }
        return objEstados;
    }

    public void setObjEstados(TuTutelaEstado objEstados) {
        this.objEstados = objEstados;
    }

    public TuPersona getObjPersona() {
        if (objPersona == null) {
//            objPersona = new TuPersona();
        }
        return objPersona;
    }

    public void setObjPersona(TuPersona objPersona) {
        this.objPersona = objPersona;
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

    public LazyDataModel<TuTutela> getLazyTuTutela() {
        return lazyTuTutela;
    }

    public void setLazyTuTutela(LazyDataModel<TuTutela> lazyTuTutela) {
        this.lazyTuTutela = lazyTuTutela;
    }

    public LazyDataModel<TuTutelaEstado> getLazyTuTutelaEstado() {
        return lazyTuTutelaEstado;
    }

    public void setLazyTuTutelaEstado(LazyDataModel<TuTutelaEstado> lazyTuTutelaEstado) {
        this.lazyTuTutelaEstado = lazyTuTutelaEstado;
    }

    public LazyDataModel<TuSeguimiento> getLazyTuTutelaSeguimiento() {
        return lazyTuTutelaSeguimiento;
    }

    public void setLazyTuTutelaSeguimiento(LazyDataModel<TuSeguimiento> lazyTuTutelaSeguimiento) {
        this.lazyTuTutelaSeguimiento = lazyTuTutelaSeguimiento;
    }

    public LazyDataModel<TuPersona> getLazyTuPersona() {
        return lazyTuPersona;
    }

    public void setLazyTuPersona(LazyDataModel<TuPersona> lazyTuPersona) {
        this.lazyTuPersona = lazyTuPersona;
    }

    public LazyDataModel<CntPrestadorSede> getLazySedes() {
        return lazySedes;
    }

    public void setLazySedes(LazyDataModel<CntPrestadorSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    public LazyDataModel<AuAnexo4> getLazyAnexo4() {
        return lazyAnexo4;
    }

    public void setLazyAnexo4(LazyDataModel<AuAnexo4> lazyAnexo4) {
        this.lazyAnexo4 = lazyAnexo4;
    }

    public LazyDataModel<AuAnexo4Item> getLazyAnexo4Items() {
        return lazyAnexo4Items;
    }

    public void setLazyAnexo4Items(LazyDataModel<AuAnexo4Item> lazyAnexo4Items) {
        this.lazyAnexo4Items = lazyAnexo4Items;
    }

    public LazyDataModel<TuPersona> getLazyHistorialPersona() {
        return lazyHistorialPersona;
    }

    public List<TuTutelaEstado> getRegistrosEstados() {
        if (registrosEstados == null) {
            registrosEstados = new ArrayList<>();
        }
        return registrosEstados;
    }

    public void setRegistrosEstados(List<TuTutelaEstado> registrosAuditoriaDetalles) {
        this.registrosEstados = registrosAuditoriaDetalles;
    }

    public List<TuPersona> getRegistrosHistorialPersona() {
        if (registrosHistorialPersona == null) {
            registrosHistorialPersona = new ArrayList<>();
        }
        return registrosHistorialPersona;
    }

    public void setRegistrosHistorialPersona(List<TuPersona> registrosHistorialPersona) {
        this.registrosHistorialPersona = registrosHistorialPersona;
    }

    public List<TuSeguimiento> getRegistroSeguimiento() {
        if (registroSeguimiento == null) {
            registroSeguimiento = new ArrayList<>();
        }
        return registroSeguimiento;
    }

    public void setRegistroSeguimiento(List<TuSeguimiento> registroSeguimiento) {
        this.registroSeguimiento = registroSeguimiento;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public HashMap<Integer, Usuario> getHashUsuarios() {
        return hashUsuarios;
    }

    public void setHashUsuarios(HashMap<Integer, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    public List<Maestro> getListaEstadoProceso() {
        return listaEstadoProceso;
    }

    public void setListaEstadoProceso(List<Maestro> listaEstadoProceso) {
        this.listaEstadoProceso = listaEstadoProceso;
    }

    public HashMap<Integer, Maestro> getHashEstadoProceso() {
        return hashEstadoProceso;
    }

    public void setHashEstadoProceso(HashMap<Integer, Maestro> hashEstadoProceso) {
        this.hashEstadoProceso = hashEstadoProceso;
    }

    public List<Maestro> getListaEstadoTutela() {
        return listaEstadoTutela;
    }

    public void setListaEstadoTutela(List<Maestro> listaEstadoTutela) {
        this.listaEstadoTutela = listaEstadoTutela;
    }

    public HashMap<Integer, Maestro> getHashEstadoTutela() {
        return hashEstadoTutela;
    }

    public void setHashEstadoTutela(HashMap<Integer, Maestro> hashEstadoTutela) {
        this.hashEstadoTutela = hashEstadoTutela;
    }

    public List<Maestro> getListaEstadoFallo() {
        return listaEstadoFallo;
    }

    public void setListaEstadoFallo(List<Maestro> listaEstadoFallo) {
        this.listaEstadoFallo = listaEstadoFallo;
    }

    public HashMap<Integer, Maestro> getHashEstadoFallo() {
        return hashEstadoFallo;
    }

    public void setHashEstadoFallo(HashMap<Integer, Maestro> hashEstadoFallo) {
        this.hashEstadoFallo = hashEstadoFallo;
    }

    public List<Maestro> getListaEstadoIntegridad() {
        return listaEstadoIntegridad;
    }

    public void setListaEstadoIntegridad(List<Maestro> listaEstadoIntegridad) {
        this.listaEstadoIntegridad = listaEstadoIntegridad;
    }

    public HashMap<Integer, Maestro> getHashEstadoIntegridad() {
        return hashEstadoIntegridad;
    }

    public void setHashEstadoIntegridad(HashMap<Integer, Maestro> hashEstadoIntegridad) {
        this.hashEstadoIntegridad = hashEstadoIntegridad;
    }

    public List<Maestro> getListaEstadoEntregaSucesiva() {
        return listaEstadoEntregaSucesiva;
    }

    public void setListaEstadoEntregaSucesiva(List<Maestro> listaEstadoEntregaSucesiva) {
        this.listaEstadoEntregaSucesiva = listaEstadoEntregaSucesiva;
    }

    public HashMap<Integer, Maestro> getHashEstadoEntregaSucesiva() {
        return hashEstadoEntregaSucesiva;
    }

    public void setHashEstadoEntregaSucesiva(HashMap<Integer, Maestro> hashEstadoEntregaSucesiva) {
        this.hashEstadoEntregaSucesiva = hashEstadoEntregaSucesiva;
    }

    public List<Maestro> getListaEstadoExoneracion() {
        return listaEstadoExoneracion;
    }

    public void setListaEstadoExoneracion(List<Maestro> listaEstadoExoneracion) {
        this.listaEstadoExoneracion = listaEstadoExoneracion;
    }

    public HashMap<Integer, Maestro> getHashEstadoExoneracion() {
        return hashEstadoExoneracion;
    }

    public void setHashEstadoExoneracion(HashMap<Integer, Maestro> hashEstadoExoneracion) {
        this.hashEstadoExoneracion = hashEstadoExoneracion;
    }

    public List<Maestro> getListaMedidaProvisional() {
        return listaMedidaProvisional;
    }

    public void setListaMedidaProvisional(List<Maestro> listaMedidaProvisional) {
        this.listaMedidaProvisional = listaMedidaProvisional;
    }

    public HashMap<Integer, Maestro> getHashMedidaProvisional() {
        return hashMedidaProvisional;
    }

    public void setHashMedidaProvisional(HashMap<Integer, Maestro> hashMedidaProvisional) {
        this.hashMedidaProvisional = hashMedidaProvisional;
    }

    public List<Maestro> getListaClaseSancion() {
        return listaClaseSancion;
    }

    public void setListaClaseSancion(List<Maestro> listaClaseSancion) {
        this.listaClaseSancion = listaClaseSancion;
    }

    public HashMap<Integer, Maestro> getHashClaseSancion() {
        return hashClaseSancion;
    }

    public void setHashClaseSancion(HashMap<Integer, Maestro> hashClaseSancion) {
        this.hashClaseSancion = hashClaseSancion;
    }

    public List<Maestro> getListaSmlv() {
        return listaSmlv;
    }

    public void setListaSmlv(List<Maestro> listaSmlv) {
        this.listaSmlv = listaSmlv;
    }

    public HashMap<Integer, Maestro> getHashSmlv() {
        return hashSmlv;
    }

    public void setHashSmlv(HashMap<Integer, Maestro> hashSmlv) {
        this.hashSmlv = hashSmlv;
    }

    public List<Maestro> getListaClaseArresto() {
        return listaClaseArresto;
    }

    public void setListaClaseArresto(List<Maestro> listaClaseArresto) {
        this.listaClaseArresto = listaClaseArresto;
    }

    public HashMap<Integer, Maestro> getHashClaseArresto() {
        return hashClaseArresto;
    }

    public void setHashClaseArresto(HashMap<Integer, Maestro> hashClaseArresto) {
        this.hashClaseArresto = hashClaseArresto;
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

    public List<Maestro> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<Maestro> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public HashMap<Integer, Maestro> getHashSexo() {
        return hashSexo;
    }

    public void setHashSexo(HashMap<Integer, Maestro> hashSexo) {
        this.hashSexo = hashSexo;
    }

    public List<Maestro> getListaTipoEstadoPersona() {
        return listaTipoEstadoPersona;
    }

    public void setListaTipoEstadoPersona(List<Maestro> listaTipoEstadoPersona) {
        this.listaTipoEstadoPersona = listaTipoEstadoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadoPersona() {
        return hashTipoEstadoPersona;
    }

    public void setHashTipoEstadoPersona(HashMap<Integer, Maestro> hashTipoEstadoPersona) {
        this.hashTipoEstadoPersona = hashTipoEstadoPersona;
    }

    public List<Maestro> getListaTipoSeguimiento() {
        return listaTipoSeguimiento;
    }

    public void setListaTipoSeguimiento(List<Maestro> listaTipoSeguimiento) {
        this.listaTipoSeguimiento = listaTipoSeguimiento;
    }

    public HashMap<Integer, Maestro> getHashTipoSeguimiento() {
        return hashTipoSeguimiento;
    }

    public void setHashTipoSeguimiento(HashMap<Integer, Maestro> hashTipoSeguimiento) {
        this.hashTipoSeguimiento = hashTipoSeguimiento;
    }

    public List<TuRepresentante> getListaUsuariosRepresentanteDemandado() {
        return listaUsuariosRepresentanteDemandado;
    }

    public void setListaUsuariosRepresentanteDemandado(List<TuRepresentante> listaUsuariosRepresentanteDemandado) {
        this.listaUsuariosRepresentanteDemandado = listaUsuariosRepresentanteDemandado;
    }

    public HashMap<Integer, TuRepresentante> getHashUsuariosRepresentanteDemandado() {
        return hashUsuariosRepresentanteDemandado;
    }

    public void setHashUsuariosRepresentanteDemandado(HashMap<Integer, TuRepresentante> hashUsuariosRepresentanteDemandado) {
        this.hashUsuariosRepresentanteDemandado = hashUsuariosRepresentanteDemandado;
    }

    public List<Maestro> getListaTipoPresentacion() {
        return listaTipoPresentacion;
    }

    public void setListaTipoPresentacion(List<Maestro> listaTipoPresentacion) {
        this.listaTipoPresentacion = listaTipoPresentacion;
    }

    public HashMap<Integer, Maestro> getHashTipoPresentacion() {
        return hashTipoPresentacion;
    }

    public void setHashTipoPresentacion(HashMap<Integer, Maestro> hashTipoPresentacion) {
        this.hashTipoPresentacion = hashTipoPresentacion;
    }

    public List<Maestro> getListaPresentacion() {
        return listaPresentacion;
    }

    public void setListaPresentacion(List<Maestro> listaPresentacion) {
        this.listaPresentacion = listaPresentacion;
    }

    public HashMap<Integer, Maestro> getHashPresentacion() {
        return hashPresentacion;
    }

    public void setHashPresentacion(HashMap<Integer, Maestro> hashPresentacion) {
        this.hashPresentacion = hashPresentacion;
    }

    public List<Maestro> getListaTutelaCausa() {
        return listaTutelaCausa;
    }

    public void setListaTutelaCausa(List<Maestro> listaTutelaCausa) {
        this.listaTutelaCausa = listaTutelaCausa;
    }

    public HashMap<Integer, Maestro> getHashTutelaCausa() {
        return hashTutelaCausa;
    }

    public void setHashTutelaCausa(HashMap<Integer, Maestro> hashTutelaCausa) {
        this.hashTutelaCausa = hashTutelaCausa;
    }

    public List<TuGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public List<TuJuzgado> getListaJuzgados() {
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

    public List<Usuario> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<Usuario> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    public List<Usuario> getListaAbogados() {
        return listaAbogados;
    }

    public void setListaAbogados(List<Usuario> listaAbogados) {
        this.listaAbogados = listaAbogados;
    }

    public List<Usuario> getListaGestores() {
        return listaGestores;
    }

    public void setListaGestores(List<Usuario> listaGestores) {
        this.listaGestores = listaGestores;
    }

    public void setListaGrupos(List<TuGrupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public TuTutelaServicioIface getTuTutelaServicio() {
        return tuTutelaServicio;
    }

    public void setTuTutelaServicio(TuTutelaServicioIface tuTutelaServicio) {
        this.tuTutelaServicio = tuTutelaServicio;
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

    public static Object getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }

    public void setParamConsultaEstado(List<TuTutelaEstado> registrosEstados) {
        this.registrosEstados = registrosEstados;
    }

    public List<TuGrupoUsuario> getListaTuGrupoUsuarios() {
        return listaTuGrupoUsuarios;
    }

    public void setListaTuGrupoUsuarios(List<TuGrupoUsuario> listaTuGrupoUsuarios) {
        this.listaTuGrupoUsuarios = listaTuGrupoUsuarios;
    }

    /*public ParamConsulta getParamConsultaRepresentanteDemandado() {
        if (paramConsultaRepresentanteDemandado == null) {
            paramConsultaRepresentanteDemandado = new ParamConsulta();
            paramConsultaRepresentanteDemandado.setRegistrosPagina(super.getTamanoPagina());
            if (getParamConsultaRepresentanteDemandado().getFiltros() == null) {
                getParamConsultaRepresentanteDemandado().setFiltros(new HashMap<>());
            }
            getParamConsulta(5).getFiltros().put("activo", "true");
        }
        return paramConsultaRepresentanteDemandado;
    }*/
    public TuTutelaItem getObjTutulaItem() {
        return objTutulaItem;
    }

    public void setObjTutulaItem(TuTutelaItem objTutulaItem) {
        this.objTutulaItem = objTutulaItem;
    }

    public TuSeguimiento getObjEstadosSeguimiento() {
        return objEstadosSeguimiento;
    }

    public void setObjEstadosSeguimiento(TuSeguimiento objEstadosSeguimiento) {
        this.objEstadosSeguimiento = objEstadosSeguimiento;
    }

    public AuAnexo4 getCmAuditoriaAutorizacion() {
        if (cmAuditoriaAutorizacion == null) {
            cmAuditoriaAutorizacion = new AuAnexo4();
        }
        return cmAuditoriaAutorizacion;
    }

    public void setCmAuditoriaAutorizacion(AuAnexo4 cmAuditoriaAutorizacion) {
        this.cmAuditoriaAutorizacion = cmAuditoriaAutorizacion;
    }

    public List<TuPersonaContacto> getListaTuPersonaContacto() {
        if (listaTuPersonaContacto == null) {
            listaTuPersonaContacto = new ArrayList<>();
        }
        return listaTuPersonaContacto;
    }

    public void setListaTuPersonaContacto(List<TuPersonaContacto> listaTuPersonaContacto) {
        this.listaTuPersonaContacto = listaTuPersonaContacto;
    }

    public List<Maestro> getListaArgumentos() {
        return listaArgumentos;
    }

    public void setListaArgumentos(List<Maestro> listaArgumentos) {
        this.listaArgumentos = listaArgumentos;
    }

    public HashMap<Integer, Maestro> getHashArgumentos() {
        return hashArgumentos;
    }

    public void setHashArgumentos(HashMap<Integer, Maestro> hashArgumentos) {
        this.hashArgumentos = hashArgumentos;
    }

    public List<Maestro> getListaPretensiones() {
        return listaPretensiones;
    }

    public void setListaPretensiones(List<Maestro> listaPretensiones) {
        this.listaPretensiones = listaPretensiones;
    }

    public HashMap<Integer, Maestro> getHashPretensiones() {
        return hashPretensiones;
    }

    public void setHashPretensiones(HashMap<Integer, Maestro> hashPretensiones) {
        this.hashPretensiones = hashPretensiones;
    }

    public List<Maestro> getListaReferencias() {
        return listaReferencias;
    }

    public void setListaReferencias(List<Maestro> listaReferencias) {
        this.listaReferencias = listaReferencias;
    }

    public HashMap<Integer, Maestro> getHashReferencias() {
        return hashReferencias;
    }

    public void setHashReferencias(HashMap<Integer, Maestro> hashReferencias) {
        this.hashReferencias = hashReferencias;
    }

    public TuPersonaContacto getTuPersonaContacto() {
        if (tuPersonaContacto == null) {
            tuPersonaContacto = new TuPersonaContacto();
        }
        return tuPersonaContacto;
    }

    public void setTuPersonaContacto(TuPersonaContacto tuPersonaContacto) {
        this.tuPersonaContacto = tuPersonaContacto;
    }

    public List<TuGrupoUsuario> getListaUsuarioGrupos() {
        return listaUsuarioGrupos;
    }

    public void setListaUsuarioGrupos(List<TuGrupoUsuario> listaUsuarioGrupos) {
        this.listaUsuarioGrupos = listaUsuarioGrupos;
    }

    public TuTutelaEstado getModicarResponsable() {
        if (modicarResponsable == null) {
            modicarResponsable = new TuTutelaEstado();
        }
        return modicarResponsable;
    }

    public TuDiagnostico getCmAuditoriaDiagnostico() {
        if (cmAuditoriaDiagnostico == null) {
            cmAuditoriaDiagnostico = new TuDiagnostico();
        }
        return cmAuditoriaDiagnostico;
    }

    public void setCmAuditoriaDiagnostico(TuDiagnostico cmAuditoriaDiagnostico) {
        this.cmAuditoriaDiagnostico = cmAuditoriaDiagnostico;
    }

    public TuAdjunto getBorrarAdjunto() {
        if (borrarAdjunto == null) {
            borrarAdjunto = new TuAdjunto();
        }
        return borrarAdjunto;
    }

    public void setBorrarAdjunto(TuAdjunto borrarAdjunto) {
        this.borrarAdjunto = borrarAdjunto;
    }

    public AuAnexo4 getAuAnexo4() {
        if (auAnexo4 == null) {
            auAnexo4 = new AuAnexo4();
        }
        return auAnexo4;
    }

    public void setAuAnexo4(AuAnexo4 auAnexo4) {
        this.auAnexo4 = auAnexo4;
    }

    public void setModicarResponsable(TuTutelaEstado modicarResponsable) {
        this.modicarResponsable = modicarResponsable;
    }

    public SelDiagnosticosBean getDiagnosticosBean() {
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

    public boolean isDeshabilitarCampoEstadoInicialFallo() {
        return deshabilitarCampoEstadoInicialFallo;
    }

    public void setDeshabilitarCampoEstadoInicialFallo(boolean deshabilitarCampoEstadoInicialFallo) {
        this.deshabilitarCampoEstadoInicialFallo = deshabilitarCampoEstadoInicialFallo;
    }

    public boolean isDeshabilitarCampoEstadoInicialImpugnacion() {
        return deshabilitarCampoEstadoInicialImpugnacion;
    }

    public void setDeshabilitarCampoEstadoInicialImpugnacion(boolean deshabilitarCampoEstadoInicialImpugnacion) {
        this.deshabilitarCampoEstadoInicialImpugnacion = deshabilitarCampoEstadoInicialImpugnacion;
    }

    public boolean isDeshabilitarCampoEstadoInicialIntegralidad() {
        return deshabilitarCampoEstadoInicialIntegralidad;
    }

    public void setDeshabilitarCampoEstadoInicialIntegralidad(boolean deshabilitarCampoEstadoInicialIntegralidad) {
        this.deshabilitarCampoEstadoInicialIntegralidad = deshabilitarCampoEstadoInicialIntegralidad;
    }

    public boolean isDeshabilitarCampoEstadoInicialEntregaSucesiva() {
        return deshabilitarCampoEstadoInicialEntregaSucesiva;
    }

    public void setDeshabilitarCampoEstadoInicialEntregaSucesiva(boolean deshabilitarCampoEstadoInicialEntregaSucesiva) {
        this.deshabilitarCampoEstadoInicialEntregaSucesiva = deshabilitarCampoEstadoInicialEntregaSucesiva;
    }

    public boolean isDeshabilitarCampoEstadoInicialExoneracion() {
        return deshabilitarCampoEstadoInicialExoneracion;
    }

    public void setDeshabilitarCampoEstadoInicialExoneracion(boolean deshabilitarCampoEstadoInicialExoneracion) {
        this.deshabilitarCampoEstadoInicialExoneracion = deshabilitarCampoEstadoInicialExoneracion;
    }

    public boolean isDeshabilitarCampoEstadoInicialMedidaProvisional() {
        return deshabilitarCampoEstadoInicialMedidaProvisional;
    }

    public void setDeshabilitarCampoEstadoInicialMedidaProvisional(boolean deshabilitarCampoEstadoInicialMedidaProvisional) {
        this.deshabilitarCampoEstadoInicialMedidaProvisional = deshabilitarCampoEstadoInicialMedidaProvisional;
    }

    public boolean isDeshabilitarCampoEstadoInicialClaseSancion() {
        return deshabilitarCampoEstadoInicialClaseSancion;
    }

    public void setDeshabilitarCampoEstadoInicialClaseSancion(boolean deshabilitarCampoEstadoInicialClaseSancion) {
        this.deshabilitarCampoEstadoInicialClaseSancion = deshabilitarCampoEstadoInicialClaseSancion;
    }

    public boolean isDeshabilitarCampoEstadoInicialSMLV() {
        return deshabilitarCampoEstadoInicialSMLV;
    }

    public void setDeshabilitarCampoEstadoInicialSMLV(boolean deshabilitarCampoEstadoInicialSMLV) {
        this.deshabilitarCampoEstadoInicialSMLV = deshabilitarCampoEstadoInicialSMLV;
    }

    public boolean isDeshabilitarCampoEstadoInicialUVT() {
        return deshabilitarCampoEstadoInicialUVT;
    }

    public void setDeshabilitarCampoEstadoInicialUVT(boolean deshabilitarCampoEstadoInicialUVT) {
        this.deshabilitarCampoEstadoInicialUVT = deshabilitarCampoEstadoInicialUVT;
    }

    public boolean isDeshabilitarCampoEstadoInicialClaseArresto() {
        return deshabilitarCampoEstadoInicialClaseArresto;
    }

    public void setDeshabilitarCampoEstadoInicialClaseArresto(boolean deshabilitarCampoEstadoInicialClaseArresto) {
        this.deshabilitarCampoEstadoInicialClaseArresto = deshabilitarCampoEstadoInicialClaseArresto;
    }

    public boolean isDeshabilitarCampoEstadoInicialDiasArresto() {
        return deshabilitarCampoEstadoInicialDiasArresto;
    }

    public void setDeshabilitarCampoEstadoInicialDiasArresto(boolean deshabilitarCampoEstadoInicialDiasArresto) {
        this.deshabilitarCampoEstadoInicialDiasArresto = deshabilitarCampoEstadoInicialDiasArresto;
    }

    public boolean isDeshabilitarCampoEstadoInicialJuzgado() {
        return deshabilitarCampoEstadoInicialJuzgado;
    }

    public void setDeshabilitarCampoEstadoInicialJuzgado(boolean deshabilitarCampoEstadoInicialJuzgado) {
        this.deshabilitarCampoEstadoInicialJuzgado = deshabilitarCampoEstadoInicialJuzgado;
    }

    public boolean isDeshabilitarCampoEstadoInicialRepresentanteDemandado() {
        return deshabilitarCampoEstadoInicialRepresentanteDemandado;
    }

    public void setDeshabilitarCampoEstadoInicialRepresentanteDemandado(boolean deshabilitarCampoEstadoInicialRepresentanteDemandado) {
        this.deshabilitarCampoEstadoInicialRepresentanteDemandado = deshabilitarCampoEstadoInicialRepresentanteDemandado;
    }

    public boolean isDeshabilitarCampoEstadoInicialGrupo() {
        return deshabilitarCampoEstadoInicialGrupo;
    }

    public void setDeshabilitarCampoEstadoInicialGrupo(boolean deshabilitarCampoEstadoInicialGrupo) {
        this.deshabilitarCampoEstadoInicialGrupo = deshabilitarCampoEstadoInicialGrupo;
    }

    public boolean isCampoObligatotioFallo() {
        return campoObligatotioFallo;
    }

    public void setCampoObligatotioFallo(boolean campoObligatotioFallo) {
        this.campoObligatotioFallo = campoObligatotioFallo;
    }

    public boolean isCampoObligatorioMedidaProvisional() {
        return campoObligatorioMedidaProvisional;
    }

    public void setCampoObligatorioMedidaProvisional(boolean campoObligatorioMedidaProvisional) {
        this.campoObligatorioMedidaProvisional = campoObligatorioMedidaProvisional;
    }

    public boolean isCampoObligatotioClaseSansion() {
        return campoObligatotioClaseSansion;
    }

    public void setCampoObligatotioClaseSansion(boolean campoObligatotioClaseSansion) {
        this.campoObligatotioClaseSansion = campoObligatotioClaseSansion;
    }

    public boolean isCampoObligatotioSmlv() {
        return campoObligatotioSmlv;
    }

    public void setCampoObligatotioSmlv(boolean campoObligatotioSmlv) {
        this.campoObligatotioSmlv = campoObligatotioSmlv;
    }

    public boolean isCampoObligatotioUVT() {
        return campoObligatotioUVT;
    }

    public void setCampoObligatotioUVT(boolean campoObligatotioUVT) {
        this.campoObligatotioUVT = campoObligatotioUVT;
    }

    public boolean isCampoObligatotioClaseArresto() {
        return campoObligatotioClaseArresto;
    }

    public void setCampoObligatotioClaseArresto(boolean campoObligatotioClaseArresto) {
        this.campoObligatotioClaseArresto = campoObligatotioClaseArresto;
    }

    public boolean isCampoObligatotioDiasArresto() {
        return campoObligatotioDiasArresto;
    }

    public void setCampoObligatotioDiasArresto(boolean campoObligatotioDiasArresto) {
        this.campoObligatotioDiasArresto = campoObligatotioDiasArresto;
    }

    public boolean isCampoObligatotioRepresentanteDemandado() {
        return campoObligatotioRepresentanteDemandado;
    }

    public void setCampoObligatotioRepresentanteDemandado(boolean campoObligatotioRepresentanteDemandado) {
        this.campoObligatotioRepresentanteDemandado = campoObligatotioRepresentanteDemandado;
    }

    public boolean isCrearOeditarItem() {
        return crearOeditarItem;
    }

    public boolean isBloquearTipoReparto() {
        return bloquearTipoReparto;
    }

    public void setBloquearTipoReparto(boolean bloquearTipoReparto) {
        this.bloquearTipoReparto = bloquearTipoReparto;
    }

    public int getIdTipoSeguimientoEnviar() {
        return idTipoSeguimientoEnviar;
    }

    public void setIdTipoSeguimientoEnviar(int idTipoSeguimientoEnviar) {
        this.idTipoSeguimientoEnviar = idTipoSeguimientoEnviar;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public void setCrearOeditarItem(boolean crearOeditarItem) {
        this.crearOeditarItem = crearOeditarItem;
    }

    public Integer getIdDiagostigoMemoria() {
        return idDiagostigoMemoria;
    }

    public void setIdDiagostigoMemoria(Integer idDiagostigoMemoria) {
        this.idDiagostigoMemoria = idDiagostigoMemoria;
    }

    public TuDiagnostico getEditarDiagnostico() {
        return editarDiagnostico;
    }

    public void setEditarDiagnostico(TuDiagnostico editarDiagnostico) {
        this.editarDiagnostico = editarDiagnostico;
    }

    public Integer getIdentificarEditarGestionDiaganostico() {
        return identificarEditarGestionDiaganostico;
    }

    public void setIdentificarEditarGestionDiaganostico(Integer identificarEditarGestionDiaganostico) {
        this.identificarEditarGestionDiaganostico = identificarEditarGestionDiaganostico;
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

    public List<TuTutelaEstado> getListaServiciosCrear() {
        return listaServiciosCrear;
    }

    public void setListaServiciosCrear(List<TuTutelaEstado> listaServiciosCrear) {
        this.listaServiciosCrear = listaServiciosCrear;
    }

    public List<TuTutelaItem> getListaTutelaEstadoItem() {
        return listaTutelaEstadoItem;
    }

    public void setListaTutelaEstadoItem(List<TuTutelaItem> listaTutelaEstadoItem) {
        this.listaTutelaEstadoItem = listaTutelaEstadoItem;
    }

    public List<CntPrestadorSede> getListaPrestadorSedes() {
        return ListaPrestadorSedes;
    }

    public void setListaPrestadorSedes(List<CntPrestadorSede> ListaPrestadorSedes) {
        this.ListaPrestadorSedes = ListaPrestadorSedes;
    }

    public List<TuMemorialPersona> getListApoderados() {
        if (listApoderados == null) {
            listApoderados = new ArrayList<>();
        }
        return listApoderados;
    }

    public void setListApoderados(List<TuMemorialPersona> listApoderados) {
        this.listApoderados = listApoderados;
    }

    public List<TuMemorialPersona> getListAsistentes() {
        if (listAsistentes == null) {
            listAsistentes = new ArrayList<>();
        }
        return listAsistentes;
    }

    public void setListAsistentes(List<TuMemorialPersona> listAsistentes) {
        this.listAsistentes = listAsistentes;
    }

    public List<TuAdjunto> getListaAdjuntosServicio() {
        return listaAdjuntosServicio;
    }

    public void setListaAdjuntosServicio(List<TuAdjunto> listaAdjuntosServicio) {
        this.listaAdjuntosServicio = listaAdjuntosServicio;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR);
        //inicializarTablaPersona();
        procesoFinal();
    }

    public void verContactos(int idPersona) {
        getTuPersonaContacto().getTuPersona().setId(idPersona);
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_CONTACTOS_PERSONAS);
        getTuTutelaServicio().Accion(this);
        if (!isError()) {
            PrimeFaces.current().ajax().update("frmContactosPersona");
            PrimeFaces.current().executeScript("PF('dialogoContactosPersona').show()");
        }
        procesoFinal();
    }

    public void refrescarObjetoEstado() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(VER_ESTADOS);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarObjetoEstadoItem() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(VER_ITEMS);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarObjetoTutela() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarTutelas() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarSedes() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_BUSCAR_SEDES);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarEstado() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(VER_ESTADOS);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarLazyEstado() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_ESTADOS);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarListaAnexos4() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_ANEXOS_4);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarListaAnexos4Items() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR_ANEXOS4_ITEMS);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarHistorialPersona() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_HISTORIAL_PERSONA);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarLazySeguimiento() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_SEGUIMIENTO);
        getTuTutelaServicio().Accion(this);
    }

    public void refrescarLazyGestionSeguimiento() {
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_LISTAR_SEGUIMIENTO);
        getTuTutelaServicio().Accion(this);
    }

    public SelDiagnosticosBean getSelDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        inicializarTablaEstados();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getTuTutelaServicio().Accion(this);

//         PrimeFaces.current().ajax().update("frmVerDetalleFactura:pDetalleFactura");
        procesoFinal();
    }

    public void crear() {
        getTuTutelaServicio().consultarTodosEstadoTutela(this);
        setObjeto(new TuTutela());
        setObjEstados(new TuTutelaEstado());
        desabilitarCamposEstadoInicial();
        limpiarDatosAuxiliaresContacto();
        super.setAccion(Url.ACCION_CREAR);
        getTuTutelaServicio().Accion(this);
        procesoFinal();

    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        inicializarTablaEstados();
        limpiarDatosAuxiliaresContacto();
        super.setAccion(Url.ACCION_EDITAR);
        super.setDoAccion(Url.ACCION_EDITAR);
        getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void verGestion(int _id) {
        getObjeto().setId(_id);
        inicializarTablaEstados();
        limpiarDatosAuxiliaresContacto();
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR_GESTION);
        getTuTutelaServicio().Accion(this);
        listaServiciosCrear = new ArrayList(this.getObjeto().getListaTuTutelaEstado());
        procesoFinal();
    }

    public void editarResponsable(int _id) {
        getObjeto().setId(_id);
        this.modicarResponsable = new TuTutelaEstado();
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_EDITAR_RESPONSABLE);
        getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void gestionSeguimiento(int _id) {
        getObjeto().setId(_id);
        inicializarTablaGestionSeguimiento();
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_ADICIONAL_6);
        //getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void validarArrestoyMulta() {
        if (getObjeto().getEstadoInicial().getMaeSmlvId() != null) {
            if (getObjeto().getEstadoInicial().getUvt() == null) {
                addError("El campo UVT es obligatorio");
            }
        } else if (getObjeto().getEstadoInicial().getMaeClaseArrestoId() != null) {
            if (getObjeto().getEstadoInicial().getUvt() == null) {
                addError("El campo Dias Arresto es obligatorio");
            }
        }
    }

    public void validarArrestoyMultaAdicional(TuTutelaEstado obj) {
        if (obj.getMaeSmlvId() != null) {
            if (obj.getUvt() == null) {
                addError("El campo UVT es obligatorio");
            }
        } else if (obj.getMaeClaseArrestoId() != null) {
            if (obj.getUvt() == null) {
                addError("El campo Dias Arresto es obligatorio");
            }
        }
    }

    public void guardar() {

        if (getObjeto().getRadicadoNumero().length() != 23) {
            addError("El campo Número Radicado debe tener 23 caracteres");
        }

        if (!super.isError()) {
            TuPersona personaModificar = getObjeto().getTuPersona();
            getTuTutelaServicio().consultarPersona(this);
            asignacionResultadosConsultaParaBeanCaso();
            if (this.getObjeto().getTuPersona().exitePersona()) {
                personaModificar.setId(this.getObjeto().getTuPersona().getId());
                this.getObjeto().setTuPersona(personaModificar);
            }
            super.setAccion(Url.ACCION_GUARDAR);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getTuTutelaServicio().Accion(this);
        }
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(Url.ACCION_MODIFICAR);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        getObjeto().setBorrado(Boolean.TRUE);
        super.setAccion(Url.ACCION_BORRAR);
        super.setDoAccion(Url.ACCION_BORRAR);
        getTuTutelaServicio().Accion(this);
        procesoFinal();

    }

    public void modificarGestion() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR_GESTION);
        getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void modificarResponsable() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_MODIFICAR_RESPONSABLE_ESTADO_ACTUAL);
        getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void desabilitarCamposEstadoInicial() {
        deshabilitarCampoEstadoInicialFallo = true;
        deshabilitarCampoEstadoInicialImpugnacion = true;
        deshabilitarCampoEstadoInicialIntegralidad = true;
        deshabilitarCampoEstadoInicialEntregaSucesiva = true;
        deshabilitarCampoEstadoInicialExoneracion = true;
        deshabilitarCampoEstadoInicialMedidaProvisional = true;
        deshabilitarCampoEstadoInicialClaseSancion = true;
        deshabilitarCampoEstadoInicialSMLV = true;
        deshabilitarCampoEstadoInicialUVT = true;
        deshabilitarCampoEstadoInicialClaseArresto = true;
        deshabilitarCampoEstadoInicialDiasArresto = true;
        deshabilitarCampoEstadoInicialJuzgado = true;
        deshabilitarCampoEstadoInicialRepresentanteDemandado = true;
        deshabilitarCampoEstadoInicialGrupo = true;

        campoObligatotioFallo = false;
        campoObligatorioMedidaProvisional = false;
        campoObligatotioClaseSansion = false;
        campoObligatotioSmlv = false;
        campoObligatotioUVT = false;
        campoObligatotioClaseArresto = false;
        campoObligatotioDiasArresto = false;
        campoObligatotioRepresentanteDemandado = false;

        this.getObjeto().getEstadoInicial().setMaeTipoFalloId(null);
        this.getObjeto().getEstadoInicial().setImpugnacion(null);
        this.getObjeto().getEstadoInicial().setIntegralidad(null);
        this.getObjeto().getEstadoInicial().setEntregaSucesiva(null);
        this.getObjeto().getEstadoInicial().setExoneracion(null);
        this.getObjeto().getEstadoInicial().setMedidadProvisional(null);
        this.getObjeto().getEstadoInicial().setMaeClaseSancionId(null);
        this.getObjeto().getEstadoInicial().setMaeSmlvId(null);
        this.getObjeto().getEstadoInicial().setUvt(null);
        this.getObjeto().getEstadoInicial().setMaeClaseArrestoId(null);
        this.getObjeto().getEstadoInicial().setListaFiltrosRepresentanteDemandado(null);
        this.getObjeto().getEstadoInicial().setDiasArresto(null);
        //
    }

    public void desabilitarCamposEstadoInicialAdicional() {
        deshabilitarCampoEstadoInicialFallo = true;
        deshabilitarCampoEstadoInicialImpugnacion = true;
        deshabilitarCampoEstadoInicialIntegralidad = true;
        deshabilitarCampoEstadoInicialEntregaSucesiva = true;
        deshabilitarCampoEstadoInicialExoneracion = true;
        deshabilitarCampoEstadoInicialMedidaProvisional = true;
        deshabilitarCampoEstadoInicialClaseSancion = true;
        deshabilitarCampoEstadoInicialSMLV = true;
        deshabilitarCampoEstadoInicialUVT = true;
        deshabilitarCampoEstadoInicialClaseArresto = true;
        deshabilitarCampoEstadoInicialDiasArresto = true;
        deshabilitarCampoEstadoInicialJuzgado = true;
        deshabilitarCampoEstadoInicialRepresentanteDemandado = true;
        deshabilitarCampoEstadoInicialGrupo = true;

        campoObligatotioFallo = false;
        campoObligatorioMedidaProvisional = false;
        campoObligatotioClaseSansion = false;
        campoObligatotioSmlv = false;
        campoObligatotioUVT = false;
        campoObligatotioClaseArresto = false;
        campoObligatotioDiasArresto = false;
        campoObligatotioRepresentanteDemandado = false;
        bloquearTipoReparto = false;

        this.getObjEstados().setMaeTipoFalloId(null);
        this.getObjEstados().setImpugnacion(null);
        this.getObjEstados().setIntegralidad(null);
        this.getObjEstados().setEntregaSucesiva(null);
        this.getObjEstados().setExoneracion(null);
        this.getObjEstados().setMedidadProvisional(null);
        this.getObjEstados().setMaeClaseSancionId(null);
        this.getObjEstados().setMaeSmlvId(null);
        this.getObjEstados().setUvt(null);
        this.getObjEstados().setMaeClaseArrestoId(null);
        this.getObjEstados().setListaFiltrosRepresentanteDemandado(null);
        this.getObjEstados().setDiasArresto(null);
        this.getObjEstados().setTipoReparto(null);
    }

    public void desabilitarCamposEstadoInicialEditarAdicional() {
        deshabilitarCampoEstadoInicialFallo = true;
        deshabilitarCampoEstadoInicialImpugnacion = true;
        deshabilitarCampoEstadoInicialIntegralidad = true;
        deshabilitarCampoEstadoInicialEntregaSucesiva = true;
        deshabilitarCampoEstadoInicialExoneracion = true;
        deshabilitarCampoEstadoInicialMedidaProvisional = true;
        deshabilitarCampoEstadoInicialClaseSancion = true;
        deshabilitarCampoEstadoInicialSMLV = true;
        deshabilitarCampoEstadoInicialUVT = true;
        deshabilitarCampoEstadoInicialClaseArresto = true;
        deshabilitarCampoEstadoInicialDiasArresto = true;
        deshabilitarCampoEstadoInicialJuzgado = true;
        deshabilitarCampoEstadoInicialRepresentanteDemandado = true;
        deshabilitarCampoEstadoInicialGrupo = true;

        campoObligatotioFallo = false;
        campoObligatorioMedidaProvisional = false;
        campoObligatotioClaseSansion = false;
        campoObligatotioSmlv = false;
        campoObligatotioUVT = false;
        campoObligatotioClaseArresto = false;
        campoObligatotioDiasArresto = false;
        campoObligatotioRepresentanteDemandado = false;

    }

    /**
     * *********************************
     ******PROCESOS SOBRE SERVICIOS****** **********************************
     */
    /**
     * Borrar servicio de la lista
     *
     * @param pos
     */
    public void borrarServicio(int pos) {
        try {
            //Retirar el servicio de la lista
            List<TuTutelaEstado> lista = new ArrayList();
            int i = 0, j = 0;
            for (TuTutelaEstado det : this.objeto.getListaTuTutelaEstado()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            this.objeto.setListaTuTutelaEstado(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Servicio");
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearServicio').hide();");
            PrimeFaces.current().ajax().update("frmGestion:tablaGestionTutelaEstado");
            // PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
        }
    }

    public void borrarEstadoDb(int id) {
        if (id > 0 && this.lazyTuTutelaEstado.getWrappedData().size() > 1) {
            getObjEstados().setId(id);
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(BORRAR_ESTADO);
            getTuTutelaServicio().Accion(this);

        } else {
            this.addError("No se puede eliminar el estado porque debe tener minimo 1");
        }
        procesoFinal();
        refrescarObjetoTutela();
    }

    public void borrarPrescripcionMemoria() {
        this.objTutulaItem.setPrescripcionCntPrestadorSedeId(new CntPrestadorSede());
        PrimeFaces.current().ajax().update("frmCrearItemAdicional:sedePrescripcion");

    }

    public void borrarDestinoMemoria() {
        this.objTutulaItem.setDestinoCntPrestadorSedeId(new CntPrestadorSede());
        PrimeFaces.current().ajax().update("frmCrearItemAdicional:sedeServicio");
    }

    public void borrarItem(int pos) {
        try {
            //Retirar el servicio de la lista
            List<TuTutelaItem> lista = new ArrayList();
            int i = 0, j = 0;
            for (TuTutelaItem det : this.objEstados.getListaTuTutelaItem()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            this.objEstados.setListaTuTutelaItem(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Item");
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearItem').hide();");
            PrimeFaces.current().ajax().update("frmCrearTablaItem:tablaItemMostrar");
            //PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
        }
    }

    public void borrarSeguimiento(int pos) {
        try {
            //Retirar el servicio de la lista
            List<TuSeguimiento> lista = new ArrayList();
            int i = 0, j = 0;
            for (TuSeguimiento det : this.objEstados.getTuSeguimientosList()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            this.objEstados.setTuSeguimientosList(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Item");
        }

        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrearTablaItem:tablaSeguimientoMostrar");
            //PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
        }
    }

    public void borrarItemDb(int id) {
        if (id > 0) {
            getObjTutulaItem().setId(id);
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(BORRAR_ITEM);
            getTuTutelaServicio().Accion(this);

        }
        procesoFinal();
        refrescarObjetoEstado();
    }

    public void borrarDiagnosticoDb(int id, int pos) {
        if (id > 0) {
            getCmAuditoriaDiagnostico().setId(id);
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(BORRAR_DIAGNOSTICO);
            getTuTutelaServicio().Accion(this);

        } else {
            borrarAdjuntoSMemoria(id, pos, 4);
        }
        procesoFinal();
    }

    public void borrarAutorizacionDb(int id, int pos) {
        if (id > 0) {
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(BORRAR_AUTORIZACION);
            getTuTutelaServicio().Accion(this);
        }
        borrarAdjuntoSMemoria(0, pos, 5);
        procesoFinal();
    }

    public void borrarAutorizacionGestionDb(int id, int pos) {
        if (id > 0) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(BORRAR_AUTORIZACION);
            getTuTutelaServicio().Accion(this);
        }
        borrarAdjuntoSMemoria(0, pos, 5);
        procesoFinal();
    }

    public void borrarSeguimientodb(int id) {
        if (id > 0) {
            getObjEstadosSeguimiento().setId(id);
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(BORRAR_SEGUIMIENTO);
            getTuTutelaServicio().Accion(this);

        }
        procesoFinal();
        refrescarObjetoEstado();
    }

    public void borrarDiagnosticoMemoria(int pos) {
        try {
            //Retirar el servicio de la lista
            List<TuDiagnostico> lista = new ArrayList();
            int i = 0, j = 0;
            for (TuDiagnostico det : this.objTutulaItem.getTuTutelaDiagnosticosId()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            this.objTutulaItem.setTuTutelaDiagnosticosId(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Seguimiento");
        }

        if (!super.isError()) {
            //PrimeFaces.current().executeScript("PF('dialogoCrearItem').hide();");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:tablaCrearDiagnostico");
            //PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
        }
    }

    public void borrarAutorizacionesMemoria(int pos) {
        try {
            //Retirar el servicio de la lista
            List<AuAnexo4> lista = new ArrayList();
            int i = 0, j = 0;
            for (AuAnexo4 det : this.objTutulaItem.getRegistrosAuditoriaAutorizacion()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            this.objTutulaItem.setRegistrosAuditoriaAutorizacion(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Autorización");
        }
    }

    public void borrarContacto(TuPersonaContacto personaContacto) {
        List<TuPersonaContacto> listaContactos = new ArrayList<>();
        int posicionEliminar = personaContacto.getPosicion();
        for (TuPersonaContacto contacto : getListaTuPersonaContacto()) {
            if (contacto.getPosicion() != posicionEliminar) {
                listaContactos.add(contacto);
            }
        }

        if (personaContacto.getId() != null) {
            this.getTuPersonaContacto().setId(personaContacto.getId());
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(ACCION_BORRAR_CONTACTOS_PERSONAS);
            getTuTutelaServicio().Accion(this);
        }

        setListaTuPersonaContacto(listaContactos);

        addMensaje("Se ha realizado la eliminación del contacto");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
        PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
    }

    /**
     * Se encarga de buscar el servicio en memoria
     *
     * @param pos
     *
     */
    public void buscarEstadoEnMemoria(int pos) {
        for (TuTutelaEstado servicio : this.objeto.getListaTuTutelaEstado()) {
            if (servicio.getPos() == pos) {
                this.setObjEstados(servicio);
                break;
            }
        }
    }

    /**
     * Se encarga de buscar el item en memoria
     *
     * @param pos
     *
     */
    public void buscarItemEnMemoria(int pos) {
        for (TuTutelaItem servicio : this.objEstados.getListaTuTutelaItem()) {
            if (servicio.getPos() == pos) {
                this.setObjTutulaItem(servicio);
                break;
            }
        }
    }

    public void modificarEstado() throws ParseException {

        if (this.getObjEstados().getId() != null
                && this.getObjEstados().getId() > 0) {
            //asignarAdjuntosServicioEditar();
            //getAusCasoServicio().modificarServicio(this);
            //refrescarObjetoCaso();
            //listaServiciosCrear = new ArrayList(this.getObjeto().getServiciosList());
            Maestro estado = getHashEstadoTutela().get(this.getObjEstados().getMaeEstadoId());
            if (estado != null) {
                this.getObjEstados().setMaeEstadoCodigo(estado.getValor());
                this.getObjEstados().setMaeEstadoValor(estado.getNombre());
            }
            Maestro proceso = getHashEstadoProceso().get(this.getObjEstados().getMaeProcesoId());
            if (proceso != null) {
                this.getObjEstados().setMaeProcesoCodigo(proceso.getValor());
                this.getObjEstados().setMaeProcesoValor(proceso.getNombre());
            }
            Maestro tipoFallo = getHashEstadoFallo().get(this.getObjEstados().getMaeTipoFalloId());
            if (tipoFallo != null) {
                this.getObjEstados().setMaeTipoFalloCodigo(tipoFallo.getValor());
                this.getObjEstados().setMaeTipoFalloValor(tipoFallo.getNombre());
            }
            Maestro claseSancion = getHashClaseSancion().get(this.getObjEstados().getMaeClaseSancionId());
            if (claseSancion != null) {
                this.getObjEstados().setMaeClaseSancionCodigo(claseSancion.getValor());
                this.getObjEstados().setMaeClaseSancionValor(claseSancion.getNombre());
            }
            Maestro smlv = getHashSmlv().get(this.getObjEstados().getMaeSmlvId());
            if (smlv != null) {
                this.getObjEstados().setMaeSmlvCodigo(smlv.getValor());
                this.getObjEstados().setMaeSmlvValor(smlv.getNombre());
            }
            Maestro claseArresto = getHashClaseArresto().get(this.getObjEstados().getMaeClaseArrestoId());
            if (claseArresto != null) {
                this.getObjEstados().setMaeClaseArrestoCodigo(claseArresto.getValor());
                this.getObjEstados().setMaeClaseArrestoValor(claseArresto.getNombre());
            }
            if (!isError()) {
                super.setAccion(ACCION_MODIFICAR);
                super.setDoAccion(MODIFICAR_TUTELA_ESTADO);
                getTuTutelaServicio().Accion(this);
            }

        } else {
            PrimeFaces.current().resetInputs("frmTutelas");
            PrimeFaces.current().ajax().update("frmCrear");
            PrimeFaces.current().executeScript("PF('dialogoEditarEstado').hide();");
        }
        procesoFinal();
        refrescarObjetoTutela();
    }

    public void modificarSeguimiento() {
        Maestro tipoSeguimiento = getHashTipoSeguimiento().get(getObjEstadosSeguimiento().getMaeTipoSeguimientoId());
        if (tipoSeguimiento != null) {
            getObjEstadosSeguimiento().setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
            getObjEstadosSeguimiento().setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
        }
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(MODIFICAR_TUTELA_ESTADO_SEGUIMIENTO);
        getTuTutelaServicio().Accion(this);
        refrescarObjetoEstado();
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditarItemYSeguimiento:tablaEditarSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarItemYSeguimiento:tablaEditarSeguimiento");
            PrimeFaces.current().executeScript("PF('dialogoEditarSeguimiento').hide();");
        }
        procesoFinal();
    }

    public void modificarItem() {

        Maestro tipoPresentacion = getHashTipoPresentacion().get(objTutulaItem.getMaeTipoPrestacionId());
        if (tipoPresentacion != null) {
            objTutulaItem.setMaeTipoPrestacionCodigo(tipoPresentacion.getValor());
            objTutulaItem.setMaeTipoPrestacionValor(tipoPresentacion.getNombre());
        } else {
            objTutulaItem.setMaeTipoPrestacionCodigo(null);
            objTutulaItem.setMaeTipoPrestacionValor(null);
        }
        Maestro presentacion = getHashPresentacion().get(objTutulaItem.getMaePresentacionId());
        if (presentacion != null) {
            objTutulaItem.setMaePresentacionCodigo(presentacion.getValor());
            objTutulaItem.setMaePresentacionValor(presentacion.getNombre());
        } else {
            objTutulaItem.setMaePresentacionCodigo(null);
            objTutulaItem.setMaePresentacionValor(null);
        }
        Maestro tutelaCausa = getHashTutelaCausa().get(objTutulaItem.getMaeCausaTutelaId());
        if (tutelaCausa != null) {
            objTutulaItem.setMaeCausaTutelaCodigo(tutelaCausa.getValor());
            objTutulaItem.setMaeCausaTutelaValor(tutelaCausa.getNombre());
        } else {
            objTutulaItem.setMaeCausaTutelaCodigo(null);
            objTutulaItem.setMaeCausaTutelaValor(null);
        }
        
        //2024-06-18 jyperez se adicionan validaciones a campos prescripcionCntPrestadorSedeNombre y cantidad
        //2024-09-27 jyperez se adiciona validación de campoObligatoriItem, que se configura en false para Item No Procedimiento
        if (campoObligatorioItem && objTutulaItem.getCantidad() <= 0) {
            this.addError("La cantidad debe ser mayor a cero.");
        }
        if (objTutulaItem.getPrescripcionCntPrestadorSedeId() == null) {
            this.addError("Se debe seleccionar la Ips de Prescripción.");
        }
        //2024-09-23 jyperez se adicionan validaciones aplica Destino
        if (objTutulaItem.isAplicaDestino() && objTutulaItem.getDestinoCntPrestadorSedeId() == null) {
            this.addError("Si se selecciona Aplica Destino, debe seleccionarse la Ips de Destino.");
        }
        
        //2025-07-03 jyperez actualizamos los valores de los maestros
        if (objTutulaItem.getMaeTipoAsignacionId() != null) {
            Maestro tipoAsignacion = hashTipoAsignacion.get(objTutulaItem.getMaeTipoAsignacionId());
            if (tipoAsignacion != null) {
                objTutulaItem.setMaeTipoAsignacionCodigo(tipoAsignacion.getValor());
                objTutulaItem.setMaeTipoAsignacionValor(tipoAsignacion.getNombre());
            } else {
                this.addError("Error configurando maestro Tipo Asignación.");
            }
        }
        if (objTutulaItem.getMaeEstadoItemId()!= null) {
            Maestro estadoItem = hashEstadoSolicitud.get(objTutulaItem.getMaeEstadoItemId());
            if (estadoItem != null) {
                objTutulaItem.setMaeEstadoItemCodigo(estadoItem.getValor());
                objTutulaItem.setMaeEstadoItemValor(estadoItem.getNombre());
            } else {
                this.addError("Error configurando maestro Estado Item.");
            }
        }

        if (!super.isError()) {
            super.setAccion(ACCION_MODIFICAR);
            super.setDoAccion(MODIFICAR_TUTELA_ESTADO_ITEM);
            getTuTutelaServicio().Accion(this);
        }
        procesoFinal();
    }

    public void modificarItemGestion() {

        Maestro tipoPresentacion = getHashTipoPresentacion().get(objTutulaItem.getMaeTipoPrestacionId());
        if (tipoPresentacion != null) {
            objTutulaItem.setMaeTipoPrestacionCodigo(tipoPresentacion.getValor());
            objTutulaItem.setMaeTipoPrestacionValor(tipoPresentacion.getNombre());
        } else {
            objTutulaItem.setMaeTipoPrestacionCodigo(null);
            objTutulaItem.setMaeTipoPrestacionValor(null);
        }
        Maestro presentacion = getHashPresentacion().get(objTutulaItem.getMaePresentacionId());
        if (presentacion != null) {
            objTutulaItem.setMaePresentacionCodigo(presentacion.getValor());
            objTutulaItem.setMaePresentacionValor(presentacion.getNombre());
        } else {
            objTutulaItem.setMaePresentacionCodigo(null);
            objTutulaItem.setMaePresentacionValor(null);
        }
        Maestro tutelaCausa = getHashTutelaCausa().get(objTutulaItem.getMaeCausaTutelaId());
        if (tutelaCausa != null) {
            objTutulaItem.setMaeCausaTutelaCodigo(tutelaCausa.getValor());
            objTutulaItem.setMaeCausaTutelaValor(tutelaCausa.getNombre());
        } else {
            objTutulaItem.setMaeCausaTutelaCodigo(null);
            objTutulaItem.setMaeCausaTutelaValor(null);
        }
        //2024-06-18 jyperez se adicionan validaciones a campos prescripcionCntPrestadorSedeNombre y cantidad
        //2024-09-27 jyperez se adiciona validación de campoObligatoriItem, que se configura en false para Item No Procedimiento
        if (campoObligatorioItem && objTutulaItem.getCantidad() <= 0) {
            this.addError("La cantidad debe ser mayor a cero.");
        }
        if (objTutulaItem.getPrescripcionCntPrestadorSedeId() == null) {
            this.addError("Se debe seleccionar la Ips de Prescripción.");
        }
        //2024-09-23 jyperez se adicionan validaciones aplica Destino
        if (objTutulaItem.isAplicaDestino() && objTutulaItem.getDestinoCntPrestadorSedeId() == null) {
            this.addError("Si se selecciona Aplica Destino, debe seleccionarse la Ips de Destino.");
        }
        
        //2025-07-03 jyperez actualizamos los valores de los maestros
        if (objTutulaItem.getMaeTipoAsignacionId() != null) {
            Maestro tipoAsignacion = hashTipoAsignacion.get(objTutulaItem.getMaeTipoAsignacionId());
            if (tipoAsignacion != null) {
                objTutulaItem.setMaeTipoAsignacionCodigo(tipoAsignacion.getValor());
                objTutulaItem.setMaeTipoAsignacionValor(tipoAsignacion.getNombre());
            } else {
                this.addError("Error configurando maestro Tipo Asignación.");
            }
        }
        if (objTutulaItem.getMaeEstadoItemId()!= null) {
            Maestro estadoItem = hashEstadoSolicitud.get(objTutulaItem.getMaeEstadoItemId());
            if (estadoItem != null) {
                objTutulaItem.setMaeEstadoItemCodigo(estadoItem.getValor());
                objTutulaItem.setMaeEstadoItemValor(estadoItem.getNombre());
                if (objTutulaItem.isAplicaAsignacion() && objTutulaItem.getMaeEstadoItemCodigo().equals(TuTutelaItem.ESTADO_ITEM_NO_ASIGNADO)) {
                    this.addError("Si aplica asignación debe configurarse un estado válido.");
                }
            } else {
                this.addError("Error configurando maestro Estado Item.");
            }
        } else {
            this.addError("el estado es obligatorio.");
        }

        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            getTuTutelaServicio().Accion(this);
        }
        procesoFinal();
    }

    /**
     * Se encarga de editar el servicio
     *
     * @param _id
     * @param pos
     *
     */
    public void editarEstados(int _id, int pos) {
        getTuTutelaServicio().consultarTodosEstadoTutela(this);
        getTuTutelaServicio().listarUsuarios(this);
        if (!super.isError()) {
            if (_id > 0) {
                this.getObjEstados().setId(_id);
                super.setAccion(Url.ACCION_EDITAR);
                super.setDoAccion(EDITAR_TUTELA_ESTADO);
                getTuTutelaServicio().Accion(this);
                getTuTutelaServicio().listarJuzgados(this);
                accionesEstadosAdicionalesEditarTutelas();
                habilitarCamposTipoFalloAdicional();
                habilitarCamposTipoClaseSancionAdicional();
                setListaAbogados(new ArrayList<>());
                setListaGestores(new ArrayList<>());
                setListaMedicos(new ArrayList<>());
                listaAbogados.add(this.getObjEstados().getAbogadoGnUsuarioId());
                listaGestores.add(this.getObjEstados().getGestorGnUsuarioId());
                listaMedicos.add(this.getObjEstados().getMedicoGnUsuarioId());
            } else {
                buscarEstadoEnMemoria(pos);
            }
            //verificarHabilitacionCamposPorMotivo();
            //verificarHabilitacionCampoPorEstado();
            if (this.getObjEstados().getTuAdjuntosList() != null) {
                listaAdjuntosServicio = new ArrayList(this.getObjEstados().getTuAdjuntosList());
            }
        }
        procesoFinal();
    }

    public void editarItem(int _id, int pos) {
        //crearOeditarItem = false;
        if (_id > 0) {
            this.getObjTutulaItem().setId(_id);
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(EDITAR_TUTELA_ESTADOS_ITEM);
            getTuTutelaServicio().Accion(this);
        } else {
            buscarItemEnMemoria(pos);
        }
        //2024-09-24 jyperez validamos si el tipo de item es no procedimiento, para activar la variable en false
        int idTecnologiPorDefecto = PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_CODIGO) == null ? ID_TECNOLOGIA_POR_DEFECTO : Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_CODIGO));
        if (this.getObjTutulaItem() != null && this.getObjTutulaItem().getMaTecnologiaId().equals(idTecnologiPorDefecto) ) {
            setCampoObligatorioItem(false);
        } else {
            setCampoObligatorioItem(true);
        }
        procesoFinal();
    }

    public void editarItemGestion(int _id, int pos) {
        //crearOeditarItem = false;
        if (_id > 0) {
            this.getObjTutulaItem().setId(_id);
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(EDITAR_TUTELA_ESTADOS_ITEM);
            getTuTutelaServicio().Accion(this);
        } else {
            buscarItemEnMemoria(pos);
        }
        //2024-09-24 jyperez validamos si el tipo de item es no procedimiento, para activar la variable en false
        int idTecnologiPorDefecto = PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_CODIGO) == null ? ID_TECNOLOGIA_POR_DEFECTO : Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_CODIGO));
        if (this.getObjTutulaItem() != null && this.getObjTutulaItem().getMaTecnologiaId().equals(idTecnologiPorDefecto) ) {
            setCampoObligatorioItem(false);
        } else {
            setCampoObligatorioItem(true);
        }
        procesoFinal();
    }

    public void editarItemYSeguimiento(int id, int pos) {
        this.setObjEstados(new TuTutelaEstado());
        //crearOeditarItem = false;
        if (id > 0) {
            this.getObjEstados().setId(id);
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(VER_ESTADOS);
            getTuTutelaServicio().Accion(this);
        } else {
            buscarEstadoEnMemoria(pos);
        }
        if (!super.isError()) {
            //2025-07-14 jyperez adicionamos variable que controlará si se muestra la pantalla de ITEM
            if (this.objEstados.getMaeEstadoCodigo().equals(TuTutelaEstado.ESTADO_SEGUIMIENTO_TUTELA_NUEVA) ||
                   this.objEstados.getMaeEstadoCodigo().equals(TuTutelaEstado.ESTADO_SEGUIMIENTO_REQUERIMIENTO) ) {
                habilitarPanelItem = true;
            } else {
                habilitarPanelItem = false;
            }
            PrimeFaces.current().resetInputs("frmEditarItemYSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarItemYSeguimiento");
            PrimeFaces.current().executeScript("PF('dialogoEditarItemYSeguimiento').show()");
        }
        generarMensajes();
    }

    public void editarSeguimiento(int _id, int pos) {
        if (_id > 0) {
            consultarTipoSeguimiento();
            this.getObjEstadosSeguimiento().setId(_id);
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(EDITAR_TUTELA_ESTADOS_SEGUIMIENTO);
            getTuTutelaServicio().Accion(this);
        } else {
            //buscarSeguimientoEnMemoria(pos);
        }
        PrimeFaces.current().ajax().update("frmEditarSeguimiento");
        PrimeFaces.current().resetInputs("frmEditarSeguimiento");
        PrimeFaces.current().executeScript("PF('dialogoEditarSeguimiento').show()");
        procesoFinal();
    }

    /**
     * Agregar servicio a la lista
     */
    public void adicionarEstado() {

        try {

            TuTutelaEstado obj = getObjEstados();
            Maestro estado = getHashEstadoTutela().get(obj.getMaeEstadoId());
            if (estado != null) {
                obj.setMaeEstadoCodigo(estado.getValor());
                obj.setMaeEstadoValor(estado.getNombre());
            }
            Maestro proceso = getHashEstadoProceso().get(obj.getMaeProcesoId());
            if (proceso != null) {
                obj.setMaeProcesoCodigo(proceso.getValor());
                obj.setMaeProcesoValor(proceso.getNombre());
            }
            Maestro tipoFallo = getHashEstadoFallo().get(obj.getMaeTipoFalloId());
            if (tipoFallo != null) {
                obj.setMaeTipoFalloCodigo(tipoFallo.getValor());
                obj.setMaeTipoFalloValor(tipoFallo.getNombre());
            }
            Maestro claseSancion = getHashClaseSancion().get(obj.getMaeClaseSancionId());
            if (claseSancion != null) {
                obj.setMaeClaseSancionCodigo(claseSancion.getValor());
                obj.setMaeClaseSancionValor(claseSancion.getNombre());
            }
            Maestro smlv = getHashSmlv().get(obj.getMaeSmlvId());
            if (smlv != null) {
                obj.setMaeSmlvCodigo(smlv.getValor());
                obj.setMaeSmlvValor(smlv.getNombre());
            }
            Maestro claseArresto = getHashClaseArresto().get(obj.getMaeClaseArrestoId());
            if (claseArresto != null) {
                obj.setMaeClaseArrestoCodigo(claseArresto.getValor());
                obj.setMaeClaseArrestoValor(claseArresto.getNombre());
            }

            if (!super.isError()) {
                super.setAccion(Url.ACCION_ADICIONAL_3);
                getTuTutelaServicio().Accion(this);
            }
            procesoFinal();
        } catch (NumberFormatException e) {
            super.addError("No es posible adicionar estado tutela");
        }

    }

    public void adicionarTablaEstado() {

        try {
            if (!this.objEstados.getListaTuTutelaItem().isEmpty()) {
                super.setAccion(Url.ACCION_ADICIONAL_2);
                super.setDoAccion(ACCION_GUARDAR_ESTADO_ITEM);
                //super.setAccion(Url.ACCION_GUARDAR);
                //super.setDoAccion(ACCION_GUARDAR_ESTADO_ITEM);
                getTuTutelaServicio().Accion(this);

            }
            if (!this.objEstados.getTuSeguimientosList().isEmpty()) {
                super.setAccion(Url.ACCION_ADICIONAL_2);
                super.setDoAccion(ACCION_GUARDAR_ESTADO_SEGUIMIENTO);
                //super.setAccion(Url.ACCION_GUARDAR);
                //super.setDoAccion(ACCION_GUARDAR_ESTADO_SEGUIMIENTO);
                getTuTutelaServicio().Accion(this);
            }
            procesoFinal();
        } catch (Exception e) {
            super.addError("No es posible adicionar servicio");
        }

    }

    public void adicionarContacto() {
        int LONGITUD_MAXIMA_CONTACTO = 32;
        boolean esContactoValido = true;
        TuPersonaContacto tuPersonaContacto = getTuPersonaContacto();

        for (TuPersonaContacto contacto : getListaTuPersonaContacto()) {
            if (contacto.getNumeroContacto().equals(tuPersonaContacto.getNumeroContacto())) {
                esContactoValido = false;
                break;
            }
        }
        if (!esContactoValido) {
            addError("El número de contacto ya existe");
        }

        if (esContactoValido && tuPersonaContacto.getNumeroContacto().length() >= LONGITUD_MAXIMA_CONTACTO) {
            addError("El número de contacto excede la longitud de caracteres : " + LONGITUD_MAXIMA_CONTACTO);
            esContactoValido = false;
        }

        if (esContactoValido) {
            tuPersonaContacto.setPosicion(getListaTuPersonaContacto().size());
            getListaTuPersonaContacto().add(tuPersonaContacto);
        }

        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide();");
            PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
            PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
            PrimeFaces.current().ajax().update("frmGestion:tablaContactoPersona");
        }
        generarMensajes();
    }

    public void modificarTablaEstado() {

        try {
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoEditarItemYSeguimiento').hide();");
                PrimeFaces.current().ajax().update("frmEditar:tablaEditarTutelaEstados");
                //PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
                //PrimeFaces.current().ajax().update("frmGestion:tablaServicios");
            }
            generarMensajes();
        } catch (Exception e) {
            super.addError("No es posible adicionar servicio");
        }

    }

    /**
     * Agregar servicio a la lista
     */
    public void adicionarItem() {

        try {
            TuTutelaItem obj = getObjTutulaItem();
            //2024-05-30 jyperez validamos que se haya agregado un diagnóstico al menos
            if(obj.getTuTutelaDiagnosticosId().isEmpty()) {
                this.addError("Se debe agregar un diagnóstico.");
            }
            //2024-06-18 jyperez se adicionan validaciones a campos prescripcionCntPrestadorSedeNombre y cantidad
            //2024-09-27 jyperez se adiciona validación de campoObligatoriItem, que se configura en false para Item No Procedimiento
            if ( campoObligatorioItem && obj.getCantidad() <= 0) {
                this.addError("La cantidad debe ser mayor a cero.");
            }
            if (obj.getPrescripcionCntPrestadorSedeId() == null) {
                this.addError("Se debe seleccionar la Ips de Prescripción.");
            }
            //2024-09-23 jyperez validamos si aplica destino
            if (obj.isAplicaDestino() && obj.getDestinoCntPrestadorSedeId() == null) {
                this.addError("Si se selecciona Aplica Destino, debe seleccionarse la Ips de Destino.");
            }
            //2025-07-03 jyperez actualizamos los valores de los maestros
            if (obj.getMaeTipoAsignacionId() != null) {
                Maestro tipoAsignacion = hashTipoAsignacion.get(obj.getMaeTipoAsignacionId());
                if (tipoAsignacion != null) {
                    obj.setMaeTipoAsignacionCodigo(tipoAsignacion.getValor());
                    obj.setMaeTipoAsignacionValor(tipoAsignacion.getNombre());
                } else {
                    this.addError("Error configurando maestro Tipo Asignación.");
                }
            }
            if (obj.getMaeEstadoItemId()!= null) {
                Maestro estadoItem = hashEstadoSolicitud.get(obj.getMaeEstadoItemId());
                if (estadoItem != null) {
                    obj.setMaeEstadoItemCodigo(estadoItem.getValor());
                    obj.setMaeEstadoItemValor(estadoItem.getNombre());
                } else {
                    this.addError("Error configurando maestro Estado Item.");
                }
            }
            if (!super.isError()) {
                if (obj.getMaTecnologiaId() != null && obj.getMaTecnologiaCodigo() != null && obj.getMaTecnologiaValor() != null) {
                    //Agregar dato a la lista
                    if (this.objEstados.getListaTuTutelaItem() == null) {
                        this.objEstados.setListaTuTutelaItem(new ArrayList());
                    }
                    obj.setPos(this.objEstados.getListaTuTutelaItem().size());
                    Maestro tipoPresentacion = getHashTipoPresentacion().get(obj.getMaeTipoPrestacionId());
                    if (tipoPresentacion != null) {
                        obj.setMaeTipoPrestacionCodigo(tipoPresentacion.getValor());
                        obj.setMaeTipoPrestacionValor(tipoPresentacion.getNombre());
                    }
                    Maestro presentacion = getHashPresentacion().get(obj.getMaePresentacionId());
                    if (presentacion != null) {
                        obj.setMaePresentacionCodigo(presentacion.getValor());
                        obj.setMaePresentacionValor(presentacion.getNombre());
                    }
                    Maestro tutelaCausa = getHashTutelaCausa().get(obj.getMaeCausaTutelaId());
                    if (tutelaCausa != null) {
                        obj.setMaeCausaTutelaCodigo(tutelaCausa.getValor());
                        obj.setMaeCausaTutelaValor(tutelaCausa.getNombre());
                    }
                    //2024-06-04 jyperez adicion campos tipo servicio
                    Maestro tipoServicio = getHashTipoServicio().get(obj.getMaeTipoServicioId());
                    if (tipoServicio != null) {
                        obj.setMaeTipoServicioCodigo(tipoServicio.getValor());
                        obj.setMaeTipoServicioValor(tipoServicio.getNombre());
                    }
                    this.objEstados.getListaTuTutelaItem().add(obj);
                    if (!super.isError()) {
                        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide();");
                        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide();");
                        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide();");
                        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide();");
                        PrimeFaces.current().executeScript("PF('dialogoCrearItem').hide();");
                        PrimeFaces.current().ajax().update("frmCrearTablaItem:tablaItemMostrar");
                        //PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaServicios");
                        //PrimeFaces.current().ajax().update("frmGestion:tablaServicios");
                    }
                } else {
                    this.addError("El campo Tecnología es obligatorio");
                }
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar Item");
        }
        generarMensajes();
    }
    
    public void validarAplicaDestinoTutelaItem() {
        if (!getObjTutulaItem().isAplicaDestino()) {
            getObjTutulaItem().setDestinoCntPrestadorSedeId(null);
            getObjTutulaItem().setDestinoCntPrestadorSedeNombre("");
        }
    }

    public void adicionarSeguimiento() {

        try {
            TuSeguimiento obj = getObjEstadosSeguimiento();
            if (obj.getMaeTipoSeguimientoId() > 0) {
                //Agregar dato a la lista
                if (this.objEstados.getTuSeguimientosList() == null) {
                    this.objEstados.setTuSeguimientosList(new ArrayList());
                }
                obj.setPos(this.objEstados.getTuSeguimientosList().size());
                Maestro tipoSeguimiento = getHashTipoSeguimiento().get(obj.getMaeTipoSeguimientoId());
                if (tipoSeguimiento != null) {
                    obj.setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
                    obj.setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
                }

                this.objEstados.getTuSeguimientosList().add(obj);
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoCrearSeguimiento').hide();");
                    PrimeFaces.current().ajax().update("frmCrearTablaItem:tablaSeguimientoMostrar");
                    //PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaServicios");
                    //PrimeFaces.current().ajax().update("frmGestion:tablaServicios");
                }
            } else {
                this.addError("El campo Tipo Seguimiento es obligatorio");
            }
            generarMensajes();
        } catch (Exception e) {
            super.addError("No es posible adicionar estado");
        }

    }

    public void crearItem() {
        objTutulaItem = new TuTutelaItem();
        //2025-07-01 jyperez inicializamos el objeto de usuario a cargar de la lista.
        objTutulaItem.setGnUsuarioAsignadoId(new Usuario());
        //this.setDeshabilitarCamposSeguimientoPorMotivo(true);
        //evaluarHabilitarCampoPorEstadoInsert();
        PrimeFaces.current().resetInputs("frmCrearItemAdicional");
        PrimeFaces.current().ajax().update("frmCrearItemAdicional");
        //2024-09-17 jyperez configuramos campo obligatorio para tecnologias en true
        setCampoObligatorioItem(true);
        //2025-07-01 jyperez adicionamos consulta de usuarios para cargar la lista
        getTuTutelaServicio().listarUsuariosPorRol(this);
        PrimeFaces.current().executeScript("PF('dialogoCrearItem').show()");
    }

    public void crearSeguimiento() {
        consultarTipoSeguimiento();
        objEstadosSeguimiento = new TuSeguimiento();
        //this.setDeshabilitarCamposSeguimientoPorMotivo(true);
        //evaluarHabilitarCampoPorEstadoInsert();
        PrimeFaces.current().resetInputs("frmCrearSeguimientoAdicional");
        PrimeFaces.current().ajax().update("frmCrearSeguimientoAdicional");
        PrimeFaces.current().executeScript("PF('dialogoCrearSeguimiento').show()");
        generarMensajes();
    }

    public void crearEstado() {
        desabilitarCamposEstadoInicialAdicional();
        listarJuzgadosAsociadas();
        getTuTutelaServicio().consultarEstadoTutela(this);
        objEstados = new TuTutelaEstado();
        objEstados.setTuJuzgadoId(this.objeto.getActualTuTutelaEstadoId().getTuJuzgadoId());
        objEstados.setListaTuTutelaItem(new ArrayList<>());
        //this.setDeshabilitarCamposSeguimientoPorMotivo(true);
        //evaluarHabilitarCampoPorEstadoInsert();
        PrimeFaces.current().resetInputs("frmCrearEstadoAdicional");
        PrimeFaces.current().ajax().update("frmCrearEstadoAdicional");
        PrimeFaces.current().executeScript("PF('dialogoCrearServicio').show()");
    }

    public void crearTablaItem(int id, int pos) {
        this.setObjEstados(new TuTutelaEstado());
        objTutulaItem = new TuTutelaItem();
        //2025-08-25 jyperez adicionamos la carga del valor por defecto del item
        Maestro estadoTutela = getHashValorEstadoSolicitud().get(TuTutelaItem.ESTADO_ITEM_NO_ASIGNADO);
        if (estadoTutela != null) {
            objTutulaItem.setMaeEstadoItemId(estadoTutela.getId());
            objTutulaItem.setMaeEstadoItemCodigo(estadoTutela.getValor());
            objTutulaItem.setMaeEstadoItemValor(estadoTutela.getNombre());
        }
        //crearOeditarItem = true;
        inicializarTablaSeguimiento();
        if (id > 0) {
            this.getObjEstados().setId(id);
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(VER_ESTADOS);
            getTuTutelaServicio().Accion(this);

        } else {
            buscarEstadoEnMemoria(pos);
        }
        if (!super.isError()) {
            //2025-07-14 jyperez adicionamos variable que controlará si se muestra la pantalla de ITEM
            if (this.objEstados.getMaeEstadoCodigo().equals(TuTutelaEstado.ESTADO_SEGUIMIENTO_TUTELA_NUEVA) ||
                   this.objEstados.getMaeEstadoCodigo().equals(TuTutelaEstado.ESTADO_SEGUIMIENTO_REQUERIMIENTO) ) {
                habilitarPanelItem = true;
            } else {
                habilitarPanelItem = false;
            }
            PrimeFaces.current().resetInputs("frmCrearTablaItem");
            PrimeFaces.current().ajax().update("frmCrearTablaItem");
            PrimeFaces.current().executeScript("PF('dialogoTablaItem').show()");
        }
        generarMensajes();
    }

    public void crearDocumentoEstados(int id) {
        this.setDocumentoMemorial(new ReporteMemorial());
        //this.setObjEstados(new TuTutelaEstado());
        //this.getObjEstados().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(DESCARGAR_DOCUMENTO_MEMORIAL);
        getTuTutelaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmCrearDocumentoMemorial");
            PrimeFaces.current().ajax().update("frmCrearDocumentoMemorial");
            PrimeFaces.current().executeScript("PF('dialogoCrearDocumentoMemorial').show()");
        }
        generarMensajes();
    }

    public void crearContacto() {
        setTuPersonaContacto(new TuPersonaContacto());
        PrimeFaces.current().resetInputs("frmCrearContacto:panelCrearContacto");
        PrimeFaces.current().ajax().update("frmCrearContacto:panelCrearContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearContacto').show()");
    }

    public void verEstado(int _id, int pos) {
        if (_id > 0) {
            getObjEstados().setId(_id);
            //inicializarTablaEstados();
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(VER_ESTADOS);
            getTuTutelaServicio().Accion(this);
            getTuTutelaServicio().listarJuzgados(this);
        } else {
            buscarEstadoEnMemoria(pos);
        }
        //2025-07-14 jyperez adicionamos variable que controlará si se muestra la pantalla de ITEM
        if (this.objEstados.getMaeEstadoCodigo().equals(TuTutelaEstado.ESTADO_SEGUIMIENTO_TUTELA_NUEVA) ||
               this.objEstados.getMaeEstadoCodigo().equals(TuTutelaEstado.ESTADO_SEGUIMIENTO_REQUERIMIENTO) ) {
            habilitarPanelItem = true;
        } else {
            habilitarPanelItem = false;
        }
        PrimeFaces.current().ajax().update("frmVerEstado");
        PrimeFaces.current().executeScript("PF('dialogoVerEstado').show()");
//         PrimeFaces.current().ajax().update("frmVerDetalleFactura:pDetalleFactura");
        procesoFinal();
    }

    public void verSeguimiento(int _id) {
        consultarTipoSeguimiento();
        getObjEstadosSeguimiento().setId(_id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(VER_SEGUIMIENTO);
        getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void verSeguimientoObservacion(int _id) {
        getObjEstadosSeguimiento().setId(_id);
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(VER_SEGUIMIENTO);
        getTuTutelaServicio().Accion(this);
        procesoFinal();
    }

    public void verItem(int _id) {
        getObjTutulaItem().setId(_id);
        //inicializarTablaEstados();
        super.setDoAccion(VER_ITEMS);
        getTuTutelaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerItem");
        PrimeFaces.current().executeScript("PF('dialogoVerItem').show()");
//         PrimeFaces.current().ajax().update("frmVerDetalleFactura:pDetalleFactura");
        procesoFinal();
    }

    public void verHistorialPersona(String documento) {
        try {
            inicializarTablaHistorialPersona(documento);
            PrimeFaces.current().resetInputs("frmHistorialPersonas");
            PrimeFaces.current().ajax().update("frmHistorialPersonas");
            PrimeFaces.current().executeScript("PF('dialogoHistorialPersonas').show()");
            procesoFinal();
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public void inicializarTablaEstados() {
        //this.setParamConsultaServiciosEstado(new ParamConsulta());

        lazyTuTutelaEstado = new LazyDataModel<TuTutelaEstado>() {
            private List<TuTutelaEstado> TututelaEstado;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<TuTutelaEstado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarLazyEstado();
                TututelaEstado = getRegistrosEstados();
                setRowCount(getParamConsulta(0).getCantidadRegistros());

                return TututelaEstado;
            }

            @Override
            public String getRowKey(TuTutelaEstado estado) {
                return estado.getId().toString();
            }

            @Override
            public TuTutelaEstado getRowData(String personaId
            ) {
                Integer id = Integer.valueOf(personaId);
                for (TuTutelaEstado estado : TututelaEstado) {
                    if (id.equals(estado.getId())) {
                        return estado;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaAnexo4() {

        lazyAnexo4 = new LazyDataModel<AuAnexo4>() {
            private List<AuAnexo4> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(5).getCantidadRegistros();
            }

            @Override
            public List<AuAnexo4> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(5).setPrimerRegistro(primerRegistro);
                getParamConsulta(5).setRegistrosPagina(registrosPagina);
                getParamConsulta(5).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(5).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaAnexos4();
                lista = getRegistrosAnexo4();
                setRowCount(getParamConsulta(5).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuAnexo4 anexo) {
                return anexo.getId().toString();
            }

            @Override
            public AuAnexo4 getRowData(String idAnexo4) {
                Integer id = Integer.valueOf(idAnexo4);
                for (AuAnexo4 anexo : lista) {
                    if (id.equals(anexo.getId())) {
                        return anexo;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaAnexo4Items() {

        lazyAnexo4Items = new LazyDataModel<AuAnexo4Item>() {
            private List<AuAnexo4Item> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(6).getCantidadRegistros();
            }

            @Override
            public List<AuAnexo4Item> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(6).setPrimerRegistro(primerRegistro);
                getParamConsulta(6).setRegistrosPagina(registrosPagina);
                getParamConsulta(6).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(6).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaAnexos4Items();
                lista = getRegistrosAnexo4Item();
                setRowCount(getParamConsulta(6).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuAnexo4Item anexo) {
                return anexo.getId().toString();
            }

            @Override
            public AuAnexo4Item getRowData(String idAnexo4) {
                Integer id = Integer.valueOf(idAnexo4);
                for (AuAnexo4Item anexo : lista) {
                    if (id.equals(anexo.getId())) {
                        return anexo;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaPersona() {

        lazyTuPersona = new LazyDataModel<TuPersona>() {
            private List<TuPersona> tuPersona;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<TuPersona> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                tuPersona = getRegistroPersonas();
                setRowCount(getParamConsulta(1).getCantidadRegistros());

                return tuPersona;
            }

            @Override
            public String getRowKey(TuPersona persona) {
                return persona.getId().toString();
            }

            @Override
            public TuPersona getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (TuPersona persona : tuPersona) {
                    if (id.equals(persona.getId())) {
                        return persona;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaSeguimiento() {

        lazyTuTutelaSeguimiento = new LazyDataModel<TuSeguimiento>() {
            private List<TuSeguimiento> tuSeguimiento;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(8).getCantidadRegistros();
            }

            @Override
            public List<TuSeguimiento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(8).setPrimerRegistro(primerRegistro);
                getParamConsulta(8).setRegistrosPagina(registrosPagina);
                getParamConsulta(8).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(8).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarLazySeguimiento();
                tuSeguimiento = getRegistroSeguimiento();
                setRowCount(getParamConsulta(8).getCantidadRegistros());

                return tuSeguimiento;
            }

            @Override
            public String getRowKey(TuSeguimiento seguimiento) {
                return seguimiento.getId().toString();
            }

            @Override
            public TuSeguimiento getRowData(String seguimientoId) {
                Integer id = Integer.valueOf(seguimientoId);
                for (TuSeguimiento seguimiento : tuSeguimiento) {
                    if (id.equals(seguimiento.getId())) {
                        return seguimiento;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaGestionSeguimiento() {
        lazyTuTutelaSeguimiento = new LazyDataModel<TuSeguimiento>() {
            private List<TuSeguimiento> tuSeguimiento;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(9).getCantidadRegistros();
            }

            @Override
            public List<TuSeguimiento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(9).setPrimerRegistro(primerRegistro);
                getParamConsulta(9).setRegistrosPagina(registrosPagina);
                getParamConsulta(9).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(9).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarLazyGestionSeguimiento();
                tuSeguimiento = getRegistroSeguimiento();
                setRowCount(getParamConsulta(9).getCantidadRegistros());
                return tuSeguimiento;
            }

            @Override
            public String getRowKey(TuSeguimiento seguimiento) {
                return seguimiento.getId().toString();
            }

            @Override
            public TuSeguimiento getRowData(String seguimientoId) {
                Integer id = Integer.valueOf(seguimientoId);
                for (TuSeguimiento seguimiento : tuSeguimiento) {
                    if (id.equals(seguimiento.getId())) {
                        return seguimiento;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaHistorialPersona(String documento) {

        this.getParamConsulta(7).setParametroConsulta1(documento);

        lazyHistorialPersona = new LazyDataModel<TuPersona>() {
            private List<TuPersona> tuPersona;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(7).getCantidadRegistros();
            }

            @Override
            public List<TuPersona> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(7).setPrimerRegistro(primerRegistro);
                getParamConsulta(7).setRegistrosPagina(registrosPagina);
                getParamConsulta(7).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(7).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHistorialPersona();
                tuPersona = getRegistrosHistorialPersona();
                setRowCount(getParamConsulta(7).getCantidadRegistros());

                return tuPersona;
            }

            @Override
            public String getRowKey(TuPersona persona) {
                return persona.getId().toString();
            }

            @Override
            public TuPersona getRowData(String personaId
            ) {
                Integer id = Integer.valueOf(personaId);
                for (TuPersona persona : tuPersona) {
                    if (id.equals(persona.getId())) {
                        return persona;
                    }
                }
                return null;
            }
        };
    }

    public void consultarTipoSeguimiento() {
        setListaTipoSeguimiento(new ArrayList<>());
        List<TuGrupoUsuario> tuGrupoUsuario = getTuTutelaServicio().consultarUsuarioGrupoPorTipo(this);
        if (tuGrupoUsuario != null && !tuGrupoUsuario.isEmpty()) {
            switch (tuGrupoUsuario.get(0).getTipo()) {
                case TuGrupoUsuario.TIPO_GESTOR:
                    getTuTutelaServicio().listarTipoSeguimiento(this, 1);
                    break;
                case TuGrupoUsuario.TIPO_ABOGADO:
                    getTuTutelaServicio().listarTipoSeguimiento(this, 2);
                    break;
                case TuGrupoUsuario.TIPO_MEDICO:
                    getTuTutelaServicio().listarTipoSeguimiento(this, 3);
                    break;
            }
        } else {
            addMensaje("El usuario no pertenece a ningun grupo para generar un seguimiento");
        }
        //generarMensajes();
    }

    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 15000000 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }

    public void handleFileUploadEstadoInicial(FileUploadEvent event) throws IOException {

        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_estado_";
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

            /*List<TuAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjeto().getEstadoInicial().getTuAdjuntosList().addAll(listaAnexos);
            }*/
            //agraegar el nuevo anexo a la lista de anexos de tutela
            TuAdjunto adjunto = new TuAdjunto();
            adjunto.setId(null);
            adjunto.setNombreArchivo(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjeto().getEstadoInicial().getTuAdjuntosList().size());

            //listaAnexos.add(adjunto);
            this.getObjeto().getEstadoInicial().getTuAdjuntosList().add(adjunto);

            //llenarSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS, listaAnexos);
            PrimeFaces.current().resetInputs("frmCrear:tablaCrearAnexosEstados");
            PrimeFaces.current().ajax().update("frmCrear:tablaCrearAnexosEstados");

        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public void handleFileUploadServicio(FileUploadEvent event) throws IOException {

        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_estado_";
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

            /*List<TuAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjEstados().getTuAdjuntosList().addAll(listaAnexos);
            }*/
            //agraegar el nuevo anexo a la lista de anexos de tutela
            TuAdjunto adjunto = new TuAdjunto();
            adjunto.setId(null);
            adjunto.setNombreArchivo(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjEstados().getTuAdjuntosList().size());

            //listaAnexos.add(adjunto);
            this.getObjEstados().getTuAdjuntosList().add(adjunto);

            //llenarSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS, listaAnexos);
            PrimeFaces.current().resetInputs("frmCrearEstadoAdicional:tablaEstadosAdicionalAnexos");
            PrimeFaces.current().ajax().update("frmCrearEstadoAdicional:tablaEstadosAdicionalAnexos");
            PrimeFaces.current().resetInputs("frmEditarEstadoAdicional:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmEditarEstadoAdicional:tablaAnexosServicios");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public void handleFileUploadItem(FileUploadEvent event) throws IOException {

        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_Item_";
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

            /*List<TuAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS_ITEM);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjTutulaItem().getTuAdjuntosList().addAll(listaAnexos);
            }*/
            //agraegar el nuevo anexo a la lista de anexos de tutela
            TuAdjunto adjunto = new TuAdjunto();
            adjunto.setId(null);
            adjunto.setNombreArchivo(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjTutulaItem().getTuAdjuntosList().size());

            //listaAnexos.add(adjunto);
            this.getObjTutulaItem().getTuAdjuntosList().add(adjunto);

            //llenarSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS_ITEM, listaAnexos);
            PrimeFaces.current().resetInputs("frmCrearItemAdicional:tablaItemdicionalAnexos");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:tablaItemdicionalAnexos");
            //PrimeFaces.current().resetInputs("frmEditarServicio:tablaAnexosServicios");
            //PrimeFaces.current().ajax().update("frmEditarServicio:tablaAnexosServicios");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public void handleFileUploadSeguimiento(FileUploadEvent event) throws IOException {

        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_Seguimiento_";
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

            /*List<TuAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS_SEGUIMIENTO);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjEstadosSeguimiento().getTuAdjuntosList().addAll(listaAnexos);
            }*/
            //agraegar el nuevo anexo a la lista de anexos de tutela
            TuAdjunto adjunto = new TuAdjunto();
            adjunto.setId(null);
            adjunto.setNombreArchivo(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjEstadosSeguimiento().getTuAdjuntosList().size());

            //listaAnexos.add(adjunto);
            this.getObjEstadosSeguimiento().getTuAdjuntosList().add(adjunto);

            //llenarSesionAdjuntos(SESION_ADJUNTO_TUTELA_ESTADOS_SEGUIMIENTO, listaAnexos);
            PrimeFaces.current().resetInputs("frmCrearSeguimientoAdicional:tablaSeguimientoAdicionalAnexos");
            PrimeFaces.current().ajax().update("frmCrearSeguimientoAdicional:tablaSeguimientoAdicionalAnexos");
            PrimeFaces.current().resetInputs("frmEditarSeguimiento:tablaEditarAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarSeguimiento:tablaEditarAnexosSeguimiento");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto servicio : " + e.getMessage());
            generarMensajes();
        }
    }

    public void borrarAdjuntoEstadoAdicionalMemoria(int pos) {
        try {
            delAdjuntoEstadoAdicionalMemoria(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrearEstadoAdicional:tablaEstadosAdicionalAnexos");
            PrimeFaces.current().ajax().update("frmEditarEstadoAdicional:tablaAnexosServicios");
            //PrimeFaces.current().ajax().update("frmEditarServicio:tablaAnexosServicios");
        }
    }

    public void borrarAdjuntoMemoriaGestion(int id, int pos, Integer identificadoArchivo) {
        try {
            if (id > 0) {
                getBorrarAdjunto().setId(id);
                setBorrarAdjunto(new TuAdjunto());
                borrarAdjunto.setId(id);
                super.setAccion(Url.ACCION_ADICIONAL_1);
                super.setDoAccion(BORRAR_ADJUNTO);
                getTuTutelaServicio().Accion(this);

            }
            switch (identificadoArchivo) {
                case 0:
                    delAdjuntoEstadoAdicionalMemoria(pos);
                    break;
                case 1:
                    delAdjuntoServicioMemoria(pos);
                    break;
                case 2:
                    delAdjuntoItemMemoria(pos);
                    break;
                case 3:
                    delAdjuntoSeguimientoMemoria(pos);
                    break;
                case 4:
                    borrarDiagnosticoMemoria(pos);
                    break;
                case 5:
                    borrarAutorizacionesMemoria(pos);
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

    public void borrarAdjuntoSMemoria(int id, int pos, Integer identificadoArchivo) {

        try {
            if (id > 0) {
                getBorrarAdjunto().setId(id);
                setBorrarAdjunto(new TuAdjunto());
                borrarAdjunto.setId(id);
                super.setAccion(Url.ACCION_BORRAR);
                super.setDoAccion(BORRAR_ADJUNTO);
                getTuTutelaServicio().Accion(this);

            }
            switch (identificadoArchivo) {
                case 0:
                    delAdjuntoEstadoAdicionalMemoria(pos);
                    break;
                case 1:
                    delAdjuntoServicioMemoria(pos);
                    break;
                case 2:
                    delAdjuntoItemMemoria(pos);
                    break;
                case 3:
                    delAdjuntoSeguimientoMemoria(pos);
                    break;
                case 4:
                    borrarDiagnosticoMemoria(pos);
                    break;
                case 5:
                    borrarAutorizacionesMemoria(pos);
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

    public void delAdjuntoItemMemoria(int pos) {
        List<TuAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (TuAdjunto det : this.objTutulaItem.getTuAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.objTutulaItem.setTuAdjuntosList(lista);
    }

    public void delAdjuntoSeguimientoMemoria(int pos) {
        List<TuAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (TuAdjunto det : this.getObjEstadosSeguimiento().getTuAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getObjEstadosSeguimiento().setTuAdjuntosList(lista);
    }

    public void delAdjuntoServicioMemoria(int pos) {
        List<TuAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (TuAdjunto det : this.objeto.getEstadoInicial().getTuAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.objeto.getEstadoInicial().setTuAdjuntosList(lista);
    }

    public void delAdjuntoEstadoAdicionalMemoria(int pos) {
        List<TuAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (TuAdjunto det : this.objEstados.getTuAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.objEstados.setTuAdjuntosList(lista);
    }

    public void descargarAnexo(TuAdjunto adjunto) {
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

    public void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (getDoAccion()) {
                        case TuTutelaBean.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmTutelas");
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.ACCION_BUSCAR_SEDES:
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case TuTutelaBean.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVer");
                            PrimeFaces.current().ajax().update("frmAuditoriaGeneralVer");
                            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.VER_ESTADOS:
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.VER_ITEMS:
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.VER_SEGUIMIENTO:
                            PrimeFaces.current().ajax().update("frmVerSeguimiento");
                            PrimeFaces.current().executeScript("PF('dialogoVerSeguimiento').show()");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().resetInputs("frmCrear");
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().ajax().update("frmCrear:pCrearServicio");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
                            PrimeFaces.current().resetInputs("frmTutelas");
                            PrimeFaces.current().ajax().update("frmTutelas");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_EDITAR:
                    switch (getDoAccion()) {
                        case TuTutelaBean.ACCION_EDITAR:
                            PrimeFaces.current().resetInputs("frmEditar");
                            PrimeFaces.current().ajax().update("frmEditar");
                            PrimeFaces.current().ajax().update("frmAuditoriaGeneralEditar");
                            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.EDITAR_TUTELA_ESTADO:
                            PrimeFaces.current().ajax().update("frmEditarEstadoAdicional");
                            PrimeFaces.current().resetInputs("frmEditarEstadoAdicional");
                            PrimeFaces.current().executeScript("PF('dialogoEditarEstado').show()");
                            crearLog(getObjEstados().toString());
                            break;

                        case TuTutelaBean.EDITAR_TUTELA_ESTADOS_ITEM:
                            PrimeFaces.current().ajax().update("frmEditarItemAdicional");
                            PrimeFaces.current().resetInputs("frmEditarItemAdicional");
                            PrimeFaces.current().executeScript("PF('dialogoEditarItem').show()");
                            crearLog(getObjTutulaItem().toString());
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (getDoAccion()) {
                        case TuTutelaBean.ACCION_MODIFICAR:
                            //refrescar();
                            PrimeFaces.current().ajax().update("frmEditar");
                            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
                            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
                            PrimeFaces.current().resetInputs("frmTutelas");
                            PrimeFaces.current().ajax().update("frmTutelas");
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO:
                            PrimeFaces.current().resetInputs("frmEditar");
                            PrimeFaces.current().ajax().update("frmEditar");
                            PrimeFaces.current().ajax().update("frmEditar:panelEditarEstados");
                            //PrimeFaces.current().resetInputs("frmGestion");
                            //PrimeFaces.current().ajax().update("frmGestion");
                            PrimeFaces.current().executeScript("PF('dialogoEditarEstado').hide();");
                            crearLog(getObjEstados().toString());
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_ITEM:
                            refrescarObjetoEstado();
                            PrimeFaces.current().resetInputs("frmEditarItemYSeguimiento:tablaEditarItem");
                            PrimeFaces.current().ajax().update("frmEditarItemYSeguimiento:tablaEditarItem");
                            //debemos actualizar las tablas de Estados
                            PrimeFaces.current().executeScript("PF('dialogoEditarItem').hide();");
                            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide();");
                            crearLog(getObjTutulaItem().toString());
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO:
                            refrescarObjetoEstadoItem();
                            PrimeFaces.current().resetInputs("frmEditarItemAdicional:tablaEditarDiagnostico");
                            PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaEditarDiagnostico");
                            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide();");
                            crearLog(getObjTutulaItem().toString());
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_SEGUIMIENTO:
                            crearLog(getObjEstadosSeguimiento().toString());
                            break;

                    }
                    break;

                case Url.ACCION_BORRAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            PrimeFaces.current().ajax().update("frmCrearTablaItem:tablaItemMostrar");
                            PrimeFaces.current().resetInputs("frmTutelas");
                            PrimeFaces.current().ajax().update("frmTutelas");
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.BORRAR_ESTADO:
                            PrimeFaces.current().resetInputs("frmEditar:tablaEditarTutelaEstados");
                            PrimeFaces.current().ajax().update("frmEditar:tablaEditarTutelaEstados");
                            PrimeFaces.current().ajax().update("frmTutelas");
                            break;
                        case TuTutelaBean.BORRAR_ITEM:
                            PrimeFaces.current().resetInputs("frmEditarItemYSeguimiento:tablaEditarItem");
                            PrimeFaces.current().ajax().update("frmEditarItemYSeguimiento:tablaEditarItem");
                            crearLog(getObjTutulaItem().toString());
                            break;
                        case TuTutelaBean.BORRAR_SEGUIMIENTO:
                            PrimeFaces.current().resetInputs("frmEditarItemYSeguimiento:tablaEditarSeguimiento");
                            PrimeFaces.current().ajax().update("frmEditarItemYSeguimiento:tablaEditarSeguimiento");
                            crearLog(getObjEstadosSeguimiento().toString());
                            break;
                        case TuTutelaBean.BORRAR_DIAGNOSTICO:
                            refrescarObjetoEstadoItem();
                            PrimeFaces.current().resetInputs("frmEditarItemAdicionalGestion:tablaEditarDiagnostico");
                            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:tablaEditarDiagnostico");
                            PrimeFaces.current().resetInputs("frmEditarItemAdicional:tablaEditarDiagnostico");
                            PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaEditarDiagnostico");
                            crearLog(getObjEstadosSeguimiento().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case TuTutelaBean.ACCION_EDITAR_GESTION:
                            PrimeFaces.current().ajax().update("frmGestion");
                            PrimeFaces.current().ajax().update("frmAuditoriaGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoGestion').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.VER_ESTADOS:
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.EDITAR_TUTELA_ESTADOS_ITEM:
                            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion");
                            PrimeFaces.current().resetInputs("frmEditarItemAdicionalGestion");
                            PrimeFaces.current().executeScript("PF('dialogoEditarItemGestion').show()");
                            crearLog(getObjTutulaItem().toString());
                            break;
                        case TuTutelaBean.ACCION_ADICIONAL_1:
                            refrescarObjetoEstado();
                            PrimeFaces.current().resetInputs("frmCrearTablaItem:tablaItemMostrar");
                            PrimeFaces.current().ajax().update("frmCrearTablaItem:tablaItemMostrar");
                            PrimeFaces.current().executeScript("PF('dialogoEditarItemGestion').hide();");
                            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide();");
                            crearLog(getObjTutulaItem().toString());
                            break;
                        case TuTutelaBean.MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO:
                            refrescarObjetoEstadoItem();
                            PrimeFaces.current().resetInputs("frmEditarItemAdicionalGestion:tablaEditarDiagnostico");
                            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:tablaEditarDiagnostico");
                            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide();");
                            crearLog(getObjTutulaItem().toString());
                            break;
                        case TuTutelaBean.ACCION_MODIFICAR_GESTION:
                            PrimeFaces.current().executeScript("PF('dialogoGestion').hide();");
                            PrimeFaces.current().resetInputs("frmTutelas");
                            PrimeFaces.current().ajax().update("frmTutelas");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;

                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case TuTutelaBean.VER_ESTADOS:
                            crearLog(getObjeto().toString());
                            break;
                        case TuTutelaBean.ACCION_GUARDAR_ESTADO_ITEM:
                            PrimeFaces.current().executeScript("PF('dialogoTablaItem').hide();");
                            PrimeFaces.current().ajax().update("frmGestion:tablaGestionTutelaEstado");
                            crearLog(getObjEstados().toString());
                            break;
                        case TuTutelaBean.ACCION_GUARDAR_ESTADO_SEGUIMIENTO:
                            PrimeFaces.current().executeScript("PF('dialogoTablaItem').hide();");
                            PrimeFaces.current().ajax().update("frmGestion:tablaGestionTutelaEstado");
                            crearLog(getObjEstados().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    PrimeFaces.current().executeScript("PF('dialogoCrearServicio').hide();");
                    PrimeFaces.current().ajax().update("frmGestion:gestionPanelDatosTutela");
                    PrimeFaces.current().ajax().update("frmGestion:tablaGestionTutelaEstado");
                    crearLog(getObjEstados().toString());
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case TuTutelaBean.ACCION_EDITAR_RESPONSABLE:
                            PrimeFaces.current().ajax().update("frmCambiarResponsable:panelCambiarResponsable");
                            PrimeFaces.current().executeScript("PF('dialogoCambiarResponsable').show()");
                            crearLog("Editar Responsable", getObjeto().toString());
                            break;
                        case TuTutelaBean.ACCION_MODIFICAR_RESPONSABLE_ESTADO_ACTUAL:
                            PrimeFaces.current().resetInputs("frmTutelas");
                            PrimeFaces.current().ajax().update("frmTutelas");
                            PrimeFaces.current().executeScript("PF('dialogoCambiarResponsable').hide();");
                            crearLog("Modificar Responsable", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_6:
                            PrimeFaces.current().ajax().update("frmHistorialTutelas:panelGestionSeguimiento");
                            PrimeFaces.current().executeScript("PF('dialogoHistorialTutelas').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case ACCION_LISTAR_SEGUIMIENTO:
                            crearLog(getObjeto().toString());
                            break;
                        case VER_SEGUIMIENTO:
                            PrimeFaces.current().ajax().update("frmVerSeguimientoObservacion:verPanelDatosEstadoSeguimiento");
                            PrimeFaces.current().executeScript("PF('dialogoVerSeguimientoObservacion').show()");
                            crearLog(getObjEstadosSeguimiento().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
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

    public String getJuzgado(int id) {
        try {
            return hashJuzgados.get(id).getNombre() + " " + hashJuzgados.get(id).getCodigo_despacho();
        } catch (Exception e) {
            return "";
        }
    }

    public String getUsuario(int id) {
        try {
            return hashUsuarios.get(id).getNombre() + " " + hashUsuarios.get(id).getUsuario();
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
//        if (ubicacionesFiltradas.size() == 1) {
//            getObjeto().setPersonaUbicacion(ubicacionesFiltradas.get(0));
//        }
        return ubicacionesFiltradas;
    }

//    private void refrescarFormularios() {
//        PrimeFaces.current().ajax().update("frmCrear");
//        PrimeFaces.current().ajax().update("frmEditar");
//    }
    public void handleFileUploadTutela(FileUploadEvent event) throws IOException {
        try {

            String file = org.apache.commons.io.FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_tutelas_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.TU_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            } else {
                File directorios = new File(ruta);
                if (!directorios.exists()) {
                    if (directorios.mkdirs()) {
                        System.out.println("Multiples directorios fueron creados");
                    } else {
                        System.out.println("Error al crear directorios");
                    }
                }
            }
            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            /*List<TuAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_TUTELA);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjeto().getAdjuntosList().addAll(listaAnexos);
            }*/
            //agraegar el nuevo anexo a la lista de anexos de tutela
            TuAdjunto adjunto = new TuAdjunto();
            adjunto.setId(null);
            adjunto.setNombreArchivo(filename);
            adjunto.setArchivo(event.getFile().getFileName());
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjeto().getAdjuntosList().size());

            //listaAnexos.add(adjunto);
            this.getObjeto().getAdjuntosList().add(adjunto);

            //llenarSesionAdjuntos(SESION_ADJUNTO_TUTELA, listaAnexos);
            PrimeFaces.current().resetInputs("frmCrear:tablaAnexosTutela");
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosTutela");
            //PrimeFaces.current().resetInputs("frmEditar:tablaAnexosCasos");
            //PrimeFaces.current().ajax().update("frmEditar:tablaAnexosCasos");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto caso : " + e.getMessage());
            generarMensajes();
        }
    }

    public void borrarAdjuntoCasoDeMemoria(int pos) {
        try {
            delAdjuntoCasoMemoria(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosTutela");
            //PrimeFaces.current().ajax().update("frmEditar:tablaAnexosTutela");
        }
    }

    public void delAdjuntoCasoMemoria(int pos) {
        List<TuAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (TuAdjunto det : this.getObjeto().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getObjeto().setAdjuntosList(lista);
    }

    public void buscarPersonaEnSistema(Integer peticionario) {

        if (this.getObjeto().getTuPersona().getMaeTipoDocumentoId() > 0
                && this.getObjeto().getTuPersona().getNumeroDocumento() != null) {
            try {
                //rEFRESCAR BÚSQUEDA DE PERSONA

                this.getObjeto().getTuPersona().setId(0);
                this.getObjeto().getTuPersona().setEsAfiliado(false);
                if (!getHashTiposDocumento().isEmpty() && getHashTiposDocumento() != null) {
                    Maestro tipoDocumento = getHashTiposDocumento().get(this.getObjeto().getTuPersona().getMaeTipoDocumentoId());
                    if (tipoDocumento != null) {
                        this.getObjeto().getTuPersona().setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                        this.getObjeto().getTuPersona().setMaeTipoDocumentoValor(tipoDocumento.getNombre());
                    }
                }
                //Consultar afiliado en BD
                getTuTutelaServicio().consultarPersonaAfiliada(this);
                //getPersonaServicio().consultarPersona(personaBean);
                asignacionResultadosConsultaParaBeanCaso();
                if (!this.getObjeto().getTuPersona().exitePersona()) {

                    //Consultar persona en BD
                    getTuTutelaServicio().consultarPersona(this);
                    if (this.getErrores().size() > 0) {
                        this.addError(this.getErrores().get(0));
                        this.getErrores().clear();
                    }
                    asignacionResultadosConsultaParaBeanCaso();
                }

                if (!this.getObjeto().getTuPersona().exitePersona() && !existePersonaAfiliada()) {
                    String documento = this.getObjeto().getTuPersona().getNumeroDocumento();
                    int tipoDoc = this.getObjeto().getTuPersona().getMaeTipoDocumentoId();
                    this.getObjeto().setTuPersona(new TuPersona());
                    this.getObjeto().getTuPersona().setMaeTipoDocumentoId(tipoDoc);
                    this.getObjeto().getTuPersona().setNumeroDocumento(documento);
                    this.getObjeto().getTuPersona().setMaeEstadoAfiliadoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.ESTADO_PERSONA_DEFECTO_CREAR)));

                    //this.listaPersonaTelefono = new ArrayList<>();
                }

                PrimeFaces.current().ajax().update("frmCrear:panelPersonaCrear");
                PrimeFaces.current().ajax().update("frmEditar:panelPersonaEditar");
                //PrimeFaces.current().ajax().update("frmCrear:telefonos");
                //PrimeFaces.current().ajax().update("frmEditar");

                generarMensajes();
            } catch (Exception ex) {
                Logger.getLogger(AusCasoBean.class.getName()).log(Level.SEVERE, null, ex);
                this.addError(ex.getMessage());
                generarMensajes();
            }
        }
    }

    private boolean existePersonaAfiliada() {
        return this.getObjeto().getTuPersona().getEsAfiliado();
    }

    private void asignacionResultadosConsultaParaBeanCaso() {
        this.getObjeto().setTuPersona(this.getObjeto().getTuPersona());
        //this.listaPersonaTelefono = personaBean.get ListaausPersonaTelefono();
        //this.listaPersonaTelefono = personaBean.getObjeto().getListaTelefonos();
    }

    public List<Usuario> completarUsuariosAbogados(String query) {
        List<Usuario> usuariosFiltrado = new ArrayList<>();
        if (!getListaUsuarios().isEmpty()) {
            getListaUsuarios().stream().filter(user -> (user.getNombre().toLowerCase().contains(query.toLowerCase()))).forEachOrdered(user -> {
                usuariosFiltrado.add(user);
            });
        }
        if (usuariosFiltrado.size() == 1) {
            //this.getServicioParaCrear().setIdUsuarioResponsable(usuariosFiltrado.get(0).getId());
            this.getObjeto().getEstadoInicial().getAbogadoGnUsuarioId().setId(usuariosFiltrado.get(0).getId());
        }
        return usuariosFiltrado;
    }

    public List<Usuario> completarUsuariosMedicos(String query) {
        List<Usuario> usuariosFiltrado = new ArrayList<>();
        if (!getListaUsuarios().isEmpty()) {
            getListaUsuarios().stream().filter(user -> (user.getNombre().toLowerCase().contains(query.toLowerCase()))).forEachOrdered(user -> {
                usuariosFiltrado.add(user);
            });
        }
        if (usuariosFiltrado.size() == 1) {
            //this.getServicioParaCrear().setIdUsuarioResponsable(usuariosFiltrado.get(0).getId());
            this.getObjeto().getEstadoInicial().getMedicoGnUsuarioId().setId(usuariosFiltrado.get(0).getId());
        }
        return usuariosFiltrado;
    }

    public List<Usuario> completarUsuariosGestor(String query) {
        List<Usuario> usuariosFiltrado = new ArrayList<>();
        if (!getListaUsuarios().isEmpty()) {
            getListaUsuarios().stream().filter(user -> (user.getNombre().toLowerCase().contains(query.toLowerCase()))).forEachOrdered(user -> {
                usuariosFiltrado.add(user);
            });
        }
        if (usuariosFiltrado.size() == 1) {
            //this.getServicioParaCrear().setIdUsuarioResponsable(usuariosFiltrado.get(0).getId());
            this.getObjeto().getEstadoInicial().getGestorGnUsuarioId().setId(usuariosFiltrado.get(0).getId());
        }
        return usuariosFiltrado;
    }

    public List<Usuario> completarUsuariosResponsable(String query) {
        List<Usuario> usuariosFiltrado = new ArrayList<>();
        for (Usuario user : getListaUsuarios()) {
            if (user.getNombre().toLowerCase().contains(query.toLowerCase())) {
                usuariosFiltrado.add(user);
            }
        }
        if (usuariosFiltrado.size() == 1) {
            //this.getServicioParaCrear().setIdUsuarioResponsable(usuariosFiltrado.get(0).getId());
            this.getObjeto().getEstadoInicial().getResponsableGnUsuarioId().setId(usuariosFiltrado.get(0).getId());
        }
        return usuariosFiltrado;
    }

    public void accionesEstadosTutelas() {
        try {
            getTuTutelaServicio().crearEstadoBloquearYcalarCamposObligatoriosParaEstadosTutelas(this);
        } catch (Exception ex) {
            addError("Hubo error en la acciones");
        }
        generarMensajes();
    }

    public void habilitarCamposTipoFallo() {
        try {
            getTuTutelaServicio().crearHabilitarCamposTipoFallo(this);
        } catch (Exception ex) {
            addError("Hubo error habilitar fallo");
        }
        generarMensajes();

    }

    public void habilitarCamposTipoFalloAdicional() {
        try {
            getTuTutelaServicio().crearHabilitarCamposTipoFalloAdicional(this);
        } catch (Exception ex) {
            addError("Hubo error habilitar fallo");
        }
        generarMensajes();
    }

    public void habilitarCamposTipoClaseSancion() {
        try {
            getTuTutelaServicio().crearHabilitarCamposTipoClaseSancion(this);
        } catch (Exception ex) {
            addError("Hubo error habilitar clase sanción");
        }
        generarMensajes();
    }

    public void habilitarCamposTipoClaseSancionAdicional() {
        try {
            getTuTutelaServicio().crearHabilitarCamposTipoClaseSancionAdicional(this);
        } catch (Exception ex) {
            addError("Hubo error habilitar clase sanción");
        }
        generarMensajes();
    }

    public void listarJuzgadosAsociadas() {
        getTuTutelaServicio().listarJuzgados(this);
        PrimeFaces.current().ajax().update("frmCrear:juzgado");
        generarMensajes();
    }

    public void listarJuszadosAsociadasEditar() {
        getTuTutelaServicio().listarJuzgados(this);
        generarMensajes();
    }

    public void incrementarFecha() {
        Date date = getObjeto().getEstadoInicial().getFechaNotificacion();
        Calendar c = Calendar.getInstance();
        int i=0;
        // set calendar time with given date
        c.setTime(date);
        // number of days to increment
        int maxIncrement = Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.INCREMENTO_DIAS_TUTELA_FECHA_VEN));
        // add days to date 
        //2025-04-07 jyperez vamos a validar la suma de cada día hasta el maximo de días de la variable INCREMENTO_DIAS_TUTELA_FECHA_VEN
        //c.add(Calendar.DAY_OF_WEEK, maxIncrement);
        // check if the date after addition is a working day. 
        // If not then keep on incrementing it till it is a working day
        //boolean 
        while (i<maxIncrement) {
            c.add(Calendar.DAY_OF_WEEK, 1);
            try {
                if(getTuTutelaServicio().consultarDiaHabil(c.getTime())) {
                    //c.add(Calendar.DAY_OF_WEEK, 1);
                    i++;
                }
            } catch (Exception ex) {
            }
        }
        getObjeto().getEstadoInicial().setFechaVencimiento(c.getTime());
        generarMensajes();
    }

    public void incrementarFechaAdicional() {
        Date date = getObjEstados().getFechaNotificacion();
        Calendar c = Calendar.getInstance();
        int i=0;
        // set calendar time with given date
        c.setTime(date);
        // number of days to increment
        int maxIncrement = Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.INCREMENTO_DIAS_TUTELA_FECHA_VEN));
        // add days to date 
        //2025-04-07 jyperez vamos a validar la suma de cada día hasta el maximo de días de la variable INCREMENTO_DIAS_TUTELA_FECHA_VEN
        //c.add(Calendar.DAY_OF_WEEK, maxIncrement);
        // check if the date after addition is a working day. 
        // If not then keep on incrementing it till it is a working day
        //boolean 
        while (i<maxIncrement) {
            c.add(Calendar.DAY_OF_WEEK, 1);
            try {
                if(getTuTutelaServicio().consultarDiaHabil(c.getTime())) {
                    i++;
                }
            } catch (Exception ex) {
            }
        }
        getObjEstados().setFechaVencimiento(c.getTime());
        generarMensajes();
    }

    public void accionesEstadosAdicionalesTutelas() {
        try {
            getTuTutelaServicio().crearEstadoBloquearYcalarCamposObligatoriosParaEstadosTutelasAdicionales(this);
        } catch (Exception ex) {
            addError("Hubo error en la acciones");
        }
        generarMensajes();
    }

    public void accionesEstadosAdicionalesEditarTutelas() {
        desabilitarCamposEstadoInicialEditarAdicional();
        Integer idTutelaEstado = this.objEstados.getMaeEstadoId();
        Maestro estadoTuMaestro = getHashEstadoTutela().get(idTutelaEstado);
        if (estadoTuMaestro != null) {
            this.objEstados.setMaeEstadoCodigo(estadoTuMaestro.getValor());
            this.objEstados.setMaeEstadoValor(estadoTuMaestro.getNombre());
        }
        /*if (idTutelaEstado == Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.HABI_CAMPO_ESTADO_FALLO))) {
            deshabilitarCampoEstadoInicialFallo = false;
            campoObligatotioFallo = true;
        } else if (idTutelaEstado == Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.HABI_CAMPO_ESTADO_SANSION_COMFIR))) {
            //this.getObjeto().getEstadoInicial().setDeshabilitarCampoFallo(false);
            deshabilitarCampoEstadoInicialClaseSancion = false;
            deshabilitarCampoEstadoInicialRepresentanteDemandado = false;
            campoObligatotioClaseSansion = true;
            campoObligatotioRepresentanteDemandado = true;
        } else if (idTutelaEstado == Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.HABI_CAMPO_ESTADO_TUTELA_NUEVA))) {
            deshabilitarCampoEstadoInicialMedidaProvisional = false;
            campoObligatorioMedidaProvisional = true;
        } else if (idTutelaEstado == Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.HABI_CAMPO_ESTADO_SANSION))) {
            deshabilitarCampoEstadoInicialClaseSancion = false;
            deshabilitarCampoEstadoInicialRepresentanteDemandado = false;
            campoObligatotioClaseSansion = true;
            campoObligatotioRepresentanteDemandado = true;
        }*/
    }

    public void limpiarAsignacionAutomatica() {
        this.objeto.getEstadoInicial().setTuGrupoId(null);
        this.objeto.getEstadoInicial().setAbogadoGnUsuarioId(null);
        this.objeto.getEstadoInicial().setGestorGnUsuarioId(null);
        this.objeto.getEstadoInicial().setMedicoGnUsuarioId(null);
    }

    public void limpiarAsignacionAdicionalesAutomatica() {
        this.objEstados.setTuGrupoId(null);
        this.objEstados.setAbogadoGnUsuarioId(null);
        this.objEstados.setGestorGnUsuarioId(null);
        this.objEstados.setMedicoGnUsuarioId(null);
    }

    public List<Usuario> completarUsuarios(String query) {
        List<Usuario> usuariosFiltrado = new ArrayList<>();
        for (Usuario user : getListaUsuarios()) {
            if (user.getNombre().toLowerCase().contains(query.toLowerCase())) {
                usuariosFiltrado.add(user);
            }
        }
        if (usuariosFiltrado.size() == 1) {
            //this.getServicioParaCrear().setIdUsuarioResponsable(usuariosFiltrado.get(0).getId());
            this.getObjEstados().getResponsableGnUsuarioId().setId(usuariosFiltrado.get(0).getId());
        }
        return usuariosFiltrado;
    }

    public void consultarGruposTutelas() {
        try {
            getTuTutelaServicio().consultarGruposEstadoInicialTutelas(this);
        } catch (Exception e) {
            this.addError("Error hacer el reparto automatico");
        }
        generarMensajes();
    }

    public void consultarGruposAdicionalesTutelas() {
        try {
            getTuTutelaServicio().consultarGruposEstadosAdicionalesTutelas(this);
        } catch (Exception e) {
            this.addError("Error hacer el reparto automatico");
        }
        generarMensajes();
    }

    public void consultarUsuariosGruposTutelas() {
        try {
            getTuTutelaServicio().consultarUsuariosGruposTutelasEstadoInicial(this);
        } catch (Exception e) {
            this.addError("Error hacer la consulta de grupos para los ususarios");
        }
        generarMensajes();
    }

    public void grupoAdicional(List<TuGrupoUsuario> tuGrupoUsuarios) {
        for (TuGrupoUsuario tuGrupoUsuario : tuGrupoUsuarios) {
            Usuario usuario = new Usuario();
            usuario.setId(tuGrupoUsuario.getUsuario().getId());
            usuario.setUsuario(tuGrupoUsuario.getUsuario().getUsuario());
            usuario.setNombre(tuGrupoUsuario.getUsuario().getNombre());
            switch (tuGrupoUsuario.getTipo()) {
                case TuGrupoUsuario.TIPO_ABOGADO:
                    this.objEstados.setAbogadoGnUsuarioId(usuario);
                    listaAbogados.add(usuario);
                    break;
                case TuGrupoUsuario.TIPO_GESTOR:
                    this.objEstados.setGestorGnUsuarioId(usuario);
                    listaGestores.add(usuario);
                    break;
                case TuGrupoUsuario.TIPO_MEDICO:
                    this.objEstados.setMedicoGnUsuarioId(usuario);
                    listaMedicos.add(usuario);
                    break;
            }
        }
    }

    public void consultarUsuariosGruposAdicionalesTutelas() {
        if (this.objEstados.getTuGrupoId() == null) {
            limpiarAsignacionAutomatica();
        }
        setListaAbogados(new ArrayList<>());
        setListaGestores(new ArrayList<>());
        setListaMedicos(new ArrayList<>());
        if (this.objEstados.getTuGrupoId() != null) {
            List<TuGrupoUsuario> tuGrupoUsuarios = getTuTutelaServicio().consultarUsuariosGruposAdicional(this);
            grupoAdicional(tuGrupoUsuarios);
        }
        generarMensajes();
    }

    public void consultarListaMaDiagnosticos() {
        crearOeditarItem = true;
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }

    public void cerrarDialogoDiagnostico(boolean crearOeditarItem) throws Exception {
        MaDiagnostico maObjeto = getSelDiagnosticosBean().getObjeto();
        TuDiagnostico tuDiagnostico = new TuDiagnostico();
        tuDiagnostico.setTuTutelasId(this.objeto);
        tuDiagnostico.setMaDiagnosticosId(maObjeto.getId());
        tuDiagnostico.setMaDiagnosticosCodigo(maObjeto.getCodigo());
        tuDiagnostico.setMaDiagnosticosValor(maObjeto.getNombre());
        if (maObjeto.getTipoDiagnostico() != null) {
            tuDiagnostico.setMaeTipoDiagnosticoId(maObjeto.getTipoDiagnostico().getId());
            tuDiagnostico.setMaeTipoDiagnosticoCodigo(maObjeto.getTipoDiagnostico().getNombre());
            tuDiagnostico.setMaeTipoDiagnosticoValor(maObjeto.getTipoDiagnostico().getDescripcion());
        }
        if (crearOeditarItem) {
            //auditoriaGuardar(tuDiagnostico);
            //tuDiagnostico.setPos(this.objTutulaItem.getTuTutelaDiagnosticosId().size());
            //this.objTutulaItem.getTuTutelaDiagnosticosId().add(tuDiagnostico);
            //PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            //PrimeFaces.current().ajax().update("frmCrearItemAdicional:tablaCrearDiagnostico");
            setCmAuditoriaDiagnostico(new TuDiagnostico());
            setCmAuditoriaDiagnostico(tuDiagnostico);
            PrimeFaces.current().ajax().update("frmCrearDiagnostico:panelDiagnosticoLista");
            PrimeFaces.current().executeScript("PF('dialogoCrearDiagnostico').show()");
        } else {
            if (idDiagostigoMemoria != null) {
                tuDiagnostico.setId(idDiagostigoMemoria);
                TuDiagnostico consultarDiagnostico = getTuTutelaServicio().consultarDiagnostico(idDiagostigoMemoria);
                if (consultarDiagnostico != null) {
                    tuDiagnostico.setEsPrincipal(consultarDiagnostico.isEsPrincipal());
                }
                setCmAuditoriaDiagnostico(new TuDiagnostico());
                setCmAuditoriaDiagnostico(tuDiagnostico);
                //super.setAccion(Url.ACCION_MODIFICAR);
                //super.setDoAccion(MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO);
                //getTuTutelaServicio().Accion(this);
                //setIdDiagostigoMemoria(null);
                //setEditarDiagnostico(null);
                PrimeFaces.current().ajax().update("frmEditarDiagnostico:panelEditarDiagnosticoLista");
                PrimeFaces.current().executeScript("PF('dialogoEditarDiagnostico').show()");
            }
        }
    }

    public void agregarDiagnostico() {
        boolean esValidaInsercion = true;
        for (TuDiagnostico diagnostico : this.objTutulaItem.getTuTutelaDiagnosticosId()) {
            if (diagnostico.isEsPrincipal() && getCmAuditoriaDiagnostico().isEsPrincipal()) {
                esValidaInsercion = false;
                this.addError("No puede haber más de un diagnóstico principal");
                break;
            }
        }
        if (esValidaInsercion) {
            auditoriaGuardar(getCmAuditoriaDiagnostico());
            getCmAuditoriaDiagnostico().setPos(this.objTutulaItem.getTuTutelaDiagnosticosId().size());
            this.objTutulaItem.getTuTutelaDiagnosticosId().add(getCmAuditoriaDiagnostico());
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:tablaCrearDiagnostico");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaEditarDiagnostico");
            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:tablaEditarDiagnostico");
            PrimeFaces.current().executeScript("PF('dialogoCrearDiagnostico').hide()");
        } else {
            PrimeFaces.current().ajax().update("frmCrearDiagnostico");
            generarMensajes();
        }

    }

    public void modificarDiagnostico() {
        boolean esValidaInsercion = true;
        for (TuDiagnostico diagnostico : this.objTutulaItem.getTuTutelaDiagnosticosId()) {
            if (!diagnostico.getId().equals(getCmAuditoriaDiagnostico().getId())
                    && diagnostico.isEsPrincipal() && getCmAuditoriaDiagnostico().isEsPrincipal()) {
                esValidaInsercion = false;
                this.addError("No puede haber más de un diagnóstico principal");
                break;
            }
        }

        if (esValidaInsercion) {
            setEditarDiagnostico(getCmAuditoriaDiagnostico());
            if (identificarEditarGestionDiaganostico == 1) {
                super.setAccion(Url.ACCION_MODIFICAR);
                super.setDoAccion(MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO);
            } else {
                super.setAccion(Url.ACCION_ADICIONAL_1);
                super.setDoAccion(MODIFICAR_TUTELA_ESTADO_ITEM_DIAGNOSTICO);
            }

            getTuTutelaServicio().Accion(this);
            setIdDiagostigoMemoria(null);
            setEditarDiagnostico(null);
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaEditarDiagnostico");
            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:tablaEditarDiagnostico");
            PrimeFaces.current().executeScript("PF('dialogoEditarDiagnostico').hide()");
            procesoFinal();
        } else {
            PrimeFaces.current().ajax().update("frmEditarDiagnostico");
            generarMensajes();
        }
    }

    public void editarDiagnosticoDb(int id) {
        crearOeditarItem = false;
        setIdDiagostigoMemoria(id);
        identificarEditarGestionDiaganostico = 1;
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }

    public void editarDiagnosticoGestionDb(int id) {
        crearOeditarItem = false;
        identificarEditarGestionDiaganostico = 0;
        setIdDiagostigoMemoria(id);
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }

    public void consultarSedesDestino() {
        try {
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrestadorIpsDestino:tablaRegistrosIpsDestino");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmPrestadorIpsDestino:tablaRegistrosIpsDestino");
            PrimeFaces.current().ajax().update("frmPrestadorIpsDestino:hPanelIpsDestino");
            //this.setParamConsultaPrestador(new ParamConsulta());
            this.getParamConsulta(4).setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmPrestadorIpsDestino");
            PrimeFaces.current().executeScript("PF('dialogoIpsDestino').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
        generarMensajes();
    }

    public void consultarSedesPrescripcion() {
        try {
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrestadorPrescripcion:tablaRegistrosPrescripcion");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmPrestadorPrescripcion:tablaRegistrosPrescripcion");
            PrimeFaces.current().ajax().update("frmPrestadorPrescripcion:hPanelPrescripcion");
            //this.setParamConsultaPrestador(new ParamConsulta());
            this.getParamConsulta(4).setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmPrestadorPrescripcion");
            PrimeFaces.current().executeScript("PF('dialogoPrescripcion').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
            generarMensajes();
        }
    }

    public void onRowSelectIpsDestino(SelectEvent event) {
        try {
            getObjTutulaItem().setDestinoCntPrestadorSedeId((CntPrestadorSede) event.getObject());
            getObjTutulaItem().setDestinoCntPrestadorSedeNombre(getObjTutulaItem().getDestinoCntPrestadorSedeId().getNombreSede());
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:sedeServicio");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:sedeServicio");
            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:sedeServicio");
            //PrimeFaces.current().ajax().update("frmEditarServicio:prestadorDestino");
            //PrimeFaces.current().ajax().update("frmEditarServicio:sedeServicioDestino");
            PrimeFaces.current().executeScript("PF('dialogoIpsDestino').hide()");
            PrimeFaces.current().executeScript("PF('tablaWidgetIPS').clearFilters()");
            PrimeFaces.current().executeScript("PF('tablaWidgetIPS').unselectAllRows()");
            PrimeFaces.current().resetInputs("frmPrestadorIpsDestino:tablaRegistrosIpsDestino");
            PrimeFaces.current().resetInputs("frmPrestadorIpsDestino:panelIpsDestino");
            PrimeFaces.current().ajax().update("frmPrestadorIpsDestino:tablaRegistrosIpsDestino");
            PrimeFaces.current().ajax().update("frmPrestadorIpsDestino");
            PrimeFaces.current().ajax().update("frmPrestadorIpsDestino:panelIpsDestino");
        } catch (Exception ex) {
            addError("Ocurrio un error al seleccionar la Destino: " + ex.toString());
            generarMensajes();
        }

    }

    public void onRowSelectPrescripcion(SelectEvent event) {
        try {
            getObjTutulaItem().setPrescripcionCntPrestadorSedeId((CntPrestadorSede) event.getObject());
            getObjTutulaItem().setPrescripcionCntPrestadorSedeNombre(getObjTutulaItem().getPrescripcionCntPrestadorSedeId().getNombreSede());
            PrimeFaces.current().ajax().update("frmCrearItemAdicional:sedePrescripcion");
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:sedePrescripcion");
            PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:sedePrescripcion");
            //PrimeFaces.current().ajax().update("frmCrearServicio:sedeServicioDestino");
            //PrimeFaces.current().ajax().update("frmEditarServicio:prestadorDestino");
            //PrimeFaces.current().ajax().update("frmEditarServicio:sedeServicioDestino");
            PrimeFaces.current().executeScript("PF('dialogoPrescripcion').hide()");
            PrimeFaces.current().executeScript("PF('tablaWidgetPrescripcion').clearFilters()");
            PrimeFaces.current().executeScript("PF('tablaWidgetPrescripcion').unselectAllRows()");
            PrimeFaces.current().resetInputs("frmPrestadorPrescripcion:tablaRegistrosPrescripcion");
            PrimeFaces.current().resetInputs("frmPrestadorPrescripcion:panelPrescripcion");
            PrimeFaces.current().ajax().update("frmPrestadorPrescripcion:tablaRegistrosPrescripcion");
            PrimeFaces.current().ajax().update("frmPrestadorPrescripcion");
            PrimeFaces.current().ajax().update("frmPrestadorPrescripcion:panelPrescripcion");
        } catch (Exception ex) {
            addError("Ocurrio un error al seleccionar la Prescripción: " + ex.toString());
            generarMensajes();
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

    public String obtenerMunicipio(int id) {
        try {
            return ubicacionesRecursiva.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void consultarTecnologia() {
        crearOeditarItem = true;
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void cerrarDialogoTecnologia(boolean crearItem) {
        if (crearItem) {
            objTutulaItem = new TuTutelaItem();
            //2025-07-01 jyperez inicializamos el objeto de usuario a cargar de la lista.
            objTutulaItem.setGnUsuarioAsignadoId(new Usuario());
            //2025-08-25 jyperez adicionamos la carga del valor por defecto del item
            Maestro estadoTutela = getHashValorEstadoSolicitud().get(TuTutelaItem.ESTADO_ITEM_NO_ASIGNADO);
            if (estadoTutela != null) {
                objTutulaItem.setMaeEstadoItemId(estadoTutela.getId());
                objTutulaItem.setMaeEstadoItemCodigo(estadoTutela.getValor());
                objTutulaItem.setMaeEstadoItemValor(estadoTutela.getNombre());
            }
        }
        MaTecnologia maTecnologia = getTecnologiasBean().getObjeto();
        objTutulaItem.setTipoServicio(1);
        objTutulaItem.setMaTecnologiaId(maTecnologia.getId());
        objTutulaItem.setMaTecnologiaCodigo(maTecnologia.getCodigoPropio());
        objTutulaItem.setMaTecnologiaValor(maTecnologia.getCupsDescipcion());
        //this.setDeshabilitarCamposSeguimientoPorMotivo(true);
        //evaluarHabilitarCampoPorEstadoInsert();
        if (crearItem) {
            PrimeFaces.current().resetInputs("frmCrearItemAdicional");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional");
            //2024-09-17 jyperez configuramos campo obligatorio para item tecnologias en true
            setCampoObligatorioItem(true);
            //2025-07-01 jyperez adicionamos consulta de usuarios para cargar la lista
            getTuTutelaServicio().listarUsuariosPorRol(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearItem').show()");
        } else {
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:infoTegnologia");
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
        }

    }

    public void consultarMedicamento() {
        crearOeditarItem = true;
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");

    }

    public void cerrarDialogoMedicamento(boolean crearItem) {
        if (crearItem) {
            objTutulaItem = new TuTutelaItem();
            //2025-07-01 jyperez inicializamos el objeto de usuario a cargar de la lista.
            objTutulaItem.setGnUsuarioAsignadoId(new Usuario());
            //2025-08-25 jyperez adicionamos la carga del valor por defecto del item
            Maestro estadoTutela = getHashValorEstadoSolicitud().get(TuTutelaItem.ESTADO_ITEM_NO_ASIGNADO);
            if (estadoTutela != null) {
                objTutulaItem.setMaeEstadoItemId(estadoTutela.getId());
                objTutulaItem.setMaeEstadoItemCodigo(estadoTutela.getValor());
                objTutulaItem.setMaeEstadoItemValor(estadoTutela.getNombre());
            }
        }
        MaMedicamento maMedicamento = getMedicamentosBean().getObjeto();
        objTutulaItem.setTipoServicio(2);
        objTutulaItem.setMaTecnologiaId(maMedicamento.getId());
        objTutulaItem.setMaTecnologiaCodigo(maMedicamento.getCum());
        objTutulaItem.setMaTecnologiaValor(maMedicamento.getDescripcionEstandarizada());
        //this.setDeshabilitarCamposSeguimientoPorMotivo(true);
        //evaluarHabilitarCampoPorEstadoInsert();
        if (crearItem) {
            PrimeFaces.current().resetInputs("frmCrearItemAdicional");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional");
            //2024-09-17 jyperez configuramos campo obligatorio para item medicamentos en true
            setCampoObligatorioItem(true);
            //2025-07-01 jyperez adicionamos consulta de usuarios para cargar la lista
            getTuTutelaServicio().listarUsuariosPorRol(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearItem').show()");
        } else {
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:infoTegnologia");
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
        }

    }

    public void consultarInsumo() {
        crearOeditarItem = true;
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");

    }

    public void cerrarDialogoInsumo(boolean crearItem) {
        if (crearItem) {
            objTutulaItem = new TuTutelaItem();
            //2025-07-01 jyperez inicializamos el objeto de usuario a cargar de la lista.
            objTutulaItem.setGnUsuarioAsignadoId(new Usuario());
            //2025-08-25 jyperez adicionamos la carga del valor por defecto del item
            Maestro estadoTutela = getHashValorEstadoSolicitud().get(TuTutelaItem.ESTADO_ITEM_NO_ASIGNADO);
            if (estadoTutela != null) {
                objTutulaItem.setMaeEstadoItemId(estadoTutela.getId());
                objTutulaItem.setMaeEstadoItemCodigo(estadoTutela.getValor());
                objTutulaItem.setMaeEstadoItemValor(estadoTutela.getNombre());
            }
        }
        MaInsumo maInsumo = getInsumosBean().getObjeto();
        objTutulaItem.setTipoServicio(3);
        objTutulaItem.setMaTecnologiaId(maInsumo.getId());
        objTutulaItem.setMaTecnologiaCodigo(maInsumo.getCodigo());
        objTutulaItem.setMaTecnologiaValor(maInsumo.getDescripcion());
        //this.setDeshabilitarCamposSeguimientoPorMotivo(true);
        //evaluarHabilitarCampoPorEstadoInsert();
        if (crearItem) {
            PrimeFaces.current().resetInputs("frmCrearItemAdicional");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional");
            //2024-09-17 jyperez configuramos campo obligatorio para item insumos en true
            setCampoObligatorioItem(true);
            //2025-07-01 jyperez adicionamos consulta de usuarios para cargar la lista
            getTuTutelaServicio().listarUsuariosPorRol(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearItem').show()");
        } else {
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:infoTegnologia");
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
        }

    }

    public void consultarPaquete() {
        crearOeditarItem = true;
        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
        PrimeFaces.current().ajax().update("frmPaqueteBusqueda");

    }

    public void cerrarDialogoPaquete(boolean crearItem) {
        if (crearItem) {
            objTutulaItem = new TuTutelaItem();
            //2025-07-01 jyperez inicializamos el objeto de usuario a cargar de la lista.
            objTutulaItem.setGnUsuarioAsignadoId(new Usuario());
            //2025-08-25 jyperez adicionamos la carga del valor por defecto del item
            Maestro estadoTutela = getHashValorEstadoSolicitud().get(TuTutelaItem.ESTADO_ITEM_NO_ASIGNADO);
            if (estadoTutela != null) {
                objTutulaItem.setMaeEstadoItemId(estadoTutela.getId());
                objTutulaItem.setMaeEstadoItemCodigo(estadoTutela.getValor());
                objTutulaItem.setMaeEstadoItemValor(estadoTutela.getNombre());
            }
        }
        MaPaquete maPaquete = getPaquetesBean().getObjeto();
        objTutulaItem.setTipoServicio(4);
        objTutulaItem.setMaTecnologiaId(maPaquete.getId());
        objTutulaItem.setMaTecnologiaCodigo(maPaquete.getCodigo());
        objTutulaItem.setMaTecnologiaValor(maPaquete.getNombre());
        //this.setDeshabilitarCamposSeguimientoPorMotivo(true);
        //evaluarHabilitarCampoPorEstadoInsert();
        if (crearItem) {
            PrimeFaces.current().resetInputs("frmCrearItemAdicional");
            PrimeFaces.current().ajax().update("frmCrearItemAdicional");
            //2024-09-17 jyperez configuramos campo obligatorio para paquetes en true
            setCampoObligatorioItem(true);
            //2025-07-01 jyperez adicionamos consulta de usuarios para cargar la lista
            getTuTutelaServicio().listarUsuariosPorRol(this);
            PrimeFaces.current().executeScript("PF('dialogoCrearItem').show()");
        } else {
            PrimeFaces.current().ajax().update("frmEditarItemAdicional:infoTegnologia");
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
        }

    }

    public void consultarNoprocedimiento() {
        //2024-10-08 jyperez se inicializa el objeto de tutelaItem
        objTutulaItem = new TuTutelaItem();
        //2025-07-01 jyperez inicializamos el objeto de usuario a cargar de la lista.
        objTutulaItem.setGnUsuarioAsignadoId(new Usuario());
        objTutulaItem.setTipoServicio(5);
        int idTecnologiPorDefecto = PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_CODIGO) == null ? ID_TECNOLOGIA_POR_DEFECTO : Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_CODIGO));
        String valorTecnologiPorDefecto = PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_VALOR) == null ? VALOR_TECNOLOGIA_POR_DEFECTO : PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TECNOLOGIA_POR_DEFECTO_VALOR);
        objTutulaItem.setMaTecnologiaId(idTecnologiPorDefecto);
        objTutulaItem.setMaTecnologiaCodigo(String.valueOf(idTecnologiPorDefecto));
        objTutulaItem.setMaTecnologiaValor(valorTecnologiPorDefecto);
        crearOeditarItem = true;
        //2024-09-17 jyperez configuramos campo obligatorio para item No procedimiento en false
        setCampoObligatorioItem(false);
        //2025-08-25 jyperez adicionamos la carga del valor por defecto del item
        Maestro estadoTutela = getHashValorEstadoSolicitud().get(TuTutelaItem.ESTADO_ITEM_NO_ASIGNADO);
        if (estadoTutela != null) {
            objTutulaItem.setMaeEstadoItemId(estadoTutela.getId());
            objTutulaItem.setMaeEstadoItemCodigo(estadoTutela.getValor());
            objTutulaItem.setMaeEstadoItemValor(estadoTutela.getNombre());
        }
        //2025-07-01 jyperez adicionamos consulta de usuarios para cargar la lista
        getTuTutelaServicio().listarUsuariosPorRol(this);
        PrimeFaces.current().resetInputs("frmCrearItemAdicional");
        PrimeFaces.current().ajax().update("frmCrearItemAdicional");
        PrimeFaces.current().executeScript("PF('dialogoCrearItem').show()");

    }

    public void verListarAnexo4() {
        //inicializarTablaAnexo4();  
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_ANEXOS_4);
        getTuTutelaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmListarAnexo4:tablaListarAnexo4");
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoListarAnexo4').show()");
        }
        procesoFinal();
    }

    public void verListarAnexo4Items(int idAnexo4) {
        getAuAnexo4().setId(idAnexo4);
        inicializarTablaAnexo4Items();
        DataTable tablaListarAnexo4 = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmListarAnexo4Items:tablaListarAnexo4Items");
        tablaListarAnexo4.reset();
        PrimeFaces.current().resetInputs("frmListarAnexo4Items");
        PrimeFaces.current().ajax().update("frmListarAnexo4Items");
        PrimeFaces.current().executeScript("PF('dialogoListarAnexo4Items').show()");
    }

    /*public void onRowSelectAutorizacion(SelectEvent event) {
        AuAnexo4 anexo = (AuAnexo4) event.getObject();
        AuAnexo4 autorizacion = new AuAnexo4();
        this.setCmAuditoriaAutorizacion(autorizacion);
        autorizacion.setId(anexo.getId());
        autorizacion.setMaServicioHabilitadoCodigo(String.valueOf(anexo.getMaServicioHabilitadoCodigo()));
        autorizacion.setMaServicioHabilitadoValor(anexo.getMaServicioHabilitadoValor());
        autorizacion.setFechaAutorizacion(anexo.getFechaAutorizacion());
        autorizacion.setNumeroAutorizacion(anexo.getNumeroAutorizacion());
        //autorizacion.setAnexo4(anexo);
        if (anexo.getCntPrestadorSedeId() != null) {
            CntPrestador prestador = Optional.ofNullable(anexo.getCntPrestadorSedeId().getCntPrestador()).orElse(new CntPrestador());
            if (prestador.getId() != null) {
                autorizacion.setPrestadorNombre(prestador.getRazonSocial());
                autorizacion.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
            }
        }

        PrimeFaces.current().resetInputs("frmEditarAutorizacion:panelAutorizacion");
        PrimeFaces.current().ajax().update("frmEditarAutorizacion:panelAutorizacion");
        PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').show()");
        generarMensajes();
    }*/
    
    public void onRowSelectAutorizacion(AuAnexo4 event) {
        AuAnexo4 anexo = event;
        AuAnexo4 autorizacion = new AuAnexo4();
        this.setCmAuditoriaAutorizacion(autorizacion);
        autorizacion.setId(anexo.getId());
        autorizacion.setMaServicioHabilitadoCodigo(String.valueOf(anexo.getMaServicioHabilitadoCodigo()));
        autorizacion.setMaServicioHabilitadoValor(anexo.getMaServicioHabilitadoValor());
        autorizacion.setFechaAutorizacion(anexo.getFechaAutorizacion());
        autorizacion.setNumeroAutorizacion(anexo.getNumeroAutorizacion());
        //autorizacion.setAnexo4(anexo);
        if (anexo.getCntPrestadorSedeId() != null) {
            CntPrestador prestador = Optional.ofNullable(anexo.getCntPrestadorSedeId().getCntPrestador()).orElse(new CntPrestador());
            if (prestador.getId() != null) {
                autorizacion.setPrestadorNombre(prestador.getRazonSocial());
                autorizacion.setPrestadorNumeroDocumento(prestador.getNumeroDocumento());
            }
        }

        PrimeFaces.current().resetInputs("frmEditarAutorizacion:panelAutorizacion");
        PrimeFaces.current().ajax().update("frmEditarAutorizacion:panelAutorizacion");
        PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').show()");
        generarMensajes();
    }
    
    public void agregarAutorizacion() {
        int idPosInsertar = 0;
        getCmAuditoriaAutorizacion().setNumeroAutorizacion(String.valueOf(getCmAuditoriaAutorizacion().getId()));
        getCmAuditoriaAutorizacion().setPos(idPosInsertar);
        this.objTutulaItem.getRegistrosAuditoriaAutorizacion().add(getCmAuditoriaAutorizacion());
        PrimeFaces.current().ajax().update("frmCrearItemAdicional:panelCrearAutorizaciones");
        PrimeFaces.current().ajax().update("frmCrearItemAdicional:tablaCrearAutorizaciones");
        PrimeFaces.current().ajax().update("frmEditarItemAdicional:panelEditarAutorizaciones");
        PrimeFaces.current().ajax().update("frmEditarItemAdicional:tablaEditarAutorizaciones");
        PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:panelEditarGestionAutorizaciones");
        PrimeFaces.current().ajax().update("frmEditarItemAdicionalGestion:tablaEditarGestionAutorizaciones");
        PrimeFaces.current().executeScript("PF('dialogoListarAnexo4').hide()");
        PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').hide()");
    }

    public void eventoAgregarListaEnviarA() {
        try {

            this.getObjEstadosSeguimiento().setTipoSeguimientoEnviarAsignacion(0);

            int idEstadoAsignar = Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_ESTADO_ASIGNAR));
            int idEstadoCierreParcial = Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.TIPO_ESTADO_CIERRE_PARCIAL));
            if (this.getObjEstadosSeguimiento().getMaeTipoSeguimientoId() == idEstadoAsignar
                    || this.getObjEstadosSeguimiento().getMaeTipoSeguimientoId() == idEstadoCierreParcial) {
                int idTipoSeguimiento = this.getObjEstadosSeguimiento().getMaeTipoSeguimientoId() == idEstadoAsignar
                        ? TIPO_ENVIO_SEGUIMIENTO_ASIGNAR : TIPO_ENVIO_SEGUIMIENTO_CIERRE_PARCIAL;
                this.getObjEstadosSeguimiento().setTipoSeguimientoEnviarAsignacion(idTipoSeguimiento);
                super.setAccion(ACCION_VER);
                super.setDoAccion(ACCION_VER_ENVIOS_SEGUIMIENTO);
                getTuTutelaServicio().Accion(this);
                if (isError()) {
                    this.getObjEstadosSeguimiento().setTipoSeguimientoEnviarAsignacion(0);
                }
            }
        } catch (Exception e) {
            this.getObjEstadosSeguimiento().setTipoSeguimientoEnviarAsignacion(0);
        }

        PrimeFaces.current().ajax().update("frmCrearSeguimientoAdicional");
    }

    public void limpiarDatosAuxiliaresContacto() {
        setTuPersonaContacto(new TuPersonaContacto());
        setListaTuPersonaContacto(new ArrayList<>());
    }

    public void pruebaAfiliado() {
        List<TuTutelaRespuesta> tuTutelaRespuestas = getTuTutelaServicio().consultarTutelaPorDocumento("CC", "98666517", Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.REST_TIPO_ESTADO_FALLO)));
        for (TuTutelaRespuesta tuTutelaRespuesta : tuTutelaRespuestas) {
            tuTutelaRespuesta.getNumeroTutela();
        }
    }

    public void imprimiMemorial() {

        try {
            String NOMBRE_ARCHIVO = "reporteMemorial.docx";
            getTuTutelaServicio().generarDocumentoMemorial(this);

            GeneradorDocumento generadorDocumento = new GeneradorDocumento();
            generadorDocumento.generarWord(getDocumentoMemorial().getTuTutelas().getId().toString(),
                     getDocumentoMemorial().getTuTutelasEstados().getTuJuzgadoId().getNombre(),
                     getDocumentoMemorial().getMaeReferenciaValor(),
                     getDocumentoMemorial().getTuTutelas().getTuPersona().getNombreCompleto(),
                     getDocumentoMemorial().getTuTutelas().getTuPersona().getNumeroDocumento(),
                     getDocumentoMemorial().getTuTutelas().getRadicadoNumero(),
                     getDocumentoMemorial().getTuPersonaApoderado().getNombreCompleto(),
                     getDocumentoMemorial().getTuPersonaApoderado().getNumeroDocumento(),
                     getDocumentoMemorial().getTuPersonaApoderado().getNumeroTarjetaProfesional(),
                     getDocumentoMemorial().getArgumentos(),
                     getDocumentoMemorial().getPretensiones(),
                     getDocumentoMemorial().getTuMemorialFirma().getFirma(),
                     getDocumentoMemorial().getTuPersonaApoderado().getMaeGnCargoValor(),
                     getDocumentoMemorial().getTuPersonaAsistente().getNombreCompleto(),
                     PropApl.getInstance().get(PropApl.TU_RUTA_CARGA),
                     NOMBRE_ARCHIVO);
            String rutaCompleta = PropApl.getInstance().get(PropApl.TU_RUTA_CARGA) + NOMBRE_ARCHIVO;
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
            String attachmentName = "attachment; filename=\"" + NOMBRE_ARCHIVO + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            ec.setResponseContentType("application/vnd.ms-excel");
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
            setDocumentoMemorial(new ReporteMemorial());
            PrimeFaces.current().resetInputs("frmCrearDocumentoMemorial");
            PrimeFaces.current().ajax().update("frmCrearDocumentoMemorial");
            PrimeFaces.current().executeScript("PF('dialogoCrearDocumentoMemorial').hide()");
        } catch (Exception ex) {
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
        }

        generarMensajes();
    }
    
    public void actualizarAsignacion() {
        
    }
    
    public void actualizarListaUsuariosPorRol() {
        getTuTutelaServicio().listarUsuariosPorRol(this);
    }
    
    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
    /**
     * Función para cargar dinámicamente la lista de Causa Tutela en Gestionar - Gestionar Estados - Agregar Item
     */
    public void cargarListaCausaTutela(){
        getTuTutelaServicio().consultarMaestroCausaTutelaPorTipoPresentacion(this);
    }

    /**
     * @return the listaTipoServicio
     */
    public List<Maestro> getListaTipoServicio() {
        return listaTipoServicio;
    }

    /**
     * @param listaTipoServicio the listaTipoServicio to set
     */
    public void setListaTipoServicio(List<Maestro> listaTipoServicio) {
        this.listaTipoServicio = listaTipoServicio;
    }

    /**
     * @return the hashTipoServicio
     */
    public HashMap<Integer, Maestro> getHashTipoServicio() {
        return hashTipoServicio;
    }

    /**
     * @param hashTipoServicio the hashTipoServicio to set
     */
    public void setHashTipoServicio(HashMap<Integer, Maestro> hashTipoServicio) {
        this.hashTipoServicio = hashTipoServicio;
    }

    /**
     * @return the campoObligatorioItem
     */
    public boolean isCampoObligatorioItem() {
        return campoObligatorioItem;
    }

    /**
     * @param campoObligatorioItem the campoObligatorioItem to set
     */
    public void setCampoObligatorioItem(boolean campoObligatorioItem) {
        this.campoObligatorioItem = campoObligatorioItem;
    }

    /**
     * @return the requeridoCampoEstadoIInicialIntegralidad
     */
    public boolean isRequeridoCampoEstadoIInicialIntegralidad() {
        return requeridoCampoEstadoIInicialIntegralidad;
    }

    /**
     * @param requeridoCampoEstadoIInicialIntegralidad the requeridoCampoEstadoIInicialIntegralidad to set
     */
    public void setRequeridoCampoEstadoIInicialIntegralidad(boolean requeridoCampoEstadoIInicialIntegralidad) {
        this.requeridoCampoEstadoIInicialIntegralidad = requeridoCampoEstadoIInicialIntegralidad;
    }

    /**
     * @return the listaTipoAsignacion
     */
    public List<Maestro> getListaTipoAsignacion() {
        return listaTipoAsignacion;
    }

    /**
     * @param listaTipoAsignacion the listaTipoAsignacion to set
     */
    public void setListaTipoAsignacion(List<Maestro> listaTipoAsignacion) {
        this.listaTipoAsignacion = listaTipoAsignacion;
    }

    /**
     * @return the hashTipoASignacion
     */
    public HashMap<Integer, Maestro> getHashTipoAsignacion() {
        return hashTipoAsignacion;
    }

    /**
     * @param hashTipoASignacion the hashTipoASignacion to set
     */
    public void setHashTipoAsignacion(HashMap<Integer, Maestro> hashTipoAsignacion) {
        this.hashTipoAsignacion = hashTipoAsignacion;
    }

    /**
     * @return the listaEstadoSolicitud
     */
    public List<Maestro> getListaEstadoSolicitud() {
        return listaEstadoSolicitud;
    }

    /**
     * @param listaEstadoSolicitud the listaEstadoSolicitud to set
     */
    public void setListaEstadoSolicitud(List<Maestro> listaEstadoSolicitud) {
        this.listaEstadoSolicitud = listaEstadoSolicitud;
    }

    /**
     * @return the hashEstadoSolicitud
     */
    public HashMap<Integer, Maestro> getHashEstadoSolicitud() {
        return hashEstadoSolicitud;
    }

    /**
     * @param hashEstadoSolicitud the hashEstadoSolicitud to set
     */
    public void setHashEstadoSolicitud(HashMap<Integer, Maestro> hashEstadoSolicitud) {
        this.hashEstadoSolicitud = hashEstadoSolicitud;
    }

    /**
     * @return the habilitarPanelItem
     */
    public boolean isHabilitarPanelItem() {
        return habilitarPanelItem;
    }

    /**
     * @param habilitarPanelItem the habilitarPanelItem to set
     */
    public void setHabilitarPanelItem(boolean habilitarPanelItem) {
        this.habilitarPanelItem = habilitarPanelItem;
    }

    /**
     * @return the descripcionGenerica
     */
    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    /**
     * @param descripcionGenerica the descripcionGenerica to set
     */
    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    /**
     * @return the hashValorEstadoSolicitud
     */
    public HashMap<String, Maestro> getHashValorEstadoSolicitud() {
        return hashValorEstadoSolicitud;
    }

    /**
     * @param hashValorEstadoSolicitud the hashValorEstadoSolicitud to set
     */
    public void setHashValorEstadoSolicitud(HashMap<String, Maestro> hashValorEstadoSolicitud) {
        this.hashValorEstadoSolicitud = hashValorEstadoSolicitud;
    }
}
