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
public class AuAnexo3CargaAnuladaSuceso extends Auditoria{
    
    //estado
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_EXITOSO = 1;
    public static final int ESTADO_FALLIDO = 2;
    
    
    private Integer id;
    private AuAnexo3CargaAnulada AuAnexo3CargaAnuladasId;
    private AuAnexo3 AuAnexos3Id;
    private int estado;
    private byte[] data;
    private int fila;
    private int columna;
    private String detalleFallo;
    private Date fechaHora;
  

    public AuAnexo3CargaAnuladaSuceso() {
    }

    public AuAnexo3CargaAnuladaSuceso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuAnexo3CargaAnulada getAuAnexo3CargaAnuladasId() {
        return AuAnexo3CargaAnuladasId;
    }

    public void setAuAnexo3CargaAnuladasId(AuAnexo3CargaAnulada AuAnexo3CargaAnuladasId) {
        this.AuAnexo3CargaAnuladasId = AuAnexo3CargaAnuladasId;
    }

    public AuAnexo3 getAuAnexos3Id() {
        return AuAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexo3 AuAnexos3Id) {
        this.AuAnexos3Id = AuAnexos3Id;
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
        return "AuAnexo3CargaAnuladaSuceso{" + "id=" + id + ", AuAnexo3CargaAnuladasId=" + AuAnexo3CargaAnuladasId + ", AuAnexos3Id=" + AuAnexos3Id + ", estado=" + estado + ", data=" + data + ", fila=" + fila + ", columna=" + columna + ", detalleFallo=" + detalleFallo + ", fechaHora=" + fechaHora + '}';
    }

}
