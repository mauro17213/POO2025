/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaIss2001Tarifario;
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
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.Iss2001ServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelIss2001Bean extends Url {

    private Iss2001ServicioIface iss2001Servicio;
    private MaIss2001Tarifario objeto;
    private List<MaIss2001Tarifario> registros;
    private LazyDataModel<MaIss2001Tarifario> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        lazyRegistros = new LazyDataModel<MaIss2001Tarifario>() {
            private List<MaIss2001Tarifario> listaMaIss2001Tarifario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaIss2001Tarifario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaIss2001Tarifario = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaIss2001Tarifario;
            }

            @Override
            public String getRowKey(MaIss2001Tarifario maIss2001) {
                return maIss2001.getId().toString();
            }

            @Override
            public MaIss2001Tarifario getRowData(String maIss2001Id) {
                Integer id = Integer.valueOf(maIss2001Id);
                for (MaIss2001Tarifario maIss2001 : listaMaIss2001Tarifario) {
                    if (id.equals(maIss2001.getId())) {
                        return maIss2001;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getIss2001Servicio().cargaInicial(this);
    }

    public Iss2001ServicioIface getIss2001Servicio() {
        return iss2001Servicio;
    }

    public void setIss2001Servicio(Iss2001ServicioIface iss2001Servicio) {
        this.iss2001Servicio = iss2001Servicio;
    }

    public MaIss2001Tarifario getObjeto() {
        return objeto;
    }

    public void setObjeto(MaIss2001Tarifario objeto) {
        this.objeto = objeto;
    }

    public List<MaIss2001Tarifario> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaIss2001Tarifario> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaIss2001Tarifario> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaIss2001Tarifario> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectIss2001(SelectEvent event) {
        objeto = (MaIss2001Tarifario) event.getObject();
    }
}
