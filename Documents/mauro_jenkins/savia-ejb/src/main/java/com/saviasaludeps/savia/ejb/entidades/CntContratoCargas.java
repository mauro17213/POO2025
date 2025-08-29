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
@Table(name = "cnt_contrato_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoCargas.findAll", query = "SELECT c FROM CntContratoCargas c"),
    @NamedQuery(name = "CntContratoCargas.findById", query = "SELECT c FROM CntContratoCargas c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoCargas.findByEstado", query = "SELECT c FROM CntContratoCargas c WHERE c.estado = :estado"),
    @NamedQuery(name = "CntContratoCargas.findByFechaHoraInicio", query = "SELECT c FROM CntContratoCargas c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CntContratoCargas.findByFechaHoraFin", query = "SELECT c FROM CntContratoCargas c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CntContratoCargas.findByRegistrosTotal", query = "SELECT c FROM CntContratoCargas c WHERE c.registrosTotal = :registrosTotal"),
    @NamedQuery(name = "CntContratoCargas.findByRegistrosExitosos", query = "SELECT c FROM CntContratoCargas c WHERE c.registrosExitosos = :registrosExitosos"),
    @NamedQuery(name = "CntContratoCargas.findByRegistrosRechazados", query = "SELECT c FROM CntContratoCargas c WHERE c.registrosRechazados = :registrosRechazados"),
    @NamedQuery(name = "CntContratoCargas.findByNombreArchivo", query = "SELECT c FROM CntContratoCargas c WHERE c.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "CntContratoCargas.findByTipo", query = "SELECT c FROM CntContratoCargas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntContratoCargas.findByRuta", query = "SELECT c FROM CntContratoCargas c WHERE c.ruta = :ruta"),
    @NamedQuery(name = "CntContratoCargas.findByArchivo", query = "SELECT c FROM CntContratoCargas c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CntContratoCargas.findByExiste", query = "SELECT c FROM CntContratoCargas c WHERE c.existe = :existe"),
    @NamedQuery(name = "CntContratoCargas.findByRespNombre", query = "SELECT c FROM CntContratoCargas c WHERE c.respNombre = :respNombre"),
    @NamedQuery(name = "CntContratoCargas.findByRespRuta", query = "SELECT c FROM CntContratoCargas c WHERE c.respRuta = :respRuta"),
    @NamedQuery(name = "CntContratoCargas.findByRespArchivo", query = "SELECT c FROM CntContratoCargas c WHERE c.respArchivo = :respArchivo"),
    @NamedQuery(name = "CntContratoCargas.findByRespExiste", query = "SELECT c FROM CntContratoCargas c WHERE c.respExiste = :respExiste"),
    @NamedQuery(name = "CntContratoCargas.findByUsuarioCrea", query = "SELECT c FROM CntContratoCargas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoCargas.findByTerminalCrea", query = "SELECT c FROM CntContratoCargas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoCargas.findByFechaHoraCrea", query = "SELECT c FROM CntContratoCargas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntContratoCargas implements Serializable {

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
    @Column(name = "registros_exitosos")
    private Integer registrosExitosos;
    @Column(name = "registros_rechazados")
    private Integer registrosRechazados;
    @Size(max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratoCargasId", fetch = FetchType.LAZY)
    private List<CntContratoCargaSucesos> cntContratoCargaSucesosList;

    public CntContratoCargas() {
    }

    public CntContratoCargas(Integer id) {
        this.id = id;
    }

    public CntContratoCargas(Integer id, int estado, Date fechaHoraInicio, int registrosTotal, int tipo, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registrosTotal = registrosTotal;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
    public List<CntContratoCargaSucesos> getCntContratoCargaSucesosList() {
        return cntContratoCargaSucesosList;
    }

    public void setCntContratoCargaSucesosList(List<CntContratoCargaSucesos> cntContratoCargaSucesosList) {
        this.cntContratoCargaSucesosList = cntContratoCargaSucesosList;
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
        if (!(object instanceof CntContratoCargas)) {
            return false;
        }
        CntContratoCargas other = (CntContratoCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoCargas[ id=" + id + " ]";
    }
    
}
