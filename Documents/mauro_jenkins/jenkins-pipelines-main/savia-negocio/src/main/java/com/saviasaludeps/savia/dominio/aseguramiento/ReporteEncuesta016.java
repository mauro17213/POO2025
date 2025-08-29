/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class ReporteEncuesta016 implements Serializable {
    
    private String id;
    private String strMunicipio;
    private String strNombre;
    private String strFecha;
    private String strDocumento ;
    private String strObservaciones;
    private Boolean blnRespuesta1;
    private Boolean blnRespuesta2;
    private Boolean blnRespuesta3;
    private Boolean blnRespuesta4;
    private Boolean blnRespuesta5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getStrMunicipio() {
        return strMunicipio;
    }

    public void setStrMunicipio(String strMunicipio) {
        this.strMunicipio = strMunicipio;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getStrDocumento() {
        return strDocumento;
    }

    public void setStrDocumento(String strDocumento) {
        this.strDocumento = strDocumento;
    }

    public String getStrObservaciones() {
        return strObservaciones;
    }

    public void setStrObservaciones(String strObservaciones) {
        this.strObservaciones = strObservaciones;
    }

    public Boolean getBlnRespuesta1() {
        return blnRespuesta1;
    }

    public void setBlnRespuesta1(Boolean blnRespuesta1) {
        this.blnRespuesta1 = blnRespuesta1;
    }

    public Boolean getBlnRespuesta2() {
        return blnRespuesta2;
    }

    public void setBlnRespuesta2(Boolean blnRespuesta2) {
        this.blnRespuesta2 = blnRespuesta2;
    }

    public Boolean getBlnRespuesta3() {
        return blnRespuesta3;
    }

    public void setBlnRespuesta3(Boolean blnRespuesta3) {
        this.blnRespuesta3 = blnRespuesta3;
    }

    public Boolean getBlnRespuesta4() {
        return blnRespuesta4;
    }

    public void setBlnRespuesta4(Boolean blnRespuesta4) {
        this.blnRespuesta4 = blnRespuesta4;
    }

    public Boolean getBlnRespuesta5() {
        return blnRespuesta5;
    }

    public void setBlnRespuesta5(Boolean blnRespuesta5) {
        this.blnRespuesta5 = blnRespuesta5;
    }

    @Override
    public String toString() {
        return "ReporteEncuesta016{" + "id=" + id + ", strMunicipio=" + strMunicipio + ", strNombre=" + strNombre + ", dtmFecha=" + strFecha + ", strDocumento=" + strDocumento + ", strObservaciones=" + strObservaciones + ", blnRespuesta1=" + blnRespuesta1 + ", blnRespuesta2=" + blnRespuesta2 + ", blnRespuesta3=" + blnRespuesta3 + ", blnRespuesta4=" + blnRespuesta4 + ", blnRespuesta5=" + blnRespuesta5 + '}';
    }
    
}
