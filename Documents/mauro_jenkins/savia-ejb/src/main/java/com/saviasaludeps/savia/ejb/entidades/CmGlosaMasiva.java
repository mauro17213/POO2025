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
@Table(name = "cm_glosa_masiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmGlosaMasiva.findAll", query = "SELECT c FROM CmGlosaMasiva c"),
    @NamedQuery(name = "CmGlosaMasiva.findById", query = "SELECT c FROM CmGlosaMasiva c WHERE c.id = :id"),
    @NamedQuery(name = "CmGlosaMasiva.findByValorFacturas", query = "SELECT c FROM CmGlosaMasiva c WHERE c.valorFacturas = :valorFacturas"),
    @NamedQuery(name = "CmGlosaMasiva.findByValorTotalPagadoEps", query = "SELECT c FROM CmGlosaMasiva c WHERE c.valorTotalPagadoEps = :valorTotalPagadoEps"),
    @NamedQuery(name = "CmGlosaMasiva.findByPorcentajePagadoEps", query = "SELECT c FROM CmGlosaMasiva c WHERE c.porcentajePagadoEps = :porcentajePagadoEps"),
    @NamedQuery(name = "CmGlosaMasiva.findByValorTotalAceptadoIps", query = "SELECT c FROM CmGlosaMasiva c WHERE c.valorTotalAceptadoIps = :valorTotalAceptadoIps"),
    @NamedQuery(name = "CmGlosaMasiva.findByPorcentajeAceptadoIps", query = "SELECT c FROM CmGlosaMasiva c WHERE c.porcentajeAceptadoIps = :porcentajeAceptadoIps"),
    @NamedQuery(name = "CmGlosaMasiva.findByEstadoProceso", query = "SELECT c FROM CmGlosaMasiva c WHERE c.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "CmGlosaMasiva.findByCantidadFacturas", query = "SELECT c FROM CmGlosaMasiva c WHERE c.cantidadFacturas = :cantidadFacturas"),
    @NamedQuery(name = "CmGlosaMasiva.findByCantidadFacturasConRespuestaGlosa", query = "SELECT c FROM CmGlosaMasiva c WHERE c.cantidadFacturasConRespuestaGlosa = :cantidadFacturasConRespuestaGlosa"),
    @NamedQuery(name = "CmGlosaMasiva.findByCantidadFacturasConRatificacionGlosa", query = "SELECT c FROM CmGlosaMasiva c WHERE c.cantidadFacturasConRatificacionGlosa = :cantidadFacturasConRatificacionGlosa"),
    @NamedQuery(name = "CmGlosaMasiva.findByHoraFinalizacionRegistro", query = "SELECT c FROM CmGlosaMasiva c WHERE c.horaFinalizacionRegistro = :horaFinalizacionRegistro"),
    @NamedQuery(name = "CmGlosaMasiva.findByUsuarioCrea", query = "SELECT c FROM CmGlosaMasiva c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmGlosaMasiva.findByTerminalCrea", query = "SELECT c FROM CmGlosaMasiva c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmGlosaMasiva.findByFechaHoraCrea", query = "SELECT c FROM CmGlosaMasiva c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmGlosaMasiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_facturas")
    private BigDecimal valorFacturas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total_pagado_eps")
    private BigDecimal valorTotalPagadoEps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_pagado_eps")
    private BigDecimal porcentajePagadoEps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total_aceptado_ips")
    private BigDecimal valorTotalAceptadoIps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_aceptado_ips")
    private BigDecimal porcentajeAceptadoIps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_proceso")
    private int estadoProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_facturas")
    private int cantidadFacturas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_facturas_con_respuesta_glosa")
    private int cantidadFacturasConRespuestaGlosa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_facturas_con_ratificacion_glosa")
    private int cantidadFacturasConRatificacionGlosa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_finalizacion_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFinalizacionRegistro;
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
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @OneToMany(mappedBy = "cmGlosaMasivaId", fetch = FetchType.LAZY)
    private List<CmGlosaRespuestas> cmGlosaRespuestasList;

    public CmGlosaMasiva() {
    }

    public CmGlosaMasiva(Integer id) {
        this.id = id;
    }

    public CmGlosaMasiva(Integer id, BigDecimal valorFacturas, BigDecimal valorTotalPagadoEps, BigDecimal porcentajePagadoEps, BigDecimal valorTotalAceptadoIps, BigDecimal porcentajeAceptadoIps, int estadoProceso, int cantidadFacturas, int cantidadFacturasConRespuestaGlosa, int cantidadFacturasConRatificacionGlosa, Date horaFinalizacionRegistro, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valorFacturas = valorFacturas;
        this.valorTotalPagadoEps = valorTotalPagadoEps;
        this.porcentajePagadoEps = porcentajePagadoEps;
        this.valorTotalAceptadoIps = valorTotalAceptadoIps;
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
        this.estadoProceso = estadoProceso;
        this.cantidadFacturas = cantidadFacturas;
        this.cantidadFacturasConRespuestaGlosa = cantidadFacturasConRespuestaGlosa;
        this.cantidadFacturasConRatificacionGlosa = cantidadFacturasConRatificacionGlosa;
        this.horaFinalizacionRegistro = horaFinalizacionRegistro;
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

    public BigDecimal getValorFacturas() {
        return valorFacturas;
    }

    public void setValorFacturas(BigDecimal valorFacturas) {
        this.valorFacturas = valorFacturas;
    }

    public BigDecimal getValorTotalPagadoEps() {
        return valorTotalPagadoEps;
    }

    public void setValorTotalPagadoEps(BigDecimal valorTotalPagadoEps) {
        this.valorTotalPagadoEps = valorTotalPagadoEps;
    }

    public BigDecimal getPorcentajePagadoEps() {
        return porcentajePagadoEps;
    }

    public void setPorcentajePagadoEps(BigDecimal porcentajePagadoEps) {
        this.porcentajePagadoEps = porcentajePagadoEps;
    }

    public BigDecimal getValorTotalAceptadoIps() {
        return valorTotalAceptadoIps;
    }

    public void setValorTotalAceptadoIps(BigDecimal valorTotalAceptadoIps) {
        this.valorTotalAceptadoIps = valorTotalAceptadoIps;
    }

    public BigDecimal getPorcentajeAceptadoIps() {
        return porcentajeAceptadoIps;
    }

    public void setPorcentajeAceptadoIps(BigDecimal porcentajeAceptadoIps) {
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
    }

    public int getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(int estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public int getCantidadFacturas() {
        return cantidadFacturas;
    }

    public void setCantidadFacturas(int cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    public int getCantidadFacturasConRespuestaGlosa() {
        return cantidadFacturasConRespuestaGlosa;
    }

    public void setCantidadFacturasConRespuestaGlosa(int cantidadFacturasConRespuestaGlosa) {
        this.cantidadFacturasConRespuestaGlosa = cantidadFacturasConRespuestaGlosa;
    }

    public int getCantidadFacturasConRatificacionGlosa() {
        return cantidadFacturasConRatificacionGlosa;
    }

    public void setCantidadFacturasConRatificacionGlosa(int cantidadFacturasConRatificacionGlosa) {
        this.cantidadFacturasConRatificacionGlosa = cantidadFacturasConRatificacionGlosa;
    }

    public Date getHoraFinalizacionRegistro() {
        return horaFinalizacionRegistro;
    }

    public void setHoraFinalizacionRegistro(Date horaFinalizacionRegistro) {
        this.horaFinalizacionRegistro = horaFinalizacionRegistro;
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

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    @XmlTransient
    public List<CmGlosaRespuestas> getCmGlosaRespuestasList() {
        return cmGlosaRespuestasList;
    }

    public void setCmGlosaRespuestasList(List<CmGlosaRespuestas> cmGlosaRespuestasList) {
        this.cmGlosaRespuestasList = cmGlosaRespuestasList;
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
        if (!(object instanceof CmGlosaMasiva)) {
            return false;
        }
        CmGlosaMasiva other = (CmGlosaMasiva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmGlosaMasiva[ id=" + id + " ]";
    }
    
}
