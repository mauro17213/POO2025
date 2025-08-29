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
@Table(name = "auc_carga_cierres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucCargaCierres.findAll", query = "SELECT a FROM AucCargaCierres a"),
    @NamedQuery(name = "AucCargaCierres.findById", query = "SELECT a FROM AucCargaCierres a WHERE a.id = :id"),
    @NamedQuery(name = "AucCargaCierres.findByEstado", query = "SELECT a FROM AucCargaCierres a WHERE a.estado = :estado"),
    @NamedQuery(name = "AucCargaCierres.findByFechaHoraInicio", query = "SELECT a FROM AucCargaCierres a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AucCargaCierres.findByFechaHoraFin", query = "SELECT a FROM AucCargaCierres a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AucCargaCierres.findByRegistrosTotal", query = "SELECT a FROM AucCargaCierres a WHERE a.registrosTotal = :registrosTotal"),
    @NamedQuery(name = "AucCargaCierres.findByRegistrosExitosos", query = "SELECT a FROM AucCargaCierres a WHERE a.registrosExitosos = :registrosExitosos"),
    @NamedQuery(name = "AucCargaCierres.findByRegistrosRechazados", query = "SELECT a FROM AucCargaCierres a WHERE a.registrosRechazados = :registrosRechazados"),
    @NamedQuery(name = "AucCargaCierres.findByNombreArchivo", query = "SELECT a FROM AucCargaCierres a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "AucCargaCierres.findByRuta", query = "SELECT a FROM AucCargaCierres a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AucCargaCierres.findByArchivo", query = "SELECT a FROM AucCargaCierres a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AucCargaCierres.findByExiste", query = "SELECT a FROM AucCargaCierres a WHERE a.existe = :existe"),
    @NamedQuery(name = "AucCargaCierres.findByUsuarioCrea", query = "SELECT a FROM AucCargaCierres a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucCargaCierres.findByTerminalCrear", query = "SELECT a FROM AucCargaCierres a WHERE a.terminalCrear = :terminalCrear"),
    @NamedQuery(name = "AucCargaCierres.findByFechaHoraCrea", query = "SELECT a FROM AucCargaCierres a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AucCargaCierres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
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
    private short registrosTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros_exitosos")
    private short registrosExitosos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros_rechazados")
    private short registrosRechazados;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
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
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crear")
    private String terminalCrear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucCargaCierresId", fetch = FetchType.LAZY)
    private List<AucCargaCierreSucesos> aucCargaCierreSucesosList;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public AucCargaCierres() {
    }

    public AucCargaCierres(Integer id) {
        this.id = id;
    }

    public AucCargaCierres(Integer id, short estado, Date fechaHoraInicio, short registrosTotal, short registrosExitosos, short registrosRechazados, String nombreArchivo, String ruta, String archivo, boolean existe, String usuarioCrea, String terminalCrear, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registrosTotal = registrosTotal;
        this.registrosExitosos = registrosExitosos;
        this.registrosRechazados = registrosRechazados;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrear = terminalCrear;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
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

    public short getRegistrosTotal() {
        return registrosTotal;
    }

    public void setRegistrosTotal(short registrosTotal) {
        this.registrosTotal = registrosTotal;
    }

    public short getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(short registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public short getRegistrosRechazados() {
        return registrosRechazados;
    }

    public void setRegistrosRechazados(short registrosRechazados) {
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

    public String getTerminalCrear() {
        return terminalCrear;
    }

    public void setTerminalCrear(String terminalCrear) {
        this.terminalCrear = terminalCrear;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    @XmlTransient
    public List<AucCargaCierreSucesos> getAucCargaCierreSucesosList() {
        return aucCargaCierreSucesosList;
    }

    public void setAucCargaCierreSucesosList(List<AucCargaCierreSucesos> aucCargaCierreSucesosList) {
        this.aucCargaCierreSucesosList = aucCargaCierreSucesosList;
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
        if (!(object instanceof AucCargaCierres)) {
            return false;
        }
        AucCargaCierres other = (AucCargaCierres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucCargaCierres[ id=" + id + " ]";
    }
    
}
