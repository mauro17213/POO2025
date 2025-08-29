/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmFacturaGlosaServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
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
import java.util.Calendar;
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
@Named("cmFacturaGlosaBean")
@ViewScoped
public class CmFacturaGlosaBean extends Url {

    @Autowired
    private CmFacturaGlosaServicioIface cmFacturaGlosaServicio;
    private CmFactura objeto;
    private List<CmFactura> registros;
    private List<CmDetalle> registrosDetalles;
    private List<CmDetalle> registrosDetallesSelected;
    private List<CmGlosaRespuestaDetalle> registrosGlosaRespuestaDetalle;
    private List<CmGlosaRespuesta> registrosGlosaRespuesta;
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle;
    private List<CmAuditoriaAdjunto> registrosAdjuntosFactura;
    private List<CmDetalle> registrosDetallesFiltrados;
    private List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa;
    private List<CmAuditoriaConceptoContable> registrosConceptoContable;
    private List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico;
    private List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion;
    private LazyDataModel<CmFactura> lazyRegistro;
    private LazyDataModel<CmDetalle> lazyDetalles;
    private LazyDataModel<CmGlosaRespuestaDetalle> lazyGlosaRespuestaDetalle;
    private LazyDataModel<CmGlosaRespuesta> lazyGlosaRespuesta;
    private CmGlosaRespuesta cmGlosaRespuesta;
    private List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita = new ArrayList<>();
    
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private boolean habilitarBusquedaMuliUsuario;

    private Date fechaMaximaCalendario;

    private String facturasBloquedas;

    public final static int MAX_OBSERVACION_LOGS = 16300;

    public static int TIPO_CAMPO_VALOR_PAGADO_EPS = 1;
    public static int TIPO_CAMPO_VALOR_ACEPTADO_IPS = 2;

    public static final char ACCION_GUARDAR_VALORES_AUDITORIA = 'R';
    public static final char ACCION_MODIFICAR_ESTADO_AUDITORIA = 'S';
    public static final char ACCION_BUSCAR_FACTURAS_BLOQUEDAS = 'T';
    public static final char ACCION_VER_FACTURA = 'U';
    public static final char ACCION_LISTAR_GLOSA_RESPUESTA = 'W';
    public static final char ACCION_LISTAR_RESPUESTA_DETALLES = 'X';
    public static final char ACCION_LISTAR_DETALLES = 'Y';
    public static final char ACCION_LISTAR_DETALLES_SIN_PAGINAR = 'Z';
    public static final char ACCION_VER_RESPUESTA_GLOSA = 'a';
    public static final char ACCION_VER_DETALLE = 'b';
    public static final char ACCION_LISTAR_DIAGNOSTICOS = 'c';
    public static final char ACCION_LISTAR_MOTIVOS_GLOSA = 'd';
    public static final char ACCION_LISTAR_CONCEPTOS = 'e';
    public static final char ACCION_LISTAR_AUTORIZACIONES = 'f';
    public static final char ACCION_LISTAR_DETALLES_CON_VALOR_PENDIENTE = 'g';
    public static final char ACCION_VER_SERVICIO_AUDITORIA = 'h';
    public static final char ACCION_LISTAR_ADJUNTOS_CMDETALLES = 'i';
    public static final char ACCION_LISTAR_ADJUNTOS_FACTURA  = 'j';
    public static final char ACCION_LISTAR_DESCUENTO_CAPITA = 'k';
    
    public static final int DIAS_ADICIONALES_BLOQUEO = 15;
    public static final int LONGITUD_OBSERVACION_RATIFICACION = 5;
    private boolean seleccionarTodosDetalles;

    private String mensaje;
    private String mensajeFaceGeneral;

    //Variables para el reporte glosas
    private ReporteRespuestaGlosa reporteRespuestaGlosa;
    private List<ReporteRespuestaGlosa> listaReporteRespuestaGlosa;

    private ParamConsulta paramConsultaServicios;
    private ParamConsulta paramConsultaRespuestaGlosa;
    private ParamConsulta paramConsultaDestallesGlosa;
    private ParamConsulta paramConsultaUtilitario;
    private ParamConsulta paramConsultaAuditoria;
    private ArrayList<CmDetalle> destallesConValoresAuditar;
    

    CmDetalle detalleEditadoActual;
    private CmDetalle detalleServicioActual;

