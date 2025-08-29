package com.saviasaludeps.savia.dominio.contratacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class DTOCntContratoDetalle extends Auditoria{

    @SerializedName(value = "idContrato")
    private Integer idContrato;
    @SerializedName(value = "numeroContrato")
    private String contrato;
    @SerializedName(value = "idContratoSede")
    private Integer idContratoSede;
    @SerializedName(value = "idContratoDetalle")
    private Integer id;
    @SerializedName(value = "activo")
    private boolean activo;
    @SerializedName(value = "tipoTecnologia")
    private int tipoTecnologia;
    @SerializedName(value = "maTecnologiaId")
    private int maTecnologiaId;
    @SerializedName(value = "maTecnologiaCodigo")
    private String maTecnologiaCodigo;
    @SerializedName(value = "maTecnologiaValor")
    private String maTecnologiaValor;
    @SerializedName(value = "maServicioHabilitacionId")
    private Integer maServicioHabilitacionId;
    @SerializedName(value = "maServicioHabilitacionCodigo")
    private String maServicioHabilitacionCodigo;
    @SerializedName(value = "maServicioHabilitacionValor")
    private String maServicioHabilitacionValor;
    @SerializedName(value = "tipoManualTarifario")
    private Integer tipoManualTarifario;
    @SerializedName(value = "maManualTarifarioId")
    private Integer maManualTarifarioId;
    @SerializedName(value = "maManualTarifarioCodigo")
    private String maManualTarifarioCodigo;
    @SerializedName(value = "maManualTarifarioValor")
    private String maManualTarifarioValor;
    @SerializedName(value = "maManualTarifarioAgno")
    private Integer maManualTarifarioAgno;
    @SerializedName(value = "valorManual")
    private BigDecimal valorManual;
    @SerializedName(value = "valorContratado")
    private BigDecimal valorContratado;
    @SerializedName(value = "porcentajeVariacion")
    private BigDecimal porcentajeVariacion;
    @SerializedName(value = "complejidad")
    private Integer complejidad;
    @SerializedName(value = "observacionIncluye")
    private String observacionIncluye;
    @SerializedName(value = "observacionExcluye")
    private String observacionExcluye;
    @SerializedName(value = "interdependencia")
    private boolean interdependencia;
    @SerializedName(value = "maeAmbitoId")
    private Integer maeAmbitoId;
    @SerializedName(value = "maeAmbitoCodigo")
    private String maeAmbitoCodigo;
    @SerializedName(value = "maeAmbitoValor")
    private String maeAmbitoValor;
    @SerializedName(value = "fechaHoraInicio")
    private Date fechaHoraInicio;
    @SerializedName(value = "fechaHoraFin")
    private Date fechaHoraFin;
    @SerializedName(value = "automatico")
    private boolean automatico;
    @SerializedName(value = "valorMaximoRegulado")
    private BigDecimal valorMaximoRegulado;
//    private CntContratoSede cntContratoSede;
//    private CntPrestadorSede cntPrestadorSedesInterdependencia;
//    private CntContrato cntContrato;

    public DTOCntContratoDetalle() {
    }

    public DTOCntContratoDetalle(CntContratoDetalle cntContratoDetalle) {
        this.id = cntContratoDetalle.getId();
        this.idContrato = cntContratoDetalle.getCntContrato().getId();
        this.idContratoSede = cntContratoDetalle.getCntContratoSede().getId();
        this.contrato = cntContratoDetalle.getCntContrato().getContrato();
        this.activo = cntContratoDetalle.getActivo();
        this.tipoTecnologia = cntContratoDetalle.getTipoTecnologia();
        this.maTecnologiaId = cntContratoDetalle.getMaTecnologiaId();
        this.maTecnologiaCodigo = cntContratoDetalle.getMaTecnologiaCodigo();
        this.maTecnologiaValor = cntContratoDetalle.getMaTecnologiaValor();
        this.maServicioHabilitacionId = cntContratoDetalle.getMaServicioHabilitacionId();
        this.maServicioHabilitacionCodigo = cntContratoDetalle.getMaServicioHabilitacionCodigo();
        this.maServicioHabilitacionValor = cntContratoDetalle.getMaServicioHabilitacionValor();
        this.tipoManualTarifario = cntContratoDetalle.getTipoManualTarifario();
        this.maManualTarifarioId = cntContratoDetalle.getMaManualTarifarioId();
        this.maManualTarifarioCodigo = cntContratoDetalle.getMaManualTarifarioCodigo();
        this.maManualTarifarioValor = cntContratoDetalle.getMaManualTarifarioValor();
        this.maManualTarifarioAgno = cntContratoDetalle.getMaManualTarifarioAgno();
        this.valorManual = cntContratoDetalle.getValorManual();
        this.valorContratado = cntContratoDetalle.getValorContratado();
        this.porcentajeVariacion = cntContratoDetalle.getPorcentajeVariacion();
        this.complejidad = cntContratoDetalle.getComplejidad();
        this.observacionIncluye = cntContratoDetalle.getObservacionIncluye();
        this.observacionExcluye = cntContratoDetalle.getObservacionExcluye();
        this.interdependencia = cntContratoDetalle.getInterdependencia();
        this.maeAmbitoId = cntContratoDetalle.getMaeAmbitoId();
        this.maeAmbitoCodigo = cntContratoDetalle.getMaeAmbitoCodigo();
        this.maeAmbitoValor = cntContratoDetalle.getMaeAmbitoValor();
        this.fechaHoraInicio = cntContratoDetalle.getFechaHoraInicio();
        this.fechaHoraFin = cntContratoDetalle.getFechaHoraFin();
        this.automatico = cntContratoDetalle.isAutomaticoManual();
        this.valorMaximoRegulado = cntContratoDetalle.getValorMaximoRegulado();
        this.usuarioCrea = cntContratoDetalle.getUsuarioCrea();
        this.terminalCrea = cntContratoDetalle.getTerminalCrea();
        this.fechaHoraCrea = cntContratoDetalle.getFechaHoraCrea();
        this.usuarioModifica = cntContratoDetalle.getUsuarioModifica();
        this.terminalModifica = cntContratoDetalle.getTerminalModifica();
        this.fechaHoraModifica = cntContratoDetalle.getFechaHoraModifica();
//    private CntContratoSede cntContratoSede;
//    private CntPrestadorSede cntPrestadorSedesInterdependencia;
//    private CntContrato cntContrato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdContratoSede() {
        return idContratoSede;
    }

    public void setIdContratoSede(Integer idContratoSede) {
        this.idContratoSede = idContratoSede;
    }
    
    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
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

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaServicioHabilitacionId() {
        return maServicioHabilitacionId;
    }

    public void setMaServicioHabilitacionId(Integer maServicioHabilitacionId) {
        this.maServicioHabilitacionId = maServicioHabilitacionId;
    }

    public String getMaServicioHabilitacionCodigo() {
        return maServicioHabilitacionCodigo;
    }

    public void setMaServicioHabilitacionCodigo(String maServicioHabilitacionCodigo) {
        this.maServicioHabilitacionCodigo = maServicioHabilitacionCodigo;
    }

    public String getMaServicioHabilitacionValor() {
        return maServicioHabilitacionValor;
    }

    public void setMaServicioHabilitacionValor(String maServicioHabilitacionValor) {
        this.maServicioHabilitacionValor = maServicioHabilitacionValor;
    }

    public Integer getTipoManualTarifario() {
        return tipoManualTarifario;
    }

    public void setTipoManualTarifario(Integer tipoManualTarifario) {
        this.tipoManualTarifario = tipoManualTarifario;
    }

    public Integer getMaManualTarifarioId() {
        return maManualTarifarioId;
    }

    public void setMaManualTarifarioId(Integer maManualTarifarioId) {
        this.maManualTarifarioId = maManualTarifarioId;
    }

    public String getMaManualTarifarioCodigo() {
        return maManualTarifarioCodigo;
    }

    public void setMaManualTarifarioCodigo(String maManualTarifarioCodigo) {
        this.maManualTarifarioCodigo = maManualTarifarioCodigo;
    }

    public String getMaManualTarifarioValor() {
        return maManualTarifarioValor;
    }

    public void setMaManualTarifarioValor(String maManualTarifarioValor) {
        this.maManualTarifarioValor = maManualTarifarioValor;
    }

    public Integer getMaManualTarifarioAgno() {
        return maManualTarifarioAgno;
    }

    public void setMaManualTarifarioAgno(Integer maManualTarifarioAgno) {
        this.maManualTarifarioAgno = maManualTarifarioAgno;
    }

    public BigDecimal getValorManual() {
        return valorManual;
    }

    public void setValorManual(BigDecimal valorManual) {
        this.valorManual = valorManual;
    }

    public BigDecimal getValorContratado() {
        return valorContratado;
    }

    public void setValorContratado(BigDecimal valorContratado) {
        this.valorContratado = valorContratado;
    }

    public BigDecimal getPorcentajeVariacion() {
        return porcentajeVariacion;
    }

    public void setPorcentajeVariacion(BigDecimal porcentajeVariacion) {
        this.porcentajeVariacion = porcentajeVariacion;
    }

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public String getObservacionIncluye() {
        return observacionIncluye;
    }

    public void setObservacionIncluye(String observacionIncluye) {
        this.observacionIncluye = observacionIncluye;
    }

    public String getObservacionExcluye() {
        return observacionExcluye;
    }

    public void setObservacionExcluye(String observacionExcluye) {
        this.observacionExcluye = observacionExcluye;
    }

    public boolean isInterdependencia() {
        return interdependencia;
    }

    public void setInterdependencia(boolean interdependencia) {
        this.interdependencia = interdependencia;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public boolean isAutomatico() {
        return automatico;
    }

    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    public BigDecimal getValorMaximoRegulado() {
        return valorMaximoRegulado;
    }

    public void setValorMaximoRegulado(BigDecimal valorMaximoRegulado) {
        this.valorMaximoRegulado = valorMaximoRegulado;
    }
    
    
    public CntContratoDetalle convertir(){
        CntContratoDetalle contratoDetalle = new CntContratoDetalle();
        CntContrato cntContrato = new CntContrato();
        CntContratoSede cntContratoSede = new CntContratoSede();
        
        cntContratoSede.setId(idContratoSede);
        
        cntContrato.setId(this.idContrato);
        cntContrato.setContrato(this.contrato);
        
        contratoDetalle.setCntContratoSede(cntContratoSede);
        contratoDetalle.setCntContrato(cntContrato);
        contratoDetalle.setId(this.id);
        contratoDetalle.setActivo(this.activo);
        contratoDetalle.setTipoTecnologia(this.tipoTecnologia);
        contratoDetalle.setMaTecnologiaId(this.maTecnologiaId);
        contratoDetalle.setMaTecnologiaCodigo(this.maTecnologiaCodigo);
        contratoDetalle.setMaTecnologiaValor(this.maTecnologiaValor);
        contratoDetalle.setMaServicioHabilitacionId(this.maServicioHabilitacionId);
        contratoDetalle.setMaServicioHabilitacionCodigo(this.maServicioHabilitacionCodigo);
        contratoDetalle.setMaServicioHabilitacionValor(this.maServicioHabilitacionValor);
        contratoDetalle.setTipoManualTarifario(this.tipoManualTarifario);
        contratoDetalle.setMaManualTarifarioId(this.maManualTarifarioId);
        contratoDetalle.setMaManualTarifarioCodigo(this.maManualTarifarioCodigo);
        contratoDetalle.setMaManualTarifarioValor(this.maManualTarifarioValor);
        contratoDetalle.setMaManualTarifarioAgno(this.maManualTarifarioAgno);
        contratoDetalle.setValorManual(this.valorManual);
        contratoDetalle.setValorContratado(this.valorContratado);
        contratoDetalle.setPorcentajeVariacion(this.porcentajeVariacion);
        contratoDetalle.setComplejidad(this.complejidad);
        contratoDetalle.setObservacionIncluye(this.observacionIncluye);
        contratoDetalle.setObservacionExcluye(this.observacionExcluye);
        contratoDetalle.setInterdependencia(this.interdependencia);
        contratoDetalle.setMaeAmbitoId(this.maeAmbitoId);
        contratoDetalle.setMaeAmbitoCodigo(this.maeAmbitoCodigo);
        contratoDetalle.setMaeAmbitoValor(this.maeAmbitoValor);
        contratoDetalle.setFechaHoraInicio(this.fechaHoraInicio);
        contratoDetalle.setFechaHoraFin(this.fechaHoraFin);
        contratoDetalle.setAutomaticoManual(this.automatico);
        contratoDetalle.setValorMaximoRegulado(this.valorMaximoRegulado);
        contratoDetalle.setUsuarioCrea(this.usuarioCrea);
        contratoDetalle.setTerminalCrea(this.terminalCrea);
        contratoDetalle.setFechaHoraCrea(fechaHoraCrea);
        contratoDetalle.setUsuarioModifica(this.usuarioModifica);
        contratoDetalle.setTerminalModifica(this.terminalModifica);
        contratoDetalle.setFechaHoraModifica(this.fechaHoraModifica);
        
        return contratoDetalle;
    }

    @Override
    public String toString() {
        return "DTOCntContratoDetalle{" + "id=" + id + ", activo=" + activo + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maServicioHabilitacionId=" + maServicioHabilitacionId + ", maServicioHabilitacionCodigo=" + maServicioHabilitacionCodigo + ", maServicioHabilitacionValor=" + maServicioHabilitacionValor + ", tipoManualTarifario=" + tipoManualTarifario + ", maManualTarifarioId=" + maManualTarifarioId + ", maManualTarifarioCodigo=" + maManualTarifarioCodigo + ", maManualTarifarioValor=" + maManualTarifarioValor + ", maManualTarifarioAgno=" + maManualTarifarioAgno + ", valorManual=" + valorManual + ", valorContratado=" + valorContratado + ", porcentajeVariacion=" + porcentajeVariacion + ", complejidad=" + complejidad + ", observacionIncluye=" + observacionIncluye + ", observacionExcluye=" + observacionExcluye + ", interdependencia=" + interdependencia + ", maeAmbitoId=" + maeAmbitoId + ", maeAmbitoCodigo=" + maeAmbitoCodigo + ", maeAmbitoValor=" + maeAmbitoValor + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", automatico=" + automatico + ", valorMaximoRegulado=" + valorMaximoRegulado + '}';
    }
    
    public String toStringJson() {
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        //2022-04-26 (rpalacic)Clonar clase y ajustar datos
        DTOCntContratoDetalle clonDetalle;
        try {
            clonDetalle = (DTOCntContratoDetalle) this.clone();
            //clonDetalle.setCntPrestadorSedesInterdependencia(null);
        } catch (CloneNotSupportedException ex) {
            clonDetalle = this;
        }
        return gson.toJson(clonDetalle);
    }
}