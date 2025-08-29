package com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento;

import java.io.Serializable;

/**
 *
 * @author LFRIVERA
 */
public class AfiliadoProgramaDTO implements Serializable {

    private String tipo;
    private String descripcion;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
