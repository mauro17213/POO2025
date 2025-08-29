/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import java.io.Serializable;

/**
 *
 * @author Jhohan Lopez
 * @since 14/04/2025
 *
 * Clase que representa datos del afiliado en la carga masiva de variables
 * especificas
 */
public class PeAfiliadoCargaVariable implements Serializable {

    private String tipoDocumento;
    private String identificacion;

    public PeAfiliadoCargaVariable() {
    }

    public PeAfiliadoCargaVariable(String tipoDocumento, String identificacion) {
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String documentoIdentificacion) {
        this.identificacion = documentoIdentificacion;
    }

    @Override
    public String toString() {
        return "PeAfiliadoCargaVariable{" + "tipoDocumento=" + tipoDocumento 
                + ", documentoIdentificacion=" + identificacion + '}';
    }

}
