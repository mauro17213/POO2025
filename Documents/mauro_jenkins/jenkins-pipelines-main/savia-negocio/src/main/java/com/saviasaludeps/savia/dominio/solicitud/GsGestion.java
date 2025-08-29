/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jramirez
 */
public class GsGestion extends Auditoria{
    
    public static final int ESTADO_REGISTRADO = 0;
    public static final int ESTADO_GESTION = 1;
    public static final int ESTADO_RESUELTO = 2;
    public static final int ESTADO_NO_TRAMITADO = 3;
    public static final int ESTADO_REASIGNADO = 4;
    
    private Integer id;
    private int estado;
    private String descripcion;
    private GsSolicitud gsSolicitud;
    private Usuario usuario;

    public GsGestion() {
    }

    public GsGestion(Integer id) {
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
            case ESTADO_REGISTRADO:
                return "Registrado";
            case ESTADO_GESTION:
                return "En Gestión";
            case ESTADO_RESUELTO:
                return "Resuelto";
            case ESTADO_NO_TRAMITADO:
                return "No Tramitado";
            case ESTADO_REASIGNADO:
                return "Reasignado";
            default:
                return "";
        }
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public GsSolicitud getGsSolicitud() {
        return gsSolicitud;
    }

    public void setGsSolicitud(GsSolicitud gsSolicitud) {
        this.gsSolicitud = gsSolicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "GsGestion{" + "id=" + id + ", estado=" + estado + ", descripcion=" + descripcion + ", gsSolicitud=" + gsSolicitud + ", usuario=" + usuario + '}';
    }
}
