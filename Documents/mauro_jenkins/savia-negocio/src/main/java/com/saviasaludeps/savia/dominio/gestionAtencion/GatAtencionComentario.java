/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class GatAtencionComentario extends Auditoria {
    
    private GatAtencion gatAtencionId;
    private Integer id;
    private String comentario;

    public GatAtencionComentario() {
    }

    public GatAtencionComentario(Integer id) {
        this.id = id;
    }

    public GatAtencion getGatAtencionId() {
        return gatAtencionId;
    }

    public void setGatAtencionId(GatAtencion gatAtencionId) {
        this.gatAtencionId = gatAtencionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "GatAtencionComentario{" + "id=" + id + ", comentario=" + comentario + ", gatAtencionId=" + gatAtencionId + '}';
    }
    
}
