package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjOtrosi extends Auditoria {
        
    private Integer id;
    private CntjContrato contratoId;
    private Integer numero;
    private Integer tipo;
    private Date fechasuscripcion;
    private Date fechaInicio;
    private Date fechaTerminacion;
    private Integer plazoMeses;
    private Integer plazoDias;
    private BigDecimal valor;
    private String justificacion;
    private String elementoAdicional;
    private Integer estado;
    private Integer estadoLegalizacion;

    public CntjOtrosi() {
    }

    public CntjOtrosi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntjContrato getContratoId() {
        return contratoId;
    }

    public void setContratoId(CntjContrato contratoId) {
        this.contratoId = contratoId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Date getFechasuscripcion() {
        return fechasuscripcion;
    }

    public void setFechasuscripcion(Date fechasuscripcion) {
        this.fechasuscripcion = fechasuscripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public Integer getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(Integer plazoDias) {
        this.plazoDias = plazoDias;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getElementoAdicional() {
        return elementoAdicional;
    }

    public void setElementoAdicional(String elementoAdicional) {
        this.elementoAdicional = elementoAdicional;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getEstadoLegalizacion() {
        return estadoLegalizacion;
    }

    public void setEstadoLegalizacion(Integer estadoLegalizacion) {
        this.estadoLegalizacion = estadoLegalizacion;
    }

    @Override
    public String toString() {
        return "CntjOtrosi{" + "id=" + id + ", contratoId=" + contratoId + ", numero=" + numero + ", tipo=" + tipo + ", fechasuscripcion=" + fechasuscripcion + ", fechaInicio=" + fechaInicio + ", fechaTerminacion=" + fechaTerminacion + ", plazoMeses=" + plazoMeses + ", plazoDias=" + plazoDias + ", valor=" + valor + ", justificacion=" + justificacion + ", elementoAdicional=" + elementoAdicional + ", estado=" + estado + ", estadoLegalizacion=" + estadoLegalizacion + '}';
    }

}
