/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeNota;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jperezn
 */
public class CmFactura extends Auditoria implements Cloneable {

    private Integer id;
    private Integer idCntPrestador;
    private int idCmRipsCargas;
    private int maeTipoContratoId;
    private String maeTipoContratoCodigo;
    private String maeTipoContratoValor;
    private int numeroRadicado;
    private int cantidadDetalles;
    private String numeroFacturado;
    private String nit;
    private String ips;
    private Date fechaRadicacion;
    private Date fechaPrestacion;
    private int maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private BigDecimal valorFactura;
    private int estadoFactura;
    private boolean multiUsuario;
    private boolean respuestaIps;
    private BigDecimal valorInicialGlosa;
    private List<CmGlosaRespuesta> listaGlosaRespuesta;
    private List<CmGlosa> listaCmGlosas;
    private List<CmDetalle> listaCmDetalles;
    private String documentoMonoUsuario;
    private String nombreMonoUsuario;
    private CmGlosaRespuestaDetalle cmGlosaRespuestaDetalle;
    private List<CmGlosaRespuestaDetalle> listCmGlosaRespuestaDetalle;
    private BigDecimal valorPendienteActual;
    private Empresa empresa;
    private CntPrestador CntPrestador;
    private BigDecimal valorPagadoEPS;
    private BigDecimal porcentajePagadoEPS;
    private BigDecimal valorAceptadoIPS;
    private BigDecimal porcentajeAceptadoIPS;
    private boolean estadoRespuestaConciliada;
    private boolean estadoFacturaDeBloqueo;
    private boolean version;
    private int tipoRespuesta;
    private String tipoRespuestaStr;
    private String observacion;
    private int tipoAuditoria;
    private Date fechaVencimiento;
    private Date fechaMarcacion;
    private int marcacion;
    private int cantidadProcesosEjecutados;
    private CmGrupo cmGrupo;
    private Usuario usuarioLider;
    private Usuario usuarioMedico;
    private Usuario usuarioTecnico;
    private Usuario usuarioGestiona;
    private BigDecimal valorPagadoFactura;
    private BigDecimal valorBruto;
    private String responsablePago;
    private boolean pbs;
    private BigDecimal valorCopago;
    private String numeroContrato;
    private int origenFactura;
    private String usuarioAudita;
    private String terminalAudita;
    private Date fechaHoraAudita;
    private String usuarioDevuelve;
    private String terminalDevuelve;
    private Date fechaHoraDevuelve;
    private String usuarioRespuesta;
    private String terminalRespuesta;
    private Date fechaHoraRespuesta;
    private String usuarioConcilia;
    private String terminalConcilia;
    private Date fechaHoraConcilia;
    private BigDecimal valorCuotaModeradora;
    private Date fechaMarcacionRespuestaIps;
    private Date fechaAsignacionTecnico;
    private Date fechaAsignacionMedico;
    private CmFeRipsCarga cmFeRipsCarga;
    private CmFeNota cmFeNota = new CmFeNota();

    public static final int ID_PRESTADOR_DEFECTO = 1;
    public static final int TAMANIO_OBSERVACION = 20;
    public static final int TIPO_CALCULO_CONCILIACION_IND = 1;
    public static final int TIPO_CALCULO_CONCILIACION_MAS = 2;
    public static final int TIPO_CALCULO_CONCILIACION_CON = 3;

    public static final int TIPO_BLOQUEO_GLOSA = 1;
    public static final int MAXIMO_PROCESOS_EJECUTADOS = 2;

    public static final int ESTADO_FACTURA_SIN_AUDITORIA = 0;
    public static final int ESTADO_FACTURA_EN_AUDITORIA = 1;
    public static final int ESTADO_FACTURA_GLOSADA = 2;
    //public static final int ESTADO_FACTURA_CIERRE_AUDITORIA = 3;
    public static final int ESTADO_FACTURA_CONCILIADA = 4;
    public static final int ESTADO_FACTURA_DEVUELTA = 5;
    public static final int ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION = 6;
    public static final int ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA = 7;
    public static final int ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION = 8;
    public static final int ESTADO_FACTURA_AUDITADA_EXITOSA = 9;
    public static final int ESTADO_FACTURA_SIN_PROCESAR = 10;
    public static final int ESTADO_FACTURA_SIN_NOTIFICAR = 11;
    public static final int ESTADO_FACTURA_ANULADA = 12;

