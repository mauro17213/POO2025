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
@Table(name = "pe_programa_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeProgramaDiagnosticos.findAll", query = "SELECT p FROM PeProgramaDiagnosticos p"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findById", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.id = :id"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByMaDiagnosticoId", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.maDiagnosticoId = :maDiagnosticoId"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByMaDiagnosticoCodigo", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.maDiagnosticoCodigo = :maDiagnosticoCodigo"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByMaDiagnosticoValor", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.maDiagnosticoValor = :maDiagnosticoValor"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByMarcaAutomatico", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.marcaAutomatico = :marcaAutomatico"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByDirecciona", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.direcciona = :direcciona"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByAplicaRescate", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.aplicaRescate = :aplicaRescate"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByBorrado", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.borrado = :borrado"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByUsuarioCrea", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByTerminalCrea", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByFechaHoraCrea", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByUsuarioModifica", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByTerminalModifica", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByFechaHoraModifica", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByUsuarioBorra", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByTerminalBorra", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "PeProgramaDiagnosticos.findByFechaHoraBorra", query = "SELECT p FROM PeProgramaDiagnosticos p WHERE p.fechaHoraBorra = :fechaHoraBorra")})
public class PeProgramaDiagnosticos implements Serializable {

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
    @Size(min = 1, max = 32)
    @Column(name = "ma_diagnostico_codigo")
    private String maDiagnosticoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_diagnostico_valor")
    private String maDiagnosticoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marca_automatico")
    private boolean marcaAutomatico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "direcciona")
    private boolean direcciona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate")
    private boolean aplicaRescate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;

    public PeProgramaDiagnosticos() {
    }

    public PeProgramaDiagnosticos(Integer id) {
        this.id = id;
    }

    public PeProgramaDiagnosticos(Integer id, int maDiagnosticoId, String maDiagnosticoCodigo, String maDiagnosticoValor, boolean marcaAutomatico, boolean direcciona, boolean aplicaRescate, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maDiagnosticoId = maDiagnosticoId;
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
        this.maDiagnosticoValor = maDiagnosticoValor;
        this.marcaAutomatico = marcaAutomatico;
        this.direcciona = direcciona;
        this.aplicaRescate = aplicaRescate;
        this.borrado = borrado;
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

    public boolean getMarcaAutomatico() {
        return marcaAutomatico;
    }

    public void setMarcaAutomatico(boolean marcaAutomatico) {
        this.marcaAutomatico = marcaAutomatico;
    }

    public boolean getDirecciona() {
        return direcciona;
    }

    public void setDirecciona(boolean direcciona) {
        this.direcciona = direcciona;
    }

    public boolean getAplicaRescate() {
        return aplicaRescate;
    }

    public void setAplicaRescate(boolean aplicaRescate) {
        this.aplicaRescate = aplicaRescate;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
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
        if (!(object instanceof PeProgramaDiagnosticos)) {
            return false;
        }
        PeProgramaDiagnosticos other = (PeProgramaDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeProgramaDiagnosticos[ id=" + id + " ]";
    }
    
}
