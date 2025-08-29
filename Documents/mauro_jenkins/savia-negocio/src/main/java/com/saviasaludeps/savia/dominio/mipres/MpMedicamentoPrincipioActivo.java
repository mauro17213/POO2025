package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author stivenGomez
 */
public class MpMedicamentoPrincipioActivo extends Auditoria {

    private Integer id;
    private int consecutivoOrden;
    private String codigoPrincipioActivo;
    private BigDecimal concecutivoCantidad;
    private String unidadMedidaConcentracion;
    private BigDecimal cantidadContenido;
    private String unidadCantidadContenido;
    //falta id medicamento;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentoId;

    public MpMedicamentoPrincipioActivo() {
    }

    public MpMedicamentoPrincipioActivo(Integer id) {
        this.id = id;
    }

    public MpMedicamentoPrincipioActivo(Integer id, int consecutivoOrden, String codigoPrincipioActivo, BigDecimal concecutivoCantidad, String unidadMedidaConcentracion, BigDecimal cantidadContenido, String unidadCantidadContenido) {
        this.id = id;
        this.consecutivoOrden = consecutivoOrden;
        this.codigoPrincipioActivo = codigoPrincipioActivo;
        this.concecutivoCantidad = concecutivoCantidad;
        this.unidadMedidaConcentracion = unidadMedidaConcentracion;
        this.cantidadContenido = cantidadContenido;
        this.unidadCantidadContenido = unidadCantidadContenido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public String getCodigoPrincipioActivo() {
        return codigoPrincipioActivo;
    }

    public void setCodigoPrincipioActivo(String codigoPrincipioActivo) {
        this.codigoPrincipioActivo = codigoPrincipioActivo;
    }

    public BigDecimal getConcecutivoCantidad() {
        return concecutivoCantidad;
    }

    public void setConcecutivoCantidad(BigDecimal concecutivoCantidad) {
        this.concecutivoCantidad = concecutivoCantidad;
    }

    public String getUnidadMedidaConcentracion() {
        return unidadMedidaConcentracion;
    }

    public void setUnidadMedidaConcentracion(String unidadMedidaConcentracion) {
        this.unidadMedidaConcentracion = unidadMedidaConcentracion;
    }

    public BigDecimal getCantidadContenido() {
        return cantidadContenido;
    }

    public void setCantidadContenido(BigDecimal cantidadContenido) {
        this.cantidadContenido = cantidadContenido;
    }

      
    public String getUnidadCantidadContenido() {
        return unidadCantidadContenido;
    }

    public void setUnidadCantidadContenido(String unidadCantidadContenido) {
        this.unidadCantidadContenido = unidadCantidadContenido;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentoId() {
        return mpPrescripcionMedicamentoId;
    }

    public void setMpPrescripcionMedicamentoId(MpPrescripcionMedicamento mpPrescripcionMedicamentoId) {
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    @Override
    public String toString() {
        return "MpMedicamentoPrincipioActivo{" + "id=" + id + ", consecutivoOrden=" + consecutivoOrden + ", codigoPrincipioActivo=" + codigoPrincipioActivo + ", concecutivoCantidad=" + concecutivoCantidad + ", unidadMedidaConcentracion=" + unidadMedidaConcentracion + ", cantidadContenido=" + cantidadContenido + ", unidadCantidadContenido=" + unidadCantidadContenido + '}';
    }

}
