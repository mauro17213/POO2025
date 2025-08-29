package com.saviasaludeps.savia.web.recobro.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaSelectorOperacionMasiva;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoFactura;
import com.saviasaludeps.savia.dominio.recobro.RcoFacturaDetalle;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupo;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.recobro.servicio.RecobroFacturaServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class RecobroFacturaBean extends Url {

    @Autowired
    private RecobroFacturaServicioIface rcoFacturaServicio;
    private RcoFactura objeto;
    private RcoFacturaDetalle objFacturaDetalle;
    private RefAnexo9 objetoRefAnexo9;
    private AsegAfiliado asegAfiliadoId;
    private RcoGrupo objGrupo;
    private PePrograma peProgramaId;
    private CntPrestadorSede cntPresdadorSedeId;
    private Maestro maestroId;
    private CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasiva;

    private LazyDataModel<RcoFactura> lazyRcoFacturas;
    private LazyDataModel<RcoFacturaDetalle> lazyRcoFacturasDetalles;
    private LazyDataModel<RcoFacturaDetalle> lazyVerRcoFacturasDetalles;
    private LazyDataModel<CntPrestadorSede> lazyIPS;
    private LazyDataModel<RcoGrupo> lazyRcoGrupos;
    private LazyDataModel<CntPrestadorSede> lazyIPSMasivo;
    private LazyDataModel<RcoGrupo> lazyRcoGruposMasivo;

    private List<RcoFactura> registros;
    private List<RcoFacturaDetalle> registrosFacturaDetalles;
    private List<RcoFacturaDetalle> registrosNoAplicanRecobro;
    private List<RcoFacturaDetalle> registrosDetalles;
    private List<RcoFacturaDetalle> registroDetalleFacturaSeleccionadoMasivos;
    private List<RcoGrupo> registrosGrupos;
    private List<CntPrestadorSede> registroIPS;
    private List<RcoGrupo> registrosGruposMasivo;
    private List<CntPrestadorSede> registroIPSMasivo;
    private List<Ubicacion> listaUbicacion;
    private List<PePrograma> listaPePrograma;
    private List<Maestro> listaTiposDocumentoEmpresa;
    private List<Maestro> listaTipoDocumento;
    private List<Maestro> listaEstadoFactura;
    private List<Maestro> listaEstadoConciliacion;
    private List<Maestro> listaEstadoAuditoria;

    private HashMap<Integer, Ubicacion> hashUbicacion;
    private HashMap<Integer, RcoFacturaDetalle> hashFacturas;
    private HashMap<Integer, RcoFacturaDetalle> hashDetallesSeleccionados;
    private HashMap<Integer, RcoFacturaDetalle> hashFacturasAuditadas;
    private HashMap<Integer, RcoFacturaDetalle> hashDetalleFacturaSeleccionadoMasivos;

    private String dialogo;
    private String mensajeGeneral;
    private String verDetalle;
    private String observacionRecobroMasivo;
    private boolean seleccionarFacturas = false;

    private boolean aplicaRecobroGestionar = true;
    private boolean deshabilitarbuttomsGestionar = false;
    private boolean deshabilitarObligatoriedadGestionar = true;

    private boolean aplicaRecobroMasivo = true;
    private boolean deshabilitarbuttomsMasivo = false;
    private boolean deshabilitarObligatoriedadMasivo = true;

    public static final String DIALOGO_CREAR = "Crear";
    public static final char ACCION_GUARDAR_GESTIONAR_MASIVO = 'Q';
    public static final char ACCION_GUARDAR_GESTIONAR_TOTAL = 'Z';
    public final static int GESTION_MASIVA_PARCIAL = 0;
    public final static int GESTION_MASIVA_TOTAL = 1;

    private List<PePrograma> listaProgramas;

    public String getVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle(String verDetalle) {
        this.verDetalle = verDetalle;
    }

    public RecobroFacturaBean() {
        this.objeto = new RcoFactura();
        Modulo _mod = super.validarModulo(Modulo.ID_RCO_FACTURAS);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyRcoFacturas = new LazyDataModel<RcoFactura>() {

                private List<RcoFactura> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(0).getCantidadRegistros();
                }

                @Override
                public List<RcoFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(RcoFactura objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public RcoFactura getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (RcoFactura objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };

            lazyRcoFacturasDetalles = new LazyDataModel<RcoFacturaDetalle>() {
                private List<RcoFacturaDetalle> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(1).getCantidadRegistros();
                }

                @Override
                public List<RcoFacturaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarFacturaDetalle();
                    if (getRegistrosFacturaDetalles() != null) {
                        for (RcoFacturaDetalle detalle : getRegistrosFacturaDetalles()) {
                            if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
                                getRegistroDetalleFacturaSeleccionadoMasivos().add(detalle);
                            }
                        }
                    }
                    lista = getRegistrosFacturaDetalles();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(RcoFacturaDetalle objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public RcoFacturaDetalle getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (RcoFacturaDetalle objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };
            //listar IPS
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
            //Listar grupos de Recobro grupos
            lazyRcoGrupos = new LazyDataModel<RcoGrupo>() {

                private List<RcoGrupo> listaGrupos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(3).getCantidadRegistros();
                }

                @Override
                public List<RcoGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(3).setPrimerRegistro(primerRegistro);
                    getParamConsulta(3).setRegistrosPagina(registrosPagina);
                    getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarGrupos();
                    listaGrupos = getRegistrosGrupos();
                    setRowCount(getParamConsulta(3).getCantidadRegistros());
                    return listaGrupos;
                }

                @Override
                public String getRowKey(RcoGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public RcoGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (RcoGrupo objeto : listaGrupos) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };

            //listar IPS Masivo
            lazyIPSMasivo = new LazyDataModel<CntPrestadorSede>() {
                private List<CntPrestadorSede> listaIPSMasivo;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(4).getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(4).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(4).setPrimerRegistro(primerRegistro);
                    getParamConsulta(4).setRegistrosPagina(registrosPagina);
                    getParamConsulta(4).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(4).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarIpsMasivo();
                    listaIPSMasivo = getRegistroIPS();
                    setRowCount(getParamConsulta(4).getCantidadRegistros());
                    return listaIPSMasivo;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntPrestadorSede ips : listaIPSMasivo) {
                        if (id.equals(ips.getId())) {
                            return ips;
                        }
                    }
                    return null;
                }
            };
            //Listar grupos de Recobro grupos
            lazyRcoGruposMasivo = new LazyDataModel<RcoGrupo>() {

                private List<RcoGrupo> listaGruposMasivo;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(5).getCantidadRegistros();
                }

                @Override
                public List<RcoGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(5).setPrimerRegistro(primerRegistro);
                    getParamConsulta(5).setRegistrosPagina(registrosPagina);
                    getParamConsulta(5).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(5).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarGruposMasivo();
                    listaGruposMasivo = getRegistrosGrupos();
                    setRowCount(getParamConsulta(5).getCantidadRegistros());
                    return listaGruposMasivo;
                }

                @Override
                public String getRowKey(RcoGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public RcoGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (RcoGrupo objeto : listaGruposMasivo) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };
            // Listar del ver Recobro
            lazyVerRcoFacturasDetalles = new LazyDataModel<RcoFacturaDetalle>() {
                private List<RcoFacturaDetalle> listaDetallesMasiva;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(6).getCantidadRegistros();
                }

                @Override
                public List<RcoFacturaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(6).setPrimerRegistro(primerRegistro);
                    getParamConsulta(6).setRegistrosPagina(registrosPagina);
                    getParamConsulta(6).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(6).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    verRefrescarFacturaDetalle();
                    listaDetallesMasiva = getRegistrosFacturaDetalles();
                    setRowCount(getParamConsulta(6).getCantidadRegistros());
                    return listaDetallesMasiva;
                }

                @Override
                public String getRowKey(RcoFacturaDetalle objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public RcoFacturaDetalle getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (RcoFacturaDetalle objeto : listaDetallesMasiva) {
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
        getRcoFacturaServicio().cargaInicial(this);
        listar();
    }

    public List<Maestro> getListaEstadoFactura() {
        return listaEstadoFactura;
    }

    public void setListaEstadoFactura(List<Maestro> listaEstadoFactura) {
        this.listaEstadoFactura = listaEstadoFactura;
    }

    public List<Maestro> getListaEstadoConciliacion() {
        return listaEstadoConciliacion;
    }

    public void setListaEstadoConciliacion(List<Maestro> listaEstadoConciliacion) {
        this.listaEstadoConciliacion = listaEstadoConciliacion;
    }

    public List<Maestro> getListaEstadoAuditoria() {
        return listaEstadoAuditoria;
    }

    public void setListaEstadoAuditoria(List<Maestro> listaEstadoAuditoria) {
        this.listaEstadoAuditoria = listaEstadoAuditoria;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public boolean isDeshabilitarbuttomsGestionar() {
        return deshabilitarbuttomsGestionar;
    }

    public void setDeshabilitarbuttomsGestionar(boolean deshabilitarbuttomsGestionar) {
        this.deshabilitarbuttomsGestionar = deshabilitarbuttomsGestionar;
    }

    public boolean isDeshabilitarObligatoriedadGestionar() {
        return deshabilitarObligatoriedadGestionar;
    }

    public void setDeshabilitarObligatoriedadGestionar(boolean deshabilitarObligatoriedadGestionar) {
        this.deshabilitarObligatoriedadGestionar = deshabilitarObligatoriedadGestionar;
    }

    public boolean isDeshabilitarbuttomsMasivo() {
        return deshabilitarbuttomsMasivo;
    }

    public void setDeshabilitarbuttomsMasivo(boolean deshabilitarbuttomsMasivo) {
        this.deshabilitarbuttomsMasivo = deshabilitarbuttomsMasivo;
    }

    public boolean isDeshabilitarObligatoriedadMasivo() {
        return deshabilitarObligatoriedadMasivo;
    }

//Getter And Setter
    public void setDeshabilitarObligatoriedadMasivo(boolean deshabilitarObligatoriedadMasivo) {
        this.deshabilitarObligatoriedadMasivo = deshabilitarObligatoriedadMasivo;
    }

    public LazyDataModel<RcoFacturaDetalle> getLazyVerRcoFacturasDetalles() {
        return lazyVerRcoFacturasDetalles;
    }

    public void setLazyVerRcoFacturasDetalles(LazyDataModel<RcoFacturaDetalle> lazyVerRcoFacturasDetalles) {
        this.lazyVerRcoFacturasDetalles = lazyVerRcoFacturasDetalles;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIPSMasivo() {
        return lazyIPSMasivo;
    }

    public void setLazyIPSMasivo(LazyDataModel<CntPrestadorSede> lazyIPSMasivo) {
        this.lazyIPSMasivo = lazyIPSMasivo;
    }

    public LazyDataModel<RcoGrupo> getLazyRcoGruposMasivo() {
        return lazyRcoGruposMasivo;
    }

    public List<RcoGrupo> getRegistrosGruposMasivo() {
        return registrosGruposMasivo;
    }

    public void setRegistrosGruposMasivo(List<RcoGrupo> registrosGruposMasivo) {
        this.registrosGruposMasivo = registrosGruposMasivo;
    }

    public List<CntPrestadorSede> getRegistroIPSMasivo() {
        return registroIPSMasivo;
    }

    public void setRegistroIPSMasivo(List<CntPrestadorSede> registroIPSMasivo) {
        this.registroIPSMasivo = registroIPSMasivo;
    }

    public void setLazyRcoGruposMasivo(LazyDataModel<RcoGrupo> lazyRcoGruposMasivo) {
        this.lazyRcoGruposMasivo = lazyRcoGruposMasivo;
    }

    public PePrograma getPeProgramaId() {
        if (peProgramaId == null) {
            peProgramaId = new PePrograma();
        }
        return peProgramaId;
    }

    public void setPeProgramaId(PePrograma peProgramaId) {
        this.peProgramaId = peProgramaId;
    }

    public List<RcoFacturaDetalle> getRegistrosNoAplicanRecobro() {
        return registrosNoAplicanRecobro;
    }

    public void setRegistrosNoAplicanRecobro(List<RcoFacturaDetalle> registrosNoAplicanRecobro) {
        this.registrosNoAplicanRecobro = registrosNoAplicanRecobro;
    }

    public CntPrestadorSede getCntPresdadorSedeId() {
        return cntPresdadorSedeId;
    }

    public void setCntPresdadorSedeId(CntPrestadorSede cntPresdadorSedeId) {
        this.cntPresdadorSedeId = cntPresdadorSedeId;
    }

    public List<PePrograma> getListaPePrograma() {
        return listaPePrograma;
    }

    public void setListaPePrograma(List<PePrograma> listaPePrograma) {
        this.listaPePrograma = listaPePrograma;
    }

    public List<PePrograma> getListaProgramas() {
        return listaProgramas;
    }

    public AsegAfiliado getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(AsegAfiliado asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
    }

    public void setListaProgramas(List<PePrograma> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public List<RcoFacturaDetalle> getRegistrosDetalles() {
        if (registrosDetalles == null) {
            registrosDetalles = new ArrayList<>();
        }
        return registrosDetalles;
    }

    public List<Ubicacion> getListaUbicacion() {
        return listaUbicacion;
    }

    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public RcoGrupo getObjGrupo() {
        return objGrupo;
    }

    public void setObjGrupo(RcoGrupo objGrupo) {
        this.objGrupo = objGrupo;
    }

    public void setRegistrosDetalles(List<RcoFacturaDetalle> registrosDetalles) {
        this.registrosDetalles = registrosDetalles;
    }

    public LazyDataModel<RcoGrupo> getLazyRcoGrupos() {
        return lazyRcoGrupos;
    }

    public void setLazyRcoGrupos(LazyDataModel<RcoGrupo> lazyRcoGrupos) {
        this.lazyRcoGrupos = lazyRcoGrupos;
    }

    public List<RcoGrupo> getRegistrosGrupos() {
        if (registrosGrupos == null) {
            registrosGrupos = new ArrayList<>();
        }
        return registrosGrupos;
    }

    public void setRegistrosGrupos(List<RcoGrupo> registrosGrupos) {
        this.registrosGrupos = registrosGrupos;
    }

    public LazyDataModel<RcoFacturaDetalle> getLazyRcoFacturasDetalles() {
        return lazyRcoFacturasDetalles;
    }

    public boolean isSeleccionarFacturas() {
        return seleccionarFacturas;
    }

    public void setSeleccionarFacturas(boolean seleccionarFacturas) {
        this.seleccionarFacturas = seleccionarFacturas;
    }

    public void setLazyRcoFacturasDetalles(LazyDataModel<RcoFacturaDetalle> lazyRcoFacturasDetalles) {
        this.lazyRcoFacturasDetalles = lazyRcoFacturasDetalles;
    }

    public RecobroFacturaServicioIface getRcoFacturaServicio() {
        return rcoFacturaServicio;
    }

    public void setRcoFacturaServicio(RecobroFacturaServicioIface rcoFacturaServicio) {
        this.rcoFacturaServicio = rcoFacturaServicio;
    }

    public RcoFactura getObjeto() {
        return objeto;
    }

    public void setObjeto(RcoFactura objeto) {
        this.objeto = objeto;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public List<RcoFactura> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RcoFactura> registros) {
        this.registros = registros;
    }

    public LazyDataModel<RcoFactura> getLazyRcoFacturas() {
        return lazyRcoFacturas;
    }

    public void setLazyRcoFacturas(LazyDataModel<RcoFactura> lazyRcoFacturas) {
        this.lazyRcoFacturas = lazyRcoFacturas;
    }

    public List<RcoFacturaDetalle> getRegistrosFacturaDetalles() {
        return registrosFacturaDetalles;
    }

    public void setRegistrosFacturaDetalles(List<RcoFacturaDetalle> registrosFacturaDetalles) {
        this.registrosFacturaDetalles = registrosFacturaDetalles;
    }

    public HashMap<Integer, RcoFacturaDetalle> getHashFacturasSelecionadas() {
        if (hashFacturas == null) {
            hashFacturas = new HashMap<>();
        }
        return hashFacturas;
    }

    public void setHashFacturasSelecionadas(HashMap<Integer, RcoFacturaDetalle> hashFacturasSelecionadas) {
        this.hashFacturas = hashFacturasSelecionadas;
    }

    public RcoFacturaDetalle getObjFacturaDetalle() {
        return objFacturaDetalle;
    }

    public void setObjFacturaDetalle(RcoFacturaDetalle objFacturaDetalle) {
        this.objFacturaDetalle = objFacturaDetalle;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIPS() {
        return lazyIPS;
    }

    public void setLazyIPS(LazyDataModel<CntPrestadorSede> lazyIPS) {
        this.lazyIPS = lazyIPS;
    }

    public String getDialogo() {
        return dialogo;
    }

    public void setDialogo(String dialogo) {
        this.dialogo = dialogo;
    }

    public List<CntPrestadorSede> getRegistroIPS() {
        return registroIPS;
    }

    public void setRegistroIPS(List<CntPrestadorSede> registroIPS) {
        this.registroIPS = registroIPS;
    }

    public RefAnexo9 getObjetoRefAnexo9() {
        return objetoRefAnexo9;
    }

    public void setObjetoRefAnexo9(RefAnexo9 objetoRefAnexo9) {
        this.objetoRefAnexo9 = objetoRefAnexo9;
    }

    public HashMap<Integer, Ubicacion> getHashUbicacion() {
        return hashUbicacion;
    }

    public void setHashUbicacion(HashMap<Integer, Ubicacion> hashUbicacion) {
        this.hashUbicacion = hashUbicacion;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public Maestro getMaestroId() {
        return maestroId;
    }

    public void setMaestroId(Maestro maestroId) {
        this.maestroId = maestroId;
    }

    public List<RcoFacturaDetalle> getRegistroDetalleFacturaSeleccionadoMasivos() {
        if (registroDetalleFacturaSeleccionadoMasivos == null) {
            registroDetalleFacturaSeleccionadoMasivos = new ArrayList<>();
        }
        return registroDetalleFacturaSeleccionadoMasivos;
    }

    public void setRegistroDetalleFacturaSeleccionadoMasivos(List<RcoFacturaDetalle> registroDetalleFacturaSeleccionadoMasivos) {
        this.registroDetalleFacturaSeleccionadoMasivos = registroDetalleFacturaSeleccionadoMasivos;
    }

    public HashMap<Integer, RcoFacturaDetalle> getHashDetalleFacturaSeleccionadoMasivos() {
        return hashDetalleFacturaSeleccionadoMasivos;
    }

    public void setHashDetalleFacturaSeleccionadoMasivos(HashMap<Integer, RcoFacturaDetalle> hashDetalleFacturaSeleccionadoMasivos) {
        this.hashDetalleFacturaSeleccionadoMasivos = hashDetalleFacturaSeleccionadoMasivos;
    }

    public CmAuditoriaSelectorOperacionMasiva getCmSelectorOperacionaMasiva() {
        return cmSelectorOperacionaMasiva;
    }

    public void setCmSelectorOperacionaMasiva(CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasiva) {
        this.cmSelectorOperacionaMasiva = cmSelectorOperacionaMasiva;
    }

    public HashMap<Integer, RcoFacturaDetalle> getHashDetallesSelecionados() {
        if (hashDetallesSeleccionados == null) {
            hashDetallesSeleccionados = new HashMap<>();
        }
        return hashDetallesSeleccionados;
    }

    public void setHashDetallesSeleccionados(HashMap<Integer, RcoFacturaDetalle> hashDetallesSeleccionados) {
        this.hashDetallesSeleccionados = hashDetallesSeleccionados;
    }

    public HashMap<Integer, RcoFacturaDetalle> getHashFacturasAuditadas() {
        if (hashFacturasAuditadas == null) {
            hashFacturasAuditadas = new HashMap<>();
        }
        return hashFacturasAuditadas;
    }

    public void setHashFacturasAuditadas(HashMap<Integer, RcoFacturaDetalle> hashFacturasAuditadas) {
        this.hashFacturasAuditadas = hashFacturasAuditadas;
    }

    public boolean isAplicaRecobroGestionar() {
        return aplicaRecobroGestionar;
    }

    public void setAplicaRecobroGestionar(boolean aplicaRecobroGestionar) {
        this.aplicaRecobroGestionar = aplicaRecobroGestionar;
    }

    public boolean isAplicaRecobroMasivo() {
        return aplicaRecobroMasivo;
    }

    public void setAplicaRecobroMasivo(boolean aplicaRecobroMasivo) {
        this.aplicaRecobroMasivo = aplicaRecobroMasivo;
    }

    public String getObservacionRecobroMasivo() {
        return observacionRecobroMasivo;
    }

    public void setObservacionRecobroMasivo(String observacionRecobroMasivo) {
        this.observacionRecobroMasivo = observacionRecobroMasivo;
    }

//Metodos 
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getRcoFacturaServicio().Accion(this);
    }

    public void gestionar(int id) {
        setObjeto(new RcoFactura(id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getRegistroDetalleFacturaSeleccionadoMasivos().clear();
        getHashDetallesSelecionados().clear();
        getRcoFacturaServicio().Accion(this);
        getHashFacturasAuditadas().clear();
        procesoFinal();
    }

    public void consultarIPS() {
        getRcoFacturaServicio().listarIPS(this);
        PrimeFaces.current().ajax().update("frmIpsLista");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
    }

    public void refrescarIps() {
        getRcoFacturaServicio().listarIPS(this);
        PrimeFaces.current().ajax().update("frmIpsLista");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
    }

    public void verGrupos() {
        getRcoFacturaServicio().listarGrupos(this);
        PrimeFaces.current().ajax().update("frmGrupoFactura");
        PrimeFaces.current().executeScript("PF('dlgGrupoFactura').show()");
    }

    public void refrescarGrupos() {
        getRcoFacturaServicio().listarGrupos(this);
        PrimeFaces.current().ajax().update("frmGrupoFactura");
        PrimeFaces.current().executeScript("PF('dlgGrupoFactura').show()");
    }

    public void consultarIPSMasivo() {
        getRcoFacturaServicio().listarIPSMasivo(this);
        PrimeFaces.current().ajax().update("frmIpsListaMasivo");
        PrimeFaces.current().executeScript("PF('dialogoIpsListaMasivo').show()");
    }

    public void refrescarIpsMasivo() {
        getRcoFacturaServicio().listarIPSMasivo(this);
        PrimeFaces.current().ajax().update("frmIpsListaMasivo");
    }

    public void verGruposMasivo() {
        getRcoFacturaServicio().listarGrupoMasivo(this);
        PrimeFaces.current().ajax().update("frmGrupoFacturaMasivo");
        PrimeFaces.current().executeScript("PF('dlgGrupoFacturaMasivo').show()");
    }

    public void refrescarGruposMasivo() {
        getRcoFacturaServicio().listarGrupoMasivo(this);
        PrimeFaces.current().ajax().update("frmGrupoFacturaMasivo");
    }

    public void ver(int id) {
        setObjeto(new RcoFactura(id));
        super.setAccion(ACCION_VER);
        getRcoFacturaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarFacturaDetalle() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getRcoFacturaServicio().Accion(this);
        procesoFinal();
    }

    public void verRefrescarFacturaDetalle() {
        super.setAccion(Url.ACCION_VER);
        getRcoFacturaServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmFacturas");

                    break;
                case Url.ACCION_ADICIONAL_1:
                    PrimeFaces.current().ajax().update("frmGestionarFactura");
                    PrimeFaces.current().executeScript("PF('dialogoGestionarFactura').show()");
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVerFactura");
                    PrimeFaces.current().executeScript("PF('dialogoVerFactura').show()");
                    break;
            }
        }
        generarMensajes();
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : getListaUbicacion()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjetoRefAnexo9().setAcompananteMunicipio(ubicacionesFiltradas.get(0).getNombre());
            getObjetoRefAnexo9().setAcompananteDepartamento(ubicacionesFiltradas.get(0).getUbicacionPadre().getNombre());
        }
        return ubicacionesFiltradas;
    }

    public void mostrarMensajeGeneral(String mensaje) {
        setMensajeGeneral(mensaje);
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void verDetalledeDetalleFactura(int id) {
        setObjFacturaDetalle(new RcoFacturaDetalle(id));
        setCntPresdadorSedeId(new CntPrestadorSede());
        setObjGrupo(new RcoGrupo());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_10);
        getRcoFacturaServicio().Accion(this);
        if (getErrores().size() >= 1) {
            generarMensajes();
            return;
        }
        PrimeFaces.current().ajax().update("frmVerFacturaAuditada");
        PrimeFaces.current().executeScript("PF('dialogoVerFacturaAuditada').show();");
    }
    
    public void gestionarDetalleFactura(int id) {
        setObjFacturaDetalle(new RcoFacturaDetalle(id));
        setCntPresdadorSedeId(new CntPrestadorSede());
        setObjGrupo(new RcoGrupo());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
        getRcoFacturaServicio().Accion(this);
        onChangeAplicaRecobroPorGestion();
        PrimeFaces.current().ajax().update("frmGestionarDetalleFactura");
        PrimeFaces.current().executeScript("PF('dialogoGestionarDetalleFactura').show()");

    }

    public void modificar() {
        setMaestroId(new Maestro(62181));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_6);
        getRcoFacturaServicio().Accion(this);
        if (getErrores().size() >= 1) {
            PrimeFaces.current().ajax().update("frmGestionarDetalleFactura");
            generarMensajes();
        }
        generarMensajes();
        if (getHashFacturasAuditadas().get(objFacturaDetalle.getId()) == null) {
            getHashFacturasAuditadas().put(objFacturaDetalle.getId(), objFacturaDetalle);
        }
        limpiarValores();
        PrimeFaces.current().executeScript("PF('dialogoGestionarDetalleFactura').hide();");
        refrescarFacturaDetalle();

    }

    public void limpiarValores() {
        setObjGrupo(new RcoGrupo());
        setPeProgramaId(new PePrograma());
        setCntPresdadorSedeId(new CntPrestadorSede());
    }

    public void seleccionarPePrograma(int id) {
        objFacturaDetalle.setPeProgramaId(new PePrograma(id));
        getRcoFacturaServicio().seleccionarPePrograma(this);
        if (getErrores().size() > 1) {
            generarMensajes();
        }

    }

    public void seleccionarPeProgramaMasivo(int id) {
        peProgramaId = new PePrograma(id);
        getRcoFacturaServicio().seleccionarPePrograma(this);
        if (getErrores().size() > 1) {
            generarMensajes();
        }

    }

    public void calcularPendiente() {
        BigDecimal valorFacturado = BigDecimal.valueOf(getObjFacturaDetalle().getValorFacturado());
        BigDecimal valorTotalRecobro = getObjFacturaDetalle().getValorTotalRecobro();
        getObjFacturaDetalle().setValorRestanteRecobro(valorFacturado.subtract(valorTotalRecobro));
    }

    public void causalRecobro() {
        if (getObjFacturaDetalle().isCausalRecobro() == true) {
            BigDecimal valorFacturado = BigDecimal.valueOf(getObjFacturaDetalle().getValorFacturado());
            getObjFacturaDetalle().setValorTotalRecobro(valorFacturado);
            getObjFacturaDetalle().setValorRestanteRecobro(valorFacturado.subtract(getObjFacturaDetalle().getValorTotalRecobro()));
        } else {
            getObjFacturaDetalle().setValorTotalRecobro(BigDecimal.valueOf(0));
            getObjFacturaDetalle().setValorRestanteRecobro(BigDecimal.valueOf(getObjFacturaDetalle().getValorFacturado()));
        }

    }

    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
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

    public void verSeleccionOperacionMasiva(int idFactura, int tipoGestionMasiva) {
        objeto.setPeProgramaId(new PePrograma());
        objFacturaDetalle = new RcoFacturaDetalle();
        setCmSelectorOperacionaMasiva(new CmAuditoriaSelectorOperacionMasiva());
        getRegistroDetalleFacturaSeleccionadoMasivos().clear();

        if (GESTION_MASIVA_PARCIAL == tipoGestionMasiva) {
            setRegistroDetalleFacturaSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values()));
            if (getRegistroDetalleFacturaSeleccionadoMasivos().isEmpty()) {
                this.addError("Para realizar la operación debe seleccionar facturas");
            }
            for (RcoFacturaDetalle detallesAuditados : getRegistroDetalleFacturaSeleccionadoMasivos()) {
                if ("auditado".equals(detallesAuditados.getMaeEstadoValor())) {
                    this.addError("Hay facturas auditadas en la seleccion, favor revisar");
                }
            }
        } else {
        }

        if (!isError()) {
            getCmSelectorOperacionaMasiva().setIdFactura(idFactura);
            getCmSelectorOperacionaMasiva().setTipoGestionMasiva(tipoGestionMasiva);
            limpiarValores();
            PrimeFaces.current().ajax().update("frmGestionarDetalleFacturaMasivo");
            PrimeFaces.current().executeScript("PF('dialogoGestionarDetalleFacturaMasivo').show()");
        } else {
            generarMensajes();
        }
    }

    public void salirGestinarDetalleFactura() {
        PrimeFaces.current().resetInputs("frmGestionarDetalleFactura");
        PrimeFaces.current().ajax().update("frmGestionarDetalleFactura");
        PrimeFaces.current().executeScript("PF('dialogoGestionarDetalleFactura').hide();");
    }

    public void gestionarMasivoPorFactura() {
        if (aplicaRecobroMasivo == true) {
            if (objGrupo.getId() != null && cntPresdadorSedeId.getId() != null) {
                setMaestroId(new Maestro(62181));
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_GUARDAR_GESTIONAR_MASIVO);
                getRcoFacturaServicio().Accion(this);
            } else {
                addError("Hay campos obligatorios sin llenar");
            }
        } else {
            setMaestroId(new Maestro(62181));
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_GUARDAR_GESTIONAR_MASIVO);
            getRcoFacturaServicio().Accion(this);
        }
        if (getErrores().size() >= 1) {
            generarMensajes();
            return;
        } else {
            PrimeFaces.current().executeScript("PF('dialogoGestionarDetalleFacturaMasivo').hide();");
            addMensaje("Facturas gestionadas con exito");
            getRegistroDetalleFacturaSeleccionadoMasivos().clear();
            refrescarFacturaDetalle();
            generarMensajes();
        }

    }

    public void gestionarMasivoTodas() {
        if (aplicaRecobroMasivo == true) {
            if (objGrupo.getId() != null && cntPresdadorSedeId.getId() != null) {
                setMaestroId(new Maestro(62181));
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_GUARDAR_GESTIONAR_TOTAL);
                getRcoFacturaServicio().Accion(this);
            } else {
                addError("Hay campos obligatorios sin llenar");
            }
        } else {
            setMaestroId(new Maestro(62181));
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_GUARDAR_GESTIONAR_TOTAL);
            getRcoFacturaServicio().Accion(this);
        }
        if (getErrores().size() >= 1) {
            generarMensajes();
            return;
        } else {
            PrimeFaces.current().executeScript("PF('dialogoGestionarDetalleFacturaMasivo').hide();");
            addMensaje("Facturas gestionadas con exito");
            refrescarFacturaDetalle();
            generarMensajes();
        }
    }

    public void salirAuditoriaFacturas() {
        int contadorFacturasAuditadas = 0;
        int contadorRegistroDellates = 0;

        if (getHashFacturasAuditadas().isEmpty()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionarFactura').hide();");
            super.setAccion(ACCION_LISTAR);
            procesoFinal();
            return;
        }

        for (RcoFacturaDetalle detalle : getHashFacturasAuditadas().values()) {
            if (detalle.isAplicaRecobro() == false) {
                contadorFacturasAuditadas++;
            }
        }
        if ("pendiente auditoria".equals(getObjeto().getMaeEstadoRecobroValor())) {

            for (RcoFacturaDetalle detalle : getRegistrosFacturaDetalles()) {
                if (detalle.isAplicaRecobro() == false) {
                    contadorRegistroDellates++;
                }
            }
            if (contadorRegistroDellates == contadorFacturasAuditadas) {
                objeto.setMaeEstadoRecobroId(62042);
                objeto.setMaeEstadoRecobroCodigo("4");
                objeto.setMaeEstadoRecobroValor("auditada - no recobro");

            } else if (contadorFacturasAuditadas < getHashFacturasAuditadas().size()) {
                objeto.setMaeEstadoRecobroId(62041);
                objeto.setMaeEstadoRecobroCodigo("3");
                objeto.setMaeEstadoRecobroValor("auditada - potencial recobro");
            } else {
                objeto.setMaeEstadoRecobroId(62040);
                objeto.setMaeEstadoRecobroCodigo("2");
                objeto.setMaeEstadoRecobroValor("en auditoria");
            }
            getRcoFacturaServicio().actualizarEstadoFactura(this);
        }
        generarMensajes();
        PrimeFaces.current().executeScript("PF('dialogoGestionarFactura').hide();");
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void onRowSelectGrupo(SelectEvent event) {
        objGrupo = (RcoGrupo) event.getObject();
        getRcoFacturaServicio().elegirGrupo(this);
        objFacturaDetalle.setRcoGruposId(objGrupo);
        if (getErrores().size() >= 1) {
            generarMensajes();
            return;
        }
        PrimeFaces.current().executeScript("PF('dlgGrupoFactura').hide();");
        PrimeFaces.current().ajax().update("frmGestionarDetalleFactura:columGrupo");
    }

    public void onRowSelectIPS(SelectEvent event) {
        cntPresdadorSedeId = (CntPrestadorSede) event.getObject();
        getRcoFacturaServicio().elegirIPS(this);
        objFacturaDetalle.setCntPrestadoresSedesId(cntPresdadorSedeId);
        if (getErrores().size() >= 1) {
            generarMensajes();
            return;
        }
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide();");
        PrimeFaces.current().ajax().update("frmGestionarDetalleFactura:actualizarIPS");
    }

    public void onChangeAplicaRecobroMasivo() {
        if (aplicaRecobroMasivo == true) {
            deshabilitarbuttomsMasivo = false;
            deshabilitarObligatoriedadMasivo = true;
        } else {
            deshabilitarbuttomsMasivo = true;
            deshabilitarObligatoriedadMasivo = false;
            limpiarValores();
        }
    }

    public void onChangeAplicaRecobroPorGestion() {
        if (objFacturaDetalle.isAplicaRecobro() == true) {
            deshabilitarbuttomsGestionar = true;
            deshabilitarObligatoriedadGestionar = false;
        } else {
            deshabilitarbuttomsGestionar = false;
            deshabilitarObligatoriedadGestionar = true;

            limpiarValores();
        }
    }

    public void onRowSelectGrupoMasivo(SelectEvent event) {
        objGrupo = (RcoGrupo) event.getObject();
        getRcoFacturaServicio().elegirGrupo(this);
        if (getErrores().size() >= 1) {
            generarMensajes();
            return;
        }
        PrimeFaces.current().executeScript("PF('dlgGrupoFacturaMasivo').hide();");
        PrimeFaces.current().ajax().update("frmGestionarDetalleFacturaMasivo:columGrupoMasivo");
    }

    public void onRowSelectIPSMasivo(SelectEvent event) {
        cntPresdadorSedeId = (CntPrestadorSede) event.getObject();
        getRcoFacturaServicio().elegirIPS(this);
        if (getErrores().size() >= 1) {
            generarMensajes();
            return;
        }
        PrimeFaces.current().executeScript("PF('dialogoIpsListaMasivo').hide();");
        PrimeFaces.current().ajax().update("frmGestionarDetalleFacturaMasivo:actualizarIPSMasivo");
    }

    public void onCheckearTodosItems(ToggleSelectEvent event) {
        if (event.isSelected()) {
            for (RcoFacturaDetalle detalle : getRegistroDetalleFacturaSeleccionadoMasivos()) {

                if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
                    getHashDetallesSelecionados().put(detalle.getId(), detalle);
                }
            }
        } else {
            for (RcoFacturaDetalle detalle : getRegistrosFacturaDetalles()) {
                if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
                    getHashDetallesSelecionados().remove(detalle.getId());
                }
            }
        }
    }

    public void onCheckearItem(SelectEvent event) {
        RcoFacturaDetalle detalle = (RcoFacturaDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
            getHashDetallesSelecionados().put(detalle.getId(), detalle);
        }
    }

    public void onDescheckearItem(UnselectEvent event) {
        RcoFacturaDetalle detalle = (RcoFacturaDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
            getHashDetallesSelecionados().remove(detalle.getId());
        }
    }

    public void onSeleccionarFilaMasiva(SelectEvent event) {
        RcoFacturaDetalle detalle = (RcoFacturaDetalle) event.getObject();

        for (RcoFacturaDetalle detalleIn : getRegistroDetalleFacturaSeleccionadoMasivos()) {
            if (getHashDetallesSelecionados().get(detalleIn.getId()) != null) {
                getHashDetallesSelecionados().remove(detalleIn.getId());
            }
        }

        if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
            getHashDetallesSelecionados().put(detalle.getId(), detalle);
        }
    }

    public void onDeseleccionarFilaMasiva(SelectEvent event) {
        RcoFacturaDetalle detalle = (RcoFacturaDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
            getHashDetallesSelecionados().remove(detalle.getId());
        }
    }

}
