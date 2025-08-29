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
@Table(name = "au_tope_afiliados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuTopeAfiliados.findAll", query = "SELECT a FROM AuTopeAfiliados a"),
    @NamedQuery(name = "AuTopeAfiliados.findById", query = "SELECT a FROM AuTopeAfiliados a WHERE a.id = :id"),
    @NamedQuery(name = "AuTopeAfiliados.findByActivo", query = "SELECT a FROM AuTopeAfiliados a WHERE a.activo = :activo"),
    @NamedQuery(name = "AuTopeAfiliados.findByJustificacionActivo", query = "SELECT a FROM AuTopeAfiliados a WHERE a.justificacionActivo = :justificacionActivo"),
    @NamedQuery(name = "AuTopeAfiliados.findByJustificacionInactivo", query = "SELECT a FROM AuTopeAfiliados a WHERE a.justificacionInactivo = :justificacionInactivo"),
    @NamedQuery(name = "AuTopeAfiliados.findByUsuarioCrea", query = "SELECT a FROM AuTopeAfiliados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuTopeAfiliados.findByTerminalCrea", query = "SELECT a FROM AuTopeAfiliados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuTopeAfiliados.findByFechaHoraCrea", query = "SELECT a FROM AuTopeAfiliados a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuTopeAfiliados.findByUsuarioModifica", query = "SELECT a FROM AuTopeAfiliados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuTopeAfiliados.findByTerminalModifica", query = "SELECT a FROM AuTopeAfiliados a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuTopeAfiliados.findByFechaHoraModifica", query = "SELECT a FROM AuTopeAfiliados a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuTopeAfiliados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "justificacion_activo")
    private String justificacionActivo;
    @Size(max = 1024)
    @Column(name = "justificacion_inactivo")
    private String justificacionInactivo;
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
    @JoinColumn(name = "ase_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados aseAfiliadosId;

    public AuTopeAfiliados() {
    }

    public AuTopeAfiliados(Integer id) {
        this.id = id;
    }

    public AuTopeAfiliados(Integer id, boolean activo, String justificacionActivo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.activo = activo;
        this.justificacionActivo = justificacionActivo;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getJustificacionActivo() {
        return justificacionActivo;
    }

    public void setJustificacionActivo(String justificacionActivo) {
        this.justificacionActivo = justificacionActivo;
    }

    public String getJustificacionInactivo() {
        return justificacionInactivo;
    }

    public void setJustificacionInactivo(String justificacionInactivo) {
        this.justificacionInactivo = justificacionInactivo;
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

    public AsegAfiliados getAseAfiliadosId() {
        return aseAfiliadosId;
    }

    public void setAseAfiliadosId(AsegAfiliados aseAfiliadosId) {
        this.aseAfiliadosId = aseAfiliadosId;
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
        if (!(object instanceof AuTopeAfiliados)) {
            return false;
        }
        AuTopeAfiliados other = (AuTopeAfiliados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuTopeAfiliados[ id=" + id + " ]";
    }
    
}
