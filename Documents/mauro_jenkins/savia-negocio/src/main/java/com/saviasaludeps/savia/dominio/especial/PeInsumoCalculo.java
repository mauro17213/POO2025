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
public class PeInsumoCalculo implements Serializable {
    private List<String> variables; //nombre de variables relacionadas
    private List<PeEtapaCalculo> etapas;

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public List<PeEtapaCalculo> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<PeEtapaCalculo> etapas) {
        this.etapas = etapas;
    }

    @Override
    public String toString() {
        return "PeInsumoCalculo{" + "variables=" + variables + ", etapas=" + etapas + '}';
    }
    
}
