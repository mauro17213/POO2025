/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

/**
 *
 * @author jdlopez
 * clase logica para llevar datos de operacion en una ejecucion de insumo calculo para variables tipo calculo
 */
public class PeOperando {
    private String nombre;//nombre unico de la variable (operando)
    private Integer tipo; //tipo de variable (operando)
    private Object valor; //valor del operador en su ejecucion

    public PeOperando() {
    }

    public PeOperando(PeVariableValor variableValor) {
        this.nombre = variableValor.getNombre();
        this.tipo = variableValor.getTipo();
        this.valor = variableValor.getValorObject();
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PeOperador{" + "nombre=" + nombre + ", tipo=" + tipo + ", valor=" + valor + '}';
    }
    
}
