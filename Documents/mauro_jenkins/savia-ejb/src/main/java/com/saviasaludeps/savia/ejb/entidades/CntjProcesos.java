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
@Table(name = "cntj_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjProcesos.findAll", query = "SELECT c FROM CntjProcesos c"),
    @NamedQuery(name = "CntjProcesos.findById", query = "SELECT c FROM CntjProcesos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjProcesos.findByNombre", query = "SELECT c FROM CntjProcesos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjProcesos.findByDescripcion", query = "SELECT c FROM CntjProcesos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjProcesos.findByActivo", query = "SELECT c FROM CntjProcesos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntjProcesos.findByTipoProceso", query = "SELECT c FROM CntjProcesos c WHERE c.tipoProceso = :tipoProceso"),
    @NamedQuery(name = "CntjProcesos.findByUsuarioCrea", query = "SELECT c FROM CntjProcesos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjProcesos.findByTerminalCrea", query = "SELECT c FROM CntjProcesos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjProcesos.findByFechaHoraCrea", query = "SELECT c FROM CntjProcesos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjProcesos.findByUsuarioModifica", query = "SELECT c FROM CntjProcesos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjProcesos.findByTerminalModifica", query = "SELECT c FROM CntjProcesos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjProcesos.findByFechaHoraModifica", query = "SELECT c FROM CntjProcesos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjProcesos implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "tipo_proceso")
    private Integer tipoProceso;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjProcesosId", fetch = FetchType.LAZY)
    private List<CntjCampos> cntjCamposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjProcesosId", fetch = FetchType.LAZY)
    private List<CntjExpedientes> cntjExpedientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjProcesosId", fetch = FetchType.LAZY)
    private List<CntjEstados> cntjEstadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjProcesosId", fetch = FetchType.LAZY)
    private List<CntjProcesoDocumentos> cntjProcesoDocumentosList;

    public CntjProcesos() {
    }

    public CntjProcesos(Integer id) {
        this.id = id;
    }

    public CntjProcesos(Integer id, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(Integer tipoProceso) {
        this.tipoProceso = tipoProceso;
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
    public List<CntjCampos> getCntjCamposList() {
        return cntjCamposList;
    }

    public void setCntjCamposList(List<CntjCampos> cntjCamposList) {
        this.cntjCamposList = cntjCamposList;
    }

    @XmlTransient
    public List<CntjExpedientes> getCntjExpedientesList() {
        return cntjExpedientesList;
    }

    public void setCntjExpedientesList(List<CntjExpedientes> cntjExpedientesList) {
        this.cntjExpedientesList = cntjExpedientesList;
    }

    @XmlTransient
    public List<CntjEstados> getCntjEstadosList() {
        return cntjEstadosList;
    }

    public void setCntjEstadosList(List<CntjEstados> cntjEstadosList) {
        this.cntjEstadosList = cntjEstadosList;
    }

    @XmlTransient
    public List<CntjProcesoDocumentos> getCntjProcesoDocumentosList() {
        return cntjProcesoDocumentosList;
    }

    public void setCntjProcesoDocumentosList(List<CntjProcesoDocumentos> cntjProcesoDocumentosList) {
        this.cntjProcesoDocumentosList = cntjProcesoDocumentosList;
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
        if (!(object instanceof CntjProcesos)) {
            return false;
        }
        CntjProcesos other = (CntjProcesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjProcesos[ id=" + id + " ]";
    }
    
}
