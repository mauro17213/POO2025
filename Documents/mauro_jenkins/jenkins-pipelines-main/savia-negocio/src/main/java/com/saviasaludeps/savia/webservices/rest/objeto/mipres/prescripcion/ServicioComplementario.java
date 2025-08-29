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
public class ServicioComplementario implements Serializable {

    private Integer ConOrden;
    private Integer TipoPrest;
    private Integer TipTut;
    private Integer CausaS1;
    private Integer CausaS2;
    private Integer CausaS3;
    private Integer CausaS4;
    private String DescCausaS4;
    private Integer CausaS5;
    private String CodSerComp;
    private String DescSerComp;
    private String CanForm;
    private String CadaFreUso;
    private Integer CodFreUso;
    private String Cant;
    private String CantTotal;
    private Integer CodPerDurTrat;
    private Integer TipoTrans;
    private Integer ReqAcom;
    private String TipoIDAcomAlb;
    private String NroIDAcomAlb;
    private Integer ParentAcomAlb;
    private String NombAlb;
    private Integer CodMunOriAlb;
    private Integer CodMunDesAlb;
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

    public Integer getCausaS2() {
        return CausaS2;
    }

    public void setCausaS2(Integer CausaS2) {
        this.CausaS2 = CausaS2;
    }

    public Integer getCausaS3() {
        return CausaS3;
    }

    public void setCausaS3(Integer CausaS3) {
        this.CausaS3 = CausaS3;
    }

    public Integer getCausaS4() {
        return CausaS4;
    }

    public void setCausaS4(Integer CausaS4) {
        this.CausaS4 = CausaS4;
    }

    public String getDescCausaS4() {
        return DescCausaS4;
    }

    public void setDescCausaS4(String DescCausaS4) {
        this.DescCausaS4 = DescCausaS4;
    }

    public Integer getCausaS5() {
        return CausaS5;
    }

    public void setCausaS5(Integer CausaS5) {
        this.CausaS5 = CausaS5;
    }

    public String getCodSerComp() {
        return CodSerComp;
    }

    public void setCodSerComp(String CodSerComp) {
        this.CodSerComp = CodSerComp;
    }

    public String getDescSerComp() {
        return DescSerComp;
    }

    public void setDescSerComp(String DescSerComp) {
        this.DescSerComp = DescSerComp;
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

    public String getCantTotal() {
        return CantTotal;
    }

    public void setCantTotal(String CantTotal) {
        this.CantTotal = CantTotal;
    }

    public Integer getCodPerDurTrat() {
        return CodPerDurTrat;
    }

    public void setCodPerDurTrat(Integer CodPerDurTrat) {
        this.CodPerDurTrat = CodPerDurTrat;
    }

    public Integer getTipoTrans() {
        return TipoTrans;
    }

    public void setTipoTrans(Integer TipoTrans) {
        this.TipoTrans = TipoTrans;
    }

    public Integer getReqAcom() {
        return ReqAcom;
    }

    public void setReqAcom(Integer ReqAcom) {
        this.ReqAcom = ReqAcom;
    }

    public String getTipoIDAcomAlb() {
        return TipoIDAcomAlb;
    }

    public void setTipoIDAcomAlb(String TipoIDAcomAlb) {
        this.TipoIDAcomAlb = TipoIDAcomAlb;
    }

    public String getNroIDAcomAlb() {
        return NroIDAcomAlb;
    }

    public void setNroIDAcomAlb(String NroIDAcomAlb) {
        this.NroIDAcomAlb = NroIDAcomAlb;
    }

    public Integer getParentAcomAlb() {
        return ParentAcomAlb;
    }

    public void setParentAcomAlb(Integer ParentAcomAlb) {
        this.ParentAcomAlb = ParentAcomAlb;
    }

    public String getNombAlb() {
        return NombAlb;
    }

    public void setNombAlb(String NombAlb) {
        this.NombAlb = NombAlb;
    }

    public Integer getCodMunOriAlb() {
        return CodMunOriAlb;
    }

    public void setCodMunOriAlb(Integer CodMunOriAlb) {
        this.CodMunOriAlb = CodMunOriAlb;
    }

    public Integer getCodMunDesAlb() {
        return CodMunDesAlb;
    }

    public void setCodMunDesAlb(Integer CodMunDesAlb) {
        this.CodMunDesAlb = CodMunDesAlb;
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

    public Integer getTipTut() {
        return TipTut;
    }

    public void setTipTut(Integer TipTut) {
        this.TipTut = TipTut;
    }
    
}
