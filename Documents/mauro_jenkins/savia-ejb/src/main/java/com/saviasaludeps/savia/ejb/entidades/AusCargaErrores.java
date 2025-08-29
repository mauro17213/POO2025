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
@Table(name = "aus_carga_errores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusCargaErrores.findAll", query = "SELECT a FROM AusCargaErrores a"),
    @NamedQuery(name = "AusCargaErrores.findById", query = "SELECT a FROM AusCargaErrores a WHERE a.id = :id"),
    @NamedQuery(name = "AusCargaErrores.findByFila", query = "SELECT a FROM AusCargaErrores a WHERE a.fila = :fila"),
    @NamedQuery(name = "AusCargaErrores.findByColumna", query = "SELECT a FROM AusCargaErrores a WHERE a.columna = :columna"),
    @NamedQuery(name = "AusCargaErrores.findByDescripcion", query = "SELECT a FROM AusCargaErrores a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AusCargaErrores.findByUsuarioCrea", query = "SELECT a FROM AusCargaErrores a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusCargaErrores.findByTerminalCrea", query = "SELECT a FROM AusCargaErrores a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusCargaErrores.findByFechaHoraCrea", query = "SELECT a FROM AusCargaErrores a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AusCargaErrores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fila")
    private Integer fila;
    @Column(name = "columna")
    private Integer columna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
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
    @JoinColumn(name = "aus_carga_masivas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AusCargaMasivas ausCargaMasivasId;

    public AusCargaErrores() {
    }

    public AusCargaErrores(Integer id) {
        this.id = id;
    }

    public AusCargaErrores(Integer id, String descripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public AusCargaMasivas getAusCargaMasivasId() {
        return ausCargaMasivasId;
    }

    public void setAusCargaMasivasId(AusCargaMasivas ausCargaMasivasId) {
        this.ausCargaMasivasId = ausCargaMasivasId;
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
        if (!(object instanceof AusCargaErrores)) {
            return false;
        }
        AusCargaErrores other = (AusCargaErrores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusCargaErrores[ id=" + id + " ]";
    }
    
}
