package com.saviasaludeps.savia.web.crue.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import static com.saviasaludeps.savia.dominio.crue.RefAnexo9.SEMAFORO_HORA_CAMBIO;
import static com.saviasaludeps.savia.dominio.crue.RefAnexo9.SEMAFORO_HORA_MANANA;
import static com.saviasaludeps.savia.dominio.crue.RefAnexo9.SEMAFORO_HORA_TARDE;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Adjunto;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9DatoClinico;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Diagnostico;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Estado;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Gestion;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Semaforo;
import com.saviasaludeps.savia.dominio.crue.RefTransporte;
import com.saviasaludeps.savia.dominio.crue.RefTransporteSeguimiento;
import com.saviasaludeps.savia.dominio.crue.ReporteReferencia;
import com.saviasaludeps.savia.dominio.crue.ReporteReferenciaContraReferencia;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaRelacion;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorSedesBean;
import com.saviasaludeps.savia.web.crue.bean.DTO.RefAnexo9TriageDTO;
import com.saviasaludeps.savia.web.crue.servicio.ReferenciaContraRefIface;
import com.saviasaludeps.savia.web.crue.utilidades.CrueConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.file.UploadedFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jaime Andres Olarte
 */
@Named
@ViewScoped
public class ReferenciaContraRefBean extends Url {

    @Autowired
    private ReferenciaContraRefIface referenciaContraServicio;
    private RefAnexo9 objeto;
    private List<RefAnexo9> registros;
    private List<RefAnexo9> listaAnexos9Activos;
    private LazyDataModel<RefAnexo9> lazyRegistros;
    private RefAnexo9Adjunto objetoAdjunto;
    private RefAnexo9DatoClinico objetoDatosClinicos;
    private RefAnexo9Gestion objetoGestion;
    private RefTransporte objetoTransporte;
    private RefAnexo9Diagnostico objetoDiagnosticoBorrar;
    private CntProfesionalPrestador objetoProfesionalPrestador;
    private RefTransporteSeguimiento objetoTransporteSeguimiento;
    private SelDiagnosticosBean selDiagnosticosBean;
    private List<AsegAfiliado> registrosAfiliados;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
    private List<CntPrestadorSede> registroIPS;
    private LazyDataModel<CntPrestadorSede> lazyIPS;
    private List<RefAnexo9Gestion> registrosRefAnexos9Gestion;
    private LazyDataModel<RefAnexo9Gestion> lazyGestion;
    private List<RefTransporte> registrosRefTransportes;
    private LazyDataModel<RefTransporte> lazyTransporte;
    private Date fechaActual;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaTiposDocumento;
    private List<Maestro> listaTiposDocumentoPersona;
    private List<Maestro> listaTiposDocumentoEmpresa;
    private HashMap<Integer, Maestro> hashTiposDocumentoProfesional;
    private List<Maestro> listaTiposDocumentoProfesional;
    private List<Maestro> listaTiposGenero;
    private HashMap<Integer, Ubicacion> hashUbicacion;
    private List<Ubicacion> listaUbicacion;
    private List<MaServicioHabilitacion> listaMaServicioHabilitacion;
    private List<Maestro> listaTipoGestion;
    private HashMap<Integer, Maestro> hashTipoGestion;
    private List<Maestro> listaMotivoGestion;
    private List<Maestro> listaMotivoGestionFiltro;
    private List<Maestro> listaTipoTransporte;
    private HashMap<Integer, Maestro> hashTipoTransporte;
    private List<Maestro> listaTransporteLiquidacion;
    private HashMap<Integer, Maestro> hashTransporteLiquidacion;
    private List<Maestro> listaClaseTransporte;
    private List<Maestro> listaClaseTransporteFiltro;
    private List<Maestro> listaInsumoTransporte;
    private HashMap<Integer, Maestro> hashInsumoTransporte;
    private List<Maestro> listaTipoAdjunto;
    private HashMap<Integer, Maestro> hashTipoAdjunto;
    private List<Maestro> listaCausaExterna;
    private HashMap<Integer, Maestro> hashCausaExterna;
    private List<Maestro> listaCondicionDestino;
    private HashMap<Integer, Maestro> hashCondicionDestino;
    private List<Maestro> listaTipoAtencion;
    private HashMap<Integer, Maestro> hashTipoAtencion;
    private List<Maestro> listaModalidadTecnologia;
    private HashMap<Integer, Maestro> hashModalidadTecnologia;
    private List<Maestro> listaTecnologia;
    private HashMap<Integer, Maestro> hashTecnologia;
    private List<Maestro> listaCanalComunicacion;
    private HashMap<Integer, Maestro> hashCanalComunicacion;
    private List<Maestro> listaGrupoServicio;
    private HashMap<Integer, Maestro> hashGrupoServicio;
    private List<Maestro> listaTipoAislamiento;
    private HashMap<Integer, Maestro> hashTipoAislamiento;
    private List<PeAfiliadosPrograma> listaAfiliadoPrograma;
    private List<Maestro> listaMaternoPerinata;
    private HashMap<Integer, Maestro> hashMaternoPerinata;
     
    private UploadedFile archivoAdjunto;
    private SelEspecialidadesBean selEspecialidadesBean;
    private String dialogo;
    private String colorEstadoAfiliado;
    private List<RefAnexo9Semaforo> listaRefAnexo9Semaforo;
    private SelPrestadorSedesBean sedesBean;
    private Integer[] insumosSeleccionados;
    private List<Maestro> listaTipoSeguimiento;
    private HashMap<Integer, Maestro> hashTipoSeguimiento;
    private List<RefAnexo9Adjunto> listaRefAnexo9Adjuntos;
    private List<RefAnexo9Diagnostico> diagnosticosBorrar;
    private Ubicacion objetoUbicacionAcomp;
    private String nombreDocumento;
    private boolean mostrarBorrarDocumento;
    private String insumoSeleccionadoTexto;
    private SelServiciosHabilitacionBean selServiciosHabilitacionBean;
    private SelTecnologiasBean tecnologiasBean;
    private int servicioHabilitacion;
    private List<RefAnexo9TriageDTO> listaTriage;
    private RefAnexo9TriageDTO objectoTriage;
    private Maestro maeClaseTransporte;
    private HashMap<Integer, Maestro> hashEstadoSolicitud;
    private List<Maestro> listaEstadoSolicitud;
    private boolean deshabilitadoCanal;
    private ReporteReferencia reporte;
    private List<ReporteReferencia> listaReporte;
    private boolean inhabilitarMotivo;
    private boolean gestionar;
    private boolean isActivoAfiliado;
    private boolean existeDatoClinico;
    private List<Maestro> listaEstadosAfiliado;
    private HashMap<Integer, Maestro> hashEstadosAfiliado;
    private List<Maestro> listaRegimen;
    private HashMap<Integer, Maestro> hashRegimen;
    private String direccionAlternativa;
    private String nombreContactoEmergencia;
    private String telefonoContactoEmergencia;

    private String telefonoFijoAfiliado;
    private String telefonoMovilAfiliado;
    private String observacionGenerica;

    private ReporteReferenciaContraReferencia reporteReferenciaContraReferencia;
    private List<ReporteReferenciaContraReferencia> listaReporteReferenciaContraReferencia;

    public final static SimpleDateFormat FORMATO_REPORTE = new SimpleDateFormat("YYYYMMddHHmmss");
    public static final int MAXIMO_DIAGNOSTICOS = 3;
    public static final int ID_GNEMPRESACRUE = 1;
    public static final String GESTION_ESTADO_CERRADA = "13";
    public static final String ESTADO_CERRADA = "13";
    public static final Integer CANAL_INTEROPERABILIDAD = 9804;
    public static final Integer CANAL_IPS = 9807;
    public static final String DIALOGO_CREAR = "Crear";
    public static final String DIALOGO_EDITAR = "Editar";
    public static final String DIALOGO_GESTIONAR = "Gestionar";
    private static final int SERVICIO_HABILITACION_SOLICITA = 1;
    private static final int SERVICIO_HABILITACION_REMITE = 2;
    public static final int MOTIVO_GESTION_REGULADO = 2790;
    public static final String CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO = "EPSS40";
    public static final String CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO = "EPS040";
    public static final int ID_TIPO_DOCUMENTO_ACOMP_CC = 4;
    public static final String PATTERN = "^[a-zA-Z0-9\\s~ñÑ.;:_'°º/¿?!¡@#$%^*()-=+|{}áéíóúüÁÉÍÓÚàèìòùÀÈÌÒÙ`´‘’“”>\\[\\]\\x22\\\\²³]*$";
    public static final int CODIGO_SERVICIOS_HOBILITACION = 710;
    public static final String CODIGO_SERVICIOS_GINECOBOSTETRICIA = "320";
    //2021-04-06 Se instancia singleton con anotación spring
    public static final char ACCION_BORRAR_CONTACTOS_PERSONAS = 'a';
    //variables auxiliares
    private List<Maestro> listaGestionarMotivos;
    private Date fechaMaximaCalendario;
    private Date fechaInicio;
    private Date fechaFin;
    
    //motivos
    private static final int REMITIDO = 10281;

    public ReferenciaContraRefBean() {
        this.objeto = new RefAnexo9();
        this.objetoAdjunto = new RefAnexo9Adjunto();
        fechaActual = new Date();
        fechaMaximaCalendario = fechaActual;
        this.deshabilitadoCanal = true;
        Modulo mod = super.validarModulo(Modulo.ID_REFERENCIA_CONTRARREFERENCIA);
        super.addListaParamConsultas(new ParamConsulta());// ya no se usa
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());//4 ya no se usa
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("Modulo", mod.getId());
            super.setModulo(mod);
            lazyRegistros = new LazyDataModel<RefAnexo9>() {
                private List<RefAnexo9> listaRefAnexo9;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<RefAnexo9> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaRefAnexo9 = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaRefAnexo9;
                }

                @Override
                public String getRowKey(RefAnexo9 refAnexo9) {
                    return refAnexo9.getId().toString();
                }