    public CmFacturaGlosaBean() {
        this.objeto = new CmFactura();
        Modulo mod = super.validarModulo(Modulo.ID_FACTURA_GLOSAS);
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

    public CmFacturaGlosaServicioIface getCmFacturaGlosaServicio() {
        return cmFacturaGlosaServicio;
    }

    public void setCmFacturaGlosaServicio(CmFacturaGlosaServicioIface cmFacturaGlosaServicio) {
        this.cmFacturaGlosaServicio = cmFacturaGlosaServicio;
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

    public List<CmDetalle> getRegistrosDetalles() {
        if (registrosDetalles == null) {
            registrosDetalles = new ArrayList<>();
        }
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

    public List<CmDetalle> getRegistrosDetallesSelected() {
        return registrosDetallesSelected;
    }

    public void setRegistrosDetallesSelected(List<CmDetalle> registrosDetallesSelected) {
        this.registrosDetallesSelected = registrosDetallesSelected;
    }

    public LazyDataModel<CmGlosaRespuestaDetalle> getLazyGlosaRespuestaDetalle() {
        return lazyGlosaRespuestaDetalle;
    }

    public void setLazyGlosaRespuestaDetalle(LazyDataModel<CmGlosaRespuestaDetalle> lazyGlosaRespuestaDetalle) {
        this.lazyGlosaRespuestaDetalle = lazyGlosaRespuestaDetalle;
    }

    public LazyDataModel<CmGlosaRespuesta> getLazyGlosaRespuesta() {
        return lazyGlosaRespuesta;
    }

    public void setLazyGlosaRespuesta(LazyDataModel<CmGlosaRespuesta> lazyGlosaRespuesta) {
        this.lazyGlosaRespuesta = lazyGlosaRespuesta;
    }

    public List<CmGlosaRespuestaDetalle> getRegistrosGlosaRespuestaDetalle() {
        return registrosGlosaRespuestaDetalle;
    }

    public void setRegistrosGlosaRespuestaDetalle(List<CmGlosaRespuestaDetalle> registrosGlosaRespuestaDetalle) {
        this.registrosGlosaRespuestaDetalle = registrosGlosaRespuestaDetalle;
    }

    public List<CmGlosaRespuesta> getRegistrosGlosaRespuesta() {
        return registrosGlosaRespuesta;
    }

    public void setRegistrosGlosaRespuesta(List<CmGlosaRespuesta> registrosGlosaRespuesta) {
        this.registrosGlosaRespuesta = registrosGlosaRespuesta;
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

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public Date getFechaMaximaCalendario() {
        return fechaMaximaCalendario;
    }

    public void setFechaMaximaCalendario(Date fechaMaximaCalendario) {
        this.fechaMaximaCalendario = fechaMaximaCalendario;
    }

    public CmGlosaRespuesta getCmGlosaRespuesta() {
        if (cmGlosaRespuesta == null) {
            cmGlosaRespuesta = new CmGlosaRespuesta();
        }
        return cmGlosaRespuesta;
    }

    public void setCmGlosaRespuesta(CmGlosaRespuesta cmGlosaRespuesta) {
        this.cmGlosaRespuesta = cmGlosaRespuesta;
    }

    public boolean isSeleccionarTodosDetalles() {
        return seleccionarTodosDetalles;
    }

    public void setSeleccionarTodosDetalles(boolean seleccionarTodosDetalles) {
        this.seleccionarTodosDetalles = seleccionarTodosDetalles;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeFaceGeneral() {
        return mensajeFaceGeneral;
    }

    public void setMensajeFaceGeneral(String mensajeFaceGeneral) {
        this.mensajeFaceGeneral = mensajeFaceGeneral;
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
        if (paramConsultaRespuestaGlosa == null) {
            paramConsultaRespuestaGlosa = new ParamConsulta();
        }
        return paramConsultaDestallesGlosa;
    }

    public void setParamConsultaDestallesGlosa(ParamConsulta paramConsultaDestallesGlosa) {
        this.paramConsultaDestallesGlosa = paramConsultaDestallesGlosa;
    }

    public ParamConsulta getParamConsultaAuditoria() {
        if (paramConsultaAuditoria == null) {
            paramConsultaAuditoria = new ParamConsulta();
        }
        return paramConsultaAuditoria;
    }

    public void setParamConsultaAuditoria(ParamConsulta paramConsultaAuditoria) {
        this.paramConsultaAuditoria = paramConsultaAuditoria;
    }

    public ArrayList<CmDetalle> getDestallesConValoresAuditar() {
        if (destallesConValoresAuditar == null) {
            destallesConValoresAuditar = new ArrayList<>();
        }
        return destallesConValoresAuditar;
    }

    public void setDestallesConValoresAuditar(ArrayList<CmDetalle> destallesConValoresAuditar) {
        this.destallesConValoresAuditar = destallesConValoresAuditar;
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

    public List<CmAuditoriaCapitaDescuento> getRegistrosDescuentoCapita() {
        return registrosDescuentoCapita;
    }

    public void setRegistrosDescuentoCapita(List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita) {
        this.registrosDescuentoCapita = registrosDescuentoCapita;
    }
    

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void listarDiagnosticos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DIAGNOSTICOS);
        getCmFacturaGlosaServicio().Accion(this);
    }

    public void listarConceptosContables() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CONCEPTOS);
        getCmFacturaGlosaServicio().Accion(this);
    }

    public void listarMotivosGlosa() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_GLOSA);
        getCmFacturaGlosaServicio().Accion(this);
    }

