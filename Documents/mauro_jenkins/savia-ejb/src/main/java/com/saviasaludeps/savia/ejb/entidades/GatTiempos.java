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
@Table(name = "gat_tiempos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatTiempos.findAll", query = "SELECT g FROM GatTiempos g"),
    @NamedQuery(name = "GatTiempos.findById", query = "SELECT g FROM GatTiempos g WHERE g.id = :id"),
    @NamedQuery(name = "GatTiempos.findByTipo", query = "SELECT g FROM GatTiempos g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GatTiempos.findByFechaInicio", query = "SELECT g FROM GatTiempos g WHERE g.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "GatTiempos.findByFechaFin", query = "SELECT g FROM GatTiempos g WHERE g.fechaFin = :fechaFin"),
    @NamedQuery(name = "GatTiempos.findByTiempoTotal", query = "SELECT g FROM GatTiempos g WHERE g.tiempoTotal = :tiempoTotal"),
    @NamedQuery(name = "GatTiempos.findByActivo", query = "SELECT g FROM GatTiempos g WHERE g.activo = :activo"),
    @NamedQuery(name = "GatTiempos.findByTiempoTranscurrido", query = "SELECT g FROM GatTiempos g WHERE g.tiempoTranscurrido = :tiempoTranscurrido"),
    @NamedQuery(name = "GatTiempos.findByFechaFinReal", query = "SELECT g FROM GatTiempos g WHERE g.fechaFinReal = :fechaFinReal"),
    @NamedQuery(name = "GatTiempos.findByUsuarioCrea", query = "SELECT g FROM GatTiempos g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatTiempos.findByTerminalCrea", query = "SELECT g FROM GatTiempos g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatTiempos.findByFechaHoraCrea", query = "SELECT g FROM GatTiempos g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatTiempos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_total")
    private int tiempoTotal;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "tiempo_transcurrido")
    private Integer tiempoTranscurrido;
    @Column(name = "fecha_fin_real")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinReal;
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
    @JoinColumn(name = "gat_atenciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GatAtenciones gatAtencionesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public GatTiempos() {
    }

    public GatTiempos(Integer id) {
        this.id = id;
    }

    public GatTiempos(Integer id, int tipo, Date fechaInicio, Date fechaFin, int tiempoTotal, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tiempoTotal = tiempoTotal;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(Integer tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
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

    public GatAtenciones getGatAtencionesId() {
        return gatAtencionesId;
    }

    public void setGatAtencionesId(GatAtenciones gatAtencionesId) {
        this.gatAtencionesId = gatAtencionesId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
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
        if (!(object instanceof GatTiempos)) {
            return false;
        }
        GatTiempos other = (GatTiempos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatTiempos[ id=" + id + " ]";
    }
    
}
