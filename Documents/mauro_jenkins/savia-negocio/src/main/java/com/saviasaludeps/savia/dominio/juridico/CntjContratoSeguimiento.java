package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author StivenGV
 */
public class CntjContratoSeguimiento extends Auditoria {
    
    public static final int PERIODICIDAD_MENSUAL = 1;
    public static final int PERIODICIDAD_BIMESTRAL = 2;
    public static final int PERIODICIDAD_SEMESTRAL = 3;
    
    public static final int ESTADO_PENDIENTE = 1;
    public static final int ESTADO_EN_PROCESO = 2;
    public static final int ESTADO_EN_REVISION = 3;
    public static final int ESTADO_REQUIERRE_CORRECION = 4;
    public static final int ESTADO_ACTIVO = 5;
    public static final int ESTADO_FINALIZADO = 6;
    public static final int ESTADO_CANCELADO = 7;
    
    private Integer id;
    private int periodicidad;
    private Date fechaInicioSeguimiento;
    private Date fechaFinSeguimiento;
    private int estadoSeguimiento;
    private String observaciones;
    private CntjContrato cntjContratoId;
    private CntjTercero cntjTerceroId;

    public CntjContratoSeguimiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(int periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Date getFechaInicioSeguimiento() {
        return fechaInicioSeguimiento;
    }

    public void setFechaInicioSeguimiento(Date fechaInicioSeguimiento) {
        this.fechaInicioSeguimiento = fechaInicioSeguimiento;
    }

    public Date getFechaFinSeguimiento() {
        return fechaFinSeguimiento;
    }

    public void setFechaFinSeguimiento(Date fechaFinSeguimiento) {
        this.fechaFinSeguimiento = fechaFinSeguimiento;
    }

    public int getEstadoSeguimiento() {
        return estadoSeguimiento;
    }

    public void setEstadoSeguimiento(int estadoSeguimiento) {
        this.estadoSeguimiento = estadoSeguimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CntjContrato getCntjContratoId() {
        return cntjContratoId;
    }

    public void setCntjContratoId(CntjContrato cntjContratoId) {
        this.cntjContratoId = cntjContratoId;
    }

    public CntjTercero getCntjTerceroId() {
        return cntjTerceroId;
    }

    public void setCntjTerceroId(CntjTercero cntjTerceroId) {
        this.cntjTerceroId = cntjTerceroId;
    }
    
    //Auxiliares
    public String getPeriodicidadStr() {
       String valor = "";
        switch (getPeriodicidad()) {
            case 1:
                valor = "MENSUAL";
                break;
            case 2:
                valor = "BIMESTRAL";
                break;
            case 3:
                valor = "SEMESTRAL";
                break;
        }
        return valor;
    }
    
    public String getEstadoSeguimientoStr() {
       String valor = "";
        switch (getPeriodicidad()) {
            case 1:
                valor = "PENDIENTE";
                break;
            case 2:
                valor = "EN PROCESO";
                break;
            case 3:
                valor = "EN REVISIÓN";
                break;
            case 4:
                valor = "REQUIERE CORRECIÓN";
                break;
            case 5:
                valor = "ACTIVO";
                break;
            case 6:
                valor = "FINALIZADO";
                break;
            case 7:
                valor = "CANCELADO";
                break;
        }
        return valor;
    } 
    
}
