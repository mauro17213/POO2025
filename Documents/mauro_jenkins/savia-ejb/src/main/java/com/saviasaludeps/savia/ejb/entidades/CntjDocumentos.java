/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "cntj_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjDocumentos.findAll", query = "SELECT c FROM CntjDocumentos c"),
    @NamedQuery(name = "CntjDocumentos.findById", query = "SELECT c FROM CntjDocumentos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjDocumentos.findByNombre", query = "SELECT c FROM CntjDocumentos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjDocumentos.findByDescripcion", query = "SELECT c FROM CntjDocumentos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjDocumentos.findByTipo", query = "SELECT c FROM CntjDocumentos c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntjDocumentos.findByDocumentoNombre", query = "SELECT c FROM CntjDocumentos c WHERE c.documentoNombre = :documentoNombre"),
    @NamedQuery(name = "CntjDocumentos.findByDocumentoRuta", query = "SELECT c FROM CntjDocumentos c WHERE c.documentoRuta = :documentoRuta"),
    @NamedQuery(name = "CntjDocumentos.findByDocumentoArchivo", query = "SELECT c FROM CntjDocumentos c WHERE c.documentoArchivo = :documentoArchivo"),
    @NamedQuery(name = "CntjDocumentos.findByDocumentoExiste", query = "SELECT c FROM CntjDocumentos c WHERE c.documentoExiste = :documentoExiste"),
    @NamedQuery(name = "CntjDocumentos.findByEtapaContratacion", query = "SELECT c FROM CntjDocumentos c WHERE c.etapaContratacion = :etapaContratacion"),
    @NamedQuery(name = "CntjDocumentos.findByUsuarioCrea", query = "SELECT c FROM CntjDocumentos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjDocumentos.findByTerminalCrea", query = "SELECT c FROM CntjDocumentos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjDocumentos.findByFechaHoraCrea", query = "SELECT c FROM CntjDocumentos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjDocumentos.findByUsuarioModifica", query = "SELECT c FROM CntjDocumentos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjDocumentos.findByTerminalModifica", query = "SELECT c FROM CntjDocumentos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjDocumentos.findByFechaHoraModifica", query = "SELECT c FROM CntjDocumentos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjDocumentos implements Serializable {

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
    @Column(name = "tipo")
    private short tipo;
    @Size(max = 64)
    @Column(name = "documento_nombre")
    private String documentoNombre;
    @Size(max = 512)
    @Column(name = "documento_ruta")
    private String documentoRuta;
    @Size(max = 64)
    @Column(name = "documento_archivo")
    private String documentoArchivo;
    @Column(name = "documento_existe")
    private Boolean documentoExiste;
    @Column(name = "etapa_contratacion")
    private Integer etapaContratacion;
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
    @OneToMany(mappedBy = "cntjDocumentosId", fetch = FetchType.LAZY)
    private List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
    @JoinColumn(name = "cntj_contratos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjContratos cntjContratosId;
    @JoinColumn(name = "cntj_expedientes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjExpedientes cntjExpedientesId;
    @JoinColumn(name = "cntj_plantillas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjPlantillas cntjPlantillasId;

    public CntjDocumentos() {
    }

    public CntjDocumentos(Integer id) {
        this.id = id;
    }

    public CntjDocumentos(Integer id, String nombre, short tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
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

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getDocumentoNombre() {
        return documentoNombre;
    }

    public void setDocumentoNombre(String documentoNombre) {
        this.documentoNombre = documentoNombre;
    }

    public String getDocumentoRuta() {
        return documentoRuta;
    }

    public void setDocumentoRuta(String documentoRuta) {
        this.documentoRuta = documentoRuta;
    }

    public String getDocumentoArchivo() {
        return documentoArchivo;
    }

    public void setDocumentoArchivo(String documentoArchivo) {
        this.documentoArchivo = documentoArchivo;
    }

    public Boolean getDocumentoExiste() {
        return documentoExiste;
    }

    public void setDocumentoExiste(Boolean documentoExiste) {
        this.documentoExiste = documentoExiste;
    }

    public Integer getEtapaContratacion() {
        return etapaContratacion;
    }

    public void setEtapaContratacion(Integer etapaContratacion) {
        this.etapaContratacion = etapaContratacion;
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

    public CntjContratos getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContratos cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
    }

    public CntjExpedientes getCntjExpedientesId() {
        return cntjExpedientesId;
    }

    public void setCntjExpedientesId(CntjExpedientes cntjExpedientesId) {
        this.cntjExpedientesId = cntjExpedientesId;
    }

    public CntjPlantillas getCntjPlantillasId() {
        return cntjPlantillasId;
    }

    public void setCntjPlantillasId(CntjPlantillas cntjPlantillasId) {
        this.cntjPlantillasId = cntjPlantillasId;
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
        if (!(object instanceof CntjDocumentos)) {
            return false;
        }
        CntjDocumentos other = (CntjDocumentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjDocumentos[ id=" + id + " ]";
    }
    
}
