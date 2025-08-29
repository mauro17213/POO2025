/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;



public class CmAuditoriaConceptoContable extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int maeConceptosId;
    private String maeConceptosCodigo;
    private String maeConceptosValor;
    private int maeCentroCostoId;
    private String maeCentroCostoCodigo;
    private String maeCentroCostoValor;
    private BigDecimal porcentaje;
    private String codigoMunicipio;
    private String municipioAfiliado;
    private CmDetalle cmDetalle;
    private Integer posInsertar;
    
    private Maestro maestroConceptos;
    private Maestro maestroCentroCosto;
    
    private Ubicacion ubicacionMunicipio = null;
    private boolean mostrarCentroCosto;
     
    
    public CmAuditoriaConceptoContable() {
    }

    public CmAuditoriaConceptoContable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getMunicipioAfiliado() {
        return municipioAfiliado;
    }

    public void setMunicipioAfiliado(String municipioAfiliado) {
        this.municipioAfiliado = municipioAfiliado;
    }

    public Integer getPosInsertar() {
        return posInsertar;
    }

    public void setPosInsertar(Integer posInsertar) {
        this.posInsertar = posInsertar;
    }

    public int getMaeConceptosId() {
        return maeConceptosId;
    }

    public void setMaeConceptosId(int maeConceptosId) {
        this.maeConceptosId = maeConceptosId;
    }

    public String getMaeConceptosCodigo() {
        return maeConceptosCodigo;
    }

    public void setMaeConceptosCodigo(String maeConceptosCodigo) {
        this.maeConceptosCodigo = maeConceptosCodigo;
    }

    public String getMaeConceptosValor() {
        return maeConceptosValor;
    }

    public void setMaeConceptosValor(String maeConceptosValor) {
        this.maeConceptosValor = maeConceptosValor;
    }

    public int getMaeCentroCostoId() {
        return maeCentroCostoId;
    }

    public void setMaeCentroCostoId(int maeCentroCostoId) {
        this.maeCentroCostoId = maeCentroCostoId;
    }

    public String getMaeCentroCostoCodigo() {
        return maeCentroCostoCodigo;
    }

    public void setMaeCentroCostoCodigo(String maeCentroCostoCodigo) {
        this.maeCentroCostoCodigo = maeCentroCostoCodigo;
    }

    public String getMaeCentroCostoValor() {
        return maeCentroCostoValor;
    }

    public void setMaeCentroCostoValor(String maeCentroCostoValor) {
        this.maeCentroCostoValor = maeCentroCostoValor;
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

    public Maestro getMaestroConceptos() {
        if(maestroConceptos == null){
            maestroConceptos = new Maestro();
        }
        return maestroConceptos;
    }

    public void setMaestroConceptos(Maestro maestroConceptos) {
        this.maestroConceptos = maestroConceptos;
    }

    public Maestro getMaestroCentroCosto() {
        if(maestroCentroCosto == null){
            maestroCentroCosto = new Maestro();
        }
        return maestroCentroCosto;
    }

     public void setMaestroCentroCosto(Maestro maestroCentroCosto) {
        this.maestroCentroCosto = maestroCentroCosto;
    }
    
    public void castCentroCostoMaestro(Maestro maestroCentroCosto) {
        if (maestroCentroCosto.getId() != null) {
            this.maeCentroCostoId = maestroCentroCosto.getId();
            this.maeCentroCostoValor = maestroCentroCosto.getNombre();
            this.maeCentroCostoCodigo = maestroCentroCosto.getValor();
        }
    }
    
    public void castConceptoContableMaestro(Maestro maestroConceptoContable) {
        if (maestroConceptoContable.getId() != null) {
            this.maeConceptosId = maestroConceptoContable.getId();
            this.maeConceptosCodigo = maestroConceptoContable.getValor();
            this.maeConceptosValor = maestroConceptoContable.getNombre();
        }
    }

    public Ubicacion getUbicacionMunicipio() {
        return ubicacionMunicipio;
    }

    public void setUbicacionMunicipio(Ubicacion ubicacionMunicipio) {
        this.ubicacionMunicipio = ubicacionMunicipio;
    }   

    public boolean isMostrarCentroCosto() {
        return mostrarCentroCosto;
    }

    public void setMostrarCentroCosto(boolean mostrarCentroCosto) {
        this.mostrarCentroCosto = mostrarCentroCosto;
    }


    @Override
    public String toString() {
        return "CmAuditoriaConceptoContable{" + "id=" + id + ", maeConceptosId=" + maeConceptosId + ", maeConceptosCodigo=" + maeConceptosCodigo + ", maeConceptosValor=" + maeConceptosValor + ", maeCentroCostoId=" + maeCentroCostoId + ", maeCentroCostoCodigo=" + maeCentroCostoCodigo + ", maeCentroCostoValor=" + maeCentroCostoValor + ", porcentaje=" + porcentaje + ", codigoMunicipio=" + codigoMunicipio + ", municipioAfiliado=" + municipioAfiliado + ", id detalle=" + getCmDetalle().getId() + '}';
    }


    
}
