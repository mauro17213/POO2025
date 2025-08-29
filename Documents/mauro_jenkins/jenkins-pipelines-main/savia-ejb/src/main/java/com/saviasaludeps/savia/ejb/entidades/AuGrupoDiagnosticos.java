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
@Table(name = "au_grupo_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuGrupoDiagnosticos.findAll", query = "SELECT a FROM AuGrupoDiagnosticos a"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findById", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.id = :id"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByMaDiagnosticoId", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.maDiagnosticoId = :maDiagnosticoId"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByMaDiagnosticoCodigo", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.maDiagnosticoCodigo = :maDiagnosticoCodigo"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByMaDiagnosticoValor", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.maDiagnosticoValor = :maDiagnosticoValor"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByUsuarioCrea", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByTerminalCrea", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByFechaHoraCrea", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByUsuarioModifica", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByTerminalModifica", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuGrupoDiagnosticos.findByFechaHoraModifica", query = "SELECT a FROM AuGrupoDiagnosticos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuGrupoDiagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_diagnostico_id")
    private int maDiagnosticoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ma_diagnostico_codigo")
    private String maDiagnosticoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ma_diagnostico_valor")
    private String maDiagnosticoValor;
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
    @JoinColumn(name = "au_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuGrupos auGruposId;

    public AuGrupoDiagnosticos() {
    }

    public AuGrupoDiagnosticos(Integer id) {
        this.id = id;
    }

    public AuGrupoDiagnosticos(Integer id, int maDiagnosticoId, String maDiagnosticoCodigo, String maDiagnosticoValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maDiagnosticoId = maDiagnosticoId;
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
        this.maDiagnosticoValor = maDiagnosticoValor;
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

    public int getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(int maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
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

    public AuGrupos getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupos auGruposId) {
        this.auGruposId = auGruposId;
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
        if (!(object instanceof AuGrupoDiagnosticos)) {
            return false;
        }
        AuGrupoDiagnosticos other = (AuGrupoDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuGrupoDiagnosticos[ id=" + id + " ]";
    }
    
}
