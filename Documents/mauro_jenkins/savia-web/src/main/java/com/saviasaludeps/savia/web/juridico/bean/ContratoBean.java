package com.saviasaludeps.savia.web.juridico.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoGarantia;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoIndicador;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoInforme;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoInformeAdjunto;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoObligacion;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSeguimiento;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosi;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosiAdjunto;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.web.juridico.servicio.ContratoServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
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
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class ContratoBean extends Url {

    private ContratoServicioIface cntjContratoServicio;

    private CntjContrato objeto;
    private LazyDataModel<CntjContrato> lazyContrato;
    private LazyDataModel<Usuario> lazyUsuarios;
    private LazyDataModel<CntjDocumento> lazyDocumentos;
    private LazyDataModel<CntjTercero> lazyTerceros;
    private LazyDataModel<CntjOtrosi> lazyOtroSi;

    private List<CntjContrato> registros;
    private List<CntjDocumento> listaDocumentos;
    private List<Usuario> listaUsuario;
    private List<CntjTercero> registrosTerceros;
    private Integer tipoSelectTercero;
    private List<Maestro> listaTipoDocumentoTercero;
    private List<Maestro> listaTipoTercero;
    private List<Maestro> listaEstadoContrato;
    private HashMap<Integer, Maestro> hashlistaEstadoContrato;
    private List<CntjOtrosiAdjunto> adjuntosOtroSi;
    private List<Maestro> listaTipoOtrosi;
    private CntjOtrosiAdjunto objetoOtrosiAdjunto;
    private List<CntjOtrosiAdjunto> listaAdjuntoeliminar;
    private List<CntjOtrosi> registrosOtrosi;
    private List<Maestro> listaEstadoOtrosi;
    private List<Maestro> listaTipoArchivo;
    private HashMap<Integer, Maestro> hashlistaTipoArchivo;
    private List<Maestro> listaClaseContrato;
    private HashMap<Integer, Maestro> hashlistaClaseContrato;
    private boolean requierePlazo;
    private boolean requiereValor;
    private boolean requiereFechas;
    private boolean habilitarMotivoSuspension;
    private List<Maestro> listaGarantiaContrato;
    private HashMap<Integer, Maestro> hashlistaGarantiaContrato;
    private List<CntjContratoGarantia> listaGarantiaeliminar;
    private List<CntjContratoIndicador> listaIndicadorEliminar;
    private List<CntjContratoObligacion> listaObligacionEliminar;
    private List<CntjContratoSupervisor> listaSupervisorEliminar;
    private List<Maestro> listaIndicadorContrato;
    private HashMap<Integer, Maestro> hashlistaIndicadorContrato;
    private List<CntjProceso> listaProcesos;
    private HashMap<Integer, CntjProceso> hashlistaProceso;
    private List<Maestro> listaEstadoLegalizacion;
    private List<Maestro> listaModalidad;
    private HashMap<Integer, Maestro> hashlistaModalidad;
    private List<Maestro> listaRegimen;
    private HashMap<Integer, Maestro> hashlistaRegimen;
    private List<Maestro> listaComplejidad;
    private List<Maestro> listaFormaDePago;
    private List<Maestro> listaTipoAnticipo;
    private double minimoPlazoDia;
    private HashMap<Integer, List<Maestro>> hashlistaValidacionGarantia;
    private Date fechaHastaGarantia;
    private List<Maestro> listaTipoArchivoAdjuntoInforme;
    private HashMap<Integer, Maestro> hashTipoArchivoAdjuntoInforme;
    private List<Maestro> listaTipoGasto;
    private List<Maestro> listaTipoInforme;
    private List<Maestro> listaEstadoInforme;
    private Integer diasProrrogaAnterior;
    private BigDecimal valorAdicionAnterior;
    private Date fechaFinAnterior;
    private List<Maestro> listaPeriodicidadSeguimiento;
    private List<Maestro> listaEstadoLegalizacionOtrosi;
    private boolean contratoVigente;
    private CntjDocumento objDocumento;
    private String contenidoPdf;
    private boolean expedienteContrato;

    //Objetos
    private CntjOtrosi objetoOtrosi;
    private CntjContratoSupervisor objetoSupervisor;
    private CntjContratoGarantia objetoGarantia;
    private CntjContratoObligacion objetoObligacion;
    private CntjContratoIndicador objetoIndicador;
    private CntjContratoSeguimiento objetoSeguimiento;
    private CntjContratoInforme objetoInforme;
    private CntjContratoInformeAdjunto objetoAdjuntoInforme;

    public ContratoBean() {
        this.objeto = new CntjContrato();
        this.objetoOtrosi = new CntjOtrosi();
        this.objetoOtrosiAdjunto = new CntjOtrosiAdjunto();
        this.objetoGarantia = new CntjContratoGarantia();
        this.objetoIndicador = new CntjContratoIndicador();
        this.objetoObligacion = new CntjContratoObligacion();
        this.objetoSeguimiento = new CntjContratoSeguimiento();
        this.objetoInforme = new CntjContratoInforme();
        this.objetoAdjuntoInforme = new CntjContratoInformeAdjunto();
        this.objDocumento = new CntjDocumento();
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_CONTRATOS);
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.setModulo(_mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            lazyContrato = new LazyDataModel<CntjContrato>() {

                private List<CntjContrato> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntjContrato> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CntjContrato objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjContrato getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjContrato objeto : lista) {
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
        getCntjContratoServicio().cargaInicial(this);
        listar();
    }

    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">

    public boolean getExpedienteContrato() {
        return expedienteContrato;
    }

    public void setExpedienteContrato(boolean expedienteContrato) {
        this.expedienteContrato = expedienteContrato;
    }
    
    public String getContenidoPdf() {
        return contenidoPdf;
    }

    public void setContenidoPdf(String contenidoPdf) {
        this.contenidoPdf = contenidoPdf;
    }

    public CntjDocumento getObjDocumento() {
        return objDocumento;
    }

    public void setObjDocumento(CntjDocumento objDocumento) {
        this.objDocumento = objDocumento;
    }

    public String getTipoDocumento(Integer id) {
        return CntjConstantes.getTipoDocumento(id);
    }

    public List<CntjDocumento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<CntjDocumento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public LazyDataModel<CntjDocumento> getLazyDocumentos() {
        return lazyDocumentos;
    }

    public void setLazyDocumentos(LazyDataModel<CntjDocumento> lazyDocumentos) {
        this.lazyDocumentos = lazyDocumentos;
    }

    public String getBooleanStr(boolean valor) {
        if (valor) {
            return "Si";
        }
        return "N0";
    }

    public String getEstadoLegaizacionOtrosi(Integer id) {
        return CntjConstantes.getEstadoLegalizacionOtrosiStr(id);
    }

    public List<Maestro> getListaEstadoLegalizacionOtrosi() {
        return listaEstadoLegalizacionOtrosi;
    }

    public void setListaEstadoLegalizacionOtrosi(List<Maestro> listaEstadoLegalizacionOtrosi) {
        this.listaEstadoLegalizacionOtrosi = listaEstadoLegalizacionOtrosi;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public HashMap<Integer, CntjProceso> getHashlistaProceso() {
        return hashlistaProceso;
    }

    public void setHashlistaProceso(HashMap<Integer, CntjProceso> hashlistaProceso) {
        this.hashlistaProceso = hashlistaProceso;
    }

    public boolean isContratoVigente() {
        return contratoVigente;
    }

    public void setContratoVigente(boolean contratoVigente) {
        this.contratoVigente = contratoVigente;
    }

    public String getPeridicidadSeguimientoStr(Integer id) {
        return CntjConstantes.getPeriodoSeguimiento(id);
    }

    public List<Maestro> getListaPeriodicidadSeguimiento() {
        return listaPeriodicidadSeguimiento;
    }

    public void setListaPeriodicidadSeguimiento(List<Maestro> listaPeriodicidadSeguimiento) {
        this.listaPeriodicidadSeguimiento = listaPeriodicidadSeguimiento;
    }

    public List<Maestro> getListaEstadoInforme() {
        return listaEstadoInforme;
    }

    public void setListaEstadoInforme(List<Maestro> listaEstadoInforme) {
        this.listaEstadoInforme = listaEstadoInforme;
    }

    public Date getFechaFinAnterior() {
        return fechaFinAnterior;
    }

    public void setFechaFinAnterior(Date fechaFinAnterior) {
        this.fechaFinAnterior = fechaFinAnterior;
    }

    public BigDecimal getValorAdicionAnterior() {
        return valorAdicionAnterior;
    }

    public void setValorAdicionAnterior(BigDecimal valorAdicionAnterior) {
        this.valorAdicionAnterior = valorAdicionAnterior;
    }

    public Integer getDiasProrrogaAnterior() {
        return diasProrrogaAnterior;
    }

    public void setDiasProrrogaAnterior(Integer diasProrrogaAnterior) {
        this.diasProrrogaAnterior = diasProrrogaAnterior;
    }

    public boolean isHabilitarMotivoSuspension() {
        return habilitarMotivoSuspension;
    }

    public void setHabilitarMotivoSuspension(boolean habilitarMotivoSuspension) {
        this.habilitarMotivoSuspension = habilitarMotivoSuspension;
    }

    public List<Maestro> getListaTipoInforme() {
        return listaTipoInforme;
    }

    public void setListaTipoInforme(List<Maestro> listaTipoInforme) {
        this.listaTipoInforme = listaTipoInforme;
    }

    public String getTipoGastoStr(Integer id) {
        return CntjConstantes.getTipoGastoStr(id);
    }

    public List<Maestro> getListaTipoGasto() {
        return listaTipoGasto;
    }

    public void setListaTipoGasto(List<Maestro> listaTipoGasto) {
        this.listaTipoGasto = listaTipoGasto;
    }

    public Date getFechaHastaGarantia() {
        return fechaHastaGarantia;
    }

    public void setFechaHastaGarantia(Date fechaHastaGarantia) {
        this.fechaHastaGarantia = fechaHastaGarantia;
    }

    public HashMap<Integer, List<Maestro>> getHashlistaValidacionGarantia() {
        return hashlistaValidacionGarantia;
    }

    public void setHashlistaValidacionGarantia(HashMap<Integer, List<Maestro>> hashlistaValidacionGarantia) {
        this.hashlistaValidacionGarantia = hashlistaValidacionGarantia;
    }

    public String getProcesoStr(Integer id) {
        if (id == null) {
            return "";
        }
        if (getListaProcesos() != null && !getListaProcesos().isEmpty()) {
            return getListaProcesos().stream()
                    .filter(obj -> obj.getId() == id)
                    .map(CntjProceso::getNombre)
                    .findFirst()
                    .orElse("No encontrado");
        }
        return "";
    }

    public double getMinimoPlazoDia() {
        return minimoPlazoDia;
    }

    public void setMinimoPlazoDia(double minimoPlazoDia) {
        this.minimoPlazoDia = minimoPlazoDia;
    }

    public String getNaturalezaTercero(Integer id) {
        return CntjConstantes.getTipoNaturaleza(id);
    }

    public List<Maestro> getListaTipoAnticipo() {
        return listaTipoAnticipo;
    }

    public void setListaTipoAnticipo(List<Maestro> listaTipoAnticipo) {
        this.listaTipoAnticipo = listaTipoAnticipo;
    }

    public List<Maestro> getListaFormaDePago() {
        return listaFormaDePago;
    }

    public void setListaFormaDePago(List<Maestro> listaFormaDePago) {
        this.listaFormaDePago = listaFormaDePago;
    }

    public List<Maestro> getListaRegimen() {
        return listaRegimen;
    }

    public void setListaRegimen(List<Maestro> listaRegimen) {
        this.listaRegimen = listaRegimen;
    }

    public HashMap<Integer, Maestro> getHashlistaRegimen() {
        return hashlistaRegimen;
    }

    public void setHashlistaRegimen(HashMap<Integer, Maestro> hashlistaRegimen) {
        this.hashlistaRegimen = hashlistaRegimen;
    }

    public HashMap<Integer, Maestro> getHashlistaModalidad() {
        return hashlistaModalidad;
    }

    public void setHashlistaModalidad(HashMap<Integer, Maestro> hashlistaModalidad) {
        this.hashlistaModalidad = hashlistaModalidad;
    }

    public List<Maestro> getListaComplejidad() {
        return listaComplejidad;
    }

    public void setListaComplejidad(List<Maestro> listaComplejidad) {
        this.listaComplejidad = listaComplejidad;
    }

    public List<Maestro> getListaModalidad() {
        return listaModalidad;
    }

    public void setListaModalidad(List<Maestro> listaModalidad) {
        this.listaModalidad = listaModalidad;
    }

    public List<Maestro> getListaEstadoLegalizacion() {
        return listaEstadoLegalizacion;
    }

    public void setListaEstadoLegalizacion(List<Maestro> listaEstadoLegalizacion) {
        this.listaEstadoLegalizacion = listaEstadoLegalizacion;
    }

    public List<CntjProceso> getListaProcesos() {
        return listaProcesos;
    }

    public void setListaProcesos(List<CntjProceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public List<CntjContratoSupervisor> getListaSupervisorEliminar() {
        return listaSupervisorEliminar;
    }

    public void setListaSupervisorEliminar(List<CntjContratoSupervisor> listaSupervisorEliminar) {
        this.listaSupervisorEliminar = listaSupervisorEliminar;
    }

    public List<CntjContratoObligacion> getListaObligacionEliminar() {
        return listaObligacionEliminar;
    }

    public void setListaObligacionEliminar(List<CntjContratoObligacion> listaObligacionEliminar) {
        this.listaObligacionEliminar = listaObligacionEliminar;
    }

    public List<CntjContratoIndicador> getListaIndicadorEliminar() {
        return listaIndicadorEliminar;
    }

    public void setListaIndicadorEliminar(List<CntjContratoIndicador> listaIndicadorEliminar) {
        this.listaIndicadorEliminar = listaIndicadorEliminar;
    }

    public List<Maestro> getListaIndicadorContrato() {
        return listaIndicadorContrato;
    }

    public void setListaIndicadorContrato(List<Maestro> listaIndicadorContrato) {
        this.listaIndicadorContrato = listaIndicadorContrato;
    }

    public HashMap<Integer, Maestro> getHashlistaIndicadorContrato() {
        return hashlistaIndicadorContrato;
    }

    public void setHashlistaIndicadorContrato(HashMap<Integer, Maestro> hashlistaIndicadorContrato) {
        this.hashlistaIndicadorContrato = hashlistaIndicadorContrato;
    }

    public List<CntjContratoGarantia> getListaGarantiaeliminar() {
        return listaGarantiaeliminar;
    }

    public void setListaGarantiaeliminar(List<CntjContratoGarantia> listaGarantiaeliminar) {
        this.listaGarantiaeliminar = listaGarantiaeliminar;
    }

    public String getEstadoGarantiaStr(Integer id) {
        return CntjConstantes.getGarantiaContratoStr(id);
    }

    public String getEstadoLegaizacionStr(Integer id) {
        return CntjConstantes.getEstadoLegalizacionContratoStr(id);
    }

    public String getComplegidadStr(Integer id) {
        return CntjConstantes.getComplejidadContratoStr(id);
    }

    public String getFormaPagoStr(Integer id) {
        return CntjConstantes.getFormaDePagoContratoStr(id);
    }

    public String getTipoAnticipoStr(Integer id) {
        return CntjConstantes.getTipoAnticipoContratoStr(id);
    }

    public List<Maestro> getListaGarantiaContrato() {
        return listaGarantiaContrato;
    }

    public void setListaGarantiaContrato(List<Maestro> listaGarantiaContrato) {
        this.listaGarantiaContrato = listaGarantiaContrato;
    }

    public HashMap<Integer, Maestro> getHashlistaGarantiaContrato() {
        return hashlistaGarantiaContrato;
    }

    public void setHashlistaGarantiaContrato(HashMap<Integer, Maestro> hashlistaGarantiaContrato) {
        this.hashlistaGarantiaContrato = hashlistaGarantiaContrato;
    }

    public List<Maestro> getListaClaseContrato() {
        return listaClaseContrato;
    }

    public void setListaClaseContrato(List<Maestro> listaClaseContrato) {
        this.listaClaseContrato = listaClaseContrato;
    }

    public HashMap<Integer, Maestro> getHashlistaClaseContrato() {
        return hashlistaClaseContrato;
    }

    public void setHashlistaClaseContrato(HashMap<Integer, Maestro> hashlistaClaseContrato) {
        this.hashlistaClaseContrato = hashlistaClaseContrato;
    }

    public boolean isRequiereFechas() {
        return requiereFechas;
    }

    public void setRequiereFechas(boolean requiereFechas) {
        this.requiereFechas = requiereFechas;
    }

    public boolean isRequiereValor() {
        return requiereValor;
    }

    public void setRequiereValor(boolean requiereValor) {
        this.requiereValor = requiereValor;
    }

    public boolean isRequierePlazo() {
        return requierePlazo;
    }

    public void setRequierePlazo(boolean requierePlazo) {
        this.requierePlazo = requierePlazo;
    }

    public List<Maestro> getListaTipoArchivo() {
        return listaTipoArchivo;
    }

    public void setListaTipoArchivo(List<Maestro> listaTipoArchivo) {
        this.listaTipoArchivo = listaTipoArchivo;
    }

    public HashMap<Integer, Maestro> getHashlistaTipoArchivo() {
        return hashlistaTipoArchivo;
    }

    public void setHashlistaTipoArchivo(HashMap<Integer, Maestro> hashlistaTipoArchivo) {
        this.hashlistaTipoArchivo = hashlistaTipoArchivo;
    }

    public List<Maestro> getListaEstadoOtrosi() {
        return listaEstadoOtrosi;
    }

    public void setListaEstadoOtrosi(List<Maestro> listaEstadoOtrosi) {
        this.listaEstadoOtrosi = listaEstadoOtrosi;
    }

    public String getEstadoOtrosi(Integer id) {
        return CntjConstantes.getEstadoOtrosiStr(id);
    }

    public String getTipoOtrosi(Integer id) {
        return CntjConstantes.getTipoOtrosiStr(id);
    }

    public LazyDataModel<CntjOtrosi> getLazyOtroSi() {
        return lazyOtroSi;
    }

    public void setLazyOtroSi(LazyDataModel<CntjOtrosi> lazyOtroSi) {
        this.lazyOtroSi = lazyOtroSi;
    }

    public List<CntjOtrosi> getRegistrosOtrosi() {
        return registrosOtrosi;
    }

    public void setRegistrosOtrosi(List<CntjOtrosi> registrosOtrosi) {
        this.registrosOtrosi = registrosOtrosi;
    }

    public List<CntjOtrosiAdjunto> getListaAdjuntoeliminar() {
        return listaAdjuntoeliminar;
    }

    public void setListaAdjuntoeliminar(List<CntjOtrosiAdjunto> listaAdjuntoeliminar) {
        this.listaAdjuntoeliminar = listaAdjuntoeliminar;
    }

    public CntjOtrosiAdjunto getObjetoOtrosiAdjunto() {
        return objetoOtrosiAdjunto;
    }

    public void setObjetoOtrosiAdjunto(CntjOtrosiAdjunto objetoOtrosiAdjunto) {
        this.objetoOtrosiAdjunto = objetoOtrosiAdjunto;
    }

    public List<Maestro> getListaTipoOtrosi() {
        return listaTipoOtrosi;
    }

    public void setListaTipoOtrosi(List<Maestro> listaTipoOtrosi) {
        this.listaTipoOtrosi = listaTipoOtrosi;
    }

    public CntjOtrosi getObjetoOtrosi() {
        return objetoOtrosi;
    }

    public void setObjetoOtrosi(CntjOtrosi objetoOtrosi) {
        this.objetoOtrosi = objetoOtrosi;
    }

    public List<CntjOtrosiAdjunto> getAdjuntosOtroSi() {
        return adjuntosOtroSi;
    }

    public void setAdjuntosOtroSi(List<CntjOtrosiAdjunto> adjuntosOtroSi) {
        this.adjuntosOtroSi = adjuntosOtroSi;
    }

    public List<Maestro> getListaEstadoContrato() {
        return listaEstadoContrato;
    }

    public void setListaEstadoContrato(List<Maestro> listaEstadoContrato) {
        this.listaEstadoContrato = listaEstadoContrato;
    }

    public HashMap<Integer, Maestro> getHashlistaEstadoContrato() {
        return hashlistaEstadoContrato;
    }

    public void setHashlistaEstadoContrato(HashMap<Integer, Maestro> hashlistaEstadoContrato) {
        this.hashlistaEstadoContrato = hashlistaEstadoContrato;
    }

    public List<Maestro> getListaTipoTercero() {
        return listaTipoTercero;
    }

    public void setListaTipoTercero(List<Maestro> listaTipoTercero) {
        this.listaTipoTercero = listaTipoTercero;
    }

    public List<Maestro> getListaTipoDocumentoTercero() {
        return listaTipoDocumentoTercero;
    }

    public void setListaTipoDocumentoTercero(List<Maestro> listaTipoDocumentoTercero) {
        this.listaTipoDocumentoTercero = listaTipoDocumentoTercero;
    }

    public LazyDataModel<CntjTercero> getLazyTerceros() {
        return lazyTerceros;
    }

    public void setLazyTerceros(LazyDataModel<CntjTercero> lazyTerceros) {
        this.lazyTerceros = lazyTerceros;
    }

    public List<CntjTercero> getRegistrosTerceros() {
        return registrosTerceros;
    }

    public void setRegistrosTerceros(List<CntjTercero> registrosTerceros) {
        this.registrosTerceros = registrosTerceros;
    }

    public Integer getTipoSelectTercero() {
        return tipoSelectTercero;
    }

    public void setTipoSelectTercero(Integer tipoSelectTercero) {
        this.tipoSelectTercero = tipoSelectTercero;
    }

    public CntjContrato getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjContrato objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CntjContrato> getLazyContrato() {
        return lazyContrato;
    }

    public void setLazyContrato(LazyDataModel<CntjContrato> lazyContrato) {
        this.lazyContrato = lazyContrato;
    }

    public List<CntjContrato> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntjContrato> registros) {
        this.registros = registros;
    }

    public ContratoServicioIface getCntjContratoServicio() {
        return cntjContratoServicio;
    }

    public void setCntjContratoServicio(ContratoServicioIface cntjContratoServicio) {
        this.cntjContratoServicio = cntjContratoServicio;
    }

    public CntjContratoSupervisor getObjetoSupervisor() {
        return objetoSupervisor;
    }

    public void setObjetoSupervisor(CntjContratoSupervisor objetoSupervisor) {
        this.objetoSupervisor = objetoSupervisor;
    }

    public CntjContratoGarantia getObjetoGarantia() {
        return objetoGarantia;
    }

    public void setObjetoGarantia(CntjContratoGarantia objetoGarantia) {
        this.objetoGarantia = objetoGarantia;
    }

    public CntjContratoObligacion getObjetoObligacion() {
        return objetoObligacion;
    }

    public void setObjetoObligacion(CntjContratoObligacion objetoObligacion) {
        this.objetoObligacion = objetoObligacion;
    }

    public CntjContratoIndicador getObjetoIndicador() {
        return objetoIndicador;
    }

    public void setObjetoIndicador(CntjContratoIndicador objetoIndicador) {
        this.objetoIndicador = objetoIndicador;
    }

    public CntjContratoSeguimiento getObjetoSeguimiento() {
        return objetoSeguimiento;
    }

    public void setObjetoSeguimiento(CntjContratoSeguimiento objetoSeguimiento) {
        this.objetoSeguimiento = objetoSeguimiento;
    }

    public CntjContratoInforme getObjetoInforme() {
        return objetoInforme;
    }

    public void setObjetoInforme(CntjContratoInforme objetoInforme) {
        this.objetoInforme = objetoInforme;
    }

    public CntjContratoInformeAdjunto getObjetoAdjuntoInforme() {
        return objetoAdjuntoInforme;
    }

    public void setObjetoAdjuntoInforme(CntjContratoInformeAdjunto objetoAdjuntoInforme) {
        this.objetoAdjuntoInforme = objetoAdjuntoInforme;
    }

    public List<Maestro> getListaTipoArchivoAdjuntoInforme() {
        return listaTipoArchivoAdjuntoInforme;
    }

    public void setListaTipoArchivoAdjuntoInforme(List<Maestro> listaTipoArchivoAdjuntoInforme) {
        this.listaTipoArchivoAdjuntoInforme = listaTipoArchivoAdjuntoInforme;
    }

    public HashMap<Integer, Maestro> getHashTipoArchivoAdjuntoInforme() {
        return hashTipoArchivoAdjuntoInforme;
    }

    public void setHashTipoArchivoAdjuntoInforme(HashMap<Integer, Maestro> hashTipoArchivoAdjuntoInforme) {
        this.hashTipoArchivoAdjuntoInforme = hashTipoArchivoAdjuntoInforme;
    }

    public String getEtapaDesignacionStr(Integer id) {
        return CntjConstantes.getEtapaDesignacion(id);
    }

    // </editor-fold>
    
    // Metodos
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCntjContratoServicio().Accion(this);
    }

    public void refrescarTerceros() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjContratoServicio().Accion(this);
    }

    public void refrescarDocumentos() {
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_VER);
        getCntjContratoServicio().Accion(this);
    }

    public void listarOtrosi() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescarOtrosis() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjContratoServicio().Accion(this);
    }

    public void listarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        procesoFinal();
    }

    public void refrescarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getCntjContratoServicio().Accion(this);
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getCntjContratoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void ver(Integer id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_VER);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void editar(Integer idComite) {
        this.objeto.setId(idComite);
        super.setAccion(ACCION_EDITAR);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCntjContratoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void listarTerceros(Integer tipo) {
        setTipoSelectTercero(tipo);
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyTerceros();
        PrimeFaces.current().executeScript("PF('dialogoListaTercero').show()");
        procesoFinal();
    }

    private void cargarLazyTerceros() {
        lazyTerceros = new LazyDataModel<CntjTercero>() {
            private List<CntjTercero> listaTerceros;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<CntjTercero> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                //filtrosHash.put(CntjConstantes.ACTIVO_STR, CntjConstantes.ACTIVO.toString());
                getParamConsulta(0).setFiltros(filtrosHash);
                refrescarTerceros();
                listaTerceros = getRegistrosTerceros();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaTerceros;
            }

            @Override
            public String getRowKey(CntjTercero objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjTercero getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (CntjTercero user : listaTerceros) {
                    if (id.equals(user.getId())) {
                        return user;
                    }
                }
                return null;
            }
        };
    }

    public String getTipoTerceroStr(Integer id) {
        return CntjConstantes.getTipoTercero(id);
    }

    public void onRowSelectUsuario(SelectEvent event) {
        CntjTercero tercero = (CntjTercero) event.getObject();
        if (getTipoSelectTercero().equals(0)) {
            getObjetoSupervisor().setCntjTercerosId(tercero);
            PrimeFaces.current().ajax().update("frmCrearSupervisor");
            PrimeFaces.current().executeScript("PF('dialogoListaTercero').hide()");
        } else if (getTipoSelectTercero().equals(2)) {
            this.objeto.setCntjTerceroId(tercero);
            PrimeFaces.current().ajax().update("frmCrear:pnContratista");
            PrimeFaces.current().executeScript("PF('dialogoListaTercero').hide()");
        } else {
            getObjetoSupervisor().setCntjTercerosId(tercero);
            PrimeFaces.current().ajax().update("frmEditar:tablaResponsable");
            PrimeFaces.current().executeScript("PF('dialogoListaTercero').hide()");
        }
    }

    public void verOtrosSi(Integer idcontrato) {
        getObjeto().setId(idcontrato);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_8);
        getCntjContratoServicio().Accion(this);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyOtrosi();
        PrimeFaces.current().executeScript("PF('dialogoListaOtrosies').show()");
        procesoFinal();
    }

    private void cargarLazyOtrosi() {
        lazyOtroSi = new LazyDataModel<CntjOtrosi>() {
            private List<CntjOtrosi> listado;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<CntjOtrosi> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ID_CONTRATO, getObjeto().getId());
                getParamConsulta(1).setFiltros(filtrosHash);
                refrescarOtrosis();
                listado = getRegistrosOtrosi();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listado;
            }

            @Override
            public String getRowKey(CntjOtrosi obj) {
                return obj.getId().toString();
            }

            @Override
            public CntjOtrosi getRowData(String iddata) {
                Integer id = Integer.valueOf(iddata);
                for (CntjOtrosi obj : listado) {
                    if (id.equals(obj.getId())) {
                        return obj;
                    }
                }
                return null;
            }
        };
    }

    public void crearOtroSi() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getCntjContratoServicio().Accion(this);
        if (!super.isError()) {
            validarTipoOtrosiCampos();            
        } else {
            this.setAccion(CntjConstantes.ACCION_NA);
        }
        procesoFinal();
    }

    public void crearOtroSiExpediente() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_9);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void guardarOtrosi() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjContratoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearOtrosi').hide();");
        }
        procesoFinal();
    }

    public void verOtrosi(Integer id) {
        this.objetoOtrosi.setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void editarOtrosi(Integer id) {
        getObjetoOtrosi().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_EDITAR);
        getCntjContratoServicio().Accion(this);
        validarTipoOtrosiCampos();
        procesoFinal();
    }

    public void modificarOtrosi() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getCntjContratoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarOtrosi').hide();");
        }
        procesoFinal();
    }

    public void crearAdjunto() {
        this.objetoOtrosiAdjunto = new CntjOtrosiAdjunto();
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().resetInputs("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        super.setAccion(CntjConstantes.ACCION_NA);
        UploadedFile archivoAdjunto = event.getFile();
        try {
            if (this.objetoOtrosiAdjunto.getMaetipoArchivoId() == null) {
                this.addError("Debe seleccionar un valor para el campo documento");
            } else {
                boolean existe = this.adjuntosOtroSi.stream()
                        .anyMatch(elemento -> elemento.getMaetipoArchivoId().equals(this.objetoOtrosiAdjunto.getMaetipoArchivoId()));
                if (existe) {
                    this.addError("Ya existe un adjunto agregado para el documento seleccionado, solo se permite un adjunto por documento.");
                    PrimeFaces.current().ajax().update("frmCrearAdjunto");
                } else {
                    Maestro tipoArchivo = this.getHashlistaTipoArchivo().get(this.objetoOtrosiAdjunto.getMaetipoArchivoId());
                    if (tipoArchivo != null) {
                        this.objetoOtrosiAdjunto.setMaetipoArchivoCodigo(tipoArchivo.getValor());
                        this.objetoOtrosiAdjunto.setMaetipoArchivoValor(tipoArchivo.getNombre());
                    }
                    this.objetoOtrosiAdjunto.setId((adjuntosOtroSi.size() * -1) - 1);
                    this.objetoOtrosiAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
                    String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
                    this.objetoOtrosiAdjunto.setNombre(nombreAdjunto);
                    this.adjuntosOtroSi.add(this.objetoOtrosiAdjunto);
                    this.objetoOtrosiAdjunto = new CntjOtrosiAdjunto();
                    PrimeFaces.current().ajax().update("frmCrearOtrosi:tablaAdjunto");
                    PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide();");
                }
            }

        } catch (IOException ex) {
            this.addError(ex.getMessage());
        }
        procesoFinal();
    }

    public void cargarAdjuntoInforme(FileUploadEvent event) throws IOException {
        UploadedFile archivoAdjunto = event.getFile();
        try {
            if (this.objetoAdjuntoInforme.getMaeTipoArchivoId() <= 0) {
                this.addError("Debe seleccionar un valor para el campo tipo archivo");
            } else {
                boolean existe = getObjetoInforme().getCntjContratoInformeAdjuntoList().stream()
                        .anyMatch(elemento -> elemento.getMaeTipoArchivoId() == this.objetoAdjuntoInforme.getMaeTipoArchivoId());
                if (existe) {
                    this.addError("Ya existe un adjunto agregado para el informe seleccionado, solo se permite un adjunto por documento.");
                    PrimeFaces.current().ajax().update("frmCrearAdjuntoInforme");
                } else {
                    Maestro tipoArchivo = this.getHashTipoArchivoAdjuntoInforme().get(this.objetoAdjuntoInforme.getMaeTipoArchivoId());
                    if (tipoArchivo != null) {
                        this.objetoAdjuntoInforme.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                        this.objetoAdjuntoInforme.setMaeTipoArchivoValor(tipoArchivo.getNombre());
                    }
                    this.objetoAdjuntoInforme.setAdjuntoStream(archivoAdjunto.getInputStream());
                    String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
                    this.objetoAdjuntoInforme.setNombre(nombreAdjunto);
                    getObjetoInforme().getCntjContratoInformeAdjuntoList().add(this.objetoAdjuntoInforme);
                    PrimeFaces.current().ajax().update("frmCrearInforme:tablaAdjunto");
                    PrimeFaces.current().executeScript("PF('dialogoCrearAdjuntoInforme').hide();");
                }
            }

        } catch (IOException ex) {
            this.addError(ex.getMessage());
        }
        procesoFinal();
    }

    public void eliminarAdjunto(CntjOtrosiAdjunto adjunto) {
        if (adjunto.getId() < 0) {
            this.adjuntosOtroSi.removeIf(item -> item.getId().equals(adjunto.getId()));
        } else {
            this.listaAdjuntoeliminar.add(adjunto);
            this.adjuntosOtroSi.removeIf(item -> item.getId().equals(adjunto.getId()));
        }
    }

    public void validarTipoOtrosiCampos() {
        setRequiereValor(false);
        setRequierePlazo(false);
        setRequiereFechas(false);

        if (this.getObjetoOtrosi().getTipo() != null && (this.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_PRORROGA) || this.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_PRORROGA_MODIFICACION) || this.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO))) {
            setRequierePlazo(true);
            setRequiereFechas(true);
        }

        if (this.getObjetoOtrosi().getTipo() != null && (this.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ADICION) || this.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ADICION_MODIFICACION))) {
            setRequiereValor(true);
        }

        if (this.getObjetoOtrosi().getTipo() != null && (this.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_PRORROGA_ADICION) || this.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_PRORROGA_ADICION_MODIFICACION))) {
            setRequiereValor(true);
            setRequierePlazo(true);
            setRequiereFechas(true);
        }
        PrimeFaces.current().ajax().update("frmCrearOtrosi");
        PrimeFaces.current().ajax().update("frmEditarOtrosi");
    }

    public void restablecerFechaInicio() {
        if (this.getObjetoOtrosi().getFechasuscripcion().after(this.getObjetoOtrosi().getFechaInicio())) {
            this.getObjetoOtrosi().setFechaInicio(this.getObjetoOtrosi().getFechasuscripcion());
        }
    }

    public Date getFechaMinimaInicioOtrosi() {
        CntjOtrosi otrosi = new CntjOtrosi();
        if (getRegistrosOtrosi() != null && !getRegistrosOtrosi().isEmpty()) {
            Optional<CntjOtrosi> ultimoOtrosi = this.registrosOtrosi.stream()
                    .filter(obj -> (obj.getFechaInicio() != null && obj.getFechaTerminacion() != null) && !obj.getId().equals(this.getObjetoOtrosi().getId()))
                    .max(Comparator.comparingInt(CntjOtrosi::getNumero));
            if (ultimoOtrosi.isPresent()) {
                otrosi = ultimoOtrosi.get();
            } else {
                if (getObjetoOtrosi() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                    return getObjeto().getFechaInicio();
                } else {
                    otrosi.setFechaTerminacion(getObjeto().getFechaFin());
                }
            }
        } else {
            if (getObjetoOtrosi() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                return getObjeto().getFechaInicio();
            } else {
                otrosi.setFechaTerminacion(getObjeto().getFechaFin());
            }
        }

        if (otrosi.getFechaTerminacion() == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(otrosi.getFechaTerminacion());
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Añade 1 día
        return calendar.getTime();
    }

    public Date getFechaMaximaInicioOtrosi() {
        CntjOtrosi otrosi = new CntjOtrosi();
        if (getRegistrosOtrosi() != null && !getRegistrosOtrosi().isEmpty()) {
            Optional<CntjOtrosi> ultimoOtrosi = this.registrosOtrosi.stream()
                    .filter(obj -> (obj.getFechaInicio() != null && obj.getFechaTerminacion() != null) && !obj.getId().equals(this.getObjetoOtrosi().getId()))
                    .max(Comparator.comparingInt(CntjOtrosi::getNumero));
            if (ultimoOtrosi.isPresent()) {
                otrosi = ultimoOtrosi.get();
            } else {
                if (getObjetoOtrosi() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                    otrosi.setFechaTerminacion(null);
                } else {
                    otrosi.setFechaTerminacion(getObjeto().getFechaFin());
                }
            }
        } else {
            if (getObjetoOtrosi() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                otrosi.setFechaTerminacion(null);
            } else {
                otrosi.setFechaTerminacion(getObjeto().getFechaFin());
            }
        }

        if (otrosi.getFechaTerminacion() == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(otrosi.getFechaTerminacion());
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Añade 1 día
        return calendar.getTime();
    }

    public Date getFechaMinimaFinOtrosi() {
        CntjOtrosi otrosi = new CntjOtrosi();
        if (getRegistrosOtrosi() != null && !getRegistrosOtrosi().isEmpty()) {
            Optional<CntjOtrosi> ultimoOtrosi = this.registrosOtrosi.stream()
                    .filter(obj -> (obj.getFechaInicio() != null && obj.getFechaTerminacion() != null) && !obj.getId().equals(this.getObjetoOtrosi().getId()))
                    .max(Comparator.comparingInt(CntjOtrosi::getNumero));
            if (ultimoOtrosi.isPresent()) {
                otrosi = ultimoOtrosi.get();
            } else {
                if (getObjetoOtrosi() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                    return getObjetoOtrosi().getFechaInicio();
                } else {
                    otrosi.setFechaTerminacion(getObjeto().getFechaFin());
                }
            }
        } else {
            if (getObjetoOtrosi() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                return getObjetoOtrosi().getFechaInicio();
            } else {
                otrosi.setFechaTerminacion(getObjeto().getFechaFin());
            }
        }

        if (otrosi.getFechaTerminacion() == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(otrosi.getFechaTerminacion());
        calendar.add(Calendar.DAY_OF_MONTH, 2); // Añade 1 día
        return calendar.getTime();
    }

    public Date minFechaSuscripcionOtrosi() {
        CntjOtrosi otrosi = new CntjOtrosi();
        if (getRegistrosOtrosi() != null && !getRegistrosOtrosi().isEmpty()) {
            Optional<CntjOtrosi> ultimoOtrosi = this.registrosOtrosi.stream()
                    .filter(item -> (!item.getId().equals(this.getObjetoOtrosi().getId())))
                    .max(Comparator.comparingInt(CntjOtrosi::getNumero));
            if (ultimoOtrosi.isPresent()) {
                otrosi = ultimoOtrosi.get();
            } else {
                if (getObjetoOtrosi().getTipo() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                    return getObjeto().getFechaInicio();
                } else {
                    otrosi.setFechasuscripcion(getObjeto().getFechaInicio());
                }
            }
        } else {
            if (getObjetoOtrosi().getTipo() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                return getObjeto().getFechaInicio();
            } else {
                otrosi.setFechasuscripcion(getObjeto().getFechaInicio());
            }
        }

        if (otrosi.getFechasuscripcion() == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(otrosi.getFechasuscripcion());
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Añade 1 día
        return calendar.getTime();
    }

    public Date maxFechaSuscripcionOtrosi() {
        CntjOtrosi otrosi = new CntjOtrosi();
        if (getRegistrosOtrosi() != null && !getRegistrosOtrosi().isEmpty()) {
            Optional<CntjOtrosi> ultimoOtrosi = this.registrosOtrosi.stream()
                    .filter(obj -> (obj.getFechaInicio() != null && obj.getFechaTerminacion() != null) && !obj.getId().equals(this.getObjetoOtrosi().getId()))
                    .max(Comparator.comparingInt(CntjOtrosi::getNumero));
            if (ultimoOtrosi.isPresent()) {
                otrosi = ultimoOtrosi.get();
            } else {
                if (getObjetoOtrosi() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                    otrosi.setFechaTerminacion(null);
                } else {
                    otrosi.setFechaTerminacion(getObjeto().getFechaFin());
                }
            }
        } else {
            if (getObjetoOtrosi().getTipo() != null && getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                return null;
            } else {
                otrosi.setFechaTerminacion(getObjeto().getFechaFin());
            }
        }

        if (otrosi.getFechaTerminacion() == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(otrosi.getFechaTerminacion());
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Resta 1 día
        Date fechaminima = minFechaSuscripcionOtrosi();
        if (fechaminima.after(calendar.getTime())) {
            return fechaminima;
        }
        return calendar.getTime();
    }

    public void restablecerInicioOtrosi() {
        if (getObjetoOtrosi() != null && getObjetoOtrosi().getFechaInicio() != null) {
            getObjetoOtrosi().setFechaInicio(null);
            getObjetoOtrosi().setFechaTerminacion(null);
        }
    }

    public boolean permitirEditarOtrosi(CntjOtrosi otrosi) {
        if (this.registrosOtrosi.isEmpty()) {
            return false;
        }
        return this.registrosOtrosi.stream()
                .max(Comparator.comparingInt(CntjOtrosi::getNumero))
                .map(maxObj -> maxObj.getId() == otrosi.getId())
                .orElse(false);
    }

    public void crearGarantiaContrato() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        getCntjContratoServicio().Accion(this);
        validarGarantiaHasta();
        procesoFinal();
    }

    public void guardarGarantiaContrato() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjContratoServicio().Accion(this);
        if (!super.isError()) {
            getObjetoGarantia().setIdTemporal(getObjeto().getCntjContratoGarantiaList().size() + 1);
            getObjetoGarantia().setEstado(CntjConstantes.GARANTIA_CONTRATO_VIGENTE);
            getObjeto().getCntjContratoGarantiaList().add(getObjetoGarantia());
            PrimeFaces.current().executeScript("PF('dialogoCrearGarantia').hide();");
        }
        procesoFinal();
    }

    public void eliminarGarantiaContrato(CntjContratoGarantia garantia) {
        if (garantia.getId() == null) {
            getObjeto().getCntjContratoGarantiaList().removeIf(item -> item.getIdTemporal() == garantia.getIdTemporal());
        } else {
            this.listaGarantiaeliminar.add(garantia);
            getObjeto().getCntjContratoGarantiaList().removeIf(item -> item.getId().equals(garantia.getId()));
        }
    }

    public void crearIndicadorContrato() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_CREAR);
        setObjetoIndicador(new CntjContratoIndicador());
        procesoFinal();
    }

    public void guardarIndicadorContrato() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR);
        getObjetoIndicador().setIdTemporal(getObjeto().getCntjContratoIndicadorList().size() + 1);
        getObjeto().getCntjContratoIndicadorList().add(getObjetoIndicador());
        setObjetoIndicador(new CntjContratoIndicador());
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearIndicador').hide();");
        }
        procesoFinal();
    }

    public void eliminarIndicadorContrato(CntjContratoIndicador indicador) {
        if (indicador.getId() == null) {
            getObjeto().getCntjContratoIndicadorList().removeIf(item -> item.getIdTemporal() == indicador.getIdTemporal());
        } else {
            this.listaIndicadorEliminar.add(indicador);
            getObjeto().getCntjContratoIndicadorList().removeIf(item -> item.getId().equals(indicador.getId()));
        }
    }

    public void crearObligacionesContrato() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_CREAR);
        setObjetoObligacion(new CntjContratoObligacion());
        procesoFinal();
    }

    public void guardarObligacionContrato() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR);
        getObjetoObligacion().setNumeroObligacion(getObjeto().getCntjContratoObligacionList().size() + 1);
        getObjeto().getCntjContratoObligacionList().add(getObjetoObligacion());
        setObjetoObligacion(new CntjContratoObligacion());
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearObligacion').hide();");
        }
        procesoFinal();
    }

    public void eliminarObligacionContrato(CntjContratoObligacion obligacion) {
        if (obligacion.getId() == null) {
            getObjeto().getCntjContratoObligacionList().removeIf(item -> item.getIdTemporal() == obligacion.getIdTemporal());
        } else {
            this.listaObligacionEliminar.add(obligacion);
            getObjeto().getCntjContratoObligacionList().removeIf(item -> item.getId().equals(obligacion.getId()));
        }
    }

    public void crearSupervidorContrato() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_CREAR);
        setObjetoSupervisor(new CntjContratoSupervisor());
        procesoFinal();
    }

    public void guardarSupervidorContrato() {
        if (getObjetoSupervisor().getCntjTercerosId() == null) {
            super.setAccion(CntjConstantes.ACCION_NA);
            this.addError("Debe seleccionar el supervisor");
        } else {
            super.setAccion(ACCION_ADICIONAL_6);
            super.setDoAccion(ACCION_GUARDAR);
            if (this.objeto.getCntjContratoSupervisorList() == null) {
                this.objeto.setCntjContratoSupervisorList(new ArrayList<>());
            }
            this.objeto.getCntjContratoSupervisorList().add(getObjetoSupervisor());
        }
        procesoFinal();
    }

    public void eliminarSupervisorContrato(CntjContratoSupervisor supervisor) {
        if (supervisor.getId() == null) {
            this.objeto.getCntjContratoSupervisorList().removeIf(su -> (su.getCntjTercerosId().getId()
                    .equals(supervisor.getCntjTercerosId().getId())));
        } else {
            this.listaSupervisorEliminar.add(supervisor);
            this.objeto.getCntjContratoSupervisorList().removeIf(su -> (su.getId()
                    .equals(supervisor.getId())));
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaResponsable");
        PrimeFaces.current().ajax().update("frmEditar:tablaResponsable");
    }

    public void cambioPlazoMeses() {
        if (this.objeto.getPlazoInicialMeses() != null) {
            setMinimoPlazoDia(Double.valueOf(this.objeto.getPlazoInicialMeses()));
            if (Integer.parseInt(this.objeto.getPlazoInicialMeses()) > Integer.parseInt(this.objeto.getPlazoInicialDias())) {
                this.objeto.setPlazoInicialDias("" + this.objeto.getPlazoInicialMeses());
            }
        } else {
            setMinimoPlazoDia(Double.valueOf("1"));
        }
        cambioPlazodias();
    }

    public void cambioPlazodias() {
        if (this.objeto.getPlazoInicialDias() != null) {
            this.objeto.setPlazoTotalDias(Integer.parseInt(this.objeto.getPlazoInicialDias()));
        } else {
            this.objeto.setPlazoTotalDias(0);
        }
    }

    public void calcularValorTotal() {
        if (this.objeto.getValorInicial() != null) {
            this.objeto.setValorTotal(this.objeto.getValorInicial());
        } else {
            this.objeto.setValorTotal(BigDecimal.valueOf(0));
        }
    }

    public void validarGarantiaHasta() {
        if (getObjetoGarantia().getMaeGarantiaContratoId() != null) {
            if (getHashlistaValidacionGarantia() != null && !getHashlistaValidacionGarantia().isEmpty()) {
                List<Maestro> maeVigenciaHasta = getHashlistaValidacionGarantia().get(MaestroAccion.CNTJ_GARANTIA_VIGENCIA_HASTA);
                List<Maestro> maeVigencia4meses = getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_4_MES);
                List<Maestro> maeVigencia36meses = getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_36_MES);
                List<Maestro> maeVigencia60meses = getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_60_MES);
                List<Maestro> maeVigencia12meses = getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_12_MES);

                if (getHashlistaValidacionGarantia().get(MaestroAccion.CNTJ_GARANTIA_VIGENCIA_HASTA)
                        .stream().anyMatch(elemento -> elemento.getId().equals(getObjetoGarantia().getMaeGarantiaContratoId()))) {
                    setFechaHastaGarantia(this.getObjeto().getFechaFin());
                }
                if (getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_4_MES)
                        .stream().anyMatch(elemento -> elemento.getId().equals(getObjetoGarantia().getMaeGarantiaContratoId()))) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(this.getObjeto().getFechaFin());
                    calendar.add(Calendar.MONTH, 4);
                    setFechaHastaGarantia(calendar.getTime());
                }
                if (getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_36_MES)
                        .stream().anyMatch(elemento -> elemento.getId().equals(getObjetoGarantia().getMaeGarantiaContratoId()))) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(this.getObjeto().getFechaFin());
                    calendar.add(Calendar.MONTH, 36);
                    setFechaHastaGarantia(calendar.getTime());
                }
                if (getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_60_MES)
                        .stream().anyMatch(elemento -> elemento.getId().equals(getObjetoGarantia().getMaeGarantiaContratoId()))) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(this.getObjeto().getFechaFin());
                    calendar.add(Calendar.MONTH, 60);
                    setFechaHastaGarantia(calendar.getTime());
                }
                if (getHashlistaValidacionGarantia().get(MaestroAccion.GARANTIA_VIG_HASTA_12_MES)
                        .stream().anyMatch(elemento -> elemento.getId().equals(getObjetoGarantia().getMaeGarantiaContratoId()))) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(this.getObjeto().getFechaFin());
                    calendar.add(Calendar.MONTH, 12);
                    setFechaHastaGarantia(calendar.getTime());
                }
            }
        } else {
            setFechaHastaGarantia(this.getObjeto().getFechaFin());
        }
    }

    public void verSeguimientos(CntjContrato objeto) {
        setObjeto(objeto);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void crearSeguimiento() {
        setObjetoSeguimiento(new CntjContratoSeguimiento());
        if (getObjeto().getProceso().equals("1")) {
            getObjetoSeguimiento().setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_MENSUAL);
        } else if (getObjeto().getProceso().equals("2")
                && getObjeto().getFormaPago().equals(1)) {
            getObjetoSeguimiento().setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_BIMESTRAL);
        } else {
            getObjetoSeguimiento().setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_SEMESTRAL);
        }
        getObjetoSeguimiento().setEstadoSeguimiento(CntjContratoSeguimiento.ESTADO_PENDIENTE);
        PrimeFaces.current().executeScript("PF('dialogoSeguimiento').show()");
        PrimeFaces.current().ajax().update("frmSeguimiento");
    }

    public void guardarSeguimiento() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void editarSeguimiento(CntjContratoSeguimiento seguimiento) {
        setObjetoSeguimiento(seguimiento);
        PrimeFaces.current().ajax().update("frmSeguimiento");
        PrimeFaces.current().executeScript("PF('dialogoSeguimiento').show()");
    }

    public void verInformes(Integer idcontrato) {
        getObjeto().setId(idcontrato);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void crearInforme() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_4);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void editarInforme(CntjContratoInforme objeto) {
        setObjetoInforme(objeto);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_6);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void guardarInforme() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_5);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void crearAdjuntoInforme() {
        super.setAccion(CntjConstantes.ACCION_NA);
        setObjetoAdjuntoInforme(new CntjContratoInformeAdjunto());
        PrimeFaces.current().ajax().update("frmCrearAdjuntoInforme");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjuntoInforme').show()");
    }

    public void modificarInforme() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_7);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void verdocumentos(Integer id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_VER);
        cargarLazyDocumentos();
        PrimeFaces.current().executeScript("PF('dialogoDocumentos').show()");
        procesoFinal();
    }

    private void cargarLazyDocumentos() {
        lazyDocumentos = new LazyDataModel<CntjDocumento>() {

            private List<CntjDocumento> listaDocumentos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<CntjDocumento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                getParamConsulta(3).setFiltros(filtrosHash);
                refrescarDocumentos();
                listaDocumentos = getListaDocumentos();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return listaDocumentos;
            }

            @Override
            public String getRowKey(CntjDocumento objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjDocumento getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CntjDocumento objeto : listaDocumentos) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void setValorMese(String valor) {
        if (getObjeto().getPlazoInicialMeses() != null) {
            getObjeto().setPlazoInicialMeses(valor);
        }
        calcularValores();
    }

    public void calcularPlazos() {
        if (getObjeto().getPlazoInicialDias() != null) {
            Integer plazoProrroga = getObjeto().getPlazoProrrogas() != null ? getObjeto().getPlazoProrrogas() : 0;
            getObjeto().setPlazoTotalDias(Integer.valueOf(getObjeto().getPlazoInicialDias()) + plazoProrroga);
        }
        calcularFechaSuspensionContrato();
        calcularValores();
    }

    public void calcularDiasTotales() {
        Integer dias = Integer.parseInt(getObjeto().getPlazoInicialDias());
        int diasTotales = dias + getObjeto().getPlazoProrrogas();
        getObjeto().setPlazoTotalDias(diasTotales);
    }

    public void calcularValores() {
        if (getObjeto().getPlazoInicialDias() != null) {
            Long dias = getObjeto().getValorInicial().longValue() / Integer.parseInt(getObjeto().getPlazoInicialDias());
            getObjeto().setValorDia(new BigDecimal(dias));
        }

        if (this.getObjeto().getPlazoInicialMeses() != null) {
            Long meses = getObjeto().getValorInicial().longValue() / Integer.parseInt(getObjeto().getPlazoInicialMeses());
            getObjeto().setValorMes(new BigDecimal(meses));
        } else {
            getObjeto().setValorMes(new BigDecimal(0));
        }

        if (getObjeto().getValorInicial() != null) {
            BigDecimal valorAdiciones = getObjeto().getValorAdiciones() != null ? getObjeto().getValorAdiciones() : BigDecimal.ZERO;
            getObjeto().setValorTotal(getObjeto().getValorInicial().add(valorAdiciones));
        }
    }

    public String obtenetTipoIndicador(int tipo) {
        String valor = "";
        if (getListaIndicadorContrato() != null && getListaIndicadorContrato().size() > 0) {
            for (Maestro maestro : getListaIndicadorContrato()) {
                if (maestro.getId().equals(tipo)) {
                    valor = maestro.getNombre();
                }
            }
        }
        return valor;
    }

    public void generarArchivoAdjuntoInforme(CntjContratoInformeAdjunto adjunto) {
        super.setAccion(CntjConstantes.ACCION_NA);
        if (adjunto.isExiste()) {
            StringBuilder rutaCompleta = new StringBuilder(adjunto.getRuta()).append(adjunto.getArchivo());
            descargarArchivo(rutaCompleta.toString(), adjunto.getArchivo());
        } else {
            this.addError("El archivo no existe en la ruta de descarga.");
        }
        procesoFinal();
    }

    private void descargarArchivo(String rutaCompleta, String nombreArchivo) {
        try {
            File file = new File(rutaCompleta.toString());
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + nombreArchivo + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            ec.setResponseContentType(Files.probeContentType(Paths.get(rutaCompleta)));
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
    }

    public void calcularFechaInicioContrato() {
        if (getObjeto().getFechaInicio() != null) {
            getObjeto().setFechaInicio(null);
        }
    }

    private void calcularFechaSuspensionContrato() {
        if (getObjeto().getFechaSuspension() != null) {
            getObjeto().setFechaSuspension(null);
        }
    }

    public void descargarAdjuntos(CntjContratoInformeAdjunto adjunto) {
        super.setAccion(CntjConstantes.ACCION_NA);
        if (adjunto.isExiste()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            //String filePath = facesContext.getExternalContext().getRequestParameterMap().get("filePath");
            StringBuilder rutaCompleta = new StringBuilder(adjunto.getRuta()).append(adjunto.getArchivo());
            File file = new File(rutaCompleta.toString());
            if (!file.exists()) {
                super.addError("No se encontro el archivo que desea descargar.");
                response.reset();
                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Responde con 204 No Content
                facesContext.responseComplete();
            } else {

                response.reset(); // Resetea la respuesta para evitar conflictos
                response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
                response.setContentLength((int) file.length());
                response.setContentType(FacesContext.getCurrentInstance().getExternalContext().getMimeType(file.getName()));

                facesContext.responseComplete(); // Completa la respuesta antes de enviar el archivo

                try (FileInputStream fileInputStream = new FileInputStream(file); OutputStream responseOutputStream = response.getOutputStream()) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        responseOutputStream.write(buffer, 0, bytesRead);
                    }

                    responseOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            this.addError("El archivo no existe en la ruta de descarga.");
        }
        procesoFinal();

    }

    public void validaMotivoSuspension() {
        if (getObjeto().getFechaSuspension() != null) {
            setHabilitarMotivoSuspension(true);
        } else {
            setHabilitarMotivoSuspension(false);
        }
    }

    public void validarTipoAnticipo() {
        if (getObjeto().getTipoAnticipo() == null || getObjeto().getTipoAnticipo() == CntjConstantes.TIPO_ANTICIPO_NO_PACTADO) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            CntjConstantes.desactivarInputNumber("frmCrear:cvaloranticipo", facesContext);
            CntjConstantes.desactivarInputNumber("frmEditar:cvaloranticipo", facesContext);
            getObjeto().setValorAnticipo(BigDecimal.ZERO);
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            CntjConstantes.activarInputNumber("frmCrear:cvaloranticipo", facesContext);
            CntjConstantes.activarInputNumber("frmEditar:cvaloranticipo", facesContext);
        }
    }

    public void calcularFechaInicioInforme() {
        getObjetoInforme().setMinimaFechaInicio(null);
        if (getObjetoInforme().getTipoInforme().equals(CntjConstantes.TIPO_INFORME_ORDINARIO)) {
            //validamos si ya existe otro informe de tipo ordinario o si es el primero
            boolean existe = getObjeto().getCntjContratoInformeList().stream()
                    .anyMatch(elemento -> elemento.getTipoInforme().equals(CntjConstantes.TIPO_INFORME_ORDINARIO));
            if (existe) {
                //conocemo el tipo de contrato segun el proceso
                Integer tipoContrato = getListaProcesos().stream()
                        .filter(obj -> obj.getId().equals(Integer.valueOf(getObjeto().getProceso())))
                        .map(CntjProceso::getTipoProceso)
                        .findFirst()
                        .orElse(0);

                Optional<CntjContratoInforme> anteriorInforme = getObjeto().getCntjContratoInformeList().stream()
                        .filter(informe -> informe.getTipoInforme().equals(CntjConstantes.TIPO_INFORME_ORDINARIO))
                        .max(Comparator.comparingInt(CntjContratoInforme::getId));

                if (tipoContrato.equals(CntjConstantes.TIPO_PROCESO_SALUD)) {
                    java.util.Date fecha = new java.util.Date(anteriorInforme.get().getFechaInicioPeriodo().getTime());
                    LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate primerDiaMesSiguiente = fechaInicioLocal.plusMonths(1).withDayOfMonth(1);
                    getObjetoInforme().setMinimaFechaInicio(Date.from(primerDiaMesSiguiente.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    java.util.Date fecha = new java.util.Date(anteriorInforme.get().getFechaInicioPeriodo().getTime());
                    if (getObjeto().getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_FIJO)) {
                        LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate proximoInicio = fechaInicioLocal.plusMonths(6).withDayOfMonth(1);
                        getObjetoInforme().setMinimaFechaInicio(Date.from(proximoInicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    } else {
                        LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate proximoInicio = fechaInicioLocal.plusMonths(2).withDayOfMonth(1);
                        getObjetoInforme().setMinimaFechaInicio(Date.from(proximoInicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    }
                }
            } else {
                getObjetoInforme().setMinimaFechaInicio(getObjeto().getFechaInicio());
            }
        }

        if (getObjetoInforme().getTipoInforme().equals(CntjConstantes.TIPO_INFORME_FINAL)) {
            //conocemo el tipo de contrato segun el proceso
            Integer tipoContrato = getListaProcesos().stream()
                    .filter(obj -> obj.getId().equals(Integer.valueOf(getObjeto().getProceso())))
                    .map(CntjProceso::getTipoProceso)
                    .findFirst()
                    .orElse(0);

            if (tipoContrato.equals(CntjConstantes.TIPO_PROCESO_SALUD)) {
                java.util.Date fecha = new java.util.Date(getObjeto().getFechaFin().getTime());
                LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate proximoInicio = fechaInicioLocal.withDayOfMonth(1);
                getObjetoInforme().setMinimaFechaInicio(Date.from(proximoInicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            } else {
                if (getObjeto().getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_FIJO)) {
                    java.util.Date fecha = new java.util.Date(getObjeto().getFechaFin().getTime());
                    LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate proximoInicio = fechaInicioLocal.minusMonths(5).withDayOfMonth(1);
                    getObjetoInforme().setMinimaFechaInicio(Date.from(proximoInicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    java.util.Date fecha = new java.util.Date(getObjeto().getFechaFin().getTime());
                    LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate proximoInicio = fechaInicioLocal.minusMonths(1).withDayOfMonth(1);
                    getObjetoInforme().setMinimaFechaInicio(Date.from(proximoInicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
            }
        }

        calcularFechaFinInforme();
    }

    public void calcularFechaFinInforme() {
        getObjetoInforme().setMaximaFechaFin(null);
        Integer proceso = getListaProcesos().stream()
                .filter(obj -> obj.getId().equals(Integer.valueOf(getObjeto().getProceso())))
                .map(CntjProceso::getTipoProceso)
                .findFirst()
                .orElse(0);
        if (proceso.equals(CntjConstantes.TIPO_PROCESO_SALUD)) {
            if (getObjetoInforme().getFechaInicioPeriodo() != null) {
                java.util.Date fecha = new java.util.Date(getObjetoInforme().getFechaInicioPeriodo().getTime());
                LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate ultimoDiaDelMes = fechaInicioLocal.with(TemporalAdjusters.lastDayOfMonth());
                getObjetoInforme().setMaximaFechaFin(Date.from(ultimoDiaDelMes.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
        } else {
            if (getObjetoInforme().getFechaInicioPeriodo() != null) {
                java.util.Date fecha = new java.util.Date(getObjetoInforme().getFechaInicioPeriodo().getTime());
                LocalDate fechaInicioLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (getObjeto().getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_VARIABLE)) {
                    LocalDate nuevaFecha = fechaInicioLocal.plusMonths(1).withDayOfMonth(1);
                    LocalDate ultimoDiaDelMes = nuevaFecha.with(TemporalAdjusters.lastDayOfMonth());
                    getObjetoInforme().setMaximaFechaFin(Date.from(ultimoDiaDelMes.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    LocalDate nuevaFecha = fechaInicioLocal.plusMonths(5).withDayOfMonth(1);
                    LocalDate ultimoDiaDelMes = nuevaFecha.with(TemporalAdjusters.lastDayOfMonth());
                    getObjetoInforme().setMaximaFechaFin(Date.from(ultimoDiaDelMes.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
            }
        }
    }

    public void validarPeriodicidad() {
        if (getObjeto().getProceso() != null && getObjeto().getFormaPago() != null) {
            if (getHashlistaProceso() != null) {
                CntjProceso proceso = getHashlistaProceso().get(Integer.valueOf(getObjeto().getProceso()));
                if (proceso != null) {
                    if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_ADMINISTRATIVO) && getObjeto().getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_VARIABLE)) {
                        getObjeto().setPeriodicidadSeguimiento(CntjConstantes.PERIODO_MENSUAL);
                    } else if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_ADMINISTRATIVO) && getObjeto().getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_FIJO)) {
                        getObjeto().setPeriodicidadSeguimiento(CntjConstantes.PERIODO_TRIMESTRAL);
                    } else {
                        getObjeto().setPeriodicidadSeguimiento(CntjConstantes.PERIODO_MENSUAL);
                    }
                }
            }

        }
    }

    public void consultarResponsableSecop() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(ACCION_ADICIONAL_1);
        cargarLazyUsuario();
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
        procesoFinal();
    }

    private void cargarLazyUsuario() {
        lazyUsuarios = new LazyDataModel<Usuario>() {
            private List<Usuario> listaUsuario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarUsuarios();
                listaUsuario = getListaUsuario();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return listaUsuario;
            }

            @Override
            public String getRowKey(Usuario usuario) {
                return usuario.getId().toString();
            }

            @Override
            public Usuario getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (Usuario user : listaUsuario) {
                    if (id.equals(user.getId())) {
                        return user;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectResponsableSecop(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        getObjeto().setResponsablePublicaSecop(usuario.getNombre());
        PrimeFaces.current().ajax().update("frmCrear:cresponsableSecop");
        PrimeFaces.current().ajax().update("frmEditar:cresponsableSecop");
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
    }

    public void verResponsableSeguimientos(Integer idSupervisor) {
        setObjetoSupervisor(new CntjContratoSupervisor(idSupervisor));
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void verDetalleGarantia(Integer idGarantia) {
        setObjetoGarantia(new CntjContratoGarantia(idGarantia));
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    public void descargarDocumento(CntjDocumento documento) {
        super.setAccion(CntjConstantes.ACCION_NA);
        if (documento.getDocumentoExiste()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            //String filePath = facesContext.getExternalContext().getRequestParameterMap().get("filePath");
            StringBuilder rutaCompleta = new StringBuilder(documento.getDocumentoRuta()).append(documento.getDocumentoArchivo());
            File file = new File(rutaCompleta.toString());
            if (!file.exists()) {
                super.addError("No se encontro el archivo que desea descargar.");
                response.reset();
                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Responde con 204 No Content
                facesContext.responseComplete();
            } else {

                response.reset(); // Resetea la respuesta para evitar conflictos
                response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
                response.setContentLength((int) file.length());
                response.setContentType(FacesContext.getCurrentInstance().getExternalContext().getMimeType(file.getName()));

                facesContext.responseComplete(); // Completa la respuesta antes de enviar el archivo

                try (FileInputStream fileInputStream = new FileInputStream(file); OutputStream responseOutputStream = response.getOutputStream()) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        responseOutputStream.write(buffer, 0, bytesRead);
                    }

                    responseOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            this.addError("El archivo no existe en la ruta de descarga.");
        }
        procesoFinal();

    }

    public void visualizardocumentos(Integer id) {
        getObjDocumento().setId(id);
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCntjContratoServicio().Accion(this);
        generarDocumentoPdfStream();
        procesoFinal();
    }

    public void generarDocumentoPdfStream() {
        try {
            File file = new File(getObjDocumento().getDocumentoRuta() + getObjDocumento().getDocumentoArchivo());
            if(!file.exists()){
               super.addError("El sistema no puede encontrar el archivo especificado.");
                setContenidoPdf(null);
                generarMensajes();
                return;
            }
            byte[] fileContent = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(fileContent);
            }
            setContenidoPdf(Base64.getEncoder().encodeToString(fileContent));
        } catch (IOException e) {
            e.printStackTrace();
            setContenidoPdf(null);
        }
    }
    
    public void crearExpedienteInforme() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_10);
        getCntjContratoServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmGestion");
                    break;
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmGestion");
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    break;
                case Url.ACCION_CREAR:
                    validarTipoAnticipo();
                    PrimeFaces.current().resetInputs("frmCrear");
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    break;
                case Url.ACCION_EDITAR:
                    validarTipoAnticipo();
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                    PrimeFaces.current().ajax().update("frmEditar");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmTerceroLista");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmUsuariosLista");
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            PrimeFaces.current().ajax().update("frmVerResSeg");
                            PrimeFaces.current().executeScript("PF('dialogoVerResSeguimiento').show()");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmOtrosies");
                            break;
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmCrearOtrosi");
                            PrimeFaces.current().ajax().update("frmCrearOtrosi");
                            PrimeFaces.current().executeScript("PF('dialogoCrearOtrosi').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog(getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmOtrosies");
                            PrimeFaces.current().ajax().update("frmGestion");
                            break;
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVerOtrosi");
                            PrimeFaces.current().executeScript("PF('dialogoVerOtrosi').show()");
                            break;
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().executeScript("PF('dialogoEditarOtrosi').show()");
                            PrimeFaces.current().ajax().update("frmEditarOtrosi");
                            crearLog(getObjetoOtrosi().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog(getObjetoOtrosi().toString());
                            PrimeFaces.current().ajax().update("frmOtrosies");
                            PrimeFaces.current().ajax().update("frmGestion");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog(getObjeto().toString());
                            PrimeFaces.current().executeScript("PF('dialogoSeguimientos').show()");
                            PrimeFaces.current().ajax().update("frmVerSeguimientos");
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            crearLog(getObjeto().toString());
                            PrimeFaces.current().executeScript("PF('dialogoSeguimiento').hide()");
                            PrimeFaces.current().ajax().update("frmVerSeguimientos");
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            crearLog(getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerInformes");
                            PrimeFaces.current().ajax().update("frmheadOptions");
                            PrimeFaces.current().executeScript("PF('dialogoVerInformes').show()");
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            crearLog(getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmCrearInforme");
                            PrimeFaces.current().executeScript("PF('dialogoCrearInforme').show()");
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            crearLog(getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerInformes");
                            PrimeFaces.current().executeScript("PF('dialogoCrearInforme').hide()");
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            PrimeFaces.current().ajax().update("frmEditarInforme");
                            PrimeFaces.current().executeScript("PF('dialogoEditarInforme').show()");
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            PrimeFaces.current().ajax().update("frmVerInformes");
                            PrimeFaces.current().executeScript("PF('dialogoEditarInforme').hide()");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVerGarantia");
                            PrimeFaces.current().executeScript("PF('dialogoverGarantia').show()");
                            break;
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmCrearGarantia");
                            PrimeFaces.current().ajax().update("frmCrearGarantia");
                            PrimeFaces.current().executeScript("PF('dialogoCrearGarantia').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmCrear:tablaGarantia");
                            PrimeFaces.current().ajax().update("frmEditar:tablaGarantia");
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmCrearIndicador");
                            PrimeFaces.current().ajax().update("frmCrearIndicador");
                            PrimeFaces.current().executeScript("PF('dialogoCrearIndicador').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmCrear:tablaIndicador");
                            PrimeFaces.current().ajax().update("frmEditar:tablaIndicador");
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmCrearObligacion");
                            PrimeFaces.current().ajax().update("frmCrearObligacion");
                            PrimeFaces.current().executeScript("PF('dialogoCrearObligacion').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmCrear:tablaObligacion");
                            PrimeFaces.current().ajax().update("frmEditar:tablaObligacion");
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().resetInputs("frmCrearSupervisor");
                            PrimeFaces.current().ajax().update("frmCrearSupervisor");
                            PrimeFaces.current().executeScript("PF('dialogoCrearSupervidor').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmCrear:tablaResponsable");
                            PrimeFaces.current().ajax().update("frmEditar:tablaResponsable");
                            PrimeFaces.current().executeScript("PF('dialogoCrearSupervidor').hide()");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmDocumentos");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmVisualizaDoc");
                            PrimeFaces.current().executeScript("PF('dialogoVisualizarDoc').show()");
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

}
