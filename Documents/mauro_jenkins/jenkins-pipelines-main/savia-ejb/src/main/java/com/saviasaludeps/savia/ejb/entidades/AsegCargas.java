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
@Table(name = "aseg_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegCargas.findAll", query = "SELECT a FROM AsegCargas a"),
    @NamedQuery(name = "AsegCargas.findById", query = "SELECT a FROM AsegCargas a WHERE a.id = :id"),
    @NamedQuery(name = "AsegCargas.findByNombre", query = "SELECT a FROM AsegCargas a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AsegCargas.findByRuta", query = "SELECT a FROM AsegCargas a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AsegCargas.findByArchivo", query = "SELECT a FROM AsegCargas a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AsegCargas.findByExiste", query = "SELECT a FROM AsegCargas a WHERE a.existe = :existe"),
    @NamedQuery(name = "AsegCargas.findByRespNombre", query = "SELECT a FROM AsegCargas a WHERE a.respNombre = :respNombre"),
    @NamedQuery(name = "AsegCargas.findByRespRuta", query = "SELECT a FROM AsegCargas a WHERE a.respRuta = :respRuta"),
    @NamedQuery(name = "AsegCargas.findByRespArchivo", query = "SELECT a FROM AsegCargas a WHERE a.respArchivo = :respArchivo"),
    @NamedQuery(name = "AsegCargas.findByRespExiste", query = "SELECT a FROM AsegCargas a WHERE a.respExiste = :respExiste"),
    @NamedQuery(name = "AsegCargas.findByEstado", query = "SELECT a FROM AsegCargas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsegCargas.findByTipo", query = "SELECT a FROM AsegCargas a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AsegCargas.findByRegistros", query = "SELECT a FROM AsegCargas a WHERE a.registros = :registros"),
    @NamedQuery(name = "AsegCargas.findByExitosos", query = "SELECT a FROM AsegCargas a WHERE a.exitosos = :exitosos"),
    @NamedQuery(name = "AsegCargas.findByFallidos", query = "SELECT a FROM AsegCargas a WHERE a.fallidos = :fallidos"),
    @NamedQuery(name = "AsegCargas.findByDetalle", query = "SELECT a FROM AsegCargas a WHERE a.detalle = :detalle"),
    @NamedQuery(name = "AsegCargas.findByFechaHoraInicio", query = "SELECT a FROM AsegCargas a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AsegCargas.findByFechaHoraFin", query = "SELECT a FROM AsegCargas a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AsegCargas.findByUsuarioCrea", query = "SELECT a FROM AsegCargas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegCargas.findByTerminalCrea", query = "SELECT a FROM AsegCargas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegCargas.findByFechaHoraCrea", query = "SELECT a FROM AsegCargas a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AsegCargas implements Serializable {

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
    @Column(name = "tipo")
    private int tipo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegCargasId", fetch = FetchType.LAZY)
    private List<AsegDetalleCargas> asegDetalleCargasList;

    public AsegCargas() {
    }

    public AsegCargas(Integer id) {
        this.id = id;
    }

    public AsegCargas(Integer id, String nombre, String ruta, String archivo, boolean existe, boolean respExiste, int estado, int tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
        this.respExiste = respExiste;
        this.estado = estado;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
    public List<AsegDetalleCargas> getAsegDetalleCargasList() {
        return asegDetalleCargasList;
    }

    public void setAsegDetalleCargasList(List<AsegDetalleCargas> asegDetalleCargasList) {
        this.asegDetalleCargasList = asegDetalleCargasList;
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
        if (!(object instanceof AsegCargas)) {
            return false;
        }
        AsegCargas other = (AsegCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegCargas[ id=" + id + " ]";
    }
    
}
