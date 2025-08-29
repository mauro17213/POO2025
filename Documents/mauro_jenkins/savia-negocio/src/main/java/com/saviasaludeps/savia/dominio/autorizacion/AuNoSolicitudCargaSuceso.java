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
public class AuNoSolicitudCargaSuceso extends Auditoria{
    
    public static final int TIPO_OK = 0;
    public static final int TIPO_ESTRUCTURA = 1;
    public static final int ESTADO_NORMATIVA  = 2;
            
            
    private Integer id;
    private AuNoSolicitudCargaDetalle auNoSolicitudCargaDetallesId;
    private AuNoSolicitudCarga auNoSolicitudCargasId;
    private int tipo;
    private String descripcion;
    private Integer fila;
    private Integer columna;
    private Date fechaHora;
    
    public AuNoSolicitudCargaSuceso(){
        
    }
    
    public AuNoSolicitudCargaSuceso(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudCargaDetalle getAuNoSolicitudCargaDetallesId() {
        return auNoSolicitudCargaDetallesId;
    }

    public void setAuNoSolicitudCargaDetallesId(AuNoSolicitudCargaDetalle auNoSolicitudCargaDetallesId) {
        this.auNoSolicitudCargaDetallesId = auNoSolicitudCargaDetallesId;
    }

    public AuNoSolicitudCarga getAuNoSolicitudCargasId() {
        return auNoSolicitudCargasId;
    }

    public void setAuNoSolicitudCargasId(AuNoSolicitudCarga auNoSolicitudCargasId) {
        this.auNoSolicitudCargasId = auNoSolicitudCargasId;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
     // Metodo adcionales
    public String getTipoStr(){
        String nombreTipo = "";
        switch(tipo){
            case TIPO_OK:
                nombreTipo = "OK";
                break;
            case TIPO_ESTRUCTURA:
                nombreTipo = "Estructura";
                break;
            case ESTADO_NORMATIVA:
                nombreTipo = "Normativa";
                break;
             default:
                break;
        }     
        return nombreTipo;
    }
    
}
