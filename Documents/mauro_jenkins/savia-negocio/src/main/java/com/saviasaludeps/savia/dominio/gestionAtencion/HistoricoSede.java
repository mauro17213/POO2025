package com.saviasaludeps.savia.dominio.gestionAtencion;

import java.io.Serializable;

/**
 *
 * @author StivenGV
 */
public class HistoricoSede implements Serializable {
    
    private String fecha;
    private int turnosCreados;
    private int turnosAtendidos;
    private int turnosAbandonados;
    private int turnosSobreAtendidos;
    private int promedioAtencion;

    public HistoricoSede() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTurnosCreados() {
        return turnosCreados;
    }

    public void setTurnosCreados(int turnosCreados) {
        this.turnosCreados = turnosCreados;
    }

    public int getTurnosAtendidos() {
        return turnosAtendidos;
    }

    public void setTurnosAtendidos(int turnosAtendidos) {
        this.turnosAtendidos = turnosAtendidos;
    }

    public int getTurnosAbandonados() {
        return turnosAbandonados;
    }

    public void setTurnosAbandonados(int turnosAbandonados) {
        this.turnosAbandonados = turnosAbandonados;
    }

    public int getTurnosSobreAtendidos() {
        return turnosSobreAtendidos;
    }

    public void setTurnosSobreAtendidos(int turnosSobreAtendidos) {
        this.turnosSobreAtendidos = turnosSobreAtendidos;
    }

    public int getPromedioAtencion() {
        return promedioAtencion;
    }

    public void setPromedioAtencion(int promedioAtencion) {
        this.promedioAtencion = promedioAtencion;
    }
    
}
