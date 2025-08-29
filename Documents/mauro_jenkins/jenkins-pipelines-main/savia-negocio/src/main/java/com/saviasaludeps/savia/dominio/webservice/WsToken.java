/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.webservice;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yjimenez
 */
public class WsToken implements Serializable {

    private Integer id;
    private String token;
    private String tokenCorto;
    private Date fechaHoraInicio;
    private int tiempo;
    private Date fechaHoraFin;
    private String ipSolicita;
    private int transacciones;
    private WsConexion wsConexion;

    public WsToken() {
    }
    
    public WsToken(Integer id) {
        this.id = id;
    }

    public WsToken(Integer id, String token, Date fechaHoraInicio, int tiempo, Date fechaHoraFin, int transacciones, WsConexion wsConexion) {
        this.id = id;
        this.token = token;
        this.fechaHoraInicio = fechaHoraInicio;
        this.tiempo = tiempo;
        this.fechaHoraFin = fechaHoraFin;
        this.transacciones = transacciones;
        this.wsConexion = wsConexion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenCorto() {
        return tokenCorto;
    }

    public void setTokenCorto(String tokenCorto) {
        this.tokenCorto = tokenCorto;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getIpSolicita() {
        return ipSolicita;
    }

    public void setIpSolicita(String ipSolicita) {
        this.ipSolicita = ipSolicita;
    }

    public int getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(int transacciones) {
        this.transacciones = transacciones;
    }

    public WsConexion getWsConexion() {
        return wsConexion;
    }

    public void setWsConexion(WsConexion wsConexion) {
        this.wsConexion = wsConexion;
    }

}
