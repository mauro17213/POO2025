package com.saviasaludeps.savia.web.crue.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2DatoAtencion;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Diagnostico;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Gestion;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Semaforo;
import com.saviasaludeps.savia.dominio.crue.ReporteAnexo2;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuReporte;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorSedesBean;
import com.saviasaludeps.savia.web.crue.servicio.SolicitudesUrgenciasIface;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_LISTAR;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
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
 * @author AlexanderDiaz
 */
@Named
@ViewScoped
public class SolicitudesUrgenciasBean extends Url {

    @Autowired
    private SolicitudesUrgenciasIface solicitudesUrgenciasServicio;
    private AuAnexo2 objeto;
    private AuAnexo2Rescate objetoRescate;
    private List<AuAnexo2> registros;
    private LazyDataModel<AuAnexo2> lazyRegistros;
    private AuSolicitudAdjunto objetoAdjunto;
    private AuAnexo2DatoAtencion objetoDatosClinicos;
    private AuAnexo2Gestion objetoGestion;
    private CntProfesionalPrestador objetoProfesionalPrestador;
    private SelDiagnosticosBean selDiagnosticosBean;
    private List<AsegAfiliado> registrosAfiliados;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
    private List<CntPrestadorSede> registroIPS;
    private LazyDataModel<CntPrestadorSede> lazyIPS;
    private LazyDataModel<CntPrestadorSede> lazyIPSAtencion;
    private List<AuAnexo2Gestion> registrosRefAnexos9Gestion;
    private LazyDataModel<AuAnexo2Gestion> lazyGestion;

    private Date fechaActual;
    private HashMap<Integer, Maestro> hashRegimen;
    private List<Maestro> listaRegimen;
    private List<Maestro> listaAmbitoAtencion;
    private HashMap<String, Maestro> hashManejoIntegral;
    private List<Maestro> listaManejoIntegral;
    private List<Maestro> listaTiposDocumento;
    private List<Maestro> listaTiposDocumentoPersona;
    private HashMap<Integer, Maestro> hashTiposDocumentoProfesional;
    private List<Maestro> listaTiposDocumentoProfesional;
    private List<Maestro> listaTiposGenero;

    private HashMap<Integer, Maestro> hashOrigen;
    private List<Maestro> listaOrigen;
    private HashMap<Integer, Maestro> hashDestino;
    private List<Maestro> listaDestino;
    private HashMap<Integer, Maestro> hashDestinoTotal;
    private List<Maestro> listaDestinoTotal;
    private int diasVigencia;
    private boolean mostrarBorrarDocumento;
    private String nombreDocumento;
    private String observacionGenerica;

    private boolean disableRemite;
    private boolean isActivoAfiliado;

    private HashMap<String, MaTecnologia> hashMaTecnologias;
    private List<MaTecnologia> listaMaTecnologias;
    private HashMap<String, MaServicioHabilitacion> hashMaServicioHabilitacionItems;
    private List<MaServicioHabilitacion> listaMaServicioHabilitacionItems;

    private List<Maestro> listaTipoDiagnostico;
    private HashMap<Integer, Maestro> hashTipoDiagnostico;
    private HashMap<Integer, Ubicacion> hashUbicacion;
    private List<Ubicacion> listaUbicacion;
    private List<Maestro> listaEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private List<MaServicioHabilitacion> listaMaServicioHabilitacion;
    private HashMap<Integer, MaServicioHabilitacion> hashMapMaServicioHabilitacion;
    private List<Maestro> listaTipoGestion;
    private HashMap<Integer, Maestro> hashTipoGestion;
    private List<Maestro> listaCondicionDestino;
    private HashMap<Integer, Maestro> hashCondicionDestino;
    private List<Maestro> listaViaIngreso;
    private HashMap<Integer, Maestro> hashViaIngreso;
    private List<Maestro> listaMotivoGestion;
    private HashMap<Integer, Maestro> hashMotivoGestion;
    private List<Maestro> listaTipoAdjunto;
    private HashMap<Integer, Maestro> hashTipoAdjunto;
    private UploadedFile archivoAdjunto;
    private SelEspecialidadesBean selEspecialidadesBean;
    private String dialogo;
    private String colorEstadoAfiliado;
    private List<AuAnexo2Semaforo> listaAuAnexo2Semaforo;
    private SelPrestadorSedesBean sedesBean;
    private Integer[] insumosSeleccionados;
    private List<Maestro> listaTipoSeguimiento;
    private HashMap<Integer, Maestro> hashTipoSeguimiento;
    private Ubicacion objetoUbicacionAcomp;
    private Integer tipoImpresion;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaEstadosAfiliado;
    private HashMap<Integer, Maestro> hashEstadosAfiliado;

    private DefaultStreamedContent streamReportePdf;

    private String codigosUrgenciasMedica;
    private String codigosUrgenciasOdontologica;
    private String codigosServicioHabilitacion;

    private String telefonoFijoAfiliado;
    private String telefonoMovilAfiliado;

    public final static int MAXIMO_DIAGNOSTICOS = 3;
    public final static String AMBITO_URGENCIA = "Urgencia";
    public final static String AMBITO_URGENCIA_VALOR = "H";

    public final static String DIALOGO_CREAR = "Crear";
    private final static String DIALOGO_EDITAR = "Editar";
    private final static String DIALOGO_GESTIONAR = "Gestionar";

    public final static String ACCION_EVENTO_VER = "ver";
    public final static String ACCION_EVENTO_GESTION = "gestion";
    public final static String AU4_OBSERVACION_1 = "Autorización Automatica";
    public final static String AU4_OBSERVACION_0 = "Autorización Aplicación";

