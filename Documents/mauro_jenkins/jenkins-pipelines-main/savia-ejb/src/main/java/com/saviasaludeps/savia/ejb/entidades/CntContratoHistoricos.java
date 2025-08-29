/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "cnt_contrato_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoHistoricos.findAll", query = "SELECT c FROM CntContratoHistoricos c"),
    @NamedQuery(name = "CntContratoHistoricos.findById", query = "SELECT c FROM CntContratoHistoricos c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoHistoricos.findByOrigen", query = "SELECT c FROM CntContratoHistoricos c WHERE c.origen = :origen"),
    @NamedQuery(name = "CntContratoHistoricos.findByTipo", query = "SELECT c FROM CntContratoHistoricos c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntContratoHistoricos.findByUsuarioCrea", query = "SELECT c FROM CntContratoHistoricos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoHistoricos.findByTerminalCrea", query = "SELECT c FROM CntContratoHistoricos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoHistoricos.findByFechaHoraCrea", query = "SELECT c FROM CntContratoHistoricos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntContratoHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "origen")
    private Integer origen;
    @Column(name = "tipo")
    private Integer tipo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "to_string")
    private String toString;
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
    @JoinColumn(name = "cnt_contrato_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratoDetalles cntContratoDetallesId;
    @JoinColumn(name = "cnt_contrato_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratoSedes cntContratoSedesId;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratos cntContratosId;

    public CntContratoHistoricos() {
    }

    public CntContratoHistoricos(Integer id) {
        this.id = id;
    }

    public CntContratoHistoricos(Integer id, String toString, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.toString = toString;
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

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
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

    public CntContratoDetalles getCntContratoDetallesId() {
        return cntContratoDetallesId;
    }

    public void setCntContratoDetallesId(CntContratoDetalles cntContratoDetallesId) {
        this.cntContratoDetallesId = cntContratoDetallesId;
    }

    public CntContratoSedes getCntContratoSedesId() {
        return cntContratoSedesId;
    }

    public void setCntContratoSedesId(CntContratoSedes cntContratoSedesId) {
        this.cntContratoSedesId = cntContratoSedesId;
    }

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
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
        if (!(object instanceof CntContratoHistoricos)) {
            return false;
        }
        CntContratoHistoricos other = (CntContratoHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoHistoricos[ id=" + id + " ]";
    }
    
}
