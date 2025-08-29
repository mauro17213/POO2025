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
@Table(name = "pe_afiliado_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findAll", query = "SELECT p FROM PeAfiliadoDiagnosticos p"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findById", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.id = :id"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByMaDiagnosticosId", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.maDiagnosticosId = :maDiagnosticosId"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByMaDiagnosticosCodigo", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByMaDiagnosticosValor", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByPrincipal", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.principal = :principal"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByUsuarioCrea", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByTerminalCrea", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByFehaHoraCrea", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.fehaHoraCrea = :fehaHoraCrea"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByUsuarioModifica", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByTerminalModifica", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeAfiliadoDiagnosticos.findByFechaHoraModifica", query = "SELECT p FROM PeAfiliadoDiagnosticos p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeAfiliadoDiagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ma_diagnosticos_id")
    private String maDiagnosticosId;
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
    @Column(name = "principal")
    private boolean principal;
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
    @Column(name = "feha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fehaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "pe_afiliados_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeAfiliadosProgramas peAfiliadosProgramasId;

    public PeAfiliadoDiagnosticos() {
    }

    public PeAfiliadoDiagnosticos(Integer id) {
        this.id = id;
    }

    public PeAfiliadoDiagnosticos(Integer id, String maDiagnosticosId, String maDiagnosticosCodigo, String maDiagnosticosValor, boolean principal, String usuarioCrea, String terminalCrea, Date fehaHoraCrea) {
        this.id = id;
        this.maDiagnosticosId = maDiagnosticosId;
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
        this.maDiagnosticosValor = maDiagnosticosValor;
        this.principal = principal;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fehaHoraCrea = fehaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(String maDiagnosticosId) {
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

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
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

    public Date getFehaHoraCrea() {
        return fehaHoraCrea;
    }

    public void setFehaHoraCrea(Date fehaHoraCrea) {
        this.fehaHoraCrea = fehaHoraCrea;
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

    public PeAfiliadosProgramas getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(PeAfiliadosProgramas peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
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
        if (!(object instanceof PeAfiliadoDiagnosticos)) {
            return false;
        }
        PeAfiliadoDiagnosticos other = (PeAfiliadoDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeAfiliadoDiagnosticos[ id=" + id + " ]";
    }
    
}
