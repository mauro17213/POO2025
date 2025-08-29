
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class SolicitudReporteEntregasDTO implements Serializable {

    @SerializedName("nut")
    private String nut;
    @SerializedName("fechaHoraTransaccion")
    private String fechaHoraTransaccion;
    @SerializedName("codHabilitacionPrestador")
    private String codHabilitacionPrestador;
    @SerializedName("numeroAutorizacion")
    private String numeroAutorizacion;
    @SerializedName("reclamaAfiliado")
    private String reclamaAfiliado;
    @SerializedName("fechaHoraEntrega")
    private String fechaHoraEntrega;
    @SerializedName("fuenteOrigen")
    private String fuenteOrigen;
    @SerializedName("nombreReclama")
    private String nombreReclama;
    @SerializedName("telefonoReclama")
    private String telefonoReclama;
    @SerializedName("celularReclama")
    private String celularReclama;
    @SerializedName("registros")
    private String registros;
    @SerializedName("entrega")
    private List<SolicitudReporteEntregas2DTO> entrega;
    @SerializedName("usuarioCrea")
    private String usuarioCrea;
    @SerializedName("fechaHoraCrea")
    private String fechaHoraCrea;
    @SerializedName("terminalCrea")
    private String terminalCrea;

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

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getReclamaAfiliado() {
        return reclamaAfiliado;
    }

    public void setReclamaAfiliado(String reclamaAfiliado) {
        this.reclamaAfiliado = reclamaAfiliado;
    }

    public String getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(String fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public String getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(String fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public String getNombreReclama() {
        return nombreReclama;
    }

    public void setNombreReclama(String nombreReclama) {
        this.nombreReclama = nombreReclama;
    }

    public String getTelefonoReclama() {
        return telefonoReclama;
    }

    public void setTelefonoReclama(String telefonoReclama) {
        this.telefonoReclama = telefonoReclama;
    }

    public String getCelularReclama() {
        return celularReclama;
    }

    public void setCelularReclama(String celularReclama) {
        this.celularReclama = celularReclama;
    }

    public String getRegistros() {
        return registros;
    }

    public void setRegistros(String registros) {
        this.registros = registros;
    }

    public List<SolicitudReporteEntregas2DTO> getEntrega() {
        return entrega;
    }

    public void setEntrega(List<SolicitudReporteEntregas2DTO> entrega) {
        this.entrega = entrega;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(String fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SolicitudReporteEntregasDTO{nut=").append(nut);
        sb.append(", fechaHoraTransaccion=").append(fechaHoraTransaccion);
        sb.append(", codHabilitacionPrestador=").append(codHabilitacionPrestador);
        sb.append(", numeroAutorizacion=").append(numeroAutorizacion);
        sb.append(", reclamaAfiliado=").append(reclamaAfiliado);
        sb.append(", fechaHoraEntrega=").append(fechaHoraEntrega);
        sb.append(", fuenteOrigen=").append(fuenteOrigen);
        sb.append(", nombreReclama=").append(nombreReclama);
        sb.append(", telefonoReclama=").append(telefonoReclama);
        sb.append(", celularReclama=").append(celularReclama);
        sb.append(", registros=").append(registros);
        sb.append(", entrega=").append(entrega);
        sb.append(", usuarioCrea=").append(usuarioCrea);
        sb.append(", fechaHoraCrea=").append(fechaHoraCrea);
        sb.append(", terminalCrea=").append(terminalCrea);
        sb.append('}');
        return sb.toString();
    }
    
}
