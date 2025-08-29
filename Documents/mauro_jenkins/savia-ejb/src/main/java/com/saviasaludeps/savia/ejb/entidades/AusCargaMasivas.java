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
@Table(name = "aus_carga_masivas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusCargaMasivas.findAll", query = "SELECT a FROM AusCargaMasivas a"),
    @NamedQuery(name = "AusCargaMasivas.findById", query = "SELECT a FROM AusCargaMasivas a WHERE a.id = :id"),
    @NamedQuery(name = "AusCargaMasivas.findByCantidadRegistros", query = "SELECT a FROM AusCargaMasivas a WHERE a.cantidadRegistros = :cantidadRegistros"),
    @NamedQuery(name = "AusCargaMasivas.findByEstado", query = "SELECT a FROM AusCargaMasivas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AusCargaMasivas.findByExitosos", query = "SELECT a FROM AusCargaMasivas a WHERE a.exitosos = :exitosos"),
    @NamedQuery(name = "AusCargaMasivas.findByFallidos", query = "SELECT a FROM AusCargaMasivas a WHERE a.fallidos = :fallidos"),
    @NamedQuery(name = "AusCargaMasivas.findByTipo", query = "SELECT a FROM AusCargaMasivas a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AusCargaMasivas.findByNombre", query = "SELECT a FROM AusCargaMasivas a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AusCargaMasivas.findByRuta", query = "SELECT a FROM AusCargaMasivas a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AusCargaMasivas.findByArchivo", query = "SELECT a FROM AusCargaMasivas a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AusCargaMasivas.findByExiste", query = "SELECT a FROM AusCargaMasivas a WHERE a.existe = :existe"),
    @NamedQuery(name = "AusCargaMasivas.findByRespNombre", query = "SELECT a FROM AusCargaMasivas a WHERE a.respNombre = :respNombre"),
    @NamedQuery(name = "AusCargaMasivas.findByRespRuta", query = "SELECT a FROM AusCargaMasivas a WHERE a.respRuta = :respRuta"),
    @NamedQuery(name = "AusCargaMasivas.findByRespArchivo", query = "SELECT a FROM AusCargaMasivas a WHERE a.respArchivo = :respArchivo"),
    @NamedQuery(name = "AusCargaMasivas.findByRespExiste", query = "SELECT a FROM AusCargaMasivas a WHERE a.respExiste = :respExiste"),
    @NamedQuery(name = "AusCargaMasivas.findByFechaHoraInicio", query = "SELECT a FROM AusCargaMasivas a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AusCargaMasivas.findByFechaHoraFin", query = "SELECT a FROM AusCargaMasivas a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AusCargaMasivas.findByObservacion", query = "SELECT a FROM AusCargaMasivas a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AusCargaMasivas.findByUsuarioCrea", query = "SELECT a FROM AusCargaMasivas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusCargaMasivas.findByTerminalCrea", query = "SELECT a FROM AusCargaMasivas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusCargaMasivas.findByFechaHoraCrea", query = "SELECT a FROM AusCargaMasivas a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AusCargaMasivas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cantidad_registros")
    private Integer cantidadRegistros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "exitosos")
    private Integer exitosos;
    @Column(name = "fallidos")
    private Integer fallidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
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
    @Column(name = "existe")
    private Boolean existe;
    @Size(max = 256)
    @Column(name = "resp_nombre")
    private String respNombre;
    @Size(max = 512)
    @Column(name = "resp_ruta")
    private String respRuta;
    @Size(max = 128)
    @Column(name = "resp_archivo")
    private String respArchivo;
    @Column(name = "resp_existe")
    private Boolean respExiste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ausCargaMasivasId", fetch = FetchType.LAZY)
    private List<AusCargaErrores> ausCargaErroresList;
    @OneToMany(mappedBy = "ausCargaMasivasId", fetch = FetchType.LAZY)
    private List<AusCasos> ausCasosList;
    @JoinColumn(name = "gn_empresa_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresaId;

    public AusCargaMasivas() {
    }

    public AusCargaMasivas(Integer id) {
        this.id = id;
    }

    public AusCargaMasivas(Integer id, int estado, int tipo, String nombre, String ruta, String archivo, Date fechaHoraInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.fechaHoraInicio = fechaHoraInicio;
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

    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public Boolean getExiste() {
        return existe;
    }

    public void setExiste(Boolean existe) {
        this.existe = existe;
    }

    public String getRespNombre() {
        return respNombre;
    }

    public void setRespNombre(String respNombre) {
        this.respNombre = respNombre;
    }

    public String getRespRuta() {
        return respRuta;
    }

    public void setRespRuta(String respRuta) {
        this.respRuta = respRuta;
    }

    public String getRespArchivo() {
        return respArchivo;
    }

    public void setRespArchivo(String respArchivo) {
        this.respArchivo = respArchivo;
    }

    public Boolean getRespExiste() {
        return respExiste;
    }

    public void setRespExiste(Boolean respExiste) {
        this.respExiste = respExiste;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    @XmlTransient
    public List<AusCargaErrores> getAusCargaErroresList() {
        return ausCargaErroresList;
    }

    public void setAusCargaErroresList(List<AusCargaErrores> ausCargaErroresList) {
        this.ausCargaErroresList = ausCargaErroresList;
    }

    @XmlTransient
    public List<AusCasos> getAusCasosList() {
        return ausCasosList;
    }

    public void setAusCasosList(List<AusCasos> ausCasosList) {
        this.ausCasosList = ausCasosList;
    }

    public GnEmpresas getGnEmpresaId() {
        return gnEmpresaId;
    }

    public void setGnEmpresaId(GnEmpresas gnEmpresaId) {
        this.gnEmpresaId = gnEmpresaId;
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
        if (!(object instanceof AusCargaMasivas)) {
            return false;
        }
        AusCargaMasivas other = (AusCargaMasivas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusCargaMasivas[ id=" + id + " ]";
    }
    
}
