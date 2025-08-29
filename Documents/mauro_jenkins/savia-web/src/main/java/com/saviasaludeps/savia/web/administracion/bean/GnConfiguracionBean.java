/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.GnConfiguracion;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.web.administracion.servicio.GnConfiguracionServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class GnConfiguracionBean extends Url {

    private GnConfiguracionServicioIface gnConfiguracionServicio;
    private GnConfiguracion objeto;
    private List<GnConfiguracion> registros;
    private LazyDataModel<GnConfiguracion> lazyGnConfiguracion;

    public GnConfiguracionBean() {
        this.objeto = new GnConfiguracion();
        Modulo mod = super.validarModulo(Modulo.ID_GN_CONFIGURACION);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);

            lazyGnConfiguracion = new LazyDataModel<GnConfiguracion>() {
                private List<GnConfiguracion> gnConfiguraciones;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GnConfiguracion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    gnConfiguraciones = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return gnConfiguraciones;
                }

                @Override
                public String getRowKey(GnConfiguracion configuracionObj) {
                    return configuracionObj.getId().toString();
                }

                @Override
                public GnConfiguracion getRowData(String portabilidadId) {
                    Integer id = Integer.valueOf(portabilidadId);
                    for (GnConfiguracion portabilidad : gnConfiguraciones) {
                        if (id.equals(portabilidad.getId())) {
                            return portabilidad;
                        }
                    }
                    return null;
                }
            };
        }
    }

    public GnConfiguracionServicioIface getGnConfiguracionServicio() {
        return gnConfiguracionServicio;
    }

    public void setGnConfiguracionServicio(GnConfiguracionServicioIface gnConfiguracionServicio) {
        this.gnConfiguracionServicio = gnConfiguracionServicio;
    }

    public GnConfiguracion getObjeto() {
        return objeto;
    }

    public void setObjeto(GnConfiguracion objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<GnConfiguracion> getLazyGnConfiguracion() {
        return lazyGnConfiguracion;
    }

    public void setLazyGnConfiguracion(LazyDataModel<GnConfiguracion> lazyGnConfiguracion) {
        this.lazyGnConfiguracion = lazyGnConfiguracion;
    }

    public List<GnConfiguracion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GnConfiguracion> registros) {
        this.registros = registros;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGnConfiguracionServicio().Accion(this);
    }

    public void modificarConfiguracion(RowEditEvent event) {
        setObjeto((GnConfiguracion) event.getObject());
        super.setAccion(ACCION_EDITAR);
        getGnConfiguracionServicio().Accion(this);
        procesoFinal();
    }

    public void cantidadCarga() {
        try {
            addMensaje("Registros cargados. " + PropApl.getInstance().cantidad());
            generarMensajes();
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
    }

    public void refrescarConfiguracion() {
        try {
            PropApl.getInstance().actualizar();
            addMensaje("Se actualizo la configuración del sistema correctamente.");
            generarMensajes();
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getGnConfiguracionServicio().Accion(this);
        setObjeto(new GnConfiguracion());
        refrescar();
        PrimeFaces.current().ajax().update("frmConfiguracion");
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
        }
        generarMensajes();
    }

}
