/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Stiven Giraldo
 */
public class ReportePortabilidad implements Serializable{
    
    private static final SimpleDateFormat formato1 = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat formato2 = new SimpleDateFormat("EEEE dd 'de' MMMM 'del año' yyyy", new Locale("es","CO"));
    
    private String id;
    private String ruta;
    private String nombreArchivo;
    private String strAfiliado;
    private String strIdentificacion;
    private String strGenero;
    private String strFechaNacimiento;
    private String strFechaSisben;
    private String strMunicipioAfiliacion;
    private String strEstadoAfiliacion;
    private String strIpsPortabilidad;
    private String strSedeIpsPortabilidad;
    private String strFechaInicial;
    private String strFechaFinal;
    private String strUsuarioImprime;
    private String strCodigoVerificacion;
    private Date dtmFechaHoy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStrAfiliado() {
        return strAfiliado;
    }

    public void setStrAfiliado(String strAfiliado) {
        this.strAfiliado = strAfiliado;
    }

    public String getStrIdentificacion() {
        return strIdentificacion;
    }

    public void setStrIdentificacion(String strIdentificacion) {
        this.strIdentificacion = strIdentificacion;
    }

    public String getStrGenero() {
        return strGenero;
    }

    public void setStrGenero(String strGenero) {
        //2022-09-23 jyperez ajustamos para que reciba simplemente el valor.
        /*if(strGenero.equals("1")){
            strGenero = "Femenino";
        }else{
            if(strGenero.equals("2")){
                strGenero = "Masculino";
            }else{
                strGenero = "Sin identificar";
            }
        }*/
        if (strGenero != null && !strGenero.equals("")) {
            this.strGenero = strGenero;
        } else {
            this.strGenero = "Sin identificar";
        }
    }

    public String getStrFechaNacimiento() {
        return strFechaNacimiento;
    }

    public void setStrFechaNacimiento(String strFechaNacimiento) {
        this.strFechaNacimiento = strFechaNacimiento;
    }

    public String getStrFechaSisben() {
        return strFechaSisben;
    }

    public void setStrFechaSisben(String strFechaSisben) {
        this.strFechaSisben = strFechaSisben;
    }

    public String getStrMunicipioAfiliacion() {
        return strMunicipioAfiliacion;
    }

    public void setStrMunicipioAfiliacion(String strMunicipioAfiliacion) {
        this.strMunicipioAfiliacion = strMunicipioAfiliacion;
    }

    public String getStrEstadoAfiliacion() {
        return strEstadoAfiliacion;
    }

    public void setStrEstadoAfiliacion(String strEstadoAfiliacion) {
        this.strEstadoAfiliacion = strEstadoAfiliacion;
    }

    public String getStrIpsPortabilidad() {
        return strIpsPortabilidad;
    }

    public void setStrIpsPortabilidad(String strIpsPortabilidad) {
        this.strIpsPortabilidad = strIpsPortabilidad;
    }

    public String getStrSedeIpsPortabilidad() {
        return strSedeIpsPortabilidad;
    }

    public void setStrSedeIpsPortabilidad(String strSedeIpsPortabilidad) {
        this.strSedeIpsPortabilidad = strSedeIpsPortabilidad;
    }

    public String getStrFechaInicial() {
        return strFechaInicial;
    }

    public void setStrFechaInicial(String strFechaInicial) throws ParseException {
        if(strFechaInicial != null && !strFechaInicial.equals("")){
            Date fecha = formato1.parse(strFechaInicial);
            strFechaInicial = formato2.format(fecha);
        }        
        this.strFechaInicial = strFechaInicial;
    }

    public String getStrFechaFinal() {
        return strFechaFinal;
    }

    public void setStrFechaFinal(String strFechaFinal) throws ParseException {
        if(strFechaFinal != null && !strFechaFinal.equals("")){
            Date fecha = formato1.parse(strFechaFinal);
            strFechaFinal = formato2.format(fecha);
        }        
        this.strFechaFinal = strFechaFinal;
    }

    public String getStrUsuarioImprime() {
        return strUsuarioImprime;
    }

    public void setStrUsuarioImprime(String strUsuarioImprime) {
        this.strUsuarioImprime = strUsuarioImprime;
    }

    public Date getDtmFechaHoy() {
        dtmFechaHoy = new Date();
        return dtmFechaHoy;
    }

    public void setDtmFechaHoy(Date dtmFechaHoy) {
        this.dtmFechaHoy = dtmFechaHoy;
    }

    @Override
    public String toString() {
        return "ReportePortabilidad{" + "id=" + id + ", strAfiliado=" + strAfiliado + ", strIdentificacion=" + strIdentificacion + ", strGenero=" + strGenero + ", strFechaNacimiento=" + strFechaNacimiento + ", strFechaSisben=" + strFechaSisben + ", strMunicipioAfiliacion=" + strMunicipioAfiliacion + ", strEstadoAfiliacion=" + strEstadoAfiliacion + ", strIpsPortabilidad=" + strIpsPortabilidad + ", strSedeIpsPortabilidad=" + strSedeIpsPortabilidad + ", strFechaInicial=" + strFechaInicial + ", strFechaFinal=" + strFechaFinal + ", strUsuarioImprime=" + strUsuarioImprime + ", dtmFechaHoy=" + dtmFechaHoy + '}';
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the strCodigoVerificacion
     */
    public String getStrCodigoVerificacion() {
        return strCodigoVerificacion;
    }

    /**
     * @param strCodigoVerificacion the strCodigoVerificacion to set
     */
    public void setStrCodigoVerificacion(String strCodigoVerificacion) {
        this.strCodigoVerificacion = strCodigoVerificacion;
    }
    
  
    
    
    
}
