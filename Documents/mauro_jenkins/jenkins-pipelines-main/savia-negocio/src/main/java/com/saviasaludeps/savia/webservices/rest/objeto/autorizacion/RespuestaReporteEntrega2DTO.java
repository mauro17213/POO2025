package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

public class RespuestaReporteEntrega2DTO implements Serializable {

    private String idEntrega;
    private String idItem;
    private String codigoTecnologia;
    private String descripcionTecnologia;
    private String Mensaje;

    public String getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(String idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public String getDescripcionTecnologia() {
        return descripcionTecnologia;
    }

    public void setDescripcionTecnologia(String descripcionTecnologia) {
        this.descripcionTecnologia = descripcionTecnologia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RespuestaReporteEntrega2DTO{idEntrega=").append(idEntrega);
        sb.append(", idItem=").append(idItem);
        sb.append(", codigoTecnologia=").append(codigoTecnologia);
        sb.append(", descripcionTecnologia=").append(descripcionTecnologia);
        sb.append(", Mensaje=").append(Mensaje);
        sb.append('}');
        return sb.toString();
    }
    
}
