/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.auditoria.concurrente.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAfiliadoContacto;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucDiagnostico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionAdverso;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionEstancia;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionHistorico;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionInoportunidad;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionObjecion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionPatologia;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionSeguimiento;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionServicio;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucJustificacionEstanciasProlongada;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.ReporteActaJustificacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.ReporteHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.especial.PeAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeSugeridoAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.auditoria.concurrente.servicio.AucHospitalizacionServiceIface;
import com.saviasaludeps.savia.web.auditoria.concurrente.servicio.AucHospitalizacionServicioImpl;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Validacion;
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
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
 * @author sgiraldov
 */
@Named
@ViewScoped
public class AucHospitalizacionBean extends Url {

    @Autowired
    private AucHospitalizacionServiceIface aucHospitalizacionServicio;
    private AucHospitalizacion objeto;
    private List<AucHospitalizacion> registros;
    private LazyDataModel<AucHospitalizacion> lazyAucHospitalizacion;

    //Listas auxiliares
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaTipoDocumentoEmpresa;
    private List<Maestro> listaGeneroAfiliado;
    private List<Maestro> listaEstadosAfiliado;
    private List<Maestro> listaRegimenAfiliado;
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaTiposIngreso;
    private HashMap<Integer, Maestro> hashTiposIngreso;
    private List<Maestro> listaCtnModalidades;
    private HashMap<Integer, Maestro> hashCtnModalidades;
    private List<Maestro> listaRemisionesNoPertinentes;
    private HashMap<Integer, Maestro> hashRemisionesNoPertinentes;
    private List<Maestro> listaTiposDiagnostico;
    private HashMap<Integer, Maestro> hashTiposDiagnostico;
    private List<Maestro> listaTipoSeguimiento;
    private HashMap<Integer, Maestro> hashTipoSeguimiento;
    private List<Maestro> listaServicio;
    private HashMap<Integer, Maestro> hashServicio;
    private List<Maestro> listaCausaEstancia;
    private HashMap<Integer, Maestro> hashCausaEstancia;
    private List<Maestro> listaPropuestaIntervencion;
    private HashMap<Integer, Maestro> hashPropuestaIntervencion;
    private List<Maestro> listaPatologia;
    private HashMap<Integer, Maestro> hashPatologia;
    private List<Maestro> listaTipoInoportunidad;
    private HashMap<Integer, Maestro> hashTipoInoportunidad;
    private List<Maestro> listaCategoriaEvento;
    private HashMap<Integer, Maestro> hashCategoriaEvento;
    private List<Maestro> listaSubcategoriaEvento;
    private HashMap<Integer, Maestro> hashSubcategoriaEvento;
    private List<Maestro> listaConclusionEvento;
    private HashMap<Integer, Maestro> hashConclusionEveno;
    private List<Maestro> listaDestinoEgreso;
    private HashMap<Integer, Maestro> hashDestinoEgreso;
    private List<Maestro> listaDestinoGR;
    private HashMap<Integer, Maestro> hashDestinoGR;
    private List<Maestro> listaConductaEgreso;
    private HashMap<Integer, Maestro> hashConductaEgreso;
    private List<Maestro> listCausasActivo;
    private HashMap<Integer, Maestro> hashCausasActivo;
    private List<Maestro> listCausasInactivo;
    private HashMap<Integer, Maestro> hashCausasInActivo;
    private List<Maestro> listaMotivoInoportunidad;
    private HashMap<Integer, Maestro> hashMotivoInoportunidad;
    private HashMap<Integer, PePrograma> hashPePrograma;
    private List<Maestro> listaTiposSugeridoAdjuntos;
    private HashMap<Integer, Maestro> hashTiposSugeridoAdjuntos;
    private List<PePrograma> listaProgramasEspecialesRescate;
    private HashMap<Integer, PePrograma> hashProgramasEspecialesRescate;
    private List<Maestro> listaCausaIngresoPrevenibles;
    private HashMap<Integer, Maestro> hashCausaIngresoPrevenible;
    private List<Maestro> listaAreaResponsableIngresoPrevenible;
    private HashMap<Integer, Maestro> hashAreaResponsableIngresoPrevenible;
    private List<Maestro> listaSeguimientoGestores;
    private HashMap<Integer, Maestro> hashSeguimientoGestores;
    private List<Maestro> listaSeguimientoEstado;
    private HashMap<Integer, Maestro> hashSeguimientoEstado;
    private List<Maestro> listaMotivoReingreso;
    private HashMap<Integer, Maestro> hashMotivoReingreso;
    private List<Maestro> listaProgramaAltaTemprana;
    private HashMap<Integer, Maestro> hashProgramaAltaTemprana;
    
    //Variables auxiliares
    private List<AsegAfiliado> listaAfiliados;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
    private List<CntPrestadorSede> listaIps;
    private LazyDataModel<CntPrestadorSede> lazyIps;
    private List<Ubicacion> listaCiudades;
    private List<Usuario> listaUsuarios;

    private List<AucAfiliadoContacto> listaTuPersonaContacto;
    private List<AsegAfiliadoContacto> listaContactosAseg;
    private List<AsegAfiliadoContacto> listaContactos;
    private List<PeAfiliadoSugerido> listaSegueridoMemoria;
    private List<PeTelefono> listaContactosPrograma;
    private List<PeTelefono> listaContactosBorrar;
    private List<AucDiagnostico> listaDiagnosticosBorrar;
    private List<AucDiagnostico> listaDiagnosticosEgresoBorrar;
    private List<AucDiagnostico> listaDiagnosticosEstanciaEspecialidadBorrar;
    private List<AuAnexo2Rescate> listaHistorialRescate;
    private List<AucHospitalizacionHistorico> listaHistorialEgreso;
    private List<AucHospitalizacionServicio> listaMemoriaTecnologia;
    private AucHospitalizacionSeguimiento objetoSeguimiento;
    private AucHospitalizacionEstancia objetoEstancia;
    private AucHospitalizacionPatologia objetoPatologia;
    private AucHospitalizacionInoportunidad objetoInoportunidad;
    private AucHospitalizacionAdverso objetoAdverso;
    private AucHospitalizacionObjecion objetoObjecion;
    private AucHospitalizacionServicio objetoServicio;
    private AucHospitalizacion objetoHospitalizacionAplicaRescate;
    private AucDiagnostico objetoDiagnostico;
    private AucAfiliadoContacto aucAfiliadoContacto;
    private AucJustificacionEstanciasProlongada objetoJustificacionEstanciasProlongada;
    private CntPrestadorSede objPrestadorSede;
    private PeAfiliadosPrograma objetoPePregrama;
    private PeAfiliadoSugerido objetoSugerido;
    private AuAnexo2Rescate objetoRescate;
    private List<PeAdjunto> peAdjuntos;
    private PeAdjunto objectoAdjunto;
    private Date fechaActual;
    private String telefonoFijoAfiliado;
    private String telefonoMovilAfiliado;
    private String descripcionGenerica;
    private UploadedFile archivoAdjunto;
    private int contadorArchivos = 0;
    private int sizeLimitByAnexo;
    private int maxCantAnexos;

    private boolean isObjecion;
    private int isIngresoOgreso;
    private boolean isEgresoDiagnosticoEspecialidad;
    private boolean habilitarEgresoNumeroCertificado;
    private boolean campoObligatorioNumeroCertificado;
    private boolean crearOgestionarDiagnostico;
    private boolean bloquearBotonesCierreAuditoria;
    private boolean habilitarCampoValorEstancia;
    private boolean CampoObligatorioValorEstancia;
    private boolean habilitarFechaEgreso;
    private boolean habilitarCampoEmpresaAdministra;
    private boolean habilitarCampoImprimirActa;
    private boolean habilitarCampoMotivoInoportidad;
    private boolean campoObligatorioMotivoInoportidad;
    private boolean habilitarAreaResponsableIngresoPrevenible;
    private boolean campoObligatorioAreaResponsableIngresoPrevenible;

    private SelDiagnosticosBean diagnosticosBean;
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;
    private SelEspecialidadesBean especiliadaBean;
            
    public static final int MILISECONDS_BY_DAY = 86400000;
    public static final int MAXIMO_CANTIDAD_ARCHIVOS = 5;
    public static final int MAXIMO_TAMANO_ARCHIVOS = 15000000;

    /* @PreDestroy
    public void preDestroy() {
        this.hashUbicaciones = null;
        this.objeto = null;
    }*/
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public AucHospitalizacionBean() {
        this.objeto = new AucHospitalizacion();
        this.fechaActual = new Date();
        setMaxCantAnexos(MAXIMO_CANTIDAD_ARCHIVOS);
        setSizeLimitByAnexo(MAXIMO_TAMANO_ARCHIVOS);
        Modulo _mod = super.validarModulo(Modulo.ID_AUC_HOSPITALIZACION);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta()); // revisar si se esta utilizando el lzay personas
        super.addListaParamConsultas(new ParamConsulta());
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAucHospitalizacion = new LazyDataModel<AucHospitalizacion>() {

                private List<AucHospitalizacion> lista;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

                @Override
                public List<AucHospitalizacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AucHospitalizacion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AucHospitalizacion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AucHospitalizacion objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            //Afiliado
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
                    afiliados = getListaAfiliados();
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
            // Lazy Ips
            lazyIps = new LazyDataModel<CntPrestadorSede>() {
                private List<CntPrestadorSede> listaIps;

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
                    listaIps = getListaIps();
                    setRowCount(getParamConsulta(2).getCantidadRegistros());
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
        }
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAucHospitalizacionServicio().cargasInicial(this);
        listar();
        //listar();
    }

    public AucHospitalizacionServiceIface getAucHospitalizacionServicio() {
        if (aucHospitalizacionServicio == null) {
            aucHospitalizacionServicio = new AucHospitalizacionServicioImpl();
        }
        return aucHospitalizacionServicio;
    }

    public void setAucHospitalizacionServicio(AucHospitalizacionServiceIface aucHospitalizacionServicio) {
        this.aucHospitalizacionServicio = aucHospitalizacionServicio;
    }

    public AucHospitalizacion getObjeto() {
        return objeto;
    }

    public void setObjeto(AucHospitalizacion objeto) {
        this.objeto = objeto;
    }

    public List<AucHospitalizacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AucHospitalizacion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AucHospitalizacion> getLazyAucHospitalizacion() {
        return lazyAucHospitalizacion;
    }

