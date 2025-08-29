/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class AsegMaNovedad extends Auditoria {

    private Integer id;
    private String codigoNovedad;
    private String descripcionNovedad;
    private Integer regimen;
    private boolean activo;
    private String codigoNovedadPadre;
    private Boolean reporteNormativo;
    private List<AsegRegistroNovedad> listaAsegRegistroNovedades;

    public AsegMaNovedad() {
    }

    public AsegMaNovedad(Integer id) {
        this.id = id;
    }

    public AsegMaNovedad(Integer id, String codigoNovedad, String descripcionNovedad, Integer regimen, boolean activo) {
        this.id = id;
        this.codigoNovedad = codigoNovedad;
        this.descripcionNovedad = descripcionNovedad;
        this.regimen = regimen;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoNovedad() {
        return codigoNovedad;
    }

    public void setCodigoNovedad(String codigoNovedad) {
        this.codigoNovedad = codigoNovedad;
    }

    public String getDescripcionNovedad() {
        return descripcionNovedad;
    }

    public void setDescripcionNovedad(String descripcionNovedad) {
        this.descripcionNovedad = descripcionNovedad;
    }

    public Integer getRegimen() {
        return regimen;
    }

    public void setRegimen(Integer regimen) {
        this.regimen = regimen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCodigoNovedadPadre() {
        return codigoNovedadPadre;
    }

    public void setCodigoNovedadPadre(String codigoNovedadPadre) {
        this.codigoNovedadPadre = codigoNovedadPadre;
    }

    public Boolean getReporteNormativo() {
        return reporteNormativo;
    }

    public void setReporteNormativo(Boolean reporteNormativo) {
        this.reporteNormativo = reporteNormativo;
    }

    public List<AsegRegistroNovedad> getListaAsegRegistroNovedades() {
        return listaAsegRegistroNovedades;
    }

    public void setListaAsegRegistroNovedades(List<AsegRegistroNovedad> listaAsegRegistroNovedades) {
        this.listaAsegRegistroNovedades = listaAsegRegistroNovedades;
    }

    @Override
    public String toString() {
        return "AsegMaNovedad{" + "id=" + id + ", codigoNovedad=" + codigoNovedad + ", descripcionNovedad=" + descripcionNovedad + ", regimen=" + regimen + ", activo=" + activo + ", codigoNovedadPadre=" + codigoNovedadPadre + ", reporteNormativo=" + reporteNormativo + '}';
    }
    
}
