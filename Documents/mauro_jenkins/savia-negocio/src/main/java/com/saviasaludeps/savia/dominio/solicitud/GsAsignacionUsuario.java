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
public class GsAsignacionUsuario extends Auditoria {

    private Integer id;
    private int tipoSolicitud;
    private int zonasId;
    private int ubicacionesId;
    private int usuarioId;

    public GsAsignacionUsuario() {
    }

    public GsAsignacionUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(int tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public int getZonasId() {
        return zonasId;
    }

    public void setZonasId(int zonasId) {
        this.zonasId = zonasId;
    }

    public int getUbicacionesId() {
        return ubicacionesId;
    }

    public void setUbicacionesId(int ubicacionesId) {
        this.ubicacionesId = ubicacionesId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
