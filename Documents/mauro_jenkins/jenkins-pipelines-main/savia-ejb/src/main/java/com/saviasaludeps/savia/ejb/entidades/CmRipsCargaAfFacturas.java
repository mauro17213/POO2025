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
@Table(name = "cm_rips_carga_af_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargaAfFacturas.findAll", query = "SELECT c FROM CmRipsCargaAfFacturas c"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findById", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByFila", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByCodigoReps", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByRazonSocial", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.razonSocial = :razonSocial"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByNit", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.nit = :nit"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByNumeroFactura", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByFechaFactura", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.fechaFactura = :fechaFactura"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByFechaInicio", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByFechaFinal", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByCodigoEps", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.codigoEps = :codigoEps"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByNombreAdministradora", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.nombreAdministradora = :nombreAdministradora"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByContrato", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByPlanBeneficios", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.planBeneficios = :planBeneficios"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByNumeroPoliza", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.numeroPoliza = :numeroPoliza"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByValorCopago", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.valorCopago = :valorCopago"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByValorComision", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.valorComision = :valorComision"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByValorDescuento", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.valorDescuento = :valorDescuento"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByValorAPagar", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.valorAPagar = :valorAPagar"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByArchivoRuta", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByArchivoNombre", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByTerminalCrea", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargaAfFacturas.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargaAfFacturas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargaAfFacturas implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_a_pagar")
    private BigDecimal valorAPagar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
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

    public CmRipsCargaAfFacturas() {
    }

    public CmRipsCargaAfFacturas(Integer id) {
        this.id = id;
    }

    public CmRipsCargaAfFacturas(Integer id, int fila, String codigoReps, String razonSocial, String maeTipoDocumentoCodigo, String nit, String numeroFactura, Date fechaFactura, String codigoEps, BigDecimal valorCopago, BigDecimal valorAPagar, String archivoNombreOriginal, String archivoRuta, String archivoNombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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
        this.valorAPagar = valorAPagar;
        this.archivoNombreOriginal = archivoNombreOriginal;
        this.archivoRuta = archivoRuta;
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
        if (!(object instanceof CmRipsCargaAfFacturas)) {
            return false;
        }
        CmRipsCargaAfFacturas other = (CmRipsCargaAfFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAfFacturas[ id=" + id + " ]";
    }
    
}
