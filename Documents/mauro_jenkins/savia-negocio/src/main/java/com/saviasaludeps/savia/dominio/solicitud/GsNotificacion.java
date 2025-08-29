/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jramirez
 */
public class GsNotificacion extends Auditoria {

    public static final int TIPO_CORREO = 0;
    public static final int TIPO_SMS = 1;

    public static final int ESTADO_CREADO = 0;
    public static final int ESTADO_ENVIADO = 1;
    public static final int ESTADO_FALLIDO = 2;
    public static final int ESTADO_CANCELADO = 3;

    private Integer id;
    private int tipo;
    private String correo;
    private String celular;
    private String encabezado;
    private String detalle;
    private int estado;
    private GsSolicitud gsSolicitudesId;

    public GsNotificacion() {
    }

    public GsNotificacion(Integer id) {
        this.id = id;
    }

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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

    public GsSolicitud getGsSolicitudesId() {
        return gsSolicitudesId;
    }

    public void setGsSolicitudesId(GsSolicitud gsSolicitudesId) {
        this.gsSolicitudesId = gsSolicitudesId;
    }

    @Override
    public String toString() {
        return "GsNotificacion{" + "id=" + id + ", tipo=" + tipo + ", correo=" + correo + ", celular=" + celular + ", encabezado=" + encabezado + ", detalle=" + detalle + ", estado=" + estado + ", gsSolicitudesId=" + gsSolicitudesId + '}';
    }
}
