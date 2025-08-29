/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudEntregaCargaSuceso extends Auditoria{
    
    //estado
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_EXITOSO = 1;
    public static final int ESTADO_FALLIDO = 2;
    
    
    private Integer id;
    private AuNoSolicitudEntregaCarga auNoSolicitudEntregaCargasId;
    private AuNoSolicitudEntrega auNoSolicitudEntregaId;
    private byte[] data;
    private int fila;
    private int estado;
    private String detalleFallo;
    private Date fechaHoraProceso;
  

    public AuNoSolicitudEntregaCargaSuceso() {
    }

    public AuNoSolicitudEntregaCargaSuceso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudEntregaCarga getAuNoSolicitudEntregaCargasId() {
        return auNoSolicitudEntregaCargasId;
    }

    public void setAuNoSolicitudEntregaCargasId(AuNoSolicitudEntregaCarga auNoSolicitudEntregaCargasId) {
        this.auNoSolicitudEntregaCargasId = auNoSolicitudEntregaCargasId;
    }


    public AuNoSolicitudEntrega getAuNoSolicitudEntregaId() {
        return auNoSolicitudEntregaId;
    }

    public void setAuNoSolicitudEntregaId(AuNoSolicitudEntrega auNoSolicitudEntregaId) {
        this.auNoSolicitudEntregaId = auNoSolicitudEntregaId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDetalleFallo() {
        return detalleFallo;
    }

    public void setDetalleFallo(String detalleFallo) {
        this.detalleFallo = detalleFallo;
    }

    public Date getFechaHoraProceso() {
        return fechaHoraProceso;
    }

    public void setFechaHoraProceso(Date fechaHoraProceso) {
        this.fechaHoraProceso = fechaHoraProceso;
    }

    //Metodo auxiliar
    public String getEstadoStr() {
        String estadoStr = "";
        switch (estado) {
            case ESTADO_PENDIENTE:
                estadoStr = "Pendiente";
                break;
            case ESTADO_EXITOSO:
                estadoStr = "Exitoso";
                break;
            case ESTADO_FALLIDO:
                estadoStr = "Fallido";
                break;
           
        }
        return estadoStr;
    }

    @Override
    public String toString() {
        return "AuNoSolicitudEntregaCargaSuceso{" + "id=" + id + ", auNoSolicitudEntregaCargasId=" + auNoSolicitudEntregaCargasId + ", auNoSolicitudEntregaId=" + auNoSolicitudEntregaId + ", data=" + data + ", fila=" + fila + ", estado=" + estado + ", detalleFallo=" + detalleFallo + ", fechaHoraProceso=" + fechaHoraProceso + '}';
    }
}
