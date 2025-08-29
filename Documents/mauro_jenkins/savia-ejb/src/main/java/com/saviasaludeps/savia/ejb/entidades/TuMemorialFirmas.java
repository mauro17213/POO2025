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
import javax.persistence.Lob;
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
@Table(name = "tu_memorial_firmas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuMemorialFirmas.findAll", query = "SELECT t FROM TuMemorialFirmas t"),
    @NamedQuery(name = "TuMemorialFirmas.findById", query = "SELECT t FROM TuMemorialFirmas t WHERE t.id = :id"),
    @NamedQuery(name = "TuMemorialFirmas.findByNombre", query = "SELECT t FROM TuMemorialFirmas t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TuMemorialFirmas.findByUsuarioCrea", query = "SELECT t FROM TuMemorialFirmas t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuMemorialFirmas.findByTerminalCrea", query = "SELECT t FROM TuMemorialFirmas t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuMemorialFirmas.findByFechaHoraCrea", query = "SELECT t FROM TuMemorialFirmas t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuMemorialFirmas.findByUsuarioModifica", query = "SELECT t FROM TuMemorialFirmas t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuMemorialFirmas.findByTerminalModifica", query = "SELECT t FROM TuMemorialFirmas t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuMemorialFirmas.findByFechaHoraModifica", query = "SELECT t FROM TuMemorialFirmas t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuMemorialFirmas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "firma")
    private byte[] firma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
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
    @JoinColumn(name = "tu_memorial_persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuMemorialPersonas tuMemorialPersonaId;

    public TuMemorialFirmas() {
    }

    public TuMemorialFirmas(Integer id) {
        this.id = id;
    }

    public TuMemorialFirmas(Integer id, byte[] firma, String nombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.firma = firma;
        this.nombre = nombre;
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

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public TuMemorialPersonas getTuMemorialPersonaId() {
        return tuMemorialPersonaId;
    }

    public void setTuMemorialPersonaId(TuMemorialPersonas tuMemorialPersonaId) {
        this.tuMemorialPersonaId = tuMemorialPersonaId;
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
        if (!(object instanceof TuMemorialFirmas)) {
            return false;
        }
        TuMemorialFirmas other = (TuMemorialFirmas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuMemorialFirmas[ id=" + id + " ]";
    }
    
}
