/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmFacturaConciliacionServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_3;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_VER;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@Named("cmFacturaConciliacionBean")
@ViewScoped
public class CmFacturaConciliacionBean extends Url {

    @Autowired
    private CmFacturaConciliacionServicioIface cmFacturaConciliacionServicio;
    private CmFactura objeto;
    private List<CmFactura> registros;
    private List<CmDetalle> registrosDetalles;
    private List<CmGlosaRespuesta> registrosGlosaRespuesta;
    private List<CmGlosaRespuestaDetalle> registrosGlosaRespuestaDetalle;
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle;
    private List<CmDetalle> registrosDetallesFiltrados;
    private List<CmDetalle> registrosDetallesSelected;
    private List<CmFactura> registrosFacturaSelected;
    private List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa;
    private List<CmAuditoriaConceptoContable> registrosConceptoContable;
    private List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico;
    private List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion;
    private List<CmFactura> registrosConsultaTodasFacturas;
    private List<CmAuditoriaAdjunto> registrosAdjuntosFactura;
    private List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita = new ArrayList<>();
    private CmGlosaRespuesta cmGlosaRespuesta;
    private LazyDataModel<CmFactura> lazyRegistro;
    private LazyDataModel<CmDetalle> lazyDetalles;
    private LazyDataModel<CmGlosaRespuesta> lazyGlosaRespuesta;
    private LazyDataModel<CmGlosaRespuestaDetalle> lazyGlosaRespuestaDetalle;
    private boolean habilitarBusquedaMuliUsuario;
    private Date fechaMaximaCalendario;

    
    public final static int MAX_OBSERVACION_LOGS = 16300;

    public static int TIPO_CAMPO_VALOR_PAGADO_EPS = 1;
    public static int TIPO_CAMPO_VALOR_ACEPTADO_IPS = 2;

    public static int TIPO_CONCILIACION_MASIVA = 1;
    public static int TIPO_CONCILIACION_INDIVIDUAL = 2;

    public static final char ACCION_BUSCAR_FACTURAS_BLOQUEDAS = 'Q';
    public static final char ACCION_LISTAR_DETALLES_SIN_PAGINAR = 'R';
    public static final char ACCION_BUSCAR_TODAS_FACTURAS = 'S';
    public static final char ACCION_VER_FACTURA = 'T';
    public static final char ACCION_LISTAR_GLOSA_RESPUESTA = 'U';
    public static final char ACCION_LISTAR_RESPUESTA_DETALLES = 'W';
    public static final char ACCION_LISTAR_DETALLES = 'X';
    public static final char ACCION_GUARDAR_CONCILIACION_IN = 'Z';
    public static final char ACCION_VER_RESPUESTA_GLOSA = 'a';
    public static final char ACCION_VER_DETALLE = 'b';
    public static final char ACCION_LISTAR_DIAGNOSTICOS = 'c';
    public static final char ACCION_LISTAR_MOTIVOS_GLOSA = 'd';
    public static final char ACCION_LISTAR_CONCEPTOS = 'e';
    public static final char ACCION_LISTAR_AUTORIZACIONES = 'f';
    public static final char ACCION_VER_SERVICIO_AUDITORIA = 'g';
    public static final char ACCION_LISTAR_ADJUNTOS_CMDETALLES = 'i';
    public static final char ACCION_LISTAR_ADJUNTOS_FACTURA  = 'h';
    public static final char ACCION_LISTAR_DESCUENTO_CAPITA = 'k';
    
    private String mensajeGeneral;
    private String mensajeFaceGeneral;
    CmConciliacion cmConciliacion;
    private boolean seleccionarTodosDetalles;
    private boolean seleccionTodasFacturas = false;
    
    
    private HashMap<Integer, CmFactura> hashFacturasSelecionadas;
    private HashMap<Integer, CmDetalle> hashDetallesSelecionados;

    private String facturasBloquedas;
    private String facturasAfectadasLog;

    private ParamConsulta paramConsultaServicios;
    private ParamConsulta paramConsultaRespuestaGlosa;
    private ParamConsulta paramConsultaDestallesGlosa;
    private ParamConsulta paramConsultaUtilitario;

    //Reporte Respuesta Glosa
    private ReporteRespuestaGlosa reporteRespuestaGlosa;
    private List<ReporteRespuestaGlosa> listaReporteRespuestaGlosa;

    //Reporte Consiliacion Masiva
    private ReporteConciliacion reporteConciliacionMasiva;
    private List<ReporteConciliacion> listaReporteConsiliacionMasiva;
    
        
    private CmDetalle detalleEditadoActual;
    private CmDetalle detalleServicioActual;

