/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jdlopez
 */
public class PeEtapaCalculo implements Serializable {
    private List<String> operandos;
    private String operacion; //(PeOperacion)
    private String asignacionTemporal; //opcional

    public List<String> getOperandos() {
        return operandos;
    }

    public void setOperandos(List<String> operandos) {
        this.operandos = operandos;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getAsignacionTemporal() {
        return asignacionTemporal;
    }

    public void setAsignacionTemporal(String asignacionTemporal) {
        this.asignacionTemporal = asignacionTemporal;
    }

    @Override
    public String toString() {
        return "PeEtapaCalculo{" + "operandos=" + operandos + ", operacion=" + operacion + ", asignacionTemporal=" + asignacionTemporal + '}';
    }
}
