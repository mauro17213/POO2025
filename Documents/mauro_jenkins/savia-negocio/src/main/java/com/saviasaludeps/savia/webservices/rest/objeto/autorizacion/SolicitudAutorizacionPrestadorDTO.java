
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SolicitudAutorizacionPrestadorDTO implements Serializable {

    @SerializedName("nut")
    private String nut;
    @SerializedName("fechaHoraTransaccion")
    private String fechaHoraTransaccion;
    @SerializedName("codHabilitacionPrestador")
    private String codHabilitacionPrestador;
    @SerializedName("tipoDocumento")
    private String tipoDocumento;
    @SerializedName("numeroDocumento")
    private String numeroDocumento;
    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SolicitudAutorizacionPrestadorDTO{nut=").append(nut);
        sb.append(", fechaHoraTransaccion=").append(fechaHoraTransaccion);
        sb.append(", codHabilitacionPrestador=").append(codHabilitacionPrestador);
        sb.append(", tipoDocumento=").append(tipoDocumento);
        sb.append(", numeroDocumento=").append(numeroDocumento);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append('}');
        return sb.toString();
    }
    

}
