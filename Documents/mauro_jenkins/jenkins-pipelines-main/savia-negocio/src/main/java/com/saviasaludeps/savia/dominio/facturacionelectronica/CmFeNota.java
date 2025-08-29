package com.saviasaludeps.savia.dominio.facturacionelectronica;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author LFRIVERA
 */
public class CmFeNota extends Auditoria implements Serializable {
    
    public static final int TIPO_NOTA_CREDITO    = 0;
    public static final int TIPO_NOTA_DEBIDO     = 1;
    public static final int TIPO_AJUSTE = 2;
    

    private Integer id;
    private int tipo;
    private String numeroNota;
    private BigDecimal valorNota;
    private String cude;
    private Date fechaHoraEmision;
    private Integer cmFacturasId;
    private Integer cmFeRipsFacturasId;

    public CmFeNota() {
    }

    public CmFeNota(int tipo) {
        this.tipo = tipo;
    }

    public CmFeNota(Integer id, int tipo, String numeroNota, BigDecimal valorNota, String cude,
            Date fechaHoraEmision, String usuarioCrea, String terminalCrea,
            Date fechaHoraCrea, Integer cmFacturasId, Integer cmFeRipsFacturasId) {
        this.id = id;
        this.tipo = tipo;
        this.numeroNota = numeroNota;
        this.valorNota = valorNota;
        this.cude = cude;
        this.fechaHoraEmision = fechaHoraEmision;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.cmFacturasId = cmFacturasId;
        this.cmFeRipsFacturasId = cmFeRipsFacturasId;
    }
    
    public CmFeNota(Integer id, int tipo, String numeroNota, BigDecimal valorNota, String cude,
            Date fechaHoraEmision, String usuarioCrea,  String terminalCrea, Date fechaHoraCrea ) {
        this.id = id;
        this.tipo = tipo;
        this.numeroNota = numeroNota;
        this.valorNota = valorNota;
        this.cude = cude;
        this.fechaHoraEmision = fechaHoraEmision;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }


    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public BigDecimal getValorNota() {
        return valorNota;
    }

    public void setValorNota(BigDecimal valorNota) {
        this.valorNota = valorNota;
    }

    public String getCude() {
        return cude;
    }

    public void setCude(String cude) {
        this.cude = cude;
    }

    public Date getFechaHoraEmision() {
        return fechaHoraEmision;
    }

    public void setFechaHoraEmision(Date fechaHoraEmision) {
        this.fechaHoraEmision = fechaHoraEmision;
    }

    public Integer getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(Integer cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public Integer getCmFeRipsFacturasId() {
        return cmFeRipsFacturasId;
    }

    public void setCmFeRipsFacturasId(Integer cmFeRipsFacturasId) {
        this.cmFeRipsFacturasId = cmFeRipsFacturasId;
    }
    
    public String getTipoStr() {
        return CmFeNota.getTipoStr(getTipo());
    }
    
    public static String getTipoStr(int tipo) {
        switch (tipo) {
            case TIPO_AJUSTE:
                return "Ajuste";
            case TIPO_NOTA_CREDITO:
                return "Nota Credito";
            case TIPO_NOTA_DEBIDO:
                return "Nota Debito";
            default:
                return "";
        }
    }
}
