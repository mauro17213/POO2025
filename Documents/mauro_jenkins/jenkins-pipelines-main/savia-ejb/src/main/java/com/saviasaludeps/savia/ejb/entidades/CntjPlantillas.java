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
import javax.persistence.Lob;
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
@Table(name = "cntj_plantillas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjPlantillas.findAll", query = "SELECT c FROM CntjPlantillas c"),
    @NamedQuery(name = "CntjPlantillas.findById", query = "SELECT c FROM CntjPlantillas c WHERE c.id = :id"),
    @NamedQuery(name = "CntjPlantillas.findByNombre", query = "SELECT c FROM CntjPlantillas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjPlantillas.findByDescripcion", query = "SELECT c FROM CntjPlantillas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjPlantillas.findByActivo", query = "SELECT c FROM CntjPlantillas c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntjPlantillas.findByVersion", query = "SELECT c FROM CntjPlantillas c WHERE c.version = :version"),
    @NamedQuery(name = "CntjPlantillas.findByUsuarioCrea", query = "SELECT c FROM CntjPlantillas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjPlantillas.findByTerminalCrea", query = "SELECT c FROM CntjPlantillas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjPlantillas.findByFechaHoraCrea", query = "SELECT c FROM CntjPlantillas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjPlantillas.findByUsuarioModifica", query = "SELECT c FROM CntjPlantillas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjPlantillas.findByTerminalModifica", query = "SELECT c FROM CntjPlantillas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjPlantillas.findByFechaHoraModifica", query = "SELECT c FROM CntjPlantillas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjPlantillas implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "estructura")
    private String estructura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "version")
    private String version;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjPlantillasId", fetch = FetchType.LAZY)
    private List<CntjEstadoPlantillas> cntjEstadoPlantillasList;
    @JoinColumn(name = "cntj_proceso_documentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjProcesoDocumentos cntjProcesoDocumentosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjPlantillasId", fetch = FetchType.LAZY)
    private List<CntjPlantillaCampos> cntjPlantillaCamposList;
    @OneToMany(mappedBy = "cntjPlantillasId", fetch = FetchType.LAZY)
    private List<CntjDocumentos> cntjDocumentosList;

    public CntjPlantillas() {
    }

    public CntjPlantillas(Integer id) {
        this.id = id;
    }

    public CntjPlantillas(Integer id, String nombre, String descripcion, boolean activo, String version, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        this.version = version;
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

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
    public List<CntjEstadoPlantillas> getCntjEstadoPlantillasList() {
        return cntjEstadoPlantillasList;
    }

    public void setCntjEstadoPlantillasList(List<CntjEstadoPlantillas> cntjEstadoPlantillasList) {
        this.cntjEstadoPlantillasList = cntjEstadoPlantillasList;
    }

    public CntjProcesoDocumentos getCntjProcesoDocumentosId() {
        return cntjProcesoDocumentosId;
    }

    public void setCntjProcesoDocumentosId(CntjProcesoDocumentos cntjProcesoDocumentosId) {
        this.cntjProcesoDocumentosId = cntjProcesoDocumentosId;
    }

    @XmlTransient
    public List<CntjPlantillaCampos> getCntjPlantillaCamposList() {
        return cntjPlantillaCamposList;
    }

    public void setCntjPlantillaCamposList(List<CntjPlantillaCampos> cntjPlantillaCamposList) {
        this.cntjPlantillaCamposList = cntjPlantillaCamposList;
    }

    @XmlTransient
    public List<CntjDocumentos> getCntjDocumentosList() {
        return cntjDocumentosList;
    }

    public void setCntjDocumentosList(List<CntjDocumentos> cntjDocumentosList) {
        this.cntjDocumentosList = cntjDocumentosList;
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
        if (!(object instanceof CntjPlantillas)) {
            return false;
        }
        CntjPlantillas other = (CntjPlantillas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjPlantillas[ id=" + id + " ]";
    }
    
}
