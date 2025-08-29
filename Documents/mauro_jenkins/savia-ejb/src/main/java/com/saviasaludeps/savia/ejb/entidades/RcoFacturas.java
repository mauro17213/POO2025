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
@Table(name = "rco_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoFacturas.findAll", query = "SELECT r FROM RcoFacturas r"),
    @NamedQuery(name = "RcoFacturas.findById", query = "SELECT r FROM RcoFacturas r WHERE r.id = :id"),
    @NamedQuery(name = "RcoFacturas.findByAplicaRecobro", query = "SELECT r FROM RcoFacturas r WHERE r.aplicaRecobro = :aplicaRecobro"),
    @NamedQuery(name = "RcoFacturas.findByTipoRecobro", query = "SELECT r FROM RcoFacturas r WHERE r.tipoRecobro = :tipoRecobro"),
    @NamedQuery(name = "RcoFacturas.findByMaeTipoContratoId", query = "SELECT r FROM RcoFacturas r WHERE r.maeTipoContratoId = :maeTipoContratoId"),
    @NamedQuery(name = "RcoFacturas.findByMaeTipoContratoCodigo", query = "SELECT r FROM RcoFacturas r WHERE r.maeTipoContratoCodigo = :maeTipoContratoCodigo"),
    @NamedQuery(name = "RcoFacturas.findByMaeTipoContratoValor", query = "SELECT r FROM RcoFacturas r WHERE r.maeTipoContratoValor = :maeTipoContratoValor"),
    @NamedQuery(name = "RcoFacturas.findByNumeroContrato", query = "SELECT r FROM RcoFacturas r WHERE r.numeroContrato = :numeroContrato"),
    @NamedQuery(name = "RcoFacturas.findByNit", query = "SELECT r FROM RcoFacturas r WHERE r.nit = :nit"),
    @NamedQuery(name = "RcoFacturas.findByIps", query = "SELECT r FROM RcoFacturas r WHERE r.ips = :ips"),
    @NamedQuery(name = "RcoFacturas.findByNumeroRadicado", query = "SELECT r FROM RcoFacturas r WHERE r.numeroRadicado = :numeroRadicado"),
    @NamedQuery(name = "RcoFacturas.findByNumeroFacturado", query = "SELECT r FROM RcoFacturas r WHERE r.numeroFacturado = :numeroFacturado"),
    @NamedQuery(name = "RcoFacturas.findByValorFactura", query = "SELECT r FROM RcoFacturas r WHERE r.valorFactura = :valorFactura"),
    @NamedQuery(name = "RcoFacturas.findByValorPagadoFactura", query = "SELECT r FROM RcoFacturas r WHERE r.valorPagadoFactura = :valorPagadoFactura"),
    @NamedQuery(name = "RcoFacturas.findByEstadoFactura", query = "SELECT r FROM RcoFacturas r WHERE r.estadoFactura = :estadoFactura"),
    @NamedQuery(name = "RcoFacturas.findByFechaHoraPrestacion", query = "SELECT r FROM RcoFacturas r WHERE r.fechaHoraPrestacion = :fechaHoraPrestacion"),
    @NamedQuery(name = "RcoFacturas.findByFechaHoraRadicacion", query = "SELECT r FROM RcoFacturas r WHERE r.fechaHoraRadicacion = :fechaHoraRadicacion"),
    @NamedQuery(name = "RcoFacturas.findByMaeRegimenId", query = "SELECT r FROM RcoFacturas r WHERE r.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "RcoFacturas.findByMaeRegimenCodigo", query = "SELECT r FROM RcoFacturas r WHERE r.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "RcoFacturas.findByMaeRegimenValor", query = "SELECT r FROM RcoFacturas r WHERE r.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "RcoFacturas.findByCmFechaHoraCrea", query = "SELECT r FROM RcoFacturas r WHERE r.cmFechaHoraCrea = :cmFechaHoraCrea"),
    @NamedQuery(name = "RcoFacturas.findByValorInicialGlosa", query = "SELECT r FROM RcoFacturas r WHERE r.valorInicialGlosa = :valorInicialGlosa"),
    @NamedQuery(name = "RcoFacturas.findByMaeEstadoRecobroId", query = "SELECT r FROM RcoFacturas r WHERE r.maeEstadoRecobroId = :maeEstadoRecobroId"),
    @NamedQuery(name = "RcoFacturas.findByMaeEstadoRecobroCodigo", query = "SELECT r FROM RcoFacturas r WHERE r.maeEstadoRecobroCodigo = :maeEstadoRecobroCodigo"),
    @NamedQuery(name = "RcoFacturas.findByMaeEstadoRecobroValor", query = "SELECT r FROM RcoFacturas r WHERE r.maeEstadoRecobroValor = :maeEstadoRecobroValor"),
    @NamedQuery(name = "RcoFacturas.findByUsuarioCrea", query = "SELECT r FROM RcoFacturas r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoFacturas.findByTerminalCrea", query = "SELECT r FROM RcoFacturas r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoFacturas.findByFechaHoraCrea", query = "SELECT r FROM RcoFacturas r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RcoFacturas.findByUsuarioModifica", query = "SELECT r FROM RcoFacturas r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RcoFacturas.findByTerminalModifica", query = "SELECT r FROM RcoFacturas r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RcoFacturas.findByFechaHoraModifica", query = "SELECT r FROM RcoFacturas r WHERE r.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "RcoFacturas.findByUsuarioAuditoria", query = "SELECT r FROM RcoFacturas r WHERE r.usuarioAuditoria = :usuarioAuditoria"),
    @NamedQuery(name = "RcoFacturas.findByTerminalAuditoria", query = "SELECT r FROM RcoFacturas r WHERE r.terminalAuditoria = :terminalAuditoria"),
    @NamedQuery(name = "RcoFacturas.findByFechaHoraAuditoria", query = "SELECT r FROM RcoFacturas r WHERE r.fechaHoraAuditoria = :fechaHoraAuditoria")})
