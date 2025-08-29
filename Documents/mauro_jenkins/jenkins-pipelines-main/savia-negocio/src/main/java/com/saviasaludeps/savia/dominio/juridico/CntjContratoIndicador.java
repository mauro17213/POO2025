package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author idbohorquez
 */
public class CntjContratoIndicador extends Auditoria{
    
    private Integer id;
    private Integer tipoIndicador;
    private String descripcion;
    private String meta;
    private Boolean aplicaDescuento;
    private BigDecimal porcentajeDescuento;
    private BigDecimal valorDescuento;    
    private CntjContrato cntjContratosId;
    
    //Auxiliares
    private int idTemporal;

    public CntjContratoIndicador() {
    }

    public CntjContratoIndicador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(Integer tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Boolean getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(Boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public CntjContrato getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContrato cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
    }
    
    //Auxiliares

    public int getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(int idTemporal) {
        this.idTemporal = idTemporal;
    }
    

    @Override
    public String toString() {
        return "CntjContratoIndicador{" + "id=" + id + ", tipoIndicador=" + tipoIndicador + ", descripcion=" + descripcion + ", meta=" + meta + ", aplicaDescuento=" + aplicaDescuento + ", porcentajeDescuento=" + porcentajeDescuento + ", valorDescuento=" + valorDescuento + ", cntjContratosId=" + cntjContratosId + '}';
    }
    
}
