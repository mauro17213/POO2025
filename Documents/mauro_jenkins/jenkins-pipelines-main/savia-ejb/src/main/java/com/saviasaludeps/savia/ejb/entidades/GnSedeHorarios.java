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
@Table(name = "gn_sede_horarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnSedeHorarios.findAll", query = "SELECT g FROM GnSedeHorarios g"),
    @NamedQuery(name = "GnSedeHorarios.findById", query = "SELECT g FROM GnSedeHorarios g WHERE g.id = :id"),
    @NamedQuery(name = "GnSedeHorarios.findByDias", query = "SELECT g FROM GnSedeHorarios g WHERE g.dias = :dias"),
    @NamedQuery(name = "GnSedeHorarios.findByHoraDesde", query = "SELECT g FROM GnSedeHorarios g WHERE g.horaDesde = :horaDesde"),
    @NamedQuery(name = "GnSedeHorarios.findByHoraHasta", query = "SELECT g FROM GnSedeHorarios g WHERE g.horaHasta = :horaHasta"),
    @NamedQuery(name = "GnSedeHorarios.findByActivo", query = "SELECT g FROM GnSedeHorarios g WHERE g.activo = :activo"),
    @NamedQuery(name = "GnSedeHorarios.findByUsuarioCrea", query = "SELECT g FROM GnSedeHorarios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnSedeHorarios.findByTerminalCrea", query = "SELECT g FROM GnSedeHorarios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnSedeHorarios.findByFechaHoraCrea", query = "SELECT g FROM GnSedeHorarios g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnSedeHorarios.findByUsuarioModifica", query = "SELECT g FROM GnSedeHorarios g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnSedeHorarios.findByTerminalModifica", query = "SELECT g FROM GnSedeHorarios g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnSedeHorarios.findByFechaHoraModifica", query = "SELECT g FROM GnSedeHorarios g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnSedeHorarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "dias")
    private String dias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_desde")
    @Temporal(TemporalType.TIME)
    private Date horaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_hasta")
    @Temporal(TemporalType.TIME)
    private Date horaHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
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
    @JoinColumn(name = "gn_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnSedes gnSedesId;

    public GnSedeHorarios() {
    }

    public GnSedeHorarios(Integer id) {
        this.id = id;
    }

    public GnSedeHorarios(Integer id, String dias, Date horaDesde, Date horaHasta, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.dias = dias;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.activo = activo;
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

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public Date getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Date horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public GnSedes getGnSedesId() {
        return gnSedesId;
    }

    public void setGnSedesId(GnSedes gnSedesId) {
        this.gnSedesId = gnSedesId;
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
        if (!(object instanceof GnSedeHorarios)) {
            return false;
        }
        GnSedeHorarios other = (GnSedeHorarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnSedeHorarios[ id=" + id + " ]";
    }
    
}
