package com.saviasaludeps.savia.dominio.gestionAtencion;

import java.io.Serializable;

/**
 *
 * @author StivenGV
 */
public class GatReporteTaquilla implements Serializable {
    
    private String nombreTaquilla;
    private String colaboradorTaquilla;
    private int usuariosAtencion;
    private int atendidos;
    private int abandonos;
    private int reposos;
    private int tiempoReposo;
    private int transferidos;

    public GatReporteTaquilla() {
    }

    public String getNombreTaquilla() {
        return nombreTaquilla;
    }

    public void setNombreTaquilla(String nombreTaquilla) {
        this.nombreTaquilla = nombreTaquilla;
    }

    public String getColaboradorTaquilla() {
        return colaboradorTaquilla;
    }

    public void setColaboradorTaquilla(String colaboradorTaquilla) {
        this.colaboradorTaquilla = colaboradorTaquilla;
    }

    public int getUsuariosAtencion() {
        return usuariosAtencion;
    }

    public void setUsuariosAtencion(int usuariosAtencion) {
        this.usuariosAtencion = usuariosAtencion;
    }

    public int getAtendidos() {
        return atendidos;
    }

    public void setAtendidos(int atendidos) {
        this.atendidos = atendidos;
    }

    public int getAbandonos() {
        return abandonos;
    }

    public void setAbandonos(int abandonos) {
        this.abandonos = abandonos;
    }

    public int getReposos() {
        return reposos;
    }

    public void setReposos(int reposos) {
        this.reposos = reposos;
    }

    public int getTiempoReposo() {
        return tiempoReposo;
    }

    public void setTiempoReposo(int tiempoReposo) {
        this.tiempoReposo = tiempoReposo;
    }

    public int getTransferidos() {
        return transferidos;
    }

    public void setTransferidos(int transferidos) {
        this.transferidos = transferidos;
    }
    
    
    
}