    public static final int TIPO_AUDITORIA_SIN_AUDITORIA = 0;
    public static final int TIPO_AUDITORIA_PERTINENCIA_TECNICA = 1;
    public static final int TIPO_AUDITORIA_PERTINENCIA_MEDICA = 2;
    public static final int TIPO_AUDITORIA_DEVOLUCION_TECNICA = 3;
    public static final int TIPO_AUDITORIA_DEVOLUCION_MEDICA = 4;
    public static final int TIPO_AUDITORIA_CIERRE_AUDITORIA = 5;
    public static final int TIPO_AUDITORIA_TECNICA = 6;
    public static final int TIPO_AUDITORIA_MEDICA = 7;
    public static final int TIPO_AUDITORIA_PARA_PAGO = 8;

    public static final String TIPO_TRANSACCION_INICIAL = "1";
    
    public static final String CODIGO_MODALIDAD_CONTRATO_CAPITA = "01";
    public static final String CODIGO_MODALIDAD_CONTRATO_PGP    = "03";
    
    public final static String CODIGO_REGIMEN_SUBSIDIADO   = "01";
    public final static String CODIGO_REGIMEN_CONTRIBUTIVO = "02";
    
    public static final int ORIGEN_FACTURA_INTRASAVIA = 1;
    public static final int ORIGEN_FACTURA_SOMOS_MAS  = 2;
    public static final int ORIGEN_FACTURA_CONEXIONES = 3;
    
    public static final boolean RESPUESTA_IPS_NO = false;
    public static final boolean RESPUESTA_IPS_SI = true;
    
    public static final int TIPO_USUARIO_AUDITORIA_LIDER   = 1;
    public static final int TIPO_USUARIO_AUDITORIA_TECNICO = 2;
    public static final int TIPO_USUARIO_AUDITORIA_MEDICO  = 3;
    public static final int TIPO_USUARIO_GESTIONA          = 4;
    
    public static final boolean VERSION_RESOLUCION_2284 = true;
    public static final boolean VERSION_RESOLUCION_3047 = false;

    private int tipoCalculoConciliacion;
    private boolean bloqueda;
    private int numeroLineaArchivo;
    private String historialProceso;
    private String regional;
    private CmRipsCarga cmRipCarga;
    private CmAuditoriaDevolucion cmAuditoriaDevolucion;
    private CmAuditoriaCierre cmAuditoriaCierre;

    private List<CmAuditoriaAdjunto> listaCmAuditoriaAdjuntos;
    private ArrayList<Integer> listaAdjuntosEliminar;
    private boolean devuelta;
    private int numeroDiasPasados;
    
   
    public CmFactura() {
    }

    public CmFactura(Integer id) {
        this.id = id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CmFactura clone;
        try {
            clone = (CmFactura) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Error al clonar objeto CmFactura");
        }
        return clone;
    }

    public CmFactura(Integer id, int estadoFactura, int numeroRadicado, String nit, String ips) {
        this.id = id;
        this.estadoFactura = estadoFactura;
        this.numeroRadicado = numeroRadicado;
        this.nit = nit;
        this.ips = ips;
    }

    public CmFactura(Integer id, int estadoFactura, int numeroRadicado, String nit, String ips, int maeRegimenId, Date fechaRadicacion, Date fechaPrestacion, String maeRegimenValor, BigDecimal valorFactura,BigDecimal valorCopago ) {
        this.id = id;
        this.estadoFactura = estadoFactura;
        this.numeroRadicado = numeroRadicado;
        this.nit = nit;
        this.ips = ips;
        this.maeRegimenId = maeRegimenId;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaPrestacion = fechaPrestacion;
        this.maeRegimenValor = maeRegimenValor;
        this.valorFactura = valorFactura;
        this.valorCopago = valorCopago;
    }

    public CmFactura(Integer id, int numeroRadicado, String nit, String ips, String numeroFacturado, Date fechaRadicacion, Date fechaPrestacion, String maeTipoContratoValor, int maeRegimenId, BigDecimal valorFactura, int estadoFactura, boolean multiUsuario, BigDecimal valorInicialGlosa) {
        this.id = id;
        this.numeroRadicado = numeroRadicado;
        this.nit = nit;
        this.ips = ips;
        this.numeroFacturado = numeroFacturado;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaPrestacion = fechaPrestacion;
        this.maeTipoContratoValor = maeTipoContratoValor;
        this.maeRegimenId = maeRegimenId;
        this.valorFactura = valorFactura;
        this.estadoFactura = estadoFactura;
        this.multiUsuario = multiUsuario;
        this.valorInicialGlosa = valorInicialGlosa;
    }