                @Override
                public RefAnexo9 getRowData(String refAnexo9Id) {
                    Integer id = Integer.valueOf(refAnexo9Id);
                    for (RefAnexo9 refAnexo9 : listaRefAnexo9) {
                        if (id.equals(refAnexo9.getId())) {
                            return refAnexo9;
                        }
                    }
                    return null;
                }
            };

            //Afiliados
            this.getParamConsulta(1).setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyAfiliados = new LazyDataModel<AsegAfiliado>() {

                private List<AsegAfiliado> afiliados;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(1).getCantidadRegistros();
                }

                @Override
                public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarAfiliado();
                    afiliados = getRegistrosAfiliados();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
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

            //IPS
            this.getParamConsulta(2).setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyIPS = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaIPS;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(2).getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(2).setPrimerRegistro(primerRegistro);
                    getParamConsulta(2).setRegistrosPagina(registrosPagina);
                    getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarIps();
                    listaIPS = getRegistroIPS();
                    setRowCount(getParamConsulta(2).getCantidadRegistros());
                    return listaIPS;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntPrestadorSede ips : listaIPS) {
                        if (id.equals(ips.getId())) {
                            return ips;
                        }
                    }
                    return null;
                }
            };

            //Gestion
            lazyGestion = new LazyDataModel<RefAnexo9Gestion>() {
                private List<RefAnexo9Gestion> gestiones;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(3).getCantidadRegistros();
                }

                @Override
                public List<RefAnexo9Gestion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(3).setPrimerRegistro(primerRegistro);
                    getParamConsulta(3).setRegistrosPagina(registrosPagina);
                    getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarGestiones();
                    gestiones = getRegistrosRefAnexos9Gestion();
                    setRowCount(getParamConsulta(3).getCantidadRegistros());
                    return gestiones;
                }

                @Override
                public String getRowKey(RefAnexo9Gestion gestion) {
                    return gestion.getId().toString();
                }

                @Override
                public RefAnexo9Gestion getRowData(String gestionId) {
                    Integer id = Integer.valueOf(gestionId);
                    for (RefAnexo9Gestion gestion : gestiones) {
                        if (id.equals(gestion.getId())) {
                            return gestion;
                        }
                    }
                    return null;
                }
            };

            //Transporte
            lazyTransporte = new LazyDataModel<>() {

                private List<RefTransporte> transportes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(5).getCantidadRegistros();
                }

                @Override
                public List<RefTransporte> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(5).setPrimerRegistro(primerRegistro);
                    getParamConsulta(5).setRegistrosPagina(registrosPagina);
                    getParamConsulta(5).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(5).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrecarTransporte();
                    transportes = getRegistrosRefTransportes();
                    setRowCount(getParamConsulta(5).getCantidadRegistros());
                    return transportes;
                }

                @Override
                public String getRowKey(RefTransporte transporte) {
                    return transporte.getId().toString();
                }

                @Override
                public RefTransporte getRowData(String transporteId) {
                    Integer id = Integer.valueOf(transporteId);
                    for (RefTransporte transporte : transportes) {
                        if (id == transporte.getId().intValue()) {
                            return transporte;
                        }
                    }
                    return null;
                }
            };

            if (!getErrores().isEmpty()) {
                generarMensajes();
            }
        }
    }

    @PostConstruct
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getReferenciaContraServicio().cargaInicial(this);
        List<Maestro> listaCanalTemp = new ArrayList<>();
        for (Maestro maestro : listaCanalComunicacion) {
            if (!maestro.getId().equals(CANAL_INTEROPERABILIDAD)) {
                if (super.getConexion().getEmpresa().isAdministradora()) {
                    if (!maestro.getId().equals(CANAL_IPS)) {
                        listaCanalTemp.add(maestro);
                    }
                } else {
                    listaCanalTemp.add(maestro);
                }

            }
        }
        listaCanalComunicacion.clear();
        listaCanalComunicacion.addAll(listaCanalTemp);
        listaCanalTemp = null;
        listar();
    }

    public boolean isExisteDatoClinico() {
        return existeDatoClinico;
    }

    public void setExisteDatoClinico(boolean existeDatoClinico) {
        this.existeDatoClinico = existeDatoClinico;
    }

    public RefAnexo9 getObjeto() {
        return objeto;
    }

    public void setObjeto(RefAnexo9 objeto) {
        this.objeto = objeto;
    }

    public List<RefAnexo9> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RefAnexo9> registros) {
        this.registros = registros;
    }

    public LazyDataModel<RefAnexo9> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<RefAnexo9> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
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

    public List<Maestro> getListaCondicionDestino() {
        return listaCondicionDestino;
    }

    public void setListaCondicionDestino(List<Maestro> listaCondicionDestino) {
        this.listaCondicionDestino = listaCondicionDestino;
    }

    public String getDireccionAlternativa() {
        return direccionAlternativa;
    }

    public void setDireccionAlternativa(String direccionAlternativa) {
        this.direccionAlternativa = direccionAlternativa;
    }

    public String getNombreContactoEmergencia() {
        return nombreContactoEmergencia;
    }

    public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
        this.nombreContactoEmergencia = nombreContactoEmergencia;
    }

    public String getTelefonoContactoEmergencia() {
        return telefonoContactoEmergencia;
    }

    public void setTelefonoContactoEmergencia(String telefonoContactoEmergencia) {
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
    }

    public HashMap<Integer, Maestro> getHashCondicionDestino() {
        return hashCondicionDestino;
    }

    public void setHashCondicionDestino(HashMap<Integer, Maestro> hashCondicionDestino) {
        this.hashCondicionDestino = hashCondicionDestino;
    }

    public List<Maestro> getListaTipoAtencion() {
        return listaTipoAtencion;
    }

    public void setListaTipoAtencion(List<Maestro> listaTipoAtencion) {
        this.listaTipoAtencion = listaTipoAtencion;
    }

    public HashMap<Integer, Maestro> getHashTipoAtencion() {
        return hashTipoAtencion;
    }

    public void setHashTipoAtencion(HashMap<Integer, Maestro> hashTipoAtencion) {
        this.hashTipoAtencion = hashTipoAtencion;
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

    public List<Maestro> getListaTecnologia() {
        return listaTecnologia;
    }

    public void setListaTecnologia(List<Maestro> listaTecnologia) {
        this.listaTecnologia = listaTecnologia;
    }

    public HashMap<Integer, Maestro> getHashTecnologia() {
        return hashTecnologia;
    }

    public void setHashTecnologia(HashMap<Integer, Maestro> hashTecnologia) {
        this.hashTecnologia = hashTecnologia;
    }

    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
    }

    public HashMap<Integer, Ubicacion> getHashUbicacion() {
        return hashUbicacion;
    }

    public void setHashUbicacion(HashMap<Integer, Ubicacion> hashUbicacion) {
        this.hashUbicacion = hashUbicacion;
    }

    public List<Ubicacion> getListaUbicacion() {
        return listaUbicacion;
    }

    public ReferenciaContraRefIface getReferenciaContraServicio() {
        return referenciaContraServicio;
    }

    public void setReferenciaContraServicio(ReferenciaContraRefIface referenciaContraServicio) {
        this.referenciaContraServicio = referenciaContraServicio;
    }

    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<AsegAfiliado> getRegistrosAfiliados() {
        return registrosAfiliados;
    }

    public void setRegistrosAfiliados(List<AsegAfiliado> registrosAfiliados) {
        this.registrosAfiliados = registrosAfiliados;
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliados() {
        return lazyAfiliados;
    }

    public void setLazyAfiliados(LazyDataModel<AsegAfiliado> lazyAfiliados) {
        this.lazyAfiliados = lazyAfiliados;
    }

    public List<CntPrestadorSede> getRegistroIPS() {
        return registroIPS;
    }

    public void setRegistroIPS(List<CntPrestadorSede> registroIPS) {
        this.registroIPS = registroIPS;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIPS() {
        return lazyIPS;
    }

    public void setLazyIPS(LazyDataModel<CntPrestadorSede> lazyIPS) {
        this.lazyIPS = lazyIPS;
    }

    public SelDiagnosticosBean getSelDiagnosticosBean() {
        selDiagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return selDiagnosticosBean;
    }

    public void setSelDiagnosticosBean(SelDiagnosticosBean selDiagnosticosBean) {
        this.selDiagnosticosBean = selDiagnosticosBean;
    }

    public List<MaServicioHabilitacion> getListaMaServicioHabilitacion() {
        return listaMaServicioHabilitacion;
    }

    public void setListaMaServicioHabilitacion(List<MaServicioHabilitacion> listaMaServicioHabilitacion) {
        this.listaMaServicioHabilitacion = listaMaServicioHabilitacion;
    }

    public List<Maestro> getListaTipoAdjunto() {
        return listaTipoAdjunto;
    }

    public void setListaTipoAdjunto(List<Maestro> listaTipoAdjunto) {
        this.listaTipoAdjunto = listaTipoAdjunto;
    }

    public HashMap<Integer, Maestro> getHashTipoAdjunto() {
        return hashTipoAdjunto;
    }

    public void setHashTipoAdjunto(HashMap<Integer, Maestro> hashTipoAdjunto) {
        this.hashTipoAdjunto = hashTipoAdjunto;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public RefAnexo9Adjunto getObjetoAdjunto() {
        return objetoAdjunto;
    }

    public void setObjetoAdjunto(RefAnexo9Adjunto objetoAdjunto) {
        this.objetoAdjunto = objetoAdjunto;
    }

    public RefAnexo9DatoClinico getObjetoDatosClinicos() {
        return objetoDatosClinicos;
    }

    public void setObjetoDatosClinicos(RefAnexo9DatoClinico objetoDatosClinicos) {
        this.objetoDatosClinicos = objetoDatosClinicos;
    }

    public SelEspecialidadesBean getEspecialidadesBean() {
        selEspecialidadesBean = (SelEspecialidadesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selEspecialidadesBean");
        return selEspecialidadesBean;
    }

    public void setEspecialidadesBean(SelEspecialidadesBean selEspecialidadesBean) {
        this.selEspecialidadesBean = selEspecialidadesBean;
    }

    public SelEspecialidadesBean getSelEspecialidadesBean() {
        return selEspecialidadesBean;
    }

    public void setSelEspecialidadesBean(SelEspecialidadesBean selEspecialidadesBean) {
        this.selEspecialidadesBean = selEspecialidadesBean;
    }

    public String getColorEstadoAfiliado() {
        return colorEstadoAfiliado;
    }

    public void setColorEstadoAfiliado(String colorEstadoAfiliado) {
        this.colorEstadoAfiliado = colorEstadoAfiliado;
    }

    public List<Maestro> getListaTipoGestion() {
        return listaTipoGestion;
    }

    public void setListaTipoGestion(List<Maestro> listaTipoGestion) {
        this.listaTipoGestion = listaTipoGestion;
    }

    public HashMap<Integer, Maestro> getHashTipoGestion() {
        return hashTipoGestion;
    }

    public void setHashTipoGestion(HashMap<Integer, Maestro> hashTipoGestion) {
        this.hashTipoGestion = hashTipoGestion;
    }

    public List<Maestro> getListaMotivoGestion() {
        return listaMotivoGestion;
    }

    public void setListaMotivoGestion(List<Maestro> listaMotivoGestion) {
        this.listaMotivoGestion = listaMotivoGestion;
    }

    public List<Maestro> getListaMotivoGestionFiltro() {
        return listaMotivoGestionFiltro;
    }

    public void setListaMotivoGestionFiltro(List<Maestro> listaMotivoGestionFiltro) {
        this.listaMotivoGestionFiltro = listaMotivoGestionFiltro;
    }

    public List<RefAnexo9Gestion> getRegistrosRefAnexos9Gestion() {
        return registrosRefAnexos9Gestion;
    }

    public void setRegistrosRefAnexos9Gestion(List<RefAnexo9Gestion> registrosRefAnexos9Gestion) {
        this.registrosRefAnexos9Gestion = registrosRefAnexos9Gestion;
    }

    public LazyDataModel<RefAnexo9Gestion> getLazyGestion() {
        return lazyGestion;
    }

    public void setLazyGestion(LazyDataModel<RefAnexo9Gestion> lazyGestion) {
        this.lazyGestion = lazyGestion;
    }

    public RefAnexo9Gestion getObjetoGestion() {
        return objetoGestion;
    }

    public void setObjetoGestion(RefAnexo9Gestion objectoGestion) {
        this.objetoGestion = objectoGestion;
    }

    public List<RefAnexo9Semaforo> getListaRefAnexo9Semaforo() {
        return listaRefAnexo9Semaforo;
    }

    public void setListaRefAnexo9Semaforo(List<RefAnexo9Semaforo> listaRefAnexo9Semaforo) {
        this.listaRefAnexo9Semaforo = listaRefAnexo9Semaforo;
    }

    public List<RefTransporte> getRegistrosRefTransportes() {
        return registrosRefTransportes;
    }

    public void setRegistrosRefTransportes(List<RefTransporte> registrosRefTransportes) {
        this.registrosRefTransportes = registrosRefTransportes;
    }

    public LazyDataModel<RefTransporte> getLazyTransporte() {
        return lazyTransporte;
    }

    public void setLazyTransporte(LazyDataModel<RefTransporte> lazyTransporte) {
        this.lazyTransporte = lazyTransporte;
    }

    public List<Maestro> getListaTipoTransporte() {
        return listaTipoTransporte;
    }

    public void setListaTipoTransporte(List<Maestro> listaTipoTransporte) {
        this.listaTipoTransporte = listaTipoTransporte;
    }

    public HashMap<Integer, Maestro> getHashTipoTransporte() {
        return hashTipoTransporte;
    }

    public void setHashTipoTransporte(HashMap<Integer, Maestro> hashTipoTransporte) {
        this.hashTipoTransporte = hashTipoTransporte;
    }

    public List<Maestro> getListaTransporteLiquidacion() {
        return listaTransporteLiquidacion;
    }

    public void setListaTransporteLiquidacion(List<Maestro> listaTransporteLiquidacion) {
        this.listaTransporteLiquidacion = listaTransporteLiquidacion;
    }

    public HashMap<Integer, Maestro> getHashTransporteLiquidacion() {
        return hashTransporteLiquidacion;
    }

    public void setHashTransporteLiquidacion(HashMap<Integer, Maestro> hashTransporteLiquidacion) {
        this.hashTransporteLiquidacion = hashTransporteLiquidacion;
    }

    public List<Maestro> getListaClaseTransporte() {
        return listaClaseTransporte;
    }

    public void setListaClaseTransporte(List<Maestro> listaClaseTransporte) {
        this.listaClaseTransporte = listaClaseTransporte;
    }

    public List<Maestro> getListaClaseTransporteFiltro() {
        return listaClaseTransporteFiltro;
    }

    public void setListaClaseTransporteFiltro(List<Maestro> listaClaseTransporteFiltro) {
        this.listaClaseTransporteFiltro = listaClaseTransporteFiltro;
    }

    public List<Maestro> getListaInsumoTransporte() {
        return listaInsumoTransporte;
    }

    public void setListaInsumoTransporte(List<Maestro> listaInsumoTransporte) {
        this.listaInsumoTransporte = listaInsumoTransporte;
    }

    public HashMap<Integer, Maestro> getHashInsumoTransporte() {
        return hashInsumoTransporte;
    }

    public void setHashInsumoTransporte(HashMap<Integer, Maestro> hashInsumoTransporte) {
        this.hashInsumoTransporte = hashInsumoTransporte;
    }

    public RefTransporte getObjetoTransporte() {
        return objetoTransporte;
    }

    public void setObjetoTransporte(RefTransporte objetoTransporte) {
        this.objetoTransporte = objetoTransporte;
    }

    public RefTransporteSeguimiento getObjetoTransporteSeguimiento() {
        return objetoTransporteSeguimiento;
    }

    public void setObjetoTransporteSeguimiento(RefTransporteSeguimiento objetoTransporteSeguimiento) {
        this.objetoTransporteSeguimiento = objetoTransporteSeguimiento;
    }

    public SelPrestadorSedesBean getSedesBean() {
        sedesBean = (SelPrestadorSedesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorSedesBean");
        return sedesBean;
    }

    public void setSedesBean(SelPrestadorSedesBean sedesBean) {
        this.sedesBean = sedesBean;
    }

    public Integer[] getInsumosSeleccionados() {
        return insumosSeleccionados;
    }

    public void setInsumosSeleccionados(Integer[] insumosSeleccionados) {
        this.insumosSeleccionados = insumosSeleccionados;
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

    public String getDialogo() {
        return dialogo;
    }

    public void setDialogo(String dialogo) {
        this.dialogo = dialogo;
    }

    public CntProfesionalPrestador getObjetoProfesionalPrestador() {
        return objetoProfesionalPrestador;
    }

    public void setObjetoProfesionalPrestador(CntProfesionalPrestador objetoProfesionalPrestador) {
        this.objetoProfesionalPrestador = objetoProfesionalPrestador;
    }

    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public List<RefAnexo9Adjunto> getListaRefAnexo9Adjuntos() {
        return listaRefAnexo9Adjuntos;
    }

    public void setListaRefAnexo9Adjuntos(List<RefAnexo9Adjunto> listaRefAnexo9Adjuntos) {
        this.listaRefAnexo9Adjuntos = listaRefAnexo9Adjuntos;
    }

    public Ubicacion getObjetoUbicacionAcomp() {
        return objetoUbicacionAcomp;
    }

    public void setObjetoUbicacionAcomp(Ubicacion objetoUbicacionAcomp) {
        this.objetoUbicacionAcomp = objetoUbicacionAcomp;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public boolean getMostrarBorrarDocumento() {
        return mostrarBorrarDocumento;
    }

    public void setMostrarBorrarDocumento(boolean mostrarBorrarDocumento) {
        this.mostrarBorrarDocumento = mostrarBorrarDocumento;
    }

    public String getInsumoSeleccionadoTexto() {
        return insumoSeleccionadoTexto;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoProfesional() {
        return hashTiposDocumentoProfesional;
    }

    public void setHashTiposDocumentoProfesional(HashMap<Integer, Maestro> hashTiposDocumentoProfesional) {
        this.hashTiposDocumentoProfesional = hashTiposDocumentoProfesional;
    }

    public List<Maestro> getListaTiposDocumentoProfesional() {
        return listaTiposDocumentoProfesional;
    }

    public void setListaTiposDocumentoProfesional(List<Maestro> listaTiposDocumentoProfesional) {
        this.listaTiposDocumentoProfesional = listaTiposDocumentoProfesional;
    }

    public void setInsumoSeleccionadoTexto(String insumoSeleccionadoTexto) {
        this.insumoSeleccionadoTexto = insumoSeleccionadoTexto;
    }

    public SelServiciosHabilitacionBean getSelServiciosHabilitacionBean() {
        selServiciosHabilitacionBean = (SelServiciosHabilitacionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selServiciosHabilitacionBean");
        return selServiciosHabilitacionBean;
    }

    public void setSelServiciosHabilitacionBean(SelServiciosHabilitacionBean selServiciosHabilitacionBean) {
        this.selServiciosHabilitacionBean = selServiciosHabilitacionBean;
    }

    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public int getServicioHabilitacion() {
        return servicioHabilitacion;
    }

    public void setServicioHabilitacion(int servicioHabilitacion) {
        this.servicioHabilitacion = servicioHabilitacion;
    }

    public List<RefAnexo9TriageDTO> getListaTriage() {
        return listaTriage;
    }

    public void setListaTriage(List<RefAnexo9TriageDTO> listaTriage) {
        this.listaTriage = listaTriage;
    }

    public RefAnexo9TriageDTO getObjectoTriage() {
        return objectoTriage;
    }

    public void setObjectoTriage(RefAnexo9TriageDTO objectoTriage) {
        this.objectoTriage = objectoTriage;
    }

    public List<Maestro> getListaEstadoSolicitud() {
        return listaEstadoSolicitud;
    }

    public void setListaEstadoSolicitud(List<Maestro> listaEstadoSolicitud) {
        this.listaEstadoSolicitud = listaEstadoSolicitud;
    }

    public HashMap<Integer, Maestro> getHashEstadoSolicitud() {
        return hashEstadoSolicitud;
    }

    public void setHashEstadoSolicitud(HashMap<Integer, Maestro> hashEstadoSolicitud) {
        this.hashEstadoSolicitud = hashEstadoSolicitud;
    }

    public HashMap<Integer, Maestro> getHashCanalComunicacion() {
        return hashCanalComunicacion;
    }

    public void setHashCanalComunicacion(HashMap<Integer, Maestro> hashCanalComunicacion) {
        this.hashCanalComunicacion = hashCanalComunicacion;
    }

    public List<Maestro> getListaGrupoServicio() {
        return listaGrupoServicio;
    }

    public void setListaGrupoServicio(List<Maestro> listaGrupoServicio) {
        this.listaGrupoServicio = listaGrupoServicio;
    }

    public HashMap<Integer, Maestro> getHashGrupoServicio() {
        return hashGrupoServicio;
    }

    public void setHashGrupoServicio(HashMap<Integer, Maestro> hashGrupoServicio) {
        this.hashGrupoServicio = hashGrupoServicio;
    }

    public List<Maestro> getListaTipoAislamiento() {
        return listaTipoAislamiento;
    }

    public void setListaTipoAislamiento(List<Maestro> listaTipoAislamiento) {
        this.listaTipoAislamiento = listaTipoAislamiento;
    }

    public HashMap<Integer, Maestro> getHashTipoAislamiento() {
        return hashTipoAislamiento;
    }

    public void setHashTipoAislamiento(HashMap<Integer, Maestro> hashTipoAislamiento) {
        this.hashTipoAislamiento = hashTipoAislamiento;
    }

    public List<PeAfiliadosPrograma> getListaAfiliadoPrograma() {
        return listaAfiliadoPrograma;
    }

    public void setListaAfiliadoPrograma(List<PeAfiliadosPrograma> listaAfiliadoPrograma) {
        this.listaAfiliadoPrograma = listaAfiliadoPrograma;
    }

    public List<Maestro> getListaMaternoPerinata() {
        return listaMaternoPerinata;
    }

    public void setListaMaternoPerinata(List<Maestro> listaMaternoPerinata) {
        this.listaMaternoPerinata = listaMaternoPerinata;
    }

    public HashMap<Integer, Maestro> getHashMaternoPerinata() {
        return hashMaternoPerinata;
    }

    public void setHashMaternoPerinata(HashMap<Integer, Maestro> hashMaternoPerinata) {
        this.hashMaternoPerinata = hashMaternoPerinata;
    }

    public List<Maestro> getListaCanalComunicacion() {
        return listaCanalComunicacion;
    }

    public void setListaCanalComunicacion(List<Maestro> listaCanalComunicacion) {
        this.listaCanalComunicacion = listaCanalComunicacion;
    }

    public boolean getDeshabilitadoCanal() {
        return deshabilitadoCanal;
    }

    public void setDeshabilitadoCanal(boolean deshabilitadoCanal) {
        this.deshabilitadoCanal = deshabilitadoCanal;
    }

    public ReporteReferencia getReporte() {
        return reporte;
    }

    public void setReporte(ReporteReferencia reporte) {
        this.reporte = reporte;
    }

    public ReporteReferenciaContraReferencia getReporteReferenciaContraReferencia() {
        return reporteReferenciaContraReferencia;
    }

    public void setReporteReferenciaContraReferencia(ReporteReferenciaContraReferencia reporteReferenciaContraReferencia) {
        this.reporteReferenciaContraReferencia = reporteReferenciaContraReferencia;
    }

    public List<ReporteReferencia> getListaReporte() {
        return listaReporte;
    }

    public void setListaReporte(List<ReporteReferencia> listaReporte) {
        this.listaReporte = listaReporte;
    }

    public List<ReporteReferenciaContraReferencia> getListaReporteReferenciaContraReferencia() {
        return listaReporteReferenciaContraReferencia;
    }

    public void setListaReporteReferenciaContraReferencia(List<ReporteReferenciaContraReferencia> listaReporteReferenciaContraReferencia) {
        this.listaReporteReferenciaContraReferencia = listaReporteReferenciaContraReferencia;
    }

    public RefAnexo9Diagnostico getObjetoDiagnosticoBorrar() {
        return objetoDiagnosticoBorrar;
    }

    public void setObjetoDiagnosticoBorrar(RefAnexo9Diagnostico objetoDiagnosticoBorrar) {
        this.objetoDiagnosticoBorrar = objetoDiagnosticoBorrar;
    }

    public List<RefAnexo9Diagnostico> getDiagnosticosBorrar() {
        return diagnosticosBorrar;
    }

    public void setDiagnosticosBorrar(List<RefAnexo9Diagnostico> diagnosticosBorrar) {
        this.diagnosticosBorrar = diagnosticosBorrar;
    }

    public boolean getInhabilitarMotivo() {
        return inhabilitarMotivo;
    }

    public void setInhabilitarMotivo(boolean inhabilitarMotivo) {
        this.inhabilitarMotivo = inhabilitarMotivo;
    }

    public boolean getGestionar() {
        return gestionar;
    }

    public void setGestionar(boolean gestionar) {
        this.gestionar = gestionar;
    }

    public List<Maestro> getListaGestionarMotivos() {
        return listaGestionarMotivos;
    }

    public void setListaGestionarMotivos(List<Maestro> listaGestionarMotivos) {
        this.listaGestionarMotivos = listaGestionarMotivos;
    }

    public Date getFechaMaximaCalendario() {
        return fechaMaximaCalendario;
    }

    public void setFechaMaximaCalendario(Date fechaMaximaCalendario) {
        this.fechaMaximaCalendario = fechaMaximaCalendario;
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

    public List<Maestro> getListaEstadosAfiliado() {
        return listaEstadosAfiliado;
    }

    public void setListaEstadosAfiliado(List<Maestro> listaEstadosAfiliado) {
        this.listaEstadosAfiliado = listaEstadosAfiliado;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliado() {
        return hashEstadosAfiliado;
    }

    public List<Maestro> getListaRegimen() {
        return listaRegimen;
    }

    public void setListaRegimen(List<Maestro> listaRegimen) {
        this.listaRegimen = listaRegimen;
    }

    public HashMap<Integer, Maestro> getHashRegimen() {
        return hashRegimen;
    }

    public void setHashRegimen(HashMap<Integer, Maestro> hashRegimen) {
        this.hashRegimen = hashRegimen;
    }

    public boolean isIsActivoAfiliado() {
        return isActivoAfiliado;
    }

    public void setIsActivoAfiliado(boolean isActivoAfiliado) {
        this.isActivoAfiliado = isActivoAfiliado;
    }

    public void setHashEstadosAfiliado(HashMap<Integer, Maestro> hashEstadosAfiliado) {
        this.hashEstadosAfiliado = hashEstadosAfiliado;
    }

    public Maestro getMaeClaseTransporte() {
        return maeClaseTransporte;
    }

    public void setMaeClaseTransporte(Maestro maeClaseTransporte) {
        this.maeClaseTransporte = maeClaseTransporte;
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

    public List<RefAnexo9> getListaAnexos9Activos() {
        return listaAnexos9Activos;
    }

    public void setListaAnexos9Activos(List<RefAnexo9> listaAnexos9Activos) {
        this.listaAnexos9Activos = listaAnexos9Activos;
    }

    public String getObservacionGenerica() {
        return observacionGenerica;
    }

    public void setObservacionGenerica(String observacionGenerica) {
        this.observacionGenerica = observacionGenerica;
    }

    public String getValidadorPatter() {
        return PATTERN;
    }

    public String obtenerSiNo(boolean bandera) {
        try {
            if (getObjeto().getAsegAfiliado() != null && getObjeto().getAsegAfiliado().getMaeTipoDocumento() != 0) {
                return bandera ? "Si" : "No";
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerColorEstado() {
        return getObjeto().getAsegAfiliado().getMaeEstadoAfiliacion() == 134 ? "font-weight: bold;" : "color: #ff0000; font-weight: bold;";
    }

    public String obtenerTriage(int triage) {
        String ruta = "";
        switch (triage) {
            case 1:
                ruta = "../resources/images/triage1.png";
                break;
            case 2:
                ruta = "../resources/images/triage2.png";
                break;
            case 3:
                ruta = "../resources/images/triage3.png";
                break;
            case 4:
                ruta = "../resources/images/triage4.png";
                break;
            case 5:
                ruta = "../resources/images/triage5.png";
                break;
            default:
                ruta = "";
                break;
        }

        return ruta;
    }

    public void obtenerInsumosSeleccionados() {
        insumoSeleccionadoTexto = "";
        for (int i = 0; i < insumosSeleccionados.length; i++) {
            insumoSeleccionadoTexto += hashInsumoTransporte.get(insumosSeleccionados[i]).getNombre() + (i + 1 <= insumosSeleccionados.length ? "" : ",");
        }
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicacion.get(id).getUbicacionPadre().getId();
            return hashUbicacion.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerUbicacion(int id) {
        if (id == 0) {
            return "";
        }
        String ubi = hashUbicacion.get(id).getNombreDepartamentoCiudad();
        return ubi;
    }

    public String semaforo(int tiempo) {
        String estilo = "";
        for (RefAnexo9Semaforo obj : listaRefAnexo9Semaforo) {
            if (tiempo <= obj.getTiempo()) {
                estilo = "width: 10px; height: 10px; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%; background: #" + obj.getColor() + ";";
                break;
            } else {
                estilo = "width: 10px; height: 10px; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%; background: #" + obj.getColor() + ";";
            }
        }
        return estilo;
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : getListaUbicacion()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setAcompananteMunicipio(ubicacionesFiltradas.get(0).getNombre());
            getObjeto().setAcompananteDepartamento(ubicacionesFiltradas.get(0).getUbicacionPadre().getNombre());
            //actualizarSedes();
        }
        return ubicacionesFiltradas;
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion municipio = null;
        if (getHashUbicacion() != null) {
            municipio = getHashUbicacion().get(id);
        }
        if (municipio != null && municipio.getUbicacionPadre() != null) {
            Ubicacion departamento = municipio.getUbicacionPadre();
            if (departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = departamento.getUbicacionPadre();
                // ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = departamento.getNombre();
            ubicacionStr = municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public void actualizarDepartamentoAcomp(SelectEvent event) {
        Ubicacion ubicacion = (Ubicacion) event.getObject();
        if (ubicacion != null && ubicacion.getUbicacionPadre() != null) {
            objeto.setAcompananteMunicipio(ubicacion.getNombre());
            objeto.setAcompananteDepartamento(ubicacion.getUbicacionPadre().getNombre());
        }
    }

    public boolean validarTipoDocumentoAcomp() {
        if (getObjeto().getMaeAcompananteTipoDocumentoId() == null) {
            return false;
        }
        return getObjeto().getMaeAcompananteTipoDocumentoId() == ReferenciaContraRefBean.ID_TIPO_DOCUMENTO_ACOMP_CC;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getReferenciaContraServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescarAfiliado() {
        getReferenciaContraServicio().listarAfiliado(this);
    }

    public void refrescarIps() {
        getReferenciaContraServicio().listarIPS(this);
    }

    public void refrescarProfesional() {
        getReferenciaContraServicio().consultarProfesional(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getReferenciaContraServicio().Accion(this);
        colorEstadoAfiliado = obtenerColorEstado();
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        try {
//            this.setParamConsulta3(new ParamConsulta());
            this.getParamConsulta(3).setEmpresaId(super.getConexion().getEmpresa().getId());
            this.getParamConsulta(3).setParametroConsulta1(id);
            this.getParamConsulta(3).setFiltros(new HashMap<>());

//            this.setParamConsulta5(new ParamConsulta());
            this.getParamConsulta(5).setEmpresaId(super.getConexion().getEmpresa().getId());
            this.getParamConsulta(5).setParametroConsulta1(id);
            this.getParamConsulta(5).setFiltros(new HashMap<>());
        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
        procesoFinal();
    }

    public void crear() {
        setObjeto(new RefAnexo9());
        setObjetoUbicacionAcomp(new Ubicacion());
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_CREAR);
        getReferenciaContraServicio().Accion(this);
        setDialogo(DIALOGO_CREAR);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        super.setDoAccion(ACCION_EDITAR);
        getReferenciaContraServicio().Accion(this);
        colorEstadoAfiliado = obtenerColorEstado();
        setDialogo(DIALOGO_EDITAR);
        procesoFinal();
    }
    
    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getReferenciaContraServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
            PrimeFaces.current().ajax().update("frmEditarAfiliado");
            PrimeFaces.current().ajax().update("frmEditarProfes");
            PrimeFaces.current().ajax().update("frmEditarDatos");
            PrimeFaces.current().ajax().update("frmReferencia");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }
    
    public void getAuditoriaBorrado(RefAnexo9Adjunto obj) {
        setObjetoAdjunto(new RefAnexo9Adjunto());
        setObjetoAdjunto(obj);
        PrimeFaces.current().resetInputs("frmAuditoriaAjuntoBorrado");
        PrimeFaces.current().ajax().update("frmAuditoriaAjuntoBorrado");
        PrimeFaces.current().executeScript("PF('dialogoAuditoriaAjuntoBorrado').show()");
       
    }
    
    public void gestionar(int id) {
        try {
//            this.setParamConsulta3(new ParamConsulta());
            this.getParamConsulta(3).setEmpresaId(super.getConexion().getEmpresa().getId());
            this.getParamConsulta(3).setParametroConsulta1(id);
            this.getParamConsulta(3).setFiltros(new HashMap<>());
            getObjeto().setId(id);
            setObjetoGestion(new RefAnexo9Gestion());
            super.setAccion(ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_VER);
            getReferenciaContraServicio().Accion(this);
            colorEstadoAfiliado = obtenerColorEstado();
            setDialogo(DIALOGO_GESTIONAR);
            boolean estadoAfiliado = getReferenciaContraServicio().consultarEstadoAfiliado(this);
            if(!estadoAfiliado){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ADVERTENCIA", "El afiliado se encuentra en el estado " + getObjeto().getAsegAfiliado().getMaeEstadoAfiliacionValor()));
            }
            procesoFinal();
        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void refrescarGestiones() {
        getReferenciaContraServicio().listarGestionar(this);
    }

    public void crearGestion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }

    public void guardarGestionar() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(objetoGestion.getMaeTipoId(), this);
        if(maestro != null){
            if (maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)) {
                if (!this.validarTransporte()) {
                    PrimeFaces.current().executeScript("PF('dialogoConfirmarTransporte').show()");
                    return;
                }
            }
        }
        /*if (objetoGestion.getMaeTipoId() == RefAnexo9Gestion.ESTADO_CERRADA) {
            if (!this.validarTransporte()) {
                PrimeFaces.current().executeScript("PF('dialogoConfirmarTransporte').show()");
                return;
            }
        }*/
        getReferenciaContraServicio().Accion(this);
        if (!this.getErrores().isEmpty()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmGestionarVer:btnGestionar");
            return;
        }
        this.gestionar = this.validarEstadoGestonarFinal(objetoGestion.getMaeTipoId());
        PrimeFaces.current().ajax().update("frmGestionarVer:btnGestionar");
        PrimeFaces.current().resetInputs("frmReferencia");
        PrimeFaces.current().ajax().update("frmReferencia");
        procesoFinal();
    }

    public void abrirAdjuntosGestionar() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }

    public void guardarAdjuntoGestionar() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }

    public void transporte(int id) {
        getObjeto().setId(id);
        try {
//            this.setParamConsulta5(new ParamConsulta());
            this.getParamConsulta(5).setEmpresaId(super.getConexion().getEmpresa().getId());
            this.getParamConsulta(5).setParametroConsulta1(id);
            this.getParamConsulta(5).setFiltros(new HashMap<>());
        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER);
        getReferenciaContraServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmTransporteVer");
        PrimeFaces.current().ajax().update("frmAuditoriaTransporte:labelDatosAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoTransporte').show()");
        procesoFinal();
    }

    public void crearTransporte() {
        setObjetoTransporte(new RefTransporte());
        insumosSeleccionados = new Integer[listaInsumoTransporte.size()];
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }

    public void refrecarTransporte() {
        getReferenciaContraServicio().listarTransporte(this);
    }
    
    public void refrecarAdjuntos() {
        getReferenciaContraServicio().listarAdjuntos(this);
    }
    
    public void guardarTransporte() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        getReferenciaContraServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoAgregarTransporte').hide()");
        }

        procesoFinal();
    }

    public void consultarAfiliado() {
        try {

            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
//            PrimeFaces.current().ajax().update("frmAfiliadoLista");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void consultarIPS() {
        try {
            PrimeFaces.current().ajax().update("frmIpsLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }
    
    public void consultarAcompañante() {
        try {
            
            if (getObjeto().getMaeAcompananteTipoDocumentoId() == null) {
                return;
            }

            if (getObjeto().getAcompananteDocumento() == null || getObjeto().getAcompananteDocumento().isBlank()) {
                return;
            }
           
            if (!getErrores().isEmpty()) {
                generarMensajes();
                return;
            }
            Maestro tipoDocumento = getReferenciaContraServicio().consultarMaestroTipoDocumento(this);
            if(getObjeto().getMaeAcompananteTipoDocumentoId().equals(tipoDocumento.getId()) && getObjeto().getAcompananteDocumento().equals("1111")){
                getObjeto().setAcompanantePrimerNombre("SIN DATO");
                getObjeto().setAcompananteSegundoNombre("SIN DATO");
                getObjeto().setAcompanantePrimerApellido("SIN DATO");
                getObjeto().setAcompananteSegundoApellido("SIN DATO");
                getObjeto().setAcompananteDireccion("SIN DATO");
                getObjeto().setAcompananteTelefono("1111111111");
                if(getObjeto().getAsegAfiliado() != null){
                    if (getObjeto().getAsegAfiliado().getResidenciaUbicacion() != null && getObjeto().getAsegAfiliado().getResidenciaUbicacion().getUbicacionPadre() != null) {
                        setObjetoUbicacionAcomp(getObjeto().getAsegAfiliado().getResidenciaUbicacion());
                        getObjeto().setAcompananteMunicipio(getObjeto().getAsegAfiliado().getResidenciaUbicacion().getNombre());
                        getObjetoUbicacionAcomp().setUbicacionPadre(getObjeto().getAsegAfiliado().getResidenciaUbicacion().getUbicacionPadre());
                        getObjeto().setAcompananteDepartamento(getObjeto().getAsegAfiliado().getResidenciaUbicacion().getUbicacionPadre().getNombre());
                    }
                }
            }else{
                getObjeto().setAcompanantePrimerNombre(null);
                getObjeto().setAcompananteSegundoNombre(null);
                getObjeto().setAcompanantePrimerApellido(null);
                getObjeto().setAcompananteSegundoApellido(null);
                getObjeto().setAcompananteDireccion(null);
                getObjeto().setAcompananteTelefono(null);
                getObjeto().setAcompananteDepartamento(null);
                setObjetoUbicacionAcomp(new Ubicacion());
                getObjeto().setAcompananteMunicipio(null);
            }
            ///getReferenciaContraServicio().consultarProfesional(this);
            PrimeFaces.current().resetInputs("frmCrearAfiliado:pnlAcompanante");
            PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAcompanante");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void consultarProfesional() {
        try {
            if (objeto.getCntPrestadorSede() == null
                    || objeto.getCntPrestadorSede().getId() == null) {
                addError("Se debe seleccionar una ips");
            }

            if (getObjeto().getCntProfesionales().getMaeTipoCodumentoId() == 0) {
                return;
            }

            if (getObjeto().getCntProfesionales().getDocumento() == null || getObjeto().getCntProfesionales().getDocumento().isBlank()) {
                return;
            }

            if (!getErrores().isEmpty()) {
                generarMensajes();
                return;
            }
            getReferenciaContraServicio().consultarProfesional(this);
            PrimeFaces.current().resetInputs("frmCrearAfiliado:pnlPersona");
            PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlPersona");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void consultarDiagnostico() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoDiagnostico() {
        boolean agregar = true;
        for (RefAnexo9Diagnostico diag : getObjeto().getListaRefAnexo9Diagnostico()) {
            if (diag.getMaDiagnosticosId() == getSelDiagnosticosBean().getObjeto().getId()) {
                agregar = false;
                addMensaje("El diagnostico " + getSelDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
            }
        }

        if (agregar) {
            RefAnexo9Diagnostico refAnexo9Diagnostico = new RefAnexo9Diagnostico();
            if (getObjeto().getListaRefAnexo9Diagnostico().isEmpty()) {
                refAnexo9Diagnostico.setPrincipal(true);
            }
            refAnexo9Diagnostico.setMaDiagnosticosCodigo(getSelDiagnosticosBean().getObjeto().getCodigo());
            refAnexo9Diagnostico.setMaDiagnosticosId(getSelDiagnosticosBean().getObjeto().getId());
            refAnexo9Diagnostico.setMaDiagnosticosValor(getSelDiagnosticosBean().getObjeto().getNombre());
            getObjeto().getListaRefAnexo9Diagnostico().add(refAnexo9Diagnostico);
            if (getSelDiagnosticosBean().getObjeto().getPriorizarCrue() != null
                    && getSelDiagnosticosBean().getObjeto().getPriorizarCrue()) {
                getObjeto().setDiagnosticoEmergente(getSelDiagnosticosBean().getObjeto().getPriorizarCrue());
            }
        } else {
            generarMensajes();
        }
        getSelDiagnosticosBean().setObjeto(new MaDiagnostico());
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
        if (dialogo.equals(DIALOGO_CREAR)) {
            PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlDiagnostico");
        } else {
            PrimeFaces.current().ajax().update("frmEditarDatos:pnlDiagnostico");
        }
    }

    public void consultarSedeTransporte() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoSedeLista').show()");
            PrimeFaces.current().ajax().update("frmSedeLista");
        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoSedeTransporte() {
        getObjetoTransporte().setCntPrestadorSede(getSedesBean().getObjeto());
        getSedesBean().setObjeto(new CntPrestadorSede());
        PrimeFaces.current().executeScript("PF('dialogoSedeLista').hide()");
        PrimeFaces.current().ajax().update("frmAgregarTrans:olSedeTransporte");
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        getReferenciaContraServicio().asignarAfiliado(((AsegAfiliado) event.getObject()).getId(), this);
        if (isActivoAfiliado) {
            PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAfiliado");
            PrimeFaces.current().ajax().update("frmCrearAfiliado:pProgramaEspecialCrear");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
        }
        if (getListaAnexos9Activos() != null && !getListaAnexos9Activos().isEmpty()) {
            addMensaje("El paciente cuenta con remisiones activas");
            PrimeFaces.current().ajax().update("frmRemisionesActivas");
            PrimeFaces.current().executeScript("PF('dialogoRemisionesActivas').show()");
        }
        generarMensajes();
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        if (dialogo.equals(DIALOGO_CREAR)) {
            this.objeto.setCntPrestadorSede(ips);
            getParamConsulta(2).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlIPS");

        } else if (dialogo.equals(DIALOGO_GESTIONAR)) {
            this.objetoGestion.setCntPrestadorSede(ips);
            PrimeFaces.current().ajax().update("frmAgregarGestion:olPrestadorIPS");
        }
        getObjeto().setHabilitarCups(true);
        getObjeto().setHabilitarDireccionAlternativa(true);
        getObjeto().setHabilitarNombreContactoEmergencia(true);
        getObjeto().setHabilitarTelefonoContactoEmergencia(true);
        getObjeto().setHabilitarMaeCausaExternaId(true);
        getObjeto().setHabilitarPrioridad(true);
        getObjeto().setHabilitarMaeTipoAtencionId(true);
        getObjeto().setHabilitarGrupoServicio(true);
        getObjeto().setHabilitarMaeModalidadTecnologiaId(true);
        getObjeto().setHabilitarMaeCondicionDestinoId(true);
        
        getObjeto().setMaeCausaExternaId(null);
        getObjeto().setMaeCausaExternaCodigo(null);
        getObjeto().setMaeCausaExternaValor(null);
        getObjeto().setPrioridad(null);
        getObjeto().setMaeTipoAtencionId(null);
        getObjeto().setMaeTipoAtencionCodigo(null);
        getObjeto().setMaeTipoAtencionValor(null);
        getObjeto().setMaeUbicacionId(null);
        getObjeto().setMaeUbicacionCodigo(null);
        getObjeto().setMaeUbicacionValor(null);
        getObjeto().setMaeModalidadTecnologiaId(null);
        getObjeto().setMaeModalidadTecnologiaCodigo(null);
        getObjeto().setMaeModalidadTecnologiaValor(null);
        
        getObjeto().setTipoTecnologia(null);
        getObjeto().setMaTecnologiaId(null);
        getObjeto().setMaTecnologiaCodigo(null);
        getObjeto().setMaTecnologiaValor(null);
        getObjeto().setCantidadTecnologiaSolicitada(null);
        
        getObjeto().setMaeCondicionDestinoId(null);
        getObjeto().setMaeCondicionDestinoCodigo(null);
        getObjeto().setMaeCondicionDestinoValor(null);
        getObjeto().setVersion(RefAnexo9.VERSION_0);
        if(ips.getFechaFacturaElectronica() != null){
            getObjeto().setVersion(RefAnexo9.VERSION_1);
            getObjeto().setHabilitarCups(false);
            getObjeto().setHabilitarDireccionAlternativa(false);
            getObjeto().setHabilitarNombreContactoEmergencia(false);
            getObjeto().setHabilitarTelefonoContactoEmergencia(false);
            getObjeto().setHabilitarMaeCausaExternaId(false);
            getObjeto().setHabilitarPrioridad(false);
            getObjeto().setHabilitarMaeTipoAtencionId(false);
            getObjeto().setHabilitarGrupoServicio(false);
            getObjeto().setHabilitarMaeModalidadTecnologiaId(false);
            getObjeto().setHabilitarMaeCondicionDestinoId(false);
        }else if(ips.getGrupoRipsMinisterio() != null){
            if(ips.getGrupoRipsMinisterio().equals(1)
                ||ips.getGrupoRipsMinisterio().equals(2)){
                getObjeto().setVersion(RefAnexo9.VERSION_1);
                getObjeto().setHabilitarCups(false);
                getObjeto().setHabilitarDireccionAlternativa(false);
                getObjeto().setHabilitarNombreContactoEmergencia(false);
                getObjeto().setHabilitarTelefonoContactoEmergencia(false);
                getObjeto().setHabilitarMaeCausaExternaId(false);
                getObjeto().setHabilitarPrioridad(false);
                getObjeto().setHabilitarMaeTipoAtencionId(false);
                getObjeto().setHabilitarGrupoServicio(false);
                getObjeto().setHabilitarMaeModalidadTecnologiaId(false);
                getObjeto().setHabilitarMaeCondicionDestinoId(false);
            }
        }
        PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAfiliado");
        PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlCausaMotivaaAtención");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
    }

    public void cancelarCreacionAnexo9() {
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_CREAR);
        getReferenciaContraServicio().Accion(this);
    }

    public void diagnosticoPrincipal(int id) {
        for (RefAnexo9Diagnostico diagnostico : getObjeto().getListaRefAnexo9Diagnostico()) {
            if (diagnostico.getMaDiagnosticosId() != id) {
                diagnostico.setPrincipal(false);
            } else {
                diagnostico.setPrincipal(true);
            }
        }
        if (dialogo.equals(DIALOGO_CREAR)) {
            PrimeFaces.current().ajax().update("frmCrearAfiliado:tablaDiagnosticos");
        } else if (dialogo.equals(DIALOGO_EDITAR)) {
            PrimeFaces.current().ajax().update("frmEditarDatos:tablaDiagnosticos");
        }
    }

    public void borrarDiagnostico(RefAnexo9Diagnostico diagnostico) {
        if (diagnostico.getId() == null) {
            getObjeto().getListaRefAnexo9Diagnostico().remove(diagnostico);
            this.validarDiagnosticoPrincipal();
            if (dialogo.equals(DIALOGO_CREAR)) {
                PrimeFaces.current().ajax().update("frmCrearAfiliado:tablaDiagnosticos");
            } else if (dialogo.equals(DIALOGO_EDITAR)) {
                PrimeFaces.current().ajax().update("frmEditarDatos:tablaDiagnosticos");
            }
        } else {
//            setObjetoDiagnosticoBorrar(diagnostico);
//            super.setAccion(Url.ACCION_MODIFICAR);
//            super.setDoAccion(Url.ACCION_BORRAR);
//            getReferenciaContraServicio().Accion(this);
            diagnosticosBorrar.add(diagnostico);
            getObjeto().getListaRefAnexo9Diagnostico().remove(diagnostico);
            this.validarDiagnosticoPrincipal();
//            procesoFinal();
        }
    }

    public void abrirAdjuntos() {
        setObjetoAdjunto(new RefAnexo9Adjunto());
        setNombreDocumento("");
        setMostrarBorrarDocumento(false);
        PrimeFaces.current().resetInputs(":frmAdjunto");
        PrimeFaces.current().ajax().update(":frmAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
    }

    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        objetoAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
        this.mostrarBorrarDocumento = true;
        nombreDocumento = archivoAdjunto.getFileName();
    }

    public void agregarArchivo() {
        try {
            if (objetoAdjunto.getMaeTipoArchivoId() == 0) {
                addError("Tipo Adjunto: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }
            Maestro tipoAdjunto = hashTipoAdjunto.get(objetoAdjunto.getMaeTipoArchivoId());
            objetoAdjunto.setMaeTipoArchivoCodigo(tipoAdjunto.getValor());
            objetoAdjunto.setMaeTipoArchivoValor(tipoAdjunto.getNombre());

            RefAnexo9Adjunto borrarObj = new RefAnexo9Adjunto();
            getListaRefAnexo9Adjuntos().remove(borrarObj);

            String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".") + 1;
            objetoAdjunto.setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objetoAdjunto.setNombre(nombreAdjunto.substring(0, indiceExtension));
            objetoAdjunto.setNombreArchivo(archivoAdjunto.getFileName());
            getListaRefAnexo9Adjuntos().add(objetoAdjunto);
            objetoAdjunto = new RefAnexo9Adjunto();
            switch (dialogo) {
                case DIALOGO_CREAR:
                    PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAdjuntos");
                    break;
                case DIALOGO_GESTIONAR:
                    PrimeFaces.current().ajax().update("frmGestionarVer:tablaAdjuntosGestionar");
                    break;
                case DIALOGO_EDITAR:
                    PrimeFaces.current().ajax().update("frmEditarDatos:tablaAdjuntosEditar");
                    break;
                default:
                    break;
            }
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').hide()");
        } catch (Exception ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void borrarArchivo() {
        archivoAdjunto = null;
        this.mostrarBorrarDocumento = false;
        nombreDocumento = "";
    }

    public void borrarAdjunto(RefAnexo9Adjunto adjunto) {
        listaRefAnexo9Adjuntos.remove(adjunto);
        switch (dialogo) {
            case DIALOGO_CREAR:
                PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAdjuntos");
                break;
            case DIALOGO_GESTIONAR:
                PrimeFaces.current().ajax().update("frmGestionarVer:tablaAdjuntosGestionar");
                break;
            case DIALOGO_EDITAR:
                PrimeFaces.current().ajax().update("frmEditarDatos:tablaAdjuntosEditar");
                break;
            default:
                break;
        }
    }
    
    public void ventanaBorrarAdjunto(int _id) {
        this.setObjetoAdjunto(new RefAnexo9Adjunto());
        this.getObjetoAdjunto().setId(_id);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmBorrarAdjunto");
            PrimeFaces.current().ajax().update("frmBorrarAdjunto");
            PrimeFaces.current().executeScript("PF('dialogoBorrarAdjunto').show()");
        }
    }
    
    public void borrarAdjuntoReferencia() {
        
        if (objetoAdjunto.getId() != null) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_BORRAR_CONTACTOS_PERSONAS);
            getReferenciaContraServicio().Accion(this);
        }

        addMensaje("Se ha realizado la eliminación del contacto");
        generarMensajes();
        refrecarAdjuntos();
        //PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
        PrimeFaces.current().ajax().update("frmGestionarVer:pnlAdjuntos");
        PrimeFaces.current().executeScript("PF('dialogoBorrarAdjunto').hide()");
        //PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
    }
    
    public void consultarEspecialidad() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoEspecialidad() {
        getObjetoProfesionalPrestador().setMaEspecialidadCodigo(getEspecialidadesBean().getObjeto().getCodigo());
        getObjetoProfesionalPrestador().setMaEspecialidadValor(getEspecialidadesBean().getObjeto().getNombre());
        getObjetoProfesionalPrestador().setMaEspecialidadId(getEspecialidadesBean().getObjeto().getId());
        getSelDiagnosticosBean().setObjeto(new MaDiagnostico());
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmCrearAfiliado:labelEspecialidad");
    }

    public void verSeguimiento(int id) {
        setObjetoTransporte(new RefTransporte());
        getObjetoTransporte().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }

    public void crearSeguimiento(int id) {
        setObjetoTransporte(new RefTransporte());
        getObjetoTransporte().setId(id);
        setObjetoTransporteSeguimiento(new RefTransporteSeguimiento());
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getReferenciaContraServicio().Accion(this);
        procesoFinal();
    }

    public void guardarSeguimiento() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getReferenciaContraServicio().Accion(this);
        if (!this.getErrores().isEmpty()) {
            generarMensajes();
            return;
        }
        setObjetoTransporteSeguimiento(new RefTransporteSeguimiento());
        insumosSeleccionados = new Integer[listaInsumoTransporte.size()];
        procesoFinal();
    }

    public boolean validarEstadoEditar(int estado) {
        boolean deshabilitar = true;
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(estado, this);
        if(maestro != null){
            if (( maestro.getValor().equals(RefAnexo9Estado.ESTADO_GESTION_DE_REGULACION_CODIGO) || maestro.getValor().equals(RefAnexo9Estado.ESTADO_PREADMITIDA_CODIGO))
                && super.getConexion().getEmpresa().isAdministradora()) {
                deshabilitar = false;
            }
            if (maestro.getValor().equals(RefAnexo9Estado.ESTADO_PREADMITIDA_CODIGO) && !super.getConexion().getEmpresa().isAdministradora()) {
                deshabilitar = false;
            }
        }
        /*if ((estado == RefAnexo9Estado.ESTADO_GESTION_DE_REGULACION || estado == RefAnexo9Estado.ESTADO_PREADMITIDA)
                && super.getConexion().getEmpresa().isAdministradora()) {
            deshabilitar = false;
        }
        if (estado == RefAnexo9Estado.ESTADO_PREADMITIDA && !super.getConexion().getEmpresa().isAdministradora()) {
            deshabilitar = false;
        }*/
        return deshabilitar;
    }

    public boolean validarEstadoGestonar(int estado) {
        boolean deshabilitar = false;
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(estado, this);
        if(maestro != null){
            if (maestro.getValor().equals(RefAnexo9Estado.ESTADO_ANULADA_CODIGO) || maestro.getValor().equals(RefAnexo9Estado.ESTADO_CANCELADA_CODIGO) 
                || maestro.getValor().equals(RefAnexo9Estado.ESTADO_CERRADA_CODIGO) || maestro.getValor().equals(RefAnexo9Estado.ESTADO_RECHAZADA_CODIGO)) {
            deshabilitar = true;
            }
        }
        /*if (estado == RefAnexo9Estado.ESTADO_ANULADA || estado == RefAnexo9Estado.ESTADO_CANCELADA
                || estado == RefAnexo9Estado.ESTADO_CERRADA || estado == RefAnexo9Estado.ESTADO_RECHAZADA) {
            deshabilitar = true;
        }*/
        return deshabilitar;
    }

    public boolean validarEstadoGestonarFinal(int estado) {
        boolean deshabilitar = true;
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(estado, this);
        if(maestro != null){
            if ((maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_ANULADA_CODIGO))
                    || (maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)) 
                    || (maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_CANCELADA_CODIGO)) ){
                deshabilitar = false;
            }
        }
        
        /*if (estado == RefAnexo9Gestion.ESTADO_ANULADA || estado == RefAnexo9Gestion.ESTADO_CERRADA
                || estado == RefAnexo9Gestion.ESTADO_CANCELADA) {
            deshabilitar = false;
        }*/
        return deshabilitar;
    }

    public boolean validarEstadoTransporte(int estado) {
        boolean deshabilitar = false;
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(estado, this);
        if(maestro != null){
            if (maestro.getValor().equals(RefAnexo9Estado.ESTADO_ANULADA_CODIGO) || maestro.getValor().equals(RefAnexo9Estado.ESTADO_CANCELADA_CODIGO) 
                || maestro.getValor().equals(RefAnexo9Estado.ESTADO_CERRADA_CODIGO) || maestro.getValor().equals(RefAnexo9Estado.ESTADO_RECHAZADA_CODIGO)) {
                deshabilitar = true;
            }
        }
        /*if (estado == RefAnexo9Estado.ESTADO_ANULADA || estado == RefAnexo9Estado.ESTADO_CANCELADA
                || estado == RefAnexo9Estado.ESTADO_CERRADA || estado == RefAnexo9Estado.ESTADO_RECHAZADA) {
            deshabilitar = true;
        }*/
        return deshabilitar;
    }

    public String obtenerColorEmergente(int estado, boolean emergente) {
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(estado, this);
        if(maestro != null){
            if (maestro.getValor().equals(RefAnexo9Estado.ESTADO_PREADMITIDA_CODIGO)
                && emergente) {
                return "red";
            }
        }
        /*if (estado == RefAnexo9Estado.ESTADO_PREADMITIDA
                && emergente) {
            return "red";
        }*/
        return "white";
    }

    public void calcularIMC() {
        //0000877: RefyContraRef  18-02-2022
        if (getObjetoDatosClinicos().getPeso() == null || getObjetoDatosClinicos().getTalla() == null) {
            getObjetoDatosClinicos().setImc(null);
            if (getAccion() == Url.ACCION_EDITAR) {
                PrimeFaces.current().ajax().update("frmEditarDatos:txtIMC");
            } else {
                PrimeFaces.current().ajax().update("frmCrearAfiliado:txtIMC");
            }
        } else if (getObjetoDatosClinicos().getPeso().compareTo(BigDecimal.ZERO) == 0) {
            getObjetoDatosClinicos().setPeso(null);
            getObjetoDatosClinicos().setImc(null);
            if (getAccion() == Url.ACCION_EDITAR) {
                PrimeFaces.current().ajax().update("frmEditarDatos:pnlAntrometricos");
            } else {
                PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAntrometricos");
            }
        } else if (getObjetoDatosClinicos().getTalla() == 0) {
            getObjetoDatosClinicos().setTalla(null);
            getObjetoDatosClinicos().setImc(null);
            if (getAccion() == Url.ACCION_EDITAR) {
                PrimeFaces.current().ajax().update("frmEditarDatos:pnlAntrometricos");
            } else {
                PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAntrometricos");
            }
        } else {
            Double tallaMts = getObjetoDatosClinicos().getTalla().doubleValue() / (100);
            tallaMts = tallaMts * tallaMts;
            Double imc = getObjetoDatosClinicos().getPeso().doubleValue() / tallaMts;
            getObjetoDatosClinicos().setImc(BigDecimal.valueOf(imc));
            if (getAccion() == Url.ACCION_EDITAR) {
                PrimeFaces.current().resetInputs("frmEditarDatos:txtIMC");
                PrimeFaces.current().ajax().update("frmEditarDatos:pnlAntrometricos");
            } else {
                PrimeFaces.current().resetInputs("frmCrearAfiliado:txtIMC");
                PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlAntrometricos");
            }
        }
    }

    public void descargarAdjunto(RefAnexo9Adjunto adjunto) {
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            switch (ext) {
                case ".pdf":
                    ec.setResponseContentType("application/pdf");
                    break;
                case ".PDF":
                    ec.setResponseContentType("application/pdf");
                    break;
                case ".jpeg":
                    ec.setResponseContentType(" image/jpeg");
                    break;
                case ".JPEG":
                    ec.setResponseContentType(" image/jpeg");
                    break;
                case ".jpg":
                    ec.setResponseContentType(" image/jpeg");
                    break;
                case ".JPG":
                    ec.setResponseContentType(" image/jpeg");
                    break;
                case ".doc":
                    ec.setResponseContentType("application/msword");
                    break;
                case ".docx":
                    ec.setResponseContentType("application/msword");
                    break;
                case ".DOC":
                    ec.setResponseContentType("application/msword");
                    break;
                case ".DOCX":
                    ec.setResponseContentType("application/msword");
                    break;
                default:
                    break;
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

    public void consultarServicioHabilitacion(int servicio) {
        try {
            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').show()");
            PrimeFaces.current().ajax().update("frmServiciosHabilitacionBusqueda");
            setServicioHabilitacion(servicio);
        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoServicios() {
        if (getSelServiciosHabilitacionBean().getObjeto().isActivo()) {
            boolean validacion = true;
            if (servicioHabilitacion == SERVICIO_HABILITACION_SOLICITA) {
                getObjeto().setMaServicioSolicitaId(getSelServiciosHabilitacionBean().getObjeto().getId());
                getObjeto().setMaServicioSolicitaCodigo(getSelServiciosHabilitacionBean().getObjeto().getCodigo() + "");
                getObjeto().setMaServicioSolicitaValor(getSelServiciosHabilitacionBean().getObjeto().getNombre());
            } else if (servicioHabilitacion == SERVICIO_HABILITACION_REMITE) {
                getObjeto().setMaServicioRemiteId(getSelServiciosHabilitacionBean().getObjeto().getId());
                getObjeto().setMaServicioRemiteCodigo(getSelServiciosHabilitacionBean().getObjeto().getCodigo() + "");
                getObjeto().setMaServicioRemiteValor(getSelServiciosHabilitacionBean().getObjeto().getNombre());
            }
            
           
            if(getSelServiciosHabilitacionBean().getObjeto().getCodigo() == CODIGO_SERVICIOS_HOBILITACION){
                getObjeto().setHabilitarCodigoCUPSprocedimientoRequerido(false);
                getObjeto().setHabilitarCups(false);
                getObjeto().setMaeMaternoPerinatalId(null);
                getObjeto().setMaeMaternoPerinatalCodigo(null);
                getObjeto().setMaeMaternoPerinatalValor(null);
                getObjeto().setMaeMaternoPerinatalTipo(null);
                getObjeto().setHabilitarMaeMaternoPerinatalId(true);
                validacion = false;
            }else{
                List<MaRelacion> listaMaRelaciones = getReferenciaContraServicio().consultarServiciosHabilitacionId(getSelServiciosHabilitacionBean());
                if(!listaMaRelaciones.isEmpty()){
                    for(MaRelacion maRelacion:listaMaRelaciones){
                        if(maRelacion.getMaId() == getSelServiciosHabilitacionBean().getObjeto().getId() 
                                && maRelacion.getMaCodigo().equals(CODIGO_SERVICIOS_GINECOBOSTETRICIA)){
                            validacion = false;
                            getObjeto().setHabilitarMaeMaternoPerinatalId(true);
                            if(getObjeto().getAsegAfiliado() != null){
                                if(getObjeto().getAsegAfiliado().getEdad() != null){
                                    String[] strEdad = getObjeto().getAsegAfiliado().getEdad().split(" ");
                                    if(getObjeto().getAsegAfiliado().getMaeGeneroCodigo().equalsIgnoreCase("f") 
                                            && Integer.parseInt(strEdad[0]) >= 12 && Integer.parseInt(strEdad[0]) <= 55){
                                        getObjeto().setHabilitarMaeMaternoPerinatalId(false);
                                        getObjeto().setHabilitarCodigoCUPSprocedimientoRequerido(true);
                                        getObjeto().setHabilitarCups(true);
                                        if (getObjeto().getVersion().equals(1)) {
                                            getObjeto().setHabilitarCups(false);
                                        }
                                        getObjeto().setTipoTecnologia(null);
                                        getObjeto().setMaTecnologiaId(null);
                                        getObjeto().setMaTecnologiaCodigo(null);
                                        getObjeto().setMaTecnologiaValor(null);
                                        getObjeto().setCantidadTecnologiaSolicitada(null);
                                        getObjeto().setRequiereContraste(Boolean.FALSE);
                                        getObjeto().setRequiereSedacion(Boolean.FALSE);
                                        getObjeto().setExamenBag(Boolean.FALSE);
                                        getObjeto().setMaeTipoAislamientoId(null);
                                        getObjeto().setMaeTipoAislamientoCodigo(null);
                                        getObjeto().setMaeTipoAislamientoValor(null);
                                        getObjeto().setMaeTipoAislamientoTipo(null);
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
            if(validacion){
                getObjeto().setHabilitarCups(true);
                getObjeto().setHabilitarMaeMaternoPerinatalId(true);
                if (getObjeto().getVersion().equals(1)) {
                    getObjeto().setHabilitarCups(false);
                }
                getObjeto().setHabilitarCodigoCUPSprocedimientoRequerido(true);
                getObjeto().setRequiereContraste(Boolean.FALSE);
                getObjeto().setRequiereSedacion(Boolean.FALSE);
                getObjeto().setExamenBag(Boolean.FALSE);
                getObjeto().setTipoTecnologia(null);
                getObjeto().setMaTecnologiaId(null);
                getObjeto().setMaTecnologiaCodigo(null);
                getObjeto().setMaTecnologiaValor(null);
                getObjeto().setCantidadTecnologiaSolicitada(null);
                getObjeto().setMaeTipoAislamientoId(null);
                getObjeto().setMaeTipoAislamientoCodigo(null);
                getObjeto().setMaeTipoAislamientoValor(null);
                getObjeto().setMaeTipoAislamientoTipo(null);
                getObjeto().setMaeMaternoPerinatalId(null);
                getObjeto().setMaeMaternoPerinatalCodigo(null);
                getObjeto().setMaeMaternoPerinatalValor(null);
                getObjeto().setMaeMaternoPerinatalTipo(null);
            }
            PrimeFaces.current().ajax().update("frmCrearAfiliado:panelCausaMotivaaAtención");
            PrimeFaces.current().ajax().update("frmEditarAfiliado:panelCausaMotivaaAtenciónEditar");
            /*switch (getSelServiciosHabilitacionBean().getObjeto().getCodigo()) {
                case CODIGO_SERVICIOS_HOBILITACION:
                    
                    PrimeFaces.current().ajax().update("frmCrearAfiliado:panelCausaMotivaaAtención");
                    PrimeFaces.current().ajax().update("frmEditarAfiliado:panelCausaMotivaaAtenciónEditar");
                    break;
                case CODIGO_SERVICIOS_GINECOBOSTETRICIA:
                    getObjeto().setHabilitarMaeMaternoPerinatalId(true);
                    if(getObjeto().getAsegAfiliado() != null){
                        if(getObjeto().getAsegAfiliado().getEdad() != null){
                            String[] strEdad = getObjeto().getAsegAfiliado().getEdad().split(" ");
                            if(getObjeto().getAsegAfiliado().getMaeGeneroCodigo().equalsIgnoreCase("f") 
                                    && Integer.parseInt(strEdad[0]) >= 12 && Integer.parseInt(strEdad[0]) <= 55){
                                getObjeto().setHabilitarMaeMaternoPerinatalId(false);
                            }
                        }
                    }
                    PrimeFaces.current().ajax().update("frmCrearAfiliado:panelCausaMotivaaAtención");
                    PrimeFaces.current().ajax().update("frmEditarAfiliado:panelCausaMotivaaAtenciónEditar");
                    break;
                default:
                    getObjeto().setHabilitarCups(true);
                    getObjeto().setHabilitarMaeMaternoPerinatalId(true);
                    if(getObjeto().getVersion().equals(1)){
                        getObjeto().setHabilitarCups(false);
                    }   getObjeto().setHabilitarCodigoCUPSprocedimientoRequerido(true);
                    getObjeto().setRequiereContraste(Boolean.FALSE);
                    getObjeto().setRequiereSedacion(Boolean.FALSE);
                    getObjeto().setExamenBag(Boolean.FALSE);
                    getObjeto().setTipoTecnologia(null);
                    getObjeto().setMaTecnologiaId(null);
                    getObjeto().setMaTecnologiaCodigo(null);
                    getObjeto().setMaTecnologiaValor(null);
                    getObjeto().setCantidadTecnologiaSolicitada(null);
                    getObjeto().setMaeTipoAislamientoId(null);
                    getObjeto().setMaeTipoAislamientoCodigo(null);
                    getObjeto().setMaeTipoAislamientoValor(null);
                    getObjeto().setMaeTipoAislamientoTipo(null);
                    getObjeto().setMaeMaternoPerinatalId(null);
                    getObjeto().setMaeMaternoPerinatalCodigo(null);
                    getObjeto().setMaeMaternoPerinatalValor(null);
                    getObjeto().setMaeMaternoPerinatalTipo(null);
                    PrimeFaces.current().ajax().update("frmCrearAfiliado:panelCausaMotivaaAtención");
                    PrimeFaces.current().ajax().update("frmEditarAfiliado:panelCausaMotivaaAtenciónEditar");
                    break;
            }*/
            
            getSelServiciosHabilitacionBean().setObjeto(new MaServicioHabilitacion());
            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').hide()");
            if (getDialogo().equals(DIALOGO_CREAR)) {
                if (servicioHabilitacion == SERVICIO_HABILITACION_REMITE) {
                    PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlServicios");
                } else if (servicioHabilitacion == SERVICIO_HABILITACION_SOLICITA) {
                    PrimeFaces.current().ajax().update("frmCrearAfiliado:pnlServicios");
                }
            } else if (getDialogo().equals(DIALOGO_EDITAR)) {
                PrimeFaces.current().ajax().update("frmEditarDatos");
            }
        } else {
            this.addError("El servicio seleccionado no esta activo.");
            generarMensajes();
        }
    }

    public StreamedContent generarReporte(String id, Integer version) {
        getObjeto().setId(Integer.parseInt(id));
        StreamedContent streamedContent2 = null;
        try {
            super.setAccion(ACCION_ADICIONAL_4);
            getReferenciaContraServicio().Accion(this);
            if (getReporte().getIntNumeroSolicitud() != null) {
                listaReporte = new ArrayList<>();
                listaReporte.add(getReporte());
                //Estrucutra de JasperReport
                JRBeanCollectionDataSource beanColDataSource = null;
                InputStream is = null;
                if (version.equals(0)){
                    is = getClass().getResourceAsStream("/reportes/anexo_tecnico.jasper");
                    beanColDataSource = new JRBeanCollectionDataSource(listaReporte);
                }else{
                    is = getClass().getResourceAsStream("/reportes/anexo_tecnico_2335.jasper");
                    beanColDataSource = new JRBeanCollectionDataSource(listaReporte);
                }
                
                Map<String, Object> parameters = new HashMap<>();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                parameters.put(ReporteReferencia.LISTA_DIAGNOSTICOS, new JRBeanCollectionDataSource((Collection<?>) getReporte().getListaDiagnosticosIngreso()));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                String nombreReporte = "";
                if(getObjeto().getVersion().equals(1)){
                    if (getObjeto().getTipo() == RefAnexo9.TIPO_SOLICITUD_REFERENCIA) {
                        nombreReporte = "anexo1-9-";
                    } else if (getObjeto().getTipo() == RefAnexo9.TIPO_SOLICITUD_CONTRAREFERENCIA) {
                        nombreReporte = "anexo1-10-";
                    }
                }else{
                    if (getObjeto().getTipo() == RefAnexo9.TIPO_SOLICITUD_REFERENCIA) {
                        nombreReporte = "anexo9-";
                    } else if (getObjeto().getTipo() == RefAnexo9.TIPO_SOLICITUD_CONTRAREFERENCIA) {
                        nombreReporte = "anexo10-";
                    }
                }
                nombreReporte = nombreReporte + getObjeto().getId() + "_" + FORMATO_REPORTE.format(getObjeto().getFechaHoraCrea()) + ".pdf";
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombreReporte).build();
            } else {
                this.addError("Error no hay datos para generar el formulario");
                generarMensajes();
            }
        } catch (JRException ex) {
            streamedContent2 = null;
            this.addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        }
        return streamedContent2;
    }

    public StreamedContent generarReporteContraReferencia(String id) {
        getObjeto().setId(Integer.parseInt(id));
        StreamedContent streamedContent2 = null;
        try {
            super.setAccion(ACCION_ADICIONAL_5);
            getReferenciaContraServicio().Accion(this);
            if (getReporteReferenciaContraReferencia().getIntNumeroSolicitud() != null) {
                listaReporteReferenciaContraReferencia = new ArrayList<>();
                listaReporteReferenciaContraReferencia.add(getReporteReferenciaContraReferencia());
                //Estrucutra de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/ReferenciaContraReferencias.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReporteReferenciaContraReferencia);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                parameters.put(RefAnexo9.LISTA_DIAGNOSTICOS, new JRBeanCollectionDataSource((Collection<?>) listaReporteReferenciaContraReferencia.get(0).getListaDiagnosticos()));
                parameters.put(RefAnexo9.LISTA_GESTION, new JRBeanCollectionDataSource((Collection<?>) listaReporteReferenciaContraReferencia.get(0).getListaRefAnexo9Gestion()));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                String nombreReporte = "";
                if (getObjeto().getTipo() == RefAnexo9.TIPO_SOLICITUD_REFERENCIA) {
                    nombreReporte = "anexo9-";
                } else if (getObjeto().getTipo() == RefAnexo9.TIPO_SOLICITUD_CONTRAREFERENCIA) {
                    nombreReporte = "anexo10-";
                }
                nombreReporte = getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo() + "_" + getObjeto().getAsegAfiliado().getNumeroDocumento() + "_" + nombreReporte + getObjeto().getId() + "_" + FORMATO_REPORTE.format(getObjeto().getFechaHoraCrea()) + ".pdf";
                //streamedContent2 = new DefaultStreamedContent(stream, "application/pdf", nombreReporte);
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").name(nombreReporte + ".pdf").stream(() -> stream).build();
            } else {
                this.addError("Error no hay datos para generar el formulario");
                generarMensajes();
            }
        } catch (JRException ex) {
            streamedContent2 = null;
            this.addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        }
        return streamedContent2;
    }

    public void validarGestion() {
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(objetoGestion.getMaeTipoId(), this);
        if(maestro != null){
            if (maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)) {
                if (!this.validarTransporte()) {
                    PrimeFaces.current().executeScript("PF('dialogoConfirmarTransporte').show()");
                }
                //objetoGestion.setMaeMotivoId(REMITIDO);
                inhabilitarMotivo = false;
            }else{
                objetoGestion.setMaeMotivoId(0);
                inhabilitarMotivo = false;
            }
        }
        /*if (objetoGestion.getMaeTipoId() == RefAnexo9Gestion.ESTADO_CERRADA) {
            if (!this.validarTransporte()) {
                PrimeFaces.current().executeScript("PF('dialogoConfirmarTransporte').show()");
            }
            objetoGestion.setMaeMotivoId(REMITIDO);
            inhabilitarMotivo = true;
        } else {
            objetoGestion.setMaeMotivoId(0);
            inhabilitarMotivo = false;
        }*/
        setListaGestionarMotivos(
                getReferenciaContraServicio().motivosGestionEstado(this)
        );
    }
    
    public boolean validarFechaHoraEgresoGestion() {
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(objetoGestion.getMaeTipoId(), this);
        if(maestro != null){
            if(maestro.contieneAccion(MaestroAccion.CR_GESTION_TIPO_FECHA_HORA_EGRESO) 
                    && RefAnexo9.ID_SERVICIO_HABILIDADO.equals(objeto.getMaServicioSolicitaId())){
                return true;
            }
           
        }
        return false;
    }
    
    public boolean validarCampoOblicatorioFechaHoraEgresoGestion() {
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(objetoGestion.getMaeTipoId(), this);
        if(maestro != null){
            if(maestro.contieneAccion(MaestroAccion.CR_GESTION_TIPO_FECHA_HORA_EGRESO)){
                return true;
            }
        }
        return false;
    }
    
    public boolean validarFechaReferencia() {
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(objetoGestion.getMaeTipoId(), this);
        if(maestro != null){
            if (maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_DIRECCIONA_CODIGO)) {
                 return true;
            }else if(maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_GESTION_NOTA_CODIGO)){
                 return true;
            }
        }
        /*if (objetoGestion.getMaeTipoId() == RefAnexo9Gestion.ESTADO_DIRECCIONA) {
            return true;
        } else if (objetoGestion.getMaeTipoId() == RefAnexo9Gestion.ESTADO_GESTION_NOTA) {
            return true;
        }*/
        return false;
    }
    
    public boolean validarCampoOblicatorioFechaHoraAceptacion() {
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(objetoGestion.getMaeTipoId(), this);
        if(maestro != null){
            if (maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_DIRECCIONA_CODIGO)) {
                return true;
            }else if(maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_GESTION_NOTA_CODIGO)){
                return false;
            }
        }
        /*if (objetoGestion.getMaeTipoId() == RefAnexo9Gestion.ESTADO_DIRECCIONA) {
            return true;
        } else if (objetoGestion.getMaeTipoId() == RefAnexo9Gestion.ESTADO_GESTION_NOTA) {
            return false;
        }*/
        return false;
    }

    public void filtrarMotivoByEstado() {
        try {
            getListaMotivoGestionFiltro().addAll(getListaMotivoGestion());
            if (!getRegistros().isEmpty()) {
                if (getRegistros().get(0).getRefAnexo9Gestion() != null) {
                    int estado = getRegistros().get(0).getRefAnexo9Gestion().getMaeTipoId();
                    objetoGestion = new RefAnexo9Gestion();
                    Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(estado, this);
                    if(maestro != null){
                        if(maestro.getTipo().equals(RefAnexo9Gestion.ESTADO_GESTION_TIPO) && maestro.getValor().equals(RefAnexo9Gestion.ESTADO_CERRADA_CODIGO)){
                            objetoGestion.setMaeMotivoId(REMITIDO);
                        }else{
                            objetoGestion.setMaeMotivoId(0);
                        }
                    }else{
                        objetoGestion.setMaeMotivoId(0);
                    }
                    /*if (estado == RefAnexo9Gestion.ESTADO_CERRADA) {
                        objetoGestion.setMaeMotivoId(REMITIDO);
                    } else {
                        objetoGestion.setMaeMotivoId(0);
                    }*/
                    getObjetoGestion().setMaeTipoId(estado);
                    setListaMotivoGestionFiltro(
                            getReferenciaContraServicio().motivosGestionEstado(this)
                    );
                }
            }

//            PrimeFaces.current().executeScript("PF('tablaWidget').clearFilters()");
//              PrimeFaces.current().resetInputs("frmReferencia:tablaRegistros:filtroMotivo");
        } catch (Exception e) {
            this.addError(e.getMessage());
            generarMensajes();
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                    PrimeFaces.current().resetInputs("frmVer");
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().ajax().update("frmAuditoriaVer:labelDatosAuditoria");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().ajax().update("frmCrearAfiliado");
                            PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                            break;
                    }
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().resetInputs("frmEditarAfiliado");
                    PrimeFaces.current().ajax().update("frmEditarAfiliado");
                    PrimeFaces.current().resetInputs("frmEditarProfes");
                    PrimeFaces.current().ajax().update("frmEditarProfes");
                    PrimeFaces.current().resetInputs("frmEditarDatos");
                    PrimeFaces.current().ajax().update("frmEditarDatos");
                    PrimeFaces.current().ajax().update("frmAuditoriaEditar:labelDatosAuditoria");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_BORRAR:
//                            PrimeFaces.current().ajax().update("frmEditarDatos:tablaDiagnosticos");
                            crearLog("Borrar Diagnostico", getObjetoDiagnosticoBorrar().toString());
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmReferencia");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmReferencia");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmReferencia");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().resetInputs("frmGestionarVer");
                            PrimeFaces.current().ajax().update("frmGestionarVer");
                            PrimeFaces.current().ajax().update("frmAuditoriaGestionar:labelDatosAuditoria");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            crearLog("Ver Gestión", getObjetoGestion().toString());
                            break;
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmAgregarGestion");
                            PrimeFaces.current().ajax().update("frmAgregarGestion");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarGestion').show()");
                            crearLog("Crear Gestión", "Creación de Registro");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmGestionarVer");
                            PrimeFaces.current().ajax().update("frmGestionarVer:btnGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarGestion').hide()");
                            crearLog("Guardar Gestión", getObjetoGestion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().resetInputs("frmAdjuntoG");
                            PrimeFaces.current().ajax().update("frmAdjuntoG");
                            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmGestionarVer:tablaAdjuntosGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').hide()");
                            PrimeFaces.current().resetInputs("frmReferencia");
                            PrimeFaces.current().ajax().update("frmReferencia");
                            crearLog("Guardar Gestión Adjunto", getObjetoAdjunto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmAgregarTrans");
                            PrimeFaces.current().ajax().update("frmAgregarTrans");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarTransporte').show()");
                            crearLog("Crear Transporte", "Creación de Registro");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmTransporteVer");
                            crearLog("Guardar Transporte", getObjetoTransporte().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().resetInputs("frmAgregarSeguimiento");
                            PrimeFaces.current().ajax().update("frmAgregarSeguimiento");
                            PrimeFaces.current().executeScript("PF('dialogoSeguimiento').show()");
                            crearLog("Crear Seguimiento", getObjetoTransporteSeguimiento().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmAgregarSeguimiento");
                            crearLog("Guardar Seguimiento", getObjetoTransporteSeguimiento().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().resetInputs("frmVerSeguimiento");
                            PrimeFaces.current().ajax().update("frmVerSeguimiento");
                            PrimeFaces.current().executeScript("PF('dialogoVerSeguimiento').show()");
                            crearLog("Ver Seguimiento", getObjetoTransporte().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("referenciaContraRefBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("referenciaContraRefBean");
    }

    private void validarDiagnosticoPrincipal() {
        boolean principal = false;
        for (RefAnexo9Diagnostico diagnostico : getObjeto().getListaRefAnexo9Diagnostico()) {
            if (diagnostico.isPrincipal()) {
                principal = true;
            }
        }
        if (!principal && !getObjeto().getListaRefAnexo9Diagnostico().isEmpty()) {
            getObjeto().getListaRefAnexo9Diagnostico().get(0).setPrincipal(true);
        }
    }

    public void agregarTransporteGestionCerrado() {
        PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
        PrimeFaces.current().executeScript("PF('dialogoAgregarGestion').hide()");
        PrimeFaces.current().executeScript("PF('dialogoConfirmarTransporte').hide()");
        this.transporte(getObjeto().getId());
    }

    private boolean validarTransporte() {
//        this.setParamConsulta5(new ParamConsulta());
        this.getParamConsulta(6).setEmpresaId(super.getConexion().getEmpresa().getId());
        this.getParamConsulta(6).setParametroConsulta1(getObjeto().getId());
        this.getParamConsulta(6).setPrimerRegistro(0);
        this.getParamConsulta(6).setRegistrosPagina(1);
        this.getParamConsulta(6).setFiltros(new HashMap<>());
        this.getReferenciaContraServicio().consultarTransporte(this);

        if (this.getRegistrosRefTransportes().isEmpty()) {
            this.addError("Error debe agregar un transporte antes cambiar el estado a cerrado");
            generarMensajes();
            return false;
        }
        return true;
    }

    public void validarClaseTransporte() {
        getReferenciaContraServicio().claseTransporteTipo(this);
    }

    public boolean validarProovedorTransporte() {
        if (getMaeClaseTransporte().contieneAccion(MaestroAccion.REF_CLASE_TRANSPORTE_CON_PROVEEDOR)) {
            return false;
        } else {
            getObjetoTransporte().setCntPrestadorSede(null);
            return true;
        }
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public void mostrarMensaje(String desc) {
        setObservacionGenerica(desc);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void consultarProcedimiento() {
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void cerrarDialogoTecnologia() {
        MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
        getObjeto().setTipoTecnologia(AuConstantes.ID_TECNOLOGIA);
        getObjeto().setMaTecnologiaId(tecnologia.getId());
        getObjeto().setMaTecnologiaCodigo(tecnologia.getCups());
        getObjeto().setMaTecnologiaValor(tecnologia.getCupsDescipcion());
        getObjeto().setCantidadTecnologiaSolicitada(1);
        PrimeFaces.current().ajax().update("frmCrearAfiliado:labelTecnologia");
        PrimeFaces.current().ajax().update("frmCrearAfiliado:labelCantidad");
        PrimeFaces.current().ajax().update("frmEditarDatos:labelTecnologia");
        PrimeFaces.current().ajax().update("frmEditarDatos:labelCantidad");
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
        PrimeFaces.current().executeScript("PF('dialogoProcedimiento').show()");
    }

    public void agregarProcedimiento() {
        try {
            if(getObjeto().getCantidadTecnologiaSolicitada() < 1){
                this.addError("Error la cantidad tiene que se mayor a 0");
            }
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide();");
                PrimeFaces.current().executeScript("PF('dialogoProcedimiento').hide();");
                PrimeFaces.current().ajax().update("frmCrearAfiliado:labelCantidad");
                PrimeFaces.current().ajax().update("frmEditarDatos:labelCantidad");
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar Procedimiento");
        }
        generarMensajes();
    }
    
    public String colorGestion(int maeEstadoId, Date fechaHoraUltimaGestion){
        
        String color = "white";
        LocalTime horaMañana = LocalTime.parse(SEMAFORO_HORA_MANANA);
        LocalTime horaTarde = LocalTime.parse(SEMAFORO_HORA_TARDE);
        LocalTime horaCambio = LocalTime.parse(SEMAFORO_HORA_CAMBIO);
        String stringHoraActual = LocalTime.now().toString();
        LocalTime horaActual = LocalTime.parse(stringHoraActual.substring(0, 8));
        LocalTime horaUltimaGestion;
        //LocalTime horaActual = LocalTime.parse("19:10:11");
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(maeEstadoId, this);
        if(maestro != null){
            if(maestro.contieneAccion(MaestroAccion.CR_A9_ESTADO_SEMAFORO_GESTION)){
                if(calcularDiasUltimaGestion(fechaHoraUltimaGestion) >= 1){
                    color = "green";
                    return color;
                }
                if(horaActual.isAfter(horaMañana) && horaActual.isBefore(horaTarde)){
                    if(fechaHoraUltimaGestion != null){
                        String stringFechaUltimaNota = fechaHoraUltimaGestion.toString().substring(11, 19);
                        horaUltimaGestion = LocalTime.parse(stringFechaUltimaNota);
                        if(horaUltimaGestion.isAfter(horaMañana) && horaUltimaGestion.isBefore(horaActual)){
                            color = "white";
                        }else{
                            color = "green";
                        }
                    }
                }else if(horaActual.isAfter(horaTarde) && horaCambio.isBefore(horaActual)){
                    if(fechaHoraUltimaGestion != null){
                        String stringFechaUltimaNota = fechaHoraUltimaGestion.toString().substring(11, 19);
                        horaUltimaGestion = LocalTime.parse(stringFechaUltimaNota);
                        if(horaUltimaGestion.isAfter(horaTarde) && horaUltimaGestion.isBefore(horaActual)){
                            color = "white";
                        }else{
                            color = "green";
                        }
                    }
                }
            }
        }
        return color;
    }
    
    public int calcularDiasUltimaGestion(Date fechaUltimaGestion) {
        Long fechaInicio;
        Long fechaFin;
        if (fechaUltimaGestion != null) {
            fechaInicio = fechaUltimaGestion.getTime();
        } else {
            return 0;
        }
        fechaFin = new Date().getTime();
        int milisecondsByDay = 86400000;
        return (int) ((fechaFin - fechaInicio) / milisecondsByDay);
    }
    
    public String colorEvolucion(int maeEstadoId, Date fechaHoraUltimaGestion, Date fechaHoraAdjuntoEvolucion){
        
        String color = "white";
        LocalTime horaMañana = LocalTime.parse(SEMAFORO_HORA_MANANA);
        LocalTime horaTarde = LocalTime.parse(SEMAFORO_HORA_TARDE);
        LocalTime horaCambio = LocalTime.parse(SEMAFORO_HORA_CAMBIO);
        String stringHoraActual = LocalTime.now().toString();
        LocalTime horaActual = LocalTime.parse(stringHoraActual.substring(0, 8));
        LocalTime horaAdjuntoEvolucion;
        Maestro maestro = getReferenciaContraServicio().consultarMaestroGestionEstado(maeEstadoId,this);
        if(maestro != null){
            if(maestro.contieneAccion(MaestroAccion.CR_A9_ESTADO_SEMAFORO_GESTION)){
                if(fechaHoraUltimaGestion != null && fechaHoraAdjuntoEvolucion  == null){
                    color = "blue";
                    return color;
                }
                
                if(calcularDiasUltimaEvolucion(fechaHoraAdjuntoEvolucion) >= 1){
                    color = "blue";
                    return color;
                }
                    
                if(horaActual.isAfter(horaMañana) && horaActual.isBefore(horaTarde)){
                    if(fechaHoraAdjuntoEvolucion != null){
                        String stringFechaAdjuntoEvolucion = fechaHoraAdjuntoEvolucion.toString().substring(11, 19);
                        horaAdjuntoEvolucion = LocalTime.parse(stringFechaAdjuntoEvolucion);
                        if(horaAdjuntoEvolucion.isAfter(horaMañana) && horaAdjuntoEvolucion.isBefore(horaActual)){
                            color = "white";
                        }else{
                            color = "blue";
                        }
                    }
                }else if(horaActual.isAfter(horaTarde) && horaCambio.isBefore(horaActual)){
                    if(fechaHoraAdjuntoEvolucion != null){
                        String stringFechaAdjuntoEvolucion = fechaHoraAdjuntoEvolucion.toString().substring(11, 19);
                        horaAdjuntoEvolucion = LocalTime.parse(stringFechaAdjuntoEvolucion);
                        if(horaAdjuntoEvolucion.isAfter(horaTarde) && horaAdjuntoEvolucion.isBefore(horaActual)){
                            color = "white";
                        }else{
                            color = "blue";
                        }
                    }
                }
            }
        }
        return color;
    }
    
    public String colorMaternoPerinatal(int maeEstadoId, String maeMaternoPerinatalCodigo, String motivoCodigo){
        
        String color = "white";
        if(maeMaternoPerinatalCodigo != null && !maeMaternoPerinatalCodigo.isBlank()){
            color = "orange";
            if(maeMaternoPerinatalCodigo.equals(RefAnexo9.MAESTRO_CODIGO_MATERNO_PERINATAL)){
                color = "white";
                return color;
            }
            Maestro maestroEstado = getReferenciaContraServicio().consultarMaestroGestionEstado(maeEstadoId, this);
            if (maestroEstado != null) {
                if (maestroEstado.getValor().equals(RefAnexo9.ESTADO_CERRADA) && motivoCodigo.equals(RefAnexo9Gestion.ESTADO_MOTIVO_REMITIDO)) {
                    color = "white";
                } else if (maestroEstado.getValor().equals(RefAnexo9.ESTADO_CANCELADA) || maestroEstado.getValor().equals(RefAnexo9.ESTADO_ANAULADA)) {
                    color = "white";
                } 
            }
            
        }
        return color;
    }
    
    public int calcularDiasUltimaEvolucion(Date fechaHoraAdjuntoEvolucion) {
        Long fechaInicio;
        Long fechaFin;
        if (fechaHoraAdjuntoEvolucion != null) {
            fechaInicio = fechaHoraAdjuntoEvolucion.getTime();
        } else {
            return 0;
        }  
        fechaFin = new Date().getTime();
        int milisecondsByDay = 86400000;
        return (int) ((fechaFin - fechaInicio) / milisecondsByDay);
    }
    
    private void establecerSemaforoServicioGestionados(RefAnexo9 anexo9) {
        RefAnexo9Gestion gestion = null;
        switch (anexo9.getMaeEstadoCodigo()) {
            case CrueConstantes.MAE_CODIGO_ESTADO_ADMITIDO:
            case CrueConstantes.MAE_CODIGO_ESTADO_PRE_ADMITIDO:
                break;
            case CrueConstantes.MAE_CODIGO_ESTADO_DIRECCIONADA:
                //se consulta el ultimo motivo
                gestion = referenciaContraServicio.consultarUltimaGestionAnexo9(anexo9);
                if (gestion.getMaeTipoCodigo().equals(CrueConstantes.MAE_CODIGO_TIPO_GESTION_NOTA)) {
                    this.verficarUltimoDireccionamiento(anexo9);
                    break;
                }
                if (gestion.getMaeMotivoCodigo().equals(CrueConstantes.MAE_CODIGO_MOTIVO_AGENDADA)) {
                    anexo9.setSemaforoAgendamiento(1);
                }
                break;
            case CrueConstantes.MAE_CODIGO_ESTADO_CERRADA:
                //verificamos el direccionamiento
                this.verficarUltimoDireccionamiento(anexo9); 
                //se consulta el ultimo motivo 
                gestion = referenciaContraServicio.consultarUltimaGestionAnexo9(anexo9);
                if (gestion.getMaeMotivoCodigo().equals(CrueConstantes.MAE_CODIGO_MOTIVO_EFECTIVA)) {
                    anexo9.setSemaforoEfectividad(1);
                }
                break;
            case CrueConstantes.MAE_CODIGO_ESTADO_ANULADA:
            case CrueConstantes.MAE_CODIGO_ESTADO_CANCELADA:
                this.verficarUltimoDireccionamiento(anexo9); 
                break;

            default:
                break;
        }
    }
    
    public boolean validarSemaforoServicioGestionados(RefAnexo9 anexo9) {
        if (anexo9.getMaServicioSolicitaCodigo() != null && 
                    anexo9.getMaServicioSolicitaCodigo().equals(CrueConstantes.MA_CODIGO_SERVICIO_H_RADIOLOGIA_IMAGENES_DIAGNOSTICAS)) {
            this.establecerSemaforoServicioGestionados(anexo9);
            return true;
        }
        return false;
    }

    private void verficarUltimoDireccionamiento(RefAnexo9 anexo9) {
        RefAnexo9Gestion gestionDireccionamiento = referenciaContraServicio.consultarUltimoDireccionamiento(anexo9);
        if (gestionDireccionamiento != null && gestionDireccionamiento.getMaeMotivoCodigo().equals(CrueConstantes.MAE_CODIGO_MOTIVO_AGENDADA)) {
            anexo9.setSemaforoAgendamiento(1);
        }
    }

}
