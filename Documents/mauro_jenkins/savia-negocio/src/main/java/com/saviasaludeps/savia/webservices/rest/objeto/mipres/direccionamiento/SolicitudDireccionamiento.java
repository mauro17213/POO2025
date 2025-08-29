package com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento;

public class SolicitudDireccionamiento {
    
    private String noPrescripcion;
    private String tipoTec;
    private int conTec;
    private String tipoIDPaciente;
    private String noIDPaciente;
    private int noEntrega;
    private int noSubEntrega;
    private String tipoIDProv;
    private String noIDProv;
    private String codMunEnt;
    private String FechaMaxEnt;
    private String cantTotAEntregar;
    private String dirPaciente;
    private String codSerTecAEntregar;

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

    public int getConTec() {
        return conTec;
    }

    public void setConTec(int conTec) {
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

    public int getNoEntrega() {
        return noEntrega;
    }

    public void setNoEntrega(int noEntrega) {
        this.noEntrega = noEntrega;
    }

    public int getNoSubEntrega() {
        return noSubEntrega;
    }

    public void setNoSubEntrega(int noSubEntrega) {
        this.noSubEntrega = noSubEntrega;
    }

    public String getTipoIDProv() {
        return tipoIDProv;
    }

    public void setTipoIDProv(String tipoIDProv) {
        this.tipoIDProv = tipoIDProv;
    }

    public String getNoIDProv() {
        return noIDProv;
    }

    public void setNoIDProv(String noIDProv) {
        this.noIDProv = noIDProv;
    }

    public String getCodMunEnt() {
        return codMunEnt;
    }

    public void setCodMunEnt(String codMunEnt) {
        this.codMunEnt = codMunEnt;
    }

    public String getFechaMaxEnt() {
        return FechaMaxEnt;
    }

    public void setFechaMaxEnt(String FechaMaxEnt) {
        this.FechaMaxEnt = FechaMaxEnt;
    }

    public String getCantTotAEntregar() {
        return cantTotAEntregar;
    }

    public void setCantTotAEntregar(String cantTotAEntregar) {
        this.cantTotAEntregar = cantTotAEntregar;
    }

    public String getDirPaciente() {
        return dirPaciente;
    }

    public void setDirPaciente(String dirPaciente) {
        this.dirPaciente = dirPaciente;
    }

    public String getCodSerTecAEntregar() {
        return codSerTecAEntregar;
    }

    public void setCodSerTecAEntregar(String codSerTecAEntregar) {
        this.codSerTecAEntregar = codSerTecAEntregar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SolicitudDireccionamiento{noPrescripcion=").append(noPrescripcion);
        sb.append(", tipoTec=").append(tipoTec);
        sb.append(", conTec=").append(conTec);
        sb.append(", tipoIDPaciente=").append(tipoIDPaciente);
        sb.append(", noIDPaciente=").append(noIDPaciente);
        sb.append(", noEntrega=").append(noEntrega);
        sb.append(", noSubEntrega=").append(noSubEntrega);
        sb.append(", tipoIDProv=").append(tipoIDProv);
        sb.append(", noIDProv=").append(noIDProv);
        sb.append(", codMunEnt=").append(codMunEnt);
        sb.append(", FechaMaxEnt=").append(FechaMaxEnt);
        sb.append(", cantTotAEntregar=").append(cantTotAEntregar);
        sb.append(", dirPaciente=").append(dirPaciente);
        sb.append(", codSerTecAEntregar=").append(codSerTecAEntregar);
        sb.append('}');
        return sb.toString();
    }
}
