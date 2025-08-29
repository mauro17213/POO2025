package com.saviasaludeps.savia.solicitud.dominio;

public class TecnologiaDTO {
    private int idTecnologia;
    private String codigoTecnologia;
    private int idAfiliado;
    private int tipoTecnologia;
    private int estadoItemSolicitud;
    private int anexo3Id;

    public int getIdTecnologia() {
        return idTecnologia;
    }

    public void setIdTecnologia(int idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getEstadoItemSolicitud() {
        return estadoItemSolicitud;
    }

    public void setEstadoItemSolicitud(int estadoItemSolicitud) {
        this.estadoItemSolicitud = estadoItemSolicitud;
    }

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public int getAnexo3Id() {
        return anexo3Id;
    }

    public void setAnexo3Id(int anexo3Id) {
        this.anexo3Id = anexo3Id;
    }
            
}
