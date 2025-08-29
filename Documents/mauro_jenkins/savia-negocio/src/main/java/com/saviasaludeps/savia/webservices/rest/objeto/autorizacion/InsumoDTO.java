package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

/**
 *
 * @author LFRIVERA
 */
public class InsumoDTO extends EntregaDTO {

    private String codigoInsumo;
    private String cantidadInsumo;
    private String duracionTratamiento;
    private String numEntregas;
    private String pbs;
    private String valorUnitarioInsumo;

    public String getCodigoInsumo() {
        return codigoInsumo;
    }

    public void setCodigoInsumo(String codigoInsumo) {
        this.codigoInsumo = codigoInsumo;
    }

    public String getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(String cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
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

    public String getValorUnitarioInsumo() {
        return valorUnitarioInsumo;
    }

    public void setValorUnitarioInsumo(String valorUnitarioInsumo) {
        this.valorUnitarioInsumo = valorUnitarioInsumo;
    }
}
