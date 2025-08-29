
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SolicitudAutorizacionFecha1DTO implements Serializable {

    @SerializedName("nut")
    private String nut;
    @SerializedName("fechaHoraTransaccion")
    private String fechaHoraTransaccion;
    @SerializedName("codHabilitacionPrestador")
    private String codHabilitacionPrestador;
    @SerializedName("fechaHoraInicio")
    private String fechaHoraInicio;
    @SerializedName("fechaHoraFin")
    private String fechaHoraFin;
    @SerializedName("estado")
    private String estado;

    public String getNut() {
        return nut;
    }

    public void setNut(String nut) {
        this.nut = nut;
    }

    public String getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(String fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public String getCodHabilitacionPrestador() {
        return codHabilitacionPrestador;
    }

    public void setCodHabilitacionPrestador(String codHabilitacionPrestador) {
        this.codHabilitacionPrestador = codHabilitacionPrestador;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SolicitudAutorizacionFecha1DTO{nut=").append(nut);
        sb.append(", fechaHoraTransaccion=").append(fechaHoraTransaccion);
        sb.append(", codHabilitacionPrestador=").append(codHabilitacionPrestador);
        sb.append(", fechaHoraInicio=").append(fechaHoraInicio);
        sb.append(", fechaHoraFin=").append(fechaHoraFin);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }
 
}
