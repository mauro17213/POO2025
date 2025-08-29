/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class AucHospitalizacionEstado extends Auditoria {
    
    //ESTADOS 
    public static final int ESTADO_HOSPITALIZADO = 1;
    public static final int ESTADO_EGRESADO = 2;
    public static final int ESTADO_ANULADO = 3;
    public static final int ESTADO_DEVOLUCION = 4;
    
    //ESTADO AUDITORIA     
    public static final int ESTADO_AUDITORIA_INGRESADO = 1;
    public static final int ESTADO_AUDITORIA_EN_GESTION = 2;
    public static final int ESTADO_AUDITORIA_EGRESADO = 3;
    public static final int ESTADO_AUDITORIA_CERRADO = 4;
    public static final int ESTADO_AUDITORIA_ANULADO = 5;
    public static final int ESTADO_AUDITORIA_DEVUELTO = 6;
    
    //FUENTE ORIGEN 0 - Manual | 1 - Carga Masiva | 2- Interoperabilidad
    public static final int FUENTE_DE_ORIGEN_MANUAL = 0;
    public static final int FUENTE_DE_ORIGEN_CARGA_MASIVA = 1;
    public static final int FUENTE_DE_ORIGEN_INTEROPERABILIDAD = 2;
    public static final int FUENTE_DE_ORIGEN_CARGA_MASIVA_CIERRE = 3;
    
    private Integer id;
    private Short estado;
    private Short estadoAuditoria;
    private Short fuenteOrigen;
    private String observacion;
    private AucHospitalizacion aucHospitalizacionId;
    private int pos;
    public static final Short FUENTE_ORIGEN_HOSPITALIZACION = 0 ;
    public static final Short FUENTE_ORIGEN_CARGA_MASIVA = 1 ;
   
    public AucHospitalizacionEstado(){
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Short getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(Short estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Short getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Short fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AucHospitalizacion getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizacion aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "AucHospitalizacionEstado{" + "id=" + id + ", estado=" + estado + ", estadoAuditoria=" + estadoAuditoria + ", fuenteOrigen=" + fuenteOrigen + ", observacion=" + observacion + ", aucHospitalizacionId=" + aucHospitalizacionId + ", pos=" + pos + '}';
    }
}
