/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PeAfiliadosPrograma extends Auditoria implements Serializable {

    public final static int ORIGEN_HOSPITALIZACION = 6;
    public final static int ORIGEN_ANEXOS3 = 3;
    //Ambios
    public final static int AMBITO_AMBULATORIO = 0;
    public final static int AMBITO_HOSPITALARIO = 1;

    private Integer id;
    private boolean activo;
    private Integer maeCausaActivoId;
    private String maeCausaActivoCodigo;
    private String maeCausaActivoValor;
    private Integer maeCausaInactivoId;
    private String maeCausaInactivoCodigo;
    private String maeCausaInactivoValor;
    private Date fechaInicioPrograma;
    private Date fechaFinPrograma;
    private Date fechaDiagnostico;
    private Integer estadoDiagnostico;
    private Integer maeRegionCorporalId;
    private String maeRegionCorporalCodigo;
    private String maeRegionCorporalValor;
    private Integer maeMedioDxId;
    private String maeMedioDxCodigo;
    private String maeMedioDxValor;
    private PePrograma pePrograma;
    private AsegAfiliado asegAfiliado;
    private CntPrestadorSede cntPrestadorSede;
    private MaDiagnostico maDiagnosticoPrincipal;
    private MaDiagnostico maDiagnostico2;
    private MaDiagnostico maDiagnostico3;
    private Maestro maeRegionCorporal;
    private Maestro maeMedioDx;
    private Maestro maeCausaActivoObj;
    private Maestro maeCausaInactivoObj;
    private Usuario gnUsuario;
    private List<PeGestion> listaPeGestion;
    private List<PeAdjunto> listaPeAdjunto;
    private transient Integer sincronizado;
    private transient String programaCargaMasiva;
    private String errorCarga;
    private Integer fuenteOrigen;
    private Integer idSolicitudOrigen;
    private PeAfiliadoSugerido peAfiliadoSugerido;
    private AucHospitalizacion aucHospitalizacion;
    private Integer tipoPaciente;
    private boolean actualizar;
    private boolean adherente;
    private boolean disentimiento;
    private boolean planificacionFamiliar;
    private Integer estadoSivigila;
    private Integer maeCausaDescarteId;
    private String maeCausaDescarteCodigo;
    private String maeCausaDescarteValor;
    private Integer notificadoSivigila;

    private List<PeAfiliadoDiagnostico> peAfiliadoDiagnosticoList;
    private PeAfiliadoDiagnostico objetoDiagnostico;
    private List<PeAfiliadoDiagnostico> peAfiliadoDiagnosticoAuxList;
    private boolean puedeGestionar;
    private Integer idSentencia;
    private String codigoSentencia;
    private String valorSentencia;

    public PeAfiliadosPrograma() {
        peAfiliadoDiagnosticoList = new ArrayList<>();
        peAfiliadoDiagnosticoAuxList = new ArrayList<>();
    }

    public PeAfiliadosPrograma(Integer id) {
        this.id = id;
    }

    public PeAfiliadosPrograma(PeAfiliadosPrograma afiliadoPrograma) {//constructor para crear una copia de un afiliado programa con datos necesarios para insertar un nuevo registro
        this.id = afiliadoPrograma.getId();
        this.tipoPaciente = afiliadoPrograma.getTipoPaciente();
        this.activo = afiliadoPrograma.getActivo();
        this.maeCausaActivoId = afiliadoPrograma.getMaeCausaActivoId();
        this.maeCausaActivoCodigo = afiliadoPrograma.getMaeCausaActivoCodigo();
        this.maeCausaActivoValor = afiliadoPrograma.getMaeCausaActivoValor();
        this.maeCausaInactivoId = afiliadoPrograma.getMaeCausaInactivoId();
        this.maeCausaInactivoCodigo = afiliadoPrograma.getMaeCausaInactivoCodigo();
        this.maeCausaInactivoValor = afiliadoPrograma.getMaeCausaInactivoValor();
        this.fechaInicioPrograma = afiliadoPrograma.getFechaInicioPrograma();
        this.fechaFinPrograma = afiliadoPrograma.getFechaFinPrograma();
        this.maDiagnosticoPrincipal = afiliadoPrograma.getMaDiagnosticoPrincipal();
        this.maDiagnostico2 = afiliadoPrograma.getMaDiagnostico2();
        this.maDiagnostico3 = afiliadoPrograma.getMaDiagnostico3();
        this.fechaDiagnostico = afiliadoPrograma.getFechaDiagnostico();
        this.maeRegionCorporalId = afiliadoPrograma.getMaeRegionCorporalId();
        this.maeRegionCorporalCodigo = afiliadoPrograma.getMaeRegionCorporalCodigo();
        this.maeRegionCorporalValor = afiliadoPrograma.getMaeRegionCorporalValor();
        this.maeMedioDxId = afiliadoPrograma.getMaeMedioDxId();
        this.maeMedioDxCodigo = afiliadoPrograma.getMaeMedioDxCodigo();
        this.maeMedioDxValor = afiliadoPrograma.getMaeMedioDxValor();
        this.fuenteOrigen = afiliadoPrograma.getFuenteOrigen();
        this.idSolicitudOrigen = afiliadoPrograma.getIdSolicitudOrigen();
        this.adherente = afiliadoPrograma.getAdherente();
        this.disentimiento = afiliadoPrograma.getDisentimiento();
        this.estadoSivigila = afiliadoPrograma.getEstadoSivigila();
        this.notificadoSivigila = afiliadoPrograma.getNotificadoSivigila();
        this.maeCausaDescarteId = afiliadoPrograma.getMaeCausaDescarteId();
        this.maeCausaDescarteCodigo = afiliadoPrograma.getMaeCausaDescarteCodigo();
        this.maeCausaDescarteValor = afiliadoPrograma.getMaeCausaDescarteValor();
        this.estadoDiagnostico = afiliadoPrograma.getEstadoDiagnostico();
        this.planificacionFamiliar = afiliadoPrograma.isPlanificacionFamiliar();
        this.idSentencia = afiliadoPrograma.getIdSentencia();
        this.codigoSentencia = afiliadoPrograma.getCodigoSentencia();
        this.valorSentencia = afiliadoPrograma.getValorSentencia();
        this.fechaHoraModifica = null;
        this.usuarioModifica = null;
        this.terminalModifica = null;
        //relaciones
        this.pePrograma = afiliadoPrograma.getPePrograma();
        this.asegAfiliado = afiliadoPrograma.getAsegAfiliado();
        this.aucHospitalizacion = afiliadoPrograma.getAucHospitalizacion();
        this.cntPrestadorSede = afiliadoPrograma.getCntPrestadorSede();
//        this.gnUsuario = afiliadoPrograma.getGnUsuario();
        this.peAfiliadoSugerido = afiliadoPrograma.getPeAfiliadoSugerido();
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getMaeCausaActivoId() {
        return maeCausaActivoId;
    }

    public void setMaeCausaActivoId(Integer maeCausaActivoId) {
        this.maeCausaActivoId = maeCausaActivoId;
    }

    public String getMaeCausaActivoCodigo() {
        return maeCausaActivoCodigo;
    }

    public void setMaeCausaActivoCodigo(String maeCausaActivoCodigo) {
        this.maeCausaActivoCodigo = maeCausaActivoCodigo;
    }

    public String getMaeCausaActivoValor() {
        return maeCausaActivoValor;
    }

    public void setMaeCausaActivoValor(String maeCausaActivoValor) {
        this.maeCausaActivoValor = maeCausaActivoValor;
    }

    public Integer getMaeCausaInactivoId() {
        return maeCausaInactivoId;
    }

    public void setMaeCausaInactivoId(Integer maeCausaInactivoId) {
        this.maeCausaInactivoId = maeCausaInactivoId;
    }

    public String getMaeCausaInactivoCodigo() {
        return maeCausaInactivoCodigo;
    }

    public void setMaeCausaInactivoCodigo(String maeCausaInactivoCodigo) {
        this.maeCausaInactivoCodigo = maeCausaInactivoCodigo;
    }

    public String getMaeCausaInactivoValor() {
        return maeCausaInactivoValor;
    }

    public void setMaeCausaInactivoValor(String maeCausaInactivoValor) {
        this.maeCausaInactivoValor = maeCausaInactivoValor;
    }

    public Date getFechaInicioPrograma() {
        return fechaInicioPrograma;
    }

    public void setFechaInicioPrograma(Date fechaInicioPrograma) {
        this.fechaInicioPrograma = fechaInicioPrograma;
    }

    public Date getFechaFinPrograma() {
        return fechaFinPrograma;
    }

    public void setFechaFinPrograma(Date fechaFinPrograma) {
        this.fechaFinPrograma = fechaFinPrograma;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public Integer getEstadoDiagnostico() {
        return estadoDiagnostico;
    }

    public void setEstadoDiagnostico(Integer estadoDiagnostico) {
        this.estadoDiagnostico = estadoDiagnostico;
    }

    public Integer getMaeRegionCorporalId() {
        return maeRegionCorporalId;
    }

    public void setMaeRegionCorporalId(Integer maeRegionCorporalId) {
        this.maeRegionCorporalId = maeRegionCorporalId;
    }

    public String getMaeRegionCorporalCodigo() {
        return maeRegionCorporalCodigo;
    }

    public void setMaeRegionCorporalCodigo(String maeRegionCorporalCodigo) {
        this.maeRegionCorporalCodigo = maeRegionCorporalCodigo;
    }

    public String getMaeRegionCorporalValor() {
        return maeRegionCorporalValor;
    }

    public void setMaeRegionCorporalValor(String maeRegionCorporalValor) {
        this.maeRegionCorporalValor = maeRegionCorporalValor;
    }

    public Integer getMaeMedioDxId() {
        return maeMedioDxId;
    }

    public void setMaeMedioDxId(Integer maeMedioDxId) {
        this.maeMedioDxId = maeMedioDxId;
    }

    public String getMaeMedioDxCodigo() {
        return maeMedioDxCodigo;
    }

    public void setMaeMedioDxCodigo(String maeMedioDxCodigo) {
        this.maeMedioDxCodigo = maeMedioDxCodigo;
    }

    public String getMaeMedioDxValor() {
        return maeMedioDxValor;
    }

    public void setMaeMedioDxValor(String maeMedioDxValor) {
        this.maeMedioDxValor = maeMedioDxValor;
    }

    public PePrograma getPePrograma() {
        return pePrograma;
    }

    public void setPePrograma(PePrograma pePrograma) {
        this.pePrograma = pePrograma;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public Usuario getGnUsuario() {
        return gnUsuario;
    }

    public void setGnUsuario(Usuario gnUsuario) {
        this.gnUsuario = gnUsuario;
    }

    public List<PeGestion> getListaPeGestion() {
        return listaPeGestion;
    }

    public void setListaPeGestion(List<PeGestion> listaPeGestion) {
        this.listaPeGestion = listaPeGestion;
    }

    public List<PeAdjunto> getListaPeAdjunto() {
        return listaPeAdjunto;
    }

    public void setListaPeAdjunto(List<PeAdjunto> listaPeAdjunto) {
        this.listaPeAdjunto = listaPeAdjunto;
    }

    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getProgramaCargaMasiva() {
        return programaCargaMasiva;
    }

    public void setProgramaCargaMasiva(String programaCargaMasiva) {
        this.programaCargaMasiva = programaCargaMasiva;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    public MaDiagnostico getMaDiagnosticoPrincipal() {
        return maDiagnosticoPrincipal;
    }

    public void setMaDiagnosticoPrincipal(MaDiagnostico maDiagnosticoPrincipal) {
        this.maDiagnosticoPrincipal = maDiagnosticoPrincipal;
    }

    public MaDiagnostico getMaDiagnostico2() {
        return maDiagnostico2;
    }

    public void setMaDiagnostico2(MaDiagnostico maDiagnostico2) {
        this.maDiagnostico2 = maDiagnostico2;
    }

    public MaDiagnostico getMaDiagnostico3() {
        return maDiagnostico3;
    }

    public void setMaDiagnostico3(MaDiagnostico maDiagnostico3) {
        this.maDiagnostico3 = maDiagnostico3;
    }

    public Maestro getMaeRegionCorporal() {
        return maeRegionCorporal;
    }

    public void setMaeRegionCorporal(Maestro maeRegionCorporal) {
        this.maeRegionCorporal = maeRegionCorporal;
    }

    public Maestro getMaeMedioDx() {
        return maeMedioDx;
    }

    public void setMaeMedioDx(Maestro maeMedioDx) {
        this.maeMedioDx = maeMedioDx;
    }

    public Maestro getMaeCausaActivoObj() {
        return maeCausaActivoObj;
    }

    public void setMaeCausaActivoObj(Maestro maeCausaActivoObj) {
        this.maeCausaActivoObj = maeCausaActivoObj;
    }

    public Maestro getMaeCausaInactivoObj() {
        return maeCausaInactivoObj;
    }

    public void setMaeCausaInactivoObj(Maestro maeCausaInactivoObj) {
        this.maeCausaInactivoObj = maeCausaInactivoObj;
    }

    public List<PeAfiliadoDiagnostico> getPeAfiliadoDiagnosticoList() {
        return peAfiliadoDiagnosticoList;
    }

    public void setPeAfiliadoDiagnosticoList(List<PeAfiliadoDiagnostico> peAfiliadoDiagnosticoList) {
        this.peAfiliadoDiagnosticoList = peAfiliadoDiagnosticoList;
    }

    public PeAfiliadoDiagnostico getObjetoDiagnostico() {
        return objetoDiagnostico;
    }

    public void setObjetoDiagnostico(PeAfiliadoDiagnostico objetoDiagnostico) {
        this.objetoDiagnostico = objetoDiagnostico;
    }

    public String getActivoStr() {
        return (getActivo()) ? "Si" : "No";
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public List<PeAfiliadoDiagnostico> getPeAfiliadoDiagnosticoAuxList() {
        return peAfiliadoDiagnosticoAuxList;
    }

    public void setPeAfiliadoDiagnosticoAuxList(List<PeAfiliadoDiagnostico> peAfiliadoDiagnosticoAuxList) {
        this.peAfiliadoDiagnosticoAuxList = peAfiliadoDiagnosticoAuxList;
    }

    public Integer getIdSolicitudOrigen() {
        return idSolicitudOrigen;
    }

    public void setIdSolicitudOrigen(Integer idSolicitudOrigen) {
        this.idSolicitudOrigen = idSolicitudOrigen;
    }

    public PeAfiliadoSugerido getPeAfiliadoSugerido() {
        return peAfiliadoSugerido;
    }

    public void setPeAfiliadoSugerido(PeAfiliadoSugerido peAfiliadoSugerido) {
        this.peAfiliadoSugerido = peAfiliadoSugerido;
    }

    public AucHospitalizacion getAucHospitalizacion() {
        return aucHospitalizacion;
    }

    public void setAucHospitalizacion(AucHospitalizacion aucHospitalizacion) {
        this.aucHospitalizacion = aucHospitalizacion;
    }

    public Integer getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(Integer tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isPuedeGestionar() {
        return puedeGestionar;
    }

    public void setPuedeGestionar(boolean puedeGestionar) {
        this.puedeGestionar = puedeGestionar;
    }

    public boolean getAdherente() {
        return adherente;
    }

    public void setAdherente(boolean adherente) {
        this.adherente = adherente;
    }

    public boolean getDisentimiento() {
        return disentimiento;
    }

    public void setDisentimiento(boolean disentimiento) {
        this.disentimiento = disentimiento;
    }

    public Integer getEstadoSivigila() {
        return estadoSivigila;
    }

    public void setEstadoSivigila(Integer estadoSivigila) {
        this.estadoSivigila = estadoSivigila;
    }

    public Integer getMaeCausaDescarteId() {
        return maeCausaDescarteId;
    }

    public void setMaeCausaDescarteId(Integer maeCausaDescarteId) {
        this.maeCausaDescarteId = maeCausaDescarteId;
    }

    public String getMaeCausaDescarteCodigo() {
        return maeCausaDescarteCodigo;
    }

    public void setMaeCausaDescarteCodigo(String maeCausaDescarteCodigo) {
        this.maeCausaDescarteCodigo = maeCausaDescarteCodigo;
    }

    public String getMaeCausaDescarteValor() {
        return maeCausaDescarteValor;
    }

    public void setMaeCausaDescarteValor(String maeCausaDescarteValor) {
        this.maeCausaDescarteValor = maeCausaDescarteValor;
    }

    public Integer getNotificadoSivigila() {
        return notificadoSivigila;
    }

    public void setNotificadoSivigila(Integer notificadoSivigila) {
        this.notificadoSivigila = notificadoSivigila;
    }

    public boolean isPlanificacionFamiliar() {
        return planificacionFamiliar;
    }

    public void setPlanificacionFamiliar(boolean planificacionFamiliar) {
        this.planificacionFamiliar = planificacionFamiliar;
    }

    public Integer getIdSentencia() {
        return idSentencia;
    }

    public void setIdSentencia(Integer idSentencia) {
        this.idSentencia = idSentencia;
    }

    public String getCodigoSentencia() {
        return codigoSentencia;
    }

    public void setCodigoSentencia(String codigoSentencia) {
        this.codigoSentencia = codigoSentencia;
    }

    public String getValorSentencia() {
        return valorSentencia;
    }

    public void setValorSentencia(String valorSentencia) {
        this.valorSentencia = valorSentencia;
    }

    public String getNotificadoSivigilaStr() {
        String valor = "";
        if (getNotificadoSivigila() == null) {
            return valor;
        }
        switch (getNotificadoSivigila()) {
            case 0:
                valor = "Si";
                break;
            case 1:
                valor = "No";
                break;
            case 2:
                valor = "No Aplica";
                break;
            default:
                valor = "";
        }
        return valor;
    }

    public String getBooleanToStr(Boolean valor) {
        return (valor) ? "Si" : "No";
    }

    public String getDatosAuditoria() {
        String datos = "";
        if (getAsegAfiliado().getPrimerNombre() != null) {
            datos += " " + getAsegAfiliado().getPrimerNombre();
        }
        if (getAsegAfiliado().getSegundoNombre() != null) {
            datos += " " + getAsegAfiliado().getSegundoNombre();
        }
        if (getAsegAfiliado().getPrimerApellido() != null) {
            datos += " " + getAsegAfiliado().getPrimerApellido();
        }
        if (getAsegAfiliado().getSegundoApellido() != null) {
            datos += " " + getAsegAfiliado().getSegundoApellido();
        }
        if (getAsegAfiliado().getMaeTipoDocumentoCodigo() != null) {
            datos += " " + getAsegAfiliado().getMaeTipoDocumentoCodigo();
        }
        if (getAsegAfiliado().getNumeroDocumento() != null) {
            datos += " " + getAsegAfiliado().getNumeroDocumento() + " ";
        }
        return datos;
    }

    @Override
    public String toString() {
        return "PeAfiliadosPrograma{" + "id=" + id + ", activo=" + activo + ", maeCausaActivoId=" + maeCausaActivoId + ", maeCausaActivoCodigo=" + maeCausaActivoCodigo + ", maeCausaActivoValor=" + maeCausaActivoValor + ", maeCausaInactivoId=" + maeCausaInactivoId + ", maeCausaInactivoCodigo=" + maeCausaInactivoCodigo + ", maeCausaInactivoValor=" + maeCausaInactivoValor + ", fechaInicioPrograma=" + fechaInicioPrograma + ", fechaFinPrograma=" + fechaFinPrograma + ", fechaDiagnostico=" + fechaDiagnostico + ", maeRegionCorporalId=" + maeRegionCorporalId + ", maeRegionCorporalCodigo=" + maeRegionCorporalCodigo + ", maeRegionCorporalValor=" + maeRegionCorporalValor + ", maeMedioDxId=" + maeMedioDxId + ", maeMedioDxCodigo=" + maeMedioDxCodigo + ", maeMedioDxValor=" + maeMedioDxValor + ", pePrograma=" + pePrograma + ", asegAfiliado=" + asegAfiliado + ", cntPrestadorSede=" + cntPrestadorSede + ", maDiagnosticoPrincipal=" + maDiagnosticoPrincipal + ", maDiagnostico2=" + maDiagnostico2 + ", maDiagnostico3=" + maDiagnostico3 + ", maeRegionCorporal=" + maeRegionCorporal + ", maeMedioDx=" + maeMedioDx + ", maeCausaActivoObj=" + maeCausaActivoObj + ", maeCausaInactivoObj=" + maeCausaInactivoObj + ", gnUsuario=" + gnUsuario + ", listaPeGestion=" + listaPeGestion + ", listaPeAdjunto=" + listaPeAdjunto + ", sincronizado=" + sincronizado + ", programaCargaMasiva=" + programaCargaMasiva + ", errorCarga=" + errorCarga + ", fuenteOrigen=" + fuenteOrigen + ", idSolicitudOrigen=" + idSolicitudOrigen + ", peAfiliadoSugerido=" + peAfiliadoSugerido + ", aucHospitalizacion=" + aucHospitalizacion + ", tipoPaciente=" + tipoPaciente + ", actualizar=" + actualizar + ", adherente=" + adherente + ", disentimiento=" + disentimiento + ", planificacionFamiliar=" + planificacionFamiliar + ", sivigila=" + estadoSivigila + ", maeCausaDescarteId=" + maeCausaDescarteId + ", maeCausaDescarteCodigo=" + maeCausaDescarteCodigo + ", maeCausaDescarteValor=" + maeCausaDescarteValor + ", peAfiliadoDiagnosticoList=" + peAfiliadoDiagnosticoList + ", objetoDiagnostico=" + objetoDiagnostico + ", peAfiliadoDiagnosticoAuxList=" + peAfiliadoDiagnosticoAuxList + ", puedeGestionar=" + puedeGestionar + ", idSentencia=" + idSentencia + ", codigoSentencia=" + codigoSentencia + ", valorSentencia=" + valorSentencia + '}';
    }

}
