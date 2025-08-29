/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class AsegRadicadoNovedad extends Auditoria {

    private Integer id;
    private Boolean soporteNovedad;
    private String historicoAfiliado;
    private List<AsegAdjunto> listaAsegAdjuntos;
    private AsegAfiliado asegAfiliado;
    private List<AsegRegistroNovedad> listaAsegRegistroNovedades;

    public AsegRadicadoNovedad() {
    }

    public AsegRadicadoNovedad(Integer id) {
        this.id = id;
    }

    public AsegRadicadoNovedad(Integer id, Boolean soporteNovedad, String historicoAfiliado, Date fechaHoraCrea) {
        this.id = id;
        this.soporteNovedad = soporteNovedad;
        this.historicoAfiliado = historicoAfiliado;
        super.setFechaHoraCrea(fechaHoraCrea);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSoporteNovedad() {
        return soporteNovedad;
    }

    public void setSoporteNovedad(Boolean soporteNovedad) {
        this.soporteNovedad = soporteNovedad;
    }

    public String getHistoricoAfiliado() {
        return historicoAfiliado;
    }

    public void setHistoricoAfiliado(String historicoAfiliado) {
        this.historicoAfiliado = historicoAfiliado;
    }

    public List<AsegAdjunto> getListaAsegAdjuntos() {
        return listaAsegAdjuntos;
    }

    public void setListaAsegAdjuntos(List<AsegAdjunto> listaAsegAdjuntos) {
        this.listaAsegAdjuntos = listaAsegAdjuntos;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public List<AsegRegistroNovedad> getListaAsegRegistroNovedades() {
        return listaAsegRegistroNovedades;
    }

    public void setListaAsegRegistroNovedades(List<AsegRegistroNovedad> listaAsegRegistroNovedades) {
        this.listaAsegRegistroNovedades = listaAsegRegistroNovedades;
    }

    @Override
    public String toString() {
        return "AsegRadicadoNovedad{" + "id=" + id + ", soporteNovedad=" + soporteNovedad + ", historicoAfiliado=" + historicoAfiliado + ", listaAsegAdjuntos=" + listaAsegAdjuntos + ", asegAfiliado=" + asegAfiliado + '}';
    }
    
}
