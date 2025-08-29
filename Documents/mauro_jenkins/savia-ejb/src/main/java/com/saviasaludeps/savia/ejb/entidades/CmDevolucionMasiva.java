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
@Table(name = "cm_devolucion_masiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmDevolucionMasiva.findAll", query = "SELECT c FROM CmDevolucionMasiva c"),
    @NamedQuery(name = "CmDevolucionMasiva.findById", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.id = :id"),
    @NamedQuery(name = "CmDevolucionMasiva.findByNit", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.nit = :nit"),
    @NamedQuery(name = "CmDevolucionMasiva.findByIps", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.ips = :ips"),
    @NamedQuery(name = "CmDevolucionMasiva.findBySumaValorFacturado", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.sumaValorFacturado = :sumaValorFacturado"),
    @NamedQuery(name = "CmDevolucionMasiva.findBySumaValorCopago", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.sumaValorCopago = :sumaValorCopago"),
    @NamedQuery(name = "CmDevolucionMasiva.findBySumaValorBruto", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.sumaValorBruto = :sumaValorBruto"),
    @NamedQuery(name = "CmDevolucionMasiva.findByCmRadicado", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.cmRadicado = :cmRadicado"),
    @NamedQuery(name = "CmDevolucionMasiva.findByEstadoProceso", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "CmDevolucionMasiva.findByCantidadFacturas", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.cantidadFacturas = :cantidadFacturas"),
    @NamedQuery(name = "CmDevolucionMasiva.findByCantidadFacturasRegistradas", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.cantidadFacturasRegistradas = :cantidadFacturasRegistradas"),
    @NamedQuery(name = "CmDevolucionMasiva.findByHoraFinalizacionRegistro", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.horaFinalizacionRegistro = :horaFinalizacionRegistro"),
    @NamedQuery(name = "CmDevolucionMasiva.findByUsuarioCrea", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmDevolucionMasiva.findByTerminalCrea", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmDevolucionMasiva.findByFechaHoraCrea", query = "SELECT c FROM CmDevolucionMasiva c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmDevolucionMasiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 16)
    @Column(name = "nit")
    private String nit;
    @Size(max = 128)
    @Column(name = "ips")
    private String ips;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "suma_valor_facturado")
    private BigDecimal sumaValorFacturado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "suma_valor_copago")
    private BigDecimal sumaValorCopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "suma_valor_bruto")
    private BigDecimal sumaValorBruto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "cm_radicado")
    private String cmRadicado;
    @Column(name = "estado_proceso")
    private Integer estadoProceso;
    @Column(name = "cantidad_facturas")
    private Integer cantidadFacturas;
    @Column(name = "cantidad_facturas_registradas")
    private Integer cantidadFacturasRegistradas;
    @Column(name = "hora_finalizacion_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFinalizacionRegistro;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
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
    @OneToMany(mappedBy = "cmDevolucionMasivaId", fetch = FetchType.LAZY)
    private List<CmAuditoriaDevoluciones> cmAuditoriaDevolucionesList;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @OneToMany(mappedBy = "cmDevolucionMasivaId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList;

    public CmDevolucionMasiva() {
    }

    public CmDevolucionMasiva(Integer id) {
        this.id = id;
    }

    public CmDevolucionMasiva(Integer id, BigDecimal sumaValorFacturado, BigDecimal sumaValorCopago, BigDecimal sumaValorBruto, String cmRadicado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.sumaValorFacturado = sumaValorFacturado;
        this.sumaValorCopago = sumaValorCopago;
        this.sumaValorBruto = sumaValorBruto;
        this.cmRadicado = cmRadicado;
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

    public BigDecimal getSumaValorFacturado() {
        return sumaValorFacturado;
    }

    public void setSumaValorFacturado(BigDecimal sumaValorFacturado) {
        this.sumaValorFacturado = sumaValorFacturado;
    }

    public BigDecimal getSumaValorCopago() {
        return sumaValorCopago;
    }

    public void setSumaValorCopago(BigDecimal sumaValorCopago) {
        this.sumaValorCopago = sumaValorCopago;
    }

    public BigDecimal getSumaValorBruto() {
        return sumaValorBruto;
    }

    public void setSumaValorBruto(BigDecimal sumaValorBruto) {
        this.sumaValorBruto = sumaValorBruto;
    }

    public String getCmRadicado() {
        return cmRadicado;
    }

    public void setCmRadicado(String cmRadicado) {
        this.cmRadicado = cmRadicado;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
    public List<CmAuditoriaDevoluciones> getCmAuditoriaDevolucionesList() {
        return cmAuditoriaDevolucionesList;
    }

    public void setCmAuditoriaDevolucionesList(List<CmAuditoriaDevoluciones> cmAuditoriaDevolucionesList) {
        this.cmAuditoriaDevolucionesList = cmAuditoriaDevolucionesList;
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
        if (!(object instanceof CmDevolucionMasiva)) {
            return false;
        }
        CmDevolucionMasiva other = (CmDevolucionMasiva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmDevolucionMasiva[ id=" + id + " ]";
    }
    
}
