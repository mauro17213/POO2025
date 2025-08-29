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
@Table(name = "cm_auditoria_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findAll", query = "SELECT c FROM CmAuditoriaDiagnosticos c"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findById", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByMaDiagniosticosId", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.maDiagniosticosId = :maDiagniosticosId"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByMaDiagnosticosCodigo", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByMaDiagnosticosValor", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByPrincipal", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.principal = :principal"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByUsuarioCrea", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByUsuarioModifica", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByTerminalModifica", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmAuditoriaDiagnosticos.findByFechaHoraModifica", query = "SELECT c FROM CmAuditoriaDiagnosticos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmAuditoriaDiagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_diagniosticos_id")
    private int maDiagniosticosId;
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
    @JoinColumn(name = "cm_detalles_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;

    public CmAuditoriaDiagnosticos() {
    }

    public CmAuditoriaDiagnosticos(Integer id) {
        this.id = id;
    }

    public CmAuditoriaDiagnosticos(Integer id, int maDiagniosticosId, boolean principal, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maDiagniosticosId = maDiagniosticosId;
        this.principal = principal;
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

    public int getMaDiagniosticosId() {
        return maDiagniosticosId;
    }

    public void setMaDiagniosticosId(int maDiagniosticosId) {
        this.maDiagniosticosId = maDiagniosticosId;
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

    public CmDetalles getCmDetallesId() {
        return cmDetallesId;
    }

    public void setCmDetallesId(CmDetalles cmDetallesId) {
        this.cmDetallesId = cmDetallesId;
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
        if (!(object instanceof CmAuditoriaDiagnosticos)) {
            return false;
        }
        CmAuditoriaDiagnosticos other = (CmAuditoriaDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDiagnosticos[ id=" + id + " ]";
    }
    
}
