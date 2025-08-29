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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "fin_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FinCargas.findAll", query = "SELECT f FROM FinCargas f"),
    @NamedQuery(name = "FinCargas.findById", query = "SELECT f FROM FinCargas f WHERE f.id = :id"),
    @NamedQuery(name = "FinCargas.findByNombre", query = "SELECT f FROM FinCargas f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FinCargas.findByRuta", query = "SELECT f FROM FinCargas f WHERE f.ruta = :ruta"),
    @NamedQuery(name = "FinCargas.findByArchivo", query = "SELECT f FROM FinCargas f WHERE f.archivo = :archivo"),
    @NamedQuery(name = "FinCargas.findByExiste", query = "SELECT f FROM FinCargas f WHERE f.existe = :existe"),
    @NamedQuery(name = "FinCargas.findByRespNombre", query = "SELECT f FROM FinCargas f WHERE f.respNombre = :respNombre"),
    @NamedQuery(name = "FinCargas.findByRespRuta", query = "SELECT f FROM FinCargas f WHERE f.respRuta = :respRuta"),
    @NamedQuery(name = "FinCargas.findByRespArchivo", query = "SELECT f FROM FinCargas f WHERE f.respArchivo = :respArchivo"),
    @NamedQuery(name = "FinCargas.findByRespExiste", query = "SELECT f FROM FinCargas f WHERE f.respExiste = :respExiste"),
    @NamedQuery(name = "FinCargas.findByEstado", query = "SELECT f FROM FinCargas f WHERE f.estado = :estado"),
    @NamedQuery(name = "FinCargas.findByRegistros", query = "SELECT f FROM FinCargas f WHERE f.registros = :registros"),
    @NamedQuery(name = "FinCargas.findByExitosos", query = "SELECT f FROM FinCargas f WHERE f.exitosos = :exitosos"),
    @NamedQuery(name = "FinCargas.findByFallidos", query = "SELECT f FROM FinCargas f WHERE f.fallidos = :fallidos"),
    @NamedQuery(name = "FinCargas.findByFechaHoraInicio", query = "SELECT f FROM FinCargas f WHERE f.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "FinCargas.findByFechaHoraFin", query = "SELECT f FROM FinCargas f WHERE f.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "FinCargas.findByUsuarioCrea", query = "SELECT f FROM FinCargas f WHERE f.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "FinCargas.findByTerminalCrea", query = "SELECT f FROM FinCargas f WHERE f.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "FinCargas.findByFechaHoraCrea", query = "SELECT f FROM FinCargas f WHERE f.fechaHoraCrea = :fechaHoraCrea")})
public class FinCargas implements Serializable {

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
    @Column(name = "registros")
    private Integer registros;
    @Column(name = "exitosos")
    private Integer exitosos;
    @Column(name = "fallidos")
    private Integer fallidos;
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

    public FinCargas() {
    }

    public FinCargas(Integer id) {
        this.id = id;
    }

    public FinCargas(Integer id, String nombre, String ruta, String archivo, boolean existe, boolean respExiste, int estado, Date fechaHoraInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
        this.respExiste = respExiste;
        this.estado = estado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinCargas)) {
            return false;
        }
        FinCargas other = (FinCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.FinCargas[ id=" + id + " ]";
    }
    
}