    public void listarAutorizaciones() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUTORIZACIONES);
        getCmFacturaGlosaServicio().Accion(this);
    }
    
    public void listarAdjuntosCmDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_CMDETALLES);
        getCmFacturaGlosaServicio().Accion(this);
    }
    
     public void listarAdjuntosFactura() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_FACTURA);
        getCmFacturaGlosaServicio().Accion(this);
    }
     
    public void listarDescuentoCapita() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DESCUENTO_CAPITA);
        getCmFacturaGlosaServicio().Accion(this);
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmFacturaGlosaServicio().Accion(this);
    }

    public void refrescarDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_CON_VALOR_PENDIENTE);
        getCmFacturaGlosaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarDetallesSinPaginar() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_SIN_PAGINAR);
        getCmFacturaGlosaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarGlosaRespuesta() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_GLOSA_RESPUESTA);
        getCmFacturaGlosaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarGlosaRespuestaDetalle() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_RESPUESTA_DETALLES);
        getCmFacturaGlosaServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int _idFactura) {
        getObjeto().setId(_idFactura);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_FACTURA);
        getCmFacturaGlosaServicio().Accion(this);
        listarAdjuntosFactura();
        inicializarTableDetalles();
        inicializarTablaGlosaRespuesta();
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("scrollPanelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verGestionarGlosas(int _idFactura) {
        getObjeto().setId(_idFactura);
        getRegistrosDetalles().clear();
        boolean validarOperacion;
        List<CmFactura> list = new ArrayList<>();
        list.add(getObjeto());
        validarOperacion = verificarFacturasBloquedas(list);

        if (validarOperacion) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_VER);
            getCmFacturaGlosaServicio().Accion(this);
            inicializarTableDetallesSinPaginar();
            if (getRegistrosDetallesSelected() != null) {
                getRegistrosDetallesSelected().clear();
            }
            actualizarSumatoriaValores();
            actualizaBloqueoFactura(true);
        }

        if (!this.isError()) {
            super.setAccion(ACCION_EDITAR);
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
            PrimeFaces.current().ajax().update("scrollPanelGestionarGlosa");
            PrimeFaces.current().executeScript("PF('dialogoGestionarGlosas').show()");
            procesoFinal();
        } else {
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
            procesoFinal();
        }
    }

    public void verRespuestaDestallesGlosa(int _idGlosaRespuesta) {
        this.getCmGlosaRespuesta().setId(_idGlosaRespuesta);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_RESPUESTA_GLOSA);
        getCmFacturaGlosaServicio().Accion(this);
        inicializarTablaGlosaRespuestaDetalles(_idGlosaRespuesta);
        PrimeFaces.current().resetInputs("frmVerGlosaRespuesas");
        PrimeFaces.current().ajax().update("frmVerGlosaRespuesas");
        PrimeFaces.current().executeScript("PF('dialogoVerGlosaRespuestaDetalles').show()");
        procesoFinal();
    }

    public void verReponderGlosa() {
        this.getObjeto().getCmGlosaRespuestaDetalle().setTipoEstadoRespuesta(0);
        this.getObjeto().getCmGlosaRespuestaDetalle().setObservacion("");
        PrimeFaces.current().resetInputs("frmReponderGlosa");
        PrimeFaces.current().ajax().update("frmReponderGlosa");
        PrimeFaces.current().executeScript("PF('dialogoRegistrarGlosa').show()");
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

    public void verDetalle() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_DETALLE);
        getCmFacturaGlosaServicio().Accion(this);
    }
    
    
    public void verNota(CmFactura factura) {
        this.setObjeto(factura);
        PrimeFaces.current().ajax().update("frmVerNota");
        PrimeFaces.current().executeScript("PF('dialogoVerNota').show()");
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
        getCmFacturaGlosaServicio().Accion(this);
    }

    public void responderGlosa() {

        boolean validacionProceso = true;
        CmGlosaRespuestaDetalle respuestaGlosa = this.getObjeto().getCmGlosaRespuestaDetalle();
        BigDecimal valorIPS = respuestaGlosa.getValorAceptadoIpsPrecalculado() == null
                ? new BigDecimal("0") : respuestaGlosa.getValorAceptadoIpsPrecalculado();
        BigDecimal valorEPS = respuestaGlosa.getValorPagadoEpsPrecalculado() == null
                ? new BigDecimal("0") : respuestaGlosa.getValorPagadoEpsPrecalculado();

        BigDecimal valorPendienteActualFactura = this.getObjeto().getValorPendienteActual() == null
                ? new BigDecimal("0") : this.getObjeto().getValorPendienteActual();
        BigDecimal sumaPendienteActualDetalles = respuestaGlosa.getValorPendienteActualItemsSeleccionados() == null
                ? new BigDecimal("0") : respuestaGlosa.getValorPendienteActualItemsSeleccionados();
        BigDecimal SumaEpsIps = valorIPS.add(valorEPS);
        int contadorDetallesReconocer = 0;

        if (CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION == respuestaGlosa.getTipoEstadoRespuesta()) {

            if (valorIPS.compareTo(new BigDecimal("0")) > 0 || valorEPS.compareTo(new BigDecimal("0")) > 0) {
                this.addError("Para la ratificar la glosa los servicios o detalles no deben tener valores EPS "
                        + "ni valores aceptados IPS, solo deben tener observación.");
                validacionProceso = false;
            }
            if (validacionProceso) {
                int contadorObservaciones = 0;
                for (CmDetalle detalle : this.getRegistrosDetalles()) {
                    String nuevaObservacion = detalle.getObservacionGlosa() == null ? ""
                            : detalle.getObservacionGlosa();
                    String observacionHistorica = detalle.getObservacionRespuestaDetalles() == null ? ""
                            : detalle.getObservacionRespuestaDetalles();
                    String observacionGlosaFinal = nuevaObservacion + observacionHistorica;
                    if (observacionGlosaFinal != null
                            && LONGITUD_OBSERVACION_RATIFICACION < observacionGlosaFinal.trim().length()) {
                        contadorObservaciones++;
                    }
                }
                if (this.getRegistrosDetalles().size() != contadorObservaciones) {
                    this.addError("Para realizar el proceso los detalles deseados deben tener solo observación.");
                    validacionProceso = false;
                }
            }
        }

        if (CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA == respuestaGlosa.getTipoEstadoRespuesta()) {

            if (valorIPS.compareTo(new BigDecimal("0")) == 0
                    && valorEPS.compareTo(new BigDecimal("0")) == 0
                    && this.getRegistrosDetalles() != null) {

                for (CmDetalle detalle : this.getRegistrosDetalles()) {
                    String nuevaObservacion = detalle.getObservacionGlosa() == null ? ""
                            : detalle.getObservacionGlosa();
                    String observacionHistorica = detalle.getObservacionRespuestaDetalles() == null ? ""
                            : detalle.getObservacionRespuestaDetalles();
                    String observacionGlosaFinal = nuevaObservacion + observacionHistorica;
                    if (observacionGlosaFinal != null
                            && LONGITUD_OBSERVACION_RATIFICACION < observacionGlosaFinal.trim().length()) {
                        contadorDetallesReconocer++;
                    }
                }
            }
            if (respuestaGlosa.getValorPendienteActualPrecalculado().compareTo(new BigDecimal("0")) == 0) {
                this.addError("El valor suma pendiente actual debe ser mayor a cero.");
                validacionProceso = false;
            }
        }

        if (validacionProceso && sumaPendienteActualDetalles.compareTo(valorPendienteActualFactura) > 0) {
            this.addError("La sumatoria de los valores pendientes actuales de detalles superan el pendiente actual de factura, por favor verifique los datos.");
            validacionProceso = false;
        }

        if (validacionProceso && (contadorDetallesReconocer == 0 && SumaEpsIps.compareTo(new BigDecimal("0")) == 0)) {
            this.addError("Para realizar la respuesta de glosa, debe ratificar o glosar minímo un detalle.");
            validacionProceso = false;
        }

        if (validacionProceso) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_GUARDAR);
            getCmFacturaGlosaServicio().Accion(this);
        }

        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoRegistrarGlosa').hide();");
            PrimeFaces.current().executeScript("PF('dialogoGestionarGlosas').hide();");
        } else {
            PrimeFaces.current().resetInputs("frmGestionarGlosas");
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
            PrimeFaces.current().resetInputs("frmReponderGlosa");
            PrimeFaces.current().ajax().update("frmReponderGlosa");
            procesoFinal();
        }

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

    public void refrescarLimpiarFormulario() {
        limpiarFormularioResponderGlosa();
        PrimeFaces.current().resetInputs("frmRespuestaGlosas");
        PrimeFaces.current().ajax().update("frmRespuestaGlosas");
    }

    public void refrescarLimpiarFormularioCalculoMasivo() {
        limpiarFormularioResponderGlosa();
        PrimeFaces.current().resetInputs("frmGestionarGlosas:panelFormularioCalculoRespuestas");
        PrimeFaces.current().ajax().update("frmGestionarGlosas:panelFormularioCalculoRespuestas");
    }

    public void conversionPorcentaje(int tipoCampo) {
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

    public void conversionValor(int tipoCampo) {
        //MathContext mc  = new MathContext(4);
        CmGlosaRespuestaDetalle respuesta = this.getObjeto().getCmGlosaRespuestaDetalle();

        BigDecimal valorFinal = new BigDecimal("0");

        BigDecimal porcentaje = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? respuesta.getPorcentajePagadoEps()
                : respuesta.getPorcentajeAceptadoIps();

        BigDecimal valorLimite = respuesta.getValorPendienteActualItemsSeleccionados();

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

    private void procesoFinal() {

        String descLog = "{respuesta glosas}";
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
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    crearLog(descLog);
                    PrimeFaces.current().ajax().update("frmGlosas");
                    break;
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            crearLog("Ver facturas", "Ver factura");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar", StringUtils.abbreviate(" ---> Factura: " + this.getObjeto().toString() + "  ---> Config: " + getObjeto().getCmGlosaRespuestaDetalle().toString(), MAX_OBSERVACION_LOGS));
                            PrimeFaces.current().ajax().update("frmGlosas");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    crearLog("Bloqueo-Desbloqueo Factura", descLog);
                    PrimeFaces.current().ajax().update("frmGlosas");
                    break;
                case Url.ACCION_ADICIONAL_4:
                    crearLog("Reintento Sincronizacion Glosa", this.getCmGlosaRespuesta().toString());
                    PrimeFaces.current().ajax().update("frmGlosas");
                    break;
            }
        }
        generarMensajes();
    }

    private void procesoFinalModificarTipoAuditoria(CmFactura factura) {
        try {
            crearLog("Modificar Tipo Auditoria", StringUtils.abbreviate(" ---> Nuevo Tipo Auditoria: " + factura.getTipoAuditoriaStr() + ",  ---> Factura: " + factura.toString(), MAX_OBSERVACION_LOGS));
        } catch (Exception e) {
        }
    }

    public void inicializarTableDetallesSinPaginar() {
        refrescarDetallesSinPaginar();
    }

    public void inicializarTableDetalles() {
        this.setParamConsultaServicios(new ParamConsulta());
        this.getParamConsultaServicios().setEmpresaId(super.getConexion().getEmpresa().getId());
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

    public void inicializarTablaGlosaRespuesta() {
        this.setParamConsultaRespuestaGlosa(new ParamConsulta());
        this.getParamConsultaRespuestaGlosa().setEmpresaId(super.getConexion().getEmpresa().getId());
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
        this.getParamConsultaDestallesGlosa().setParametroConsulta1(_idGlosaRespuesta);
        this.getParamConsultaDestallesGlosa().setEmpresaId(super.getConexion().getEmpresa().getId());
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

    public void habilitarBusquedaUsuarioMultiple() {
        PrimeFaces.current().resetInputs("frmGlosas");
        PrimeFaces.current().ajax().update("frmGlosas");
    }

    public void mostrarMensaje(String mensaje) {
        setMensaje(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void actualizarPanelMostrarMensaje() {
        PrimeFaces.current().ajax().update("frmObservacion");
    }

    public void mostrarFaceMensajeGeneral() {
        if (getMensajeFaceGeneral() != null && !getMensajeFaceGeneral().isEmpty()) {
            this.addMensaje(getMensajeFaceGeneral());
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
            generarMensajes();
            setMensajeFaceGeneral("");
        }
    }

    public void cerrarVentanaGestionarGlosas() {
        getRegistrosDetalles().clear();
        getRegistrosDetallesFiltrados().clear();
        actualizaBloqueoFactura(false);
        PrimeFaces.current().resetInputs("frmGestionarGlosas:tablaDetalles");
        PrimeFaces.current().ajax().update("frmGestionarGlosas:tablaDetalles");
        if (super.getMensajes() != null && (super.getMensajes().size() > 0 || super.isError())) {
            super.setDoAccion(ACCION_GUARDAR);
            procesoFinal();
        }
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

        BigDecimal valorBaseComparacion = detalle.getValorPendienteActual();
        valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0")
                : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);

        BigDecimal valorSumaReponder = new BigDecimal("0");
        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);

        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatoria de valor EPS e IPS no debe superar el valor pendiente actual.");
            generarMensajes();
            detalle.setValorAceptadoIPS(null);
            detalle.setValorPagadoEPS(null);
            detalle.setPorcentajeAceptadoIPS(null);
            detalle.setPorcentajePagadoEPS(null);
        }

        if (super.isError()) {
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
        }

        actualizarSumatoriaValores();
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

            PrimeFaces.current().resetInputs("frmGestionarGlosas:pDetallesSumatoria");
            PrimeFaces.current().ajax().update("frmGestionarGlosas:pDetallesSumatoria");
        } else {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
        }
    }

    public void aplicarCalculoGrupoDetalles() {

        BigDecimal valorSumaReponder = new BigDecimal("0");
        CmGlosaRespuestaDetalle respuestGlosa = this.getObjeto().getCmGlosaRespuestaDetalle();

        BigDecimal valorIPS = respuestGlosa.getValorAceptadoIps() == null ? new BigDecimal("0") : respuestGlosa.getValorAceptadoIps();
        BigDecimal valorEPS = respuestGlosa.getValorPagadoEps() == null ? new BigDecimal("0") : respuestGlosa.getValorPagadoEps();

        /*
        if ( valorIPS.compareTo(BigDecimal.ZERO) == 0 &&  valorEPS.compareTo(BigDecimal.ZERO) == 0) {
            this.addError("Debe ingregar uno de los valores masivos para poder realizar el cálculo"); 
        }*/
        List<CmDetalle> registrosDetallesProcesar = getRegistrosDetallesFiltrados() != null
                ? getRegistrosDetallesFiltrados() : getRegistrosDetalles();
        if (registrosDetallesProcesar == null || registrosDetallesProcesar.isEmpty()) {
            this.addError("Debe existir una lista de servicios o detalles para hacer calculos masivos.");
        }

        BigDecimal valorBaseComparacion = respuestGlosa.getValorPendienteActualItemsSeleccionados();
        valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0")
                : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);

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
            PrimeFaces.current().ajax().update("frmGestionarGlosas:tablaDetalles");
            PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').filter();");
            PrimeFaces.current().ajax().update("frmGestionarGlosas:pDetallesSumatoria");

        } else {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
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
        PrimeFaces.current().ajax().update("frmGestionarGlosas:panelFormularioCalculoRespuestas");

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
                int idUsuarioSolicita = this.getConexion().getUsuario().getId();
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_BUSCAR_FACTURAS_BLOQUEDAS);
                this.getParamConsultaUtilitario().setParametroConsulta1(idFacturas);
                this.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioSolicita);
                getCmFacturaGlosaServicio().Accion(this);

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

    public StreamedContent generarReporteRespuestaGlosa(Integer id) {
        StreamedContent streamedContent2 = null;
        InputStream is = null;

        try {
            setListaReporteRespuestaGlosa(new ArrayList());
            setReporteRespuestaGlosa(new ReporteRespuestaGlosa());
            getReporteRespuestaGlosa().setId(id);
            super.setAccion(ACCION_ADICIONAL_3);
            getCmFacturaGlosaServicio().Accion(this);
            if (getListaReporteRespuestaGlosa().size() > 0) {

                //Estrucutra de JasperReport
                is = getClass().getResourceAsStream("/reportes/respuesta_glosa.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(getListaReporteRespuestaGlosa());

                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("Reporte.pdf").build();
                crearLog("Generar Reporte Glosa", getReporteRespuestaGlosa().toString());

            } else {
                if (!this.isError()) {
                    this.addError("Error no hay datos para generar el formulario");
                }
                PrimeFaces.current().executeScript("PF('tablaWidget').filter();");
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
                    Logger.getLogger(CmFacturaGlosaBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return streamedContent2;
    }

    public void bloquearFacturas(int idFactura) {
        this.setParamConsultaUtilitario(new ParamConsulta());
        this.getObjeto().setId(idFactura);
        super.setAccion(ACCION_ADICIONAL_2);
        this.getParamConsultaUtilitario().setParametroConsulta1(idFactura);
        this.getParamConsultaUtilitario().setParametroConsulta2(new Date());
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.DAY_OF_MONTH, DIAS_ADICIONALES_BLOQUEO);
        this.getParamConsultaUtilitario().setParametroConsulta3(calendario.getTime());
        this.getParamConsultaUtilitario().setParametroConsulta4(CmFactura.TIPO_BLOQUEO_GLOSA);
        getCmFacturaGlosaServicio().Accion(this);
        procesoFinal();
    }

    public void desBloquearFactura(int idFactura) {
        this.setParamConsultaUtilitario(new ParamConsulta());
        this.getObjeto().setId(idFactura);
        super.setAccion(ACCION_ADICIONAL_2);
        this.getParamConsultaUtilitario().setParametroConsulta1(idFactura);
        this.getParamConsultaUtilitario().setParametroConsulta2(null);
        this.getParamConsultaUtilitario().setParametroConsulta3(null);
        this.getParamConsultaUtilitario().setParametroConsulta4(0);
        getCmFacturaGlosaServicio().Accion(this);
        procesoFinal();
    }

    public void modificarAuditoria(CmFactura factura) {
        this.setParamConsultaUtilitario(new ParamConsulta());
        this.getParamConsultaUtilitario().setParametroConsulta1(factura.getId());
        this.getParamConsultaUtilitario().setParametroConsulta2(factura.getTipoAuditoria());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR_ESTADO_AUDITORIA);
        getCmFacturaGlosaServicio().Accion(this);
        if (!this.isError()) {
            procesoFinalModificarTipoAuditoria(factura);
            guardarValoresAuditoria();
            generarMensajes();
        }
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

    public void guardarValoresAuditoria() {

        this.setParamConsultaAuditoria(new ParamConsulta());
        ArrayList<CmDetalle> destallesAuditar = new ArrayList<>();
        for (CmDetalle registrosDetalle : this.getRegistrosDetalles()) {
            boolean actualizacionEPS = false;
            boolean actualizacionIPS = false;
            boolean actualizacionObservacion = false;
            if (registrosDetalle.getValorPagadoEPS() != null
                    && registrosDetalle.getPorcentajePagadoEPS() != null) {
                actualizacionEPS = true;
            }
            if (registrosDetalle.getValorAceptadoIPS() != null
                    && registrosDetalle.getPorcentajeAceptadoIPS() != null) {
                actualizacionIPS = true;
            }

            if (registrosDetalle.getObservacionGlosa() != null) {
                actualizacionObservacion = true;
            }
            if ((actualizacionEPS) || (actualizacionIPS) || actualizacionObservacion) {
                destallesAuditar.add(registrosDetalle);
            }
        }

        if (!destallesAuditar.isEmpty()) {
            this.setDestallesConValoresAuditar(destallesAuditar);
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_GUARDAR_VALORES_AUDITORIA);
            getCmFacturaGlosaServicio().Accion(this);
        }
    }

    public void guardarReintentoSincronizacion(int idRespuestaGlosa) {
        this.getCmGlosaRespuesta().setId(idRespuestaGlosa);
        super.setAccion(ACCION_ADICIONAL_4);
        getCmFacturaGlosaServicio().Accion(this);
        if (!this.isError()) {
            this.addMensaje("El reintento de sincronización se esta ejecutando para respuesta glosa de id: " + idRespuestaGlosa);
        }
        procesoFinal();
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
    
    public void clearViewScopedBeans() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("cmFacturaGlosaBean");
    }

}
