/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class MpEntregaFactura
        extends Auditoria {

    public static final Boolean FACTURA_CERRADA = true;

    private Integer id;
    private Short estado;
    private String noFactura;
    private String cufe;
    private Date fechaFacturacion;
    private String mpEntregaFacturascol;
    private String idFacturacion;
    private String idTransaccion;
    private String idCicloFacturacion;
    private String noidEPS;
    private String codEPS;
    private BigDecimal cantUnitaria;
    private BigDecimal valorUnitario;
    private BigDecimal copago;
    private BigDecimal cuotaModeradora;
    private BigDecimal valorTotal;
    private Short noSubEntrega;
    private Date fechaAnulacion;
    private Short confirmaTipoComparador;
    private Short confirmaCodigoComparador;
    private BigDecimal confirmaValorTotalComparador;
    private String confirmaUnidadAdminstrativo;
    private String confirmaUnidadHomologado;
    private BigDecimal confirmaValorMinimaConcentracion;
    private boolean confirmaAfectaPresupuesto;
    private Date confirmaFecha;
    private boolean confirmaCierreCiclo;
    private Date confirmaFhCierreCiclo;
    private Date confirmaFhCierreFacturaEps;
    private Date fechaConsumo;
    private Date fechaConsumoDato;
    private String codigoFacturado;
    private String nit;

    private MpDireccionamientoEntregado mpDireccionamientoEntregadoId;
    private MpEntregaSuministro mpEntregaSuministroId;

    public MpEntregaFactura() {
    }

    public MpEntregaFactura(Integer id) {
        this.id = id;
    }

    public MpEntregaFactura(Integer id, Short estado, String noFactura, String cufe, Date fechaFacturacion, String mpEntregaFacturascol, String idFacturacion, String idTransaccion, String idCicloFacturacion, String noidEPS, String codEPS, BigDecimal cantUnitaria, BigDecimal valorUnitario, BigDecimal copago, BigDecimal cuotaModeradora, BigDecimal valorTotal, Short noSubEntrega, Date fechaAnulacion, Short confirmaTipoComparador, Short confirmaCodigoComparador, BigDecimal confirmaValorTotalComparador, String confirmaUnidadAdminstrativo, String confirmaUnidadHomologado, BigDecimal confirmaValorMinimaConcentracion, boolean confirmaAfectaPresupuesto, Date confirmaFecha, boolean confirmaCierreCiclo, Date confirmaFhCierreCiclo, Date confirmaFhCierreFacturaEps, MpDireccionamientoEntregado mpDireccionamientoEntregadoId, MpEntregaSuministro mpEntregaSuministroId) {
        this.id = id;
        this.estado = estado;
        this.noFactura = noFactura;
        this.cufe = cufe;
        this.fechaFacturacion = fechaFacturacion;
        this.mpEntregaFacturascol = mpEntregaFacturascol;
        this.idFacturacion = idFacturacion;
        this.idTransaccion = idTransaccion;
        this.idCicloFacturacion = idCicloFacturacion;
        this.noidEPS = noidEPS;
        this.codEPS = codEPS;
        this.cantUnitaria = cantUnitaria;
        this.valorUnitario = valorUnitario;
        this.copago = copago;
        this.cuotaModeradora = cuotaModeradora;
        this.valorTotal = valorTotal;
        this.noSubEntrega = noSubEntrega;
        this.fechaAnulacion = fechaAnulacion;
        this.confirmaTipoComparador = confirmaTipoComparador;
        this.confirmaCodigoComparador = confirmaCodigoComparador;
        this.confirmaValorTotalComparador = confirmaValorTotalComparador;
        this.confirmaUnidadAdminstrativo = confirmaUnidadAdminstrativo;
        this.confirmaUnidadHomologado = confirmaUnidadHomologado;
        this.confirmaValorMinimaConcentracion = confirmaValorMinimaConcentracion;
        this.confirmaAfectaPresupuesto = confirmaAfectaPresupuesto;
        this.confirmaFecha = confirmaFecha;
        this.confirmaCierreCiclo = confirmaCierreCiclo;
        this.confirmaFhCierreCiclo = confirmaFhCierreCiclo;
        this.confirmaFhCierreFacturaEps = confirmaFhCierreFacturaEps;
        this.mpDireccionamientoEntregadoId = mpDireccionamientoEntregadoId;
        this.mpEntregaSuministroId = mpEntregaSuministroId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public String getMpEntregaFacturascol() {
        return mpEntregaFacturascol;
    }

    public void setMpEntregaFacturascol(String mpEntregaFacturascol) {
        this.mpEntregaFacturascol = mpEntregaFacturascol;
    }

    public String getIdFacturacion() {
        return idFacturacion;
    }

    public void setIdFacturacion(String idFacturacion) {
        this.idFacturacion = idFacturacion;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdCicloFacturacion() {
        return idCicloFacturacion;
    }

    public void setIdCicloFacturacion(String idCicloFacturacion) {
        this.idCicloFacturacion = idCicloFacturacion;
    }

    public String getNoidEPS() {
        return noidEPS;
    }

    public void setNoidEPS(String noidEPS) {
        this.noidEPS = noidEPS;
    }

    public String getCodEPS() {
        return codEPS;
    }

    public void setCodEPS(String codEPS) {
        this.codEPS = codEPS;
    }

    public BigDecimal getCantUnitaria() {
        return cantUnitaria;
    }

    public void setCantUnitaria(BigDecimal cantUnitaria) {
        this.cantUnitaria = cantUnitaria;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getCopago() {
        return copago;
    }

    public void setCopago(BigDecimal copago) {
        this.copago = copago;
    }

    public BigDecimal getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(BigDecimal cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCodigoFacturado() {
        return codigoFacturado;
    }

    public void setCodigoFacturado(String codigoFacturado) {
        this.codigoFacturado = codigoFacturado;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Short getNoSubEntrega() {
        return noSubEntrega;
    }

    public void setNoSubEntrega(Short noSubEntrega) {
        this.noSubEntrega = noSubEntrega;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Short getConfirmaTipoComparador() {
        return confirmaTipoComparador;
    }

    public void setConfirmaTipoComparador(Short confirmaTipoComparador) {
        this.confirmaTipoComparador = confirmaTipoComparador;
    }

    public Short getConfirmaCodigoComparador() {
        return confirmaCodigoComparador;
    }

    public void setConfirmaCodigoComparador(Short confirmaCodigoComparador) {
        this.confirmaCodigoComparador = confirmaCodigoComparador;
    }

    public BigDecimal getConfirmaValorTotalComparador() {
        return confirmaValorTotalComparador;
    }

    public void setConfirmaValorTotalComparador(BigDecimal confirmaValorTotalComparador) {
        this.confirmaValorTotalComparador = confirmaValorTotalComparador;
    }

    public String getConfirmaUnidadAdminstrativo() {
        return confirmaUnidadAdminstrativo;
    }

    public void setConfirmaUnidadAdminstrativo(String confirmaUnidadAdminstrativo) {
        this.confirmaUnidadAdminstrativo = confirmaUnidadAdminstrativo;
    }

    public String getConfirmaUnidadHomologado() {
        return confirmaUnidadHomologado;
    }

    public void setConfirmaUnidadHomologado(String confirmaUnidadHomologado) {
        this.confirmaUnidadHomologado = confirmaUnidadHomologado;
    }

    public BigDecimal getConfirmaValorMinimaConcentracion() {
        return confirmaValorMinimaConcentracion;
    }

    public void setConfirmaValorMinimaConcentracion(BigDecimal confirmaValorMinimaConcentracion) {
        this.confirmaValorMinimaConcentracion = confirmaValorMinimaConcentracion;
    }

    public boolean isConfirmaAfectaPresupuesto() {
        return confirmaAfectaPresupuesto;
    }

    public void setConfirmaAfectaPresupuesto(boolean confirmaAfectaPresupuesto) {
        this.confirmaAfectaPresupuesto = confirmaAfectaPresupuesto;
    }

    public Date getConfirmaFecha() {
        return confirmaFecha;
    }

    public void setConfirmaFecha(Date confirmaFecha) {
        this.confirmaFecha = confirmaFecha;
    }

    public boolean isConfirmaCierreCiclo() {
        return confirmaCierreCiclo;
    }

    public void setConfirmaCierreCiclo(boolean confirmaCierreCiclo) {
        this.confirmaCierreCiclo = confirmaCierreCiclo;
    }

    public Date getConfirmaFhCierreCiclo() {
        return confirmaFhCierreCiclo;
    }

    public void setConfirmaFhCierreCiclo(Date confirmaFhCierreCiclo) {
        this.confirmaFhCierreCiclo = confirmaFhCierreCiclo;
    }

    public Date getConfirmaFhCierreFacturaEps() {
        return confirmaFhCierreFacturaEps;
    }

    public void setConfirmaFhCierreFacturaEps(Date confirmaFhCierreFacturaEps) {
        this.confirmaFhCierreFacturaEps = confirmaFhCierreFacturaEps;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public Date getFechaConsumoDato() {
        return fechaConsumoDato;
    }

    public void setFechaConsumoDato(Date fechaConsumoDato) {
        this.fechaConsumoDato = fechaConsumoDato;
    }

    public MpDireccionamientoEntregado getMpDireccionamientoEntregadoId() {
        return mpDireccionamientoEntregadoId;
    }

    public void setMpDireccionamientoEntregadoId(MpDireccionamientoEntregado mpDireccionamientoEntregadoId) {
        this.mpDireccionamientoEntregadoId = mpDireccionamientoEntregadoId;
    }

    public MpEntregaSuministro getMpEntregaSuministroId() {
        return mpEntregaSuministroId;
    }

    public void setMpEntregaSuministroId(MpEntregaSuministro mpEntregaSuministroId) {
        this.mpEntregaSuministroId = mpEntregaSuministroId;
    }

    @Override
    public String toString() {
        return "MpEntregaFactura{" + "id=" + id + ", estado=" + estado + ", noFactura=" + noFactura + ", cufe=" + cufe + ", fechaFacturacion=" + fechaFacturacion + ", mpEntregaFacturascol=" + mpEntregaFacturascol + ", idFacturacion=" + idFacturacion + ", idTransaccion=" + idTransaccion + ", idCicloFacturacion=" + idCicloFacturacion + ", noidEPS=" + noidEPS + ", codEPS=" + codEPS + ", cantUnitaria=" + cantUnitaria + ", valorUnitario=" + valorUnitario + ", copago=" + copago + ", cuotaModeradora=" + cuotaModeradora + ", valorTotal=" + valorTotal + ", noSubEntrega=" + noSubEntrega + ", fechaAnulacion=" + fechaAnulacion + ", confirmaTipoComparador=" + confirmaTipoComparador + ", confirmaCodigoComparador=" + confirmaCodigoComparador + ", confirmaValorTotalComparador=" + confirmaValorTotalComparador + ", confirmaUnidadAdminstrativo=" + confirmaUnidadAdminstrativo + ", confirmaUnidadHomologado=" + confirmaUnidadHomologado + ", confirmaValorMinimaConcentracion=" + confirmaValorMinimaConcentracion + ", confirmaAfectaPresupuesto=" + confirmaAfectaPresupuesto + ", confirmaFecha=" + confirmaFecha + ", confirmaCierreCiclo=" + confirmaCierreCiclo + ", confirmaFhCierreCiclo=" + confirmaFhCierreCiclo + ", confirmaFhCierreFacturaEps=" + confirmaFhCierreFacturaEps + ", mpDireccionamientoEntregadoId=" + mpDireccionamientoEntregadoId + '}';
    }

}