    public CmFactura(Integer id, String maeTipoContratoValor, int numeroRadicado, String numeroFacturado, String nit, String ips, Date fechaRadicacion, Date fechaPrestacion, String maeRegimenValor, BigDecimal valorFactura, int estadoFactura, BigDecimal valorCopago) {
        this.id = id;
        this.maeTipoContratoValor = maeTipoContratoValor;
        this.numeroRadicado = numeroRadicado;
        this.numeroFacturado = numeroFacturado;
        this.nit = nit;
        this.ips = ips;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaPrestacion = fechaPrestacion;
        this.maeRegimenValor = maeRegimenValor;
        this.valorFactura = valorFactura;
        this.estadoFactura = estadoFactura;
        this.valorCopago = valorCopago;
    }
    
     public CmFactura(Integer id, String maeTipoContratoValor, int numeroRadicado, String numeroFacturado, String nit, String ips, Date fechaRadicacion, Date fechaPrestacion, String maeRegimenValor, BigDecimal valorFactura, int estadoFactura, BigDecimal valorCopago, BigDecimal valorPendienteActual) {
        this.id = id;
        this.maeTipoContratoValor = maeTipoContratoValor;
        this.numeroRadicado = numeroRadicado;
        this.numeroFacturado = numeroFacturado;
        this.nit = nit;
        this.ips = ips;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaPrestacion = fechaPrestacion;
        this.maeRegimenValor = maeRegimenValor;
        this.valorFactura = valorFactura;
        this.estadoFactura = estadoFactura;
        this.valorCopago = valorCopago;
        this.valorPendienteActual = valorPendienteActual;
    }
     
