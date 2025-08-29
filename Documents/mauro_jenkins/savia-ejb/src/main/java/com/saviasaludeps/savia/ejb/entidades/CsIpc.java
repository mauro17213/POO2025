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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cs_ipc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsIpc.findAll", query = "SELECT c FROM CsIpc c"),
    @NamedQuery(name = "CsIpc.findById", query = "SELECT c FROM CsIpc c WHERE c.id = :id"),
    @NamedQuery(name = "CsIpc.findByAgno", query = "SELECT c FROM CsIpc c WHERE c.agno = :agno"),
    @NamedQuery(name = "CsIpc.findByMes", query = "SELECT c FROM CsIpc c WHERE c.mes = :mes"),
    @NamedQuery(name = "CsIpc.findByIncremento", query = "SELECT c FROM CsIpc c WHERE c.incremento = :incremento"),
    @NamedQuery(name = "CsIpc.findByUsuarioCrea", query = "SELECT c FROM CsIpc c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CsIpc.findByTerminalCrea", query = "SELECT c FROM CsIpc c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CsIpc.findByFechaHoraCrea", query = "SELECT c FROM CsIpc c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CsIpc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agno")
    private int agno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mes")
    private int mes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "incremento")
    private BigDecimal incremento;
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

    public CsIpc() {
    }

    public CsIpc(Integer id) {
        this.id = id;
    }

    public CsIpc(Integer id, int agno, int mes, BigDecimal incremento, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.agno = agno;
        this.mes = mes;
        this.incremento = incremento;
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

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public BigDecimal getIncremento() {
        return incremento;
    }

    public void setIncremento(BigDecimal incremento) {
        this.incremento = incremento;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CsIpc)) {
            return false;
        }
        CsIpc other = (CsIpc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CsIpc[ id=" + id + " ]";
    }
    
}
