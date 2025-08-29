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
@Table(name = "au_no_solicitud_carga_sucesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudCargaSucesos.findAll", query = "SELECT a FROM AuNoSolicitudCargaSucesos a"),
    @NamedQuery(name = "AuNoSolicitudCargaSucesos.findById", query = "SELECT a FROM AuNoSolicitudCargaSucesos a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudCargaSucesos.findByTipo", query = "SELECT a FROM AuNoSolicitudCargaSucesos a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AuNoSolicitudCargaSucesos.findByDescripcion", query = "SELECT a FROM AuNoSolicitudCargaSucesos a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AuNoSolicitudCargaSucesos.findByFila", query = "SELECT a FROM AuNoSolicitudCargaSucesos a WHERE a.fila = :fila"),
    @NamedQuery(name = "AuNoSolicitudCargaSucesos.findByColumna", query = "SELECT a FROM AuNoSolicitudCargaSucesos a WHERE a.columna = :columna"),
    @NamedQuery(name = "AuNoSolicitudCargaSucesos.findByFechaHora", query = "SELECT a FROM AuNoSolicitudCargaSucesos a WHERE a.fechaHora = :fechaHora")})
public class AuNoSolicitudCargaSucesos implements Serializable {

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
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fila")
    private Integer fila;
    @Column(name = "columna")
    private Integer columna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "au_no_solicitud_carga_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudCargaDetalles auNoSolicitudCargaDetallesId;
    @JoinColumn(name = "au_no_solicitud_cargas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudCargas auNoSolicitudCargasId;

    public AuNoSolicitudCargaSucesos() {
    }

    public AuNoSolicitudCargaSucesos(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudCargaSucesos(Integer id, int tipo, String descripcion, Date fechaHora) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public AuNoSolicitudCargaDetalles getAuNoSolicitudCargaDetallesId() {
        return auNoSolicitudCargaDetallesId;
    }

    public void setAuNoSolicitudCargaDetallesId(AuNoSolicitudCargaDetalles auNoSolicitudCargaDetallesId) {
        this.auNoSolicitudCargaDetallesId = auNoSolicitudCargaDetallesId;
    }

    public AuNoSolicitudCargas getAuNoSolicitudCargasId() {
        return auNoSolicitudCargasId;
    }

    public void setAuNoSolicitudCargasId(AuNoSolicitudCargas auNoSolicitudCargasId) {
        this.auNoSolicitudCargasId = auNoSolicitudCargasId;
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
        if (!(object instanceof AuNoSolicitudCargaSucesos)) {
            return false;
        }
        AuNoSolicitudCargaSucesos other = (AuNoSolicitudCargaSucesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargaSucesos[ id=" + id + " ]";
    }
    
}
