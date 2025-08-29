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
@Table(name = "tu_tutela_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuTutelaDiagnosticos.findAll", query = "SELECT t FROM TuTutelaDiagnosticos t"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findById", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.id = :id"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByMaDiagnosticosId", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.maDiagnosticosId = :maDiagnosticosId"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByMaDiagnosticosCodigo", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByMaDiagnosticosValor", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByMaeTipoDiagnosticoId", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.maeTipoDiagnosticoId = :maeTipoDiagnosticoId"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByMaeTipoDiagnosticoCodigo", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByMaeTipoDiagnosticoValor", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByEsPrincipal", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.esPrincipal = :esPrincipal"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByUsuarioCrea", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByTerminalCrea", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByFechaHoraCrea", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByUsuarioModifica", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByTerminalModifica", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuTutelaDiagnosticos.findByFechaHoraModifica", query = "SELECT t FROM TuTutelaDiagnosticos t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuTutelaDiagnosticos implements Serializable {

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
    @Column(name = "mae_tipo_diagnostico_id")
    private Integer maeTipoDiagnosticoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_diagnostico_codigo")
    private String maeTipoDiagnosticoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_diagnostico_valor")
    private String maeTipoDiagnosticoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_principal")
    private boolean esPrincipal;
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
    @JoinColumn(name = "tu_tutela_items_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelaItems tuTutelaItemsId;
    @JoinColumn(name = "tu_tutelas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelas tuTutelasId;

    public TuTutelaDiagnosticos() {
    }

    public TuTutelaDiagnosticos(Integer id) {
        this.id = id;
    }

    public TuTutelaDiagnosticos(Integer id, int maDiagnosticosId, String maDiagnosticosCodigo, String maDiagnosticosValor, boolean esPrincipal, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maDiagnosticosId = maDiagnosticosId;
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
        this.maDiagnosticosValor = maDiagnosticosValor;
        this.esPrincipal = esPrincipal;
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

    public Integer getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(Integer maeTipoDiagnosticoId) {
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

    public boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
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

    public TuTutelaItems getTuTutelaItemsId() {
        return tuTutelaItemsId;
    }

    public void setTuTutelaItemsId(TuTutelaItems tuTutelaItemsId) {
        this.tuTutelaItemsId = tuTutelaItemsId;
    }

    public TuTutelas getTuTutelasId() {
        return tuTutelasId;
    }

    public void setTuTutelasId(TuTutelas tuTutelasId) {
        this.tuTutelasId = tuTutelasId;
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
        if (!(object instanceof TuTutelaDiagnosticos)) {
            return false;
        }
        TuTutelaDiagnosticos other = (TuTutelaDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuTutelaDiagnosticos[ id=" + id + " ]";
    }
    
}
