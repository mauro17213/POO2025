package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MpPrescripcionInsumo extends Auditoria {

    //ESTADOS
    public static final int ESTADO_PENDIENTE = 3;
    public static final int ESTADO_ANULANDO_NO_DIRECCIONAMIENTO = 14;

    private Integer id;
    private Integer idTransaccion;
    private String idDireccionamiento;
    private int estado;
    private int estadoJuntaProfesionales;
    private int consecutivoOrden;
    private int tipoPrestacion;
    private int tipoTecnologia;
    private Integer causaSolicitud1;
    private Integer causaSolicitud2;
    private Integer causaSolicitud3;
    private Integer causaSolicitud4;
    private Integer causaSolicitud5;
    private String codigoDispositivo;
    private String cantidad;
    private String frecuenciaUso;
    private String nombreTecnologiaAvalada;
    private String descripcionCausaS4;
    private String codigoForma;
    private String cantidadFormulada;
    private String descripcionCausa4;
    private Integer codigoServicioComplementario;
    private Integer duracionTratamiento;
    private String tipoTransporte;
    private String reqAcom;
    private String tipoDocumentoAcompananteAlbergue;
    private String numeroDocumentoAcompanante;
    private String tipoDocumentoAcompanante;
    private String parentezcoAcompanante;
    private String nombreAlb;
    private String codigoMunicipioOrigenAlb;
    private String codigoMunicipioDestinoAlb;
    private String justificacionNoPbs;
    private String indicacionesRecomendaciones;
    private BigDecimal cantidadTotalEntrega;
    private BigDecimal entregados;
    private BigDecimal pendiente;
    private Date fechaDireccionamiento;
    private Date fechaMaximaEntrega;
    private MpPrescripcion mpPrescripcion;
    private String maeDispositivosId;
    private String maeDispositivosCodigo;
    private String maeDispositivosNombre;
    private String maeServiciosComplementariosId;
    private String maeServiciosComplementariosCodigo;
    private String maeServiciosComplementariosNombre;
    private int codFrecuenciaUso;
    private int codPerDurTrat;
    private Boolean usoServCosmeticoSuntuario;
    private Boolean servicioPrestaraColombia;
    private Boolean servRegistradoAutCompetente;
    private Boolean servCondicionClinDiagPaciente;
    private Boolean evidenciaEfiEfecClinica;
    private String numActaJunta;
    private Integer consecutivoJuntaTecnologia;
    private String modJunta;
    private Date fechaActaJunta;
    private String justificacionTecJunta;
    private String descripcionServicioComplementario;

    private String usuarioAtiende;
    private String terminalAtiende;
    private Date fechaHoraAtiende;
    private Boolean atendido;
    private Boolean banderaAtencion;

    public MpPrescripcionInsumo() {
    }

    public MpPrescripcionInsumo(Integer id) {
        this.id = id;
    }

    public String getNumActaJunta() {
        return numActaJunta;
    }

    public void setNumActaJunta(String numActaJunta) {
        this.numActaJunta = numActaJunta;
    }

    public Integer getConsecutivoJuntaTecnologia() {
        return consecutivoJuntaTecnologia;
    }

    public void setConsecutivoJuntaTecnologia(Integer consecutivoJuntaTecnologia) {
        this.consecutivoJuntaTecnologia = consecutivoJuntaTecnologia;
    }

    public String getModJunta() {
        return modJunta;
    }

    public void setModJunta(String modJunta) {
        this.modJunta = modJunta;
    }

    public String getJustificacionTecJunta() {
        return justificacionTecJunta;
    }

    public void setJustificacionTecJunta(String justificacionTecJunta) {
        this.justificacionTecJunta = justificacionTecJunta;
    }

    public String getDescripcionServicioComplementario() {
        return descripcionServicioComplementario;
    }

    public void setDescripcionServicioComplementario(String descripcionServicioComplementario) {
        this.descripcionServicioComplementario = descripcionServicioComplementario;
    }

    public Date getFechaActaJunta() {
        return fechaActaJunta;
    }

    public void setFechaActaJunta(Date fechaActaJunta) {
        this.fechaActaJunta = fechaActaJunta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(String idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoJuntaProfesionales() {
        return estadoJuntaProfesionales;
    }

    public void setEstadoJuntaProfesionales(int estadoJuntaProfesionales) {
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
    }

    public int getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public int getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(int tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getCausaSolicitud1() {
        return causaSolicitud1;
    }

    public void setCausaSolicitud1(Integer causaSolicitud1) {
        this.causaSolicitud1 = causaSolicitud1;
    }

    public Integer getCausaSolicitud2() {
        return causaSolicitud2;
    }

    public void setCausaSolicitud2(Integer causaSolicitud2) {
        this.causaSolicitud2 = causaSolicitud2;
    }

    public Integer getCausaSolicitud3() {
        return causaSolicitud3;
    }

    public void setCausaSolicitud3(Integer causaSolicitud3) {
        this.causaSolicitud3 = causaSolicitud3;
    }

    public Integer getCausaSolicitud4() {
        return causaSolicitud4;
    }

    public void setCausaSolicitud4(Integer causaSolicitud4) {
        this.causaSolicitud4 = causaSolicitud4;
    }

    public Integer getCausaSolicitud5() {
        return causaSolicitud5;
    }

    public void setCausaSolicitud5(Integer causaSolicitud5) {
        this.causaSolicitud5 = causaSolicitud5;
    }

    public String getCodigoDispositivo() {
        return codigoDispositivo;
    }

    public void setCodigoDispositivo(String codigoDispositivo) {
        this.codigoDispositivo = codigoDispositivo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFrecuenciaUso() {
        return frecuenciaUso;
    }

    public void setFrecuenciaUso(String frecuenciaUso) {
        this.frecuenciaUso = frecuenciaUso;
    }

    public String getNombreTecnologiaAvalada() {
        return nombreTecnologiaAvalada;
    }

    public void setNombreTecnologiaAvalada(String nombreTecnologiaAvalada) {
        this.nombreTecnologiaAvalada = nombreTecnologiaAvalada;
    }

    public String getDescripcionCausaS4() {
        return descripcionCausaS4;
    }

    public void setDescripcionCausaS4(String descripcionCausaS4) {
        this.descripcionCausaS4 = descripcionCausaS4;
    }

    public String getCodigoForma() {
        return codigoForma;
    }

    public void setCodigoForma(String codigoForma) {
        this.codigoForma = codigoForma;
    }

    public String getCantidadFormulada() {
        return cantidadFormulada;
    }

    public void setCantidadFormulada(String cantidadFormulada) {
        this.cantidadFormulada = cantidadFormulada;
    }

    public String getDescripcionCausa4() {
        return descripcionCausa4;
    }

    public void setDescripcionCausa4(String descripcionCausa4) {
        this.descripcionCausa4 = descripcionCausa4;
    }

    public Integer getCodigoServicioComplementario() {
        return codigoServicioComplementario;
    }

    public void setCodigoServicioComplementario(Integer codigoServicioComplementario) {
        this.codigoServicioComplementario = codigoServicioComplementario;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public String getReqAcom() {
        return reqAcom;
    }

    public void setReqAcom(String reqAcom) {
        this.reqAcom = reqAcom;
    }

    public String getTipoDocumentoAcompananteAlbergue() {
        return tipoDocumentoAcompananteAlbergue;
    }

    public void setTipoDocumentoAcompananteAlbergue(String tipoDocumentoAcompananteAlbergue) {
        this.tipoDocumentoAcompananteAlbergue = tipoDocumentoAcompananteAlbergue;
    }

    public String getNumeroDocumentoAcompanante() {
        return numeroDocumentoAcompanante;
    }

    public void setNumeroDocumentoAcompanante(String numeroDocumentoAcompanante) {
        this.numeroDocumentoAcompanante = numeroDocumentoAcompanante;
    }

    public String getTipoDocumentoAcompanante() {
        return tipoDocumentoAcompanante;
    }

    public void setTipoDocumentoAcompanante(String tipoDocumentoAcompanante) {
        this.tipoDocumentoAcompanante = tipoDocumentoAcompanante;
    }

    public String getParentezcoAcompanante() {
        return parentezcoAcompanante;
    }

    public void setParentezcoAcompanante(String parentezcoAcompanante) {
        this.parentezcoAcompanante = parentezcoAcompanante;
    }

    public String getNombreAlb() {
        return nombreAlb;
    }

    public void setNombreAlb(String nombreAlb) {
        this.nombreAlb = nombreAlb;
    }

    public String getCodigoMunicipioOrigenAlb() {
        return codigoMunicipioOrigenAlb;
    }

    public void setCodigoMunicipioOrigenAlb(String codigoMunicipioOrigenAlb) {
        this.codigoMunicipioOrigenAlb = codigoMunicipioOrigenAlb;
    }

    public String getCodigoMunicipioDestinoAlb() {
        return codigoMunicipioDestinoAlb;
    }

    public void setCodigoMunicipioDestinoAlb(String codigoMunicipioDestinoAlb) {
        this.codigoMunicipioDestinoAlb = codigoMunicipioDestinoAlb;
    }

    public String getJustificacionNoPbs() {
        return justificacionNoPbs;
    }

    public void setJustificacionNoPbs(String justificacionNoPbs) {
        this.justificacionNoPbs = justificacionNoPbs;
    }

    public String getIndicacionesRecomendaciones() {
        return indicacionesRecomendaciones;
    }

    public void setIndicacionesRecomendaciones(String indicacionesRecomendaciones) {
        this.indicacionesRecomendaciones = indicacionesRecomendaciones;
    }

    public BigDecimal getCantidadTotalEntrega() {
        return cantidadTotalEntrega;
    }

    public void setCantidadTotalEntrega(BigDecimal cantidadTotalEntrega) {
        this.cantidadTotalEntrega = cantidadTotalEntrega;
    }

    public BigDecimal getEntregados() {
        return entregados;
    }

    public void setEntregados(BigDecimal entregados) {
        this.entregados = entregados;
    }

    public BigDecimal getPendiente() {
        return pendiente;
    }

    public void setPendiente(BigDecimal pendiente) {
        this.pendiente = pendiente;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public MpPrescripcion getMpPrescripcion() {
        return mpPrescripcion;
    }

    public void setMpPrescripcion(MpPrescripcion mpPrescripcion) {
        this.mpPrescripcion = mpPrescripcion;
    }

    public Date getFechaMaximaEntrega() {
        return fechaMaximaEntrega;
    }

    public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
        this.fechaMaximaEntrega = fechaMaximaEntrega;
    }

    public String getMaeDispositivosId() {
        return maeDispositivosId;
    }

    public void setMaeDispositivosId(String maeDispositivosId) {
        this.maeDispositivosId = maeDispositivosId;
    }

    public String getMaeDispositivosCodigo() {
        return maeDispositivosCodigo;
    }

    public void setMaeDispositivosCodigo(String maeDispositivosCodigo) {
        this.maeDispositivosCodigo = maeDispositivosCodigo;
    }

    public String getMaeDispositivosNombre() {
        return maeDispositivosNombre;
    }

    public void setMaeDispositivosNombre(String maeDispositivosNombre) {
        this.maeDispositivosNombre = maeDispositivosNombre;
    }

    public String getMaeServiciosComplementariosId() {
        return maeServiciosComplementariosId;
    }

    public void setMaeServiciosComplementariosId(String maeServiciosComplementariosId) {
        this.maeServiciosComplementariosId = maeServiciosComplementariosId;
    }

    public String getMaeServiciosComplementariosCodigo() {
        return maeServiciosComplementariosCodigo;
    }

    public void setMaeServiciosComplementariosCodigo(String maeServiciosComplementariosCodigo) {
        this.maeServiciosComplementariosCodigo = maeServiciosComplementariosCodigo;
    }

    public String getMaeServiciosComplementariosNombre() {
        return maeServiciosComplementariosNombre;
    }

    public void setMaeServiciosComplementariosNombre(String maeServiciosComplementariosNombre) {
        this.maeServiciosComplementariosNombre = maeServiciosComplementariosNombre;
    }

    public int getCodFrecuenciaUso() {
        return codFrecuenciaUso;
    }

    public void setCodFrecuenciaUso(int codFrecuenciaUso) {
        this.codFrecuenciaUso = codFrecuenciaUso;
    }

    public int getCodPerDurTrat() {
        return codPerDurTrat;
    }

    public void setCodPerDurTrat(int codPerDurTrat) {
        this.codPerDurTrat = codPerDurTrat;
    }

    public Boolean getUsoServCosmeticoSuntuario() {
        return usoServCosmeticoSuntuario;
    }

    public void setUsoServCosmeticoSuntuario(Boolean usoServCosmeticoSuntuario) {
        this.usoServCosmeticoSuntuario = usoServCosmeticoSuntuario;
    }

    public Boolean getServicioPrestaraColombia() {
        return servicioPrestaraColombia;
    }

    public void setServicioPrestaraColombia(Boolean servicioPrestaraColombia) {
        this.servicioPrestaraColombia = servicioPrestaraColombia;
    }

    public Boolean getServRegistradoAutCompetente() {
        return servRegistradoAutCompetente;
    }

    public void setServRegistradoAutCompetente(Boolean servRegistradoAutCompetente) {
        this.servRegistradoAutCompetente = servRegistradoAutCompetente;
    }

    public Boolean getServCondicionClinDiagPaciente() {
        return servCondicionClinDiagPaciente;
    }

    public void setServCondicionClinDiagPaciente(Boolean servCondicionClinDiagPaciente) {
        this.servCondicionClinDiagPaciente = servCondicionClinDiagPaciente;
    }

    public Boolean getEvidenciaEfiEfecClinica() {
        return evidenciaEfiEfecClinica;
    }

    public void setEvidenciaEfiEfecClinica(Boolean evidenciaEfiEfecClinica) {
        this.evidenciaEfiEfecClinica = evidenciaEfiEfecClinica;
    }

    public String getUsuarioAtiende() {
        return usuarioAtiende;
    }

    public void setUsuarioAtiende(String usuarioAtiende) {
        this.usuarioAtiende = usuarioAtiende;
    }

    public String getTerminalAtiende() {
        return terminalAtiende;
    }

    public void setTerminalAtiende(String terminalAtiende) {
        this.terminalAtiende = terminalAtiende;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    public Boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(Boolean atendido) {
        this.atendido = atendido;
    }

    public Boolean getBanderaAtencion() {
        return banderaAtencion;
    }

    public void setBanderaAtencion(Boolean banderaAtencion) {
        this.banderaAtencion = banderaAtencion;
    }

    @Override
    public String toString() {
        return "MpPrescripcionInsumo{" + "id=" + id + ", idTransaccion=" + idTransaccion + ", idDireccionamiento=" + idDireccionamiento + ", estado=" + estado + ", estadoJuntaProfesionales=" + estadoJuntaProfesionales + ", consecutivoOrden=" + consecutivoOrden + ", tipoPrestacion=" + tipoPrestacion + ", tipoTecnologia=" + tipoTecnologia + ", causaSolicitud1=" + causaSolicitud1 + ", causaSolicitud2=" + causaSolicitud2 + ", causaSolicitud3=" + causaSolicitud3 + ", causaSolicitud4=" + causaSolicitud4 + ", causaSolicitud5=" + causaSolicitud5 + ", codigoDispositivo=" + codigoDispositivo + ", cantidad=" + cantidad + ", frecuenciaUso=" + frecuenciaUso + ", nombreTecnologiaAvalada=" + nombreTecnologiaAvalada + ", descripcionCausaS4=" + descripcionCausaS4 + ", codigoForma=" + codigoForma + ", cantidadFormulada=" + cantidadFormulada + ", descripcionCausa4=" + descripcionCausa4 + ", codigoServicioComplementario=" + codigoServicioComplementario + ", duracionTratamiento=" + duracionTratamiento + ", tipoTransporte=" + tipoTransporte + ", reqAcom=" + reqAcom + ", tipoDocumentoAcompananteAlbergue=" + tipoDocumentoAcompananteAlbergue + ", numeroDocumentoAcompanante=" + numeroDocumentoAcompanante + ", tipoDocumentoAcompanante=" + tipoDocumentoAcompanante + ", parentezcoAcompanante=" + parentezcoAcompanante + ", nombreAlb=" + nombreAlb + ", codigoMunicipioOrigenAlb=" + codigoMunicipioOrigenAlb + ", codigoMunicipioDestinoAlb=" + codigoMunicipioDestinoAlb + ", justificacionNoPbs=" + justificacionNoPbs + ", indicacionesRecomendaciones=" + indicacionesRecomendaciones + ", cantidadTotalEntrega=" + cantidadTotalEntrega + ", entregados=" + entregados + ", pendiente=" + pendiente + ", fechaDireccionamiento=" + fechaDireccionamiento + ", mpPrescripcion=" + mpPrescripcion + '}';
    }

}
