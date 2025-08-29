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
@Table(name = "cntj_comite_asistentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjComiteAsistentes.findAll", query = "SELECT c FROM CntjComiteAsistentes c"),
    @NamedQuery(name = "CntjComiteAsistentes.findById", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.id = :id"),
    @NamedQuery(name = "CntjComiteAsistentes.findByAprueba", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.aprueba = :aprueba"),
    @NamedQuery(name = "CntjComiteAsistentes.findByObservaciones", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CntjComiteAsistentes.findByBorrado", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.borrado = :borrado"),
    @NamedQuery(name = "CntjComiteAsistentes.findByUsuarioCrea", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjComiteAsistentes.findByTerminalCrea", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjComiteAsistentes.findByFechaHoraCrea", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjComiteAsistentes.findByUsuarioModifica", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjComiteAsistentes.findByTerminalModifica", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjComiteAsistentes.findByFechaHoraModifica", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "CntjComiteAsistentes.findByUsuarioBorra", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "CntjComiteAsistentes.findByTerminalBorra", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "CntjComiteAsistentes.findByFechaHoraBorra", query = "SELECT c FROM CntjComiteAsistentes c WHERE c.fechaHoraBorra = :fechaHoraBorra")})
public class CntjComiteAsistentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aprueba")
    private boolean aprueba;
    @Size(max = 512)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "cntj_comites_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjComites cntjComitesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public CntjComiteAsistentes() {
    }

    public CntjComiteAsistentes(Integer id) {
        this.id = id;
    }

    public CntjComiteAsistentes(Integer id, boolean aprueba, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.aprueba = aprueba;
        this.borrado = borrado;
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

    public boolean getAprueba() {
        return aprueba;
    }

    public void setAprueba(boolean aprueba) {
        this.aprueba = aprueba;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public CntjComites getCntjComitesId() {
        return cntjComitesId;
    }

    public void setCntjComitesId(CntjComites cntjComitesId) {
        this.cntjComitesId = cntjComitesId;
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
        if (!(object instanceof CntjComiteAsistentes)) {
            return false;
        }
        CntjComiteAsistentes other = (CntjComiteAsistentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjComiteAsistentes[ id=" + id + " ]";
    }
    
}
