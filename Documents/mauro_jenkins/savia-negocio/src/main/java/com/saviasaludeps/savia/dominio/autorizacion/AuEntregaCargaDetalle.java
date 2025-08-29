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
 * @author iavenegas
 */
public class AuEntregaCargaDetalle extends Auditoria {

    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_EXITOSO = 1;
    public static final int ESTADO_FALLIDO = 2;

    private Integer id;
    private byte[] data;
    private int fila;
    private int estado;
    private String detalleFallo;
    private Date fechaHoraProceso;
    private AuAnexo4Entrega auAnexo4Entrega;
    private AuEntregaCarga auEntregaCarga;

    public AuEntregaCargaDetalle() {
    }

    public AuEntregaCargaDetalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public AuAnexo4Entrega getAuAnexo4Entrega() {
        return auAnexo4Entrega;
    }

    public void setAuAnexo4Entrega(AuAnexo4Entrega auAnexo4Entrega) {
        this.auAnexo4Entrega = auAnexo4Entrega;
    }

    public AuEntregaCarga getAuEntregaCarga() {
        return auEntregaCarga;
    }

    public void setAuEntregaCarga(AuEntregaCarga auEntregaCarga) {
        this.auEntregaCarga = auEntregaCarga;
    }

    public String estadoStr() {
        switch (estado) {
            case ESTADO_EXITOSO:
                return "Exitoso";
            case ESTADO_FALLIDO:
                return "Fallido";
            case ESTADO_PENDIENTE:
                return "Pendiente";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "AuEntregaCargaDetalle{" + "id=" + id + ", data=" + data + ", fila=" + fila + ", estado=" + estado + ", detalleFallo=" + detalleFallo + ", fechaHoraProceso=" + fechaHoraProceso + ", auAnexo4Entrega=" + auAnexo4Entrega + ", auEntregaCarga=" + auEntregaCarga + '}';
    }

}
