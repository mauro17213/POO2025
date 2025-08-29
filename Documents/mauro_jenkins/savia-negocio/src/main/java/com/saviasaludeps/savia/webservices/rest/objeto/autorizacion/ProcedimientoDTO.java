package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

/**
 *
 * @author LFRIVERA
 */
public class ProcedimientoDTO extends EntregaDTO {

    private String cups;
    private String cantidadProcedimiento;
    private String codigoHabilitacionProcedimiento;
    private String duracionTratamiento;
    private String numEntregas;
    private String pbs;

    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    public String getCantidadProcedimiento() {
        return cantidadProcedimiento;
    }

    public void setCantidadProcedimiento(String cantidadProcedimiento) {
        this.cantidadProcedimiento = cantidadProcedimiento;
    }

    public String getCodigoHabilitacionProcedimiento() {
        return codigoHabilitacionProcedimiento;
    }

    public void setCodigoHabilitacionProcedimiento(String codigoHabilitacionProcedimiento) {
        this.codigoHabilitacionProcedimiento = codigoHabilitacionProcedimiento;
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
