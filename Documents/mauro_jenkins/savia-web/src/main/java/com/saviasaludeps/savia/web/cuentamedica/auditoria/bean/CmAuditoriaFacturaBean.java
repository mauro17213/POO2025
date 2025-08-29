/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoHistorico;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionObjecion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaNovedad;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaSelectorOperacionMasiva;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaUsuarioActual;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmRespuestaGenerica;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmReintento;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccionDetalle;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio.CmAuditoriaFacturaServicioIface;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.HistorialAfiliadoHash;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.JsonMostrable;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jperezn
 */
@ManagedBean
@ViewScoped
public class CmAuditoriaFacturaBean extends Url {

    private CmAuditoriaFacturaServicioIface cmAuditoriaFacturaServicio;
    private CmFactura objeto;
    private CmReintento objetoReintento;
    private AsegRegistroNovedad objetoRegistroNovedad;
    private CntContrato contratoConSedes;

    public final static int GESTION_MASIVA_PARCIAL = 0;
    public final static int GESTION_MASIVA_TOTAL = 1;
    public static final String TEXTO_ITEM_NO_DEFINIDO = "No definido";
    public final static int MAX_OBSERVACION_LOGS = 16300;
    
    private LazyDataModel<CmFactura> lazyCmAuditoriaFactuar;
    private LazyDataModel<CmDetalle> lazyCmAuditoriaDetalles;
    private LazyDataModel<CmDetalle> lazyGlosaFacturaDetalle;
    private LazyDataModel<AuAnexo4> lazyAnexo4;
    private LazyDataModel<AuAnexo4Item> lazyAnexo4Items;
    private LazyDataModel<CntContrato> lazyCntContratos;
    private LazyDataModel<CmDetalle> lazyDetalleAgestionar;
    private LazyDataModel<AsegRegistroNovedad> lazyAseguramientoNovedadHistorial;
    private LazyDataModel<PeAfiliadosPrograma> lazyAfiliadosPrograma;
    private LazyDataModel<CmFeSoporte> lazySoportes;

    private List<CmFactura> registros;
    private List<CmDetalle> registrosAuditoriaDetalles;
    private List<CmDetalle> registrosGlosaFacturaDetalle;
    private List<AsegAfiliadoHistorico> registrosAuditoriaNovedad;
    private List<CmAuditoriaConceptoContable> registrosConceptoContable;
    private List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico;
    private List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion;
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjuto;
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle;
    private List<Maestro> registrosMaestroDiagnostico;
    private List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa;
    private List<AuAnexo4> registrosAnexo4;
    private List<AuAnexo4Item> registrosAnexo4Item;
    private List<CmDetalle> registrosDetallesSeleccionadoMasivos;
    private List<CmAuditoriaCapitaDescuento> registrosAuditoriaCapitaDescuento;
    private List<CntContrato> registrosCntContratos;
    private List<CmDetalle> registrosDetallesGlosar;
    private List<CmDetalle> registrosDetallesAgestionar;
    private List<CmSincronizacion> listaSincronizaciones;
    private List<AsegRegistroNovedad> registrosAseguramientoNovedad;
    private List<PeAfiliadosPrograma> registrosAfiliadosPrograma;
    private List<AucHospitalizacionObjecion> registroHospitalizacionObjeciones; 
    private ArrayList<Integer> registrosAdjuntosEliminar;
    private ArrayList<CmFeSoporte> registrosSoportes = new ArrayList<>();

    private CmFactura glosaFactura;
    private CmDetalle objetoItemServicio;
    private List<CmAuditoriaMotivoGlosa> listaMotivos;
    private List<CmAuditoriaMotivoGlosa> listaMotivosEspecificosEncontrados;
    private List<CmAuditoriaAutorizacion> listaAutorizacion;
    private List<CmAuditoriaConceptoContable> listaConceptosContables;
    private List<CmAuditoriaDiagnostico> listaDiagnosticos;
    private List<WsCmTransaccionDetalle> listaWsCmTransaccionDetalle;
    private CmAuditoriaDevolucion cmAuditoriaDevolucion;
    private CmAuditoriaMotivoGlosa cmAuditoriaMotivoGlosa;
    private CmAuditoriaAutorizacion cmAuditoriaAutorizacion;
    private CmAuditoriaConceptoContable cmAuditoriaConceptoContable;
    private CmAuditoriaDiagnostico cmAuditoriaDiagnostico;
    private CmAuditoriaNovedad cmAuditoriaNovedad;
    private CmAuditoriaCapitaDescuento cmAuditoriaCapitaDescuento;
    private CmAuditoriaUsuarioActual cmAuditoriaUsuarioActual;
   
    private ParamConsulta paramConsultaUtilitario;
    private ParamConsulta paramConsultaServiciosAuditoria;
    private ParamConsulta paramConsultaGlosaFacturaDetalles;
    private ParamConsulta paramConsultaRespuestaFactura;
    private ParamConsulta paramConsultaMaestroDiagnostico;
    private ParamConsulta paramConsultaAuditoriaNovedad;
    private ParamConsulta paramConsultaAuditoriaDiagnosticos;
    private ParamConsulta paramConsultaAuditoriaConceptoContable;
    private ParamConsulta paramConsultaAuditoriaMotivoGlosa;
    private ParamConsulta paramConsultaAuditoriaAutorizacion;
    private ParamConsulta paramConsultaAuditoriaAdjunto;
    private ParamConsulta paramConsultaAnexo4;
    private ParamConsulta paramConsultaAnexo4Item;
    private ParamConsulta paramConsultaAuditoriaDescuentoCapita;
    private ParamConsulta paramConsultaCntContratos;
    private ParamConsulta paramConsultaDetallesAgestionar;
    private ParamConsulta paramConsultaAfiliadoProgramas;
    private ParamConsulta paramConsultaConsultarSuceso;
    private ParamConsulta paramConsultaSoportes  = new ParamConsulta();
 
    public static final char ACCION_VER_AUDITORIA_FACTURA = 'A';
    public static final char ACCION_LISTAR_AUDITORIA_DETALLES = 'B';
    public static final char ACCION_CONSULTAR_PROGRAMAS_ACTIVOS_POR_AFILIADO = 'C';
    public static final char ACCION_LISTAR_GLOSA_FACTURA_DETALLES = 'D';
    public static final char ACCION_LISTAR_AUDITORIA_NOVEDADES = 'E';
    public static final char ACCION_VER_AUDITORIA_DETALLE = 'F';
    public static final char ACCION_VER_VISTA_GETION_ITEM = 'G';
    public static final char ACCION_LISTAR_DIAGNOSTICOS = 'H';
    public static final char ACCION_LISTAR_MOTIVOS_GLOSA = 'I';
    public static final char ACCION_LISTAR_CONCEPTOS = 'J';
    public static final char ACCION_LISTAR_ADJUNTOS = 'K';
    public static final char ACCION_LISTAR_AUTORIZACIONES = 'L';
    public static final char ACCION_GUARDAR_GESTIONAR_ITEM = 'M';
    public static final char ACCION_LISTAR_ANEXOS_4 = 'N';
    public static final char ACCION_LISTAR_AUDITORIA_DESCUENTO_CAPITA = 'O';
    public static final char ACCION_GUARDAR_ADJUNTOS = 'P';
    public static final char ACCION_GUARDAR_GESTIONAR_MASIVO = 'Q';
    public static final char ACCION_GUARDAR_ESTADO_FACTURA = 'R';
    public static final char ACCION_VERIFICAR_EXISTENCIA_ANEXO4 = 'S';
    public static final char ACCION_LISTAR_CNT_CONTRATOS = 'T';
    public static final char ACCION_GUARDAR_CAPITA_DESCUENTO = 'U';
    public static final char ACCION_GUARDAR_ESTADO_AUDITORIA = 'V';
    public static final char ACCION_GUARDAR_DEVOLUCION_FACTURA = 'W';
    public static final char ACCION_LISTAR_DETALLES_POR_GESTIONAR = 'X';
    public static final char ACCION_VALIDAR_FACTURA_MONO_USUARIO = 'Y';
    public static final char ACCION_LISTAR_TODOS_DETALLES_POR_FACTUA = 'Z';
    public static final char ACCION_GUARDAR_CAPITA_DESCUENTO_MASIVO = 'a';
    public static final char ACCION_LISTAR_ANEXOS4_ITEMS = 'b';
    public static final char ACCION_VERIFICAR_EXISTENCIA_ANEXO4_ITEM = 'c';
    public static final char ACCION_BORRAR_CONCEPTOS_POR_FACTURA = 'd';
    public static final char ACCION_BORRAR_MOTIVOS_POR_FACTURA = 'e';
    public static final char ACCION_BORRAR_DIAGNOSTICOS_POR_FACTURA = 'f';
    public static final char ACCION_OBTENER_VALOR_TOTAL_DETALLES_POR_FACTURA = 'g';
    public static final char ACCION_OBTENER_VALOR_TEMPORALES_M2_FACTURA = 'h';
    public static final char ACCION_VER_GESTION_FACTURA = 'i';
    public static final char ACCION_VER_REINTENTOS_FACTURA = 'j';
    public static final char ACCION_GUARDAR_REINTENTO_FACTURA = 'k';
    public static final char ACCION_VER_DESCUENTO_CAPITA = 'l';
    public static final char ACCION_VER_DEVOLUCION_FACTURA = 'm';
    public static final char ACCION_LISTAR_SINCRONIZACIONES_FACTURA = 'n';
    public static final char ACCION_VER_WS_TRANSACCIONES_DETALLE = 'o';
    public static final char ACCION_VER_ASEGURAMIENTO_AFILIADO_ID = 'p';
    public static final char ACCION_VER_MOTIVO_ESPECIFICO_HIJO = 'q';
    public static final char ACCION_CONSULTAR_UBICACION_USUARIO = 'r';
    public static final char ACCION_GUARDAR_EDICION_MOTIVO = 's';
    public static final char ACCION_VER_MOTIVO_GLOSA = 't';
    public static final char ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO = 'u';
    public static final char ACCION_BLOQUEAR_FACTURAS_AUDITORIA = 'v';
    public static final char ACCION_DESBLOQUEAR_FACTURAS_AUDITORIA = 'w';
    public static final char ACCION_BORRAR_AUTORIZACIONES_POR_FACTURA = 'x';
    public static final char ACCION_LISTAR_CENTRO_COSTOS_ASOCIADOS = 'y';
    public static final char ACCION_BORRAR_DESCUENTO_CAPITA = 'z';
    public static final char ACCION_VER_MOTIVOS_MULTI_DETALLES= '1';
    public static final char ACCION_BORRAR_MOTIVOS_ESPECIFICOS= '2';
    public static final char ACCION_GUARDAR_MARCADO_GLOSA_IPS = '3';
    public static final char ACCION_GUARDAR_DESMARCADO_GLOSA_IPS = '4';
    public static final char ACCION_VER_OBJECIONES_USUARIO = '5';
    public static final char ACCION_VER_CONTRATO_SEDES = '6';
    public static final char ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO = '7';
    public static final char ACCION_LISTAR_ADJUNTOS_CMDETALLES = '8';
    public static final char ACCION_LISTAR_MOTIVOS_ESPECIFICOS_DEVOLUCION = '9';
    public static final char ACCION_VER_MOTIVO_APLICACION_HIJO = '0';
    public static final char DO_ACCION_VER_SOPORTES = '-';

    private List<Maestro> listaMaeTipoDocumento;
    private HashMap<Integer, Maestro> hashMaeTipoDocumento;
    private List<Maestro> listaMaeConceptos;
    private List<Maestro> listaMaeConceptosTotales;
    private List<Maestro> listaMaeConceptosSubsidiados;
    private List<Maestro> listaMaeConceptosContributivos;
    private HashMap<Integer, Maestro> hashMaeConceptos;
    private List<Maestro> listaMaeCentrosCostos;
    private HashMap<Integer, Maestro> hashMaeCentrosCostos;
    private List<Maestro> listaMaeTipoContrato;
    private HashMap<Integer, Maestro> hashMaeTipoContrato;
    private List<Maestro> listaMaeMotivoDevolucion;
    private List<Maestro> listaMaeMotivoDevolucionEspecifico;
    private HashMap<Integer,List<Maestro>> hashMaeMotivoDevolucionPadre;
    private HashMap<Integer,List<Maestro>> hashMaeMotivoGlosaPadre;
    private HashMap<Integer, Maestro> hashMaeMotivoDevolucion;
    private HashMap<Integer, Maestro> hashMaeMotivoDevolucionEspecifico;
    private List<Maestro> listaMaeRegimen;
    private HashMap<Integer, Maestro> hashMaeRegimen;
    private List<Maestro> listaMaeMotivos;
    private HashMap<Integer, Maestro> hashMaeMotivos;
    private List<Maestro> listaMaeMotivosEspecificos;
    private List<Maestro> listaMaeMotivosAplicacion;
    private HashMap<Integer, Maestro> hashMaeMotivosEspecificos;
    private HashMap<Integer, Maestro> hashMaeMotivosAplicacion;
    private HashMap<Integer, String> hashInsumoAuditoriaEliminados;
    private HashMap<Integer, String> hashInsumoAuditoriaInsertados;
    private HashMap<Integer, Maestro> hashTipoSoporte = new HashMap<>();

    private String descripcionGenerica;
    private boolean esGestionIndividual;
    private boolean aplicaRecobro;
    private Map< String, Integer> listaTiposEstadoAuditoria;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    private PeAfiliadosPrograma peAfiliadosPrograma;
    private AsegAfiliado afiliadoCompleto;
    private HistorialAfiliadoHash historiaAfiliadoHash;
    private HashMap<Integer, CmDetalle> hashDetallesSelecionados;

    private int tipoGestionMasiva;
    private CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasiva;
    private AuAnexo4 auAnexo4;

    private BigDecimal sumaValorFacturadoDetallesPorFactura;
    private JsonMostrable jsonMostrable;
    
    private CmRespuestaGenerica cmRespuestaGenerica;
    private List<Integer> motivosEspecificosSeleccionados = new ArrayList();

    private SelDiagnosticosBean diagnosticosBean;