    public final static String CODIGO_ENTIDAD_REGIMEN_SUBSIDIADO = "EPSS40";
    public final static String CODIGO_ENTIDAD_REGIMEN_CONTRIBUTIVO = "EPS040";
    public final static String REGIMEN_SUBSIDIADO = "1";
    public final static String CODIGO_SERVICIO_HABILITACION_SERVICIO_URGENCIAS = "501";
    public final static String CODIGO_SERVICIO_HABILITACION_OODONTOLOGIA = "334";
    public final static String APROBACION_ANEXO2 = "Aprobación automática";
    public final static String CARGO_AUTORIZA = "AUTORIZADOR SAVIASALUD";
    public final static String EPS_TELEFONO = "018000423683";
    public final static SimpleDateFormat FORMATO_REPORTE = new SimpleDateFormat("YYYYMMddHHmmss");
    private String mensajeLogImprimir;
    private Date fechaMinima;

    

    //private String accionEvento;
    public SolicitudesUrgenciasBean() {
        fechaMinima = restarDiasFecha(new Date(), 30);
        this.objeto = new AuAnexo2();
        this.objetoAdjunto = new AuSolicitudAdjunto();
        fechaActual = new Date();
        Modulo mod = super.validarModulo(Modulo.ID_SOLICITUDES_URGENCIAS);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyRegistros = new LazyDataModel<AuAnexo2>() {
                private List<AuAnexo2> listaAuAnexo2;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(0).getCantidadRegistros();
                }

                @Override
                public List<AuAnexo2> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaAuAnexo2 = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return listaAuAnexo2;
                }

                @Override
                public String getRowKey(AuAnexo2 auAnexo2) {
                    return auAnexo2.getId().toString();
                }

