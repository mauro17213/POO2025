/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCasoServicio;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.dominio.atencionusuario.Tecnologia;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusCasoServicioIface;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import javax.faces.application.FacesMessage;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public final class AusCasoBean extends Url {

    private AusCasoServicioIface ausCasoServicio;
    private AusCaso objeto;
    private List<AusCaso> registros;
    private LazyDataModel<AusCaso> lazyAusCaso;
    private LazyDataModel<CntPrestadorSede> lazySedes;
    private LazyDataModel<AusPersona> lazyAusPersona;
    public static final char ACCION_BUSCAR_SEDES = 'a';
    public static final char ACCION_BUSCAR_PERSONAS = 'b';
    public static final char ACCION_BUSCAR_TELEFONOS_PERSONA = 'c';
    public static final char ACCION_REVERTIR_CASO = 'd';
    public static final char ACCION_CERRAR_CASO_AUTOMATICO = 'e';
    public static final char ACCION_DUPLICAR_SERVICIO = 'f';
    public static final char ACCION_VER_SERVICIO = 'g';
    public static final char ACCION_MODIFICAR_SERVICIO = 'h';
    public static final char ACCION_VER_SEGUIMIENTO = 'i';
    public static final char ACCION_MODIFICAR_GESTION = 'j';
    public static final char ACCION_EDITAR_SEGUIMIENTO = 'k';
    public static final char ACCION_MODIFICAR_SEGUIMIENTO = 'l';
    public static final char ACCION_BORRAR_SERVICIO = 'm';
    public static final int ORIGEN_CIERRE_MANUAL = 1;


    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    private List<Maestro> listaTipoEstadoSolicitud;
    private HashMap<Integer, Maestro> hashTipoEstadoSolicitud;

    private List<Maestro> listaSexo;
    private HashMap<Integer, Maestro> hashSexo;

    private List<Maestro> listaTipoSolicitudOrigen;
    private HashMap<Integer, Maestro> hashTipoSolicitudOrigen;

    private List<Maestro> listaTipoSolicitud;
    private HashMap<Integer, Maestro> hashTipoSolicitud;

    private List<Maestro> listaTipoSolicitudPrioridad;
    private HashMap<Integer, Maestro> hashTipoSolicitudPrioridad;

    private List<Maestro> listaTipoSolicitudEnteControl;
    private HashMap<Integer, Maestro> hashTipoSolicitudEnteControl;

    private List<Maestro> listaTipoSolicitudRiesgoVida;
    private HashMap<Integer, Maestro> hashTipoSolicitudRiesgoVida;

    private List<Maestro> listaEstadoServicio;
    private HashMap<Integer, Maestro> hashEstadoServicio;

    private List<Maestro> listaTipoServicioAmbito;
    private HashMap<Integer, Maestro> hashTipoServicioAmbito;

    private List<Maestro> listaTipoServicioMotivo;
    private HashMap<Integer, Maestro> hashTipoServicioMotivo;

    private List<Maestro> listaTipoServicio;
    private HashMap<Integer, Maestro> hashTipoServicio;

    private List<Maestro> listaTipoDiagnostico;
    private HashMap<Integer, Maestro> hashTipoDiagnostico;
    private LazyDataModel<Maestro> lazyDiagnostico;

    private List<Maestro> listaTipoServicioIPS;
    private HashMap<Integer, Maestro> hashTipoServicioIPS;

    private List<Maestro> listaTipoSeguimiento;
    private HashMap<Integer, Maestro> hashTipoSeguimiento;

    private List<Maestro> listaTipoEstadoPersona;
    private HashMap<Integer, Maestro> hashTipoEstadosPersona;

    private List<Maestro> listaTipoPatologia;
    private HashMap<Integer, Maestro> hashTipoPatologia;

    private List<Maestro> listaCanalSuperSalud;
    private HashMap<Integer, Maestro> hashCanalSuperSalud;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    private List<Maestro> listaTipoAdministrativo;
    private HashMap<Integer, Maestro> hashTipoAdministrativo;

    private List<Maestro> listaMotivoReabrirCaso;
    private HashMap<Integer, Maestro> hashMotivoReabrirCaso;

    private List<Maestro> listaTecnologiaAltoCosto;
    private HashMap<Integer, Maestro> hashTecnologiaAltoCosto;

    private List<Maestro> listaMotivo;
    private HashMap<Integer, Maestro> hashMotivo;

    private List<Maestro> listaTipoMotivo;
    private HashMap<Integer, Maestro> hashTipoMotivo;

    private List<Maestro> listaSubMotivo;
    private HashMap<Integer, Maestro> hashSubMotivo;

    private List<Maestro> listaCerrarCaso;
    private HashMap<Integer, Maestro> hashCerrarCaso;

    private boolean regimen;
    //private final static Map<String, Integer> listaEstadosServicio;
    private List<Maestro> listaEstadosServicio;
    //private final static Map<String, Integer> listaEstadosServicioEnCreacion;
    private List<Maestro> listaEstadosServicioEnCreacion;

    private List<AusPersonaTelefono> listaPersonaTelefono;
    private List<AusPersonaTelefono> listaPeticionarioTelefono;
    private List<AusAdjunto> listaAdjuntosCaso;
    private List<AusAdjunto> listaAdjuntosServicio;
    private List<AusAdjunto> listaAdjuntosSeguimiento;

    private AusPersonaBean personaBean;
    private AusPersonaBean personaBean2;
    private AusPersona crearPersona;
    private AusPersona crearPeticionario;
    private AusPersonaTelefono personaTelefono;
    private AusPersonaTelefono peticionarioTelefono;
    private int contadorArchivos = 0;
    private List<AusCasoServicio> listaServiciosCrear;
    private AusCasoServicio servicioParaCrear;
    private List<Usuario> listaUsuarios;
    private HashMap<Integer, Usuario> hashUsuarios;
    private boolean adjuntoVisualizarPDF;
    private boolean adjuntoVsisualizarOtraExtension;
    private String urlVisualizarAdjunto;
    private StreamedContent contenidoArchivoStream;
    private SelDiagnosticosBean diagnosticosBean;
    private SelEspecialidadesBean especialidadesBean;

    private List<CntPrestadorSede> ListaPrestadorSedes;
    private List<AusPersona> listaPersonasHistorial;
    private CntPrestadorSede objetoPrestadorSede;
    private CntPrestadorSede objetoPrestadorIpsDestino;
    private CntPrestador objetoPrestador;
    private CntPrestador objetoPrestadorIps;
    private List<Maestro> listaTiposDocumentoEmpresa;
    private AusCaso objBorrarCaso;
    private AusCaso objServicioResuelto;
    private AusCaso objRevertirCaso;
    private AusCaso objCerrarCaso;
    private List<AusCasoServicio> listaServiciosResultos;

    private SelMedicamentoBean medicamentosBean;
    private SelTecnologiasBean tecnologiasBean;
    private SelInsumosBean insumosBean;

    public static final String SESION_ADJUNTO_CASOS = "ajuntosCaso";
    public static final String SESION_ADJUNTO_SERVICIOS = "ajuntoServicios";
    public static final String SESION_ADJUNTO_SEGUIMIENTOS = "ajuntoSeguimientos";

    public static final String ORIGEN_PETICION_CREAR_CASO = "crearCaso";
    public static final String ORIGEN_PETICION_GESTIONAR_CASO = "gestionarCaso";

    public AusSeguimiento seguimientoProcesar;

    public boolean deshabilitarCampoSeguimiento;
    public boolean deshabilitarCampoNumeroRadicado;
    public boolean ocultarGestion;
    public String origenPeticion;
    public int habilidarCampoComentarioRevertirCaso;

    private final static Map<Integer, Integer> listaTiposEstratos;

    private int sizeLimitByAnexo;

    private int maxCantAnexos;

    private String urlDescargaAdjuntosCaso;
    private int horasSeguimiento;

    private Usuario user;

    private Integer edadAfiliado;

    private String peticionario;
    private String parentesco;
    public boolean habilitarPeticionario;
    private boolean camposObligatoriosPeticionario;

    private boolean isListarVencidos;
    private int cantidadVencidos;
    private int idCasoCierre;
    private String codigoCie;
    private String nombreCie;
    private Integer idCie;
    private String descripcionServicio;
    private String documentoPersona;
    private String descripcionGenerica;
    
    //2024-06-24 jyperez variables para la selección de varias tecnologias
    private static final int LIMITE_CANTIDAD_TECNOLOGIAS_SERVICIOS = 10;
    private List<Tecnologia> listaTecnologiaServicios;
    private boolean crearServicio;
    private int contTecnologiaServicios;
    
    //2025-03-12 jyperez variables para validación de casos en gestión con la misma tecnologia
    private List<AusCaso> listaCasosSimilares;
    private int tipoTecnologiaServicioDuplicado;
    private List<String> registrosServiciosDuplicados;
    private AusCasoServicio servicioParaConsultar;
    
    //2025-07-25 jyperez nuevo campo para almacenar el estado del servicio a modificar
    private int idEstadoServicioAnterior;
    // campos para servicios habilitar o inabilitar
    static {
        //listaEstadosServicio = AusCasoServicio.getTiposEstado();
        //listaEstadosServicioEnCreacion = AusCasoServicio.getTiposEstadoEnCreacion();
        listaTiposEstratos = AusPersona.getTiposEstratos();
    }

    public AusCasoBean() {
        this.objeto = new AusCaso();
        this.personaBean = new AusPersonaBean();
        this.personaBean2 = new AusPersonaBean();
        this.crearPersona = new AusPersona();
        this.crearPeticionario = new AusPersona();
        this.personaTelefono = new AusPersonaTelefono();
        this.peticionarioTelefono = new AusPersonaTelefono();
        servicioParaCrear = new AusCasoServicio();
        seguimientoProcesar = new AusSeguimiento();
        //2025-06-24 jyperez inicializamos la lista para las tecnologias
        this.listaTecnologiaServicios= new ArrayList();
        crearServicio = true;
        contTecnologiaServicios = 0;
        //2025-03-12 jyperez inicializamos la variable
        this.registrosServiciosDuplicados = new ArrayList();
        //agregarTiempoGrowl(TIEMPO_MENSAJES_DEFECTO);
        agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
        //setMaxCantAnexos(MAX_CANT_ANEXOS);
        setMaxCantAnexos(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.MAX_CANT_ANEXOS)));
        //setSizeLimitByAnexo(MAX_TAM_ANEXO);
        setSizeLimitByAnexo(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.MAX_TAM_ANEXO)));
        Modulo mod = super.validarModulo(Modulo.ID_AUS_CASOS);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());

        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);

            lazyAusCaso = new LazyDataModel<AusCaso>() {
                private List<AusCaso> ausCaso;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AusCaso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    ausCaso = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return ausCaso;
                }

                @Override
                public String getRowKey(AusCaso ausCaso) {
                    return ausCaso.getId().toString();
                }

                @Override
                public AusCaso getRowData(String casoId) {
                    Integer id = Integer.valueOf(casoId);
                    for (AusCaso ausCaso : ausCaso) {
                        if (id.equals(ausCaso.getId())) {
                            return ausCaso;
                        }
                    }
                    return null;
                }
            };
            lazySedes = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaSedes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(0).getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarSedes();
                    listaSedes = getListaPrestadorSedes();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
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
            lazyAusPersona = new LazyDataModel<AusPersona>() {
                private List<AusPersona> ausPersona;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(1).getCantidadRegistros();
                }

                @Override
                public List<AusPersona> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarPesonaHistorial();
                    ausPersona = getListaPersonasHistorial();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    //agrega registros hash datos

                    return ausPersona;
                }

                @Override
                public String getRowKey(AusPersona ausPersona) {
                    return ausPersona.getId().toString();
                }

                @Override
                public AusPersona getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (AusPersona ausPersona : ausPersona) {
                        if (id.equals(ausPersona.getId())) {
                            return ausPersona;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getAusCasoServicio().cargaInial(this);
        listar();
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        if (ubicacionesRecursiva == null) {
            ubicacionesRecursiva = new HashMap<>();
        }
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<Maestro> getListaTipoAdministrativo() {
        return listaTipoAdministrativo;
    }

    public void setListaTipoAdministrativo(List<Maestro> listaTipoAdministrativo) {
        this.listaTipoAdministrativo = listaTipoAdministrativo;
    }

    public HashMap<Integer, Maestro> getHashTipoAdministrativo() {
        return hashTipoAdministrativo;
    }

    public void setHashTipoAdministrativo(HashMap<Integer, Maestro> hashTipoAdministrativo) {
        this.hashTipoAdministrativo = hashTipoAdministrativo;
    }

    public List<Maestro> getListaMotivoReabrirCaso() {
        return listaMotivoReabrirCaso;
    }

    public void setListaMotivoReabrirCaso(List<Maestro> listaMotivoReabrirCaso) {
        this.listaMotivoReabrirCaso = listaMotivoReabrirCaso;
    }

    public HashMap<Integer, Maestro> getHashMotivoReabrirCaso() {
        return hashMotivoReabrirCaso;
    }

    public void setHashMotivoReabrirCaso(HashMap<Integer, Maestro> hashMotivoReabrirCaso) {
        this.hashMotivoReabrirCaso = hashMotivoReabrirCaso;
    }

    public List<Maestro> getListaTecnologiaAltoCosto() {
        return listaTecnologiaAltoCosto;
    }

    public void setListaTecnologiaAltoCosto(List<Maestro> listaTecnologiaAltoCosto) {
        this.listaTecnologiaAltoCosto = listaTecnologiaAltoCosto;
    }

    public HashMap<Integer, Maestro> getHashTecnologiaAltoCosto() {
        return hashTecnologiaAltoCosto;
    }

    public void setHashTecnologiaAltoCosto(HashMap<Integer, Maestro> hashTecnologiaAltoCosto) {
        this.hashTecnologiaAltoCosto = hashTecnologiaAltoCosto;
    }

    public List<Maestro> getListaMotivo() {
        return listaMotivo;
    }

    public void setListaMotivo(List<Maestro> listaMotivo) {
        this.listaMotivo = listaMotivo;
    }

    public HashMap<Integer, Maestro> getHashMotivo() {
        return hashMotivo;
    }

    public void setHashMotivo(HashMap<Integer, Maestro> hashMotivo) {
        this.hashMotivo = hashMotivo;
    }

    public List<Maestro> getListaTipoMotivo() {
        return listaTipoMotivo;
    }

    public void setListaTipoMotivo(List<Maestro> listaTipoMotivo) {
        this.listaTipoMotivo = listaTipoMotivo;
    }

    public HashMap<Integer, Maestro> getHashTipoMotivo() {
        if (hashTipoMotivo == null) {
            hashTipoMotivo = new HashMap<>();
        }
        return hashTipoMotivo;
    }

    public void setHashTipoMotivo(HashMap<Integer, Maestro> hashTipoMotivo) {
        this.hashTipoMotivo = hashTipoMotivo;
    }

    public List<Maestro> getListaSubMotivo() {
        return listaSubMotivo;
    }

    public void setListaSubMotivo(List<Maestro> listaSubMotivo) {
        this.listaSubMotivo = listaSubMotivo;
    }

    public HashMap<Integer, Maestro> getHashSubMotivo() {
        if (hashSubMotivo == null) {
            hashSubMotivo = new HashMap<>();
        }
        return hashSubMotivo;
    }

    public void setHashSubMotivo(HashMap<Integer, Maestro> hashSubMotivo) {
        this.hashSubMotivo = hashSubMotivo;
    }

    public List<Maestro> getListaCerrarCaso() {
        return listaCerrarCaso;
    }

    public void setListaCerrarCaso(List<Maestro> listaCerrarCaso) {
        this.listaCerrarCaso = listaCerrarCaso;
    }

    public HashMap<Integer, Maestro> getHashCerrarCaso() {
        return hashCerrarCaso;
    }

    public void setHashCerrarCaso(HashMap<Integer, Maestro> hashCerrarCaso) {
        this.hashCerrarCaso = hashCerrarCaso;
    }

    public AusCaso getObjeto() {
        return objeto;
    }

    public void setObjeto(AusCaso objeto) {
        this.objeto = objeto;
    }

    public List<AusCaso> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AusCaso> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AusCaso> getLazyCaso() {
        return lazyAusCaso;
    }

    public LazyDataModel<AusPersona> getLazyAusPersona() {
        return lazyAusPersona;
    }

    public void setLazyAusPersona(LazyDataModel<AusPersona> lazyAusPersona) {
        this.lazyAusPersona = lazyAusPersona;
    }

    public void setLazyCaso(LazyDataModel<AusCaso> lazyCaso) {
        this.lazyAusCaso = lazyCaso;
    }

    public LazyDataModel<Maestro> getLazyDiagnostico() {
        return lazyDiagnostico;
    }

    public void setLazyDiagnostico(LazyDataModel<Maestro> lazyDiagnostico) {
        this.lazyDiagnostico = lazyDiagnostico;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public String getTipoDocumento(int id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
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

    public boolean isRegimen() {
        return regimen;
    }

    public boolean getRegimen() {
        return regimen;
    }

    public void setRegimen(boolean regimen) {
        this.regimen = regimen;
    }

    public String getSexo(int id) {
        try {
            return hashSexo.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public AusPersona getCrearPersona() {
        return crearPersona;
    }

    public void setCrearPersona(AusPersona crearPersona) {
        this.crearPersona = crearPersona;
    }

    public AusPersona getCrearPeticionario() {
        return crearPeticionario;
    }

    public void setCrearPeticionario(AusPersona crearPeticionario) {
        this.crearPeticionario = crearPeticionario;
    }

    public Integer getEdadAfiliado() {
        setEdadAfiliado(this.objeto.getAusPersonasId().getEdad());
        return edadAfiliado;
    }

    public void setEdadAfiliado(Integer edadAfiliado) {
        this.edadAfiliado = edadAfiliado;
    }

    public void setHashSexo(HashMap<Integer, Maestro> hashSexo) {
        this.hashSexo = hashSexo;
    }

    public String getTipoEstadoPersona(int id) {
        try {
            return hashTipoEstadosPersona.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTipoEstadoPersona() {
        return listaTipoEstadoPersona;
    }

    public void setListaTipoEstadoPersona(List<Maestro> listaTipoEstadoPersona) {
        this.listaTipoEstadoPersona = listaTipoEstadoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadosPersona() {
        return hashTipoEstadosPersona;
    }

    public void setHashTipoEstadosPersona(HashMap<Integer, Maestro> hashTipoEstadosPersona) {
        this.hashTipoEstadosPersona = hashTipoEstadosPersona;
    }

    public List<Maestro> getListaTipoEstadoSolicitud() {
        return listaTipoEstadoSolicitud;
    }

    public void setListaTipoEstadoSolicitud(List<Maestro> listaTipoEstadoSolicitud) {
        this.listaTipoEstadoSolicitud = listaTipoEstadoSolicitud;
    }

    public HashMap<Integer, Maestro> getHashTipoEstadoSolicitud() {
        return hashTipoEstadoSolicitud;
    }

    public String getTipoEstadoSolicitud(int id) {
        try {
            String nombre = hashTipoEstadoSolicitud.get(id).getNombre();
            this.objeto.setEstado(nombre);
            return nombre;
        } catch (Exception e) {
            return "";
        }
    }

    public void setHashTipoEstadoSolicitud(HashMap<Integer, Maestro> hashTipoEstadoSolicitud) {
        this.hashTipoEstadoSolicitud = hashTipoEstadoSolicitud;
    }

    public AusCasoServicioIface getAusCasoServicio() {
        return ausCasoServicio;
    }

    public void setAusCasoServicio(AusCasoServicioIface ausCasoServicio) {
        this.ausCasoServicio = ausCasoServicio;
    }

    public LazyDataModel<AusCaso> getLazyAusCaso() {
        return lazyAusCaso;
    }

    public void setLazyAusCaso(LazyDataModel<AusCaso> lazyAusCaso) {
        this.lazyAusCaso = lazyAusCaso;
    }

    public LazyDataModel<CntPrestadorSede> getLazySedes() {
        return lazySedes;
    }

    public void setLazySedes(LazyDataModel<CntPrestadorSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public List<Maestro> getListaTipoSolicitud() {
        return listaTipoSolicitud;
    }

    public void setListaTipoSolicitud(List<Maestro> listaTipoSolicitud) {
        this.listaTipoSolicitud = listaTipoSolicitud;
    }

    public String getTipoSolicitud(int id) {
        try {
            return hashTipoSolicitud.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitud() {
        return hashTipoSolicitud;
    }

    public void setHashTipoSolicitud(HashMap<Integer, Maestro> hashTipoSolicitud) {
        this.hashTipoSolicitud = hashTipoSolicitud;
    }

    public List<Maestro> getListaTipoSolicitudOrigen() {
        return listaTipoSolicitudOrigen;
    }

    public void setListaTipoSolicitudOrigen(List<Maestro> listaTipoSolicitudOrigen) {
        this.listaTipoSolicitudOrigen = listaTipoSolicitudOrigen;
    }

    public String getTipoSolicitudOrigen(int id) {
        try {
            return hashTipoSolicitudOrigen.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudOrigen() {
        return hashTipoSolicitudOrigen;
    }

    public void setHashTipoSolicitudOrigen(HashMap<Integer, Maestro> hashTipoSolicitudOrigen) {
        this.hashTipoSolicitudOrigen = hashTipoSolicitudOrigen;
    }

    public List<Maestro> getListaTipoSolicitudPrioridad() {
        return listaTipoSolicitudPrioridad;
    }

    public void setListaTipoSolicitudPrioridad(List<Maestro> listaTipoSolicitudPrioridad) {
        this.listaTipoSolicitudPrioridad = listaTipoSolicitudPrioridad;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudPrioridad() {
        return hashTipoSolicitudPrioridad;
    }

    public void setHashTipoSolicitudPrioridad(HashMap<Integer, Maestro> hashTipoSolicitudPrioridad) {
        this.hashTipoSolicitudPrioridad = hashTipoSolicitudPrioridad;
    }

    public String getTipoSolicitudPrioridad(int id) {
        try {
            return hashTipoSolicitudPrioridad.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTipoSolicitudEnteControl() {
        return listaTipoSolicitudEnteControl;
    }

    public void setListaTipoSolicitudEnteControl(List<Maestro> listaTipoSolicitudEnteControl) {
        this.listaTipoSolicitudEnteControl = listaTipoSolicitudEnteControl;
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudEnteControl() {
        return hashTipoSolicitudEnteControl;
    }

    public String getTipoSolicitudEnteControl(int id) {
        try {
            return hashTipoSolicitudEnteControl.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void setHashTipoSolicitudEnteControl(HashMap<Integer, Maestro> hashTipoSolicitudEnteControl) {
        this.hashTipoSolicitudEnteControl = hashTipoSolicitudEnteControl;
    }

    public List<Maestro> getListaTipoSolicitudRiesgoVida() {
        return listaTipoSolicitudRiesgoVida;
    }

    public void setListaTipoSolicitudRiesgoVida(List<Maestro> listaTipoSolicitudRiesgoVida) {
        this.listaTipoSolicitudRiesgoVida = listaTipoSolicitudRiesgoVida;
    }

    public String getTipoSolicitudRiegoVida(int id) {
        try {
            return hashTipoSolicitudRiesgoVida.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public HashMap<Integer, Maestro> getHashTipoSolicitudRiesgoVida() {
        return hashTipoSolicitudRiesgoVida;
    }

    public void setHashTipoSolicitudRiesgoVida(HashMap<Integer, Maestro> hashTipoSolicitudRiesgoVida) {
        this.hashTipoSolicitudRiesgoVida = hashTipoSolicitudRiesgoVida;
    }

    public List<Maestro> getListaEstadoServicio() {
        return listaEstadoServicio;
    }

    public void setListaEstadoServicio(List<Maestro> listaEstadoServicio) {
        this.listaEstadoServicio = listaEstadoServicio;
    }

    public HashMap<Integer, Maestro> getHashEstadoServicio() {
        return hashEstadoServicio;
    }

    public void setHashEstadoServicio(HashMap<Integer, Maestro> hashEstadoServicio) {
        this.hashEstadoServicio = hashEstadoServicio;
    }

    public List<Maestro> getListaTipoServicioAmbito() {
        return listaTipoServicioAmbito;
    }

    public void setListaTipoServicioAmbito(List<Maestro> listaTipoServicioAmbito) {
        this.listaTipoServicioAmbito = listaTipoServicioAmbito;
    }

    public HashMap<Integer, Maestro> getHashTipoServicioAmbito() {
        return hashTipoServicioAmbito;
    }

    public void setHashTipoServicioAmbito(HashMap<Integer, Maestro> hashTipoServicioAmbito) {
        this.hashTipoServicioAmbito = hashTipoServicioAmbito;
    }

    public String getTipoServicioAmbito(int id) {
        try {
            return hashTipoServicioAmbito.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTipoServicioMotivo() {
        return listaTipoServicioMotivo;
    }

    public void setListaTipoServicioMotivo(List<Maestro> listaTipoServicioMotivo) {
        this.listaTipoServicioMotivo = listaTipoServicioMotivo;
    }

    public HashMap<Integer, Maestro> getHashTipoServicioMotivo() {
        return hashTipoServicioMotivo;
    }

    public void setHashTipoServicioMotivo(HashMap<Integer, Maestro> hashTipoServicioMotivo) {
        this.hashTipoServicioMotivo = hashTipoServicioMotivo;
    }

    public String getTipoServicioMotivo(int id) {
        try {
            return hashTipoServicioMotivo.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTipoServicio() {
        return listaTipoServicio;
    }

    public void setListaTipoServicio(List<Maestro> listaTipoServicio) {
        this.listaTipoServicio = listaTipoServicio;
    }

    public HashMap<Integer, Maestro> getHashTipoServicio() {
        return hashTipoServicio;
    }

    public void setHashTipoServicio(HashMap<Integer, Maestro> hashTipoServicio) {
        this.hashTipoServicio = hashTipoServicio;
    }

    public String getTipoServicio(int id) {
        try {
            return hashTipoServicio.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
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

    public String getTipoDiagnostico(int id) {
        try {
            return hashTipoDiagnostico.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTipoPatologia() {
        return listaTipoPatologia;
    }

    public void setListaTipoPatologia(List<Maestro> listaTipoPatologia) {
        this.listaTipoPatologia = listaTipoPatologia;
    }

    public HashMap<Integer, Maestro> getHashTipoPatologia() {
        return hashTipoPatologia;
    }

    public void setHashTipoPatologia(HashMap<Integer, Maestro> hashTipoPatologia) {
        this.hashTipoPatologia = hashTipoPatologia;
    }

    public String getTipoPatologia(int id) {
        try {
            return hashTipoPatologia.get(id).getNombre();
        } catch (Exception e) {
            return " ";
        }
    }

    public String getTipoDiagnosticoVer(int id) {
        try {
            //super.setAccion(ACCION_LISTAR);
            //super.setDoAccion(ACCION_MODIFICAR);
            //setIdCie(id);
            //getAusCasoServicio().Accion(this);
            return hashTipoDiagnostico.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTipoServicioIPS() {
        return listaTipoServicioIPS;
    }

    public void setListaTipoServicioIPS(List<Maestro> listaTipoServicioIPS) {
        this.listaTipoServicioIPS = listaTipoServicioIPS;
    }

    public HashMap<Integer, Maestro> getHashTipoServicioIPS() {
        return hashTipoServicioIPS;
    }

    public void setHashTipoServicioIPS(HashMap<Integer, Maestro> hashTipoServicioIPS) {
        this.hashTipoServicioIPS = hashTipoServicioIPS;
    }

    public String getTipoServicioIPS(int id) {
        try {
            return hashTipoServicioIPS.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getNombreUsuario(List<Usuario> lista) {
        try {
            return lista.get(2).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Maestro> getListaTipoSeguimiento() {
        return listaTipoSeguimiento;
    }

    public SelDiagnosticosBean getDiagnosticosBean() {
        return diagnosticosBean;
    }

    public void setDiagnosticosBean(SelDiagnosticosBean diagnosticosBean) {
        this.diagnosticosBean = diagnosticosBean;
    }

    public SelEspecialidadesBean getEspecialidadesBean() {
        return especialidadesBean;
    }

    public void setEspecialidadesBean(SelEspecialidadesBean especialidadesBean) {
        this.especialidadesBean = especialidadesBean;
    }

    public List<Maestro> filtroTiposSeguimientosAceptados() {
        List<Maestro> tiposSeguimientosFiltrados = new ArrayList<>();
        if (getListaTipoSeguimiento() != null) {
            for (Maestro maestro : getListaTipoSeguimiento()) {
                //if (getIdTipoSeguimiento(AusCasoBean.DESC_SEGUIMIENTO_RADICADO) != maestro.getId()) {
                if (Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_RADIC)) != maestro.getId()) {
                    tiposSeguimientosFiltrados.add(maestro);
                }
            }
        }

        return tiposSeguimientosFiltrados;
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

    public String getTipoSeguimiento(int id) {
        try {
            return hashTipoSeguimiento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<AusPersonaTelefono> getListaPersonaTelefono() {
        return listaPersonaTelefono;
    }

    public void setListaPersonaTelefono(List<AusPersonaTelefono> listaPersonaTelefono) {
        this.listaPersonaTelefono = listaPersonaTelefono;
    }

    public List<AusPersonaTelefono> getListaPeticionarioTelefono() {
        return listaPeticionarioTelefono;
    }

    public void setListaPeticionarioTelefono(List<AusPersonaTelefono> listaPeticionarioTelefono) {
        this.listaPeticionarioTelefono = listaPeticionarioTelefono;
    }

    public AusPersonaBean getPersonaBean() {
        return personaBean;
    }

    public void setPersonaBean(AusPersonaBean personaBean) {
        this.personaBean = personaBean;
    }

    public AusPersonaBean getPersonaBean2() {
        return personaBean2;
    }

    public void setPersonaBean2(AusPersonaBean personaBean2) {
        this.personaBean2 = personaBean2;
    }

    public AusPersonaTelefono getPersonaTelefono() {
        return personaTelefono;
    }

    public void setPersonaTelefono(AusPersonaTelefono personaTelefono) {
        this.personaTelefono = personaTelefono;
    }

    public AusPersonaTelefono getPeticionarioTelefono() {
        return peticionarioTelefono;
    }

    public void setPeticionarioTelefono(AusPersonaTelefono peticionarioTelefono) {
        this.peticionarioTelefono = peticionarioTelefono;
    }

    public AusCasoServicio getServicioParaCrear() {
        return servicioParaCrear;
    }

    public void setServicioParaCrear(AusCasoServicio ServicioParaCrear) {
        this.servicioParaCrear = ServicioParaCrear;
    }

    public List<AusCasoServicio> getListaServiciosCrear() {
        return listaServiciosCrear;
    }

    public void setListaServiciosCrear(List<AusCasoServicio> listaServiciosCrear) {
        this.listaServiciosCrear = listaServiciosCrear;
    }

    public List<Maestro> getListaEstadosServicio() {
        return listaEstadosServicio;
    }

    public List<Maestro> getListaEstadosServicioEnCreacion() {
        return listaEstadosServicioEnCreacion;
    }

    public void setListaEstadosServicioEnCreacion(List<Maestro> listaEstadosServicioEnCreacion) {
        this.listaEstadosServicioEnCreacion = listaEstadosServicioEnCreacion;
    }

    public String getOrigenPeticion() {
        return origenPeticion;
    }

    public void setOrigenPeticion(String origenPeticion) {
        this.origenPeticion = origenPeticion;
    }

    public int getHabilidarCampoComentarioRevertirCaso() {
        return habilidarCampoComentarioRevertirCaso;
    }

    public void setHabilidarCampoComentarioRevertirCaso(int habilidarCampoComentarioRevertirCaso) {
        this.habilidarCampoComentarioRevertirCaso = habilidarCampoComentarioRevertirCaso;
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

    public String getUrlDescargaAdjuntosCaso() {

        /*if( urlDescargaAdjuntosCaso == null || urlDescargaAdjuntosCaso == ""){
            urlDescargaAdjuntosCaso = Parametrizacion.URL_DESCARGA_ADJUNTOS_DEFECTO;
        }*/
        return urlDescargaAdjuntosCaso;
    }

    public void setUrlDescargaAdjuntosCaso(String urlDescargaAdjuntosCaso) {
        this.urlDescargaAdjuntosCaso = urlDescargaAdjuntosCaso;
    }

    public List<Maestro> asignarListaEstadosServicioFiltrada() {
        String origenPeticion = getOrigenPeticion() == null ? "" : getOrigenPeticion();
        List<Maestro> listaEstado = getListaEstadoServicio();
        if (origenPeticion.equalsIgnoreCase(ORIGEN_PETICION_CREAR_CASO)
                || origenPeticion.equalsIgnoreCase(ORIGEN_PETICION_GESTIONAR_CASO)) {
            listaEstado = getListaEstadosServicioEnCreacion();
        }
        return listaEstado;
    }

    public String getTipoEstadoServicio(int id) {
        try {
            return AusCasoServicio.getEstadoStr(id);
        } catch (Exception e) {
            return "";
        }
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

    public String getUsuario(int id) {
        try {
            return hashUsuarios.get(id).getNombre() + " " + hashUsuarios.get(id).getUsuario();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isAdjuntoVisualizarPDF() {
        return adjuntoVisualizarPDF;
    }

    public void setAdjuntoVisualizarPDF(boolean adjuntoVisualizarPDF) {
        this.adjuntoVisualizarPDF = adjuntoVisualizarPDF;
    }

    public boolean isAdjuntoVsisualizarOtraExtension() {
        return adjuntoVsisualizarOtraExtension;
    }

    public void setAdjuntoVsisualizarOtraExtension(boolean adjuntoVsisualizarOtraExtension) {
        this.adjuntoVsisualizarOtraExtension = adjuntoVsisualizarOtraExtension;
    }

    public String getUrlVisualizarAdjunto() {
        return urlVisualizarAdjunto;
    }

    public void setUrlVisualizarAdjunto(String urlVisualizarAdjunto) {
        this.urlVisualizarAdjunto = urlVisualizarAdjunto;
    }

    public List<AusAdjunto> getListaAdjuntosCaso() {
        return listaAdjuntosCaso;
    }

    public void setListaAdjuntosCaso(List<AusAdjunto> listaAdjuntosCaso) {
        this.listaAdjuntosCaso = listaAdjuntosCaso;
    }

    public StreamedContent getContenidoArchivoStream() {
        return contenidoArchivoStream;
    }

    public void setContenidoArchivoStream(StreamedContent contenidoArchivoStream) {
        this.contenidoArchivoStream = contenidoArchivoStream;
    }

    public List<AusAdjunto> getListaAdjuntosServicio() {
        return listaAdjuntosServicio;
    }

    public void setListaAdjuntosServicio(List<AusAdjunto> listaAdjuntosServicio) {
        this.listaAdjuntosServicio = listaAdjuntosServicio;
    }

    public AusSeguimiento getSeguimientoProcesar() {
        return seguimientoProcesar;
    }

    public void setSeguimientoProcesar(AusSeguimiento seguimientoProcesar) {
        this.seguimientoProcesar = seguimientoProcesar;
    }

    public List<AusAdjunto> getListaAdjuntosSeguimiento() {
        return listaAdjuntosSeguimiento;
    }

    public void setListaAdjuntosSeguimiento(List<AusAdjunto> listaAdjuntosSeguimiento) {
        this.listaAdjuntosSeguimiento = listaAdjuntosSeguimiento;
    }

    public boolean isDeshabilitarCampoSeguimiento() {
        return deshabilitarCampoSeguimiento;
    }

    public String getDocumentoPersona() {
        return documentoPersona;
    }

    public void setDocumentoPersona(String documentoPersona) {
        this.documentoPersona = documentoPersona;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    public boolean getOcultarGestion() {
        return ocultarGestion;
    }

    public boolean isOcultarGestion() {
        return ocultarGestion;
    }

    public void setOcultarGestion(boolean ocultarGestion) {
        this.ocultarGestion = ocultarGestion;
    }

    public void setDeshabilitarCamposSeguimientoPorMotivo(boolean deshabilitarCampoSeguimiento) {
        this.deshabilitarCampoSeguimiento = deshabilitarCampoSeguimiento;
        this.getServicioParaCrear().setDeshabilitarCampoAsignado(deshabilitarCampoSeguimiento);
        this.getServicioParaCrear().setDeshabilitarCampoCantidad(deshabilitarCampoSeguimiento);
        this.getServicioParaCrear().setDeshabilitarCampoDiagnostico(deshabilitarCampoSeguimiento);
        this.getServicioParaCrear().setDeshabilitarCampoEspecialidad(deshabilitarCampoSeguimiento);
        this.getServicioParaCrear().setDeshabilitarCampoIpsPres(deshabilitarCampoSeguimiento);
        this.getServicioParaCrear().setDesHabilitarCampoDescripcion(deshabilitarCampoSeguimiento);
    }

    public void setDeshabilitarCamposSeguimientoPorEstado(boolean activarCampo) {
        int idEstadoActual = this.getServicioParaCrear().getMaeEstadoId();
        //int idEstadoCierre = getListaEstadosServicio().get("Cerrado");
        int idEstadoCierre = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO));
        //int idEstadoResuelto = getListaEstadosServicio().get("Resuelto");
        int idEstadoResuelto = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO));
        if (idEstadoActual != idEstadoCierre && idEstadoActual != idEstadoResuelto) {
            this.getServicioParaCrear().setDesHabilitarCampoDescripcion(activarCampo);
        } else {
            this.getServicioParaCrear().setDesHabilitarCampoDescripcion(!activarCampo);
        }
        this.getServicioParaCrear().setDeshabilitarCampoAsignado(activarCampo);
    }
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }
    
    public void refrescar() {
        if (!isListarVencidos) {
            super.setAccion(Url.ACCION_LISTAR);
            super.setDoAccion(Url.ACCION_LISTAR);
            getAusCasoServicio().Accion(this);
            //procesoFinal();
        } else {
            isListarVencidos = false;
        }
    }

    public void refrescarSedes() {

        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_BUSCAR_SEDES);
        getAusCasoServicio().Accion(this);

    }

    public void refrescarPesonaHistorial() {

        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_BUSCAR_PERSONAS);
        getAusCasoServicio().Accion(this);

    }

    public void listarVencidos() {
        //int idEstadoSolicitud = getIdTipoEstadoSolicitud("Radicado");
        int idEstadoSolicitud = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_RADICADO));
        this.getParamConsulta().getFiltros().put("maeSolicitudEstado2", idEstadoSolicitud);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.getParamConsulta().getFiltros().put("fechaVencimiento", formato.format(new Date()));
        setIsListarVencidos(true);
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getAusCasoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoCasosVencidos').hide()");
        PrimeFaces.current().ajax().update("frmCasos");
    }

    public void contarVencidos() {
        //int idEstadoSolicitud = getIdTipoEstadoSolicitud("Radicado");
        int idEstadoSolicitud = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_RADICADO));
        this.getParamConsulta().getFiltros().put("maeSolicitudEstado2", idEstadoSolicitud);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.getParamConsulta().getFiltros().put("fechaVencimiento", formato.format(new Date()));
        getAusCasoServicio().contarVencidos(this);
        PrimeFaces.current().ajax().update("frmCasosVencidos");
        PrimeFaces.current().executeScript("PF('dialogoCasosVencidos').show()");
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getAusCasoServicio().Accion(this);       
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmAuditoriaGeneralVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verGestion(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getAusCasoServicio().Accion(this);
        //agregarTiempoGrowl(TIEMPO_MENSAJES_DEFECTO);
        agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
        listaServiciosCrear = new ArrayList(this.getObjeto().getServiciosList());
        listaPersonaTelefono = new ArrayList();
        listaPeticionarioTelefono = new ArrayList();
        listaAdjuntosCaso = new ArrayList();
        this.getObjeto().setRenderAgregarSeguimientos(verificarEstadoSegCerradoSolucionado());
        PrimeFaces.current().ajax().update("frmGestion");
        PrimeFaces.current().ajax().update("frmAuditoriaGestionar");
        PrimeFaces.current().executeScript("PF('dialogoGestion').show()");
        procesoFinal();
    }

    public void verHistorialPersonal(String documento) {
        try {
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPersonasHistorial:tablaRegistrosPersonasHistorial");
            //dataTable2.clearLazyCache();
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            this.setParamConsulta(new ParamConsulta());
            this.setDocumentoPersona(documento);
            //getParamConsultaPersonaHistorial().getFiltros().put("documento", documento);
            PrimeFaces.current().resetInputs("frmPersonasHistorial:tablaRegistrosPersonasHistorial");
            PrimeFaces.current().ajax().update("frmPersonasHistorial:hPanelPersonasHistorial");
            PrimeFaces.current().ajax().update("frmPersonasHistorial");
            PrimeFaces.current().executeScript("PF('dialogoPersonasHistorial').show()");
            procesoFinal();
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void verServicio(int _id) {
        getServicioParaCrear().setId(_id);
        //getAusCasoServicio().consultarUsuario(this);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_SERVICIO);
        getAusCasoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerServicio");
        PrimeFaces.current().executeScript("PF('dialogoVerServicio').show()");
        procesoFinal();
    }

    public void verServicioResueltos(int _id) {
        setObjServicioResuelto(new AusCaso());
        getObjServicioResuelto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_3);
        getAusCasoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmServiciosHistorial");
            PrimeFaces.current().executeScript("PF('dialogoServiciosHistorial').show()");
        }

        procesoFinal();
    }

    public void revertirCasoGestion(int _id) {
        setHabilidarCampoComentarioRevertirCaso(0);
        setObjRevertirCaso(new AusCaso());
        getObjRevertirCaso().setId(_id);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_ADICIONAL_4);
        getAusCasoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmRevertirCaso");
            PrimeFaces.current().executeScript("PF('dialogoRevertirCaso').show()");
        }

        procesoFinal();
    }

    public void cerrarCasoGestion(int _id) {
        setObjCerrarCaso(new AusCaso());
        setSeguimientoProcesar(new AusSeguimiento());
        getObjCerrarCaso().setId(_id);
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_ADICIONAL_5);
        getAusCasoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCerrarCaso");
            PrimeFaces.current().executeScript("PF('dialogoCerrarCaso').show()");
        }

        procesoFinal();
    }

    public void verAusTelefonos(AusPersona ausPersona) {
        this.getObjeto().setAsuPersonasId(ausPersona);
        super.setAccion(ACCION_BUSCAR_TELEFONOS_PERSONA);
        getAusCasoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmTelefonoPersona");
        PrimeFaces.current().executeScript("PF('dialogoTelefonoPersona').show()");
        procesoFinal();
    }

    public void buscarSeguimientoEnMemoria(int pos) {
        for (AusSeguimiento seguimiento : this.getObjeto().getSeguimientosList()) {
            if (seguimiento.getPos() == pos) {
                this.setSeguimientoProcesar(seguimiento);
                break;
            }
        }
    }

    public void buscarServicioEnMemoria(int pos) {
        for (AusCasoServicio servicio : this.getListaServiciosCrear()) {
            if (servicio.getPos() == pos) {
                this.setServicioParaCrear(servicio);
                break;
            }
        }
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getAusCasoServicio().Accion(this);
        //agregarTiempoGrowl(TIEMPO_MENSAJES_DEFECTO);
        //agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));

        listaPersonaTelefono = new ArrayList<>();
        listaPeticionarioTelefono = new ArrayList<>();
        listaServiciosCrear = new ArrayList<>();
        asignarEstadoInicialCaso();
        this.setPeticionario("No");
        setHabilitarPeticionario(true);
        setCamposObligatoriosPeticionario(false);
        //listarSedesAsociadas();
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        //PrimeFaces.current().ajax().update("frmCrear:panelPeticionarioCrear");
        //PrimeFaces.current().ajax().update("frmCrear:labelPeticionarioTipoDocumento");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
    }
    
    public void guardar() throws ParseException {
        asignarFechas();
        asignarTelefonoParaPersona(listaPersonaTelefono);
        asignarTelefonoParaPeticionario(listaPeticionarioTelefono);
        asignarServiciosParaCasos(listaServiciosCrear);
        if (validacionProcesoGuardado()) {
            asignarEstadoCasoSegunSeguimiento();
            AusPersona personaModificar = getObjeto().getAusPersonasId();
            personaBean.setObjeto(this.getObjeto().getAusPersonasId());
            //getAusCasoServicio().consultarPersona(personaBean);
            //getPersonaServicio().consultarPersona(personaBean);
            //asignacionResultadosConsultaParaBeanCaso();
            if (this.getObjeto().getAusPersonasId().exitePersona()) {
                personaModificar.setId(this.getObjeto().getAusPersonasId().getId());
                this.getObjeto().setAusPersonasId(personaModificar);
            }
            if (this.getPeticionario().equals("Si")) {
                AusPersona personaModificar2 = getObjeto().getPeticionario();
                personaBean2.setObjeto(this.getObjeto().getPeticionario());
                getAusCasoServicio().consultarPersona(personaBean2);
                //getPersonaServicio().consultarPersona(personaBean2);
                asignacionResultadosConsultaParaBeanCaso2();
                if (this.getObjeto().getPeticionario().exitePersona()) {
                    personaModificar2.setId(this.getObjeto().getPeticionario().getId());
                    this.getObjeto().setPeticionario(personaModificar2);
                }
            }
            super.setAccion(ACCION_GUARDAR);
            getAusCasoServicio().Accion(this);
            if (!super.isError()) {
                //agregarTiempoGrowl(TIEMPO_MENSAJES_CREACION);
                agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_CREAC)));
                PrimeFaces.current().resetInputs("frmCasos");
                PrimeFaces.current().ajax().update("frmCasos");
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
            }
            procesoFinal();
            //refrescar();
        }
        generarMensajes();
    }

    public String obtenerNombreAsignadoHistorico(int idEstadoHistorico, int idUsuarioResponsable) {
        String nombre = "";
        if (idEstadoHistorico == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO))) {
            nombre = getUsuario(idUsuarioResponsable);
        }
        return nombre;
    }

    public boolean validacionProcesoGuardado() {
        boolean esProcesoValido = true;
        /*Integer idSolicitudOrigen = this.getObjeto().getMaeSolicitudOrigenId();
        if (idSolicitudOrigen != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.EXTERNO_CASO_ORIGEN_PAGINA_WEB))) {
            esProcesoValido = validarExistenciaServicios();
        }*/
        if (esProcesoValido && !validarSeguimientoAdicionalEstadoSolucionado()) {
            esProcesoValido = false;
            this.addError("Para crear el seguimiento en estado (" + "Cerrado" //DESC_SEGUIMIENTO_CERRADO
                    + ") debe tener los servicios en estado \n"
                    + " resuelto, anulado o cerrado.");
        }
        return esProcesoValido;
    }

    public boolean validarExistenciaServicios() {
        boolean hayServicios = true;
        if (this.getObjeto().getServiciosList().isEmpty()) {
            hayServicios = false;
            this.addError("Ingrese al menos un servicio para continuar.");
        }
        return hayServicios;
    }

    public boolean validarSeguimientoAdicionalEstadoSolucionado() {
        boolean isCreableSeguimiento = true;
        AusSeguimiento seguimientoAdicional = this.getObjeto().getSeguimientoAdicional();
        if (seguimientoAdicional.getMaeEstadoId() > 0
                && seguimientoAdicional.getObservacion().length() > 1) {
            isCreableSeguimiento = validarSeguimientoSolucionado(seguimientoAdicional);
        }
        return isCreableSeguimiento;
    }

    public boolean validarSeguimientoSolucionado(AusSeguimiento seguimiento) {
        boolean estadoSeguimientoValido = true;
        //if (seguimiento.getMaeEstadoId() == getIdTipoSeguimiento(DESC_SEGUIMIENTO_CERRADO)) {
        if (seguimiento.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_CERRA))) {
            int cantidadEstadosValidados = contarEstadoPermitidosParaSeguimiento(this.getObjeto().getServiciosList());
            estadoSeguimientoValido = cantidadEstadosValidados == this.getObjeto().getServiciosList().size();
        }
        return estadoSeguimientoValido;
    }

    public void editar(int _id) {
        getAusCasoServicio().consultarUsuario(this);
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        super.setDoAccion(ACCION_EDITAR);
        getAusCasoServicio().Accion(this);
        //agregarTiempoGrowl(TIEMPO_MENSAJES_DEFECTO);
        agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
        //vaciarSesionAdjuntos(SESION_ADJUNTO_CASOS);
        //vaciarSesionAdjuntos(SESION_ADJUNTO_SERVICIOS);
        //vaciarSesionAdjuntos(SESION_ADJUNTO_SEGUIMIENTOS);
        listarSedesAsociadasEdit();
        debahilitarSubtipoMotivo();
        setDeshabilitarCamposSeguimientoPorMotivo(verificarEstadoSegCerradoSolucionado());
        listaPersonaTelefono = new ArrayList(this.getObjeto().getAsuPersonasId().getListaTelefonos());
        listaPeticionarioTelefono = new ArrayList(this.getObjeto().getPeticionario().getListaTelefonos());
        listaServiciosCrear = new ArrayList(this.getObjeto().getServiciosList());
        listaAdjuntosCaso = new ArrayList(this.getObjeto().getAdjuntosList());
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().ajax().update("frmAuditoriaGeneralEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public void verificarHabilitacionEstadoSeguimiento() {
        AusSeguimiento seguimiento = this.getSeguimientoProcesar();
        //boolean habilitacion = seguimiento.getMaeEstadoId() == getIdTipoSeguimiento(DESC_SEGUIMIENTO_RADICADO);
        boolean habilitacion = seguimiento.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_RADIC));
        seguimiento.setDesHabilitarSelectEstado(habilitacion);
    }

    public boolean existeSeguimientoEnDB(AusSeguimiento seguimiento) {
        boolean existeSeguimiento = false;
        if (seguimiento.getId() != null && seguimiento.getId() > 0) {
            existeSeguimiento = true;
        }
        return existeSeguimiento;
    }

    public void modificarGestion() {
        asignarServiciosEditar();
        if (validacionProcesoGuardado()) {
            asignarEstadoCasoSegunSeguimientoGestion();
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_MODIFICAR_GESTION);
            getAusCasoServicio().Accion(this);
            //refrescarCasos();
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoGestion').hide();");
                PrimeFaces.current().resetInputs("frmCasos");
                PrimeFaces.current().ajax().update("frmCasos");
            }
            procesoFinal();
        }
        generarMensajes();
    }

    public void modificar() throws ParseException {
        asignarServiciosEditar();
        if (validacionProcesoGuardado()
                && validarEstadoCasoSegunSeguimiento(this.getObjeto().getSeguimientoAdicional())) {
            asignarTelefonosEditar();
            asignarTelefonosPeticionarioEditar();
            asignarAdjuntosCasosEditar();
            asignarEstadoCasoSegunSeguimiento();
            super.setAccion(ACCION_MODIFICAR);
            super.setDoAccion(ACCION_MODIFICAR);
            getAusCasoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
                PrimeFaces.current().executeScript("PF('dialogoGestion').hide();");
                PrimeFaces.current().resetInputs("frmCasos");
                PrimeFaces.current().ajax().update("frmCasos");
            }
            procesoFinal();
            //refrescarCasos();
        }
        generarMensajes();
    }

    public void refrescarObjetoCaso() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getAusCasoServicio().Accion(this);
        listarSedesAsociadasEdit();
    }

    public void refrescarCasos() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_LISTAR);
        getAusCasoServicio().Accion(this);
    }

    public void borrar(int _id) {
        setObjBorrarCaso(new AusCaso());
        getObjBorrarCaso().setId(_id);
        PrimeFaces.current().resetInputs("frmRechazarCaso");
        PrimeFaces.current().ajax().update("frmRechazarCaso");
        PrimeFaces.current().executeScript("PF('dialogoRechazarCaso').show()");
    }

    public void rechazarCaso() {
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR);
        getAusCasoServicio().Accion(this);
        agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
        PrimeFaces.current().executeScript("PF('dialogoRechazarCaso').hide();");
        PrimeFaces.current().ajax().update("frmCasos");
        procesoFinal();
    }

    public void reabrirCaso() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_REVERTIR_CASO);
        getAusCasoServicio().Accion(this);
        agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
        PrimeFaces.current().executeScript("PF('dialogoRevertirCaso').hide();");
        PrimeFaces.current().ajax().update("frmCasos");
        procesoFinal();
    }

    public void cerrarCaso() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR);
        getAusCasoServicio().Accion(this);
        agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
        PrimeFaces.current().executeScript("PF('dialogoCerrarCaso').hide();");
        PrimeFaces.current().ajax().update("frmCasos");
        procesoFinal();
    }

    /**
     * *********************************
     *****PROCESOS SOBRE UBICACIONES
     *
     ***** **********************************
     * @param id
     */
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        if (ubicacionesRecursiva != null) {
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

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setUbicacion(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public List<Maestro> completarIps(String query) {
        List<Maestro> ipsFiltradas = new ArrayList<>();
        for (Maestro servicio : getListaTipoServicioIPS()) {
            if (servicio.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ipsFiltradas.add(servicio);
            }
        }
        if (ipsFiltradas.size() == 1) {
//            this.getServicioParaCrear().setMaeIps(ipsFiltradas.get(0).getId());
        }
        return ipsFiltradas;
    }

    public List<Maestro> completarIpsPrescriptora(String query) {
        List<Maestro> ipsFiltradas = new ArrayList<>();
        for (Maestro servicio : getListaTipoServicioIPS()) {
            if (servicio.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ipsFiltradas.add(servicio);
            }
        }
        if (ipsFiltradas.size() == 1) {
//            this.getServicioParaCrear().setMaeIpsPrescriptora(ipsFiltradas.get(0).getId());
        }
        return ipsFiltradas;
    }

    public List<Maestro> completarPatologia(String query) {
        List<Maestro> patologiaFiltradas = new ArrayList<>();
        for (Maestro maestro : getListaTipoPatologia()) {
            if (maestro.getNombre().toLowerCase().contains(query.toLowerCase())) {
                patologiaFiltradas.add(maestro);
            }
        }
        if (patologiaFiltradas.size() == 1) {
//            this.getServicioParaCrear().setMaeIpsPrescriptora(patologiaFiltradas.get(0).getId());
        }
        return patologiaFiltradas;
    }

    public List<Usuario> completarUsuarios(String query) {
        List<Usuario> usuariosFiltrado = new ArrayList<>();
        if (getListaUsuarios() != null) {
            for (Usuario user : getListaUsuarios()) {
                if (user.getNombre().toLowerCase().contains(query.toLowerCase())) {
                    usuariosFiltrado.add(user);
                }
            }
        }

        if (usuariosFiltrado.size() == 1) {
            //this.getServicioParaCrear().setIdUsuarioResponsable(usuariosFiltrado.get(0).getId());
            this.getServicioParaCrear().getIdUsuarioResponsable().setId(usuariosFiltrado.get(0).getId());
        }
        return usuariosFiltrado;
    }

    /*public List<Maestro> completarDiagnostico(String query) {
        List<Maestro> diagnosticoFiltrado = new ArrayList<>();
        for (Maestro obj : getListaTipoDiagnostico()) {
            if (obj.getNombre().toLowerCase().contains(query.toLowerCase())) {
                diagnosticoFiltrado.add(obj);
            }
        }
        if (diagnosticoFiltrado.size() == 1) {
//            this.getServicioParaCrear().setMaeDiagnostico(diagnosticoFiltrado.get(0).getId());
        }
        return diagnosticoFiltrado;
    }*/

    public List<Maestro> completarTipoServicio(String query) {
        List<Maestro> tipoServicioFiltrado = new ArrayList<>();
        for (Maestro obj : getListaTipoServicio()) {
            if (obj.getNombre().toLowerCase().contains(query.toLowerCase())) {
                tipoServicioFiltrado.add(obj);
            }
        }
        if (tipoServicioFiltrado.size() == 1) {
            this.getServicioParaCrear().setMaServicioId(tipoServicioFiltrado.get(0).getId());
            this.getServicioParaCrear().setMaServicioCodigo(tipoServicioFiltrado.get(0).getDescripcion());
            this.getServicioParaCrear().setMaServicioValor(tipoServicioFiltrado.get(0).getValor());
        }
        return tipoServicioFiltrado;
    }

    public List<Maestro> completarMotivo(String query) {
        List<Maestro> motivoFiltrado = new ArrayList<>();
        if (getListaTipoServicioMotivo() != null) {
            for (Maestro obj : getListaTipoServicioMotivo()) {
                if (obj.getNombre().toLowerCase().contains(query.toLowerCase())) {
                    motivoFiltrado.add(obj);
                }
            }
        }

        if (motivoFiltrado.size() == 1) {
//            this.getServicioParaCrear().setMaeServicioMotivo(motivoFiltrado.get(0).getId());
        }
        return motivoFiltrado;
    }

    public List<Maestro> completarAmbito(String query) {
        List<Maestro> ambitoFiltrado = new ArrayList<>();
        for (Maestro obj : getListaTipoServicioAmbito()) {
            if (obj.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ambitoFiltrado.add(obj);
            }
        }
        if (ambitoFiltrado.size() == 1) {
            //this.getServicioParaCrear().setMaeServicioMotivo(ambitoFiltrado.get(0).getId());
        }
        return ambitoFiltrado;
    }

    private void agregarTiempoGrowl(int tiempo) {
        UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmSelEmpBoton:mensajeGeneral");
        component.getAttributes().put("life", tiempo);
    }

    public void buscarPersonaEnSistema(Integer peticionario) {

        if (this.getObjeto().getAusPersonasId().getMae_tipo_documento_id() > 0
                && this.getObjeto().getAsuPersonasId().getDocumento() != null && peticionario != 0) {
            try {
                //rEFRESCAR BÚSQUEDA DE PERSONA
                this.getObjeto().getAusPersonasId().setId(0);
                this.getObjeto().getAusPersonasId().setEsAfiliado(false);
                if (!getHashTiposDocumento().isEmpty() && getHashTiposDocumento() != null) {
                    Maestro tipoDocumento = getHashTiposDocumento().get(this.getObjeto().getAusPersonasId().getMae_tipo_documento_id());
                    if (tipoDocumento != null) {
                        this.getObjeto().getAusPersonasId().setMae_tipo_documento_codigo(tipoDocumento.getValor());
                        this.getObjeto().getAusPersonasId().setMae_tipo_documento_valor(tipoDocumento.getNombre());
                    }
                }
                //Consultar persona en BD
                personaBean.setObjeto(this.getObjeto().getAusPersonasId());
                getAusCasoServicio().consultarPersona(personaBean);
                //getPersonaServicio().consultarPersona(personaBean);
                asignacionResultadosConsultaParaBeanCaso();
                if (!this.getObjeto().getAusPersonasId().exitePersona()) {
                    asignacionHashsParaBeanPersona();
                    //getAusCasoServicio().buscarAfiliadoEnSomosMas(personaBean);
                    getAusCasoServicio().consultarPersonaAfiliada(personaBean);/// pendiente implementacion
                    if (personaBean.getErrores().size() > 0) {
                        this.addError(personaBean.getErrores().get(0));
                        personaBean.getErrores().clear();
                    }
                    asignacionResultadosConsultaParaBeanCaso();
                }
                if (!this.getObjeto().getAusPersonasId().exitePersona() && !existePersonaAfiliada()) {
                    String documento = this.getObjeto().getAusPersonasId().getDocumento();
                    int tipoDoc = this.getObjeto().getAusPersonasId().getMae_tipo_documento_id();
                    this.getObjeto().setAusPersonasId(new AusPersona());
                    this.getObjeto().getAusPersonasId().setMae_tipo_documento_id(tipoDoc);
                    this.getObjeto().getAusPersonasId().setDocumento(documento);
                    this.listaPersonaTelefono = new ArrayList<>();
                }
                //if (peticionario != 0) {
                PrimeFaces.current().ajax().update("frmCrear");
                PrimeFaces.current().ajax().update("frmCrear:telefonosPersonas");
                PrimeFaces.current().ajax().update("frmEditar");
                //}

                generarMensajes();
            } catch (Exception ex) {
                Logger.getLogger(AusCasoBean.class.getName()).log(Level.SEVERE, null, ex);
                this.addError(ex.getMessage());
                generarMensajes();
            }
        }
    }

    public void buscarPeticionarioEnSistema() {
        if (this.getObjeto().getPeticionario().getMae_tipo_documento_id() > 0
                && this.getObjeto().getPeticionario().getDocumento() != null) {
            try {
                //rEFRESCAR BÚSQUEDA DE PERSONA
                this.getObjeto().getPeticionario().setId(0);
                this.getObjeto().getPeticionario().setEsAfiliado(false);
                if (!getHashTiposDocumento().isEmpty() && getHashTiposDocumento() != null) {
                    Maestro tipoDocumento = getHashTiposDocumento().get(this.getObjeto().getPeticionario().getMae_tipo_documento_id());
                    if (tipoDocumento != null) {
                        this.getObjeto().getPeticionario().setMae_tipo_documento_codigo(tipoDocumento.getValor());
                        this.getObjeto().getPeticionario().setMae_tipo_documento_valor(tipoDocumento.getNombre());
                    }
                }
                //Consultar persona en BD
                personaBean2.setObjeto(this.getObjeto().getPeticionario());
                getAusCasoServicio().consultarPersona(personaBean2);
                asignacionResultadosConsultaParaBeanCaso2();
                if (!this.getObjeto().getPeticionario().exitePersona()) {
                    asignacionHashsParaBeanPersona2();
//                    getPersonaServicio().buscarAfiliadoEnSomosMas(personaBean2);
                    getAusCasoServicio().consultarPersonaAfiliada(personaBean2);
                    if (personaBean2.getErrores().size() > 0) {
                        this.addError(personaBean2.getErrores().get(0));
                        personaBean2.getErrores().clear();
                    }
                    asignacionResultadosConsultaParaBeanCaso2();
                }
                if (!this.getObjeto().getPeticionario().exitePersona() && !existePersonaAfiliada2()) {
                    String documento = this.getObjeto().getPeticionario().getDocumento();
                    int tipoDoc = this.getObjeto().getPeticionario().getMae_tipo_documento_id();
                    this.getObjeto().setPeticionario(new AusPersona());
                    this.getObjeto().getPeticionario().setMae_tipo_documento_id(tipoDoc);
                    this.getObjeto().getPeticionario().setDocumento(documento);
//                    this.listaPeticionarioTelefono = new ArrayList<>();
                }
                PrimeFaces.current().ajax().update("frmCrear:panelPeticionarioCrear");
                PrimeFaces.current().ajax().update("frmEditar:panelPeticionario");
                //PrimeFaces.current().ajax().update("frmCrear:telefonosPeticionarioCrear");
                PrimeFaces.current().ajax().update("frmEditar:telefonosPeticionario");
                generarMensajes();
            } catch (Exception ex) {
                Logger.getLogger(AusCasoBean.class.getName()).log(Level.SEVERE, null, ex);
                this.addError(ex.getMessage());
                generarMensajes();
            }
        }
    }

    private void asignacionHashsParaBeanPersona() {
        personaBean.setHashTiposDocumento(this.getHashTiposDocumento());
//        personaBean.setHashSexo(this.getHashSexo());
//        personaBean.setHashEstadosPersona(this.getHashTipoEstadosPersona());
    }

    private void asignacionHashsParaBeanPersona2() {
        personaBean2.setHashTiposDocumento(this.getHashTiposDocumento());
//        personaBean2.setHashSexo(this.getHashSexo());
//        personaBean2.setHashEstadosPersona(this.getHashTipoEstadosPersona());
    }

    private void asignacionResultadosConsultaParaBeanCaso() {
        this.getObjeto().setAusPersonasId(personaBean.getObjeto());
        //this.listaPersonaTelefono = personaBean.get ListaausPersonaTelefono();
        this.listaPersonaTelefono = personaBean.getObjeto().getListaTelefonos();
    }

    private void asignacionResultadosConsultaParaBeanCaso2() {
        this.getObjeto().setPeticionario(personaBean2.getObjeto());
        this.getObjeto().setParentesco(getParentesco());
        //this.listaPeticionarioTelefono = personaBean2.getListaPersonaTelefono();
        this.listaPeticionarioTelefono = personaBean2.getObjeto().getListaTelefonos();
    }

    private boolean existePersonaAfiliada() {
        return this.getObjeto().getAusPersonasId().getEsAfiliado();
    }

    private boolean existePersonaAfiliada2() {
        return this.getObjeto().getPeticionario().getEsAfiliado();
    }

    /**
     * *********************************
     ******PROCESOS SOBRE SERVICIOS****** **********************************
     */
    public void crearServicio() {
        if (this.getObjeto().getAusPersonasId().getMae_tipo_documento_id() > 0
                && this.getObjeto().getAsuPersonasId().getDocumento() != null) {
            //2024-06-21 jyperez cambio crear servicio
            this.setServicioParaCrear(new AusCasoServicio());
            //2025-06-24 jyperez inicializamos la lista para las tecnologias, cada vez que ingremos a agregar servicios
            this.listaTecnologiaServicios= new ArrayList();
            crearServicio = true;
            contTecnologiaServicios = 0;
            //setListaTipoDiagnostico(new ArrayList());
            //setHashTipoDiagnostico(new HashMap<>());

            this.getServicioParaCrear().setHabilitarServicioTipoAdministracion(true);
            this.getServicioParaCrear().setDeshabilitarCampoAsignado(true);
            this.getServicioParaCrear().setHabilitarMedicamentoCobertura(true);

            //vaciarSesionAdjuntos(AusCasoBean.SESION_ADJUNTO_SERVICIOS);
            //evaluarHabilitarCampoPorEstadoInsert();
            //setCodigoCie("");
            //setNombreCie("");
            PrimeFaces.current().resetInputs("frmCrearServicio");
            PrimeFaces.current().ajax().update("frmCrearServicio");
            PrimeFaces.current().resetInputs("frmCrearServicio:pCrearServicio");
            PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicio");
            PrimeFaces.current().executeScript("PF('dialogoCrearServicio').show()");
        } else {
            super.addError("Se deben completar los datos del Afiliado para poder adicionar los servicios.");
            generarMensajes();
        }
        
    }

    /**
     * Borrar servicio de la lista
     *
     * @param servicio
     * @param pos
     */
    /*public void borrarServicio(int pos) {
        try {
            //Retirar el servicio de la lista
            List<AusCasoServicio> lista = new ArrayList();
            int i = 0, j = 0;
            for (AusCasoServicio det : this.getListaServiciosCrear()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            this.setListaServiciosCrear(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Servicio");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearServicio').hide();");
            PrimeFaces.current().ajax().update("frmCrear:tablaServicios");
            PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
        }
    }*/
    
    public void borrarServicio(AusCasoServicio servicio) {
        List<AusCasoServicio> listaContactos = new ArrayList<>();
        int posicionEliminar = servicio.getPos();
        for (AusCasoServicio contacto : getListaServiciosCrear()) {
            if (contacto.getPos() != posicionEliminar) {
                listaContactos.add(contacto);
            }
        }

        if (servicio.getId() != null) {
            this.getServicioParaCrear().setId(servicio.getId());
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(ACCION_BORRAR_SERVICIO);
            getAusCasoServicio().Accion(this);
        }

        setListaServiciosCrear(listaContactos);

        addMensaje("Se ha realizado la eliminación del servicio");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:tablaServicios");
        PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
        PrimeFaces.current().ajax().update("frmGestion:tablaServicios");
        PrimeFaces.current().resetInputs("frmCasos");
        PrimeFaces.current().ajax().update("frmCasos");
    }
    
    /**
     * Agregar servicio a la lista
     */
    public void adicionarServicio() {

        try {
            AusCasoServicio obj = getServicioParaCrear();
            obj.setBorrado(Boolean.FALSE);
            getAusCasoServicio().validarCamposObligatorios(this);

            //Agregar dato a la lista
            if (getListaServiciosCrear() == null) {
                setListaServiciosCrear(new ArrayList());
            }
            
            //2024-06-21 jyperez ajustamos las validaciones con respecto a una nueva lista de tecnologías
            if (this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                if (this.getListaTecnologiaServicios().isEmpty()) {
                    addError("Debe agregarse al menos un procedimiento.");
                } else {
                    if (this.getServicioParaCrear().getMedicamento() != null) {
                        if (this.getServicioParaCrear().getMedicamento()) {
                            //2024-06-24 jyperez se cambia la validación para encontrar algún medicamento en la lista de tecnologías
                            boolean encontro = true;
                            for (Tecnologia tec: getListaTecnologiaServicios()) {
                                if (!tec.getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                    encontro = false;
                                }
                            }
                            if (!encontro) {
                                addError("Cuando selecciona Si en Aplica Medicamento, solo puede agregarse Medicamentos.");
                            }
                            //2024-08-22 jyperez validamos que las fechas de inicio de vigencia y fin hayan sido diligenciadas
                            if (this.getServicioParaCrear().getFechaInicioVigencia() == null ||
                                    this.getServicioParaCrear().getFechaFinVigencia() == null) {
                                addError("Si agregó tipo de procedimiento Medicamento, es obligatorio ingresar las dos fechas de vigencia.");
                            } else if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                                addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                            }
                        } else {
                            // si no hay medicamentos, no debe haber alguno agregado a la lista
                            boolean encontro = false;
                            for (Tecnologia tec: getListaTecnologiaServicios()) {
                                if (tec.getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                    encontro = true;
                                }
                            }
                            if (encontro) {
                                addError("Para agregar Medicamentos debe Seleccionar Si en Aplica Medicamento. No es posible agregarlos en conjunto con otras tecnologias.");
                            }
                            //2024-08-22 jyperez validamos que la fecha fin de vigencia hayan sido diligenciada
                            if (this.getServicioParaCrear().getFechaInicioVigencia() == null) {
                                addError("si eligió tipo de procedimiento tecnología o insumo, es obligatorio ingresar la fecha inicio de vigencia.");
                            } else if (this.getServicioParaCrear().getFechaFinVigencia() != null) {
                                if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                                addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                                }
                            }
                        }
                    } else {
                        //2024-08-22 jyperez validamos que la fecha fin de vigencia hayan sido diligenciada
                        if (this.getServicioParaCrear().getFechaInicioVigencia() == null) {
                            addError("si eligió tipo de procedimiento tecnología o insumo, es obligatorio ingresar la fecha inicio de vigencia.");
                        } else if (this.getServicioParaCrear().getFechaFinVigencia() != null) {
                            if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                            addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                            }
                        }
                    }
                }
            }
            
            if (!super.isError()) {
                Maestro estadoServicio = getHashEstadoServicio().get(obj.getMaeEstadoId());
                if (estadoServicio != null) {
                    obj.setMaeEstadoCodigo(estadoServicio.getValor());
                    obj.setMaeEstadoValor(estadoServicio.getNombre());
                }
                Maestro ambitoServicio = getHashTipoServicioAmbito().get(obj.getMaeServicioAmbitoId());
                if (ambitoServicio != null) {
                    obj.setMaeServicioAmbitoCodigo(ambitoServicio.getValor());
                    obj.setMaeServicioAmbitoValor(ambitoServicio.getNombre());
                }
                Maestro tipoServicioMotivo = getHashTipoServicioMotivo().get(obj.getMaeServicioMotivoId());
                if (tipoServicioMotivo != null) {
                    obj.setMaeServicioMotivoCodigo(tipoServicioMotivo.getValor());
                    obj.setMaeServicioMotivoValor(tipoServicioMotivo.getNombre());
                }
                Maestro patologiaServicio = getHashTipoPatologia().get(obj.getMaePatologiaId());
                if (patologiaServicio != null) {
                    obj.setMaePatologiaCodigo(patologiaServicio.getValor());
                    obj.setMaePatologiaValor(patologiaServicio.getNombre());
                }
                Maestro tipoAdministrativo = getHashTipoAdministrativo().get(obj.getMaeTipoAdministrativoId());
                if (tipoAdministrativo != null) {
                    obj.setMaeTipoAdministrativoCodigo(tipoAdministrativo.getValor());
                    obj.setMaeTipoAdministrativoValor(tipoAdministrativo.getNombre());
                }
                if(this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                    //2024-06-24 jyperez realizamos el recorrido de la lista de tecnologías para agregar la información al objeto y agregarlo a la lista de servicios para crear
                    AusCasoServicio objClon = null;
                    for(Tecnologia tec: this.getListaTecnologiaServicios()) {
                        try {
                            objClon = (AusCasoServicio) obj.clone();
                        } catch (CloneNotSupportedException ex) {
                            objClon = obj;
                        }
                        //agregamos la tecnología en el objeto
                        objClon.setTipoTecnologia(tec.getTipoTecnologia());
                        objClon.setMaTecnologiaId(tec.getMaTecnologiaId());
                        objClon.setMaTecnologiaCodigo(tec.getMaTecnologiaCodigo());
                        objClon.setMaTecnologiaValor(tec.getMaTecnologiaValor());
                        //agregamos a la lista de servicios a crear cada una de las tecnologías en el listado
                        objClon.setPos(getListaServiciosCrear().size());
                        //2025-03-21 jyperez validamos si la tecnología agregada no es tipo Tecnologia, desmarcamos el campo aplica cita
                        if (objClon.isAsignacionCita() && !tec.getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_TECNOLOGIA)))) {
                            objClon.setAsignacionCita(false);
                        }
                        this.getListaServiciosCrear().add(objClon);
                    }
                } else {
                    //2024-06-24 jyperez cuando no hay procedimientos a agregar, se adiciona únicamente el servicio sin la información asignada de tecnología.
                    obj.setPos(getListaServiciosCrear().size());
                    this.getListaServiciosCrear().add(obj);
                }
                PrimeFaces.current().ajax().update("frmCrear:tablaServicios");
                PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
                PrimeFaces.current().ajax().update("frmGestion:tablaServicios");
                PrimeFaces.current().executeScript("PF('dialogoCrearServicio').hide();");
            }
            generarMensajes();
        } catch (Exception e) {
            super.addError("No es posible adicionar los servicios");
        }

    }

    public void adicionarDuplicarServicio() {

        try {
            AusCasoServicio obj = getServicioParaCrear();
            obj.setBorrado(Boolean.FALSE);
            getAusCasoServicio().validarCamposObligatorios(this);

            //Agregar dato a la lista
            if (getListaServiciosCrear() == null) {
                setListaServiciosCrear(new ArrayList());
            }

            if (this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                if (this.getServicioParaCrear().getMaTecnologiaId() == null) {
                    addError("El procedimiento es obligatorio.");
                }
                if (this.getServicioParaCrear().getMaTecnologiaId() != null) {
                    if (this.getServicioParaCrear().getMedicamento() != null) {
                        if (this.getServicioParaCrear().getMedicamento()) {
                            //2024-06-13 jyperez se cambia la validación debido a que no está permitiendo agregar medicamento cuando esta marcado que si
                            if (!getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                addError("Cuando selecciona Si en Aplica Medicamento, debe agregar Medicamento.");
                            }
                        } else if (getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                            addError("Para agregar un Medicamento debe Seleccionar Si en Aplica Medicamento.");
                        }
                    }
                }
            }

            if (this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                if (this.getServicioParaCrear().getMedicamento() == null) {
                    addError("El Medicamento es obligatorio.");
                }
                if (this.getServicioParaCrear().getMedicamento() != null) {
                    if (this.getServicioParaCrear().getMedicamento() == null) {
                        addError("El Aplica Medicamento Cobertura es obligatorio.");
                    }
                }
            }
            if (!super.isError()) {
                Maestro estadoServicio = getHashEstadoServicio().get(obj.getMaeEstadoId());
                if (estadoServicio != null) {
                    obj.setMaeEstadoCodigo(estadoServicio.getValor());
                    obj.setMaeEstadoValor(estadoServicio.getNombre());
                }
                Maestro ambitoServicio = getHashTipoServicioAmbito().get(obj.getMaeServicioAmbitoId());
                if (ambitoServicio != null) {
                    obj.setMaeServicioAmbitoCodigo(ambitoServicio.getValor());
                    obj.setMaeServicioAmbitoValor(ambitoServicio.getNombre());
                }
                Maestro tipoServicioMotivo = getHashTipoServicioMotivo().get(obj.getMaeServicioMotivoId());
                if (tipoServicioMotivo != null) {
                    obj.setMaeServicioMotivoCodigo(tipoServicioMotivo.getValor());
                    obj.setMaeServicioMotivoValor(tipoServicioMotivo.getNombre());
                }
                Maestro patologiaServicio = getHashTipoPatologia().get(obj.getMaePatologiaId());
                if (patologiaServicio != null) {
                    obj.setMaePatologiaCodigo(patologiaServicio.getValor());
                    obj.setMaePatologiaValor(patologiaServicio.getNombre());
                }
                Maestro tipoAdministrativo = getHashTipoAdministrativo().get(obj.getMaeTipoAdministrativoId());
                if (tipoAdministrativo != null) {
                    obj.setMaeTipoAdministrativoCodigo(tipoAdministrativo.getValor());
                    obj.setMaeTipoAdministrativoValor(tipoAdministrativo.getNombre());
                }
                obj.setPos(getListaServiciosCrear().size());
                this.getListaServiciosCrear().add(obj);

                PrimeFaces.current().ajax().update("frmCrear:tablaServicios");
                PrimeFaces.current().ajax().update("frmEditar:tablaServicios");
                PrimeFaces.current().ajax().update("frmGestion:tablaServicios");
                PrimeFaces.current().executeScript("PF('dialogoDuplicarServicio').hide();");
            }
            generarMensajes();
        } catch (Exception e) {
            super.addError("No es posible adicionar servicio");
        }

    }
    
    public void editarServicio(int _id, int pos) {
        //2024-06-24 jyperez actualizamos la variable de crearServicio para controlar los buscadores de tecnologías
        crearServicio = false;
        if (_id > 0) {
            this.getServicioParaCrear().setId(_id);
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(ACCION_DUPLICAR_SERVICIO);
            getAusCasoServicio().Accion(this);
        } else {
            buscarServicioEnMemoria(pos);
        }
        getAusCasoServicio().editarServicioCamposObligatorios(this);
        desabilidarCamposAplicaMedicamentoCobertura();
        listaAdjuntosServicio = new ArrayList(this.getServicioParaCrear().getAdjuntosList());
        PrimeFaces.current().ajax().update("frmEditarServicio");
        PrimeFaces.current().resetInputs("frmEditarServicio");
        PrimeFaces.current().executeScript("PF('dialogoEditarServicio').show()");
        procesoFinal();
    }
    
    public void editarServicioGestion(int _id, int pos) {
        //2024-06-24 jyperez actualizamos la variable de crearServicio para controlar los buscadores de tecnologías
        crearServicio = false;
        if (_id > 0) {
            this.getServicioParaCrear().setId(_id);
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_DUPLICAR_SERVICIO);
            getAusCasoServicio().Accion(this);
        } else {
            buscarServicioEnMemoria(pos);
        }
        getAusCasoServicio().editarServicioCamposObligatorios(this);
        desabilidarCamposAplicaMedicamentoCobertura();
        listaAdjuntosServicio = new ArrayList(this.getServicioParaCrear().getAdjuntosList());
        PrimeFaces.current().ajax().update("frmEditarServicioGestion");
        PrimeFaces.current().resetInputs("frmEditarServicioGestion");
        PrimeFaces.current().executeScript("PF('dialogoEditarServicioGestion').show()");
        procesoFinal();
    }
    
    public void duplicarServicio(AusCasoServicio servicio) {
        //2024-06-24 jyperez actualizamos la variable de crearServicio para controlar los buscadores de tecnologías
        crearServicio = false;
        this.setServicioParaCrear(new AusCasoServicio());
        AusCasoServicio temporal = new AusCasoServicio(servicio);
        this.getServicioParaCrear().setMaeEstadoId(servicio.getMaeEstadoId());
        this.setServicioParaCrear(temporal);
        if (getServicioParaCrear().getMaTecnologiaId() != null) {
            getServicioParaCrear().setHabiltarCampoProcedimiento(true);
        } else {
            getServicioParaCrear().setHabiltarCampoProcedimiento(false);
        }
        if (getServicioParaCrear().getMedicamento() != null) {
            desabilidarCamposAplicaMedicamentoCobertura();
        }
        getAusCasoServicio().editarServicioCamposObligatorios(this);
        desabilidarCamposAplicaMedicamentoCobertura();
        listaAdjuntosServicio = new ArrayList(this.getServicioParaCrear().getAdjuntosList());
        PrimeFaces.current().ajax().update("frmDuplicarServicio");
        PrimeFaces.current().resetInputs("frmDuplicarServicio");
        PrimeFaces.current().executeScript("PF('dialogoDuplicarServicio').show()");
        procesoFinal();
    }

    public void modificarServicio() throws ParseException {
        if (this.getServicioParaCrear().getId() != null
                && this.getServicioParaCrear().getId() > 0) {
            if (this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                if (this.getServicioParaCrear().getMaTecnologiaId() == null) {
                    addError("El procedimiento es obligatorio.");
                } else {
                    if (this.getServicioParaCrear().getMedicamento() != null) {
                        if (this.getServicioParaCrear().getMedicamento()) {
                            //2024-06-13 jyperez se cambia la validación debido a que no está permitiendo agregar medicamento cuando esta marcado que si
                            if (!getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                addError("Cuando selecciona Si en Aplica Medicamento, debe agregar Medicamento.");
                            }
                        } else if (getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                            addError("Para agregar un Medicamento debe Seleccionar Si en Aplica Medicamento.");
                        }
                    }
                }
            }
            getAusCasoServicio().validarCamposObligatorios(this);
            if (!super.isError()) {
                asignarAdjuntosServicioEditar();
                super.setAccion(ACCION_MODIFICAR);
                super.setDoAccion(ACCION_MODIFICAR_SERVICIO);
                getAusCasoServicio().Accion(this);
                refrescarObjetoCaso();
                listaServiciosCrear = new ArrayList(this.getObjeto().getServiciosList());
            }
        } else {
            if (this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                if (this.getServicioParaCrear().getMaTecnologiaId() == null) {
                    addError("El procedimiento es obligatorio.");
                } else {
                    if (this.getServicioParaCrear().getMedicamento() != null) {
                        if (this.getServicioParaCrear().getMedicamento()) {
                            //2024-06-13 jyperez se cambia la validación debido a que no está permitiendo agregar medicamento cuando esta marcado que si
                            if (!getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                addError("Cuando selecciona Si en Aplica Medicamento, debe agregar Medicamento.");
                            }
                        } else if (getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                            addError("Para agregar un Medicamento debe Seleccionar Si en Aplica Medicamento.");
                        }
                    }
                }
            }
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditar");
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().resetInputs("frmGestion");
            PrimeFaces.current().ajax().update("frmGestion");
            PrimeFaces.current().resetInputs("frmCasos");
            PrimeFaces.current().ajax().update("frmCrear");
            PrimeFaces.current().executeScript("PF('dialogoEditarServicio').hide();");
            PrimeFaces.current().ajax().update("frmCasos");
        }
        procesoFinal();
    }
    
    public void modificarServicioGestion() throws ParseException {
        if (this.getServicioParaCrear().getId() != null
                && this.getServicioParaCrear().getId() > 0) {
            if (this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                if (this.getServicioParaCrear().getMaTecnologiaId() == null) {
                    addError("El procedimiento es obligatorio.");
                } else {
                    if (this.getServicioParaCrear().getMedicamento() != null) {
                        if (this.getServicioParaCrear().getMedicamento()) {
                            //2024-06-13 jyperez se cambia la validación debido a que no está permitiendo agregar medicamento cuando esta marcado que si
                            if (!getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                addError("Cuando selecciona Si en Aplica Medicamento, debe agregar Medicamento.");
                            }
                            //2024-08-22 jyperez validamos que las fechas de inicio de vigencia y fin hayan sido diligenciadas
                            if (this.getServicioParaCrear().getFechaInicioVigencia() == null ||
                                    this.getServicioParaCrear().getFechaFinVigencia() == null) {
                                addError("Si agregó tipo de procedimiento Medicamento, es obligatorio ingresar las dos fechas de vigencia.");
                            } else if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                                addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                            }
                        } else {
                            if (getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                addError("Para agregar un Medicamento debe Seleccionar Si en Aplica Medicamento.");
                            }
                            //2024-08-22 jyperez validamos que la fecha fin de vigencia hayan sido diligenciada
                            if (this.getServicioParaCrear().getFechaInicioVigencia() == null) {
                                addError("si eligió tipo de procedimiento tecnología o insumo, es obligatorio ingresar la fecha inicio de vigencia.");
                            } else if (this.getServicioParaCrear().getFechaFinVigencia() != null) {
                                if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                                addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                                }
                            }
                        }
                    } else {
                        //2024-08-22 jyperez validamos que la fecha fin de vigencia hayan sido diligenciada
                        if (this.getServicioParaCrear().getFechaInicioVigencia() == null) {
                            addError("si eligió tipo de procedimiento tecnología o insumo, es obligatorio ingresar la fecha inicio de vigencia.");
                        } else if (this.getServicioParaCrear().getFechaFinVigencia() != null) {
                            if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                            addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                            }
                        }
                    }
                }
            }
            getAusCasoServicio().validarCamposObligatorios(this);
            if (!super.isError()) {
                asignarAdjuntosServicioEditar();
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_MODIFICAR_SERVICIO);
                getAusCasoServicio().Accion(this);
                refrescarObjetoCaso();
                listaServiciosCrear = new ArrayList(this.getObjeto().getServiciosList());
            }
        } else {
            if (this.getServicioParaCrear().isHabiltarCampoProcedimiento()) {
                if (this.getServicioParaCrear().getMaTecnologiaId() == null) {
                    addError("El procedimiento es obligatorio.");
                } else {
                    if (this.getServicioParaCrear().getMedicamento() != null) {
                        if (this.getServicioParaCrear().getMedicamento()) {
                            //2024-06-13 jyperez se cambia la validación debido a que no está permitiendo agregar medicamento cuando esta marcado que si
                            if (!getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                addError("Cuando selecciona Si en Aplica Medicamento, debe agregar Medicamento.");
                            }
                            //2024-08-22 jyperez validamos que las fechas de inicio de vigencia y fin hayan sido diligenciadas
                            if (this.getServicioParaCrear().getFechaInicioVigencia() == null ||
                                    this.getServicioParaCrear().getFechaFinVigencia() == null) {
                                addError("Si agregó tipo de procedimiento Medicamento, es obligatorio ingresar las dos fechas de vigencia.");
                            } else if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                                addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                            }
                        } else {
                            if (getServicioParaCrear().getTipoTecnologia().equals(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)))) {
                                addError("Para agregar un Medicamento debe Seleccionar Si en Aplica Medicamento.");
                            }
                            //2024-08-22 jyperez validamos que la fecha fin de vigencia hayan sido diligenciada
                            if (this.getServicioParaCrear().getFechaInicioVigencia() == null) {
                                addError("si eligió tipo de procedimiento tecnología o insumo, es obligatorio ingresar la fecha inicio de vigencia.");
                            } else if (this.getServicioParaCrear().getFechaFinVigencia() != null) {
                                if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                                addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                                }
                            }
                        }
                    } else {
                        //2024-08-22 jyperez validamos que la fecha fin de vigencia hayan sido diligenciada
                        if (this.getServicioParaCrear().getFechaInicioVigencia() == null) {
                            addError("si eligió tipo de procedimiento tecnología o insumo, es obligatorio ingresar la fecha inicio de vigencia.");
                        } else if (this.getServicioParaCrear().getFechaFinVigencia() != null) {
                            if (this.getServicioParaCrear().getFechaInicioVigencia().after(this.getServicioParaCrear().getFechaFinVigencia())) {
                            addError("La fecha de inicio de vigencia debe ser menor a la fecha fin de vigencia.");
                            }
                        }
                    }
                }
            }
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditar");
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().resetInputs("frmGestion");
            PrimeFaces.current().ajax().update("frmGestion");
            PrimeFaces.current().resetInputs("frmCasos");
            PrimeFaces.current().ajax().update("frmCrear");
            PrimeFaces.current().executeScript("PF('dialogoEditarServicioGestion').hide();");
            PrimeFaces.current().ajax().update("frmCasos");
        }
        procesoFinal();
    }
    
    /**
     * ************************************
     *******PROCESOS SOBRE SEGUIMIENTO******
     * ************************************
     */
    public void crearSeguimiento() {
        seguimientoProcesar = new AusSeguimiento();
        PrimeFaces.current().resetInputs("frmCrearSeguimiento");
        PrimeFaces.current().ajax().update("frmCrearSeguimiento");
        PrimeFaces.current().executeScript("PF('dialogoCrearSeguimiento').show()");
    }

    /**
     * Agregar seguimiento a la lista
     */
    public void adicionarSeguimiento() {
        try {
            AusSeguimiento seguimientoNuevo = getSeguimientoProcesar();
            if (validarEstadoSeguimiento()) {
                if (validarSeguimientoSolucionado(seguimientoNuevo)) {
                    if (validarEstadoCasoSegunSeguimiento(seguimientoNuevo)) {
                        //Adicionar registro a la lista
                        if (this.getObjeto().getSeguimientosList() == null) {
                            this.getObjeto().setSeguimientosList(new ArrayList());
                        }
                        seguimientoNuevo.setPos(this.getObjeto().getSeguimientosList().size());
                        this.getObjeto().getSeguimientosList().add(seguimientoNuevo);
                    }

                } else {
                    mostrarErrorSeguimientoEstadoSolucionado();
                }
            } else {
                super.addError("El caso ya se fue cerrado");
            }

        } catch (Exception e) {
            super.addError("No es posible adicionar servicio");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearSeguimiento').hide();");
            PrimeFaces.current().ajax().update("frmGestion:tablaSeguimientos");
            PrimeFaces.current().ajax().update("frmCasos");
        }
        generarMensajes();
    }

    public void verSeguimiento(int _id, int pos) {
        if (_id > 0) {
            getSeguimientoProcesar().setId(_id);
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_VER_SEGUIMIENTO);
            getAusCasoServicio().Accion(this);
        } else {
            buscarSeguimientoEnMemoria(pos);
        }
        PrimeFaces.current().ajax().update("frmVerSeguimiento");
        PrimeFaces.current().executeScript("PF('dialogoVerSeguimiento').show()");
        procesoFinal();
    }

    public void editarSeguimiento(int id, int pos) {
        if (id > 0) {
            this.getSeguimientoProcesar().setId(id);
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(ACCION_EDITAR_SEGUIMIENTO);
            getAusCasoServicio().Accion(this);
        } else {
            buscarSeguimientoEnMemoria(pos);
        }

        verificarHabilitacionEstadoSeguimiento();
        listaAdjuntosSeguimiento = new ArrayList(this.getSeguimientoProcesar().getAdjuntosList());
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditarSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarSeguimiento");
            PrimeFaces.current().executeScript("PF('dialogoEditarSeguimiento').show();");
        }
    }

    public void modificarSeguimiento() {
        boolean seguimientoValido = true;
        if (!validarSeguimientoSolucionado(this.getSeguimientoProcesar())) {
            mostrarErrorSeguimientoEstadoSolucionado();
            seguimientoValido = false;
        }
        if (!validarEstadoCasoSegunSeguimiento(this.getSeguimientoProcesar())) {
            seguimientoValido = false;
        }
        if (existeSeguimientoEnDB(this.getSeguimientoProcesar()) && seguimientoValido) {
            asignarAdjuntosSeguimientoEditar();
            if (asignarEstadoCasoSegunModificacionSeguimiento()) {
                getAusCasoServicio().modificarInfoBasicaCaso(this);
            }
            super.setAccion(ACCION_MODIFICAR);
            super.setDoAccion(ACCION_MODIFICAR_SEGUIMIENTO);
            getAusCasoServicio().Accion(this);
            refrescarObjetoCaso();
            this.getObjeto().setRenderAgregarSeguimientos(verificarEstadoSegCerradoSolucionado());
            setDeshabilitarCamposSeguimientoPorMotivo(verificarEstadoSegCerradoSolucionado());
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditar:tablaSeguimientos");
            PrimeFaces.current().ajax().update("frmEditar:tablaSeguimientos");
            PrimeFaces.current().resetInputs("frmGestion:tablaSeguimientos");
            PrimeFaces.current().ajax().update("frmGestion:tablaSeguimientos");
            PrimeFaces.current().executeScript("PF('dialogoEditarSeguimiento').hide();");
        }
        procesoFinal();
    }

    public void mostrarErrorSeguimientoEstadoSolucionado() {
        this.addError("Para procesar el seguimiento en estado (" + "Cerrado" //DESC_SEGUIMIENTO_CERRADO
                + ") debe tener los servicios en estado \n" + " resuelto, anulado.");
    }

    public void mostrarErrorSeguimientoEstadoDependienteSolucionado() {
        this.addError("Para procesar el caso, los seguimientos de estado (" + "Solucionado" //DESC_SEGUIMIENTO_SOLUCIONADO + "," + "Cerrado" //DESC_SEGUIMIENTO_CERRADO
                + ") deben tener los servicios en estado \n" + " resuelto, anulado.");
    }

    public void crearTelefono() {
        setPersonaTelefono(new AusPersonaTelefono());
        //PrimeFaces.current().resetInputs("frmCrearTelefono:pCrearTelefono");
        //PrimeFaces.current().ajax().update("frmCrearTelefono:pCrearTelefono");
        //PrimeFaces.current().ajax().update("frmCrear:telefonosPersonas");
        PrimeFaces.current().executeScript("PF('dialogoCrearTelefono').show()");
    }

    public void crearTelefonoPeticionario() {
//        peticionarioTelefono = new PersonaTelefono();
        PrimeFaces.current().resetInputs("frmCrearTelefonoPeticionario:panelCrearTelefonoPeticionario");
        PrimeFaces.current().ajax().update("frmCrearTelefonoPeticionario:panelCrearTelefonoPeticionario");
        PrimeFaces.current().executeScript("PF('dialogoCrearTelefonoPeticionario').show()");
    }

    public void adicionarTelefono() {
        try {
            boolean validar = true;
            AusPersonaTelefono obj = getPersonaTelefono();
            //Adicionar registro a la lista
            if (listaPersonaTelefono == null) {
                listaPersonaTelefono = new ArrayList();
            } else {
                for (AusPersonaTelefono telefono : listaPersonaTelefono) {
                    if (telefono.getNumero().equals(obj.getNumero())) {
                        validar = false;
                    }
                }
            }
            if (validar) {
                if (obj.getNumero().length() <= 10) {
                    try {
                        //if(Integer.parseInt(obj.getNumero()) < 2147483647){
                        obj.setPos(listaPersonaTelefono.size());
                        this.listaPersonaTelefono.add(obj);
                        //}else{
                        //addError("El numero de telefono es muy grande");
                        //}
                    } catch (Exception e) {
                        addError("El numero de telefono es muy grande");
                    }
                } else {
                    addError("El numero de telefono es muy grande");
                }
            } else {
                addError("El numero de telefono ya existe");
            }

        } catch (Exception e) {
            this.addError("No es posible adicionar teléfono");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearTelefono').hide();");
            PrimeFaces.current().ajax().update("frmCrear:telefonosPersonas");
            PrimeFaces.current().ajax().update("frmEditar:telefonos");
        } else {
            this.generarMensajes();
        }
    }

    public void adicionarTelefonoPeticionario() {
        try {
            boolean validar = true;
            AusPersonaTelefono obj = getPeticionarioTelefono();
            //Adicionar registro a la lista
            if (listaPeticionarioTelefono == null) {
                listaPeticionarioTelefono = new ArrayList();
            } else {
                for (AusPersonaTelefono telefono : listaPeticionarioTelefono) {
                    if (telefono.getNumero().equals(obj.getNumero())) {
                        validar = false;
                    }
                }
            }
            if (validar) {
                if (obj.getNumero().length() <= 10) {
                    try {
                        //if(Integer.parseInt(obj.getNumero()) < 2147483647){
                        obj.setPos(listaPeticionarioTelefono.size());
                        this.listaPeticionarioTelefono.add(obj);
                        //}else{
                        // addError("El numero de telefono es muy grande");
                        //}
                    } catch (Exception e) {
                        addError("El numero de telefono es muy grande");
                    }
                } else {
                    addError("El numero de telefono es muy grande");
                }
            } else {
                addError("El numero de telefono ya existe");
            }

        } catch (Exception e) {
            this.addError("No es posible adicionar teléfono");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearTelefonoPeticionario').hide();");
            PrimeFaces.current().ajax().update("frmCrear:telefonosPeticionarioCrear");
            PrimeFaces.current().ajax().update("frmEditar:telefonosPeticionario");
        } else {
            this.generarMensajes();
        }
    }

    public void borrarTelefono(int pos) {
        try {
            //Retirar registro de la lista
            List<AusPersonaTelefono> lista = new ArrayList();
            int i = 0, j = 0;
            for (AusPersonaTelefono det : listaPersonaTelefono) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            listaPersonaTelefono = lista;
        } catch (Exception e) {
            super.addError("No es posible borrar telefono");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearTelefono').hide();");
            PrimeFaces.current().ajax().update("frmCrear:telefonos");
            PrimeFaces.current().ajax().update("frmEditar:telefonos");
        }
    }

    public void borrarTelefonoPeticionario(int pos) {
        try {
            //Retirar registro de la lista
            List<AusPersonaTelefono> lista = new ArrayList();
            int i = 0, j = 0;
            for (AusPersonaTelefono det : listaPeticionarioTelefono) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            listaPeticionarioTelefono = lista;
        } catch (Exception e) {
            super.addError("No es posible borrar telefono");
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearTelefonoPeticionario').hide();");
            PrimeFaces.current().ajax().update("frmCrear:telefonosPeticionario");
            PrimeFaces.current().ajax().update("frmEditar:telefonosPeticionario");
        }
    }

    public void asignarTelefonoParaPersona(List<AusPersonaTelefono> telefonosAsignar) {
        getObjeto().getAusPersonasId().setListaTelefonos(telefonosAsignar);
    }

    public void asignarTelefonoParaPeticionario(List<AusPersonaTelefono> telefonosAsignar) {
        getObjeto().getPeticionario().setListaTelefonos(telefonosAsignar);
    }

    /*public void asignarFechas() throws ParseException {
        Date fechaCreacion = this.getObjeto().getFechaHoraCrea();
        int origen = this.getObjeto().getMaeSolicitudOrigenId();
        int riesgoVida = this.getObjeto().getMaeSolicitudRiesgoVidalId();
        String nombreOrigen = this.hashTipoSolicitudOrigen.get(origen).getNombre();
        String nombreRiesgoVida = this.hashTipoSolicitudRiesgoVida.get(riesgoVida).getNombre();
        int dias = 0;
        if (nombreRiesgoVida.equals("SIS")) {
            dias = 2;
        } else {
            if (nombreRiesgoVida.equals("Regular") && nombreOrigen.equals("SuperSalud")) {
                dias = 5;
            } else {
                dias = 13;
            }
        }
        Calendar fecha = Calendar.getInstance();
        if (fechaCreacion == null) {
            fechaCreacion = new Date();
        }
        fecha.setTime(fechaCreacion);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> lista = new ArrayList();
        lista = getAusCasoServicio().obtenerFechas(fechaCreacion);
        for (int i = 1; i <= dias; i++) {
            fecha.add(Calendar.DAY_OF_YEAR, 1);
            Date f1 = fecha.getTime();
            String date = formato.format(f1);
            for (Date fechaHabil : lista) {
                String date2 = formato.format(fechaHabil);
                if (date.equals(date2)) {
                    dias++;
                }
            }
        }
        getObjeto().setFechaNotificacion(fechaCreacion);
        getObjeto().setFechaVencimiento(fecha.getTime());
    }*/
    public void asignarFechas() throws ParseException {
        Date fechaCreacion = this.getObjeto().getFechaHoraCrea();
        //int origen = this.getObjeto().getMaeSolicitudOrigenId();
        int riesgoVida = this.getObjeto().getMaeSolicitudRiesgoVidalId();
        //String nombreOrigen = this.hashTipoSolicitudOrigen.get(origen).getNombre();
        String nombreRiesgoVida = this.hashTipoSolicitudRiesgoVida.get(riesgoVida).getNombre();
        int dias = 0;
        switch (nombreRiesgoVida) {
            case "Riesgo vital":
                dias = 1;
                break;
            case "Priorizado":
                dias = 2;
                break;
            case "Simple":
                dias = 3;
                break;
            case "Peticion general":
                dias = 15;
                break;
        }
        Calendar fecha = Calendar.getInstance();
        if (fechaCreacion == null) {
            fechaCreacion = new Date();
        }
        fecha.setTime(fechaCreacion);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        //List<Date> lista = new ArrayList();
        //lista = getAusCasoServicio().obtenerFechas(fechaCreacion);
        for (int i = 1; i <= dias; i++) {
            fecha.add(Calendar.DAY_OF_YEAR, 1);
            //Date f1 = fecha.getTime();
            /*String date = formato.format(f1);
            for (Date fechaHabil : lista) {
                String date2 = formato.format(fechaHabil);
                if (date.equals(date2)) {
                    dias++;
                }
            }*/
        }
        getObjeto().setFechaNotificacion(fechaCreacion);
        getObjeto().setFechaVencimiento(fecha.getTime());
    }

    public void asignarServiciosParaCasos(List<AusCasoServicio> serviciosAsignar) {
        getObjeto().setServiciosList(serviciosAsignar);
    }

    public void asignarAdjuntosParaCasos(List<AusAdjunto> adjuntosAsignar) {
        getObjeto().setAdjuntosList(adjuntosAsignar);
    }

    public void asignarAdjuntosParaServicios(List<AusAdjunto> adjuntosAsignar) {
        this.getServicioParaCrear().setAdjuntosList(adjuntosAsignar);
    }

    public void asignarAdjuntosParaSeguimiento(List<AusAdjunto> adjuntosAsignar) {
        this.getSeguimientoProcesar().setAdjuntosList(adjuntosAsignar);
    }

    private void asignarTelefonosEditar() {
        List<AusPersonaTelefono> listaInicial = new ArrayList();
        listaInicial.addAll(this.getObjeto().getAusPersonasId().getListaTelefonos());
        List<AusPersonaTelefono> listaFinal = new ArrayList();
        listaFinal.addAll(listaPersonaTelefono);
        List<AusPersonaTelefono> listaResultado = new ArrayList();

        for (AusPersonaTelefono telIni : listaInicial) {
            boolean encontro = false;
            for (AusPersonaTelefono telFin : listaFinal) {
                if (telIni.getId() != null && Objects.equals(telIni.getId(), telFin.getId())) {
                    telIni.setAccion(AusPersonaTelefono.ACCION_NINGUNO);
                    listaResultado.add(telIni);
                    listaFinal.remove(telFin);
                    encontro = true;
                    break;
                }
            }
            if (!encontro) {
                telIni.setAccion(AusPersonaTelefono.ACCION_BORRAR);
                listaResultado.add(telIni);
            }
        }
        for (AusPersonaTelefono detFin : listaFinal) {
            detFin.setAccion(AusPersonaTelefono.ACCION_INSERTAR);
            listaResultado.add(detFin);
        }
        asignarTelefonoParaPersona(listaResultado);
    }

    private void asignarTelefonosPeticionarioEditar() {
        List<AusPersonaTelefono> listaInicial = new ArrayList();
        listaInicial.addAll(this.getObjeto().getPeticionario().getListaTelefonos());
        List<AusPersonaTelefono> listaFinal = new ArrayList();
        listaFinal.addAll(listaPeticionarioTelefono);
        List<AusPersonaTelefono> listaResultado = new ArrayList();

        for (AusPersonaTelefono telIni : listaInicial) {
            boolean encontro = false;
            for (AusPersonaTelefono telFin : listaFinal) {
                if (telIni.getId() != null && Objects.equals(telIni.getId(), telFin.getId())) {
                    telIni.setAccion(AusPersonaTelefono.ACCION_NINGUNO);
                    listaResultado.add(telIni);
                    listaFinal.remove(telFin);
                    encontro = true;
                    break;
                }
            }
            if (!encontro) {
                telIni.setAccion(AusPersonaTelefono.ACCION_BORRAR);
                listaResultado.add(telIni);
            }
        }
        for (AusPersonaTelefono detFin : listaFinal) {
            detFin.setAccion(AusPersonaTelefono.ACCION_INSERTAR);
            listaResultado.add(detFin);
        }
        asignarTelefonoParaPeticionario(listaResultado);
    }

    private void asignarServiciosEditar() {
        List<AusCasoServicio> listaInicial = new ArrayList();
        listaInicial.addAll(this.getObjeto().getServiciosList());
        List<AusCasoServicio> listaFinal = new ArrayList();
        listaFinal.addAll(this.getListaServiciosCrear());
        List<AusCasoServicio> listaResultado = new ArrayList();

        for (AusCasoServicio serIni : listaInicial) {
            boolean encontro = false;
            for (AusCasoServicio serFin : listaFinal) {
                if (serIni.getId() != null && Objects.equals(serIni.getId(), serFin.getId())) {
                    serIni.setAccion(AusCasoServicio.ACCION_NINGUNO);
                    listaResultado.add(serIni);
                    listaFinal.remove(serFin);
                    encontro = true;
                    break;
                }
            }
            if (!encontro) {
                serIni.setAccion(AusCasoServicio.ACCION_BORRAR);
                listaResultado.add(serIni);
            }
        }
        for (AusCasoServicio detFin : listaFinal) {
            detFin.setAccion(AusCasoServicio.ACCION_INSERTAR);
            listaResultado.add(detFin);
        }
        asignarServiciosParaCasos(listaResultado);
    }

    private void asignarAdjuntosCasosEditar() {
        List<AusAdjunto> listaInicial = new ArrayList();
        listaInicial.addAll(listaAdjuntosCaso);
        List<AusAdjunto> listaFinal = new ArrayList();
        listaFinal.addAll(this.getObjeto().getAdjuntosList());
        List<AusAdjunto> listaResultado = new ArrayList();

        for (AusAdjunto adjIni : listaInicial) {
            boolean encontro = false;
            for (AusAdjunto adjFin : listaFinal) {
                if (adjIni.getId() != null && Objects.equals(adjIni.getId(), adjFin.getId())) {
                    adjIni.setAccion(AusAdjunto.ACCION_NINGUNO);
                    listaResultado.add(adjIni);
                    listaFinal.remove(adjFin);
                    encontro = true;
                    break;
                }
            }
            if (!encontro) {
                adjIni.setAccion(AusAdjunto.ACCION_BORRAR);
                listaResultado.add(adjIni);
            }
        }
        for (AusAdjunto detFin : listaFinal) {
            detFin.setAccion(AusAdjunto.ACCION_INSERTAR);
            listaResultado.add(detFin);
        }
        asignarAdjuntosParaCasos(listaResultado);
    }

    private void asignarAdjuntosSeguimientoEditar() {
        List<AusAdjunto> listaInicial = new ArrayList();
        listaInicial.addAll(listaAdjuntosSeguimiento);
        List<AusAdjunto> listaFinal = new ArrayList();
        listaFinal.addAll(this.getSeguimientoProcesar().getAdjuntosList());
        List<AusAdjunto> listaResultado = new ArrayList();

        for (AusAdjunto adjIni : listaInicial) {
            boolean encontro = false;
            for (AusAdjunto adjFin : listaFinal) {
                if (adjIni.getId() != null && Objects.equals(adjIni.getId(), adjFin.getId())) {
                    adjIni.setAccion(AusAdjunto.ACCION_NINGUNO);
                    listaResultado.add(adjIni);
                    listaFinal.remove(adjFin);
                    encontro = true;
                    break;
                }
            }
            if (!encontro) {
                adjIni.setAccion(AusAdjunto.ACCION_BORRAR);
                listaResultado.add(adjIni);
            }
        }
        for (AusAdjunto detFin : listaFinal) {
            detFin.setAccion(AusAdjunto.ACCION_INSERTAR);
            listaResultado.add(detFin);
        }
        asignarAdjuntosParaSeguimiento(listaResultado);
    }

    private void asignarAdjuntosServicioEditar() {
        List<AusAdjunto> listaInicial = new ArrayList();
        listaInicial.addAll(listaAdjuntosServicio);
        List<AusAdjunto> listaFinal = new ArrayList();
        listaFinal.addAll(this.getServicioParaCrear().getAdjuntosList());
        List<AusAdjunto> listaResultado = new ArrayList();

        for (AusAdjunto adjIni : listaInicial) {
            boolean encontro = false;
            for (AusAdjunto adjFin : listaFinal) {
                if (adjIni.getId() != null && Objects.equals(adjIni.getId(), adjFin.getId())) {
                    adjIni.setAccion(AusAdjunto.ACCION_NINGUNO);
                    listaResultado.add(adjIni);
                    listaFinal.remove(adjFin);
                    encontro = true;
                    break;
                }
            }
            if (!encontro) {
                adjIni.setAccion(AusAdjunto.ACCION_BORRAR);
                listaResultado.add(adjIni);
            }
        }
        for (AusAdjunto detFin : listaFinal) {
            detFin.setAccion(AusAdjunto.ACCION_INSERTAR);
            listaResultado.add(detFin);
        }
        asignarAdjuntosParaServicios(listaResultado);
    }

    public void listarSedesAsociadas() {
        getAusCasoServicio().listarSedes(this);
        PrimeFaces.current().ajax().update("frmCrear:sedes");
        //procesoFinal();

    }

    public void listarSedesAsociadasEdit() {
        getAusCasoServicio().listarSedes(this);
        PrimeFaces.current().ajax().update("frmEditar:sedes");
        //procesoFinal();
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        try {

            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_caso_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA);
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

            List<AusAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_CASOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getObjeto().getAdjuntosList().addAll(listaAnexos);
            }

            //agraegar el nuevo anexo a la lista de anexos de tutela
            AusAdjunto adjunto = new AusAdjunto();
            adjunto.setId(null);
            adjunto.setNombre(filename);
            adjunto.setArchivo(event.getFile().getFileName());
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getObjeto().getAdjuntosList().size());

            listaAnexos.add(adjunto);

            this.getObjeto().getAdjuntosList().add(adjunto);

            llenarSesionAdjuntos(SESION_ADJUNTO_CASOS, listaAnexos);

            PrimeFaces.current().resetInputs("frmCrear:tablaAnexosCasos");
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosCasos");
            PrimeFaces.current().resetInputs("frmEditar:tablaAnexosCasos");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosCasos");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto caso : " + e.getMessage());
            generarMensajes();
        }
    }

    public void handleFileUploadServicio(FileUploadEvent event) throws IOException {

        try {
            String file = FilenameUtils.getName(event.getFile().getFileName());
            int i = file.lastIndexOf(".");
            String ext = file.substring(i, file.length());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String nombre = "adjunto_servicio_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            }
            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            List<AusAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_SERVICIOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
                this.getServicioParaCrear().getAdjuntosList().addAll(listaAnexos);
            }
            //agraegar el nuevo anexo a la lista de anexos de tutela
            AusAdjunto adjunto = new AusAdjunto();
            adjunto.setId(null);
            adjunto.setNombre(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(this.getServicioParaCrear().getAdjuntosList().size());

            listaAnexos.add(adjunto);

            this.getServicioParaCrear().getAdjuntosList().add(adjunto);

            llenarSesionAdjuntos(SESION_ADJUNTO_SERVICIOS, listaAnexos);

            PrimeFaces.current().resetInputs("frmCrearServicio:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmCrearServicio:tablaAnexosServicios");
            PrimeFaces.current().resetInputs("frmEditarServicio:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmEditarServicio:tablaAnexosServicios");
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
            String nombre = "adjunto_seguimiento_";
            String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
            UploadedFile archivo = event.getFile();
            byte[] arreglo = archivo.getInputStream().readAllBytes();
            InputStream input = new ByteArrayInputStream(arreglo);

            String ruta = PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA);
            if (ruta == null) {
                throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
            }

            OutputStream output = new FileOutputStream(new File(ruta, filename));

            IOUtils.copy(input, output);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);

            List<AusAdjunto> listaAnexos = obtenerSesionAdjuntos(SESION_ADJUNTO_SEGUIMIENTOS);
            if (listaAnexos == null) {
                listaAnexos = new ArrayList<>();
            }
            //agraegar el nuevo anexo a la lista de anexos de tutela
            AusAdjunto adjunto = new AusAdjunto();
            adjunto.setId(null);
            adjunto.setNombre(filename);
            adjunto.setArchivo(file);
            adjunto.setRuta(ruta);
            this.auditoriaGuardar(adjunto);
            adjunto.setPos(listaAnexos.size());

            listaAnexos.add(adjunto);

            String tipoSeguimiento = (String) event.getComponent().getAttributes().get("tipoSeguimiento");

            if (tipoSeguimiento.equalsIgnoreCase("creacion")) {
                this.getObjeto().getSeguimientoAdicional().getAdjuntosList().add(adjunto);
            } else {
                this.getSeguimientoProcesar().getAdjuntosList().add(adjunto);
            }

            llenarSesionAdjuntos(SESION_ADJUNTO_SEGUIMIENTOS, listaAnexos);
            PrimeFaces.current().resetInputs("frmCrear:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosSeguimiento");
            PrimeFaces.current().resetInputs("frmEditar:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosSeguimiento");
            PrimeFaces.current().resetInputs("frmEditarSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().resetInputs("frmCrearSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmCrearSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().resetInputs("frmCerrarCaso:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmCerrarCaso:tablaAnexosSeguimiento");
        } catch (IOException e) {
            this.addError("Error al cargar archivo adjunto seguimiento : " + e.getMessage());
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
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosCasos");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosCasos");
        }
    }

    public void delAdjuntoCasoMemoria(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : this.getObjeto().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getObjeto().setAdjuntosList(lista);
    }

    public void borrarAdjuntoSeguimientoProcesar(int pos) {
        try {
            delAdjuntoSeguimientoProcesar(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCerrarCaso:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditarSeguimiento:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmCrearSeguimiento:tablaAnexosSeguimiento");
        }
    }

    public void delAdjuntoSeguimientoProcesar(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : this.getSeguimientoProcesar().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getSeguimientoProcesar().setAdjuntosList(lista);
    }

    public void borrarAdjuntoSeguimientoAdicional(int pos) {
        try {
            delAdjuntoSeguimientoAdicional(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:tablaAnexosSeguimiento");
            PrimeFaces.current().ajax().update("frmEditar:tablaAnexosSeguimiento");
        }
    }

    public void delAdjuntoSeguimientoAdicional(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : this.getObjeto().getSeguimientoAdicional().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getObjeto().getSeguimientoAdicional().setAdjuntosList(lista);
    }

    public void borrarAdjuntoServicioMemoria(int pos) {
        try {
            delAdjuntoServicioMemoria(pos);
        } catch (Exception e) {
            super.addError("No es posible borrar Adjunto");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditarServicio:tablaAnexosServicios");
            PrimeFaces.current().ajax().update("frmCrearServicio:tablaAnexosServicios");
        }
    }

    public void delAdjuntoServicioMemoria(int pos) {
        List<AusAdjunto> lista = new ArrayList();
        int i = 0, j = 0;
        for (AusAdjunto det : this.getServicioParaCrear().getAdjuntosList()) {
            if (j != pos) {
                det.setPos(i);
                lista.add(det);
                i++;
            }
            j++;
        }
        this.getServicioParaCrear().setAdjuntosList(lista);
    }

    private void llenarSesionAdjuntos(String nombreSession, List<AusAdjunto> listaAnexos) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nombreSession, listaAnexos);
    }

    private List<AusAdjunto> obtenerSesionAdjuntos(String nombreSesion) {
        List<AusAdjunto> listaAnexos = (List<AusAdjunto>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nombreSesion);
        return listaAnexos;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    private int contarEstadoPermitidosParaSeguimiento(List<AusCasoServicio> listaServicios) {
        int numeroSeguimientoAprovados = 0;
        for (AusCasoServicio servicio : listaServicios) {

            /*if (servicio.getMaeEstadoId() == AusCasoServicio.ESTADO_ANULADO
                    || servicio.getMaeEstadoId() == AusCasoServicio.ESTADO_RESUELTO
                    || servicio.getMaeEstadoId() == AusCasoServicio.ESTADO_CERRADO) {*/
            if (servicio.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ANULADO))
                    || servicio.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO))
                    || servicio.getMaeEstadoId() == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))) {
                numeroSeguimientoAprovados++;
            }
        }

        return numeroSeguimientoAprovados;
    }

    public void setAnexoAVisualizar(AusAdjunto adjunto) throws FileNotFoundException {

        String pathCompleto = adjunto.getRuta() + File.separator + adjunto.getNombre();
        this.setUrlVisualizarAdjunto(pathCompleto);
        getTempPdfFile();

    }

    public StreamedContent getTempPdfFile() throws FileNotFoundException {

        DefaultStreamedContent dsc = new DefaultStreamedContent();
        String urlVisualizar = this.getUrlVisualizarAdjunto();

        if (urlVisualizar != null) {
            int i = urlVisualizar.lastIndexOf(".");
            String ext = urlVisualizar.substring(i, urlVisualizar.length());
            File pdfFile = new File(urlVisualizar);
            InputStream stream = new FileInputStream(pdfFile);
            try {
                if (ext.equalsIgnoreCase(".pdf")) {
                    adjuntoVisualizarPDF = true;
                    dsc = DefaultStreamedContent.builder().name(pdfFile.getName()).stream(() -> stream).contentType("application/pdf").build();

                    //dsc = new DefaultStreamedContent(new FileInputStream(pdfFile), "application/pdf", pdfFile.getName());
                } else if (ext.equalsIgnoreCase(".jpg")) {
                    adjuntoVisualizarPDF = false;
                    dsc = DefaultStreamedContent.builder().name(pdfFile.getName()).stream(() -> stream).contentType("image/jpg").build();
                    //dsc = new DefaultStreamedContent(new FileInputStream(pdfFile), "image/jpg", pdfFile.getName());
                } else {
                    adjuntoVsisualizarOtraExtension = true;
                }
            } catch (Exception e) {
                adjuntoVsisualizarOtraExtension = true;
            }
            PrimeFaces.current().executeScript("PF('anexoDlg').show()");
        }
        return dsc;

    }

    public void descargarAnexo(AusAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + File.separator + adjunto.getNombre();
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

        }
    }

    public void listarMotivosSegunAmbito() {
        if (this.getServicioParaCrear().getMaeServicioAmbitoId() > 0) {
            getAusCasoServicio().listarMotivoPorAmbito(this);
            procesoFinal();
        }
    }

    public void evaluarHabilitarCamposEdicion() {
        boolean desactivar = verificarHabilitacionCamposPorMotivo();
        setLimpiarDatosHabilitadosServicio(desactivar);
        boolean desactivarEstado = verificarHabilitacionCampoPorEstado();
        setLimpiarDatosAcordeEstadoServicio(desactivarEstado);
        PrimeFaces.current().ajax().update("frmEditarServicio");
    }

    public boolean isDeshabilitarCampoNumeroRadicado() {
        try {
            if (getObjeto().getMaeSolicitudOrigenId() != 0) {
                if (hashTipoSolicitudOrigen.get(getObjeto().getMaeSolicitudOrigenId()).getNombre().equals("SuperSalud")) {
                    deshabilitarCampoNumeroRadicado = true;
                } else {
                    deshabilitarCampoNumeroRadicado = false;
                }
            }
            return deshabilitarCampoNumeroRadicado;
        } catch (Exception e) {
            return false;
        }
    }

    public void setDeshabilitarCampoNumeroRadicado(boolean deshabilitarCampoNumeroRadicado) {
        this.deshabilitarCampoNumeroRadicado = deshabilitarCampoNumeroRadicado;
    }

    public void evaluarHabilitarCampoNumeroRadicadoCreat() {
        boolean desactivar = false;
        int origen = this.getObjeto().getMaeSolicitudOrigenId();
        String nombreOrigen = this.getHashTipoSolicitudOrigen().get(origen).getNombre();
        if (nombreOrigen.equals("SuperSalud")) {
            desactivar = true;
        }
        this.setDeshabilitarCampoNumeroRadicado(desactivar);
        PrimeFaces.current().ajax().update("frmCrear:panelCasos");
    }

    public void evaluarHabilitarCampoNumeroRadicadoEdit() {
        boolean desactivar = false;
        int origen = this.getObjeto().getMaeSolicitudOrigenId();
        String nombreOrigen = this.getHashTipoSolicitudOrigen().get(origen).getNombre();
        if (nombreOrigen.equals("SuperSalud")) {
            desactivar = true;
        }
        this.setDeshabilitarCampoNumeroRadicado(desactivar);
        PrimeFaces.current().ajax().update("frmEditar:panelCasos");
    }

    public void evaluarHabilitarCamposInsercion() {
        boolean desactivar = verificarHabilitacionCamposPorMotivo();
        setLimpiarDatosHabilitadosServicio(desactivar);
        boolean desactivarEstado = verificarHabilitacionCampoPorEstado();
        setLimpiarDatosAcordeEstadoServicio(desactivarEstado);
        PrimeFaces.current().ajax().update("frmCrearServicio");
    }

    public void evaluarHabilitarCampoPorEstadoInsert() {
        boolean desactivarEstado = verificarHabilitacionCampoPorEstado();
        setLimpiarDatosAcordeEstadoServicio(desactivarEstado);
        PrimeFaces.current().ajax().update("frmCrearServicio");
    }

    public void evaluarHabilitarCampoPorEstadoEdit() {
        boolean desactivarEstado = verificarHabilitacionCampoPorEstado();
        setLimpiarDatosAcordeEstadoServicio(desactivarEstado);
        PrimeFaces.current().ajax().update("frmEditarServicio");
    }

    public boolean verificarHabilitacionCamposPorMotivo() {
        boolean desactivar = false;
        int idMotivo = this.getServicioParaCrear().getMaeServicioMotivoId();
        if (idMotivo > 0 && hashTipoServicioMotivo != null) {
            Maestro maestro = hashTipoServicioMotivo.get(idMotivo);
            desactivar = false;
            setDeshabilitarCamposSeguimientoPorMotivo(desactivar);
        }
        return desactivar;
    }

    public void habilitarCamposServicio() {
        try {
            getAusCasoServicio().crearServicioCamposObligatoriosParaEstados(this);
        } catch (Exception ex) {
            addError("Hubo error en la acciones");
        }
        generarMensajes();
    }

    public void habilitarCamposServicioRestantes() {
        try {
            getAusCasoServicio().crearServicioCamposObligatoriosParaTipoAdministracion(this);
        } catch (Exception ex) {
            addError("Hubo error en la acciones");
        }
        generarMensajes();
    }

    public boolean verificarHabilitacionCampoPorEstado() {
        //boolean desactivar = false;
        boolean desactivarMio = false;
        //int idEstado = this.getServicioParaCrear().getMaeEstadoId();
        int idEstadoMio = this.getServicioParaCrear().getMaeEstadoId();
        //idEstado = idEstado <= 0 ? AusCasoServicio.ESTADO_ASIGNADO : idEstado;
        idEstadoMio = idEstadoMio <= 0 ? Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO)) : idEstadoMio;
        if (getHashEstadoServicio() != null && !getHashEstadoServicio().isEmpty()) {
            //String desEstado = getTipoEstadoServicio(idEstado);
            if (getHashEstadoServicio().get(idEstadoMio) != null) {
                String nombre = getHashEstadoServicio().get(idEstadoMio).getNombre();
                //String desEstado = getListaEstadosServicioEnCreacion().get(idEstado).getNombre();
                //desactivar = desEstado != null && !desEstado.equalsIgnoreCase(AusCasoServicio.getEstadoStr(AusCasoServicio.ESTADO_ASIGNADO));
                desactivarMio = nombre != null && !nombre.equalsIgnoreCase(getHashEstadoServicio().get(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_ASIGNADO))).getNombre());
                setDeshabilitarCamposSeguimientoPorEstado(desactivarMio);
            }
        }
        return desactivarMio;
    }

    public void setLimpiarDatosAcordeEstadoServicio(boolean desactivar) {
        if (desactivar) {
            AusCasoServicio servicio = this.getServicioParaCrear();
            servicio.setDescripcion("");
            //servicio.setIdUsuarioResponsable(0);
            servicio.getIdUsuarioResponsable().setId(0);
            servicio.getIdUsuarioResponsable().setNombre(null);
        }
    }

    public void setLimpiarDatosHabilitadosServicio(boolean desactivar) {
        if (desactivar) {
            AusCasoServicio servicio = this.getServicioParaCrear();
//            servicio.setMaeIps(0);
//            servicio.setMaeServicio(0);
            servicio.setCantidad(0);
            servicio.setDescripcion("");
//            servicio.setMaeDiagnostico(0);
            //servicio.setIdUsuarioResponsable(0);
            servicio.getIdUsuarioResponsable().setId(0);
        }
    }

    public void asignarEstadoInicialCaso() {
        //int idEstadoSol = getIdTipoEstadoSolicitud(AusCasoBean.DESC_CASO_ESTADO_RADICADO);
        int idEstadoSol = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DES_CASO_EST_RAD));
        this.getObjeto().setMaeSolicitudEstadoId(idEstadoSol);
    }

    public void asignarEstadoCasoSegunSeguimiento() {
        int idEstadoSeguimiento = this.getObjeto().getSeguimientoAdicional().getMaeEstadoId();
        int estadoCaso = 0;
        if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMI_ESTADO_CANCELADO))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CANCELADO));
        } else if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMIENTO_ESTADO_CERRADO))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO));
        } else if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMI_ESTADO_GESTION))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_GESTION));
        } else if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMI_ESTADO_SOLUCIONADO))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_SOLUCIONADO));
        }
        if (estadoCaso > 0 && hashTipoEstadoSolicitud != null) {
            Maestro maestro = hashTipoEstadoSolicitud.get(estadoCaso);
            if (maestro != null) {
                int estadoParaCaso = maestro.getId();
                if (estadoParaCaso > 0) {
                    this.getObjeto().setMaeSolicitudEstadoId(estadoParaCaso);
                    this.getObjeto().setMaeSolicitudEstadoCodigo(maestro.getValor());
                    this.getObjeto().setMaeSolicitudEstadoValor(maestro.getNombre());
                }
            }
        }
    }

    /*public void asignarEstadoCasoSegunSeguimiento() {
        int idEstadoSeguimiento = this.getObjeto().getSeguimientoAdicional().getMaeEstadoId();
        if (idEstadoSeguimiento > 0 && hashTipoSeguimiento != null) {
            Maestro maestro = hashTipoSeguimiento.get(idEstadoSeguimiento);
            if (maestro != null) {
                int estadoParaCaso = maestro.getId();
                if (estadoParaCaso > 0) {
                    this.getObjeto().setMaeSolicitudEstadoId(estadoParaCaso);
                    this.getObjeto().setMaeSolicitudEstadoCodigo(maestro.getValor());
                    this.getObjeto().setMaeSolicitudEstadoValor(maestro.getNombre());
                }
            }
        }
    }*/
    public boolean validarEstadoCasoSegunSeguimiento(AusSeguimiento seguimiento) {
        boolean esValidoEstado = true;
        int idEstadoSeguimiento = seguimiento.getMaeEstadoId();
        if (idEstadoSeguimiento > 0 && getHashTipoSeguimiento() != null) {
            Maestro maestro = getHashTipoSeguimiento().get(idEstadoSeguimiento);
            int estadoParaCaso = maestro.getId();
            Maestro maestroCaso = getHashTipoEstadoSolicitud().get(estadoParaCaso);
            if (maestroCaso != null && Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_CERRA)) == maestroCaso.getId()) {
                //if (maestroCaso != null && getIdTipoEstadoSolicitud(DESC_SEGUIMIENTO_CERRADO) == maestroCaso.getId()) {
                int cantidadEstadosValidados = contarEstadoPermitidosParaSeguimiento(this.getObjeto().getServiciosList());
                esValidoEstado = cantidadEstadosValidados == this.getObjeto().getServiciosList().size();
            }
            if (!esValidoEstado) {
                mostrarErrorSeguimientoEstadoDependienteSolucionado();
            }
        }
        return esValidoEstado;
    }

    public void asignarEstadoCasoSegunSeguimientoGestion() {
        int idEstadoSeguimiento = this.getSeguimientoProcesar().getMaeEstadoId();
        int estadoCaso = 0;
        if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMI_ESTADO_CANCELADO))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CANCELADO));
        } else if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMIENTO_ESTADO_CERRADO))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO));
        } else if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMI_ESTADO_GESTION))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_GESTION));
        } else if (idEstadoSeguimiento == Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMI_ESTADO_SOLUCIONADO))) {
            estadoCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_SOLUCIONADO));
        }
        if (estadoCaso > 0 && hashTipoEstadoSolicitud != null) {
            Maestro maestro = hashTipoEstadoSolicitud.get(estadoCaso);
            if (maestro != null) {
                int estadoParaCaso = maestro.getId();
                if (estadoParaCaso > 0) {
                    this.getObjeto().setMaeSolicitudEstadoId(estadoParaCaso);
                    this.getObjeto().setMaeSolicitudEstadoCodigo(maestro.getValor());
                    this.getObjeto().setMaeSolicitudEstadoValor(maestro.getNombre());
                }
            }
        }
    }

    /*public void asignarEstadoCasoSegunSeguimientoGestion() {
        int idEstadoSeguimiento = this.getSeguimientoProcesar().getMaeEstadoId();
        if (idEstadoSeguimiento > 0 && hashTipoSeguimiento != null) {
            Maestro maestro = hashTipoSeguimiento.get(idEstadoSeguimiento);
            if (maestro != null) {
                int estadoParaCaso = maestro.getId();
                if (estadoParaCaso > 0) {
                    this.getObjeto().setMaeSolicitudEstadoId(estadoParaCaso);
                    this.getObjeto().setMaeSolicitudEstadoCodigo(maestro.getValor());
                    this.getObjeto().setMaeSolicitudEstadoValor(maestro.getNombre());
                }
            }
        }
    }*/
    public boolean asignarEstadoCasoSegunModificacionSeguimiento() {
        boolean cambiaEstadoCaso = false;
        int idEstadoSeguimiento = this.getSeguimientoProcesar().getMaeEstadoId();
        if (idEstadoSeguimiento > 0 && hashTipoSeguimiento != null) {
            Maestro maestro = hashTipoSeguimiento.get(idEstadoSeguimiento);
            if (maestro != null) {
                int estadoParaCaso = maestro.getId();
                int estadoCaso = this.getObjeto().getMaeSolicitudEstadoId();
                //int estadoRadicado = getIdTipoSeguimiento(DESC_SEGUIMIENTO_RADICADO);
                int estadoRadicado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_RADIC));
                int estadoActualSeguimiento = this.getSeguimientoProcesar().getMaeSolicitudEstadoActual();
                if (estadoParaCaso > 0 && estadoCaso != estadoParaCaso
                        && idEstadoSeguimiento != estadoRadicado && estadoActualSeguimiento != idEstadoSeguimiento) {
                    this.getObjeto().setMaeSolicitudEstadoId(estadoParaCaso);
                    cambiaEstadoCaso = true;
                }
            }
        }
        return cambiaEstadoCaso;
    }

    public int getIdTipoSeguimiento(String nombreEstado) {
        int idEstado = 0;
        for (Map.Entry<Integer, Maestro> entry : hashTipoSeguimiento.entrySet()) {
            Integer key = entry.getKey();
            Maestro maestro = entry.getValue();
            if (maestro.getNombre().equalsIgnoreCase(nombreEstado)) {
                idEstado = maestro.getId();
                break;
            }
        }
        return idEstado;
    }

    public Maestro getMaestroSeguimiento(Integer idMaestro) {
        Maestro maest = new Maestro();
        for (Map.Entry<Integer, Maestro> entry : hashTipoSeguimiento.entrySet()) {
            Integer key = entry.getKey();
            Maestro maestro = entry.getValue();
            if (maestro.getId() == (idMaestro)) {
                maest = maestro;

                break;
            }
        }
        return maest;
    }

    public int getIdTipoEstadoSolicitud(String nombreEstado) {
        int idEstado = 0;
        for (Map.Entry<Integer, Maestro> entry : hashTipoEstadoSolicitud.entrySet()) {
            Integer key = entry.getKey();
            Maestro maestro = entry.getValue();
            if (maestro.getNombre().equalsIgnoreCase(nombreEstado)) {
                idEstado = maestro.getId();
                break;
            }
        }
        return idEstado;
    }

    public boolean verificarEstadoSegCerradoSolucionado() {
        boolean estado = true;
        //int idTipoSolucionado = getIdTipoSeguimiento(DESC_SEGUIMIENTO_SOLUCIONADO);
        //int idTipoSolucionado = getIdTipoSeguimiento(DESC_SEGUIMIENTO_CERRADO);
        //int idTipoCerrado = getIdTipoSeguimiento(DESC_SEGUIMIENTO_CERRADO);
        int idTipoSolucionado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_CERRA));
        int idTipoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_CERRA));
        for (AusSeguimiento seguimiento : this.getObjeto().getSeguimientosList()) {
            if (seguimiento.getMaeEstadoId() == idTipoSolucionado
                    || seguimiento.getMaeEstadoId() == idTipoCerrado) {
                estado = false;
                break;
            }
        }
        return estado;
    }

    public Map<Integer, Integer> getListaTiposEstratos() {
        return listaTiposEstratos;
    }

    public int getTipoEstrato(int id) {
        try {
            return listaTiposEstratos.get(id);
        } catch (Exception e) {
            return 0;
        }
    }

    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 5242880 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }

    public List<String> completarCorreo(String query) {
        List<String> listaCorreos = new ArrayList<>();

        listaCorreos.add(query + "@gmail.com");
        listaCorreos.add(query + "@hotmail.com");
        listaCorreos.add(query + "@outlook.com");

        return listaCorreos;
    }

    public void precargaPeticionario() {
        setPeticionario("No");
        setHabilitarPeticionario(true);
        setCamposObligatoriosPeticionario(false);
        if (getObjeto().getPeticionario() != null) {
            if (getObjeto().getPeticionario().getId() != null && getObjeto().getPeticionario().getId() > 0 && !getObjeto().getPeticionario().getNombres().equals("")) {
                setPeticionario("Si");
                setHabilitarPeticionario(false);
                setCamposObligatoriosPeticionario(true);
                //setHabilitarPeticionario(true);
            }
        }
    }
    
    public String getPeticionario() {
        return peticionario;
    }
    
    public void setPeticionario(String peticionario) {
        this.peticionario = peticionario;
    }

    public List<String> listaOpcionesSiNo() {
        List<String> lista = new ArrayList();
        lista.add("Si");
        lista.add("No");
        return lista;
    }

    public String getParentesco() {
        parentesco = getObjeto().getParentesco();
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
        this.getObjeto().setParentesco(parentesco);
    }

    public List<CntPrestadorSede> getListaPrestadorSedes() {
        return ListaPrestadorSedes;
    }

    public void setListaPrestadorSedes(List<CntPrestadorSede> ListaPrestadorSedes) {
        this.ListaPrestadorSedes = ListaPrestadorSedes;
    }

    public List<AusPersona> getListaPersonasHistorial() {
        return listaPersonasHistorial;
    }

    public void setListaPersonasHistorial(List<AusPersona> listaPersonasHistorial) {
        this.listaPersonasHistorial = listaPersonasHistorial;
    }

    public CntPrestadorSede getObjetoPrestadorSede() {
        return objetoPrestadorSede;
    }

    public void setObjetoPrestadorSede(CntPrestadorSede objetoPrestadorSede) {
        this.objetoPrestadorSede = objetoPrestadorSede;
    }

    public CntPrestadorSede getObjetoPrestadorIpsDestino() {
        return objetoPrestadorIpsDestino;
    }

    public void setObjetoPrestadorIpsDestino(CntPrestadorSede objetoPrestadorIpsDestino) {
        this.objetoPrestadorIpsDestino = objetoPrestadorIpsDestino;
    }

    public CntPrestador getObjetoPrestador() {
        return objetoPrestador;
    }

    public void setObjetoPrestador(CntPrestador objetoPrestador) {
        this.objetoPrestador = objetoPrestador;
    }

    public CntPrestador getObjetoPrestadorIps() {
        return objetoPrestadorIps;
    }

    public void setObjetoPrestadorIps(CntPrestador objetoPrestadorIps) {
        this.objetoPrestadorIps = objetoPrestadorIps;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public AusCaso getObjBorrarCaso() {
        if (objBorrarCaso == null) {
            objBorrarCaso = new AusCaso();
        }
        return objBorrarCaso;
    }

    public void setObjBorrarCaso(AusCaso objBorrarCaso) {
        this.objBorrarCaso = objBorrarCaso;
    }

    public AusCaso getObjServicioResuelto() {
        if (objServicioResuelto == null) {
            objServicioResuelto = new AusCaso();
        }
        return objServicioResuelto;
    }

    public void setObjServicioResuelto(AusCaso objServicioResuelto) {
        this.objServicioResuelto = objServicioResuelto;
    }

    public AusCaso getObjRevertirCaso() {
        if (objRevertirCaso == null) {
            objRevertirCaso = new AusCaso();
        }
        return objRevertirCaso;
    }

    public void setObjRevertirCaso(AusCaso objRevertirCaso) {
        this.objRevertirCaso = objRevertirCaso;
    }

    public AusCaso getObjCerrarCaso() {
        if (objCerrarCaso == null) {
            objCerrarCaso = new AusCaso();
        }
        return objCerrarCaso;
    }

    public void setObjCerrarCaso(AusCaso objCerrarCaso) {
        this.objCerrarCaso = objCerrarCaso;
    }

    public List<AusCasoServicio> getListaServiciosResultos() {
        if (listaServiciosResultos == null) {
            listaServiciosResultos = new ArrayList<>();
        }
        return listaServiciosResultos;
    }

    public void setListaServiciosResultos(List<AusCasoServicio> listaServiciosResultos) {
        this.listaServiciosResultos = listaServiciosResultos;
    }

    public SelMedicamentoBean getMedicamentosBean() {
        medicamentosBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return medicamentosBean;
    }

    public void setMedicamentosBean(SelMedicamentoBean medicamentosBean) {
        this.medicamentosBean = medicamentosBean;
    }

    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public SelInsumosBean getInsumosBean() {
        insumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return insumosBean;
    }

    public void setInsumosBean(SelInsumosBean insumosBean) {
        this.insumosBean = insumosBean;
    }

    public List<String> listaParentesco() {
        List<String> lista = new ArrayList();
        lista.add("Padre");
        lista.add("Madre");
        lista.add("Hijo(a)");
        lista.add("Abuelo(a)");
        lista.add("Conyuge");
        lista.add("Otro");
        return lista;
    }

    public boolean isHabilitarPeticionario() {
        return habilitarPeticionario;
    }

    public void setHabilitarPeticionario(boolean habilitarPeticionario) {
        this.habilitarPeticionario = habilitarPeticionario;
    }

    public boolean isCamposObligatoriosPeticionario() {
        return camposObligatoriosPeticionario;
    }

    public void setCamposObligatoriosPeticionario(boolean camposObligatoriosPeticionario) {
        this.camposObligatoriosPeticionario = camposObligatoriosPeticionario;
    }

    public void habilitarCamposPeticionario() {
        if (peticionario != null && peticionario.equals("Si")) {
            setHabilitarPeticionario(false);
            setCamposObligatoriosPeticionario(true);
        } else {
            setHabilitarPeticionario(true);
            setCamposObligatoriosPeticionario(false);
        }
        PrimeFaces.current().ajax().update("frmCrear:panelPeticionarioCrear");
        //PrimeFaces.current().ajax().update("frmCrear:telefonosPeticionarioCrear");
    }

    public void habilitarCamposPeticionarioEditar() {
        if (peticionario.equals("Si")) {
            setHabilitarPeticionario(true);
            setCamposObligatoriosPeticionario(true);
        } else {
            setHabilitarPeticionario(false);
            setCamposObligatoriosPeticionario(false);
        }
        PrimeFaces.current().ajax().update("frmEditar:panelPeticionario");
        PrimeFaces.current().ajax().update("frmEditar:telefonosPeticionario");
    }

    public boolean isIsListarVencidos() {
        return isListarVencidos;
    }

    public void setIsListarVencidos(boolean isListarVencidos) {
        this.isListarVencidos = isListarVencidos;
    }

    public int getCantidadVencidos() {
        return cantidadVencidos;
    }

    public void setCantidadVencidos(int cantidadVencidos) {
        this.cantidadVencidos = cantidadVencidos;
    }

    public void cerrarAutomaticamente(String descripcion) throws ParseException {
        PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        PrimeFaces.current().executeScript("PF('dialogoGestion').hide();");
        setDescripcionServicio(descripcion);
        this.setAccion(ACCION_ADICIONAL_1);
        getAusCasoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCasos");
        refrescarCasos();
    }

    public int obtenerSuperSalud() {
        int id = 0;
        for (Maestro origen : getListaTipoSolicitudEnteControl()) {
            if (origen.getNombre().equals("SuperSalud")) {
                id = origen.getId();
            }
        }
        return id;
    }

    public int getIdCasoCierre() {
        return idCasoCierre;
    }

    public void setIdCasoCierre(int idCasoCierre) {
        this.idCasoCierre = idCasoCierre;
    }

    public int getHorasSeguimiento() {
        return horasSeguimiento;
    }

    public void setHorasSeguimiento(int horasSeguimiento) {
        this.horasSeguimiento = horasSeguimiento;
    }

    public int obtenerEstado(String estado) {
        int id = 0;
        for (Maestro maestro : getListaTipoEstadoSolicitud()) {
            if (maestro.getNombre().equals(estado)) {
                id = maestro.getId();
            }
        }
        return id;
    }

    public int obtenerEstadoServicio(Integer estado) {
        try {
            return listaEstadosServicio.get(estado).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getCodigoCie() {
        return codigoCie;
    }

    public void setCodigoCie(String codigoCie) {
        this.codigoCie = codigoCie;
    }

    public String getNombreCie() {
        return nombreCie;
    }

    public void setNombreCie(String nombreCie) {
        this.nombreCie = nombreCie;
    }

    public Integer getIdCie() {
        return idCie;
    }

    public void setIdCie(Integer idCie) {
        this.idCie = idCie;
    }

    public void buscarCieEnSistema() {
        boolean validar = false;
        if (getCodigoCie() != null || getNombreCie() != null) {
            if (getCodigoCie() != null) {
                validar = true;
            } else {
                if (getNombreCie() != null) {
                    if (getNombreCie().length() >= 7) {
                        validar = true;
                    } else {
                        this.addError("El nombre del CIE-X debe contener mas de 8 caracteres");
                    }
                }
            }

            if (validar) {
                super.setAccion(Url.ACCION_LISTAR);
                super.setDoAccion(Url.ACCION_MODIFICAR);
                getAusCasoServicio().Accion(this);
            }
        }
        this.generarMensajes();
    }

    public int getContadorArchivos() {
        return contadorArchivos;
    }

    public void setContadorArchivos(int contadorArchivos) {
        this.contadorArchivos = contadorArchivos;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public List<Maestro> getListaCanalSuperSalud() {
        return listaCanalSuperSalud;
    }

    public void setListaCanalSuperSalud(List<Maestro> listaCanalSuperSalud) {
        this.listaCanalSuperSalud = listaCanalSuperSalud;
    }

    public HashMap<Integer, Maestro> getHashCanalSuperSalud() {
        return hashCanalSuperSalud;
    }

    public void setHashCanalSuperSalud(HashMap<Integer, Maestro> hashCanalSuperSalud) {
        this.hashCanalSuperSalud = hashCanalSuperSalud;
    }

    public String getCanalSuperSalud(int id) {
        try {
            return hashCanalSuperSalud.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void refrescarDiagnosticos() {
        this.setAccion(ACCION_LISTAR);
        this.setDoAccion(ACCION_EDITAR);
        getAusCasoServicio().Accion(this);
    }

    public boolean validarEstadoSeguimiento() {
        boolean valido = true;
        //int idCerrado = getIdTipoSeguimiento(DESC_SEGUIMIENTO_CERRADO); 
        int idCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.DESC_SEGUI_CERRA));
        for (AusSeguimiento seg : this.getObjeto().getSeguimientosList()) {
            if (seg.getMaeEstadoId() == idCerrado) {
                valido = false;
            }
        }
        return valido;
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = ubicacionesRecursiva.get(id).getUbicacionPadre().getId();
            return ubicacionesRecursiva.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void verListaMaDiagnosticos() {
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }

    public void verListaMaEspecialidades() {
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').show()");
        PrimeFaces.current().ajax().update("frmEspecialidadBusqueda");
    }

    public void consultarSedes() {
        try {
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrestadorLista:tablaRegistrosIps");
            //dataTable2.clearLazyCache();
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmPrestadorLista:tablaRegistrosIps");
            PrimeFaces.current().ajax().update("frmPrestadorLista:hPanelIps");
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta(1).setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmPrestadorLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarSedesDestino() {
        try {
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrestadorIpsDestino:tablaRegistrosIpsDestino");
            //dataTable2. clearLazyCache();
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().resetInputs("frmPrestadorIpsDestino:tablaRegistrosIpsDestino");
            PrimeFaces.current().ajax().update("frmPrestadorIpsDestino:hPanelIpsDestino");
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmPrestadorIpsDestino");
            PrimeFaces.current().executeScript("PF('dialogoIpsDestino').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoDiagnostico() {
        
        MaDiagnostico maObjeto = getSelDiagnosticosBean().getObjeto();
        //this.servicioParaCrear.setObjetoDiagnostico(maObjetoDianostico);
        this.servicioParaCrear.setMaDiagnosticosId(maObjeto.getId());
        this.servicioParaCrear.setMaDiagnosticosCodigo(maObjeto.getCodigo());
        this.servicioParaCrear.setMaDiagnosticosValor(maObjeto.getNombre());
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmCrearServicio:labelCiexCodigo");
        PrimeFaces.current().ajax().update("frmCrearServicio:labelCiexValor");
        PrimeFaces.current().ajax().update("frmEditarServicio:labelCiexCodigo");
        PrimeFaces.current().ajax().update("frmEditarServicio:labelCiexValor");
        PrimeFaces.current().ajax().update("frmDuplicarServicio:labelCiexCodigo");
        PrimeFaces.current().ajax().update("frmDuplicarServicio:labelCiexValor");
        //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
        PrimeFaces.current().ajax().update("frmEditarServicioGestion:labelCiexCodigo");
        PrimeFaces.current().ajax().update("frmEditarServicioGestion:labelCiexValor");
    }

    public void cerrarDialogoEspecialidad() {
        MaEspecialidad maObjeto = getSelEspecialidadBean().getObjeto();
        this.servicioParaCrear.setMaServicioId(maObjeto.getId());
        this.servicioParaCrear.setMaServicioCodigo(maObjeto.getCodigo());
        this.servicioParaCrear.setMaServicioValor(maObjeto.getNombre());
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmCrearServicio:labelEspecialidadCodigo");
        PrimeFaces.current().ajax().update("frmCrearServicio:labelEspecialidadValor");
        PrimeFaces.current().ajax().update("frmEditarServicio:labelEspecialidadCodigo");
        PrimeFaces.current().ajax().update("frmEditarServicio:labelEspecialidadValor");
        PrimeFaces.current().ajax().update("frmDuplicarServicio:labelEspecialidadCodigo");
        PrimeFaces.current().ajax().update("frmDuplicarServicio:labelEspecialidadValor");
        //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
        PrimeFaces.current().ajax().update("frmEditarServicioGestion:labelEspecialidadCodigo");
        PrimeFaces.current().ajax().update("frmEditarServicioGestion:labelEspecialidadValor");
    }

    public String obtenerMunicipio(int id) {
        try {
            return ubicacionesRecursiva.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public SelDiagnosticosBean getSelDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    public SelEspecialidadesBean getSelEspecialidadBean() {
        especialidadesBean = (SelEspecialidadesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selEspecialidadesBean");
        return especialidadesBean;
    }

    public void consultarMedicamento() {
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
    }

    public void cerrarDialogoMedicamento() {
        //2024-06-24 jyperez validamos si estamos creando el servicio para actualizar la lista y no actualizamos el objeto
        if(crearServicio) {
            if (contTecnologiaServicios < LIMITE_CANTIDAD_TECNOLOGIAS_SERVICIOS) {
                //2025-03-12 jyperez adicionamos consulta y validación de servicios que contengan ya esta tecnologia para el usuario
                setServicioParaConsultar(new AusCasoServicio());
                getServicioParaConsultar().setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)));
                getServicioParaConsultar().setMaTecnologiaId(getMedicamentosBean().getObjeto().getId());
                getAusCasoServicio().consultarCasosTecnologia(this);
                if (getListaCasosSimilares() != null && !getListaCasosSimilares().isEmpty()) {
                    //lanzamos un mensaje que indique la cantidad de PQRs que tienen un dato asociado a la tecnología
                    mostrarMensajeServicioDuplicado(Tecnologia.TIPO_TECNOLOGIA_MEDICAMENTO);
                } else {
                    contTecnologiaServicios++;
                    Tecnologia tecnologia = new Tecnologia(contTecnologiaServicios);
                    //2024-06-24 jyperez ahora en la lista almacenamos la tecnología escogida. En los campos de servicioParaCrear no se almacenará ninguno.
                    tecnologia.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)));
                    tecnologia.setMaTecnologiaId(getMedicamentosBean().getObjeto().getId());
                    tecnologia.setMaTecnologiaCodigo(getMedicamentosBean().getObjeto().getCum());
                    tecnologia.setMaTecnologiaValor(getMedicamentosBean().getObjeto().getDescripcionInvima());
                    getListaTecnologiaServicios().add(tecnologia);
                    PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
                    PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
                    //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
                    PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
                    PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
                    PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
                    generarMensajes();
                }
            } else {
                this.addError("La cantidad máxima de Tecnologías a agregar es " + LIMITE_CANTIDAD_TECNOLOGIAS_SERVICIOS);
                PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
                PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
                //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
                PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
                PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
                PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
                generarMensajes();
            }
        } else {
            MaMedicamento medicamento = getMedicamentosBean().getObjeto();
            getServicioParaCrear().setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)));
            getServicioParaCrear().setMaTecnologiaId(medicamento.getId());
            getServicioParaCrear().setMaTecnologiaCodigo(medicamento.getCum());
            getServicioParaCrear().setMaTecnologiaValor(medicamento.getDescripcionInvima());
            PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
            PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
            //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
            PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
            generarMensajes();
        }
    }

    public void consultarInsumo() {
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");
    }

    public void cerrarDialogoInsumo() {
        //2024-06-24 jyperez validamos si estamos creando el servicio para actualizar la lista y no actualizamos el objeto
        if(crearServicio) {
            if (contTecnologiaServicios < LIMITE_CANTIDAD_TECNOLOGIAS_SERVICIOS) {
                //2025-03-12 jyperez adicionamos consulta y validación de servicios que contengan ya esta tecnologia para el usuario
                setServicioParaConsultar(new AusCasoServicio());
                getServicioParaConsultar().setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_INSUMO)));
                getServicioParaConsultar().setMaTecnologiaId(getInsumosBean().getObjeto().getId());
                getAusCasoServicio().consultarCasosTecnologia(this);
                if (getListaCasosSimilares() != null && !getListaCasosSimilares().isEmpty()) {
                    //lanzamos un mensaje que indique la cantidad de PQRs que tienen un dato asociado a la tecnología
                    mostrarMensajeServicioDuplicado(Tecnologia.TIPO_TECNOLOGIA_INSUMO);
                } else {
                    contTecnologiaServicios++;
                    Tecnologia tecnologia = new Tecnologia(contTecnologiaServicios);
                    //2024-06-24 jyperez ahora en la lista almacenamos la tecnología escogida. En los campos de servicioParaCrear no se almacenará ninguno.
                    tecnologia.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_INSUMO)));
                    tecnologia.setMaTecnologiaId(getInsumosBean().getObjeto().getId());
                    tecnologia.setMaTecnologiaCodigo(getInsumosBean().getObjeto().getCodigo());
                    tecnologia.setMaTecnologiaValor(getInsumosBean().getObjeto().getDescripcion());
                    getListaTecnologiaServicios().add(tecnologia);
                    PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
                    PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
                    //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
                    PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
                    PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
                    PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
                    generarMensajes();
                }
            } else {
                this.addError("La cantidad máxima de Tecnologías a agregar es " + LIMITE_CANTIDAD_TECNOLOGIAS_SERVICIOS);
                PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
                PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
                //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
                PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
                PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
                PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
                generarMensajes();
            }
        } else {
            MaInsumo insumo = getInsumosBean().getObjeto();
            getServicioParaCrear().setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_INSUMO)));
            getServicioParaCrear().setMaTecnologiaId(insumo.getId());
            getServicioParaCrear().setMaTecnologiaCodigo(insumo.getCodigo());
            getServicioParaCrear().setMaTecnologiaValor(insumo.getDescripcion());
            PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
            PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
            //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
            PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
            generarMensajes();
        }
        
    }

    public void consultarTecnologia() {
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void cerrarDialogoTecnologia() {
        //2024-06-24 jyperez validamos si estamos creando el servicio para actualizar la lista y no actualizamos el objeto
        if(crearServicio) {
            if (contTecnologiaServicios < LIMITE_CANTIDAD_TECNOLOGIAS_SERVICIOS) {
                //2025-03-12 jyperez adicionamos consulta y validación de servicios que contengan ya esta tecnologia para el usuario
                setServicioParaConsultar(new AusCasoServicio());
                getServicioParaConsultar().setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_TECNOLOGIA)));
                getServicioParaConsultar().setMaTecnologiaId(getTecnologiasBean().getObjeto().getId());
                getAusCasoServicio().consultarCasosTecnologia(this);
                if (getListaCasosSimilares() != null && !getListaCasosSimilares().isEmpty()) {
                    //lanzamos un mensaje que indique la cantidad de PQRs que tienen un dato asociado a la tecnología
                    mostrarMensajeServicioDuplicado(Tecnologia.TIPO_TECNOLOGIA_TECNOLOGIA);
                } else {
                    contTecnologiaServicios++;
                    Tecnologia tecnologia = new Tecnologia(contTecnologiaServicios);
                    //2024-06-24 jyperez ahora en la lista almacenamos la tecnología escogida. En los campos de servicioParaCrear no se almacenará ninguno.
                    tecnologia.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_TECNOLOGIA)));
                    tecnologia.setMaTecnologiaId(getTecnologiasBean().getObjeto().getId());
                    tecnologia.setMaTecnologiaCodigo(getTecnologiasBean().getObjeto().getCups());
                    tecnologia.setMaTecnologiaValor(getTecnologiasBean().getObjeto().getCupsDescipcion());
                    getListaTecnologiaServicios().add(tecnologia);
                    PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
                    PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
                    //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
                    PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
                    PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
                    PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
                    generarMensajes();
                }
            } else {
                this.addError("La cantidad máxima de Tecnologías a agregar es " + LIMITE_CANTIDAD_TECNOLOGIAS_SERVICIOS);
                PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
                PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
                //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
                PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
                PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
                PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
                generarMensajes();
            }
        } else {
            MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
            getServicioParaCrear().setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_TECNOLOGIA)));
            getServicioParaCrear().setMaTecnologiaId(tecnologia.getId());
            getServicioParaCrear().setMaTecnologiaCodigo(tecnologia.getCups());
            getServicioParaCrear().setMaTecnologiaValor(tecnologia.getCupsDescipcion());
            PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
            PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
            //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
            PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
            generarMensajes();
        }
    }
    
    public void mostrarMensajeServicioDuplicado(int TipoTecnologia) {
        this.tipoTecnologiaServicioDuplicado = TipoTecnologia;
        this.registrosServiciosDuplicados = new ArrayList();
        for (AusCaso caso: this.listaCasosSimilares) {
            registrosServiciosDuplicados.add("Número Solicitud " + caso.getId() + " | " );
        }
        PrimeFaces.current().executeScript("PF('dialogoServicioDuplicadoTecnologia').show()");
        PrimeFaces.current().ajax().update("frmServicioDuplicado");
    }
    
    public void validarAccion() {
        contTecnologiaServicios++;
        Tecnologia tecnologia = new Tecnologia(contTecnologiaServicios);
        switch(this.tipoTecnologiaServicioDuplicado) {
            case Tecnologia.TIPO_TECNOLOGIA_TECNOLOGIA:
                //contTecnologiaServicios++;
                //Tecnologia tecnologia = new Tecnologia(contTecnologiaServicios);
                //2024-06-24 jyperez ahora en la lista almacenamos la tecnología escogida. En los campos de servicioParaCrear no se almacenará ninguno.
                tecnologia.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_TECNOLOGIA)));
                tecnologia.setMaTecnologiaId(getTecnologiasBean().getObjeto().getId());
                tecnologia.setMaTecnologiaCodigo(getTecnologiasBean().getObjeto().getCups());
                tecnologia.setMaTecnologiaValor(getTecnologiasBean().getObjeto().getCupsDescipcion());
                getListaTecnologiaServicios().add(tecnologia);
                PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
            break;
            case Tecnologia.TIPO_TECNOLOGIA_MEDICAMENTO:
                //contTecnologiaServicios++;
                //Tecnologia tecnologia = new Tecnologia(contTecnologiaServicios);
                //2024-06-24 jyperez ahora en la lista almacenamos la tecnología escogida. En los campos de servicioParaCrear no se almacenará ninguno.
                tecnologia.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_MEDICAMENTO)));
                tecnologia.setMaTecnologiaId(getMedicamentosBean().getObjeto().getId());
                tecnologia.setMaTecnologiaCodigo(getMedicamentosBean().getObjeto().getCum());
                tecnologia.setMaTecnologiaValor(getMedicamentosBean().getObjeto().getDescripcionInvima());
                getListaTecnologiaServicios().add(tecnologia);
                PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
            break;
            case Tecnologia.TIPO_TECNOLOGIA_INSUMO:
                //contTecnologiaServicios++;
                //2024-06-24 jyperez ahora en la lista almacenamos la tecnología escogida. En los campos de servicioParaCrear no se almacenará ninguno.
                tecnologia.setTipoTecnologia(Short.parseShort(String.valueOf(AuConstantes.ID_INSUMO)));
                tecnologia.setMaTecnologiaId(getInsumosBean().getObjeto().getId());
                tecnologia.setMaTecnologiaCodigo(getInsumosBean().getObjeto().getCodigo());
                tecnologia.setMaTecnologiaValor(getInsumosBean().getObjeto().getDescripcion());
                getListaTecnologiaServicios().add(tecnologia);
                PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
                
            break;
        }
        //pasamnos actividades similares en las pantallas al final de esta ejecución
        PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
        PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
        //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
        PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
        PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
        PrimeFaces.current().executeScript("PF('dialogoServicioDuplicadoTecnologia').hide()");
        generarMensajes();
    }
    
    public void cancelarAccion() {
        switch(this.tipoTecnologiaServicioDuplicado) {
            case Tecnologia.TIPO_TECNOLOGIA_TECNOLOGIA:
                PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
            break;
            case Tecnologia.TIPO_TECNOLOGIA_MEDICAMENTO:
                PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
            break;
            case Tecnologia.TIPO_TECNOLOGIA_INSUMO:
                PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
            break;
        }
        PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
        PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
        //2024-06-07 jyperez adicionamos update pantalla de servicio gestión
        PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
        PrimeFaces.current().ajax().update("frmDuplicarServicio:pDuplicarServicio");
        PrimeFaces.current().executeScript("PF('dialogoServicioDuplicadoTecnologia').hide()");
        generarMensajes();
    }
    
    public void aceptarServicioTecnologiaExistente () {
        
    }
    
    public void borrarTecnologiaServicio(int id) {
        List<Tecnologia> lista = new ArrayList();
        int i=0;
        try {
            for (Tecnologia tec : this.getListaTecnologiaServicios()) {
                if (tec.getId() != id) {
                    i++;
                    tec.setId(i);
                    lista.add(tec);
                }
            }
            //actualizamos el valor de los servicios en la lista
            this.contTecnologiaServicios = i;
            this.setListaTecnologiaServicios(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar la Tecnología seleccionada");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrearServicio:pCrearServicioTecnologias");
        }
    }

    public void onRowSelectIps(SelectEvent event) {
        try {
            //getServicioParaCrear().setObjetoPrestadorSede((CntPrestadorSede) event.getObject());
            setObjetoPrestadorSede((CntPrestadorSede) event.getObject());
            getServicioParaCrear().setObjetoPrestadorSede(getObjetoPrestadorSede());
            getServicioParaCrear().setObjetoPrestadorSedeValor(getObjetoPrestadorSede().getNombreSede());
            setObjetoPrestador(getObjetoPrestadorSede().getCntPrestador());
            PrimeFaces.current().ajax().update("frmCrearServicio:prestador");
            PrimeFaces.current().ajax().update("frmCrearServicio:sedeServicio");
            PrimeFaces.current().ajax().update("frmEditarServicio:prestador");
            PrimeFaces.current().ajax().update("frmEditarServicio:sedeServicio");
            PrimeFaces.current().ajax().update("frmDuplicarServicio:prestador");
            PrimeFaces.current().ajax().update("frmDuplicarServicio:sedeServicio");
            //2024-06-07 jyperez se adiciona el llamada a la pantalla editar servicio gestion
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:prestador");
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:sedeServicio");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
            //PrimeFaces.current().executeScript("PF('tablaWidgetIPS').clearFilters()");
            //PrimeFaces.current().executeScript("PF('tablaWidgetIPS').unselectAllRows()");
            //PrimeFaces.current().resetInputs("frmPrestadorLista:tablaRegistrosIps");
            //PrimeFaces.current().resetInputs("frmPrestadorLista:panelIpsLista");
            //PrimeFaces.current().ajax().update("frmPrestadorLista:tablaRegistrosIps");
            //PrimeFaces.current().ajax().update("frmPrestadorLista");
            //PrimeFaces.current().ajax().update("frmPrestadorLista:panelIpsLista");
        } catch (Exception ex) {
            addError("Ocurrio un error al seleccionar la IPS: " + ex.toString());
        }
    }

    public void onRowSelectIpsDestino(SelectEvent event) {
        try {
            //getServicioParaCrear().setObjetoPrestadorSede((CntPrestadorSede) event.getObject());
            setObjetoPrestadorIpsDestino((CntPrestadorSede) event.getObject());
            getServicioParaCrear().setObjetoPrestadorIps(getObjetoPrestadorIpsDestino());
            getServicioParaCrear().setObjetoPrestadorIpsValor(getObjetoPrestadorIpsDestino().getNombreSede());
            setObjetoPrestadorIps(getObjetoPrestadorIpsDestino().getCntPrestador());
            PrimeFaces.current().ajax().update("frmCrearServicio:prestadorDestino");
            PrimeFaces.current().ajax().update("frmCrearServicio:sedeServicioDestino");
            PrimeFaces.current().ajax().update("frmEditarServicio:prestadorDestino");
            PrimeFaces.current().ajax().update("frmEditarServicio:sedeServicioDestino");
            PrimeFaces.current().ajax().update("frmDuplicarServicio:prestadorDestino");
            PrimeFaces.current().ajax().update("frmDuplicarServicio:sedeServicioDestino");
            //2024-06-07 jyperez se adiciona el llamada a la pantalla editar servicio gestion
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:prestadorDestino");
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:sedeServicioDestino");
            PrimeFaces.current().executeScript("PF('dialogoIpsDestino').hide()");
            //PrimeFaces.current().executeScript("PF('tablaWidgetIPS').clearFilters()");
            //PrimeFaces.current().executeScript("PF('tablaWidgetIPS').unselectAllRows()");
            //PrimeFaces.current().resetInputs("frmPrestadorLista:tablaRegistrosIps");
            //PrimeFaces.current().resetInputs("frmPrestadorLista:panelIpsLista");
            //PrimeFaces.current().ajax().update("frmPrestadorLista:tablaRegistrosIps");
            //PrimeFaces.current().ajax().update("frmPrestadorLista");
            //PrimeFaces.current().ajax().update("frmPrestadorLista:panelIpsLista");
        } catch (Exception ex) {
            addError("Ocurrio un error al seleccionar la IPS: " + ex.toString());
        }
    }

    public void getAuditoria(String str) {
        if (str != null && !str.equals("")) {
            String[] strs = str.split("&&");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CREACIÓN", strs[0]));
            if (strs.length > 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ÚLTIMA EDICIÓN", strs[1]));
            }
        }
    }

    public boolean desabilitarGestion(int id) {
        int idCasoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO));
        if (id == idCasoCerrado) {
            setOcultarGestion(false);
        } else {
            setOcultarGestion(true);
        }
        return getOcultarGestion();
    }

    public boolean desabilitarRevertirCaso(int id) {
        int idCasoCerrado = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO));
        boolean validar = false;
        if (id == idCasoCerrado) {
            validar = true;
        }
        return validar;
    }

    public void limpiarCamposProcedimiento() {
        if (!getServicioParaCrear().isHabiltarCampoProcedimiento()) {
            getServicioParaCrear().setTipoTecnologia(null);
            getServicioParaCrear().setMaTecnologiaId(null);
            getServicioParaCrear().setMaTecnologiaCodigo(null);
            getServicioParaCrear().setMaTecnologiaValor(null);
        }
    }

    public boolean desabilidarCamposProcedimiento() {
        boolean valida = true;
        if (getServicioParaCrear().isHabiltarCampoProcedimiento()) {
            valida = false;
        }
        return valida;
    }

    public void desabilidarCamposAplicaMedicamentoCobertura() {
        if (getServicioParaCrear().getMedicamento() != null) {
            if (getServicioParaCrear().getMedicamento()) {
                getServicioParaCrear().setHabilitarMedicamentoCobertura(false);

            } else {
                getServicioParaCrear().setMedicamentoCobertura(null);
                getServicioParaCrear().setHabilitarMedicamentoCobertura(true);
            }
        } else {
            getServicioParaCrear().setMedicamentoCobertura(null);
            getServicioParaCrear().setHabilitarMedicamentoCobertura(true);
        }
    }

    public void habilitarComentario() {
        Integer idMotivoReabrirCaso = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.AUS_CASO_RE_ABRIR_CASO));
        setHabilidarCampoComentarioRevertirCaso((getObjRevertirCaso().getMaeMotivoReabreId().equals(idMotivoReabrirCaso)) ? 1 : 0);
    }

    public void consultarMaestroTipoMotivo() {
        Integer idmaeMotivoEspecificoId = getObjeto().getMaeMotivoEspecificoId();
       
        if (idmaeMotivoEspecificoId != null) {
            getAusCasoServicio().maestroTipoMotivo(this);
        } else {
            getObjeto().setMaeMotivoEspecificoId(null);
        }
        debahilitarSubtipoMotivo();
    }
    public void debahilitarSubtipoMotivo(){
        Integer idCasoMotivo = Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.AUS_CASO_MOTIVO));
        if (getObjeto().getMaeMotivoEspecificoId() != null) {
            if (getObjeto().getMaeMotivoEspecificoId().equals(idCasoMotivo)) {
                getObjeto().setHabilitarCasoMotivo(true);
            } else {
                getObjeto().setHabilitarCasoMotivo(false);
            }
        }
    }
    public void consultarMaestroSubTipoMotivo() {
        Integer idmaeTipoMotivoEspecificoId = getObjeto().getMaeTipoMotivoEspecificoId();
        if (idmaeTipoMotivoEspecificoId != null) {
            getAusCasoServicio().maestroSubTipoMotivo(this);
        } else {
            getObjeto().setMaeSubtipoMotivoEspecificoId(null);
            setListaSubMotivo(new ArrayList<>());
            setHashSubMotivo(new HashMap<>());
        }
    }
    
    public boolean habilitarBotonBorrarServicio(Integer idServicio){
        boolean validar = false;
        if (idServicio != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO))
                        && idServicio != Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_RESUELTO))){
            validar = true;
        }
        return validar;
    }
    
    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            //crearLog(descripcion);
            switch (super.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog(getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmCasos");
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_EDITAR:
                           crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_BUSCAR_SEDES:
                            crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_BUSCAR_PERSONAS:
                            crearLog(getObjeto().toString());
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_VER:
                            crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_VER_SERVICIO:
                            crearLog(getServicioParaCrear().toString());
                            break;
                        case AusCasoBean.ACCION_VER_SEGUIMIENTO:
                            crearLog(getSeguimientoProcesar().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_VER_SERVICIO:
                            crearLog(getServicioParaCrear().toString());
                            break;
                        case AusCasoBean.ACCION_DUPLICAR_SERVICIO:
                            crearLog(getServicioParaCrear().toString());
                            break;
                        case AusCasoBean.ACCION_EDITAR_SEGUIMIENTO:
                            crearLog(getSeguimientoProcesar().toString());
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_SERVICIO:
                            crearLog(getServicioParaCrear().toString());
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_SEGUIMIENTO:
                            crearLog(getSeguimientoProcesar().toString());
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                           crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_BORRAR_SERVICIO:
                            crearLog(getServicioParaCrear().toString());
                            break;
                        
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Gestionar", getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_CERRAR_CASO_AUTOMATICO:
                            crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_DUPLICAR_SERVICIO:
                            crearLog(getServicioParaCrear().toString());
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_GESTION:
                            crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_MODIFICAR_SERVICIO:
                           crearLog(getServicioParaCrear().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_4:
                            crearLog(getObjeto().toString());
                            break;
                        case AusCasoBean.ACCION_REVERTIR_CASO:
                           crearLog(getObjeto().toString());
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (super.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog(getObjeto().toString());
                            break;

                    }
                    break;
                default:
                    break;
            }
        }
        generarMensajes();
    }

    /**
     * @return the listaTecnologiaServicios
     */
    public List<Tecnologia> getListaTecnologiaServicios() {
        return listaTecnologiaServicios;
    }

    /**
     * @param listaTecnologiaServicios the listaTecnologiaServicios to set
     */
    public void setListaTecnologiaServicios(List<Tecnologia> listaTecnologiaServicios) {
        this.listaTecnologiaServicios = listaTecnologiaServicios;
    }

    /**
     * @return the listaCasosSimilares
     */
    public List<AusCaso> getListaCasosSimilares() {
        return listaCasosSimilares;
    }

    /**
     * @param listaCasosSimilares the listaCasosSimilares to set
     */
    public void setListaCasosSimilares(List<AusCaso> listaCasosSimilares) {
        this.listaCasosSimilares = listaCasosSimilares;
    }

    /**
     * @return the tipoTecnologiaServicioDuplicado
     */
    public int getTipoTecnologiaServicioDuplicado() {
        return tipoTecnologiaServicioDuplicado;
    }

    /**
     * @param tipoTecnologiaServicioDuplicado the tipoTecnologiaServicioDuplicado to set
     */
    public void setTipoTecnologiaServicioDuplicado(int tipoTecnologiaServicioDuplicado) {
        this.tipoTecnologiaServicioDuplicado = tipoTecnologiaServicioDuplicado;
    }

    /**
     * @return the registrosServiciosDuplicados
     */
    public List<String> getRegistrosServiciosDuplicados() {
        return registrosServiciosDuplicados;
    }

    /**
     * @param registrosServiciosDuplicados the registrosServiciosDuplicados to set
     */
    public void setRegistrosServiciosDuplicados(List<String> registrosServiciosDuplicados) {
        this.registrosServiciosDuplicados = registrosServiciosDuplicados;
    }

    /**
     * @return the servicioParaConsultar
     */
    public AusCasoServicio getServicioParaConsultar() {
        return servicioParaConsultar;
    }

    /**
     * @param servicioParaConsultar the servicioParaConsultar to set
     */
    public void setServicioParaConsultar(AusCasoServicio servicioParaConsultar) {
        this.servicioParaConsultar = servicioParaConsultar;
    }

    /**
     * @return the idEstadoServicioAnterior
     */
    public int getIdEstadoServicioAnterior() {
        return idEstadoServicioAnterior;
    }

    /**
     * @param idEstadoServicioAnterior the idEstadoServicioAnterior to set
     */
    public void setIdEstadoServicioAnterior(int idEstadoServicioAnterior) {
        this.idEstadoServicioAnterior = idEstadoServicioAnterior;
    }
}
