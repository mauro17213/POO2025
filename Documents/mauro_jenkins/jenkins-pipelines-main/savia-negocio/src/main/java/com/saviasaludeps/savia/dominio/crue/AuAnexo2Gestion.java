/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author AlexanderDiaz
 */
public class AuAnexo2Gestion extends Auditoria {
    
    public static final int ORIGEN_EPS = 0;
    public static final int ORIGEN_IPS = 1;

    private Integer id;
    private int origen;
    private int maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    private Integer maeMotivoId;
    private String maeMotivoCodigo;
    private String maeMotivoValor;
    private String observacion;
    private String contactoNombre;
    private String contactoTelefono;
    private CntPrestadorSede cntPrestadorSede;
    private AuAnexo2 auAnexo2;

    public AuAnexo2Gestion() {
    }

    public AuAnexo2Gestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuAnexo2 getAuAnexo2() {
        return auAnexo2;
    }

    public void setAuAnexo2(AuAnexo2 auAnexo2) {
        this.auAnexo2 = auAnexo2;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(int maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public Integer getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(Integer maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    @Override
    public String toString() {
        return "AuAnexo2Gestion{" + "id=" + id + ", origen=" + origen + ", maeTipoId=" + maeTipoId + ", maeTipoCodigo=" + maeTipoCodigo + ", maeTipoCodigo=" + maeTipoCodigo
                + ", maeTipoValor=" + maeTipoValor + ", maeMotivoId=" + maeMotivoId + ", maeMotivoCodigo=" + maeMotivoCodigo + ", maeMotivoValor=" + maeMotivoValor
                + ", observacion=" + observacion + ", contactoNombre=" + contactoNombre + ", auAnexo2=" + auAnexo2 + '}';
    }
}
