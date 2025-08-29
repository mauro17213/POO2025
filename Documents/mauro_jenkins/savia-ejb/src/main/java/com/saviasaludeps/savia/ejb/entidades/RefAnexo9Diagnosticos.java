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
@Table(name = "ref_anexo9_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefAnexo9Diagnosticos.findAll", query = "SELECT r FROM RefAnexo9Diagnosticos r"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findById", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.id = :id"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByMaDiagnosticosId", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.maDiagnosticosId = :maDiagnosticosId"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByMaDiagnosticosCodigo", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByMaDiagnosticosValor", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByPrincipal", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.principal = :principal"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByUsuarioCrea", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByTerminalCrea", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByFechaHoraCrea", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByUsuarioModifica", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByTerminalModifica", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RefAnexo9Diagnosticos.findByFechaHoraModifica", query = "SELECT r FROM RefAnexo9Diagnosticos r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RefAnexo9Diagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_diagnosticos_id")
    private int maDiagnosticosId;
    @Size(max = 32)
    @Column(name = "ma_diagnosticos_codigo")
    private String maDiagnosticosCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnosticos_valor")
    private String maDiagnosticosValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "principal")
    private boolean principal;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
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
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;

    public RefAnexo9Diagnosticos() {
    }

    public RefAnexo9Diagnosticos(Integer id) {
        this.id = id;
    }

    public RefAnexo9Diagnosticos(Integer id, int maDiagnosticosId, boolean principal) {
        this.id = id;
        this.maDiagnosticosId = maDiagnosticosId;
        this.principal = principal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
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
        if (!(object instanceof RefAnexo9Diagnosticos)) {
            return false;
        }
        RefAnexo9Diagnosticos other = (RefAnexo9Diagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefAnexo9Diagnosticos[ id=" + id + " ]";
    }
    
}
