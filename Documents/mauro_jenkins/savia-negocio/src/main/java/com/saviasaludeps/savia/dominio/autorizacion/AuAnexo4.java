/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo4 extends Auditoria {

    public static final int ESTADO_AUTORIZADA = 0;
    public static final int ESTADO_AUTORIZADA_AUTOMATICO = 1;
    public static final int ESTADO_ANULADA = 2;
    public static final int ESTADO_PREAUTORIZADO = 3;
    public static final int ESTADO_AUTORIZADA_PREAUTORIZACION = 4;
    public static final int ESTADO_ANULADA_PREAUTORIZACION = 5;
    //2023-09-27 jyperez estado pago anticipado
    public static final int ESTADO_AUTORIZADO_PAGO_ANTICIPADO = 6;
    //2023-09-29 jyperez estado anulado pago anticipado
    public static final int ESTADO_ANULADO_PAGO_ANTICIPADO = 7;
    public static final int IMPRESIONES_AUTORIZADAS = 4;

    public final static String ANEXO_3 = "3";
    public final static String ANEXO_2 = "2";
    public final static String TIPO_ANEXO = "tipoAnexo";
    public final static String TIPO_SOLICITUD = "solicitud";

    public final static String ANULADO = "Anulado";

    public static final int MEDIO_AUTOMATICA = 1;
    public static final int MEDIO_MANUAL = 2;
    public static final int MEDIO_COTIZACION = 3;
    
    //FUENTE ANULADA
    public static final int FUENTE_ANULADA_MANUAL = 1;
    public static final int FUENTE_ANULADA_CARGA_MASIVA = 2;
    public static final int FUENTE_ANULADA_INTEROPERABILIDAD = 3;

    public final static short TAMANIO_OBSERVACION = 30;

    private Integer id;
    private String numeroAutorizacion;
    private Date fechaInicio;
    private Date fechaFin;
    private int diasVigencia;
    private boolean posfechada;
    private Date fechaAutorizacion;
    private Date fechaAutorizacionImpresion;
    private int estado;
    private int maeEstadoMotivoId;
    private String maeEstadoMotivoCodigo;
    private String maeEstadoMotivoValor;
    private String estadoJustificacion;
    private String afiliadoTipoDocumento;
    private String afiliadoNumeroDocumento;
    private String afiliadoPrimerApellido;
    private String afiliadoSegundoApellido;
    private String afiliadoPrimerNombre;
    private String afiliadoSegundoNombre;
    private Date afiliadoFechaNacimiento;
    private Integer afiliadoUbicacion;
    private String afiliadoDepartamento;
    private String afiliadoMunicipio;
    private String afiliadoDireccion;
    private String afiliadoTelefono;
    private String afiliadoCelular;
    private String afiliadoCorreo;
    private String prestadorTipoDocumento;
    private String prestadorNumeroDocumento;
    private String prestadorNombre;
    private String prestadorCodigoHabilitacion;
    private String prestadorTelefonoCita;
    private String prestadorDireccion;
    private String prestadorDepartamento;
    private String prestadorMunicipio;
    private Integer maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private int maeAmbitoAtencionId;
    private String maeAmbitoAtencionCodigo;
    private String maeAmbitoAtencionValor;
    private int maServicioHabilitadoId;
    private String maServicioHabilitadoCodigo;
    private String maServicioHabilitadoValor;
    private int maEspecialidadId;
    private String maEspecialidadCodigo;
    private String maEspecialidadValor;
    private Integer maeGuiaManejoIntegralId;
    private String maeGuiaManejoIntegralCodigo;
    private String maeGuiaManejoIntegralValor;
    private Integer cantidadEntregas;
    private Integer numeroEntrega;
    private String anexo3Cama;
    private String tipoServicioHabilitado;
    private String diagnosticoPrincipal;
    private String nombreAutoriza;
    private String cargoActividadAutoriza;
    private String epsTelefono;
    private String entidadPago;
    private String codigoEntidadPago;
    private boolean aplicaFactura;
    private boolean aplicaNobps;
    private boolean aplicaPac;
    private boolean aplicaCuotaModeradora;
    private Boolean aplicaCopago;
    private Boolean aplicaCuotaRecuperacion;
    private Boolean aplicaOtro;
    private Boolean aplicaAltocosto;
    private Boolean aplicaTutela;
    private boolean aplicaTopeMaximo;
    private boolean aplicaNoRed;
    private boolean aplicaAutorizacionAutomatica;
    private boolean aplicaTiqueteBonoVale;
    private boolean aplicaCapitaRecobro;
    private String motivoExentoCobro;
    private BigDecimal valorCuotaModeradora;
    private BigDecimal valorCopago;
    private BigDecimal valorPac;
    private BigDecimal valorCuotaRecuperacion;
    private BigDecimal valorCuotaOtro;
    private BigDecimal valorTopeMaximo;
    private BigDecimal valorCotizacion;
    private Integer semanasAfiliacion;
    private String numeroPrescripcion;
    private String numeroTutela;
    private Boolean excentoCopago;
    private BigDecimal porcentajeRecuperacion;
    private Integer impresionesAutorizadas;
    private Integer impresionesRealizadas;
    private String observacion;
    private String ruta;
    private String archivo;
    private List<AuAnexo4Impresion> auAnexo4ImpresionesList;
    private List<AuAnexo4Item> auAnexo4ItemsList;
    private List<AuAnexo4Estado> auAnexo4EstadosList;
    private AsegAfiliado asegAfiliadoId;
    private AuAnexo2 auAnexo2Id;
    private AuAnexo3 auAnexo3Id;
    private CntContrato cntContratoId;
    private CntPrestadorSede cntPrestadorSedeId;
    private Empresa gnEmpresaId;
    private List<AuAnexo4Entrega> auAnexo4EntregasList;
    private List<CmAuditoriaAutorizacion> cmAuditoriaAutorizacionesList;
    private List<AuAnexo4Historico> auAnexo4HistoricosList;
    private String archivoNombre;
    private Integer medioAutorizacion;
    private Boolean contratoAnticipado;
    private String contratoAnticipadoObservacion;
    //2023-09-27 jyperez nuevos campos
    private boolean pagoAnticipado;
    //2024-08-22 pvacca nuevos campos
    private boolean topeAplicado;
    //2024-04-29 pvacca nuevos campos
    private Integer fuenteAnula;
    private AuAnexo4CargaAnulada auAnexo4CargaAnuladasId;
    
    private String tipoAnexo;
    private int solicitud;

    private boolean entregada;
    private List<AuSolicitudAdjunto> listaAdjuntos;
    private boolean tieneEntrega;

    //Auxiliares
    private Date fechaPrestacion;
    private Integer numeroFactura;
    private short origenEntrega;
    private String telefono1Prestador;
    private String telefono2Prestador;
    private String tipoTelefono1Prestador;
    private String tipoTelefono2Prestador;
    private String correoElectronicoPrestador;
    private int pos;
    
    //nuevos campos resolucion 2335
    private boolean version;
    private Integer maeModalidadTecnologiaId;
    private String maeModalidadTecnologiaCodigo;
    private String maeModalidadTecnologiaValor;
    private Integer maeFinalidadTecnologiaId;
    private String maeFinalidadTecnologiaCodigo;
    private String maeFinalidadTecnologiaValor;
    private String direccionAlternativa;
    private String consecutivo;
    private Integer maeUbicacionId;
    private String maeUbicacionCodigo;
    private String maeUbicacionValor;
    
    public AuAnexo4() {
        this.listaAdjuntos = new ArrayList();
        this.auAnexo4ImpresionesList = new ArrayList();
    }

    public AuAnexo4(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDiasVigencia() {
        return diasVigencia;
    }

    public void setDiasVigencia(int diasVigencia) {
        this.diasVigencia = diasVigencia;
    }

    public boolean isPosfechada() {
        return posfechada;
    }

    public void setPosfechada(boolean posfechada) {
        this.posfechada = posfechada;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Date getFechaAutorizacionImpresion() {
        return fechaAutorizacionImpresion;
    }

    public void setFechaAutorizacionImpresion(Date fechaAutorizacionImpresion) {
        this.fechaAutorizacionImpresion = fechaAutorizacionImpresion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getMaeEstadoMotivoId() {
        return maeEstadoMotivoId;
    }

    public void setMaeEstadoMotivoId(int maeEstadoMotivoId) {
        this.maeEstadoMotivoId = maeEstadoMotivoId;
    }

    public String getMaeEstadoMotivoCodigo() {
        return maeEstadoMotivoCodigo;
    }

    public void setMaeEstadoMotivoCodigo(String maeEstadoMotivoCodigo) {
        this.maeEstadoMotivoCodigo = maeEstadoMotivoCodigo;
    }

    public String getMaeEstadoMotivoValor() {
        return maeEstadoMotivoValor;
    }

    public void setMaeEstadoMotivoValor(String maeEstadoMotivoValor) {
        this.maeEstadoMotivoValor = maeEstadoMotivoValor;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public int getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(int maeAmbitoAtencionId) {
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
    }

    public String getMaeAmbitoAtencionCodigo() {
        return maeAmbitoAtencionCodigo;
    }

    public void setMaeAmbitoAtencionCodigo(String maeAmbitoAtencionCodigo) {
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
    }

    public String getMaeAmbitoAtencionValor() {
        return maeAmbitoAtencionValor;
    }

    public void setMaeAmbitoAtencionValor(String maeAmbitoAtencionValor) {
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
    }

    public int getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(int maServicioHabilitadoId) {
        this.maServicioHabilitadoId = maServicioHabilitadoId;
    }

    public String getMaServicioHabilitadoCodigo() {
        return maServicioHabilitadoCodigo;
    }

    public void setMaServicioHabilitadoCodigo(String maServicioHabilitadoCodigo) {
        this.maServicioHabilitadoCodigo = maServicioHabilitadoCodigo;
    }

    public String getMaServicioHabilitadoValor() {
        return maServicioHabilitadoValor;
    }

    public void setMaServicioHabilitadoValor(String maServicioHabilitadoValor) {
        this.maServicioHabilitadoValor = maServicioHabilitadoValor;
    }

    public int getMaEspecialidadId() {
        return maEspecialidadId;
    }

    public void setMaEspecialidadId(int maEspecialidadId) {
        this.maEspecialidadId = maEspecialidadId;
    }

    public String getMaEspecialidadCodigo() {
        return maEspecialidadCodigo;
    }

    public void setMaEspecialidadCodigo(String maEspecialidadCodigo) {
        this.maEspecialidadCodigo = maEspecialidadCodigo;
    }

    public String getMaEspecialidadValor() {
        return maEspecialidadValor;
    }

    public void setMaEspecialidadValor(String maEspecialidadValor) {
        this.maEspecialidadValor = maEspecialidadValor;
    }

    public Integer getMaeGuiaManejoIntegralId() {
        return maeGuiaManejoIntegralId;
    }

    public void setMaeGuiaManejoIntegralId(Integer maeGuiaManejoIntegralId) {
        this.maeGuiaManejoIntegralId = maeGuiaManejoIntegralId;
    }

    public String getMaeGuiaManejoIntegralCodigo() {
        return maeGuiaManejoIntegralCodigo;
    }

    public void setMaeGuiaManejoIntegralCodigo(String maeGuiaManejoIntegralCodigo) {
        this.maeGuiaManejoIntegralCodigo = maeGuiaManejoIntegralCodigo;
    }

    public String getMaeGuiaManejoIntegralValor() {
        return maeGuiaManejoIntegralValor;
    }

    public void setMaeGuiaManejoIntegralValor(String maeGuiaManejoIntegralValor) {
        this.maeGuiaManejoIntegralValor = maeGuiaManejoIntegralValor;
    }

    public Integer getCantidadEntregas() {
        return cantidadEntregas;
    }

    public void setCantidadEntregas(Integer cantidadEntregas) {
        this.cantidadEntregas = cantidadEntregas;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public boolean isAplicaFactura() {
        return aplicaFactura;
    }

    public void setAplicaFactura(boolean aplicaFactura) {
        this.aplicaFactura = aplicaFactura;
    }

    public boolean isAplicaNobps() {
        return aplicaNobps;
    }

    public void setAplicaNobps(boolean aplicaNobps) {
        this.aplicaNobps = aplicaNobps;
    }

    public boolean isAplicaPac() {
        return aplicaPac;
    }

    public void setAplicaPac(boolean aplicaPac) {
        this.aplicaPac = aplicaPac;
    }

    public boolean isAplicaCuotaModeradora() {
        return aplicaCuotaModeradora;
    }

    public void setAplicaCuotaModeradora(boolean aplicaCuotaModeradora) {
        this.aplicaCuotaModeradora = aplicaCuotaModeradora;
    }

    public Boolean getAplicaCopago() {
        return aplicaCopago;
    }

    public void setAplicaCopago(Boolean aplicaCopago) {
        this.aplicaCopago = aplicaCopago;
    }

    public Boolean getAplicaCuotaRecuperacion() {
        return aplicaCuotaRecuperacion;
    }

    public void setAplicaCuotaRecuperacion(Boolean aplicaCuotaRecuperacion) {
        this.aplicaCuotaRecuperacion = aplicaCuotaRecuperacion;
    }

    public Boolean getAplicaOtro() {
        return aplicaOtro;
    }

    public void setAplicaOtro(Boolean aplicaOtro) {
        this.aplicaOtro = aplicaOtro;
    }

    public Boolean getAplicaAltocosto() {
        return aplicaAltocosto;
    }

    public void setAplicaAltocosto(Boolean aplicaAltocosto) {
        this.aplicaAltocosto = aplicaAltocosto;
    }

    public Boolean getAplicaTutela() {
        return aplicaTutela;
    }

    public void setAplicaTutela(Boolean aplicaTutela) {
        this.aplicaTutela = aplicaTutela;
    }

    public boolean isAplicaTopeMaximo() {
        return aplicaTopeMaximo;
    }

    public void setAplicaTopeMaximo(boolean aplicaTopeMaximo) {
        this.aplicaTopeMaximo = aplicaTopeMaximo;
    }

    public boolean isAplicaNoRed() {
        return aplicaNoRed;
    }

    public void setAplicaNoRed(boolean aplicaNoRed) {
        this.aplicaNoRed = aplicaNoRed;
    }

    public boolean isAplicaAutorizacionAutomatica() {
        return aplicaAutorizacionAutomatica;
    }

    public void setAplicaAutorizacionAutomatica(boolean aplicaAutorizacionAutomatica) {
        this.aplicaAutorizacionAutomatica = aplicaAutorizacionAutomatica;
    }

    public boolean isAplicaTiqueteBonoVale() {
        return aplicaTiqueteBonoVale;
    }

    public void setAplicaTiqueteBonoVale(boolean aplicaTiqueteBonoVale) {
        this.aplicaTiqueteBonoVale = aplicaTiqueteBonoVale;
    }

    public boolean isAplicaCapitaRecobro() {
        return aplicaCapitaRecobro;
    }

    public void setAplicaCapitaRecobro(boolean aplicaCapitaRecobro) {
        this.aplicaCapitaRecobro = aplicaCapitaRecobro;
    }

    public String getMotivoExentoCobro() {
        return motivoExentoCobro;
    }

    public void setMotivoExentoCobro(String motivoExentoCobro) {
        this.motivoExentoCobro = motivoExentoCobro;
    }   

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorPac() {
        return valorPac;
    }

    public void setValorPac(BigDecimal valorPac) {
        this.valorPac = valorPac;
    }

    public BigDecimal getValorCuotaRecuperacion() {
        return valorCuotaRecuperacion;
    }

    public void setValorCuotaRecuperacion(BigDecimal valorCuotaRecuperacion) {
        this.valorCuotaRecuperacion = valorCuotaRecuperacion;
    }

    public BigDecimal getValorCuotaOtro() {
        return valorCuotaOtro;
    }

    public void setValorCuotaOtro(BigDecimal valorCuotaOtro) {
        this.valorCuotaOtro = valorCuotaOtro;
    }

    public BigDecimal getValorTopeMaximo() {
        return valorTopeMaximo;
    }

    public void setValorTopeMaximo(BigDecimal valorTopeMaximo) {
        this.valorTopeMaximo = valorTopeMaximo;
    }

    public BigDecimal getValorCotizacion() {
        return valorCotizacion;
    }

    public void setValorCotizacion(BigDecimal valorCotizacion) {
        this.valorCotizacion = valorCotizacion;
    }

    public Integer getSemanasAfiliacion() {
        return semanasAfiliacion;
    }

    public void setSemanasAfiliacion(Integer semanasAfiliacion) {
        this.semanasAfiliacion = semanasAfiliacion;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public Boolean getExcentoCopago() {
        return excentoCopago;
    }

    public void setExcentoCopago(Boolean excentoCopago) {
        this.excentoCopago = excentoCopago;
    }

    public Integer getImpresionesAutorizadas() {
        return impresionesAutorizadas;
    }

    public void setImpresionesAutorizadas(Integer impresionesAutorizadas) {
        this.impresionesAutorizadas = impresionesAutorizadas;
    }

    public Integer getImpresionesRealizadas() {
        if (impresionesRealizadas == null) {
            impresionesRealizadas = 0;
        }
        return impresionesRealizadas;
    }

    public void setImpresionesRealizadas(Integer impresionesRealizadas) {
        this.impresionesRealizadas = impresionesRealizadas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<AuAnexo4Item> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Item> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    public List<AuAnexo4Estado> getAuAnexo4EstadosList() {
        return auAnexo4EstadosList;
    }

    public void setAuAnexo4EstadosList(List<AuAnexo4Estado> auAnexo4EstadosList) {
        this.auAnexo4EstadosList = auAnexo4EstadosList;
    }

    public AsegAfiliado getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(AsegAfiliado asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
    }

    public String getOrigen() {
        if (auAnexo2Id != null) {
            return "Anexo2";
        } else if (auAnexo3Id != null) {
            return "Anexo3";
        } else {
            return "???";
        }
    }

    public AuAnexo2 getAuAnexo2Id() {
        return auAnexo2Id;
    }

    public void setAuAnexo2Id(AuAnexo2 auAnexo2Id) {
        this.auAnexo2Id = auAnexo2Id;
    }

    public AuAnexo3 getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(AuAnexo3 auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
    }

    public CntContrato getCntContratoId() {
        return cntContratoId;
    }

    public void setCntContratoId(CntContrato cntContratoId) {
        this.cntContratoId = cntContratoId;
    }

    public CntPrestadorSede getCntPrestadorSedeId() {
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(CntPrestadorSede cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public Empresa getGnEmpresaId() {
        return gnEmpresaId;
    }

    public void setGnEmpresaId(Empresa gnEmpresaId) {
        this.gnEmpresaId = gnEmpresaId;
    }

    public List<CmAuditoriaAutorizacion> getCmAuditoriaAutorizacionesList() {
        return cmAuditoriaAutorizacionesList;
    }

    public void setCmAuditoriaAutorizacionesList(List<CmAuditoriaAutorizacion> cmAuditoriaAutorizacionesList) {
        this.cmAuditoriaAutorizacionesList = cmAuditoriaAutorizacionesList;
    }

    public List<AuAnexo4Entrega> getAuAnexo4EntregasList() {
        return auAnexo4EntregasList;
    }

    public void setAuAnexo4EntregasList(List<AuAnexo4Entrega> auAnexo4EntregasList) {
        this.auAnexo4EntregasList = auAnexo4EntregasList;
    }

    public List<AuAnexo4Historico> getAuAnexo4HistoricosList() {
        return auAnexo4HistoricosList;
    }

    public void setAuAnexo4HistoricosList(List<AuAnexo4Historico> auAnexo4HistoricosList) {
        this.auAnexo4HistoricosList = auAnexo4HistoricosList;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getAfiliadoTipoDocumento() {
        return afiliadoTipoDocumento;
    }

    public void setAfiliadoTipoDocumento(String afiliadoTipoDocumento) {
        this.afiliadoTipoDocumento = afiliadoTipoDocumento;
    }

    public String getAfiliadoNumeroDocumento() {
        return afiliadoNumeroDocumento;
    }

    public void setAfiliadoNumeroDocumento(String afiliadoNumeroDocumento) {
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
    }

    public String getAfiliadoPrimerApellido() {
        return afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    public String getAfiliadoSegundoApellido() {
        return afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    public String getAfiliadoPrimerNombre() {
        return afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    public String getAfiliadoSegundoNombre() {
        return afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    public Date getAfiliadoFechaNacimiento() {
        return afiliadoFechaNacimiento;
    }

    public void setAfiliadoFechaNacimiento(Date afiliadoFechaNacimiento) {
        this.afiliadoFechaNacimiento = afiliadoFechaNacimiento;
    }

    public Integer getAfiliadoUbicacion() {
        return afiliadoUbicacion;
    }

    public void setAfiliadoUbicacion(Integer afiliadoUbicacion) {
        this.afiliadoUbicacion = afiliadoUbicacion;
    }

    public String getAfiliadoDepartamento() {
        return afiliadoDepartamento;
    }

    public void setAfiliadoDepartamento(String afiliadoDepartamento) {
        this.afiliadoDepartamento = afiliadoDepartamento;
    }

    public String getAfiliadoMunicipio() {
        return afiliadoMunicipio;
    }

    public void setAfiliadoMunicipio(String afiliadoMunicipio) {
        this.afiliadoMunicipio = afiliadoMunicipio;
    }

    public String getAfiliadoDireccion() {
        return afiliadoDireccion;
    }

    public void setAfiliadoDireccion(String afiliadoDireccion) {
        this.afiliadoDireccion = afiliadoDireccion;
    }

    public String getAfiliadoTelefono() {
        return afiliadoTelefono;
    }

    public void setAfiliadoTelefono(String afiliadoTelefono) {
        this.afiliadoTelefono = afiliadoTelefono;
    }

    public String getAfiliadoCelular() {
        return afiliadoCelular;
    }

    public void setAfiliadoCelular(String afiliadoCelular) {
        this.afiliadoCelular = afiliadoCelular;
    }

    public String getAfiliadoCorreo() {
        return afiliadoCorreo;
    }

    public void setAfiliadoCorreo(String afiliadoCorreo) {
        this.afiliadoCorreo = afiliadoCorreo;
    }

    public String getPrestadorTipoDocumento() {
        return prestadorTipoDocumento;
    }

    public void setPrestadorTipoDocumento(String prestadorTipoDocumento) {
        this.prestadorTipoDocumento = prestadorTipoDocumento;
    }

    public String getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(String prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorNombre() {
        return prestadorNombre;
    }

    public void setPrestadorNombre(String prestadorNombre) {
        this.prestadorNombre = prestadorNombre;
    }

    public String getPrestadorCodigoHabilitacion() {
        return prestadorCodigoHabilitacion;
    }

    public void setPrestadorCodigoHabilitacion(String prestadorCodigoHabilitacion) {
        this.prestadorCodigoHabilitacion = prestadorCodigoHabilitacion;
    }

    public String getPrestadorTelefonoCita() {
        return prestadorTelefonoCita;
    }

    public void setPrestadorTelefonoCita(String prestadorTelefonoCita) {
        this.prestadorTelefonoCita = prestadorTelefonoCita;
    }

    public String getPrestadorDireccion() {
        return prestadorDireccion;
    }

    public void setPrestadorDireccion(String prestadorDireccion) {
        this.prestadorDireccion = prestadorDireccion;
    }

    public String getPrestadorDepartamento() {
        return prestadorDepartamento;
    }

    public void setPrestadorDepartamento(String prestadorDepartamento) {
        this.prestadorDepartamento = prestadorDepartamento;
    }

    public String getPrestadorMunicipio() {
        return prestadorMunicipio;
    }

    public void setPrestadorMunicipio(String prestadorMunicipio) {
        this.prestadorMunicipio = prestadorMunicipio;
    }

    public String getAnexo3Cama() {
        return anexo3Cama;
    }

    public void setAnexo3Cama(String anexo3Cama) {
        this.anexo3Cama = anexo3Cama;
    }

    public String getTipoServicioHabilitado() {
        return tipoServicioHabilitado;
    }

    public void setTipoServicioHabilitado(String tipoServicioHabilitado) {
        this.tipoServicioHabilitado = tipoServicioHabilitado;
    }

    public String getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public String getNombreAutoriza() {
        return nombreAutoriza;
    }

    public void setNombreAutoriza(String nombreAutoriza) {
        this.nombreAutoriza = nombreAutoriza;
    }

    public String getCargoActividadAutoriza() {
        return cargoActividadAutoriza;
    }

    public void setCargoActividadAutoriza(String cargoActividadAutoriza) {
        this.cargoActividadAutoriza = cargoActividadAutoriza;
    }

    public String getEpsTelefono() {
        return epsTelefono;
    }

    public void setEpsTelefono(String epsTelefono) {
        this.epsTelefono = epsTelefono;
    }

    public String getEntidadPago() {
        return entidadPago;
    }

    public void setEntidadPago(String entidadPago) {
        this.entidadPago = entidadPago;
    }

    public String getCodigoEntidadPago() {
        return codigoEntidadPago;
    }

    public void setCodigoEntidadPago(String codigoEntidadPago) {
        this.codigoEntidadPago = codigoEntidadPago;
    }

    public BigDecimal getPorcentajeRecuperacion() {
        return porcentajeRecuperacion;
    }

    public void setPorcentajeRecuperacion(BigDecimal porcentajeRecuperacion) {
        this.porcentajeRecuperacion = porcentajeRecuperacion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public List<AuAnexo4Impresion> getAuAnexo4ImpresionesList() {
        return auAnexo4ImpresionesList;
    }

    public void setAuAnexo4ImpresionesList(List<AuAnexo4Impresion> auAnexo4ImpresionesList) {
        this.auAnexo4ImpresionesList = auAnexo4ImpresionesList;
    }

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public int getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(int solicitud) {
        this.solicitud = solicitud;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }

    public List<AuSolicitudAdjunto> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<AuSolicitudAdjunto> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public boolean isTieneEntrega() {
        return tieneEntrega;
    }

    public void setTieneEntrega(boolean tieneEntrega) {
        this.tieneEntrega = tieneEntrega;
    }

    public Integer getMedioAutorizacion() {
        return medioAutorizacion;
    }

    public void setMedioAutorizacion(Integer medioAutorizacion) {
        this.medioAutorizacion = medioAutorizacion;
    }

    public String getTelefono1Prestador() {
        return telefono1Prestador;
    }

    public void setTelefono1Prestador(String telefono1Prestador) {
        this.telefono1Prestador = telefono1Prestador;
    }

    public String getTelefono2Prestador() {
        return telefono2Prestador;
    }

    public void setTelefono2Prestador(String telefono2Prestador) {
        this.telefono2Prestador = telefono2Prestador;
    }

    public String getTipoTelefono1Prestador() {
        return tipoTelefono1Prestador;
    }

    public void setTipoTelefono1Prestador(String tipoTelefono1Prestador) {
        this.tipoTelefono1Prestador = tipoTelefono1Prestador;
    }

    public String getTipoTelefono2Prestador() {
        return tipoTelefono2Prestador;
    }

    public void setTipoTelefono2Prestador(String tipoTelefono2Prestador) {
        this.tipoTelefono2Prestador = tipoTelefono2Prestador;
    }

    public String getCorreoElectronicoPrestador() {
        return correoElectronicoPrestador;
    }

    public void setCorreoElectronicoPrestador(String correoElectronicoPrestador) {
        this.correoElectronicoPrestador = correoElectronicoPrestador;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Boolean getContratoAnticipado() {
        return contratoAnticipado;
    }

    public void setContratoAnticipado(Boolean contratoAnticipado) {
        this.contratoAnticipado = contratoAnticipado;
    }

    public String getContratoAnticipadoObservacion() {
        return contratoAnticipadoObservacion;
    }

    public void setContratoAnticipadoObservacion(String contratoAnticipadoObservacion) {
        this.contratoAnticipadoObservacion = contratoAnticipadoObservacion;
    }

    public short getOrigenEntrega() {
        return origenEntrega;
    }

    public void setOrigenEntrega(short origenEntrega) {
        this.origenEntrega = origenEntrega;
    }

    public boolean isVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    public Integer getMaeModalidadTecnologiaId() {
        return maeModalidadTecnologiaId;
    }

    public void setMaeModalidadTecnologiaId(Integer maeModalidadTecnologiaId) {
        this.maeModalidadTecnologiaId = maeModalidadTecnologiaId;
    }

    public String getMaeModalidadTecnologiaCodigo() {
        return maeModalidadTecnologiaCodigo;
    }

    public void setMaeModalidadTecnologiaCodigo(String maeModalidadTecnologiaCodigo) {
        this.maeModalidadTecnologiaCodigo = maeModalidadTecnologiaCodigo;
    }

    public String getMaeModalidadTecnologiaValor() {
        return maeModalidadTecnologiaValor;
    }

    public void setMaeModalidadTecnologiaValor(String maeModalidadTecnologiaValor) {
        this.maeModalidadTecnologiaValor = maeModalidadTecnologiaValor;
    }

    public Integer getMaeFinalidadTecnologiaId() {
        return maeFinalidadTecnologiaId;
    }

    public void setMaeFinalidadTecnologiaId(Integer maeFinalidadTecnologiaId) {
        this.maeFinalidadTecnologiaId = maeFinalidadTecnologiaId;
    }

    public String getMaeFinalidadTecnologiaCodigo() {
        return maeFinalidadTecnologiaCodigo;
    }

    public void setMaeFinalidadTecnologiaCodigo(String maeFinalidadTecnologiaCodigo) {
        this.maeFinalidadTecnologiaCodigo = maeFinalidadTecnologiaCodigo;
    }

    public String getMaeFinalidadTecnologiaValor() {
        return maeFinalidadTecnologiaValor;
    }

    public void setMaeFinalidadTecnologiaValor(String maeFinalidadTecnologiaValor) {
        this.maeFinalidadTecnologiaValor = maeFinalidadTecnologiaValor;
    }

    public String getDireccionAlternativa() {
        return direccionAlternativa;
    }

    public void setDireccionAlternativa(String direccionAlternativa) {
        this.direccionAlternativa = direccionAlternativa;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getMaeUbicacionId() {
        return maeUbicacionId;
    }

    public void setMaeUbicacionId(Integer maeUbicacionId) {
        this.maeUbicacionId = maeUbicacionId;
    }

    public String getMaeUbicacionCodigo() {
        return maeUbicacionCodigo;
    }

    public void setMaeUbicacionCodigo(String maeUbicacionCodigo) {
        this.maeUbicacionCodigo = maeUbicacionCodigo;
    }

    public String getMaeUbicacionValor() {
        return maeUbicacionValor;
    }

    public void setMaeUbicacionValor(String maeUbicacionValor) {
        this.maeUbicacionValor = maeUbicacionValor;
    }
    
    /**
     * @return the pagoAnticipado
     */
    public boolean isPagoAnticipado() {
        return pagoAnticipado;
    }

    /**
     * @param pagoAnticipado the pagoAnticipado to set
     */
    public void setPagoAnticipado(boolean pagoAnticipado) {
        this.pagoAnticipado = pagoAnticipado;
    }
    
    public boolean isTopeAplicado() {
        return topeAplicado;
    }

    public void setTopeAplicado(boolean topeAplicado) {
        this.topeAplicado = topeAplicado;
    }

    public Integer getFuenteAnula() {
        return fuenteAnula;
    }

    public void setFuenteAnula(Integer fuenteAnula) {
        this.fuenteAnula = fuenteAnula;
    }

    public AuAnexo4CargaAnulada getAuAnexo4CargaAnuladasId() {
        return auAnexo4CargaAnuladasId;
    }

    public void setAuAnexo4CargaAnuladasId(AuAnexo4CargaAnulada auAnexo4CargaAnuladasId) {
        this.auAnexo4CargaAnuladasId = auAnexo4CargaAnuladasId;
    }

    public String getContratoAnticipadoObservacionCorto() {
        String observacionCorto = "";
        if (getContratoAnticipadoObservacion() != null) {
            observacionCorto = getContratoAnticipadoObservacion();
            if (getContratoAnticipadoObservacion().length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    //metodos
    @SuppressWarnings("UnusedAssignment")
    public String getMedioAutorizacionStr() {
        String medioAutorizacionStr = "";
        if(medioAutorizacion != null){
            switch (medioAutorizacion) {
                case MEDIO_AUTOMATICA:
                    medioAutorizacionStr = "Automática";
                    break;
                case MEDIO_MANUAL:
                    medioAutorizacionStr = "Manual";
                    break;
                case MEDIO_COTIZACION:
                    medioAutorizacionStr = "Cotización";
                    break;
                default:
                    medioAutorizacionStr = "";
                    break;
            }
        }
        return medioAutorizacionStr;
    }

    public BigDecimal obtenerValor() {
        BigDecimal valor = new BigDecimal("0");
        if (isAplicaCuotaModeradora()) {
            if (getValorCuotaModeradora() != null && !getValorCuotaModeradora().equals(new BigDecimal("0"))) {
                return getValorCuotaModeradora();
            }
        } else if (getAplicaCopago()) {
            if (getValorCopago() != null && !getValorCopago().equals(new BigDecimal("0"))) {
                return getValorCopago();
            }
        } else if (getAplicaCuotaRecuperacion()) {
            if (getValorCuotaRecuperacion() != null && !getValorCuotaRecuperacion().equals(new BigDecimal("0"))) {
                return getValorCuotaRecuperacion();
            }
        } else if (getAplicaOtro()) {
            if (getValorCuotaOtro() != null && !getValorCuotaOtro().equals(new BigDecimal("0"))) {
                return getValorCuotaOtro();
            }
        }
        return valor;
    }

    public int obtenerNumeroSolicitud() {
        if (getAuAnexo2Id() != null) {
            return getAuAnexo2Id().getId();
        } else if (getAuAnexo3Id() != null) {
            return getAuAnexo3Id().getId();
        } else {
            return 0;
        }
    }

    public Date getFechaPrestacion() {
        if (fechaPrestacion != null) {
            return fechaPrestacion;
        } else {
            return new Date();
        }
    }

    public String getEstadoStr() {
        switch (estado) {
            case ESTADO_AUTORIZADA:
                return "Autorizada";
            case ESTADO_AUTORIZADA_AUTOMATICO:
                return "Autorizada Automático";
            case ESTADO_ANULADA:
                return "Anulada";
            case ESTADO_PREAUTORIZADO:
                return "Preautorizado";
            case ESTADO_AUTORIZADA_PREAUTORIZACION:
                return "Autorizada Preautorización";
            case ESTADO_ANULADA_PREAUTORIZACION:
                return "Anulada Preautorización";
            case ESTADO_AUTORIZADO_PAGO_ANTICIPADO:
                return "Autorizada Pago Anticipado";
            case ESTADO_ANULADO_PAGO_ANTICIPADO:
                return "Anulada Pago Anticipado";
            default:
                return "";
        }
    }
    
    public String getTopeAplicadoStr(){
        String topeAplicadoStr = "";
        if(topeAplicado){
            topeAplicadoStr = "Si";
        }else{
            topeAplicadoStr = "No";
        }
        return topeAplicadoStr;
    }
    
    public String getPosfechadaStr(){
        String posfechadaStr = "No";
        if(posfechada){
            posfechadaStr = "Si";
        }
        return posfechadaStr;
    }
    
    public String getConsecutivoGen() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        StringBuilder consecutivoStr = new StringBuilder();
        formato.format(fechaHoraCrea);
        consecutivoStr.append(formato.format(fechaHoraCrea).replace("-", "")).append(id);
        return consecutivoStr.toString();
    }
    
    @Override
    public String toString() {
        return "AuAnexo4{" + "id=" + id + ", numeroAutorizacion=" + numeroAutorizacion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", diasVigencia=" + diasVigencia + ", posfechada=" + posfechada + ", fechaAutorizacion=" + fechaAutorizacion + ", fechaAutorizacionImpresion=" + fechaAutorizacionImpresion + ", maeEstadoMotivoId=" + maeEstadoMotivoId + ", maeEstadoMotivoCodigo=" + maeEstadoMotivoCodigo + ", maeEstadoMotivoValor=" + maeEstadoMotivoValor + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", maeAmbitoAtencionId=" + maeAmbitoAtencionId + ", maeAmbitoAtencionCodigo=" + maeAmbitoAtencionCodigo + ", maeAmbitoAtencionValor=" + maeAmbitoAtencionValor + ", maServicioHabilitadoId=" + maServicioHabilitadoId + ", maServicioHabilitadoCodigo=" + maServicioHabilitadoCodigo + ", maServicioHabilitadoValor=" + maServicioHabilitadoValor + ", maEspecialidadId=" + maEspecialidadId + ", maEspecialidadCodigo=" + maEspecialidadCodigo + ", maEspecialidadValor=" + maEspecialidadValor + ", maeGuiaManejoIntegralId=" + maeGuiaManejoIntegralId + ", maeGuiaManejoIntegralCodigo=" + maeGuiaManejoIntegralCodigo + ", maeGuiaManejoIntegralValor=" + maeGuiaManejoIntegralValor + ", cantidadEntregas=" + cantidadEntregas + ", numeroEntrega=" + numeroEntrega + ", aplicaFactura=" + aplicaFactura + ", aplicaNobps=" + aplicaNobps + ", aplicaPac=" + aplicaPac + ", aplicaCuotaModeradora=" + aplicaCuotaModeradora + ", aplicaCopago=" + aplicaCopago + ", aplicaCuotaRecuperacion=" + aplicaCuotaRecuperacion + ", aplicaOtro=" + aplicaOtro + ", aplicaAltocosto=" + aplicaAltocosto + ", aplicaTutela=" + aplicaTutela + ", aplicaTopeMaximo=" + aplicaTopeMaximo + ", aplicaNoRed=" + aplicaNoRed + ", aplicaAutorizacionAutomatica=" + aplicaAutorizacionAutomatica + ", aplicaTiqueteBonoVale=" + aplicaTiqueteBonoVale + ", aplicaCapitaRecobro=" + aplicaCapitaRecobro + ", valorCuotaModeradora=" + valorCuotaModeradora + ", valorCopago=" + valorCopago + ", valorPac=" + valorPac + ", valorCuotaRecuperacion=" + valorCuotaRecuperacion + ", valorCuotaOtro=" + valorCuotaOtro + ", valorTopeMaximo=" + valorTopeMaximo + ", valorCotizacion=" + valorCotizacion + ", semanasAfiliacion=" + semanasAfiliacion + ", numeroPrescripcion=" + numeroPrescripcion + ", numeroTutela=" + numeroTutela + ", excentoCopago=" + excentoCopago + ", impresionesAutorizadas=" + impresionesAutorizadas + ", impresionesRealizadas=" + impresionesRealizadas + ", observacion=" + observacion + '}';
    }
}
