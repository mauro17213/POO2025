/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

/**
 *
 * @author pavacca
 */
public class AusDatoGrafica {
    private String nombre;
    private int cantidadCerrados;
    private int cantidadVencidos;
    private int cantidadPendientes;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadCerrados() {
        return cantidadCerrados;
    }

    public void setCantidadCerrados(int cantidadCerrados) {
        this.cantidadCerrados = cantidadCerrados;
    }

    public int getCantidadVencidos() {
        return cantidadVencidos;
    }

    public void setCantidadVencidos(int cantidadVencidos) {
        this.cantidadVencidos = cantidadVencidos;
    }

    public int getCantidadPendientes() {
        return cantidadPendientes;
    }

    public void setCantidadPendientes(int cantidadPendientes) {
        this.cantidadPendientes = cantidadPendientes;
    }

    @Override
    public String toString() {
        return "AusDatoGrafica{" + "nombre=" + nombre + ", cantidadCerrados=" + cantidadCerrados + ", cantidadVencidos=" + cantidadVencidos + ", cantidadPendientes=" + cantidadPendientes + '}';
    }
}
