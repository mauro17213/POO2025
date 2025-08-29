/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.ReportePortabilidad;
import com.saviasaludeps.savia.dominio.atencionusuario.AusAdjunto;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusSeguimiento;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionAdverso;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionInoportunidad;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionObjecion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionPatologia;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionServicio;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucJustificacionEstanciasProlongada;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Tutela;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo3;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.tutela.TuAdjunto;
import com.saviasaludeps.savia.dominio.tutela.TuSeguimiento;
import com.saviasaludeps.savia.dominio.tutela.TuTutela;
import com.saviasaludeps.savia.web.aseguramiento.servicio.ConsultarAfiliadoServicioIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuReporte;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_3;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
//import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class ConsultarAfiliadoBean extends Url {

    @Autowired
    private ConsultarAfiliadoServicioIface consultarAfiliadoServicio;
    private AsegAfiliado objeto;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private HashMap<Integer, Maestro> hashOrigenAfiliado;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashGrupoPoblacional;
    //2022-05-16 INC 0001235 jyperez cambio programas especiales
    private List<Maestro> listaTipoPrograma;

    private List<AsegAfiliado> listaGrupoFamiliarAfiliado;
    private List<AsegPortabilidad> listaPortabilidadAfiliado;
    private List<TuTutela> listaTutelaAfiliado;

    private AsegAfiliado afiliadoCompleto;
    private List<AsegAfiliado> listaAfiliadosDatosSimplificados;

    public static final char ACCION_BUSCAR_AFILIADOS = 'O';
    public static final char ACCION_VER_AFILIADO = 'P';
    public static final char ACCION_VER_HISTORICO_NOVEDADES = 'Q';

    private LazyDataModel<AsegAfiliado> lazyAfiliado;
    private LazyDataModel<AsegRegistroNovedad> lazyHistorial;
    private List<AsegAfiliado> registros;
    private ParamConsulta paramConsultaHistorial;
    private List<AsegRegistroNovedad> listaHistorialNovedad;
    //2021-03-19 jyperez INC 740 adicion objetos Programas Especiales
    private ParamConsulta paramConsultaProgramasEspeciales;
    private List<PeAfiliadosPrograma> listaProgramasEspeciales;
    private LazyDataModel<PeAfiliadosPrograma> lazyProgramasEspeciales;
    //2022-08-09 jyperez nuevas variables RIAS
    private PeAfiliadosPrograma objetoPeAfiliadoPrograma;
    //2021-03-25 jyperez adición objetos opción Autorizaciones
    private ParamConsulta paramConsultaAutorizaciones;
    private List<AuAnexo4> listaAutorizaciones;
    private LazyDataModel<AuAnexo4> lazyAutorizaciones;
    //2022-09-13 jyperez nuevos objetos
    private AuAnexo4 objetoAutorizacion;
    private String rutaAnexo2;
    private String rutaAnexo3;
    //2021-09-03 jyperez adición objetos opción Referencias
    private ParamConsulta paramConsultaReferencias;
    private List<RefAnexo9> listaReferencias;
    private LazyDataModel<RefAnexo9> lazyReferencias;
    private List<Maestro> listaEstadosReferencia;
    private HashMap<Integer, Maestro> hashEstadosReferencia;
    //2022-03-15 jyperez adición objetos para PQRSF
    private ParamConsulta paramConsultaPQRSF;
    private List<AusCaso> listaPQRSF;
    private LazyDataModel<AusCaso> lazyPQRSF;
    private List<Maestro> listaEstadosPQRSF;
    private HashMap<Integer, Maestro> hashEstadosPQRSF;
    //2022-05-24 jyperez adición objetos para MiPRES
    private ParamConsulta paramConsultaMiPres;
    private List<MpPrescripcion> listaMiPres;
    private LazyDataModel<MpPrescripcion> lazyMiPres;
    private MpPrescripcion objetoMiPres;
    private MpPrescripcionRecobrante objetoRecobrante;
    private List<MpDetalleDTO> listaMpDetalleDTO;
    //2022-08-12 jyperez adición objetos para Tutelas
    private ParamConsulta paramConsultaTutelas;
    private List<TuTutela> listaTutelas;
    private LazyDataModel<TuTutela> lazyTutelas;
    private TuTutela objetoTutelas;
    private List<TuSeguimiento> listaSeguimientos;
    private List<Maestro> listaEstadoProceso;
    private HashMap<Integer, Maestro> hashEstadoProceso;
    //2020-11-23 jyperez cambios consulta afiliado fecha exacta
    private Date fechaActual;
    private Date fechaConsulta;
    //2021-05-18 jyperez campos certificado Afiliacion
    private CertificadoAfiliacion certificadoAfiliacion;
    private List<CertificadoAfiliacion> listaCertificadoAfiliacion;
    private transient InputStream adjuntoStream;
    //2022-03-15 jyperez campos PQRSF
    private AusCaso objetoCaso;
    //2025-03-11 jyperez nuevo objeto para PQRS, ver información de seguimiento
    private AusSeguimiento seguimientoProcesar;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private final static Map<Integer, Integer> listaTiposEstratos;
    private String peticionario;
    private HashMap<Integer, Maestro> hashSubGrupoSisben;
    //2022-06-08 jyperez adición variables contribución solidaria
    private boolean afiliadoContribucionSolidaria;
    private String mensajeAfiliadoContribucionSolidaria;
    private boolean afiliadoSinEncuestaSisben4;
    private String mensajeAfiliadoSinEncuestaSisben4;
    //2022-06-28 jyperez objetos para reporte portabilidad
    private ReportePortabilidad reporte;
    private List<ReportePortabilidad> listaReportesPortabilidad;
    //2022-07-11 jyperez variables para habilitar el reporte de portabilidad
    private boolean habilitarCertPortabilidad;
    private String mensajeCertPortabilidad;
    //2022-10-03 jyperez adición objetos para Hospitalización
    private ParamConsulta paramConsultaHospitalizacion;
    private List<AucHospitalizacion> listaHospitalizacion;
    private LazyDataModel<AucHospitalizacion> lazyHospitalizacion;
    private AucHospitalizacion objetoHospitalizacion;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    //2022-12-21 jyperez adición objetos para Direccionado
    private ParamConsulta paramConsultaDireccionado;
    private List<PeDireccionado> listaDireccionado;
    private LazyDataModel<PeDireccionado> lazyDireccionado;
    private PeDireccionado objetoDireccionado;
    private List<Maestro> listaTipoDocumentoEmpresa;
    private HashMap<Integer, Maestro> hashTipoDocumentoEmpresa;
    private List<Maestro> listaTipoGestion;
    private HashMap<Integer, Maestro> hashTipoGestion;
    private List<Maestro> listaEstadosDireccionado;
    private HashMap<Integer, Maestro> hashEstadosDireccionado;
    private AuAnexo3 objetoAnexo3;
    //2023-08-23 jyperez adición objetos para Solicitudes
    private ParamConsulta paramConsultaSolicitudes;
    private List<AuAnexo3> listaSolicitudes;
    private LazyDataModel<AuAnexo3> lazySolicitud;
    private AuAnexo3 objetoSolicitud;
    private String telefonoFijoAfiliado;
    private String telefonoMovilAfiliado;
    private List<Maestro> listaTipoCargue;
    private HashMap<Integer, Maestro> hashTipoCargue;
    private List<Maestro> listaEstadoSolicitud;
    //Variables para mensaje
    private String tituloMensaje;
    private String subtituloMensaje;
    private String mensaje;
    private List<PeAfiliadosPrograma> listaAfiliadoPrograma;
    //2023-02-07 jyperez Ajuste Programas Especiales
    private List<Maestro> listaFuenteOrigen;
    //2023-03-10 jyperez objetos nuevos hospitalización
    private AucJustificacionEstanciasProlongada objetoJustificacionEstanciasProlongada;
    private AucHospitalizacionPatologia objetoPatologia;
    private AucHospitalizacionInoportunidad objetoInoportunidad;
    private AucHospitalizacionAdverso objetoAdverso;
    private AucHospitalizacionObjecion objetoObjecion;
    private AucHospitalizacionServicio objetoServicio;

    //2022-12-07 jyperez adición ventana descripción para seguimiento - hospitalizacion
    private String descripcionGenerica;
    
    //2023-03-23 nuevas variables opción Ver autorización
    private String headerDialogVer;
    private String observacionGenerica;
    private List<AuAnexo4Item> listaAuAnexo4Items;
    private AuAnexo4Item objetoItem;
    private List<AuItemBitacora> listaBitacoras;
    private List<AuSolicitudAdjunto> listaAdjuntosCotizacion;
    
    //2025-02-05 jyperez adición objetos para Prestaciones de Afiliado
    private ParamConsulta paramConsultaCmDetalleFactura;
    private List<CmDetalle> listaCmDetalleFactura;
    private LazyDataModel<CmDetalle> lazyCmDetalleFactura;
    private CmDetalle objetoDetalleFactura;
    
        //2025-08-04 jyperez campos georeferenciación
    private MapModel<Long> registrosMapa;
    private double centroLatitud = AfiliadoParametro.CENTRO_LATITUD_INICIAL;
    private double centroLongitud = AfiliadoParametro.CENTRO_LONGITUD_INICIAL;
    private int profundidad = AfiliadoParametro.PROFUNDIDAD_INICIAL;
    private String rutaGoogleMapsEmpresarial;// = AfiliadoParametro.RUTA_GOOGLE_MAPS_KEY;
