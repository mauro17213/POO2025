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
@Table(name = "cntj_lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjLineas.findAll", query = "SELECT c FROM CntjLineas c"),
    @NamedQuery(name = "CntjLineas.findById", query = "SELECT c FROM CntjLineas c WHERE c.id = :id"),
    @NamedQuery(name = "CntjLineas.findByTipo", query = "SELECT c FROM CntjLineas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntjLineas.findByEstado", query = "SELECT c FROM CntjLineas c WHERE c.estado = :estado"),
    @NamedQuery(name = "CntjLineas.findByDescripcion", query = "SELECT c FROM CntjLineas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjLineas.findByArea", query = "SELECT c FROM CntjLineas c WHERE c.area = :area"),
    @NamedQuery(name = "CntjLineas.findByObservaciones", query = "SELECT c FROM CntjLineas c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CntjLineas.findByUsuarioCrea", query = "SELECT c FROM CntjLineas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjLineas.findByTerminalCrea", query = "SELECT c FROM CntjLineas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjLineas.findByFechaHoraCrea", query = "SELECT c FROM CntjLineas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjLineas.findByUsuarioModifica", query = "SELECT c FROM CntjLineas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjLineas.findByTerminalModifica", query = "SELECT c FROM CntjLineas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjLineas.findByFechaHoraModifica", query = "SELECT c FROM CntjLineas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjLineas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "area")
    private String area;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
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
    @OneToMany(mappedBy = "cntjLineasId", fetch = FetchType.LAZY)
    private List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
    @JoinColumn(name = "cntj_comites_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjComites cntjComitesId;
    @JoinColumn(name = "cntj_expedientes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjExpedientes cntjExpedientesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjLineasId", fetch = FetchType.LAZY)
    private List<CntjLineaAdjuntos> cntjLineaAdjuntosList;

    public CntjLineas() {
    }

    public CntjLineas(Integer id) {
        this.id = id;
    }

    public CntjLineas(Integer id, short tipo, short estado, String descripcion, String area, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.area = area;
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

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
    public List<CntjEstadoEjecuciones> getCntjEstadoEjecucionesList() {
        return cntjEstadoEjecucionesList;
    }

    public void setCntjEstadoEjecucionesList(List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList) {
        this.cntjEstadoEjecucionesList = cntjEstadoEjecucionesList;
    }

    public CntjComites getCntjComitesId() {
        return cntjComitesId;
    }

    public void setCntjComitesId(CntjComites cntjComitesId) {
        this.cntjComitesId = cntjComitesId;
    }

    public CntjExpedientes getCntjExpedientesId() {
        return cntjExpedientesId;
    }

    public void setCntjExpedientesId(CntjExpedientes cntjExpedientesId) {
        this.cntjExpedientesId = cntjExpedientesId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    @XmlTransient
    public List<CntjLineaAdjuntos> getCntjLineaAdjuntosList() {
        return cntjLineaAdjuntosList;
    }

    public void setCntjLineaAdjuntosList(List<CntjLineaAdjuntos> cntjLineaAdjuntosList) {
        this.cntjLineaAdjuntosList = cntjLineaAdjuntosList;
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
        if (!(object instanceof CntjLineas)) {
            return false;
        }
        CntjLineas other = (CntjLineas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjLineas[ id=" + id + " ]";
    }
    
}
