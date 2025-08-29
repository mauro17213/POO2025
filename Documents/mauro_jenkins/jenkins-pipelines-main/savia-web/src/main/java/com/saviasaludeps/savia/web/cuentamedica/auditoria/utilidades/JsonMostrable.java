/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import java.util.Date;

/**
 *
 * @author Jorge Eliecer Perez
 */
public class JsonMostrable {
    
    private String jsonContenido;
    private String tituloJsonMensaje;
    private Date fechaEnvio;

    public String getJsonContenido() {
        return jsonContenido;
    }

    public void setJsonContenido(String jsonContenido) {
        this.jsonContenido = jsonContenido;
    }

    public String getTituloJsonMensaje() {
        return tituloJsonMensaje;
    }

    public void setTituloJsonMensaje(String tituloJsonMensaje) {
        this.tituloJsonMensaje = tituloJsonMensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    
    
}
