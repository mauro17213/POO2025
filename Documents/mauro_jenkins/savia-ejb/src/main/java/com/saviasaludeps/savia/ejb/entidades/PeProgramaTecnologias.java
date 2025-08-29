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
@Table(name = "pe_programa_tecnologias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeProgramaTecnologias.findAll", query = "SELECT p FROM PeProgramaTecnologias p"),
    @NamedQuery(name = "PeProgramaTecnologias.findById", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.id = :id"),
    @NamedQuery(name = "PeProgramaTecnologias.findByTipoTecnologia", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "PeProgramaTecnologias.findByMaTecnologiaId", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "PeProgramaTecnologias.findByMaTecnologiaCodigo", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "PeProgramaTecnologias.findByMaTecnologiaValor", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "PeProgramaTecnologias.findByMarcaAutomatico", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.marcaAutomatico = :marcaAutomatico"),
    @NamedQuery(name = "PeProgramaTecnologias.findByDirecciona", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.direcciona = :direcciona"),
    @NamedQuery(name = "PeProgramaTecnologias.findByBorrado", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.borrado = :borrado"),
    @NamedQuery(name = "PeProgramaTecnologias.findByUsuarioCrea", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeProgramaTecnologias.findByTerminalCrea", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeProgramaTecnologias.findByFechaHoraCrea", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeProgramaTecnologias.findByUsuarioModifica", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeProgramaTecnologias.findByTerminalModifica", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeProgramaTecnologias.findByFechaHoraModifica", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "PeProgramaTecnologias.findByUsuarioBorra", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "PeProgramaTecnologias.findByTerminalBorra", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "PeProgramaTecnologias.findByFechaHoraBorra", query = "SELECT p FROM PeProgramaTecnologias p WHERE p.fechaHoraBorra = :fechaHoraBorra")})
public class PeProgramaTecnologias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private short tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
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

    public PeProgramaTecnologias() {
    }

    public PeProgramaTecnologias(Integer id) {
        this.id = id;
    }

    public PeProgramaTecnologias(Integer id, short tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, boolean marcaAutomatico, boolean direcciona, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.marcaAutomatico = marcaAutomatico;
        this.direcciona = direcciona;
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

    public short getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(short tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
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
        if (!(object instanceof PeProgramaTecnologias)) {
            return false;
        }
        PeProgramaTecnologias other = (PeProgramaTecnologias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeProgramaTecnologias[ id=" + id + " ]";
    }
    
}
