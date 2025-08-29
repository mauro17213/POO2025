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
@Table(name = "au_anexo3_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3Cargas.findAll", query = "SELECT a FROM AuAnexo3Cargas a"),
    @NamedQuery(name = "AuAnexo3Cargas.findById", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3Cargas.findByEstado", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo3Cargas.findByEstadoObservacion", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.estadoObservacion = :estadoObservacion"),
    @NamedQuery(name = "AuAnexo3Cargas.findByFechaHoraInicio", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AuAnexo3Cargas.findByFechaHoraFin", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AuAnexo3Cargas.findByRegistrosTotal", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.registrosTotal = :registrosTotal"),
    @NamedQuery(name = "AuAnexo3Cargas.findByRegistrosExitosos", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.registrosExitosos = :registrosExitosos"),
    @NamedQuery(name = "AuAnexo3Cargas.findByRegistrosRechazados", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.registrosRechazados = :registrosRechazados"),
    @NamedQuery(name = "AuAnexo3Cargas.findByNombreArchivo", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "AuAnexo3Cargas.findByRuta", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuAnexo3Cargas.findByArchivo", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuAnexo3Cargas.findByExiste", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.existe = :existe"),
    @NamedQuery(name = "AuAnexo3Cargas.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3Cargas.findByTerminalCrea", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3Cargas.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo3Cargas.findByUsuarioGestionEstado", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.usuarioGestionEstado = :usuarioGestionEstado"),
    @NamedQuery(name = "AuAnexo3Cargas.findByTerminalGestionEstado", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.terminalGestionEstado = :terminalGestionEstado"),
    @NamedQuery(name = "AuAnexo3Cargas.findByFechaHoraGestionEstado", query = "SELECT a FROM AuAnexo3Cargas a WHERE a.fechaHoraGestionEstado = :fechaHoraGestionEstado")})
public class AuAnexo3Cargas implements Serializable {

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
    @Size(max = 256)
    @Column(name = "estado_observacion")
    private String estadoObservacion;
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
    @Column(name = "registros_total")
    private int registrosTotal;
    @Column(name = "registros_exitosos")
    private Integer registrosExitosos;
    @Column(name = "registros_rechazados")
    private Integer registrosRechazados;
    @Size(max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 416)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_gestion_estado")
    private String usuarioGestionEstado;
    @Size(max = 16)
    @Column(name = "terminal_gestion_estado")
    private String terminalGestionEstado;
    @Column(name = "fecha_hora_gestion_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraGestionEstado;
    @OneToMany(mappedBy = "auAnexo3CargasId", fetch = FetchType.LAZY)
    private List<AuAnexos3> auAnexos3List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3CargasId", fetch = FetchType.LAZY)
    private List<AuAnexo3CargaDetalles> auAnexo3CargaDetallesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3CargasId", fetch = FetchType.LAZY)
    private List<AuCargaDetallesAnexos3> auCargaDetallesAnexos3List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3CargasId", fetch = FetchType.LAZY)
    private List<AuAnexo3CargaSucesos> auAnexo3CargaSucesosList;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public AuAnexo3Cargas() {
    }

    public AuAnexo3Cargas(Integer id) {
        this.id = id;
    }

    public AuAnexo3Cargas(Integer id, int estado, Date fechaHoraInicio, int registrosTotal, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registrosTotal = registrosTotal;
        this.existe = existe;
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

    public String getEstadoObservacion() {
        return estadoObservacion;
    }

    public void setEstadoObservacion(String estadoObservacion) {
        this.estadoObservacion = estadoObservacion;
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

    public int getRegistrosTotal() {
        return registrosTotal;
    }

    public void setRegistrosTotal(int registrosTotal) {
        this.registrosTotal = registrosTotal;
    }

    public Integer getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(Integer registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public Integer getRegistrosRechazados() {
        return registrosRechazados;
    }

    public void setRegistrosRechazados(Integer registrosRechazados) {
        this.registrosRechazados = registrosRechazados;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public String getUsuarioGestionEstado() {
        return usuarioGestionEstado;
    }

    public void setUsuarioGestionEstado(String usuarioGestionEstado) {
        this.usuarioGestionEstado = usuarioGestionEstado;
    }

    public String getTerminalGestionEstado() {
        return terminalGestionEstado;
    }

    public void setTerminalGestionEstado(String terminalGestionEstado) {
        this.terminalGestionEstado = terminalGestionEstado;
    }

    public Date getFechaHoraGestionEstado() {
        return fechaHoraGestionEstado;
    }

    public void setFechaHoraGestionEstado(Date fechaHoraGestionEstado) {
        this.fechaHoraGestionEstado = fechaHoraGestionEstado;
    }

    @XmlTransient
    public List<AuAnexos3> getAuAnexos3List() {
        return auAnexos3List;
    }

    public void setAuAnexos3List(List<AuAnexos3> auAnexos3List) {
        this.auAnexos3List = auAnexos3List;
    }

    @XmlTransient
    public List<AuAnexo3CargaDetalles> getAuAnexo3CargaDetallesList() {
        return auAnexo3CargaDetallesList;
    }

    public void setAuAnexo3CargaDetallesList(List<AuAnexo3CargaDetalles> auAnexo3CargaDetallesList) {
        this.auAnexo3CargaDetallesList = auAnexo3CargaDetallesList;
    }

    @XmlTransient
    public List<AuCargaDetallesAnexos3> getAuCargaDetallesAnexos3List() {
        return auCargaDetallesAnexos3List;
    }

    public void setAuCargaDetallesAnexos3List(List<AuCargaDetallesAnexos3> auCargaDetallesAnexos3List) {
        this.auCargaDetallesAnexos3List = auCargaDetallesAnexos3List;
    }

    @XmlTransient
    public List<AuAnexo3CargaSucesos> getAuAnexo3CargaSucesosList() {
        return auAnexo3CargaSucesosList;
    }

    public void setAuAnexo3CargaSucesosList(List<AuAnexo3CargaSucesos> auAnexo3CargaSucesosList) {
        this.auAnexo3CargaSucesosList = auAnexo3CargaSucesosList;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
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
        if (!(object instanceof AuAnexo3Cargas)) {
            return false;
        }
        AuAnexo3Cargas other = (AuAnexo3Cargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3Cargas[ id=" + id + " ]";
    }
    
}
