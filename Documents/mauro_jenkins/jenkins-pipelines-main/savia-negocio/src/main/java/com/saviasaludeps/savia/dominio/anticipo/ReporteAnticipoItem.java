/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.anticipo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author NEXOS
 */
public class ReporteAnticipoItem implements Serializable{
    
    private String strMaTecnologiaValor;
    private String strNit;
    private String strPrestador;
    private BigDecimal bigValor;
    private Integer intCatidad;
    
    public ReporteAnticipoItem(){
        
    }

    public String getStrMaTecnologiaValor() {
        return strMaTecnologiaValor;
    }

    public void setStrMaTecnologiaValor(String strMaTecnologiaValor) {
        this.strMaTecnologiaValor = strMaTecnologiaValor;
    }

    public String getStrNit() {
        return strNit;
    }

    public void setStrNit(String strNit) {
        this.strNit = strNit;
    }

    public String getStrPrestador() {
        return strPrestador;
    }

    public void setStrPrestador(String strPrestador) {
        this.strPrestador = strPrestador;
    }

    public BigDecimal getBigValor() {
        return bigValor;
    }

    public void setBigValor(BigDecimal bigValor) {
        this.bigValor = bigValor;
    }

    public Integer getIntCatidad() {
        return intCatidad;
    }

    public void setIntCatidad(Integer intCatidad) {
        this.intCatidad = intCatidad;
    }

   

}
