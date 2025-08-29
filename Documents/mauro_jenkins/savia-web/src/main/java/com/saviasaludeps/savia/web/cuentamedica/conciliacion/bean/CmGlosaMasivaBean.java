/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmGlosaMasivaServicioIface;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.faces.context.ExternalContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.SortMeta;

/**
 *
 * @author jepn
 */
@Named("cmGlosaMasivaBean")
@ViewScoped
public class CmGlosaMasivaBean extends Url {

    @Autowired
    private CmGlosaMasivaServicioIface cmFacturaGlosaMasivaServicio;
    private CmFactura objeto;
    private List<CmFactura> registros;
    private List<CmDetalle> registrosDetalles;
    private List<CmDetalle> registrosDetallesSeleccionadoMasivos;
    private List<CmDetalle> registrosDetallesMultifactura;
    private List<CmGlosaRespuestaDetalle> registrosGlosaRespuestaDetalle;
    private List<CmGlosaRespuesta> registrosGlosaRespuesta;
    private List<CmFactura> registrosFacturasSeleccionadas;
    private List<CmAuditoriaAdjunto> registrosAdjuntosFactura;
    private List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita = new ArrayList<>();
    private LazyDataModel<CmFactura> lazyRegistro;
    private LazyDataModel<CmDetalle> lazyDetalles;
    private LazyDataModel<CmDetalle> lazyDetallesMultifactura;
    private LazyDataModel<CmGlosaRespuestaDetalle> lazyGlosaRespuestaDetalle;
    private LazyDataModel<CmGlosaRespuesta> lazyGlosaRespuesta;
    private CmGlosaRespuesta cmGlosaRespuesta;
    private List<CmAuditoriaMotivoGlosa> listaCmAuditoriaMotivoGlosa;

    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private boolean habilitarBusquedaMuliUsuario;

    private Date fechaMaximaCalendario;

    private String facturasUsadasPorOtrosUsurios;

    public static final char ACCION_GUARDAR_PORCENTAJES_DETALLES_GLOSADOS = 'R';
    public static final char ACCION_GUARDAR_ESTADO_AUDITORIA = 'S';
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
    public static final char ACCION_MARCAR_FACTURAS = 'h';
    public static final char ACCION_DESMARCAR_FACTURAS = 'i';
    public static final char ACCION_VER_FACTURAS_GESTIONADAS_POR_OTRO_USUARIO = 'j';
    public static final char ACCION_GUARDAR_BLOQUEO_FACTURA_USADA = 'k';
    public static final char ACCION_LISTAR_DETALLES_MULTI_FACTURA = 'l';
    public static final char ACCION_GUARDAR_RESPUESTA_GLOSA = 'm';
    public static final char ACCION_VER_MOTIVOS_ESPECIFICOS_USADOS = 'n';
    public static final char ACCION_DESCARGAR_REPORTE_GLOSA = 'O';
    public static final char ACCION_VER_AUDITORIA_SERVICIO = 'p';
    public static final char ACCION_VER_GESTIONAR_GLOSA_MASIVA = 'q';
    public static final char ACCION_LISTAR_ADJUNTOS_CMDETALLES = 'r';
    public static final char ACCION_LISTAR_ADJUNTOS_FACTURA  = 's';
    public static final char ACCION_LISTAR_DESCUENTO_CAPITA= 't';
    
    public final static int MAX_OBSERVACION_LOGS = 16300;
    public static int TIPO_CAMPO_VALOR_PAGADO_EPS = 1;
    public static int TIPO_CAMPO_VALOR_ACEPTADO_IPS = 2;
    public static final int DIAS_ADICIONALES_BLOQUEO = 15;
    public static final int LONGITUD_OBSERVACION_RATIFICACION = 5;
    public static final int TIPO_AUDITORIA_SIN_SELECCION = -1;
    public static final int TIPO_AUDITORIA_PARA_PAGO = 8;

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
    private ParamConsulta paramConsultaDetallesPorFactura;
    private ArrayList<CmDetalle> destallesConValoresAuditar;

    private List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa;
    private List<CmAuditoriaConceptoContable> registrosConceptoContable;
    private List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico;
    private List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion;
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle;
    
    private HashMap<Integer, CmFactura> hashFacturasSelecionadas;
    private HashMap<Integer, CmDetalle> hashDetallesSelecionados;

    CmDetalle detalleEditadoActual;
    private CmDetalle detalleServicioActual;
    private CmGlosaMasivaN cmGlosaMasiva;

    public CmGlosaMasivaServicioIface getCmFacturaGlosaMasivaServicio() {
        return cmFacturaGlosaMasivaServicio;
    }

