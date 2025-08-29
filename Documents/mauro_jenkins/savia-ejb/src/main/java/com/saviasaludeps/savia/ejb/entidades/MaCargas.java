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
@Table(name = "ma_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaCargas.findAll", query = "SELECT m FROM MaCargas m"),
    @NamedQuery(name = "MaCargas.findById", query = "SELECT m FROM MaCargas m WHERE m.id = :id"),
    @NamedQuery(name = "MaCargas.findByNombre", query = "SELECT m FROM MaCargas m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaCargas.findByRuta", query = "SELECT m FROM MaCargas m WHERE m.ruta = :ruta"),
    @NamedQuery(name = "MaCargas.findByArchivo", query = "SELECT m FROM MaCargas m WHERE m.archivo = :archivo"),
    @NamedQuery(name = "MaCargas.findByExiste", query = "SELECT m FROM MaCargas m WHERE m.existe = :existe"),
    @NamedQuery(name = "MaCargas.findByEstado", query = "SELECT m FROM MaCargas m WHERE m.estado = :estado"),
    @NamedQuery(name = "MaCargas.findByTipo", query = "SELECT m FROM MaCargas m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MaCargas.findByRegistros", query = "SELECT m FROM MaCargas m WHERE m.registros = :registros"),
    @NamedQuery(name = "MaCargas.findByExitosos", query = "SELECT m FROM MaCargas m WHERE m.exitosos = :exitosos"),
    @NamedQuery(name = "MaCargas.findByFallidos", query = "SELECT m FROM MaCargas m WHERE m.fallidos = :fallidos"),
    @NamedQuery(name = "MaCargas.findByDetalle", query = "SELECT m FROM MaCargas m WHERE m.detalle = :detalle"),
    @NamedQuery(name = "MaCargas.findByFechaHoraInicio", query = "SELECT m FROM MaCargas m WHERE m.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "MaCargas.findByFechaHoraFin", query = "SELECT m FROM MaCargas m WHERE m.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "MaCargas.findByRespNombre", query = "SELECT m FROM MaCargas m WHERE m.respNombre = :respNombre"),
    @NamedQuery(name = "MaCargas.findByRespRuta", query = "SELECT m FROM MaCargas m WHERE m.respRuta = :respRuta"),
    @NamedQuery(name = "MaCargas.findByRespArchivo", query = "SELECT m FROM MaCargas m WHERE m.respArchivo = :respArchivo"),
    @NamedQuery(name = "MaCargas.findByRespExiste", query = "SELECT m FROM MaCargas m WHERE m.respExiste = :respExiste"),
    @NamedQuery(name = "MaCargas.findByUsuarioCrea", query = "SELECT m FROM MaCargas m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaCargas.findByTerminalCrea", query = "SELECT m FROM MaCargas m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaCargas.findByFechaHoraCrea", query = "SELECT m FROM MaCargas m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MaCargas implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maCargasId", fetch = FetchType.LAZY)
    private List<MaDetalleCargas> maDetalleCargasList;

    public MaCargas() {
    }

    public MaCargas(Integer id) {
        this.id = id;
    }

    public MaCargas(Integer id, String nombre, String ruta, String archivo, boolean existe, int estado, int tipo, boolean respExiste, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
        this.estado = estado;
        this.tipo = tipo;
        this.respExiste = respExiste;
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
    public List<MaDetalleCargas> getMaDetalleCargasList() {
        return maDetalleCargasList;
    }

    public void setMaDetalleCargasList(List<MaDetalleCargas> maDetalleCargasList) {
        this.maDetalleCargasList = maDetalleCargasList;
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
        if (!(object instanceof MaCargas)) {
            return false;
        }
        MaCargas other = (MaCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaCargas[ id=" + id + " ]";
    }
    
}
