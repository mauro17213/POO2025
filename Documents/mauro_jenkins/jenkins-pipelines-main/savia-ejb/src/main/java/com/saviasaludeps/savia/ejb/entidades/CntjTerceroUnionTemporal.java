/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cntj_tercero_union_temporal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjTerceroUnionTemporal.findAll", query = "SELECT c FROM CntjTerceroUnionTemporal c"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findById", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.id = :id"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByMaeTipoDocumentoId", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByMaeTipoDocumentoValor", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByNumeroDocumento", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByRazonSocial", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.razonSocial = :razonSocial"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByBorrado", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.borrado = :borrado"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByNaturalezaJuridica", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.naturalezaJuridica = :naturalezaJuridica"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByPorcentajeParticipacion", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.porcentajeParticipacion = :porcentajeParticipacion"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByUsuarioCrea", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByTerminalCrea", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByFechaHoraCrea", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByUsuarioModifica", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByTerminalModifica", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByFechaHoraModifica", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByUsuarioBorra", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByTerminalBorra", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "CntjTerceroUnionTemporal.findByFechaHoraBorra", query = "SELECT c FROM CntjTerceroUnionTemporal c WHERE c.fechaHoraBorra = :fechaHoraBorra")})
public class CntjTerceroUnionTemporal implements Serializable {

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
    @Size(min = 1, max = 32)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "naturaleza_juridica")
    private int naturalezaJuridica;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_participacion")
    private BigDecimal porcentajeParticipacion;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "cntj_terceros_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjTerceros cntjTercerosId;

    public CntjTerceroUnionTemporal() {
    }

    public CntjTerceroUnionTemporal(Integer id) {
        this.id = id;
    }

    public CntjTerceroUnionTemporal(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String razonSocial, boolean borrado, int naturalezaJuridica, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.razonSocial = razonSocial;
        this.borrado = borrado;
        this.naturalezaJuridica = naturalezaJuridica;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public int getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    public void setNaturalezaJuridica(int naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
    }

    public BigDecimal getPorcentajeParticipacion() {
        return porcentajeParticipacion;
    }

    public void setPorcentajeParticipacion(BigDecimal porcentajeParticipacion) {
        this.porcentajeParticipacion = porcentajeParticipacion;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public CntjTerceros getCntjTercerosId() {
        return cntjTercerosId;
    }

    public void setCntjTercerosId(CntjTerceros cntjTercerosId) {
        this.cntjTercerosId = cntjTercerosId;
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
        if (!(object instanceof CntjTerceroUnionTemporal)) {
            return false;
        }
        CntjTerceroUnionTemporal other = (CntjTerceroUnionTemporal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjTerceroUnionTemporal[ id=" + id + " ]";
    }
    
}
