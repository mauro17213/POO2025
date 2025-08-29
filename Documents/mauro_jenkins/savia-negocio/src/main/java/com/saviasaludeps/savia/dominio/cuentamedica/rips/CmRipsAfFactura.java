package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class CmRipsAfFactura extends Auditoria {

    private Integer id;
    private int fila;
    private String codigoReps;
    private String razonSocial;
    private String tipoIdentificacion;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoValor;
    private String maeTipoDocumentoCodigo;
    private String nit;
    private String numeroFactura;
    private Date fechaFactura;
    private Date fechaInicio;
    private Date fechaFinal;
    private String codigoEps;
    private String nombreAdministradora;
    private String contrato;
    private String planBeneficio;
    private String numeroPoliza;
    private BigDecimal valorCopago;
    private BigDecimal valorComision;
    private BigDecimal valorDescuento;
    private BigDecimal valorAPagar;
    private String archivoNombreOriginal;
    private String archivoRuta;
    private String archivoNombre;
    private CmRipsCarga cmRipsCarga;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public String getPlanBeneficio() {
        return planBeneficio;
    }

    public void setPlanBeneficio(String planBeneficio) {
        this.planBeneficio = planBeneficio;
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

    public CmRipsCarga getCmRipsCarga() {
        return cmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga cmRipsCarga) {
        this.cmRipsCarga = cmRipsCarga;
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

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return "CmRipsAfFactura{" + "id=" + id + ", codigoReps=" + codigoReps + ", razonSocial=" + razonSocial + ", tipoIdentificacion=" + tipoIdentificacion + ", nit=" + nit + ", numeroFactura=" + numeroFactura + ", fechaFactura=" + fechaFactura + ", cmRipsCarga=" + cmRipsCarga + '}';
    }

}
