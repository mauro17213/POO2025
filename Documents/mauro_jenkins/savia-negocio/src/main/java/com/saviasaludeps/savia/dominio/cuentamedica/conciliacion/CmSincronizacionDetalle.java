/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CmSincronizacionDetalle extends Auditoria {
    
    private Integer id;
    private String consecutivo;
    private String codigoMunicipio;
    private String conceptoContable;
    private BigDecimal valorOperacion;
    private CmSincronizacionEncabezado cmSincronizacionEncabezadosId;
    private int tipologia;
    private Integer idDetalles;
    public final static int TIPOLOGIA_CREDITO  = 1;
    public final static int TIPOLOGIA_DEBITO   = 2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public BigDecimal getValorOperacion() {
        return valorOperacion;
    }

    public void setValorOperacion(BigDecimal valorOperacion) {
        this.valorOperacion = valorOperacion;
    }

    public CmSincronizacionEncabezado getCmSincronizacionEncabezadosId() {
        return cmSincronizacionEncabezadosId;
    }

    public void setCmSincronizacionEncabezadosId(CmSincronizacionEncabezado cmSincronizacionEncabezadosId) {
        this.cmSincronizacionEncabezadosId = cmSincronizacionEncabezadosId;
    }
    
    public  String getTipologiaStr(){
        return CmSincronizacionDetalle.getTipologiaStr(getTipologia());
    }

    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int tipologia) {
        this.tipologia = tipologia;
    }

    public Integer getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(Integer idDetalles) {
        this.idDetalles = idDetalles;
    }
    
    
    
    public static String getTipologiaStr(int tipologia){
        switch (tipologia) {
            case TIPOLOGIA_CREDITO:
                return "Crédito";
            case TIPOLOGIA_DEBITO:
                return "Débito";
            default:
                return "";
        }
    }
    
}
