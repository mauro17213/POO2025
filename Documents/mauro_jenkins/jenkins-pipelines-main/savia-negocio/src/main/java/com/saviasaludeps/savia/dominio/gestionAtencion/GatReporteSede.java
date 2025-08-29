package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author StivenGV
 */
public class GatReporteSede extends Auditoria {
    
    
    private int tiquetesGenerados;
    private int tiquetesPrioritarios;
    private int tiquetesAtendidos;
    private int tiquetesSobreatendidos;
    private int tiqueteAbandonadas;
    private int promedioAtencion;
    private int taquillasAtencion;
    private int taquillasReposo;
    private int usuarioAtencion;
    private int usuarioEspera;

    public GatReporteSede() {
    }

    public int getTiquetesGenerados() {
        return tiquetesGenerados;
    }

    public void setTiquetesGenerados(int tiquetesGenerados) {
        this.tiquetesGenerados = tiquetesGenerados;
    }

    public int getTiquetesPrioritarios() {
        return tiquetesPrioritarios;
    }

    public void setTiquetesPrioritarios(int tiquetesPrioritarios) {
        this.tiquetesPrioritarios = tiquetesPrioritarios;
    }

    public int getTiquetesAtendidos() {
        return tiquetesAtendidos;
    }

    public void setTiquetesAtendidos(int tiquetesAtendidos) {
        this.tiquetesAtendidos = tiquetesAtendidos;
    }

    public int getTiquetesSobreatendidos() {
        return tiquetesSobreatendidos;
    }

    public void setTiquetesSobreatendidos(int tiquetesSobreatendidos) {
        this.tiquetesSobreatendidos = tiquetesSobreatendidos;
    }

    public int getTiqueteAbandonadas() {
        return tiqueteAbandonadas;
    }

    public void setTiqueteAbandonadas(int tiqueteAbandonadas) {
        this.tiqueteAbandonadas = tiqueteAbandonadas;
    }

    public int getPromedioAtencion() {
        return promedioAtencion;
    }

    public void setPromedioAtencion(int promedioAtencion) {
        this.promedioAtencion = promedioAtencion;
    }

    public int getTaquillasAtencion() {
        return taquillasAtencion;
    }

    public void setTaquillasAtencion(int taquillasAtencion) {
        this.taquillasAtencion = taquillasAtencion;
    }

    public int getTaquillasReposo() {
        return taquillasReposo;
    }

    public void setTaquillasReposo(int taquillasReposo) {
        this.taquillasReposo = taquillasReposo;
    }

    public int getUsuarioAtencion() {
        return usuarioAtencion;
    }

    public void setUsuarioAtencion(int usuarioAtencion) {
        this.usuarioAtencion = usuarioAtencion;
    }

    public int getUsuarioEspera() {
        return usuarioEspera;
    }

    public void setUsuarioEspera(int usuarioEspera) {
        this.usuarioEspera = usuarioEspera;
    }

    
    
    
}
