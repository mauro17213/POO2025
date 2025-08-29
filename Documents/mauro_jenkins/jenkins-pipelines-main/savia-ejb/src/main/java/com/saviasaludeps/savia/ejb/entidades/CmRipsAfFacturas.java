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
@Table(name = "cm_rips_af_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsAfFacturas.findAll", query = "SELECT c FROM CmRipsAfFacturas c"),
    @NamedQuery(name = "CmRipsAfFacturas.findById", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsAfFacturas.findByFila", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsAfFacturas.findByCodigoReps", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsAfFacturas.findByRazonSocial", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.razonSocial = :razonSocial"),
    @NamedQuery(name = "CmRipsAfFacturas.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsAfFacturas.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsAfFacturas.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsAfFacturas.findByNit", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.nit = :nit"),
    @NamedQuery(name = "CmRipsAfFacturas.findByNumeroFactura", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsAfFacturas.findByFechaFactura", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.fechaFactura = :fechaFactura"),
    @NamedQuery(name = "CmRipsAfFacturas.findByFechaInicio", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CmRipsAfFacturas.findByFechaFinal", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "CmRipsAfFacturas.findByCodigoEps", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.codigoEps = :codigoEps"),
    @NamedQuery(name = "CmRipsAfFacturas.findByNombreAdministradora", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.nombreAdministradora = :nombreAdministradora"),
    @NamedQuery(name = "CmRipsAfFacturas.findByContrato", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CmRipsAfFacturas.findByPlanBeneficios", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.planBeneficios = :planBeneficios"),
    @NamedQuery(name = "CmRipsAfFacturas.findByNumeroPoliza", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.numeroPoliza = :numeroPoliza"),
    @NamedQuery(name = "CmRipsAfFacturas.findByValorCopago", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.valorCopago = :valorCopago"),
    @NamedQuery(name = "CmRipsAfFacturas.findByValorComision", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.valorComision = :valorComision"),
    @NamedQuery(name = "CmRipsAfFacturas.findByValorDescuento", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.valorDescuento = :valorDescuento"),
    @NamedQuery(name = "CmRipsAfFacturas.findByValorAPagar", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.valorAPagar = :valorAPagar"),
    @NamedQuery(name = "CmRipsAfFacturas.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsAfFacturas.findByArchivoRuta", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsAfFacturas.findByArchivoNombre", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsAfFacturas.findByUsuarioCrea", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsAfFacturas.findByTerminalCrea", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsAfFacturas.findByFechaHoraCrea", query = "SELECT c FROM CmRipsAfFacturas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsAfFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_reps")
    private String codigoReps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo_eps")
    private String codigoEps;
    @Size(max = 64)
    @Column(name = "nombre_administradora")
    private String nombreAdministradora;
    @Size(max = 64)
    @Column(name = "contrato")
    private String contrato;
    @Size(max = 64)
    @Column(name = "plan_beneficios")
    private String planBeneficios;
    @Size(max = 64)
    @Column(name = "numero_poliza")
    private String numeroPoliza;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_copago")
    private BigDecimal valorCopago;
    @Column(name = "valor_comision")
    private BigDecimal valorComision;
    @Column(name = "valor_descuento")
    private BigDecimal valorDescuento;
    @Column(name = "valor_a_pagar")
    private BigDecimal valorAPagar;
    @Size(max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Size(max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRipsAfFacturas() {
    }

    public CmRipsAfFacturas(Integer id) {
        this.id = id;
    }

    public CmRipsAfFacturas(Integer id, int fila, String codigoReps, String razonSocial, String maeTipoDocumentoCodigo, String nit, String numeroFactura, Date fechaFactura, String codigoEps, BigDecimal valorCopago, String archivoNombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.codigoReps = codigoReps;
        this.razonSocial = razonSocial;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.nit = nit;
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
        this.codigoEps = codigoEps;
        this.valorCopago = valorCopago;
        this.archivoNombre = archivoNombre;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getCodigoReps() {
        return codigoReps;
    }

    public void setCodigoReps(String codigoReps) {
        this.codigoReps = codigoReps;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(String codigoEps) {
        this.codigoEps = codigoEps;
    }

    public String getNombreAdministradora() {
        return nombreAdministradora;
    }

    public void setNombreAdministradora(String nombreAdministradora) {
        this.nombreAdministradora = nombreAdministradora;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getPlanBeneficios() {
        return planBeneficios;
    }

    public void setPlanBeneficios(String planBeneficios) {
        this.planBeneficios = planBeneficios;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorComision() {
        return valorComision;
    }

    public void setValorComision(BigDecimal valorComision) {
        this.valorComision = valorComision;
    }

    public BigDecimal getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
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

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
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
        if (!(object instanceof CmRipsAfFacturas)) {
            return false;
        }
        CmRipsAfFacturas other = (CmRipsAfFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsAfFacturas[ id=" + id + " ]";
    }
    
}
