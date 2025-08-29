package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CmRipsCargaDetalleDTO implements Serializable {

    int id = 0;
    private String numeroFactura;
    private String codigoDocumento;
    private String documento;
    private String nombreAfiliado;
    private Date Fecha;
    private String autorizacion;
    private String codigoServicio;
    private String nombreServicio;
    private int tipoServicio;
    private int cantidad;
    private BigDecimal valor;
    private BigDecimal valorCuotaModeradora;
    private BigDecimal copago;
    private BigDecimal total;

    public static final int TIPO_SERVICIO_MEDICAMENTOS = 0;
    public static final int TIPO_SERVICIO_PROCEDIMIENTOS = 1;
    public static final int TIPO_SERVICIO_INSUMOS = 2;
    public static final int TIPO_SERVICIO_CONSULTAS = 3;

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreAfiliado() {
        return nombreAfiliado;
    }

    public void setNombreAfiliado(String nombreAfiliado) {
        this.nombreAfiliado = nombreAfiliado;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getCopago() {
        return copago;
    }

    public void setCopago(BigDecimal copago) {
        this.copago = copago;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoServicioStr() {
        String str = "";
        switch (this.tipoServicio) {
            case TIPO_SERVICIO_MEDICAMENTOS:
                str = "MEDICAMENTO";
                break;
            case TIPO_SERVICIO_CONSULTAS:
                str = "CONSULTA";
                break;
            case TIPO_SERVICIO_PROCEDIMIENTOS:
                str = "PROCEDIMIENTO";
                break;
            case TIPO_SERVICIO_INSUMOS:
                str = "INSUMO";
                break;
        }
        return str;
    }

}