    public CmFacturaConciliacionBean() {
        this.objeto = new CmFactura();
        Modulo mod = super.validarModulo(Modulo.ID_FACTURA_CONCILIACIONES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        this.fechaMaximaCalendario = new Date();
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyRegistro = new LazyDataModel<CmFactura>() {

                private List<CmFactura> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos
                    if (getRegistros() != null) {
                        for (CmFactura factura : getRegistros()) {
                            if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                                getRegistrosFacturaSelected().add(factura);
                            }
                        }
                    }
                    return lista;
                }

                @Override
                public String getRowKey(CmFactura caso) {
                    return caso.getId().toString();
                }

                @Override
                public CmFactura getRowData(String personaId) {
                    Integer id = Integer.valueOf(personaId);
                    for (CmFactura cmFactura : lista) {
                        if (id.equals(cmFactura.getId())) {
                            return cmFactura;
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
        listar();
    }

    @PreDestroy
    public void preDestroy() {
        this.objeto = null;
    }

    public CmFacturaConciliacionServicioIface getCmFacturaConciliacionServicio() {
        return cmFacturaConciliacionServicio;
    }

    public void setCmFacturaConciliacionServicio(CmFacturaConciliacionServicioIface cmFacturaConciliacionServicio) {
        this.cmFacturaConciliacionServicio = cmFacturaConciliacionServicio;
    }

    public CmFactura getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFactura objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CmFactura> getLazyRegistro() {
        return lazyRegistro;
    }

    public void setLazyRegistro(LazyDataModel<CmFactura> lazyRegistro) {
        this.lazyRegistro = lazyRegistro;
    }

    public List<CmFactura> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFactura> registros) {
        this.registros = registros;
    }

    public boolean isHabilitarBusquedaMuliUsuario() {
        return habilitarBusquedaMuliUsuario;
    }

    public void setHabilitarBusquedaMuliUsuario(boolean habilitarBusquedaMuliUsuario) {
        this.habilitarBusquedaMuliUsuario = habilitarBusquedaMuliUsuario;
    }

    public List<CmDetalle> getRegistrosDetalles() {
        return registrosDetalles;
    }

    public void setRegistrosDetalles(List<CmDetalle> registrosDetalles) {
        this.registrosDetalles = registrosDetalles;
    }

    public LazyDataModel<CmDetalle> getLazyDetalles() {
        return lazyDetalles;
    }

    public void setLazyDetalles(LazyDataModel<CmDetalle> lazyDetalles) {
        this.lazyDetalles = lazyDetalles;
    }

    public Date getFechaMaximaCalendario() {
        return fechaMaximaCalendario;
    }

    public void setFechaMaximaCalendario(Date fechaMaximaCalendario) {
        this.fechaMaximaCalendario = fechaMaximaCalendario;
    }

    public List<CmFactura> getRegistrosFacturaSelected() {
        return registrosFacturaSelected;
    }

    public void setRegistrosFacturaSelected(List<CmFactura> registrosFacturaSelected) {
        this.registrosFacturaSelected = registrosFacturaSelected;
    }

    public LazyDataModel<CmGlosaRespuesta> getLazyGlosaRespuesta() {
        return lazyGlosaRespuesta;
    }

    public void setLazyGlosaRespuesta(LazyDataModel<CmGlosaRespuesta> lazyGlosaRespuesta) {
        this.lazyGlosaRespuesta = lazyGlosaRespuesta;
    }

    public List<CmGlosaRespuesta> getRegistrosGlosaRespuesta() {
        return registrosGlosaRespuesta;
    }

    public void setRegistrosGlosaRespuesta(List<CmGlosaRespuesta> registrosGlosaRespuesta) {
        this.registrosGlosaRespuesta = registrosGlosaRespuesta;
    }

    public List<CmGlosaRespuestaDetalle> getRegistrosGlosaRespuestaDetalle() {
        return registrosGlosaRespuestaDetalle;
    }

    public void setRegistrosGlosaRespuestaDetalle(List<CmGlosaRespuestaDetalle> registrosGlosaRespuestaDetalle) {
        this.registrosGlosaRespuestaDetalle = registrosGlosaRespuestaDetalle;
    }

    public CmGlosaRespuesta getCmGlosaRespuesta() {
        if (cmGlosaRespuesta == null) {
            cmGlosaRespuesta = new CmGlosaRespuesta();
        }
        return cmGlosaRespuesta;
    }
    
    public List<CmAuditoriaAdjunto> getRegistrosAuditoriaAdjutoCmDetalle() {
        if (registrosAuditoriaAdjutoCmDetalle == null) {
            registrosAuditoriaAdjutoCmDetalle = new ArrayList<>();
        }
        return registrosAuditoriaAdjutoCmDetalle;
    }

    public void setRegistrosAuditoriaAdjutoCmDetalle(List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle) {
        this.registrosAuditoriaAdjutoCmDetalle = registrosAuditoriaAdjutoCmDetalle;
    }

    public List<CmAuditoriaAdjunto> getRegistrosAdjuntosFactura() {
        if (registrosAdjuntosFactura == null) {
            registrosAdjuntosFactura = new ArrayList<>();
        }
        return registrosAdjuntosFactura;
    }

    public void setRegistrosAdjuntosFactura(List<CmAuditoriaAdjunto> registrosAdjuntosFactura) {
        this.registrosAdjuntosFactura = registrosAdjuntosFactura;
    }
    
    

    public void setCmGlosaRespuesta(CmGlosaRespuesta cmGlosaRespuesta) {
        this.cmGlosaRespuesta = cmGlosaRespuesta;
    }

    public LazyDataModel<CmGlosaRespuestaDetalle> getLazyGlosaRespuestaDetalle() {
        return lazyGlosaRespuestaDetalle;
    }

    public void setLazyGlosaRespuestaDetalle(LazyDataModel<CmGlosaRespuestaDetalle> lazyGlosaRespuestaDetalle) {
        this.lazyGlosaRespuestaDetalle = lazyGlosaRespuestaDetalle;
    }

    public CmConciliacion getCmConciliacion() {
        if (cmConciliacion == null) {
            cmConciliacion = new CmConciliacion();
        }
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public String getMensajeGeneral() {
        return mensajeGeneral;
    }

    public void setMensajeGeneral(String mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    public boolean isSeleccionarTodosDetalles() {
        return seleccionarTodosDetalles;
    }

    public void setSeleccionarTodosDetalles(boolean seleccionarTodosDetalles) {
        this.seleccionarTodosDetalles = seleccionarTodosDetalles;
    }

    public List<CmDetalle> getRegistrosDetallesSelected() {
        return registrosDetallesSelected;
    }

    public void setRegistrosDetallesSelected(List<CmDetalle> registrosDetallesSelected) {
        this.registrosDetallesSelected = registrosDetallesSelected;
    }

    public String getMensajeFaceGeneral() {
        return mensajeFaceGeneral;
    }

    public void setMensajeFaceGeneral(String mensajeFaceGeneral) {
        this.mensajeFaceGeneral = mensajeFaceGeneral;
    }

    public boolean isSeleccionTodasFacturas() {
        return seleccionTodasFacturas;
    }

    public void setSeleccionTodasFacturas(boolean seleccionTodasFacturas) {
        this.seleccionTodasFacturas = seleccionTodasFacturas;
    }

    public List<CmFactura> getRegistrosConsultaTodasFacturas() {
        return registrosConsultaTodasFacturas;
    }

    public void setRegistrosConsultaTodasFacturas(List<CmFactura> registrosConsultaTodasFacturas) {
        this.registrosConsultaTodasFacturas = registrosConsultaTodasFacturas;
    }

    public HashMap<Integer, CmFactura> getHashFacturasSelecionadas() {
        if (hashFacturasSelecionadas == null) {
            hashFacturasSelecionadas = new HashMap<>();
        }
        return hashFacturasSelecionadas;
    }

    public void setHashFacturasSelecionadas(HashMap<Integer, CmFactura> hashFacturasSelecionadas) {
        this.hashFacturasSelecionadas = hashFacturasSelecionadas;
    }

    public HashMap<Integer, CmDetalle> getHashDetallesSelecionados() {
        if (hashDetallesSelecionados == null) {
            hashDetallesSelecionados = new HashMap<>();
        }
        return hashDetallesSelecionados;
    }

    public void setHashDetallesSelecionados(HashMap<Integer, CmDetalle> hashDetallesSelecionados) {
        this.hashDetallesSelecionados = hashDetallesSelecionados;
    }

    public List<CmDetalle> getRegistrosDetallesFiltrados() {
        return registrosDetallesFiltrados;
    }

    public void setRegistrosDetallesFiltrados(List<CmDetalle> registrosDetallesFiltrados) {
        this.registrosDetallesFiltrados = registrosDetallesFiltrados;
    }

    public String getFacturasBloquedas() {
        return facturasBloquedas;
    }

    public void setFacturasBloquedas(String facturasBloquedas) {
        this.facturasBloquedas = facturasBloquedas;
    }

    public String getFacturasAfectadasLog() {
        return facturasAfectadasLog;
    }

    public void setFacturasAfectadasLog(String facturasAfectadasLog) {
        this.facturasAfectadasLog = facturasAfectadasLog;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public ParamConsulta getParamConsultaServicios() {
        if (paramConsultaServicios == null) {
            paramConsultaServicios = new ParamConsulta();
        }
        return paramConsultaServicios;
    }

    public void setParamConsultaServicios(ParamConsulta paramConsultaServicios) {
        this.paramConsultaServicios = paramConsultaServicios;
    }

    public ParamConsulta getParamConsultaRespuestaGlosa() {
        if (paramConsultaRespuestaGlosa == null) {
            paramConsultaRespuestaGlosa = new ParamConsulta();
        }
        return paramConsultaRespuestaGlosa;
    }

    public void setParamConsultaRespuestaGlosa(ParamConsulta paramConsultaRespuestaGlosa) {
        this.paramConsultaRespuestaGlosa = paramConsultaRespuestaGlosa;
    }

    public ParamConsulta getParamConsultaDestallesGlosa() {
        if (paramConsultaDestallesGlosa == null) {
            paramConsultaDestallesGlosa = new ParamConsulta();
        }
        return paramConsultaDestallesGlosa;
    }

    public void setParamConsultaDestallesGlosa(ParamConsulta paramConsultaDestallesGlosa) {
        this.paramConsultaDestallesGlosa = paramConsultaDestallesGlosa;
    }

    public ParamConsulta getParamConsultaUtilitario() {
        if (paramConsultaUtilitario == null) {
            paramConsultaUtilitario = new ParamConsulta();
        }
        return paramConsultaUtilitario;
    }

    public void setParamConsultaUtilitario(ParamConsulta paramConsultaUtilitario) {
        this.paramConsultaUtilitario = paramConsultaUtilitario;
    }

    public CmDetalle getDetalleEditadoActual() {
        if (detalleEditadoActual == null) {
            detalleEditadoActual = new CmDetalle();
        }
        return detalleEditadoActual;
    }

    public void setDetalleEditadoActual(CmDetalle detalleEditadoActual) {
        this.detalleEditadoActual = detalleEditadoActual;
    }

    public CmDetalle getDetalleServicioActual() {
        if (detalleServicioActual == null) {
            detalleServicioActual = new CmDetalle();
        }
        return detalleServicioActual;
    }

    public void setDetalleServicioActual(CmDetalle detalleServicioActual) {
        this.detalleServicioActual = detalleServicioActual;
    }

    public List<CmAuditoriaMotivoGlosa> getRegistrosAuditoriaMotivoGlosa() {
        if (registrosAuditoriaMotivoGlosa == null) {
            registrosAuditoriaMotivoGlosa = new ArrayList<>();
        }
        return registrosAuditoriaMotivoGlosa;
    }

    public void setRegistrosAuditoriaMotivoGlosa(List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa) {
        this.registrosAuditoriaMotivoGlosa = registrosAuditoriaMotivoGlosa;
    }

    public List<CmAuditoriaDiagnostico> getRegistrosAuditoriaDiagnostico() {
        if (registrosAuditoriaDiagnostico == null) {
            registrosAuditoriaDiagnostico = new ArrayList<>();
        }
        return registrosAuditoriaDiagnostico;
    }

    public void setRegistrosAuditoriaDiagnostico(List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico) {
        this.registrosAuditoriaDiagnostico = registrosAuditoriaDiagnostico;
    }

    public List<CmAuditoriaConceptoContable> getRegistrosConceptoContable() {
        if (registrosConceptoContable == null) {
            registrosConceptoContable = new ArrayList<>();
        }
        return registrosConceptoContable;
    }

    public void setRegistrosConceptoContable(List<CmAuditoriaConceptoContable> registrosConceptoContable) {
        this.registrosConceptoContable = registrosConceptoContable;
    }

    public List<CmAuditoriaAutorizacion> getRegistrosAuditoriaAutorizacion() {
        if (registrosAuditoriaAutorizacion == null) {
            registrosAuditoriaAutorizacion = new ArrayList<>();
        }
        return registrosAuditoriaAutorizacion;
    }

    public void setRegistrosAuditoriaAutorizacion(List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion) {
        this.registrosAuditoriaAutorizacion = registrosAuditoriaAutorizacion;
    }

    public List<CmAuditoriaCapitaDescuento> getRegistrosDescuentoCapita() {
        return registrosDescuentoCapita;
    }

    public void setRegistrosDescuentoCapita(List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita) {
        this.registrosDescuentoCapita = registrosDescuentoCapita;
    }  

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmFacturaConciliacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmConciliaciones");
    }

    public void refrescarGlosaRespuesta() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_GLOSA_RESPUESTA);
        getCmFacturaConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES);
        getCmFacturaConciliacionServicio().Accion(this);
    }

    public void refrescarGlosaRespuestaDetalle() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_RESPUESTA_DETALLES);
        getCmFacturaConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarDetallesSinPaginar() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_SIN_PAGINAR);
        getCmFacturaConciliacionServicio().Accion(this);
        procesoFinal();
    }

    public void verFactura(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_FACTURA);
        getCmFacturaConciliacionServicio().Accion(this);
        listarAdjuntosFactura();
        inicializarTableDetalles(_id);
        inicializarTablaGlosaRespuesta(_id);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("scrollPanelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verDetalle() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_DETALLE);
        getCmFacturaConciliacionServicio().Accion(this);
    }

    public void verServicioAuditado(int idServicio) {

        getDetalleServicioActual().setId(idServicio);

        verDetalle();
        listarConceptosContables();
        listarDiagnosticos();
        listarMotivosGlosa();
        listarAutorizaciones();
        listarAdjuntosCmDetalles();
        listarDescuentoCapita();
        PrimeFaces.current().resetInputs("frmVerAuditoriaServicio");
        PrimeFaces.current().ajax().update("frmVerAuditoriaServicio");
        PrimeFaces.current().executeScript("PF('dialogoVerAuditoriaServicio').show()");
        setDoAccion(ACCION_VER_SERVICIO_AUDITORIA);
        procesoFinal();
    }

    public void verRespuestaDestallesGlosa(int _idGlosaRespuesta) {
        this.getCmGlosaRespuesta().setId(_idGlosaRespuesta);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_RESPUESTA_GLOSA);
        getCmFacturaConciliacionServicio().Accion(this);
        inicializarTablaGlosaRespuestaDetalles(_idGlosaRespuesta);
        PrimeFaces.current().resetInputs("frmVerGlosaRespuesas");
        PrimeFaces.current().ajax().update("frmVerGlosaRespuesas");
        PrimeFaces.current().executeScript("PF('dialogoVerGlosaRespuestaDetalles').show()");
        super.setDoAccion(ACCION_VER_RESPUESTA_GLOSA);
        procesoFinal();
    }

    public void verConciliarMasivo() {

        limpiarFormularioResponderConciliacion();

        this.getRegistrosFacturaSelected().clear();
        boolean esValidoProceso = true;
        String nitUnificado = "";

        if (esValidoProceso && getHashFacturasSelecionadas() == null || getHashFacturasSelecionadas().isEmpty()) {
            this.addError("Para realizar la operación debe seleccionar facturas");
            esValidoProceso = false;
        }

        if (esValidoProceso && this.getParamConsulta().getFiltros() != null
                && this.getParamConsulta().getFiltros().size() > 2) {
            this.addError("Para realizar la operación no debe existir mas de 1 filtro aplicado a la lista");
            esValidoProceso = false;
        }

        setRegistrosFacturaSelected(new ArrayList<>(getHashFacturasSelecionadas().values()));

        esValidoProceso = verificarFacturasBloquedas(getRegistrosFacturaSelected());

        if (esValidoProceso) {
            CmConciliacion conciliacion = this.getCmConciliacion();
            conciliacion.setValorFacturasGlosaInicialPrecalculado(BigDecimal.ZERO);
            conciliacion.setValorFacturasPendienteActualPrecalculado(BigDecimal.ZERO);
            conciliacion.setValorFacturasPrecalculado(BigDecimal.ZERO);
            conciliacion.setValorFacturasPendienteActualMasivo(BigDecimal.ZERO);

            int origenFacturaUnificado = 0;
            String numeroFacturadoInicial = "";
            for (CmFactura factura : getRegistrosFacturaSelected()) {
                if (nitUnificado.equals("")) {
                    nitUnificado = factura.getNit();
                }
                if (!factura.getNit().equals(nitUnificado)) {
                    addError("Las facturas deben tener el mismo Nit para ser procesadas, Nit filtrado: " + nitUnificado + ", Nit diferente: " + factura.getNit());
                    esValidoProceso = false;
                    break;
                }

                if (origenFacturaUnificado == 0) {
                    origenFacturaUnificado = factura.getOrigenFactura();
                    numeroFacturadoInicial = factura.getNumeroFacturado();
                }
                if (factura.getOrigenFactura() != origenFacturaUnificado) {
                    addError("Las facturas deben tener el mismo origen de factura para ser procesadas, "
                            + "origen encontrado:( " + CmFactura.getOrigenFacturaStr(origenFacturaUnificado)
                            + "  número factura:" + numeroFacturadoInicial + ") "
                            + ", origen diferente: (" + CmFactura.getOrigenFacturaStr(factura.getOrigenFactura())
                            + "  número factura:" + factura.getNumeroFacturado() + ")");
                    esValidoProceso = false;
                    break;
                }

                if (factura.getValorPendienteActual() != null
                        && factura.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0) {

                    conciliacion.setValorFacturasGlosaInicialPrecalculado(
                            conciliacion.getValorFacturasGlosaInicialPrecalculado().
                                    add(factura.getValorInicialGlosa()).
                                    setScale(4, RoundingMode.HALF_UP)
                    );
                    conciliacion.setValorFacturasPendienteActualPrecalculado(
                            conciliacion.getValorFacturasPendienteActualPrecalculado().
                                    add(factura.getValorPendienteActual()).
                                    setScale(4, RoundingMode.HALF_UP)
                    );

                    conciliacion.setValorFacturasPendienteActualMasivo(
                            conciliacion.getValorFacturasPendienteActualMasivo().
                                    add(factura.getValorPendienteActual()).
                                    setScale(4, RoundingMode.HALF_UP)
                    );

                    conciliacion.setValorFacturasPrecalculado(
                            conciliacion.getValorFacturasPrecalculado().
                                    add(factura.getValorFactura()).
                                    setScale(4, RoundingMode.HALF_UP)
                    );
                } else {
                    factura.setTipoCalculoConciliacion(CmFactura.TIPO_CALCULO_CONCILIACION_CON);
                }

            }
        }

        if (esValidoProceso) {

            actualizaBloqueoFacturas(true);

            if (!this.isError()) {
                super.setAccion(ACCION_EDITAR);
                getObjeto().setNit(nitUnificado);
                getObjeto().setHistorialProceso("Conciliación masiva");

                getHashFacturasSelecionadas().clear();
                PrimeFaces.current().resetInputs("frmResponderConciliaciones");
                PrimeFaces.current().ajax().update("frmResponderConciliaciones");
                PrimeFaces.current().executeScript("PF('dialogoGestionarConciliaciones').show()");
            }

        }
        procesoFinal();
    }

    public void verConciliarIndividual(int _idFactura) {

        getObjeto().setId(_idFactura);
        List<CmFactura> list = new ArrayList<>();
        list.add(getObjeto());
        verificarFacturasBloquedas(list);
        if (getRegistrosDetallesSelected() != null) {
            getRegistrosDetallesSelected().clear();
        }

        if (!this.isError()) {
            super.setAccion(ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_VER_FACTURA);
            getCmFacturaConciliacionServicio().Accion(this);
            inicializarTableDetallesSinPaginar();
            actualizarSumatoriaValores();
            actualizaBloqueoFactura(true);
            super.setAccion(ACCION_EDITAR);
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual");
            PrimeFaces.current().executeScript("PF('dialogoVerConciliacionIndividual').show()");
        }

        procesoFinal();
    }

    public void verReponderConciliacionIndividual() {
        this.getObjeto().getCmGlosaRespuestaDetalle().setTipoEstadoRespuesta(0);
        this.getObjeto().getCmGlosaRespuestaDetalle().setObservacion("");
        PrimeFaces.current().resetInputs("frmReponderConciliacion");
        PrimeFaces.current().ajax().update("frmReponderConciliacion");
        PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacion').show()");
    }

    public void verReponderConciliacionMasiva() {
        this.getCmConciliacion().setTipoEstadoRespuesta(0);
        this.getCmConciliacion().setObservacion("");
        PrimeFaces.current().resetInputs("frmReponderConciliacionMasiva");
        PrimeFaces.current().ajax().update("frmReponderConciliacionMasiva");
        PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacionMasiva').show()");
    }

    private void procesoFinal() {

        String descLog = "{Conciliaciones}";
        try {
            descLog = getObjeto().toString();
        } catch (Exception e) {
        }
        descLog = descLog.length() > 2045 ? descLog.substring(0, 2040) : descLog;

        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                    switch (getDoAccion()) {
                        case ACCION_VER_FACTURA:
                            crearLog(getObjeto().toString());
                            break;
                        case ACCION_VER_SERVICIO_AUDITORIA:
                            crearLog("Ver Auditoría Servicio", getDetalleServicioActual().toString());
                            break;
                        case ACCION_VER_RESPUESTA_GLOSA:
                            crearLog("Ver Detalles Glosa", this.getCmGlosaRespuesta().toString());
                            break;
                    }
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(descLog);
                    break;
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    crearLog(descLog);
                    PrimeFaces.current().ajax().update("frmConciliaciones");
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            crearLog("Gestionar", "Lista de Registros a conciliar");
                            break;
                        case Url.ACCION_GUARDAR:
                            setFacturasAfectadasLog("");
                            PrimeFaces.current().ajax().update("frmConciliaciones");
                            break;
                    }
                    break;

                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR_CONCILIACION_IN:
                            PrimeFaces.current().ajax().update("frmConciliaciones");
                            break;
                    }
                    break;

                case ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR:
                            crearLog("Bloquear/Desbloquear Facturas", getMensajeGeneral());
                            setMensajeGeneral("");
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    private void procesoFinalConciliacionIndividual() {
        try {
            String desc = " ---> Factura: " + this.getObjeto() + "  ---> Config: " + getObjeto().getCmGlosaRespuestaDetalle().toString();
            crearLog("Guardar Conciliación Ind", StringUtils.abbreviate(desc, MAX_OBSERVACION_LOGS));
        } catch (Exception e) {
        }
    }

    private void procesoFinalConciliacionMasiva() {
        try {
            crearLog("Guardar Conciliación Mas", StringUtils.abbreviate(" ---> Id facturas procesadas : " + getFacturasAfectadasLog() + ", ---> Config: " + this.getCmConciliacion().toString(), MAX_OBSERVACION_LOGS));
        } catch (Exception e) {
        }
    }

    public void inicializarTableDetalles(Integer idFactura) {
        this.setParamConsultaServicios(new ParamConsulta());
        lazyDetalles = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaServicios().getCantidadRegistros();
            }

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaServicios().setPrimerRegistro(primerRegistro);
                getParamConsultaServicios().setRegistrosPagina(registrosPagina);
                getParamConsultaServicios().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaServicios().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDetalles();
                lista = getRegistrosDetalles();
                setRowCount(getParamConsultaServicios().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle caso) {
                return caso.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmDetalle cmDetalle : lista) {
                    if (id.equals(cmDetalle.getId())) {
                        return cmDetalle;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaGlosaRespuesta(int idGlosa) {
        this.setParamConsultaRespuestaGlosa(new ParamConsulta());
        this.getParamConsultaRespuestaGlosa().setParametroConsulta1(idGlosa);
        lazyGlosaRespuesta = new LazyDataModel<CmGlosaRespuesta>() {
            private List<CmGlosaRespuesta> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaRespuestaGlosa().getCantidadRegistros();
            }

            @Override
            public List<CmGlosaRespuesta> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaRespuestaGlosa().setPrimerRegistro(primerRegistro);
                getParamConsultaRespuestaGlosa().setRegistrosPagina(registrosPagina);
                getParamConsultaRespuestaGlosa().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaRespuestaGlosa().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGlosaRespuesta();
                lista = getRegistrosGlosaRespuesta();
                setRowCount(getParamConsultaRespuestaGlosa().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmGlosaRespuesta respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public CmGlosaRespuesta getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (CmGlosaRespuesta cmRespuesta : lista) {
                    if (id.equals(cmRespuesta.getId())) {
                        return cmRespuesta;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaGlosaRespuestaDetalles(int _idGlosaRespuesta) {
        this.setParamConsultaDestallesGlosa(new ParamConsulta());
        getParamConsultaDestallesGlosa().setParametroConsulta1(_idGlosaRespuesta);
        lazyGlosaRespuestaDetalle = new LazyDataModel<CmGlosaRespuestaDetalle>() {
            private List<CmGlosaRespuestaDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaDestallesGlosa().getCantidadRegistros();
            }

            @Override
            public List<CmGlosaRespuestaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaDestallesGlosa().setPrimerRegistro(primerRegistro);
                getParamConsultaDestallesGlosa().setRegistrosPagina(registrosPagina);
                getParamConsultaDestallesGlosa().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaDestallesGlosa().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGlosaRespuestaDetalle();
                lista = getRegistrosGlosaRespuestaDetalle();
                setRowCount(getParamConsultaDestallesGlosa().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmGlosaRespuestaDetalle cmGlosaRespuestaDetalle) {
                return cmGlosaRespuestaDetalle.getId().toString();
            }

            @Override
            public CmGlosaRespuestaDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmGlosaRespuestaDetalle cmGlosaRespuestaDetalle : lista) {
                    if (id.equals(cmGlosaRespuestaDetalle.getId())) {
                        return cmGlosaRespuestaDetalle;
                    }
                }
                return null;
            }
        };
    }

    public void responderConciliacionMasiva() {

        boolean validacionProceso = true;
        CmConciliacion conciliacion = this.getCmConciliacion();

        BigDecimal valorIPSTotal = conciliacion.getValorFacturasAceptadoIpsPrecalculado() == null
                ? new BigDecimal("0") : conciliacion.getValorFacturasAceptadoIpsPrecalculado();
        BigDecimal valorEPSTotal = conciliacion.getValorFacturasPagadoEpsPrecalculado() == null
                ? new BigDecimal("0") : conciliacion.getValorFacturasPagadoEpsPrecalculado();
        BigDecimal porcentajeIPS = conciliacion.getPorcentajeAceptadoIps() == null
                ? new BigDecimal("0") : conciliacion.getPorcentajeAceptadoIps();
        BigDecimal porcentajeEPS = conciliacion.getPorcentajePagadoEps() == null
                ? new BigDecimal("0") : conciliacion.getPorcentajePagadoEps();
        BigDecimal valorSumaReponder = new BigDecimal("0");

        String responsableEPS = conciliacion.getRepresentanteEps() == null ? "" : conciliacion.getRepresentanteEps();
        String responsableIPS = conciliacion.getRepresentanteIps() == null ? "" : conciliacion.getRepresentanteIps();

        if (conciliacion.getValorFacturasPendienteActualPrecalculado().compareTo(new BigDecimal("0")) == 0) {
            this.addError("El valor suma pendiente actual debe ser mayor a cero.");
            validacionProceso = false;
        }

        if (validacionProceso && (responsableEPS.equals("")
                || responsableIPS.equals(""))) {
            this.addError("Para realizar el proceso se debe ingresar el representante EPS y representante IPS ");
            validacionProceso = false;
        }

        if (validacionProceso && !validarLongitudRepresentantes(responsableEPS, responsableIPS)) {
            this.addError("La longitud del campo representante EPS y representante IPS deben ser mayor a 7 dígitos.");
            validacionProceso = false;
        }

        if (validacionProceso) {
            int facturasVerificadas = 0;
            int cantidadFacturasVerificar = getRegistrosFacturaSelected().size();
            for (CmFactura factura : getRegistrosFacturaSelected()) {
                if (CmFactura.TIPO_CALCULO_CONCILIACION_IND == factura.getTipoCalculoConciliacion()
                        || CmFactura.TIPO_CALCULO_CONCILIACION_MAS == factura.getTipoCalculoConciliacion()
                        || CmFactura.TIPO_CALCULO_CONCILIACION_CON == factura.getTipoCalculoConciliacion()) {
                    facturasVerificadas += 1;
                }
            }

            if (facturasVerificadas != cantidadFacturasVerificar) {
                this.addError("Todas las facturas deben tener el cálculo EPS o Aceptado IPS para poder continuar.");
                validacionProceso = false;
            }
        }

        if (validacionProceso) {

            conciliacion.setValorAceptadoIps(valorIPSTotal);
            conciliacion.setValorPagadoEps(valorEPSTotal);
            conciliacion.setPorcentajeAceptadoIps(porcentajeIPS);
            conciliacion.setPorcentajePagadoEps(porcentajeEPS);

            BigDecimal valorBaseComparacion = conciliacion.getValorFacturasPendienteActualPrecalculado();
            valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0") : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);

            valorSumaReponder = valorSumaReponder.add(valorIPSTotal).
                    add(valorEPSTotal).setScale(2, RoundingMode.HALF_UP);

            if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
                this.addError("La sumatoria de los valores ingresados no debe superar el valor suma pendiente actual.");
                validacionProceso = false;
            }
        }

        if (validacionProceso) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_GUARDAR);
            getCmFacturaConciliacionServicio().Accion(this);
            procesoFinalConciliacionMasiva();
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacionMasiva').hide();");
            PrimeFaces.current().executeScript("PF('dialogoGestionarConciliaciones').hide();");
        } else {
            PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacionMasiva').hide();");
            PrimeFaces.current().resetInputs("frmResponderConciliaciones");
            PrimeFaces.current().ajax().update("frmResponderConciliaciones");
            procesoFinal();
        }

    }

    public void habilitarBusquedaUsuarioMultiple() {
        PrimeFaces.current().resetInputs("frmConciliaciones");
        PrimeFaces.current().ajax().update("frmConciliaciones");
    }

    public void mostrarMensajeGeneral(String mensaje) {
        setMensajeGeneral(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void actualizarPanelMostrarMensaje() {
        PrimeFaces.current().ajax().update("frmObservacion");
    }

    public void cerrarVentanaConciliacionMasiva() {
        actualizaBloqueoFacturas(false);
        PrimeFaces.current().executeScript("PF('tablaWidgetFacturas').unselectAllRows();");

        for (CmFactura factura : getRegistrosFacturaSelected()) {
            factura.setPorcentajePagadoEPS(null);
            factura.setPorcentajeAceptadoIPS(null);
            factura.setValorAceptadoIPS(null);
            factura.setValorPagadoEPS(null);
            factura.setObservacion(null);
            factura.setTipoCalculoConciliacion(0);
        }

        this.getCmConciliacion().setValorFacturasPagadoEpsPrecalculado(null);
        this.getCmConciliacion().setValorFacturasAceptadoIpsPrecalculado(null);

        getHashFacturasSelecionadas().clear();
        getRegistrosFacturaSelected().clear();
        super.setDoAccion(ACCION_GUARDAR);
        if (super.getMensajes() != null && (super.getMensajes().size() > 0 || super.isError())) {
            procesoFinal();
        }

    }

    public void limpiarFormularioResponderConciliacion() {
        this.getCmConciliacion().setValorPagadoEps(null);
        this.getCmConciliacion().setPorcentajePagadoEps(null);
        this.getCmConciliacion().setValorAceptadoIps(null);
        this.getCmConciliacion().setPorcentajeAceptadoIps(null);
        this.getCmConciliacion().setObservacion(null);
        this.getCmConciliacion().setTipoEstadoRespuesta(0);
    }

    private void actualizaBloqueoFactura(boolean bloquear) {
        this.setParamConsultaUtilitario(new ParamConsulta());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);

        int idUsuarioSolicita = this.getConexion().getUsuario().getId();
        int idAsignacionUsuario = bloquear ? idUsuarioSolicita : 0;

        this.getParamConsultaUtilitario().setParametroConsulta1(idAsignacionUsuario);
        this.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioSolicita);
        this.getParamConsultaUtilitario().setParametroConsulta3(this.getObjeto().getId());
        getCmFacturaConciliacionServicio().Accion(this);
    }

    private void actualizaBloqueoFacturas(boolean bloquear) {
        this.setParamConsultaUtilitario(new ParamConsulta());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        String idFacturas = "";
        StringBuilder sb = new StringBuilder();
        for (CmFactura cmFactura : getRegistrosFacturaSelected()) {
            sb.append(cmFactura.getId()).append(",");
        }
        if (sb.length() > 0) {
            idFacturas = sb.deleteCharAt(sb.length() - 1).toString();
            int idUsuarioSolicitaGestion = this.getConexion().getUsuario().getId();
            int idAsignacionUsuario = bloquear ? idUsuarioSolicitaGestion : 0;
            this.getParamConsultaUtilitario().setParametroConsulta1(idAsignacionUsuario);
            this.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioSolicitaGestion);
            this.getParamConsultaUtilitario().setParametroConsulta3(idFacturas);
            getCmFacturaConciliacionServicio().Accion(this);
        }
    }

    public void actualizarDesbloqueoFacturasMasivo() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR);
        if (this.getRegistrosFacturaSelected().isEmpty()) {
            this.addError("Para realizar la operación debe seleccionar facturas");
        } else {
            getCmFacturaConciliacionServicio().Accion(this);
        }

        if (!isError()) {
            addMensaje("El desbloqueo de facturas se ha realizado.");
            PrimeFaces.current().executeScript("PF('tablaWidgetFacturas').unselectAllRows();");
            this.getRegistrosFacturaSelected().clear();
        }
        procesoFinal();
    }

    public void limpiarFormularioRepuestaIndividual() {
        this.getObjeto().getCmGlosaRespuestaDetalle().setValorPagadoEps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setPorcentajePagadoEps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setValorAceptadoIps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setPorcentajeAceptadoIps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setTipoEstadoRespuesta(0);
        this.getObjeto().getCmGlosaRespuestaDetalle().setObservacion("");
    }

    public void refrescarPantallaResponpuestaConcilicionIndividual() {
        limpiarFormularioRepuestaIndividual();
        PrimeFaces.current().resetInputs("frmVerConciliacionIndividual");
        PrimeFaces.current().ajax().update("frmVerConciliacionIndividual");
    }

    public void listarDiagnosticos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DIAGNOSTICOS);
        getCmFacturaConciliacionServicio().Accion(this);
    }

    public void listarConceptosContables() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CONCEPTOS);
        getCmFacturaConciliacionServicio().Accion(this);
    }

    public void listarMotivosGlosa() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_GLOSA);
        getCmFacturaConciliacionServicio().Accion(this);
    }

    public void listarAutorizaciones() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUTORIZACIONES);
        getCmFacturaConciliacionServicio().Accion(this);
    }
    
    public void listarAdjuntosCmDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_CMDETALLES);
        getCmFacturaConciliacionServicio().Accion(this);
    }
    
    public void listarAdjuntosFactura() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_FACTURA);
        getCmFacturaConciliacionServicio().Accion(this);
    }
    
    public void listarDescuentoCapita() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DESCUENTO_CAPITA);
        getCmFacturaConciliacionServicio().Accion(this);
    }
     
    public void responderConciliacionIndividual() {

        boolean validacionProceso = true;

        CmGlosaRespuestaDetalle respuestaGlosa = this.getObjeto().getCmGlosaRespuestaDetalle();
        BigDecimal valorIPS = respuestaGlosa.getValorAceptadoIpsPrecalculado() == null
                ? new BigDecimal("0") : respuestaGlosa.getValorAceptadoIpsPrecalculado();
        BigDecimal valorEPS = respuestaGlosa.getValorPagadoEpsPrecalculado() == null
                ? new BigDecimal("0") : respuestaGlosa.getValorPagadoEpsPrecalculado();

        String responsableEPS = respuestaGlosa.getRepresentanteEps() == null ? "" : respuestaGlosa.getRepresentanteEps();
        String responsableIPS = respuestaGlosa.getRepresentanteIps() == null ? "" : respuestaGlosa.getRepresentanteIps();

        BigDecimal valorPendienteActualFactura = this.getObjeto().getValorPendienteActual() == null
                ? new BigDecimal("0") : this.getObjeto().getValorPendienteActual();
        BigDecimal sumaPendienteActualDetalles = respuestaGlosa.getValorPendienteActualItemsSeleccionados() == null
                ? new BigDecimal("0") : respuestaGlosa.getValorPendienteActualItemsSeleccionados();

        if (respuestaGlosa.getValorPendienteActualPrecalculado().compareTo(new BigDecimal("0")) == 0) {
            this.addError("El valor suma pendiente actual debe ser mayor a cero.");
            validacionProceso = false;
        }

        if (validacionProceso && valorIPS.compareTo(new BigDecimal("0")) == 0 && valorEPS.compareTo(new BigDecimal("0")) == 0) {
            this.addError("Para realizar el proceso los detalles o servicios deseados deben tener valor EPS o Valor aceptado IPS ");
            validacionProceso = false;
        }

        if (validacionProceso && (responsableEPS.equals("")
                || responsableIPS.equals(""))) {
            this.addError("Para realizar el proceso se debe ingresar el representante EPS y representante IPS ");
            validacionProceso = false;
        }

        if (validacionProceso && !validarLongitudRepresentantes(responsableEPS, responsableIPS)) {
            this.addError("La longitud del campo representante EPS y representante IPS deben ser mayor a 7 dígitos .");
            validacionProceso = false;
        }

        if (validacionProceso && sumaPendienteActualDetalles.compareTo(valorPendienteActualFactura) > 0) {
            this.addError("La sumatoria de los valores pendientes actuales de detalles superan el pendiente actual de factura, por favor verifique los datos.");
            validacionProceso = false;
        }

        if (validacionProceso) {
            super.setAccion(ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_GUARDAR_CONCILIACION_IN);
            getCmFacturaConciliacionServicio().Accion(this);
            procesoFinalConciliacionIndividual();
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoRegistrarConciliacion').hide();");
            PrimeFaces.current().executeScript("PF('dialogoVerConciliacionIndividual').hide();");
        } else {
            PrimeFaces.current().resetInputs("frmVerConciliacionIndividual");
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual");
            PrimeFaces.current().ajax().update("frmReponderConciliacion");
            procesoFinal();
        }

    }

    public void conversionPorcentajeConciliacionIndividual(int tipoCampo) {
        //MathContext mc  = new MathContext(4);      
        CmGlosaRespuestaDetalle respuesta = this.getObjeto().getCmGlosaRespuestaDetalle();

        BigDecimal valorLimite = respuesta.getValorPendienteActualItemsSeleccionados();
        BigDecimal valorPropuesto = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? respuesta.getValorPagadoEps()
                : respuesta.getValorAceptadoIps();

        BigDecimal promedio;
        BigDecimal procentajeCalculado = new BigDecimal("0");

        if (valorPropuesto != null && valorLimite != null) {
            if (valorPropuesto.compareTo(valorLimite) > 0) {
                String msgError = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                        ? "El valor pagado EPS no puede ser mayor que valor suma pendiente actual"
                        : "El valor aceptado IPS no puede ser mayor que valor suma pendiente actual";
                this.addError(msgError);

                if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
                    respuesta.setValorPagadoEps(null);
                } else {
                    respuesta.setValorAceptadoIps(null);
                }
            } else {
                promedio = valorPropuesto.divide(valorLimite, 5, RoundingMode.CEILING);
                procentajeCalculado = promedio.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
            }
        }

        if (procentajeCalculado.equals(new BigDecimal(BigInteger.ZERO))) {
            procentajeCalculado = null;
            generarMensajes();
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            respuesta.setPorcentajePagadoEps(procentajeCalculado);
        } else {
            respuesta.setPorcentajeAceptadoIps(procentajeCalculado);
        }
    }

    public void conversionValorConciliacionIndividual(int tipoCampo) {
        //MathContext mc  = new MathContext(4);
        CmGlosaRespuestaDetalle respuesta = this.getObjeto().getCmGlosaRespuestaDetalle();

        BigDecimal valorFinal = new BigDecimal("0");

        BigDecimal porcentaje = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? respuesta.getPorcentajePagadoEps()
                : respuesta.getPorcentajeAceptadoIps();

        BigDecimal valorLimite = respuesta.getValorPendienteActualItemsSeleccionados();;

        if (porcentaje != null && valorLimite != null && !porcentaje.equals(new BigDecimal(BigInteger.ZERO))) {
            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            valorFinal = promedio.multiply(valorLimite).setScale(4, RoundingMode.HALF_UP);
        }

        if (valorFinal.equals(new BigDecimal(BigInteger.ZERO))) {
            valorFinal = null;
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            respuesta.setValorPagadoEps(valorFinal);
        } else {
            respuesta.setValorAceptadoIps(valorFinal);
        }

    }

    public void conversionPorcentajeConciliacionMasiva(int tipoCampo) {
        CmConciliacion cmConciliacion = this.getCmConciliacion();

        BigDecimal valorLimite = cmConciliacion.getValorFacturasPendienteActualMasivo();
        BigDecimal valorPropuesto = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? cmConciliacion.getValorPagadoEps()
                : cmConciliacion.getValorAceptadoIps();

        BigDecimal promedio;
        BigDecimal procentajeCalculado = new BigDecimal("0");

        if (valorPropuesto != null && valorLimite != null) {
            if (valorPropuesto.compareTo(valorLimite) > 0) {
                String msgError = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                        ? "El valor pagado EPS no puede ser mayor que valor suma pendiente actual"
                        : "El valor aceptado IPS no puede ser mayor que valor suma pendiente actual";
                this.addError(msgError);

                if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
                    cmConciliacion.setValorPagadoEps(null);
                } else {
                    cmConciliacion.setValorAceptadoIps(null);
                }
            } else {
                promedio = valorPropuesto.divide(valorLimite, 5, RoundingMode.CEILING);
                procentajeCalculado = promedio.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
            }
        }

        if (procentajeCalculado.equals(new BigDecimal(BigInteger.ZERO))) {
            procentajeCalculado = null;
            generarMensajes();
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            cmConciliacion.setPorcentajePagadoEps(procentajeCalculado);
        } else {
            cmConciliacion.setPorcentajeAceptadoIps(procentajeCalculado);
        }
    }

    public void conversionValorConciliacionMasiva(int tipoCampo) {
        //MathContext mc  = new MathContext(4);
        CmConciliacion cmConciliacion = this.getCmConciliacion();

        BigDecimal valorFinal = new BigDecimal("0");

        BigDecimal porcentaje = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? cmConciliacion.getPorcentajePagadoEps()
                : cmConciliacion.getPorcentajeAceptadoIps();

        BigDecimal valorLimite = cmConciliacion.getValorFacturasPendienteActualMasivo();

        if (porcentaje != null && valorLimite != null && !porcentaje.equals(new BigDecimal(BigInteger.ZERO))) {
            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            valorFinal = promedio.multiply(valorLimite).setScale(4, RoundingMode.HALF_UP);
        }

        if (valorFinal.equals(new BigDecimal(BigInteger.ZERO))) {
            valorFinal = null;
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            cmConciliacion.setValorPagadoEps(valorFinal);
        } else {
            cmConciliacion.setValorAceptadoIps(valorFinal);
        }
    }

    public void conversionPorcentajeRow(CmDetalle detalle, int tipoCampo) {

        BigDecimal valorLimite = detalle.getValorPendienteActual();
        BigDecimal valorPropuesto = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? detalle.getValorPagadoEPS()
                : detalle.getValorAceptadoIPS();
        BigDecimal promedio;
        BigDecimal procentajeCalculado = new BigDecimal("0");

        if (valorPropuesto != null && valorLimite != null) {
            if (valorPropuesto.compareTo(valorLimite) > 0) {
                String msgError = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                        ? "El valor pagado EPS no puede ser mayor que valor pendiente actual"
                        : "El valor aceptado IPS no puede ser mayor que valor pendiente actual";
                this.addError(msgError);

                if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
                    detalle.setValorPagadoEPS(null);
                } else {
                    detalle.setValorAceptadoIPS(null);
                }
            } else {
                promedio = valorPropuesto.divide(valorLimite, 5, RoundingMode.CEILING);
                procentajeCalculado = promedio.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
            }
        }

        if (procentajeCalculado.equals(new BigDecimal(BigInteger.ZERO))) {
            procentajeCalculado = null;
            generarMensajes();
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            detalle.setPorcentajePagadoEPS(procentajeCalculado);
        } else {
            detalle.setPorcentajeAceptadoIPS(procentajeCalculado);
        }
    }

    public void conversionValorRow(CmDetalle detalle, int tipoCampo) {

        BigDecimal valorFinal = new BigDecimal("0");

        BigDecimal porcentaje = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? detalle.getPorcentajePagadoEPS()
                : detalle.getPorcentajeAceptadoIPS();

        BigDecimal valorLimite = detalle.getValorPendienteActual();

        if (porcentaje != null && valorLimite != null && !porcentaje.equals(new BigDecimal(BigInteger.ZERO))) {
            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            valorFinal = promedio.multiply(valorLimite).setScale(4, RoundingMode.HALF_UP);
        }

        if (valorFinal.equals(new BigDecimal(BigInteger.ZERO))) {
            valorFinal = null;
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            detalle.setValorPagadoEPS(valorFinal);
        } else {
            detalle.setValorAceptadoIPS(valorFinal);
        }
    }

    public void cerrarVentanaConciliarRespuestaIndividual() {
        actualizaBloqueoFactura(false);
        setSeleccionarTodosDetalles(false);
        PrimeFaces.current().executeScript("PF('tablaWidgetDetallesConciliacionIndividual').unselectAllRows();");
        if (getMensajeFaceGeneral() != null && !getMensajeFaceGeneral().isEmpty()) {
            this.addMensaje(getMensajeFaceGeneral());
            PrimeFaces.current().resetInputs("frmConciliaciones");
            PrimeFaces.current().ajax().update("frmConciliaciones");
            generarMensajes();
        } else {
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual");
        }
    }

    public void cerrarVentanaConciliacionIndividual() {
        getRegistrosDetalles().clear();
        getRegistrosDetallesFiltrados().clear();
        actualizaBloqueoFactura(false);
        PrimeFaces.current().resetInputs("frmVerConciliacionIndividual:tablaDetalles");
        PrimeFaces.current().ajax().update("frmVerConciliacionIndividual:tablaDetalles");
        if (super.getMensajes() != null && (super.getMensajes().size() > 0 || super.isError())) {
            super.setDoAccion(ACCION_GUARDAR);
            procesoFinal();
        }
    }

    public void procesarFacturasSeleccionadas(ToggleSelectEvent event) {
        if (event.isSelected()) {
            for (CmFactura factura : getRegistrosFacturaSelected()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) == null) {
                    getHashFacturasSelecionadas().put(factura.getId(), factura);
                }
            }

            setSeleccionTodasFacturas(true);

        } else {
            for (CmFactura factura : getRegistros()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                    getHashFacturasSelecionadas().remove(factura.getId());
                }
            }
            setSeleccionTodasFacturas(false);
        }
    }

    public void onRowSelect(SelectEvent event) {
        CmFactura facturaIn = (CmFactura) event.getObject();

        if (isSeleccionTodasFacturas()) {
            for (CmFactura factura : getRegistros()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                    getHashFacturasSelecionadas().remove(factura.getId());
                }
            }
            setSeleccionTodasFacturas(false);
        }
        if (getHashFacturasSelecionadas().get(facturaIn.getId()) == null) {
            getHashFacturasSelecionadas().put(facturaIn.getId(), facturaIn);
        }
    }

    public void onRowUnselect(SelectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
            getHashFacturasSelecionadas().remove(factura.getId());
        }
    }

    public void selectCheckbox(SelectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) == null) {
            getHashFacturasSelecionadas().put(factura.getId(), factura);
        }
    }

    public void unSelectCheckbox(UnselectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
            getHashFacturasSelecionadas().remove(factura.getId());
        }
    }

    public void refrescarPantallaResponderConciliacion() {
        limpiarFormularioResponderConciliacion();
        PrimeFaces.current().resetInputs("frmResponderConciliaciones");
        PrimeFaces.current().ajax().update("frmResponderConciliaciones");
    }

    public void refrescarLimpiarFormularioCalculoMasivo() {
        limpiarFormularioResponderGlosa();
        PrimeFaces.current().resetInputs("frmVerConciliacionIndividual:panelFormularioCalculoRespuestas");
        PrimeFaces.current().ajax().update("frmVerConciliacionIndividual:panelFormularioCalculoRespuestas");
    }

    public void limpiarFormularioResponderGlosa() {
        this.getObjeto().getCmGlosaRespuestaDetalle().setValorPagadoEps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setPorcentajePagadoEps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setValorAceptadoIps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setPorcentajeAceptadoIps(null);
        this.getObjeto().getCmGlosaRespuestaDetalle().setTipoEstadoRespuesta(0);
        this.getObjeto().getCmGlosaRespuestaDetalle().setObservacion("");
        this.getObjeto().getCmGlosaRespuestaDetalle().setObservacionMasiva("");
    }

    public void limpiarFormularioRespuestaMasiva() {
        CmConciliacion conciliacion = this.getCmConciliacion();
        conciliacion.setValorPagadoEps(null);
        conciliacion.setValorAceptadoIps(null);
        conciliacion.setPorcentajePagadoEps(null);
        conciliacion.setPorcentajeAceptadoIps(null);
        conciliacion.setObservacion(null);
    }

    public void inicializarTableDetallesSinPaginar() {
        refrescarDetallesSinPaginar();
    }

    public void aplicarCalculoGrupoDetalles() {
        BigDecimal valorSumaReponder = new BigDecimal("0");
        CmGlosaRespuestaDetalle respuestGlosa = this.getObjeto().getCmGlosaRespuestaDetalle();

        BigDecimal valorIPS = respuestGlosa.getValorAceptadoIps() == null ? new BigDecimal("0") : respuestGlosa.getValorAceptadoIps();
        BigDecimal valorEPS = respuestGlosa.getValorPagadoEps() == null ? new BigDecimal("0") : respuestGlosa.getValorPagadoEps();

        if (valorEPS.compareTo(BigDecimal.ZERO) == 0 && valorIPS.compareTo(BigDecimal.ZERO) == 0) {
            this.addError("Debe ingregar uno de los valores masivos para poder realizar el cálculo");
        }

        List<CmDetalle> registrosDetallesProcesar = getRegistrosDetallesFiltrados() != null
                ? getRegistrosDetallesFiltrados() : getRegistrosDetalles();
        if (registrosDetallesProcesar == null || registrosDetallesProcesar.isEmpty()) {
            this.addError("Debe existir una lista de servicios o detalles para hacer calculos masivos.");
        }

        BigDecimal valorBaseComparacion = respuestGlosa.getValorPendienteActualItemsSeleccionados();
        valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0") : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);

        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);

        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatoria de los valores ingresados no debe superar el valor suma pendiente actual.");
        }

        if (!this.isError()) {
            for (CmDetalle detalle : registrosDetallesProcesar) {
                if (detalle.getValorPendienteActual() != null
                        && detalle.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setObservacionGlosa(respuestGlosa.getObservacionMasiva());
                    if (respuestGlosa.getPorcentajePagadoEps() != null
                            && respuestGlosa.getPorcentajePagadoEps().compareTo(BigDecimal.ZERO) > 0) {
                        detalle.setPorcentajePagadoEPS(respuestGlosa.getPorcentajePagadoEps());
                        conversionValorRow(detalle, TIPO_CAMPO_VALOR_PAGADO_EPS);
                    }

                    if (respuestGlosa.getPorcentajeAceptadoIps() != null
                            && respuestGlosa.getPorcentajeAceptadoIps().compareTo(BigDecimal.ZERO) > 0) {
                        detalle.setPorcentajeAceptadoIPS(respuestGlosa.getPorcentajeAceptadoIps());
                        conversionValorRow(detalle, TIPO_CAMPO_VALOR_ACEPTADO_IPS);
                    }
                }
            }
            actualizarSumatoriaValores();
            limpiarFormularioResponderGlosa();
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual:tablaDetalles");
            PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').filter();");
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual:pDetallesSumatoria");

        } else {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual");
        }
    }

    public void actualizarSumatoriaValores() {

        CmGlosaRespuestaDetalle respuestaDetalle = this.getObjeto().getCmGlosaRespuestaDetalle();
        respuestaDetalle.setValorAceptadoIpsPrecalculado(BigDecimal.ZERO);
        respuestaDetalle.setValorPagadoEpsPrecalculado(BigDecimal.ZERO);
        respuestaDetalle.setValorFacturadoPrecalculado(BigDecimal.ZERO);
        respuestaDetalle.setValorPendienteActualPrecalculado(BigDecimal.ZERO);

        List<CmDetalle> registrosDetallesProcesar = getRegistrosDetallesFiltrados() != null
                && !getRegistrosDetallesFiltrados().isEmpty()
                ? getRegistrosDetallesFiltrados() : getRegistrosDetalles();

        if (registrosDetallesProcesar == null || registrosDetallesProcesar.isEmpty()) {
            this.addError("Debe existir una lista de servicios o detalles para hacer calculos masivos.");
        }

        if (!this.isError()) {
            for (CmDetalle detallesSeleccionado : registrosDetallesProcesar) {

                BigDecimal valorAceptadoIps = detallesSeleccionado.getValorAceptadoIPS() == null
                        ? new BigDecimal(BigInteger.ZERO)
                        : detallesSeleccionado.getValorAceptadoIPS();
                BigDecimal valorPagadoEps = detallesSeleccionado.getValorPagadoEPS() == null
                        ? new BigDecimal(BigInteger.ZERO)
                        : detallesSeleccionado.getValorPagadoEPS();
                BigDecimal valorFacturado = detallesSeleccionado.getValorFacturado() == null
                        ? new BigDecimal(BigInteger.ZERO)
                        : detallesSeleccionado.getValorFacturado();
                BigDecimal valorPendienteActual = detallesSeleccionado.getValorPendienteActual() == null
                        ? new BigDecimal(BigInteger.ZERO)
                        : detallesSeleccionado.getValorPendienteActual();

                respuestaDetalle.setValorAceptadoIpsPrecalculado(
                        respuestaDetalle.getValorAceptadoIpsPrecalculado().
                                add(valorAceptadoIps).
                                setScale(4, RoundingMode.HALF_UP)
                );

                respuestaDetalle.setValorPagadoEpsPrecalculado(
                        respuestaDetalle.getValorPagadoEpsPrecalculado().
                                add(valorPagadoEps).
                                setScale(4, RoundingMode.HALF_UP)
                );
                respuestaDetalle.setValorFacturadoPrecalculado(
                        respuestaDetalle.getValorFacturadoPrecalculado().
                                add(valorFacturado).
                                setScale(4, RoundingMode.HALF_UP)
                );
                respuestaDetalle.setValorPendienteActualPrecalculado(
                        respuestaDetalle.getValorPendienteActualPrecalculado().
                                add(valorPendienteActual).
                                setScale(4, RoundingMode.HALF_UP)
                );

            }

            PrimeFaces.current().resetInputs("frmVerConciliacionIndividual:pDetallesSumatoria");
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual:pDetallesSumatoria");
        } else {
            PrimeFaces.current().resetInputs("frmVerConciliacionIndividual");
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual");
            generarMensajes();
        }
    }

    public void actualizarSumaValorePorFiltro() {

        CmGlosaRespuestaDetalle respuestaDetalle = this.getObjeto().getCmGlosaRespuestaDetalle();
        respuestaDetalle.setValorAceptadoIpsSeleccionados(BigDecimal.ZERO);
        respuestaDetalle.setValorPagadoEpsSeleccionados(BigDecimal.ZERO);
        respuestaDetalle.setValorPagadoItemsSeleccionados(BigDecimal.ZERO);
        respuestaDetalle.setValorPendienteActualItemsSeleccionados(BigDecimal.ZERO);

        for (CmDetalle registrosDetalle : getRegistrosDetallesFiltrados()) {
            BigDecimal valorAceptadoIps = registrosDetalle.getValorAceptadoIPS() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : registrosDetalle.getValorAceptadoIPS();
            BigDecimal valorPagadoEps = registrosDetalle.getValorPagadoEPS() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : registrosDetalle.getValorPagadoEPS();

            BigDecimal valorPendienteActual = registrosDetalle.getValorPendienteActual() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : registrosDetalle.getValorPendienteActual();

            BigDecimal valorPagado = registrosDetalle.getValorPagado() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : registrosDetalle.getValorPagado();

            respuestaDetalle.setValorAceptadoIpsSeleccionados(
                    respuestaDetalle.getValorAceptadoIpsSeleccionados().
                            add(valorAceptadoIps).
                            setScale(4, RoundingMode.HALF_UP)
            );

            respuestaDetalle.setValorPagadoEpsSeleccionados(
                    respuestaDetalle.getValorPagadoEpsSeleccionados().
                            add(valorPagadoEps).
                            setScale(4, RoundingMode.HALF_UP)
            );

            respuestaDetalle.setValorPagadoItemsSeleccionados(
                    respuestaDetalle.getValorPagadoItemsSeleccionados().
                            add(valorPagado).
                            setScale(4, RoundingMode.HALF_UP)
            );

            respuestaDetalle.setValorPendienteActualItemsSeleccionados(
                    respuestaDetalle.getValorPendienteActualItemsSeleccionados().
                            add(valorPendienteActual).
                            setScale(4, RoundingMode.HALF_UP)
            );
        }
        refrescarLimpiarFormularioCalculoMasivo();
        PrimeFaces.current().ajax().update("frmVerConciliacionIndividual:panelFormularioCalculoRespuestas");

    }

    public void onRowEdit(RowEditEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (detalle.getValorPagadoEPS() != null
                && detalle.getValorPagadoEPS().compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRow(detalle, 1);
        }
        if (detalle.getValorAceptadoIPS() != null
                && detalle.getValorAceptadoIPS().compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRow(detalle, 2);
        }

        if (detalle.getValorPagadoEPS() == null
                || detalle.getValorPagadoEPS().compareTo(BigDecimal.ZERO) == 0
                && (detalle.getPorcentajePagadoEPS() != null
                && detalle.getPorcentajePagadoEPS().compareTo(new BigDecimal("0")) > 0)) {
            conversionValorRow(detalle, 1);
        }

        if (detalle.getValorAceptadoIPS() == null
                || detalle.getValorAceptadoIPS().compareTo(BigDecimal.ZERO) == 0
                && (detalle.getPorcentajeAceptadoIPS() != null
                && detalle.getPorcentajeAceptadoIPS().compareTo(new BigDecimal("0")) > 0)) {
            conversionValorRow(detalle, 2);
        }

        BigDecimal valorIPS = detalle.getValorAceptadoIPS() == null ? new BigDecimal("0") : detalle.getValorAceptadoIPS();
        BigDecimal valorEPS = detalle.getValorPagadoEPS() == null ? new BigDecimal("0") : detalle.getValorPagadoEPS();

        BigDecimal valorBaseComparacion = detalle.getValorPendienteActual() == null ? new BigDecimal("0")
                : detalle.getValorPendienteActual().setScale(2, RoundingMode.HALF_UP);

        BigDecimal valorSumaReponder = new BigDecimal("0");
        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);

        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatora de valor EPS e IPS no debe superar el valor pendiente actual.");
            generarMensajes();
            detalle.setValorAceptadoIPS(null);
            detalle.setValorPagadoEPS(null);
            detalle.setPorcentajeAceptadoIPS(null);
            detalle.setPorcentajePagadoEPS(null);
        }

        if (super.isError()) {
            PrimeFaces.current().ajax().update("frmVerConciliacionIndividual");
        }

        actualizarSumatoriaValores();
    }

    private boolean verificarFacturasBloquedas(List<CmFactura> facturas) {
        boolean esValidoProceso = true;
        this.setParamConsultaUtilitario(new ParamConsulta());
        if (facturas != null && facturas.size() > 0) {
            String idFacturas = "";
            StringBuilder sb = new StringBuilder();
            for (CmFactura cmFactura : facturas) {
                sb.append(cmFactura.getId()).append(",");
            }
            if (sb.length() > 0) {
                idFacturas = sb.deleteCharAt(sb.length() - 1).toString();
                setFacturasAfectadasLog(idFacturas);
                int idUsuarioSolicita = this.getConexion().getUsuario().getId();
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_BUSCAR_FACTURAS_BLOQUEDAS);
                this.getParamConsultaUtilitario().setParametroConsulta1(idFacturas);
                this.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioSolicita);
                getCmFacturaConciliacionServicio().Accion(this);

                if (getFacturasBloquedas() != null && getFacturasBloquedas().length() > 0) {
                    this.addError("Las siguentes facturas estan bloquedas por favor contacte administrador, "
                            + getFacturasBloquedas());
                    esValidoProceso = false;
                }
            }
        }
        return esValidoProceso;
    }

    public String asignarColorFila(CmFactura factura) {
        String color = "";
        boolean asignacionLibre = true;
        if (asignacionLibre
                && CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION == factura.getTipoRespuesta()) {
            color = "yellow";
            asignacionLibre = false;
        }

        if (asignacionLibre && factura.isBloqueda()) {
            color = "green";
        }

        return color;
    }

    public void onRowEditFactura(RowEditEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        int tipoCalculoConciliacion = 0;

        if (factura.getValorPagadoEPS() != null
                && factura.getValorPagadoEPS().compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRowFactura(factura, 1);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if (factura.getValorAceptadoIPS() != null
                && factura.getValorAceptadoIPS().compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRowFactura(factura, 2);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if ((factura.getValorPagadoEPS() == null
                || factura.getValorPagadoEPS().compareTo(BigDecimal.ZERO) == 0)
                && (factura.getPorcentajePagadoEPS() != null
                && factura.getPorcentajePagadoEPS().compareTo(new BigDecimal("0")) > 0)) {
            conversionValorRowFactura(factura, 1);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if ((factura.getValorAceptadoIPS() == null
                || factura.getValorAceptadoIPS().compareTo(BigDecimal.ZERO) == 0)
                && (factura.getPorcentajeAceptadoIPS() != null
                && factura.getPorcentajeAceptadoIPS().compareTo(new BigDecimal("0")) > 0)) {
            conversionValorRowFactura(factura, 2);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }
        factura.setTipoCalculoConciliacion(tipoCalculoConciliacion);

        BigDecimal valorIPS = factura.getValorAceptadoIPS() == null ? new BigDecimal("0") : factura.getValorAceptadoIPS();
        BigDecimal valorEPS = factura.getValorPagadoEPS() == null ? new BigDecimal("0") : factura.getValorPagadoEPS();
        BigDecimal valorBaseComparacion = factura.getValorPendienteActual() == null ? new BigDecimal("0") : factura.getValorPendienteActual().setScale(2, RoundingMode.HALF_UP);

        BigDecimal valorSumaReponder = new BigDecimal("0");
        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);

        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatora de valor EPS e IPS no debe superar el valor pendiente actual.");
            generarMensajes();
            factura.setValorAceptadoIPS(null);
            factura.setValorPagadoEPS(null);
            factura.setPorcentajeAceptadoIPS(null);
            factura.setPorcentajePagadoEPS(null);
            factura.setTipoCalculoConciliacion(0);
        }

        actualizarSumatoriaValoresConciliacionMasiva();
    }

    public void conversionPorcentajeRowFactura(CmFactura factura, int tipoCampo) {

        BigDecimal valorLimite = factura.getValorPendienteActual();
        BigDecimal valorPropuesto = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? factura.getValorPagadoEPS()
                : factura.getValorAceptadoIPS();
        BigDecimal promedio;
        BigDecimal procentajeCalculado = new BigDecimal("0");

        if (valorPropuesto != null && valorLimite != null) {
            if (valorPropuesto.compareTo(valorLimite) > 0) {
                String msgError = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                        ? "El valor pagado EPS no puede ser mayor que valor pendiente actual"
                        : "El valor aceptado IPS no puede ser mayor que valor pendiente actual";
                this.addError(msgError);

                if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
                    factura.setValorPagadoEPS(null);
                } else {
                    factura.setValorAceptadoIPS(null);
                }
            } else {
                promedio = valorPropuesto.divide(valorLimite, 5, RoundingMode.CEILING);
                procentajeCalculado = promedio.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
            }
        }

        if (procentajeCalculado.equals(new BigDecimal(BigInteger.ZERO))) {
            procentajeCalculado = null;
            generarMensajes();
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            factura.setPorcentajePagadoEPS(procentajeCalculado);
        } else {
            factura.setPorcentajeAceptadoIPS(procentajeCalculado);
        }
    }

    public void conversionValorRowFactura(CmFactura factura, int tipoCampo) {

        BigDecimal valorFinal = new BigDecimal("0");

        BigDecimal porcentaje = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? factura.getPorcentajePagadoEPS()
                : factura.getPorcentajeAceptadoIPS();

        BigDecimal valorLimite = factura.getValorPendienteActual();

        if (porcentaje != null && valorLimite != null && !porcentaje.equals(new BigDecimal(BigInteger.ZERO))) {
            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            valorFinal = promedio.multiply(valorLimite).setScale(4, RoundingMode.HALF_UP);
        }

        if (valorFinal.equals(new BigDecimal(BigInteger.ZERO))) {
            valorFinal = null;
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            factura.setValorPagadoEPS(valorFinal);
        } else {
            factura.setValorAceptadoIPS(valorFinal);
        }
    }

    public void actualizarSumatoriaValoresConciliacionMasiva() {
        CmConciliacion conciliacion = this.getCmConciliacion();
        conciliacion.setValorFacturasAceptadoIpsPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPagadoEpsPrecalculado(BigDecimal.ZERO);
        conciliacion.setValorFacturasPendienteActualMasivo(BigDecimal.ZERO);

        for (CmFactura factura : getRegistrosFacturaSelected()) {
            BigDecimal valorAceptadoIps = factura.getValorAceptadoIPS() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : factura.getValorAceptadoIPS();
            BigDecimal valorPagadoEps = factura.getValorPagadoEPS() == null
                    ? new BigDecimal(BigInteger.ZERO)
                    : factura.getValorPagadoEPS();

            conciliacion.setValorFacturasAceptadoIpsPrecalculado(
                    conciliacion.getValorFacturasAceptadoIpsPrecalculado().
                            add(valorAceptadoIps).
                            setScale(4, RoundingMode.HALF_UP)
            );

            conciliacion.setValorFacturasPagadoEpsPrecalculado(
                    conciliacion.getValorFacturasPagadoEpsPrecalculado().
                            add(valorPagadoEps).
                            setScale(4, RoundingMode.HALF_UP)
            );

            if (factura.getTipoCalculoConciliacion() != CmFactura.TIPO_CALCULO_CONCILIACION_IND) {
                conciliacion.setValorFacturasPendienteActualMasivo(
                        conciliacion.getValorFacturasPendienteActualMasivo().
                                add(factura.getValorPendienteActual()).
                                setScale(4, RoundingMode.HALF_UP)
                );
            }

        }

        PrimeFaces.current().resetInputs("frmResponderConciliaciones:panelgFacturaSumatoria");
        PrimeFaces.current().ajax().update("frmResponderConciliaciones:panelgFacturaSumatoria");
    }

    public void aplicarCalculoGrupoFacturas() {
        CmConciliacion conciliacion = this.getCmConciliacion();
        BigDecimal valorSumaReponder = new BigDecimal("0");
        BigDecimal valorIPS = conciliacion.getValorAceptadoIps() == null ? new BigDecimal("0") : conciliacion.getValorAceptadoIps();
        BigDecimal valorEPS = conciliacion.getValorPagadoEps() == null ? new BigDecimal("0") : conciliacion.getValorPagadoEps();

        if (valorEPS.compareTo(BigDecimal.ZERO) == 0 && valorIPS.compareTo(BigDecimal.ZERO) == 0) {
            this.addError("Debe ingregar uno de los valores masivos para poder realizar el cálculo");
        }

        if (getRegistrosFacturaSelected() == null || getRegistrosFacturaSelected().isEmpty()) {
            this.addError("Debe existir una lista de facturas para hacer calculos masivos.");
        }

        BigDecimal valorBaseComparacion = conciliacion.getValorFacturasPendienteActualMasivo();
        valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0")
                : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);

        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);

        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatoria de los valores ingresados no debe superar el valor suma pendiente masivo actual.");
        }

        if (!this.isError()) {
            for (CmFactura factura : getRegistrosFacturaSelected()) {

                BigDecimal valorFacturaIPS = factura.getValorAceptadoIPS() == null ? new BigDecimal("0") : factura.getValorAceptadoIPS();
                BigDecimal valorFacturaEPS = factura.getValorPagadoEPS() == null ? new BigDecimal("0") : factura.getValorPagadoEPS();
                int tipoConciliacion = 0;

                if ((factura.getValorPendienteActual() != null
                        && factura.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0)
                        && valorFacturaIPS.compareTo(BigDecimal.ZERO) == 0
                        && valorFacturaEPS.compareTo(BigDecimal.ZERO) == 0) {

                    factura.setObservacion(conciliacion.getObservacion());
                    if (conciliacion.getPorcentajePagadoEps() != null
                            && conciliacion.getPorcentajePagadoEps().compareTo(BigDecimal.ZERO) > 0) {
                        factura.setPorcentajePagadoEPS(conciliacion.getPorcentajePagadoEps());
                        conversionValorRowFactura(factura, TIPO_CAMPO_VALOR_PAGADO_EPS);
                        tipoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_MAS;
                    }

                    if (conciliacion.getPorcentajeAceptadoIps() != null
                            && conciliacion.getPorcentajeAceptadoIps().compareTo(BigDecimal.ZERO) > 0) {
                        factura.setPorcentajeAceptadoIPS(conciliacion.getPorcentajeAceptadoIps());
                        conversionValorRowFactura(factura, TIPO_CAMPO_VALOR_ACEPTADO_IPS);
                        tipoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_MAS;
                    }
                    factura.setTipoCalculoConciliacion(tipoConciliacion);
                }
            }
            actualizarSumatoriaValoresConciliacionMasiva();
            limpiarFormularioRespuestaMasiva();
            PrimeFaces.current().ajax().update("frmResponderConciliaciones:tablaRegistros");
            PrimeFaces.current().executeScript("PF('tablaWidgetDetallesResponder').filter();");
            PrimeFaces.current().ajax().update("frmResponderConciliaciones:panelFormularioCalculoRespuestas");
        } else {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmResponderConciliaciones");
        }

    }

    public boolean validarLongitudRepresentantes(String representanteEPS, String representanteIPS) {
        boolean esValidaLongitud = true;
        if ((representanteEPS != null && representanteIPS != null)) {
            if ((representanteEPS.length() <= 7) || (representanteIPS.length() <= 7)) {
                esValidaLongitud = false;
            }
        }
        return esValidaLongitud;
    }

    public ReporteRespuestaGlosa getReporteRespuestaGlosa() {
        return reporteRespuestaGlosa;
    }

    public void setReporteRespuestaGlosa(ReporteRespuestaGlosa reporteRespuestaGlosa) {
        this.reporteRespuestaGlosa = reporteRespuestaGlosa;
    }

    public List<ReporteRespuestaGlosa> getListaReporteRespuestaGlosa() {
        return listaReporteRespuestaGlosa;
    }

    public void setListaReporteRespuestaGlosa(List<ReporteRespuestaGlosa> listaReporteRespuestaGlosa) {
        this.listaReporteRespuestaGlosa = listaReporteRespuestaGlosa;
    }

    public ReporteConciliacion getReporteConciliacionMasiva() {
        return reporteConciliacionMasiva;
    }

    public void setReporteConciliacionMasiva(ReporteConciliacion reporteConciliacionMasiva) {
        this.reporteConciliacionMasiva = reporteConciliacionMasiva;
    }

    public List<ReporteConciliacion> getListaReporteConsiliacionMasiva() {
        return listaReporteConsiliacionMasiva;
    }

    public void setListaReporteConsiliacionMasiva(List<ReporteConciliacion> listaReporteConsiliacionMasiva) {
        this.listaReporteConsiliacionMasiva = listaReporteConsiliacionMasiva;
    }

    public StreamedContent generarReporteConciliacionInvidual(Integer id) {
        StreamedContent streamedContent2 = null;
        InputStream is = null;
        try {
            listaReporteRespuestaGlosa = new ArrayList();
            reporteRespuestaGlosa = new ReporteRespuestaGlosa();
            getReporteRespuestaGlosa().setId(id);
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_ADICIONAL_1);
            getCmFacturaConciliacionServicio().Accion(this);
            if (listaReporteRespuestaGlosa.size() > 0) {

                //Estrucutra de JasperReport
                is = getClass().getResourceAsStream("/reportes/respuesta_glosa.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReporteRespuestaGlosa);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("Reporte.pdf").build();

            } else {
                if (!this.isError()) {
                    this.addError("Error no hay datos para generar el reporte");
                }
                PrimeFaces.current().executeScript("PF('tablaWidgetFacturas').filter();");
                generarMensajes();
            }

        } catch (JRException ex) {
            streamedContent2 = null;
            this.addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(CmFacturaConciliacionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return streamedContent2;
    }

    public StreamedContent generarReporteConciliacion(int id) {
        StreamedContent streamedContent2 = null;
        InputStream is = null;
        try {
            listaReporteConsiliacionMasiva = new ArrayList();
            reporteConciliacionMasiva = new ReporteConciliacion();
            this.reporteConciliacionMasiva.setId(id);
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_ADICIONAL_2);
            getCmFacturaConciliacionServicio().Accion(this);
            if (listaReporteConsiliacionMasiva.size() > 0) {

                //Estrucutra de JasperReport
                is = getClass().getResourceAsStream("/reportes/conciliaciones.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReporteConsiliacionMasiva);

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("Reporte.pdf").build();
                crearLog("Generar Reporte", reporteConciliacionMasiva.toString());

            } else {
                this.addError("Error no hay datos para generar el reporte");
                PrimeFaces.current().executeScript("PF('tablaWidgetFacturas').filter();");
                generarMensajes();
            }
        } catch (JRException ex) {
            streamedContent2 = null;
            this.addError("Error Reporte: " + ex.toString() + ex.getMessage());
            generarMensajes();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(CmFacturaConciliacionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return streamedContent2;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }

        crearLog("Descargar", this.getObjeto().toString());

    }

    public void actualizarPanelObservacion() {
        PrimeFaces.current().ajax().update("frmObservacionDetalle");
    }

    public void registrarObservacion() {
        setDetalleEditadoActual(new CmDetalle());
        PrimeFaces.current().resetInputs("frmObservacionDetalle");
        PrimeFaces.current().ajax().update("frmObservacionDetalle");
        PrimeFaces.current().executeScript("PF('dlgObservacionDetalle').hide()");
    }

    public void clearViewScopedBeans() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("cmFacturaConciliacionBean");
    }
    
    public void descargarAdjunto(CmAuditoriaAdjunto adj) {
        String rutaCompleta = adj.getArchivoRuta() + adj.getArchivoNombre();
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
            String attachmentName = "attachment; filename=\"" + adj.getArchivoNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/doc");
            } else if (ext.equalsIgnoreCase(".docx")) {
                ec.setResponseContentType("application/docx");
            } else if (ext.equalsIgnoreCase(".xls")) {
                ec.setResponseContentType("application/xls");
            } else if (ext.equalsIgnoreCase(".xlsx")) {
                ec.setResponseContentType("application/xlsx");
            } else if (ext.equalsIgnoreCase(".pdf")) {
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
            try (OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo:" + e.getMessage());
            generarMensajes();
        }
    }

}
