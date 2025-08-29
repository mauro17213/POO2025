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
@Table(name = "tu_personas_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuPersonasContactos.findAll", query = "SELECT t FROM TuPersonasContactos t"),
    @NamedQuery(name = "TuPersonasContactos.findById", query = "SELECT t FROM TuPersonasContactos t WHERE t.id = :id"),
    @NamedQuery(name = "TuPersonasContactos.findByNumeroContacto", query = "SELECT t FROM TuPersonasContactos t WHERE t.numeroContacto = :numeroContacto"),
    @NamedQuery(name = "TuPersonasContactos.findByObservacion", query = "SELECT t FROM TuPersonasContactos t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TuPersonasContactos.findByUsuarioCrea", query = "SELECT t FROM TuPersonasContactos t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuPersonasContactos.findByTerminalCrea", query = "SELECT t FROM TuPersonasContactos t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuPersonasContactos.findByFechaHoraCrea", query = "SELECT t FROM TuPersonasContactos t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuPersonasContactos.findByUsuarioModifica", query = "SELECT t FROM TuPersonasContactos t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuPersonasContactos.findByTerminalModifica", query = "SELECT t FROM TuPersonasContactos t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuPersonasContactos.findByFechaHoraModifica", query = "SELECT t FROM TuPersonasContactos t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuPersonasContactos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_contacto")
    private String numeroContacto;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "tu_personas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuPersonas tuPersonasId;

    public TuPersonasContactos() {
    }

    public TuPersonasContactos(Integer id) {
        this.id = id;
    }

    public TuPersonasContactos(Integer id, String numeroContacto, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numeroContacto = numeroContacto;
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

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public TuPersonas getTuPersonasId() {
        return tuPersonasId;
    }

    public void setTuPersonasId(TuPersonas tuPersonasId) {
        this.tuPersonasId = tuPersonasId;
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
        if (!(object instanceof TuPersonasContactos)) {
            return false;
        }
        TuPersonasContactos other = (TuPersonasContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuPersonasContactos[ id=" + id + " ]";
    }
    
}
