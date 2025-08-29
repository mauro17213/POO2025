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
public class AuAnexo4CargaAnuladaSuceso extends Auditoria{
    
    //estado
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_EXITOSO = 1;
    public static final int ESTADO_FALLIDO = 2;
    
    
    private Integer id;
    private AuAnexo4CargaAnulada AuAnexo4CargaAnuladasId;
    private AuAnexo4 AuAnexos4Id;
    private int estado;
    private byte[] data;
    private int fila;
    private int columna;
    private String detalleFallo;
    private Date fechaHora;
  

    public AuAnexo4CargaAnuladaSuceso() {
    }

    public AuAnexo4CargaAnuladaSuceso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuAnexo4CargaAnulada getAuAnexo4CargaAnuladasId() {
        return AuAnexo4CargaAnuladasId;
    }

    public void setAuAnexo4CargaAnuladasId(AuAnexo4CargaAnulada AuAnexo4CargaAnuladasId) {
        this.AuAnexo4CargaAnuladasId = AuAnexo4CargaAnuladasId;
    }

    public AuAnexo4 getAuAnexos4Id() {
        return AuAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexo4 AuAnexos4Id) {
        this.AuAnexos4Id = AuAnexos4Id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDetalleFallo() {
        return detalleFallo;
    }

    public void setDetalleFallo(String detalleFallo) {
        this.detalleFallo = detalleFallo;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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
        return "AuAnexo4CargaAnuladaSuceso{" + "id=" + id + ", AuAnexo4CargaAnuladasId=" + AuAnexo4CargaAnuladasId + ", AuAnexos4Id=" + AuAnexos4Id + ", estado=" + estado + ", data=" + data + ", fila=" + fila + ", columna=" + columna + ", detalleFallo=" + detalleFallo + ", fechaHora=" + fechaHora + '}';
    }

}
