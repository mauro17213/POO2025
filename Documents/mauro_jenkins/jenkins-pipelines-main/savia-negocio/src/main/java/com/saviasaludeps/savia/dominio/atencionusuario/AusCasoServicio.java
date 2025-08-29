/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jhonatan Jimenez
 */
public class AusCasoServicio extends Auditoria implements  Cloneable {

    public static final int LONGITUD_CAMPO_CORTO = 20;
    public static final int CANTIDAD_SERVICIOS_CARGA_MASIVA = 1;
    public static final int CANTIDAD_SERVICIOS_RESUELTOS_CARGA_MASIVA = 0;
    private static final long serialVersionUID = 1L;
    
    public static final int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    public static final int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public static final int TIPO_TECNOLOGIA_INSUMO = 3;
    public static final int TIPO_TECNOLOGIA_PAQUETE = 4;
    public static final int TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO = 5;
    
    public static final String TIPO_TECNOLOGIA_TECNOLOGIA_STR = "Tecnología";
    public static final String TIPO_TECNOLOGIA_MEDICAMENTO_STR = "Medicamento";
    public static final String TIPO_TECNOLOGIA_INSUMO_STR = "Insumo";
    public static final String TIPO_TECNOLOGIA_PAQUETE_STR = "Paquete";
    public static final String TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO_STR = "Agrupador Medicamento";
    
    public final static short TAMANIO_MAXIMO_CAMPOS_LARGOS = 30;
    
    public static final String ESTADO_CASO_SERVICIO_ESTUDIO = "3";
    public static final String ESTADO_CASO_SERVICIO_RECHAZADO = "5";
    public static final String ESTADO_CASO_SERVICIO_CERRADO = "6";
    
    private int pos;
    private Integer id;
    private int maeServicioAmbitoId;
    private String maeServicioAmbitoCodigo;
    private String maeServicioAmbitoValor;
    private int maeServicioMotivoId;
    private String maeServicioMotivoCodigo;
    private String maeServicioMotivoValor;
    private int maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private Integer maeTipoAdministrativoId;
    private String maeTipoAdministrativoCodigo;
    private String maeTipoAdministrativoValor;
    private Date fechaCumplimiento;
    private Integer maDiagnosticosId;
    private String maDiagnosticosCodigo;
    private String maDiagnosticosValor;
    private int maServicioId;
    private String maServicioCodigo;
    private String maServicioValor;
    private Short tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private int cantidad;
    private String descripcion;
    private Boolean pertinencia;
    private MaDiagnostico maDiagnostico;
    private Integer maePatologiaId;
    private String maePatologiaCodigo;
    private String maePatologiaValor;
    private CntPrestador cntIpsPrescriptoraId;
    private CntPrestador cntIpsId;
    private AusCaso casosId;
    private CntPrestadorSede objetoPrestadorSede;
    private CntPrestadorSede objetoPrestadorIps;
    private String objetoPrestadorIpsValor;
    private String objetoPrestadorSedeValor;
    private String maDiagnosticoNombreCorto;
    private Boolean medicamento;
    private Integer medicamentoCobertura;
    private Date fechaAplica;
    private Boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    
    //2024-08-22 jyperez nuevos campos
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;
    
    //2025-03-21 jyperez nuevo campo
    private boolean asignacionCita;

    private Integer servicioAtributoIps;

    private Usuario asignadoUsuariosId;
    private List<AusAdjunto> adjuntosList;

    private List<AusServicioGestionHistorico> servicioGestionesList;

    private String datosCreacion;
    private String datosModificacion;

    private boolean deshabilitarCampoAsignado;
    private boolean deshabilitarCampoEspecialidad;
    private boolean deshabilitarCampoCantidad;
    private boolean deshabilitarCampoDiagnostico;
    private boolean deshabilitarCampoIpsPres;
    private boolean desHabilitarCampoDescripcion;
    private boolean habiltarCampoProcedimiento;

    private boolean habilitarMedicamento;
    private boolean habilitarMedicamentoCobertura;

    private boolean habilitarServicioTipoAdministracion;
    private boolean habilitarServicioDestino;
    private boolean habilitarServicioProcedimiento;
    private boolean habilitarServicioEspecialidad;
    private boolean habilitarServicioCIEX;
    private boolean habilitarServicioPatologia;