    public CmFactura getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFactura objeto) {
        this.objeto = objeto;
    }

    public CmReintento getObjetoReintento() {
        if (objetoReintento == null) {
            objetoReintento = new CmReintento();
        }
        return objetoReintento;
    }

    public void setObjetoReintento(CmReintento objetoReintento) {
        this.objetoReintento = objetoReintento;
    }

    public AsegRegistroNovedad getObjetoRegistroNovedad() {
        if (objetoRegistroNovedad == null) {
            objetoRegistroNovedad = new AsegRegistroNovedad();
        }
        return objetoRegistroNovedad;
    }

    public void setObjetoRegistroNovedad(AsegRegistroNovedad objetoRegistroNovedad) {
        this.objetoRegistroNovedad = objetoRegistroNovedad;
    }

    public List<CmFactura> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFactura> registros) {
        this.registros = registros;
    }

    public List<AsegAfiliadoHistorico> getRegistrosAuditoriaNovedad() {
        if (registrosAuditoriaNovedad == null) {
            registrosAuditoriaNovedad = new ArrayList<>();
        }
        return registrosAuditoriaNovedad;
    }

    public void setRegistrosAuditoriaNovedad(List<AsegAfiliadoHistorico> registrosAuditoriaNovedad) {
        this.registrosAuditoriaNovedad = registrosAuditoriaNovedad;
    }

    public AsegAfiliado getAfiliadoCompleto() {
        if(afiliadoCompleto == null){
            afiliadoCompleto = new AsegAfiliado();
        }
        return afiliadoCompleto;
    }

    public void setAfiliadoCompleto(AsegAfiliado afiliadoCompleto) {
        this.afiliadoCompleto = afiliadoCompleto;
    }

    public HistorialAfiliadoHash getHistoriaAfiliadoHash() {
        if (historiaAfiliadoHash == null) {
            historiaAfiliadoHash = new HistorialAfiliadoHash();
        }
        return historiaAfiliadoHash;
    }

    public void setHistoriaAfiliadoHash(HistorialAfiliadoHash historiaAfiliadoHash) {
        this.historiaAfiliadoHash = historiaAfiliadoHash;
    }

    public List<CmAuditoriaMotivoGlosa> getListaMotivos() {
        if (listaMotivos == null) {
            listaMotivos = new ArrayList<>();
        }
        return listaMotivos;
    }

    public void setListaMotivos(List<CmAuditoriaMotivoGlosa> listaMotivos) {
        this.listaMotivos = listaMotivos;
    }

    public List<CmAuditoriaMotivoGlosa> getListaMotivosEspecificosEncontrados() {
        if (listaMotivosEspecificosEncontrados == null) {
            listaMotivosEspecificosEncontrados = new ArrayList<>();
        }
        return listaMotivosEspecificosEncontrados;
    }

    public void setListaMotivosEspecificosEncontrados(List<CmAuditoriaMotivoGlosa> listaMotivosEspecificosEncontrados) {
        this.listaMotivosEspecificosEncontrados = listaMotivosEspecificosEncontrados;
    }
    
    

    public List<CmAuditoriaAutorizacion> getListaAutorizacion() {
        if (listaAutorizacion == null) {
            listaAutorizacion = new ArrayList<>();
        }
        return listaAutorizacion;
    }

    public void setListaAutorizacion(List<CmAuditoriaAutorizacion> listaAutorizacion) {
        this.listaAutorizacion = listaAutorizacion;
    }

    public List<CmAuditoriaDiagnostico> getListaDiagnosticos() {
        if (listaDiagnosticos == null) {
            listaDiagnosticos = new ArrayList<>();
        }
        return listaDiagnosticos;
    }

    public void setListaDiagnosticos(List<CmAuditoriaDiagnostico> listaDiagnosticos) {
        this.listaDiagnosticos = listaDiagnosticos;
    }

    public List<WsCmTransaccionDetalle> getListaWsCmTransaccionDetalle() {
        if (listaWsCmTransaccionDetalle == null) {
            listaWsCmTransaccionDetalle = new ArrayList<>();
        }
        return listaWsCmTransaccionDetalle;
    }

    public void setListaWsCmTransaccionDetalle(List<WsCmTransaccionDetalle> listaWsCmTransaccionDetalle) {
        this.listaWsCmTransaccionDetalle = listaWsCmTransaccionDetalle;
    }
    
    public List<CmAuditoriaConceptoContable> getListaConceptosContables() {
        if (listaConceptosContables == null) {
            listaConceptosContables = new ArrayList<>();
        }
        return listaConceptosContables;
    }

    public CmAuditoriaSelectorOperacionMasiva getCmSelectorOperacionaMasiva() {
        if (cmSelectorOperacionaMasiva == null) {
            cmSelectorOperacionaMasiva = new CmAuditoriaSelectorOperacionMasiva();
        }
        return cmSelectorOperacionaMasiva;
    }

    public void setCmSelectorOperacionaMasiva(CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasiva) {
        this.cmSelectorOperacionaMasiva = cmSelectorOperacionaMasiva;
    }

    public void setListaConceptosContables(List<CmAuditoriaConceptoContable> listaConceptosContables) {
        this.listaConceptosContables = listaConceptosContables;
    }

    public ParamConsulta getParamConsultaUtilitario() {
        if (paramConsultaUtilitario == null) {
            paramConsultaUtilitario = new ParamConsulta();
        }
        return paramConsultaUtilitario;
    }

    public ParamConsulta getParamConsultaServiciosAuditoria() {
        if (paramConsultaServiciosAuditoria == null) {
            paramConsultaServiciosAuditoria = new ParamConsulta();
        }
        return paramConsultaServiciosAuditoria;
    }

    public ParamConsulta getParamConsultaAuditoriaNovedad() {
        if (paramConsultaAuditoriaNovedad == null) {
            paramConsultaAuditoriaNovedad = new ParamConsulta();
        }
        return paramConsultaAuditoriaNovedad;
    }

    public void setParamConsultaAuditoriaNovedad(ParamConsulta paramConsultaAuditoriaNovedad) {
        this.paramConsultaAuditoriaNovedad = paramConsultaAuditoriaNovedad;
    }

    public ParamConsulta getParamConsultaAuditoriaDiagnosticos() {
        if (paramConsultaAuditoriaDiagnosticos == null) {
            paramConsultaAuditoriaDiagnosticos = new ParamConsulta();
        }
        return paramConsultaAuditoriaDiagnosticos;
    }

    public void setParamConsultaAuditoriaDiagnosticos(ParamConsulta paramConsultaAuditoriaDiagnosticos) {
        this.paramConsultaAuditoriaDiagnosticos = paramConsultaAuditoriaDiagnosticos;
    }

    public ParamConsulta getParamConsultaAuditoriaConceptoContable() {
        if (paramConsultaAuditoriaConceptoContable == null) {
            paramConsultaAuditoriaConceptoContable = new ParamConsulta();
        }
        return paramConsultaAuditoriaConceptoContable;
    }

    public void setParamConsultaAuditoriaConceptoContable(ParamConsulta paramConsultaAuditoriaConceptoContable) {
        this.paramConsultaAuditoriaConceptoContable = paramConsultaAuditoriaConceptoContable;
    }

    public ParamConsulta getParamConsultaAuditoriaMotivoGlosa() {
        if (paramConsultaAuditoriaMotivoGlosa == null) {
            paramConsultaAuditoriaMotivoGlosa = new ParamConsulta();
        }
        return paramConsultaAuditoriaMotivoGlosa;
    }

    public void setParamConsultaAuditoriaMotivoGlosa(ParamConsulta paramConsultaAuditoriaMotivoGlosa) {
        this.paramConsultaAuditoriaMotivoGlosa = paramConsultaAuditoriaMotivoGlosa;
    }

    public ParamConsulta getParamConsultaAuditoriaAutorizacion() {
        if (paramConsultaAuditoriaAutorizacion == null) {
            paramConsultaAuditoriaAutorizacion = new ParamConsulta();
        }
        return paramConsultaAuditoriaAutorizacion;
    }

    public ParamConsulta getParamConsultaAuditoriaAdjunto() {
        if (paramConsultaAuditoriaAdjunto == null) {
            paramConsultaAuditoriaAdjunto = new ParamConsulta();
        }
        return paramConsultaAuditoriaAdjunto;
    }

    public ParamConsulta getParamConsultaAuditoriaDescuentoCapita() {
        if (paramConsultaAuditoriaDescuentoCapita == null) {
            paramConsultaAuditoriaDescuentoCapita = new ParamConsulta();
        }
        return paramConsultaAuditoriaDescuentoCapita;
    }

    public void setParamConsultaAuditoriaDescuentoCapita(ParamConsulta paramConsultaAuditoriaDescuentoCapita) {
        this.paramConsultaAuditoriaDescuentoCapita = paramConsultaAuditoriaDescuentoCapita;
    }

    public ParamConsulta getParamConsultaDetallesAgestionar() {
        if (paramConsultaDetallesAgestionar == null) {
            paramConsultaDetallesAgestionar = new ParamConsulta();
        }
        return paramConsultaDetallesAgestionar;
    }

    public void setParamConsultaDetallesAgestionar(ParamConsulta paramConsultaDetallesAgestionar) {
        this.paramConsultaDetallesAgestionar = paramConsultaDetallesAgestionar;
    }

    public ParamConsulta getParamConsultaCntContratos() {
        if (paramConsultaCntContratos == null) {
            paramConsultaCntContratos = new ParamConsulta();
        }
        return paramConsultaCntContratos;
    }

    public void setParamConsultaCntContratos(ParamConsulta paramConsultaCntContratos) {
        this.paramConsultaCntContratos = paramConsultaCntContratos;
    }

    public void setParamConsultaAuditoriaAdjunto(ParamConsulta paramConsultaAuditoriaAdjunto) {
        this.paramConsultaAuditoriaAdjunto = paramConsultaAuditoriaAdjunto;
    }

    public ParamConsulta getParamConsultaAnexo4() {
        if (paramConsultaAnexo4 == null) {
            paramConsultaAnexo4 = new ParamConsulta();
        }
        return paramConsultaAnexo4;
    }

    public ParamConsulta getParamConsultaAfiliadoProgramas() {
        if (paramConsultaAfiliadoProgramas == null) {
            paramConsultaAfiliadoProgramas = new ParamConsulta();
        }
        return paramConsultaAfiliadoProgramas;
    }

    public ParamConsulta getParamConsultaConsultarSuceso() {
        if (paramConsultaConsultarSuceso == null) {
            paramConsultaConsultarSuceso = new ParamConsulta();
        }
        return paramConsultaConsultarSuceso;
    }

    public void setParamConsultaConsultarSuceso(ParamConsulta paramConsultaConsultarSuceso) {
        this.paramConsultaConsultarSuceso = paramConsultaConsultarSuceso;
    } 

    public void setParamConsultaAfiliadoProgramas(ParamConsulta paramConsultaAfiliadoProgramas) {
        this.paramConsultaAfiliadoProgramas = paramConsultaAfiliadoProgramas;
    }   

    public void setParamConsultaAnexo4(ParamConsulta paramConsultaAnexo4) {
        this.paramConsultaAnexo4 = paramConsultaAnexo4;
    }

    public ParamConsulta getParamConsultaAnexo4Item() {
        if (paramConsultaAnexo4Item == null) {
            paramConsultaAnexo4Item = new ParamConsulta();
        }
        return paramConsultaAnexo4Item;
    }

    public void setParamConsultaAnexo4Item(ParamConsulta paramConsultaAnexo4Item) {
        this.paramConsultaAnexo4Item = paramConsultaAnexo4Item;
    }

    public void setParamConsultaAuditoriaAutorizacion(ParamConsulta paramConsultaAuditoriaAutorizacion) {
        this.paramConsultaAuditoriaAutorizacion = paramConsultaAuditoriaAutorizacion;
    }

    public ParamConsulta getParamConsultaSoportes() {
        return paramConsultaSoportes;
    }

    public void setParamConsultaSoportes(ParamConsulta paramConsultaSoportes) {
        this.paramConsultaSoportes = paramConsultaSoportes;
    }

    public ParamConsulta getParamConsultaMaestroDiagnostico() {
        if (paramConsultaMaestroDiagnostico == null) {
            paramConsultaMaestroDiagnostico = new ParamConsulta();
        }
        return paramConsultaMaestroDiagnostico;
    }

    public void setParamConsultaMaestroDiagnostico(ParamConsulta paramConsultaMaestroDiagnostico) {
        this.paramConsultaMaestroDiagnostico = paramConsultaMaestroDiagnostico;
    }

    public void setParamConsultaServiciosAuditoria(ParamConsulta paramConsultaServiciosAuditoria) {
        this.paramConsultaServiciosAuditoria = paramConsultaServiciosAuditoria;
    }

    public void setParamConsultaUtilitario(ParamConsulta paramConsultaUtilitario) {
        this.paramConsultaUtilitario = paramConsultaUtilitario;
    }

    public CmAuditoriaFacturaServicioIface getCmAuditoriaFacturaServicio() {
        return cmAuditoriaFacturaServicio;
    }

    public void setCmAuditoriaFacturaServicio(CmAuditoriaFacturaServicioIface cmAuditoriaFacturaServicio) {
        this.cmAuditoriaFacturaServicio = cmAuditoriaFacturaServicio;
    }

    public LazyDataModel<CmFactura> getLazyCmAuditoriaFactuar() {
        return lazyCmAuditoriaFactuar;
    }

    public void setLazyCmAuditoriaFactuar(LazyDataModel<CmFactura> lazyCmAuditoriaFactuar) {
        this.lazyCmAuditoriaFactuar = lazyCmAuditoriaFactuar;
    }

    public LazyDataModel<CmDetalle> getLazyCmAuditoriaDetalles() {
        return lazyCmAuditoriaDetalles;
    }

    public void setLazyCmAuditoriaDetalles(LazyDataModel<CmDetalle> lazyCmAuditoriaDetalles) {
        this.lazyCmAuditoriaDetalles = lazyCmAuditoriaDetalles;
    }

    public List<CmDetalle> getRegistrosAuditoriaDetalles() {
        if (registrosAuditoriaDetalles == null) {
            registrosAuditoriaDetalles = new ArrayList<>();
        }
        return registrosAuditoriaDetalles;
    }

    public void setRegistrosAuditoriaDetalles(List<CmDetalle> registrosAuditoriaDetalles) {
        this.registrosAuditoriaDetalles = registrosAuditoriaDetalles;
    }

    public List<CmSincronizacion> getListaSincronizaciones() {
        if (listaSincronizaciones == null) {
            listaSincronizaciones = new ArrayList<>();
        }
        return listaSincronizaciones;
    }

    public void setListaSincronizaciones(List<CmSincronizacion> listaSincronizaciones) {
        this.listaSincronizaciones = listaSincronizaciones;
    }

    public ParamConsulta getParamConsultaRespuestaFactura() {
        if (paramConsultaRespuestaFactura == null) {
            paramConsultaRespuestaFactura = new ParamConsulta();
        }
        return paramConsultaRespuestaFactura;
    }

    public void setParamConsultaRespuestaFactura(ParamConsulta paramConsultaRespuestaFactura) {
        this.paramConsultaRespuestaFactura = paramConsultaRespuestaFactura;
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

    public List<CmDetalle> getRegistrosGlosaFacturaDetalle() {
        return registrosGlosaFacturaDetalle;
    }

    public void setRegistrosGlosaFacturaDetalle(List<CmDetalle> registrosGlosaFacturaDetalle) {
        this.registrosGlosaFacturaDetalle = registrosGlosaFacturaDetalle;
    }

    public ParamConsulta getParamConsultaGlosaFacturaDetalles() {
        if (paramConsultaGlosaFacturaDetalles == null) {
            paramConsultaGlosaFacturaDetalles = new ParamConsulta();
        }
        return paramConsultaGlosaFacturaDetalles;
    }

    public void setParamConsultaGlosaFacturaDetalles(ParamConsulta paramConsultaGlosaFacturaDetalles) {
        this.paramConsultaGlosaFacturaDetalles = paramConsultaGlosaFacturaDetalles;
    }

    public LazyDataModel<CmDetalle> getLazyGlosaFacturaDetalle() {
        return lazyGlosaFacturaDetalle;
    }

    public void setLazyGlosaFacturaDetalle(LazyDataModel<CmDetalle> lazyGlosaFacturaDetalle) {
        this.lazyGlosaFacturaDetalle = lazyGlosaFacturaDetalle;
    }

    public LazyDataModel<AuAnexo4> getLazyAnexo4() {
        return lazyAnexo4;
    }

    public void setLazyAnexo4(LazyDataModel<AuAnexo4> lazyAnexo4) {
        this.lazyAnexo4 = lazyAnexo4;
    }

    public LazyDataModel<AuAnexo4Item> getLazyAnexo4Items() {
        return lazyAnexo4Items;
    }

    public void setLazyAnexo4Items(LazyDataModel<AuAnexo4Item> lazyAnexo4Items) {
        this.lazyAnexo4Items = lazyAnexo4Items;
    }

    public void setLazyCntContratos(LazyDataModel<CntContrato> lazyCntContratos) {
        this.lazyCntContratos = lazyCntContratos;
    }

    public LazyDataModel<CntContrato> getLazyCntContratos() {
        return lazyCntContratos;
    }

    public LazyDataModel<CmDetalle> getLazyDetalleAgestionar() {
        return lazyDetalleAgestionar;
    }

    public void setLazyDetalleAgestionar(LazyDataModel<CmDetalle> lazyDetalleAgestionar) {
        this.lazyDetalleAgestionar = lazyDetalleAgestionar;
    }

    public LazyDataModel<AsegRegistroNovedad> getLazyAseguramientoNovedadHistorial() {
        return lazyAseguramientoNovedadHistorial;
    }

    public void setLazyAseguramientoNovedadHistorial(LazyDataModel<AsegRegistroNovedad> lazyAseguramientoNovedadHistorial) {
        this.lazyAseguramientoNovedadHistorial = lazyAseguramientoNovedadHistorial;
    }

    public LazyDataModel<PeAfiliadosPrograma> getLazyAfiliadosPrograma() {
        return lazyAfiliadosPrograma;
    }

    public void setLazyAfiliadosPrograma(LazyDataModel<PeAfiliadosPrograma> lazyAfiliadosPrograma) {
        this.lazyAfiliadosPrograma = lazyAfiliadosPrograma;
    }
    
    public List<CmDetalle> getRegistrosDetallesAgestionar() {
        return registrosDetallesAgestionar;
    }

    public void setRegistrosDetallesAgestionar(List<CmDetalle> registrosDetallesAgestionar) {
        this.registrosDetallesAgestionar = registrosDetallesAgestionar;
    }

    public List<AsegRegistroNovedad> getRegistrosAseguramientoNovedad() {
        if (registrosAseguramientoNovedad == null) {
            registrosAseguramientoNovedad = new ArrayList<>();
        }
        return registrosAseguramientoNovedad;
    }

    public void setRegistrosAseguramientoNovedad(List<AsegRegistroNovedad> registrosAseguramientoNovedad) {
        this.registrosAseguramientoNovedad = registrosAseguramientoNovedad;
    }

    public List<PeAfiliadosPrograma> getRegistrosAfiliadosPrograma() {
        if (registrosAfiliadosPrograma == null) {
            registrosAfiliadosPrograma = new ArrayList<>();
        }
        return registrosAfiliadosPrograma;
    }

    public void setRegistrosAfiliadosPrograma(List<PeAfiliadosPrograma> registrosAfiliadosPrograma) {
        this.registrosAfiliadosPrograma = registrosAfiliadosPrograma;
    }

    public List<AucHospitalizacionObjecion> getRegistroHospitalizacionObjeciones() {
        if (registroHospitalizacionObjeciones == null) {
            registroHospitalizacionObjeciones = new ArrayList<>();
        }
        return registroHospitalizacionObjeciones;
    }

    public void setRegistroHospitalizacionObjeciones(List<AucHospitalizacionObjecion> registroHospitalizacionObjeciones) {
        this.registroHospitalizacionObjeciones = registroHospitalizacionObjeciones;
    }   

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public CmFactura getGlosaFactura() {
        return glosaFactura;
    }

    public void setGlosaFactura(CmFactura glosaFactura) {
        this.glosaFactura = glosaFactura;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
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

    public List<CmAuditoriaAdjunto> getRegistrosAuditoriaAdjuto() {
        if (registrosAuditoriaAdjuto == null) {
            registrosAuditoriaAdjuto = new ArrayList<>();
        }
        return registrosAuditoriaAdjuto;
    }

    public void setRegistrosAuditoriaAdjuto(List<CmAuditoriaAdjunto> registrosAuditoriaAdjuto) {
        this.registrosAuditoriaAdjuto = registrosAuditoriaAdjuto;
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
    
    
    

    public List<CmDetalle> getRegistrosDetallesSeleccionadoMasivos() {
        if (registrosDetallesSeleccionadoMasivos == null) {
            registrosDetallesSeleccionadoMasivos = new ArrayList<>();
        }
        return registrosDetallesSeleccionadoMasivos;
    }

    public void setRegistrosDetallesSeleccionadoMasivos(List<CmDetalle> registrosDetallesSeleccionadoMasivos) {
        this.registrosDetallesSeleccionadoMasivos = registrosDetallesSeleccionadoMasivos;
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

    public List<CntContrato> getRegistrosCntContratos() {
        if (registrosCntContratos == null) {
            registrosCntContratos = new ArrayList<>();
        }
        return registrosCntContratos;
    }

    public void setRegistrosCntContratos(List<CntContrato> registrosCntContratos) {
        this.registrosCntContratos = registrosCntContratos;
    }

    public List<CmDetalle> getRegistrosDetallesGlosar() {
        if (registrosDetallesGlosar == null) {
            registrosDetallesGlosar = new ArrayList<>();
        }
        return registrosDetallesGlosar;
    }

    public void setRegistrosDetallesGlosar(List<CmDetalle> registrosDetallesGlosar) {
        this.registrosDetallesGlosar = registrosDetallesGlosar;
    }

    public List<AuAnexo4> getRegistrosAnexo4() {
        if (registrosAnexo4 == null) {
            registrosAnexo4 = new ArrayList<>();
        }
        return registrosAnexo4;
    }

    public void setRegistrosAnexo4(List<AuAnexo4> registrosAnexo4) {
        this.registrosAnexo4 = registrosAnexo4;
    }

    public List<AuAnexo4Item> getRegistrosAnexo4Item() {
        if (registrosAnexo4Item == null) {
            registrosAnexo4Item = new ArrayList<>();
        }
        return registrosAnexo4Item;
    }

    public void setRegistrosAnexo4Item(List<AuAnexo4Item> registrosAnexo4Item) {
        this.registrosAnexo4Item = registrosAnexo4Item;
    }

    public List<Maestro> getRegistrosMaestroDiagnostico() {
        if (registrosMaestroDiagnostico == null) {
            registrosMaestroDiagnostico = new ArrayList<>();
        }
        return registrosMaestroDiagnostico;
    }

    public void setRegistrosMaestroDiagnostico(List<Maestro> registrosMaestroDiagnostico) {
        this.registrosMaestroDiagnostico = registrosMaestroDiagnostico;
    }

    public CmAuditoriaDevolucion getCmAuditoriaDevolucion() {
        return cmAuditoriaDevolucion;
    }

    public void setCmAuditoriaDevolucion(CmAuditoriaDevolucion cmAuditoriaDevolucion) {
        this.cmAuditoriaDevolucion = cmAuditoriaDevolucion;
    }

    public CmAuditoriaMotivoGlosa getCmAuditoriaMotivoGlosa() {
        if (cmAuditoriaMotivoGlosa == null) {
            cmAuditoriaMotivoGlosa = new CmAuditoriaMotivoGlosa();
        }
        return cmAuditoriaMotivoGlosa;
    }

    public void setCmAuditoriaMotivoGlosa(CmAuditoriaMotivoGlosa cmAuditoriaMotivoGlosa) {
        this.cmAuditoriaMotivoGlosa = cmAuditoriaMotivoGlosa;
    }

    public CmAuditoriaAutorizacion getCmAuditoriaAutorizacion() {
        if (cmAuditoriaAutorizacion == null) {
            cmAuditoriaAutorizacion = new CmAuditoriaAutorizacion();
        }
        return cmAuditoriaAutorizacion;
    }

    public void setCmAuditoriaAutorizacion(CmAuditoriaAutorizacion cmAuditoriaAutorizacion) {
        this.cmAuditoriaAutorizacion = cmAuditoriaAutorizacion;
    }

    public CmAuditoriaConceptoContable getCmAuditoriaConceptoContable() {
        if (cmAuditoriaConceptoContable == null) {
            cmAuditoriaConceptoContable = new CmAuditoriaConceptoContable();
        }
        return cmAuditoriaConceptoContable;
    }

    public void setCmAuditoriaConceptoContable(CmAuditoriaConceptoContable cmAuditoriaConceptoContable) {
        this.cmAuditoriaConceptoContable = cmAuditoriaConceptoContable;
    }

    public CmAuditoriaDiagnostico getCmAuditoriaDiagnostico() {
        if (cmAuditoriaDiagnostico == null) {
            cmAuditoriaDiagnostico = new CmAuditoriaDiagnostico();
        }
        return cmAuditoriaDiagnostico;
    }

    public void setCmAuditoriaDiagnostico(CmAuditoriaDiagnostico cmAuditoriaDiagnostico) {
        this.cmAuditoriaDiagnostico = cmAuditoriaDiagnostico;
    }

    public CmAuditoriaNovedad getCmAuditoriaNovedad() {
        if (cmAuditoriaNovedad == null) {
            cmAuditoriaNovedad = new CmAuditoriaNovedad();
        }
        return cmAuditoriaNovedad;
    }

    public void setCmAuditoriaNovedad(CmAuditoriaNovedad cmAuditoriaNovedad) {
        this.cmAuditoriaNovedad = cmAuditoriaNovedad;
    }

    public CmAuditoriaUsuarioActual getCmAuditoriaUsuarioActual() {
        if (cmAuditoriaUsuarioActual == null) {
            cmAuditoriaUsuarioActual = new CmAuditoriaUsuarioActual();
        }
        return cmAuditoriaUsuarioActual;
    }

    public void setCmAuditoriaUsuarioActual(CmAuditoriaUsuarioActual cmAuditoriaUsuarioActual) {
        this.cmAuditoriaUsuarioActual = cmAuditoriaUsuarioActual;
    }

    public CmAuditoriaCapitaDescuento getCmAuditoriaCapitaDescuento() {
        if (cmAuditoriaCapitaDescuento == null) {
            cmAuditoriaCapitaDescuento = new CmAuditoriaCapitaDescuento();
        }
        return cmAuditoriaCapitaDescuento;
    }

    public void setCmAuditoriaCapitaDescuento(CmAuditoriaCapitaDescuento cmAuditoriaCapitaDescuento) {
        this.cmAuditoriaCapitaDescuento = cmAuditoriaCapitaDescuento;
    }

    public List<CmAuditoriaCapitaDescuento> getRegistrosAuditoriaCapitaDescuento() {
        if (registrosAuditoriaCapitaDescuento == null) {
            registrosAuditoriaCapitaDescuento = new ArrayList<>();
        }
        return registrosAuditoriaCapitaDescuento;
    }

    public void setRegistrosAuditoriaCapitaDescuento(List<CmAuditoriaCapitaDescuento> registrosAuditoriaCapitaDescuento) {
        this.registrosAuditoriaCapitaDescuento = registrosAuditoriaCapitaDescuento;
    }

    public CmDetalle getObjetoItemServicio() {
        if (objetoItemServicio == null) {
            objetoItemServicio = new CmDetalle();
        }
        return objetoItemServicio;
    }

    public void setObjetoItemServicio(CmDetalle objetoItemServicio) {
        this.objetoItemServicio = objetoItemServicio;
    }

    public List<Maestro> getListaMaeTipoDocumento() {
        return listaMaeTipoDocumento;
    }

    public void setListaMaeTipoDocumento(List<Maestro> listaMaeTipoDocumento) {
        this.listaMaeTipoDocumento = listaMaeTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashMaeTipoDocumento() {
        return hashMaeTipoDocumento;
    }

    public void setHashMaeTipoDocumento(HashMap<Integer, Maestro> hashMaeTipoDocumento) {
        this.hashMaeTipoDocumento = hashMaeTipoDocumento;
    }

    public List<Maestro> getListaMaeConceptos() {
        return listaMaeConceptos;
    }

    public void setListaMaeConceptos(List<Maestro> listaMaeConceptos) {
        this.listaMaeConceptos = listaMaeConceptos;
    }

    public List<Maestro> getListaMaeConceptosSubsidiados() {
        if( listaMaeConceptosSubsidiados == null ){
            listaMaeConceptosSubsidiados = new ArrayList<>();
        }
        return listaMaeConceptosSubsidiados;
    }

    public void setListaMaeConceptosSubsidiados(List<Maestro> listaMaeConceptosSubsidiados) {
        this.listaMaeConceptosSubsidiados = listaMaeConceptosSubsidiados;
    }

    public List<Maestro> getListaMaeConceptosContributivos() {
        if( listaMaeConceptosContributivos == null ){
            listaMaeConceptosContributivos = new ArrayList<>();
        }
        return listaMaeConceptosContributivos;
    }

    public void setListaMaeConceptosContributivos(List<Maestro> listaMaeConceptosContributivos) {
        this.listaMaeConceptosContributivos = listaMaeConceptosContributivos;
    } 

    public List<Maestro> getListaMaeConceptosTotales() {
        if( listaMaeConceptosTotales == null ){
            listaMaeConceptosTotales = new ArrayList<>();
        }
        return listaMaeConceptosTotales;
    }

    public void setListaMaeConceptosTotales(List<Maestro> listaMaeConceptosTotales) {
        this.listaMaeConceptosTotales = listaMaeConceptosTotales;
    }
    

    public HashMap<Integer, Maestro> getHashMaeConceptos() {
        return hashMaeConceptos;
    }

    public void setHashMaeConceptos(HashMap<Integer, Maestro> hashMaeConceptos) {
        this.hashMaeConceptos = hashMaeConceptos;
    }

    public List<Maestro> getListaMaeCentrosCostos() {
        return listaMaeCentrosCostos;
    }

    public void setListaMaeCentrosCostos(List<Maestro> listaMaeCentrosCostos) {
        this.listaMaeCentrosCostos = listaMaeCentrosCostos;
    }

    public HashMap<Integer, Maestro> getHashMaeCentrosCostos() {
        return hashMaeCentrosCostos;
    }

    public void setHashMaeCentrosCostos(HashMap<Integer, Maestro> hashMaeCentrosCostos) {
        this.hashMaeCentrosCostos = hashMaeCentrosCostos;
    }

    public List<Maestro> getListaMaeTipoContrato() {
        return listaMaeTipoContrato;
    }

    public void setListaMaeTipoContrato(List<Maestro> listaMaeTipoContrato) {
        this.listaMaeTipoContrato = listaMaeTipoContrato;
    }

    public HashMap<Integer, Maestro> getHashMaeTipoContrato() {
        return hashMaeTipoContrato;
    }

    public void setHashMaeTipoContrato(HashMap<Integer, Maestro> hashMaeTipoContrato) {
        this.hashMaeTipoContrato = hashMaeTipoContrato;
    }

    public List<Maestro> getListaMaeMotivoDevolucion() {
        if(listaMaeMotivoDevolucion == null){
            listaMaeMotivoDevolucion = new ArrayList<>();
        }
        return listaMaeMotivoDevolucion;
    }

    public void setListaMaeMotivoDevolucion(List<Maestro> listaMaeMotivoDevolucion) {
        this.listaMaeMotivoDevolucion = listaMaeMotivoDevolucion;
    }

    public List<Maestro> getListaMaeMotivoDevolucionEspecifico() {
        if(listaMaeMotivoDevolucionEspecifico == null){
            listaMaeMotivoDevolucionEspecifico = new ArrayList<>();
        }
        return listaMaeMotivoDevolucionEspecifico;
    }

    public void setListaMaeMotivoDevolucionEspecifico(List<Maestro> listaMaeMotivoDevolucionEspecifico) {
        this.listaMaeMotivoDevolucionEspecifico = listaMaeMotivoDevolucionEspecifico;
    }
    
    public HashMap<Integer, Maestro> getHashMaeMotivoDevolucion() {
        return hashMaeMotivoDevolucion;
    }

    public void setHashMaeMotivoDevolucion(HashMap<Integer, Maestro> hashMaeMotivoDevolucion) {
        this.hashMaeMotivoDevolucion = hashMaeMotivoDevolucion;
    }

    public HashMap<Integer, Maestro> getHashMaeMotivoDevolucionEspecifico() {
        return hashMaeMotivoDevolucionEspecifico;
    }

    public void setHashMaeMotivoDevolucionEspecifico(HashMap<Integer, Maestro> hashMaeMotivoDevolucionEspecifico) {
        this.hashMaeMotivoDevolucionEspecifico = hashMaeMotivoDevolucionEspecifico;
    }

    public HashMap<Integer, List<Maestro>> getHashMaeMotivoDevolucionPadre() {
        if (hashMaeMotivoDevolucionPadre == null) {
            hashMaeMotivoDevolucionPadre = new HashMap<>();
        }
        return hashMaeMotivoDevolucionPadre;
    }

    public void setHashMaeMotivoDevolucionPadre(HashMap<Integer, List<Maestro>> hashMaeMotivoDevolucionPadre) {
        this.hashMaeMotivoDevolucionPadre = hashMaeMotivoDevolucionPadre;
    }    

    public HashMap<Integer, List<Maestro>> getHashMaeMotivoGlosaPadre() {
        if (hashMaeMotivoGlosaPadre == null) {
            hashMaeMotivoGlosaPadre = new HashMap<>();
        }
        return hashMaeMotivoGlosaPadre;
    }

    public void setHashMaeMotivoGlosaPadre(HashMap<Integer, List<Maestro>> hashMaeMotivoGlosaPadre) {
        this.hashMaeMotivoGlosaPadre = hashMaeMotivoGlosaPadre;
    }
    
    public List<Maestro> getListaMaeRegimen() {
        return listaMaeRegimen;
    }

    public void setListaMaeRegimen(List<Maestro> listaMaeRegimen) {
        this.listaMaeRegimen = listaMaeRegimen;
    }

    public HashMap<Integer, Maestro> getHashMaeRegimen() {
        return hashMaeRegimen;
    }

    public void setHashMaeRegimen(HashMap<Integer, Maestro> hashMaeRegimen) {
        this.hashMaeRegimen = hashMaeRegimen;
    }

    public List<Maestro> getListaMaeMotivos() {
        return listaMaeMotivos;
    }

    public void setListaMaeMotivos(List<Maestro> listaMaeMotivos) {
        this.listaMaeMotivos = listaMaeMotivos;
    }

    public HashMap<Integer, Maestro> getHashMaeMotivos() {
        return hashMaeMotivos;
    }

    public void setHashMaeMotivos(HashMap<Integer, Maestro> hashMaeMotivos) {
        this.hashMaeMotivos = hashMaeMotivos;
    }

    public List<Maestro> getListaMaeMotivosEspecificos() {
        if (listaMaeMotivosEspecificos == null) {
            listaMaeMotivosEspecificos = new ArrayList<>();
        }
        return listaMaeMotivosEspecificos;
    }

    public void setListaMaeMotivosEspecificos(List<Maestro> listaMaeMotivosEspecificos) {
        this.listaMaeMotivosEspecificos = listaMaeMotivosEspecificos;
    }

    public List<Maestro> getListaMaeMotivosAplicacion() {
        if (listaMaeMotivosAplicacion == null) {
            listaMaeMotivosAplicacion = new ArrayList<>();
        }
        return listaMaeMotivosAplicacion;
    }

    public void setListaMaeMotivosAplicacion(List<Maestro> listaMaeMotivosAplicacion) {
        this.listaMaeMotivosAplicacion = listaMaeMotivosAplicacion;
    }
   
    public HashMap<Integer, Maestro> getHashMaeMotivosEspecificos() {
        if (hashMaeMotivosEspecificos == null) {
            hashMaeMotivosEspecificos = new HashMap<>();
        }
        return hashMaeMotivosEspecificos;
    }

    public void setHashMaeMotivosEspecificos(HashMap<Integer, Maestro> hashMaeMotivosEspecificos) {
        this.hashMaeMotivosEspecificos = hashMaeMotivosEspecificos;
    }

    public HashMap<Integer, Maestro> getHashMaeMotivosAplicacion() {
        if (hashMaeMotivosAplicacion == null) {
            hashMaeMotivosAplicacion = new HashMap<>();
        }
        return hashMaeMotivosAplicacion;
    }

    public void setHashMaeMotivosAplicacion(HashMap<Integer, Maestro> hashMaeMotivosAplicacion) {
        this.hashMaeMotivosAplicacion = hashMaeMotivosAplicacion;
    }
    
    public HashMap<Integer, String> getHashInsumoAuditoriaEliminados() {
        if (hashInsumoAuditoriaEliminados == null) {
            hashInsumoAuditoriaEliminados = new HashMap<>();
        }
        return hashInsumoAuditoriaEliminados;
    }

    public void setHashInsumoAuditoriaEliminados(HashMap<Integer, String> hashInsumoAuditoriaEliminados) {
        this.hashInsumoAuditoriaEliminados = hashInsumoAuditoriaEliminados;
    }
    
    public void limpiarHashInsumoAuditoriaInsertados(){
           getHashInsumoAuditoriaInsertados().clear();
    }


    public ArrayList<Integer> getRegistrosAdjuntosEliminar() {
       if (registrosAdjuntosEliminar == null) {
            registrosAdjuntosEliminar = new ArrayList<>();
        }
        return registrosAdjuntosEliminar;
    }

    public void setRegistrosAdjuntosEliminar(ArrayList<Integer> registrosAdjuntosEliminar) {
        this.registrosAdjuntosEliminar = registrosAdjuntosEliminar;
    }

    public ArrayList<CmFeSoporte> getRegistrosSoportes() {
        return registrosSoportes;
    }

    public void setRegistrosSoportes(ArrayList<CmFeSoporte> registrosSoportes) {
        this.registrosSoportes = registrosSoportes;
    }
   
    public HashMap<Integer, String> getHashInsumoAuditoriaInsertados() {
        if (hashInsumoAuditoriaInsertados == null) {
            hashInsumoAuditoriaInsertados = new HashMap<>();
        }
        return hashInsumoAuditoriaInsertados;
    }

    public void setHashInsumoAuditoriaInsertados(HashMap<Integer, String> hashInsumoAuditoriaInsertados) {
        this.hashInsumoAuditoriaInsertados = hashInsumoAuditoriaInsertados;
    }

    public HashMap<Integer, Maestro> getHashTipoSoporte() {
        return hashTipoSoporte;
    }

    public void setHashTipoSoporte(HashMap<Integer, Maestro> hashTipoSoporte) {
        this.hashTipoSoporte = hashTipoSoporte;
    }
    
  
    public void limpiarHashInsumoAuditoriaEliminados(){
         getHashInsumoAuditoriaEliminados().clear();
    }

    public boolean isEsGestionIndividual() {
        return esGestionIndividual;
    }

    public void setEsGestionIndividual(boolean esGestionIndividual) {
        this.esGestionIndividual = esGestionIndividual;
    }

    public boolean isAplicaRecobro() {
        return aplicaRecobro;
    }
    
     public boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }  

    public Map<String, Integer> getListaTiposEstadoAuditoria() {
        if (listaTiposEstadoAuditoria == null) {
            listaTiposEstadoAuditoria = new HashMap<>();
        }
        return listaTiposEstadoAuditoria;
    }

    public void setListaTiposEstadoAuditoria(Map<String, Integer> listaTiposEstadoAuditoria) {
        this.listaTiposEstadoAuditoria = listaTiposEstadoAuditoria;
    }

    public int getTipoGestionMasiva() {
        return tipoGestionMasiva;
    }

    public void setTipoGestionMasiva(int tipoGestionMasiva) {
        this.tipoGestionMasiva = tipoGestionMasiva;
    }

    public AuAnexo4 getAuAnexo4() {
        if (auAnexo4 == null) {
            auAnexo4 = new AuAnexo4();
        }
        return auAnexo4;
    }

    public void setAuAnexo4(AuAnexo4 auAnexo4) {
        this.auAnexo4 = auAnexo4;
    }

    public BigDecimal getSumaValorFacturadoDetallesPorFactura() {
        return sumaValorFacturadoDetallesPorFactura;
    }

    public void setSumaValorFacturadoDetallesPorFactura(BigDecimal sumaValorFacturadoDetallesPorFactura) {
        this.sumaValorFacturadoDetallesPorFactura = sumaValorFacturadoDetallesPorFactura;
    }

    public JsonMostrable getJsonMostrable() {
        if (jsonMostrable == null) {
            jsonMostrable = new JsonMostrable();
        }
        return jsonMostrable;
    }

    public void setJsonMostrable(JsonMostrable jsonMostrable) {
        this.jsonMostrable = jsonMostrable;
    }

    public SelDiagnosticosBean getDiagnosticosBean() {
        return diagnosticosBean;
    }

    public void setDiagnosticosBean(SelDiagnosticosBean diagnosticosBean) {
        this.diagnosticosBean = diagnosticosBean;
    }

    public CmRespuestaGenerica getCmRespuestaGenerica() {
        if(cmRespuestaGenerica == null){
             cmRespuestaGenerica = new CmRespuestaGenerica();
        }
        return cmRespuestaGenerica;
    }

    public void setCmRespuestaGenerica(CmRespuestaGenerica cmRespuestaGenerica) {
        this.cmRespuestaGenerica = cmRespuestaGenerica;
    }

    public PeAfiliadosPrograma getPeAfiliadosPrograma() {
        if (peAfiliadosPrograma == null) {
            peAfiliadosPrograma = new PeAfiliadosPrograma();
        }
        return peAfiliadosPrograma;
    }

    public List<Integer> getMotivosEspecificosSeleccionados() {
        return motivosEspecificosSeleccionados;
    }

    public void setMotivosEspecificosSeleccionados(List<Integer> motivosEspecificosSeleccionados) {
        this.motivosEspecificosSeleccionados = motivosEspecificosSeleccionados;
    }

    public void setPeAfiliadosPrograma(PeAfiliadosPrograma peAfiliadosPrograma) {
        this.peAfiliadosPrograma = peAfiliadosPrograma;
    }

    public CntContrato getContratoConSedes() {
        return contratoConSedes;
    }

    public void setContratoConSedes(CntContrato contratoConSedes) {
        this.contratoConSedes = contratoConSedes;
    }

    public LazyDataModel<CmFeSoporte> getLazySoportes() {
        return lazySoportes;
    }

    public void setLazySoportes(LazyDataModel<CmFeSoporte> lazySoportes) {
        this.lazySoportes = lazySoportes;
    }

    public CmAuditoriaFacturaBean() {
        this.objeto = new CmFactura();
        this.cmAuditoriaDevolucion = new CmAuditoriaDevolucion();
        Modulo mod = super.validarModulo(Modulo.ID_FACTURA_AUDITORIA_EVENTO);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());

        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyCmAuditoriaFactuar = new LazyDataModel<CmFactura>() {
                private List<CmFactura> auditoriaFacturas;
                
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
                    auditoriaFacturas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return auditoriaFacturas;
                }

                @Override
                public String getRowKey(CmFactura auditoriaFactura) {
                    return auditoriaFactura.getId().toString();
                }

                @Override
                public CmFactura getRowData(String auditoriaFacturaId) {
                    Integer id = Integer.valueOf(auditoriaFacturaId);
                    for (CmFactura auditoriaFactura : auditoriaFacturas) {
                        if (id.equals(auditoriaFactura.getId())) {
                            return auditoriaFactura;
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
        getCmAuditoriaFacturaServicio().cargaInicial(this);
        listar();
    }

    @PreDestroy
    public void preDestroy() {
        this.objeto = null;
        this.cmAuditoriaDevolucion = null;
        this.cmSelectorOperacionaMasiva = null;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void verFactura(int idFactura) {
        this.getObjeto().setId(idFactura);
        inicializarTablaDetallesAuditoria();
        listarAdjuntos();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("scrollPanelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verRespuestaDestallesGlosa(CmFactura factura) {
        this.setGlosaFactura(factura);
        inicializarTablaGlosaFacturaDetalles(factura.getId());
        PrimeFaces.current().resetInputs("frmVerGlosaRespuesas");
        PrimeFaces.current().ajax().update("frmVerGlosaRespuesas");
        PrimeFaces.current().executeScript("PF('dialogoVerGlosaRespuestaDetalles').show()");
        procesoFinal();
    }

    public void verGestionarFactura(int idFactura) {
        this.getObjeto().setId(idFactura);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_FACTURA);
        getRegistrosDetallesSeleccionadoMasivos().clear();
        getHashDetallesSelecionados().clear();
        getCmAuditoriaFacturaServicio().Accion(this);

        boolean esValidoProceso = validarFacturaEsAuditable(this.getObjeto());
        if (!esValidoProceso) {
            this.addError("La factura de id " + this.getObjeto().getId() + " ya se encuentra en proceso de auditoría "
                    + "en estado : " + this.getObjeto().getEstadoFacturaStr());
        }

        if (esValidoProceso) {
            esValidoProceso = validarSumatoriaDetallesPorValorFactura(this.getObjeto());
        }
        
        if (esValidoProceso) {
            esValidoProceso = bloquarFacturaPorUsuario();
        }

        if (esValidoProceso) {
            inicializarTablaDetallesAuditoria();
            listarAdjuntos();
            limpiarFiltrosPantallaGestionarFactura();
            super.setDoAccion(ACCION_VER_GESTION_FACTURA);
            procesoFinal();
            PrimeFaces.current().ajax().update("scrollPanelGestionarFactura");
            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        } else {
            PrimeFaces.current().resetInputs("frmConsultaAuditoriaEvento");
            generarMensajes();
        }

    }

    public void verListaMaDiagnosticos() {
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }

    public void verGeneracionInforme(int idFactura) {
        this.getObjeto().setId(idFactura);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmGenerarReporte");
        PrimeFaces.current().ajax().update("frmGenerarReporte");
        PrimeFaces.current().executeScript("PF('dialogoGenerarReporte').show()");
        procesoFinal();
    }

    public void verNovedades(int idServicio) {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_DETALLE);
        this.getObjetoItemServicio().setId(idServicio);
        getCmAuditoriaFacturaServicio().Accion(this);
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_ASEGURAMIENTO_AFILIADO_ID);
        getCmAuditoriaFacturaServicio().Accion(this);
        inicializarTablaAuditoriaNovedad(getObjetoRegistroNovedad().getIdAfiliado());
        PrimeFaces.current().resetInputs("frmNovedades");
        PrimeFaces.current().ajax().update("frmNovedades");
        PrimeFaces.current().executeScript("PF('dialogoNovedades').show()");
        procesoFinal();
    }

    public void verGestionarItem(CmDetalle detalle) {
        this.getObjeto().setId(detalle.getCmFacturas().getId());
        this.setObjetoItemServicio(new CmDetalle());
        this.getObjetoItemServicio().setId(detalle.getId());
        this.getObjetoItemServicio().setCmFacturas(new CmFactura(detalle.getCmFacturas().getId()));
        this.setAplicaRecobro(detalle.getAplicaRecobro());
        
        asignacionValoresConsultaSuceso(detalle);
        limpiarRegistrosTemporales();
        setEsGestionIndividual(true);
        listarConceptosContables();
        listarDiagnosticos();
        listarMotivosGlosa();
        listarAutorizaciones();
        listarAdjuntosCmDetalles();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_VISTA_GETION_ITEM);
        getCmAuditoriaFacturaServicio().Accion(this);
        verProgramasSegunDocumentoAfiliado(getObjetoItemServicio());
        super.setDoAccion(ACCION_VER_VISTA_GETION_ITEM);
        PrimeFaces.current().resetInputs("frmGestionarItem");
        PrimeFaces.current().ajax().update("frmGestionarItem");
        PrimeFaces.current().executeScript("PF('dialogoGestionarItem').show()");
        procesoFinal();
    }

    public void verGestionarMasivo(int idFactura, int tipoGestionMasiva) {

        this.getObjeto().setId(idFactura);
        String idFacturasSeleccionadas = "";
        boolean validarProceso = true;
        this.setAplicaRecobro(false);
        this.setTipoGestionMasiva(tipoGestionMasiva);

        getRegistrosDetallesSeleccionadoMasivos().clear();

        if (GESTION_MASIVA_PARCIAL == tipoGestionMasiva) {
            setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values()));
            StringBuilder sb = new StringBuilder();
            for (CmDetalle detale : getRegistrosDetallesSeleccionadoMasivos()) {
                sb.append(detale.getId()).append(",");
            }
            if (sb.length() > 0) {
                idFacturasSeleccionadas = sb.deleteCharAt(sb.length() - 1).toString();
            }
        }

        if (GESTION_MASIVA_TOTAL == tipoGestionMasiva) {
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(ACCION_VALIDAR_FACTURA_MONO_USUARIO);
            getCmAuditoriaFacturaServicio().Accion(this);
            validarProceso = !this.getObjeto().isMultiUsuario();
            if (!validarProceso) {
                this.addError("La factura de id " + idFactura + " tiene items de varios usuarios, el proceso requiere que sean del mismo usuario.");
            } else {
                super.setAccion(Url.ACCION_VER);
                super.setDoAccion(ACCION_LISTAR_TODOS_DETALLES_POR_FACTUA);
                getCmAuditoriaFacturaServicio().Accion(this);
            }
        }

        if (validarProceso) {
            inicializarTablaDetallesAgetionar(idFactura, idFacturasSeleccionadas);
        }

        if (!getRegistrosDetallesSeleccionadoMasivos().isEmpty()) {
            String documentoUnico = "";
            boolean esValidoProceso = true;
            for (CmDetalle detalleSeleccionado : getRegistrosDetallesSeleccionadoMasivos()) {

                if (GESTION_MASIVA_TOTAL != tipoGestionMasiva) {
                    if (documentoUnico.equals("")) {
                        documentoUnico = detalleSeleccionado.getDocumento();
                    }
                    if (!detalleSeleccionado.getDocumento().equals(documentoUnico)) {
                        addError("Los items deben ser del mismo afiliado : " + documentoUnico + ", documento diferente: " + detalleSeleccionado.getDocumento());
                        esValidoProceso = false;
                        break;
                    }
                }

                if (detalleSeleccionado.getCantidadMotivosAsociadas() > 0
                        || detalleSeleccionado.getCantidadConceptosContablesAsociados() > 0) {
                    addError("Los items para auditar masivamente no deben tener  motivos o conceptos contables asociados: el detalle de id " + detalleSeleccionado.getId() + ", incumple esta regla.");
                    esValidoProceso = false;
                    break;
                }
            }
            if (esValidoProceso) {
                limpiarRegistrosTemporales();
                setEsGestionIndividual(false);
                PrimeFaces.current().resetInputs("frmGestionarItem");
                PrimeFaces.current().ajax().update("frmGestionarItem");
                PrimeFaces.current().executeScript("PF('dialogoGestionarItem').show()");
            } else {
                generarMensajes();
            }
        } else {
            if (!isError()) {
                this.addError("Para realizar la operación debe seleccionar items o servicios");
                PrimeFaces.current().resetInputs("frmGestionar");
                generarMensajes();
            } else {
                generarMensajes();
            }
        }

    }

    public void verGestionarMotivosConceptosDiagnosticosMultiUsuario(int idFactura, int tipoGestionMasiva) {
        this.getObjeto().setId(idFactura);
        String idFacturasSeleccionadas = "";
        boolean validarProceso = true;
        this.setTipoGestionMasiva(tipoGestionMasiva);

        if (GESTION_MASIVA_PARCIAL == tipoGestionMasiva) {
            setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values()));
            StringBuilder sb = new StringBuilder();
            getRegistrosDetallesSeleccionadoMasivos().forEach(detale -> {
                sb.append(detale.getId()).append(",");
            });
            if (sb.length() > 0) {
                idFacturasSeleccionadas = sb.deleteCharAt(sb.length() - 1).toString();
            }
        }

        inicializarTablaDetallesAgetionar(idFactura, idFacturasSeleccionadas);

        if (validarProceso) {
            setEsGestionIndividual(false);
            limpiarRegistrosTemporales();
            PrimeFaces.current().resetInputs("frmGestionarItemMultiUsuario");
            PrimeFaces.current().ajax().update("frmGestionarItemMultiUsuario");
            PrimeFaces.current().executeScript("PF('dialogoGestionarItemMultiUsuario').show()");
        } else {
            generarMensajes();
        }
    }

    public void verConfirmarCerradoAuditoria() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_OBTENER_VALOR_TEMPORALES_M2_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);

        PrimeFaces.current().resetInputs("frmConfirmarCierre");
        PrimeFaces.current().ajax().update("frmConfirmarCierre");
        PrimeFaces.current().executeScript("PF('dialogoConfirmarCierreAuditoria').show()");
    }

    public void verSeleccionOperacionMasiva(int idFactura, int tipoGestionMasiva) {
        setCmSelectorOperacionaMasiva(new CmAuditoriaSelectorOperacionMasiva());
        getRegistrosDetallesSeleccionadoMasivos().clear();

        if (GESTION_MASIVA_PARCIAL == tipoGestionMasiva) {
            setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values()));
            if (getRegistrosDetallesSeleccionadoMasivos().isEmpty()) {
                this.addError("Para realizar la operación debe seleccionar items o servicios");
            }
        }

        if (!isError()) {
            getCmSelectorOperacionaMasiva().setIdFactura(idFactura);
            getCmSelectorOperacionaMasiva().setTipoGestionMasiva(tipoGestionMasiva);
            PrimeFaces.current().resetInputs("frmVerSeleccionOperacionMasiva");
            PrimeFaces.current().ajax().update("frmVerSeleccionOperacionMasiva");
            PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').show()");
        } else {
            generarMensajes();
        }
    }

    public void verDescuentoCapita(int idServicio) {
        this.setObjetoItemServicio(new CmDetalle());
        this.getObjetoItemServicio().setId(idServicio);
        this.setEsGestionIndividual(true);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_VISTA_GETION_ITEM);
        getCmAuditoriaFacturaServicio().Accion(this);
        listarDescuentoCapita();
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_VER_DESCUENTO_CAPITA);
        procesoFinal();
        PrimeFaces.current().executeScript("PF('dialogoCapitaDescuento').show()");
    }
    
     public void verConfiguracionesGenerales(CmDetalle detalle) {
         
         setObjetoItemServicio(detalle);
         verTabServicioAuditado(detalle);
         PrimeFaces.current().resetInputs("TabAuditoria");
         PrimeFaces.current().ajax().update("TabAuditoria");
         if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoConfiguracionesGenerales').show()");
         }
         procesoFinal();
     }
     
    public void verTabVerProgramas(CmDetalle detalle) {
        verProgramasSegunDocumentoAfiliado(detalle);
        if (!isError()) {
            PrimeFaces.current().ajax().update("TabAuditoria:frmVerProgramasTab");
        }
    }
    
    private void verProgramasSegunDocumentoAfiliado(CmDetalle detalle) {
        try {
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(ACCION_CONSULTAR_PROGRAMAS_ACTIVOS_POR_AFILIADO);
            setAfiliadoCompleto(new AsegAfiliado());
            setObjetoItemServicio(detalle);
            inicializarTablaAfiliadoProgramas(detalle.getDocumento(), String.valueOf(detalle.getMaeTipoDocumentoId()));
        } catch (Exception e) {
            addError("Error consultar programas:" + e.toString());
        }
    }
    
    public void verTabNovedades(CmDetalle detalle){
        int idServicio = detalle.getId();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_DETALLE);
        this.getObjetoItemServicio().setId(idServicio);
        getCmAuditoriaFacturaServicio().Accion(this);
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_VER_ASEGURAMIENTO_AFILIADO_ID);
        getCmAuditoriaFacturaServicio().Accion(this);
        inicializarTablaAuditoriaNovedad(getObjetoRegistroNovedad().getIdAfiliado());
        PrimeFaces.current().resetInputs("TabAuditoria:frmNovedadesTab");
        PrimeFaces.current().ajax().update("TabAuditoria:frmNovedadesTab");
        procesoFinal();
    }
    
    public void verTabServicioAuditado(CmDetalle detalle) {
        int idServicio = detalle.getId();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_VISTA_GETION_ITEM);
        this.getObjetoItemServicio().setId(idServicio);
        getCmAuditoriaFacturaServicio().Accion(this);
        listarConceptosContables();
        listarDiagnosticos();
        listarMotivosGlosa();
        listarAutorizaciones();
        PrimeFaces.current().resetInputs("TabAuditoria:frmVerAuditoriaServicioTab");
        PrimeFaces.current().ajax().update("TabAuditoria:frmVerAuditoriaServicioTab");
        procesoFinal();
    }

    public void verMotivosGlosaUsados() {

        limpiarMotivosEspecificosEncontrados();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVOS_MULTI_DETALLES);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (validarExisteciaMotivosEspecificos()) {
            refrescarVentanaEliminarMotivosEspecificos();
            PrimeFaces.current().executeScript("PF('dialogoEliminarMotivo').show()");
        }
        procesoFinal();
    }

     
    public void onTabChange(TabChangeEvent event) {

        String idTab = event.getTab().getId();
        if ("Tab1_Programas".equals(idTab)) {
            verTabVerProgramas(getObjetoItemServicio());
        }
        if ("Tab2_Novedades".equals(idTab)) {
            verTabNovedades(getObjetoItemServicio());
        }
        if ("Tab3_Configuraciones".equals(idTab)) {
            verTabServicioAuditado(getObjetoItemServicio());
        }
    }


    public void verDescuentoCapitaMasiva(int idFactura, int tipoGestionMasiva) {
        String idFacturasSeleccionadas = "";
        getCmSelectorOperacionaMasiva().setIdFactura(idFactura);
        getCmSelectorOperacionaMasiva().setTipoGestionMasiva(tipoGestionMasiva);
        this.setEsGestionIndividual(false);

        if (GESTION_MASIVA_PARCIAL == tipoGestionMasiva) {
            setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values()));
            StringBuilder sb = new StringBuilder();
            for (CmDetalle detale : getRegistrosDetallesSeleccionadoMasivos()) {
                sb.append(detale.getId()).append(",");
            }
            if (sb.length() > 0) {
                idFacturasSeleccionadas = sb.deleteCharAt(sb.length() - 1).toString();
            }
        }

        if (GESTION_MASIVA_TOTAL == tipoGestionMasiva) {
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(ACCION_LISTAR_TODOS_DETALLES_POR_FACTUA);
            getCmAuditoriaFacturaServicio().Accion(this);
        }

        inicializarTablaDetallesAgetionar(idFactura, idFacturasSeleccionadas);

        PrimeFaces.current().resetInputs("frmListaAuditoriaCapitaDescuento");
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento");
        PrimeFaces.current().executeScript("PF('dialogoCapitaDescuento').show()");
    }

    public void verDevolucionFactura(int idFactura) {
        this.getObjeto().setId(idFactura);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (validarFacturaEsAuditable(this.getObjeto())) {
            CmAuditoriaDevolucion devolucion = new CmAuditoriaDevolucion();
            setCmAuditoriaDevolucion(devolucion);

            asignarMotivosDevolucionSegunVersionFactura();
                     
            super.setDoAccion(ACCION_VER_DEVOLUCION_FACTURA);
            procesoFinal();
            PrimeFaces.current().executeScript("PF('dialogoDevolucionFactura').show()");
        } else {
            this.addError("La factura de id " + this.getObjeto().getId() + " ya se encuentra en proceso de auditoría "
                    + "en estado : " + this.getObjeto().getEstadoFacturaStr());
            PrimeFaces.current().resetInputs("frmConsultaAuditoriaEvento");
            generarMensajes();
        }
    }

    public void verCrearMotivo() {
        CmAuditoriaMotivoGlosa motivoGlosa = new CmAuditoriaMotivoGlosa();
        setCmAuditoriaMotivoGlosa(motivoGlosa);
        limpiarSelectoresMotivoGlosa();
        asignarMotivosGlosaSegunVersionFactura();

        boolean esValorUnificado = true;
        BigDecimal valorUnificadoDetalle = new BigDecimal(BigInteger.ZERO);
        for (CmDetalle detalleSeleccionado : getRegistrosDetallesSeleccionadoMasivos()) {
            if (valorUnificadoDetalle.compareTo(new BigDecimal(BigInteger.ZERO)) == 0) {
                valorUnificadoDetalle = detalleSeleccionado.getValorFacturado();
            }
            if (valorUnificadoDetalle.compareTo(detalleSeleccionado.getValorFacturado()) != 0) {
                esValorUnificado = false;
                break;
            }
        }

        if (getRegistrosDetallesSeleccionadoMasivos().isEmpty()) {
            valorUnificadoDetalle = this.getObjetoItemServicio().getValorFacturado();
        }

        motivoGlosa.setEsValorUnificado(esValorUnificado);
        if (esValorUnificado) {
            this.getObjetoItemServicio().setValorFacturado(valorUnificadoDetalle);
        }

        PrimeFaces.current().resetInputs("frmAgregarMotivo");
        PrimeFaces.current().ajax().update("frmAgregarMotivo");
        PrimeFaces.current().executeScript("PF('dialogoAgregarMotivo').show()");
    }
   
    public void verEditarMotivo(CmAuditoriaMotivoGlosa motivoGlosa){
        int idMotivoGlosa = motivoGlosa.getId();
        int idMotivoPrincipal = motivoGlosa.getMaestroMotivo().getId();
        Integer idMotivoEspecifico = motivoGlosa.getMaestroMotivoEspecifico().getId();
        Integer idMotivoAplicacion = motivoGlosa.getMaestroMotivoAplicacion().getId();
        
        setCmAuditoriaMotivoGlosa( new CmAuditoriaMotivoGlosa());
        getCmAuditoriaMotivoGlosa().setId(idMotivoGlosa);
        getCmAuditoriaMotivoGlosa().setMaeMotivoId(idMotivoPrincipal);
        getCmAuditoriaMotivoGlosa().getMaestroMotivo().setId(idMotivoPrincipal);
        getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().setId(idMotivoEspecifico);
        getCmAuditoriaMotivoGlosa().getMaestroMotivoAplicacion().setId(idMotivoAplicacion);
                
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVO_GLOSA);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!this.isError()) {
            asignarMotivoAplicacionPorDefecto();
            PrimeFaces.current().resetInputs("frmEditarMotivo");
            PrimeFaces.current().ajax().update("frmEditarMotivo");
            PrimeFaces.current().executeScript("PF('dialogoEditarMotivo').show()");          
        } else {
            generarMensajes();
        }
    }

    public void verListarAnexo4() {
        inicializarTablaAnexo4();    
        DataTable tablaListarAnexo4 = (DataTable) FacesContext.getCurrentInstance().
                                      getViewRoot().findComponent("frmListarAnexo4:tablaListarAnexo4");
        tablaListarAnexo4.reset();
        PrimeFaces.current().resetInputs("frmListarAnexo4");
        PrimeFaces.current().ajax().update("frmListarAnexo4");
        PrimeFaces.current().executeScript("PF('dialogoListarAnexo4').show()");
    }

    public void verListarAnexo4Items(int idAnexo4) {
        getAuAnexo4().setId(idAnexo4);
        inicializarTablaAnexo4Items();
        DataTable tablaListarAnexo4 = (DataTable) FacesContext.getCurrentInstance().
                                      getViewRoot().findComponent("frmListarAnexo4Items:tablaListarAnexo4Items");
        tablaListarAnexo4.reset();
        PrimeFaces.current().resetInputs("frmListarAnexo4Items");
        PrimeFaces.current().ajax().update("frmListarAnexo4Items");
        PrimeFaces.current().executeScript("PF('dialogoListarAnexo4Items').show()");
    }

    public void verListarCntContratos() {
        inicializarTablaCntContratos();
        limpiarFiltrosContratos();
        PrimeFaces.current().executeScript("PF('dialogoListarCntContratos').show()");
        PrimeFaces.current().executeScript("PF('tablaWidgetListarCntContratos').unselectAllRows();");
    }
    
    public void verCrearConceptoContable() {
        setCmAuditoriaConceptoContable(new CmAuditoriaConceptoContable());
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_CONSULTAR_UBICACION_USUARIO);
        getCmAuditoriaFacturaServicio().Accion(this);
        
        asignarListaConceptosSegunRegimen();
        
        PrimeFaces.current().resetInputs("frmAgregarConcepto");
        PrimeFaces.current().ajax().update("frmAgregarConcepto");
        PrimeFaces.current().executeScript("PF('dialogoAgregarConcepto').show()");

        if (this.isError()) {
            generarMensajes();
        }
    }
    
       public void verSoportes(int id) {
        getObjeto().setCmFeRipsCarga(new CmFeRipsCarga(id));
        inicializarTablaSoportes(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_SOPORTES);;
        limpiarSelectorSoportes();
        PrimeFaces.current().ajax().update("frmVerSoportes:panelSoportes");
        PrimeFaces.current().executeScript("PF('dialogoSoportes').show()");
        procesoFinal();
    }
       
       
    public void verPdf(CmFeSoporte soporte) {
        File file = new File(soporte.getArchivoRuta() + soporte.getArchivo());
        byte[] fileContent = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileContent);

            String titulo = soporte.getArchivoNombre();
            String base64Pdf = Base64.getEncoder().encodeToString(fileContent);

            String script = "var pdfBlob = b64toBlob('" + base64Pdf + "', 'application/pdf');"
                    + "var blobUrl = URL.createObjectURL(pdfBlob);"
                    + "var newWindow = window.open('', '_blank');"
                    + "newWindow.document.write(`"
                    + "<html><head><title>" + titulo + "</title></head>"
                    + "<body style='margin:0'>"
                    + "<iframe width='100%' height='100%' style='border:none' src='${blobUrl}'></iframe>"
                    + "</body></html>`);"
                    + "newWindow.document.close();"
                    + "function b64toBlob(b64Data, contentType) {"
                    + "  contentType = contentType || '';"
                    + "  var sliceSize = 512;"
                    + "  var byteCharacters = atob(b64Data);"
                    + "  var byteArrays = [];"
                    + "  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {"
                    + "    var slice = byteCharacters.slice(offset, offset + sliceSize);"
                    + "    var byteNumbers = new Array(slice.length);"
                    + "    for (var i = 0; i < slice.length; i++) {"
                    + "      byteNumbers[i] = slice.charCodeAt(i);"
                    + "    }"
                    + "    var byteArray = new Uint8Array(byteNumbers);"
                    + "    byteArrays.push(byteArray);"
                    + "  }"
                    + "  return new Blob(byteArrays, {type: contentType});"
                    + "}";

            PrimeFaces.current().executeScript(script);


        } catch (IOException ex) {
            addError("Ocurrio un error verPdf  :" + ex.toString());
        }
        procesoFinal();
    }

    private void asignarListaConceptosSegunRegimen() {
        List<Maestro> listaConceptos ;
        if(CmFactura.CODIGO_REGIMEN_SUBSIDIADO.equals(this.getObjeto().getMaeRegimenCodigo())){
            listaConceptos = this.getListaMaeConceptosSubsidiados().isEmpty() ? 
                                           this.getListaMaeConceptosTotales() : this.getListaMaeConceptosSubsidiados();            
            this.setListaMaeConceptos(listaConceptos);
        }
        if(CmFactura.CODIGO_REGIMEN_CONTRIBUTIVO.equals(this.getObjeto().getMaeRegimenCodigo())){
            listaConceptos = this.getListaMaeConceptosContributivos().isEmpty() ? 
                                           this.getListaMaeConceptosTotales() : this.getListaMaeConceptosContributivos(); 
            this.setListaMaeConceptos(listaConceptos);
        }
    }
    
    private void asignarMotivosDevolucionSegunVersionFactura() {
        int tipoDevolucionPadre = this.getObjeto().getVersion() ? MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_2284
                : MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_3047;
        this.setListaMaeMotivoDevolucion(getHashMaeMotivoDevolucionPadre().get(tipoDevolucionPadre));
        
        if( MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_3047 == tipoDevolucionPadre ){
            List<Maestro> listaMotivosPadre = getHashMaeMotivoDevolucionPadre().get(tipoDevolucionPadre);
            if (listaMotivosPadre.size() == 1) {
                this.getCmAuditoriaDevolucion().setMaestroMotivoDevolucion(listaMotivosPadre.get(0));
                 mostrarMotivosEspecificosDevolucion();
            }
        }    
    }
    
    private void asignarMotivosGlosaSegunVersionFactura() {
        int tipoMotivoGlosaPadre = this.getObjeto().getVersion() ? MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_2284
                : MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_3047;
        this.setListaMaeMotivos(getHashMaeMotivoGlosaPadre().get(tipoMotivoGlosaPadre));
    }
    
    private int asignarIndiceMotivoGlosa() {
        int idPosInsertar = 1;
        for (CmAuditoriaMotivoGlosa motivo : getRegistrosAuditoriaMotivoGlosa()) {
            if (motivo.getPosInsertar() != null && idPosInsertar <= motivo.getPosInsertar()) {
                idPosInsertar = motivo.getPosInsertar() + 1;
            }
        }
        return idPosInsertar;
    }

    public void verServicioAuditado(int idServicio) {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_VISTA_GETION_ITEM);
        this.getObjetoItemServicio().setId(idServicio);
        getCmAuditoriaFacturaServicio().Accion(this);
        listarConceptosContables();
        listarDiagnosticos();
        listarMotivosGlosa();
        listarAutorizaciones();
        listarDescuentoCapita();
        listarAdjuntosCmDetalles();
        super.setDoAccion(ACCION_VER_VISTA_GETION_ITEM);
        PrimeFaces.current().resetInputs("frmVerAuditoriaServicio");
        PrimeFaces.current().ajax().update("frmVerAuditoriaServicio");
        PrimeFaces.current().executeScript("PF('dialogoVerAuditoriaServicio').show()");
        procesoFinal();
    }

    public void verHistoriaNovedadesUsuario(AsegAfiliadoHistorico historico) {
        boolean resultado = false;
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();

        String datosHisorico = historico.getTostringAfiliado();

        try {
            // convertir la información del historial en un objeto de afiliado
            afiliadoCompleto = gson.fromJson(datosHisorico, AsegAfiliado.class);
            afiliadoCompleto.setFechaUltimaNovedad(historico.getFechaHoraCrea());
            resultado = true;
        } catch (JsonSyntaxException e) {
            this.addError("No se pudo obtener la información del histórico del afiliado seleccionado.");
        }

        if (resultado) {
            PrimeFaces.current().ajax().update("frmVerHistorico");
            PrimeFaces.current().executeScript("PF('dialogoVerHistorico').show()");
        }
    }
    
    public void verProgramasPorAfiliado(CmDetalle detalle){
        verProgramasSegunDocumentoAfiliado(detalle);
        if (!isError()) {
            PrimeFaces.current().ajax().update("frmVerProgramas");
            PrimeFaces.current().executeScript("PF('dialogoVerProgramas').show()");
        }
        procesoFinal();
    }
    
    public void verProgramaEspecificoPorAfiliado(PeAfiliadosPrograma programaAfiliadoIn) {       
        if (programaAfiliadoIn !=null) {
            setPeAfiliadosPrograma(programaAfiliadoIn);
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO);
            getCmAuditoriaFacturaServicio().Accion(this);
            if (!isError()) {
                PrimeFaces.current().resetInputs("frmVerProgramaEspecifico");
                PrimeFaces.current().ajax().update("frmVerProgramaEspecifico");
                PrimeFaces.current().executeScript("PF('dialogoVerProgramaEspecifico').show()");
            }
            procesoFinal();
        }
    }
    
    public void verContratoSedes(int id) {
        setContratoConSedes(new CntContrato());
        getContratoConSedes().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_CONTRATO_SEDES);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!isError()) {
            PrimeFaces.current().resetInputs("frmVerContratosSedes");
            PrimeFaces.current().ajax().update("frmVerContratosSedes");
            PrimeFaces.current().executeScript("PF('dialogoVerContratoSedes').show()");
        }
        procesoFinal();
    }
    
     public void verNota(CmFactura factura) {
        this.setObjeto(factura);
        PrimeFaces.current().ajax().update("frmVerNota");
        PrimeFaces.current().executeScript("PF('dialogoVerNota').show()");
    }

    public SelDiagnosticosBean getSelDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case ACCION_VER_AUDITORIA_FACTURA:
                            crearLog("Ver Auditaria CM", getObjeto().toString());
                            break;
                        case ACCION_VER_GESTION_FACTURA:
                            crearLog("Ver Gestionar Factura CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_VER_VISTA_GETION_ITEM:
                            crearLog("Ver Auditoria Servicio CM", this.getObjetoItemServicio().toString());
                            break;
                        case ACCION_VER_DESCUENTO_CAPITA:
                            crearLog("Ver Descuento Capita", this.getObjetoItemServicio().toString());
                            break;
                        case ACCION_CONSULTAR_PROGRAMAS_ACTIVOS_POR_AFILIADO:
                            crearLog("Ver Programas Afiliado", this.getObjetoItemServicio().toString());
                            break;
                        case ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO:
                            PePrograma pePrograma = Optional.ofNullable(this.getPeAfiliadosPrograma().getPePrograma()).
                                    orElse(new PePrograma());
                        crearLog("Ver Programa Afiliado",  String.valueOf("PePrograma id="+pePrograma.getId()+", codigo= "+pePrograma.getCodigoPrograma()+", nombre programa = "+pePrograma.getDescripcionPrograma()));
                            break;
                        case ACCION_VER_DEVOLUCION_FACTURA:
                            PrimeFaces.current().resetInputs("frmDevolucionFactura");
                            PrimeFaces.current().ajax().update("frmDevolucionFactura");
                            crearLog("Ver Devolucion Factura", this.getObjeto().toString());
                            break;                           
                        case ACCION_VER_OBJECIONES_USUARIO:
                            crearLog("Ver Objeciones Hospitalizacion", this.getObjeto().toString());
                            break;
                        case ACCION_VER_CONTRATO_SEDES:
                            crearLog("Ver Contrato Sedes", this.getObjeto().toString());
                            break;
                        case DO_ACCION_VER_SOPORTES:
                            crearLog("Ver Soportes", this.getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crearLog("Crear Reporte CM", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmConsultaInformes");
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Listar Auditoria CM", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                    break;
                case Url.ACCION_ADICIONAL_1:
                    crearLog("Gestionar Auditoria", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR_DEVOLUCION_FACTURA:
                            crearLog("Guardar Devolucion Factura", getCmAuditoriaDevolucion().toString());
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    crearLog("Ver Novedades Por Servicio", getObjetoItemServicio().toString());
                    PrimeFaces.current().ajax().update("frmNovedades");
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR_GESTIONAR_ITEM:
                            crearLog("Guardar Gestionar item", getObjetoItemServicio().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_GUARDAR_GESTIONAR_MASIVO:
                            crearLog("Guardar Gestion Masiva", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_GUARDAR_ADJUNTOS:
                            crearLog("Guardar Adjuntos", getObjeto().toString());
                            listarAdjuntos();
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_GUARDAR_ESTADO_AUDITORIA:
                            crearLog("Guardar Estado Auditoria", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                        case ACCION_BORRAR_CONCEPTOS_POR_FACTURA:
                            procesoFinalLevantamientosConceptos();
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_BORRAR_MOTIVOS_POR_FACTURA:
                            procesoFinalLevantamientosMotivos();
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_BORRAR_DIAGNOSTICOS_POR_FACTURA:
                            crearLog("Borrar Diagnosticos CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_BORRAR_AUTORIZACIONES_POR_FACTURA:
                            crearLog("Borrar Autorizacion CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_BORRAR_DESCUENTO_CAPITA:
                            crearLog("Borrar Capita CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                        case ACCION_GUARDAR_EDICION_MOTIVO:
                            crearLog("Editar Motivo CM", getCmAuditoriaMotivoGlosa().toString());
                            listarMotivosGlosa();
                            PrimeFaces.current().ajax().update("frmGestionarItem:tablaMotivos");
                            break;
                        case ACCION_GUARDAR_MARCADO_GLOSA_IPS:
                            crearLog("Marcar Glosa Ips CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                         case ACCION_GUARDAR_DESMARCADO_GLOSA_IPS:
                            crearLog("Desmarcar Glosa Ips CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                          case ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO:
                            crearLog("Marcar Copago No Efectivo CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR_CAPITA_DESCUENTO:
                            procesoFinalDescuentoCapita();
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                        case ACCION_GUARDAR_CAPITA_DESCUENTO_MASIVO:
                            procesoFinalDescuentoCapitaMasivo();
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                        case ACCION_VER_DESCUENTO_CAPITA:
                            crearLog("Ver Descuento Capita ", getObjetoItemServicio().toString());
                            PrimeFaces.current().resetInputs("frmListaAuditoriaCapitaDescuento");
                            PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_9:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR_REINTENTO_FACTURA:
                            crearLog("Guardar Reintento CM", this.getObjetoReintento().toString());
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                        case ACCION_LISTAR_SINCRONIZACIONES_FACTURA:
                            crearLog("Ver Reintentos CM ", this.getObjetoReintento().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR:
                            crearLog("Guardar Revertir Auditoria ", this.getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }
  
    private void procesoFinalDescuentoCapita() {
        StringBuilder builderTotal = new StringBuilder();
        try {
            
            CmDetalle detalle = this.getObjetoItemServicio();

            if (detalle.getCmFacturas() != null) {
                builderTotal.append("Número factura:");
                builderTotal.append(detalle.getCmFacturas().getNumeroFacturado());
                builderTotal.append(",");
            }

            for (CmAuditoriaCapitaDescuento capitaDescuento : this.getRegistrosAuditoriaCapitaDescuento()) {
                capitaDescuento.setCmDetalle(new CmDetalle(detalle.getId()));
                builderTotal.append(capitaDescuento.toString());
                builderTotal.append(",");
            }
            crearLog("Guardar Descuento Capita", StringUtils.abbreviate(builderTotal.toString(), MAX_OBSERVACION_LOGS));
        } catch (Exception e) {
        }
    }
    
    private void procesoFinalDescuentoCapitaMasivo() {
        StringBuilder builderTotal = new StringBuilder();
        StringBuilder builderInsercion = new StringBuilder();
        try {
            for (CmDetalle detalle : this.getRegistrosDetallesSeleccionadoMasivos()) {
                builderInsercion.append(detalle.getId());
                builderInsercion.append(",");
            }
            if (builderInsercion.length() > 0) {
                builderTotal.append(" Id detalles : ");
                builderTotal.append(builderInsercion.toString());
            }

            for (CmAuditoriaCapitaDescuento capitaDescuento : this.getRegistrosAuditoriaCapitaDescuento()) {
                builderTotal.append(capitaDescuento.toString());
            }
            crearLog("Guardar Desc Capita Masivo", StringUtils.abbreviate(builderTotal.toString(), MAX_OBSERVACION_LOGS));
        } catch (Exception e) {
        }
    }
    
    private void procesoFinalGestionItem() {

        StringBuilder builderInsercion = new StringBuilder();
        StringBuilder builderEliminacion = new StringBuilder();
        StringBuilder builderTotal = new StringBuilder();

        try {
            for (Map.Entry<Integer, String> entry : getHashInsumoAuditoriaInsertados().entrySet()) {
                builderInsercion.append(entry.getValue());
                builderInsercion.append(" , ");
            }

            if (builderInsercion.length() > 0) {
                builderTotal.append(" Inserciones : ");
                builderTotal.append(builderInsercion.toString());
            }

            for (Map.Entry<Integer, String> entry : getHashInsumoAuditoriaEliminados().entrySet()) {
                builderEliminacion.append(entry.getValue());
                builderEliminacion.append(" , ");
            }

            if (builderEliminacion.length() > 0) {
                builderTotal.append(" -- Eliminaciones : ");
                builderTotal.append(builderEliminacion.toString());
            }

            limpiarHashInsumoAuditoriaEliminados();
            limpiarHashInsumoAuditoriaInsertados();

            if (builderTotal.length() > 0) {
                crearLog("Guardar Gestionar Item ", StringUtils.abbreviate(builderTotal.toString(), MAX_OBSERVACION_LOGS));
            }

        } catch (Exception e) {
        }
    }
    
    private void procesoFinalLevantamientosMotivos() {
        StringBuilder builderTotal = new StringBuilder();
        StringBuilder builderInsercion = new StringBuilder();
        try {
            for (CmDetalle detalle : this.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaMotivoGlosa motivo : detalle.getListaCmAuditoriaMotivosGlosa()) {
                    builderInsercion.append(motivo.toString());
                    builderInsercion.append(",");
                }
            }

            if (builderInsercion.length() > 0) {
                builderTotal.append(" Motivos borrados : ");
                builderTotal.append(builderInsercion.toString());
            }

            builderTotal.append(" Factura: ");
            builderTotal.append(getObjeto().toString());
            crearLog("Borrado Masivo Motivos", StringUtils.abbreviate(builderTotal.toString(), MAX_OBSERVACION_LOGS));
        } catch (Exception e) {
        }
    }
    
     private void procesoFinalLevantamientosConceptos() {
        StringBuilder builderTotal = new StringBuilder();
        StringBuilder builderInsercion = new StringBuilder();
        try {
            for (CmDetalle detalle : this.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaConceptoContable concepto : detalle.getListaCmAuditoriaConceptoContable()) {
                    builderInsercion.append(concepto.toString());
                    builderInsercion.append(",");
                }
            }

            if (builderInsercion.length() > 0) {
                builderTotal.append(" Conceptos borrados : ");
                builderTotal.append(builderInsercion.toString());
            }

            builderTotal.append(" Factura: ");
            builderTotal.append(getObjeto().toString());
            crearLog("Borrado Masivo Conceptos", StringUtils.abbreviate(builderTotal.toString(), MAX_OBSERVACION_LOGS));
        } catch (Exception e) {
        }
    }
    
    public boolean validarExisenciaNitIps() {
        boolean isValido = true;
        if (this.getObjeto().getNit().equals("") && this.getObjeto().getIps().equals("")) {
            this.addError(" Para realizar la búsqueda debe incluir el Nit o Ips. ");
            isValido = false;
        }
        return isValido;
    }

    public void inicializarTablaDetallesAuditoria() {
        this.setParamConsultaServiciosAuditoria(new ParamConsulta());
        lazyCmAuditoriaDetalles = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaServiciosAuditoria().getCantidadRegistros();
            }

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaServiciosAuditoria().setPrimerRegistro(primerRegistro);
                getParamConsultaServiciosAuditoria().setRegistrosPagina(registrosPagina);
                getParamConsultaServiciosAuditoria().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaServiciosAuditoria().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDetalles();
                if (getRegistrosAuditoriaDetalles() != null) {
                    for (CmDetalle detalle : getRegistrosAuditoriaDetalles()) {
                        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
                            getRegistrosDetallesSeleccionadoMasivos().add(detalle);
                        }
                    }
                }
                lista = getRegistrosAuditoriaDetalles();
                setRowCount(getParamConsultaServiciosAuditoria().getCantidadRegistros());
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

    public void inicializarTablaCntContratos() {
        this.setParamConsultaCntContratos(new ParamConsulta());
        lazyCntContratos = new LazyDataModel<CntContrato>() {
            private List<CntContrato> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaCntContratos().getCantidadRegistros();
            }

            @Override
            public List<CntContrato> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaCntContratos().setPrimerRegistro(primerRegistro);
                getParamConsultaCntContratos().setRegistrosPagina(registrosPagina);
                getParamConsultaCntContratos().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaCntContratos().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCntContratos();
                lista = getRegistrosCntContratos();
                setRowCount(getParamConsultaCntContratos().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CntContrato contrato) {
                return contrato.getId().toString();
            }

            @Override
            public CntContrato getRowData(String contratoId) {
                Integer id = Integer.valueOf(contratoId);
                for (CntContrato contrato : lista) {
                    if (id.equals(contrato.getId())) {
                        return contrato;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaAuditoriaNovedad(int idAfiliadoAseguramiento) {
        this.setParamConsultaAuditoriaNovedad(new ParamConsulta());
        this.getParamConsultaAuditoriaNovedad().setParametroConsulta1(idAfiliadoAseguramiento);
        lazyAseguramientoNovedadHistorial = new LazyDataModel<AsegRegistroNovedad>() {
            private List<AsegRegistroNovedad> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaAuditoriaNovedad().getCantidadRegistros();
            }

            @Override
            public List<AsegRegistroNovedad> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaAuditoriaNovedad().setPrimerRegistro(primerRegistro);
                getParamConsultaAuditoriaNovedad().setRegistrosPagina(registrosPagina);
                getParamConsultaAuditoriaNovedad().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaAuditoriaNovedad().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarNovedades();
                lista = getRegistrosAseguramientoNovedad();
                setRowCount(getParamConsultaAuditoriaNovedad().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AsegRegistroNovedad novedad) {
                return novedad.getId().toString();
            }

            @Override
            public AsegRegistroNovedad getRowData(String idNovedad) {
                Integer id = Integer.valueOf(idNovedad);
                for (AsegRegistroNovedad novedad : lista) {
                    if (id.equals(novedad.getId())) {
                        return novedad;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaGlosaFacturaDetalles(int _idGlosaRespuesta) {
        this.setParamConsultaGlosaFacturaDetalles(new ParamConsulta());
        this.getParamConsultaGlosaFacturaDetalles().setParametroConsulta1(_idGlosaRespuesta);
        this.getParamConsultaGlosaFacturaDetalles().setEmpresaId(super.getConexion().getEmpresa().getId());
        lazyGlosaFacturaDetalle = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaGlosaFacturaDetalles().getCantidadRegistros();
            }
			

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaGlosaFacturaDetalles().setPrimerRegistro(primerRegistro);
                getParamConsultaGlosaFacturaDetalles().setRegistrosPagina(registrosPagina);
                getParamConsultaGlosaFacturaDetalles().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaGlosaFacturaDetalles().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGlosaRespuestaDetalle();
                lista = getRegistrosGlosaFacturaDetalle();
                setRowCount(getParamConsultaGlosaFacturaDetalles().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle cmGlosaDetalle) {
                return cmGlosaDetalle.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmDetalle cmGlosaDetalle : lista) {
                    if (id.equals(cmGlosaDetalle.getId())) {
                        return cmGlosaDetalle;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaDetallesAgetionar(int idFactura, String idFacturasSeleccionadas) {
        this.setParamConsultaDetallesAgestionar(new ParamConsulta());
        this.getParamConsultaDetallesAgestionar().setParametroConsulta1(idFactura);
        if (!"".equals(idFacturasSeleccionadas)) {
            this.getParamConsultaDetallesAgestionar().setParametroConsulta4(idFacturasSeleccionadas);
        }
        this.getParamConsultaDetallesAgestionar().setEmpresaId(super.getConexion().getEmpresa().getId());
        lazyDetalleAgestionar = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaDetallesAgestionar().getCantidadRegistros();
            }
			
                
            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaDetallesAgestionar().setPrimerRegistro(primerRegistro);
                getParamConsultaDetallesAgestionar().setRegistrosPagina(registrosPagina);
                getParamConsultaDetallesAgestionar().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaDetallesAgestionar().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDetalleAgestionar();
                lista = getRegistrosDetallesAgestionar();
                setRowCount(getParamConsultaDetallesAgestionar().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle cmGlosaDetalle) {
                return cmGlosaDetalle.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmDetalle cmGlosaDetalle : lista) {
                    if (id.equals(cmGlosaDetalle.getId())) {
                        return cmGlosaDetalle;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaAnexo4() {
        this.setParamConsultaAnexo4(new ParamConsulta());
        lazyAnexo4 = new LazyDataModel<AuAnexo4>() {
            private List<AuAnexo4> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaAnexo4().getCantidadRegistros();
            }

            @Override
            public List<AuAnexo4> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaAnexo4().setPrimerRegistro(primerRegistro);
                getParamConsultaAnexo4().setRegistrosPagina(registrosPagina);
                getParamConsultaAnexo4().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaAnexo4().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaAnexos4();
                lista = getRegistrosAnexo4();
                setRowCount(getParamConsultaAnexo4().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuAnexo4 anexo) {
                return anexo.getId().toString();
            }

            @Override
            public AuAnexo4 getRowData(String idAnexo4) {
                Integer id = Integer.valueOf(idAnexo4);
                for (AuAnexo4 anexo : lista) {
                    if (id.equals(anexo.getId())) {
                        return anexo;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaAnexo4Items() {
        this.setParamConsultaAnexo4Item(new ParamConsulta());
        lazyAnexo4Items = new LazyDataModel<AuAnexo4Item>() {
            private List<AuAnexo4Item> lista;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaAnexo4Item().getCantidadRegistros();
            }

            @Override
            public List<AuAnexo4Item> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaAnexo4Item().setPrimerRegistro(primerRegistro);
                getParamConsultaAnexo4Item().setRegistrosPagina(registrosPagina);
                getParamConsultaAnexo4Item().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaAnexo4Item().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarListaAnexos4Items();
                lista = getRegistrosAnexo4Item();
                setRowCount(getParamConsultaAnexo4Item().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(AuAnexo4Item anexo) {
                return anexo.getId().toString();
            }

            @Override
            public AuAnexo4Item getRowData(String idAnexo4) {
                Integer id = Integer.valueOf(idAnexo4);
                for (AuAnexo4Item anexo : lista) {
                    if (id.equals(anexo.getId())) {
                        return anexo;
                    }
                }
                return null;
            }
        };
    }
    
    public void inicializarTablaAfiliadoProgramas(String documento, String idTipo) {
     
        lazyAfiliadosPrograma = new LazyDataModel<PeAfiliadosPrograma>() {
            private List<PeAfiliadosPrograma> listAfiliadosProgramas;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaAfiliadoProgramas().getCantidadRegistros();
            }

            @Override
            public List<PeAfiliadosPrograma> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaAfiliadoProgramas().setPrimerRegistro(primerRegistro);
                getParamConsultaAfiliadoProgramas().setRegistrosPagina(registrosPagina);
                getParamConsultaAfiliadoProgramas().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaAfiliadoProgramas().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                getParamConsultaAfiliadoProgramas().getFiltros().put("asegAfiliado.maeTipoDocumento", idTipo);
                getParamConsultaAfiliadoProgramas().getFiltros().put("asegAfiliado.numeroDocumento", documento);
                refrescarAfiliadoPrograma();
                listAfiliadosProgramas = getRegistrosAfiliadosPrograma();
                setRowCount(getParamConsultaAfiliadoProgramas().getCantidadRegistros());
                return listAfiliadosProgramas;
            }

            @Override
            public String getRowKey(PeAfiliadosPrograma afiliadosPrograma) {
                return afiliadosPrograma.getId().toString();
            }

            @Override
            public PeAfiliadosPrograma getRowData(String programaId) {
                Integer id = Integer.valueOf(programaId);
                for (PeAfiliadosPrograma programa : listAfiliadosProgramas) {
                    if (id.equals(programa.getId())) {
                        return programa;
                    }
                }
                return null;
            }

        };
    }

    public void inicializarTablaSoportes(int idCarga) {

        getParamConsultaSoportes().setParametroConsulta1(idCarga);

        lazySoportes = new LazyDataModel<CmFeSoporte>() {

            private List<CmFeSoporte> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSoportes().getCantidadRegistros();
            }

            @Override
            public List<CmFeSoporte> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaSoportes, Map<String, FilterMeta> filtros) {
                getParamConsultaSoportes().setPrimerRegistro(primerRegistro);
                getParamConsultaSoportes().setRegistrosPagina(registrosPagina);
                getParamConsultaSoportes().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaSoportes));
                getParamConsultaSoportes().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCmSoportes();
                lista = getRegistrosSoportes();
                setRowCount(getParamConsultaSoportes().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmFeSoporte objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CmFeSoporte getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CmFeSoporte objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }
   
    public void borrarConceptosPorFactua() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BORRAR_CONCEPTOS_POR_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void borrarMotivosPorFactua() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BORRAR_MOTIVOS_POR_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void borrarDiagnosticosPorFactua() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BORRAR_DIAGNOSTICOS_POR_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
    }
    
     public void borrarAutorizacionesPorFactua() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BORRAR_AUTORIZACIONES_POR_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
    }
     
      public void borrarDescuentoCapitaPorFactura() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BORRAR_DESCUENTO_CAPITA);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUDITORIA_DETALLES);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarNovedades() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_LISTAR_AUDITORIA_NOVEDADES);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarListaAnexos4() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_LISTAR_ANEXOS_4);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarListaAnexos4Items() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_LISTAR_ANEXOS4_ITEMS);
        getCmAuditoriaFacturaServicio().Accion(this);
    }
    
    public void refrescarAfiliadoPrograma() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_CONSULTAR_PROGRAMAS_ACTIVOS_POR_AFILIADO);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarVentanaEliminarMotivosEspecificos() {
        PrimeFaces.current().resetInputs("frmEliminarMotivo");
        PrimeFaces.current().ajax().update("frmEliminarMotivo");
    }
     
    public void listarDiagnosticos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DIAGNOSTICOS);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void listarConceptosContables() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CONCEPTOS);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void listarMotivosGlosa() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_GLOSA);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void listarAutorizaciones() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUTORIZACIONES);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarGlosaRespuestaDetalle() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_GLOSA_FACTURA_DETALLES);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarDetalleAgestionar() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_POR_GESTIONAR);
        getCmAuditoriaFacturaServicio().Accion(this);
    }
    
    public void refrescarCmSoportes() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_SOPORTES);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void listarAdjuntos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS);
        getCmAuditoriaFacturaServicio().Accion(this);
    }
    
    public void listarAdjuntosCmDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_CMDETALLES);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void listarDescuentoCapita() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_LISTAR_AUDITORIA_DESCUENTO_CAPITA);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public void refrescarCntContratos() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_LISTAR_CNT_CONTRATOS);
        getCmAuditoriaFacturaServicio().Accion(this);
    }

    public StreamedContent generarReporteRespuestaGlosa(Integer id) {

        StreamedContent streamedContent2 = null;
        return streamedContent2;
    }

    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void agregarAutorizacion() {
        int idPosInsertar = 1;
        Integer idAnexoExistente = 0;
        boolean isValido = true;
        for (CmAuditoriaAutorizacion autorizacion : getRegistrosAuditoriaAutorizacion()) {
            if (autorizacion.getPosInsertar() != null && idPosInsertar <= autorizacion.getPosInsertar()) {
                idPosInsertar = autorizacion.getPosInsertar() + 1;
            }

            if (idAnexoExistente == 0) {
                idAnexoExistente = autorizacion.getAnexo4().getId();
            }

            if (idAnexoExistente != null && !Objects.equals(idAnexoExistente, autorizacion.getAnexo4().getId())) {
                this.addError("Hay autorizaciones diferentes por favor verifíquelas.");
                isValido = false;
                break;
            }
        }

        if (isValido) {
            Integer idAnexo = getCmAuditoriaAutorizacion().getAnexo4().getId();
            if (idAnexoExistente > 0 && !Objects.equals(idAnexo, idAnexoExistente)) {
                this.addError("No se puede agregar autorizaciones diferentes");
                isValido = false;
            }
        }
        
        if (isValido) {
            String nombre = Optional.ofNullable(getCmAuditoriaAutorizacion().getNombreServicio()).orElse("");
            String codigo = Optional.ofNullable(getCmAuditoriaAutorizacion().getCodigoServicio()).orElse("");
            if (nombre.length() <= 0 || codigo.length() <= 0) {
                this.addError("La autorización no posee nombre de servicio o código, por favor verifique.");
                isValido = false;
            }
        }
        

        if (isValido) {
            getCmAuditoriaAutorizacion().setNumeroAutorizacion(String.valueOf(getCmAuditoriaAutorizacion().getAnexo4().getId())); 
            getCmAuditoriaAutorizacion().setPosInsertar(idPosInsertar);
            getCmAuditoriaAutorizacion().setPagoAnticipado(getCmAuditoriaAutorizacion().getAnexo4().isPagoAnticipado());
            getRegistrosAuditoriaAutorizacion().add(getCmAuditoriaAutorizacion());
            PrimeFaces.current().ajax().update("frmGestionarItem:panelVerAutorizaciones");
            PrimeFaces.current().ajax().update("frmGestionarItem:tablaAutorizaciones");
            PrimeFaces.current().executeScript("PF('dialogoListarAnexo4').hide()");
            PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').hide()");

        } else {
            generarMensajes();
        }
    }

    public void agregarMotivo() {
        int idPosInsertar;

        boolean esValidaInsercion = validarPorcentajeMayorCero() ? 
                                    validarMotivosNoRepetidos() : false;

        if (esValidaInsercion) {
            idPosInsertar = asignarIndiceMotivoGlosa();
            getCmAuditoriaMotivoGlosa().setPosInsertar(idPosInsertar);
            getCmAuditoriaMotivoGlosa().castMaestroMotivo(getCmAuditoriaMotivoGlosa().getMaestroMotivo());
            getCmAuditoriaMotivoGlosa().castMaestroMotivoEspecifico(getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico());
            getCmAuditoriaMotivoGlosa().castMaestroMotivoAplicacion(getCmAuditoriaMotivoGlosa().getMaestroMotivoAplicacion());

            getRegistrosAuditoriaMotivoGlosa().add(getCmAuditoriaMotivoGlosa());
            auditoriaGuardar(getCmAuditoriaMotivoGlosa());
            PrimeFaces.current().resetInputs("frmAgregarMotivo");
            actualizarVisualizacionTablasMotivos();
            PrimeFaces.current().executeScript("PF('dialogoAgregarMotivo').hide()");
        } else {
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
            generarMensajes();
        }

    }

    public void agregarConceptoContable() {
        int idPosInsertar = 1;
        BigDecimal topePorcentaje = new BigDecimal("100");
        boolean esValidaInsercion = true;
        BigDecimal acumuladoPorcentaje = new BigDecimal("0");

        if (getCmAuditoriaConceptoContable().getPorcentaje().compareTo(topePorcentaje) == 1) {
            esValidaInsercion = false;
        }

        for (CmAuditoriaConceptoContable concepto : getRegistrosConceptoContable()) {
            if (concepto.getPosInsertar() != null && idPosInsertar <= concepto.getPosInsertar()) {
                idPosInsertar = concepto.getPosInsertar() + 1;
            }
            acumuladoPorcentaje = acumuladoPorcentaje.add(concepto.getPorcentaje());
        }

        if (acumuladoPorcentaje.add(getCmAuditoriaConceptoContable().
                getPorcentaje()).compareTo(topePorcentaje) == 1) {
            this.addError("La sumatoria de los porcentajes de los conceptos agregados no debe sobrepasar el 100%");
            esValidaInsercion = false;
        }

        if (esValidaInsercion) {
            getCmAuditoriaConceptoContable().setPosInsertar(idPosInsertar);

            Ubicacion ubicacion = getCmAuditoriaConceptoContable().getUbicacionMunicipio();
            String nuevoCodigo = ubicacion.getUbicacionPadre() != null
                    && ubicacion.getUbicacionPadre().getPrefijo() != null
                    ? ubicacion.getUbicacionPadre().getPrefijo() : "";
            nuevoCodigo += ubicacion.getPrefijo() != null ? ubicacion.getPrefijo() : "";

            getCmAuditoriaConceptoContable().
                    setCodigoMunicipio(nuevoCodigo);
            getCmAuditoriaConceptoContable().
                    setMunicipioAfiliado(getCmAuditoriaConceptoContable().getUbicacionMunicipio().getNombre());
            Maestro maestro = getCmAuditoriaConceptoContable().getMaestroCentroCosto();
            maestro = maestro.getId() == null ? new Maestro(0) : maestro;
            getCmAuditoriaConceptoContable().castCentroCostoMaestro(maestro);
            getCmAuditoriaConceptoContable().castConceptoContableMaestro(getCmAuditoriaConceptoContable().getMaestroConceptos());
            getRegistrosConceptoContable().add(getCmAuditoriaConceptoContable());
            auditoriaGuardar(getCmAuditoriaConceptoContable());
            PrimeFaces.current().resetInputs("frmAgregarConcepto");
            actualizarVisualizacionTablasConceptos();
            PrimeFaces.current().executeScript("PF('dialogoAgregarConcepto').hide()");
        } else {
            PrimeFaces.current().ajax().update("frmAgregarConcepto");
            generarMensajes();
        }

    }

    public void agregarDiagnostico() {
        int idPosInsertar = 1;
        boolean esValidaInsercion = true;
        for (CmAuditoriaDiagnostico diagnostico : getRegistrosAuditoriaDiagnostico()) {
            if (diagnostico.getMaDiagnosticoCodigo().equals(getCmAuditoriaDiagnostico().getMaDiagnosticoCodigo())) {
                esValidaInsercion = false;
                this.addError("No se puede repetir diagnóstico");
                break;
            }
            if (diagnostico.getPosInsertar() != null && idPosInsertar <= diagnostico.getPosInsertar()) {
                idPosInsertar = diagnostico.getPosInsertar() + 1;
            }
            if (diagnostico.isPrincipal() && getCmAuditoriaDiagnostico().isPrincipal()) {
                esValidaInsercion = false;
                this.addError("No se puede haber más de un diagnóstico principal");
                break;
            }
        }

        if (esValidaInsercion) {
            getCmAuditoriaDiagnostico().setPosInsertar(idPosInsertar);
            this.getRegistrosAuditoriaDiagnostico().add(getCmAuditoriaDiagnostico());
            actualizarVisualizacionTablaDiagnosticos();
            PrimeFaces.current().executeScript("PF('dialogoEditarDiagnostico').hide()");
        } else {
            PrimeFaces.current().ajax().update("frmEditarDiagnostico");
            generarMensajes();
        }
    }

    public void agregarCapitaDescuento() {
        int idPosInsertar = 1;
        for (CmAuditoriaCapitaDescuento descuento : getRegistrosAuditoriaCapitaDescuento()) {
            if (descuento.getPosInsertar() != null && idPosInsertar <= descuento.getPosInsertar()) {
                idPosInsertar = descuento.getPosInsertar() + 1;
            }
        }

        getCmAuditoriaCapitaDescuento().setPosInsertar(idPosInsertar);
        getRegistrosAuditoriaCapitaDescuento().add(getCmAuditoriaCapitaDescuento());
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento");
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento:tablaCapitaDescuento");
        PrimeFaces.current().executeScript("PF('dialogoAgregarCapitaDescuento').hide()");
        PrimeFaces.current().executeScript("PF('dialogoListarCntContratos').hide()");
    }

    public void agregarAdjunto(FileUploadEvent event) throws IOException {
        String ruta = PropApl.getInstance().get(PropApl.CM_RUTA_AUDITORIA_DESCARGA);
        try {
            UploadedFile archivoCarga = event.getFile();
            String nombre = archivoCarga.getFileName();
            
            if (validarLongitudNombreAdjunto(nombre)) {
                InputStream inputStream = archivoCarga.getInputStream();
                int idInsertar = 1;
                for (CmAuditoriaAdjunto adj : getRegistrosAuditoriaAdjuto()) {
                    if (adj.getPosInsertar() != null && adj.getPosInsertar() >= idInsertar) {
                        idInsertar = adj.getPosInsertar() + 1;
                    }
                }
                CmAuditoriaAdjunto adj = new CmAuditoriaAdjunto(CmAuditoriaAdjunto.TIPO_FACTURA, nombre, ruta, inputStream);
                auditoriaGuardar(adj);
                adj.setPosInsertar(idInsertar);
                getRegistrosAuditoriaAdjuto().add(adj);
            }
            
            PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntos");
            PrimeFaces.current().ajax().update("frmGestionar:panelAdjuntos");

        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }
    
    public void agregarAdjuntoCmDetalle(FileUploadEvent event) throws IOException {
        String ruta = PropApl.getInstance().get(PropApl.CM_RUTA_AUDITORIA_DESCARGA);
        try {
            UploadedFile archivoCarga = event.getFile();
            String nombre = archivoCarga.getFileName();

            if (validarLongitudNombreAdjunto(nombre)) {
                InputStream inputStream = archivoCarga.getInputStream();
                int idInsertar = 1;
                for (CmAuditoriaAdjunto adj : getRegistrosAuditoriaAdjutoCmDetalle()) {
                    if (adj.getPosInsertar() != null && adj.getPosInsertar() >= idInsertar) {
                        idInsertar = adj.getPosInsertar() + 1;
                    }
                }
                CmAuditoriaAdjunto adjunto = new CmAuditoriaAdjunto(CmAuditoriaAdjunto.TIPO_DETALLE, nombre, ruta, inputStream);
                auditoriaGuardar(adjunto);
                adjunto.setPosInsertar(idInsertar);
                getRegistrosAuditoriaAdjutoCmDetalle().add(adjunto);
            }
 
            PrimeFaces.current().ajax().update("frmGestionarItem:tablaAdjuntosCmDetalles");
            PrimeFaces.current().ajax().update("frmGestionarItem:panelVerAdjuntosCmDetalles");

        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    private boolean validarLongitudNombreAdjunto(String nombre) throws IOException {
        boolean esValido = true;
        int LONGITUD_VALIDA = 44;
        if (nombre.length() > LONGITUD_VALIDA) {
            addError("El archivo de nombre ("+nombre+") tiene una longitud de nombre superior a "+LONGITUD_VALIDA+" caracteres el cual no es permitido.");
            generarMensajes();
            esValido = false;
        } 
        return esValido;
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

    public void descargarSoporte(CmFeSoporte soporte) throws IOException {
        String rutaCompleta = soporte.getArchivoRuta() + soporte.getArchivo();
        FileInputStream fis = null;
        OutputStream output = null;
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + soporte.getArchivoNombre() + "\"";
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
            } else if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            } else if (ext.equalsIgnoreCase(".json")) {
                ec.setResponseContentType("application/json");
            } else  if (ext.equalsIgnoreCase(".xml")) {
                ec.setResponseContentType("application/xml");
            }else  {
                throw new Exception();
            }
            output = ec.getResponseOutputStream();
            output.write(exportContent);
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo  :" + e.toString());
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (output != null) {
                output.close();
            }
        }
        crearLog("Descargar Soporte", getObjeto().toString());
        procesoFinal();
    }
       
    public void retirarDiagnostico(int idPosAutorizacion) {
        for (int i = 0; i < getRegistrosAuditoriaDiagnostico().size(); i++) {
            CmAuditoriaDiagnostico diagnostico = getRegistrosAuditoriaDiagnostico().get(i);
            if (diagnostico.getPosInsertar() != null && diagnostico.getPosInsertar() == idPosAutorizacion) {
                if (diagnostico.getId() != null) {
                    this.getObjetoItemServicio().getListaDiagnosticosEliminar().add(diagnostico.getId());
                }
                getRegistrosAuditoriaDiagnostico().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionarItem:panelDiagnostico");
    }

    public void retirarAutorizacion(int idPosAutorizacion) {
        for (int i = 0; i < getRegistrosAuditoriaAutorizacion().size(); i++) {
            CmAuditoriaAutorizacion autorizacion = getRegistrosAuditoriaAutorizacion().get(i);
            if (autorizacion.getPosInsertar() != null && autorizacion.getPosInsertar() == idPosAutorizacion) {
                if (autorizacion.getId() != null) {
                    this.getObjetoItemServicio().getListaAutorizacionEliminar().add(autorizacion.getId());
                }
                getRegistrosAuditoriaAutorizacion().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionarItem:panelVerAutorizaciones");
        PrimeFaces.current().ajax().update("frmGestionarItem:tablaAutorizaciones");

    }

    public void retirarConceptoContable(int idPosConcepto) {
        for (int i = 0; i < getRegistrosConceptoContable().size(); i++) {
            CmAuditoriaConceptoContable concepto = getRegistrosConceptoContable().get(i);
            if (concepto.getPosInsertar() != null && concepto.getPosInsertar() == idPosConcepto) {
                if (concepto.getId() != null) {
                    this.getObjetoItemServicio().getListaConceptosEliminar().add(concepto.getId());
                }
                getRegistrosConceptoContable().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionarItem:tablaConceptos");
    }

    public void retirarMotivo(int idPosMotivo) {
        for (int i = 0; i < getRegistrosAuditoriaMotivoGlosa().size(); i++) {
            CmAuditoriaMotivoGlosa motivo = getRegistrosAuditoriaMotivoGlosa().get(i);
            if (motivo.getPosInsertar() != null && motivo.getPosInsertar() == idPosMotivo) {
                if (motivo.getId() != null) {
                    this.getObjetoItemServicio().getListaMotivosEliminar().add(motivo.getId());
                }
                getRegistrosAuditoriaMotivoGlosa().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionarItem:tablaMotivos");
    }

    public void retirarAdjunto(int idPosAutorizacion) {
        for (int i = 0; i < getRegistrosAuditoriaAdjuto().size(); i++) {
            CmAuditoriaAdjunto adjunto = getRegistrosAuditoriaAdjuto().get(i);
            if (adjunto.getPosInsertar() != null && adjunto.getPosInsertar() == idPosAutorizacion) {
                if (adjunto.getId() != null) {
                    this.getObjeto().getListaAdjuntosEliminar().add(adjunto.getId());
                }
                getRegistrosAuditoriaAdjuto().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntos");
        PrimeFaces.current().ajax().update("frmGestionar:panelAdjuntos");
    }
    
    public void retirarAdjuntoCmDetalle(int idPosAutorizacion) {
         
        List<CmAuditoriaAdjunto> adjuntosPorDetalle = this.getRegistrosAuditoriaAdjutoCmDetalle();
         
        for (int i = 0; i < adjuntosPorDetalle.size(); i++) {
            CmAuditoriaAdjunto adjunto = adjuntosPorDetalle.get(i);
            if (adjunto.getPosInsertar() != null && adjunto.getPosInsertar() == idPosAutorizacion) {
                if (adjunto.getId() != null) {
                    this.getRegistrosAdjuntosEliminar().add(adjunto.getId());
                }
                adjuntosPorDetalle.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionarItem:tablaAdjuntosCmDetalles");
        PrimeFaces.current().ajax().update("frmGestionarItem:panelVerAdjuntosCmDetalles");
    }

    public void retirarCapitaDescuento(int idPosCapitaDesc) {
        for (int i = 0; i < getRegistrosAuditoriaCapitaDescuento().size(); i++) {
            CmAuditoriaCapitaDescuento capitaDesc = getRegistrosAuditoriaCapitaDescuento().get(i);
            if (capitaDesc.getPosInsertar() != null && capitaDesc.getPosInsertar() == idPosCapitaDesc) {
                if (capitaDesc.getId() != null) {
                    this.getObjetoItemServicio().getListaCapitaDescuentoEliminar().add(capitaDesc.getId());
                }
                getRegistrosAuditoriaCapitaDescuento().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento");
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento:tablaCapitaDescuento");
    }

    public void editDiagnostico() {
        List<CmAuditoriaDiagnostico> lista = new ArrayList();
        this.getListaDiagnosticos().stream().map(diagnostico -> {
            if (diagnostico.getMaDiagnosticoCodigo().equals(this.getCmAuditoriaDiagnostico().getMaDiagnosticoCodigo())) {
            }
            return diagnostico;
        }).forEachOrdered(diagnostico -> {
            lista.add(diagnostico);
        });
        PrimeFaces.current().ajax().update("frmGestionarItem:tablaDiagnosticosCrear");
        PrimeFaces.current().executeScript("PF('dialogoEditarDiagnostico').hide()");
    }
    
    public void guardarEditarMotivo() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR_EDICION_MOTIVO);
        
        int idMotivoPrincipal = getCmAuditoriaMotivoGlosa().getMaeMotivoId();
        Maestro motivoPrincipalActualizar =  Optional.ofNullable(getHashMaeMotivos().get(idMotivoPrincipal)).orElse(new Maestro());
        
        int idMotivoEspecifico= getCmAuditoriaMotivoGlosa().getMaeMotivoEspecificoId();
        Maestro motivoEspecificoActualizar =  Optional.ofNullable(getHashMaeMotivosEspecificos().get(idMotivoEspecifico)).orElse(new Maestro());
        
        int idMotivoApliacion = getCmAuditoriaMotivoGlosa().getMaeMotivoGlosaAplicacionId();
        Maestro motivoApliacionActualizar =  Optional.ofNullable(getHashMaeMotivosAplicacion().get(idMotivoApliacion)).orElse(new Maestro());
      
        for (CmAuditoriaMotivoGlosa auditoriaMotivoGlosa : getRegistrosAuditoriaMotivoGlosa()) {
            if (auditoriaMotivoGlosa.getMaestroMotivo().getId().
                    equals(idMotivoPrincipal) && ! Objects.equals(auditoriaMotivoGlosa.getId(), getCmAuditoriaMotivoGlosa().getId())) {
                this.addError("No se puede agregar motivos con nombre de motivo ya existente iguales.");
                break;
            }
        }   
        
        getCmAuditoriaMotivoGlosa().castMaestroMotivo(motivoPrincipalActualizar);
        getCmAuditoriaMotivoGlosa().castMaestroMotivoEspecifico(motivoEspecificoActualizar);
        getCmAuditoriaMotivoGlosa().castMaestroMotivoAplicacion(motivoApliacionActualizar);
        
        if (!this.isError()) {
            getCmAuditoriaFacturaServicio().Accion(this);
        }

        if (!this.isError()) {        
            this.addMensaje("Motivo editado exitosamente.");
            PrimeFaces.current().executeScript("PF('dialogoEditarMotivo').hide();");
            procesoFinal();
        } else {
            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
            generarMensajes();
        }
    }

    public void cerrarDialogoDiagnostico() {
        MaDiagnostico maObjeto = getSelDiagnosticosBean().getObjeto();
        CmAuditoriaDiagnostico diagnostico = new CmAuditoriaDiagnostico();
        diagnostico.setMaDiagniosticosId(maObjeto.getId());
        diagnostico.setMaDiagnosticoCodigo(maObjeto.getCodigo());
        diagnostico.setMaDiagnosticoValor(maObjeto.getNombre());
        diagnostico.setMaDiagnosticoCaretoriaNombre(maObjeto.getMaeDiagnosticoCategoriaValor());
        auditoriaGuardar(diagnostico);
        this.setCmAuditoriaDiagnostico(diagnostico);
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmEditarDiagnostico");
        PrimeFaces.current().executeScript("PF('dialogoEditarDiagnostico').show()");
    }

    public void onRowSelectAutorizacion(SelectEvent event) {
        AuAnexo4 anexo = (AuAnexo4) event.getObject();
        CmAuditoriaAutorizacion autorizacion = new CmAuditoriaAutorizacion();
        this.setCmAuditoriaAutorizacion(autorizacion);
        autorizacion.setCodigoServicio(String.valueOf(anexo.getMaServicioHabilitadoCodigo()));
        autorizacion.setNombreServicio(anexo.getMaServicioHabilitadoValor());
        autorizacion.setFechaAutorizacion(anexo.getFechaAutorizacion());
        autorizacion.setNumeroAutorizacion(anexo.getNumeroAutorizacion());
        autorizacion.setAnexo4(anexo);
        if(anexo.getCntPrestadorSedeId() != null){
          CntPrestador prestador = Optional.ofNullable(anexo.getCntPrestadorSedeId().getCntPrestador()).orElse(new CntPrestador());
          if(prestador.getId() != null){
              autorizacion.setRazonSocialPrestador(prestador.getRazonSocial());
              autorizacion.setNitPrestador(prestador.getNumeroDocumento());
          }
        }      
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_VERIFICAR_EXISTENCIA_ANEXO4);
        getCmAuditoriaFacturaServicio().Accion(this);

        if (getCmRespuestaGenerica().isEstadoRespuesta()) {
            addError(getCmRespuestaGenerica().getMensaje());
            PrimeFaces.current().ajax().update("frmListarAnexo4");
            generarMensajes();    
        } else {
           PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').show()");
        }
    }

    public void onRowSelectAutorizacionAnexo4Item(SelectEvent event) {
        AuAnexo4Item anexo4Item = (AuAnexo4Item) event.getObject();
        CmAuditoriaAutorizacion autorizacion = new CmAuditoriaAutorizacion();
        this.setCmAuditoriaAutorizacion(autorizacion);
        autorizacion.setCodigoServicio(String.valueOf(anexo4Item.getMaTecnologiaCodigo()));
        autorizacion.setNombreServicio(anexo4Item.getMaTecnologiaValor());
        autorizacion.setFechaAutorizacion(anexo4Item.getAuAnexo4Id().getFechaAutorizacion());
        autorizacion.setNumeroAutorizacion(anexo4Item.getAuAnexo4Id().getNumeroAutorizacion());
        autorizacion.setCantidad(anexo4Item.getCantidadAutorizada());
        autorizacion.setValorAutorizacion(anexo4Item.getCostoServicio());
        AuAnexo4 anexo4 = new AuAnexo4();
        if (anexo4Item.getAuAnexo4Id() != null) {
            anexo4.setId(anexo4Item.getAuAnexo4Id().getId());
        }
        autorizacion.setAnexo4(anexo4);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_VERIFICAR_EXISTENCIA_ANEXO4_ITEM);
        getCmAuditoriaFacturaServicio().Accion(this);

        if (autorizacion.isAnexo4Valido()) {
            PrimeFaces.current().ajax().update("frmEditarAutorizacion");
            PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').show()");
            PrimeFaces.current().executeScript("PF('tablaWidgetListarAnexo4Items').filter()");
        } else {
            addError("El anexo de autorización esta siendo usado por favor seleccione otro");
            PrimeFaces.current().ajax().update("frmListarAnexo4");
            generarMensajes();
        }
    }

    public void onRowSelectCntContratos(SelectEvent event) {
        CntContrato contratoIn = (CntContrato) event.getObject();
        CmAuditoriaCapitaDescuento capitaDescuento = new CmAuditoriaCapitaDescuento();
        capitaDescuento.setContrato(contratoIn.getContrato());
        capitaDescuento.setMarcacion(true);
        CntContrato contrato = new CntContrato();
        CntPrestador prestador = new CntPrestador();
        if (contratoIn.getCntPrestador() != null
                && contratoIn.getCntPrestador().getId() != null) {
            contrato.setId(contratoIn.getId());
            prestador = new CntPrestador(contratoIn.getCntPrestador().getId(),
                    contratoIn.getCntPrestador().getRazonSocial(),
                    contratoIn.getCntPrestador().getNombreRepresentanteLegal());
            prestador.setNumeroDocumento(contratoIn.getCntPrestador().getNumeroDocumento());
        }

        contrato.setMaeEstadoContratoValor(contratoIn.getMaeEstadoContratoValor());
        contrato.setMaeEstadoContratoCodigo(contratoIn.getMaeEstadoContratoCodigo());
        contrato.setCntPrestador(prestador);
        capitaDescuento.setCntContrato(contrato);
        contrato.setDiasLimitePago(contratoIn.getDiasLimitePago());
        contrato.setFechaInicio(contratoIn.getFechaInicio());
        contrato.setFechaFin(contratoIn.getFechaInicio());
        setCmAuditoriaCapitaDescuento(capitaDescuento);
        PrimeFaces.current().ajax().update("frmAgregarCapitaDescuento");
        PrimeFaces.current().executeScript("PF('dialogoAgregarCapitaDescuento').show()");

    }

    public void guardarGestionItemServicio() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR_GESTIONAR_ITEM);

        if (validacionSumatoriaConceptosContables(getRegistrosConceptoContable())) {
            getObjetoItemServicio().setListaCmAuditoriaConceptoContable(getRegistrosConceptoContable());
            getObjetoItemServicio().setListaCmAuditoriaDiagnosticos(getRegistrosAuditoriaDiagnostico());
            getObjetoItemServicio().setListaCmAuditoriaMotivosGlosa(getRegistrosAuditoriaMotivoGlosa());
            getObjetoItemServicio().setListacmAuditoriaAutorizacion(getRegistrosAuditoriaAutorizacion());
            getObjetoItemServicio().setListaCmAuditoriaAdjuntos(getRegistrosAuditoriaAdjuto());
            getObjetoItemServicio().setListaCmAuditoriaAdjuntos(getRegistrosAuditoriaAdjutoCmDetalle());
            getCmAuditoriaFacturaServicio().Accion(this);
        } else {
            this.addError("Los conceptos contables deben sumar 100% para continuar el proceso");
        }

        if (!this.isError()) {
            procesoFinalGestionItem();
            PrimeFaces.current().executeScript("PF('dialogoGestionarItem').hide()");
            listarAdjuntos();
            PrimeFaces.current().ajax().update("frmGestionar");
        } else {
            PrimeFaces.current().ajax().update("frmGestionarItem");
            generarMensajes();
        }

    }

    public void guardarGestionMasivoServicio() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR_GESTIONAR_MASIVO);
        if (validacionSumatoriaConceptosContables(getRegistrosConceptoContable())) {
            getCmAuditoriaFacturaServicio().Accion(this);
        } else {
            this.addError("Los conceptos contables deben sumar 100% para continuar el proceso");
        }
        if (!this.isError()) {
            cerrarVisualizacionDialogosGestion();
        } else {
            PrimeFaces.current().ajax().update("frmGestionarItem");
            generarMensajes();
        }
    }

    public void guardarCapitaDescuentoMasivo() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR_CAPITA_DESCUENTO_MASIVO);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!this.isError()) {
            this.addMensaje("Se ha realizado el descuento capita masivo para la factura de id  " + this.getObjeto().getId());
            PrimeFaces.current().executeScript("PF('dialogoCapitaDescuento').hide()");
            PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').unselectAllRows();");
            procesoFinal();
        } else {
            PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento");
            generarMensajes();
        }
    }

    public void guardarAdjuntos() {

        if (this.getRegistrosAuditoriaAdjuto().size() > 0
                || this.getObjeto().getListaAdjuntosEliminar().size() > 0) {
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_GUARDAR_ADJUNTOS);
            this.getObjeto().setListaCmAuditoriaAdjuntos(this.getRegistrosAuditoriaAdjuto());
            getCmAuditoriaFacturaServicio().Accion(this);
            if (!isError()) {
                procesoFinal();
            } else {
                generarMensajes();
            }
        } else {
            this.addError("Por favor ingrese un adjunto para realizar la operación");
            generarMensajes();
        }

    }

    public void guardarReversionFactura(CmFactura cmFactura) {
        super.setAccion(ACCION_ADICIONAL_10);
        super.setDoAccion(ACCION_GUARDAR);
        this.setObjeto(cmFactura);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!this.isError()) {
            this.addMensaje("Se ha realizado la operación de reversión para la factura de número facturado: " + cmFactura.getNumeroFacturado());
            procesoFinal();
        } else {
            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
            generarMensajes();
        }
    }
    
    public void guardarBorradoMotivosEspecificos(){
        
        if(validarSeleccionMotivosEspecificos()){
         super.setAccion(ACCION_ADICIONAL_4);
         super.setDoAccion(ACCION_BORRAR_MOTIVOS_ESPECIFICOS);
         getCmAuditoriaFacturaServicio().Accion(this);
        }
       
        if (!isError()) {
            cerrarDialogosBorradoMotivosMasivos();
            limpiarListasBorradoMotivosEspecificos();
            PrimeFaces.current().ajax().update("frmGestionar");
        }
        procesoFinal();
    }
    
    public void guardarMarcadoGlosaIps(CmFactura cmFactura) {
        getObjeto().setId(cmFactura.getId());
        this.setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(cmFactura.getId());
        getParamConsultaUtilitario().setParametroConsulta2(!cmFactura.isRespuestaIps());
        getParamConsultaUtilitario().setParametroConsulta3(cmFactura.isRespuestaIps() ?  null : new Date()) ;
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR_MARCADO_GLOSA_IPS);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!isError()) {
            addMensaje("La factura de número facturado ("+ cmFactura.getNumeroFacturado()+") ha sido " +
                      (!cmFactura.isRespuestaIps() ?" marcada Ips.": " desmarcada Ips.") );
            super.setDoAccion(cmFactura.isRespuestaIps() ? ACCION_GUARDAR_DESMARCADO_GLOSA_IPS : ACCION_GUARDAR_MARCADO_GLOSA_IPS);
        }
        procesoFinal();
    }
    
    public void guardarMarcarCopagoNoEfectivo(boolean esMarcado, int tipoGestion) {
                 
        setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values())); 
        this.setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(esMarcado);
        getParamConsultaUtilitario().setParametroConsulta2(tipoGestion);
        
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!isError()) {
            limpiarPantallaSeleccionMasivaDetalles();
            addMensaje("Los detalles han sido " +
                      ( esMarcado ?" marcados en copago no efectivo.": " desmarcados en copago no efectivo.") );
            super.setDoAccion(ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO);
        }
        procesoFinal();
        
    }

    private void limpiarPantallaSeleccionMasivaDetalles() {
        PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
        PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').unselectAllRows();");
        getHashDetallesSelecionados().clear();
        getRegistrosDetallesSeleccionadoMasivos().clear();
    }

    public void cambiarEstadoAuditoria() {
        int tipoAuditoria = getObjeto().getTipoAuditoria();
        if (CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA == tipoAuditoria) {
            verConfirmarCerradoAuditoria();
        } else {
            guardarEstadoAuditoria();
        }
    }

    public void guardarEstadoAuditoria() {

        int tipoAuditoria = getObjeto().getTipoAuditoria();
        getObjeto().setTipoAuditoria(tipoAuditoria);

        if (validarFacturaEsAuditable(this.getObjeto())) {
            if (tipoAuditoria > 0) {
                super.setAccion(ACCION_ADICIONAL_4);
                super.setDoAccion(ACCION_GUARDAR_ESTADO_AUDITORIA);
                getCmAuditoriaFacturaServicio().Accion(this);
                if (!isError()) {
                    if (CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA == tipoAuditoria) {
                        PrimeFaces.current().executeScript("PF('dialogoConfirmarCierreAuditoria').hide()");
                    }
                    PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                    procesoFinal();
                } else {
                    generarMensajes();
                }
            } else {
                this.addError("Seleccione un estado de auditoría válido.");
                generarMensajes();
            }
        } else {
            this.addError("El proceso de auditoría se esta realizando, por favor espere.");
            generarMensajes();
        }
    }

    public void guardarDevolcionFactura() {

        getCmAuditoriaDevolucion().setCmFactura(new CmFactura(this.getObjeto().getId()));
        getCmAuditoriaDevolucion().castMaeContratoModalidad(getCmAuditoriaDevolucion().getMaestroContrato());
        getCmAuditoriaDevolucion().castMaeMotivoDevolucion(getCmAuditoriaDevolucion().getMaestroMotivoDevolucion());
        getCmAuditoriaDevolucion().castMaeMotivoDevolucionEspecifico(getCmAuditoriaDevolucion().getMaestroMotivoDevolucionEspecifico());
        getCmAuditoriaDevolucion().castMaeRegimen(getHashMaeRegimen().get(getCmAuditoriaDevolucion().getMaeRegimenId()));
        getCmAuditoriaDevolucion().setIps(this.getObjeto().getIps());
        getCmAuditoriaDevolucion().setNit(this.getObjeto().getNit());
        getCmAuditoriaDevolucion().setNumeroFacturado(this.getObjeto().getNumeroFacturado());
        getCmAuditoriaDevolucion().setNumeroRadicado("" + this.getObjeto().getNumeroRadicado());
        getCmAuditoriaDevolucion().setFechaDevolucion(new Date());
        getCmAuditoriaDevolucion().setFechaRadicacion(this.getObjeto().getFechaRadicacion());
        getCmAuditoriaDevolucion().setValorFactura(this.getObjeto().getValorFactura());
        getCmAuditoriaDevolucion().setMaeRegimenId(this.getObjeto().getMaeRegimenId());
        getCmAuditoriaDevolucion().setMaeRegimenCodigo(this.getObjeto().getMaeRegimenCodigo());
        getCmAuditoriaDevolucion().setMaeRegimenValor(this.getObjeto().getMaeRegimenValor());

        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR_DEVOLUCION_FACTURA);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoDevolucionFactura').hide()");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void guardarCapitaDescuento() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_GUARDAR_CAPITA_DESCUENTO);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!isError()) {
            this.addMensaje("Se ha realizado el proceso de guardado de descuento capita");
            PrimeFaces.current().executeScript("PF('dialogoCapitaDescuento').hide()");
            PrimeFaces.current().ajax().update("frmGestionar");
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public void cerrarVentanaGestionMasiva() {
        getHashDetallesSelecionados().clear();
        getRegistrosDetallesSeleccionadoMasivos().clear();
        PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').unselectAllRows();");
        if (super.getMensajes() != null && (super.getMensajes().size() > 0 || super.isError())) {
            procesoFinal();
        }
    }

    public void cerrarVentanaGestionFactura() {
        if (super.getMensajes() != null && (super.getMensajes().size() > 0 || super.isError())) {
            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento");
            PrimeFaces.current().ajax().update("frmConsultaAuditoriaEvento:tablaFacturasAuditar");
        }
    }

    public void limpiarRegistrosTemporales() {
        setRegistrosAuditoriaAdjuto(new ArrayList<>());
        setRegistrosAuditoriaAutorizacion(new ArrayList<>());
        setRegistrosAuditoriaDiagnostico(new ArrayList<>());
        setRegistrosAuditoriaMotivoGlosa(new ArrayList<>());
        setRegistrosConceptoContable(new ArrayList<>());
        setRegistrosAuditoriaAdjuto(new ArrayList<>());
        setRegistrosAdjuntosEliminar(new ArrayList<>());
        setRegistrosAuditoriaAdjutoCmDetalle(new ArrayList<>());      
        limpiarHashInsumoAuditoriaEliminados();
        limpiarHashInsumoAuditoriaInsertados();
          
    }

    public void limpiarFiltrosPantallaGestionarFactura() {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmGestionar:tablaDetalles");
            if (dataTable != null) {
                dataTable.reset();
                PrimeFaces.current().resetInputs("frmGestionar");
                PrimeFaces.current().ajax().update("frmGestionar:tablaDetalles");
            }
        } catch (Exception e) {
            this.addError(e.getMessage());
        }
    }
    
    public void limpiarListasBorradoMotivosEspecificos() {
        try {
            getHashDetallesSelecionados().clear();
            getRegistrosDetallesSeleccionadoMasivos().clear();
            getMotivosEspecificosSeleccionados().clear();
        } catch (Exception e) {
            this.addError(e.getMessage());
        }
    }
    
    public void limpiarMotivosEspecificosEncontrados() {
        try {
            getListaMotivosEspecificosEncontrados().clear();
            getMotivosEspecificosSeleccionados().clear();
        } catch (Exception e) {
            this.addError(e.getMessage());
        }
    }
    
    private void limpiarFiltrosContratos() {
        PrimeFaces.current().resetInputs("frmListarCntContratos");
        PrimeFaces.current().ajax().update("frmListarCntContratos");
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().
                findComponent("frmListarCntContratos:tablaListarCntContratos");
        dataTable.reset();
    }
    
    private void limpiarSelectMotivoAplicativo() {
        getCmAuditoriaMotivoGlosa().setMaeMotivoGlosaAplicacionId(0);
        getListaMaeMotivosAplicacion().clear();
    }
    
    private void limpiarSelectoresMotivoGlosa() {
        this.getListaMaeMotivosEspecificos().clear();
        this.getListaMaeMotivosAplicacion().clear();
    }
    
    public void cerrarDialogosBorradoMotivosMasivos() {
        PrimeFaces.current().executeScript("PF('dialogoEliminarMotivo').hide()");
        PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
        PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').unselectAllRows();");
    }

    public boolean verificarEsAuditable(CmFactura factura) {

        boolean facturaAuditable;
        int idSolicitanteAuditoria = this.getConexion().getUsuario().getId();

        facturaAuditable = isAccionAdicional8();//super administrador

        if (!facturaAuditable) {
            facturaAuditable = factura.getUsuarioLider().getId() != null
                    ? factura.getUsuarioLider().getId() == idSolicitanteAuditoria : false;
        }

        if (!facturaAuditable) {
            facturaAuditable = factura.getUsuarioGestiona().getId() != null
                    ? factura.getUsuarioGestiona().getId() == idSolicitanteAuditoria : false;
            if (facturaAuditable) {
                if (CmFactura.ESTADO_FACTURA_EN_AUDITORIA == factura.getEstadoFactura()) {
                    facturaAuditable = true;
                } else {
                    facturaAuditable = factura.getTipoAuditoria() == CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA
                            || factura.getTipoAuditoria() == CmFactura.TIPO_AUDITORIA_PERTINENCIA_TECNICA
                            || factura.getTipoAuditoria() == CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA;
                }
            }
        }

        if (facturaAuditable) {
            facturaAuditable = factura.getEstadoFactura() == CmFactura.ESTADO_FACTURA_SIN_AUDITORIA
                    || factura.getEstadoFactura() == CmFactura.ESTADO_FACTURA_EN_AUDITORIA;
        }

        return facturaAuditable;
    }

    public boolean verificarEsReintentable(CmFactura factura) {
        return factura.getEstadoFactura() > 0 && (CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA == factura.getEstadoFactura()
                || CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION == factura.getEstadoFactura()
                || CmFactura.ESTADO_FACTURA_SIN_PROCESAR == factura.getEstadoFactura());
    }

    public boolean verificarEsRevertibleFactura(CmFactura factura) {
        return factura.getEstadoFactura() > 0
                && CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA == factura.getEstadoFactura();
    }

    public boolean validarEsFacturaMarcableGlosaIps(CmFactura cmFactura) {
        boolean esFacturaMarcable = false;
        if (cmFactura.getEstadoFactura() == CmFactura.ESTADO_FACTURA_GLOSADA) {
            esFacturaMarcable = true;
        }
        return esFacturaMarcable;
    }
    
    public void calcularPorcentaje() {

        BigDecimal porcentaje = getCmAuditoriaMotivoGlosa().getPorcentaje();
        BigDecimal valor;
        if (this.isEsGestionIndividual() || getCmAuditoriaMotivoGlosa().isEsValorUnificado()) {
            valor = getObjetoItemServicio().getValorFacturado();
            if (porcentaje.compareTo(new BigDecimal("0.0")) == 0) {
                this.addError("Para calcular el porcentaje se debe ingresar valor mayor a cero");
            } else {
                BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
                BigDecimal valorMotivo = promedio.multiply(valor).setScale(4, RoundingMode.HALF_UP);
                getCmAuditoriaMotivoGlosa().setValorMotivo(valorMotivo);
                PrimeFaces.current().resetInputs("frmAgregarMotivo:porcentajeM");  
                PrimeFaces.current().resetInputs("frmEditarMotivo:porcentajeM"); 
            }
            generarMensajes();
        }
    }

    public void conversionPorcentaje() {
        BigDecimal valorLimite = this.getObjetoItemServicio().getValorFacturado();
        BigDecimal valorPropuesto = getCmAuditoriaMotivoGlosa().getValorMotivo();
        if (valorPropuesto.compareTo(valorLimite) > 0) {
            addError("El valor no debe sobrepasar el valor facturado del servicio o ítem.");
            getCmAuditoriaMotivoGlosa().setPorcentaje(new BigDecimal("0.0"));
            getCmAuditoriaMotivoGlosa().setValorMotivo(new BigDecimal("0.0"));
            generarMensajes();
        } else {
            BigDecimal promedio = valorPropuesto.divide(valorLimite, 5, RoundingMode.CEILING);
            BigDecimal procentajeCalculado = promedio.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
            getCmAuditoriaMotivoGlosa().setPorcentaje(procentajeCalculado);  
            PrimeFaces.current().resetInputs("frmAgregarMotivo:valorM");  
            PrimeFaces.current().resetInputs("frmEditarMotivo:valorM"); 
        }
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
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
            getCmAuditoriaConceptoContable().setUbicacionMunicipio(ubicacionesFiltradas.get(0));
        }
        return ubicacionesFiltradas;
    }

    public void onCheckearItem(SelectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
            getHashDetallesSelecionados().put(detalle.getId(), detalle);
        }
    }

    public void onDescheckearItem(UnselectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
            getHashDetallesSelecionados().remove(detalle.getId());
        }
    }

    public void onSeleccionarFilaMasiva(SelectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();

        for (CmDetalle detalleIn : getRegistrosAuditoriaDetalles()) {
            if (getHashDetallesSelecionados().get(detalleIn.getId()) != null) {
                getHashDetallesSelecionados().remove(detalleIn.getId());
            }
        }

        if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
            getHashDetallesSelecionados().put(detalle.getId(), detalle);
        }
    }

    public void onDesSeleccionarFilaMasiva(SelectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
            getHashDetallesSelecionados().remove(detalle.getId());
        }
    }

    public void OnCheckearTodosItemsPorPagina(ToggleSelectEvent event) {
        if (event.isSelected()) {
            for (CmDetalle detalle : getRegistrosDetallesSeleccionadoMasivos()) {
                if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
                    getHashDetallesSelecionados().put(detalle.getId(), detalle);
                }
            }
        } else {
            for (CmDetalle detalle : getRegistrosAuditoriaDetalles()) {
                if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
                    getHashDetallesSelecionados().remove(detalle.getId());
                }
            }
        }
    }

    public void generarPorPaginaXLS(Object document) {
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

    public boolean validacionSumatoriaConceptosContables(List<CmAuditoriaConceptoContable> conceptos) {
        boolean validoSumatoria = false;
        BigDecimal topePorcentaje = new BigDecimal("100");
        BigDecimal acumuladoPorcentaje = new BigDecimal("0");

        if (!conceptos.isEmpty()) {
            for (CmAuditoriaConceptoContable concepto : conceptos) {
                acumuladoPorcentaje = acumuladoPorcentaje.add(concepto.getPorcentaje());
            }
            if (acumuladoPorcentaje.compareTo(topePorcentaje) == 0) {
                validoSumatoria = true;
            }
        } else {
            validoSumatoria = true;
        }
        return validoSumatoria;
    }

    public String asignarIndicadorVisualEstadoFactura(int estadoFactura) {
        String estilo = "";
        String colorEstado = "";

        switch (estadoFactura) {
            case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA:
            case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION:
            case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION:
                colorEstado = "#bc241b;";
                break;
            case CmFactura.ESTADO_FACTURA_DEVUELTA:
                colorEstado = "#eadc2a;";
                break;
            default:
                break;
        }

        if (!colorEstado.isEmpty()) {
            estilo = "width: 13px; height: 13px; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%; background:" + colorEstado + "";
        }

        return estilo;
    }
    
    public String asignarColorDiasRadicacion(CmFactura cmFactura) {
       
        int LIMITE_CERO  = 0;
        int LIMITE_NUEVE = 9;
        int LIMITE_DIECINUEVE = 19;
        String colorEstado = "";
        
        if (CmFactura.ESTADO_FACTURA_SIN_AUDITORIA == cmFactura.getEstadoFactura()) {
            if (cmFactura.getNumeroDiasPasados() >= LIMITE_CERO
                    && cmFactura.getNumeroDiasPasados() <= LIMITE_NUEVE) {
                colorEstado = "#009900;";
            }
            if (cmFactura.getNumeroDiasPasados() > LIMITE_NUEVE
                    && cmFactura.getNumeroDiasPasados() <= LIMITE_DIECINUEVE) {
                colorEstado = "#eadc2a;";
            }
            if (cmFactura.getNumeroDiasPasados() > LIMITE_DIECINUEVE) {
                colorEstado = "#bc241b;";
            }
        } 
        return colorEstado;
    }

    public void ejercutarOperacionMasiva() {

        int tipoOperacion = getCmSelectorOperacionaMasiva().getTipoOperacion();
        switch (tipoOperacion) {
    
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_DESMARCAR_COPAGO_NO_EFECTIVO:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_MARCAR_COPAGO_NO_EFECTIVO:
                 boolean esMarcado = (tipoOperacion == CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_MARCAR_COPAGO_NO_EFECTIVO) ;
                 int tipoGestion = getCmSelectorOperacionaMasiva().getTipoGestionMasiva();
                guardarMarcarCopagoNoEfectivo(esMarcado,tipoGestion );
                break;

            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_GESTION_ITEM:
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
                verGestionarMasivo(getCmSelectorOperacionaMasiva().getIdFactura(), getCmSelectorOperacionaMasiva().getTipoGestionMasiva());
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_CAPITA_DESCUENTO:
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
                verDescuentoCapitaMasiva(getCmSelectorOperacionaMasiva().getIdFactura(), getCmSelectorOperacionaMasiva().getTipoGestionMasiva());
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_CONCEPTOS_CONTABLES:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DIAGNOSTICOS:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_AUTORIZACIONES:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DESCUENTO_CAPITA:
                asignarDetallesAOperacionMasiva(getCmSelectorOperacionaMasiva().getTipoGestionMasiva());
                switch (getCmSelectorOperacionaMasiva().getTipoOperacion()) {
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_CONCEPTOS_CONTABLES:
                        borrarConceptosPorFactua();
                        break;
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS:
                        borrarMotivosPorFactua();
                        break;
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DIAGNOSTICOS:
                        borrarDiagnosticosPorFactua();
                        break;
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_AUTORIZACIONES:
                        borrarAutorizacionesPorFactua();
                        break;
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DESCUENTO_CAPITA:
                        borrarDescuentoCapitaPorFactura();
                        break;
                }
                if (!this.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
                    this.addMensaje("Levantamiento realizado con éxito");
                    PrimeFaces.current().executeScript("PF('tablaWidgetDetalles').unselectAllRows();");
                    procesoFinal();
                    getHashDetallesSelecionados().clear();
                    getRegistrosDetallesSeleccionadoMasivos().clear();
                }
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_GESTION_CONCEPTOS_MOTIVOS:
                getRegistrosDetallesSeleccionadoMasivos().clear();
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
                asignarDetallesAOperacionMasiva(getCmSelectorOperacionaMasiva().getTipoGestionMasiva());
                this.setAplicaRecobro(false);
                if (validacionItemsGestionExistentes()) {
                    verGestionarMotivosConceptosDiagnosticosMultiUsuario(getCmSelectorOperacionaMasiva().getIdFactura(), getCmSelectorOperacionaMasiva().getTipoGestionMasiva());
                } else {
                    PrimeFaces.current().executeScript("PF('dialogoConfirmacionOperacionesMultiusuario').show()");
                }
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS:
                asignarDetallesAOperacionMasiva(getCmSelectorOperacionaMasiva().getTipoGestionMasiva());
                verMotivosGlosaUsados();
                break;
                
            default:
                this.addError("El tipo de operación no esta implementado.");
                break;
        }
        if (this.isError()) {
            this.generarMensajes();
        }

    }

    public boolean validacionItemsGestionExistentes() {
        boolean validarProceso = true;
        String mensajeExitenciaOperaciones = "";
        if (!getRegistrosDetallesSeleccionadoMasivos().isEmpty()) {
            for (CmDetalle detalleSeleccionado : getRegistrosDetallesSeleccionadoMasivos()) {
                if (  detalleSeleccionado.getCantidadConceptosContablesAsociados() > 0 || 
                      detalleSeleccionado.getCantidadDiagnosticosAsociados() > 0) {
                    String mensajeConceptos = detalleSeleccionado.getCantidadConceptosContablesAsociados() > 0 ? " - conceptos " : "";
                    String mensajeDiagnosticos = detalleSeleccionado.getCantidadDiagnosticosAsociados() > 0 ? " - diagnósticos " : "";

                    mensajeExitenciaOperaciones = "Items para auditar masivamente ya contienen : (" + mensajeConceptos + mensajeDiagnosticos + "), "
                            + "ej : " + "el detalle de id " + detalleSeleccionado.getId() + "."
                            + "Si desea continuar recuerde que si ejecuta una operación ya realizada esta no se realizará sobre detalles que ya la tengan.";

                    validarProceso = false;
                    break;
                }
            }
        }

        if (!validarProceso) {
            setDescripcionGenerica(mensajeExitenciaOperaciones);
        }
        return validarProceso;

    }

    public void mostrarCentroCostosPorAltoCosto() {
        boolean mostrarCentroCostos = false;
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CENTRO_COSTOS_ASOCIADOS);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!this.isError()) {
            if (getListaMaeCentrosCostos() != null && !getListaMaeCentrosCostos().isEmpty()) {
                mostrarCentroCostos = true;    
            }
            this.getCmAuditoriaConceptoContable().setMostrarCentroCosto(mostrarCentroCostos);
            PrimeFaces.current().ajax().update("frmAgregarConcepto");
        }
        generarMensajes();
    }

    public void mostrarMotivosEspecificosRelacionados() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVO_ESPECIFICO_HIJO);
        limpiarSelectMotivoAplicativo();
        getCmAuditoriaFacturaServicio().Accion(this);
        if (this.isError()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
        }
    }
    
    public void mostrarMotivosEspecificosRelacionadosEdicion() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVO_ESPECIFICO_HIJO);
        limpiarSelectMotivoAplicativo();
        getCmAuditoriaMotivoGlosa().getMaestroMotivo().setId(getCmAuditoriaMotivoGlosa().getMaeMotivoId());
        getCmAuditoriaFacturaServicio().Accion(this);
        if (this.isError()) {          
            generarMensajes();
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
        }
    }
   
    public void mostrarMotivosAplicacionRelacionados() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVO_APLICACION_HIJO);
        this.getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().setId( getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().getId());
        getCmAuditoriaFacturaServicio().Accion(this);
        //asignacion de motivo de aplicacion si es unico
        if(this.getListaMaeMotivosAplicacion().size() == 1){
          this.getCmAuditoriaMotivoGlosa().setMaestroMotivoAplicacion(this.getListaMaeMotivosAplicacion().get(0));
        }
        
        if (this.isError()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
        }
    }
    
    public void mostrarMotivosAplicacionRelacionadosEdicion() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVO_APLICACION_HIJO);
        this.getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().setId( getCmAuditoriaMotivoGlosa().getMaeMotivoEspecificoId());
        getCmAuditoriaFacturaServicio().Accion(this);
        asignarMotivoAplicacionPorDefecto();
        
        if (this.isError()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmEditarMotivo");
        }
    }
    
    public void mostrarMotivosEspecificosDevolucion() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_ESPECIFICOS_DEVOLUCION);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (this.isError()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmDevolucionFactura");
        }
    }

    public void asignarDetallesAOperacionMasiva(int tipoGestionMasiva) {

        if (GESTION_MASIVA_PARCIAL == tipoGestionMasiva) {
            setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values()));
        }

        if (GESTION_MASIVA_TOTAL == tipoGestionMasiva) {
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(ACCION_LISTAR_TODOS_DETALLES_POR_FACTUA);
            getCmAuditoriaFacturaServicio().Accion(this);
        }
    }
    
    private void asignarMotivoAplicacionPorDefecto() {
        if (this.getListaMaeMotivosAplicacion().size() == 1) {
            this.getCmAuditoriaMotivoGlosa().setMaestroMotivoAplicacion(
                    this.getListaMaeMotivosAplicacion().get(0));
            this.getCmAuditoriaMotivoGlosa().setMaeMotivoGlosaAplicacionId(
                    this.getCmAuditoriaMotivoGlosa().getMaestroMotivoAplicacion().getId());
        }
    }

    public void actualizarVisualizacionTablasConceptos() {
        DataTable tablaConceptosEnGestionItem = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmGestionarItem:tablaConceptos");
        if (tablaConceptosEnGestionItem != null) {
            PrimeFaces.current().ajax().update("frmGestionarItem:tablaConceptos");
        }
        DataTable tablaConceptosEnMultiUsuario = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmGestionarItemMultiUsuario:tablaConceptos");
        if (tablaConceptosEnMultiUsuario != null) {
            PrimeFaces.current().ajax().update("frmGestionarItemMultiUsuario:tablaConceptos");
        }
    }

    public void actualizarVisualizacionTablasMotivos() {
        DataTable tablaMotivoEnGestionItem = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmGestionarItem:tablaMotivos");
        if (tablaMotivoEnGestionItem != null) {
            PrimeFaces.current().ajax().update("frmGestionarItem:tablaMotivos");
        }
        DataTable tablaMotivoEnMultiUsuario = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmGestionarItemMultiUsuario:tablaMotivos");
        if (tablaMotivoEnMultiUsuario != null) {
            PrimeFaces.current().ajax().update("frmGestionarItemMultiUsuario:tablaMotivos");
        }
    }

    public void actualizarVisualizacionTablaDiagnosticos() {
        DataTable tablaDiagnosticosEnGestionItem = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmGestionarItem:tablaDiagnosticosCrear");
        if (tablaDiagnosticosEnGestionItem != null) {
            PrimeFaces.current().ajax().update("frmGestionarItem:tablaDiagnosticosCrear");
        }
        DataTable tablaDiagnosticosEnMultiUsuario = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmGestionarItemMultiUsuario:tablaDiagnosticosCrear");
        if (tablaDiagnosticosEnMultiUsuario != null) {
            PrimeFaces.current().ajax().update("frmGestionarItemMultiUsuario:tablaDiagnosticosCrear");
        }
    }

    public void cerrarVisualizacionDialogosGestion() {
        PrimeFaces.current().executeScript("PF('dialogoGestionarItem').hide()");
        PrimeFaces.current().executeScript("PF('dialogoGestionarItemMultiUsuario').hide()");
    }

    public boolean validarFacturaEsAuditable(CmFactura factura) {
        
        boolean facturaEditable = CmFactura.ESTADO_FACTURA_SIN_AUDITORIA == factura.getEstadoFactura();
        Integer idUsuario = getConexion().getUsuario().getId();
        boolean esSuperAdmin = isAccionAdicional8();//super administrador
        if( CmFactura.ESTADO_FACTURA_EN_AUDITORIA == factura.getEstadoFactura()){
            if( Objects.equals(factura.getUsuarioGestiona().getId(), idUsuario) || 
                Objects.equals(factura.getUsuarioLider().getId(), idUsuario)||
                esSuperAdmin ){
                facturaEditable = true;
            }
        } 
        return facturaEditable;
    }

    public boolean validarSumatoriaDetallesPorValorFactura(CmFactura factura) {
        boolean esSumatoriaIgual = false;
        boolean esModalidadContratoCapitaPGP = validarFacturaCierreDirecto(factura);

        if (esModalidadContratoCapitaPGP) {
            esSumatoriaIgual = true;
        } else {
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_OBTENER_VALOR_TOTAL_DETALLES_POR_FACTURA);
            getCmAuditoriaFacturaServicio().Accion(this);
            
            if (!this.isError()) {
                BigDecimal valorFacturadoFactura = factura.getValorFactura().setScale(2, RoundingMode.DOWN);
                BigDecimal valorCopago = Optional.ofNullable(factura.getValorCopago()).orElse(new BigDecimal(BigInteger.ZERO));

                valorFacturadoFactura = valorFacturadoFactura.add(valorCopago).setScale(2, RoundingMode.DOWN);
                if (this.getSumaValorFacturadoDetallesPorFactura().compareTo(valorFacturadoFactura) == 0) {
                    esSumatoriaIgual = true;
                }
                if (!esSumatoriaIgual) {
                    this.addError("El valor facturado : " + valorFacturadoFactura + " no coincide con la sumatoria del valor facturado de " + 
                            " sus detalles : " + this.getSumaValorFacturadoDetallesPorFactura() + 
                            " para la factura de número facturado : " + factura.getNumeroFacturado() +
                            " y número radicado : " + factura.getNumeroRadicado());
                }
            }         
        }
        return esSumatoriaIgual;
    }

    public boolean validarFacturaCierreDirecto(CmFactura factura) {
        return factura.getMaeTipoContratoCodigo() != null
                && (CmFactura.CODIGO_MODALIDAD_CONTRATO_CAPITA.equals(factura.getMaeTipoContratoCodigo())
                || CmFactura.CODIGO_MODALIDAD_CONTRATO_PGP.equals(factura.getMaeTipoContratoCodigo()));
    }
 
    public boolean validarExisteciaMotivosEspecificos() {
        boolean esValido = true;
        if (getListaMotivosEspecificosEncontrados().isEmpty()) {
            addError("No se han encontrado motivos para eliminar");
            esValido = false;
        }
        return esValido;
    }
    
    public boolean validarSeleccionMotivosEspecificos() {
        boolean esValido = true;
        if (getMotivosEspecificosSeleccionados().isEmpty()) {
            addError("Para realizar la eliminación debe seleccionar motivos");
            esValido = false;
        }
        return esValido;
    }

    public void verTransacciones(CmFactura factura) {
        getObjetoReintento().setIdFactura(factura.getId());
        getObjetoReintento().setCmFactura(factura);
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_VER_WS_TRANSACCIONES_DETALLE);
        getCmAuditoriaFacturaServicio().Accion(this);
      
        if (!this.isError()) {
            PrimeFaces.current().ajax().update("frmVerTransacciones");
            PrimeFaces.current().executeScript("PF('dialogoVerTransacciones').show()");

        } else {
            this.addError("Se han presentado inconsistencias al realizar la búsqueda de transaccciones."
                    + "Por favor refresque la búsqueda en auditoría o reintente de nuevo.");
        }
        procesoFinal();
    }
    
    private boolean validarPorcentajeMayorCero() {
        boolean esValidaInsercion = true;        
        if (getCmAuditoriaMotivoGlosa().getPorcentaje().compareTo(BigDecimal.ZERO) == 0) {
            esValidaInsercion = false;
            this.addError("Ingrese por favor un porcentaje mayor que cero.");
        }
        return esValidaInsercion;
    }

    private boolean validarMotivosNoRepetidos( ) {
        boolean esValidaInsercion = true;
          
        if (!this.getObjeto().getVersion()) {
            for (CmAuditoriaMotivoGlosa motivo : getRegistrosAuditoriaMotivoGlosa()) {
                if (motivo.getMaestroMotivoEspecifico().getValor().
                        equals(getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().getValor())) {
                    esValidaInsercion = false;
                    this.addError("No se puede agregar motivos específicos iguales para este detalle o servicio.");
                    break;
                }

            }
        } else {         
            for (CmAuditoriaMotivoGlosa motivo : getRegistrosAuditoriaMotivoGlosa()) {
                
                String valorMotivoApliaccionExistente = Optional.ofNullable(motivo.getMaestroMotivoAplicacion().getValor()).orElse("");
                String valorMotivoEspecificoExistente = Optional.ofNullable(motivo.getMaestroMotivoEspecifico().getValor()).orElse("");
                String valorMotivoEspecificoNuevo = Optional.ofNullable(getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().getValor()).orElse("");
                String valorMotivoAplicacionNuevo = Optional.ofNullable(getCmAuditoriaMotivoGlosa().getMaestroMotivoAplicacion().getValor()).orElse("");
                
                if ( valorMotivoApliaccionExistente.equals(valorMotivoAplicacionNuevo) && 
                     valorMotivoEspecificoExistente.equals(valorMotivoEspecificoNuevo)) {
                    esValidaInsercion = false;
                    this.addError("No se puede agregar motivos específicos y de aplicación iguales para este detalle o servicio.");
                    break;
                }
            }
        }
        return esValidaInsercion;
    }
    
    public boolean bloquarFacturaPorUsuario() {
        boolean esBloqueoPermitido;
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_BLOQUEAR_FACTURAS_AUDITORIA);
        getCmAuditoriaFacturaServicio().Accion(this);
        esBloqueoPermitido = getCmRespuestaGenerica().isEstadoRespuesta();
        if( ! esBloqueoPermitido && !isError()){
                addError(getCmRespuestaGenerica().getMensaje());
        }
        return esBloqueoPermitido;
    }

    public void verJson(String tituloJsonMensaje, String mensajeJson, Date fechaEnvio) {
        getJsonMostrable().setTituloJsonMensaje(tituloJsonMensaje);
        getJsonMostrable().setJsonContenido(mensajeJson);
        getJsonMostrable().setFechaEnvio(fechaEnvio);
        PrimeFaces.current().resetInputs("frmVerJson");
        PrimeFaces.current().ajax().update("frmVerJson");
        PrimeFaces.current().executeScript("PF('dialogoVerJson').show()");
    }

    public void reintentar(Integer idCmRadicado) {
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_GUARDAR_REINTENTO_FACTURA);
        this.getObjetoReintento().setRadicado(idCmRadicado);
        getCmAuditoriaFacturaServicio().Accion(this);
        if (!this.isError()) {
            this.addMensaje("La solicitud de reintento se ha realizado");
            PrimeFaces.current().executeScript("PF('dialogoVerReintento').hide()");
            procesoFinal();
        } else {
            PrimeFaces.current().ajax().update("frmVerReintento");
            generarMensajes();
        }
    }

    public String asignarColorNovedadFechaPrestacion(AsegRegistroNovedad asegRegistoNovedad, CmDetalle detalle) {
        String color = "";
        Date fechaHistorico = asegRegistoNovedad.getFechaNovedad();
        Date fechaBusqueda = detalle.getFechaPrestacion() == null ? new Date() : detalle.getFechaPrestacion();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (sdf.format(fechaHistorico).equals(sdf.format(fechaBusqueda))) {
            color = "green";
        }
        return color;
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        String viewMapId = (String) context.getViewRoot().getTransientStateHelper().getTransient("com.sun.faces.application.view.viewMapId");
        session.removeAttribute("cmAuditoriaFacturaBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("cmAuditoriaFacturaBean");
    }
      
    public void desBloquarFacturaPorUsuario(){
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_DESBLOQUEAR_FACTURAS_AUDITORIA);
        getCmAuditoriaFacturaServicio().Accion(this);
        super.setAccion(Url.ACCION_VER);
    }
    
    //cuando se cierra y si falla
    public void onCerrarVentanaGestionarFactura(){
            desBloquarFacturaPorUsuario();
            if(isError()){
                generarMensajes();
            }
            
    }
    
    public void verObjecionesUsuario(CmDetalle detalle){
        setObjetoItemServicio(detalle);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_OBJECIONES_USUARIO);
        getCmAuditoriaFacturaServicio().Accion(this);
        if(!isError()){
            PrimeFaces.current().resetInputs("frmVerObjeciones");
            PrimeFaces.current().ajax().update("frmVerObjeciones");
            PrimeFaces.current().executeScript("PF('dialogoObjeciones').show()");
        }
        procesoFinal();
    }
    
    public String recortarTexto(String texto){
        return texto.substring(0, 30);
    }
   
    private void asignacionValoresConsultaSuceso(CmDetalle detalle) {
        setParamConsultaConsultarSuceso(new ParamConsulta());
        getParamConsultaConsultarSuceso().setParametroConsulta1(this.getObjeto().getCmRipCarga().getId());
        getParamConsultaConsultarSuceso().setParametroConsulta2(detalle.getCmFacturas().getNumeroFacturado());
        getParamConsultaConsultarSuceso().setParametroConsulta3(detalle.getDocumento());
        getParamConsultaConsultarSuceso().setParametroConsulta4(detalle.getMaServicioCodigo());
        getParamConsultaConsultarSuceso().setParametroConsulta5(detalle.getTipoServicio());
        getParamConsultaConsultarSuceso().setParametroConsulta6("");
        getParamConsultaConsultarSuceso().setParametroConsulta7("numero_autorizacion");
    }
    
    public void limpiarSelectorSoportes() {
        DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerSoportes:tablaSoportes");
        dataTable2.reset();
        PrimeFaces.current().resetInputs("frmVerSoportes");
    }
    
     public static Object getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }
  }
