package com.saviasaludeps.savia.web.autorizacion.bean.DTO;

public class SolicitudDTO {
    String consecutivo;
    int idAfiliado;
    String codigoTecnologia;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    @Override
    public String toString() {
        return "SolicitudDTO{" + "consecutivo=" + consecutivo + ", idAfiliado=" + idAfiliado + ", codigoTecnologia=" + codigoTecnologia + '}';
    }
    
}
