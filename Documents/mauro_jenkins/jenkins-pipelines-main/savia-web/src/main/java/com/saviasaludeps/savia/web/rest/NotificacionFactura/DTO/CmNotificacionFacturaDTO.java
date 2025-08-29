package com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO;

import java.util.List;
import java.util.Objects;

public class CmNotificacionFacturaDTO {

    public static final String TIPO_TRANSACCION_1 = "1";
    public static final String TIPO_TRANSACCION_2 = "2";
    public static final String TIPO_TRANSACCION_3 = "3";
    public static final String TIPO_TRANSACCION_4 = "4";

    private String consecutivo = "";
    private String NITProveedor = "";
    private String tipoDocumento = "";
    private String numeroDocumento = "";
    private String contrato = "";
    private String periodoPago = "";
    private String regimenAfiliado = "";
    private String valorDocumento = "";
    private String numeroRadicado = "";
    private String numeroDocumentoOrigen = "";
    private String copago = "";
    private String cuotaModeradora = "";
    private String descuento = "";
    private String IVA = "";
    private String valorNotaDebito = "";
    private String fechaDocumento = "";
    private String fechaRadicacion = "";
    private String fechaProceso = "";
    private String documentoAnticipo = "";
    private String tipoTransaccion = "";
    private List<CmDetalleServicioDTO> detalleServicio;
    private List<CmDetalleServicioDTO> detalleNotaDebito;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNITProveedor() {
        return NITProveedor;
    }

    public void setNITProveedor(String NITProveedor) {
        this.NITProveedor = NITProveedor;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(String periodoPago) {
        this.periodoPago = periodoPago;
    }

    public String getRegimenAfiliado() {
        return regimenAfiliado;
    }

    public void setRegimenAfiliado(String regimenAfiliado) {
        this.regimenAfiliado = regimenAfiliado;
    }

    public String getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(String valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroDocumentoOrigen() {
        return numeroDocumentoOrigen;
    }

    public void setNumeroDocumentoOrigen(String numeroDocumentoOrigen) {
        this.numeroDocumentoOrigen = numeroDocumentoOrigen;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public String getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(String cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getIVA() {
        return IVA;
    }

    public void setIVA(String IVA) {
        this.IVA = IVA;
    }

    public String getValorNotaDebito() {
        return valorNotaDebito;
    }

    public void setValorNotaDebito(String valorNotaDebito) {
        this.valorNotaDebito = valorNotaDebito;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(String fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(String fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getDocumentoAnticipo() {
        return documentoAnticipo;
    }

    public void setDocumentoAnticipo(String documentoAnticipo) {
        this.documentoAnticipo = documentoAnticipo;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public List<CmDetalleServicioDTO> getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(List<CmDetalleServicioDTO> detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public List<CmDetalleServicioDTO> getDetalleNotaDebito() {
        return detalleNotaDebito;
    }

    public void setDetalleNotaDebito(List<CmDetalleServicioDTO> detalleNotaDebito) {
        this.detalleNotaDebito = detalleNotaDebito;
    }

    @Override
    public String toString() {
        return "CmNotificacionFacturaDTO{" + "consecutivo=" + consecutivo + ", NITProveedor=" + NITProveedor + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", contrato=" + contrato + ", valorDocumento=" + valorDocumento + ", copago=" + copago + ", cuotaModeradora=" + cuotaModeradora + ", descuento=" + descuento + ", IVA=" + IVA + ", valorNotaDebito=" + valorNotaDebito + ", fechaDocumento=" + fechaDocumento + ", fechaRadicacion=" + fechaRadicacion + ", fechaProceso=" + fechaProceso + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.consecutivo);
        hash = 73 * hash + Objects.hashCode(this.NITProveedor);
        hash = 73 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 73 * hash + Objects.hashCode(this.numeroDocumento);
        hash = 73 * hash + Objects.hashCode(this.valorDocumento);
        hash = 73 * hash + Objects.hashCode(this.numeroRadicado);
        hash = 73 * hash + Objects.hashCode(this.numeroDocumentoOrigen);
        hash = 73 * hash + Objects.hashCode(this.fechaDocumento);
        hash = 73 * hash + Objects.hashCode(this.fechaRadicacion);
        hash = 73 * hash + Objects.hashCode(this.fechaProceso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CmNotificacionFacturaDTO other = (CmNotificacionFacturaDTO) obj;
        if (!Objects.equals(this.consecutivo, other.consecutivo)) {
            return false;
        }
        if (!Objects.equals(this.NITProveedor, other.NITProveedor)) {
            return false;
        }
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        if (!Objects.equals(this.numeroDocumento, other.numeroDocumento)) {
            return false;
        }
        if (!Objects.equals(this.valorDocumento, other.valorDocumento)) {
            return false;
        }
        if (!Objects.equals(this.numeroRadicado, other.numeroRadicado)) {
            return false;
        }
        if (!Objects.equals(this.numeroDocumentoOrigen, other.numeroDocumentoOrigen)) {
            return false;
        }
        if (!Objects.equals(this.fechaDocumento, other.fechaDocumento)) {
            return false;
        }
        if (!Objects.equals(this.fechaRadicacion, other.fechaRadicacion)) {
            return false;
        }
        if (!Objects.equals(this.fechaProceso, other.fechaProceso)) {
            return false;
        }
        return true;
    }

}
