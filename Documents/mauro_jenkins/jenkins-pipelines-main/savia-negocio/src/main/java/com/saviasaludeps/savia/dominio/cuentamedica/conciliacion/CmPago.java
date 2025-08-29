package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CmPago extends Auditoria {

    public CmPago() {
    }

    public CmPago(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String idetificador;
    private int tipo;
    private int forma;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private Date fechaHora;
    private Integer facturas;
    private BigDecimal valorBruto;
    private BigDecimal valorNeto;
    private BigDecimal valorDeducciones;
    private BigDecimal valorCompensacionAnticipos;
    private List<CmPagoTransaccion> cmPagoTransaccionesList;
    private CntPrestador cntPrestadoresId;
    private List<CmPagoFactura> cmPagoFacturasList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdetificador() {
        return idetificador;
    }

    public void setIdetificador(String idetificador) {
        this.idetificador = idetificador;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getFacturas() {
        return facturas;
    }

    public void setFacturas(Integer facturas) {
        this.facturas = facturas;
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

    public BigDecimal getValorCompensacionAnticipos() {
        return valorCompensacionAnticipos;
    }

    public void setValorCompensacionAnticipos(BigDecimal valorCompensacionAnticipos) {
        this.valorCompensacionAnticipos = valorCompensacionAnticipos;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public List<CmPagoTransaccion> getCmPagoTransaccionesList() {
        return cmPagoTransaccionesList;
    }

    public void setCmPagoTransaccionesList(List<CmPagoTransaccion> cmPagoTransaccionesList) {
        this.cmPagoTransaccionesList = cmPagoTransaccionesList;
    }

    public CntPrestador getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestador cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public List<CmPagoFactura> getCmPagoFacturasList() {
        return cmPagoFacturasList;
    }

    public void setCmPagoFacturasList(List<CmPagoFactura> cmPagoFacturasList) {
        this.cmPagoFacturasList = cmPagoFacturasList;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getForma() {
        return forma;
    }

    public void setForma(int forma) {
        this.forma = forma;
    }

    @Override
    public String toString() {
        return "CmPago{" + "id=" + id + ", idetificador=" + idetificador + ", tipo=" + tipo + ", forma=" + forma + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", fechaHora=" + fechaHora + ", facturas=" + facturas + ", valorBruto=" + valorBruto + ", valorNeto=" + valorNeto + ", valorDeducciones=" + valorDeducciones + ", valorCompensacionAnticipos=" + valorCompensacionAnticipos + ", cmPagoTransaccionesList=" + cmPagoTransaccionesList + ", cntPrestadoresId=" + cntPrestadoresId + ", cmPagoFacturasList=" + cmPagoFacturasList + '}';
    }
}
