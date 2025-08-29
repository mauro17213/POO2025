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
@Table(name = "cntj_contrato_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjContratoHistoricos.findAll", query = "SELECT c FROM CntjContratoHistoricos c"),
    @NamedQuery(name = "CntjContratoHistoricos.findById", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjContratoHistoricos.findByTipo", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntjContratoHistoricos.findByFechaInicio", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntjContratoHistoricos.findByFechaFin", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "CntjContratoHistoricos.findByValor", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.valor = :valor"),
    @NamedQuery(name = "CntjContratoHistoricos.findByUsuarioCrea", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjContratoHistoricos.findByTerminalCrea", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjContratoHistoricos.findByFechaHoraCrea", query = "SELECT c FROM CntjContratoHistoricos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntjContratoHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private boolean tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
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
    @JoinColumn(name = "cntj_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratos cntjContratosId;

    public CntjContratoHistoricos() {
    }

    public CntjContratoHistoricos(Integer id) {
        this.id = id;
    }

    public CntjContratoHistoricos(Integer id, boolean tipo, Date fechaInicio, Date fechaFin, BigDecimal valor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.valor = valor;
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

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        if (!(object instanceof CntjContratoHistoricos)) {
            return false;
        }
        CntjContratoHistoricos other = (CntjContratoHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjContratoHistoricos[ id=" + id + " ]";
    }
    
}
