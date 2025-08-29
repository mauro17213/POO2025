/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class AsegAfiliadoCertificado extends Auditoria {

    private Integer id;
    private String idAfiliado;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Date fechaNacimiento;
    private Integer maeSubgrupoSisbenId;
    private String maeSubgrupoSisbenCodigo;
    private String maeSubgrupoSisbenValor;
    private int maeNivelId;
    private String maeNivelCodigo;
    private String maeNivelValor;
    private int maeGrupoPoblacionalId;
    private String maeGrupoPoblacionalCodigo;
    private String maeGrupoPoblacionalValor;
    private Date fechaAfiliacion;
    private int maeRegimenId;
    private String maeRegimenValor;
    private String maeRegimenDescripcion;
    private int maeEstadoAfiliacionId;
    private String maeEstadoAfiliacionCodigo;
    private String maeEstadoAfiliacionValor;
    private int modeloLiquidacion;
    private Date fechaRetiro;
    private int semanaAfiliacion;
    private String direccionAfiliado;
    private String telefonoAfiliado;
    private String celularAfiliado;
    private int afiliacionUbicacionId;
    private String afiliacionUbicacionValor;
    private int residenciaUbicacionId;
    private String residenciaUbicacionValor;
    private Integer cntPrestadorSedesId;
    private String cntPrestadorSedesValor;
    private String correoElectronico;
    private int tipo;
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;
    private int diasVigencia;
    private String nombreArchivo;
    private String ruta;
    private AsegAfiliado asegAfiliado;
    private String categoriaIbc;

    public AsegAfiliadoCertificado() {
    }

    public AsegAfiliadoCertificado(Integer id) {
        this.id = id;
    }

    public AsegAfiliadoCertificado(Integer id, String idAfiliado, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String primerApellido, String primerNombre, Date fechaNacimiento, int maeNivelId, String maeNivelCodigo, String maeNivelValor, int maeGrupoPoblacionalId, String maeGrupoPoblacionalCodigo, String maeGrupoPoblacionalValor, Date fechaAfiliacion, int maeRegimenId, String maeRegimenValor, String maeRegimenDescripcion, int maeEstadoAfiliacionId, String maeEstadoAfiliacionCodigo, String maeEstadoAfiliacionValor, int modeloLiquidacion, int semanaAfiliacion, int afiliacionUbicacionId, String afiliacionUbicacionValor, int residenciaUbicacionId, String residenciaUbicacionValor, int tipo, Date fechaInicioVigencia, Date fechaFinVigencia, int diasVigencia, String nombreArchivo, String ruta, String categoriaIbc) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.maeNivelId = maeNivelId;
        this.maeNivelCodigo = maeNivelCodigo;
        this.maeNivelValor = maeNivelValor;
        this.maeGrupoPoblacionalId = maeGrupoPoblacionalId;
        this.maeGrupoPoblacionalCodigo = maeGrupoPoblacionalCodigo;
        this.maeGrupoPoblacionalValor = maeGrupoPoblacionalValor;
        this.fechaAfiliacion = fechaAfiliacion;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenValor = maeRegimenValor;
        this.maeRegimenDescripcion = maeRegimenDescripcion;
        this.maeEstadoAfiliacionId = maeEstadoAfiliacionId;
        this.maeEstadoAfiliacionCodigo = maeEstadoAfiliacionCodigo;
        this.maeEstadoAfiliacionValor = maeEstadoAfiliacionValor;
        this.modeloLiquidacion = modeloLiquidacion;
        this.semanaAfiliacion = semanaAfiliacion;
        this.afiliacionUbicacionId = afiliacionUbicacionId;
        this.afiliacionUbicacionValor = afiliacionUbicacionValor;
        this.residenciaUbicacionId = residenciaUbicacionId;
        this.residenciaUbicacionValor = residenciaUbicacionValor;
        this.tipo = tipo;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.diasVigencia = diasVigencia;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.categoriaIbc = categoriaIbc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getMaeSubgrupoSisbenId() {
        return maeSubgrupoSisbenId;
    }

    public void setMaeSubgrupoSisbenId(Integer maeSubgrupoSisbenId) {
        this.maeSubgrupoSisbenId = maeSubgrupoSisbenId;
    }

    public String getMaeSubgrupoSisbenCodigo() {
        return maeSubgrupoSisbenCodigo;
    }

    public void setMaeSubgrupoSisbenCodigo(String maeSubgrupoSisbenCodigo) {
        this.maeSubgrupoSisbenCodigo = maeSubgrupoSisbenCodigo;
    }

    public String getMaeSubgrupoSisbenValor() {
        return maeSubgrupoSisbenValor;
    }

    public void setMaeSubgrupoSisbenValor(String maeSubgrupoSisbenValor) {
        this.maeSubgrupoSisbenValor = maeSubgrupoSisbenValor;
    }

    public int getMaeNivelId() {
        return maeNivelId;
    }

    public void setMaeNivelId(int maeNivelId) {
        this.maeNivelId = maeNivelId;
    }

    public String getMaeNivelCodigo() {
        return maeNivelCodigo;
    }

    public void setMaeNivelCodigo(String maeNivelCodigo) {
        this.maeNivelCodigo = maeNivelCodigo;
    }

    public String getMaeNivelValor() {
        return maeNivelValor;
    }

    public void setMaeNivelValor(String maeNivelValor) {
        this.maeNivelValor = maeNivelValor;
    }

    public int getMaeGrupoPoblacionalId() {
        return maeGrupoPoblacionalId;
    }

    public void setMaeGrupoPoblacionalId(int maeGrupoPoblacionalId) {
        this.maeGrupoPoblacionalId = maeGrupoPoblacionalId;
    }

    public String getMaeGrupoPoblacionalCodigo() {
        return maeGrupoPoblacionalCodigo;
    }

    public void setMaeGrupoPoblacionalCodigo(String maeGrupoPoblacionalCodigo) {
        this.maeGrupoPoblacionalCodigo = maeGrupoPoblacionalCodigo;
    }

    public String getMaeGrupoPoblacionalValor() {
        return maeGrupoPoblacionalValor;
    }

    public void setMaeGrupoPoblacionalValor(String maeGrupoPoblacionalValor) {
        this.maeGrupoPoblacionalValor = maeGrupoPoblacionalValor;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public String getMaeRegimenDescripcion() {
        return maeRegimenDescripcion;
    }

    public void setMaeRegimenDescripcion(String maeRegimenDescripcion) {
        this.maeRegimenDescripcion = maeRegimenDescripcion;
    }

    public int getMaeEstadoAfiliacionId() {
        return maeEstadoAfiliacionId;
    }

    public void setMaeEstadoAfiliacionId(int maeEstadoAfiliacionId) {
        this.maeEstadoAfiliacionId = maeEstadoAfiliacionId;
    }

    public String getMaeEstadoAfiliacionCodigo() {
        return maeEstadoAfiliacionCodigo;
    }

    public void setMaeEstadoAfiliacionCodigo(String maeEstadoAfiliacionCodigo) {
        this.maeEstadoAfiliacionCodigo = maeEstadoAfiliacionCodigo;
    }

    public String getMaeEstadoAfiliacionValor() {
        return maeEstadoAfiliacionValor;
    }

    public void setMaeEstadoAfiliacionValor(String maeEstadoAfiliacionValor) {
        this.maeEstadoAfiliacionValor = maeEstadoAfiliacionValor;
    }

    public int getModeloLiquidacion() {
        return modeloLiquidacion;
    }

    public void setModeloLiquidacion(int modeloLiquidacion) {
        this.modeloLiquidacion = modeloLiquidacion;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public int getSemanaAfiliacion() {
        return semanaAfiliacion;
    }

    public void setSemanaAfiliacion(int semanaAfiliacion) {
        this.semanaAfiliacion = semanaAfiliacion;
    }

    public String getDireccionAfiliado() {
        return direccionAfiliado;
    }

    public void setDireccionAfiliado(String direccionAfiliado) {
        this.direccionAfiliado = direccionAfiliado;
    }

    public String getTelefonoAfiliado() {
        return telefonoAfiliado;
    }

    public void setTelefonoAfiliado(String telefonoAfiliado) {
        this.telefonoAfiliado = telefonoAfiliado;
    }

    public String getCelularAfiliado() {
        return celularAfiliado;
    }

    public void setCelularAfiliado(String celularAfiliado) {
        this.celularAfiliado = celularAfiliado;
    }

    public int getAfiliacionUbicacionId() {
        return afiliacionUbicacionId;
    }

    public void setAfiliacionUbicacionId(int afiliacionUbicacionId) {
        this.afiliacionUbicacionId = afiliacionUbicacionId;
    }

    public String getAfiliacionUbicacionValor() {
        return afiliacionUbicacionValor;
    }

    public void setAfiliacionUbicacionValor(String afiliacionUbicacionValor) {
        this.afiliacionUbicacionValor = afiliacionUbicacionValor;
    }

    public int getResidenciaUbicacionId() {
        return residenciaUbicacionId;
    }

    public void setResidenciaUbicacionId(int residenciaUbicacionId) {
        this.residenciaUbicacionId = residenciaUbicacionId;
    }

    public String getResidenciaUbicacionValor() {
        return residenciaUbicacionValor;
    }

    public void setResidenciaUbicacionValor(String residenciaUbicacionValor) {
        this.residenciaUbicacionValor = residenciaUbicacionValor;
    }

    public Integer getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(Integer cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public String getCntPrestadorSedesValor() {
        return cntPrestadorSedesValor;
    }

    public void setCntPrestadorSedesValor(String cntPrestadorSedesValor) {
        this.cntPrestadorSedesValor = cntPrestadorSedesValor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public int getDiasVigencia() {
        return diasVigencia;
    }

    public void setDiasVigencia(int diasVigencia) {
        this.diasVigencia = diasVigencia;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the asegAfiliado
     */
    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    /**
     * @param asegAfiliado the asegAfiliado to set
     */
    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public String getCategoriaIbc() {
        return categoriaIbc;
    }

    public void setCategoriaIbc(String categoriaIbc) {
        this.categoriaIbc = categoriaIbc;
    }

    @Override
    public String toString() {
        return "AsegAfiliadoCertificado{" + "id=" + id + ", idAfiliado=" + idAfiliado + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", fechaNacimiento=" + fechaNacimiento + ", maeSubgrupoSisbenId=" + maeSubgrupoSisbenId + ", maeSubgrupoSisbenCodigo=" + maeSubgrupoSisbenCodigo + ", maeSubgrupoSisbenValor=" + maeSubgrupoSisbenValor + ", maeNivelId=" + maeNivelId + ", maeNivelCodigo=" + maeNivelCodigo + ", maeNivelValor=" + maeNivelValor + ", maeGrupoPoblacionalId=" + maeGrupoPoblacionalId + ", maeGrupoPoblacionalCodigo=" + maeGrupoPoblacionalCodigo + ", maeGrupoPoblacionalValor=" + maeGrupoPoblacionalValor + ", fechaAfiliacion=" + fechaAfiliacion + ", maeRegimenId=" + maeRegimenId + ", maeRegimenValor=" + maeRegimenValor + ", maeRegimenDescripcion=" + maeRegimenDescripcion + ", maeEstadoAfiliacionId=" + maeEstadoAfiliacionId + ", maeEstadoAfiliacionCodigo=" + maeEstadoAfiliacionCodigo + ", maeEstadoAfiliacionValor=" + maeEstadoAfiliacionValor + ", modeloLiquidacion=" + modeloLiquidacion + ", fechaRetiro=" + fechaRetiro + ", semanaAfiliacion=" + semanaAfiliacion + ", direccionAfiliado=" + direccionAfiliado + ", telefonoAfiliado=" + telefonoAfiliado + ", celularAfiliado=" + celularAfiliado + ", afiliacionUbicacionId=" + afiliacionUbicacionId + ", afiliacionUbicacionValor=" + afiliacionUbicacionValor + ", residenciaUbicacionId=" + residenciaUbicacionId + ", residenciaUbicacionValor=" + residenciaUbicacionValor + ", cntPrestadorSedesId=" + cntPrestadorSedesId + ", cntPrestadorSedesValor=" + cntPrestadorSedesValor + ", correoElectronico=" + correoElectronico + ", tipo=" + tipo + ", fechaInicioVigencia=" + fechaInicioVigencia + ", fechaFinVigencia=" + fechaFinVigencia + ", diasVigencia=" + diasVigencia + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", asegAfiliado=" + asegAfiliado + ", categoriaIbc=" + categoriaIbc + '}';
    }
}
