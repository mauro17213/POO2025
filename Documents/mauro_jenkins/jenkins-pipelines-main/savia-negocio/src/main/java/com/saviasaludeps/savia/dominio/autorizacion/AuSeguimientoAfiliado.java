/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public class AuSeguimientoAfiliado extends Auditoria {

    private Integer id;
    private Integer maeEstadoAfiliadoId;
    private String maeEstadoAfiliadoCodigo;
    private String maeEstadoAfiliadoValor;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private Integer maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Date fechaNacimiento;
    private Integer maeGeneroId;
    private String maeGeneroCodigo;
    private String maeGeneroValor;
    private String correoElectronico;
    private String contratoAfiliacion;
    private Integer tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private String direccionResidencia;
    private String barrioResidencia;
    private Boolean energiaPrepagada;
    private Boolean borrado;
    private List<AuSolicitudAdjunto> auSolicitudAdjuntosList;
    private List<AuSeguimiento> auSeguimientosList;
    private AsegAfiliado asegAfiliado;
    private Ubicacion gnResidenciaUbicacionId;
    private List<AuSeguimientoAfiliadoContacto> auSeguimientoAfiliadoContactosList;

    public AuSeguimientoAfiliado() {
    }

    public AuSeguimientoAfiliado(String maeTipoDocumentoValor, String numeroDocumento, String primerApellido, String segundoApellido, String primerNombre, String segundoNombre) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
    }

    public AuSeguimientoAfiliado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeEstadoAfiliadoId() {
        return maeEstadoAfiliadoId;
    }

    public void setMaeEstadoAfiliadoId(Integer maeEstadoAfiliadoId) {
        this.maeEstadoAfiliadoId = maeEstadoAfiliadoId;
    }

    public String getMaeEstadoAfiliadoCodigo() {
        return maeEstadoAfiliadoCodigo;
    }

    public void setMaeEstadoAfiliadoCodigo(String maeEstadoAfiliadoCodigo) {
        this.maeEstadoAfiliadoCodigo = maeEstadoAfiliadoCodigo;
    }

    public String getMaeEstadoAfiliadoValor() {
        return maeEstadoAfiliadoValor;
    }

    public void setMaeEstadoAfiliadoValor(String maeEstadoAfiliadoValor) {
        this.maeEstadoAfiliadoValor = maeEstadoAfiliadoValor;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
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

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
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

    public Integer getMaeGeneroId() {
        return maeGeneroId;
    }

    public void setMaeGeneroId(Integer maeGeneroId) {
        this.maeGeneroId = maeGeneroId;
    }

    public String getMaeGeneroCodigo() {
        return maeGeneroCodigo;
    }

    public void setMaeGeneroCodigo(String maeGeneroCodigo) {
        this.maeGeneroCodigo = maeGeneroCodigo;
    }

    public String getMaeGeneroValor() {
        return maeGeneroValor;
    }

    public void setMaeGeneroValor(String maeGeneroValor) {
        this.maeGeneroValor = maeGeneroValor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContratoAfiliacion() {
        return contratoAfiliacion;
    }

    public void setContratoAfiliacion(String contratoAfiliacion) {
        this.contratoAfiliacion = contratoAfiliacion;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getBarrioResidencia() {
        return barrioResidencia;
    }

    public void setBarrioResidencia(String barrioResidencia) {
        this.barrioResidencia = barrioResidencia;
    }

    public Boolean getEnergiaPrepagada() {
        return energiaPrepagada;
    }

    public void setEnergiaPrepagada(Boolean energiaPrepagada) {
        this.energiaPrepagada = energiaPrepagada;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public List<AuSolicitudAdjunto> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjunto> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public List<AuSeguimiento> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimiento> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public Ubicacion getGnResidenciaUbicacionId() {
        return gnResidenciaUbicacionId;
    }

    public void setGnResidenciaUbicacionId(Ubicacion gnResidenciaUbicacionId) {
        this.gnResidenciaUbicacionId = gnResidenciaUbicacionId;
    }

    public List<AuSeguimientoAfiliadoContacto> getAuSeguimientoAfiliadoContactosList() {
        return auSeguimientoAfiliadoContactosList;
    }

    public void setAuSeguimientoAfiliadoContactosList(List<AuSeguimientoAfiliadoContacto> auSeguimientoAfiliadoContactosList) {
        this.auSeguimientoAfiliadoContactosList = auSeguimientoAfiliadoContactosList;
    }

    public String getNombres() {
        return ((primerNombre == null || primerNombre.equals("")) ? "" : primerNombre) + ((segundoNombre == null || segundoNombre.equals("")) ? "" : " " + segundoNombre);
    }

    public String obtenerEnergiaPrepagada() {
        return obtenerBoolean(energiaPrepagada);
    }

    public String obtenerBoolean(Boolean valor) {
        if (valor != null) {
            if (valor) {
                return "SI";
            } else {
                return "NO";
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "AuSeguimientoAfiliado{" + "id=" + id + ", maeEstadoAfiliadoId=" + maeEstadoAfiliadoId + ", maeEstadoAfiliadoCodigo=" + maeEstadoAfiliadoCodigo + ", maeEstadoAfiliadoValor=" + maeEstadoAfiliadoValor + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", numeroDocumento=" + numeroDocumento + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", fechaNacimiento=" + fechaNacimiento + ", maeGeneroId=" + maeGeneroId + ", maeGeneroCodigo=" + maeGeneroCodigo + ", maeGeneroValor=" + maeGeneroValor + ", correoElectronico=" + correoElectronico + ", contratoAfiliacion=" + contratoAfiliacion + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", direccionResidencia=" + direccionResidencia + ", barrioResidencia=" + barrioResidencia + ", energiaPrepagada=" + energiaPrepagada + ", borrado=" + borrado + ", auSolicitudAdjuntosList=" + auSolicitudAdjuntosList + ", auSeguimientosList=" + auSeguimientosList + ", asegAfiliado=" + asegAfiliado + ", gnResidenciaUbicacion=" + gnResidenciaUbicacionId + ", auSeguimientoAfiliadoContactosList=" + auSeguimientoAfiliadoContactosList + '}';
    }

}
