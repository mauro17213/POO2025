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
@Table(name = "cm_pago_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmPagoFacturas.findAll", query = "SELECT c FROM CmPagoFacturas c"),
    @NamedQuery(name = "CmPagoFacturas.findById", query = "SELECT c FROM CmPagoFacturas c WHERE c.id = :id"),
    @NamedQuery(name = "CmPagoFacturas.findByConsecutivo", query = "SELECT c FROM CmPagoFacturas c WHERE c.consecutivo = :consecutivo"),
    @NamedQuery(name = "CmPagoFacturas.findByNumeroFactura", query = "SELECT c FROM CmPagoFacturas c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmPagoFacturas.findByClaseDocumento", query = "SELECT c FROM CmPagoFacturas c WHERE c.claseDocumento = :claseDocumento"),
    @NamedQuery(name = "CmPagoFacturas.findByCmFacturaValor", query = "SELECT c FROM CmPagoFacturas c WHERE c.cmFacturaValor = :cmFacturaValor"),
    @NamedQuery(name = "CmPagoFacturas.findByCmFacturaFecha", query = "SELECT c FROM CmPagoFacturas c WHERE c.cmFacturaFecha = :cmFacturaFecha"),
    @NamedQuery(name = "CmPagoFacturas.findByCmFacturaEstado", query = "SELECT c FROM CmPagoFacturas c WHERE c.cmFacturaEstado = :cmFacturaEstado"),
    @NamedQuery(name = "CmPagoFacturas.findByValorBruto", query = "SELECT c FROM CmPagoFacturas c WHERE c.valorBruto = :valorBruto"),
    @NamedQuery(name = "CmPagoFacturas.findByValorNeto", query = "SELECT c FROM CmPagoFacturas c WHERE c.valorNeto = :valorNeto"),
    @NamedQuery(name = "CmPagoFacturas.findByValorDeducciones", query = "SELECT c FROM CmPagoFacturas c WHERE c.valorDeducciones = :valorDeducciones"),
    @NamedQuery(name = "CmPagoFacturas.findByDescripcion", query = "SELECT c FROM CmPagoFacturas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CmPagoFacturas.findByDocumentoContable", query = "SELECT c FROM CmPagoFacturas c WHERE c.documentoContable = :documentoContable"),
    @NamedQuery(name = "CmPagoFacturas.findByUsuarioCrea", query = "SELECT c FROM CmPagoFacturas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmPagoFacturas.findByTerminalCrea", query = "SELECT c FROM CmPagoFacturas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmPagoFacturas.findByFechaHoraCrea", query = "SELECT c FROM CmPagoFacturas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmPagoFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "consecutivo")
    private Integer consecutivo;
    @Size(max = 32)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "clase_documento")
    private String claseDocumento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cm_factura_valor")
    private BigDecimal cmFacturaValor;
    @Column(name = "cm_factura_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cmFacturaFecha;
    @Column(name = "cm_factura_estado")
    private Integer cmFacturaEstado;
    @Column(name = "valor_bruto")
    private BigDecimal valorBruto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_neto")
    private BigDecimal valorNeto;
    @Column(name = "valor_deducciones")
    private BigDecimal valorDeducciones;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 16)
    @Column(name = "documento_contable")
    private String documentoContable;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmPagoFacturasId", fetch = FetchType.LAZY)
    private List<CmPagoFacturaRetenciones> cmPagoFacturaRetencionesList;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @JoinColumn(name = "cm_pago_transacciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmPagoTransacciones cmPagoTransaccionesId;
    @JoinColumn(name = "cm_pagos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmPagos cmPagosId;

    public CmPagoFacturas() {
    }

    public CmPagoFacturas(Integer id) {
        this.id = id;
    }

    public CmPagoFacturas(Integer id, String claseDocumento, BigDecimal valorNeto, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.claseDocumento = claseDocumento;
        this.valorNeto = valorNeto;
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

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getClaseDocumento() {
        return claseDocumento;
    }

    public void setClaseDocumento(String claseDocumento) {
        this.claseDocumento = claseDocumento;
    }

    public BigDecimal getCmFacturaValor() {
        return cmFacturaValor;
    }

    public void setCmFacturaValor(BigDecimal cmFacturaValor) {
        this.cmFacturaValor = cmFacturaValor;
    }

    public Date getCmFacturaFecha() {
        return cmFacturaFecha;
    }

    public void setCmFacturaFecha(Date cmFacturaFecha) {
        this.cmFacturaFecha = cmFacturaFecha;
    }

    public Integer getCmFacturaEstado() {
        return cmFacturaEstado;
    }

    public void setCmFacturaEstado(Integer cmFacturaEstado) {
        this.cmFacturaEstado = cmFacturaEstado;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(BigDecimal valorNeto) {
        this.valorNeto = valorNeto;
    }

    public BigDecimal getValorDeducciones() {
        return valorDeducciones;
    }

    public void setValorDeducciones(BigDecimal valorDeducciones) {
        this.valorDeducciones = valorDeducciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDocumentoContable() {
        return documentoContable;
    }

    public void setDocumentoContable(String documentoContable) {
        this.documentoContable = documentoContable;
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

    @XmlTransient
    public List<CmPagoFacturaRetenciones> getCmPagoFacturaRetencionesList() {
        return cmPagoFacturaRetencionesList;
    }

    public void setCmPagoFacturaRetencionesList(List<CmPagoFacturaRetenciones> cmPagoFacturaRetencionesList) {
        this.cmPagoFacturaRetencionesList = cmPagoFacturaRetencionesList;
    }

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public CmPagoTransacciones getCmPagoTransaccionesId() {
        return cmPagoTransaccionesId;
    }

    public void setCmPagoTransaccionesId(CmPagoTransacciones cmPagoTransaccionesId) {
        this.cmPagoTransaccionesId = cmPagoTransaccionesId;
    }

    public CmPagos getCmPagosId() {
        return cmPagosId;
    }

    public void setCmPagosId(CmPagos cmPagosId) {
        this.cmPagosId = cmPagosId;
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
        if (!(object instanceof CmPagoFacturas)) {
            return false;
        }
        CmPagoFacturas other = (CmPagoFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmPagoFacturas[ id=" + id + " ]";
    }
    
}
