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
@Table(name = "au_anexo3_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3Diagnosticos.findAll", query = "SELECT a FROM AuAnexo3Diagnosticos a"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findById", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByPrincipal", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.principal = :principal"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByMaDiagnosticosId", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.maDiagnosticosId = :maDiagnosticosId"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByMaDiagnosticosCodigo", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByMaDiagnosticosValor", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByMaeTipoDiagnosticoId", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.maeTipoDiagnosticoId = :maeTipoDiagnosticoId"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByMaeTipoDiagnosticoCodigo", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByMaeTipoDiagnosticoValor", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByTerminalCrea", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByUsuarioModifica", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuAnexo3Diagnosticos.findByTerminalModifica", query = "SELECT a FROM AuAnexo3Diagnosticos a WHERE a.terminalModifica = :terminalModifica")})
public class AuAnexo3Diagnosticos implements Serializable {

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
    @Size(max = 32)
    @Column(name = "ma_diagnosticos_codigo")
    private String maDiagnosticosCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnosticos_valor")
    private String maDiagnosticosValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_diagnostico_id")
    private int maeTipoDiagnosticoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_diagnostico_codigo")
    private String maeTipoDiagnosticoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_diagnostico_valor")
    private String maeTipoDiagnosticoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;

    public AuAnexo3Diagnosticos() {
    }

    public AuAnexo3Diagnosticos(Integer id) {
        this.id = id;
    }

    public AuAnexo3Diagnosticos(Integer id, boolean principal, int maDiagnosticosId, int maeTipoDiagnosticoId, String maeTipoDiagnosticoCodigo, String maeTipoDiagnosticoValor, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.principal = principal;
        this.maDiagnosticosId = maDiagnosticosId;
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
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

    public int getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(int maeTipoDiagnosticoId) {
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
    }

    public String getMaeTipoDiagnosticoCodigo() {
        return maeTipoDiagnosticoCodigo;
    }

    public void setMaeTipoDiagnosticoCodigo(String maeTipoDiagnosticoCodigo) {
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
    }

    public String getMaeTipoDiagnosticoValor() {
        return maeTipoDiagnosticoValor;
    }

    public void setMaeTipoDiagnosticoValor(String maeTipoDiagnosticoValor) {
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
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
        if (!(object instanceof AuAnexo3Diagnosticos)) {
            return false;
        }
        AuAnexo3Diagnosticos other = (AuAnexo3Diagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3Diagnosticos[ id=" + id + " ]";
    }
    
}
