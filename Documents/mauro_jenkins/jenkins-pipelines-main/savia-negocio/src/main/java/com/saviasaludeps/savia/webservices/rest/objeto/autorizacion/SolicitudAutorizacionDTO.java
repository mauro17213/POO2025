/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class SolicitudAutorizacionDTO implements Serializable {

    @SerializedName("tipoDocumento")
    private String tipoDocumento;
    @SerializedName("documento")
    private String documento;
    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;
    @SerializedName("estado")
    private String estado;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
        sb.append("SolicitudAutorizacionDTO{tipoDocumento=").append(tipoDocumento);
        sb.append(", documento=").append(documento);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }
    
}
