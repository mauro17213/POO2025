/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "gat_atenciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatAtenciones.findAll", query = "SELECT g FROM GatAtenciones g"),
    @NamedQuery(name = "GatAtenciones.findById", query = "SELECT g FROM GatAtenciones g WHERE g.id = :id"),
    @NamedQuery(name = "GatAtenciones.findByFechaHoraTiquete", query = "SELECT g FROM GatAtenciones g WHERE g.fechaHoraTiquete = :fechaHoraTiquete"),
    @NamedQuery(name = "GatAtenciones.findByFechaHoraInicio", query = "SELECT g FROM GatAtenciones g WHERE g.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "GatAtenciones.findByFechaHoraCancela", query = "SELECT g FROM GatAtenciones g WHERE g.fechaHoraCancela = :fechaHoraCancela"),
    @NamedQuery(name = "GatAtenciones.findByFechaHoraFin", query = "SELECT g FROM GatAtenciones g WHERE g.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "GatAtenciones.findByFechaHoraCalificacion", query = "SELECT g FROM GatAtenciones g WHERE g.fechaHoraCalificacion = :fechaHoraCalificacion"),
    @NamedQuery(name = "GatAtenciones.findByEstado", query = "SELECT g FROM GatAtenciones g WHERE g.estado = :estado"),
    @NamedQuery(name = "GatAtenciones.findByUsuarioCrea", query = "SELECT g FROM GatAtenciones g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatAtenciones.findByTerminalCrea", query = "SELECT g FROM GatAtenciones g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatAtenciones.findByFechaHoraCrea", query = "SELECT g FROM GatAtenciones g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatAtenciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_hora_tiquete")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraTiquete;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_cancela")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCancela;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "fecha_hora_calificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCalificacion;
    @Column(name = "estado")
    private Integer estado;
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
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @JoinColumn(name = "gat_sede_funcionarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GatSedeFuncionarios gatSedeFuncionariosId;
    @JoinColumn(name = "gat_taquillas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GatSedeTaquillas gatTaquillasId;
    @JoinColumn(name = "gat_tiquetes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GatTiquetes gatTiquetesId;
    @JoinColumn(name = "gat_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GatUsuarios gatUsuariosId;
    @JoinColumn(name = "gn_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnSedes gnSedesId;
    @OneToMany(mappedBy = "gatAtencionesId", fetch = FetchType.LAZY)
    private List<GatTiempos> gatTiemposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gatAtencionesId", fetch = FetchType.LAZY)
    private List<GatAtencionComentarios> gatAtencionComentariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gatAtencionesId", fetch = FetchType.LAZY)
    private List<GatAtencionHistoricos> gatAtencionHistoricosList;

    public GatAtenciones() {
    }

    public GatAtenciones(Integer id) {
        this.id = id;
    }

    public GatAtenciones(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public Date getFechaHoraTiquete() {
        return fechaHoraTiquete;
    }

    public void setFechaHoraTiquete(Date fechaHoraTiquete) {
        this.fechaHoraTiquete = fechaHoraTiquete;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraCancela() {
        return fechaHoraCancela;
    }

    public void setFechaHoraCancela(Date fechaHoraCancela) {
        this.fechaHoraCancela = fechaHoraCancela;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Date getFechaHoraCalificacion() {
        return fechaHoraCalificacion;
    }

    public void setFechaHoraCalificacion(Date fechaHoraCalificacion) {
        this.fechaHoraCalificacion = fechaHoraCalificacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    public GatSedeFuncionarios getGatSedeFuncionariosId() {
        return gatSedeFuncionariosId;
    }

    public void setGatSedeFuncionariosId(GatSedeFuncionarios gatSedeFuncionariosId) {
        this.gatSedeFuncionariosId = gatSedeFuncionariosId;
    }

    public GatSedeTaquillas getGatTaquillasId() {
        return gatTaquillasId;
    }

    public void setGatTaquillasId(GatSedeTaquillas gatTaquillasId) {
        this.gatTaquillasId = gatTaquillasId;
    }

    public GatTiquetes getGatTiquetesId() {
        return gatTiquetesId;
    }

    public void setGatTiquetesId(GatTiquetes gatTiquetesId) {
        this.gatTiquetesId = gatTiquetesId;
    }

    public GatUsuarios getGatUsuariosId() {
        return gatUsuariosId;
    }

    public void setGatUsuariosId(GatUsuarios gatUsuariosId) {
        this.gatUsuariosId = gatUsuariosId;
    }

    public GnSedes getGnSedesId() {
        return gnSedesId;
    }

    public void setGnSedesId(GnSedes gnSedesId) {
        this.gnSedesId = gnSedesId;
    }

    @XmlTransient
    public List<GatTiempos> getGatTiemposList() {
        return gatTiemposList;
    }

    public void setGatTiemposList(List<GatTiempos> gatTiemposList) {
        this.gatTiemposList = gatTiemposList;
    }

    @XmlTransient
    public List<GatAtencionComentarios> getGatAtencionComentariosList() {
        return gatAtencionComentariosList;
    }

    public void setGatAtencionComentariosList(List<GatAtencionComentarios> gatAtencionComentariosList) {
        this.gatAtencionComentariosList = gatAtencionComentariosList;
    }

    @XmlTransient
    public List<GatAtencionHistoricos> getGatAtencionHistoricosList() {
        return gatAtencionHistoricosList;
    }

    public void setGatAtencionHistoricosList(List<GatAtencionHistoricos> gatAtencionHistoricosList) {
        this.gatAtencionHistoricosList = gatAtencionHistoricosList;
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
        if (!(object instanceof GatAtenciones)) {
            return false;
        }
        GatAtenciones other = (GatAtenciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatAtenciones[ id=" + id + " ]";
    }
    
}
