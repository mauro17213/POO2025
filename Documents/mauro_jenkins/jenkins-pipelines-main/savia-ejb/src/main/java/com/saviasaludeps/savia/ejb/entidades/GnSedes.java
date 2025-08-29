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
@Table(name = "gn_sedes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnSedes.findAll", query = "SELECT g FROM GnSedes g"),
    @NamedQuery(name = "GnSedes.findById", query = "SELECT g FROM GnSedes g WHERE g.id = :id"),
    @NamedQuery(name = "GnSedes.findByNombre", query = "SELECT g FROM GnSedes g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnSedes.findByDescripcion", query = "SELECT g FROM GnSedes g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnSedes.findByDireccion", query = "SELECT g FROM GnSedes g WHERE g.direccion = :direccion"),
    @NamedQuery(name = "GnSedes.findByTelefono", query = "SELECT g FROM GnSedes g WHERE g.telefono = :telefono"),
    @NamedQuery(name = "GnSedes.findByActivo", query = "SELECT g FROM GnSedes g WHERE g.activo = :activo"),
    @NamedQuery(name = "GnSedes.findByMaeTipoId", query = "SELECT g FROM GnSedes g WHERE g.maeTipoId = :maeTipoId"),
    @NamedQuery(name = "GnSedes.findByMaeTipoCodigo", query = "SELECT g FROM GnSedes g WHERE g.maeTipoCodigo = :maeTipoCodigo"),
    @NamedQuery(name = "GnSedes.findByMaeTipoValor", query = "SELECT g FROM GnSedes g WHERE g.maeTipoValor = :maeTipoValor"),
    @NamedQuery(name = "GnSedes.findByUsuarioCrea", query = "SELECT g FROM GnSedes g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnSedes.findByTerminalCrea", query = "SELECT g FROM GnSedes g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnSedes.findByFechaHoraCrea", query = "SELECT g FROM GnSedes g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnSedes.findByUsuarioModifica", query = "SELECT g FROM GnSedes g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnSedes.findByTerminalModifica", query = "SELECT g FROM GnSedes g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnSedes.findByFechaHoraModifica", query = "SELECT g FROM GnSedes g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnSedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 256)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 16)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "mae_tipo_id")
    private Integer maeTipoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_codigo")
    private String maeTipoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_valor")
    private String maeTipoValor;
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
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "gn_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<GatAtenciones> gatAtencionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<GatTiquetes> gatTiquetesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<GatPantallas> gatPantallasList;
    @OneToMany(mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<AusCasos> ausCasosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<GatSedeFuncionarios> gatSedeFuncionariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<GatSedeConfiguraciones> gatSedeConfiguracionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<GatSedeTaquillas> gatSedeTaquillasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnSedesId", fetch = FetchType.LAZY)
    private List<GnSedeHorarios> gnSedeHorariosList;

    public GnSedes() {
    }

    public GnSedes(Integer id) {
        this.id = id;
    }

    public GnSedes(Integer id, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(Integer maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
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

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnUbicaciones getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(GnUbicaciones gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    @XmlTransient
    public List<GatAtenciones> getGatAtencionesList() {
        return gatAtencionesList;
    }

    public void setGatAtencionesList(List<GatAtenciones> gatAtencionesList) {
        this.gatAtencionesList = gatAtencionesList;
    }

    @XmlTransient
    public List<GatTiquetes> getGatTiquetesList() {
        return gatTiquetesList;
    }

    public void setGatTiquetesList(List<GatTiquetes> gatTiquetesList) {
        this.gatTiquetesList = gatTiquetesList;
    }

    @XmlTransient
    public List<GatPantallas> getGatPantallasList() {
        return gatPantallasList;
    }

    public void setGatPantallasList(List<GatPantallas> gatPantallasList) {
        this.gatPantallasList = gatPantallasList;
    }

    @XmlTransient
    public List<AusCasos> getAusCasosList() {
        return ausCasosList;
    }

    public void setAusCasosList(List<AusCasos> ausCasosList) {
        this.ausCasosList = ausCasosList;
    }

    @XmlTransient
    public List<GatSedeFuncionarios> getGatSedeFuncionariosList() {
        return gatSedeFuncionariosList;
    }

    public void setGatSedeFuncionariosList(List<GatSedeFuncionarios> gatSedeFuncionariosList) {
        this.gatSedeFuncionariosList = gatSedeFuncionariosList;
    }

    @XmlTransient
    public List<GatSedeConfiguraciones> getGatSedeConfiguracionesList() {
        return gatSedeConfiguracionesList;
    }

    public void setGatSedeConfiguracionesList(List<GatSedeConfiguraciones> gatSedeConfiguracionesList) {
        this.gatSedeConfiguracionesList = gatSedeConfiguracionesList;
    }

    @XmlTransient
    public List<GatSedeTaquillas> getGatSedeTaquillasList() {
        return gatSedeTaquillasList;
    }

    public void setGatSedeTaquillasList(List<GatSedeTaquillas> gatSedeTaquillasList) {
        this.gatSedeTaquillasList = gatSedeTaquillasList;
    }

    @XmlTransient
    public List<GnSedeHorarios> getGnSedeHorariosList() {
        return gnSedeHorariosList;
    }

    public void setGnSedeHorariosList(List<GnSedeHorarios> gnSedeHorariosList) {
        this.gnSedeHorariosList = gnSedeHorariosList;
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
        if (!(object instanceof GnSedes)) {
            return false;
        }
        GnSedes other = (GnSedes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnSedes[ id=" + id + " ]";
    }
    
}
