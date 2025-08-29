package com.saviasaludeps.savia.dominio.generico;

import java.io.Serializable;
import java.util.Date;

public class Hilo implements Serializable {

    private int id;
    private Date fechaInicio;
    private String nombre;
    private String proceso;
    private int tipo;
    private boolean activo;
    private int estado;

    public final static int BUCLE_INFINITO = 0;
    public final static int CARGA_MASIVA = 1;
    public final static int EJECUCION_QUERY = 2;

    public static int ESTADO_INACTIVO = 0;
    public static int ESTADO_ACTIVO = 1;
    public static int ESTADO_FINALIZAR = 2;
    public static int ESTADO_FINALIZAR_FORZADO = 3;

    public Hilo() {
    }

    public Hilo(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public String getTipoStr() {
        String str;
        switch (tipo) {
            case BUCLE_INFINITO:
                str = "Bucle infinito";
                break;
            case CARGA_MASIVA:
                str = "Proceso de carga masiva";
                break;
            case EJECUCION_QUERY:
                str = "Ejecución de query";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public String getActivoStr() {
        String str = "NO";
        if (activo) {
            str = "SI";
        }
        return str;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    
    @Override
    public String toString() {
        return "Hilo{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", nombre=" + nombre + ", tipo=" + tipo + ", activo=" + getActivoStr() + '}';
    }

}
