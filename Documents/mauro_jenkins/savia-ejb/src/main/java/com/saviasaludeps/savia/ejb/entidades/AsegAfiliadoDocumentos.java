/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "aseg_afiliado_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegAfiliadoDocumentos.findAll", query = "SELECT a FROM AsegAfiliadoDocumentos a"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findById", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.id = :id"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByMaeTipoDocumentoId", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByMaeTipoDocumentoValor", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByNumeroDocumento", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByFechaExpedicion", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.fechaExpedicion = :fechaExpedicion"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByUsuarioCrea", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByTerminalCrea", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegAfiliadoDocumentos.findByFechaHoraCrea", query = "SELECT a FROM AsegAfiliadoDocumentos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AsegAfiliadoDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_id")
    private int maeTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Column(name = "fecha_expedicion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicion;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public AsegAfiliadoDocumentos() {
    }

    public AsegAfiliadoDocumentos(Integer id) {
        this.id = id;
    }

    public AsegAfiliadoDocumentos(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
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

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
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
        if (!(object instanceof AsegAfiliadoDocumentos)) {
            return false;
        }
        AsegAfiliadoDocumentos other = (AsegAfiliadoDocumentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoDocumentos[ id=" + id + " ]";
    }
    
}
