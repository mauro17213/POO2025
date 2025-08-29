/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jdlopez
 */
public class PeVariableValorHistorico extends Auditoria {

    private Integer id;
    private int peAfiliadosProgramasId;
    private int peProgramasId;
    private int peVariablesId;
    private int peVariablesValoresId;
    private Integer peCargaVariablesId;
    private String valor;

    public PeVariableValorHistorico() {
    }

    public PeVariableValorHistorico(PeVariableValor variableValor) {
        this.peAfiliadosProgramasId = variableValor.getIdAfiliado();
        this.peVariablesId = variableValor.getIdVariable();
        this.peVariablesValoresId = variableValor.getId();
        this.valor = variableValor.getValor();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(int peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
    }

    public int getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(int peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public int getPeVariablesId() {
        return peVariablesId;
    }

    public void setPeVariablesId(int peVariablesId) {
        this.peVariablesId = peVariablesId;
    }

    public int getPeVariablesValoresId() {
        return peVariablesValoresId;
    }

    public void setPeVariablesValoresId(int peVariablesValoresId) {
        this.peVariablesValoresId = peVariablesValoresId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getPeCargaVariablesId() {
        return peCargaVariablesId;
    }

    public void setPeCargaVariablesId(Integer peCargaVariablesId) {
        this.peCargaVariablesId = peCargaVariablesId;
    }
    

    @Override
    public String toString() {
        return "PeVariableValorHistorico{" + "id=" + id + ", peAfiliadosProgramasId=" + peAfiliadosProgramasId +
                ", peProgramasId=" + peProgramasId + ", peVariablesId=" + peVariablesId +
                ", peVariablesValoresId=" + peVariablesValoresId + ", valor=" + valor 
                + ", peCargasVariablesId=" + peCargaVariablesId + '}';
    }

}
