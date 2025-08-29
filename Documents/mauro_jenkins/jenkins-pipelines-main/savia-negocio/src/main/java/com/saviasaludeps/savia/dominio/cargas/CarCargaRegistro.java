/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cargas;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author aguevara
 */
public class CarCargaRegistro extends Auditoria{
    
    
    private Integer id;
    private String jsonDatos;
    private boolean fallido;
    private String fallos;
    private int tipo;
    private int fila;
    private CarCarga carCargasId;
    
    public CarCargaRegistro(){
        
    }

    public CarCargaRegistro(Integer id, String jsonDatos, boolean fallido, String fallos) {
        this.id = id;
        this.jsonDatos = jsonDatos;
        this.fallido = fallido;
        this.fallos = fallos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsonDatos() {
        return jsonDatos;
    }

    public void setJsonDatos(String jsonDatos) {
        this.jsonDatos = jsonDatos;
    }

    public boolean isFallido() {
        return fallido;
    }

    public void setFallido(boolean fallido) {
        this.fallido = fallido;
    }

    public String getFallos() {
        return fallos;
    }

    public void setFallos(String fallos) {
        this.fallos = fallos;
    }

    public CarCarga getCarCargasId() {
        return carCargasId;
    }

    public void setCarCargasId(CarCarga carCargasId) {
        this.carCargasId = carCargasId;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    @Override
    public String toString() {
        return "CarCargaRegistro{" + "id=" + id + ", jsonDatos=" + jsonDatos + ", fallido=" + fallido + ", fallos=" + fallos + ", tipo=" + tipo + ", fila=" + fila + ", carCargasId=" + carCargasId + '}';
    }
    
    


    
    

    

    
}
