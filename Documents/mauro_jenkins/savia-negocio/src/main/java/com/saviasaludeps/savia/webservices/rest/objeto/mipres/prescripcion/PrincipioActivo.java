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
public class PrincipioActivo implements Serializable {

    private Integer ConOrden;
    private String CodPriAct;
    private String ConcCant;
    private String UMedConc;
    private String CantCont;
    private String UMedCantCont;

    public Integer getConOrden() {
        return ConOrden;
    }

    public void setConOrden(Integer ConOrden) {
        this.ConOrden = ConOrden;
    }

    public String getCodPriAct() {
        return CodPriAct;
    }

    public void setCodPriAct(String CodPriAct) {
        this.CodPriAct = CodPriAct;
    }

    public String getConcCant() {
        return ConcCant;
    }

    public void setConcCant(String ConcCant) {
        this.ConcCant = ConcCant;
    }

    public String getUMedConc() {
        return UMedConc;
    }

    public void setUMedConc(String UMedConc) {
        this.UMedConc = UMedConc;
    }

    public String getCantCont() {
        return CantCont;
    }

    public void setCantCont(String CantCont) {
        this.CantCont = CantCont;
    }

    public String getUMedCantCont() {
        return UMedCantCont;
    }

    public void setUMedCantCont(String UMedCantCont) {
        this.UMedCantCont = UMedCantCont;
    }

}
