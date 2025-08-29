/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudEntregaDetalle extends Auditoria {

    //estado
    public static final int ESTADO_PENDIENTE = 1;
    public static final int ESTADO_EN_GESTION = 2;
    public static final int ESTADO_GESTIONADO = 3;
    public static final int ESTADO_ANULADO = 4;
    //Tipo clasificacion
    public static final int CLASIFICACION_NORMAL = 1;
    public static final int CLASIFICACION_ENTREGA_CON_FALTANTES = 2;
    public static final int CLASIFICACION_FALTANTE_SIN_ENTREGA = 3;
    public static final int CLASIFICACION_FALTANTE_ENTREGADO = 4;

    private Integer id;
    private AuNoSolicitudItem auNoSolicitudItemsId;
    private int numeroEntrega;
    private int catidadTotal;
    private String cantidadAEntregar;
    private int cantidadEntregada;
    private int faltantes;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaHoraEntrega;
    private boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private int tipoEntrega;
    private Integer clasificacionEntrega;

    //variables auxiliares
    private int posicion;
    
    public AuNoSolicitudEntregaDetalle() {

    }

    public AuNoSolicitudEntregaDetalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudItem getAuNoSolicitudItemsId() {
        return auNoSolicitudItemsId;
    }

    public void setAuNoSolicitudItemsId(AuNoSolicitudItem auNoSolicitudItemsId) {
        this.auNoSolicitudItemsId = auNoSolicitudItemsId;
    }

    public int getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(int numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public int getCatidadTotal() {
        return catidadTotal;
    }

    public void setCatidadTotal(int catidadTotal) {
        this.catidadTotal = catidadTotal;
    }

    public String getCantidadAEntregar() {
        return cantidadAEntregar;
    }

    public void setCantidadAEntregar(String cantidadAEntregar) {
        this.cantidadAEntregar = cantidadAEntregar;
    }

    public int getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(int cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public int getFaltantes() {
        return faltantes;
    }

    public void setFaltantes(int faltantes) {
        this.faltantes = faltantes;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Date fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public int getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(int tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Integer getClasificacionEntrega() {
        return clasificacionEntrega;
    }

    public void setClasificacionEntrega(Integer clasificacionEntrega) {
        this.clasificacionEntrega = clasificacionEntrega;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    //Metodo auxiliar
    public String getTipoEntregaStr() {
        String tipoEntregaStr = "";
        switch (tipoEntrega) {
            case AuNoSolicitudEntrega.TIPO_NO_ENTREGADO:
                tipoEntregaStr = "No Entregado";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL:
                tipoEntregaStr = "Total";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL:
                tipoEntregaStr = "Parcial";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_RECLAMA_SIN_ENTREGAS:
                tipoEntregaStr = "Reclama sin entregas";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO:
                tipoEntregaStr = "Anulado";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO:
                tipoEntregaStr = "No Prestado";
                break;
            default:
                break;
        }
        return tipoEntregaStr;
    }
    
    public String getClasificacionEntregaStr() {
        String clasificacionEntregaStr = "";
        if(clasificacionEntrega != null){
            switch (clasificacionEntrega) {
                case CLASIFICACION_NORMAL:
                    clasificacionEntregaStr = "Normal";
                    break;
                case CLASIFICACION_ENTREGA_CON_FALTANTES:
                    clasificacionEntregaStr = "Entrega con faltante";
                    break;
                case CLASIFICACION_FALTANTE_SIN_ENTREGA:
                    clasificacionEntregaStr = "Faltante sin entregar";
                    break;
                case CLASIFICACION_FALTANTE_ENTREGADO:
                    clasificacionEntregaStr = "Faltante entregado";
                    break;
                default:
                    break;
            }
        }
        return clasificacionEntregaStr;
    }
    
    public String establecerColorTipoEntrega() {
        String color = "white";
        try {
            switch (tipoEntrega) {
                case AuNoSolicitudEntrega.TIPO_NO_ENTREGADO:
                    color = "red";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL:
                    color = "green";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL:
                    color = "yellow";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_RECLAMA_SIN_ENTREGAS:
                    color = "blue";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO:
                    color = "orange";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO:
                    color = "black";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            this.addError("Ocurrio un error color rescate");
        }
        return color;
    }

    @Override
    public String toString() {
        return "AuNoSolicitudEntregaDetalle{" + "id=" + id + ", auNoSolicitudItemsId=" + auNoSolicitudItemsId + ", numeroEntrega=" + numeroEntrega + ", catidadTotal=" + catidadTotal + ", cantidadAEntregar=" + cantidadAEntregar + ", cantidadEntregada=" + cantidadEntregada + ", faltantes=" + faltantes + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", fechaHoraEntrega=" + fechaHoraEntrega + ", borrado=" + borrado + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + ", tipoEntrega=" + tipoEntrega + ", posicion=" + posicion + '}';
    }
}
