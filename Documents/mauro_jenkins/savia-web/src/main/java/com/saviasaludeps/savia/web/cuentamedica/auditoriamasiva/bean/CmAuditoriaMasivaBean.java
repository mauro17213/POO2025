/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaModulo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaSelectorOperacionMasiva;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmRespuestaGenerica;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturasSinAutorizaciones;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.servicio.CmAuditoriaMasivaServicioIface;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jperezn
 */
@ManagedBean
@ViewScoped
public class CmAuditoriaMasivaBean extends Url {
    
    public final static char ACCION_VER_FACTURA = 'a';
    public final static char ACCION_LISTAR_DETALLES_POR_FACTURA = 'b';
    public final static char ACCION_LISTAR_ADJUNTOS_POR_FACTURA = 'c';
    public final static char ACCION_LISTAR_DIAGNOSTICOS_POR_SERVICIO = 'd';
    public final static char ACCION_LISTAR_CONCEPTOS_POR_SERVICIO = 'e';
    public final static char ACCION_LISTAR_MOTIVOS_GLOSA_POR_SERVICIO = 'f';
    public final static char ACCION_LISTAR_AUTORIZACIONES_POR_SERVICIO = 'g';
    public final static char ACCION_VER_DETALLE_SERVICIO = 'h';
    public final static char ACCION_GUARDAR_ADJUNTOS = 'i';
    public final static char ACCION_VER_AUDITORIA_MASIVA = 'j';
    public final static char ACCION_GUARDAR_ESTADO_AUDITORIA = 'k';
    public final static char ACCION_GUARDAR_AUDITORIA_MASIVA = 'l';
    public final static char ACCION_VER_MOTIVO_ESPECIFICO_HIJO = 'm';
    public final static char ACCION_LISTAR_CNT_CONTRATOS = 'n';
    public final static char ACCION_VER_DEVOLUCION_MASIVA = 'o';
    public final static char ACCION_VER_GESTION_AUDITORIA_MASIVA = 'p';
    public final static char ACCION_VER_DESCUENTO_CAPITA = 'q';
    public final static char ACCION_VALIDAR_LEVANTAMIENTOS = 'r';
    public final static char ACCION_GUARDAR_LEVANTAMIENTOS = 's';
    public final static char ACCION_VER_FACTURAS_SIN_AUTORIZACION = 't';
    public static final char ACCION_CONSULTAR_UBICACION_PRESTADOR_SEDE = 'u';
    public final static char ACCION_LISTAR_DETALLES_MULTI_FACTURA = 'v';
    public final static char ACCION_VER_GESTION_AUDITORIA_MASIVA_DETALLES = 'w';
    public final static char ACCION_GUARDAR_GESTION_AUDITORIA_MASIVA_DETALLES = 'x';
    public final static char ACCION_GUARDAR_LEVANTAMIENTOS_DETALLES = 'y';
    public final static char ACCION_LISTAR_DETALLES_FALTOS_DE_INSUMOS = 'z';
    public static final char ACCION_BLOQUEAR_FACTURAS_POR_USUARIO = 'A';
    public static final char ACCION_DESBLOQUEAR_FACTURAS_POR_USUARIO = 'B';
    public static final char ACCION_LISTAR_CENTRO_COSTOS_ASOCIADOS = 'C';
    public static final char ACCION_LISTAR_ANEXOS_4 = 'D';
    public final static char ACCION_GUARDAR_AUTORIZACION_AUDITORIA_MASIVA = 'E';
    public final static char ACCION_LISTAR_ANEXOS4_ITEMS = 'F';
    public final static char ACCION_VERIFICAR_EXISTENCIA_ANEXO4 = 'G';
    public static final char ACCION_CONSULTAR_PROGRAMAS_POR_AFILIADO = 'H';
    public static final char ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO = 'I';
    public static final char ACCION_REVERSAR_ESTADO_AUDITORIA= 'J';
    public static final char ACCION_VER_ASEGURAMIENTO_AFILIADO_ID = 'K';
    public static final char ACCION_LISTAR_AUDITORIA_NOVEDADES = 'L';
    public static final char ACCION_CONSULTAR_CANTIDAD_DETALLES_PROCESO = 'M';
    public static final char ACCION_VER_MOTIVOS_MULTI_DETALLES= 'N';
    public static final char ACCION_BORRAR_MOTIVOS_ESPECIFICOS= 'O';
    public static final char ACCION_LISTAR_TODOS_DETALLES_MULTI_FAC= 'P';
    public static final char ACCION_GUARDAR_MARCADO_GLOSA_IPS = 'Q';
    public static final char ACCION_VER_CONTRATO_SEDES = 'R';
    public static final char ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO = 'S';
    public static final char ACCION_LISTAR_ADJUNTOS_CMDETALLES = 'T';
    public static final char ACCION_LISTAR_DESCUENTO_CAPITA= 'U';
    public static final char ACCION_LISTAR_MOTIVOS_ESPECIFICOS_DEVOLUCION = 'V';
    public static final char ACCION_VER_MOTIVO_APLICACION_HIJO = 'W';
    public static final char DO_ACCION_VER_SOPORTES = 'X';
    
    public static final int ID_VENTANA_VER_DETALLES = 1;
    public static final int ID_VENTANA_TAB_DETALLES = 2;
    
    private CmAuditoriaMasivaModulo objeto;
    private CmFactura objetoFactura;
    private CmDetalle objetoDetalleServicio;
    private CntContrato contratoConSedes;
    private CmAuditoriaMasivaServicioIface cmAuditoriaMasivaServicio;
    private LazyDataModel<CmFactura> lazyCmAuditoriaMasivaFacturas;  
    private LazyDataModel<CmDetalle> lazyCmAuditoriaDetalles;
    private LazyDataModel<CntContrato> lazyCntContratos;
    private LazyDataModel<AuAnexo4> lazyAnexo4;
    private LazyDataModel<AuAnexo4Item> lazyAnexo4Items;
    private LazyDataModel<PeAfiliadosPrograma> lazyAfiliadosPrograma;
    private LazyDataModel<AsegRegistroNovedad> lazyAseguramientoNovedadHistorial;
    private LazyDataModel<CmFeSoporte> lazySoportes;
    
    private List<CmFactura> registros;
    private List<CmFactura> registrosFacturasSeleccionadas;
    private List<CmDetalle> registrosAuditoriaDetalles;
    private List<CmDetalle> registrosTodosAuditoriaDetallesMultifactura;
    private List<AsegRegistroNovedad> registrosAseguramientoNovedad;
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjuto;
    private List<CmAuditoriaAdjunto> adjuntosParaGuardar;
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle;
    private List<CmAuditoriaConceptoContable> registrosConceptoContable;
    private List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico;
    private List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion;
    private List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa;
    private List<CmAuditoriaCapitaDescuento> registrosAuditoriaCapitaDescuento;
    private List<CntContrato> registrosCntContratos;
    private List<CmFacturasSinAutorizaciones> registrosFacturasSinAutorizaciones;
    private List<CmDetalle> registrosDetallesSeleccionadoMasivos;
    private ArrayList<CmFeSoporte> registrosSoportes = new ArrayList<>();

    private List<CmAuditoriaMotivoGlosa> listaMotivosEspecificosEncontrados;
    private HashMap<Integer, CmDetalle> hashDetallesSelecionados;
    private List<AuAnexo4> registrosAnexo4;
    private List<AuAnexo4Item> registrosAnexo4Item;
    private List<PeAfiliadosPrograma> registrosAfiliadosPrograma;
    private HashMap<Integer, String> hashInsumoAuditoriaInsertados;
    private List<Usuario> usuariosGestiona;
    
    
    private CmAuditoriaConceptoContable cmAuditoriaConceptoContable;
    private CmAuditoriaDiagnostico cmAuditoriaDiagnostico;
    private CmAuditoriaMotivoGlosa cmAuditoriaMotivoGlosa;
    private CmAuditoriaCapitaDescuento cmAuditoriaCapitaDescuento;
    private CmAuditoriaDevolucion cmAuditoriaDevolucion;
    private CmAuditoriaAutorizacion cmAuditoriaAutorizacion;
    private AsegRegistroNovedad objetoRegistroNovedad;
    
    private List<Maestro> listaMaeConceptos;
    private List<Maestro> listaMaeConceptosTotales;
    private List<Maestro> listaMaeConceptosSubsidiados;
    private List<Maestro> listaMaeConceptosContributivos;
    private List<Maestro> listaMaeCentrosCostos;
    private List<Maestro> listaMaeMotivos;
    private List<Maestro> listaMaeMotivosEspecificos;
    private List<Maestro> listaMaeMotivosAplicacion;
    private List<Maestro> listaMaeMotivoDevolucion;
    private List<Maestro> listaMaeMotivoDevolucionEspecifico;
    
    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    
    private HashMap<Integer, CmFactura> hashFacturasSelecionadas;
    private String descripcionGenerica;
    
    private CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasiva;
    private CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasivaDetalles;
    
    private ParamConsulta paramConsultaUtilitario;
    private ParamConsulta paramConsultaDetallesPorFactura;
    private ParamConsulta paramConsultaCntContratos;
    private ParamConsulta paramConsultaAnexo4;
    private ParamConsulta paramConsultaAnexo4Item;
    private ParamConsulta paramConsultaAfiliadoProgramas;
    private ParamConsulta paramConsultaAuditoriaNovedad;
    private ParamConsulta paramConsultaSoportes  = new ParamConsulta();
    
    private boolean ejecucionExitosaOperacion;
    private CmRespuestaGenerica cmRespuestaGenerica;
    private AuAnexo4 auAnexo4;
    
    private PeAfiliadosPrograma peAfiliadosPrograma;
    private AsegAfiliado afiliadoCompleto;
    
    private int tipoOperacionEjecutada;
    private int numeroDetallesEnProceso;
    private List<Integer> motivosEspecificosSeleccionados = new ArrayList();
    
    private SelDiagnosticosBean diagnosticosBean;
    
    private HashMap<Integer,List<Maestro>> hashMaeMotivoDevolucionPadre;
    private HashMap<Integer,List<Maestro>> hashMaeMotivoGlosaPadre;
    private HashMap<Integer, Maestro> hashTipoSoporte = new HashMap<>();
    private boolean aplicaRecobro;

