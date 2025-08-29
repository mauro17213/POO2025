/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
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
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.Iss2000ServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelIss2000Bean extends Url {

    private Iss2000ServicioIface iss2000Servicio;
    private MaIss2000Tarifario objeto;
    private List<MaIss2000Tarifario> registros;
    private LazyDataModel<MaIss2000Tarifario> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        lazyRegistros = new LazyDataModel<MaIss2000Tarifario>() {
            private List<MaIss2000Tarifario> listaMaIss2000Tarifario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaIss2000Tarifario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaIss2000Tarifario = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaIss2000Tarifario;
            }

            @Override
            public String getRowKey(MaIss2000Tarifario maIss2000) {
                return maIss2000.getId().toString();
            }

            @Override
            public MaIss2000Tarifario getRowData(String maIss2000Id) {
                Integer id = Integer.valueOf(maIss2000Id);
                for (MaIss2000Tarifario maIss2000 : listaMaIss2000Tarifario) {
                    if (id.equals(maIss2000.getId())) {
                        return maIss2000;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getIss2000Servicio().cargaInicial(this);
    }

    public Iss2000ServicioIface getIss2000Servicio() {
        return iss2000Servicio;
    }

    public void setIss2000Servicio(Iss2000ServicioIface iss2000Servicio) {
        this.iss2000Servicio = iss2000Servicio;
    }

    public MaIss2000Tarifario getObjeto() {
        return objeto;
    }

    public void setObjeto(MaIss2000Tarifario objeto) {
        this.objeto = objeto;
    }

    public List<MaIss2000Tarifario> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaIss2000Tarifario> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaIss2000Tarifario> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaIss2000Tarifario> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectIss2000(SelectEvent event) {
        objeto = (MaIss2000Tarifario) event.getObject();
    }
}
