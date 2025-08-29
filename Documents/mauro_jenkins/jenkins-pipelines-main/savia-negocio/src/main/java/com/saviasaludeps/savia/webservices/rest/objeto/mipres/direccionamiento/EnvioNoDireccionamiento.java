package com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento;

public class EnvioNoDireccionamiento {
    
    private String noPrescripcion;
    private String tipoTec;
    private Integer conTec;
    private String tipoIDPaciente;
    private String noIDPaciente;
    private Integer causaNoEntrega;
    private String noPrescripcionAsociada;
    private Integer conTecAsociada;

    public String getNoPrescripcion() {
        return noPrescripcion;
    }

    public void setNoPrescripcion(String noPrescripcion) {
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

    public String getNoPrescripcionAsociada() {
        return noPrescripcionAsociada;
    }

    public void setNoPrescripcionAsociada(String noPrescripcionAsociada) {
        this.noPrescripcionAsociada = noPrescripcionAsociada;
    }

    public Integer getConTecAsociada() {
        return conTecAsociada;
    }

    public void setConTecAsociada(Integer conTecAsociada) {
        this.conTecAsociada = conTecAsociada;
    }

    public Integer getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(Integer causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("envioNoDireccionamiento{noPrescripcion=").append(noPrescripcion);
        sb.append(", tipoTec=").append(tipoTec);
        sb.append(", conTec=").append(conTec);
        sb.append(", tipoIDPaciente=").append(tipoIDPaciente);
        sb.append(", noIDPaciente=").append(noIDPaciente);
        sb.append(", causaNoEntrega=").append(causaNoEntrega);
        sb.append(", noPrescripcionAsociada=").append(noPrescripcionAsociada);
        sb.append(", conTecAsociada=").append(conTecAsociada);
        sb.append('}');
        return sb.toString();
    }
    
    

}
