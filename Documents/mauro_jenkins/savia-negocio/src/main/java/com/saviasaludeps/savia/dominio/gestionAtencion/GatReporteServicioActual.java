package com.saviasaludeps.savia.dominio.gestionAtencion;

import java.io.Serializable;

/**
 *
 * @author StivenGV
 */
public class GatReporteServicioActual implements Serializable {
    
    private int maeServicioId;
    private String maeServicioCodigo;
    private String maeServicioValor;
    private int generados;
    private int enEspera;
    private int atendidos;
    private int abandonados;
    private int cancelados;
    private int sobreatendidos;
    private int promedioEspera;
    private int promedioAtencion;
    private int traslados;

    public int getMaeServicioId() {
        return maeServicioId;
    }

    public void setMaeServicioId(int maeServicioId) {
        this.maeServicioId = maeServicioId;
    }

    public String getMaeServicioCodigo() {
        return maeServicioCodigo;
    }

    public void setMaeServicioCodigo(String maeServicioCodigo) {
        this.maeServicioCodigo = maeServicioCodigo;
    }

    public String getMaeServicioValor() {
        return maeServicioValor;
    }

    public void setMaeServicioValor(String maeServicioValor) {
        this.maeServicioValor = maeServicioValor;
    }

    public int getGenerados() {
        return generados;
    }

    public void setGenerados(int generados) {
        this.generados = generados;
    }

    public int getEnEspera() {
        return enEspera;
    }

    public void setEnEspera(int enEspera) {
        this.enEspera = enEspera;
    }

    public int getAtendidos() {
        return atendidos;
    }

    public void setAtendidos(int atendidos) {
        this.atendidos = atendidos;
    }

    public int getAbandonados() {
        return abandonados;
    }

    public void setAbandonados(int abandonados) {
        this.abandonados = abandonados;
    }

    public int getCancelados() {
        return cancelados;
    }

    public void setCancelados(int cancelados) {
        this.cancelados = cancelados;
    }

    public int getSobreatendidos() {
        return sobreatendidos;
    }

    public void setSobreatendidos(int sobreatendidos) {
        this.sobreatendidos = sobreatendidos;
    }

    public int getPromedioEspera() {
        return promedioEspera;
    }

    public void setPromedioEspera(int promedioEspera) {
        this.promedioEspera = promedioEspera;
    }

    public int getPromedioAtencion() {
        return promedioAtencion;
    }

    public void setPromedioAtencion(int promedioAtencion) {
        this.promedioAtencion = promedioAtencion;
    }

    public int getTraslados() {
        return traslados;
    }

    public void setTraslados(int traslados) {
        this.traslados = traslados;
    }
    
}
