/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class Medicamento implements Serializable {

    private Integer ConOrden;
    private Integer TipoMed;
    private Integer TipoPrest;
    private Integer CausaS1;
    private Integer CausaS2;
    private Integer CausaS3;
    private String MedPBSUtilizado;
    private Integer RznCausaS31;
    private String DescRzn31;
    private Integer RznCausaS32;
    private String DescRzn32;
    private Integer CausaS4;
    private String MedPBSDescartado;
    private Integer RznCausaS41;
    private String DescRzn41;
    private Integer RznCausaS42;
    private String DescRzn42;
    private Integer RznCausaS43;
    private String DescRzn43;
    private Integer RznCausaS44;
    private String DescRzn44;
    private Integer CausaS5;
    private Integer RznCausaS5;
    private Integer CausaS6;
    private String DescMedPrinAct;
    private String CodFF;
    private String CodVA;
    private String JustNoPBS;
    private String Dosis;
    private String DosisUM;
    private String NoFAdmon;
    private Integer CodFreAdmon;
    private Integer IndEsp;
    private String CanTrat;
    private Integer DurTrat;
    private String CantTotalF;
    private String UFCantTotal;
    private String IndRec;
    private Integer EstJM;
    private String DscMedPA;
    private List<PrincipioActivo> PrincipiosActivos;
    private List<IndicacionUNIRS> IndicacionesUNIRS;

    public Integer getConOrden() {
        return ConOrden;
    }

    public void setConOrden(Integer ConOrden) {
        this.ConOrden = ConOrden;
    }

    public Integer getTipoMed() {
        return TipoMed;
    }

    public void setTipoMed(Integer TipoMed) {
        this.TipoMed = TipoMed;
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

    public String getMedPBSUtilizado() {
        return MedPBSUtilizado;
    }

    public void setMedPBSUtilizado(String MedPBSUtilizado) {
        this.MedPBSUtilizado = MedPBSUtilizado;
    }

    public Integer getRznCausaS31() {
        return RznCausaS31;
    }

    public void setRznCausaS31(Integer RznCausaS31) {
        this.RznCausaS31 = RznCausaS31;
    }

    public String getDescRzn31() {
        return DescRzn31;
    }

    public void setDescRzn31(String DescRzn31) {
        this.DescRzn31 = DescRzn31;
    }

    public Integer getRznCausaS32() {
        return RznCausaS32;
    }

    public void setRznCausaS32(Integer RznCausaS32) {
        this.RznCausaS32 = RznCausaS32;
    }

    public String getDescRzn32() {
        return DescRzn32;
    }

    public void setDescRzn32(String DescRzn32) {
        this.DescRzn32 = DescRzn32;
    }

    public Integer getCausaS4() {
        return CausaS4;
    }

    public void setCausaS4(Integer CausaS4) {
        this.CausaS4 = CausaS4;
    }

    public String getMedPBSDescartado() {
        return MedPBSDescartado;
    }

    public void setMedPBSDescartado(String MedPBSDescartado) {
        this.MedPBSDescartado = MedPBSDescartado;
    }

    public Integer getRznCausaS41() {
        return RznCausaS41;
    }

    public void setRznCausaS41(Integer RznCausaS41) {
        this.RznCausaS41 = RznCausaS41;
    }

    public String getDescRzn41() {
        return DescRzn41;
    }

    public void setDescRzn41(String DescRzn41) {
        this.DescRzn41 = DescRzn41;
    }

    public Integer getRznCausaS42() {
        return RznCausaS42;
    }

    public void setRznCausaS42(Integer RznCausaS42) {
        this.RznCausaS42 = RznCausaS42;
    }

    public String getDescRzn42() {
        return DescRzn42;
    }

    public void setDescRzn42(String DescRzn42) {
        this.DescRzn42 = DescRzn42;
    }

    public Integer getRznCausaS43() {
        return RznCausaS43;
    }

    public void setRznCausaS43(Integer RznCausaS43) {
        this.RznCausaS43 = RznCausaS43;
    }

    public String getDescRzn43() {
        return DescRzn43;
    }

    public void setDescRzn43(String DescRzn43) {
        this.DescRzn43 = DescRzn43;
    }

    public Integer getRznCausaS44() {
        return RznCausaS44;
    }

    public void setRznCausaS44(Integer RznCausaS44) {
        this.RznCausaS44 = RznCausaS44;
    }

    public String getDescRzn44() {
        return DescRzn44;
    }

    public void setDescRzn44(String DescRzn44) {
        this.DescRzn44 = DescRzn44;
    }

    public Integer getCausaS5() {
        return CausaS5;
    }

    public void setCausaS5(Integer CausaS5) {
        this.CausaS5 = CausaS5;
    }

    public Integer getRznCausaS5() {
        return RznCausaS5;
    }

    public void setRznCausaS5(Integer RznCausaS5) {
        this.RznCausaS5 = RznCausaS5;
    }

    public Integer getCausaS6() {
        return CausaS6;
    }

    public void setCausaS6(Integer CausaS6) {
        this.CausaS6 = CausaS6;
    }

    public String getDescMedPrinAct() {
        return DescMedPrinAct;
    }

    public void setDescMedPrinAct(String DescMedPrinAct) {
        this.DescMedPrinAct = DescMedPrinAct;
    }

    public String getCodFF() {
        return CodFF;
    }

    public void setCodFF(String CodFF) {
        this.CodFF = CodFF;
    }

    public String getCodVA() {
        return CodVA;
    }

    public void setCodVA(String CodVA) {
        this.CodVA = CodVA;
    }

    public String getJustNoPBS() {
        return JustNoPBS;
    }

    public void setJustNoPBS(String JustNoPBS) {
        this.JustNoPBS = JustNoPBS;
    }

    public String getDosis() {
        return Dosis;
    }

    public void setDosis(String Dosis) {
        this.Dosis = Dosis;
    }

    public String getDosisUM() {
        return DosisUM;
    }

    public void setDosisUM(String DosisUM) {
        this.DosisUM = DosisUM;
    }

    public String getNoFAdmon() {
        return NoFAdmon;
    }

    public void setNoFAdmon(String NoFAdmon) {
        this.NoFAdmon = NoFAdmon;
    }

    public Integer getCodFreAdmon() {
        return CodFreAdmon;
    }

    public void setCodFreAdmon(Integer CodFreAdmon) {
        this.CodFreAdmon = CodFreAdmon;
    }

    public Integer getIndEsp() {
        return IndEsp;
    }

    public void setIndEsp(Integer IndEsp) {
        this.IndEsp = IndEsp;
    }

    public String getCanTrat() {
        return CanTrat;
    }

    public void setCanTrat(String CanTrat) {
        this.CanTrat = CanTrat;
    }

    public Integer getDurTrat() {
        return DurTrat;
    }

    public void setDurTrat(Integer DurTrat) {
        this.DurTrat = DurTrat;
    }

    public String getCantTotalF() {
        return CantTotalF;
    }

    public void setCantTotalF(String CantTotalF) {
        this.CantTotalF = CantTotalF;
    }

    public String getUFCantTotal() {
        return UFCantTotal;
    }

    public void setUFCantTotal(String UFCantTotal) {
        this.UFCantTotal = UFCantTotal;
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

    public String getDscMedPA() {
        return DscMedPA;
    }

    public void setDscMedPA(String DscMedPA) {
        this.DscMedPA = DscMedPA;
    }

    public List<PrincipioActivo> getPrincipiosActivos() {
        return PrincipiosActivos;
    }

    public void setPrincipiosActivos(List<PrincipioActivo> PrincipiosActivos) {
        this.PrincipiosActivos = PrincipiosActivos;
    }

    public List<IndicacionUNIRS> getIndicacionesUNIRS() {
        return IndicacionesUNIRS;
    }

    public void setIndicacionesUNIRS(List<IndicacionUNIRS> IndicacionesUNIRS) {
        this.IndicacionesUNIRS = IndicacionesUNIRS;
    }

}
