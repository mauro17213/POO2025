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
@Table(name = "au_no_solicitud_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findAll", query = "SELECT a FROM AuNoSolicitudDiagnosticos a"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findById", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByPrincipal", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.principal = :principal"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByMaDiagnosticosId", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.maDiagnosticosId = :maDiagnosticosId"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByMaDiagnosticosCodigo", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByMaDiagnosticosValor", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByAltoCosto", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.altoCosto = :altoCosto"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByUsuarioCrea", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByTerminalCrea", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByFechaHoraCrea", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByUsuarioModifica", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByTerminalModifica", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuNoSolicitudDiagnosticos.findByFechaHoraModifica", query = "SELECT a FROM AuNoSolicitudDiagnosticos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuNoSolicitudDiagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "principal")
    private boolean principal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_diagnosticos_id")
    private int maDiagnosticosId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_diagnosticos_codigo")
    private String maDiagnosticosCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_diagnosticos_valor")
    private String maDiagnosticosValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alto_costo")
    private boolean altoCosto;
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
    @JoinColumn(name = "au_no_solicitudes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuNoSolicitudes auNoSolicitudesId;

    public AuNoSolicitudDiagnosticos() {
    }

    public AuNoSolicitudDiagnosticos(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudDiagnosticos(Integer id, boolean principal, int maDiagnosticosId, String maDiagnosticosCodigo, String maDiagnosticosValor, boolean altoCosto, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.principal = principal;
        this.maDiagnosticosId = maDiagnosticosId;
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
        this.maDiagnosticosValor = maDiagnosticosValor;
        this.altoCosto = altoCosto;
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

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public int getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(int maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    public String getMaDiagnosticosCodigo() {
        return maDiagnosticosCodigo;
    }

    public void setMaDiagnosticosCodigo(String maDiagnosticosCodigo) {
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
    }

    public String getMaDiagnosticosValor() {
        return maDiagnosticosValor;
    }

    public void setMaDiagnosticosValor(String maDiagnosticosValor) {
        this.maDiagnosticosValor = maDiagnosticosValor;
    }

    public boolean getAltoCosto() {
        return altoCosto;
    }

    public void setAltoCosto(boolean altoCosto) {
        this.altoCosto = altoCosto;
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

    public AuNoSolicitudes getAuNoSolicitudesId() {
        return auNoSolicitudesId;
    }

    public void setAuNoSolicitudesId(AuNoSolicitudes auNoSolicitudesId) {
        this.auNoSolicitudesId = auNoSolicitudesId;
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
        if (!(object instanceof AuNoSolicitudDiagnosticos)) {
            return false;
        }
        AuNoSolicitudDiagnosticos other = (AuNoSolicitudDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudDiagnosticos[ id=" + id + " ]";
    }
    
}
