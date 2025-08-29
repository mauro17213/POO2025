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
@Table(name = "gn_alertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnAlertas.findAll", query = "SELECT g FROM GnAlertas g"),
    @NamedQuery(name = "GnAlertas.findById", query = "SELECT g FROM GnAlertas g WHERE g.id = :id"),
    @NamedQuery(name = "GnAlertas.findBySeveridad", query = "SELECT g FROM GnAlertas g WHERE g.severidad = :severidad"),
    @NamedQuery(name = "GnAlertas.findByEstado", query = "SELECT g FROM GnAlertas g WHERE g.estado = :estado"),
    @NamedQuery(name = "GnAlertas.findByNombre", query = "SELECT g FROM GnAlertas g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnAlertas.findByDescripcion", query = "SELECT g FROM GnAlertas g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnAlertas.findByFechaHoraCrea", query = "SELECT g FROM GnAlertas g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnAlertas.findByFechaHoraLee", query = "SELECT g FROM GnAlertas g WHERE g.fechaHoraLee = :fechaHoraLee"),
    @NamedQuery(name = "GnAlertas.findByFechaHoraDescarta", query = "SELECT g FROM GnAlertas g WHERE g.fechaHoraDescarta = :fechaHoraDescarta")})
public class GnAlertas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "severidad")
    private int severidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Column(name = "fecha_hora_lee")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraLee;
    @Column(name = "fecha_hora_descarta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDescarta;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public GnAlertas() {
    }

    public GnAlertas(Integer id) {
        this.id = id;
    }

    public GnAlertas(Integer id, int severidad, int estado, String nombre, String descripcion, Date fechaHoraCrea) {
        this.id = id;
        this.severidad = severidad;
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeveridad() {
        return severidad;
    }

    public void setSeveridad(int severidad) {
        this.severidad = severidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Date getFechaHoraLee() {
        return fechaHoraLee;
    }

    public void setFechaHoraLee(Date fechaHoraLee) {
        this.fechaHoraLee = fechaHoraLee;
    }

    public Date getFechaHoraDescarta() {
        return fechaHoraDescarta;
    }

    public void setFechaHoraDescarta(Date fechaHoraDescarta) {
        this.fechaHoraDescarta = fechaHoraDescarta;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
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
        if (!(object instanceof GnAlertas)) {
            return false;
        }
        GnAlertas other = (GnAlertas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnAlertas[ id=" + id + " ]";
    }
    
}
