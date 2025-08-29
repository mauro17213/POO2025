/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
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
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.SoatServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelSoatBean extends Url {

    private SoatServicioIface soatServicio;
    private MaSoatTarifario objeto;
    private List<MaSoatTarifario> registros;
    private LazyDataModel<MaSoatTarifario> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        lazyRegistros = new LazyDataModel<MaSoatTarifario>() {
            private List<MaSoatTarifario> listaMaSoatTarifario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaSoatTarifario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaSoatTarifario = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaSoatTarifario;
            }

            @Override
            public String getRowKey(MaSoatTarifario maSoat) {
                return maSoat.getId().toString();
            }

            @Override
            public MaSoatTarifario getRowData(String maSoatId) {
                Integer id = Integer.valueOf(maSoatId);
                for (MaSoatTarifario maSoat : listaMaSoatTarifario) {
                    if (id.equals(maSoat.getId())) {
                        return maSoat;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSoatServicio().cargaInicial(this);
    }

    public SoatServicioIface getSoatServicio() {
        return soatServicio;
    }

    public void setSoatServicio(SoatServicioIface soatServicio) {
        this.soatServicio = soatServicio;
    }

    public MaSoatTarifario getObjeto() {
        return objeto;
    }

    public void setObjeto(MaSoatTarifario objeto) {
        this.objeto = objeto;
    }

    public List<MaSoatTarifario> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaSoatTarifario> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaSoatTarifario> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaSoatTarifario> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectSoat(SelectEvent event) {
        objeto = (MaSoatTarifario) event.getObject();
    }
}
