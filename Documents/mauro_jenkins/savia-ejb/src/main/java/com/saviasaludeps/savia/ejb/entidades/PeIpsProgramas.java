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
@Table(name = "pe_ips_programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeIpsProgramas.findAll", query = "SELECT p FROM PeIpsProgramas p"),
    @NamedQuery(name = "PeIpsProgramas.findById", query = "SELECT p FROM PeIpsProgramas p WHERE p.id = :id"),
    @NamedQuery(name = "PeIpsProgramas.findByFechaInicio", query = "SELECT p FROM PeIpsProgramas p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PeIpsProgramas.findByFechaFin", query = "SELECT p FROM PeIpsProgramas p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "PeIpsProgramas.findByActivo", query = "SELECT p FROM PeIpsProgramas p WHERE p.activo = :activo"),
    @NamedQuery(name = "PeIpsProgramas.findByUsuariosCrea", query = "SELECT p FROM PeIpsProgramas p WHERE p.usuariosCrea = :usuariosCrea"),
    @NamedQuery(name = "PeIpsProgramas.findByTerminalCrea", query = "SELECT p FROM PeIpsProgramas p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeIpsProgramas.findByFechaHoraCrea", query = "SELECT p FROM PeIpsProgramas p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeIpsProgramas.findByUsuarioModifica", query = "SELECT p FROM PeIpsProgramas p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeIpsProgramas.findByTerminalModifica", query = "SELECT p FROM PeIpsProgramas p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeIpsProgramas.findByFechaHoraModifica", query = "SELECT p FROM PeIpsProgramas p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeIpsProgramas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuarios_crea")
    private String usuariosCrea;
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
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;

    public PeIpsProgramas() {
    }

    public PeIpsProgramas(Integer id) {
        this.id = id;
    }

    public PeIpsProgramas(Integer id, Date fechaInicio, boolean activo, String usuariosCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.activo = activo;
        this.usuariosCrea = usuariosCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUsuariosCrea() {
        return usuariosCrea;
    }

    public void setUsuariosCrea(String usuariosCrea) {
        this.usuariosCrea = usuariosCrea;
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

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
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
        if (!(object instanceof PeIpsProgramas)) {
            return false;
        }
        PeIpsProgramas other = (PeIpsProgramas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeIpsProgramas[ id=" + id + " ]";
    }
    
}
