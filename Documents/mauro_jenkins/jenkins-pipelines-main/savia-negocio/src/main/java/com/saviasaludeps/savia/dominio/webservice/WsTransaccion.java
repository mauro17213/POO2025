/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.webservice;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Fabian Coronel
 */
public class WsTransaccion extends Auditoria{
    
    public static final int ESTADO_SOLICITUD = 1;
    public static final int ESTADO_RESPUESTA = 2;

    private Integer id;
    private int estado;
    private String ipSolicitud;
    private String usuario;
    private String token;
    private Date fechaHoraSolicitud;
    private Date fechaHoraRespuesta;
    private Boolean contingencia;
    private byte[] jsonSolicitud;
    private byte[] jsonRespuesta;
    private Boolean respuesta;
    private WsConexion wsConexion;
    private WsServicio wsServicio;

    public WsTransaccion() {
    }

    public WsTransaccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }
    
    public String getEstadoStr() {
        switch (estado) {
            case ESTADO_SOLICITUD:
                return "Solicitud";
            case ESTADO_RESPUESTA:
                return "Respuesta";
            default:
                return "";
        }
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIpSolicitud() {
        return ipSolicitud;
    }

    public void setIpSolicitud(String ipSolicitud) {
        this.ipSolicitud = ipSolicitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public Boolean getContingencia() {
        return contingencia;
    }

    public void setContingencia(Boolean contingencia) {
        this.contingencia = contingencia;
    }

    public byte[] getJsonSolicitud() {
        return jsonSolicitud;
    }

    public void setJsonSolicitud(byte[] jsonSolicitud) {
        this.jsonSolicitud = jsonSolicitud;
    }

    public byte[] getJsonRespuesta() {
        return jsonRespuesta;
    }

    public void setJsonRespuesta(byte[] jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }

    public Boolean isRespuesta() {
        return respuesta;
    }
    
    public String getRespuestaStr() {
        if (respuesta == null) {
            return "N/A";
        } else {
            return (respuesta) ? "Procesado" : "En Proceso";
        }
    }

    public void setRespuesta(Boolean respuesta) {
        this.respuesta = respuesta;
    }

    public WsConexion getWsConexion() {
        return wsConexion;
    }

    public void setWsConexion(WsConexion wsConexion) {
        this.wsConexion = wsConexion;
    }

    public WsServicio getWsServicio() {
        return wsServicio;
    }

    public void setWsServicio(WsServicio wsServicio) {
        this.wsServicio = wsServicio;
    }
    
}
