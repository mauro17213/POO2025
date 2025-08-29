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
@Table(name = "gn_dias_habiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnDiasHabiles.findAll", query = "SELECT g FROM GnDiasHabiles g"),
    @NamedQuery(name = "GnDiasHabiles.findById", query = "SELECT g FROM GnDiasHabiles g WHERE g.id = :id"),
    @NamedQuery(name = "GnDiasHabiles.findByAgno", query = "SELECT g FROM GnDiasHabiles g WHERE g.agno = :agno"),
    @NamedQuery(name = "GnDiasHabiles.findByFecha", query = "SELECT g FROM GnDiasHabiles g WHERE g.fecha = :fecha"),
    @NamedQuery(name = "GnDiasHabiles.findByHabil", query = "SELECT g FROM GnDiasHabiles g WHERE g.habil = :habil"),
    @NamedQuery(name = "GnDiasHabiles.findByUsuarioCrea", query = "SELECT g FROM GnDiasHabiles g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnDiasHabiles.findByTerminalCrea", query = "SELECT g FROM GnDiasHabiles g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnDiasHabiles.findByFechaHoraCrea", query = "SELECT g FROM GnDiasHabiles g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GnDiasHabiles implements Serializable {

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
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habil")
    private boolean habil;
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

    public GnDiasHabiles() {
    }

    public GnDiasHabiles(Integer id) {
        this.id = id;
    }

    public GnDiasHabiles(Integer id, int agno, Date fecha, boolean habil, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.agno = agno;
        this.fecha = fecha;
        this.habil = habil;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getHabil() {
        return habil;
    }

    public void setHabil(boolean habil) {
        this.habil = habil;
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
        if (!(object instanceof GnDiasHabiles)) {
            return false;
        }
        GnDiasHabiles other = (GnDiasHabiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnDiasHabiles[ id=" + id + " ]";
    }
    
}
