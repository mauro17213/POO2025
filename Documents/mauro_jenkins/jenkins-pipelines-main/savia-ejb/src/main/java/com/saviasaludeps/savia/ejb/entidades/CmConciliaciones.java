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
@Table(name = "cm_conciliaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmConciliaciones.findAll", query = "SELECT c FROM CmConciliaciones c"),
    @NamedQuery(name = "CmConciliaciones.findById", query = "SELECT c FROM CmConciliaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmConciliaciones.findByValor", query = "SELECT c FROM CmConciliaciones c WHERE c.valor = :valor"),
    @NamedQuery(name = "CmConciliaciones.findByPorcentaje", query = "SELECT c FROM CmConciliaciones c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "CmConciliaciones.findByValorPagadoEps", query = "SELECT c FROM CmConciliaciones c WHERE c.valorPagadoEps = :valorPagadoEps"),
    @NamedQuery(name = "CmConciliaciones.findByPorcentajePagadoEps", query = "SELECT c FROM CmConciliaciones c WHERE c.porcentajePagadoEps = :porcentajePagadoEps"),
    @NamedQuery(name = "CmConciliaciones.findByValorAceptadoIps", query = "SELECT c FROM CmConciliaciones c WHERE c.valorAceptadoIps = :valorAceptadoIps"),
    @NamedQuery(name = "CmConciliaciones.findByPorcentajeAceptadoIps", query = "SELECT c FROM CmConciliaciones c WHERE c.porcentajeAceptadoIps = :porcentajeAceptadoIps"),
    @NamedQuery(name = "CmConciliaciones.findByEstadoProceso", query = "SELECT c FROM CmConciliaciones c WHERE c.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "CmConciliaciones.findByCantidadFacturas", query = "SELECT c FROM CmConciliaciones c WHERE c.cantidadFacturas = :cantidadFacturas"),
    @NamedQuery(name = "CmConciliaciones.findByCantidadFacturasRegistradas", query = "SELECT c FROM CmConciliaciones c WHERE c.cantidadFacturasRegistradas = :cantidadFacturasRegistradas"),
    @NamedQuery(name = "CmConciliaciones.findByHoraFinalizacionRegistro", query = "SELECT c FROM CmConciliaciones c WHERE c.horaFinalizacionRegistro = :horaFinalizacionRegistro"),
    @NamedQuery(name = "CmConciliaciones.findByArchivo", query = "SELECT c FROM CmConciliaciones c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CmConciliaciones.findByArchivoNombre", query = "SELECT c FROM CmConciliaciones c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmConciliaciones.findByArchivoRuta", query = "SELECT c FROM CmConciliaciones c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmConciliaciones.findByArchivoExiste", query = "SELECT c FROM CmConciliaciones c WHERE c.archivoExiste = :archivoExiste"),
    @NamedQuery(name = "CmConciliaciones.findByUsuarioCrea", query = "SELECT c FROM CmConciliaciones c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmConciliaciones.findByTerminalCrea", query = "SELECT c FROM CmConciliaciones c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmConciliaciones.findByFechaHoraCrea", query = "SELECT c FROM CmConciliaciones c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmConciliaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pagado_eps")
    private BigDecimal valorPagadoEps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_pagado_eps")
    private BigDecimal porcentajePagadoEps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_aceptado_ips")
    private BigDecimal valorAceptadoIps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_aceptado_ips")
    private BigDecimal porcentajeAceptadoIps;
    @Column(name = "estado_proceso")
    private Integer estadoProceso;
    @Column(name = "cantidad_facturas")
    private Integer cantidadFacturas;
    @Column(name = "cantidad_facturas_registradas")
    private Integer cantidadFacturasRegistradas;
    @Column(name = "hora_finalizacion_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFinalizacionRegistro;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 256)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Size(max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Column(name = "archivo_existe")
    private Boolean archivoExiste;
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
    @OneToMany(mappedBy = "cmConciliacionesId", fetch = FetchType.LAZY)
    private List<CmGlosaRespuestas> cmGlosaRespuestasList;
    @OneToMany(mappedBy = "cmConciliacionesId", fetch = FetchType.LAZY)
    private List<CmSincronizaciones> cmSincronizacionesList;
    @OneToMany(mappedBy = "cmConciliacionesId", fetch = FetchType.LAZY)
    private List<WsCmTransacciones> wsCmTransaccionesList;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @OneToMany(mappedBy = "cmConciliacionesId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList;

    public CmConciliaciones() {
    }

    public CmConciliaciones(Integer id) {
        this.id = id;
    }

    public CmConciliaciones(Integer id, BigDecimal valor, BigDecimal porcentaje, BigDecimal valorPagadoEps, BigDecimal porcentajePagadoEps, BigDecimal valorAceptadoIps, BigDecimal porcentajeAceptadoIps, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valor = valor;
        this.porcentaje = porcentaje;
        this.valorPagadoEps = valorPagadoEps;
        this.porcentajePagadoEps = porcentajePagadoEps;
        this.valorAceptadoIps = valorAceptadoIps;
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public BigDecimal getPorcentajePagadoEps() {
        return porcentajePagadoEps;
    }

    public void setPorcentajePagadoEps(BigDecimal porcentajePagadoEps) {
        this.porcentajePagadoEps = porcentajePagadoEps;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public BigDecimal getPorcentajeAceptadoIps() {
        return porcentajeAceptadoIps;
    }

    public void setPorcentajeAceptadoIps(BigDecimal porcentajeAceptadoIps) {
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
    }

    public Integer getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(Integer estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Integer getCantidadFacturas() {
        return cantidadFacturas;
    }

    public void setCantidadFacturas(Integer cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    public Integer getCantidadFacturasRegistradas() {
        return cantidadFacturasRegistradas;
    }

    public void setCantidadFacturasRegistradas(Integer cantidadFacturasRegistradas) {
        this.cantidadFacturasRegistradas = cantidadFacturasRegistradas;
    }

    public Date getHoraFinalizacionRegistro() {
        return horaFinalizacionRegistro;
    }

    public void setHoraFinalizacionRegistro(Date horaFinalizacionRegistro) {
        this.horaFinalizacionRegistro = horaFinalizacionRegistro;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public Boolean getArchivoExiste() {
        return archivoExiste;
    }

    public void setArchivoExiste(Boolean archivoExiste) {
        this.archivoExiste = archivoExiste;
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
    public List<CmGlosaRespuestas> getCmGlosaRespuestasList() {
        return cmGlosaRespuestasList;
    }

    public void setCmGlosaRespuestasList(List<CmGlosaRespuestas> cmGlosaRespuestasList) {
        this.cmGlosaRespuestasList = cmGlosaRespuestasList;
    }

    @XmlTransient
    public List<CmSincronizaciones> getCmSincronizacionesList() {
        return cmSincronizacionesList;
    }

    public void setCmSincronizacionesList(List<CmSincronizaciones> cmSincronizacionesList) {
        this.cmSincronizacionesList = cmSincronizacionesList;
    }

    @XmlTransient
    public List<WsCmTransacciones> getWsCmTransaccionesList() {
        return wsCmTransaccionesList;
    }

    public void setWsCmTransaccionesList(List<WsCmTransacciones> wsCmTransaccionesList) {
        this.wsCmTransaccionesList = wsCmTransaccionesList;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
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
        if (!(object instanceof CmConciliaciones)) {
            return false;
        }
        CmConciliaciones other = (CmConciliaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmConciliaciones[ id=" + id + " ]";
    }
    
}
