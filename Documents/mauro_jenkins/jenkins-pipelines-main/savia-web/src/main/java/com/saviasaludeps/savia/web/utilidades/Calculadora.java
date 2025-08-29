/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;


/**
 *
 * @author rpalacic (SystemTech Integral)
 */
public class Calculadora {
    
    private double KwhValor;
    private double KvarValor;
    private double KwhTotal;
    private double KvarTotal;
    private double KwhPrecio;
    private double KvarPrecio;

    public double getKwhValor() {
        return KwhValor;
    }

    public void setKwhValor(double KwhValor) {
        this.KwhValor = KwhValor;
    }

    public double getKvarValor() {
        return KvarValor;
    }

    public void setKvarValor(double KvarValor) {
        this.KvarValor = KvarValor;
    }

    

    public double getKwhTotal() {
        return KwhTotal;
    }

    public void setKwhTotal(double KwhTotal) {
        this.KwhTotal = KwhTotal;
    }

    public double getKvarTotal() {
        return KvarTotal;
    }

    public void setKvarTotal(double KvarTotal) {
        this.KvarTotal = KvarTotal;
    }

    public double getKwhPrecio() {
        return KwhPrecio;
    }

    public void setKwhPrecio(double KwhPrecio) {
        this.KwhPrecio = KwhPrecio;
    }

    public double getKvarPrecio() {
        return KvarPrecio;
    }

    public void setKvarPrecio(double KvarPrecio) {
        this.KvarPrecio = KvarPrecio;
    }
}
