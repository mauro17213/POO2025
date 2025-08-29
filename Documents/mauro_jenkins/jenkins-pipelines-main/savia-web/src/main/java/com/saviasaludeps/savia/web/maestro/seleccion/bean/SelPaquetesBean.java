/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
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
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.PaqueteServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelPaquetesBean extends Url {

    private PaqueteServicioIface selPaqueteServicio;
    private MaPaquete objeto;
    private List<MaPaquete> registros;
    private LazyDataModel<MaPaquete> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        //parametro activo
        getParamConsulta().setParametroConsulta1("1");
        lazyRegistros = new LazyDataModel<MaPaquete>() {
            private List<MaPaquete> listaMaPaquete;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaPaquete> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaPaquete = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaPaquete;
            }

            @Override
            public String getRowKey(MaPaquete maPaquete) {
                return maPaquete.getId().toString();
            }

            @Override
            public MaPaquete getRowData(String maPaqueteId) {
                Integer id = Integer.valueOf(maPaqueteId);
                for (MaPaquete maPaquete : listaMaPaquete) {
                    if (id.equals(maPaquete.getId())) {
                        return maPaquete;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelPaqueteServicio().cargaInicial(this);
    }

    public PaqueteServicioIface getSelPaqueteServicio() {
        return selPaqueteServicio;
    }

    public void setSelPaqueteServicio(PaqueteServicioIface selPaqueteServicio) {
        this.selPaqueteServicio = selPaqueteServicio;
    }

    public MaPaquete getObjeto() {
        return objeto;
    }

    public void setObjeto(MaPaquete objeto) {
        this.objeto = objeto;
    }

    public List<MaPaquete> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaPaquete> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaPaquete> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaPaquete> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectPaquete(SelectEvent event) {
        objeto = (MaPaquete) event.getObject();
    }
}