//    @Autowired
//    private AseguramientoSingle aseguramientoSingle;
    
    static {
        //listaEstadosServicio = AusCasoServicio.getTiposEstado();
        //listaEstadosServicioEnCreacion = AusCasoServicio.getTiposEstadoEnCreacion();
        listaTiposEstratos = AusPersona.getTiposEstratos();
    }

    public ConsultarAfiliadoBean() {
        fechaActual = new Date();
        this.objeto = new AsegAfiliado();
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_CONSULTA_AFILIADOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyAfiliado = new LazyDataModel<AsegAfiliado>() {
                private List<AsegAfiliado> vendedores;

                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
                @Override
                public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    vendedores = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return vendedores;
                }

                @Override
                public String getRowKey(AsegAfiliado vendedor) {
                    return vendedor.getId().toString();
                }

                @Override
                public AsegAfiliado getRowData(String vendedorId) {
                    Integer id = Integer.valueOf(vendedorId);
                    for (AsegAfiliado vendedor : vendedores) {
                        if (id.equals(vendedor.getId())) {
                            return vendedor;
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
        getConsultarAfiliadoServicio().cargaInial(this);
        listar();
    }

    public AsegAfiliado getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegAfiliado objeto) {
        this.objeto = objeto;
    }

    public ConsultarAfiliadoServicioIface getConsultarAfiliadoServicio() {
        return consultarAfiliadoServicio;
    }

    public void setConsultarAfiliadoServicio(ConsultarAfiliadoServicioIface consultarAfiliadoServicio) {
        this.consultarAfiliadoServicio = consultarAfiliadoServicio;
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

    public AsegAfiliado getAfiliadoCompleto() {
        return afiliadoCompleto;
    }

    public void setAfiliadoCompleto(AsegAfiliado afiliadoCompleto) {
        this.afiliadoCompleto = afiliadoCompleto;
    }

    public List<AsegAfiliado> getListaAfiliadosDatosSimplificados() {
        if (listaAfiliadosDatosSimplificados == null) {
            listaAfiliadosDatosSimplificados = new ArrayList<>();
        }
        return listaAfiliadosDatosSimplificados;
    }

    public void setListaAfiliadosDatosSimplificados(List<AsegAfiliado> listaAfiliadosDatosSimplificados) {
        this.listaAfiliadosDatosSimplificados = listaAfiliadosDatosSimplificados;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashGrupoPoblacional() {
        return hashGrupoPoblacional;
    }

    public void setHashGrupoPoblacional(HashMap<Integer, Maestro> hashGrupoPoblacional) {
        this.hashGrupoPoblacional = hashGrupoPoblacional;
    }

    public String getTipoDocumento(Integer id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public String getTipoGenero(Integer id) {
        try {
            return hashTiposGenero.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public HashMap<Integer, Maestro> getHashOrigenAfiliado() {
        return hashOrigenAfiliado;
    }

    public void setHashOrigenAfiliado(HashMap<Integer, Maestro> hashOrigenAfiliado) {
        this.hashOrigenAfiliado = hashOrigenAfiliado;
    }

    public List<AsegAfiliado> getListaGrupoFamiliarAfiliado() {
        return listaGrupoFamiliarAfiliado;
    }

    public void setListaGrupoFamiliarAfiliado(List<AsegAfiliado> listaGrupoFamiliarAfiliado) {
        this.listaGrupoFamiliarAfiliado = listaGrupoFamiliarAfiliado;
    }

    public List<AsegPortabilidad> getListaPortabilidadAfiliado() {
        return listaPortabilidadAfiliado;
    }

    public void setListaPortabilidadAfiliado(List<AsegPortabilidad> listaPortabilidadAfiliado) {
        this.listaPortabilidadAfiliado = listaPortabilidadAfiliado;
    }
    
    public String getTieneIntegralidad(){
        return listaTutelaAfiliado == null ? "NO" : !listaTutelaAfiliado.isEmpty() ? "SI" : "NO";
    }

    public String getOrigenAfiliado(Integer id) {
        try {
            return hashOrigenAfiliado.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getEstadoAfiliacion(Integer id) {
        try {
            return hashEstadosAfiliacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getGrupoPoblacional(Integer id) {
        try {
            return hashGrupoPoblacional.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliado() {
        return lazyAfiliado;
    }

    public void setLazyAfiliado(LazyDataModel<AsegAfiliado> lazyAfiliado) {
        this.lazyAfiliado = lazyAfiliado;
    }

    public List<AsegAfiliado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegAfiliado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegRegistroNovedad> getLazyHistorial() {
        return lazyHistorial;
    }

    public void setLazyHistorial(LazyDataModel<AsegRegistroNovedad> lazyHistorial) {
        this.lazyHistorial = lazyHistorial;
    }

    public ParamConsulta getParamConsultaHistorial() {
        if (paramConsultaHistorial == null) {
            paramConsultaHistorial = new ParamConsulta();
        }
        return paramConsultaHistorial;
    }

    public List<AsegRegistroNovedad> getListaHistorialNovedad() {
        return listaHistorialNovedad;
    }

    public void setListaHistorialNovedad(List<AsegRegistroNovedad> listaHistorialNovedad) {
        this.listaHistorialNovedad = listaHistorialNovedad;
    }

    public void setParamConsultaHistorial(ParamConsulta paramConsultaHistorial) {
        this.paramConsultaHistorial = paramConsultaHistorial;
    }

    public List<TuTutela> getListaTutelaAfiliado() {
        return listaTutelaAfiliado;
    }

    public void setListaTutelaAfiliado(List<TuTutela> listaTutelaAfiliado) {
        this.listaTutelaAfiliado = listaTutelaAfiliado;
    }       

    public String getEstadoCivil(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Soltero";
                break;
            case "2":
                equivalente = "Casado";
                break;
            case "3":
                equivalente = "Viudo";
                break;
            case "4":
                equivalente = "Divorciado";
                break;
            case "5":
                equivalente = "Unión Libre";
                break;
            case "6":
                equivalente = "Divorciado";
                break;
            case "7":
                equivalente = "No reportado";
                break;
            case "8":
                equivalente = "Otro";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getTipoAfiliado(String valor) {
        String equivalente;
        switch (valor) {
            case "101":
                equivalente = "Cotizante";
                break;
            case "102":
                equivalente = "Beneficiario";
                break;
            case "103":
                equivalente = "Cabeza de Hogar";
                break;
            case "104":
                equivalente = "Adicional";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getParentescoCotizante(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Cónyuge o compañero(a) permanente";
                break;
            case "2":
                equivalente = "Hijos del cotizante o del compañero(a) permanente, menores de veinticinco años que dependen económicamente del cotizante";
                break;
            case "3":
                equivalente = "Padre o madre del cotizante, cabeza de familia";
                break;
            case "4":
                equivalente = "Hijos de beneficiarios menores de 25 años o con incapacidad permanente";
                break;
            case "5":
                equivalente = "Hasta tercer grado de consanguinidad - Hijos de menores de 25 años o cualquier edad con incapacidad permanente";
                break;
            case "7":
                equivalente = "Padres que dependan económicamente de alguno de los cónyuges o compañero(a) permanente del cotizante, cuando ambos cotizan, cabeza de familia";
                break;
            case "8":
                equivalente = "Afiliado adicional";
                break;
            case "9":
                equivalente = "Hijos del cotizante o del compañero(a) permanente, de cualquier edad si tienen incapacidad permanente y depende económicamente del cotizante, cabeza de familia";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getRegimen(String id) {
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

    public String getCategoriaIbc(String valor) {
        String equivalente;
        switch (valor) {
            case "101":
                equivalente = "Categoría A";
                break;
            case "102":
                equivalente = "Categoría B";
                break;
            case "103":
                equivalente = "Categoría C";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getZona(String valor) {
        String equivalente;
        switch (valor) {
            case "R":
                equivalente = "Rural";
                break;
            case "U":
                equivalente = "Urbana";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getModeloLiquidacion(String valor) {
        String equivalente;
        switch (valor) {
            case "0":
                equivalente = "CAPITA";
                break;
            case "1":
                equivalente = "EVENTO";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getGrupoEtnico(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Indígena";
                break;
            case "2":
                equivalente = "ROM (Gitanos)";
                break;
            case "3":
                equivalente = "Raizal";
                break;
            case "4":
                equivalente = "Palenquero san jacinto";
                break;
            case "5":
                equivalente = "Afrocolombiano";
                break;
            case "6":
                equivalente = "Otras";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getNivelSisben(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Nivel 1";
                break;
            case "2":
                equivalente = "Nivel 2";
                break;
            case "3":
                equivalente = "Nivel 3";
                break;
            case "N":
                equivalente = "No Aplica";
                break;
            case "A":
                equivalente = "Categoría A";
                break;
            case "B":
                equivalente = "Categoría B";
                break;
            case "C":
                equivalente = "Categoría C";
                break;
            case "O":
                equivalente = "Categoría O";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getTipoDiscapacidad(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Física";
                break;
            case "2":
                equivalente = "Neuro-Sensorial";
                break;
            case "3":
                equivalente = "Mental";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getEstadoPortabilidad(int valor) {
        String equivalente;
        switch (valor) {
            case 1:
                equivalente = "En Trámite";
                break;
            case 2:
                equivalente = "Aprobada";
                break;
            case 3:
                equivalente = "Rechazada";
                break;
            case 4:
                equivalente = "Cancelada x Usuario";
                break;
            case 5:
                equivalente = "Finalizada";
                break;
            case 6:
                equivalente = "Aprobado con Prórroga";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getTipoPortabilidad(int valor) {
        String equivalente;
        switch (valor) {
            case 1:
                equivalente = "Ocasional";
                break;
            case 2:
                equivalente = "Temporal";
                break;
            case 3:
                equivalente = "Permanente";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_BUSCAR_AFILIADOS);
        getConsultarAfiliadoServicio().Accion(this);
    }

    public void refrescarHistorial() {
        super.setAccion(ACCION_ADICIONAL_1);
        //super.setDoAccion(ACCION_VER_HISTORICO_NOVEDADES);
        getConsultarAfiliadoServicio().Accion(this);
    }

    public void refrescarProgramasEspeciales() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }

    public void refrescarAutorizaciones() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_LISTAR);
        //super.setDoAccion(ACCION_VER_HISTORICO_NOVEDADES);
        getConsultarAfiliadoServicio().Accion(this);
    }

    public void refrescarReferencias() {
        super.setAccion(ACCION_ADICIONAL_5);
        //super.setDoAccion(ACCION_VER_HISTORICO_NOVEDADES);
        getConsultarAfiliadoServicio().Accion(this);
    }
    
    public void refrescarPQRSF() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }
    
    public void refrescarMIPRES() {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(Url.ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }
    
    public void refrescarTutelas() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }
    
    public void refrescarHospitalizacion() {
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(Url.ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }
    
    public void refrescarDireccionado() {
        super.setAccion(ACCION_ADICIONAL_10);
        super.setDoAccion(Url.ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }
    
    public void refrescarSolicitud() {
        super.setAccion(ACCION_ADICIONAL_11);
        super.setDoAccion(Url.ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }
    
    public void refrescarCmDetalleFactura() {
        super.setAccion(ACCION_ADICIONAL_12);
        super.setDoAccion(Url.ACCION_LISTAR);
        getConsultarAfiliadoServicio().Accion(this);
    }

    private void inicializarTablaAfiliados() {
//        lazyAfiliado = new LazyDataModel<AsegAfiliado>() {
//            private List<AsegAfiliado> vendedores;
//
//            @Override
//            public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, String orden, SortOrder formaOrden, Map<String, FilterMeta> filtros) {
//                getParamConsulta().setPrimerRegistro(primerRegistro);
//                getParamConsulta().setRegistrosPagina(registrosPagina);
//                getParamConsulta().setOrden(orden);
//                getParamConsulta().setAscendente(SortOrder.ASCENDING.equals(formaOrden));
//                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
//                refrescar();
//                vendedores = getRegistros();
//                setRowCount(getParamConsulta().getCantidadRegistros());
//                return vendedores;
//            }
//
//            @Override
//            public Object getRowKey(AsegAfiliado vendedor) {
//                return vendedor.getId();
//            }
//
//            @Override
//            public AsegAfiliado getRowData(String vendedorId) {
//                Integer id = Integer.valueOf(vendedorId);
//                for (AsegAfiliado vendedor : vendedores) {
//                    if (id.equals(vendedor.getId())) {
//                        return vendedor;
//                    }
//                }
//                return null;
//            }
//
//        };
    }

    public void ver() {

        boolean validarBusqueda = false;
        this.getParamConsulta().setParametroConsulta1(null);
        this.getParamConsulta().setParametroConsulta2(null);
        this.getParamConsulta().setParametroConsulta3(null);
        this.getParamConsulta().setParametroConsulta4(null);
        this.getParamConsulta().setParametroConsulta5(null);
        this.getParamConsulta().setParametroConsulta6(null);
        this.getParamConsulta().setParametroConsulta7(null);
        this.getParamConsulta().setParametroConsulta8(null);
        //2024-01-10 jyperez IS 58 retiro por duplicado - configuramos parámetro 9 para validar duplicado
        this.getParamConsulta().setParametroConsulta9(null);
        this.getParamConsulta().setFiltros(new HashMap<>());
        //configuramos el valor de 0 para que valide que los afiliados no esten marcados como duplicados
        this.getParamConsulta().setParametroConsulta9(0);
        if (this.getObjeto().getMaeTipoDocumento() > 0) {
            this.getParamConsulta().setParametroConsulta1(this.getObjeto().getMaeTipoDocumento());
            if (this.getObjeto().getNumeroDocumento().equals("")) {
                this.addError("Para realizar la búsqueda por tipo documento debe digitar un número.");
                validarBusqueda = false;
            }
        }

        if (!this.getObjeto().getNumeroDocumento().trim().equals("")) {
            //2022-08-16 jyperez INC 0001276 se ajusta para que el valor sea mayor que 1, teniendo en cuenta
            //las validaciones de tamaño de tipo de documento para afiliado que permiten desde 1 dígito
            if (this.getObjeto().getNumeroDocumento().trim().length() > 1) {
                this.getParamConsulta().setParametroConsulta2(this.getObjeto().getNumeroDocumento().trim().toUpperCase());
                validarBusqueda = true;
            } else {
                this.addError("El número de caracteres del documento debe ser mayor a 1.");
                validarBusqueda = false;
            }
        }

        if (!this.getObjeto().getPrimerNombre().trim().equals("")) {
            this.getParamConsulta().setParametroConsulta3(this.getObjeto().getPrimerNombre().trim().toUpperCase());
            validarBusqueda = true;
        }

        if (!this.getObjeto().getSegundoNombre().trim().equals("")) {
            this.getParamConsulta().setParametroConsulta4(this.getObjeto().getSegundoNombre().trim().toUpperCase());
            validarBusqueda = true;
        }

        if (!this.getObjeto().getPrimerApellido().trim().equals("")) {
            this.getParamConsulta().setParametroConsulta5(this.getObjeto().getPrimerApellido().trim().toUpperCase());
            validarBusqueda = true;
        }

        if (!this.getObjeto().getSegundoApellido().trim().equals("")) {
            this.getParamConsulta().setParametroConsulta6(this.getObjeto().getSegundoApellido().trim().toUpperCase());
            validarBusqueda = true;
        }

        if (this.getObjeto().getFechaNacimiento() != null) {
            this.getParamConsulta().setParametroConsulta7(this.getObjeto().getFechaNacimiento());
            validarBusqueda = true;
        }
        if (!this.getObjeto().getIdAfiliado().trim().equals("")) {
            if (this.getObjeto().getIdAfiliado().trim().length() > 4) {
                this.getParamConsulta().setParametroConsulta8(this.getObjeto().getIdAfiliado().trim());
                validarBusqueda = true;
            } else {
                this.addError("El número de caracteres ingresado del contrato afiliado debe ser mayor a 4.");
                validarBusqueda = false;
            }
        }
        /*} else {
            this.addError("Se debe ingresar la Fecha de Consulta.");
        }*/

        if (validarBusqueda) {
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_BUSCAR_AFILIADOS);
            getConsultarAfiliadoServicio().Accion(this);
            if (!super.isError()) {
                int cantidadRegistros = this.getRegistros() != null ? this.getRegistros().size() : 0;
                if (cantidadRegistros > 0) {
                    if (cantidadRegistros == 1) {
                        //int idAfiliado = this.getRegistros().get(0).getId();
                        verAfiliado(this.getRegistros().get(0).getId());
                    } else {
                        PrimeFaces.current().ajax().update("frmVerAfiliadosEncontrados");
                        PrimeFaces.current().executeScript("PF('dialogoAfiliadosEncontrados').show()");
                    }
                } else {
                    this.addMensaje("No se encontraron afiliados con los criterios usados.");
                }
            }
        } else {
            if (!super.isError()) {
                this.addError("Para realizar la búsqueda debe ingresar un criterio.");
            }
        }
        procesoFinal();
    }

    public void verAfiliadoHistorico(AsegAfiliado obj) {
        boolean resultado = false;
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        // obtenemos la información del objeto de historial del afiliado
        try {
            // convertir la información del historial en un objeto de afiliado
            afiliadoCompleto = gson.fromJson(obj.getListaAsegAfiliadoHistorico().get(0).getTostringAfiliado(), AsegAfiliado.class);
            afiliadoCompleto.setFechaUltimaNovedad(obj.getListaAsegAfiliadoHistorico().get(0).getFechaHoraCrea());
            resultado = true;
        } catch (JsonSyntaxException e) {
            this.addError("No se pudo obtener la información del histórico del afliado seleccionado.");
        }
        if (resultado) {
            PrimeFaces.current().ajax().update("frmVer");
            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        }
        procesoFinal();
    }

    public void verAfiliado(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_AFILIADO);
        getConsultarAfiliadoServicio().Accion(this);
        validarContribucionSolidaria();
        //2022-09-15 jyperez se adiciona la validación si el afiliado debe llenar encuesta sisben 4
        validarAfiliadoSinEncuestaSisbenVersion4();
        //2025-08-04 jyperez validamos campos de georreferenciación
        llenarRegistrosMapa(afiliadoCompleto);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVer");
            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        }
        procesoFinal();
    }
    
    /**
     * función para validar si el afiliado es grupo D, lo cual indica que es de contribución solidaria
     * Se implementa para ser usada en el Ver y el Editar
     */
    public void validarContribucionSolidaria() {
        //cargamos los valores del maestro teniendo en cuenta que debemos evaluar mediante código
        this.afiliadoContribucionSolidaria = false;
        this.mensajeAfiliadoContribucionSolidaria = "";
        try {
            if (this.afiliadoCompleto.getMaeGrupoSisbenCodigo().equals(AfiliadoParametro.GRUPO_SISBEN_D)) {
                this.afiliadoContribucionSolidaria = true;
                this.mensajeAfiliadoContribucionSolidaria = "Afiliado por Contribución Solidaria";
            }
        } catch (Exception e) {
            this.afiliadoContribucionSolidaria = false;
            this.mensajeAfiliadoContribucionSolidaria = "";
        }
    }
    
    /**
     * función para validar si el afiliado se encuentra en grupo poblacional población sisbenizada
     * y tiene configurado en la metodología de grupo poblacional Sisben 3 ó no tiene valor. Esto con el objetivo
     * de informarle que debe realizar la encuentra de sisben versión 4 y debe ser actualizarse a esa metodología.
     * Se implementa para ser usada en el Ver y el Editar
     */
    public void validarAfiliadoSinEncuestaSisbenVersion4() {
        //cargamos los valores del maestro teniendo en cuenta que debemos evaluar mediante código
        this.setAfiliadoSinEncuestaSisben4(false);
        this.setMensajeAfiliadoSinEncuestaSisben4("");
        try {
            if (this.afiliadoCompleto.getMaeGrupoPoblacionalCodigo().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)) {
                if (this.afiliadoCompleto.getMaeMetodologiaGrupoPoblacionalId() != null &&
                        (this.afiliadoCompleto.getMaeMetodologiaGrupoPoblacionalCodigo().equals(AfiliadoParametro.METODOLOGIA_GRUPO_POBLACIONAL_SISBEN_3) ||
                            this.afiliadoCompleto.getMaeMetodologiaGrupoPoblacionalCodigo().equals("") )) {
                    this.setAfiliadoSinEncuestaSisben4(true);
                    this.setMensajeAfiliadoSinEncuestaSisben4("Usuario sin encuesta Versión 4 del SISBEN");
                } else if (this.afiliadoCompleto.getMaeMetodologiaGrupoPoblacionalId() == null) {
                    this.setAfiliadoSinEncuestaSisben4(true);
                    this.setMensajeAfiliadoSinEncuestaSisben4("Usuario sin encuesta Versión 4 del SISBEN");
                }
            }
        } catch (Exception e) {
            this.setAfiliadoSinEncuestaSisben4(false);
            this.setMensajeAfiliadoSinEncuestaSisben4("");
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
            String acciones = "";
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmConsultarAfiliado");
                    break;
                case Url.ACCION_VER:
                    switch (this.getDoAccion()) {
                        case ConsultarAfiliadoBean.ACCION_BUSCAR_AFILIADOS:
                            PrimeFaces.current().ajax().update("frmConsultarAfiliado");
                            acciones = "Consultar afiliado";
                            break;
                        case ConsultarAfiliadoBean.ACCION_VER_AFILIADO:
                            acciones = "Ver Afiliado";
                            break;
                        /*case ConsultarAfiliadoBean.ACCION_VER_HISTORICO_NOVEDADES:
                            acciones = "Ver Historial";
                            break;*/
                    }
                    crearLog(acciones, getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    acciones = "Ver Historial";
                    crearLog(acciones, getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Listar Programas Especiales";
                            crearLog(acciones, getObjeto().toString());
                        break;
                        case Url.ACCION_VER:
                            acciones = "Ver Programa Especial";
                            crearLog(acciones, getObjetoPeAfiliadoPrograma().toString());
                        break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch(getDoAccion()) {
                            case Url.ACCION_LISTAR:
                                acciones = "Ver Autorizaciones";
                                crearLog(acciones, getObjeto().toString());
                                break;
                            case Url.ACCION_VER:
                                acciones = "Ver Autorizacion";
                                crearLog(acciones, getObjetoAutorizacion().toString());
                                break;
                            case Url.ACCION_ADICIONAL_1:
                                acciones = "Imprimir Autorización";
                                crearLog(acciones, getObjetoAutorizacion().toString());
                                break;
                        }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    acciones = "Ver Referencias";
                    crearLog(acciones, getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Ver Listado PQRSF";
                            crearLog(acciones, getObjeto().toString());
                        break;
                        case Url.ACCION_VER:
                            acciones = "Ver PQRSF";
                            crearLog(acciones, getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Ver Listado MIPRES";
                            crearLog(acciones, getObjeto().toString());
                        break;
                        case Url.ACCION_VER:
                            acciones = "Ver MIPRES";
                            crearLog(acciones, getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_8:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Ver Listado Tutela";
                            crearLog(acciones, getObjeto().toString());
                        break;
                        case Url.ACCION_VER:
                            acciones = "Ver Tutela";
                            crearLog(acciones, getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_9:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Ver Listado Hospitalizacion";
                            crearLog(acciones, getObjeto().toString());
                        break;
                        case Url.ACCION_VER:
                            acciones = "Ver Hospitalizacion";
                            crearLog(acciones, getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            acciones = "Ver Hospitalizacion-Justif EP";
                            crearLog(acciones, getObjetoJustificacionEstanciasProlongada().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            acciones = "Ver Hospitalizacion-Patologías";
                            crearLog(acciones, getObjetoPatologia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            acciones = "Ver Hospitalizacion-Inoport EPS";
                            crearLog(acciones, getObjetoInoportunidad().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            acciones = "Ver Hospitalizacion-Adversos";
                            crearLog(acciones, getObjetoAdverso().toString());
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            acciones = "Ver Hospitalizacion-Objeciones";
                            crearLog(acciones, getObjetoObjecion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            acciones = "Ver Hospitalizacion-Servicios";
                            crearLog(acciones, getObjetoServicio().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Ver Listado Direccionados";
                            crearLog(acciones, getObjeto().toString());
                        break;
                        case Url.ACCION_VER:
                            acciones = "Ver Direccionado";
                            crearLog(acciones, getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_11:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Ver Listado Solicitudes";
                            crearLog(acciones, getObjeto().toString());
                        break;
                        case Url.ACCION_VER:
                            acciones = "Ver Solicitud";
                            crearLog(acciones, getObjetoSolicitud().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            acciones = "Imprimir Solicitud";
                            crearLog(acciones, getObjetoSolicitud().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_12:
                    switch(getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            acciones = "Ver Listado Prestaciones";
                            crearLog(acciones, getObjeto().toString());
                        break;
                    }
                    break;
            }

        }
        generarMensajes();
    }

    public void limpiarFormulario() {
        setObjeto(new AsegAfiliado());
        PrimeFaces.current().ajax().update("frmConsultarAfiliado");
    }

    public void verHistorialNovedades(int _id) {
        super.setAccion(ACCION_ADICIONAL_1);
        //super.setDoAccion(ACCION_VER_HISTORICO_NOVEDADES);
        inicializarTablaHistorialAfiliados(_id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaHistorialNovedad.isEmpty()) {
                addMensaje("El afiliado no posee Historial de Novedades");
            } else {
                PrimeFaces.current().ajax().update("frmHistorial");
                PrimeFaces.current().executeScript("PF('dialogoHistorial').show()");
            }
        }
        procesoFinal();
    }

    public void inicializarTablaHistorialAfiliados(int id) {
        this.setParamConsultaHistorial(new ParamConsulta());
        getParamConsultaHistorial().setRegistrosPagina(30);
        this.getParamConsultaHistorial().setEmpresaId(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyHistorial = new LazyDataModel<AsegRegistroNovedad>() {

            private List<AsegRegistroNovedad> novedades;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaHistorial().getCantidadRegistros();
            }

            @Override
            public List<AsegRegistroNovedad> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaHistorial().setPrimerRegistro(primerRegistro);
                getParamConsultaHistorial().setRegistrosPagina(registrosPagina);
                getParamConsultaHistorial().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaHistorial().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHistorial();
                novedades = getListaHistorialNovedad();
                setRowCount(getParamConsultaHistorial().getCantidadRegistros());
                return novedades;
            }

            @Override
            public String getRowKey(AsegRegistroNovedad novedades) {
                return novedades.getId().toString();
            }

            @Override
            public AsegRegistroNovedad getRowData(String novedadesId) {
                Integer id = Integer.valueOf(novedadesId);
                for (AsegRegistroNovedad novedad : novedades) {
                    if (id.equals(novedad.getId())) {
                        return novedad;
                    }
                }
                return null;
            }

        };

    }

    public void verProgramasEspeciales(int id) {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        this.objeto = new AsegAfiliado(id);
        inicializarTablaProgramasEspeciales(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaProgramasEspeciales.isEmpty()) {
                addMensaje("El afiliado no tiene registros en Programas Especiales");
            } else {
                PrimeFaces.current().ajax().update("frmProgramasEspeciales");
                PrimeFaces.current().executeScript("PF('dialogoProgramasEspeciales').show()");
            }
        }
        procesoFinal();
    }

    public void inicializarTablaProgramasEspeciales(int id) {
        this.setParamConsultaProgramasEspeciales(new ParamConsulta());
        this.getParamConsultaProgramasEspeciales().setRegistrosPagina(30);
        this.getParamConsultaProgramasEspeciales().setEmpresaId(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyProgramasEspeciales = new LazyDataModel<PeAfiliadosPrograma>() {

            private List<PeAfiliadosPrograma> programas;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaProgramasEspeciales().getCantidadRegistros();
            }
            @Override
            public List<PeAfiliadosPrograma> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaProgramasEspeciales().setPrimerRegistro(primerRegistro);
                getParamConsultaProgramasEspeciales().setRegistrosPagina(registrosPagina);
                getParamConsultaProgramasEspeciales().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaProgramasEspeciales().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarProgramasEspeciales();
                programas = getListaProgramasEspeciales();
                setRowCount(getParamConsultaProgramasEspeciales().getCantidadRegistros());
                return programas;
            }

            @Override
            public String getRowKey(PeAfiliadosPrograma afiliadoPrograma) {
                return afiliadoPrograma.getId().toString();
            }

            @Override
            public PeAfiliadosPrograma getRowData(String afiliadoProgramaId) {
                Integer id = Integer.valueOf(afiliadoProgramaId);
                for (PeAfiliadosPrograma afiliadoPrograma : programas) {
                    if (id.equals(afiliadoPrograma.getId())) {
                        return afiliadoPrograma;
                    }
                }
                return null;
            }

        };

    }

    public void verAutorizaciones(int id) {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_LISTAR);
        this.objeto = new AsegAfiliado(id);
        inicializarTablaAutorizaciones(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaAutorizaciones.isEmpty()) {
                addMensaje("El afiliado no tiene registros en Autorizaciones");
            } else {
                PrimeFaces.current().ajax().update("frmAutorizaciones");
                PrimeFaces.current().executeScript("PF('dialogoAutorizaciones').show()");
            }
        }
        procesoFinal();
    }
    
    public void verAutorizacion(int id) {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER);
        this.objeto = new AsegAfiliado(id);
        getConsultarAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerAutorizacion");
        PrimeFaces.current().ajax().update("frmVerAutorizacion:pVerServicios");
        PrimeFaces.current().ajax().update("frmVerAutorizacion:tablaTecnologiasVer");
        //PrimeFaces.current().ajax().update("frmAuditoriaVer:labelDatosAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoVerAutorizacion').show()");
        PrimeFaces.current().ajax().update("dialogoVerId");
        procesoFinal();
    }
    
    public boolean validarEstadoAnulado(int estado) {
        //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
        return estado == AuAnexo4.ESTADO_ANULADA || estado == AuAnexo4.ESTADO_ANULADA_PREAUTORIZACION || estado == AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO;
    }
    
    public String obtenerTextoAnulado() {
        try {
            return "Anulada - " + getObjetoAutorizacion().getMaeEstadoMotivoValor();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String obtenerTextoAnuladoSol() {
        try {
            return getObjetoSolicitud().getEstadoStr() + " " + getObjetoSolicitud().getMaeEstadoMotivoValor();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String obtenerString(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }
    
    public void showObservacionGenerica(String desc) {
        setObservacionGenerica(desc);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
    public void verBitacoras(AuAnexo4Item item4) {
        setObjetoItem(item4);
        getConsultarAfiliadoServicio().verBitacoras(this);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacoras').show()");
        PrimeFaces.current().ajax().update("frmVerBitacoras");
        generarMensajes();
    }
    
    public void consultarAdjuntos(int id) {
        if (getObjetoAutorizacion().getAuAnexo3Id() != null) {
            getObjetoAutorizacion().getAuAnexo3Id().setObjetoTecnologia(
                    new AuAnexo3Item(id)
            );
            this.setListaAdjuntosCotizacion((List<AuSolicitudAdjunto>) new ArrayList());
            getConsultarAfiliadoServicio().consultarAdjuntosCotizacion(this);

            PrimeFaces.current().executeScript("PF('dialogoVerAdjuntosItem').show()");
            PrimeFaces.current().ajax().update("frmVerAdjuntosItem");

            generarMensajes();
        }

    }
    
    public void descargarAdjuntoSolicitudAnexo(AuSolicitudAdjunto adjunto) {
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
    
    public boolean validarEsPreautorizacion(int estado) {
        return estado == AuAnexo4.ESTADO_PREAUTORIZADO || estado == AuAnexo4.ESTADO_ANULADA_PREAUTORIZACION;
    }
    
    public StreamedContent generarReporteAnexo4(int id) {
        setObjetoAutorizacion(new AuAnexo4());
        getObjetoAutorizacion().setId(id);
        StreamedContent streamContent = null;
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getConsultarAfiliadoServicio().Accion(this);
        //SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        //AuAnexo4 anexo4 = getConsultarAfiliadoServicio().consultarAnexo4(getObjeto().getId());
        String nombreDocumento = getObjetoAutorizacion().getArchivoNombre();
        crearLog("Imprimir Autorizacion", getObjetoAutorizacion().toString());
        boolean isActivo = true; //getAuAutorizacionServicio().validarEstadoAfiliado(getObjeto().getAsegAfiliadoId().getMaeEstadoAfiliacion());        
        if (isActivo) {
            String marca;
            if (getObjetoAutorizacion().getImpresionesRealizadas() == null) {
                getObjetoAutorizacion().setImpresionesRealizadas(0);
            }
            int ejeX = 20;
            int ejeY = 20;
            if (getObjetoAutorizacion().getImpresionesRealizadas() == 0) {
                marca = "Original";
            } else {
                int impresiones = getObjetoAutorizacion().getImpresionesRealizadas();
                marca = "Copia " + impresiones;
            }
            try {
                if (getObjetoAutorizacion().getImpresionesRealizadas() < getObjetoAutorizacion().getImpresionesAutorizadas()) {
                    String ruta = getObjetoAutorizacion().getRuta();
                    String nombre = getObjetoAutorizacion().getArchivo();
                    ruta += nombre;
                    byte[] bytes = Files.readAllBytes(Paths.get(ruta));
                    AuReporte reporte = new AuReporte();
                    byte[] newBytes = reporte.generarMarcaAgua(bytes, marca, ejeX, ejeY);
                    if (newBytes != null) {
                        //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
                        if (getObjetoAutorizacion().getEstado() == AuAnexo4.ESTADO_ANULADA || getObjetoAutorizacion().getEstado() == AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO) {
                            marca = "ANULADA";
                            ejeX = 310;
                            ejeY = 565;
                            newBytes = reporte.generarMarcaAgua(newBytes, marca, ejeX, ejeY);
                        }
                        bytes = newBytes;
                    }
                    InputStream stream = new ByteArrayInputStream(bytes);
                    stream.mark(0);
                    streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombreDocumento).build();
                    getConsultarAfiliadoServicio().contarImpresion(this);
                } else {
                    addError("La autorización número " + getObjetoAutorizacion().getId() + " ya alcanzo el número de impresiones permitidas (" + getObjetoAutorizacion().getImpresionesRealizadas() + ").");
                }
            } catch (IOException ex) {
                if (getObjetoAutorizacion()!= null) {
                    AuReporte reporte = new AuReporte();
                    String ruta = getObjetoAutorizacion().getAuAnexo2Id() != null ? getRutaAnexo2() : getRutaAnexo3();
                    getObjetoAutorizacion().setRuta(ruta);
                    if (getObjetoAutorizacion().getArchivo() == null || !getObjetoAutorizacion().getArchivo().equals("")) {
                        getObjetoAutorizacion().setArchivo(AuConstantes.nombreReporteAnexo4(getObjetoAutorizacion()));
                    }
                    reporte.generarReporteAnexo4(ruta, getObjetoAutorizacion());
                    if (getObjetoAutorizacion().getImpresionesRealizadas() < getObjetoAutorizacion().getImpresionesAutorizadas()) {
                        String nombre = getObjetoAutorizacion().getArchivo();
                        ruta += nombre;
                        try {
                            byte[] bytes = Files.readAllBytes(Paths.get(ruta));
                            byte[] newBytes = reporte.generarMarcaAgua(bytes, marca, ejeX, ejeY);
                            if (newBytes != null) {
                                //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
                                if (getObjetoAutorizacion().getEstado() == AuAnexo4.ESTADO_ANULADA || getObjetoAutorizacion().getEstado() == AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO) {
                                    marca = "ANULADA";
                                    ejeX = 310;
                                    ejeY = 565;
                                    newBytes = reporte.generarMarcaAgua(newBytes, marca, ejeX, ejeY);
                                }
                                bytes = newBytes;
                            }
                            InputStream stream = new ByteArrayInputStream(bytes);
                            stream.mark(0);
                            streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(nombreDocumento).build();
                            getConsultarAfiliadoServicio().contarImpresion(this);
                        } catch (IOException e) {
                            crearLog("Fallo Imprimir Autorizacion", ex.getMessage() + " " + ex.toString());
                            addError("Hubo un fallo al descargar el pdf por favor comunicarse con el administrador");
                        }
                    } else {
                        addError("La autorización número " + getObjetoAutorizacion().getId() + " ya alcanzo el número de impresiones permitidas (" + getObjetoAutorizacion().getImpresionesRealizadas() + ").");
                }
                }
                addMensaje("Hubo un error buscando el pdf, se genero nuevamente");
            }
        } else {
            addMensaje("El Afiliado no esta activo");
        }
        generarMensajes();
        return streamContent;
    }
    
    public void generarCertificados(int id) {
        super.setAccion(ACCION_ADICIONAL_4);
        this.objeto = new AsegAfiliado(id);
        //2022-07-11 jyperez evaluamos la lista de portabilidades para validar si se habilita la impresión de certificado
        if (this.listaPortabilidadAfiliado != null && !this.listaPortabilidadAfiliado.isEmpty()) {
            habilitarCertPortabilidad = true;
            mensajeCertPortabilidad = "Certificado de Portabilidad";
        } else {
            habilitarCertPortabilidad = false;
            mensajeCertPortabilidad = "No tiene Portabilidad";
        }
        PrimeFaces.current().ajax().update("frmCrearCertificado");
        PrimeFaces.current().executeScript("PF('dialogoCrearCertificado').show()");
        procesoFinal();
    }

    public void inicializarTablaAutorizaciones(int id) {
        this.setParamConsultaAutorizaciones(new ParamConsulta());
        this.getParamConsultaAutorizaciones().setRegistrosPagina(30);
        this.getParamConsultaAutorizaciones().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        //2023-03-23 jyperez se adiciona la empresa del usuario para filtrar
        if (super.getConexion().getEmpresa().isAdministradora()) {
            this.getParamConsultaAutorizaciones().setEmpresaId(null);
        } else {
            this.getParamConsultaAutorizaciones().setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        lazyAutorizaciones = new LazyDataModel<AuAnexo4>() {

            private List<AuAnexo4> autorizaciones;
            
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
			
            @Override
            public List<AuAnexo4> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaAutorizaciones().setPrimerRegistro(primerRegistro);
                getParamConsultaAutorizaciones().setRegistrosPagina(registrosPagina);
                getParamConsultaAutorizaciones().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaAutorizaciones().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAutorizaciones();
                autorizaciones = getListaAutorizaciones();
                setRowCount(getParamConsultaAutorizaciones().getCantidadRegistros());
                return autorizaciones;
            }

            @Override
            public String getRowKey(AuAnexo4 afiliadoAutorizacion) {
                return afiliadoAutorizacion.getId().toString();
            }

            @Override
            public AuAnexo4 getRowData(String afiliadoAutorizacionId) {
                Integer id = Integer.valueOf(afiliadoAutorizacionId);
                for (AuAnexo4 afiliadoAutorizacion : autorizaciones) {
                    if (id.equals(afiliadoAutorizacion.getId())) {
                        return afiliadoAutorizacion;
                    }
                }
                return null;
            }

        };

    }

    public void verReferencias(int id) {
        super.setAccion(ACCION_ADICIONAL_5);
        this.objeto = new AsegAfiliado(id);
        inicializarTablaReferencias(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaReferencias.isEmpty()) {
                addMensaje("El afiliado no tiene registros de referencia o contra referencia");
            } else {
                PrimeFaces.current().ajax().update("frmReferencias");
                PrimeFaces.current().executeScript("PF('dialogoReferencias').show()");
            }
        }
        procesoFinal();
    }

    public void inicializarTablaReferencias(int id) {
        this.setParamConsultaReferencias(new ParamConsulta());
        this.getParamConsultaReferencias().setRegistrosPagina(30);
        this.getParamConsultaReferencias().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyReferencias = new LazyDataModel<RefAnexo9>() {

            private List<RefAnexo9> referencias;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaReferencias().getCantidadRegistros();
            }

            @Override
            public List<RefAnexo9> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaReferencias().setPrimerRegistro(primerRegistro);
                getParamConsultaReferencias().setRegistrosPagina(registrosPagina);
                getParamConsultaReferencias().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaReferencias().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarReferencias();
                referencias = getListaReferencias();
                setRowCount(getParamConsultaReferencias().getCantidadRegistros());
                return referencias;
            }

            @Override
            public String getRowKey(RefAnexo9 afiliadoReferencia) {
                return afiliadoReferencia.getId().toString();
            }

            @Override
            public RefAnexo9 getRowData(String afiliadoReferenciaId) {
                Integer id = Integer.valueOf(afiliadoReferenciaId);
                for (RefAnexo9 afiliadoReferencia : referencias) {
                    if (id.equals(afiliadoReferencia.getId())) {
                        return afiliadoReferencia;
                    }
                }
                return null;
            }

        };

    }
    
    public void verListadoPQRSF(int id) {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objeto = new AsegAfiliado(id);
        inicializarTablaPQRSF(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaPQRSF.isEmpty()) {
                addMensaje("El afiliado no tiene registros de PQRSF");
            } else {
                PrimeFaces.current().ajax().update("frmPQRSF");
                PrimeFaces.current().executeScript("PF('dialogoPQRSF').show()");
            }
        }
        procesoFinal();
    }
    
    public void inicializarTablaPQRSF(int id) {
        this.setParamConsultaPQRSF(new ParamConsulta());
        this.getParamConsultaPQRSF().setRegistrosPagina(30);
        this.getParamConsultaPQRSF().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyPQRSF = new LazyDataModel<AusCaso>() {

            private List<AusCaso> pqrsf;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaPQRSF().getCantidadRegistros();
            }

            @Override
            public List<AusCaso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaPQRSF().setPrimerRegistro(primerRegistro);
                getParamConsultaPQRSF().setRegistrosPagina(registrosPagina);
                getParamConsultaPQRSF().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaPQRSF().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPQRSF();
                pqrsf = getListaPQRSF();
                setRowCount(getParamConsultaPQRSF().getCantidadRegistros());
                return pqrsf;
            }

            @Override
            public String getRowKey(AusCaso afiliadoPQRSF) {
                return afiliadoPQRSF.getId().toString();
            }

            @Override
            public AusCaso getRowData(String afiliadoPQRSFId) {
                Integer id = Integer.valueOf(afiliadoPQRSFId);
                for (AusCaso afiliadoPQRSF : pqrsf) {
                    if (id.equals(afiliadoPQRSF.getId())) {
                        return afiliadoPQRSF;
                    }
                }
                return null;
            }

        };

    }
    
    public void verPQRSF(AusCaso obj) {
        objetoCaso = obj;
        super.setAccion(Url.ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_VER);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerPQRSF");
            PrimeFaces.current().executeScript("PF('dialogoVerPQRSF').show()");
        }
        procesoFinal();
    }
    
    public void verListadoMIPRES(int id) {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objeto = new AsegAfiliado(id);
        inicializarTablaMIPRES(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaMiPres.isEmpty()) {
                addMensaje("El afiliado no tiene registros de MIPRES");
            } else {
                PrimeFaces.current().ajax().update("frmMIPRES");
                PrimeFaces.current().executeScript("PF('dialogoMIPRES').show()");
            }
        }
        procesoFinal();
    }
    
    public void inicializarTablaMIPRES(int id) {
        this.setParamConsultaMiPres(new ParamConsulta());
        this.getParamConsultaMiPres().setRegistrosPagina(30);
        this.getParamConsultaMiPres().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyMiPres = new LazyDataModel<MpPrescripcion>() {

            private List<MpPrescripcion> mipres;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MpPrescripcion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaMiPres().setPrimerRegistro(primerRegistro);
                getParamConsultaMiPres().setRegistrosPagina(registrosPagina);
                getParamConsultaMiPres().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaMiPres().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMIPRES();
                mipres = getListaMiPres();
                setRowCount(getParamConsultaMiPres().getCantidadRegistros());
                return mipres;
            }

            @Override
            public String getRowKey(MpPrescripcion afiliadoMiPres) {
                return afiliadoMiPres.getId().toString();
            }

            @Override
            public MpPrescripcion getRowData(String afiliadoMIPRESId) {
                Integer id = Integer.valueOf(afiliadoMIPRESId);
                for (MpPrescripcion afiliadoMIPRES : mipres) {
                    if (id.equals(afiliadoMIPRES.getId())) {
                        return afiliadoMIPRES;
                    }
                }
                return null;
            }

        };

    }
    
    public void verMIPRES(MpPrescripcion obj) {
        objetoMiPres = obj;
        super.setAccion(Url.ACCION_ADICIONAL_7);
        super.setDoAccion(Url.ACCION_VER);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerMIPRES");
            PrimeFaces.current().executeScript("PF('dialogoVerMIPRES').show()");
        }
        procesoFinal();
    }
    
    public void verListadoTutelas(int id) {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objeto = new AsegAfiliado(id);
        inicializarTablaTutelas(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaTutelas.isEmpty()) {
                addMensaje("El afiliado no tiene registros de Tutelas");
            } else {
                PrimeFaces.current().ajax().update("frmTutelas");
                PrimeFaces.current().executeScript("PF('dialogoTutelas').show()");
            }
        }
        procesoFinal();
    }
    
    public void inicializarTablaTutelas(int id) {
        this.setParamConsultaTutelas(new ParamConsulta());
        this.getParamConsultaTutelas().setRegistrosPagina(30);
        this.getParamConsultaTutelas().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyTutelas = new LazyDataModel<TuTutela>() {

            private List<TuTutela> tutela;

            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaTutelas().getCantidadRegistros();
            }
            @Override
            public List<TuTutela> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaTutelas().setPrimerRegistro(primerRegistro);
                getParamConsultaTutelas().setRegistrosPagina(registrosPagina);
                getParamConsultaTutelas().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaTutelas().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarTutelas();
                tutela = getListaTutelas();
                setRowCount(getParamConsultaTutelas().getCantidadRegistros());
                return tutela;
            }

            @Override
            public String getRowKey(TuTutela afiliadoTutela) {
                return afiliadoTutela.getId().toString();
            }

            @Override
            public TuTutela getRowData(String afiliadoTutelaId) {
                Integer id = Integer.valueOf(afiliadoTutelaId);
                for (TuTutela afiliadoTutela : tutela) {
                    if (id.equals(afiliadoTutela.getId())) {
                        return afiliadoTutela;
                    }
                }
                return null;
            }

        };

    }
    
    public void verTutela(TuTutela obj) {
        objetoTutelas = obj;
        super.setAccion(Url.ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_VER);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmVerTutela");
            PrimeFaces.current().ajax().update("frmVerTutela");
            PrimeFaces.current().executeScript("PF('dialogoVerTutela').show()");
        }
        procesoFinal();
    }
    
    public void verListadoHospitalizacion(int id) {
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objetoHospitalizacion = new AucHospitalizacion(id);
        inicializarTablaHospitalizacion(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaHospitalizacion.isEmpty()) {
                addMensaje("El afiliado no tiene registros de Hospitalización");
            } else {
                PrimeFaces.current().ajax().update("frmHospitalizacion");
                PrimeFaces.current().executeScript("PF('dialogoHospitalizacion').show()");
            }
        }
        procesoFinal();
    }
    
    public void inicializarTablaHospitalizacion(int id) {
        this.setParamConsultaHospitalizacion(new ParamConsulta());
        this.getParamConsultaHospitalizacion().setRegistrosPagina(30);
        this.getParamConsultaHospitalizacion().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyHospitalizacion = new LazyDataModel<AucHospitalizacion>() {

            private List<AucHospitalizacion> hospitalizacion;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaHospitalizacion().getCantidadRegistros();
            }
            

            @Override
            public List<AucHospitalizacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaHospitalizacion().setPrimerRegistro(primerRegistro);
                getParamConsultaHospitalizacion().setRegistrosPagina(registrosPagina);
                getParamConsultaHospitalizacion().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaHospitalizacion().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHospitalizacion();
                hospitalizacion = getListaHospitalizacion();
                setRowCount(getParamConsultaHospitalizacion().getCantidadRegistros());
                return hospitalizacion;
            }

            @Override
            public String getRowKey(AucHospitalizacion afiliadoHospitalizacion) {
                return afiliadoHospitalizacion.getId().toString();
            }

            @Override
            public AucHospitalizacion getRowData(String afiliadoHospitalizacionId) {
                Integer id = Integer.valueOf(afiliadoHospitalizacionId);
                for (AucHospitalizacion afiliadoHospitalizacion : hospitalizacion) {
                    if (id.equals(afiliadoHospitalizacion.getId())) {
                        return afiliadoHospitalizacion;
                    }
                }
                return null;
            }

        };

    }
    
    public void verHospitalizacion(AucHospitalizacion obj) {
        objetoHospitalizacion = obj;
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(Url.ACCION_VER);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerHospitalizacion");
            PrimeFaces.current().executeScript("PF('dialogoVerHospitalizacion').show()");
        }
        procesoFinal();
    }
    
    public void verListadoDireccionado(int id) {
        super.setAccion(ACCION_ADICIONAL_10);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objetoDireccionado = new PeDireccionado(id);
        inicializarTablaDireccionado(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaDireccionado.isEmpty()) {
                addMensaje("El afiliado no tiene registros Direccionados");
            } else {
                PrimeFaces.current().ajax().update("frmDireccionado");
                PrimeFaces.current().executeScript("PF('dialogoDireccionado').show()");
            }
        }
        procesoFinal();
    }
    
    public void inicializarTablaDireccionado(int id) {
        this.setParamConsultaDireccionado(new ParamConsulta());
        this.getParamConsultaDireccionado().setRegistrosPagina(30);
        this.getParamConsultaDireccionado().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyDireccionado = new LazyDataModel<PeDireccionado>() {

            private List<PeDireccionado> direccionado;
@Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaDireccionado().getCantidadRegistros();
            }
			
            @Override
            public List<PeDireccionado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaDireccionado().setPrimerRegistro(primerRegistro);
                getParamConsultaDireccionado().setRegistrosPagina(registrosPagina);
                getParamConsultaDireccionado().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaDireccionado().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDireccionado();
                direccionado = getListaDireccionado();
                setRowCount(getParamConsultaDireccionado().getCantidadRegistros());
                return direccionado;
            }

            @Override
            public String getRowKey(PeDireccionado afiliadoDireccionado) {
                return afiliadoDireccionado.getId().toString();
            }

            @Override
            public PeDireccionado getRowData(String afiliadoDireccionadoId) {
                Integer id = Integer.valueOf(afiliadoDireccionadoId);
                for (PeDireccionado afiliadoDireccionado : direccionado) {
                    if (id.equals(afiliadoDireccionado.getId())) {
                        return afiliadoDireccionado;
                    }
                }
                return null;
            }

        };

    }
    
    public void verDireccionado(PeDireccionado obj) {
        objetoDireccionado = obj;
        super.setAccion(Url.ACCION_ADICIONAL_10);
        super.setDoAccion(Url.ACCION_VER);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerDireccionado");
            PrimeFaces.current().executeScript("PF('dialogoVerDireccionado').show()");
        }
        procesoFinal();
    }
    
    public String getEstadoDireccionado(int estado) {
        return PeConstantes.obtenerEstadoDireccionado(estado).toUpperCase();
    }
    
    public void verSolicitudes(int id) {
        super.setAccion(ACCION_ADICIONAL_11);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objetoSolicitud = new AuAnexo3(id);
        inicializarTablaSolicitudes(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaSolicitudes.isEmpty()) {
                addMensaje("El afiliado no tiene registros de Solicitudes");
            } else {
                PrimeFaces.current().ajax().update("frmSolicitudes");
                PrimeFaces.current().executeScript("PF('dialogoSolicitudes').show()");
            }
        }
        procesoFinal();
    }
    
    public void inicializarTablaSolicitudes(int id) {
        this.setParamConsultaSolicitudes(new ParamConsulta());
        this.getParamConsultaSolicitudes().setRegistrosPagina(30);
        this.getParamConsultaSolicitudes().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        //2023-08-25 jyperez se adiciona la empresa del usuario para filtrar
        if (super.getConexion().getEmpresa().isAdministradora()) {
            this.getParamConsultaSolicitudes().setEmpresaId(null);
        } else {
            this.getParamConsultaSolicitudes().setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        lazySolicitud = new LazyDataModel<AuAnexo3>() {

            private List<AuAnexo3> solicitud;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSolicitudes().getCantidadRegistros();
            }
			
            @Override
            public List<AuAnexo3> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros
) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaSolicitudes().setPrimerRegistro(primerRegistro);
                getParamConsultaSolicitudes().setRegistrosPagina(registrosPagina);
                getParamConsultaSolicitudes().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaSolicitudes().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarSolicitud();
                solicitud = getListaSolicitudes();
                setRowCount(getParamConsultaSolicitudes().getCantidadRegistros());
                return solicitud;
            }

            @Override
            public String getRowKey(AuAnexo3 afiliadoSolicitud) {
                return afiliadoSolicitud.getId().toString();
            }

            @Override
            public AuAnexo3 getRowData(String afiliadoSolicitudId) {
                Integer id = Integer.valueOf(afiliadoSolicitudId);
                for (AuAnexo3 afiliadoSolicitud : solicitud) {
                    if (id.equals(afiliadoSolicitud.getId())) {
                        return afiliadoSolicitud;
                    }
                }
                return null;
            }

        };

    }
    
    public void verSolicitud(AuAnexo3 obj) {
        objetoSolicitud = obj;
        super.setAccion(Url.ACCION_ADICIONAL_11);
        super.setDoAccion(Url.ACCION_VER);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerSolicitud");
            PrimeFaces.current().executeScript("PF('dialogoVerSolicitud').show()");
        }
        procesoFinal();
    }
    
    public StreamedContent generarReporteAnexo3(AuAnexo3 obj) {
        StreamedContent streamContent = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        try {
            List<ReporteAnexo3> listaReportes = getConsultarAfiliadoServicio().generarReporteAnexo3(obj.getId(), this);
            if (!listaReportes.isEmpty()) {
                String nombre = "anexo3-" + obj.getId() + "-" + obj.getAsegAfiliadoId().getNumeroDocumento() + "-" + formato.format(obj.getFechaHoraCrea()) + ".pdf";
                InputStream is = getClass().getResourceAsStream("/reportes/Anexo3.jasper");
                if (obj.getVersion() == AuConstantes.VERSION_1) {
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
                DiaHabil diaHabil = getConsultarAfiliadoServicio().validarFechaHabil(fechaActual);
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
            if (getObjetoSolicitud().getPrioridad()) {
                dias = 2;
            }
            boolean habil = false;
            while (!habil) {
                DiaHabil diaHabil = getConsultarAfiliadoServicio().validarFechaHabil(fechaActual);
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
    
    public void verPrestacionesAfiliado(int id) {
        super.setAccion(ACCION_ADICIONAL_12);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objetoDetalleFactura = new CmDetalle(id);
        inicializarTablaPrestacionesAfiliado(id);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            if (listaCmDetalleFactura.isEmpty()) {
                addMensaje("El afiliado no tiene registros de Prestaciones.");
            } else {
                PrimeFaces.current().ajax().update("frmPrestacionesAfiliado");
                PrimeFaces.current().executeScript("PF('dialogoPrestacionesAfiliado').show()");
            }
        }
        //PENDIENTE configurar proceso final con el nuevo adicional
        procesoFinal();
    }
    
    public void inicializarTablaPrestacionesAfiliado(int id) {
        this.setParamConsultaCmDetalleFactura(new ParamConsulta());
        this.getParamConsultaCmDetalleFactura().setRegistrosPagina(30);
        this.getParamConsultaCmDetalleFactura().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        //2023-08-25 jyperez se adiciona la empresa del usuario para filtrar
        if (super.getConexion().getEmpresa().isAdministradora()) {
            this.getParamConsultaCmDetalleFactura().setEmpresaId(null);
        } else {
            this.getParamConsultaCmDetalleFactura().setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        lazyCmDetalleFactura = new LazyDataModel<CmDetalle>() {

            private List<CmDetalle> detallesFactura;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaCmDetalleFactura().getCantidadRegistros();
            }
			
            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros
) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaCmDetalleFactura().setPrimerRegistro(primerRegistro);
                getParamConsultaCmDetalleFactura().setRegistrosPagina(registrosPagina);
                getParamConsultaCmDetalleFactura().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaCmDetalleFactura().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCmDetalleFactura();
                detallesFactura = getListaCmDetalleFactura();
                setRowCount(getParamConsultaCmDetalleFactura().getCantidadRegistros());
                return detallesFactura;
            }

            @Override
            public String getRowKey(CmDetalle afiliadoSolicitud) {
                return afiliadoSolicitud.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String detalleFacturaId) {
                Integer id = Integer.valueOf(detalleFacturaId);
                for (CmDetalle detalleFactura : detallesFactura) {
                    if (id.equals(detalleFactura.getId())) {
                        return detalleFactura;
                    }
                }
                return null;
            }

        };

    }
    
    public String estiloAdministradora() {
        if (esAdministradora()) {
            return "display:block;";
        } else {
            return "display:none;";
        }
    }
    
    public boolean esAdministradora() {
        return super.getConexion().getEmpresa().isAdministradora();
    }
    
    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
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
    
    public StreamedContent generarReporteNegacion(int id) {
        AuRechazo rechazo = getConsultarAfiliadoServicio().buscarRechazo(id);
        AuReporte reporte = new AuReporte(getHashUbicaciones());
        StreamedContent streamContent = reporte.generarReporteNegacionImprimir(rechazo);
        return streamContent;
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
    
    public void verPosfechados(AuAnexo3Item item) {
        getObjetoSolicitud().setListaPosfechados(new ArrayList());
        getObjetoSolicitud().setObjetoTecnologia(item);
        getConsultarAfiliadoServicio().consultarPosfechados(this);
        if (!getObjetoSolicitud().getListaPosfechados().isEmpty()) {
            PrimeFaces.current().executeScript("PF('dialogoVerPosfechados').show()");
            PrimeFaces.current().ajax().update("frmVerPosfechados");
        }
        generarMensajes();
    }
    
    public void verBitacoras(AuAnexo3Item item) {
        getObjetoSolicitud().setObjetoTecnologia(item);
//        getObjeto().setListaBitacoras(new ArrayList());
//        getAuSolicitudServicio().verBitacoras(this);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacorass').show()");
        PrimeFaces.current().ajax().update("frmVerBitacorass");
        generarMensajes();
    }
    
    public boolean validarConsultarAdjuntos(AuAnexo3Item item) {
        boolean flag = false;
        if (AuAnexo3Item.ESTADO_CON_COTIZACION == item.getEstado()) {
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
    
    public void consultarAdjuntosSol(int id) {
        getObjetoSolicitud().setObjetoTecnologia(
                getObjetoSolicitud().getAuAnexo3ItemsList()
                        .stream()
                        .filter(item -> item.getId().equals(id))
                        .findFirst().orElse(null)
        );
        this.listaAdjuntosCotizacion = new ArrayList();
        getConsultarAfiliadoServicio().consultarAdjuntosCotizacionSol(this);

        PrimeFaces.current().executeScript("PF('dialogoVerAdjuntosItem').show()");
        PrimeFaces.current().ajax().update("frmVerAdjuntosItem");
        generarMensajes();
    }
    
    public void verTutela(AuAnexo3Tutela tutela) {
        getObjetoSolicitud().setObjetoTutela(tutela);
        PrimeFaces.current().ajax().update("dialogoVerTutelaSol");
        PrimeFaces.current().executeScript("PF('dialogoVerTutelaSol').show()");
    }
    
    public String obtenerPrioridadTexto() {
        if (getObjetoSolicitud() != null && getObjetoSolicitud().getPrioridad()) {
            return "Prioritario";
        } else {
            return "No Prioritario";
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
    
    public boolean validarEstadoItemMotivo(int estado) {
        return estado == AuAnexo3Item.ESTADO_DEVUELTO_AUDITORIA
                || estado == AuAnexo3Item.ESTADO_RECHAZO_AUDITORIA
                || estado == AuAnexo3Item.ESTADO_ANULADO_AUTORIZACION
                || estado == AuAnexo3Item.ESTADO_RECHAZO
                || estado == AuAnexo3Item.ESTADO_ANULADO;
    }
    
    public void descargarAdjuntoAnexo3(AuSolicitudAdjunto adjunto) {
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
    
    public String obtenerTipoDocumentoIps(int id) {
        try {
            return hashTipoDocumentoEmpresa.get(id).getNombre();
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
    
    public String obtenerDepartamento(int id) {
        try {
            int idPadre = getHashUbicaciones().get(id).getUbicacionPadre().getId();
            return getHashUbicaciones().get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return getHashUbicaciones().get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }
    
    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
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
    
    public void verPrograma(PeAfiliadosPrograma obj) {
        setObjetoPeAfiliadoPrograma(obj);
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_VER);
        getConsultarAfiliadoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVerPrograma");
            PrimeFaces.current().executeScript("PF('dialogoVerPrograma').show()");
        }
        procesoFinal(); 
    }
    
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = getUbicacionesRecursiva().get(id);
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }
    
    public int getTipoEstrato(int id) {
        try {
            return listaTiposEstratos.get(id);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the fechaConsulta
     */
    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * @param fechaConsulta the fechaConsulta to set
     */
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * @return the paramConsultaProgramasEspeciales
     */
    public ParamConsulta getParamConsultaProgramasEspeciales() {
        return paramConsultaProgramasEspeciales;
    }

    /**
     * @param paramConsultaProgramasEspeciales the
     * paramConsultaProgramasEspeciales to set
     */
    public void setParamConsultaProgramasEspeciales(ParamConsulta paramConsultaProgramasEspeciales) {
        this.paramConsultaProgramasEspeciales = paramConsultaProgramasEspeciales;
    }

    /**
     * @return the listaProgramasEspeciales
     */
    public List<PeAfiliadosPrograma> getListaProgramasEspeciales() {
        return listaProgramasEspeciales;
    }

    /**
     * @param listaProgramasEspeciales the listaProgramasEspeciales to set
     */
    public void setListaProgramasEspeciales(List<PeAfiliadosPrograma> listaProgramasEspeciales) {
        this.listaProgramasEspeciales = listaProgramasEspeciales;
    }

    /**
     * @return the lazyProgramasEspeciales
     */
    public LazyDataModel<PeAfiliadosPrograma> getLazyProgramasEspeciales() {
        return lazyProgramasEspeciales;
    }

    /**
     * @param lazyProgramasEspeciales the lazyProgramasEspeciales to set
     */
    public void setLazyProgramasEspeciales(LazyDataModel<PeAfiliadosPrograma> lazyProgramasEspeciales) {
        this.lazyProgramasEspeciales = lazyProgramasEspeciales;
    }

    /**
     * @return the paramConsultaAutorizaciones
     */
    public ParamConsulta getParamConsultaAutorizaciones() {
        return paramConsultaAutorizaciones;
    }

    /**
     * @param paramConsultaAutorizaciones the paramConsultaAutorizaciones to set
     */
    public void setParamConsultaAutorizaciones(ParamConsulta paramConsultaAutorizaciones) {
        this.paramConsultaAutorizaciones = paramConsultaAutorizaciones;
    }

    /**
     * @return the listaAutorizaciones
     */
    public List<AuAnexo4> getListaAutorizaciones() {
        return listaAutorizaciones;
    }

    /**
     * @param listaAutorizaciones the listaAutorizaciones to set
     */
    public void setListaAutorizaciones(List<AuAnexo4> listaAutorizaciones) {
        this.listaAutorizaciones = listaAutorizaciones;
    }

    /**
     * @return the lazyAutorizaciones
     */
    public LazyDataModel<AuAnexo4> getLazyAutorizaciones() {
        return lazyAutorizaciones;
    }

    /**
     * @param lazyAutorizaciones the lazyAutorizaciones to set
     */
    public void setLazyAutorizaciones(LazyDataModel<AuAnexo4> lazyAutorizaciones) {
        this.lazyAutorizaciones = lazyAutorizaciones;
    }

    public void clearViewScopedBeans() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("consultarAfiliadoBean");
    }

    public StreamedContent generarReporteCertificadoAfiliacion(Integer id) {
        StreamedContent streamedContent2 = null;
        try {
            setCertificadoAfiliacion(new CertificadoAfiliacion());
            getCertificadoAfiliacion().setIdAfiliado(id);
            setListaCertificadoAfiliacion((List<CertificadoAfiliacion>) new ArrayList());
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_ADICIONAL_1);
            getConsultarAfiliadoServicio().Accion(this);

            if (getAdjuntoStream() != null) {
                InputStream stream = getAdjuntoStream();
                stream.mark(0); //remember to this position!
                streamedContent2 =DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(getCertificadoAfiliacion().getNombreArchivo()).build();
            } else {
                addError("No se encontraron datos para generer el certificado de afiliación.");
                generarMensajes();
            }

        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error generarReporteCertificadoAfiliacion: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }
    
    public StreamedContent generarReporteCertificadoPortabilidad(Integer id) {
        StreamedContent streamedContent2 = null;
        try {
            setAdjuntoStream(null);
            setReporte(new ReportePortabilidad());
            //objeto = new AsegAfiliado(id); se asignó el id en este objeto en la pantalla inicial
            //getReporte().setId(id.toString()); toca obtenerlo de la ultima portabilidad registrada
            getReporte().setStrUsuarioImprime(getConexion().getUsuario().getNombre());
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_ADICIONAL_2);
            getConsultarAfiliadoServicio().Accion(this);

            if (getAdjuntoStream() != null) {
                InputStream stream = getAdjuntoStream();
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(getReporte().getNombreArchivo()).build();
            } else {
                addError("No se encontraron datos para generer el certificado de Portabilidad.");
                generarMensajes();
            }

        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error generarReporteCertificadoPortabilidad: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }
    
    public void verJustificacionEstanciaProlongada(int id) {
        objetoJustificacionEstanciasProlongada = new AucJustificacionEstanciasProlongada();
        objetoJustificacionEstanciasProlongada.setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);//para el control de las opciones internas de ver
        getConsultarAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerEstanciaDescripcion");
        PrimeFaces.current().executeScript("PF('dialogoVerEstanciaDescripcion').show()");
        procesoFinal();
    }
    
    public void verPatologia(int id) {
        objetoPatologia = new AucHospitalizacionPatologia(id);
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getConsultarAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerPatologia");
        PrimeFaces.current().executeScript("PF('dialogoVerPatologia').show()");
        procesoFinal();
    }

    public void verInoportunidad(int id) {
        objetoInoportunidad = new AucHospitalizacionInoportunidad(id);
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getConsultarAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerInoportunidad");
        PrimeFaces.current().executeScript("PF('dialogoVerInoportunidad').show()");
        procesoFinal();
    }

    public void verAdverso(int id) {
        objetoAdverso = new AucHospitalizacionAdverso(id);
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_ADICIONAL_4);
        getConsultarAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerAdverso");
        PrimeFaces.current().executeScript("PF('dialogoVerAdverso').show()");
        procesoFinal();
    }

    public void verObjecion(int id) {
        objetoObjecion = new AucHospitalizacionObjecion(id);
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_ADICIONAL_5);
        getConsultarAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerObjecion");
        PrimeFaces.current().executeScript("PF('dialogoVerObjecion').show()");
        procesoFinal();
    }

    public void verServicio(int id) {
        objetoServicio = new AucHospitalizacionServicio(id);
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_ADICIONAL_6);
        getConsultarAfiliadoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerServicio");
        PrimeFaces.current().executeScript("PF('dialogoVerServicio').show()");
        procesoFinal();
    }
    
    public void verSeguimiento(AusSeguimiento objSeguimiento) {
        setSeguimientoProcesar(objSeguimiento);
        PrimeFaces.current().ajax().update("frmVerSeguimiento");
        PrimeFaces.current().executeScript("PF('dialogoVerSeguimiento').show()");
        procesoFinal();
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
    /**
     * @return the certificadoAfiliacion
     */
    public CertificadoAfiliacion getCertificadoAfiliacion() {
        return certificadoAfiliacion;
    }

    /**
     * @param certificadoAfiliacion the certificadoAfiliacion to set
     */
    public void setCertificadoAfiliacion(CertificadoAfiliacion certificadoAfiliacion) {
        this.certificadoAfiliacion = certificadoAfiliacion;
    }

    /**
     * @return the listaCertificadoAfiliacion
     */
    public List<CertificadoAfiliacion> getListaCertificadoAfiliacion() {
        return listaCertificadoAfiliacion;
    }

    /**
     * @param listaCertificadoAfiliacion the listaCertificadoAfiliacion to set
     */
    public void setListaCertificadoAfiliacion(List<CertificadoAfiliacion> listaCertificadoAfiliacion) {
        this.listaCertificadoAfiliacion = listaCertificadoAfiliacion;
    }

    /**
     * @return the adjuntoStream
     */
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    /**
     * @param adjuntoStream the adjuntoStream to set
     */
    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    /**
     * @return the hashSubGrupoSisben
     */
    public HashMap<Integer, Maestro> getHashSubGrupoSisben() {
        return hashSubGrupoSisben;
    }

    /**
     * @param hashSubGrupoSisben the hashSubGrupoSisben to set
     */
    public void setHashSubGrupoSisben(HashMap<Integer, Maestro> hashSubGrupoSisben) {
        this.hashSubGrupoSisben = hashSubGrupoSisben;
    }

    public String getGrupoSisben(Integer subgrupo) {
        String mensaje = "";
        try {
            //2022-04-22 PENDIENTE se comenta debido a que esto es un nuevo REQ del sprint 7
            Maestro ma = new Maestro();//hashSubGrupoSisben.get(subgrupo).getMaestro();
            if (ma != null) {
                mensaje = ma.getValor();
            }
        } catch (Exception e) {

        }
        return mensaje;
    }

    public String getSubGrupoSisben(Integer subgrupo) {
        String mensaje = "";
        try {
            Maestro ma = hashSubGrupoSisben.get(subgrupo);
            if (ma != null) {
                mensaje = ma.getValor();
            }
        } catch (Exception e) {

        }
        return mensaje;
    }

    public String getEstadoReferencia(Integer estado) {
        String mensaje = "";
        try {
            Maestro ma = hashEstadosReferencia.get(estado);
            if (ma != null) {
                mensaje = ma.getValor();
            }
        } catch (Exception e) {

        }
        return mensaje;
    }

    /**
     * @return the paramConsultaReferencias
     */
    public ParamConsulta getParamConsultaReferencias() {
        return paramConsultaReferencias;
    }

    /**
     * @param paramConsultaReferencias the paramConsultaReferencias to set
     */
    public void setParamConsultaReferencias(ParamConsulta paramConsultaReferencias) {
        this.paramConsultaReferencias = paramConsultaReferencias;
    }

    /**
     * @return the listaReferencias
     */
    public List<RefAnexo9> getListaReferencias() {
        return listaReferencias;
    }

    /**
     * @param listaReferencias the listaReferencias to set
     */
    public void setListaReferencias(List<RefAnexo9> listaReferencias) {
        this.listaReferencias = listaReferencias;
    }

    /**
     * @return the lazyReferencias
     */
    public LazyDataModel<RefAnexo9> getLazyReferencias() {
        return lazyReferencias;
    }

    /**
     * @param lazyReferencias the lazyReferencias to set
     */
    public void setLazyReferencias(LazyDataModel<RefAnexo9> lazyReferencias) {
        this.lazyReferencias = lazyReferencias;
    }

    /**
     * @return the listaEstadosReferencia
     */
    public List<Maestro> getListaEstadosReferencia() {
        return listaEstadosReferencia;
    }

    /**
     * @param listaEstadosReferencia the listaEstadosReferencia to set
     */
    public void setListaEstadosReferencia(List<Maestro> listaEstadosReferencia) {
        this.listaEstadosReferencia = listaEstadosReferencia;
    }

    /**
     * @return the hashEstadosReferencia
     */
    public HashMap<Integer, Maestro> getHashEstadosReferencia() {
        return hashEstadosReferencia;
    }

    /**
     * @param hashEstadosReferencia the hashEstadosReferencia to set
     */
    public void setHashEstadosReferencia(HashMap<Integer, Maestro> hashEstadosReferencia) {
        this.hashEstadosReferencia = hashEstadosReferencia;
    }

    /**
     * @return the paramConsultaPQRSF
     */
    public ParamConsulta getParamConsultaPQRSF() {
        return paramConsultaPQRSF;
    }

    /**
     * @param paramConsultaPQRSF the paramConsultaPQRSF to set
     */
    public void setParamConsultaPQRSF(ParamConsulta paramConsultaPQRSF) {
        this.paramConsultaPQRSF = paramConsultaPQRSF;
    }

    /**
     * @return the listaPQRSF
     */
    public List<AusCaso> getListaPQRSF() {
        return listaPQRSF;
    }

    /**
     * @param listaPQRSF the listaPQRSF to set
     */
    public void setListaPQRSF(List<AusCaso> listaPQRSF) {
        this.listaPQRSF = listaPQRSF;
    }

    /**
     * @return the lazyPQRSF
     */
    public LazyDataModel<AusCaso> getLazyPQRSF() {
        return lazyPQRSF;
    }

    /**
     * @param lazyPQRSF the lazyPQRSF to set
     */
    public void setLazyPQRSF(LazyDataModel<AusCaso> lazyPQRSF) {
        this.lazyPQRSF = lazyPQRSF;
    }

    /**
     * @return the listaEstadosPQRSF
     */
    public List<Maestro> getListaEstadosPQRSF() {
        return listaEstadosPQRSF;
    }

    /**
     * @param listaEstadosPQRSF the listaEstadosPQRSF to set
     */
    public void setListaEstadosPQRSF(List<Maestro> listaEstadosPQRSF) {
        this.listaEstadosPQRSF = listaEstadosPQRSF;
    }

    /**
     * @return the hashEstadosPQRSF
     */
    public HashMap<Integer, Maestro> getHashEstadosPQRSF() {
        return hashEstadosPQRSF;
    }

    /**
     * @param hashEstadosPQRSF the hashEstadosPQRSF to set
     */
    public void setHashEstadosPQRSF(HashMap<Integer, Maestro> hashEstadosPQRSF) {
        this.hashEstadosPQRSF = hashEstadosPQRSF;
    }

    /**
     * @return the objetoCaso
     */
    public AusCaso getObjetoCaso() {
        return objetoCaso;
    }

    /**
     * @param objetoCaso the objetoCaso to set
     */
    public void setObjetoCaso(AusCaso objetoCaso) {
        this.objetoCaso = objetoCaso;
    }

    /**
     * @return the ubicacionesRecursiva
     */
    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    /**
     * @param ubicacionesRecursiva the ubicacionesRecursiva to set
     */
    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    /**
     * @return the peticionario
     */
    public String getPeticionario() {
        peticionario = "No";
        if (this.getObjetoCaso().getPeticionario() != null) {
            if (getObjetoCaso().getPeticionario().getId() != null && getObjetoCaso().getPeticionario().getId() > 0 && !getObjetoCaso().getPeticionario().getNombres().equals("")) {
                peticionario = "Si";
            }
        }
        return peticionario;
    }

    /**
     * @param peticionario the peticionario to set
     */
    public void setPeticionario(String peticionario) {
        this.peticionario = peticionario;
    }

    /**
     * @return the listaTipoPrograma
     */
    public List<Maestro> getListaTipoPrograma() {
        return listaTipoPrograma;
    }

    /**
     * @param listaTipoPrograma the listaTipoPrograma to set
     */
    public void setListaTipoPrograma(List<Maestro> listaTipoPrograma) {
        this.listaTipoPrograma = listaTipoPrograma;
    }

    /**
     * @return the paramConsultaMiPres
     */
    public ParamConsulta getParamConsultaMiPres() {
        return paramConsultaMiPres;
    }

    /**
     * @param paramConsultaMiPres the paramConsultaMiPres to set
     */
    public void setParamConsultaMiPres(ParamConsulta paramConsultaMiPres) {
        this.paramConsultaMiPres = paramConsultaMiPres;
    }

    /**
     * @return the lazyMiPres
     */
    public LazyDataModel<MpPrescripcion> getLazyMiPres() {
        return lazyMiPres;
    }

    /**
     * @param lazyMiPres the lazyMiPres to set
     */
    public void setLazyMiPres(LazyDataModel<MpPrescripcion> lazyMiPres) {
        this.lazyMiPres = lazyMiPres;
    }

    /**
     * @return the listaMiPres
     */
    public List<MpPrescripcion> getListaMiPres() {
        return listaMiPres;
    }

    /**
     * @param listaMiPres the listaMiPres to set
     */
    public void setListaMiPres(List<MpPrescripcion> listaMiPres) {
        this.listaMiPres = listaMiPres;
    }

    /**
     * @return the objetoMiPres
     */
    public MpPrescripcion getObjetoMiPres() {
        return objetoMiPres;
    }

    /**
     * @param objetoMiPres the objetoMiPres to set
     */
    public void setObjetoMiPres(MpPrescripcion objetoMiPres) {
        this.objetoMiPres = objetoMiPres;
    }

    /**
     * @return the listaMpDetalleDTO
     */
    public List<MpDetalleDTO> getListaMpDetalleDTO() {
        return listaMpDetalleDTO;
    }

    /**
     * @param listaMpDetalleDTO the listaMpDetalleDTO to set
     */
    public void setListaMpDetalleDTO(List<MpDetalleDTO> listaMpDetalleDTO) {
        this.listaMpDetalleDTO = listaMpDetalleDTO;
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

    /**
     * @return the objetoRecobrante
     */
    public MpPrescripcionRecobrante getObjetoRecobrante() {
        return objetoRecobrante;
    }

    /**
     * @param objetoRecobrante the objetoRecobrante to set
     */
    public void setObjetoRecobrante(MpPrescripcionRecobrante objetoRecobrante) {
        this.objetoRecobrante = objetoRecobrante;
    }
    
    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
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
                resultado = "Anulado Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_ANULADO_NO_DIRECCIONADO:
                resultado = "Anulado no Direccionado";
                break;
            case MpDetalleDTO.ID_ESTADO_PROGRAMADO:
                resultado = "Programado";
                break;
            case MpDetalleDTO.ID_ESTADO_ENTREGADO:
                resultado = "Entregado";
                break;
            case MpDetalleDTO.ID_ESTADO_REPORTADO:
                resultado = "Reportado";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }
    
    public void llenarRegistrosMapa(AsegAfiliado afiliado) {
//        genSimbolo01();
        registrosMapa = new DefaultMapModel<>();
        String ruta01 = AfiliadoParametro.ICONO_RUTA + AfiliadoParametro.ICONO_01;
        String ruta02 = AfiliadoParametro.ICONO_RUTA + AfiliadoParametro.ICONO_02;
        //for (MapaAfiliado obj : listaMapaAfiliados) {
        if (afiliado.getDireccionGeorefLatitud() != null && afiliado.getDireccionGeorefLongitud() != null) {
            LatLng coordenadas = new LatLng(afiliado.getDireccionGeorefLatitud().doubleValue(), afiliado.getDireccionGeorefLongitud().doubleValue());
            Marker marcador = new Marker(coordenadas, afiliado.getNombreCompleto(), afiliado.getIdAfiliado(), ruta01);
//            marcador.setIcon(getSymbol());
            registrosMapa.addOverlay(marcador);
            //actualizamos los datos de centrado del mapa
            this.setCentroLatitud(afiliado.getDireccionGeorefLatitud().doubleValue());
            this.setCentroLongitud(afiliado.getDireccionGeorefLongitud().doubleValue());
        }
        //}
        //Circulo
//        LatLng coord = new LatLng(centroLatitud, centroLongitud);
//        Circle<Long> circle1 = new Circle<>(coord, 10000);
//        circle1.setStrokeColor("#d93c3c");
//        circle1.setFillColor("#d93c3c");
//        circle1.setFillOpacity(0.1);
//        circle1.setData(1L);
//        registrosMapa.addOverlay(circle1);
    }

    public void onStateChange(StateChangeEvent event) {
        // Obtén el nuevo estado del mapa
        centroLatitud = event.getCenter().getLat();
        centroLongitud = event.getCenter().getLng();
        profundidad = event.getZoomLevel();
//        // Actualiza los marcadores
//        registrosMapa.addOverlay(new Marker(new LatLng(centroLatitud, centroLongitud), "Centro", 1001));
//        refrescar();
    }

    public List<Marker<Long>> getMarcadores() {
        return registrosMapa.getMarkers();
    }

    public void onMarkerSelect(OverlaySelectEvent<Long> event) {
        Marker<Long> marcador = (Marker) event.getOverlay();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        marcador.getData() + " ", marcador.getTitle() + " LAT: " + marcador.getLatlng().getLat() + " , LON: " + marcador.getLatlng().getLng()));
    }
    
    public void verMapaGeoreferencia() {
        PrimeFaces.current().ajax().update("frmVerGeoreferenciacion");
        PrimeFaces.current().executeScript("PF('dialogoVerGeoReferenciacion').show()");
    }
    
    public String obtenerNombreFuenteOrigen(int id) {
        return AuConstantes.obtenerNombreFuenteOrigen(id);
    }

    /**
     * @return the afiliadoContribucionSolidaria
     */
    public boolean isAfiliadoContribucionSolidaria() {
        return afiliadoContribucionSolidaria;
    }

    /**
     * @param afiliadoContribucionSolidaria the afiliadoContribucionSolidaria to set
     */
    public void setAfiliadoContribucionSolidaria(boolean afiliadoContribucionSolidaria) {
        this.afiliadoContribucionSolidaria = afiliadoContribucionSolidaria;
    }

    /**
     * @return the mensajeAfiliadoContribucionSolidaria
     */
    public String getMensajeAfiliadoContribucionSolidaria() {
        return mensajeAfiliadoContribucionSolidaria;
    }

    /**
     * @param mensajeAfiliadoContribucionSolidaria the mensajeAfiliadoContribucionSolidaria to set
     */
    public void setMensajeAfiliadoContribucionSolidaria(String mensajeAfiliadoContribucionSolidaria) {
        this.mensajeAfiliadoContribucionSolidaria = mensajeAfiliadoContribucionSolidaria;
    }

    /**
     * @return the reporte
     */
    public ReportePortabilidad getReporte() {
        return reporte;
    }

    /**
     * @param reporte the reporte to set
     */
    public void setReporte(ReportePortabilidad reporte) {
        this.reporte = reporte;
    }

    /**
     * @return the listaReportesPortabilidad
     */
    public List<ReportePortabilidad> getListaReportesPortabilidad() {
        return listaReportesPortabilidad;
    }

    /**
     * @param listaReportesPortabilidad the listaReportesPortabilidad to set
     */
    public void setListaReportesPortabilidad(List<ReportePortabilidad> listaReportesPortabilidad) {
        this.listaReportesPortabilidad = listaReportesPortabilidad;
    }

    /**
     * @return the habilitarCertPortabilidad
     */
    public boolean isHabilitarCertPortabilidad() {
        return habilitarCertPortabilidad;
    }

    /**
     * @param habilitarCertPortabilidad the habilitarCertPortabilidad to set
     */
    public void setHabilitarCertPortabilidad(boolean habilitarCertPortabilidad) {
        this.habilitarCertPortabilidad = habilitarCertPortabilidad;
    }

    /**
     * @return the mensajeCertPortabilidad
     */
    public String getMensajeCertPortabilidad() {
        return mensajeCertPortabilidad;
    }

    /**
     * @param mensajeCertPortabilidad the mensajeCertPortabilidad to set
     */
    public void setMensajeCertPortabilidad(String mensajeCertPortabilidad) {
        this.mensajeCertPortabilidad = mensajeCertPortabilidad;
    }

    /**
     * @return the objetoPeAfiliadoPrograma
     */
    public PeAfiliadosPrograma getObjetoPeAfiliadoPrograma() {
        return objetoPeAfiliadoPrograma;
    }

    /**
     * @param objetoPeAfiliadoPrograma the objetoPeAfiliadoPrograma to set
     */
    public void setObjetoPeAfiliadoPrograma(PeAfiliadosPrograma objetoPeAfiliadoPrograma) {
        this.objetoPeAfiliadoPrograma = objetoPeAfiliadoPrograma;
    }
    
    public String obtenerTipoTencnologia(int id) {
        return AuConstantes.obtenerTipoTecnologia(id);
    }

    /**
     * @return the paramConsultaTutelas
     */
    public ParamConsulta getParamConsultaTutelas() {
        return paramConsultaTutelas;
    }

    /**
     * @param paramConsultaTutelas the paramConsultaTutelas to set
     */
    public void setParamConsultaTutelas(ParamConsulta paramConsultaTutelas) {
        this.paramConsultaTutelas = paramConsultaTutelas;
    }

    /**
     * @return the listaTutelas
     */
    public List<TuTutela> getListaTutelas() {
        return listaTutelas;
    }

    /**
     * @param listaTutelas the listaTutelas to set
     */
    public void setListaTutelas(List<TuTutela> listaTutelas) {
        this.listaTutelas = listaTutelas;
    }

    /**
     * @return the lazyTutelas
     */
    public LazyDataModel<TuTutela> getLazyTutelas() {
        return lazyTutelas;
    }

    /**
     * @param lazyTutelas the lazyTutelas to set
     */
    public void setLazyTutelas(LazyDataModel<TuTutela> lazyTutelas) {
        this.lazyTutelas = lazyTutelas;
    }

    /**
     * @return the objetoTutelas
     */
    public TuTutela getObjetoTutelas() {
        return objetoTutelas;
    }

    /**
     * @param objetoTutelas the objetoTutelas to set
     */
    public void setObjetoTutelas(TuTutela objetoTutelas) {
        this.objetoTutelas = objetoTutelas;
    }

    /**
     * @return the listaEstadoProceso
     */
    public List<Maestro> getListaEstadoProceso() {
        return listaEstadoProceso;
    }

    /**
     * @param listaEstadoProceso the listaEstadoProceso to set
     */
    public void setListaEstadoProceso(List<Maestro> listaEstadoProceso) {
        this.listaEstadoProceso = listaEstadoProceso;
    }

    /**
     * @return the hashEstadoProceso
     */
    public HashMap<Integer, Maestro> getHashEstadoProceso() {
        return hashEstadoProceso;
    }

    /**
     * @param hashEstadoProceso the hashEstadoProceso to set
     */
    public void setHashEstadoProceso(HashMap<Integer, Maestro> hashEstadoProceso) {
        this.hashEstadoProceso = hashEstadoProceso;
    }

    /**
     * @return the listaSeguimientos
     */
    public List<TuSeguimiento> getListaSeguimientos() {
        return listaSeguimientos;
    }

    /**
     * @param listaSeguimientos the listaSeguimientos to set
     */
    public void setListaSeguimientos(List<TuSeguimiento> listaSeguimientos) {
        this.listaSeguimientos = listaSeguimientos;
    }

    /**
     * @return the objetoAutorizacion
     */
    public AuAnexo4 getObjetoAutorizacion() {
        return objetoAutorizacion;
    }

    /**
     * @param objetoAutorizacion the objetoAutorizacion to set
     */
    public void setObjetoAutorizacion(AuAnexo4 objetoAutorizacion) {
        this.objetoAutorizacion = objetoAutorizacion;
    }

    /**
     * @return the rutaAnexo2
     */
    public String getRutaAnexo2() {
        return rutaAnexo2;
    }

    /**
     * @param rutaAnexo2 the rutaAnexo2 to set
     */
    public void setRutaAnexo2(String rutaAnexo2) {
        this.rutaAnexo2 = rutaAnexo2;
    }

    /**
     * @return the rutaAnexo3
     */
    public String getRutaAnexo3() {
        return rutaAnexo3;
    }

    /**
     * @param rutaAnexo3 the rutaAnexo3 to set
     */
    public void setRutaAnexo3(String rutaAnexo3) {
        this.rutaAnexo3 = rutaAnexo3;
    }

    /**
     * @return the afiliadoSinEncuestaSisben4
     */
    public boolean isAfiliadoSinEncuestaSisben4() {
        return afiliadoSinEncuestaSisben4;
    }

    /**
     * @param afiliadoSinEncuestaSisben4 the afiliadoSinEncuestaSisben4 to set
     */
    public void setAfiliadoSinEncuestaSisben4(boolean afiliadoSinEncuestaSisben4) {
        this.afiliadoSinEncuestaSisben4 = afiliadoSinEncuestaSisben4;
    }

    /**
     * @return the mensajeAfiliadoSinEncuestaSisben4
     */
    public String getMensajeAfiliadoSinEncuestaSisben4() {
        return mensajeAfiliadoSinEncuestaSisben4;
    }

    /**
     * @param mensajeAfiliadoSinEncuestaSisben4 the mensajeAfiliadoSinEncuestaSisben4 to set
     */
    public void setMensajeAfiliadoSinEncuestaSisben4(String mensajeAfiliadoSinEncuestaSisben4) {
        this.mensajeAfiliadoSinEncuestaSisben4 = mensajeAfiliadoSinEncuestaSisben4;
    }

    /**
     * @return the paramConsultaHospitalizacion
     */
    public ParamConsulta getParamConsultaHospitalizacion() {
        return paramConsultaHospitalizacion;
    }

    /**
     * @param paramConsultaHospitalizacion the paramConsultaHospitalizacion to set
     */
    public void setParamConsultaHospitalizacion(ParamConsulta paramConsultaHospitalizacion) {
        this.paramConsultaHospitalizacion = paramConsultaHospitalizacion;
    }

    /**
     * @return the listaHospitalizacion
     */
    public List<AucHospitalizacion> getListaHospitalizacion() {
        return listaHospitalizacion;
    }

    /**
     * @param listaHospitalizacion the listaHospitalizacion to set
     */
    public void setListaHospitalizacion(List<AucHospitalizacion> listaHospitalizacion) {
        this.listaHospitalizacion = listaHospitalizacion;
    }

    /**
     * @return the lazyHospitalizacion
     */
    public LazyDataModel<AucHospitalizacion> getLazyHospitalizacion() {
        return lazyHospitalizacion;
    }

    /**
     * @param lazyHospitalizacion the lazyHospitalizacion to set
     */
    public void setLazyHospitalizacion(LazyDataModel<AucHospitalizacion> lazyHospitalizacion) {
        this.lazyHospitalizacion = lazyHospitalizacion;
    }

    /**
     * @return the objetoHospitalizacion
     */
    public AucHospitalizacion getObjetoHospitalizacion() {
        return objetoHospitalizacion;
    }

    /**
     * @param objetoHospitalizacion the objetoHospitalizacion to set
     */
    public void setObjetoHospitalizacion(AucHospitalizacion objetoHospitalizacion) {
        this.objetoHospitalizacion = objetoHospitalizacion;
    }

    /**
     * @return the hashUbicaciones
     */
    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    /**
     * @param hashUbicaciones the hashUbicaciones to set
     */
    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
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
     * @return the paramConsultaDireccionado
     */
    public ParamConsulta getParamConsultaDireccionado() {
        return paramConsultaDireccionado;
    }

    /**
     * @param paramConsultaDireccionado the paramConsultaDireccionado to set
     */
    public void setParamConsultaDireccionado(ParamConsulta paramConsultaDireccionado) {
        this.paramConsultaDireccionado = paramConsultaDireccionado;
    }

    /**
     * @return the listaDireccionado
     */
    public List<PeDireccionado> getListaDireccionado() {
        return listaDireccionado;
    }

    /**
     * @param listaDireccionado the listaDireccionado to set
     */
    public void setListaDireccionado(List<PeDireccionado> listaDireccionado) {
        this.listaDireccionado = listaDireccionado;
    }

    /**
     * @return the lazyDireccionado
     */
    public LazyDataModel<PeDireccionado> getLazyDireccionado() {
        return lazyDireccionado;
    }

    /**
     * @param lazyDireccionado the lazyDireccionado to set
     */
    public void setLazyDireccionado(LazyDataModel<PeDireccionado> lazyDireccionado) {
        this.lazyDireccionado = lazyDireccionado;
    }

    /**
     * @return the objetoDireccionado
     */
    public PeDireccionado getObjetoDireccionado() {
        return objetoDireccionado;
    }

    /**
     * @param objetoDireccionado the objetoDireccionado to set
     */
    public void setObjetoDireccionado(PeDireccionado objetoDireccionado) {
        this.objetoDireccionado = objetoDireccionado;
    }

    /**
     * @return the listaTipoDocumentoEmpresa
     */
    public List<Maestro> getListaTipoDocumentoEmpresa() {
        return listaTipoDocumentoEmpresa;
    }

    /**
     * @param listaTipoDocumentoEmpresa the listaTipoDocumentoEmpresa to set
     */
    public void setListaTipoDocumentoEmpresa(List<Maestro> listaTipoDocumentoEmpresa) {
        this.listaTipoDocumentoEmpresa = listaTipoDocumentoEmpresa;
    }

    /**
     * @return the hashTipoDocumentoEmpresa
     */
    public HashMap<Integer, Maestro> getHashTipoDocumentoEmpresa() {
        return hashTipoDocumentoEmpresa;
    }

    /**
     * @param hashTipoDocumentoEmpresa the hashTipoDocumentoEmpresa to set
     */
    public void setHashTipoDocumentoEmpresa(HashMap<Integer, Maestro> hashTipoDocumentoEmpresa) {
        this.hashTipoDocumentoEmpresa = hashTipoDocumentoEmpresa;
    }

    /**
     * @return the listaTipoGestion
     */
    public List<Maestro> getListaTipoGestion() {
        return listaTipoGestion;
    }

    /**
     * @param listaTipoGestion the listaTipoGestion to set
     */
    public void setListaTipoGestion(List<Maestro> listaTipoGestion) {
        this.listaTipoGestion = listaTipoGestion;
    }

    /**
     * @return the hashTipoGestion
     */
    public HashMap<Integer, Maestro> getHashTipoGestion() {
        return hashTipoGestion;
    }

    /**
     * @param hashTipoGestion the hashTipoGestion to set
     */
    public void setHashTipoGestion(HashMap<Integer, Maestro> hashTipoGestion) {
        this.hashTipoGestion = hashTipoGestion;
    }

    /**
     * @return the listaEstadosDireccionado
     */
    public List<Maestro> getListaEstadosDireccionado() {
        return listaEstadosDireccionado;
    }

    /**
     * @param listaEstadosDireccionado the listaEstadosDireccionado to set
     */
    public void setListaEstadosDireccionado(List<Maestro> listaEstadosDireccionado) {
        this.listaEstadosDireccionado = listaEstadosDireccionado;
    }

    /**
     * @return the hashEstadosDireccionado
     */
    public HashMap<Integer, Maestro> getHashEstadosDireccionado() {
        return hashEstadosDireccionado;
    }

    /**
     * @param hashEstadosDireccionado the hashEstadosDireccionado to set
     */
    public void setHashEstadosDireccionado(HashMap<Integer, Maestro> hashEstadosDireccionado) {
        this.hashEstadosDireccionado = hashEstadosDireccionado;
    }

    /**
     * @return the objetoAnexo3
     */
    public AuAnexo3 getObjetoAnexo3() {
        return objetoAnexo3;
    }

    /**
     * @param objetoAnexo3 the objetoAnexo3 to set
     */
    public void setObjetoAnexo3(AuAnexo3 objetoAnexo3) {
        this.objetoAnexo3 = objetoAnexo3;
    }
    
    public String obtenerBoolean(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    /**
     * @return the listaFuenteOrigen
     */
    public List<Maestro> getListaFuenteOrigen() {
        return listaFuenteOrigen;
    }

    /**
     * @param listaFuenteOrigen the listaFuenteOrigen to set
     */
    public void setListaFuenteOrigen(List<Maestro> listaFuenteOrigen) {
        this.listaFuenteOrigen = listaFuenteOrigen;
    }

    /**
     * @return the objetoJustificacionEstanciasProlongada
     */
    public AucJustificacionEstanciasProlongada getObjetoJustificacionEstanciasProlongada() {
        return objetoJustificacionEstanciasProlongada;
    }

    /**
     * @param objetoJustificacionEstanciasProlongada the objetoJustificacionEstanciasProlongada to set
     */
    public void setObjetoJustificacionEstanciasProlongada(AucJustificacionEstanciasProlongada objetoJustificacionEstanciasProlongada) {
        this.objetoJustificacionEstanciasProlongada = objetoJustificacionEstanciasProlongada;
    }

    /**
     * @return the objetoPatologia
     */
    public AucHospitalizacionPatologia getObjetoPatologia() {
        return objetoPatologia;
    }

    /**
     * @param objetoPatologia the objetoPatologia to set
     */
    public void setObjetoPatologia(AucHospitalizacionPatologia objetoPatologia) {
        this.objetoPatologia = objetoPatologia;
    }

    /**
     * @return the objetoInoportunidad
     */
    public AucHospitalizacionInoportunidad getObjetoInoportunidad() {
        return objetoInoportunidad;
    }

    /**
     * @param objetoInoportunidad the objetoInoportunidad to set
     */
    public void setObjetoInoportunidad(AucHospitalizacionInoportunidad objetoInoportunidad) {
        this.objetoInoportunidad = objetoInoportunidad;
    }

    /**
     * @return the objetoAdverso
     */
    public AucHospitalizacionAdverso getObjetoAdverso() {
        return objetoAdverso;
    }

    /**
     * @param objetoAdverso the objetoAdverso to set
     */
    public void setObjetoAdverso(AucHospitalizacionAdverso objetoAdverso) {
        this.objetoAdverso = objetoAdverso;
    }

    /**
     * @return the objetoObjecion
     */
    public AucHospitalizacionObjecion getObjetoObjecion() {
        return objetoObjecion;
    }

    /**
     * @param objetoObjecion the objetoObjecion to set
     */
    public void setObjetoObjecion(AucHospitalizacionObjecion objetoObjecion) {
        this.objetoObjecion = objetoObjecion;
    }

    /**
     * @return the objetoServicio
     */
    public AucHospitalizacionServicio getObjetoServicio() {
        return objetoServicio;
    }

    /**
     * @param objetoServicio the objetoServicio to set
     */
    public void setObjetoServicio(AucHospitalizacionServicio objetoServicio) {
        this.objetoServicio = objetoServicio;
    }

    /**
     * @return the headerDialogVer
     */
    public String getHeaderDialogVer() {
        return headerDialogVer;
    }

    /**
     * @param headerDialogVer the headerDialogVer to set
     */
    public void setHeaderDialogVer(String headerDialogVer) {
        this.headerDialogVer = headerDialogVer;
    }

    /**
     * @return the observacionGenerica
     */
    public String getObservacionGenerica() {
        return observacionGenerica;
    }

    /**
     * @param observacionGenerica the observacionGenerica to set
     */
    public void setObservacionGenerica(String observacionGenerica) {
        this.observacionGenerica = observacionGenerica;
    }

    /**
     * @return the listaAuAnexo4Items
     */
    public List<AuAnexo4Item> getListaAuAnexo4Items() {
        return listaAuAnexo4Items;
    }

    /**
     * @param listaAuAnexo4Items the listaAuAnexo4Items to set
     */
    public void setListaAuAnexo4Items(List<AuAnexo4Item> listaAuAnexo4Items) {
        this.listaAuAnexo4Items = listaAuAnexo4Items;
    }

    /**
     * @return the objetoItem
     */
    public AuAnexo4Item getObjetoItem() {
        return objetoItem;
    }

    /**
     * @param objetoItem the objetoItem to set
     */
    public void setObjetoItem(AuAnexo4Item objetoItem) {
        this.objetoItem = objetoItem;
    }

    /**
     * @return the listaBitacoras
     */
    public List<AuItemBitacora> getListaBitacoras() {
        return listaBitacoras;
    }

    /**
     * @param listaBitacoras the listaBitacoras to set
     */
    public void setListaBitacoras(List<AuItemBitacora> listaBitacoras) {
        this.listaBitacoras = listaBitacoras;
    }

    /**
     * @return the listaAdjuntosCotizacion
     */
    public List<AuSolicitudAdjunto> getListaAdjuntosCotizacion() {
        return listaAdjuntosCotizacion;
    }

    /**
     * @param listaAdjuntosCotizacion the listaAdjuntosCotizacion to set
     */
    public void setListaAdjuntosCotizacion(List<AuSolicitudAdjunto> listaAdjuntosCotizacion) {
        this.listaAdjuntosCotizacion = listaAdjuntosCotizacion;
    }

//    /**
//     * @return the aseguramientoSingle
//     */
//    public AseguramientoSingle getAseguramientoSingle() {
//        return aseguramientoSingle;
//    }
//
//    /**
//     * @param aseguramientoSingle the aseguramientoSingle to set
//     */
//    public void setAseguramientoSingle(AseguramientoSingle aseguramientoSingle) {
//        this.aseguramientoSingle = aseguramientoSingle;
//    }

    /**
     * @return the paramConsultaSolicitudes
     */
    public ParamConsulta getParamConsultaSolicitudes() {
        return paramConsultaSolicitudes;
    }

    /**
     * @param paramConsultaSolicitudes the paramConsultaSolicitudes to set
     */
    public void setParamConsultaSolicitudes(ParamConsulta paramConsultaSolicitudes) {
        this.paramConsultaSolicitudes = paramConsultaSolicitudes;
    }

    /**
     * @return the listaSolicitudes
     */
    public List<AuAnexo3> getListaSolicitudes() {
        return listaSolicitudes;
    }

    /**
     * @param listaSolicitudes the listaSolicitudes to set
     */
    public void setListaSolicitudes(List<AuAnexo3> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    /**
     * @return the lazySolicitud
     */
    public LazyDataModel<AuAnexo3> getLazySolicitud() {
        return lazySolicitud;
    }

    /**
     * @param lazySolicitud the lazySolicitud to set
     */
    public void setLazySolicitud(LazyDataModel<AuAnexo3> lazySolicitud) {
        this.lazySolicitud = lazySolicitud;
    }

    /**
     * @return the objetoSolicitud
     */
    public AuAnexo3 getObjetoSolicitud() {
        return objetoSolicitud;
    }

    /**
     * @param objetoSolicitud the objetoSolicitud to set
     */
    public void setObjetoSolicitud(AuAnexo3 objetoSolicitud) {
        this.objetoSolicitud = objetoSolicitud;
    }

    /**
     * @return the telefonoFijoAfiliado
     */
    public String getTelefonoFijoAfiliado() {
        return telefonoFijoAfiliado;
    }

    /**
     * @param telefonoFijoAfiliado the telefonoFijoAfiliado to set
     */
    public void setTelefonoFijoAfiliado(String telefonoFijoAfiliado) {
        this.telefonoFijoAfiliado = telefonoFijoAfiliado;
    }

    /**
     * @return the telefonoMovilAfiliado
     */
    public String getTelefonoMovilAfiliado() {
        return telefonoMovilAfiliado;
    }

    /**
     * @param telefonoMovilAfiliado the telefonoMovilAfiliado to set
     */
    public void setTelefonoMovilAfiliado(String telefonoMovilAfiliado) {
        this.telefonoMovilAfiliado = telefonoMovilAfiliado;
    }

    /**
     * @return the listaTipoCargue
     */
    public List<Maestro> getListaTipoCargue() {
        return listaTipoCargue;
    }

    /**
     * @param listaTipoCargue the listaTipoCargue to set
     */
    public void setListaTipoCargue(List<Maestro> listaTipoCargue) {
        this.listaTipoCargue = listaTipoCargue;
    }

    /**
     * @return the hashTipoCargue
     */
    public HashMap<Integer, Maestro> getHashTipoCargue() {
        return hashTipoCargue;
    }

    /**
     * @param hashTipoCargue the hashTipoCargue to set
     */
    public void setHashTipoCargue(HashMap<Integer, Maestro> hashTipoCargue) {
        this.hashTipoCargue = hashTipoCargue;
    }

    /**
     * @return the tituloMensaje
     */
    public String getTituloMensaje() {
        return tituloMensaje;
    }

    /**
     * @param tituloMensaje the tituloMensaje to set
     */
    public void setTituloMensaje(String tituloMensaje) {
        this.tituloMensaje = tituloMensaje;
    }

    /**
     * @return the subtituloMensaje
     */
    public String getSubtituloMensaje() {
        return subtituloMensaje;
    }

    /**
     * @param subtituloMensaje the subtituloMensaje to set
     */
    public void setSubtituloMensaje(String subtituloMensaje) {
        this.subtituloMensaje = subtituloMensaje;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the listaAfiliadoPrograma
     */
    public List<PeAfiliadosPrograma> getListaAfiliadoPrograma() {
        return listaAfiliadoPrograma;
    }

    /**
     * @param listaAfiliadoPrograma the listaAfiliadoPrograma to set
     */
    public void setListaAfiliadoPrograma(List<PeAfiliadosPrograma> listaAfiliadoPrograma) {
        this.listaAfiliadoPrograma = listaAfiliadoPrograma;
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
     * @return the paramConsultaCmDetalleFactura
     */
    public ParamConsulta getParamConsultaCmDetalleFactura() {
        return paramConsultaCmDetalleFactura;
    }

    /**
     * @param paramConsultaCmDetalleFactura the paramConsultaCmDetalleFactura to set
     */
    public void setParamConsultaCmDetalleFactura(ParamConsulta paramConsultaCmDetalleFactura) {
        this.paramConsultaCmDetalleFactura = paramConsultaCmDetalleFactura;
    }

    /**
     * @return the listaCmDetalleFactura
     */
    public List<CmDetalle> getListaCmDetalleFactura() {
        return listaCmDetalleFactura;
    }

    /**
     * @param listaCmDetalleFactura the listaCmDetalleFactura to set
     */
    public void setListaCmDetalleFactura(List<CmDetalle> listaCmDetalleFactura) {
        this.listaCmDetalleFactura = listaCmDetalleFactura;
    }

    /**
     * @return the lazyCmDetalleFactura
     */
    public LazyDataModel<CmDetalle> getLazyCmDetalleFactura() {
        return lazyCmDetalleFactura;
    }

    /**
     * @param lazyCmDetalleFactura the lazyCmDetalleFactura to set
     */
    public void setLazyCmDetalleFactura(LazyDataModel<CmDetalle> lazyCmDetalleFactura) {
        this.lazyCmDetalleFactura = lazyCmDetalleFactura;
    }

    /**
     * @return the objetoDetalleFactura
     */
    public CmDetalle getObjetoDetalleFactura() {
        return objetoDetalleFactura;
    }

    /**
     * @param objetoDetalleFactura the objetoDetalleFactura to set
     */
    public void setObjetoDetalleFactura(CmDetalle objetoDetalleFactura) {
        this.objetoDetalleFactura = objetoDetalleFactura;
    }

    /**
     * @return the seguimientoProcesar
     */
    public AusSeguimiento getSeguimientoProcesar() {
        return seguimientoProcesar;
    }

    /**
     * @param seguimientoProcesar the seguimientoProcesar to set
     */
    public void setSeguimientoProcesar(AusSeguimiento seguimientoProcesar) {
        this.seguimientoProcesar = seguimientoProcesar;
    }

    /**
     * @return the registrosMapa
     */
    public MapModel<Long> getRegistrosMapa() {
        return registrosMapa;
    }

    /**
     * @param registrosMapa the registrosMapa to set
     */
    public void setRegistrosMapa(MapModel<Long> registrosMapa) {
        this.registrosMapa = registrosMapa;
    }

    /**
     * @return the centroLatitud
     */
    public double getCentroLatitud() {
        return centroLatitud;
    }

    /**
     * @param centroLatitud the centroLatitud to set
     */
    public void setCentroLatitud(double centroLatitud) {
        this.centroLatitud = centroLatitud;
    }

    /**
     * @return the centroLongitud
     */
    public double getCentroLongitud() {
        return centroLongitud;
    }

    /**
     * @param centroLongitud the centroLongitud to set
     */
    public void setCentroLongitud(double centroLongitud) {
        this.centroLongitud = centroLongitud;
    }

    /**
     * @return the profundidad
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     * @param profundidad the profundidad to set
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     * @return the rutaGoogleMapsEmpresarial
     */
    public String getRutaGoogleMapsEmpresarial() {
        return rutaGoogleMapsEmpresarial;
    }

    /**
     * @param rutaGoogleMapsEmpresarial the rutaGoogleMapsEmpresarial to set
     */
    public void setRutaGoogleMapsEmpresarial(String rutaGoogleMapsEmpresarial) {
        this.rutaGoogleMapsEmpresarial = rutaGoogleMapsEmpresarial;
    }
}
