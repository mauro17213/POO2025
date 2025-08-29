/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cnt_contrato_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoDetalles.findAll", query = "SELECT c FROM CntContratoDetalles c"),
    @NamedQuery(name = "CntContratoDetalles.findById", query = "SELECT c FROM CntContratoDetalles c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoDetalles.findByFechaHoraInicio", query = "SELECT c FROM CntContratoDetalles c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CntContratoDetalles.findByFechaHoraFin", query = "SELECT c FROM CntContratoDetalles c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CntContratoDetalles.findByAutomaticoManual", query = "SELECT c FROM CntContratoDetalles c WHERE c.automaticoManual = :automaticoManual"),
    @NamedQuery(name = "CntContratoDetalles.findByAutomaticoMasivo", query = "SELECT c FROM CntContratoDetalles c WHERE c.automaticoMasivo = :automaticoMasivo"),
    @NamedQuery(name = "CntContratoDetalles.findByAutomaticoInteroperabilidad", query = "SELECT c FROM CntContratoDetalles c WHERE c.automaticoInteroperabilidad = :automaticoInteroperabilidad"),
    @NamedQuery(name = "CntContratoDetalles.findByPreautorizacion", query = "SELECT c FROM CntContratoDetalles c WHERE c.preautorizacion = :preautorizacion"),
    @NamedQuery(name = "CntContratoDetalles.findByActivo", query = "SELECT c FROM CntContratoDetalles c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntContratoDetalles.findByTipoTecnologia", query = "SELECT c FROM CntContratoDetalles c WHERE c.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "CntContratoDetalles.findByMaTecnologiaId", query = "SELECT c FROM CntContratoDetalles c WHERE c.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "CntContratoDetalles.findByMaTecnologiaCodigo", query = "SELECT c FROM CntContratoDetalles c WHERE c.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "CntContratoDetalles.findByMaTecnologiaValor", query = "SELECT c FROM CntContratoDetalles c WHERE c.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "CntContratoDetalles.findByMaServicioHabilitacionId", query = "SELECT c FROM CntContratoDetalles c WHERE c.maServicioHabilitacionId = :maServicioHabilitacionId"),
    @NamedQuery(name = "CntContratoDetalles.findByMaServicioHabilitacionCodigo", query = "SELECT c FROM CntContratoDetalles c WHERE c.maServicioHabilitacionCodigo = :maServicioHabilitacionCodigo"),
    @NamedQuery(name = "CntContratoDetalles.findByMaServicioHabilitacionValor", query = "SELECT c FROM CntContratoDetalles c WHERE c.maServicioHabilitacionValor = :maServicioHabilitacionValor"),
    @NamedQuery(name = "CntContratoDetalles.findByTipoManualTarifario", query = "SELECT c FROM CntContratoDetalles c WHERE c.tipoManualTarifario = :tipoManualTarifario"),
    @NamedQuery(name = "CntContratoDetalles.findByMaManualTarifarioId", query = "SELECT c FROM CntContratoDetalles c WHERE c.maManualTarifarioId = :maManualTarifarioId"),
    @NamedQuery(name = "CntContratoDetalles.findByMaManualTarifarioCodigo", query = "SELECT c FROM CntContratoDetalles c WHERE c.maManualTarifarioCodigo = :maManualTarifarioCodigo"),
    @NamedQuery(name = "CntContratoDetalles.findByMaManualTarifarioValor", query = "SELECT c FROM CntContratoDetalles c WHERE c.maManualTarifarioValor = :maManualTarifarioValor"),
    @NamedQuery(name = "CntContratoDetalles.findByMaManualTarifarioAgno", query = "SELECT c FROM CntContratoDetalles c WHERE c.maManualTarifarioAgno = :maManualTarifarioAgno"),
    @NamedQuery(name = "CntContratoDetalles.findByValorManual", query = "SELECT c FROM CntContratoDetalles c WHERE c.valorManual = :valorManual"),
    @NamedQuery(name = "CntContratoDetalles.findByValorContratado", query = "SELECT c FROM CntContratoDetalles c WHERE c.valorContratado = :valorContratado"),
    @NamedQuery(name = "CntContratoDetalles.findByPorcentajeVariacion", query = "SELECT c FROM CntContratoDetalles c WHERE c.porcentajeVariacion = :porcentajeVariacion"),
    @NamedQuery(name = "CntContratoDetalles.findByComplejidad", query = "SELECT c FROM CntContratoDetalles c WHERE c.complejidad = :complejidad"),
    @NamedQuery(name = "CntContratoDetalles.findByObservacionIncluye", query = "SELECT c FROM CntContratoDetalles c WHERE c.observacionIncluye = :observacionIncluye"),
    @NamedQuery(name = "CntContratoDetalles.findByObservacionExcluye", query = "SELECT c FROM CntContratoDetalles c WHERE c.observacionExcluye = :observacionExcluye"),
    @NamedQuery(name = "CntContratoDetalles.findByInterdependencia", query = "SELECT c FROM CntContratoDetalles c WHERE c.interdependencia = :interdependencia"),
    @NamedQuery(name = "CntContratoDetalles.findByMaeAmbitoId", query = "SELECT c FROM CntContratoDetalles c WHERE c.maeAmbitoId = :maeAmbitoId"),
    @NamedQuery(name = "CntContratoDetalles.findByMaeAmbitoCodigo", query = "SELECT c FROM CntContratoDetalles c WHERE c.maeAmbitoCodigo = :maeAmbitoCodigo"),
    @NamedQuery(name = "CntContratoDetalles.findByMaeAmbitoValor", query = "SELECT c FROM CntContratoDetalles c WHERE c.maeAmbitoValor = :maeAmbitoValor"),
    @NamedQuery(name = "CntContratoDetalles.findByValorMaximoRegulado", query = "SELECT c FROM CntContratoDetalles c WHERE c.valorMaximoRegulado = :valorMaximoRegulado"),
    @NamedQuery(name = "CntContratoDetalles.findByUsuarioCrea", query = "SELECT c FROM CntContratoDetalles c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoDetalles.findByTerminalCrea", query = "SELECT c FROM CntContratoDetalles c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoDetalles.findByFechaHoraCrea", query = "SELECT c FROM CntContratoDetalles c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntContratoDetalles.findByUsuarioModifica", query = "SELECT c FROM CntContratoDetalles c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntContratoDetalles.findByTerminalModifica", query = "SELECT c FROM CntContratoDetalles c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntContratoDetalles.findByFechaHoraModifica", query = "SELECT c FROM CntContratoDetalles c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntContratoDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "automatico_manual")
    private boolean automaticoManual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "automatico_masivo")
    private boolean automaticoMasivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "automatico_interoperabilidad")
    private boolean automaticoInteroperabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preautorizacion")
    private boolean preautorizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "ma_servicio_habilitacion_id")
    private Integer maServicioHabilitacionId;
    @Size(max = 32)
    @Column(name = "ma_servicio_habilitacion_codigo")
    private String maServicioHabilitacionCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_habilitacion_valor")
    private String maServicioHabilitacionValor;
    @Column(name = "tipo_manual_tarifario")
    private Integer tipoManualTarifario;
    @Column(name = "ma_manual_tarifario_id")
    private Integer maManualTarifarioId;
    @Size(max = 32)
    @Column(name = "ma_manual_tarifario_codigo")
    private String maManualTarifarioCodigo;
    @Size(max = 512)
    @Column(name = "ma_manual_tarifario_valor")
    private String maManualTarifarioValor;
    @Column(name = "ma_manual_tarifario_agno")
    private Integer maManualTarifarioAgno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_manual")
    private BigDecimal valorManual;
    @Column(name = "valor_contratado")
    private BigDecimal valorContratado;
    @Column(name = "porcentaje_variacion")
    private BigDecimal porcentajeVariacion;
    @Column(name = "complejidad")
    private Integer complejidad;
    @Size(max = 2048)
    @Column(name = "observacion_incluye")
    private String observacionIncluye;
    @Size(max = 2048)
    @Column(name = "observacion_excluye")
    private String observacionExcluye;
    @Basic(optional = false)
    @NotNull
    @Column(name = "interdependencia")
    private boolean interdependencia;
    @Column(name = "mae_ambito_id")
    private Integer maeAmbitoId;
    @Size(max = 8)
    @Column(name = "mae_ambito_codigo")
    private String maeAmbitoCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_valor")
    private String maeAmbitoValor;
    @Column(name = "valor_maximo_regulado")
    private BigDecimal valorMaximoRegulado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(mappedBy = "cntContratoDetallesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricos> cntContratoHistoricosList;
    @OneToMany(mappedBy = "cntContratoDetallesId", fetch = FetchType.LAZY)
    private List<AuAnexo4Items> auAnexo4ItemsList;
    @JoinColumn(name = "cnt_contrato_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratoSedes cntContratoSedesId;
    @JoinColumn(name = "cnt_prestador_sedes_interdependencia_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesInterdependenciaId;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratos cntContratosId;
    @OneToMany(mappedBy = "cntContratoDetallesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList;

    public CntContratoDetalles() {
    }

    public CntContratoDetalles(Integer id) {
        this.id = id;
    }

    public CntContratoDetalles(Integer id, Date fechaHoraInicio, boolean automaticoManual, boolean automaticoMasivo, boolean automaticoInteroperabilidad, boolean preautorizacion, boolean activo, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, boolean interdependencia, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.automaticoManual = automaticoManual;
        this.automaticoMasivo = automaticoMasivo;
        this.automaticoInteroperabilidad = automaticoInteroperabilidad;
        this.preautorizacion = preautorizacion;
        this.activo = activo;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.interdependencia = interdependencia;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean getAutomaticoManual() {
        return automaticoManual;
    }

    public void setAutomaticoManual(boolean automaticoManual) {
        this.automaticoManual = automaticoManual;
    }

    public boolean getAutomaticoMasivo() {
        return automaticoMasivo;
    }

    public void setAutomaticoMasivo(boolean automaticoMasivo) {
        this.automaticoMasivo = automaticoMasivo;
    }

    public boolean getAutomaticoInteroperabilidad() {
        return automaticoInteroperabilidad;
    }

    public void setAutomaticoInteroperabilidad(boolean automaticoInteroperabilidad) {
        this.automaticoInteroperabilidad = automaticoInteroperabilidad;
    }

    public boolean getPreautorizacion() {
        return preautorizacion;
    }

    public void setPreautorizacion(boolean preautorizacion) {
        this.preautorizacion = preautorizacion;
    }

    public boolean getActivo() {
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

    public boolean getInterdependencia() {
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

    public BigDecimal getValorMaximoRegulado() {
        return valorMaximoRegulado;
    }

    public void setValorMaximoRegulado(BigDecimal valorMaximoRegulado) {
        this.valorMaximoRegulado = valorMaximoRegulado;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    @XmlTransient
    public List<CntContratoHistoricos> getCntContratoHistoricosList() {
        return cntContratoHistoricosList;
    }

    public void setCntContratoHistoricosList(List<CntContratoHistoricos> cntContratoHistoricosList) {
        this.cntContratoHistoricosList = cntContratoHistoricosList;
    }

    @XmlTransient
    public List<AuAnexo4Items> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Items> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    public CntContratoSedes getCntContratoSedesId() {
        return cntContratoSedesId;
    }

    public void setCntContratoSedesId(CntContratoSedes cntContratoSedesId) {
        this.cntContratoSedesId = cntContratoSedesId;
    }

    public CntPrestadorSedes getCntPrestadorSedesInterdependenciaId() {
        return cntPrestadorSedesInterdependenciaId;
    }

    public void setCntPrestadorSedesInterdependenciaId(CntPrestadorSedes cntPrestadorSedesInterdependenciaId) {
        this.cntPrestadorSedesInterdependenciaId = cntPrestadorSedesInterdependenciaId;
    }

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
    }

    @XmlTransient
    public List<CntContratoHistoricoValidaciones> getCntContratoHistoricoValidacionesList() {
        return cntContratoHistoricoValidacionesList;
    }

    public void setCntContratoHistoricoValidacionesList(List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList) {
        this.cntContratoHistoricoValidacionesList = cntContratoHistoricoValidacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CntContratoDetalles)) {
            return false;
        }
        CntContratoDetalles other = (CntContratoDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoDetalles[ id=" + id + " ]";
    }
    
}
