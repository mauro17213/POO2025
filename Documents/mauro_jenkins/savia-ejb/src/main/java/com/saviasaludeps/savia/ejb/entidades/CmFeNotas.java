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
@Table(name = "cm_fe_notas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeNotas.findAll", query = "SELECT c FROM CmFeNotas c"),
    @NamedQuery(name = "CmFeNotas.findById", query = "SELECT c FROM CmFeNotas c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeNotas.findByTipo", query = "SELECT c FROM CmFeNotas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmFeNotas.findByNumeroNota", query = "SELECT c FROM CmFeNotas c WHERE c.numeroNota = :numeroNota"),
    @NamedQuery(name = "CmFeNotas.findByValorNota", query = "SELECT c FROM CmFeNotas c WHERE c.valorNota = :valorNota"),
    @NamedQuery(name = "CmFeNotas.findByCude", query = "SELECT c FROM CmFeNotas c WHERE c.cude = :cude"),
    @NamedQuery(name = "CmFeNotas.findByFechaHoraEmision", query = "SELECT c FROM CmFeNotas c WHERE c.fechaHoraEmision = :fechaHoraEmision"),
    @NamedQuery(name = "CmFeNotas.findByUsuarioCrea", query = "SELECT c FROM CmFeNotas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmFeNotas.findByTerminalCrea", query = "SELECT c FROM CmFeNotas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmFeNotas.findByFechaHoraCrea", query = "SELECT c FROM CmFeNotas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmFeNotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_nota")
    private String numeroNota;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_nota")
    private BigDecimal valorNota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "cude")
    private String cude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEmision;
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
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @JoinColumn(name = "cm_fe_rips_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFeRipsFacturas cmFeRipsFacturasId;

    public CmFeNotas() {
    }

    public CmFeNotas(Integer id) {
        this.id = id;
    }

    public CmFeNotas(Integer id, int tipo, String numeroNota, BigDecimal valorNota, String cude, Date fechaHoraEmision, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.numeroNota = numeroNota;
        this.valorNota = valorNota;
        this.cude = cude;
        this.fechaHoraEmision = fechaHoraEmision;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public BigDecimal getValorNota() {
        return valorNota;
    }

    public void setValorNota(BigDecimal valorNota) {
        this.valorNota = valorNota;
    }

    public String getCude() {
        return cude;
    }

    public void setCude(String cude) {
        this.cude = cude;
    }

    public Date getFechaHoraEmision() {
        return fechaHoraEmision;
    }

    public void setFechaHoraEmision(Date fechaHoraEmision) {
        this.fechaHoraEmision = fechaHoraEmision;
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

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public CmFeRipsFacturas getCmFeRipsFacturasId() {
        return cmFeRipsFacturasId;
    }

    public void setCmFeRipsFacturasId(CmFeRipsFacturas cmFeRipsFacturasId) {
        this.cmFeRipsFacturasId = cmFeRipsFacturasId;
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
        if (!(object instanceof CmFeNotas)) {
            return false;
        }
        CmFeNotas other = (CmFeNotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeNotas[ id=" + id + " ]";
    }
    
}
