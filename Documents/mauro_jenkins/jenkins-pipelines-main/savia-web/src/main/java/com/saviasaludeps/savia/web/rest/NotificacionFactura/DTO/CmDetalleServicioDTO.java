package com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO;


public class CmDetalleServicioDTO {

    private String municipio = "";
    private String conceptoContable = "";
    private String valorOperacion = "";

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public String getValorOperacion() {
        return valorOperacion;
    }

    public void setValorOperacion(String valorOperacion) {
        this.valorOperacion = valorOperacion;
    }

}