                @Override
                public AuAnexo2 getRowData(String auAnexo2Id) {
                    Integer id = Integer.valueOf(auAnexo2Id);
                    for (AuAnexo2 auAnexo2 : listaAuAnexo2) {
                        if (id.equals(auAnexo2.getId())) {
                            return auAnexo2;
                        }
                    }
                    return null;
                }
            };
            //afiliados
            lazyAfiliados = new LazyDataModel<AsegAfiliado>() {

                private List<AsegAfiliado> afiliados;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(1).getCantidadRegistros();
                }

                @Override
                public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
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
            //listar ips
            lazyIPS = new LazyDataModel<CntPrestadorSede>() {
                private List<CntPrestadorSede> listaIPS;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(2).getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
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
        }
    }

    @PostConstruct
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getSolicitudesUrgenciasServicio().cargaInicial(this);
        listar();
    }

    public AuAnexo2 getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo2 objeto) {
        this.objeto = objeto;
    }

    public List<AuAnexo2> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo2> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo2> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<AuAnexo2> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashRegimen() {
        return hashRegimen;
    }

    public void setHashRegimen(HashMap<Integer, Maestro> hashRegimen) {
        this.hashRegimen = hashRegimen;
    }

    public List<Maestro> getListaRegimen() {
        return listaRegimen;
    }

    public void setListaRegimen(List<Maestro> listaRegimen) {
        this.listaRegimen = listaRegimen;
    }

    public List<Maestro> getListaAmbitoAtencion() {
        return listaAmbitoAtencion;
    }

    public void setListaAmbitoAtencion(List<Maestro> listaAmbitoAtencion) {
        this.listaAmbitoAtencion = listaAmbitoAtencion;
    }

    public HashMap<String, Maestro> getHashManejoIntegral() {
        return hashManejoIntegral;
    }

    public void setHashManejoIntegral(HashMap<String, Maestro> hashManejoIntegral) {
        this.hashManejoIntegral = hashManejoIntegral;
    }

    public List<Maestro> getListaManejoIntegral() {
        return listaManejoIntegral;
    }

    public void setListaManejoIntegral(List<Maestro> listaManejoIntegral) {
        this.listaManejoIntegral = listaManejoIntegral;
    }

    public List<Maestro> getListaCondicionDestino() {
        return listaCondicionDestino;
    }

    public void setListaCondicionDestino(List<Maestro> listaCondicionDestino) {
        this.listaCondicionDestino = listaCondicionDestino;
    }

    public HashMap<Integer, Maestro> getHashCondicionDestino() {
        return hashCondicionDestino;
    }

    public void setHashCondicionDestino(HashMap<Integer, Maestro> hashCondicionDestino) {
        this.hashCondicionDestino = hashCondicionDestino;
    }

    public List<Maestro> getListaViaIngreso() {
        return listaViaIngreso;
    }

    public void setListaViaIngreso(List<Maestro> listaViaIngreso) {
        this.listaViaIngreso = listaViaIngreso;
    }

    public HashMap<Integer, Maestro> getHashViaIngreso() {
        return hashViaIngreso;
    }

    public void setHashViaIngreso(HashMap<Integer, Maestro> hashViaIngreso) {
        this.hashViaIngreso = hashViaIngreso;
    }

    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
    }

    public AuAnexo2Rescate getObjetoRescate() {
        return objetoRescate;
    }

    public void setObjetoRescate(AuAnexo2Rescate objetoRescate) {
        this.objetoRescate = objetoRescate;
    }

    public HashMap<Integer, Maestro> getHashOrigen() {
        return hashOrigen;
    }

    public void setHashOrigen(HashMap<Integer, Maestro> hashOrigen) {
        this.hashOrigen = hashOrigen;
    }

    public List<Maestro> getListaOrigen() {
        return listaOrigen;
    }

    public void setListaOrigen(List<Maestro> listaOrigen) {
        this.listaOrigen = listaOrigen;
    }

    public HashMap<Integer, Maestro> getHashDestino() {
        return hashDestino;
    }

    public void setHashDestino(HashMap<Integer, Maestro> hashDestino) {
        this.hashDestino = hashDestino;
    }

    public List<Maestro> getListaDestino() {
        return listaDestino;
    }

    public void setListaDestino(List<Maestro> listaDestino) {
        this.listaDestino = listaDestino;
    }

    public HashMap<Integer, Maestro> getHashDestinoTotal() {
        return hashDestinoTotal;
    }

    public void setHashDestinoTotal(HashMap<Integer, Maestro> hashDestinoTotal) {
        this.hashDestinoTotal = hashDestinoTotal;
    }

    public List<Maestro> getListaDestinoTotal() {
        return listaDestinoTotal;
    }

    public void setListaDestinoTotal(List<Maestro> listaDestinoTotal) {
        this.listaDestinoTotal = listaDestinoTotal;
    }

    public int getDiasVigencia() {
        return diasVigencia;
    }

    public void setDiasVigencia(int diasVigencia) {
        this.diasVigencia = diasVigencia;
    }

    public boolean isMostrarBorrarDocumento() {
        return mostrarBorrarDocumento;
    }

    public void setMostrarBorrarDocumento(boolean mostrarBorrarDocumento) {
        this.mostrarBorrarDocumento = mostrarBorrarDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public boolean isDisableRemite() {
        return disableRemite;
    }

    public void setDisableRemite(boolean disableRemite) {
        this.disableRemite = disableRemite;
    }

    public HashMap<String, MaTecnologia> getHashMaTecnologias() {
        return hashMaTecnologias;
    }

    public void setHashMaTecnologias(HashMap<String, MaTecnologia> hashMaTecnologias) {
        this.hashMaTecnologias = hashMaTecnologias;
    }

    public List<MaTecnologia> getListaMaTecnologias() {
        return listaMaTecnologias;
    }

    public void setListaMaTecnologias(List<MaTecnologia> listaMaTecnologias) {
        this.listaMaTecnologias = listaMaTecnologias;
    }

    public HashMap<String, MaServicioHabilitacion> getHashMaServicioHabilitacionItems() {
        return hashMaServicioHabilitacionItems;
    }

    public void setHashMaServicioHabilitacionItems(HashMap<String, MaServicioHabilitacion> hashMaServicioHabilitacionItems) {
        this.hashMaServicioHabilitacionItems = hashMaServicioHabilitacionItems;
    }

    public List<MaServicioHabilitacion> getListaMaServicioHabilitacionItems() {
        return listaMaServicioHabilitacionItems;
    }

    public void setListaMaServicioHabilitacionItems(List<MaServicioHabilitacion> listaMaServicioHabilitacionItems) {
        this.listaMaServicioHabilitacionItems = listaMaServicioHabilitacionItems;
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

    public HashMap<Integer, Ubicacion> getHashUbicacion() {
        return hashUbicacion;
    }

    public void setHashUbicacion(HashMap<Integer, Ubicacion> hashUbicacion) {
        this.hashUbicacion = hashUbicacion;
    }

    public List<Ubicacion> getListaUbicacion() {
        return listaUbicacion;
    }

    public SolicitudesUrgenciasIface getSolicitudesUrgenciasServicio() {
        return solicitudesUrgenciasServicio;
    }

    public void setSolicitudesUrgenciasServicio(SolicitudesUrgenciasIface solicitudesUrgenciasServicio) {
        this.solicitudesUrgenciasServicio = solicitudesUrgenciasServicio;
    }

    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public List<Maestro> getListaEstadosAfiliacion() {
        return listaEstadosAfiliacion;
    }

    public void setListaEstadosAfiliacion(List<Maestro> listaEstadosAfiliacion) {
        this.listaEstadosAfiliacion = listaEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
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

    public LazyDataModel<CntPrestadorSede> getLazyIPSAtencion() {
        return lazyIPSAtencion;
    }

    public void setLazyIPSAtencion(LazyDataModel<CntPrestadorSede> lazyIPSAtencion) {
        this.lazyIPSAtencion = lazyIPSAtencion;
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

    public HashMap<Integer, MaServicioHabilitacion> getHashMapMaServicioHabilitacion() {
        return hashMapMaServicioHabilitacion;
    }

    public void setHashMapMaServicioHabilitacion(HashMap<Integer, MaServicioHabilitacion> hashMapMaServicioHabilitacion) {
        this.hashMapMaServicioHabilitacion = hashMapMaServicioHabilitacion;
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

    public AuSolicitudAdjunto getObjetoAdjunto() {
        return objetoAdjunto;
    }

    public void setObjetoAdjunto(AuSolicitudAdjunto objetoAdjunto) {
        this.objetoAdjunto = objetoAdjunto;
    }

    public AuAnexo2DatoAtencion getObjetoDatosClinicos() {
        return objetoDatosClinicos;
    }

    public void setObjetoDatosClinicos(AuAnexo2DatoAtencion objetoDatosClinicos) {
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

    public HashMap<Integer, Maestro> getHashMotivoGestion() {
        return hashMotivoGestion;
    }

    public void setHashMotivoGestion(HashMap<Integer, Maestro> hashMotivoGestion) {
        this.hashMotivoGestion = hashMotivoGestion;
    }

    public List<AuAnexo2Gestion> getRegistrosRefAnexos9Gestion() {
        return registrosRefAnexos9Gestion;
    }

    public void setRegistrosRefAnexos9Gestion(List<AuAnexo2Gestion> registrosRefAnexos9Gestion) {
        this.registrosRefAnexos9Gestion = registrosRefAnexos9Gestion;
    }

    public LazyDataModel<AuAnexo2Gestion> getLazyGestion() {
        return lazyGestion;
    }

    public void setLazyGestion(LazyDataModel<AuAnexo2Gestion> lazyGestion) {
        this.lazyGestion = lazyGestion;
    }

    public AuAnexo2Gestion getObjetoGestion() {
        return objetoGestion;
    }

    public void setObjetoGestion(AuAnexo2Gestion objectoGestion) {
        this.objetoGestion = objectoGestion;
    }

    public List<AuAnexo2Semaforo> getListaAuAnexo2Semaforo() {
        return listaAuAnexo2Semaforo;
    }

    public void setListaAuAnexo2Semaforo(List<AuAnexo2Semaforo> listaAuAnexo2Semaforo) {
        this.listaAuAnexo2Semaforo = listaAuAnexo2Semaforo;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
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

    public String getCodigosUrgenciasMedica() {
        return codigosUrgenciasMedica;
    }

    public void setCodigosUrgenciasMedica(String codigosUrgenciasMedica) {
        this.codigosUrgenciasMedica = codigosUrgenciasMedica;
    }

    public String getCodigosUrgenciasOdontologica() {
        return codigosUrgenciasOdontologica;
    }

    public void setCodigosUrgenciasOdontologica(String codigosUrgenciasOdontologica) {
        this.codigosUrgenciasOdontologica = codigosUrgenciasOdontologica;
    }

    public String getCodigosServicioHabilitacion() {
        return codigosServicioHabilitacion;
    }

    public void setCodigosServicioHabilitacion(String codigosServicioHabilitacion) {
        this.codigosServicioHabilitacion = codigosServicioHabilitacion;
    }

    public boolean isIsActivoAfiliado() {
        return isActivoAfiliado;
    }

    public void setIsActivoAfiliado(boolean isActivoAfiliado) {
        this.isActivoAfiliado = isActivoAfiliado;
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

    public Ubicacion getObjetoUbicacionAcomp() {
        return objetoUbicacionAcomp;
    }

    public void setObjetoUbicacionAcomp(Ubicacion objetoUbicacionAcomp) {
        this.objetoUbicacionAcomp = objetoUbicacionAcomp;
    }

    public Integer getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(Integer tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    public DefaultStreamedContent getStreamReportePdf() {
        return streamReportePdf;
    }

    public void setStreamReportePdf(DefaultStreamedContent streamReportePdf) {
        this.streamReportePdf = streamReportePdf;
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

    public String getObservacionGenerica() {
        return observacionGenerica;
    }

    public void setObservacionGenerica(String observacionGenerica) {
        this.observacionGenerica = observacionGenerica;
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
            case 0:
                ruta = "../resources/images/triage0.png";
                break;
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

    public Integer obtenerEdad(Date fechaNacimiento) {
        if (fechaNacimiento != null) {
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTime(fechaNacimiento);
            Calendar today = new GregorianCalendar();
            today.setTime(new Date());
            return today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        } else {
            return null;
        }
    }

    public String semaforo(int tiempo) {
        String estilo = "";
        for (AuAnexo2Semaforo obj : listaAuAnexo2Semaforo) {
            if (tiempo <= obj.getTiempo()) {
                estilo = "width: 10px; height: 10px; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%; background: #" + obj.getColor() + ";";
                break;
            } else {
                estilo = "width: 10px; height: 10px; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%; background: #" + obj.getColor() + ";";
            }
        }
        return estilo;
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

    public String obtenerDiagnosticoPrincipal(List<AuAnexo2Diagnostico> listaAuAnexo2Diagnostico) {
        String nombreDiagnostico = "";
        if (listaAuAnexo2Diagnostico != null) {
            for (AuAnexo2Diagnostico diagnostico : listaAuAnexo2Diagnostico) {
                if (diagnostico.isPrincipal()) {
                    nombreDiagnostico = diagnostico.getMaDiagnosticosValor();
                    break;
                }
            }
        }
        return nombreDiagnostico;
    }

    public void actualizarDepartamentoAcomp(SelectEvent event) {
        Ubicacion ubicacion = (Ubicacion) event.getObject();
        if (ubicacion != null && ubicacion.getUbicacionPadre() != null) {
            //objeto.setAcompananteMunicipio(ubicacion.getNombre());
            //objeto.setAcompananteDepartamento(ubicacion.getUbicacionPadre().getNombre());
        }
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getSolicitudesUrgenciasServicio().Accion(this);
    }

    public void refrescarAfiliado() {
        /*super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getSolicitudesUrgenciasServicio().Accion(this);*/
        //2021-01-07 jyperez cambiamos las consultas a listas por funciones directas.
        // estas opciones no deben salir por el listar
        getSolicitudesUrgenciasServicio().listarAfiliado(this);
    }

    public void refrescarIps() {
        /*super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getSolicitudesUrgenciasServicio().Accion(this);*/
        //2021-01-07 jyperez cambiamos las consultas a listas por funciones directas.
        // estas opciones no deben salir por el listar
        getSolicitudesUrgenciasServicio().listarIPS(this);
    }

    public void cargarListaEstados() {
        //if (ACCION_EVENTO_GESTION.equals(accionEvento)) {
//        listaEstadoUrgencia2 = new ArrayList();
        boolean validacion;
        if (this.objeto.getEstado() != null) {
            int estadoActualInt = this.objeto.getEstado();
//            for (Maestro estado : listaEstadoUrgencia) {
            int estadoLista = 0;
            validacion = false;
            //2021-01-11 jyperez No cargamos el valor Anulado en la lista :)
            if (estadoLista != AuAnexo2.ESTADO_ANULADA) {
                //validamos estado actual del anexo 2
                switch (estadoActualInt) {
                    case AuAnexo2.ESTADO_PENDIENTE_AUDITORIA:
                        if (estadoLista != AuAnexo2.ESTADO_PENDIENTE_AUDITORIA) {
                            validacion = true;
                        }
                        break;

                    case AuAnexo2.ESTADO_RECHAZADA_AUDITORIA:
                        if (estadoLista == AuAnexo2.ESTADO_ANULADA) {
                            validacion = true;
                        }
                        break;
                    case AuAnexo2.ESTADO_AUTORIZADA:
                        if (estadoLista == AuAnexo2.ESTADO_ANULADA) {
                            validacion = true;
                        }
                        break;
                    default:
                        validacion = false;
                        break;
                }
                if (validacion) {
//                        listaEstadoUrgencia2.add(estado);
                }
            }
//            }
        }
        //}
        //return accionEvento.equals(this.getAccionEvento());
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        //super.setDoAccion(ACCION_VER);
        getSolicitudesUrgenciasServicio().Accion(this);
        colorEstadoAfiliado = obtenerColorEstado();
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmAuditoriaVer:labelDatosAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void gestionar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        mensajeLogImprimir = "Ingresa a Opción Gestionar ";
        getSolicitudesUrgenciasServicio().Accion(this);
        colorEstadoAfiliado = obtenerColorEstado();
        //se incluye funcionalidad que actualiza la lista de estados de gestion, solo lo hará una vez
        // porque no se llama 3 veces como en la pantalla cada vez que ingresa
//        cargarListaEstados();
        PrimeFaces.current().resetInputs("frmGestionar1");
        PrimeFaces.current().ajax().update("frmGestionar1");
        PrimeFaces.current().resetInputs("frmGestionar2");
        PrimeFaces.current().ajax().update("frmGestionar2");
        PrimeFaces.current().resetInputs("frmGestionar3");
        PrimeFaces.current().ajax().update("frmGestionar3");
        PrimeFaces.current().ajax().update("frmAuditoriaGestionar:labelDatosAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getSolicitudesUrgenciasServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrearSolicitudUrgencia");
        PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia");
        //PrimeFaces.current().resetInputs("frmCrearProfes");
        //PrimeFaces.current().ajax().update("frmCrearProfes");
        //PrimeFaces.current().resetInputs("frmCrearDatos");
        //PrimeFaces.current().ajax().update("frmCrearDatos");
        //PrimeFaces.current().resetInputs("frmDatosAntro");
        //PrimeFaces.current().ajax().update("frmDatosAntro");
        //PrimeFaces.current().resetInputs("frmDatosClinico");
        //PrimeFaces.current().ajax().update("frmDatosClinico");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        this.getObjeto().setRemitido(false);
        this.setDisableRemite(true);
        setDialogo(DIALOGO_CREAR);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        super.limpiarMensajes();
        getSolicitudesUrgenciasServicio().Accion(this);
        if (!this.getErrores().isEmpty()) {
            generarMensajes();
            return;
        }
        PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        procesoFinal();
    }

    public void gestionarEstado() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        mensajeLogImprimir = "Realiza Proceso de Gestión ";
        getSolicitudesUrgenciasServicio().Accion(this);
        if (!this.getErrores().isEmpty()) {
            generarMensajes();
            return;
        }
        PrimeFaces.current().resetInputs("frmGestionar1");
        PrimeFaces.current().ajax().update("frmGestionar1");
        PrimeFaces.current().resetInputs("frmGestionar2");
        PrimeFaces.current().ajax().update("frmGestionar2");
        PrimeFaces.current().resetInputs("frmGestionar3");
        PrimeFaces.current().ajax().update("frmGestionar3");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
        PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        procesoFinal();
    }

    public void editar(int id) {
        procesoFinal();
    }

    public void modificar() {
        procesoFinal();
    }

    public void borrar(int id) {
        procesoFinal();
    }

    public void consultarAfiliado() {
        try {

            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
//            PrimeFaces.current().ajax().update("frmAfiliadoLista");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            //addError(ex.getMessage());
        }
    }

    public void consultarIPS() {
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        PrimeFaces.current().ajax().update("frmIpsLista");
    }

    public void consultarIPSRemite() {
        try {
//            this.setParamConsulta2(new ParamConsulta());
            this.getParamConsulta(2).setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyIPSAtencion = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaIPS;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
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

            PrimeFaces.current().executeScript("PF('dialogoIpsListaAt').show()");
            PrimeFaces.current().ajax().update("frmIpsListaAt");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            //addError(ex.getMessage());
        }
    }

    public void consultarProfesional() {
        try {
            if (getObjeto().getCntProfesionales().getMaeTipoCodumentoId() == 0) {
                addError("Tipo documento: Error de validación: se necesita un valor.");
            }
//            if (getObjeto().getCntProfesionales().getDocumento() == null || objeto.getCntProfesionales().getDocumento().isBlank()) {
//                addError("Número documento: Error de validación: se necesita un valor.");
//            }
            if (!getErrores().isEmpty()) {
                generarMensajes();
                return;
            }
            getSolicitudesUrgenciasServicio().consultarProfesional(this);
            generarMensajes();
            PrimeFaces.current().resetInputs("frmCrearSolicitudUrgencia:pnlPersona");
            PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:pnlPersona");
        } catch (Exception ex) {
            addError(ex.getMessage());
            generarMensajes();
        }
    }

    public void consultarDiagnostico() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            //addError(ex.getMessage());
        }
    }

    public void cerrarDialogoDiagnostico() {
        boolean agregar = true;
        for (AuAnexo2Diagnostico diag : getObjeto().getListaAuAnexo2Diagnostico()) {
            if (diag.getMaDiagnosticosId() == getSelDiagnosticosBean().getObjeto().getId().intValue()) {
                agregar = false;
                addMensaje("El diagnostico " + getSelDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
            }
        }

        if (agregar) {
            AuAnexo2Diagnostico auAnexo2Diagnostico = new AuAnexo2Diagnostico();
            if (getObjeto().getListaAuAnexo2Diagnostico().isEmpty()) {
                auAnexo2Diagnostico.setPrincipal(true);
            }
            auAnexo2Diagnostico.setAuAnexo2(getObjeto());
            auAnexo2Diagnostico.setMaDiagnosticosCodigo(getSelDiagnosticosBean().getObjeto().getCodigo());
            auAnexo2Diagnostico.setMaDiagnosticosId(getSelDiagnosticosBean().getObjeto().getId());
            auAnexo2Diagnostico.setMaDiagnosticosValor(getSelDiagnosticosBean().getObjeto().getNombre());

//            auditoriaGuardar(auAnexo2Diagnostico);
            getObjeto().getListaAuAnexo2Diagnostico().add(auAnexo2Diagnostico);
        } else {
            generarMensajes();
        }
        getSelDiagnosticosBean().setObjeto(new MaDiagnostico());
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
        if (dialogo.equals(DIALOGO_CREAR)) {
            PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:tablaDiagnosticos");
        }
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        getSolicitudesUrgenciasServicio().asignarAfiliado(((AsegAfiliado) event.getObject()).getId(), this);
        if (isActivoAfiliado) {
            PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:pnlAfiliado");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
        }
        generarMensajes();
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        if (dialogo.equals(DIALOGO_CREAR)) {
            this.objeto.setCntPrestadorSede(ips);
            getParamConsulta(2).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:pnlIPS");

        } else if (dialogo.equals(DIALOGO_GESTIONAR)) {
            this.objetoGestion.setCntPrestadorSede(ips);
            PrimeFaces.current().ajax().update("frmAgregarGestion:olPrestadorIPS");
        }
        getObjeto().setVersion(AuAnexo2.VERSION_0);
        getObjeto().setHabilitarCampoDireccionAlternativa(true);
        getObjeto().setHabilitarCampoViaIngresoPersonaServicioSalud(true);
        getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_ORIGEN_ATENCION);
        getObjeto().setLabelDestino(AuAnexo2.LABEL_DESTINO);
        if(ips.getFechaFacturaElectronica() != null){
            getObjeto().setVersion(AuAnexo2.VERSION_1);
            getObjeto().setHabilitarCampoDireccionAlternativa(false);
            getObjeto().setHabilitarCampoViaIngresoPersonaServicioSalud(false);
            getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_CAUSA_MOTIVA_ATENCION);
            getSolicitudesUrgenciasServicio().completarMaestroVersion2335(this);
        }else if(ips.getGrupoRipsMinisterio() != null){
            if(ips.getGrupoRipsMinisterio().equals(1)
                || ips.getGrupoRipsMinisterio().equals(2)){
                getObjeto().setVersion(AuAnexo2.VERSION_1);
                getObjeto().setHabilitarCampoDireccionAlternativa(false);
                getObjeto().setHabilitarCampoViaIngresoPersonaServicioSalud(false);
                getObjeto().setLabelOrigenAtencion(AuAnexo2.LABEL_CAUSA_MOTIVA_ATENCION);
                getObjeto().setLabelDestino(AuAnexo2.LABEL_CONDICION_DESTINO_PERSONA);
                getSolicitudesUrgenciasServicio().completarMaestroVersion2335(this);
            }else{
                getSolicitudesUrgenciasServicio().completarMaestro(this);
            }
        }else{
            getSolicitudesUrgenciasServicio().completarMaestro(this);
        }
        PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:dirAlterna");
        PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:pnlOrigenA");
        PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:pnlDestinoA");
        
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
    }

    public void onRowSelectIpsAt(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        if (dialogo.equals(DIALOGO_CREAR)) {
            getObjeto().setRemiteNit(ips.getCntPrestador().getNumeroDocumento());
            getObjeto().setRemiteNombre(ips.getCntPrestador().getRazonSocial());
            getParamConsulta(2).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:panelTriage");

        } else if (dialogo.equals(DIALOGO_GESTIONAR)) {
            this.objetoGestion.setCntPrestadorSede(ips);
            PrimeFaces.current().ajax().update("frmAgregarGestion:olPrestadorIPS");
        }
        PrimeFaces.current().executeScript("PF('dialogoIpsListaAt').hide()");
    }

    public void actualizarPacienteRemitido() {
        if (!getObjeto().isRemitido()) {
            getObjeto().setRemiteNit(null);
            getObjeto().setRemiteNombre(null);
            PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:panelTriage");
        }
    }

    public void diagnosticoPrincipal(int id) {
        for (AuAnexo2Diagnostico diagnostico : getObjeto().getListaAuAnexo2Diagnostico()) {
            if (diagnostico.getMaDiagnosticosId() != id) {
                diagnostico.setPrincipal(false);
            } else {
                diagnostico.setPrincipal(true);
            }
        }
        PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:tablaDiagnosticos");
    }

    public void borrarDiagnostico(AuAnexo2Diagnostico diagnostico) {
        getObjeto().getListaAuAnexo2Diagnostico().remove(diagnostico);
        this.validarDiagnosticoPrincipal();
        PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:tablaDiagnosticos");
    }

    private void validarDiagnosticoPrincipal() {
        boolean principal = getObjeto().getListaAuAnexo2Diagnostico().stream()
                .anyMatch(diagnostico -> diagnostico.isPrincipal());

        if (!principal && !getObjeto().getListaAuAnexo2Diagnostico().isEmpty()) {
            getObjeto().getListaAuAnexo2Diagnostico().get(0).setPrincipal(true);
        }
    }

    public void abrirAdjuntos() {
        setObjetoAdjunto(new AuSolicitudAdjunto());
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

            String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".") + 1;
            objetoAdjunto.setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objetoAdjunto.setNombre(nombreAdjunto);
//            objetoAdjunto.setFechaHoraCrea(new Date());
            getObjeto().getListaAuSolicitudAdjunto().add(objetoAdjunto);
            objetoAdjunto = new AuSolicitudAdjunto();
            if (dialogo.equals(DIALOGO_CREAR)) {
                PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:tablaAdjuntosVer");
            } else if (dialogo.equals(DIALOGO_GESTIONAR)) {
                PrimeFaces.current().ajax().update("frmGestionarVer:tablaAdjuntosGestionar");
            } else if (dialogo.equals(DIALOGO_EDITAR)) {
                PrimeFaces.current().ajax().update("frmEditarDatos:tablaAdjuntosEditar");
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

    public void BorrarAdjunto(AuSolicitudAdjunto adjunto) {
        getObjeto().getListaAuSolicitudAdjunto().remove(adjunto);
        if (dialogo.equals(DIALOGO_CREAR)) {
            PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:tablaAdjuntosVer");
        } else if (dialogo.equals(DIALOGO_GESTIONAR)) {
            PrimeFaces.current().ajax().update("frmGestionarVer:tablaAdjuntosGestionar");
        } else if (dialogo.equals(DIALOGO_EDITAR)) {
            PrimeFaces.current().ajax().update("frmEditarDatos:tablaAdjuntosEditar");
        }
    }

    public void consultarEspecialidad() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");

        } catch (Exception ex) {
            getObjeto().setAsegAfiliado(new AsegAfiliado());
            //addError(ex.getMessage());
        }
    }

    public void cerrarDialogoEspecialidad() {
        getObjetoProfesionalPrestador().setMaEspecialidadCodigo(getEspecialidadesBean().getObjeto().getCodigo());
        getObjetoProfesionalPrestador().setMaEspecialidadValor(getEspecialidadesBean().getObjeto().getNombre());
        getObjetoProfesionalPrestador().setMaEspecialidadId(getEspecialidadesBean().getObjeto().getId());
        getSelDiagnosticosBean().setObjeto(new MaDiagnostico());
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmCrearSolicitudUrgencia:labelEspecialidad");
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmReferencia");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmReferencia");
                break;
            case Url.ACCION_ADICIONAL_2:// jyperez Opción Gestionar Debe actualizarse
                switch (getDoAccion()) {
                    case Url.ACCION_VER:
                        crearLog(getMensajeLogImprimir() + getObjeto().toString());
                        break;
                    case Url.ACCION_MODIFICAR:
                        crearLog(getMensajeLogImprimir() + getObjeto().toString());
                        PrimeFaces.current().ajax().update("frmReferencia");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_3://se incluye para dejar la referencia en el log de la acción
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog(getMensajeLogImprimir());
                        break;
                    case Url.ACCION_VER:
                        crearLog(getMensajeLogImprimir() + getObjeto().toString());
                }
                break;
        }
        generarMensajes();
    }

    public void verRescates(int id) {
        getObjeto().setId(id);
        getObjeto().setListaAuAnexo2Rescate(new ArrayList());
        getSolicitudesUrgenciasServicio().verRescatesAnexo2(this);
        PrimeFaces.current().executeScript("PF('dlgRescates').show()");
        PrimeFaces.current().ajax().update("frmRescates");
        generarMensajes();
    }

    public void verRescate(int id) {
        objetoRescate = new AuAnexo2Rescate();
        getObjetoRescate().setId(id);
        getSolicitudesUrgenciasServicio().verRescate(this);
        PrimeFaces.current().executeScript("PF('dialogoVerRescate').show()");
        PrimeFaces.current().ajax().update("frmVerRescate");
        generarMensajes();
    }

    public void cambiarTipoDiagnostico(int idDiagnostico) {
        for (AuAnexo2Diagnostico diagnostico : getObjeto().getListaAuAnexo2Diagnostico()) {
            if (diagnostico.getMaDiagnosticosId() == idDiagnostico) {
                Maestro tipoDiagnostico = hashTipoDiagnostico.get(diagnostico.getMaeTipoDiagnosticoId());
                diagnostico.setMaeTipoDiagnosticoCodigo(tipoDiagnostico.getValor());
                diagnostico.setMaeTipoDiagnosticoValor(tipoDiagnostico.getNombre());
                diagnostico.setMaeTipoDiagnosticoId(tipoDiagnostico.getId());
            }
        }
    }

    public void imprimir(int id, int idEstado, int version) {
        setAccion(ACCION_ADICIONAL_3);
        setDoAccion(ACCION_LISTAR);
        mensajeLogImprimir = "Ingresa a Opción Imprimir ";
        //reseteamos la variable de selección
        setTipoImpresion(null);
        getObjeto().setId(id);
        getObjeto().setEstado(idEstado);
        getObjeto().setVersion(version);
        this.setStreamReportePdf(null);
        PrimeFaces.current().resetInputs("frmImprimir");
        PrimeFaces.current().ajax().update("frmImprimir");
        PrimeFaces.current().executeScript("PF('dialogoImprimir').show()");
        procesoFinal();
    }

    public StreamedContent generarReportesAnexo() {
        AuReporte reporte = new AuReporte(getHashUbicaciones());
        Integer id = this.getObjeto().getId();
        StreamedContent streamedContent = null;
        setAccion(ACCION_ADICIONAL_3);
        setDoAccion(ACCION_VER);
        mensajeLogImprimir = "";
        if (this.getTipoImpresion() == 2) {
            if(id != null && getObjeto().getVersion() != null){
                streamedContent = generarReporteAnexo2(id,  getObjeto().getVersion());//IdAnexo2
                mensajeLogImprimir = "Impresión Anexo 2 ";
            }
            
        } else if (this.getTipoImpresion() == 4) {
            AuAnexo4 anexo4 = getSolicitudesUrgenciasServicio().consultarByIdAnexo2(id);
            mensajeLogImprimir = "Impresión Anexo 4 ";
            if (anexo4 != null
                    && anexo4.getRuta() != null
                    && !anexo4.getRuta().isEmpty()
                    && anexo4.getArchivo() != null
                    && !anexo4.getArchivo().isEmpty()) {
                File fileReporte = new File(anexo4.getRuta(), anexo4.getArchivo());
                if (!fileReporte.exists()) {
                    addMensaje("El archivo " + anexo4.getArchivo() + " no se encuentra en la ruta especificada.");
                    PrimeFaces.current().ajax().update("frmImprimir");
                    //PrimeFaces.current().executeScript("PF('dialogoImprimir').show()");
                    generarMensajes();
                    return null;
                }
                try {
                    InputStream stream = new BufferedInputStream(new FileInputStream(fileReporte));
                    streamedContent = DefaultStreamedContent.builder().contentType("*/*").name(fileReporte.getName()).stream(() -> stream).build();
                } catch (IOException e) {
                }
            } else {
                //2021-01-12 jyperez se reemplaza función de generar reporte
                // por funcionalidad centralizada de autorizaciones
                if (anexo4 != null) {
                    streamedContent = reporte.generarReporteAnexo4Imprimir(anexo4);
                    //streamedContent = generarReporteAnexo4(id);//IdAnexo2
                }
            }
        }
        if (streamedContent == null) {
            addMensaje("No hay datos para generar el reporte.");
            //PrimeFaces.current().resetInputs("frmImprimir");
            PrimeFaces.current().ajax().update("frmImprimir");
            //PrimeFaces.current().executeScript("PF('dialogoImprimir').show()");
        } else {
            //2023-10-18 jyperez se comenta linea teniendo en cuenta que la variable no se utiliza
            //this.setStreamReportePdf(new DefaultStreamedContent(streamedContent.getStream(), "application/pdf"));
        }
        
        //PrimeFaces.current().ajax().update(":frmImprimir");
        //PrimeFaces.current().executeScript("PF('dialogoImprimir').show()");
        
        //2021-01-08 jyperez se llama a proceso final para que, deje el log de la opción adicional 3 - Imprimir
        procesoFinal();

        return streamedContent;
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
            switch (ext) {
                case ".pdf":
                    ec.setResponseContentType("application/pdf");
                    break;
                case ".PDF":
                    ec.setResponseContentType("application/pdf");
                    break;
                case ".jpeg":
                    ec.setResponseContentType("	image/jpeg");
                    break;
                case ".JPEG":
                    ec.setResponseContentType("	image/jpeg");
                    break;
                case ".jpg":
                    ec.setResponseContentType("	image/jpeg");
                    break;
                case ".JPG":
                    ec.setResponseContentType("	image/jpeg");
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
                    throw new Exception();
            }
            /*if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else {
                throw new Exception();
            }*/
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public StreamedContent generarReporteAnexo4BORRAR(int idAnexo2) {
        StreamedContent streamContent = null;
        try {
            List<ReporteAnexo4> listaReportes = getSolicitudesUrgenciasServicio().generarReporteAnexo4(idAnexo2, this);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo4.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").name("ReporteAnexo4.pdf").stream(() -> stream).build();
                //streamContent = new DefaultStreamedContent.Builder().name("Reporte.pdf").contentType("application/pdf").stream(()-> stream);
            } else {
                //2021-01-11 jyperez Se cambia el mensaje de Error por un mensaje Informativo ( esto es necesario cuando se llama desde la funcionalidad guardar
                // y se autoriza automáticamente.
                addMensaje("Error no hay datos para generar el reporte");
            }
        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        }
        return streamContent;
    }

    public StreamedContent generarReporteAnexo2(int id, int version) {
        StreamedContent streamContent = null;
        try {
            List<ReporteAnexo2> listaReportes = getSolicitudesUrgenciasServicio().generarReporteAnexo2(id, this);
            if (!listaReportes.isEmpty()) {
                InputStream is = null;
                JRBeanCollectionDataSource beanColDataSource = null;
                if(version == 1){
                    is = getClass().getResourceAsStream("/reportes/Anexo2_2335.jasper");
                    beanColDataSource = new JRBeanCollectionDataSource(listaReportes);
                    
                }else{
                    is = getClass().getResourceAsStream("/reportes/Anexo2.jasper");
                    beanColDataSource = new JRBeanCollectionDataSource(listaReportes);
                }
                
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                String nombreReporte = "";
                if(version == 1){
                    nombreReporte = "anexo1-2-" + getObjeto().getId() + "_" + FORMATO_REPORTE.format(getObjeto().getFechaHoraCrea()) + ".pdf";
                }else{
                    nombreReporte = "anexo2-" + getObjeto().getId() + "_" + FORMATO_REPORTE.format(getObjeto().getFechaHoraCrea()) + ".pdf";
                }
                
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").name(nombreReporte).stream(() -> stream).build();
                setObjeto(new AuAnexo2());
                //streamContent = new DefaultStreamedContent.Builder().name("Reporte.pdf").contentType("application/pdf").stream(()-> stream);
            } else {
                addMensaje("Error no hay datos para generar el reporte");
            }

        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        }

        return streamContent;
    }

    /**
     * @return the mensajeLogImprimir
     */
    public String getMensajeLogImprimir() {
        return mensajeLogImprimir;
    }

    /**
     * @param mensajeLogImprimir the mensajeLogImprimir to set
     */
    public void setMensajeLogImprimir(String mensajeLogImprimir) {
        this.mensajeLogImprimir = mensajeLogImprimir;
    }

    /**
     * Función para restar dias a una fecha
     *
     * @param fecha fecha a modificar
     * @param dias días restar de la fecha
     * @return
     */
    public static Date restarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        int horas;
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        horas = dias * (-24);
        calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    /**
     * @return the fechaMinima
     */
    public Date getFechaMinima() {
        return fechaMinima;
    }

    /**
     * @param fechaMinima the fechaMinima to set
     */
    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public boolean validarEstadoAnulado(int codigo) {
        return AuAnexo2.ESTADO_ANULADA == codigo;
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("solicitudUrgenciasBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("solicitudUrgenciasBean");
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

    public String obtenerUbicacion(int id) {
        if (id == 0) {
            return "";
        }
        String ubi = getHashUbicaciones().get(id).getNombreDepartamentoCiudad();
        return ubi;
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public void showObservacionGenerica(String desc) {
        setObservacionGenerica(desc);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
}
