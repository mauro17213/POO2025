/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "mp_codigo_unirs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpCodigoUnirs.findAll", query = "SELECT m FROM MpCodigoUnirs m"),
    @NamedQuery(name = "MpCodigoUnirs.findById", query = "SELECT m FROM MpCodigoUnirs m WHERE m.id = :id"),
    @NamedQuery(name = "MpCodigoUnirs.findByCodigo", query = "SELECT m FROM MpCodigoUnirs m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MpCodigoUnirs.findByIndicacion", query = "SELECT m FROM MpCodigoUnirs m WHERE m.indicacion = :indicacion"),
    @NamedQuery(name = "MpCodigoUnirs.findByUsuarioCrea", query = "SELECT m FROM MpCodigoUnirs m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpCodigoUnirs.findByTerminalCrea", query = "SELECT m FROM MpCodigoUnirs m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpCodigoUnirs.findByFechaHoraCrea", query = "SELECT m FROM MpCodigoUnirs m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpCodigoUnirs.findByUsuarioModifica", query = "SELECT m FROM MpCodigoUnirs m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpCodigoUnirs.findByTerminalModifica", query = "SELECT m FROM MpCodigoUnirs m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpCodigoUnirs.findByFechaHoraModifica", query = "SELECT m FROM MpCodigoUnirs m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MpCodigoUnirs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "indicacion")
    private String indicacion;
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
    @OneToMany(mappedBy = "mpCodigoUnirsId", fetch = FetchType.LAZY)
    private List<MpMedicamentoIndicacionesUnirs> mpMedicamentoIndicacionesUnirsList;

    public MpCodigoUnirs() {
    }

    public MpCodigoUnirs(Integer id) {
        this.id = id;
    }

    public MpCodigoUnirs(Integer id, String codigo, String indicacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.indicacion = indicacion;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
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

    @XmlTransient
    public List<MpMedicamentoIndicacionesUnirs> getMpMedicamentoIndicacionesUnirsList() {
        return mpMedicamentoIndicacionesUnirsList;
    }

    public void setMpMedicamentoIndicacionesUnirsList(List<MpMedicamentoIndicacionesUnirs> mpMedicamentoIndicacionesUnirsList) {
        this.mpMedicamentoIndicacionesUnirsList = mpMedicamentoIndicacionesUnirsList;
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
        if (!(object instanceof MpCodigoUnirs)) {
            return false;
        }
        MpCodigoUnirs other = (MpCodigoUnirs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpCodigoUnirs[ id=" + id + " ]";
    }
    
}
