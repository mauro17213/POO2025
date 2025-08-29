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
@Table(name = "cntj_comites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjComites.findAll", query = "SELECT c FROM CntjComites c"),
    @NamedQuery(name = "CntjComites.findById", query = "SELECT c FROM CntjComites c WHERE c.id = :id"),
    @NamedQuery(name = "CntjComites.findByEstado", query = "SELECT c FROM CntjComites c WHERE c.estado = :estado"),
    @NamedQuery(name = "CntjComites.findByFechaProgramacion", query = "SELECT c FROM CntjComites c WHERE c.fechaProgramacion = :fechaProgramacion"),
    @NamedQuery(name = "CntjComites.findByFechaHoraInicio", query = "SELECT c FROM CntjComites c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CntjComites.findByFechaHoraFin", query = "SELECT c FROM CntjComites c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CntjComites.findByObservaciones", query = "SELECT c FROM CntjComites c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CntjComites.findByConclusiones", query = "SELECT c FROM CntjComites c WHERE c.conclusiones = :conclusiones"),
    @NamedQuery(name = "CntjComites.findByAdjuntoNombre", query = "SELECT c FROM CntjComites c WHERE c.adjuntoNombre = :adjuntoNombre"),
    @NamedQuery(name = "CntjComites.findByAdjuntoArchivo", query = "SELECT c FROM CntjComites c WHERE c.adjuntoArchivo = :adjuntoArchivo"),
    @NamedQuery(name = "CntjComites.findByAdjuntoRuta", query = "SELECT c FROM CntjComites c WHERE c.adjuntoRuta = :adjuntoRuta"),
    @NamedQuery(name = "CntjComites.findByAdjuntoExiste", query = "SELECT c FROM CntjComites c WHERE c.adjuntoExiste = :adjuntoExiste"),
    @NamedQuery(name = "CntjComites.findByUsuarioCrea", query = "SELECT c FROM CntjComites c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjComites.findByTerminalCrea", query = "SELECT c FROM CntjComites c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjComites.findByFechaHoraCrea", query = "SELECT c FROM CntjComites c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjComites.findByUsuarioModifica", query = "SELECT c FROM CntjComites c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjComites.findByTerminalModifica", query = "SELECT c FROM CntjComites c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjComites.findByFechaHoraModifica", query = "SELECT c FROM CntjComites c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjComites implements Serializable {

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
    @Column(name = "fecha_programacion")
    @Temporal(TemporalType.DATE)
    private Date fechaProgramacion;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 1024)
    @Column(name = "conclusiones")
    private String conclusiones;
    @Size(max = 45)
    @Column(name = "adjunto_nombre")
    private String adjuntoNombre;
    @Size(max = 45)
    @Column(name = "adjunto_archivo")
    private String adjuntoArchivo;
    @Size(max = 45)
    @Column(name = "adjunto_ruta")
    private String adjuntoRuta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adjunto_existe")
    private boolean adjuntoExiste;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjComitesId", fetch = FetchType.LAZY)
    private List<CntjLineas> cntjLineasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjComitesId", fetch = FetchType.LAZY)
    private List<CntjComiteAsistentes> cntjComiteAsistentesList;

    public CntjComites() {
    }

    public CntjComites(Integer id) {
        this.id = id;
    }

    public CntjComites(Integer id, short estado, Date fechaProgramacion, boolean adjuntoExiste, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaProgramacion = fechaProgramacion;
        this.adjuntoExiste = adjuntoExiste;
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

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getFechaProgramacion() {
        return fechaProgramacion;
    }

    public void setFechaProgramacion(Date fechaProgramacion) {
        this.fechaProgramacion = fechaProgramacion;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

    public String getAdjuntoNombre() {
        return adjuntoNombre;
    }

    public void setAdjuntoNombre(String adjuntoNombre) {
        this.adjuntoNombre = adjuntoNombre;
    }

    public String getAdjuntoArchivo() {
        return adjuntoArchivo;
    }

    public void setAdjuntoArchivo(String adjuntoArchivo) {
        this.adjuntoArchivo = adjuntoArchivo;
    }

    public String getAdjuntoRuta() {
        return adjuntoRuta;
    }

    public void setAdjuntoRuta(String adjuntoRuta) {
        this.adjuntoRuta = adjuntoRuta;
    }

    public boolean getAdjuntoExiste() {
        return adjuntoExiste;
    }

    public void setAdjuntoExiste(boolean adjuntoExiste) {
        this.adjuntoExiste = adjuntoExiste;
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
    public List<CntjLineas> getCntjLineasList() {
        return cntjLineasList;
    }

    public void setCntjLineasList(List<CntjLineas> cntjLineasList) {
        this.cntjLineasList = cntjLineasList;
    }

    @XmlTransient
    public List<CntjComiteAsistentes> getCntjComiteAsistentesList() {
        return cntjComiteAsistentesList;
    }

    public void setCntjComiteAsistentesList(List<CntjComiteAsistentes> cntjComiteAsistentesList) {
        this.cntjComiteAsistentesList = cntjComiteAsistentesList;
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
        if (!(object instanceof CntjComites)) {
            return false;
        }
        CntjComites other = (CntjComites) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjComites[ id=" + id + " ]";
    }
    
}
