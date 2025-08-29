/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author stive
 */
public class GnCorreoEnvio extends Auditoria {
    
    //Origenes
    public static final int ORIGEN_CONEXIONES = 0;
    public static final int ORIGEN_USUARIOS = 1;
    public static final int ORIGEN_PORTABILIDAD = 2;
    public static final int ORIGEN_AUS_CASOS = 3;
    public static final int ORIGEN_AUS_SERVICIOS = 4;
    public static final int ORIGEN_CRUE_SOLICITUDES = 5;
    public static final int ORIGEN_EXTERNO_CASOS = 6;
    public static final int ORIGEN_EXTERNO_TURNO = 7;
    public static final int ORIGEN_MIPRES = 8;
    public static final int ORIGEN_SOLICITUDES_CASOS = 9;
    public static final int ORIGEN_RCO_CONCILIACIONES = 10;
    
    //TIPOS
    public static final int TIPO_TEXTO = 0;
    public static final int TIPO_HTML = 1;
    
    //Estados
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_ENVIADO = 1;
    public static final int ESTADO_FALLIDO = 2;

    
    private Integer id;
    private int origen;
    private String destino;
    private String encabezado;
    private String detalle;
    private int estado;
    private int tipo;
    private String adjunto1;
    private String adjunto2;
    private String adjunto3;
    private Date fechaHoraEnvio;

    public GnCorreoEnvio() {
    }

    public GnCorreoEnvio(Integer id) {
        this.id = id;
    }

    /**
     * Crea el objeto con la siguiente informacion
     * @param origen (La clase los tiene de forma estatica)
     * @param destino (Correo o correos separados por coma de destion)
     * @param encabezado (Asunto)
     * @param detalle (Mensaje)
     * @param tipo (La clase los tiene de forma estatica)
     */
    public GnCorreoEnvio(int origen, String destino, String encabezado, String detalle, int tipo) {
        this.origen = origen;
        this.destino = destino;
        this.encabezado = encabezado;
        this.detalle = detalle;
        this.tipo = tipo;
        this.fechaHoraCrea = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getAdjunto1() {
        return adjunto1;
    }

    public void setAdjunto1(String adjunto1) {
        this.adjunto1 = adjunto1;
    }

    public String getAdjunto2() {
        return adjunto2;
    }

    public void setAdjunto2(String adjunto2) {
        this.adjunto2 = adjunto2;
    }

    public String getAdjunto3() {
        return adjunto3;
    }

    public void setAdjunto3(String adjunto3) {
        this.adjunto3 = adjunto3;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    @Override
    public String toString() {
        return "GnCorreoEnvio{" + "id=" + id + ", origen=" + origen + ", destino=" + destino + ", encabezado=" + encabezado + ", detalle=" + detalle + ", estado=" + estado + ", tipo=" + tipo + ", adjunto1=" + adjunto1 + ", adjunto2=" + adjunto2 + ", adjunto3=" + adjunto3 + ", fechaHoraEnvio=" + fechaHoraEnvio + '}';
    }
    
}
