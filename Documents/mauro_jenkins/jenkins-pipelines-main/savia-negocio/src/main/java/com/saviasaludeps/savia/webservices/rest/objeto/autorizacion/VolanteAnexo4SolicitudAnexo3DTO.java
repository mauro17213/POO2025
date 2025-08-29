/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class VolanteAnexo4SolicitudAnexo3DTO implements Serializable{

    private String codigoHabilitacionIPSSedeAutorizada;
    private String nombreIPSSedeAutorizada;
    private String contenido;
    private String nombre;
    private String tipoContenido;

    public String getCodigoHabilitacionIPSSedeAutorizada() {
        return codigoHabilitacionIPSSedeAutorizada;
    }

    public void setCodigoHabilitacionIPSSedeAutorizada(String codigoHabilitacionIPSSedeAutorizada) {
        this.codigoHabilitacionIPSSedeAutorizada = codigoHabilitacionIPSSedeAutorizada;
    }

    public String getNombreIPSSedeAutorizada() {
        return nombreIPSSedeAutorizada;
    }

    public void setNombreIPSSedeAutorizada(String nombreIPSSedeAutorizada) {
        this.nombreIPSSedeAutorizada = nombreIPSSedeAutorizada;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

}
