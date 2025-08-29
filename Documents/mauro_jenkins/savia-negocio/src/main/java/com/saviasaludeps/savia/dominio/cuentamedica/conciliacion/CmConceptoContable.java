/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CmConceptoContable extends Auditoria{
    
    private Integer id;
    private int idConcepto;
    private String nombreConcepto;
    private BigDecimal porcentaje;
    private String codigoMunicipio;
    private String municipioAfiliado;
    private CmDetalle cmDetalleId;

    public CmConceptoContable() {
    }

    public CmConceptoContable(Integer id, int idConcepto, String nombreConcepto, BigDecimal porcentaje, String codigoMunicipio, String municipioAfiliado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, CmDetalle cmDetalleId) {
        this.id = id;
        this.idConcepto = idConcepto;
        this.nombreConcepto = nombreConcepto;
        this.porcentaje = porcentaje;
        this.codigoMunicipio = codigoMunicipio;
        this.municipioAfiliado = municipioAfiliado;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.cmDetalleId = cmDetalleId;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getNombreConcepto() {
        return nombreConcepto;
    }

    public void setNombreConcepto(String nombreConcepto) {
        this.nombreConcepto = nombreConcepto;
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
    
    public CmDetalle getCmDetalleId() {
        return cmDetalleId;
    }

    public void setCmDetalleId(CmDetalle cmDetalleId) {
        this.cmDetalleId = cmDetalleId;
    }
    
    
}
