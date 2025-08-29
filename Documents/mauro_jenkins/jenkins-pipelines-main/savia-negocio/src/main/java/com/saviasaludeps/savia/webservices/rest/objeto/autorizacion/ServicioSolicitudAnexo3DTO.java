/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class ServicioSolicitudAnexo3DTO implements Serializable{

    private String numeroAutorizacion;
    private String estadoTecnologia;
    private String codigoEstadoTecnologia;
    private List<DetalleSolicitudAnexo3DTO> detalle;
    private VolanteAnexo4SolicitudAnexo3DTO volanteAnexo4;

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getEstadoTecnologia() {
        return estadoTecnologia;
    }

    public void setEstadoTecnologia(String estadoTecnologia) {
        this.estadoTecnologia = estadoTecnologia;
    }

    public String getCodigoEstadoTecnologia() {
        return codigoEstadoTecnologia;
    }

    public void setCodigoEstadoTecnologia(String codigoEstadoTecnologia) {
        this.codigoEstadoTecnologia = codigoEstadoTecnologia;
    }

    public List<DetalleSolicitudAnexo3DTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleSolicitudAnexo3DTO> detalle) {
        this.detalle = detalle;
    }

    public VolanteAnexo4SolicitudAnexo3DTO getVolanteAnexo4() {
        return volanteAnexo4;
    }

    public void setVolanteAnexo4(VolanteAnexo4SolicitudAnexo3DTO volanteAnexo4) {
        this.volanteAnexo4 = volanteAnexo4;
    }

}
