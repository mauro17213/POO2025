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
@Table(name = "cnt_prestador_union_temporal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntPrestadorUnionTemporal.findAll", query = "SELECT c FROM CntPrestadorUnionTemporal c"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findById", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.id = :id"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByBorrado", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.borrado = :borrado"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByUsuarioCrea", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByTerminalCrea", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByFechaHoraCrea", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByUsuarioModifica", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByTerminalModifica", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByFechaHoraModifica", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByUsuarioBorra", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByTerminalBorra", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "CntPrestadorUnionTemporal.findByFechaHoraBorra", query = "SELECT c FROM CntPrestadorUnionTemporal c WHERE c.fechaHoraBorra = :fechaHoraBorra")})
public class CntPrestadorUnionTemporal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "borrado")
    private Boolean borrado;
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
    @JoinColumn(name = "cnt_prestador_union_temporal_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadorUnionTemporalId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;

    public CntPrestadorUnionTemporal() {
    }

    public CntPrestadorUnionTemporal(Integer id) {
        this.id = id;
    }

    public CntPrestadorUnionTemporal(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
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

    public CntPrestadores getCntPrestadorUnionTemporalId() {
        return cntPrestadorUnionTemporalId;
    }

    public void setCntPrestadorUnionTemporalId(CntPrestadores cntPrestadorUnionTemporalId) {
        this.cntPrestadorUnionTemporalId = cntPrestadorUnionTemporalId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
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
        if (!(object instanceof CntPrestadorUnionTemporal)) {
            return false;
        }
        CntPrestadorUnionTemporal other = (CntPrestadorUnionTemporal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntPrestadorUnionTemporal[ id=" + id + " ]";
    }
    
}
