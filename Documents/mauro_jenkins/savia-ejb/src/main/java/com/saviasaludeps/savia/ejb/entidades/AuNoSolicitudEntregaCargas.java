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
@Table(name = "au_no_solicitud_entrega_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findAll", query = "SELECT a FROM AuNoSolicitudEntregaCargas a"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findById", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByNombre", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByRuta", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByArchivo", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByExiste", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.existe = :existe"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByEstado", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByRegistros", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.registros = :registros"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByExitosos", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.exitosos = :exitosos"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByFallidos", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.fallidos = :fallidos"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByDetalle", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.detalle = :detalle"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByFechaHoraInicio", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByFechaHoraFin", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByTipoTecnologia", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByUsuarioCrea", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByTerminalCrea", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargas.findByFechaHoraCrea", query = "SELECT a FROM AuNoSolicitudEntregaCargas a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuNoSolicitudEntregaCargas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "registros")
    private Integer registros;
    @Column(name = "exitosos")
    private Integer exitosos;
    @Column(name = "fallidos")
    private Integer fallidos;
    @Size(max = 512)
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
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
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auNoSolicitudEntregaCargasId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudEntregaCargaSucesos> auNoSolicitudEntregaCargaSucesosList;

    public AuNoSolicitudEntregaCargas() {
    }

    public AuNoSolicitudEntregaCargas(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudEntregaCargas(Integer id, String nombre, String ruta, String archivo, boolean existe, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getRegistros() {
        return registros;
    }

    public void setRegistros(Integer registros) {
        this.registros = registros;
    }

    public Integer getExitosos() {
        return exitosos;
    }

    public void setExitosos(Integer exitosos) {
        this.exitosos = exitosos;
    }

    public Integer getFallidos() {
        return fallidos;
    }

    public void setFallidos(Integer fallidos) {
        this.fallidos = fallidos;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
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

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    @XmlTransient
    public List<AuNoSolicitudEntregaCargaSucesos> getAuNoSolicitudEntregaCargaSucesosList() {
        return auNoSolicitudEntregaCargaSucesosList;
    }

    public void setAuNoSolicitudEntregaCargaSucesosList(List<AuNoSolicitudEntregaCargaSucesos> auNoSolicitudEntregaCargaSucesosList) {
        this.auNoSolicitudEntregaCargaSucesosList = auNoSolicitudEntregaCargaSucesosList;
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
        if (!(object instanceof AuNoSolicitudEntregaCargas)) {
            return false;
        }
        AuNoSolicitudEntregaCargas other = (AuNoSolicitudEntregaCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaCargas[ id=" + id + " ]";
    }
    
}
