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
@Table(name = "au_anexo3_carga_anuladas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findAll", query = "SELECT a FROM AuAnexo3CargaAnuladas a"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findById", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByEstado", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByNombre", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByRuta", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByArchivo", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByExiste", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.existe = :existe"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByRegistros", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.registros = :registros"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByExitosos", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.exitosos = :exitosos"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByFallidos", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.fallidos = :fallidos"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByFechaHoraInicio", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByFechaHoraFin", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByTerminalCrea", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3CargaAnuladas.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3CargaAnuladas a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo3CargaAnuladas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
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
    @OneToMany(mappedBy = "auAnexo3CargaAnuladasId", fetch = FetchType.LAZY)
    private List<AuAnexos3> auAnexos3List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3CargaAnuladasId", fetch = FetchType.LAZY)
    private List<AuAnexo3CargaAnuladaSucesos> auAnexo3CargaAnuladaSucesosList;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public AuAnexo3CargaAnuladas() {
    }

    public AuAnexo3CargaAnuladas(Integer id) {
        this.id = id;
    }

    public AuAnexo3CargaAnuladas(Integer id, int estado, String nombre, String ruta, String archivo, Date fechaHoraInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    @XmlTransient
    public List<AuAnexos3> getAuAnexos3List() {
        return auAnexos3List;
    }

    public void setAuAnexos3List(List<AuAnexos3> auAnexos3List) {
        this.auAnexos3List = auAnexos3List;
    }

    @XmlTransient
    public List<AuAnexo3CargaAnuladaSucesos> getAuAnexo3CargaAnuladaSucesosList() {
        return auAnexo3CargaAnuladaSucesosList;
    }

    public void setAuAnexo3CargaAnuladaSucesosList(List<AuAnexo3CargaAnuladaSucesos> auAnexo3CargaAnuladaSucesosList) {
        this.auAnexo3CargaAnuladaSucesosList = auAnexo3CargaAnuladaSucesosList;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
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
        if (!(object instanceof AuAnexo3CargaAnuladas)) {
            return false;
        }
        AuAnexo3CargaAnuladas other = (AuAnexo3CargaAnuladas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3CargaAnuladas[ id=" + id + " ]";
    }
    
}
