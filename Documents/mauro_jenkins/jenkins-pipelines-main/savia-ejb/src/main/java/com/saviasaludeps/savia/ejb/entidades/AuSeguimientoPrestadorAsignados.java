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
@Table(name = "au_seguimiento_prestador_asignados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findAll", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findById", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.id = :id"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByEstado", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByBorrado", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByUsuarioCrea", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByTerminalCrea", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByFechaHoraCrea", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByUsuarioModifica", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByTerminalModifica", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByFechaHoraModifica", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByUsuarioBorra", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByTerminalBorra", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuSeguimientoPrestadorAsignados.findByFechaHoraBorra", query = "SELECT a FROM AuSeguimientoPrestadorAsignados a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuSeguimientoPrestadorAsignados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
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
    @JoinColumn(name = "au_seguimientos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuSeguimientos auSeguimientosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;

    public AuSeguimientoPrestadorAsignados() {
    }

    public AuSeguimientoPrestadorAsignados(Integer id) {
        this.id = id;
    }

    public AuSeguimientoPrestadorAsignados(Integer id, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public AuSeguimientos getAuSeguimientosId() {
        return auSeguimientosId;
    }

    public void setAuSeguimientosId(AuSeguimientos auSeguimientosId) {
        this.auSeguimientosId = auSeguimientosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
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
        if (!(object instanceof AuSeguimientoPrestadorAsignados)) {
            return false;
        }
        AuSeguimientoPrestadorAsignados other = (AuSeguimientoPrestadorAsignados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuSeguimientoPrestadorAsignados[ id=" + id + " ]";
    }
    
}
