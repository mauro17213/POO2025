package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class CmRipsAcConsulta extends Auditoria {

    private Integer id;
    private int fila;
    private String numFactura;
    private String codigoReps;
    private String maeTipoDocumentoCodigo;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoValor;
    private String maTecnologiaCodigo;
    private Integer maTecnologiaId;
    private String maTecnologiaValor;
    private String documentoAfiliado;
    private Date fechaConsulta;
    private String autorizacion;
    private String maeFinalidadConsultaCodigo;
    private Integer maeFinalidadConsultaId;
    private String maeFinalidadConsultaValor;
    private Integer maeCausaExternaId;
    private String maeCausaExternaCodigo;
    private String maeCausaExternaValor;
    private String maDiagnosticoPrincipalCodigo;
    private Integer maDiagnosticoPrincipalId;
    private String maDiagnosticoPrincipalValor;
    private String maDiagnosticoRelacionado1Codigo;
    private Integer maDiagnosticoRelacionado1Id;
    private String maDiagnosticoRelacionado1Valor;
    private String maDiagnosticoRelacionado2Codigo;
    private Integer maDiagnosticoRelacionado2Id;
    private String maDiagnosticoRelacionado2Valor;
    private String maDiagnosticoRelacionado3Codigo;
    private Integer maDiagnosticoRelacionado3Id;
    private String maDiagnosticoRelacionado3Valor;
    private String maeTipoDiagnosticoCodigo;
    private Integer maeTipoDiagnosticoId;
    private String maeTipoDiagnosticoValor;
    private BigDecimal valorConsulta;
    private BigDecimal valorCuotaModeradora;
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

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
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

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
    }

    public BigDecimal getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(BigDecimal valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
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

    public String getMaeFinalidadConsultaCodigo() {
        return maeFinalidadConsultaCodigo;
    }

    public void setMaeFinalidadConsultaCodigo(String maeFinalidadConsultaCodigo) {
        this.maeFinalidadConsultaCodigo = maeFinalidadConsultaCodigo;
    }

    public Integer getMaeFinalidadConsultaId() {
        return maeFinalidadConsultaId;
    }

    public void setMaeFinalidadConsultaId(Integer maeFinalidadConsultaId) {
        this.maeFinalidadConsultaId = maeFinalidadConsultaId;
    }

    public String getMaeFinalidadConsultaValor() {
        return maeFinalidadConsultaValor;
    }

    public void setMaeFinalidadConsultaValor(String maeFinalidadConsultaValor) {
        this.maeFinalidadConsultaValor = maeFinalidadConsultaValor;
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

    public String getMaDiagnosticoRelacionado3Codigo() {
        return maDiagnosticoRelacionado3Codigo;
    }

    public void setMaDiagnosticoRelacionado3Codigo(String maDiagnosticoRelacionado3Codigo) {
        this.maDiagnosticoRelacionado3Codigo = maDiagnosticoRelacionado3Codigo;
    }

    public Integer getMaDiagnosticoRelacionado3Id() {
        return maDiagnosticoRelacionado3Id;
    }

    public void setMaDiagnosticoRelacionado3Id(Integer maDiagnosticoRelacionado3Id) {
        this.maDiagnosticoRelacionado3Id = maDiagnosticoRelacionado3Id;
    }

    public String getMaDiagnosticoRelacionado3Valor() {
        return maDiagnosticoRelacionado3Valor;
    }

    public void setMaDiagnosticoRelacionado3Valor(String maDiagnosticoRelacionado3Valor) {
        this.maDiagnosticoRelacionado3Valor = maDiagnosticoRelacionado3Valor;
    }

    public String getMaeTipoDiagnosticoCodigo() {
        return maeTipoDiagnosticoCodigo;
    }

    public void setMaeTipoDiagnosticoCodigo(String maeTipoDiagnosticoCodigo) {
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
    }

    public Integer getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(Integer maeTipoDiagnosticoId) {
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
    }

    public String getMaeTipoDiagnosticoValor() {
        return maeTipoDiagnosticoValor;
    }

    public void setMaeTipoDiagnosticoValor(String maeTipoDiagnosticoValor) {
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return "CmRipsAcConsulta{" + "id=" + id + ", numFactura=" + numFactura + ", codigoReps=" + codigoReps + ", codTipoDocumento=" + maeTipoDocumentoCodigo + ", documentoAfiliado=" + documentoAfiliado + ", fechaConsulta=" + fechaConsulta + ", autorizacion=" + autorizacion + '}';
    }

}
