/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.utilidades.DateUtils;
import com.saviasaludeps.savia.web.administracion.servicio.CalendarioServicioIface;
import com.saviasaludeps.savia.web.administracion.servicio.CalendarioServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class CalendarioBean extends Url {

    private CalendarioServicioIface calendarioServicio;
    private DiaHabil objeto;
    private List<DiaHabil> registros;
    private ScheduleModel lazyEventModel;
    private Date selectedDate;

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public CalendarioBean() {
        this.objeto = new DiaHabil();
        Modulo _mod = super.validarModulo(Modulo.ID_CALENDARIO);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
        }
    }

    @PostConstruct
    public void init() {
        lazyEventModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(LocalDateTime start, LocalDateTime end) {
                getParamConsulta().setParametroConsulta1(DateUtils.asDate(start));
                getParamConsulta().setParametroConsulta2(DateUtils.asDate(end));
                refrescar();
                for (DiaHabil obj : getRegistros()) {
                        DefaultScheduleEvent event = DefaultScheduleEvent.builder().startDate(DateUtils.asLocalDateTime(obj.getFecha())).endDate(DateUtils.asLocalDateTime(obj.getFecha())).allDay(true).build();
                    event.setStyleClass("myclass");
                    addEvent(event);
                }
            }
        };
    }

    public CalendarioServicioIface getCalendarioServicio() {
        if (calendarioServicio == null) {
            calendarioServicio = new CalendarioServicioImpl();
        }
        return calendarioServicio;
    }

    public void setCalendarioServicio(CalendarioServicioIface calendarioServicio) {
        this.calendarioServicio = calendarioServicio;
    }

    public DiaHabil getObjeto() {
        return objeto;
    }

    public void setObjeto(DiaHabil objeto) {
        this.objeto = objeto;
    }

    public List<DiaHabil> getRegistros() {
        return registros;
    }

    public void setRegistros(List<DiaHabil> registros) {
        this.registros = registros;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCalendarioServicio().Accion(this);
    }

    /**
     * Elimina un día hábil
     *
     * @param selectEvent
     */
    public void onEventSelect(SelectEvent selectEvent) {
        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();
        LocalDateTime date = event.getStartDate();
        Date fechaActual = new Date();
        Date fechaSeleccionada = DateUtils.asDate(date);
        if (fechaSeleccionada.before(fechaActual)) {
            addError("No es posible cambiar días anteriores al actual");
            setObjeto(new DiaHabil());
        } else {
            DiaHabil obj = new DiaHabil();
            obj.setFecha(fechaSeleccionada);
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) obj.getFecha());
            obj.setAgno(cal.get(Calendar.YEAR));
            obj.setHabil(false);
            setObjeto(obj);
            super.setAccion(ACCION_MODIFICAR);
            getCalendarioServicio().Accion(this);
        }
        PrimeFaces.current().ajax().update("frmCalendario");
        generarMensajes();
    }

    /**
     * Agrega un nuevo día hábil
     *
     * @param selectEvent
     */
    public void onDateSelect(SelectEvent selectEvent) {
        LocalDateTime date = (LocalDateTime) selectEvent.getObject();
        Date fechaActual = new Date();
        Date fechaSeleccionada = DateUtils.asDate(date);
        if (fechaSeleccionada.before(fechaActual)) {
            addError("No es posible cambiar días anteriores al actual");
            setObjeto(new DiaHabil());
        } else {
            DiaHabil obj = new DiaHabil();
            obj.setFecha(fechaSeleccionada);
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) obj.getFecha());
            obj.setAgno(cal.get(Calendar.YEAR));
            obj.setHabil(false);
            setObjeto(obj);
            super.setAccion(ACCION_MODIFICAR);
            getCalendarioServicio().Accion(this);
        }
        PrimeFaces.current().ajax().update("frmCalendario");
        generarMensajes();
    }

}
