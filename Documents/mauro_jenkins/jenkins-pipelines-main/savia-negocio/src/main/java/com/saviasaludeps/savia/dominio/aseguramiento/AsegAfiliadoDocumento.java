package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class AsegAfiliadoDocumento extends Auditoria {

    private Integer id;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private Date fechaExpedicion;
    private AsegAfiliado asegAfiliado;

    public AsegAfiliadoDocumento() {
    }

    public AsegAfiliadoDocumento(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the maeTipoDocumentoId
     */
    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    /**
     * @param maeTipoDocumentoId the maeTipoDocumentoId to set
     */
    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    /**
     * @return the maeTipoDocumentoCodigo
     */
    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    /**
     * @param maeTipoDocumentoCodigo the maeTipoDocumentoCodigo to set
     */
    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    /**
     * @return the maeTipoDocumentoValor
     */
    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    /**
     * @param maeTipoDocumentoValor the maeTipoDocumentoValor to set
     */
    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the fechaExpedicion
     */
    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * @param fechaExpedicion the fechaExpedicion to set
     */
    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
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

    @Override
    public String toString() {
        return "AsegAfiliadoDocumento{" + "id=" + id + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", fechaExpedicion=" + fechaExpedicion + ", asegAfiliado=" + asegAfiliado + '}';
    }

}
