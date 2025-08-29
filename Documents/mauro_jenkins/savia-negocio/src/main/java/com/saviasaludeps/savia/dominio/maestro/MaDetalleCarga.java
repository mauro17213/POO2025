/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class MaDetalleCarga extends Auditoria {
    
    public final static int ESTADO_EN_COLA = 1;
    public final static int ESTADO_PROCESANDO = 2;
    public final static int ESTADO_INGRESADO = 3;
    public final static int ESTADO_FALLIDO = 4;

    private Integer id;
    private int estado;
    private byte[] data;
    private String detalleFallo;
    private Date fechaHoraProceso;
    
    private MaCarga maCarga;

    public MaDetalleCarga() {
    }

    public MaDetalleCarga(Integer id) {
        this.id = id;
    }

    public MaDetalleCarga(Integer id, String detalleFallo, Date fechaHoraProceso) {
        this.id = id;
        this.detalleFallo = detalleFallo;
        this.fechaHoraProceso = fechaHoraProceso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the maCarga
     */
    public MaCarga getMaCarga() {
        return maCarga;
    }

    /**
     * @param maCarga the maCarga to set
     */
    public void setMaCarga(MaCarga maCarga) {
        this.maCarga = maCarga;
    }
    
    private String getEstadoCarga() {
        String mensaje = "";
        switch (this.estado) {
            case ESTADO_EN_COLA:
                mensaje = "En Cola";
            break;
            case ESTADO_PROCESANDO:
                mensaje = "En Proceso";
            break;
            case ESTADO_INGRESADO:
                mensaje = "Ingresado";
            break;
            case ESTADO_FALLIDO:
                mensaje = "Fallido";
            break;
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return id + "," + (new String(data)) + "," + getEstadoCarga() + "," + detalleFallo + "," + fechaHoraProceso.toString() + "\n";
    }
    
}
