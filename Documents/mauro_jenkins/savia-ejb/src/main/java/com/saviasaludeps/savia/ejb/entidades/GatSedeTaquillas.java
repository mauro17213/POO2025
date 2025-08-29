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
@Table(name = "gat_sede_taquillas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatSedeTaquillas.findAll", query = "SELECT g FROM GatSedeTaquillas g"),
    @NamedQuery(name = "GatSedeTaquillas.findById", query = "SELECT g FROM GatSedeTaquillas g WHERE g.id = :id"),
    @NamedQuery(name = "GatSedeTaquillas.findByNombre", query = "SELECT g FROM GatSedeTaquillas g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GatSedeTaquillas.findByActivo", query = "SELECT g FROM GatSedeTaquillas g WHERE g.activo = :activo"),
    @NamedQuery(name = "GatSedeTaquillas.findByUsuarioCrea", query = "SELECT g FROM GatSedeTaquillas g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatSedeTaquillas.findByTerminalCrea", query = "SELECT g FROM GatSedeTaquillas g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatSedeTaquillas.findByFechaHoraCrea", query = "SELECT g FROM GatSedeTaquillas g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GatSedeTaquillas.findByUsuarioModifica", query = "SELECT g FROM GatSedeTaquillas g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GatSedeTaquillas.findByTerminalModifica", query = "SELECT g FROM GatSedeTaquillas g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GatSedeTaquillas.findByFechaHoraModifica", query = "SELECT g FROM GatSedeTaquillas g WHERE g.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "GatSedeTaquillas.findByDisponible", query = "SELECT g FROM GatSedeTaquillas g WHERE g.disponible = :disponible")})
public class GatSedeTaquillas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "disponible")
    private boolean disponible;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gatTaquillasId", fetch = FetchType.LAZY)
    private List<GatAtenciones> gatAtencionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gatSedeTaquillasId", fetch = FetchType.LAZY)
    private List<GatTaquillaFuncionarios> gatTaquillaFuncionariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gatSedeTaquillasId", fetch = FetchType.LAZY)
    private List<GatTiketeLlamados> gatTiketeLlamadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gatSedeTaquillasId", fetch = FetchType.LAZY)
    private List<GatTaquillaServicios> gatTaquillaServiciosList;
    @JoinColumn(name = "gn_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnSedes gnSedesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public GatSedeTaquillas() {
    }

    public GatSedeTaquillas(Integer id) {
        this.id = id;
    }

    public GatSedeTaquillas(Integer id, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.disponible = disponible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @XmlTransient
    public List<GatAtenciones> getGatAtencionesList() {
        return gatAtencionesList;
    }

    public void setGatAtencionesList(List<GatAtenciones> gatAtencionesList) {
        this.gatAtencionesList = gatAtencionesList;
    }

    @XmlTransient
    public List<GatTaquillaFuncionarios> getGatTaquillaFuncionariosList() {
        return gatTaquillaFuncionariosList;
    }

    public void setGatTaquillaFuncionariosList(List<GatTaquillaFuncionarios> gatTaquillaFuncionariosList) {
        this.gatTaquillaFuncionariosList = gatTaquillaFuncionariosList;
    }

    @XmlTransient
    public List<GatTiketeLlamados> getGatTiketeLlamadosList() {
        return gatTiketeLlamadosList;
    }

    public void setGatTiketeLlamadosList(List<GatTiketeLlamados> gatTiketeLlamadosList) {
        this.gatTiketeLlamadosList = gatTiketeLlamadosList;
    }

    @XmlTransient
    public List<GatTaquillaServicios> getGatTaquillaServiciosList() {
        return gatTaquillaServiciosList;
    }

    public void setGatTaquillaServiciosList(List<GatTaquillaServicios> gatTaquillaServiciosList) {
        this.gatTaquillaServiciosList = gatTaquillaServiciosList;
    }

    public GnSedes getGnSedesId() {
        return gnSedesId;
    }

    public void setGnSedesId(GnSedes gnSedesId) {
        this.gnSedesId = gnSedesId;
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
        if (!(object instanceof GatSedeTaquillas)) {
            return false;
        }
        GatSedeTaquillas other = (GatSedeTaquillas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatSedeTaquillas[ id=" + id + " ]";
    }
    
}
