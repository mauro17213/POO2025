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
@Table(name = "gat_atencion_comentarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatAtencionComentarios.findAll", query = "SELECT g FROM GatAtencionComentarios g"),
    @NamedQuery(name = "GatAtencionComentarios.findById", query = "SELECT g FROM GatAtencionComentarios g WHERE g.id = :id"),
    @NamedQuery(name = "GatAtencionComentarios.findByComentario", query = "SELECT g FROM GatAtencionComentarios g WHERE g.comentario = :comentario"),
    @NamedQuery(name = "GatAtencionComentarios.findByUsuarioCrea", query = "SELECT g FROM GatAtencionComentarios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatAtencionComentarios.findByTerminalCrea", query = "SELECT g FROM GatAtencionComentarios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatAtencionComentarios.findByFechaHoraCrea", query = "SELECT g FROM GatAtencionComentarios g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatAtencionComentarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "comentario")
    private String comentario;
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
    @JoinColumn(name = "gat_atenciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GatAtenciones gatAtencionesId;

    public GatAtencionComentarios() {
    }

    public GatAtencionComentarios(Integer id) {
        this.id = id;
    }

    public GatAtencionComentarios(Integer id, String comentario, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.comentario = comentario;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public GatAtenciones getGatAtencionesId() {
        return gatAtencionesId;
    }

    public void setGatAtencionesId(GatAtenciones gatAtencionesId) {
        this.gatAtencionesId = gatAtencionesId;
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
        if (!(object instanceof GatAtencionComentarios)) {
            return false;
        }
        GatAtencionComentarios other = (GatAtencionComentarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatAtencionComentarios[ id=" + id + " ]";
    }
    
}
