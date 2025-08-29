/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo3 extends Auditoria {

    //Estados
    public static final int ESTADO_PENDIENTE = 1;
    public static final int ESTADO_GESTIONADO = 2;
    public static final int ESTADO_ANULADO = 3;
    public static final int ESTADO_EN_GESTION = 4;
    public static final int ESTADO_DEVUELTO = 5;
    public static final String TEXTO_ESTADO_PENDIENTE = "PENDIENTE";
    public static final String TEXTO_ESTADO_GESTIONADO = "GESTIONADO";
    public static final String TEXTO_ESTADO_ANULADO = "ANULADO";
    public static final String TEXTO_ESTADO_EN_GESTION = "EN GESTION";
    public static final String TEXTO_ESTADO_DEVUELTO = "EN DEVOLUCION";

    public static final int ESTADO_PROCESO_ACTUAL_LIBRE = 0;
    public static final int ESTADO_PROCESO_ACTUAL_EDICION = 1;
    public static final int ESTADO_PROCESO_ACTUAL_AUDITORIA = 2;

    //Ubicacion paciente    
    public static final String CONSULTA_EXTERNA = "C";
    public static final String URGENCIAS = "U";
    public static final String HOSPITALIZACION = "H";
    public static final String DOMICILIO = "D";
    
    //LEY 2335 
    public static final String LABEL_ORIGEN_ATENCION = "Origen de la atención";
    public static final String LABEL_TIPO_SERVICIO_SOLICITADO = "Tipo Servicio Solicitado";
    public static final String LABEL_UBICACION_PACIENTE = "Ubicación del paciente";
    
    public static final String LABEL_CAUSA_MOTIVA_ATENCION = "Causa que motiva la atención";
    public static final String LABEL_TIPO_ATENCION_SOLICITADA = "Tipo de atención solicitada";
    public static final String LABEL_GRUPO_SERVICIOS = "Grupo de Servicios";
    
    //FUENTE ANULADA
    public static final int FUENTE_ANULADA_MANUAL = 1;
    public static final int FUENTE_ANULADA_CARGA_MASIVA = 2;
    public static final int FUENTE_ANULADA_INTEROPERABILIDAD = 3;
    
    private Integer id;
    private String numero;
    private Date fechaSolicitud;
    private String nombreAcompanante;
    private String telefonoAcompanante;
    private String celularAcompanente;
    private int estado;
    private Integer maeEstadoMotivoId;
    private String maeEstadoMotivoCodigo;
    private String maeEstadoMotivoValor;
    private String estadoJustificacion;
    private String alternativa;
    private Integer maeCausaExternaId;
    private String maeCausaExternaCodigo;
    private String maeCausaExternaValor;
    private int maeAmbitoAtencionId;
    private String maeAmbitoAtencionCodigo;
    private String maeAmbitoAtencionValor;
    private int maServicioSolicitadoId;
    private String maServicioSolicitadoCodigo;
    private String maServicioSolicitadoValor;
    private Integer maServicioHabilitadoId;
    private String maServicioHabilitadoCodigo;
    private String maServicioHabilitadoValor;
    private Integer peProgramaEspecialId;
    private int maeOrigenAtencionId;
    private String maeOrigenAtencionCodigo;
    private String maeOrigenAtencionValor;
    private Boolean prioridad;
    private Integer maeTipoServicioId;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private Integer maeUbicacionId;
    private String maeUbicacionCodigo;
    private String maeUbicacionValor;
    private String cama;
    private String justificacionClinica;
    private int maeTipoCodumentoId;
    private int maeTipoDumentoCodigo;
    private String nombreProfesional;
    private String documentoProfesional;
    private String cargoProfesional;
    private String telefonoProfesional;
    private String celularProfesional;
    private int fuenteOrigen;
    private String peProgramaEspecialValor;
    private String peProgramaEspecialCodigo;
    private Integer maeGuiaManejoIntegralId;
    private String maeGuiaManejoIntegralCodigo;
    private String maeGuiaManejoIntegralValor;
    private Boolean cuotaModeradora;
    private Boolean cuotaRecuperacion;
    private Boolean copago;
    private List<AuAnexo3Adjunto> auAnexo3AdjuntosList;
    private List<AuAnexo4> auAnexos4List;
    private AsegAfiliado asegAfiliadoId;
    private AuAnexo3Carga auAnexo3CargaId;
    private CntPrestadorSede cntPrestadorSedeId;
    private CntProfesional cntProfesionaleId;
    private Empresa gnEmpresaId;
    private PePrograma peProgramaId;
    private List<AuAnexo3Item> auAnexo3ItemsList;
    private List<AuAnexo3Diagnostico> auAnexo3DiagnosticosList;
    private List<AuAnexo3Afiliado> auAnexo3AfiliadosList;
    private List<AuAnexo3Tutela> auAnexo3TutelasList;
    private List<AuRechazo> auRechazosList;
    private List<AuSolicitudAdjunto> auSolicitudAdjuntosList;
    private List<AuAnexo3Historico> auAnexo3HistoricosList;
    private List<PePrograma> aucSugerirProgramaList;
    private List<PeAfiliadoSugerido> aucSugeridolList;
    private String consecutivo;
    private int cargaFila;
    private Integer fuenteAnula;
    private AuAnexo3CargaAnulada auAnexo3CargaAnuladasId;
    
    //nuevos campos resolucion 2335
    private boolean version;
    private Integer maeModalidadTecnologiaId;
    private String maeModalidadTecnologiaCodigo;
    private String maeModalidadTecnologiaValor;
    private Integer maeFinalidadTecnologiaId;
    private String maeFinalidadTecnologiaCodigo;
    private String maeFinalidadTecnologiaValor;
    private String direccionAlternativa;

    //Diagnosticos
    private AuAnexo3Diagnostico objetoDiagnostico;

    //Prescripciones
    private AuAnexo3Item objetoTecnologia;

    //Variables adicionales
    private Integer maeUbicacionPaciente;
    private boolean esEditar;
    private Date fechaDesdeHistoria;
    private Date fechaHastaHistoria;
    private Date evaluacionHistoria;
    private int maeTipoDocumentoArchivo;
//    private AuAnexo3Adjunto adjuntoActual;
    private CntPrestadorSede objetoIps;
    private boolean esAuditar;
    private boolean tieneRescates;
    private int cantidadAutorizaciones;
    private int cantidadTecnologias;
    private int estadoProcesoActual;
    private List<AuAnexo3Item> listaPosfechados;
    private List<AuAnexo3Item> listaDevoluciones;
    private List<AuItemBitacora> listaBitacoras;
    private List<AuAnexo2Rescate> auAnexo2RescateList;
     private List<AuAnexo3Item> listaPosfechadosEditar;

    //Variables para profesional
    private Integer maeEspecialidadProfesional;
    private boolean newProfesional;
    private MaEspecialidad objetoEspecialidad;

    //private MaServicioHabilitacion objetoServicioHabilitacion;

    private CntContratoDetalle objetoContratoDetalle;
    private AuCotizacion objetoCotizacion;
    private String observacionAuditar;
    private String comentario;

    private Boolean cargaMasiva;
    private Boolean webService;

    private AuAnexo3Tutela objetoTutela;

    //Variables para cargaMasiva
    private String errorCarga;

    //Rechazo
    private int idMotivo;

    //Errores
    private List<String> errores;
    private List<String> erroresAutomatica;

    //Mensajes
    private List<String> mensajeAutomatica;

    //Automatico
    private boolean automatica;
    private transient List<AuAnexo4Automatico> listaAutomaticos;
    private AuAnexo4 objetoAnexo4;
    private boolean preAutorizacion;
    
    //variable auxliares para la ley 2335
    private boolean habilitarCampoDireccionAlternativa;
    private boolean habilitarCampoModalidadTecnologia;
    private boolean habilitarCampoFinalidadTecnologia;
    private String labelOrigenAtencion;
    private String labelTipoServicioSolicitado;
    private String labelUbicacionPaciente;
    
    public AuAnexo3() {
        auAnexos4List = new ArrayList();
        auAnexo3ItemsList = new ArrayList();
        auAnexo3DiagnosticosList = new ArrayList();
        this.newProfesional = true;
        cntProfesionaleId = new CntProfesional();
        auSolicitudAdjuntosList = new ArrayList();
        auAnexo3AfiliadosList = new ArrayList();
        auAnexo3TutelasList = new ArrayList();
    }

    public AuAnexo3(int id) {
        this.id = id;
        auAnexos4List = new ArrayList();
        auAnexo3ItemsList = new ArrayList();
        auAnexo3DiagnosticosList = new ArrayList();
        this.newProfesional = true;
        cntProfesionaleId = new CntProfesional();
        auSolicitudAdjuntosList = new ArrayList();
        auAnexo3AfiliadosList = new ArrayList();
        auAnexo3TutelasList = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getTelefonoAcompanante() {
        return telefonoAcompanante;
    }

    public void setTelefonoAcompanante(String telefonoAcompanante) {
        this.telefonoAcompanante = telefonoAcompanante;
    }

    public String getCelularAcompanente() {
        return celularAcompanente;
    }

    public void setCelularAcompanente(String celularAcompanente) {
        this.celularAcompanente = celularAcompanente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
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

    public Boolean getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }

    public String getCargoProfesional() {
        return cargoProfesional;
    }

    public void setCargoProfesional(String cargoProfesional) {
        this.cargoProfesional = cargoProfesional;
    }

    public String getTelefonoProfesional() {
        return telefonoProfesional;
    }

    public void setTelefonoProfesional(String telefonoProfesional) {
        this.telefonoProfesional = telefonoProfesional;
    }

    public String getCelularProfesional() {
        return celularProfesional;
    }

    public void setCelularProfesional(String celularProfesional) {
        this.celularProfesional = celularProfesional;
    }

    public int getMaeOrigenAtencionId() {
        return maeOrigenAtencionId;
    }

    public void setMaeOrigenAtencionId(int maeOrigenAtencionId) {
        this.maeOrigenAtencionId = maeOrigenAtencionId;
    }

    public String getMaeOrigenAtencionCodigo() {
        return maeOrigenAtencionCodigo;
    }

    public void setMaeOrigenAtencionCodigo(String maeOrigenAtencionCodigo) {
        this.maeOrigenAtencionCodigo = maeOrigenAtencionCodigo;
    }

    public String getMaeOrigenAtencionValor() {
        return maeOrigenAtencionValor;
    }

    public void setMaeOrigenAtencionValor(String maeOrigenAtencionValor) {
        this.maeOrigenAtencionValor = maeOrigenAtencionValor;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public int getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(int maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public Integer getPeProgramaEspecialId() {
        return peProgramaEspecialId;
    }

    public void setPeProgramaEspecialId(Integer peProgramaEspecialId) {
        this.peProgramaEspecialId = peProgramaEspecialId;
    }

    public String getPeProgramaEspecialValor() {
        return peProgramaEspecialValor;
    }

    public void setPeProgramaEspecialValor(String peProgramaEspecialValor) {
        this.peProgramaEspecialValor = peProgramaEspecialValor;
    }

    public String getPeProgramaEspecialCodigo() {
        return peProgramaEspecialCodigo;
    }

    public void setPeProgramaEspecialCodigo(String peProgramaEspecialCodigo) {
        this.peProgramaEspecialCodigo = peProgramaEspecialCodigo;
    }

    public Integer getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(Integer maServicioHabilitadoId) {
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

    public Boolean getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(Boolean cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public Boolean getCuotaRecuperacion() {
        return cuotaRecuperacion;
    }

    public void setCuotaRecuperacion(Boolean cuotaRecuperacion) {
        this.cuotaRecuperacion = cuotaRecuperacion;
    }

    public Boolean getCopago() {
        return copago;
    }

    public void setCopago(Boolean copago) {
        this.copago = copago;
    }

    public String getNombreAcompanante() {
        return nombreAcompanante;
    }

    public void setNombreAcompanante(String nombreAcompanante) {
        this.nombreAcompanante = nombreAcompanante;
    }

    public List<AuAnexo3Adjunto> getAuAnexo3AdjuntosList() {
        return auAnexo3AdjuntosList;
    }

    public void setAuAnexo3AdjuntosList(List<AuAnexo3Adjunto> auAnexo3AdjuntosList) {
        this.auAnexo3AdjuntosList = auAnexo3AdjuntosList;
    }

    public List<AuAnexo4> getAuAnexos4List() {
        return auAnexos4List;
    }

    public void setAuAnexos4List(List<AuAnexo4> auAnexos4List) {
        this.auAnexos4List = auAnexos4List;
    }

    public AsegAfiliado getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(AsegAfiliado asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
    }

    public AuAnexo3Carga getAuAnexo3CargaId() {
        return auAnexo3CargaId;
    }

    public void setAuAnexo3CargaId(AuAnexo3Carga auAnexo3CargaId) {
        this.auAnexo3CargaId = auAnexo3CargaId;
    }

    public CntPrestadorSede getCntPrestadorSedeId() {
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(CntPrestadorSede cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public CntProfesional getCntProfesionaleId() {
        return cntProfesionaleId;
    }

    public void setCntProfesionaleId(CntProfesional cntProfesionaleId) {
        this.cntProfesionaleId = cntProfesionaleId;
    }

    public Empresa getGnEmpresaId() {
        return gnEmpresaId;
    }

    public void setGnEmpresaId(Empresa gnEmpresaId) {
        this.gnEmpresaId = gnEmpresaId;
    }

    public List<AuAnexo3Item> getAuAnexo3ItemsList() {
        return auAnexo3ItemsList;
    }

    public void setAuAnexo3ItemsList(List<AuAnexo3Item> auAnexo3ItemsList) {
        this.auAnexo3ItemsList = auAnexo3ItemsList;
    }

    public List<AuAnexo3Diagnostico> getAuAnexo3DiagnosticosList() {
        return auAnexo3DiagnosticosList;
    }

    public void setAuAnexo3DiagnosticosList(List<AuAnexo3Diagnostico> auAnexo3DiagnosticosList) {
        this.auAnexo3DiagnosticosList = auAnexo3DiagnosticosList;
    }

    public List<AuAnexo3Afiliado> getAuAnexo3AfiliadosList() {
        return auAnexo3AfiliadosList;
    }

    public void setAuAnexo3AfiliadosList(List<AuAnexo3Afiliado> auAnexo3AfiliadosList) {
        this.auAnexo3AfiliadosList = auAnexo3AfiliadosList;
    }

    public List<AuRechazo> getAuRechazosList() {
        return auRechazosList;
    }

    public void setAuRechazosList(List<AuRechazo> auRechazosList) {
        this.auRechazosList = auRechazosList;
    }

    public AuAnexo3Diagnostico getObjetoDiagnostico() {
        return objetoDiagnostico;
    }

    public void setObjetoDiagnostico(AuAnexo3Diagnostico objetoDiagnostico) {
        this.objetoDiagnostico = objetoDiagnostico;
    }

    public AuAnexo3Item getObjetoTecnologia() {
        return objetoTecnologia;
    }

    public void setObjetoTecnologia(AuAnexo3Item objetoTecnologia) {
        this.objetoTecnologia = objetoTecnologia;
    }

    public Integer getMaeUbicacionPaciente() {
        return maeUbicacionPaciente;
    }

    public void setMaeUbicacionPaciente(Integer maeUbicacionPaciente) {
        this.maeUbicacionPaciente = maeUbicacionPaciente;
    }

    public Integer getMaeEspecialidadProfesional() {
        return maeEspecialidadProfesional;
    }

    public void setMaeEspecialidadProfesional(Integer maeEspecialidadProfesional) {
        this.maeEspecialidadProfesional = maeEspecialidadProfesional;
    }

    public boolean isEsEditar() {
        return esEditar;
    }

    public void setEsEditar(boolean esEditar) {
        this.esEditar = esEditar;
    }

    public Date getFechaDesdeHistoria() {
        return fechaDesdeHistoria;
    }

    public void setFechaDesdeHistoria(Date fechaDesdeHistoria) {
        this.fechaDesdeHistoria = fechaDesdeHistoria;
    }

    public Date getFechaHastaHistoria() {
        return fechaHastaHistoria;
    }

    public void setFechaHastaHistoria(Date fechaHastaHistoria) {
        this.fechaHastaHistoria = fechaHastaHistoria;
    }

    public Date getEvaluacionHistoria() {
        return evaluacionHistoria;
    }

    public void setEvaluacionHistoria(Date evaluacionHistoria) {
        this.evaluacionHistoria = evaluacionHistoria;
    }

    public int getMaeTipoDocumentoArchivo() {
        return maeTipoDocumentoArchivo;
    }

    public void setMaeTipoDocumentoArchivo(int maeTipoDocumentoArchivo) {
        this.maeTipoDocumentoArchivo = maeTipoDocumentoArchivo;
    }

    public CntPrestadorSede getObjetoIps() {
        return objetoIps;
    }

    public void setObjetoIps(CntPrestadorSede objetoIps) {
        this.objetoIps = objetoIps;
    }

    public boolean isEsAuditar() {
        return esAuditar;
    }

    public void setEsAuditar(boolean esAuditar) {
        this.esAuditar = esAuditar;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

   /* public MaServicioHabilitacion getObjetoServicioHabilitacion() {
        return objetoServicioHabilitacion;
    }

    public void setObjetoServicioHabilitacion(MaServicioHabilitacion objetoServicioHabilitacion) {
        this.objetoServicioHabilitacion = objetoServicioHabilitacion;
        if (objetoServicioHabilitacion != null) {
            setMaServicioHabilitadoId(objetoServicioHabilitacion.getId());
            setMaServicioHabilitadoCodigo("" + objetoServicioHabilitacion.getCodigo());
            setMaServicioHabilitadoValor(objetoServicioHabilitacion.getNombre());
            setMaServicioSolicitadoId(objetoServicioHabilitacion.getId());
            setMaServicioSolicitadoCodigo("" + objetoServicioHabilitacion.getCodigo());
            setMaServicioSolicitadoValor(objetoServicioHabilitacion.getNombre());
        }
    }*/

    public boolean isNewProfesional() {
        return newProfesional;
    }

    public void setNewProfesional(boolean newProfesional) {
        this.newProfesional = newProfesional;
    }

    public MaEspecialidad getObjetoEspecialidad() {
        return objetoEspecialidad;
    }

    public void setObjetoEspecialidad(MaEspecialidad objetoEspecialidad) {
        this.objetoEspecialidad = objetoEspecialidad;
    }

    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
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

    public PePrograma getPeProgramaId() {
        return peProgramaId;
    }

    public void setPeProgramaId(PePrograma peProgramaId) {
        this.peProgramaId = peProgramaId;
    }

    public List<AuAnexo3Tutela> getAuAnexo3TutelasList() {
        return auAnexo3TutelasList;
    }

    public void setAuAnexo3TutelasList(List<AuAnexo3Tutela> auAnexo3TutelasList) {
        this.auAnexo3TutelasList = auAnexo3TutelasList;
    }

    public int getCantidadAutorizaciones() {
        return cantidadAutorizaciones;
    }

    public void setCantidadAutorizaciones(int cantidadAutorizaciones) {
        this.cantidadAutorizaciones = cantidadAutorizaciones;
    }

    public int getCantidadTecnologias() {
        return cantidadTecnologias;
    }

    public void setCantidadTecnologias(int cantidadTecnologias) {
        this.cantidadTecnologias = cantidadTecnologias;
    }

    public List<AuAnexo3Item> getListaPosfechados() {
        return listaPosfechados;
    }

    public void setListaPosfechados(List<AuAnexo3Item> listaPosfechados) {
        this.listaPosfechados = listaPosfechados;
    }

    public List<AuAnexo3Item> getListaPosfechadosEditar() {
        return listaPosfechadosEditar;
    }

    public void setListaPosfechadosEditar(List<AuAnexo3Item> listaPosfechadosEditar) {
        this.listaPosfechadosEditar = listaPosfechadosEditar;
    }

    public List<AuAnexo3Item> getListaDevoluciones() {
        return listaDevoluciones;
    }

    public void setListaDevoluciones(List<AuAnexo3Item> listaDevoluciones) {
        this.listaDevoluciones = listaDevoluciones;
    }

    public List<AuItemBitacora> getListaBitacoras() {
        return listaBitacoras;
    }

    public void setListaBitacoras(List<AuItemBitacora> listaBitacoras) {
        this.listaBitacoras = listaBitacoras;
    }

    public CntContratoDetalle getObjetoContratoDetalle() {
        return objetoContratoDetalle;
    }

    public void setObjetoContratoDetalle(CntContratoDetalle objetoContratoDetalle) {
        this.objetoContratoDetalle = objetoContratoDetalle;
    }

    public AuCotizacion getObjetoCotizacion() {
        return objetoCotizacion;
    }

    public void setObjetoCotizacion(AuCotizacion objetoCotizacion) {
        this.objetoCotizacion = objetoCotizacion;
    }

    public String getObservacionAuditar() {
        return observacionAuditar;
    }

    public void setObservacionAuditar(String observacionAuditar) {
        this.observacionAuditar = observacionAuditar;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Integer getMaeEstadoMotivoId() {
        return maeEstadoMotivoId;
    }

    public void setMaeEstadoMotivoId(Integer maeEstadoMotivoId) {
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

    public AuAnexo3Tutela getObjetoTutela() {
        return objetoTutela;
    }

    public void setObjetoTutela(AuAnexo3Tutela objetoTutela) {
        this.objetoTutela = objetoTutela;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public List<String> getErroresAutomatica() {
        return erroresAutomatica;
    }

    public void setErroresAutomatica(List<String> erroresAutomatica) {
        this.erroresAutomatica = erroresAutomatica;
    }

    public List<String> getMensajeAutomatica() {
        return mensajeAutomatica;
    }

    public void setMensajeAutomatica(List<String> mensajeAutomatica) {
        this.mensajeAutomatica = mensajeAutomatica;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    public void setAutomatica(boolean automatica) {
        this.automatica = automatica;
    }

    public List<AuAnexo4Automatico> getListaAutomaticos() {
        return listaAutomaticos;
    }

    public void setListaAutomaticos(List<AuAnexo4Automatico> listaAutomaticos) {
        this.listaAutomaticos = listaAutomaticos;
    }

    public AuAnexo4 getObjetoAnexo4() {
        return objetoAnexo4;
    }

    public void setObjetoAnexo4(AuAnexo4 objetoAnexo4) {
        this.objetoAnexo4 = objetoAnexo4;
    }

    public Boolean getCargaMasiva() {
        return cargaMasiva;
    }

    public void setCargaMasiva(Boolean cargaMasiva) {
        this.cargaMasiva = cargaMasiva;
    }

    public Boolean getWebService() {
        return webService;
    }

    public void setWebService(Boolean webService) {
        this.webService = webService;
    }

    public int getCargaFila() {
        return cargaFila;
    }

    public void setCargaFila(int cargaFila) {
        this.cargaFila = cargaFila;
    }

    public Integer getFuenteAnula() {
        return fuenteAnula;
    }

    public void setFuenteAnula(Integer fuenteAnula) {
        this.fuenteAnula = fuenteAnula;
    }

    public AuAnexo3CargaAnulada getAuAnexo3CargaAnuladasId() {
        return auAnexo3CargaAnuladasId;
    }

    public void setAuAnexo3CargaAnuladasId(AuAnexo3CargaAnulada auAnexo3CargaAnuladasId) {
        this.auAnexo3CargaAnuladasId = auAnexo3CargaAnuladasId;
    }

    public int getMaeTipoCodumentoId() {
        return maeTipoCodumentoId;
    }

    public void setMaeTipoCodumentoId(int maeTipoCodumentoId) {
        this.maeTipoCodumentoId = maeTipoCodumentoId;
    }

    public int getMaeTipoDumentoCodigo() {
        return maeTipoDumentoCodigo;
    }

    public void setMaeTipoDumentoCodigo(int maeTipoDumentoCodigo) {
        this.maeTipoDumentoCodigo = maeTipoDumentoCodigo;
    }

    public String getDocumentoProfesional() {
        return documentoProfesional;
    }

    public void setDocumentoProfesional(String documentoProfesional) {
        this.documentoProfesional = documentoProfesional;
    }

    public List<AuSolicitudAdjunto> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjunto> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public int getEstadoProcesoActual() {
        return estadoProcesoActual;
    }

    public void setEstadoProcesoActual(int estadoProcesoActual) {
        this.estadoProcesoActual = estadoProcesoActual;
    }

    public boolean isPreAutorizacion() {
        return preAutorizacion;
    }

    public void setPreAutorizacion(boolean preAutorizacion) {
        this.preAutorizacion = preAutorizacion;
    }

    public List<AuAnexo2Rescate> getAuAnexo2RescateList() {
        return auAnexo2RescateList;
    }

    public void setAuAnexo2RescateList(List<AuAnexo2Rescate> auAnexo2RescateList) {
        this.auAnexo2RescateList = auAnexo2RescateList;
    }

    public boolean isTieneRescates() {
        return tieneRescates;
    }

    public void setTieneRescates(boolean tieneRescates) {
        this.tieneRescates = tieneRescates;
    }

    public List<AuAnexo3Historico> getAuAnexo3HistoricosList() {
        return auAnexo3HistoricosList;
    }

    public void setAuAnexo3HistoricosList(List<AuAnexo3Historico> auAnexo3HistoricosList) {
        this.auAnexo3HistoricosList = auAnexo3HistoricosList;
    }

    public List<PePrograma> getAucSugerirProgramaList() {
        return aucSugerirProgramaList;
    }

    public void setAucSugerirProgramaList(List<PePrograma> aucSugerirProgramaList) {
        this.aucSugerirProgramaList = aucSugerirProgramaList;
    }

    public List<PeAfiliadoSugerido> getAucSugeridolList() {
        if(aucSugeridolList == null){
            aucSugeridolList = new ArrayList<>();
        }
        return aucSugeridolList;
    }

    public void setAucSugeridolList(List<PeAfiliadoSugerido> aucSugeridolList) {
        this.aucSugeridolList = aucSugeridolList;
    }
    
    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public boolean getVersion() {
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

    public boolean isHabilitarCampoDireccionAlternativa() {
        return habilitarCampoDireccionAlternativa;
    }

    public void setHabilitarCampoDireccionAlternativa(boolean habilitarCampoDireccionAlternativa) {
        this.habilitarCampoDireccionAlternativa = habilitarCampoDireccionAlternativa;
    }

    public boolean isHabilitarCampoModalidadTecnologia() {
        return habilitarCampoModalidadTecnologia;
    }

    public void setHabilitarCampoModalidadTecnologia(boolean habilitarCampoModalidadTecnologia) {
        this.habilitarCampoModalidadTecnologia = habilitarCampoModalidadTecnologia;
    }

    public boolean isHabilitarCampoFinalidadTecnologia() {
        return habilitarCampoFinalidadTecnologia;
    }

    public void setHabilitarCampoFinalidadTecnologia(boolean habilitarCampoFinalidadTecnologia) {
        this.habilitarCampoFinalidadTecnologia = habilitarCampoFinalidadTecnologia;
    }

    public String getLabelOrigenAtencion() {
        return labelOrigenAtencion;
    }

    public void setLabelOrigenAtencion(String labelOrigenAtencion) {
        this.labelOrigenAtencion = labelOrigenAtencion;
    }

    public String getLabelTipoServicioSolicitado() {
        return labelTipoServicioSolicitado;
    }

    public void setLabelTipoServicioSolicitado(String labelTipoServicioSolicitado) {
        this.labelTipoServicioSolicitado = labelTipoServicioSolicitado;
    }

    public String getLabelUbicacionPaciente() {
        return labelUbicacionPaciente;
    }

    public void setLabelUbicacionPaciente(String labelUbicacionPaciente) {
        this.labelUbicacionPaciente = labelUbicacionPaciente;
    }
    
    public String getEstadoStr() {
        String valor = "";
        switch (this.estado) {
            case ESTADO_PENDIENTE:
                valor = TEXTO_ESTADO_PENDIENTE;
                break;
            case ESTADO_GESTIONADO:
                valor = TEXTO_ESTADO_GESTIONADO;
                break;
            case ESTADO_ANULADO:
                valor = TEXTO_ESTADO_ANULADO;
                break;
            case ESTADO_EN_GESTION:
                valor = TEXTO_ESTADO_EN_GESTION;
                break;
            case ESTADO_DEVUELTO:
                valor = TEXTO_ESTADO_DEVUELTO;
                break;
        }
        return valor;
    }

    public String ubicacionValor(String ubicacion) {
        String valor = "";
        switch (ubicacion) {
            case "1":
                valor = CONSULTA_EXTERNA;
                break;
            case "2":
                valor = URGENCIAS;
                break;
            case "3":
                valor = HOSPITALIZACION;
                break;
            case "4":
                valor = DOMICILIO;
                break;
        }

        return valor;
    }

    public String getPrioritarioStr() {
        if (prioridad != null) {
            if (prioridad) {
                return "Sí";
            } else {
                return "No";
            }
        } else {
            return "No";
        }
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
        return "AuAnexo3{" + "id=" + id + ", numero=" + numero + ", fechaSolicitud=" + fechaSolicitud + ", nombreAcompanante=" + nombreAcompanante + ", telefonoAcompanante=" + telefonoAcompanante + ", celularAcompanente=" + celularAcompanente + ", estado=" + estado + ", maeEstadoMotivoId=" + maeEstadoMotivoId + ", maeEstadoMotivoCodigo=" + maeEstadoMotivoCodigo + ", maeEstadoMotivoValor=" + maeEstadoMotivoValor + ", estadoJustificacion=" + estadoJustificacion + ", alternativa=" + alternativa + ", maeCausaExternaId=" + maeCausaExternaId + ", maeCausaExternaCodigo=" + maeCausaExternaCodigo + ", maeCausaExternaValor=" + maeCausaExternaValor + ", maeAmbitoAtencionId=" + maeAmbitoAtencionId + ", maeAmbitoAtencionCodigo=" + maeAmbitoAtencionCodigo + ", maeAmbitoAtencionValor=" + maeAmbitoAtencionValor + ", maServicioSolicitadoId=" + maServicioSolicitadoId + ", maServicioSolicitadoCodigo=" + maServicioSolicitadoCodigo + ", maServicioSolicitadoValor=" + maServicioSolicitadoValor + ", maServicioHabilitadoId=" + maServicioHabilitadoId + ", maServicioHabilitadoCodigo=" + maServicioHabilitadoCodigo + ", maServicioHabilitadoValor=" + maServicioHabilitadoValor + ", peProgramaEspecialId=" + peProgramaEspecialId + ", maeOrigenAtencionId=" + maeOrigenAtencionId + ", maeOrigenAtencionCodigo=" + maeOrigenAtencionCodigo + ", maeOrigenAtencionValor=" + maeOrigenAtencionValor + ", prioridad=" + prioridad + ", maeTipoServicioId=" + maeTipoServicioId + ", maeTipoServicioCodigo=" + maeTipoServicioCodigo + ", maeTipoServicioValor=" + maeTipoServicioValor + ", maeUbicacionId=" + maeUbicacionId + ", maeUbicacionCodigo=" + maeUbicacionCodigo + ", maeUbicacionValor=" + maeUbicacionValor + ", cama=" + cama + ", justificacionClinica=" + justificacionClinica + ", maeTipoCodumentoId=" + maeTipoCodumentoId + ", maeTipoDumentoCodigo=" + maeTipoDumentoCodigo + ", nombreProfesional=" + nombreProfesional + ", documentoProfesional=" + documentoProfesional + ", cargoProfesional=" + cargoProfesional + ", telefonoProfesional=" + telefonoProfesional + ", celularProfesional=" + celularProfesional + ", fuenteOrigen=" + fuenteOrigen + ", peProgramaEspecialValor=" + peProgramaEspecialValor + ", peProgramaEspecialCodigo=" + peProgramaEspecialCodigo + ", maeGuiaManejoIntegralId=" + maeGuiaManejoIntegralId + ", maeGuiaManejoIntegralCodigo=" + maeGuiaManejoIntegralCodigo + ", maeGuiaManejoIntegralValor=" + maeGuiaManejoIntegralValor + ", cuotaModeradora=" + cuotaModeradora + ", cuotaRecuperacion=" + cuotaRecuperacion + ", copago=" + copago + ", auAnexo3AdjuntosList=" + auAnexo3AdjuntosList + ", auAnexos4List=" + auAnexos4List + ", asegAfiliadoId=" + asegAfiliadoId + ", auAnexo3CargaId=" + auAnexo3CargaId + ", cntPrestadorSedeId=" + cntPrestadorSedeId + ", cntProfesionaleId=" + cntProfesionaleId + ", gnEmpresaId=" + gnEmpresaId + ", peProgramaId=" + peProgramaId + ", auAnexo3ItemsList=" + auAnexo3ItemsList + ", auAnexo3DiagnosticosList=" + auAnexo3DiagnosticosList + ", auAnexo3AfiliadosList=" + auAnexo3AfiliadosList + ", auAnexo3TutelasList=" + auAnexo3TutelasList + ", auRechazosList=" + auRechazosList + ", auSolicitudAdjuntosList=" + auSolicitudAdjuntosList + ", auAnexo3HistoricosList=" + auAnexo3HistoricosList + ", consecutivo=" + consecutivo + ", cargaFila=" + cargaFila + ", version=" + version + ", maeModalidadTecnologiaId=" + maeModalidadTecnologiaId + ", maeModalidadTecnologiaCodigo=" + maeModalidadTecnologiaCodigo + ", maeModalidadTecnologiaValor=" + maeModalidadTecnologiaValor + ", maeFinalidadTecnologiaId=" + maeFinalidadTecnologiaId + ", maeFinalidadTecnologiaCodigo=" + maeFinalidadTecnologiaCodigo + ", maeFinalidadTecnologiaValor=" + maeFinalidadTecnologiaValor + ", direccionAlternativa=" + direccionAlternativa + ", objetoDiagnostico=" + objetoDiagnostico + ", objetoTecnologia=" + objetoTecnologia + ", maeUbicacionPaciente=" + maeUbicacionPaciente + ", esEditar=" + esEditar + ", fechaDesdeHistoria=" + fechaDesdeHistoria + ", fechaHastaHistoria=" + fechaHastaHistoria + ", evaluacionHistoria=" + evaluacionHistoria + ", maeTipoDocumentoArchivo=" + maeTipoDocumentoArchivo + ", objetoIps=" + objetoIps + ", esAuditar=" + esAuditar + ", tieneRescates=" + tieneRescates + ", cantidadAutorizaciones=" + cantidadAutorizaciones + ", cantidadTecnologias=" + cantidadTecnologias + ", estadoProcesoActual=" + estadoProcesoActual + ", listaPosfechados=" + listaPosfechados + ", listaDevoluciones=" + listaDevoluciones + ", listaBitacoras=" + listaBitacoras + ", auAnexo2RescateList=" + auAnexo2RescateList + ", maeEspecialidadProfesional=" + maeEspecialidadProfesional + ", newProfesional=" + newProfesional + ", objetoEspecialidad=" + objetoEspecialidad + ", objetoContratoDetalle=" + objetoContratoDetalle + ", objetoCotizacion=" + objetoCotizacion + ", observacionAuditar=" + observacionAuditar + ", comentario=" + comentario + ", cargaMasiva=" + cargaMasiva + ", webService=" + webService + ", objetoTutela=" + objetoTutela + ", errorCarga=" + errorCarga + ", idMotivo=" + idMotivo + ", errores=" + errores + ", erroresAutomatica=" + erroresAutomatica + ", mensajeAutomatica=" + mensajeAutomatica + ", automatica=" + automatica + ", listaAutomaticos=" + listaAutomaticos + ", objetoAnexo4=" + objetoAnexo4 + ", preAutorizacion=" + preAutorizacion + '}';
    }

}