    public CmFactura(Integer id, String maeTipoContratoValor, int numeroRadicado, String numeroFacturado, 
            String nit, String ips, Date fechaRadicacion, Date fechaPrestacion, String maeRegimenValor, 
            BigDecimal valorFactura, int estadoFactura, BigDecimal valorCopago, BigDecimal valorPendienteActual,
            Integer cmRipCargaId, Integer cmFeRipsCargaId) {
        this.id = id;
        this.maeTipoContratoValor = maeTipoContratoValor;
        this.numeroRadicado = numeroRadicado;
        this.numeroFacturado = numeroFacturado;
        this.nit = nit;
        this.ips = ips;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaPrestacion = fechaPrestacion;
        this.maeRegimenValor = maeRegimenValor;
        this.valorFactura = valorFactura;
        this.estadoFactura = estadoFactura;
        this.valorCopago = valorCopago;
        this.valorPendienteActual = valorPendienteActual;
        this.setCmRipCarga(new CmRipsCarga(cmRipCargaId));
        this.setCmFeRipsCarga(new CmFeRipsCarga(cmFeRipsCargaId));
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getNumeroFacturado() {
        return numeroFacturado;
    }

    public void setNumeroFacturado(String numeroFacturado) {
        this.numeroFacturado = numeroFacturado;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public int getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(int estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public boolean isMultiUsuario() {
        return multiUsuario;
    }

    public void setMultiUsuario(boolean multiUsuario) {
        this.multiUsuario = multiUsuario;
    }

    public BigDecimal getValorInicialGlosa() {
        return valorInicialGlosa;
    }

    public void setValorInicialGlosa(BigDecimal valorInicialGlosa) {
        this.valorInicialGlosa = valorInicialGlosa;
    }

    public List<CmGlosaRespuesta> getListaGlosaRespuesta() {
        return listaGlosaRespuesta;
    }

    public void setListaGlosaRespuesta(List<CmGlosaRespuesta> listaGlosaRespuesta) {
        this.listaGlosaRespuesta = listaGlosaRespuesta;
    }

    public BigDecimal getValorPendienteActual() {
        return valorPendienteActual;
    }

    public void setValorPendienteActual(BigDecimal valorPendienteActual) {
        this.valorPendienteActual = valorPendienteActual;
    }

    public List<CmGlosa> getListaCmGlosas() {
        return listaCmGlosas;
    }

    public void setListaCmGlosas(List<CmGlosa> listaCmGlosas) {
        this.listaCmGlosas = listaCmGlosas;
    }

    public List<CmDetalle> getListaCmDetalles() {
        return listaCmDetalles;
    }

    public void setListaCmDetalles(List<CmDetalle> listaCmDetalles) {
        this.listaCmDetalles = listaCmDetalles;
    }

    public String getDocumentoMonoUsuario() {
        return documentoMonoUsuario;
    }

    public void setDocumentoMonoUsuario(String documentoMonoUsuario) {
        this.documentoMonoUsuario = documentoMonoUsuario;
    }

    public String getNombreMonoUsuario() {
        return nombreMonoUsuario;
    }

    public boolean isEstadoRespuestaConciliada() {
        if (getTipoRespuesta() > 0
                && CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION == getTipoRespuesta()) {
            estadoRespuestaConciliada = true;
        }
        return estadoRespuestaConciliada;
    }

    public void setEstadoRespuestaConciliada(boolean estadoRespuestaConciliada) {
        this.estadoRespuestaConciliada = estadoRespuestaConciliada;
    }

    public boolean isEstadoFacturaDeBloqueo() {
        if ((getTipoRespuesta() > 0
                && CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION == getTipoRespuesta()) ||
            ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION  == this.getEstadoFactura()) {
            estadoFacturaDeBloqueo = true;
        }
        return estadoFacturaDeBloqueo;
    }

    public void setEstadoFacturaDeBloqueo(boolean estadoFacturaDeBloqueo) {
        this.estadoFacturaDeBloqueo = estadoFacturaDeBloqueo;
    }
    
    

    public void setNombreMonoUsuario(String nombreMonoUsuario) {
        this.nombreMonoUsuario = nombreMonoUsuario;
    }

    public CmGlosaRespuestaDetalle getCmGlosaRespuestaDetalle() {
        if (cmGlosaRespuestaDetalle == null) {
            cmGlosaRespuestaDetalle = new CmGlosaRespuestaDetalle();
        }
        return cmGlosaRespuestaDetalle;
    }

    public void setCmGlosaRespuestaDetalle(CmGlosaRespuestaDetalle cmGlosaRespuestaDetalle) {
        this.cmGlosaRespuestaDetalle = cmGlosaRespuestaDetalle;
    }

    public List<CmGlosaRespuestaDetalle> getListCmGlosaRespuestaDetalle() {
        if (listCmGlosaRespuestaDetalle == null) {
            listCmGlosaRespuestaDetalle = new ArrayList<>();
        }
        return listCmGlosaRespuestaDetalle;
    }

    public void setListCmGlosaRespuestaDetalle(List<CmGlosaRespuestaDetalle> listCmGlosaRespuestaDetalle) {
        this.listCmGlosaRespuestaDetalle = listCmGlosaRespuestaDetalle;
    }
    
    public List<CmAuditoriaAdjunto> getListaCmAuditoriaAdjuntos() {
        if (listaCmAuditoriaAdjuntos == null) {
            listaCmAuditoriaAdjuntos = new ArrayList<>();
        }
        return listaCmAuditoriaAdjuntos;
    }

    public void setListaCmAuditoriaAdjuntos(List<CmAuditoriaAdjunto> listaCmAuditoriaAdjuntos) {
        this.listaCmAuditoriaAdjuntos = listaCmAuditoriaAdjuntos;
    }

    public ArrayList<Integer> getListaAdjuntosEliminar() {
        if (listaAdjuntosEliminar == null) {
            listaAdjuntosEliminar = new ArrayList<>();
        }
        return listaAdjuntosEliminar;
    }

    public void setListaAdjuntosEliminar(ArrayList<Integer> listaAdjuntosEliminar) {
        this.listaAdjuntosEliminar = listaAdjuntosEliminar;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public CntPrestador getCntPrestador() {
        return CntPrestador;
    }

    public void setCntPrestador(CntPrestador CntPrestador) {
        this.CntPrestador = CntPrestador;
    }

    public BigDecimal getValorPagadoEPS() {
        return valorPagadoEPS;
    }

    public void setValorPagadoEPS(BigDecimal valorPagadoEPS) {
        this.valorPagadoEPS = valorPagadoEPS;
    }

    public BigDecimal getPorcentajePagadoEPS() {
        return porcentajePagadoEPS;
    }

    public void setPorcentajePagadoEPS(BigDecimal porcentajePagadoEPS) {
        this.porcentajePagadoEPS = porcentajePagadoEPS;
    }

    public BigDecimal getValorAceptadoIPS() {
        return valorAceptadoIPS;
    }

    public void setValorAceptadoIPS(BigDecimal valorAceptadoIPS) {
        this.valorAceptadoIPS = valorAceptadoIPS;
    }

    public BigDecimal getPorcentajeAceptadoIPS() {
        return porcentajeAceptadoIPS;
    }

    public void setPorcentajeAceptadoIPS(BigDecimal porcentajeAceptadoIPS) {
        this.porcentajeAceptadoIPS = porcentajeAceptadoIPS;
    }

    public int getTipoRespuesta() {
        return tipoRespuesta;
    }

    public void setTipoRespuesta(int tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

    public int getNumeroLineaArchivo() {
        return numeroLineaArchivo;
    }

    public void setNumeroLineaArchivo(int numeroLineaArchivo) {
        this.numeroLineaArchivo = numeroLineaArchivo;
    }

    public String getTipoRespuestaStr() {
        if (getTipoRespuesta() >= 0) {
            CmGlosaRespuesta respuesta = new CmGlosaRespuesta();
            respuesta.setTipoRespuesta(getTipoRespuesta());
            tipoRespuestaStr = respuesta.getTipoRespuestaStr();
        }
        return tipoRespuestaStr;
    }

    public void setTipoRespuestaStr(String tipoRespuestaStr) {
        this.tipoRespuestaStr = tipoRespuestaStr;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionCorto() {
        if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacion;
        }
    }

    public Usuario getUsuarioLider() {
        if (usuarioLider == null) {
            usuarioLider = new Usuario();
        }
        return usuarioLider;
    }

    public void setUsuarioLider(Usuario usuarioLider) {
        this.usuarioLider = usuarioLider;
    }

    public Usuario getUsuarioMedico() {
        if (usuarioMedico == null) {
            usuarioMedico = new Usuario();
        }
        return usuarioMedico;
    }

    public void setUsuarioMedico(Usuario usuarioMedico) {
        this.usuarioMedico = usuarioMedico;
    }

    public Usuario getUsuarioTecnico() {
        if (usuarioTecnico == null) {
            usuarioTecnico = new Usuario();
        }
        return usuarioTecnico;
    }

    public void setUsuarioTecnico(Usuario usuarioTecnico) {
        this.usuarioTecnico = usuarioTecnico;
    }

    public Usuario getUsuarioGestiona() {
        if (usuarioGestiona == null) {
            usuarioGestiona = new Usuario();
        }
        return usuarioGestiona;
    }

    public void setUsuarioGestiona(Usuario usuarioGestiona) {
        this.usuarioGestiona = usuarioGestiona;
    }

    public CmGrupo getCmGrupo() {
        if (cmGrupo == null) {
            cmGrupo = new CmGrupo();
        }
        return cmGrupo;
    }

    public void setCmGrupo(CmGrupo cmGrupo) {
        this.cmGrupo = cmGrupo;
    }

    public String getUsuarioAudita() {
        return usuarioAudita;
    }

    public void setUsuarioAudita(String usuarioAudita) {
        this.usuarioAudita = usuarioAudita;
    }

    public String getTerminalAudita() {
        return terminalAudita;
    }

    public void setTerminalAudita(String terminalAudita) {
        this.terminalAudita = terminalAudita;
    }

    public Date getFechaHoraAudita() {
        return fechaHoraAudita;
    }

    public void setFechaHoraAudita(Date fechaHoraAudita) {
        this.fechaHoraAudita = fechaHoraAudita;
    }

    public String getUsuarioDevuelve() {
        return usuarioDevuelve;
    }

    public void setUsuarioDevuelve(String usuarioDevuelve) {
        this.usuarioDevuelve = usuarioDevuelve;
    }

    public String getTerminalDevuelve() {
        return terminalDevuelve;
    }

    public void setTerminalDevuelve(String terminalDevuelve) {
        this.terminalDevuelve = terminalDevuelve;
    }

    public Date getFechaHoraDevuelve() {
        return fechaHoraDevuelve;
    }

    public void setFechaHoraDevuelve(Date fechaHoraDevuelve) {
        this.fechaHoraDevuelve = fechaHoraDevuelve;
    }

    public String getUsuarioRespuesta() {
        return usuarioRespuesta;
    }

    public void setUsuarioRespuesta(String usuarioRespuesta) {
        this.usuarioRespuesta = usuarioRespuesta;
    }

    public String getTerminalRespuesta() {
        return terminalRespuesta;
    }

    public void setTerminalRespuesta(String terminalRespuesta) {
        this.terminalRespuesta = terminalRespuesta;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public String getUsuarioConcilia() {
        return usuarioConcilia;
    }

    public void setUsuarioConcilia(String usuarioConcilia) {
        this.usuarioConcilia = usuarioConcilia;
    }

    public String getTerminalConcilia() {
        return terminalConcilia;
    }

    public void setTerminalConcilia(String terminalConcilia) {
        this.terminalConcilia = terminalConcilia;
    }

    public Date getFechaHoraConcilia() {
        return fechaHoraConcilia;
    }

    public void setFechaHoraConcilia(Date fechaHoraConcilia) {
        this.fechaHoraConcilia = fechaHoraConcilia;
    }

    public Date getFechaAsignacionTecnico() {
        return fechaAsignacionTecnico;
    }

    public void setFechaAsignacionTecnico(Date fechaAsignacionTecnico) {
        this.fechaAsignacionTecnico = fechaAsignacionTecnico;
    }

    public Date getFechaAsignacionMedico() {
        return fechaAsignacionMedico;
    }

    public void setFechaAsignacionMedico(Date fechaAsignacionMedico) {
        this.fechaAsignacionMedico = fechaAsignacionMedico;
    }

    public int getTipoCalculoConciliacion() {
        return tipoCalculoConciliacion;
    }

    public void setTipoCalculoConciliacion(int tipoCalculoConciliacion) {
        this.tipoCalculoConciliacion = tipoCalculoConciliacion;
    }

    public boolean isRespuestaIps() {
        return respuestaIps;
    }
    
    public boolean getRespuestaIps() {
        return respuestaIps;
    }

    public void setRespuestaIps(boolean respuestaIps) {
        this.respuestaIps = respuestaIps;
    }

    public Date getFechaMarcacionRespuestaIps() {
        return fechaMarcacionRespuestaIps;
    }

    public void setFechaMarcacionRespuestaIps(Date fechaMarcacionRespuestaIps) {
        this.fechaMarcacionRespuestaIps = fechaMarcacionRespuestaIps;
    }  

    public String getMultiUsuarioStr() {
        return this.isMultiUsuario() ? "Si" : "No";
    }

    public CmRipsCarga getCmRipCarga() {
        if(cmRipCarga==null){
            cmRipCarga = new CmRipsCarga();
        }
        return cmRipCarga;
    }

    public void setCmRipCarga(CmRipsCarga cmRipCarga) {
        this.cmRipCarga = cmRipCarga;
    }
    
    
    public String getRespuestaIpsStr(){
     return getRespuestaIpsStr(isRespuestaIps());
    }
    
    public static String getRespuestaIpsStr(boolean respuestaIps){    
        return respuestaIps ? "Si":"No";            
    }  

    public String getTipoAuditoriaStr() {
        return CmFactura.getTipoAuditoriaStr(getTipoAuditoria());
    }

    public static String getTipoAuditoriaStr(int tipoAuditoria) {
        switch (tipoAuditoria) {
            case TIPO_AUDITORIA_SIN_AUDITORIA:
                return "Sin auditoría";
            case TIPO_AUDITORIA_PERTINENCIA_TECNICA:
                return "Pertinencia técnica";
            case TIPO_AUDITORIA_PERTINENCIA_MEDICA:
                return "Pertinencia médica";
            case TIPO_AUDITORIA_DEVOLUCION_TECNICA:
                return "Devolucion técnica";
            case TIPO_AUDITORIA_DEVOLUCION_MEDICA:
                return "Devolucion médica";
            case TIPO_AUDITORIA_CIERRE_AUDITORIA:
                return "Cierre auditoría";
            case TIPO_AUDITORIA_TECNICA:
                return "Técnica";
            case TIPO_AUDITORIA_MEDICA:
                return "Médica";
            case TIPO_AUDITORIA_PARA_PAGO:
                return "Para pago";
            default:
                return "";
        }
    }

    public int getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(int tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }

    public boolean isBloqueda() {
        if (CmFactura.TIPO_BLOQUEO_GLOSA == getMarcacion()) {
            bloqueda = true;
        }
        return bloqueda;
    }

    public void setBloqueda(boolean bloqueda) {
        this.bloqueda = bloqueda;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaMarcacion() {
        return fechaMarcacion;
    }

    public void setFechaMarcacion(Date fechaMarcacion) {
        this.fechaMarcacion = fechaMarcacion;
    }

    public int getMarcacion() {
        return marcacion;
    }

    public void setMarcacion(int marcacion) {
        this.marcacion = marcacion;
    }

    public String getHistorialProceso() {
        return historialProceso;
    }

    public void setHistorialProceso(String historialProceso) {
        this.historialProceso = historialProceso;
    }

    public int getCantidadProcesosEjecutados() {
        return cantidadProcesosEjecutados;
    }

    public void setCantidadProcesosEjecutados(int cantidadProcesosEjecutados) {
        this.cantidadProcesosEjecutados = cantidadProcesosEjecutados;
    }

    public boolean getMaximoProcesosRegistrados() {
        return this.getCantidadProcesosEjecutados() > MAXIMO_PROCESOS_EJECUTADOS;
    }

    public int getIdCmRipsCargas() {
        return idCmRipsCargas;
    }

    public void setIdCmRipsCargas(int idCmRipsCargas) {
        this.idCmRipsCargas = idCmRipsCargas;
    }

    public int getMaeTipoContratoId() {
        return maeTipoContratoId;
    }

    public void setMaeTipoContratoId(int maeTipoContratoId) {
        this.maeTipoContratoId = maeTipoContratoId;
    }

    public String getMaeTipoContratoCodigo() {
        return maeTipoContratoCodigo;
    }

    public void setMaeTipoContratoCodigo(String maeTipoContratoCodigo) {
        this.maeTipoContratoCodigo = maeTipoContratoCodigo;
    }

    public String getMaeTipoContratoValor() {
        return maeTipoContratoValor;
    }

    public void setMaeTipoContratoValor(String maeTipoContratoValor) {
        this.maeTipoContratoValor = maeTipoContratoValor;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
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

    public BigDecimal getValorPagadoFactura() {
        return valorPagadoFactura;
    }

    public void setValorPagadoFactura(BigDecimal valorPagadoFactura) {
        this.valorPagadoFactura = valorPagadoFactura;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public CmAuditoriaDevolucion getCmAuditoriaDevolucion() {
        return cmAuditoriaDevolucion;
    }

    public void setCmAuditoriaDevolucion(CmAuditoriaDevolucion cmAuditoriaDevolucion) {
        this.cmAuditoriaDevolucion = cmAuditoriaDevolucion;
    } 

    public CmAuditoriaCierre getCmAuditoriaCierre() {
        if(cmAuditoriaCierre == null){
            cmAuditoriaCierre = new CmAuditoriaCierre();
        }
        return cmAuditoriaCierre;
    }

    public void setCmAuditoriaCierre(CmAuditoriaCierre cmAuditoriaCierre) {
        this.cmAuditoriaCierre = cmAuditoriaCierre;
    }
    
    public String getResponsablePago() {
        if ("".equals(responsablePago) || responsablePago == null) {
            responsablePago = "Savia Salud EPS";
        }
        return responsablePago;
    }

    public void setResponsablePago(String responsablePago) {
        this.responsablePago = responsablePago;
    }

    public String getRegional() {
        if ("".equals(regional) || regional == null) {
            regional = "Nacional";
        }
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getEstadoFacturaStr() {
        return CmFactura.getEstadoFacturaStr(getEstadoFactura());
    }

    public boolean isDevuelta() {
        return  CmFactura.ESTADO_FACTURA_DEVUELTA == getEstadoFactura() || 
                CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION == getEstadoFactura() ;
    }

    public void setDevuelta(boolean devuelta) {
        this.devuelta = devuelta;
    }

    public int getCantidadDetalles() {
        return cantidadDetalles;
    }

    public void setCantidadDetalles(int cantidadDetalles) {
        this.cantidadDetalles = cantidadDetalles;
    }    

    public int getNumeroDiasPasados() {
        return numeroDiasPasados;
    }

    public void setNumeroDiasPasados(int numeroDiasPasados) {
        this.numeroDiasPasados = numeroDiasPasados;
    }

    public static String getEstadoFacturaStr(int estadoFactura) {
        String str;
        switch (estadoFactura) {
            case ESTADO_FACTURA_SIN_PROCESAR:
                str = "Radicado - Espera SAP (M1)";
                break;
            case ESTADO_FACTURA_SIN_NOTIFICAR:
                str = "Radicado - Espera SAP (M1) - ERROR";
                break;
            case ESTADO_FACTURA_SIN_AUDITORIA:
                str = "Radicado";
                break;
            case ESTADO_FACTURA_EN_AUDITORIA:
                str = "En auditoría";
                break;
            case ESTADO_FACTURA_GLOSADA:
                str = "Auditada-Glosada";
                break;
            case ESTADO_FACTURA_CONCILIADA:
                str = "Conciliada";
                break;
            case ESTADO_FACTURA_DEVUELTA:
                str = "Devuelta";
                break;
            case ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION:
                str = "Devuelta - Espera SAP (DEV)";
                break;
            case ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA:
                str = "Auditada - Espera SAP (M2)";
                break;
            case ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION:
                str = "Conciliada - Espera SAP (M3)";
                break;
            case ESTADO_FACTURA_AUDITADA_EXITOSA:
                str = "Auditada";
                break;
            case ESTADO_FACTURA_ANULADA:
                str = "Anulada";
                break;    
            default:
                str = "";
                break;
        }
        return str;
    }

    public boolean isVersion() {
        return version;
    }
     
    public boolean getVersion() {
        return version;
    }

    public Integer getIdCntPrestador() {
        return idCntPrestador;
    }

    public CmFeNota getCmFeNota() {
        return cmFeNota;
    }

    public void setCmFeNota(CmFeNota cmFeNota) {
        this.cmFeNota = cmFeNota;
    }
    
    

    public void setIdCntPrestador(Integer idCntPrestador) {
        this.idCntPrestador = idCntPrestador;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }
    
    public String getVersionStr() {
        return CmFactura.getVersionStr(isVersion());
    }
    
    public static String getVersionStr(boolean version) {
      return  VERSION_RESOLUCION_2284 == version? "2284": "3047" ;
    }
    
    public String getOrigenFacturaStr() {
        return CmFactura.getOrigenFacturaStr(getOrigenFactura());
    }
    
    public static String getOrigenFacturaStr(int origenFactura) {
        String str;
        switch (origenFactura) {
            case ORIGEN_FACTURA_INTRASAVIA:
                str = "IntraSavia";
                break;
            case ORIGEN_FACTURA_SOMOS_MAS:
                str = "SomosMas";
                break;
            case ORIGEN_FACTURA_CONEXIONES:
                str = "Conexiones";
                break;  
            default:
                str = "";
                break;
        }
        return str;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmFactura)) {
            return false;
        }
        CmFactura other = (CmFactura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean isPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }
    
    public String getPbsStr() {
        return this.isPbs() ? "Si" : "No";
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public int getOrigenFactura() {
        return origenFactura;
    }

    public void setOrigenFactura(int origenFactura) {
        this.origenFactura = origenFactura;
    }   

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public CmFeRipsCarga getCmFeRipsCarga() {
        return cmFeRipsCarga;
    }

    public void setCmFeRipsCarga(CmFeRipsCarga cmFeRipsCarga) {
        this.cmFeRipsCarga = cmFeRipsCarga;
    }
    
    public boolean isTieneNotas(){
        return getCmFeNota().getId() != null;
    }
    
    public boolean isFacturaCargaElectronica(){
        return getCmFeRipsCarga() != null &&
                getCmFeRipsCarga().getId() != null;
    }
    
    @Override
    public String toString() {
        return "CmFactura{" + "id=" + id + ", numeroRadicado=" + numeroRadicado + ", nit=" + nit + ", ips=" + ips + ", numeroFacturado=" + numeroFacturado + ", fechaRadicacion=" + fechaRadicacion + ", fechaPrestacion=" + fechaPrestacion + ", maeContratoValor=" + maeTipoContratoValor + ", maeRegimenId=" + maeRegimenId + ", valorFactura=" + valorFactura + ", estadoFactura=" + estadoFactura + ", multiUsuario=" + multiUsuario + ", valorInicialGlosa=" + valorInicialGlosa + ", listaGlosaRespuesta=" + listaGlosaRespuesta + ", listaCmGlosas=" + listaCmGlosas + ", listaCmDetalles=" + listaCmDetalles + ", documentoMonoUsuario=" + documentoMonoUsuario + ", nombreMonoUsuario=" + nombreMonoUsuario + ", cmGlosaRespuestaDetalle=" + cmGlosaRespuestaDetalle + ", valorPendienteActual=" + valorPendienteActual + ", empresa=" + empresa + ", tipoAuditoria = "+getTipoAuditoriaStr() +", CntPrestador=" + CntPrestador + ",historial proceso=" +historialProceso+'}';
    }

}
