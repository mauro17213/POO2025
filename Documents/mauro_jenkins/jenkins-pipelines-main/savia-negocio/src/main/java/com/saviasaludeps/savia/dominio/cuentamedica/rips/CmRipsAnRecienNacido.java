package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class CmRipsAnRecienNacido extends Auditoria {

    private Integer id;
    private int fila;
    private String numeroFactura;
    private String codigoReps;
    private String maeTipoDocumentoMadreCodigo;
    private Integer maeTipoDocumentoMadreId;
    private String maeTipoDocumentoMadreValor;
    private String documentoAfiliadoMadre;
    private Date fechaNacimiento;
    private Date horaNacimiento;
    private Integer edadGestacion;
    private String maeControlPenatalCodigo;
    private Integer maeControlPenatalId;
    private String maeControlPenatalValor;
    private String maeSexoCodigo;
    private Integer maeSexoId;
    private String maeSexoValor;
    private BigDecimal peso;
    private String maDiagnosticoPrincipalCodigo;
    private Integer maDiagnosticoPrincipalId;
    private String maDiagnosticoPrincipalValor;
    private String maCausaMuerteDiagnosticoCodigo;
    private Integer maCausaMuerteDiagnosticoId;
    private String maCausaMuerteDiagnosticoValor;
    private Date fechaMuerte;
    private Date horaMuerte;
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

    public String getDocumentoAfiliadoMadre() {
        return documentoAfiliadoMadre;
    }

    public void setDocumentoAfiliadoMadre(String documentoAfiliadoMadre) {
        this.documentoAfiliadoMadre = documentoAfiliadoMadre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getHoraNacimiento() {
        return horaNacimiento;
    }

    public void setHoraNacimiento(Date horaNacimiento) {
        this.horaNacimiento = horaNacimiento;
    }

    public Integer getEdadGestacion() {
        return edadGestacion;
    }

    public void setEdadGestacion(Integer edadGestacion) {
        this.edadGestacion = edadGestacion;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Date getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Date fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public Date getHoraMuerte() {
        return horaMuerte;
    }

    public void setHoraMuerte(Date horaMuerte) {
        this.horaMuerte = horaMuerte;
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

    public String getMaeTipoDocumentoMadreCodigo() {
        return maeTipoDocumentoMadreCodigo;
    }

    public void setMaeTipoDocumentoMadreCodigo(String maeTipoDocumentoMadreCodigo) {
        this.maeTipoDocumentoMadreCodigo = maeTipoDocumentoMadreCodigo;
    }

    public Integer getMaeTipoDocumentoMadreId() {
        return maeTipoDocumentoMadreId;
    }

    public void setMaeTipoDocumentoMadreId(Integer maeTipoDocumentoMadreId) {
        this.maeTipoDocumentoMadreId = maeTipoDocumentoMadreId;
    }

    public String getMaeTipoDocumentoMadreValor() {
        return maeTipoDocumentoMadreValor;
    }

    public void setMaeTipoDocumentoMadreValor(String maeTipoDocumentoMadreValor) {
        this.maeTipoDocumentoMadreValor = maeTipoDocumentoMadreValor;
    }

    public String getMaeControlPenatalCodigo() {
        return maeControlPenatalCodigo;
    }

    public void setMaeControlPenatalCodigo(String maeControlPenatalCodigo) {
        this.maeControlPenatalCodigo = maeControlPenatalCodigo;
    }

    public Integer getMaeControlPenatalId() {
        return maeControlPenatalId;
    }

    public void setMaeControlPenatalId(Integer maeControlPenatalId) {
        this.maeControlPenatalId = maeControlPenatalId;
    }

    public String getMaeControlPenatalValor() {
        return maeControlPenatalValor;
    }

    public void setMaeControlPenatalValor(String maeControlPenatalValor) {
        this.maeControlPenatalValor = maeControlPenatalValor;
    }

    public String getMaeSexoCodigo() {
        return maeSexoCodigo;
    }

    public void setMaeSexoCodigo(String maeSexoCodigo) {
        this.maeSexoCodigo = maeSexoCodigo;
    }

    public Integer getMaeSexoId() {
        return maeSexoId;
    }

    public void setMaeSexoId(Integer maeSexoId) {
        this.maeSexoId = maeSexoId;
    }

    public String getMaeSexoValor() {
        return maeSexoValor;
    }

    public void setMaeSexoValor(String maeSexoValor) {
        this.maeSexoValor = maeSexoValor;
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

    public String getMaCausaMuerteDiagnosticoCodigo() {
        return maCausaMuerteDiagnosticoCodigo;
    }

    public void setMaCausaMuerteDiagnosticoCodigo(String maCausaMuerteDiagnosticoCodigo) {
        this.maCausaMuerteDiagnosticoCodigo = maCausaMuerteDiagnosticoCodigo;
    }

    public Integer getMaCausaMuerteDiagnosticoId() {
        return maCausaMuerteDiagnosticoId;
    }

    public void setMaCausaMuerteDiagnosticoId(Integer maCausaMuerteDiagnosticoId) {
        this.maCausaMuerteDiagnosticoId = maCausaMuerteDiagnosticoId;
    }

    public String getMaCausaMuerteDiagnosticoValor() {
        return maCausaMuerteDiagnosticoValor;
    }

    public void setMaCausaMuerteDiagnosticoValor(String maCausaMuerteDiagnosticoValor) {
        this.maCausaMuerteDiagnosticoValor = maCausaMuerteDiagnosticoValor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
}
