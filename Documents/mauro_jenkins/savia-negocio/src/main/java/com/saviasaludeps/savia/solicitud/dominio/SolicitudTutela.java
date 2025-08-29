/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.solicitud.dominio;

import java.io.Serializable;


/**
 *
 * @author Stiven Giraldo
 */
public class SolicitudTutela implements Serializable {
    
    private String tipoDocumento = "";
    private String numeroDocumento = "";
    
    public SolicitudTutela(String tipoDocumento, String numeroDocumento){
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    public String obtenerJSON(){
        return AuConstantes.LLAVE_ABRE + AuConstantes.COMILLA_DOBLE + AuConstantes.TEXTO_WS_TIPO_DOCUMENTO + AuConstantes.COMILLA_DOBLE + AuConstantes.DOS_PUNTOS + AuConstantes.COMILLA_DOBLE + tipoDocumento + AuConstantes.COMILLA_DOBLE + AuConstantes.COMA + 
               AuConstantes.COMILLA_DOBLE + AuConstantes.TEXTO_WS_NUMERO_DOCUMENTO + AuConstantes.COMILLA_DOBLE + AuConstantes.DOS_PUNTOS + AuConstantes.COMILLA_DOBLE + numeroDocumento + AuConstantes.COMILLA_DOBLE + AuConstantes.LLAVE_CIERRA;
    }

    
}
