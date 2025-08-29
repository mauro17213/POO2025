package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class CmRipsAhHospitalizacion extends Auditoria {

    private Integer id;
    private int fila;
    private String numeroFactura;
    private String codigoReps;
    private String maeTipoDocumentoCodigo;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoValor;
    private String documentoAfiliado;
    private String maeViaIngresoCodigo;
    private Integer maeViaIngresoId;
    private String maeViaIngresoValor;
    private Date fechaIngreso;
    private Date horaIngreso;
    private String autorizacion;
    private String maeCausaExternaCodigo;
    private Integer maeCausaExternaId;
    private String maeCausaExternaValor;
    private String maDiagnosticoIngresoCodigo;
    private Integer maDiagnosticoIngresoId;
    private String maDiagnosticoIngresoValor;
    private String maDiagnosticoEgresoCodigo;
    private Integer maDiagnosticoEgresoId;
    private String maDiagnosticoEgresoValor;
    private String maDiagnosticoRelacionado1Codigo;
    private Integer maDiagnosticoRelacionado1Id;
    private String maDiagnosticoRelacionado1Valor;
    private String maDiagnosticoRelacionado2Codigo;
    private Integer maDiagnosticoRelacionado2Id;
    private String maDiagnosticoRelacionado2Valor;
    private String maDiagnosticoRelacionado3Codigo;
    private Integer maDiagnosticoRelacionado3Id;
    private String maDiagnosticoRelacionado3Valor;
    private String maDiagnosticoComplicacionCodigo;
    private Integer maDiagnosticoComplicacionId;
    private String maDiagnosticoComplicacionValor;
    private String maeEstadoSalidaCodigo;
    private Integer maeEstadoSalidaId;
    private String maeEstadoSalidaValor;
    private String maDiagnosticoMuerteCodigo;
    private Integer maDiagnosticoMuerteId;
    private String maDiagnosticoMuerteValor;
    private Date fechaSalida;
    private Date horaSalida;
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

    public String getMaeViaIngresoCodigo() {
        return maeViaIngresoCodigo;
    }

    public void setMaeViaIngresoCodigo(String maeViaIngresoCodigo) {
        this.maeViaIngresoCodigo = maeViaIngresoCodigo;
    }

    public Integer getMaeViaIngresoId() {
        return maeViaIngresoId;
    }

    public void setMaeViaIngresoId(Integer maeViaIngresoId) {
        this.maeViaIngresoId = maeViaIngresoId;
    }

    public String getMaeViaIngresoValor() {
        return maeViaIngresoValor;
    }

    public void setMaeViaIngresoValor(String maeViaIngresoValor) {
        this.maeViaIngresoValor = maeViaIngresoValor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
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

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
    }

    public String getMaDiagnosticoIngresoCodigo() {
        return maDiagnosticoIngresoCodigo;
    }

    public void setMaDiagnosticoIngresoCodigo(String maDiagnosticoIngresoCodigo) {
        this.maDiagnosticoIngresoCodigo = maDiagnosticoIngresoCodigo;
    }

    public Integer getMaDiagnosticoIngresoId() {
        return maDiagnosticoIngresoId;
    }

    public void setMaDiagnosticoIngresoId(Integer maDiagnosticoIngresoId) {
        this.maDiagnosticoIngresoId = maDiagnosticoIngresoId;
    }

    public String getMaDiagnosticoIngresoValor() {
        return maDiagnosticoIngresoValor;
    }

    public void setMaDiagnosticoIngresoValor(String maDiagnosticoIngresoValor) {
        this.maDiagnosticoIngresoValor = maDiagnosticoIngresoValor;
    }

    public String getMaDiagnosticoEgresoCodigo() {
        return maDiagnosticoEgresoCodigo;
    }

    public void setMaDiagnosticoEgresoCodigo(String maDiagnosticoEgresoCodigo) {
        this.maDiagnosticoEgresoCodigo = maDiagnosticoEgresoCodigo;
    }

    public Integer getMaDiagnosticoEgresoId() {
        return maDiagnosticoEgresoId;
    }

    public void setMaDiagnosticoEgresoId(Integer maDiagnosticoEgresoId) {
        this.maDiagnosticoEgresoId = maDiagnosticoEgresoId;
    }

    public String getMaDiagnosticoEgresoValor() {
        return maDiagnosticoEgresoValor;
    }

    public void setMaDiagnosticoEgresoValor(String maDiagnosticoEgresoValor) {
        this.maDiagnosticoEgresoValor = maDiagnosticoEgresoValor;
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

    public String getMaDiagnosticoComplicacionCodigo() {
        return maDiagnosticoComplicacionCodigo;
    }

    public void setMaDiagnosticoComplicacionCodigo(String maDiagnosticoComplicacionCodigo) {
        this.maDiagnosticoComplicacionCodigo = maDiagnosticoComplicacionCodigo;
    }

    public Integer getMaDiagnosticoComplicacionId() {
        return maDiagnosticoComplicacionId;
    }

    public void setMaDiagnosticoComplicacionId(Integer maDiagnosticoComplicacionId) {
        this.maDiagnosticoComplicacionId = maDiagnosticoComplicacionId;
    }

    public String getMaDiagnosticoComplicacionValor() {
        return maDiagnosticoComplicacionValor;
    }

    public void setMaDiagnosticoComplicacionValor(String maDiagnosticoComplicacionValor) {
        this.maDiagnosticoComplicacionValor = maDiagnosticoComplicacionValor;
    }

    public String getMaeEstadoSalidaCodigo() {
        return maeEstadoSalidaCodigo;
    }

    public void setMaeEstadoSalidaCodigo(String maeEstadoSalidaCodigo) {
        this.maeEstadoSalidaCodigo = maeEstadoSalidaCodigo;
    }

    public Integer getMaeEstadoSalidaId() {
        return maeEstadoSalidaId;
    }

    public void setMaeEstadoSalidaId(Integer maeEstadoSalidaId) {
        this.maeEstadoSalidaId = maeEstadoSalidaId;
    }

    public String getMaeEstadoSalidaValor() {
        return maeEstadoSalidaValor;
    }

    public void setMaeEstadoSalidaValor(String maeEstadoSalidaValor) {
        this.maeEstadoSalidaValor = maeEstadoSalidaValor;
    }

    public String getMaDiagnosticoMuerteCodigo() {
        return maDiagnosticoMuerteCodigo;
    }

    public void setMaDiagnosticoMuerteCodigo(String maDiagnosticoMuerteCodigo) {
        this.maDiagnosticoMuerteCodigo = maDiagnosticoMuerteCodigo;
    }

    public Integer getMaDiagnosticoMuerteId() {
        return maDiagnosticoMuerteId;
    }

    public void setMaDiagnosticoMuerteId(Integer maDiagnosticoMuerteId) {
        this.maDiagnosticoMuerteId = maDiagnosticoMuerteId;
    }

    public String getMaDiagnosticoMuerteValor() {
        return maDiagnosticoMuerteValor;
    }

    public void setMaDiagnosticoMuerteValor(String maDiagnosticoMuerteValor) {
        this.maDiagnosticoMuerteValor = maDiagnosticoMuerteValor;
    }

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return "CmRipsAhHospitalizacion{" + "id=" + id + ", numeroFactura=" + numeroFactura + ", codigoReps=" + codigoReps + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", cmRipsCarga=" + cmRipsCarga.getId() + '}';
    }

}
