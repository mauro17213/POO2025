package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

/**
 *
 * @author LFRIVERA
 */
public class MedicamentoDTO extends EntregaDTO {

    private String cum;
    private String viaAdministracion;
    private String cantidadMedicamento;
    private String dosisMedicamento;
    private String frecuenciaMedicamento;
    private String posologiaMedicamento;
    private String duracionTratamiento;
    private String numEntregas;
    private String pbs;

    public String getCum() {
        return cum;
    }

    public void setCum(String cum) {
        this.cum = cum;
    }

    public String getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(String viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getCantidadMedicamento() {
        return cantidadMedicamento;
    }

    public void setCantidadMedicamento(String cantidadMedicamento) {
        this.cantidadMedicamento = cantidadMedicamento;
    }

    public String getDosisMedicamento() {
        return dosisMedicamento;
    }

    public void setDosisMedicamento(String dosisMedicamento) {
        this.dosisMedicamento = dosisMedicamento;
    }

    public String getFrecuenciaMedicamento() {
        return frecuenciaMedicamento;
    }

    public void setFrecuenciaMedicamento(String frecuenciaMedicamento) {
        this.frecuenciaMedicamento = frecuenciaMedicamento;
    }

    public String getPosologiaMedicamento() {
        return posologiaMedicamento;
    }

    public void setPosologiaMedicamento(String posologiaMedicamento) {
        this.posologiaMedicamento = posologiaMedicamento;
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