    public void setLazyAucHospitalizacion(LazyDataModel<AucHospitalizacion> lazyAucHospitalizacion) {
        this.lazyAucHospitalizacion = lazyAucHospitalizacion;
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

    public List<Maestro> getListaTipoDocumentoEmpresa() {
        return listaTipoDocumentoEmpresa;
    }

    public void setListaTipoDocumentoEmpresa(List<Maestro> listaTipoDocumentoEmpresa) {
        this.listaTipoDocumentoEmpresa = listaTipoDocumentoEmpresa;
    }

    public List<Maestro> getListaGeneroAfiliado() {
        return listaGeneroAfiliado;
    }

    public void setListaGeneroAfiliado(List<Maestro> listaGeneroAfiliado) {
        this.listaGeneroAfiliado = listaGeneroAfiliado;
    }

    public List<Maestro> getListaEstadosAfiliado() {
        return listaEstadosAfiliado;
    }

    public void setListaEstadosAfiliado(List<Maestro> listaEstadosAfiliado) {
        this.listaEstadosAfiliado = listaEstadosAfiliado;
    }

    public List<Maestro> getListaRegimenAfiliado() {
        return listaRegimenAfiliado;
    }

    public void setListaRegimenAfiliado(List<Maestro> listaRegimenAfiliado) {
        this.listaRegimenAfiliado = listaRegimenAfiliado;
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

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Maestro> getListaTiposIngreso() {
        return listaTiposIngreso;
    }

    public void setListaTiposIngreso(List<Maestro> listaTiposIngreso) {
        this.listaTiposIngreso = listaTiposIngreso;
    }

    public HashMap<Integer, Maestro> getHashTiposIngreso() {
        return hashTiposIngreso;
    }

    public void setHashTiposIngreso(HashMap<Integer, Maestro> hashTiposIngreso) {
        this.hashTiposIngreso = hashTiposIngreso;
    }

    public List<Maestro> getListaCtnModalidades() {
        return listaCtnModalidades;
    }

    public void setListaCtnModalidades(List<Maestro> listaCtnModalidades) {
        this.listaCtnModalidades = listaCtnModalidades;
    }

    public HashMap<Integer, Maestro> getHashCtnModalidades() {
        return hashCtnModalidades;
    }

    public void setHashCtnModalidades(HashMap<Integer, Maestro> hashCtnModalidades) {
        this.hashCtnModalidades = hashCtnModalidades;
    }

    public List<Maestro> getListaRemisionesNoPertinentes() {
        return listaRemisionesNoPertinentes;
    }

    public void setListaRemisionesNoPertinentes(List<Maestro> listaRemisionesNoPertinentes) {
        this.listaRemisionesNoPertinentes = listaRemisionesNoPertinentes;
    }

    public HashMap<Integer, Maestro> getHashRemisionesNoPertinentes() {
        return hashRemisionesNoPertinentes;
    }

    public void setHashRemisionesNoPertinentes(HashMap<Integer, Maestro> hashRemisionesNoPertinentes) {
        this.hashRemisionesNoPertinentes = hashRemisionesNoPertinentes;
    }

    public List<Maestro> getListaTiposDiagnostico() {
        return listaTiposDiagnostico;
    }

    public void setListaTiposDiagnostico(List<Maestro> listaTiposDiagnostico) {
        this.listaTiposDiagnostico = listaTiposDiagnostico;
    }

    public HashMap<Integer, Maestro> getHashTiposDiagnostico() {
        return hashTiposDiagnostico;
    }

    public void setHashTiposDiagnostico(HashMap<Integer, Maestro> hashTiposDiagnostico) {
        this.hashTiposDiagnostico = hashTiposDiagnostico;
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

    public List<Maestro> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<Maestro> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public HashMap<Integer, Maestro> getHashServicio() {
        return hashServicio;
    }

    public void setHashServicio(HashMap<Integer, Maestro> hashServicio) {
        this.hashServicio = hashServicio;
    }

    public List<Maestro> getListaCausaEstancia() {
        return listaCausaEstancia;
    }

    public void setListaCausaEstancia(List<Maestro> listaCausaEstancia) {
        this.listaCausaEstancia = listaCausaEstancia;
    }

    public HashMap<Integer, Maestro> getHashCausaEstancia() {
        return hashCausaEstancia;
    }

    public void setHashCausaEstancia(HashMap<Integer, Maestro> hashCausaEstancia) {
        this.hashCausaEstancia = hashCausaEstancia;
    }

    public List<Maestro> getListaPropuestaIntervencion() {
        return listaPropuestaIntervencion;
    }

    public void setListaPropuestaIntervencion(List<Maestro> listaPropuestaIntervencion) {
        this.listaPropuestaIntervencion = listaPropuestaIntervencion;
    }

    public HashMap<Integer, Maestro> getHashPropuestaIntervencion() {
        return hashPropuestaIntervencion;
    }

    public void setHashPropuestaIntervencion(HashMap<Integer, Maestro> hashPropuestaIntervencion) {
        this.hashPropuestaIntervencion = hashPropuestaIntervencion;
    }

    public List<Maestro> getListaPatologia() {
        return listaPatologia;
    }

    public void setListaPatologia(List<Maestro> listaPatologia) {
        this.listaPatologia = listaPatologia;
    }

    public HashMap<Integer, Maestro> getHashPatologia() {
        return hashPatologia;
    }

    public void setHashPatologia(HashMap<Integer, Maestro> hashPatologia) {
        this.hashPatologia = hashPatologia;
    }

    public List<Maestro> getListaTipoInoportunidad() {
        return listaTipoInoportunidad;
    }

    public void setListaTipoInoportunidad(List<Maestro> listaTipoInoportunidad) {
        this.listaTipoInoportunidad = listaTipoInoportunidad;
    }

    public HashMap<Integer, Maestro> getHashTipoInoportunidad() {
        return hashTipoInoportunidad;
    }

    public void setHashTipoInoportunidad(HashMap<Integer, Maestro> hashTipoInoportunidad) {
        this.hashTipoInoportunidad = hashTipoInoportunidad;
    }

    public List<Maestro> getListaCategoriaEvento() {
        return listaCategoriaEvento;
    }

    public void setListaCategoriaEvento(List<Maestro> listaCategoriaEvento) {
        this.listaCategoriaEvento = listaCategoriaEvento;
    }

    public HashMap<Integer, Maestro> getHashCategoriaEvento() {
        return hashCategoriaEvento;
    }

    public void setHashCategoriaEvento(HashMap<Integer, Maestro> hashCategoriaEvento) {
        this.hashCategoriaEvento = hashCategoriaEvento;
    }

    public List<Maestro> getListaSubcategoriaEvento() {
        return listaSubcategoriaEvento;
    }

    public void setListaSubcategoriaEvento(List<Maestro> listaSubcategoriaEvento) {
        this.listaSubcategoriaEvento = listaSubcategoriaEvento;
    }

    public HashMap<Integer, Maestro> getHashSubcategoriaEvento() {
        return hashSubcategoriaEvento;
    }

    public void setHashSubcategoriaEvento(HashMap<Integer, Maestro> hashSubcategoriaEvento) {
        this.hashSubcategoriaEvento = hashSubcategoriaEvento;
    }

    public List<Maestro> getListaConclusionEvento() {
        return listaConclusionEvento;
    }

    public void setListaConclusionEvento(List<Maestro> listaConclusionEvento) {
        this.listaConclusionEvento = listaConclusionEvento;
    }

    public HashMap<Integer, Maestro> getHashConclusionEveno() {
        return hashConclusionEveno;
    }

    public void setHashConclusionEveno(HashMap<Integer, Maestro> hashConclusionEveno) {
        this.hashConclusionEveno = hashConclusionEveno;
    }

    public List<Maestro> getListaDestinoEgreso() {
        return listaDestinoEgreso;
    }

    public void setListaDestinoEgreso(List<Maestro> listaDestinoEgreso) {
        this.listaDestinoEgreso = listaDestinoEgreso;
    }

    public HashMap<Integer, Maestro> getHashDestinoEgreso() {
        return hashDestinoEgreso;
    }

    public void setHashDestinoEgreso(HashMap<Integer, Maestro> hashDestinoEgreso) {
        this.hashDestinoEgreso = hashDestinoEgreso;
    }

    public List<Maestro> getListaDestinoGR() {
        return listaDestinoGR;
    }

    public void setListaDestinoGR(List<Maestro> listaDestinoGR) {
        this.listaDestinoGR = listaDestinoGR;
    }

    public HashMap<Integer, Maestro> getHashDestinoGR() {
        return hashDestinoGR;
    }

    public void setHashDestinoGR(HashMap<Integer, Maestro> hashDestinoGR) {
        this.hashDestinoGR = hashDestinoGR;
    }

    public List<Maestro> getListaConductaEgreso() {
        return listaConductaEgreso;
    }

    public void setListaConductaEgreso(List<Maestro> listaConductaEgreso) {
        this.listaConductaEgreso = listaConductaEgreso;
    }

    public HashMap<Integer, Maestro> getHashConductaEgreso() {
        return hashConductaEgreso;
    }

    public void setHashConductaEgreso(HashMap<Integer, Maestro> hashConductaEgreso) {
        this.hashConductaEgreso = hashConductaEgreso;
    }

    public List<Maestro> getListCausasActivo() {
        return listCausasActivo;
    }

    public void setListCausasActivo(List<Maestro> listCausasActivo) {
        this.listCausasActivo = listCausasActivo;
    }

    public HashMap<Integer, Maestro> getHashCausasActivo() {
        return hashCausasActivo;
    }

    public void setHashCausasActivo(HashMap<Integer, Maestro> hashCausasActivo) {
        this.hashCausasActivo = hashCausasActivo;
    }

    public List<Maestro> getListCausasInactivo() {
        return listCausasInactivo;
    }

    public void setListCausasInactivo(List<Maestro> listCausasInactivo) {
        this.listCausasInactivo = listCausasInactivo;
    }

    public HashMap<Integer, Maestro> getHashCausasInActivo() {
        return hashCausasInActivo;
    }

    public void setHashCausasInActivo(HashMap<Integer, Maestro> hashCausasInActivo) {
        this.hashCausasInActivo = hashCausasInActivo;
    }

    public List<Maestro> getListaMotivoInoportunidad() {
        return listaMotivoInoportunidad;
    }

    public void setListaMotivoInoportunidad(List<Maestro> listaMotivoInoportunidad) {
        this.listaMotivoInoportunidad = listaMotivoInoportunidad;
    }

    public HashMap<Integer, Maestro> getHashMotivoInoportunidad() {
        return hashMotivoInoportunidad;
    }

    public void setHashMotivoInoportunidad(HashMap<Integer, Maestro> hashMotivoInoportunidad) {
        this.hashMotivoInoportunidad = hashMotivoInoportunidad;
    }

    public HashMap<Integer, PePrograma> getHashPePrograma() {
        return hashPePrograma;
    }

    public void setHashPePrograma(HashMap<Integer, PePrograma> hashPePrograma) {
        this.hashPePrograma = hashPePrograma;
    }

    public List<Maestro> getListaTiposSugeridoAdjuntos() {
        return listaTiposSugeridoAdjuntos;
    }

    public void setListaTiposSugeridoAdjuntos(List<Maestro> listaTiposSugeridoAdjuntos) {
        this.listaTiposSugeridoAdjuntos = listaTiposSugeridoAdjuntos;
    }

    public HashMap<Integer, Maestro> getHashTiposSugeridoAdjuntos() {
        return hashTiposSugeridoAdjuntos;
    }

    public void setHashTiposSugeridoAdjuntos(HashMap<Integer, Maestro> hashTiposSugeridoAdjuntos) {
        this.hashTiposSugeridoAdjuntos = hashTiposSugeridoAdjuntos;
    }

    public List<PePrograma> getListaProgramasEspecialesRescate() {
        return listaProgramasEspecialesRescate;
    }

    public void setListaProgramasEspecialesRescate(List<PePrograma> listaProgramasEspecialesRescate) {
        this.listaProgramasEspecialesRescate = listaProgramasEspecialesRescate;
    }

    public HashMap<Integer, PePrograma> getHashProgramasEspecialesRescate() {
        return hashProgramasEspecialesRescate;
    }

    public void setHashProgramasEspecialesRescate(HashMap<Integer, PePrograma> hashProgramasEspecialesRescate) {
        this.hashProgramasEspecialesRescate = hashProgramasEspecialesRescate;
    }

    public List<Maestro> getListaCausaIngresoPrevenibles() {
        return listaCausaIngresoPrevenibles;
    }

    public void setListaCausaIngresoPrevenibles(List<Maestro> listaCausaIngresoPrevenibles) {
        this.listaCausaIngresoPrevenibles = listaCausaIngresoPrevenibles;
    }

    public HashMap<Integer, Maestro> getHashCausaIngresoPrevenible() {
        return hashCausaIngresoPrevenible;
    }

    public void setHashCausaIngresoPrevenible(HashMap<Integer, Maestro> hashCausaIngresoPrevenible) {
        this.hashCausaIngresoPrevenible = hashCausaIngresoPrevenible;
    }

    public List<Maestro> getListaAreaResponsableIngresoPrevenible() {
        return listaAreaResponsableIngresoPrevenible;
    }

    public void setListaAreaResponsableIngresoPrevenible(List<Maestro> listaAreaResponsableIngresoPrevenible) {
        this.listaAreaResponsableIngresoPrevenible = listaAreaResponsableIngresoPrevenible;
    }

    public HashMap<Integer, Maestro> getHashAreaResponsableIngresoPrevenible() {
        return hashAreaResponsableIngresoPrevenible;
    }

    public void setHashAreaResponsableIngresoPrevenible(HashMap<Integer, Maestro> hashAreaResponsableIngresoPrevenible) {
        this.hashAreaResponsableIngresoPrevenible = hashAreaResponsableIngresoPrevenible;
    }

    public List<Maestro> getListaSeguimientoGestores() {
        return listaSeguimientoGestores;
    }

    public void setListaSeguimientoGestores(List<Maestro> listaSeguimientoGestores) {
        this.listaSeguimientoGestores = listaSeguimientoGestores;
    }

    public HashMap<Integer, Maestro> getHashSeguimientoGestores() {
        return hashSeguimientoGestores;
    }

    public void setHashSeguimientoGestores(HashMap<Integer, Maestro> hashSeguimientoGestores) {
        this.hashSeguimientoGestores = hashSeguimientoGestores;
    }

    public List<Maestro> getListaSeguimientoEstado() {
        return listaSeguimientoEstado;
    }

    public void setListaSeguimientoEstado(List<Maestro> listaSeguimientoEstado) {
        this.listaSeguimientoEstado = listaSeguimientoEstado;
    }

    public List<Maestro> getListaMotivoReingreso() {
        return listaMotivoReingreso;
    }

    public void setListaMotivoReingreso(List<Maestro> listaMotivoReingreso) {
        this.listaMotivoReingreso = listaMotivoReingreso;
    }

    public HashMap<Integer, Maestro> getHashMotivoReingreso() {
        return hashMotivoReingreso;
    }

    public void setHashMotivoReingreso(HashMap<Integer, Maestro> hashMotivoReingreso) {
        this.hashMotivoReingreso = hashMotivoReingreso;
    }

    public List<Maestro> getListaProgramaAltaTemprana() {
        return listaProgramaAltaTemprana;
    }

    public void setListaProgramaAltaTemprana(List<Maestro> listaProgramaAltaTemprana) {
        this.listaProgramaAltaTemprana = listaProgramaAltaTemprana;
    }

    public HashMap<Integer, Maestro> getHashProgramaAltaTemprana() {
        return hashProgramaAltaTemprana;
    }

    public void setHashProgramaAltaTemprana(HashMap<Integer, Maestro> hashProgramaAltaTemprana) {
        this.hashProgramaAltaTemprana = hashProgramaAltaTemprana;
    }

    public HashMap<Integer, Maestro> getHashSeguimientoEstado() {
        return hashSeguimientoEstado;
    }

    public void setHashSeguimientoEstado(HashMap<Integer, Maestro> hashSeguimientoEstado) {
        this.hashSeguimientoEstado = hashSeguimientoEstado;
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

    public void setLazyAfiliados(LazyDataModel<AsegAfiliado> lazyAfiliados) {
        this.lazyAfiliados = lazyAfiliados;
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

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public AucHospitalizacionSeguimiento getObjetoSeguimiento() {
        if (objetoSeguimiento == null) {
            objetoSeguimiento = new AucHospitalizacionSeguimiento();
        }
        return objetoSeguimiento;
    }

    public void setObjetoSeguimiento(AucHospitalizacionSeguimiento objetoSeguimiento) {
        this.objetoSeguimiento = objetoSeguimiento;
    }

    public AucHospitalizacionEstancia getObjetoEstancia() {
        if (objetoEstancia == null) {
            objetoEstancia = new AucHospitalizacionEstancia();
        }
        return objetoEstancia;
    }

    public void setObjetoEstancia(AucHospitalizacionEstancia objetoEstancia) {
        this.objetoEstancia = objetoEstancia;
    }

    public AucHospitalizacionPatologia getObjetoPatologia() {
        if (objetoPatologia == null) {
            objetoPatologia = new AucHospitalizacionPatologia();
        }
        return objetoPatologia;
    }

    public void setObjetoPatologia(AucHospitalizacionPatologia objetoPatologia) {
        this.objetoPatologia = objetoPatologia;
    }

    public AucHospitalizacionInoportunidad getObjetoInoportunidad() {
        if (objetoInoportunidad == null) {
            objetoInoportunidad = new AucHospitalizacionInoportunidad();
        }
        return objetoInoportunidad;
    }

    public void setObjetoInoportunidad(AucHospitalizacionInoportunidad objetoInoportunidad) {
        this.objetoInoportunidad = objetoInoportunidad;
    }

    public AucHospitalizacionAdverso getObjetoAdverso() {
        if (objetoAdverso == null) {
            objetoAdverso = new AucHospitalizacionAdverso();
        }
        return objetoAdverso;
    }

    public void setObjetoAdverso(AucHospitalizacionAdverso objetoAdverso) {
        this.objetoAdverso = objetoAdverso;
    }

    public AucHospitalizacionObjecion getObjetoObjecion() {
        if (objetoObjecion == null) {
            objetoObjecion = new AucHospitalizacionObjecion();
        }
        return objetoObjecion;
    }

    public void setObjetoObjecion(AucHospitalizacionObjecion objetoObjecion) {
        this.objetoObjecion = objetoObjecion;
    }

    public AucHospitalizacionServicio getObjetoServicio() {
        if (objetoServicio == null) {
            objetoServicio = new AucHospitalizacionServicio();
        }
        return objetoServicio;
    }

    public void setObjetoServicio(AucHospitalizacionServicio objetoServicio) {
        this.objetoServicio = objetoServicio;
    }

    public AucHospitalizacion getObjetoHospitalizacionAplicaRescate() {
        if (objetoHospitalizacionAplicaRescate == null) {
            objetoHospitalizacionAplicaRescate = new AucHospitalizacion();
        }
        return objetoHospitalizacionAplicaRescate;
    }

    public void setObjetoHospitalizacionAplicaRescate(AucHospitalizacion objetoHospitalizacionAplicaRescate) {
        this.objetoHospitalizacionAplicaRescate = objetoHospitalizacionAplicaRescate;
    }

    public AucDiagnostico getObjetoDiagnostico() {
        if (objetoDiagnostico == null) {
            objetoDiagnostico = new AucDiagnostico();
        }
        return objetoDiagnostico;
    }

    public void setObjetoDiagnostico(AucDiagnostico objetoDiagnostico) {
        this.objetoDiagnostico = objetoDiagnostico;
    }

    public AucJustificacionEstanciasProlongada getObjetoJustificacionEstanciasProlongada() {
        if (objetoJustificacionEstanciasProlongada == null) {
            objetoJustificacionEstanciasProlongada = new AucJustificacionEstanciasProlongada();
        }
        return objetoJustificacionEstanciasProlongada;
    }

    public void setObjetoJustificacionEstanciasProlongada(AucJustificacionEstanciasProlongada objetoJustificacionEstanciasProlongada) {
        this.objetoJustificacionEstanciasProlongada = objetoJustificacionEstanciasProlongada;
    }

    public CntPrestadorSede getObjPrestadorSede() {
        if (objPrestadorSede == null) {
            objPrestadorSede = new CntPrestadorSede();
        }
        return objPrestadorSede;
    }

    public void setObjPrestadorSede(CntPrestadorSede objPrestadorSede) {
        this.objPrestadorSede = objPrestadorSede;
    }

    public PeAfiliadosPrograma getObjetoPePregrama() {
        if (objetoPePregrama == null) {
            objetoPePregrama = new PeAfiliadosPrograma();
        }
        return objetoPePregrama;
    }

    public void setObjetoPePregrama(PeAfiliadosPrograma objetoPePregrama) {
        this.objetoPePregrama = objetoPePregrama;
    }

    public PeAfiliadoSugerido getObjetoSugerido() {
        if (objetoSugerido == null) {
            objetoSugerido = new PeAfiliadoSugerido();
        }
        return objetoSugerido;
    }

    public void setObjetoSugerido(PeAfiliadoSugerido objetoSugerido) {
        this.objetoSugerido = objetoSugerido;
    }

    public AuAnexo2Rescate getObjetoRescate() {
        if (objetoRescate == null) {
            objetoRescate = new AuAnexo2Rescate();
        }
        return objetoRescate;
    }

    public void setObjetoRescate(AuAnexo2Rescate objetoRescate) {
        this.objetoRescate = objetoRescate;
    }

    public List<PeAdjunto> getPeAdjuntos() {
        if (peAdjuntos == null) {
            peAdjuntos = new ArrayList<>();
        }
        return peAdjuntos;
    }

    public void setPeAdjuntos(List<PeAdjunto> peAdjuntos) {
        this.peAdjuntos = peAdjuntos;
    }

    public PeAdjunto getObjectoAdjunto() {
        if (objectoAdjunto == null) {
            objectoAdjunto = new PeAdjunto();
        }
        return objectoAdjunto;
    }

    public void setObjectoAdjunto(PeAdjunto objectoAdjunto) {
        this.objectoAdjunto = objectoAdjunto;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
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

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
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

    public List<AucAfiliadoContacto> getListaTuPersonaContacto() {
        if (listaTuPersonaContacto == null) {
            listaTuPersonaContacto = new ArrayList<>();
        }
        return listaTuPersonaContacto;
    }

    public List<AsegAfiliadoContacto> getListaContactosAseg() {
        if (listaContactosAseg == null) {
            listaContactosAseg = new ArrayList<>();
        }
        return listaContactosAseg;
    }

    public void setListaContactosAseg(List<AsegAfiliadoContacto> listaContactosAseg) {
        this.listaContactosAseg = listaContactosAseg;
    }

    public void setListaTuPersonaContacto(List<AucAfiliadoContacto> listaTuPersonaContacto) {
        this.listaTuPersonaContacto = listaTuPersonaContacto;
    }

    public List<AsegAfiliadoContacto> getListaContactos() {
        if (listaContactos == null) {
            listaContactos = new ArrayList<>();
        }
        return listaContactos;
    }

    public void setListaContactos(List<AsegAfiliadoContacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public List<PeAfiliadoSugerido> getListaSegueridoMemoria() {
        if (listaSegueridoMemoria == null) {
            listaSegueridoMemoria = new ArrayList<>();
        }
        return listaSegueridoMemoria;
    }

    public void setListaSegueridoMemoria(List<PeAfiliadoSugerido> listaSegueridoMemoria) {
        this.listaSegueridoMemoria = listaSegueridoMemoria;
    }

    public List<PeTelefono> getListaContactosPrograma() {
        if (listaContactosPrograma == null) {
            listaContactosPrograma = new ArrayList<>();
        }
        return listaContactosPrograma;
    }

    public void setListaContactosPrograma(List<PeTelefono> listaContactosPrograma) {
        this.listaContactosPrograma = listaContactosPrograma;
    }

    public List<PeTelefono> getListaContactosBorrar() {
        if (listaContactosBorrar == null) {
            listaContactosBorrar = new ArrayList<>();
        }
        return listaContactosBorrar;
    }

    public void setListaContactosBorrar(List<PeTelefono> listaContactosBorrar) {
        this.listaContactosBorrar = listaContactosBorrar;
    }

    public List<AucDiagnostico> getListaDiagnosticosBorrar() {
        if (listaDiagnosticosBorrar == null) {
            listaDiagnosticosBorrar = new ArrayList<>();
        }
        return listaDiagnosticosBorrar;
    }

    public void setListaDiagnosticosBorrar(List<AucDiagnostico> listaDiagnosticosBorrar) {
        this.listaDiagnosticosBorrar = listaDiagnosticosBorrar;
    }

    public List<AucDiagnostico> getListaDiagnosticosEgresoBorrar() {
        if (listaDiagnosticosEgresoBorrar == null) {
            listaDiagnosticosEgresoBorrar = new ArrayList<>();
        }
        return listaDiagnosticosEgresoBorrar;
    }

    public void setListaDiagnosticosEgresoBorrar(List<AucDiagnostico> listaDiagnosticosEgresoBorrar) {
        this.listaDiagnosticosEgresoBorrar = listaDiagnosticosEgresoBorrar;
    }

    public List<AucDiagnostico> getListaDiagnosticosEstanciaEspecialidadBorrar() {
        return listaDiagnosticosEstanciaEspecialidadBorrar;
    }

    public void setListaDiagnosticosEstanciaEspecialidadBorrar(List<AucDiagnostico> listaDiagnosticosEstanciaEspecialidadBorrar) {
        this.listaDiagnosticosEstanciaEspecialidadBorrar = listaDiagnosticosEstanciaEspecialidadBorrar;
    }

    public List<AuAnexo2Rescate> getListaHistorialRescate() {
        if (listaHistorialRescate == null) {
            listaHistorialRescate = new ArrayList<>();
        }
        return listaHistorialRescate;
    }

    public void setListaHistorialRescate(List<AuAnexo2Rescate> listaHistorialRescate) {
        this.listaHistorialRescate = listaHistorialRescate;
    }

    public List<AucHospitalizacionServicio> getListaMemoriaTecnologia() {
        if (listaMemoriaTecnologia == null) {
            listaMemoriaTecnologia = new ArrayList<>();
        }
        return listaMemoriaTecnologia;
    }

    public void setListaMemoriaTecnologia(List<AucHospitalizacionServicio> listaMemoriaTecnologia) {
        this.listaMemoriaTecnologia = listaMemoriaTecnologia;
    }

    public List<AucHospitalizacionHistorico> getListaHistorialEgreso() {
        if (listaHistorialEgreso == null) {
            listaHistorialEgreso = new ArrayList<>();
        }
        return listaHistorialEgreso;
    }

    public void setListaHistorialEgreso(List<AucHospitalizacionHistorico> listaHistorialEgreso) {
        this.listaHistorialEgreso = listaHistorialEgreso;
    }

    public AucAfiliadoContacto getAucAfiliadoContacto() {
        if (aucAfiliadoContacto == null) {
            aucAfiliadoContacto = new AucAfiliadoContacto();
        }
        return aucAfiliadoContacto;
    }

    public void setAucAfiliadoContacto(AucAfiliadoContacto aucAfiliadoContacto) {
        this.aucAfiliadoContacto = aucAfiliadoContacto;
    }

    public boolean isIsObjecion() {
        return isObjecion;
    }

    public void setIsObjecion(boolean isObjecion) {
        this.isObjecion = isObjecion;
    }

    public int getIsIngresoOgreso() {
        return isIngresoOgreso;
    }

    public void setIsIngresoOgreso(int isIngresoOgreso) {
        this.isIngresoOgreso = isIngresoOgreso;
    }

    public boolean isIsEgresoDiagnosticoEspecialidad() {
        return isEgresoDiagnosticoEspecialidad;
    }

    public void setIsEgresoDiagnosticoEspecialidad(boolean isEgresoDiagnosticoEspecialidad) {
        this.isEgresoDiagnosticoEspecialidad = isEgresoDiagnosticoEspecialidad;
    }

    public boolean isHabilitarEgresoNumeroCertificado() {
        return habilitarEgresoNumeroCertificado;
    }

    public void setHabilitarEgresoNumeroCertificado(boolean habilitarEgresoNumeroCertificado) {
        this.habilitarEgresoNumeroCertificado = habilitarEgresoNumeroCertificado;
    }

    public boolean isCampoObligatorioNumeroCertificado() {
        return campoObligatorioNumeroCertificado;
    }

    public void setCampoObligatorioNumeroCertificado(boolean campoObligatorioNumeroCertificado) {
        this.campoObligatorioNumeroCertificado = campoObligatorioNumeroCertificado;
    }

    public boolean isCrearOgestionarDiagnostico() {
        return crearOgestionarDiagnostico;
    }

    public void setCrearOgestionarDiagnostico(boolean crearOgestionarDiagnostico) {
        this.crearOgestionarDiagnostico = crearOgestionarDiagnostico;
    }

    public boolean isBloquearBotonesCierreAuditoria() {
        return bloquearBotonesCierreAuditoria;
    }

    public void setBloquearBotonesCierreAuditoria(boolean bloquearBotonesCierreAuditoria) {
        this.bloquearBotonesCierreAuditoria = bloquearBotonesCierreAuditoria;
    }

    public boolean isHabilitarCampoValorEstancia() {
        return habilitarCampoValorEstancia;
    }

    public void setHabilitarCampoValorEstancia(boolean habilitarCampoValorEstancia) {
        this.habilitarCampoValorEstancia = habilitarCampoValorEstancia;
    }

    public boolean isCampoObligatorioValorEstancia() {
        return CampoObligatorioValorEstancia;
    }

    public void setCampoObligatorioValorEstancia(boolean CampoObligatorioValorEstancia) {
        this.CampoObligatorioValorEstancia = CampoObligatorioValorEstancia;
    }

    public boolean isHabilitarFechaEgreso() {
        return habilitarFechaEgreso;
    }

    public void setHabilitarFechaEgreso(boolean habilitarFechaEgreso) {
        this.habilitarFechaEgreso = habilitarFechaEgreso;
    }

    public boolean isHabilitarCampoEmpresaAdministra() {
        return habilitarCampoEmpresaAdministra;
    }

    public void setHabilitarCampoEmpresaAdministra(boolean habilitarCampoEmpresaAdministra) {
        this.habilitarCampoEmpresaAdministra = habilitarCampoEmpresaAdministra;
    }

    public boolean isHabilitarCampoImprimirActa() {
        return habilitarCampoImprimirActa;
    }

    public void setHabilitarCampoImprimirActa(boolean habilitarCampoImprimirActa) {
        this.habilitarCampoImprimirActa = habilitarCampoImprimirActa;
    }

    public boolean isHabilitarCampoMotivoInoportidad() {
        return habilitarCampoMotivoInoportidad;
    }

    public void setHabilitarCampoMotivoInoportidad(boolean habilitarCampoMotivoInoportidad) {
        this.habilitarCampoMotivoInoportidad = habilitarCampoMotivoInoportidad;
    }

    public boolean isCampoObligatorioMotivoInoportidad() {
        return campoObligatorioMotivoInoportidad;
    }

    public void setCampoObligatorioMotivoInoportidad(boolean campoObligatorioMotivoInoportidad) {
        this.campoObligatorioMotivoInoportidad = campoObligatorioMotivoInoportidad;
    }

    public boolean isHabilitarAreaResponsableIngresoPrevenible() {
        return habilitarAreaResponsableIngresoPrevenible;
    }

    public void setHabilitarAreaResponsableIngresoPrevenible(boolean habilitarAreaResponsableIngresoPrevenible) {
        this.habilitarAreaResponsableIngresoPrevenible = habilitarAreaResponsableIngresoPrevenible;
    }

    public boolean isCampoObligatorioAreaResponsableIngresoPrevenible() {
        return campoObligatorioAreaResponsableIngresoPrevenible;
    }

    public void setCampoObligatorioAreaResponsableIngresoPrevenible(boolean campoObligatorioAreaResponsableIngresoPrevenible) {
        this.campoObligatorioAreaResponsableIngresoPrevenible = campoObligatorioAreaResponsableIngresoPrevenible;
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

    public SelEspecialidadesBean getEspecialidadBean() {
        especiliadaBean = (SelEspecialidadesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selEspecialidadesBean");
        return especiliadaBean;
    }

    public void setSelEspecialidadesBean(SelEspecialidadesBean especiliadaBean) {
        this.especiliadaBean = especiliadaBean;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAucHospitalizacionServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void borrarEstancia(int _id, int pos) {
        /*if (getObjeto().getAucHospitalizacionEstanciaList().size() == 1) {
            addError("No se puede eliminar porque debe tener por lo menos una estancia");
        }*/
 /*if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            int idUltimoEstancia = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getId();
            if (idUltimoEstancia != _id) {
                addError("No puede eliminar estancia no es la ultima");
            }
        }*/
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            getObjetoEstancia().setId(_id);
            super.setAccion(Url.ACCION_ADICIONAL_5);
            super.setDoAccion(Url.ACCION_ADICIONAL_5);
            getAucHospitalizacionServicio().Accion(this);
        }

        procesoFinal();
    }

    public void borrarInoportunidad() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void ventanaBorrarInoportunidad(int _id) {
        setObjetoInoportunidad(new AucHospitalizacionInoportunidad());
        getObjetoInoportunidad().setId(_id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmBorrarInoportunidad");
            PrimeFaces.current().ajax().update("frmBorrarInoportunidad");
            PrimeFaces.current().executeScript("PF('dialogoBorrarInoportunidad').show()");
        }
    }

    public void ventanaBorrarAdverso(int _id) {
        setObjetoAdverso(new AucHospitalizacionAdverso());
        getObjetoAdverso().setId(_id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmBorrarAdverso");
            PrimeFaces.current().ajax().update("frmBorrarAdverso");
            PrimeFaces.current().executeScript("PF('dialogoBorrarAdverso').show()");
        }
    }

    public void ventanaBorrarObjecion(int _id) {
        setObjetoObjecion(new AucHospitalizacionObjecion());
        getObjetoObjecion().setId(_id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmBorrarObjecion");
            PrimeFaces.current().ajax().update("frmBorrarObjecion");
            PrimeFaces.current().executeScript("PF('dialogoBorrarObjecion').show()");
        }
    }
    
    public void ventanaBorrarGestionGestoras(int _id) {
        setObjetoSeguimiento(new AucHospitalizacionSeguimiento());
        getObjetoSeguimiento().setId(_id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmBorrarGestionGestoras");
            PrimeFaces.current().ajax().update("frmBorrarGestionGestoras");
            PrimeFaces.current().executeScript("PF('dialogoBorrarGestionGestoras').show()");
        }
    }
    
    public void ventanaBorrarServicio(int _id) {
        setObjetoServicio(new AucHospitalizacionServicio());
        getObjetoServicio().setId(_id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmBorrarServicio");
            PrimeFaces.current().ajax().update("frmBorrarServicio");
            PrimeFaces.current().executeScript("PF('dialogoBorrarServicio').show()");
        }
    }

    public void verSeguegrirProgramaMemoria(PeAfiliadoSugerido suegerido) {
        setObjetoSugerido(new PeAfiliadoSugerido());
        setObjetoSugerido(suegerido);
        PrimeFaces.current().resetInputs("frmVerSugerirPrograma");
        PrimeFaces.current().ajax().update("frmVerSugerirPrograma");
        PrimeFaces.current().executeScript("PF('dialogoVerSugerirPrograma').show()");
    }

    public void borrarAdverso() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void borrarObjecion() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void borrarGestionGestoras() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_ADICIONAL_6);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void borrarServicio() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void borrarSugerido(int _id) {
        setObjetoSugerido(new PeAfiliadoSugerido());
        getObjetoSugerido().setId(_id);
        getAucHospitalizacionServicio().validarSugeridoParaBorrar(this);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_ADICIONAL_4);
        procesoFinal();
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

    public void borrarAdjuntoRescateMemoria(AuSolicitudAdjunto sugerido) {
        int posicionEliminar = this.getObjetoRescate().getAuSolicitudAdjuntosList().indexOf(sugerido);
        this.getObjetoRescate().getAuSolicitudAdjuntosList().remove(posicionEliminar);
        addMensaje("Se ha realizado la eliminación del archivo");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmAgregarRescate:tablaRescateAnexos");
        PrimeFaces.current().ajax().update("frmAgregarRescate:tablaRescateAnexos");
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verSeguimiento(int id) {
        getObjetoSeguimiento().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void verGestorasRegionales(int id) {
        getObjetoSeguimiento().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_10);
        super.setDoAccion(Url.ACCION_VER);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void verEstancia(int id) {
        getObjetoEstancia().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verEstanciaGestion(int id) {
        getObjetoEstancia().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verEstanciaEditar(int id) {
        getObjetoEstancia().setId(id);
        super.setAccion(Url.ACCION_EDITAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_9);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verJustificacionEstanciaProlongada(int id) {
        getObjetoJustificacionEstanciasProlongada().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_8);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verPatologia(int id) {
        getObjetoPatologia().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verInoportunidad(int id) {
        getObjetoInoportunidad().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verAdverso(int id) {
        getObjetoAdverso().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_4);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verObjecion(int id) {
        getObjetoObjecion().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_5);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verServicio(int id) {
        getObjetoServicio().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_6);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verPrograma(int id, int programaOsugerido) {
        if (programaOsugerido == 1) {
            getObjetoPePregrama().setId(id);
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_8);
        } else if (programaOsugerido == 0) {
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_9);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verRescate(int id) {
        setObjetoRescate(new AuAnexo2Rescate());
        getObjetoRescate().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_11);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verProgramaEditar(int id, int programaOsugerido) {
        if (programaOsugerido == 1) {
            getObjetoPePregrama().setId(id);
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_10);
        } else if (programaOsugerido == 0) {
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_11);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verProgramaVer(int id, int programaOsugerido) {
        if (programaOsugerido == 1) {
            getObjetoPePregrama().setId(id);
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(Url.ACCION_ADICIONAL_9);
        } else if (programaOsugerido == 0) {
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(Url.ACCION_ADICIONAL_10);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verHistorialValorEstancia() {
        setListaHistorialEgreso(new ArrayList<>());
        getAucHospitalizacionServicio().consultarHistorialHospitalizacionesEgreso(this);
        PrimeFaces.current().resetInputs("frmEgresoHistorial");
        PrimeFaces.current().ajax().update("frmEgresoHistorial");
        PrimeFaces.current().executeScript("PF('dialogoEgresoHistorial').show()");

    }

    public void crear() {
        setHabilitarEgresoNumeroCertificado(true);
       // setCrearOgestionarDiagnostico(true);
        setListaTuPersonaContacto(new ArrayList<>());
        setListaContactosAseg(new ArrayList<>());
        setCrearOgestionarDiagnostico(true);
        super.setAccion(Url.ACCION_CREAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void crearContacto() {
        setAucAfiliadoContacto(new AucAfiliadoContacto());
        PrimeFaces.current().resetInputs("frmCrearContacto:panelCrearContacto");
        PrimeFaces.current().ajax().update("frmCrearContacto:panelCrearContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearContacto').show()");
    }

    public void crearContactoGestion() {
        setAucAfiliadoContacto(new AucAfiliadoContacto());
        PrimeFaces.current().resetInputs("frmCrearContactoGestion:panelCrearContactoGestion");
        PrimeFaces.current().ajax().update("frmCrearContactoGestion:panelCrearContactoGestion");
        PrimeFaces.current().executeScript("PF('dialogoCrearContactoGestion').show()");
    }

    public void crearGestionRiesgo() {
        setListaSegueridoMemoria(new ArrayList<>());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmSugerirProgramaCrear");
            PrimeFaces.current().ajax().update("frmSugerirProgramaCrear");
            PrimeFaces.current().executeScript("PF('dialogoSugerirProgramaCrear').show()");
        }
        generarMensajes();
    }

    public void crearRescateHistorial() {
        setListaHistorialRescate(new ArrayList<>());
        getAucHospitalizacionServicio().consultarHistorialRescate(this);
        PrimeFaces.current().resetInputs("frmRescateHistorialCrear");
        PrimeFaces.current().ajax().update("frmRescateHistorialCrear");
        PrimeFaces.current().executeScript("PF('dialogoRescateHistorialCrear').show()");

    }
    
    public void crearNoAptoRescate(){
        setObjetoSeguimiento(new AucHospitalizacionSeguimiento());
        getAucHospitalizacionServicio().consultarMaestrosSeguimiento(this);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmNoAptoRescate");
            PrimeFaces.current().ajax().update("frmNoAptoRescate");
            PrimeFaces.current().executeScript("PF('dialogoNoAptoRescate').show()");
        }
        generarMensajes();
    }
    
    public void crearRescate() {
        setHashPePrograma(new HashMap<>());
        setObjetoRescate(new AuAnexo2Rescate());
        getObjetoRescate().setPePrograma(new PePrograma());
        setListaProgramasEspecialesRescate(new ArrayList<>());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        // se inabilita validadacion por que requerimiento de que pueda aplicar rescate 
        //getAucHospitalizacionServicio().consultarNoAptoRescate(this);
        getAucHospitalizacionServicio().consultarListaAplicaRescateHospitalizacion(this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        
        if (!super.isError()) {
            if(hopitalizacion.getAplicaRescate() == hopitalizacion.NO_APTO_RESCATE){
                PrimeFaces.current().resetInputs("frmAgregarRescateNoActo");
                PrimeFaces.current().ajax().update("frmAgregarRescateNoActo");
                PrimeFaces.current().executeScript("PF('dialogoAgregarRescateNoActo').show()");
            }else{
                if(getListaProgramasEspecialesRescate().isEmpty()){
                    addError("No Aplica para rescate");
                }
                if(!super.isError()){
                    PrimeFaces.current().resetInputs("frmAgregarRescate");
                    PrimeFaces.current().ajax().update("frmAgregarRescate");
                    PrimeFaces.current().executeScript("PF('dialogoAgregarRescate').show()");
                }
            }
        }
        generarMensajes();

    }
    
    public void crearRescateNoActo() {
        setHashPePrograma(new HashMap<>());
        setObjetoRescate(new AuAnexo2Rescate());
        getObjetoRescate().setPePrograma(new PePrograma());
        setListaProgramasEspecialesRescate(new ArrayList<>());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        // se inabilita validadacion por que requerimiento de que pueda aplicar rescate 
        //getAucHospitalizacionServicio().consultarNoAptoRescate(this);
        getAucHospitalizacionServicio().consultarListaAplicaRescateHospitalizacion(this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if(getListaProgramasEspecialesRescate().isEmpty()){
            addError("No Aplica para rescate");
        }
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmAgregarRescate");
            PrimeFaces.current().ajax().update("frmAgregarRescate");
            PrimeFaces.current().executeScript("PF('dialogoAgregarRescate').show()");
            PrimeFaces.current().executeScript("PF('dialogoAgregarRescateNoActo').hide()");
        }
        generarMensajes();

    }
    
    public void abrirAdjuntos(int id, int programaOsugerido) {
        if (programaOsugerido == 1) {
            setObjetoPePregrama(new PeAfiliadosPrograma());
            setObjectoAdjunto(new PeAdjunto());
            getObjetoPePregrama().setId(id);
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_CREAR);
            setPeAdjuntos(new ArrayList<>());
        } else if (programaOsugerido == 0) {
            setObjetoSugerido(new PeAfiliadoSugerido());
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_11);
        }

        getAucHospitalizacionServicio().Accion(this);
        if (programaOsugerido == 1) {
            PrimeFaces.current().resetInputs("frmAdjunto");
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().ajax().update("frmAuditoriaAdjuntoProgramas");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
        } else {
            PrimeFaces.current().resetInputs("frmAdjuntoSugerido");
            PrimeFaces.current().ajax().update("frmAdjuntoSugerido");
            PrimeFaces.current().executeScript("PF('dialogoAgregarAdjunto').show()");
        }
        //

    }

    public void verAdjuntos(int id, int programaOsugerido) {
        if (programaOsugerido == 1) {
            setObjetoPePregrama(new PeAfiliadosPrograma());
            setObjectoAdjunto(new PeAdjunto());
            getObjetoPePregrama().setId(id);
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(Url.ACCION_ADICIONAL_12);
            setPeAdjuntos(new ArrayList<>());
        } else if (programaOsugerido == 0) {
            setObjetoSugerido(new PeAfiliadoSugerido());
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(Url.ACCION_ADICIONAL_13);
        }

        getAucHospitalizacionServicio().Accion(this);
        if (programaOsugerido == 1) {
            PrimeFaces.current().resetInputs("frmVerAdjunto");
            PrimeFaces.current().ajax().update("frmVerAdjunto");
            PrimeFaces.current().ajax().update("frmVerAuditoriaAdjuntoProgramas");
            PrimeFaces.current().executeScript("PF('dialogoVerAdjuntosPrograma').show()");
        } else {
            PrimeFaces.current().resetInputs("frmVerAdjuntoSugerido");
            PrimeFaces.current().ajax().update("frmVerAdjuntoSugerido");
            PrimeFaces.current().executeScript("PF('dialogoVerAdjuntoSugerido').show()");
        }
    }

    public void editarAdjuntos(int id, int programaOsugerido) {
        if (programaOsugerido == 1) {
            setObjetoPePregrama(new PeAfiliadosPrograma());
            setObjectoAdjunto(new PeAdjunto());
            getObjetoPePregrama().setId(id);
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_12);
            setPeAdjuntos(new ArrayList<>());
        } else if (programaOsugerido == 0) {
            setObjetoSugerido(new PeAfiliadoSugerido());
            getObjetoSugerido().setId(id);
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_13);
        }

        getAucHospitalizacionServicio().Accion(this);
        if (programaOsugerido == 1) {
            PrimeFaces.current().resetInputs("frmVerAdjunto");
            PrimeFaces.current().ajax().update("frmVerAdjunto");
            PrimeFaces.current().ajax().update("frmVerAuditoriaAdjuntoProgramas");
            PrimeFaces.current().executeScript("PF('dialogoVerAdjuntosPrograma').show()");
        } else {
            PrimeFaces.current().resetInputs("frmVerAdjuntoSugerido");
            PrimeFaces.current().ajax().update("frmVerAdjuntoSugerido");
            PrimeFaces.current().executeScript("PF('dialogoVerAdjuntoSugerido').show()");
        }
    }

    public void crearSugerido() {
        setHashPePrograma(new HashMap<>());
        getObjeto().setAucSugerirProgramaList(new ArrayList<>());
        setObjetoSugerido(new PeAfiliadoSugerido());
        getObjetoSugerido().setListaAdjunto(new ArrayList<>());
        getAucHospitalizacionServicio().consultarGestionRiegosSugerido(this);
        PrimeFaces.current().resetInputs("frmAgregarSugerirPrograma");
        PrimeFaces.current().ajax().update("frmAgregarSugerirPrograma");
        PrimeFaces.current().executeScript("PF('dialogoAgregarSugerirPrograma').show()");
    }

    public void guardarGestionRiesgo() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void guardarRescate() {
        AuAnexo2Rescate obj = getObjetoRescate();
        obj.setEstado(AuAnexo2Rescate.ESTADO_PENDIENTE);
        obj.setFuenteOrigen(AuAnexo2Rescate.FUENTE_ORIGEN_HOSPITALIZACION);
        obj.setAucHospitalizacion(getObjeto());

        PePrograma tipo = getHashPePrograma().get(obj.getPePrograma().getId());
        if (tipo != null) {
            obj.getPePrograma().setCodigoPrograma(tipo.getCodigoPrograma());
            obj.getPePrograma().setDescripcionPrograma(tipo.getDescripcionPrograma());
        }
        if (!getAucHospitalizacionServicio().consultarAplicaRescateHospitalizacionPrograma(this)) {
            addError("No puede generar un rescate puede tener uno pendiente, o en gestion, o ya fue rescatado");
        }
        //obj.setPos(getObjeto().getAucRescateList().size());
        if (!super.isError()) {
            //refrescarSeguimientos();
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_10);
            getAucHospitalizacionServicio().Accion(this);
            getObjeto().getAucRescateList().add(obj);
        }

        procesoFinal();
    }

    public void guardarAdjuntoPrograma() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAucHospitalizacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmAdjunto");
        procesoFinal();
        setPeAdjuntos(new ArrayList<>());
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
            if(obj.getListaAdjunto().isEmpty()){
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

    public void agregarAdjuntoSugerido() {
        try {

            PeAfiliadoSugerido obj = getObjetoSugerido();
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_12);
            getAucHospitalizacionServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().ajax().update("frmAdjuntoSugerido");
                PrimeFaces.current().executeScript("PF('dialogoAgregarAdjunto').hide()");
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        setListaDiagnosticosBorrar(new ArrayList<>());
        setListaDiagnosticosEgresoBorrar(new ArrayList<>());
        setListaDiagnosticosEstanciaEspecialidadBorrar(new ArrayList<>());
        super.setAccion(ACCION_EDITAR);
        super.setDoAccion(ACCION_EDITAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    /**
     * Metodo que se encarga de abrir el modal de edicion del seguimiento
     *
     * @param id Id del registro
     * @param editarOgestionar Variable para saber si habre modal de edicio o
     * editar gestion
     */
    public void editarSeguimiento(int id, boolean editarOgestionar) {
        getObjetoSeguimiento().setId(id);
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_2);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void editarGestionGestoras(int id) {
        getObjetoSeguimiento().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_10);
        super.setDoAccion(Url.ACCION_EDITAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    /**
     * Metodo que se encarga de abrir el modal de edicion del estancia
     *
     * @param id Id del registro
     * @param editarOgestionar Variable para saber si habre modal de edicio o
     * editar gestion
     */
    public void editarEstancia(int id, boolean editarOgestionar) {
        getObjetoEstancia().setId(id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_2);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
        }

        getAucHospitalizacionServicio().Accion(this);
        if (getObjetoEstancia().getFechaEgreso() != null) {
            int posicion = -1;
            for (AucHospitalizacionEstancia estancia : getObjeto().getAucHospitalizacionEstanciaList()) {
                if (estancia.getId().equals(getObjetoEstancia().getId())) {
                    posicion = getObjeto().getAucHospitalizacionEstanciaList().indexOf(estancia);
                    break;
                }
            }
            if (posicion != -1) {
                if ((posicion + 1) == getObjeto().getAucHospitalizacionEstanciaList().size()) {
                    setHabilitarFechaEgreso(false);
                } else {
                    setHabilitarFechaEgreso(true);
                }
            } else {
                setHabilitarFechaEgreso(false);
            }

        } else {
            setHabilitarFechaEgreso(false);
        }
        procesoFinal();
    }

    public void editarJustificacionProlongada(int id, boolean editarOgestionar) {
        getObjetoJustificacionEstanciasProlongada().setId(id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_8);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_1);
            super.setDoAccion(Url.ACCION_ADICIONAL_2);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    /**
     * Metodo que se encarga de abrir el modal de edicion del patologia
     *
     * @param id Id del registro
     * @param editarOgestionar Variable para saber si habre modal de edicio o
     * editar gestion
     */
    public void editarPatologia(int id, boolean editarOgestionar) {
        getObjetoPatologia().setId(id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_3);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_3);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    /**
     * Metodo que se encarga de abrir el modal de edicion del inoportunidad
     *
     * @param id Id del registro
     * @param editarOgestionar Variable para saber si habre modal de edicio o
     * editar gestion
     */
    public void editarInoportunidad(int id, boolean editarOgestionar) {
        getObjetoInoportunidad().setId(id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_4);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_4);
            super.setTakeAccion(Url.ACCION_CREAR);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    /**
     * Metodo que se encarga de abrir el modal de edicion del Adverso
     *
     * @param id Id del registro
     * @param editarOgestionar Variable para saber si habre modal de edicio o
     * editar gestion
     */
    public void editarAdverso(int id, boolean editarOgestionar) {
        getObjetoAdverso().setId(id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(ACCION_ADICIONAL_5);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_3);
            super.setDoAccion(Url.ACCION_ADICIONAL_3);
        }
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    /**
     * Metodo que se encarga de abrir el modal de edicion del objecion
     *
     * @param id Id del registro
     * @param editarOgestionar Variable para saber si habre modal de edicio o
     * editar gestion
     */
    public void editarObjecion(int id, boolean editarOgestionar) {
        getObjetoObjecion().setId(id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(ACCION_ADICIONAL_6);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_ADICIONAL_5);
        }
        setIsObjecion(true);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    /**
     * Metodo que se encarga de abrir el modal de edicion del servicio
     *
     * @param id Id del registro
     * @param editarOgestionar Variable para saber si habre modal de edicio o
     * editar gestion
     */
    public void editarServicio(int id, boolean editarOgestionar) {
        getObjetoServicio().setId(id);
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (editarOgestionar) {
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_7);
        } else {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_ADICIONAL_6);
        }
        setIsObjecion(false);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        if (getObjeto().getAucAfiliadoId().getAsegAfiliadoId() == null) {
            addError("El Afiliado: Este campo es obligatorio");
        }
        if (getObjeto().getCntPrestadorSedeId().getId() == null) {
            addError("El Ips: Este campo es obligatorio");
        }
        if (getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
            addError("El Diagnostico: Este campo es obligatorio");
        }
        getAucHospitalizacionServicio().consultarHospitalizacionExceptoAnuladas(this);
        boolean dosIps = getAucHospitalizacionServicio().validarHospitalizacionIpsSoloDosActivas(getObjeto().getAucAfiliadoId().getAsegAfiliadoId(), this);
        if (dosIps) {
            getAucHospitalizacionServicio().validarHospitalizacionActivas(getObjeto().getAucAfiliadoId().getAsegAfiliadoId(), this);
        }
        if (!super.isError()) {
            super.setAccion(ACCION_GUARDAR);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }
    
    @SuppressWarnings("IncompatibleEquals")
    public void modificar() throws InterruptedException {
        try {
            SimpleDateFormat formatterUltimaEstanciaFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatterEgreso = new SimpleDateFormat("yyyy-MM-dd");
            if (getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
                addError("El Diagnostico: Este campo es obligatorio");
            }
            if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
               
                Date ultimaEstanciaFechaEgreso = (getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                        && !getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) ? getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() : null;
                if (ultimaEstanciaFechaEgreso != null) {

                    String formatUltimaEstanciaFechaEgreso = formatterUltimaEstanciaFechaEgreso.format(ultimaEstanciaFechaEgreso);

                    Date fechaEgreso = getObjeto().getAucEgresoId().getFechaEgreso();
                    if (fechaEgreso != null && !fechaEgreso.equals("")) {
                        String format = formatterEgreso.format(fechaEgreso);

                        if (!formatUltimaEstanciaFechaEgreso.equals(format)) {
                            addError("La fecha de egreso de la hospitalizacion debe ser igual a la ultima fecha de egreso del paciente");
                        }
                    }
                }
            }
            if(getObjeto().getAucEgresoId().isFallecido()){
                if(getObjeto().getAucEgresoId().getNumCertificado().length() > 32){
                    addError("El numero certificado tiene muchos caracteres");
                }
            }
            if(getObjeto().getAucIngresoId().getMaeAreaIngresoPrevenibleId() != null ){
                if(getObjeto().getAucIngresoId().getMaeCausaIngresoPrevalenteId() == null ){
                    addError("Causa de ingreso prevenible: Este campo es obligatorio");
                }
            }
            //getAucHospitalizacionServicio().consultarHospitalizacionExceptoAnuladas(this);
            if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                Date fechaIngreso = getObjeto().getAucIngresoId().getFechaIngreso();
                String formatFechaIngreso = formatterUltimaEstanciaFechaEgreso.format(fechaIngreso);
                LocalDate convertidaFechaIngreso = LocalDate.parse(formatFechaIngreso);

                Date fechaEgresoEstanciaInicial = getObjeto().getAucHospitalizacionEstanciaList().get(0).getFechaEgreso();
                if (fechaEgresoEstanciaInicial != null) {
                    String formatfechaEgresoUltimaEstancia = formatterUltimaEstanciaFechaEgreso.format(fechaEgresoEstanciaInicial);
                    LocalDate convertidaFechaEgresoEstanciaInicial = LocalDate.parse(formatfechaEgresoUltimaEstancia);
                    if (convertidaFechaEgresoEstanciaInicial != null) {
                        if (convertidaFechaIngreso.isAfter(convertidaFechaEgresoEstanciaInicial)) {
                            addError("No puede modificar la fecha de ingreso porque la fecha de egreso de la primer estancia es mayor");
                        }
                    }
                }
            }
            if (!getObjeto().getAucHospitalizacionInoportunidadList().isEmpty()) {
                for (AucHospitalizacionInoportunidad inoportunidad : getObjeto().getAucHospitalizacionInoportunidadList()) {
                    Date fechaInicioInoportunidad = inoportunidad.getFechaInicioInoportunidad();
                    if (fechaInicioInoportunidad != null) {
                        String formatfechaFinInoportunidad = formatterUltimaEstanciaFechaEgreso.format(fechaInicioInoportunidad);
                        Date convertidafechaInicioInoportunidad = formatterUltimaEstanciaFechaEgreso.parse(formatfechaFinInoportunidad);

                        Date fechaIngreso = getObjeto().getAucIngresoId().getFechaIngreso();
                        if (fechaIngreso != null) {

                            String formatFechaEgreso = formatterEgreso.format(fechaIngreso);
                            Date fechaConvertidaFechaIngreso = formatterEgreso.parse(formatFechaEgreso);
                            if (convertidafechaInicioInoportunidad.before(fechaConvertidaFechaIngreso)) {
                                addError("Tiene inoportunidades fecha inicio mayor que la fecha de ingreso del paciente");
                                break;
                            }
                        }
                    }

                }
            }
            if (!getObjeto().getAucHospitalizacionServicioList().isEmpty()) {
                for (AucHospitalizacionServicio inoportunidad : getObjeto().getAucHospitalizacionServicioList()) {
                    Date fechaInicioInoportunidad = inoportunidad.getFechaPrestacion();
                    if (fechaInicioInoportunidad != null) {

                        String formatfechaFinInoportunidad = formatterUltimaEstanciaFechaEgreso.format(fechaInicioInoportunidad);
                        LocalDate convertidaFechaFinInoportunidad = LocalDate.parse(formatfechaFinInoportunidad);
                        Date fechaEgreso = getObjeto().getAucIngresoId().getFechaIngreso();
                        if (fechaEgreso != null) {
                            String formatFechaEgreso = formatterEgreso.format(fechaEgreso);
                            LocalDate convetidaFechaEgreso = LocalDate.parse(formatFechaEgreso);
                            if (convetidaFechaEgreso.isAfter(convertidaFechaFinInoportunidad)) {
                                addError("Tiene servicios con fecha prestación mayor que la fecha de ingreso del paciente");
                                break;
                            }
                        }
                    }

                }
            }
            if(Validacion.isNumerico(getObjeto().getAucAfiliadoId().getNumeroDocumento())){
                BigDecimal prueba = new BigDecimal(getObjeto().getAucAfiliadoId().getNumeroDocumento());
                if(getObjeto().getAucEgresoId().getValorEstancia().compareTo(prueba) == 0){
                    addError("Valor Estancia:  No puede ser el mismo documento del afiliado");
                }   
            }
            if (!super.isError()) {
                if (getObjeto().isCierreAuditoria()) {
                    setBloquearBotonesCierreAuditoria(true);
                } else {
                    setBloquearBotonesCierreAuditoria(false);
                }
                super.setAccion(ACCION_MODIFICAR);
                super.setDoAccion(ACCION_MODIFICAR);
                getAucHospitalizacionServicio().Accion(this);
            }
        } catch (ParseException e) {
        }
        procesoFinal();
    }

    public void modificarIngreso() {
        try {
            SimpleDateFormat formatterUltimaEstanciaFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatterEgreso = new SimpleDateFormat("yyyy-MM-dd");
            if (getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
                addError("El Diagnostico: Este campo es obligatorio");
            }
            if(getObjeto().getAucIngresoId().getMaeAreaIngresoPrevenibleId() != null ){
                if(getObjeto().getAucIngresoId().getMaeCausaIngresoPrevalenteId() == null ){
                    addError("Causa de ingreso prevenible: Este campo es obligatorio");
                }
            }
            //getAucHospitalizacionServicio().consultarHospitalizacionExceptoAnuladas(this);

            if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                Date fechaIngreso = getObjeto().getAucIngresoId().getFechaIngreso();
                String formatFechaIngreso = formatterUltimaEstanciaFechaEgreso.format(fechaIngreso);
                LocalDate convertidaFechaIngreso = LocalDate.parse(formatFechaIngreso);

                Date fechaEgresoEstanciaInicial = getObjeto().getAucHospitalizacionEstanciaList().get(0).getFechaEgreso();
                if (fechaEgresoEstanciaInicial != null) {
                    String formatfechaEgresoUltimaEstancia = formatterUltimaEstanciaFechaEgreso.format(fechaEgresoEstanciaInicial);
                    LocalDate convertidaFechaEgresoEstanciaInicial = LocalDate.parse(formatfechaEgresoUltimaEstancia);
                    if (convertidaFechaEgresoEstanciaInicial != null) {
                        if (convertidaFechaIngreso.isAfter(convertidaFechaEgresoEstanciaInicial)) {
                            addError("No puede modificar la fecha de ingreso porque la fecha de egreso de la primer estancia es mayor");
                        }
                    }
                }

            }

            if (!getObjeto().getAucHospitalizacionInoportunidadList().isEmpty()) {
                for (AucHospitalizacionInoportunidad inoportunidad : getObjeto().getAucHospitalizacionInoportunidadList()) {
                    Date fechaInicioInoportunidad = inoportunidad.getFechaInicioInoportunidad();
                    if (fechaInicioInoportunidad != null) {
                        String formatfechaFinInoportunidad = formatterUltimaEstanciaFechaEgreso.format(fechaInicioInoportunidad);
                        Date convertidafechaInicioInoportunidad = formatterUltimaEstanciaFechaEgreso.parse(formatfechaFinInoportunidad);
                        Date fechaIngreso = getObjeto().getAucIngresoId().getFechaIngreso();
                        if (fechaIngreso != null) {
                            String formatFechaEgreso = formatterEgreso.format(fechaIngreso);
                            Date fechaConvertidaFechaIngreso = formatterEgreso.parse(formatFechaEgreso);
                            if (convertidafechaInicioInoportunidad.before(fechaConvertidaFechaIngreso)) {
                                addError("Tiene inoportunidades fecha inicio mayor que la fecha de ingreso del paciente");
                                break;
                            }
                        }
                    }

                }
            }
            if (!getObjeto().getAucHospitalizacionServicioList().isEmpty()) {
                for (AucHospitalizacionServicio inoportunidad : getObjeto().getAucHospitalizacionServicioList()) {
                    Date fechaInicioInoportunidad = inoportunidad.getFechaPrestacion();
                    if (fechaInicioInoportunidad != null) {

                        String formatfechaFinInoportunidad = formatterUltimaEstanciaFechaEgreso.format(fechaInicioInoportunidad);
                        LocalDate convertidaFechaFinInoportunidad = LocalDate.parse(formatfechaFinInoportunidad);
                        Date fechaEgreso = getObjeto().getAucIngresoId().getFechaIngreso();
                        if (fechaEgreso != null) {
                            String formatFechaEgreso = formatterEgreso.format(fechaEgreso);
                            LocalDate convetidaFechaEgreso = LocalDate.parse(formatFechaEgreso);
                            if (convetidaFechaEgreso.isAfter(convertidaFechaFinInoportunidad)) {
                                addError("Tiene servicios con fecha prestación mayor que la fecha de ingreso del paciente");
                                break;
                            }
                        }
                    }

                }
            }
            AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
            if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                addError("No se pude aplicar, por favor refrescar la pantalla");
            }
            if (!super.isError()) {
                super.setAccion(Url.ACCION_ADICIONAL_1);
                super.setDoAccion(Url.ACCION_ADICIONAL_1);
                getAucHospitalizacionServicio().Accion(this);
            }
        } catch (ParseException e) {
            addError("Hubo un error en las fechas");
        }

        procesoFinal();
    }
    
    public void modificarIngresoGestoras() {
      
        if (getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
            addError("El Diagnostico: Este campo es obligatorio");
        }
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_10);
            super.setDoAccion(Url.ACCION_ADICIONAL_5);
            getAucHospitalizacionServicio().Accion(this);
        }

        procesoFinal();
    }
    
    @SuppressWarnings("IncompatibleEquals")
    public void modificarEgreso() {
        try {
            if (getObjeto().getAucEgresoId().getFechaEgreso() == null) {
                addError("Fecha Egreso:  Este campo es obligatorio");
            }
            if (getObjeto().getAucEgresoId().isFallecido() == false && getObjeto().getAucEgresoId().getMaeDestinoEgresoId() == 0) {
                addError("Destino:  Este campo es obligatorio");
            }
            if (getObjeto().getAucEgresoId().getMaeConductaEgresoId() == 0) {
                addError("Conducta:  Este campo es obligatorio");
            }
            if (getObjeto().getAucEgresoId().getMaEspecialidadesId() == 0) {
                addError("Especialidad:  Este campo es obligatorio");
            }
            if (getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                addError("Debe de tener al menos una estacia");
            }
            for (AucHospitalizacionEstancia estancia : getObjeto().getAucHospitalizacionEstanciaList()) {
                if (estancia.getFechaEgreso() == null || estancia.getFechaEgreso().equals("")) {
                    addError("Tiene estancias abiertas, No puede guardar");
                    break;
                }
            }
            if(getObjeto().getAucEgresoId().isFallecido()){
                if(getObjeto().getAucEgresoId().getNumCertificado().length() > 32){
                    addError("El numero certificado tiene muchos caracteres");
                }
            }
            if (getObjeto().isCierreAuditoria()) {
                for (AucHospitalizacionInoportunidad inoportunidad : getObjeto().getAucHospitalizacionInoportunidadList()) {
                    if (inoportunidad.getFechaFinInoportunidad() == null || inoportunidad.getFechaFinInoportunidad().equals("")) {
                        addError("Tiene inoportunidades abiertas, No puede cerrar la Hospitalización");
                        break;
                    }
                }
                if (getObjeto().getAucEgresoId().getAucDiagnosticosList().isEmpty()) {
                    addError("Diagnostico egreso: debe tener por lo menos uno diagnostico");
                }
                if (getObjeto().getAucEgresoId().getValorEstancia() == null) {
                    addError("Valor Estancia:  Este campo es obligatorio");
                }
                AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
                if (hopitalizacion.getAucIngresoId().getIngreso() == 2) {
                    addError("El Reingreso:  Este campo es obligatorio");
                }
            }
            
            if(Validacion.isNumerico(getObjeto().getAucAfiliadoId().getNumeroDocumento())){
                BigDecimal prueba = new BigDecimal(getObjeto().getAucAfiliadoId().getNumeroDocumento());
                if(getObjeto().getAucEgresoId().getValorEstancia().compareTo(prueba) == 0){
                    addError("Valor Estancia:  No puede ser el mismo documento del afiliado");
                }   
            }
            
            
            if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                Date ultimaEstanciaFechaEgreso = (getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                        && !getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) ? getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() : null;
                if (ultimaEstanciaFechaEgreso != null) {
                    SimpleDateFormat formatterUltimaEstanciaFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
                    String formatUltimaEstanciaFechaEgreso = formatterUltimaEstanciaFechaEgreso.format(ultimaEstanciaFechaEgreso);

                    Date fechaEgreso = getObjeto().getAucEgresoId().getFechaEgreso();
                    SimpleDateFormat formatterEgreso = new SimpleDateFormat("yyyy-MM-dd");
                    String format = formatterEgreso.format(fechaEgreso);

                    if (!formatUltimaEstanciaFechaEgreso.equals(format)) {
                        addError("La fecha de egreso de la hospitalizacion debe ser igual a la ultima fecha de egreso del paciente");
                    }
                }
            }

            if (!getObjeto().getAucHospitalizacionInoportunidadList().isEmpty()) {
                for (AucHospitalizacionInoportunidad inoportunidad : getObjeto().getAucHospitalizacionInoportunidadList()) {
                    Date fechaFinInoportunidad = inoportunidad.getFechaFinInoportunidad();
                    if (fechaFinInoportunidad != null) {
                        SimpleDateFormat formatterUltimaEstanciaFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
                        String formatfechaFinInoportunidad = formatterUltimaEstanciaFechaEgreso.format(fechaFinInoportunidad);
                        //LocalDate convertidaFechaFinInoportunidad = LocalDate.parse(formatfechaFinInoportunidad);
                        Date convertidaFechaFinInoportunidad = formatterUltimaEstanciaFechaEgreso.parse(formatfechaFinInoportunidad);
                        Date fechaEgreso = getObjeto().getAucEgresoId().getFechaEgreso();
                        if (fechaEgreso != null) {
                            SimpleDateFormat formatterEgreso = new SimpleDateFormat("yyyy-MM-dd");
                            String formatFechaEgreso = formatterEgreso.format(fechaEgreso);
                            ///LocalDate convetidaFechaEgreso = LocalDate.parse(formatFechaEgreso);
                            Date convetidaFechaEgreso = formatterEgreso.parse(formatFechaEgreso);
                            if (convetidaFechaEgreso.before(convertidaFechaFinInoportunidad)) {
                                addError("Tiene inoportunidades fecha fin mayores que la fecha de egreso del paciente");
                                break;
                            }
                        }
                    }
                }
            }
            AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
            if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                addError("No se pude aplicar, por favor refrescar la pantalla");
            }
            //getAucHospitalizacionServicio().consultarHospitalizacionExceptoAnuladasEgreso(this);
            if (!super.isError()) {
                if (getObjeto().isCierreAuditoria()) {
                    setBloquearBotonesCierreAuditoria(true);
                } else {
                    setBloquearBotonesCierreAuditoria(false);
                }
                if (getObjeto().getAucEgresoId().getId() == null) {
                    super.setAccion(Url.ACCION_ADICIONAL_1);
                    super.setDoAccion(Url.ACCION_ADICIONAL_4);
                } else {
                    super.setAccion(Url.ACCION_ADICIONAL_1);
                    super.setDoAccion(Url.ACCION_ADICIONAL_5);
                }
                getAucHospitalizacionServicio().Accion(this);
            }
        } catch (ParseException e) {
            addError("Hubo un error en el proceso de fechas ");
        }
        procesoFinal();
    }
    
    @SuppressWarnings("IncompatibleEquals")
    public void modificarEgresoGestoras() {
        try {
            if (getObjeto().getAucEgresoId().getFechaEgreso() == null) {
                addError("Fecha Egreso:  Este campo es obligatorio");
            }
            if (getObjeto().getAucEgresoId().isFallecido() == false && getObjeto().getAucEgresoId().getMaeDestinoEgresoId() == 0) {
                addError("Destino:  Este campo es obligatorio");
            }
            if (getObjeto().getAucEgresoId().getMaeConductaEgresoId() == 0) {
                addError("Conducta:  Este campo es obligatorio");
            }
            if (getObjeto().getAucEgresoId().getMaEspecialidadesId() == 0) {
                addError("Especialidad:  Este campo es obligatorio");
            }
            if (getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                addError("Debe de tener al menos una estacia");
            }
            for (AucHospitalizacionEstancia estancia : getObjeto().getAucHospitalizacionEstanciaList()) {
                if (estancia.getFechaEgreso() == null || estancia.getFechaEgreso().equals("")) {
                    addError("Tiene estancias abiertas, No puede guardar");
                    break;
                }
            }
            if(getObjeto().getAucEgresoId().isFallecido()){
                if(getObjeto().getAucEgresoId().getNumCertificado().length() > 32){
                    addError("El numero certificado tiene muchos caracteres");
                }
            }
            if (getObjeto().isCierreAuditoria()) {
                if (getObjeto().getAucEgresoId().getAucDiagnosticosList().isEmpty()) {
                    addError("Diagnostico egreso: debe tener por lo menos uno diagnostico");
                }
                if (getObjeto().getAucEgresoId().getValorEstancia() == null) {
                    addError("Valor Estancia:  Este campo es obligatorio");
                }
                AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
                if (hopitalizacion.getAucIngresoId().getIngreso() == 2) {
                    addError("El Reingreso:  Este campo es obligatorio");
                }
            }
            
            if(Validacion.isNumerico(getObjeto().getAucAfiliadoId().getNumeroDocumento())){
                BigDecimal prueba = new BigDecimal(getObjeto().getAucAfiliadoId().getNumeroDocumento());
                if(getObjeto().getAucEgresoId().getValorEstancia().compareTo(prueba) == 0){
                    addError("Valor Estancia:  No puede ser el mismo documento del afiliado");
                }   
            }
            
            
            if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                Date ultimaEstanciaFechaEgreso = (getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                        && !getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) ? getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() : null;
                if (ultimaEstanciaFechaEgreso != null) {
                    SimpleDateFormat formatterUltimaEstanciaFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
                    String formatUltimaEstanciaFechaEgreso = formatterUltimaEstanciaFechaEgreso.format(ultimaEstanciaFechaEgreso);

                    Date fechaEgreso = getObjeto().getAucEgresoId().getFechaEgreso();
                    SimpleDateFormat formatterEgreso = new SimpleDateFormat("yyyy-MM-dd");
                    String format = formatterEgreso.format(fechaEgreso);

                    if (!formatUltimaEstanciaFechaEgreso.equals(format)) {
                        addError("La fecha de egreso de la hospitalizacion debe ser igual a la ultima fecha de egreso del paciente");
                    }
                }
            }

            AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
            if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                addError("No se pude aplicar, por favor refrescar la pantalla");
            }
            //getAucHospitalizacionServicio().consultarHospitalizacionExceptoAnuladasEgreso(this);
            if (!super.isError()) {
                if (getObjeto().isCierreAuditoria()) {
                    setBloquearBotonesCierreAuditoria(true);
                } else {
                    setBloquearBotonesCierreAuditoria(false);
                }
                if (getObjeto().getAucEgresoId().getId() == null) {
                    super.setAccion(Url.ACCION_ADICIONAL_10);
                    super.setDoAccion(Url.ACCION_ADICIONAL_2);
                } else {
                    super.setAccion(Url.ACCION_ADICIONAL_10);
                    super.setDoAccion(Url.ACCION_ADICIONAL_3);
                }
                getAucHospitalizacionServicio().Accion(this);
            }
        } catch (Exception e) {
            addError("Hubo un error en el proceso de fechas ");
        }
        procesoFinal();
    }
    
    public void modificarSeguimiento() {
        Maestro tipoSeguimiento = getHashTipoSeguimiento().get(getObjetoSeguimiento().getMaeTipoSeguimientoId());
        getObjetoSeguimiento().setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
        getObjetoSeguimiento().setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
        getObjetoSeguimiento().setMaeTipoSeguimientoTipo(tipoSeguimiento.getTipo());
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void modificarGestorasRegionales() {
        Maestro tipoGestion = getHashSeguimientoGestores().get(getObjetoSeguimiento().getMaeTipoGestionId());
        if(tipoGestion != null){
            getObjetoSeguimiento().setMaeTipoGestionCodigo(tipoGestion.getValor());
            getObjetoSeguimiento().setMaeTipoGestionValor(tipoGestion.getNombre());
            getObjetoSeguimiento().setMaeTipoGestionTipo(tipoGestion.getTipo());
        }
        
        Maestro tipoGestionEstado = getHashSeguimientoEstado().get(getObjetoSeguimiento().getMaeTipoGestionEstadoId());
        if(tipoGestionEstado != null){
            getObjetoSeguimiento().setMaeTipoGestionEstadoCodigo(tipoGestionEstado.getValor());
            getObjetoSeguimiento().setMaeTipoGestionEstadoValor(tipoGestionEstado.getNombre());
            getObjetoSeguimiento().setMaeTipoGestionEstadoTipo(tipoGestionEstado.getTipo());
        }
        
        Maestro maestro = getAucHospitalizacionServicio().consultarMaestro(getObjetoSeguimiento().getMaeTipoGestionId(), this);
        if(maestro != null){
            if(maestro.contieneAccion(MaestroAccion.AUC_SEGUIMIENTO_GESTORES_IPS_RECEPTORA)){
                if(getObjetoSeguimiento().getCntPrestadoresId() == null){
                        addError("El campo Ips Receptora es obligatorio");
                }
            }
        }
        Maestro destinoGR = getHashDestinoGR().get(getObjetoSeguimiento().getMaeDestinoId());
        if(destinoGR != null){
            getObjetoSeguimiento().setMaeDestinoCodigo(destinoGR.getValor());
            getObjetoSeguimiento().setMaeDestinoValor(destinoGR.getNombre());
            getObjetoSeguimiento().setMaeDestinoTipo(destinoGR.getTipo());
        }else{
            getObjetoSeguimiento().setMaeDestinoCodigo(null);
            getObjetoSeguimiento().setMaeDestinoValor(null);
            getObjetoSeguimiento().setMaeDestinoTipo(null);
        }
        if(!super.isError()){
            super.setAccion(Url.ACCION_ADICIONAL_10);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            getAucHospitalizacionServicio().Accion(this);
        }
        
        procesoFinal();
    }
    
    public void modificarSeguimientoGestion() {
        Maestro tipoSeguimiento = getHashTipoSeguimiento().get(getObjetoSeguimiento().getMaeTipoSeguimientoId());
        getObjetoSeguimiento().setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
        getObjetoSeguimiento().setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    @SuppressWarnings("UnusedAssignment")
    public void modificarEstancia() throws ParseException {

        Maestro servicio = getHashServicio().get(getObjetoEstancia().getMaeServicioId());
        if (servicio != null) {
            getObjetoEstancia().setMaeServicioCodigo(servicio.getValor());
            getObjetoEstancia().setMaeServicioValor(servicio.getNombre());
        }
        calcularDias(getObjetoEstancia().getFechaIngreso(), getObjetoEstancia().getFechaEgreso());
        if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            int posicion = -1;
            for (AucHospitalizacionEstancia estancia : getObjeto().getAucHospitalizacionEstanciaList()) {
                if (estancia.getId().equals(getObjetoEstancia().getId())) {
                    posicion = getObjeto().getAucHospitalizacionEstanciaList().indexOf(estancia);
                }
            }
            if (posicion != -1) {
                int tamaño = posicion + 1;
                int posicionAnterior = 0;
                int posicionSiguiente = 0;
                if (posicion == 0) {
                    if (tamaño < getObjeto().getAucHospitalizacionEstanciaList().size()) {
                        posicionSiguiente = posicion + 1;
                        AucHospitalizacionEstancia estanciaSiguiente = getObjeto().getAucHospitalizacionEstanciaList().get(posicionSiguiente);
                        if (estanciaSiguiente.getMaeServicioId() == getObjetoEstancia().getMaeServicioId()) {
                            addError("No puede modificar el servicio porque la estancia siguiente ya existe");
                        }
                    }
                }
                if (posicion > 0) {
                    posicionAnterior = posicion - 1;
                    posicionSiguiente = posicion + 1;
                    AucHospitalizacionEstancia estanciaAnterior = getObjeto().getAucHospitalizacionEstanciaList().get(posicionAnterior);
                    if (estanciaAnterior.getMaeServicioId() == getObjetoEstancia().getMaeServicioId()) {
                        addError("No puede modificar el servicio porque la estancia anterior ya existe");
                    }

                    if (tamaño < getObjeto().getAucHospitalizacionEstanciaList().size()) {
                        AucHospitalizacionEstancia estanciaSiguiente = getObjeto().getAucHospitalizacionEstanciaList().get(posicionSiguiente);
                        if (estanciaSiguiente.getMaeServicioId() == getObjetoEstancia().getMaeServicioId()) {
                            addError("No puede modificar el servicio porque la estancia siguiente ya existe");
                        }
                    }
                }

            }
        }
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAucHospitalizacionServicio().Accion(this);

        procesoFinal();
    }

    public void modificarJustificacionEstanciaProlongada() {
        Maestro causa = getHashCausaEstancia().get(getObjetoJustificacionEstanciasProlongada().getMaeCausaEstanciaProlongadaId());
        if (causa != null) {
            getObjetoJustificacionEstanciasProlongada().setMaeCausaEstanciaProlongadaCodigo(causa.getValor());
            getObjetoJustificacionEstanciasProlongada().setMaeCausaEstanciaProlongadaValor(causa.getNombre());
        }
        Maestro propuesta = getHashPropuestaIntervencion().get(getObjetoJustificacionEstanciasProlongada().getMaePropuestaIntervensionId());
        if (propuesta != null) {
            getObjetoJustificacionEstanciasProlongada().setMaePropuestaIntervensionCodigo(propuesta.getValor());
            getObjetoJustificacionEstanciasProlongada().setMaePropuestaIntervensionValor(propuesta.getNombre());
        }
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_8);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    @SuppressWarnings("UnusedAssignment")
    public void modificarEstanciaGestion() throws ParseException {

        Maestro servicio = getHashServicio().get(getObjetoEstancia().getMaeServicioId());
        if (servicio != null) {
            getObjetoEstancia().setMaeServicioCodigo(servicio.getValor());
            getObjetoEstancia().setMaeServicioValor(servicio.getNombre());
        }
        calcularDias(getObjetoEstancia().getFechaIngreso(), getObjetoEstancia().getFechaEgreso());
        if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            int posicion = -1;
            for (AucHospitalizacionEstancia estancia : getObjeto().getAucHospitalizacionEstanciaList()) {
                if (estancia.getId().equals(getObjetoEstancia().getId())) {
                    posicion = getObjeto().getAucHospitalizacionEstanciaList().indexOf(estancia);
                }
            }
            if (posicion != -1) {
                int tamaño = posicion + 1;
                int posicionAnterior = 0;
                int posicionSiguiente = 0;
                if (posicion == 0) {
                    if (tamaño < getObjeto().getAucHospitalizacionEstanciaList().size()) {
                        posicionSiguiente = posicion + 1;
                        AucHospitalizacionEstancia estanciaSiguiente = getObjeto().getAucHospitalizacionEstanciaList().get(posicionSiguiente);
                        if (estanciaSiguiente.getMaeServicioId() == getObjetoEstancia().getMaeServicioId()) {
                            addError("No puede modificar el servicio porque la estancia siguiente ya existe");
                        }
                    }
                }
                if (posicion > 0) {
                    posicionAnterior = posicion - 1;
                    posicionSiguiente = posicion + 1;
                    AucHospitalizacionEstancia estanciaAnterior = getObjeto().getAucHospitalizacionEstanciaList().get(posicionAnterior);
                    if (estanciaAnterior.getMaeServicioId() == getObjetoEstancia().getMaeServicioId()) {
                        addError("No puede modificar el servicio porque la estancia anterior ya existe");
                    }

                    if (tamaño < getObjeto().getAucHospitalizacionEstanciaList().size()) {
                        AucHospitalizacionEstancia estanciaSiguiente = getObjeto().getAucHospitalizacionEstanciaList().get(posicionSiguiente);
                        if (estanciaSiguiente.getMaeServicioId() == getObjetoEstancia().getMaeServicioId()) {
                            addError("No puede modificar el servicio porque la estancia siguiente ya existe");
                        }
                    }
                }

            }
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_8);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    public void modificarJustificacionEstanciaProlongadaGestion() {
        Maestro causa = getHashCausaEstancia().get(getObjetoJustificacionEstanciasProlongada().getMaeCausaEstanciaProlongadaId());
        if (causa != null) {
            getObjetoJustificacionEstanciasProlongada().setMaeCausaEstanciaProlongadaCodigo(causa.getValor());
            getObjetoJustificacionEstanciasProlongada().setMaeCausaEstanciaProlongadaValor(causa.getNombre());
        }
        Maestro propuesta = getHashPropuestaIntervencion().get(getObjetoJustificacionEstanciasProlongada().getMaePropuestaIntervensionId());
        if (propuesta != null) {
            getObjetoJustificacionEstanciasProlongada().setMaePropuestaIntervensionCodigo(propuesta.getValor());
            getObjetoJustificacionEstanciasProlongada().setMaePropuestaIntervensionValor(propuesta.getNombre());
        }
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_6);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void modificarPatologia() {
        Maestro patologia = getHashPatologia().get(getObjetoPatologia().getMaePatologiaId());
        if (patologia != null) {
            getObjetoPatologia().setMaePatologiaCodigo(patologia.getValor());
            getObjetoPatologia().setMaePatologiaValor(patologia.getNombre());
        }
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void modificarPatologiaGestion() {
        Maestro patologia = getHashPatologia().get(getObjetoPatologia().getMaePatologiaId());
        getObjetoPatologia().setMaePatologiaCodigo(patologia.getValor());
        getObjetoPatologia().setMaePatologiaValor(patologia.getNombre());
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_9);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    @SuppressWarnings("IncompatibleEquals")
    public void modificarInoportunidad() throws ParseException {
        calcularDiasInoportunidad(getObjetoInoportunidad().getFechaInicioInoportunidad(), getObjetoInoportunidad().getFechaFinInoportunidad());
        getAucHospitalizacionServicio().consultarHospitalizacionInoportunidad(this);
        Maestro tipo = getHashTipoInoportunidad().get(getObjetoInoportunidad().getMaeTipoInoportunidadId());
        if (tipo != null) {
            getObjetoInoportunidad().setMaeTipoInoportunidadCodigo(tipo.getValor());
            getObjetoInoportunidad().setMaeTipoInoportunidadValor(tipo.getNombre());
        }
        Maestro motivo = getHashMotivoInoportunidad().get(getObjetoInoportunidad().getMaeMotivoInoportunidadId());
        if (motivo != null) {
            getObjetoInoportunidad().setMaeMotivoInoportunidadCodigo(motivo.getValor());
            getObjetoInoportunidad().setMaeMotivoInoportunidadValor(motivo.getNombre());
        }
        if (getObjetoInoportunidad().getFechaFinInoportunidad() != null && !getObjetoInoportunidad().getFechaFinInoportunidad().equals("")) {
            if (getObjetoInoportunidad().getMaeMotivoInoportunidadId() == null) {
                addError("Motivo Fin: Este campo es obligatorio");
            }
        }

        if (!super.isError()) {
            super.setAccion(ACCION_MODIFICAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_4);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    @SuppressWarnings("IncompatibleEquals")
    public void modificarInoportunidadGestion() throws ParseException {
        calcularDiasInoportunidad(getObjetoInoportunidad().getFechaInicioInoportunidad(), getObjetoInoportunidad().getFechaFinInoportunidad());
        getAucHospitalizacionServicio().consultarHospitalizacionInoportunidad(this);
        Maestro tipo = getHashTipoInoportunidad().get(getObjetoInoportunidad().getMaeTipoInoportunidadId());
        if (tipo != null) {
            getObjetoInoportunidad().setMaeTipoInoportunidadCodigo(tipo.getValor());
            getObjetoInoportunidad().setMaeTipoInoportunidadValor(tipo.getNombre());
        }

        Maestro motivo = getHashMotivoInoportunidad().get(getObjetoInoportunidad().getMaeMotivoInoportunidadId());
        if (motivo != null) {
            getObjetoInoportunidad().setMaeMotivoInoportunidadCodigo(motivo.getValor());
            getObjetoInoportunidad().setMaeMotivoInoportunidadValor(motivo.getNombre());
        }
        if (getObjetoInoportunidad().getFechaFinInoportunidad() != null && !getObjetoInoportunidad().getFechaFinInoportunidad().equals("")) {
            if (getObjetoInoportunidad().getMaeMotivoInoportunidadId() == null) {
                addError("Motivo Fin: Este campo es obligatorio");
            }
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_4);
            super.setTakeAccion(Url.ACCION_GUARDAR);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    @SuppressWarnings("IncompatibleEquals")
    public void modificarAdverso() {
        getAucHospitalizacionServicio().consultarHospitalizacionAdverso(this);
        if (getObjetoAdverso().getFechaSolicitudAnalisis() != null && !getObjetoAdverso().getFechaSolicitudAnalisis().equals("")) {
            if (getObjetoAdverso().getFechaSolicitudAnalisis().before(getObjetoAdverso().getFechaEvento())) {
                addError("la fecha de solicitud de análisis a la IPS no puede ser mayor a la fecha evento");
            }
        }
        SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
        String formatFechaEgreso = formatterFechaEgreso.format(getObjetoAdverso().getFechaEvento());
        LocalDate fechaEgreso = LocalDate.parse(formatFechaEgreso);
        if (fechaEgreso != null) {
            String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
            LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
            if (fechaIngresoHospitalizacion.isAfter(fechaEgreso)) {
                addError("La fecha de evento es menor a las fechas de hospitalizacion de ingreso");
            }
        }
        String formatFechaSolicitud = formatterFechaEgreso.format(getObjetoAdverso().getFechaSolicitudAnalisis());
        LocalDate fechaSolicitud = LocalDate.parse(formatFechaSolicitud);
        if (fechaSolicitud != null) {
            String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
            LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
            if (fechaIngresoHospitalizacion.isAfter(fechaSolicitud)) {
                addError("La fecha de solicitud es menor a las fechas de hospitalizacion de ingreso");
            }
        }
        if (getObjetoAdverso().getFechaAnalisis() != null && !getObjetoAdverso().getFechaAnalisis().equals("")) {
            String formatFechaAnalisis = formatterFechaEgreso.format(getObjetoAdverso().getFechaAnalisis());
            LocalDate fechaAnalisis = LocalDate.parse(formatFechaAnalisis);
            if (fechaAnalisis != null && !fechaAnalisis.equals("")) {
                String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
                LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
                if (fechaIngresoHospitalizacion.isAfter(fechaAnalisis)) {
                    addError("La fecha de analisis es menor a las fechas de hospitalizacion de ingreso");
                }
            }
        }

        if (!super.isError()) {
            Maestro categoria = getHashCategoriaEvento().get(getObjetoAdverso().getMaeCategoriaEventoId());
            if (categoria != null) {
                getObjetoAdverso().setMaeCategoriaEventoCodigo(categoria.getValor());
                getObjetoAdverso().setMaeCategoriaEventoValor(categoria.getNombre());
            }
            Maestro subcategoria = getHashSubcategoriaEvento().get(getObjetoAdverso().getMaeSubcategoriaEventoId());
            if (subcategoria != null) {
                getObjetoAdverso().setMaeSubcategoriaEventoCodigo(subcategoria.getValor());
                getObjetoAdverso().setMaeSubcategoriaEventoValor(subcategoria.getNombre());
            }
            Maestro conclusion = getHashConclusionEveno().get(getObjetoAdverso().getMaeConclusionEventoId());
            if (conclusion != null) {
                getObjetoAdverso().setMaeConclusionEventoCodigo(conclusion.getValor());
                getObjetoAdverso().setMaeConclusionEventoValor(conclusion.getNombre());
            }
            super.setAccion(ACCION_MODIFICAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_5);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    @SuppressWarnings("IncompatibleEquals")
    public void modificarAdversoGestion() {
        SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
        String formatFechaEgreso = formatterFechaEgreso.format(getObjetoAdverso().getFechaEvento());
        LocalDate fechaEgreso = LocalDate.parse(formatFechaEgreso);
        if (fechaEgreso != null) {
            String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
            LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
            if (fechaIngresoHospitalizacion.isAfter(fechaEgreso)) {
                addError("La fecha de evento es menor a las fechas de hospitalizacion de ingreso");
            }
        }
        String formatFechaSolicitud = formatterFechaEgreso.format(getObjetoAdverso().getFechaSolicitudAnalisis());
        LocalDate fechaSolicitud = LocalDate.parse(formatFechaSolicitud);
        if (fechaSolicitud != null) {
            String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
            LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
            if (fechaIngresoHospitalizacion.isAfter(fechaSolicitud)) {
                addError("La fecha de solicitud es menor a las fechas de hospitalizacion de ingreso");
            }
        }
        if (getObjetoAdverso().getFechaAnalisis() != null && !getObjetoAdverso().getFechaAnalisis().equals("")) {
            String formatFechaAnalisis = formatterFechaEgreso.format(getObjetoAdverso().getFechaAnalisis());
            LocalDate fechaAnalisis = LocalDate.parse(formatFechaAnalisis);
            if (fechaAnalisis != null && !fechaAnalisis.equals("")) {
                String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
                LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
                if (fechaIngresoHospitalizacion.isAfter(fechaAnalisis)) {
                    addError("La fecha de analisis es menor a las fechas de hospitalizacion de ingreso");
                }
            }
        }

        if (!super.isError()) {
            Maestro categoria = getHashCategoriaEvento().get(getObjetoAdverso().getMaeCategoriaEventoId());
            if (categoria != null) {
                getObjetoAdverso().setMaeCategoriaEventoCodigo(categoria.getValor());
                getObjetoAdverso().setMaeCategoriaEventoValor(categoria.getNombre());
            }
            Maestro subcategoria = getHashSubcategoriaEvento().get(getObjetoAdverso().getMaeSubcategoriaEventoId());
            if (subcategoria != null) {
                getObjetoAdverso().setMaeSubcategoriaEventoCodigo(subcategoria.getValor());
                getObjetoAdverso().setMaeSubcategoriaEventoValor(subcategoria.getNombre());
            }
            Maestro conclusion = getHashConclusionEveno().get(getObjetoAdverso().getMaeConclusionEventoId());
            if (conclusion != null) {
                getObjetoAdverso().setMaeConclusionEventoCodigo(conclusion.getValor());
                getObjetoAdverso().setMaeConclusionEventoValor(conclusion.getNombre());
            }
            super.setAccion(Url.ACCION_ADICIONAL_3);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            getAucHospitalizacionServicio().Accion(this);
        }

        procesoFinal();
    }

    public void modificarObjecion() {
        if (getObjetoObjecion().getCantidadSolicitada() == 0) {
            addError("La cantidad debe ser mayor a 0");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_MODIFICAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_6);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    public void modificarObjecionGestion() {
        if (getObjetoObjecion().getCantidadSolicitada() == 0) {
            addError("La cantidad debe ser mayor a 0");
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_10);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    public void modificarServicio() {
        getAucHospitalizacionServicio().consultarHospitalizacionServicio(this);
        SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
        String formatFechaEgreso = formatterFechaEgreso.format(getObjetoServicio().getFechaPrestacion());
        LocalDate fechaEgreso = LocalDate.parse(formatFechaEgreso);
        if (fechaEgreso != null) {
            String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
            LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
            if (fechaIngresoHospitalizacion.isAfter(fechaEgreso)) {
                addError("La fecha de presentacion es menor a las fechas de hospitalizacion de ingreso");
            }
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_MODIFICAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_7);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    public void modificarServicioGestion() {
        SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
        String formatFechaEgreso = formatterFechaEgreso.format(getObjetoServicio().getFechaPrestacion());
        LocalDate fechaEgreso = LocalDate.parse(formatFechaEgreso);
        if (fechaEgreso != null) {
            String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
            LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
            if (fechaIngresoHospitalizacion.isAfter(fechaEgreso)) {
                addError("La fecha de presentacion es menor a las fechas de hospitalizacion de ingreso");
            }
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_ADICIONAL_2);
            super.setDoAccion(Url.ACCION_ADICIONAL_11);
            getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    public void rechazarSugerido() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void noAptoRescate() {
        getObjetoSeguimiento().setAucHospitalizacionId(getObjeto());
        Maestro tipoSeguimiento = getHashTipoSeguimiento().get(getObjetoSeguimiento().getMaeTipoSeguimientoId());
        if(tipoSeguimiento != null){
            getObjetoSeguimiento().setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
            getObjetoSeguimiento().setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre()); 
            getObjetoSeguimiento().setMaeTipoSeguimientoTipo(tipoSeguimiento.getTipo()); 
        }
        //2023-09-22 jyperez configuración valor por defecto
        getObjetoSeguimiento().setOrigen(AucHospitalizacionSeguimiento.ORIGEN_MANUAL);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(AucHospitalizacionSeguimiento.DESCRIPCION_NO_APTO_RESCATE).append(getObjetoSeguimiento().getDescripcion());
        getObjetoSeguimiento().setDescripcion(stringBuilder1.toString());
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_13);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void ventanaGestionar(int id) {
        getObjeto().setId(id);
        setListaDiagnosticosBorrar(new ArrayList<>());
        setListaDiagnosticosEgresoBorrar(new ArrayList<>());
        setListaDiagnosticosEstanciaEspecialidadBorrar(new ArrayList<>());
        setCrearOgestionarDiagnostico(false);
        getAucHospitalizacionServicio().consultarMaestroDestino(this);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void gestionar() {
        if (!super.isError()) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_MODIFICAR);
            //getAucHospitalizacionServicio().Accion(this);
        }
        procesoFinal();
    }

    @SuppressWarnings("UnusedAssignment")
    public void adicionarContacto() {
        int LONGITUD_MAXIMA_CONTACTO = 32;
        boolean esContactoValido = true;
        AucAfiliadoContacto tuPersonaContacto = getAucAfiliadoContacto();

        for (AucAfiliadoContacto contacto : getListaTuPersonaContacto()) {
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

        if (!tuPersonaContacto.getNumeroContacto().matches("\\d*")) {
            addError("El número de contacto tiene letras");
            esContactoValido = false;
        }

        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide();");
            PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
            PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
            PrimeFaces.current().ajax().update("frmGestionar:tablaContactoPersona");
        }
        generarMensajes();
    }

    public void adicionarContactoGestionar() {
        int LONGITUD_MAXIMA_CONTACTO = 32;
        boolean esContactoValido = true;
        AucAfiliadoContacto tuPersonaContacto = getAucAfiliadoContacto();

        for (AucAfiliadoContacto contacto : getListaTuPersonaContacto()) {
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

        if (!tuPersonaContacto.getNumeroContacto().matches("\\d*")) {
            addError("El número de contacto tiene letras");
            esContactoValido = false;
        }

        if (esContactoValido) {
            getAucAfiliadoContacto().setAucAfiliadoId(getObjeto().getAucAfiliadoId());
            getAucHospitalizacionServicio().guardarAfiliadoContacto(this);
            tuPersonaContacto.setPosicion(getListaTuPersonaContacto().size());
            getListaTuPersonaContacto().add(tuPersonaContacto);
        }

        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearContactoGestion').hide();");
            PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
            PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
            PrimeFaces.current().ajax().update("frmGestionar:tablaContactoPersona");
        }
        generarMensajes();
    }
    
    @SuppressWarnings("LocalVariableHidesMemberVariable")
    public void borrarContacto(AucAfiliadoContacto personaContacto) {
        List<AucAfiliadoContacto> listaContactosBorrar = new ArrayList<>();
        int posicionEliminar = personaContacto.getPosicion();
        getListaTuPersonaContacto().stream().filter(contacto -> (contacto.getPosicion() != posicionEliminar)).forEachOrdered(contacto -> {
            listaContactosBorrar.add(contacto);
        });

        if (personaContacto.getId() != null) {
            this.getAucAfiliadoContacto().setId(personaContacto.getId());
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(Url.ACCION_MODIFICAR);
            getAucHospitalizacionServicio().Accion(this);
        }

        setListaTuPersonaContacto(listaContactosBorrar);

        addMensaje("Se ha realizado la eliminación del contacto");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:tablaContactoPersona");
        PrimeFaces.current().ajax().update("frmGestionar:tablaContactoPersona");
        PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
    }
    
    public void borrarDiagnosticoEspecialidadBD(AucHospitalizacion hospitalizacion) {
     
        if (hospitalizacion.getMaEspecialidadesId() != null) {
            hospitalizacion.setMaEspecialidadesId(null);
            hospitalizacion.setMaEspecialidadesCodigo(null);
            hospitalizacion.setMaEspecialidadesValor(null);
            getAucHospitalizacionServicio().guardarDiagnosticoEspecialidad(this);
        }
        addMensaje("Se ha realizado la eliminación de la especalidad");
        generarMensajes();
        //PrimeFaces.current().ajax().update("frmCrear:panelEspecialidadGestionar");
        PrimeFaces.current().ajax().update("frmGestionar:panelEspecialidadGestionar");
        //PrimeFaces.current().ajax().update("frmEditar:panelEspecialidadGestionar");
    }
    
    public void borrarDiagnosticoEspecialidadMemoria(AucHospitalizacion hospitalizacion) {
     
        if (hospitalizacion.getMaEspecialidadesId() != null) {
            hospitalizacion.setMaEspecialidadesId(null);
            hospitalizacion.setMaEspecialidadesCodigo(null);
            hospitalizacion.setMaEspecialidadesValor(null);
        }
        addMensaje("Se ha realizado la eliminación de la especalidad");
        generarMensajes();
        //PrimeFaces.current().ajax().update("frmCrear:panelEspecialidadGestionar");
        PrimeFaces.current().ajax().update("frmDiagnosticoEspecialidad:panelDiagnosticoEspecialidad");
        //PrimeFaces.current().ajax().update("frmEditar:panelEspecialidadGestionar");
    }
    
    @SuppressWarnings("LocalVariableHidesMemberVariable")
    public void borrarTecnologiaMemria(AucHospitalizacionServicio servicio) {    
        List<AucHospitalizacionServicio> listaContactos = new ArrayList<>();
        int posicionEliminar = servicio.getPos();
        getListaMemoriaTecnologia().stream().filter(contacto -> (contacto.getPos() != posicionEliminar)).forEachOrdered(contacto -> {
            listaContactos.add(contacto);
        });
        setListaMemoriaTecnologia(listaContactos);

        addMensaje("Se ha realizado la eliminación del contacto");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmServicio:tablaServicios");
    }

    public void consultarAfiliado() {
        PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
    }

    public void refrescarHospitalizacion() {
        getAucHospitalizacionServicio().listarAfiliado(this);
    }

    public void refrescarAfiliado() {
        getAucHospitalizacionServicio().listarAfiliado(this);
    }

    public void refrescarSeguimientos() {
        getAucHospitalizacionServicio().listarSeguimientos(this);
    }
    
    public void refrescarGestorasRegionales() {
        getAucHospitalizacionServicio().listarGestorasRegionales(this);
    }
    
    public void refrescarDiagnosticoEstancia() {
        getAucHospitalizacionServicio().listarDiagnosticoEstancia(this);
    }
    
    public void refrescarDiagnosticosIngreso() {
        getAucHospitalizacionServicio().listarDiagnosticosIngreso(this);
    }

    public void refrescarDiagnosticosEgreso() {
        getAucHospitalizacionServicio().listarDiagnosticosEgreso(this);
    }

    public void refrescarEstancias() {
        getAucHospitalizacionServicio().listarEstancias(this);
    }

    public void refrescarJustifiacionEstanciasProlongada() {
        getAucHospitalizacionServicio().listarJustifiacionEstanciasProlongada(this);
    }

    public void refrescarPalogias() {
        getAucHospitalizacionServicio().listarPalogias(this);
    }

    public void refrescarInoportunidades() {
        getAucHospitalizacionServicio().listarInoportunidades(this);
    }

    public void refrescarAdversos() {
        getAucHospitalizacionServicio().listarAdversos(this);
    }

    public void refrescarObjeciones() {
        getAucHospitalizacionServicio().listarObjeciones(this);
    }

    public void refrescarServicios() {
        getAucHospitalizacionServicio().listarServicios(this);
    }

    public void refrescarGestionRiesgo() {
        getAucHospitalizacionServicio().listarGestionRiesgo(this);
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        AsegAfiliado afiliado = (AsegAfiliado) event.getObject();
        boolean isActivo = getAucHospitalizacionServicio().validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion(), this);
        if (isActivo) {
            //boolean hospitalizacionActivo = getAucHospitalizacionServicio().validarHospitalizacionActivas(afiliado, this);
            //if (hospitalizacionActivo) {
            //getObjeto().getAucAfiliadoId().setId(afiliado.getId());
            getObjeto().getAucAfiliadoId().setAsegAfiliadoId(afiliado);
            getAucHospitalizacionServicio().consultarTelefonosAfiliadoAseguradoYhospitalizacion(this);
            getAucHospitalizacionServicio().completarAfiliado(this);
            getParamConsulta(1).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrear:pAfiliadoCrear");
            PrimeFaces.current().ajax().update("frmCrear:charlsonCrear");
            PrimeFaces.current().ajax().update("frmCrear:panelTablaContactosCrearAseg");
            //PrimeFaces.current().ajax().update("frmEditar:panelAfiliadoEditar");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
            //}

        }
        generarMensajes();
    }

    /**
     * Calcula los dias dependiendo de las fechas
     *
     * @param fechaIngreso
     * @param fechaEgreso
     * @throws java.text.ParseException
     */
    @SuppressWarnings({"IncompatibleEquals", "null"})
    public void calcularDias(Date fechaIngreso, Date fechaEgreso) throws ParseException {
        boolean necesitaDosFechas = true;
        if (fechaIngreso == null || fechaIngreso.equals("")) {
            necesitaDosFechas = false;
        }
        if (fechaEgreso == null || fechaEgreso.equals("")) {
            fechaEgreso = new Date();
        }
        if (necesitaDosFechas) {
            if (fechaEgreso.before(fechaIngreso)) {
                addError("la fecha de egreso no puede ser mayor a la de ingreso");
            }
            if (!super.isError()) {
                int milisecondsByDay = 86400000;
                int dias = (int) ((fechaEgreso.getTime() - fechaIngreso.getTime()) / milisecondsByDay);
                //long numberOFDays = DAYS.between(feIngreso, feEgreso);
                getObjetoEstancia().setDias(dias);

            }
        }
        generarMensajes();
    }

    @SuppressWarnings({"IncompatibleEquals", "null"})
    public void calcularDiasInoportunidad(Date fechaIngreso, Date fechaEgreso) throws ParseException {
        boolean necesitaDosFechas = true;
        if (fechaIngreso == null || fechaIngreso.equals("")) {
            necesitaDosFechas = false;
        }

        if (fechaEgreso == null || fechaEgreso.equals("")) {
            fechaEgreso = new Date();
        }

        if (necesitaDosFechas) {
            if (fechaEgreso.before(fechaIngreso)) {
                addError("la fecha de fin no puede ser mayor a la de inicio");
            }
            if (!super.isError()) {
                int milisecondsByDay = 86400000;
                int dias = (int) ((fechaEgreso.getTime() - fechaIngreso.getTime()) / milisecondsByDay);
                //long numberOFDays = DAYS.between(feIngreso, feEgreso);
                getObjetoInoportunidad().setDiasInoportunidad(dias);

            }
        }
        generarMensajes();
    }

    public void campoObligatorioFechaFin() throws ParseException {
        if (getObjetoInoportunidad().getMaeMotivoInoportunidadId() != null) {
            setCampoObligatorioMotivoInoportidad(true);
            setHabilitarCampoMotivoInoportidad(false);
        } else {
            setCampoObligatorioMotivoInoportidad(false);
            setHabilitarCampoMotivoInoportidad(true);
        }
    }

    public void calcularReingreso(Date fechaIngreso) {
        getAucHospitalizacionServicio().calcularRegingreso(this);
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

    public String obtenerUbicacion(int id) {
        if (id == 0) {
            return "";
        }
        String ubi = getHashUbicaciones().get(id).getNombreDepartamentoCiudad();
        return ubi;
    }
    
    public void consultarIpsReceptoras() {
        PrimeFaces.current().executeScript("PF('dialogoIpsListaResceptoras').show()");
    }
    
    public void onRowSelectIpsReceptoras(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjetoSeguimiento().setCntPrestadorSedesId(ips);
        getObjetoSeguimiento().setCntPrestadoresId(ips.getCntPrestador());
        setParamConsulta(new ParamConsulta());
        getParamConsulta(1).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsListaResceptoras').hide()");
        PrimeFaces.current().ajax().update("frmGestorasRegionales:ipsReceptoraGestores");
        PrimeFaces.current().ajax().update("frmEditarGestorasRegionales:ipsReceptoraGestores");
    }
    
    public void consultarIps() {
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
    }

    public void consultarIpsActas() {
        PrimeFaces.current().executeScript("PF('dialogoIpsListaActa').show()");
    }

    public void refrescarIps() {
        getAucHospitalizacionServicio().listarIps(this);
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjeto().setCntPrestadorSedeId(ips);
        getObjeto().setCntPrestadorId(ips.getCntPrestador());
        setParamConsulta(new ParamConsulta());
        getParamConsulta(1).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmCrear:panelPrestadorCrear");
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

    public void consultarDiagnostico(int ingresoOegreso) {
        if (getObjeto().getId() != null) {
            AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
            if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                addError("No se pude aplicar, por favor refrescar la pantalla");
            }
        }

        if (!super.isError()) {
            switch (ingresoOegreso) {
                case 0:
                    setIsIngresoOgreso(0);
                    break;
                case 1:
                    setIsIngresoOgreso(1);
                    break;
                case 2:
                    setIsIngresoOgreso(2);
                    if(getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().size() > 0){
                        addError("No puede agregar otro diagnostico");
                    }   break;
                default:
                    break;
            }
            if(!super.isError()){
                PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
                PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
            }
        }
        generarMensajes();
    }
    
    public void agregarEspecialidadDiagnostico() {
        if (getObjeto().getId() != null) {
            AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
            if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                addError("No se pude aplicar, por favor refrescar la pantalla");
            }
            if(hopitalizacion.getMaEspecialidadesId() != null){
                 addError("No pude agregar otra especialidad");
            }
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoEspecialidad').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoEspecialidad");
        }
        generarMensajes();
    }
    
    public void agregarDiagnosticoEstancia() {
        if (getObjeto().getId() != null) {
            AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
            if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                addError("No se pude aplicar, por favor refrescar la pantalla");
            }
            if(hopitalizacion.getAucHospitalizacionDiagnosticoEstanciaTratanteList().isEmpty()){
                getObjeto().setAucHospitalizacionDiagnosticoEstanciaTratanteList(new ArrayList<>());
            }
            if(!hopitalizacion.getAucHospitalizacionDiagnosticoEstanciaTratanteList().isEmpty() 
                && hopitalizacion.getAucHospitalizacionDiagnosticoEstanciaTratanteList().size() > 0){
                addError("No se pude agregar otro diagnostico");
            }
        }
        
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoEstancia').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoEstancia");
        }
        generarMensajes();
    }
    
    public void cerrarDialogoDiagnostico() {
        boolean agregar = true;
        switch (getIsIngresoOgreso()) {
            case 0:
                if (getObjeto().getAucIngresoId().getAucDiagnosticosList() == null) {
                    getObjeto().getAucEgresoId().setAucDiagnosticosList(new ArrayList<>());
                }   
                for (AucDiagnostico diag : getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                    if (diag.getMaDiagnosticoId() == getDiagnosticosBean().getObjeto().getId()) {
                        agregar = false;
                        addMensaje("El diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
                    }
                }   
                for (AucDiagnostico diagnostico : getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                    if (diagnostico.isPrincipal() && getDiagnosticosBean().getObjeto().isPrincipal()) {
                        agregar = false;
                        this.addError("No puede haber más de un diagnóstico principal");
                        break;
                    }
                }   
                break;
            case 1:
                if (getObjeto().getAucEgresoId().getAucDiagnosticosList() == null) {
                    getObjeto().getAucEgresoId().setAucDiagnosticosList(new ArrayList<>());
                }   
                for (AucDiagnostico diag : getObjeto().getAucEgresoId().getAucDiagnosticosList()) {
                    if (diag.getMaDiagnosticoId() == getDiagnosticosBean().getObjeto().getId()) {
                        agregar = false;
                        addMensaje("El diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
                    }
                }   
                for (AucDiagnostico diagnostico : getObjeto().getAucEgresoId().getAucDiagnosticosList()) {
                    if (diagnostico.isPrincipal() && getDiagnosticosBean().getObjeto().isPrincipal()) {
                        agregar = false;
                        this.addError("No puede haber más de un diagnóstico principal");
                        break;
                    }
                }  
                break;
            case 2:
                getObjeto().setAucHospitalizacionDiagnosticoEstanciaTratanteList(new ArrayList<>());
                break;
            default:
                break;
        }
        if (agregar) {
            AucDiagnostico diagnostico = new AucDiagnostico();
            diagnostico.setMaDiagnosticoId(getDiagnosticosBean().getObjeto().getId());
            diagnostico.setMaDiagnosticoCodigo(getDiagnosticosBean().getObjeto().getCodigo());
            diagnostico.setMaDiagnosticoValor(getDiagnosticosBean().getObjeto().getNombre());
            switch (getIsIngresoOgreso()) {
                case 0:
                    if (getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
                        diagnostico.setPrincipal(true);
                    }   diagnostico.setPos(getObjeto().getAucIngresoId().getAucDiagnosticosList().size());
                    getObjeto().getAucIngresoId().getAucDiagnosticosList().add(diagnostico);
                    break;
                case 1:
                    if (getObjeto().getAucEgresoId().getAucDiagnosticosList().isEmpty()) {
                        diagnostico.setPrincipal(true);
                    }   diagnostico.setPos(getObjeto().getAucEgresoId().getAucDiagnosticosList().size());
                    getObjeto().getAucEgresoId().getAucDiagnosticosList().add(diagnostico);
                    break;
                case 2:
                    if (getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().isEmpty()) {
                        diagnostico.setPrincipal(true);
                    }   
                    getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().add(diagnostico);
                    break;
                default:
                    break;
            }
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoEditar");
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticoEgresoGestion");
            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaDiagnosticosEgresoVer");
            PrimeFaces.current().ajax().update("frmDiagnosticoEstancia:tablaEstanciaEspecialidadGestion");
        }
        getDiagnosticosBean().setObjeto(new MaDiagnostico());
        generarMensajes();
    }

    public void cambiarTipoDiagnostico(int idDiagnostico) {
        getObjeto().getAucIngresoId().getAucDiagnosticosList().stream().filter(diagnostico -> (diagnostico.getMaDiagnosticoId() == idDiagnostico)).forEachOrdered(diagnostico -> {
            Maestro tipoDiagnostico = getHashTiposDiagnostico().get(diagnostico.getMaeTipoDiagnosticoId());
            diagnostico.setMaeTipoDiagnosticoCodigo(tipoDiagnostico.getValor());
            diagnostico.setMaeTipoDiagnosticoValor(tipoDiagnostico.getNombre());
            /*if (!isCrearOgestionarDiagnostico()) {
            diagnostico.setAucIngresosId(getObjeto().getAucIngresoId());
            setObjetoDiagnostico(new AucDiagnostico());
            setObjetoDiagnostico(diagnostico);
            getAucHospitalizacionServicio().validarDiagnosticosNoHayaMasPrincipales(this, getObjetoDiagnostico());
            if(!super.isError()){
            getAucHospitalizacionServicio().guardarDiagnostico(this);
            refrescarDiagnosticosIngreso();
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
            }
            }*/
        });
        generarMensajes();
    }

    public void cambiarTipoDiagnosticoEgresado(int idDiagnostico) {
        getObjeto().getAucEgresoId().getAucDiagnosticosList().stream().filter(diagnostico -> (diagnostico.getMaDiagnosticoId() == idDiagnostico)).forEachOrdered(diagnostico -> {
            Maestro tipoDiagnostico = getHashTiposDiagnostico().get(diagnostico.getMaeTipoDiagnosticoId());
            diagnostico.setMaeTipoDiagnosticoCodigo(tipoDiagnostico.getValor());
            diagnostico.setMaeTipoDiagnosticoValor(tipoDiagnostico.getNombre());
        });
    }

    public void cambiarPrincipalDiagnostico(int idDiagnostico) {
        getObjeto().getAucIngresoId().getAucDiagnosticosList().forEach(diagnostico -> {
            if (diagnostico.getMaDiagnosticoId() != idDiagnostico) {
                diagnostico.setPrincipal(false);
            } else {
                diagnostico.setPrincipal(true);
            }
        });
    }

    public void cambiarPrincipalDiagnosticoEgresado(int idDiagnostico) {
        getObjeto().getAucEgresoId().getAucDiagnosticosList().forEach(diagnostico -> {
            if (diagnostico.getMaDiagnosticoId() != idDiagnostico) {
                diagnostico.setPrincipal(false);
            } else {
                diagnostico.setPrincipal(true);
            }
        });
    }

    public void borrarDiagnosticoBD(AucDiagnostico diagnostico) {
        if (getObjeto().getAucIngresoId().getAucDiagnosticosList().size() > 1) {
            int idDiagnostico = 0;
            boolean pricipal = false;
            for (AucDiagnostico diagn : getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                if (diagn.getId() != null && Objects.equals(diagn.getId(), diagnostico.getId())) {
                    idDiagnostico = diagn.getMaDiagnosticoId();
                    if (diagn.isPrincipal()) {
                        pricipal = true;
                    }
                }
            }
            getObjeto().getAucIngresoId().getAucDiagnosticosList().remove(diagnostico);
            getListaDiagnosticosBorrar().add(diagnostico);
            if (pricipal && !getObjeto().getAucIngresoId().getAucDiagnosticosList().isEmpty()) {
                getObjeto().getAucIngresoId().getAucDiagnosticosList().get(0).setPrincipal(true);
            }
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosEditar");
        } else {
            addError("Debe existir al menos un diagnostico");
        }
        generarMensajes();
    }
    
    public void borrarDiagnosticoEstanciaBD(AucDiagnostico diagnostico) {
      
        int idDiagnostico = 0;
        boolean pricipal = false;
        for (AucDiagnostico diagn : getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList()) {
            if (diagn.getId() != null && Objects.equals(diagn.getId(), diagnostico.getId())) {
                idDiagnostico = diagn.getMaDiagnosticoId();
                if (diagn.isPrincipal()) {
                    pricipal = true;
                }
            }
        }
        getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().remove(diagnostico);
        getListaDiagnosticosEstanciaEspecialidadBorrar().add(diagnostico);
        if (pricipal && !getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().isEmpty()) {
            getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().get(0).setPrincipal(true);
        }
        
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        getAucHospitalizacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionar:tablaEstanciaEspecialidadGestion");
        //PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEspecialidadGestion");
       
        generarMensajes();
    }
    
    public void borrarDiagnostico(int id) {
        List<AucDiagnostico> nuevaLista = new ArrayList();
        boolean tiene_pricipal = false;
        if (getObjeto().getAucIngresoId().getAucDiagnosticosList().size() > 1) {
            getObjeto().getAucIngresoId().getAucDiagnosticosList().stream().filter(diagnostico -> (diagnostico.getMaDiagnosticoId() != id)).forEachOrdered(diagnostico -> {
                nuevaLista.add(diagnostico);
            });

            for (AucDiagnostico diagnostico : nuevaLista) {
                if (diagnostico.isPrincipal()) {
                    tiene_pricipal = true;
                }
            }
            if (!tiene_pricipal && nuevaLista.size() > 0) {
                nuevaLista.get(0).setPrincipal(true);
            }
            getObjeto().getAucIngresoId().setAucDiagnosticosList(nuevaLista);
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosEditar");
        } else {
            addError("Debe existir al menos un diagnostico");
            generarMensajes();
        }
    }
    
    public void borrarDiagnosticoEstanciatico(int id) {
        List<AucDiagnostico> nuevaLista = new ArrayList();
        boolean tiene_pricipal = false;
       
            getObjeto().getAucHospitalizacionDiagnosticoEstanciaTratanteList().stream().filter(diagnostico -> (diagnostico.getMaDiagnosticoId() != id)).forEachOrdered(diagnostico -> {
                nuevaLista.add(diagnostico);
            });

            for (AucDiagnostico diagnostico : nuevaLista) {
                if (diagnostico.isPrincipal()) {
                    tiene_pricipal = true;
                }
            }
            if (!tiene_pricipal && nuevaLista.size() > 0) {
                nuevaLista.get(0).setPrincipal(true);
            }
            getObjeto().setAucHospitalizacionDiagnosticoEstanciaTratanteList(nuevaLista);
            PrimeFaces.current().ajax().update("frmDiagnosticoEstancia:tablaEstanciaEspecialidadGestion");
            //PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosEditar");
    }
    
    public void borrarDiagnosticoEgresoBD(AucDiagnostico diagnostico) {
        if (getObjeto().getAucEgresoId().getAucDiagnosticosList().size() > 1) {
            int idDiagnostico = 0;
            boolean pricipal = false;
            for (AucDiagnostico diagn : getObjeto().getAucEgresoId().getAucDiagnosticosList()) {
                if (diagn.getId() != null && Objects.equals(diagn.getId(), diagnostico.getId())) {
                    idDiagnostico = diagn.getMaDiagnosticoId();
                    if (diagn.isPrincipal()) {
                        pricipal = true;
                    }
                }
            }
            getObjeto().getAucEgresoId().getAucDiagnosticosList().remove(diagnostico);
            getListaDiagnosticosEgresoBorrar().add(diagnostico);
            if (pricipal && !getObjeto().getAucEgresoId().getAucDiagnosticosList().isEmpty()) {
                getObjeto().getAucEgresoId().getAucDiagnosticosList().get(0).setPrincipal(true);
            }
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticoEgresoGestion");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticoEgresoEditar");
        } else {
            addError("Debe existir al menos un diagnostico");
        }
        generarMensajes();
    }

    public void borrarDiagnosticoEgreso(int id) {
        List<AucDiagnostico> nuevaLista = new ArrayList();
        boolean tiene_pricipal = false;
        if (getObjeto().getAucEgresoId().getAucDiagnosticosList().size() > 1) {
            getObjeto().getAucEgresoId().getAucDiagnosticosList().stream().filter(diagnostico -> (diagnostico.getMaDiagnosticoId() != id)).forEachOrdered(diagnostico -> {
                nuevaLista.add(diagnostico);
            });

            for (AucDiagnostico diagnostico : nuevaLista) {
                if (diagnostico.isPrincipal()) {
                    tiene_pricipal = true;
                }
            }
            if (!tiene_pricipal && nuevaLista.size() > 0) {
                nuevaLista.get(0).setPrincipal(true);
            }
            getObjeto().getAucEgresoId().setAucDiagnosticosList(nuevaLista);
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticoEgresoGestion");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticoEgresoEditar");
            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaDiagnosticosEgresoVer");
        } else {
            addError("Debe existir al menos un diagnostico");
            generarMensajes();
        }
    }

    public void borrarDiagnosticoMemoria(int pos) {
        try {
            //Retirar el servicio de la lista
            List<AucDiagnostico> lista = new ArrayList();
            int i = 0, j = 0;
            for (AucDiagnostico det : getObjeto().getAucIngresoId().getAucDiagnosticosList()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            getObjeto().getAucIngresoId().setAucDiagnosticosList(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Seguimiento");
        }

        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoEditar");
        }
    }

    public void borrarDiagnosticoEgresoMemoria(int pos) {
        try {
            //Retirar el servicio de la lista
            List<AucDiagnostico> lista = new ArrayList();
            int i = 0, j = 0;
            for (AucDiagnostico det : getObjeto().getAucEgresoId().getAucDiagnosticosList()) {
                if (j != pos) {
                    det.setPos(i);
                    lista.add(det);
                    i++;
                }
                j++;
            }
            getObjeto().getAucEgresoId().setAucDiagnosticosList(lista);
        } catch (Exception e) {
            super.addError("No es posible borrar Seguimiento");
        }

        if (!super.isError()) {
            //PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticoEgresoGestion");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoEditar");
        }
    }

    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public void crearSeguimiento() {
        setObjetoSeguimiento(new AucHospitalizacionSeguimiento());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmSeguimiento");
            PrimeFaces.current().executeScript("PF('dialogoSeguimiento').show()");
            crearLog("Crear Seguimiento", getObjetoSeguimiento().toString());
        }
        generarMensajes();
    }

    public void guardarSeguimiento() {
        try {
            AucHospitalizacionSeguimiento obj = getObjetoSeguimiento();
            obj.setAucHospitalizacionId(getObjeto());
            Maestro tipoSeguimiento = getHashTipoSeguimiento().get(obj.getMaeTipoSeguimientoId());
            if(tipoSeguimiento != null){
                obj.setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
                obj.setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
                obj.setMaeTipoSeguimientoTipo(tipoSeguimiento.getTipo());
            }
            
            //2023-09-05 jyperez configuración valor por defecto
            obj.setOrigen(AucHospitalizacionSeguimiento.ORIGEN_MANUAL);
            obj.setBorrado(Boolean.FALSE);
            //Agregar dato a la lista
            if (getObjeto().getAucHospitalizacionSeguimientoList() == null) {
                getObjeto().setAucHospitalizacionSeguimientoList(new ArrayList<>());
            }
            getAucHospitalizacionServicio().guardarSeguimientos(this);
            obj.setPos(getObjeto().getAucHospitalizacionSeguimientoList().size());
            getObjeto().getAucHospitalizacionSeguimientoList().add(obj);
            if (!super.isError()) {
                refrescarSeguimientos();
                //PrimeFaces.current().ajax().update("frmEditar:tablaSeguimientosEditar");
                PrimeFaces.current().ajax().update("frmGestionar");
                PrimeFaces.current().ajax().update("frmGestionar:tablaSeguimientosGestionar");
                PrimeFaces.current().executeScript("PF('dialogoSeguimiento').hide()");
                PrimeFaces.current().resetInputs("frmHospitalizaciones");
                PrimeFaces.current().ajax().update("frmHospitalizaciones");
                crearLog("Guardar Seguimiento", getObjetoSeguimiento().toString());
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }
    
    public void guardarGestorasRegionales() {
        try {
            AucHospitalizacionSeguimiento obj = getObjetoSeguimiento();
            Maestro maestro = getAucHospitalizacionServicio().consultarMaestro(obj.getMaeTipoGestionId(), this);
            if(maestro != null){
                if(maestro.contieneAccion(MaestroAccion.AUC_SEGUIMIENTO_GESTORES_IPS_RECEPTORA)){
                    if(obj.getCntPrestadoresId() == null){
                        addError("El campo Ips Receptora es obligatorio");
                    }
                }
            }
            obj.setAucHospitalizacionId(getObjeto());
            Maestro tipoGestion = getHashSeguimientoGestores().get(obj.getMaeTipoGestionId());
            if(tipoGestion != null){
                obj.setMaeTipoGestionCodigo(tipoGestion.getValor());
                obj.setMaeTipoGestionValor(tipoGestion.getNombre());
                obj.setMaeTipoGestionTipo(tipoGestion.getTipo());
            }
            Maestro tipoGestionEstado = getHashSeguimientoEstado().get(obj.getMaeTipoGestionEstadoId());
            if(tipoGestion != null){
                obj.setMaeTipoGestionEstadoCodigo(tipoGestionEstado.getValor());
                obj.setMaeTipoGestionEstadoValor(tipoGestionEstado.getNombre());
                obj.setMaeTipoGestionEstadoTipo(tipoGestionEstado.getTipo());
            }
            Maestro destinoGR = getHashDestinoGR().get(obj.getMaeDestinoId());
            if(destinoGR != null){
                obj.setMaeDestinoCodigo(destinoGR.getValor());
                obj.setMaeDestinoValor(destinoGR.getNombre());
                obj.setMaeDestinoTipo(destinoGR.getTipo());
            }
            //2023-09-05 jyperez configuración valor por defecto
            obj.setOrigen(AucHospitalizacionSeguimiento.ORIGEN_MANUAL);
            obj.setBorrado(Boolean.FALSE);
            //Agregar dato a la lista
            if (getObjeto().getAucHospitalizacionGestorasRegionalesList() == null) {
                getObjeto().setAucHospitalizacionGestorasRegionalesList(new ArrayList<>());
            }
            
            if (!super.isError()) {
                getAucHospitalizacionServicio().guardarGestionRegionales(this);
                obj.setPos(getObjeto().getAucHospitalizacionGestorasRegionalesList().size());
                getObjeto().getAucHospitalizacionGestorasRegionalesList().add(obj);
                refrescarGestorasRegionales();
                //PrimeFaces.current().ajax().update("frmEditar:tablaSeguimientosEditar");
                PrimeFaces.current().ajax().update("frmVerGestorasRegionales");
                PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaGestorasRegionalesGestionar");
                PrimeFaces.current().executeScript("PF('dialogoGestorasRegionales').hide()");
                //PrimeFaces.current().resetInputs("frmHospitalizaciones");
                //PrimeFaces.current().ajax().update("frmHospitalizaciones");
                crearLog("Guardar Gestoras Regionales", getObjetoSeguimiento().toString());
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }
    
    public void guardarDiagnosticosEstancia() {
        try {
            getAucHospitalizacionServicio().guardarDiagnosticoEstancia(this);
            if (!super.isError()) {
                refrescarDiagnosticoEstancia();
                PrimeFaces.current().ajax().update("frmGestionar:tablaEstanciaEspecialidadGestion");
                PrimeFaces.current().executeScript("PF('dialogoDiagnosticoEstancia').hide()");
                crearLog("Guardar Diagnostico Estancia", getObjeto().toString());
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar diagnostico estancia");
        }
        generarMensajes();
    }
    
    public void crearGestionGestoras() {
        setObjetoSeguimiento(new AucHospitalizacionSeguimiento());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        getObjetoSeguimiento().setHabilitarCampoIpsReceptora(true);
        getObjetoSeguimiento().setHabilitarCampoFechaCierreGestion(true);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestorasRegionales");
            PrimeFaces.current().executeScript("PF('dialogoGestorasRegionales').show()");
            crearLog("Crear Gestoras Regionales", getObjetoSeguimiento().toString());
        }
        generarMensajes();
    }
    
    public void guardarSeguimientoAnulacion() {
        try {
            AucHospitalizacionSeguimiento obj = getObjetoSeguimiento();
            obj.setAucHospitalizacionId(getObjeto());
            obj.setBorrado(Boolean.FALSE);
            Maestro tipoSeguimiento = getHashTipoSeguimiento().get(obj.getMaeTipoSeguimientoId());
            if(tipoSeguimiento != null){
                obj.setMaeTipoSeguimientoCodigo(tipoSeguimiento.getValor());
                obj.setMaeTipoSeguimientoValor(tipoSeguimiento.getNombre());
                obj.setMaeTipoSeguimientoTipo(tipoSeguimiento.getTipo());
            }
            super.setAccion(Url.ACCION_ADICIONAL_4);
            super.setDoAccion(Url.ACCION_GUARDAR);
            getAucHospitalizacionServicio().Accion(this);
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        procesoFinal();
    }

    public void crearEstancia() {
        setObjetoEstancia(new AucHospitalizacionEstancia());
        if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            Date fechaEgreso = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso();
            if (fechaEgreso == null) {
                addError("No puede agregar una Estancia nueva sin haber cerrado el anterior");
            } else {
                getObjetoEstancia().setFechaIngreso(fechaEgreso);
            }
        } else {
            getObjetoEstancia().setFechaIngreso(getObjeto().getAucIngresoId().getFechaIngreso());
        }
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEstancia");
            PrimeFaces.current().executeScript("PF('dialogoEstancia').show()");
            crearLog("Crear Estancia", getObjetoEstancia().toString());
        }
        generarMensajes();
    }
    
    public void crearEstanciaGestoras() {
        setObjetoEstancia(new AucHospitalizacionEstancia());
        if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            Date fechaEgreso = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso();
            if (fechaEgreso == null) {
                addError("No puede agregar una Estancia nueva sin haber cerrado el anterior");
            } else {
                getObjetoEstancia().setFechaIngreso(fechaEgreso);
            }
        } else {
            getObjetoEstancia().setFechaIngreso(getObjeto().getAucIngresoId().getFechaIngreso());
        }
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEstanciaGestoras");
            PrimeFaces.current().executeScript("PF('dialogoEstanciaGestoras').show()");
            crearLog("Crear Estancia", getObjetoEstancia().toString());
        }
        generarMensajes();
    }
    
    public void crearJustificacionEstanciaProlongada() {
        setObjetoJustificacionEstanciasProlongada(new AucJustificacionEstanciasProlongada());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEstanciaDescripcion");
            PrimeFaces.current().executeScript("PF('dialogoEstanciaDescripcion').show()");
            crearLog("Crear Justificación Estancia Prolongada", getObjetoJustificacionEstanciasProlongada().toString());
        }
        generarMensajes();
    }

    public void crearEstanciaDescripcion(int id) {
        setObjetoEstancia(new AucHospitalizacionEstancia());
        getObjetoEstancia().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();

    }

    @SuppressWarnings("IncompatibleEquals")
    public void guardarEstancia() {
        try {

            AucHospitalizacionEstancia obj = getObjetoEstancia();
            calcularDias(obj.getFechaIngreso(), obj.getFechaEgreso());
            if (obj.getFechaEgreso() != null && !obj.getFechaEgreso().equals("")) {
                if (obj.getFechaEgreso().before(obj.getFechaIngreso())) {
                    addError("la fecha de egreso no puede ser mayor a la de ingreso");
                }
            }
            if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                AucHospitalizacionEstancia estancia = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1);
                if (estancia.getMaeServicioId() == obj.getMaeServicioId()) {
                    addError("El servicio no puede ser el anterior");
                }
            }

            if (!super.isError()) {
                obj.setAucHospitalizacionId(getObjeto());
                Maestro servicio = getHashServicio().get(obj.getMaeServicioId());
                obj.setMaeServicioCodigo(servicio.getValor());
                obj.setMaeServicioValor(servicio.getNombre());
                //Agregar dato a la lista
                if (getObjeto().getAucHospitalizacionEstanciaList() == null) {
                    getObjeto().setAucHospitalizacionEstanciaList(new ArrayList<>());
                }
                getAucHospitalizacionServicio().guardarEstancias(this);
                obj.setPos(getObjeto().getAucHospitalizacionEstanciaList().size());
                getObjeto().getAucHospitalizacionEstanciaList().add(obj);
                if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                    if (getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                            && !getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) {
                        getObjeto().getAucEgresoId().setFechaEgreso(getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso());
                    } else {
                        getObjeto().getAucEgresoId().setFechaEgreso(null);
                    }
                }
                if (!super.isError()) {
                    //PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEditar");
                    refrescarEstancias();
                    PrimeFaces.current().ajax().update("frmGestionar");
                    PrimeFaces.current().ajax().update("frmGestionar:tablaEstanciaGestionar");
                    PrimeFaces.current().ajax().update("frmGestionar:fechaEgresoGestionar");
                    PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEditar");
                    PrimeFaces.current().executeScript("PF('dialogoEstancia').hide()");
                    PrimeFaces.current().executeScript("PF('dialogoNoCerrarHospitalizacion').hide()");
                    crearLog("Guardar Estancia", getObjetoEstancia().toString());
                }

            }
        } catch (ParseException e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }
    
    @SuppressWarnings("IncompatibleEquals")
    public void guardarEstanciaGestoras() {
        try {

            AucHospitalizacionEstancia obj = getObjetoEstancia();
            calcularDias(obj.getFechaIngreso(), obj.getFechaEgreso());
            if (obj.getFechaEgreso() != null && !obj.getFechaEgreso().equals("")) {
                if (obj.getFechaEgreso().before(obj.getFechaIngreso())) {
                    addError("la fecha de egreso no puede ser mayor a la de ingreso");
                }
            }
            if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                AucHospitalizacionEstancia estancia = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1);
                if (estancia.getMaeServicioId() == obj.getMaeServicioId()) {
                    addError("El servicio no puede ser el anterior");
                }
            }

            if (!super.isError()) {
                obj.setAucHospitalizacionId(getObjeto());
                Maestro servicio = getHashServicio().get(obj.getMaeServicioId());
                obj.setMaeServicioCodigo(servicio.getValor());
                obj.setMaeServicioValor(servicio.getNombre());
                //Agregar dato a la lista
                if (getObjeto().getAucHospitalizacionEstanciaList() == null) {
                    getObjeto().setAucHospitalizacionEstanciaList(new ArrayList<>());
                }
                
                super.setAccion(Url.ACCION_ADICIONAL_10);
                super.setDoAccion(Url.ACCION_ADICIONAL_4);
                getAucHospitalizacionServicio().Accion(this);
                if (!super.isError()) {
                    //getAucHospitalizacionServicio().guardarEstancias(this);
                    obj.setPos(getObjeto().getAucHospitalizacionEstanciaList().size());
                    getObjeto().getAucHospitalizacionEstanciaList().add(obj);
                    if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
                        if (getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso() != null
                                && !getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso().equals("")) {
                            getObjeto().getAucEgresoId().setFechaEgreso(getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1).getFechaEgreso());
                        } else {
                            getObjeto().getAucEgresoId().setFechaEgreso(null);
                        }
                    }
                }
            }
        } catch (ParseException e) {
            super.addError("No es posible adicionar seguimiento");
        }
        procesoFinal();
    }
    
    public void guardarEspecialidadDiagnostico() {
        try {
            if(getObjeto().getMaEspecialidadesId() == null){
                addError("El campo especialidad es obligatorio.");
            }
           
            if (!super.isError()) {
                getAucHospitalizacionServicio().guardarDiagnosticoEspecialidad(this);
                PrimeFaces.current().ajax().update("frmGestionar:panelEspecialidadGestionar");
                PrimeFaces.current().executeScript("PF('dialogoDiagnosticoEspecialidad').hide()");
                crearLog("Guardar Diagnostico Estancia", getObjeto().toString());
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }
    
    public void guardarEstanciaDescripcion() {
        try {
            AucJustificacionEstanciasProlongada obj = getObjetoJustificacionEstanciasProlongada();
            obj.setAucHospitalizacionId(getObjeto());
            Maestro causa = getHashCausaEstancia().get(obj.getMaeCausaEstanciaProlongadaId());
            if (causa != null) {
                obj.setMaeCausaEstanciaProlongadaCodigo(causa.getValor());
                obj.setMaeCausaEstanciaProlongadaValor(causa.getNombre());
            }
            Maestro propuesta = getHashPropuestaIntervencion().get(obj.getMaePropuestaIntervensionId());
            if (propuesta != null) {
                obj.setMaePropuestaIntervensionCodigo(propuesta.getValor());
                obj.setMaePropuestaIntervensionValor(propuesta.getNombre());
            }

            //Agregar dato a la lista
            if (getObjeto().getAucHospitalizacionJustificacionEstanciasProlongadaList() == null) {
                getObjeto().setAucHospitalizacionJustificacionEstanciasProlongadaList(new ArrayList<>());
            }
            getAucHospitalizacionServicio().guardarJustificacionEstanciaProlongada(this);
            obj.setPos(getObjeto().getAucHospitalizacionJustificacionEstanciasProlongadaList().size());
            getObjeto().getAucHospitalizacionJustificacionEstanciasProlongadaList().add(obj);
            if (!super.isError()) {
                refrescarJustifiacionEstanciasProlongada();
                PrimeFaces.current().ajax().update("frmGestionar:tablaJustificacionEstanciaProlongadaGestionar");
                PrimeFaces.current().executeScript("PF('dialogoEstanciaDescripcion').hide()");
                crearLog("Guardar Justificación Estancia Prolongada", getObjetoJustificacionEstanciasProlongada().toString());
            }

        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void crearPatologia() {
        setObjetoPatologia(new AucHospitalizacionPatologia());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmPatologia");
            PrimeFaces.current().executeScript("PF('dialogoPatologia').show()");
            crearLog("Crear Patología", getObjetoPatologia().toString());
        }
        generarMensajes();
    }

    public void guardarPatologia() {
        try {
            AucHospitalizacionPatologia obj = getObjetoPatologia();
            obj.setAucHospitalizacionId(getObjeto());
            Maestro patologia = getHashPatologia().get(obj.getMaePatologiaId());
            obj.setMaePatologiaCodigo(patologia.getValor());
            obj.setMaePatologiaValor(patologia.getNombre());
            //Agregar dato a la lista
            if (getObjeto().getAucHospitalizacionPatologiaList() == null) {
                getObjeto().setAucHospitalizacionPatologiaList(new ArrayList<>());
            }
            getAucHospitalizacionServicio().guardarPatologias(this);
            obj.setPos(getObjeto().getAucHospitalizacionPatologiaList().size());
            getObjeto().getAucHospitalizacionPatologiaList().add(obj);
            if (!super.isError()) {
                //PrimeFaces.current().ajax().update("frmEditar:tablaPatologiaEditar");
                PrimeFaces.current().ajax().update("frmGestionar");
                PrimeFaces.current().ajax().update("frmGestionar:tablaPatologiaGestionar");
                PrimeFaces.current().executeScript("PF('dialogoPatologia').hide()");
                crearLog("Guardar Patología", getObjetoPatologia().toString());
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void crearInoportunidad() {
        setObjetoInoportunidad(new AucHospitalizacionInoportunidad());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            setCampoObligatorioMotivoInoportidad(false);
            setHabilitarCampoMotivoInoportidad(true);
            PrimeFaces.current().ajax().update("frmInoportunidad");
            PrimeFaces.current().executeScript("PF('dialogoInoportunidad').show()");
            crearLog("Crear Inoportunidad", getObjetoInoportunidad().toString());
        }
        generarMensajes();
    }

    @SuppressWarnings("IncompatibleEquals")
    public void guardarInoportunidad() {
        try {

            AucHospitalizacionInoportunidad obj = getObjetoInoportunidad();
            calcularDiasInoportunidad(obj.getFechaInicioInoportunidad(), obj.getFechaFinInoportunidad());
            if (obj.getFechaFinInoportunidad() != null && !obj.getFechaFinInoportunidad().equals("")) {
                if (obj.getMaeMotivoInoportunidadId() == null) {
                    addError("Motivo Fin: Este campo es obligatorio");
                }
                if (obj.getFechaFinInoportunidad().before(obj.getFechaInicioInoportunidad())) {
                    addError("la fecha de fin no puede ser mayor a la de inicio");
                }
            }
            getAucHospitalizacionServicio().consultarHospitalizacionInoportunidad(this);
            if (!super.isError()) {
                obj.setAucHospitalizacionId(getObjeto());
                Maestro tipo = getHashTipoInoportunidad().get(obj.getMaeTipoInoportunidadId());
                if (tipo != null) {
                    obj.setMaeTipoInoportunidadCodigo(tipo.getValor());
                    obj.setMaeTipoInoportunidadValor(tipo.getNombre());
                }

                Maestro motivo = getHashMotivoInoportunidad().get(obj.getMaeMotivoInoportunidadId());
                if (motivo != null) {
                    obj.setMaeMotivoInoportunidadCodigo(motivo.getValor());
                    obj.setMaeMotivoInoportunidadValor(motivo.getNombre());
                }
                //Agregar dato a la lista
                if (getObjeto().getAucHospitalizacionInoportunidadList() == null) {
                    getObjeto().setAucHospitalizacionInoportunidadList(new ArrayList<>());
                }
                getAucHospitalizacionServicio().guardarInoportunidad(this);
                obj.setPos(getObjeto().getAucHospitalizacionInoportunidadList().size());
                getObjeto().getAucHospitalizacionInoportunidadList().add(obj);
                if (!super.isError()) {
                    refrescarInoportunidades();
                    //PrimeFaces.current().ajax().update("frmEditar:tablaInoportunidadEditar");
                    PrimeFaces.current().ajax().update("frmGestionar");
                    PrimeFaces.current().ajax().update("frmGestionar:tablaInoportunidadGestionar");
                    PrimeFaces.current().executeScript("PF('dialogoInoportunidad').hide()");
                    crearLog("Guardar Inoportunidad", getObjetoInoportunidad().toString());
                }
            }
        } catch (ParseException e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void crearAdverso() {
        setObjetoAdverso(new AucHospitalizacionAdverso());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAdverso");
            PrimeFaces.current().executeScript("PF('dialogoAdverso').show()");
            crearLog("Crear Adverso", getObjetoAdverso().toString());
        }
        generarMensajes();
    }

    @SuppressWarnings("IncompatibleEquals")
    public void guardarAdverso() {
        try {
            AucHospitalizacionAdverso obj = getObjetoAdverso();
            //getAucHospitalizacionServicio().consultarHospitalizacionAdverso(this);
            if (obj.getFechaSolicitudAnalisis() != null && !obj.getFechaSolicitudAnalisis().equals("")) {
                if (obj.getFechaSolicitudAnalisis().before(obj.getFechaEvento())) {
                    addError("la fecha de solicitud de análisis a la IPS no puede ser mayor a la fecha evento");
                }
            }

            SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
            String formatFechaEgreso = formatterFechaEgreso.format(obj.getFechaEvento());
            LocalDate fechaEgreso = LocalDate.parse(formatFechaEgreso);
            if (fechaEgreso != null) {
                String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
                LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
                if (fechaIngresoHospitalizacion.isAfter(fechaEgreso)) {
                    addError("La fecha de evento es menor a las fechas de hospitalizacion de ingreso");
                }
            }
            String formatFechaSolicitud = formatterFechaEgreso.format(obj.getFechaSolicitudAnalisis());
            LocalDate fechaSolicitud = LocalDate.parse(formatFechaSolicitud);
            if (fechaSolicitud != null) {
                String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
                LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
                if (fechaIngresoHospitalizacion.isAfter(fechaSolicitud)) {
                    addError("La fecha de solicitud es menor a las fechas de hospitalizacion de ingreso");
                }
            }
            if (obj.getFechaAnalisis() != null && !obj.getFechaAnalisis().equals("")) {
                String formatFechaAnalisis = formatterFechaEgreso.format(obj.getFechaAnalisis());
                LocalDate fechaAnalisis = LocalDate.parse(formatFechaAnalisis);
                if (fechaAnalisis != null && !fechaAnalisis.equals("")) {
                    String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
                    LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
                    if (fechaIngresoHospitalizacion.isAfter(fechaAnalisis)) {
                        addError("La fecha de analisis es menor a las fechas de hospitalizacion de ingreso");
                    }
                }
            }

            if (!super.isError()) {
                obj.setAucHospitalizacionId(getObjeto());
                Maestro categoria = getHashCategoriaEvento().get(obj.getMaeCategoriaEventoId());
                if (categoria != null) {
                    obj.setMaeCategoriaEventoCodigo(categoria.getValor());
                    obj.setMaeCategoriaEventoValor(categoria.getNombre());
                }
                Maestro subcategoria = getHashSubcategoriaEvento().get(obj.getMaeSubcategoriaEventoId());
                if (subcategoria != null) {
                    obj.setMaeSubcategoriaEventoCodigo(subcategoria.getValor());
                    obj.setMaeSubcategoriaEventoValor(subcategoria.getNombre());
                }
                Maestro conclusion = getHashConclusionEveno().get(obj.getMaeConclusionEventoId());
                if (conclusion != null) {
                    obj.setMaeConclusionEventoCodigo(conclusion.getValor());
                    obj.setMaeConclusionEventoValor(conclusion.getNombre());
                }
                //Agregar dato a la lista
                getAucHospitalizacionServicio().guardarAdversos(this);
                if (!super.isError()) {
                    obj.setPos(getObjeto().getAucHosptalizacionAdversoList().size());
                    getObjeto().getAucHosptalizacionAdversoList().add(obj);
                    //PrimeFaces.current().ajax().update("frmEditar:tablaAdversoEditar");
                    PrimeFaces.current().ajax().update("frmGestionar");
                    PrimeFaces.current().ajax().update("frmGestionar:tablaAdversoGestionar");
                    PrimeFaces.current().executeScript("PF('dialogoAdverso').hide()");
                    crearLog("Guardar Adverso", getObjetoAdverso().toString());
                }
            }

        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void crearObjecion() {
        setObjetoObjecion(new AucHospitalizacionObjecion());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            getObjetoObjecion().setCantidadSolicitada(1);
            setIsObjecion(true);
            PrimeFaces.current().ajax().update("frmObjecion");
            PrimeFaces.current().executeScript("PF('dialogoObjecion').show()");
            crearLog("Crear Objecion", getObjetoObjecion().toString());
        }
        generarMensajes();
    }

    public void guardarObjecion() {
        try {
            AucHospitalizacionObjecion obj = getObjetoObjecion();
            if (obj.getCantidadSolicitada() == 0) {
                super.addError("La cantidad debe ser mayor a 0");
            }
            //Agregar dato a la lista
            if (getObjeto().getAucHospitalizacionObjecionList() == null) {
                getObjeto().setAucHospitalizacionObjecionList(new ArrayList<>());
            }

            if (obj.getMaTecnologiaId() == 0) {
                addError("La tecnología es obligatoria");
            }

            if (!super.isError()) {
                getAucHospitalizacionServicio().guardarObjeciones(this);
                obj.setPos(getObjeto().getAucHospitalizacionObjecionList().size());
                getObjeto().getAucHospitalizacionObjecionList().add(obj);
                //PrimeFaces.current().ajax().update("frmEditar:tablaObjecionEditar");
                PrimeFaces.current().ajax().update("frmGestionar");
                PrimeFaces.current().ajax().update("frmGestionar:tablaObjecionGestionar");
                PrimeFaces.current().executeScript("PF('dialogoObjecion').hide()");
                crearLog("Guardar Objecion", getObjetoObjecion().toString());
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar seguimiento");
        }
        generarMensajes();
    }

    public void crearServicio() {
        setObjetoServicio(new AucHospitalizacionServicio());
        setListaMemoriaTecnologia(new ArrayList<>());
        AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
        if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
            addError("No se pude aplicar, por favor refrescar la pantalla");
        }
        if (!super.isError()) {
            setIsObjecion(false);
            PrimeFaces.current().ajax().update("frmServicio");
            PrimeFaces.current().executeScript("PF('dialogoServicio').show()");
            crearLog("Crear Servicio", getObjetoServicio().toString());
        }
        generarMensajes();
    }

    public void crearSegumientoAnulacion(int id) {
        getObjeto().setId(id);
        setObjetoSeguimiento(new AucHospitalizacionSeguimiento());
        setCrearOgestionarDiagnostico(false);
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_ADICIONAL_4);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void crearGestorasRegionales(int id) {
        getObjeto().setId(id);
         setListaDiagnosticosBorrar(new ArrayList<>());
        setListaDiagnosticosEgresoBorrar(new ArrayList<>());
        setListaDiagnosticosEstanciaEspecialidadBorrar(new ArrayList<>());
        setCrearOgestionarDiagnostico(false);
        getAucHospitalizacionServicio().consultarMaestroDestino(this);
        super.setAccion(Url.ACCION_ADICIONAL_10);
        super.setDoAccion(Url.ACCION_ADICIONAL_10);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }
    
    public void revertirHospitalizacion(int id) {
        getObjeto().setId(id);
        setObjetoSeguimiento(new AucHospitalizacionSeguimiento());
        super.setAccion(Url.ACCION_ADICIONAL_7);
        super.setDoAccion(Url.ACCION_ADICIONAL_7);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void revertirEstadoHospitalizacion() {
        super.setAccion(Url.ACCION_ADICIONAL_7);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAucHospitalizacionServicio().Accion(this);
        procesoFinal();
    }

    public void guardarServicio() {
        try {
            AucHospitalizacionServicio obj = getObjetoServicio();
            SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("yyyy-MM-dd");
            if (obj.getFechaPrestacion() != null) {
                String formatFechaEgreso = formatterFechaEgreso.format(obj.getFechaPrestacion());
                LocalDate fechaEgreso = LocalDate.parse(formatFechaEgreso);
                if (fechaEgreso != null) {
                    String formatIngresoHospitalizacion = formatterFechaEgreso.format(getObjeto().getAucIngresoId().getFechaIngreso());
                    LocalDate fechaIngresoHospitalizacion = LocalDate.parse(formatIngresoHospitalizacion);
                    if (fechaIngresoHospitalizacion.isAfter(fechaEgreso)) {
                        addError("La fecha de presentacion es menor a las fechas de hospitalizacion de ingreso");
                    }
                }
            }

            if (getListaMemoriaTecnologia().isEmpty()) {
                addError("La tecnología es obligatoria");
            }

            //Agregar dato a la lista
            if (getObjeto().getAucHospitalizacionServicioList() == null) {
                getObjeto().setAucHospitalizacionServicioList(new ArrayList<>());
            }
            getAucHospitalizacionServicio().consultarHospitalizacionServicio(this);
            if (!super.isError()) {
                getAucHospitalizacionServicio().guardarServicios(this);
                getListaMemoriaTecnologia().stream().map(memoria -> {
                    memoria.setPos(getObjeto().getAucHospitalizacionServicioList().size());
                    return memoria;
                }).forEachOrdered(memoria -> {
                    getObjeto().getAucHospitalizacionServicioList().add(memoria);
                });

                //PrimeFaces.current().ajax().update("frmEditar:tablaServicioEditar");
                PrimeFaces.current().ajax().update("frmGestionar");
                PrimeFaces.current().ajax().update("frmGestionar:tablaServicioGestionar");
                PrimeFaces.current().executeScript("PF('dialogoServicio').hide()");
                crearLog("Guardar Servicio", getObjetoServicio().toString());
            }
        } catch (Exception e) {
            super.addError("No es posible adicionar servicio");
        }
        generarMensajes();

    }

    public void consultarTecnologia() {
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
        PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
    }

    public void cerrarDialogoTecnologia() {
        MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
        if (isIsObjecion()) {
            getObjetoObjecion().setTipoTecnologia(AuConstantes.ID_TECNOLOGIA);
            getObjetoObjecion().setMaTecnologiaId(tecnologia.getId());
            getObjetoObjecion().setMaTecnologiaCodigo(tecnologia.getCups());
            getObjetoObjecion().setMaTecnologiaValor(tecnologia.getCupsDescipcion());
            getObjetoObjecion().setAucHospitalizacionId(getObjeto());
            PrimeFaces.current().ajax().update("frmObjecion:panelObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecion:pEditarObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecionGestion:pEditarObjecionGestion");
        } else {
            setObjetoServicio(new AucHospitalizacionServicio());
            getObjetoServicio().setTipoTecnologia(AuConstantes.ID_TECNOLOGIA);
            getObjetoServicio().setMaTecnologiaId(tecnologia.getId());
            getObjetoServicio().setMaTecnologiaCodigo(tecnologia.getCups());
            getObjetoServicio().setMaTecnologiaValor(tecnologia.getCupsDescipcion());
            getObjetoServicio().setAucHospitalizacionId(getObjeto());
            getObjetoServicio().setPos(getListaMemoriaTecnologia().size());
            getListaMemoriaTecnologia().add(getObjetoServicio());
            PrimeFaces.current().ajax().update("frmServicio:tablaServicios");
            PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
        }
        PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
    }

    public void consultarMedicamento() {
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
    }

    public void cerrarDialogoMedicamento() {
        MaMedicamento medicamento = getMedicamentosBean().getObjeto();
        if (isIsObjecion()) {
            getObjetoObjecion().setTipoTecnologia(AuConstantes.ID_MEDICAMENTO);
            getObjetoObjecion().setMaTecnologiaId(medicamento.getId());
            getObjetoObjecion().setMaTecnologiaCodigo(medicamento.getCum());
            getObjetoObjecion().setMaTecnologiaValor(medicamento.getDescripcionInvima());
            getObjetoObjecion().setAucHospitalizacionId(getObjeto());
            PrimeFaces.current().ajax().update("frmObjecion:panelObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecion:pEditarObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecionGestion:pEditarObjecionGestion");
        } else {
            getObjetoServicio().setTipoTecnologia(AuConstantes.ID_MEDICAMENTO);
            getObjetoServicio().setMaTecnologiaId(medicamento.getId());
            getObjetoServicio().setMaTecnologiaCodigo(medicamento.getCum());
            getObjetoServicio().setMaTecnologiaValor(medicamento.getDescripcionInvima());
            getObjetoServicio().setAucHospitalizacionId(getObjeto());
            PrimeFaces.current().ajax().update("frmServicio:tipoTecnologia");
            PrimeFaces.current().ajax().update("frmServicio:codigoTecnologia");
            PrimeFaces.current().ajax().update("frmServicio:tecnologia");
            PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
        }
        PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
    }

    public void consultarInsumo() {
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");
    }

    public void cerrarDialogoInsumo() {
        MaInsumo insumo = getInsumosBean().getObjeto();
        if (isIsObjecion()) {
            getObjetoObjecion().setTipoTecnologia(AuConstantes.ID_INSUMO);
            getObjetoObjecion().setMaTecnologiaId(insumo.getId());
            getObjetoObjecion().setMaTecnologiaCodigo(insumo.getCodigo());
            getObjetoObjecion().setMaTecnologiaValor(insumo.getDescripcion());
            getObjetoObjecion().setAucHospitalizacionId(getObjeto());
            PrimeFaces.current().ajax().update("frmObjecion:panelObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecion:pEditarObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecionGestion:pEditarObjecionGestion");
        } else {
            getObjetoServicio().setTipoTecnologia(AuConstantes.ID_INSUMO);
            getObjetoServicio().setMaTecnologiaId(insumo.getId());
            getObjetoServicio().setMaTecnologiaCodigo(insumo.getCodigo());
            getObjetoServicio().setMaTecnologiaValor(insumo.getDescripcion());
            getObjetoServicio().setAucHospitalizacionId(getObjeto());
            PrimeFaces.current().ajax().update("frmServicio:tipoTecnologia");
            PrimeFaces.current().ajax().update("frmServicio:codigoTecnologia");
            PrimeFaces.current().ajax().update("frmServicio:tecnologia");
            PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
        }
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
    }

    public void consultarPaquete() {
        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
        PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
    }

    public void cerrarDialogoPaquete() {
        MaPaquete paquete = getPaquetesBean().getObjeto();
        if (isIsObjecion()) {
            getObjetoObjecion().setTipoTecnologia(AuConstantes.ID_PAQUETE);
            getObjetoObjecion().setMaTecnologiaId(paquete.getId());
            getObjetoObjecion().setMaTecnologiaCodigo(paquete.getCodigo());
            getObjetoObjecion().setMaTecnologiaValor(paquete.getNombre());
            getObjetoObjecion().setAucHospitalizacionId(getObjeto());
            PrimeFaces.current().ajax().update("frmObjecion:panelObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecion:pEditarObjecion");
            PrimeFaces.current().ajax().update("frmEditarObjecionGestion:pEditarObjecionGestion");
        } else {
            getObjetoServicio().setTipoTecnologia(AuConstantes.ID_PAQUETE);
            getObjetoServicio().setMaTecnologiaId(paquete.getId());
            getObjetoServicio().setMaTecnologiaCodigo(paquete.getCodigo());
            getObjetoServicio().setMaTecnologiaValor(paquete.getNombre());
            getObjetoServicio().setAucHospitalizacionId(getObjeto());
            PrimeFaces.current().ajax().update("frmServicio:tipoTecnologia");
            PrimeFaces.current().ajax().update("frmServicio:codigoTecnologia");
            PrimeFaces.current().ajax().update("frmServicio:tecnologia");
            PrimeFaces.current().ajax().update("frmEditarServicio:pEditarServicio");
            PrimeFaces.current().ajax().update("frmEditarServicioGestion:pEditarServicioGestion");
        }
        PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
    }

    public void consultarEspecialidad(int egresoDiagnosticoEspecialidad) {
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').show()");
        PrimeFaces.current().ajax().update("frmEspecialidadBusqueda");
        if (egresoDiagnosticoEspecialidad == 0) {
            setIsEgresoDiagnosticoEspecialidad(true);
        } else {
            setIsEgresoDiagnosticoEspecialidad(false);
        }
    }

    public void cerrarDialogoEspecialidad() {
        MaEspecialidad especialidad = getEspecialidadBean().getObjeto();
        if(isIsEgresoDiagnosticoEspecialidad()){
            getObjeto().setMaEspecialidadesId(especialidad.getId());
            getObjeto().setMaEspecialidadesCodigo(especialidad.getCodigo());
            getObjeto().setMaEspecialidadesValor(especialidad.getNombre());
        }else{
            getObjeto().getAucEgresoId().setMaEspecialidadesId(especialidad.getId());
            getObjeto().getAucEgresoId().setMaEspecialidadesCodigo(especialidad.getCodigo());
            getObjeto().getAucEgresoId().setMaEspecialidadesValor(especialidad.getNombre());
        }
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmGestionar:especialidadEgresoGestionar");
        PrimeFaces.current().ajax().update("frmEditar:especialidadEgresoEditar");
        PrimeFaces.current().ajax().update("frmVerGestorasRegionales:especialidadEgresoGestionar");
        PrimeFaces.current().ajax().update("frmDiagnosticoEspecialidad:panelDiagnosticoEspecialidad");
    }

    public void eventoHabilitarDescripcion() {
        if (getObjeto().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
            getObjeto().getAucIngresoId().setHabilitarDescripcion(1);
        } else {
            getObjeto().getAucIngresoId().setHabilitarDescripcion(0);
        }
        PrimeFaces.current().ajax().update("frmCrear:pIngresoDescripcionCrear");
        //PrimeFaces.current().ajax().update("frmEditar:especialidadEgresoEditar");
    }
    
    public void eventoConsultarMaestroPosibleAltaTemprana(){
        setListaProgramaAltaTemprana(new ArrayList<>());
        setHashProgramaAltaTemprana(new HashMap<>());
        getObjeto().getAucIngresoId().setCampoObligatorioProgramaAltaTemprana(false); 
        if(getObjeto().getAucIngresoId().getAltaTemprana() != null){
            if(getObjeto().getAucIngresoId().getAltaTemprana() == 1){
                getAucHospitalizacionServicio().consultarMaestroPosibleAltaTemprana(this);
                getObjeto().getAucIngresoId().setCampoObligatorioProgramaAltaTemprana(true);
            }
        }
    }
    
    public void eventoConsultarMaestroMotivoReingreso(){
        setListaMotivoReingreso(new ArrayList<>());
        setHashMotivoReingreso(new HashMap<>());
        getObjeto().getAucIngresoId().setCampoObligatorioMotivoReingreso(false); 
        if(getObjeto().getAucIngresoId().getIngreso() != null){
            if(getObjeto().getAucIngresoId().getIngreso() == 0){
                agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "Reingresa antes de 15 días por la misma causa, no fue programado, y refleja la NO CALIDAD en la prestación de servicios."));
                getAucHospitalizacionServicio().consultarMaestroMotivoReingreso(this);
                getObjeto().getAucIngresoId().setCampoObligatorioMotivoReingreso(true);
            }
        }
    }
    
    public void cargarConsultarMaestroMotivoReingreso(){
        setListaMotivoReingreso(new ArrayList<>());
        setHashMotivoReingreso(new HashMap<>());
        getObjeto().getAucIngresoId().setCampoObligatorioMotivoReingreso(false); 
        if(getObjeto().getAucIngresoId().getIngreso() != null){
            if(getObjeto().getAucIngresoId().getIngreso() == 0){
                getAucHospitalizacionServicio().consultarMaestroMotivoReingreso(this);
                getObjeto().getAucIngresoId().setCampoObligatorioMotivoReingreso(true);
            }
        }
    }
    
    public void habilitarCampoIpsReceptoras(){
        getObjetoSeguimiento().setHabilitarCampoIpsReceptora(true); 
        if(getObjetoSeguimiento().getMaeTipoGestionId() != null){
            Maestro maestro = getAucHospitalizacionServicio().consultarMaestro(getObjetoSeguimiento().getMaeTipoGestionId(),this);
            if(maestro != null){
                if(maestro.contieneAccion(MaestroAccion.AUC_SEGUIMIENTO_GESTORES_IPS_RECEPTORA)){
                     getObjetoSeguimiento().setHabilitarCampoIpsReceptora(false); 
                }else{
                    getObjetoSeguimiento().setCntPrestadoresId(null);
                    getObjetoSeguimiento().setCntPrestadorSedesId(null);
                }
            }
        }
    }
    
    public void habilitarCampoFechaCierreGestion(){
        getObjetoSeguimiento().setHabilitarCampoFechaCierreGestion(true); 
        if(getObjetoSeguimiento().getMaeTipoGestionEstadoId() != null){
            Maestro maestro = getAucHospitalizacionServicio().consultarMaestro(getObjetoSeguimiento().getMaeTipoGestionEstadoId(),this);
            if(maestro != null){
                if(maestro.contieneAccion(MaestroAccion.AUC_SEGUIMIENTO_ESTADO_FECHA_CIERRE_GESTION)){
                    getObjetoSeguimiento().setHabilitarCampoFechaCierreGestion(false); 
                }else{
                    getObjetoSeguimiento().setFechaCierreGestion(null);
                    getObjetoSeguimiento().setMaeDestinoId(null);
                    getObjetoSeguimiento().setMaeDestinoCodigo(null);
                    getObjetoSeguimiento().setMaeDestinoValor(null);
                }
            }
        }
    }
    
    public void habilitarCampoIpsReceptorasCargar(){
        getObjetoSeguimiento().setHabilitarCampoIpsReceptora(true); 
        if(getObjetoSeguimiento().getMaeTipoGestionId() != null){
            Maestro maestro = getAucHospitalizacionServicio().consultarMaestro(getObjetoSeguimiento().getMaeTipoGestionId(),this);
            if(maestro != null){
                if(maestro.contieneAccion(MaestroAccion.AUC_SEGUIMIENTO_GESTORES_IPS_RECEPTORA)){
                     getObjetoSeguimiento().setHabilitarCampoIpsReceptora(false); 
                }
            }
        }
    }
    
    public void habilitarCampoNumeroCertificado() {
        if (getObjeto().getAucEgresoId().isFallecido()) {
            setHabilitarEgresoNumeroCertificado(false);
            setCampoObligatorioNumeroCertificado(true);
            getAucHospitalizacionServicio().consultarLimpiarMaestroDestion(this);
        } else {
            setHabilitarEgresoNumeroCertificado(true);
            setCampoObligatorioNumeroCertificado(false);
            getObjeto().getAucEgresoId().setMaeDestinoEgresoId(0);
            getObjeto().getAucEgresoId().setNumCertificado(null);
            getAucHospitalizacionServicio().consultarMaestroDestino(this);
        }
        generarMensajes();
    }

    public void metodoHabilitarCampoValorEstancia() {

        if (getObjeto().getAucEgresoId().isIpsEntregaValor()) {
            setHabilitarCampoValorEstancia(false);
            setCampoObligatorioValorEstancia(true);
        } else {
            setHabilitarCampoValorEstancia(true);
            setCampoObligatorioValorEstancia(false);
            getObjeto().getAucEgresoId().setValorEstancia(new BigDecimal(0));
        }
    }

    public void listarEstadosDeSubCategoria() {
        getAucHospitalizacionServicio().listarEstadosDeSubCategoria(this);
    }

    public void onRowSelectIpsActa(SelectEvent event) {
        CntPrestadorSede sede = (CntPrestadorSede) event.getObject();
        setObjPrestadorSede(sede);
        List<ReporteActaJustificacion> listaReportes = getAucHospitalizacionServicio().generarReporteActas(this);
        if (!listaReportes.isEmpty()) {
            setHabilitarCampoImprimirActa(false);
            PrimeFaces.current().ajax().update("frmImprimirActa");
            PrimeFaces.current().executeScript("PF('dialogoImprimirActa').show()");
        } else {
            setHabilitarCampoImprimirActa(true);
            addError("No hay datos para generar el reporte");
        }
        generarMensajes();
    }

    public StreamedContent imprimiActa() {
        StreamedContent streamContent = null;
        try {
            List<ReporteActaJustificacion> listaReportes = getAucHospitalizacionServicio().generarReporteActas(this);
            if (!listaReportes.isEmpty()) {
                InputStream is = getClass().getResourceAsStream("/reportes/ActaJustificacion.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReportes);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                //streamContent = new DefaultStreamedContent(stream, "application/pdf", "Reporte.pdf");
                //PrimeFaces.current().executeScript("PF('dialogoIpsListaActa').hide()");
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").name("acta_de_justificacion_de_estancia_prolongada.pdf").stream(() -> stream).build();
            } else {
                addError("Error no hay datos para generar el reporte");
            }
            //setParamConsulta2(new ParamConsulta());
            //getParamConsulta2().setFiltros(new HashMap<>());

        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
        }
        PrimeFaces.current().executeScript("PF('dialogoImprimirActa').hide()");
        generarMensajes();
        return streamContent;
    }

    public StreamedContent descargarHositalizacion(int idHospitalizacion) {
        getObjeto().setId(idHospitalizacion);
        StreamedContent streamContent = null;
        LocalDate fechaActl = LocalDate.now();
        super.setAccion(ACCION_ADICIONAL_8);
        getAucHospitalizacionServicio().Accion(this);
        try {
            if (!super.isError()) {
                List<ReporteHospitalizacion> reporteHospitalizacion = getAucHospitalizacionServicio().descargarHospitalizacion(this);

                InputStream is = getClass().getResourceAsStream("/reportes/ActaHospitalizacion.jasper");
                //JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(null);
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                parameters.put(AucHospitalizacion.LISTA_DIAGNOSTICOS, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion.get(0).getListaDiagnosticosIngreso()));
                parameters.put(AucHospitalizacion.LISTA_ESTANCIAS, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion.get(0).getListaEstancias()));
                parameters.put(AucHospitalizacion.LISTA_DIAGNOSTICOS_EGRESO, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion.get(0).getListaDiagnosticosEgreso()));
                parameters.put(AucHospitalizacion.LISTA_SEGUIMIENTO, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion));
                parameters.put(AucHospitalizacion.LISTA_ESTANCIAS_PROLONGADAS, new JRBeanCollectionDataSource((Collection<?>) reporteHospitalizacion));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0);
                AucHospitalizacion hospitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(idHospitalizacion, this);
                StringBuilder nombreArchivo = new StringBuilder();
                nombreArchivo.append(hospitalizacion.getAucAfiliadoId().getMaeTipoDocumentoCodigo());
                nombreArchivo.append(hospitalizacion.getAucAfiliadoId().getNumeroDocumento());
                nombreArchivo.append("_Hosp_");
                nombreArchivo.append(getObjeto().getId());
                nombreArchivo.append("_");
                nombreArchivo.append(fechaActl.toString().replace("-", ""));
                streamContent = DefaultStreamedContent.builder().contentType("application/pdf").name(nombreArchivo + ".pdf").stream(() -> stream).build();
            }
        } catch (JRException ex) {
            streamContent = null;
            addError("Error Reporte: " + ex.toString() + ex.getMessage());
        }
        generarMensajes();
        return streamContent;
    }

    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
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

    @SuppressWarnings("UnnecessaryUnboxing")
    public String getCausasRecursiva(int id) {
        String causasStr = "";

        for (Maestro maestro : listCausasActivo) {
            if (maestro.getId().intValue() == id) {
                causasStr = maestro.getNombre();
                break;
            }
        }

        for (Maestro maestro : listCausasInactivo) {
            if (maestro.getId().intValue() == id) {
                causasStr = maestro.getNombre();
                break;
            }
        }

        return causasStr;
    }

    public String getEstadoRecursiva(Boolean estado) {
        if (estado != null && estado) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String getEstado(Integer id) {
        //1 - Pendiente | 2 - Marcado | 3 - Rechazado
        return PeConstantes.getEstadoSugerido(id);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    //PrimeFaces.current().resetInputs("frmHospitalizaciones");
                    PrimeFaces.current().ajax().update("frmHospitalizaciones");
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                    PrimeFaces.current().resetInputs("frmHospitalizaciones");
                    PrimeFaces.current().ajax().update("frmHospitalizaciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().ajax().update("frmEditar");
                            PrimeFaces.current().ajax().update("frmAuditoriaEditar:labelDatosAuditoria");
                            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmEditarSeguimiento");
                            PrimeFaces.current().executeScript("PF('dialogoEditarSeguimiento').show()");
                            crearLog(getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmEditarEstancias");
                            PrimeFaces.current().executeScript("PF('dialogoEstanciasEditar').show()");
                            crearLog(getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmEditarPatologia");
                            PrimeFaces.current().executeScript("PF('dialogoPatologiaEditar').show()");
                            crearLog(getObjetoPatologia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            PrimeFaces.current().ajax().update("frmEditarInoportunidad");
                            PrimeFaces.current().executeScript("PF('dialogoInoportunidadEditar').show()");
                            crearLog(getObjetoInoportunidad().toString());
                            break;
                        case ACCION_ADICIONAL_5:
                            PrimeFaces.current().ajax().update("frmEditarAdverso");
                            PrimeFaces.current().executeScript("PF('dialogoAdversoEditar').show()");
                            crearLog(getObjetoAdverso().toString());
                            break;
                        case ACCION_ADICIONAL_6:
                            PrimeFaces.current().ajax().update("frmEditarObjecion");
                            PrimeFaces.current().executeScript("PF('dialogoObjecionEditar').show()");
                            crearLog(getObjetoObjecion().toString());
                            break;
                        case ACCION_ADICIONAL_7:
                            PrimeFaces.current().ajax().update("frmEditarServicio");
                            PrimeFaces.current().executeScript("PF('dialogoServicioEditar').show()");
                            crearLog(getObjetoServicio().toString());
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            PrimeFaces.current().ajax().update("frmEditarEstanciaDescripcion");
                            PrimeFaces.current().executeScript("PF('dialogoEditarEstanciaDescripcion').show()");
                            crearLog(getObjetoJustificacionEstanciasProlongada().toString());
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            PrimeFaces.current().ajax().update("frmVerEstancia");
                            PrimeFaces.current().executeScript("PF('dialogoVerEstancia').show()");
                            crearLog(getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            PrimeFaces.current().ajax().update("frmVerPrograma");
                            PrimeFaces.current().executeScript("PF('dialogoVerPrograma').show()");
                            crearLog(getObjetoPePregrama().toString());
                            break;
                        case Url.ACCION_ADICIONAL_11:
                            PrimeFaces.current().ajax().update("frmVerSugerido");
                            PrimeFaces.current().executeScript("PF('dialogoVerSugerido').show()");
                            crearLog(getObjetoSugerido().toString());
                            break;
                    }

                    break;
                case Url.ACCION_MODIFICAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:fechaInicioHospitalizacionEditar");
                            PrimeFaces.current().ajax().update("frmEditar:diasHospitalizacionEditar");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            refrescarSeguimientos();
                            PrimeFaces.current().executeScript("PF('dialogoEditarSeguimiento').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaSeguimientosEditar");
                            crearLog(getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            refrescarEstancias();
                            PrimeFaces.current().executeScript("PF('dialogoEstanciasEditar').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEditar");
                            PrimeFaces.current().ajax().update("frmEditar:fechaEgresoGestionar");
                            crearLog(getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            refrescarPalogias();
                            PrimeFaces.current().executeScript("PF('dialogoPatologiaEditar').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaPatologiaEditar");
                            crearLog(getObjetoPatologia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            refrescarInoportunidades();
                            PrimeFaces.current().executeScript("PF('dialogoInoportunidadEditar').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaInoportunidadEditar");
                            crearLog(getObjetoInoportunidad().toString());
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            refrescarAdversos();
                            PrimeFaces.current().executeScript("PF('dialogoAdversoEditar').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaAdversoEditar");
                            crearLog(getObjetoAdverso().toString());
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            refrescarObjeciones();
                            PrimeFaces.current().executeScript("PF('dialogoObjecionEditar').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaObjecionEditar");
                            crearLog(getObjetoObjecion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            refrescarServicios();
                            PrimeFaces.current().executeScript("PF('dialogoServicioEditar').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaServicioEditar");
                            crearLog(getObjetoServicio().toString());
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            refrescarJustifiacionEstanciasProlongada();
                            PrimeFaces.current().executeScript("PF('dialogoEditarEstanciaDescripcion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaJustificacionEstanciaProlongadaEditar");
                            crearLog(getObjetoJustificacionEstanciasProlongada().toString());
                            break;
                    }

                    break;
                case Url.ACCION_BORRAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            refrescarDiagnosticosIngreso();
                            refrescarDiagnosticosEgreso();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticoEgresoGestion");
                            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosEditar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticoEgresoEditar");
                            crearLog(getObjetoDiagnostico().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            refrescarEstancias();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaEstanciaGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEditar");
                            crearLog(getObjetoEstancia().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVer");
                            PrimeFaces.current().ajax().update("frmAuditoriaVer:labelDatosAuditoria");
                            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                            crearLog(getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmVerSeguimiento");
                            PrimeFaces.current().executeScript("PF('dialogoVerSeguimiento').show()");
                            crearLog(getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmVerEstancia");
                            PrimeFaces.current().executeScript("PF('dialogoVerEstancia').show()");
                            crearLog(getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmVerPatologia");
                            PrimeFaces.current().executeScript("PF('dialogoVerPatologia').show()");
                            crearLog(getObjetoPatologia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            PrimeFaces.current().ajax().update("frmVerInoportunidad");
                            PrimeFaces.current().executeScript("PF('dialogoVerInoportunidad').show()");
                            crearLog(getObjetoInoportunidad().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            PrimeFaces.current().ajax().update("frmVerAdverso");
                            PrimeFaces.current().executeScript("PF('dialogoVerAdverso').show()");
                            crearLog(getObjetoAdverso().toString());
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            PrimeFaces.current().ajax().update("frmVerObjecion");
                            PrimeFaces.current().executeScript("PF('dialogoVerObjecion').show()");
                            crearLog(getObjetoObjecion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            PrimeFaces.current().ajax().update("frmVerServicio");
                            PrimeFaces.current().executeScript("PF('dialogoVerServicio').show()");
                            crearLog(getObjetoServicio().toString());
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            PrimeFaces.current().ajax().update("frmVerEstanciaDescripcion");
                            PrimeFaces.current().executeScript("PF('dialogoVerEstanciaDescripcion').show()");
                            crearLog(getObjetoJustificacionEstanciasProlongada().toString());
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            PrimeFaces.current().ajax().update("frmVerPrograma");
                            PrimeFaces.current().executeScript("PF('dialogoVerPrograma').show()");
                            crearLog(getObjetoPePregrama().toString());
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            PrimeFaces.current().ajax().update("frmVerSugerido");
                            PrimeFaces.current().executeScript("PF('dialogoVerSugerido').show()");
                            crearLog(getObjetoSugerido().toString());
                            break;
                        case Url.ACCION_ADICIONAL_11:
                            PrimeFaces.current().ajax().update("frmVerRescate");
                            PrimeFaces.current().executeScript("PF('dialogoVerRescate').show()");
                            crearLog(getObjetoRescate().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:fechaEgresoGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:valorEstanciaEgresoGestionar");
                            PrimeFaces.current().ajax().update("frmAuditoriaGestionar:labelDatosAuditoria");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            crearLog("Gestionar",getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Modificar Hospitalización",getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmVerEstancia");
                            PrimeFaces.current().executeScript("PF('dialogoVerEstancia').show()");
                            crearLog("Ver Estancia",getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmEditarEstanciaDescripcionGestion");
                            PrimeFaces.current().executeScript("PF('dialogoEditarEstanciaDescripcionGestion').show()");
                            crearLog("Editar Justi Estancia Prolo",getObjetoJustificacionEstanciasProlongada().toString());
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            refrescarJustifiacionEstanciasProlongada();
                            PrimeFaces.current().executeScript("PF('dialogoEditarEstanciaDescripcionGestion').hide()");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaJustificacionEstanciaProlongadaGestionar");
                            crearLog("Modificar Justi Estancia Prolo",getObjetoJustificacionEstanciasProlongada().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            refrescarEstancias();
                            refrescarDiagnosticosIngreso();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaEstanciaGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:ingresoGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
                            PrimeFaces.current().ajax().update("frmGestionar:fechaInicioHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:diasHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:fechaInicioHospitalizacionEditar");
                            PrimeFaces.current().ajax().update("frmEditar:diasHospitalizacionEditar");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Modificar Ingreso",getObjeto().getAucIngresoId().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:panelBotonesGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:CierreAuditoria");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmGestionar:fechaFinHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:diasHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:fechaFinHospitalizacionEditar");
                            PrimeFaces.current().ajax().update("frmEditar:diasHospitalizacionEditar");
                            crearLog("Guardar Egreso",getObjeto().getAucEgresoId().toString());
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:panelBotonesGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:CierreAuditoria");
                            PrimeFaces.current().ajax().update("frmGestionar:fechaFinHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:diasHospitalizacionGestionar");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Modificar Egreso",getObjeto().getAucEgresoId().toString());
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            refrescarGestionRiesgo();
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().resetInputs("frmSugerirProgramaCrear");
                            PrimeFaces.current().ajax().update("frmSugerirProgramaCrear");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaProgramaEspecialGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoSugerirProgramaCrear').hide()");
                            crearLog("Guardar Gestión Riesgo",getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            PrimeFaces.current().ajax().update("frmVerPrograma");
                            PrimeFaces.current().executeScript("PF('dialogoVerPrograma').show()");
                            crearLog("Ver Programa",getObjetoPePregrama().toString());
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            PrimeFaces.current().ajax().update("frmVerSugerido");
                            PrimeFaces.current().executeScript("PF('dialogoVerSugerido').show()");
                            crearLog("Ver Sugerido",getObjetoSugerido().toString());
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaRescateGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarRescate').hide()");
                            PrimeFaces.current().executeScript("PF('dialogoAgregarRescateNoActo').hide()");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Guardar Rescate",getObjetoRescate().toString());
                            break;
                        case Url.ACCION_ADICIONAL_13:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaSeguimientosGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoNoAptoRescate').hide()");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Guardar No Apto Rescate",getObjetoSeguimiento().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmEditarSeguimientoGestion");
                            PrimeFaces.current().executeScript("PF('dialogoEditarSeguimientoGestion').show()");
                            crearLog("Editar Seguimiento Gestión",getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            refrescarSeguimientos();
                            PrimeFaces.current().executeScript("PF('dialogoEditarSeguimientoGestion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaSeguimientosEditar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaSeguimientosGestionar");
                            crearLog("Modificar Seguimiento",getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmEditarEstanciasGestion");
                            PrimeFaces.current().executeScript("PF('dialogoEstanciasEditarGestion').show()");
                            crearLog("Editar Estancia",getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            refrescarEstancias();
                            PrimeFaces.current().executeScript("PF('dialogoEstanciasEditarGestion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEditar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaEstanciaGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:fechaEgresoGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:fechaFinHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:diasHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:fechaFinHospitalizacionEditar");
                            PrimeFaces.current().ajax().update("frmEditar:diasHospitalizacionEditar");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaEstanciaVer");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:fechaEgresoGestionar");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:fechaFinHospitalizacionGestionar");
                            crearLog("Modificar Estancia",getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmEditarPatologiaGestion");
                            PrimeFaces.current().executeScript("PF('dialogoPatologiaEditarGestion').show()");
                            crearLog("Editar Patología",getObjetoPatologia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            refrescarPalogias();
                            PrimeFaces.current().executeScript("PF('dialogoPatologiaEditarGestion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaPatologiaEditar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaPatologiaGestionar");
                            crearLog("Modificar Patología",getObjetoPatologia().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                             switch(getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    PrimeFaces.current().ajax().update("frmEditarInoportunidadGestion");
                                    PrimeFaces.current().executeScript("PF('dialogoInoportunidadEditarGestion').show()");
                                    crearLog("Editar Inoportunidad",getObjetoInoportunidad().toString());
                                    break;
                                case Url.ACCION_GUARDAR:
                                    refrescarInoportunidades();
                                    PrimeFaces.current().executeScript("PF('dialogoInoportunidadEditarGestion').hide()");
                                    PrimeFaces.current().ajax().update("frmEditar:tablaInoportunidadEditar");
                                    PrimeFaces.current().ajax().update("frmGestionar:tablaInoportunidadGestionar");
                                    crearLog("Modificar Inoportunidad",getObjetoInoportunidad().toString());
                                    break;
                            }
                            break;
                        /*case ACCION_MODIFICAR_INOPORTUNIDAD:
                            refrescarInoportunidades();
                            PrimeFaces.current().executeScript("PF('dialogoInoportunidadEditarGestion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaInoportunidadEditar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaInoportunidadGestionar");
                            crearLog("Modificar Inoportunidad",getObjetoInoportunidad().toString());
                            break;*/
                        case ACCION_ADICIONAL_5:
                            PrimeFaces.current().ajax().update("frmEditarObjecionGestion");
                            PrimeFaces.current().executeScript("PF('dialogoObjecionEditarGestion').show()");
                            crearLog("Editar Objeción",getObjetoObjecion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            refrescarObjeciones();
                            PrimeFaces.current().executeScript("PF('dialogoObjecionEditarGestion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaObjecionEditar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaObjecionGestionar");
                            crearLog("Modificar Objeción",getObjetoObjecion().toString());
                            break;
                        case ACCION_ADICIONAL_6:
                            PrimeFaces.current().ajax().update("frmEditarServicioGestion");
                            PrimeFaces.current().executeScript("PF('dialogoServicioEditarGestion').show()");
                            crearLog("Editar Servicio",getObjetoServicio().toString());
                            break;
                        case Url.ACCION_ADICIONAL_11:
                            refrescarServicios();
                            PrimeFaces.current().executeScript("PF('dialogoServicioEditarGestion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaServicioEditar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaServicioGestionar");
                            crearLog("Modificar Servicio",getObjetoServicio().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmEditarAdversoGestion");
                            PrimeFaces.current().executeScript("PF('dialogoAdversoEditarGestion').show()");
                            crearLog("Editar Adverso",getObjetoAdverso().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            refrescarAdversos();
                            PrimeFaces.current().executeScript("PF('dialogoAdversoEditarGestion').hide()");
                            PrimeFaces.current().ajax().update("frmEditar:tablaAdversoEditar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaAdversoGestionar");
                            crearLog("Modificar Adverso",getObjetoAdverso().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_4:
                            PrimeFaces.current().ajax().update("frmSeguimientoAnulacion");
                            PrimeFaces.current().executeScript("PF('dialogoSeguimientoAnulacion').show()");
                            crearLog("Anulacion",getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoSeguimientoAnulacion').hide()");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Guardar Anulación",getObjetoSeguimiento().toString());
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            refrescarEstancias();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaEstanciaGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEditar");
                            crearLog("Borrar Estancia",getObjetoEstancia().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            refrescarAdversos();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaAdversoGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaAdversoEditar");
                            PrimeFaces.current().executeScript("PF('dialogoBorrarAdverso').hide()");
                            crearLog("Borrar Adverso",getObjetoAdverso().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            refrescarObjeciones();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaObjecionGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaObjecionEditar");
                            PrimeFaces.current().executeScript("PF('dialogoBorrarObjecion').hide()");
                            crearLog("Borrar Objeción",getObjetoObjecion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            refrescarInoportunidades();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaInoportunidadGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaInoportunidadEditar");
                            PrimeFaces.current().executeScript("PF('dialogoBorrarInoportunidad').hide()");
                            crearLog("Borrar Inoportunidad",getObjetoInoportunidad().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            refrescarServicios();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaServicioGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaServicioEditar");
                            PrimeFaces.current().executeScript("PF('dialogoBorrarServicio').hide()");
                            crearLog("Borrar Servicio",getObjetoServicio().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            PrimeFaces.current().ajax().update("frmRechazarSugerido");
                            PrimeFaces.current().executeScript("PF('dialogoRechazarSugerido').show()");
                            crearLog("Borrar Sugerido",getObjetoSugerido().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            refrescarGestionRiesgo();
                            PrimeFaces.current().ajax().update("frmGestionar:tablaProgramaEspecialGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:tablaProgramaEspecialEditar");
                            PrimeFaces.current().executeScript("PF('dialogoRechazarSugerido').hide()");
                            PrimeFaces.current().ajax().update("frmRechazarSugerido");
                            crearLog("Modificar Sugerido",getObjetoSugerido().toString());
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            refrescarGestorasRegionales();
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaGestorasRegionalesGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoBorrarGestionGestoras').hide()");
                            crearLog("Borrar Gestoras Regionales",getObjetoSeguimiento().toString());

                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_7:
                            PrimeFaces.current().ajax().update("frmRevertirEstadoHospitalizacion");
                            PrimeFaces.current().executeScript("PF('dialogoRevertirEstadoHospitalizacion').show()");
                            crearLog("Revertir Estado Hospitalización",getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoRevertirEstadoHospitalizacion').hide()");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Guardar Revertir Estado Hospitalización",getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_10:
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales");
                            PrimeFaces.current().executeScript("PF('dialogoVerGestorasRegionales').show()");
                            crearLog("Gestoras Regionales",getObjeto().toString());
                            break;
                        case ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoRevertirEstadoHospitalizacion').hide()");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Guardar Revertir Estado Hospitalización",getObjeto().toString());
                            break;
                        case Url.ACCION_VER:
                            PrimeFaces.current().executeScript("PF('dialogoVerGestorasRegionalesv').show()");
                            PrimeFaces.current().resetInputs("frmVerGestorasRegionalesv");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionalesv");
                            crearLog("Ver gestoras Regionales",getObjeto().toString());
                            break; 
                        case ACCION_EDITAR:
                            PrimeFaces.current().executeScript("PF('dialogoEditarGestorasRegionales').show()");
                            PrimeFaces.current().resetInputs("frmEditarGestorasRegionales");
                            PrimeFaces.current().ajax().update("frmEditarGestorasRegionales");
                            crearLog("Editar gestoras Regionales",getObjeto().toString());
                            break; 
                        case Url.ACCION_ADICIONAL_1:
                            refrescarGestorasRegionales();
                            PrimeFaces.current().executeScript("PF('dialogoEditarGestorasRegionales').hide()");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaGestorasRegionalesGestionar");
                            crearLog(getObjetoSeguimiento().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:panelBotonesGestionar");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:CierreAuditoria");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:fechaFinHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:diasHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:fechaFinHospitalizacionEditar");
                            PrimeFaces.current().ajax().update("frmEditar:diasHospitalizacionEditar");
                            crearLog("Guardar Egreso",getObjeto().getAucEgresoId().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:panelBotonesGestionar");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:CierreAuditoria");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:fechaFinHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:diasHospitalizacionGestionar");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Modificar Egreso",getObjeto().getAucEgresoId().toString());
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            //PrimeFaces.current().ajax().update("frmEditar:tablaEstanciaEditar");
                            refrescarEstancias();
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaEstanciaVer");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:fechaEgresoGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoEstanciaGestoras').hide()");
                            PrimeFaces.current().executeScript("PF('dialogoNoCerrarHospitalizacionGestoras').hide()");
                            crearLog("Guardar Estancia", getObjetoEstancia().toString());
                            break;
                          case Url.ACCION_ADICIONAL_5:
                            refrescarEstancias();
                            refrescarDiagnosticosIngreso();
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaEstanciaVer");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:pIngresoVer");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:tablaDiagnosticosVer");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:fechaInicioHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmVerGestorasRegionales:diasHospitalizacionGestionar");
                            PrimeFaces.current().ajax().update("frmEditar:fechaInicioHospitalizacionEditar");
                            PrimeFaces.current().ajax().update("frmEditar:diasHospitalizacionEditar");
                            PrimeFaces.current().resetInputs("frmHospitalizaciones");
                            PrimeFaces.current().ajax().update("frmHospitalizaciones");
                            crearLog("Modificar Ingreso",getObjeto().getAucIngresoId().toString());
                            break;
                    }
                    break;

            }
        }
        generarMensajes();
    }

    public boolean validarEstadoEgresado(int estado) {
        return estado == AucHospitalizacion.ESTADO_AFILIADO_EGRESADO;
    }

    public void validarEstaciaAbierta() {
        boolean cerrarModalGestionarOeditar = false;
        if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            AucHospitalizacionEstancia estanciaUltima = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1);
            if (getObjeto().getEstado() == AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO && estanciaUltima.getFechaEgreso() != null) {
                cerrarModalGestionarOeditar = true;
            }
        }

        if (cerrarModalGestionarOeditar) {
            //PrimeFaces.current().resetInputs("frmGestionar");
            //PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
            PrimeFaces.current().executeScript("PF('dialogoNoCerrarHospitalizacion').show()");
        }
        generarMensajes();
    }
    
    public void validarEstaciaAbiertaGestoras() {
        boolean cerrarModalGestionarOeditar = false;
        if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            AucHospitalizacionEstancia estanciaUltima = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1);
            if (getObjeto().getEstado() == AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO && estanciaUltima.getFechaEgreso() != null) {
                cerrarModalGestionarOeditar = true;
            }
        }

        if (cerrarModalGestionarOeditar) {
            //PrimeFaces.current().resetInputs("frmGestionar");
            //PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoVerGestorasRegionales').show()");
            PrimeFaces.current().executeScript("PF('dialogoNoCerrarHospitalizacionGestoras').show()");
        }
        generarMensajes();
    }
    
    public void validarEstaciaAbiertaEditar() throws InterruptedException {

        Thread.sleep(2000L);
        boolean cerrarModalGestionarOeditar = false;
        if (!getObjeto().getAucHospitalizacionEstanciaList().isEmpty()) {
            AucHospitalizacionEstancia estanciaUltima = getObjeto().getAucHospitalizacionEstanciaList().get(getObjeto().getAucHospitalizacionEstanciaList().size() - 1);
            if (getObjeto().getEstado() == AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO && estanciaUltima.getFechaEgreso() != null) {
                cerrarModalGestionarOeditar = true;
            }
        }

        if (cerrarModalGestionarOeditar) {
            //PrimeFaces.current().resetInputs("frmGestionar");
            //PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
            PrimeFaces.current().executeScript("PF('dialogoNoCerrarHospitalizacion').show()");
        }
        agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
        generarMensajes();
    }

    @SuppressWarnings({"null", "UnusedAssignment"})
    public String obtenerColorUltimaNota(Date fechaUltimaNota, int estado) {
        String color = "";
        LocalDate fechaActua = LocalDate.now();
        Date fechaSeguimiento = null;
        String formatStringFechaSeguimiento = null;
        LocalDate convertidaFechaSegumiento = null;
        SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyy-MM-dd");
        if (estado == AucHospitalizacion.ESTADO_AFILIADO_HOSPITALIZADO) {
            fechaSeguimiento = fechaUltimaNota;
            if (fechaSeguimiento != null) {
                formatStringFechaSeguimiento = formatterFecha.format(fechaSeguimiento);
                convertidaFechaSegumiento = LocalDate.parse(formatStringFechaSeguimiento);
            } else {
                convertidaFechaSegumiento = LocalDate.now();
            }
            //int milisecondsByDay = 86400000;
            long diasFecha = DAYS.between(convertidaFechaSegumiento, fechaActua);
            //int diasFecha = (int) ((fechaActua.to - convertidaFechaSegumiento) / milisecondsByDay);
            if (diasFecha < 0) {
                color = "white";
            } else if (diasFecha >= 0 && diasFecha <= 3) {
                color = "green";
            } else if (diasFecha > 3) {
                color = "red";
            }
        } else {
            color = "white";
        }
        return color;
    }

    public void handleFileUploadItem(FileUploadEvent event) throws IOException {

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
                String nombre = getObjeto().getAucAfiliadoId().getMaeTipoDocumentoCodigo() + getObjeto().getAucAfiliadoId().getNumeroDocumento() + maestro.getNombre().replace(" ", "_") + "_";
                String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
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
                String nombre = getObjeto().getAucAfiliadoId().getMaeTipoDocumentoCodigo() + getObjeto().getAucAfiliadoId().getNumeroDocumento() + maestro.getNombre().replace(" ", "_") + "_";
                String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
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

    public void handleFileUploadAdjuntoRescate(FileUploadEvent event) throws IOException {

        try {
            
            Maestro maestro = getAucHospitalizacionServicio().consultarMaestroPeadjuntoTipo(this);
            if(maestro != null){
                String file = FilenameUtils.getName(event.getFile().getFileName());
                int i = file.lastIndexOf(".");
                String ext = file.substring(i, file.length());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String nombre = getObjeto().getAucAfiliadoId().getMaeTipoDocumentoCodigo() + getObjeto().getAucAfiliadoId().getNumeroDocumento() + maestro.getNombre().replace(" ", "_") + "_";
                String filename = nombre + sdf.format(new Date()) + "_" + (++contadorArchivos) + ext;
                UploadedFile archivo = event.getFile();
                byte[] arreglo = archivo.getInputStream().readAllBytes();
                InputStream input = new ByteArrayInputStream(arreglo);

                String ruta = PropApl.getInstance().get(PropApl.AU_RUTA_ADJUNTOS_RESCATES);
                if (ruta == null) {
                    throw new IOException("Configura la ruta de los adjuntos  en tabla parametros");
                }
                OutputStream output = new FileOutputStream(new File(ruta, filename));

                IOUtils.copy(input, output);
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);

                //agraegar el nuevo anexo a la lista de anexos de tutela
                AuSolicitudAdjunto adjunto = new AuSolicitudAdjunto();
                adjunto.setId(null);
                adjunto.setNombre(file);
                adjunto.setArchivo(filename);
                adjunto.setRuta(ruta);
                adjunto.setOrigen(AuSolicitudAdjunto.ORIGEN_HOSPITALIZACION);
                adjunto.setMaeTipoArchivoId(maestro.getId());
                adjunto.setMaeTipoArchivoCodigo(maestro.getValor());
                adjunto.setMaeTipoArchivoValor(maestro.getNombre());
                adjunto.setExiste(true);
                this.auditoriaGuardar(adjunto);
                if (this.getObjetoRescate().getAuSolicitudAdjuntosList() == null) {
                    this.getObjetoRescate().setAuSolicitudAdjuntosList(new ArrayList<>());
                }
                //adjunto.setPosicion(this.getObjetoRescate().getAuSolicitudAdjuntosList().size());
                this.getObjetoRescate().getAuSolicitudAdjuntosList().add(adjunto);
                getObjetoRescate().setMaeTipoArchivoId(null);
                //PrimeFaces.current().resetInputs("frmAgregarRescate:somTipoRescate");
                //PrimeFaces.current().ajax().update("frmAgregarRescate:somTipoRescate");
                PrimeFaces.current().resetInputs("frmAgregarRescate:tablaRescateAnexos");
                PrimeFaces.current().ajax().update("frmAgregarRescate:tablaRescateAnexos");
            }else {
                this.addError("Error al consultar maestro");
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
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {

        }
    }
    
    public void descargarRescateAnexo(AuSolicitudAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + File.separator + adjunto.getArchivo();
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
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {

        }
    }
    
    /**
     * Metodo para convertir el archivo para megas
     *
     * @return
     */
    public String convertirBytesParaMegasTamAdjunto() {
        int valor = getSizeLimitByAnexo() <= 0 ? 15000000 : getSizeLimitByAnexo();
        float MEGABYTE = 1024L * 1024L;
        float b = valor / MEGABYTE;
        return (b + " MB");
    }

    /**
     *
     * @return
     */
    public boolean validarEdicionValorEstanciaEstadoCerrado() {
        boolean validar = true;
        if ((!isHabilitarCampoValorEstancia() && isBloquearBotonesCierreAuditoria()) && this.isAccionAdicional9()) {
            validar = false;
        } else if (!isHabilitarCampoValorEstancia() && isBloquearBotonesCierreAuditoria()) {
            validar = true;
        } else if (!isHabilitarCampoValorEstancia()) {
            validar = false;
        }
        return validar;
    }

    public String getTipoAdjuntoRecursiva(int id) {
        return getHashTiposSugeridoAdjuntos().get(id).getNombre();
    }

    public void borrarAdjuntoPrograma(String nombre) {
        PeAdjunto borraObjecto = new PeAdjunto();
        for (PeAdjunto peAdjunto : peAdjuntos) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }

        peAdjuntos.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAdjunto");
    }

    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
            if (objectoAdjunto.getMaeTipoArchivoId() == 0) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }
            PeAdjunto borrarObj = new PeAdjunto();
            for (PeAdjunto peAdjunto : peAdjuntos) {
                if (peAdjunto.getMaeTipoArchivoId() == objectoAdjunto.getMaeTipoArchivoId()) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            peAdjuntos.remove(borrarObj);
            objectoAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            objectoAdjunto.setExtensión(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objectoAdjunto.setNombre(nombreAdjunto.substring(0, indiceExtension));
            peAdjuntos.add(objectoAdjunto);
            objectoAdjunto = new PeAdjunto();
            PrimeFaces.current().ajax().update("frmAdjunto");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void descargarAdjuntoPrograma(PeAdjunto adjunto) {
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
            String attachmentName = "attachment; filename=\"" + adjunto.getArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else {
                throw new Exception();
            }
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    private void agregarTiempoGrowl(int tiempo) {
        UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmSelEmpBoton:mensajeGeneral");
        component.getAttributes().put("life", tiempo);
    }

    /**
     * *
     * Se encarga de devolver el color si aplica reste 0 = no aplica rescate 1 =
     * apliza pero no tiene rescate generado 2 = apliza rescate y tiene rescate
     * configurado
     *
     * @param idHospitalizacion
     * @return
     */
    public String establecerColorRescate(int idHospitalizacion) {
        String color = "white";
        try {
            setObjetoHospitalizacionAplicaRescate(new AucHospitalizacion());
            getObjetoHospitalizacionAplicaRescate().setId(idHospitalizacion);
            int aplicaRescate = getAucHospitalizacionServicio().consultarSiAplicaRescate(this);
            switch (aplicaRescate) {
                case 1:
                    color = "red";
                    break;
                case 2:
                    color = "green";
                    break;
                case 3:
                    color = "orange";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            this.addError("Ocurrio un error color rescate");
        }
        return color;
    }
    
    public void eventoHabilitarDescripcionIngresoPrevenible() {
        if (getObjeto().getAucIngresoId().getMaeCausaIngresoPrevalenteId() != null) {
            getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(1);
            this.setCampoObligatorioAreaResponsableIngresoPrevenible(true);
            this.setHabilitarAreaResponsableIngresoPrevenible(false);
        } else {
            getObjeto().getAucIngresoId().setMaeAreaIngresoPrevenibleId(null);
            getObjeto().getAucIngresoId().setDescripcionIngresoPrevenible(null);
            getObjeto().getAucIngresoId().setHabilitarDescripcionIngresoPrevenible(0);
            this.setCampoObligatorioAreaResponsableIngresoPrevenible(false);
            this.setHabilitarAreaResponsableIngresoPrevenible(true);
        }
        PrimeFaces.current().resetInputs("frmCrear:areaResponsableIngresoPrevenible");
        PrimeFaces.current().ajax().update("frmCrear:areaResponsableIngresoPrevenible");
        PrimeFaces.current().ajax().update("frmCrear:pIngresoDescripcionÁreaResponsableIngresoPrevenibleCrear");
        PrimeFaces.current().resetInputs("frmGestionar:areaResponsableIngresoPrevenible");
        PrimeFaces.current().ajax().update("frmGestionar:areaResponsableIngresoPrevenible");
        PrimeFaces.current().resetInputs("frmEditar:areaResponsableIngresoPrevenible");
        PrimeFaces.current().ajax().update("frmEditar:areaResponsableIngresoPrevenible");
        //PrimeFaces.current().ajax().update("frmEditar:especialidadEgresoEditar");
    }
    
    public boolean inabilitarCampoDescripcionIngresoPrevenible(){
        boolean validar = false;
        if(getObjeto().getAucIngresoId().getHabilitarDescripcionIngresoPrevenible() == 0 && !bloquearBotonesCierreAuditoria){
            validar = true;
        }else if(getObjeto().getAucIngresoId().getHabilitarDescripcionIngresoPrevenible() == 1 && bloquearBotonesCierreAuditoria){
            validar = true;
        }else if(getObjeto().getAucIngresoId().getHabilitarDescripcionIngresoPrevenible() == 0 && bloquearBotonesCierreAuditoria){
            validar = true;
        }
        return validar;
    }
    
    public void validarMotoSuperiorACienMil(){
        //getObjeto().getAucEgresoId().setHabilitarADvertenciaValorEstancia(0);
        BigDecimal cienMil = new BigDecimal(100000000); 
        if(getObjeto().getAucEgresoId().getValorEstancia() != null){
            if (getObjeto().getAucEgresoId().getValorEstancia().compareTo(cienMil) == 1) {
                agregarTiempoGrowl(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.TIEMP_MENS_DEFEC)));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "El valor supera los $100.000.000, por favor verifique si es correcto antes de guardar"));
                //getObjeto().getAucEgresoId().setHabilitarADvertenciaValorEstancia(1);
            }
        }
        
    }
}
