/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.ServicioHabilitacionServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelServiciosHabilitacionBean extends Url {

    private ServicioHabilitacionServicioIface selServicioHabilitacionServicio;
    private MaServicioHabilitacion objeto;
    private List<MaServicioHabilitacion> registros;
    private LazyDataModel<MaServicioHabilitacion> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        lazyRegistros = new LazyDataModel<MaServicioHabilitacion>() {
            private List<MaServicioHabilitacion> listaMaServicioHabilitacion;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaServicioHabilitacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaServicioHabilitacion = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaServicioHabilitacion;
            }

            @Override
            public String getRowKey(MaServicioHabilitacion maServicioHabilitacion) {
                return maServicioHabilitacion.getId().toString();
            }

            @Override
            public MaServicioHabilitacion getRowData(String maServicioHabilitacionId) {
                Integer id = Integer.valueOf(maServicioHabilitacionId);
                for (MaServicioHabilitacion maServicioHabilitacion : listaMaServicioHabilitacion) {
                    if (id.equals(maServicioHabilitacion.getId())) {
                        return maServicioHabilitacion;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelServicioHabilitacionServicio().cargaInicial(this);
    }

    public ServicioHabilitacionServicioIface getSelServicioHabilitacionServicio() {
        return selServicioHabilitacionServicio;
    }

    public void setSelServicioHabilitacionServicio(ServicioHabilitacionServicioIface selServicioHabilitacionServicio) {
        this.selServicioHabilitacionServicio = selServicioHabilitacionServicio;
    }

    public MaServicioHabilitacion getObjeto() {
        return objeto;
    }

    public void setObjeto(MaServicioHabilitacion objeto) {
        this.objeto = objeto;
    }

    public List<MaServicioHabilitacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaServicioHabilitacion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaServicioHabilitacion> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaServicioHabilitacion> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectServicioHabilitacion(SelectEvent event) {
        objeto = (MaServicioHabilitacion) event.getObject();
    }

    public String getActivo(boolean valor) {
        if (valor) {
            return "Si";
        } else {
            return "No";
        }
    }
}