    public void setCmFacturaGlosaMasivaServicio(CmGlosaMasivaServicioIface cmFacturaGlosaMasivaServicio) {
        this.cmFacturaGlosaMasivaServicio = cmFacturaGlosaMasivaServicio;
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

    public List<CmAuditoriaAdjunto> getRegistrosAdjuntosFactura() {
        if (registrosAdjuntosFactura == null) {
            registrosAdjuntosFactura = new ArrayList<>();
        }
        return registrosAdjuntosFactura;
    }

    public void setRegistrosAdjuntosFactura(List<CmAuditoriaAdjunto> registrosAdjuntosFactura) {
        this.registrosAdjuntosFactura = registrosAdjuntosFactura;
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

    public ParamConsulta getParamConsultaDetallesPorFactura() {
        if (paramConsultaDetallesPorFactura == null) {
            paramConsultaDetallesPorFactura = new ParamConsulta();
        }
        return paramConsultaDetallesPorFactura;
    }

    public void setParamConsultaDetallesPorFactura(ParamConsulta paramConsultaDetallesPorFactura) {
        this.paramConsultaDetallesPorFactura = paramConsultaDetallesPorFactura;
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
    
    public List<CmFactura> getRegistrosFacturasSeleccionadas() {
        if (registrosFacturasSeleccionadas == null) {
            registrosFacturasSeleccionadas = new ArrayList<>();
        }
        return registrosFacturasSeleccionadas;
    }

    public void setRegistrosFacturasSeleccionadas(List<CmFactura> registrosFacturasSeleccionadas) {
        this.registrosFacturasSeleccionadas = registrosFacturasSeleccionadas;
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

    public String getFacturasUsadasPorOtrosUsurios() {
        return facturasUsadasPorOtrosUsurios;
    }

    public void setFacturasUsadasPorOtrosUsurios(String facturasUsadasPorOtrosUsurios) {
        this.facturasUsadasPorOtrosUsurios = facturasUsadasPorOtrosUsurios;
    }

    public List<CmDetalle> getRegistrosDetallesMultifactura() {
        return registrosDetallesMultifactura;
    }

    public void setRegistrosDetallesMultifactura(List<CmDetalle> registrosDetallesMultifactura) {
        this.registrosDetallesMultifactura = registrosDetallesMultifactura;
    }

    public LazyDataModel<CmDetalle> getLazyDetallesMultifactura() {
        return lazyDetallesMultifactura;
    }

    public void setLazyDetallesMultifactura(LazyDataModel<CmDetalle> lazyDetallesMultifactura) {
        this.lazyDetallesMultifactura = lazyDetallesMultifactura;
    }

    public List<CmDetalle> getRegistrosDetallesSeleccionadoMasivos() {
        if (registrosDetallesSeleccionadoMasivos == null) {
            registrosDetallesSeleccionadoMasivos = new ArrayList<>();
        }
        return registrosDetallesSeleccionadoMasivos;
    }

    public void setRegistrosDetallesSeleccionadoMasivos(List<CmDetalle> registrosDetallesSeleccionadoMasivos) {
        this.registrosDetallesSeleccionadoMasivos = registrosDetallesSeleccionadoMasivos;
    }

    public CmGlosaMasivaN getCmGlosaMasiva() {
        if (cmGlosaMasiva == null) {
            cmGlosaMasiva = new CmGlosaMasivaN();
        }
        return cmGlosaMasiva;
    }

    public void setCmGlosaMasiva(CmGlosaMasivaN cmRespuestaGlosaMasiva) {
        this.cmGlosaMasiva = cmRespuestaGlosaMasiva;
    }

    public List<CmAuditoriaMotivoGlosa> getListaCmAuditoriaMotivoGlosa() {
        if (listaCmAuditoriaMotivoGlosa == null) {
            listaCmAuditoriaMotivoGlosa = new ArrayList<>();
        }
        return listaCmAuditoriaMotivoGlosa;
    }

    public void setListaCmAuditoriaMotivoGlosa(List<CmAuditoriaMotivoGlosa> listaCmAuditoriaMotivoGlosa) {
        this.listaCmAuditoriaMotivoGlosa = listaCmAuditoriaMotivoGlosa;
    }

    public List<CmAuditoriaCapitaDescuento> getRegistrosDescuentoCapita() {
        return registrosDescuentoCapita;
    }

    public void setRegistrosDescuentoCapita(List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita) {
        this.registrosDescuentoCapita = registrosDescuentoCapita;
    }

    public CmGlosaMasivaBean() {
        this.objeto = new CmFactura();
        Modulo mod = super.validarModulo(Modulo.ID_CM_RESPUESTA_GLOSA_MASIVA);
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

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void listarDiagnosticos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DIAGNOSTICOS);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void listarConceptosContables() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CONCEPTOS);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void listarMotivosGlosa() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_GLOSA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void listarAutorizaciones() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUTORIZACIONES);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }
    
    public void listarAdjuntosCmDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_CMDETALLES);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }
    
    public void listarAdjuntosFactura() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_FACTURA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }
    
    public void listarDescuentoCapita() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DESCUENTO_CAPITA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void refrescarDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_CON_VALOR_PENDIENTE);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarDetallesSinPaginar() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_SIN_PAGINAR);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarGlosaRespuesta() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_GLOSA_RESPUESTA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarGlosaRespuestaDetalle() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_RESPUESTA_DETALLES);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int _idFactura) {
        getObjeto().setId(_idFactura);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_FACTURA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        listarAdjuntosFactura();
        inicializarTableDetalles();
        inicializarTablaGlosaRespuesta();
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("scrollPanelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verGestionarGlosasMasivas() {
        sincronizarFiltrosSeleccionParaFacturasSeleccionadas(getHashFacturasSelecionadas(), getRegistrosFacturasSeleccionadas());
        boolean sonValidasFacturas = validarFormularioGlosasMasivas();
        if (sonValidasFacturas) {
            bloquearFacturasParaGlosar();
            limpiarFiltrosTablaFacturas();
            limpiarFiltrosTablaDetalles();
            limpiarTipoAuditoria();
            setCmGlosaMasiva(geCmRespuestaGlosaMasivaConValoresTotalizados());
            verDetallesMultiFactura(getIdsFacturasProcesoMasivo());
            verMotivosEspecificosDeFacturas();
            PrimeFaces.current().resetInputs("frmGestionarGlosas");
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
            PrimeFaces.current().executeScript("PF('dialogoGestionarGlosas').show()");
        } else {
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
        }
        setAccion(ACCION_VER);
        setDoAccion(ACCION_VER_GESTIONAR_GLOSA_MASIVA);
        procesoFinal();
    }

    public void verRespuestaDestallesGlosa(int _idGlosaRespuesta) {
        this.getCmGlosaRespuesta().setId(_idGlosaRespuesta);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_RESPUESTA_GLOSA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        inicializarTablaGlosaRespuestaDetalles(_idGlosaRespuesta);
        PrimeFaces.current().resetInputs("frmVerGlosaRespuesas");
        PrimeFaces.current().ajax().update("frmVerGlosaRespuesas");
        PrimeFaces.current().executeScript("PF('dialogoVerGlosaRespuestaDetalles').show()");
        procesoFinal();
    }

    public void verFormularioReponderGlosa() {
        this.getObjeto().getCmGlosaRespuestaDetalle().setTipoEstadoRespuesta(0);
        this.getObjeto().getCmGlosaRespuestaDetalle().setObservacion("");
        PrimeFaces.current().resetInputs("frmReponderGlosa");
        PrimeFaces.current().ajax().update("frmReponderGlosa");
        PrimeFaces.current().executeScript("PF('dialogoRegistrarGlosa').show()");
    }

    public void verServicioAuditado(int idServicio) {

        getDetalleServicioActual().setId(idServicio);
        verDetalle();
        verFactura();
        listarConceptosContables();
        listarDiagnosticos();
        listarMotivosGlosa();
        listarAutorizaciones();
        listarAdjuntosCmDetalles();     
        listarDescuentoCapita();
        PrimeFaces.current().resetInputs("frmVerAuditoriaServicio");
        PrimeFaces.current().ajax().update("frmVerAuditoriaServicio");
        PrimeFaces.current().executeScript("PF('dialogoVerAuditoriaServicio').show()");
        setAccion(ACCION_VER);
        setDoAccion(ACCION_VER_AUDITORIA_SERVICIO);
        procesoFinal();
    }

    public void verDetalle() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_DETALLE);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void verFactura() {
        int idFactura = getDetalleServicioActual().getCmFacturas().getId();
        CmFactura factura = getHashFacturasSelecionadas().get(idFactura);
        if (factura != null) {
            this.setObjeto(factura);
        }
    }

    public void verAsignacionValoresDetalles() {
        setRegistrosDetallesSeleccionadoMasivos((new ArrayList<>(getHashDetallesSelecionados().values())));
        actualizarSumatoriaValores(getRegistrosDetallesSeleccionadoMasivos());
        this.setRegistrosFacturasSeleccionadas(new ArrayList<>(getHashFacturasSelecionadas().values()));
        if (!isError()) {
            PrimeFaces.current().resetInputs("frmAplicaValoresGlosas");
            PrimeFaces.current().ajax().update("frmAplicaValoresGlosas");
            PrimeFaces.current().executeScript("PF('dialogoAplicarValoresServicio').show()");
        }
        procesoFinal();
    }

    public void verDetallesMultiFactura(String idFacturas) {
        this.setParamConsultaDetallesPorFactura(new ParamConsulta());
        getParamConsultaDetallesPorFactura().setParametroConsulta1(idFacturas);
        refrescarDetallesMultiFactura();
    }

    public void verMotivosEspecificosDeFacturas() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVOS_ESPECIFICOS_USADOS);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public boolean validarFormularioGlosasMasivas() {
        boolean esValido;
        esValido = validarCantidadFacturasMinimo();
        if (esValido) {
            esValido = validarFacturasMismoPrestador();
        }
        if (esValido) {
            esValido = validarFacturasNoGestionadasPorOtrosUsuarios();
        }
        if (esValido) {
            esValido = validarFacturasMismoOrigen();
        }

        return esValido;
    }

    private boolean validarFacturasMismoOrigen() {
        boolean esValido = true;
        int origenFacturaUnificado = 0;
        String numeroFacturadoInicial = "";
        for (Map.Entry<Integer, CmFactura> cmFacturaMap : getHashFacturasSelecionadas().entrySet()) {
            CmFactura cmFactura = cmFacturaMap.getValue();
            if (origenFacturaUnificado == 0) {
                origenFacturaUnificado = cmFactura.getOrigenFactura();
                numeroFacturadoInicial = cmFactura.getNumeroFacturado();
            }
            if (cmFactura.getOrigenFactura() != origenFacturaUnificado) {
                addError("Las facturas deben tener el mismo origen de factura para ser procesadas, "
                        + "origen encontrado:( " + CmFactura.getOrigenFacturaStr(origenFacturaUnificado)
                        + "  número factura:" + numeroFacturadoInicial + ") "
                        + ", origen diferente: (" + CmFactura.getOrigenFacturaStr(cmFactura.getOrigenFactura())
                        + "  número factura:" + cmFactura.getNumeroFacturado() + ")");
                esValido = false;
                break;
            }
        }
        return esValido;
    }

    private boolean validarCantidadFacturasMinimo() {
        boolean esCantidadValida = true;
        if (getHashFacturasSelecionadas().isEmpty()) {
            this.addError("Para realizar la glosa masiva se debe seleccionar facturas.");
            esCantidadValida = false;
        }

        if (esCantidadValida) {
            if (getHashFacturasSelecionadas().size() <= 1) {
                this.addError("Para realizar la glosa masiva debe seleccionar mas de una factura.");
                esCantidadValida = false;
            }
        }

        return esCantidadValida;
    }

    private void sincronizarFiltrosSeleccionParaFacturasSeleccionadas(HashMap<Integer, CmFactura> hashFacturasSelecionadas, List<CmFactura> registrosFacturasSeleccionadas) {
        try {
            if (hashFacturasSelecionadas.size() > registrosFacturasSeleccionadas.size()) {
                for (Map.Entry<Integer, CmFactura> entry : hashFacturasSelecionadas.entrySet()) {
                    Object idCmFactura = entry.getKey();
                    CmFactura cmFactura = entry.getValue();
                    int facturaEncontrada = 0;
                    for (CmFactura factura : getRegistrosFacturasSeleccionadas()) {
                        if (idCmFactura == factura.getId()) {
                            facturaEncontrada++;
                            break;
                        }
                    }

                    if (facturaEncontrada == 0) {
                        registrosFacturasSeleccionadas.add(cmFactura);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private boolean validarFacturasMismoPrestador() {
        boolean esMismoPrestador = true;
        String nitGlobal = null;
        for (Map.Entry<Integer, CmFactura> entry : getHashFacturasSelecionadas().entrySet()) {
            CmFactura factura = entry.getValue();
            String nitComparado = factura.getNit();
            if (nitGlobal == null) {
                nitGlobal = nitComparado;
            }
            if (!Objects.equals(nitGlobal, nitComparado)) {
                this.addError("Para realizar la glosa masiva las facturas deben tener el mismo número Nit,"
                        + " Nit encontrado : " + nitGlobal + ", Nit diferente : " + nitComparado);
                esMismoPrestador = false;
                break;
            }
        }
        return esMismoPrestador;
    }

    public boolean validarNoEsFacturaGlosable(CmFactura factura) {

        boolean facturaEditable = false;
        //la factura esta conciliada o en proceso y ademas esta marcada
        if (factura != null && factura.isEstadoFacturaDeBloqueo()) {
            facturaEditable = true;
        }
        return facturaEditable;
    }

    public boolean validarSeleccionDetalles() {
        boolean hayDetalles = true;
        if (getRegistrosDetallesSeleccionadoMasivos().isEmpty()) {
            addError("Para aplicar los valores debe seleccionar detalles");
            hayDetalles = false;

        }
        return hayDetalles;
    }

    public boolean validarTopePorcentajeAplicarDetalleMasivo(BigDecimal porcentajeEps, BigDecimal porcentajeIps) {
        boolean hayDetalles = true;
        BigDecimal topePorcentaje = new BigDecimal("100");
        BigDecimal sumatoriaPorcentajes = new BigDecimal("0");
        sumatoriaPorcentajes = sumatoriaPorcentajes.add(porcentajeEps).
                add(porcentajeIps).setScale(2, RoundingMode.HALF_UP);

        if (sumatoriaPorcentajes.compareTo(topePorcentaje) == 1) {
            this.addError("La sumatoria de los porcentajes no debe ser mayor a 100%.");
            hayDetalles = false;
        }
        return hayDetalles;
    }

    private boolean validarFacturasNoGestionadasPorOtrosUsuarios() {
        boolean sonfacturasLibres = true;
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_FACTURAS_GESTIONADAS_POR_OTRO_USUARIO);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        if (getFacturasUsadasPorOtrosUsurios() != null && getFacturasUsadasPorOtrosUsurios().length() > 0) {
            this.addError("Las siguentes facturas estan bloquedas por favor contacte administrador, " + getFacturasUsadasPorOtrosUsurios());
            sonfacturasLibres = false;
        }
        return sonfacturasLibres;
    }

    private boolean validarFormularioAplicarPorcentajeCmDetalles(List<CmDetalle> registrosDetallesProcesar, CmGlosaRespuestaDetalle respuestGlosa) {
        boolean esValido = validarCantidadMinimaCmDetallesParaGlosar(registrosDetallesProcesar);
        if (esValido) {
            esValido = validarDistribucionPorcentajeValido(respuestGlosa);
        }
        return esValido;
    }

    private boolean validarDistribucionPorcentajeValido(CmGlosaRespuestaDetalle respuestGlosa) {
        boolean esValido = true;
        BigDecimal valorIPS = respuestGlosa.getValorAceptadoIps() == null ? new BigDecimal("0") : respuestGlosa.getValorAceptadoIps();
        BigDecimal valorEPS = respuestGlosa.getValorPagadoEps() == null ? new BigDecimal("0") : respuestGlosa.getValorPagadoEps();
        BigDecimal valorSumaReponder = new BigDecimal("0");
        BigDecimal valorBaseComparacion = respuestGlosa.getValorPendienteActualPrecalculado();
        valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0")
                : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);
        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);
        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatoria de los valores ingresados no debe superar el valor suma pendiente actual.");
            esValido = false;
        }
        return esValido;
    }

    private boolean validarCantidadMinimaCmDetallesParaGlosar(List<CmDetalle> registrosDetallesProcesar) {
        boolean esValido = true;
        if (registrosDetallesProcesar == null || registrosDetallesProcesar.isEmpty()) {
            this.addError("Debe existir una lista de servicios o detalles para hacer calculos masivos.");
            esValido = false;
        }
        return esValido;
    }

    private void bloquearFacturasParaGlosar() {
        actualizaBloqueoFacturas(true);
    }

    private void desBloquearFacturasParaGlosar() {
        actualizaBloqueoFacturas(false);
    }

    public void aplicarValorPorcentajeParaFacturasParaGlosar() {

        CmGlosaMasivaN respuetaGlosaMasiva = this.getCmGlosaMasiva();
        BigDecimal valorSumaReponder = new BigDecimal("0");
        BigDecimal valorIPS = respuetaGlosaMasiva.getValorAceptadoIps() == null ? new BigDecimal("0") : respuetaGlosaMasiva.getValorAceptadoIps();
        BigDecimal valorEPS = respuetaGlosaMasiva.getValorPagadoEps() == null ? new BigDecimal("0") : respuetaGlosaMasiva.getValorPagadoEps();

        if (valorEPS.compareTo(BigDecimal.ZERO) == 0 && valorIPS.compareTo(BigDecimal.ZERO) == 0) {
            this.addError("Debe ingregar uno de los valores masivos para poder realizar el cálculo");
        }

        BigDecimal valorBaseComparacion = respuetaGlosaMasiva.getValorFacturasPendienteActualMasivo();
        valorBaseComparacion = valorBaseComparacion == null ? new BigDecimal("0")
                : valorBaseComparacion.setScale(2, RoundingMode.HALF_UP);

        valorSumaReponder = valorSumaReponder.add(valorIPS).
                add(valorEPS).setScale(2, RoundingMode.HALF_UP);

        if (valorSumaReponder.compareTo(valorBaseComparacion) > 0) {
            this.addError("La sumatoria de los valores ingresados no debe superar el valor suma pendiente masivo actual.");
        }

        if (!this.isError()) {

            for (Map.Entry<Integer, CmFactura> entry : getHashFacturasSelecionadas().entrySet()) {
                CmFactura factura = entry.getValue();

                BigDecimal valorFacturaIPS = factura.getValorAceptadoIPS() == null ? new BigDecimal("0") : factura.getValorAceptadoIPS();
                BigDecimal valorFacturaEPS = factura.getValorPagadoEPS() == null ? new BigDecimal("0") : factura.getValorPagadoEPS();
                int tipoConciliacion = 0;

                if ((factura.getValorPendienteActual() != null
                        && factura.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0)
                        && valorFacturaIPS.compareTo(BigDecimal.ZERO) == 0
                        && valorFacturaEPS.compareTo(BigDecimal.ZERO) == 0) {

                    factura.setObservacion(respuetaGlosaMasiva.getObservacion());
                    if (respuetaGlosaMasiva.getPorcentajePagadoEps() != null
                            && respuetaGlosaMasiva.getPorcentajePagadoEps().compareTo(BigDecimal.ZERO) > 0) {
                        factura.setPorcentajePagadoEPS(respuetaGlosaMasiva.getPorcentajePagadoEps());
                        asignarValorEpsIpsCmFactura(factura, TIPO_CAMPO_VALOR_PAGADO_EPS);
                        tipoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_MAS;
                    }

                    if (respuetaGlosaMasiva.getPorcentajeAceptadoIps() != null
                            && respuetaGlosaMasiva.getPorcentajeAceptadoIps().compareTo(BigDecimal.ZERO) > 0) {
                        factura.setPorcentajeAceptadoIPS(respuetaGlosaMasiva.getPorcentajeAceptadoIps());
                        asignarValorEpsIpsCmFactura(factura, TIPO_CAMPO_VALOR_ACEPTADO_IPS);
                        tipoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_MAS;
                    }
                    factura.setTipoCalculoConciliacion(tipoConciliacion);
                }
            }
            actualizarSumatoriaValoresRespuestaGlosaMasiva();
            limpiarFormularioAplicarPorcentajesMasivos();
            PrimeFaces.current().ajax().update("frmGestionarGlosas:tablaRegistrosFacturas");
            PrimeFaces.current().executeScript("PF('tablaWidgetFacturasSeleccionadas').filter();");
            PrimeFaces.current().ajax().update("frmGestionarGlosas:panelFormularioCalculoRespuestas");
        } else {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmGestionarGlosas");
        }

    }

    public void aplicarPorcentajeDetallesSeleccionados() {
        boolean hayCondificionesParaGlosar;
        CmGlosaMasivaN respuetaGlosaMasiva = this.getCmGlosaMasiva();
        BigDecimal porcentajeIPS = Optional.ofNullable(respuetaGlosaMasiva.getPorcentajeAceptadoIps()).orElse(new BigDecimal("0"));
        BigDecimal porcentajeEPS = Optional.ofNullable(respuetaGlosaMasiva.getPorcentajePagadoEps()).orElse(new BigDecimal("0"));

        hayCondificionesParaGlosar = validarSeleccionDetalles();
        if (hayCondificionesParaGlosar) {
            hayCondificionesParaGlosar = validarTopePorcentajeAplicarDetalleMasivo(porcentajeEPS, porcentajeIPS);
        }

        if (hayCondificionesParaGlosar) {
            for (CmDetalle detalle : getRegistrosDetallesSeleccionadoMasivos()) {
                if (detalle.getValorPendienteActual() != null
                        && detalle.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setObservacionGlosa(respuetaGlosaMasiva.getObservacion());
                    if (porcentajeEPS.compareTo(BigDecimal.ZERO) > 0) {
                        detalle.setPorcentajePagadoEPS(porcentajeEPS);
                        conversionValorRow(detalle, TIPO_CAMPO_VALOR_PAGADO_EPS);
                    }

                    if (porcentajeIPS.compareTo(BigDecimal.ZERO) > 0) {
                        detalle.setPorcentajeAceptadoIPS(porcentajeIPS);
                        conversionValorRow(detalle, TIPO_CAMPO_VALOR_ACEPTADO_IPS);
                    }
                }
            }

            limpiarFormularioAplicarPorcentajesMasivos();
            limpiarSeleccionDetalles();
            PrimeFaces.current().ajax().update("frmGestionarGlosas:tablaDetalles");
            PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').filter();");
        }
        procesoFinal();

    }

    private void actualizaBloqueoFacturas(boolean bloquear) {
        this.setParamConsultaUtilitario(new ParamConsulta());
        int idUsuarioSolicita = this.getConexion().getUsuario().getId();
        int idAsignacionUsuario = bloquear ? idUsuarioSolicita : 0;
        this.getParamConsultaUtilitario().setParametroConsulta1(idAsignacionUsuario);
        this.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioSolicita);
        this.getParamConsultaUtilitario().setParametroConsulta3(getIdsFacturasProcesoMasivo());
        super.setAccion(ACCION_GUARDAR);
        super.setDoAccion(ACCION_GUARDAR_BLOQUEO_FACTURA_USADA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void eventoCheckFactura(SelectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) == null) {
            getHashFacturasSelecionadas().put(factura.getId(), factura);
        }
    }

    public void eventoCheckDetalle(SelectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
            getHashDetallesSelecionados().put(detalle.getId(), detalle);
        }
        actualizarValoresReferenciaDetallesSeleccioados();
    }

    public void eventoUnCheckFactura(UnselectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
            getHashFacturasSelecionadas().remove(factura.getId());
        }
    }

    public void eventoUnCheckDetalle(UnselectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
            getHashDetallesSelecionados().remove(detalle.getId());
        }
        actualizarValoresReferenciaDetallesSeleccioados();
    }

    public void eventoCheckTodasFacturas(ToggleSelectEvent event) {
        if (event.isSelected()) {
            for (CmFactura factura : getRegistrosFacturasSeleccionadas()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) == null) {
                    getHashFacturasSelecionadas().put(factura.getId(), factura);
                }
            }

        } else {
            for (CmFactura factura : getRegistros()) {
                if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                    getHashFacturasSelecionadas().remove(factura.getId());
                }
            }
        }
    }

    public void eventoCheckTodasDetalles(ToggleSelectEvent event) {
        if (event.isSelected()) {
            for (CmDetalle detalle : getRegistrosDetallesSeleccionadoMasivos()) {
                if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
                    getHashDetallesSelecionados().put(detalle.getId(), detalle);
                }
            }
            actualizarValoresReferenciaDetallesSeleccioados();

        } else {
            for (CmDetalle detalle : getRegistrosDetallesMultifactura()) {
                if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
                    getHashDetallesSelecionados().remove(detalle.getId());
                }
            }
            actualizarValoresReferenciaDetallesSeleccioados();
        }
    }

    public void eventoEditarFactura(RowEditEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        int tipoCalculoConciliacion = 0;

        if (factura.getValorPagadoEPS() != null
                && factura.getValorPagadoEPS().compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRowFactura(factura, TIPO_CAMPO_VALOR_PAGADO_EPS);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if (factura.getValorAceptadoIPS() != null
                && factura.getValorAceptadoIPS().compareTo(new BigDecimal("0")) > 0) {
            conversionPorcentajeRowFactura(factura, TIPO_CAMPO_VALOR_ACEPTADO_IPS);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if ((factura.getValorPagadoEPS() == null
                || factura.getValorPagadoEPS().compareTo(BigDecimal.ZERO) == 0)
                && (factura.getPorcentajePagadoEPS() != null
                && factura.getPorcentajePagadoEPS().compareTo(new BigDecimal("0")) > 0)) {
            asignarValorEpsIpsCmFactura(factura, TIPO_CAMPO_VALOR_PAGADO_EPS);
            tipoCalculoConciliacion = CmFactura.TIPO_CALCULO_CONCILIACION_IND;
        }

        if ((factura.getValorAceptadoIPS() == null
                || factura.getValorAceptadoIPS().compareTo(BigDecimal.ZERO) == 0)
                && (factura.getPorcentajeAceptadoIPS() != null
                && factura.getPorcentajeAceptadoIPS().compareTo(new BigDecimal("0")) > 0)) {
            asignarValorEpsIpsCmFactura(factura, TIPO_CAMPO_VALOR_ACEPTADO_IPS);
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

        actualizarSumatoriaValoresRespuestaGlosaMasiva();
    }

    public void eventoTransformarValorEnPorcentaje(int tipoCampo) {
        CmGlosaMasivaN respuestaGlosaMasiva = this.getCmGlosaMasiva();

        BigDecimal valorLimite = respuestaGlosaMasiva.getValorPendienteActualDetallesSeleccionados();
        BigDecimal valorPropuesto = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? respuestaGlosaMasiva.getValorPagadoEps()
                : respuestaGlosaMasiva.getValorAceptadoIps();

        BigDecimal promedio;
        BigDecimal procentajeCalculado = new BigDecimal("0");

        if (valorLimite.compareTo(BigDecimal.ZERO) == 0) {
            addError("Para realizar el calculo dese seleccionar detalles.");
            valorLimite = null;
            generarMensajes();
        }

        if (valorPropuesto != null && valorLimite != null) {
            if (valorPropuesto.compareTo(valorLimite) > 0) {
                String msgError = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                        ? "El valor pagado EPS no puede ser mayor que valor suma pendiente actual"
                        : "El valor aceptado IPS no puede ser mayor que valor suma pendiente actual";
                this.addError(msgError);

                if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
                    respuestaGlosaMasiva.setValorPagadoEps(null);
                } else {
                    respuestaGlosaMasiva.setValorAceptadoIps(null);
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
            respuestaGlosaMasiva.setPorcentajePagadoEps(procentajeCalculado);
        } else {
            respuestaGlosaMasiva.setPorcentajeAceptadoIps(procentajeCalculado);
        }
    }

    public void eventoTransformarPorcentajeEnValor(int tipoCampo) {

        CmGlosaMasivaN respuestaGlosaMasiva = this.getCmGlosaMasiva();
        BigDecimal valorFinal = new BigDecimal("0");

        BigDecimal porcentaje = (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo)
                ? respuestaGlosaMasiva.getPorcentajePagadoEps()
                : respuestaGlosaMasiva.getPorcentajeAceptadoIps();

        BigDecimal valorLimite = respuestaGlosaMasiva.getValorPendienteActualDetallesSeleccionados();

        if (valorLimite.compareTo(BigDecimal.ZERO) == 0) {
            addError("Para realizar el calculo dese seleccionar detalles.");
            valorLimite = null;
            generarMensajes();
        }

        if (porcentaje != null && valorLimite != null && !porcentaje.equals(new BigDecimal(BigInteger.ZERO))) {
            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            valorFinal = promedio.multiply(valorLimite).setScale(4, RoundingMode.HALF_UP);
        }

        if (valorFinal.equals(new BigDecimal(BigInteger.ZERO))) {
            valorFinal = null;
        }

        if (TIPO_CAMPO_VALOR_PAGADO_EPS == tipoCampo) {
            respuestaGlosaMasiva.setValorPagadoEps(valorFinal);
        } else {
            respuestaGlosaMasiva.setValorAceptadoIps(valorFinal);
        }
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

    public void asignarValorEpsIpsCmFactura(CmFactura factura, int tipoCampo) {

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

    public void actualizarValoresReferenciaDetallesSeleccioados() {
        CmGlosaMasivaN respuestaGlosaMasiva = getCmGlosaMasiva();
        respuestaGlosaMasiva.setValorFacturasPendienteActualMasivo(BigDecimal.ZERO);
        for (Map.Entry<Integer, CmDetalle> e : getHashDetallesSelecionados().entrySet()) {
            CmDetalle cmDetalle = e.getValue();
            BigDecimal valorPendienteActual = Optional.ofNullable(cmDetalle.getValorPendienteActual()).orElse(new BigDecimal("0"));
            respuestaGlosaMasiva.setValorPendienteActualDetallesSeleccionados(respuestaGlosaMasiva.getValorPendienteActualDetallesSeleccionados().
                    add(valorPendienteActual).
                    setScale(4, RoundingMode.HALF_UP)
            );
        }
    }

    public void actualizarSumatoriaValoresRespuestaGlosaMasiva() {
        CmGlosaMasivaN respuestaGlosaMasiva = getCmGlosaMasiva();
        respuestaGlosaMasiva.setValorFacturasAceptadoIpsPrecalculado(BigDecimal.ZERO);
        respuestaGlosaMasiva.setValorFacturasPagadoEpsPrecalculado(BigDecimal.ZERO);
        respuestaGlosaMasiva.setValorFacturasPendienteActualMasivo(BigDecimal.ZERO);

        for (CmFactura factura : getRegistrosFacturasSeleccionadas()) {

            BigDecimal valorAceptadoIps = Optional.ofNullable(factura.getValorAceptadoIPS()).orElse(new BigDecimal("0"));
            BigDecimal valorPagadoEps = Optional.ofNullable(factura.getValorPagadoEPS()).orElse(new BigDecimal("0"));

            respuestaGlosaMasiva.setValorFacturasAceptadoIpsPrecalculado(respuestaGlosaMasiva.getValorFacturasAceptadoIpsPrecalculado().
                    add(valorAceptadoIps).
                    setScale(4, RoundingMode.HALF_UP)
            );

            respuestaGlosaMasiva.setValorFacturasPagadoEpsPrecalculado(respuestaGlosaMasiva.getValorFacturasPagadoEpsPrecalculado().
                    add(valorPagadoEps).
                    setScale(4, RoundingMode.HALF_UP)
            );

            if (factura.getTipoCalculoConciliacion() != CmFactura.TIPO_CALCULO_CONCILIACION_IND) {
                respuestaGlosaMasiva.setValorFacturasPendienteActualMasivo(respuestaGlosaMasiva.getValorFacturasPendienteActualMasivo().
                        add(factura.getValorPendienteActual()).
                        setScale(4, RoundingMode.HALF_UP)
                );
            }

        }

        PrimeFaces.current().resetInputs("frmGestionarGlosas:panelgFacturaSumatoria");
        PrimeFaces.current().ajax().update("frmGestionarGlosas:panelgFacturaSumatoria");
    }

    public void limpiarVariablesSeleccionMasiva() {
        getRegistrosFacturasSeleccionadas().clear();
        getHashFacturasSelecionadas().clear();
        getHashDetallesSelecionados().clear();
        PrimeFaces.current().executeScript("PF('tablaWidget').unselectAllRows();");
    }

    public void limpiarSeleccionDetalles() {
        setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>());
        PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').unselectAllRows();");
        procesoFinal();
    }

    public void limpiarTipoAuditoria() {
        this.getObjeto().setTipoAuditoria(TIPO_AUDITORIA_SIN_SELECCION);
    }

    private void limpiarFiltrosTablaDetalles() {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmGestionarGlosas:tablaDetalles");
        dataTable.reset();
        dataTable.resetRows();
        getRegistrosDetallesSeleccionadoMasivos().clear();
    }

    private void limpiarFiltrosTablaFacturas() {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmGestionarGlosas:tablaRegistrosFacturas");
        dataTable.reset();
        dataTable.resetRows();
        getRegistrosDetallesSeleccionadoMasivos().clear();
    }

    public void guardarResponderGlosa() {
        try {
            boolean hayAsignacionOperacionesCompleta;
            CmGlosaRespuestaDetalle respuestaGlosa = this.getObjeto().getCmGlosaRespuestaDetalle();
            getCmGlosaMasiva().setEstadoGlosaMasivaGlobal(respuestaGlosa.getTipoEstadoRespuesta());
            getCmGlosaMasiva().setObservacion(respuestaGlosa.getObservacion());

            hayAsignacionOperacionesCompleta = validarInsumosParaGlosaMasiva();

            if (hayAsignacionOperacionesCompleta) {
                if (asignarCantidadFacturasSegunEstado(getCmGlosaMasiva())) {
                    asignarCmDetallesDiligenciados();
                    super.setAccion(ACCION_GUARDAR);
                    super.setDoAccion(ACCION_GUARDAR_RESPUESTA_GLOSA);
                    getCmFacturaGlosaMasivaServicio().Accion(this);
                }
            }

            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoRegistrarGlosa').hide();");
                PrimeFaces.current().executeScript("PF('dialogoGestionarGlosas').hide();");
                addMensaje("La glosa masiva se esta procesando.");
            } else {
                PrimeFaces.current().resetInputs("frmGestionarGlosas");
                PrimeFaces.current().ajax().update("frmGestionarGlosas");
                PrimeFaces.current().resetInputs("frmReponderGlosa");
                PrimeFaces.current().ajax().update("frmReponderGlosa");
            }
        } catch (Exception e) {
        }
        procesoFinal();
    }

    private boolean validarInsumosParaGlosaMasiva() {
        boolean esValido;
        esValido = validarDetallesDiligenciados(this.getRegistrosDetallesMultifactura());
        if (esValido) {
            esValido = validarTipoAuditoriaParaPago();
        }
        return esValido;
    }

    private boolean validarTipoAuditoriaValida() {
        boolean esTipoValido = true;
        if (TIPO_AUDITORIA_SIN_SELECCION == getObjeto().getTipoAuditoria()) {
            addError("Seleccione un tipo de auditoría válida.");
            esTipoValido = false;
        }
        return esTipoValido;
    }

    private boolean validarTipoAuditoriaParaPago() {
        boolean esTipoValido = true;
        if (TIPO_AUDITORIA_PARA_PAGO != getObjeto().getTipoAuditoria()) {
            addError("Para realizar la respuesta debe seleccionar el tipo auditoría 'Para pago'.");
            esTipoValido = false;
        }
        return esTipoValido;
    }

    public boolean validarFacturasDiligenciadas() {
        boolean todosOperacioneValidas = true;
        for (Map.Entry<Integer, CmFactura> cmFacturaItem : getHashFacturasSelecionadas().entrySet()) {
            CmFactura cmFactura = cmFacturaItem.getValue();
            boolean hayGlosado = hayGlosadoFactura(cmFactura);
            boolean hayRataificacion = hayRatificacionFactura(cmFactura);
            boolean sinProceso = !hayGlosado && !hayRataificacion;
            if (sinProceso) {
                addError("La factura de número de facturado : " + cmFactura.getNumeroFacturado() + ", no tiene (ratificación o  glosado), por favor realice uno. ");
                todosOperacioneValidas = false;
                break;
            }
        }
        return todosOperacioneValidas;
    }

    private boolean validarDetallesDiligenciados(List<CmDetalle> detalles) {
        boolean sonDetallesDiligenciados = true;
        int numeroDetallesError = 0;
        for (CmDetalle detalle : detalles) {
            BigDecimal valorEPS = Optional.ofNullable(detalle.getPorcentajePagadoEPS()).orElse(BigDecimal.ZERO);
            BigDecimal valorIPS = Optional.ofNullable(detalle.getPorcentajeAceptadoIPS()).orElse(BigDecimal.ZERO);
            String observacion = Optional.ofNullable(detalle.getObservacionGlosa()).orElse("");
            if (!validarExistenciaGlosado(valorEPS, valorIPS) && !validarExistenciaRatificacion(observacion)) {
                addError("El detalle de número facturado (" + detalle.getCmFacturas().getNumeroFacturado() + ") y código de servicio (" + detalle.getMaServicioCodigo() + ") tiene que tener valores Eps/Ips u observación.");
                sonDetallesDiligenciados = false;
                numeroDetallesError++;
            }
            if (!sonDetallesDiligenciados && numeroDetallesError > 2) {
                break;
            }
        }

        if (detalles != null && detalles.isEmpty()) {
            addError("Para realizar el proceso debe responder o ratificar detalles.");
            sonDetallesDiligenciados = false;
        }
        return sonDetallesDiligenciados;
    }

    private boolean validarExistenciaRatificacion(String observacion) {
        return LONGITUD_OBSERVACION_RATIFICACION < observacion.length();
    }

    private boolean validarExistenciaGlosado(BigDecimal valorEPS, BigDecimal valorIPS) {
        return valorEPS.compareTo(BigDecimal.ZERO) > 0 || valorIPS.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean asignarCantidadFacturasSegunEstado(CmGlosaMasivaN cmGlosaMasiva) {
        boolean esAsignacionExitosa = true;
        cmGlosaMasiva.setValorAceptadoIps(BigDecimal.ZERO);
        cmGlosaMasiva.setValorPagadoEps(BigDecimal.ZERO);
        cmGlosaMasiva.setPorcentajePagadoEps(BigDecimal.ZERO);
        cmGlosaMasiva.setPorcentajeAceptadoIps(BigDecimal.ZERO);
        cmGlosaMasiva.setHoraFinalizacionRegistro(new Date());
        cmGlosaMasiva.setEstadoGlosaMasivaGlobal(CmGlosaMasivaN.ESTADO_INICIALIZADO);
        cmGlosaMasiva.setValorTotalAceptadoIps(BigDecimal.ZERO);
        cmGlosaMasiva.setValorTotalPagadoEps(BigDecimal.ZERO);

        try {
            for (Map.Entry<Integer, CmFactura> cmFacturaItem : getHashFacturasSelecionadas().entrySet()) {
                CmFactura cmFactura = cmFacturaItem.getValue();
                BigDecimal valorIps = Optional.ofNullable(cmGlosaMasiva.getValorAceptadoIps()).orElse(new BigDecimal("0"));
                BigDecimal valorEps = Optional.ofNullable(cmGlosaMasiva.getValorPagadoEps()).orElse(new BigDecimal("0"));
                cmGlosaMasiva.setValorAceptadoIps(cmGlosaMasiva.getValorAceptadoIps().
                        add(valorIps).
                        setScale(4, RoundingMode.HALF_UP));
                cmGlosaMasiva.setValorPagadoEps(cmGlosaMasiva.getValorPagadoEps().
                        add(valorEps).
                        setScale(4, RoundingMode.HALF_UP));
                cmGlosaMasiva.setCntPrestadores(new CntPrestador(cmFactura.getCntPrestador().getId()));

            }
            cmGlosaMasiva.setCantidadFacturas(getHashFacturasSelecionadas().size());
        } catch (Exception e) {
            addError("Error asignarCantidadFacturasSegunEstado :" + e.toString());
            esAsignacionExitosa = false;
        }
        return esAsignacionExitosa;
    }

    private boolean asignarCmDetallesDiligenciados() {
        boolean hayAsignacion = true;
        try {
            HashMap<String, List<CmDetalle>> hashCmDetalles = new HashMap();
            List<CmDetalle> detalles;
            for (CmDetalle cmDetalle : this.getRegistrosDetallesMultifactura()) {
                String llave = cmDetalle.getCmFacturas().getNumeroFacturado();
                if (hashCmDetalles.get(llave) == null) {
                    detalles = new ArrayList<>();
                    detalles.add(cmDetalle);
                    hashCmDetalles.put(llave, detalles);
                } else {
                    hashCmDetalles.get(llave).add(cmDetalle);
                }
            }

            for (Map.Entry<Integer, CmFactura> entry : this.getHashFacturasSelecionadas().entrySet()) {
                CmFactura cmFactura = entry.getValue();
                String llave = cmFactura.getNumeroFacturado();
                cmFactura.setListaCmDetalles(hashCmDetalles.get(llave));

            }
        } catch (Exception e) {
            hayAsignacion = false;
        }

        return hayAsignacion;
    }

    private boolean hayRatificacionFactura(CmFactura cmFactura) {
        boolean hayRatificacionFactura = false;
        String observacionCmFacturaFinal = Optional.ofNullable(cmFactura.getObservacion()).orElse("");
        if (LONGITUD_OBSERVACION_RATIFICACION < observacionCmFacturaFinal.trim().length()) {
            hayRatificacionFactura = true;
        }
        return hayRatificacionFactura;
    }

    private boolean hayGlosadoFactura(CmFactura cmFactura) {
        boolean hayGlosadoFactura = false;
        BigDecimal valorEPS = Optional.ofNullable(cmFactura.getValorPagadoEPS()).orElse(new BigDecimal("0"));
        BigDecimal valorIPS = Optional.ofNullable(cmFactura.getValorAceptadoIPS()).orElse(new BigDecimal("0"));
        if (valorIPS.compareTo(new BigDecimal("0")) > 0 || valorEPS.compareTo(new BigDecimal("0")) > 0) {
            hayGlosadoFactura = true;
        }
        return hayGlosadoFactura;
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

    public void limpiarFormularioAplicarPorcentajesMasivos() {
        this.getCmGlosaMasiva().setValorPagadoEps(null);
        this.getCmGlosaMasiva().setPorcentajePagadoEps(null);
        this.getCmGlosaMasiva().setValorAceptadoIps(null);
        this.getCmGlosaMasiva().setPorcentajeAceptadoIps(null);
        this.getCmGlosaMasiva().setObservacion(null);
        this.getCmGlosaMasiva().setValorPendienteActualDetallesSeleccionados(BigDecimal.ZERO);
        PrimeFaces.current().resetInputs("frmGestionarGlosas:panelFormularioCalculoRespuestas");
        PrimeFaces.current().ajax().update("frmGestionarGlosas:panelFormularioCalculoRespuestas");
    }

    public void refrescarLimpiarFormulario() {
        limpiarFormularioResponderGlosa();
        PrimeFaces.current().resetInputs("frmRespuestaGlosas");
        PrimeFaces.current().ajax().update("frmRespuestaGlosas");
    }

    public void refrescarLimpiarFormularioCalculoMasivo() {
        limpiarFormularioResponderGlosa();
        PrimeFaces.current().resetInputs("frmAplicaValoresGlosas:panelDetallesSumatoria");
        PrimeFaces.current().ajax().update("frmAplicaValoresGlosas:panelDetallesSumatoria");
    }

    public void refrescarDetallesMultiFactura() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_MULTI_FACTURA);
        getCmFacturaGlosaMasivaServicio().Accion(this);
    }

    public void conversionPorcentaje(int tipoCampo) {
        //MathContext mc  = new MathContext(4);
        CmGlosaRespuestaDetalle respuesta = this.getObjeto().getCmGlosaRespuestaDetalle();

        BigDecimal valorLimite = respuesta.getValorPendienteActualPrecalculado();
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

        BigDecimal valorLimite = respuesta.getValorPendienteActualPrecalculado();

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
                        case ACCION_VER_AUDITORIA_SERVICIO:
                            crearLog("Ver Auditoria Servicio", getObjeto().toString());
                            break;
                        case ACCION_VER_RESPUESTA_GLOSA:
                            crearLog("Ver Detalle Glosa", getObjeto().toString());
                            break;
                        case ACCION_VER_GESTIONAR_GLOSA_MASIVA:
                            crearLog("Ver Gestionar Glosa Masiva", getIdsFacturasProcesoMasivo());
                            break;
                    }
                    break;
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR_ESTADO_AUDITORIA:
                            break;
                        case ACCION_MARCAR_FACTURAS:
                            crearLog("Marcado Masivo", descLog);
                            PrimeFaces.current().ajax().update("frmGlosas");
                            break;
                        case ACCION_DESMARCAR_FACTURAS:
                            crearLog("DesMarcado Masivo", descLog);
                            PrimeFaces.current().ajax().update("frmGlosas");
                            break;
                        case ACCION_GUARDAR_RESPUESTA_GLOSA:
                            crearLog("Guardar Respuesta Masiva", getIdsFacturasProcesoMasivo());
                            break;
                    }
                    break;
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

    public void inicializarTablaDetallesMultiFactura(String idFacturas) {
        this.setParamConsultaDetallesPorFactura(new ParamConsulta());
        getParamConsultaDetallesPorFactura().setParametroConsulta1(idFacturas);
        lazyDetallesMultifactura = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaDetallesPorFactura().getCantidadRegistros();
            }

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaDetallesPorFactura().setPrimerRegistro(primerRegistro);
                getParamConsultaDetallesPorFactura().setRegistrosPagina(registrosPagina);
                getParamConsultaDetallesPorFactura().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaDetallesPorFactura().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDetallesMultiFactura();
                lista = getRegistrosDetallesMultifactura();
                setRowCount(getParamConsultaDetallesPorFactura().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle detalle) {
                return detalle.getId().toString();
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

    public void eventoCerrarVentanaGestionarGlosas() {

        desBloquearFacturasParaGlosar();
        limpiarVariablesSeleccionMasiva();
        PrimeFaces.current().resetInputs("frmGestionarGlosas:tablaDetalles");
        PrimeFaces.current().ajax().update("frmGestionarGlosas:tablaDetalles");
        PrimeFaces.current().ajax().update("frmGlosas");
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

        actualizarSumatoriaValores(getRegistrosDetallesMultifactura());
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

    public void actualizarSumatoriaValores(List<CmDetalle> registrosDetallesProcesar) {

        CmGlosaRespuestaDetalle respuestaDetalle = this.getObjeto().getCmGlosaRespuestaDetalle();
        respuestaDetalle.setValorAceptadoIpsPrecalculado(BigDecimal.ZERO);
        respuestaDetalle.setValorPagadoEpsPrecalculado(BigDecimal.ZERO);
        respuestaDetalle.setValorFacturadoPrecalculado(BigDecimal.ZERO);
        respuestaDetalle.setValorPendienteActualPrecalculado(BigDecimal.ZERO);

        if (registrosDetallesProcesar == null || registrosDetallesProcesar.isEmpty()) {
            this.addError("Debe existir una lista de servicios o detalles seleccionados para hacer calculos masivos.");
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

            PrimeFaces.current().resetInputs("frmAplicaValoresGlosas:pDetallesSumatoria");
            PrimeFaces.current().ajax().update("frmAplicaValoresGlosas:pDetallesSumatoria");
        }
    }

    public void guardarPorcentaje() {
        if (validarFormularioAplicarPorcentajeCmDetalles(getRegistrosDetallesSeleccionadoMasivos(), this.getObjeto().getCmGlosaRespuestaDetalle())) {
            asignarPorcentajeCmDetallesGlosados(getRegistrosDetallesSeleccionadoMasivos(), this.getObjeto().getCmGlosaRespuestaDetalle());
            guardarPorcentajesCmDetallesGlosados(getRegistrosDetallesSeleccionadoMasivos());
            if (!isError()) {
                limpiarFormularioResponderGlosa();
                actualizarVentanaGestionRespuestaGlosa();
            }
        }
        procesoFinal();
    }

    private boolean asignarPorcentajeCmDetallesGlosados(List<CmDetalle> registrosDetallesProcesar, CmGlosaRespuestaDetalle respuestGlosa) {
        boolean hayAsignacionPorcentajes = true;
        try {
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
        } catch (Exception e) {
            hayAsignacionPorcentajes = false;
        }
        return hayAsignacionPorcentajes;
    }

    private void actualizarVentanaGestionRespuestaGlosa() {
        PrimeFaces.current().ajax().update("frmGestionarGlosas:tablaDetalles");
        PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').filter();");
        PrimeFaces.current().ajax().update("frmAplicaValoresGlosas:pDetallesSumatoria");
    }

    public void actualizarSumaValorePorFiltro(List<CmDetalle> registrosDetallesProcesar) {

        CmGlosaRespuestaDetalle respuestaDetalle = this.getObjeto().getCmGlosaRespuestaDetalle();
        respuestaDetalle.setValorAceptadoIpsSeleccionados(BigDecimal.ZERO);
        respuestaDetalle.setValorPagadoEpsSeleccionados(BigDecimal.ZERO);
        respuestaDetalle.setValorPagadoItemsSeleccionados(BigDecimal.ZERO);
        respuestaDetalle.setValorPendienteActualItemsSeleccionados(BigDecimal.ZERO);

        for (CmDetalle registrosDetalle : registrosDetallesProcesar) {
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

    public StreamedContent descargarReporteRespuestaGlosaMasiva(Integer id) {
        StreamedContent streamedContent2 = null;
        InputStream is = null;

        try {
            setListaReporteRespuestaGlosa(new ArrayList());
            setReporteRespuestaGlosa(new ReporteRespuestaGlosa());
            getReporteRespuestaGlosa().setId(id);
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_DESCARGAR_REPORTE_GLOSA);
            getCmFacturaGlosaMasivaServicio().Accion(this);
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
                crearLog("Generar Reporte Glosa", getObjeto().toString());

            } else {
                if (!this.isError()) {
                    this.addError("Error no hay datos para generar el formulario");
                }
                PrimeFaces.current().resetInputs("frmVer:pVerFactura");
                PrimeFaces.current().ajax().update("frmVer:pVerFactura");
                PrimeFaces.current().executeScript("PF('tablaWidgetRespuestaGlosas').filter();");
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
                    Logger.getLogger(CmGlosaMasivaBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return streamedContent2;
    }

    public void guardarMarcadoFacturas() {
        try {
            if (validarCantidadFacturasMinimo()) {
                this.getObjeto().setId(0);
                super.setAccion(ACCION_GUARDAR);
                super.setDoAccion(ACCION_MARCAR_FACTURAS);
                getCmFacturaGlosaMasivaServicio().Accion(this);
                limpiarVariablesSeleccionMasiva();
            }
        } catch (Exception e) {
        }
        procesoFinal();
    }

    public void guardarDesMarcarFacturas() {
        try {
            if (validarCantidadFacturasMinimo()) {
                this.getObjeto().setId(0);
                super.setAccion(ACCION_GUARDAR);
                super.setDoAccion(ACCION_DESMARCAR_FACTURAS);
                getCmFacturaGlosaMasivaServicio().Accion(this);
                limpiarVariablesSeleccionMasiva();
            }
        } catch (Exception e) {
        }
        procesoFinal();
    }

    public void guardarTipoAuditoria(CmFactura factura) {
        try {

            if (validarTipoAuditoriaValida()) {
                super.setAccion(ACCION_GUARDAR);
                super.setDoAccion(ACCION_GUARDAR_ESTADO_AUDITORIA);
                getCmFacturaGlosaMasivaServicio().Accion(this);
                if (!this.isError()) {
                    procesoFinalModificarTipoAuditoria(factura);
                    guardarPorcentajesCmDetallesGlosados(getRegistrosDetallesMultifactura());
                    this.addMensaje("Se ha realizado el cambio de estado auditoría");
                }
            }
        } catch (Exception e) {
        }
        procesoFinal();
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

    private void guardarPorcentajesCmDetallesGlosados(List<CmDetalle> registrosDetallesProcesar) {

        this.setParamConsultaAuditoria(new ParamConsulta());
        ArrayList<CmDetalle> destallesAuditar = new ArrayList<>();
        for (CmDetalle registrosDetalle : registrosDetallesProcesar) {
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
            super.setAccion(ACCION_GUARDAR);
            super.setDoAccion(ACCION_GUARDAR_PORCENTAJES_DETALLES_GLOSADOS);
            getCmFacturaGlosaMasivaServicio().Accion(this);
        }
    }

    public void guardarReintentoSincronizacion(int idRespuestaGlosa) {
        this.getCmGlosaRespuesta().setId(idRespuestaGlosa);
        super.setAccion(ACCION_ADICIONAL_4);
        getCmFacturaGlosaMasivaServicio().Accion(this);
        if (!this.isError()) {
            this.addMensaje("El reintento de sincronización se esta ejecutando para respuesta glosa de id: " + idRespuestaGlosa);
        }
        procesoFinal();
    }

    public String getIdsFacturasProcesoMasivo() {
        String idsFacturas = "";
        try {
            List<Integer> facturas = new ArrayList<>(getHashFacturasSelecionadas().keySet());
            idsFacturas = Stream.of(facturas.toArray()).map(String::valueOf).collect(Collectors.joining(","));
        } catch (Exception e) {
        }
        return idsFacturas;
    }

    public CmGlosaMasivaN geCmRespuestaGlosaMasivaConValoresTotalizados() {

        CmGlosaMasivaN cmGlosaMasivaIn = new CmGlosaMasivaN();

        try {

            cmGlosaMasivaIn.setValorFacturasGlosaInicialPrecalculado(BigDecimal.ZERO);
            cmGlosaMasivaIn.setValorFacturasPendienteActualPrecalculado(BigDecimal.ZERO);
            cmGlosaMasivaIn.setValorFacturasPrecalculado(BigDecimal.ZERO);
            cmGlosaMasivaIn.setValorFacturasPendienteActualMasivo(BigDecimal.ZERO);
            cmGlosaMasivaIn.setValorFacturas(BigDecimal.ZERO);
            cmGlosaMasivaIn.setCantidadFacturas(getHashFacturasSelecionadas().size());
            cmGlosaMasivaIn.setValorPendienteActualDetallesSeleccionados(BigDecimal.ZERO);

            for (Map.Entry<Integer, CmFactura> entry : getHashFacturasSelecionadas().entrySet()) {
                CmFactura factura = entry.getValue();
                if (factura.getValorPendienteActual() != null
                        && factura.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0) {

                    cmGlosaMasivaIn.setValorFacturasGlosaInicialPrecalculado(cmGlosaMasivaIn.getValorFacturasGlosaInicialPrecalculado().
                            add(factura.getValorInicialGlosa()).
                            setScale(4, RoundingMode.HALF_UP)
                    );
                    cmGlosaMasivaIn.setValorFacturasPendienteActualPrecalculado(cmGlosaMasivaIn.getValorFacturasPendienteActualPrecalculado().
                            add(factura.getValorPendienteActual()).
                            setScale(4, RoundingMode.HALF_UP)
                    );

                    cmGlosaMasivaIn.setValorFacturasPendienteActualMasivo(cmGlosaMasivaIn.getValorFacturasPendienteActualMasivo().
                            add(factura.getValorPendienteActual()).
                            setScale(4, RoundingMode.HALF_UP)
                    );

                    cmGlosaMasivaIn.setValorFacturasPrecalculado(cmGlosaMasivaIn.getValorFacturasPrecalculado().
                            add(factura.getValorFactura()).
                            setScale(4, RoundingMode.HALF_UP)
                    );
                    cmGlosaMasivaIn.setValorFacturas(cmGlosaMasivaIn.getValorFacturasPrecalculado());
                }
            }
        } catch (Exception e) {
        }
        return cmGlosaMasivaIn;
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
