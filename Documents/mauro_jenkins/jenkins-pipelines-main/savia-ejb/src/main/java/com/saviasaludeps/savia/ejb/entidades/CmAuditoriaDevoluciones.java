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
@Table(name = "cm_auditoria_devoluciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaDevoluciones.findAll", query = "SELECT c FROM CmAuditoriaDevoluciones c"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findById", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeMotivoDevolucionId", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeMotivoDevolucionId = :maeMotivoDevolucionId"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeMotivoDevolucionCodigo", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeMotivoDevolucionCodigo = :maeMotivoDevolucionCodigo"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeMotivoDevolucionValor", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeMotivoDevolucionValor = :maeMotivoDevolucionValor"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeContratoModalidadId", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeContratoModalidadId = :maeContratoModalidadId"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeContratoModalidadCodigo", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeContratoModalidadCodigo = :maeContratoModalidadCodigo"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeContratoModalidadValor", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeContratoModalidadValor = :maeContratoModalidadValor"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeRegimenId", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeRegimenCodigo", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeRegimenValor", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByNit", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.nit = :nit"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByIps", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.ips = :ips"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByNumeroRadicado", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.numeroRadicado = :numeroRadicado"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByNumeroFacturado", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.numeroFacturado = :numeroFacturado"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByFechaRadicacion", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.fechaRadicacion = :fechaRadicacion"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByFechaDevolucion", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.fechaDevolucion = :fechaDevolucion"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByValorFactura", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.valorFactura = :valorFactura"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeDevolucionMotivoGeneralId", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeDevolucionMotivoGeneralId = :maeDevolucionMotivoGeneralId"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeDevolucionMotivoGeneralCodigo", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeDevolucionMotivoGeneralCodigo = :maeDevolucionMotivoGeneralCodigo"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeDevolucionMotivoGeneralValor", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeDevolucionMotivoGeneralValor = :maeDevolucionMotivoGeneralValor"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByMaeDevolucionMotivoGeneralDescripcion", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.maeDevolucionMotivoGeneralDescripcion = :maeDevolucionMotivoGeneralDescripcion"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByUsuarioCrea", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByUsuarioModifica", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByTerminalModifica", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmAuditoriaDevoluciones.findByFechaHoraModifica", query = "SELECT c FROM CmAuditoriaDevoluciones c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmAuditoriaDevoluciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_motivo_devolucion_id")
    private int maeMotivoDevolucionId;
    @Size(max = 8)
    @Column(name = "mae_motivo_devolucion_codigo")
    private String maeMotivoDevolucionCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_devolucion_valor")
    private String maeMotivoDevolucionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_contrato_modalidad_id")
    private int maeContratoModalidadId;
    @Size(max = 8)
    @Column(name = "mae_contrato_modalidad_codigo")
    private String maeContratoModalidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_contrato_modalidad_valor")
    private String maeContratoModalidadValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_regimen_id")
    private int maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ips")
    private String ips;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_radicado")
    private String numeroRadicado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_facturado")
    private String numeroFacturado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_radicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_devolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_factura")
    private BigDecimal valorFactura;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "mae_devolucion_motivo_general_id")
    private Integer maeDevolucionMotivoGeneralId;
    @Size(max = 8)
    @Column(name = "mae_devolucion_motivo_general_codigo")
    private String maeDevolucionMotivoGeneralCodigo;
    @Size(max = 128)
    @Column(name = "mae_devolucion_motivo_general_valor")
    private String maeDevolucionMotivoGeneralValor;
    @Size(max = 512)
    @Column(name = "mae_devolucion_motivo_general_descripcion")
    private String maeDevolucionMotivoGeneralDescripcion;
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
    @JoinColumn(name = "cm_devolucion_masiva_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmDevolucionMasiva cmDevolucionMasivaId;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @OneToMany(mappedBy = "cmAuditoriaDevolucionesId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList;

    public CmAuditoriaDevoluciones() {
    }

    public CmAuditoriaDevoluciones(Integer id) {
        this.id = id;
    }

    public CmAuditoriaDevoluciones(Integer id, int maeMotivoDevolucionId, int maeContratoModalidadId, int maeRegimenId, String nit, String ips, String numeroRadicado, String numeroFacturado, Date fechaRadicacion, Date fechaDevolucion, BigDecimal valorFactura, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeMotivoDevolucionId = maeMotivoDevolucionId;
        this.maeContratoModalidadId = maeContratoModalidadId;
        this.maeRegimenId = maeRegimenId;
        this.nit = nit;
        this.ips = ips;
        this.numeroRadicado = numeroRadicado;
        this.numeroFacturado = numeroFacturado;
        this.fechaRadicacion = fechaRadicacion;
        this.fechaDevolucion = fechaDevolucion;
        this.valorFactura = valorFactura;
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

    public int getMaeMotivoDevolucionId() {
        return maeMotivoDevolucionId;
    }

    public void setMaeMotivoDevolucionId(int maeMotivoDevolucionId) {
        this.maeMotivoDevolucionId = maeMotivoDevolucionId;
    }

    public String getMaeMotivoDevolucionCodigo() {
        return maeMotivoDevolucionCodigo;
    }

    public void setMaeMotivoDevolucionCodigo(String maeMotivoDevolucionCodigo) {
        this.maeMotivoDevolucionCodigo = maeMotivoDevolucionCodigo;
    }

    public String getMaeMotivoDevolucionValor() {
        return maeMotivoDevolucionValor;
    }

    public void setMaeMotivoDevolucionValor(String maeMotivoDevolucionValor) {
        this.maeMotivoDevolucionValor = maeMotivoDevolucionValor;
    }

    public int getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(int maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
    }

    public String getMaeContratoModalidadCodigo() {
        return maeContratoModalidadCodigo;
    }

    public void setMaeContratoModalidadCodigo(String maeContratoModalidadCodigo) {
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
    }

    public String getMaeContratoModalidadValor() {
        return maeContratoModalidadValor;
    }

    public void setMaeContratoModalidadValor(String maeContratoModalidadValor) {
        this.maeContratoModalidadValor = maeContratoModalidadValor;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroFacturado() {
        return numeroFacturado;
    }

    public void setNumeroFacturado(String numeroFacturado) {
        this.numeroFacturado = numeroFacturado;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getMaeDevolucionMotivoGeneralId() {
        return maeDevolucionMotivoGeneralId;
    }

    public void setMaeDevolucionMotivoGeneralId(Integer maeDevolucionMotivoGeneralId) {
        this.maeDevolucionMotivoGeneralId = maeDevolucionMotivoGeneralId;
    }

    public String getMaeDevolucionMotivoGeneralCodigo() {
        return maeDevolucionMotivoGeneralCodigo;
    }

    public void setMaeDevolucionMotivoGeneralCodigo(String maeDevolucionMotivoGeneralCodigo) {
        this.maeDevolucionMotivoGeneralCodigo = maeDevolucionMotivoGeneralCodigo;
    }

    public String getMaeDevolucionMotivoGeneralValor() {
        return maeDevolucionMotivoGeneralValor;
    }

    public void setMaeDevolucionMotivoGeneralValor(String maeDevolucionMotivoGeneralValor) {
        this.maeDevolucionMotivoGeneralValor = maeDevolucionMotivoGeneralValor;
    }

    public String getMaeDevolucionMotivoGeneralDescripcion() {
        return maeDevolucionMotivoGeneralDescripcion;
    }

    public void setMaeDevolucionMotivoGeneralDescripcion(String maeDevolucionMotivoGeneralDescripcion) {
        this.maeDevolucionMotivoGeneralDescripcion = maeDevolucionMotivoGeneralDescripcion;
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

    public CmDevolucionMasiva getCmDevolucionMasivaId() {
        return cmDevolucionMasivaId;
    }

    public void setCmDevolucionMasivaId(CmDevolucionMasiva cmDevolucionMasivaId) {
        this.cmDevolucionMasivaId = cmDevolucionMasivaId;
    }

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    @XmlTransient
    public List<CmRadicados> getCmRadicadosList() {
        return cmRadicadosList;
    }

    public void setCmRadicadosList(List<CmRadicados> cmRadicadosList) {
        this.cmRadicadosList = cmRadicadosList;
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
        if (!(object instanceof CmAuditoriaDevoluciones)) {
            return false;
        }
        CmAuditoriaDevoluciones other = (CmAuditoriaDevoluciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaDevoluciones[ id=" + id + " ]";
    }
    
}
