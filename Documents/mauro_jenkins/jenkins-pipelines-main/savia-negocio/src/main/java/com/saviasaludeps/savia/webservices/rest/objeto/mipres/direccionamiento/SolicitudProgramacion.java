package com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento;

public class SolicitudProgramacion {
    
    private Integer id;
    private String fecMaxEnt;
    private String tipoIDSedeProv;
    private String noIDSedeProv;
    private String codSedeProv;
    private String codSerTecAEntregar;
    private String cantTotAEntregar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecMaxEnt() {
        return fecMaxEnt;
    }

    public void setFecMaxEnt(String fecMaxEnt) {
        this.fecMaxEnt = fecMaxEnt;
    }

    public String getTipoIDSedeProv() {
        return tipoIDSedeProv;
    }

    public void setTipoIDSedeProv(String tipoIDSedeProv) {
        this.tipoIDSedeProv = tipoIDSedeProv;
    }

    public String getNoIDSedeProv() {
        return noIDSedeProv;
    }

    public void setNoIDSedeProv(String noIDSedeProv) {
        this.noIDSedeProv = noIDSedeProv;
    }

    public String getCodSedeProv() {
        return codSedeProv;
    }

    public void setCodSedeProv(String codSedeProv) {
        this.codSedeProv = codSedeProv;
    }

    public String getCodSerTecAEntregar() {
        return codSerTecAEntregar;
    }

    public void setCodSerTecAEntregar(String codSerTecAEntregar) {
        this.codSerTecAEntregar = codSerTecAEntregar;
    }

    public String getCantTotAEntregar() {
        return cantTotAEntregar;
    }

    public void setCantTotAEntregar(String cantTotAEntregar) {
        this.cantTotAEntregar = cantTotAEntregar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SolicitudProgramacion{id=").append(id);
        sb.append(", fecMaxEnt=").append(fecMaxEnt);
        sb.append(", tipoIDSedeProv=").append(tipoIDSedeProv);
        sb.append(", noIDSedeProv=").append(noIDSedeProv);
        sb.append(", codSedeProv=").append(codSedeProv);
        sb.append(", codSerTecAEntregar=").append(codSerTecAEntregar);
        sb.append(", cantTotAEntregar=").append(cantTotAEntregar);
        sb.append('}');
        return sb.toString();
    }

}