    public List<CmFactura> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFactura> registros) {
        this.registros = registros;
    }

    public boolean isEjecucionExitosaOperacion() {
        return ejecucionExitosaOperacion;
    }
    
    public boolean isContratoCapitaPGP(String tipoContrado) {
        return CmFactura.CODIGO_MODALIDAD_CONTRATO_CAPITA.equals(tipoContrado)
                || CmFactura.CODIGO_MODALIDAD_CONTRATO_PGP.equals(tipoContrado);
    }

    public void setEjecucionExitosaOperacion(boolean ejecucionExitosaOperacion) {
        this.ejecucionExitosaOperacion = ejecucionExitosaOperacion;
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

    public List<CmDetalle> getRegistrosTodosAuditoriaDetallesMultifactura() {
        if (registrosTodosAuditoriaDetallesMultifactura == null) {
            registrosTodosAuditoriaDetallesMultifactura = new ArrayList<>();
        }
        return registrosTodosAuditoriaDetallesMultifactura;
    }

    public void setRegistrosTodosAuditoriaDetallesMultifactura(List<CmDetalle> registrosTodosAuditoriaDetallesMultifactura) {
        this.registrosTodosAuditoriaDetallesMultifactura = registrosTodosAuditoriaDetallesMultifactura;
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
    
    public LazyDataModel<AuAnexo4Item> getLazyAnexo4Items() {
        return lazyAnexo4Items;
    }

    public void setLazyAnexo4Items(LazyDataModel<AuAnexo4Item> lazyAnexo4Items) {
        this.lazyAnexo4Items = lazyAnexo4Items;
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

    public LazyDataModel<CmFactura> getLazyCmAuditoriaMasivaFacturas() {
        return lazyCmAuditoriaMasivaFacturas;
    }

    public void setLazyCmAuditoriaMasivaFacturas(LazyDataModel<CmFactura> lazyCmAuditoriaMasivaFacturas) {
        this.lazyCmAuditoriaMasivaFacturas = lazyCmAuditoriaMasivaFacturas;
    }

    public LazyDataModel<CmDetalle> getLazyCmAuditoriaDetalles() {
        return lazyCmAuditoriaDetalles;
    }

    public void setLazyCmAuditoriaDetalles(LazyDataModel<CmDetalle> lazyCmAuditoriaDetalles) {
        this.lazyCmAuditoriaDetalles = lazyCmAuditoriaDetalles;
    }
    
    public LazyDataModel<AuAnexo4> getLazyAnexo4() {
        return lazyAnexo4;
    }

    public void setLazyAnexo4(LazyDataModel<AuAnexo4> lazyAnexo4) {
        this.lazyAnexo4 = lazyAnexo4;
    }

    public LazyDataModel<CntContrato> getLazyCntContratos() {
        return lazyCntContratos;
    }

    public void setLazyCntContratos(LazyDataModel<CntContrato> lazyCntContratos) {
        this.lazyCntContratos = lazyCntContratos;
    }
    
    public LazyDataModel<PeAfiliadosPrograma> getLazyAfiliadosPrograma() {
        return lazyAfiliadosPrograma;
    }

    public void setLazyAfiliadosPrograma(LazyDataModel<PeAfiliadosPrograma> lazyAfiliadosPrograma) {
        this.lazyAfiliadosPrograma = lazyAfiliadosPrograma;
    }

    public CmAuditoriaMasivaServicioIface getCmAuditoriaMasivaServicio() {
        return cmAuditoriaMasivaServicio;
    }

    public void setCmAuditoriaMasivaServicio(CmAuditoriaMasivaServicioIface cmAuditoriaMasivaServicio) {
        this.cmAuditoriaMasivaServicio = cmAuditoriaMasivaServicio;
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

    public List<CmAuditoriaAdjunto> getAdjuntosParaGuardar() {
        if (adjuntosParaGuardar == null) {
            adjuntosParaGuardar = new ArrayList<>();
        }
        return adjuntosParaGuardar;
    }

    public void setAdjuntosParaGuardar(List<CmAuditoriaAdjunto> adjuntosParaGuardar) {
        this.adjuntosParaGuardar = adjuntosParaGuardar;
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

    public List<CmAuditoriaDiagnostico> getRegistrosAuditoriaDiagnostico() {
        if (registrosAuditoriaDiagnostico == null) {
            registrosAuditoriaDiagnostico = new ArrayList<>();
        }
        return registrosAuditoriaDiagnostico;
    }

    public void setRegistrosAuditoriaDiagnostico(List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico) {
        this.registrosAuditoriaDiagnostico = registrosAuditoriaDiagnostico;
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

    public List<CmAuditoriaMotivoGlosa> getRegistrosAuditoriaMotivoGlosa() {
        if (registrosAuditoriaMotivoGlosa == null) {
            registrosAuditoriaMotivoGlosa = new ArrayList<>();
        }
        return registrosAuditoriaMotivoGlosa;
    }

    public void setRegistrosAuditoriaMotivoGlosa(List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa) {
        this.registrosAuditoriaMotivoGlosa = registrosAuditoriaMotivoGlosa;
    }
    
    public List<CmAuditoriaCapitaDescuento> getRegistrosAuditoriaCapitaDescuento() {
        if (registrosAuditoriaCapitaDescuento == null) {
            registrosAuditoriaCapitaDescuento = new ArrayList<>();
        }
        return registrosAuditoriaCapitaDescuento;
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

    public HashMap<Integer, Maestro> getHashTipoSoporte() {
        return hashTipoSoporte;
    }

    public void setHashTipoSoporte(HashMap<Integer, Maestro> hashTipoSoporte) {
        this.hashTipoSoporte = hashTipoSoporte;
    }
    
    

    public void setRegistrosAuditoriaCapitaDescuento(List<CmAuditoriaCapitaDescuento> registrosAuditoriaCapitaDescuento) {
        this.registrosAuditoriaCapitaDescuento = registrosAuditoriaCapitaDescuento;
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

    public List<CmFacturasSinAutorizaciones> getRegistrosFacturasSinAutorizaciones() {
        if (registrosFacturasSinAutorizaciones == null) {
            registrosFacturasSinAutorizaciones = new ArrayList<>();
        }
        return registrosFacturasSinAutorizaciones;
    }

    public void setRegistrosFacturasSinAutorizaciones(List<CmFacturasSinAutorizaciones> registrosFacturasSinAutorizaciones) {
        this.registrosFacturasSinAutorizaciones = registrosFacturasSinAutorizaciones;
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

    public CmAuditoriaMasivaModulo getObjeto() {
        return objeto;
    }

    public void setObjeto(CmAuditoriaMasivaModulo objeto) {
        this.objeto = objeto;
    }

    public CmFactura getObjetoFactura() {
        if (objetoFactura == null) {
            objetoFactura = new CmFactura();
        }
        return objetoFactura;
    }

    public void setObjetoFactura(CmFactura objetoFactura) {
        this.objetoFactura = objetoFactura;
    }

    public CmDetalle getObjetoDetalleServicio() {
        if (objetoDetalleServicio == null) {
            objetoDetalleServicio = new CmDetalle();
        }
        return objetoDetalleServicio;
    }

    public void setObjetoDetalleServicio(CmDetalle objetoDetalleServicio) {
        this.objetoDetalleServicio = objetoDetalleServicio;
    }
    
    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
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
    
     public ParamConsulta getParamConsultaAnexo4() {
        if (paramConsultaAnexo4 == null) {
            paramConsultaAnexo4 = new ParamConsulta();
        }
        return paramConsultaAnexo4;
    }

    public void setParamConsultaAnexo4(ParamConsulta paramConsultaAnexo4) {
        this.paramConsultaAnexo4 = paramConsultaAnexo4;
    }
    
    public ParamConsulta getParamConsultaCntContratos() {
        if (paramConsultaCntContratos == null) {
            paramConsultaCntContratos = new ParamConsulta();
        }
        return paramConsultaCntContratos;
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

    public void setParamConsultaCntContratos(ParamConsulta paramConsultaCntContratos) {
        this.paramConsultaCntContratos = paramConsultaCntContratos;
    }
    
    public ParamConsulta getParamConsultaAfiliadoProgramas() {
        if (paramConsultaAfiliadoProgramas == null) {
            paramConsultaAfiliadoProgramas = new ParamConsulta();
        }
        return paramConsultaAfiliadoProgramas;
    }

    public void setParamConsultaAfiliadoProgramas(ParamConsulta paramConsultaAfiliadoProgramas) {
        this.paramConsultaAfiliadoProgramas = paramConsultaAfiliadoProgramas;
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

    public ParamConsulta getParamConsultaSoportes() {
        return paramConsultaSoportes;
    }

    public void setParamConsultaSoportes(ParamConsulta paramConsultaSoportes) {
        this.paramConsultaSoportes = paramConsultaSoportes;
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
  
    public CmAuditoriaMotivoGlosa getCmAuditoriaMotivoGlosa() {
        if (cmAuditoriaMotivoGlosa == null) {
            cmAuditoriaMotivoGlosa = new CmAuditoriaMotivoGlosa();
        }
        return cmAuditoriaMotivoGlosa;
    }

    public void setCmAuditoriaMotivoGlosa(CmAuditoriaMotivoGlosa cmAuditoriaMotivoGlosa) {
        this.cmAuditoriaMotivoGlosa = cmAuditoriaMotivoGlosa;
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

    public CmAuditoriaDevolucion getCmAuditoriaDevolucion() {
        if (cmAuditoriaDevolucion == null) {
            cmAuditoriaDevolucion = new CmAuditoriaDevolucion();
        }
        return cmAuditoriaDevolucion;
    }

    public void setCmAuditoriaDevolucion(CmAuditoriaDevolucion cmAuditoriaDevolucion) {
        this.cmAuditoriaDevolucion = cmAuditoriaDevolucion;
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

    public List<Maestro> getListaMaeCentrosCostos() {
        return listaMaeCentrosCostos;
    }

    public void setListaMaeCentrosCostos(List<Maestro> listaMaeCentrosCostos) {
        this.listaMaeCentrosCostos = listaMaeCentrosCostos;
    }
    
    public List<Maestro> getListaMaeMotivos() {
        if (listaMaeMotivos == null) {
            listaMaeMotivos = new ArrayList<>();
        }
        return listaMaeMotivos;
    }

    public void setListaMaeMotivos(List<Maestro> listaMaeMotivos) {
        this.listaMaeMotivos = listaMaeMotivos;
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

    public List<Maestro> getListaMaeMotivoDevolucion() {
        return listaMaeMotivoDevolucion;
    }

    public void setListaMaeMotivoDevolucion(List<Maestro> listaMaeMotivoDevolucion) {
        this.listaMaeMotivoDevolucion = listaMaeMotivoDevolucion;
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


    public SelDiagnosticosBean getDiagnosticosBean() {
        return diagnosticosBean;
    }

    public void setDiagnosticosBean(SelDiagnosticosBean diagnosticosBean) {
        this.diagnosticosBean = diagnosticosBean;
    }
    
     public CmAuditoriaSelectorOperacionMasiva getCmSelectorOperacionaMasiva() {
        if ( cmSelectorOperacionaMasiva == null) {
            cmSelectorOperacionaMasiva = new CmAuditoriaSelectorOperacionMasiva();
        }
        return cmSelectorOperacionaMasiva;
    }

    public void setCmSelectorOperacionaMasiva(CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasiva) {
        this.cmSelectorOperacionaMasiva = cmSelectorOperacionaMasiva;
    }

    public CmAuditoriaSelectorOperacionMasiva getCmSelectorOperacionaMasivaDetalles() {
        if ( cmSelectorOperacionaMasivaDetalles == null ) {
            cmSelectorOperacionaMasivaDetalles = new CmAuditoriaSelectorOperacionMasiva();
        }
        return cmSelectorOperacionaMasivaDetalles;
    }

    public void setCmSelectorOperacionaMasivaDetalles(CmAuditoriaSelectorOperacionMasiva cmSelectorOperacionaMasivaDetalles) {
        this.cmSelectorOperacionaMasivaDetalles = cmSelectorOperacionaMasivaDetalles;
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

    public ArrayList<CmFeSoporte> getRegistrosSoportes() {
        return registrosSoportes;
    }

    public void setRegistrosSoportes(ArrayList<CmFeSoporte> registrosSoportes) {
        this.registrosSoportes = registrosSoportes;
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
    
    public CmRespuestaGenerica getCmRespuestaGenerica() {
        if(cmRespuestaGenerica == null){
            cmRespuestaGenerica = new CmRespuestaGenerica();
        }
        return cmRespuestaGenerica;
    }

    public void setCmRespuestaGenerica(CmRespuestaGenerica cmRespuestaGenerica) {
        this.cmRespuestaGenerica = cmRespuestaGenerica;
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
    
    public List<AuAnexo4Item> getRegistrosAnexo4Item() {
        if (registrosAnexo4Item == null) {
            registrosAnexo4Item = new ArrayList<>();
        }
        return registrosAnexo4Item;
    }

    public void setRegistrosAnexo4Item(List<AuAnexo4Item> registrosAnexo4Item) {
        this.registrosAnexo4Item = registrosAnexo4Item;
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
    
    public PeAfiliadosPrograma getPeAfiliadosPrograma() {
        if (peAfiliadosPrograma == null) {
            peAfiliadosPrograma = new PeAfiliadosPrograma();
        }
        return peAfiliadosPrograma;
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

    public List<Usuario> getUsuariosGestiona() {
        if (usuariosGestiona == null) {
              usuariosGestiona = new ArrayList<>();
        }
        return usuariosGestiona;
    }

    public void setUsuariosGestiona(List<Usuario> usuariosGestiona) {
        this.usuariosGestiona = usuariosGestiona;
    }   
    
    public void setPeAfiliadosPrograma(PeAfiliadosPrograma peAfiliadosPrograma) {
        this.peAfiliadosPrograma = peAfiliadosPrograma;
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
    
    public LazyDataModel<AsegRegistroNovedad> getLazyAseguramientoNovedadHistorial() {
        return lazyAseguramientoNovedadHistorial;
    }

    public void setLazyAseguramientoNovedadHistorial(LazyDataModel<AsegRegistroNovedad> lazyAseguramientoNovedadHistorial) {
        this.lazyAseguramientoNovedadHistorial = lazyAseguramientoNovedadHistorial;
    }

    public LazyDataModel<CmFeSoporte> getLazySoportes() {
        return lazySoportes;
    }

    public void setLazySoportes(LazyDataModel<CmFeSoporte> lazySoportes) {
        this.lazySoportes = lazySoportes;
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
    
    public List<AsegRegistroNovedad> getRegistrosAseguramientoNovedad() {
        if (registrosAseguramientoNovedad == null) {
            registrosAseguramientoNovedad = new ArrayList<>();
        }
        return registrosAseguramientoNovedad;
    }

    public void setRegistrosAseguramientoNovedad(List<AsegRegistroNovedad> registrosAseguramientoNovedad) {
        this.registrosAseguramientoNovedad = registrosAseguramientoNovedad;
    }
    
     public List<CmAuditoriaAdjunto> getRegistrosAuditoriaAdjutoCmDetalle() {
        if (registrosAuditoriaAdjutoCmDetalle == null) {
            registrosAuditoriaAdjutoCmDetalle = new ArrayList<>();
        }
        return registrosAuditoriaAdjutoCmDetalle;
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
    

    public void setRegistrosAuditoriaAdjutoCmDetalle(List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle) {
        this.registrosAuditoriaAdjutoCmDetalle = registrosAuditoriaAdjutoCmDetalle;
    }

    public int getNumeroDetallesEnProceso() {
        return numeroDetallesEnProceso;
    }

    public void setNumeroDetallesEnProceso(int numeroDetallesEnProceso) {
        this.numeroDetallesEnProceso = numeroDetallesEnProceso;
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
    
    public List<Integer> getMotivosEspecificosSeleccionados() {
        return motivosEspecificosSeleccionados;
    }

    public void setMotivosEspecificosSeleccionados(List<Integer> motivosEspecificosSeleccionados) {
        this.motivosEspecificosSeleccionados = motivosEspecificosSeleccionados;
    }

    public int getTipoOperacionEjecutada() {
        return tipoOperacionEjecutada;
    }

    public void setTipoOperacionEjecutada(int tipoOperacionEjecutada) {
        this.tipoOperacionEjecutada = tipoOperacionEjecutada;
    }

    public CntContrato getContratoConSedes() {
        return contratoConSedes;
    }

    public void setContratoConSedes(CntContrato contratoConSedes) {
        this.contratoConSedes = contratoConSedes;
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
    
    public CmAuditoriaMasivaBean() {
        this.objeto = new CmAuditoriaMasivaModulo();
        Modulo mod = super.validarModulo(Modulo.ID_CM_AUDITORIA_MASIVA);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyCmAuditoriaMasivaFacturas= new LazyDataModel<CmFactura>() {
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
                    //agrega registros hash datos
                    if (getRegistros() != null) {
                        for (CmFactura factura : getRegistros()) {
                            if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
                                getRegistrosFacturasSeleccionadas().add(factura);
                            }
                        }
                    }
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
        getCmAuditoriaMasivaServicio().cargaInicial(this);
        listar();
    }
    
    
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }
    
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void refrescarNovedades() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUDITORIA_NOVEDADES);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void refrescarVentanaEliminarMotivosEspecificos() {
        PrimeFaces.current().resetInputs("frmEliminarMotivo");
        PrimeFaces.current().ajax().update("frmEliminarMotivo");
    }
    
    public void refrescarCmSoportes() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_SOPORTES);
        getCmAuditoriaMasivaServicio().Accion(this);
    }

 
    public void eventoCheckFactura(SelectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) == null) {
            getHashFacturasSelecionadas().put(factura.getId(), factura);
        }
    }
     
    public void eventoUnCheckFactura(UnselectEvent event) {
        CmFactura factura = (CmFactura) event.getObject();
        if (getHashFacturasSelecionadas().get(factura.getId()) != null) {
            getHashFacturasSelecionadas().remove(factura.getId());
        }
    }
    
    public void eventoCerrarVentanaOperacionMasiva() {
        getRegistrosFacturasSeleccionadas().clear();
        getHashFacturasSelecionadas().clear();
        getHashDetallesSelecionados().clear();
        PrimeFaces.current().executeScript("PF('tablaWidgetFacturas').unselectAllRows();");
        PrimeFaces.current().executeScript("PF('tablaWidgetDetallesMasivos').unselectAllRows();");
        this.getObjeto().setProcesamientoPorDetalles(false);
        desBloquarFacturaPorUsuario();
        if( super.getMensajes() != null && 
            (super.getMensajes().size() > 0 || super.isError()) ){
               procesoFinal();
        }
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
    
    public void eventoSelectCntContratos(SelectEvent event) {
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
    
    public void eventoCheckearDetalle(SelectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) == null) {
            getHashDetallesSelecionados().put(detalle.getId(), detalle);
        }
    }

    public void eventoDescheckearDetalle(UnselectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
            getHashDetallesSelecionados().remove(detalle.getId());
        }
    }

    public void eventoSeleccionarFilaDetalle(SelectEvent event) {
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

    public void eventoDesSeleccionarFilaDetalle(SelectEvent event) {
        CmDetalle detalle = (CmDetalle) event.getObject();
        if (getHashDetallesSelecionados().get(detalle.getId()) != null) {
            getHashDetallesSelecionados().remove(detalle.getId());
        }
    }

    public void eventoCheckearTodosDetallesPorPagina(ToggleSelectEvent event) {
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
    
    public void eventowSeleccionAutorizacion(SelectEvent event) {
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
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VERIFICAR_EXISTENCIA_ANEXO4);
        getCmAuditoriaMasivaServicio().Accion(this);

        if (getCmRespuestaGenerica().isEstadoRespuesta()) {
            addError(getCmRespuestaGenerica().getMensaje());
            PrimeFaces.current().ajax().update("frmListarAnexo4");
            generarMensajes(); 
        } else {
            PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').show()"); 
        }
    }

    private void habilitarCampoValorMotivo(CmAuditoriaMotivoGlosa motivoGlosa) {
        boolean esValorUnificado = true;
        try {
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
            
            if (esValorUnificado
                    && getRegistrosDetallesSeleccionadoMasivos().isEmpty()) {
                esValorUnificado = false;
            }
            
            if (esValorUnificado) {
                motivoGlosa.setValorMotivoMaximo(valorUnificadoDetalle);
            }
            motivoGlosa.setEsValorUnificado(esValorUnificado);
        } catch (Exception e) {}
    }
    
    private boolean getVersionItemsHomogeneos() {
        boolean version = this.getObjeto().getVersion();
        if (this.getObjeto().isProcesamientoPorDetalles()) {
            version = getRegistrosDetallesSeleccionadoMasivos().get(0).getCmFacturas().getVersion();
        }
        return version;
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_CREAR:
                    switch (getDoAccion()) {
                        case ACCION_GUARDAR_ADJUNTOS:
                            crearLog("Guardar Adjuntos", getObjeto().toString());
                            break;
                        case ACCION_VER_AUDITORIA_MASIVA:
                            crearLog("Ver Auditar Facturas", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
                            PrimeFaces.current().ajax().update("scrollPanelAuditoriaMasiva");
                            PrimeFaces.current().executeScript("PF('dialogoAuditoriaMasiva').show()");
                            break;
                        case ACCION_VER_GESTION_AUDITORIA_MASIVA:
                            crearLog("Ver Gestion Auditaria Facturas", getObjeto().toString());
                            break;
                        case ACCION_VER_DESCUENTO_CAPITA:
                            crearLog("Ver Descuento Capita", getObjeto().toString());
                            break;
                        case ACCION_GUARDAR_AUDITORIA_MASIVA:
                            crearLog("Guardar Auditoria Masiva", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmListaFacturasAuditar");
                            break;
                        case ACCION_GUARDAR_LEVANTAMIENTOS:
                            crearLog("Guardar Levantamientos", getObjeto().toString());
                            break;
                        case ACCION_VER_GESTION_AUDITORIA_MASIVA_DETALLES:
                            crearLog("Ver Gestion Auditoria Detalles", getObjeto().toString());
                            break;
                        case ACCION_GUARDAR_GESTION_AUDITORIA_MASIVA_DETALLES:           
                            procesoFinalGestionInsercionItems();
                            PrimeFaces.current().executeScript("PF('dialogoGestionAuditoriaMasiva').hide()"); 
                            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
                            break;
                        case ACCION_GUARDAR_LEVANTAMIENTOS_DETALLES:
                            crearLog("Guardar Levantamiento Detalles", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
                            break;
                        case ACCION_GUARDAR_AUTORIZACION_AUDITORIA_MASIVA:
                            crearLog("Guardar Autorizacion masiva", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
                            break;
                        case ACCION_REVERSAR_ESTADO_AUDITORIA:
                            procesoFinalReversarEstadoEnAuditoria();
                            PrimeFaces.current().ajax().update("frmListaFacturasAuditar");
                            break;
                        case ACCION_GUARDAR_MARCADO_GLOSA_IPS:
                            crearLog("Marcar Glosa Ips CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmListaFacturasAuditar");
                            break;
                         case ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO:
                            crearLog("Marcar Copago No Efectivo CM", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
                            break;    
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Listar Facturas", getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case ACCION_VER_FACTURA:
                            PrimeFaces.current().ajax().update("frmVerFactura");
                            PrimeFaces.current().ajax().update("scrollPanelVer");
                            PrimeFaces.current().executeScript("PF('dialogoVerFactura').show()");
                            crearLog("Ver Factura", getObjetoFactura().toString());
                            break;
                        case ACCION_VER_DETALLE_SERVICIO:
                            crearLog("Ver Auditoria Detalle ", getObjetoDetalleServicio().toString());
                            PrimeFaces.current().resetInputs("frmVerAuditoriaServicio");
                            PrimeFaces.current().ajax().update("frmVerAuditoriaServicio");
                            break;
                        case ACCION_LISTAR_CNT_CONTRATOS:
                            crearLog("Ver Descuento Masivo ", getCmAuditoriaCapitaDescuento().toString());
                            break;
                        case ACCION_CONSULTAR_PROGRAMAS_POR_AFILIADO:
                            crearLog("Ver Programas Afiliado", this.getObjetoDetalleServicio().toString());
                            break;
                        case ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO:
                            PePrograma pePrograma = Optional.ofNullable(this.getPeAfiliadosPrograma().getPePrograma()).
                                    orElse(new PePrograma());
                            crearLog("Ver Programa Afiliado",  String.valueOf("PePrograma id="+pePrograma.getId()+", codigo= "+pePrograma.getCodigoPrograma()+", nombre programa = "+pePrograma.getDescripcionPrograma()));                        
                            break;
                        case ACCION_VER_CONTRATO_SEDES:
                            crearLog("Ver Contrato Sedes", this.getObjeto().toString());
                            break;
                    }
                    break;

                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case ACCION_VER_DEVOLUCION_MASIVA:
                            crearLog("Ver Devolucion Masiva ", getCmAuditoriaDevolucion().toString());
                            PrimeFaces.current().resetInputs("frmDevolucionFactura");
                            PrimeFaces.current().ajax().update("frmDevolucionFactura");
                            PrimeFaces.current().executeScript("PF('dialogoDevolucionFactura').show()");
                            break;
                        case ACCION_GUARDAR:
                            crearLog("Guardar Devolucion Masiva ", getCmAuditoriaDevolucion().toString());
                            PrimeFaces.current().ajax().update("frmListaFacturasAuditar");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
            }
        }
        this.getObjeto().setNombreOperacionMasiva("");
        generarMensajes();
    }
    
    public void procesoFinalGestionInsercionItems() {
        StringBuilder builderDetallesAfectados = new StringBuilder();
        StringBuilder builderInsercion = new StringBuilder();
        StringBuilder builderTotal = new StringBuilder();
        try {
            for (Map.Entry<Integer, String> entry : getHashInsumoAuditoriaInsertados().entrySet()) {
                builderInsercion.append(entry.getValue());
                builderInsercion.append(" , ");
            }

            if (builderInsercion.length() > 0) {
                builderTotal.append(" Inserciones : ");
                builderDetallesAfectados.append("Detalles afectados:");
                for (CmDetalle registroDetalles : getRegistrosDetallesSeleccionadoMasivos()) {      
                    builderDetallesAfectados.append(registroDetalles.getId());
                    builderDetallesAfectados.append(",");
                }
                
                builderTotal.append(builderDetallesAfectados.toString());
                builderTotal.append(builderInsercion.toString());
            }else{
                 builderTotal.append("Se realizó operación pero sin selección de insumos de auditoria ");
            }
            
            crearLog("Guardar Auditoria Detalles", StringUtils.abbreviate(builderTotal.toString(), 16300));
        } catch (Exception e) {
        }
        getRegistrosDetallesSeleccionadoMasivos().clear();
        getHashInsumoAuditoriaInsertados().clear();
    }
    
    public void procesoFinalCierreEstadoFactura() {
        StringBuilder builderTotal = new StringBuilder();
        try {
            StringBuilder builderTotalInsumos = new StringBuilder();
            StringBuilder builderInsumosMasivos = new StringBuilder();
            StringBuilder builderFacturasProcesadas = new StringBuilder();
            builderFacturasProcesadas.append(" Id facturas procesadas : ");
            for (Integer idFactura : this.getObjeto().getIdsFacturas()) {
                builderFacturasProcesadas.append(idFactura);
                builderFacturasProcesadas.append(",");
            }

            for (CmAuditoriaConceptoContable concepto : this.getObjeto().getRegistrosConceptoContable()) {
                builderInsumosMasivos.append(concepto.toString());
                builderInsumosMasivos.append(",");
            }
            
            for (CmAuditoriaDiagnostico diagnostico : this.getObjeto().getRegistrosAuditoriaDiagnostico()) {
                builderInsumosMasivos.append(diagnostico.toString());
                builderInsumosMasivos.append(",");
            }
         
            for (CmAuditoriaMotivoGlosa motivos : this.getObjeto().getRegistrosAuditoriaMotivoGlosa()) {
                builderInsumosMasivos.append(motivos.toString());
                builderInsumosMasivos.append(",");
            }
            
            for (CmAuditoriaCapitaDescuento capita : this.getObjeto().getRegistrosAuditoriaCapitaDescuento()) {
                builderInsumosMasivos.append(capita.toString());
                builderInsumosMasivos.append(",");
            }

            if (builderInsumosMasivos.length() > 0) {
                builderTotalInsumos.append(" Insumos aplicados masivos a facturas : ");
                builderTotalInsumos.append(builderInsumosMasivos.toString());
            }else{
                builderTotalInsumos.append("Se realizó operación pero sin selección de insumos de auditoria ");
            }
            
            builderTotal.append(builderFacturasProcesadas.toString());
            builderTotal.append(builderTotalInsumos.toString());
           
            crearLog("Guardar Estado Auditoria", StringUtils.abbreviate(builderTotal.toString(), 16300));
        } catch (Exception e) {
        }
      
    }
    
    public void procesoFinalReversarEstadoEnAuditoria() {
        try {
            StringBuilder builderTotalInsumos = new StringBuilder();
            StringBuilder builderInsercion = new StringBuilder();
            for (CmFactura cmFactyra : getRegistrosFacturasSeleccionadas()) {
                builderInsercion.append(cmFactyra.toString());
                builderInsercion.append(",");
            }
            if (builderInsercion.length() > 0) {
                builderTotalInsumos.append("Factura cambiada: ");
                builderTotalInsumos.append(builderInsercion.toString());
            }else{
                 builderTotalInsumos.append("Se realizó operación pero sin selección de insumos de auditoria ");
            }
            crearLog("Reversar Estado En Auditoria", builderTotalInsumos.toString());
        } catch (Exception e) {
        }
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
     
    public void verFactura(int idFactura) {
        this.getObjetoFactura().setId(idFactura);
        inicializarTablaDetallesPorFactura(idFactura);
        listarAdjuntosPorFactura(idFactura);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_FACTURA);
        getCmAuditoriaMasivaServicio().Accion(this);
        procesoFinal();
    }
    
      public void verAuditoriaDetalleServicio(int idServicio, int idVentanaLlamado) {
               
        this.getObjetoDetalleServicio().setId(idServicio);
        listarConceptosContablesPorDetalleServicio(idServicio);
        listarDiagnosticosPorDetalleServicio(idServicio);
        listarMotivosGlosaPorDetalleServicio(idServicio);
        listarAutorizacionesPorDetalleServicio(idServicio);
        listarAdjuntosPorDetalles(idServicio);
        listarDescuentoCapita();
       
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_DETALLE_SERVICIO);
        getCmAuditoriaMasivaServicio().Accion(this);
        
        if( ID_VENTANA_VER_DETALLES == idVentanaLlamado ){
            PrimeFaces.current().executeScript("PF('dialogoVerAuditoriaServicio').show()");
        }
        procesoFinal();
    }
      
    public void verCrearConceptoContable() {
        setCmAuditoriaConceptoContable(new CmAuditoriaConceptoContable());
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_CONSULTAR_UBICACION_PRESTADOR_SEDE);
        getCmAuditoriaMasivaServicio().Accion(this);
        asignarListaConceptosSegunRegimen();
        PrimeFaces.current().resetInputs("frmAgregarConcepto");
        PrimeFaces.current().ajax().update("frmAgregarConcepto");
        PrimeFaces.current().executeScript("PF('dialogoAgregarConcepto').show()");
    }
    
    public void verCrearMotivo() {
        CmAuditoriaMotivoGlosa motivoGlosa = new CmAuditoriaMotivoGlosa();
        setCmAuditoriaMotivoGlosa(motivoGlosa);
        limpiarSelectoresMotivoGlosa();
        habilitarCampoValorMotivo(motivoGlosa);
        boolean esValido;

        esValido = validarVersionesFacturaMotivosGlosa();

        if (esValido) {
            asignarSelectMotivoGlosaPadre();
           
            PrimeFaces.current().resetInputs("frmAgregarMotivo");
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
            PrimeFaces.current().executeScript("PF('dialogoAgregarMotivo').show()");
        }

        procesoFinal();
    }
          
    public void verListaMaDiagnosticos() {
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
    }
    
     public void verAuditoriaMasivaFacturas(){   
        boolean esValidoProceso = true;
       
         limpiarListasAuxiliares();

         if (getHashFacturasSelecionadas().isEmpty()) {
             this.addError("Para realizar la auditoría debe seleccionar facturas.");
             esValidoProceso = false;
         }
         
         if (esValidoProceso) {
             if (getHashFacturasSelecionadas().size() <= 1) {
                 this.addError("Para realizar la auditoría debe seleccionar mas de una factura.");
                 esValidoProceso = false;
             }
         }

         if (esValidoProceso) {
             esValidoProceso = validarIgualNumeroNitEnFacturas();
         }
         
         if (esValidoProceso) {
             asignarAtributosParaProcesamientoMasivo();
             if ( ! this.getObjeto().isContratoCapitaPGP()) {
                 esValidoProceso = validarExistenciaDetallesEnFacturas();
             }
         }

         if (esValidoProceso) {
             esValidoProceso = validarCantidadDetallesLimite();
         }

         if (esValidoProceso) {
             esValidoProceso = bloquarFacturaPorUsuario();
         }

         if (esValidoProceso) {
             esValidoProceso = validarIgualVersionEnFacturas();
         }

        if (esValidoProceso && !this.isError()) {
            getObjetoFactura().setId(0);
            getRegistrosDetallesSeleccionadoMasivos().clear();
            getHashDetallesSelecionados().clear();
            inicializarTablaDetallesMultiFactura(obtenerIdsFacturasProcesoMasivo());
     
            super.setAccion(Url.ACCION_CREAR);
            super.setDoAccion(ACCION_VER_AUDITORIA_MASIVA);
        }
        procesoFinal();    
    }
    
     public void verDevolucionMasiva() {
         boolean esValidoProceso = true;
         setCmAuditoriaDevolucion(new CmAuditoriaDevolucion());

         if (getHashFacturasSelecionadas().isEmpty()) {
             this.addError("Para realizar la devolución debe seleccionar facturas");
             esValidoProceso = false;
         }
         
        if (esValidoProceso) {
             if (getHashFacturasSelecionadas().size() <= 1) {
                 this.addError("Para realizar la devolución debe seleccionar mas de una factura.");
                 esValidoProceso = false;
             }
         }

         if (esValidoProceso) {
             esValidoProceso = validarIgualPrestadorEnFacturas();
         }
         
         if (esValidoProceso) {
           esValidoProceso = validarIgualVersionEnFacturas();
         }
         
         if (esValidoProceso && !this.isError()) {
             asignarAtributosParaProcesamientoMasivo();
             asignarSelectMotivoPadreDevolucion(); 
         }

         super.setAccion(Url.ACCION_ADICIONAL_1);
         super.setDoAccion(ACCION_VER_DEVOLUCION_MASIVA);

         procesoFinal();
    }
     
    public void verGestionAuditoriaMasiva() {
        this.setAplicaRecobro(false);
        this.setAccion(ACCION_CREAR);
        this.setDoAccion(ACCION_VER_GESTION_AUDITORIA_MASIVA);
        PrimeFaces.current().resetInputs("frmGestionAuditoriaMasiva");
        PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva");
        PrimeFaces.current().executeScript("PF('dialogoGestionAuditoriaMasiva').show()");
        procesoFinal();
    }
    
    public void verGestionAuditoriaAutorizacionesMasiva() {
        PrimeFaces.current().resetInputs("frmGestionAuditoriaMasivaAutorizacion");
        PrimeFaces.current().ajax().update("frmGestionAuditoriaMasivaAutorizacion");
        PrimeFaces.current().executeScript("PF('dialogoGestionAuditoriaMasivaAutorizacion').show()");
    }
    
    public void verDescuentoCapitaMasivo() {      
        this.setAccion(ACCION_CREAR);
        this.setDoAccion(ACCION_VER_DESCUENTO_CAPITA);
        PrimeFaces.current().resetInputs("frmListaAuditoriaCapitaDescuento");
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento");
        PrimeFaces.current().executeScript("PF('dialogoCapitaDescuento').show()");   
        procesoFinal();
    }
    
    public void verListaCntContratros() {
        inicializarTablaCntContratos();
        if (!this.isError()) {
            limpiarFiltrosContratos();
            PrimeFaces.current().executeScript("PF('dialogoListarCntContratos').show()");
            PrimeFaces.current().executeScript("PF('tablaWidgetListarCntContratos').unselectAllRows();");
        }
        procesoFinal();
    }
    
    public void verSeleccionOperacionMasiva(){
            setCmSelectorOperacionaMasiva(new CmAuditoriaSelectorOperacionMasiva());
            if(this.getObjeto().isProcesamientoPorDetalles()){
                limpiarListasAuditoria();
            }    
            this.getObjeto().setProcesamientoPorDetalles(false);
            PrimeFaces.current().resetInputs("frmVerSeleccionOperacionMasiva");
            PrimeFaces.current().ajax().update("frmVerSeleccionOperacionMasiva");
            PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').show()");
    }
    
    public void verSeleccionOperacionMasivaDetalles() {
        
        setCmSelectorOperacionaMasivaDetalles(new CmAuditoriaSelectorOperacionMasiva());
        getRegistrosDetallesSeleccionadoMasivos().clear();
        setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values()));  
        if (getRegistrosDetallesSeleccionadoMasivos().isEmpty()) {
            this.addError("Para realizar la operación debe seleccionar items o servicios");
        }

        if (!this.isError()) {
            this.getObjeto().setProcesamientoPorDetalles(true);
            PrimeFaces.current().resetInputs("frmVerSeleccionOperacionMasivaDetalles");
            PrimeFaces.current().ajax().update("frmVerSeleccionOperacionMasivaDetalles");
            PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasivaDetalles').show()");
        } else {
            generarMensajes();
        }

    }
    
     public void verProgramasPorAfiliado(CmDetalle detalle){
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_CONSULTAR_PROGRAMAS_POR_AFILIADO);
        setAfiliadoCompleto(new AsegAfiliado());
        setObjetoDetalleServicio(detalle);
        inicializarTablaAfiliadoProgramas(detalle.getDocumento(), String.valueOf(detalle.getMaeTipoDocumentoId()));
        if (!isError()) {
            PrimeFaces.current().ajax().update("TabAuditoria:frmVerProgramasTab");
        }
        procesoFinal();
    }
    
    public void verProgramaEspecificoPorAfiliado(PeAfiliadosPrograma programaAfiliadoIn) {
        if (programaAfiliadoIn != null) {
            setPeAfiliadosPrograma(programaAfiliadoIn);
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO);
            getCmAuditoriaMasivaServicio().Accion(this);
            if (!isError()) {
                PrimeFaces.current().ajax().update("frmVerProgramaEspecifico");
                PrimeFaces.current().executeScript("PF('dialogoVerProgramaEspecifico').show()");
            }
            procesoFinal();
        }
    }
    
    public void verListarAnexo4() {
        try {
            inicializarTablaAnexo4();
            DataTable tablaListarAnexo4 = (DataTable) FacesContext.getCurrentInstance().
                    getViewRoot().findComponent("frmListarAnexo4:tablaListarAnexo4");
            tablaListarAnexo4.reset();
            if(isError()){
              generarMensajes();
            }
            PrimeFaces.current().resetInputs("frmListarAnexo4");
            PrimeFaces.current().ajax().update("frmListarAnexo4");
            PrimeFaces.current().executeScript("PF('dialogoListarAnexo4').show()");
        } catch (Exception e) {
            addError("Error al listar anexo 4 asociados :"+e.toString());
            generarMensajes();
        }
    }
    
    public void verListarAnexo4Items(int idAnexo4) {
        try {
            getAuAnexo4().setId(idAnexo4);
            inicializarTablaAnexo4Items();
            DataTable tablaListarAnexo4 = (DataTable) FacesContext.getCurrentInstance().
                    getViewRoot().findComponent("frmListarAnexo4Items:tablaListarAnexo4Items");
            tablaListarAnexo4.reset();
            if(isError()){
              generarMensajes();
            }
            PrimeFaces.current().resetInputs("frmListarAnexo4Items");
            PrimeFaces.current().ajax().update("frmListarAnexo4Items");
            PrimeFaces.current().executeScript("PF('dialogoListarAnexo4Items').show()");
           
        } catch (Exception e) {
            addError("Error al listar anexo 4 items asociados :" + e.toString());
            generarMensajes();
        }
    }
    
    public void verContratoSedes(int id) {
        setContratoConSedes(new CntContrato());
        getContratoConSedes().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_CONTRATO_SEDES);
        getCmAuditoriaMasivaServicio().Accion(this);
        if (!isError()) {
            PrimeFaces.current().resetInputs("frmVerContratosSedes");
            PrimeFaces.current().ajax().update("frmVerContratosSedes");
            PrimeFaces.current().executeScript("PF('dialogoVerContratoSedes').show()");
        }
        procesoFinal();
    }
    
     public void verSoportes(int id) {
        inicializarTablaSoportes(id);
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

    public void listarMotivosEspecificosRelacionados() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVO_ESPECIFICO_HIJO);
        limpiarSelectMotivoAplicativo();
        getCmAuditoriaMasivaServicio().Accion(this);
        if (this.isError()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
        }
    }
    
     public void retirarConceptoContable(int idPosConcepto) {
        for (int i = 0; i < getRegistrosConceptoContable().size(); i++) {
            CmAuditoriaConceptoContable concepto = getRegistrosConceptoContable().get(i);
            if (concepto.getPosInsertar() != null && concepto.getPosInsertar() == idPosConcepto) {
                getRegistrosConceptoContable().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:tablaConceptos");
    }
     
    public void retirarDiagnostico(int idPosAutorizacion) {
        for (int i = 0; i < getRegistrosAuditoriaDiagnostico().size(); i++) {
            CmAuditoriaDiagnostico diagnostico = getRegistrosAuditoriaDiagnostico().get(i);
            if (diagnostico.getPosInsertar() != null && diagnostico.getPosInsertar() == idPosAutorizacion) {
                getRegistrosAuditoriaDiagnostico().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:panelDiagnostico");
    }
       
    public void retirarMotivo(int idPosMotivo) {
        for (int i = 0; i < getRegistrosAuditoriaMotivoGlosa().size(); i++) {
            CmAuditoriaMotivoGlosa motivo = getRegistrosAuditoriaMotivoGlosa().get(i);
            if (motivo.getPosInsertar() != null && motivo.getPosInsertar() == idPosMotivo) {
                getRegistrosAuditoriaMotivoGlosa().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:tablaMotivos");
    }
     
     public void retirarCapitaDescuento(int idPosCapitaDesc) {
        for (int i = 0; i < getRegistrosAuditoriaCapitaDescuento().size(); i++) {
            CmAuditoriaCapitaDescuento capitaDesc = getRegistrosAuditoriaCapitaDescuento().get(i);
            if (capitaDesc.getPosInsertar() != null && capitaDesc.getPosInsertar() == idPosCapitaDesc) {
                getRegistrosAuditoriaCapitaDescuento().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento");
        PrimeFaces.current().ajax().update("frmListaAuditoriaCapitaDescuento:tablaCapitaDescuento");
    }
     
     public void retirarAutorizacion(int idPosAutorizacion) {
        for (int i = 0; i < getRegistrosAuditoriaAutorizacion().size(); i++) {
            CmAuditoriaAutorizacion autorizacion = getRegistrosAuditoriaAutorizacion().get(i);
            if (autorizacion.getPosInsertar() != null && autorizacion.getPosInsertar() == idPosAutorizacion) {
                getRegistrosAuditoriaAutorizacion().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmGestionAuditoriaMasivaAutorizacion:panelVerAutorizaciones");
        PrimeFaces.current().ajax().update("frmGestionAuditoriaMasivaAutorizacion:tablaAutorizaciones");

    }

      public void inicializarTablaDetallesPorFactura(int idFactura) {
        this.setParamConsultaDetallesPorFactura(new ParamConsulta());
        getParamConsultaDetallesPorFactura().setParametroConsulta1(idFactura);
        lazyCmAuditoriaDetalles = new LazyDataModel<CmDetalle>() {
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
                refrescarDetalles();
                lista = getRegistrosAuditoriaDetalles();
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
      
      public void inicializarTablaDetallesMultiFactura(String idFacturas) {
        this.setParamConsultaDetallesPorFactura(new ParamConsulta());
        getParamConsultaDetallesPorFactura().setParametroConsulta1(idFacturas);
        lazyCmAuditoriaDetalles = new LazyDataModel<CmDetalle>() {
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
                lista = getRegistrosAuditoriaDetalles();
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
   
    public void refrescarDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_POR_FACTURA);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
     public void refrescarDetallesMultiFactura() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_MULTI_FACTURA);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void refrescarCntContratos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CNT_CONTRATOS);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void listarAdjuntosPorFactura(int idFactura) {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_POR_FACTURA);
        setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(idFactura);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void listarAdjuntosPorDetalles(int idCmDetalle) {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_CMDETALLES);
        setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta3(idCmDetalle);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void listarDiagnosticosPorDetalleServicio(int idServicio) {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DIAGNOSTICOS_POR_SERVICIO);
        setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(idServicio);
        getCmAuditoriaMasivaServicio().Accion(this);
    }

    public void listarConceptosContablesPorDetalleServicio(int idServicio) {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CONCEPTOS_POR_SERVICIO);
        setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(idServicio);
        getCmAuditoriaMasivaServicio().Accion(this);
    }

    public void listarMotivosGlosaPorDetalleServicio(int idServicio) {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_GLOSA_POR_SERVICIO);
        setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(idServicio);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void listarTodosDetalelMultiFactura() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_TODOS_DETALLES_MULTI_FAC);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
     public void listarDescuentoCapita() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DESCUENTO_CAPITA);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
    public void refrescarListaAnexos4() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ANEXOS_4);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
    
      public void refrescarListaAnexos4Items() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_ANEXOS4_ITEMS);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
      
    public void refrescarAfiliadoPrograma() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_CONSULTAR_PROGRAMAS_POR_AFILIADO);
        getCmAuditoriaMasivaServicio().Accion(this);
    }

    public void listarAutorizacionesPorDetalleServicio(int idServicio) {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_AUTORIZACIONES_POR_SERVICIO);
        setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(idServicio);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
      
    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
      public void mostrarMotivosEspecificosDevolucion() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_ESPECIFICOS_DEVOLUCION);
        getCmAuditoriaMasivaServicio().Accion(this);
        if (this.isError()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmDevolucionFactura");
        }
    }
    
    public void mostrarMotivosAplicacionRelacionados() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVO_APLICACION_HIJO);
        this.getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().setId(getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().getId());
        getCmAuditoriaMasivaServicio().Accion(this);
         //asignacion de motivo de aplicacion si es unico
        if(this.getListaMaeMotivosAplicacion().size() == 1){
          this.getCmAuditoriaMotivoGlosa().setMaestroMotivoAplicacion(this.getListaMaeMotivosAplicacion().get(0));
        }
        if (this.isError()) {
            generarMensajes();
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
        }
    } 
    
    public boolean verificarFacturaAuditable(CmFactura factura) {
        
        boolean facturaEditable = factura.getEstadoFactura() != CmFactura.ESTADO_FACTURA_SIN_AUDITORIA;
        Integer idUsuario = getConexion().getUsuario().getId();
        if( CmFactura.ESTADO_FACTURA_EN_AUDITORIA == factura.getEstadoFactura()){
            if( Objects.equals(factura.getUsuarioGestiona().getId(), idUsuario) || 
                Objects.equals(factura.getUsuarioLider().getId(), idUsuario) ){
                facturaEditable = false;
            }
        } 
        return facturaEditable;
    }
    
    public void verMotivosGlosaUsados() {
        limpiarMotivosEspecificosEncontrados();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_MOTIVOS_MULTI_DETALLES);
        getCmAuditoriaMasivaServicio().Accion(this);
        if (validarExisteciaMotivosEspecificos()) {
            refrescarVentanaEliminarMotivosEspecificos();
            PrimeFaces.current().executeScript("PF('dialogoEliminarMotivo').show()");
        }
        procesoFinal();
    }

    public boolean validarInsumosDetallesParaCerrarAuditoria() {
        boolean sonDetallesValidos = true;
        String numeroFacturado ;
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_DETALLES_FALTOS_DE_INSUMOS);
        getCmAuditoriaMasivaServicio().Accion(this);
        
        if(!getRegistrosTodosAuditoriaDetallesMultifactura().isEmpty() && !isError()){
            for (CmDetalle cmDetalle : getRegistrosTodosAuditoriaDetallesMultifactura()) {
                if(!cmDetalle.getAplicaConcepto()|| !cmDetalle.getAplicaDx() ){
                  sonDetallesValidos = false;
                  numeroFacturado = cmDetalle.getCmFacturas().getNumeroFacturado();
                  this.addError("Si no selecciona conceptos y diagnósticos como operación masiva, todos los detalles deben tener conceptos y diagnósticos. La factura de número facturado : "+numeroFacturado+" posee detalles sin conceptos contables o diagnósticos,"
                          + "Id detalle : "+cmDetalle.getId());
                  break;
                }
            }
        }
        
        return sonDetallesValidos;
    }
    
    public void validarFacturasParaIniciarCierreAuditoria() {
        int tipoAuditoria = getObjeto().getTipoAuditoria(); 
        if (tipoAuditoria == 0) {
            this.addError("Seleccione un estado de auditoría válido.");
            generarMensajes();
        } else {
            if (CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA == tipoAuditoria) {
                validarEstadoCierre();
            }
        }
    }
    
    public void validarEstadoCierre() {
        
        boolean contratoCapitaPGP = this.getObjeto().isContratoCapitaPGP();
        boolean esDigilenciadoTodosDetalles = false;
        if (!contratoCapitaPGP && (this.getRegistrosConceptoContable().isEmpty()
                && this.getRegistrosAuditoriaDiagnostico().isEmpty())) {
            esDigilenciadoTodosDetalles = validarInsumosDetallesParaCerrarAuditoria();
        }
        
        if (!contratoCapitaPGP && !this.isError() && !esDigilenciadoTodosDetalles && 
                (this.getRegistrosConceptoContable().isEmpty() || this.getRegistrosAuditoriaDiagnostico().isEmpty())) {
              this.addError("Si desea realizar la operación masiva debe ingregar conceptos contables y diagnósticos.");
        }
            
        if (!this.isError()) {
            if (!contratoCapitaPGP) {
                if (!validacionSumatoriaConceptosContables(getRegistrosConceptoContable())) {
                    this.addError("Los conceptos contables deben sumar 100% para continuar el proceso");
                }
            }
        }
        if (!this.isError()) {
            super.setAccion(ACCION_VER);
            super.setDoAccion(ACCION_VER_FACTURAS_SIN_AUTORIZACION);
            getCmAuditoriaMasivaServicio().Accion(this);
            if (!this.isError()) {
                if (this.getRegistrosFacturasSinAutorizaciones().isEmpty()) {
                    guardarEstadoCierre();
                } else {
                    PrimeFaces.current().resetInputs("frmConfirmarCierre");
                    PrimeFaces.current().ajax().update("frmConfirmarCierre");
                    PrimeFaces.current().executeScript("PF('dialogoConfirmarCierreFaltanAutorizaciones').show()");
                }
            }
        }
        if (this.isError()) {
            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
            generarMensajes();
        }
    }
    
    
    public boolean validarEjecucionLevantamientos(int idTipoLevantamientos) {
        boolean esValidaOperacion = true;
        this.setAccion(ACCION_CREAR);
        this.setDoAccion(ACCION_VALIDAR_LEVANTAMIENTOS);
        getCmAuditoriaMasivaServicio().Accion(this);
       if(this.isError() || !this.isEjecucionExitosaOperacion()){
          esValidaOperacion = false;
        }
        return esValidaOperacion;
    }
    
    public boolean validarIgualNumeroRadicadoEnFacturas() {
        boolean esValidoProceso = true;
        Integer numeroRadicado = null;
        for (Map.Entry<Integer, CmFactura> entry : getHashFacturasSelecionadas().entrySet()) {
            CmFactura factura = entry.getValue();
            Integer numeroRadicadoPorFactura = factura.getNumeroRadicado();
            if (numeroRadicado == null) {
                numeroRadicado = numeroRadicadoPorFactura;
            }
            if (!Objects.equals(numeroRadicado, numeroRadicadoPorFactura)) {
                this.addError("Para realizar la auditoría las facturas deben ser del mismo número radicado,"
                        + " radicado encontrado : " + numeroRadicado + ", radicado diferente : " + numeroRadicadoPorFactura);
                esValidoProceso = false;
                break;
            }
        }

        return esValidoProceso;
    }
    
    public boolean validarIgualPrestadorEnFacturas() {
        boolean esValidoProceso = true;
        List<CmFactura>  facturas = new ArrayList<>(getHashFacturasSelecionadas().values());
        boolean sonIguales = facturas.stream()
                .map(CmFactura::getNit).distinct().count()==1;
        
        if(!sonIguales){
             List<String> numerosPrestadorDistintos = facturas.stream()
                .map(CmFactura::getNit)
                .distinct()
                .collect(Collectors.toList());
             this.addError("Las facturas deben ser del mismo prestador, prestadores escontrados( "+numerosPrestadorDistintos.toString()+" )");
             esValidoProceso = false;
        }

        return esValidoProceso;
    }
    
    public boolean validarExistenciaDetallesEnFacturas() {
        boolean esValidoProceso = true;
        for (Map.Entry<Integer, CmFactura> entry : getHashFacturasSelecionadas().entrySet()) {
            CmFactura factura = entry.getValue();
            int cantidadDetalles = factura.getCantidadDetalles();
            if (cantidadDetalles == 0) {
               this.addError("Para realizar la auditoría las facturas deben tener detalles, la factura de número facturado : " +
                       factura.getNumeroFacturado() + ", no tiene. ");
                esValidoProceso = false;
                break;
            }
                
        }

        return esValidoProceso;
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
    
    public boolean validarDetallesSeleccionadosMismoUsuario() {
        boolean esValido = true;
        String documentoBase = null;
        for (CmDetalle detalle : getRegistrosDetallesSeleccionadoMasivos()) {
            if (documentoBase == null) {
                documentoBase = detalle.getDocumento();
            }
            if (!Objects.equals(documentoBase, detalle.getDocumento())) {
                this.addError("Para la auditoría de autorizaciones el número de documento de usuario en todos los detalles o servicios debe ser el mismo."
                        + " Número encontrado : " + documentoBase + ", Número diferente : " + detalle.getDocumento());
                esValido = false;
                break;
            }
        }
        return esValido;
    }
    
    public boolean validarIgualNumeroNitEnFacturas() {
        boolean esValidoProceso = true;
        String nitGlobal = null;
        for (Map.Entry<Integer, CmFactura> entry : getHashFacturasSelecionadas().entrySet()) {
            CmFactura factura = entry.getValue();
            String nitComparado = factura.getNit();
            if (nitGlobal == null) {
                nitGlobal = nitComparado;
            }
            if (!Objects.equals(nitGlobal, nitComparado)) {
                this.addError("Para realizar la auditoría las facturas deben tener el mismo número Nit,"
                        + " Nit encontrado : " + nitGlobal + ", Nit diferente : " + nitComparado);
                esValidoProceso = false;
                break;
            }
        }
        return esValidoProceso;
    }
    
    public boolean validarCantidadDetallesLimite() {
        boolean esValidoProceso = true;
        final int CANTIDAD_DETALLES_LIMITE_PROCESO = 10000;
        this.setParamConsultaDetallesPorFactura(new ParamConsulta());
        String idsFacturas = obtenerIdsFacturasProcesoMasivo();
        getParamConsultaDetallesPorFactura().setParametroConsulta1(idsFacturas);
        if (idsFacturas != null && !"".equals(idsFacturas)) {
            super.setAccion(Url.ACCION_VER);
            super.setDoAccion(ACCION_CONSULTAR_CANTIDAD_DETALLES_PROCESO);
            getCmAuditoriaMasivaServicio().Accion(this);
            if (getNumeroDetallesEnProceso() > 0 && getNumeroDetallesEnProceso() > CANTIDAD_DETALLES_LIMITE_PROCESO) {
                this.addError("Para realizar la auditoría la cantidad de detalles permitidos a procesar debe ser menor o igual a " + CANTIDAD_DETALLES_LIMITE_PROCESO);
                esValidoProceso = false;
            }
        }
        return esValidoProceso;
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
    
     public boolean validarIgualVersionEnFacturas() {
        boolean esValidoProceso = true;
        List<CmFactura>  facturas = new ArrayList<>(getHashFacturasSelecionadas().values());
        boolean sonIguales = facturas.stream()
                .map(CmFactura::getVersionStr).distinct().count()==1;
        
        if(!sonIguales){
             List<String> versionesEncontradas = facturas.stream()
                .map(CmFactura::getVersionStr)
                .distinct()
                .collect(Collectors.toList());
             this.addError("Las facturas deben ser de la misma versión, versiones encontradas( "+versionesEncontradas.toString()+" )");
             esValidoProceso = false;
        }

        return esValidoProceso;
    }
  
    public void retirarAdjunto(int idPosAutorizacion) {
        for (int i = 0; i < getAdjuntosParaGuardar().size(); i++) {
            CmAuditoriaAdjunto adjunto = getAdjuntosParaGuardar().get(i);
            if (adjunto.getPosInsertar() != null && adjunto.getPosInsertar() == idPosAutorizacion) {
                getAdjuntosParaGuardar().remove(i);
                if (adjunto.isGuardadoEnDisco()) {
                        this.getObjeto().getListaAdjuntosEliminar().add(adjunto.getPosInsertar());
                }
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmAuditoriaMasiva:tablaAdjuntos");
    }
    
    public void retirarAdjuntoCmDetalle(int idPosAdjunto) {
         
        List<CmAuditoriaAdjunto> adjuntosPorDetalle = this.getRegistrosAuditoriaAdjutoCmDetalle();
        
        for (int i = 0; i <adjuntosPorDetalle.size(); i++) {
            CmAuditoriaAdjunto adjunto = adjuntosPorDetalle.get(i);
            if (adjunto.getPosInsertar() != null && adjunto.getPosInsertar() == idPosAdjunto) {
                adjuntosPorDetalle.remove(i);
                if (adjunto.isGuardadoEnDisco()) {
                       // this.getObjeto().getListaAdjuntosEliminar().add(adjunto.getPosInsertar());
                }
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmAuditoriaMasiva:tablaAdjuntos");
    }
    
    public void agregarAdjunto(FileUploadEvent event) throws IOException {
        String ruta = PropApl.getInstance().get(PropApl.CM_RUTA_AUDITORIA_DESCARGA);
        try {
            UploadedFile archivoCarga = event.getFile();
            String nombre = archivoCarga.getFileName();
            
            if (validarLongitudNombreAdjunto(nombre)) {
                InputStream inputStream = archivoCarga.getInputStream();
                int idInsertar = 1;
                for (CmAuditoriaAdjunto adj : getAdjuntosParaGuardar()) {
                    if (adj.getPosInsertar() != null && adj.getPosInsertar() >= idInsertar) {
                        idInsertar = adj.getPosInsertar() + 1;
                    }
                }
                CmAuditoriaAdjunto adj = new CmAuditoriaAdjunto(CmAuditoriaAdjunto.TIPO_FACTURA, nombre, ruta, inputStream);
                auditoriaGuardar(adj);
                adj.setPosInsertar(idInsertar);
                getAdjuntosParaGuardar().add(adj);
            }

            PrimeFaces.current().ajax().update("frmAuditoriaMasiva:tablaAdjuntos");
            PrimeFaces.current().ajax().update("frmAuditoriaMasiva:panelFacturaParaAuditar");

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
                CmAuditoriaAdjunto adj = new CmAuditoriaAdjunto(CmAuditoriaAdjunto.TIPO_DETALLE, nombre, ruta, inputStream);
                auditoriaGuardar(adj);
                adj.setPosInsertar(idInsertar);
                getRegistrosAuditoriaAdjutoCmDetalle().add(adj);
            }

            PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:tablaAdjuntosCmDetalles");
            PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:panelVerAdjuntosCmDetalles");

        } catch (IOException ex) {
            this.addError(ex.toString());
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
            PrimeFaces.current().resetInputs("frmAgregarConcepto");
            PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:tablaConceptos"); 
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
            PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:tablaDiagnosticosCrear");
            PrimeFaces.current().executeScript("PF('dialogoAgregarDiagnostico').hide()");
        } else {
            PrimeFaces.current().ajax().update("frmAgregarDiagnostico");
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
            PrimeFaces.current().resetInputs("frmAgregarMotivo");
            PrimeFaces.current().ajax().update("frmGestionAuditoriaMasiva:tablaMotivos");
            PrimeFaces.current().executeScript("PF('dialogoAgregarMotivo').hide()");
        } else {
            PrimeFaces.current().ajax().update("frmAgregarMotivo");
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
            PrimeFaces.current().resetInputs("frmGestionAuditoriaMasivaAutorizacion");
            PrimeFaces.current().ajax().update("frmGestionAuditoriaMasivaAutorizacion:panelVerAutorizaciones");
            PrimeFaces.current().ajax().update("frmGestionAuditoriaMasivaAutorizacion:tablaAutorizaciones");
            PrimeFaces.current().executeScript("PF('dialogoListarAnexo4').hide()");
            PrimeFaces.current().executeScript("PF('dialogoEditarAutorizacion').hide()");

        } else {
            generarMensajes();
        }
    }
    
    public void guardarAdjuntos() {

        if (this.getAdjuntosParaGuardar().size() > 0 || 
            this.getObjeto().getListaAdjuntosEliminar().size() >0 ) {
            super.setAccion(ACCION_CREAR);
            super.setDoAccion(ACCION_GUARDAR_ADJUNTOS);
            getCmAuditoriaMasivaServicio().Accion(this);
            if (!isError()) {
                this.addMensaje("Se han agregado los adjuntos a las facturas" );
                procesoFinal();
            } else {
                generarMensajes();
            }
        } else {
            this.addError("Por favor ingrese un adjunto para realizar la operación");
            generarMensajes();
        }

    }
    
    public void guardarEstadoCierre() {
        PrimeFaces.current().executeScript("PF('dialogoConfirmarCierreFaltanAutorizaciones').hide()");
        this.getObjeto().setTipoOperacionMasiva(CmAuditoriaMasivaModulo.TIPO_OPERACION_CIERRE_AUDITORIA_MASIVA);
                
        this.getObjeto().setRegistrosConceptoContable(this.getRegistrosConceptoContable());
        this.getObjeto().setRegistrosAuditoriaDiagnostico(this.getRegistrosAuditoriaDiagnostico());
        this.getObjeto().setRegistrosAuditoriaMotivoGlosa(this.getRegistrosAuditoriaMotivoGlosa());
        this.getObjeto().setRegistrosAuditoriaCapitaDescuento(this.getRegistrosAuditoriaCapitaDescuento());
        this.getObjeto().setRegistrosAuditoriaAdjutoCmDetalle(this.getRegistrosAuditoriaAdjutoCmDetalle());
        this.getObjeto().setAplicaRecobro(this.getAplicaRecobro());
        
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_GUARDAR_AUDITORIA_MASIVA);
        getCmAuditoriaMasivaServicio().Accion(this);
        if (!this.isError()) {
            addMensaje("El proceso de auditoría masiva se esta realizando "
                    + "para las facturas seleccionadas de número radicado : " +
                       this.getObjeto().getNumeroRadicado()+" por favor espere.");
            PrimeFaces.current().executeScript("PF('dialogoAuditoriaMasiva').hide()");  
            procesoFinalCierreEstadoFactura();
        } else {
            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
            generarMensajes();
        }
    }

    public void guardarDevolcionFactura() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);     
        getCmAuditoriaDevolucion().castMaeMotivoDevolucion(getCmAuditoriaDevolucion().getMaestroMotivoDevolucion());
        getCmAuditoriaDevolucion().castMaeMotivoDevolucionEspecifico(getCmAuditoriaDevolucion().getMaestroMotivoDevolucionEspecifico());         
        this.getObjeto().setCmAuditoriaDevolucion(this.getCmAuditoriaDevolucion());
        this.getObjeto().setTipoOperacionMasiva(CmAuditoriaMasivaModulo.TIPO_OPERACION_DEVOLUCION_AUDITORIA_MASIVA );
        this.getObjeto().setObservacion(this.getCmAuditoriaDevolucion().getObservacion());
        getCmAuditoriaMasivaServicio().Accion(this);
        if (!this.isError()) {
            addMensaje("El proceso de devolución masiva se esta realizando "
                    + "para las facturas seleccionadas del prestador con número de Nit : "
                    + this.getObjeto().getNit() + " por favor espere.");
            PrimeFaces.current().executeScript("PF('dialogoDevolucionFactura').hide()");  
        } else {
            PrimeFaces.current().ajax().update("frmDevolucionFactura");
            generarMensajes();
        }
    }
    
    public void guardarLevantamientos() {
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_GUARDAR_LEVANTAMIENTOS);
        getCmAuditoriaMasivaServicio().Accion(this);
        if ( !this.isError() || this.isEjecucionExitosaOperacion() ) {
            String nombreProceso = CmAuditoriaSelectorOperacionMasiva.getTipoOperacionStr(
                                     this.getCmSelectorOperacionaMasiva().getTipoOperacion());
            this.getObjeto().setNombreOperacionMasiva(nombreProceso);
            this.addMensaje("El proceso de "+nombreProceso+" para radicado:"+
                    this.getObjeto().getNumeroRadicado() + " se esta realizando.");
            procesoFinal();
        }else{
           PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
           generarMensajes();
        }
    }
    
    public void guardarLevantamientosDetalles() {
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_GUARDAR_LEVANTAMIENTOS_DETALLES);
        getCmAuditoriaMasivaServicio().Accion(this);
        if ( !this.isError() ) {
            String nombreProceso = CmAuditoriaSelectorOperacionMasiva.getTipoOperacionStr(
                                     this.getCmSelectorOperacionaMasivaDetalles().getTipoOperacion());
            this.getObjeto().setNombreOperacionMasiva(nombreProceso);
            this.addMensaje("El proceso de "+nombreProceso+" para radicado:"+
                    this.getObjeto().getNumeroRadicado() + " se esta realizando.");
            getRegistrosDetallesSeleccionadoMasivos().clear();
            getHashDetallesSelecionados().clear();
            procesoFinal();
        }else{
           PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
           generarMensajes();
        }
    }
    
    public void guardarAuditoriaDetalles() {

        if (!validacionSumatoriaConceptosContables(getRegistrosConceptoContable())) {
            this.addError("Los conceptos contables deben sumar 100% para continuar el proceso");
        }

        if (!this.isError()) {
            super.setAccion(ACCION_CREAR);
            super.setDoAccion(ACCION_GUARDAR_GESTION_AUDITORIA_MASIVA_DETALLES);
            getCmAuditoriaMasivaServicio().Accion(this);      
            getHashDetallesSelecionados().clear();
            getRegistrosConceptoContable().clear();
            getRegistrosAuditoriaDiagnostico().clear();
            getRegistrosAuditoriaMotivoGlosa().clear();
            getRegistrosAuditoriaAutorizacion().clear();
            this.addMensaje("El proceso de auditoría para servicios se ha realizado.");
            procesoFinal();
            getRegistrosDetallesSeleccionadoMasivos().clear();
        } else {
            generarMensajes();
        }
    }
    
      public void guardarAuditoriaMasivaAutorizaciones() {
              
          if (!getRegistrosAuditoriaAutorizacion().isEmpty()) {
              super.setAccion(ACCION_CREAR);
              super.setDoAccion(ACCION_GUARDAR_AUTORIZACION_AUDITORIA_MASIVA);
              getCmAuditoriaMasivaServicio().Accion(this);

              if (!this.isError()) {
                  PrimeFaces.current().executeScript("PF('dialogoGestionAuditoriaMasivaAutorizacion').hide()");
                  this.addMensaje("El proceso de asignar autorización masiva se ha realizado.");
                  getRegistrosDetallesSeleccionadoMasivos().clear();
                  getHashDetallesSelecionados().clear();
                  procesoFinal();
              } else {
                  PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
                  generarMensajes();
              }
          }else{
             this.addError("El proceso requiere que se halla seleccionado una autorización para continuar.");
             generarMensajes();
          }       
      }
      
    public void guardarBorradoMotivosEspecificos() {

        if (validarSeleccionMotivosEspecificos()) {
            if (CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS == getTipoOperacionEjecutada()) {
                super.setAccion(ACCION_CREAR);
                super.setDoAccion(ACCION_BORRAR_MOTIVOS_ESPECIFICOS);
                getCmAuditoriaMasivaServicio().Accion(this);
            }
            if (CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS_FACTURAS == getTipoOperacionEjecutada()) {
                super.setAccion(ACCION_CREAR);
                super.setDoAccion(ACCION_GUARDAR_LEVANTAMIENTOS);
                this.getObjeto().setMotivosEspecificosSeleccionados(this.getMotivosEspecificosSeleccionados());
                getCmAuditoriaMasivaServicio().Accion(this);
                this.addMensaje("El proceso de levantamiento de motivos específicos para el radicado:"+
                    this.getObjeto().getNumeroRadicado() + " se esta realizando.");
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
            }
        }

        if (!isError()) {
            cerrarDialogosBorradoMotivosMasivos();
            limpiarListasBorradoMotivosEspecificos();
            PrimeFaces.current().ajax().update("frmAuditoriaMasiva");
        }
        procesoFinal();

    }
    
     public void guardarMarcadoGlosaIps(CmFactura cmFactura) {
        getObjeto().setNit(cmFactura.getNit());
        this.setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(cmFactura.getId());
        getParamConsultaUtilitario().setParametroConsulta2(!cmFactura.isRespuestaIps());
        getParamConsultaUtilitario().setParametroConsulta3(cmFactura.isRespuestaIps() ?  null : new Date()) ;
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_GUARDAR_MARCADO_GLOSA_IPS);
        getCmAuditoriaMasivaServicio().Accion(this);
        if (!isError()) {
            addMensaje("La factura de número facturado ("+ cmFactura.getNumeroFacturado()+") ha sido " +
                      (!cmFactura.isRespuestaIps() ?" marcada Ips.": " desmarcada Ips.") );
        }
        procesoFinal();
    }
     
    public void guardarMarcarCopagoNoEfectivo(boolean esMarcado) {
                 
        setRegistrosDetallesSeleccionadoMasivos(new ArrayList<>(getHashDetallesSelecionados().values())); 
        this.setParamConsultaUtilitario(new ParamConsulta());
        getParamConsultaUtilitario().setParametroConsulta1(esMarcado);

        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO);
        getCmAuditoriaMasivaServicio().Accion(this);
        if (!isError()) {
            limpiarMarcacionCopagoNoEfectivo();
            addMensaje("Los detalles han sido " +
                      ( esMarcado ?" marcados en copago no efectivo.": " desmarcados en copago no efectivo.") );
            super.setDoAccion(ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO);
        }
        procesoFinal();
        
    }

    private void limpiarMarcacionCopagoNoEfectivo() {
        PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasivaDetalles').hide()");
        getRegistrosDetallesSeleccionadoMasivos().clear();
        getHashDetallesSelecionados().clear();
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
    
    public void asignarAtributosParaProcesamientoMasivo() {
        this.setRegistrosFacturasSeleccionadas(new ArrayList<>(getHashFacturasSelecionadas().values()));
        this.getObjeto().setIdsFacturas(new ArrayList<>(getHashFacturasSelecionadas().keySet()));
        CmFactura facturaPrototipo = getHashFacturasSelecionadas().get( this.getObjeto().getIdsFacturas().get(0) );
        this.getObjeto().setTipoContrato( facturaPrototipo.getMaeTipoContratoCodigo() );
        this.getObjeto().setNumeroRadicado( facturaPrototipo.getNumeroRadicado());
        this.getObjeto().setNit( facturaPrototipo.getNit() ); 
        this.getObjeto().setIps( facturaPrototipo.getIps() );
        int idPrestador =   facturaPrototipo.getCntPrestador() != null &&
                            facturaPrototipo.getCntPrestador().getId() != null ? 
                            (int)facturaPrototipo.getCntPrestador().getId() : 0;
        this.getObjeto().setContratoCapitaPGP(isContratoCapitaPGP(this.getObjeto().getTipoContrato()));
        this.getObjeto().setIdPrestador(idPrestador);
        this.getObjeto().setRegimen(facturaPrototipo.getMaeRegimenValor());
        this.getObjeto().setCodigoRegimen(facturaPrototipo.getMaeRegimenCodigo());
        this.getObjeto().setVersion(facturaPrototipo.getVersion());
    }
    
    private void asignarAtributosParaProcesamientoDesbloqueo() {
        this.setRegistrosFacturasSeleccionadas(new ArrayList<>(getHashFacturasSelecionadas().values()));
        this.getObjeto().setIdsFacturas(new ArrayList<>(getHashFacturasSelecionadas().keySet()));
    }

    public SelDiagnosticosBean getSelDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
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
    
     public void mostrarCentroCostosPorAltoCosto() {
        boolean mostrarCentroCostos = false;
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_LISTAR_CENTRO_COSTOS_ASOCIADOS);
        getCmAuditoriaMasivaServicio().Accion(this);
        if (! this.isError()) {
            if (getListaMaeCentrosCostos() != null && !getListaMaeCentrosCostos().isEmpty()) {
                mostrarCentroCostos = true;
            }
            this.getCmAuditoriaConceptoContable().setMostrarCentroCosto(mostrarCentroCostos);
        }
        generarMensajes(); 
    }
     
     public void verCrearDiagnostico() {
        MaDiagnostico maObjeto = getSelDiagnosticosBean().getObjeto();
        CmAuditoriaDiagnostico diagnostico = new CmAuditoriaDiagnostico();
        diagnostico.setMaDiagniosticosId(maObjeto.getId());
        diagnostico.setMaDiagnosticoCodigo(maObjeto.getCodigo());
        diagnostico.setMaDiagnosticoValor(maObjeto.getNombre());
        diagnostico.setMaDiagnosticoCaretoriaNombre(maObjeto.getMaeDiagnosticoCategoriaValor());
        auditoriaGuardar(diagnostico);
        this.setCmAuditoriaDiagnostico(diagnostico);
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmAgregarDiagnostico");
        PrimeFaces.current().executeScript("PF('dialogoAgregarDiagnostico').show()");
    }
     
    public void ejercutarOperacionMasiva() {

        int tipoOperacion = getCmSelectorOperacionaMasiva().getTipoOperacion();
        this.getObjeto().setTipoOperacionMasiva(tipoOperacion);
        this.setTipoOperacionEjecutada(tipoOperacion);
        switch (tipoOperacion) {
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_GESTION_AUDITORIA_MASIVA:
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
                verGestionAuditoriaMasiva();
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_CAPITA_DESCUENTO:
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
                verDescuentoCapitaMasivo();
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DIAGNOSTICOS:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_CONCEPTOS_CONTABLES:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_AUTORIZACIONES:  
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DESCUENTO_CAPITA: 
                if(validarEjecucionLevantamientos(tipoOperacion)){
                  PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasiva').hide()");
                  guardarLevantamientos();
                } else {
                    if (!this.isError()) {
                        this.addError("El proceso de levantamiento se esta realizando "
                                + " para facturas de radicado: " + this.getObjeto().getNumeroRadicado()
                                + ", por favor espere.");
                    }
                    PrimeFaces.current().ajax().update("frmVerSeleccionOperacionMasiva");
                    generarMensajes();
                }
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS_FACTURAS:
                listarTodosDetalelMultiFactura();
                if( ! getRegistrosDetallesSeleccionadoMasivos().isEmpty() ){
                      verMotivosGlosaUsados();
                }else{
                    this.addError("No se han podido encontrar detalles multifactura.");
                }            
                break;
            default:
                this.getObjeto().setTipoOperacionMasiva(0);
                this.addError("El tipo de operación no esta implementado.");
                break;
        }

    }
    
    public void ejercutarOperacionMasivaDetalles() {
        int tipoOperacion = getCmSelectorOperacionaMasivaDetalles().getTipoOperacion();
        this.setTipoOperacionEjecutada(tipoOperacion);
        switch (tipoOperacion) {
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_DESMARCAR_COPAGO_NO_EFECTIVO:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_MARCAR_COPAGO_NO_EFECTIVO:
                boolean esMarcado = (tipoOperacion == CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_MARCAR_COPAGO_NO_EFECTIVO);
                guardarMarcarCopagoNoEfectivo(esMarcado);
                break;

            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_GESTION_ITEM:
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasivaDetalles').hide()");
                limpiarListasAuditoria();
                verGestionAuditoriaMasiva();
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DIAGNOSTICOS:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_CONCEPTOS_CONTABLES:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_AUTORIZACIONES:
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DESCUENTO_CAPITA:    
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasivaDetalles').hide()");
                guardarLevantamientosDetalles();
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_GESTION_AUTORIZACIONES:
                PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasivaDetalles').hide()");
                limpiarListasAuditoria();
                if (validarDetallesSeleccionadosMismoUsuario()) {
                    verGestionAuditoriaAutorizacionesMasiva();
                } else {
                    generarMensajes();
                }
                break;
            case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS_ESPECIFICOS:  
                verMotivosGlosaUsados();
                break;
            default:
                this.getObjeto().setTipoOperacionMasiva(0);
                this.addError("El tipo de operación no esta implementado.");
                generarMensajes();
                break;
        }
    }
         
    public void limpiarListasAuxiliares() {
        this.getAdjuntosParaGuardar().clear();
        this.getRegistrosFacturasSeleccionadas().clear();
        limpiarListasAuditoria();
        this.getObjeto().setObservacion("");
        this.getObjeto().getListaFacturasPorAdjunto().clear();
        this.getObjeto().getListaAdjuntosEliminar().clear();
        this.getObjeto().getIdsFacturas().clear();
        this.getObjeto().setTipoAuditoria(0);
        this.getObjeto().setProcesamientoPorDetalles(false);
    }
    
    public void limpiarListasAuditoria(){
        this.getRegistrosConceptoContable().clear();
        this.getRegistrosAuditoriaDiagnostico().clear();
        this.getRegistrosAuditoriaMotivoGlosa().clear();
        this.getRegistrosAuditoriaCapitaDescuento().clear();
        this.getRegistrosAuditoriaAutorizacion().clear();
        this.getRegistrosAuditoriaAdjutoCmDetalle().clear();
    }
     
     public void limpiarRender() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("cmAuditoriaMasivaBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("cmAuditoriaMasivaBean");
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
     
     public void desbloquerFacturasMasivoPorUsarios(){
        boolean esValidoProceso = true;
        if (getHashFacturasSelecionadas().isEmpty()) {
             this.addError("Para realizar el desbloqueo debe seleccionar facturas.");
             esValidoProceso = false;
         }
    
         if (esValidoProceso) {
             asignarAtributosParaProcesamientoDesbloqueo();
             desBloquarFacturaPorUsuario();
             if (!isError()) {
                 eventoCerrarVentanaOperacionMasiva();
                 addMensaje("El proceso de desbloqueo de facturas se ha realizado.");
             }
         }
        
        procesoFinal();
     }
     
     public void desBloquarFacturaPorUsuario(){
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_DESBLOQUEAR_FACTURAS_POR_USUARIO);
        getCmAuditoriaMasivaServicio().Accion(this);
    }
     
    public boolean bloquarFacturaPorUsuario() {
        boolean esBloqueoPermitido ;
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_BLOQUEAR_FACTURAS_POR_USUARIO);
         getCmAuditoriaMasivaServicio().Accion(this);
        esBloqueoPermitido = getCmRespuestaGenerica().isEstadoRespuesta();
        if( ! esBloqueoPermitido && !isError()){
                addError(getCmRespuestaGenerica().getMensaje());
        }
        return esBloqueoPermitido;
    }
    
     public void conversionPorcentaje() {
    
        BigDecimal valorLimite = this.getCmAuditoriaMotivoGlosa().getValorMotivoMaximo();
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
        }
    }
    
    public void calcularPorcentaje() {

        BigDecimal porcentaje = getCmAuditoriaMotivoGlosa().getPorcentaje();
        BigDecimal valor;
        if ( getCmAuditoriaMotivoGlosa().isEsValorUnificado()) {
            valor = getCmAuditoriaMotivoGlosa().getValorMotivoMaximo();
            if (porcentaje.compareTo(new BigDecimal("0.0")) == 0) {
                this.addError("Para calcular el porcentaje se debe ingresar valor mayor a cero");
                getCmAuditoriaMotivoGlosa().setValorMotivo(null);
            } else {
                BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
                BigDecimal valorMotivo = promedio.multiply(valor).setScale(4, RoundingMode.HALF_UP);
                getCmAuditoriaMotivoGlosa().setValorMotivo(valorMotivo);
                PrimeFaces.current().resetInputs("frmAgregarMotivo:porcentajeM");  
            }
            generarMensajes();
        }
    }
    
    public void reversarEstadoAuditoria(CmFactura cmFactura) {
        
        this.setRegistrosFacturasSeleccionadas(new ArrayList<>());
        this.getRegistrosFacturasSeleccionadas().add(new CmFactura(cmFactura.getId()));
        
        boolean esValidoProceso = true;
        if (this.getRegistrosFacturasSeleccionadas().isEmpty()) {
            this.addError("Para realizar el proceso debe seleccionar facturas.");
            esValidoProceso = false;
        }
        
        if (esValidoProceso) {
            super.setAccion(ACCION_CREAR);
            super.setDoAccion(ACCION_REVERSAR_ESTADO_AUDITORIA);
            getCmAuditoriaMasivaServicio().Accion(this);
        }
        
        if (!isError()) {
            addMensaje("El proceso reversión se ha realizado. Para la factura de número facturado : "+cmFactura.getNumeroFacturado());
        }
        
        this.setRegistrosFacturasSeleccionadas(new ArrayList<>());
        
        procesoFinal();
        
    }
    
    public boolean validarReversaFactura(CmFactura factura) {
        boolean isValido = false;
        if (factura.getEstadoFactura() == CmFactura.ESTADO_FACTURA_EN_AUDITORIA) {
            isValido = true;
        }
        return isValido;
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
  
    public boolean validarEsFacturaMarcableGlosaIps(CmFactura cmFactura) {
        boolean esFacturaMarcable = false;
        if (cmFactura.getEstadoFactura() == CmFactura.ESTADO_FACTURA_GLOSADA) {
            esFacturaMarcable = true;
        }
        return esFacturaMarcable;
    }
    
    private boolean validarVersionesFacturaMotivosGlosa() {
        boolean esValido;
        if (this.getObjeto().isProcesamientoPorDetalles()) {
            esValido = getRegistrosDetallesSeleccionadoMasivos().stream()
                    .map(detalle -> detalle.getCmFacturas().getVersionStr()).distinct().count() == 1;
            if (!esValido) {
                addError("Los detalles seleccionados correspenden a versiones de factura diferente.");
            }
        } else {
            esValido = validarIgualVersionEnFacturas();
        }
        return esValido;
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
        boolean version = getVersionItemsHomogeneos();
        if (!version) {
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
                if (motivo.getMaestroMotivoAplicacion().getValor().
                        equals(getCmAuditoriaMotivoGlosa().getMaestroMotivoAplicacion().getValor())
                        && motivo.getMaestroMotivoEspecifico().getValor().
                                equals(getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().getValor())) {
                    esValidaInsercion = false;
                    this.addError("No se puede agregar motivos específicos y de aplicación iguales para este detalle o servicio.");
                    break;
                }
            }
        }
        return esValidaInsercion;
    }
    
    public void verConfiguracionesGeneralesTabs(CmDetalle detalle) {
         
         setObjetoDetalleServicio(detalle);
         verAuditoriaDetalleServicio(getObjetoDetalleServicio().getId(), ID_VENTANA_TAB_DETALLES);
         PrimeFaces.current().resetInputs("TabAuditoria");
         PrimeFaces.current().ajax().update("TabAuditoria");
         if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoConfiguracionesGenerales').show()");
         }
         procesoFinal();
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
     
    public String asignarColorDiasRadicacion(CmFactura cmFactura) {
       
        int LIMITE_CERO  = 0;
        int LIMITE_NUEVE = 9;
        int LIMITE_DIECINUEVE = 19;
        String estilo = "";
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
     
     private String obtenerIdsFacturasProcesoMasivo() {
        String idsFacturas = "";
        try {
            idsFacturas = Stream.of(this.getObjeto().getIdsFacturas().toArray()).map(String::valueOf).collect(Collectors.joining(","));
        } catch (Exception e) {
        }
        return idsFacturas;
    }
     
    private void asignarListaConceptosSegunRegimen() {
        List<Maestro> listaConceptos ;
        if(CmFactura.CODIGO_REGIMEN_SUBSIDIADO.equals(this.getObjeto().getCodigoRegimen())){
            listaConceptos = this.getListaMaeConceptosSubsidiados().isEmpty() ? 
                                           this.getListaMaeConceptosTotales() : this.getListaMaeConceptosSubsidiados();            
            this.setListaMaeConceptos(listaConceptos);
        }
        if(CmFactura.CODIGO_REGIMEN_CONTRIBUTIVO.equals(this.getObjeto().getCodigoRegimen())){
            listaConceptos = this.getListaMaeConceptosContributivos().isEmpty() ? 
                                           this.getListaMaeConceptosTotales() : this.getListaMaeConceptosContributivos(); 
            this.setListaMaeConceptos(listaConceptos);
        }
    }
    
    private void asignarSelectMotivoPadreDevolucion() {
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
    
    private void asignarSelectMotivoGlosaPadre() {
        boolean version = getVersionItemsHomogeneos();
        int tipoMotivoGlosaPadre = version ? MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_2284
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
    
     public void onTabChange(TabChangeEvent event) {
        String idTab = event.getTab().getId();
        if ("Tab1_Novedades_Servicio".equals(idTab)) {
            verAuditoriaDetalleServicio(getObjetoDetalleServicio().getId(), ID_VENTANA_TAB_DETALLES);
        }
        if ("Tab2_Programas".equals(idTab)) {
            verProgramasPorAfiliado(getObjetoDetalleServicio());
        }
        if ("Tab3_Novedades".equals(idTab)) {
            verTabNovedades(getObjetoDetalleServicio());
        }
    }
     
     public void verTabNovedades(CmDetalle detalle){
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_ASEGURAMIENTO_AFILIADO_ID);
        getCmAuditoriaMasivaServicio().Accion(this);
        inicializarTablaAuditoriaNovedad(getObjetoRegistroNovedad().getIdAfiliado());
        PrimeFaces.current().resetInputs("TabAuditoria:frmNovedadesTab");
        PrimeFaces.current().ajax().update("TabAuditoria:frmNovedadesTab");
        procesoFinal();
    }
          
    public void cerrarDialogosBorradoMotivosMasivos() {
        PrimeFaces.current().executeScript("PF('dialogoVerSeleccionOperacionMasivaDetalles').hide()");
        PrimeFaces.current().executeScript("PF('dialogoEliminarMotivo').hide()");
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
