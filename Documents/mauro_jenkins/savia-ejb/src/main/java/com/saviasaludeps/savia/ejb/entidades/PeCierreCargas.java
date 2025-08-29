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
@Table(name = "pe_cierre_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeCierreCargas.findAll", query = "SELECT p FROM PeCierreCargas p"),
    @NamedQuery(name = "PeCierreCargas.findById", query = "SELECT p FROM PeCierreCargas p WHERE p.id = :id"),
    @NamedQuery(name = "PeCierreCargas.findByMotivo", query = "SELECT p FROM PeCierreCargas p WHERE p.motivo = :motivo"),
    @NamedQuery(name = "PeCierreCargas.findByPeriodo", query = "SELECT p FROM PeCierreCargas p WHERE p.periodo = :periodo"),
    @NamedQuery(name = "PeCierreCargas.findByFechaHoraDesde", query = "SELECT p FROM PeCierreCargas p WHERE p.fechaHoraDesde = :fechaHoraDesde"),
    @NamedQuery(name = "PeCierreCargas.findByFechaHoraHasta", query = "SELECT p FROM PeCierreCargas p WHERE p.fechaHoraHasta = :fechaHoraHasta"),
    @NamedQuery(name = "PeCierreCargas.findByUsuarioCrea", query = "SELECT p FROM PeCierreCargas p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeCierreCargas.findByTerminalCrea", query = "SELECT p FROM PeCierreCargas p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeCierreCargas.findByFechaHoraCrea", query = "SELECT p FROM PeCierreCargas p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeCierreCargas.findByUsuarioModifica", query = "SELECT p FROM PeCierreCargas p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeCierreCargas.findByTerminalModifica", query = "SELECT p FROM PeCierreCargas p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeCierreCargas.findByFechaHoraModifica", query = "SELECT p FROM PeCierreCargas p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeCierreCargas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo")
    private int periodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraHasta;
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
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;

    public PeCierreCargas() {
    }

    public PeCierreCargas(Integer id) {
        this.id = id;
    }

    public PeCierreCargas(Integer id, String motivo, int periodo, Date fechaHoraDesde, Date fechaHoraHasta, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.motivo = motivo;
        this.periodo = periodo;
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Date getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public void setFechaHoraDesde(Date fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public Date getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public void setFechaHoraHasta(Date fechaHoraHasta) {
        this.fechaHoraHasta = fechaHoraHasta;
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

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
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
        if (!(object instanceof PeCierreCargas)) {
            return false;
        }
        PeCierreCargas other = (PeCierreCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeCierreCargas[ id=" + id + " ]";
    }
    
}
