/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class AucAfiliado extends Auditoria {

    private Integer id;
    private int maeEstadoAfiliadoId;
    private String maeEstadoAfiliadoCodigo;
    private String maeEstadoAfiliadoValor;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
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
    private String direccionResidencia;
    private Integer ubicacionAfiliacionId;
    private String contratoAfiliacion;
    private AsegAfiliado asegAfiliadoId;
    private Integer maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;

    private List<AucHospitalizacion> aucHospitalizacionList;
    private List<AucAfiliadoContacto> aucAfiliadoContactoList;

    public AucAfiliado() {
    }

    public AucAfiliado(Integer id) {
        this.id = id;
    }

    public AucAfiliado(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String primerApellido, String segundoApellido, String primerNombre, String segundoNombre) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeEstadoAfiliadoId() {
        return maeEstadoAfiliadoId;
    }

    public void setMaeEstadoAfiliadoId(int maeEstadoAfiliadoId) {
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

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public Integer getUbicacionAfiliacionId() {
        return ubicacionAfiliacionId;
    }

    public void setUbicacionAfiliacionId(Integer ubicacionAfiliacionId) {
        this.ubicacionAfiliacionId = ubicacionAfiliacionId;
    }

    public String getContratoAfiliacion() {
        return contratoAfiliacion;
    }

    public void setContratoAfiliacion(String contratoAfiliacion) {
        this.contratoAfiliacion = contratoAfiliacion;
    }

    public AsegAfiliado getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(AsegAfiliado asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
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

    public List<AucHospitalizacion> getAucHospitalizacionList() {
        return aucHospitalizacionList;
    }

    public void setAucHospitalizacionList(List<AucHospitalizacion> aucHospitalizacionList) {
        this.aucHospitalizacionList = aucHospitalizacionList;
    }

    public List<AucAfiliadoContacto> getAucAfiliadoContactoList() {
        return aucAfiliadoContactoList;
    }

    public void setAucAfiliadoContactoList(List<AucAfiliadoContacto> aucAfiliadoContactoList) {
        this.aucAfiliadoContactoList = aucAfiliadoContactoList;
    }

    //Metodos auxiliares    
    public String getNombreCompleto() {
        String nombre = getPrimerNombre() + " ";
        if (getSegundoNombre() != null) {
            nombre += getSegundoNombre() + " ";
        }
        nombre += getPrimerApellido();
        if (getSegundoApellido() != null) {
            nombre += " " + getSegundoApellido();
        }
        return nombre;
    }

    public String getNombres() {
        String nombres = getPrimerNombre();
        if (getSegundoNombre() != null) {
            nombres += " " + getSegundoNombre();
        }
        if (nombres == null) {
            return "";
        }
        return nombres;
    }

    public String getApellidos() {
        String apellidos = getPrimerApellido();
        if (getSegundoApellido() != null) {
            apellidos += " " + getSegundoApellido();
        }
        if (apellidos == null) {
            return "";
        }
        return apellidos;
    }

    public String getDatosAuditoria() {
        String datos = "";
        if (getPrimerNombre() != null) {
            datos += " " + getPrimerNombre();
        }
        if (getSegundoNombre() != null) {
            datos += " " + getSegundoNombre();
        }
        if (getPrimerApellido() != null) {
            datos += " " + getPrimerApellido();
        }
        if (getSegundoApellido() != null) {
            datos += " " + getSegundoApellido();
        }
        if (getMaeTipoDocumentoCodigo() != null) {
            datos += " " + getMaeTipoDocumentoCodigo();
        }
        if (getNumeroDocumento() != null) {
            datos += " " + getNumeroDocumento() + " ";
        }
        return datos;
    }

    @Override
    public String toString() {
        return "AucAfiliado{" + "id=" + id + ", maeEstadoAfiliadoId=" + maeEstadoAfiliadoId + ", maeEstadoAfiliadoCodigo=" + maeEstadoAfiliadoCodigo + ", maeEstadoAfiliadoValor=" + maeEstadoAfiliadoValor + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", fechaNacimiento=" + fechaNacimiento + ", maeGeneroId=" + maeGeneroId + ", maeGeneroCodigo=" + maeGeneroCodigo + ", maeGeneroValor=" + maeGeneroValor + ", correoElectronico=" + correoElectronico + ", direccionResidencia=" + direccionResidencia + ", ubicacionAfiliacionId=" + ubicacionAfiliacionId + ", contratoAfiliacion=" + contratoAfiliacion + ", asegAfiliadoId=" + asegAfiliadoId + '}';
    }

}
