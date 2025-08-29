/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jramirer
 */
public class TuTutela extends Auditoria {

    private Integer id;
    private Integer tuPersonaId;
    private String radicadoNumero;
    private TuTutelaEstado actualTuTutelaEstadoId;
    private Ubicacion gnUbicacionId;
    private TuPersona tuPersona;
    private List<TuAdjunto> adjuntosList;
    
    private Boolean autoCompleatadoFormulario;
    
    private TuTutelaEstado estadoInicial;
//    private Ubicacion ubicacionesId;
    private List<TuTutelaEstado> listaTuTutelaEstado;
    private Boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    
    //2025-07-17 jyperez nuevos campos;
    private Integer cantidadItems;
    private Integer cantidadItemsCerrados;
    
    //Para color
    private String colorVencimiento;
    private Integer diasVencimiento;
    
    public TuTutela() {
        
    }

    public TuTutela(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<TuAdjunto> getAdjuntosList() {
        if (adjuntosList == null){
            adjuntosList = new ArrayList<>();
        }
        return adjuntosList;
    }

    public void setAdjuntosList(List<TuAdjunto> adjuntosList) {
        this.adjuntosList = adjuntosList;
    }
    
    public Integer getTuPersonaId() {
        if(tuPersona == null)
            tuPersona = new TuPersona();
        return tuPersonaId;
    }

    public void setTuPersonaId(Integer tuPersonaId) {
        this.tuPersonaId = tuPersonaId;
    }

    public String getRadicadoNumero() {
        return radicadoNumero;
    }

    public void setRadicadoNumero(String radicadoNumero) {
        this.radicadoNumero = radicadoNumero;
    }
    
    public TuTutelaEstado getActualTuTutelaEstadoId() {
        return actualTuTutelaEstadoId;
    }

    public void setActualTuTutelaEstadoId(TuTutelaEstado actualTuTutelaEstadoId) {
        this.actualTuTutelaEstadoId = actualTuTutelaEstadoId;
    }
    
    public boolean isAutoCompleatadoFormulario() {
        return autoCompleatadoFormulario;
    }

    public Boolean getAutoCompleatadoFormulario() {
        return autoCompleatadoFormulario;
    }

    public void setAutoCompleatadoFormulario(Boolean autoCompleatadoFormulario) {
        this.autoCompleatadoFormulario = autoCompleatadoFormulario;
    }
    
    public Ubicacion getGnUbicacionId() {
        if (gnUbicacionId == null) {
            gnUbicacionId = new Ubicacion();
        }
        return gnUbicacionId;
    }

    public void setGnUbicacionId(Ubicacion gnUbicacionId) {
        this.gnUbicacionId = gnUbicacionId;
    }

    public List<TuTutelaEstado> getListaTuTutelaEstado() {
        if(listaTuTutelaEstado == null){
            listaTuTutelaEstado = new ArrayList<>();
        }
        return listaTuTutelaEstado;
    }

    public void setListaTuTutelaEstado(List<TuTutelaEstado> listaTuTutelaEstado) {
        this.listaTuTutelaEstado = listaTuTutelaEstado;
    }

    public TuPersona getTuPersona() {
        if(tuPersona == null)
            tuPersona = new TuPersona();
        return tuPersona;
    }

    public void setTuPersona(TuPersona tuPersona) {
        this.tuPersona = tuPersona;
    }

    public TuTutelaEstado getEstadoInicial() {
        if(estadoInicial == null)
            estadoInicial = new TuTutelaEstado();
        return estadoInicial;
    }

    public void setEstadoInicial(TuTutelaEstado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
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

    public String getColorVencimiento() {
        return colorVencimiento;
    }

    public void setColorVencimiento(String colorVencimiento) {
        this.colorVencimiento = colorVencimiento;
    }

    /*public Integer getDiasVencimiento() {
        diasVencimiento = 0;
        if (getFechaNotificacion() != null) {
            if (estado != null) {
                if (estado.equals("Cerrado")) {
                    if (getListaTuTutelaEstado().size() > 0) {
                        Date fechaFin = getListaTuTutelaEstado().get(getListaTuTutelaEstado().size() - 1).getFechaHoraCrea();
                        diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), fechaFin);
                    } else {
                        diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), new Date());
                    }
                } else {
                    diasVencimiento = diasEntreFechas(getFechaNotificacion().toString(), new Date());
                }
            } 
        }
        if (diasVencimiento < 0) {
            diasVencimiento = 0;
        }
        return diasVencimiento;
    }*/
    
    public Integer getDiasVencimiento() {
        try{
            diasVencimiento = 0;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strFechaActal = simpleDateFormat.format(new Date());
            if(getActualTuTutelaEstadoId() != null){
                if(getActualTuTutelaEstadoId().getFechaVencimiento() != null){
                    Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(getActualTuTutelaEstadoId().getFechaVencimiento().toString());
                    diasVencimiento = diasEntreFechas(strFechaActal, fechaFin);
                    if (diasVencimiento < 0) {
                        diasVencimiento = diasVencimiento * -1;
                    }
                }
            }
            
        }catch (ParseException ex) {
            Logger.getLogger(TuTutela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diasVencimiento;
    }
    
    public void setDiasVencimiento(Integer diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }
    
    private static int diasEntreFechas(String fechaInicial, Date fechaFinal) throws ParseException {
        Date newFechaInicial = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial);
        int dias = (int) ((fechaFinal.getTime() - newFechaInicial.getTime()) / 86400000);
        return dias;
    }
    
    public String establecerColorTutelas() {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String strFechaActal = simpleDateFormat.format(new Date());
            if(getActualTuTutelaEstadoId() != null){
                if(getActualTuTutelaEstadoId().getFechaVencimiento() != null){
                    Date newDateVencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(getActualTuTutelaEstadoId().getFechaVencimiento().toString());
                    Date newDateActual = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaActal);
                    int posicion = newDateVencimiento.compareTo(newDateActual);
                    if (posicion < 0) {
                        colorVencimiento = "red";
                    } else if (posicion == 0) {
                        colorVencimiento = "yellow";
                    } else {
                        colorVencimiento = "green";
                    }
                }
            }
        } catch (Exception e) {

        }

        return colorVencimiento;
    }
    
    public String establecerColorItemsCerrados() {
        String color = "white";
        try{
            if (getCantidadItems() != null && getCantidadItemsCerrados() != null) {
                if (getCantidadItems() == getCantidadItemsCerrados()) {
                    color = "green";
                } else {
                    color = "red";
                }
            }
        } catch (Exception e) {
            
        }
        return color;
    }

    @Override
    public String toString() {
        return "TuTutela{" + "id=" + id + ", radicadoNumero=" + radicadoNumero + ", actualTuTutelaEstadoId=" + actualTuTutelaEstadoId + ", gnUbicacionId=" + gnUbicacionId + ", tuPersona=" + tuPersona + ", estadoInicial=" + estadoInicial + ", borrado=" + borrado + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + '}';
    }

    /**
     * @return the cantidadItems
     */
    public Integer getCantidadItems() {
        return cantidadItems;
    }

    /**
     * @param cantidadItems the cantidadItems to set
     */
    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    /**
     * @return the cantidadItemCerrados
     */
    public Integer getCantidadItemsCerrados() {
        return cantidadItemsCerrados;
    }

    /**
     * @param cantidadItemCerrados the cantidadItemCerrados to set
     */
    public void setCantidadItemsCerrados(Integer cantidadItemsCerrados) {
        this.cantidadItemsCerrados = cantidadItemsCerrados;
    }
    
}
