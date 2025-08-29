package com.saviasaludeps.savia.dominio.autorizacion;

import java.io.Serializable;
import java.util.Date;

public class AuAnexo3CargaSuceso implements Serializable {

    public static final int TIPO_SUCESO_ESTRUCTURA = 0;
    public static final int TIPO_SUCESO_NORMA = 1;
    public static final int TIPO_SUCESO_CARGA = 2;

    private Integer id;
    private int tipo;
    private String descripcion;
    private Integer fila;
    private Integer columna;
    private Date fechaHora;
    private AuAnexo3CargaDetalle auAnexo3CargaDetalle;
    private AuAnexo3Carga auAnexo3Carga;

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

    public AuAnexo3CargaDetalle getAuAnexo3CargaDetalle() {
        return auAnexo3CargaDetalle;
    }

    public void setAuAnexo3CargaDetalle(AuAnexo3CargaDetalle auAnexo3CargaDetalle) {
        this.auAnexo3CargaDetalle = auAnexo3CargaDetalle;
    }

    public AuAnexo3Carga getAuAnexo3Carga() {
        return auAnexo3Carga;
    }

    public void setAuAnexo3Carga(AuAnexo3Carga auAnexo3Carga) {
        this.auAnexo3Carga = auAnexo3Carga;
    }
    
        public String getTipoStr() {
        String str;
        switch (this.tipo) {
            case TIPO_SUCESO_CARGA:
                str = "CARGA";
                break;
            case TIPO_SUCESO_ESTRUCTURA:
                str = "ESTRUCTURA";
                break;
            case TIPO_SUCESO_NORMA:
                str = "NORMA";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    @Override
    public String toString() {
        return "AuAnexo3CargaSuceso{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + ", fila=" + fila + ", columna=" + columna + ", fechaHora=" + fechaHora + ", auAnexo3Carga=" + auAnexo3Carga + '}';
    }
    
}
