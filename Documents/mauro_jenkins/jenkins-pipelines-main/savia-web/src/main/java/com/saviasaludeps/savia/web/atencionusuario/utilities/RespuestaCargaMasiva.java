/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.utilities;

/**
 *
 * @author Jorge Eliecer Perez
 */
public class RespuestaCargaMasiva {
    
    private boolean respuestaAfirmativa;
    private String  contenidoRespuesta;

    public boolean isRespuestaAfirmativa() {
        return respuestaAfirmativa;
    }

    public void setRespuestaAfirmativa(boolean respuestaAfirmativa) {
        this.respuestaAfirmativa = respuestaAfirmativa;
    }

    public String getContenidoRespuesta() {
        return contenidoRespuesta;
    }

    public void setContenidoRespuesta(String contenidoRespuesta) {
        this.contenidoRespuesta = contenidoRespuesta;
    }
    
}
