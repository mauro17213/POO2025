package com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento;

public class noDireccionado {
    
    private Long noPrescripcion;
    private String tipoTec;
    private Integer conTec;
    private String tipoIDPaciente;
    private String noIDPaciente;
    private Integer causaNoEntrega;
    private Long noPrescripcionAsociada;
    private Integer conTecAsociada;

    public Long getNoPrescripcion() {
        return noPrescripcion;
    }

    public void setNoPrescripcion(Long noPrescripcion) {
        this.noPrescripcion = noPrescripcion;
    }

    public String getTipoTec() {
        return tipoTec;
    }

    public void setTipoTec(String tipoTec) {
        this.tipoTec = tipoTec;
    }

    public Integer getConTec() {
        return conTec;
    }

    public void setConTec(Integer conTec) {
        this.conTec = conTec;
    }

    public String getTipoIDPaciente() {
        return tipoIDPaciente;
    }

    public void setTipoIDPaciente(String tipoIDPaciente) {
        this.tipoIDPaciente = tipoIDPaciente;
    }

    public String getNoIDPaciente() {
        return noIDPaciente;
    }

    public void setNoIDPaciente(String noIDPaciente) {
        this.noIDPaciente = noIDPaciente;
    }

    public Integer getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(Integer causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public Long getNoPrescripcionAsociada() {
        return noPrescripcionAsociada;
    }

    public void setNoPrescripcionAsociada(Long noPrescripcionAsociada) {
        this.noPrescripcionAsociada = noPrescripcionAsociada;
    }

    public Integer getConTecAsociada() {
        return conTecAsociada;
    }

    public void setConTecAsociada(Integer conTecAsociada) {
        this.conTecAsociada = conTecAsociada;
    }
}