public class RcoFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_recobro")
    private boolean aplicaRecobro;
    @Column(name = "tipo_recobro")
    private Integer tipoRecobro;
    @Column(name = "mae_tipo_contrato_id")
    private Integer maeTipoContratoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_contrato_codigo")
    private String maeTipoContratoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_contrato_valor")
    private String maeTipoContratoValor;
    @Size(max = 32)
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ips")
    private String ips;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_radicado")
    private int numeroRadicado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_facturado")
    private String numeroFacturado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_factura")
    private BigDecimal valorFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pagado_factura")
    private BigDecimal valorPagadoFactura;
    @Column(name = "estado_factura")
    private Integer estadoFactura;
    @Column(name = "fecha_hora_prestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraPrestacion;
    @Column(name = "fecha_hora_radicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRadicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_regimen_id")
    private int maeRegimenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Column(name = "cm_fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cmFechaHoraCrea;
    @Column(name = "valor_inicial_glosa")
    private BigDecimal valorInicialGlosa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_recobro_id")
    private int maeEstadoRecobroId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_recobro_codigo")
    private String maeEstadoRecobroCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_estado_recobro_valor")
    private String maeEstadoRecobroValor;
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
    @Column(name = "usuario_auditoria")
    private String usuarioAuditoria;
    @Size(max = 16)
    @Column(name = "terminal_auditoria")
    private String terminalAuditoria;
    @Column(name = "fecha_hora_auditoria")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAuditoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rcoFacturasId", fetch = FetchType.LAZY)
    private List<RcoFacturaDetalles> rcoFacturaDetallesList;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;

    public RcoFacturas() {
    }

    public RcoFacturas(Integer id) {
        this.id = id;
    }

    public RcoFacturas(Integer id, boolean aplicaRecobro, String nit, String ips, int numeroRadicado, String numeroFacturado, BigDecimal valorFactura, BigDecimal valorPagadoFactura, int maeRegimenId, String maeRegimenCodigo, String maeRegimenValor, int maeEstadoRecobroId, String maeEstadoRecobroCodigo, String maeEstadoRecobroValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.aplicaRecobro = aplicaRecobro;
        this.nit = nit;
        this.ips = ips;
        this.numeroRadicado = numeroRadicado;
        this.numeroFacturado = numeroFacturado;
        this.valorFactura = valorFactura;
        this.valorPagadoFactura = valorPagadoFactura;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenCodigo = maeRegimenCodigo;
        this.maeRegimenValor = maeRegimenValor;
        this.maeEstadoRecobroId = maeEstadoRecobroId;
        this.maeEstadoRecobroCodigo = maeEstadoRecobroCodigo;
        this.maeEstadoRecobroValor = maeEstadoRecobroValor;
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

    public boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }

    public Integer getTipoRecobro() {
        return tipoRecobro;
    }

    public void setTipoRecobro(Integer tipoRecobro) {
        this.tipoRecobro = tipoRecobro;
    }

    public Integer getMaeTipoContratoId() {
        return maeTipoContratoId;
    }

    public void setMaeTipoContratoId(Integer maeTipoContratoId) {
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

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
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

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroFacturado() {
        return numeroFacturado;
    }

    public void setNumeroFacturado(String numeroFacturado) {
        this.numeroFacturado = numeroFacturado;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public BigDecimal getValorPagadoFactura() {
        return valorPagadoFactura;
    }

    public void setValorPagadoFactura(BigDecimal valorPagadoFactura) {
        this.valorPagadoFactura = valorPagadoFactura;
    }

    public Integer getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(Integer estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Date getFechaHoraPrestacion() {
        return fechaHoraPrestacion;
    }

    public void setFechaHoraPrestacion(Date fechaHoraPrestacion) {
        this.fechaHoraPrestacion = fechaHoraPrestacion;
    }

    public Date getFechaHoraRadicacion() {
        return fechaHoraRadicacion;
    }

    public void setFechaHoraRadicacion(Date fechaHoraRadicacion) {
        this.fechaHoraRadicacion = fechaHoraRadicacion;
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

    public Date getCmFechaHoraCrea() {
        return cmFechaHoraCrea;
    }

    public void setCmFechaHoraCrea(Date cmFechaHoraCrea) {
        this.cmFechaHoraCrea = cmFechaHoraCrea;
    }

    public BigDecimal getValorInicialGlosa() {
        return valorInicialGlosa;
    }

    public void setValorInicialGlosa(BigDecimal valorInicialGlosa) {
        this.valorInicialGlosa = valorInicialGlosa;
    }

    public int getMaeEstadoRecobroId() {
        return maeEstadoRecobroId;
    }

    public void setMaeEstadoRecobroId(int maeEstadoRecobroId) {
        this.maeEstadoRecobroId = maeEstadoRecobroId;
    }

    public String getMaeEstadoRecobroCodigo() {
        return maeEstadoRecobroCodigo;
    }

    public void setMaeEstadoRecobroCodigo(String maeEstadoRecobroCodigo) {
        this.maeEstadoRecobroCodigo = maeEstadoRecobroCodigo;
    }

    public String getMaeEstadoRecobroValor() {
        return maeEstadoRecobroValor;
    }

    public void setMaeEstadoRecobroValor(String maeEstadoRecobroValor) {
        this.maeEstadoRecobroValor = maeEstadoRecobroValor;
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

    public String getUsuarioAuditoria() {
        return usuarioAuditoria;
    }

    public void setUsuarioAuditoria(String usuarioAuditoria) {
        this.usuarioAuditoria = usuarioAuditoria;
    }

    public String getTerminalAuditoria() {
        return terminalAuditoria;
    }

    public void setTerminalAuditoria(String terminalAuditoria) {
        this.terminalAuditoria = terminalAuditoria;
    }

    public Date getFechaHoraAuditoria() {
        return fechaHoraAuditoria;
    }

    public void setFechaHoraAuditoria(Date fechaHoraAuditoria) {
        this.fechaHoraAuditoria = fechaHoraAuditoria;
    }

    @XmlTransient
    public List<RcoFacturaDetalles> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalles> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
    }

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
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
        if (!(object instanceof RcoFacturas)) {
            return false;
        }
        RcoFacturas other = (RcoFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoFacturas[ id=" + id + " ]";
    }
    
}