    public static final int ESTADO_ASIGNADO = 1;
    public static final int ESTADO_RECIBIDO = 2;
    public static final int ESTADO_ESTUDIO = 3;
    public static final int ESTADO_RESUELTO = 4;
    public static final int ESTADO_RECHAZADO = 5;
    public static final int ESTADO_CERRADO = 6;
    public static final int ESTADO_ANULADO = 7;
    public static final int ESTADO_AUDITAR = 8;
    public static final int ESTADO_ASIGNADO_MIO = 58383;

    public static final int ACCION_INSERTAR = 0;
    public static final int ACCION_BORRAR = 1;
    public static final int ACCION_NINGUNO = 2;

    private int accion = ACCION_INSERTAR;
    //private int idUsuarioResponsable;
    private Usuario idUsuarioResponsable;

    private Maestro objetoIpsPrescriptora;
    private Maestro objetoIps;
    private Maestro objetoTipoServicio;
    //private MaEspecialidad objetoTipoServicio;
    private Maestro objetoDiagnostico;
    private Maestro objetoPatologia;
    private Maestro objetoMotivo;
    private Maestro objetoAmbito;
    private Maestro objetoEspecialidadServicio;

    private Date fechaVencimiento;
    private int diasVencidos;
    private String colorVencimiento;

