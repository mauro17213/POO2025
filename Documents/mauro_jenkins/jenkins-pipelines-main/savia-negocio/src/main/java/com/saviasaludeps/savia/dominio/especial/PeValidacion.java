/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import java.io.Serializable;

/**
 *
 * @author jdlopez
 * Su tipologia indica la validacion a realizar ( PeTipoValidacion ).
 * Puede tener cualquier tipo de dato en su respectivo valor (Integer, String, Date, BigDecimal)
 * de acuerdo al tipo de variable o valor de variable.
 */
public class PeValidacion implements Serializable {
    private String nombre;
    private Integer tipo;
    private Object valor; 

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer codigo) {
        this.tipo = codigo;
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

    @Override
    public String toString() {
        return "PeValidacion{" + "tipo=" + tipo + ", nombre=" + nombre + ", valor=" + valor + '}';
    }

  
}
