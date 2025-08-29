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
@Table(name = "ant_anticipo_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntAnticipoGestiones.findAll", query = "SELECT a FROM AntAnticipoGestiones a"),
    @NamedQuery(name = "AntAnticipoGestiones.findById", query = "SELECT a FROM AntAnticipoGestiones a WHERE a.id = :id"),
    @NamedQuery(name = "AntAnticipoGestiones.findByTipo", query = "SELECT a FROM AntAnticipoGestiones a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AntAnticipoGestiones.findByEstado", query = "SELECT a FROM AntAnticipoGestiones a WHERE a.estado = :estado"),
    @NamedQuery(name = "AntAnticipoGestiones.findByDescripcion", query = "SELECT a FROM AntAnticipoGestiones a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AntAnticipoGestiones.findByUsuarioCrea", query = "SELECT a FROM AntAnticipoGestiones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AntAnticipoGestiones.findByTerminalCrea", query = "SELECT a FROM AntAnticipoGestiones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AntAnticipoGestiones.findByFechaHoraCrea", query = "SELECT a FROM AntAnticipoGestiones a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AntAnticipoGestiones implements Serializable {

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
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
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
    @JoinColumn(name = "ant_anticipos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AntAnticipos antAnticiposId;

    public AntAnticipoGestiones() {
    }

    public AntAnticipoGestiones(Integer id) {
        this.id = id;
    }

    public AntAnticipoGestiones(Integer id, int tipo, String descripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    public AntAnticipos getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipos antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
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
        if (!(object instanceof AntAnticipoGestiones)) {
            return false;
        }
        AntAnticipoGestiones other = (AntAnticipoGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AntAnticipoGestiones[ id=" + id + " ]";
    }
    
}