    public static Map<String, Integer> getTiposEstado() {
        Map<String, Integer> tipos = new LinkedHashMap<>();
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_ASIGNADO), AusCasoServicio.ESTADO_ASIGNADO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_RECIBIDO), AusCasoServicio.ESTADO_RECIBIDO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_ESTUDIO), AusCasoServicio.ESTADO_ESTUDIO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_RESUELTO), AusCasoServicio.ESTADO_RESUELTO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_RECHAZADO), AusCasoServicio.ESTADO_RECHAZADO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_CERRADO), AusCasoServicio.ESTADO_CERRADO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_ANULADO), AusCasoServicio.ESTADO_ANULADO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_AUDITAR), AusCasoServicio.ESTADO_AUDITAR);
        return tipos;
    }

    public static Map<String, Integer> getTiposEstadoEnCreacion() {
        Map<String, Integer> tipos = new LinkedHashMap<>();
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_ASIGNADO), AusCasoServicio.ESTADO_ASIGNADO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_ESTUDIO), AusCasoServicio.ESTADO_ESTUDIO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_RESUELTO), AusCasoServicio.ESTADO_RESUELTO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_AUDITAR), AusCasoServicio.ESTADO_AUDITAR);
        return tipos;
    }

    public static Map<String, Integer> getTiposEstadoEnGestion() {
        Map<String, Integer> tipos = new LinkedHashMap<>();
        //tipos.put( AusCasoServicio.getEstadoStr(ESTADO_ASIGNADO), AusCasoServicio.ESTADO_ASIGNADO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_ESTUDIO), AusCasoServicio.ESTADO_ESTUDIO);
        //tipos.put( AusCasoServicio.getEstadoStr(ESTADO_RECIBIDO), AusCasoServicio.ESTADO_RECIBIDO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_RECHAZADO), AusCasoServicio.ESTADO_RECHAZADO);
        //tipos.put( AusCasoServicio.getEstadoStr(ESTADO_RESUELTO),Servicio.ESTADO_RESUELTO);
        tipos.put(AusCasoServicio.getEstadoStr(ESTADO_CERRADO), AusCasoServicio.ESTADO_CERRADO);
        return tipos;
    }

    public String getTipoEstadoStr() {
        return AusCasoServicio.getEstadoStr(getMaeEstadoId());
    }

    public static String getEstadoStr(int tipoEstado) {
        String str;
        switch (tipoEstado) {
            case ESTADO_ASIGNADO:
                str = "Asignado";
                break;
            case ESTADO_RECIBIDO:
                str = "Recibido";
                break;
            case ESTADO_ESTUDIO:
                str = "Estudio";
                break;
            case ESTADO_RESUELTO:
                str = "Resuelto";
                break;
            case ESTADO_RECHAZADO:
                str = "Rechazado";
                break;
            case ESTADO_CERRADO:
                str = "Cerrado";
                break;
            case ESTADO_ANULADO:
                str = "Anulado";
                break;
            //case ESTADO_ASIGNADO_MIO:
            //str = "Asignado";
            //break;

            default:
                str = "Ninguno";
                break;
        }
        return str;
    }

    public String getPertinenciaStr() {
        String pertinencia = "N/A";
        if (getPertinencia() != null) {
            pertinencia = (getPertinencia()) ? "SI" : "NO";
        }
        return pertinencia;
    }

    public AusCasoServicio() {

    }

    public AusCasoServicio(AusCasoServicio servicio) {
        this.maeEstadoId = servicio.getMaeEstadoId();
        this.maeServicioAmbitoId = servicio.getMaeServicioAmbitoId();
        this.maeTipoAdministrativoId = servicio.getMaeTipoAdministrativoId();
        this.objetoMotivo = servicio.getObjetoMotivo();
        this.habiltarCampoProcedimiento = servicio.isHabiltarCampoProcedimiento();
        this.tipoTecnologia = servicio.getTipoTecnologia();
        this.maTecnologiaId = servicio.getMaTecnologiaId();
        this.maTecnologiaCodigo = servicio.getMaTecnologiaCodigo();
        this.maTecnologiaValor = servicio.getMaTecnologiaValor();
        this.maServicioId = servicio.getMaServicioId();
        this.maServicioCodigo = servicio.getMaServicioCodigo();
        this.maServicioValor = servicio.getMaServicioValor();
        this.fechaCumplimiento = servicio.getFechaCumplimiento();
        this.descripcion = servicio.getDescripcion();
        //this.objetoDiagnostico = servicio.getObjetoDiagnostico();
        this.maDiagnosticosId = servicio.getMaDiagnosticosId();
        this.maDiagnosticosCodigo = servicio.getMaDiagnosticosCodigo();
        this.maDiagnosticosValor = servicio.getMaDiagnosticosValor();
        this.maePatologiaId = servicio.getMaePatologiaId();
        this.maePatologiaCodigo = servicio.getMaePatologiaCodigo();
        this.maePatologiaValor = servicio.getMaePatologiaValor();
        this.objetoPrestadorSede = servicio.getObjetoPrestadorSede();
        this.objetoPrestadorSedeValor = servicio.getObjetoPrestadorSedeValor();
        this.objetoPrestadorIps = servicio.getObjetoPrestadorIps();
        this.objetoPrestadorIpsValor = servicio.getObjetoPrestadorIpsValor();
        this.idUsuarioResponsable = servicio.getIdUsuarioResponsable();
        this.medicamento = servicio.getMedicamento();
        this.medicamentoCobertura = servicio.getMedicamentoCobertura();
        this.servicioAtributoIps = servicio.getServicioAtributoIps();
    }

    public AusCasoServicio(Integer id) {
        this.id = id;
    }

    public AusCasoServicio(Integer id, int maeEstadoId) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public Integer getMaeTipoAdministrativoId() {
        return maeTipoAdministrativoId;
    }

    public void setMaeTipoAdministrativoId(Integer maeTipoAdministrativoId) {
        this.maeTipoAdministrativoId = maeTipoAdministrativoId;
    }

    public String getMaeTipoAdministrativoCodigo() {
        return maeTipoAdministrativoCodigo;
    }

    public void setMaeTipoAdministrativoCodigo(String maeTipoAdministrativoCodigo) {
        this.maeTipoAdministrativoCodigo = maeTipoAdministrativoCodigo;
    }

    public String getMaeTipoAdministrativoValor() {
        return maeTipoAdministrativoValor;
    }

    public void setMaeTipoAdministrativoValor(String maeTipoAdministrativoValor) {
        this.maeTipoAdministrativoValor = maeTipoAdministrativoValor;
    }

    public Date getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    public void setFechaCumplimiento(Date fechaCumplimiento) {
        this.fechaCumplimiento = fechaCumplimiento;
    }

    public Integer getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(Integer maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    public String getMaDiagnosticosCodigo() {
        return maDiagnosticosCodigo;
    }

    public void setMaDiagnosticosCodigo(String maDiagnosticosCodigo) {
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
    }

    public String getMaDiagnosticosValor() {
        return maDiagnosticosValor;
    }

    public void setMaDiagnosticosValor(String maDiagnosticosValor) {
        this.maDiagnosticosValor = maDiagnosticosValor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPertinencia() {
        return pertinencia;
    }

    public void setPertinencia(Boolean pertinencia) {
        this.pertinencia = pertinencia;
    }

    public MaDiagnostico getMaDiagnostico() {
        return maDiagnostico;
    }

    public void setMaDiagnostico(MaDiagnostico maDiagnostico) {
        this.maDiagnostico = maDiagnostico;
    }

    public AusCaso getCasosId() {
        return casosId;
    }

    public void setCasosId(AusCaso casosId) {
        this.casosId = casosId;
    }

    public CntPrestadorSede getObjetoPrestadorSede() {
        return objetoPrestadorSede;
    }

    public void setObjetoPrestadorSede(CntPrestadorSede objetoPrestadorSede) {
        this.objetoPrestadorSede = objetoPrestadorSede;
    }

    public CntPrestadorSede getObjetoPrestadorIps() {
        return objetoPrestadorIps;
    }

    public void setObjetoPrestadorIps(CntPrestadorSede objetoPrestadorIps) {
        this.objetoPrestadorIps = objetoPrestadorIps;
    }

    public String getObjetoPrestadorIpsValor() {
        return objetoPrestadorIpsValor;
    }

    public void setObjetoPrestadorIpsValor(String objetoPrestadorIpsValor) {
        this.objetoPrestadorIpsValor = objetoPrestadorIpsValor;
    }

    public String getObjetoPrestadorSedeValor() {
        return objetoPrestadorSedeValor;
    }

    public void setObjetoPrestadorSedeValor(String objetoPrestadorSedeValor) {
        this.objetoPrestadorSedeValor = objetoPrestadorSedeValor;
    }

    public Usuario getAsignadoUsuariosId() {
        return asignadoUsuariosId;
    }

    public void setAsignadoUsuariosId(Usuario asignadoUsuariosId) {
        this.asignadoUsuariosId = asignadoUsuariosId;
    }

    public List<AusAdjunto> getAdjuntosList() {
        if (adjuntosList == null) {
            adjuntosList = new ArrayList<>();
        }
        return adjuntosList;
    }

    public void setAdjuntosList(List<AusAdjunto> adjuntosList) {
        this.adjuntosList = adjuntosList;
    }

    public void setDatosCreacion(String datosCreacion) {
        this.datosCreacion = datosCreacion;
    }

    public int getMaeServicioAmbitoId() {
        return maeServicioAmbitoId;
    }

    public void setMaeServicioAmbitoId(int maeServicioAmbitoId) {
        this.maeServicioAmbitoId = maeServicioAmbitoId;
    }

    public String getMaeServicioAmbitoCodigo() {
        return maeServicioAmbitoCodigo;
    }

    public void setMaeServicioAmbitoCodigo(String maeServicioAmbitoCodigo) {
        this.maeServicioAmbitoCodigo = maeServicioAmbitoCodigo;
    }

    public String getMaeServicioAmbitoValor() {
        return maeServicioAmbitoValor;
    }

    public void setMaeServicioAmbitoValor(String maeServicioAmbitoValor) {
        this.maeServicioAmbitoValor = maeServicioAmbitoValor;
    }

    public int getMaeServicioMotivoId() {
        return maeServicioMotivoId;
    }

    public void setMaeServicioMotivoId(int maeServicioMotivoId) {
        this.maeServicioMotivoId = maeServicioMotivoId;
    }

    public String getMaeServicioMotivoCodigo() {
        return maeServicioMotivoCodigo;
    }

    public void setMaeServicioMotivoCodigo(String maeServicioMotivoCodigo) {
        this.maeServicioMotivoCodigo = maeServicioMotivoCodigo;
    }

    public String getMaeServicioMotivoValor() {
        return maeServicioMotivoValor;
    }

    public void setMaeServicioMotivoValor(String maeServicioMotivoValor) {
        this.maeServicioMotivoValor = maeServicioMotivoValor;
    }

    public int getMaServicioId() {
        return maServicioId;
    }

    public void setMaServicioId(int maServicioId) {
        this.maServicioId = maServicioId;
    }

    public String getMaServicioCodigo() {
        return maServicioCodigo;
    }

    public void setMaServicioCodigo(String maServicioCodigo) {
        this.maServicioCodigo = maServicioCodigo;
    }

    public String getMaServicioValor() {
        return maServicioValor;
    }

    public void setMaServicioValor(String maServicioValor) {
        this.maServicioValor = maServicioValor;
    }

    public Short getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Short tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }
    
    public String getTipoTecnologiaStr() {
        String str="";
        if (tipoTecnologia != null) {
            switch (tipoTecnologia) {
                case TIPO_TECNOLOGIA_TECNOLOGIA:
                    str = TIPO_TECNOLOGIA_TECNOLOGIA_STR;
                    break;
                case TIPO_TECNOLOGIA_MEDICAMENTO:
                    str = TIPO_TECNOLOGIA_MEDICAMENTO_STR;
                    break;
                case TIPO_TECNOLOGIA_INSUMO:
                    str = TIPO_TECNOLOGIA_INSUMO_STR;
                    break;
                case TIPO_TECNOLOGIA_PAQUETE:
                    str = TIPO_TECNOLOGIA_PAQUETE_STR;
                    break;
                case TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO:
                    str = TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO_STR;
                    break;
                default:
                    str = "";
                    break;
            }
        }
        return str;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }
    
    public String getMaTecnologiaValorCorto() {
        String tecnologiaValorCorto = "";
        if (getMaTecnologiaValor()!= null) {
            tecnologiaValorCorto = getMaTecnologiaValor();
            if (getMaTecnologiaValor().length() >= TAMANIO_MAXIMO_CAMPOS_LARGOS) {
                return tecnologiaValorCorto.substring(0, TAMANIO_MAXIMO_CAMPOS_LARGOS) + "..";
            } else {
                return tecnologiaValorCorto;
            }
        }
        return tecnologiaValorCorto;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaePatologiaId() {
        return maePatologiaId;
    }

    public void setMaePatologiaId(Integer maePatologiaId) {
        this.maePatologiaId = maePatologiaId;
    }

    public String getMaePatologiaCodigo() {
        return maePatologiaCodigo;
    }

    public void setMaePatologiaCodigo(String maePatologiaCodigo) {
        this.maePatologiaCodigo = maePatologiaCodigo;
    }

    public String getMaePatologiaValor() {
        return maePatologiaValor;
    }

    public void setMaePatologiaValor(String maePatologiaValor) {
        this.maePatologiaValor = maePatologiaValor;
    }

    public CntPrestador getCntIpsPrescriptoraId() {
        return cntIpsPrescriptoraId;
    }

    public void setCntIpsPrescriptoraId(CntPrestador cntIpsPrescriptoraId) {
        this.cntIpsPrescriptoraId = cntIpsPrescriptoraId;
    }

    public CntPrestador getCntIpsId() {
        return cntIpsId;
    }

    public void setCntIpsId(CntPrestador cntIpsId) {
        this.cntIpsId = cntIpsId;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Usuario getIdUsuarioResponsable() {
        if (idUsuarioResponsable == null) {
            idUsuarioResponsable = new Usuario();
        }
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(Usuario idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    public String getDatosCreacion() {
        datosCreacion = "El usuario " + this.getUsuarioCrea() + " el dia " + this.getFechaHoraCrea()
                + " desde la terminal " + this.getTerminalCrea() + " realizó la creación del registro.";

        return datosCreacion;
    }

    public boolean isDeshabilitarCampoAsignado() {
        return deshabilitarCampoAsignado;
    }

    public void setDeshabilitarCampoAsignado(boolean deshabilitarCampoAsignado) {
        this.deshabilitarCampoAsignado = deshabilitarCampoAsignado;
    }

    public void setDatosModificacion(String datosModificacion) {
        this.datosModificacion = datosModificacion;
    }

    public boolean isDeshabilitarCampoEspecialidad() {
        return deshabilitarCampoEspecialidad;
    }

    public void setDeshabilitarCampoEspecialidad(boolean deshabilitarCampoEspecialidad) {
        this.deshabilitarCampoEspecialidad = deshabilitarCampoEspecialidad;
    }

    public boolean isDeshabilitarCampoCantidad() {
        return deshabilitarCampoCantidad;
    }

    public void setDeshabilitarCampoCantidad(boolean deshabilitarCampoCantidad) {
        this.deshabilitarCampoCantidad = deshabilitarCampoCantidad;
    }

    public boolean isDeshabilitarCampoDiagnostico() {
        return deshabilitarCampoDiagnostico;
    }

    public void setDeshabilitarCampoDiagnostico(boolean deshabilitarCampoDiagnostico) {
        this.deshabilitarCampoDiagnostico = deshabilitarCampoDiagnostico;
    }

    public boolean isDeshabilitarCampoIpsPres() {
        return deshabilitarCampoIpsPres;
    }

    public void setDeshabilitarCampoIpsPres(boolean deshabilitarCampoIpsPres) {
        this.deshabilitarCampoIpsPres = deshabilitarCampoIpsPres;
    }

    public boolean isDesHabilitarCampoDescripcion() {
        return desHabilitarCampoDescripcion;
    }

    public void setDesHabilitarCampoDescripcion(boolean desHabilitarCampoDescripcion) {
        this.desHabilitarCampoDescripcion = desHabilitarCampoDescripcion;
    }

    public Maestro getObjetoIps() {
        return objetoIps;
    }

    public Maestro getObjetoIpsPrescriptora() {
        return objetoIpsPrescriptora;
    }

    public void setObjetoIpsPrescriptora(Maestro objetoIpsPrescriptora) {
        this.objetoIpsPrescriptora = objetoIpsPrescriptora;
    }

    public void setObjetoIps(Maestro objetoIps) {
        this.objetoIps = objetoIps;
    }

    public Maestro getObjetoTipoServicio() {
        return objetoTipoServicio;
    }

    public void setObjetoTipoServicio(Maestro objetoTipoServicio) {
        this.objetoTipoServicio = objetoTipoServicio;
    }

    public Maestro getObjetoDiagnostico() {
        return objetoDiagnostico;
    }

    public void setObjetoDiagnostico(Maestro objetoDiagnostico) {
        this.objetoDiagnostico = objetoDiagnostico;
    }

    public Maestro getObjetoMotivo() {
        return objetoMotivo;
    }

    public void setObjetoMotivo(Maestro objetoMotivo) {
        this.objetoMotivo = objetoMotivo;
    }

    public Maestro getObjetoAmbito() {
        return objetoAmbito;
    }

    public void setObjetoAmbito(Maestro objetoAmbito) {
        this.objetoAmbito = objetoAmbito;
    }

    public Maestro getObjetoPatologia() {
        if(objetoPatologia == null){
            objetoPatologia = new Maestro();
        }
        return objetoPatologia;
    }

    public void setObjetoPatologia(Maestro objetoPatologia) {
        this.objetoPatologia = objetoPatologia;
    }

    public boolean isHabiltarCampoProcedimiento() {
        return habiltarCampoProcedimiento;
    }

    public void setHabiltarCampoProcedimiento(boolean habiltarCampoProcedimiento) {
        this.habiltarCampoProcedimiento = habiltarCampoProcedimiento;
    }

    public boolean isHabilitarMedicamento() {
        return habilitarMedicamento;
    }

    public void setHabilitarMedicamento(boolean habilitarMedicamento) {
        this.habilitarMedicamento = habilitarMedicamento;
    }

    public boolean isHabilitarMedicamentoCobertura() {
        return habilitarMedicamentoCobertura;
    }

    public void setHabilitarMedicamentoCobertura(boolean habilitarMedicamentoCobertura) {
        this.habilitarMedicamentoCobertura = habilitarMedicamentoCobertura;
    }

    public boolean isHabilitarServicioTipoAdministracion() {
        return habilitarServicioTipoAdministracion;
    }

    public void setHabilitarServicioTipoAdministracion(boolean habilitarServicioTipoAdministracion) {
        this.habilitarServicioTipoAdministracion = habilitarServicioTipoAdministracion;
    }

    public boolean isHabilitarServicioDestino() {
        return habilitarServicioDestino;
    }

    public void setHabilitarServicioDestino(boolean habilitarServicioDestino) {
        this.habilitarServicioDestino = habilitarServicioDestino;
    }

    public boolean isHabilitarServicioProcedimiento() {
        return habilitarServicioProcedimiento;
    }

    public void setHabilitarServicioProcedimiento(boolean habilitarServicioProcedimiento) {
        this.habilitarServicioProcedimiento = habilitarServicioProcedimiento;
    }

    public boolean isHabilitarServicioEspecialidad() {
        return habilitarServicioEspecialidad;
    }

    public void setHabilitarServicioEspecialidad(boolean habilitarServicioEspecialidad) {
        this.habilitarServicioEspecialidad = habilitarServicioEspecialidad;
    }

    public boolean isHabilitarServicioCIEX() {
        return habilitarServicioCIEX;
    }

    public void setHabilitarServicioCIEX(boolean habilitarServicioCIEX) {
        this.habilitarServicioCIEX = habilitarServicioCIEX;
    }

    public boolean isHabilitarServicioPatologia() {
        return habilitarServicioPatologia;
    }

    public void setHabilitarServicioPatologia(boolean habilitarServicioPatologia) {
        this.habilitarServicioPatologia = habilitarServicioPatologia;
    }

    public String getDatosModificacion() {
        if (!"".equals(this.getUsuarioModifica()) && this.getUsuarioModifica() != null) {
            datosModificacion = "El usuario " + this.getUsuarioModifica() + " el dia " + this.getFechaHoraModifica()
                    + " desde la terminal " + this.getTerminalModifica() + " realizó la modificación del registro.";
        }
        return datosModificacion;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public List<AusServicioGestionHistorico> getServicioGestionesList() {
        return servicioGestionesList;
    }

    public void setServicioGestionesList(List<AusServicioGestionHistorico> servicioGestionesList) {
        this.servicioGestionesList = servicioGestionesList;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getDiasVencidos() {
        return diasVencidos;
    }

    public void setDiasVencidos(int diasVencidos) {
        this.diasVencidos = diasVencidos;
    }

    public String getColorVencimiento() {
        int dias = getDiasVencimiento();
        if (getFechaCumplimiento() == null) {
            if (dias > 0) {
                colorVencimiento = "red";
            } else {
                colorVencimiento = "yellow";
            }
        } else {
            colorVencimiento = "green";
        }
        return colorVencimiento;
    }

    public void setColorVencimiento(String colorVencimiento) {
        this.colorVencimiento = colorVencimiento;
    }

    public Maestro getObjetoEspecialidadServicio() {
        return objetoEspecialidadServicio;
    }

    public void setObjetoEspecialidadServicio(Maestro objetoEspecialidadServicio) {
        this.objetoEspecialidadServicio = objetoEspecialidadServicio;
    }

    public Boolean getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Boolean medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getMedicamentoCobertura() {
        return medicamentoCobertura;
    }

    public void setMedicamentoCobertura(Integer medicamentoCobertura) {
        this.medicamentoCobertura = medicamentoCobertura;
    }

    public Date getFechaAplica() {
        return fechaAplica;
    }

    public void setFechaAplica(Date fechaAplica) {
        this.fechaAplica = fechaAplica;
    }

    public Integer getServicioAtributoIps() {
        return servicioAtributoIps;
    }

    public void setServicioAtributoIps(Integer servicioAtributoIps) {
        this.servicioAtributoIps = servicioAtributoIps;
    }

    public String getMaDiagnosticoNombreCorto() {
        if (getMaDiagnostico() != null && getMaDiagnostico().getNombre() != null) {
            maDiagnosticoNombreCorto = getMaDiagnostico().getNombre();
            if (getMaDiagnostico().getNombre().length() >= LONGITUD_CAMPO_CORTO) {
                return maDiagnosticoNombreCorto.substring(0, LONGITUD_CAMPO_CORTO) + "..";
            } else {
                return maDiagnosticoNombreCorto;
            }
        }
        return maDiagnosticoNombreCorto;
    }

    public void asignarHorasVencimiento(Date fechaCreacion, int horasVencimiento, List<Date> fechasHabiles) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCreacion);
        calendar.add(Calendar.HOUR, horasVencimiento);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        int dias = 0;
        for (int i = 0; i <= dias; i++) {
            Date f1 = calendar.getTime();
            String date = formato.format(f1);
            for (Date fechaHabil : fechasHabiles) {
                String date2 = formato.format(fechaHabil);
                if (date.equals(date2)) {
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    dias++;
                }
            }
        }
        setFechaVencimiento(calendar.getTime());
    }

    public String getAplicaMedicamentoStr() {
        String aplica = "";
        if (medicamento != null) {
            if (medicamento) {
                aplica = "Si";
            } else {
                aplica = "No";
            }
        }

        return aplica;
    }

    public String getAplicaMedicamentoCoberturaStr() {
        String aplica = "";
        if (medicamentoCobertura != null) {
            switch (medicamentoCobertura) {
                case 0:
                    aplica = "NO POS";
                    break;
                case 1:
                    aplica = "POS";
                    break;
            }
        }

        return aplica;
    }

    public String getServicioAtribuidoIPSStr() {
        String aplica = "";
        if (servicioAtributoIps != null) {
            switch (servicioAtributoIps) {
                case 0:
                    aplica = "No";
                    break;
                case 1:
                    aplica = "Si";
                    break;
                case 3:
                    aplica = "No Aplica";
                    break;
            }
        }
        return aplica;
    }

    public int getDiasVencimiento() {
        int diasVencidos = 0;

        if (getFechaCumplimiento() == null) {
            if (getServicioGestionesList() != null
                    && !getServicioGestionesList().isEmpty() && getServicioGestionesList().size() > 0) {
                AusServicioGestionHistorico ausServicioGestionHistorico = getServicioGestionesList().get(getServicioGestionesList().size() - 1);
                if (ausServicioGestionHistorico.getMaeEstadoValor().equals("Asignado")) {
                    Date fechaActual = new Date();
                    diasVencidos = (int) ((fechaActual.getTime() - ausServicioGestionHistorico.getFechaHoraCrea().getTime()) / 86400000);
                }
            }
        } else {
            if (getServicioGestionesList() != null
                    && !getServicioGestionesList().isEmpty() && getServicioGestionesList().size() > 0) {
                AusServicioGestionHistorico ausServicioGestionHistorico = getServicioGestionesList().get(getServicioGestionesList().size() - 1);
                if (ausServicioGestionHistorico.getMaeEstadoValor().equals("Asignado")) {
                    diasVencidos = (int) ((getFechaCumplimiento().getTime() - ausServicioGestionHistorico.getFechaHoraCrea().getTime()) / 86400000);
                }
            }
        }
        if (diasVencidos < 0) {
            diasVencidos = 0;
        }
        this.setDiasVencidos(diasVencidos);
        return diasVencidos;

    }

    /* public int getDiasVencimiento() {
        int diasVencidos = 0;
        if (getFechaCumplimiento() == null) {
            if (getFechaVencimiento() != null) {
                Date fechaActual = new Date();
                diasVencidos = (int) ((fechaActual.getTime() - getFechaVencimiento().getTime()) / 86400000);
            }
        } else {
            if (getFechaVencimiento() != null) {
                diasVencidos = (int) ((getFechaCumplimiento().getTime() - getFechaVencimiento().getTime()) / 86400000);
            }
        }
        if (diasVencidos < 0) {
            diasVencidos = 0;
        }
        this.setDiasVencidos(diasVencidos);
        return diasVencidos;
    }*/
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AusCasoServicio)) {
            return false;
        }
        AusCasoServicio other = (AusCasoServicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AusCasoServicio{" + "id=" + id + ", maeServicioAmbitoId=" + maeServicioAmbitoId + ", maeServicioAmbitoCodigo=" + maeServicioAmbitoCodigo + ", maeServicioAmbitoValor=" + maeServicioAmbitoValor + ", maeServicioMotivoId=" + maeServicioMotivoId + ", maeServicioMotivoCodigo=" + maeServicioMotivoCodigo + ", maeServicioMotivoValor=" + maeServicioMotivoValor + ", maeEstadoId=" + maeEstadoId + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + ", fechaCumplimiento=" + fechaCumplimiento + ", maServicioId=" + maServicioId + ", maServicioCodigo=" + maServicioCodigo + ", maServicioValor=" + maServicioValor + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", cantidad=" + cantidad + ", descripcion=" + descripcion + ", pertinencia=" + pertinencia + ", maDiagnostico=" + maDiagnostico + ", maePatologiaId=" + maePatologiaId + ", maePatologiaCodigo=" + maePatologiaCodigo + ", maePatologiaValor=" + maePatologiaValor + ", cntIpsPrescriptoraId=" + cntIpsPrescriptoraId + ", cntIpsId=" + cntIpsId + ", fechaInicioVigencia=" + fechaInicioVigencia + ", asignacionCita=" + asignacionCita + '}';
    }
    
    @Override
    public Object clone()throws CloneNotSupportedException{  
	return (AusCasoServicio)super.clone();  
   }

    /**
     * @return the fechaInicioVigencia
     */
    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    /**
     * @param fechaInicioVigencia the fechaInicioVigencia to set
     */
    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    /**
     * @return the fechaFinVigencia
     */
    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    /**
     * @param fechaFinVigencia the fechaFinVigencia to set
     */
    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    /**
     * @return the asignacionCita
     */
    public boolean isAsignacionCita() {
        return asignacionCita;
    }

    /**
     * @param asignacionCita the asignacionCita to set
     */
    public void setAsignacionCita(boolean asignacionCita) {
        this.asignacionCita = asignacionCita;
    }
    
}
