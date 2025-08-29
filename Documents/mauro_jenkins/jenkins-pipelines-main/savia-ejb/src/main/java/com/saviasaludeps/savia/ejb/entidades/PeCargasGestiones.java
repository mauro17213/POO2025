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
@Table(name = "pe_cargas_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeCargasGestiones.findAll", query = "SELECT p FROM PeCargasGestiones p"),
    @NamedQuery(name = "PeCargasGestiones.findById", query = "SELECT p FROM PeCargasGestiones p WHERE p.id = :id"),
    @NamedQuery(name = "PeCargasGestiones.findByNombre", query = "SELECT p FROM PeCargasGestiones p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PeCargasGestiones.findByRuta", query = "SELECT p FROM PeCargasGestiones p WHERE p.ruta = :ruta"),
    @NamedQuery(name = "PeCargasGestiones.findByArchivo", query = "SELECT p FROM PeCargasGestiones p WHERE p.archivo = :archivo"),
    @NamedQuery(name = "PeCargasGestiones.findByExiste", query = "SELECT p FROM PeCargasGestiones p WHERE p.existe = :existe"),
    @NamedQuery(name = "PeCargasGestiones.findByRespNombre", query = "SELECT p FROM PeCargasGestiones p WHERE p.respNombre = :respNombre"),
    @NamedQuery(name = "PeCargasGestiones.findByRespRuta", query = "SELECT p FROM PeCargasGestiones p WHERE p.respRuta = :respRuta"),
    @NamedQuery(name = "PeCargasGestiones.findByRespArchivo", query = "SELECT p FROM PeCargasGestiones p WHERE p.respArchivo = :respArchivo"),
    @NamedQuery(name = "PeCargasGestiones.findByRespExiste", query = "SELECT p FROM PeCargasGestiones p WHERE p.respExiste = :respExiste"),
    @NamedQuery(name = "PeCargasGestiones.findByEstado", query = "SELECT p FROM PeCargasGestiones p WHERE p.estado = :estado"),
    @NamedQuery(name = "PeCargasGestiones.findByRegistros", query = "SELECT p FROM PeCargasGestiones p WHERE p.registros = :registros"),
    @NamedQuery(name = "PeCargasGestiones.findByExitosos", query = "SELECT p FROM PeCargasGestiones p WHERE p.exitosos = :exitosos"),
    @NamedQuery(name = "PeCargasGestiones.findByFallidos", query = "SELECT p FROM PeCargasGestiones p WHERE p.fallidos = :fallidos"),
    @NamedQuery(name = "PeCargasGestiones.findByDetalle", query = "SELECT p FROM PeCargasGestiones p WHERE p.detalle = :detalle"),
    @NamedQuery(name = "PeCargasGestiones.findByFechaHoraInicio", query = "SELECT p FROM PeCargasGestiones p WHERE p.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "PeCargasGestiones.findByFechaHoraFin", query = "SELECT p FROM PeCargasGestiones p WHERE p.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "PeCargasGestiones.findByUsuarioCrea", query = "SELECT p FROM PeCargasGestiones p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeCargasGestiones.findByTerminalCrea", query = "SELECT p FROM PeCargasGestiones p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeCargasGestiones.findByFechaHoraCrea", query = "SELECT p FROM PeCargasGestiones p WHERE p.fechaHoraCrea = :fechaHoraCrea")})
public class PeCargasGestiones implements Serializable {

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
    @Size(max = 256)
    @Column(name = "resp_nombre")
    private String respNombre;
    @Size(max = 512)
    @Column(name = "resp_ruta")
    private String respRuta;
    @Size(max = 128)
    @Column(name = "resp_archivo")
    private String respArchivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resp_existe")
    private boolean respExiste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros")
    private int registros;
    @Column(name = "exitosos")
    private Integer exitosos;
    @Column(name = "fallidos")
    private Integer fallidos;
    @Size(max = 512)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
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

    public PeCargasGestiones() {
    }

    public PeCargasGestiones(Integer id) {
        this.id = id;
    }

    public PeCargasGestiones(Integer id, String nombre, String ruta, String archivo, boolean existe, boolean respExiste, int estado, int registros, Date fechaHoraInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
        this.respExiste = respExiste;
        this.estado = estado;
        this.registros = registros;
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

    public boolean getRespExiste() {
        return respExiste;
    }

    public void setRespExiste(boolean respExiste) {
        this.respExiste = respExiste;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeCargasGestiones)) {
            return false;
        }
        PeCargasGestiones other = (PeCargasGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeCargasGestiones[ id=" + id + " ]";
    }
    
}
