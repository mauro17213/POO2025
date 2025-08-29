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
public class RespuestaSolicitudAnexo3DTO implements Serializable {

    private String rc;
    private String msg;
    private String fechaTransaccion;
    private List<DetalleRespuestaSolicitudAnexo3DTO> consultaSolicitudResponse;

    
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DetalleRespuestaSolicitudAnexo3DTO> getConsultaSolicitudResponse() {
        return consultaSolicitudResponse;
    }

    public void setConsultaSolicitudResponse(List<DetalleRespuestaSolicitudAnexo3DTO> consultaSolicitudResponse) {
        this.consultaSolicitudResponse = consultaSolicitudResponse;
    }

}
