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
@Table(name = "rco_acta_asistentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoActaAsistentes.findAll", query = "SELECT r FROM RcoActaAsistentes r"),
    @NamedQuery(name = "RcoActaAsistentes.findById", query = "SELECT r FROM RcoActaAsistentes r WHERE r.id = :id"),
    @NamedQuery(name = "RcoActaAsistentes.findByNombre", query = "SELECT r FROM RcoActaAsistentes r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RcoActaAsistentes.findByCargo", query = "SELECT r FROM RcoActaAsistentes r WHERE r.cargo = :cargo"),
    @NamedQuery(name = "RcoActaAsistentes.findByFirma", query = "SELECT r FROM RcoActaAsistentes r WHERE r.firma = :firma"),
    @NamedQuery(name = "RcoActaAsistentes.findByObservaciones", query = "SELECT r FROM RcoActaAsistentes r WHERE r.observaciones = :observaciones"),
    @NamedQuery(name = "RcoActaAsistentes.findByUsuarioCrea", query = "SELECT r FROM RcoActaAsistentes r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoActaAsistentes.findByTerminalCrea", query = "SELECT r FROM RcoActaAsistentes r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoActaAsistentes.findByFechaHoraCrea", query = "SELECT r FROM RcoActaAsistentes r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RcoActaAsistentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 128)
    @Column(name = "firma")
    private String firma;
    @Size(max = 256)
    @Column(name = "observaciones")
    private String observaciones;
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
    @JoinColumn(name = "rco_actas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RcoActas rcoActasId;

    public RcoActaAsistentes() {
    }

    public RcoActaAsistentes(Integer id) {
        this.id = id;
    }

    public RcoActaAsistentes(Integer id, String nombre, String cargo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public RcoActas getRcoActasId() {
        return rcoActasId;
    }

    public void setRcoActasId(RcoActas rcoActasId) {
        this.rcoActasId = rcoActasId;
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
        if (!(object instanceof RcoActaAsistentes)) {
            return false;
        }
        RcoActaAsistentes other = (RcoActaAsistentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoActaAsistentes[ id=" + id + " ]";
    }
    
}
