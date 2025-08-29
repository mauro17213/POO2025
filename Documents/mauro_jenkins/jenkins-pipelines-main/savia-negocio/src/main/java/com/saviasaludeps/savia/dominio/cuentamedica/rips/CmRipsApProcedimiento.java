package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class CmRipsApProcedimiento extends Auditoria {

    private Integer id;
    private int fila;
    private String numeroFactura;
    private String codigoReps;
    private String maeTipoDocumentoCodigo;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoValor;
    private String documentoAfiliado;
    private Date fechaProcedimiento;
    private String autorizacion;
    private String maTecnologiaCodigo;
    private int maTecnologiaId;
    private String maTecnologiaValor;
    private String maeAmbitoAtencionCodigo;
    private Integer maeAmbitoAtencionId;
    private String maeAmbitoAtencionValor;
    private String maeFinalidadProcedimientoCodigo;
    private Integer maeFinalidadProcedimientoId;
    private String maeFinalidadProcedimientoValor;
    private String maePersonalAtiendeCodigo;
    private Integer maePersonalAtiendeId;
    private String maePersonalAtiendeValor;
    private String maDiagnosticoPrincipalCodigo;
    private Integer maDiagnosticoPrincipalId;
    private String maDiagnosticoPrincipalValor;
    private String maDiagnosticoRelacionado1Codigo;
    private Integer maDiagnosticoRelacionado1Id;
    private String maDiagnosticoRelacionado1Valor;
    private String maDiagnosticoRelacionado2Codigo;
    private Integer maDiagnosticoRelacionado2Id;
    private String maDiagnosticoRelacionado2Valor;
    private String maeFormaActoCodigo;
    private Integer maeFormaActoId;
    private String maeFormaActoValor;
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

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCodigoReps() {
        return codigoReps;
    }

    public void setCodigoReps(String codigoReps) {
        this.codigoReps = codigoReps;
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

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public Date getFechaProcedimiento() {
        return fechaProcedimiento;
    }

    public void setFechaProcedimiento(Date fechaProcedimiento) {
        this.fechaProcedimiento = fechaProcedimiento;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
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

    public String getMaeAmbitoAtencionCodigo() {
        return maeAmbitoAtencionCodigo;
    }

    public void setMaeAmbitoAtencionCodigo(String maeAmbitoAtencionCodigo) {
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
    }

    public Integer getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(Integer maeAmbitoAtencionId) {
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
    }

    public String getMaeAmbitoAtencionValor() {
        return maeAmbitoAtencionValor;
    }

    public void setMaeAmbitoAtencionValor(String maeAmbitoAtencionValor) {
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
    }

    public String getMaeFinalidadProcedimientoCodigo() {
        return maeFinalidadProcedimientoCodigo;
    }

    public void setMaeFinalidadProcedimientoCodigo(String maeFinalidadProcedimientoCodigo) {
        this.maeFinalidadProcedimientoCodigo = maeFinalidadProcedimientoCodigo;
    }

    public Integer getMaeFinalidadProcedimientoId() {
        return maeFinalidadProcedimientoId;
    }

    public void setMaeFinalidadProcedimientoId(Integer maeFinalidadProcedimientoId) {
        this.maeFinalidadProcedimientoId = maeFinalidadProcedimientoId;
    }

    public String getMaeFinalidadProcedimientoValor() {
        return maeFinalidadProcedimientoValor;
    }

    public void setMaeFinalidadProcedimientoValor(String maeFinalidadProcedimientoValor) {
        this.maeFinalidadProcedimientoValor = maeFinalidadProcedimientoValor;
    }

    public String getMaePersonalAtiendeCodigo() {
        return maePersonalAtiendeCodigo;
    }

    public void setMaePersonalAtiendeCodigo(String maePersonalAtiendeCodigo) {
        this.maePersonalAtiendeCodigo = maePersonalAtiendeCodigo;
    }

    public Integer getMaePersonalAtiendeId() {
        return maePersonalAtiendeId;
    }

    public void setMaePersonalAtiendeId(Integer maePersonalAtiendeId) {
        this.maePersonalAtiendeId = maePersonalAtiendeId;
    }

    public String getMaePersonalAtiendeValor() {
        return maePersonalAtiendeValor;
    }

    public void setMaePersonalAtiendeValor(String maePersonalAtiendeValor) {
        this.maePersonalAtiendeValor = maePersonalAtiendeValor;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public String getMaDiagnosticoRelacionado1Codigo() {
        return maDiagnosticoRelacionado1Codigo;
    }

    public void setMaDiagnosticoRelacionado1Codigo(String maDiagnosticoRelacionado1Codigo) {
        this.maDiagnosticoRelacionado1Codigo = maDiagnosticoRelacionado1Codigo;
    }

    public Integer getMaDiagnosticoRelacionado1Id() {
        return maDiagnosticoRelacionado1Id;
    }

    public void setMaDiagnosticoRelacionado1Id(Integer maDiagnosticoRelacionado1Id) {
        this.maDiagnosticoRelacionado1Id = maDiagnosticoRelacionado1Id;
    }

    public String getMaDiagnosticoRelacionado1Valor() {
        return maDiagnosticoRelacionado1Valor;
    }

    public void setMaDiagnosticoRelacionado1Valor(String maDiagnosticoRelacionado1Valor) {
        this.maDiagnosticoRelacionado1Valor = maDiagnosticoRelacionado1Valor;
    }

    public String getMaDiagnosticoRelacionado2Codigo() {
        return maDiagnosticoRelacionado2Codigo;
    }

    public void setMaDiagnosticoRelacionado2Codigo(String maDiagnosticoRelacionado2Codigo) {
        this.maDiagnosticoRelacionado2Codigo = maDiagnosticoRelacionado2Codigo;
    }

    public Integer getMaDiagnosticoRelacionado2Id() {
        return maDiagnosticoRelacionado2Id;
    }

    public void setMaDiagnosticoRelacionado2Id(Integer maDiagnosticoRelacionado2Id) {
        this.maDiagnosticoRelacionado2Id = maDiagnosticoRelacionado2Id;
    }

    public String getMaDiagnosticoRelacionado2Valor() {
        return maDiagnosticoRelacionado2Valor;
    }

    public void setMaDiagnosticoRelacionado2Valor(String maDiagnosticoRelacionado2Valor) {
        this.maDiagnosticoRelacionado2Valor = maDiagnosticoRelacionado2Valor;
    }

    public String getMaeFormaActoCodigo() {
        return maeFormaActoCodigo;
    }

    public void setMaeFormaActoCodigo(String maeFormaActoCodigo) {
        this.maeFormaActoCodigo = maeFormaActoCodigo;
    }

    public Integer getMaeFormaActoId() {
        return maeFormaActoId;
    }

    public void setMaeFormaActoId(Integer maeFormaActoId) {
        this.maeFormaActoId = maeFormaActoId;
    }

    public String getMaeFormaActoValor() {
        return maeFormaActoValor;
    }

    public void setMaeFormaActoValor(String maeFormaActoValor) {
        this.maeFormaActoValor = maeFormaActoValor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return "CmRipsApProcedimiento{" + "idcmRipsCargaApProcedimiento=" + id + ", numeroFactura=" + numeroFactura + ", codigoReps=" + codigoReps + ", documentoAfiliado=" + documentoAfiliado + ", fechaProcedimiento=" + fechaProcedimiento + ", autorizacion=" + autorizacion + ", cmRipsCarga=" + cmRipsCarga + '}';
    }

}
