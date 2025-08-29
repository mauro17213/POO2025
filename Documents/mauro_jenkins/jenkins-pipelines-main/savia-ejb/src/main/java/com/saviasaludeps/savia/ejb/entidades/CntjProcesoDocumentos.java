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
@Table(name = "cntj_proceso_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjProcesoDocumentos.findAll", query = "SELECT c FROM CntjProcesoDocumentos c"),
    @NamedQuery(name = "CntjProcesoDocumentos.findById", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByNombre", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByDescripcion", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByActivo", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByEtapaContratacion", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.etapaContratacion = :etapaContratacion"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByTipoDocumento", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByUsuarioCrea", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByTerminalCrea", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByFechaHoraCrea", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByUsuarioModifica", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByTerminalModifica", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjProcesoDocumentos.findByFechaHoraModifica", query = "SELECT c FROM CntjProcesoDocumentos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjProcesoDocumentos implements Serializable {

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
    @Size(max = 256)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etapa_contratacion")
    private int etapaContratacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_documento")
    private int tipoDocumento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjProcesoDocumentosId", fetch = FetchType.LAZY)
    private List<CntjEstadoProcesoDocumentos> cntjEstadoProcesoDocumentosList;
    @OneToMany(mappedBy = "cntjProcesoDocumentosId", fetch = FetchType.LAZY)
    private List<CntjPlantillas> cntjPlantillasList;
    @JoinColumn(name = "cntj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjProcesos cntjProcesosId;

    public CntjProcesoDocumentos() {
    }

    public CntjProcesoDocumentos(Integer id) {
        this.id = id;
    }

    public CntjProcesoDocumentos(Integer id, String nombre, int activo, int etapaContratacion, int tipoDocumento, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.etapaContratacion = etapaContratacion;
        this.tipoDocumento = tipoDocumento;
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

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getEtapaContratacion() {
        return etapaContratacion;
    }

    public void setEtapaContratacion(int etapaContratacion) {
        this.etapaContratacion = etapaContratacion;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
    public List<CntjEstadoProcesoDocumentos> getCntjEstadoProcesoDocumentosList() {
        return cntjEstadoProcesoDocumentosList;
    }

    public void setCntjEstadoProcesoDocumentosList(List<CntjEstadoProcesoDocumentos> cntjEstadoProcesoDocumentosList) {
        this.cntjEstadoProcesoDocumentosList = cntjEstadoProcesoDocumentosList;
    }

    @XmlTransient
    public List<CntjPlantillas> getCntjPlantillasList() {
        return cntjPlantillasList;
    }

    public void setCntjPlantillasList(List<CntjPlantillas> cntjPlantillasList) {
        this.cntjPlantillasList = cntjPlantillasList;
    }

    public CntjProcesos getCntjProcesosId() {
        return cntjProcesosId;
    }

    public void setCntjProcesosId(CntjProcesos cntjProcesosId) {
        this.cntjProcesosId = cntjProcesosId;
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
        if (!(object instanceof CntjProcesoDocumentos)) {
            return false;
        }
        CntjProcesoDocumentos other = (CntjProcesoDocumentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjProcesoDocumentos[ id=" + id + " ]";
    }
    
}
