package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjContratoHistorico extends Auditoria{
    
    private Integer id;
    private boolean tipo;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal valor;    
    private CntjContrato cntjContratosId;

    public CntjContratoHistorico() {
    }

    public CntjContratoHistorico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CntjContrato getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContrato cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
    }

    @Override
    public String toString() {
        return "CntjContratoHistorico{" + "id=" + id + ", tipo=" + tipo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", valor=" + valor + ", cntjContratosId=" + cntjContratosId + '}';
    }
    
}
