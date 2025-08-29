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
@Table(name = "gn_maestro_relaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnMaestroRelaciones.findAll", query = "SELECT g FROM GnMaestroRelaciones g"),
    @NamedQuery(name = "GnMaestroRelaciones.findById", query = "SELECT g FROM GnMaestroRelaciones g WHERE g.id = :id"),
    @NamedQuery(name = "GnMaestroRelaciones.findByUsuarioCrea", query = "SELECT g FROM GnMaestroRelaciones g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnMaestroRelaciones.findByTerminalCrea", query = "SELECT g FROM GnMaestroRelaciones g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnMaestroRelaciones.findByFechaHoraCrea", query = "SELECT g FROM GnMaestroRelaciones g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GnMaestroRelaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 126)
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
    @JoinColumn(name = "gn_maestros_id_padre", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnMaestros gnMaestrosIdPadre;
    @JoinColumn(name = "gn_maestros_id_hijo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnMaestros gnMaestrosIdHijo;

    public GnMaestroRelaciones() {
    }

    public GnMaestroRelaciones(Integer id) {
        this.id = id;
    }

    public GnMaestroRelaciones(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public GnMaestros getGnMaestrosIdPadre() {
        return gnMaestrosIdPadre;
    }

    public void setGnMaestrosIdPadre(GnMaestros gnMaestrosIdPadre) {
        this.gnMaestrosIdPadre = gnMaestrosIdPadre;
    }

    public GnMaestros getGnMaestrosIdHijo() {
        return gnMaestrosIdHijo;
    }

    public void setGnMaestrosIdHijo(GnMaestros gnMaestrosIdHijo) {
        this.gnMaestrosIdHijo = gnMaestrosIdHijo;
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
        if (!(object instanceof GnMaestroRelaciones)) {
            return false;
        }
        GnMaestroRelaciones other = (GnMaestroRelaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnMaestroRelaciones[ id=" + id + " ]";
    }
    
}
