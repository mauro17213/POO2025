package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author bsgomez
 */
public class MpPrescripcionSuministro extends Auditoria {

    private Integer idPrescripcion;
    private String numeroPrescripcion;
    private String codigoAmbitoAtencion;
    private Date fechaPrescripcion;
    private Integer maDiagnosticoPrincipalId;
    private String maDiagnosticoPrincipalCodigo;
    private String maDiagnosticoPrincipalValor;
    private String idSuministro;

    private Integer idAfiliado;
    private String primerNombreAfiliado;
    private String primerApellidoAfiliado;
    private String segundoNombreAfiliado;
    private String segundoApellidoAfiliado;

    private String numeroDocumentoAfiliado;
    private String regimenAfiliado;

    private Integer idItem;
    private Integer tipoTecnologiaItem;
    private String nombreItem;

    private String cantidadOrdenada;
    private String cantidadDireccionada;
    private String tipoPrestacion;
    private String notaAuditoria;
    private String ipsDirecciona;
    private MpDireccionamientoEntregado entrega;

    public MpPrescripcionSuministro() {
    }

    public Integer getIdPrescripcion() {
        return idPrescripcion;
    }

    public void setIdPrescripcion(Integer idPrescripcion) {
        this.idPrescripcion = idPrescripcion;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getCodigoAmbitoAtencion() {
        return codigoAmbitoAtencion;
    }

    public void setCodigoAmbitoAtencion(String codigoAmbitoAtencion) {
        this.codigoAmbitoAtencion = codigoAmbitoAtencion;
    }

    public Date getFechaPrescripcion() {
        return fechaPrescripcion;
    }

    public void setFechaPrescripcion(Date fechaPrescripcion) {
        this.fechaPrescripcion = fechaPrescripcion;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public String getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(String idSuministro) {
        this.idSuministro = idSuministro;
    }

    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getPrimerNombreAfiliado() {
        return primerNombreAfiliado;
    }

    public void setPrimerNombreAfiliado(String primerNombreAfiliado) {
        this.primerNombreAfiliado = primerNombreAfiliado;
    }

    public String getPrimerApellidoAfiliado() {
        return primerApellidoAfiliado;
    }

    public void setPrimerApellidoAfiliado(String primerApellidoAfiliado) {
        this.primerApellidoAfiliado = primerApellidoAfiliado;
    }

    public String getSegundoNombreAfiliado() {
        return segundoNombreAfiliado;
    }

    public void setSegundoNombreAfiliado(String segundoNombreAfiliado) {
        this.segundoNombreAfiliado = segundoNombreAfiliado;
    }

    public String getSegundoApellidoAfiliado() {
        return segundoApellidoAfiliado;
    }

    public void setSegundoApellidoAfiliado(String segundoApellidoAfiliado) {
        this.segundoApellidoAfiliado = segundoApellidoAfiliado;
    }

    public String getNumeroDocumentoAfiliado() {
        return numeroDocumentoAfiliado;
    }

    public void setNumeroDocumentoAfiliado(String numeroDocumentoAfiliado) {
        this.numeroDocumentoAfiliado = numeroDocumentoAfiliado;
    }

    public String getRegimenAfiliado() {
        return regimenAfiliado;
    }

    public void setRegimenAfiliado(String regimenAfiliado) {
        this.regimenAfiliado = regimenAfiliado;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getTipoTecnologiaItem() {
        return tipoTecnologiaItem;
    }

    public void setTipoTecnologiaItem(Integer tipoTecnologiaItem) {
        this.tipoTecnologiaItem = tipoTecnologiaItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getCantidadOrdenada() {
        return cantidadOrdenada;
    }

    public void setCantidadOrdenada(String cantidadOrdenada) {
        this.cantidadOrdenada = cantidadOrdenada;
    }

    public String getCantidadDireccionada() {
        return cantidadDireccionada;
    }

    public void setCantidadDireccionada(String cantidadDireccionada) {
        this.cantidadDireccionada = cantidadDireccionada;
    }

    public String getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(String tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public String getNotaAuditoria() {
        return notaAuditoria;
    }

    public void setNotaAuditoria(String notaAuditoria) {
        this.notaAuditoria = notaAuditoria;
    }

    public String getIpsDirecciona() {
        return ipsDirecciona;
    }

    public void setIpsDirecciona(String ipsDirecciona) {
        this.ipsDirecciona = ipsDirecciona;
    }

    public MpDireccionamientoEntregado getEntrega() {
        return entrega;
    }

    public void setEntrega(MpDireccionamientoEntregado entrega) {
        this.entrega = entrega;
    }

    @Override
    public String toString() {
        return "MpPrescripcionSuministro{" + "idPrescripcion=" + idPrescripcion + ", numeroPrescripcion=" + numeroPrescripcion + ", codigoAmbitoAtencion=" + codigoAmbitoAtencion + ", fechaPrescripcion=" + fechaPrescripcion + ", maDiagnosticoPrincipalId=" + maDiagnosticoPrincipalId + ", maDiagnosticoPrincipalCodigo=" + maDiagnosticoPrincipalCodigo + ", maDiagnosticoPrincipalValor=" + maDiagnosticoPrincipalValor + ", idAfiliado=" + idAfiliado + ", primerNombreAfiliado=" + primerNombreAfiliado + ", primerApellidoAfiliado=" + primerApellidoAfiliado + ", segundoNombreAfiliado=" + segundoNombreAfiliado + ", segundoApellidoAfiliado=" + segundoApellidoAfiliado + ", numeroDocumentoAfiliado=" + numeroDocumentoAfiliado + ", regimenAfiliado=" + regimenAfiliado + ", idItem=" + idItem + ", tipoTecnologiaItem=" + tipoTecnologiaItem + ", nombreItem=" + nombreItem + ", cantidadOrdenada=" + cantidadOrdenada + ", cantidadDireccionada=" + cantidadDireccionada + ", tipoPrestacion=" + tipoPrestacion + ", notaAuditoria=" + notaAuditoria + '}';
    }

}
