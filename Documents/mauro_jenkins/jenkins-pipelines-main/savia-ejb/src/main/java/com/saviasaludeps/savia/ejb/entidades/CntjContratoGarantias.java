/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cntj_contrato_garantias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjContratoGarantias.findAll", query = "SELECT c FROM CntjContratoGarantias c"),
    @NamedQuery(name = "CntjContratoGarantias.findById", query = "SELECT c FROM CntjContratoGarantias c WHERE c.id = :id"),
    @NamedQuery(name = "CntjContratoGarantias.findByMaeGarantiaContratoId", query = "SELECT c FROM CntjContratoGarantias c WHERE c.maeGarantiaContratoId = :maeGarantiaContratoId"),
    @NamedQuery(name = "CntjContratoGarantias.findByMaeGarantiaContratoCodigo", query = "SELECT c FROM CntjContratoGarantias c WHERE c.maeGarantiaContratoCodigo = :maeGarantiaContratoCodigo"),
    @NamedQuery(name = "CntjContratoGarantias.findByMaeGarantiaContratoValor", query = "SELECT c FROM CntjContratoGarantias c WHERE c.maeGarantiaContratoValor = :maeGarantiaContratoValor"),
    @NamedQuery(name = "CntjContratoGarantias.findByFechaExpedicion", query = "SELECT c FROM CntjContratoGarantias c WHERE c.fechaExpedicion = :fechaExpedicion"),
    @NamedQuery(name = "CntjContratoGarantias.findByPorcentajeValorContrato", query = "SELECT c FROM CntjContratoGarantias c WHERE c.porcentajeValorContrato = :porcentajeValorContrato"),
    @NamedQuery(name = "CntjContratoGarantias.findByPorcentajeValorAnticipo", query = "SELECT c FROM CntjContratoGarantias c WHERE c.porcentajeValorAnticipo = :porcentajeValorAnticipo"),
    @NamedQuery(name = "CntjContratoGarantias.findByValorAsegurado", query = "SELECT c FROM CntjContratoGarantias c WHERE c.valorAsegurado = :valorAsegurado"),
    @NamedQuery(name = "CntjContratoGarantias.findByVigenciaDesde", query = "SELECT c FROM CntjContratoGarantias c WHERE c.vigenciaDesde = :vigenciaDesde"),
    @NamedQuery(name = "CntjContratoGarantias.findByVigenciaHasta", query = "SELECT c FROM CntjContratoGarantias c WHERE c.vigenciaHasta = :vigenciaHasta"),
    @NamedQuery(name = "CntjContratoGarantias.findByEstado", query = "SELECT c FROM CntjContratoGarantias c WHERE c.estado = :estado"),
    @NamedQuery(name = "CntjContratoGarantias.findByRequiereRenovacion", query = "SELECT c FROM CntjContratoGarantias c WHERE c.requiereRenovacion = :requiereRenovacion"),
    @NamedQuery(name = "CntjContratoGarantias.findByFechaAprobacion", query = "SELECT c FROM CntjContratoGarantias c WHERE c.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "CntjContratoGarantias.findByUsuarioCrea", query = "SELECT c FROM CntjContratoGarantias c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjContratoGarantias.findByTerminalCrea", query = "SELECT c FROM CntjContratoGarantias c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjContratoGarantias.findByFechaHoraCrea", query = "SELECT c FROM CntjContratoGarantias c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjContratoGarantias.findByUsuarioModifica", query = "SELECT c FROM CntjContratoGarantias c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjContratoGarantias.findByTerminalModifica", query = "SELECT c FROM CntjContratoGarantias c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjContratoGarantias.findByFechaHoraModifica", query = "SELECT c FROM CntjContratoGarantias c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjContratoGarantias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_garantia_contrato_id")
    private int maeGarantiaContratoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_garantia_contrato_codigo")
    private String maeGarantiaContratoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_garantia_contrato_valor")
    private String maeGarantiaContratoValor;
    @Column(name = "fecha_expedicion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_valor_contrato")
    private BigDecimal porcentajeValorContrato;
    @Column(name = "porcentaje_valor_anticipo")
    private BigDecimal porcentajeValorAnticipo;
    @Column(name = "valor_asegurado")
    private BigDecimal valorAsegurado;
    @Column(name = "vigencia_desde")
    @Temporal(TemporalType.DATE)
    private Date vigenciaDesde;
    @Column(name = "vigencia_hasta")
    @Temporal(TemporalType.DATE)
    private Date vigenciaHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "requiere_renovacion")
    private Boolean requiereRenovacion;
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;
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
    @JoinColumn(name = "cntj_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratos cntjContratosId;

    public CntjContratoGarantias() {
    }

    public CntjContratoGarantias(Integer id) {
        this.id = id;
    }

    public CntjContratoGarantias(Integer id, int maeGarantiaContratoId, String maeGarantiaContratoCodigo, String maeGarantiaContratoValor, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeGarantiaContratoId = maeGarantiaContratoId;
        this.maeGarantiaContratoCodigo = maeGarantiaContratoCodigo;
        this.maeGarantiaContratoValor = maeGarantiaContratoValor;
        this.estado = estado;
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

    public int getMaeGarantiaContratoId() {
        return maeGarantiaContratoId;
    }

    public void setMaeGarantiaContratoId(int maeGarantiaContratoId) {
        this.maeGarantiaContratoId = maeGarantiaContratoId;
    }

    public String getMaeGarantiaContratoCodigo() {
        return maeGarantiaContratoCodigo;
    }

    public void setMaeGarantiaContratoCodigo(String maeGarantiaContratoCodigo) {
        this.maeGarantiaContratoCodigo = maeGarantiaContratoCodigo;
    }

    public String getMaeGarantiaContratoValor() {
        return maeGarantiaContratoValor;
    }

    public void setMaeGarantiaContratoValor(String maeGarantiaContratoValor) {
        this.maeGarantiaContratoValor = maeGarantiaContratoValor;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public BigDecimal getPorcentajeValorContrato() {
        return porcentajeValorContrato;
    }

    public void setPorcentajeValorContrato(BigDecimal porcentajeValorContrato) {
        this.porcentajeValorContrato = porcentajeValorContrato;
    }

    public BigDecimal getPorcentajeValorAnticipo() {
        return porcentajeValorAnticipo;
    }

    public void setPorcentajeValorAnticipo(BigDecimal porcentajeValorAnticipo) {
        this.porcentajeValorAnticipo = porcentajeValorAnticipo;
    }

    public BigDecimal getValorAsegurado() {
        return valorAsegurado;
    }

    public void setValorAsegurado(BigDecimal valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }

    public Date getVigenciaDesde() {
        return vigenciaDesde;
    }

    public void setVigenciaDesde(Date vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    public Date getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(Date vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Boolean getRequiereRenovacion() {
        return requiereRenovacion;
    }

    public void setRequiereRenovacion(Boolean requiereRenovacion) {
        this.requiereRenovacion = requiereRenovacion;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
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

    public CntjContratos getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContratos cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
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
        if (!(object instanceof CntjContratoGarantias)) {
            return false;
        }
        CntjContratoGarantias other = (CntjContratoGarantias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjContratoGarantias[ id=" + id + " ]";
    }
    
}
