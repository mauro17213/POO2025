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
@Table(name = "aus_persona_telefonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusPersonaTelefonos.findAll", query = "SELECT a FROM AusPersonaTelefonos a"),
    @NamedQuery(name = "AusPersonaTelefonos.findById", query = "SELECT a FROM AusPersonaTelefonos a WHERE a.id = :id"),
    @NamedQuery(name = "AusPersonaTelefonos.findByNumero", query = "SELECT a FROM AusPersonaTelefonos a WHERE a.numero = :numero"),
    @NamedQuery(name = "AusPersonaTelefonos.findByObservacion", query = "SELECT a FROM AusPersonaTelefonos a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AusPersonaTelefonos.findByUsuarioCrea", query = "SELECT a FROM AusPersonaTelefonos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusPersonaTelefonos.findByTerminalCrea", query = "SELECT a FROM AusPersonaTelefonos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusPersonaTelefonos.findByFechaHoraCrea", query = "SELECT a FROM AusPersonaTelefonos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AusPersonaTelefonos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero")
    private String numero;
    @Size(max = 256)
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
    @JoinColumn(name = "aus_personas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AusPersonas ausPersonasId;

    public AusPersonaTelefonos() {
    }

    public AusPersonaTelefonos(Integer id) {
        this.id = id;
    }

    public AusPersonaTelefonos(Integer id, String numero, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numero = numero;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public AusPersonas getAusPersonasId() {
        return ausPersonasId;
    }

    public void setAusPersonasId(AusPersonas ausPersonasId) {
        this.ausPersonasId = ausPersonasId;
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
        if (!(object instanceof AusPersonaTelefonos)) {
            return false;
        }
        AusPersonaTelefonos other = (AusPersonaTelefonos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusPersonaTelefonos[ id=" + id + " ]";
    }
    
}
