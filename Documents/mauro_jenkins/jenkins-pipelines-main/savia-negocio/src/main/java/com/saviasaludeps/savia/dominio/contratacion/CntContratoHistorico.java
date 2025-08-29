/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jyperez
 */
public class CntContratoHistorico extends Auditoria {
    
    private Integer id;
    private Integer origen;
    private Integer tipo;
    private String toString;
    private CntContrato cntContrato;
    private CntContratoSede cntContratoSede;
    private CntContratoDetalle cntContratoDetalle;
    
    public final static int TIPO_CONTRATO = 0;
    public final static int TIPO_DETALLE = 1;
    public final static int ORIGEN_MANUAL = 0;
    public final static int ORIGEN_CARGA_MASIVA = 1;
    public final static String FECHA_HORA_FIN = "fechaHoraFin";
    
    
    public CntContratoHistorico() {
    }

    public CntContratoHistorico(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the toString
     */
    public String getToString() {
        return toString;
    }

    /**
     * @param toString the toString to set
     */
    public void setToString(String toString) {
        this.toString = toString;
    }

    /**
     * @return the cntContrato
     */
    public CntContrato getCntContrato() {
        return cntContrato;
    }

    /**
     * @param cntContrato the cntContrato to set
     */
    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    /**
     * @return the origen
     */
    public Integer getOrigen() {
        return origen;
    }
    
    /**
     * obtener la descripción del origen
     * @return descripción del origen
     */
    public String getOrigenStr() {
        String mensaje;
        switch(origen) {
            case ORIGEN_CARGA_MASIVA:
                mensaje = "Carga Masiva";
                break;
            case ORIGEN_MANUAL:
                mensaje = "Manual";
                break;
            default:
                mensaje = "";
        }
        return mensaje;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the cntContratoSede
     */
    public CntContratoSede getCntContratoSede() {
        return cntContratoSede;
    }

    /**
     * @param cntContratoSede the cntContratoSede to set
     */
    public void setCntContratoSede(CntContratoSede cntContratoSede) {
        this.cntContratoSede = cntContratoSede;
    }

    /**
     * @return the cntContratoDetalle
     */
    public CntContratoDetalle getCntContratoDetalle() {
        return cntContratoDetalle;
    }

    /**
     * @param cntContratoDetalle the cntContratoDetalle to set
     */
    public void setCntContratoDetalle(CntContratoDetalle cntContratoDetalle) {
        this.cntContratoDetalle = cntContratoDetalle;
    }

    
}
