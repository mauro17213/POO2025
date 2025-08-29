
package com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;


public class WsCmFacturaDetalle extends Auditoria {


    public static final short TIPOLOGIA_CREDITO = 1;
    public static final short TIPOLOGIA_DEBITO  = 2;
    
    private Integer id;
    private Integer idDetalles;
    private short tipologia;
    private String consecutivo;
    private String codigoMunicipio;
    private String conceptoContable;
    private BigDecimal valorOperacion;
    private WsCmFactura wsCmFactura;

    public WsCmFacturaDetalle() {
        wsCmFactura = new WsCmFactura();
    }

    public WsCmFacturaDetalle(Integer id) {
        this.id = id;
        wsCmFactura = new WsCmFactura();
    }

    public WsCmFacturaDetalle(Integer id, short tipologia, String consecutivo, String codigoMunicipio, String conceptoContable, BigDecimal valorOperacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipologia = tipologia;
        this.consecutivo = consecutivo;
        this.codigoMunicipio = codigoMunicipio;
        this.conceptoContable = conceptoContable;
        this.valorOperacion = valorOperacion;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        wsCmFactura = new WsCmFactura();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(Integer idDetalles) {
        this.idDetalles = idDetalles;
    }

    public short getTipologia() {
        return tipologia;
    }

    public void setTipologia(short tipologia) {
        this.tipologia = tipologia;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public BigDecimal getValorOperacion() {
        return valorOperacion;
    }

    public void setValorOperacion(BigDecimal valorOperacion) {
        this.valorOperacion = valorOperacion;
    }

    public WsCmFactura getWsCmFactura() {
        return wsCmFactura;
    }

    public void setWsCmFactura(WsCmFactura wsCmFactura) {
        this.wsCmFactura = wsCmFactura;
    }
    
    public String getTipologiaStr() {
        return WsCmFacturaDetalle.getTipologiaStr(getTipologia());
    }
    
    public static String getTipologiaStr(int tipologia) {
        String str;
        switch (tipologia) {
            case TIPOLOGIA_CREDITO:
                str = "Crédito";
                break;
            case TIPOLOGIA_DEBITO:
                str = "Débito";
                break;
            
            default:
                str = "";
                break;
        }
        return str;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof WsCmFacturaDetalle)) {
            return false;
        }
        WsCmFacturaDetalle other = (WsCmFacturaDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WsCmFacturaDetalle{" + "id=" + id + ", idDetalles=" + idDetalles + ", tipologia=" + tipologia + ", consecutivo=" + consecutivo + ", codigoMunicipio=" + codigoMunicipio + ", conceptoContable=" + conceptoContable + ", valorOperacion=" + valorOperacion + '}';
    }
    
}
