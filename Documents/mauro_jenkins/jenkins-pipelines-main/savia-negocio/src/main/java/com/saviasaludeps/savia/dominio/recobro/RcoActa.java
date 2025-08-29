/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class RcoActa extends Auditoria{
     
    public static final String LISTA_ASISTENTES = "LISTA_ASISTENTES";
   
    private Integer id;
    private RcoConciliacion rcoConciliacionesId;
    private String area;
    private String asunto;
    private String lugar;
    private String orderDelDia;
    private String desarrolloReunion;
    private String observaciones;
    
    //variables axuliares
    private Long longValorConciliacion;
    private Long longValorTotalConciliado;
    private Long longValorRestanteNoConciliado;
    private String strUsuarioCrea;
    private Date strFechaHoraCrea;
    private String strFechaActaConciliacion;
    private List<RcoActaAsistente> listaRcoActaAsistente;
    
    public RcoActa(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RcoConciliacion getRcoConciliacionesId() {
        return rcoConciliacionesId;
    }

    public void setRcoConciliacionesId(RcoConciliacion rcoConciliacionesId) {
        this.rcoConciliacionesId = rcoConciliacionesId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getOrderDelDia() {
        return orderDelDia;
    }

    public void setOrderDelDia(String orderDelDia) {
        this.orderDelDia = orderDelDia;
    }

    public String getDesarrolloReunion() {
        return desarrolloReunion;
    }

    public void setDesarrolloReunion(String desarrolloReunion) {
        this.desarrolloReunion = desarrolloReunion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getLongValorConciliacion() {
        return longValorConciliacion;
    }

    public void setLongValorConciliacion(Long longValorConciliacion) {
        this.longValorConciliacion = longValorConciliacion;
    }

    public Long getLongValorTotalConciliado() {
        return longValorTotalConciliado;
    }

    public void setLongValorTotalConciliado(Long longValorTotalConciliado) {
        this.longValorTotalConciliado = longValorTotalConciliado;
    }

    public Long getLongValorRestanteNoConciliado() {
        return longValorRestanteNoConciliado;
    }

    public void setLongValorRestanteNoConciliado(Long longValorRestanteNoConciliado) {
        this.longValorRestanteNoConciliado = longValorRestanteNoConciliado;
    }

    public String getStrUsuarioCrea() {
        return strUsuarioCrea;
    }

    public void setStrUsuarioCrea(String strUsuarioCrea) {
        this.strUsuarioCrea = strUsuarioCrea;
    }

    public Date getStrFechaHoraCrea() {
        return strFechaHoraCrea;
    }

    public void setStrFechaHoraCrea(Date strFechaHoraCrea) {
        this.strFechaHoraCrea = strFechaHoraCrea;
    }

    public String getStrFechaActaConciliacion() {
        return strFechaActaConciliacion;
    }

    public void setStrFechaActaConciliacion(String strFechaActaConciliacion) {
        this.strFechaActaConciliacion = strFechaActaConciliacion;
    }

    public List<RcoActaAsistente> getListaRcoActaAsistente() {
        return listaRcoActaAsistente;
    }

    public void setListaRcoActaAsistente(List<RcoActaAsistente> listaRcoActaAsistente) {
        this.listaRcoActaAsistente = listaRcoActaAsistente;
    }
    
}
