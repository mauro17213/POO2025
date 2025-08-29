/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.seleccion.bean;

import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.web.mipres.seleccion.servicio.SelCodigoMipresServicioIface;
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
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelCodigoMipresBean extends Url {

    private SelCodigoMipresServicioIface selCodigoMipresServicio;
    private MpCodigoInsumo objeto;
    private List<MpCodigoInsumo> registros;
    private LazyDataModel<MpCodigoInsumo> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        lazyRegistros = new LazyDataModel<MpCodigoInsumo>() {
            private List<MpCodigoInsumo> listaMpCodigoInsumo;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MpCodigoInsumo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMpCodigoInsumo = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMpCodigoInsumo;
            }

            @Override
            public String getRowKey(MpCodigoInsumo maIss2000) {
                return maIss2000.getId().toString();
            }

            @Override
            public MpCodigoInsumo getRowData(String mpCodigoInsumoId) {
                Integer id = Integer.valueOf(mpCodigoInsumoId);
                for (MpCodigoInsumo mpCodigoInsumo : listaMpCodigoInsumo) {
                    if (id.equals(mpCodigoInsumo.getId())) {
                        return mpCodigoInsumo;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelCodigoMipresServicio().cargaInicial(this);
    }

    public MpCodigoInsumo getObjeto() {
        return objeto;
    }

    public void setObjeto(MpCodigoInsumo objeto) {
        this.objeto = objeto;
    }

    public List<MpCodigoInsumo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MpCodigoInsumo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MpCodigoInsumo> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MpCodigoInsumo> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectMpCodigoInsumo(SelectEvent event) {
        objeto = (MpCodigoInsumo) event.getObject();
    }

    /**
     * @return the selCodigoMipresServicio
     */
    public SelCodigoMipresServicioIface getSelCodigoMipresServicio() {
        return selCodigoMipresServicio;
    }

    /**
     * @param selCodigoMipresServicio the selCodigoMipresServicio to set
     */
    public void setSelCodigoMipresServicio(SelCodigoMipresServicioIface selCodigoMipresServicio) {
        this.selCodigoMipresServicio = selCodigoMipresServicio;
    }
}
