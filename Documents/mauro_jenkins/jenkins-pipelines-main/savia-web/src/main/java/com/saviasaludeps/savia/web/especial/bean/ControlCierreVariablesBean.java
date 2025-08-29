/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.especial.PeCierreCarga;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.web.especial.servicio.ControlCierreVariablesIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_CREAR;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jdlopez
 */
@ManagedBean
@ViewScoped
public class ControlCierreVariablesBean extends Url {
    
    @Autowired
    private ControlCierreVariablesIface controlCierreVariablesServicio;
    private PeCierreCarga objeto;
    private LazyDataModel<PeCierreCarga> lazyCierre;
    private List<PeCierreCarga> registros;
    private List<PePrograma> programas;
    private List<Maestro> listaPeriodoCarga;
    private List<Maestro> listaMeses;
    private Date fechaActual;
    
    public ControlCierreVariablesBean() {
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_CONTROL_CIERRES);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");

        } else {
            super.setModulo(mod);
            this.lazyCierre = new LazyDataModel<PeCierreCarga>() {
                
                private List<PeCierreCarga> lista;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PeCierreCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    listar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }
            };

        }
    }
    
    @PostConstruct
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        controlCierreVariablesServicio.cargaInicial(this);
    }
    
     /*
     Metodos de logica y eventos
     */
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        controlCierreVariablesServicio.Accion(this);
        this.procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        controlCierreVariablesServicio.Accion(this);
        procesoFinal();
    }
    
    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        controlCierreVariablesServicio.Accion(this);
        procesoFinal();
    }
    
     public void ver(int id) {
        this.getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        controlCierreVariablesServicio.Accion(this);
        procesoFinal();
    }
    public void editar(int id) {
        this.getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        controlCierreVariablesServicio.Accion(this);
        procesoFinal();
    }
    
    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        controlCierreVariablesServicio.Accion(this);
        procesoFinal();
    }
    
    public String getPeriodoStr(int periodo) {
        if (periodo < 1 || periodo > 12) {
            return "";
        }
        Month mes = Month.of(periodo);
        return mes.getDisplayName(TextStyle.FULL, new Locale("es", "CO")).toUpperCase();
    }
    
    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                if (!this.isError()) {
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVerCierre').show()");
                }
                break;
            case Url.ACCION_CREAR:
                PrimeFaces.current().resetInputs("frmCrear");
                PrimeFaces.current().ajax().update("frmCrear");
                PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                break;
            case Url.ACCION_EDITAR:
                if (!this.isError()) {
                    PrimeFaces.current().resetInputs("frmEditar");
                    PrimeFaces.current().ajax().update("frmEditar");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                }
                break;
            case Url.ACCION_GUARDAR:
                if (!this.isError()) {
                    PrimeFaces.current().ajax().update("frmControlCierre");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                }
                break;
            case Url.ACCION_MODIFICAR:
                if (!this.isError()) {
                    PrimeFaces.current().ajax().update("frmControlCierre");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
                }
            case Url.ACCION_BORRAR:
                break;
            case Url.ACCION_LISTAR:
                break;
            case Url.ACCION_ADICIONAL_2:
                break;
        }
        generarMensajes();
    }
    
    /*
    Metodos getters y setters
    */

    public PeCierreCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(PeCierreCarga objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<PeCierreCarga> getLazyCierre() {
        return lazyCierre;
    }

    public void setLazyCierre(LazyDataModel<PeCierreCarga> lazyCierre) {
        this.lazyCierre = lazyCierre;
    }

    public List<PeCierreCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeCierreCarga> registros) {
        this.registros = registros;
    }

    public List<PePrograma> getProgramas() {
        return programas;
    }

    public void setProgramas(List<PePrograma> programas) {
        this.programas = programas;
    }

    public List<Maestro> getListaPeriodoCarga() {
        return listaPeriodoCarga;
    }

    public void setListaPeriodoCarga(List<Maestro> listaPeriodoCarga) {
        this.listaPeriodoCarga = listaPeriodoCarga;
    }

    public List<Maestro> getListaMeses() {
        return listaMeses;
    }

    public void setListaMeses(List<Maestro> listaMeses) {
        this.listaMeses = listaMeses;
    }

    public Date getFechaActual() {
        return new Date();
    }

    public void setFechaActual(Date fechaMinimaCierre) {
        this.fechaActual = fechaMinimaCierre;
    }
    
}
