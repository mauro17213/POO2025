package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

public class MpEntregaSuministro extends Auditoria {

    private Integer id;
    private String idSuministro;
    private Integer numeroEntrega;
    private Boolean ultimaEntrega;
    private Boolean entregaCompleta;
    private Boolean anulado;
    private String causaNoEntrega;
    private int estado;
    private String respuestaSuministro;
    private int estadoMipres;
    private Date fechaSuministro;
    private Date fechaConsumo;
    private Date fechaAnulacion;
    private String numeroPrescripcionAsociada;
    private Integer consecutivo;
    private Integer cantidadEntrega;
    private String numeroLote;
    private BigDecimal valorEntregado;
    private MpDireccionamientoEntregado mpDireccionamientoEntregadoId;

    public MpEntregaSuministro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(String idSuministro) {
        this.idSuministro = idSuministro;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public Boolean getUltimaEntrega() {
        return ultimaEntrega;
    }

    public void setUltimaEntrega(Boolean ultimaEntrega) {
        this.ultimaEntrega = ultimaEntrega;
    }

    public Boolean getEntregaCompleta() {
        return entregaCompleta;
    }

    public void setEntregaCompleta(Boolean entregaCompleta) {
        this.entregaCompleta = entregaCompleta;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public String getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(String causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getRespuestaSuministro() {
        return respuestaSuministro;
    }

    public void setRespuestaSuministro(String respuestaSuministro) {
        this.respuestaSuministro = respuestaSuministro;
    }

    public Date getFechaSuministro() {
        return fechaSuministro;
    }

    public void setFechaSuministro(Date fechaSuministro) {
        this.fechaSuministro = fechaSuministro;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getNumeroPrescripcionAsociada() {
        return numeroPrescripcionAsociada;
    }

    public void setNumeroPrescripcionAsociada(String numeroPrescripcionAsociada) {
        this.numeroPrescripcionAsociada = numeroPrescripcionAsociada;
    }

    public int getEstadoMipres() {
        return estadoMipres;
    }

    public void setEstadoMipres(int estadoMipres) {
        this.estadoMipres = estadoMipres;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getCantidadEntrega() {
        return cantidadEntrega;
    }

    public void setCantidadEntrega(Integer cantidadEntrega) {
        this.cantidadEntrega = cantidadEntrega;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public BigDecimal getValorEntregado() {
        return valorEntregado;
    }

    public void setValorEntregado(BigDecimal valorEntregado) {
        this.valorEntregado = valorEntregado;
    }

    public MpDireccionamientoEntregado getMpDireccionamientoEntregadoId() {
        return mpDireccionamientoEntregadoId;
    }

    public void setMpDireccionamientoEntregadoId(MpDireccionamientoEntregado mpDireccionamientoEntregadoId) {
        this.mpDireccionamientoEntregadoId = mpDireccionamientoEntregadoId;
    }

}
