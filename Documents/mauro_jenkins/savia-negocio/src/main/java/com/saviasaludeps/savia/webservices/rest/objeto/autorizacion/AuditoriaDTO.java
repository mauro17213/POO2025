package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

/**
 *
 * @author LFRIVERA
 */
public class AuditoriaDTO {

    private String usuarioCrea;
    private String terminalCrea;
    private String fechaHoraCrea;

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(String fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }
}
