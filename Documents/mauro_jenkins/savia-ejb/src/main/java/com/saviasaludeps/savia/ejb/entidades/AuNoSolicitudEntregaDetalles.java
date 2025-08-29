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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "au_no_solicitud_entrega_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findAll", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findById", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByNumeroEntrega", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.numeroEntrega = :numeroEntrega"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByCatidadTotal", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.catidadTotal = :catidadTotal"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByCantidadEntregada", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.cantidadEntregada = :cantidadEntregada"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByFaltantes", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.faltantes = :faltantes"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByFechaInicio", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByFechaFin", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.fechaFin = :fechaFin"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByBorrado", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByClasificacionEntrega", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.clasificacionEntrega = :clasificacionEntrega"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByUsuarioCrea", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByTerminalCrea", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByFechaHoraCrea", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByUsuarioModifica", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByTerminalModifica", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByFechaHoraModifica", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByUsuarioBorra", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByTerminalBorra", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuNoSolicitudEntregaDetalles.findByFechaHoraBorra", query = "SELECT a FROM AuNoSolicitudEntregaDetalles a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuNoSolicitudEntregaDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_entrega")
    private int numeroEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "catidad_total")
    private int catidadTotal;
    @Column(name = "cantidad_entregada")
    private Integer cantidadEntregada;
    @Column(name = "faltantes")
    private Integer faltantes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Column(name = "clasificacion_entrega")
    private Integer clasificacionEntrega;
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
    @OneToMany(mappedBy = "auNoSolicitudEntregaDetallesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudEntregas> auNoSolicitudEntregasList;
    @JoinColumn(name = "au_no_solicitud_items_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuNoSolicitudItems auNoSolicitudItemsId;

    public AuNoSolicitudEntregaDetalles() {
    }

    public AuNoSolicitudEntregaDetalles(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudEntregaDetalles(Integer id, int numeroEntrega, int catidadTotal, Date fechaInicio, Date fechaFin, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numeroEntrega = numeroEntrega;
        this.catidadTotal = catidadTotal;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public int getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(int numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public int getCatidadTotal() {
        return catidadTotal;
    }

    public void setCatidadTotal(int catidadTotal) {
        this.catidadTotal = catidadTotal;
    }

    public Integer getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Integer cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getFaltantes() {
        return faltantes;
    }

    public void setFaltantes(Integer faltantes) {
        this.faltantes = faltantes;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Integer getClasificacionEntrega() {
        return clasificacionEntrega;
    }

    public void setClasificacionEntrega(Integer clasificacionEntrega) {
        this.clasificacionEntrega = clasificacionEntrega;
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

    @XmlTransient
    public List<AuNoSolicitudEntregas> getAuNoSolicitudEntregasList() {
        return auNoSolicitudEntregasList;
    }

    public void setAuNoSolicitudEntregasList(List<AuNoSolicitudEntregas> auNoSolicitudEntregasList) {
        this.auNoSolicitudEntregasList = auNoSolicitudEntregasList;
    }

    public AuNoSolicitudItems getAuNoSolicitudItemsId() {
        return auNoSolicitudItemsId;
    }

    public void setAuNoSolicitudItemsId(AuNoSolicitudItems auNoSolicitudItemsId) {
        this.auNoSolicitudItemsId = auNoSolicitudItemsId;
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
        if (!(object instanceof AuNoSolicitudEntregaDetalles)) {
            return false;
        }
        AuNoSolicitudEntregaDetalles other = (AuNoSolicitudEntregaDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaDetalles[ id=" + id + " ]";
    }
    
}
