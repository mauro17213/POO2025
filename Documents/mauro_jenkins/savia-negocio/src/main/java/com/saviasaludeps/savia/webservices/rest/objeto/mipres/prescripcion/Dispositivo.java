/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class Dispositivo implements Serializable {

    private Integer ConOrden;
    private Integer TipoPrest;
    private Integer CausaS1;
    private String CodDisp;
    private String CanForm;
    private String CadaFreUso;
    private Integer CodFreUso;
    private String Cant;
    private Integer CodPerDurTrat;
    private String CantTotal;
    private String JustNoPBS;
    private String IndRec;
    private Integer EstJM;

    public Integer getConOrden() {
        return ConOrden;
    }

    public void setConOrden(Integer ConOrden) {
        this.ConOrden = ConOrden;
    }

    public Integer getTipoPrest() {
        return TipoPrest;
    }

    public void setTipoPrest(Integer TipoPrest) {
        this.TipoPrest = TipoPrest;
    }

    public Integer getCausaS1() {
        return CausaS1;
    }

    public void setCausaS1(Integer CausaS1) {
        this.CausaS1 = CausaS1;
    }

    public String getCodDisp() {
        return CodDisp;
    }

    public void setCodDisp(String CodDisp) {
        this.CodDisp = CodDisp;
    }

    public String getCanForm() {
        return CanForm;
    }

    public void setCanForm(String CanForm) {
        this.CanForm = CanForm;
    }

    public String getCadaFreUso() {
        return CadaFreUso;
    }

    public void setCadaFreUso(String CadaFreUso) {
        this.CadaFreUso = CadaFreUso;
    }

    public Integer getCodFreUso() {
        return CodFreUso;
    }

    public void setCodFreUso(Integer CodFreUso) {
        this.CodFreUso = CodFreUso;
    }

    public String getCant() {
        return Cant;
    }

    public void setCant(String Cant) {
        this.Cant = Cant;
    }

    public Integer getCodPerDurTrat() {
        return CodPerDurTrat;
    }

    public void setCodPerDurTrat(Integer CodPerDurTrat) {
        this.CodPerDurTrat = CodPerDurTrat;
    }

    public String getCantTotal() {
        return CantTotal;
    }

    public void setCantTotal(String CantTotal) {
        this.CantTotal = CantTotal;
    }

    public String getJustNoPBS() {
        return JustNoPBS;
    }

    public void setJustNoPBS(String JustNoPBS) {
        this.JustNoPBS = JustNoPBS;
    }

    public String getIndRec() {
        return IndRec;
    }

    public void setIndRec(String IndRec) {
        this.IndRec = IndRec;
    }

    public Integer getEstJM() {
        return EstJM;
    }

    public void setEstJM(Integer EstJM) {
        this.EstJM = EstJM;
    }

}
