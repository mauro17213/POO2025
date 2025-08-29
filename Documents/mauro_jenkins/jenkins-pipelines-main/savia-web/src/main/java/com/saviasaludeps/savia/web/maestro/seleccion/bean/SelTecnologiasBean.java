/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
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
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.TecnologiaServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelTecnologiasBean extends Url {

    private TecnologiaServicioIface selTecnologiaServicio;
    private MaTecnologia objeto;
    private List<MaTecnologia> registros;
    private LazyDataModel<MaTecnologia> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        //parametro activo
        getParamConsulta().setParametroConsulta1("1");
        lazyRegistros = new LazyDataModel<MaTecnologia>() {
            private List<MaTecnologia> listaMaTecnologia;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaTecnologia> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaTecnologia = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaTecnologia;
            }

            @Override
            public String getRowKey(MaTecnologia maTecnologia) {
                return maTecnologia.getId().toString();
            }

            @Override
            public MaTecnologia getRowData(String maTecnologiaId) {
                Integer id = Integer.valueOf(maTecnologiaId);
                for (MaTecnologia maTecnologia : listaMaTecnologia) {
                    if (id.equals(maTecnologia.getId())) {
                        return maTecnologia;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelTecnologiaServicio().cargaInicial(this);
    }

    public TecnologiaServicioIface getSelTecnologiaServicio() {
        return selTecnologiaServicio;
    }

    public void setSelTecnologiaServicio(TecnologiaServicioIface selTecnologiaServicio) {
        this.selTecnologiaServicio = selTecnologiaServicio;
    }

    public MaTecnologia getObjeto() {
        return objeto;
    }

    public void setObjeto(MaTecnologia objeto) {
        this.objeto = objeto;
    }

    public List<MaTecnologia> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaTecnologia> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaTecnologia> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaTecnologia> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectTecnologia(SelectEvent event) {
        objeto = (MaTecnologia) event.getObject();
    }
}
