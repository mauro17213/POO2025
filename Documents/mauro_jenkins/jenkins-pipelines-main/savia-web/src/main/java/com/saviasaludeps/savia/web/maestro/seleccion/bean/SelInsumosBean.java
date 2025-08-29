/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
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
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.InsumoServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelInsumosBean extends Url {

    private InsumoServicioIface selInsumoServicio;
    private MaInsumo objeto;
    private List<MaInsumo> registros;
    private LazyDataModel<MaInsumo> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        //parametro activo
        getParamConsulta().setParametroConsulta1("1");
        lazyRegistros = new LazyDataModel<MaInsumo>() {
            private List<MaInsumo> listaMaInsumo;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaInsumo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaInsumo = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaInsumo;
            }

            @Override
            public String getRowKey(MaInsumo maInsumo) {
                return maInsumo.getId().toString();
            }

            @Override
            public MaInsumo getRowData(String maInsumoId) {
                Integer id = Integer.valueOf(maInsumoId);
                for (MaInsumo maInsumo : listaMaInsumo) {
                    if (id.equals(maInsumo.getId())) {
                        return maInsumo;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelInsumoServicio().cargaInicial(this);
    }

    public InsumoServicioIface getSelInsumoServicio() {
        return selInsumoServicio;
    }

    public void setSelInsumoServicio(InsumoServicioIface selInsumoServicio) {
        this.selInsumoServicio = selInsumoServicio;
    }

    public MaInsumo getObjeto() {
        return objeto;
    }

    public void setObjeto(MaInsumo objeto) {
        this.objeto = objeto;
    }

    public List<MaInsumo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaInsumo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaInsumo> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaInsumo> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectInsumo(SelectEvent event) {
        objeto = (MaInsumo) event.getObject();
    }
}
