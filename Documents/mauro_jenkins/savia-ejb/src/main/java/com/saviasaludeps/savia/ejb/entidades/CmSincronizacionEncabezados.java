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
@Table(name = "cm_sincronizacion_encabezados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmSincronizacionEncabezados.findAll", query = "SELECT c FROM CmSincronizacionEncabezados c"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findById", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.id = :id"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByEstado", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByProveedorNit", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.proveedorNit = :proveedorNit"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByNumeroDocumento", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByNumeroRadicado", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.numeroRadicado = :numeroRadicado"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByFacturaId", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.facturaId = :facturaId"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByRegimen", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.regimen = :regimen"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByValorDocumento", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.valorDocumento = :valorDocumento"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByValorPagado", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.valorPagado = :valorPagado"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByValorGlosado", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.valorGlosado = :valorGlosado"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByValorCopago", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.valorCopago = :valorCopago"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByFechaHoraDocumento", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.fechaHoraDocumento = :fechaHoraDocumento"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByFechaHoraProceso", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.fechaHoraProceso = :fechaHoraProceso"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByCuotaModeradora", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.cuotaModeradora = :cuotaModeradora"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByContrato", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByUsuarioCrea", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByTerminalCrea", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmSincronizacionEncabezados.findByFechaHoraCrea", query = "SELECT c FROM CmSincronizacionEncabezados c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmSincronizacionEncabezados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 16)
    @Column(name = "proveedor_nit")
    private String proveedorNit;
    @Size(max = 32)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 32)
    @Column(name = "numero_radicado")
    private String numeroRadicado;
    @Column(name = "factura_id")
    private Integer facturaId;
    @Size(max = 16)
    @Column(name = "regimen")
    private String regimen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_documento")
    private BigDecimal valorDocumento;
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Column(name = "valor_glosado")
    private BigDecimal valorGlosado;
    @Column(name = "valor_copago")
    private BigDecimal valorCopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_documento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_proceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraProceso;
    @Column(name = "cuota_moderadora")
    private BigDecimal cuotaModeradora;
    @Size(max = 32)
    @Column(name = "contrato")
    private String contrato;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmSincronizacionEncabezadosId", fetch = FetchType.LAZY)
    private List<CmSincronizacionDetalles> cmSincronizacionDetallesList;
    @JoinColumn(name = "cm_radicados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRadicados cmRadicadosId;

    public CmSincronizacionEncabezados() {
    }

    public CmSincronizacionEncabezados(Integer id) {
        this.id = id;
    }

    public CmSincronizacionEncabezados(Integer id, int estado, Date fechaHoraDocumento, Date fechaHoraProceso, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraDocumento = fechaHoraDocumento;
        this.fechaHoraProceso = fechaHoraProceso;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getProveedorNit() {
        return proveedorNit;
    }

    public void setProveedorNit(String proveedorNit) {
        this.proveedorNit = proveedorNit;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public BigDecimal getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public Date getFechaHoraDocumento() {
        return fechaHoraDocumento;
    }

    public void setFechaHoraDocumento(Date fechaHoraDocumento) {
        this.fechaHoraDocumento = fechaHoraDocumento;
    }

    public Date getFechaHoraProceso() {
        return fechaHoraProceso;
    }

    public void setFechaHoraProceso(Date fechaHoraProceso) {
        this.fechaHoraProceso = fechaHoraProceso;
    }

    public BigDecimal getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(BigDecimal cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
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
    public List<CmSincronizacionDetalles> getCmSincronizacionDetallesList() {
        return cmSincronizacionDetallesList;
    }

    public void setCmSincronizacionDetallesList(List<CmSincronizacionDetalles> cmSincronizacionDetallesList) {
        this.cmSincronizacionDetallesList = cmSincronizacionDetallesList;
    }

    public CmRadicados getCmRadicadosId() {
        return cmRadicadosId;
    }

    public void setCmRadicadosId(CmRadicados cmRadicadosId) {
        this.cmRadicadosId = cmRadicadosId;
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
        if (!(object instanceof CmSincronizacionEncabezados)) {
            return false;
        }
        CmSincronizacionEncabezados other = (CmSincronizacionEncabezados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmSincronizacionEncabezados[ id=" + id + " ]";
    }
    
}
