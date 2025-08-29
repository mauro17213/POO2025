/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Recurrencia;
import com.saviasaludeps.savia.web.administracion.servicio.RecurrenciaServicioIface;
import com.saviasaludeps.savia.web.administracion.servicio.RecurrenciaServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author jjmosquera
 */
@ManagedBean
@ViewScoped
public class RecurrenciaBean extends Url {

    private RecurrenciaServicioIface recurrenciaServicio;
    private Recurrencia objeto;
    private List<Recurrencia> registros;
    private LazyDataModel<Recurrencia> lazyRecurrencia;

    private List<Maestro> maestros;

    public RecurrenciaBean() {
        this.objeto = new Recurrencia();
        Modulo _mod = super.validarModulo(Modulo.ID_RECURRENCIAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyRecurrencia = new LazyDataModel<Recurrencia>() {

                private List<Recurrencia> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<Recurrencia> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(Recurrencia objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public Recurrencia getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (Recurrencia objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getRecurrenciaServicio().cargasInicial(this);
        listar();
    }

    public RecurrenciaServicioIface getRecurrenciaServicio() {
        if (recurrenciaServicio == null) {
            recurrenciaServicio = new RecurrenciaServicioImpl();
        }
        return recurrenciaServicio;
    }

    public void setRecurrenciaServicio(RecurrenciaServicioIface recurrenciaServicio) {
        this.recurrenciaServicio = recurrenciaServicio;
    }

    public Recurrencia getObjeto() {
        return objeto;
    }

    public void setObjeto(Recurrencia objeto) {
        this.objeto = objeto;
    }

    public List<Recurrencia> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Recurrencia> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Recurrencia> getLazyRecurrencia() {
        return lazyRecurrencia;
    }

    public void setLazyRecurrencia(LazyDataModel<Recurrencia> lazyRecurrencia) {
        this.lazyRecurrencia = lazyRecurrencia;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getRecurrenciaServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getRecurrenciaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getRecurrenciaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        if (objeto.getFechaFin() == null || objeto.getFechaInicio().before(objeto.getFechaFin()) || objeto.getFechaInicio().equals(objeto.getFechaFin())) {
//            if (objeto.getFechaHoraEjecucion().after(objeto.getFechaInicio()) && objeto.getFechaFin().after(objeto.getFechaHoraEjecucion())
//                    || objeto.getFechaHoraEjecucion().equals(objeto.getFechaInicio()) || objeto.getFechaHoraEjecucion().equals(objeto.getFechaFin())) {
            Recurrencia recurrencia = new Recurrencia();
            String perioricidad = recurrencia.perioticidadNum(objeto.getPeriodicidad(), objeto.getTipoPeriodicidad());
            if (perioricidad != null) {
                addError("Por favor seleccione el tipo de perioricidad adecuado a " + perioricidad);
            } else {
                super.setAccion(ACCION_GUARDAR);
                getRecurrenciaServicio().Accion(this);
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
                }
            }
//            } else {
//                addError("Fecha de ejecucion es erronea");
//            }
        } else {
            addError("La fecha Inicio no debe ser mayor a fecha fin");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getRecurrenciaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        if (objeto.getFechaFin() == null || objeto.getFechaInicio().before(objeto.getFechaFin()) || objeto.getFechaInicio().equals(objeto.getFechaFin())) {
//            if (objeto.getFechaHoraEjecucion().getTime() >= objeto.getFechaInicio().getTime() && objeto.getFechaFin().getTime() <= objeto.getFechaHoraEjecucion().getTime()
//                    || objeto.getFechaHoraEjecucion().equals(objeto.getFechaInicio()) || objeto.getFechaHoraEjecucion().equals(objeto.getFechaFin())) {
            Recurrencia recurrencia = new Recurrencia();
            String perioricidad = recurrencia.perioticidadNum(objeto.getPeriodicidad(), objeto.getTipoPeriodicidad());
            if (perioricidad != null) {
                addError("Por favor seleccione el tipo de perioricidad adecuado a " + perioricidad);
            } else {
                super.setAccion(ACCION_MODIFICAR);
                getRecurrenciaServicio().Accion(this);
                if (!super.isError()) {
                    PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
                }
            }
//            } else {
//                addError("La fecha de ejecucion es erronea");
//            }
        } else {
            addError("La fecha Inicio no debe ser mayor a fecha fin");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getRecurrenciaServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmRecurrencias");
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

}
