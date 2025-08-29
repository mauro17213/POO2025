/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author acuartas
 */
public class GatTaquillaServicio extends Auditoria {

    private Integer id;
    private int maeTipoServicioId;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private GatSedeTaquilla gatSedeTaquillaId;
    private List<GatAtencion> listaGatAtenciones;

    public GatTaquillaServicio() {
    }

    public GatTaquillaServicio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(int maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
    }

    public GatSedeTaquilla getGatSedeTaquillaId() {
        return gatSedeTaquillaId;
    }

    public void setGatSedeTaquillaId(GatSedeTaquilla gatSedeTaquillaId) {
        this.gatSedeTaquillaId = gatSedeTaquillaId;
    }

    public List<GatAtencion> getListaGatAtenciones() {
        return listaGatAtenciones;
    }

    public void setListaGatAtenciones(List<GatAtencion> listaGatAtenciones) {
        this.listaGatAtenciones = listaGatAtenciones;
    }

    
    
    @Override
    public String toString() {
        return "GatTaquillaServicio{" + "id=" + id + ", maeTipoServicioId=" + maeTipoServicioId + ", maeTipoServicioCodigo=" + maeTipoServicioCodigo + ", maeTipoServicioValor=" + maeTipoServicioValor + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", gatSedeTaquillaId=" + gatSedeTaquillaId + '}';
    }
    
}
