
package com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;;


public class WsCmFactura extends Auditoria {
    
    public static final short ESTADO_EN_CREACION = 1;
    public static final short ESTADO_CREADA = 2;
    public static final short ESTADO_EXITOSO = 3;
    public static final short ESTADO_REINTENTO = 4;
    public static final short ESTADO_RECONSTRUCCION  = 5;
    public static final short ESTADO_SIN_VALORES_EPS = 6;

    private Integer id;
    private short estado;
    private String proveedorNit;
    private String numeroDocumento;
    private String numeroRadicado;
    private Integer facturaId;
    private String regimen;
    private BigDecimal valorDocumento;
    private BigDecimal valorPagado;
    private BigDecimal valorGlosado;
    private BigDecimal valorCopago;
    private Date fechaHoraDocumento;
    private Date fechaHoraProceso;
    private BigDecimal cuotaModeradora;
    private String contrato;
    private String usuarioCrea;
    private String terminalCrea;
    private Date fechaHoraCrea;
    private CmFactura cmFactura;
    private CmRadicado cmRadicado;
    private List<WsCmFacturaDetalle> listWsCmFacturaDetalles;
    private List<CmGlosaRespuestaDetalle> listCmGlosaRespuestaDetalle;

    public WsCmFactura() {
        cmFactura = new CmFactura();
        cmRadicado = new CmRadicado();
        listWsCmFacturaDetalles = new ArrayList<>();
        listCmGlosaRespuestaDetalle = new ArrayList<>();
    }

    public WsCmFactura(Integer id) {
        this.id = id;
        cmFactura = new CmFactura();
        cmRadicado = new CmRadicado();
        listWsCmFacturaDetalles = new ArrayList<>();
        listCmGlosaRespuestaDetalle = new ArrayList<>();
    }

    public WsCmFactura(Integer id, short estado, Date fechaHoraDocumento, Date fechaHoraProceso, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraDocumento = fechaHoraDocumento;
        this.fechaHoraProceso = fechaHoraProceso;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        cmFactura = new CmFactura();
        cmRadicado = new CmRadicado();
        listWsCmFacturaDetalles = new ArrayList<>();
        listCmGlosaRespuestaDetalle = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getProveedorNit() {
        return proveedorNit;
    }

    public void setProveedorNit(String proveedorNit) {
        this.proveedorNit = proveedorNit;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public BigDecimal getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public Date getFechaHoraDocumento() {
        return fechaHoraDocumento;
    }

    public void setFechaHoraDocumento(Date fechaHoraDocumento) {
        this.fechaHoraDocumento = fechaHoraDocumento;
    }

    public Date getFechaHoraProceso() {
        return fechaHoraProceso;
    }

    public void setFechaHoraProceso(Date fechaHoraProceso) {
        this.fechaHoraProceso = fechaHoraProceso;
    }

    public BigDecimal getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(BigDecimal cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }


    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public CmRadicado getCmRadicado() {
        return cmRadicado;
    }

    public void setCmRadicado(CmRadicado cmRadicado) {
        this.cmRadicado = cmRadicado;
    }

    public List<WsCmFacturaDetalle> getListWsCmFacturaDetalles() {
        return listWsCmFacturaDetalles;
    }

    public void setListWsCmFacturaDetalles(List<WsCmFacturaDetalle> listWsCmFacturaDetalles) {
        this.listWsCmFacturaDetalles = listWsCmFacturaDetalles;
    }

    public List<CmGlosaRespuestaDetalle> getListCmGlosaRespuestaDetalle() {
        return listCmGlosaRespuestaDetalle;
    }

    public void setListCmGlosaRespuestaDetalle(List<CmGlosaRespuestaDetalle> listCmGlosaRespuestaDetalle) {
        this.listCmGlosaRespuestaDetalle = listCmGlosaRespuestaDetalle;
    }
    
    public String getEstadoStr() {
        return WsCmFactura.getEstadoStr(getEstado());
    }
    
    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_EN_CREACION:
                str = "En Creación";
                break;
            case ESTADO_CREADA:
                str = "Creada";
                break;
            case ESTADO_EXITOSO:
                str = "Exitoso";
                break;
             case ESTADO_REINTENTO:
                str = "Reintento";
                break;
            case ESTADO_RECONSTRUCCION:
                str = "Reconstrucción";
                break;  
             case ESTADO_SIN_VALORES_EPS:
                str = "Sin Valores Eps";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    public boolean existeWsCmFactura() {
        return this.getId() != null && this.getId() > 0;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof WsCmFactura)) {
            return false;
        }
        WsCmFactura other = (WsCmFactura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WsCmFactura{" + "id=" + id + ", estado=" + estado + ", proveedorNit=" + proveedorNit + ", numeroDocumento=" + numeroDocumento + ", numeroRadicado=" + numeroRadicado + ", facturaId=" + facturaId + ", regimen=" + regimen + ", valorDocumento=" + valorDocumento + ", valorPagado=" + valorPagado + ", valorGlosado=" + valorGlosado + ", valorCopago=" + valorCopago + ", fechaHoraDocumento=" + fechaHoraDocumento + ", fechaHoraProceso=" + fechaHoraProceso + ", cuotaModeradora=" + cuotaModeradora + ", contrato=" + contrato + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + '}';
    }

    
}
