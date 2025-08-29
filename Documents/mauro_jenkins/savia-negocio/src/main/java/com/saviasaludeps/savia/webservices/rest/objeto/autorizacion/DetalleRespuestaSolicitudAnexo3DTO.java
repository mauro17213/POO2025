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
public class DetalleRespuestaSolicitudAnexo3DTO implements Serializable {

    private AfiliadoSolicitudAnexo3DTO afiliado;
    private List<SolicitudSolicitudAnexo3DTO> solicitudes;
    private List<Anexo4DTO> autorizaciones;

    public AfiliadoSolicitudAnexo3DTO getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(AfiliadoSolicitudAnexo3DTO afiliado) {
        this.afiliado = afiliado;
    }

    public List<SolicitudSolicitudAnexo3DTO> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudSolicitudAnexo3DTO> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Anexo4DTO> getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(List<Anexo4DTO> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }
    
}
