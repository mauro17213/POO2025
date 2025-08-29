/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "fe_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeDocumentos.findAll", query = "SELECT f FROM FeDocumentos f"),
    @NamedQuery(name = "FeDocumentos.findById", query = "SELECT f FROM FeDocumentos f WHERE f.id = :id"),
    @NamedQuery(name = "FeDocumentos.findByPrestadorNit", query = "SELECT f FROM FeDocumentos f WHERE f.prestadorNit = :prestadorNit"),
    @NamedQuery(name = "FeDocumentos.findByDocumentoTipo", query = "SELECT f FROM FeDocumentos f WHERE f.documentoTipo = :documentoTipo"),
    @NamedQuery(name = "FeDocumentos.findByDocumentoId", query = "SELECT f FROM FeDocumentos f WHERE f.documentoId = :documentoId"),
    @NamedQuery(name = "FeDocumentos.findByDocumentoNumero", query = "SELECT f FROM FeDocumentos f WHERE f.documentoNumero = :documentoNumero"),
    @NamedQuery(name = "FeDocumentos.findByDocumentoValor", query = "SELECT f FROM FeDocumentos f WHERE f.documentoValor = :documentoValor"),
    @NamedQuery(name = "FeDocumentos.findByEstado", query = "SELECT f FROM FeDocumentos f WHERE f.estado = :estado"),
    @NamedQuery(name = "FeDocumentos.findByCodigoUnicoDian", query = "SELECT f FROM FeDocumentos f WHERE f.codigoUnicoDian = :codigoUnicoDian"),
    @NamedQuery(name = "FeDocumentos.findByEstadoDescripcion", query = "SELECT f FROM FeDocumentos f WHERE f.estadoDescripcion = :estadoDescripcion"),
    @NamedQuery(name = "FeDocumentos.findByReferenciaPrestadoroNit", query = "SELECT f FROM FeDocumentos f WHERE f.referenciaPrestadoroNit = :referenciaPrestadoroNit"),
    @NamedQuery(name = "FeDocumentos.findByReferenciaDocumentoTipo", query = "SELECT f FROM FeDocumentos f WHERE f.referenciaDocumentoTipo = :referenciaDocumentoTipo"),
    @NamedQuery(name = "FeDocumentos.findByReferenciaDocumentoId", query = "SELECT f FROM FeDocumentos f WHERE f.referenciaDocumentoId = :referenciaDocumentoId"),
    @NamedQuery(name = "FeDocumentos.findByReferenciaDocumentoNumero", query = "SELECT f FROM FeDocumentos f WHERE f.referenciaDocumentoNumero = :referenciaDocumentoNumero"),
    @NamedQuery(name = "FeDocumentos.findByFechaDocumento", query = "SELECT f FROM FeDocumentos f WHERE f.fechaDocumento = :fechaDocumento"),
    @NamedQuery(name = "FeDocumentos.findByFechaRadicacion", query = "SELECT f FROM FeDocumentos f WHERE f.fechaRadicacion = :fechaRadicacion"),
    @NamedQuery(name = "FeDocumentos.findByFechaHoraCrea", query = "SELECT f FROM FeDocumentos f WHERE f.fechaHoraCrea = :fechaHoraCrea")})
public class FeDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "prestador_nit")
    private String prestadorNit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "documento_tipo")
    private short documentoTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "documento_id")
    private int documentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "documento_numero")
    private String documentoNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "documento_valor")
    private BigDecimal documentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "codigo_unico_dian")
    private String codigoUnicoDian;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "estado_descripcion")
    private String estadoDescripcion;
    @Size(max = 64)
    @Column(name = "referencia_prestadoro_nit")
    private String referenciaPrestadoroNit;
    @Column(name = "referencia_documento_tipo")
    private Short referenciaDocumentoTipo;
    @Column(name = "referencia_documento_id")
    private Integer referenciaDocumentoId;
    @Size(max = 64)
    @Column(name = "referencia_documento_numero")
    private String referenciaDocumentoNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_documento")
    @Temporal(TemporalType.DATE)
    private Date fechaDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_radicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaRadicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @OneToMany(mappedBy = "feDocumentosId", fetch = FetchType.LAZY)
    private List<FeDocumentos> feDocumentosList;
    @JoinColumn(name = "fe_documentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FeDocumentos feDocumentosId;

    public FeDocumentos() {
    }

    public FeDocumentos(Integer id) {
        this.id = id;
    }

    public FeDocumentos(Integer id, String prestadorNit, short documentoTipo, int documentoId, String documentoNumero, BigDecimal documentoValor, String estado, String codigoUnicoDian, String estadoDescripcion, Date fechaDocumento, Date fechaRadicacion, Date fechaHoraCrea) {
        this.id = id;
        this.prestadorNit = prestadorNit;
        this.documentoTipo = documentoTipo;
        this.documentoId = documentoId;
        this.documentoNumero = documentoNumero;
        this.documentoValor = documentoValor;
        this.estado = estado;
        this.codigoUnicoDian = codigoUnicoDian;
        this.estadoDescripcion = estadoDescripcion;
        this.fechaDocumento = fechaDocumento;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrestadorNit() {
        return prestadorNit;
    }

    public void setPrestadorNit(String prestadorNit) {
        this.prestadorNit = prestadorNit;
    }

    public short getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(short documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public BigDecimal getDocumentoValor() {
        return documentoValor;
    }

    public void setDocumentoValor(BigDecimal documentoValor) {
        this.documentoValor = documentoValor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoUnicoDian() {
        return codigoUnicoDian;
    }

    public void setCodigoUnicoDian(String codigoUnicoDian) {
        this.codigoUnicoDian = codigoUnicoDian;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public String getReferenciaPrestadoroNit() {
        return referenciaPrestadoroNit;
    }

    public void setReferenciaPrestadoroNit(String referenciaPrestadoroNit) {
        this.referenciaPrestadoroNit = referenciaPrestadoroNit;
    }

    public Short getReferenciaDocumentoTipo() {
        return referenciaDocumentoTipo;
    }

    public void setReferenciaDocumentoTipo(Short referenciaDocumentoTipo) {
        this.referenciaDocumentoTipo = referenciaDocumentoTipo;
    }

    public Integer getReferenciaDocumentoId() {
        return referenciaDocumentoId;
    }

    public void setReferenciaDocumentoId(Integer referenciaDocumentoId) {
        this.referenciaDocumentoId = referenciaDocumentoId;
    }

    public String getReferenciaDocumentoNumero() {
        return referenciaDocumentoNumero;
    }

    public void setReferenciaDocumentoNumero(String referenciaDocumentoNumero) {
        this.referenciaDocumentoNumero = referenciaDocumentoNumero;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    @XmlTransient
    public List<FeDocumentos> getFeDocumentosList() {
        return feDocumentosList;
    }

    public void setFeDocumentosList(List<FeDocumentos> feDocumentosList) {
        this.feDocumentosList = feDocumentosList;
    }

    public FeDocumentos getFeDocumentosId() {
        return feDocumentosId;
    }

    public void setFeDocumentosId(FeDocumentos feDocumentosId) {
        this.feDocumentosId = feDocumentosId;
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
        if (!(object instanceof FeDocumentos)) {
            return false;
        }
        FeDocumentos other = (FeDocumentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.FeDocumentos[ id=" + id + " ]";
    }
    
}
