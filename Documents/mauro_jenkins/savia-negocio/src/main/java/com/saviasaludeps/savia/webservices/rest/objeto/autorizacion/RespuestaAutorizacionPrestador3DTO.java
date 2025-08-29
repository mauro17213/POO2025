package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RespuestaAutorizacionPrestador3DTO implements Serializable {
    
    private String nua;
    private Date fechaAutorizacion;
    private String estadoAutorizacion;
    private Date fechaAnulacion;
    private String solicitud;
    private List<RespuestaAutorizacionPrestador4DTO> items;

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getNua() {
        return nua;
    }

    public void setNua(String nua) {
        this.nua = nua;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public String getEstadoAutorizacion() {
        return estadoAutorizacion;
    }

    public void setEstadoAutorizacion(String estadoAutorizacion) {
        this.estadoAutorizacion = estadoAutorizacion;
    }

    public List<RespuestaAutorizacionPrestador4DTO> getItems() {
        return items;
    }

    public void setItems(List<RespuestaAutorizacionPrestador4DTO> items) {
        this.items = items;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RespuestaAutorizacionPrestador3DTO{nua=").append(nua);
        sb.append(", fechaAutorizacion=").append(fechaAutorizacion);
        sb.append(", estadoAutorizacion=").append(estadoAutorizacion);
        sb.append(", solicitud=").append(solicitud);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
    
}
