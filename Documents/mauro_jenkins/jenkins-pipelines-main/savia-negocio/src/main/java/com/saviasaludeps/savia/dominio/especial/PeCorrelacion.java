/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jdlopez
 */
public class PeCorrelacion implements Serializable {
    private String correlacion; //nombre de la variable a relacionar
    private Integer tipo; //tipo de operacion a ejecutar (PeTipoCorrelacion)
    private Object valorVariable;
    private Object valorCorrelacion;
    private Boolean match;
    private Map<String, List<Object>> combinaciones;
    
    public String getCorrelacion() {
        return correlacion;
    }

    public void setCorrelacion(String correlacion) {
        this.correlacion = correlacion;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Object getValorVariable() {
        return valorVariable;
    }

    public void setValorVariable(Object valorVariable) {
        this.valorVariable = valorVariable;
    }

    public Object getValorCorrelacion() {
        return valorCorrelacion;
    }

    public void setValorCorrelacion(Object valorCorrelacion) {
        this.valorCorrelacion = valorCorrelacion;
    }

    public Boolean getMatch() {
        return match;
    }

    public void setMatch(Boolean match) {
        this.match = match;
    }

    
    public Map<String, List<Object>> getCombinaciones() {
        return combinaciones;
    }

    public void setCombinaciones(Map<String, List<Object>> combinaciones) {
        this.combinaciones = combinaciones;
    }

    @Override
    public String toString() {
        return "PeCorrelacion{" + "correlacion=" + correlacion + ", tipo=" + tipo + ", valorVariable=" + valorVariable + ", valorCorrelacion=" + valorCorrelacion + ", match=" + match + ", combinaciones=" + combinaciones + '}';
    }

}
