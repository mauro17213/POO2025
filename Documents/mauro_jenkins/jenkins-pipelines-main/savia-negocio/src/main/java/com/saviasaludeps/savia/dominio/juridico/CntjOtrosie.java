package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author StivenGV
 */
public class CntjOtrosie extends Auditoria {
    
    private Integer id;
    private Integer numeroOtrosi;
    private Integer tipoOtrosi;
    private Date fechaSuscripcionOtrosi;
    private Date fechaInicioOtrosi;
    private Date fechaTerminacionOtrosi;
    private Integer plazoProrrogaMeses;
    private Integer plazoProrrogaDias;
    private BigDecimal valorOtrosi;
    private String justificacion;
    private String elementosAdicionales;
    private Integer estadoOtrosi;
    private List<CntjOtrosiAdjunto> cntjOtrosiAdjuntoList;
    private CntjContrato cntjContratoId;

    public CntjOtrosie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroOtrosi() {
        return numeroOtrosi;
    }

    public void setNumeroOtrosi(Integer numeroOtrosi) {
        this.numeroOtrosi = numeroOtrosi;
    }

    public Integer getTipoOtrosi() {
        return tipoOtrosi;
    }

    public void setTipoOtrosi(Integer tipoOtrosi) {
        this.tipoOtrosi = tipoOtrosi;
    }

    public Date getFechaSuscripcionOtrosi() {
        return fechaSuscripcionOtrosi;
    }

    public void setFechaSuscripcionOtrosi(Date fechaSuscripcionOtrosi) {
        this.fechaSuscripcionOtrosi = fechaSuscripcionOtrosi;
    }

    public Date getFechaInicioOtrosi() {
        return fechaInicioOtrosi;
    }

    public void setFechaInicioOtrosi(Date fechaInicioOtrosi) {
        this.fechaInicioOtrosi = fechaInicioOtrosi;
    }

    public Date getFechaTerminacionOtrosi() {
        return fechaTerminacionOtrosi;
    }

    public void setFechaTerminacionOtrosi(Date fechaTerminacionOtrosi) {
        this.fechaTerminacionOtrosi = fechaTerminacionOtrosi;
    }

    public Integer getPlazoProrrogaMeses() {
        return plazoProrrogaMeses;
    }

    public void setPlazoProrrogaMeses(Integer plazoProrrogaMeses) {
        this.plazoProrrogaMeses = plazoProrrogaMeses;
    }

    public Integer getPlazoProrrogaDias() {
        return plazoProrrogaDias;
    }

    public void setPlazoProrrogaDias(Integer plazoProrrogaDias) {
        this.plazoProrrogaDias = plazoProrrogaDias;
    }

    public BigDecimal getValorOtrosi() {
        return valorOtrosi;
    }

    public void setValorOtrosi(BigDecimal valorOtrosi) {
        this.valorOtrosi = valorOtrosi;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getElementosAdicionales() {
        return elementosAdicionales;
    }

    public void setElementosAdicionales(String elementosAdicionales) {
        this.elementosAdicionales = elementosAdicionales;
    }

    public Integer getEstadoOtrosi() {
        return estadoOtrosi;
    }

    public void setEstadoOtrosi(Integer estadoOtrosi) {
        this.estadoOtrosi = estadoOtrosi;
    }

    public List<CntjOtrosiAdjunto> getCntjOtrosiAdjuntoList() {
        return cntjOtrosiAdjuntoList;
    }

    public void setCntjOtrosiAdjuntoList(List<CntjOtrosiAdjunto> cntjOtrosiAdjuntoList) {
        this.cntjOtrosiAdjuntoList = cntjOtrosiAdjuntoList;
    }

    public CntjContrato getCntjContratoId() {
        return cntjContratoId;
    }

    public void setCntjContratoId(CntjContrato cntjContratoId) {
        this.cntjContratoId = cntjContratoId;
    }

    @Override
    public String toString() {
        return "CntjOtrosie{" + "id=" + id + ", numeroOtrosi=" + numeroOtrosi + ", tipoOtrosi=" + tipoOtrosi + ", fechaSuscripcionOtrosi=" + fechaSuscripcionOtrosi + ", fechaInicioOtrosi=" + fechaInicioOtrosi + ", fechaTerminacionOtrosi=" + fechaTerminacionOtrosi + ", plazoProrrogaMeses=" + plazoProrrogaMeses + ", plazoProrrogaDias=" + plazoProrrogaDias + ", valorOtrosi=" + valorOtrosi + ", justificacion=" + justificacion + ", elementosAdicionales=" + elementosAdicionales + ", estadoOtrosi=" + estadoOtrosi + '}';
    }
    
}
