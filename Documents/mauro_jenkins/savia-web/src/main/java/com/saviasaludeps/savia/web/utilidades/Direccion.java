/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import java.util.LinkedHashMap;

/**
 *
 * @author rpalacios
 */
public class Direccion {

    private String via;
    private String numero;
    private String ord1;
    private String orientacion1;
    private String placa1;
    private String ord2;
    private String orientacion2;
    private String placa2;
    private String descripcion;

    private LinkedHashMap<String, String> mapVias;
    private LinkedHashMap<String, String> mapOrd;
    private LinkedHashMap<String, String> mapOrientacion;

    public Direccion() {
        //Carga de vía
        mapVias = new LinkedHashMap();
        mapVias.put("SD", "Sin Dirección");
        mapVias.put("AV", "Avenida");
        mapVias.put("CL", "Calle");
        mapVias.put("CQ", "Circular");
        mapVias.put("CR", "Carrera");
        mapVias.put("DG", "Diagonal");
        mapVias.put("TR", "Transversal");
        //Cargar Ord
        mapOrd = new LinkedHashMap();
        mapOrd.put("A", "A");
        mapOrd.put("AA", "AA");
        mapOrd.put("AB", "AB");
        mapOrd.put("AC", "AC");
        mapOrd.put("AE", "AE");
        mapOrd.put("AF", "AF");
        mapOrd.put("B", "B");
        mapOrd.put("BB", "BB");
        mapOrd.put("BC", "BC");
        mapOrd.put("C", "C");
        mapOrd.put("CC", "CC");
        mapOrd.put("CCC", "CCC");
        mapOrd.put("D", "D");
        mapOrd.put("DA", "DA");
        mapOrd.put("DD", "DD");
        mapOrd.put("E", "E");
        mapOrd.put("EE", "EE");
        mapOrd.put("F", "F");
        mapOrd.put("FF", "FF");
        mapOrd.put("G", "G");
        mapOrd.put("GG", "GG");
        mapOrd.put("H", "H");
        mapOrd.put("HA", "HA");
        mapOrd.put("HB", "HB");
        mapOrd.put("HC", "HC");
        mapOrd.put("HD", "HD");
        mapOrd.put("HE", "HE");
        mapOrd.put("HF", "HF");
        mapOrd.put("HG", "HG");
        //Carga Orientación
        mapOrientacion = new LinkedHashMap();
        mapOrientacion.put("SUR", "SUR");
        mapOrientacion.put("ESTE", "ESTE");
        mapOrientacion.put("OESTE", "OESTE");
        mapOrientacion.put("NORTE", "NORTE");
    }

    public LinkedHashMap<String, String> getMapVias() {
        return mapVias;
    }

    public void setMapVias(LinkedHashMap<String, String> mapVias) {
        this.mapVias = mapVias;
    }

    public LinkedHashMap<String, String> getMapOrd() {
        return mapOrd;
    }

    public void setMapOrd(LinkedHashMap<String, String> mapOrd) {
        this.mapOrd = mapOrd;
    }

    public LinkedHashMap<String, String> getMapOrientacion() {
        return mapOrientacion;
    }

    public void setMapOrientacion(LinkedHashMap<String, String> mapOrientacion) {
        this.mapOrientacion = mapOrientacion;
    }

    public boolean isSinDireccion() {
        return (via != null && via.equals("SD"));
    }

    public String getDireccion() {
        String direccion = "";
        if (!via.equals("SD")) {
            direccion += (via == null || via.equals("")) ? "" : mapVias.get(via);
            direccion += (numero == null || numero.equals("")) ? "" : " " + numero.trim();
            direccion += (ord1 == null || ord1.equals("")) ? "" : " " + ord1;
            direccion += (orientacion1 == null || orientacion1.equals("")) ? "" : " " + orientacion1;
            direccion += (placa1 == null || placa1.equals("")) ? "" : " # " + placa1.trim();
            direccion += (ord2 == null || ord2.equals("")) ? "" : " " + ord2;
            direccion += (orientacion2 == null || orientacion2.equals("")) ? "" : " " + orientacion2;
            direccion += (placa2 == null || placa2.equals("")) ? "" : " - " + placa2.trim();
        }
        direccion += (descripcion == null || descripcion.equals("")) ? "" : " " + descripcion.trim();
        return direccion.trim();
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOrd1() {
        return ord1;
    }

    public void setOrd1(String ord1) {
        this.ord1 = ord1;
    }

    public String getOrientacion1() {
        return orientacion1;
    }

    public void setOrientacion1(String orientacion1) {
        this.orientacion1 = orientacion1;
    }

    public String getPlaca1() {
        return placa1;
    }

    public void setPlaca1(String placa1) {
        this.placa1 = placa1;
    }

    public String getOrd2() {
        return ord2;
    }

    public void setOrd2(String ord2) {
        this.ord2 = ord2;
    }

    public String getOrientacion2() {
        return orientacion2;
    }

    public void setOrientacion2(String orientacion2) {
        this.orientacion2 = orientacion2;
    }

    public String getPlaca2() {
        return placa2;
    }

    public void setPlaca2(String placa2) {
        this.placa2 = placa2;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
