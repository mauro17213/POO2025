/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import java.io.Serializable;

/**
 *
 * @author jdlopez
 */
public class PeVariableValorAlmacenado implements Serializable {

    private Integer idVariableValor;//id de variable valor almacenado en db
    private Object valor;//valor almacenado en db

    public PeVariableValorAlmacenado() {
    }

    public PeVariableValorAlmacenado(Integer idVariableValor, Object valor) {
        this.idVariableValor = idVariableValor;
        this.valor = valor;
    }
    
    public Integer getIdVariableValor() {
        return idVariableValor;
    }

    public void setIdVariableValor(Integer idVariableValor) {
        this.idVariableValor = idVariableValor;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valorAlmacenado) {
        this.valor = valorAlmacenado;
    }

    @Override
    public String toString() {
        return "PeVariableValorAlmacenado{" + "idVariableValor=" + idVariableValor + ", valorAlmacenado=" + valor + '}';
    }
}
