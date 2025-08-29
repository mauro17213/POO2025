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
 * @author José Pérez
 */
public class AsegDetalleCarga extends Auditoria {

    public final static int ESTADO_EN_COLA = 1;
    public final static int ESTADO_PROCESANDO = 2;
    public final static int ESTADO_INGRESADO = 3;
    public final static int ESTADO_FALLIDO = 4;
    
    
    private Integer id;
    private byte[] data;
    private int estado;
    private String detalleFallo;
    private Date fechaHoraProceso;
    private AsegCarga asegCarga;

    public AsegDetalleCarga() {
    }

    public AsegDetalleCarga(Integer id) {
        this.id = id;
    }

    public AsegDetalleCarga(Integer id, byte[] data, int estado, Date fechaHoraProceso) {
        this.id = id;
        this.data = data;
        this.estado = estado;
        this.fechaHoraProceso = fechaHoraProceso;
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

    @Override
    public String toString() {
        return id + "," + (new String(data)) + "," + getEstadoCarga() + "," + detalleFallo + "," + fechaHoraProceso.toString() + "\n";
    }

    /**
     * @return the asegCarga
     */
    public AsegCarga getAsegCarga() {
        return asegCarga;
    }

    /**
     * @param asegCarga the asegCarga to set
     */
    public void setAsegCarga(AsegCarga asegCarga) {
        this.asegCarga = asegCarga;
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
}
