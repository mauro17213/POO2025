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
@Table(name = "au_no_solicitud_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudItems.findAll", query = "SELECT a FROM AuNoSolicitudItems a"),
    @NamedQuery(name = "AuNoSolicitudItems.findById", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudItems.findByCntContratoDetallesId", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.cntContratoDetallesId = :cntContratoDetallesId"),
    @NamedQuery(name = "AuNoSolicitudItems.findByEstado", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuNoSolicitudItems.findByEstadoJustificacion", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.estadoJustificacion = :estadoJustificacion"),
    @NamedQuery(name = "AuNoSolicitudItems.findByTipoTecnologia", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaTecnologiaId", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaTecnologiaCodigo", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaTecnologiaValor", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaMedicamentosId", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maMedicamentosId = :maMedicamentosId"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaMedicamentosCodigo", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maMedicamentosCodigo = :maMedicamentosCodigo"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaMedicamentosValor", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maMedicamentosValor = :maMedicamentosValor"),
    @NamedQuery(name = "AuNoSolicitudItems.findByCantidadSolicitada", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaServicioHabilitacionId", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maServicioHabilitacionId = :maServicioHabilitacionId"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaServicioHabilitacionCodigo", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maServicioHabilitacionCodigo = :maServicioHabilitacionCodigo"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaServicioHabilitacionValor", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maServicioHabilitacionValor = :maServicioHabilitacionValor"),
    @NamedQuery(name = "AuNoSolicitudItems.findByDosis", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.dosis = :dosis"),
    @NamedQuery(name = "AuNoSolicitudItems.findByFrecuencia", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.frecuencia = :frecuencia"),
    @NamedQuery(name = "AuNoSolicitudItems.findByPosologia", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.posologia = :posologia"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaeViaAdministracionId", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maeViaAdministracionId = :maeViaAdministracionId"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaeViaAdministracionCodigo", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maeViaAdministracionCodigo = :maeViaAdministracionCodigo"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaeViaAdministracionValor", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maeViaAdministracionValor = :maeViaAdministracionValor"),
    @NamedQuery(name = "AuNoSolicitudItems.findByMaeViaAdministracionTipo", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.maeViaAdministracionTipo = :maeViaAdministracionTipo"),
    @NamedQuery(name = "AuNoSolicitudItems.findByTipoEntrega", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.tipoEntrega = :tipoEntrega"),
    @NamedQuery(name = "AuNoSolicitudItems.findByDuracionTratamiento", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.duracionTratamiento = :duracionTratamiento"),
    @NamedQuery(name = "AuNoSolicitudItems.findByNumEntregas", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.numEntregas = :numEntregas"),
    @NamedQuery(name = "AuNoSolicitudItems.findByPbs", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.pbs = :pbs"),
    @NamedQuery(name = "AuNoSolicitudItems.findByValorUnitario", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "AuNoSolicitudItems.findByValorTotal", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.valorTotal = :valorTotal"),
    @NamedQuery(name = "AuNoSolicitudItems.findByBorrado", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AuNoSolicitudItems.findByUsuarioCrea", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuNoSolicitudItems.findByTerminalCrea", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuNoSolicitudItems.findByFechaHoraCrea", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuNoSolicitudItems.findByUsuarioModifica", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuNoSolicitudItems.findByTerminalModifica", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuNoSolicitudItems.findByFechaHoraModifica", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuNoSolicitudItems.findByUsuarioBorra", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuNoSolicitudItems.findByTerminalBorra", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuNoSolicitudItems.findByFechaHoraBorra", query = "SELECT a FROM AuNoSolicitudItems a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuNoSolicitudItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cnt_contrato_detalles_id")
    private Integer cntContratoDetallesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 1024)
    @Column(name = "estado_justificacion")
    private String estadoJustificacion;
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
    @Column(name = "ma_medicamentos_id")
    private Integer maMedicamentosId;
    @Size(max = 32)
    @Column(name = "ma_medicamentos_codigo")
    private String maMedicamentosCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamentos_valor")
    private String maMedicamentosValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    @Column(name = "ma_servicio_habilitacion_id")
    private Integer maServicioHabilitacionId;
    @Size(max = 32)
    @Column(name = "ma_servicio_habilitacion_codigo")
    private String maServicioHabilitacionCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_habilitacion_valor")
    private String maServicioHabilitacionValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dosis")
    private BigDecimal dosis;
    @Column(name = "frecuencia")
    private Integer frecuencia;
    @Size(max = 1024)
    @Column(name = "posologia")
    private String posologia;
    @Column(name = "mae_via_administracion_id")
    private Integer maeViaAdministracionId;
    @Size(max = 8)
    @Column(name = "mae_via_administracion_codigo")
    private String maeViaAdministracionCodigo;
    @Size(max = 128)
    @Column(name = "mae_via_administracion_valor")
    private String maeViaAdministracionValor;
    @Size(max = 4)
    @Column(name = "mae_via_administracion_tipo")
    private String maeViaAdministracionTipo;
    @Column(name = "tipo_entrega")
    private Integer tipoEntrega;
    @Column(name = "duracion_tratamiento")
    private Integer duracionTratamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_entregas")
    private int numEntregas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbs")
    private boolean pbs;
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @OneToMany(mappedBy = "auNoSolicitudItemsId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudEntregas> auNoSolicitudEntregasList;
    @JoinColumn(name = "au_no_solicitudes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuNoSolicitudes auNoSolicitudesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auNoSolicitudItemsId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudEntregaDetalles> auNoSolicitudEntregaDetallesList;

    public AuNoSolicitudItems() {
    }

    public AuNoSolicitudItems(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudItems(Integer id, int estado, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, int cantidadSolicitada, int numEntregas, boolean pbs, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.cantidadSolicitada = cantidadSolicitada;
        this.numEntregas = numEntregas;
        this.pbs = pbs;
        this.borrado = borrado;
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

    public Integer getCntContratoDetallesId() {
        return cntContratoDetallesId;
    }

    public void setCntContratoDetallesId(Integer cntContratoDetallesId) {
        this.cntContratoDetallesId = cntContratoDetallesId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
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

    public Integer getMaMedicamentosId() {
        return maMedicamentosId;
    }

    public void setMaMedicamentosId(Integer maMedicamentosId) {
        this.maMedicamentosId = maMedicamentosId;
    }

    public String getMaMedicamentosCodigo() {
        return maMedicamentosCodigo;
    }

    public void setMaMedicamentosCodigo(String maMedicamentosCodigo) {
        this.maMedicamentosCodigo = maMedicamentosCodigo;
    }

    public String getMaMedicamentosValor() {
        return maMedicamentosValor;
    }

    public void setMaMedicamentosValor(String maMedicamentosValor) {
        this.maMedicamentosValor = maMedicamentosValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
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

    public BigDecimal getDosis() {
        return dosis;
    }

    public void setDosis(BigDecimal dosis) {
        this.dosis = dosis;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public Integer getMaeViaAdministracionId() {
        return maeViaAdministracionId;
    }

    public void setMaeViaAdministracionId(Integer maeViaAdministracionId) {
        this.maeViaAdministracionId = maeViaAdministracionId;
    }

    public String getMaeViaAdministracionCodigo() {
        return maeViaAdministracionCodigo;
    }

    public void setMaeViaAdministracionCodigo(String maeViaAdministracionCodigo) {
        this.maeViaAdministracionCodigo = maeViaAdministracionCodigo;
    }

    public String getMaeViaAdministracionValor() {
        return maeViaAdministracionValor;
    }

    public void setMaeViaAdministracionValor(String maeViaAdministracionValor) {
        this.maeViaAdministracionValor = maeViaAdministracionValor;
    }

    public String getMaeViaAdministracionTipo() {
        return maeViaAdministracionTipo;
    }

    public void setMaeViaAdministracionTipo(String maeViaAdministracionTipo) {
        this.maeViaAdministracionTipo = maeViaAdministracionTipo;
    }

    public Integer getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(Integer tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public int getNumEntregas() {
        return numEntregas;
    }

    public void setNumEntregas(int numEntregas) {
        this.numEntregas = numEntregas;
    }

    public boolean getPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    @XmlTransient
    public List<AuNoSolicitudEntregas> getAuNoSolicitudEntregasList() {
        return auNoSolicitudEntregasList;
    }

    public void setAuNoSolicitudEntregasList(List<AuNoSolicitudEntregas> auNoSolicitudEntregasList) {
        this.auNoSolicitudEntregasList = auNoSolicitudEntregasList;
    }

    public AuNoSolicitudes getAuNoSolicitudesId() {
        return auNoSolicitudesId;
    }

    public void setAuNoSolicitudesId(AuNoSolicitudes auNoSolicitudesId) {
        this.auNoSolicitudesId = auNoSolicitudesId;
    }

    @XmlTransient
    public List<AuNoSolicitudEntregaDetalles> getAuNoSolicitudEntregaDetallesList() {
        return auNoSolicitudEntregaDetallesList;
    }

    public void setAuNoSolicitudEntregaDetallesList(List<AuNoSolicitudEntregaDetalles> auNoSolicitudEntregaDetallesList) {
        this.auNoSolicitudEntregaDetallesList = auNoSolicitudEntregaDetallesList;
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
        if (!(object instanceof AuNoSolicitudItems)) {
            return false;
        }
        AuNoSolicitudItems other = (AuNoSolicitudItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudItems[ id=" + id + " ]";
    }
    
}
