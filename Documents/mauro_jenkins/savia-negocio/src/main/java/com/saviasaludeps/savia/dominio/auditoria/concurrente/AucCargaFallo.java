/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class AucCargaFallo extends Auditoria {

    //Tipos
    public static final int TIPO_ESTRUCTURA = 1;
    public static final int TIPO_VALIDACION = 2;
    public static final int TIPO_AFILIADO_HOSPITALIZADO = 3;
    public static final int TIPO_REGISTRO_INGRESO = 4;
    public static final int TIPO_REGISTRO_EGRESO = 5;

    //Texto
    private static final String ESTRUCTURA = "Estructura";
    private static final String VALIDACION = "Validación";
    private static final String AFILIADO_HOSPITALIZADO = "Afiliado hospitalizado";
    private static final String REGISTRO_INGRESO = "Registro exitoso";
    private static final String REGISTRO_EGRESO = "Registro egreso";

    private Integer id;
    private int tipo;
    private String descripcion;
    private int fila;
    private int columna;
    private Date fechaHora;
    private AucCarga aucCargaId;

    public AucCargaFallo() {
    }

    public AucCargaFallo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public AucCarga getAucCargaId() {
        return aucCargaId;
    }

    public void setAucCargaId(AucCarga aucCargaId) {
        this.aucCargaId = aucCargaId;
    }

    //Metodos
    public String getTipoStr() {
        String tip = "";
        switch (getTipo()) {
            case TIPO_ESTRUCTURA:
                tip = ESTRUCTURA;
                break;
            case TIPO_VALIDACION:
                tip = VALIDACION;
                break;
            case TIPO_AFILIADO_HOSPITALIZADO:
                tip = AFILIADO_HOSPITALIZADO;
                break;
            case TIPO_REGISTRO_INGRESO:
                tip = REGISTRO_INGRESO;
                break;
            case TIPO_REGISTRO_EGRESO:
                tip = REGISTRO_EGRESO;
                break;
        }
        return tip;
    }

    @Override
    public String toString() {
        return "AucCargaFallo{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + ", fila=" + fila + ", columna=" + columna + ", fechaHora=" + fechaHora + ", aucCargaId=" + aucCargaId + '}';
    }

}
