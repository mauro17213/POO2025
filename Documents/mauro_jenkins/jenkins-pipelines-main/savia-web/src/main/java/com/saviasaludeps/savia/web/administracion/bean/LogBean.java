/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Log;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.web.administracion.servicio.LogServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.administracion.servicio.LogServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class LogBean extends Url {

    private LogServicioIface logServicio;
    private Log objeto;
    private List<Log> registros;
    private LazyDataModel<Log> lazyLog;

    private Date fechaInicio;
    private Date fechaFin;

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

    public LogBean() {
        this.objeto = new Log();
        Modulo _mod = super.validarModulo(Modulo.ID_LOGS);
        Calendar cal = Calendar.getInstance();
        Calendar calIni = (Calendar) cal.clone();
        calIni.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 00, 00, 00);
        Calendar calFin = (Calendar) cal.clone();
        calFin.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calFin.add(Calendar.MONTH, -1);
        fechaInicio = calIni.getTime();
        fechaFin = calFin.getTime();
        super.getParamConsulta().setParametroConsulta1(fechaInicio);
        super.getParamConsulta().setParametroConsulta2(fechaFin);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyLog = new LazyDataModel<Log>() {

                private List<Log> logs;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<Log> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setParametroConsulta1(fechaInicio);
                    getParamConsulta().setParametroConsulta2(fechaFin);
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    logs = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return logs;
                }

                @Override
                public String getRowKey(Log log) {
                    return log.getId().toString();
                }

                @Override
                public Log getRowData(String logId) {
                    Integer id = Integer.valueOf(logId);
                    for (Log log : logs) {
                        if (id.equals(log.getId())) {
                            return log;
                        }
                    }
                    return null;
                }

            };
        }
    }

    public Log getObjeto() {
        return objeto;
    }

    public void setObjeto(Log objeto) {
        this.objeto = objeto;
    }

    public List<Log> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Log> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Log> getLazyLog() {
        return lazyLog;
    }

    public void setLazyLog(LazyDataModel<Log> lazyLog) {
        this.lazyLog = lazyLog;
    }

    public LogServicioIface getLogServicio() {
        if (logServicio == null) {
            logServicio = new LogServicioImpl();
        }
        return logServicio;
    }

    public void setLogServicio(LogServicioIface logServicio) {
        this.logServicio = logServicio;
    }

    @PostConstruct
    public void postConstruct() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getLogServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getLogServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            crearLog(getObjeto().toString());
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    break;
            }

        }
        generarMensajes();
        PrimeFaces.current().ajax().update("frmLogs");
    }

}
