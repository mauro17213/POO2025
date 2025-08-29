package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

/**
 *
 * @author LFRIVERA
 */
public class PaqueteDTO extends EntregaDTO {

    private String codigoPaquete;
    private String cantidadPaquete;
    private String duracionTratamiento;
    private String numEntregas;
    private String pbs;

    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public String getCantidadPaquete() {
        return cantidadPaquete;
    }

    public void setCantidadPaquete(String cantidadPaquete) {
        this.cantidadPaquete = cantidadPaquete;
    }

    public String getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(String duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public String getNumEntregas() {
        return numEntregas;
    }

    public void setNumEntregas(String numEntregas) {
        this.numEntregas = numEntregas;
    }

    public String getPbs() {
        return pbs;
    }

    public void setPbs(String pbs) {
        this.pbs = pbs;
    }
}
