package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author StivenGV
 */
public class CntjContratoInforme extends Auditoria {

    public static final int TIPO_REGULAR = 1;
    public static final int TIPO_EXTRAORDINARIO = 2;
    public static final int TIPO_FINAL = 3;
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_GENERADO = 1;
    public static final int ESTADO_APROBADO = 2;
    public static final int ESTADO_DEVUELTO = 3;

    public static final String REGULAR = "Ordinario";
    public static final String EXTRAORDINARIO = "Extraordinario";
    public static final String FINAL = "Último Informe";
    public static final String PENDIENTE = "Pendiente";
    public static final String GENERADO = "Generado";
    public static final String APROBADO = "Aprobado";
    public static final String DEVUELTO = "Devuelto";

    private Integer id;
    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    private Integer tipoInforme;
    private Date fechaAprobacion;
    private String observaciones;
    private int estado;
    private List<CntjContratoInformeAdjunto> cntjContratoInformeAdjuntoList;
    private CntjContratoSupervisor cntjContratoSupervisorId;
    private CntjContrato cntjContratoId;
    private Date minimaFechaInicio;
    private Date maximaFechaFin;

    public CntjContratoInforme() {
    }

    public CntjContratoInforme(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicioPeriodo() {
        return fechaInicioPeriodo;
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo;
    }

    public Date getFechaFinPeriodo() {
        return fechaFinPeriodo;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo;
    }

    public Integer getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(Integer tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<CntjContratoInformeAdjunto> getCntjContratoInformeAdjuntoList() {
        return cntjContratoInformeAdjuntoList;
    }

    public void setCntjContratoInformeAdjuntoList(List<CntjContratoInformeAdjunto> cntjContratoInformeAdjuntoList) {
        this.cntjContratoInformeAdjuntoList = cntjContratoInformeAdjuntoList;
    }

    public CntjContratoSupervisor getCntjContratoSupervisorId() {
        return cntjContratoSupervisorId;
    }

    public void setCntjContratoSupervisorId(CntjContratoSupervisor cntjContratoSupervisorId) {
        this.cntjContratoSupervisorId = cntjContratoSupervisorId;
    }

    public CntjContrato getCntjContratoId() {
        return cntjContratoId;
    }

    public void setCntjContratoId(CntjContrato cntjContratoId) {
        this.cntjContratoId = cntjContratoId;
    }

    public Date getMinimaFechaInicio() {
        return minimaFechaInicio;
    }

    public void setMinimaFechaInicio(Date minimaFechaInicio) {
        this.minimaFechaInicio = minimaFechaInicio;
    }

    public Date getMaximaFechaFin() {
        return maximaFechaFin;
    }

    public void setMaximaFechaFin(Date maximaFechaFin) {
        this.maximaFechaFin = maximaFechaFin;
    }
    
    

    //Auxiliares
    public String getTipoInformeStr() {
        String valor = "";
        switch (getTipoInforme()) {
            case TIPO_REGULAR:
                valor = REGULAR;
                break;
            case TIPO_EXTRAORDINARIO:
                valor = EXTRAORDINARIO;
                break;
            case TIPO_FINAL:
                valor = FINAL;
                break;
        }
        return valor;
    }

    public String getEstadoStr() {
        String valor = "";
        switch (getEstado()) {
            case ESTADO_PENDIENTE:
                valor = PENDIENTE;
                break;
            case ESTADO_GENERADO:
                valor = GENERADO;
                break;
            case ESTADO_APROBADO:
                valor = APROBADO;
                break;
            case ESTADO_DEVUELTO:
                valor = DEVUELTO;
                break;
        }
        return valor;
    }

    @Override
    public String toString() {
        return "CntjContratoInforme{" + "id=" + id + ", fechaInicioPeriodo=" + fechaInicioPeriodo + ", fechaFinPeriodo=" + fechaFinPeriodo + ", tipoInforme=" + tipoInforme + ", fechaAprobacion=" + fechaAprobacion + ", observaciones=" + observaciones + ", estado=" + estado + '}';
    }

}
