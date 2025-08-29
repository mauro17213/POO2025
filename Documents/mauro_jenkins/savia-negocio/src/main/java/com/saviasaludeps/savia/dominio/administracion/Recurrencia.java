/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import org.jsoup.Jsoup;

/**
 *
 * @author jjmosquera
 */
public class Recurrencia extends Auditoria implements Serializable {

    public final static int TIPO_PERIODICIDAD_DIA = 0;
    public final static int TIPO_PERIODICIDAD_SEMANA = 1;
    public final static int TIPO_PERIODICIDAD_MES = 2;
    public final static int TIPO_PERIODICIDAD_AGNO = 3;
    public final static int DIA = 7;
    public final static int SEMANA = 4;
    public final static int MES = 12;
    public final static int AGNO = 1;

    public Recurrencia() {
    }

    public Recurrencia(Integer id) {
        this.id = id;
    }

    public Recurrencia(Integer id, String nombre, byte[] script, boolean activo, Date fechaFin, int tipoPeriodicidad, int periodicidad) {
        this.id = id;
        this.nombre = nombre;
        this.script = script;
        this.activo = activo;
        this.fechaFin = fechaFin;
        this.tipoPeriodicidad = tipoPeriodicidad;
        this.periodicidad = periodicidad;
    }

    private Integer id;
    private String nombre;
    private String descripcion;
    private byte[] script;
    private boolean activo;
    private Date fechaInicio;
    private Date fechaFin;
    private int tipoPeriodicidad;
    private int periodicidad;
    private Date fechaHoraEjecucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getScript() {
        return script;
    }

    public String getScriptLimpio() {                
        return Jsoup.parse(this.script == null ? "" : new String(this.script)).text();
    }

    public void setScript(byte[] script) {
        this.script = script;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        if (activo) {
            return "SI";
        } else {
            return "NO";
        }
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getTipoPeriodicidad() {
        return tipoPeriodicidad;
    }

    public void setTipoPeriodicidad(int tipoPeriodicidad) {
        this.tipoPeriodicidad = tipoPeriodicidad;
    }

    public int getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(int periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Date getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

    public String getScriptStr() {
        return (this.script == null ? "" : new String(this.script, StandardCharsets.UTF_8));
    }

    public void setScriptStr(String script) {
        this.script = script.getBytes();
    }

    public String tipoPerioricidad(int tipoPeriodicidad) {
        String perioricidad = "";
        switch (tipoPeriodicidad) {
            case TIPO_PERIODICIDAD_DIA:
                perioricidad = "DIA";
                break;
            case TIPO_PERIODICIDAD_SEMANA:
                perioricidad = "SEMANA";
                break;
            case TIPO_PERIODICIDAD_MES:
                perioricidad = "MES";
                break;
            case TIPO_PERIODICIDAD_AGNO:
                perioricidad = "AÑO";
                break;
        }
        return perioricidad;
    }

    public String perioticidadNum(int periodicidad, int tipoPeriodicidad) {
        String perioricida = null;
        switch (tipoPeriodicidad) {
            case TIPO_PERIODICIDAD_DIA:

                if (periodicidad >= DIA) {
                    perioricida = "SEMANA";
                }
                break;
            case TIPO_PERIODICIDAD_SEMANA:
                if (periodicidad >= SEMANA) {
                    perioricida = "MES";
                }
                break;
            case TIPO_PERIODICIDAD_MES:
                if (periodicidad >= MES) {
                    perioricida = "AÑO";
                }
                break;
            case TIPO_PERIODICIDAD_AGNO:
                if (periodicidad > AGNO) {
                    perioricida = "ERROR";
                }
                break;
        }

        return perioricida;
    }

    @Override
    public String toString() {
        return "Recurrencia{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", script=" + Arrays.toString(script) + ", activo=" + activo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoPeriodicidad=" + tipoPeriodicidad + ", periodicidad=" + periodicidad + '}';
    }

}
