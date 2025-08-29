/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

/**
 *
 * @author Jorge Eliecer Perez
 */
public class CmRespuestaGenerica {
    
    private boolean estadoRespuesta;
    private String mensaje;

    public CmRespuestaGenerica() {
    }

    public CmRespuestaGenerica(boolean estadoRespuesta, String mensaje) {
        this.estadoRespuesta = estadoRespuesta;
        this.mensaje = mensaje;
    }

    public boolean isEstadoRespuesta() {
        return estadoRespuesta;
    }

    public void setEstadoRespuesta(boolean estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
