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
import javax.persistence.CascadeType;
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
@Table(name = "au_cotizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuCotizaciones.findAll", query = "SELECT a FROM AuCotizaciones a"),
    @NamedQuery(name = "AuCotizaciones.findById", query = "SELECT a FROM AuCotizaciones a WHERE a.id = :id"),
    @NamedQuery(name = "AuCotizaciones.findByAntAnticiposId", query = "SELECT a FROM AuCotizaciones a WHERE a.antAnticiposId = :antAnticiposId"),
    @NamedQuery(name = "AuCotizaciones.findByAntAnticipoItemsId", query = "SELECT a FROM AuCotizaciones a WHERE a.antAnticipoItemsId = :antAnticipoItemsId"),
    @NamedQuery(name = "AuCotizaciones.findByActivo", query = "SELECT a FROM AuCotizaciones a WHERE a.activo = :activo"),
    @NamedQuery(name = "AuCotizaciones.findByTipoTecnologia", query = "SELECT a FROM AuCotizaciones a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuCotizaciones.findByMaTecnologiaId", query = "SELECT a FROM AuCotizaciones a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuCotizaciones.findByMaTecnologiaCodigo", query = "SELECT a FROM AuCotizaciones a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuCotizaciones.findByMaTecnologiaValor", query = "SELECT a FROM AuCotizaciones a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuCotizaciones.findByTipoTarifa", query = "SELECT a FROM AuCotizaciones a WHERE a.tipoTarifa = :tipoTarifa"),
    @NamedQuery(name = "AuCotizaciones.findByMaTarifarioId", query = "SELECT a FROM AuCotizaciones a WHERE a.maTarifarioId = :maTarifarioId"),
    @NamedQuery(name = "AuCotizaciones.findByMaTarifarioCodigo", query = "SELECT a FROM AuCotizaciones a WHERE a.maTarifarioCodigo = :maTarifarioCodigo"),
    @NamedQuery(name = "AuCotizaciones.findByMaTarifarioValor", query = "SELECT a FROM AuCotizaciones a WHERE a.maTarifarioValor = :maTarifarioValor"),
    @NamedQuery(name = "AuCotizaciones.findByMaMedicamentoId", query = "SELECT a FROM AuCotizaciones a WHERE a.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "AuCotizaciones.findByMaMedicamentoCodigo", query = "SELECT a FROM AuCotizaciones a WHERE a.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "AuCotizaciones.findByMaMedicamentoValor", query = "SELECT a FROM AuCotizaciones a WHERE a.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "AuCotizaciones.findByPorcentajeNegociacion", query = "SELECT a FROM AuCotizaciones a WHERE a.porcentajeNegociacion = :porcentajeNegociacion"),
    @NamedQuery(name = "AuCotizaciones.findByValorTecnologia", query = "SELECT a FROM AuCotizaciones a WHERE a.valorTecnologia = :valorTecnologia"),
    @NamedQuery(name = "AuCotizaciones.findByFechaInicioVigencia", query = "SELECT a FROM AuCotizaciones a WHERE a.fechaInicioVigencia = :fechaInicioVigencia"),
    @NamedQuery(name = "AuCotizaciones.findByFechaFinVigencia", query = "SELECT a FROM AuCotizaciones a WHERE a.fechaFinVigencia = :fechaFinVigencia"),
    @NamedQuery(name = "AuCotizaciones.findByObservacion", query = "SELECT a FROM AuCotizaciones a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AuCotizaciones.findByPagoAnticipado", query = "SELECT a FROM AuCotizaciones a WHERE a.pagoAnticipado = :pagoAnticipado"),
    @NamedQuery(name = "AuCotizaciones.findByFuenteOrigen", query = "SELECT a FROM AuCotizaciones a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AuCotizaciones.findByMpNumeroPrescripcion", query = "SELECT a FROM AuCotizaciones a WHERE a.mpNumeroPrescripcion = :mpNumeroPrescripcion"),
    @NamedQuery(name = "AuCotizaciones.findByTipoTecnologiaMipres", query = "SELECT a FROM AuCotizaciones a WHERE a.tipoTecnologiaMipres = :tipoTecnologiaMipres"),
    @NamedQuery(name = "AuCotizaciones.findByUsuarioCrea", query = "SELECT a FROM AuCotizaciones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuCotizaciones.findByTerminalCrea", query = "SELECT a FROM AuCotizaciones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuCotizaciones.findByFechaHoraCrea", query = "SELECT a FROM AuCotizaciones a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuCotizaciones.findByUsuarioModifica", query = "SELECT a FROM AuCotizaciones a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuCotizaciones.findByTerminalModifica", query = "SELECT a FROM AuCotizaciones a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuCotizaciones.findByFechaHoraModifica", query = "SELECT a FROM AuCotizaciones a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuCotizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ant_anticipos_id")
    private Integer antAnticiposId;
    @Column(name = "ant_anticipo_items_id")
    private Integer antAnticipoItemsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tarifa")
    private int tipoTarifa;
    @Column(name = "ma_tarifario_id")
    private Integer maTarifarioId;
    @Size(max = 32)
    @Column(name = "ma_tarifario_codigo")
    private String maTarifarioCodigo;
    @Size(max = 512)
    @Column(name = "ma_tarifario_valor")
    private String maTarifarioValor;
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 32)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_negociacion")
    private BigDecimal porcentajeNegociacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_tecnologia")
    private BigDecimal valorTecnologia;
    @Column(name = "fecha_inicio_vigencia")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioVigencia;
    @Column(name = "fecha_fin_vigencia")
    @Temporal(TemporalType.DATE)
    private Date fechaFinVigencia;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "pago_anticipado")
    private Boolean pagoAnticipado;
    @Column(name = "fuente_origen")
    private Integer fuenteOrigen;
    @Size(max = 32)
    @Column(name = "mp_numero_prescripcion")
    private String mpNumeroPrescripcion;
    @Column(name = "tipo_tecnologia_mipres")
    private Integer tipoTecnologiaMipres;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auCotizacionesId", fetch = FetchType.LAZY)
    private List<AuCotizacionAdjuntos> auCotizacionAdjuntosList;
    @OneToMany(mappedBy = "auCotizacionesId", fetch = FetchType.LAZY)
    private List<AuAnexo4Items> auAnexo4ItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auCotizacionesId", fetch = FetchType.LAZY)
    private List<AuCotizacionItems> auCotizacionItemsList;
    @OneToMany(mappedBy = "auCotizacionesId", fetch = FetchType.LAZY)
    private List<MpCotizaciones> mpCotizacionesList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "mp_prescripcion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionId;
    @OneToMany(mappedBy = "auCotizacionesId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @OneToMany(mappedBy = "auCotizacionesId", fetch = FetchType.LAZY)
    private List<AntAnticipoValores> antAnticipoValoresList;

    public AuCotizaciones() {
    }

    public AuCotizaciones(Integer id) {
        this.id = id;
    }

    public AuCotizaciones(Integer id, boolean activo, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, int tipoTarifa, BigDecimal porcentajeNegociacion, BigDecimal valorTecnologia, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.activo = activo;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.tipoTarifa = tipoTarifa;
        this.porcentajeNegociacion = porcentajeNegociacion;
        this.valorTecnologia = valorTecnologia;
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

    public Integer getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(Integer antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    public Integer getAntAnticipoItemsId() {
        return antAnticipoItemsId;
    }

    public void setAntAnticipoItemsId(Integer antAnticipoItemsId) {
        this.antAnticipoItemsId = antAnticipoItemsId;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
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

    public int getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(int tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public Integer getMaTarifarioId() {
        return maTarifarioId;
    }

    public void setMaTarifarioId(Integer maTarifarioId) {
        this.maTarifarioId = maTarifarioId;
    }

    public String getMaTarifarioCodigo() {
        return maTarifarioCodigo;
    }

    public void setMaTarifarioCodigo(String maTarifarioCodigo) {
        this.maTarifarioCodigo = maTarifarioCodigo;
    }

    public String getMaTarifarioValor() {
        return maTarifarioValor;
    }

    public void setMaTarifarioValor(String maTarifarioValor) {
        this.maTarifarioValor = maTarifarioValor;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public BigDecimal getPorcentajeNegociacion() {
        return porcentajeNegociacion;
    }

    public void setPorcentajeNegociacion(BigDecimal porcentajeNegociacion) {
        this.porcentajeNegociacion = porcentajeNegociacion;
    }

    public BigDecimal getValorTecnologia() {
        return valorTecnologia;
    }

    public void setValorTecnologia(BigDecimal valorTecnologia) {
        this.valorTecnologia = valorTecnologia;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getPagoAnticipado() {
        return pagoAnticipado;
    }

    public void setPagoAnticipado(Boolean pagoAnticipado) {
        this.pagoAnticipado = pagoAnticipado;
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public String getMpNumeroPrescripcion() {
        return mpNumeroPrescripcion;
    }

    public void setMpNumeroPrescripcion(String mpNumeroPrescripcion) {
        this.mpNumeroPrescripcion = mpNumeroPrescripcion;
    }

    public Integer getTipoTecnologiaMipres() {
        return tipoTecnologiaMipres;
    }

    public void setTipoTecnologiaMipres(Integer tipoTecnologiaMipres) {
        this.tipoTecnologiaMipres = tipoTecnologiaMipres;
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
    public List<AuCotizacionAdjuntos> getAuCotizacionAdjuntosList() {
        return auCotizacionAdjuntosList;
    }

    public void setAuCotizacionAdjuntosList(List<AuCotizacionAdjuntos> auCotizacionAdjuntosList) {
        this.auCotizacionAdjuntosList = auCotizacionAdjuntosList;
    }

    @XmlTransient
    public List<AuAnexo4Items> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Items> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    @XmlTransient
    public List<AuCotizacionItems> getAuCotizacionItemsList() {
        return auCotizacionItemsList;
    }

    public void setAuCotizacionItemsList(List<AuCotizacionItems> auCotizacionItemsList) {
        this.auCotizacionItemsList = auCotizacionItemsList;
    }

    @XmlTransient
    public List<MpCotizaciones> getMpCotizacionesList() {
        return mpCotizacionesList;
    }

    public void setMpCotizacionesList(List<MpCotizaciones> mpCotizacionesList) {
        this.mpCotizacionesList = mpCotizacionesList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public MpPrescripciones getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripciones mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    @XmlTransient
    public List<AntAnticipoValores> getAntAnticipoValoresList() {
        return antAnticipoValoresList;
    }

    public void setAntAnticipoValoresList(List<AntAnticipoValores> antAnticipoValoresList) {
        this.antAnticipoValoresList = antAnticipoValoresList;
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
        if (!(object instanceof AuCotizaciones)) {
            return false;
        }
        AuCotizaciones other = (AuCotizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuCotizaciones[ id=" + id + " ]";
    }
    
}
