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
@Table(name = "auc_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucCargas.findAll", query = "SELECT a FROM AucCargas a"),
    @NamedQuery(name = "AucCargas.findById", query = "SELECT a FROM AucCargas a WHERE a.id = :id"),
    @NamedQuery(name = "AucCargas.findByEstado", query = "SELECT a FROM AucCargas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AucCargas.findByFechaHoraInicio", query = "SELECT a FROM AucCargas a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AucCargas.findByFechaHoraFin", query = "SELECT a FROM AucCargas a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AucCargas.findByRegistrosTotal", query = "SELECT a FROM AucCargas a WHERE a.registrosTotal = :registrosTotal"),
    @NamedQuery(name = "AucCargas.findByHopitalizados", query = "SELECT a FROM AucCargas a WHERE a.hopitalizados = :hopitalizados"),
    @NamedQuery(name = "AucCargas.findByRegistrosExitosos", query = "SELECT a FROM AucCargas a WHERE a.registrosExitosos = :registrosExitosos"),
    @NamedQuery(name = "AucCargas.findByRegistrosRechazados", query = "SELECT a FROM AucCargas a WHERE a.registrosRechazados = :registrosRechazados"),
    @NamedQuery(name = "AucCargas.findByNombreArchivo", query = "SELECT a FROM AucCargas a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "AucCargas.findByRuta", query = "SELECT a FROM AucCargas a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AucCargas.findByArchivo", query = "SELECT a FROM AucCargas a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AucCargas.findByExiste", query = "SELECT a FROM AucCargas a WHERE a.existe = :existe"),
    @NamedQuery(name = "AucCargas.findByRespNombre", query = "SELECT a FROM AucCargas a WHERE a.respNombre = :respNombre"),
    @NamedQuery(name = "AucCargas.findByRespRuta", query = "SELECT a FROM AucCargas a WHERE a.respRuta = :respRuta"),
    @NamedQuery(name = "AucCargas.findByRespArchivo", query = "SELECT a FROM AucCargas a WHERE a.respArchivo = :respArchivo"),
    @NamedQuery(name = "AucCargas.findByRespExiste", query = "SELECT a FROM AucCargas a WHERE a.respExiste = :respExiste"),
    @NamedQuery(name = "AucCargas.findByTipo", query = "SELECT a FROM AucCargas a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AucCargas.findByUsuarioCrea", query = "SELECT a FROM AucCargas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucCargas.findByTerminalCrea", query = "SELECT a FROM AucCargas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucCargas.findByFechaHoraCrea", query = "SELECT a FROM AucCargas a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AucCargas implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "hopitalizados")
    private short hopitalizados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros_exitosos")
    private int registrosExitosos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros_rechazados")
    private int registrosRechazados;
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
    @Size(min = 1, max = 256)
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
    @Column(name = "resp_existe")
    private Boolean respExiste;
    @Column(name = "tipo")
    private Integer tipo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucCargasId", fetch = FetchType.LAZY)
    private List<AucCargaFallos> aucCargaFallosList;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public AucCargas() {
    }

    public AucCargas(Integer id) {
        this.id = id;
    }

    public AucCargas(Integer id, int estado, Date fechaHoraInicio, int registrosTotal, short hopitalizados, int registrosExitosos, int registrosRechazados, String nombreArchivo, String ruta, String archivo, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registrosTotal = registrosTotal;
        this.hopitalizados = hopitalizados;
        this.registrosExitosos = registrosExitosos;
        this.registrosRechazados = registrosRechazados;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.archivo = archivo;
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

    public short getHopitalizados() {
        return hopitalizados;
    }

    public void setHopitalizados(short hopitalizados) {
        this.hopitalizados = hopitalizados;
    }

    public int getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(int registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public int getRegistrosRechazados() {
        return registrosRechazados;
    }

    public void setRegistrosRechazados(int registrosRechazados) {
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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
    public List<AucCargaFallos> getAucCargaFallosList() {
        return aucCargaFallosList;
    }

    public void setAucCargaFallosList(List<AucCargaFallos> aucCargaFallosList) {
        this.aucCargaFallosList = aucCargaFallosList;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
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
        if (!(object instanceof AucCargas)) {
            return false;
        }
        AucCargas other = (AucCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucCargas[ id=" + id + " ]";
    }
    
}
