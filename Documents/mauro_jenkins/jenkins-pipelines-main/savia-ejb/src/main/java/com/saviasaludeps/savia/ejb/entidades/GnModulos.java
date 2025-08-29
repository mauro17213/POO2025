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
@Table(name = "gn_modulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnModulos.findAll", query = "SELECT g FROM GnModulos g"),
    @NamedQuery(name = "GnModulos.findById", query = "SELECT g FROM GnModulos g WHERE g.id = :id"),
    @NamedQuery(name = "GnModulos.findByNombre", query = "SELECT g FROM GnModulos g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnModulos.findByTipo", query = "SELECT g FROM GnModulos g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GnModulos.findByDescripcion", query = "SELECT g FROM GnModulos g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnModulos.findByActivo", query = "SELECT g FROM GnModulos g WHERE g.activo = :activo"),
    @NamedQuery(name = "GnModulos.findByPrivilegios", query = "SELECT g FROM GnModulos g WHERE g.privilegios = :privilegios"),
    @NamedQuery(name = "GnModulos.findByOrden", query = "SELECT g FROM GnModulos g WHERE g.orden = :orden"),
    @NamedQuery(name = "GnModulos.findByUrl", query = "SELECT g FROM GnModulos g WHERE g.url = :url"),
    @NamedQuery(name = "GnModulos.findByIcono", query = "SELECT g FROM GnModulos g WHERE g.icono = :icono"),
    @NamedQuery(name = "GnModulos.findByAmbiente", query = "SELECT g FROM GnModulos g WHERE g.ambiente = :ambiente"),
    @NamedQuery(name = "GnModulos.findByVersion", query = "SELECT g FROM GnModulos g WHERE g.version = :version"),
    @NamedQuery(name = "GnModulos.findByFechaVersion", query = "SELECT g FROM GnModulos g WHERE g.fechaVersion = :fechaVersion"),
    @NamedQuery(name = "GnModulos.findByAdicional1Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional1Nombre = :adicional1Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional2Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional2Nombre = :adicional2Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional3Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional3Nombre = :adicional3Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional4Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional4Nombre = :adicional4Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional5Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional5Nombre = :adicional5Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional6Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional6Nombre = :adicional6Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional7Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional7Nombre = :adicional7Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional8Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional8Nombre = :adicional8Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional9Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional9Nombre = :adicional9Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional10Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional10Nombre = :adicional10Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional11Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional11Nombre = :adicional11Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional12Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional12Nombre = :adicional12Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional13Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional13Nombre = :adicional13Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional14Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional14Nombre = :adicional14Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional15Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional15Nombre = :adicional15Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional16Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional16Nombre = :adicional16Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional17Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional17Nombre = :adicional17Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional18Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional18Nombre = :adicional18Nombre"),
    @NamedQuery(name = "GnModulos.findByAdicional19Nombre", query = "SELECT g FROM GnModulos g WHERE g.adicional19Nombre = :adicional19Nombre"),
    @NamedQuery(name = "GnModulos.findByUsuarioCrea", query = "SELECT g FROM GnModulos g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnModulos.findByTerminalCrea", query = "SELECT g FROM GnModulos g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnModulos.findByFechaHoraCrea", query = "SELECT g FROM GnModulos g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnModulos.findByUsuarioModifica", query = "SELECT g FROM GnModulos g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnModulos.findByTerminalModifica", query = "SELECT g FROM GnModulos g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnModulos.findByFechaHoraModifica", query = "SELECT g FROM GnModulos g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnModulos implements Serializable {

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
    @Size(min = 1, max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "privilegios")
    private String privilegios;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Size(max = 256)
    @Column(name = "url")
    private String url;
    @Size(max = 32)
    @Column(name = "icono")
    private String icono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ambiente")
    private int ambiente;
    @Size(max = 8)
    @Column(name = "version")
    private String version;
    @Column(name = "fecha_version")
    @Temporal(TemporalType.DATE)
    private Date fechaVersion;
    @Size(max = 45)
    @Column(name = "adicional1_nombre")
    private String adicional1Nombre;
    @Size(max = 45)
    @Column(name = "adicional2_nombre")
    private String adicional2Nombre;
    @Size(max = 45)
    @Column(name = "adicional3_nombre")
    private String adicional3Nombre;
    @Size(max = 45)
    @Column(name = "adicional4_nombre")
    private String adicional4Nombre;
    @Size(max = 45)
    @Column(name = "adicional5_nombre")
    private String adicional5Nombre;
    @Size(max = 45)
    @Column(name = "adicional6_nombre")
    private String adicional6Nombre;
    @Size(max = 45)
    @Column(name = "adicional7_nombre")
    private String adicional7Nombre;
    @Size(max = 45)
    @Column(name = "adicional8_nombre")
    private String adicional8Nombre;
    @Size(max = 45)
    @Column(name = "adicional9_nombre")
    private String adicional9Nombre;
    @Size(max = 45)
    @Column(name = "adicional10_nombre")
    private String adicional10Nombre;
    @Size(max = 45)
    @Column(name = "adicional11_nombre")
    private String adicional11Nombre;
    @Size(max = 45)
    @Column(name = "adicional12_nombre")
    private String adicional12Nombre;
    @Size(max = 45)
    @Column(name = "adicional13_nombre")
    private String adicional13Nombre;
    @Size(max = 45)
    @Column(name = "adicional14_nombre")
    private String adicional14Nombre;
    @Size(max = 45)
    @Column(name = "adicional15_nombre")
    private String adicional15Nombre;
    @Size(max = 45)
    @Column(name = "adicional16_nombre")
    private String adicional16Nombre;
    @Size(max = 45)
    @Column(name = "adicional17_nombre")
    private String adicional17Nombre;
    @Size(max = 45)
    @Column(name = "adicional18_nombre")
    private String adicional18Nombre;
    @Size(max = 45)
    @Column(name = "adicional19_nombre")
    private String adicional19Nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnModulosId", fetch = FetchType.LAZY)
    private List<GnModuloManuales> gnModuloManualesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnModulos", fetch = FetchType.LAZY)
    private List<GnPermisos> gnPermisosList;
    @OneToMany(mappedBy = "gnModulosId", fetch = FetchType.LAZY)
    private List<GnModulos> gnModulosList;
    @JoinColumn(name = "gn_modulos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnModulos gnModulosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnModulosId", fetch = FetchType.LAZY)
    private List<GnModuloVersiones> gnModuloVersionesList;

    public GnModulos() {
    }

    public GnModulos(Integer id) {
        this.id = id;
    }

    public GnModulos(Integer id, String nombre, String tipo, boolean activo, String privilegios, int orden, int ambiente, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.activo = activo;
        this.privilegios = privilegios;
        this.orden = orden;
        this.ambiente = ambiente;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(int ambiente) {
        this.ambiente = ambiente;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFechaVersion() {
        return fechaVersion;
    }

    public void setFechaVersion(Date fechaVersion) {
        this.fechaVersion = fechaVersion;
    }

    public String getAdicional1Nombre() {
        return adicional1Nombre;
    }

    public void setAdicional1Nombre(String adicional1Nombre) {
        this.adicional1Nombre = adicional1Nombre;
    }

    public String getAdicional2Nombre() {
        return adicional2Nombre;
    }

    public void setAdicional2Nombre(String adicional2Nombre) {
        this.adicional2Nombre = adicional2Nombre;
    }

    public String getAdicional3Nombre() {
        return adicional3Nombre;
    }

    public void setAdicional3Nombre(String adicional3Nombre) {
        this.adicional3Nombre = adicional3Nombre;
    }

    public String getAdicional4Nombre() {
        return adicional4Nombre;
    }

    public void setAdicional4Nombre(String adicional4Nombre) {
        this.adicional4Nombre = adicional4Nombre;
    }

    public String getAdicional5Nombre() {
        return adicional5Nombre;
    }

    public void setAdicional5Nombre(String adicional5Nombre) {
        this.adicional5Nombre = adicional5Nombre;
    }

    public String getAdicional6Nombre() {
        return adicional6Nombre;
    }

    public void setAdicional6Nombre(String adicional6Nombre) {
        this.adicional6Nombre = adicional6Nombre;
    }

    public String getAdicional7Nombre() {
        return adicional7Nombre;
    }

    public void setAdicional7Nombre(String adicional7Nombre) {
        this.adicional7Nombre = adicional7Nombre;
    }

    public String getAdicional8Nombre() {
        return adicional8Nombre;
    }

    public void setAdicional8Nombre(String adicional8Nombre) {
        this.adicional8Nombre = adicional8Nombre;
    }

    public String getAdicional9Nombre() {
        return adicional9Nombre;
    }

    public void setAdicional9Nombre(String adicional9Nombre) {
        this.adicional9Nombre = adicional9Nombre;
    }

    public String getAdicional10Nombre() {
        return adicional10Nombre;
    }

    public void setAdicional10Nombre(String adicional10Nombre) {
        this.adicional10Nombre = adicional10Nombre;
    }

    public String getAdicional11Nombre() {
        return adicional11Nombre;
    }

    public void setAdicional11Nombre(String adicional11Nombre) {
        this.adicional11Nombre = adicional11Nombre;
    }

    public String getAdicional12Nombre() {
        return adicional12Nombre;
    }

    public void setAdicional12Nombre(String adicional12Nombre) {
        this.adicional12Nombre = adicional12Nombre;
    }

    public String getAdicional13Nombre() {
        return adicional13Nombre;
    }

    public void setAdicional13Nombre(String adicional13Nombre) {
        this.adicional13Nombre = adicional13Nombre;
    }

    public String getAdicional14Nombre() {
        return adicional14Nombre;
    }

    public void setAdicional14Nombre(String adicional14Nombre) {
        this.adicional14Nombre = adicional14Nombre;
    }

    public String getAdicional15Nombre() {
        return adicional15Nombre;
    }

    public void setAdicional15Nombre(String adicional15Nombre) {
        this.adicional15Nombre = adicional15Nombre;
    }

    public String getAdicional16Nombre() {
        return adicional16Nombre;
    }

    public void setAdicional16Nombre(String adicional16Nombre) {
        this.adicional16Nombre = adicional16Nombre;
    }

    public String getAdicional17Nombre() {
        return adicional17Nombre;
    }

    public void setAdicional17Nombre(String adicional17Nombre) {
        this.adicional17Nombre = adicional17Nombre;
    }

    public String getAdicional18Nombre() {
        return adicional18Nombre;
    }

    public void setAdicional18Nombre(String adicional18Nombre) {
        this.adicional18Nombre = adicional18Nombre;
    }

    public String getAdicional19Nombre() {
        return adicional19Nombre;
    }

    public void setAdicional19Nombre(String adicional19Nombre) {
        this.adicional19Nombre = adicional19Nombre;
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

    @XmlTransient
    public List<GnModuloManuales> getGnModuloManualesList() {
        return gnModuloManualesList;
    }

    public void setGnModuloManualesList(List<GnModuloManuales> gnModuloManualesList) {
        this.gnModuloManualesList = gnModuloManualesList;
    }

    @XmlTransient
    public List<GnPermisos> getGnPermisosList() {
        return gnPermisosList;
    }

    public void setGnPermisosList(List<GnPermisos> gnPermisosList) {
        this.gnPermisosList = gnPermisosList;
    }

    @XmlTransient
    public List<GnModulos> getGnModulosList() {
        return gnModulosList;
    }

    public void setGnModulosList(List<GnModulos> gnModulosList) {
        this.gnModulosList = gnModulosList;
    }

    public GnModulos getGnModulosId() {
        return gnModulosId;
    }

    public void setGnModulosId(GnModulos gnModulosId) {
        this.gnModulosId = gnModulosId;
    }

    @XmlTransient
    public List<GnModuloVersiones> getGnModuloVersionesList() {
        return gnModuloVersionesList;
    }

    public void setGnModuloVersionesList(List<GnModuloVersiones> gnModuloVersionesList) {
        this.gnModuloVersionesList = gnModuloVersionesList;
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
        if (!(object instanceof GnModulos)) {
            return false;
        }
        GnModulos other = (GnModulos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnModulos[ id=" + id + " ]";
    }
    
}
