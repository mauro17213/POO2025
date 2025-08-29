/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.bean.DTO;

/**
 *
 * @author aguevara
 */
public class JsonDatoDTO {
    
    private String tipo;
    private String valor;
    private String nombre;
    private String expresion;
    private String descripcion;

    public JsonDatoDTO() {
    }   
    

    // Getters y setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "JsonDatoDTO{" +
                "tipo='" + tipo + '\'' +
                ", valor='" + valor + '\'' +
                ", nombre='" + nombre + '\'' +
                ", expresion='" + expresion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    /**
     * @return the expresion
     */
    public String getExpresion() {
        return expresion;
    }

    /**
     * @param expresion the expresion to set
     */
    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
    
}
