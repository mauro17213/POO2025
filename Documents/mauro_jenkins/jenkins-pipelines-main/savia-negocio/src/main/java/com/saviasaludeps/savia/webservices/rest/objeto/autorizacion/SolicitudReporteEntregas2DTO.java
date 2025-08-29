
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SolicitudReporteEntregas2DTO implements Serializable {

    @SerializedName("codigoTecnologia")
    private String codigoTecnologia;
    
    @SerializedName("tipoEntrega")
    private String tipoEntrega;
//    @SerializedName("cantidadAutorizada")
//    private String cantidadAutorizada;
    @SerializedName("cantidadEntregada")
    private String cantidadEntregada;
//    @SerializedName("cantidadPendiente")
//    private String cantidadPendiente;
    @SerializedName("causaNoEntrega")
    private String causaNoEntrega;
    
//    @SerializedName("anulada")
//    private int anulada;
//    
//    @SerializedName("anulaObservacion")
//    private int anulaObservacion;

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

//    public String getCantidadAutorizada() {
//        return cantidadAutorizada;
//    }
//
//    public void setCantidadAutorizada(String cantidadAutorizada) {
//        this.cantidadAutorizada = cantidadAutorizada;
//    }

    public String getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(String cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

//    public String getCantidadPendiente() {
//        return cantidadPendiente;
//    }
//
//    public void setCantidadPendiente(String cantidadPendiente) {
//        this.cantidadPendiente = cantidadPendiente;
//    }

    public String getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(String causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

//    public int getAnulada() {
//        return anulada;
//    }
//
//    public void setAnulada(int anulada) {
//        this.anulada = anulada;
//    }
//
//    public int getAnulaObservacion() {
//        return anulaObservacion;
//    }
//
//    public void setAnulaObservacion(int anulaObservacion) {
//        this.anulaObservacion = anulaObservacion;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SolicitudReporteEntregas2DTO{codigoTecnologia=").append(codigoTecnologia);
        sb.append(", tipoEntrega=").append(tipoEntrega);
        sb.append(", cantidadEntregada=").append(cantidadEntregada);
        sb.append(", causaNoEntrega=").append(causaNoEntrega);
//        sb.append(", anulada=").append(anulada);
//        sb.append(", anulaObservacion=").append(anulaObservacion);
        sb.append('}');
        return sb.toString();
    }

}
