/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;


public class CmAuditoriaDiagnostico extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int idCmAuditoriaDetalle;
    private CmDetalle cmDetalle;
    private boolean principal;
    private int maDiagniosticosId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;
    private String maDiagnosticoCaretoriaNombre;
    private Integer posInsertar;
    
    public CmAuditoriaDiagnostico() {
    }

    public CmAuditoriaDiagnostico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCmAuditoriaDetalle() {
        return idCmAuditoriaDetalle;
    }

    public void setIdCmAuditoriaDetalle(int idCmAuditoriaDetalle) {
        this.idCmAuditoriaDetalle = idCmAuditoriaDetalle;
    }

    public CmDetalle getCmDetalle() {
        if(cmDetalle == null){
            cmDetalle = new CmDetalle();
        }
        return cmDetalle;
    }

    public void setCmDetalle(CmDetalle cmDetalle) {
        this.cmDetalle = cmDetalle;
    }


    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public int getMaDiagniosticosId() {
        return maDiagniosticosId;
    }

    public void setMaDiagniosticosId(int maDiagniosticosId) {
        this.maDiagniosticosId = maDiagniosticosId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    
    public String getPrincipalStr(){
        return isPrincipal() ? "Si" : "No";
    }

   

    public Integer getPosInsertar() {
        return posInsertar;
    }

    public void setPosInsertar(Integer posInsertar) {
        this.posInsertar = posInsertar;
    }

    public String getMaDiagnosticoCaretoriaNombre() {
        return maDiagnosticoCaretoriaNombre;
    }

    public void setMaDiagnosticoCaretoriaNombre(String maDiagnosticoCaretoriaNombre) {
        this.maDiagnosticoCaretoriaNombre = maDiagnosticoCaretoriaNombre;
    }

    @Override
    public String toString() {
        return "CmAuditoriaDiagnostico{" + "id=" + id + ", detalleId =" + getCmDetalle().getId() + ", principal=" + principal + ", maDiagniosticosId=" + maDiagniosticosId + ", maDiagnosticoCodigo=" + maDiagnosticoCodigo + ", maDiagnosticoValor=" + maDiagnosticoValor + '}';
    }
    
}
