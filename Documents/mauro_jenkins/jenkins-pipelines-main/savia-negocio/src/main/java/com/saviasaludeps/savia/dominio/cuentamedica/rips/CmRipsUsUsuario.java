package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmRipsUsUsuario extends Auditoria {

    private Integer id;
    private int fila;
    private String maeTipoDocumentoCodigo;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoValor;
    private String documentoAfiliado;
    private String administradora;
    private String maeTipoUsuarioCodigo;
    private Integer maeTipoUsuarioId;
    private String maeTipoUsuarioValor;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Integer edad;
    private String codigoUnidadMedidaEdad;
    private String maeSexoCodigo;
    private Integer maeSexoId;
    private String maeSexoValor;
    private String departamentoCodigo;
    private Integer departamentoId;
    private String departamentoNombre;
    private String ciudadCodigo;
    private Integer ciudadId;
    private String ciudadNombre;
    private String maeZonaResidenciaCodigo;
    private Integer maeZonaResidenciaId;
    private String maeZonaResidenciaValor;
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

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public String getAdministradora() {
        return administradora;
    }

    public void setAdministradora(String administradora) {
        this.administradora = administradora;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCodigoUnidadMedidaEdad() {
        return codigoUnidadMedidaEdad;
    }

    public void setCodigoUnidadMedidaEdad(String codigoUnidadMedidaEdad) {
        this.codigoUnidadMedidaEdad = codigoUnidadMedidaEdad;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
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

    public String getMaeTipoUsuarioCodigo() {
        return maeTipoUsuarioCodigo;
    }

    public void setMaeTipoUsuarioCodigo(String maeTipoUsuarioCodigo) {
        this.maeTipoUsuarioCodigo = maeTipoUsuarioCodigo;
    }

    public Integer getMaeTipoUsuarioId() {
        return maeTipoUsuarioId;
    }

    public void setMaeTipoUsuarioId(Integer maeTipoUsuarioId) {
        this.maeTipoUsuarioId = maeTipoUsuarioId;
    }

    public String getMaeTipoUsuarioValor() {
        return maeTipoUsuarioValor;
    }

    public void setMaeTipoUsuarioValor(String maeTipoUsuarioValor) {
        this.maeTipoUsuarioValor = maeTipoUsuarioValor;
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

    public String getDepartamentoCodigo() {
        return departamentoCodigo;
    }

    public void setDepartamentoCodigo(String departamentoCodigo) {
        this.departamentoCodigo = departamentoCodigo;
    }

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
    }

    public String getCiudadCodigo() {
        return ciudadCodigo;
    }

    public void setCiudadCodigo(String ciudadCodigo) {
        this.ciudadCodigo = ciudadCodigo;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public String getMaeZonaResidenciaCodigo() {
        return maeZonaResidenciaCodigo;
    }

    public void setMaeZonaResidenciaCodigo(String maeZonaResidenciaCodigo) {
        this.maeZonaResidenciaCodigo = maeZonaResidenciaCodigo;
    }

    public Integer getMaeZonaResidenciaId() {
        return maeZonaResidenciaId;
    }

    public void setMaeZonaResidenciaId(Integer maeZonaResidenciaId) {
        this.maeZonaResidenciaId = maeZonaResidenciaId;
    }

    public String getMaeZonaResidenciaValor() {
        return maeZonaResidenciaValor;
    }

    public void setMaeZonaResidenciaValor(String maeZonaResidenciaValor) {
        this.maeZonaResidenciaValor = maeZonaResidenciaValor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return "CmRipsUsUsuario{" + "id=" + id + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", documentoAfiliado=" + documentoAfiliado + ", maeTipoUsuarioCodigo=" + maeTipoUsuarioCodigo + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", edad=" + edad + ", maeSexoCodigo=" + maeSexoCodigo + ", archivoRuta=" + archivoRuta + '}';
    }

}
