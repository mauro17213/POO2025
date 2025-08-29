/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Jose Perez
 */
public class AusSolicitud extends Auditoria {
    
    public final static short TAMANIO_MAXIMO_CAMPOS_LARGOS = 30;
    
    public final static String ESTADO_SOLICITUD_RADICADO = "1";
    public final static String ESTADO_SOLICITUD_CERRADO = "2";
    public final static String ESTADO_SOLICITUD_CREADO = "3";
    
    public final static String TIPO_SOLICITUD_PETICION_ANONIMA = "11";
    
    public final static String ESTADO_SOLICITUD_RADICADO_STR = "Radicado";
    public final static String ESTADO_SOLICITUD_CERRADO_STR = "Cerrado";
    public final static String ESTADO_SOLICITUD_CREADO_STR = "Creado";

    private Integer id;
    private Integer maeTipoSolicitudId;
    private String maeTipoSolicitudCodigo;
    private String maeTipoSolicitudValor;
    private String nombres;
    private String apellidos;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private String email;
    private String detalleEmail;
    private String telefono;
    private String descripcion;
    private Integer maeEstadoSolicitudId;
    private String maeEstadoSolicitudCodigo;
    private String maeEstadoSolicitudValor;
    private boolean aplicaCaso;
    //2024-07-31 nuevo campo
    private String direccionNotificacion;
    
    private AusCaso ausCaso;
    private List<AusAdjunto> ausAdjuntosList;
    
    public AusSolicitud() {
    }

    public AusSolicitud(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the maeTipoSolicitudId
     */
    public Integer getMaeTipoSolicitudId() {
        return maeTipoSolicitudId;
    }

    /**
     * @param maeTipoSolicitudId the maeTipoSolicitudId to set
     */
    public void setMaeTipoSolicitudId(Integer maeTipoSolicitudId) {
        this.maeTipoSolicitudId = maeTipoSolicitudId;
    }

    /**
     * @return the maeTipoSolicitudCodigo
     */
    public String getMaeTipoSolicitudCodigo() {
        return maeTipoSolicitudCodigo;
    }

    /**
     * @param maeTipoSolicitudCodigo the maeTipoSolicitudCodigo to set
     */
    public void setMaeTipoSolicitudCodigo(String maeTipoSolicitudCodigo) {
        this.maeTipoSolicitudCodigo = maeTipoSolicitudCodigo;
    }

    /**
     * @return the maeTipoSolicitudValor
     */
    public String getMaeTipoSolicitudValor() {
        return maeTipoSolicitudValor;
    }

    /**
     * @param maeTipoSolicitudValor the maeTipoSolicitudValor to set
     */
    public void setMaeTipoSolicitudValor(String maeTipoSolicitudValor) {
        this.maeTipoSolicitudValor = maeTipoSolicitudValor;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the maeTipoDocumentoId
     */
    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    /**
     * @param maeTipoDocumentoId the maeTipoDocumentoId to set
     */
    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the detalleEmail
     */
    public String getDetalleEmail() {
        return detalleEmail;
    }

    /**
     * @param detalleEmail the detalleEmail to set
     */
    public void setDetalleEmail(String detalleEmail) {
        this.detalleEmail = detalleEmail;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcionCorta() {
        String descripcionCorto = "";
        if (getDescripcion()!= null) {
            descripcionCorto = getDescripcion();
            if (getDescripcion().length() >= TAMANIO_MAXIMO_CAMPOS_LARGOS) {
                return descripcionCorto.substring(0, TAMANIO_MAXIMO_CAMPOS_LARGOS) + "..";
            } else {
                return descripcionCorto;
            }
        }
        return descripcionCorto;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the maeEstadoSolicitudId
     */
    public Integer getMaeEstadoSolicitudId() {
        return maeEstadoSolicitudId;
    }

    /**
     * @param maeEstadoSolicitudId the maeEstadoSolicitudId to set
     */
    public void setMaeEstadoSolicitudId(Integer maeEstadoSolicitudId) {
        this.maeEstadoSolicitudId = maeEstadoSolicitudId;
    }

    /**
     * @return the maeEstadoSolicitudCodigo
     */
    public String getMaeEstadoSolicitudCodigo() {
        return maeEstadoSolicitudCodigo;
    }

    /**
     * @param maeEstadoSolicitudCodigo the maeEstadoSolicitudCodigo to set
     */
    public void setMaeEstadoSolicitudCodigo(String maeEstadoSolicitudCodigo) {
        this.maeEstadoSolicitudCodigo = maeEstadoSolicitudCodigo;
    }

    /**
     * @return the maeEstadoSolicitudValor
     */
    public String getMaeEstadoSolicitudValor() {
        return maeEstadoSolicitudValor;
    }

    /**
     * @param maeEstadoSolicitudValor the maeEstadoSolicitudValor to set
     */
    public void setMaeEstadoSolicitudValor(String maeEstadoSolicitudValor) {
        this.maeEstadoSolicitudValor = maeEstadoSolicitudValor;
    }

    /**
     * @return the aplicaCaso
     */
    public boolean isAplicaCaso() {
        return aplicaCaso;
    }

    /**
     * @param aplicaCaso the aplicaCaso to set
     */
    public void setAplicaCaso(boolean aplicaCaso) {
        this.aplicaCaso = aplicaCaso;
    }

    /**
     * @return the ausCaso
     */
    public AusCaso getAusCaso() {
        return ausCaso;
    }

    /**
     * @param ausCaso the ausCaso to set
     */
    public void setAusCaso(AusCaso ausCaso) {
        this.ausCaso = ausCaso;
    }

    /**
     * @return the ausAdjuntosList
     */
    public List<AusAdjunto> getAusAdjuntosList() {
        return ausAdjuntosList;
    }

    /**
     * @param ausAdjuntosList the ausAdjuntosList to set
     */
    public void setAusAdjuntosList(List<AusAdjunto> ausAdjuntosList) {
        this.ausAdjuntosList = ausAdjuntosList;
    }

    @Override
    public String toString() {
        return "AusSolicitud{" + "id=" + id + ", maeTipoSolicitudId=" + maeTipoSolicitudId + ", maeTipoSolicitudCodigo=" + maeTipoSolicitudCodigo + ", maeTipoSolicitudValor=" + maeTipoSolicitudValor + ", nombres=" + nombres + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", numeroDocumento=" + numeroDocumento + ", email=" + email + ", telefono=" + telefono + ", descripcion=" + descripcion + ", maeEstadoSolicitudId=" + maeEstadoSolicitudId + ", maeEstadoSolicitudCodigo=" + maeEstadoSolicitudCodigo + ", maeEstadoSolicitudValor=" + maeEstadoSolicitudValor + ", aplicaCaso=" + aplicaCaso + ", ausCaso=" + ausCaso + '}';
    }

    /**
     * @return the direccionNotificacion
     */
    public String getDireccionNotificacion() {
        return direccionNotificacion;
    }

    /**
     * @param direccionNotificacion the direccionNotificacion to set
     */
    public void setDireccionNotificacion(String direccionNotificacion) {
        this.direccionNotificacion = direccionNotificacion;
    }
    
    
}
