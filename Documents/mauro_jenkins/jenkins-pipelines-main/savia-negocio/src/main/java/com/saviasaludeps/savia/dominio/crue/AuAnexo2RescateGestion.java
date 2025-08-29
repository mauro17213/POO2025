/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author iavenegas
 */
public class AuAnexo2RescateGestion extends Auditoria {

    public final static short TIPO_CAMBIO_ESTADO = 0;
    public final static short TIPO_GESTION = 1;
    public final static short TAMANIO_OBSERVACION = 50;

    private Integer id;
    private short tipo;
    private String observacion;
    private Date fechaHoraGestion;
    private AuAnexo2Rescate auAnexo2Rescate;
    private Date fechaHoraDireccionamiento;
    private Integer maeMotivoRescateId;
    private String maeMotivoRescateCodigo;
    private String maeMotivoRescateValor;
    private String maeMotivoRescateTipo;
    //aux
    private String observacionCorto;

    public AuAnexo2RescateGestion() {
    }

    public AuAnexo2RescateGestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaHoraGestion() {
        return fechaHoraGestion;
    }

    public void setFechaHoraGestion(Date fechaHoraGestion) {
        this.fechaHoraGestion = fechaHoraGestion;
    }

    public AuAnexo2Rescate getAuAnexo2Rescate() {
        return auAnexo2Rescate;
    }

    public void setAuAnexo2Rescate(AuAnexo2Rescate auAnexo2Rescate) {
        this.auAnexo2Rescate = auAnexo2Rescate;
    }

    public Date getFechaHoraDireccionamiento() {
        return fechaHoraDireccionamiento;
    }

    public void setFechaHoraDireccionamiento(Date fechaHoraDireccionamiento) {
        this.fechaHoraDireccionamiento = fechaHoraDireccionamiento;
    }

    public Integer getMaeMotivoRescateId() {
        return maeMotivoRescateId;
    }

    public void setMaeMotivoRescateId(Integer maeMotivoRescateId) {
        this.maeMotivoRescateId = maeMotivoRescateId;
    }

    public String getMaeMotivoRescateCodigo() {
        return maeMotivoRescateCodigo;
    }

    public void setMaeMotivoRescateCodigo(String maeMotivoRescateCodigo) {
        this.maeMotivoRescateCodigo = maeMotivoRescateCodigo;
    }

    public String getMaeMotivoRescateValor() {
        return maeMotivoRescateValor;
    }

    public void setMaeMotivoRescateValor(String maeMotivoRescateValor) {
        this.maeMotivoRescateValor = maeMotivoRescateValor;
    }

    public String getMaeMotivoRescateTipo() {
        return maeMotivoRescateTipo;
    }

    public void setMaeMotivoRescateTipo(String maeMotivoRescateTipo) {
        this.maeMotivoRescateTipo = maeMotivoRescateTipo;
    }
    
    //METODOS
    public String getTipoStr() {
        switch (this.tipo) {
            case TIPO_CAMBIO_ESTADO:
                return "Cambio estado";
            case TIPO_GESTION:
                return "Gestion";
            default:
                return "";
        }
    }

    public String getObservacionCorto() {
        if (getObservacion() != null) {
            observacionCorto = getObservacion();
            if (getObservacion().length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    @Override
    public String toString() {
        return "AuAnexo2RescateGestion{" + "id=" + id + ", tipo=" + tipo + ", observacion=" + observacion + ", fechaHoraGestion=" + fechaHoraGestion 
                + "fechaHoraDireccionamiento=" + fechaHoraDireccionamiento + ", maeMotivoRescateCodigo=" + maeMotivoRescateCodigo + ", maeMotivoRescateValor=" + maeMotivoRescateValor 
                + ", maeMotivoRescateTipo=" + maeMotivoRescateTipo + ", auAnexo2Rescate=" + auAnexo2Rescate + '}';
    }

}
